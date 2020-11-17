<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page import="java.util.List, java.util.Iterator, java.util.ArrayList, mx.gob.stps.portal.core.infra.utils.Constantes, mx.gob.stps.portal.persistencia.vo.ReferenciaLaboralVO, java.util.Calendar, java.util.Date, java.text.DateFormat, java.text.SimpleDateFormat, java.text.ParseException"%>
<%
	int rowCounter = 0;
	String vProxy = "http://orangoo.com/newnox?lang=";
	pageContext.setAttribute("vProxy",vProxy);
	List<ReferenciaLaboralVO> references = new ArrayList<ReferenciaLaboralVO>();
	if (null != request.getSession().getAttribute("LST_REFERENCES"))
		references = (List<ReferenciaLaboralVO>)request.getSession().getAttribute("LST_REFERENCES");
	String fechaIngreso = null != session.getAttribute("fechaIngreso") ? (String)session.getAttribute("fechaIngreso") : "";
	String fechaTerminacion = null != session.getAttribute("fechaTerminacion") ? (String)session.getAttribute("fechaTerminacion") : "";
%>
<script>
	dojo.addOnLoad(function() {
    	entidadFederativaStore.url = "${context}domicilio.do?method=obtenerEntidad";
	    entidadFederativaStore.close();
	    loadHistFeds();
	});
