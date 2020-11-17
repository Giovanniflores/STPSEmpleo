<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="ftp" value="${FTP_STATIC_CONTENT}" />
<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="url" value="${pageContext.request.requestURI}" />

<t:publicTemplate>
	<jsp:attribute name="titulo">Calendario</jsp:attribute>
	<jsp:attribute name="palabraDescripcion">Servicio Nacional de Empleo</jsp:attribute>  
	<jsp:attribute name="tituloSitio">Portal del Empleo</jsp:attribute>
 	<jsp:attribute name="urlRedSocial"><c:url value="${url}"/></jsp:attribute>
	<jsp:attribute name="descripcionRedSocial">
		Portal del Empleo: Calendario.
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
          <li><a href="<c:url value="/jsp/empleo/servicioNacionalDeEmpleo/talleresBuscadoresEmpleo.jsp"/>">Talleres para buscadores de empleo</a></li>
          <li><a href="<c:url value="/jsp/empleo/servicioNacionalDeEmpleo/talleresPresenciales.jsp"/>">Talleres presenciales</a></li>
          <li class="active">Calendario</li>
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
			<h2 class="titulosh2">Calendario</h2>
		
	        <div class="row">
	  		 <div class="col-sm-4 col-xs-6">
	  		 	
	  		 </div>
	  		 <div class="col-sm-4 col-xs-6">
	  		 <p>Talleres presenciales</p>
			 </div>
	  		 <div class="col-sm-4 col-xs-6">
	  		 <p>Descarga</p>
			</div>
		
	  		<div class="col-sm-4 col-xs-6">
	  		<p>Calendario</p>
	  		 </div>
	  		 <div class="col-sm-4 col-xs-6">
	  		 
			 </div>
	  		 <div class="col-sm-4 col-xs-6">
	  		 <a href="${ftp}/estadisticas/CALENDARIO_TBE.pdf" target="_blank">
				<img alt="" src="${context}/css/images/contenido/historico.bmp" class="img-responsive"/></a>
			</div>
			</div>
			
       	   </div>
	      </div>
	     </div>



    </div>
	</jsp:body>
</t:publicTemplate>
