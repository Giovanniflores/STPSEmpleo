<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setBundle basename="MessageResources" var="portalbundle"/>

<c:set var="regexpmail">
^[a-zA-Z0-9_-]+(?:\.[a-zA-Z0-9_-]+)*@(?:[a-zA-Z0-9](?:[a-zA-Z0-9-_]*[a-zA-Z0-9-])?\.)+[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?$
</c:set>
<c:set var="regexppwd">
^[a-zA-Z0-9]{8,12}
</c:set>
<c:set var="regexplogin">
^[a-zA-Z������������0-9\s]{6,10}$
</c:set>

<%String context = request.getContextPath() +"/";%>

<style type="text/css">
	#dialogMsg_underlay { background-color:gray; }
	.oculto {
	   position: absolute !important;
	   top: -9999px !important;
	   left: -9999px !important;
	}
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
		dijit.byId('usuario').focus(); 
	});

	function doSubmit(method){
		dojo.byId('method').value=method;
		dojo.byId('cuentaRegenerada').value=false;
		dojo.byId('registroEmpresaForm').submit();
	}
	
	function doSubmitReg(){
		document.getElementById('method').value = 'toRegeneracion';
		document.getElementById('cuentaRegenerada').value = true;
		dojo.byId('registroEmpresaForm').submit();
	}	
	
	function recuperarContrasena(){
		doSubmit('toRecuperaContrasena');
	}
	
	
	function registrate(){
		if (validate()==true){			
			if (validaUsuarioUnico()==false){
				var vMensajeConOfertas = "";
				if(validaTieneOfertas()){
					vMensajeConOfertas =
						"<strong>El usuario ya est� registrado en el portal.</strong><br/><br/>" + 
						"Para ingresar al portal, puedes:<br/>" + 
						"<input id='btnRecuperar' name='btnRecuperar' class='boton_naranja' type='button' value='Recuperar tu contrase�a' onclick='recuperarContrasena();'/>"
						+ "<br/>" +
						"Si lo deseas comun�cate al tel�fono <strong>01 800 841 2020</strong> <br/>" + 
						"<input id='btnCerrar' name='btnCerrar' class='boton_naranja' type='button' value='Cerrar' onclick='cerrarError();'/>";					
				} else {
					vMensajeConOfertas =
						"<strong>El usuario ya est� registrado en el portal.</strong><br/><br/>" + 
						"Para ingresar al portal, puedes:<br/>" + 					
						"<input id='btnRecuperar' name='btnRecuperar' class='boton_naranja' type='button' value='Recuperar tu contrase�a' onclick='recuperarContrasena();'/>" +
						"     o     " + 
						"<input id='btnRegenerar' name='btnRegenerar' class='boton_naranja' type='button' value='Registrarte de nuevo' onclick='doSubmitReg();'/>"
						+ "<br/>Si lo deseas comun�cate al tel�fono <strong>01 800 841 2020</strong> <br/>" + 
						"<input id='btnCerrar' name='btnCerrar' class='boton_naranja' type='button' value='Cerrar' onclick='cerrarError();'/>";
				}							
				showErrorMsg(vMensajeConOfertas);			
				return;
			}
			doSubmit('toRegistro');
		}
	}	

	function validate(){
		var valido = true;		
		
		if (!dijit.byId('usuario').isValid()){
			dijit.byId('usuario').focus();
			return false;
		}

		if (!dijit.byId('contrasena').isValid()){
			dijit.byId('contrasena').focus();
			//algo
			return false;
		}

		if (!dijit.byId('confirmacion').isValid()){
			dijit.byId('confirmacion').focus();
			return false;
		}		
		
		if (!validaIgualdad()){
			return false;
		}

		if (!validarUsuario()){
			return false;	
		}		
		return valido;
	}	
	
	function validaIgualdad(){
		var valido = true;		
		if (dijit.byId('contrasena') && dijit.byId('confirmacion')){
			if (dijit.byId('contrasena').get('value') != dijit.byId('confirmacion').get('value')){
				valido = false;
				var errmsg = 'La confirmaci�n de la contrase�a no coincide. Favor de verificar el dato proporcionado.';
				var textBox = dijit.byId('confirmacion');
				displayErrorMsg(textBox, errmsg);				
			}
		}
		return valido;
	}	
	
	function validarUsuario() {
		var valido = true;		
		var errmsg = '';		
		var login = dijit.byId('usuario').get('value');		
		if (login){
			var regExp = /${regexpmail}/;
			errmsg = 'Favor de proporcionar una cuenta de correo electr�nico v�lida.';				
			valido = regExp.test(login);				
			dojo.byId('esCorreo').value=1;
		}
		if (!valido){
			var textBox = dijit.byId('usuario');
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
	        style: "width:300px; height:200px;",
	        showTitle: false, draggable : false, closable : false
	    });				
		dialogMsg.show();
	}	
	
	function closeWindow(){
		dialogMsg.hide();
	}
	
	
	
	
	
  	function showErrorMsg(errmsg){
  		dojo.byId('errorMsgArea').innerHTML = errmsg;
  		dojo.byId('holderMensaje').style.display='block';
  	}
  	
  	function cerrarError(){
  		dojo.byId('holderMensaje').style.display='none';
  	}  	
	
	function keySubmit(e) {
	    if (e.keyCode == 13) {
	    	registrate();
	    }
	}

	var confirmclean = true;
	var pswclean = true;

	function clean(field) {
		if (field==1){
			if (pswclean){
				dojo.byId('contrasena').value='';
				dojo.byId('confirmacion').value='';
				pswclean = false;
			}		
		}
		if (field==2){
			if (confirmclean){
				dojo.byId('confirmacion').value='';
				confirmclean = false;
			}
		}
	}
	
	function validaTieneOfertas(){
		var tieneOfertas = true;
		dojo.byId('method').value='validaTieneOfertas';
		dojo.xhrPost({url: 'registroEmpresa.do', form:'registroEmpresaForm', sync: true, 
	  		  load: function(data){
	  					var res = dojo.fromJson(data);
	  					if('exito' == res.type){
	  						if ('tieneOfertas'== res.message){
	  							tieneOfertas = true;	
	  						} else {
	  							tieneOfertas = false;
	  						}
	  					} else if('error' == res.type){
	  						tieneOfertas = false;
	  					}
	 				}
			});
		return tieneOfertas;		
	}

	function validaUsuarioUnico(){
		var unico = true;		
		dojo.byId('method').value='validaUsuarioUnico';
		dojo.xhrPost({url: 'registroEmpresa.do', form:'registroEmpresaForm', sync: true, 
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
	
	function cancelConfirmation() {
		var answer = confirm("Los datos no guardados se perder�n �Continuar?");
		if (answer){		
			doSubmitEmpresa('cancelar');
		}
	}
	
	function cancelar(){
		document.location.href = '/registroEmpresa.do?method=init'; 
	}
	
</script>
		
<form name="registroEmpresaForm" id="registroEmpresaForm" method="post" action="registroEmpresa.do" dojoType="dijit.form.Form">
	<input type="hidden" name="method" 					id="method" 				value="init"	dojoType="dijit.form.TextBox"/> 
	<input type="hidden" name="esCorreo" 				id="esCorreo" 				value="0" 		dojoType="dijit.form.TextBox"/>
	<input type="hidden" name="cuentaRegenerada" 		id="cuentaRegenerada" 		value="false" 	dojoType="dijit.form.TextBox"/>	
	<input type="hidden" name="tieneOfertas" 			id="tieneOfertas" 			value="${registroEmpresaForm.tieneOfertas}" dojoType="dijit.form.TextBox"/>		
	<input type="hidden" name="urlRecuperaContrasena" 	id="urlRecuperaContrasena" 	value="${registroEmpresaForm.urlRecuperaContrasena}" />
	
	
<div class="formApp">
<div class="flow_1">Registro de Empresas</div>
<div class="form_wrap">
<p class="instruc_01"><strong>�Reg�strate en 2 sencillos pasos!</strong></p>
<div class="form_edge">
<div class="stepApp">
	<h2><img src="css/images/paso_1B.png" alt="Paso 1 de 2. Correo electr�nico y contrase�a"></h2>
	<p>Los datos marcados con <span>*</span> son obligatorios</p>
</div>
<div class="user_dt form_nom">
	<h3>Datos de acceso</h3>
</div>
<fieldset>
	<legend>Correo electr�nico y contrase�a</legend>
	<div class="margin_top_10">
		<label for="usuario"><span>*</span> Correo electr�nico</label>
	</div>
	<p>Escribe tu correo electr�nico para enviarte postulaciones de candidatos a tus ofertas de empleo.</p>
	<div class="ctrl_08 margin_top_10">
		<input class="usuario" type="text" id="usuario" name="usuario" value="${registroEmpresaForm.usuario}"
	    		   size="50" maxlength="65" trim="true"
	    		   oncopy="return false;" oncut="return false" onpaste="return false;"
	    		   dojoType="dijit.form.ValidationTextBox" value="Correo electr�nico"
	    		   regExp="${regexpmail}" invalidMessage="Correo electronico invalido, favor de verificar la captura."
	    		   required="true" missingMessage="El correo electr�nico es requerido."/>
	</div>

    <div class="ctrl_09">
        <div class="margin_top_10">
            <label for="contrasena"><span>*</span>Contrase�a</label>
			<p>Crea una contrase�a para tener acceso a tu Espacio y administrar tus ofertas de empleo. La contrase�a debe ser de 8 a 12 caracteres como m�nimo y sin espacios, que pueden ser letras may�sculas o min�sculas y/o n�meros. No se admiten letras acentuadas ni caracteres especiales.</p>
			<div class="margin_top_10">
	            <input type="password" id="contrasena" name="contrasena" value="${registroEmpresaForm.contrasena}"
	            	   required="true" missingMessage="La contrase�a es requerida."
	            	   constraints="{min:8,max:12}" rangeMessage="La contrase�a debe contar con 8 a 12 caracteres."
			           regExp="${regexppwd}" invalidMessage="Contrase�a inv�lida, verificar captura, debe contar con 8 a 12 caracteres y no se permiten caracteres como � � & ! @ # %  ni vocales acentuadas, ni espacios en blanco."
			           dojoType="dijit.form.ValidationTextBox" size="65" maxlength="12" trim="true"
			           onfocus="javascript:clean(this, 1);"/>
			</div>
        </div>
        <div class="margin_top_10">
            <label for="confirmacion"><span>*</span>Confirma tu contrase�a</label>
            <input type="password" id="confirmacion" name="confirmacion" value="${registroEmpresaForm.confirmacion}"
	            	   required="true" missingMessage="La confirmaci�n de contrase�a es requerida."
	            	   constraints="{min:8,max:12}" rangeMessage="La confirmaci�n de contrase�a debe contar con 8 a 12 caracteres."
			           regExp="${regexppwd}" invalidMessage="La confirmaci�n de la contrase�a no coincide con la contrase�a capturada. Favor de revisar los datos proporcionados."
			           dojoType="dijit.form.ValidationTextBox" size="65" maxlength="12" trim="true"
			           onfocus="javascript:clean(this, 2);"
			           onkeypress="javascript:keySubmit(event);"/>
        </div>
    </div>
</fieldset>
</div>
</div>
<div class="form_nav">
	<input id="btnRegistro" type="button" value="Continuar" onclick="registrate();" />
	<input type="button" value="Cancelar" onclick="cancelar();">
</div>	
</div>
	
</form>

		<div dojoType="dijit.Dialog" id="holderMensaje" title="Error" class="suelto" style="display: none;" draggable="false">
			<div class="msg_contain">
				<p id="errorMsgArea" style='text-align: center;'/>
			</div>
			<p class="form_nav">	
				<button class="button" onclick="dijit.byId('holderMensaje').hide();">Cerrar</button>
	        </p>
		</div>	
