<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script type="text/javascript">
function toggleTelefono(){			
	if(dijit.byId('clave').get('value')=='' || dijit.byId('telefono').get('value')==''){
		message("Ingrese primero el teléfono principal");
	}else{			
		document.getElementById('telefonoAdicional').style.display = 'block';
		document.getElementById('bAgregarTelefono').style.display = 'none';			
	}
}	
</script>

<c:forEach var="telefono" items="${registroEmpresaForm.telefonosAdicionales}" varStatus="status">
	<input type="hidden" id="idTelefonoAdicional_${status.count}" 			name="idTelefonoAdicional_${status.count}"			value="${telefono.idTelefono}"/>
	<input type="hidden" id="idTipoTelefonoAdicional_${status.count}" 		name="idTipoTelefonoAdicional_${status.count}"  	value="${telefono.idTipoTelefono}"  />
	<input type="hidden" id="accesoTelefonoAdicional_${status.count}" 		name="accesoTelefonoAdicional_${status.count}"  	value="${telefono.acceso}"  />
	<input type="hidden" id="claveTelefonoAdicional_${status.count}" 		name="claveTelefonoAdicional_${status.count}"  		value="${telefono.clave}"  />
	<input type="hidden" id="telefonoAdicional_${status.count}" 			name="telefonoAdicional_${status.count}"  			value="${telefono.telefono}"  />
	<input type="hidden" id="extensionTelefonoAdicional_${status.count}" 	name="extensionTelefonoAdicional_${status.count}"  	value="${telefono.extension}"  />
	
	<div class="grid1_3  margin_top_20 fl">
		<c:if test="${telefono.idTipoTelefono==registroEmpresaForm.idTipoTelefonoFijo}">
			<div class="margin-r_20 fl">
			    <div class="labeled margin_no_t"><span>*</span> Tipo de teléfono</div>
				<div class="tipo_tel margin_top_10 fl" style="width:53px">
					<input type="radio" name="tipoTelefonoAdd_${status.count}" id="telefonoFijoAdd_${status.count}" checked="checked" 
				       value="${registroEmpresaForm.idTipoTelefonoFijo}" 
				       onclick="javascript:estableceAccesoAdd('accesoTelefonoAdicional_${status.count}','idTipoTelefonoAdicional_${status.count}',${status.count},'telefonoFijoAdd_${status.count}','telefonoCelularAdd_${status.count}','extensionDivAddFijo_${status.count}','extensionDivAddCelular_${status.count}');actualizaValorAd('idTipoTelefonoAdicional_${status.count}',this.value);">
				    <label for="telefonoFijoAdd_${status.count}" class="fl">Fijo</label>
			    </div>
				<div class="tipo_tel margin_top_10 fl" style="width:60px">
					<input type="radio" name="tipoTelefonoAdd_${status.count}" id="telefonoCelularAdd_${status.count}"
			    	   value="${registroEmpresaForm.idTipoTelefonoCelular}" 
			    	   onclick="javascript:estableceAccesoAdd('accesoTelefonoAdicional_${status.count}','idTipoTelefonoAdicional_${status.count}',${status.count},'telefonoFijoAdd_${status.count}','telefonoCelularAdd_${status.count}','extensionDivAddFijo_${status.count}','extensionDivAddCelular_${status.count}');actualizaValorAd('idTipoTelefonoAdicional_${status.count}',this.value);">
			    	<label for="telefonoCelularAdd_${status.count}" class="fl">Celular</label>
		    	</div>
			</div>
		</c:if>
		<c:if test="${telefono.idTipoTelefono==registroEmpresaForm.idTipoTelefonoCelular}">
			<div class="margin-r_20 fl">
				<div class="labeled margin_no_t"><span>*</span> Tipo de teléfono</div>
				<div class="tipo_tel margin_top_10 fl" style="width:53px">
					<input type="radio" name="tipoTelefonoAdd_${status.count}" id="telefonoFijoAdd_${status.count}" 
				       value="${registroEmpresaForm.idTipoTelefonoFijo}" onclick="javascript:estableceAccesoAdd('accesoTelefonoAdicional_${status.count}','idTipoTelefonoAdicional_${status.count}',${status.count},'telefonoFijoAdd_${status.count}','telefonoCelularAdd_${status.count}','extensionDivAddFijo_${status.count}','extensionDivAddCelular_${status.count}');actualizaValorAd('idTipoTelefonoAdicional_${status.count}',this.value);">
				    <label for="telefonoFijoAdd_${status.count}" class="fl">Fijo</label>
				</div>
				<div class="tipo_tel margin_top_10 fl" style="width:60px">
					<input type="radio" name="tipoTelefonoAdd_${status.count}" id="telefonoCelularAdd_${status.count}" checked="checked" 
			    	   value="${registroEmpresaForm.idTipoTelefonoCelular}" onclick="javascript:estableceAccesoAdd('accesoTelefonoAdicional_${status.count}','idTipoTelefonoAdicional_${status.count}',${status.count},'telefonoFijoAdd_${status.count}','telefonoCelularAdd_${status.count}','extensionDivAddFijo_${status.count}','extensionDivAddCelular_${status.count}');actualizaValorAd('idTipoTelefonoAdicional_${status.count}',this.value);">
			    	<label for="telefonoFijoAdd_${status.count}" class="fl">Celular</label>	
				</div>  
			</div>  				
		</c:if>
		
		<div class="margin-r_10 fl">
			<label class="fw_no_bold"><span>*</span> Acceso</label>
			<div class="ta_center margin_top_10">
		        <span id="accesoDivAdd_${status.count}">
					${telefono.acceso}
				</span>
			</div>
		</div>
		
	    <div class="fl">
	    	<label><span>*</span>Clave LADA</label>
			<input type="text" dojoType="dijit.form.ValidationTextBox"
				maxlength="3" style="width:97px;" trim="true"
	  			missingMessage="clave lada."
				regExp="^[0-9]{2,3}$" invalidMessage="clave lada es invalida."
	 			value="${telefono.clave}"
				onchange="javascript:actualizaValorAd('claveTelefonoAdicional_${status.count}',this.value);"/>
		</div>
	</div>

	<div class="margin_top_20 fl">
		<label for="widget_dijit_form_ValidationTextBox_1"><span>*</span>Teléfono</label>
    	<input type="text" 
            dojoType="dijit.form.ValidationTextBox"
           style="width:137px;margin-right:20px;" trim="true"
            required="true" missingMessage="número telefónico."
            invalidMessage="número telefónico es inválido." 
            value="${telefono.telefono}"
            maxlength="8" regExp="^[0-9]{7,8}$"	
            onchange="javascript:actualizaValorAd('telefonoAdicional_${status.count}',this.value);"/>	
	</div>
	
	<div class="margin_top_20 fl">
   		<c:if test="${telefono.idTipoTelefono==registroEmpresaForm.idTipoTelefonoFijo}">
           			
 					<div id="extensionDivAddFijo_${status.count}" style="display: block;">
	               		<label>Extensión</label>
	               		<input type="text" 
	                   dojoType="dijit.form.ValidationTextBox"
	                    style="width:136px;margin-right:20px" trim="true" 
	               	   required="false" missingMessage="Es necesario indicar la extension."
	               	    invalidMessage="La extension es invalida."
	               	   value="${telefono.extension}"
	               	   maxlength="8" regExp="^[0-9]{1,8}$"
	               	   onchange="javascript:actualizaValorAd('extensionTelefonoAdicional_${status.count}',this.value);javascript:validaExtensionAdd(this)"/>
               	   </div>
               	   
               	   
               	   <div id="extensionDivAddCelular_${status.count}" style="display: none;">
	               		<label>Extensión</label>
	               		<input type="text" 
	                   dojoType="dijit.form.ValidationTextBox"
	                    style="width:136px;margin-right:20px" trim="true" 
	               	   required="false" missingMessage="Es necesario indicar la extension."
	               	    invalidMessage="La extension es invalida."
	               	   value=""
	               	   disabled="disabled"
	               	   readonly="readonly"
	               	   maxlength="8" regExp="^[0-9]{1,8}$"
	               	   onchange="javascript:actualizaValorAd('extensionTelefonoAdicional_${status.count}',this.value);javascript:validaExtensionAdd(this)"/>
               	   </div>
               	   
		</c:if>
		<c:if test="${telefono.idTipoTelefono==registroEmpresaForm.idTipoTelefonoCelular}">
               	  
 					<div id="extensionDivAddFijo_${status.count}" style="display: none;">
	               		 <label>Extensión</label>
	               		<input type="text" 
	                   dojoType="dijit.form.ValidationTextBox"
	                    style="width:136px;margin-right:20px" trim="true" 
	               	   required="false" missingMessage="Es necesario indicar la extension."
	               	    invalidMessage="La extension es invalida."
	               	   value=""
	               	   maxlength="8" regExp="^[0-9]{1,8}$"
	               	   onchange="javascript:actualizaValorAd('extensionTelefonoAdicional_${status.count}',this.value);javascript:validaExtensionAdd(this)"/>
               	   </div>
               	   
               	   <div id="extensionDivAddCelular_${status.count}" style="display: block;">
	               		<label>Extensión</label>
	               		<input type="text" 
	                   dojoType="dijit.form.ValidationTextBox"
	                    style="width:width:136px;margin-right:20px;" trim="true" 
	               	   required="false" missingMessage="Es necesario indicar la extension."
	               	    invalidMessage="La extension es invalida."
	               	   value=""
	               	  readonly="readonly"
	               	   disabled="disabled"
	               	   maxlength="8" regExp="^[0-9]{1,8}$"
	               	   onchange="javascript:actualizaValorAd('extensionTelefonoAdicional_${status.count}',this.value);javascript:validaExtensionAdd(this)"/>
               	   </div>
		</c:if>
	</div>	
	<div class="eliminar_bloque">
		<input type="button" class="eliminar" onclick="javascript:eliminarTelefono('${telefono.acceso}','${telefono.clave}','${telefono.telefono}','${telefono.extension}');" value="Eliminar" />
	</div>
</c:forEach>

<c:if test="${registroEmpresaForm.totalTelefonosAdicionales<2}">
 	 <div class="margin_top_50 fl add_ph" id="bAgregarTelefono" style="display: block;">
 	 	<input type="button" class="agregar" onclick="toggleTelefono();" value="Agregar otro teléfono" />
 	 </div>
</c:if>
<div class="entero">   						
</div>


	
	