<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>

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
    dojo.require("dijit.form.NumberTextBox");
    dojo.require("dijit.form.TextBox");
	dojo.require("dijit.form.ComboBox");
	dojo.require("dojo.data.ItemFileReadStore");
</script>
<noscript>Funciones de JavaScript desactivadas por navegador</noscript>
<script type="text/javascript">
function doSubmit(method){
	//dojo.byId("method").value=method;
	document.getElementById('method').value=method;   
	document.parametrosForm.submit();
}
function doSubmitAjax(method){
	
	if (dojo.byId('idParametro').value<=0){
		alert('Favor de seleccionar el parámetro.');
		return;		
	}
	
	if(!dijit.byId('valor').isValid()){
		alert('Valor del parámetro requerido.');
		return;
	}
	if(!validarArgumento())
		return;
		
	dojo.byId('method').value=method;
	dojo.byId('btnEnviar').disabled=true;
	
	dojo.xhrPost(
			 {
			  url: 'parametrizarbloque.do',
			  form:'parametrosForm',
			  timeout:180000, // Tiempo de espera 3 min
			  load: function(data){
				    var res = dojo.fromJson(data); // Datos del servidor

				    dojo.byId('message').innerHTML=res.message;
				    
					if (res.type!='err'){// EXITO
				    	dojo.byId('message').style.color='#000066';
				    	dojo.byId('divRegis').style.display='none';
				    	dojo.byId('divModif').style.display='block';
				    	//dojo.byId('bloqueAsignacion').disabled=true;
				        dijit.byId('idParametroValue').disabled=true;
				        dijit.byId('valor').disabled=true;
				    }else{ // ERROR
				    	dojo.byId('message').style.color='#FF0000';
				    	dojo.byId('divRegis').style.display='block';
				    	dojo.byId('divModif').style.display='none';
				    	//dojo.byId('bloqueAsignacion').disabled=false;
				    	dijit.byId('idParametroValue').disabled=false;
				        dijit.byId('valor').disabled=false;
				    	dojo.byId('btnEnviar').disabled=false;
				    }
			    }
			 } );
}

function getParametro(idParametro){

	dojo.byId('method').value = 'valorparametro';
	dojo.byId('idParametro').value=idParametro;

	dojo.xhrPost({url: 'parametrizarbloque.do', form:'parametrosForm', timeout:180000, // Tiempo de espera 3 min
		  	      load: function(data){
				          var param = dojo.fromJson(data);
				          dojo.byId('valor').value = param.valor;
			           }
			     });
}

function validarArgumento(){
	
	var regExpUrl 		= /(http|https):\/\/(\w+:{0,1}\w*@)?(\S+)(:[0-9]+)?(\/|\/([\w#!:.?+=&%@!-\/]))?/;
	
	var idParametro = dojo.byId('idParametro').value;
	var valor 		= dojo.byId('valor').value;	
	
	if(idParametro == 1 || idParametro == 2){		
		if (!isNumber(valor)) {
			alert('El valor no es numerico.');
			return false;
		}
	
	/*else if(idParametro == 3){ //Jmhg :-> Validando hasta conocer que tipo de validacion es
		if (!soloCaracteres(valor)) {
			alert('Solo es premitido caracteres :-> ' + valor);
			return false;
		}
	}
	*/
	}else if(idParametro == 4 || idParametro == 5){		
		if (!regExpUrl.test(valor)) {
			alert('El valor no es una url valida.');
			return false;
		}
	}else if(idParametro >= 6 && idParametro <= 12){	
		if (!isNumber(valor)) {
			alert('El valor no es numerico.');
			return false;
		}
	}
	return true;
	
}

function isNumber(n) {
	  return !isNaN(parseFloat(n)) && isFinite(n);
	}	
	


</script>

<html:messages id="errors">
	<span class="redFont Font80"><bean:write name="errors"/></span><br/>
</html:messages>

<html:messages id="messages" message="true">
	<span class="Font80"><bean:write name="messages"/></span><br/>
</html:messages>

<form name="parametrosForm" id="parametrosForm" method="post" action="parametrizarbloque.do" dojoType="dijit.form.Form">

<div dojoType="dojo.data.ItemFileReadStore" jsId="parametrosStore" url="${context}parametrizarbloque.do?method=parametros"></div>

	<!-- indica el metodo a invocar -->
	<input type="hidden" name="method" id="method" value="init"/>
	<input type="hidden" name="idParametro" id="idParametro" value="0"/>

	<h3>Administración de parámetros</h3>

	<span>
		<strong><label for="idParametroValue">Parámetros</label> *:</strong><br/>
		<select dojotype="dijit.form.ComboBox" store="parametrosStore" id="idParametroValue" required="true"
				style="width:300px"
		        onchange="getParametro(dijit.byId('idParametroValue').get('item').label[0])" ></select>
	</span>
	<br/><br/>
	<span>
		<strong><label for="valor">Valor</label> *:</strong><br/>
		<input type="text" name="valor" id="valor" style="width:450px"
		       dojoType="dijit.form.ValidationTextBox" required="true" missingMessage="Dato requerido" value=""/>
	</span>
	<br/><br/>
	<div id="divRegis">
		<input type="button" id="btnEnviar" value="Enviar" class="boton" onclick="doSubmitAjax('registrar');" />
		<input type="button" id="btnCancel" value="Cancelar" class="boton" onclick="doSubmit('cancelar');" />
	</div>
	<div id="divModif" style="display:none;">
		<input type="button" id="btnModif" value="Modificar" class="boton" onclick="doSubmit('init');" />
	</div>
	<br/>
	
	<div id="message"></div>

</form>
