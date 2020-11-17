<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<table class="margin_top_40" style="width:940px" cellspacing="0" cellpadding="0" border="0">
	<tr class="temas">
		    <th style="width:130px">Fecha de eliminación</th>
		    <th style="width:480px">Título de la oferta</th>    	 
		    <th>Editar</th>
    </tr>

	<c:set var="sufijo" value="" />
	<c:set var="pageList" value="PAGE_LIST${sufijo}" />
	
	<c:forEach var="oferta" items="${sessionScope[pageList]}" varStatus="rowCounter">
		
		<!-- registros sobre los que se pagina-->		
		<tr>
		    <td class="checar"><strong><fmt:formatDate value="${oferta.fechaModificacion}"pattern="dd/MM/yyyy"/></strong></td>
		   	<td class="checar"><strong><c:out value="${oferta.tituloOferta}"/></strong></td>
		    
		    <c:url var="urlEdicion" value="${pageContext.request.contextPath}/registro-unico.do">
		    	<c:param name="method" value="redirectEditaOfertaRU"/>
		    	<c:param name="id" value="${oferta.idOfertaEmpleo}"/>	
		    	<c:param name="idEvento" value="69"/>
		    	<c:param name="tipoOferta" value="${oferta.tipoOferta}"/>
		    </c:url>
		    <td><a href="${urlEdicion}">Editar</a></td>
		</tr>
		
	</c:forEach>		
	    
</table>

</br>

<jsp:include page="../../layout/pager.jsp" flush="true">
         <jsp:param name="SUFIJO" value="${sufijo}"/>
         <jsp:param name="tipoRegistros" value="ofertas"/>		 
</jsp:include>