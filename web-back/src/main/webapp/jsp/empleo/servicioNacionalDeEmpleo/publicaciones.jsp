<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="url" value="${pageContext.request.requestURI}" />


<t:publicTemplate>
	<jsp:attribute name="titulo">Publicaciones de empleo SNE</jsp:attribute>
    <jsp:attribute name="palabraDescripcion">Servicio Nacional de Empleo</jsp:attribute>  
    <jsp:attribute name="tituloSitio">Portal del Empleo</jsp:attribute>
 	<jsp:attribute name="urlRedSocial"><c:url value="${url}"/></jsp:attribute>
	<jsp:attribute name="descripcionRedSocial">
		La Secretar&iacute;a del Trabajo y Previsi&oacute;n Social, a trav&eacute;s del Servicio Nacional de Empleo (SNE) proporciona servicios de informaci&oacute;n a la poblaci&oacute;n desempleada y subempleada,  a fin de facilitar su inserci&oacute;n al mercado laboral. Es por ello que las publicaciones del SNE atienden necesidades de informaci&oacute;n espec&iacute;ficas de los buscadores de empleo y de los empleadores y algunos otros agentes que tienen intervenci&oacute;n en el mercado de trabajo. La informaci&oacute;n que proporcionan contribuye a la toma de decisiones de los destinatarios correspondientes.
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
          <li class="active">Publicaciones</li>
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
			<h2 class="titulosh2">Publicaciones</h2>
		
	        <p>La Secretar&iacute;a del Trabajo y Previsi&oacute;n Social, a trav&eacute;s del Servicio Nacional de Empleo (SNE) proporciona servicios de informaci&oacute;n a la poblaci&oacute;n desempleada y subempleada,  a fin de facilitar su inserci&oacute;n al mercado laboral. Es por ello que las publicaciones del SNE atienden necesidades de informaci&oacute;n espec&iacute;ficas de los buscadores de empleo y de los empleadores y algunos otros agentes que tienen intervenci&oacute;n en el mercado de trabajo. La informaci&oacute;n que proporcionan contribuye a la toma de decisiones de los destinatarios correspondientes.</p>
<p>Si eres buscador de empleo, cada quincena y de manera gratuita, encontrar&aacute;s en el peri&oacute;dico informaci&oacute;n detallada y vigente sobre las oportunidades de trabajo que capta el SNE, as&iacute; como orientaci&oacute;n sobre mejores pr&aacute;cticas en la b&uacute;squeda de empleo.</p>
<p>Si eres empleador o reclutador, a trav&eacute;s del peri&oacute;dico podr&aacute;s difundir las vacantes que requieras cubrir y sin costo alguno.</p>
<p>Se distribuye los d&iacute;as 1 y 16 de cada mes de manera f&iacute;sica en las oficinas del SNE as&iacute; como algunas otras dependencias gubernamentales y de manera virtual a trav&eacute;s del Portal del Empleo.</p>
<p>Por su parte, la revista informativa Empleo y Capacitaci&oacute;n, tiene como prop&oacute;sito apoyar la toma de decisiones de los empleadores as&iacute; como de las instituciones relacionadas con el empleo y la capacitaci&oacute;n, a trav&eacute;s de la difusi&oacute;n de informaci&oacute;n sobre las caracter&iacute;sticas y funcionamiento del mercado laboral local y de los resultados de las acciones de capacitaci&oacute;n llevadas a cabo por el SNE de cada entidad federativa.</p>
<p>Se distribuye cada trimestre de manera electr&oacute;nica a trav&eacute;s del Portal del Empleo.</p>
       	   
       	   </div>
	      </div>
	     </div>



    </div>
	</jsp:body>
</t:publicTemplate>
