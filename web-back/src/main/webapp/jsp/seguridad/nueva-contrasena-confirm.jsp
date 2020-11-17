<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<jsp:useBean id="fecha" scope="page" class="java.util.Date" />

<fmt:setBundle basename="MessageResources" var="portalbundle" />

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

	function enviarCorreo() {
		if (dijit.byId('correoElectronico').value == '') {
			displayErrorMsg(dijit.byId('correoElectronico'),
					"Debe indicar la cuenta de correo electronico a donde se enviarán sus datos.");
			return false;
		}

		if (!dijit.byId('correoElectronico').isValid()) {
			displayErrorMsg(dijit.byId('correoElectronico'), dijit.byId(
					'correoElectronico').get("invalidMessage"));
			return false;
		}

		//NOTIFICACION EMPRESA
		if (dojo.byId('idTipoUsuario').value == 2) {
			dojo.byId('method').value = 'notificacionEmpresa';
			bloquedoPantalla();
			dojo
					.xhrPost({
						url : 'nuevaContrasena.do',
						form : 'nuevaContrasenaForm',
						load : function(data) {
							desbloqueoPantalla();
							var res = dojo.fromJson(data); // Datos del servidor
							if ('exito' == res.type) {
								message(res.message);
								dojo.byId('botonMail').style.display = 'none';
								dojo.byId('controlesMail').style.display = 'none';
								dijit.byId('btnEnviar').disabled = false; // una vez enviado se evita que vuelvan a enviar
							} else if ('error' == res.type) {
								message('No se pudo enviar la notificación, favor de intentar de nuevo ó tiene la opción de imprimirlo.');
							} else if ('errormaildate' == res.type) {
								message('No se pudo enviar la notificación, favor de verificar que haya capturado los datos necesarios.');
							} else {
								message('No se pudo enviar la notificación, favor de intentar de nuevo ó tiene la opción de imprimirlo.');
							}
						}
					});
		}

		//NOTIFICACION CANDIDATO
		if (dojo.byId('idTipoUsuario').value == 3) {
			dojo.byId('method').value = 'notificaCandidato';
			bloquedoPantalla();
			dojo
					.xhrPost({
						url : 'nuevaContrasena.do',
						form : 'nuevaContrasenaForm',
						load : function(data) {
							desbloqueoPantalla();
							var res = dojo.fromJson(data); // Datos del servidor
							if ('exito' == res.type) {
								message(res.message);
								dojo.byId('botonMail').style.display = 'none';
								dojo.byId('controlesMail').style.display = 'none';
								dijit.byId('btnEnviar').disabled = false; // una vez enviado se evita que vuelvan a enviar
							} else if ('error' == res.type) {
								message('No se pudo enviar la notificación, favor de intentar de nuevo ó tiene la opción de imprimirlo.');
							} else if ('errormaildate' == res.type) {
								message('No se pudo enviar la notificación, favor de verificar que haya capturado los datos necesarios.');
							} else {
								message('No se pudo enviar la notificación, favor de intentar de nuevo ó tiene la opción de imprimirlo.');
							}
						}
					});
		}
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

	function imprimir() {
		window.print();
	}

	function gotoHome() {
		dojo.byId('method').value='toHome';
		dojo.byId('nuevaContrasenaForm').submit();
		
	}

	function gotoLogin() {
		dojo.byId('method').value = 'toLogin';
		dojo.byId('nuevaContrasenaForm').submit();
	}
</script>
<noscript>Funciones de JavaScript desactivadas por navegador</noscript>

<form name="nuevaContrasenaForm" id="nuevaContrasenaForm" method="post"
	action="nuevaContrasena.do" dojoType="dijit.form.Form">
	<input type="hidden" name="method" id="method" value="init"
		dojoType="dijit.form.TextBox" />
	<!-- indica el metodo a invocar -->
	<input type="hidden" name="usuario" id="usuario" value="${nuevaContrasenaForm.usuario}" /> 
 	<input type="hidden" name="idUsuario" id="idUsuario" value="${nuevaContrasenaForm.idUsuario}" /> 
 	<input type="hidden" name="contrasena" id="contrasena" value="${nuevaContrasenaForm.contrasena}" />
	<input type="hidden" name="idCandidato" id="idCandidato" value="${nuevaContrasenaForm.idCandidato}" /> 
	<input type="hidden" name="idEmpresa" id="idEmpresa" value="${nuevaContrasenaForm.idEmpresa}" /> 
	<input type="hidden" name="idTipoUsuario" id="idTipoUsuario" value="${nuevaContrasenaForm.idTipoUsuario}" />

	<div class="row">
		<div class="col-sm-12">
			<div class="page-header">
				<h3>Tu contraseña</h3>
			</div>
		</div>
		<div class="col-sm-12">
			<fieldset class="panel panel-PE">
				<div class="panel-heading">
					<legend class="panel-title">${nuevaContrasenaForm.nombre}</legend>
				</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-12">
							<p>
								<b>Tu contraseña es:</b>
							</p>
							<textarea class="panel-body" style="text-align: center; resize: none; border:0" readonly rows=1 cols=12>${nuevaContrasenaForm.contrasena}</textarea>
							<p>
								<strong>Imprime y guarda</strong> tu contraseña en un lugar
								seguro y memorizala bien
							</p>
							<div class="col-sm-6 hidden-xs">
								<div class="form-group">
									<input class="btnPE form-control" type="button"
										value="Imprimir" id="btnImprimir" name="btnImprimir"
										onclick="imprimir();" />
								</div>
							</div>
							<div id="botonMail" class="col-sm-6">
								<div class="form-group">
									<p div="idLeyendaMail" style="display: none">
										O <strong>envíala a tu correo</strong> electrónico que usaste
										como usuario
									</p>
									<input type="button" id="btnEnvioMail" name="btnEnvioMail"
										class="btnPE form-control"
										value="Enviar a mi correo electr&oacute;nico"
										onclick="enviarPorCorreo();" />
								</div>
							</div>
							<div id="controlesMail" style="display: none">
								<div class="col-xs-6 col-sm-3">
									<div class="form-group">
										<input type="text" id="correoElectronico"
											name="correoElectronico"
											value="${nuevaContrasenaForm.correoElectronico}"
											dojoType="dijit.form.ValidationTextBox" size="65"
											maxlength="65" trim="true"
											placeholder="Dirección de correo electrónico"
											style="width: 100%;" size="65" maxlength="65" trim="true"
											oncopy="return false;" oncut="return false"
											onpaste="return false;" class="form-control"
											regExp="${regexpmail}"
											invalidMessage="Correo electronico invalido, verificar captura." />
									</div>
								</div>
								<div class="col-xs-6 col-sm-3">
									<div class="form-group">
										<input id="btnEnviar" name="btnEnviar"
											class="btnPE form-control" type="button" value="Enviar"
											onclick="javascript:enviarCorreo();" />
									</div>
								</div>
							</div>
							<div class="col-sm-6">
								<div class="form-group">
									<input type="button" value="Iniciar sesión"
										class="btnPE form-control" onclick="javascript:gotoLogin();" />
								</div>
							</div>
							<div class="col-sm-6">
								<div class="form-group">
									<input type="button" value="Cerrar" class="btnPE form-control"
										onclick="javascript:gotoHome();" />
								</div>
							</div>
						</div>
					</div>
				</div>
			</fieldset>
		</div>
	</div>
</form>