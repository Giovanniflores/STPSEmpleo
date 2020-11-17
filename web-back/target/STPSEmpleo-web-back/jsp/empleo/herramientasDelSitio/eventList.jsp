<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="portal-web" var="messages"/>
<fmt:setLocale value="es_MX"/>
<c:set var="context" value="${pageContext.request.contextPath}" />
				<div class="panel-body">
		          <c:forEach var="eventos" items="${sessionScope.previousEventList.elementosPaginaActual}">
		          	<div class="noticia">
		          		<c:if test="${null != eventos.yearInitDate}">
		          			<h3><c:out value="${eventos.yearInitDate}"/><br/><c:out value="${eventos.monthInitDate}"/></h3>
		          		</c:if>
		          		<c:if test="${null == eventos.yearInitDate}">
		          			<h3><c:out value="${eventos.monthInitDate}"/></h3>
		          		</c:if>
				        <img src="${context}/css/images/ico_conversacion.gif" alt="Noticia de TICs">
				        <h4><c:out value="${eventos.evento}"/></h4>
				        <p><fmt:formatDate type="date" dateStyle="long" value="${eventos.fechaInicio}"/> <c:out value=" ${eventos.calle} ${eventos.numeroExterior} C.P. ${eventos.codigoPostal} ${eventos.localidad}"/>
				        </p>
				        <a href="<fmt:message key='app.domain.ferias.virtual' bundle='${messages}'/>/content/oferta/OfertasEvento.jsf?faces-redirect=true&idEvento=${eventos.idEvento}&idEntidad=${eventos.idEntidad}" class="pull-right" target="_blank">ver evento</a>
				    </div>
				 </c:forEach>
				 <div class="panel-heading text-center">
					<c:if  test="${sessionScope.previousEventList.activaAnterior}">
						<input type="button" id="activaAnterior" onclick="back();" value="anterior" class="btn btn-green"/>
					</c:if>	
					<c:if test="${sessionScope.previousEventList.activaSiguiente}">
						<input type="button" id="activaSiguiente" onclick="next();" value="siguiente" class="btn btn-green"/>
					</c:if>	
				 </div>
				 <br>
				 <a href="<c:url value="/jsp/empleo/herramientasDelSitio/eventos.jsp"/>" id="noticiasAnteriores">Ver Eventos Recientes</a>
	          </div>