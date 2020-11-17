<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="mx.gob.stps.portal.core.infra.utils.Constantes" %>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setBundle basename="MessageResources" var="portalbundle"/>

<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="googiespell/AJS.js"></script>
<script type="text/javascript" src="googiespell/googiespell.js"></script>
<script type="text/javascript" src="googiespell/cookiesupport.js"></script>
<script type="text/javascript" src="js/helper/messageHelper.js"></script>
<script type="text/javascript" src="js/idle/idle.js"></script>

<c:if test="${edicionEmpresaForm.permisoGeolocalizacion}">
	<script type="text/javascript" src="https://maps.google.com/maps/api/js?v=3&sensor=false&libraries=places" ></script>
	<script src="<%=request.getContextPath()%>/js/geolocalizacion.js" type="text/javascript"></script>
</c:if>
<link href="googiespell/googiespell.css" rel="stylesheet" type="text/css" />

<c:set var="regexpnumext">^[\w\d\s]{1,50}$</c:set>
<c:set var="regexpnumint">^[\w\d\s]{1,50}</c:set>
<c:set var="regexpcalle">^[\w\d\s\.áéíóúÁÉÍÓÚñÑ]{1,150}$</c:set>
<c:set var="regexpmail"><fmt:message key='app.pattern.mail' bundle='${portalbundle}'/></c:set>
<c:set var="regexpacceso">^(044|01)$</c:set>
<c:set var="regexplada">^[+]?\d{2,3}$</c:set>
<c:set var="regexptelefono">^[+]?\d{7,8}$</c:set>
<c:set var="regexpextension">^[+]?\d{0,6}$</c:set>
<c:set var="regexpcp">^[0-9]{5}</c:set>
<c:set var="regexprfc">^[A-Za-z\s\ñÑ0-9]{0,13}$</c:set>
<c:set var="regexpnombre">^[A-Za-z\s\-.áéíóúÁÉÍÓÚñÑ']{1,50}$</c:set>
<c:set var="regexpapellido1">^[A-Za-z\s\-.áéíóúÁÉÍÓÚñÑ']{2,50}$</c:set>
<c:set var="regexprazonsocial">^[A-Za-z\s\d\-.&,áéíóúÁÉÍÓÚñÑ']{3,150}$</c:set>
<c:set var="regexpnombrecomercial">^[A-Za-z\s\d\-.&,áéíóúÁÉÍÓÚñÑ']{0,150}$</c:set>
<c:set var="regexpnombrecontacto">^[A-Za-z\s\-.áéíóúÁÉÍÓÚñÑ']{1,150}$</c:set>
<c:set var="regexpnumeroEmpleados">^[0-9]{1,5}$</c:set>
<c:set var="regexpwebsite">^[-A-Za-z0-9+&@#/%?=~_|!:,.;]*[-A-Za-z0-9+&@#/%=~_|]$</c:set>
<c:set var="regexppwd">^[a-zA-Z0-9]{8,12}</c:set>

<%
	String context = request.getContextPath() +"/";
	String vProxy = "http://orangoo.com/newnox?lang=";
	// String vProxy = context + "/SpellCheck.do?method=gogiespell&lang=";  
%>

<style type="text/css">

	.suggestionsBox {
		position: absolute;
		left: 30px;
		margin: 0px 0px 0px 0px;
		width: 320px;
		background-color: #FE642E;
		-moz-border-radius: 7px;
		-webkit-border-radius: 7px;
		border: 2px solid #FE642E;	
		color: #fff;
	}
	
	.suggestionList {
		margin: 0px;
		padding: 0px;
	}
	
	.suggestionList li {n
		
		margin: 0px 0px 3px 0px;
		padding: 3px;
		cursor: pointer;
	}
	
	.suggestionList li:hover {
		background-color: #659CD8;
	}
</style>

<script type="text/javascript" lang="es">
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
	dojo.require("dojo.parser");

	dojo.addOnLoad(function() {
		entidadFederativaStore.url = "${context}domicilio.do?method=obtenerEntidad";
	    entidadFederativaStore.close();	
	    
		dijit.byId('idEntidadSelect').disabled=false;
		dijit.byId('idMunicipioSelect').disabled=false;
		dijit.byId('idColoniaSelect').disabled=false;
		cargaCatalogoFederativas();
		fillUpAccessKey();	
		//COLOCAR FOCO EN CONTROL INICIAL
		if (dijit.byId('rfc') && dijit.byId('rfc').value=='')
			dojo.byId('rfc').focus();			
	});

		
	function cargaCatalogoFederativas() {
		var vEntidad = '${edicionEmpresaForm.idEntidad}';
		var vMunicipio = '${edicionEmpresaForm.idMunicipio}';
		entidadFederativaStore.fetch({
    		sync: true,
          		onComplete: function(items, request) {
          		if (items.length == 0) return;
          		dijit.byId('idEntidadSelect').attr('value', '${edicionEmpresaForm.idEntidad}');
    				municipioStore.url = "${context}domicilio.do?method=obtenerMunicipio" + "&" + "idEntidad="+ vEntidad;
    				municipioStore.close();
    				municipioStore.fetch({
    				sync: true,
          				onComplete: function(items, request) {
          					if (items.length == 0) return;
          					dijit.byId('idMunicipioSelect').attr('value', '${edicionEmpresaForm.idMunicipio}');
    						coloniaStore.url = "${context}domicilio.do?method=obtenerColonia" + "&" + "idEntidad="+ vEntidad + "&" + "idMunicipio="+ vMunicipio;
    						coloniaStore.close();
    						coloniaStore.fetch({
    						sync: true,
          						onComplete: function(items, request) {
          							if (items.length == 0) return;                   	
          							dijit.byId('idColoniaSelect').attr('value', '${edicionEmpresaForm.idColonia}');
          							cargarDireccion();
          						}
    						});
          				}
    				});
          		}
    	});
	}
	
	function reenvio(cambioCorreo,data){
		var res = dojo.fromJson(data);
		if (res.msg.type == 'ext') {
			<c:if test="${not empty USUARIO_APP && (USUARIO_APP.administrador || USUARIO_APP.publicador)}">
				location.replace('<c:url value="/filtercompany.do?method=init"/>');	
			</c:if>
			<c:if test="${not empty USUARIO_APP && USUARIO_APP.empresa}">
				location.replace('<c:url value="/miEspacioEmpresas.do"/>');	
			</c:if>
		}	
	}	
	
	function doSubmitEmpresa(method) {
		var param = "";
		dojo.byId('method').value=method;
		dojo.byId('btnActualizar').disabled=true;
		limpiarDescripcion();
		var calle = document.getElementById('calle').value;
		var numeroExterior = document.getElementById('numeroExterior').value;
		var numeroInterior = document.getElementById('numeroInterior').value;
		var entreCalle = document.getElementById('entreCalle').value;
		var yCalle = document.getElementById('yCalle').value;
		var idEntidad = dijit.byId('idEntidadSelect').get('value');
		var idMunicipio = dijit.byId('idMunicipioSelect').get('value');
		var idColonia = dijit.byId('idColoniaSelect').get('value');
		var codigoPostal = dijit.byId('codigoPostal').get('value');
		var password = dijit.byId('password').get('value');
		var idTelefono = dijit.byId('idTelefono').get('value');
		var acceso = dijit.byId('acceso').get('value');
		var clave = dijit.byId('clave').get('value');
		var telefono = dijit.byId('telefono').get('value');
		var extension = dijit.byId('extension').get('value');
		
		var latitud;
		if (dijit.byId('txtLat') != undefined) {
			latitud = dijit.byId('txtLat').get('value');
		}
		
		var longitud;
		if (dijit.byId('txtLat') != undefined) {
			longitud = dijit.byId('txtLng').get('value');	
		}
		
		if (dijit.byId('idTelefonoAdd1') != undefined) {
			var idTelefono1 = dijit.byId('idTelefonoAdd1').get('value');
			var acceso1 = dijit.byId('accesoAdd1').get('value');
			var clave1 = dijit.byId('claveAdd1').get('value');
			var telefono1 = dijit.byId('telefonoAdd1').get('value');
			var extension1 = dijit.byId('extensionAdd1').get('value');
			param += '&accesoAdd1='+acceso1+'&claveAdd1='+clave1+'&telefonoAdd1='+telefono1+'&extensionAdd1='+extension1+'&idTelefonoAdd1='+idTelefono1;
		}
		if (dijit.byId('idTelefonoAdd2') != undefined) {
			var idTelefono2 = dijit.byId('idTelefonoAdd2').get('value');
			var acceso2 = dijit.byId('accesoAdd2').get('value');
			var clave2 = dijit.byId('claveAdd2').get('value');
			var telefono2 = dijit.byId('telefonoAdd2').get('value');
			var extension2 = dijit.byId('extensionAdd2').get('value');
			param += '&accesoAdd2='+acceso2+'&claveAdd2='+clave2+'&telefonoAdd2='+telefono2+'&extensionAdd2='+extension2+'&idTelefonoAdd2='+idTelefono2;
		}
		if (dijit.byId('paginaWeb') != undefined) {
			param += '&paginaWeb='+dijit.byId('paginaWeb').get('value');
		}
   		dojo.xhrGet( {
			url: 'edicionEmpresa.do?method=actualizar&calle='+calle+'&numeroExterior='+numeroExterior+'&numeroInterior='+numeroInterior+'&entreCalle='+entreCalle+'&yCalle='+yCalle+'&idEntidad='+idEntidad+'&idMunicipio='+idMunicipio+'&idColonia='+idColonia+'&codigoPostal='+codigoPostal+'&password='+password+'&accesoAdd0='+acceso+'&claveAdd0='+clave+'&telefonoAdd0='+telefono+'&extensionAdd0='+extension+'&idTelefonoAdd0='+idTelefono+param+'&latitud='+latitud+'&longitud='+longitud,
	  		form:'edicionEmpresaForm',
	  	 	sync: true,
	  		timeout:180000,
	  		load: function(data) {
				var res = dojo.fromJson(data);
				mensaje(res.msg.message);			
				setTimeout("reenvio("+false+",'"+data+"');",1500); 
	    	}
		});		
	}	
	
	function limpiarDescripcion(){ 
		var descripcion = dojo.byId('descripcion').value;
		descripcion = descripcion.replace(/\n/g, "");
		dojo.byId("descripcion").value = descripcion;
    }
	
	function cancelConfirmation() {
		var answer = confirm("Los datos no guardados se perderán ¿Continuar con la Cancelación?");
		if (answer){
			location.replace('<c:url value="/miEspacioEmpresas.do"/>');
		}
	}	

	function confirmPassword() {
		var valido = true;		
		if (!dijit.byId('password').isValid()){
			var textBox = dijit.byId("password");
			displayErrorMsg(textBox, textBox.get("invalidMessage"));				
			return false;
		} else {
			if (!dijit.byId('confirmpasswd').isValid()){
				dijit.byId('confirmpasswd').focus();
				var textBox = dijit.byId("confirmpasswd");
				displayErrorMsg(textBox, textBox.get("invalidMessage"));					
				return false;
			} else {				
				if (!validaIgualdad()){
					return false;
				} else {
					return valido;
				}										
			}					
		}		
	}	
    		
	function validaIgualdad(){
		var validoIgualdad = true;		
		if (dijit.byId('password') && dijit.byId('confirmpasswd')){
			if (dijit.byId('password').get('value') != dijit.byId('confirmpasswd').get('value')){
				validoIgualdad = false;
				dijit.byId('password').focus();
				var errmsg = 'La confirmación de la contraseña no coincide. Favor de verificar el dato proporcionado.';
				var textBox = dijit.byId("confirmpasswd");
				displayErrorMsg(textBox, errmsg);					
			}
		}
		return validoIgualdad;
	}		
	
    function limpiarEspacios(ele){
    	if (!ele || !ele.value) return;
    	
    	var cadena = ele.value;
    	ele.value = cadena.replace("  "," ");
    }
    
    function estableceValores(){
		if(dijit.byId('idEntidadSelect'))
			dojo.byId('idEntidad').value   			 		= dijit.byId('idEntidadSelect').get('value');
		if(dijit.byId('idMunicipioSelect'))
			dojo.byId('idMunicipio').value   		 		= dijit.byId('idMunicipioSelect').get('value');
		if(dijit.byId('idColoniaSelect'))
			dojo.byId('idColonia').value   			 		= dijit.byId('idColoniaSelect').get('value');		
		if(dijit.byId('tiposEmpresaSelect'))
			dojo.byId('idTipoEmpresa').value   	     		= dijit.byId('tiposEmpresaSelect').get('value');		
		if(dijit.byId('tiposActividadEconomicaSelect'))
			dojo.byId('idTipoActividadEconomica').value   	= dijit.byId('tiposActividadEconomicaSelect').get('value');		
    }
    
    function actualizar() {
		estableceValores();
		if (!isValidRegExp(dijit.byId('rfc'), /^[A-Za-z\s\ñÑ0-9]{10,13}$/, "Verifique RFC")) return;
		if (!isValidRegExp(dijit.byId('nombreComercial'), /^[A-Za-z\s\d\-.&,áéíóúÁÉÍÓÚñÑ']{0,150}$/, "Verifique Nombre comercial, no se permiten caracteres especiales.")) return;
		if (!isValidReg(dijit.byId('calle'), /^[\w\d\s\.áéíóúÁÉÍÓÚñÑ]{1,150}$/, "Verifique calle")) return;
		if (!isValidReg(dijit.byId('numeroExterior'), /^[\w\d\s]{1,50}$/, "Verifique Número exterior")) return;
		if (!confirmPassword()) return;
		if (!isValidRegExp(dijit.byId('numeroInterior'), /^[\w\d\s]{1,50}/, "Verifique Número interior")) return;
		if (!isValidRegExp(dijit.byId('entreCalle'), /^[\w\d\s\.áéíóúÁÉÍÓÚñÑ]{1,150}$/, "Verifique Entre qué calles")) return;
		if (!isValidRegExp(dijit.byId('yCalle'), /^[\w\d\s\.áéíóúÁÉÍÓÚñÑ]{1,150}$/, "Verifique y entre qué calles")) return;
		if (!isValidRegExp(dijit.byId('paginaWeb'), /^[-A-Za-z0-9+&@#/%?=~_|!:,.;]*[-A-Za-z0-9+&@#/%=~_|]$/, "Verifique Página web")) return;
		if (!validaCampos()) return;
		if (!validatePhoneList()) return;
		doSubmitEmpresa('actualizar'); 
    }
    
    function isValidRegExp(field, exp, msg) {
    	if (field.get('value').length>0 && !exp.test(field.get('value'))) {
    		dojo.byId(field).focus();
    		message(msg);
    		return false;
    	}
    	return true;
    }
    
    function isValidReg(field, exp, msg) {
    	if (field.get('value').length==0 || (field.get('value').length>0 && !exp.test(field.get('value')))) {
    		dojo.byId(field).focus();
    		message(msg);
    		return false;
    	}
    	return true;
    }
    
    function trimAll(sString){    	
        while (sString.substring(0,1) == ' '){
            sString = sString.substring(1, sString.length);
        }
        while (sString.substring(sString.length-1, sString.length) == ' '){
            sString = sString.substring(0,sString.length-1);
        }
    	return sString;
    }    
    
	function validaCampos(){
		var vDescripcion = document.getElementById('descripcion');
		if (trimAll(vDescripcion.value) === ''){	
			vDescripcion.focus();
			alertMsg('Favor de describir la empresa.');
			return false;				
		} else {
			var controlDescripcion = document.getElementById('descripcion').value;
			if(controlDescripcion.length>2000){
				vDescripcion.focus();
				alertMsg('La descripción de la empresa es demasiado larga, no debe ser mayor a 2000 caracteres.');
				return false;					
			}
		}							
		if (!dijit.byId('edicionEmpresaForm').isValid()){
			if (!validaCampo('contactoEmpresa')) {
				return false;
			} else {
				var vContactoEmp = dijit.byId('contactoEmpresa');
				if(!validarNombreContacto(vContactoEmp)) return false;	
			}
			var isAdmor = eval('${edicionEmpresaForm.idTipoUsuario eq 1 ? true : false}');
			var noTipoEmpresa = eval('${edicionEmpresaForm.idTipoEmpresa eq -1 ? true : false}');
			var noTipoActividad = eval('${edicionEmpresaForm.idTipoActividadEconomica eq -1 ? true : false}');			
			if(isAdmor || noTipoEmpresa || noTipoActividad){
				if (!validaCampoSelect('tiposEmpresaSelect')) return false;
				if (!validaCampoSelect('tiposActividadEconomicaSelect')) return false;		        					
			}			
			if (!validaCampo('numeroEmpleados')) return false;			
			var controlEmpleados = dijit.byId('numeroEmpleados');					
			if(parseInt(controlEmpleados.value)<1){
				dojo.byId('numeroEmpleados').focus();
				alertMsg('El número de empleados debe ser mayor a cero.');
				return false;				
			}			
			if (!validaCampo('calle') || !isValidRegExp(dijit.byId('calle'), /^[\w\d\s\.áéíóúÁÉÍÓÚñÑ]{1,150}$/, "Verifique Número interior")) return false;
			if (!validaCampo('numeroExterior')) return false;
			if (!validaCampoSelect('idEntidadSelect')) return false;
			if (!validaCampoSelect('idMunicipioSelect')) return false;
			if (!validaCampoSelect('idColoniaSelect')) return false;
			if (confirmPassword()==false){	
				return false;
			}			
			if (!hasTelPrinc()){
				if(document.getElementById('clave') && document.getElementById('clave').value!=''){
					if (!checkWithoutMessage('clave')){
						alertMsg('La clave del teléfono es inválida.');
						document.getElementById('clave').focus();
					} else {
						if (!checkWithoutMessage('telefono')){
							alertMsg('El número telefónico es inválido.');
							document.getElementById('telefono').focus();
						} else {
							var vExtension = dijit.byId('extension');
							if(vExtension.value.length>0){
								validarExtensionPrincipal(vExtension);
							}
						}	
					}						
					return false;
				} else {
					alertMsg('Debe capturar al menos un teléfono principal con clave lada y número telefónico.');
					document.getElementById('clave').focus();
					return false;					
				}				
			} else {
				var tipoTelefonoId = getRadioValue('idTipoTelefono');		
				if(tipoTelefonoId=='${edicionEmpresaForm.idTipoTelefonoFijo}'){		
					return changePhoneSizeRequired();				
				} else {
					dojo.byId('idTipoTelefono').focus();
					alertMsg("El teléfono principal debe ser un télefono fijo. ");
					return false;
				}				
			}	
			
		} else {
			if (!hasTelPrinc()){
				if(document.getElementById('clave') && document.getElementById('clave').value!=''){
					if (!checkWithoutMessage('clave')){
						alertMsg('La clave del teléfono es inválida.');
						document.getElementById('clave').focus();
					} else {
						if (!checkWithoutMessage('telefono')){
							alertMsg('El número telefónico es inválido.');
							document.getElementById('telefono').focus();
						} else {
							var vExtension = dijit.byId('extension');
							if(vExtension.value.length>0){
								validarExtensionPrincipal(vExtension);
							}
						}	
					}						
					return false;					
				} else {
					alertMsg('Debe capturar al menos un teléfono principal con clave lada y número telefónico.');
					return false;					
				}				
			} else {
				var tipoTelefonoId = getRadioValue('idTipoTelefono');
				
				if(tipoTelefonoId=='${edicionEmpresaForm.idTipoTelefonoFijo}' ){	
					return changePhoneSizeRequired();						
				} else {
					dojo.byId('idTipoTelefono').focus();
					alertMsg("El telefono principal debe ser un telefono fijo. ");
					return false;
				}											
			} 				
		}						
		return true;
	}	
	
	function validarNombreContacto(formatoContacto){
		var regExpContacto = /^[A-Za-z\s\áéíóúÁÉÍÓÚñÑ]{1,150}$/;
		if (!regExpContacto.test(formatoContacto.value)) {
			alertMsg("El nombre del contacto es inválido porque no contiene caracteres alfabéticos");	
			formatoContacto.focus();
			return false;
		}
		return true;		
	}   	
	
	function validarExtensionPrincipal(formatoExtension){
		var regExp = /^[+]?\d{0,8}$/;
		if (!regExp.test(formatoExtension.value)) {
			alertMsg("La extensión del teléfono es inválida.");	
			formatoExtension.focus();
			return false;
		}
		return true;		
	}	
	
	function validaCampo(campo){
		var control = dijit.byId(campo);
		
		if (control && control.value==''){
			dojo.byId(control).focus();
			alertMsg("Es necesario indicar el (la) " + 
					control.get("missingMessage"));
			return false;
		}
		
		if (!dijit.byId(campo).isValid()){
			dojo.byId(control).focus(); 
			alertMsg("Es necesario revisar el (la) " + 
					control.get("invalidMessage"));
			return false;
		}
	
		return true;
	}
	
	function validaCampoSelect(campo){
		var control = dijit.byId(campo);
		
		if (control && control.get('value')==''){
			dojo.byId(control).focus();
			alertMsg("Es necesario indicar el (la) " + 
					control.get("missingMessage"));
			return false;
		}

		return true;
	}
	
	function alertMsg(msg){
		message(msg);
	}
	
	function displayErrorMsg(textBox, errmsg){
		var originalValidator = textBox.validator;
		textBox.validator = function() {return false;};
		textBox.validate();
		textBox.validator = originalValidator;
		
		dijit.showTooltip(
			    errmsg,
			    textBox.domNode, 
			    textBox.get("tooltipPosition"),
			    !textBox.isLeftToRight()
		);
	}	
	
	function changeAdditPhoneSizeRequired(indice){
		var vLblClave = 'claveAdd' + indice;
		var vLblTelefono = 'telefonoAdd' + indice;
		if(document.getElementById(vLblClave) && document.getElementById(vLblTelefono)){
			var vClave = document.getElementById(vLblClave);
			var vTelefono = document.getElementById(vLblTelefono);
			if(vClave.value.length < 2){
				vClave.focus();
				mensaje('Debe proporcionar una clave LADA de 2 ó 3 caracteres');
				return false;
			} else if(vClave.value.length == 2) {
				if(vTelefono.value.length != 8){
					vClave.focus();
					mensaje('Debe proporcionar un teléfono de 8 dígitos');
					return false;
				}
			} else if(vClave.value.length==3){
				if(vTelefono.value.length!=7){
					vClave.focus();
					mensaje('Debe proporcionar un teléfono de 7 dígitos');
					return false;
				}	
			}					
		}
		return true;
	}
	
	function changePhoneSizeRequired(){
		var vClave = document.getElementById('clave');
		var vTelefono = document.getElementById('telefono');
		 
		if(vClave.value.length < 2){
			vClave.focus();
			mensaje('Debe proporcionar una clave LADA de 2 ó 3 caracteres');
			return false;
		} else if(vClave.value.length == 2) {
			if(vTelefono.value.length != 8){
				vClave.focus();
				mensaje('Debe proporcionar un teléfono de 8 dígitos');
				return false;
			}
		} else if(vClave.value.length==3){
			if(vTelefono.value.length!=7){
				vClave.focus();
				mensaje('Debe proporcionar un teléfono de 7 dígitos');
				return false;
			}	
		}
		return true;
	}
	
	function mensaje(mensaje) {	
		message(mensaje);
	}		

	function checkEntidadMuniColonia(checkType){
		var vEntidad = dijit.byId('idEntidadSelect').get('value');
		var vMunicipio = dijit.byId('idMunicipioSelect').get('value');
		var vColonia   = dijit.byId('idColoniaSelect').get('value');
		if (checkType==1 && !vEntidad){
			dojo.byId('codigoPostal').value = '';	
			dijit.byId('idEntidadSelect').set('value','');
			dijit.byId('idMunicipioSelect').set('value','');
			dijit.byId('idColoniaSelect').set('value','');			
			municipioStore.close();	
			coloniaStore.close();	
			dijit.byId('idEntidadSelect').focus();
			alertMsg("Por favor seleccione alguna de las opciones del listado de entidades.");			
		} else if (checkType==2 && !vMunicipio){
			dojo.byId('codigoPostal').value = '';	
			dijit.byId('idMunicipioSelect').set('value','');
			dijit.byId('idColoniaSelect').set('value','');			
			municipioStore.close();	
			coloniaStore.close();	
			dijit.byId('idMunicipioSelect').focus();
			alertMsg("Por favor seleccione alguna de las opciones del listado de municipios o delegaciones.");						
		} else if (checkType==3 && !vColonia){
			dojo.byId('codigoPostal').value = '';	
			dijit.byId('idColoniaSelect').set('value','');			
			coloniaStore.close();	
			dijit.byId('idColoniaSelect').focus();
			alertMsg("Por favor seleccione alguna de las opciones del listado de colonias.");				
		}
	}	
	
	function actualizaMunicipio(){
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
	
	function actualizaColonia(){
		var vEntidad   = dijit.byId('idEntidadSelect').get('value');
		var vMunicipio = dijit.byId('idMunicipioSelect').get('value');
		
		if (vEntidad && vMunicipio){
			dojo.byId('codigoPostal').value = '';
			coloniaStore.close();						
			coloniaStore.url = "${context}domicilio.do?method=obtenerColonia" + "&" + "idEntidad="+ vEntidad + "&" + "idMunicipio="+ vMunicipio;
			coloniaStore.close();
			dijit.byId('idColoniaSelect').set('value','');
		}
	}	
	
	function actualizaCodigoPostal(){		
		var vEntidad   = dijit.byId('idEntidadSelect').get('value');
		var vMunicipio = dijit.byId('idMunicipioSelect').get('value');
		var vColonia   = dijit.byId('idColoniaSelect').get('value');
		
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

  	function showErrorMsg(errmsg){
  		dojo.byId('errorMsgArea').innerHTML = errmsg;
  		dojo.byId('holderMensaje').style.display='block';
  	}

  	function cerrarError(){
  		dojo.byId('holderMensaje').style.display='none';
  	}

  	function validaCorreoElectronicoUnico(){
		var unico = true;		
		if (dijit.byId('correoElectronico').value==''){
			alertMsg(control.get("invalidMessage"));
			return false;
		}		
		dojo.byId('method').value='validaCorreoElectronicoUnico';
		dojo.xhrPost({url: 'edicionEmpresa.do', form:'edicionEmpresaForm', sync: true, 
			  		  load: function(data){
			  					var res = dojo.fromJson(data);
			  					if('exito' == res.type){
			  						if ('unico'== res.message){
			  							unico = true;	
			  						} else {
			  							unico = false;
			  						}
			  					} else if('error' == res.type){
			  						unico = false;
			  					}
			 				}
					});
		return unico;
	}  	
  	
	function getAnyElementValueById(elementId){
		var vElement = dijit.byId(elementId).value;
		if(vElement==undefined || vElement==''){
			vElement = document.getElementById(elementId).value;
		}
		return vElement;
	}  	
	
	function hasTelPrinc() {	
		var radioChecked = getRadioValue('idTipoTelefono');
		if (radioChecked!=='${edicionEmpresaForm.idTipoTelefonoFijo}' 
			||	!checkWithoutMessage('clave') ||
					!checkWithoutMessage('telefono')){
			return false;			
		} else {
			var regExp = /^[+]?\d{0,8}$/;
			var vExtension = dijit.byId('extension');
			if(vExtension.value.length>0 && !regExp.test(vExtension.value)){
				return false;
			}	
		}
		return true;
	}  	
	
	function checkWithoutMessage(campo){		
		var control = dijit.byId(campo);
		if (control && control.value==''){
				return false;
		} else if (!dijit.byId(campo).isValid()){
			return false;
		}	
		return true;
	}

	function fillUpAccessKey(){
		var tipoTelefonoId = getRadioValue('idTipoTelefono');
		var accesoDes;
	    if(tipoTelefonoId == 1)
	    	accesoDes = <%="'" + Constantes.CLAVE_TELEFONO_CELULAR + "'"%>;
	    else
	    	accesoDes = <%="'" + Constantes.CLAVE_TELEFONO_FIJO + "'"%>;   		
	    var wAcceso = dijit.byId('acceso');
	    
	    wAcceso.attr('value',accesoDes);
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

	function caracteresValidos(e){
    	var tecla_codigo = (document.all) ? e.keyCode : e.which;
    	if(tecla_codigo==8 || tecla_codigo==0) return true;
    	if (tecla_codigo==225 || tecla_codigo==233 || tecla_codigo==237 || tecla_codigo==243 || tecla_codigo==250) return true; //vocales minusculas con acento
    	if (tecla_codigo==193 || tecla_codigo==201 || tecla_codigo==205 || tecla_codigo==211 || tecla_codigo==218) return true; //vocales mayuzculas con acento
    	if (tecla_codigo==209 || tecla_codigo==241 ) return true; //Ñ y ñ   		
    	var patron =/[A-Za-z\s\-.áéíóúÁÉÍÓÚñÑ0-9.,;:]/;
    	tecla_valor = String.fromCharCode(tecla_codigo);
    	return patron.test(tecla_valor);
    }		
	
	function cargarDireccion(){
		<c:if test="${edicionEmpresaForm.permisoGeolocalizacion}">
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
  		<c:if test="${edicionEmpresaForm.permisoGeolocalizacion}">
	  		var address =dijit.byId('calle').attr('value')+' '+ dijit.byId('numeroExterior').attr('value')+', '+
			dijit.byId('idColoniaSelect').attr('displayedValue')+', '+
			dijit.byId('idEntidadSelect').attr('displayedValue')+', '+
			dijit.byId('idMunicipioSelect').attr('displayedValue');
	  		dijit.byId('txtGeoreferencia').attr('value', address);
	  		geocodeAddress(map, address);
	  	</c:if>
  	}
</script>
<c:if test="${edicionEmpresaForm.permisoGeolocalizacion}">
	<script type="text/javascript">
		function fillLatLng(position){
			dijit.byId('txtLat').attr('value', position.lat());
			dijit.byId('txtLng').attr('value', position.lng());
		}
	</script>
</c:if>
<noscript>Funciones de javascript desactivadas por el navegador</noscript>
<style>
	@import "css/geolocalizacion.css";
</style>


<c:if test="${not empty ERROR_MSG}">
	<script type="text/javascript">
		dojo.addOnLoad(function(){
			var html = '${ERROR_MSG}'+
					   '<br/><br/>'+
					   '<input id="btnCerrar" name="btnCerrar" class="boton_naranja" type="button" value="Cerrar" onclick="cerrarError();"/>';
			showErrorMsg(html);
			//alertMsg('${ERROR_MSG}');
		});
	</script>
</c:if>


<div dojoType="dojo.data.ItemFileReadStore" jsId="entidadFederativaStore"    	urlPreventCache="true" clearOnClose="true" ></div>
<div dojoType="dojo.data.ItemFileReadStore" jsId="municipioStore"            	urlPreventCache="true" clearOnClose="true" ></div>
<div dojoType="dojo.data.ItemFileReadStore" jsId="coloniaStore"              	urlPreventCache="true" clearOnClose="true" ></div>
<div dojoType="dojo.data.ItemFileReadStore" jsId="tiposEmpresaStore"         	url="${context}edicionEmpresa.do?method=tiposEmpresa"></div>
<div dojoType="dojo.data.ItemFileReadStore" jsId="tiposActividadEconomicaStore" url="${context}edicionEmpresa.do?method=tiposActividadEconomica"></div>

<form name="edicionEmpresaForm" id="edicionEmpresaForm" method="post" action="edicionEmpresa.do" dojoType="dijit.form.Form">
	<input type="hidden" name="method" 						id="method" 					value="init" dojoType="dijit.form.TextBox"/> <!-- indica el metodo a invocar -->	
	<input type="hidden" name="idTipoEmpresa"   			id="idTipoEmpresa"   			value="${edicionEmpresaForm.idTipoEmpresa}" dojoType="dijit.form.TextBox"/>
	<input type="hidden" name="idTipoActividadEconomica"   	id="idTipoActividadEconomica"   value="${edicionEmpresaForm.idTipoActividadEconomica}" dojoType="dijit.form.TextBox"/>
	<input type="hidden" name="idEntidad"   	id="idEntidad"		value="${edicionEmpresaForm.idEntidad}" 	dojoType="dijit.form.TextBox"/>
	<input type="hidden" name="idMunicipio"		id="idMunicipio"   	value="${edicionEmpresaForm.idMunicipio}" 	dojoType="dijit.form.TextBox"/>
	<input type="hidden" name="idColonia"   	id="idColonia" 		value="${edicionEmpresaForm.idColonia}" 	dojoType="dijit.form.TextBox"/>
	<input type="hidden" name="idTelefono" 		id="idTelefono" 	value="${edicionEmpresaForm.idTelefono}" 	dojoType="dijit.form.TextBox"/>	
	<input type="hidden" name="idTipoPersona"   			id="idTipoPersona"	value="${edicionEmpresaForm.idTipoPersona}" dojoType="dijit.form.TextBox"/>
	<input type="hidden" name="nombre"   					id="nombre"   		value="${edicionEmpresaForm.nombre}" 		dojoType="dijit.form.TextBox"/>
	<input type="hidden" name="apellido1"   				id="apellido1"   	value="${edicionEmpresaForm.apellido1}" 	dojoType="dijit.form.TextBox"/>
	<input type="hidden" name="apellido2"   				id="apellido2"   	value="${edicionEmpresaForm.apellido2}" 	dojoType="dijit.form.TextBox"/>
	<input type="hidden" name="razonSocial"   				id="razonSocial"   	value="${edicionEmpresaForm.razonSocial}" 	dojoType="dijit.form.TextBox"/>
	
	<div class="tab_block">
		<div align="left" style="display:block;" id="returnME">
			<c:if test="${not empty USUARIO_APP && (USUARIO_APP.administrador || USUARIO_APP.publicador)}">
				<a class="expand" href="javascript:location.replace('<c:url value="/filtercompany.do?method=init"/>');">
			</c:if>
			<c:if test="${not empty USUARIO_APP && (USUARIO_APP.empresa)}">
				<a class="expand" href="javascript:location.replace('<c:url value="/miEspacioEmpresas.do"/>');">
			</c:if>
			<strong>Ir al inicio de Mi espacio</strong></a>
		</div>
		<div class="Tab_espacio">
			<h3>Datos de la empresa</h3>
			<div class="subTab">
				<ul>
					<li class="curSubTab"><span>Editar mis datos</span></li>
					<li><a href="<c:url value="/uploadcompanylogo.do?method=init"/>">Cambiar logotipo</a></li>
				</ul>
				<div class="clearfix"></div>
			</div>
			<div class="clearfix"></div>
		</div>
		<p class="labeled">Los datos marcados con <span>*</span> son obligatorios</p>
	</div>
	<fieldset class="generalesOferta">
		<legend>Mis datos</legend>
	<div class="grid1_3 margin_top_10">
		<label for="rfc">RFC</label>        
	    <input type="text" name="rfc" id="rfc"
	           required="false" missingMessage="RFC"
	           regExp="${regexprfc}" invalidMessage="RFC inválido favor de verificar."
	           value="${edicionEmpresaForm.rfc}"
	           maxlength="13" dojoType="dijit.form.ValidationTextBox"/>
	</div>       
	<div class="grid1_3 margin_top_10">
		<label for="nombreComercial">Nombre comercial</label>		
	        <input id="nombreComercial" name="nombreComercial" maxlength="150" size="50" 			
	        	dojoType="dijit.form.ValidationTextBox" required="false"  
	        	regExp="${regexpnombrecomercial}" invalidMessage="Nombre comercial inválido, no se permiten caracteres especiales." 
	        	onpaste="return false;" uppercase="true"   		        	
	        	value="${edicionEmpresaForm.nombreComercial}"
	        	missingMessage="Dato requerido" trim="true"/>      	
	 </div>   
		
		<div class="campoTexto margin_top_20">
			<label for="descripcion">Descripción de la empresa</label>						
			    <textarea name="descripcion" id="descripcion" maxlength="2000" onkeypress="return caracteresValidos(event);" onpaste="return false;"
        	    regExp="^[A-Za-z\s\-.áéíóúÁÉÍÓÚñÑ0-9.,;:/']{1,2000}$" style="width: 90%;" cols="70" rows="4" trim="true" required="true" class="textGoogie">${edicionEmpresaForm.descripcion}</textarea>										        	    
        	    <script type="text/javascript">
         		 var vSpellCon = new GoogieSpell("googiespell/", '<%=vProxy%>');
         		 vSpellCon.setLanguages({'es': 'Español'});
         		 vSpellCon.hideLangWindow();
         		 vSpellCon.decorateTextarea("descripcion");
			    </script>								
	    </div>	
		<div class="grid1_3 margin_top_10">    
		<label for="contactoEmpresa"><span>*</span> Nombre del representante de la empresa</label>
		<input type="text" id="contactoEmpresa" name="contactoEmpresa" 
	              dojoType="dijit.form.ValidationTextBox" 
	              required="true" missingMessage="nombre del representante de la empresa."
	              regExp="${regexpnombrecontacto}" invalidMessage="Nombre inválido, no se permiten números ni caracteres especiales." 
	              maxlength="50" uppercase="true" trim="true" 
	              value="${edicionEmpresaForm.contactoEmpresa}"
	              onkeyup="limpiarEspacios(this)"/>
		</div>		
        <c:choose>
        	<c:when test="${edicionEmpresaForm.idTipoUsuario ==1 || edicionEmpresaForm.idTipoEmpresa==-1 || edicionEmpresaForm.idTipoActividadEconomica==-1 }">
					<div class="grid1_3 margin_top_10">
						<label for="tiposEmpresaSelect"><span>*</span> Tipo de empresa</label>
						<select id="tiposEmpresaSelect" name="tiposEmpresaSelect"
								required="true" missingMessage="tipo de empresa" 
						        store="tiposEmpresaStore" dojotype="dijit.form.FilteringSelect"
						        value="${edicionEmpresaForm.idTipoEmpresa}">
						</select>
					</div>	

					<div class="grid1_3 margin_top_10">
						<label for="tiposActividadEconomicaSelect"><span>*</span> Actividad económica principal</label>
						<select id="tiposActividadEconomicaSelect" name="tiposActividadEconomicaSelect"
								required="true"  missingMessage="actividad económica principal." maxHeight="250"
						        store="tiposActividadEconomicaStore" dojotype="dijit.form.FilteringSelect"
						        value="${edicionEmpresaForm.idTipoActividadEconomica}">
						</select>
					</div>					 
        	</c:when>
        	<c:otherwise>
				<p><strong>Tipo de empresa</strong><br />
					${edicionEmpresaForm.tipoEmpresa}
				</p>	
				<!-- p><strong>Actividad económica principal</strong></p -->
				<p><strong>Sector</strong><br />
					${edicionEmpresaForm.sector}
				</p>
				<p><strong>Subsector</strong><br />
					${edicionEmpresaForm.subsector}
				</p>				
				<p><strong>Rama</strong><br />
					${edicionEmpresaForm.actividadEconomica}
				</p>								
        	</c:otherwise>
        </c:choose>       				
		<div class="grid1_3 margin_top_10">
			<label for="numeroEmpleados"><span>*</span> Número de empleados</label>
	           <input type="text" name="numeroEmpleados" id="numeroEmpleados" value="${edicionEmpresaForm.numeroEmpleados}"
	                  dojoType="dijit.form.ValidationTextBox"
	                  maxlength="5" style="width:8em;" trim="true" 
	                  required="true" missingMessage="número de empleados"
	                  regExp="${regexpnumeroEmpleados}" invalidMessage="El número de empleados es inválido." />
		</div>	
		<%-- 		
		<p class="c_naranja">
			Deseo que mis datos personales permanezcan confidenciales
			<input type="checkbox" name="confidencial" id="confidencial" value="1">
		</p>	
		--%>
		<div class="grid1_3 margin_top_10">			
			<div class="labeled"><span>*</span> ¿Cómo se enteró del portal del empleo?</div>
			${edicionEmpresaForm.medioEnterado}
		</div> 										  			
	</div>
	</fieldset>
	<fieldset class="domicilio">
		<legend>Domicilio de la empresa</legend>
		<div class="grid1_3 margin_top_10 fl">	
			<label for="calle"><span>*</span> Calle</label>	
			<input type="text" id="calle" name="calle" value="${edicionEmpresaForm.calle}"
			       dojoType="dijit.form.ValidationTextBox"
			       maxlength="150" size="60" uppercase="true" trim="true"
			       required="true" missingMessage="calle." 
			       regExp="${regexpcalle}" invalidMessage="La calle es inválida, favor de verificar" />
		</div>
		<div class="margin_top_10 fl">
			<div class="fl">	
				<label for="numeroExterior"><span>*</span> Número exterior</label>	
				<input type="text" id="numeroExterior" name="numeroExterior" maxlength="50" size="4" trim="true"
					   value="${edicionEmpresaForm.numeroExterior}" dojoType="dijit.form.ValidationTextBox"
					   required="true"          missingMessage="número exterior." 
					   regExp="${regexpnumext}" invalidMessage="Número exterior inválido."/>
			</div>
			<div class="fl">	
				<label for="numeroInterior">Número interior</label>
				<input type="text" id="numeroInterior" name="numeroInterior" maxlength="50" size="4" trim="true" 
					   value="${edicionEmpresaForm.numeroInterior}" dojoType="dijit.form.ValidationTextBox"
					   required="false" 
					   regExp="${regexpnumint}" invalidMessage="Número interior inválido."/>
			</div>
		</div>
		<div class="clearfix"></div>
		<div class="margin_top_10 ubicacion_1">
			<div class="grid1_3 fl">
				<label for="entreCalle">Entre qué calles</label>
				<input type="text" id="entreCalle" name="entreCalle" value="${edicionEmpresaForm.entreCalle}"
				       dojoType="dijit.form.ValidationTextBox"
				       maxlength="150" size="60" uppercase="true" trim="true"
				       regExp="${regexpcalle}" invalidMessage="La calle es inválida, favor de verificar" /> 
			</div>
			<div class="y">
				<label for="yCalle">y</label>
			</div>
			<div class="grid1_3 margin_top_30 fl">
				<input type="text" id="yCalle" name="yCalle" value="${edicionEmpresaForm.yCalle}"
				       dojoType="dijit.form.ValidationTextBox"
				       maxlength="150" size="60" uppercase="true" trim="true"
				       regExp="${regexpcalle}" invalidMessage="La calle es inválida, favor de verificar" />
			</div>
			<div class="clearfix"></div>
		</div>
		<div class="grid1_3 margin_top_10 fl">
			<label for="idEntidadSelect"><span>*</span> Entidad federativa</label>
			<select id="idEntidadSelect" name="idEntidadSelect"
				    dojotype="dijit.form.FilteringSelect" store="entidadFederativaStore" maxHeight="250"
					required="true"  missingMessage="entidad federativa." scrollOnFocus="true" 
					value="${edicionEmpresaForm.idEntidad}" tabindex="0" autocomplete="true"
					onchange="javascript:actualizaMunicipio();" onblur="javascript:checkEntidadMuniColonia(1);">
			</select>
		</div>
		<div class="grid1_3 margin_top_10 fl">
			<label for="idMunicipioSelect">Municipio o Delegación</label>
			<select id="idMunicipioSelect" name="idMunicipioSelect"
				    dojotype="dijit.form.FilteringSelect" store="municipioStore"  maxHeight="250"
					required="true" missingMessage="municipio o delegación." scrollOnFocus="true" 
					value="${edicionEmpresaForm.idMunicipio}"  autocomplete="true"		
					onchange="javascript:actualizaColonia();" onblur="javascript:checkEntidadMuniColonia(2);">
			</select>
		</div>
		<div class="grid1_3 margin_top_10 fl margin_no_r">
			<label for="idColoniaSelect"><span>*</span> Colonia</label>
			<select id="idColoniaSelect" name="idColoniaSelect"
			        dojotype="dijit.form.FilteringSelect" store="coloniaStore"  maxHeight="250"
			        required="true" missingMessage="colonia." scrollOnFocus="true" 
			        value="${edicionEmpresaForm.idColonia}"  autocomplete="true"
			        onchange="javascript:actualizaCodigoPostal();" onblur="javascript:checkEntidadMuniColonia(3);">
			</select>		
		</div>
		<div class="clearfix"></div>
		<div class="grid1_3 margin_top_10 fl">
			<label for="codigoPostal">Código postal</label>
			<input type="text" name="codigoPostal" id="codigoPostal"
				   dojoType="dijit.form.ValidationTextBox" readonly="readonly"
			       maxlength="5" style="width:7em;" trim="true"
			       value="${edicionEmpresaForm.codigoPostal}"
			       required="false"      missingMessage="código postal."
	    		   regExp="${regexpcp}" invalidMessage="Código postal incorrecto, solo se admiten caracteres numéricos." />
		</div>	
	</fieldset>
	<c:if test="${edicionEmpresaForm.permisoGeolocalizacion}">
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
                	<input id="txtGeoLatLng" type="hidden" value="{&quot;lat&quot;:${edicionEmpresaForm.latitud}, &quot;lng&quot;:${edicionEmpresaForm.longitud}}" dojoType="dijit.form.TextBox"/>
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
    <fieldset class="datosCuenta">
    	<legend>Correo electrónico y Contraseña</legend>
    	<p class="user_alert">Recuerda que si modificas tu contraseña, cambiarán tus datos para ingresar a tu cuenta.</p>
    	<%-- 	DE MOMENTO OCULTAR CAMBIO DE CORREO	
		<p class="user_alert">Recuerda que si modificas tu correo electrónico, cambiará tu usuario</p>
		<p class="un_cuarto fl"><span class="c_naranja">*</span><strong>Correo electrónico</strong><br />
	          <input type="text" id="correoElectronico" name="correoElectronico"
	                 value="${edicionEmpresaForm.correoElectronico}"
	                 dojoType="dijit.form.ValidationTextBox"
	                 size="65" maxlength="65" trim="false;"
	                 oncopy="return false;" oncut="return false" onpaste="return false;"
	                 regExp="${regexpmail}" invalidMessage="Correo electrónico inválido, favor de verificar."/>
		</p>
		<div style="clear:both"></div>
		--%>
		<div class="grid1_3 margin_top_10">
			<div class="labeled"><span>*</span> Usuario</div>
			<span class="usuario">&nbsp;${edicionEmpresaForm.usuario}</span>
		</div>
		<div class="grid1_3 margin_top_10">
        	<label for="password"><span>*</span> Contraseña</label>
        	<input type="password" dojoType="dijit.form.ValidationTextBox" name="password" id="password" maxlength="12" 
        	intermediateChanges="false" regExp="${regexppwd}" trim="true"
        	invalidMessage="Contraseña inválida, verificar captura, debe contar con 8 a 12 caracteres y no se permiten caracteres como Ñ ñ & ! @ # % ni vocales acentuadas, ni espacios en blanco."
        	constraints="{min:8,max:12}" rangeMessage="La contraseña debe contar con 8 a 12 caracteres.">
        </div>     
        <div class="grid1_3 margin_top_10">
        	<label for="confirmpasswd"><span>*</span> Confirmar contraseña</label>
        	<input type="password" dojoType="dijit.form.ValidationTextBox" name="confirmpasswd" id="confirmpasswd" 
        	missingMessage="La confirmación de contraseña es requerida." trim="true"
        	constraints="{min:8,max:12}" rangeMessage="La confirmación de contraseña debe contar con 8 a 12 caracteres."
        	maxlength="12" onblur="confirmPassword();"
        	regExp="${regexppwd}" invalidMessage="La confirmación de la contraseña tiene caracteres no coincide con la contraseña capturada. Favor de revisar los datos proporcionados.">
        </div> 
	</fieldset>
	<fieldset>
		<legend>Teléfono de la empresa</legend>
		<div class="labeled margin_top_10"><span>*</span> Teléfono</div>
		<p><em>Debe ingresar un total de 10 dígitos (Clave LADA + Teléfono).</em></p>
		<div class="grid1_3  margin_top_20 fl">
			<div class="margin-r_20 fl">
				<div class="labeled margin_no_t"><span>*</span> Tipo de teléfono</div>	
					<div>
						<div class="tipo_tel margin_top_10 margin-r_10 fl">		
							<input class="fl" type="radio" name="tipoTelefono" id="idTipoTelefonoFijo" checked="checked" 
							       value="${edicionEmpresaForm.idTipoTelefonoFijo}" onclick="fillUpAccessKey()"/>
							<label class="fl" for="idTipoTelefonoFijo">Fijo</label>
						</div>
						<div class="tipo_tel margin_top_10 fl"> 
							<input class="fl" type="radio" name="tipoTelefono" id="idTipoTelefono" disabled="disabled"
						    	   value="${edicionEmpresaForm.idTipoTelefonoCelular}" onclick="fillUpAccessKey()"/>
							<label class="fl" for="idTipoTelefono">Celular</label>
					    </div>
				    </div>					       			       
		    </div>	
		    <div class="margin-r_10 fl">    
				 <label for="acceso"><span>*</span> Acceso</label>
				 <div class="ta_center">
	             <span class="acceso">
	             	<input type="text" name="acceso" id="acceso" value="${edicionEmpresaForm.acceso}" maxlength="3" style="width:3em;" dojoType="dijit.form.ValidationTextBox" required="true" 
	             	readonly="readonly" regExp='^(<%=Constantes.CLAVE_TELEFONO_CELULAR + "|" + Constantes.CLAVE_TELEFONO_FIJO%>)$' 
	             	invalidMessage="Acceso telefónico inválido" missingMessage="Es necesario indicar el acceso telefónico" trim="true">
	             </span>
	             </div>
			</div>
			<div class="fl">
				<label for="clave"><span>*</span> Clave lada</label>
	         	<input type="text" name="clave" id="clave" maxlength="3" style="width:90px !important" value="${edicionEmpresaForm.clave}" dojoType="dijit.form.ValidationTextBox" 
	         	required="true" regExp="^[+]?\d{2,3}$" invalidMessage="Clave lada inválida" missingMessage="Es necesario indicar la clave lada" trim="true">
			</div>
		</div>
		<div class="margin_top_20 fl">
			<label for="telefono"><span>*</span> Teléfono</label>
	     	<input type="text" name="telefono" id="telefono" maxlength="8" style="width:8em;" value="${edicionEmpresaForm.telefono}" onBlur="changePhoneSizeRequired();" 
	     	dojoType="dijit.form.ValidationTextBox" required="true" regExp="^[+]?\d{7,8}$" 
	     	invalidMessage="Teléfono inválido" missingMessage="Es necesario indicar el teléfono" trim="true">
		</div>
		<div class="margin_top_20 fl">
			<label for="extension">Extensión</label>
            <input class="sugerido" type="text" name="extension" id="extension" maxlength="8" style="width:8em;" 
            	value="${edicionEmpresaForm.extension}" dojoType="dijit.form.ValidationTextBox" required="false" 
            	regExp="^[+]?\d{0,8}$" invalidMessage="Extensión telefónica inválida" missingMessage="Es necesario indicar la extensión telefónica" trim="true">		
		</div>
		<div id="phoneadds" name="phoneadds">
			<jsp:include page="/jsp/company/misDatos/phonetable.jsp" flush="true" />  
		</div>
		<div class="clearfix"></div>
		<div class="grid1_3 margin_top_30">	
			  <label for="paginaWeb">Página web</label>
	          <input type="text" id="paginaWeb" name="paginaWeb"
	                 value="${edicionEmpresaForm.paginaWeb}"
	                 dojoType="dijit.form.ValidationTextBox"
	                 size="65" maxlength="65" trim="false;"
	                 oncopy="return false;" oncut="return false" onpaste="return false;"
	                 regExp="${regexpwebsite}" invalidMessage="Formato de pagina web inválido, favor de verificar." />
		</div>		
	</fieldset>
	<div class="form_nav">
		<input id="btnActualizar" name="btnActualizar" class="boton_naranja" type="button" value="Guardar" onclick="actualizar();"/>
		<input id="btnCancel" name="btnCancel" class="boton_naranja" type="button" value="Cancelar" onclick="cancelConfirmation();"/>
	</div>		        						    
	<div dojoType="dijit.Dialog" id="MensajeAlert" title="Mensaje" style="display:none" draggable ="false" >
	  <table class="alertDialog" >
		 <tr align="center">
			 <td><div id ="mensaje"></div>&nbsp;</td>				 
		 </tr>
	 </table>		
</form>





