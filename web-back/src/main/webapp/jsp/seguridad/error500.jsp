<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="url" value="${pageContext.request.requestURI}" />

<t:publicTemplate>
	<jsp:attribute name="titulo">Error interno del servidor</jsp:attribute>
    <jsp:attribute name="palabraDescripcion">Candidatos</jsp:attribute>  
    <jsp:attribute name="tituloSitio">Portal del empleo</jsp:attribute>
 	<jsp:attribute name="urlRedSocial"><c:url value="${url}"/></jsp:attribute>
	<jsp:attribute name="descripcionRedSocial">
		Por el momento se estan realizando actualizaciones a la p&aacute;gina, por favor intenta m&aacute;s tarde.
	</jsp:attribute>
	<jsp:body>
		<!-- div ruta_navegacion -->
	    <div class="row">
	      <div class="col-sm-12">
	        <ol class="breadcrumb">
	          <li>Ruta de navegación:</li>
	          <li><a href="<c:url value="/inicio.do"/>">Inicio</a></li>
          	  <li class="active">Actualizaciones en proceso.</li>
	        </ol>
	      </div>
	    </div>
	    <!-- /div ruta_navegacion -->
	    
		<div class="row">
			<div class="col-sm-8">
				<div id="error" class="contenedor">
					<div class="panel panel-grey">
						<div class="panel-body">
							<div class="col-md-6">
								<img class="img-responsive" src="${context}/css/images/alert-actualizacion.png" alt="">
							</div>
							<div class="col-md-6">
								<h2>Error 500<br>Lo sentimos...</h2>
								<p>Ha ocurrido un error y tu solicitud no puede ser completada. Por favor intenta m&aacute;s tarde.</p>
							</div>
						</div>
					</div>
                
					<p>&nbsp;</p>
					<div class="clearfix"></div>
				</div>
			</div>
		</div>
	    
	    
	    
	    
	</jsp:body>
</t:publicTemplate>
