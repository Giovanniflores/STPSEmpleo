<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<input type="hidden" id="totalCarrerasAdicionales" name="totalCarrerasAdicionales"  value="${ofertaEmpleoForm.totalCarrerasAdicionales}"  />

<c:forEach var="carrera" items="${ofertaEmpleoForm.carrerasAdicionales}" varStatus="status">
	<div>
		<input type="hidden" id="idOfertaCarreraAdicional_${status.count}" name="idOfertaCarreraAdicional_${status.count}" value="${carrera.idRegistro}"/>
		<input type="hidden" id="idCarreraAdicional_${status.count}"       name="idCarreraAdicional_${status.count}"       value="${carrera.id}"  />
		<label>Carrera o especialidad</label>
		<select required="true" missingMessage="Es necesario indicar la carrera o especialidad." 
		        dojotype="dijit.form.FilteringSelect" value="${carrera.id}"  
		        maxHeight="250" 
		        onchange="actualizaValorAd('idCarreraAdicional_${status.count}',this.value)"> 
				<c:forEach var="carreraM" items="${ofertaEmpleoForm.carrerasMultiRegistro}">
						<c:choose>
							<c:when test="${carreraM.idCatalogoOpcion eq carrera.id}">
								<option value="${carreraM.idCatalogoOpcion}"  selected>${carreraM.opcion}</option>
							</c:when>
							<c:otherwise>
								<option value="${carreraM.idCatalogoOpcion}">${carreraM.opcion}</option>
							</c:otherwise>
						</c:choose>											
				</c:forEach>		        		        
		</select> 
		<p class="eliminar_bloque bis">
			<input type="button" class="eliminar" onclick="javascript:eliminarCarrera('${carrera.id}');" value="Eliminar" title="Eliminar carrera" />
		</p>
	</div>
</c:forEach>
		
<c:if test="${ofertaEmpleoForm.totalCarrerasAdicionales<2}">
		<p class="agregar_bloque cancunilo" id="bAgregarCarrera" style="display: block;">
			<input type="button" class="agregar" onclick="javascript:toggle();" value="Agregar otra carrera o especialidad" title="Agregar otra carrera o especialidad" />
		</p>
</c:if>			

