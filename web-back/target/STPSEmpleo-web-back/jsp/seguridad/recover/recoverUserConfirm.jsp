<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="mx.gob.stps.portal.core.candidate.vo.CandidatoVo" %>
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

	var underlay;
	
	function sendMail() {
		var res;
		if (dijit.byId('correo').value != '') {
			if (dijit.byId('correo').value == dijit.byId('correoElectronico').value) {
				blockScreenCURP();
				dojo.xhrPost({
					url : 'recuperaContrasena.do?method=sendMail',
					form : 'contrasenaForm',
					load : function(data) {
						unblockScreenCURP();
						res = dojo.fromJson(data);
						if ('exito' == res.type) {
							document.getElementById("divDatosCandidato").style.display='block';
						  	dojo.byId('btnAceptar').style.display = 'none';
						}else {
							//dijit.byId('msjUsuario').show();
							message(res.message);
						}
					}
				})
			}else {
				dijit.byId('msjUsuario').show();
   			}
		}else {
			dijit.byId('correo').focus();
			message('Favor de completar los caracteres que están con asterisco *');
		}
	}
	
	function doRegistrar(idtipoUsuario){
			if(dijit.byId('idtipoUsuario').value == '3'){
				dijit.byId('msjUsuario').hide();
				window.location.href='${context}registro.do?method=toIniciaRegistro';
			}
			if(dijit.byId('idtipoUsuario').value == '2'){
				dijit.byId('msjUsuario').hide();
   				window.location.href='${context}registroEmpresa.do?method=toPreregistro';
			}
	}
	
	function cerrarMsjUsuario(){
		dijit.byId('msjUsuario').hide();
	}

	function blockScreenCURP() {
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

	function unblockScreenCURP() {
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

	function closeWindow() {
		dialogMsg.hide();
	}

	function imprimir() {
		window.print();
	}

	function gotoHome() {
		dojo.byId('method').value = 'toHome';
		dojo.byId('contrasenaForm').submit();
	}
</script>
<style>
.button {
    background-color: #666;
    border: 1px solid #888787;
    color: #fff !important;
    cursor: pointer;
    padding: 5px 15px !important;
    -webkit-border-radius: 5px;
	-moz-border-radius: 5px;
	border-radius: 5px;
	-webkit-box-shadow: 0 2px 2px rgba(0,0,0,0.4);
	-moz-box-shadow: 0 2px 2px rgba(0,0,0,0.4);
	box-shadow: 0 2px 2px rgba(0,0,0,0.4);
}
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
#btnAceptar {
	margin-right: 40px;
}
.dijitDialogTitleBar .dijitDialogCloseIcon {
	display: none;
}
</style>

<noscript>Funciones de JavaScript desactivadas por navegador</noscript>

<form name="contrasenaForm" id="contrasenaForm" method="post"
	action="recuperaContrasena.do" dojoType="dijit.form.Form">
	<input type="hidden" name="method" id="method" value="init"
		dojoType="dijit.form.TextBox" />
	<!-- indica el metodo a invocar -->
	<input type="hidden" name="correoElectronico" id="correoElectronico" value="${cand.correoElectronico}" dojoType="dijit.form.ValidationTextBox"/> 
	<input type="hidden" name="usuario" id="usuario" value="${contrasenaForm.usuario}" dojoType="dijit.form.ValidationTextBox"/> 
	<input type="hidden" name="idtipoUsuario" id="idtipoUsuario" value="${contrasenaForm.idTipoUsuario}" dojoType="dijit.form.ValidationTextBox"/>

	<div class="miEspacio formApp">
		<fieldset class="panel panel-PE">
		<div class="panel-heading">
			<legend class="panel-title">RECUPERAR USUARIO</legend>
		</div>
		<div class="panel-body">
		<div class="col-md-5" style="float:none;margin:auto;text-align:center">
			<p>&nbsp;</p><p>&nbsp;</p>
			<div class="alert alert-info">
				<p>&nbsp;</p>
				<p>Favor de completar los caracteres que están con asterisco <strong class="c_naranja">*</strong></p>
				<p>
					Usuario recuperado: <br/>
					<strong class="c_naranja">${contrasenaForm.correocode}</strong>
				</p>
			</div>
			<div class="row">
				<div class="col-md-12">
					<input type="text" name="correo" id="correo" size="40" maxlength="65" dojoType="dijit.form.ValidationTextBox" class="form-control"
						required="true" regExp="${regexpmail}" invalidMessage="Correo electronico invalido, verificar captura." missingMessage="Favor de completar los caracteres que están con asterisco *."/>
					<p>&nbsp;</p>
				</div>
			</div>
			<div class="clearfix"></div>
			
			<div id="divDatosCandidato" class="alert alert-success" style="display: none">
				<p>
					<em><strong>Datos de recuperación de usuario Candidato</strong></em>
				</p>
				<p>
					<em><strong>Nombre: </strong>${contrasenaForm.nombre}<br />
					<strong>CURP: </strong>${contrasenaForm.curp}<br /><br />
					<strong>Usuario: </strong><span 
					style="display:inline-block;background-color:#3c763d;color:#dff0d8;
					padding:0 5px;-webkit-border-radius:3px;-moz-border-radius:3px;
					border-radius: 5px;">${contrasenaForm.usuario}</span></em><br />
					<strong>Mensaje de Correo Electrónico Enviado a: </strong>
					<span style="display:inline-block;background-color:#3c763d;color:#dff0d8;
						padding:0 5px;-webkit-border-radius:3px;-moz-border-radius:3px;
						border-radius: 5px;">${_RECOVER}</span>
				</p>
				<p>Favor de Revisar Tu Carpeta de Correos SPAM</p>
			</div><p>&nbsp;</p>
			<div id="divDatosEmpresa" class="alert alert-success" style="display: none">
				<p>
					<em><strong>Datos de recuperación de usuario Empresa</strong></em>
				</p>
				<p>
					<em><strong>Razón Social: </strong>${contrasenaForm.nombre}<br />
					<strong>RFC: </strong>${contrasenaForm.rfc}<br />
					<strong>Usuario: </strong><span 
					style="display:inline-block;background-color:#3c763d;color:#dff0d8;
					padding:0 5px;-webkit-border-radius:3px;-moz-border-radius:3px;
					border-radius: 5px;">${contrasenaForm.usuario}</span></em><br />
				</p>
			</div>
			
			
			<div class="form_nav"><p>&nbsp;</p>
				<input type="button" id="btnAceptar" value="Aceptar" class="boton_naranja  bt_grande" onclick="javascript:sendMail();" /> <input type="button"
					value="Cerrar" class="boton_naranja  bt_grande" onclick="javascript:gotoHome();" />
			</div>
			
			</div>
			</div>
			<div class="clearfix"></div>
		</fieldset>
		
		<div dojoType="dijit.Dialog" id="msjUsuario" title="Aviso"
		draggable="false">
		<div class="msg_contain">
			<p align="center">
				Los Datos son Incorrectos. Para ingresar al portal,<br>
			<p align="center" class="form_nav 2">
				<button id="btnAceptar" text-align:center class="button"
					onclick="javascript:doRegistrar(${contrasenaForm.idTipoUsuario});">Registrarte de Nuevo</button>
			</p>
			<p align="center">
				Si lo deseas comunícate, al teléfono <strong>01 800 841
					2020</strong><br>
			<p align="center" class="form_nav 2">
				<button id="btnAceptar" class="button"
					onclick="javascript:cerrarMsjUsuario();">Cerrar</button>
			</p>
		</div>
	</div>
		

	</div>


</form>
