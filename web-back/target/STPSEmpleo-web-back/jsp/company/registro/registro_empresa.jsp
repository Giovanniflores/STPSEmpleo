<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="mx.gob.stps.portal.core.infra.utils.Constantes" %>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>

<fmt:setBundle basename="MessageResources" var="portalbundle"/>
<fmt:setBundle basename="portal-web" var="messages"/>

<script type="text/javascript" src="googiespell/AJS.js"></script>
<script type="text/javascript" src="googiespell/googiespell.js"></script>
<script type="text/javascript" src="googiespell/cookiesupport.js"></script>
<link href="googiespell/googiespell.css" rel="stylesheet" type="text/css" />

<c:if test="${registroEmpresaForm.permisoGeolocalizacion}">
	<script type="text/javascript" src="https://maps.google.com/maps/api/js?v=3&sensor=false&libraries=places" ></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/geolocalizacion.js" ></script>
	
	<script type="text/javascript">
		function fillLatLng(position){
			dijit.byId('txtLat').attr('value', position.lat());
			dijit.byId('txtLng').attr('value', position.lng());
		}
	</script>
</c:if>

<c:set var="regexpcurp">[a-zA-Z]{4}\d{6}[hmHM][a-zA-Z]{2}[B-DF-HJ-NP-TV-Z|b-df-hj-np-tv-z]{3}[a-zA-Z0-9][0-9]</c:set>
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


 <%String context = request.getContextPath() +"/";
 	 String vProxy = "http://orangoo.com/newnox?lang=";
// 	 String vProxy = context + "SpellCheck.do?method=gogiespell&lang=";
	 pageContext.setAttribute("vProxy",vProxy);
 %>

<style type="text/css">
	#dialogMensaje_underlay { background-color:gray; }
	#underlay_underlay { background-color:gray; }
