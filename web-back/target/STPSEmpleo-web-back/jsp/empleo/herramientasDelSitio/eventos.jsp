<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="portal-web" var="messages"/>
<fmt:setLocale value="es_MX"/>
<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="url" value="${pageContext.request.requestURI}" />

<t:publicTemplate>
	<jsp:attribute name="titulo">Ferias de Empleo</jsp:attribute>
    <jsp:attribute name="palabraDescripcion">Herramientas del Sitio</jsp:attribute>  
    <jsp:attribute name="tituloSitio">Portal del Empleo</jsp:attribute>
 	<jsp:attribute name="urlRedSocial"><c:url value="${url}"/></jsp:attribute>
	<jsp:attribute name="descripcionRedSocial">
		Portal del Empleo: Eventos.
	</jsp:attribute>
	<jsp:body>
		<!-- div ruta_navegacion -->
	    <div class="row">
	      <div class="col-sm-12">
	        <ol class="breadcrumb">
	          <li>Ruta de navegación:</li>
	           <li> <a href="<c:url value="/inicio.do"/>">Inicio</a></li>
<!-- 	          <li><a href="#">Inicio</a></li> -->
	          <li><a href="<c:url value="/jsp/empleo/herramientasDelSitio/mapaDeSitio.jsp"/>">Herramientas del sitio</a></li>
<!-- 				<li><a href="#">Herramientas del sitio</a></li> -->
          	  <li class="active">Eventos</li>
	        </ol>
	      </div>
	    </div>
	    <!-- /div ruta_navegacion -->
	    
	    <div class="row">
		  <!-- div menu -->
		  <jsp:include page="menu.jsp"/>
		  
		  <!-- div contenido -->
	      <div class="col-sm-8 col-sm-pull-4">
	      
	       <jsp:include page="/WEB-INF/template/redes.jsp"/>
	
	        <div class="panel panel-eventos">
	          <!-- div class="panel-heading">
	            <h2 class="titulosh2">Eventos Recientes</h2>
	          </div -->
	          <div class="panel-body">
	              <h3>Eventos Recientes</h3>
		          <c:forEach var="eventos" items="${sessionScope.eventosRecientesList.elementos}">
		          	<div class="noticia">
				        <img src="${context}/css/images/ico_conversacion.gif" alt="Noticia de TICs">
				        <h4><c:out value="${eventos.evento}"/></h4>
				        <p><fmt:formatDate type="date" dateStyle="long" value="${eventos.fechaInicio}"/> <c:out value=" ${eventos.calle} ${eventos.numeroExterior} C.P. ${eventos.codigoPostal} ${eventos.localidad}"/>
				        </p>
				        <a href="<fmt:message key='app.domain.ferias.virtual' bundle='${messages}'/>/content/oferta/OfertasEvento.jsf?faces-redirect=true&idEvento=${eventos.idEvento}&idEntidad=${eventos.idEntidad}" class="pull-right" target="_blank">ver evento</a>
				    </div>
				 </c:forEach>
				 <br>
				 <a href="<c:url value="/registroseventos.do?method=previousEvents"/>" id="noticiasAnteriores">Ver Eventos Anteriores</a>
	          </div>
	        </div>
	      </div>
	      <!-- /div contenido -->
	    </div>
	</jsp:body>
</t:publicTemplate>
