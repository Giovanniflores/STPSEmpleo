<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="url" value="${pageContext.request.requestURI}" />


<t:publicTemplate>
	<jsp:attribute name="titulo">Talleres para buscadores de empleo</jsp:attribute>
    <jsp:attribute name="palabraDescripcion">Servicio Nacional de Empleo</jsp:attribute>  
    <jsp:attribute name="tituloSitio">Portal del Empleo</jsp:attribute>
 	<jsp:attribute name="urlRedSocial"><c:url value="${url}"/></jsp:attribute>
	<jsp:attribute name="descripcionRedSocial">
		Los talleres para buscadores de empleo son un recurso que pone a tu alcance informaci&oacute;n que se brinda a trav&eacute;s de los talleres presenciales y del taller en l&iacute;nea, m&oacute;dulos de atenci&oacute;n que ofrecen consejos de quienes emprendieron la tarea de encontrar un empleo acorde a su perfil laboral y expectativas.
	</jsp:attribute>
	<jsp:body>
	
	<!-- div ruta_navegacion -->
    <div class="row">
      <div class="col-sm-12">
        <ol class="breadcrumb">
          <li>Ruta de navegación:</li>
          <li><a href="<c:url value="/inicio.do"/>">Inicio</a></li>
          <li><a href="<c:url value="/jsp/empleo/servicioNacionalDeEmpleo/servicioNacionalDeEmpleo.jsp"/>">Servicio Nacional de Empleo</a></li>
          <li><a href="<c:url value="/jsp/empleo/servicioNacionalDeEmpleo/programasServiciosEmpleo.jsp"/>">Programas y servicios de empleo</a></li>
          <li><a href="<c:url value="/jsp/empleo/servicioNacionalDeEmpleo/vinculacionLaboral.jsp"/>">Vinculación laboral</a></li>
          <li class="active">Talleres para buscadores de empleo</li>
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
			<h2 class="titulosh2">Talleres para buscadores de empleo</h2>
		
	        <p>Los talleres para buscadores de empleo son un recurso que pone a tu alcance informaci&oacute;n que se brinda a trav&eacute;s de los talleres presenciales y del taller en l&iacute;nea, m&oacute;dulos de atenci&oacute;n que ofrecen consejos de quienes emprendieron la tarea de encontrar un empleo acorde a su perfil laboral y expectativas.</p>
<p>En estos talleres se encuentran consejos &uacute;tiles para mejorar la b&uacute;squeda de empleo, conocer las mejores vacantes, integrar la documentaci&oacute;n necesaria, lograr un contacto efectivo con el empleador, identificar y demostrar las cualidades personales, decidir por la mejor opci&oacute;n y conservar el empleo.</p>
       	   </div>
	      </div>
	     </div>



    </div>
	</jsp:body>
</t:publicTemplate>
