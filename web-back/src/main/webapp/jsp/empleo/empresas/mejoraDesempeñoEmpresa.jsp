<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="url" value="${pageContext.request.requestURI}" />


<t:publicTemplate>
	<jsp:attribute name="titulo">Mejora el desempe&ntilde;o de tu empresa</jsp:attribute>
    <jsp:attribute name="palabraDescripcion">Empresas</jsp:attribute> 
	<jsp:attribute name="tituloSitio">Portal del Empleo</jsp:attribute>
 	<jsp:attribute name="urlRedSocial"><c:url value="${url}"/></jsp:attribute>
	<jsp:attribute name="descripcionRedSocial">
		Hoy en d&iacute;a, las empresas necesitan de un &aacute;rea que est&eacute; al pendiente de las necesidades tanto de la organizaci&oacute;n, como de los trabajadores; el &aacute;rea de recursos humanos es aqu&eacute;lla que se encarga de armonizar ambas partes. Por un lado, se encarga de definir, reclutar y contratar al personal que requiere la empresa y, por otro lado, se encarga de retener,  motivar y lograr la permanencia de los trabajadores en la organizaci&oacute;n.
	</jsp:attribute>
	<jsp:body>
	
	<!-- div ruta_navegacion -->
    <div class="row">
      <div class="col-sm-12">
        <ol class="breadcrumb">
          <li>Ruta de navegación:</li>
          <li><a href="<c:url value="/inicio.do"/>">Inicio</a></li>
          <li><a href="<c:url value="/jsp/empleo/empresas/empresas.jsp"/>">Empresas</a></li>
          <li class="active">Mejora el desempeño de tu empresa</li>
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
            <img alt="" src="${context}/css/images/contenido/desempeno_empresa.png" class="img-responsive">
          </div>
          <div class="col-md-6">
            <h2>Mejora el desempeño de tu empresa</h2>
            <p>El área de recursos humanos es una pieza fundamental dentro de una empresa, ya que es la encargada de empatar el desempeño de cada trabajador, con la misión y los objetivos de la organización. Un buen desempeño en el área garantizará, en gran medida, la productividad y el éxito de la empresa.</p>
          </div>
        </div>
        </div>

        <div class="clearfix"></div>
        
        <div class="panel">
          <div class="panel-body">

	        <p>Hoy en d&iacute;a, las empresas necesitan de un &aacute;rea que est&eacute; al pendiente de las necesidades tanto de la organizaci&oacute;n, como de los trabajadores; el &aacute;rea de recursos humanos es aqu&eacute;lla que se encarga de armonizar ambas partes. Por un lado, se encarga de definir, reclutar y contratar al personal que requiere la empresa y, por otro lado, se encarga de retener,  motivar y lograr la permanencia de los trabajadores en la organizaci&oacute;n.</p>
<p>Para asegurarte de seleccionar al candidato que mejor cumpla con las expectativas de la vacante y que contribuya al cumplimiento de los objetivos de la empresa, es importante realizar un proceso de reclutamiento exitoso.</p>
<p>En este apartado, se tocar&aacute;n los siguientes temas:</p>
<ul class="list-group-contenidos">
    <li><a href="<c:url value="/jsp/empleo/empresas/evaluacionCandidatos.jsp"/>">La evaluaci&oacute;n de candidatos.</a></li>
    <li><a href="<c:url value="/jsp/empleo/empresas/seleccionDePersonal.jsp"/>">La selecci&oacute;n de personal.</a></li>
    <li><a href="<c:url value="/jsp/empleo/empresas/analisisPuestos.jsp"/>">El an&aacute;lisis de puestos.</a></li>
    <li><a href="<c:url value="/jsp/empleo/empresas/preparacionEntrevista.jsp"/>">Preparaci&oacute;n de la entrevista.</a></li>
    <li><a href="<c:url value="/jsp/empleo/empresas/conoceTusEmpleados.jsp"/>">Conoce a tus empleados.</a></li>
    <li><a href="<c:url value="/jsp/empleo/empresas/motivaATuPersonal.jsp"/>">Motiva a tu personal.</a></li>
    <li><a href="<c:url value="/jsp/empleo/empresas/accidentesTrabajo.jsp"/>">Accidentes en el trabajo.</a></li>
</ul>
<p>Optimiza la productividad del &aacute;rea de recursos humanos y conoce nuevas pr&aacute;cticas de implementaci&oacute;n para elevar el proceso de selecci&oacute;n de personal.</p>
	        
	       </div>
	     </div>
       
      </div>

    </div>
	</jsp:body>
</t:publicTemplate>
