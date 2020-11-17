<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="url" value="${pageContext.request.requestURI}" />


<t:publicTemplate>
	<jsp:attribute name="titulo">Movilidad Laboral Interna</jsp:attribute>
    <jsp:attribute name="palabraDescripcion">Servicio Nacional de Empleo</jsp:attribute>  
    <jsp:attribute name="tituloSitio">Portal del Empleo</jsp:attribute>
 	<jsp:attribute name="urlRedSocial"><c:url value="${url}"/></jsp:attribute>
	<jsp:attribute name="descripcionRedSocial">
		Es el subprograma que apoya econ&oacute;micamente a los Solicitantes de empleo que requieren trasladarse a Entidades federativas, municipios o localidades distintas al lugar de su residencia, con fines ocupacionales, para ocupar un puesto de trabajo acorde a su perfil laboral. Su operaci&oacute;n se lleva a cabo a trav&eacute;s de las siguientes modalidades:
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
          <li class="active">Movilidad Laboral Interna</li>
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
			<h2 class="titulosh2">Movilidad Laboral Interna</h2>
		
	        <p>Es el subprograma que apoya econ&oacute;micamente a los Solicitantes de empleo que requieren trasladarse a Entidades federativas, municipios o localidades distintas al lugar de su residencia, con fines ocupacionales, para ocupar un puesto de trabajo acorde a su perfil laboral. Su operaci&oacute;n se lleva a cabo a trav&eacute;s de las siguientes modalidades:</p>
<p><strong>a) Sector Agr&iacute;cola</strong><br />
Atiende a Solicitantes de empleo dedicados a actividades del campo como jornaleros agr&iacute;colas, que a petici&oacute;n de Empleadores desarrollan labores estacionales propias de este sector, que tengan disposici&oacute;n para cambiar de residencia de manera temporal.</p>
<p><strong>b) Sectores Industrial y de Servicios</strong><br />
Atiende a Solicitantes de empleo para facilitar su vinculaci&oacute;n laboral a oportunidades de trabajo identificadas en los sectores industrial y de servicios, que tengan disposici&oacute;n para cambiar de residencia de manera temporal y/o permanente.</p>
<h3>Requisitos:</h3>
<ul class="list-group-contenidos">
    <li>Ser buscador de empleo.</li>
    <li>Edad 16 a&ntilde;os o m&aacute;s.</li>
    <li>Estar registrado en el SNE.</li>
</ul>
<h3>Documentaci&oacute;n:</h3>
<ul class="list-group-contenidos">
    <li>Identificaci&oacute;n oficial vigente.</li>
    <li>CURP.</li>
    <li>Comprobante de domicilio.</li>
    <li>Documento que muestre la CLABE INTERBANCARIA de 18 posiciones (Opcional).</li>
</ul>
<p>Los documentos se deber&aacute;n presentar en original y copia legible, una vez cotejada la informaci&oacute;n, se devolver&aacute;n los originales.</p>
<p>Para mayor informaci&oacute;n, acude a la <a href="<c:url value="/jsp/empleo/servicioNacionalDeEmpleo/ubicacion.jsp"/>">oficina</a> del Servicio Nacional de Empleo m&aacute;s cercana a tu domicilio. Tambi&eacute;n, puedes llamarnos al 01 800 8 41 2020, sin ning&uacute;n costo, ll&aacute;manos de 8 am a 10 pm los 365 d&iacute;as del a&ntilde;o, y uno de nuestros consejeros con gusto te atender&aacute;.</p>
       	   </div>
	      </div>
	     </div>



    </div>
	</jsp:body>
</t:publicTemplate>
