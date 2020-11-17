<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<table cellspacing="0" cellpadding="0" border="0" width="70%">
	<tbody>
		<c:if test="${not empty calList}">
			<tr class="temas">
				<th>Telefono</th>
				<th>Llamadas</th>
				<th> </th>
			</tr>
			<c:forEach var="telefono" items="${calList}" varStatus="rowCounter">
				<tr <c:out value="${rowCounter.count % 2 == 0?'':'class=odd'}"/>>
					<td>${telefono.detalle}</td>
					<td>${telefono.contador}</td>
					<td><a class="btn_portal" href="javascript:deletePhone(${telefono.detalle})">Eliminar</a></td>
				</tr>
			</c:forEach>
		</c:if>
	</tbody>
</table>