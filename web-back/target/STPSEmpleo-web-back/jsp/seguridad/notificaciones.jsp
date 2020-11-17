<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%String context = request.getContextPath() +"/";%>
<style type="text/css">
	.redFont{
		color: #FF0000;
	}
	.Font80{
		font-size: 80%;
	}
</style>
<script type="text/javascript">
  dojo.require("dijit.dijit"); // loads the optimized dijit layer
  dojo.require("dijit.form.Form");
  dojo.require("dijit.Dialog");
  dojo.require("dijit.form.Button");
  dojo.require("dijit.form.TextBox");
  dojo.require("dijit.form.ValidationTextBox");
  dojo.require("dijit.form.RadioButton");
  dojo.require("dojox.form.Uploader");
	  
  function notifica(){	  

	  var isFile = false;
	  
	  var hasMail = dijit.byId('usuario').get('value') && dijit.byId('usuario').get('value')!='';
	  var hasFile = dojo.byId('archivoUsuarios').value && dojo.byId('archivoUsuarios').value!='';

	  if (!hasMail && !hasFile){
		  message('Debe indicar el nombre de usuario o archivo de direcciones para realizar la notificación.');
		  dijit.byId('usuario').focus();
		  return;
	  } else if (hasMail && hasFile) {
		  message('Solo puede indicar el nombre de usuario o el archivo pero no ambos.');
		  dijit.byId('usuario').focus();
		  return;
	  }
	  
	  if (hasMail){
		  if (!dijit.byId('usuario').isValid){
			  dijit.byId('usuario').focus();
			  return;
		  }
	  }
	  
	  if (hasFile){
		  isFile = true;
	  }

	  if (!confirm("Se realizará la notificación por correo, ¿desea continuar?")) return;
	  
	  if (dijit.byId('notificaEmpresa').get('checked')){
		  
		  if (dijit.byId('tipoNotificacionRegistro').get('checked')){
			  doSubmit('notificaRegistroEmpresa', isFile);
			  
		  } else if (dijit.byId('tipoNotificacionRecupera').get('checked')){
			  doSubmit('notificaRecuperaContrasenaEmpresa', isFile);
		  }
	  } else if (dijit.byId('notificaCandidato').get('checked')){

		  if (dijit.byId('tipoNotificacionRegistro').get('checked')){
			  doSubmit('notificaRegistroCandidato', isFile);

		  } else if (dijit.byId('tipoNotificacionRecupera').get('checked')){
			  doSubmit('notificaRecuperaContrasenaCandidato', isFile);
		  }
	  }
  }

  function doSubmit(method, isFile){

	dojo.byId('method').value = method;
	dojo.byId('btnNotificaEmpresa').disabled='disabled';
	dojo.byId('fileFlag').value = isFile;
	
	document.notificacionesForm.submit();
  }
  
  function message(msg){
	var mensaje = new dijit.Dialog({draggable: false, title: 'Notificación', content: msg, style: "width: 300px"});
	mensaje.show();
  }
  
  function limpiar(){
	  dojo.byId('method').value = 'init';
	  dijit.byId('usuario').set('value', '');
	  dojo.byId('archivoUsuarios').value = '';
  }
  
</script>
<noscript>Funciones de JavaScript desactivadas por navegador</noscript>

<c:if test="${not empty ResultVO}">
<script type="text/javascript">

	dojo.addOnLoad(function(){
		message('${ResultVO.message}');
	});

</script>
</c:if>

<html:messages id="errors"><span class="redFont Font80"><bean:write name="errors"/></span><br/></html:messages>
<html:messages id="messages" message="true"><span class="Font80"><bean:write name="messages"/></span><br/></html:messages>

<div class="form_consulta">
<form name="notificacionesForm" id="notificacionesForm" method="post" enctype="multipart/form-data" 
      action="notificaciones.do" dojoType="dijit.form.Form">

	<input type="hidden" name="method" id="method" value="init"/>
	<input type="hidden" name="fileFlag" id="fileFlag" value=""/>

 	<div>
		<h3>Notificaciones</h3>

		<div class="entero">
			<label for="text1"><strong>Notificar a:</strong></label><br/>
			<input type="radio" dojoType="dijit.form.RadioButton" name="opcionnotifica" id="notificaEmpresa" value="1" checked="checked"/>&nbsp;Empresa
			&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="radio" dojoType="dijit.form.RadioButton" name="opcionnotifica" id="notificaCandidato" value="2"/>&nbsp;Candidato
		</div>		
		<div class="entero">
			<label for="text1"><strong>Notificacíon por:</strong></label><br/>
			<input type="radio" dojoType="dijit.form.RadioButton" name="tipoNotificacion" id="tipoNotificacionRegistro" value="1" checked="checked"/>&nbsp;Registro
			&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="radio" dojoType="dijit.form.RadioButton" name="tipoNotificacion" id="tipoNotificacionRecupera" value="2"/>&nbsp;Recuperación de contraseña
		</div>		
		<div class="entero">
			<label for="text1"><strong>Usuario:&nbsp;&nbsp;</strong></label><br/>
			<input id="usuario" name="usuario" type="text" dojoType="dijit.form.ValidationTextBox" 
				   size="80" maxlength="65" trim="true"/>
		</div>
		<div class="entero">
			<label for="text1"><strong>Notificación multiple:&nbsp;&nbsp;</strong></label><br/>
			<input type="file" id="archivoUsuarios" name="archivoUsuarios" 
			       value="Seleccion de archivo..." size="50" maxlength="50" trim="true"/>
		</div>
        <div class="entero" style="text-align: center;">
			<input type="button" id="btnNotificaEmpresa" name="btnNotificaEmpresa" value="Notificar" class="boton" onclick="notifica();" />
			<input type="button" id="btnLimpiar" name="btnLimpiar" value="Limpiar" class="boton" onclick="limpiar();" />
		</div>

	</div>
</form>
</div>
