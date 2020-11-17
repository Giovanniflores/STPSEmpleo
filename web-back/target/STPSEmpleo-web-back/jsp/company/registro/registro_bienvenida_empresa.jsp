<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<jsp:useBean id="fecha" scope="page" class="java.util.Date"/>

<fmt:setBundle basename="MessageResources" var="portalbundle"/>

<c:set var="regexpmail">
^[a-zA-Z0-9_-]+(?:\.[a-zA-Z0-9_-]+)*@(?:[a-zA-Z0-9](?:[a-zA-Z0-9-_]*[a-zA-Z0-9-])?\.)+[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?$
</c:set>

<%String context = request.getContextPath() + "/";%>

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
		if (dijit.byId('msgSent') && dijit.byId('msgSent').value=='1'){
			dijit.byId('msgSent').value=='2';
		}			
	});
	
	function doSubmit(method){
		dojo.byId('method').value=method;
		dojo.byId('registroEmpresaForm').submit();
	}	
	
	function continuar(){
		doSubmit('toMiEspacio');
	}
	
	
	function enviarPorCorreo(){		

		dojo.byId('method').value='notificaRegistroEmpresa';		
		bloquedoPantalla();		
		dojo.xhrPost({url: 'registroEmpresa.do', form:'registroEmpresaForm', 
			  		  load: function(data){
			  					desbloqueoPantalla();
			  					var res = dojo.fromJson(data); // Datos del servidor
			  					if('exito' == res.type){
				  					dojo.byId('btnEnvioMail').disabled=true; // una vez enviado se evita que vuelvan a enviar
			  					} else if('error' == res.type){
			  						message('No se pudo enviar el usuario y contraseña a la cuenta de correo proporcionada, inténtelo nuevamente o seleccione el botón [Imprimir].');
			  					} else if('errormaildate' == res.type){
			  						message('No se pudo enviar la notificación, favor de verificar que haya capturado los datos necesarios (Usuario, Contraseña, Correo electrónico)');
			  					} else {
			  						message('No se pudo enviar el usuario y contraseña a la cuenta de correo proporcionada, inténtelo nuevamente o seleccione el botón [Imprimir].');
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
  	
	var dialogMsg;
	
	function message(msg){
		alert(msg);
	}

	function closeWindow(){
		dialogMsg.hide();
	}
  	
	function imprimir(){
		window.print();
	}	
</script>	

<c:if test="${registroEmpresaForm.correoElectronico}">
	<script type="text/javascript">
		dojo.addOnLoad(function() {
			enviarPorCorreo();
		});
	</script>
</c:if>


<form name="registroEmpresaForm" id="registroEmpresaForm" method="post" action="registroEmpresa.do" dojoType="dijit.form.Form">
	<input type="hidden" name="method" id="method" value="init" dojoType="dijit.form.TextBox"/> <!-- indica el metodo a invocar -->
	<input type="hidden" name="msgSent" id="msgSent" value="1" dojoType="dijit.form.TextBox"/>
 
	
	<div class="formApp">
		<div class="flow_1">Registro de Empresas</div>
		<div class="screen_bienvenido">
			<h2 class="bienvenido_1">
				Bienvenido,<br/>
				<c:choose>
					<c:when test="${registroEmpresaForm.idTipoEmpresa eq '2' and registroEmpresaForm.idTipoPersona eq '1'}">
						${registroEmpresaForm.nombre} ${registroEmpresaForm.apellido1}
						<c:if test="${not empty registroEmpresaForm.apellido2}">${registroEmpresaForm.apellido2}</c:if>
					</c:when>									
					<c:otherwise>
						${registroEmpresaForm.razonSocial}
					</c:otherwise>
				</c:choose>					
			</h2>
			<div class="access_1"><strong>Has creado tu acceso al portal.</strong><br>
				Estos son los datos de tu registro para acceder al portal			
			</div>
			<div class="user_psswd">
				<div class="labeled">Correo electrónico:</div>${registroEmpresaForm.usuario}
				<div></div>
				<div class="labeled">Contraseña</div>${registroEmpresaForm.contrasena}
				<div></div>
			</div>	
			<div class="fecha_alta">
				<p><strong>Fecha de registro:</strong> 
					<fmt:formatDate pattern="dd/MM/yy" value="${fecha}"/>
				</p>
		        <p><strong>ID Portal del Empleo:</strong> ${registroEmpresaForm.idPortalEmpleo}</p>
		        
				<c:choose>
					<c:when test="${registroEmpresaForm.idTipoEmpresa eq '2' and registroEmpresaForm.idTipoPersona eq '1'}">
				        <p><strong>Fecha de nacimiento:</strong> 
				        	${registroEmpresaForm.fechaNacimiento}
				        </p>
					</c:when>									
					<c:otherwise>
				        <p><strong>Fecha de acta constitutiva:</strong>
				        	${registroEmpresaForm.fechaActa}
				        </p>
					</c:otherwise>
				</c:choose>			        
		        

		    </div>	
			<div class="cntrl_alta">
				<input type="button" value="Imprime tus claves de acceso" class="ico_imprimir fl" 
					id="btnImprimir" name="btnImprimir" onclick="imprimir();">
				
				<input type="button" value="Envía tus claves de acceso" class="ico_enviar fl" 
					id="btnEnvioMail" name="btnEnvioMail" onclick="enviarPorCorreo();" >
				
				<div class="clearfix"></div>
			</div>		    
			<div class="msg_listo">Te recordamos que para hacer uso de los beneficios de tu registro, es necesario crear una oferta de empleo</div>
			<div class="form_nav">
				<input type="button" value="Crear oferta de empleo" id="btnContinuar" name="btnContinuar" onclick="continuar();" >
			</div>
			<div class="end_links">	
				<a href="<%=context%>registroEmpresa.do?method=toMiEspacio">Ir a mi espacio</a>
				<a href="<%=context%>logout.do">Salir</a>				
			</div>		    		
				
		</div>
				
	    <div id="holderMensaje" class="holder_mensaje" style="display:none">
	       	<div class="posicion">
	        	<p class="mensaje_error" id="errorMsgArea"></p>
			</div>
	    </div>					
			 
	</div>	
</form>


