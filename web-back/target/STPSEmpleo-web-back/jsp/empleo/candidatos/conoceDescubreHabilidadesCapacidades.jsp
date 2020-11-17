<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="url" value="${pageContext.request.requestURI}" />


<t:publicTemplate>
	<jsp:attribute name="titulo">Conoce y descubre tus habilidades y capacidades</jsp:attribute>
    <jsp:attribute name="palabraDescripcion">Candidatos</jsp:attribute>  
    <jsp:attribute name="tituloSitio">Portal del Empleo</jsp:attribute>
 	<jsp:attribute name="urlRedSocial"><c:url value="${url}"/></jsp:attribute>
	<jsp:attribute name="descripcionRedSocial">
		 Como ya mencionamos, existen condiciones que te competen exclusivamente a ti para lograr tu mejor inclusi&oacute;n al mercado de trabajo. Entre ellas est&aacute; tu disposici&oacute;n para integrarte realmente a un empleo, que identifiques las habilidades que posees, que sepas cu&aacute;les son tus actitudes y valores hacia el trabajo; as&iacute; como el conocimiento que puedes tener sobre las vacantes existentes en tu localidad o entidad, los perfiles que buscan, los valores que requieren, etc.
	</jsp:attribute>
	<jsp:body>
	
	<!-- div ruta_navegacion -->
    <div class="row">
      <div class="col-sm-12">
        <ol class="breadcrumb">
          <li>Ruta de navegación:</li>
         <li><a href="<c:url value="/inicio.do"/>">Inicio</a></li>
          <li><a href="<c:url value="/jsp/empleo/candidatos/candidatos.jsp"/>">Candidatos</a></li>
          <li><a href="<c:url value="/jsp/empleo/candidatos/habilidadesBusquedaEmpleo.jsp"/>">Habilidades para la Búsqueda de Empleo (HPBE)</a></li>
          <li><a href="<c:url value="/jsp/empleo/candidatos/postula.jsp"/>">Conoce</a></li>
          <li class="active">Conoce y descubre tus habilidades y capacidades</li>
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
			<h2 class="titulosh2">Conoce y descubre tus habilidades y capacidades</h2>
		
	        <p>Como ya mencionamos, existen condiciones que te competen exclusivamente a ti para lograr tu mejor inclusi&oacute;n al mercado de trabajo. Entre ellas est&aacute; tu disposici&oacute;n para integrarte realmente a un empleo, que identifiques las habilidades que posees, que sepas cu&aacute;les son tus actitudes y valores hacia el trabajo; as&iacute; como el conocimiento que puedes tener sobre las vacantes existentes en tu localidad o entidad, los perfiles que buscan, los valores que requieren, etc.</p>
			<p>Para descubrir tus habilidades es necesario que reflexiones sobre las siguientes preguntas:</p>
				<ul class="list-group-contenidos">
    				<li>&iquest;Qu&eacute; quiero hacer?</li>
    				<li class="no_line">&iquest;Qu&eacute; se hacer?</li>
				</ul>
<p>Darles respuesta te ayudar&aacute; a descubrir tus habilidades y capacidades. Si cuentas con un poco m&aacute;s de tiempo te puede interesar responder el siguiente <a href="${context}/download/candidatos/puntos_fuertes_y_puntos_debiles.docx">cuestionario</a> y contar con un perfil de tus fortalezas y debilidades.</p>
       	   </div>
	      </div>
	     </div>



    </div>
	</jsp:body>
</t:publicTemplate>
