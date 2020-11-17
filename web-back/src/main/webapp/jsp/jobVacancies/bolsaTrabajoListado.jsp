<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" 		prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" 		prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:set var="sufijo" value="" />
<c:set var="pageList" value="PAGE_LIST${sufijo}" />


<c:choose>
	<c:when test="${not empty (sessionScope[pageList])}">
	<div class="no-more-tables">
 	<table id="resultados" summary="Los resultados de la busqueda" class="table table-striped table-condensed offer">
 		<thead>
		<tr>
			<th width="270">Título de la oferta
            	<a class="ascendente" href="javascript:doSubmitOrden(1,1)"></a>
				<a class="descendente" href="javascript:doSubmitOrden(1,2)"></a>
			</th>
            <th width="154">Ubicación
            	<a class="ascendente" href="javascript:doSubmitOrden(2,1)"></a>
				<a class="descendente" href="javascript:doSubmitOrden(2,2)"></a>
			</th>
            <th width="298">Empresa
            	<a class="ascendente" href="javascript:doSubmitOrden(3,1)"></a>
				<a class="descendente" href="javascript:doSubmitOrden(3,2)"></a>
			</th>
            <th width="177">Fecha de publicación
            	<a class="ascendente" href="javascript:doSubmitOrden(4,1)"></a>
				<a class="descendente" href="javascript:doSubmitOrden(4,2)"></a>
			</th>
			<th>Detalle de la oferta</th> 
		</tr>
		</thead>
		<c:forEach var="oferta" items="${sessionScope[pageList]}" varStatus="rowCounter">

			<c:choose>
	    	<c:when test="${empty oferta.puesto && empty oferta.empresa && empty oferta.estado && empty oferta.url}">
	    		<c:set var="valorHref" value=""/>
	    	</c:when>
	    	<c:when test="${not empty oferta.puesto && not empty oferta.estado && not empty oferta.url}">
	    		<c:set var="valorHref" value="<a class=detalle href='${oferta.url}' target=_blank>Ver detalle</a>" />
	    	</c:when>
	    	</c:choose>

			<c:choose>
	    	<c:when test="${empty oferta.url}">
	    		<c:set var="tituloHref" value="<a class=titulo href='#'>${oferta.puesto}</a>" />
	    	</c:when>
	    	<c:when test="${not empty oferta.url}">
   		    		<c:set var="tituloHref" value="<a class=titulo href='${oferta.url}' target=_blank>${oferta.puesto}</a>" />
<%-- 	    		<c:set var="tituloHref" value="<a class=titulo href='${oferta.url}' target=_blank>${oferta.puesto}</a>" /> --%>
	    	</c:when>
	    	</c:choose>
          	<tr <c:out value="${rowCounter.count % 2 == 0?'':'class=odd'}"/>>
	            <td>${tituloHref}</td>
	            <td><c:out value="${oferta.estado}"/></td>
	
				<c:choose>
				<c:when test="${not empty oferta.idDiscapacidad}">
				      <td align="center">
				   		 <c:forEach var="imagen" items="${oferta.imagen}">
				   		        <img alt="<c:out value="${imagen}"/>" src="images/<c:out value="${imagen}"/>" />
				          </c:forEach>
					  </td>
				</c:when>
				<c:otherwise>
				<td>
				<c:out value="${oferta.empresa}"/>
				</td >
				</c:otherwise>
	    		</c:choose>
	            
	            <td><c:out value="${oferta.fecha}"/></td>
	            
	            <c:if test="${oferta.idOferta == 0 }">
			   		<td>${valorHref}</td >
			   	</c:if>
<%-- 			   	<c:if test="${oferta.idOferta != 0 }"> --%>
<%-- 			   		<td><a class="detalle" href="javascript:mostrarDetalle(${oferta.idOferta})">Ver detalle</a></td>		   		 --%>
<%-- 			   	</c:if> --%>
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
		<table id="resultados" summary="Los resultados de la busqueda">
			<tr>
				<th>No se encontraron ofertas de empleo</th>
			</tr>
		</table>
    </c:if>
	</c:otherwise>

</c:choose>
