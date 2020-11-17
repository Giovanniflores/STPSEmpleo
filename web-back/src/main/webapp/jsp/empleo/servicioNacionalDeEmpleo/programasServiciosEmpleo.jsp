<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="url" value="${pageContext.request.requestURI}" />


<t:publicTemplate>
	<jsp:attribute name="titulo">Programas y servicios de empleo del SNE</jsp:attribute>
    <jsp:attribute name="palabraDescripcion">Servicio Nacional de Empleo</jsp:attribute>  
    <jsp:attribute name="tituloSitio">Portal del Empleo</jsp:attribute>
 	<jsp:attribute name="urlRedSocial"><c:url value="${url}"/></jsp:attribute>
	<jsp:attribute name="descripcionRedSocial">
		El Servicio Nacional de Empleo se encuentra respaldado por un marco legal que establecieron las bases de su creación.
	</jsp:attribute>
	<jsp:body>
	
	<!-- div ruta_navegacion -->
    <div class="row">
      <div class="col-sm-12">
        <ol class="breadcrumb">
          <li>Ruta de navegación:</li>
          <li><a href="<c:url value="/inicio.do"/>">Inicio</a></li>
          <li><a href="<c:url value="/jsp/empleo/servicioNacionalDeEmpleo/servicioNacionalDeEmpleo.jsp"/>">Servicio Nacional de Empleo</a></li>
          <li class="active">Programas y servicios de empleo</li>
        </ol>
      </div>
    </div>
    <!-- /div ruta_navegacion -->

    <!-- div ruta_navegacion -->
    <div class="row">
    
      <jsp:include page="menu.jsp"/>
      
      <div class="col-sm-8 col-sm-pull-4">
      
      	 <jsp:include page="/WEB-INF/template/redes.jsp"/>
      
        <div class="panel panel-grey">
          <div class="panel-body">
          <div class="col-md-6">
            <img alt="" src="${context}/css/images/contenido/imagen_programasYservicios.png" class="img-responsive">
          </div>
          <div class="col-md-6">
            <h2>Programas y servicios de empleo</h2>
            <p>El Servicio Nacional de Empleo se encuentra respaldado por un marco legal que establecieron las bases de su creación.</p>
          </div>
        </div>
        </div>

        <div class="clearfix"></div>
        
        <div class="panel">
          <div class="panel-body">

	        <p>Benef&iacute;ciate de los servicios que te ofrece el SNE.</p>
<p>El Servicio Nacional de Empleo se encuentra respaldado por un <a href="<c:url value="/jsp/empleo/servicioNacionalDeEmpleo/marcoJuridico.jsp"/>">marco jur&iacute;dico</a> que establecieron las bases de su creaci&oacute;n.</p>
<p>Los servicios que ofrece el SNE a la poblaci&oacute;n se agrupan en tres grandes rubros que, a su vez, engloban una gran variedad de opciones para las actividades laborales:</p>
<p><strong>Servicios de Vinculaci&oacute;n Laboral</strong></p>
<ol>
    <li>Bolsa de trabajo.</li>
    <li>Talleres para buscadores de empleo.</li>
    <li>Ferias de empleo.</li>
    <li>Informaci&oacute;n laboral v&iacute;a telef&oacute;nica.</li>
    <li>Centros de intermediaci&oacute;n laboral.</li>
    <li>Kioscos de informaci&oacute;n.</li>
    <li>Peri&oacute;dicos de ofertas de empleo.</li>
    <li>Boletines informativos.</li>
</ol>
<p><strong>Apoyos econ&oacute;micos y capacitaci&oacute;n.</strong></p>
<ol>
    <li>Becas a la Capacitaci&oacute;n para el Trabajo (B&eacute;cate).</li>
    <li>Fomento al autoempleo.</li>
</ol>
<p><strong>Movilidad laboral interna y externa</strong></p>
<ol>
    <li>Programa de Trabajadores Agr&iacute;colas Temporales (M&eacute;xico-Canad&aacute;).</li>
    <li>Programa Especial para Repatriados.</li>
    <li>Programa de Apoyo a Jornaleros Agr&iacute;colas.</li>
</ol>
	       </div>
	     </div>
       
      </div>



    </div>
	</jsp:body>
</t:publicTemplate>
