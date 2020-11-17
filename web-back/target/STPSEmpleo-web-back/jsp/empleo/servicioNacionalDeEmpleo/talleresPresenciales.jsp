<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="ftp" value="${FTP_STATIC_CONTENT}" />
<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="url" value="${pageContext.request.requestURI}" />

<t:publicTemplate>
	<jsp:attribute name="titulo">Talleres presenciales</jsp:attribute>
    <jsp:attribute name="palabraDescripcion">Servicio Nacional de Empleo</jsp:attribute>  
    <jsp:attribute name="tituloSitio">Portal del Empleo</jsp:attribute>
 	<jsp:attribute name="urlRedSocial"><c:url value="${url}"/></jsp:attribute>
	<jsp:attribute name="descripcionRedSocial">
		Cuando el buscador de empleo se enfrenta una y otra vez al proceso de b&uacute;squeda y no logra incorporarse al mercado de trabajo, es necesario que haga un alto en el camino y eval&uacute;e  la posibilidad de inscribirse a un taller para buscadores de empleo en su modalidad presencial.
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
          <li class="active">Talleres presenciales</li>
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
			<h2 class="titulosh2">Talleres presenciales</h2>
		
	        <p>Cuando el buscador de empleo se enfrenta una y otra vez al proceso de b&uacute;squeda y no logra incorporarse al mercado de trabajo, es necesario que haga un alto en el camino y eval&uacute;e  la posibilidad de inscribirse a un taller para buscadores de empleo en su modalidad presencial.</p>
<p>Este tipo de talleres est&aacute; orientado a que los participantes, mediante el intercambio de experiencias y con la gu&iacute;a de un facilitador, reflexionen sobre lo que est&aacute;n haciendo mal, identifiquen qu&eacute; y c&oacute;mo lo pueden hacer mejor y lograr una mejor y m&aacute;s r&aacute;pida vinculaci&oacute;n a un empleo.</p>
<p>Los talleres presenciales est&aacute;n compuestos por dos m&oacute;dulos; Conoce y Postula. Cada uno se toma por separado y tiene una duraci&oacute;n de al menos 2 y m&aacute;ximo 3 horas.</p>
<p>La tem&aacute;tica que se aborda en cada m&oacute;dulo es:</p>
<p>Tema 1: Conoce</p>
<ul class="list-group-contenidos">
    <li>Las herramientas que te ofrece el SNE para la b&uacute;squeda de empleo.</li>
    <li>Conoce y descubre tus habilidades y capacidades.</li>
    <li>Identifica las caracter&iacute;sticas del mercado de trabajo.</li>
    <li>Define tu objetivo laboral.</li>
</ul>
<p>Tema 2: Postula</p>
<ul class="list-group-contenidos">
    <li>&iquest;C&oacute;mo y d&oacute;nde encontrar vacantes y ofertas de empleo?</li>
    <li>&iquest;C&oacute;mo redactar un CV?</li>
    <li>La carta de recomendaci&oacute;n.</li>
    <li>&iquest;C&oacute;mo superar la entrevista de trabajo?</li>
</ul>
<p>Si al buscador le interesa tomar el taller,  a trav&eacute;s del v&iacute;nculo podr&aacute; conocer el calendario de los eventos programados para el mes en la oficina de su inter&eacute;s; en &eacute;ste mismo podr&aacute; descargar el <a href="${ftp}/estadisticas/CALENDARIO_TBE.pdf" target="_blank">calendario de los eventos</a> programados.</p>
       	   </div>
	      </div>
	     </div>



    </div>
	</jsp:body>
</t:publicTemplate>
