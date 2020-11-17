<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="url" value="${pageContext.request.requestURI}" />


<t:publicTemplate>
	<jsp:attribute name="titulo">Talleres en l&iacute;nea</jsp:attribute>
    <jsp:attribute name="palabraDescripcion">Servicio Nacional de Empleo</jsp:attribute>  
    <jsp:attribute name="tituloSitio">Portal del Empleo</jsp:attribute>
 	<jsp:attribute name="urlRedSocial"><c:url value="${url}"/></jsp:attribute>
	<jsp:attribute name="descripcionRedSocial">
		El taller en l&iacute;nea para buscadores de empleo tiene por objetivo que el buscador desarrolle un conjunto de habilidades. &iquest;De qu&eacute; hablamos cuando lo hacemos de habilidades? Hablamos de herramientas y de actitudes que son necesarias para la b&uacute;squeda de empleo.
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
          <li class="active">Talleres en línea</li>
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
			<h2 class="titulosh2">Talleres en línea</h2>
		
	        <p>El taller en l&iacute;nea para buscadores de empleo tiene por objetivo que el buscador desarrolle un conjunto de habilidades. &iquest;De qu&eacute; hablamos cuando lo hacemos de habilidades? Hablamos de herramientas y de actitudes que son necesarias para la b&uacute;squeda de empleo.</p>
<p>El taller aborda los siguientes temas:</p>
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
<p>La ventaja de los talleres en l&iacute;nea es que el buscador de empleo lo puede tomar en los momentos que tenga disponibles para acceder al sitio web &mdash;durante la vigencia del taller&mdash; y concluirlo de acuerdo a su disposici&oacute;n. El taller tiene validez y al concluirlo obtendr&aacute; un comprobante de que lo ha cursado.</p>
<p>Para tomar el taller en l&iacute;nea es necesario estar registrado y desde tu espacio personal podr&aacute;s ingresar a &eacute;l.</p>
       	   </div>
	      </div>
	     </div>



    </div>
	</jsp:body>
</t:publicTemplate>
