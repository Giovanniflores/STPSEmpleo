<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<jsp:useBean id="fecha" scope="page" class="java.util.Date"/>

<fmt:setBundle basename="MessageResources" var="portalbundle"/>

<c:set var="regexpmail">
^[a-zA-Z0-9_-]+(?:\.[a-zA-Z0-9_-]+)*@(?:[a-zA-Z0-9](?:[a-zA-Z0-9-_]*[a-zA-Z0-9-])?\.)+[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?$
</c:set>


<%String context = request.getContextPath() +"/";%>

<style type="text/css">
	#dialogMsg_underlay { background-color:gray; }
	#underlay_underlay { background-color:gray; }
</style>
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
	dojo.require("dijit.form.FilteringSelect");

	dojo.addOnLoad(function() {
		// ... 
	});

	function enviarPorCorreo(){
		dojo.byId('botonMail').style.display='none';		
		dojo.byId('controlesMail').style.display='block';
		dojo.byId('correoElectronico').focus();
	}
	
	function enviarCorreo() {
		
		if (dijit.byId('correoElectronico').value==''){
			displayErrorMsg(dijit.byId('correoElectronico'), "Debe indicar la cuenta de correo electronico a donde se enviarán sus datos.");
			return;
		}

		if (!dijit.byId('correoElectronico').isValid()){
			displayErrorMsg(dijit.byId('correoElectronico'), dijit.byId('correoElectronico').get("invalidMessage"));
			return;
		}
		dojo.byId('method').value='notificacionRecuperacionPswEmpresa';		
		bloquedoPantalla();		
		dojo.xhrPost({url: 'registroEmpresa.do', form:'activacionForm', 
			  		  load: function(data){
			  					desbloqueoPantalla();		  					
			  					var res = dojo.fromJson(data); // Datos del servidor
			  					if('exito' == res.type) {
			  						message(res.message);
				  					dojo.byId('botonMail').style.display='none';
				  					dojo.byId('controlesMail').style.display='none';				  						
				  					dijit.byId('btnEnviar').disabled=false; // una vez enviado se evita que vuelvan a enviar
			  					} else if('error' == res.type){
			  						message('No se pudo enviar la notificación, favor de intentar de nuevo ó tiene la opción de imprimirlo.');
			  					} else if('errormaildate' == res.type){
			  						message('No se pudo enviar la notificación, favor de verificar que haya capturado los datos necesarios.');
			  					} else {
			  						message('No se pudo enviar la notificación, favor de intentar de nuevo ó tiene la opción de imprimirlo.');
			  					}
			 				}
					});
	}

	var underlay;

    function bloquedoPantalla() {
		var html = '<h3>¡Se estan enviando sus datos por correo!</h3>'+
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

	function closeWindow(){
		dialogMsg.hide();
	}
	
	function imprimir(){
		window.print();
	}
	
	function gotoHome(){
		dojo.byId('method').value='toHome';
		dojo.byId('activacionForm').submit();	
	}

	function gotoLogin(){
		dojo.byId('method').value='toLogin';
		dojo.byId('activacionForm').submit();
	}
	
</script>
<noscript>Funciones de JavaScript desactivadas por navegador</noscript>

<form name="activacionForm" id="activacionForm" method="post" action="activacion.do" dojoType="dijit.form.Form">
	<input type="hidden" name="method" id="method" value="init" dojoType="dijit.form.TextBox"/> <!-- indica el metodo a invocar -->
	<input type="hidden" name="usuario" id="usuario" value="${activacionForm.confirmacion.usuario}"/>
	<input type="hidden" name="contrasena" id="contrasena" value="${activacionForm.confirmacion.contrasena}"/>
	<input type="hidden" name="idEmpresa" id="idEmpresa" value="${activacionForm.idEmpresa}"/>
	
<div class="miEspacio formApp">
	<h3>Tu contraseña</h3>
	<fieldset class="recovery_msg bloque">
		<legend>&nbsp;</legend>
		<p>${activacionForm.confirmacion.nombre}<br/>
			<strong class="c_naranja">Tu contraseña es:</strong>
		</p>
		<p class="aviso un_tercio">
			<span class="c_naranja">${activacionForm.confirmacion.contrasena}</span>
		</p>
		<p><strong>Imprime y guarda</strong> tu usuario y contraseña en un lugar seguro y memorizalos bien</p>
	</fieldset>
		<div class="form_nav">
				<input class="boton_naranja" type="button" value="Imprimir" id="btnImprimir" name="btnImprimir" onclick="imprimir();" />
		</div>
		<div class="form_nav">
			<p div="idLeyendaMail" style="display:none">O <strong>envíalos a tu correo</strong> electrónico que usaste como Usuario</p>
			<p>
			<div id="botonMail">
				<input type="button" id="btnEnvioMail" name="btnEnvioMail" class="boton_naranja"
					   value="Enviar a mi correo electr&oacute;nico"
					   onclick="enviarPorCorreo();" />
			</div>
		</div>
		<div class="form_nav" id="controlesMail" style="display:none">
			<input type="text" id="correoElectronico" name="correoElectronico" value="${activacionForm.confirmacion.correoElectronico}"
		           dojoType="dijit.form.ValidationTextBox" size="65" maxlength="65" trim="true"
		           oncopy="return false;" oncut="return false" onpaste="return false;"
		           regExp="${regexpmail}" invalidMessage="Correo electronico invalido, verificar captura."/>

			<input id="btnEnviar" name="btnEnviar" class="boton_naranja" type="button" value="Enviar" onclick="javascript:enviarCorreo();" />
		</div>
		<div class="form_nav">
			<input type="button" value="Iniciar sesión" class="boton_naranja  bt_grande" onclick="javascript:gotoLogin();"/>
			&nbsp;&nbsp;&nbsp;
			<input type="button" value="Cerrar" class="boton_naranja  bt_grande" onclick="javascript:gotoHome();"/>
		</div>
</div>
</form>
