<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="url" value="${pageContext.request.requestURI}" />


<t:publicTemplate>
	<jsp:attribute name="titulo">Asesor&iacute;a laboral</jsp:attribute>
    <jsp:attribute name="palabraDescripcion">Candidatos</jsp:attribute>  
    <jsp:attribute name="tituloSitio">Portal del Empleo</jsp:attribute>
 	<jsp:attribute name="urlRedSocial"><c:url value="${url}"/></jsp:attribute>
	<jsp:attribute name="descripcionRedSocial">
		   En esta sección encontrarás orientación y asistencia relacionada con el entorno laboral, así como información acerca de los servicios que proporciona la Secretaría del Trabajo y Previsión Social (STPS) en materia de asesoría, conciliación y representación jurídica de los trabajadores.
	</jsp:attribute>
	<jsp:body>
	
	<!-- div ruta_navegacion -->
    <div class="row">
      <div class="col-sm-12">
        <ol class="breadcrumb">
          <li>Ruta de navegación:</li>
          <li><a href="<c:url value="/inicio.do"/>">Inicio</a></li>
          <li><a href="<c:url value="/jsp/empleo/candidatos/candidatos.jsp"/>">Candidatos</a></li>
          <li class="active">Asesoría laboral</li>
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
            <img alt="" src="${context}/css/images/contenido/asesoria_lab.png" class="img-responsive">
          </div>
          <div class="col-md-6">
            <h2>Asesoría laboral</h2>
            <p>En esta sección encontrarás orientación y asistencia relacionada con el entorno laboral, así como información acerca de los servicios que proporciona la Secretaría del Trabajo y Previsión Social (STPS) en materia de asesoría, conciliación y representación jurídica de los trabajadores.</p>
          </div>
        </div>
        </div>

        <div class="clearfix"></div>
        
        <div class="panel">
          <div class="panel-body">

	        <p>La Secretaría del Trabajo y Previsión Social (STPS), como autoridad laboral, cuenta con diversas instituciones para tutelar los derechos de los trabajadores. Una de ellas es la Procuraduría Federal de la Defensa del Trabajo (PROFEDET). Esta institución proporciona servicios gratuitos de asesoría, conciliación y representación jurídica a los trabajadores, sus beneficiarios y sus sindicatos. Por ello, si buscas orientación o asistencia laboral, información sobre contratos colectivos o agrupaciones sindicales, en esta sección encontrarás:</p>
			<ul class="list-group-contenidos">
    			<li>Las caracter&iacute;sticas de los servicios que proporciona la Secretar&iacute;a del Trabajo y Previsi&oacute;n Social en materia de asesor&iacute;a,  conciliaci&oacute;n y representaci&oacute;n jur&iacute;dica.</li>
    			<li>La ubicaci&oacute;n geogr&aacute;fica de las oficinas de la Procuradur&iacute;a Federal de la Defensa del Trabajo</li>
    			<li>Las preguntas m&aacute;s frecuentes clasificadas seg&uacute;n el ciclo de vida laboral (inicio, durante y fin)</li>
    			<li>La Ley Federal del Trabajo</li>
    			<li>Enlace a los expedientes de los Contratos Colectivos de Trabajo depositados ante la Junta Federal de Conciliaci&oacute;n y Arbitraje</li>
    			<li>Acceso a la informaci&oacute;n actualizada de sindicatos, federaciones y confederaciones registrados ante la Secretar&iacute;a del Trabajo y Previsi&oacute;n Social</li>
			</ul>
<br />
<p><strong>&iexcl;Nuestros servicios son gratuitos!</strong><br />
<strong>&iexcl;Estamos para servirte!</strong></p>
	       </div>
	     </div>
       
      </div>



    </div>
	</jsp:body>
</t:publicTemplate>
