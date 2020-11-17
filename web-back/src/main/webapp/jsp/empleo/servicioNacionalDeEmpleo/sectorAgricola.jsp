<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="url" value="${pageContext.request.requestURI}" />

<t:publicTemplate>
	<jsp:attribute name="titulo">Sector agr&iacute;cola</jsp:attribute>
    <jsp:attribute name="palabraDescripcion">Servicio Nacional de Empleo</jsp:attribute>  
    <jsp:attribute name="tituloSitio">Portal del Empleo</jsp:attribute>
 	<jsp:attribute name="urlRedSocial"><c:url value="${url}"/></jsp:attribute>
	<jsp:attribute name="descripcionRedSocial">
		El subprograma de Movilidad Laboral en el sector agr&iacute;cola ayuda a vincular jornaleros en dicho sector, con apoyos econ&oacute;micos para que puedan desplazarse de sus lugares de origen a las zonas de destino y viceversa.
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
          <li><a href="${context}/jsp/empleo/servicioNacionalDeEmpleo/mecanismoMovilidadLaboralInternaExterna.jsp">Mecanismo de Movilidad Laboral interna y externa</a></li>
          <li><a href="${context}/jsp/empleo/servicioNacionalDeEmpleo/subprogramaMovilidadLaboral.jsp">Subprograma de Movilidad Laboral</a></li>
          <li class="active">Sector agrícola</li>
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
			<h2 class="titulosh2">Sector agrícola</h2>
		
	        <p>El subprograma de Movilidad Laboral en el sector agr&iacute;cola ayuda a vincular jornaleros en dicho sector, con apoyos econ&oacute;micos para que puedan desplazarse de sus lugares de origen a las zonas de destino y viceversa.</p>
<p>Las personas que est&eacute;n interesadas en recibir los apoyos de este subprograma deber&aacute;n cubrir los siguientes requisitos:</p>
<ol>
    <li>Realizar, personalmente, el tr&aacute;mite &ldquo;Solicitud de Registro en el Servicio Nacional de Empleo (SNE)&rdquo;.</li>
    <li>Llenar y firmar bajo protesta de decir verdad el formato &ldquo;Registro del Solicitante&rdquo; (formato SNE 01).</li>
    <li>Tener 16 a&ntilde;os o m&aacute;s.</li>
    <li>Ser jornalero agr&iacute;cola con disposici&oacute;n para cambiar de residencia, de manera temporal por motivos laborales.</li>
    <li>Estar desempleado o subempleado.</li>
    <li>Entregar una fotograf&iacute;a reciente, tama&ntilde;o infantil (para la elaboraci&oacute;n de la credencial).</li>
</ol>
<p>Presentar original (para su cotejo) y entregar copia simple legible de la siguiente documentaci&oacute;n:</p>
<ul class="list-group-contenidos">
    <li>Clave &Uacute;nica de Registro de Poblaci&oacute;n (CURP).</li>
    <li>Identificaci&oacute;n oficial vigente (expedida por el Instituto Nacional Electoral [INE]).</li>
    <li>En caso de ser menor de 18 a&ntilde;os, se aceptar&aacute; identificaci&oacute;n original con fotograf&iacute;a y firma que emita una autoridad local.</li>
</ul>
<p><strong>&iquest;Qu&eacute; apoyos otorgan?</strong></p>
<ol>
    <li>Informaci&oacute;n de las empresas agr&iacute;colas que solicitan tu trabajo.</li>
    <li>Colocaci&oacute;n en la empresa que elijas.</li>
    <li>Apoyo econ&oacute;mico para gastos de traslado, de tu lugar de origen hacia el lugar de trabajo y de retorno.</li>
    <li>Cumplimiento de las condiciones de trabajo conforme a la Ley Federal de Trabajo (LFT).</li>
</ol>
<p><strong>Movilidad Laboral sector agr&iacute;cola:</strong></p>
<p>Una oportunidad de empleo en los campos agr&iacute;colas de tu pa&iacute;s.</p>
       	   
       	   </div>
	      </div>
	     </div>



    </div>
	</jsp:body>
</t:publicTemplate>
