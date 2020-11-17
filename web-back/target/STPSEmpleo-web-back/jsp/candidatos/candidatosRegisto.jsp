<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="mx.gob.stps.portal.persistencia.vo.CatalogoOpcionVO"%>

<%@ page import="mx.gob.stps.portal.core.infra.utils.Constantes"%>	
<%@ page import="java.util.List"%>
<%@ page import="java.util.Calendar"%>

<style type="text/css">
	div.cargaCurp {background-image: url(images/ajax-loader.gif);}
</style>

<% 		
	String context = request.getContextPath() +"/";
	String inicioPage =  context + "registroCandidatos.do?method=retornaInicio";
%>

	<link href="${PATH_CSS_SWB_APP}estilos_mi_espacio.css" rel="stylesheet" type="text/css" />	

	<script type="text/javascript">
		 
	  var underlay;
	  
	  dojo.require("dijit.form.Form");
	  dojo.require("dijit.form.ValidationTextBox");
	  dojo.require("dijit.form.SimpleTextarea");
	  dojo.require("dijit.form.NumberTextBox");
	  dojo.require("dijit.form.Button");
	  dojo.require("dojox.image.Lightbox");
	  dojo.require("dojox.widget.FisheyeLite");
	  dojo.require("dojo.parser");
      dojo.require("dijit.form.Button");
      dojo.require("dijit.Dialog");
      dojo.require("dijit.form.TextBox");	  
	  dojo.require("dijit.dijit");
      dojo.require("dijit._Calendar");
      dojo.require("dijit.form.DateTextBox");
      dojo.require('dijit.Dialog'); 
      dojo.require("dijit.form.ComboBox");
	  dojo.require("dojo.data.ItemFileReadStore");  
	  dojo.require("dijit.form.FilteringSelect");
	 
	   function doSubmit(method){	
	    dojo.byId('botonEnviarAhora').disabled = true;
		document.cadidatosRegistroForm.disabled = true;
		document.cadidatosRegistroForm.method.value=method;
		desbloqueo();
		registrarCandidato();
		bloqueo();
	   }	
				
		function redireccionarLogin(){
			window.location='<%=inicioPage%>';
		}	
				
		function validaForm(){
			validaDatos();
				var bandera = false;
				if(validaCorreo() && 
					validaCombos())	{
					bandera =  true;							
			}	
		return bandera;		
		}
		
		function validaDatos(){
			if(false){			
				mensaje('Faltan datos requeridos, favor de verificar.');
				}	
		}
				
		function validaCorreo(){	
			var  bandera = false; 	
			var  correoA = document.cadidatosRegistroForm.correoElectronico.value;
			var  correoB = document.cadidatosRegistroForm.correoElectronicoConfirma.value;
			
			if(validarCorreoValues(
					document.cadidatosRegistroForm.correoElectronico,
						document.cadidatosRegistroForm.correoElectronicoConfirma)&&         
							correoA == correoB){
				bandera = true;
			} else {
				mensaje("Favor de confirmar la cuenta de correo electrónico.");
				document.cadidatosRegistroForm.correoElectronico.focus();
			}			
		return bandera;		
		}
		
		function validarCorreoValues(correo,correoConfirmar){
		return(validarFormatoCorreoValue(correo) && 
				validarFormatoCorreoValue(correoConfirmar));
		}
		
		function validaCombos(){	
			if (dijit.byId('idEstadoCivilStoreValue').get('item')== null){
				mensaje("Es necesario seleccionar el estado civil.");
				return false;
			}
			if (dijit.byId('discapacidadStoreValue').get('item')== null){
				mensaje("Es necesario seleccionar el tipo de discapacidad.");
				return false;
			}
			if (dijit.byId('idEntidadFederativaSelect').get('item')== null){			
				mensaje("Favor de proporcionar la entidad federativa .");
				return false;
			}
			if (dijit.byId('idMunicipioSelect').get('item')== null){			
				mensaje("Favor de proporcionar el municipio .");
				return false;
			}
			if (dijit.byId('portalEnterasteStoreValue').get('item')== null){			
				mensaje("Es necesario seleccionar cómo te enteraste de este portal.");
				return false;
			}
			
			
		dojo.byId('idEstadoCivil').value = dijit.byId('idEstadoCivilStoreValue').get('item').label[0];
		dojo.byId('portalEnteraste').value = dijit.byId('portalEnterasteStoreValue').get('item').label[0];
		dojo.byId('discapacidad').value = dijit.byId('discapacidadStoreValue').get('item').label[0];
		dojo.byId('entidadFederativa').value = dijit.byId('idEntidadFederativaSelect').get('value');
		dojo.byId('municipio_delegacion').value = dijit.byId('idMunicipioSelect').get('value');
		
			return true;
		}
		
		function mensaje(mensaje){
			dojo.byId('mensaje').innerHTML = mensaje;
			dijit.byId('MensajeAlert').show();		
		}
		
		function showDialog() {	
			if(validaForm() &&  validaConfirmacion())
        		dijit.byId('dialog').show();       
      	}  
      	function closeDialog(){
	        dijit.byId('dialog').hide();
	        setTimeout("document.cadidatosRegistroForm.correoElectronico.focus();",1000); 	
	           
      	} 	
      	function desbloqueo(){
      	 dojo.byId('idNombre').disabled = false;
      	 dojo.byId('idApellido1').disabled = false; 
      	 dojo.byId('idApellido2').disabled = false; 
      	 dojo.byId('idCurp').disabled = false;      	 
      	 dojo.byId('idEntidadNacimientoStoreValue').disabled = false;
      	 dojo.byId('idFechaNacimientoHidden').disabled = false;      	 
      	}
      	
      	function bloqueo(){
      	
      	 dojo.byId('idNombre').disabled = true;
      	 dojo.byId('idApellido1').disabled = true; 
      	 dojo.byId('idApellido2').disabled = true; 
      	 dojo.byId('idCurp').disabled = true;      	 
      	 dojo.byId('idEntidadNacimientoStoreValue').disabled = true;   
      	 dojo.byId('idFechaNacimientoHidden').disabled = true;      	 
      	 
      	 document.cadidatosRegistroForm.botonCurp.disabled="disabled";	
      	 document.cadidatosRegistroForm.botonDatosPersonales.disabled="disabled";
      	 
      	 dojo.byId('idDatosComplementos').style.display='block';
       	 dojo.byId('datosPersonales').style.display='block';		 
		 dojo.byId('datosCurp').style.display='block';
		 dojo.byId('conozco').style.display='none';   
	     
		     if(document.cadidatosRegistroForm.idGeneroMasculino.checked)
		     	document.cadidatosRegistroForm.idGeneroFemenino.disabled = "disabled";
		     else 
		     	document.cadidatosRegistroForm.idGeneroMasculino.disabled = "disabled";       	
		     
      	}
      	
      	function verificarCurp(method){             	 
	      	if(validaPersonales()){	      	
	      	 	document.cadidatosRegistroForm.method.value=method;
		      		bloquedoPantalla(); 
				    consultaDatosPersonales(); 	
			  } 
      	 }
       function	consultaDatosPersonales(){
      	  			dojo.xhrPost(
					 {
					  url: 'registroCandidatos.do',
					  form:'cadidatosRegistroForm',
					  timeout:180000, // Tiempo de espera 3 min
					  load: function(data){
						    var res = dojo.fromJson(data); // Datos del servidor
						    desbloqueoPantalla();	
						    	if('EXITOSO' == res.statusOper){					    
									dojo.byId('idCurp').value = "";
							     	dojo.byId('idCurp').value = res.curp;
							     	dojo.byId('idFechaNacimientoHidden').value = dojo.byId('idFechaNacimiento').value;
							     	dojo.byId('idFechaNacimientoHidden').style.display = "block";
							     	dojo.byId('idEntidadNacimientoText').style.display = "block";		
							     	dojo.byId('widget_idFechaNacimiento').style.display = "none";
							     	dojo.byId('widget_idEntidadNacimientoStoreValue').style.display = "none";							     				    							     				    
							    		bloqueo();
						    	} else if('NO EXITOSO' == res.statusOper && 'Error' != res.message ) {
						    		mensaje("Favor de revisar los datos proporcionados.");
						    		document.getElementById("botonDatosPersonales").readOnly = false;
						    		document.getElementById("conozcoCurp").readOnly = false;
						    		desbloquearParaContultaCurp();   						    		
						    	} else if('ERROR_CAPTCHA' == res.statusOper) {
						    		mensaje(res.message);
						    		document.getElementById("botonDatosPersonales").readOnly = false;
						    		document.getElementById("conozcoCurp").readOnly = false;
						    		desbloquearParaContultaCurp();   						    		
						    	} else {
						    		mensaje("La transacción no pudo ser completada, favor de intentarlo nuevamente después de unos instantes.");
						    		document.getElementById("botonDatosPersonales").readOnly = false;
						    		document.getElementById("conozcoCurp").readOnly = false;
						    		desbloquearParaContultaCurp();   
						    	}	
						 	}
						 } 
					 );	
      			}
      	
      	function bloqueParaContultaCurp(){
	      	document.getElementById("idNombre").readOnly = true;  
	      	document.getElementById("idApellido1").readOnly = true;  
	      	document.getElementById("idApellido2").readOnly = true;  
	      	document.getElementById("idGeneroMasculino").readOnly = true;  
	      	document.getElementById("idGeneroFemenino").readOnly = true;  
	      	document.getElementById("idFechaNacimiento").readOnly = true;
	      	document.getElementById("idEntidadNacimientoStoreValue").readOnly = true;
      	}
      	
      	
      	function desbloquearParaContultaCurp(){
	      	document.getElementById("idNombre").readOnly = false;  
	      	document.getElementById("idApellido1").readOnly = false;  
	      	document.getElementById("idApellido2").readOnly = false;  
	      	document.getElementById("idGeneroMasculino").readOnly = false;  
	      	document.getElementById("idGeneroFemenino").readOnly = false;  
	      	document.getElementById("idFechaNacimiento").readOnly = false;
	      	document.getElementById("idEntidadNacimientoStoreValue").readOnly = false;
      	}
      	
     function verificarDatosPersonales(method){     

	      	if(validaCurp()){
	      		document.cadidatosRegistroForm.method.value=method;      	 
      	   		bloquedoPantalla();	
      	   		consultandoCurp(); 				 	 
			 }
      	}
     function consultandoCurp(){
     	     dojo.xhrPost(
					 {
					  url: 'registroCandidatos.do',
					  form:'cadidatosRegistroForm',
					  timeout:180000, // Tiempo de espera 3 min
					  load: function(data){
					  	desbloqueoPantalla();						    
						    var res = dojo.fromJson(data); // Datos del servidor						     
						     
						    if('EXITOSO' == res.statusOper){
							     	dojo.byId('idNombre').value = res.nombre;
							     	dojo.byId('idApellido1').value = res.apellido1;
							     	dojo.byId('idApellido2').value = res.apellido2;							     								     	
							     	dojo.byId('idEntidadNacimientoHidden').value = res.idEntidadNacimiento;
							     	dojo.byId('idFechaNacimientoHidden').value = res.fechaNacimientoString;
							     	dojo.byId('idFechaNacimiento').value = res.fechaNacimientoString;
							     	dojo.byId('idFechaNacimientoHidden').style.display = "block";
							     	dojo.byId('widget_idFechaNacimiento').style.display = "none";
							     	dojo.byId('widget_idEntidadNacimientoStoreValue').style.display = "none";
							        dojo.byId('idEntidadNacimientoText').style.display = "block";
							        dojo.byId('idEntidadNacimientoText').value = res.estadoEntidadString;     
							     	
							     	if(1 == res.genero){
								     	document.cadidatosRegistroForm.idGeneroMasculino.checked = true;						     	
								     	document.cadidatosRegistroForm.idGeneroFemenino.disabled="disabled";					     	
							     	} else {
								     	document.cadidatosRegistroForm.idGeneroFemenino.checked  = true;
								     	document.cadidatosRegistroForm.idGeneroMasculino.disabled="disabled";
							     	}							    
							     bloqueo();							     	
						     } else if('NO EXITOSO' == res.statusOper && 'Error' != res.message ) {
						   	 	mensaje("La curp no existe, favor de verificarla.");
						   	 	document.getElementById("botonCurp").readOnly = false;
						   	 	document.getElementById("conozcoCurp").readOnly = false;
						   	 	document.getElementById("idCurp").readOnly = false;  
						   	 } else if('ERROR_CAPTCHA' == res.statusOper) {
							   	 	mensaje(res.message);
							   	 	document.getElementById("botonCurp").readOnly = false;
							   	 	document.getElementById("conozcoCurp").readOnly = false;
							   	 	document.getElementById("idCurp").readOnly = false;  
							 } else {
						   	 	mensaje("La transacción no pudo ser completada, favor de intentarlo nuevamente después de unos instantes.");
						   	 	document.getElementById("botonCurp").readOnly = false; 
						   	 	document.getElementById("conozcoCurp").readOnly = false;
						   	 	document.getElementById("idCurp").readOnly = false; 
						   	 }						   	 
						   	}
						 } 
					 );	
     			}
      	
      function validaCurp(){

			if(!dijit.byId('code').isValid()){
				mensaje("Favor de proporcionar el texto de la imagen.");								
				return false;
			}
    	  
			if(!dijit.byId('idCurp').isValid()){
				mensaje("Verifique el formato correcto de la curp.");								
				return false;
			}

    	  return true;
	  }
		
      function validaPersonales(){       

			if(!dijit.byId('code').isValid()){
				mensaje("Favor de proporcionar el texto de la imagen.");								
				return false;
			}

			if(!dijit.byId('idNombre').isValid()){
				mensaje("Favor de proporcionar el nombre (s) de forma correcta.");								
			return false;
			}			
			if(!dijit.byId('idApellido1').isValid()){
				mensaje("Favor de proporcionar el primer apellido de forma correcta.");
			return false;
			}
			if(!dijit.byId('idApellido2').isValid()){
				mensaje("Favor de proporcionar el segundo apellido de forma correcta.");
			return false;
			}
			
			if(!dijit.byId('idFechaNacimiento').isValid()){
				mensaje("Favor de proporcionar la fecha de nacimiento de forma correcta.");
			return false;
			}		
			
			if (dijit.byId('idEntidadNacimientoStoreValue').get('item')== null){
				mensaje("Es necesario seleccionar la entidad de nacimiento.");
			return false;
			}				
					
			dojo.byId('idEntidadNacimientoText').value = dijit.byId('idEntidadNacimientoStoreValue').get('item').name[0];
			dojo.byId('idEntidadNacimientoHidden').value = dijit.byId('idEntidadNacimientoStoreValue').get('item').label[0];
			
			return true;		
		}


		function validaConfirmacion(){			
			if(!document.cadidatosRegistroForm.veracidadDatos.checked){
			mensaje("La casilla de confirmo que he revisado mis datos y son correctos, es obligatoria.");
			return false;
			}
		return true;
		}
			 
	 	function registrarCandidato(){	      
	     dojo.xhrPost(
				 {
				  url: 'registroCandidatos.do',
				  form:'cadidatosRegistroForm',
				  timeout:180000, // Tiempo de espera 3 min
				  load: function(data){				 
				 	var res = dojo.fromJson(data); // Datos del servidor				
						if (res.type == 'ext'){// EXITO			    
							mensaje(res.message);
						  setTimeout("redireccionarLogin()",10000); 
						 }else if (res.type == 'errCorreo'){	
						 	mensaje(res.message);
						    dojo.byId('botonEnviarAhora').disabled = false;
						 }else{
							mensaje(res.message);
						  setTimeout("redireccionarLogin()",10000);
						}						
				   	 }
				 } 
			 );
	 	}
    
	function verifyStop(id){
		var widget = dijit.byId(id);
		if(!widget.isValid()){
			widget.focus();
		}	
	}
	
	function getFechaEntrevista(){
	   	fechaActual = new Date();
        dijit.byId('idFechaNacimiento').constraints.max = fechaActual;
	 }
	 
	 function conozcoMiCurp(){	
	 	if(document.cadidatosRegistroForm.conozcoCurp.checked){
		 	dojo.byId('datosPersonales').style.display='block';
		 	dojo.byId('datosCurp').style.display='none'; 	 	
	 	} else {
		 	dojo.byId('datosPersonales').style.display='none';
		 	dojo.byId('datosCurp').style.display='block';
	 	}
	 	
	 	changeSecureCodeImage();
	 }
    
      dojo.addOnLoad(function() {      
     	document.getElementById("conozcoCurp").disabled = false;  
    	document.getElementById("idCurp").disabled = false;  
        document.cadidatosRegistroForm.conozcoCurp.checked = false;
		document.oncontextmenu  = function(){return false;};
		document.onselectstart  = function(){return false;};
			getFechaEntrevista();	
		
		var inputcode = document.getElementById("code"); 
		inputcode.focus(); 
		
		
		
		
       }); 
       
       
    function bloquedoPantalla() {    
		underlay = 
	    new dijit.Dialog({
	        title: 	 'Mensaje',
	        content: "Su consulta puede tardar algunos segundos.",
	        style: 	 "width: auto",	        	        
	        draggable : false, closable : false,disableCloseButton: true
	    });
	    underlay.closeButtonNode.style.display = 'none'; 
		underlay.show();
	
	}
	
	function desbloqueoPantalla() {
		underlay.hide();
	}
	
	function validarFormatoCorreo(correo) {	
	var regExp = /^[a-zA-Z0-9_-]+(?:\.[a-zA-Z0-9_-]+)*@(?:[a-zA-Z0-9](?:[a-zA-Z0-9-_]*[a-zA-Z0-9-])?\.)+[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?$/;
	if (!regExp.test(correo.value)) {
		mensaje('Formato de correo electrónico inválido.');		
		}	
	}

	function validarFormatoCorreoValue(correo) {
	var regExp = /^[a-zA-Z0-9_-]+(?:\.[a-zA-Z0-9_-]+)*@(?:[a-zA-Z0-9](?:[a-zA-Z0-9-_]*[a-zA-Z0-9-])?\.)+[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?$/;
	if (!regExp.test(correo.value)) {
		mensaje('Formato de correo electrónico inválido.');
		return false;		
	}
		return true;
	}
	
	function noArrastrar(correoElectronico){
		var aux = correoElectronico.value;
		correoElectronico.value = '';
		correoElectronico.value = aux;
	}

    function changeSecureCodeImage(){
		var img = document.getElementById('imgseccode');

		if(img){
			img.src = 'captcha?time='+ new Date();
			dijit.byId('code').set('value', '');
		}
    }
    
    function limpiarExpacios(id){
    	var cadena = dojo.byId(id).value;
    	dojo.byId(id).value =cadena.replace("  "," ");
    }
    
  //Desplegar ventana para diferencia_conocimientos_habilidades.html
    function openIndicacionesUsuario() {
      var newWin = window.open('indicaciones_usuario.html', "Indicaciones para el usuario","height=500,width=900,resizable=yes,scrollbars=yes");
    }
  
  
    function recargaComboEntidad(){

    	var vEntidad = dijit.byId('idEntidadFederativaSelect').get('value');		
         	dijit.byId('idMunicipioSelect').disabled=false;         		
         	municipioStore.url = "${context}registroCandidatos.do?method=obtenerMunicipioRegistro" + "&" + "entidadFederativa="+ vEntidad;
        	municipioStore.close();
           
    }
  
</script>
	
<form id="cadidatosRegistroForm" name="cadidatosRegistroForm"  
	method="post" action="/registroCandidatos.do" dojoType="dijit.form.Form">

<input type="hidden" name="method" value="init"/>


<div>  

	<h3>Datos personales</h3>

	
	<div class="formulario">
		 <div class="entero">
			   <a href="javascript:openIndicacionesUsuario();" >Indicaciones para usuario</a>
		</div>		    
	
 		<div id = "conozco" > 
		 <p class="entero">&nbsp;<br/>
			 <strong>
                   	No conozco mi CURP.&nbsp;&nbsp;&nbsp;
                   	<input type="checkbox" name="conozcoCurp" id="conozcoCurp"  onclick="conozcoMiCurp()">		                     	
                   </strong>
		 </p>  
		</div>
		
		<div class="entero">
			<img src="<%="captcha?time="+ Calendar.getInstance().getTimeInMillis()%>" id="imgseccode" width="155" height="65"/>
			<br/>
        	<a href="javascript:changeSecureCodeImage();">Cambiar imagen</a>
        	<p>
        		<label for="cmnt_seccode">El texto de la imagen es*: &nbsp;&nbsp;</label>
        		<input dojoType="dijit.form.ValidationTextBox" 
        		       required="true" missingMessage="Dato requerido" style="width:18%"
        		       maxlength="6" size="15" id="code" name="code" />

        	</p>
		</div>
		
			 <div id = "datosCurp" >    
			     <div id = "entero" >			         
                         <span class="un_medio">&nbsp;<br />
                            <strong>
                            	<br/>
                            	<br/>
                             <label for="idCurp">CURP*:</label>
                            </strong>                    
                             <input 
	                             id="idCurp" 
	                             name="curp" 
	                             dojoType="dijit.form.ValidationTextBox" 
	                             required="true" missingMessage="Dato requerido" 
	                             regExp="[a-zA-Z]{4}\d{6}[hmHM][a-zA-Z]{2}[B-DF-HJ-NP-TV-Z|b-df-hj-np-tv-z]{3}[a-zA-Z0-9][0-9]" 
	                             invalidMessage="Dato Inválido" 
	                             maxlength="18"	                            
	                             uppercase="true"
	                             clearOnClose="true" 	                             
	                             style="
		                             font-family:Arial, Helvetica, sans-serif; 
		                             font-size:12px; color:#444444; 
		                             width:300px;" 
	                             value="" 
	                             trim="true"/>
                     </span>
                     
                      <span class="un_medio">
                       <br/>
                       <strong>
                             <label >&nbsp;</label>
                            </strong>     
                           <span>
                          		<input type="button" id="botonCurp" class="boton" 
                          		onclick="verificarDatosPersonales('verificarDatosPersonales');" value="Verificar CURP"
                          		style="">
                           </span>
                      </span>  
                   </div> 
                 </div> 
              <div class="entero"></div>                     
 			  <div id="datosPersonales" style="display:none">
                      <div id="dato_nombre">
                          <p class="un_tercio">
                            <label for="text1"><strong> <label for="idNombre">Nombre (s)*</label></strong></label>
                            <br />
                               <input id="idNombre" 
		                             name="nombre" 
		                             dojoType="dijit.form.ValidationTextBox" 
		                             required="true"
		                             regExp="^[A-Za-z\s\-.ñÑ/']{1,50}$"
		                             invalidMessage="Dato Inválido" 
		                             maxlength="50"		                             
		                             uppercase="true"
		                             style="
			                             font-family:Arial, Helvetica, sans-serif; 
			                             font-size:12px; color:#444444; 
			                             width:195px;
			                             height:20px;
			                             margin-top: 7px;	
			                             border: none;	                             
			                             " 
		                             value="" 
		                             trim="true" 
		                             onkeyup="limpiarExpacios('idNombre')"/>
                          <p class="un_tercio">
                            <label for="text2"><strong><label for="idApellido1">Primer apellido*</label> </strong></label>

                            <br />
                           <input id="idApellido1" 
		                             name="apellido1" 
		                             dojoType="dijit.form.ValidationTextBox" 
		                             required="true" 
		                             regExp="^[A-Za-z\s\-.ñÑ/']{1,50}$"
		                             invalidMessage="Dato Inválido" 
		                             maxlength="50"		                            
		                             uppercase="true" 
		                             style="
			                             font-family:Arial, Helvetica, sans-serif; 
			                             font-size:12px; color:#444444; 
			                             width:195px;
			                             height:20px;
			                             margin-top: 7px;	
			                             border: none;	                             
			                             " 
		                             value="" 
		                             trim="true"
		                             onkeyup="limpiarExpacios('idApellido1')"/>
                          </p>
                          <p class="un_tercio">
                            <label for="text3"><strong><label for="idApellido2">Segundo apellido</label> </strong></label>
                            <br />                           
                             <input  id="idApellido2" 
		                             name="apellido2" 
		                             dojoType="dijit.form.ValidationTextBox" 
		                             required="false" 
		                             regExp="^[A-Za-z\s\-.ñÑ/']{1,50}$"
		                             invalidMessage="Dato Inválido"		                          
		                             uppercase="true"  
		                             maxlength="50" 
		                             style="
			                             font-family:Arial, Helvetica, sans-serif; 
			                             font-size:12px; color:#444444; 
			                             width:195px;
			                             height:20px;
			                             margin-top: 7px;	
			                             border: none;	                             
			                             " 
		                             value="" 
		                             trim="true" 
		                             onkeyup="limpiarExpacios('idApellido2')"/>
		                      </p>
                            
                      </div>
	<div class="entero">
                      	<span  class="un_tercio"><strong>Género*</strong><br />
	                      Masculino<input type="radio" name="genero" value="1" checked =true id="idGeneroMasculino">
	                      	&nbsp;&nbsp;&nbsp;
	                      Femenino<input type="radio" name="genero" value="2" id="idGeneroFemenino">
                    </span >
                      
                     <span  class="un_tercio"><strong>Fecha de nacimiento (dd/mm/aaaa)*</strong><br />	
                        <input type="text"   id="idFechaNacimientoHidden" name="fechaNacimiento"  style="display: none" disabled="disabled">                        	            	
		          
		             	<input type="text" 
		             		   name="fechaNacimiento" 
		             		   id="idFechaNacimiento"		             		    
		             		   dojoType="dijit.form.DateTextBox" 
		             		   maxlength="10"		             		     
		             		   constraints={datePattern:'dd/MM/yyyy'} 
		             		   required="true" />
		             </span >
		           
                        <span class="un_tercio"><strong>Entidad de nacimiento*</strong><br />
                   
                         <div dojoType="dojo.data.ItemFileReadStore" 
	                         	  jsId="idEntidadNacimientoStore" 
	                         	  url="${context}registroCandidatos.do?method=entidadesFederativas">
	                         </div>
	                         
							 <select dojotype="dijit.form.ComboBox" 
									store="idEntidadNacimientoStore" 
									id="idEntidadNacimientoStoreValue"
									name="idEntidadNacimientoStoreValue" 
									required="true">
							 </select>
                         
                         <input type="text"       id="idEntidadNacimientoText"   disabled="disabled" style="display:none">
                         <input type="hidden"     id="idEntidadNacimientoHidden" name="idEntidadNacimiento">
                
                          	</span>
                      <div class="entero">
                      
                      <p class="un_tercio"></p>
                     <p class="un_tercio"><input type="button" id = "botonDatosPersonales" class="boton" onclick="verificarCurp('verificarCurp');" 
                     value="Validar datos en RENAPO" >
                     </p>
                     <p class="un_tercio"></p>
                     </div>
                     <div class="entero"></div>
  	</div>            
                               
 </div>
 			<div id = "idDatosComplementos" style="display:none">
 			<p class="entero">
                      El estado civil y el tipo de discapacidad sólo se almacenarán, nunca serán mostrados a las empresas.</p>
<div class="entero">
                       <span class="un_medio"><strong>Estado civil*</strong><br />
	                    
	                      <div dojoType="dojo.data.ItemFileReadStore" 
	                         	  jsId="idEstadoCivilStore" 
	                         	  url="${context}registroCandidatos.do?method=estadoCivil">
	                         </div>
	                         
							 <select dojotype="dijit.form.ComboBox" 
									store="idEstadoCivilStore" 
									id="idEstadoCivilStoreValue"
									name="idEstadoCivilStoreValue" 
									required="true">
							 </select> 
						
						<input type="hidden"   id="idEstadoCivil" name="idEstadoCivil" >
							 
                      </span>
                      
                       <span class="un_medio">
                      <strong>Tipo de discapacidad*</strong> <br />
                            
	                      	<div dojoType="dojo.data.ItemFileReadStore" 
	                         	  jsId="discapacidadStore" 
	                         	  url="${context}registroCandidatos.do?method=tipoDiscapacidad">
	                         </div>
	                         
							 <select dojotype="dijit.form.ComboBox" 
									store="discapacidadStore"
									id="discapacidadStoreValue"						    		
                    				labelAttr="name"                     
									required="true">									
							 </select>    
					
					<input type="hidden"   id="discapacidad" name="discapacidad" >
					  
                      </span>
</div>                      
                      
                      <div class="entero">
                       <span class="un_medio"><strong>Entidad federativa de residencia*</strong><br />
	                    
	                      <div dojoType="dojo.data.ItemFileReadStore" 
	                         	  jsId="idEntidadFederativaStore" 
	                         	  url="${context}registroCandidatos.do?method=entidadesFederativasPreRegistro">
	                         </div>
	                         <select dojotype="dijit.form.FilteringSelect" store="idEntidadFederativaStore" id="idEntidadFederativaSelect" 
							required="true"  invalidMessage="Dato inválido" tabindex="1"
					        missingMessage="Dato requerido" autocomplete="true" onchange="recargaComboEntidad();">
						</select>
							
						
						<input type="hidden"   id="entidadFederativa" name="entidadFederativa" >
							 
                      </span>
                      
                       <span class="un_medio">
                      <strong>Municipio*</strong> <br />
                            
	                      	<div dojoType="dojo.data.ItemFileReadStore" 
	                         	  jsId="municipioStore" 
	                         	  urlPreventCache="true" clearOnClose="true" >
	                         </div>
	                         
	                         <select dojotype="dijit.form.FilteringSelect" store="municipioStore" id="idMunicipioSelect" 
					        required="true"  invalidMessage="Dato inválido" disabled="disabled"
					        missingMessage="Dato requerido" autocomplete="true">
						</select>
	                           
					
					<input type="hidden"   id="municipio_delegacion" name="municipio_delegacion" >
					  
                      </span>
</div>  
                      <div class="entero">
                        <strong>¿Cómo te enteraste de este portal?*</strong><br />
	                     	
	                     	 <div dojoType="dojo.data.ItemFileReadStore" 
	                         	  jsId="portalEnterasteStore"	                         	
	                         	  url="${context}registroCandidatos.do?method=medioPortal">
	                         </div>
	                         
							 <select dojotype="dijit.form.ComboBox" 
									store="portalEnterasteStore" 
									id="portalEnterasteStoreValue"
									name="portalEnterasteStoreValue" 
									required="true">
							 </select>
							 							 					
						<input type="hidden"   id="portalEnteraste" name="portalEnteraste" >

                        </div>
                     <p class="entero">
	                     <strong>
	                     	Deseo que mis datos personales permanezcan confidenciales.&nbsp;&nbsp;&nbsp;
	                     	<input type="checkbox" name="confidencialidadDatos" value="true"> 
	                     	<input type="hidden"   name="confidencialidadDatos" value="false">
	                     </strong>
	                     <br />
					  </p>
					  
					  <p class="entero">
	                     <strong>Confirmo que he revisado mis datos y son correctos.&nbsp;*&nbsp;&nbsp;
	                     	<input type="checkbox" name="veracidadDatos" id="veracidadDatos" value="true">	                     	
	                     </strong>
	                     <br />
					  </p>
					  
               
               <div>
               	 <h3>Datos de identificaci&oacute;n de la cuenta</h3>   
                 	<div class="un_medio">
                        <p class="entero">
                            <strong>Correo electrónico:*</strong><br />                             
                         
                        <input type="text" name="correoElectronico" id="correoElectronico" size="45" maxlength="65" 
                    	onchange="validarFormatoCorreo(this);" oncopy="return false;" trim="true"
                    	onselect="noArrastrar(this);"
                    	oncut="return false" onpaste="return false;" />
                    	    
                         </p> 
                    </div>  
                                                   
                     <div class="un_medio">
                        <p class="entero">
                            <strong>Confirmar correo electrónico:*</strong><br />
                           
                              <input type="text" name="correoElectronicoConfirma" id="correoElectronicoConfirma" size="45" maxlength="65", 
                    	     onchange="validarFormatoCorreo(this);" oncopy="return false;" trim="true"
                    	     onselect="noArrastrar(this);" 
                    	     oncut="return false" onpaste="return false;" />
                                		 		
                                		
                         </p> 
                    </div>  
               </div>
         	
         	<div>
               	 <h3>Términos y condiciones</h3>
               	 <div class="un_medio">     
	                    <p class="entero">	                    
	                     	<strong>He leído y acepto los términos y condiciones&nbsp;&nbsp;&nbsp;                         
		                         <input type="checkbox" name="aceptacionTerminos" id="aceptacionTerminos" value="true">		                                             
	                         </strong><br />
		                     <br />
						</p>
					</div>   
               	 	<div class="un_medio">              
	                     <p class="entero">
		                     <a href="<%=Constantes.TERMINOS_CONDICIONES%>" target="_new" class="links">Terminos y condiciones&nbsp;
		                          <img src="images/flecha_cont_externo.gif" title="Contenido externo" />
		                      </a>
		                    <br />
						 </p>  
                    </div>
           </div>
            <div style="text-align: center;" class="entero">           		
           			<input type="button" value="Crear cuenta" class="boton" onclick="showDialog();">           		
           			<input type="button" value="Cancelar" class="boton" onclick="redireccionarLogin();">
           	</div>
             </div></div>
             
    <div dojoType="dijit.Dialog" id="dialog" title="Confirmación" style="display:none" draggable ="false" >
		 <table class="alertDialog" >
			 <tr>
				 <td><input type="button" class="boton" value="Enviar ahora" id ="botonEnviarAhora"  name ="botonEnviarAhora" onclick="javascript:doSubmit('registrar');">&nbsp;</td>
				 <td><input type="button" class="boton" value="Corregir correo"  onclick="closeDialog();" >&nbsp;</td>
				 <td><input type="button" class="boton" value="Cancelar"  onclick="redireccionarLogin();">&nbsp;</td>
			</tr>
		 </table>	 
	</div>
	
	<div dojoType="dijit.Dialog" id="MensajeAlert" title="Mensaje" style="display:none" draggable ="false" >
		  <table class="alertDialog" >
			 <tr align="center">				 	
				 <td><div id ="mensaje" style="width:400px;height:400px;overflow:auto;vertical-align:middle;"></div>&nbsp;</td>			 
			 </tr>
		 </table>	
	</div>
</div>             
</form>
<br/>
             