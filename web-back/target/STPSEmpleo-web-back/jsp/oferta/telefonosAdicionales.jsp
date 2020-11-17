<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<noscript>Funciones de JavaScript desactivadas por navegador</noscript>
<c:forEach var="telefono" items="${ofertaEmpleoForm.telefonosAdicionales}" varStatus="status">
	<input type="hidden" id="idTelefonoAdicional_${status.count}" name="idTelefonoAdicional_${status.count}"value="${telefono.idTelefono}"/>
	<input type="hidden" id="idTipoTelefonoAdicional_${status.count}" name="idTipoTelefonoAdicional_${status.count}"  value="${telefono.idTipoTelefono}"  />
	<input type="hidden" id="accesoTelefonoAdicional_${status.count}" name="accesoTelefonoAdicional_${status.count}"  value="${telefono.acceso}"  />
	<input type="hidden" id="claveTelefonoAdicional_${status.count}" name="claveTelefonoAdicional_${status.count}"  value="${telefono.clave}"  />
	<input type="hidden" id="telefonoAdicional_${status.count}" name="telefonoAdicional_${status.count}"  value="${telefono.telefono}"  />
	<input  type="hidden" id="extensionTelefonoAdicional_${status.count}" name="extensionTelefonoAdicional_${status.count}"  value="${telefono.extension}"  />
	<div style="clear:both">
		<div class="grid1_3  margin_top_20 fl">
			<c:if test="${telefono.idTipoTelefono==ofertaEmpleoForm.idTipoTelefonoFijo}">
				<div class="margin-r_20 fl">
					<div class="labeled margin_no_t"><span>*</span> Tipo de teléfono</div>
					<div class="tipo_tel margin_top_10 fl" style="margin-right:5px">
						<input class="fl" type="radio" name="idTipoTelefonoAdd_${status.count}" id="idTipoTelefonoAdd_${status.count}" checked="checked" 
							       value="${ofertaEmpleoForm.idTipoTelefonoFijo}" onclick="javascript:estableceAccesoAdd('accesoTelefonoAdicional_${status.count}','idTipoTelefonoAdicional_${status.count}',${status.count},'idTipoTelefonoAdd_${status.count}',5,'extensionDivAddFijo_${status.count}','extensionDivAddCelular_${status.count}');actualizaValorAd('idTipoTelefonoAdicional_${status.count}',this.value);">
						<label class="fl" for="idTipoTelefonoAdd_${status.count}">Fijo</label>
					</div>
					<div class="tipo_tel margin_top_10 fl">
						<input class="fl" type="radio" name="idTipoTelefonoAdd_${status.count}" id="idTipoTelefonoAdd_${status.count}"
					    	   		value="${ofertaEmpleoForm.idTipoTelefonoCelular}" onclick="javascript:estableceAccesoAdd('accesoTelefonoAdicional_${status.count}','idTipoTelefonoAdicional_${status.count}',${status.count},'idTipoTelefonoAdd_${status.count}',1,'extensionDivAddFijo_${status.count}','extensionDivAddCelular_${status.count}');actualizaValorAd('idTipoTelefonoAdicional_${status.count}',this.value);">
						<label class="fl" for="idTipoTelefonoAdd_${status.count}">Celular</label>
					</div>			
				</div>
			</c:if>	
			<c:if test="${telefono.idTipoTelefono==ofertaEmpleoForm.idTipoTelefonoCelular}">	
				<div class="margin-r_20 fl">
					<div class="labeled margin_no_t"><span>*</span> Tipo de teléfono</div>
					<div class="tipo_tel margin_top_10 fl" style="margin-right:5px">
						<input class="fl" type="radio" name="idTipoTelefonoAdd_${status.count}" id="idTipoTelefonoAdd_${status.count}" 
					      		 value="${ofertaEmpleoForm.idTipoTelefonoFijo}" onclick="javascript:estableceAccesoAdd('accesoTelefonoAdicional_${status.count}','idTipoTelefonoAdicional_${status.count}',${status.count},'idTipoTelefonoAdd_${status.count}',5,'extensionDivAddFijo_${status.count}','extensionDivAddCelular_${status.count}');actualizaValorAd('idTipoTelefonoAdicional_${status.count}',this.value);">
						<label class="fl" for="idTipoTelefonoAdd_${status.count}">Fijo</label>
					</div>
					<div class="tipo_tel margin_top_10 fl">
						<input class="fl" type="radio" name="idTipoTelefonoAdd_${status.count}" id="idTipoTelefonoAdd_${status.count}" checked="checked" 
				    	  		 value="${ofertaEmpleoForm.idTipoTelefonoCelular}" onclick="javascript:estableceAccesoAdd('accesoTelefonoAdicional_${status.count}','idTipoTelefonoAdicional_${status.count}',${status.count},'idTipoTelefonoAdd_${status.count}',1,'extensionDivAddFijo_${status.count}','extensionDivAddCelular_${status.count}');actualizaValorAd('idTipoTelefonoAdicional_${status.count}',this.value);">
				    	<label class="fl" for="idTipoTelefonoAdd_${status.count}">Celular</label>
					</div>
				</div>
			</c:if>	
			<div class="margin-r_10 fl">
				<label class="fw_no_bold" for="accesoDivAdd_${status.count}"><span>*</span> Acceso</label>
				<div class="ta_center margin_top_10">
					<span id="accesoDivAdd_${status.count}">
						${telefono.acceso}
					</span>
				</div>
			</div>
			<div class="fl">
				<label for="claveTelefonoAdd_${status.count}"><span>*</span> Clave LADA</label>
				<input id="claveTelefonoAdd_${status.count}" name="claveTelefonoAdd_${status.count}" value="${telefono.clave}"
					dojoType="dijit.form.ValidationTextBox"	maxlength="3" trim="true" required="true" style="width: 3em"
					missingMessage="Es necesario indicar la clave lada." regExp="^[0-9]{2,3}$" invalidMessage="La clave lada es invalida."
					onchange="javascript:actualizaValorAd('claveTelefonoAdicional_${status.count}',this.value);"/>
			</div>
		</div>
		<div class="margin_top_20 fl">
			<label for="telefonoAdd_${status.count}"><span>*</span> Teléfono</label>
			<input id="telefonoAdd_${status.count}" name="telefonoAdd_${status.count}" value="${telefono.telefono}"
	        	dojoType="dijit.form.ValidationTextBox"
	            maxlength="8" trim="true"
	            required="true" missingMessage="Es necesario indicar el numero telefonico."
	            regExp="^[0-9]{7,8}$" invalidMessage="El numero telefonico es invalido." 	
	            onchange="javascript:actualizaValorAd('telefonoAdicional_${status.count}',this.value);"/>
		</div>
		<div class="margin_top_20 fl">
			<label for="extensionTelefonoAdd_${status.count}">Extensión</label>
			<input type="text" id="extensionTelefonoAdd_${status.count}" name="extensionTelefonoAdd_${status.count}"
				value="${telefono.extension}" dojoType="dijit.form.ValidationTextBox"
				maxlength="8" trim="true" 
		        required="false" missingMessage="Es necesario indicar la extension."
		        regExp="^[0-9]{1,8}$" invalidMessage="La extension es invalida."
		        <c:if test="${telefono.idTipoTelefono==ofertaEmpleoForm.idTipoTelefonoCelular}">
		        	disabled="disabled" readonly="readonly"
		        </c:if>
		        onchange="javascript:actualizaValorAd('extensionTelefonoAdicional_${status.count}',this.value);javascript:validaExtensionAdd(this)"/>
		</div>
	    <div class="margin_top_50 margin-r_10 fl">
			<input type="button" class="eliminar" onclick="javascript:eliminarTelefono('${telefono.acceso}','${telefono.clave}','${telefono.telefono}','${telefono.extension}');" value="Eliminar teléfono" title="Eliminar teléfono"/>
	    </div>
	</div>
</c:forEach>
<c:if test="${ofertaEmpleoForm.totalTelefonosAdicionales<2}">
	<div class="margin_top_50 fl add_ph" id="bAgregarTelefono" style="display: block;">
		<input type="button" class="agregar" onclick="javascript:toggle();" value="Agregar otro teléfono" title="Agregar otro telefono"/>
	</div>
	<div class="clearfix"></div>
</c:if>
<c:if test="${ofertaEmpleoForm.totalTelefonosAdicionales>=2}">
	<div class="entero"></div>
</c:if>
