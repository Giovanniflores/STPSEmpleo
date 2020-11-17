<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="url" value="${pageContext.request.requestURI}" />

<t:publicTemplate>
	<jsp:attribute name="titulo">Movilidad Laboral Externa</jsp:attribute>
    <jsp:attribute name="palabraDescripcion">Servicio Nacional de Empleo</jsp:attribute>  
    <jsp:attribute name="tituloSitio">Portal del Empleo</jsp:attribute>
 	<jsp:attribute name="urlRedSocial"><c:url value="${url}"/></jsp:attribute>
	<jsp:attribute name="descripcionRedSocial">
		El Mecanismo de Movilidad Laboral es normado y coordinado por los gobiernos federales de M&eacute;xico y Canad&aacute;, mediante el cual se comprometen a impulsar en forma ordenada, legal y segura el flujo de trabajadores temporales mexicanos a Canad&aacute;.
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
          <li class="active">Movilidad Laboral Externa</li>
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
			<h2 class="titulosh2">Movilidad Laboral Externa</h2>
		
	        <p>El Mecanismo de Movilidad Laboral es normado y coordinado por los gobiernos federales de M&eacute;xico y Canad&aacute;, mediante el cual se comprometen a impulsar en forma ordenada, legal y segura el flujo de trabajadores temporales mexicanos a Canad&aacute;.</p>
<p>Con base en las necesidades de personal de los empleadores canadienses, las Oficinas del Servicio Nacional de Empleo reclutan y seleccionan a personas desempleadas y subempleadas que cuenten con la experiencia laboral m&iacute;nima requerida por el empleador en las ocupaciones ofertadas. Es importante destacar que el SNE es la &uacute;nica instancia autorizada para el reclutamiento, selecci&oacute;n y contrataci&oacute;n de trabajadores, garantizando de este modo que la atenci&oacute;n que se les brinda sea personal y gratuita, sin la participaci&oacute;n de intermediarios.</p>
<h3>Requisitos:</h3>
<ul class="list-group-contenidos">
    <li>Ser de nacionalidad mexicana y residir en el territorio nacional.</li>
    <li>Contar con una identificaci&oacute;n oficial vigente (IFE / INE, Pasaporte o Cartilla Militar).</li>
    <li>Contar con la C&eacute;dula &Uacute;nica de Registro de Poblaci&oacute;n (CURP).</li>
    <li>Comprobante de &uacute;ltimo grado de estudios.</li>
    <li>Acreditar el dominio del idioma de acuerdo al requerimiento del empleador y de la ocupaci&oacute;n ofertada.</li>
    <li>Tener experiencia laboral comprobable de acuerdo a la vacante ofertada.</li>
</ul>
<p>&nbsp;</p>
<h3>A d&oacute;nde dirigirse</h3>
<p>Los candidatos que cumplan con todos los requisitos mencionados anteriormente favor de acudir a la <a href="<c:url value="/jsp/empleo/servicioNacionalDeEmpleo/ubicacion.jsp"/>">Oficina del Servicio Nacional de Empleo</a> m&aacute;s cercana a su domicilio.</p>
<p>Para mayor informaci&oacute;n llame al 01800 841 20 20, ll&aacute;manos de 8 am a 10 pm los 365 d&iacute;as del a&ntilde;o desde cualquier parte de la Rep&uacute;blica Mexicana.</p>
 
       	   </div>
	      </div>
	     </div>



    </div>
	</jsp:body>
</t:publicTemplate>
