<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="mx.gob.stps.portal.core.infra.utils.Constantes"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%String context = request.getContextPath() +"/";
  pageContext.setAttribute("context", context);
  String vProxy = context + "SpellCheck.do?method=gogiespell&lang=";
  int idTipoUsuarioCandidato = Constantes.TIPO_USUARIO.CANDIDATO.getIdTipoUsuario();
  
%>

<script type="text/javascript" src="js/helper/messageHelper.js"></script>
<script type="text/javascript" src="js/misDatos/messagesValidate.js"></script>
<c:set var="regexpmail">^[a-zA-Z0-9_-]+(?:\.[a-zA-Z0-9_-]+)*@(?:[a-zA-Z0-9](?:[a-zA-Z0-9-_]*[a-zA-Z0-9-])?\.)+[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?$</c:set>

<c:set var="regexppwd">^[a-zA-Z0-9]{8,12}</c:set>

<c:if test="${perfilForm.idEstadoCivil<=0}">
	<c:set var="classCivil" value="sugerido" />
</c:if>
<c:if test="${empty perfilForm.numeroInterior}">
	<c:set var="classNumInt" value="sugerido" />
</c:if>
<c:if test="${empty perfilForm.entreCalle}">
	<c:set var="classCalle" value="sugerido" />
</c:if>
<c:if test="${empty perfilForm.yCalle}">
	<c:set var="classYCalle" value="sugerido" />
</c:if>
<c:if test="${empty perfilForm.extension}">
	<c:set var="classExt" value="sugerido" />
</c:if>
<c:if test="${empty perfilForm.correoElectronico}">
	<c:set var="classCorreo" value="sugerido" />
</c:if>

<c:if test="${perfilForm.idEstadoCivil>0}">
	<c:set var="classCivil" value="" />
</c:if>
<c:if test="${not empty perfilForm.numeroInterior}">
	<c:set var="classNumInt" value="" />
</c:if>
<c:if test="${not empty perfilForm.entreCalle}">
	<c:set var="classCalle" value="" />
</c:if>
<c:if test="${not empty perfilForm.yCalle}">
	<c:set var="classYCalle" value="" />
</c:if>
<c:if test="${not empty perfilForm.extension}">
	<c:set var="classExt" value="" />
</c:if>
<c:if test="${not empty perfilForm.correoElectronico}">
	<c:set var="classCorreo" value="" />
</c:if>

<script type="text/javascript">

    dojo.require("dijit.dijit");
    dojo.require("dijit.form.Form");
    dojo.require("dijit.form.DateTextBox");
    dojo.require("dijit.form.ValidationTextBox");
    dojo.require("dijit.form.TextBox");
	dojo.require("dojo.data.ItemFileReadStore");
	dojo.require("dijit.form.CheckBox");
	dojo.require("dijit.form.FilteringSelect");
	dojo.require("dijit.form.MultiSelect");
	dojo.require("dijit.form.RadioButton");
	dojo.require('dijit.Dialog');
	
	dojo.addOnLoad(function() {	
		var vCodigoPostal = dojo.byId('codigoPostal').value;
		//EMPIEZA PARA UPDATE		
	    entidadFederativaStore.url = "${context}domicilio.do?method=obtenerEntidad";
	    entidadFederativaStore.close();	   
    	//TERMINA PARA UPDATE
		//loadCheck();
		cargaCatalogoFederativas();
		
		/* var tipoTelefono = ${perfilForm.idTipoTelefono};
		 
		 if(tipoTelefono==1){
			   dijit.byId('extension').value='';
			   dijit.byId('extension').disabled=true;
			   document.getElementById('extension').readOnly=true;
		 }*/
		 fillUpAccessKey(1);
		 fillUpAccessKey(2);
		 fillUpAccessKey(3);
	});
	
	function colorear(){
		if (!dijit.byId('idEstadoCivilSelect').get('value'))
			dojo.byId('idEstadoCivilSelect').className='sugerido';

		if (!dojo.byId('numeroInterior').value)
			dojo.byId('numeroInterior').className='sugerido';

		if (!dojo.byId('entreCalle').value)
			dojo.byId('entreCalle').className='sugerido';

		if (!dojo.byId('yCalle').value)
			dojo.byId('yCalle').className='sugerido';

		if (!dojo.byId('extension').value)
			dojo.byId('extension').className='sugerido';

		if (!dojo.byId('correoElectronico').value)
			dojo.byId('correoElectronico').className='sugerido';		
	}
	
//--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*

function cargaCatalogoTrabajo(){
	buscaTrabajoStore.fetch({
		sync: true,
   			onComplete: function(items, request) {
		      if (items.length == 0) {
		         return;
		      }
		      dijit.byId('idRazonBusquedaSelect').attr('value', '${perfilForm.idRazonBusqueda}');
   			}
		});
}

//--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*
	
function requerirRazon(){
	var idTrabaja = dijit.byId('idTrabaja1');
	if (idTrabaja.checked) {
		document. getElementById('razonRequerida').innerHTML = '¿Por qué buscas trabajo?*';
		dijit.byId('idRazonBusquedaSelect').attr('required', true);
	}else{
		document. getElementById('razonRequerida').innerHTML = '¿Por qué buscas trabajo?';
		dijit.byId('idRazonBusquedaSelect').attr('required', false);
	}	
}

function setDiv(id, visible) {
		var vStyle = visible ? 'visible':'hidden';
		var vBlock = visible ? 'block':'none';
		document.getElementById(id).style.visibility = vStyle;
		document.getElementById(id).style.display = vBlock;
}

function correoCapturadoRadio(radio){
	
	if(radio.checked){
		if(!correoCapturado()){
			message('Debes Capturar un correo.');
			radio.checked = false;
			return false;
		}
	}
	return true;
}

function correoCapturado(){
	email = dijit.byId('correoAux').get('value');
	
	if(email == '' ){
		var vStyle = document.getElementById('correoNuevo').style.display;
		if (vStyle == 'block') {
			emailnew = dijit.byId('correoElectronico').get('value');
			if(emailnew != ''){
				var control = dijit.byId('correoElectronico');

				

				if (control && control.value == '') {
					displayErrorMsg(control, control.get("missingMessage"));
					messageFocus(control.get("missingMessage"),'correoElectronico');
					return false;
				}

				if (!control.isValid()) {
					displayErrorMsg(control, control.get("invalidMessage"));
					messageFocus(control.get("invalidMessage"),'correoElectronico');
					return false;
				}
				
					return true;
				
			}
		}
		return false;
	}
	
	return true;
}
function hasCel(radio){
	if(radio.checked){
		var tipoTelefonoId1 		= getRadioValue('idTipoTelefono1');
		var tipoTelefonoId2 		= getRadioValue('idTipoTelefono2');
		var tipoTelefonoId3 		= getRadioValue('idTipoTelefono3');
		
		if(!(tipoTelefonoId1 == 1 || tipoTelefonoId2 == 1 || tipoTelefonoId3 == 1)) {
			message("Debes capturar un numero de celular.");
			radio.checked = false;
		}
	}
}
function hasTelPrinc(radio) {
	if (radio.checked) {
		var elements = document.perfilForm.idTipoTelefono;
		var radioChecked = false;
	/*	for (i = 0; i < elements.length; i++) {
			if (elements[i].checked) {
				radioChecked = elements[i].checked;
				break;
			}
		}*/
		if(getRadioValue('idTipoTelefono1') != null){
			radioChecked = true;
		}
		if (!radioChecked ||
				!dijit.byId('acceso1').isValid(true) || 
					!dijit.byId('clave1').isValid(true) ||
						!dijit.byId('telefono1').isValid(true)){
			mensaje('Debe capturar al menos un teléfono');			
			radio.checked = false;
		
			}
	}
	return true;
}

function hasTel() {
	var tipoTelefonoId1 = getRadioValue('idTipoTelefono1');
	var tipoTelefonoId2 = getRadioValue('idTipoTelefono2');
	var tipoTelefonoId3 = getRadioValue('idTipoTelefono3');
	if (!(tipoTelefonoId1 == 5 || tipoTelefonoId2 == 5 || tipoTelefonoId3 == 5) ) {
		message("Debes capturar un número de teléfono fijo.");
		return false;
	}
	return true;
}
function hasCel() {
	var tipoTelefonoId1 = getRadioValue('idTipoTelefono1');
	var tipoTelefonoId2 = getRadioValue('idTipoTelefono2');
	var tipoTelefonoId3 = getRadioValue('idTipoTelefono3');
	if (!(tipoTelefonoId1 == 1 || tipoTelefonoId2 == 1 || tipoTelefonoId3 == 1) ) {
		message("Debes capturar un número de celular.");
		return false;
	}
	return true;
}
function validarHorario() {
	var horaIni = formatoHora('horaContactoIniSelect');
	var horaFin = formatoHora('horaContactoFinSelect');
	
	if (horaIni>horaFin) {
		mensaje("Verificar que si el \"horario para contactarlo a\" es mayor al horario para contactarlo de\"");
		horaIni.focus();
		return false;
	}
	return true;
}

function formatoHora(id){
	var hora = new Array();
	hora = dijit.byId(id).get('displayedValue').split(':');	
	return hora[0]+ "" + hora[1];
}

function esCandidato(){
	return ${USUARIO_APP.idTipoUsuario} == 3;
}
	
function redireccionarLogin(){
	window.location='logout.do';
}

function validarFormatoCorreo(correo) {	
	var regExp = /^[a-zA-Z0-9_-]+(?:\.[a-zA-Z0-9_-]+)*@(?:[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?\.)+[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?$/;
	if (!regExp.test(correo.value)) {
		mensaje('Formato de correo electronico inválido');
		correo.focus();
		return false;
	}
	return true;
}

function showConf(param) {
	if(param>0){
	document.getElementById('correoActual').style.display='none';
	document.getElementById('correoNuevo').style.display='block';
	}else{
		
		document.getElementById('correoActual').style.display='block';
		document.getElementById('correoNuevo').style.display='none';
		
	}
	
}

function setDiv(id, visible) {
	var vStyle = visible ? 'visible':'hidden';
	document.getElementById(id).style.visibility = vStyle;
}

//funcion para obtener el valor de un radiobutton seleccionado
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

function uncheckRadioGroup(radioGroupName) {
	var radios = document.getElementsByTagName('input');
	for (var i=0; i<radios.length; i++) {
		var input = radios[ i ];
		if (input.type == 'radio' && input.name == radioGroupName && input.checked) {
			input.checked = false;
			break;
		}
	}
}

function checkRadioGroupByValue(radioGroupName, value) {
	var radios = document.getElementsByTagName('input');
	for (var i=0; i<radios.length; i++) {
		var input = radios[ i ];
		if (input.type == 'radio' && input.name == radioGroupName && input.value == value) {
			input.checked = true;
			return;
		}
	}
}

//INICIA FUNCIONES TEL_PRINCIPAL
//Dependiendo de la seleccion de tipo de telefono, coloca la clave de acceso correspondiente
function fillUpAccessKey(phoneIndex){
	var tipoTelefonoId = getRadioValue('idTipoTelefono' + phoneIndex);
	var accesoDes;
	
    if(tipoTelefonoId == 1){ 
    	accesoDes = <%="'" + Constantes.CLAVE_TELEFONO_CELULAR + "'"%>;
    	document.getElementById('extension' + phoneIndex).value = '';
		dijit.byId('extension' + phoneIndex).disabled=true;
		document.getElementById('extension' + phoneIndex).readOnly=true;
    }
    else{
    	accesoDes = <%="'" + Constantes.CLAVE_TELEFONO_FIJO + "'"%>;
    	// no se debe resetear para que en un carga
    	//document.getElementById('extension' + phoneIndex).value = '';
		dijit.byId('extension' + phoneIndex).disabled=false;
		document.getElementById('extension' + phoneIndex).readOnly=false;
    }

	var wAcceso = dijit.byId('acceso' + phoneIndex);
    wAcceso.attr('value',accesoDes);
}

function fillUpAccessKeyLoad(phoneIndex){
	var tipoTelefonoId = getRadioValue('idTipoTelefono' + phoneIndex);
	var accesoDes;
	
    if(tipoTelefonoId == 5){ 
    	accesoDes = <%="'" + Constantes.CLAVE_TELEFONO_CELULAR + "'"%>;
    	document.getElementById('extension' + phoneIndex).value = '';
		dijit.byId('extension' + phoneIndex).disabled=true;
		document.getElementById('extension' + phoneIndex).readOnly=true;
    }
    else{
    	accesoDes = <%="'" + Constantes.CLAVE_TELEFONO_FIJO + "'"%>;   		
    	document.getElementById('extension' + phoneIndex).value = '';
		dijit.byId('extension' + phoneIndex).disabled=false;
		document.getElementById('extension' + phoneIndex).readOnly=false;
    }

	var wAcceso = dijit.byId('acceso' + phoneIndex);
    wAcceso.attr('value',accesoDes);
}

//funcion para validar que los tamaños de la clave LADA y el telefono correspondan
function changePhoneSizeRequired(phoneIndex){
	var vClave = document.getElementById('clave' + phoneIndex);
	var vTelefono = document.getElementById('telefono' + phoneIndex);
	 
	if(vClave.value.length < 2){
		mensaje('Debe proporcionar una clave LADA de 2 ó 3 caracteres');
	} else if(vClave.value.length == 2) {
		if(vTelefono.value.length != 8){
			mensaje('Debe proporcionar un teléfono de 8 dígitos');
		}
	} else if(vClave.value.length==3){
		if(vTelefono.value.length!=7){
			mensaje('Debe proporcionar un teléfono de 7 dígitos');
		}	
	}
}

function openPhoneWindow() {
  var newWin = window.open('<%=context + "phone.do?method=init"%>', "Telefonos","height=500,width=900,resizable=yes,scrollbars=yes");
}

function irA(opcion){
	if ((opcion > 0) && (opcion < 5)){
		var cv = ${estatusCV};
		cv ++; //para poder ver la ventana siguiente al estatus actual
		var tab = ${thisTabId};
		if ((cv >= opcion) && (tab != opcion) ){
			if (opcion == 1){
				location.replace('<%=response.encodeURL(context + "perfil.do?method=init")%>');
			}
			if (opcion == 2){
				location.replace('<%=response.encodeURL(context + "escolaridad.do?method=init")%>');
			}
			if (opcion == 3){
				location.replace('<%=response.encodeURL(context+"experiencia.do?method=init")%>');
			}
			if (opcion == 4){
				location.replace('<%=response.encodeURL(context+"expectativa.do?method=init")%>');
			}
		}
		if (cv < opcion ){
			if (tab == (opcion -1)){
				mensaje("Debe completar el formulario actual");
			}else {
				mensaje("Debe completar el formulario #" + cv); //ultimo formulario completado
			}
			
		}
	}
}

function cancelarPerfil() {
	var answer = confirm("Los datos no guardados se perderán ¿Continuar?");
	if (answer){
		location.replace('<c:url value="/miEspacioCandidato.do"/>');
	}
}

function validarPorId(id,mensajeValidacion){
	if(!dijit.byId(id).isValid())
		return "<li>"+mensajeValidacion + "</li>";
	else
		return "";
}

function validarSelect(id,mensajeValidacion){

	if (dijit.byId(id).get('item')== null){
		return "<li>"+mensajeValidacion + "</li>";
	} else {
		return "";
	}		
}

function recibirOfertas(){
	var telefono = document.perfilForm.idRecibeOferta1.checked;
	var correo	 = document.perfilForm.idRecibeOferta2.checked;
	if(telefono && correo ){	
		return  4;
	}else if(telefono && !correo){	
		return  2;
	}else if(!telefono && correo){	
		return  3;
	}	
}

function validarTelefono() {
	var tipoTelefonoId 		= getRadioValue('idTipoTelefono1');
	var clave 				= document.getElementById('clave1').value;
	var vClave 				= document.getElementById('clave1').value.length;
	var vTelefono 			= document.getElementById('telefono1').value.length;
	var tel 				= document.getElementById('telefono1').value;
	var vExtension 			= document.getElementById('extension1').value;
	var cantidadCaracteres 	= vClave + vTelefono;
	if (!tipoTelefonoId) {
		message('Debe seleccionar tipo de teléfono'); 
		return false;
	}
	if (!isNumber(clave)) {
	  message('La clave LADA debe ser un valor numérico (0-9).');
	  document.perfilForm.clave.focus();
	  return false;
	}		 	
	if (!tel) {
		message('Es necesario indicar el teléfono');
		document.getElementById('telefono').focus(); 
		return false;
	}
	if (!isNumber(tel)) {
		message('El teléfono debe ser un valor numérico (0-9).');
		document.getElementById('telefono').focus();
		return false;
	}	
  	if (tipoTelefonoId == 1 && vClave != 2 || vTelefono != 8){ // Celular
  		if (vClave == 2 && cantidadCaracteres != 10) {
  			message('Es necesario indicar una clave LADA de 2 caracteres y proporcionar un télefono de 8 digitos');
  			document.getElementById('clave').focus();	  
			return false;
  		}
  		if (vClave == 3 && cantidadCaracteres != 10) {
  			message('Es necesario indicar una clave LADA  3 caracteres y proporcionar un teléfono de 7 digitos');
  			document.getElementById('clave').focus();	  
			return false;
  		}
  	}else if (tipoTelefonoId == 2) { // Telefono fijo
  		if (vClave == 2 && cantidadCaracteres != 10) {
  			message('Es necesario indicar una clave LADA de 2 caracteres y proporcionar un télefono de 8 digitos');
  			document.getElementById('clave').focus();	  
			return false;
  		}
  		if (vClave == 3 && cantidadCaracteres != 10) {
  			message('Es necesario indicar una clave LADA  3 caracteres y proporcionar un teléfono de 7 digitos');
  			document.getElementById('clave').focus();	  
			return false;
  		}
 	}
  	if (vExtension && !isNumber(vExtension)) {
  	  message('La extensión debe ser un valor numérico (0-9).');
  	  document.perfilForm.extension.focus();
  	  return false;
  	}  	
 	return true;
}

	function noArrastrar(correoElectronico){
		var aux = correoElectronico.value;
		correoElectronico.value = '';
		correoElectronico.value = aux;
	}
	//TERMINA FUNCIONES TEL_PRINCIPAL
	
	function validarCorreo() {
		var vStyle = document.getElementById('correoNuevo').style.display;
		if (vStyle == 'block') {
			
			var correoNew = document.getElementById('correoElectronico');
			var correoConf = document.getElementById('correoElectronicoConf');
			
			if (correoNew.value == '') {
				mensaje('Debe proporcionar un correo electrónico válido');
				correoNew.focus();
				return false;
			}
			if (correoConf.value == '') {
				mensaje('Debe proporcionar la confirmación de correo electrónico');
				correoConf.focus();
				return false;
			}
			if (correoNew.value != correoConf.value) {
				mensaje('El correo electrónico y la confirmación deben ser iguales');
				correoNew.focus();
				return false;
			}
			var correoAnt = document.getElementById('correoAux');
			if (correoNew.value == correoAnt.value) {
				mensaje('El nuevo correo electrónico proporcionado debe ser diferente al proporcionado anteriormente.');
				correoNew.focus();
				return false;
			}
			//correoElectronico
			//if(validarFormatoCorreo(correoNew.value)){
			//	return false;
			//}
			
			var control = dijit.byId('correoElectronico');

		

			if (control && control.value == '') {
				displayErrorMsg(control, control.get("missingMessage"));
				messageFocus(control.get('missingMessage'),'correoElectronico');
				return false;
			}

			if (!control.isValid()) {
				displayErrorMsg(control, control.get("invalidMessage"));
				messageFocus(control.get('invalidMessage'),'correoElectronico');
				return false;
			}
	
			document.perfilForm.method.value = 'validarCorreo';
			dojo.xhrGet( {
				url: 'perfil.do',
			  	form:'perfilForm',
			  	sync: true,
			  	timeout:180000,
			  	load: function(data) {
					var res = dojo.fromJson(data);
					if (res.type == 'ext') {
						message(res.message); 						
						doSubmitPerfil(true);						
						
					} else {
						mensaje(res.message);
					}
			    }
			});
		} else {
			var control = dijit.byId('correoElectronico');
			if (control && control.value == '') {
				control.focus();
				mensaje(control.get("missingMessage"));
				return false;
			}
			if (!control.isValid()) {
				control.focus();
				mensaje(control.get("invalidMessage"));
				return false;
			}
			doSubmitPerfil(false);
		}
	}
	function validarDatoPorDato(){
		var varMensajes = validarIdMensaje();	
		var aux = '<p>Los campos con * son obligatorios</p>' + varMensajes;
		if("" != varMensajes){
			mensaje(aux);
			return false;
		}else{
		 	return true;
		}
	}
	function validarIdMensaje(){
		var mensajeError =  "";
			//mensajeError += validarSelect("idEstadoCivilSelect","Favor de proporcionar el estado civil .");
			//mensajeError += validarPorId("codigoPostal","El código postal debe tener un formato de 5 caracteres numéricos.");
			//mensajeError += validarPorId("pais","Favor de proporcionar el pais .");
			//mensajeError += validarSelect("idEntidadSelect","Favor de proporcionar la entidad federativa .");
			//mensajeError += validarSelect("idMunicipioSelect","Favor de proporcionar el municipio .");
			//mensajeError += validarSelect("idColoniaSelect","Favor de proporcionar la colonia .");
			//mensajeError += validarPorId("calle","Favor de proporcionar la calle .");
			//mensajeError += validarPorId("numeroExterior","Favor de proporcionar el número exterior .");
			//mensajeError += validarPorId("numeroInterior","Favor de proporcionar el número interior .");
			//mensajeError += validarPorId("entreCalle","Favor de proporcionar el entre calle .");
			//mensajeError += validarPorId("yCalle","Favor de proporcionar el y calle .");
			//mensajeError += validarSelect("horaContactoIniSelect","Favor de proporcionar el horario para contactarlo.");
			//mensajeError += validarSelect("horaContactoFinSelect","Favor de proporcionar el horario para contactarlo a.");		
			//mensajeError += validarPorId("acceso","Favor de proporcionar el acceso .");
			//mensajeError += validarPorId("clave","Favor de proporcionar la clave lada .");
			//mensajeError += validarPorId("telefono","Favor de proporcionar el teléfono .");
			//mensajeError += validarPorId("extension","Favor de proporcionar el Extensión .");
			//mensajeError += validarSelect("idRazonBusquedaSelect","Favor de proporcionar el ¿Por qué buscas trabajo?.");
			//mensajeError += validarPorId("inicioBusqueda","Favor de proporcionar cuando Empezaste a buscar empleo.");
			//mensajeError += validarTelefono();
		return mensajeError;
	}
	
	function reenvio(cambioCorreo,data){
	
		var res = dojo.fromJson(data);
			//location.replace('<%=response.encodeURL(context + "escolaridad.do?method=init")%>');		
		if (res.msg.type == 'ext') {	
			location.replace('<%=response.encodeURL(context + "escolaridad.do?method=init")%>');			
		}
	}
	
	
	function validarForma() {
		return validarDatoPorDato() && dijit.byId('perfilForm').isValid();
	}
	function validarPerfilForm() {
		var flag = false;
		if (validarForma()) {
	    	return true;
	    } 
	    return flag;
	}
	function setChecked(id, chk) {
	//not working
		document.getElementById(id).checked = chk;
		
	}
	/*
	function loadCheck() {
		var hasConfiden = eval('${perfilForm.confidencialidad eq confidenSI ? true : false}');
		setChecked('hasConfiden', hasConfiden);
		var hasCtcCorreo = eval('${perfilForm.contactoCorreo eq ctcCorreoSI ? true : false}');
		setChecked('hasCtcCorreo', hasCtcCorreo);
		var hasCtcTel = eval('${perfilForm.contactoTelefono eq ctcTelSI ? true : false}');
		setChecked('hasCtcTel', hasCtcTel);
	}
	*/
	function mensaje(mensaje) {	
		message(mensaje);
	}
	
	function doSubmitCancel() {
		/*if (confirm('${perfilForm.errorMessageTabDoble}')) {
			document.perfilForm.method.value = 'init';
			document.perfilForm.submit();
		}*/
		messageRutaCancel('${perfilForm.errorMessageTab}','<c:url value="/miEspacioCandidato.do"/>');
	}	
	
	function validateForm() {
		var password = dijit.byId('password');
		var street = document.getElementById('calle');
		var numExt = document.getElementById('numeroExterior');
		var colony = document.getElementById('idColoniaSelect');
		var contactTel = dojo.byId('hasCtcTel').checked;
		var contactEmail = dojo.byId('hasCtcCorreo').checked;
		var idRecibeOferta = dojo.byId('idRecibeOferta1').checked;

    
		if (!password.isValid()) {
			message('La contraseña no debe contener ñ, acentos o caracteres especiales.');
			password.focus();
			return false;
		}		
		if (!street.value) {
			message('Es necesario indicar el nombre de la calle.');
			street.focus();
			return false;
		}
		if (!numExt.value) {
			message('Es necesario indicar el número exterior.');
			numExt.focus();
			return false;
		}
		if (dijit.byId('idEntidadSelect').get('item')== null || dijit.byId('idEntidadSelect').get('item').value == 0) {
    		message('Debe seleccionar entidad federativa dónde radica.');
			return false;
    	}
    	if (dijit.byId('idMunicipioSelect').get('item')== null || dijit.byId('idMunicipioSelect').get('item').value == 0) {
    		message('Debe seleccionar municipio o delegación dónde radica.');
			return false;
    	}
    	if (dijit.byId('idColoniaSelect').get('item')== null || dijit.byId('idColoniaSelect').get('item').value == 0) {
    		message('Debe seleccionar colonia dónde radica.');
			return false;
    	}
    	
    	if (!contactTel && !contactEmail){
			message('Es necesario indicar el medio de contacto.');
			return false;
		}

    	if (idRecibeOferta && !hasCel()) {
   			return false;
   		}
		return true;
	}
		
	function doSubmitPerfil(cambioCorreo) {
		dojo.byId('btnActualizar').disabled=true;
		if (validateForm() && validarTelefono() && validatePhoneList() ) {
	   		document.perfilForm.method.value = 'registrar';
	   		
	   		dijit.byId('idEstadoCivil').set('value', 
	   		dijit.byId('idEstadoCivilSelect').get('value'));
	   		dijit.byId('idEntidad').set('value', 
	   		dijit.byId('idEntidadSelect').get('value'));
	   		dijit.byId('idMunicipio').set('value', 
	   		dijit.byId('idMunicipioSelect').get('value'));
	   		dijit.byId('idColonia').set('value', 
	   		dijit.byId('idColoniaSelect').get('value'));
	   	
	   		// Cambio para poder aceptar el recibir ofertas por
	   		document.getElementById('idRecibeOferta').value = recibirOfertas();
	   	
	   		//Valores para los checkbox
	   		if (dojo.byId('hasConfiden').checked) {
	   			document.getElementById('confidencialidad').value = '${confidenSI}';
	   		} else {
	   			document.getElementById('confidencialidad').value = '${confidenNO}';
	   		}
	   		if (dojo.byId('hasCtcCorreo').checked) {
	   			document.getElementById('contactoCorreo').value = '${ctcCorreoSI}';
	   		} else {
	   			document.getElementById('contactoCorreo').value = '${ctcCorreoNO}';
	   		}
	   		if (dojo.byId('hasCtcTel').checked) {
	   			document.getElementById('contactoTelefono').value = '${ctcTelSI}';
	   		} else {
	   			document.getElementById('contactoTelefono').value = '${ctcTelNO}';
	   		}
	   		
	   		dojo.xhrGet( {
				url: 'perfil.do',
		  		form:'perfilForm',
		  	 	sync: true,
		  		timeout:180000,
		  		load: function(data) {
					var res = dojo.fromJson(data);
					//alert(res.msg.message);
					if (res.msg.type == 'ext') {
						messageRuta2BtnEditarPerfil(res.msg.message,"<%=response.encodeURL(context + "escolaridad.do?method=init")%>","<%=response.encodeURL(context+"ofertasPerfiles.do?method=init&tipoConsulta=1")%>");
					}
					else
					{
						message(res.msg.message);	
					}
					//messageFuncion(res.msg.message,reenvio(cambioCorreo,data));
					
					//setInterval(function () {validateDialog(cambioCorreo,data)}, 1000);
						
					
					//reenvio(cambioCorreo, data)
					//mensaje(res.msg.message);
					//reenvio(cambioCorreo, data);
		    	}
			});
		}
		dojo.byId('btnActualizar').disabled=false;
	}
	function validateDialog(cambioCorreo,data){
		
		if(!dialogOpen()){
			reenvio(cambioCorreo, data);
		}
	}
	function confirmPassword() {
		return dijit.byId('password').value == dijit.byId('confirmpasswd').value;
	}
	function validatePhoneList() {
		var tipoTelefonoId1 = getRadioValue('idTipoTelefonoAdd1');
		var tipoTelefonoId2 = getRadioValue('idTipoTelefonoAdd2');
		var tipoTelefonoId3 = getRadioValue('idTipoTelefonoAdd3');
		if (tipoTelefonoId1) {
			if (tipoTelefonoId2) {
				if (tipoTelefonoId3)
					return (validatePhone(1) && validatePhone(2) && validatePhone(3));
				else
					return (validatePhone(1) && validatePhone(2));
			}else {
				return validatePhone(1);
			}
		}
		return true;
	}
	function validatePhone(index) {
		var tipoTelefonoId = getRadioValue('idTipoTelefono' + index);
		var vClave = document.getElementById('clave' + index).value;
        var vTelefono = document.getElementById('telefono' + index).value;
        var vExtension = document.getElementById('extension' + index).value;
        var telefonoAdd = document.getElementById('telefono' + index);
        var claveAdd = document.getElementById('clave' + index);
        var extensionAdd = document.getElementById('extension' + index);
        if (!tipoTelefonoId) {
        	message('Debe seleccionar el tipo de teléfono.');
			return false;
        }
        if(!vTelefono) {
        	message('Es necesario indicar el número telefónico.');
        	telefonoAdd.focus();
        	return false;
        }else {
        	if(!isNumber(vTelefono)){
	        	message('El número telefónico debe ser un valor numérico (0-9).');
	        	telefonoAdd.focus();
        		return false;
	        }       
        }
        if (!isNumber(vClave)) {
         	message('La clave LADA debe ser un valor numérico (0-9).');
         	claveAdd.focus();
        	return false;
        }else {
        	if (!vClave || vClave.length<2){
        		message('Es necesario indicar una clave LADA de 2 ó 3 caracteres.');
        		claveAdd.focus();
        		return false;
	        }else {
				if(vClave.length==2) {
					if(vTelefono.length!=8){
						message('Es necesario indicar un número telefónico de 8 dígitos.');
						claveAdd.focus();
        				return false;
					}
				}else if(vClave.length==3) {
					if (vTelefono.length!=7){
						message('Es necesario indicar un número telefónico de 7 dígitos.');
						claveAdd.focus();
        				return false;
					}	
				}         
	         }          
         }
         if (vExtension && !isNumber(vExtension)) {
         	message('La extensión debe ser un valor numérico (0-9).');
         	extensionAdd.focus();
        	return false;
         }
         return true;
	}
	function actulizaMunicipio(){
		var vEntidad = dijit.byId('idEntidadSelect').get('value');
		if (vEntidad){
			municipioStore.url = '';
			municipioStore.close();			

			coloniaStore.url = '';
			coloniaStore.close();			
		
			dojo.byId('codigoPostal').value = '';
			dijit.byId('idMunicipioSelect').reset();
			dijit.byId('idColoniaSelect').reset();
			municipioStore.url = "${context}domicilio.do?method=obtenerMunicipio" + "&" + "idEntidad="+ vEntidad;
			municipioStore.close();			
		}
	}
	function actulizaColonia(){
		var vEntidad   = dijit.byId('idEntidadSelect').get('value');
		var vMunicipio = dijit.byId('idMunicipioSelect').get('value');
		if (vEntidad && vMunicipio){
			dojo.byId('codigoPostal').value = '';
			dijit.byId('idColoniaSelect').reset();
			coloniaStore.url = "${context}domicilio.do?method=obtenerColonia" + "&" + "idEntidad="+ vEntidad + "&" + "idMunicipio="+ vMunicipio;
			coloniaStore.close();
		}
	}
	function actulizaCodigoPostal(){
		var vEntidad   = dijit.byId('idEntidadSelect').get('value');
		var vMunicipio = dijit.byId('idMunicipioSelect').get('value');
		var vColonia = dijit.byId('idColoniaSelect').get('value');;
		if (vEntidad && vMunicipio && vColonia){
	    	var url = '${context}domicilio.do?method=obtenerCodigoPostal&idEntidad='+ vEntidad + '&idMunicipio='+ vMunicipio + '&idColonia='+ vColonia;
			dojo.xhrGet({url: url, sync: true, timeout:180000,
		       load: function(data){
					       var res = dojo.fromJson(data);
						   dojo.byId('codigoPostal').value = res.codigoPostal;
					    }
				});
		} else {
			dojo.byId('codigoPostal').value='';
		}
	}
	function cargaCatalogoFederativas() {
		var vEntidad = '${perfilForm.idEntidad}';
		var vMunicipio = '${perfilForm.idMunicipio}';
		entidadFederativaStore.fetch({
    		sync: true,
          		onComplete: function(items, request) {
          		if (items.length == 0) return;
          		dijit.byId('idEntidadSelect').attr('value', '${perfilForm.idEntidad}');
    				municipioStore.url = "${context}domicilio.do?method=obtenerMunicipio" + "&" + "idEntidad="+ vEntidad;
    				municipioStore.close();
    				municipioStore.fetch({
    				sync: true,
          				onComplete: function(items, request) {
          					if (items.length == 0) return;
          					dijit.byId('idMunicipioSelect').attr('value', '${perfilForm.idMunicipio}');
    						coloniaStore.url = "${context}domicilio.do?method=obtenerColonia" + "&" + "idEntidad="+ vEntidad + "&" + "idMunicipio="+ vMunicipio;
    						coloniaStore.close();
    						coloniaStore.fetch({
    						sync: true,
          						onComplete: function(items, request) {
          							if (items.length == 0) return;                   	
          							dijit.byId('idColoniaSelect').attr('value', '${perfilForm.idColonia}');
          							cargarDireccion();
          						}
    						});
          				}
    				});
          	}
    	});
	}
	
  	

  	
  	
  	function doSubmitDesactivarCandidato(estado){
  		if(estado==true){
  	
  			if (!confirm('¿Desea realizar la desactivación de su cuenta personal?')) return;
  			document.perfilForm.method.value = 'desactivar';
	   		dojo.xhrGet( {
				url: 'perfil.do',
		  		form:'perfilForm',
		  	 	sync: true,
		  		timeout:180000,
		  		load: function(data) {
					var res = dojo.fromJson(data);	
					
					mensaje(res.msg.message);
					// si se desactiva no se reenvio : reenvio(cambioCorreo, data);
					
					if(res.msg.type=="ext"){
						redireccionarLogin();
					}
					
		    	}
			}); 
  		}
  		//redireccionarLogin();
  	}
  	
  	function doSubmitDesactivarAdmor(estado){
  		if(estado==true){
  	
  			if (!confirm('¿Desea realizar la desactivación de su cuenta personal?')) return;
  			document.perfilForm.method.value = 'desactivar';
	   		dojo.xhrGet( {
				url: 'perfil.do',
		  		form:'perfilForm',
		  	 	sync: true,
		  		timeout:180000,
		  		load: function(data) {
					var res = dojo.fromJson(data);
					if (res.msg.type == 'ext') {
						messageRuta2BtnEditarPerfil(res.msg.message,"<%=response.encodeURL(context + "escolaridad.do?method=init")%>","<%=response.encodeURL(context+"ofertasPerfiles.do?method=init&tipoConsulta=1")%>");
					}
					else
					{
						message(res.msg.message);	
					}
					
					//mensaje(res.msg.message);
					
					//reenvio(cambioCorreo, data);					
		    	}
			}); 
  		}
  	}  	

  	/* TODO: COMENTAR EN PRODUCCION */
  	function doSubmitDesactivarAdmor(estado){
  		if(estado==true){
  			var txt = document.getElementById('detalleDesactivacion');
  			var regExp = /^[\w\d\s\.áéíóúÁÉÍÓÚñÑ;]{1,200}$/;
  			if(txt.value == ''){
  				mensaje('Debe proporcionar el detalle del motivo de desactivación');
  				return false;
  			} else if (!regExp.test(txt.value)) {
  				mensaje('Descripcion contiene caracteres inválidos');
  				return false;
  			} else if(txt.value.length>200) {
  				mensaje('El detalle del motivo de desactivación es demasiado largo, no debe ser mayor a 200 caracteres.');
  				return false;
  			} else {
  	  			if (!confirm('¿Desea realizar la desactivación de la cuenta personal del candidato?')) return;
  	  			document.perfilForm.method.value = 'desactivar';
  		   		dojo.xhrGet( {
  					url: 'perfil.do',
  			  		form:'perfilForm',
  			  	 	sync: true,
  			  		timeout:180000,
  			  		load: function(data) {
  						var res = dojo.fromJson(data);
  					
  						if (res.msg.type == 'ext') {
  							messageRuta2BtnEditarPerfil(res.msg.message,"<%=response.encodeURL(context + "escolaridad.do?method=init")%>","<%=response.encodeURL(context+"ofertasPerfiles.do?method=init&tipoConsulta=1")%>");
  						}
  						else
  						{
  							message(res.msg.message);	
  						}
  										
  			    	}
  				}); 
  		   		redireccionarLogin();
  			}  			
  		}
  	}
  	
  	
  	
	function caracteresValidos(e){
    	var tecla_codigo = (document.all) ? e.keyCode : e.which;
    	if(tecla_codigo==8 || tecla_codigo==0)
    		return true;
    	if (tecla_codigo==225 || tecla_codigo==233 || tecla_codigo==237 || tecla_codigo==243 || tecla_codigo==250) 
    		return true; //vocales minusculas con acento
    	if (tecla_codigo==193 || tecla_codigo==201 || tecla_codigo==205 || tecla_codigo==211 || tecla_codigo==218) 
    		return true; //vocales mayuzculas con acento
    	if (tecla_codigo==209 || tecla_codigo==241 ) 
    		return true; //ï¿½ y ï¿½
   		
    	var patron =/[0-9\-a-zA-Z_ .,:;#]/;
    	tecla_valor = String.fromCharCode(tecla_codigo);
    	return patron.test(tecla_valor);
    }  	
	
  	function maximaLongitud(texto,maxlong) {
	  	var tecla, int_value, out_value;
	
	  	if (texto.value.length > maxlong)
	  	{
	  	/*con estas 3 sentencias se consigue que el texto se reduzca
	  	al tamaño maximo permitido, sustituyendo lo que se haya
	  	introducido, por los primeros caracteres hasta dicho limite*/
	  	in_value = texto.value.replace(/^\s+|\s*[\r\n][\r\n \t]*/g, "");
	  	
	  	out_value = in_value.substring(0,maxlong);
	  	texto.value = out_value;
	  	
	  	return false;
	  	}
	  	return true;
  	}

	function doSubmitAddPhone(phoneIndex) {
		if (validateData(phoneIndex)) {
			addPhone(phoneIndex);
		}
	}

	function doDeletePhone(phoneIndex) {
		if (confirm('Esta usted seguro de eliminar teléfono')) {
			var idPhone = document.getElementById('idTelefono' + phoneIndex).value;
			deletePhone(idPhone, phoneIndex);
		}
	}

	function addPhone(phoneIndex) {
		var tipoAdd = getRadioValue('idTipoTelefono' + phoneIndex);
		var telefonoAdd = document.getElementById('telefono' + phoneIndex).value;
		var claveAdd = document.getElementById('clave' + phoneIndex).value;
		var accesoAdd = document.getElementById('acceso' + phoneIndex).value;
		var extensionAdd = document.getElementById('extension' + phoneIndex).value;
		dojo.xhrGet({url: 'perfil.do?method=saveAddPhone&claveAdd='+claveAdd+'&accesoAdd='+accesoAdd+'&telefonoAdd='+telefonoAdd+'&tipoAdd='+tipoAdd+'&extensionAdd='+extensionAdd, form:'perfilForm', sync: true, timeout:180000,
			load: function(data) {
//										 dojo.byId('phoneadds').innerHTML=data;
				document.getElementById('idTelefono' + phoneIndex).value = data;
				switch (phoneIndex) {
					case 1:
						// TODO: What 'bout this case???
						break;
					case 2:
						document.getElementById('phoneTwoSave').style.display = 'none';
						document.getElementById('phoneTwoDelete').style.display = 'block';
						document.getElementById('phoneTwoAdd').style.display = 'block';
						break;
					case 3:
						document.getElementById('phoneThreeSave').style.display = 'none';
						document.getElementById('phoneThreeDelete').style.display = 'block';
						break;
				}
			}});
	}

	function deletePhone(idphone, phoneIndex) {
		dojo.xhrGet({url: 'perfil.do?method=deletePhone&idPhone='+idphone, form:'perfilForm', sync: true, timeout:180000,
			load: function(data) {
//										 dojo.byId('phoneadds').innerHTML=data;
				switch (phoneIndex) {
					case 1:
						// Do Nothing
						break;
					case 2:
						if (document.getElementById("phoneThreeVisible").value == "on") {

							// Copy values from phoneThree -> phoneTwo
							if (document.getElementById('idTelefono3').value != "") { // There is a real value
								document.getElementById('idTelefono' + phoneIndex).value = document.getElementById('idTelefono' + (phoneIndex + 1)).value;
							}
							uncheckRadioGroup('idTipoTelefono' + phoneIndex);
							var idTipoTelefono = getRadioValue('idTipoTelefono' + (phoneIndex + 1));
							checkRadioGroupByValue('idTipoTelefono' + phoneIndex, idTipoTelefono);
							document.getElementById('clave' + phoneIndex).value = document.getElementById('clave' + (phoneIndex + 1)).value;
							document.getElementById('acceso' + phoneIndex).value = document.getElementById('acceso' + (phoneIndex + 1)).value;
							document.getElementById('telefono' + phoneIndex).value = document.getElementById('telefono' + (phoneIndex + 1)).value;
							document.getElementById('extension' + phoneIndex).value = document.getElementById('extension' + (phoneIndex + 1)).value;

							if (document.getElementById('idTelefono3').value != "") { // There is a real value
								document.getElementById('phoneTwoSave').style.display = 'none';
								document.getElementById('phoneTwoDelete').style.display = 'block';
								document.getElementById('phoneTwoAdd').style.display = 'block';
							} else {
								document.getElementById('phoneTwoSave').style.display = 'block';
								document.getElementById('phoneTwoDelete').style.display = 'none';
								document.getElementById('phoneTwoAdd').style.display = 'none';
							}
							document.getElementById('phoneThree').style.display = 'none';

							// Reset values of phone3
							document.getElementById('idTelefono' + (phoneIndex + 1)).value = '';
							uncheckRadioGroup('idTipoTelefono' + (phoneIndex + 1));
							document.getElementById('clave' + (phoneIndex + 1)).value = '';
							document.getElementById('acceso' + (phoneIndex + 1)).value = '';
							document.getElementById('telefono' + (phoneIndex + 1)).value = '';
							document.getElementById('extension' + (phoneIndex + 1)).value = '';

							document.getElementById("phoneThreeVisible").value = "off";
						} else {

							document.getElementById('phoneOneAdd').style.display = 'block';
							document.getElementById('phoneTwo').style.display = 'none';
							document.getElementById('phoneTwoSave').style.display = 'block';
							document.getElementById('phoneTwoDelete').style.display = 'none';
							document.getElementById('phoneTwoAdd').style.display = 'none';
							document.getElementById('phoneThree').style.display = 'none';

							// Reset values
							document.getElementById('idTelefono' + phoneIndex).value = '';
							uncheckRadioGroup('idTipoTelefono' + phoneIndex);
							document.getElementById('clave' + phoneIndex).value = '';
							document.getElementById('acceso' + phoneIndex).value = '';
							document.getElementById('telefono' + phoneIndex).value = '';
							document.getElementById('extension' + phoneIndex).value = '';
						}
						break;
					case 3:
						document.getElementById('phoneTwoAdd').style.display = 'block';
						document.getElementById('phoneThree').style.display = 'none';

						document.getElementById("phoneThreeVisible").value = "off";

						// Reset values
						document.getElementById('idTelefono' + phoneIndex).value = '';
						uncheckRadioGroup('idTipoTelefono' + phoneIndex);
						document.getElementById('clave' + phoneIndex).value = '';
						document.getElementById('acceso' + phoneIndex).value = '';
						document.getElementById('telefono' + phoneIndex).value = '';
						document.getElementById('extension' + phoneIndex).value = '';
						break;
				}
			}});
	}

	function fillAccessAddKey(phoneIndex) {
		var accesoDes;
		var tipoTelefonoId = getRadioValue('idTipoTelefono' + phoneIndex);
		if (tipoTelefonoId == 1){
			accesoDes = '044';
			document.getElementById('extension' + phoneIndex).value='';
			dijit.byId('extension' + phoneIndex).disabled=true;
//									 document.getElementById('extensionAdd').readOnly=true;
			document.getElementById('extension' + phoneIndex).readOnly=true;
		} else {
			accesoDes = '01';
			document.getElementById('extension' + phoneIndex).value='';
			dijit.byId('extension' + phoneIndex).disabled=false;
			document.getElementById('extension' + phoneIndex).readOnly=false;
		}
//								 var wAcceso = document.getElementById('accesoAdd');
		var wAcceso = document.getElementById('acceso' + phoneIndex);
		wAcceso.value=accesoDes;
		wAcceso.disabled='disabled';
	}

	function toggleAddPhone1() {
		document.getElementById('phoneOneAdd').style.display = 'none';
		document.getElementById('phoneTwo').style.display = 'block';
		document.getElementById('phoneTwoAdd').style.display = 'none';
	}

	function toggleAddPhone2() {
		document.getElementById('phoneTwoAdd').style.display = 'none';
		document.getElementById('phoneTwoSave').style.display = 'none';
		document.getElementById('phoneThree').style.display = 'block';
		document.getElementById('phoneThreeSave').style.display = 'block';
		document.getElementById('phoneThreeDelete').style.display = 'none';

		document.getElementById("phoneThreeVisible").value = "on";
	}

	function isNumber(n) {
		return !isNaN(parseFloat(n)) && isFinite(n);
	}

	//Dependiendo de la seleccion de tipo de telefono, coloca la clave de acceso correspondiente
	function fillUpAccess(index) {
		var tipoTelefonoId = getRadioValue('idTipoTelefono' + index);
		var accesoDes;
		if (tipoTelefonoId == 1){
			accesoDes = '044';
			document.getElementById('extension' + index).value='';
			dijit.byId('extension' + index).disabled=true;
			document.getElementById('extension' + index).readOnly=true;

		} else{
			accesoDes = '01';
			document.getElementById('extension' + index).value='';
			dijit.byId('extension' + index).disabled=false;
			document.getElementById('extension' + index).readOnly=false;

		}
		var wAcceso = dijit.byId('acceso' + index);
		wAcceso.attr('value',accesoDes);
	}

	function validateData(phoneIndex) {
		var tipoTelefonoId = getRadioValue('idTipoTelefono' + phoneIndex);
		var vClave = document.getElementById('clave' + phoneIndex).value;
		var vTelefono = document.getElementById('telefono' + phoneIndex).value;
		var vExtension = document.getElementById('extension' + phoneIndex).value;
		if (!tipoTelefonoId) {
			message('Debe seleccionar el tipo de teléfono.');
			return false;
		}
		if (!isNumber(vClave)) {
			message('La clave LADA debe ser un valor numérico (0-9).');
//									 document.perfilForm.claveAdd.focus();
			document.perfilForm['clave' + phoneIndex].focus();
			return false;
		}else {
			if (!vClave || vClave.length<2){
				message('Debe proporcionar una clave LADA de 2 ó 3 caracteres.');
//										 document.perfilForm.claveAdd.focus();
				document.perfilForm['clave' + phoneIndex].focus();
				return false;
			}else {
				if(vClave.length==2) {
					if(vTelefono.length!=8){
						message('Debe proporcionar un número telefónico de 8 dígitos.');
//												 document.perfilForm.claveAdd.focus();
						document.perfilForm['clave' + phoneIndex].focus();
						return false;
					}
				}else if(vClave.length==3) {
					if (vTelefono.length!=7){
						message('Debe proporcionar un número telefónico de 7 dígitos.');
//												 document.perfilForm.claveAdd.focus();
						document.perfilForm['clave' + phoneIndex].focus();
						return false;
					}
				}
			}
		}
		if(!vTelefono) {
			message('Debe proporcionar el número telefónico.');
//									 document.perfilForm.telefonoAdd.focus();
			document.perfilForm['telefono' + phoneIndex].focus();
			return false;
		}else {
			if(!isNumber(vTelefono)){
				message('El número telefónico debe ser un valor numérico (0-9).');
//										 document.perfilForm.telefonoAdd.focus();
				document.perfilForm['telefono' + phoneIndex].focus();
				return false;
			}
		}
		if (vExtension && !isNumber(vExtension)) {
			message('La extensión debe ser un valor numérico (0-9).');
//									 document.perfilForm.extensionAdd.focus();
			document.perfilForm['extension' + phoneIndex].focus();
			return false;
		}
		return true;
	}
	
	function cargarDireccion(){
		<c:if test="${perfilForm.permisoGeolocalizacion}">
			loadGoogleMaps();
	  		$('#googleMapsLoading').hide();
			$('#googleMapsSection').show();
			var originalLocation = $.parseJSON(dijit.byId('txtGeoLatLng').attr('value'));
			if(originalLocation && originalLocation.lat!=0 && originalLocation.lng!=0){
				console.log('existen coordenadas registradas');
				console.log('Latitud: ' + originalLocation.lat);
				var location = new google.maps.LatLng(originalLocation.lat, originalLocation.lng);
				mapCenter = new google.maps.LatLng(originalLocation.lat, originalLocation.lng);
				map.setZoom(17);
				map.setCenter(mapCenter);
				placeMarker(location);
			}else{
				console.log('No existen coordenadas registradas.');
				$('#mensajeAvisoGeolocalizacion').show();
				ubicarDireccionUsuario();
			}
		</c:if>
	}
  	
  	function ubicarDireccionUsuario(){
  		<c:if test="${perfilForm.permisoGeolocalizacion}">
	  		var address =dijit.byId('calle').attr('value')+' '+ dijit.byId('numeroExterior').attr('value')+', '+
			dijit.byId('idColoniaSelect').attr('displayedValue')+', '+
			dijit.byId('idEntidadSelect').attr('displayedValue')+', '+
			dijit.byId('idMunicipioSelect').attr('displayedValue');
	  		dijit.byId('txtGeoreferencia').attr('value', address);
	  		geocodeAddress(map, address);
	  	</c:if>
  	}

</script>

<c:if test="${perfilForm.permisoGeolocalizacion}">
	<script type="text/javascript" src="https://maps.google.com/maps/api/js?v=3&sensor=false&libraries=places" ></script>
	<script src="<%=request.getContextPath()%>/js/geolocalizacion.js" type="text/javascript"></script>
	<script type="text/javascript">
		function fillLatLng(position){
			dijit.byId('txtLat').attr('value', position.lat());
			dijit.byId('txtLng').attr('value', position.lng());
		}
	</script>
</c:if>


<noscript>Funciones de JavaScript desactivadas por navegador</noscript>

<style>
	@import "css/geolocalizacion.css";
</style>

<div class="formApp miEspacio">
	<!-- INICIA FORMULARIO DE PERFIL  -->
	<form name="perfilForm" id="perfilForm" method="post"
		action="perfil.do" dojoType="dijit.form.Form">
		<input type="hidden" name="Ctrl" id="Ctrl"
			dojoType="dijit.form.TextBox" value="1" /> <input type="hidden"
			name="method" id="method" value="init" /> <input type="hidden"
			name="idphone" id="idphone" value="" />

		<div dojoType="dojo.data.ItemFileReadStore"
			jsId="entidadFederativaStore" urlPreventCache="true"
			clearOnClose="true"></div>
		<div dojoType="dojo.data.ItemFileReadStore" jsId="municipioStore"
			urlPreventCache="true" clearOnClose="true"></div>
		<div dojoType="dojo.data.ItemFileReadStore" jsId="coloniaStore"
			urlPreventCache="true" clearOnClose="true"></div>

		<div dojoType="dojo.data.ItemFileReadStore" jsId="buscaTrabajoStore"
			urlPreventCache="true" clearOnClose="true"
			url="${context}perfil.do?method=buscaTrabajo&idTrab=${perfilForm.idTrabaja}"></div>


		<h2>Mi espacio</h2>
		<div class="tab_block">
			<div align="left" id="returnME" style="display: block;">
				<a
					href="javascript:location.replace('<c:url value="/miEspacioCandidato.do"/>');"
					class="expand"> <strong>Ir al inicio de Mi espacio</strong></a>
			</div>
			<div class="Tab_espacio">
				<h3>Editar mi perfil</h3>
				<div class="subTab">
					<ul>
						<li class="curSubTab"><span>Datos personales</span></li>
						<li><a
							href="javascript:messageRutaCancel('${perfilForm.errorMessageTab}','<%=response.encodeURL(context + "escolaridad.do?method=init")%>');">Escolaridad
								y otros conocimientos</a></li>
						<li><a
							href="javascript:messageRutaCancel('${perfilForm.errorMessageTab}','<%=response.encodeURL(context+"experiencia.do?method=init")%>');">Experiencia
								y expectativas laborales</a></li>
						<li><a
							href="javascript:messageRutaCancel( '${perfilForm.errorMessageTab}', '<%=response.encodeURL(context+"expectativa.do?method=init")%>');">Situación
								laboral</a></li>
					</ul>
					<div class="clearfix"></div>
				</div>
				<div class="clearfix"></div>
			</div>
			<!--  
	        <div class="sublevelTitle">
	            Ofertas de acuerdo a mi perfil
	        </div>-->
			<p class="labeled">
				Los datos marcados con <span>*</span> son obligatorios
			</p>
		</div>



		<div class="app_holder2">
			<div class="app">
				<div class="datos_personales">

					<fieldset class="cuenta_edit">
						<legend>Datos de Mi cuenta</legend>
						<div class="grid1_3 margin_top_10">
							<label>Usuario</label> <span class="usuario">&nbsp;${perfilForm.usuario}</span>
						</div>
						<div class="grid1_3 margin_top_10">
							<label for="password">Cambiar contraseña</label> <input
								type="password" dojoType="dijit.form.ValidationTextBox"
								name="password" id="password" maxlength="10"
								intermediateChanges="false" constraints="{min:8,max:12}"
								regExp="${regexppwd}"
								invalidMessage="La contraseña debe conformarse entre 8 y 12 letras y/o números">
						</div>
						<div class="grid1_3 margin_top_10">
							<label for="confirmpasswd">Confirmar contraseña</label> <input
								type="password" dojoType="dijit.form.ValidationTextBox"
								name="confirmpasswd" id="confirmpasswd" maxlength="10"
								validator="confirmPassword" intermediateChanges="false"
								invalidMessage="La confirmación debe ser igual a la contraseña">
						</div>
						<div class="gris margin_top_30">
							<!--  TODO: COMENTAR EN PRODUCCION   -->
							<c:if
								test="${not empty USUARIO_APP and not USUARIO_APP.candidato}">
								<p>
									<span class="c_naranja">*</span><strong>Detalle de la
										desactivación</strong>
								</p>
								<p>Indique el detalle del motivo de la desactivación de la
									cuenta del candidato</p>
								<br clear="all">
								<span class="c_rojo">Haz clic derecho sobre las palabras
									subrayadas para obtener sugerencias</span>
								<div class="campoTexto">
									<p class="tres_cuartos fl">
										<textarea rows="4" cols="70" style="width: 90%;"
											name="detalleDesactivacion" id="detalleDesactivacion"
											onkeypress="return caracteresValidos(event);" trim="true"
											onchange="return maximaLongitud(this,200)"
											onKeyUp="return maximaLongitud(this,200)" maxlength="200"
											style="width:550px;min-height:150px;_height:200px;"
											required="false"
											regExp="^[\w\d\s\.&aacute;&eacute;&iacute;&oacute;&uacute;&Aacute;&Eacute;&Iacute;&Oacute;&Uacute;&Ntilde;&ntilde;]{1,200}$">${perfilForm.detalleDesactivacion}</textarea>
										<script type="text/javascript">
		         		var vSpellCon = new GoogieSpell("googiespell/", '<%//=vProxy%>');
		         		vSpellCon.setLanguages({'es': 'Español'});
	   					vSpellCon.hideLangWindow();
	  					vSpellCon.decorateTextarea("detalleDesactivacion");
					</script>
									</p>
									<p class="fl" style="margin: 40px 0 0;"></p>
									<br clear="all" />
								</div>
							</c:if>


							<c:choose>
								<c:when
									test="${not empty USUARIO_APP and not USUARIO_APP.candidato}">
									<c:choose>
										<c:when test="${perfilForm.estatusCandidato eq 1}">
											<p class="c_naranja">
												Al elegir "Desactivar cuenta", ocultarás temporalmente tu
												información.<br> Considera que las empresas no podrán
												consultar tus datos, ni comunicarse contigo.
											</p>
											<div>
												<input type="button" id="btnDesactivar" name="btnDesactivar"
													class="boton_naranja"
													onclick="doSubmitDesactivarAdmor(true);" value="Desactivar">
											</div>
										</c:when>
									</c:choose>
								</c:when>
								<c:when
									test="${not empty USUARIO_APP and USUARIO_APP.candidato}">
									<c:choose>
										<c:when test="${perfilForm.estatusCandidato eq 1}">
											<p class="c_naranja">
												Al elegir "Desactivar cuenta", ocultarás temporalmente tu
												información.<br> Considera que las empresas no podrán
												consultar tus datos, ni comunicarse contigo.
											</p>
											<div>
												<input type="button" id="btnDesactivar" name="btnDesactivar"
													class="boton_naranja"
													onclick="doSubmitDesactivarCandidato(true);"
													value="Desactivar">
											</div>
										</c:when>
									</c:choose>
								</c:when>
							</c:choose>
						</div>
					</fieldset>




					<fieldset class="estado_civil">
						<legend>Estado civil</legend>

						<div class="grid1_3 margin_top_30">
							<label for="idEstadoCivilSelect">Estado civil</label> <input
								type="hidden" name="idEstadoCivil" id="idEstadoCivil"
								dojoType="dijit.form.TextBox"> <select
								dojotype="dijit.form.FilteringSelect" id="idEstadoCivilSelect"
								value="${perfilForm.idEstadoCivil}" required="false"
								invalidMessage="Dato inválido" missingMessage="Dato requerido"
								autocomplete="true" class="${classCivil}">
								<c:forEach var="row" items="${estadoscivil}">
									<option value="${row.idCatalogoOpcion}">${row.opcion}</option>
								</c:forEach>
							</select>
						</div>
					</fieldset>
					<fieldset class="dom_edit">
						<legend>Domicilio actual</legend>
						<div class="grid1_3 margin_top_10 fl">
							<label for="calle"><span>*</span> Calle</label> <input
								type="text" name="calle" id="calle" maxlength="150" size="60"
								value="${perfilForm.calle}"
								dojoType="dijit.form.ValidationTextBox" required="true"
								regExp="^[\w\d\s\.áéíóúÁÉÍÓÚñÑ]{1,150}$" uppercase="true"
								invalidMessage="La calle no es válida, favor de verificar"
								missingMessage="Debe indicar la calle." trim="true">
						</div>
						<div class="margin_top_10 fl">
							<div class="fl">
								<label for="numeroExterior"><span>*</span> Número
									exterior</label> <input id="numeroExterior" name="numeroExterior"
									maxlength="50" size="4" value="${perfilForm.numeroExterior}"
									dojoType="dijit.form.ValidationTextBox" required="true"
									regExp="^[\w\d\s]{1,50}$"
									invalidMessage="Número exterior no válido."
									missingMessage="Debe indicar el número exterior." trim="true">
							</div>
							<div class="fl">
								<label for="numeroInterior"><span></span> Número
									interior</label> <input id="numeroInterior" name="numeroInterior"
									maxlength="50" size="4" value="${perfilForm.numeroInterior}"
									dojoType="dijit.form.ValidationTextBox" required="false"
									regExp="^[\w\d\s]{1,50}"
									invalidMessage="Numero interior no válido"
									missingMessage="Dato requerido" trim="true"
									class="${classNumInt}">
							</div>
						</div>
						<div class="clearfix"></div>
						<div class="margin_top_10 ubicacion_1">
							<div class="grid1_3 fl">
								<label for="entreCalle">Entre qué calles</label> <input
									id="entreCalle" name="entreCalle" maxlength="150" size="60"
									value="${perfilForm.entreCalle}"
									dojoType="dijit.form.ValidationTextBox" required="false"
									regExp="^[\w\d\s\.áéíóúÁÉÍÓÚñÑ]{1,150}$" uppercase="true"
									invalidMessage="La calle no es válida, favor de verificar"
									trim="true" class="${classCalle}">
							</div>
							<div class="y">
								<label for="yCalle">y</label>
							</div>
							<div class="grid1_3 margin_top_30 fl">
								<input id="yCalle" name="yCalle" maxlength="150" size="60"
									value="${perfilForm.yCalle}"
									dojoType="dijit.form.ValidationTextBox" required="false"
									regExp="^[\w\d\s\.áéíóúÁÉÍÓÚñÑ]{1,150}$" uppercase="true"
									invalidMessage="La calle no es válida, favor de verificar"
									trim="true" class="${classYCalle}">
							</div>
						</div>
						<div class="clearfix"></div>
						<div class="grid1_3 margin_top_10 fl">
							<label for="idEntidadSelect"><span>*</span> Entidad
								Federativa donde radicas</label> <input type="hidden" name="idEntidad"
								id="idEntidad" dojoType="dijit.form.TextBox"> <select
								dojotype="dijit.form.FilteringSelect"
								store="entidadFederativaStore" id="idEntidadSelect"
								required="true" invalidMessage="Dato no válido"
								missingMessage="Es necesario indicar la entidad."
								autocomplete="true" onchange="javascript:actulizaMunicipio();"
								scrollOnFocus="true" maxHeight="250">
							</select>
						</div>
						<div class="grid1_3 margin_top_10 fl">
							<label for="idMunicipioSelect"><span>*</span> Municipio o
								Delegación</label> <input type="hidden" name="idMunicipio"
								id="idMunicipio" dojoType="dijit.form.TextBox"> <select
								dojotype="dijit.form.FilteringSelect" store="municipioStore"
								id="idMunicipioSelect" required="true"
								invalidMessage="Dato no válido"
								missingMessage="Es necesario indicar un municipio."
								autocomplete="true" onchange="javascript:actulizaColonia();"
								scrollOnFocus="true" maxHeight="250">
							</select>
						</div>
						<div class="grid1_3 fl margin_top_10 margin_no_r">
							<label for="idColonia"><span>*</span> Colonia</label> <input
								type="hidden" name="idColonia" id="idColonia"
								dojoType="dijit.form.TextBox"> <select
								dojotype="dijit.form.FilteringSelect" store="coloniaStore"
								id="idColoniaSelect" required="true"
								invalidMessage="Dato inválido"
								missingMessage="Es necesario indicar la colonia."
								autocomplete="true"
								onchange="javascript:actulizaCodigoPostal();"
								scrollOnFocus="true" maxHeight="250">
							</select>
						</div>
						<div class="clearfix"></div>
						<div class="grid1_3 margin_top_10">
							<label for="codigoPostal">Código Postal</label> <input
								type="text" name="codigoPostal" id="codigoPostal" maxlength="5"
								style="width: 7em;" value="${perfilForm.codigoPostal}"
								dojoType="dijit.form.ValidationTextBox" required="true"
								regExp="^[0-9]{5}"
								invalidMessage="Codigo incorrecto, solo se admiten caracteres numericos."
								missingMessage="Es necesario indicar el codigo postal."
								trim="true" readonly="readonly">
						</div>
					</fieldset>

					<c:if test="${perfilForm.permisoGeolocalizacion}">
						<fieldset class="datosContacto">
							<legend>Georeferencia</legend>
		                    	<div id="googleMapsLoading">
		                    		 <img alt="Espere por favor..." src="<%=request.getContextPath()%>/images/geolocalizacion.gif" height=""/> Cargando informaci&oacute;n de ubicación
		                    	</div>
		                   		<div id="mensajeAvisoGeolocalizacion" style="display: none;">
		                    		<p class="c_naranja">La ubicación se calculo por medio del domicilio actual registrado y aun no se ha guardado la información 
		                    		geo-referencial de su ubicación en nuestro registros</p>
		                   		</div>
		                    	<div id="googleMapsSection" style="display: none;">
			                    	<input id="txtGeoreferencia" type="hidden" value="" dojoType="dijit.form.TextBox"/>
			                    	<input id="txtLat" type="hidden" name="latitud" value="" dojoType="dijit.form.TextBox"/>
			                    	<input id="txtLng" type="hidden" name="longitud" value="" dojoType="dijit.form.TextBox"/>
			                    	<input id="txtGeoLatLng" type="hidden" value="{&quot;lat&quot;:${perfilForm.latitud}, &quot;lng&quot;:${perfilForm.longitud}}" dojoType="dijit.form.TextBox"/>
									<input id="pac-input" class="controls" type="text" placeholder="Indique una dirección ">
									<div id="map" style="width: 100%; height: 400px;"></div>
									
									<input id="btnCurrentLocation" type="button" value="Obtener ubicación por Navegador" 
										style="font-size: 12px; margin: 0px;" class="boton_naranja"
										onclick="loadCurrentLocation();"/>
									
									<input id="btnBuscarByDireccion" type="button" value="Obtener por domicilio registrado" 
										style="font-size: 12px; margin: 0px;" class="boton_naranja"
										onclick="ubicarDireccionUsuario();"/>
								</div>
						</fieldset>
					</c:if>
					
					<fieldset class="datosContacto">
						<legend>Datos de contacto</legend>
						<div class="labeled margin_top_10">
							<span>*</span> Teléfono
						</div>
						<p>
							<em>Debe ingresar un total de 10 dígitos (Clave LADA +
								Teléfono).</em>
						</p>

						<c:set var="phonesSize"
							value="${sessionScope['LST_TELEFONOS_ADICIONALES'].size()}" />
						<c:set var="phones"
							value="${sessionScope['LST_TELEFONOS_ADICIONALES']}" />

						<div id="phoneOne">


							<div class="grid1_3  margin_top_20 fl">
								<div class="margin-r_20 fl">
									<div class="labeled margin_no_t">
										<span>*</span> Tipo de teléfono
									</div>
									<div class="fl">
										<logic:iterate id="tipoTels" name="perfilForm"
											property="tipoTelefonos"
											type="mx.gob.stps.portal.persistencia.vo.CatalogoOpcionVO">
											<div style="margin-left: 13px"
												class="tipo_tel margin_top_10 fl">
												<input type="radio" name="idTipoTelefono1"
													data-dojo-type="dijit.form.RadioButton"
													<%--id="idTipoTelefono1"--%> value="${tipoTels.idCatalogoOpcion}"
													onClick="fillUpAccessKey(1);"
													${phones[0].idTipoTelefono eq tipoTels.idCatalogoOpcion ? 'checked="checked"' : '' } />
												<label><%=tipoTels.getOpcion()%></label>
											</div>
										</logic:iterate>

									</div>
								</div>
								<div class="margin-r_10 fl">
									<label class="fw_no_bold" for="acceso1"><span>*</span>
										Acceso</label>
									<div class="ta_center">
										<span class="acceso"><input type="text" name="acceso1"
											id="acceso1" value="${perfilForm.acceso}" maxlength="3"
											style="width: 3em;" dojoType="dijit.form.ValidationTextBox"
											required="true" readonly="readonly"
											regExp='^(<%=Constantes.CLAVE_TELEFONO_CELULAR + "|" + Constantes.CLAVE_TELEFONO_FIJO%>)$'
											invalidMessage="Dato inválido"
											missingMessage="Dato requerido" trim="true"></span>
									</div>
								</div>
								<div class="fl">
									<label for="clave1"><span>*</span> Clave lada</label> <input
										type="text" name="clave1" id="clave1" maxlength="3"
										style="width: 3em;" value="${perfilForm.clave}"
										dojoType="dijit.form.ValidationTextBox" required="true"
										regExp="^[+]?\d{2,3}$"
										invalidMessage="La clave debe ser numérica"
										missingMessage="Dato requerido" trim="true">
								</div>
							</div>
							<div class="margin_top_20 fl margin-r_20">
								<label for="telefono1"><span>*</span> Teléfono</label> <input
									type="text" name="telefono1" id="telefono1" maxlength="8"
									style="width: 8em;" value="${perfilForm.telefono}"
									onBlur="changePhoneSizeRequired(1);"
									dojoType="dijit.form.ValidationTextBox" required="true"
									regExp="^[+]?\d{7,8}$"
									invalidMessage="El teléfono debe ser numérico"
									missingMessage="Dato requerido" trim="true">
							</div>
							<div class="margin_top_20 fl margin-r_20">
								<label for="extension1">Extensión</label> <input type="text"
									name="extension1" id="extension1" maxlength="8"
									style="width: 8em;" value="${perfilForm.extension}"
									dojoType="dijit.form.ValidationTextBox" required="false"
									regExp="^[+]?\d{0,8}$"
									invalidMessage="La extensión debe ser numérica"
									missingMessage="Dato requerido" trim="true" class="${classExt}">
							</div>
							<div class="margin_top_50 fl add_ph" id="phoneOneAdd"
								style="${phonesSize == 1 ? 'display: block' : 'display: none'}">
								<input type="button" class="agregar"
									onclick="javascript:toggleAddPhone1();"
									title="Agregar teléfono" value="Agregar teléfono" />
							</div>
						</div>
						<div class="clearfix"></div>

						<div id="phoneTwo"
							style="${phonesSize >= 2 ? 'display: block' : 'display: none'}">
							<input type="hidden" id="idTelefono2"
								value="${phonesSize >= 2 ? phones[1].idTelefono : ''}" />
							<div class="grid1_3  margin_top_20 fl">
								<div class="margin-r_20 fl">
									<div class="labeled margin_no_t">
										<span>*</span> Tipo de teléfono
									</div>
									<div class="fl">
										<logic:iterate id="tipoTels" name="perfilForm"
											property="tipoTelefonos"
											type="mx.gob.stps.portal.persistencia.vo.CatalogoOpcionVO">
											<div style="margin-left: 13px"
												class="tipo_tel margin_top_10 fl">
												<input type="radio" name="idTipoTelefono2"
													data-dojo-type="dijit.form.RadioButton"
													<%--id="idTipoTelefono2"--%> value="${tipoTels.idCatalogoOpcion}"
													onClick="fillUpAccessKey(2);"
													${phones[1].idTipoTelefono eq tipoTels.idCatalogoOpcion ? 'checked="checked"' : '' } />
												<label><%=tipoTels.getOpcion()%></label>
											</div>
										</logic:iterate>
									</div>
								</div>
								<div class="margin-r_10 fl">
									<label class="fw_no_bold" for="acceso2"><span>*</span>
										Acceso</label>
									<div class="ta_center">
										<span class="acceso"><input type="text" name="acceso2"
											id="acceso2" value="${phones[1].acceso}" maxlength="3"
											style="width: 3em;" dojoType="dijit.form.ValidationTextBox"
											required="true" readonly="readonly"
											regExp='^(<%=Constantes.CLAVE_TELEFONO_CELULAR + "|" + Constantes.CLAVE_TELEFONO_FIJO%>)$'
											invalidMessage="Dato inválido"
											missingMessage="Dato requerido" trim="true"></span>
									</div>
								</div>
								<div class="fl">
									<label for="clave2"><span>*</span> Clave lada</label> <input
										type="text" name="clave2" id="clave2" maxlength="3"
										style="width: 3em;" value="${phones[1].clave}"
										dojoType="dijit.form.ValidationTextBox" required="true"
										regExp="^[+]?\d{2,3}$"
										invalidMessage="La clave debe ser numérica"
										missingMessage="Dato requerido" trim="true">
								</div>
							</div>
							<div class="margin_top_20 fl margin-r_20">
								<label for="telefono2"><span>*</span> Teléfono</label> <input
									type="text" name="telefono2" id="telefono2" maxlength="8"
									style="width: 8em;" value="${phones[1].telefono}"
									onBlur="changePhoneSizeRequired(2);"
									dojoType="dijit.form.ValidationTextBox" required="true"
									regExp="^[+]?\d{7,8}$"
									invalidMessage="El teléfono debe ser numérico"
									missingMessage="Dato requerido" trim="true">
							</div>
							<div class="margin_top_20 fl margin-r_20">
								<label for="extension2">Extensión</label> <input type="text"
									name="extension2" id="extension2" maxlength="8"
									style="width: 8em;" value="${phones[1].extension}"
									dojoType="dijit.form.ValidationTextBox" required="false"
									regExp="^[+]?\d{0,8}$"
									invalidMessage="La extensión debe ser numérica"
									missingMessage="Dato requerido" trim="true" class="${classExt}">
							</div>
							<div id="phoneTwoSave" class="margin_top_50 fl"
								style="${phonesSize >= 2 ? 'display: none' : 'display: block'}">
								<input type="button" class="enviar"
									<%--id="btnAgregarTelefono"--%> title="Guardar teléfono"
									value="Guardar teléfono" onclick="doSubmitAddPhone(2);" />
							</div>
							<div id="phoneTwoDelete" class="margin_top_50 margin-r_10 fl"
								style="${phonesSize >= 2 ? 'display: block' : 'display: none'}">
								<input type="button" class="eliminar"
									onclick="javascript:doDeletePhone(2)" title="Eliminar teléfono"
									value="Eliminar teléfono" />
							</div>
							<div id="phoneTwoAdd" class="margin_top_50 fl add_ph"
								style="${phonesSize >= 3 ? 'display: none' : 'display: block'}">
								<input type="button" class="agregar"
									onclick="javascript:toggleAddPhone2();"
									title="Agregar teléfono" value="Agregar teléfono" />
							</div>
						</div>
						<div class="clearfix"></div>

						<div id="phoneThree"
							style="${phonesSize >= 3 ? 'display: block' : 'display: none'}">
							<input type="hidden" id="idTelefono3"
								value="${phonesSize >= 3 ? phones[2].idTelefono : ''}" /> <input
								type="hidden" id="phoneThreeVisible"
								value="${phonesSize >= 3 ? " on" : "off"}"/>
							<div class="grid1_3  margin_top_20 fl">
								<div class="margin-r_20 fl">
									<div class="labeled margin_no_t">
										<span>*</span> Tipo de teléfono
									</div>
									<div class="fl">
										<logic:iterate id="tipoTels" name="perfilForm"
											property="tipoTelefonos"
											type="mx.gob.stps.portal.persistencia.vo.CatalogoOpcionVO">
											<div style="margin-left: 13px"
												class="tipo_tel margin_top_10 fl">
												<input type="radio" name="idTipoTelefono3"
													data-dojo-type="dijit.form.RadioButton"
													<%--id="idTipoTelefono3"--%> value="${tipoTels.idCatalogoOpcion}"
													onClick="fillUpAccessKey(3);"
													${phones[2].idTipoTelefono eq tipoTels.idCatalogoOpcion ? 'checked="checked"' : '' } />
												<label><%=tipoTels.getOpcion()%></label>
											</div>
										</logic:iterate>
									</div>
								</div>
								<div class="margin-r_10 fl">
									<label class="fw_no_bold" for="acceso3"><span>*</span>
										Acceso</label>
									<div class="ta_center">
										<span class="acceso"><input type="text" name="acceso3"
											id="acceso3" value="${phones[2].acceso}" maxlength="3"
											style="width: 3em;" dojoType="dijit.form.ValidationTextBox"
											required="true" readonly="readonly"
											regExp='^(<%=Constantes.CLAVE_TELEFONO_CELULAR + "|" + Constantes.CLAVE_TELEFONO_FIJO%>)$'
											invalidMessage="Dato inválido"
											missingMessage="Dato requerido" trim="true"></span>
									</div>
								</div>
								<div class="fl">
									<label for="clave3"><span>*</span> Clave lada</label> <input
										type="text" name="clave3" id="clave3" maxlength="3"
										style="width: 3em;" value="${phones[2].clave}"
										dojoType="dijit.form.ValidationTextBox" required="true"
										regExp="^[+]?\d{2,3}$"
										invalidMessage="La clave debe ser numérica"
										missingMessage="Dato requerido" trim="true">
								</div>
							</div>
							<div class="margin_top_20 fl margin-r_20">
								<label for="telefono3"><span>*</span> Teléfono</label> <input
									type="text" name="telefono3" id="telefono3" maxlength="8"
									style="width: 8em;" value="${phones[2].telefono}"
									onBlur="changePhoneSizeRequired(3);"
									dojoType="dijit.form.ValidationTextBox" required="true"
									regExp="^[+]?\d{7,8}$"
									invalidMessage="El teléfono debe ser numérico"
									missingMessage="Dato requerido" trim="true">
							</div>
							<div class="margin_top_20 fl margin-r_20">
								<label for="extension3">Extensión</label> <input type="text"
									name="extension3" id="extension3" maxlength="8"
									style="width: 8em;" value="${phones[2].extension}"
									dojoType="dijit.form.ValidationTextBox" required="false"
									regExp="^[+]?\d{0,8}$"
									invalidMessage="La extensión debe ser numérica"
									missingMessage="Dato requerido" trim="true" class="${classExt}">
							</div>
							<div id="phoneThreeSave" class="margin_top_50 fl"
								style="${phonesSize >= 3 ? 'display: none' : 'display: block'}">
								<input type="button" class="enviar"
									<%--id="btnAgregarTelefono"--%> title="Guardar teléfono"
									value="Guardar teléfono" onclick="doSubmitAddPhone(3);" />
							</div>
							<div id="phoneThreeDelete" class="margin_top_50 margin-r_10 fl"
								style="${phonesSize >= 3 ? 'display: block' : 'display: none'}">
								<input type="button" class="eliminar"
									onclick="javascript:doDeletePhone(3)" title="Eliminar teléfono"
									value="Eliminar teléfono" />
							</div>
						</div>
						<div class="clearfix"></div>
						<input type="hidden" name="correoAux" id="correoAux"
							value="${perfilForm.correoElectronico}"
							dojoType="dijit.form.TextBox" />

						<div class="grid1_3 margin_top_30">
							<div id="correoActual" style="display: block;">
								<label>Correo electrónico ${perfilForm.correoElectronico}</label> <input type="button"
									id="btnCambiarCorreo" name="btnCambiarCorreo"
									class="boton_naranja" onclick="showConf(1);"
									value="Cambiar correo electrónico">
							</div>
						</div>
						<div id="correoNuevo" style="display: none;">
							<label>Nuevo correo electrónico</label> <input type="text"
								name="correoElectronico" id="correoElectronico" size="50"
								maxlength="65" value="${perfilForm.correoElectronico}"
								onchange="validarFormatoCorreo(this);" oncopy="return false;"
								onselect="noArrastrar(this);" oncut="return false"
								missingMessage="Es necesario indicar el correo electrónico."
								dojoType="dijit.form.ValidationTextBox" regExp="${regexpmail}"
								invalidMessage="Formato de correo electrónico invalido."
								onpaste="return false;" class="${classCorreo}">
							<div class="margin_top_20">
								<label>Confirmación nuevo correo electrónico</label> <input
									type="text" name="correoElectronicoConf"
									id="correoElectronicoConf" size="50" maxlength="65" value=""
									onchange="validarFormatoCorreo(this);" oncopy="return false;"
									onselect="noArrastrar(this);" oncut="return false"
									onpaste="return false;" class="${classCorreo}"> <input
									type="button" id="btnCambiarCorreo" name="btnCambiarCorreo"
									class="boton_naranja" onclick="showConf(0);" value="Cancelar">
							</div>
						</div>
						<div class="contact_pref margin_top_30">
							<div class="labeled">
								<span>*</span> Medio de contacto preferente
							</div>
							<input type="hidden" name="contactoTelefono"
								id="contactoTelefono" /> <input type="hidden"
								name="contactoCorreo" id="contactoCorreo" /> <input
								type="checkbox" name="hasCtcTel" id="hasCtcTel"
								dojoType="dijit.form.CheckBox" onclick="hasTelPrinc(this);"
								${perfilForm.contactoTelefono eq ctcTelSI ? "checked" : ""} /><label
								for="hasCtcTel">Teléfono</label><br /> <input type="checkbox"
								name="hasCtcCorreo" id="hasCtcCorreo"
								dojoType="dijit.form.CheckBox"
								onclick="correoCapturadoRadio(this)"
								${perfilForm.contactoCorreo eq ctcCorreoSI ? "checked" : ""} /><label
								for="hasCtcCorreo">Correo electrónico</label> <input
								type="hidden" name="idRecibeOferta" id="idRecibeOferta" />
						</div>
						<div class="offer_pref margin_top_30">
							<div class="labeled">¿Deseas recibir ofertas de empleo?
								por:</div>
							<input type="checkbox" id="idRecibeOferta1" value="${recibeTEL}"
								${perfilForm.idRecibeOferta eq recibeTEL ? 'checked="checked"' : '' }
								${perfilForm.idRecibeOferta eq recibeAMBAS ? 'checked="checked"' : '' }
								onclick="hasCel(this);" dojoType="dijit.form.CheckBox" /> <label
								for="idRecibeOferta1">Celular</label> <br /> <input
								type="checkbox" id="idRecibeOferta2" value="${recibeCORREO}"
								onclick="correoCapturadoRadio(this)"
								${perfilForm.idRecibeOferta eq recibeCORREO ? 'checked="checked"' : '' }
								${perfilForm.idRecibeOferta eq recibeAMBAS ? 'checked="checked"' : '' }
								dojoType="dijit.form.CheckBox" /> <label for="idRecibeOferta2">Correo
								electrónico</label> <input type="hidden" name="confidencialidad"
								id="confidencialidad" />
							<div class="margin_top_20">
								<input type="checkbox" name="hasConfiden" id="hasConfiden"
									${perfilForm.confidencialidad eq confidenSI ? "checked" : ""}
									value="hasConfiden" dojoType="dijit.form.CheckBox" /> <label
									for="hasConfiden">Deseo que mis datos personales
									permanezcan confidenciales</label>
							</div>
						</div>
					</fieldset>
				</div>
			</div>
		</div>
		<div class="form_nav">
			<input type="button" id="btnActualizar" name="btnActualizar"
				class="boton_naranja" onclick="validarCorreo();" value="Guardar">
			<input type="button" class="boton_naranja"
				onclick="doSubmitCancel();" value="Cancelar">
		</div>
	</form>
</div>