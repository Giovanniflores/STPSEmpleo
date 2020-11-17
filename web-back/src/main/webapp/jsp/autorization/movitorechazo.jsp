<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%String context = request.getContextPath() +"/";%>


<div class="entero">
	<p class="entero" style="text-align:left;">
	    <strong><label for="idMotivoRechazoSelect">Motivo</label>:</strong><br/>
		<input type="hidden" name="idMotivoRechazo" id="idMotivoRechazo"/>
		<div dojoType="dojo.data.ItemFileReadStore" jsId="motivosStore" url="${context}autorization.do?method=motivosCatalogo&idRegValidar=${autorizationForm.idRegValidar}"></div>
		<select dojotype="dijit.form.ComboBox" store="motivosStore" id="idMotivoRechazoSelect" required="true" missingMessage="Dato requerido"></select>
	</p>
	<p class="entero" style="text-align:left;">
	    <strong>Detalle:</strong><br/>
	    <textarea id="motivoRechazoText" name="motivoRechazoText" dojoType="dijit.form.Textarea" required="true" 
				  style="height:200px;width:200px;overflow-y:hidden;overflow-x:auto; -webkit-box-sizing:border-box;" trim="true">
	    </textarea>
	    		
		<div dojoType="dijit.Tooltip"
			connectId="motivoRechazoText"
			label="Dato requerido"
			position="above">
		</div>
	    
	</p>
	<br/>
	<br/>
	<p class="entero" style="text-align:center;">
		<input type="button" value="Eliminar registro" class="boton" onclick="javascript:rechazarRegistro(${autorizationForm.idRegValidar});"/>
    	<input type="button" value="Cancelar" class="boton" onclick="javascript:closeWindowRechazo();"/>
	</p>
</div>

