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
		dojo.xhrPost({url: 'registroEmpresa.do', form:'contrasenaForm', 
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
		dojo.byId('contrasenaForm').submit();	
	}

</script>
<style>
.boton_naranja {
    background: #4f6710;
	background: -moz-linear-gradient(top,  #bfd255 0%, #8eb92a 15%, #4f6710 97%, #9ecb2d 100%);
	background: -webkit-linear-gradient(top,  #bfd255 0%,#8eb92a 15%,#4f6710 97%,#9ecb2d 100%);
	background: linear-gradient(to bottom,  #bfd255 0%,#8eb92a 15%,#4f6710 97%,#9ecb2d 100%);
	filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#bfd255', endColorstr='#9ecb2d',GradientType=0 );
    border: 1px solid #4f6710;
    border-radius: 5px;
    color: #fff;
    padding: 5px 20px;
}
.alert {
	-webkit-box-shadow: 0 1px 3px rgba(0,0,0,0.35);
	-moz-box-shadow: 0 1px 3px rgba(0,0,0,0.35);
	box-shadow: 0 1px 3px rgba(0,0,0,0.35);
}
</style>
<noscript>Funciones de JavaScript desactivadas por navegador</noscript>

<form name="contrasenaForm" id="contrasenaForm" method="post" action="recuperaContrasena.do" dojoType="dijit.form.Form">
	<input type="hidden" name="method" id="method" value="init" dojoType="dijit.form.TextBox"/> <!-- indica el metodo a invocar -->
	<input type="hidden" name="usuario" id="usuario" value="${contrasenaForm.usuario}"/>
	
<div class="miEspacio formApp">
	<fieldset class="panel panel-PE">
		<div class="panel-heading">
			<legend class="panel-title">TE HEMOS ENVIADO UN ENLACE PARA RECUPERAR TU CONTRASEÑA</legend>
		</div>
		<div class="panel-body">
			<div class="col-md-5" style="float:none;margin:auto;text-align:center">
				<p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p>
				<div id="divDatosCandidato" class="alert alert-success">
					<p>&nbsp;</p>
					<p>
						<strong>Mensaje de Correo Electrónico Enviado a: <span 
						style="display:inline-block;background-color:#3c763d;color:#dff0d8;
						padding:0 5px;-webkit-border-radius:3px;-moz-border-radius:3px;
						border-radius: 5px;">${contrasenaForm.correoElectronico}</span>
					</p>
					<p>Favor de Revisar Tu Carpeta de Correos <strong>SPAM</strong></p>
				
				</div>
			</div>
			<div class="form_nav">
				<p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p>
				<input type="button" value="Cerrar" class="boton_naranja  bt_grande" onclick="javascript:gotoHome();"/>
			</div>
			
		</div>
	</fieldset>
	
</div>
</form>