</script>
<script>
	function actulizaMunicipio() {
		var vEntidad = dijit.byId('idEntidadEmpresaSelect').get('value');
		if (vEntidad) {
			municipioStore.url = '';
			municipioStore.close();			

			coloniaStore.url = '';
			coloniaStore.close();			
		
			dojo.byId('codigoPostalEmpresa').value = '';
			dijit.byId('idMunicipioEmpresaSelect').reset();
			dijit.byId('idColoniaEmpresaSelect').reset();
			municipioStore.url = "${context}domicilio.do?method=obtenerMunicipio" + "&" + "idEntidad="+ vEntidad;
			municipioStore.close();			
		}
	}
	function actulizaColonia() {
		var vEntidad   = dijit.byId('idEntidadEmpresaSelect').get('value');
		var vMunicipio = dijit.byId('idMunicipioEmpresaSelect').get('value');
		if (vEntidad && vMunicipio){
			dojo.byId('codigoPostalEmpresa').value = '';
			dijit.byId('idColoniaEmpresaSelect').reset();
			dijit.byId('idLocalidadEmpresaSelect').reset();
			coloniaStore.url = "${context}domicilio.do?method=obtenerColonia" + "&" + "idEntidad="+ vEntidad + "&" + "idMunicipio="+ vMunicipio;
			coloniaStore.close();
			localidadStore.url = "${context}domicilio.do?method=obtenerLocalidades" + "&" + "idEntidad="+ vEntidad + "&" + "idMunicipio="+ vMunicipio;
			localidadStore.close();
		}
	}
	function actulizaCodigoPostal() {
		var vEntidad   = dijit.byId('idEntidadEmpresaSelect').get('value');
		var vMunicipio = dijit.byId('idMunicipioEmpresaSelect').get('value');
		var vColonia = dijit.byId('idColoniaEmpresaSelect').get('value');;
		if (vEntidad && vMunicipio && vColonia){
	    	var url = '${context}domicilio.do?method=obtenerCodigoPostal&idEntidad='+ vEntidad + '&idMunicipio='+ vMunicipio + '&idColonia='+ vColonia;
			dojo.xhrGet({url: url, sync: true, timeout:180000,
		       load: function(data){
					       var res = dojo.fromJson(data);
						   dojo.byId('codigoPostalEmpresa').value = res.codigoPostal;
					    }
				});
		} else {
			dojo.byId('codigoPostalEmpresa').value='';
		}
	}
	function fillUpAccessKey() {
		var accesoDes;
		var tipoTelefonoId = getRadioValue('idTipoTelefono');
	    if (tipoTelefonoId == 1) {
	    	accesoDes = <%="'" + Constantes.CLAVE_TELEFONO_CELULAR + "'"%>;
	    	document.getElementById('extension').value=''; 
	    	dijit.byId('extension').disabled=true;
	    	document.getElementById('extension').readOnly=true;
	    }else {
	    	accesoDes = <%="'" + Constantes.CLAVE_TELEFONO_FIJO + "'"%>;
	    	document.getElementById('extension').value=''; 
	    	dijit.byId('extension').disabled=false;
	    	document.getElementById('extension').readOnly=false;
	    } 		
	    var wAcceso = dijit.byId('acceso');
	    wAcceso.attr('value',accesoDes);
	}
	function changePhoneSizeRequired() {
		var vClave = document.getElementById('clave');
		var vTelefono = document.getElementById('telefono');
		if(vClave.value.length < 2){
			vClave.focus();
			message('Debe proporcionar una clave LADA de 2 ó 3 caracteres');
			return false;
		} else if(vClave.value.length == 2) {
			if(vTelefono.value.length != 8){
				vClave.focus();
				message('Debe proporcionar un teléfono de 8 dígitos');
				return false;
			}
		} else if(vClave.value.length==3){
			if(vTelefono.value.length!=7){
				vClave.focus();
				message('Debe proporcionar un teléfono de 7 dígitos');
				return false;
			}	
		}
		return true;
	}
	function loadHistFeds() {
		var vEntidad = '${ProgramaForm.referenciaPrincipal.domicilio.idEntidad}';
		var vMunicipio = '${ProgramaForm.referenciaPrincipal.domicilio.idMunicipio}';
		var vColonia = '${ProgramaForm.referenciaPrincipal.domicilio.idColonia}';
		entidadFederativaStore.fetch({
    		sync: true,
          	onComplete: function(items, request) {
	          	if (items.length == 0) return;
	          	dijit.byId('idEntidadEmpresaSelect').attr('value', '${ProgramaForm.referenciaPrincipal.domicilio.idEntidad}');
	    		municipioStore.url = "${context}domicilio.do?method=obtenerMunicipio" + "&" + "idEntidad="+ vEntidad;
	    		municipioStore.close();
	    		municipioStore.fetch({
		    		sync: true,
		         	onComplete: function(items, request) {
		          		if (items.length == 0) return;
		          		dijit.byId('idMunicipioEmpresaSelect').attr('value', '${ProgramaForm.referenciaPrincipal.domicilio.idMunicipio}');
		    			coloniaStore.url = "${context}domicilio.do?method=obtenerColonia" + "&" + "idEntidad="+ vEntidad + "&" + "idMunicipio="+ vMunicipio;
		    			coloniaStore.close();
		    			coloniaStore.fetch({
		    				sync: true,
		          			onComplete: function(items, request) {
		          				if (items.length == 0) return;                   	
		          				dijit.byId('idColoniaEmpresaSelect').attr('value', '${ProgramaForm.referenciaPrincipal.domicilio.idColonia}');
		          			}
		    			});
		    			localidadStore.url = "${context}domicilio.do?method=obtenerLocalidades" + "&" + "idEntidad="+ vEntidad + "&" + "idMunicipio="+ vMunicipio;
		    			localidadStore.close();
		    			localidadStore.fetch({
		    		    	sync: true,
		    		        onComplete: function(items, request) {
		    		        	if (items.length == 0) return;                   	
		    		          	dijit.byId('idLocalidadEmpresaSelect').attr('value', '${ProgramaForm.referenciaPrincipal.domicilio.idLocalidad}');
		    		        }
		    		    });
		          	}
	    		});
          	}
    	});
	}
