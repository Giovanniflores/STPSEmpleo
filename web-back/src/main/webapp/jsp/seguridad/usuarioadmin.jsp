<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
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
	dojo.require("dijit.form.ValidationTextBox");
	dojo.require("dijit.form.TextBox");
	dojo.require("dijit.form.ComboBox");
	dojo.require("dojo.data.ItemFileReadStore");
</script>
<noscript>Funciones de JavaScript desactivadas por navegador</noscript>
<script type="text/javascript">
function doSubmit(method){
	document.usuarioForm.method.value=method;
	document.usuarioForm.submit();
}

function doSubmitAjax(method){

	if (dijit.byId('usuarioForm').isValid()){

		dojo.byId('method').value=method;
		dojo.byId('btnEnviar').disabled=true;

		if (dijit.byId('idEntidadSelect').get('item') && dijit.byId('idEntidadSelect').get('item').label){
			dojo.byId('idEntidad').value = dijit.byId('idEntidadSelect').get('item').label[0];
		}
		if (dijit.byId('idTipoUsuarioSelect').get('item') && dijit.byId('idTipoUsuarioSelect').get('item').label){
			dojo.byId('idTipoUsuario').value = dijit.byId('idTipoUsuarioSelect').get('item').label[0];
		}
		if (dijit.byId('idPerfilSelect').get('item') && dijit.byId('idPerfilSelect').get('item').label){
			dojo.byId('idPerfil').value = dijit.byId('idPerfilSelect').get('item').label[0];
		}

		dojo.xhrPost(
				 {
				  url: 'usuarioadmin.do',
				  form:'usuarioForm',
				  timeout:180000, // Tiempo de espera 3 min
				  load: function(data){
					    var res = dojo.fromJson(data); // Datos del servidor

					    dojo.byId('message').innerHTML=res.message;
					    
						if (res.type!='err'){// EXITO
					    	dojo.byId('message').style.color='#000066';
					    	dojo.byId('divRegis').style.display='none';
					    	dojo.byId('divNuevo').style.display='block';

					    	dojo.byId('usuario').disabled=true;					    	
					    	dojo.byId('idEntidadSelect').disabled=true;
					    	dojo.byId('idTipoUsuarioSelect').disabled=true;
					    	dojo.byId('idPerfilSelect').disabled=true;
					    	dojo.byId('contrasena').disabled=true;

					    }else{ // ERROR
					    	dojo.byId('message').style.color='#FF0000';
					    	dojo.byId('divRegis').style.display='block';
					    	dojo.byId('divNuevo').style.display='none';
					    }
				    }
				 } );
	} else {
		dojo.byId('message').innerHTML = 'Faltan datos requeridos, favor de verificar.';
	}
	
}

</script>

<html:messages id="errors">
	<span class="redFont Font80"><bean:write name="errors"/></span><br/>
</html:messages>

<html:messages id="messages" message="true">
	<span class="Font80"><bean:write name="messages"/></span><br/>
</html:messages>


<form name="usuarioForm" id="usuarioForm" method="post" action="usuarioadmin.do" dojoType="dijit.form.Form">

	<input type="hidden" name="method" id="method" value="init"/> <!-- indica el metodo a invocar -->

	<br/>

	<h3>Registro de usuario</h3>
	
	<table>
		<tr>
			<td>Nombre:</td>
			<td>
				<input id="nombre" name="nombre" value="${usuarioForm.nombre}" dojoType="dijit.form.TextBox" maxlength="50" trim="true"/>
			</td>
		</tr>
		<tr>
			<td>Primer apellido:</td>
			<td>
				<input id="apellido1" name="apellido1" value="${usuarioForm.apellido1}" dojoType="dijit.form.TextBox" maxlength="50" trim="true"/>
			</td>
		</tr>
		<tr>
			<td>Segundo apellido:</td>
			<td>
				<input id="apellido2" name="apellido2" value="${usuarioForm.apellido2}" dojoType="dijit.form.TextBox" maxlength="50" trim="true"/>
			</td>
		</tr>
		<tr>
			<td>Usuario:</td>
			<td>
				<input id="usuario" name="usuario" value="${usuarioForm.usuario}" 
				       dojoType="dijit.form.ValidationTextBox" required="true" 
				       maxlength="50" trim="true"/>
			</td>
		</tr>	
		<tr>
			<td>Correo electrónico:</td>
			<td>
				<input id="correoElectronico" name="correoElectronico" value="${usuarioForm.correoElectronico}" 
				       dojoType="dijit.form.ValidationTextBox" required="true" 
				       invalidMessage="Dato inválido" maxlength="50" 
				       regExp="^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$" trim="true"/>
			</td>
		</tr>	
		<tr>
			<td>Contraseña:</td>
			<td>
				<input type="password" id="contrasena" name="contrasena" value="${usuarioForm.contrasena}" 
				       dojoType="dijit.form.ValidationTextBox" required="true" 
				       invalidMessage="Dato inválido" missingMessage="Contraseña requerida"
				       rangeMessage="La contraseña debe ser mayor a 8 caracteres"
				       constraints="{min:8,max:20}"
				       maxlength="20" trim="true"/>
			</td>
		</tr>	
		<tr>
			<td>Entidad:</td>
			<td>
			<input type="hidden" name="idEntidad" id="idEntidad"/>
			<div dojoType="dojo.data.ItemFileReadStore" jsId="entidadesStore" url="${context}usuarioadmin.do?method=entidades"></div>
			<select dojotype="dijit.form.ComboBox" store="entidadesStore" id="idEntidadSelect" required="true"></select>
			</td>
		</tr>
		<tr>
			<td>Tipo de usuario:</td>
			<td>
			<input type="hidden" name="idTipoUsuario" id="idTipoUsuario"/>
			<div dojoType="dojo.data.ItemFileReadStore" jsId="tiposStore" url="${context}usuarioadmin.do?method=tiposusuario"></div>
			<select dojotype="dijit.form.ComboBox" store="tiposStore" id="idTipoUsuarioSelect" required="true"></select>
			</td>
		</tr>
		<tr>
			<td>Perfil:</td>
			<td>
			<input type="hidden" name="idPerfil" id="idPerfil"/>
			<div dojoType="dojo.data.ItemFileReadStore" jsId="perfilesStore" url="${context}usuarioadmin.do?method=perfiles"></div>
			<select dojotype="dijit.form.ComboBox" store="perfilesStore" id="idPerfilSelect" required="true"></select>
			</td>
		</tr>
	</table>

	<p>
		<div id="divRegis">
			<input type="button" id="btnEnviar" value="Enviar" class="boton" onclick="doSubmitAjax('registrar');" />
			<input type="button" id="btnCancel" value="Cancelar" class="boton" onclick="doSubmit('init');" />
		</div>
		<div id="divNuevo" style="display:none;">
			<input type="button" id="btnNuevo" value="Nuevo" class="boton" onclick="doSubmit('init');" />
		</div>
	</p>

	<br/>
	<div id="message"></div>
	
</form>
