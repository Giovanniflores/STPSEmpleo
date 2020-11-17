<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:forEach var="conocimiento" items="${ofertaEmpleoForm.conocimientosAdicionales}" varStatus="status">
			<input type="hidden" id="idConocimientoAdicional_${status.count}" name="idConocimientoAdicional_${status.count}" value="${conocimiento.idOfertaRequisito}"/>
			<div class="margin_top_20">
				<div class="grid1_3 fl">
					<label for="conocAdicional_${status.count}">Conocimiento</label>
					<input type="hidden" id="conocimientoAdicional_${status.count}" name="conocimientoAdicional_${status.count}" value="${conocimiento.descripcion}" />
					<input type="text" 
					      size="150" maxlength="50" trim="true"
		                   dojoType="dijit.form.ValidationTextBox"
		                   id="conocAdicional_${status.count}"
						   required="false" missingMessage="Debe indicar el conocimiento." 
						   value="${conocimiento.descripcion}"
						    onchange="actualizaValorAd('conocimientoAdicional_${status.count}',this.value)"/>
				</div>
				<div class="grid1_3 fl">
					<label for="selectConocimientoExperiencia_${status.count}">Experiencia</label>
					<input type="hidden" id="idConocimientoExperiencia_${status.count}" name="idConocimientoExperiencia_${status.count}"  value="${conocimiento.idExperiencia}"/>
					<select 
				        required="false" missingMessage="Es necesario indicar los años de experiencia del conocimiento."
				        id="selectConocimientoExperiencia_${status.count}"
				        store="experienciaTotalStore" dojotype="dijit.form.FilteringSelect"
				        value="${conocimiento.idExperiencia}"
				        onchange="actualizaValorAd('idConocimientoExperiencia_${status.count}',this.value)">
					</select>
				</div>
				<div class="clearfix"></div>
			</div>
			<p class="eliminar_bloque agregar_bloque">
				<input type="button" class="eliminar" onclick="javascript:eliminarConocimiento('${conocimiento.descripcion}');" value="Eliminar" title="Eliminar conocimiento"/>
			</p>
		    </c:forEach>
		 	<c:if test="${ofertaEmpleoForm.totalConocimientosAdicionales<2}">
			 	<p class="agregar_bloque" id="bAgregarConocimiento" style="display: block; left: 730px;">
			 		<input type="button" class="agregar" onclick="javascript:toggleConocimientos();" value="Agregar otro conocimiento" title="Agregar otro conocimiento"/>
			 	</p>
			</c:if>
			<div class="entero">
				<p></p>		    						
			</div>