</script>
<div dojoType="dojo.data.ItemFileReadStore" jsId="entidadFederativaStore" urlPreventCache="true" clearOnClose="true"></div>
<div dojoType="dojo.data.ItemFileReadStore" jsId="municipioStore" urlPreventCache="true" clearOnClose="true"></div>
<div dojoType="dojo.data.ItemFileReadStore" jsId="coloniaStore" urlPreventCache="true" clearOnClose="true"></div>
<div dojoType="dojo.data.ItemFileReadStore" jsId="localidadStore" urlPreventCache="true" clearOnClose="true"></div>
<div id="addReference" style="display:block">
	<fieldset class="ultimo_trabajo bloque">
		<legend>Experiencia Laboral más reciente</legend>
		<div class="grid1_3 margin_top_10 fl">
			<div id="spanEmpresaReq" style="display:none"><label for="empresa"><span >*</span> Nombre o razón social de la empresa</label></div>
			<div id="spanEmpresa" style="display:block"><label for="empresa">Nombre o razón social de la empresa</label></div>
			<input type="text" name="empresa" id="empresa" regExp="^[A-Za-z0-9\s\d\-.&,áéíóúÁÉÍÓÚñÑ']{3,150}$" uppercase="true" onblur="setRequiredJob();"
				dojoType="dijit.form.ValidationTextBox" required="true"  maxlength="150" value="${ProgramaForm.perfil.trabajoActual.empresa}"
				invalidMessage="El Nombre o	razón social de la empresa es invalido, favor de verificar" trim ="true"
				missingMessage="Es necesario indicar que Nombre o razón social de la empresa." />
		</div>
		<div class="fl margin_top_30">
			<label class="fw_no_bold" for="confidencialidadEmpresa">
				<input type="checkbox" dojoType="dijit.form.CheckBox" name="confidencialidadEmpresa" id="confidencialidadEmpresa" value="${mostrarEmpSI}"
					${ProgramaForm.perfil.trabajoActual.confidencialidadEmpresa eq mostrarEmpSI ? 'checked="checked"' : '' } required="false" />
				Omitir este dato en la publicación de mi perfil</label>
		</div>
		<div class="clearfix"></div>
		<div class="grid1_3 margin_top_10 fl">
			<div id="spanIngresoReq" style="display:none"><label for="laboresInicialDia"><span>*</span> Fecha de ingreso</label></div>
			<div id="spanIngreso" style="display:block"><label for="laboresInicialDia">Fecha de ingreso</label></div>
			<select dojotype="dijit.form.FilteringSelect" name="laboresInicialDia" id="laboresInicialDia" missingMessage="Es necesario indicar la Fecha de ingreso.">
				<option value="0">Día</option>
				<%=getSelectedDay(fechaIngreso)%>
			</select>
			<select dojotype="dijit.form.FilteringSelect" name="laboresInicialMes" id="laboresInicialMes" missingMessage="Es necesario indicar la Fecha de ingreso.">
				<option value="0">Mes</option>
				<%=getSelectedMonth(fechaIngreso) %>
			</select>
			<select dojotype="dijit.form.FilteringSelect" name="laboresInicialAnual" id="laboresInicialAnual" missingMessage="Es necesario indicar la Fecha de ingreso.">
				<option value="0">Año</option>
				<%=getSelectedYear(fechaIngreso) %>
			</select>
		</div>
		<div class="grid1_3 margin_top_10 fl">
			<div id="spanTerminacionReq" style="display:none"><label for="laboresFinalDia"><span>*</span> Fecha de terminación</label></div>
			<div id="spanTerminacion" style="display:block"><label for="laboresFinalDia">Fecha de terminación</label></div>
			<select dojotype="dijit.form.FilteringSelect" name="laboresFinalDia" id="laboresFinalDia" missingMessage="Es necesario indicar la Fecha de terminación.">
				<option value="0">Día</option>
				<%=getSelectedDay(fechaTerminacion)%>
			</select>
			<select dojotype="dijit.form.FilteringSelect" name="laboresFinalMes" id="laboresFinalMes" missingMessage="Es necesario indicar la Fecha de terminación.">
				<option value="0">Mes</option>
				<%=getSelectedMonth(fechaTerminacion) %>
			</select>
			<select dojotype="dijit.form.FilteringSelect" name="laboresFinalAnual" id="laboresFinalAnual" missingMessage="Es necesario indicar la Fecha de terminación.">
				<option value="0">Año</option>
				<%=getSelectedYear(fechaTerminacion) %>
			</select>
		</div>
		<div class="grid1_3 margin_top_10 fl">
			<div id="spanPuestoReq" style="display:none"><label for="puesto"><span>*</span> Puesto desempeñado</label></div>
			<div id="spanPuesto" style="display:block"><label for="puesto">Puesto desempeñado</label></div>
			<input type="text"  dojoType="dijit.form.ValidationTextBox" name="puesto" id="puesto" maxlength="150" regExp="^[A-Za-z\s\-.áéíóúÁÉÍÓÚñÑ']{1,150}$"
				value="${ProgramaForm.perfil.trabajoActual.puesto}" required="false" invalidMessage="El valor especificado puesto desempeñado no es válido."
				trim ="true" uppercase="true" missingMessage="Es necesario selecionar el puesto desempeñado." />
		</div>
		<div class="grid1_3 margin_top_10 fl">
			<div id="spanJerarquiaReq" style="display:none"><label for="idJerarquiaSelect"><span>*</span> Jerarquía del puesto</label></div>
			<div id="spanJerarquia" style="display:block"><label for="idJerarquiaSelect">Jerarquía del puesto</label></div>
			<input type="hidden" name="idJerarquia" id="idJerarquia" dojoType="dijit.form.TextBox" />
			<select dojotype="dijit.form.FilteringSelect" id="idJerarquiaSelect" value="${ProgramaForm.perfil.trabajoActual.idJerarquia}" required="false"
				invalidMessage="El valor especificado jerarquía del puesto no es válido." missingMessage="Es necesario selecionar el Jerarquía del puesto." autocomplete="true">
				<c:forEach var="row" items="${jerarquias}">
					<option value="${row.idCatalogoOpcion}">${row.opcion}</option>
				</c:forEach>
			</select>
		</div>
		<div class="grid1_3 margin_top_10 fl">
			<div id="spanPersonasReq" style="display:none"><label for="personasCargoSelect"><span>*</span> Número de personas a cargo</label></div>
			<div id="spanPersonas" style="display:block"><label for="personasCargoSelect">Número de personas a cargo</label></div>
			<input type="hidden" name="personasCargo" id="personasCargo" dojoType="dijit.form.TextBox" />
			<select dojotype="dijit.form.FilteringSelect" id="personasCargoSelect" value="${ProgramaForm.perfil.trabajoActual.personasCargo}" 
				required="true" missingMessage="Es necesario seleccionar el número de personas a cargo." autocomplete="true">
				<c:forEach var="row" items="${personascargo}">
					<option value="${row.idCatalogoOpcion}">${row.opcion}</option>
				</c:forEach>
			</select>
		</div>
		<div class="grid1_3 margin_top_10 fl">
			<div id="spanSalarioReq" style="display:none"><label for="salarioMensual"><span>*</span> Salario mensual recibido</label></div>
			<div id="spanSalario" style="display:block"><label for="salarioMensual">Salario mensual recibido</label></div>
			<input type="text" name="salarioMensual" id="salarioMensual" dojoType="dijit.form.ValidationTextBox" required="true"  maxlength="9"
				value="${ProgramaForm.perfil.trabajoActual.salarioMensual}" invalidMessage="El valor especificado no es válido por Salario Mensual recibido."
				regExp="^[+]?\d{1,6}(\.\d{1,2})?$" trim="true" invalidMessage="El valor especificado no es válido por Salario Mensual recibido." />
		</div>
		<div class="clearfix"></div>
		<div class="grid1_3 margin_top_10 fl">
			<div id="spanCalleEmpresaReq" style="display:none"><label for="calleEmpresa"><span>*</span> Calle</label></div>
			<div id="spanCalleEmpresa" style="display:block"><label for="calleEmpresa">Calle</label></div>
				<input type="text" name="calleEmpresa" id="calleEmpresa" dojoType="dijit.form.ValidationTextBox" maxlength="150" trim="true"
					value="${ProgramaForm.referenciaPrincipal.domicilio.calle}" regExp="^[\w\d\s\.áéíóúÁÉÍÓÚñÑ]{1,150}$"
					invalidMessage="El valor especificado no es válido para calle de la experiencia." required="false"
					missingMessage="Es neccesario indicar calle de la experiencia." uppercase="true"/>
		</div>
		<div class="grid1_3 margin_top_10 fl">
			<div id="spanNumExtEmpresaReq" style="display:none"><label for="numExtEmpresa"><span>*</span> Número exterior</label></div>
			<div id="spanNumExtEmpresa" style="display:block"><label for="numExtEmpresa">Número exterior</label></div>
			<input type="text" name="numExtEmpresa" id="numExtEmpresa" dojoType="dijit.form.ValidationTextBox" maxlength="50" required="true"
				value="${ProgramaForm.referenciaPrincipal.domicilio.numeroExterior}"
				trim="true" regExp="^[\w\d\s]{1,50}$" invalidMessage="El valor especificado no es válido para número exterior de la experiencia."
				missingMessage="Es neccesario indicar número exterior de la experiencia." />
		</div>
		<div class="grid1_3 margin_top_10 fl">
			<label for="numIntEmpresa">Número interior</label>
			<input type="text" name="numIntEmpresa" id="numIntEmpresa" dojoType="dijit.form.ValidationTextBox" maxlength="50"
				value="${ProgramaForm.referenciaPrincipal.domicilio.numeroInterior}" required="false" trim="true" regExp="^[\w\d\s]{1,50}"
				invalidMessage="El valor especificado no es válido para número interior de la experiencia."
				missingMessage="Es necesario indicar número interior de la experiencia." />
		</div>
		<div class="grid1_3 margin_top_10 fl">
			<div id="spanIdEntidadSelectReq" style="display:none"><label for="idEntidadSelect"><span>*</span> Entidad Federativa</label></div>
			<div id="spanIdEntidadSelect" style="display:block"><label for="idEntidadSelect">Entidad Federativa</label></div>
			<input type="hidden" name="idEntidadEmpresa" id="idEntidadEmpresa" dojoType="dijit.form.TextBox">
				<select dojotype="dijit.form.FilteringSelect" store="entidadFederativaStore" id="idEntidadEmpresaSelect" required="false"
					invalidMessage="Dato no válido" missingMessage="Es necesario indicar la entidad." autocomplete="true"
					onchange="javascript:actulizaMunicipio();" scrollOnFocus="true" maxHeight="250">
				</select>
		</div>
		<div class="grid1_3 margin_top_10 fl">
			<div id="spanIdMunicipioSelectReq" style="display:none"><label for="idMunicipioSelect"><span>*</span> Municipio o Delegación</label></div>
			<div id="spanIdMunicipioSelect" style="display:block"><label for="idMunicipioSelect">Municipio o Delegación</label></div>
			<input type="hidden" name="idMunicipioEmpresa" id="idMunicipioEmpresa" dojoType="dijit.form.TextBox">
			<select dojotype="dijit.form.FilteringSelect" store="municipioStore" id="idMunicipioEmpresaSelect" required="false"
				invalidMessage="Dato no válido" missingMessage="Es necesario indicar un municipio."
				autocomplete="true" onchange="javascript:actulizaColonia();" scrollOnFocus="true" maxHeight="250">
			</select>
		</div>
		<div class="grid1_3 margin_top_10 fl">
			<div id="spanIdColoniaSelectReq" style="display:none"><label for="idColonia"><span>*</span> Colonia</label></div>
			<div id="spanIdColoniaSelect" style="display:block"><label for="idColonia">Colonia</label></div>
			<input type="hidden" name="idColoniaEmpresa" id="idColoniaEmpresa" dojoType="dijit.form.TextBox">
			<select dojotype="dijit.form.FilteringSelect" store="coloniaStore" id="idColoniaEmpresaSelect" required="false"
				invalidMessage="Dato inválido" missingMessage="Es necesario indicar la colonia." autocomplete="true"
				onchange="javascript:actulizaCodigoPostal();" scrollOnFocus="true" maxHeight="250">
			</select>
		</div>
		<div class="grid1_3 margin_top_10 fl">
			<div id="spanIdLocalidadSelectReq" style="display:none"><label for="idLocalidadEmpresa"><span>*</span> Localidad</label></div>
			<div id="spanIdLocalidadSelect" style="display:block"><label for="idLocalidadEmpresa">Localidad</label></div>
			<input type="hidden" name="idLocalidadEmpresa" id="idLocalidadEmpresa" dojoType="dijit.form.TextBox">
			<select dojotype="dijit.form.FilteringSelect" id="idLocalidadEmpresaSelect" required="false" missingMessage="Debe seleccionar la localidad."
				store="localidadStore" autocomplete="true" scrollOnFocus="true" maxHeight="250">
			</select>
		</div>
		<div class="grid1_3 margin_top_10 fl">
			<div id="spanCodigoPostalEmpresaReq" style="display:none"><label for="codigoPostalEmpresa"><span>*</span> Código Postal</label></div>
			<div id="spanCodigoPostalEmpresa" style="display:block"><label for="codigoPostalEmpresa">Código Postal</label></div>
			<input type="text" name="codigoPostalEmpresa" id="codigoPostalEmpresa" maxlength="5" style="width: 7em;" dojoType="dijit.form.ValidationTextBox"
				value="" regExp="^[0-9]{5}" invalidMessage="Codigo incorrecto, solo se admiten caracteres numericos." readonly="readonly"
				missingMessage="Es necesario indicar el codigo postal." trim="true" required="false">
		</div>
		<div class="clearfix"></div>
		<div class="funciones_01 margin_top_30">
			<div id="spanFuncionReq" style="display:none"><label for="funcion"><span>*</span> Funciones desempeñadas</label></div>
			<div id="spanFuncion" style="display:block"><label for="funcion">Funciones desempeñadas</label></div>
			<div class="campoTexto">
				<textarea name="funcion" id="funcion" maxlength="2000" onkeypress="return caracteresValidos(event);" trim="true"
					onchange="return maximaLongitud(this,2000)" onKeyUp="return maximaLongitud(this,2000)" onblur="trimSpaces(this)"
					regExp="^[\w\d\s\.&aacute;&eacute;&iacute;&oacute;&uacute;&Aacute;&Eacute;&Iacute;&Oacute;&Uacute;&Ntilde;&ntilde;]{1,2000}$"
					class="textGoogie" require="false">${ProgramaForm.perfil.trabajoActual.funcion}</textarea>
					<script type="text/javascript">
						var vSpellFuncion = new GoogieSpell("googiespell/", '<%=vProxy%>');
						vSpellFuncion.setLanguages({'es': 'Español'});
						vSpellFuncion.hideLangWindow();
						vSpellFuncion.decorateTextarea("funcion");
					</script>
			</div>
			<div class="margin_40"></div>
		</div>
		<div class="funciones_01 margin_top_30">
			<div id="spanHerramientasReq" style="display:none"><label for="herramientas"><span>*</span>Competencias Técnicas (Herramientas, habilidades, equipo y/o maquinaria utilizada en el trabajo)</label></div>
			<div id="spanHerramientas" style="display:block"><label for="herramientas">Competencias Técnicas (Herramientas, habilidades, equipo y/o maquinaria utilizada en el trabajo)</label></div>
			<div class="campoTexto">
				<textarea name="herramientas" id="herramientas" maxlength="2000" onkeypress="return caracteresValidos(event);" trim="true"
					onchange="return maximaLongitud(this,2000)" onKeyUp="return maximaLongitud(this,2000)" onblur="trimSpaces(this)"
					regExp="^[\w\d\s\.&aacute;&eacute;&iacute;&oacute;&uacute;&Aacute;&Eacute;&Iacute;&Oacute;&Uacute;&Ntilde;&ntilde;]{1,2000}$"
					class="textGoogie" require="false">${ProgramaForm.referenciaPrincipal.trabajoActual.herramientas}</textarea>
					<script type="text/javascript">
						var vSpellFuncion = new GoogieSpell("googiespell/", '<%=vProxy%>');
						vSpellFuncion.setLanguages({'es': 'Español'});
						vSpellFuncion.hideLangWindow();
						vSpellFuncion.decorateTextarea("herramientas");
					</script>
			</div>
			<div class="margin_40"></div>
		</div>
		<div class="labeled margin_top_10"><span>*</span> Referencia laboral</div>
		<div class="grid1_3 margin_top_10 fl">
			<label for="nombreReferencia"><span>*</span> Nombre(s)</label>
			<input type="text" dojoType="dijit.form.ValidationTextBox" name="nombreReferencia" id="nombreReferencia" maxlength="50" required="true"
				uppercase="true" trim ="true" value="${ProgramaForm.referenciaPrincipal.trabajoActual.refNombre}" missingMessage="Dato requerido">
		</div>
		<div class="grid1_3 margin_top_10 fl">
			<label for="primerApellidoReferencia"><span>*</span> Primer Apellido</label>
				<input type="text" dojoType="dijit.form.ValidationTextBox" name="primerApellidoReferencia" id="primerApellidoReferencia" trim ="true" uppercase="true" 
					value="${ProgramaForm.referenciaPrincipal.trabajoActual.refApellido1}" maxlength="50" required="true" missingMessage="Dato requerido">
		</div>
		<div class="grid1_3 margin_top_10 fl">
			<label for="segundoApellidoReferencia">Segundo Apellido</label>
			<input type="text" dojoType="dijit.form.ValidationTextBox" name="segundoApellidoReferencia" id="segundoApellidoReferencia" trim ="true" uppercase="true"
				value="${ProgramaForm.referenciaPrincipal.trabajoActual.refApellido2}" maxlength="50" required="true" missingMessage="Dato requerido">
		</div>
		<div class="grid1_3 margin_top_10 fl">
			<label for="puestoReferencia"> <span>*</span> Puesto desempeñado</label>
			<input type="text" name="puestoReferencia" id="puestoReferencia" maxlength="150" regExp="^[A-Za-z\s\-.áéíóúÁÉÍÓÚñÑ']{1,150}$" required="true"
				dojoType="dijit.form.ValidationTextBox" invalidMessage="El valor especificado puesto desempeñado no es válido." uppercase="true" trim ="true"
				value="${ProgramaForm.referenciaPrincipal.trabajoActual.refPuesto}" missingMessage="Es necesario selecionar el puesto desempeñado." />
		</div>
		<div class="clearfix"></div>
		<div class="labeled margin_top_10"><span>*</span> Teléfono</div>
		<p><em>Debe ingresar un total de 10 dígitos (Clave LADA + Teléfono).</em></p>
		<div class="grid1_3  margin_top_20 fl">
			<div class="margin-r_20 fl">
				<div class="labeled margin_no_t"><span>*</span> Tipo de teléfono</div>	
				<div>
					<div class="tipo_tel margin_top_10 margin-r_10 fl">		
						<input class="fl" type="radio" name="tipoTelefono" id="idTipoTelefonoFijo" value="<%=Constantes.TELEFONO_FIJO %>" 
							${ProgramaForm.referenciaPrincipal.telefono.idTipoTelefono eq 5 ? 'checked="checked"' : '' } onclick="fillUpAccessKey()"/>
						<label class="fl" for="idTipoTelefonoFijo">Fijo</label>
					</div>
					<div class="tipo_tel margin_top_10 fl"> 
						<input class="fl" type="radio" name="tipoTelefono" id="idTipoTelefono" value="<%=Constantes.TELEFONO_CELULAR %>"
							${ProgramaForm.referenciaPrincipal.telefono.idTipoTelefono eq 1 ? 'checked="checked"' : '' } onclick="fillUpAccessKey()"/>
						<label class="fl" for="idTipoTelefono">Celular</label>
					</div>
				</div>					       			       
			</div>
			<div class="margin-r_10 fl">    
				<label for="acceso"><span>*</span> Acceso</label>
				<div class="ta_center">
					<span class="acceso">
						<input type="text" name="acceso" id="acceso" maxlength="3" style="width:3em;" dojoType="dijit.form.ValidationTextBox" required="true"
							value="${ProgramaForm.referenciaPrincipal.telefono.acceso}"
							readonly="readonly" regExp='^(<%=Constantes.CLAVE_TELEFONO_CELULAR + "|" + Constantes.CLAVE_TELEFONO_FIJO%>)$' 
							invalidMessage="Acceso telefónico inválido" missingMessage="Es necesario indicar el acceso telefónico" trim="true">
					</span>
				</div>
			</div>
			<div class="fl">
				<label for="clave"><span>*</span> Clave lada</label>
				<input type="text" name="clave" id="clave" maxlength="3" dojoType="dijit.form.ValidationTextBox"
					value="${ProgramaForm.referenciaPrincipal.telefono.clave}" required="true"
					regExp="^[+]?\d{2,3}$" invalidMessage="Clave lada inválida" missingMessage="Es necesario indicar la clave lada" trim="true">
			</div>
		</div>
		<div class="margin_top_20 fl">
			<label for="telefono"><span>*</span> Teléfono</label>
				<input type="text" name="telefono" id="telefono" maxlength="8" style="width:8em;" onBlur="changePhoneSizeRequired();"
					dojoType="dijit.form.ValidationTextBox" required="true" regExp="^[+]?\d{7,8}$" invalidMessage="Teléfono inválido"
					value="${ProgramaForm.referenciaPrincipal.telefono.telefono}" missingMessage="Es necesario indicar el teléfono" trim="true">
		</div>
		<div class="margin_top_20 fl">
			<label for="extension">Extensión</label>
				<input class="sugerido" type="text" name="extension" id="extension" maxlength="8" style="width:8em;" dojoType="dijit.form.ValidationTextBox"
					value="${ProgramaForm.referenciaPrincipal.telefono.extension}" regExp="^[+]?\d{0,8}$" invalidMessage="Extensión telefónica inválida"
					required="false" missingMessage="Es necesario indicar la extensión telefónica" trim="true">		
		</div>
	</fieldset>
