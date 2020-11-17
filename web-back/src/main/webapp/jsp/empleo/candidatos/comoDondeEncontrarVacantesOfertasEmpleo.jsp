<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="url" value="${pageContext.request.requestURI}" />

<t:publicTemplate>
	<jsp:attribute name="titulo">&iquest;D&oacute;nde encontrar ofertas de empleo?</jsp:attribute>
    <jsp:attribute name="palabraDescripcion">Candidatos</jsp:attribute> 
    <jsp:attribute name="tituloSitio">Portal del Empleo</jsp:attribute>
	<jsp:attribute name="urlRedSocial"><c:url value="${url}"/></jsp:attribute>
	<jsp:attribute name="descripcionRedSocial">
		El sue&ntilde;o de toda/todo buscadora/buscador es empezar a trabajar. Buscar un empleo requiere de cierta creatividad. Las empresas, adem&aacute;s de sus propios canales, usan medios como la radio, la prensa, boletines, portales especializados, portales privados y p&uacute;blicos, entre otros, para la promoci&oacute;n de sus vacantes. Para las/los buscadoras/buscadores hay muchas maneras de encontrar vacantes. Dependiendo de d&oacute;nde vivas; las siguientes son algunas de las fuentes m&aacute;s comunes:
	</jsp:attribute>
	<jsp:body>
	
	<!-- div ruta_navegacion -->
    <div class="row">
      <div class="col-sm-12">
        <ol class="breadcrumb">
          <li>Ruta de navegación:</li>
          <li><a href="<c:url value="/inicio.do"/>">Inicio</a></li>
          <li><a href="<c:url value="/jsp/empleo/candidatos/candidatos.jsp"/>">Candidatos</a></li>
          <li><a href="<c:url value="/jsp/empleo/candidatos/habilidadesBusquedaEmpleo.jsp"/>">Habilidades para la Búsqueda de Empleo (HPBE)</a></li>
          <li><a href="<c:url value="/jsp/empleo/candidatos/postula.jsp"/>">Postula</a></li>
          <li class="active">¿Cómo y dónde encontrar vacantes y ofertas de empleo?</li>
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
			<h2 class="titulosh2">¿Cómo y dónde encontrar vacantes y ofertas de empleo?</h2>
		
	        <p>El sue&ntilde;o de toda/todo buscadora/buscador es empezar a trabajar. Buscar un empleo requiere de cierta creatividad. Las empresas, adem&aacute;s de sus propios canales, usan medios como la radio, la prensa, boletines, portales especializados, portales privados y p&uacute;blicos, entre otros, para la promoci&oacute;n de sus vacantes. Para las/los buscadoras/buscadores hay muchas maneras de encontrar vacantes. Dependiendo de d&oacute;nde vivas; las siguientes son algunas de las fuentes m&aacute;s comunes:</p>
<ul class="list-group-contenidos">
    <li>El Servicio Nacional de Empleo.</li>
    <li>Avisos de &ldquo;Se busca...&rdquo;, &ldquo;Se necesita...&rdquo; que se publican en negocios, tiendas o mamparas p&uacute;blicas (sistema de transporte p&uacute;blico, escuelas, centros educativos, etc.)</li>
    <li>Peri&oacute;dicos y boletines que publican vacantes.</li>
    <li>Las oficinas municipales y delegaciones en sus &aacute;reas de vinculaci&oacute;n.</li>
    <li>Oficinas de intermediaci&oacute;n laboral privadas (tipo Manpower, Adecco, etc.)</li>
    <li>Sitios de empleo en internet (Portal del Empleo, Bumeran, Trabajando.com, etc.)</li>
    <li>Asociaciones de empleadores y profesionistas (COPARMEX, CANACO, CANACINTRA, Asociaci&oacute;n de Arquitectos, etc.)</li>
    <li>Amigos, parientes, vecinos y otros contactos personales (redes personales)</li>
    <li>Los centros educativos (UNAM, UAM, POLI, TEC, UVM, UA, etc.)</li>
</ul>
<p>Es importante que reflexiones sobre las siguientes preguntas: &iquest;Cu&aacute;les de ellas conoces? &iquest;Cu&aacute;les has usado? &iquest;Cu&aacute;les te han facilitado la b&uacute;squeda? El objetivo de tu reflexi&oacute;n es que comprendas que no hay una v&iacute;a &uacute;nica para lograr identificar las vacantes que te interesan.</p>
<p>Al acceder u obtener informaci&oacute;n en cada una de las fuentes enumeradas, debes establecer: &iquest;para qu&eacute; quiero esa informaci&oacute;n? Por lo que es importante que contemples que la informaci&oacute;n obtenida por cualquiera de estos medios debe estar orientada a:</p>
<ul class="list-group-contenidos">
    <li>Emplear de manera efectiva tu tiempo.</li>
    <li>Mantener tu motivaci&oacute;n en la b&uacute;squeda de empleo.</li>
    <li>Tomar decisiones sobre la informaci&oacute;n que signifique oportunidades reales para t&iacute;.</li>
    <li>Hacer que tus posibilidades de encontrar un empleo se multipliquen.</li>
</ul>
<p>Si cuentas con 10 minutos &eacute;chale un vistazo al siguiente v&iacute;nculo para que identifiques en qu&eacute; te pueden servir diversos <a href="${context}/download/candidatos/tipos_de_recursos_de_informacion.docx">tipos de recursos de informaci&oacute;n</a>.</p>
       	   </div>
	      </div>
	     </div>



    </div>
	</jsp:body>
</t:publicTemplate>
