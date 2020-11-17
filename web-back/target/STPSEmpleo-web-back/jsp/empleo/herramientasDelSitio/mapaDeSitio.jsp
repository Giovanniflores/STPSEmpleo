<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="url" value="${pageContext.request.requestURI}" />

	<t:publicTemplate>
	<jsp:attribute name="titulo">Mapa de sitio</jsp:attribute>
    <jsp:attribute name="palabraDescripcion">Herramientas del Sitio</jsp:attribute>  
    <jsp:attribute name="tituloSitio">Portal del Empleo</jsp:attribute>
 	<jsp:attribute name="urlRedSocial"><c:url value="${url}"/></jsp:attribute>
	<jsp:attribute name="descripcionRedSocial">
		Portal del Empleo:  Mapa de sitio.
	</jsp:attribute>
	<jsp:body>
		<!-- div ruta_navegacion -->
	    <div class="row">
	      <div class="col-sm-12">
	        <ol class="breadcrumb">
	          <li>Ruta de navegación:</li>
	          <li> <a href="<c:url value="/inicio.do"/>">Inicio</a></li>
	          <li><a href="<c:url value="/jsp/empleo/herramientasDelSitio/mapaDeSitio.jsp"/>">Herramientas del sitio</a></li>
          	  <li class="active">Mapa de sitio</li>
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
	
	        <div class="panel panel-mapaSitio">
	          <div class="panel-heading">
	            <h2 class="titulosh2">Mapa de sitio</h2>
	          </div>
	          <div class="panel-body">
	            <div class="seccion">
					<h3><a href="<c:url value="/jsp/empleo/candidatos/candidatos.jsp"/>">Candidatos</a></h3>
					<ul>
						<li>
							<a href="<c:url value="/jsp/empleo/candidatos/registrate.jsp"/>">Regístrate y haz que las empresas te vean</a>
						</li>
						<li>
							<a href="<c:url value="/jsp/empleo/candidatos/descubreHerramientas.jsp"/>">Descubre las herramientas que tenemos para ti</a>
						</li>
						<li>
							<a href="<c:url value="/jsp/empleo/candidatos/conoceServiciosCandidato.jsp"/>">Conoce los servicios que el portal tiene para ti</a>
						</li>
						<li>
							<a href="<c:url value="/jsp/empleo/candidatos/introduccionBusquedaEmpleo.jsp"/>">Introducción a la búsqueda de empleo</a>
						</li>
						<li>
							<a href="<c:url value="/jsp/empleo/candidatos/habilidadesBusquedaEmpleo.jsp"/>">Habilidades para la Búsqueda de Empleo (HPBE)</a>
						</li>
						<li>
							<a href="<c:url value="/jsp/empleo/candidatos/alternativasBusquedaEmpleo.jsp"/>">Alternativas de búsqueda de empleo</a>
						</li>
						<li>
							<a href="<c:url value="/jsp/empleo/candidatos/capacitacionBecas.jsp"/>">Capacitación y becas</a>
						</li>
						<li>
							<a href="<c:url value="/jsp/empleo/candidatos/asesoriaLaboral.jsp"/>">Asesoría laboral</a>
						</li>
					</ul>
                 </div>
                 
                 <div class="seccion">
					<h3><a href="<c:url value="/jsp/empleo/empresas/empresas.jsp"/>">Empresas</a></h3>
                     <ul>
						<li>
							<a href="<c:url value="/jsp/empleo/empresas/descubreHerramientasEmpresas.jsp"/>">Descubre las herramientas que tenemos para ti</a>
						</li>
						<li>
							<a href="<c:url value="/jsp/empleo/empresas/conoceServiciosPortalEmpresas.jsp"/>">Conoce los servicios que el portal tiene para ti</a>
						</li>
						<li>
							<a href="<c:url value="/jsp/empleo/empresas/mejoraDesempeñoEmpresa.jsp"/>">Mejora el desempeño de tu empresa</a>
						</li>
					  </ul>
                 </div>
                 
                 <div class="seccion">
					<h3><a href="<c:url value="/jsp/empleo/servicioNacionalDeEmpleo/servicioNacionalDeEmpleo.jsp"/>">Servicio Nacional de Empleo</a></h3>
					<ul>
						<li>
							<a href="<c:url value="/jsp/empleo/servicioNacionalDeEmpleo/acercaSne.jsp"/>">Acerca del SNE</a>
						</li>
						<li>
							<a href="<c:url value="/jsp/empleo/servicioNacionalDeEmpleo/programasServiciosEmpleo.jsp"/>">Programas y servicios de empleo</a>
						</li>
						<li>
							<a href="<c:url value="/jsp/empleo/servicioNacionalDeEmpleo/estadisticasLaborales.jsp"/>">Estadísticas laborales</a>
						</li>
                    </ul>
                 </div>
                 
                 <div>
                 	<a href="${context}/sitemap.xml"><span class="badge">XML</span></a>
                 </div>
                 
	          </div>
	        </div>
	        
	      </div>
	      <!-- /div contenido -->
	    </div>
	    
	    
	    
	    
	</jsp:body>
</t:publicTemplate>
