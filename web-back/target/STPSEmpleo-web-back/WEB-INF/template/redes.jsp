<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.Calendar" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%String context = request.getContextPath() +"/";%>
<%String remoteAddr = request.getRemoteAddr(); %>

<script type="text/javascript" src="http://www.google.com/recaptcha/api/js/recaptcha_ajax.js"></script> 
<script type="text/javascript">

				dojo.require("dijit.dijit"); // loads the optimized dijit layer
				dojo.require("dijit.form.TextBox");
				dojo.require("dijit.Dialog");
				dojo.require("dijit.form.ValidationTextBox");
				
				dojo.require("dijit.form.Form");
				

				
				function validar(){
					
					if (validarCampos()){
						
						//showErrorDialog();
						//validacapGoogle();
						validaCaptcha();
						
						//isValidCaptcha();
						
					}
				}
				
				function validacapGoogle(){
					dojo.xhrPost({url: 'activacion.do?method=veriCaptchaGoogle&recaptcha_challenge_field='+ Recaptcha.get_challenge()+
				  		'&recaptcha_response_field='+Recaptcha.get_response(),
						  load: function(data) {
							  		//alert(data);
									var res = dojo.fromJson(data);
									if ('err' == res.type) {
							   			//dijit.byId('code').focus();
							   			//displayErrorMsg(dijit.byId('code'), dijit.byId('code').get("invalidMessage"));
							   			//alert('La solucion al captcha no es correcta');
							   			dijit.byId('dialogErrorCaptcha').show();
							   			javascript:showRecaptcha('captcha2');
									} else {
										//alert('La solucion es correcta en el captcha');
										doSubmitAjax();
									}
								}
						});
				}
				
				function cambiarPregunta(){
					dojo.xhrPost({url: '<%=request.getContextPath()%>/registro.do?method=cambiaPregunta',
						  load: function(data) {
							  document.getElementById('captchaPregunta').innerHTML = '<span>*</span>' + data;
						  }
							  		
						});
				}
				
// 				function validaCaptcha(){
// 					alert('validaCapthca');
// 					//dojo.xhrPost({url: '${pageContext.request.contextPath}/registro.do?method=validaCaptcha',
// 					alert(document.getElementById('response').value);
// 					//alert(dojo.byId('response').value);
					
