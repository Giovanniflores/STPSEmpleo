<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<jsp:useBean id="fecha" scope="page" class="java.util.Date"/>

<c:set var="regexpmail">^[a-zA-Z0-9_-]+(?:\.[a-zA-Z0-9_-]+)*@(?:[a-zA-Z0-9](?:[a-zA-Z0-9-_]*[a-zA-Z0-9-])?\.)+[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?$</c:set>

<%String context = request.getContextPath() +"/";%>

<style type="text/css">
	#dialogMsg_underlay { background-color:gray; }
	#underlay_underlay { background-color:gray; }
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
	dojo.require("dijit.form.FilteringSelect");

	dojo.addOnLoad(function() {
		// ... 
	});

	function doSubmit(method){
		dojo.byId('method').value=method;
		dojo.byId('registroCandidatoForm').submit();
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
	
	function enviar(){
		
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
	
  	function showErrorMsg(errmsg){
  		dojo.byId('errorMsgArea').innerHTML = errmsg;
  		dojo.byId('holderMensaje').style.display='block';
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
	
	function message(msg){
		var html =
		'<p style="text-align: center;">'+ msg +'</p>'+
		'<p style="text-align: center;">'+
		'<button id="btnCerrar" name="btnCerrar" dojoType="dijit.form.Button" onclick="closeWindow();">Cerrar</button>'+
	    '</p>';

		dialogMsg = new dijit.Dialog({
	        title: 'Mensaje',
	        content: html,
	        style: "width:300px;",
	        showTitle: false, draggable : false, closable : false
	    });		
		
		//dojo.style(dialogDetalle.closeButtonNode,"display","none");	
		dialogMsg.show();
	}

	function closeWindow(){
		dialogMsg.hide();
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

<c:if test="${registroCandidatoForm.correo}">
	<c:set var="correoprev" value="${registroCandidatoForm.usuario}"/>
</c:if>
<c:if test="${!registroCandidatoForm.correo}">
	<c:set var="correoprev" value=""/>
</c:if>

<form name="registroCandidatoForm" id="registroCandidatoForm" method="post" action="registro.do" dojoType="dijit.form.Form">
	<input type="hidden" name="method" id="method" value="init" dojoType="dijit.form.TextBox"/> <!-- indica el metodo a invocar -->

	<div id="registro_bienvenida" class="bloque">
		<h2>¡Muy bien! Has creado tu acceso al portal. </h2>
		<p class="registro_datos">
		Fecha de alta: <fmt:formatDate pattern="dd/MM/yy" value="${fecha}"/><br/>
	   	Usuario: ${registroCandidatoForm.usuario}<br/>
	    Contraseña: ${registroCandidatoForm.contrasena}
	    </p>

		<p><strong>Imprime y guarda</strong> tu usuario y contraseña en un lugar seguro y memorizalos bien.</p>
		<p>
			<input class="boton_naranja" type="button" value="Imprimir" id="btnImprimir" name="btnImprimir" onclick="imprimir();" />
		</p>
		
		<div id="botonMail">
		<p>O <strong>envíalos al correo</strong> electrónico que usaste como Usuario</p>
		<p>
			<input id="btnEnvioMail" name="btnEnvioMail" class="boton_naranja" type="button" value="Enviar a mi correo electrónico"
				   onclick="enviarPorCorreo();" />
		</p>
		</div>

		<div id="controlesMail" style="display:none">
		<label for="correoElectronico">
			<p>O <strong>escribe tu correo electr&oacute;nico</strong> para enviarte los datos de tu cuenta</p>
		</label>
		<p>
			<input type="text" id="correoElectronico" name="correoElectronico" value="${correoprev}"
		           dojoType="dijit.form.ValidationTextBox" size="65" maxlength="65" trim="true"
		           oncopy="return false;" oncut="return false" onpaste="return false;"
		           regExp="${regexpmail}" invalidMessage="Correo electronico invalido, verificar captura."/>

			<input id="btnEnviar" name="btnEnviar" class="boton_naranja" type="button" value="Enviar" onclick="enviar();" />
		    
		</p>
		</div>
		
    	<div id="holderMensaje" class="holder_mensaje" style="display:none; text-align: center;">
       		<div class="posicion">
        		<p class="mensaje_error" id="errorMsgArea"></p>
			</div>
    	</div>
		
		<p class="important c_azul">Para terminar tu registro, contin&uacute;a con el llenado de tu perfil laboral.</p>
	  	<p>
	  		<input id="btnContinuar" name="btnContinuar" class="boton_azul" type="button" value="Continuar" onclick="continuar();"/>
	  	</p>
	</div>

</form>
