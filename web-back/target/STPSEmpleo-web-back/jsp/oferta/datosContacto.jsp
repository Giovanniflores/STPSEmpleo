<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
 <%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="es" lang="es">
<head>
	<style>
		#menuAdmin {
		    color: #333333;
		    font-family: Tahoma,Geneva,sans-serif;
		    font-size: 11px;
		    margin: auto;
		    width: 930px;
		}
		#menuAdmin ul {
		    list-style: outside none none;
		    margin-left: 2%;
		    margin-right: 2%;
		    margin-top: 24px;
		}
		#menuAdmin ul li {
		    float: left;
		    height: 36px;
		    margin-bottom: 10px;
		    margin-left: 5px;
		}
		#menuAdmin ul li a.adminCerrar {
		    background: #fe6a08;
		    color: #ffffff;
		}
		#menuAdmin ul li a {
		    border: 1px solid #dbdbdb;
		    color: #333333;
		    padding: 6px;
		    text-align: center;
		    text-decoration: none;
		    width: auto;
		}
	</style>
 	<%String context = request.getContextPath() +"/";%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<fmt:setBundle basename="MessageResources" var="portalbundle"/>

<c:set var="regexpnombre">^[\w\d\s\.áéíóúÁÉÍÓÚñÑ]{1,50}$</c:set>
<c:set var="regexpplazas">^[0-9]{1,8}$</c:set>