// 				}
				
				

				function isValidCaptcha() {
					return true;
				}
				
				function validarUsuario() {
					var valido = true;
					
					var errmsg = '';
					
					var login = dijit.byId('usuario').get('value');
					
					if (login){
						 if (login.indexOf("@") !=-1) {
							var regExp = /${regexpmail}/;
							errmsg = 'Correo electronico invalido, favor de verificar la captura.';
							valido = regExp.test(login);
						 } else {
							var regExp = /${regexplogin}/;
							errmsg = 'Nombre de usuario invalido, debe contar entre 8 y 12 caracteres, no se permiten acentos o caracteres especiales, ejemplos: "$,%,&,?,#,!"';
							valido = regExp.test(login);

						 }
					}
				
					if (!valido){
						var textBox = dijit.byId("usuario");
						displayErrorMsg(textBox, errmsg);
					}
					
					return valido;
				}

				function displayErrorMsg(textBox, errmsg) {
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

			  	function showErrorMsg(errmsg){
			  		dojo.byId('errorMsgArea').innerHTML = errmsg;
			  		dojo.byId('holderMensaje').style.display='block';
			  	}
			  	function showErrorMsg(errmsg){
			  		replaceDialogMsg(errmsg, "errorMsgArea");
			  		dijit.byId("holderMensaje").show();
			  	}

			  	function cerrarError(){
			  		dojo.byId('holderMensaje').style.display='none';
			  	}

			  	
			  	
			  	function validarCampos() {
					
					if(dojo.byId('btnRecomienda').click){

						if (dijit.byId('remitente').value==''){
							displayErrorMsg(dijit.byId('remitente'), dijit.byId('remitente').get("missingMessage"));
							return false;
						}

						if (!dijit.byId('mailRemitente').isValid()){
							displayErrorMsg(dijit.byId('mailRemitente'), dijit.byId('mailRemitente').get("missingMessage"));
							return false;
						}
						
						if (!dijit.byId('destinatario').isValid()){
							displayErrorMsg(dijit.byId('destinatario'), dijit.byId('destinatario').get("missingMessage"));
							return false;
						}
						if (!dijit.byId('mailDestinatario').isValid()){
							displayErrorMsg(dijit.byId('mailDestinatario'), dijit.byId('mailDestinatario').get("missingMessage"));
							return false;
						}
						
						if (!dijit.byId('respuestaUsuario').isValid()){
							displayErrorMsg(dijit.byId('respuestaUsuario'), dijit.byId('respuestaUsuario').get("missingMessage"));
							return false;
						}
// 						if (!dijit.byId('mensajeAdicional').value==''){
// 							displayErrorMsg(dijit.byId('mensajeAdicional'), dijit.byId('mensajeAdicional').get("missingMessage"));
// 							return false;
// 						}
						
						
                        
					}	
					
					return true;
				
			  	}
			  	function captchaIsNull(){
					if (dijit.byId('respuestaUsuario').value == ''){
						displayErrorMsg(dijit.byId('respuestaUsuario'), dijit.byId('respuestaUsuario').get("missingMessage"));
						return true;
					}
					
					return false;
				}
			  	
			  	function doSubmitAjax(){

			  		if (captchaIsNull())
						return;
						
			  		dojo.byId('registroForm');	
				    	dojo.xhrPost({url: '<%=request.getContextPath()%>/recomendar.do?method=notificaRegistro',  form:'RecomendarForm', timeout:180000,
				        	load: function(data){    		
				        		
				        		dojo.byId('RecomendarForm').innerHTML=data;    
				        		location.href = '#showAll';	
				        		var res = dojo.fromJson(data);
				        		if ('notificaRegistro'== res.result){
				        			
				        		dijit.byId('Exito').show();
				        		}else{ 
									dijit.byId('dialogErrornotifica').show();
				        		}
				        		
				        	}});
					

					}
			  
			  

				function closeWindow(){
					dialogMensaje.hide();
				}
				
				
				
				function validaCaptcha(){
					var valido = false;

					//dojo.byId('method').value='validaCaptcha';
					dojo.xhrPost({url: '<%=request.getContextPath()%>/registro.do?method=validaCaptcha', form:'RecomendarForm', sync: true, 
						  		  load: function(data){
						  					var res = dojo.fromJson(data);
						  					
											if ('captcha'== res.statusOper ){
												
												valido = true;
												doSubmitAjax();
											}else{ 
												dijit.byId('dialogErrorCaptcha').show();
											
						 				}
						  		  }
					
								});
					
					return valido;
				}
				function alertMsg(msg){
					alert(msg);
				}
				
// 				require(["dijit/Dialog", "dojo/domReady!"], function(Dialog){
// 				    myDialog = new Dialog({
// 				        title: "My Dialog",
// 				        content: "Test content.",
// 				        style: "width: 300px"
// 				    });
// 				});
			  	
</script>
<c:if test="${not empty ERROR_MSG}">
	<script type="text/javascript">
		dojo.addOnLoad(function() {
			message('${ERROR_MSG}');
		}); 
	</script>
</c:if>

<div id="socialMedia" class="panel panel-grey socialMedia">
	<div class="panel-body">
		<a href="javascript:print()" class="iconPrint">
			<img src="<%=request.getContextPath()%>/css/img/icon-imprimir.png" class="pull-right"
				alt="Imprimir página" title="Imprimir página">
		</a>

		<img id="btnRecomienda" src="<%=request.getContextPath()%>/css/img/icon-correo_tools.png"
			class="pull-right" alt="Recomendar a un amigo" title="Recomendar a un amigo" onclick="cambiarPregunta();">

		<a target="_blank" href="https://twitter.com/share" class="twt">
			<img src="<%=request.getContextPath()%>/css/img/icon-twitter_tools.png" class="pull-right"
				alt="Compartir en twitter" title="Compartir en twitter">
		</a>
		<img id="facebookShare" src="<%=request.getContextPath()%>/css/img/icon-facebook_tools.png" 
			class="pull-right"  alt="Compartir en facebook" title="Compartir en facebook">
	</div>
</div>

<div id="recomientaPE" class="panel panel-transparent" style="display: none;">
	 <div class="panel-heading">
      <h3 class="panel-title">
        Recomendar
      </h3>
    </div>
	<div class="panel-body">
		<form name="RecomendarForm" id="RecomendarForm" action="/recomendar.do" dojoType="dijit.form.Form">
			<div class="col-sm-6">
				<div class="form-group" >
				    <label for="remitente" ><span>*</span>Remitente:</label>
				    <input id="remitente" name="txtFromName" type="text" class="form-control input-sm" dojoType="dijit.form.ValidationTextBox" required="true" missingMessage="Es necesario el valor de Remitente">
				  </div>
			</div>
			<div class="col-sm-6">
				<div class="form-group" >
				    <label for="mailRemitente"><span>*</span>Correo del Remitente:</label>
				    <input id="mailRemitente" name=txtFromEmail type="email" class="form-control input-sm" dojoType="dijit.form.ValidationTextBox"  required="true" missingMessage="Es necesario el Correo de Remitente">
				  </div>
			</div>
			<div class="col-sm-6">
				<div class="form-group" >
				    <label for="destinatario"><span>*</span>Destinatario:</label>
				    <input id="destinatario" name=txtToName type="text" class="form-control input-sm" dojoType="dijit.form.ValidationTextBox"  required="true" missingMessage="Es necesario el valor de Destinatario">
				  </div>
			</div>
			<div class="col-sm-6">
				<div class="form-group" >
				    <label for="mailDestinatario"><span>*</span>Correo del destinatario:</label>
				    <input id="mailDestinatario" name=txtToEmail type="email" class="form-control input-sm" dojoType="dijit.form.ValidationTextBox"  required="true" missingMessage="Es necesario el Correo de Destinatario">
				  </div>
			</div>
			<div class="col-sm-6">
			<div id="divCaptcha" class="form-group" >
			
			<legend class="panel-title">Responde a la siguiente pregunta</legend>
			
							<label id="captchaPregunta"><span>*</span>${sessionScope.registroForm.pregunta}</label>
						        <input type="text" name="respuestaUsuario" id="respuestaUsuario" size="40" maxlength="15"
						    	   dojoType="dijit.form.ValidationTextBox" class="form-control"
						    	   required="true" missingMessage="Es necesario proporcionar la respuesta a la pregunta"/>
						    	<span class="help-block"><a href="javascript:cambiarPregunta()" > Cambiar pregunta</a></span>   
						       
							    <html:messages id="errors">
									<span class="redFont Font80"><bean:write name="errors"/></span><br/>
								</html:messages>
							</div>
			</div>
<!-- 			<div class="col-sm-6"> -->
<!-- 				<div class="form-group" dojoType="dijit.Texto_Imagen"> -->
<!-- 				    <label for="capcha">El texto de la imagen es:oooooooooooooo</label> -->
<!-- 				    <input id="capcha" type="email" class="form-control input-sm"> -->
<!-- 				  </div> -->
<!-- 			</div> -->
			<div class="col-sm-12">
				<div class="form-group" >
				    <label for="mensajeAdicional">Mensaje adicional</label>
				    <textarea id="mensajeAdicional" name=tarMsg class="form-control" rows="3" dojoType="dijit.form.TextBox"></textarea>
				  </div>
			</div>
			<div class="col-sm-4">
				<div class="form-group">
					<input id="cancelaRecomendar" class="btn-sm btn-buscador btn-block" type="button" value="Cancelar" onClick="validar">	    
				</div>
			</div>
			<div class="col-sm-4">
				<div class="form-group">
					<input class="btn-sm btn-buscador btn-block" type="reset" value="Limpiar">	    
				</div>
			</div>
			<div class="col-sm-4">
				<div class="form-group">
				
					<input   id="enviar" class="btn-sm btn-buscador btn-block" type="button" value="Enviar" onClick="validar();">	  					    
				
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

	<div dojoType="dijit.Dialog" id="exito" title="Exito" draggable ="false">
	<div class="msg_contain">
		Se ha enviado su recomendación.
	</div>
	<p class="form_nav">
		<button class="button" onclick="dijit.byId('exito').hide();">Aceptar</button>
	</p>
</div>
			
<div dojoType="dijit.Dialog" id="dialogErrornotifica" title="Error" draggable ="false">
	<div class="msg_contain">
		No se ha enviado su recomendación.
	</div>
	<p class="form_nav">
		<button class="button" onclick="dijit.byId('dialogErrornotifica').hide();">Aceptar</button>
	</p>
</div>
		<p class="col-sm-4" <span>*</span>Datos requeridos </p>	
			
		</form>
	</div>
</div>


