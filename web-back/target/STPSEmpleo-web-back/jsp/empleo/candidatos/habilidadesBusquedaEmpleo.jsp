<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="url" value="${pageContext.request.requestURI}" />

<t:publicTemplate>
	<jsp:attribute name="titulo">Habilidades para la B&uacute;squeda de Empleo</jsp:attribute>
    <jsp:attribute name="palabraDescripcion">Candidatos</jsp:attribute>  
    <jsp:attribute name="tituloSitio">Portal del Empleo</jsp:attribute>
 	<jsp:attribute name="urlRedSocial"><c:url value="${url}"/></jsp:attribute>
	<jsp:attribute name="descripcionRedSocial">
		¡Sé un mejor candidato para las empresas! De qué hablamos cuando lo hacemos de habilidades. Hablamos de herramientas y de actitudes, en este caso, que son importantes para la búsqueda de empleo.
	</jsp:attribute>
	<jsp:body>
	
	<!-- div ruta_navegacion -->
    <div class="row">
      <div class="col-sm-12">
        <ol class="breadcrumb">
          <li>Ruta de navegación:</li>
           <li><a href="<c:url value="/inicio.do"/>">Inicio</a></li>
          <li><a href="<c:url value="/jsp/empleo/candidatos/candidatos.jsp"/>">Candidatos</a></li>
          <li class="active">Habilidades para la Búsqueda de Empleo (HPBE)</li>
        </ol>
      </div>
    </div>
    <!-- /div ruta_navegacion -->

    <!-- div ruta_navegacion -->
    <div class="row">
    
      <jsp:include page="menu.jsp"/>
      
      <div class="col-sm-8 col-sm-pull-4">
      	 
      	 <jsp:include page="/WEB-INF/template/redes.jsp"/>
      
        <div class="panel panel-grey">
          <div class="panel-body">
          <div class="col-md-6">
            <img alt="" src="${context}/css/images/contenido/imagen_habilidades.png" class="img-responsive">
          </div>
          <div class="col-md-6">
            <h2>Habilidades para la Búsqueda de Empleo (HPBE)</h2>
            <p>En esta sección, encontrarás las herramientas necesarias para una búsqueda de empleo efectiva y cubrir tus expectativas laborales.</p>
          </div>
        </div>
        </div>

        <div class="clearfix"></div>
        
        <div class="panel">
          <div class="panel-body">

	        <p><strong>¡Sé un mejor candidato para las empresas!</strong></p>
	        <p>De qué hablamos cuando lo hacemos de habilidades. Hablamos de herramientas y de actitudes, en este caso, que son importantes para la búsqueda de empleo.</p>
	        <p>La búsqueda de empleo implica una condición en la que se encuentran las personas que no cuentan con uno y aquellos que, aun teniéndolo, tratan de encontrar uno más acorde a sus habilidades o que les permita tener un mejor ingreso. Implica también una actitud que está asociada a la disposición para la búsqueda.</p>
	        <p>Habilidades para la Búsqueda de Empleo te ofrece la posibilidad de desarrollar aquellas que requieres para una búsqueda efectiva de empleo. Para ello, el tema se ha dividido en dos pasos:</p>
	          <h3>Conoce</h3>
	          <p>Identifica tus fortalezas y debilidades para colocarte en un empleo acorde a tu perfil laboral, a través de los servicios que te ofrece el Servicio Nacional de Empleo.</p>
	          <h3>Postula</h3>
	          <p>Redacta un CV y una carta de presentación de impacto, controla los nervios en una entrevista de trabajo, y entérate cómo y dónde encontrar ofertas de empleo.</p>
	       </div>
	     </div>
       
      </div>



    </div>
	</jsp:body>
</t:publicTemplate>
