<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="url" value="${pageContext.request.requestURI}" />

<t:publicTemplate>
	<jsp:attribute name="titulo">Oficinas de la PROFEDET</jsp:attribute>
    <jsp:attribute name="palabraDescripcion">Candidatos</jsp:attribute>  
    <jsp:attribute name="tituloSitio">Portal del Empleo</jsp:attribute>
 	<jsp:attribute name="urlRedSocial"><c:url value="${url}"/></jsp:attribute>
	<jsp:attribute name="descripcionRedSocial">
		Localización geográfica de las Procuradurías Federales y Auxiliares en el interior de la República Mexicana.
	</jsp:attribute>
	<jsp:body>
	
	<!-- div ruta_navegacion -->
    <div class="row">
      <div class="col-sm-12">
        <ol class="breadcrumb">
          <li>Ruta de navegación:</li>
          <li><a href="<c:url value="/inicio.do"/>">Inicio</a></li>
          <li class="active">Candidatos</li>
        </ol>
      </div>
    </div>
    <!-- /div ruta_navegacion -->
    

    <div class="row">
	  <!-- div menu -->
	  <jsp:include page="menu.jsp"/>
	  
	  <!-- div contenido -->
      <div class="col-sm-8 col-sm-pull-4">
        <!--div class="panel panel-grey">
          <div class="panel-body">
          <div class="col-md-12">
            <iframe class="embed-responsive-item" src="https://www.google.com/maps/d/embed?mid=zSWMxtTCYgPk.kWGI3v-k7Dsc" width="100%" height="430" ></iframe>
          </div>
        </div>
        </div-->

        <div class="panel panel-grey">
          <div class="panel-body">
          <div class="col-md-6">
          	<img alt="" src="${context}/css/images/jud-profedet.jpg" class="img-responsive" />
          </div>
          <div class="col-md-6">
            <h2>PROFEDET</h2>
            <p>Procuradur&iacute;a Federal de la Defensa del Trabajo</p>
          </div>
        </div>
        </div>

        <div class="clearfix"></div>
        <div class="panel">
          <div class="panel-body">
          	<h2>¿Qu&eacute; hacemos?</h2>
			<p><strong>La Procuradur&iacute;a Federal de la Defensa del Trabajo</strong> (<strong>PROFEDET</strong>) es un &oacute;rgano desconcentrado de la Secretar&iacute;a del Trabajo 
			y Previsi&oacute;n Social (STPS), que tiene la misi&oacute;n de proteger los derechos de los trabajadores ante la autoridad laboral, mediante los 
			servicios de <strong>asesor&iacute;a, conciliaci&oacute;n y representaci&oacute;n legal.</strong></p>
			<p><em style="font-size: 150%">Todos los servicios son gratuitos.</em></p>
			<p>Llama al 59 98 2000 o al 01 800 911 7877</p>
			<p>Escribe a <a href="mailto:orientacionprofedet@stps.gob.mx">orientacionprofedet@stps.gob.mx</a></p>
			<p>Conoce la <a target="_blank" href="http://www.gob.mx/profedet/articulos/procuradurias-foraneas?idiom=es">ubicación de las Procuradurías Foráneas</a></p>

	        </div>
	        <img style="margin:50px 0;-webkit-border-radius: 5px;-moz-border-radius: 5px;border-radius: 5px;" alt="" src="${context}/css/images/il-profedet.jpg" class="img-responsive" />
        </div>
        
        
               

              
        
      </div>
      <!-- /div contenido -->
    </div>
    
	</jsp:body>
</t:publicTemplate>
