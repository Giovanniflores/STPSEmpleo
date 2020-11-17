<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="java.util.Calendar" %>
<%@ page import="mx.gob.stps.portal.web.infra.utils.PropertiesLoader"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>

<fmt:setBundle basename="portal-web" var="messages"/>

<c:set var="regexpcurp">[a-zA-Z]{4}\d{6}[hmHM][a-zA-Z]{2}[B-DF-HJ-NP-TV-Z|b-df-hj-np-tv-z]{3}[a-zA-Z0-9][0-9]</c:set>
<c:set var="regexpnombre">^[A-Za-z\s\��']{1,50}$</c:set>
<c:set var="regexpnss">[0-9]{11}</c:set>

<%String context = request.getContextPath() +"/";%>
<%String remoteAddr = request.getRemoteAddr(); %>

<%
	PropertiesLoader properties = PropertiesLoader.getInstance();
	String urlRedirect = properties.getProperty("app.swb.redirect.oficinas.ubicacion");
%>

<style type="text/css">
	#dialogMensaje_underlay { background-color:gray; }
	#underlay_underlay { background-color:gray; }
</style>
<style type="text/css">
	.oculto {
	   position: absolute !important;
	   top: -9999px !important;
	   left: -9999px !important;
	}
</style>
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
	
	dojo.addOnLoad(function() {
		dojo.byId('conozcoCurpSi').focus();
	});
	
	// Variables para gestionar la respuesta a la validaci�n de Curpo y NSS
	//var LOGGEDUSER_REG_PPC = "${LOGGEDUSER_REG_PPC}";	
	var NOTLOGGEDUSER_NOTREG = "${NOTLOGGEDUSER_NOTREG}";
	var NOTLOGGEDUSER_REG_AND_FUERAPPC = "${NOTLOGGEDUSER_REG_AND_FUERAPPC}";	
	var NOTLOGGEDUSER_REG_AND_PPC = "${NOTLOGGEDUSER_REG_AND_PPC}";
	var NOTLOGGEDUSER_REG_AND_NOTPPC = "${NOTLOGGEDUSER_REG_AND_NOTPPC}";
	var IMSS_NOTAVAILABLE = "${IMSS_NOTAVAILABLE}";
	var NSS_REG_IN_IMSS = "${NSS_REG_IN_IMSS}";
	var NSS_NOTREG_IN_IMSS = "${NSS_NOTREG_IN_IMSS}";
	var DATOS_INCORRECTOS = "${DATOS_INCORRECTOS}";
	var RESULT_ERROR = "${RESULT_ERROR}";
	var RESULT_SUCCESS = "${RESULT_SUCCESS}";	
	var CURP_NOT_RENAPO = "${CURP_NOT_RENAPO}";
	var DATOS_NOT_RENAPO = "${DATOS_NOT_RENAPO}";	

	function doSubmit(method){
		dojo.byId('method').value=method;
		dojo.byId('registroCandidatoForm').submit();
	}

	function continuar(){
		estableceValores();
		doSubmit('toPreregistro');		
	}	
	
	
	function continuar(){
		estableceValores();
		verificaCurpNss();
	}
		
	function continuarCurp(){
		estableceValores();
		doSubmit('toActualizarPreregistro');
	}
	
	function conozcoMiCurp(){

		if(dojo.byId('conozcoCurpSi').checked){
			dojo.byId('porCurp').style.display='block';			
			dojo.byId('btnValidarCurp').style.display='inline';
			dojo.byId('btnValidarDatos').style.display='none';
			dojo.byId('porDatosPersonales').style.display='none';
			dijit.byId('conozcoCurpNo').setChecked(false);			
			limpiarPanelDatosPersonales();
			
		} else {
			dojo.byId('porDatosPersonales').style.display='block';			
			dojo.byId('btnValidarCurp').style.display='none';
			dojo.byId('btnValidarDatos').style.display='inline';
			dojo.byId('porCurp').style.display='none';
			dijit.byId('conozcoCurpSi').setChecked(false);			
			limpiarPanelCurp();

		}

	}

    function limpiarEspacios(ele){
    	
    	if (!ele || !ele.value) return;
    	
    	var cadena = ele.value;
    	ele.value = cadena.replace("  "," ");
    }
    
    function estableceValores(){
    	dojo.byId('dia').value  = dijit.byId('diaSelect').get('value');
    	dojo.byId('anio').value = dijit.byId('anioSelect').get('value');
    	dojo.byId('mes').value  = dijit.byId('mesSelect').get('value');
    	dojo.byId('idEntidadNacimiento').value = dijit.byId('entidadNacimientoSelect').get('value');
    }
    
	var dialogMensaje;
	  
	function showMensaje(){
		var html = '<h3>�Muy Bien!</h3>'+
		           '<p>Los datos de tu CURP son correctos</p>'+
				   '<p><button id="btnValidar" name="btnValidar" class="boton_naranja" onclick="continuar();">Continuar</button></p>';
		
		dialogMensaje = new dijit.Dialog({
	        title: 'Mensaje',
			content: html,
	        style: "width:350px;",
	        showTitle: true, draggable : true, closable : true
	    });		
		
		dojo.style(dialogMensaje.closeButtonNode,"display","none");
		dialogMensaje.show();
	}

	function closeWindow(){
		dialogMensaje.hide();
	}

	function validaCURP(){

		estableceValores();

		
		if (dijit.byId('nss').value == ''){
			displayErrorMsg(dijit.byId('nss'), dijit.byId('nss').get("missingMessage"));
			return;
		}

		if (!dijit.byId('nss').isValid()){
			displayErrorMsg(dijit.byId('nss'), dijit.byId('nss').get("invalidMessage"));
			return;			
		}
		
		if(!validaCaptcha()){
			return;
		}
			
		if (dojo.byId('conozcoCurpSi').checked){ /** Consulta Datos personales a partir de CURP **/
			
			if (dijit.byId('curp').value==''){
				displayErrorMsg(dijit.byId('curp'), dijit.byId('curp').get("missingMessage"));
				return;
			}

			if (!dijit.byId('curp').isValid()){
				displayErrorMsg(dijit.byId('curp'), dijit.byId('curp').get("invalidMessage"));
				return;
			}

			if (!dijit.byId('respuestaUsuario').isValid()){
				displayErrorMsg(dijit.byId('respuestaUsuario'), dijit.byId('respuestaUsuario').get("missingMessage"));
				return false;
			}

			/** Antes de invocar el WS para consultar el CURP se verifica si ya se encuentra registrado en el Portal **/

			if (!validaCURPUnico(true)){ 
				return;
			} 

			verificarDatosPersonales(); 

		} else if (dojo.byId('conozcoCurpNo').checked){ /** Consulta CURP a partir de los Datos personales **/
		
			if (!dijit.byId('nombre').isValid()){
				dijit.byId('nombre').focus();
				dojo.byId('btnValidarCurp').focus();
				return;
			}
			if (!dijit.byId('apellido1').isValid()){
				dijit.byId('apellido1').focus();
				dojo.byId('btnValidarCurp').focus();
				return;
			}
			if (!dijit.byId('apellido2').isValid()){
				dijit.byId('apellido2').focus();
				dojo.byId('btnValidarCurp').focus();
				return;
			}
			if (!dijit.byId('diaSelect').isValid()){
				dijit.byId('diaSelect').focus();
				dojo.byId('btnValidarCurp').focus();
				return;
			}
			if (!dijit.byId('mesSelect').isValid()){
				dijit.byId('mesSelect').focus();
				dojo.byId('btnValidarCurp').focus();
				return;
			}
			if (!dijit.byId('anioSelect').isValid()){
				dijit.byId('anioSelect').focus();
				dojo.byId('btnValidarCurp').focus();
				return;
			}

			if (!dijit.byId('respuestaUsuario').isValid()){
				displayErrorMsg(dijit.byId('respuestaUsuario'), dijit.byId('respuestaUsuario').get("missingMessage"));
				return false;
			}
			
			var dia  = dijit.byId('diaSelect').get('value');
			var mes  = dijit.byId('mesSelect').get('value');
			var anio = dijit.byId('anioSelect').get('value');

			if (dia=='0'){
				alertMsg(dijit.byId('diaSelect').get("missingMessage"));
				dijit.byId('diaSelect').focus();
				return false;
			}

			if (mes=='0'){
				alertMsg(dijit.byId('mesSelect').get("missingMessage"));
				dijit.byId('mesSelect').focus();
				return false;
			}

			if (anio=='0'){
				alertMsg(dijit.byId('anioSelect').get("missingMessage"));
				dijit.byId('anioSelect').focus();
				return false;
			}

			if (!dijit.byId('entidadNacimientoSelect').isValid()){
				dijit.byId('entidadNacimientoSelect').focus();
				dojo.byId('btnValidarCurp').focus();
				return;
			}

			verificarCurp();
		}
	}
	
	function validaInput_NSS_captcha(){
		
		if (dijit.byId('nss').value == ''){
			displayErrorMsg(dijit.byId('nss'), dijit.byId('nss').get("missingMessage"));
			return false;
		}
		
		if (!dijit.byId('nss').isValid()){
			displayErrorMsg(dijit.byId('nss'), dijit.byId('nss').get("invalidMessage"));
			return false;			
		}
		
		if(!validaCaptcha()){
			return false;
		}
		
		return true;
	}
	
	function validaInput_curp(){
		
		if (dijit.byId('curp').value==''){
			displayErrorMsg(dijit.byId('curp'), dijit.byId('curp').get("missingMessage"));
			return false;
		}

		if (!dijit.byId('curp').isValid()){
			displayErrorMsg(dijit.byId('curp'), dijit.byId('curp').get("invalidMessage"));
			return false;
		}

		if (!dijit.byId('respuestaUsuario').isValid()){
			displayErrorMsg(dijit.byId('respuestaUsuario'), dijit.byId('respuestaUsuario').get("missingMessage"));
			return false;
		}
		
		return true;
	}
	
	function validaInput_datos(){
		
		if (!dijit.byId('nombre').isValid()){
			dijit.byId('nombre').focus();
			dojo.byId('btnValidarDatos').focus();
			return false;
		}

		var idElement = 'nombre';
		if (contieneMultiplesEspacios(dijit.byId(idElement).value)){
			displayErrorMsg(dijit.byId(idElement), dijit.byId(idElement).get("invalidMessage"));
			return false;
		}

		if (!dijit.byId('apellido1').isValid()){
			dijit.byId('apellido1').focus();
			dojo.byId('btnValidarDatos').focus();
			return false;
		}
		
		idElement = 'apellido1';
		if (contieneMultiplesEspacios(dijit.byId(idElement).value)){
			displayErrorMsg(dijit.byId(idElement), dijit.byId(idElement).get("invalidMessage"));
			return false;			
		}
		
		if (!dijit.byId('apellido2').isValid()){
			dijit.byId('apellido2').focus();
			dojo.byId('btnValidarDatos').focus();
			return false;
		}
		
		idElement = 'apellido2';
		if (contieneMultiplesEspacios(dijit.byId(idElement).value)){
			displayErrorMsg(dijit.byId(idElement), dijit.byId(idElement).get("invalidMessage"));
			return false;			
		}		

		if (!dijit.byId('diaSelect').isValid()){
			dijit.byId('diaSelect').focus();
			dojo.byId('btnValidarDatos').focus();
			return false;
		}

		if (!dijit.byId('mesSelect').isValid()){
			dijit.byId('mesSelect').focus();
			dojo.byId('btnValidarDatos').focus();
			return false;
		}
		
		if (!dijit.byId('anioSelect').isValid()){
			dijit.byId('anioSelect').focus();
			dojo.byId('btnValidarDatos').focus();
			return false;
		}		
		
		if (!dijit.byId('respuestaUsuario').isValid()){
			displayErrorMsg(dijit.byId('respuestaUsuario'), dijit.byId('respuestaUsuario').get("missingMessage"));
			return false;
		}		
		
		var dia  = dijit.byId('diaSelect').value;		
		var mes  = dijit.byId('mesSelect').value;
		var anio = dijit.byId('anioSelect').value;		

		if (dia == '0'){
			var idElement = 'diaSelect';				
			displayErrorMsg(dijit.byId(idElement), dijit.byId(idElement).get("invalidMessage"));			
			//dijit.byId(idElement).focus();			
			return false;
		}

		if (mes == '0'){			
			var idElement = 'mesSelect';			
			displayErrorMsg(dijit.byId(idElement), dijit.byId(idElement).get("invalidMessage"));			
			//dijit.byId(idElement).focus();			
			return false;
		}

		if (anio == '0'){
			var idElement = 'anioSelect';				
			displayErrorMsg(dijit.byId(idElement), dijit.byId(idElement).get("invalidMessage"));			
			//dijit.byId(idElement).focus();			
			return false;
		}	
		
		if (!isValidDate(dia, mes, anio)){
			var idElement = 'diaSelect';			
			displayErrorMsg(dijit.byId(idElement), dijit.byId(idElement).get("invalidMessage"));			
			//dijit.byId(idElement).focus();			
			return false;
		}
		
		if (!dijit.byId('entidadNacimientoSelect').isValid()){
			dijit.byId('entidadNacimientoSelect').focus();
			dojo.byId('btnValidarDatos').focus();
			return false;
		}
		
		return true;
	}

	function contieneMultiplesEspacios(str){		
		if (dojo.trim(str).search('\\s{2,}') > 0)
			return true;

		return false
	}

	// month toma los valores 1...12
	function isValidDate(day, month, year){
		
		if (day == '0')
			return false;

		if (month == '0')
			return false;

		if (year == '0')
			return false;
		
		var fecha = new Date(year, month-1, day); 
		
		if (fecha.getMonth() != month-1)
			return false;

		return true;
	}

	function continuaRegistroByCurp(){

		if (captchaIsNull())
			return;		
		
		if (!validaInput_NSS_captcha())
			return;

		if (!validaInput_curp())
			return;

		bloqueoPantalla();
		
		dojo.xhrPost(
			{url: 'registro.do?method=continuaRegistroByCurp', form: 'registroCandidatoForm',
		  	   load: function(data){
							desbloqueoPantalla();

							var res = dojo.fromJson(data);
							if(res.result == NOTLOGGEDUSER_NOTREG){
								window.open('${pageContext.request.contextPath}/registro.do?method=toPreregistro', '_self');
								
							} else if(res.result == CURP_NOT_RENAPO){								
								showDialogCurpNoRenapo();
								
							} else if(res.result == IMSS_NOTAVAILABLE){
					    		showMsgImmsNoDisponible();

					    	} else if(res.result == NSS_NOTREG_IN_IMSS){
					    		showMsgNoRegistradoImms();
					    		
					    	} else if(res.result ==  NOTLOGGEDUSER_REG_AND_FUERAPPC){
					    		showCurpRegistradaFueraPPC();
					    		
					    	} else if(res.result == NOTLOGGEDUSER_REG_AND_PPC){
					    		showCurpRegistradaPPC();

					    	} else if(res.result == NOTLOGGEDUSER_REG_AND_NOTPPC){
					    		autenticaGoToTerminos();	

					    	} else if(res.result == DATOS_INCORRECTOS){
					    		showErrorMsg("Existen datos err�neos o faltantes.");
					    		
					    	} else {
					    		showMsgError();
					    	}
		  	   		 }
			}
		);			
	}
	
	function continuaRegistroByDatos(){
		
		if (!validaInput_NSS_captcha())
			return;

		if (!validaInput_datos())
			return;

		estableceValores();
		
		bloqueoPantalla();
	
		dojo.xhrPost(
				{ url: 	'registro.do?method=continuaRegistroByDatosPersonales',
				  form:	'registroCandidatoForm',
			  	  load: function(data){
							desbloqueoPantalla();
							
							var res = dojo.fromJson(data);
							if(res.result == NOTLOGGEDUSER_NOTREG){
								window.open('${pageContext.request.contextPath}/registro.do?method=toPreregistro', '_self');
								
							} else if(DATOS_NOT_RENAPO == res.result){								
								showDialogDatosNoRenapo();
								
							} else if(IMSS_NOTAVAILABLE == res.result){
						    	showMsgImmsNoDisponible();
						    		
						    } else if(NSS_NOTREG_IN_IMSS == res.result){
						    	showMsgNoRegistradoImms();
						    		
						    } else if(res.result == NOTLOGGEDUSER_REG_AND_PPC){
						    	showCurpRegistradaPPC();

						    } else if(res.result == NOTLOGGEDUSER_REG_AND_NOTPPC){
						    	autenticaGoToTerminos();
						    	
					    	} else if(res.result == DATOS_INCORRECTOS){
					    		showErrorMsg("Existen datos err�neos o faltantes.");						    	
						    	
						    } else {
						    	showMsgError();
						    }
			  	   }
				}
		);
		
	}

	function autenticaGoToActualizaPerfil(){

		bloqueoPantalla();
		
		dojo.xhrPost(
			{url: 'registro.do?method=autenticaAutomaticamente', form: 'registroCandidatoForm',
		  	   	load: function(data){		
		  	   		desbloqueoPantalla();
		  	   		var res = dojo.fromJson(data);	  	   		
		  	  	 	if(RESULT_SUCCESS == res.result){		  	  	 		
		  	  	 		window.open('<%=response.encodeURL(request.getContextPath()+"/perfil.do?method=init")%>', '_self');

		  	  	 	} else {
			    		showMsgError();
			    		
		  	  	 	}

				}
			}
		);					  		   
	}
	
	function autenticaGoToTerminos(){
		bloqueoPantalla();		
		
		dojo.xhrPost(
				{url: 'registro.do?method=autenticaAutomaticamente',// form: 'registroCandidatoForm',
			  	   	load: function(data){					  	   		
			  	   		desbloqueoPantalla();			
			  	   		
			  	   		var res = dojo.fromJson(data);		  	   		
			  	  	 	if(RESULT_SUCCESS == res.result){
			  	  	 		window.open('${pageContext.request.contextPath}/registro.do?method=showTerminosCondiciones', '_self');

			  	  	 	} else {
				    		showMsgError();
				    		
			  	  	 	}

					}
				}
			);					  		   
	}
	
	function deleteRegistroFormFromSession(){
		dojo.xhrPost(
				{url: 'registro.do?method=deleteRegistroFormFromSession', form: 'registroCandidatoForm',
			  	   	load: function(data){					  	   			  	   		
			  	   		var res = dojo.fromJson(data);

			  	  	 	if(RESULT_SUCCESS != res.result)
				    		showMsgError();
					}
				}
			);

	}
		
  	/** Consulta Datos personales a partir de CURP **/
    function verificarDatosPersonales(){
    	bloqueoPantalla();
  		
		dojo.xhrPost({url: 'registro.do?method=verificarDatosPersonales',form: 'registroCandidatoForm',

				  	   load: function(data){
							desbloqueoPantalla();
				  			var res = dojo.fromJson(data); // Datos del servidor
					    	if('EXITOSO' == res.statusOper){
					    		dojo.byId('nombre').value = res.nombre;
						     	dojo.byId('apellido1').value = res.apellido1;
						     	dojo.byId('apellido2').value = res.apellido2;
						     	dijit.byId('entidadNacimientoSelect').set('value', res.idEntidadNacimiento);

						     	var fhnac = res.fechaNacimientoString;
						     	if (fhnac && fhnac.length==10){
						     		if(fhnac.indexOf("/")>1){
							     		var dia = fhnac.substring(0,2);
							     		var mes = fhnac.substring(3,5);
							     		var anio = fhnac.substring(6,10);
							     		}
							     		else{
							     			var dia = fhnac.substring(8,10);
								     		var mes = fhnac.substring(5,7);
								     		var anio = fhnac.substring(0,4);
								     			
							     		}
							     		

						     		// Se quita el 0 al inicio de la cadena en caso de ser menos a 10, ejemplo '08'
						     		dia = dia * 10;
						     		dia = dia / 10;
						     		dia = parseInt(dia);
						     		
						     		mes = mes * 10;
						     		mes = mes / 10;
						     		mes = parseInt(mes);

						     		anio = parseInt(anio);
						     		
						     		dijit.byId('diaSelect').set('value', dia);
						     		dijit.byId('mesSelect').set('value', mes);
						     		dijit.byId('anioSelect').set('value', anio);
						     	}

						        if(res.genero==1){
						     		dojo.byId('idGeneroMasculino').checked = true;						     		
						     	} else {
						     		dojo.byId('idGeneroFemenino').checked = true;
						     	}
						        
						        continuar();

					     	} else if('NO EXITOSO' == res.statusOper && 'Error' != res.message ) {
					     		dijit.byId('curp').focus();
					    	 	//displayErrorMsg(dijit.byId('curp'), "La curp no existe, favor de verificarla.");
								//res.message
				    			var nofind = false;
				    			if (res.message && res.message.indexOf('no se encuentra')>0){
				    				nofind = true;
				    			}

				    			var msgerr;

				    			if (nofind){
				    				/* 
				    				msgerr =
				    				"La CURP no existe, favor de verificarla.<br/>Si tu CURP es correcta visita la siguiente liga <br/>"+
				    				"<a href='http://www.renapo.gob.mx/swb/swb/RENAPO/tramite' target='_blank'>http://www.renapo.gob.mx/swb/swb/RENAPO/tramite</a> "+
				    				"<br/>para ver si existe un problema con tu CURP.<br/><br/>"+
				    				"<input id='btnCerrar' name='btnCerrar' class='boton_naranja' type='button' value='Cerrar' onclick='cerrarError();'/>";
				    				*/
				    				dijit.byId("dialogErrorCurpNoExiste").show();
				    			} else {
								    /*
				    				msgerr = 
									"Favor de revisar los datos proporcionados.<br/>Si tus datos son correctos visita la siguiente liga <br/>"+
									"<a href='http://www.renapo.gob.mx/swb/swb/RENAPO/tramite' target='_blank'>http://www.renapo.gob.mx/swb/swb/RENAPO/tramite</a> "+
									"<br/>para ver si existe un problema con tu CURP.<br/><br/>"+
									"<input id='btnCerrar' name='btnCerrar' class='boton_naranja' type='button' value='Cerrar' onclick='cerrarError();'/>";
									*/
								    dijit.byId("dialogErrorCurpError").show();
				    			}

								//showErrorMsg(msgerr);
					     	} else if('ERROR_CAPTCHA' == res.statusOper) {
					   		//	dijit.byId('code').focus();
					   		//	displayErrorMsg(dijit.byId('code'), dijit.byId('code').get("invalidMessage"));
					     		dijit.byId('dialogErrorCaptcha').show();

					     	} else if('exception' == res.statusOper) {
					    		var msgerr = "La transacci�n no pudo ser completada, favor de intentarlo nuevamente despu�s de unos instantes.";
								showErrorMsg(msgerr);
							 } else {
					    		var msgerr = "La transacci�n no pudo ser completada, favor de intentarlo nuevamente despu�s de unos instantes.";
			 					showErrorMsg(msgerr);
						   	 }
					   	}
					});	
	}

  	function showMessageCurpDuplicado(resultExiste){

  		if (resultExiste == "nounico_ppc")
  			replaceDialogMsg("La CURP ya est� registrada en el sistema e inscrita al Programa de Promoci�n y Colocaci�n del Seguro de Desempleo (PPC-SE). Para ingresar al portal, puedes:", "msgCurpRegistrada");
  		
  		dijit.byId("dialogCurpRegistrada").show();
  	}
  	
  	function verificarCurp(){

  		bloqueoPantalla();

	//	dojo.byId('method').value = 'verificarCurp';
  		
		dojo.xhrPost({url: 'registro.do?method=verificarCurp',form: 'registroCandidatoForm',
					load: function(data){

							desbloqueoPantalla();  
							var res = dojo.fromJson(data); // Datos del servidor
			    			if('EXITOSO' == res.statusOper){
				     			

				     			//dojo.byId('porCurp').style.display='block';
				     			dojo.byId('curp').value = res.curp;
				     			//dojo.byId('divCaptcha').style.display='none';

								/** Una vez que se consulta el CURP a partir de los datos se valida si no se encuentra registrado YA en el Portal **/				     
								if (!validaCURPUnico(false)){
									return;
								}

							   	//showMensaje();
							   	continuar();

					    } else if('NO EXITOSO' == res.statusOper && 'Error' != res.message ) {
					    	dijit.byId('nombre').focus();
			    			//displayErrorMsg(dijit.byId('nombre'), "Favor de revisar los datos proporcionados.");

			    			var nofind = false;
			    			if (res.message && res.message.indexOf('no se encuentra')>0){
			    				nofind = true;
			    			}
			    			
			    			var msgerr; 

			    			if (nofind){
			    				msgerr =
			    				/*
			    				"La CURP no existe, favor de verificarla.<br/>Si tu CURP es correcta visita la siguiente liga <br/>"+
			    				"<a href='http://www.renapo.gob.mx/swb/swb/RENAPO/tramite' target='_blank'>http://www.renapo.gob.mx/swb/swb/RENAPO/tramite</a> "+
			    				"<br/>para ver si existe un problema con tu CURP.<br/><br/>"+
			    				"<input id='btnCerrar' name='btnCerrar' class='boton_naranja' type='button' value='Cerrar' onclick='cerrarError();'/>";
			    				*/
			    				dijit.byId("dialogErrorCurpNoExiste").show();
			    			} else {
							    /*
			    				msgerr = 
								"Favor de revisar los datos proporcionados.<br/>Si tus datos son correctos visita la siguiente liga <br/>"+ 
								"<a href='http://www.renapo.gob.mx/swb/swb/RENAPO/tramite' target='_blank'>http://www.renapo.gob.mx/swb/swb/RENAPO/tramite</a> "+
								"<br/>para ver si existe un problema con tu CURP.<br/><br/>"+
								"<input id='btnCerrar' name='btnCerrar' class='boton_naranja' type='button' value='Cerrar' onclick='cerrarError();'/>";
								*/
							    dijit.byId("dialogErrorCurpError").show();
			    			}
									     
					    	//showErrorMsg(msgerr);
			    		} else if('ERROR_CAPTCHA' == res.statusOper) {
			   				//dijit.byId('code').focus();
			   				//displayErrorMsg(dijit.byId('code'), dijit.byId('code').get("invalidMessage"));
			    			dijit.byId('dialogErrorCaptcha').show();

			    		} else if('exception' == res.statusOper) {
							/*
			    			var msgerr = "La transacci�n no pudo ser completada,<br/>favor de intentarlo nuevamente despu�s de unos instantes.<br/><br/>"+
						                 "<input id='btnCerrar' name='btnCerrar' class='boton_naranja' type='button' value='Cerrar' onclick='cerrarError();'/>";
				    		*/
						    var msgerr = "La transacci�n no pudo ser completada, favor de intentarlo nuevamente despu�s de unos instantes.";
							showErrorMsg(msgerr);			    			
				 		} else {
			    			/*
				 			var msgerr = "La transacci�n no pudo ser completada,<br/>favor de intentarlo nuevamente despu�s de unos instantes.<br/><br/>"+
			                             "<input id='btnCerrar' name='btnCerrar' class='boton_naranja' type='button' value='Cerrar' onclick='cerrarError();'/>";
				 			*/
				    		var msgerr = "La transacci�n no pudo ser completada, favor de intentarlo nuevamente despu�s de unos instantes.";			                
				 			showErrorMsg(msgerr);
			    		}
			 		}
				});
  		
	}

  	function showErrorMsg(errmsg){
  		replaceDialogMsg(errmsg, "errorMsgArea");
  		dijit.byId("holderMensaje").show();
  	}
  	
  	
  	function replaceDialogMsg(errMsg, idMsgContent){
  		var textnode = document.createTextNode(errMsg);
  	    var item = document.getElementById(idMsgContent);
  	    item.replaceChild(textnode, item.childNodes[0]);
  	}

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
	
	var underlay;
	
    function bloqueoPantalla() {

		var html = '<h3>�Su consulta puede tardar algunos segundos!</h3>'+
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
	
	function desbloqueoPantalla() {
		underlay.hide();
	}
	

	function captchaIsNull(){
		if (dijit.byId('respuestaUsuario').value == ''){
			displayErrorMsg(dijit.byId('respuestaUsuario'), dijit.byId('respuestaUsuario').get("missingMessage"));
			return true;
		}
		
		return false;
	}
	
	function validaCaptcha(){
		var valido = false;

		dojo.byId('method').value='validaCaptcha';
		dojo.xhrPost({url: 'registro.do', form:'registroCandidatoForm', sync: true, 
			  		  load: function(data){
			  					var res = dojo.fromJson(data);
			  					
								if ('captcha'== res.statusOper)
			  						valido = true;
								else 
									dijit.byId('dialogErrorCaptcha').show();
			 				}
					});
		
		return valido;
	}

	function validaCURPUnico(estableceDatos){
		var unico = true;
		
		dojo.byId('method').value='validaCURPUnico';

		dojo.xhrPost({url: 'registro.do', form:'registroCandidatoForm', sync: true, 
			  		  load: function(data){
			  					var res = dojo.fromJson(data);
			  					
			  					if('nss_error' == res.statusOper){
		  							if (res.result == 4) {
		  							  showMsgImmsNoDisponible();
		  							
		  							  
		  						  } else if (res.result == 5) {
		  							  showMsgNoRegistradoImms();
		  									  							  
		  						  } else if (res.result==101){
		  							showMsgImmsDuplicada();
		  									  						  }
		  						  else{
		  							  showMsgError();
		  							  
		  						  }
		  							unico = false;
		  						}
			  					else{
			  					if('exito' == res.statusOper){
			  						
			  						if ('unico'== res.message){
			  							unico = true;
			  						} else {
			  							unico = false;
			  							
			  							// establece los datos del ciudadano obtenidos a partir de su CURP
			  							if (estableceDatos){
			  								//Si ya esta registrado se debe cargar los datos
				  							dojo.byId('curp').value = res.curp;

									     	dojo.byId('nombre').value = res.nombre;
									     	dojo.byId('apellido1').value = res.apellido1;
									     	dojo.byId('apellido2').value = res.apellido2;
									     	dijit.byId('entidadNacimientoSelect').set('value', res.idEntidadNacimiento);

									     	var fhnac = res.fechaNacimientoString;
									     	if (fhnac && fhnac.length==10){
									     		var dia = fhnac.substring(0,2);
									     		var mes = fhnac.substring(3,5);
									     		var anio = fhnac.substring(6,10);
												
									     		// Se quita el 0 al inicio de la cadena en caso de ser menos a 10, ejemplo '08'
									     		dia = dia * 10;
									     		dia = dia / 10;
									     		dia = parseInt(dia);
									     		
									     		mes = mes * 10;
									     		mes = mes / 10;
									     		mes = parseInt(mes);

									     		anio = parseInt(anio);

									     		dijit.byId('diaSelect').set('value',  dia);
									     		dijit.byId('mesSelect').set('value',  mes);
									     		dijit.byId('anioSelect').set('value', anio);
									     	}

									        if(res.genero==1){
									     		dojo.byId('idGeneroMasculino').checked = true;						     		
									     	} else {
									     		dojo.byId('idGeneroFemenino').checked = true;
									     	}
									        
			  							}
			  							showMessageCurpDuplicado(res.message);
			  						}
			  					} else if('error' == res.statusOper){
			  						alertMsg(res.message);
			  						unico = false;
			  					}
			 				}
			  		  }
					});

		return unico;
	}
	
	function alertMsg(msg){
		alert(msg);
	}

	function recuperaPsw(){		
		doSubmit('toRecuperaContrasena');
	}

	function cambiarPregunta(){
		dojo.xhrPost({url: 'registro.do?method=cambiaPregunta',
			  load: function(data) {
				  document.getElementById('captchaPregunta').innerHTML = '<span>*</span>' + data;
			  }
				  		
			});
	}
	
	function verificaCurpNss(){
		// Validamos que est�n correctamente informados los campos
		
		if (dijit.byId('nss').value == ''){
			displayErrorMsg(dijit.byId('nss'), dijit.byId('nss').get("missingMessage"));
			return;
		}

		if (!dijit.byId('nss').isValid()){
			displayErrorMsg(dijit.byId('nss'), dijit.byId('nss').get("invalidMessage"));
			return;			
		}

		dojo.xhrPost({url: 'registro.do?method=verificaCurpNss', form: 'registroCandidatoForm',
					  load: function(data){
						  var res = dojo.fromJson(data);
						
						  if (res.result == 1){
							  doSubmit('toPreregistro'); // exito, avanza siguiente paso
						  }
						  /*else if (res.result == 2) { 
							  showMsgInscritoPPC();
							  
						  } else if (res.result == 3) { 
							  showMsgFueraPPC();
						  } */
						  else if (res.result == 4) {
							  showMsgImmsNoDisponible();				  
							  
						  } else if (res.result == 5) {
							  showMsgNoRegistradoImms();
							  
						  } else {
							  showMsgError();
						  }
						  
					  	}
					 });
		
	}
	
	function showMsgImmsNoDisponible(){				
		var idTag = "msgImmsNoDisponible";
		dijit.byId(idTag).show();
	}

	function showMsgNoRegistradoImms(){
		var idTag = "msgNoRegistradoImms";
		dijit.byId(idTag).show();		
	}

	function showCurpRegistradaPPC(){
		var idTag = "dialogCurpRegistradaPPC";
		dijit.byId(idTag).show();		
	}	

	function hideCurpRegistradaPPC(){	
		var idTag = "dialogCurpRegistradaPPC";
		dijit.byId(idTag).hide();
	}
	
	function showMsgError(){
		var idTag = "msgError";
		dijit.byId(idTag).show();
	}

	function limpiarPanelCurp(){		
		dojo.byId('curp').value = null;
		displayErrorMsg(dijit.byId('curp'), '');
		dijit.hideTooltip(dijit.byId('curp').domNode);
		
		dijit.hideTooltip(dijit.byId('nss').domNode);
	}

	function limpiarPanelDatosPersonales(){		
		dojo.byId('diaSelect').value = 'Dia';
		dijit.hideTooltip(dijit.byId('nss').domNode);
	}
	
	function showMsgCancelar(){		
		var idTag = "msgCancelar";
		dijit.byId(idTag).show();		
	}

	function showDialogCurpNoRenapo(){		
		var idTag = "dialogCurpNoRenapo";
		dijit.byId(idTag).show();		
	}
	
	function showDialogDatosNoRenapo(){		
		var idTag = "dialogDatosNoRenapo";
		dijit.byId(idTag).show();		
	}
	
	function showCurpRegistradaFueraPPC(){
		var idTag = "dialogCurpRegistradaFueraPPC";
		dijit.byId(idTag).show();		
	}	
	
	function errMsgDatos(){
		showErrorMsg("Existen datos err�neos o faltantes.");
	}
	

	function cleanupMultipleWhiteSpaces(cadena){

		if (cadena == null)
			return null;
		
		espacio = " ";
		nuevaCadena = "";
		anteriorCaracter = "";

		for (i = 0; i < dojo.trim(cadena).length; i++) { 					
			
			actualCaracter = dojo.trim(cadena).substr(i, 1);
			
			if (actualCaracter != espacio){
				nuevaCadena = nuevaCadena + actualCaracter;
				
			} else {				
				if (anteriorCaracter != espacio){
					nuevaCadena = nuevaCadena + espacio;
				}						
			}

			anteriorCaracter = dojo.trim(cadena).substr(i, 1);
		}

		return nuevaCadena;
	}
	
	function cleanupInputWithMultipleWhiteSpaces(id){			
		elemento = dojo.byId(id);		
		nuevaCadena = cleanupMultipleWhiteSpaces(elemento.value);

		elemento.value = nuevaCadena;
		
		// actualizamos el dojo widget para que no se active el invalid message
		dijit.byId(id).value = nuevaCadena;
	}	

</script>

<noscript>Funciones de JavaScript desactivadas por navegador</noscript>

<div dojoType="dojo.data.ItemFileReadStore" jsId="diasStore"              url="${context}registro.do?method=dias"></div>
<div dojoType="dojo.data.ItemFileReadStore" jsId="mesesStore"             url="${context}registro.do?method=meses"></div>
<div dojoType="dojo.data.ItemFileReadStore" jsId="aniosStore"             url="${context}registro.do?method=anios"></div>
<div dojoType="dojo.data.ItemFileReadStore" jsId="entidadNacimientoStore" url="${context}registro.do?method=entidades"></div>

<form name="registroCandidatoForm" id="registroCandidatoForm" method="post" action="registro.do" dojoType="dijit.form.Form">
	<input type="hidden" name="method" id="method" value="init" dojoType="dijit.form.TextBox"/> <!-- indica el metodo a invocar -->
 
 	<input type="hidden" name="dia" id="dia" value="${registroCandidatoForm.dia}" dojoType="dijit.form.TextBox"/>
 	<input type="hidden" name="mes" id="mes" value="${registroCandidatoForm.mes}" dojoType="dijit.form.TextBox"/>
 	<input type="hidden" name="anio" id="anio" value="${registroCandidatoForm.anio}" dojoType="dijit.form.TextBox"/>
 	<input type="hidden" name="idEntidadNacimiento" id="idEntidadNacimiento" value="${registroCandidatoForm.idEntidadNacimiento}" dojoType="dijit.form.TextBox"/>
 	<input type="hidden" value="${param.remoteAddr}"/>
 	
 	
 	
 <%-- E S T R U C T U R A
      N U E V A --%>
      
<div class="formApp">
	<div class="flow_1">Registro de Candidatos</div>
	<div class="form_wrap">
		<div class="instruc_01"><strong>�Reg�strate en 3 sencillos pasos!</strong></div>
		<div class="form_edge">
			<div class="stepApp">
			    <h2><img alt="Paso 1 de 3. Datos de identificaci�n" src="css/images/paso_1.png"></h2>
			    <p>Los datos marcados con <span>*</span> son obligatorios</p>

				<c:if test="${!registroCandidatoForm.registroPPC}">
					<div class="user_dt form_nom">
						<h3>Datos de la Clave �nica de Registro de Poblaci�n</h3>
					</div>
				</c:if>
				
				<c:if test="${registroCandidatoForm.registroPPC}">
					<div class="user_dt form_nom">
						<h3>Datos de la Clave �nica de Registro de Poblaci�n y N�mero de Seguridad Social</h3>
					</div>
				</c:if>
			</div>
						
		<fieldset class="field_curp">
				<legend>CURP</legend>

			    <div class="ctrl_01">
			        <div class="labeled"><span>*</span>�Conoces tu CURP?</div>
			        <div class="CURP_01">
			            <input type="radio" data-dojo-type="dijit/form/RadioButton" name="conozcoCurp" id="conozcoCurpSi" onclick="conozcoMiCurp()" value="1"/><label for="conozcoCurpSi">S�</label><br />
			            <input type="radio" data-dojo-type="dijit/form/RadioButton" name="conozcoCurp" id="conozcoCurpNo" onclick="conozcoMiCurp()" value="0" checked="checked"/><label for="conozcoCurpNo">No</label>
			        </div>
			        <div class="clearfix"></div>
			    </div>
        
		<div dojoType="dijit.Dialog" id="holderMensaje" title="Error" draggable="false">
			<div class="msg_contain">
				<p id="errorMsgArea" style='text-align: center;'/>
			</div>
			<p class="form_nav">	
				<button class="button" onclick="dijit.byId('holderMensaje').hide();">Cerrar</button>
			</p>			
		</div>

        <%-- Si s� conoce su CURP --%>
		<div id="porCurp" class="ctrl_02" style="display:none">
	        <div class="margin_top_30">
	            <label for="curp"><span>*</span>CURP</label>
	            <p>La CURP te servir� como identificador para tu registro al Portal del Empleo y Servicio Nacional de Empleo, por lo que debes proporcionarla.<br />
				Por favor ingresa los siguientes datos:</p>
				<div class="margin_top_10">
	            <input type="text" id="curp"  name="curp" 
	               dojoType="dijit.form.ValidationTextBox" 
	               required="false"       missingMessage="Debe de indicar la CURP." 
	               regExp="${regexpcurp}" invalidMessage="CURP inv�lida, favor de verificar su captura." 
	               maxlength="18" uppercase="true" clearOnClose="true" trim="true"
	               value="${registroCandidatoForm.curp}"/>
				</div>
	        </div>
       </div>
        
		<%-- Si no conoce su CURP --%>	
		<div id="porDatosPersonales">
				<p class="margin_top_30">La CURP te servir� como identificador para tu registro al Portal del Empleo y Servicio Nacional de Empleo, por lo que debes proporcionarla.<br />
				Por favor ingresa los siguientes datos:</p>	
				<div class="ctrl_04 nombre_01">
					<div class="grid1_3 fl">
						<label for="nombre"><span>*</span>Nombre(s)</label>
						<input type="text" id="nombre" name="nombre" 
			               dojoType="dijit.form.ValidationTextBox" 
			               required="true"          missingMessage="Debe indicar el nombre."
			               regExp="${regexpnombre}" invalidMessage="Nombre inv�lido, no se permiten n�meros ni acentos o caracteres especiales." 
			               maxlength="50" uppercase="true" trim="true" onchange="cleanupInputWithMultipleWhiteSpaces(this.id);"
			               value="${registroCandidatoForm.nombre}"			               
			               />
					</div>
					<div class="grid1_3 fl">
						<label for="apellido1"><span>*</span>Primer apellido</label>
						<input type="text" id="apellido1" name="apellido1" 
			               dojoType="dijit.form.ValidationTextBox" 
			               required="true"          missingMessage="Debe indicar el primer apellido."
			               regExp="${regexpnombre}" invalidMessage="Apellido inv�lido, no se permiten n�meros ni acentos o caracteres especiales." 
			               maxlength="50" uppercase="true" trim="true" onchange="cleanupInputWithMultipleWhiteSpaces(this.id);"
			               value="${registroCandidatoForm.apellido1}"
			               />
					</div>
					<div class="grid1_3 fl margin_no_r">
						<label for="apellido2">Segundo apellido</label>
						<input type="text" id="apellido2" name="apellido2" 
			               dojoType="dijit.form.ValidationTextBox" 
			               required="false"          missingMessage="Debe indicar el segundo apellido."
			               regExp="${regexpnombre}" invalidMessage="Apellido inv�lido, no se permiten n�meros ni acentos o caracteres especiales." 
			               maxlength="50" uppercase="true" trim="true" onchange="cleanupInputWithMultipleWhiteSpaces(this.id);"
			               value="${registroCandidatoForm.apellido2}"
			               />
					</div>
				</div>
				<div class="clearfix"></div>
					<div class="sexo_01">
						<div class="labeled"><span>*</span>Sexo</div>
						<input type="radio" name="genero" data-dojo-type="dijit/form/RadioButton" id="idGeneroMasculino" value="1" checked="checked"/><label for="idGeneroMasculino">Hombre</label><br />
						<input type="radio" name="genero" data-dojo-type="dijit/form/RadioButton" id="idGeneroFemenino"  value="2"/><label for="idGeneroFemenino">Mujer</label>
						<div class="clearfix"></div>
					</div>				
				    <div class="ctrl_06">
				        <div class="grid1_3 fl">
				            <div class="labeled"><span>*</span>Fecha de nacimiento</div>
				            <label for="diaSelect" class="oculto">dia de nacimiento</label>
				            <select id="diaSelect" name="diaSelect"
								required="true" missingMessage="Debe indicar el dia de su nacimiento."
								invalidMessage="Dia inv�lido, favor de verificar."
								promptMessage="Dia"
					        	value="${registroCandidatoForm.dia}" autocomplete="true"
					        	
					        	dojotype="dijit.form.FilteringSelect" store="diasStore"></select>
					        <label for="mesSelect" class="oculto">mes de nacimiento</label>
				            <select id="mesSelect" name="mesSelect"
							    required="true" missingMessage="Debe indicar el mes de su nacimiento."
							    invalidMessage="Mes inv�lido, favor de verificar."
						    	promptMessage="Mes"
					        	value="${registroCandidatoForm.mes}" autocomplete="true"
					        	dojotype="dijit.form.FilteringSelect" store="mesesStore"></select>
					        <label for="anioSelect" class="oculto">a�o de nacimiento</label>
				            <select id="anioSelect" name="anioSelect"
								required="true" missingMessage="Debe indicar el a�o de su nacimiento."
								invalidMessage="A�o inv�lido, favor de verificar." 
								promptMessage="A�o"
					        	value="${registroCandidatoForm.anio}" autocomplete="true"
					        	dojotype="dijit.form.FilteringSelect" store="aniosStore"></select>
				        </div>
				        <div class="grid1_3 fl">
				            <label class="labeled" for="entidadNacimientoSelect"><span>*</span>Lugar de nacimiento</label>
				            <select id="entidadNacimientoSelect" name="entidadNacimientoSelect"
							required="true" missingMessage="Debe indicar la entidad de nacimiento."
							invalidMessage="Lugar de nacimiento inv�lido."
					        value="${registroCandidatoForm.idEntidadNacimiento}" autocomplete="true"
					        dojotype="dijit.form.FilteringSelect" store="entidadNacimientoStore"></select>
				        </div>
				        <div class="clearfix"></div>
				    </div>
				</div>		    
			</fieldset>
			
			
			<fieldset class="nss_1">
			<legend>NSS</legend>
				
					<div class="grid1_3">
						<label><span>*</span> N�mero de Seguridad Social</label>
						<input type="text" id="nss"  name="nss" 
				               dojoType="dijit.form.ValidationTextBox" 
				               required="true"       missingMessage="Debe de indicar el N�mero de Seguro Social." 
				               regExp="${regexpnss}" invalidMessage="N�mero de Seguro Social inv�lida, favor de verificar su captura." 
				               maxlength="11" uppercase="true" clearOnClose="true" trim="true"
				               value="${registroCandidatoForm.nss}"/>
				</div>
			</fieldset>
			
			<fieldset class="field_pregunta">
				<legend>Responde a la siguiente pregunta</legend>
				<div id="divCaptcha" class="ctrl_07 margin_top_30">
			        <label id="captchaPregunta"><span>*</span>${registroCandidatoForm.pregunta}</label>
			        <input type="text" name="respuestaUsuario" id="respuestaUsuario" size="40" maxlength="15"
			    	   dojoType="dijit.form.ValidationTextBox"
			    	   required="true" missingMessage="Es necesario proporcionar la respuesta a la pregunta"
			    	   style="font-family:Arial, Helvetica, sans-serif;font-size:12px; color:#444444;width:300px;"/><br />
			        <a href="javascript:cambiarPregunta()" > Cambiar pregunta</a>
			    <html:messages id="errors">
					<span class="redFont Font80"><bean:write name="errors"/></span><br/>
				</html:messages>
			    </div>
			</fieldset>
		</div>
	</div>
</div>  
	
	<div class="form_nav">				
		<input id="btnValidarDatos" name="btnValidarDatos" class="boton_azul" type="button" value="Continuar" onclick="continuaRegistroByDatos();" style="display: inline;"/>
		<input id="btnValidarCurp" name="btnValidarCurp" class="boton_azul" type="button" value="Continuar" onclick="continuaRegistroByCurp();" style="display: none;"/>
		<input type="button" value="Cancelar" onclick="showMsgCancelar()"/>
	</div>     

</form>
			
<div dojoType="dijit.Dialog" id="dialogErrorCurpError" title="Error" style="display: none;" draggable ="false">
	<div class="msg_contain">
		<p>
			Favor de revisar los datos proporcionados.<br/>
			Si tus datos son correctos visita la siguiente liga<br/>
			<a href='http://www.renapo.gob.mx/swb/swb/RENAPO/tramite' target='_blank'>http://www.renapo.gob.mx/swb/swb/RENAPO/tramite</a><br/>
			para ver si existe un problema con tu CURP.
		</p>
	</div>
	<p class="form_nav">
		<button class="button" onclick="dijit.byId('dialogErrorCurpError').hide();">Aceptar</button>
	</p>
</div>

<div dojoType="dijit.Dialog" id="dialogErrorCurpNoExiste" title="Error" style="display: none;" draggable ="false">
	<div class="msg_contain">
		<p>
		La CURP no existe, favor de verificarla.<br/>
		Si tu CURP es correcta visita la siguiente liga:<br/>
		<a href='http://www.renapo.gob.mx/swb/swb/RENAPO/tramite' target='_blank'>http://www.renapo.gob.mx/swb/swb/RENAPO/tramite</a><br/>
		para ver si existe un problema con tu CURP.
		</p>
	</div>
	<p class="form_nav">
		<button class="button" onclick="dijit.byId('dialogErrorCurpNoExiste').hide();">Aceptar</button>
	</p>
</div>	
			
<div dojoType="dijit.Dialog" id="dialogCurpRegistradaPPC" title="Aviso" draggable ="false" class="suelto">
	<div class="msg_contain">
		<p id="msgCurpRegistrada">La CURP ya se encuentra registrada en el sistema y en el PPC.</p>
		<br/>
		
		<p class="form_nav 2" >
		
			<button id="btnActualizaPerfil" class="button" onclick="hideCurpRegistradaPPC(); autenticaGoToActualizaPerfil();">Actualizar perfil</button>
			<br/>			
			<br/><br/>
			<button id="btnRecuperaPsw" class="button" onclick="recuperaPsw();">Recuperar tu contrase�a</button>
			<br/>		
		</p>
			
		<p>
			Si lo deseas comun�cate al tel�fono <strong><fmt:message key='portal.contacto.telefono' bundle='${messages}'/></strong>
		</p>
		<p class="form_nav 2" >		
			<br/>
			<button id="btnCerrar" class="button" onclick="hideCurpRegistradaPPC();">Cerrar</button>
		</p>
	</div>
</div>

<div dojoType="dijit.Dialog" id="dialogErrorCaptcha" title="Error" draggable ="false">
	<div class="msg_contain">
		La respuesta no es correcta: por favor, ponga una nueva o cambie de pregunta.
	</div>
	<p class="form_nav">
		<button class="button" onclick="dijit.byId('dialogErrorCaptcha').hide();">Aceptar</button>
	</p>
</div>

<div dojoType="dijit.Dialog" id="msgNoRegistradoImms" title="Aviso" draggable="false" style="display:none">
	<div class="msg_contain">
		<p>No se ha podido validar el NSS; por favor verifique su situaci&oacute;n en el IMSS.</p>
	</div>
	<p class="form_nav">	
		<button class="button" onclick="dijit.byId('msgNoRegistradoImms').hide();">Aceptar</button>
	</p>
</div>	

<div dojoType="dijit.Dialog" id="msgImmsNoDisponible" title="Error" draggable="false" style="display:none">
	<div class="msg_contain">
		<p>No se puede validar el NSS porque el servicio del IMSS no est� disponible: por favor int�ntelo de nuevo m�s tarde.</p>
	</div>
	<p class="form_nav">	
		<button class="button" onclick="window.open('${pageContext.request.contextPath}/logout.do', '_self');">Aceptar</button>
	</p>
</div>

<div dojoType="dijit.Dialog" id="msgCancelar" title="Aviso" draggable="false" style="display:none">
	<div class="msg_contain">
		<p>Los cambios no se guardar&aacute;n</p>
	</div>
	<p class="form_nav">	
		<button class="button" onclick="window.open('${pageContext.request.contextPath}/logout.do', '_self');">Aceptar</button>
	</p>
</div>

<div dojoType="dijit.Dialog" id="msgError" title="Error" draggable="false" style="display:none">
	<div class="msg_contain">
		<p>Ha ocurrido un error.</p>
	</div>
	<p class="form_nav">	
		<button class="button" onclick="dijit.byId('msgError').hide();">Aceptar</button>
	</p>
</div>

<div dojoType="dijit.Dialog" id="dialogCurpNoRenapo" title="Error" draggable="false" style="display:none">
	<div class="msg_contain">
		<p>
			La CURP no existe, favor de verificarla. Si tu CURP es correcta visita la siguiente url <a href="http://www.renapo.gob.mx/swb/swb/RENAPO/tramite">http://www.renapo.gob.mx/swb/swb/RENAPO/tramite</a> para ver si existe un problema con tu CURP.
		</p>
	</div>
	<p class="form_nav">
		<button class="button" onclick="dijit.byId('dialogCurpNoRenapo').hide();">Cerrar</button>
	</p>
</div>

<div dojoType="dijit.Dialog" id="dialogDatosNoRenapo" title="Error" draggable="false" style="display:none">
	<div class="msg_contain">
		<p>
			Favor de revisar los datos proporcionados. Si tus datos son correctos visita la siguiente  http://www.renapo.gob.mx/swb/swb/RENAPO/tramite para ver si existe un problema con tu CURP.
		</p>
	</div>
	<p class="form_nav">
		<button class="button" onclick="dijit.byId('dialogDatosNoRenapo').hide();">Cerrar</button>
	</p>
</div>

<div dojoType="dijit.Dialog" id="dialogCurpRegistradaFueraPPC" title="Error" draggable="false" style="display:none">
	<div class="msg_contain">
		<p>
			Tenemos registrado que ya participaste en el programa del Programa de Promoci&oacute;n y Colocaci&oacute;n del Seguro de Desempleo (PPC-SD) por lo tanto no se puede llevar a cabo tu inscripci&oacute;n. Consulta las <a href="#" onclick="window.open('${pageContext.request.contextPath}/registro.do?method=toTerminosGenerales', '_self');">pol&iacute;ticas de inscripci&oacute;n</a> del PPC-SD  o acude a alguna de nuestras <a href="<%=urlRedirect%>">oficinas del SNE</a>.
		</p>
	</div>
	<p class="form_nav">
		<button class="button" onclick="dijit.byId('dialogCurpRegistradaFueraPPC').hide();">Cerrar</button>
	</p>	
</div>

