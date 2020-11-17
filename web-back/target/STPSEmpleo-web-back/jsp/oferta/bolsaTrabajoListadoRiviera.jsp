<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" 		prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" 		prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:set var="sufijo" value="" />
<c:set var="pageList" value="PAGE_LIST${sufijo}" />

<c:choose>
	<c:when test="${not empty (sessionScope[pageList])}">
<div class="no-more-tables">
 	<table id="resultados" summary="Los resultados de la busqueda" class="table table-striped table-condensed">
	 	<thead>
			<tr>
				<th>Título de la oferta
	            	<a class="ascendente" href="javascript:doSubmitOrden(1,1)"></a>
					<a class="descendente" href="javascript:doSubmitOrden(1,2)"></a>
				</th>
	            <th>Ubicación
	            	<a class="ascendente" href="javascript:doSubmitOrden(2,1)"></a>
					<a class="descendente" href="javascript:doSubmitOrden(2,2)"></a>
				</th>
	            <th>Sueldo
	            	<a class="ascendente" href="javascript:doSubmitOrden(3,1)"></a>
					<a class="descendente" href="javascript:doSubmitOrden(3,2)"></a>
				</th>
				 <th>Descripción
	            	<a class="ascendente" href="javascript:doSubmitOrden(4,1)"></a>
					<a class="descendente" href="javascript:doSubmitOrden(4,2)"></a>
				</th>
	            <th class="text-center">Ver oferta</th>
			</tr>
	</thead>

		<c:forEach var="oferta" items="${sessionScope[pageList]}" varStatus="rowCounter">


          	<tr <c:out value="${rowCounter.count % 2 == 0?'':'class=odd'}"/>>
	            <td data-title="Título de la oferta"><c:out value="${oferta.nombrepuesto}"/></td>
	            <td data-title="Ubicación"><c:out value="${oferta.ubicacion}"/></td>
				<td data-title="Sueldo"><c:out value="${oferta.sueldo}"/></td >
				<td data-title="Descripción"><c:out value="${oferta.descripcion}"/></td>
	            
	            <c:if test="${oferta.idOferta == 0 }">
			   		<td>${valorHref}</td >
			   	</c:if>

			   	<c:if test="${oferta.idOferta != 0 }">
			   		<td data-title="Ver detalle"><a class="upper-btn" href="${pageContext.request.contextPath}/detalleoferta.do?method=init&id_oferta_empleo=${oferta.idOferta}">Ver vacante</a></td>
			   	</c:if>
          	</tr>
		</c:forEach>
		
		
		
	</table>
</div>

	<jsp:include page="../layout/pager.jsp" flush="true">
		<jsp:param name="SUFIJO" value="${sufijo}"/>
		<jsp:param name="tipoRegistros" value="ofertas"/>         
	</jsp:include>

	</c:when>
	
	<c:otherwise>
	<c:if test="${existenOfertas == false}">
		<table id="resultados" summary="Los resultados de la busqueda" class="table table-striped table-condensed">
			<tr>
				<th>No se encontraron ofertas de empleo</th>
			</tr>
		</table>
    </c:if>
	</c:otherwise>

</c:choose>
