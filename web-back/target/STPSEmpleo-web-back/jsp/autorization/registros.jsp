<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<table cellspacing="0" cellpadding="0" border="0" width="100%">
<tbody>
	<tr class="temas">
    	<th>Registro</th>
		<th>Propietario</th>
        <th>Estatus</th>
        <th>Fecha de registro</th>
        <th class="fin">Detalle</th>
    </tr>

	<c:set var="sufijo" value="" />
	<c:set var="pageList" value="PAGE_LIST${sufijo}" />
	
	<c:forEach var="registro" items="${sessionScope[pageList]}" varStatus="rowCounter">
	<tr <c:out value="${rowCounter.count % 2 == 0?'':'class=odd'}"/>>
		<td><p><strong>${registro.subTipoRegistro}:</strong><br> ${registro.registro}</p></td>
        <td><p><strong>${registro.tipoPropietario}:</strong><br> ${registro.propietario}</p></td>
        <td>${registro.estatusRegDesc}</td>
        <td>
	        <c:if test="${not empty registro.fechaAlta}">
		        <fmt:formatDate value="${registro.fechaAlta}" pattern="dd/MM/yyyy" />
	        </c:if>
        </td>        
        <td><a class="btn_portal" href="javascript:submitToMethod('detalleRegistro', ${registro.idRegValidar})">Ver detalle...</a></td>        
    </tr>
    </c:forEach>

</tbody>
</table>

<jsp:include page="../layout/pager.jsp" flush="true">
         <jsp:param name="SUFIJO" value="${sufijo}"/>
         <jsp:param name="tipoRegistros" value="registros"/>		 
</jsp:include>

