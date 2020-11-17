<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<jsp:useBean id="fecha" scope="page" class="java.util.Date" />

<fmt:setBundle basename="MessageResources" var="portalbundle" />

<c:set var="regexppwd">^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{7,12}[^\s]$</c:set>
					   
<c:set var="regexpmail">
^[a-zA-Z0-9_-]+(?:\.[a-zA-Z0-9_-]+)*@(?:[a-zA-Z0-9](?:[a-zA-Z0-9-_]*[a-zA-Z0-9-])?\.)+[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?$
</c:set>

<%
	String context = request.getContextPath() + "/";
%>

<style type="text/css">
#dialogMsg_underlay {
	background-color: gray;
}

#underlay_underlay {
	background-color: gray;
}
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

	function enviarPorCorreo() {
		dojo.byId('botonMail').style.display = 'none';
		dojo.byId('controlesMail').style.display = 'block';
		dojo.byId('correoElectronico').focus();
	}
	
	function PasswordValid() {
		if (confirmPassword()) {
			if(/\s/.test(dijit.byId('contrasena').value)){
				message('La contraseña debe conformarse entre 8 y 12 caracteres, con al menos una mayúscula, una minúscula y un número, no debe contener espacios en blanco.');
				dijit.byId('contrasena').focus();
    		 	return false;
			}
			if(!checkPassword(dijit.byId('contrasena').value)) {
				message('La contraseña debe conformarse entre 8 y 12 caracteres, con al menos una mayúscula, una minúscula y un número, no debe contener espacios en blanco.');
				dijit.byId('contrasena').focus();
				return false;
			} else {
				return true;
			}
		} else {
			message('Las Contraseñas deben coincidir');
			return false;
		}
	}
	
	function confirmPassword(){
		if (dijit.byId('contrasena').value == dijit.byId('confirmpasswd').value) {
			return true;
		}else{
			return false;
		}
	}

	function doSubmitCancel() {
		dojo.byId('method').value = 'toHome';
		dojo.byId('nuevaContrasenaForm').submit();
	}
	
	function checkPassword(str)
  	{
   		var re = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{7,12}[^\s]$/;
    	return re.test(str);
  	}

	var underlay;

	function bloquedoPantalla() {
		var html = '<h3>¡Se estan enviando sus datos por correo!</h3>'
				+ '<p><img style="text-align: center;" src="images/ajax-loader.gif"/></p>';

		underlay = new dijit.Dialog({
			title : 'Mensaje',
			content : html,
			style : "width: auto",
			draggable : false,
			closable : false,
			disableCloseButton : true
		});
		underlay.closeButtonNode.style.display = 'none';
		underlay.show();
	}
	
	function bloquedoPantallaPass() {
		var html = '<h3>¡Guardando Contraseña!</h3>'
				+ '<p><img style="text-align: center;" src="images/ajax-loader.gif"/></p>';

		underlay = new dijit.Dialog({
			title : 'Mensaje',
			content : html,
			style : "width: auto",
			draggable : false,
			closable : false,
			disableCloseButton : true
		});
		underlay.closeButtonNode.style.display = 'none';
		underlay.show();
	}

	function desbloqueoPantalla() {
		underlay.hide();
	}

	function displayErrorMsg(textBox, errmsg) {
		var originalValidator = textBox.validator;
		textBox.validator = function() {
			return false;
		};
		textBox.validate();
		textBox.validator = originalValidator;

		dijit.showTooltip(
		//textBox.get("invalidMessage"),
		errmsg, textBox.domNode, textBox.get("tooltipPosition"), !textBox
				.isLeftToRight());
	}

	function Guardar() {
			if (PasswordValid()) {
				dojo.byId('method').value = 'Guardar';
				bloquedoPantallaPass();
				dojo.xhrPost({
					url : 'nuevaContrasena.do',
					form : 'nuevaContrasenaForm',
					load : function(data) {
						desbloqueoPantalla();
						var res = dojo.fromJson(data); // Datos del servidor
						if ('exito' == res.type) {
							message(res.message);
							dojo.byId('method').value = 'Aceptar';
							dojo.byId('nuevaContrasenaForm').submit();
						} else if ('error' == res.type) {
							message(res.message);
						}
					}
				});
			} 
		}
	

	function gotoHome() {
		dojo.byId('method').value = 'toHome';
		dojo.byId('nuevaContrasenaForm').submit();
	}
</script>
<noscript>Funciones de JavaScript desactivadas por navegador</noscript>

<form name="nuevaContrasenaForm" id="nuevaContrasenaForm" method="post"
	action="nuevaContrasena.do" dojoType="dijit.form.Form">
	<input type="hidden" name="method" id="method" value="init"
		dojoType="dijit.form.TextBox" />
	<!-- indica el metodo a invocar -->
	<input type="hidden" name="usuario" id="usuario"
		value="${nuevaContrasenaForm.usuario}" /> <input type="hidden"
		name="idUsuario" id="idUsuario"
		value="${nuevaContrasenaForm.idUsuario}" /> <input type="hidden"
		name="idCandidato" id="idCandidato"
		value="${nuevaContrasenaForm.idCandidato}" /> <input type="hidden"
		name="idEmpresa" id="idEmpresa"
		value="${nuevaContrasenaForm.idEmpresa}" /> <input type="hidden"
		name="idTipoUsuario" id="idTipoUsuario"
		value="${nuevaContrasenaForm.idTipoUsuario}" />

	<div class="row">
		<div class="col-sm-12">
			<fieldset class="panel panel-PE">
				<div class="panel-heading">
					<legend class="panel-title">NUEVA CONTRASEÑA</legend>
				</div>
				</br>
				<p>
					Favor de cambiar contraseña <strong>${nuevaContrasenaForm.nombre}</strong>
				</p>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-12">
							<div class="grid1_3 margin_top_10">
								<label for="password">Cambiar contraseña</label> <input
									type="password" dojoType="dijit.form.ValidationTextBox"
									name="contrasena" id="contrasena" maxlength="12"
									intermediateChanges="true" 
									regExp="${regexppwd}"
									invalidMessage="La contraseña debe conformarse entre 8 y 12 caracteres, con al menos una mayúscula, una minúscula y un número, no debe contener espacios en blanco.">
							</div>
							</br>
							<div class="grid1_3 margin_top_10">
								<label for="confirmpasswd">Confirmar contraseña</label> <input
									type="password" dojoType="dijit.form.ValidationTextBox"
									name="confirmpasswd" id="confirmpasswd" maxlength="12"
									validator="confirmPassword" intermediateChanges="false"
									invalidMessage="La confirmación debe ser igual a la contraseña">
							</div>
							</br>
						</div>
					</div>
				</div>
		</div>
		</fieldset>
	</div>
	<div class="form_nav">
		<input type="button" id="btnActualizar" name="btnActualizar"
			class="boton_naranja" onclick="javascript:Guardar();" value="Guardar">
		<input type="button" class="boton_naranja"
			onclick="javascript:gotoHome();" value="Cancelar">
	</div>
</form>