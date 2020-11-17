<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="url" value="${pageContext.request.requestURI}" />

<t:publicTemplate>
	<jsp:attribute name="titulo">B&eacute;cate</jsp:attribute>
    <jsp:attribute name="palabraDescripcion">Servicio Nacional de Empleo</jsp:attribute>  
    <jsp:attribute name="tituloSitio">Portal del Empleo</jsp:attribute>
 	<jsp:attribute name="urlRedSocial"><c:url value="${url}"/></jsp:attribute>
	<jsp:attribute name="descripcionRedSocial">
		El subprograma B&eacute;cate apoya a buscadores de empleo que requieran capacitarse para facilitar su colocaci&oacute;n en un empleo o el desarrollo de una actividad productiva por cuenta propia.
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
          <li><a href="<c:url value="/jsp/empleo/servicioNacionalDeEmpleo/programasApoyoEmpleo.jsp"/>">Programas de Apoyo al Empleo</a></li>
          <li class="active">Becas a la Capacitación para el Trabajo (Bécate)</li>
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
			<h2 class="titulosh2">Becas a la Capacitación para el Trabajo (Bécate)</h2>
		
	        <p>El subprograma B&eacute;cate apoya a buscadores de empleo que requieran capacitarse para facilitar su colocaci&oacute;n en un empleo o el desarrollo de una actividad productiva por cuenta propia.</p>
<p>A trav&eacute;s de este subprograma, recibir&aacute;s capacitaci&oacute;n de entre uno y tres meses, dependiendo del programa de capacitaci&oacute;n. Durante el per&iacute;odo de capacitaci&oacute;n, se te otorgar&aacute; un apoyo econ&oacute;mico (beca de capacitaci&oacute;n) de uno a tres salarios m&iacute;nimos de la zona econ&oacute;mica donde se realice el curso, por cada d&iacute;a que asistas, as&iacute; como ayuda para transporte de $20, por cada d&iacute;a que asistas al curso de capacitaci&oacute;n</p>
<p>B&eacute;cate cuenta con las siguientes modalidades:</p>
<ul class="list-group-contenidos">
    <li><strong>Capacitaci&oacute;n Mixta.</strong><br />
    Para quienes aspiren a ser contratados por una empresa que requiera personal capacitado de acuerdo a los requerimientos de sus vacantes disponibles.</li>
    <li><strong>Capacitaci&oacute;n en la pr&aacute;ctica laboral.</strong><br />
    Para quienes deseen adquirir o fortalecer sus competencias laborales capacit&aacute;ndose directamente en el proceso productivo de una empresa.</li>
    <li><strong>Capacitaci&oacute;n para el autoempleo.</strong><br />
    Para quienes no logran vincularse a un puesto de trabajo y deseen desarrollar una actividad productiva por cuenta propia.</li>
    <li><strong>Vales de capacitaci&oacute;n.</strong><br />
    Para quienes necesitan actualizar, mejorar y/o reconvertir sus competencias, habilidades y/o destrezas laborales con la finalidad de incrementar sus posibilidades de contrataci&oacute;n.</li>
</ul>
<p><strong>Requisitos:</strong></p>
<ol>
    <li>Realizar, personalmente, el tr&aacute;mite &ldquo;Solicitud de Registro en el Servicio Nacional de Empleo (SNE)&rdquo;.</li>
    <li>Llenar y firmar bajo protesta de decir verdad el formato &ldquo;Registro del Solicitante&rdquo;.</li>
    <li>Ser buscador de empleo.</li>
    <li>Tener 16 a&ntilde;os o m&aacute;s.</li>
    <li>Cubrir el perfil establecido en el programa de capacitaci&oacute;n a desarrollar.</li>
</ol>
<p>Presentar original (para su cotejo) y entregar copia simple legible de la siguiente documentaci&oacute;n:</p>
<ul class="list-group-contenidos">
    <li>Documento que acredite tu nivel m&aacute;ximo de escolaridad.</li>
    <li>Identificaci&oacute;n oficial (credencial para votar vigente expedida por el Instituto Nacional Electoral [INE], c&eacute;dula profesional, pasaporte vigente o Cartilla del Servicio Militar Nacional).</li>
    <li>Para el caso de menores de 18 a&ntilde;os, se aceptar&aacute; la identificaci&oacute;n con fotograf&iacute;a y firma, expedida por el Gobierno Federal, Estatal o Municipal. Trat&aacute;ndose de personas preliberadas, se aceptar&aacute; la carta de preliberaci&oacute;n que emita el Centro de Readaptaci&oacute;n Social correspondiente.</li>
    <li>Clave &Uacute;nica de Registro Poblaci&oacute;n (CURP). Si la identificaci&oacute;n oficial contiene impresa la clave CURP, no ser&aacute; necesario presentar este documento.</li>
</ul>
<!--  las 24 horas del día -->
<p>Para mayor información, acude a la <a href="<c:url value="/jsp/empleo/servicioNacionalDeEmpleo/ubicacion.jsp"/>">oficina</a> del Servicio Nacional de Empleo más cercana a tu domicilio. También, puedes llamarnos al 01 800 8 41 2020, sin ningún costo, ll&aacute;manos de 8 am a 10 pm los 365 días del año, y uno de nuestros consejeros con gusto te atenderá.</p>
       	   </div>
	      </div>
	     </div>



    </div>
	</jsp:body>
</t:publicTemplate>
