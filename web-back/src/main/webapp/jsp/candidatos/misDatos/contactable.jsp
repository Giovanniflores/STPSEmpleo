<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.List, java.util.ArrayList, mx.gob.stps.portal.web.candidate.form.ProgramaForm, mx.gob.stps.portal.web.infra.helper.BeneficiarioBO, mx.gob.stps.portal.core.infra.utils.Constantes"%>
<%
	List<BeneficiarioBO> beneficiaries = new ArrayList<BeneficiarioBO>();
	if (null != request.getSession().getAttribute("LST_CONTACTS"))
		beneficiaries = (List<BeneficiarioBO>)request.getSession().getAttribute("LST_CONTACTS");
%>
<script>
	function doSubmmitDataCt() {
		var tipoTelefonoId = getRadioValue('idTipoTelefono');
		document.ProgramaForm.method.value = 'addContact';
		document.ProgramaForm.tipoTelefono.value = tipoTelefonoId;
		document.ProgramaForm.idParentescoCto.value = dijit.byId('idParentescoCtoSelect').get('value');
		dojo.xhrGet({
			url: 'perfilComplementario.do',
			form:'ProgramaForm',
			sync: true,
			timeout:180000,
			load: function(data) {
				clearWidgetsAndAddHtml('contacts',data);
			}
		}); 
	}
	function validateCtForm() {
		if (!validateContact()) return false;
		if (!validatePhone()) return false;
		doSubmmitDataCt();
	}
	function validateContact() {
		var nombreContacto = dijit.byId('nombreContacto');
		var primerApellidoCto = dijit.byId('primerApellidoCto');
		var idParentescoCtoSelect = dijit.byId('idParentescoCtoSelect');
		if (nombreContacto.value=='') {
			nombreContacto.focus();
			message('Nombre es requerido.');
			return false;
		}
		if (primerApellidoCto.value=='') {
			primerApellidoCto.focus();
			message('Primer apellido es requerido.');
			return false;
		}
		if (idParentescoCtoSelect.get('value')==0) {
			idParentescoCtoSelect.focus();
			message('Seleccione Parentesco.');
			return false;
		}
		return true;
	}
	function validatePhone() {
		var tipoTelefonoId = getRadioValue('tipoTelefono');
		var vClave = document.getElementById('clave').value;
        var vTelefono = document.getElementById('telefono').value; 
        var vExtension = document.getElementById('extension').value; 
        if (null == tipoTelefonoId) {
        	document.ProgramaForm.tipoTelefono[0].focus();
        	message('Debe seleccionar el tipo de tel�fono.');
			return false;
        }
        if (!isNumber(vClave)) {
        	document.ProgramaForm.clave.focus();
         	message('La clave LADA debe ser un valor num�rico (0-9).');
        	return false;
        }else {
        	if (!vClave || vClave.length<2) {
        		document.ProgramaForm.clave.focus();
        		message('Debe proporcionar una clave LADA de 2 � 3 caracteres.');
        		return false;
	        }else {
				if(vClave.length==2) {
					if(vTelefono.length!=8) {
						document.ProgramaForm.clave.focus();
						message('Debe proporcionar un n�mero telef�nico de 8 d�gitos.');
        				return false;
					}
				}else if(vClave.length==3) {
					if (vTelefono.length!=7) {
						document.ProgramaForm.clave.focus();
						message('Debe proporcionar un n�mero telef�nico de 7 d�gitos.');
        				return false;
					}	
				}         
	         }          
         }
         if(!vTelefono) {
         	document.ProgramaForm.telefono.focus();
        	message('Debe proporcionar el n�mero telef�nico.');
        	return false;
        }else {
        	if(!isNumber(vTelefono)) {
        		document.ProgramaForm.telefono.focus();
	        	message('El n�mero telef�nico debe ser un valor num�rico (0-9).');
        		return false;
	        }       
        }
         if (vExtension && !isNumber(vExtension)) {
         	document.ProgramaForm.extension.focus();
         	message('La extensi�n debe ser un valor num�rico (0-9).');
        	return false;
         }
         return true;
	}
	function doDeleteCt(indexCt) {
		messageConfirmDel('�Est� seguro de eliminar contacto?', indexCt);
	}
	function messageConfirmDel(msg, indexCt) {
		var html =
			'<div id="messageDialgopPh" name="messageDialgopPh">' +
			'<p style="text-align: center;">'+ msg +'</p>'+
			'<p class="form_nav">' + 
			'<input id="btnRenv" class="boton_naranja" type="button" name="btnRenv" onClick="deleteCt('+indexCt+')" value="Aceptar"/>' +
			'<input id="btnCanc" class="boton_naranja" type="button" name="btnCanc" onClick="dialogMsgConfirmDel.hide();" value="Cancelar"/>' +
    		'</p>' +
    		'</div>';
		dialogMsgConfirmDel = new dijit.Dialog({
	        title: 'Mensaje',
	        content: html,
	        style: "width:300px;",
	        showTitle: false, draggable : false, closable : false,
	    });	
		dialogMsgConfirmDel.show();
	}
	function deleteCt(indexCt) {
		dialogMsgConfirmDel.hide();
		dojo.xhrPost({url: 'perfilComplementario.do?method=deleteContact&indexContact='+indexCt,preventCache: true, form:'ProgramaForm', sync: true, timeout:180000, 
			load: function(data) {
					clearWidgetsAndAddHtml('contacts',data);
		}});
	}
	function getRadioValue(idOrName) {
		var value = null;
		var element = document.getElementById(idOrName);
		var radioGroupName = null;  
		
		if (element == null) {
			radioGroupName = idOrName;
		} else {
			radioGroupName = element.name;     
		}
		if (radioGroupName == null) {
			return null;
		}
		var radios = document.getElementsByTagName('input');
		for (var i=0; i<radios.length; i++) {
			var input = radios[ i ];    
			if (input.type == 'radio' && input.name == radioGroupName && input.checked) {
				value = input.value;
				break;
			}
		}
		return value;
	}
	function fillUpAccessKey() {
		var accesoDes;
		var tipoTelefonoId = getRadioValue('idTipoTelefono');
	    if (tipoTelefonoId == 1) {
	    	accesoDes = <%="'" + Constantes.CLAVE_TELEFONO_CELULAR + "'"%>;
	    	document.getElementById('extension').value='';
	    	document.getElementById('extension').readOnly=true;
	    }else {
	    	accesoDes = <%="'" + Constantes.CLAVE_TELEFONO_FIJO + "'"%>; 
	    	document.getElementById('extension').readOnly=false;  	
	    }	
	    var wAcceso = dijit.byId('acceso');
	    wAcceso.attr('value',accesoDes);
	}
	function fillUpAccess(phoneIndex) {
		var accesoDes;
		var tipoTelefonoId = getRadioValue('idTipoTelefono_' + phoneIndex);
	    if (tipoTelefonoId == 1) {
	    	accesoDes = <%="'" + Constantes.CLAVE_TELEFONO_CELULAR + "'"%>;
	    	document.getElementById('extension_' + phoneIndex).value='';
	    	document.getElementById('extension_' + phoneIndex).readOnly=true;
	    }else {
	    	accesoDes = <%="'" + Constantes.CLAVE_TELEFONO_FIJO + "'"%>; 
	    	document.getElementById('extension_' + phoneIndex).readOnly=false;  	
	    }	
	    var wAcceso = dijit.byId('acceso_' + phoneIndex);
	    wAcceso.attr('value',accesoDes);
	}
	function isNumber(n) {
  		return !isNaN(parseFloat(n)) && isFinite(n);
	}