<c:set var="regexpnumext">^[\w\d\s]{1,50}$</c:set>
<c:set var="regexcodunipuestosfp">^[0-9^.]{1,10}$</c:set>
<c:set var="regexpnumint">^[\w\d\s]{1,50}</c:set>
<c:set var="regexpcalle">^[\w\d\s\.áéíóúÁÉÍÓÚñÑ]{1,150}$</c:set>
<c:set var="regexpmail">^[a-zA-Z0-9_-]+(?:\.[a-zA-Z0-9_-]+)*@(?:[a-zA-Z0-9](?:[a-zA-Z0-9-_]*[a-zA-Z0-9-])?\.)+[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?$</c:set>
<c:set var="regexpacceso">^(044|01)$</c:set>
<c:set var="regexplada">^[+]?\d{2,3}$</c:set>
<c:set var="regexptelefono">^[+]?\d{7,8}$</c:set>
<c:set var="regexpextension">^[+]?\d{0,8}$</c:set>
<c:set var="regexpcp">^[0-9]{5}</c:set>
<script type="text/javascript" src="js/helper/messageHelper.js"></script>
<script type="text/javascript">
	dojo.require("dijit.dijit"); // loads the optimized dijit layer
	dojo.require("dijit.form.Form");
	dojo.require("dijit.Dialog");
	dojo.require("dijit.form.Button");
	dojo.require("dijit.form.ComboBox");
	dojo.require("dojo.data.ItemFileReadStore");
	dojo.require("dijit.form.Textarea");
	dojo.require("dijit.form.TextBox");
	dojo.require("dijit.form.ValidationTextBox");
	dojo.require("dijit.form.DateTextBox");
	dojo.require("dijit.form.FilteringSelect");
	dojo.require("dijit.form.MultiSelect");
	dojo.require("dijit.form.CheckBox");
	
	function estableceAcceso(){
		if(dojo.byId('telefonoFijo').checked){
			dojo.byId('accesoDiv').innerHTML = '${ofertaEmpleoForm.accesoFijo}';
			document.getElementById('acceso').value = '${ofertaEmpleoForm.accesoFijo}';
			document.getElementById('extension').value = '';
			dijit.byId('extension').disabled=false;
			 document.getElementById('extension').readOnly=false;
		} else if(dojo.byId('telefonoCelular').checked){
			dojo.byId('accesoDiv').innerHTML = '${ofertaEmpleoForm.accesoCelular}';
			document.getElementById('acceso').value = '${ofertaEmpleoForm.accesoCelular}';
			document.getElementById('extension').value = '';
			dijit.byId('extension').disabled=true;
			 document.getElementById('extension').readOnly=true;
		} else {
			dojo.byId('accesoDiv').innerHTML = '${ofertaEmpleoForm.accesoCelular}';
			document.getElementById('acceso').value = '${ofertaEmpleoForm.accesoCelular}';
		}
	}
	
	function estableceAccesoAdd(campoAcceso,campoTipoTelefono,id,tipoTelefono,tipoTelefonoValue,divExtensionFijo,divExtensionCelular) {
		if (tipoTelefonoValue == 5) {
			dojo.byId('accesoDivAdd_'+id).innerHTML = '${ofertaEmpleoForm.accesoFijo}';
			document.getElementById(campoAcceso).value = '${ofertaEmpleoForm.accesoFijo}';
			document.getElementById(campoTipoTelefono).value = '${ofertaEmpleoForm.idTipoTelefonoFijo}';
			document.getElementById(divExtensionFijo).style.display='block';
			document.getElementById(divExtensionCelular).style.display='none';
			document.getElementById('extensionAdd').value='';
		} else if(tipoTelefonoValue == 1) {
			dojo.byId('accesoDivAdd_'+id).innerHTML = '${ofertaEmpleoForm.accesoCelular}';
			document.getElementById(campoAcceso).value = '${ofertaEmpleoForm.accesoCelular}';
			document.getElementById(campoTipoTelefono).value = '${ofertaEmpleoForm.idTipoTelefonoCelular}';
			document.getElementById(divExtensionFijo).style.display='none';
			document.getElementById(divExtensionCelular).style.display='block';
			document.getElementById('extensionAdd').value='';
			document.getElementById('extensionTelefonoAdicional_'+id).value='';
		} else {
			dojo.byId('accesoDivAdd_'+id).innerHTML = '${ofertaEmpleoForm.accesoCelular}';
			document.getElementById(campoAcceso).value = '${ofertaEmpleoForm.accesoCelular}';
			document.getElementById(campoTipoTelefono).value = '${ofertaEmpleoForm.idTipoTelefonoCelular}';
		}
	}
	
	function changePhoneSizeRequired(){
		var vClave = dijit.byId('clave');
		var vTelefono = dijit.byId('telefono');
		 
		if(vClave.value.length < 2){
			vClave.focus();
			displayErrorMsg(vClave, 'Debe proporcionar una clave LADA de 2 ó 3 caracteres');

		} else if(vClave.value.length == 2) {
			if(vTelefono.value.length != 8){
				displayErrorMsg(vTelefono, 'Debe proporcionar un teléfono de 8 dígitos');
			}
		} else if(vClave.value.length==3){
			if(vTelefono.value.length!=7){
				displayErrorMsg(vTelefono, 'Debe proporcionar un teléfono de 7 dígitos');
			}	
		}
	}
	
	
	function changePhoneSizeRequiredAdd(campoClave,campoTelefono){
		var vClave = dijit.byId(campoClave);
		var vTelefono = dijit.byId(campoTelefono);
		 
		if(vClave.value.length < 2){
			vClave.focus();
			displayErrorMsg(vClave, 'Debe proporcionar una clave LADA de 2 ó 3 caracteres');

		} else if(vClave.value.length == 2) {
			if(vTelefono.value.length != 8){
				displayErrorMsg(vTelefono, 'Debe proporcionar un teléfono de 8 dígitos');
			}
		} else if(vClave.value.length==3){
			if(vTelefono.value.length!=7){
				displayErrorMsg(vTelefono, 'Debe proporcionar un teléfono de 7 dígitos');
			}	
		}
	}
	
	
	function actulizaMunicipio(){
		var vEntidad = dijit.byId('idEntidadDomicilio').get('value');
		municipioStore.url="";
		municipioStore.close();	
		coloniaStore.url="";
		coloniaStore.close();
		dijit.byId('codigoPostal').set('value','');
		dijit.byId('idColoniaDomicilio').set('value','');
		if (vEntidad){
			
			dijit.byId('idMunicipioDomicilio').reset();
			dijit.byId('idColoniaDomicilio').reset();
			
			//dijit.byId('idMunicipioSelect').disabled=false;
			municipioStore.url = "${context}domicilio.do?method=obtenerMunicipio" + "&" + "idEntidad="+ vEntidad;
			municipioStore.close();			
		}
	}
	
	
	function actulizaColonia(){
		dijit.byId('codigoPostal').set('value','');
		var vEntidad   = dijit.byId('idEntidadDomicilio').get('value');
		var vMunicipio = dijit.byId('idMunicipioDomicilio').get('value');
		coloniaStore.url="";
		coloniaStore.close();	
		if (vEntidad && vMunicipio){
			
			dijit.byId('idColoniaDomicilio').reset();
			
			//dijit.byId('idColoniaSelect').disabled=false;
			coloniaStore.url = "${context}domicilio.do?method=obtenerColonia" + "&" + "idEntidad="+ vEntidad + "&" + "idMunicipio="+ vMunicipio;
			coloniaStore.close();
		}
	}
	
function actulizaCodigoPostal(){
	dijit.byId('codigoPostal').set('value','');
		var vEntidad   = dijit.byId('idEntidadDomicilio').get('value');
		var vMunicipio = dijit.byId('idMunicipioDomicilio').get('value');
		var vColonia   = dijit.byId('idColoniaDomicilio').get('value');
		
		if (vEntidad && vMunicipio && vColonia){
	    	var url = '${context}domicilio.do?method=obtenerCodigoPostal&idEntidad='+ vEntidad + '&idMunicipio='+ vMunicipio + '&idColonia='+ vColonia;

			dojo.xhrGet({url: url, sync: true, timeout:180000,
		       load: function(data){
					       var res = dojo.fromJson(data);
						   dojo.byId('codigoPostal').value = res.codigoPostal;
					    }
				});
		}
		
	}
	
	function doSubmit(method,idBoton) {
		dojo.byId(idBoton).disabled= true;
		estableceValores();
		if (!validaCampos()) {
			dojo.byId(idBoton).disabled= false;
			return;
		}
		if (!dojo.byId('medioTelefono').checked &&
				!dojo.byId('medioCorreo').checked &&
				!dojo.byId('medioDomicilio').checked) {
				messageDojoFocus('Es requerido que indique el medio de contacto de la oferta.', 'medioTelefono');
				//dojo.byId('medioTelefono').focus();
				dojo.byId(idBoton).disabled= false;
				return;
		}
		document.getElementById('method').value = method;
		dojo.byId('registroDatosContacto').submit();
		//document.registroDatosContacto.method.value=method;
		//document.registroDatosContacto.submit();
	}
	function messageDojoFocus(msg,id) {
		dojo.byId(id).focus();
		var html =
			'<div id="messageDialgop3" name="messageDialgop3">' +
		'<p style="text-align: center;">'+ msg +'</p>'+
		'<p class="form_nav">'
		+ cerrarMsg()
	    + '</p>'
	    +'</div>';
		dialogMsg = new dijit.Dialog({
	        title: 'Mensaje',
	        content: html,
	        style: "width:300px;",
	        showTitle: false, draggable : false, closable : false
	        
	    });		
		dialogMsg.show();
	}
	function gotoCancelInfUbi() {
		document.getElementById('method').value = 'cancelarEdicionOferta';
		dojo.byId('registroDatosContacto').submit();
	}
	function doSubmitCancelar() {
		var input = '<input id="btnCnl" class="boton_naranja" type="button" name="btnCnl" onClick="gotoCancelInfUbi();" value="Aceptar"/>';
		messageInput('Los datos no guardados se perderán ¿Continuar?',input);
	}
	function validaCampos() {
		if (!dijit.byId('registroDatosContacto').isValid()) {
			var vClave = dijit.byId('clave');
			var vTelefono = dijit.byId('telefono');
			if (!validaCampo('nombreContacto')) return false;
			if (!validaCampo('cargoContacto')) return false;
			if (!validaCampo('clave')) return false;
			if (!validaCampo('telefono')) return false;
			if (vClave.value.length == 2) {
				if (vTelefono.value.length != 8) {
					messageFocus('Debe proporcionar un teléfono de 8 dígitos', 'telefono');
					//vClave.focus();
					document.getElementById(vTelefono.id).blur();
					//vClave.focus();
					return false;
				}
			} else if (vClave.value.length==3) {
				if (vTelefono.value.length!=7) {
					messageFocus('Debe proporcionar un teléfono de 7 dígitos', 'telefono');v
					//vTelefono.focus();
					document.getElementById(vTelefono.id).blur()	;
					//vTelefono.focus();
					return false;
				}	
			}
			if (!dijit.byId('extension').isValid()){
				messageFocus(dijit.byId('extension').get("invalidMessage"), 'extension');
				displayErrorMsg(dijit.byId('extension'), dijit.byId('extension').get("invalidMessage"));
				//document.getElementById('extension').focus();
				document.getElementById('extension').blur();
				//document.getElementById('extension').focus();
				return false;
			}
			if (!validaCampo('correoElectronicoContacto')) return false;
			if (!validaCampo('calle')) return false;
			if (!validaCampo('numeroExterior')) return false;
			if (!validaCampoSelect('idEntidadDomicilio')) return false;
			if (!validaCampoSelect('idMunicipioDomicilio')) return false;
			if (!validaCampoSelect('idColoniaDomicilio')) return false;
			if (!validaCampoSelect('mesSelect')) return false;
			if (!validaCampoSelect('anioSelect')) return false;
			

		}
		return true;
	}
	
	function validaCampo(campo) {
		var control = dijit.byId(campo);
		if (control && control.value=='') {
			messageFocus(control.get("missingMessage"), campo);
			displayErrorMsg(dijit.byId(campo), dijit.byId(campo).get("missingMessage"));
			//control.focus();
			document.getElementById(campo).blur();
			//control.focus();
			return false;
		}
		if (!dijit.byId(campo).isValid()) {
			messageFocus(control.get("invalidMessage"), campo);
			displayErrorMsg(dijit.byId(campo), dijit.byId(campo).get("invalidMessage"));
			//control.focus();
			document.getElementById(campo).blur();
			//control.focus();
			return false;
		}
		return true;
	}

	function validaCampoSelect(campo) {
		var control = dijit.byId(campo);
		if (control && control.get('value')==''){
			messageFocus(control.get("missingMessage"), campo);
			dijit.showTooltip(control.get("missingMessage"), control.domNode, control.get("tooltipPosition"), !control.isLeftToRight());
			//control.focus();
			document.getElementById(campo).blur();
			//control.focus();
			return false;
		}
		return true;
	}

	function alertMsg(msg){
		message(msg);
	}
	
	function estableceValores() {
		if (dojo.byId('medioTelefono').checked)dojo.byId('contactoTelefono').value= 2;
		if (dojo.byId('medioCorreo').checked)dojo.byId('contactoCorreo').value= 2;
		if (dojo.byId('medioDomicilio').checked)dojo.byId('contactoDomicilio').value=2; 
	}
	
function doSubmitAddTelefono() {
	if (!validaCampo('claveAdd')) return false;
	if (!validaCampo('telefonoAdd')) return false;
	var vClave = document.getElementById('claveAdd');
	var vTelefono = document.getElementById('telefonoAdd');
	if (vClave.value.length == 2) {
		if (vTelefono.value.length != 8){
			alertMsg('Debe proporcionar un teléfono de 8 dígitos');
			return false;
		}
	}else if (vClave.value.length==3) {
		if(vTelefono.value.length!=7) {
			alertMsg('Debe proporcionar un teléfono de 7 dígitos');
			return false;
		}	
	}
	if (!dijit.byId('extensionAdd').isValid()) {
		alertMsg(dijit.byId('extensionAdd').get("invalidMessage"));
		displayErrorMsg(dijit.byId('extensionAdd'), dijit.byId('extensionAdd').get("invalidMessage"));
		document.getElementById('extensionAdd').focus();
		document.getElementById('extensionAdd').blur();
		document.getElementById('extensionAdd').focus();
		return false;
	}
	addTelefono();		
}

function addTelefono() {
	var idtipoTelefonoAdd=dijit.byId('idTipoTelefonoAdd').get('value');
	var accesoAdd=dijit.byId('accesoAdd').get('value');
	var claveAdd=dijit.byId('claveAdd').get('value');
	var telefonoAdd=dijit.byId('telefonoAdd').get('value');
	var extensionAdd=dijit.byId('extensionAdd').get('value');
	document.getElementById('telefonoAdicional').style.display = 'none';
	dojo.xhrPost({url: 'ofertaEmpleo.do?method=telefonoAdicional&idtipoTelefonoAdd='+idtipoTelefonoAdd+'&accesoAdd='+accesoAdd+"&claveAdd="+claveAdd+"&telefonoAdd="+telefonoAdd+"&extensionAdd="+extensionAdd, form:'registroDatosContacto', sync: true,preventCache: true,timeout:180000, 
				  load: function(data) {
					  	dijit.byId('idTipoTelefonoAdd').set('value','');
					  	dijit.byId('accesoAdd').set('value','');
					  	dijit.byId('claveAdd').set('value','');
					  	dijit.byId('telefonoAdd').set('value','');
					  	dijit.byId('extensionAdd').set('value','');
					  	//dojo.byId('agregaTelefono').innerHTML=data;
					  	//dojo.parser.parse("agregaTelefono");
					  	clearWidgetsAndAddHtml('agregaTelefono',data);
				  }});
}
function clearWidgetsAndAddHtml(name, data) {
	clearWidgetsHtml(name);
	var node = removeNodesElement(name);
	if (!checkIE()){
		dojo.byId(name).innerHTML = data;
	} else {
		node.appendChild(adddiv(data));
		addSpanRequired(node);
	}
	dojo.parser.parse(name);
}
function removeNodesElement(id) {
	var myNode = document.getElementById(id);
	while (myNode.firstChild) {
	    myNode.removeChild(myNode.firstChild);
	}
	return myNode;
}
function clearWidgetsHtml(name)	{
	dojo.forEach(dijit.findWidgets(dojo.byId(name)), function(w) {
		w.destroyRecursive();
	});
}
function addSpanRequired(node){
	var spanNode = document.createElement("SPAN");
	var spanText = document.createTextNode("* ");
	spanNode.appendChild(spanText);
	node.appendChild(spanNode);
	return node;
}
function toggle() {
	document.getElementById('telefonoAdicional').style.display = 'block';
	document.getElementById('bAgregarTelefono').style.display = 'none';
	resetRadios('tipoTelefonoAdd');
	//dijit.byId('tipoTelefonoAdd').set('value','${ofertaEmpleoForm.idTipoTelefonoFijo}');
	dijit.byId('accesoAdd').set('value','${ofertaEmpleoForm.accesoFijo}');
}
function resetRadios(name) {
	var elements = document.getElementsByName(name);
	for (var i=0, len=elements.length; i<len; ++i)
        elements[i].checked=false;
}
function eliminarTelefono(acceso,clave,telefono,extension) {
	var accesoEliminar=acceso;
	var claveEliminar=clave;
	var telefonoEliminar=telefono;
	var extensionEliminar=extension;
	document.getElementById('telefonoAdicional').style.display = 'none';
	dojo.xhrPost({url: 'ofertaEmpleo.do?method=eliminarTelefonoAdicional&accesoEliminar='+accesoEliminar+'&claveEliminar='+claveEliminar+'&telefonoEliminar='+telefonoEliminar+'&extensionEliminar='+extensionEliminar, form:'registroDatosContacto', sync: true,preventCache: true, timeout:180000, 
				  load: function(data) {
					 	dijit.byId('idTipoTelefonoAdd').set('value','');
					  	dijit.byId('accesoAdd').set('value','');
					  	dijit.byId('claveAdd').set('value','');
					  	dijit.byId('telefonoAdd').set('value','');
					  	dijit.byId('extensionAdd').set('value','');
					  	//dojo.byId('agregaTelefono').innerHTML=data;
					  	//dojo.parser.parse("agregaTelefono");
					  	clearWidgetsAndAddHtml('agregaTelefono',data);
				  }});
}

function actualizaValorAd(campo,valor){
	
	
	document.getElementById(campo).value=valor;
	
	
}	

dojo.addOnLoad(function() {
	
	//dojo.parser.parse("registroDatosContacto");
	
   var idTipoTelefonoFijo = '${ofertaEmpleoForm.idTipoTelefonoFijo}';
   var idTipoTelefonoCelular = '${ofertaEmpleoForm.idTipoTelefonoCelular}';
   var tipoTelefono = '${ofertaEmpleoForm.tipoTelefono}';
   
   if(tipoTelefono==idTipoTelefonoFijo)dojo.byId('telefonoFijo').checked=true;
   if(tipoTelefono==idTipoTelefonoCelular){
	   dojo.byId('telefonoCelular').checked=true;
	   dijit.byId('extension').value='';
	   dijit.byId('extension').disabled=true;
	   document.getElementById('extension').readOnly=true;
   }
   
   
   var acceso = '${ofertaEmpleoForm.acceso}';
   
   if(acceso==null||acceso==""){
	   
	   dojo.byId('accesoDiv').innerHTML = '${ofertaEmpleoForm.accesoFijo}';
	   document.getElementById('acceso').value = '${ofertaEmpleoForm.accesoFijo}'; 
	   
	   
   }
   
   var vEntidad = '${ofertaEmpleoForm.domicilio.idEntidad}';
   var vMunicipio = '${ofertaEmpleoForm.domicilio.idMunicipio}';
   var vColonia = '${ofertaEmpleoForm.domicilio.idColonia}';
   municipioStore.url = "${context}domicilio.do?method=obtenerMunicipio" + "&" + "idEntidad="+ vEntidad;
	municipioStore.close();	

  	municipioStore.fetch({
        	onComplete: function(items, request) {
        		if (items.length == 0) return;
        		dijit.byId('idMunicipioDomicilio').attr('value', vMunicipio);
        	}
  	});
  	
  	
	
	if (vEntidad && vMunicipio){
		coloniaStore.url = "${context}domicilio.do?method=obtenerColonia" + "&" + "idEntidad="+ vEntidad + "&" + "idMunicipio="+ vMunicipio;
		coloniaStore.close();
		
		coloniaStore.fetch({
        	onComplete: function(items, request) {
        		if (items.length == 0) return;
        		dijit.byId('idColoniaDomicilio').attr('value', vColonia);
        	}
  	});
	}
	
	
	
	   var medioCorreo = '${ofertaEmpleoForm.contactoCorreo}';
	   var medioTelefono = '${ofertaEmpleoForm.contactoTelefono}';
	   var medioDomicilio = '${ofertaEmpleoForm.contactoDomicilio}';
    	if(medioCorreo>1)dojo.byId('medioCorreo').checked=true;
   		if(medioTelefono>1)dojo.byId('medioTelefono').checked=true;
   		if(medioDomicilio>1)dojo.byId('medioDomicilio').checked=true;
});


function displayErrorMsg(textBox, errmsg){
	var originalValidator = textBox.validator;
	textBox.validator = function() {return false;};
	textBox.validate();
	textBox.validator = originalValidator;
	
	dijit.showTooltip(
		    //textBox.get("invalidMessage"),
		    errmsg,
		    textBox.domNode, 
		    textBox.get("tooltipPosition"),
		    !textBox.isLeftToRight()
	);
}


function edicionValidadion(){
	alert('Favor de llenar los campos marcados como obligatorios');
	
}
	function gotoInformacionUbicacion() { 
  		document.registroDatosContacto.fin.value="false";
  		document.registroDatosContacto.informacion.value="true";
  		document.registroDatosContacto.method.value='cancelarEdicionOferta';
		document.registroDatosContacto.submit();
  	}
	function gotoRequisitos() { 
  		document.registroDatosContacto.fin.value="false";
  		document.registroDatosContacto.requisitos.value="true";
  		document.registroDatosContacto.method.value='cancelarEdicionOferta';
		document.registroDatosContacto.submit();
  	}
	function cambiaSeccion(seccion) {
		var input;
		if (seccion==4)
  			input = '<input id="btnSec" class="boton_naranja" type="button" name="btnSec" onClick="gotoInformacionUbicacion();" value="Aceptar"/>';
		if (seccion==5)
  			input = '<input id="btnSec" class="boton_naranja" type="button" name="btnSec" onClick="gotoRequisitos();" value="Aceptar"/>';
		messageInput('Los datos no guardados se perderán ¿Continuar?',input);
	}
	function messageInput(msg,input) {
		var html =
			'<div id="messageDialgop2" name="messageDialgop2">' +
				'<p style="text-align: center;">'+ msg +'</p><br>'+
				'<p class="form_nav">' + input + cerrarMsg() + 
			'</div>';

		dialogMsg = new dijit.Dialog({
	        title: 'Mensaje',
	        content: html,
	        style: "width:300px;",
	        showTitle: false, draggable : false, closable : false,
	        hide: function(){
	        	clearWidgetsHtml('messageDialgop2');
	        	dialogMsg.destroy()
	        }
	    });			
		dialogMsg.show();
	}
function validaExtensionAdd(campo){
	
	
	if (!dijit.byId(campo).isValid()){
		alertMsg(dijit.byId(campo).get("invalidMessage"));
		displayErrorMsg(dijit.byId(campo), dijit.byId(campo).get("invalidMessage"));
		dijit.byId(campo).set('value','');
		dijit.byId(campo).focus();
		return false;
	}
	
}

	function showErrorMsg(errmsg){
  		dojo.byId('errorMsgArea').innerHTML = errmsg;
  		dojo.byId('holderMensaje').style.display='block';
  	}

	function cerrarError(){
  		dojo.byId('holderMensaje').style.display='none';
  	}

	function enviarAMisOfertas() {
		document.registroDatosContacto.action = '<c:url value="/OfertaNavegacion.do?method=init"/>';
		document.registroDatosContacto.submit();
	}		
	
</script>
<noscript>Funciones de JavaScript desactivadas por navegador</noscript>

<c:if test="${not empty ERROR_MSG}">
	<script type="text/javascript">
		dojo.addOnLoad(function(){
			/*var html = '${ERROR_MSG}'+
					   '<br/><br/>'+
					   '<input id="btnCerrar" name="btnCerrar" class="boton_naranja" type="button" value="Cerrar" onclick="cerrarError();"/>';
			showErrorMsg(html);*/
			message('${ERROR_MSG}');
		});
	</script>
</c:if>

</head>
<body>
<div dojoType="dojo.data.ItemFileReadStore" jsId="actividadEconomicaStore"         url="${context}ofertaEmpleo.do?method=actividadEconomica" urlPreventCache="true"></div>
<div dojoType="dojo.data.ItemFileReadStore" jsId="entidadStore"                url="${context}ofertaEmpleo.do?method=entidad" urlPreventCache="true"></div>
<div dojoType="dojo.data.ItemFileReadStore" jsId="municipioStore"            urlPreventCache="true" clearOnClose="true" ></div>
<div dojoType="dojo.data.ItemFileReadStore" jsId="coloniaStore"              urlPreventCache="true" clearOnClose="true" ></div>
<div style="clear: both"></div>
<div class="tab_block">
	<div align="left" style="display:block;" id="returnME">
		<a class="expand" href="javascript:location.replace('<c:url value="/miEspacioEmpresas.do"/>');">
			<strong>Ir al inicio de Mi espacio</strong>
		</a>
	</div>
	<div class="Tab_espacio">
		<h3>Crear oferta de empleo</h3>
		<div class="subTab">
			<c:if test="${ofertaEmpleoForm.idOfertaEmpleo>0}">
				<c:if test="${not empty USUARIO_APP && USUARIO_APP.empresa}">
					<div class="nav_miPerfil">
						<ul>
						    <li><a href="<c:url value="/OfertaNavegacion.do?method=init"/>">Ver mis ofertas de empleo</a></li>
					    	<li><a href="<c:url value="/dondePublicar.do?method=init"/>">Crear oferta de empleo</a></li>	
					    	<li class="curSubTab"><span>Editar oferta de empleo</span></li>
					    </ul>
					</div>
				</c:if>
			
				<div class="nav_misOfertas">
				     <ul>
				         <li class="nav_ubicacion"><a href="javascript:cambiaSeccion(4);">Información y ubicación de la oferta de empleo</a></li>		
				         <li class="nav_requisitos"><a href="javascript:cambiaSeccion(5);">Requisitos de la oferta de empleo</a></li>	
				         <li class="curSubTab"><span style="padding: 18px 7px 15px 15px;">Datos de contacto</span></li>
				     </ul>
			    </div>
			
			
			</c:if>
			<c:if test="${ofertaEmpleoForm.idOfertaEmpleo==0}">
			<c:if test="${not empty USUARIO_APP && USUARIO_APP.empresa}">
				<div class="nav_miPerfil">
					<ul>				    	
				    	<li class="curSubTab">
				    		<span>Crear oferta de empleo</span>
				    	</li>
					    <li><a href="<c:url value="/OfertaNavegacion.do?method=init"/>">Mis ofertas de empleo</a></li>	
					    <li><a href="<c:url value="/RecuperaOfertas.do?method=init"/>">Utiliza oferta como plantilla</a></li>
					    <li><a href="<c:url value="/reporteOfertasEmpresa.do?method=init"/>">Reporte de ofertas de empleo</a></li>
				    </ul>
				</div>
			</c:if>
			</c:if>
			<div class="clearfix"></div>
		</div>
		<div class="clearfix"></div>
	</div>
	<p class="instruc_01"><strong>Crea una oferta de empleo siguiendo tres sencillos pasos</strong></p>
	<div class="form_edge">
		<div class="stepApp">
			<h2><img alt="Paso 3 de 3. Datos de contacto" src="css/images/paso_3c.png"></h2>
		</div>
	</div>
	<p class="labeled">Los datos marcados con <span>*</span> son obligatorios</p>
</div>

<form name="registroDatosContacto" id="registroDatosContacto" action="ofertaEmpleo.do" method="post" dojoType="dijit.form.Form">
	<input type="hidden" name="method" id="method" value="init">
	<input type="hidden" name="fin" id="fin" value="true">
	<input type="hidden" name="informacion" id="informacion" value="false">
	<input type="hidden" name="requisitos" id="requisitos" value="false">
 	<input type="hidden" name="contactoTelefono" 	id="contactoTelefono"   value="1" dojoType="dijit.form.TextBox"/>
  	<input type="hidden" name="contactoCorreo" 	id="contactoCorreo"   value="1" dojoType="dijit.form.TextBox"/>
   	<input type="hidden" name="contactoDomicilio" 	id="contactoDomicilio"   value="1" dojoType="dijit.form.TextBox"/>
   	<input type="hidden" name="acceso" 	id="acceso"   value="${ofertaEmpleoForm.acceso}" dojoType="dijit.form.TextBox"/>
    <input type="hidden" name="accesoAdd" 	id="accesoAdd"   value="${ofertaEmpleoForm.accesoFijo}" dojoType="dijit.form.TextBox"/>
    <input type="hidden" name="idTipoTelefonoAdd" 	id="idTipoTelefonoAdd"   value="${ofertaEmpleoForm.idTipoTelefonoFijo}" dojoType="dijit.form.TextBox"/>
    <!-- input type="hidden" name="nombreEmpresa" 	id="nombreEmpresa"   value="${ofertaEmpleoForm.nombreEmpresa}" dojoType="dijit.form.TextBox"/-->
    <input type="hidden" name="idActividadEconomica" 	id="idActividadEconomica"   value="${ofertaEmpleoForm.idActividadEconomica}" dojoType="dijit.form.TextBox"/>
   
<fieldset class="datos_contacto bloque">
	<legend>Datos de contacto para esta oferta</legend>
	<div class="labeled">Datos generales</div>
	<div class="margin_top_10">
		<div class="grid1_3 fl">
			<div class="labeled margin_no_t"></div>
			<div>${empresaPublica}</div>
		</div>
		<div class="clearfix"></div>
		<div class="grid1_3 fl">
			<div class="labeled"><span>*</span> Actividad ecónomica principal</div>
			<div>${ofertaEmpleoForm.actividadEconomica}</div>
		</div>
		<div class="clearfix"></div>
	</div>
	<div class="margin_top_20 grid1_3">
		
				<label for="nombreEmpresa"><span>*</span> Nombre de la empresa que ofrece la oferta de empleo</label>
				<input type="text" id="nombreEmpresa" name="nombreEmpresa" value="${ofertaEmpleoForm.nombreEmpresa}" size="150" maxlength="150" trim="true"
		        	uppercase="true" dojoType="dijit.form.ValidationTextBox"	required="true" missingMessage="Debe indicar el nombre de la empresa." />
		
		<div class="clearfix"></div>
	</div>
	<div class="margin_top_10">
		<div class="grid1_3 fl">
			<label for="nombreContacto"><span>*</span> Persona de contacto</label>
			<input type="text" id="nombreContacto" name="nombreContacto" value="${ofertaEmpleoForm.nombreContacto}" uppercase="true"
				      size="150" maxlength="150" trim="true"
	                   dojoType="dijit.form.ValidationTextBox"
					   required="true" missingMessage="Debe indicar el nombre del contacto." />
		</div>
		<div class="grid1_3 fl">
			<label for="cargoContacto"><span>*</span> Cargo</label>
			<input type="text" id="cargoContacto" name="cargoContacto" value="${ofertaEmpleoForm.cargoContacto}" uppercase="true"
				      size="150" maxlength="80" trim="true"
	                   dojoType="dijit.form.ValidationTextBox"
					   required="true" missingMessage="Debe indicar el cargo del contacto." />
		</div>
		<div class="clearfix"></div>
	</div>
	<div class="labeled margin_top_10"><span>*</span> Teléfono</div>
	<div><em>Debe ingresar un total de 10 dígitos (Clave LADA + Teléfono).</em></div>
	<div class="grid1_3 fl margin_top_20">
		<div class="margin-r_20 fl">
			<div class="labeled margin_no_t"><span>*</span> Tipo de teléfono</div>
			<div class="tipo_tel margin_top_10 margin-r_10 fl">
				<input type="radio" name="tipoTelefono" id="telefonoFijo" checked="checked" 
			       value="${ofertaEmpleoForm.idTipoTelefonoFijo}" onclick="estableceAcceso()">
			    <label class="fl" for="telefonoFijo">Fijo</label>
			</div>
			<div class="tipo_tel margin_top_10 fl">
				<input type="radio" name="tipoTelefono" id="telefonoCelular"
		    	   value="${ofertaEmpleoForm.idTipoTelefonoCelular}" onclick="estableceAcceso()">
		    	<label class="fl" for="telefonoCelular">Celular</label>
			</div>   
		</div>
		<div class="margin-r_10 fl">
			<label for="accesoDiv" class="fw_no_bold"><span>*</span> Acceso</label>
			<div class="ta_center margin_top_10">
				<span id="accesoDiv">
					${ofertaEmpleoForm.acceso}
				</span>
			</div>
		</div>
		<div class="fl">
			<label for="clave"><span>*</span> Clave LADA</label>
			<input type="text" id="clave" name="clave" value="${ofertaEmpleoForm.clave}"
			       dojoType="dijit.form.ValidationTextBox"
			       maxlength="3" style="width:3em;" trim="true"
			       required="true" missingMessage="Es necesario indicar la clave LADA"
			       regExp="${regexplada}" invalidMessage="La clave LADA es invalida."
			       onBlur="changePhoneSizeRequired();"/>
		</div>
	</div>
	<div class="margin_top_20 fl">
		<label for="telefono"><span>*</span> Teléfono</label>
		<input type="text" name="telefono" id="telefono" value="${ofertaEmpleoForm.telefono}"
                   dojoType="dijit.form.ValidationTextBox"
                   maxlength="8" trim="true"
                   required="true" missingMessage="Es necesario indicar el numero telefonico."
                   regExp="${regexptelefono}" invalidMessage="El numero telefonico es invalido." 
                   onBlur="changePhoneSizeRequired();"/>
	</div>
	<div class="margin_top_20 fl">
		<label for="extension">Extensión</label>
		<input type="text" name="extension" id="extension" value="${ofertaEmpleoForm.extension}"
                   dojoType="dijit.form.ValidationTextBox"
                   maxlength="8" trim="true" 
               	   required="false" missingMessage="Es necesario indicar la extension."
               	   regExp="${regexpextension}" invalidMessage="La extension no es válida."/>
	</div>
	<div id="agregaTelefono" name="agregaTelefono">
		<jsp:include page="/jsp/oferta/telefonosAdicionales.jsp" flush="true" />
	</div>
	<div id="telefonoAdicional" style="display:none">
		<div class="grid1_3  margin_top_20 fl">
			<div class="margin-r_20 fl">
				<div class="labeled margin_no_t"><span>*</span> Tipo de teléfono</div>
				<div class="tipo_tel margin_top_10 margin-r_10 fl">
					<input type="radio" name="tipoTelefonoAdd" id="tipoTelefonoAdd" 
				       value="${ofertaEmpleoForm.idTipoTelefonoFijo}" onclick="estableceAccesoAdd('accesoAdd','idTipoTelefonoAdd',0,'tipoTelefonoAdd',5,'extensionDivAddFijo','extensionDivAddCelular')">
				    <label class="fl" for="telefonoFijoAdd">Fijo</label>
			    </div>
			    <div class="tipo_tel margin_top_10 fl">
				    <input type="radio" name="tipoTelefonoAdd" id="tipoTelefonoAdd"
			    	   value="${ofertaEmpleoForm.idTipoTelefonoCelular}" onclick="estableceAccesoAdd('accesoAdd','idTipoTelefonoAdd',0,'tipoTelefonoAdd',1,'extensionDivAddFijo','extensionDivAddCelular')">
			    	<label class="fl" for="telefonoFijoAdd">Celular</label>
		    	</div>
			</div>
			<div class="margin-r_10 fl">
				<label class="fw_no_bold" for="accesoDivAdd_0"><span>*</span> Acceso</label>
				<div class="ta_center margin_top_10">
					<span id="accesoDivAdd_0">
						${ofertaEmpleoForm.accesoFijo}
					</span>
				</div>
			</div>
			<div class="fl">
				<label for="claveAdd"><span>*</span> Clave LADA</label>
				<input type="text" id="claveAdd" name="claveAdd" 
			       dojoType="dijit.form.ValidationTextBox"
			       maxlength="3" style="width:3em;" trim="true"
			       required="true" missingMessage="Es necesario indicar la clave LADA."
			       regExp="${regexplada}" invalidMessage="La clave LADA es invalida."
			       onBlur="changePhoneSizeRequiredAdd('claveAdd','telefonoAdd');"/>
			</div>
		</div>
		<div class="margin_top_20 fl">
			<label for="telefonoAdd"><span>*</span> Teléfono</label>
			<input type="text" name="telefonoAdd" id="telefonoAdd" value=""
                   dojoType="dijit.form.ValidationTextBox"
                   maxlength="8" style="width:8em;" trim="true"
                   required="true" missingMessage="Es necesario indicar el numero telefonico."
                   regExp="${regexptelefono}" invalidMessage="El numero telefonico es invalido." 
                   onBlur="changePhoneSizeRequiredAdd('claveAdd','telefonoAdd');"/>
		</div>
		<div id="extensionDivAddFijo" style="display: block;">
			<div class="margin_top_20 fl">
				<label for="extensionAdd">Extensión</label>
				<input type="text" name="extensionAdd" id="extensionAdd" value=""
	                   dojoType="dijit.form.ValidationTextBox"
	                   maxlength="8" style="width:8em;" trim="true" 
	               	   required="false" missingMessage="Es necesario indicar la extension."
	               	   regExp="${regexpextension}" invalidMessage="La extension es invalida."/>
            </div>
			<div class="margin_top_50 fl add_ph">
				<input type="button" class="enviar" id="btnAgregarTelefono"  value="Guardar teléfono" onclick="javascript:doSubmitAddTelefono();" title="Guardar telefono"/>
			</div>
		</div>
		<div id="extensionDivAddCelular" style="display: none;">
			<div class="margin_top_20 fl">
				<label for="extensionAddCel">Extensión</label>
				<input type="text" name="extensionAddCel" id="extensionAddCel" value=""
	                   dojoType="dijit.form.ValidationTextBox"
	                   disabled="disabled"
	                   readonly="readonly"
	                   value=""
	                   maxlength="8" style="width:8em;" trim="true" 
	               	   required="false" missingMessage="Es necesario indicar la extension."
	               	   regExp="${regexpextension}" invalidMessage="La extension es invalida."/>
            </div>
            <div class="margin_top_50 fl add_ph">
				<input type="button" class="enviar" id="btnAgregarTelefono"  value="Guardar teléfono" onclick="javascript:doSubmitAddTelefono();" title="Guardar telefono"/>
			</div>
		</div>
		<div class="clearfix"></div>
	</div>
		<div class="grid1_3 margin_top_20">
			 <label for="correoElectronicoContacto"><span>*</span> Correo electrónico</label>
			 <input type="text" id="correoElectronicoContacto" name="correoElectronicoContacto"
                  value="${ofertaEmpleoForm.correoElectronicoContacto}"
                  dojoType="dijit.form.ValidationTextBox"
                  size="65" maxlength="150" trim="false;"
                  oncopy="return false;" oncut="return false" onpaste="return false;"
                  required="true" missingMessage="Es necesario indicar el correo electronico."
                  regExp="${regexpmail}" invalidMessage="Correo electronico invalido, verificar captura."/>
		</div>

</fieldset>


		<div id="holderMensaje" class="holder_mensaje" style="display:none; text-align: center;">
        	<div class="posicion">
	        	<p class="mensaje_error" id="errorMsgArea">
	        		<input id="btnCerrar" name="btnCerrar" class="boton_naranja" type="button" value="Cerrar" onclick="cerrarError();"/>
	        	</p>
			</div>
        </div>

<fieldset class="domicio_oferta bloque">
	<legend>Domicilio de contacto</legend>
	<p class="labeled">Domicilio</p>
	
	<div class="grid1_3 margin_top_10 fl">
		<label for="calle"><span>*</span> Calle</label>
		<input type="text" id="calle" name="calle" value="${ofertaEmpleoForm.domicilio.calle}"
			       dojoType="dijit.form.ValidationTextBox"
			       maxlength="200" size="60" uppercase="true" trim="true"
			       required="true" missingMessage="Debe indicar La calle."
			       regExp="${regexpcalle}" invalidMessage="La calle es invalida, favor de verificar" />
	</div>
	<div class="margin_top_10 fl">
		<div class="fl">
			<label for="numeroExterior"><span>*</span> Número exterior</label>
			<input type="text" id="numeroExterior" name="numeroExterior" maxlength="50" size="4" trim="true"
					   value="${ofertaEmpleoForm.domicilio.numeroExterior}" dojoType="dijit.form.ValidationTextBox"
					   required="true"          missingMessage="Debe indicar el numero exterior."
					   regExp="${regexpnumext}" invalidMessage="Numero exterior invalido."/>
		</div>
		<div class="fl">
			<label for="numeroInterior">Número interior</label>
			<input type="text" id="numeroInterior" name="numeroInterior" maxlength="50" size="4" trim="true" 
					   value="${ofertaEmpleoForm.domicilio.numeroInterior}" dojoType="dijit.form.ValidationTextBox"
					   required="false" 
					   regExp="${regexpnumint}" invalidMessage="Numero interior invalido."/>
		</div>
	</div>
	<div class="clearfix"></div>
	<div class="margin_top_10 ubicacion_1">
		<div class="grid1_3 fl">
			<label for="entreCalle">Entre qué calles</label>
			<input type="text" id="entreCalle" name="entreCalle" value="${ofertaEmpleoForm.domicilio.entreCalle}"
			       dojoType="dijit.form.ValidationTextBox"
			       maxlength="150" uppercase="true" trim="true"
			       regExp="${regexpcalle}" invalidMessage="La calle es invalida, favor de verificar" />
		</div>
		<div class="y">
			<label for="yCalle">y</label>
		</div>
		<div class="grid1_3 margin_top_30 fl">
			<input type="text" id="yCalle" name="yCalle" value="${ofertaEmpleoForm.domicilio.yCalle}"
			       dojoType="dijit.form.ValidationTextBox"
			       maxlength="150" size="60" uppercase="true" trim="true"
			       regExp="${regexpcalle}" invalidMessage="La calle es invalida, favor de verificar" />	
		</div>
		<div class="clearfix"></div>
	</div>
	<div class="margin_top_10">
		<div class="grid1_3 fl">
			<label for="idEntidadDomicilio"><span>*</span> Entidad federativa</label>
			<select id="idEntidadDomicilio" name="idEntidadDomicilio"
					    dojotype="dijit.form.FilteringSelect" store="entidadStore"
						required="true"  missingMessage="Es necesario indicar la entidad federativa."
						tabindex="1" autocomplete="true"
						value="${ofertaEmpleoForm.domicilio.idEntidad}"
						onchange="javascript:actulizaMunicipio();">
			</select>
		</div>
		<div class="grid1_3 fl">
			<label for="idMunicipioDomicilio"><span>*</span> Municipio o Delegación</label>
			<select id="idMunicipioDomicilio" name="idMunicipioDomicilio"
					    dojotype="dijit.form.FilteringSelect" store="municipioStore" 
						required="true" missingMessage="Es necesario indicar un municipio." 
						autocomplete="true"
						value="${ofertaEmpleoForm.domicilio.idMunicipio}"
						onchange="javascript:actulizaColonia();">
			</select>
		</div>
		<div class="grid1_3 fl margin_no_r">
			<label for="idColoniaDomicilio"><span>*</span> Colonia</label>
			<select id="idColoniaDomicilio" name="idColoniaDomicilio"
				        dojotype="dijit.form.FilteringSelect" store="coloniaStore"
				        required="true" missingMessage="Es necesario indicar la colonia."
				        autocomplete="true"
				        value="${ofertaEmpleoForm.domicilio.idColonia}"
				        onchange="javascript:actulizaCodigoPostal();">
			</select>
		</div>
		<div class="clearfix"></div>
	</div>
	<div class="grid1_3 margin_top_10">
		<label for="codigoPostal">Código Postal</label>
		<input type="text" name="codigoPostal" id="codigoPostal"
				   dojoType="dijit.form.ValidationTextBox" readonly="readonly"
			       maxlength="5" style="width:7em;" trim="true"
			       required="false"      missingMessage="Es necesario indicar el codigo postal."
	    		   regExp="${regexpcp}" invalidMessage="Codigo incorrecto, solo se admiten caracteres numericos." 
	    		    value="${ofertaEmpleoForm.domicilio.codigoPostal}" />
	</div>
	<div class="grid1_3">
		<div class="labeled"><span>*</span>Medio de contacto</div>
		<ul>
			<li>
				<input class="fl" type="checkbox" name="medioTelefono" id="medioTelefono"/>
				<label for="medioTelefono">Teléfono</label>
			</li>
			<li>
				<input class="fl" type="checkbox" name="medioCorreo" id="medioCorreo"/>
				<label for="medioCorreo">Correo electrónico</label>
			</li>
			<li>
				<input class="fl" type="checkbox" name="medioDomicilio" id="medioDomicilio"/>
				<label for="medioDomicilio">Domicilio</label>
			</li>
		</ul>
	</div>
	
	
	<c:if test="${ofertaEmpleoForm.empresaSFP == true}">
		<div class="grid1_3">
			<div class="fl">
				<label for="numeroExterior"><span>*</span> Código universal de puesto SFP</label>
				<input type="text" id="codigo_universal_de_puesto_sfp" name="codigo_universal_de_puesto_sfp" maxlength="10" size="10" trim="true"
						   value="" dojoType="dijit.form.ValidationTextBox"
						   required="true"          missingMessage="Debe indicar el codigo universal de puesto sfp."
						   regExp="${regexcodunipuestosfp}" invalidMessage="codigo universal de puesto sfp invalido."/>
			</div>
		</div>
	</c:if>	
</fieldset>
<c:if test="${ofertaEmpleoForm.idOfertaEmpleo>0}">
	<div class="form_nav">
		<input type="button" value="Guardar" id="btnGuardar" class="boton_naranja" onclick="doSubmit('editarOferta',this.id);"/>&nbsp;&nbsp;
		<input type="button" value="Cancelar" id="btnCancelar" class="boton_naranja" onclick="doSubmitCancelar();"/>
	</div>
</c:if>
<c:if test="${ofertaEmpleoForm.idOfertaEmpleo==0}">
	<div class="form_nav">
		<input type="button" value="Guardar" id="btnGuardar" class="boton_azul" onclick="doSubmit('registrarOferta',this.id);"/>
	</div>
</c:if>
</form>

</body>

<c:if test="${ofertaEmpleoForm.idOfertaEmpleo>0}">
<c:if test="${!ofertaEmpleoForm.datosValidosEdicion}">
<script type="text/javascript">
edicionValidadion();
	</script>
	</c:if>
</c:if>

<%
	if (request.getSession().getAttribute("mensajeEdicion")!=null&&request.getSession().getAttribute("mensajeEdicion")!="") {
		String mensaje=String.valueOf(request.getSession().getAttribute("mensajeEdicion"));
%>
		<script type="text/javascript">
			var input = '<input id="btnAct" class="boton_naranja" type="button" name="btnAct" onClick="enviarAMisOfertas();" value="Aceptar"/>';
			messageAcept('<%=mensaje%>', input);
			function messageAcept(msg,input) {
				var html =
					'<div id="messageDialgop2" name="messageDialgop2">' +
						'<p style="text-align: center;">'+ msg +'</p><br>'+
						'<p class="form_nav">' + input + 
					'</div>';
				dialogMsg = new dijit.Dialog({
			        title: 'Mensaje',
			        content: html,
			        style: "width:300px;",
			        showTitle: false, draggable : false, closable : false,
			        hide: function(){
			        	clearWidgetsHtml('messageDialgop2');
			        	dialogMsg.destroy()
			        }
			    });			
				dialogMsg.show();
		}
		</script>
<%
		request.getSession().removeAttribute("mensajeEdicion");
	}
%>
<%
	if (request.getSession().getAttribute("alert4Admin") != null && request.getSession().getAttribute("alert4Admin") != "") {
		String msg = String.valueOf(request.getSession().getAttribute("alert4Admin"));
%>
		<script>message('<%=msg%>');</script>
<%
		request.getSession().removeAttribute("alert4Admin");
	}
%>
</html>

