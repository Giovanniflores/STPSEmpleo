<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ page import="mx.gob.stps.portal.web.infra.utils.Utils"%>
<%@ page import="mx.gob.stps.portal.web.autorization.form.AutorizationForm"%>

<%
	String context = request.getContextPath() +"/";
	AutorizationForm autorizationForm = (AutorizationForm)session.getAttribute("autorizationForm");
%>

<div>
		<table class="offer">
			<tr>
				<td colspan="4"><h2>Perfil de la empresa</h2></td>
			</tr>
			<tr>
				<td>Fecha de registro a empresa fraudulenta</td>				
				<td><strong><fmt:formatDate value="${autorizationForm.empresaFraudulenta.fechaDeteccionFraude}"  pattern="dd/MM/yyyy hh:mm" /></strong></td>
			  	<td>Usuario que detectó</td>
			  	<td><strong>${autorizationForm.empresaFraudulenta.nombreUsuarioDetectorFraude}</strong></td>				
			</tr>			
			<tr>
				<td>Fuente</td>
				<td colspan="3" ><strong>Portal</strong></td>
			</tr>				
			<tr>
				<td colspan="4"><h2>Perfil de la empresa</h2></td>
			</tr>			
			<tr>
				<td>Nombre de la empresa</td>
				<c:choose>
				    <c:when test="${not empty autorizationForm.empresaFraudulenta.nombre}">
				        <td colspan="3" ><strong>${autorizationForm.empresaFraudulenta.nombre}&nbsp; 
				        ${autorizationForm.empresaFraudulenta.apellido1}&nbsp; ${autorizationForm.empresaFraudulenta.apellido2}</strong></td>
				    </c:when>
				    <c:otherwise>
				        <td colspan="3" ><strong>${autorizationForm.empresaFraudulenta.razonSocial}</strong></td>
				    </c:otherwise>
				</c:choose>	
			</tr>							
			<tr>
				<td>ID del Portal del Empleo</td>
				<td ><strong>${autorizationForm.empresaFraudulenta.idPortalEmpleo}</strong></td>
				<td>ID de la empresa</td>
				<td ><strong>${autorizationForm.empresaFraudulenta.idEmpresa}</strong></td>
			</tr>
			  <tr>
			  	<td>Tipo de persona</td>
				<c:choose>
				    <c:when test="${not empty autorizationForm.empresaFraudulenta.nombre}">
				        <td ><strong>Física</strong></td>
				    </c:when>
				    <c:otherwise>
				        <td ><strong>Moral</strong></td>
				    </c:otherwise>
				</c:choose>	
				<c:choose>
				    <c:when test="${not empty autorizationForm.empresaFraudulenta.nombre}">
				    	<td>Fecha de nacimiento:</td>
				        <td ><strong><fmt:formatDate value="${autorizationForm.empresaFraudulenta.fechaNacimiento}"  pattern="dd/MM/yyyy hh:mm" /></strong></td>				        
				    </c:when>
				    <c:otherwise>
				    <td>Fecha de acta constitutiva:</td>
				        <td ><strong><fmt:formatDate value="${autorizationForm.empresaFraudulenta.fechaActa}"  pattern="dd/MM/yyyy hh:mm" /></strong></td>				        
				    </c:otherwise>
				</c:choose>	
			 </tr>				 
			 	
			<tr>
				<td>Descripción de la empresa</td>
				<td colspan=3><strong>${autorizationForm.empresaFraudulenta.descripcion}</strong></td>
			</tr>
			<tr>	
				<td>Nombre del representante de la empresa</td>
				<td><strong>${autorizationForm.empresaFraudulenta.contactoEmpresa}</strong></td>
			</tr>
			<tr>
				<td>Tipo de empresa</td>
				<td><strong>${autorizationForm.empresaFraudulenta.tipoEmpresa}</strong></td>
				<td>Actividad económica principal</td>
				<td><strong>${autorizationForm.empresaFraudulenta.actividadEconomica}</strong></td>
			</tr>
			<tr>
				<td>Número de empleados</td>
				<td><strong>${autorizationForm.empresaFraudulenta.numeroEmpleados}</strong></td>
				<td>Correo electrónico</td>
				<td><strong>${autorizationForm.empresaFraudulenta.correoElectronico}</strong></td>
			</tr>			
			
			<tr>
				<td colspan="4"><h2>Domicilio de la empresa</h2></td>
			</tr>
			<tr>
				<td colspan="4" >
					<table class="offer">
						<tr>
							<td>Calle</td>
							<td><strong>${autorizationForm.empresaFraudulenta.domicilio.calle}</strong></td>
							<td>Número exterior</td>
							<td><strong>${autorizationForm.empresaFraudulenta.domicilio.numeroExterior}</strong></td>
							<td>Número interior</td>
							<td><strong>${autorizationForm.empresaFraudulenta.domicilio.numeroInterior}</strong></td>							
						</tr>
						<tr>
							<td>Entre calles</td>
							<td><strong>${autorizationForm.empresaFraudulenta.domicilio.entreCalle}</strong></td>
							<td>Y calle</td>
							<td><strong>${autorizationForm.empresaFraudulenta.domicilio.yCalle}</strong></td>		
							<td>Código postal</td>
							<td><strong>${autorizationForm.empresaFraudulenta.domicilio.codigoPostal}</strong></td>																									
						</tr>					
						<tr>
							<td>Entidad federativa</td>
							<td><strong>${autorizationForm.empresaFraudulenta.domicilio.entidad}</strong></td>
							<td>Municipio</td>
							<td><strong>${autorizationForm.empresaFraudulenta.domicilio.municipio}</strong></td>
							<td>Colonia</td>
							<td><strong>${autorizationForm.empresaFraudulenta.domicilio.colonia}</strong></td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td colspan="4"><h2>Teléfono(s) de la empresa</h2></td>
			</tr>
			<tr>
				<td colspan="4">
					<table class="offer">
						<tr>
							<th>Tipo de teléfono</th>
							<th>Acceso</th>
							<th>Clave Lada</th>
							<th>Teléfono</th>
							<th>Extensión</th>
						</tr>

						<c:forEach var="telefono" items="${autorizationForm.empresaFraudulenta.telefonos}" varStatus="rowPhoneCounter">
					 		<c:if test="${rowPhoneCounter.count <= 10 }">
						 		<tr <c:out value="${rowPhoneCounter.count % 2 == 0?'':'class=odd'}"/>>		 								 		
									<c:choose>
									    <c:when test="${telefono.idTipoTelefono == 1}">
									        <td ><strong>Celular</strong></td>
									    </c:when>
									    <c:otherwise>
									        <td ><strong>Fijo</strong></td>
									    </c:otherwise>
									</c:choose>		 								 											
									<td><strong>${telefono.acceso}</strong></td>
									<td><strong>${telefono.clave}</strong></td>
									<td><strong>${telefono.telefono}</strong></td>
									<td><strong>${telefono.extension}</strong></td>
								</tr>	
							</c:if>						
						</c:forEach>
						
					</table>
				</td>
			</tr>
		</table>

		<p style="text-align:center">
        	<input type="button" value="Regresar" class="boton" onclick="javascript:closeWindowEmpFraudulenta();"/>
        </p>
</div>