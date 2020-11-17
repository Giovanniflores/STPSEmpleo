<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="url" value="${pageContext.request.requestURI}" />

<t:publicTemplate>
	<jsp:attribute name="titulo">Herramientas para postulantes</jsp:attribute>
    <jsp:attribute name="palabraDescripcion">Candidatos</jsp:attribute>  
    <jsp:attribute name="tituloSitio">Portal del Empleo</jsp:attribute>
	<jsp:attribute name="urlRedSocial"><c:url value="${url}"/></jsp:attribute>
	<jsp:attribute name="descripcionRedSocial">
		<p>Generalmente los empleadores que buscan cubrir sus vacantes tienen interés en entrevistar a las/los posibles candidatas/os y solicitan la entrega de alguna documentación (CV, carta de recomendación, etc.). 
	</jsp:attribute>
	<jsp:body>
	
	<!-- div ruta_navegacion -->
    <div class="row">
      <div class="col-sm-12">
        <ol class="breadcrumb">
          <li>Ruta de navegación:</li>
          <li><a href="<c:url value="/inicio.do"/>">Inicio</a></li>
          <li><a href="<c:url value="/jsp/empleo/candidatos/candidatos.jsp"/>">Candidatos</a></li>
          <li><a href="<c:url value="/jsp/empleo/candidatos/habilidadesBusquedaEmpleo.jsp"/>">Habilidades para la Búsqueda de Empleo (HPBE) </a></li>
          <li class="active">Postula</li>
        </ol>
      </div>
    </div>
    <!-- /div ruta_navegacion -->

    <!-- div ruta_navegacion -->
    <div class="row">
    
      <jsp:include page="menu.jsp"/>
      
      <div class="col-sm-8 col-sm-pull-4">
      	 
      	 <jsp:include page="/WEB-INF/template/redes.jsp"/>
      
        <div class="clearfix"></div>
		
		 <div class="panel">
          <div class="panel-body">
			<h2 class="titulosh2">Postula</h2>
		
	        <p>Generalmente los empleadores que buscan cubrir sus vacantes tienen interés en entrevistar a las/los posibles candidatas/os y solicitan
	         la entrega de alguna documentación (CV, carta de recomendación, etc.). Por otra parte, cuando a la/el buscadora/buscador le interese trabajar 
	         en alguna empresa o institución, debe enviar una carta de presentación en la que destaque que es lo que ofrece a ésta y que posee 
	         (conocimientos, habilidades, actitudes y aptitudes) que le permitan contribuir al logro de los objetivos de la misma. En este apartado del 
	         <strong>Taller para Buscadores de Empleo</strong> se tocarán los siguientes temas:</p>

	        <ul class="list-group-contenidos">
	          <li><a href="<c:url value="/jsp/empleo/candidatos/comoDondeEncontrarVacantesOfertasEmpleo.jsp"/>">¿Cómo y dónde encontrar vacantes y ofertas de empleo?</a></li>
	          <li><a href="<c:url value="/jsp/empleo/candidatos/comoRedactarCurriculumVitae.jsp"/>">¿Cómo redactar un currículum vitae?</a></li>
	          <li><a href="<c:url value="/jsp/empleo/candidatos/cartaRecomendacion.jsp"/>">La carta de recomendación</a></li>
	          <li><a href="<c:url value="/jsp/empleo/candidatos/comoSuperarEntrevistaTrabajo.jsp"/>">¿Cómo superar la entrevista de trabajo?</a></li>
	        </ul>
       	   </div>
	      </div>
	     </div>



    </div>
	</jsp:body>
</t:publicTemplate>
