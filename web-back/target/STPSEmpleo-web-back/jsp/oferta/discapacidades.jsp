<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

					<div class="labeled">Tipo de discapacidad que acepta</div>		
					<div>Puedes seleccionar más de una opción</div><br>		
					<ul class="diasLaborales">
						<li>
							<c:if test="${ofertaEmpleoForm.discapacidadAuditiva==1}">
								<input type="checkbox" name="discapacidadAuditivaC" id="discapacidadAuditivaC" value="1" onclick="javascript:limpiaDiscapacidades(1)" checked="checked" >
							</c:if>
							<c:if test="${ofertaEmpleoForm.discapacidadAuditiva!=1}">
								<input type="checkbox" name="discapacidadAuditivaC" id="discapacidadAuditivaC" value="1" onclick="javascript:limpiaDiscapacidades(1)" >
							</c:if>	
							<label for="discapacidadAuditivaC">Auditiva</label>					
						</li>
						<li>
							<c:if test="${ofertaEmpleoForm.discapacidadIntelectual==1}">
								<input type="checkbox" name="discapacidadIntelectualC" id="discapacidadIntelectualC" value="1" onclick="javascript:limpiaDiscapacidades(1)" checked="checked" >
							</c:if>
							<c:if test="${ofertaEmpleoForm.discapacidadIntelectual!=1}">
								<input type="checkbox" name="discapacidadIntelectualC" id="discapacidadIntelectualC" value="1" onclick="javascript:limpiaDiscapacidades(1)" >
							</c:if>	
							<label for="discapacidadIntelectualC">Intelectual</label>					
						</li>
						<li>
							<c:if test="${ofertaEmpleoForm.discapacidadMental==1}">
								<input type="checkbox" name="discapacidadMentalC" id="discapacidadMentalC" value="1" onclick="javascript:limpiaDiscapacidades(1)" checked="checked" >
							</c:if>
							<c:if test="${ofertaEmpleoForm.discapacidadMental!=1}">
								<input type="checkbox" name="discapacidadMentalC" id="discapacidadMentalC" value="1" onclick="javascript:limpiaDiscapacidades(1)" >
							</c:if>	
							<label for="discapacidadMentalC">Mental</label>					
						</li>
						<li>
							<c:if test="${ofertaEmpleoForm.discapacidadMotriz==1}">
								<input type="checkbox" name="discapacidadMotrizC" id="discapacidadMotrizC" value="1" onclick="javascript:limpiaDiscapacidades(1)" checked="checked" >
							</c:if>
							<c:if test="${ofertaEmpleoForm.discapacidadMotriz!=1}">
								<input type="checkbox" name="discapacidadMotrizC" id="discapacidadMotrizC" value="1" onclick="javascript:limpiaDiscapacidades(1)" >
							</c:if>	
							<label for="discapacidadMotrizC">Motriz</label>					
						</li>
						<li>
							<c:if test="${ofertaEmpleoForm.discapacidadVisual==1}">
								<input type="checkbox" name="discapacidadVisualC" id="discapacidadVisualC" value="1" onclick="javascript:limpiaDiscapacidades(1)" checked="checked" >
							</c:if>
							<c:if test="${ofertaEmpleoForm.discapacidadVisual!=1}">
								<input type="checkbox" name="discapacidadVisualC" id="discapacidadVisualC" value="1" onclick="javascript:limpiaDiscapacidades(1)" >
							</c:if>	
							<label for="discapacidadVisualC">Visual</label>					
						</li>
						<li>
							<c:if test="${ofertaEmpleoForm.discapacidadNinguna==1}">
								<input type="checkbox" name="discapacidadNingunaC" id="discapacidadNingunaC" value="1" onclick="javascript:limpiaDiscapacidades(0)" checked="checked" >
							</c:if>
							<c:if test="${ofertaEmpleoForm.discapacidadNinguna!=1}">
								<input type="checkbox" name="discapacidadNingunaC" id="discapacidadNingunaC" value="1" onclick="javascript:limpiaDiscapacidades(0)" >
							</c:if>	
							<label for="discapacidadNingunaC" id="discapacidadNingunaL">Ninguna</label>					
						</li>																									
					</ul>				
