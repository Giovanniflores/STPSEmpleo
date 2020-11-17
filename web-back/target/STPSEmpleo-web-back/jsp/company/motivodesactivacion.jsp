<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%String context = request.getContextPath() +"/";%>

<div class="entero">
	<p class="entero" style="text-align:left;">
	    <strong>*Motivo de desactivación:</strong><br/>
		<input type="hidden" name="idMotivoDesactivacion" id="idMotivoDesactivacion"/>
		<div dojoType="dojo.data.ItemFileReadStore" jsId="motivosStore" 
			url="${context}filtercompany.do?method=motivosDesactivacionCatalogo"></div>
		<select dojotype="dijit.form.ComboBox" store="motivosStore" 
			id="idMotivoDesactivacionSelect" required="true" missingMessage="Dato requerido"></select>	
	</p>
	<p class="entero" style="text-align:left;">
	    <strong>Descripción:</strong><br/>
       	<textarea id="motivoDesactivacionText" name="motivoDesactivacionText"  
    			onkeypress="return caracteresValidos(event);"
    			trim="true" onchange="return maximaLongitud(this,50)" onKeyUp="return maximaLongitud(this,50)"
    			maxlength="50" required="false" style="height:200px;width:200px;overflow-y:hidden;overflow-x:auto; -webkit-box-sizing:border-box;"     			 
    			regExp="^[\w\d\s\.&aacute;&eacute;&iacute;&oacute;&uacute;&Aacute;&Eacute;&Iacute;&Oacute;&Uacute;&Ntilde;&ntilde;]{1,50}$" >
    	</textarea>
	    		
		<div dojoType="dijit.Tooltip"
			connectId="idMotivoDesactivacion"
			label="Dato requerido"
			position="above">
		</div>
	</p>	
	<br/>
	<br/>
	<p class="entero" style="text-align:center;">
		<input type="button" value="Desactivar" class="boton" onclick="javascript:deactivateCompany(${FilterCompanyForm.idEmpresaADesactivar});"/>
    	<input type="button" value="Cancelar" class="boton" onclick="javascript:closeWindowDesactivacion();"/>	
	</p>
		
</div>