</style>
<script type="text/javascript" src="js/helper/messageHelper.js"></script>
<script type="text/javascript" src="googiespell/AJS.js"></script>
<script type="text/javascript" src="googiespell/googiespell.js"></script>
<script type="text/javascript" src="googiespell/cookiesupport.js"></script>
<link   href="googiespell/googiespell.css" rel="stylesheet" type="text/css" />



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
	
	dojo.addOnLoad(function() {
		entidadFederativaStore.url = "${context}domicilio.do?method=obtenerEntidad";
	    entidadFederativaStore.close();	
		dijit.byId('idEntidadSelect').disabled=false;
		dijit.byId('idMunicipioSelect').disabled=false;
		dijit.byId('idColoniaSelect').disabled=false;
		
		entidadNacimientoStore.url = "${context}domicilio.do?method=obtenerEntidad";
		entidadNacimientoStore.close();	
		 
		sectorStore.url = "${context}registroEmpresa.do?method=tiposSector";
		sectorStore.close();
		
		tiposSociedadStore.url = "${context}registroEmpresa.do?method=tiposSociedad";
		tiposSociedadStore.close();		
        
	    var idTipoTelefonoFijo = '${registroEmpresaForm.idTipoTelefonoFijo}';
	    var idTipoTelefonoCelular = '${registroEmpresaForm.idTipoTelefonoCelular}';
	    var tipoTelefono = '${registroEmpresaForm.tipoTelefono}';	   
	    if(tipoTelefono==idTipoTelefonoFijo)dojo.byId('telefonoFijo').checked=true;
	    if(tipoTelefono==idTipoTelefonoCelular)dojo.byId('telefonoCelular').checked=true;
	   
	    var acceso = '${registroEmpresaForm.acceso}';	   
	    if(acceso==null||acceso==""){		   
		    document.getElementById('acceso').value = '${registroEmpresaForm.accesoFijo}'; 
	    }	   
		
		if (dijit.byId('tiposEmpresaSelect')){
			dijit.byId('tiposEmpresaSelect').attr( 'value', '${registroEmpresaForm.idTipoEmpresa}');
			dojo.byId('idTipoEmpresa').value = '${registroEmpresaForm.idTipoEmpresa}'
			dojo.byId('tiposEmpresaSelect').focus();
		}
		actualizaCamposRelacionadosConTipoEmpresa();
		
	});	
	
	
	function actualizaCamposRelacionadosConTipoEmpresa(){							
		var vIdTipoEmpresa = dijit.byId('tiposEmpresaSelect').value;
		
		if(vIdTipoEmpresa){
			if(vIdTipoEmpresa == '1'){
				//Publica
				document.getElementById('divEmpresaPrivada').style.display='none';
				document.getElementById('porCurp').style.display = 'none';
				dojo.byId('divCurp').style.display='none';
				dojo.byId('curpSoloLectura').style.display='none';				
				document.getElementById('personaFisica').style.display='none';				
				document.getElementById('divRazonSocial').style.display='block';				
				document.getElementById('personaMoral').style.display='block';
				document.getElementById('divNombrePublica').style.display='block';	
				document.getElementById('divNombreOng').style.display='none';
				document.getElementById("divNombreComercial").className = "grid1_3 fl margin_no_r";
				document.getElementById('divNombreComercial').style.display='block';								
				document.getElementById('divMoralRazonSocial').style.display='none';
				document.getElementById('divActividadEconomica').style.display='block';				
				document.getElementById('h3Domicilio').style.display='block';
				document.getElementById('divDomicilio').style.display='block';				
				document.getElementById('h3DatosContacto').style.display='block';
				document.getElementById('divDatosContacto').style.display='block';		
				document.getElementById('buttonParagraph').style.display='block';		
				document.getElementById('divCaptcha').style.display='none';
				document.getElementById('div_form_nav').style.display='none';
				
				dojo.byId('btnGuardar').disabled=false;
				dojo.byId('curp').value='';
				
			} else if(vIdTipoEmpresa == '2'){

				document.getElementById('divEmpresaPrivada').style.display='block';
				document.getElementById('porCurp').style.display = 'none';
				dojo.byId('divCurp').style.display='none';
				dojo.byId('curpSoloLectura').style.display='none';				
				document.getElementById('personaFisica').style.display='none';				
				document.getElementById('divRazonSocial').style.display='block';				
				document.getElementById('personaMoral').style.display='block';
				document.getElementById('divNombrePublica').style.display='none';	
				document.getElementById('divNombreOng').style.display='none';		
				document.getElementById("divNombreComercial").className = "grid1_3 fl";
				document.getElementById('divNombreComercial').style.display='block';
				document.getElementById('divMoralRazonSocial').style.display='block';
				document.getElementById('divActividadEconomica').style.display='block';				
				document.getElementById('h3Domicilio').style.display='block';
				document.getElementById('divDomicilio').style.display='block';				
				document.getElementById('h3DatosContacto').style.display='block';
				document.getElementById('divDatosContacto').style.display='block';		
				document.getElementById('buttonParagraph').style.display='block';		
				document.getElementById('divCaptcha').style.display='none';
				document.getElementById('div_form_nav').style.display='none';				
				
				dojo.byId('nombreComercial').value = '';
				actualizaTipoPersona();				
				
			} else if(vIdTipoEmpresa == '3'){
				//Privada
				document.getElementById('divEmpresaPrivada').style.display='none';
				document.getElementById('porCurp').style.display = 'none';
				dojo.byId('divCurp').style.display='none';
				dojo.byId('curpSoloLectura').style.display='none';				
				document.getElementById('personaFisica').style.display='none';				
				document.getElementById('divRazonSocial').style.display='block';				
				document.getElementById('personaMoral').style.display='block';
				document.getElementById('divNombrePublica').style.display='none';	
				document.getElementById('divNombreOng').style.display='block';		
				document.getElementById("divNombreComercial").className = "grid1_3 fl margin_no_r";
				document.getElementById('divNombreComercial').style.display='block';
				document.getElementById('divMoralRazonSocial').style.display='none';
				document.getElementById('divActividadEconomica').style.display='block';				
				document.getElementById('h3Domicilio').style.display='block';
				document.getElementById('divDomicilio').style.display='block';				
				document.getElementById('h3DatosContacto').style.display='block';
				document.getElementById('divDatosContacto').style.display='block';		
				document.getElementById('buttonParagraph').style.display='block';		
				document.getElementById('divCaptcha').style.display='none';
				document.getElementById('div_form_nav').style.display='none';					
				
				dojo.byId('nombreComercial').value = '';
				dojo.byId('btnGuardar').disabled=false;
				dojo.byId('curp').value='';
				
			}
			
			if(dijit.byId('tiposEmpresaSelect'))
				dojo.byId('idTipoEmpresa').value = dijit.byId('tiposEmpresaSelect').get('value');					
		}
	}		
	
	
	function actualizaTipoPersona(){

		var vIdTipoEmpresa = dijit.byId('tiposEmpresaSelect').value;
		
		if(vIdTipoEmpresa == '2' && dojo.byId('idTipoPersonaFisica').checked){
			
			document.getElementById("idConocesTuCurpNo").checked = false;
			document.getElementById("idConocesTuCurpSi").checked = true;
			
			if(dijit.byId('razonSocial'))
				dojo.byId('razonSocial').value = '';	
	    	if(dijit.byId('diaActaSelect'))
	    		dijit.byId('diaActaSelect').reset();
	    	if(dijit.byId('anioActaSelect'))
	    		dijit.byId('anioActaSelect').reset();
	    	if(dijit.byId('mesActaSelect'))
	    		dijit.byId('mesActaSelect').reset();
			
			document.getElementById('divEmpresaPrivada').style.display='block';
			document.getElementById('porCurp').style.display = 'block';
			dojo.byId('divCurp').style.display='block';
			dojo.byId('curpSoloLectura').style.display='none';				
			document.getElementById('personaFisica').style.display='none';				
			document.getElementById('divRazonSocial').style.display='none';				
			document.getElementById('personaMoral').style.display='none';
			document.getElementById('divNombrePublica').style.display='none';	
			document.getElementById('divNombreOng').style.display='none';		
			document.getElementById("divNombreComercial").className = "grid1_3 fl";
			document.getElementById('divNombreComercial').style.display='none';
			document.getElementById('divMoralRazonSocial').style.display='none';
			document.getElementById('divActividadEconomica').style.display='none';				
			document.getElementById('h3Domicilio').style.display='none';
			document.getElementById('divDomicilio').style.display='none';				
			document.getElementById('h3DatosContacto').style.display='none';
			document.getElementById('divDatosContacto').style.display='none';		
			document.getElementById('buttonParagraph').style.display='none';		
			document.getElementById('divCaptcha').style.display='block';
			document.getElementById('div_form_nav').style.display='block';
	    	//CALS 07/11/2016
	    	document.getElementById('tiposSociedadDiv').style.display='none';	    	
			
			dojo.byId('btnGuardar').disabled=true;						
			dojo.byId('nombre').focus();	    	

			
		} else if(vIdTipoEmpresa == '2' && dojo.byId('idTipoPersonaMoral').checked) {
			
			document.getElementById("idConocesTuCurpSi").checked = false;
			document.getElementById("idConocesTuCurpNo").checked = true;
			
			if(dijit.byId('nombre'))			
				dojo.byId('nombre').value = '';
			if(dijit.byId('apellido1'))	
				dojo.byId('apellido1').value = '';
			if(dijit.byId('apellido2'))	
				dojo.byId('apellido2').value = '';			
	    	if(dijit.byId('diaSelect'))
	    		dijit.byId('diaSelect').reset();
	    	if(dijit.byId('anioSelect'))
	    		dijit.byId('anioSelect').reset();
	    	if(dijit.byId('mesSelect'))
	    		dijit.byId('mesSelect').reset();
	    	    	
			document.getElementById('divEmpresaPrivada').style.display='block';
			document.getElementById('porCurp').style.display = 'none';
			dojo.byId('divCurp').style.display='none';
			dojo.byId('curpSoloLectura').style.display='none';				
			document.getElementById('personaFisica').style.display='none';				
			document.getElementById('divRazonSocial').style.display='block';				
			document.getElementById('personaMoral').style.display='block';
			document.getElementById('divNombrePublica').style.display='none';	
			document.getElementById('divNombreOng').style.display='none';		
			document.getElementById("divNombreComercial").className = "grid1_3 fl";
			document.getElementById('divNombreComercial').style.display='block';
			document.getElementById('divMoralRazonSocial').style.display='block';
			document.getElementById('divActividadEconomica').style.display='block';				
			document.getElementById('h3Domicilio').style.display='block';
			document.getElementById('divDomicilio').style.display='block';				
			document.getElementById('h3DatosContacto').style.display='block';
			document.getElementById('divDatosContacto').style.display='block';		
			document.getElementById('buttonParagraph').style.display='block';		
			document.getElementById('divCaptcha').style.display='none';
			document.getElementById('div_form_nav').style.display='none';		    	

			dojo.byId('curp').value='';	    	
	    	dojo.byId('razonSocial').focus();
			dojo.byId('btnGuardar').disabled=false;
	    	
		}	
	}	
	
	
	function actualizaConoceCurp(){
						
		if(document.getElementById('idConocesTuCurpSi').checked){		

			document.getElementById('divEmpresaPrivada').style.display='block';
			document.getElementById('porCurp').style.display = 'block';
			dojo.byId('divCurp').style.display='block';
			dojo.byId('curpSoloLectura').style.display='none';				
			document.getElementById('personaFisica').style.display='none';				
			document.getElementById('divRazonSocial').style.display='none';				
			document.getElementById('personaMoral').style.display='none';
			document.getElementById('divNombrePublica').style.display='none';	
			document.getElementById('divNombreOng').style.display='none';		
			document.getElementById("divNombreComercial").className = "grid1_3 fl";
			document.getElementById('divNombreComercial').style.display='none';
			document.getElementById('divMoralRazonSocial').style.display='none';
			document.getElementById('divActividadEconomica').style.display='none';				
			document.getElementById('h3Domicilio').style.display='none';
			document.getElementById('divDomicilio').style.display='none';				
			document.getElementById('h3DatosContacto').style.display='none';
			document.getElementById('divDatosContacto').style.display='none';		
			document.getElementById('buttonParagraph').style.display='none';		
			document.getElementById('divCaptcha').style.display='block';
			document.getElementById('div_form_nav').style.display='block';					
						
		} else if(document.getElementById('idConocesTuCurpNo').checked){
			
			document.getElementById('divEmpresaPrivada').style.display='block';
			document.getElementById('porCurp').style.display = 'block';
			dojo.byId('divCurp').style.display='none';
			dojo.byId('curpSoloLectura').style.display='none';				
			document.getElementById('personaFisica').style.display='block';				
			document.getElementById('divRazonSocial').style.display='none';				
			document.getElementById('personaMoral').style.display='none';
			document.getElementById('divNombrePublica').style.display='none';	
			document.getElementById('divNombreOng').style.display='none';		
			document.getElementById("divNombreComercial").className = "grid1_3 fl";
			document.getElementById('divNombreComercial').style.display='none';
			document.getElementById('divMoralRazonSocial').style.display='none';
			document.getElementById('divActividadEconomica').style.display='none';				
			document.getElementById('h3Domicilio').style.display='none';
			document.getElementById('divDomicilio').style.display='none';				
			document.getElementById('h3DatosContacto').style.display='none';
			document.getElementById('divDatosContacto').style.display='none';		
			document.getElementById('buttonParagraph').style.display='none';		
			document.getElementById('divCaptcha').style.display='block';
			document.getElementById('div_form_nav').style.display='block';				
		}
	}
	
	function cambiarPregunta(){
		dojo.xhrPost({url: 'registroEmpresa.do?method=cambiaPregunta',
			  load: function(data) {
				  document.getElementById('captchaPregunta').innerHTML = data;
			  }				  		
			});
	}	
	
	function validaCURP(){
		
		if(document.getElementById('idConocesTuCurpNo').checked){
			estableceValoresFechaNacimiento();	
		}
				
		validarDatosRelacionadosCurp();
		
		if (!dijit.byId('respuestaUsuario').isValid()){
			displayErrorMsg(dijit.byId('respuestaUsuario'), dijit.byId('respuestaUsuario').get("missingMessage"));
			return false;
		}
				
		if(document.getElementById('idConocesTuCurpNo').checked){
			consultarCurpDesconocido();	
			
		} else if(document.getElementById('idConocesTuCurpSi').checked){
			consultarCurpConocido();	
		}
	}

	function validaCaptcha(){
		if (validaRespuestaCaptcha()) {
			validaCURP()
		} else {
//			alertMsg('La respuesta no es correcta: por favor, ponga una nueva o cambie de pregunta.');
			dijit.byId("dialogErrorCaptcha").show();
		}
	}
	
    function estableceValoresFechaNacimiento(){
    	dojo.byId('dia').value  = dijit.byId('diaSelect').get('value');
    	dojo.byId('anio').value = dijit.byId('anioSelect').get('value');
    	dojo.byId('mes').value  = dijit.byId('mesSelect').get('value');
    	dojo.byId('idEntidadNacimiento').value = dijit.byId('entidadNacimientoSelect').get('value');
    }			
					
	function validarDatosRelacionadosCurp(){
	
		if(document.getElementById('idConocesTuCurpSi').checked){		
			if (!validaCampo('curp')){
				displayErrorMsg(dijit.byId('curp'),"Es necesario indicar el CURP.");			
				dijit.byId('curp').focus();			
				dojo.byId('btnValidarCurp').focus();
				return false;
			}			
			
		} else if(document.getElementById('idConocesTuCurpNo').checked) {
			
			if (!validaCampo('nombre')){
				displayErrorMsg(dijit.byId('nombre'),"Es necesario indicar el nombre.");			
				dijit.byId('nombre').focus();			
				dojo.byId('btnValidarCurp').focus();
				return false;
			}
			if (!validaCampo('apellido1')){
				displayErrorMsg(dijit.byId('apellido1'),"Es necesario indicar el primer apellido.");	
				dijit.byId('apellido1').focus();
				dojo.byId('btnValidarCurp').focus();
				return false;
			}
			if (!validaCampo('apellido2')){
				alertMsg(dijit.byId('apellido2').get("invalidMessage"));
				dijit.byId('apellido2').focus();
				dojo.byId('btnValidarCurp').focus();
				return false;
			}
			if (!dijit.byId('diaSelect').isValid()){
				alertMsg('Es necesario seleccionar el dia de fecha de nacimiento.');
				dijit.byId('diaSelect').focus();
				dojo.byId('btnValidarCurp').focus();
				return false;
			}
			if (!dijit.byId('mesSelect').isValid()){
				alertMsg('Es necesario seleccionar el mes de fecha de nacimiento.');
				dijit.byId('mesSelect').focus();
				dojo.byId('btnValidarCurp').focus();
				return false;
			}
			if (!dijit.byId('anioSelect').isValid()){
				alertMsg('Es necesario seleccionar el año de fecha de nacimiento.');
				dijit.byId('anioSelect').focus();
				dojo.byId('btnValidarCurp').focus();
				return false;
			}
			
			var dia  = dijit.byId('diaSelect').get('value');
			var mes  = dijit.byId('mesSelect').get('value');
			var anio = dijit.byId('anioSelect').get('value');

			if (dia=='0'){
				alertMsg('Es necesario seleccionar el dia de fecha de nacimiento.');
				dijit.byId('diaSelect').focus();
				return false;
			}

			if (mes=='0'){
				alertMsg('Es necesario seleccionar el mes de fecha de nacimiento.');
				dijit.byId('mesSelect').focus();
				return false;
			}

			if (anio=='0'){
				alertMsg('Es necesario seleccionar el año de fecha de nacimiento.');
				dijit.byId('anioSelect').focus();
				return false;
			}

			if (!dijit.byId('entidadNacimientoSelect').isValid()){
				alertMsg('Es necesario seleccionar la entidad federativa de nacimiento.');
				dijit.byId('entidadNacimientoSelect').focus();
				dojo.byId('btnValidarCurp').focus();
				return false;
			}				
		}
	}	
	
	
	function recuperaPsw(){		
		doSubmit('toRecuperaContrasena');
	}
	
	function doSubmit(method){
		dojo.byId('method').value=method;
		dojo.byId('registroEmpresaForm').submit();
	}
	
	function consultarCurpDesconocido(){
		
		var curpValido = true;			
		
		bloquearPantalla();
		
		dojo.xhrPost({url: 'registroEmpresa.do?method=validaCurp', form:'registroEmpresaForm', sync: true, 
	  		  load: function(data){	
	  			  
	  			desbloquearPantalla(); 
	  			
	  			var res = dojo.fromJson(data);
	  			
	  			if('EXITOSO' == res.statusOper){
	  				
						curpValido = true;	
  						
  						curpValue = res.value;
  						
  						establecerDatosCurp(res.nombre, res.apellido1, res.apellido2, res.entidadNacimiento, res.genero, res.curp, res.fechaNac);  						
						
  						redesplegarDatosCurp(false); 
  						
  						var esUnico = validaCURPUnico();
  						
  						if(!esUnico){
  							
  							var mensajecurp = "<strong>La CURP ya está registrada.</strong><br/>" + 
  								"Para ingresar al portal, puedes:<br/>"+
  								"<input id='btnRecuperaPsw' name='btnRecuperaPsw' class='boton_naranja' type='button' value='Recuperar tu contraseña'" +
  								" onclick='recuperaPsw();'><br/>" + 
  								"Si lo deseas comunícate al teléfono <strong><fmt:message key='portal.contacto.telefono' bundle='${messages}'/></strong><br/>";
  								
  								//showErrorMsg(mensajecurp);
  								alertMsg(mensajecurp);
  								
  						} else {
  													  							
	  						curpValido = true;	
							redesplegarDatosCurp(true);  							
  						}  						
  						
				} else if('NO EXITOSO' == res.statusOper && res.message && res.message.indexOf('realizar su consulta')>0) {
 						
	    			var msgerr = "La transacción no pudo ser completada,<br/>favor de intentarlo nuevamente después de unos instantes.<br/><br/>";
					//showErrorMsg(msgerr);
	    			alertMsg(msgerr);
	  				
				} else if('NO EXITOSO' == res.statusOper && 'Error' != res.message ) {	
  						
  						dijit.byId('nombre').focus();
		    			var nofind = false;
		    			if (res.message && res.message.indexOf('no se encuentra')>0){
		    				nofind = true;
		    			}
		    			
		    			var msgerr; 	
		    			if (nofind){
		    				msgerr = "La CURP no existe, favor de verificarla.<br/>Si tu CURP es correcta visita la siguiente liga <br/>"+
		    				"<a href='http://www.renapo.gob.mx/swb/swb/RENAPO/tramite' target='_blank'>http://www.renapo.gob.mx/swb/swb/RENAPO/tramite</a> "+
		    				"<br/>para ver si existe un problema con tu CURP.<br/><br/>"

		    			} else {
						    msgerr = "Favor de revisar los datos proporcionados.<br/>Si tus datos son correctos visita la siguiente liga <br/>"+
								"<a href='http://www.renapo.gob.mx/swb/swb/RENAPO/tramite' target='_blank'>http://www.renapo.gob.mx/swb/swb/RENAPO/tramite</a> "+
								"<br/>para ver si existe un problema con tu CURP.<br/><br/>";

		    			}
		    			//showErrorMsg(msgerr);
		    			alertMsg(msgerr);
  						
  				} else if('NO EXITOSO' == res.statusOper && res.message && res.message.indexOf('realizar su consulta')>0) {
  						
		    			var msgerr = "La transacción no pudo ser completada,<br/>favor de intentarlo nuevamente después de unos instantes.<br/><br/>";
						//showErrorMsg(msgerr);
		    			alertMsg(msgerr);
  						
  				} else if('error' == res.statusOper){
  						
		    			var msgerr = "La transacción no pudo ser completada,<br/>favor de intentarlo nuevamente después de unos instantes.<br/><br/>";		                 
						//showErrorMsg(msgerr);
						alertMsg(msgerr);
  						
  						curpValido = false;
  						
  				} else if('undefined' == res.statusOper){

		    			var msgerr = "La transacción no pudo ser completada,<br/>favor de intentarlo nuevamente después de unos instantes.<br/><br/>";
						//showErrorMsg(msgerr);
		    			alertMsg(msgerr);
  				}	  			
	  		  }
		});		
	}
	
	
	function consultarCurpConocido(){
		
		//COMENTADO POR EL ESTILO bloquearPantalla();
		
		var curpValue = dojo.byId('curp').value;		
		var curpValido = true;	
		
		var esUnico = validaCURPUnico();
		
		if(!esUnico){
			
				var mensajecurp = "<strong>La CURP ya está registrada.</strong><br/>" + 
					"Para ingresar al portal, puedes:<br/>"+
					"<input id='btnRecuperaPsw' name='btnRecuperaPsw' class='boton_naranja' type='button' value='Recuperar tu contraseña'" +
					" onclick='recuperaPsw();'><br/>" + 
					"Si lo deseas comunícate al teléfono <strong><fmt:message key='portal.contacto.telefono' bundle='${messages}'/></strong><br/>";
					
					curpValido = false;	
					
					//showErrorMsg(mensajecurp);
					alertMsg(mensajecurp);
					
					//desbloquearPantalla(); 

		} else {			
			dojo.xhrPost({url: 'registroEmpresa.do?method=validaCurp', form:'registroEmpresaForm', sync: true, 
		  		  load: function(data){
		  			  	
		  					//desbloquearPantalla(); 
		  			  
		  					var res = dojo.fromJson(data);
		  					
		  					if('EXITOSO' == res.statusOper){

		  						curpValido = true;
		  						
		  						establecerDatosCurp(res.nombre, res.apellido1, res.apellido2, res.entidadNacimiento, res.genero, res.curp, res.fechaNac);
		  						
		  						redesplegarDatosCurp(true);  
		  						
		  					} else if(res.message && res.message.indexOf('La respuesta no es correcta')>0) {
		  						
		  						//showErrorMsg(res.message);
		  						alertMsg(res.message);
		  						
		  					} else if('NO EXITOSO' == res.statusOper && 'Error' != res.message ) {	
		  						
		  						dijit.byId('nombre').focus();
				    			var nofind = false;
				    			if (res.message && res.message.indexOf('no se encuentra')>0){
				    				nofind = true;
				    			}
				    			
				    			var msgerr; 	
				    			if (nofind){
				    				msgerr = "La CURP no existe, favor de verificarla.<br/>Si tu CURP es correcta visita la siguiente liga <br/>"+
				    				"<a href='http://www.renapo.gob.mx/swb/swb/RENAPO/tramite' target='_blank'>http://www.renapo.gob.mx/swb/swb/RENAPO/tramite</a> "+
				    				"<br/>para ver si existe un problema con tu CURP.<br/><br/>";

				    			} else {
								    msgerr = "Favor de revisar los datos proporcionados.<br/>Si tus datos son correctos visita la siguiente liga <br/>"+
										"<a href='http://www.renapo.gob.mx/swb/swb/RENAPO/tramite' target='_blank'>http://www.renapo.gob.mx/swb/swb/RENAPO/tramite</a> "+
										"<br/>para ver si existe un problema con tu CURP.<br/><br/>";
				    			}
				    			//showErrorMsg(msgerr);
				    			alertMsg(msgerr);
		  						
		  					} else if('err' == res.statusOper && res.message && res.message.indexOf('realizar su consulta')>0) {
		  						
				    			var msgerr = "La transacción no pudo ser completada,<br/>favor de intentarlo nuevamente después de unos instantes.<br/><br/>";
	    						//showErrorMsg(msgerr);
				    			alertMsg(msgerr);
		  						
		  					} else if('error' == res.statusOper){
		  						
				    			var msgerr = "La transacción no pudo ser completada,<br/>favor de intentarlo nuevamente después de unos instantes.<br/><br/>";
	    						//showErrorMsg(msgerr);
	    						alertMsg(msgerr);
		  						
		  						curpValido = false;
		  						
		  					} else if('undefined' == res.statusOper){

				    			var msgerr = "La transacción no pudo ser completada,<br/>favor de intentarlo nuevamente después de unos instantes.<br/><br/>";
	    						//showErrorMsg(msgerr);
				    			alertMsg(msgerr);
		  					}
		  		  		}
					});
			return curpValido;
		}
	}
	

	function validaCURPUnico(){
		var unico = true;
		
		dojo.xhrPost({url: 'registroEmpresa.do?method=validaCURPUnico', form:'registroEmpresaForm', sync: true, 
	  		  load: function(data){
	  					var res = dojo.fromJson(data);	
	  					
	  					if(res.statusOper == 'exito'){
	  						if (res.message == 'unico'){
	  							unico = true;
	  							
	  						} else {
	  							unico = false;	  	
	  						}
	  					} else if (res.statusOper == 'error'){
	  						unico = false;
	  					}	  					
				}
		});
		
		return unico;
	}

	function validaRespuestaCaptcha(){
		var captchaValido = false;
		dojo.xhrPost({url: 'registroEmpresa.do?method=validaRespuestaCaptcha', form:'registroEmpresaForm', sync: true,
			load: function(data){
				var res = dojo.fromJson(data);
				console.log(res.statusOper == 'exito' ? 'Captcha correcto' : 'Captcha incorrecto');
				captchaValido = res.statusOper == 'exito';
			}
		});
		return captchaValido;
	}

		
	var underlay;
	
    function bloquearPantalla() {
    	
		var html = '<h3>¡Su consulta puede tardar algunos segundos!</h3>'+
		'<p><img style="text-align: center;" src="images/ajax-loader.gif"/></p>';
		underlay = 
	    new dijit.Dialog({
	        title: 	 'Mensaje',
	        content: html,
	        style: 	 "width: auto",	        	        
	        draggable : false, closable : false,disableCloseButton: true
	    });
	    underlay.closeButtonNode.style.display = 'none'; 
		underlay.show();	
	}
	
    
	function desbloquearPantalla() {
		underlay.hide();
	}
	

	function calcularEdad(fechaNacimiento) {
		 
	    var hoy = new Date();
	    var anioActual = hoy.getFullYear();
	    var mesActual = hoy.getMonth();
	    var diaActual = hoy.getDate();
	 
	    var anioFechaNacimiento = fechaNacimiento.getFullYear();
	    var mesFechaNacimiento = fechaNacimiento.getMonth();
	    var diaFechaNacimiento = fechaNacimiento.getDate();
	 
	    var edad = anioActual - anioFechaNacimiento;
	    var mesEdad = mesActual - mesFechaNacimiento;
	    var diaEdad = diaActual - diaFechaNacimiento;
	    
	    if(mesEdad < 0 || (mesEdad == 0 && diaEdad <0)) {
	    	edad = parseInt(edad) -1;
	    }
	    
	    return edad;	 
	}	
	
	function convertirAFormatoFecha( dateString){
        
		var dateObject = null;
		
		if (typeof dateString !== 'undefined' && dateString.length > 1) {
			
	        var array = dateString.split('/');

	        var dia = parseInt(array[0]);
	        var mes = parseInt(array[1]);
	        var anio = parseInt(array[2]);
		
	        dateObject = new Date(anio, mes - 1, dia);
		}
        
        return dateObject;
    }	
	
	function establecerDatosCurp(nombre, apellido1, apellido2, entidadNacimiento, genero, curp, fechaNac){
		
		document.getElementById('porCurp').style.display = 'block';		
		document.getElementById('divCurp').style.display = 'none';
		document.getElementById('curpSoloLectura').style.display = 'block';		
		
		dojo.byId('curp').value = curp;
		dijit.byId('curp').readOnly = true;			
		dojo.byId('readOnlyCurp').innerHTML = curp;
		dojo.byId('readOnlyNombre').innerHTML = nombre;
		dojo.byId('readOnlyApellido1').innerHTML = apellido1; 
		if(apellido2){
			dojo.byId('readOnlyApellido2').innerHTML = apellido2;	
		}		
				
		if(genero == 'H'){
			dojo.byId('readOnlySexo').innerHTML = 'Hombre';
		} else if(genero == 'M'){
			dojo.byId('readOnlySexo').innerHTML = 'Mujer';	
		}
		
		dojo.byId('readOnlyEntidadNacimiento').innerHTML = entidadNacimiento;
		
		dojo.byId('readOnlyEdad').innerHTML = calcularEdad(convertirAFormatoFecha(fechaNac));

		if (typeof fechaNac !== 'undefined' && fechaNac.length > 1) {
		   var arrayd = fechaNac.split('/');
		   dojo.byId('dia').innerHTML = arrayd[0];
   		   dojo.byId('mes').innerHTML = arrayd[1];
		   dojo.byId('anio').innerHTML = arrayd[2];
		}
					
	}
	
	
	function redesplegarDatosCurp(esUnico){		
		if(esUnico){
			document.getElementById('porCurp').style.display = 'block';		
			document.getElementById('divCurp').style.display = 'none';
			document.getElementById('curpSoloLectura').style.display = 'block';
			document.getElementById('personaFisica').style.display = 'none';
			
			document.getElementById('idConocesCurp').style.display = 'none';
			
			document.getElementById('divRazonSocial').style.display='block';				
			document.getElementById('personaMoral').style.display='block';
			document.getElementById('divNombrePublica').style.display='none';	
			document.getElementById('divNombreOng').style.display='none';
			document.getElementById("divNombreComercial").className = "grid1_3 fl margin_no_r";
			document.getElementById('divNombreComercial').style.display='block';								
			document.getElementById('divMoralRazonSocial').style.display='none';
			document.getElementById('divActividadEconomica').style.display='block';				
			document.getElementById('h3Domicilio').style.display='block';
			document.getElementById('divDomicilio').style.display='block';				
			document.getElementById('h3DatosContacto').style.display='block';
			document.getElementById('divDatosContacto').style.display='block';		
			document.getElementById('buttonParagraph').style.display='block';		
			document.getElementById('divCaptcha').style.display='none';
			document.getElementById('div_form_nav').style.display='none';			
			dojo.byId('btnGuardar').disabled=false;	
			
		}
	}	
	
	/*TELEFONOS*/
	function estableceAcceso(){
		if(dojo.byId('telefonoFijo').checked){
			dojo.byId('accesoDiv').innerHTML = '${registroEmpresaForm.accesoFijo}';
			document.getElementById('acceso').value = '${registroEmpresaForm.accesoFijo}';
		} else if(dojo.byId('telefonoCelular').checked){
			dojo.byId('accesoDiv').innerHTML = '${registroEmpresaForm.accesoCelular}';
			document.getElementById('acceso').value = '${registroEmpresaForm.accesoCelular}';
		} else {
			dojo.byId('accesoDiv').innerHTML = '${registroEmpresaForm.accesoCelular}';
			document.getElementById('acceso').value = '${registroEmpresaForm.accesoCelular}';
		}
	}	
	
	function estableceAccesoAdd(campoAcceso,campoTipoTelefono,id,telefonoFijo,telefonoCelular,divExtensionFijo,divExtensionCelular){
		if(document.getElementById(telefonoFijo).checked){
			dojo.byId('accesoDivAdd_'+id).innerHTML = '${registroEmpresaForm.accesoFijo}';
			document.getElementById(campoAcceso).value = '${registroEmpresaForm.accesoFijo}';
			document.getElementById(campoTipoTelefono).value = '${registroEmpresaForm.idTipoTelefonoFijo}';
			document.getElementById(divExtensionFijo).style.display='block';
			document.getElementById(divExtensionCelular).style.display='none';
			document.getElementById('extensionAdd').value='';
		} else if(document.getElementById(telefonoCelular).checked){
			dojo.byId('accesoDivAdd_'+id).innerHTML = '${registroEmpresaForm.accesoCelular}';
			document.getElementById(campoAcceso).value = '${registroEmpresaForm.accesoCelular}';
			document.getElementById(campoTipoTelefono).value = '${registroEmpresaForm.idTipoTelefonoCelular}';
			document.getElementById(divExtensionFijo).style.display='none';
			document.getElementById(divExtensionCelular).style.display='block';
			document.getElementById('extensionAdd').value='';
			if(document.getElementById('extensionTelefonoAdicional_'+id)){
				document.getElementById('extensionTelefonoAdicional_'+id).value="";
			}			
		} else {
			dojo.byId('accesoDivAdd_'+id).innerHTML = '${registroEmpresaForm.accesoCelular}';
			document.getElementById(campoAcceso).value = '${registroEmpresaForm.accesoCelular}';
			document.getElementById(campoTipoTelefono).value = '${registroEmpresaForm.idTipoTelefonoCelular}';
		}
	}	
	
	function changePhoneSizeRequired(){
		var vClave = dijit.byId('clave');
		var vTelefono = dijit.byId('telefono');
		 
		if(vClave.value.length < 2){
			vClave.focus();
			displayErrorMsg(vClave, 'Debe proporcionar una clave LADA de 2 ó 3 caracteres');
			return false;
		} else if(vClave.value.length == 2) {
			if(vTelefono.value.length != 8){
				displayErrorMsg(vTelefono, 'Debe proporcionar un teléfono de 8 dígitos');
				return false;
			}
		} else if(vClave.value.length==3){
			if(vTelefono.value.length!=7){
				displayErrorMsg(vTelefono, 'Debe proporcionar un teléfono de 7 dígitos');
				return false;
			}	
		}
		return true;
	}		
	
	function changePhoneSizeRequiredAdd(campoClave,campoTelefono){
		var vClave = dijit.byId(campoClave);
		var vTelefono = dijit.byId(campoTelefono);		 
		if(vClave.value.length < 2){
			vClave.focus();
			displayErrorMsg(vClave, 'Debe proporcionar una clave LADA de 2 ó 3 caracteres');
			return false;
		} else if(vClave.value.length == 2) {
			if(vTelefono.value.length != 8){
				displayErrorMsg(vTelefono, 'Debe proporcionar un teléfono de 8 dígitos');
				return false;
			}
		} else if(vClave.value.length==3){
			if(vTelefono.value.length!=7){
				displayErrorMsg(vTelefono, 'Debe proporcionar un teléfono de 7 dígitos');
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
	
	function doSubmitAddTelefono() {	
		var vAcceso = document.getElementById('accesoAdd').value;
		if(vAcceso==null||vAcceso==""){
			alertMsg('Debe seleccionar un tipo de teléfono (fijo o celular) para que se obtenga el número de acceso telefónico correspondiente');	
			return false;
		} else {
			if (!checkWithoutMessage('claveAdd')){
				alertMsg('La clave es inválida.');
				return false;
			} else {
				if (!checkWithoutMessage('telefonoAdd')){
					alertMsg('El número telefónico es inválido.');
					return false;
				} else {
					var vClave = document.getElementById('claveAdd');
					var vTelefono = document.getElementById('telefonoAdd');
					var vExtension = document.getElementById('extensionAdd');
					if(vClave.value.length == 2) {			
						if(vTelefono.value.length != 8){				
							alertMsg('Debe proporcionar un teléfono de 8 dígitos');				
							return false;
						}
					} else if(vClave.value.length==3){			
						if(vTelefono.value.length!=7){				
							alertMsg('Debe proporcionar un teléfono de 7 dígitos');			
							return false;
						}	
					}	
					if(vExtension.value.length>0){
						if (!checkWithoutMessage('extensionAdd')){
							alertMsg('La extensión es inválida.');
							return false;
						}	
					}
					addTelefono();	
					telefonoFijoAdd.checked=false;
					telefonoCelularAdd.checked=false;					
				}		
			}
		}			
	}
	
	function addTelefono() {
		var accesoAdd=dijit.byId('accesoAdd').get('value');
		var idtipoTelefonoAdd=1;
		if(accesoAdd != '01'){
			idtipoTelefonoAdd=5;			
		}
		var claveAdd=dijit.byId('claveAdd').get('value');
		var telefonoAdd=dijit.byId('telefonoAdd').get('value');
		var extensionAdd=dijit.byId('extensionAdd').get('value');
		document.getElementById('telefonoAdicional').style.display = 'none';
		var url = 'registroEmpresa.do?method=telefonoAdicional&idtipoTelefonoAdd='+idtipoTelefonoAdd+'&accesoAdd='+accesoAdd+"&claveAdd="+claveAdd+"&telefonoAdd="+telefonoAdd+"&extensionAdd="+extensionAdd;
		dojo.xhrPost({url: url, form:'registroEmpresaForm', sync: true,preventCache: true,timeout:180000, 
					  load: function(data) {
						  	dijit.byId('idTipoTelefonoAdd').set('value','');
						  	dijit.byId('accesoAdd').set('value','');
						  	dijit.byId('claveAdd').set('value','');
						  	dijit.byId('telefonoAdd').set('value','');
						  	dijit.byId('extensionAdd').set('value','');
						  	dojo.byId('agregaTelefono').innerHTML=data;
						  	dojo.parser.parse("agregaTelefono");						  	
					  }});
	}	
	
	function toggle() {
		document.getElementById('telefonoAdicional').style.display = 'block';
		document.getElementById('bAgregarTelefono').style.display = 'none';
	}
	
	function eliminarTelefono(acceso,clave,telefono,extension){
		var accesoEliminar=acceso;
		var claveEliminar=clave;
		var telefonoEliminar=telefono;
		var extensionEliminar=extension;
		document.getElementById('telefonoAdicional').style.display = 'none';
		dojo.xhrPost({url: 'registroEmpresa.do?method=deletePhone&accesoEliminar='+accesoEliminar+'&claveEliminar='+claveEliminar+'&telefonoEliminar='+telefonoEliminar+'&extensionEliminar='+extensionEliminar, form:'registroEmpresaForm', sync: true,preventCache: true, timeout:180000, 
					  load: function(data) {
						  dijit.byId('idTipoTelefonoAdd').set('value','');
						  	dijit.byId('accesoAdd').set('value','');
						  	dijit.byId('claveAdd').set('value','');
						  	dijit.byId('telefonoAdd').set('value','');
						  	dijit.byId('extensionAdd').set('value','');
						  	dojo.byId('agregaTelefono').innerHTML=data;
						  	dojo.parser.parse("agregaTelefono");						  	
					  }});
	}
	
	function actualizaValorAd(campo,valor){
		document.getElementById(campo).value=valor;
	}		
	/*TERMINA TELEFONOS*/
		
	
	function doSubmitEmpresa(method){
		dojo.byId('method').value=method;
		dojo.byId('btnGuardar').disabled=true;
		dojo.byId('registroEmpresaForm').submit();
	}	
	
	function cancelConfirmation() {
		var answer = confirm("Los datos no guardados se perderán ¿Continuar?");
		if (answer){
			doSubmitEmpresa('cancelar');
		}
	}	
	
	function cancelar(){
		document.location.href = '/registroEmpresa.do?method=init';
	}	
	
    function limpiarEspacios(ele){
    	if (!ele || !ele.value) return;
    	
    	var cadena = ele.value;
    	ele.value = cadena.replace("  "," ");
    }
    
    function estableceValores(){
		var vIdTipoEmpresa = dijit.byId('tiposEmpresaSelect').get('value');

    	if(vIdTipoEmpresa == 2 && dojo.byId('idTipoPersonaFisica').checked){

        	if(dijit.byId('diaSelect'))
        		dojo.byId('dia').value  = dijit.byId('diaSelect').get('value');
        	if(dijit.byId('anioSelect'))
        		dojo.byId('anio').value = dijit.byId('anioSelect').get('value');
        	if(dijit.byId('mesSelect'))
        		dojo.byId('mes').value  = dijit.byId('mesSelect').get('value');
        	        	
        	document.getElementById('dia').style.display = 'block';        	 
        	
    		if(dijit.byId('entidadNacimientoSelect'))
    			dojo.byId('idEntidadNacimiento').value = dijit.byId('entidadNacimientoSelect').get('value');		
        	    		
    	} else if(vIdTipoEmpresa == 2 && dojo.byId('idTipoPersonaMoral').checked) {
    		    		
        	if(dijit.byId('diaActaSelect'))
        		dojo.byId('dia').value  = dijit.byId('diaActaSelect').get('value');
        	if(dijit.byId('anioActaSelect'))
        		dojo.byId('anio').value = dijit.byId('anioActaSelect').get('value');
        	if(dijit.byId('mesActaSelect'))
        		dojo.byId('mes').value  = dijit.byId('mesActaSelect').get('value');  
    	
    	}  	
    	
		if(dijit.byId('idEntidadSelect'))
			dojo.byId('idEntidad').value   			 		= dijit.byId('idEntidadSelect').get('value');
		if(dijit.byId('idMunicipioSelect'))
			dojo.byId('idMunicipio').value   		 		= dijit.byId('idMunicipioSelect').get('value');
		if(dijit.byId('idColoniaSelect'))
			dojo.byId('idColonia').value   			 		= dijit.byId('idColoniaSelect').get('value');		
		if(dijit.byId('tiposEmpresaSelect'))
			dojo.byId('idTipoEmpresa').value   	     		= dijit.byId('tiposEmpresaSelect').get('value');	
		if(dijit.byId('tiposSociedadSelect'))
			dojo.byId('idTipoSociedad').value   	     		= dijit.byId('tiposSociedadSelect').get('value');	
		
		if(dijit.byId('idRamaSelect'))
			dojo.byId('idTipoActividadEconomica').value   	= dijit.byId('idRamaSelect').get('value');		
		if(dijit.byId('enterasteSelect'))
			dojo.byId('idMedio').value   					= dijit.byId('enterasteSelect').get('value');			
    }	
	
    
    function regenerar(){
		estableceValores();
		if (!validaCampos()) return;	
		doSubmitEmpresa('regenerar');    	
    }
    
    
	function registrar(){
		estableceValores();
		if (!validaCampos()) return;
		if (validaCorreoElectronicoUnico()==false){
			alertMsg('El correo electrónico ya se encuentra registrado.');
			var textBox = dijit.byId('correoElectronico');
			displayErrorMsg(textBox, 'El correo electrónico ya se encuentra registrado.');
			return;
		}		
		doSubmitEmpresa('registrar');
	}	
	
	function validarClaveLada(claveLada, indice) {
		var regExp = /^[+]?\d{2,3}$/;
		if (!regExp.test(claveLada.value)) {
			if(indice==undefined){
				alertMsg("La clave lada del teléfono adicional es inválida.");
			} else {
				alertMsg("La clave lada del teléfono adicional No. " + indice + " es inválida.");	
			}			
			return false;
		}
		return true;
	}	
	
	function validarFormatoTelefono(formatoTelefono, indice) {
		var regExp = /^[+]?\d{7,8}$/;
		if (!regExp.test(formatoTelefono.value)) {
			if(indice==undefined){
				alertMsg("El teléfono adicional es inválido.");	
			} else {
				alertMsg("El teléfono adicional No. " + indice + " es inválido.");	
			}			
			return false;
		}
		return true;
	}	
	
	function validarExtension(formatoExtension, indice){
		var regExp = /^[+]?\d{0,6}$/;
		if (!regExp.test(formatoExtension.value)) {
			if(indice==undefined){
				alertMsg("La extensión del teléfono adicional es inválida.");	
			} else {
				alertMsg("La extensión del teléfono adicional No. " + indice + " es inválida.");	
			}			
			return false;
		}
		return true;		
	}
	
	function validarNombrePers(formatoNomPers){
		var regExpApe1 = /^[A-Za-z\s\áéíóúÁÉÍÓÚñÑ]{1,50}$/;
		if (!regExpApe1.test(formatoNomPers.value)) {
			alertMsg("El nombre es inválido porque no contiene caracteres alfabéticos");	
			formatoNomPers.focus();
			return false;
		}
		return true;		
	} 	
	
	function validarApe1(formatoApe){
		var regExpApe1 = /^[A-Za-z\s\áéíóúÁÉÍÓÚñÑ]{2,50}$/;
		if (!regExpApe1.test(formatoApe.value)) {
			alertMsg("El primer apellido es inválido porque no contiene caracteres alfabéticos");	
			formatoApe.focus();
			return false;
		}
		return true;		
	}  		
	
	function validarRazonSocial(formatoRazon){
		var regExpRazonSoc = /^[A-Za-z\s\áéíóúÁÉÍÓÚñÑ]{3,150}$/;
		if (!regExpRazonSoc.test(formatoRazon.value)) {
			alertMsg("La razón social es inválida porque no contiene caracteres alfabéticos");	
			formatoRazon.focus();
			return false;
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

	function validaCampos(){
		var vIdTipoEmpresa = dijit.byId('tiposEmpresaSelect').get('value');
    	
		if (!dijit.byId('registroEmpresaForm').isValid()){
			
			if(vIdTipoEmpresa == 2 && dojo.byId('idTipoPersonaFisica').checked && document.getElementById('idConocesTuCurpNo').checked){				
				
				if (!validaCampo('nombre')){
					var cNombre = dijit.byId('nombre');
					displayErrorMsg(cNombre,"Es necesario indicar el nombre.");
					return false;
				} else {					
					var vNomPersEmp = dijit.byId('nombre');
					if(!validarNombrePers(vNomPersEmp)) return false;						
				}
				
				if (!validaCampo('apellido1')){
					var cApellido1 = dijit.byId('apellido1');
					displayErrorMsg(cApellido1,"Es necesario indicar el primer apellido.");					
					return false;
				} else {
					var vApe1Emp = dijit.byId('apellido1');
					if(!validarApe1(vApe1Emp)) return false;						
				} 				
				
				var dayiniadd = document.getElementById('diaSelect');
				var monthiniadd = document.getElementById('mesSelect');
				var yeariniadd = document.getElementById('anioSelect');		
				
				if (dayiniadd.value == 'Día') {
		    		alert('Es necesario seleccionar el dia de fecha de nacimiento.');
		    		dojo.byId('apellido2').focus();
					return false;
		    	}
		    	if (monthiniadd.value == 'Mes') {
		    		alert('Es necesario seleccionar el mes de fecha de nacimiento.');
		    		dojo.byId('apellido2').focus();
					return false;
		    	}
		    	if (yeariniadd.value == 'Año') {
		    		alert('Es necesario seleccionar el año de fecha de nacimiento.');
		    		dojo.byId('apellido2').focus();
					return false;
		    	}
		    	
				if (!validaCampoSelect('diaSelect')) return false;
				if (!validaCampoSelect('mesSelect')) return false;
				if (!validaCampoSelect('anioSelect')) return false;
				if (!validaCampoSelect('entidadNacimientoSelect')) return false;
				
			} else if(vIdTipoEmpresa == 2 && dojo.byId('idTipoPersonaMoral').checked){				
				
				if (!validaCampo('razonSocial')){
					var cRazon = dijit.byId('razonSocial');
					displayErrorMsg(cRazon,"Es necesario indicar la razón social.");										
					return false;
				} else {						
					var vRazonEmp = dijit.byId('razonSocial');
					if(!validarRazonSocial(vRazonEmp)) return false;						
				}
				
				var dayiniadd = document.getElementById('diaActaSelect');
				var monthiniadd = document.getElementById('mesActaSelect');
				var yeariniadd = document.getElementById('anioActaSelect');	
				
				if (dayiniadd.value == 'Día') {
		    		alert('Es necesario seleccionar el dia de fecha de acta constitutiva.');
		    		dojo.byId('razonSocial').focus();
					return false;
		    	}
		    	if (monthiniadd.value == 'Mes') {
		    		alert('Es necesario seleccionar el mes de fecha de acta constitutiva.');
		    		dojo.byId('razonSocial').focus();
					return false;
		    	}
		    	if (yeariniadd.value == 'Año') {
		    		alert('Es necesario seleccionar el año de fecha de acta constitutiva.');
		    		dojo.byId('razonSocial').focus();
					return false;
		    	} 
		    	
				if (!validaCampoSelect('diaActaSelect')) return false;
				if (!validaCampoSelect('mesActaSelect')) return false;
				if (!validaCampoSelect('anioActaSelect')) return false;
			
			} else if(vIdTipoEmpresa == 1){
				
				if (!validaCampo('razonSocialPublica')){
					var cRazon = dijit.byId('razonSocialPublica');
					displayErrorMsg(cRazon,"Es necesario indicar el nombre de la empresa o institución pública.");										
					return false;
				} else {						
					var vRazonEmp = dijit.byId('razonSocialPublica');
					if(!validarRazonSocial(vRazonEmp)) return false;						
				}				
				
			} else if(vIdTipoEmpresa == 3){
				
				if (!validaCampo('razonSocialOng')){
					var cRazon = dijit.byId('razonSocialOng');
					displayErrorMsg(cRazon,"Es necesario indicar el nombre de la organización.");										
					return false;
				} else {						
					var vRazonEmp = dijit.byId('razonSocialOng');
					if(!validarRazonSocial(vRazonEmp)) return false;						
				}					
			}
						
			var vDescripcion = document.getElementById('descripcion');
			if(!vDescripcion.value){	
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
			
			if (!validaCampo('contactoEmpresa')) {
				return false;
			} else {
				var vContactoEmp = dijit.byId('contactoEmpresa');
				if(!validarNombreContacto(vContactoEmp)) return false;	
			}		
			
			if (!validaCampo('cargoContacto')) {
				return false;
			} else {
				var vContactoEmp = dijit.byId('cargoContacto');
				if(!validarNombreContacto(vContactoEmp)) return false;	
			}				
			
			//CALS 07/11/2016
			if(!(vIdTipoEmpresa == 2 && dojo.byId('idTipoPersonaFisica').checked))
				if (!validaCampoSelect('tiposSociedadSelect')) return false;
			
			if (!validaCampoSelect('tiposEmpresaSelect')) return false;
			if (!validaCampoSelect('idSectorSelect')) return false;
			if (!validaCampoSelect('idSubsectorSelect')) return false;
			if (!validaCampoSelect('idRamaSelect')) return false;			
			if (!validaCampo('numeroEmpleados')) return false;
			
			var controlEmpleados = dijit.byId('numeroEmpleados');					
			if(parseInt(controlEmpleados.value)<1){
				dojo.byId('numeroEmpleados').focus();
				alertMsg('El número de empleados debe ser mayor a cero.');
				return false;				
			}				
			
			if (!validaCampoSelect('enterasteSelect')) return false;
			if (!validaCampo('calle')) return false;
			if (!validaCampo('numeroExterior')) return false;
			if (!validaCampoSelect('idEntidadSelect')) return false;
			if (!validaCampoSelect('idMunicipioSelect')) return false;
			if (!validaCampoSelect('idColoniaSelect')) return false;
			
			if (!hasTelPrinc()){
				alertMsg('Debe capturar al menos un teléfono');
				return false;
			} else {
				if(!dojo.byId('telefonoFijo').checked){			
					dojo.byId('clave').focus();
					alertMsg("El teléfono principal debe ser un teléfono fijo. ");
					return false;					
				}				
				var vClave = dijit.byId('clave');
				var vTelefono = dijit.byId('telefono');
				var vExtension = dijit.byId('extension');
				if (!validaCampo('clave')) return false;
				if (!validaCampo('telefono')) return false;
				if(vClave.value.length == 2) {
					if(vTelefono.value.length != 8){
						alertMsg('Debe proporcionar un teléfono de 8 dígitos');
						vClave.focus();
						return false;
					}
				} else if(vClave.value.length==3){
					if(vTelefono.value.length!=7){
						alertMsg('Debe proporcionar un teléfono de 7 dígitos');
						vClave.focus();
						return false;
					}	
				}		
				if(vExtension.value.length>0 ){
					if (!validaCampo('extension')){
						alertMsg('La extension no es válida.');
						vClave.focus();
						return false;							
					}				
				}
				//telefonos adicionales 					
				if(document.getElementById('claveTelefonoAdicional_2')){
					var vAddClave2  = document.getElementById('claveTelefonoAdicional_2');
					var vAddTelefono2  = document.getElementById('telefonoAdicional_2');	
					var vAddExtension2  = document.getElementById('extensionTelefonoAdicional_2');	
					if (!validarClaveLada(vAddClave2, 2)) return false;
					if (!validarFormatoTelefono(vAddTelefono2, 2)) return false;
					if(vAddClave2.value.length == 2) {
						if(vAddTelefono2.value.length != 8){
							alertMsg('Debe proporcionar un teléfono de 8 dígitos en el segundo teléfono adicional');
							return false;
						}
					} else if(vAddClave2.value.length==3){
						if(vAddTelefono2.value.length!=7){
							alertMsg('Debe proporcionar un teléfono de 7 dígitos en el segundo teléfono adicional');
							return false;
						}	
					}
					if(vAddExtension2.value.length>0){
						if (!validarExtension(vAddExtension2, 2)) return false;
					}
				}				
				if(document.getElementById('claveTelefonoAdicional_1')){
					var vAddClave1  = document.getElementById('claveTelefonoAdicional_1');
					var vAddTelefono1  = document.getElementById('telefonoAdicional_1');
					var vAddExtension1  = document.getElementById('extensionTelefonoAdicional_1');
					if (!validarClaveLada(vAddClave1,1)) return false;
					if (!validarFormatoTelefono(vAddTelefono1,1)) return false;
					if(vAddClave1.value.length == 2) {
						if(vAddTelefono1.value.length != 8){
							alertMsg('Debe proporcionar un teléfono de 8 dígitos en el primer teléfono adicional');
							vAddClave1.focus();
							return false;
						}
					} else if(vAddClave1.value.length==3){
						if(vAddTelefono1.value.length!=7){
							alertMsg('Debe proporcionar un teléfono de 7 dígitos en el primer teléfono adicional');
							vAddClave1.focus();
							return false;
						}	
					}		
					if(vAddExtension1.value.length>0){
						if (!validarExtension(vAddExtension1, 1)) return false;
					}					
				}
			}			
		}
		return true;
	}	
	
	
	function validaCampo(campo){
		var control = dijit.byId(campo);		
		
		/*if (control && control.value==''){
			dojo.byId(control).focus();
			alertMsg("Es necesario indicar el (la) " + 
					control.get("missingMessage"));
			return false;
		}*/
		
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
	
	function mensaje(mensaje) {	
		dojo.byId('mensaje').innerHTML = mensaje;
		dijit.byId('MensajeAlert').show();
	}	
	
	function checkEntidad(){
		var vEntidad = dijit.byId('idEntidadSelect').get('value');
		if (!vEntidad){
			dojo.byId('codigoPostal').value = '';				
			dijit.byId('yCalle').focus();		
			alertMsg("Por favor seleccione alguna de las opciones de los listado de entidades.");				
		}
	}
	
	function checkMunicipio(){
		var vMunicipio = dijit.byId('idMunicipioSelect').get('value');
		if (!vMunicipio){
			dojo.byId('codigoPostal').value = '';				
			dijit.byId('yCalle').focus();		
			alertMsg("Por favor seleccione alguna de las opciones de los listado de municipios y delegaciones.");				
		}
	}	
	
	function checkColonia(){
		var vColonia   = dijit.byId('idColoniaSelect').get('value');
		if (!vColonia){
			dojo.byId('codigoPostal').value = '';				
			dijit.byId('yCalle').focus();		
			alertMsg("Por favor seleccione alguna de las opciones de los listado de colonias.");				
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
		} else {
			dojo.byId('codigoPostal').value = '';	
			dijit.byId('yCalle').focus();					
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
			dijit.byId('idColoniaSelect').focus();	
		} else {
			dojo.byId('codigoPostal').value = '';	
			dijit.byId('idEntidadSelect').focus();		
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
						   habilitarGeolocalizacion();
					    }
				});
		} else {
			dojo.byId('codigoPostal').value = '';	
			dijit.byId('idEntidadSelect').focus();	
		}		
	}	
	
	function actualizaSubsector() {		
		var vSector = dijit.byId('idSectorSelect').get('value');
		if (vSector){
			subsectorStore.url = '';
			subsectorStore.close();			

			ramaStore.url = '';
			ramaStore.close();
			
			dijit.byId('idSubsectorSelect').reset();
			dijit.byId('idRamaSelect').reset();
			
			subsectorStore.url = "${context}registroEmpresa.do?method=tiposSubsector" + "&" + "idSector=" + vSector;
			subsectorStore.close();		
		} else {	
			dijit.byId('yCalle').focus();					
		}
	}	
	
	function actualizaRama() {		
		var vSubsector = dijit.byId('idSubsectorSelect').get('value');
		if (vSubsector){		

			ramaStore.url = '';
			ramaStore.close();
			
			dijit.byId('idRamaSelect').reset();
			
			ramaStore.url = "${context}registroEmpresa.do?method=tiposActividadEconomica" + "&" + "idSubsector=" + vSubsector;
			ramaStore.close();		
		} else {	
			dijit.byId('yCalle').focus();					
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
		if (dojo.byId('correoElectronico').value==''){
			alertMsg(control.get("invalidMessage"));
			return false;
		}		
		dojo.xhrPost({url: 'registroEmpresa.do?method=validaCorreoElectronicoUnico', form:'registroEmpresaForm', sync: true, 
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
		if (!validaCampo('clave') ||
					!validaCampo('telefono')){
			return false;			
		}
		return true;
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
	
	function clearNumeroEmpleados(){	
		var vNumeroEmpleados = dijit.byId('numeroEmpleados');					
		if(vNumeroEmpleados.value.length > 0 && parseInt(vNumeroEmpleados.value)<1){
			dojo.byId('numeroEmpleados').value = '';		
		}		
	}
	
	function revisarOrtografia(){
		//alert('Ortografia revisada');
	}

	/*
	dojo.addOnLoad(function(){
		alertMsg(dijit.byId('apellido2').get("invalidMessage"));
	});
	*/
	
	/*function alertMsg(msg){
		alert(msg);
	}*/
	
	function alertMsg(msg){
		
		var idTag = "mensajeEmergente";

		if (dijit.byId(idTag) == null){

			var html = "<div class=\"msg_contain\">"+"<p>"+ msg +"</p>"+"</div>"+
			   	       "<p class=\"form_nav\"><button onclick=\"dijit.byId('"+idTag+"').hide();\">Cerrar</button></p>";

			dialogMensaje = new dijit.Dialog({
			         title: 'Mensaje',
					 id: idTag,			        
					content: html,
			        style: "width:350px;",
			        showTitle: true, draggable : true, closable : true
			});
			
			//dojo.style(dialogMensaje.closeButtonNode,"display","none");
		}

		dijit.byId(idTag).show();
	}
	
	function habilitarGeolocalizacion(){
		<c:if test="${registroEmpresaForm.permisoGeolocalizacion}">
			var address = null;
			if($('#calle').val() && $('#numeroExterior').val() && $('#codigoPostal').val()){
				address = $('#calle').val()+' '+
						  $('#numeroExterior').val()+', '+
						  $('#idColoniaSelect').val()+', '+
						  $('#idMunicipioSelect').val()+', '+
						  $('#idEntidadSelect').val();
				if(!map){
					loadGoogleMaps();				
				}
				geocodeAddress(map, address);
				$('#fieldGeoreferencia').slideDown();
				$('#btnBuscarByDireccion').show();
			}else{
				$('#btnBuscarByDireccion').hide();
			}
		</c:if>
	}
	
	function ubicarDireccionUsuario(){
		<c:if test="${registroEmpresaForm.permisoGeolocalizacion}">
			var address =address = $('#calle').val()+' '+
			  $('#numeroExterior').val()+', '+
			  $('#idColoniaSelect').val()+', '+
			  $('#idMunicipioSelect').val()+', '+
			  $('#idEntidadSelect').val();
	  		geocodeAddress(map, address);
  		</c:if>
  	}

</script>	





<c:if test="${not empty ERROR_MSG}">
	<script type="text/javascript">
		dojo.addOnLoad(function(){
			var html = '${ERROR_MSG}'+
					   '<br/><br/>'+
					   '<input id="btnCerrar" name="btnCerrar" class="boton_naranja" type="button" value="Cerrar" onclick="cerrarError();"/>';
			
		alertMsg('${ERROR_MSG}');
		});
	</script>
	
<noscript>Funciones de JavaScript desactivadas por navegador</noscript>

</c:if>

<div dojoType="dojo.data.ItemFileReadStore" jsId="entidadFederativaStore"    	urlPreventCache="true" clearOnClose="true" ></div>
<div dojoType="dojo.data.ItemFileReadStore" jsId="municipioStore"            	urlPreventCache="true" clearOnClose="true" ></div>
<div dojoType="dojo.data.ItemFileReadStore" jsId="coloniaStore"              	urlPreventCache="true" clearOnClose="true" ></div>
<div dojoType="dojo.data.ItemFileReadStore" jsId="diasStore"                 	url="${context}registroEmpresa.do?method=dias"></div>
<div dojoType="dojo.data.ItemFileReadStore" jsId="mesesStore"                	url="${context}registroEmpresa.do?method=meses"></div>
<div dojoType="dojo.data.ItemFileReadStore" jsId="aniosStore"                	url="${context}registroEmpresa.do?method=anios"></div>
<div dojoType="dojo.data.ItemFileReadStore" jsId="tiposEmpresaStore"         	url="${context}registroEmpresa.do?method=tiposEmpresa"></div>
<div dojoType="dojo.data.ItemFileReadStore" jsId="tiposSociedadStore"         	url="${context}registroEmpresa.do?method=tiposSociedad"></div>
<div dojoType="dojo.data.ItemFileReadStore" jsId="sectorStore"  			  	urlPreventCache="true" clearOnClose="true" ></div>
<div dojoType="dojo.data.ItemFileReadStore" jsId="subsectorStore"            	urlPreventCache="true" clearOnClose="true" ></div>
<div dojoType="dojo.data.ItemFileReadStore" jsId="ramaStore"	              	urlPreventCache="true" clearOnClose="true" ></div>
<div dojoType="dojo.data.ItemFileReadStore" jsId="portalEnterasteStore" 		url="${context}registroEmpresa.do?method=medioPortal"></div>
<div dojoType="dojo.data.ItemFileReadStore" jsId="aniosActaStore"               url="${context}registroEmpresa.do?method=aniosactual"></div>
<div dojoType="dojo.data.ItemFileReadStore" jsId="entidadNacimientoStore"		urlPreventCache="true" clearOnClose="true" ></div>

<jsp:useBean id="fecha" scope="page" class="java.util.Date"/>
<c:set var="anioactual"><fmt:formatDate pattern="yyyy" value="${fecha}"/></c:set>
<c:set var="anionacimiento" value="${registroEmpresaForm.anio}"/>

<form name="registroEmpresaForm" id="registroEmpresaForm" method="post" action="registroEmpresa.do" dojoType="dijit.form.Form">
	<input type="hidden" name="method" 						id="method" 					value="init" 									dojoType="dijit.form.TextBox"/>	
	<input type="hidden" name="cuentaRegenerada" 			id="cuentaRegenerada" 			value="${registroEmpresaForm.cuentaRegenerada}" dojoType="dijit.form.TextBox"/>	
	<input type="hidden" name="tieneOfertas" 				id="tieneOfertas" 				value="${registroEmpresaForm.tieneOfertas}" 	dojoType="dijit.form.TextBox"/>		
	<input type="hidden" name="idTipoEmpresa"   			id="idTipoEmpresa"   			value="" 										dojoType="dijit.form.TextBox"/>
	<input type="hidden" name="idTipoSociedad"   			id="idTipoSociedad"   			value="" 										dojoType="dijit.form.TextBox"/>
	<input type="hidden" name="idTipoActividadEconomica"   	id="idTipoActividadEconomica"   value="" 										dojoType="dijit.form.TextBox"/>
	<input type="hidden" name="idMedio"   					id="idMedio"   					value="" 										dojoType="dijit.form.TextBox"/>
 	<input type="hidden" name="dia" 						id="dia" 						value="${registroEmpresaForm.dia}" 				dojoType="dijit.form.TextBox"/>
 	<input type="hidden" name="mes" 						id="mes" 						value="${registroEmpresaForm.mes}" 				dojoType="dijit.form.TextBox"/>
 	<input type="hidden" name="anio" 						id="anio" 						value="${registroEmpresaForm.anio}" 			dojoType="dijit.form.TextBox"/>
	<input type="hidden" name="idEntidad"   				id="idEntidad" 			 		value="" 										dojoType="dijit.form.TextBox"  />
	<input type="hidden" name="idMunicipio"					id="idMunicipio"   		 		value="" 										dojoType="dijit.form.TextBox"/>
	<input type="hidden" name="idColonia"   				id="idColonia" 					value="" 										dojoType="dijit.form.TextBox"/>
	<input type="hidden" name="idphone" 					id="idphone" 			 		value="" />		
   	<input type="hidden" name="acceso" 						id="acceso"   					value="${registroEmpresaForm.acceso}" 				dojoType="dijit.form.TextBox"/>
    <input type="hidden" name="accesoAdd" 					id="accesoAdd"   				value="" 											dojoType="dijit.form.TextBox"/>
    <input type="hidden" name="idTipoTelefonoAdd" 			id="idTipoTelefonoAdd"   		value="${registroEmpresaForm.idTipoTelefonoFijo}"	dojoType="dijit.form.TextBox"/>
	<input type="hidden" name="idEntidadNacimiento"   		id="idEntidadNacimiento" 		value="" 										dojoType="dijit.form.TextBox"  />
 	
 	
 	
<div class="formApp">
<div class="flow_1">Registro de Empresas</div>
<div class="form_wrap">
<p class="instruc_01"><strong>¡Regístrate en 2 sencillos pasos!</strong></p>
<div class="form_edge">
<div class="stepApp">
	<h2><img src="css/images/paso_2B.png" alt="Paso 2 de 2. Perfil de la empresa"></h2>
	<p>Los datos marcados con <span>*</span> son obligatorios</p>
</div>
<div class="user_dt form_nom">
	<h3>Datos de la empresa</h3>
</div>

	<fieldset class="field_curp">
		<legend>Tipo de empresa</legend> 
	    <div class="grid1_3 margin_top_10">
			<label for="tiposEmpresaSelect">Tipo de empresa</label>
			<p class="labeled"><span>*</span> Selecciona un tipo de empresa</p>
				<select id="tiposEmpresaSelect" name="tiposEmpresaSelect"
						required="true" missingMessage="tipo de empresa" 
				        store="tiposEmpresaStore" dojotype="dijit.form.FilteringSelect"
				        value="${registroEmpresaForm.idTipoEmpresa}" onchange="javascript:actualizaCamposRelacionadosConTipoEmpresa();" >
				</select>
		</div>
		
	    <div class="tipo_persona" id="divEmpresaPrivada" style="display:none">  	   
			<div class="labeled">Tipo de persona</div>  
			<p class="labeled"><span>*</span> Selecciona un tipo de persona</p>
			<div>
	        <c:choose>
	        	<c:when test="${registroEmpresaForm.idTipoEmpresa eq '2' and registroEmpresaForm.idTipoPersona eq '2'}">
		            <input type="radio" name="idTipoPersona" id="idTipoPersonaMoral" onclick="actualizaTipoPersona()" value="2" checked="checked" />
		            <label for="idTipoPersonaMoral">&nbsp;Moral</label><br>     	   
					<input type="radio" name="idTipoPersona" id="idTipoPersonaFisica" onclick="actualizaTipoPersona()" value="1" />
					<label for="idTipoPersonaFisica">&nbsp;Física</label>
	        	</c:when>
	        	<c:otherwise>
		            <input type="radio" name="idTipoPersona" id="idTipoPersonaMoral" onclick="actualizaTipoPersona()" value="2"/>
		            <label for="idTipoPersonaMoral">&nbsp;Moral</label><br>       	   
					<input type="radio" name="idTipoPersona" id="idTipoPersonaFisica" onclick="actualizaTipoPersona()" value="1" checked="checked" />
					<label for="idTipoPersonaFisica">&nbsp;Física</label>
	        	</c:otherwise>
	        </c:choose>	
	        </div>
		</div>
		    		    
	</fieldset>	    

	
	<fieldset id="porCurp" style="display:none">
		<legend>CURP</legend>
		<div class="tipo_persona margin_top_30" id="idConocesCurp" style="display:block" >
			<div class="labeled"><span>*</span> ¿Conoces tu CURP?</div>
			
			<c:choose>
				<c:when test="${registroEmpresaForm.idConocesTuCurp eq '2'}">
					<input class="fl" type="radio" name="idConocesTuCurp" id="idConocesTuCurpSi" onclick="actualizaConoceCurp()" value="2" checked="checked" />
					<label for="idConocesTuCurpSi">&nbsp;Sí</label><br>
					<input class="fl" type="radio" name="idConocesTuCurp" id="idConocesTuCurpNo" onclick="actualizaConoceCurp()" value="1" />
					<label for="idConocesTuCurpNo">&nbsp;No</label>					
	        	</c:when>
	        	<c:otherwise>	
					<input class="fl" type="radio" name="idConocesTuCurp" id="idConocesTuCurpSi" onclick="actualizaConoceCurp()" value="2" />
					<label for="idConocesTuCurpSi">&nbsp;Sí</label><br>
					<input class="fl" type="radio" name="idConocesTuCurp" id="idConocesTuCurpNo" onclick="actualizaConoceCurp()" value="1"  checked="checked" />
					<label for="idConocesTuCurpNo">&nbsp;No</label>					        	
	        	</c:otherwise>			
			</c:choose>			
		</div>
		
		<div class="margin_top_20" id="divCurp" style="display:none">
			<label for="curp"><span>*</span> CURP</label>
			<p>La Clave Única de Registro de Población (CURP), te servirá como identificador para tu registro al Portal del Empleo y Servicio Nacional de Empleo, por lo que debes proporcionarla.
			Por favor ingresa los siguientes datos:</p>
            <input type="text" id="curp"  name="curp" 
	               dojoType="dijit.form.ValidationTextBox" 
	               required="true"      
	               invalidMessage="campo CURP."  
	               maxlength="18" uppercase="true" clearOnClose="true" trim="true"
	               value="${registroEmpresaForm.curp}" />
		</div>
			
	    <div class="datos_01" id="curpSoloLectura" style="display:block">
	        <strong class="user_name">
	        	<span id="readOnlyNombre"></span> <span id="readOnlyApellido1"></span> <span id="readOnlyApellido2"></span>
	        </strong>
	        <p align="center">CURP: <strong><span id="readOnlyCurp"></span></strong>, <span id="readOnlySexo"></span>, <span id="readOnlyEdad"></span> años, 
	        Lugar de nacimiento: <span id="readOnlyEntidadNacimiento"></p>				
	    </div>										
	</fieldset>				
	
	<fieldset id="personaFisica"  style="display:none">
		<!-- <legend>Persona Física</legend> -->
		<p>La Clave Única de Registro de Población (CURP), te servirá como identificador para tu registro al Portal del Empleo y Servicio Nacional de Empleo, por lo que debes proporcionarla.
			Por favor ingresa los siguientes datos:</p>
		<div class="grid1_3 margin_top_10 fl">
			<label for="nombre"><span>*</span> Nombre(s)</label>
			<input type="text" id="nombre" name="nombre" 
		              dojoType="dijit.form.ValidationTextBox"   
		              required="false"          missingMessage="nombre."
		              regExp="${regexpnombre}" invalidMessage="Nombre inválido, no se permiten números ni caracteres especiales y la longitud mínima es 1 caracter." 
		              maxlength="50" uppercase="true" trim="true"  
		              value="${registroEmpresaForm.nombre}"
		              onkeyup="limpiarEspacios(this)"/>
		</div>
		<div class="grid1_3 margin_top_10 fl">
			<label for="apellido1"><span>*</span> Primer apellido</label>
			<input type="text" id="apellido1" name="apellido1" 
		              dojoType="dijit.form.ValidationTextBox"   
		              required="false"          missingMessage="primer apellido."
		              regExp="${regexpapellido1}" invalidMessage="Apellido inválido, no se permiten números ni caracteres especiales y la longitud mínima es 2 caracteres." 
		              maxlength="50" uppercase="true" trim="true"  
		              value="${registroEmpresaForm.apellido1}"
		              onkeyup="limpiarEspacios(this)"/>
		</div>
		<div class="grid1_3 margin_top_10 margin_no_r fl">
			<label for="apellido2">Segundo apellido</label>
			<input type="text" id="apellido2" name="apellido2" 
		              dojoType="dijit.form.ValidationTextBox"  
		              required="false"          missingMessage="segundo apellido."
		              regExp="${regexpnombre}" invalidMessage="Apellido inválido, no se permiten números ni caracteres especiales y la longitud mínima es 2 caracteres." 
		              maxlength="50" uppercase="true" trim="true"   
		              value="${registroEmpresaForm.apellido2}"
		              onkeyup="limpiarEspacios(this)"/>
		</div>
		<div class="clearfix"></div>
		<div class="sexo">
			<div class="labeled"><span>*</span> Sexo</div>
			<div>
				<input type="radio" name="genero" id="idGeneroMasculino" value="1" checked="checked"/><label for="idGeneroMasculino">&nbsp;Hombre</label><br />
				<input type="radio" name="genero" id="idGeneroFemenino"  value="2"/><label for="idGeneroFemenino">&nbsp;Mujer</label>
			</div>
			<div class="clearfix"></div>
		</div>
		<div class="grid1_3 margin_top_10 fl">
			<div class="labeled"><span>*</span> Fecha de nacimiento</div>
			<div class="fl">
				<select id="diaSelect" name="diaSelect" 
						required="false" missingMessage="día de su nacimiento."
						invalidMessage="Día inválido, favor de verificar." 
						promptMessage="Día" maxHeight="250" 
						regExp="${regexpdia}"
			        	value="${registroEmpresaForm.dia}" autocomplete="true"
			        	dojotype="dijit.form.FilteringSelect" store="diasStore"></select>
			</div>
			<div class="fl">
				<select id="mesSelect" name="mesSelect"
					    required="false" missingMessage="mes de su nacimiento."
					    invalidMessage="Mes inválido, favor de verificar."
				    	promptMessage="Mes" maxHeight="250"
			        	value="${registroEmpresaForm.mes}" autocomplete="true"
			        	dojotype="dijit.form.FilteringSelect" store="mesesStore"></select>
			</div>
			<div class="fl">
				<select id="anioSelect" name="anioSelect"
						required="false" missingMessage="año de su nacimiento."
						invalidMessage="Año inválido, favor de verificar." 
						promptMessage="Año" maxHeight="250"
			        	value="${registroEmpresaForm.anio}" autocomplete="true"
			        	dojotype="dijit.form.FilteringSelect" store="aniosStore"></select>
			</div>
		</div>
		<div class="grid1_3 margin_top_30 fl">
				<label for="lugarNacimiento"><span>*</span> Lugar de nacimiento</label>
				<select id="entidadNacimientoSelect" name="entidadNacimientoSelect"
						required="true" missingMessage="Debe indicar la entidad de nacimiento."
						invalidMessage="Lugar de nacimiento inválido."
				        value="${registroEmpresaForm.idEntidadNacimiento}" autocomplete="true"
				        dojotype="dijit.form.FilteringSelect" store="entidadNacimientoStore">
				</select>
		</div>
		<div class="clearfix"></div>
	</fieldset>
	<div class="generalesOferta bloque">


<fieldset id="divRazonSocial" style="display:none">
	<legend>Identificación de la empresa</legend> 
		<div id="personaMoral" style="display:none">
			<div class="margin_top_10">
		        <div class="grid1_3 fl">
		            <label for="rfc"><span></span> RFC</label>
		            <input type="text" name="rfc" id="rfc"
		               required="false" missingMessage="RFC"
		               regExp="${regexprfc}" invalidMessage="RFC inválido favor de verificar."
		               value="${registroEmpresaForm.rfc}" 
		        	   maxlength="13" dojoType="dijit.form.ValidationTextBox"/>	    
		        </div>
		        
		        <div id="divNombrePublica" style="display:none" class="grid1_3 fl">
	        		<label for="razonSocialPublica"><span>*</span> Nombre de la empresa / Institución pública</label>
		            <input type="text" id="razonSocialPublica" name="razonSocialPublica" 
			              dojoType="dijit.form.ValidationTextBox" 
			              required="false"          missingMessage="nombre de la empresa."
			              regExp="${regexprazonsocial}" invalidMessage="Nombre de la empresa inválida, no se permiten números ni caracteres especiales y la longitud mínima es 3 caracteres." 
			              maxlength="150" uppercase="true" trim="true"   
			              value="${registroEmpresaForm.razonSocialPublica}"
			              onkeyup="limpiarEspacios(this)"/>	        		        			        
		        </div>
		        <div id="divNombreOng" style="display:none" class="grid1_3 fl">
	        		<label for="razonSocialOng"><span>*</span> Nombre de la organización</label>
		            <input type="text" id="razonSocialOng" name="razonSocialOng" 
			              dojoType="dijit.form.ValidationTextBox" 
			              required="false"          missingMessage="nombre de la organización."
			              regExp="${regexprazonsocial}" invalidMessage="Nombre de la organización inválida, no se permiten números ni caracteres especiales y la longitud mínima es 3 caracteres." 
			              maxlength="150" uppercase="true" trim="true"   
			              value="${registroEmpresaForm.razonSocialOng}"
			              onkeyup="limpiarEspacios(this)"/>	        			        				        
		        </div>
		        
		        <div id="divNombreComercial" style="display:none" class="grid1_3 fl">
		            <label for="nombreComercial"><span></span> Nombre comercial</label>
		            <input id="nombreComercial" name="nombreComercial" maxlength="150" size="50" 			
		        	dojoType="dijit.form.ValidationTextBox" required="false"  
		        	regExp="${regexpnombrecomercial}" invalidMessage="Nombre comercial inválido, no se permiten números ni caracteres especiales." 
		        	onpaste="return false;" uppercase="true"   		        	
		        	value="${registroEmpresaForm.nombreComercial}" 
		        	missingMessage="Dato requerido" trim="true"/>
		        </div>
		        <div class="clearfix"></div> 
		    </div>

		<div class="margin_top_20" id="divMoralRazonSocial" style="display:none">
	        <div class="grid1_3 fl">
	            <label id="lblRazonSocial" for="razonSocial"><span>*</span> Razón social</label>
	            <input type="text" id="razonSocial" name="razonSocial" 
		              dojoType="dijit.form.ValidationTextBox" 
		              required="false"          missingMessage="razón social."
		              regExp="${regexprazonsocial}" invalidMessage="Razón social inválida, no se permiten números ni caracteres especiales y la longitud mínima es 3 caracteres." 
		              maxlength="150" uppercase="true" trim="true"   
		              value="${registroEmpresaForm.razonSocial}"
		              onkeyup="limpiarEspacios(this)"/>
	        </div>
	        <div class="grid1_3 fl">
	            <label for="diaActaSelect"><span>*</span> Fecha de acta constitutiva</label>
	            <div class="fl">
	                <select id="diaActaSelect" name="diaActaSelect"
							required="false" missingMessage="día del acta constitutiva."
							invalidMessage="Día inválido, favor de verificar."
							promptMessage="Día" maxHeight="250"
				        	value="${registroEmpresaForm.diaActa}" autocomplete="true"
				        	dojotype="dijit.form.FilteringSelect" store="diasStore"></select>
	            </div>
	            <div class="fl">
	                <select id="mesActaSelect" name="mesActaSelect"
						    required="false" missingMessage="mes del acta constitutiva."
						    invalidMessage="Mes inválido, favor de verificar."
					    	promptMessage="Mes" maxHeight="250"
				        	value="${registroEmpresaForm.mesActa}" autocomplete="true"
				        	dojotype="dijit.form.FilteringSelect" store="mesesStore"></select>
	            </div>
	            <div class="fl">
	                <select id="anioActaSelect" name="anioActaSelect"
							required="false" missingMessage="año del acta constitutiva."
							invalidMessage="Año inválido, favor de verificar." 
							promptMessage="Año" maxHeight="250"
				        	value="${registroEmpresaForm.anioActa}" autocomplete="true"
				        	dojotype="dijit.form.FilteringSelect" store="aniosActaStore"></select>
	                </select>
	            </div>
	        </div>
	        <div class="clearfix"></div>
	    </div>
	    <div class="experiencia_01 margin_top_30">
                	<label for="descripcion"><span>*</span> Descripción de la empresa</label>
                	<textarea cols="124" rows="4"
                	style="height: 110px !important; max-height: 110px !important;" 
		              name="descripcion" id="descripcion" trim="true" required="true"invalidMessage="Es necesario ingresar la descripción de la empresa." onchange="return maximaLongitud(this,2000)" onKeyUp="return maximaLongitud(this,2000)"
		            	  regExp="^[A-Za-z\s\-.áéíóúÁÉÍÓÚñÑ0-9.,;:/']{1,2000}$" onkeypress="return caracteresValidos(event);" onpaste="return false;"
		            	  onblur="return maximaLongitud(this,2000)" class="textGoogie">${registroEmpresaForm.descripcion}		            	  		            	 
		            </textarea>		            		            	 
			    	<script type="text/javascript">
		         		var vSpellCon = new GoogieSpell("googiespell/", "<%=vProxy%>");
		         		vSpellCon.setLanguages({'es': 'Español'});
		         		vSpellCon.hideLangWindow();
		         		vSpellCon.decorateTextarea("descripcion");
					</script>
		</div>
		
		<div class="margin_40"></div>
				
		<div class="margin_top_10">
	        <div class="grid1_3 fl">
	            <label for="contactoEmpresa"><span>*</span> Nombre del representante de la empresa</label>
	            <input type="text" id="contactoEmpresa" name="contactoEmpresa" 
	              dojoType="dijit.form.ValidationTextBox" 
	              required="true"  missingMessage="nombre del representante de la empresa."
	              regExp="${regexpnombrecontacto}" invalidMessage="Nombre de representante inválido, no se permiten números ni caracteres especiales y la longitud mínima es 1 caracter." 
	              maxlength="50" uppercase="true" trim="true" 
	              value="${registroEmpresaForm.contactoEmpresa}"
	              onkeyup="limpiarEspacios(this)"/> 
	        </div>
	        <div class="grid1_3 fl">
	            <label><span>*</span> Cargo del contacto</label>
	            <input type="text" id="cargoContacto" name="cargoContacto" 
	              dojoType="dijit.form.ValidationTextBox" 
	              required="true"  missingMessage="cargo del contacto."
	              regExp="${regexpnombrecontacto}" invalidMessage="Cargo del contacto inválido, no se permiten números ni caracteres especiales y la longitud mínima es 1 caracter." 
	              maxlength="50" uppercase="true" trim="true" 
	              value="${registroEmpresaForm.cargoContacto}"
	              onkeyup="limpiarEspacios(this)"/> 
	        </div>
	        <div id="tiposSociedadDiv" class="grid1_3 fl margin_no_r">
	            <label><span>*</span> Tipo de sociedad</label>
				<select id="tiposSociedadSelect" name="tiposSociedadSelect"
						required="true" missingMessage="tipo de sociedad" 
				        store="tiposSociedadStore" dojotype="dijit.form.FilteringSelect"
				        value="${registroEmpresaForm.idTipoSociedad}"  >
				</select>
	        </div>
	        <div class="clearfix"></div>
	    </div>
	</div>
</fieldset>	

<fieldset id="divActividadEconomica" style="display:block">
	<legend>Actividad económica principal</legend>
	<!-- <p class="labeled">Selecciona una opción de cada uno de los siguientes campos</p>-->
	<div class="grid1_3 fl">
		<label for="idSectorSelect"><span>*</span> Sector</label>
		<select id="idSectorSelect" name="idSectorSelect"
			dojotype="dijit.form.FilteringSelect" store="sectorStore" scrollOnFocus="true" maxHeight="250" 
			required="true"  missingMessage="sector" 
			tabindex="0" autocomplete="true" onchange="javascript:actualizaSubsector();" >					
		</select>
	</div>
	<div class="grid1_3 fl">
		<label for="idSubsectorSelect"><span>*</span> Subsector</label>
		<select id="idSubsectorSelect" name="idSubsectorSelect"
			dojotype="dijit.form.FilteringSelect" store="subsectorStore" scrollOnFocus="true" maxHeight="250" 
			required="true"  missingMessage="subsector" 
			tabindex="0" autocomplete="true" onchange="javascript:actualizaRama();" >
		</select>
	</div>
	<div class="grid1_3 fl margin_no_r">
		<label for="idRamaSelect"><span>*</span> Rama</label>
		<select id="idRamaSelect" name="idRamaSelect"
			dojotype="dijit.form.FilteringSelect" store="ramaStore" scrollOnFocus="true" maxHeight="250" 
			required="true"  missingMessage="rama" 
			tabindex="0" autocomplete="true" >
		</select>
	</div>	
	<div class="clearfix"></div>
	<div class="grid1_3 margin_top_20">					 	
		<label for="numeroEmpleados"><span>*</span> Número de empleados</label>
		<input type="text" name="numeroEmpleados" id="numeroEmpleados" value="${registroEmpresaForm.numeroEmpleados}"
			dojoType="dijit.form.ValidationTextBox" onfocus="clearNumeroEmpleados()"
			maxlength="5" style="width:8em;" trim="true" 
			required="true" missingMessage="número de empleados"
			regExp="${regexpnumeroEmpleados}" invalidMessage="Número de empleados es inválido." />
	</div>	
	<div class="grid1_3 margin_top_20">	
		<label for="enterasteSelect"><span>*</span> ¿Cómo se enteró del portal del empleo?</label>
		<select id="enterasteSelect" name="enterasteSelect"
			required="true" missingMessage="¿Cómo se enteró del portal del empleo?"
			store="portalEnterasteStore" dojotype="dijit.form.FilteringSelect"
			value="${registroEmpresaForm.idMedio}">
		</select>
	</div>
</fieldset>

	<%-- Lo sacamos del fieldset divActividadEconomica para no condicionarlo --%>
	<div id="holderMensaje" class="holder_mensaje" style="display:none; text-align: center;">
		<div class="posicion">
		<p class="mensaje_error" id="errorMsgArea">
			<input id="btnCerrar" name="btnCerrar" class="boton_naranja" type="button" value="Cerrar" onclick="cerrarError();"/>
		</p>
		</div>
	</div>		

<h3 id="h3Domicilio" style="display:block">Domicilio de la empresa</h3>
<fieldset id="divDomicilio" style="display:block">	
	<legend>Domicilio</legend>
	<div class="grid1_3 margin_top_10 fl">
		<label for="calle"><span>*</span> Calle</label>
		<input type="text" id="calle" name="calle" value="${registroEmpresaForm.calle}"
			dojoType="dijit.form.ValidationTextBox"
			maxlength="150" size="60" uppercase="true" trim="true"
			required="true" missingMessage="calle." 
			regExp="${regexpcalle}" invalidMessage="La calle es inválida, favor de verificar" 
			onchange="habilitarGeolocalizacion();" />
	</div>
	<div class="margin_top_10 fl">
		<div class="fl">
			<label for="numeroExterior"><span>*</span> Número exterior</label>
			<input type="text" id="numeroExterior" name="numeroExterior" maxlength="50" size="4" trim="true"
				   value="${registroEmpresaForm.numeroExterior}" dojoType="dijit.form.ValidationTextBox"
				   required="true"          missingMessage="número exterior." 
				   regExp="${regexpnumext}" invalidMessage="Número exterior inválido."
				   onchange="habilitarGeolocalizacion();" />
		</div>
		<div class="fl">
			<label for="numeroInterior"><span></span> Número interior</label>
			<input type="text" id="numeroInterior" name="numeroInterior" maxlength="50" size="4" trim="true" 
				   value="${registroEmpresaForm.numeroInterior}" dojoType="dijit.form.ValidationTextBox"
				   required="false" 
				   regExp="${regexpnumint}" invalidMessage="Número interior inválido."/>
		</div>
	</div>
	<div class="clearfix"></div>
	<div class="margin_top_10 ubicacion_1">
		<div class="grid1_3 fl">
			<label for="entreCalle">Entre qué calles</label>
			<input type="text" id="entreCalle" name="entreCalle" value="${registroEmpresaForm.entreCalle}"
			       dojoType="dijit.form.ValidationTextBox"
			       maxlength="150" size="60" uppercase="true" trim="true"
			       regExp="${regexpcalle}" invalidMessage="La calle es inválida, favor de verificar" /> 
		</div>
		<div class="y"><label for="yCalle">y</label></div>
		<div class="grid1_3 margin_top_30 fl">
			<input type="text" id="yCalle" name="yCalle" value="${registroEmpresaForm.yCalle}"
			       dojoType="dijit.form.ValidationTextBox"
			       maxlength="150" size="60" uppercase="true" trim="true"
			       regExp="${regexpcalle}" invalidMessage="La calle es inválida, favor de verificar" />
		</div>
	</div>
	<div class="clearfix"></div>
	<div class="grid1_3 margin_top_10 fl">
		<label for="idEntidadSelect"><span>*</span> Entidad </label>
		<select id="idEntidadSelect" name="idEntidadSelect"
			dojotype="dijit.form.FilteringSelect" store="entidadFederativaStore" scrollOnFocus="true" maxHeight="250" 
			required="true"  missingMessage="entidad federativa." 
			tabindex="0" autocomplete="true" 
			onchange="javascript:actualizaMunicipio();" onblur="javascript:checkEntidad();" >
		</select>
	</div>
	<div class="grid1_3 margin_top_10 fl">
		<label for="idMunicipioSelect"><span>*</span> Municipio o delegación</label>
		<select id="idMunicipioSelect" name="idMunicipioSelect" 
			dojotype="dijit.form.FilteringSelect" store="municipioStore" scrollOnFocus="true" maxHeight="250"
			required="true" missingMessage="municipio o delegación." 
			autocomplete="true"
			onchange="javascript:actualizaColonia();" onblur="javascript:checkMunicipio();" >
		</select>
	</div>
	<div class="grid1_3 fl margin_top_10 margin_no_r">
		<label for="idColoniaSelect"><span>*</span> Colonia</label>
		<select id="idColoniaSelect" name="idColoniaSelect" 
			dojotype="dijit.form.FilteringSelect" store="coloniaStore" scrollOnFocus="true" maxHeight="250"
			required="true" missingMessage="colonia."
			autocomplete="true"
			onchange="javascript:actualizaCodigoPostal();" onblur="javascript:checkColonia();"  >
		</select>					
	</div>
	<div class="clearfix"></div>
	<div class="grid1_3 margin_top_10 fl">
		<label for="codigoPostal">Código postal</label>
		<input type="text" name="codigoPostal" id="codigoPostal"
			dojoType="dijit.form.ValidationTextBox" readonly="readonly"
			maxlength="5" style="width:7em;" trim="true"
			required="false"      missingMessage="código postal."
			regExp="${regexpcp}" invalidMessage="Código postal incorrecto, solo se admiten caracteres numéricos." />
	</div>
</fieldset>
<fieldset id="fieldGeoreferencia" class="datosContacto" style="display: none;">
	<legend>Georeferencia</legend>
	<input id="txtLat" type="hidden" name="latitud" value="" dojoType="dijit.form.TextBox"/>
    <input id="txtLng" type="hidden" name="longitud" value="" dojoType="dijit.form.TextBox"/>
    
    <input id="pac-input" class="controls" type="hidden" placeholder="Indique una dirección... " autocomplete="off" />
	<div id="map" style="width: 100%; height: 400px;"></div>
	
	<input id="btnCurrentLocation" type="button" value="Obtener ubicación por Navegador" 
		style="font-size: 12px; margin: 0px;" class="boton_naranja"
		onclick="loadCurrentLocation();"/>
	
	<input id="btnBuscarByDireccion" type="button" value="Obtener por domicilio registrado" 
		style="font-size: 12px; margin: 0px;" class="boton_naranja"
		onclick="ubicarDireccionUsuario();"/>
		
</fieldset>


<h3 id="h3DatosContacto" style="display:block">Datos de contacto</h3>
<fieldset id="divDatosContacto" style="display:block">
	<legend>Teléfono de la empresa</legend>	
		<div class="labeled margin_top_10"><span>*</span> Teléfono</div>
		<p><em>Debes ingresar un total de 10 dígitos (Clave LADA + Teléfono). Tu primer registro debe ser un número fijo.</em></p>
		<div class="grid1_3  margin_top_20 fl">
			<div class="margin-r_20 fl">
				<div class="labeled margin_no_t"><span>*</span> Tipo de teléfono</div>
				<div style="width:53px" class="tipo_tel margin_top_10 fl">
					<input type="radio" name="tipoTelefono" id="telefonoFijo" checked="checked" 
				       value="${registroEmpresaForm.idTipoTelefonoFijo}" onclick="estableceAcceso()"><label class="fl" for="telefonoFijo">Fijo</label>
				</div>
				<div style="width:60px" class="tipo_tel margin_top_10 fl">
					<input type="radio" name="tipoTelefono" id="telefonoCelular" disabled="disabled"
			    	   value="${registroEmpresaForm.idTipoTelefonoCelular}" onclick="estableceAcceso()"><label class="fl" for="telefonoCelular">Celular</label>
				</div>
			</div>
			<div class="margin-r_10 fl">
				<label class="fw_no_bold"><span>*</span> Acceso</label>
				<div class="ta_center margin_top_10">
					<span id="accesoDiv">
						<c:if test="${empty registroEmpresaForm.acceso}">${registroEmpresaForm.accesoFijo}</c:if>
						<c:if test="${not empty registroEmpresaForm.acceso}">${registroEmpresaForm.acceso}</c:if>
					</span>
				</div>
			</div>
			<div class="fl">
				<label for="clave"><span>*</span> Clave LADA</label>
		         	<input type="text" name="clave" id="clave" maxlength="3" style="width:3em;" value="${registroEmpresaForm.clave}" dojoType="dijit.form.ValidationTextBox" 
		         	required="true" regExp="^[+]?\d{2,3}$" invalidMessage="Clave lada inválida" missingMessage="clave lada" trim="true" onBlur="changePhoneSizeRequired();" >
			</div>
		</div>
		<div class="margin_top_20 fl">
			<label for="telefono"><span>*</span> Teléfono</label>
	     	<input type="text" name="telefono" id="telefono" maxlength="8" style="width:8em;" value="${registroEmpresaForm.telefono}" onBlur="changePhoneSizeRequired();" 
	     	dojoType="dijit.form.ValidationTextBox" required="true" regExp="^[+]?\d{7,8}$" 
	     	invalidMessage="Teléfono inválido" missingMessage="teléfono" trim="true" onBlur="changePhoneSizeRequired();" >	
		</div>
		<div class="margin_top_20 fl">
			<label for="extension"><span></span> Extensión</label>
            <input class="sugerido" type="text" name="extension" id="extension" maxlength="8" style="width:8em;" 
            	value="${registroEmpresaForm.extension}" dojoType="dijit.form.ValidationTextBox" required="false" 
            	regExp="^[+]?\d{0,8}$" invalidMessage="Extensión telefónica inválida" missingMessage="extensión telefónica" trim="true">
		</div>
		<div id="agregaTelefono" name="agregaTelefono">
			<jsp:include page="/jsp/company/registro/phonetable.jsp" flush="true" />  
		</div>
		<div class="clearfix"></div>
		<div id="telefonoAdicional" class="otra_opcion" style="display: none;">
			<div class="labeled margin_top_10">Teléfono adicional</div>
			
			
			<div class="grid1_3  margin_top_20 fl">
				<div class="margin-r_20 fl">
					<div class="labeled margin_no_t"><span>*</span> Tipo de teléfono</div>
					<div style="width:53px" class="tipo_tel margin_top_10 fl">
						<input type="radio" name="tipoTelefonoAdd" id="telefonoFijoAdd" value="${registroEmpresaForm.idTipoTelefonoFijo}" onclick="estableceAccesoAdd('accesoAdd','idTipoTelefonoAdd',0,'telefonoFijoAdd','telefonoCelularAdd','extensionDivAddFijo','extensionDivAddCelular')">
						<label class="fl" for="telefonoFijoAdd">Fijo</label>
					</div>
					<div class="tipo_tel margin_top_10 fl">
						<input type="radio" name="tipoTelefonoAdd" id="telefonoCelularAdd" value="${registroEmpresaForm.idTipoTelefonoCelular}" onclick="estableceAccesoAdd('accesoAdd','idTipoTelefonoAdd',0,'telefonoFijoAdd','telefonoCelularAdd','extensionDivAddFijo','extensionDivAddCelular')">
		   	   			<label class="fl" for="telefonoCelularAdd">Celular</label>
					</div>
				</div>
				<div class="margin-r_10 fl">
					<label class="fw_no_bold"><span>*</span> Acceso</label>
					<div class="ta_center margin_top_10">
						<span id="accesoDivAdd_0">
							${registroEmpresaForm.acceso}
						</span>
					</div>
				</div>
				<div class="fl">
					<label for="claveAdd"><span>*</span> Clave LADA</label>
					<input type="text" id="claveAdd" name="claveAdd" 
				       dojoType="dijit.form.ValidationTextBox"
				       maxlength="3" style="width:3em;" trim="true"
				       required="true" missingMessage="clave lada."
				       regExp="^[+]?\d{2,3}$" invalidMessage="Clave lada inválida"
				       onBlur="changePhoneSizeRequiredAdd('claveAdd','telefonoAdd');"/>
				</div>
			</div>
			<div class="margin_top_20 fl">
				<label for="telefonoAdd"><span>*</span> Teléfono</label>
             	<input type="text" name="telefonoAdd" id="telefonoAdd" value=""
                 dojoType="dijit.form.ValidationTextBox"
                 maxlength="8" style="width:8em;" trim="true"
                 required="true" missingMessage="número telefónico."
                 regExp="^[+]?\d{7,8}$" invalidMessage="Número telefónico inválido." 
                 onBlur="changePhoneSizeRequiredAdd('claveAdd','telefonoAdd');"/>
			</div>
			<div id="extensionDivAddFijo" style="display: block;" class="margin_top_20 fl">
				<label for="extensionAdd">Extensión</label>
				<input type="text" name="extensionAdd" id="extensionAdd" value=""
                   dojoType="dijit.form.ValidationTextBox"
                   maxlength="8" style="width:8em;" trim="true" 
               	   required="false" missingMessage="Es necesario indicar la extension."
               	   regExp="^[+]?\d{0,8}$" invalidMessage="La extension es invalida."/>
			</div>
			<div class="margin_top_50 fl">
				<input type="button" class="enviar" id="btnAgregarTelefono"  value="Guardar teléfono" onclick="javascript:doSubmitAddTelefono();" title="Guardar telefono"/>
			</div>
			<div class="clearfix"></div>
		</div>
		<div class="margin_top_30">
			<c:if test="${registroEmpresaForm.cuentaRegenerada eq false}">
			  <div>
				  <label for="correoElectronico"><span>*</span> Correo electrónico</label>	
				  <p class="user_alert">Si modificas tu correo electrónico, cambiará tu usuario</p>
		          <input type="text" id="correoElectronico" name="correoElectronico"
		                 value="${registroEmpresaForm.correoElectronico}"
		                 dojoType="dijit.form.ValidationTextBox" size="65" maxlength="65" trim="false;"
		                 oncopy="return false;" oncut="return false" onpaste="return false;" missingMessage="correo electrónico."
		                 regExp="${regexpmail}" invalidMessage="Correo electrónico inválido, favor de verificar."/>	
	          </div>       			
			</c:if>		
			<c:if test="${registroEmpresaForm.cuentaRegenerada eq true}">
			  <div>
				  <label for="correoElectronico">Correo electrónico</label><br />${registroEmpresaForm.correoElectronico}	
				  <input type="hidden" name="correoElectronico" id="correoElectronico" 	
				  	value="${registroEmpresaForm.correoElectronico}" dojoType="dijit.form.TextBox"/>				  			  
	          </div>		
			</c:if>	
		</div>
		<div class="margin_top_30">
			<label for="paginaWeb">Página web</label>
			<p>Escribe la URL del sitio</p>
			<input type="text" id="paginaWeb" name="paginaWeb"
				value="${registroEmpresaForm.paginaWeb}"
				dojoType="dijit.form.ValidationTextBox"
				size="65" maxlength="65" trim="false;"
				oncopy="return false;" oncut="return false" onpaste="return false;"
				regExp="${regexpwebsite}" invalidMessage="Formato de pagina web inválido, favor de verificar." />
		</div>
</fieldset>	

		<p id="buttonParagraph" class="form_nav" style="text-align: center; display:block">
			<c:if test="${registroEmpresaForm.cuentaRegenerada eq false}">
				<input id="btnGuardar" name="btnGuardar" class="boton_azul" type="button" value="Finalizar" onclick="registrar();"/>
				<input id="btnCancel" 	name="btnCancel" 	class="boton_azul" type="button" value="Cancelar" 	onclick="cancelConfirmation();"/>
			</c:if>		
			<c:if test="${registroEmpresaForm.cuentaRegenerada eq true}">
				<input id="btnGuardar" 	name="btnGuardar" 	class="boton_azul" type="button" value="Actualizar" onclick="regenerar();"/>
				<input id="btnCancel" 	name="btnCancel" 	class="boton_azul" type="button" value="Cancelar" 	onclick="cancelConfirmation();"/>
			</c:if>						
		</p>		        		
		
	<div dojoType="dijit.Dialog" id="MensajeAlert" title="Mensaje" style="display:none" draggable ="false" >
	  <table class="alertDialog" >
		 <tr align="center">
			 <td><div id ="mensaje"></div>&nbsp;</td>				 
		 </tr>
	 </table>	
	</div>
	
	<%-- B L O Q U E   C A P T C H A --%>
	
	<fieldset id="divCaptcha" style="display:none">
		<legend>Responde la siguiente pregunta</legend>
		<div class="margin_top_30" id="divCaptcha">
			<label id="captchaPregunta"><span>*</span> ${registroEmpresaForm.pregunta}</label>
			<input type="text" name="respuestaUsuario" id="respuestaUsuario" size="40" maxlength="15"
				    	   dojoType="dijit.form.ValidationTextBox"
				    	   required="true" missingMessage="Es necesario proporcionar la respuesta a la pregunta"
				    	   style="font-family:Arial, Helvetica, sans-serif;font-size:12px; color:#444444;width:300px;"/>
			<a href="javascript:cambiarPregunta()" ><img src="${PATH_CSS_SWB}css_registro_candidato/images/icono-cambiar_pregunta.png" width="20" height="20" /></a>
			<a href="javascript:cambiarPregunta()" >Cambiar pregunta</a>			
				<html:messages id="errors">
					<span class="redFont Font80"><bean:write name="errors"/></span><br/>
				</html:messages>
		</div>
	</fieldset>
	
	<div class="form_nav" id="div_form_nav" style="display:block">
		<input id="btnValidarCurp" name="btnValidarCurp" class="boton_azul" type="button" value="Validar" 	onclick="validaCaptcha();"/>
		<input id="btnCancel" 	   name="btnCancel" 	 class="boton_azul" type="button" value="Cancelar" 	onclick="cancelar();"/>
	</div>
	
</div>
</div>	
</div>
</div>

</form>


<div dojoType="dijit.Dialog" id="dialogErrorCaptcha" title="Error" draggable ="false">
	<div class="msg_contain">
		La respuesta no es correcta: por favor, ponga una nueva o cambie de pregunta.
	</div>
	<p class="form_nav">
		<button class="button" onclick="dijit.byId('dialogErrorCaptcha').hide();">Aceptar</button>
	</p>
</div>