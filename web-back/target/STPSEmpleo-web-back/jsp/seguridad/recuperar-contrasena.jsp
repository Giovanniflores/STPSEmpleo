<!-- OAM Octavio Alvarez Pagina Principal de La Forma Recuperar Contraseña -->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.Calendar"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String context = request.getContextPath() + "/";
%>
<%
	String remoteAddr = request.getRemoteAddr();
%>
<%
	String idInactivoAPeticionDelUsuario = (null != request.getSession()
			.getAttribute("idInactivoAPeticionDelUsuario")
					? request.getSession().getAttribute("idInactivoAPeticionDelUsuario").toString()
					: "");
	String idInactivoPorVigencia = (null != request.getSession().getAttribute("idInactivoPorVigencia")
			? request.getSession().getAttribute("idInactivoPorVigencia").toString()
			: "");
	String idInactivoPorAdmor = (null != request.getSession().getAttribute("idInactivoPorAdmor")
			? request.getSession().getAttribute("idInactivoPorAdmor").toString()
			: "");
	String usuarioInactivoAPeticionDelUsuario = (null != request.getSession()
			.getAttribute("UsuarioInactivoAPeticion")
					? request.getSession().getAttribute("UsuarioInactivoAPeticion").toString()
					: "");
	String usuarioInactivoPorVigencia = (null != request.getSession().getAttribute("usuarioInactivoPorVigencia")
			? request.getSession().getAttribute("usuarioInactivoPorVigencia").toString()
			: "");
