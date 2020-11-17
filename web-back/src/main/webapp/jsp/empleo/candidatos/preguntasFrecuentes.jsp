<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="url" value="${pageContext.request.requestURI}" />


<t:publicTemplate>
	<jsp:attribute name="titulo">Preguntas frecuentes</jsp:attribute>
    <jsp:attribute name="palabraDescripcion">Candidatos</jsp:attribute>  
    <jsp:attribute name="tituloSitio">Portal del Empleo</jsp:attribute>
 	<jsp:attribute name="urlRedSocial"><c:url value="${url}"/></jsp:attribute>
	<jsp:attribute name="descripcionRedSocial">
		Resuelve las dudas m&aacute;s frecuentes que se presentan en las diferentes etapas de la vida laboral.
	</jsp:attribute>
	<jsp:body>
	
	<!-- div ruta_navegacion -->
    <div class="row">
      <div class="col-sm-12">
        <ol class="breadcrumb">
          <li>Ruta de navegación:</li>
          <li><a href="<c:url value="/inicio.do"/>">Inicio</a></li>
          <li><a href="<c:url value="/jsp/empleo/candidatos/candidatos.jsp"/>">Candidatos</a></li>
          <li><a href="<c:url value="/jsp/empleo/candidatos/asesoriaLaboral.jsp"/>">Asesoría laboral</a></li>
          <li class="active">Preguntas frecuentes</li>
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
			<h2 class="titulosh2">Preguntas frecuentes</h2>
		
	        <p><strong>&iexcl;No te quedes con la duda!</strong></p>
<p>Resuelve las dudas m&aacute;s frecuentes que se presentan en las diferentes etapas de la vida laboral.</p>
<p>A trav&eacute;s de las llamadas telef&oacute;nicas que realizan los usuarios al centro de contacto de la PROFEDET, se ha realizado un compendio con las preguntas m&aacute;s frecuentes recibidas.</p>
<ul class="list-group-contenidos">
    <li><strong><a href="<c:url value="/jsp/empleo/candidatos/alInicioVidaLaboral.jsp"/>"> Al inicio de la vida laboral</a></strong></li>
    <li><strong><a href="<c:url value="/jsp/empleo/candidatos/duranteVidaLaboral.jsp"/>">Durante la vida laboral</a></strong></li>
    <li><strong><a href="<c:url value="/jsp/empleo/candidatos/alTerminoVidaLaboral.jsp"/>">Al t&eacute;rmino de la vida laboral</a></strong></li>
</ul>
       	   </div>
	      </div>
	     </div>



    </div>
	</jsp:body>
</t:publicTemplate>
