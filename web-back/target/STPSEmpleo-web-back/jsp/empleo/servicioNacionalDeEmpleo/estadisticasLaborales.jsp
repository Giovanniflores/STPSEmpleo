<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="ftp" value="${FTP_STATIC_CONTENT}" />
<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="url" value="${pageContext.request.requestURI}" />

<t:publicTemplate>
	<jsp:attribute name="titulo">Estad&iacute;sticas Laborales</jsp:attribute>
    <jsp:attribute name="palabraDescripcion">Servicio Nacional de Empleo</jsp:attribute>  
    <jsp:attribute name="tituloSitio">Portal del Empleo</jsp:attribute>
 	<jsp:attribute name="urlRedSocial"><c:url value="${url}"/></jsp:attribute>
	<jsp:attribute name="descripcionRedSocial">
		Esta sección tiene el propósito de ofrecer información relevante del mercado de trabajo a partir de una mejor organización de la información, así como el acceso y descarga de datos. Además, presenta por separado cifras recientes e históricas.
	</jsp:attribute>
	<jsp:body>
	
	<!-- div ruta_navegacion -->
    <div class="row">
      <div class="col-sm-12">
        <ol class="breadcrumb">
          <li>Ruta de navegación:</li>
          <li><a href="<c:url value="/inicio.do"/>">Inicio</a></li>
          <li><a href="<c:url value="/jsp/empleo/servicioNacionalDeEmpleo/servicioNacionalDeEmpleo.jsp"/>">Servicio Nacional de Empleo</a></li>
          <li class="active">Estadísticas laborales</li>
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
            <img alt="" src="${context}/css/images/contenido/estadisticas_laborales.png" class="img-responsive">
          </div>
          <div class="col-md-6">
            <h2>Estadísticas laborales</h2>
            <p>Esta sección tiene el propósito de ofrecer información relevante del mercado de trabajo a partir de una mejor organización de la información, así como el acceso y descarga de datos. Además, presenta por separado cifras recientes e históricas.</p>
          </div>
        </div>
        </div>

        <div class="clearfix"></div>
        
        <div class="panel">
          <div class="panel-body">

	        <p>La coordinación General del Servicio Nacional del Empleo, presenta la serie estadística por cada uno de los programas que opera del año 2001 a la fecha. Estas series contienen un recuento de los avances alcanzados a nivel nacional de los programas.</p>
	       
	       </div>
	     </div>
       
      </div>



    </div>
	</jsp:body>
</t:publicTemplate>
