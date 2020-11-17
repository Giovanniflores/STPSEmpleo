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
dojo.require("dijit.form.Button");
dojo.require("dijit.form.ComboBox");
dojo.require("dojo.data.ItemFileReadStore");
  
  function consultaPermisos(idPerfil){

		dojo.byId('method').value = 'permisos';
		dojo.byId('idPerfil').value=idPerfil;

		dojo.xhrPost({url: 'adminpermisos.do', form:'adminpermisosForm', timeout:180000, // Tiempo de espera 3 min
			  	      load: function(data){
			  	    			dojo.byId('tabla').innerHTML=data;
				           }
				     });
  }

  function asignarPermisos(){
	  
	  if (dojo.byId('idPerfil').value<=0){
		  alert('Favor de seleccionar el Perfil');
		  return;
	  }

	  if (countChecks('accionopcion') == 0){
		  var answer = confirm("¿Desea eliminar todos los permisos del perfil seleccionado?");
		  if (!answer) return;
	  }

	  /*if (countChecks('autentica') == 0){
		  var answer = confirm("¿Desea permitir el acceso sin autorización a todos los modulos del sitio?");
		  if (!answer) return;
	  }*/

	  dojo.byId('method').value = 'asignar';

	  dojo.xhrPost({url: 'adminpermisos.do', form:'adminpermisosForm', timeout:180000, // Tiempo de espera 3 min
			  	    load: function(data){
			  	    	       var res = dojo.fromJson(data);
						       dojo.byId('message').innerHTML = res.message;

							   if (res.type!='err'){// EXITO
							      dojo.byId('message').style.color='#000066';
							   }else{ // ERROR
							      dojo.byId('message').style.color='#FF0000';
							   }
				          }
				   });
  }

  function turnChecks(name, value){
	  var c = new Array();
	  c = document.getElementsByTagName('input');
	  
	  for (var i = 0; i < c.length; i++){
	    if (c[i].type == 'checkbox' && c[i].name == name){
	      c[i].checked = value;
	    }
	  }
  }

  function countChecks(name){
	  var c = new Array();
	  c = document.getElementsByTagName('input');
	  
	  var count = 0;
	  
	  for (var i = 0; i < c.length; i++){
	    if (c[i].type == 'checkbox' && c[i].name == name && c[i].checked){
	    	count++;
	    }
	  }
	  
	  return count;
  }
  
</script>

<html:messages id="errors"><span class="redFont Font80"><bean:write name="errors"/></span><br/></html:messages>
<html:messages id="messages" message="true"><span class="Font80"><bean:write name="messages"/></span><br/></html:messages>

<form name="adminpermisosForm" id="adminpermisosForm" method="post" action="adminpermisos.do" dojoType="dijit.form.Form">
	
	<div dojoType="dojo.data.ItemFileReadStore" jsId="perfilesStore" url="${context}adminpermisos.do?method=perfiles"></div>

	<input type="hidden" name="method" id="method" value="init"/>
	<input type="hidden" name="idPerfil" id="idPerfil" value="0"/>

	<h3>Asignación de permisos por perfil</h3>
	<br/>
	<div id="message"></div>

	<span>
		<strong>*Perfil:</strong>
		<select dojotype="dijit.form.ComboBox" store="perfilesStore" id="idPerfilValue" required="true"
		        onchange="consultaPermisos(dijit.byId('idPerfilValue').get('item').label[0])" ></select>
		
		<input type="button" id="btnEnviar" value="Asignar" class="boton" onclick="asignarPermisos();"/>
	</span>
	<br/><br/>
	<div id="tabla">
		<table cellspacing="0" cellpadding="0" border="0" width="100%">
		<tbody>
			<tr class="temas">
		    	<th>Acceso</th>
		    	<th>Vínculo</th>
		    	<th>Permisos asignados</th>
		    	<%--<th>Requiere usuario autenticado</th>--%>
		    </tr>
		    <tr class='odd'><td colspan="3"><p>&nbsp;</p></td></tr>
			<tr><td colspan="3"><p>&nbsp;</p></td></tr>
		</tbody>
		</table>
	</div>

</form>
