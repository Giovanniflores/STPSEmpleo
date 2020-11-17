<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="url" value="${pageContext.request.requestURI}" />

<t:publicTemplate>
	<jsp:attribute name="titulo">Bolsas de empleo asociadas</jsp:attribute>
    <jsp:attribute name="palabraDescripcion">Candidatos</jsp:attribute>  
    <jsp:attribute name="tituloSitio">Portal del Empleo</jsp:attribute>
 	<jsp:attribute name="urlRedSocial"><c:url value="${url}"/></jsp:attribute>
	<jsp:attribute name="descripcionRedSocial">
		Puedes tener acceso desde el Portal del Empleo a las ofertas de las principales  bolsas de trabajo por Internet, como OCCMundial, Bumeran, Manpower y Zonajobs. Asociaci&oacute;n Mexicana en Direcci&oacute;n de Recursos Humanos (AMEDIRH), Adecco, El Universal, as&iacute; como a las vacantes que se publican en la secci&oacute;n&nbsp;Trabaja En de la  Secretar&iacute;a de la Funci&oacute;n P&uacute;blica, en el Instituto Mexicano de la Juventud, en &nbsp; Empleos Verdes,&nbsp;en&nbsp;Turijobs M&eacute;xico y en Discapacidad y Empleo.
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
          <li class="active">Bolsas de empleo asociadas</li>
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
			<h2 class="titulosh2">Bolsas de empleo asociadas</h2>
		
	        <p>Puedes tener acceso desde el <strong>Portal del Empleo</strong> a las ofertas de las principales  bolsas de trabajo por Internet, como 
				<a href="http://www.occ.com.mx" target="_blank"><strong>OCC Mundial</strong></a>, 
				<!-- <a target="_blank" href="http://www.bumeran.com.mx"><strong>Bumeran</strong></a> -->   
				<a target="_blank" href="http://www.manpower.com.mx"><strong>Manpower</strong></a>, La
				<a target="_blank" href="http://www.amedirh.com.mx"> <strong>Asociaci&oacute;n Mexicana en Direcci&oacute;n de Recursos Humanos (AMEDIRH)</strong></a>, 
				<a target="_blank" href="http://www.adecco.com.mx"><strong>Adecco</strong></a>, 
				as&iacute; como a las vacantes que se publican en la secci&oacute;n&nbsp;
				<a target="_blank" href="http://www.trabajaen.gob.mx"><strong>Trabaja En</strong></a> 
				de la  Secretar&iacute;a de la Funci&oacute;n P&uacute;blica, en el 
				<a target="_blank" href="http://www.imjuventud.gob.mx/pagina.php?pag_id=92"><strong>Instituto Mexicano de la Juventud</strong></a>, 
				en&nbsp; 
				<a target="_blank" href="http://www.empleosverdes.com.mx/"><strong>Empleos Verdes</strong></a>,
				&nbsp;en&nbsp;
				<a target="_blank" href="http://www.turijobs.com.mx/"><strong>Turijobs M&eacute;xico</strong></a> 
				y en 
				<a target="_blank" href="http://www.discapacidadyempleo.com.mx/"><strong>Discapacidad y Empleo</strong></a>.
			</p>
			
		<div class="row">
	  		 <div class="col-sm-4 col-xs-6">
	  		 <a target="_blank" href="https://www.occ.com.mx/">
	  		 	<img alt="" src="${context}/css/images/contenido/occ.png" class="img-responsive"/></a>
			 </div>
	  		 <div class="col-sm-4 col-xs-6">
	  		 <a target="_blank" href="http://www.manpower.com.mx/wps/portal/web/">
				<img alt="" src="${context}/css/images/contenido/manpower.png" class="img-responsive"/></a>
			</div>
		
	  		<!-- <div class="col-sm-4 col-xs-6">
	  		<a target="_blank" href="http://www.bumeran.com.mx/">
	  		 	<img alt="" src="${context}/css/images/contenido/bumeran.png" class="img-responsive"/></a>
	  		 </div> -->
	  		 <div class="col-sm-4 col-xs-6">
	  		<a target="_blank" href="https://www.trabajos.mx/">
	  		 	<img alt="" src="${context}/images/otras-Trabajos-MX.png" class="img-responsive"/></a>
	  		 </div>
	  		 <div class="col-sm-4 col-xs-6">
	  		 <a target="_blank" href="http://www.adecco.com.mx/">
	  		 	<img alt="" src="${context}/css/images/contenido/adecco.png" class="img-responsive"/></a>
			 </div>
	  		 <div class="col-sm-4 col-xs-6">
	  		 <a target="_blank" href="http://www.amedirh.com.mx/">
				<img alt="" src="${context}/css/images/contenido/amedirh.png" class="img-responsive"/></a>
			</div>
			
	  		 <div class="col-sm-4 col-xs-6">
	  		 <a target="_blank" href="http://www.trabajaen.gob.mx/menuini/js_paginad.jsp">
	  		 	<img alt="" src="${context}/css/images/contenido/trabajaen.png" class="img-responsive"/></a>
	  		 </div>
	  		 <div class="col-sm-4 col-xs-6">
	  		 	<a target="_blank" href="http://www.imjuventud.gob.mx/pagina.php?pag_id=92">
	  		 	<img alt="" src="${context}/css/images/contenido/imjuve.png" class="img-responsive"/></a>
			 </div>
	  		 <div class="col-sm-4 col-xs-6">
	  		 <a target="_blank" href="http://empleosverdes.com.mx/">
				<img alt="" src="${context}/css/images/contenido/empleos-verdes2012.png" class="img-responsive"/></a>
			</div>
	  		 <div class="col-sm-4 col-xs-6">
	  		 <a target="_blank" href="http://www.turijobs.com.mx/">
	  		 	<img alt="" src="${context}/css/images/contenido/turijobs.png" class="img-responsive"/></a>
			 </div>
			<div class="col-sm-4 col-xs-6">
			<a target="_blank" href="https://superchamba.com/">
				<img alt="" src="${context}/images/otras-superchamba.png" class="img-responsive"/></a>
			</div>
			<div class="col-sm-4 col-xs-6">
	  		 <a target="_blank" href="http://www.discapacidadyempleo.com.mx/">
				<img alt="" src="${context}/css/images/contenido/discapacidadyempleo.png" class="img-responsive"/></a>
			</div>
		</div>		

       	   </div>
	      </div>
	     </div>



    </div>
	</jsp:body>
</t:publicTemplate>
