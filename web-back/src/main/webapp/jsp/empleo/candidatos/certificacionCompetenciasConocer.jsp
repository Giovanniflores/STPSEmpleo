<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="url" value="${pageContext.request.requestURI}" />

<t:publicTemplate>
	<jsp:attribute name="titulo">Certificaci&oacute;n de competencias (CONOCER)</jsp:attribute>
    <jsp:attribute name="palabraDescripcion">Candidatos</jsp:attribute>  
    <jsp:attribute name="tituloSitio">Portal del Empleo</jsp:attribute>
 	<jsp:attribute name="urlRedSocial"><c:url value="${url}"/></jsp:attribute>
	<jsp:attribute name="descripcionRedSocial">
		 El Sistema Nacional de Competencias, es un instrumento del Gobierno Federal que contribuye a la competitividad econ&oacute;mica, el desarrollo educativo y el progreso social del pa&iacute;s con base en el capital humano.
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
          <li class="active">Certificación de competencias (CONOCER)</li>
        </ol>
      </div>
    </div>
    <!-- /div ruta_navegacion -->

    <!-- div ruta_navegacion -->
    <div class="row">
    
      <jsp:include page="menu.jsp" />
      
      <div class="col-sm-8 col-sm-pull-4">
      	 
      	 <jsp:include page="/WEB-INF/template/redes.jsp" />
      
        <div class="clearfix"></div>
		
		 <div class="panel">
          <div class="panel-body">
			<h2 class="titulosh2">Certificación de competencias (CONOCER)</h2>
		
	        <p>El Sistema Nacional de Competencias, es un instrumento del Gobierno Federal que contribuye a la competitividad econ&oacute;mica, el desarrollo educativo y el progreso social del pa&iacute;s con base en el capital humano.</p>
			<p>El Sistema Nacional de Competencias de las personas se compone de tres piezas clave:</p>
				<ul class="list-group-contenidos">
    			  <li>Comit&eacute;s Sectoriales de Gesti&oacute;n por Competencias</li>
    			  <li>Instrumentos de transferencia de conocimiento</li>
    			  <li>Red nacional de evaluaci&oacute;n y certificaci&oacute;n de competencias</li>
                </ul>
			 <img alt="Conocer"
							src="${context}/css/images/contenido/esquema_snc.jpg"
							class="img-responsive" />
			<p>
							<strong>CONOCER</strong>
						</p>
<p>La imagen anterior muestra informaci&oacute;n sobre el Sistema Nacional de Competencias de las personas, CONOCER, la cual se compone de tres piezas clave que son:</p>
<p>Comit&eacute;s Sectoriales de Gesti&oacute;n por Competencias: Definen la agenda de capital humano para la competitividad del sector al que representan.</p>
<p>Instrumentos de transferencia de conocimiento: Los Comit&eacute;s Sectoriales de Gesti&oacute;n por Competencias desarrollan e inscriben los est&aacute;ndares de competencia en el Registro Nacional de Est&aacute;ndares de Competencia RENEC.</p>
<p>Red nacional de evaluaci&oacute;n y certificaci&oacute;n de competencias: Eval&uacute;an y certifican a las personas con base en los est&aacute;ndares de competencia.</p>
<p>Todo esto da como resultado a personas certificadas m&aacute;s competentes en los sectores productivo, social, educativo y de gobierno.</p>
<p>El Sistema Nacional de Competencias brinda m&uacute;ltiples beneficios, entre ellos:</p>
<ul class="list-group-contenidos">
    <li>Posiciona a M&eacute;xico y a sus sectores productivos ante el mundo, como un destino seguro y rentable para inversiones productivas, dada la certificaci&oacute;n de sus trabajadores y estudiantes como personas competentes para las funciones clave que requieren.</li>
    <li>Contribuye al incremento de la competitividad y productividad  de las personas y de las empresas e instituciones.</li>
    <li>Contribuye al incremento del Producto Interno Bruto Nacional y per c&aacute;pita.</li>
    <li>Se genera un banco de capital intelectual con base en el Registro Nacional de Est&aacute;ndares de Competencia.</li>
</ul>
<p>M&aacute;s informaci&oacute;n sobre CONOCER para:</p>
<a href="<c:url value="/jsp/empleo/candidatos/certificacionCompetencias.jsp"/>">
	  		 	<img alt="Información para candidatos"
							src="${context}/css/images/contenido/boton_candidatos.png"
							class="img-responsive" />
						</a>
	  		 	
<a href="<c:url value="/jsp/empleo/candidatos/formasAumentarCompetitividad.jsp"/>">
<img alt="Información para empresas"
							src="${context}/css/images/contenido/boton_empresas.png"
							class="img-responsive" />
						</a>

<p>
							<strong>CONOCER Tel.</strong><br />
Desde la Cd. de M&eacute;xico <strong>2282-0200</strong><br />
Del interior marca <strong>01 800 288 26 66</strong>
						</p>
<p>M&aacute;s informaci&oacute;n en <a
								href="http://www.conocer.gob.mx" target="_blank">www.conocer.gob.mx</a>
						</p>
		

       	   </div>
	      </div>
	     </div>



    </div>
	</jsp:body>
</t:publicTemplate>