%>
<c:set var="regexptelefono">^[+]?\d{7,10}$</c:set>
<c:set var="regexpmail">
^[a-zA-Z0-9_-]+(?:\.[a-zA-Z0-9_-]+)*@(?:[a-zA-Z0-9](?:[a-zA-Z0-9-_]*[a-zA-Z0-9-])?\.)+[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?$
</c:set>

<c:set var="regexplogin">
^[a-zA-ZáéíóúÁÉÍÓÚñÑ0-9]{8,12}$
</c:set>

<c:set var="regexprazonsocial">
^[A-Za-z\s\d\-.&,áéíóúÁÉÍÓÚñÑ']{3,150}$
</c:set>
<script type="text/javascript" src="js/helper/messageHelper.js"></script>
<script type="text/javascript"
	src="http://www.google.com/recaptcha/api/js/recaptcha_ajax.js"></script>
<script type="text/javascript">
	require(["dojo/cookie", "dojo/domReady!"]);
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

	dojo
			.addOnLoad(function() {
				// ... 
				if (${requestScope.urlvalido}) {
						message("URL Invalido o Caduco, favor de recuperar contraseña");
				} 
			});

	//OAM
	function recuperaContrasena() {
		if (validarCamposContrasena()) {
			validaCorreoElectronicoUnico();
		}
	}

	function enviarCorreo() {
		dojo.byId('method').value = 'mensajeContrasena';
		dojo.byId('contrasenaForm').submit();
	}

	function validaCorreoElectronicoUnico() {
		var res;
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

		if (!dijit.byId('respuestaUsuario').isValid()) {
			dojo.byId('respuestaUsuario').focus();
			displayErrorMsg(dijit.byId('respuestaUsuario'), dijit.byId(
					'respuestaUsuario').get("missingMessage"));
			return false;
		}
		bloqueandoPantalla();
		dojo
				.xhrPost({
					url : 'recuperaContrasena.do?method=sendUrl4Recover',
					form : 'contrasenaForm',
					load : function(data) {
						desbloqueoPantalla();
						res = dojo.fromJson(data);
						if ('exito' == res.type) {
							if ('unico' == res.message) {
								message('Correo electrónico no localizado, favor de verificar los datos.');
								return false;
							} else {
								confirmByEmail();
							}
						} else {
							message(res.message);
							dojo.byId('respuestaUsuario').focus();
							return false;
						}
					}
				});
	}
	
	function confirmByEmail() {
		dojo.byId('method').value = 'confirmByEmail';
		dojo.byId('contrasenaForm').submit();
	}

	//OAM
	function bloqueandoPantalla() {
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

	//OAM
	function bloqueandoPantallaCURP() {
		var html = '<h3>¡VERIFICANDO DATOS!</h3>'
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

	function validaCurpUnico() {
		var res;
		bloqueandoPantallaCURP();
		dojo.xhrGet({
			url : 'recuperaContrasena.do?method=validaCURPUnico',
			form : 'contrasenaForm',
			sync: true,
			timeout:180000,
			load : function(data) {
				desbloqueoPantalla();
				res = dojo.fromJson(data);
				if ('exito' == res.type) {
					doSubmitAjax("Candidato");
				}else if ('error' == res.type) {
					messageNotice(res.message);
				}
			}
		});
	}
	
	function validaEmpresaRFC() {
		var res;
		bloqueandoPantallaCURP();
		dojo.xhrGet({
			url : 'recuperaContrasena.do?method=validaEmpresa',
			form : 'contrasenaForm',
			load : function(data) {
				desbloqueoPantalla();
				res = dojo.fromJson(data);
				if ('exito' == res.type) {
					doSubmitAjax("Empresa");
				} else if ('error' == res.type) {
					dijit.byId('msjEmpresa').show();
				}
			}
		});
	}

	function recuperaUsuarioCandidato() {
		if (validarCamposUsuarioCandidato()) {
			if (!dijit.byId('respuestaUsuario').isValid()) {
				dojo.byId('respuestaUsuario').focus();
				displayErrorMsg(dijit.byId('respuestaUsuario'), dijit.byId('respuestaUsuario').get("missingMessage"));
				return false;
			}
			validaCurpUnico();
		}
	}

	function recuperaUsuarioEmpresa() {
		if (validarCamposUsuarioEmpresa()) {
			if (!dijit.byId('respuestaUsuario').isValid()) {
				dojo.byId('respuestaUsuario').focus();
				displayErrorMsg(dijit.byId('respuestaUsuario'), dijit.byId('respuestaUsuario').get("missingMessage"));
				return false;
			}
			validaEmpresaRFC();
		}
	}
	
		function doRegistrarCandidato(){
   			dijit.byId('msjCandidato').hide();
   			window.location.href='${context}registro.do?method=toIniciaRegistro';
   		}
   		
		function cerrarMsjCandidato(){
    		dijit.byId('msjCandidato').hide();
       }
       
       function doRegistrarEmpresa(){
   			dijit.byId('msjEmpresa').hide();
   			window.location.href='${context}registroEmpresa.do?method=toPreregistro';
   		}
   		
   		 function cerrarMsjEmpresa(){
    		dijit.byId('msjEmpresa').hide();
       }

	function validacapGoogle() {
		dojo
				.xhrPost({
					url : 'activacion.do?method=veriCaptchaGoogle&recaptcha_challenge_field='
							+ Recaptcha.get_challenge()
							+ '&recaptcha_response_field='
							+ Recaptcha.get_response(),
					load : function(data) {
						//alert(data);
						var res = dojo.fromJson(data);
						if ('err' == res.type) {
							//dijit.byId('code').focus();
							//displayErrorMsg(dijit.byId('code'), dijit.byId('code').get("invalidMessage"));
							//alert('La solucion al captcha no es correcta');
							dijit.byId('dialogErrorCaptcha').show();
							javascript: showRecaptcha('captcha2');
						} else {
							//alert('La solucion es correcta en el captcha');
							doSubmitAjax();
						}
					}
				});
	}

	function cambiarPregunta(){
		dojo.xhrGet({
			  url: 'recuperaContrasena.do?method=cambiaPregunta',
			  load: function(data) {
				  document.getElementById('captchaPregunta').innerHTML = '<span>*</span>' + data;
			  }
				  		
			});
	}

	function validaCaptcha() {
		alert('validaCapthca');
		alert(document.getElementById('response').value);
	}
	
	function confirmRespuesta(){
		if (dijit.byId('respuestaUsuario').value == '') {
			dojo.byId('respuestaUsuario').focus();
			displayErrorMsg(dijit.byId('respuestaUsuario'), "Debe indicar la respuesta.");
		    return;
		}
		
		if (!dijit.byId('respuestaUsuario').isValid()) {
			dojo.byId('respuestaUsuario').focus();
		    displayErrorMsg(dijit.byId('respuestaUsuario'), dijit.byId(
					'respuestaUsuario').get("missingMessage"));
			return;
		}
		return true;
	}

	function validarCamposContrasena() {
		if (dijit.byId('correoElectronico').value == '') {
			displayErrorMsg(dijit.byId('correoElectronico'),
					"Debe indicar la cuenta de correo electronico a donde se enviarán sus datos.");
			return;
		}

		if (!dijit.byId('correoElectronico').isValid()) {
			displayErrorMsg(dijit.byId('correoElectronico'), dijit.byId(
					'correoElectronico').get("invalidMessage"));
			return;
		}
		if(!confirmRespuesta()){
			return;
		}
		return true;
	}

	function validarCamposUsuarioCandidato() {
		if (dijit.byId('curp').value == '') {
			displayErrorMsg(dijit.byId('curp'), dijit.byId('curp').get(
					"missingMessage"));
			return;
		}

		if (!dijit.byId('curp').isValid()) {
			displayErrorMsg(dijit.byId('curp'), dijit.byId('curp').get(
					"invalidMessage"));
			return;
		}
		
		if (!dijit.byId('correoElectronicoCandidato').isValid()) {
			displayErrorMsg(dijit.byId('correoElectronicoCandidato'), dijit.byId(
					'correoElectronicoCandidato').get("invalidMessage"));
			return;
		}
		
		if (!dijit.byId('telefono').isValid()) {
			displayErrorMsg(dijit.byId('telefono'), dijit.byId('telefono').get("invalidMessage"));
					return;
		}
		return true;
	}

	function validarCamposUsuarioEmpresa() {
		if (dijit.byId('rfc').value == '') {
			displayErrorMsg(dijit.byId('rfc'),"Debe indicar RFC de la Empresa.");
			return;
		}
		if (!dijit.byId('rfc').isValid()) {
			displayErrorMsg(dijit.byId('rfc'), dijit.byId('rfc').get("invalidMessage"));
			return;
		}
		if (dijit.byId('codigoPostal').value == '') {
			displayErrorMsg(dijit.byId('codigoPostal'),"Debe indicar Codigo Postal de la Empresa.");
			return;
		}
		if (!dijit.byId('codigoPostal').isValid()) {
			displayErrorMsg(dijit.byId('codigoPostal'), dijit.byId('codigoPostal').get("invalidMessage"));
			return;
		}
		return true;
	}

	function changeSecureCodeImage() {
		var img = document.getElementById('imgseccode')
		if (img) {
			img.src = 'captcha?time=' + new Date();
			dijit.byId('code').set('value', '');
		}
	}

	function isValidCaptcha() {
		return true;
	}

	function doSubmitAjax(tipoUsuario) {
		if ('Candidato' == tipoUsuario) {
			dojo.byId('method').value = 'recuperaUsuarioCandidato';
			dojo.byId('contrasenaForm').submit();
		} else {
			dojo.byId('method').value = 'recuperaUsuarioEmpresa';
			dojo.byId('contrasenaForm').submit();
		}
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

	function showErrorMsg(errmsg) {
		dojo.byId('errorMsgArea').innerHTML = errmsg;
		dojo.byId('holderMensaje').style.display = 'block';
	}

	function cerrarError() {
		dojo.byId('holderMensaje').style.display = 'none';
	}

	function showRecaptcha(nombreDiv) {
		Recaptcha.create("6Le1ZeMSAAAAAJUZu1g9iswjIkiuVQ_k2XVtuTB3", nombreDiv,
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
					theme : 'white',
					lang : 'es',
					callback : Recaptcha.focus_response_field
				});
	}

	function muestraDatos() {
		var vurl = 'http://www.google.com/recaptcha/api/verify?'
				+ 'privatekey=6Le1ZeMSAAAAAAXSYw_mo65yIrRUBpQU4PjKNbIW&'
				+ 'remoteip=192.168.5.88' + '&challenge='
				+ Recaptcha.get_challenge() + '&response='
				+ Recaptcha.get_response();
		alert(vurl);
		dojo.xhrGet({
			url : vurl,
			form : 'myForm',
			load : function(data) {
				alert('data');
			}
		});
	}

	function llenaChallenge() {
		document.getElementById('challenge').value = Recaptcha.get_challenge();
		document.getElementById('response').value = Recaptcha.get_response();
	}
	
	function messageNotice(msg) {
		var html =
	    '<div class="msg_contain">' +
		'<p style="text-align: center;">'+ msg +'</p>'+
		'<p align="center" class="form_nav 2">'
		//+ '<button id="btnAceptar" text-align:center class="button" onclick="javascript:doRegistrarCandidato();">Registrarte de Nuevo</button>' + '</p>'
		+ '<p align="center">' + 'Para cualquier aclaración envía correo electrónico a: <strong>portal_empleo@stps.gob.mx</strong><br>' + '</p>'
		+ '<p align="center">' + 'Para una respuesta más oportuna, favor de anexar tu curp al correo<br>' + '</p>'
		+ '<p align="center" class="form_nav 2">'
		+ '<button id="btnAceptar" class="button" onclick="javascript:closeNotice();">Cerrar</button>' + '</p>'
	    +'</div>';
	
		dialogMsg = new dijit.Dialog({
	        title: 'Aviso',
	        content: html,
	        style: "width:300px;",
	        showTitle: false, draggable : false, closable : false,
	    });			
		dialogMsg.show();
	}
	
	function closeNotice() {
		dialogMsg.hide();
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

<!-- GOBmx -->
	<link rel="shortcut icon" type="image/x-icon" href="http://www.gob.mx/assets/favicon.ico">
    <link href="<%=request.getContextPath()%>/css/cssgobmx/main.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/css/cssgobmx/application.css" rel="stylesheet">
    <!-- GOBmx -->

<form name="contrasenaForm" id="contrasenaForm" method="post" action="recuperaContrasena.do" dojoType="dijit.form.Form">
	<input type="hidden" name="method" id="method" value="init" />
	<!-- indica el metodo a invocar -->
	<input type="hidden" name="dia" id="dia" value="" dojoType="dijit.form.TextBox" /> 
	<input type="hidden" name="mes" id="mes" value="" dojoType="dijit.form.TextBox" /> 
	<input type="hidden" name="anio" id="anio" value="" dojoType="dijit.form.TextBox" /> 
	<input type="hidden" name="urlvalido" id="urlvalido" value="${contrasenaForm.urlvalido}" />
	
	    <!-- GOBmx -->
<div class="row">
	<header>
	<nav class="navbar navbar-inverse navbar-fixed-top navbar">
		<div class="wrapper">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed" data-target="#navbarMainCollapse" data-toggle="collapse">
					<span class="sr-only">Interruptor de Navegación</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
				 <a class="gobmx" title="Ir a gob.mx" target="_blank" style="padding-left: 10px;" href="https://www.gob.mx/">
                <img src="<%=request.getContextPath()%>/css/cssgobmx/gobmxlogo.svg" alt="gob.mx"></a>
			</div>
			<div id="navbarMainCollapse" class="collapse navbar-collapse">
				<ul class="nav navbar-nav navbar-right">
					<li><a href="https://www.gob.mx/tramites" class="" target="_blank" title="Trámites">Trámites</a></li>
					<li><a href="https://www.gob.mx/gobierno" class="" target="_blank" title="Gobierno">Gobierno</a></li>
					<li><a href="https://www.gob.mx/participa" class="" target="_blank" title="Participación Ciudadana">Participa</a></li>
					<li><a href="http://datos.gob.mx/" target="_blank" title="Datos abiertos">Datos</a></li>
					<li><a href="https://www.gob.mx/busqueda?utf8=%E2%9C%93" target="_blank" title="Datos abiertos">
						<img alt="Búsqueda" width="20" height="20" class="optical-adjust-search" src="<%=request.getContextPath()%>/css/cssgobmx/search.svg">
					</a></li>
				</ul>
			</div>
		</div>
	</nav>
</header>
	</div>
	    <!-- GOBmx -->
	    
 <div class="clearfix"></div>
	
	
	
	<!-- RECUPERA USUARIO / CONTRASEÑA OAM -->
	
	
	
	
	<div class="col-sm-12">
		<div class="page-header">
			<h3>Recuperar Contraseña y Usuario / Correo Electrónico</h3>
		</div>
	</div>
	<div class="col-sm-12">
		<fieldset class="panel panel-PE">
			<div class="panel-heading">
				<legend class="panel-title">Recuperar Contraseña</legend>
			</div>
			<div class="panel-body">
				<div class="row">
					<div class="col-sm-6">
						<div class="form-group">
							<label for="usuario"><span>*</span> Escribe tu correo electrónico con el que te registraste</label> 
							<input type="text" id="correoElectronico" name="correoElectronico" class="form-control" value="${contrasenaForm.correoElectronico}" dojoType="dijit.form.ValidationTextBox" placeholder="usuario@correo.com" style="width: 100%;" size="65" maxlength="65" trim="true" oncopy="return false;" oncut="return false" onpaste="return false;" regExp="${regexpmail}" invalidMessage="Correo electronico invalido, verificar captura." />
						</div>
						<div class="form_nav text-left">
							<div class="form-group">
								<input type="button" id="btnEnviar" value="Recuperar contraseña" class="btnPE" onclick="recuperaContrasena();" />
							</div>
						</div>
					</div>
					<div class="col-sm-6" style="display: none">
						<div id="holderMensaje">
							<div id="errorMsgArea">
								<input id="btnCerrar" name="btnCerrar" type="button" value="Cerrar" onclick="cerrarError();" />
							</div>
						</div>
					</div>
				</div>
			</div>
		</fieldset>
	</div>
	<div class="clearfix"></div>
	<fieldset class="col-sm-12 panel-PE" style="margin-bottom: 20px">
		<div class="panel-heading">
			<legend class="panel-title">Recuperar Usuario / Correo Electrónico</legend>
		</div>
		<div class="col-md-12" style="margin-top: 20px">
			<div class="alert alert-warning" style="color:#000000;border-color:#D5C8AD !important">
				<label><span>*</span> Campos obligatorios</label><br /> 
				<label><span style="color:#5F7C12">*</span> Escribe al menos una de las opciones</label>
			</div>
		</div> 
		<div class="panel-body">
			<div class="row">
				<div class="col-md-6">
				<fieldset class="panel-PE" style="border:1px solid #ccc !important">
					<div class="panel-heading">
						<legend class="panel-title">CANDIDATO</legend>
					</div>
					<div class="panel-body">
						<div class="form-group">
							<label for="curp" id="lblcurp"><span style="font-weight: bold;">*</span> Escribe tu CURP</label> 
							<input id="curp" name="curp" class="form-control" placeholder="CURP" dojoType="dijit.form.ValidationTextBox" required="true" missingMessage="Es necesario que capture su CURP." regExp="[a-zA-Z]{4}\d{6}[hmHM][a-zA-Z]{2}[B-DF-HJ-NP-TV-Z|b-df-hj-np-tv-z]{3}[a-zA-Z0-9][0-9]" invalidMessage="CURP invalida, favor de verificar su captura." maxlength="18" uppercase="true" clearOnClose="true" trim="true" uppercase="true" style="width: 166px;" />
						</div>
						<div class="form-group">
							<label for="correoElectronicoCandidato"><span style="color: #5F7C12; font-weight: bold">*</span> Dirección de correo electrónico</label> 
							<input type="text" id="correoElectronicoCandidato" name="correoElectronicoCandidato" class="form-control" value="${contrasenaForm.correoElectronico}"
							dojoType="dijit.form.ValidationTextBox" placeholder="usuario@correo.com" width: 100%;" size="65" maxlength="65" trim="true" oncopy="return false;" oncut="return false" onpaste="return false;" regExp="${regexpmail}" invalidMessage="Correo electronico invalido, verificar captura." />
						</div>
						<div class="form-group">
							<label for="telefono"><span style="color: #5F7C12; font-weight: bold">*</span> Escribe tu teléfono</label> 
							<input type="text" name="telefono" id="telefono" placeholder="Teléfono" class="form-control" regExp="${regexptelefono}"
								value="${contrasenaForm.telefono}" dojoType="dijit.form.ValidationTextBox" size="20" invalidMessage="Número de Telefono Invalido" />
						</div> 
						<input type="button" id="btnEnviarCandidato" value="Aceptar" class="btnPE" onclick="recuperaUsuarioCandidato();" />
					</div>
				</fieldset>
				</div>
				<!-- RECUPERA USUARIO EMPRESA OAM -->
				<div class="col-md-6">
				<fieldset class="panel-PE" style="border:1px solid #ccc !important">
					<div class="panel-heading">
						<legend class="panel-title">EMPRESA</legend>
					</div>
					<div class="panel-body">
						<div class="form-group">
							<label for="rfc"><span style="font-weight: bold">*</span> Escribe tu RFC</label>
							<input id="rfc" name="rfc" maxlength="13" size="13" placeholder="RFC" class="form-control" dojoType="dijit.form.ValidationTextBox" required="true" trim="true" regExp="^[A-Za-z\s\áéíóúÁÉÍÓÚñÑ0-9/']{1,150}$" value="${contrasenaForm.rfc}" invalidMessage="Dato inválido" trim="true" />
							<p><i>Si no cuentas con RFC comunícate al teléfono 01 800 841 2020</i></p>
						</div>
						<div class="form-group">
							<label for="codigoPostal"><span style="font-weight: bold">*</span> Escribe tu código postal</label>
							<input type="text" name="codigoPostal" id="codigoPostal" class="form-control" placeholder="Código Postal" dojoType="dijit.form.ValidationTextBox" maxlength="5" style="width: 248px;" trim="true" required="true" missingMessage="Es necesario indicar el codigo postal." regExp="${regexpcp}" invalidMessage="Codigo incorrecto, solo se admiten caracteres numericos." value="${contrasenaForm.codigoPostal}" />
						</div>
						<input type="button" id="btnEnviarEmpresa" value="Aceptar" class="btnPE" onclick="recuperaUsuarioEmpresa();" />
					</div>
				</fieldset>
				</div>
			</div>
		</div>
	</fieldset>
	<fieldset class="panel panel-PE col-md-12">
		<div class="panel-heading">
			<legend class="panel-title">Responde a la siguiente pregunta</legend>
		</div>
		<div class="panel-body">
			<div class="row">
				<div class="col-md-9">
					<div id="divCaptcha" class="form-group">
						<label for="respuestaUsuario" id="captchaPregunta"><span>*</span>${contrasenaForm.pregunta}</label>
						<input type="text" name="respuestaUsuario" id="respuestaUsuario" size="40" maxlength="15" dojoType="dijit.form.ValidationTextBox" class="form-control" required="true" missingMessage="Es necesario proporcionar la respuesta a la pregunta" />
						<span class="help-block"><a href="javascript:cambiarPregunta()"> Cambiar pregunta</a></span>
						<html:messages id="errors">
							<span class="redFont Font80"><bean:write name="errors" /></span>
							<br />
						</html:messages>
					</div>
				</div>
			</div>
		</div>
	</fieldset>
	<div dojoType="dijit.Dialog" id="msjCandidato" title="Aviso" draggable="false">
		<div class="msg_contain">
			<p align="center" class="form_nav 2">
				Los Datos son Incorrectos. Para ingresar al portal,<br>
				<button id="btnAceptar" text-align:center class="button" onclick="javascript:doRegistrarCandidato();">Registrarte de Nuevo</button>
			</p>
			<p align="center" class="form_nav 2">
				Si lo deseas comunícate, al teléfono <strong>01 800 841 2020</strong><br>
				<button id="btnAceptar" class="button" onclick="javascript:cerrarMsjCandidato();">Cerrar</button>
			</p>
		</div>
	</div>
	<div dojoType="dijit.Dialog" id="msjEmpresa" title="Aviso" draggable="false">
		<div class="msg_contain">
			<p align="center" class="form_nav 2">
				Los Datos son Incorrectos. Para ingresar al portal,<br>
				<button id="btnAceptar" class="button" onclick="javascript:doRegistrarEmpresa();">Registrarte de Nuevo</button>
			</p>
			<p align="center" class="form_nav 2">
				Si lo deseas comunícate, al teléfono <strong>01 800 841 2020</strong><br>
				<button id="btnAceptar" class="button" onclick="javascript:cerrarMsjEmpresa();">Cerrar</button>
			</p>
		</div>
	</div>
	<html:messages id="errors">
		<span class="redFont Font80"><bean:write name="errors" /></span>
		<br />
	</html:messages>
</form>


<div class="clearfix"></div>


<!-- GOBmx -->
  	
  				      	 <div class="row"> 

<footer class="main-footer">
  <div class="list-info">
    <div class="wrapper">
      <div class="row">
        <div class="col-sm-4">
          <h2>Enlaces</h2>
          <ul>
            <li><a href="http://reformas.gob.mx/" target="_blank" class="sendEstFooter" title="Enlace abre en ventana nueva">Reformas</a></li>
            <li><a href="https://www.gob.mx/publicaciones" target="_blank" title="Enlace abre en ventana nueva">Publicaciones Oficiales</a></li>
            <li><a href="http://portaltransparencia.gob.mx/" target="_blank" class="sendEstFooter" title="Enlace abre en ventana nueva">Portal de Obligaciones de Transparencia</a></li>
            <li><a href="https://www.infomex.org.mx/gobiernofederal/home.action" target="_blank" class="sendEstFooter" title="Enlace abre en ventana nueva">Sistema Infomex</a></li>
            <li><a href="http://inicio.ifai.org.mx/SitePages/ifai.aspx" target="_blank" class="sendEstFooter" title="Enlace abre en ventana nueva">INAI</a></li>
          </ul>
        </div>
        <div class="col-sm-4">
          <h2>¿Qué es gob.mx?</h2>
          <p>Es el portal único de trámites, información y participación ciudadana. <a href="https://www.gob.mx/que-es-gobmx" target="_blank">Leer más</a></p>
          <ul>
            <li><a href="https://www.gob.mx/en/index" title="Versión en inglés del sitio web">English</a></li>
            <li><a href="https://www.gob.mx/temas" target="_blank">Temas</a></li>
            <li><a href="https://www.gob.mx/accesibilidad" target="_blank">Declaración de Accesibilidad</a></li>
            <li><a href="https://www.gob.mx/privacidadintegral" target="_blank">Aviso de privacidad integral</a></li>
            <li><a href="https://www.gob.mx/privacidadsimplificado" target="_blank">Aviso de privacidad simplificado</a></li>
            <li><a href="https://www.gob.mx/terminos" target="_blank">Términos y Condiciones</a></li>
            <li><a href="https://www.gob.mx/terminos#medidas-seguridad-informacion" target="_blank">Política de seguridad</a></li>
            <li><a href="http://www.ordenjuridico.gob.mx/" target="_blank" class="sendEstFooter" title="Enlace abre en ventana nueva">Marco Jurídico</a></li>
            <li><a href="https://www.gob.mx/sitemap" target="_blank">Mapa de sitio</a></li>
          </ul>
        </div>
        <div class="col-sm-4">
          <h2>Contacto</h2>
          <p>Mesa de ayuda: dudas e información<br>gobmx@funcionpublica.gob.mx</p>
          <p><a href="https://www.gob.mx/tramites/ficha/presentacion-de-quejas-y-denuncias-en-la-sfp/SFP54" target="_blank">Denuncia contra servidores públicos</a></p>
        </div>
      </div>
      <div class="row">
      <div class="col-sm-4">
          <form id="subscribe" action="http://www.gobernacion.gob.mx/work/models/header/subscribe" accept-charset="UTF-8" data-remote="true" method="post"><input name="utf8" type="hidden" value="✓">
            <h2>Mantente informado. Suscríbete.</h2>
            <div class="form-group-icon">
              <label class="sr-only" for="email">Suscribirse</label>
              <input id="email" type="text" name="email" class="form-control" placeholder="Ingresa tu correo electrónico" maxlength="255">
              <button type="submit" class="blue-button-footer btn registerMail" title="Suscribirse">
                <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
              </button>
            </div>
            <div id="responseEmail" aria-live="polite"></div>
          </form>
      </div>
        <div class="col-sm-4 col-sm-offset-4">
          <h2>Síguenos en</h2>
          <ul class="list-inline">
            <li>
            <a href="https://www.facebook.com/gobmx" target="_blank" class="sendEstFooterRs share-info" red="Facebook" title="enlace a facebook abre en una nueva ventana">
            <img alt="Facebook" src="<%=request.getContextPath()%>/css/cssgobmx/facebook_footer.png">
            </a>
            </li>
            <li>
            <a href="https://twitter.com/gobmx" target="_blank" class="sendEstFooterRs share-info" red="Twitter" title="Enlace a twitter abre en una nueva ventana">
            <img alt="Twitter" src="<%=request.getContextPath()%>/css/cssgobmx/twitter_footer.png">
            </a>
            </li>
          </ul>
        </div>
      </div>
    </div>
  </div>
  <div class="wrapper">
    <div class="row">
      <div class="col-sm-4">
         <a class="gobmx" title="Ir a gob.mx" target="_blank" style="padding-left: 10px;" href="https://www.gob.mx/">
                <img src="<%=request.getContextPath()%>/css/cssgobmx/gobmxlogo.svg" alt="gob.mx"></a>
      </div>
      <div class="col-sm-4 col-sm-offset-4">
      </div>
    </div>
  </div>
</footer>
 </div> 
 
 <!-- GOBmx -->

