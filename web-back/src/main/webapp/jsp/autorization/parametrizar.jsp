<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>

<script type="text/javascript">
    dojo.require("dijit.dijit"); // loads the optimized dijit layer
    dojo.require("dijit.form.NumberTextBox");
    dojo.require("dijit.form.TextBox");
</script>
<style type="text/css">
	.redFont{
		color: #FF0000;
	}
	.Font80{
		font-size: 80%;
	}
</style>
<script type="text/javascript">
function doSubmit(method){
	dojo.byId("method").value=method;
	document.parametrosForm.submit();
}
function doSubmitAjax(method){
	
	dojo.byId('method').value=method;
	dojo.byId('btnEnviar').disabled=true;
	
	dojo.xhrPost(
			 {
			  url: 'parametrizar.do',
			  form:'parametrosForm',
			  timeout:180000, // Tiempo de espera 3 min
			  load: function(data){
				    var res = dojo.fromJson(data); // Datos del servidor

				    dojo.byId('message').innerHTML=res.message;
				    
					if (res.type!='err'){// EXITO
				    	dojo.byId('message').style.color='#000066';
				    	dojo.byId('divRegis').style.display='none';
				    	dojo.byId('divModif').style.display='block';
				    	dojo.byId('tiempoReasignacion').disabled=true;
				    }else{ // ERROR
				    	dojo.byId('message').style.color='#FF0000';
				    	dojo.byId('divRegis').style.display='block';
				    	dojo.byId('divModif').style.display='none';
				    }
			    }
			 } );
}
</script>
<noscript>Funciones de JavaScript desactivadas por navegador</noscript>
<html:messages id="errors">
	<span class="redFont Font80"><bean:write name="errors"/></span><br/>
</html:messages>

<html:messages id="messages" message="true">
	<span class="Font80"><bean:write name="messages"/></span><br/>
</html:messages>

<form name="parametrosForm" id="parametrosForm"
      method="post" action="parametrizar.do"
      dojoType="dijit.form.Form">

	<!-- indica el metodo a invocar -->
	<input type="hidden" name="method" id="method" value="init"/>

	<h3>Parametrizar tiempo de asignación</h3>

	<p>
		<strong><label for="tiempoReasignacion">Tiempo entre la asignación (minutos)</label>*:</strong><br/>
		<input type="text" name="tiempoReasignacion" id="tiempoReasignacion" size="10" maxlength="3"
		       dojoType="dijit.form.NumberTextBox" required="true" constraints="{min:30,max:120}"
		       invalidMessage="Dato Inválido" rangeMessage="Dato fuera de rango (30-120)" missingMessage="Dato requerido"
		       value="${parametrosForm.tiempoReasignacion}"/>
        <label for="tiempoReasignacion"></label>
		<br/>
		<br/>
		
		<div id="divRegis">
			<input type="button" id="btnEnviar" value="Enviar" class="boton" onclick="doSubmitAjax('registrar');" />
			<input type="button" id="btnCancel" value="Cancelar" class="boton" onclick="doSubmit('init');" />
		</div>
		<div id="divModif" style="display:none;">
			<input type="button" id="btnModif" value="Modificar" class="boton" onclick="doSubmit('init');" />
		</div>
		<br/>
		<div id="message"></div>
	</p>

</form>

<script>
dojo.addOnLoad(function(){
    //dojo.query("body").addClass("claro");
})
</script>
