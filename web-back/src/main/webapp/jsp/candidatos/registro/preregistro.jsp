<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setBundle basename="portal-web" var="messages"/>

<c:set var="regexpmail">^[a-zA-Z0-9_-]+(?:\.[a-zA-Z0-9_-]+)*@(?:[a-zA-Z0-9](?:[a-zA-Z0-9-_]*[a-zA-Z0-9-])?\.)+[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?$</c:set>
<c:set var="regexppwd">^[a-zA-Z0-9]{8,12}</c:set>
<c:set var="regexplogin">^[a-zA-Z0-9-]{8,12}$</c:set>


<c:set var="pwdaux" value="xxxxxxxxxx"/>

<%String context = request.getContextPath() +"/";%>

<style type="text/css">
	#dialogMsg_underlay { background-color:gray; }
</style>
<script type="text/javascript" src="js/registro/validateServiceAjax.js"></script>
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
		//dijit.byId('usuario').focus(); 
	});

	function doSubmit(method){
		dojo.byId('method').value=method;
		dojo.byId('registroCandidatoForm').submit();
	}
	
	var ppcSdSolicitud = ("${registroCandidatoForm.ppcSdSolicitud}" == "true" ? true : false);	
	
	function registrate(){		

		if ('${pwdaux}' == dojo.byId('contrasena').value){
			dojo.byId('contrasena').value='';
		}

		if ('${pwdaux}' == dojo.byId('confirmacion').value){
			dojo.byId('confirmacion').value='';
		}
		// En validate se valida los campos y los valores (el captcha no se valida)
		if (validate()==true){

			//validate si el usuario es unico
			if (validaUsuarioUnico()==false){
				
				// Si es un registro al PE y al PPC
				if (ppcSdSolicitud){	
					dijit.byId('dialogUsuarioNoDisponible').show();
					
				// Si es un registro al PE pero no al PPC
				} else {

					if (dojo.byId('esCorreo').value!=1){
						var textBox = dijit.byId("usuario");
						displayErrorMsg(textBox, 'El nombre de Usuario ya se encuentra registrado, favor de proporcionar uno distinto.');

					} else if (dojo.byId('esCorreo').value==1) {

						var mensajelogin =
							"<strong>El correo electrónico ya está registrado.</strong><br/>"+
							"Para ingresar al portal, puedes:<br/>"+
							"<input id='btnRecuperaPsw' name='btnRecuperaPsw' class='boton_naranja' type='button' value='Recuperar tu contraseña' onclick='recuperaPsw();'/>"+
							"<br/>"+
							"Si lo deseas comunícate al teléfono <strong><fmt:message key='portal.contacto.telefono' bundle='${messages}'/></strong><br/>"+
							"<input id='btnCerrar' name='btnCerrar' class='boton_naranja' type='button' value='Cerrar' onclick='cerrarError();'/>";
							
						showErrorMsg(mensajelogin);
					}
				}

				return;
			}

			doSubmit('toDatosPersonales');			
		}
	}
	
	function validate(){
				
		if (!dijit.byId('usuario').isValid()){
			dojo.byId('usuario').focus();
			return false;
		}

		if (!dijit.byId('contrasena').isValid()){
			dojo.byId('contrasena').focus();
			dojo.byId('contrasena').blur();
			return false;
		}

		if (!dijit.byId('confirmacion').isValid()){
			dojo.byId('confirmacion').focus();
			dojo.byId('confirmacion').blur();
			return false;
		}
		//validar si los dos claves son iguales
		if (!validaIgualdad()){
			return false;
		}
		//Se valida si el usuario es Valido correo o nombre
		if (!validarUsuario()){
			return false;	
		}

		return true;
	}
	
	function validaIgualdad(){
		var valido = true;
		
		if (dijit.byId('contrasena') && dijit.byId('confirmacion')){

			if (dijit.byId('contrasena').get('value') != dijit.byId('confirmacion').get('value')){
				valido = false;

				var errmsg = 'La confirmación de la contraseña no coincide con la contraseña capturada. Favor de revisar los datos proporcionados.';

				var textBox = dijit.byId("confirmacion");
				displayErrorMsg(textBox, errmsg);
			}
		}

		return valido;
	}
	//Se valida si el usuario o correo es unico
	function validarUsuario() {
		var valido = true;
		
		var errmsg = '';
		
		var login = dijit.byId('usuario').get('value');
		
		if (login){
			 if (login.indexOf("@") !=-1) {
				var regExp = /${regexpmail}/;
				errmsg = 'Correo electrónico inválido, favor de verificar la captura.';
				
				valido = regExp.test(login);
				
				dojo.byId('esCorreo').value=1;
			 } else {
				var regExp = /${regexplogin}/;
				errmsg = 'Nombre de usuario inválido, debe contar entre 8 y 12 caracteres, no se permiten acentos, caracteres especiales o espacio en blanco, ejemplos: "$,%,&,?,#,!"';
				
				valido = regExp.test(login);
				
				dojo.byId('esCorreo').value=0;
			 }
		}

		if (!valido){
			var textBox = dijit.byId("usuario");
			displayErrorMsg(textBox, errmsg);
		}
		
		return valido;
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
	
	var dialogMsg;
	
	function message(msg){
		var html =
		'<p style="text-align: center;">'+ msg +'</p>'+
		'<p style="text-align: center;">'+
		'<button id="btnCerrar" name="btnCerrar" dojoType="dijit.form.Button" onclick="closeWindow();">Cerrar</button>'+
	    '</p>';

		dialogMsg = new dijit.Dialog({
	        title: 'Detalle de registro',
	        content: html,
	        style: "width:300px",
	        showTitle: false, draggable : false, closable : false
	    });		
		
		//dojo.style(dialogDetalle.closeButtonNode,"display","none");	
		dialogMsg.show();
	}

	function closeWindow(){
		dialogMsg.hide();
	}
	
	function keySubmit(e) {
	    if (e.keyCode == 13) {
	    	registrate();
	    }
	}

	var userclean = true;
	var pwdclean  = true;
	var confclean = true;

	function clean(field) {

		if (field==1 && userclean){
			dojo.byId('usuario').value='';
			userclean = false;		
		}

		if (field==2 && pwdclean){
			dojo.byId('contrasena').value='';
			pwdclean = false;		
		}

		if (field==3 && confclean){
			dojo.byId('confirmacion').value='';
			confclean = false;		
		}
	}
	
	function validaUsuarioUnico(){
		var unico = true;
		
		dojo.byId('method').value='validaUsuarioUnico';

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
	}
	
  	function showErrorMsg(errmsg){
  		dojo.byId('errorMsgArea').innerHTML = errmsg;
  		dojo.byId('holderMensaje').style.display='block';
  	}

  	function cerrarError(){
  		dojo.byId('holderMensaje').style.display='none';
  	}

	function recuperaPsw(){
		doSubmit('toRecuperaContrasena');
	}
	
</script>

<noscript>Funciones de JavaScript desactivadas por navegador</noscript>

<c:if test="${registroCandidatoForm.registroDirecto}">
<script type="text/javascript">
	dojo.addOnLoad(function() {
		userclean = false; // Se apaga la bandera para limpiar el campo de nombre de usuario al obtener el foco
		
		dojo.byId('usuario').value      = '${registroCandidatoForm.usuario}';
		dojo.byId('contrasena').value   = '${registroCandidatoForm.contrasena}';
		dojo.byId('confirmacion').value = '${registroCandidatoForm.confirmacion}';

		registrate();
		
		message('Datos son inválidos, favor de verificar su captura de acuerdo a las especificaciones para el nombre de usuario y la contraseña.');
		
	});
</script>
</c:if>

<form name="registroCandidatoForm" id="registroCandidatoForm" method="post" action="registro.do" dojoType="dijit.form.Form">




	<input type="hidden" name="method" id="method" value="init" dojoType="dijit.form.TextBox"/> <!-- indica el metodo a invocar -->
	<input type="hidden" name="esCorreo" id="esCorreo" value="0" dojoType="dijit.form.TextBox"/>


<div class="formApp">
<div class="flow_1">Registro de Candidatos</div>
<div class="form_wrap">
<p class="instruc_01"><strong>¡Regístrate en 3 sencillos pasos!</strong></p>
<div class="form_edge">
<div class="stepApp">
	<h2><img alt="Paso 2 de 3. Usuario y contraseña" src="css/images/paso_2.png"></h2>
	<p>Los datos marcados con <span>*</span> son obligatorios</p>
</div>
<div class="user_dt form_nom">
	<h3>Datos de acceso</h3>
</div>
<fieldset>
	<legend>Usuario y contraseña</legend>
	<div class="margin_top_10">
		<label for="usuario"><span>*</span> Usuario o correo electrónico</label>
	</div>
	<p>Crea un nombre de usuario entre 8 y 12 caracteres o escribe un correo electrónico para tu registro.	Pueden ser letras mayúsculas o minúsculas y/o números. Si usas tu correo electrónico como usuario asegúrate que la dirección sea correcta.</p>
	<p><strong>Recuerda que a través de tu correo te enviaremos ofertas laborales.</strong></p>
	<div class="ctrl_08 margin_top_10">
		<input type="text" id="usuario" name="usuario" value="Usuario o correo electrónico"
	    		   size="50" maxlength="65" trim="true"
	    		   onfocus="javascript:clean(1);"
	    		   oncopy="return false;" oncut="return false" onpaste="return false;"
	    		   dojoType="dijit.form.ValidationTextBox"
	    		   required="true" missingMessage="El usuario es requerido."/>
	</div>
	
	
		<div id="holderMensaje" class="holder_mensaje" style="display:none; text-align: center;">
	       	<div class="posicion">
	        	<p class="mensaje_error" id="errorMsgArea" style='text-align: center;'>
	        	<input id="btnCerrar" name="btnCerrar" class="boton_naranja" type="button" value="Cerrar" onclick="cerrarError();"/>
	        	</p>
			</div>
	    </div>
    <div class="ctrl_09">
        <div class="margin_top_30">
            <label for="contrasena"><span>*</span>Contraseña</label>
            
			<p><em>La contraseña se te solicitará cada vez que quieras tener acceso a tu Espacio y a tu Perfil Laboral, y debe ser una sola palabra de 8 a 12 caracteres como mínimo que pueden ser
		letras mayúsculas o minúsculas y/o números.</em></p>
			<p><strong><em>Las letras acentuadas u otros caracteres no se permiten.</em></strong></p>
			<div class="margin_top_10">
	            <input type="password" id="contrasena" name="contrasena" value="${pwdaux}"
						   onfocus="javascript:clean(2);"
		            	   required="true" missingMessage="La contraseña es requerida."
		            	   constraints="{min:8,max:12}"
		            	   rangeMessage="La contraseña debe contar entre 8 y 12 caracteres."
				           regExp="${regexppwd}"
				           invalidMessage="Contraseña inválida, debe contar entre 8 y 12 caracteres, no se permiten caracteres especiales ni espacios en blanco, ejemplos: ñ,$,%,&,?,#,!."
				           dojoType="dijit.form.ValidationTextBox" size="65" maxlength="12" trim="true"/>
			</div>
        </div>
        <div class="margin_top_10">
            <label for="confirmacion"><span>*</span>Confirma tu contraseña</label>
            <input type="password" id="confirmacion" name="confirmacion" value="${pwdaux}"
	            	   onfocus="javascript:clean(3);"
	            	   required="true" missingMessage="La confirmación de contraseña es requerida."
	            	   constraints="{min:8,max:12}"
	            	   rangeMessage="La confirmación de contraseña debe contar entre 8 y 12 caracteres."
			           regExp="${regexppwd}"
			           invalidMessage="Confirmación de contraseña inválida, debe contar entre 8 y 12 caracteres, no se permiten caracteres especiales ni espacios en blanco, ejemplos: ñ,$,%,&,?,#,!."
			           dojoType="dijit.form.ValidationTextBox" size="65" maxlength="12" trim="true"
			           onkeypress="javascript:keySubmit(event);"/>
        </div>
    </div>
</fieldset>
</div>
</div>
<div class="form_nav">
	<input id="btnRegistro" class="boton_azul" type="button" value="Continuar" onclick="registrate();" />
	<input type="button" value="Cancelar" onclick="dijit.byId('msgCancelar').show();">
</div>
</div>

</form>

<div dojoType="dijit.Dialog" id="dialogUsuarioNoDisponible" title="Error" draggable="false" style="display:none">
	<div class="msg_contain">
		<p>
			El usuario que ha elegido ya no se encuentra disponible. Favor de proporcionar otro.
		</p>
	</div>
	<p class="form_nav">
		<button class="button" onclick="dijit.byId('dialogUsuarioNoDisponible').hide();">Cerrar</button>
	</p>	
</div>

<div dojoType="dijit.Dialog" id="msgCancelar" title="Aviso" style="display:none" draggable="false">
	<div class="msg_contain">
		<p>Los cambios no se guardar&aacute;n</p>		
	</div>
	<p class="form_nav">	
		<button class="button" onclick="window.open('${pageContext.request.contextPath}/logout.do', '_self');">Aceptar</button>
	</p>
</div>
