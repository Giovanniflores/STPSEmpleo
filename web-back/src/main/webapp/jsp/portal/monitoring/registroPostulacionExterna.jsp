<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>

<fmt:setBundle basename="MessageResources" var="portalbundle"/>
<fmt:setBundle basename="portal-web" var="messages"/>

<c:set var="regexprazonsocial">^[A-Za-z\s\d\·ÈÌÛ˙¡…Õ”⁄Ò—']{3,150}$</c:set>
<c:set var="regexprazonsocial_alt">^[A-Za-z\s\d\-.&,·ÈÌÛ˙¡…Õ”⁄Ò—']{3,150}$</c:set>
<c:set var="regexpnombrecontacto">^[A-Za-z\s\d\·ÈÌÛ˙¡…Õ”⁄Ò—']{1,150}$</c:set>
<c:set var="regexpnombrecontacto_alt">^[A-Za-z\s\-.·ÈÌÛ˙¡…Õ”⁄Ò—']{1,150}$</c:set>
<c:set var="regexptitulo">^[A-Za-z\s\d\·ÈÌÛ˙¡…Õ”⁄Ò—']{1,150}$</c:set>
<c:set var="regexptitulo_alt">^[A-Za-z\s\d\-.&,·ÈÌÛ˙¡…Õ”⁄Ò—']{1,150}$</c:set>
<c:set var="regexpsalario">^[+]?\d{1,6}(\.\d{1,2})?$</c:set>
<c:set var="regexpcomoteenteraste">^[A-Za-z0-9\s\·ÈÌÛ˙¡…Õ”⁄Ò—']{1,150}$</c:set>
<c:set var="regexpcomoteenteraste_alt">^[A-Za-z0-9\s\-.·ÈÌÛ˙¡…Õ”⁄Ò—']{1,150}$</c:set>
<c:set var="regexpmail">^[a-zA-Z0-9_-]+(?:\.[a-zA-Z0-9_-]+)*@(?:[a-zA-Z0-9](?:[a-zA-Z0-9-_]*[a-zA-Z0-9-])?\.)+[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?$</c:set>
<c:set var="regexpacceso">^(044|01)$</c:set>

<%
String context = request.getContextPath() +"/";  
%>

<script type="text/javascript">
	dojo.require("dijit.dijit"); // loads the optimized dijit layer
	dojo.require("dijit.form.Form");
	dojo.require("dijit.Dialog");
	dojo.require("dijit.form.Button");
	dojo.require("dijit.form.ComboBox");
	dojo.require("dojo.data.ItemFileReadStore");
	dojo.require("dijit.form.TextBox");
	dojo.require("dijit.form.ValidationTextBox");
	dojo.require("dijit.form.DateTextBox");
	dojo.require("dijit.form.FilteringSelect");
	
	dojo.addOnLoad(function() {
			    	
		var idTipoTelefonoFijo = '${TELEFONO_FIJO}';
		var idTipoTelefonoCelular = '${TELEFONO_CELULAR}';		
		var accesoFijo = '${CLAVE_TELEFONO_FIJO}';
		var accesoCelular = '${CLAVE_TELEFONO_CELULAR}';		
		
	    //var acceso = '${registroPostulacionExternaForm.acceso}';	   
	    //if(acceso==null||acceso==""){	
		//    document.getElementById('acceso').value = '${registroPostulacionExternaForm.accesoFijo}'; 
	    //}	    
	});	


	function limitTextArea(element, limit) { 
		if (element){
			if (element.value.length > limit) { 
				element.value = element.value.substring(0, limit); 
			} 		
		}
	} 
	
	
	function registrar(){	
		estableceValores();
		if (!validaCampos()) return;
		//doSubmitPostulacion('registrar');
		validarDatosCorrectos();
	}
	
	
	function estableceValores(){
		estableceTipoTelefono();
		estableceValoresFechaContacto();
		estableceValoresFechaEntrevista();
	}	
	
	
	function doSubmitPostulacion(method){
		document.getElementById('method').value=method;
		document.getElementById('btnGuardar').disabled=true;
		document.getElementById('registroPostulacionForm').submit();
		//dojo.byId('method').value=method;
		//dojo.byId('btnGuardar').disabled=true;
		//dojo.byId('registroPostulacionForm').submit();
	}		

	
	function validarDatosCorrectos(){
		var datosCorrectos = true;
		
		dojo.byId('method').value='validarDatosCorrectos';

		dojo.xhrPost({url: 'registroPostulacionExterna.do', form:'registroPostulacionForm', sync: true, 
			  		  load: function(data){
			  					var res = dojo.fromJson(data);
			  					
			  					if('exito' == res.type){
			  						doSubmitPostulacion('registrar');
			  					} else if('error' == res.type){
			  						datosCorrectos = false;
			  						alertMsg(res.message);
			  					}
			 				}
					});

		return datosCorrectos;
	}	
	
	
	function validaCampos(){
		
		if (!validaCampo('nombreEmpresa')) return false;
		
		if (!validaCampo('nombreContacto')) return false;
		
		if (!validaCampo('cargoContacto')) return false;
		
		if (!hasTelPrinc()){
			if(!document.getElementById('telefonoFijo').checked && !document.getElementById('telefonoCelular').checked){
	 			alertMsg('El tipo de telÈfono es requerido.');				
				document.getElementById("telefonoFijo").focus();
				return false;
			}else if (!validaCampo('clave')){
				displayErrorMsg(dijit.byId('clave'), dijit.byId('clave').get("missingMessage"));				
				//alertMsg('La clave LADA es requerida.');				
				document.getElementById("clave").focus();				
				return false;
			}else if (!validaCampo('telefono')){
				//alertMsg('El telÈfono es requerido.');
				displayErrorMsg(dijit.byId('telefono'), dijit.byId('telefono').get("missingMessage"));
				document.getElementById("telefono").focus();				
				return false;
			}
		} else {
			
			var vClave = dijit.byId('clave');
			var vTelefono = dijit.byId('telefono');
			var vExtension = dijit.byId('extension');
			if (!validaCampo('clave')) return false;
			if (!validaCampo('telefono')) return false;
			
			if(vClave.value.length == 2) {
				if(vTelefono.value.length != 8){
					//alertMsg('Debe proporcionar un telÈfono de 8 dÌgitos');
					displayErrorMsg(dijit.byId('telefono'), "Debe proporcionar un telÈfono de 8 dÌgitos");
					vClave.focus();
					return false;
				}
			} else if(vClave.value.length==3){
				if(vTelefono.value.length!=7){
					//alertMsg('Debe proporcionar un telÈfono de 7 dÌgitos');
					displayErrorMsg(dijit.byId('telefono'), "Debe proporcionar un telÈfono de 7 dÌgitos");
					vClave.focus();
					return false;
				}	
			}	
			
			if(vExtension.value.length>0 ){
				if (!validaCampo('extension')){
					alertMsg('Extension errÛnea, debe ser un valor numÈrico (0-9).');
					vClave.focus();
					return false;							
				}				
			}			
		}
		
		if(dijit.byId('correoElectronico').value!=''){
			if (!validaCampo('correoElectronico')) return false;
		}
		
		if (!validaCampo('tituloOferta')) return false;

		if (!validaCampo('salarioMensual')) return false;
		if (dijit.byId('salarioMensual').get('value')<=0){
			dijit.byId('salarioMensual').set('value','');			
			validaCampo('salarioMensual');
			return false;	
		}		
		
		if (!validaCampo('comoTeEnterasteOferta')) return false;
		
		if (!dijit.byId('diaContactoSelect').isValid() || dojo.byId('diaContactoSelect').value=='DÌa'){
			//alertMsg('Debe seleccionar el dÌa de fecha de contacto.');
			displayErrorMsg(dijit.byId('diaContactoSelect'), "Debe seleccionar el dÌa de fecha de contacto.");
			//dijit.byId('diaContactoSelect').focus();
			return false;
		}
		if (!dijit.byId('mesContactoSelect').isValid() || dojo.byId('mesContactoSelect').value=='Mes'){
			//alertMsg('Debe seleccionar el mes de fecha de contacto.');
			displayErrorMsg(dijit.byId('mesContactoSelect'), "Debe seleccionar el mes de fecha de contacto.");
			//dijit.byId('mesContactoSelect').focus();
			return false;
		}
		if (!dijit.byId('anioContactoSelect').isValid() || dojo.byId('anioContactoSelect').value=='AÒo'){
			//alertMsg('Debe seleccionar el aÒo de fecha de contacto.');
			displayErrorMsg(dijit.byId('anioContactoSelect'), "Debe seleccionar el aÒo de fecha de contacto.");
			//dijit.byId('anioContactoSelect').focus();
			return false;
		}		
		
		if (!dijit.byId('diaEntrevistaSelect').isValid() || dojo.byId('diaEntrevistaSelect').value=='DÌa'){
			//alertMsg('Debe seleccionar el dÌa de fecha de entrevista.');
			displayErrorMsg(dijit.byId('diaEntrevistaSelect'), "Debe seleccionar el dÌa de fecha de entrevista.");
			//dijit.byId('diaEntrevistaSelect').focus();
			return false;
		}
		if (!dijit.byId('mesEntrevistaSelect').isValid() || dojo.byId('mesEntrevistaSelect').value=='Mes'){
			//alertMsg('Debe seleccionar el mes de fecha de entrevista.');
			displayErrorMsg(dijit.byId('mesEntrevistaSelect'), "Debe seleccionar el mes de fecha de entrevista.");
			//dijit.byId('mesEntrevistaSelect').focus();
			return false;
		}
		if (!dijit.byId('anioEntrevistaSelect').isValid() || dojo.byId('anioEntrevistaSelect').value=='AÒo'){
			//alertMsg('Debe seleccionar el aÒo de fecha de entrevista.');
			displayErrorMsg(dijit.byId('anioEntrevistaSelect'), "Debe seleccionar el aÒo de fecha de entrevista.");
			//dijit.byId('anioEntrevistaSelect').focus();
			return false;
		}		
		
		return true;
	}
	
	
	function validaCampo(campo){
		var control = dijit.byId(campo);
		
		if (control && control.value==''){
			dojo.byId(control).focus();
			//alertMsg("El " + control.get("missingMessage") + " es requerido.");
			displayErrorMsg(dijit.byId(campo), dijit.byId(campo).get("missingMessage"));
			return false;
		}
		
		if (!dijit.byId(campo).isValid()){
			dojo.byId(control).focus(); 
			//alertMsg(control.get("invalidMessage"));
			displayErrorMsg(dijit.byId(campo), dijit.byId(campo).get("invalidMessage"));
			return false;
		}
	
		return true;
	}	
	
	
	
	
	function validaCampoSinMensaje(campo){
		var control = dijit.byId(campo);
		
		if (control && control.value==''){
			return false;
		}
		
		if (!dijit.byId(campo).isValid()){
			return false;
		}
	
		return true;
	}		

	
	function hasTelPrinc() {	
		if ((!document.getElementById('telefonoFijo').checked && !document.getElementById('telefonoCelular').checked) 
				|| !validaCampoSinMensaje('clave') || !validaCampoSinMensaje('telefono')){
			return false;			
		}
		return true;
	}	
	
	
	function estableceTipoTelefono(){
		
		if(document.getElementById('telefonoFijo').checked){
			document.getElementById('idTipoTelefono').value = '${TELEFONO_FIJO}';
			estableceAcceso();
			
		} else if(document.getElementById('telefonoCelular').checked){
			document.getElementById('idTipoTelefono').value = '${TELEFONO_CELULAR}';
			estableceAcceso();
		}	
	}
	
	
    function estableceValoresFechaContacto(){
    	dojo.byId('diaContacto').value  = dijit.byId('diaContactoSelect').get('value');    	
    	dojo.byId('mesContacto').value  = dijit.byId('mesContactoSelect').get('value');
    	dojo.byId('anioContacto').value = dijit.byId('anioContactoSelect').get('value');
    }		
    
    
    function estableceValoresFechaEntrevista(){
    	dojo.byId('diaEntrevista').value  = dijit.byId('diaEntrevistaSelect').get('value');    	
    	dojo.byId('mesEntrevista').value  = dijit.byId('mesEntrevistaSelect').get('value');
    	dojo.byId('anioEntrevista').value = dijit.byId('anioEntrevistaSelect').get('value');    	
    }	    
    
    
	function estableceAcceso(){
		if(document.getElementById('telefonoFijo').checked){
			document.getElementById('accesoDiv').innerHTML = '${CLAVE_TELEFONO_FIJO}';
			document.getElementById('acceso').value = '${CLAVE_TELEFONO_FIJO}';
			dijit.byId('extension').disabled=false;
			document.getElementById('extension').readOnly=false;
			 
		} else if(document.getElementById('telefonoCelular').checked){
			document.getElementById("accesoDiv").innerHTML = '${CLAVE_TELEFONO_CELULAR}';
			document.getElementById('acceso').value = '${CLAVE_TELEFONO_CELULAR}';
			document.getElementById('extension').value = '';
			dijit.byId('extension').disabled=true;
			document.getElementById('extension').readOnly=true;
			 
		} else {
			document.getElementById("accesoDiv").innerHTML = '${CLAVE_TELEFONO_FIJO}';
			document.getElementById('acceso').value = '${CLAVE_TELEFONO_FIJO}';
		}
	}    
  
    
	function changePhoneSizeRequired(){
		var vClave = dijit.byId('clave');
		var vTelefono = dijit.byId('telefono');
		 
		if(vClave.value.length < 2){
			vClave.focus();
			displayErrorMsg(vClave, 'Debe proporcionar una clave LADA de 2 o 3 caracteres');
			return false;
		} else if(vClave.value.length == 2) {
			if(vTelefono.value.length != 8){
				displayErrorMsg(vTelefono, 'Debe proporcionar un telÈfono de 8 dÌgitos');
				return false;
			}
		} else if(vClave.value.length==3){
			if(vTelefono.value.length!=7){
				displayErrorMsg(vTelefono, 'Debe proporcionar un telÈfono de 7 dÌgitos');
				return false;
			}	
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
	
	
    function enviarAMiEspacioCandidato(){
    	dijit.byId('MensajeAlerConfirt').hide();
    	window.location.href = '${context}registroPostulacionExterna.do?method=enviarAMiEspacioCandidato';
    }
    

    function enviarASeguimiento(){
    	dijit.byId('MensajeAlerConfirt').hide();
    	dojo.byId('method').value='enviarASeguimiento';
    	dojo.byId('registroPostulacionForm').submit();

    }
    
    
    function capturarOtra(){
    	dijit.byId('MensajeAlerConfirt').hide();
    	window.location.href = '${context}registroPostulacionExterna.do?method=limpiarFormulario';
    
    }    
    
    
    function cancelar(){
		dojo.byId('mensajeConfir').innerHTML = 'Los datos no guardados se perder·n øContinuar?';
		dijit.byId('MensajeAlerConfirt').show();		
    }
    
    function closeDialog(id){
        dijit.byId('MensajeAlerConfirt').hide();
	}    
    

  	function cerrarError(){  		
        dijit.byId('msgErrores').hide();
  	}    
  	
  	
	function alertMsg(msg){
		dojo.byId('contenidoMensajeError').innerHTML = msg;
		dijit.byId('msgErroresVal').show();
	}
	

    function closeMsg(){
        dijit.byId('msgErroresVal').hide();
	} 	  	
	
</script>

<c:if test="${not empty ERROR_MSG}">
	<script type="text/javascript">	
		dojo.addOnLoad(function(){
			dijit.byId('msgErrores').show();
		});
		
	</script>
</c:if>

<c:if test="${not empty CONFIRMAR_OTRA_MAS && CONFIRMAR_OTRA_MAS eq true}">
	<script type="text/javascript">
		dojo.addOnLoad(function(){
			dijit.byId('MensajeAlerConfirt2').show();
		});
	</script>
</c:if>


<noscript>Funciones de JavaScript desactivadas por navegador</noscript>

<div id="mis_ofertas" class="formApp miEspacio">

        <h2>Mi espacio</h2>

        <div class="tab_block">
            <div align="left" style="display:block;" id="returnME">
                <a class="expand" href="javascript:location.replace('<c:url value="/miEspacioCandidato.do"/>');">
                    <strong>Ir al inicio de Mi espacio</strong>
                </a>
            </div>
            <div class="Tab_espacio">
                <h3>Mis ofertas de empleo</h3>

                <div class="subTab">
                    <ul>
                        <li>
                            <a href="<c:url value="/misofertas.do?method=misPostulaciones"/>">Mis postulaciones</a>
                        </li>
                        <li>
                            <a href="<c:url value="/misofertas.do?method=misOfertasGuardadas"/>">Mis ofertas guardadas</a>
                        </li>
                        <li>
                            <a href="<c:url value="/misofertas.do?method=empresasQueMeBuscan"/>">Empresas que me buscan</a>
                        </li>
                        <!--INI_JGLC_PPC-->
<!--                         <li class="curSubTab"> -->
<!--                             <span>Registrar postulaciones externas</span> -->
<!--                         </li> -->
<!--                         <li> -->
<!--                         	<a id="seguimiento_postulaciones_externas" href="seguimientoPostulacionExterna.do?method=init">Seguimiento a postulaciones externas</a>                            -->
<!--                         </li> -->
<!--                         <li> -->
<!--                             <a href="/adminNoPostulacionesCandidato.do?method=init" id="registrar_motivo_no_postulacion">Registrar motivo de no postulaci&oacute;n</a> -->
<!--                         </li> -->
                        <!--FIN_JGLC_PPC-->
                    </ul>
                    <div class="clearfix"></div>
                </div>
            </div>
        </div>


<div dojoType="dojo.data.ItemFileReadStore" jsId="diasContactoStore"	url="${context}registroPostulacionExterna.do?method=dias"></div>
<div dojoType="dojo.data.ItemFileReadStore" jsId="mesesContactoStore"   url="${context}registroPostulacionExterna.do?method=meses"></div>
<div dojoType="dojo.data.ItemFileReadStore" jsId="aniosContactoStore"   url="${context}registroPostulacionExterna.do?method=aniosContacto"></div>
<div dojoType="dojo.data.ItemFileReadStore" jsId="diasEntrevistaStore"	url="${context}registroPostulacionExterna.do?method=dias"></div>
<div dojoType="dojo.data.ItemFileReadStore" jsId="mesesEntrevistaStore"   url="${context}registroPostulacionExterna.do?method=meses"></div>
<div dojoType="dojo.data.ItemFileReadStore" jsId="aniosEntrevistaStore"   url="${context}registroPostulacionExterna.do?method=aniosEntrevista"></div>


	<form name="registroPostulacionForm" id="registroPostulacionForm" method="post" action="registroPostulacionExterna.do" dojoType="dijit.form.Form">
	 	<input type="hidden" name="method" 			id="method" 		value="init" 											dojoType="dijit.form.TextBox"/>
	 	<input type="hidden" name="accesoCelular" 	id="accesoCelular"  value="${CLAVE_TELEFONO_CELULAR}" 				dojoType="dijit.form.TextBox"		/>
	 	<input type="hidden" name="accesoFijo" 		id="accesoFijo"     value="${CLAVE_TELEFONO_FIJO}" 					dojoType="dijit.form.TextBox"	/>
	 	<input type="hidden" name="idTipoTelefonoCelular" 	id="idTipoTelefonoCelular"  value="${TELEFONO_CELULAR}" 		dojoType="dijit.form.TextBox"		/>
	 	<input type="hidden" name="idTipoTelefonoFijo" 		id="idTipoTelefonoFijo"     value="${TELEFONO_FIJO}" 	dojoType="dijit.form.TextBox"	/>
	 	
	 	<input type="hidden" name="idTipoTelefono" 	id="idTipoTelefono" value="${registroPostulacionExternaForm.idTipoTelefono}" 		dojoType="dijit.form.TextBox"/>
	 	<input type="hidden" name="acceso" 			id="acceso"   		value="${registroPostulacionExternaForm.acceso}" 				dojoType="dijit.form.TextBox"/>
	 	<input type="hidden" name="diaContacto"		id="diaContacto"	value="${registroPostulacionExternaForm.diaContacto}" 			dojoType="dijit.form.TextBox"/>
	 	<input type="hidden" name="mesContacto" 	id="mesContacto" 	value="${registroPostulacionExternaForm.mesContacto}" 			dojoType="dijit.form.TextBox"/>
	 	<input type="hidden" name="anioContacto" 	id="anioContacto" 	value="${registroPostulacionExternaForm.anioContacto}" 		dojoType="dijit.form.TextBox"/>
	 	<input type="hidden" name="diaEntrevista"	id="diaEntrevista"	value="${registroPostulacionExternaForm.diaEntrevista}" 		dojoType="dijit.form.TextBox"/>
	 	<input type="hidden" name="mesEntrevista" 	id="mesEntrevista" 	value="${registroPostulacionExternaForm.mesEntrevista}" 		dojoType="dijit.form.TextBox"/>
	 	<input type="hidden" name="anioEntrevista" 	id="anioEntrevista" value="${registroPostulacionExternaForm.anioEntrevista}" 		dojoType="dijit.form.TextBox"/>
			
			<h3>Registrar postulaciones externas</h3>
			<div class="postulacione_e">
				<fieldset>
			    	<legend>Datos de la empresa</legend>
			        <div class="grid1_3 margin_top_20">
			            <label for="nombreEmpresa"><span>*</span> Nombre de la empresa que ofrece la oferta</label>
			            <input type="text" name="nombreEmpresa" id="nombreEmpresa"
			               required="true" missingMessage="El nombre de la empresa que ofrece la oferta es requerido."
			               regExp="${regexprazonsocial}" trim="true" 
			               invalidMessage="Nombre de la empresa que ofrece la oferta inv·lido, no se permiten caracteres especiales."
			               value="${registroPostulacionExternaForm.nombreEmpresa}" 
			               onkeydown="limitTextArea(this,150);" onkeyup="limitTextArea(this,150);" 
			        	   maxlength="150" dojoType="dijit.form.ValidationTextBox"/>	    
			        </div>
			        <div class="margin_top_20">
			            <div class="grid1_3 fl">
				            <label for="nombreContacto"><span>*</span> Persona de contacto</label>
				            <input type="text" name="nombreContacto" id="nombreContacto"
				               required="true" missingMessage="El nombre de la persona de contacto es requerido."
				               regExp="${regexpnombrecontacto}" trim="true" 
				               invalidMessage="Nombre de la persona de contacto inv·lido, no se permiten caracteres especiales."
				               value="${registroPostulacionExternaForm.nombreContacto}" 
				               onkeydown="limitTextArea(this,150);" onkeyup="limitTextArea(this,150);" 
				        	   maxlength="150" dojoType="dijit.form.ValidationTextBox"/>	    
			            </div>
			            <div class="grid1_3 fl">
				            <label for="cargoContacto"><span>*</span> Cargo</label>
				            <input type="text" name="cargoContacto" id="cargoContacto" trim="true" 
				            	required="true" missingMessage="El cargo es requerido." regExp="${regexpnombrecontacto}"  
				               invalidMessage="Cargo inv·lido, no se permiten caracteres especiales." 
				               value="${registroPostulacionExternaForm.cargoContacto}" 
				               onkeydown="limitTextArea(this,150);" onkeyup="limitTextArea(this,150);"  
				        	   maxlength="150" dojoType="dijit.form.ValidationTextBox"/>	    
			            </div>
			            <div class="clearfix"></div>
			        </div>
			    </fieldset>	
			    
			    <fieldset>
			    	<legend>Datos de contacto</legend>			        	
		        	<div class="labeled"><span>*</span> TelÈfono</div>
		        	<p><em>Debes ingresar un total de 10 dÌgitos (Clave LADA + TelÈfono).</em></p>
			        <div class="grid1_3  margin_top_20 fl">
			            <div class="margin-r_20 fl">
			                <div class="labeled margin_no_t"><span>*</span> Tipo de telÈfono</div>
			                <div class="fl">   
			                	<c:choose>
			                		<c:when test="${empty registroPostulacionExternaForm.idTipoTelefono}">
										<div class="tipo_tel margin_top_10 fl">
											<input type="radio" name="tipoTelefono" id="telefonoFijo"
										       value="${registroPostulacionExternaForm.idTipoTelefonoFijo}" onclick="estableceAcceso()">&nbsp;
										       <label class="fl" for="telefonoFijo">Fijo</label>
										</div>
										<div class="tipo_tel margin_top_10 fl">
											<input type="radio" name="tipoTelefono" id="telefonoCelular"
									    	   value="${registroPostulacionExternaForm.idTipoTelefonoCelular}" onclick="estableceAcceso()">&nbsp;
									    	   <label class="fl" for="telefonoCelular">Celular</label>
										</div>				                		
			                		</c:when>
			                		<c:otherwise>
			                			<c:choose>
			                				<c:when test="${registroPostulacionExternaForm.idTipoTelefono eq registroPostulacionExternaForm.idTipoTelefonoCelular}">
												<div class="tipo_tel margin_top_10 fl">
													<input type="radio" name="tipoTelefono" id="telefonoFijo"
												       value="${registroPostulacionExternaForm.idTipoTelefonoFijo}" onclick="estableceAcceso()">&nbsp;
												       <label class="fl" for="telefonoFijo">Fijo</label>
												</div>
												<div class="tipo_tel margin_top_10 fl">
													<input type="radio" name="tipoTelefono" id="telefonoCelular" checked
											    	   value="${registroPostulacionExternaForm.idTipoTelefonoCelular}" onclick="estableceAcceso()">&nbsp;
											    	   <label class="fl" for="telefonoCelular">Celular</label>
												</div>			                				
			                				</c:when>
			                				<c:otherwise>
												<div class="tipo_tel margin_top_10 fl">
													<input type="radio" name="tipoTelefono" id="telefonoFijo" checked
												       value="${registroPostulacionExternaForm.idTipoTelefonoFijo}" onclick="estableceAcceso()">&nbsp;
												       <label class="fl" for="telefonoFijo">Fijo</label>
												</div>
												<div class="tipo_tel margin_top_10 fl">
													<input type="radio" name="tipoTelefono" id="telefonoCelular"
											    	   value="${registroPostulacionExternaForm.idTipoTelefonoCelular}" onclick="estableceAcceso()">&nbsp;
											    	   <label class="fl" for="telefonoCelular">Celular</label>
												</div>			                					                				                				
			                				</c:otherwise>
			                			</c:choose>			                						                			
			                		</c:otherwise>
			                	</c:choose>

			                </div>
			            </div>
			            <div class="margin-r_10 fl">
							<label for="accesoDiv" class="fw_no_bold" ><span>*</span> Acceso</label>
							<div class="ta_center margin_top_10">
								<span id="accesoDiv" >${registroPostulacionExternaForm.acceso}</span>
							</div>
			            </div>
			            <div class="fl">
							<label for="clave"><span>*</span> Clave LADA</label>
					         	<input type="text" name="clave" id="clave" maxlength="3" style="width:3em;" value="${registroPostulacionExternaForm.clave}" 
					         	dojoType="dijit.form.ValidationTextBox" 
					         	required="false" regExp="^[+]?\d{2,3}$" trim="true" 
					         	invalidMessage="Clave LADA errÛnea, debe ser un valor numÈrico." 
					         	missingMessage="La clave LADA es requerida." trim="true" onBlur="changePhoneSizeRequired();" >
			            </div>
			        </div>
			        <div class="margin_top_20 fl">
						<label for="telefono"><span>*</span> TelÈfono</label>
				     	<input type="text" name="telefono" id="telefono" maxlength="8"  style="width:8em;" 
				     		value="${registroPostulacionExternaForm.telefono}" onBlur="changePhoneSizeRequired();" 
				     	dojoType="dijit.form.ValidationTextBox" required="false" regExp="^[+]?\d{7,8}$" 
				     	invalidMessage="Debe proporcionar un telÈfono de (7 u 8) dÌgitos" missingMessage="El telÈfono es requerido." trim="true" 
				     	onBlur="changePhoneSizeRequired();" >	
			        </div>
			        <div class="margin_top_20 fl">
						<label for="extension"><span></span> ExtensiÛn</label>
			            <input class="sugerido" type="text" name="extension" id="extension" maxlength="6"  style="width:8em;" 
			            	value="${registroPostulacionExternaForm.extension}" 
			            	dojoType="dijit.form.ValidationTextBox" required="false" 			            	  
			            	regExp="^[+]?\d{0,6}$" invalidMessage="ExtensiÛn telefÛnica errÛnea, debe ser un valor numÈrico (0-9)." 
			            	missingMessage="" trim="true">
			        </div>
			        <div class="clearfix"></div>
			        <div class="margin_top_30">
					  	<label for="correoElectronico"><span></span> Correo electrÛnico</label>	
			          	<input type="text" id="correoElectronico" name="correoElectronico"
			                 value="${registroPostulacionExternaForm.correoElectronico}" required="false"
			                 dojoType="dijit.form.ValidationTextBox" maxlength="65" trim="false;"
			                 oncopy="return false;" oncut="return false" onpaste="return false;" 
			                 onkeydown="limitTextArea(this,65);" onkeyup="limitTextArea(this,65);" 
			                 missingMessage="El correo electrÛnico es requerido." regExp="${regexpmail}" 
			                 invalidMessage="Formato de correo electrÛnico inv·lido."/>				       
			       </div>
			    </fieldset>	
			    	
				<fieldset>
			    	<legend>Datos de la oferta</legend>
			        <div class="margin_top_20">
			            <div class="grid1_3 fl">
				            <label for="tituloOferta"><span>*</span> TÌtulo de la oferta</label>
				            <input type="text" name="tituloOferta" id="tituloOferta"
				               required="true" missingMessage="El tÌtulo de la oferta es requerido."
				               regExp="${regexptitulo}" trim="true" 
				               invalidMessage="TÌtulo de la oferta inv·lido, no se permiten caracteres especiales." 
				               onkeydown="limitTextArea(this,150);" onkeyup="limitTextArea(this,150);" 
				               value="${registroPostulacionExternaForm.tituloOferta}" 
				        	   maxlength="150" dojoType="dijit.form.ValidationTextBox"/>
			            </div>
			            <div class="grid1_3 fl">
				            <label for="salarioMensual"><span>*</span> Salario mensual</label>
				            <input type="text" name="salarioMensual" id="salarioMensual"
				               required="true" missingMessage="El salario mensual es requerido."
				               regExp="${regexpsalario}" 
				               invalidMessage="salario mensual inv·lido favor de verificar."
				               value="${registroPostulacionExternaForm.salarioMensual}" 
				        	   maxlength="9" dojoType="dijit.form.ValidationTextBox"/>	    
				        </div>								
			            <div class="clearfix"></div>
			        </div>
					<div class="grid1_3 margin_top_20">
				            <label for="comoTeEnterasteOferta"><span>*</span> øCÛmo te enteraste de la oferta de empleo?</label>
				            <input type="text" name="comoTeEnterasteOferta" id="comoTeEnterasteOferta"
				               required="true" missingMessage="El cÛmo te enteraste de la oferta de empleo es requerido."
				               regExp="${regexpcomoteenteraste}" trim="true" 
				               onkeydown="limitTextArea(this,150);" onkeyup="limitTextArea(this,150);" 
				               invalidMessage="CÛmo te enteraste de la oferta de empleo inv·lido, no se permiten caracteres especiales."
				               value="${registroPostulacionExternaForm.comoTeEnterasteOferta}" 
				        	   maxlength="150" dojoType="dijit.form.ValidationTextBox"/>	
					</div>
					<div class="clearfix"></div>
			    </fieldset>		
			    	    		    
			    <fieldset class="fechas_p">
			    	<legend>Fechas</legend>
			        <div class="margin_top_20">
			            <div class="grid1_3 fl">
			                <div class="labeled"><span>*</span> Fecha de contacto</div>
							<select id="diaContactoSelect" name="diaContactoSelect" 
									required="true" missingMessage="El dÌa de fecha de contacto es requerido."
									invalidMessage="DÌa de fecha de contacto inv·lido, no se permiten caracteres especiales." 
									promptMessage="DÌa de fecha de contacto" maxHeight="250" 
									regExp="${regexpdia}" 
						        	value="${registroPostulacionExternaForm.diaContacto}" autocomplete="true"
						        	dojotype="dijit.form.FilteringSelect" store="diasContactoStore">
						    </select>

			                </select>
							<select id="mesContactoSelect" name="mesContactoSelect"
								    required="false" missingMessage="El mes de fecha de contacto es requerido."
								    invalidMessage="Mes de fecha de contacto inv·lido, no se permiten caracteres especiales."
							    	promptMessage="Mes de fecha de contacto" maxHeight="250" 
						        	value="${registroPostulacionExternaForm.mesContacto}" autocomplete="true"
						        	dojotype="dijit.form.FilteringSelect" store="mesesContactoStore">
						    </select>

							<select id="anioContactoSelect" name="anioContactoSelect"
									required="false" missingMessage="El aÒo de fecha de contacto es requerido."
									invalidMessage="AÒo de fecha de contacto inv·lido, no se permiten caracteres especiales." 
									promptMessage="AÒo de fecha de contacto" maxHeight="250" 
						        	value="${registroPostulacionExternaForm.anioContacto}" autocomplete="true"
						        	dojotype="dijit.form.FilteringSelect" store="aniosContactoStore">
						    </select>
			            </div>
			            <div class="grid1_3 fl">
			                <div class="labeled"><span>*</span> Fecha de entrevista</div>
							<select id="diaEntrevistaSelect" name="diaEntrevistaSelect" 
									required="true" missingMessage="El dÌa de fecha de entrevista es requerido."
									invalidMessage="DÌa de fecha de entrevista inv·lido, no se permiten caracteres especiales." 
									promptMessage="DÌa de fecha de entrevista" maxHeight="250" 
									regExp="${regexpdia}"
						        	value="${registroPostulacionExternaForm.diaEntrevista}" autocomplete="true"
						        	dojotype="dijit.form.FilteringSelect" store="diasEntrevistaStore">
						    </select>
							<select id="mesEntrevistaSelect" name="mesEntrevistaSelect"
								    required="false" missingMessage="El mes de fecha de entrevista es requerido."
								    invalidMessage="Mes de fecha de entrevista inv·lido, favor de verificar."
							    	promptMessage="Mes de fecha de entrevista" maxHeight="250"
						        	value="${registroPostulacionExternaForm.mesEntrevista}" autocomplete="true"
						        	dojotype="dijit.form.FilteringSelect" store="mesesEntrevistaStore">
						    </select>
							<select id="anioEntrevistaSelect" name="anioEntrevistaSelect"
									required="false" missingMessage="El aÒo de fecha de entrevista es requerido."
									invalidMessage="AÒo de fecha de entrevista inv·lido, no se permiten caracteres especiales." 
									promptMessage="AÒo de fecha de entrevista" maxHeight="250"
						        	value="${registroPostulacionExternaForm.anioEntrevista}" autocomplete="true"
						        	dojotype="dijit.form.FilteringSelect" store="aniosEntrevistaStore">
						    </select>
			            </div>
			        </div>
			    </fieldset>
			    
				<div class="form_nav" id="div_form_nav">
						<input id="btnGuardar" name="btnGuardar" type="button" value="Guardar" 		onclick="registrar();"/>
						<input id="btnCancel"  name="btnCancel"  type="button" value="Cancelar" 	onclick="cancelar();"/>
				</div>			    			    		
			</div>		
		</form>
		
		
		<div dojoType="dijit.Dialog" id="msgErroresVal" title="Error" draggable="false" style="display:none">
			<div class="msg_contain">
				<p><div id="contenidoMensajeError"></div></p>
			</div>
			<p class="form_nav">	
				<button class="button" onclick="closeMsg();">Aceptar</button>
			</p>
		</div>
		
		
		<div dojoType="dijit.Dialog" id="msgErrores" title="Error" draggable="false" style="display:none">
			<div class="msg_contain">
				<p>${ERROR_MSG}</p>
			</div>
			<p class="form_nav">	
				<button class="button" onclick="cerrarError();">Aceptar</button>
			</p>
		</div>
		
		
		<div dojoType="dijit.Dialog" id="MensajeAlerConfirt" title="Aviso" draggable ="false" class="suelto">
			<div class="msg_contain">
				<p><div id ="mensajeConfir"></div></p>
				<br/><br/>
				
				<p class="form_nav 2" >
					<button id="btnAceptar"  class="button" onclick="enviarAMiEspacioCandidato();">Aceptar</button>										
					<button id="btnCancelar" class="button" onclick="closeDialog();">Cancelar</button>							
				</p>
			</div>
		</div>

		
		<div dojoType="dijit.Dialog" id="MensajeAlerConfirt2" title="Aviso" draggable ="false" class="suelto">
			<div class="msg_contain">
				<p><strong>Aviso</strong><br/><br/>
					Ha quedado registrada tu postulaciÛn externa.<br/>
					<strong>øDesea registrar otra postulaciÛn?</strong><br/>
				</p>
				<br/><br/>				
				<p class="form_nav 2" >
					<button id="btnNo" class="button" onclick="capturarOtra();">SÌ</button>										
					<button id="btnSi" class="button" onclick="enviarASeguimiento();">No</button>		
				</p>
			</div>
		</div>
		
</div>


