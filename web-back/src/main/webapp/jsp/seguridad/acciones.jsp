<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<table cellspacing="0" cellpadding="0" border="0" width="100%">
<tbody>
	<tr class="temas">
		<th>Acceso</th>
    	<th>Vinculo</th>
    	<th><input type="checkbox" onclick="javascript:turnChecks('accionopcion', this.checked);" id="checkPermisos"/><label for="checkPermisos">Permisos asignado</label></th>
    	<%--<th><input type="checkbox" onclick="javascript:turnChecks('autentica', this.checked);"/>Requiere usuario autenticado</th>--%>
    </tr>

	<c:forEach var="accion" items="${ACCIONES}" varStatus="index">
	<tr <c:out value="${index.count % 2 == 0?'':'class=odd'}"/>>
		<td><p>${accion.descripcion}</p></td>
		<td><p>${accion.vinculo}</p></td>
		<td>
			<c:if test="${accion.asignado == 0}">
				<input type="checkbox" name="accionopcion" id="accionopcion" value="${accion.idAccion}" />
			</c:if>
			<c:if test="${accion.asignado == 1}">
				<input type="checkbox" name="accionopcion" id="accionopcion" value="${accion.idAccion}" checked="checked" />
			</c:if>			
		</td>
		<%--<td>
			
			<c:if test="${accion.autenticado == 0}">
				<input type="checkbox" name="autentica" id="autentica" value="${accion.idAccion}" />
			</c:if>
			<c:if test="${accion.autenticado == 1}">
				<input type="checkbox" name="autentica" id="autentica" value="${accion.idAccion}" checked="checked" />
			</c:if>
		</td>--%>
	</tr>
    </c:forEach>
</tbody>
</table>