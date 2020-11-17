<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF8"%>
<%@ page import="java.util.Calendar, mx.gob.stps.portal.web.infra.utils.PropertiesLoader"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%
	PropertiesLoader properties = PropertiesLoader.getInstance();
	String redirect = properties.getProperty("app.swb.redirect.home");
%>

<script type="text/javascript">
	dojo.require("dijit.Dialog");
	dojo.require("dijit.form.FilteringSelect");
	dojo.require("dijit.form.Textarea");
	dojo.require("dijit.form.CheckBox");
	dojo.require("dijit.form.ValidationTextBox");
	
	function validateData() {
		if (dijit.byId('idCategory').get('item')== null || dijit.byId('idCategory').get('item').value == 0){
			msgalert("Es necesario seleccionar categoria.");
			return false;
		}
		if(!dijit.byId('matter').isValid()){
			msgalert("Favor de proporcionar el texto del asunto.");								
			return false;
		}
		if(dijit.byId('message').value == ''){
			msgalert("Favor de proporcionar el texto del mensaje.");								
			return false;
		}
		if(!dijit.byId('email').isValid()){
			msgalert("Favor de proporcionar el correo electrónico.");								
			return false;
		}
		if(!dijit.byId('name').isValid()){
			msgalert("Favor de proporcionar el nombre.");								
			return false;
		}
		if(!dijit.byId('lastname').isValid()){
			msgalert("Favor de proporcionar el primer apellido.");								
			return false;
		}
		if(!dijit.byId('middlename').isValid()){
			msgalert("Favor de proporcionar el segundo apellido.");								
			return false;
		}
		if(!dijit.byId('lada').isValid()){
			msgalert("Favor de proporcionar clave lada.");								
			return false;
		}
		
		if (!dijit.byId('respuestaUsuario').isValid()){
			msgalert("Es necesario proporcionar la respuesta a la pregunta");
			return false;
		}
		
		if(!dijit.byId('phone').isValid()){
			msgalert("Favor de proporcionar algún telefono.");								
			return false;
		}else {
			return phoneSize();
		}
// 		if(!dijit.byId('code').isValid()){
// 			msgalert("Favor de proporcionar el texto de la imagen.");								
// 			return false;
// 		}
		
		

		return true;
	}
	function validatemail(correo) {	
		var regExp = /^[a-zA-Z0-9_-]+(?:\.[a-zA-Z0-9_-]+)*@(?:[a-zA-Z0-9](?:[a-zA-Z0-9-_]*[a-zA-Z0-9-])?\.)+[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?$/;
		if (!regExp.test(correo.value)) {
			msgalert('Formato de correo electrónico no válido.');		
		}	
	}
	function msgalert(msg){
		dojo.byId('msg').innerHTML = msg;
		dijit.byId('msgAlert').show();		
	}
	function doSubmitAjax() {
		if (validateData()) {
			var answer = confirm("¿Está seguro de enviar los datos?");
			if (answer) {
				document.suggestionForm.submit();
				alert('Los datos han sido enviados con éxito');
				//window.close();
			}
		}
	}
	function cancelConfirmation(method) {
		var answer = confirm("Los datos no guardados se perderán ¿Continuar?");
		if (answer) {
			dojo.byId('method').value=method;
			document.suggestionForm.submit();
		}
	}
	function changeSecureCodeImage(){
		var img = document.getElementById('imgseccode')
		if(img){
			img.src = 'captcha?time=' + new Date();
			dijit.byId('code').set('value', '');
		}
    }
    function isValidCaptcha(method){
    	dojo.byId('method').value=method;
    	//dojo.byId('code').value=dijit.byId('code').value;
//     	dojo.xhrPost(
// 			{
// 				url: 'suggestion.do',
// 				form:'suggestionForm',
// 				timeout:180000, // Tiempo de espera 3 min
// 				load: function(data){
// 					var res = dojo.fromJson(data);
// 					if	('ERROR_CAPTCHA' == res.message) {
// 						msgalert('Favor de proporcionar el texto de la imagen.');
// 					}else {
						doSubmitAjax();
// 					}
// 				}
// 			}
// 		);
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
  	function fillUpAccessKey(){
  		var tipoTelefonoId = getRadioValue('phoneType');
  		var accesoDes;
      	if(tipoTelefonoId == 1)
      		accesoDes = '044';
      	else
      		accesoDes = '01';   		
      	var wAcceso = dijit.byId('access');
      	wAcceso.attr('value',accesoDes);
  	}
  	function phoneSize(){
  		var vClave = document.getElementById('lada');
  		var vTelefono = document.getElementById('phone'); 
  		if(vClave.value.length < 2){
  			msgalert('Debe proporcionar clave LADA de 2 &oacute; 3 caracteres');
  			return false;
  		}else if(vClave.value.length == 2) {
  			  	if(vTelefono.value.length != 8) {
  					msgalert('Debe proporcionar un tel&eacute;fono de 8 dígitos');
  					return false;
  				}
  		}else if(vClave.value.length==3) {
  				if(vTelefono.value.length!=7){
  					msgalert('Debe proporcionar un tel&eacute;fono de 7 dígitos');
  					return false;
  				}	
  		}
  		return true;
  	}
  	
  	function cambiarPregunta(){
		dojo.xhrPost({url: 'suggestion.do?method=cambiaPregunta',
			  load: function(data) {
				  document.getElementById('captchaPregunta').innerHTML = data;
			  }
				  		
			});
	}
</script>
<noscript>Funciones de JavaScript desactivadas por navegador</noscript>

<form id="suggestionForm" name="suggestionForm" action="${context}suggestion.do?method=saveSuggestion" method="post" autocomplete="off">
	<input type="hidden" name="method" id="method" value="saveSuggestion"/>
	<div class="entero"></div>
	
	
	<div class="panel panel-contacto">
	    <div class="panel-body">
			<div class="row">
				
				<div class="col-sm-6">
		       		<div class="form-group">
		       			<IMG SRC="images/logo_portal_del_empleo.gif" alt="Portal del empleo, llama al 01800-8412020" class="img-responsive"/>
		       		</div>
		       	</div>
		       	
		       	<div class="clearfix"></div>
		       	
				<div class="col-sm-6">
		       		<div class="form-group">
		       			<label for="idCategory">Categoría:</label>
						<select id="idCategory" name="idCategory" dojoType="dijit.form.FilteringSelect" class="form-control-dojo">
							<option value="0">SELECCIONE UNA OPCIÓN</option>
							<option value="1">QUEJA</option>
							<option value="2">DUDA</option>
							<option value="3">SUGERENCIA</option>
							<option value="4">COMENTARIO</option>
						</select>
		       		</div>
		       	</div>
		       	
		       	<div class="col-sm-6">
		       		<div class="form-group">
		       			<label for="matter">Asunto:</label>
						<input id="matter" name="matter" maxlength="40" size="44" dojoType="dijit.form.ValidationTextBox" required="true" regExp="^[A-Za-zÃ¡Ã©Ã­Ã³ÃºAÃÃÃÃ\s\-.Ã±Ã/']{1,40}$" value=""
							   invalidMessage="Dato no v&aacute;lido" trim="true" missingMessage="Asunto es requerido." class="form-control-dojo"/>               			           			    					
		       		</div>
		       	</div>
		       	
		       	<div class="col-sm-12">
		       		<div class="form-group">
						<label>Mensaje:</label>
						<textarea id="message" name="message" dojoType="dijit.form.Textarea" required="true" invalidMessage="Dato no v&aacute;lido" trim="true" missingMessage="El mensaje es requerido." class="form-control-dojo" rows="3" style="height: 80px;"></textarea>               			           			    					               			           			    					
		       		</div>
		       	</div>
		       	
		       	<div class="col-sm-12">
		       		<div class="form-group">
		       			<label for="email">Correo electr&oacute;nico:</label>
			            <input type="text" name="email" id="email" size="44" maxlength="65" dojoType="dijit.form.ValidationTextBox" required="true" onchange="validatemail(this);" class="form-control-dojo"/>
		       		</div>
		       	</div>
		       	
		       	<div class="col-sm-12">
				    <div class="un_tercio" dojoType="dijit.Dialog" id="msgAlert" title="msg" style="display:none" draggable ="false" >
						  <table class="alertDialog" >
							 <tr align="center">				 	
								 <td><div id ="msg" style="width:250px;height:50px;overflow:auto;vertical-align:middle;"></div>&nbsp;</td>			 
							 </tr>
						 </table>	
					</div>
		       	</div>
		       	
		       	<div class="col-sm-4">
		       		<div class="form-group">
		       			<label for="name">Nombre:</label>
						<input id="name" name="name" maxlength="50" size="44" dojoType="dijit.form.ValidationTextBox" required="true" regExp="^[A-Za-zÃ¡Ã©Ã­Ã³ÃºAÃÃÃÃ\s\-.Ã±Ã/']{1,50}$" value=""
							   invalidMessage="Dato no v&aacute;lido" trim="true" missingMessage="Nombre es requerido." class="form-control-dojo"/>
		       		</div>
		       	</div>
		       	
		       	<div class="col-sm-4">
		       		<div class="form-group">
		       			<label for="lastname">Primer Apellido:</label>
						<input id="lastname" name="lastname" maxlength="50" size="44" dojoType="dijit.form.ValidationTextBox" required="true" regExp="^[A-Za-zÃ¡Ã©Ã­Ã³ÃºAÃÃÃÃ\s\-.Ã±Ã/']{1,50}$" value=""
							   invalidMessage="Dato no v&aacute;lido" trim="true" missingMessage="Primer Apellido es requerido." class="form-control-dojo"/>
		       		</div>
		       	</div>
		       	
		       	<div class="col-sm-4">
		       		<div class="form-group">
		       			<label for="middlename">Segundo Apellido:</label>
						<input id="middlename" name="middlename" maxlength="50" size="44" dojoType="dijit.form.ValidationTextBox" required="false" regExp="^[A-Za-zÃ¡Ã©Ã­Ã³ÃºAÃÃÃÃ\s\-.Ã±Ã/']{1,50}$" value=""
							   invalidMessage="Dato no v&aacute;lido" missingMessage="S&oacute;lo se permiten letras" trim="true" class="form-control-dojo"/>
		       		</div>
		       	</div>
		       	
		       	<div class="col-sm-4">
		       		<div class="form-group">
		       			<label>Tipo de tel&eacute;fono:</label>
		       			<div class="row">
				          	<div class="col-sm-12">
					            <label class="radio-inline"> 
					            	<input type="radio" name="phoneType" id="phoneType" value="1" onClick="fillUpAccessKey();" />Celular
					            </label>
					            <label class="radio-inline">
					            	<input type="radio" name="phoneType" id="phoneType" value="5" onClick="fillUpAccessKey();" checked/>Fijo
					            </label>
				          	</div>
				          </div>
		       		</div>
		       	</div>
		       	
		       	<div class="clearfix"></div>
		       	
	       		<div class="col-sm-3">
		       		<div class="form-group">
		       			<label for="access">Acceso:</label>
			            <input type="text" name="access" id="access" value="01" maxlength="3" dojoType="dijit.form.ValidationTextBox"
			            		required="true" readonly="readonly" invalidMessage="Dato no v&aacute;lido" missingMessage="Dato requerido" trim="true" class="form-control-dojo"/>
		       		</div>
		       	</div>
		       	
		       	<div class="col-sm-3">
		       		<div class="form-group">
		       			<label for="lada">Clave Lada:</label>
			            <input type="text" name="lada" id="lada" maxlength="3"  value="" dojoType="dijit.form.ValidationTextBox"
			            	required="true" regExp="^[+]?\d{2,3}$" invalidMessage="Dato no v&aacute;lido" missingMessage="Dato requerido" trim="true" class="form-control-dojo"/>
		       		</div>
		       	</div>
		       	
		       	<div class="col-sm-3">
		       		<div class="form-group">
		       			<label for="phone"> Tel&eacute;fono:</label>
			            <input type="text" name="phone" id="phone" maxlength="8" value="" dojoType="dijit.form.ValidationTextBox" required="true" regExp="^[+]?\d{7,8}$"
			            invalidMessage="Dato no v&aacute;lido"	missingMessage="Dato requerido" trim="true" class="form-control-dojo"/>
		       		</div>
		       	</div>
		       	
		       	<div class="col-sm-3">
		       		<div class="form-group">
		       			<label for="ext">Extensi&oacute;n:</label>
			            <input type="text" name="ext" id="ext" maxlength="6" value="" dojoType="dijit.form.ValidationTextBox" class="form-control-dojo"
			                   required="false" regExp="^[+]?\d{0,6}$" invalidMessage="Dato no v&aacute;lido" missingMessage="Dato requerido" trim="true" />
		       		</div>
		       	</div>
		       	
		       	<div class="col-sm-12">
		       		<div class="form-group">
		       			<p><mark><b>Responde a la siguiente pregunta</b></mark></p>
		       		</div>
		       	</div>
		       	
		       	<div class="col-sm-6">
		       		<div class="form-group">
						<span class="c_naranja_respuesta">*</span><label id="captchaPregunta">${activacionForm.pregunta}</label>
					    <input type="text" name="respuestaUsuario" id="respuestaUsuario" size="40" maxlength="15"
					    	   dojoType="dijit.form.ValidationTextBox"
					    	   required="true" missingMessage="Es necesario proporcionar la respuesta a la pregunta"
					    	   class="form-control-dojo"/> 
		       		</div>
		       	</div>
		       	
		       	<div class="col-sm-6">
		       		<div class="form-group">
		       			<span class="hideLabel hidden-xs"></span>
						<a href="javascript:cambiarPregunta()" ><img src="images/icono-cambiar_pregunta.png" width="20" height="20" /></a>
		       		</div>
		       	</div>
		       	
		       	<div class="col-sm-12">
		       		<div class="form-group">
						<html:messages id="errors">
							<span class="redFont Font80"><bean:write name="errors"/></span><br/>
						</html:messages>
		       		</div>
		       	</div>
		       	
				<div class="col-sm-12" id="divRegis" >         			    				   
					<div class="row">
						<div class="col-xs-4">
		       				<div class="form-group">
								<input type="button" id="btnSave" value="Enviar" class="boton btn-block" onclick="javascript:isValidCaptcha('captcha');" />
		       				</div>
		       			</div>
		       			
		       			<div class="col-xs-4">
		       				<div class="form-group">
								<input type="reset" id="btnReset" value="Limpiar" class="boton btn-block" />
		       				</div>
		       			</div>
		       			
		       			<div class="col-xs-4">
		       				<div class="form-group">
								<input type="button" id="btnCancel" value="Cancelar" class="boton btn-block" onclick="window.close()" />
		       				</div>
		       			</div>                                
					</div>
				</div>
			
			</div>
		</div>
	</div>
	
</form>