<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.Calendar" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%String context = request.getContextPath() +"/";%>
<%String remoteAddr = request.getRemoteAddr(); %>
<%
	String idInactivoAPeticionDelUsuario = (null!= request.getSession().getAttribute("idInactivoAPeticionDelUsuario") ? request.getSession().getAttribute("idInactivoAPeticionDelUsuario").toString() : "");
	String idInactivoPorVigencia = (null!= request.getSession().getAttribute("idInactivoPorVigencia") ? request.getSession().getAttribute("idInactivoPorVigencia").toString() : "");
	String idInactivoPorAdmor = (null!= request.getSession().getAttribute("idInactivoPorAdmor") ? request.getSession().getAttribute("idInactivoPorAdmor").toString() : "");
	String usuarioInactivoAPeticionDelUsuario = (null!= request.getSession().getAttribute("UsuarioInactivoAPeticion") ? request.getSession().getAttribute("UsuarioInactivoAPeticion").toString() : "");
	String usuarioInactivoPorVigencia = (null!= request.getSession().getAttribute("usuarioInactivoPorVigencia") ? request.getSession().getAttribute("usuarioInactivoPorVigencia").toString() : "");
%>
<c:set var="regexpmail">
^[a-zA-Z0-9_-]+(?:\.[a-zA-Z0-9_-]+)*@(?:[a-zA-Z0-9](?:[a-zA-Z0-9-_]*[a-zA-Z0-9-])?\.)+[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?$
</c:set>

<c:set var="regexplogin">
^[a-zA-ZáéíóúÁÉÍÓÚñÑ0-9]{8,12}$
</c:set>

<c:set var="regexprazonsocial">
^[A-Za-z\s\d\-.&,áéíóúÁÉÍÓÚñÑ']{3,150}$
</c:set>
 <script src="dojotoolkit/dojo/dojo.js"
 	djConfig="parseOnLoad: true, locale:'es'"></script>
<script type="text/javascript" src="js/helper/messageHelper.js"></script>
<script type="text/javascript" src="http://www.google.com/recaptcha/api/js/recaptcha_ajax.js"></script>   	
<script type="text/javascript">
	dojo.require("dijit.dijit"); // loads the optimized dijit layer
	dojo.require("dijit.form.Form");
	dojo.require("dijit.form.ValidationTextBox");
	dojo.require("dijit.form.TextBox");
	dojo.require("dijit.form.ComboBox");
	dojo.require("dojo.data.ItemFileReadStore");
    dojo.require("dijit.form.DateTextBox");    
	dojo.require('dijit.Dialog');
	dojo.require("dijit.form.CheckBox");
	dojo.require("dijit.form.FilteringSelect");
	
	dojo.addOnLoad(function() {
		mostrarDivTipo();
		//showRecaptcha('captcha2');
	}); 
	
	function recupera(){
		estableceValores();
		if (validarCampos()){
			doSubmitAjax();
			//validacapGoogle();
			//validaCaptcha();
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
		dojo.xhrPost({url: 'registro.do?method=cambiaPregunta',
			  load: function(data) {
				  document.getElementById('captchaPregunta').innerHTML = '<span>*</span>' + data;
			  }
				  		
			});
	}
	
	function validaCaptcha(){
		alert('validaCapthca');
		alert(document.getElementById('response').value);
		//alert(dojo.byId('response').value);
		
	}

	function validarCampos() {
		/* if (dijit.byId('code').value==''){
			displayErrorMsg(dijit.byId('code'), dijit.byId('code').get("missingMessage"));
			return false;
		}

		if (!dijit.byId('code').isValid()){
			displayErrorMsg(dijit.byId('code'), dijit.byId('code').get("invalidMessage"));
			return false;			
		}

		if (dijit.byId('usuario').value==''){
			displayErrorMsg(dijit.byId('usuario'), dijit.byId('usuario').get("missingMessage"));
			return false;
		} */
		
		/**if (!validarUsuario()){
			return false;
		}**/
		if(dojo.byId('tipoUsuarioCandidato').checked){

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

		} else if(dojo.byId('tipoUsuarioEmpresa').checked) {
			if (!dijit.byId('tiposEmpresaSelect').isValid()) {
				message(dijit.byId('tiposEmpresaSelect').get("missingMessage"));
				dijit.byId('tiposEmpresaSelect').focus();
				dojo.byId('btnEnviar').focus();
				return false;
			}
			if (!dijit.byId('diaSelect').isValid()){
				dijit.byId('diaSelect').focus();
				dojo.byId('btnEnviar').focus();
				return false;
			}
			if (!dijit.byId('mesSelect').isValid()){
				dijit.byId('mesSelect').focus();
				dojo.byId('btnEnviar').focus();
				return false;
			}
			if (!dijit.byId('anioSelect').isValid()){
				dijit.byId('anioSelect').focus();
				dojo.byId('btnEnviar').focus();
				return false;
			}
			
			if (!dijit.byId('respuestaUsuario').isValid()){
				displayErrorMsg(dijit.byId('respuestaUsuario'), dijit.byId('respuestaUsuario').get("missingMessage"));
				return false;
			}
		}	
		
		return true;
	}

    function estableceValores(){
    	dojo.byId('dia').value  = dijit.byId('diaSelect').get('value');
    	dojo.byId('anio').value = dijit.byId('anioSelect').get('value');
    	dojo.byId('mes').value  = dijit.byId('mesSelect').get('value');
    	if(dijit.byId('tiposEmpresaSelect')){
    		dojo.byId('idTipoEmpresa').value = dijit.byId('tiposEmpresaSelect').get('value');	
    	}    	 	
    	
    }

	
	function changeSecureCodeImage() {
		var img = document.getElementById('imgseccode')
		if(img) {
			img.src = 'captcha?time=' + new Date();
			dijit.byId('code').set('value', '');
		}
	}

	function isValidCaptcha() {
		return true;/*
	    dojo.xhrPost({url: 'activacion.do?method=captcha&code='+ dijit.byId('code').value,
					  load: function(data) {
								var res = dojo.fromJson(data);
								if ('err' == res.type) {
						   			dijit.byId('code').focus();
						   			displayErrorMsg(dijit.byId('code'), dijit.byId('code').get("invalidMessage"));

								} else {
									doSubmitAjax();
								}
							}
					});*/
	}

	function doSubmitAjax(){

		if(dojo.byId("tipoUsuarioEmpresa").checked){
			dojo.byId('method').value = 'activacionEmpresa';
			dojo.byId('btnEnviar').disabled=true;
			dojo.byId('activacionForm').submit();	

		} else if(dojo.byId("tipoUsuarioCandidato").checked) {
			dojo.byId('method').value = 'activacionCandidato';
			dojo.byId('btnEnviar').disabled=true;
			dojo.byId('activacionForm').submit();
		}
	}

	function mostrarDivTipo(){		
		if(document.getElementById("tipoUsuarioEmpresa").checked){
			document.getElementById("divEmpresa").style.display='block';
			document.getElementById("divCandidato").style.display='none';
			dojo.byId('usuario').focus();
		} else {
			document.getElementById("divCandidato").style.display='block';
			document.getElementById("divEmpresa").style.display='none';
			dojo.byId('curp').focus();
		}	
	}
	
	function actualizaCamposRelacionadosConTipoEmpresa(){
		var vIdTipoEmpresa = dijit.byId('tiposEmpresaSelect').get('value');
		
		//FIXME
		if(vIdTipoEmpresa){
		
			if(vIdTipoEmpresa == 1){
								
				document.getElementById('divEmpresaPrivada').style.display='none';
				document.getElementById('divRazonSocial').style.display='block';
				document.getElementById('lblRazonSocial').innerHTML = '${activacionForm.etiquetaEmpresaPublica}';	
			
			} else if(vIdTipoEmpresa == 2){
			
				document.getElementById('divEmpresaPrivada').style.display='block';
				document.getElementById('divRazonSocial').style.display='none';				
				document.getElementById('lblRazonSocial').innerHTML = '';	
			
			} else if(vIdTipoEmpresa == 3){
			
				document.getElementById('divEmpresaPrivada').style.display='none';
				document.getElementById('divRazonSocial').style.display='block';
				document.getElementById('lblRazonSocial').innerHTML = '${activacionForm.etiquetaEmpresaOng}';	
				
			}
			
			if(dijit.byId('tiposEmpresaSelect'))
				dojo.byId('idTipoEmpresa').value = dijit.byId('tiposEmpresaSelect').get('value');				
			
		}			
				
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

  	function cerrarError(){
  		dojo.byId('holderMensaje').style.display='none';
  	}

  	
  	function showRecaptcha(nombreDiv){
  			Recaptcha.create("6Le1ZeMSAAAAAJUZu1g9iswjIkiuVQ_k2XVtuTB3",
  				    nombreDiv,
  				    {
  				custom_translations : {
  					 instructions_visual : "Escribe las dos palabras:",
  					 instructions_audio : "Escribe lo que escuchas",
  					 play_again : "Reproducir sonido de nuevo",
  					 cant_hear_this : "Descargar sonido en formato MP3",
  					 visual_challenge : "Enfrentar un desafío visual",
  					 audio_challenge : "Enfrentar un desafío de audio",
  		             refresh_btn : "Enfrentar un nuevo desafío",
  		             help_btn : "Ayuda",
  				 },
  				      theme: 'white',
  				      lang: 'es',
  				      callback: Recaptcha.focus_response_field
  				    }
  				  );
  		}
  	
  	function muestraDatos(){ 		
  		
  		var vurl = 'http://www.google.com/recaptcha/api/verify?'+
  		'privatekey=6Le1ZeMSAAAAAAXSYw_mo65yIrRUBpQU4PjKNbIW&'+
  		'remoteip=192.168.5.88'+
  		'&challenge='+Recaptcha.get_challenge()+
  		'&response='+Recaptcha.get_response();
  		alert(vurl);
	    dojo.xhrGet({url: vurl, form:'myForm',
			  load: function(data) {
				        alert('data');
						/*var res = dojo.fromJson(data);
						if ('err' == res.type) {
				   			dijit.byId('code').focus();
				   			displayErrorMsg(dijit.byId('code'), dijit.byId('code').get("invalidMessage"));

						} else {
							doSubmitAjax();
						}*/
					}
			});	
  		
  	}
  	
  	function llenaChallenge(){
  		
  		
  		document.getElementById('challenge').value=Recaptcha.get_challenge();
  		document.getElementById('response').value=Recaptcha.get_response();  		
  	}
  	
</script>


	<script type="text/javascript">
 var RecaptchaOptions = {
		 custom_translations : {
			 instructions_visual : "Escribe las dos palabras:",
			 instructions_audio : "Escribe lo que escuchas",
			 play_again : "Reproducir sonido de nuevo",
			 cant_hear_this : "Descargar sonido en formato MP3",
			 visual_challenge : "Enfrentar un desafío visual",
			 audio_challenge : "Enfrentar un desafío de audio",
             refresh_btn : "Enfrentar un nuevo desafío",
             help_btn : "Ayuda",
		 },
    theme : 'white',
    lang : 'es',
 };
 </script>

<noscript>Funciones de JavaScript desactivadas por navegador</noscript>

<c:if test="${not empty ERROR_MSG}">
	<script type="text/javascript">
		dojo.addOnLoad(function() {
			message('${ERROR_MSG}');
		}); 
	</script>
</c:if>

<div dojoType="dojo.data.ItemFileReadStore" jsId="diasStore"  url="${context}registro.do?method=dias"></div>
<div dojoType="dojo.data.ItemFileReadStore" jsId="mesesStore" url="${context}registro.do?method=meses"></div>
<div dojoType="dojo.data.ItemFileReadStore" jsId="aniosStore" url="${context}registro.do?method=aniosactual"></div>

<div dojoType="dojo.data.ItemFileReadStore" jsId="tiposEmpresaStore" url="${context}activacion.do?method=tiposEmpresa"></div>

<form name="activacionForm" id="activacionForm" method="post" action="activacion.do" dojoType="dijit.form.Form">
	<input type="hidden" name="method" id="method" value="init"/> <!-- indica el metodo a invocar -->
 	<input type="hidden" name="dia" id="dia" value="" dojoType="dijit.form.TextBox"/>
 	<input type="hidden" name="mes" id="mes" value="" dojoType="dijit.form.TextBox"/>
 	<input type="hidden" name="anio" id="anio" value="" dojoType="dijit.form.TextBox"/>
 	<input type="hidden" name="idTipoEmpresa" id="idTipoEmpresa" value="" dojoType="dijit.form.TextBox"/>
 	<input type="hidden" value="${param.remoteAddr}"/>
	<c:choose>
		<c:when test="${not empty idInactivoAPeticionDelUsuario and idInactivoAPeticionDelUsuario != ''}">
			<script>
				message('Tu cuenta de usuario ${usuarioInactivoAPeticionDelUsuario}  se encuentra en estatus inactiva, para reactivarla dirígete a la seccion de "Recuperar contraseña" y genera una nueva.');
			</script>		
		</c:when>
		<c:when test="${not empty idInactivoPorVigencia and idInactivoPorVigencia != ''}">
			<script>
			message('Tu cuenta de usuario ${usuarioInactivoPorVigencia}  se encuentra en estatus inactiva, para reactivarla dirígete a la seccion de "Recuperar contraseña" y genera una nueva.');
			</script>		
		</c:when>							
		<c:when test="${not empty idInactivoPorAdmor and idInactivoPorAdmor != '' }">
			<script>
				message('Se detectó un problema con su registro, para ingresar nuevamente acuda a las oficinas del SNE más cercanas.');
			</script>		
		</c:when>			
	</c:choose>
	
	
	<div class="row">
		<div class="col-sm-12">
			<div class="page-header">
				<h3>Recuperar contraseña</h3>
			</div>
		</div>
		
		<div class="col-sm-12">
			<fieldset class="panel panel-PE">
				<div class="panel-heading">
					<legend class="panel-title">Tipo Usuario</legend>
				</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6 col-sm-offset-6">
							<span class="labeled pull-right">Los datos marcados con <span>*</span> son obligatorios</span>
						</div>
						
						<div class="clearfix"></div>
						
						<div class="col-sm-6">
							<div class="form-group">
								<div class="labeled">Tipo de usuario</div>
								<label for="tipoUsuarioEmpresa" class="checkbox-inline">
									<input class="fl" type="radio" name="tipoUsuario" value="1"  id="tipoUsuarioEmpresa" onclick="mostrarDivTipo()"/>
									Empresa
								</label>
								<label for="tipoUsuarioCandidato" class="checkbox-inline">
									<input class="fl" type="radio" name="tipoUsuario" value="2"  id="tipoUsuarioCandidato" onclick="mostrarDivTipo()" checked="checked"/>
									Candidato
								</label>
							</div>
						</div>
						
						<div class="col-sm-6" style="display:none">
							<div id="holderMensaje">
								<div id="errorMsgArea">
						       		<input id="btnCerrar" name="btnCerrar" type="button" value="Cerrar" onclick="cerrarError();"/>
								</div>
							</div>
						</div>
						
						<div id="divCandidato" class="col-xs-9" style="display: none">
							<div class="form-group">
								<label for="curp"><span>*</span> Escribe tu <strong>CURP</strong></label>
								<input id="curp" name="curp" class="form-control"
									   dojoType="dijit.form.ValidationTextBox"
									   required="true" missingMessage="Es necesario que capture su CURP."
						               regExp="[a-zA-Z]{4}\d{6}[hmHM][a-zA-Z]{2}[B-DF-HJ-NP-TV-Z|b-df-hj-np-tv-z]{3}[a-zA-Z0-9][0-9]" 
									   invalidMessage="CURP invalida, favor de verificar su captura."
									   maxlength="18" uppercase="true" clearOnClose="true" trim="true" uppercase="true"/>
							</div>
						</div>
						
						<div id="divEmpresa" style = "display: none">
							<div class="col-xs-9">
								<div class="form-group">
									<label for="lblTipoEmpresa"><span>*</span> Tipo de empresa</label>
									<select id="tiposEmpresaSelect" name="tiposEmpresaSelect" class="form-control"
											required="true" missingMessage="Seleccione el tipo de empresa" 
									        store="tiposEmpresaStore" dojotype="dijit.form.FilteringSelect"
									        value="${activacionForm.idTipoEmpresa}" onchange="javascript:actualizaCamposRelacionadosConTipoEmpresa();" >
									</select>
								</div>	
							</div>
							
							<div class="clearfix"></div>
							
							
							<div id="divRazonSocial" style="display:none">	
								<div class="col-xs-9">	
									<div class="form-group">	
										<label for="razonSocial" id="lblRazonSocial" > </label>
										<input type="text" id="razonSocial" name="razonSocial" class="form-control" 
									              dojoType="dijit.form.ValidationTextBox" 
									              required="false"          missingMessage="razón social."
									              regExp="${regexprazonsocial}" 
									              invalidMessage="Razón social inválida, no se permiten números ni caracteres especiales y la longitud mínima es 3 caracteres." 
									              maxlength="150" uppercase="true" trim="true"   
									              value="${activacionForm.razonSocial}"
									              onkeyup="limpiarEspacios(this)"/>
									 </div>
							     </div>  								
							</div>
							
							<div class="clearfix"></div>
							
							<div id="divEmpresaPrivada" style="display:none">
								<div class="col-xs-12">
									<div class="form-group">
	                                    <label>
	                                    	<span class="c_naranja">*</span> Selecciona la fecha de nacimiento si eres Persona Física o la fecha de Acta Constitutiva si eres Persona Moral
										</label>
									</div>
								</div>	
								
								<div class="clearfix"></div>
								
                                <div class="col-md-3 col-xs-9">
                                	<div class="form-group">
		                                <select id="diaSelect" name="diaSelect" class="form-control"
											required="true" missingMessage="Debe indicar el dia."
											invalidMessage="Dia invalido, favor de verificar."
											promptMessage="Dia" maxHeight="250"
								        	value="${activacionForm.dia}" autocomplete="true"
								        	dojotype="dijit.form.FilteringSelect" store="diasStore"></select>
								    </div>
							    </div>
							    <div class="col-md-3 col-xs-9">
							    	<div class="form-group">
		                                <select id="mesSelect" name="mesSelect" class="form-control"
											required="true" missingMessage="Debe indicar el mes."
										    invalidMessage="Mes invalido, favor de verificar."
									    	promptMessage="Mes" maxHeight="250"
								        	value="${activacionForm.mes}" autocomplete="true"
								        	dojotype="dijit.form.FilteringSelect" store="mesesStore"></select>
								    </div>
							     </div>
							    <div class="col-md-3 col-xs-9">
							    	<div class="form-group">
		                                <select id="anioSelect" name="anioSelect" class="form-control"
											required="true" missingMessage="Debe indicar el año."
											invalidMessage="Año invalido, favor de verificar." 
											promptMessage="Año" maxHeight="250"
								        	value="${activacionForm.anio}" autocomplete="true"
								        	dojotype="dijit.form.FilteringSelect" store="aniosStore"></select>
								    </div>
								</div>
							</div>
						</div>
				</fieldset>
		</div>
		
		<div class="col-sm-12">
			<fieldset class="panel panel-PE">
				<div class="panel-heading">
					<legend class="panel-title">Usuario</legend>
				</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-xs-9">
							<div class="form-group">
								<label for="usuario"><span>*</span> Escribe tu correo electrónico (si eres empresa) o el nombre de usuario (si eres candidato) con el que te registraste</label>
								<input type="text" name="usuario" id="usuario" size="40" maxlength="65"
						    	   dojoType="dijit.form.ValidationTextBox" class="form-control"
						    	   required="true" missingMessage="El usuario es requerido."/> 
							</div>
						</div>
					</div>
				</div>
			</fieldset>
		</div>
		
		<div class="col-sm-12">
			<fieldset class="panel panel-PE">
				<div class="panel-heading">
					<legend class="panel-title">Responde a la siguiente pregunta</legend>
				</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-xs-9">
							<div id="divCaptcha" class="form-group">
						        <label id="captchaPregunta"><span>*</span>${activacionForm.pregunta}</label>
						        <input type="text" name="respuestaUsuario" id="respuestaUsuario" size="40" maxlength="15"
						    	   dojoType="dijit.form.ValidationTextBox" class="form-control"
						    	   required="true" missingMessage="Es necesario proporcionar la respuesta a la pregunta"/>
						    	<span class="help-block"><a href="javascript:cambiarPregunta()" > Cambiar pregunta</a></span>   
						        
							    <html:messages id="errors">
									<span class="redFont Font80"><bean:write name="errors"/></span><br/>
								</html:messages>
							</div>
						</div>
					</div>
				</div>
			</fieldset>
		</div>
		
		
	
	
	</div>
	
	<html:messages id="errors">
		<span class="redFont Font80"><bean:write name="errors"/></span><br/>
	</html:messages>
<!-- 			<p> -->
<%-- 	    		<img src="captcha?time=<%=""+ Calendar.getInstance().getTimeInMillis()%>" id="imgseccode" width="155" height="65"/><br/><br/> --%>
<!-- 		    	<a href="javascript:changeSecureCodeImage();" class="c_naranja">Cambiar imagen</a> -->
<!-- 			</p> -->
<!-- 			<p> -->
<!-- 				<span class="c_naranja">*</span> -->
<!-- 				El texto de la imagen es: -->
<!-- 	       		<input type="text" id="code" name="code" dojoType="dijit.form.ValidationTextBox" -->
<!-- 	       		       required="true" -->
<!-- 	       		       missingMessage="Es necesario que capture el texto de la imagen." -->
<!-- 	       		       invalidMessage="No corresponde la cadena de verificación." -->
<!-- 	       		       style="width:18%" maxlength="6" size="15"/> -->
<!-- 			</p> -->
			
	<div class="form_nav text-center">
		<div class="form-group">
			<input type="button" id="btnEnviar" value="Recuperar contraseña" class="btnPE" onclick="recupera();"/>
		</div>
	</div>
	
	
	

</form>

	<div dojoType="dijit.Dialog" id="dialogErrorCaptcha" title="Error" style="display:none"  style="width:300px;height:150px;" draggable ="false" >
		El texto introducido no es el mismo que el de la imagen.
	</div>


