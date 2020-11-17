<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%String context = request.getContextPath() +"/";%>

<c:if test="${not empty PAGE_LIST}">
	<div id="ofertas_recomendadas">        	
		<h3>Ofertas de empleo recomendadas</h3>						
		<c:forEach var="ofertasPerfil" items="${PAGE_LIST}"  varStatus="rowCounter">
			<p <c:out value="${rowCounter.count % 2 == 0 ? 'class=marcado':'class=simple'}"/>> <a href="<%=context%>detalleoferta.do?method=init&id_oferta_empleo=${ofertasPerfil.idOfertaEmpleo}" >${ofertasPerfil.tituloOferta}</a> </p>
		</c:forEach>                  
	</div>
    <span class="bottom_ofertas"><a href="<%=response.encodeURL(context+"ofertasPerfiles.do?method=init&tipoConsulta=1")%>">Ver más ofertas</a></span>
</c:if>

<c:if test="${empty PAGE_LIST}">
	<div id="ofertas_recomendadas">        	
		<h3>Ofertas de empleo recomendadas</h3>
	</div>
    <span class="bottom_ofertas"><a href="<%=response.encodeURL(context+"ofertasPerfiles.do?method=init&tipoConsulta=1")%>">Ver más ofertas</a></span>
</c:if>