</script>
<fieldset class="data_edit">
	<legend>Datos de contacto</legend>
	<div id="addcontact" style="display:block">
		<div class="grid1_3 margin_top_10 fl">
			<label for="nombreContacto"><span>*</span> Nombre(s)</label>
			<input type="text" dojoType="dijit.form.ValidationTextBox" name="nombreContacto" id="nombreContacto" maxlength="50" required="true"
				trim="true" uppercase="true" missingMessage="Dato requerido">
		</div>
		<div class="grid1_3 margin_top_10 fl">
			<label for="primerApellidoCto"><span>*</span> Primer Apellido</label>
			<input type="text" dojoType="dijit.form.ValidationTextBox" name="primerApellidoCto" id="primerApellidoCto" maxlength="50" required="true"
				trim="true" uppercase="true" missingMessage="Dato requerido">
		</div>
		<div class="grid1_3 margin_top_10 fl">
			<label for="segundoApellidoCto">Segundo Apellido</label>
			<input type="text" dojoType="dijit.form.ValidationTextBox" name="segundoApellidoCto" id="segundoApellidoCto" maxlength="50" required="false"
				trim="true" uppercase="true" missingMessage="Dato requerido">
		</div>
		<div class="grid1_3 margin_top_10 fl">
			<label for="idParentescoCtoSelect"><span>*</span> Parentesco</label>
				<input type="hidden" name="idParentescoCto" id="idParentescoCto" dojoType="dijit.form.TextBox">
				<select dojotype="dijit.form.FilteringSelect" id="idParentescoCtoSelect" autocomplete="true"
					required="true" missingMessage="Seleccione Parentesco" class="${classCivil}">
					<c:forEach var="row" items="${parentesco}">
						<option value="${row.idCatalogoOpcion}">${row.opcion}</option>
					</c:forEach>
				</select>
		</div>
		<div class="clearfix"></div>
		<div class="labeled margin_top_10"><span>*</span> Tel�fono</div>
		<p class="labeled">Debe ingresar un total de 10 d�gitos (Clave LADA + Tel�fono).</p>
		<div class="grid1_3  margin_top_20 fl">
			<div class="margin-r_20 fl">
				<div class="labeled margin_no_t"><span>*</span> Tipo de tel�fono</div>	
				<div>
					<div class="tipo_tel margin_top_10 margin-r_10 fl">		
						<input class="fl" type="radio" name="tipoTelefono" id="idTipoTelefonoFijo" value="<%=Constantes.TELEFONO_FIJO%>" onclick="fillUpAccessKey()"/>
						<label class="fl" for="idTipoTelefonoFijo">Fijo</label>
					</div>
					<div class="tipo_tel margin_top_10 fl"> 
						<input class="fl" type="radio" name="tipoTelefono" id="idTipoTelefono" value="<%=Constantes.TELEFONO_CELULAR%>" onclick="fillUpAccessKey()"/>
						<label class="fl" for="idTipoTelefono">Celular</label>
					</div>
				 </div>					       			       
		    </div>	
		    <div class="margin-r_10 fl">    
				 <label for="acceso"><span>*</span> Acceso</label>
				 <div class="ta_center">
		             <span class="acceso">
		             	<input type="text" name="acceso" id="acceso" value="" maxlength="3" style="width:3em;" dojoType="dijit.form.ValidationTextBox"
		             		required="true" disabled="disabled" regExp='^(<%=Constantes.CLAVE_TELEFONO_CELULAR + "|" + Constantes.CLAVE_TELEFONO_FIJO%>)$' 
		             		invalidMessage="Acceso telef�nico inv�lido" missingMessage="Es necesario indicar el acceso telef�nico" trim="true">
		             </span>
	             </div>
			</div>
			<div class="fl">
				<label for="clave"><span>*</span> Clave lada</label>
	         	<input type="text" name="clave" id="clave" maxlength="3" required="true" value="" dojoType="dijit.form.ValidationTextBox" regExp="^[+]?\d{2,3}$"
	       			invalidMessage="Clave lada inv�lida" missingMessage="Es necesario indicar la clave lada" trim="true">
			</div>
		</div>
		<div class="margin_top_20 fl">
			<label for="telefono"><span>*</span> Tel�fono</label>
	     	<input type="text" name="telefono" id="telefono" dojoType="dijit.form.ValidationTextBox" maxlength="8" style="width:8em;" regExp="^[+]?\d{7,8}$"
	     		value="" trim="true" required="true" invalidMessage="Tel�fono inv�lido" missingMessage="Es necesario indicar el tel�fono">
		</div>
		<div class="margin_top_20 fl">
			<label for="extension">Extensi�n</label>
	           <input type="text" name="extension" id="extension" dojoType="dijit.form.ValidationTextBox"  maxlength="8" style="width:8em;" value="" required="false"
	           		regExp="^[+]?\d{0,8}$" invalidMessage="Extensi�n telef�nica inv�lida" missingMessage="Es necesario indicar la extensi�n telef�nica" trim="true">		
		</div>
		<div class="clearfix"></div>
		<div class="margin_top_10 ta_right" style="width: 918px">
			<input type="button" class="enviar" title="Agregar contacto" value="Agregar contacto" onclick="validateCtForm();" />
		</div>
	</div>
	<%
		int index = 1;
		for (BeneficiarioBO bo : beneficiaries) {
			bo.setIndex(index);
			String count = "" + index;
	%>
		<div id="contactExt">
			<input type="hidden" id="idBeneficiario_<%=count %>" name="idBeneficiario_<%=count %>" value="<%=bo.getBeneficiario().getIdBeneficiario() %>" dojoType="dijit.form.TextBox"/>
			<input type="hidden" id="idParentesco_<%=count %>" name="idParentesco_<%=count %>" value="<%=bo.getBeneficiario().getIdParentesco() %>" dojoType="dijit.form.TextBox"/>
			<div class="clearfix"></div>
			<div class="grid1_3 margin_top_10 fl">
				<label for="nombreContacto_<%=count %>"><span>*</span> Nombre(s)</label>
				<input type="text" dojoType="dijit.form.ValidationTextBox" name="nombreContacto_<%=count %>" id="nombreContacto_<%=count %>"
					maxlength="50" value="<%=bo.getBeneficiario().getNombre() %>" required="true" trim="true" uppercase="true" missingMessage="Dato requerido">
			</div>
			<div class="grid1_3 margin_top_10 fl">
				<label for="primerApellidoCto_<%=count %>"><span>*</span> Primer Apellido</label>
				<input type="text" dojoType="dijit.form.ValidationTextBox" name="primerApellidoCto_<%=count %>" id="primerApellidoCto_<%=count %>" 
					value="<%=bo.getBeneficiario().getPrimerApellido() %>" maxlength="50" required="true" trim="true" uppercase="true" missingMessage="Dato requerido">
			</div>
			<div class="grid1_3 margin_top_10 fl">
				<label for="segundoApellidoCto_<%=count %>">Segundo Apellido</label>
				<input type="text" dojoType="dijit.form.ValidationTextBox" name="segundoApellidoCto_<%=count %>" id="segundoApellidoCto_<%=count %>"
					value="<% if (null != bo.getBeneficiario().getSegundoApellido()) out.print(bo.getBeneficiario().getSegundoApellido()); %>" maxlength="50" required="false" trim="true" uppercase="true" missingMessage="Dato requerido">
			</div>
			<div class="grid1_3 margin_top_10 fl">
				<label for="idParentescoCtoSelect_<%=count %>"><span>*</span> Parentesco</label>
					<select dojotype="dijit.form.FilteringSelect" id="idParentescoCtoSelect_<%=count %>" autocomplete="true" required="true" 
						value="<%=bo.getBeneficiario().getIdParentesco() %>" missingMessage="Seleccione Parentesco">
						<c:forEach var="row" items="${parentesco}">
							<option value="${row.idCatalogoOpcion}">${row.opcion}</option>
						</c:forEach>
					</select>
			</div>
			<div class="clearfix"></div>
			<div class="labeled margin_top_10"><span>*</span> Tel�fono</div>
			<p class="labeled">Debe ingresar un total de 10 d�gitos (Clave LADA + Tel�fono).</p>
			<div class="grid1_3  margin_top_20 fl">
				<div class="margin-r_20 fl">
					<div class="labeled margin_no_t"><span>*</span> Tipo de tel�fono</div>	
					<div>
						<div class="tipo_tel margin_top_10 margin-r_10 fl">		
							<input class="fl" type="radio" name="tipoTelefono_<%=count %>" id="idTipoTelefonoFijo_<%=count %>" value="5"
								onclick="fillUpAccess(<%=count %>)" <% if (bo.getBeneficiario().getTelefono().getIdTipoTelefono() == 5) out.println("checked=\"checked\""); %> />
							<label class="fl" for="idTipoTelefonoFijo_<%=count %>">Fijo</label>
						</div>
						<div class="tipo_tel margin_top_10 fl"> 
							<input class="fl" type="radio" name="tipoTelefono_<%=count %>" id="idTipoTelefono_<%=count %>" value="1"
								onclick="fillUpAccess(<%=count %>)" <% if (bo.getBeneficiario().getTelefono().getIdTipoTelefono() == 1) out.println("checked=\"checked\""); %> />
							<label class="fl" for="idTipoTelefono_<%=count %>">Celular</label>
						</div>
					 </div>					       			       
			    </div>	
			    <div class="margin-r_10 fl">    
					 <label for="acceso"><span>*</span> Acceso</label>
					 <div class="ta_center">
			             <span class="acceso">
			             	<input type="text" name="acceso_<%=count %>" id="acceso_<%=count %>" maxlength="3" style="width:3em;" dojoType="dijit.form.ValidationTextBox"
			             		value="<%=bo.getBeneficiario().getTelefono().getAcceso() %>" disabled="disabled" regExp='^(<%=Constantes.CLAVE_TELEFONO_CELULAR + "|" + Constantes.CLAVE_TELEFONO_FIJO%>)$' 
			             		invalidMessage="Acceso telef�nico inv�lido" missingMessage="Es necesario indicar el acceso telef�nico" required="true" trim="true">
			             </span>
		             </div>
				</div>
				<div class="fl">
					<label for="clave"><span>*</span> Clave lada</label>
		         	<input type="text" name="clave_<%=count %>" id="clave_<%=count %>" maxlength="3" required="true" dojoType="dijit.form.ValidationTextBox" regExp="^[+]?\d{2,3}$"
		       			value="<%=bo.getBeneficiario().getTelefono().getClave() %>" invalidMessage="Clave lada inv�lida" missingMessage="Es necesario indicar la clave lada" trim="true">
				</div>
			</div>
			<div class="margin_top_20 fl">
				<label for="telefono"><span>*</span> Tel�fono</label>
		     	<input type="text" name="telefono_<%=count %>" id="telefono_<%=count %>" dojoType="dijit.form.ValidationTextBox" maxlength="8" style="width:8em;"
		     		value="<%=bo.getBeneficiario().getTelefono().getTelefono() %>" trim="true" required="true" invalidMessage="Tel�fono inv�lido" missingMessage="Es necesario indicar el tel�fono" regExp="^[+]?\d{7,8}$">
			</div>
			<div class="margin_top_20 fl">
				<label for="extension">Extensi�n</label>
		           <input type="text" name="extension_<%=count %>" id="extension_<%=count %>" dojoType="dijit.form.ValidationTextBox"  maxlength="8" style="width:8em;"
		           		value="<% if (null != bo.getBeneficiario().getTelefono().getExtension()) out.print(bo.getBeneficiario().getTelefono().getExtension()); %>" required="false" regExp="^[+]?\d{0,8}$" invalidMessage="Extensi�n telef�nica inv�lida" missingMessage="Es necesario indicar la extensi�n telef�nica" trim="true">		
			</div>
			<div class="clearfix"></div>
			<div class="margin_top_10 ta_right" style="width: 918px">
				<input type="button" class="enviar" title="Eliminar contacto" value="Eliminar contacto" onclick="doDeleteCt(<%=count %>);" />
			</div>
		</div>
	<% 
		index++;
		}
	%>
</fieldset>