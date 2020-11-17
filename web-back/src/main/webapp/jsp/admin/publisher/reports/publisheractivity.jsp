<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="mx.gob.stps.portal.core.infra.utils.Constantes"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript">
    dojo.require("dijit.dijit"); // loads the optimized dijit layer
	dojo.require("dijit.form.Form");    
    dojo.require("dijit._Calendar");
    dojo.require("dijit.form.Textarea");
    dojo.require("dijit.form.DateTextBox");    
    dojo.require("dijit.form.NumberTextBox");
    dojo.require("dijit.form.ValidationTextBox");
    dojo.require("dijit.form.TextBox");
	dojo.require("dijit.Dialog");
    dojo.require("dijit.form.Button");
</script>
<style type="text/css">
	.redFont{
		color: #FF0000;
	}
	#publisherActivityForm .row {
		margin-top: 30px;
		padding-bottom: 10px;
		border-bottom: 1px solid #cccccc;
	}
</style>
<script type="text/javascript">
	function doSubmit(method) {
		if(checkOtherFields()) {
			document.publisherActivityForm.method.value=method;
			document.publisherActivityForm.submit();			
		}
	}
	function doSubmitAction() {
		document.publisherActivityForm.method.value='excel';
		document.publisherActivityForm.submit();			
	}
	function doSubmitPager(method){
	  dojo.byId('method').value = method;	 
	  dojo.xhrPost({url: 'publisheractivity.do', form:'publisherActivityForm', timeout:180000, 
	  	load: function(data) {
	  		dojo.byId('table').innerHTML=data;
		}});  
	}
	function cancelConfirmation() {
		var answer = confirm("Los datos no guardados se perderán ¿Continuar?");
		if (answer){
			doSubmit('init');
		}
	}
	function getAnyElementValueById(elementId){
		var vElement = dijit.byId(elementId).value;
		if(vElement==undefined || vElement==''){
			vElement = document.getElementById(elementId).value;
		}
		return vElement;
	}
	function checkOtherFields(){
		var vForm = true;
		var vMessage = '';
		var name = getAnyElementValueById('name');
		var lastname = getAnyElementValueById('lastname');
		var dateInitAdd = getAnyElementValueById('dateInitAdd');
		var dateFinalAdd = getAnyElementValueById('dateFinalAdd');
		if (name.length<1 && lastname.length<1 && dateInitAdd.length<1) {
			vMessage = vMessage + '\nDebe proporcionar un criterio para realizar la búsqueda.';
			vForm = false;
		}
		if (dateInitAdd > dateFinalAdd) {
			vMessage = vMessage + '\nEl rango de fecha no es válido.';
			vForm = false;
		}
		if (dateInitAdd.length>1 && dateFinalAdd.length<1) {
			vMessage = vMessage + '\nDebe proporcionar el rango de fecha completo.';
			vForm = false;
		}
		if (vMessage.length>0) {
			alert(vMessage);
		}
		return vForm;	
	}
	function doSubmitPagina(pagina) {
		dojo.byId('method').value = "goToPage";
		dojo.byId('goToPageNumber').value = pagina;
		dojo.xhrPost({url: 'publisheractivity.do', form:'publisherActivityForm', timeout:180000, 
			load: function(data) {
				dojo.byId('table').innerHTML=data;
		}});
	}
	function mostrarResumen(idResumen) {
	    var thisRow = dojo.style(idResumen, "display");
	    if (thisRow == 'block') {
	        dojo.style(idResumen, "display", "none");
	        dojo.style(eval("\"" + "hide" + idResumen + "\""), "display", "none");
	        dojo.style(eval("\"" + "show" + idResumen + "\""), "display", "block");
	    }
	    if (thisRow == 'none') {
	        dojo.style(idResumen, "display", "block");
	        dojo.style(eval("\"" + "hide" + idResumen + "\""), "display", "block");
	        dojo.style(eval("\"" + "show" + idResumen + "\""), "display", "none");
	    }
	 }
	function mostrarTodas(tipo, totReg) {
	    if (tipo == 1) {
	        for (var i = 1; i <= totReg; i++) {
	            dojo.style("hideAll", "display", "block");
	            dojo.style("showAll", "display", "none");
	            dojo.style(eval("\"" + i + "\""), "display", "block");
	            dojo.style(eval("\"" + "hide" + i + "\""), "display", "block");
	            dojo.style(eval("\"" + "show" + i + "\""), "display", "none");
	        }
	    }
	    if (tipo == 2) {
	        for (var i = 1; i <= totReg; i++) {
	            dojo.style("hideAll", "display", "none");
	            dojo.style("showAll", "display", "block");
	            dojo.style(eval("\"" + i + "\""), "display", "none");
	            dojo.style(eval("\"" + "hide" + i + "\""), "display", "none");
	            dojo.style(eval("\"" + "show" + i + "\""), "display", "block");
	        }
	    }
	}
</script>
<noscript>Funciones de JavaScript desactivadas por navegador</noscript>

<html:messages id="errors">
	<span class="redFont Font80"><bean:write name="errors"/></span><br/>
</html:messages>

<html:messages id="messages" message="true">
	<span class="Font80"><bean:write name="messages"/></span><br/>
</html:messages>

<div class="form_consulta">
<form name="publisherActivityForm" id="publisherActivityForm" method="post" action="publisheractivity.do" dojoType="dijit.form.Form">
	<input type="hidden" name="method" id="method" value="filter"/>
	<input type="hidden" name="goToPageNumber" id="goToPageNumber" value="1" />

	<h3>Productividad de los publicadores</h3>
	<div>
       	<div class="row">
        	<label for="name"><strong>Nombre(s):</strong></label><br/>
			<input id="name" name="name" required="false" dojoType="dijit.form.ValidationTextBox"
				   invalidMessage="Dato no válido" size="65" maxlength="65" clearOnClose="true" trim="true" />
       	</div>
       	<div class="row">
            <label for="lastname"><strong>Primer apellido:&nbsp;&nbsp;</strong></label><br/>
            <input id="lastname" name="lastname" required="false" dojoType="dijit.form.ValidationTextBox" size="65" maxlength="65" 
            trim="true" />
       	</div>
		<div class="row">
			<label for="dateAdd"><strong>Fecha de reporte&nbsp;&nbsp;</strong></label><br/>
			<label for="dateAddInit"><strong>Entre:&nbsp;&nbsp;</strong></label>
       		<input type="text" name="dateInitAdd" id="dateInitAdd" value="" dojoType="dijit.form.DateTextBox" maxlength="10" constraints={datePattern:'dd/MM/yyyy'} trim="true" required="false"/>
       		<label for="dateFinalAdd"><strong>y&nbsp;&nbsp;</strong></label>
       		<input type="text" name="dateFinalAdd" id="dateFinalAdd" value="" dojoType="dijit.form.DateTextBox" maxlength="10" constraints={datePattern:'dd/MM/yyyy'} trim="true" required="false"/>
	   	</div>
		<div class="row">
			<input type="button" id="btnBuscar" value="Buscar"  class="boton" onclick="doSubmit('filter');" />
			<input type="reset" id="btnReset" value="Restablecer" class="boton" />
		</div>
		<div class="row">
			<div id="table"></div>
		</div>
	</div>
	<div id="message"></div>
</form>
</div>
<script>
	doSubmitPager('page');
</script>