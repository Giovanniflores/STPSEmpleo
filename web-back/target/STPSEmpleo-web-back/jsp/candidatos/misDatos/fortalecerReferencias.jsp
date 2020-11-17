<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="mx.gob.stps.portal.web.candidate.form.ProgramaForm, mx.gob.stps.portal.core.infra.utils.Constantes, java.util.Calendar, java.util.Date, java.text.DateFormat, java.text.SimpleDateFormat, java.text.ParseException"%>
<%
	String context = request.getContextPath() +"/";
	String vProxy = "http://orangoo.com/newnox?lang=";
	pageContext.setAttribute("vProxy",vProxy);
%>
<script type="text/javascript" src="js/helper/messageHelper.js"></script>
<script type="text/javascript">
	var depGrado  = new Array(); //Declara arreglo de dependencias de catalogo Grado de Estudios
	<% //Declara arreglo de dependencias de catalogo Grado de Estudios
		String[] depGrado = session.getAttribute("depGrado") != null  ? (String[]) session.getAttribute("depGrado") : new String[0];
		for (int i = 0; i < depGrado.length; i++) { %>
			<%="depGrado[" + i + "] = '" + depGrado[i] + "';"%>
	<%}%>
</script>
<script type="text/javascript">
    dojo.require("dijit.dijit");
    dojo.require("dijit.form.Form");
    dojo.require("dijit.form.CheckBox");
    dojo.require("dijit/form/MultiSelect");
    dojo.require("dijit.form.FilteringSelect");
    dojo.require("dojo.data.ItemFileReadStore");
    dojo.require("dijit.form.ValidationTextBox");
	function doSubmmitDataComp() {
    	dialogMsgConfirmMML.hide();
		document.ProgramaForm.method.value = 'createReference';
		document.ProgramaForm.idJerarquia.value = dijit.byId('idJerarquiaSelect').get('value');
		document.ProgramaForm.personasCargo.value = dijit.byId('personasCargoSelect').get('value');
		document.ProgramaForm.idEntidadEmpresa.value = dijit.byId('idEntidadEmpresaSelect').get('value');
		document.ProgramaForm.idMunicipioEmpresa.value = dijit.byId('idMunicipioEmpresaSelect').get('value');
		document.ProgramaForm.idColoniaEmpresa.value = dijit.byId('idColoniaEmpresaSelect').get('value');
		document.ProgramaForm.idLocalidadEmpresa.value = dijit.byId('idLocalidadEmpresaSelect').get('value');
		document.ProgramaForm.tipoTelefono.value = getRadioValue('tipoTelefono');
		var laboresInicialDia=document.getElementById('laboresInicialDia').value;
		var laboresInicialMes=dijit.byId('laboresInicialMes').get('value');
		var laboresInicialAnual=document.getElementById('laboresInicialAnual').value;
		var laboresInicial = laboresInicialDia + '/' + laboresInicialMes + '/' + laboresInicialAnual
		if (laboresInicialDia != 0)
			document.ProgramaForm.laboresInicial.value = laboresInicial;
		var laboresFinalDia=document.getElementById('laboresFinalDia').value;
		var laboresFinalMes=dijit.byId('laboresFinalMes').get('value');
		var laboresFinalAnual=document.getElementById('laboresFinalAnual').value;
		var laboresFinal = laboresFinalDia + '/' + laboresFinalMes + '/' + laboresFinalAnual
		if (laboresFinalDia != 0)
			document.ProgramaForm.laboresFinal.value = laboresFinal;
		dojo.xhrGet({
			url: 'perfilComplementario.do',
			form:'ProgramaForm',
			sync: true,
			timeout:180000,
			load: function(data) {
				clearWidgetsAndAddHtml('references',data);
			}
		}); 
    }
    function doDelete(idReference) {
		dialogMsgConfirmDel.hide();
		var action = 'perfilComplementario.do?method=deleteReference&idReference='+idReference;
		dojo.xhrPost({url: action, form:'ProgramaForm', sync: true, preventCache:true, timeout:180000, 
			load: function(data) {
				clearWidgetsAndAddHtml('references',data);
		}});
	}
    function messagePath(msg,path){
		var html =
			'<div id="messageDialgop2" name="messageDialgop2">' +
		'<p style="text-align: center;">'+ msg +'</p>'+
		'<p class="form_nav">'
		+ cerrarMsgRuta(path, "Aceptar")
	    + '</p>'
	    +'</div>';
	
		dialogMsg = new dijit.Dialog({
	        title: 'Mensaje',
	        content: html,
	        style: "width:300px;",
	        showTitle: false, draggable : false, closable : false,
	    });			
		dialogMsg.show();
	}
    function messageConfirm(msg) {
		var html =
			'<div id="messageDialgopC" name="messageDialgopC">' +
			'<p style="text-align: center;">'+ msg +'</p>'+
			'<p class="form_nav">' + 
			'<input id="btnRenv" class="boton_naranja" type="button" name="btnRenv" onClick="doSubmmitDataComp()" value="Aceptar"/>' +
			'<input id="btnCanc" class="boton_naranja" type="button" name="btnCanc" onClick="dialogMsgConfirm.hide();" value="Cancelar"/>' +
    		'</p>' +
    		'</div>';
		dialogMsgConfirm = new dijit.Dialog({
	        title: 'Mensaje',
	        content: html,
	        style: "width:300px;",
	        showTitle: false, draggable : false, closable : false,
	    });	
		dialogMsgConfirm.show();
	}
	function messageConfirmDel(msg, idReference) {
		var html =
			'<div id="messageDialgoDel" name="messageDialgoDel">' +
			'<p style="text-align: center;">'+ msg +'</p>'+
			'<p class="form_nav">' + 
			'<input id="btnRenv" class="boton_naranja" type="button" name="btnRenv" onClick="doDelete('+idReference+')" value="Aceptar"/>' +
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
	function toggleDelete(idReference) {
		messageConfirmDel('¿Está seguro de eliminar la referencia?', idReference);	
	}
	function doSubmitCancel() {
		messageRutaCancel('Los datos no guardados se perderán ¿Continuar?','<c:url value="/miEspacioCandidato.do"/>');
	}
	function doSubmit() {
		location.href = "${context}miEspacioCandidato.do";
	}
	function validateForm() {
		if (!validateLastJob()) return false;
		if (!validateReference()) return false;
		if (!validatePhone()) return false;
		messageConfirmMML('¿Está seguro de guardar datos complementarios?');
	}
	function messageConfirmMML(msg) {
		var html =
			'<div id="messageDialgopM" name="messageDialgopM">' +
			'<p style="text-align: center;">'+ msg +'</p>'+
			'<p class="form_nav">' + 
			'<input id="btnRenv" class="boton_naranja" type="button" name="btnRenv" onClick="doSubmmitDataComp()" value="Aceptar"/>' +
			'<input id="btnCanc" class="boton_naranja" type="button" name="btnCanc" onClick="dialogMsgConfirmMML.hide();" value="Cancelar"/>' +
    		'</p>' +
    		'</div>';
		dialogMsgConfirmMML = new dijit.Dialog({
	        title: 'Mensaje',
	        content: html,
	        style: "width:300px;",
	        showTitle: false, draggable : false, closable : false,
	    });	
		dialogMsgConfirmMML.show();
	}
	function validateLastJob() {
		var required = true;
		var empresa = dijit.byId('empresa');
		var puesto = dijit.byId('puesto');
		var idJerarquiaSelect = dijit.byId('idJerarquiaSelect');
		var personasCargoSelect = dijit.byId('personasCargoSelect');
		var salarioMensual = dijit.byId('salarioMensual');
		var laboresInicialDia = dijit.byId('laboresInicialDia');
		var laboresInicialMes = dijit.byId('laboresInicialMes');
		var laboresInicialAnual = dijit.byId('laboresInicialAnual');
		var laboresFinalDia = dijit.byId('laboresFinalDia');
		var laboresFinalMes = dijit.byId('laboresFinalMes');
		var laboresFinalAnual = dijit.byId('laboresFinalAnual');
		var funcion = document.getElementById('funcion');
		if (required) {
			if (empresa.get('value')=='') {
				empresa.focus();
				message('Nombre de empresa es requerido.');
				return false;
			}
			if (laboresInicialDia.get('value')==0) {
				laboresInicialDia.focus();
				message('Seleccione dia fecha de ingreso.');
				return false;
			}
			if (laboresInicialMes.get('value')==0) {
				laboresInicialMes.focus();
				message('Seleccione mes fecha de ingreso.');
				return false;
			}
			if (laboresInicialAnual.get('value')==0) {
				laboresInicialAnual.focus();
				message('Seleccione año fecha de ingreso.');
				return false;
			}
			if (laboresFinalDia.get('value')==0) {
				laboresFinalDia.focus();
				message('Seleccione dia fecha de terminación.');
				return false;
			}
			if (laboresFinalMes.get('value')==0) {
				laboresFinalMes.focus();
				message('Seleccione mes fecha de terminación.');
				return false;
			}
			if (laboresFinalAnual.get('value')==0) {
				laboresFinalAnual.focus();
				message('Seleccione año fecha de terminación.');
				return false;
			}
			if (puesto.get('value')=='') {
				puesto.focus();
				message('Puesto desempeñado es requerido.');
				return false;
			}
			if (idJerarquiaSelect.get('value')==0) {
				idJerarquiaSelect.focus();
				message('Seleccione jerarquía del puesto.');
				return false;
			}
			if (personasCargoSelect.get('value')==0) {
				personasCargoSelect.focus();
				message('Seleccione número de personas a cargo.');
				return false;
			}
			if (salarioMensual.get('value')=='') {
				salarioMensual.focus();
				message('Salario mensual recibido es requerido.');
				return false;
			}
			if (funcion.value=='') {
				funcion.focus();
				message('Funciones desempeñadas es requerido.');
				return false;
			}
		}
		return true;
	}
	function validateReference() {
		var calleEmpresa = dijit.byId('calleEmpresa');
		var numExtEmpresa = dijit.byId('numExtEmpresa');
		var idEntidadEmpresaSelect = dijit.byId('idEntidadEmpresaSelect');
		var idMunicipioEmpresaSelect = dijit.byId('idMunicipioEmpresaSelect');
		var idColoniaEmpresaSelect = dijit.byId('idColoniaEmpresaSelect');
		var idEntidadEmpresaSelect = dijit.byId('idEntidadEmpresaSelect');
		var funcion = document.getElementById('funcion');
		var herramientas = document.getElementById('herramientas');
		var nombreReferencia = dijit.byId('nombreReferencia');
		var primerApellidoReferencia = dijit.byId('primerApellidoReferencia');
		var segundoApellidoReferencia = dijit.byId('segundoApellidoReferencia');
		var puestoReferencia = dijit.byId('puestoReferencia');
		if (calleEmpresa.get('value')=='') {
			calleEmpresa.focus();
			message('Calle es requerido.');
			return false;
		}
		if (numExtEmpresa.get('value')=='') {
			numExtEmpresa.focus();
			message('Número exterior es requerido.');
			return false;
		}
		if (idEntidadEmpresaSelect.get('value')==0) {
			idEntidadEmpresaSelect.focus();
			message('Seleccione entidad Federativa.');
			return false;
		}
		if (idMunicipioEmpresaSelect.get('value')==0) {
			idMunicipioEmpresaSelect.focus();
			message('Seleccione municipio o Delegación.');
			return false;
		}
		if (idColoniaEmpresaSelect.get('value')==0) {
			idColoniaEmpresaSelect.focus();
			message('Seleccione colonia.');
			return false;
		}
		if (funcion.value=='') {
			funcion.focus();
			message('Funciones desempeñadas es requerido.');
			return false;
		}
		if (herramientas.value=='') {
			herramientas.focus();
			message('Herramientas es requerido.');
			return false;
		}
		if (nombreReferencia.value=='') {
			nombreReferencia.focus();
			message('Nombre es requerido.');
			return false;
		}
		if (primerApellidoReferencia.value=='') {
			primerApellidoReferencia.focus();
			message('Primer apellido es requerido.');
			return false;
		}
		if (puestoReferencia.value=='') {
			puestoReferencia.focus();
			message('Puesto desempeñado es requerido.');
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
        	message('Debe seleccionar el tipo de teléfono.');
			return false;
        }
        if (!isNumber(vClave)) {
        	document.ProgramaForm.clave.focus();
         	message('La clave LADA debe ser un valor numérico (0-9).');
        	return false;
        }else {
        	if (!vClave || vClave.length<2) {
        		document.ProgramaForm.clave.focus();
        		message('Debe proporcionar una clave LADA de 2 ó 3 caracteres.');
        		return false;
	        }else {
				if(vClave.length==2) {
					if(vTelefono.length!=8) {
						document.ProgramaForm.clave.focus();
						message('Debe proporcionar un número telefónico de 8 dígitos.');
        				return false;
					}
				}else if(vClave.length==3) {
					if (vTelefono.length!=7) {
						document.ProgramaForm.clave.focus();
						message('Debe proporcionar un número telefónico de 7 dígitos.');
        				return false;
					}	
				}         
	         }          
         }
         if(!vTelefono) {
         	document.ProgramaForm.telefono.focus();
        	message('Debe proporcionar el número telefónico.');
        	return false;
        }else {
        	if(!isNumber(vTelefono)) {
        		document.ProgramaForm.telefono.focus();
	        	message('El número telefónico debe ser un valor numérico (0-9).');
        		return false;
	        }       
        }
         if (vExtension && !isNumber(vExtension)) {
         	document.ProgramaForm.extension.focus();
         	message('La extensión debe ser un valor numérico (0-9).');
        	return false;
         }
         return true;
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
	function isNumber(n) {
  		return !isNaN(parseFloat(n)) && isFinite(n);
	}
</script>
<noscript>Funciones de JavaScript desactivadas por navegador</noscript>
<div class="formApp miEspacio">
	<form name="ProgramaForm" id="ProgramaForm" method="post" action="perfilComplementario.do" dojoType="dijit.form.Form">
		<input type="hidden" name="method" id="method" value="" />
		<input type="hidden" name="laboresInicial" id="laboresInicial" value="" />
		<input type="hidden" name="laboresFinal" id="laboresFinal" value="" />
		<h2>Mi espacio</h2>
		<div class="tab_block">
			<div align="left" id="returnME" style="display: block;">
				<a
					href="javascript:location.replace('<c:url value="/miEspacioCandidato.do"/>');"
					class="expand"> <strong>Ir al inicio de Mi espacio</strong></a>
			</div>
			<div class="Tab_espacio">
				<h3>Portafolio de servicios</h3>
				<div class="subTab">
					<ul>
						<li class="curSubTab"><a href="${context}perfilComplementario.do?method=strengthenProfile">Fortalecer perfil</a></li>
						<li class="curSubTab"><a href="${context}perfilComplementario.do?method=languages">Idiomas</a></li>
                    	<c:if test="${ProgramaForm.modalidad.param.toLowerCase()=='mml'}">
							<li class="curSubTab"><span>Experiencia laboral</span></li>
						</c:if>
					</ul>
					<div class="clearfix"></div>
				</div>
				<div class="clearfix"></div>
			</div>
			<p class="labeled">
				${ProgramaForm.modalidad.nombreCorto}<br>
				<span class="normal">Datos iniciales para el Fortalecimiento del perfil</span><br>
				Los datos marcados con <span>*</span> son obligatorios
			</p>
		</div>
		<div class="app_holder2">
			<div class="app">
				<div class="datos_personales">
						<c:if test="${ProgramaForm.modalidad.param.toLowerCase()=='mml'}">
							<div id="experiences" name="experiences">
								<jsp:include page="/jsp/candidatos/misDatos/addReference.jsp" flush="true" />  
							</div>
							<div id="references" name="references">
								<jsp:include page="/jsp/candidatos/misDatos/referencestable.jsp" flush="true" />  
							</div>
						</c:if>
				</div>
			</div>
		</div>
		<div class="form_nav">
			<input type="button" id="btnActualizar" name="btnActualizar" class="boton_naranja" onclick="doSubmit();" value="Finalizar">
			<!--  input type="button" class="boton_naranja" onclick="doSubmitCancel();" value="Cancelar" -->
		</div>
	</form>
</div>