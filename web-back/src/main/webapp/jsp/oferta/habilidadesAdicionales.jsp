<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:forEach var="habilidad" items="${ofertaEmpleoForm.habilidadesAdicionales}" varStatus="status">
			<input type="hidden" id="idHabilidadAdicional_${status.count}" name="idHabilidadAdicional_${status.count}" value="${habilidad.idOfertaRequisito}"/>
			  <p class="un_tercio fl"><strong><label for="habAdicional_${status.count}">Habilidad Adicional</label></strong><br />
			<input type="hidden" id="habilidadAdicional_${status.count}" name="habilidadAdicional_${status.count}"  value="${habilidad.descripcion}"/>
			<input type="text" 
			      size="150" maxlength="50" trim="true" style="width: 250px"
                   dojoType="dijit.form.ValidationTextBox"
                   id="habAdicional_${status.count}" 
				   required="false" missingMessage="Debe indicar la habilidad." 
				   value="${habilidad.descripcion}"
				   onchange="actualizaValorAd('habilidadAdicional_${status.count}',this.value)"/></p>

		<p class="un_tercio fl"><strong><label for="selectHabExperiencia_${status.count}">Experiencia</label></strong><br />
		<input type="hidden" id="idHabilidadExperiencia_${status.count}" name="idHabilidadExperiencia_${status.count}" value="${habilidad.idExperiencia}"/>
			<select required="false" missingMessage="Es necesario indicar los años de experiencia de la habilidad."
			        id="selectHabExperiencia_${status.count}"
			        store="experienciaTotalStore" dojotype="dijit.form.FilteringSelect"
			        value="${habilidad.idExperiencia}" 
			        onchange="actualizaValorAd('idHabilidadExperiencia_${status.count}',this.value)">
			</select></p>
			<p class="eliminar_bloque">
			<input type="button" class="eliminar" onclick="javascript:eliminarHabilidad('${habilidad.descripcion}');" value="Eliminar" title="Eliminar habilidad"/>
			</p>
		 
		    <br clear="all" />
		    </c:forEach>
 	<c:if test="${ofertaEmpleoForm.totalHabilidadesAdicionales<2}">
 	<p class="agregar_bloque" id="bAgregarHabilidad" style="display: block;">
 	<input type="button" class="agregar" onclick="javascript:toggleHabilidad();" value="Agregar" title="Agregar habilidad"/>
 	</p>
</c:if>
		<div class="entero">
	    						
		</div>
