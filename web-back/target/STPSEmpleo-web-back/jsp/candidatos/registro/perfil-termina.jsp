<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<jsp:useBean id="fecha" scope="page" class="java.util.Date"/>

<c:set var="regexpmail">^[a-zA-Z0-9_-]+(?:\.[a-zA-Z0-9_-]+)*@(?:[a-zA-Z0-9](?:[a-zA-Z0-9-_]*[a-zA-Z0-9-])?\.)+[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?$</c:set>

<%String context = request.getContextPath() +"/";%>

<style type="text/css">
	#dialogMsg_underlay { background-color:gray; }
</style>

<script type="text/javascript" src="js/helper/messageHelper.js"></script>
<!-- Google Code for Registros Conversion Page -->
<script type="text/javascript">
/* <![CDATA[ */
var google_conversion_id = 999490997;
var google_conversion_language = "en";
var google_conversion_format = "2";
var google_conversion_color = "ffffff";
var google_conversion_label = "AbP8CLv0wQMQtYvM3AM";
var google_conversion_value = 0;
/* ]]> */
</script>
<script type="text/javascript" src="http://www.googleadservices.com/pagead/conversion.js"></script>

<script type="text/javascript" src="js/helper/messageHelper.js"></script>
<noscript>
<div style="display:inline;">
<img height="1" width="1" style="border-style:none;" alt="" src="http://www.googleadservices.com/pagead/conversion/999490997/?value=0&amp;label=AbP8CLv0wQMQtYvM3AM&amp;guid=ON&amp;script=0"/>
</div>
</noscript>


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
	dojo.require("dijit.form.FilteringSelect");

	dojo.addOnLoad(function() {
		// ... 
	});

	function doSubmit(method){
		dojo.byId('method').value=method;
		dojo.byId('registroCandidatoForm').submit();
	}

	function irMiEspacio(){
		doSubmit('toMiEspacio');
	}

	function irPerfilLaboral(){
		doSubmit('toMisDatos');
	}

	function continuar(){
		
		if (dijit.byId('correoElectronico').value!='' && 
			!dijit.byId('correoElectronico').isValid()){
			dijit.byId('correoElectronico').reset();
		}

		/*if (validaCorreoElectronicoUnico()==false){
			var textBox = dijit.byId('correoElectronico');
			displayErrorMsg(textBox, 'El correo electronico ya se encuentra registrado.');
			return;
		}*/

		doSubmit('toCurp');
	}

	function enviarPorCorreo(){
		dojo.byId('botonMail').style.display='none';
		dojo.byId('controlesMail').style.display='block';
		dojo.byId('correoElectronico').focus();
	}

	function crearPdfRegistro(){
	 	message('No se pudo enviar los términos y condiciones del PPC con los datos de usuario y contraseña a la cuenta de correo proporcionada, inténtelo nuevamente o seleccione la opción <input type="button" class="ico_imprimir fl" value="Imprime tus claves de acceso" id="btnImprimir" name="btnImprimir" onclick="imprimir();" />');
	/*		
		dojo.byId('method').value='crearPdfRegistro';
		
		bloquedoPantalla();
		
		dojo.xhrPost({url: 'registro.do', form:'registroCandidatoForm', 
			  		  load: function(data){
			  					desbloqueoPantalla();
			  					var res = dojo.fromJson(data); // Datos del servidor
							
			  					if('exito' == res.type){
				  					dojo.byId('btnEnviar').disabled=true; // una vez enviado se evita que vuelvan a enviar
				  					document.getElementById("btnEnviar").className = "ico_enviar off";
				  					message('Correo enviado con éxito');
				  					
			  					} else if('error' == res.type){
			  						message('No se pudo enviar la notificación, favor de intentar de nuevo ó tiene la opción de imprimirlo.');
			  					} else if('errormaildate' == res.type){
			  						<c:choose>
			  						 <c:when test="${registroCandidatoForm.registroPPC && registroCandidatoForm.aceptacionTerminos == requestScope.aceptaTerminosSi.idOpcion}">
			  						 	message('No se pudo enviar los términos y condiciones del PPC con los datos de usuario y contraseña a la cuenta de correo proporcionada, inténtelo nuevamente o seleccione la opción [Imprimir].');
			  						 </c:when>
			  						 <c:otherwise >
			  							message('No se pudo enviar la notificación, favor de verificar que haya capturado los datos necesarios (Usuario, Contraseña, Correo electrónico)');
			  						</c:otherwise>
			  						</c:choose>
			  					} else {
			  						message('No se pudo enviar la notificación, favor de intentar de nuevo ó tiene la opción de imprimirlo.');
			  					}
			 				}
					});
*/
	}
	function enviarCorreoAjax(){
	dojo.byId('method').value='notificaRegistro';
	
		
		bloquedoPantalla();
		dojo.xhrPost({url: 'registro.do', form:'registroCandidatoForm', 
			  		  load: function(data){
			  					desbloqueoPantalla();
			  					var res = dojo.fromJson(data); // Datos del servidor
							
			  					if('exito' == res.type){
				  					dojo.byId('btnEnviar').disabled=true; // una vez enviado se evita que vuelvan a enviar
				  					document.getElementById("btnEnviar").className = "ico_enviar off";
				  					message('Correo enviado con éxito');
				  					
			  					} else if('error' == res.type){
			  						message('No se pudo enviar la notificación, favor de intentar de nuevo ó tiene la opción de imprimirlo.');
			  					} else if('errormaildate' == res.type){
			  						<c:choose>
			  						<c:when test="${registroCandidatoForm.registroPPC && registroCandidatoForm.aceptacionTerminos == requestScope.aceptaTerminosSi.idOpcion}">
			  								messageImprimir('No se pudo enviar los términos y condiciones del PPC con los datos de usuario y contraseña a la cuenta de correo proporcionada, inténtelo nuevamente o seleccione la opción ','open_pdf_ppc();','Imprimir');
			  						</c:when>
			  						 <c:otherwise >
			  							message('No se pudo enviar la notificación, favor de verificar que haya capturado los datos necesarios (Usuario, Contraseña, Correo electrónico)');
			  						</c:otherwise>
			  						</c:choose>
			  					} else {
			  						message('No se pudo enviar la notificación, favor de intentar de nuevo ó tiene la opción de imprimirlo.');
			  					}
			 				}
					});

	}
	
	function copyValidaCorreo(){
		
		copiarValor('correoElectronicoPopup','correoElectronico');
		dialogMsg.hide();
		if(!validaCorreoMsg()){
			return false;
		}
		dialogMsg.hide();
		enviarCorreoAjax;
		cerrarError();
		return true;
	}
	
	function validaCorreo(){
		correo = dijit.byId('correoElectronico');
		if(correo.get("value") == ''){
			return false;
		}
		if (!correo.isValid()){
			return false;
		}
		return true;
	}
	
	function validaCorreoMsg(){
		correo = dijit.byId('correoElectronico');
		
		if(correo.get("value") == ''){
			message('Se debe capturar el correo para poder enviar la información.')
			return false;
		}
		if (!correo.isValid()){
			message('Se debe capturar un correo válido.');
			return false;	
		}

		enviarCorreoAjax();
		return true;
	}
	
	function enviarCorreo(){
		
		if(!validaCorreo()){
			
			messagebuttoninput('Capture su correo','<input id="btnEnviarPop" name="btnEnviarPop" class="boton_naranja" type="button" value="Enviar" onclick="copyValidaCorreo();"/>',
			'<input id="correoElectronicoPopup"  name="correoElectronicoPopup" value="" dojoType="dijit.form.ValidationTextBox" size="65" maxlength="65" trim="true" oncopy="return false;" oncut="return false" onpaste="return false;" regExp="${regexpmail}" invalidMessage="Correo electrónico inválido, verificar captura."/>')
			
			return false;
			
		}
		
		enviarCorreoAjax();
		/*
		if (dijit.byId('correoElectronico').value==''){
			displayErrorMsg(dijit.byId('correoElectronico'), "Debe indicar la cuenta de correo electronico a donde se enviarán sus datos.");
			return;
		}

		if (!dijit.byId('correoElectronico').isValid()){
			displayErrorMsg(dijit.byId('correoElectronico'), dijit.byId('correoElectronico').get("invalidMessage"));
			return;
		}

		dojo.byId('method').value='notificaRegistro';
		
		bloquedoPantalla();
		
		dojo.xhrPost({url: 'registro.do', form:'registroCandidatoForm', 
			  		  load: function(data){
			  					desbloqueoPantalla();

			  					var res = dojo.fromJson(data); // Datos del servidor

			  					if('exito' == res.type){
				  					dojo.byId('btnEnviar').disabled=true; // una vez enviado se evita que vuelvan a enviar
			  					} else if('error' == res.type){
			  						message('No se pudo enviar la notificación, favor de intentar de nuevo ó tiene la opción de imprimirlo.');
			  					} else if('errormaildate' == res.type){
			  						message('No se pudo enviar la notificación, favor de verificar que haya capturado los datos necesarios (Usuario, Contraseña, Correo electronico)');
			  					} else {
			  						message('No se pudo enviar la notificación, favor de intentar de nuevo ó tiene la opción de imprimirlo.');
			  					}
			 				}
					});
*/
	}

	var underlay;

    function bloquedoPantalla() {
		var html = '<h3>¡Se est&aacute;n enviando sus datos por correo!</h3>'+
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
	
	
  	/*function validaCorreoElectronicoUnico(){
		var unico = true;
		
		if (dijit.byId('correoElectronico').value==''){
			return true; // ya que el correo es OPCIONAL no se valida
		}
		
		dojo.byId('method').value='validaCorreoElectronicoUnico';

		dojo.xhrPost({url: 'registro.do', form:'registroCandidatoForm', sync: true, 
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
	}*/

	var dialogMsg;
	
	function open_pdf_ppc(){
		//crearPdfRegistro
		
		    var myWindow = window.open("<%=context%>registro.do?method=crearPdfRegistro", "", "width=800, height=800");
		
	}
	
	function imprimir(){
		
		
			window.print();
		
	}
	
</script>
<noscript>Funciones de JavaScript desactivadas por navegador</noscript>

<c:if test="${registroCandidatoForm.correo}">
<script type="text/javascript">
dojo.addOnLoad(function() {
	enviarPorCorreo();
});
</script>
</c:if>
<form name="registroCandidatoForm" id="registroCandidatoForm" method="post" action="registro.do" dojoType="dijit.form.Form">
	<input type="hidden" name="method" id="method" value="init" dojoType="dijit.form.TextBox"/> <!-- indica el metodo a invocar -->
	<input type="hidden"  id="correoElectronico" name="correoElectronico" value="${registroCandidatoForm.correoElectronico}"
		           regExp="${regexpmail}" invalidMessage="Correo electronico inv&aacute;lido, verificar captura."
		            dojoType="dijit.form.ValidationTextBox"/>
		            
<div class="formApp">
	
	<div class="flow_1">Registro de Candidatos</div>
	<div class="screen_bienvenido">

		<h2 class="bienvenido_1">
		Bienvenido, <br />
		${registroCandidatoForm.nombre} ${registroCandidatoForm.apellido1} ${registroCandidatoForm.apellido2}
		</h2>
		<div class="access_1"><strong>Has creado tu acceso al portal.</strong></div>

		<c:if test = "${registroCandidatoForm.mostrarMensajeRepatriados}">
		<div class="access_1">Como trabajador repatriado puedes ponerte en contacto con nosotros acudiendo a la oficina más cercana del Servicio Nacional del Empleo<p>
		</c:if>

		<c:choose>
		<c:when test="${registroCandidatoForm.registroPPC && registroCandidatoForm.aceptacionTerminos == requestScope.aceptaTerminosSi.idOpcion}">
	    	<div class="msg_listo">A partir de este momento, puedes utilizar todos los servicios del SNE, así como estar inscrito en el Programa de Promoción y Colocación del Seguro de Desempleo (PPC-SD).</div>
	    	<div class="msg_listo">Para mantenerte en el programa, es necesario postularte a ofertas del SNE.</div>
	    	<div class="access_1">
				Estos son los datos de tu registro para acceder al portal
			</div>
		</c:when>
		<c:otherwise>
		
	    <div class="access_1">
			Éstos son tus datos de registro para tener acceso
		</div>
		</c:otherwise>
		</c:choose>
		<div class="user_psswd">
			<div class="labeled">Usuario o correo electrónico:</div>
			<div>${registroCandidatoForm.usuario}</div>
			<div class="labeled">Contraseña</div>
			<div>${registroCandidatoForm.contrasena}</div>
		</div>
		<div class="fecha_alta"><strong>Fecha de alta:</strong> <fmt:formatDate pattern="dd/MM/yy" value="${fecha}"/></div>

		<c:if test="${registroCandidatoForm.candidatoConalep eq true}">
			<div class="form_nav">
				<a class="button" href="<c:url value="/conalep.do?method=init"/>">Completa tu registro CONALEP</a>
			</div>
		</c:if>

		<p style="font-size: 70%" class="labeled"></p>
		<div class="cntrl_alta">
		<c:choose>
		<c:when test="${registroCandidatoForm.registroPPC && registroCandidatoForm.aceptacionTerminos == requestScope.aceptaTerminosSi.idOpcion}">
			<input type="button" class="ico_imprimir fl" value="Imprime tus claves de acceso" id="btnImprimir" name="btnImprimir" onclick="open_pdf_ppc();" />
		</c:when>
		<c:otherwise>
			<input type="button" class="ico_imprimir fl" value="Imprime tus claves de acceso" id="btnImprimir" name="btnImprimir" onclick="imprimir();" />
		</c:otherwise>
		</c:choose>
			<input type="button" id="btnEnviar" name="btnEnviar" class="ico_enviar fl" value="Envía tus claves de acceso" onclick="javascript:enviarCorreo();"/>
	        <div class="clearfix"></div>
	    </div>

	    	<div class="msg_listo">A partir de ahora, puedes buscar ofertas de empleo acordes a tu perfil laboral y postularte a ellas</div>
	    

	<div id="holderMensaje" class="holder_mensaje" style="display:none; text-align: center;z-index:900;">
	       	<div class="posicion">
	        	<p class="mensaje_error" id="errorMsgArea" style='text-align: center;z-index:900;'>
	        	<input id="btnCerrar" name="btnCerrar" class="boton_naranja" type="button" value="Cerrar" onclick="cerrarError();"/>
	        	</p>
			</div>
	    </div>
	    
	    <div id="holderMensaje2" class="holder_mensaje" style="display:none; text-align: center;">
	       	<div class="posicion">
	        	<p class="mensaje_error" id="errorMsgArea2" style='text-align: center;'>
	        	<input id="btnCerrar" name="btnCerrar" class="boton_naranja" type="button" value="Cerrar" onclick="cerrarError();"/>
	        	</p>
			</div>
	    </div>
		<div class="form_nav">
			<input type="button" id="btnPerfilLaboral" name="btnPerfilLaboral" value="Buscar oferta de empleo" onclick="cerrarRuta('<%=response.encodeURL(request.getContextPath() +"/ofertasPerfiles.do?method=init&tipoConsulta=1")%>');"/>
		</div>
		
		
		<!-- div class="estudiarCanada">
		  <a href="${context}registro_candidato_quebec.do">Complementar datos</a>
		</div -->
		
		
		<div class="end_links">	
			<a id="btnPerfilLaboral" name="btnPerfilLaboral" onclick="cerrarRuta('<c:url value="/miEspacioCandidato.do"/>')">Ir a mi espacio</a>
			<a onclick="cerrarRuta('<%=context%>logout.do');">Salir</a>
		</div>
	
	
	<%--
	<div id="registro_bienvenida" class="bloque">
		<p class="registro_datos">
	   	Usuario: ${registroCandidatoForm.usuario}<br/>
	    Contraseña: ${registroCandidatoForm.contrasena}<br/>
	    Fecha de alta: <fmt:formatDate pattern="dd/MM/yy" value="${fecha}"/>
	    </p>

		<div id="controlesMail">
		<label for="correoElectronico">
		<p>Escribe un correo electr&oacute;nico para <strong>enviarte</strong> los datos de tu cuenta o <strong>impr&iacute;melos</strong></p>
		</label>
		<p>
			<input type="text" id="correoElectronico" name="correoElectronico" value="${registroCandidatoForm.correoElectronico}"
		           dojoType="dijit.form.ValidationTextBox" size="65" maxlength="65" trim="true"
		           oncopy="return false;" oncut="return false" onpaste="return false;"
		           regExp="${regexpmail}" invalidMessage="Correo electronico inv&aacute;lido, verificar captura."/>
			<input id="btnEnviar" name="btnEnviar" class="boton_naranja" type="button" value="Enviar" onclick="enviarCorreo();" />
			&nbsp;&nbsp;
		    <input class="boton_naranja" type="button" value="Imprimir" id="btnImprimir" name="btnImprimir" onclick="imprimir();" />
		</p>
		</div>

		<div class="aviso tres_cuartos">
	    	<p>A partir de <strong>ahora puedes buscar ofertas de empleo</strong> acordes con tu perfil laboral y postularte a ellas.</p>
		<p>
			<input type="button" id="btnPerfilLaboral" name="btnPerfilLaboral" class="boton_azul"
		          value="Buscar ofertas de empleo" onclick="javascript:irMiEspacio();"/>
		</p>
	    </div>
	  <p class="tres_cuartos" style="margin: 20px auto;"><strong>Si cuentas con estudios, conocimientos, habilidades y experiencias adicionales<br/> te sugerimos ampl&iacute;es tu perfil laboral.</strong></p>
	    <p>
	    	<input type="button" id="btnEspacio" name="btnEspacio" class="boton_naranja"
	    	       value="Ampliar mi perfil laboral" onclick="javascript:irPerfilLaboral();" />
	    </p>
	</div>
		--%>

	</div>
	
</div>
</form>
