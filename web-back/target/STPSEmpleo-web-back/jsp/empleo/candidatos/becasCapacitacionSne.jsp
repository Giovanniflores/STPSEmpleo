<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="url" value="${pageContext.request.requestURI}" />

<t:publicTemplate>
	<jsp:attribute name="titulo">Becas para la capacitaci&oacute;n del SNE</jsp:attribute>
    <jsp:attribute name="palabraDescripcion">Candidatos</jsp:attribute>  
    <jsp:attribute name="tituloSitio">Portal del Empleo</jsp:attribute>
 	<jsp:attribute name="urlRedSocial"><c:url value="${url}"/></jsp:attribute>
	<jsp:attribute name="descripcionRedSocial">
		 Si buscas empleo y no cuentas con la capacitación o experiencia laboral necesaria, el Servicio Nacional de Empleo te brinda, sin costo alguno, apoyos de diversa índole para que desarrolles las destrezas y habilidades requeridas por el mercado de trabajo. Es a través del Subprograma Bécate que puedes aprovechar las siguientes modalidades de capacitación:
	</jsp:attribute>
	<jsp:body>
	
	<!-- div ruta_navegacion -->
    <div class="row">
      <div class="col-sm-12">
        <ol class="breadcrumb">
          <li>Ruta de navegación:</li>
          <li><a href="<c:url value="/inicio.do"/>">Inicio</a></li>
          <li><a href="<c:url value="/jsp/empleo/candidatos/candidatos.jsp"/>">Candidatos</a></li>
          <li><a href="<c:url value="/jsp/empleo/candidatos/capacitacionBecas.jsp"/>">Capacitación y becas</a></li>
          <li class="active">Becas a la capacitación del SNE</li>
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
			<h2 class="titulosh2">Becas a la capacitación del SNE</h2>
		
	        <p>Si buscas empleo y no cuentas con la capacitación o experiencia laboral necesaria, el Servicio Nacional de Empleo te brinda, sin costo alguno, apoyos de diversa índole para que desarrolles las destrezas y habilidades requeridas por el mercado de trabajo. Es a través del Subprograma Bécate que puedes aprovechar las siguientes modalidades de capacitación:</p>

	        <ul class="list-group-contenidos">
	          <li><a href="<c:url value="/jsp/empleo/candidatos/capacitacionMixta.jsp"/>">Capacitación mixta</a></li>
	          <li><a href="<c:url value="/jsp/empleo/candidatos/capacitacionPracticaLaboral.jsp"/>">Capacitación en la práctica laboral</a></li>
	          <li><a href="<c:url value="/jsp/empleo/candidatos/capacitacionAutoempleo.jsp"/>">Capacitación para el autoempleo</a></li>
	          <li><a href="<c:url value="/jsp/empleo/candidatos/valesCapacitacion.jsp"/>">Vales de capacitación</a></li>
	        </ul>
       	   </div>
	      </div>
	     </div>



    </div>
	</jsp:body>
</t:publicTemplate>
