<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="portal-web" var="messages"/>
<fmt:setLocale value="es_MX"/>
<c:set var="context" value="${pageContext.request.contextPath}" />
<script src="dojotoolkit/dojo/dojo.js" djConfig="parseOnLoad: true, locale:'es'"></script>
<script type="text/javascript">
	function next() {
		dojo.xhrPost({url: '${pageContext.request.contextPath}/registroseventos.do?method=paginatorEvents&goAhead=true', timeout:180000, 
	    	load: function(data) {
				dojo.byId('previousEvents').innerHTML = data;
	    }});
	}
	function back() {
		dojo.xhrPost({url: '${pageContext.request.contextPath}/registroseventos.do?method=paginatorEvents&goBack=true', timeout:180000, 
			load: function(data) {
				dojo.byId('previousEvents').innerHTML = data;
		}});
	}
</script>
<t:publicTemplate>
	<jsp:attribute name="titulo">Ferias de Empleo</jsp:attribute>
    <jsp:attribute name="palabraDescripcion">Herramientas del Sitio</jsp:attribute>  
    <jsp:attribute name="tituloSitio">Portal del Empleo</jsp:attribute>

	<jsp:body>
		<!-- div ruta_navegacion -->
	    <div class="row">
	      <div class="col-sm-12">
	        <ol class="breadcrumb">
	          <li>Ruta de navegación:</li>
	           <li> <a href="<c:url value="/inicio.do"/>">Inicio</a></li>
	          <li><a href="<c:url value="/jsp/empleo/herramientasDelSitio/mapaDeSitio.jsp"/>">Herramientas del sitio</a></li>
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
	          <div class="panel-heading">
	            <h2 class="titulosh2">Eventos Anteriores</h2>
	          </div>
	          <div id="previousEvents">
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
			  </div>
	        </div>
	      </div>
	      <!-- /div contenido -->
	    </div>
	</jsp:body>
</t:publicTemplate>