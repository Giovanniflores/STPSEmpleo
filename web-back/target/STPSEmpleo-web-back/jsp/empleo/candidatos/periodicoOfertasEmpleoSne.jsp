<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="url" value="${pageContext.request.requestURI}" />

<t:publicTemplate>
	<jsp:attribute name="titulo">Periódico de ofertas de empleo del SNE</jsp:attribute>
    <jsp:attribute name="palabraDescripcion">Candidatos</jsp:attribute>  
    <jsp:attribute name="tituloSitio">Portal del Empleo</jsp:attribute>
 	<jsp:attribute name="urlRedSocial"><c:url value="${url}"/></jsp:attribute>
	<jsp:attribute name="descripcionRedSocial">
		Es una publicaci&oacute;n de la Secretar&iacute;a del Trabajo y Previsi&oacute;n Social (STPS) y el Servicio Nacional de Empleo (SNE) que tiene la finalidad de ampliar las opciones de oportunidades de trabajo que se ofrecen en el Portal del Empleo de una manera sencilla y accesible.
	</jsp:attribute>
	<jsp:body>
	
	<!-- div ruta_navegacion -->
    <div class="row">
      <div class="col-sm-12">
        <ol class="breadcrumb">
          <li>Ruta de navegación:</li>
          <li><a href="<c:url value="/inicio.do"/>">Inicio</a></li>
          <li><a href="<c:url value="/jsp/empleo/candidatos/candidatos.jsp"/>">Candidatos</a></li>
          <li><a href="<c:url value="/jsp/empleo/candidatos/alternativasBusquedaEmpleo.jsp"/>">Alternativas de búsqueda de empleo</a></li>
          <li class="active">Periódico de ofertas de empleo del SNE</li>
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
			<h2 class="titulosh2">Periódico de ofertas de empleo del SNE</h2>
		
	        <p>Es una publicaci&oacute;n de la Secretar&iacute;a del Trabajo y Previsi&oacute;n Social (STPS) y el Servicio Nacional de Empleo (SNE) que tiene la finalidad de ampliar las opciones de oportunidades de trabajo que se ofrecen en el Portal del Empleo de una manera sencilla y accesible.</p>
			<p>El peri&oacute;dico de ofertas de empleo del SNE se edita quincenalmente en todas las entidades federativas del pa&iacute;s a trav&eacute;s de las oficinas del organismo.</p>
			<p>Para mayor facilidad, es posible descargarlo del sitio, con actualizaciones los d&iacute;as 1 y 15 de cada mes.</p>
			<p>Si deseas encontrar ofertas de una forma m&aacute;s r&aacute;pida, utiliza la <a href="<c:url value="/busquedaEspecificaOfertas.do?method=buscar"/>">B&uacute;squeda espec&iacute;fica</a> y <a href="<c:url value="/registro_candidato.do"/>">Reg&iacute;strate</a> en el portal.</p>
			<p>Nota: Para consultar y descargar el peri&oacute;dico de ofertas de empleo del SNE requieres tener instalado</p>
			<a href="http://get.adobe.com/es/reader/" target="_blank">
			<img alt="" src="${context}/css/images/contenido/getacro.gif" class="img-responsive"/></a>
					

       	   </div>
	      </div>
	     </div>



    </div>
	</jsp:body>
</t:publicTemplate>