</div>
<%!
	String getSelectedDay(String sDate) {
		boolean today = false;
		Date date = null;
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			if (null == sDate || sDate.isEmpty()) {
				today = true;
				date = new Date();
			}else date = (Date)formatter.parse(sDate);
		} catch (ParseException e) { date = null; }
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		StringBuilder day = new StringBuilder();
		int dateDay = calendar.get(Calendar.DAY_OF_MONTH);
		for (int i = 1; i<32; i++) {
			String iday = "0" + i;
			if (iday.length() > 2) iday = iday.substring(1,3);
			day.append("<option value=\"" + iday + "\"");
			if (!today && i==dateDay)
				day.append(" selected ");
			day.append(">" + iday + "</option>\n");
		}
		return day.toString();
	}
	
	String getSelectedMonth(String sDate) {
		Date date = null;
		boolean today = false;
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			if (null == sDate || sDate.isEmpty()) {
				today = true;
				date = new Date();
			}else date = (Date)formatter.parse(sDate);
		} catch (ParseException e) { date = null; }
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		StringBuilder day = new StringBuilder();
		int month = calendar.get(Calendar.MONTH) + 1;
		for (int i = 1; i<13; i++) {
			String iday = "0" + i;
			if (iday.length() > 2) iday = iday.substring(1,3);
			day.append("<option value=\"" + iday + "\"");
			if (!today && i==month)
				day.append(" selected ");
			day.append(">" + getLblMonth(i) + "</option>\n");
		}
		return day.toString();
	}
	
	String getSelectedYear(String sDate) {
		Date date = null;
		boolean today = false;
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			if (null == sDate || sDate.isEmpty()) {
				today = true;
				date = new Date();
			}else date = (Date)formatter.parse(sDate);
		} catch (ParseException e) { date = null; }
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		StringBuilder day = new StringBuilder();
		int year = calendar.get(Calendar.YEAR);
		for (int i = 1979; i<2032; i++) {
			day.append("<option value=\"" + i + "\"");
			if (!today &&  i==year)
				day.append(" selected ");
			day.append(">" + i + "</option>\n");
		}
		return day.toString();
	}
	
	String getLblMonth(int imonth) {
		StringBuilder month = new StringBuilder();
		switch(imonth) {
			case 1 : month.append("Enero"); break;
			case 2 : month.append("Febrero"); break;
			case 3 : month.append("Marzo"); break;
			case 4 : month.append("Abril"); break;
			case 5 : month.append("Mayo"); break;
			case 6 : month.append("Junio"); break;
			case 7 : month.append("Julio"); break;
			case 8 : month.append("Agosto"); break;
			case 9 : month.append("Septiembre"); break;
			case 10 : month.append("Octubre"); break;
			case 11 : month.append("Noviembre"); break;
			case 12 : month.append("Diciembre"); break;
			default : month.append("");
		}
		return month.toString();
	}
%>