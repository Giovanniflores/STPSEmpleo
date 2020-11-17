<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%String context = request.getContextPath() +"/";%>
<style type="text/css">
	.redFont{
		color: #FF0000;
	}
	#cuerpo_int .row {
		margin-top: 30px;
		padding-bottom: 10px;
		border-bottom: 1px solid #cccccc;
	}
</style>
<script type="text/javascript">
	dojo.require("dijit.dijit"); // loads the optimized dijit layer
	dojo.require("dijit.form.Form");
	dojo.require("dijit.form.Button");
  
  function consultaPermisos(idPerfil){

		dojo.byId('method').value = 'permisos';
		dojo.byId('idPerfil').value=idPerfil;

		dojo.xhrPost({url: 'adminpermisos.do', form:'adminusuarioForm', timeout:180000, // Tiempo de espera 3 min
			  	      load: function(data){
			  	    			dojo.byId('tabla').innerHTML=data;
				           }
				     });
  }

  function doSubmit(method){
		document.adminusuarioForm.method.value=method;
		document.adminusuarioForm.submit();
  }

</script>
<noscript>Funciones de JavaScript desactivadas por navegador</noscript>

<html:messages id="errors"><span class="redFont Font80"><bean:write name="errors"/></span><br/></html:messages>
<html:messages id="messages" message="true"><span class="Font80"><bean:write name="messages"/></span><br/></html:messages>

<form name="adminusuarioForm" id="adminusuarioForm" method="post" action="adminusuarios.do" dojoType="dijit.form.Form">
	<input type="hidden" name="method" id="method" value="init"/>
	<input type="hidden" name="idUsuario" id="idUsuario" value="${usuariodetalle.idUsuario}"/>

	<h3>Desbloqueo de usuario</h3>

	<div class="row">
		<strong>*<label for="usuario">Nombre de usuario</label>:</strong>
		<br/>
		<input id="usuario" name="usuario" value="${usuariodetalle.usuario}"
		       dojoType="dijit.form.TextBox" size="50" maxlength="50" trim="true"/>
		&nbsp;&nbsp;
		<input type="button" id="btnBuscar" value="Buscar" class="boton" onclick="doSubmit('buscar');"/>
	</div>

 	<div>
 		<div class="row">
	        <div class="col-md-12">
	            <label for="text1"><strong>ID Usuario:&nbsp;&nbsp;</strong></label>${usuariodetalle.idUsuario}
	        </div>
        </div>
        <div class="row">
	        <div class="col-md-12">
	            <label for="text1"><strong>ID Usuario:&nbsp;&nbsp;</strong></label>${usuariodetalle.usuario}
	        </div>
        </div>
        <div class="row">
	        <div class="col-md-12">
	            <label for="text1"><strong>Correo electrónico:&nbsp;&nbsp;</strong></label>${usuariodetalle.correoElectronico}
	        </div>
        </div>
        <div class="row">
	        <div class="col-md-12">
	            <label for="text1"><strong>Nombre:&nbsp;&nbsp;</strong></label>${usuariodetalle.nombre}
	        </div>        
        </div>
        <div class="row">
	        <div class="col-md-12">
	            <label for="text1"><strong>Primer apellido:&nbsp;&nbsp;</strong></label>${usuariodetalle.apellido1}
	        </div>        
        </div>
        <div class="row">
	        <div class="col-md-12">
	            <label for="text1"><strong>Segundo apellido:&nbsp;&nbsp;</strong></label>${usuariodetalle.apellido2}
	        </div>
        </div>
        <div class="row">
	        <div class="col-md-12">
	            <label for="text1"><strong>Tipo de usuario:&nbsp;&nbsp;</strong></label>${usuariodetalle.idTipoUsuario}
	        </div>
        </div>
        <div class="row">
	        <div class="col-md-12">
	            <label for="text1"><strong>Perfil:&nbsp;&nbsp;</strong></label>${usuariodetalle.idPerfil}
	        </div>        
        </div>
        <div class="row">
	        <div class="col-md-12">
	            <label for="text1"><strong>Estatus:&nbsp;&nbsp;</strong></label>${usuariodetalle.estatus}
	        </div>
        </div>
        <div class="row">
        	<div class="col-md-12">
	        	<label for="text1"><strong>Sesion activa:&nbsp;&nbsp;</strong></label>
	        	<c:if test="${usuariodetalle.sesionActiva == 0}">
	        	Usuario no firmado en el portal
	        	</c:if>
	        	<c:if test="${usuariodetalle.sesionActiva == 1}">
	            Usuario con Sesión Activa &nbsp;&nbsp;&nbsp;&nbsp;
	            <input type="button" id="btnDesbloquear" value="Desbloquear" class="boton" onclick="doSubmit('desbloquear');"/>
	        	</c:if>
        	</div>
        </div>
	</div>
</form>
