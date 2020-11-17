<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page import="java.util.List, java.util.Iterator, java.util.ArrayList, mx.gob.stps.portal.core.infra.utils.Constantes, mx.gob.stps.portal.persistencia.vo.ReferenciaLaboralVO, java.util.Calendar, java.util.Date, java.text.DateFormat, java.text.SimpleDateFormat, java.text.ParseException"%>
<%
	int rowCounter = 0;
	String vProxy = "http://orangoo.com/newnox?lang=";
	pageContext.setAttribute("vProxy",vProxy);
	String fechaIngreso = null != session.getAttribute("_fechaIngreso") ? (String)session.getAttribute("_fechaIngreso") : "";
	String fechaTerminacion = null != session.getAttribute("_fechaTerminacion") ? (String)session.getAttribute("_fechaTerminacion") : "";
%>
<script>
	dojo.addOnLoad(function() {
    	entidadFederativaStore.url = "${context}domicilio.do?method=obtenerEntidad";
	    entidadFederativaStore.close();
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
	    if(tipoTelefonoId == 1)
	    	accesoDes = <%="'" + Constantes.CLAVE_TELEFONO_CELULAR + "'"%>;
	    else
	    	accesoDes = <%="'" + Constantes.CLAVE_TELEFONO_FIJO + "'"%>;   		
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
</script>
<div dojoType="dojo.data.ItemFileReadStore" jsId="entidadFederativaStore" urlPreventCache="true" clearOnClose="true"></div>
<div dojoType="dojo.data.ItemFileReadStore" jsId="municipioStore" urlPreventCache="true" clearOnClose="true"></div>
<div dojoType="dojo.data.ItemFileReadStore" jsId="coloniaStore" urlPreventCache="true" clearOnClose="true"></div>
<div dojoType="dojo.data.ItemFileReadStore" jsId="localidadStore" urlPreventCache="true" clearOnClose="true"></div>
<div id="addReference" style="display:block">
	<fieldset class="ultimo_trabajo bloque">
		<legend>Referencia Laboral</legend>
		<div class="grid1_3 margin_top_10 fl">
			<label for="empresa"><span>*</span> Nombre o razón social de la empresa</label>
			<input type="text" name="empresa" id="empresa" uppercase="true" dojoType="dijit.form.ValidationTextBox" required="true"  maxlength="150" value=""
				regExp="^[A-Za-z0-9\s\d\-.&,áéíóúÁÉÍÓÚñÑ']{3,150}$" invalidMessage="El Nombre o	razón social de la empresa es invalido, favor de verificar"
				missingMessage="Es necesario indicar que Nombre o razón social de la empresa." />
		</div>
		<div class="fl margin_top_30">
			<label class="fw_no_bold" for="confidencialidadEmpresa">
				<input type="checkbox" dojoType="dijit.form.CheckBox" name="confidencialidadEmpresa" id="confidencialidadEmpresa" value="${mostrarEmpSI}" required="false" />
				Omitir este dato en la publicación de mi perfil</label>
		</div>
		<div class="clearfix"></div>
		<div class="grid1_3 margin_top_10 fl">
			<label for="laboresInicialDia"><span>*</span> Fecha de ingreso</label>
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
			<label for="laboresFinalDia"><span>*</span> Fecha de terminación</label>
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
			<label for="puesto">Puesto desempeñado</label>
			<input type="text"  dojoType="dijit.form.ValidationTextBox" name="puesto" id="puesto" maxlength="150" regExp="^[A-Za-z\s\-.áéíóúÁÉÍÓÚñÑ']{1,150}$"
				uppercase="true" value="" required="false" invalidMessage="El valor especificado puesto desempeñado no es válido." missingMessage="Es necesario selecionar el puesto desempeñado." />
		</div>
		<div class="grid1_3 margin_top_10 fl">
			<label for="idJerarquiaSelect">Jerarquía del puesto</label>
			<input type="hidden" name="idJerarquia" id="idJerarquia" dojoType="dijit.form.TextBox" />
			<select dojotype="dijit.form.FilteringSelect" id="idJerarquiaSelect" value="" required="false"
				invalidMessage="El valor especificado jerarquía del puesto no es válido." missingMessage="Es necesario selecionar el Jerarquía del puesto." autocomplete="true">
				<c:forEach var="row" items="${jerarquias}">
					<option value="${row.idCatalogoOpcion}">${row.opcion}</option>
				</c:forEach>
			</select>
		</div>
		<div class="grid1_3 margin_top_10 fl">
			<label for="personasCargoSelect">Número de personas a cargo</label>
			<input type="hidden" name="personasCargo" id="personasCargo" dojoType="dijit.form.TextBox" />
			<select dojotype="dijit.form.FilteringSelect" id="personasCargoSelect" value="" 
				required="true" missingMessage="Es necesario seleccionar el número de personas a cargo." autocomplete="true">
				<c:forEach var="row" items="${personascargo}">
					<option value="${row.idCatalogoOpcion}">${row.opcion}</option>
				</c:forEach>
			</select>
		</div>
		<div class="grid1_3 margin_top_10 fl">
			<label for="salarioMensual">Salario mensual recibido</label>
			<input type="text" name="salarioMensual" id="salarioMensual" dojoType="dijit.form.ValidationTextBox" required="true"  maxlength="9"
				value="" invalidMessage="El valor especificado no es válido por Salario Mensual recibido."
				regExp="^[+]?\d{1,6}(\.\d{1,2})?$" trim="true" invalidMessage="El valor especificado no es válido por Salario Mensual recibido." />
		</div>
		<div class="clearfix"></div>
		<div class="grid1_3 margin_top_10 fl">
			<label for="calleEmpresa"><span>*</span> Calle</label>
				<input type="text" name="calleEmpresa" id="calleEmpresa" dojoType="dijit.form.ValidationTextBox" maxlength="150" trim="true" value=""
					invalidMessage="El valor especificado no es válido para calle de la experiencia." regExp="^[\w\d\s\.áéíóúÁÉÍÓÚñÑ]{1,150}$" required="true"
					missingMessage="Es neccesario indicar calle de la experiencia." uppercase="true"/>
		</div>
		<div class="grid1_3 margin_top_10 fl">
			<label for="numExtEmpresa"><span>*</span> Número exterior</label>
			<input type="text" name="numExtEmpresa" id="numExtEmpresa" dojoType="dijit.form.ValidationTextBox" maxlength="50" required="true" value=""
				trim="true" regExp="^[\w\d\s]{1,50}$" invalidMessage="El valor especificado no es válido para número exterior de la experiencia."
				missingMessage="Es neccesario indicar número exterior de la experiencia." />
		</div>
		<div class="grid1_3 margin_top_10 fl">
			<label for="numIntEmpresa">Número interior</label>
			<input type="text" name="numIntEmpresa" id="numIntEmpresa" dojoType="dijit.form.ValidationTextBox" maxlength="50" value="" required="false" 
				uppercase="true" trim="true" regExp="^[\w\d\s]{1,50}"
				invalidMessage="El valor especificado no es válido para número interior de la experiencia."
				missingMessage="Es necesario indicar número interior de la experiencia." />
		</div>
		<div class="grid1_3 margin_top_10 fl">
			<label for="idEntidadSelect"><span>*</span> Entidad Federativa</label>
			<input type="hidden" name="idEntidadEmpresa" id="idEntidadEmpresa" dojoType="dijit.form.TextBox">
				<select dojotype="dijit.form.FilteringSelect" store="entidadFederativaStore" id="idEntidadEmpresaSelect" required="true"
					invalidMessage="Dato no válido" missingMessage="Es necesario indicar la entidad." autocomplete="true"
					onchange="javascript:actulizaMunicipio();" scrollOnFocus="true" maxHeight="250">
				</select>
		</div>
		<div class="grid1_3 margin_top_10 fl">
			<label for="idMunicipioSelect"><span>*</span> Municipio o Delegación</label>
			<input type="hidden" name="idMunicipioEmpresa" id="idMunicipioEmpresa" dojoType="dijit.form.TextBox">
			<select dojotype="dijit.form.FilteringSelect" store="municipioStore" id="idMunicipioEmpresaSelect" required="true"
				invalidMessage="Dato no válido" missingMessage="Es necesario indicar un municipio."
				autocomplete="true" onchange="javascript:actulizaColonia();" scrollOnFocus="true" maxHeight="250">
			</select>
		</div>
		<div class="grid1_3 margin_top_10 fl">
			<label for="idColonia"><span>*</span> Colonia</label>
			<input type="hidden" name="idColoniaEmpresa" id="idColoniaEmpresa" dojoType="dijit.form.TextBox">
			<select dojotype="dijit.form.FilteringSelect" store="coloniaStore" id="idColoniaEmpresaSelect" required="true"
				invalidMessage="Dato inválido" missingMessage="Es necesario indicar la colonia." autocomplete="true"
				onchange="javascript:actulizaCodigoPostal();" scrollOnFocus="true" maxHeight="250">
			</select>
		</div>
		<div class="grid1_3 margin_top_10 fl">
			<label for="idLocalidadEmpresa"><span>*</span> Localidad</label>
			<input type="hidden" name="idLocalidadEmpresa" id="idLocalidadEmpresa" dojoType="dijit.form.TextBox">
			<select dojotype="dijit.form.FilteringSelect" id="idLocalidadEmpresaSelect" required="true" missingMessage="Debe seleccionar la localidad."
				store="localidadStore" autocomplete="true" scrollOnFocus="true" maxHeight="250">
			</select>
		</div>
		<div class="grid1_3 margin_top_10 fl">
			<label for="codigoPostalEmpresa"><span>*</span> Código Postal</label>
			<input type="text" name="codigoPostalEmpresa" id="codigoPostalEmpresa" maxlength="5" style="width: 7em;" dojoType="dijit.form.ValidationTextBox"
				value="" regExp="^[0-9]{5}" invalidMessage="Codigo incorrecto, solo se admiten caracteres numericos." readonly="readonly"
				missingMessage="Es necesario indicar el codigo postal." trim="true" required="true">
		</div>
		<div class="clearfix"></div>
		<div class="funciones_01 margin_top_30">
			<label for="funcion"><span>*</span> Funciones desempeñadas</label>
			<div class="campoTexto">
				<textarea name="funcion" id="funcion" maxlength="2000" onkeypress="return caracteresValidos(event);" trim="true"
					onchange="return maximaLongitud(this,2000)" onKeyUp="return maximaLongitud(this,2000)"
					regExp="^[\w\d\s\.&aacute;&eacute;&iacute;&oacute;&uacute;&Aacute;&Eacute;&Iacute;&Oacute;&Uacute;&Ntilde;&ntilde;]{1,2000}$"
					class="textGoogie" require="false"></textarea>
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
			<label for="herramientas"><span>*</span> Herramientas, equipo y maquinaria utilizada en el trabajo</label>
			<div class="campoTexto">
				<textarea name="herramientas" id="herramientas" maxlength="2000" onkeypress="return caracteresValidos(event);" trim="true"
					onchange="return maximaLongitud(this,2000)" onKeyUp="return maximaLongitud(this,2000)"
					regExp="^[\w\d\s\.&aacute;&eacute;&iacute;&oacute;&uacute;&Aacute;&Eacute;&Iacute;&Oacute;&Uacute;&Ntilde;&ntilde;]{1,2000}$"
					class="textGoogie" require="false"></textarea>
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
				uppercase="true" value="" missingMessage="Dato requerido">
		</div>
		<div class="grid1_3 margin_top_10 fl">
			<label for="primerApellidoReferencia"><span>*</span> Primer Apellido</label>
				<input type="text" dojoType="dijit.form.ValidationTextBox" name="primerApellidoReferencia" id="primerApellidoReferencia" uppercase="true" 
					value="" maxlength="50" required="true" missingMessage="Dato requerido">
		</div>
		<div class="grid1_3 margin_top_10 fl">
			<label for="segundoApellidoReferencia">Segundo Apellido</label>
			<input type="text" dojoType="dijit.form.ValidationTextBox" name="segundoApellidoReferencia" id="segundoApellidoReferencia" uppercase="true"
				value="" maxlength="50" required="true" missingMessage="Dato requerido">
		</div>
		<div class="grid1_3 margin_top_10 fl">
			<label for="puestoReferencia"> <span>*</span> Puesto desempeñado</label>
			<input type="text" name="puestoReferencia" id="puestoReferencia" maxlength="150" regExp="^[A-Za-z\s\-.áéíóúÁÉÍÓÚñÑ']{1,150}$" required="true"
				dojoType="dijit.form.ValidationTextBox" invalidMessage="El valor especificado puesto desempeñado no es válido." uppercase="true"
				value="" missingMessage="Es necesario selecionar el puesto desempeñado." />
		</div>
		<div class="clearfix"></div>
		<div class="labeled margin_top_10"><span>*</span> Teléfono</div>
		<p><em>Debe ingresar un total de 10 dígitos (Clave LADA + Teléfono).</em></p>
		<div class="grid1_3  margin_top_20 fl">
			<div class="margin-r_20 fl">
				<div class="labeled margin_no_t"><span>*</span> Tipo de teléfono</div>	
				<div>
					<div class="tipo_tel margin_top_10 margin-r_10 fl">		
						<input class="fl" type="radio" name="tipoTelefono" id="idTipoTelefonoFijo" value="<%=Constantes.TELEFONO_FIJO %>" onclick="fillUpAccessKey()"/>
						<label class="fl" for="idTipoTelefonoFijo">Fijo</label>
					</div>
					<div class="tipo_tel margin_top_10 fl"> 
						<input class="fl" type="radio" name="tipoTelefono" id="idTipoTelefono" value="<%=Constantes.TELEFONO_CELULAR %>" onclick="fillUpAccessKey()"/>
						<label class="fl" for="idTipoTelefono">Celular</label>
					</div>
				</div>					       			       
			</div>
			<div class="margin-r_10 fl">    
				<label for="acceso"><span>*</span> Acceso</label>
				<div class="ta_center">
					<span class="acceso">
						<input type="text" name="acceso" id="acceso" maxlength="3" style="width:3em;" dojoType="dijit.form.ValidationTextBox" required="true"
							value="" readonly="readonly" regExp='^(<%=Constantes.CLAVE_TELEFONO_CELULAR + "|" + Constantes.CLAVE_TELEFONO_FIJO%>)$' 
							invalidMessage="Acceso telefónico inválido" missingMessage="Es necesario indicar el acceso telefónico" trim="true">
					</span>
				</div>
			</div>
			<div class="fl">
				<label for="clave"><span>*</span> Clave lada</label>
				<input type="text" name="clave" id="clave" maxlength="3" style="width:90px !important" dojoType="dijit.form.ValidationTextBox"
					value="" required="true" regExp="^[+]?\d{2,3}$" invalidMessage="Clave lada inválida" missingMessage="Es necesario indicar la clave lada" trim="true">
			</div>
		</div>
		<div class="margin_top_20 fl">
			<label for="telefono"><span>*</span> Teléfono</label>
				<input type="text" name="telefono" id="telefono" maxlength="8" style="width:8em;" invalidMessage="Teléfono inválido" onBlur="changePhoneSizeRequired();"
					dojoType="dijit.form.ValidationTextBox" required="true" regExp="^[+]?\d{7,8}$" value="" missingMessage="Es necesario indicar el teléfono" trim="true">
		</div>
		<div class="margin_top_20 fl">
			<label for="extension">Extensión</label>
				<input class="sugerido" type="text" name="extension" id="extension" maxlength="8" style="width:8em;" dojoType="dijit.form.ValidationTextBox"
					value="" regExp="^[+]?\d{0,8}$" invalidMessage="Extensión telefónica inválida" required="false" missingMessage="Es necesario indicar la extensión telefónica" trim="true">		
		</div>
		<div class="clearfix"></div>
		<div class="form_nav">
			<input type="button" id="btnActualizar" name="btnActualizar" class="boton_naranja" onclick="validateForm();" value="Agregar Referencia">
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