<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%String context = request.getContextPath() +"/";%>
<style type="text/css">
	.redFont{
		color: #FF0000;
	}
	#transferenciaForm .row {
		margin-top: 30px;
		padding-bottom: 10px;
		border-bottom: 1px solid #cccccc;
	}
</style>
<script type="text/javascript">
	dojo.require("dijit.dijit"); // loads the optimized dijit layer
	dojo.require("dijit.form.Form");
	dojo.require("dijit.form.Button");
	dojo.require("dijit.form.DateTextBox");
	
	function doSubmit(method){
		
		if(dijit.byId('fecha').isValid()){
			
			var answer = confirm("¿Desea iniciar el proceso de transferencia de las ofertas de SFP?");
			  if (!answer) return;
			
			document.getElementById('btnTransferencia').disabled='disabled';

			document.transferenciaForm.method.value=method;
			document.transferenciaForm.submit();

			//dijit.byId('fecha').setAttribute('disabled', true);
		}else{
			alert('La fecha de transferencia es requerida.');
		}
  	}
</script>
<noscript>Funciones de JavaScript desactivadas por navegador</noscript>

<form name="transferenciaForm" id="transferenciaForm" method="post" action="transferenciasfp.do" dojoType="dijit.form.Form">
	<input type="hidden" name="method" id="method" value="init"/>

	<h3>Transferencia de ofertas externas - SFP</h3>

	<div class="row">
		<strong>*<label for="fecha">Fecha de ofertas a transferir</label>:</strong>
		<br/>
		<input id="fecha" name="fecha" dojoType="dijit.form.DateTextBox" maxlength="10" required="true"  missingMessage="Fecha requerida" value=""
			   constraints="{datePattern:'dd/MM/yyyy'}" />
		&nbsp;&nbsp;
		<input type="button" id="btnTransferencia" name="btnTransferencia"
		       value="Inicia transferencia" class="boton" onclick="doSubmit('transferencia');"/>
	</div>

 	<div>
        <p class="entero">
        	<html:messages id="errors"><span class="redFont Font80"><bean:write name="errors"/></span><br/></html:messages>
			<html:messages id="messages" message="true"><span class="Font80"><bean:write name="messages"/></span><br/></html:messages>
        </p>
        
        <c:if test="${not empty totales}">
        <br/>
        <div>
	        <p class="row">
	            <label for="text1"><strong>[${totales.totalOfertasConsultadas}]&nbsp;&nbsp;</strong></label>Ofertas de Función Publica a procesar
	        </p>
	        <p class="row">
	            <label for="text1"><strong>[${totales.totalOfertasAgregadas}]&nbsp;&nbsp;</strong></label>Ofertas externas agregadas
	        </p>
	        <p class="row">
	            <label for="text1"><strong>[${totales.totalOfertasModificadas}]&nbsp;&nbsp;</strong></label>Ofertas externas modificadas
	        </p>
        </div>
        </c:if>
	</div>
</form>