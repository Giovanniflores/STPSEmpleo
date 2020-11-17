<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="url" value="${pageContext.request.requestURI}" />


<t:publicTemplate>
	<jsp:attribute name="titulo">Recibe ofertas v&iacute;a SMS</jsp:attribute>
    <jsp:attribute name="palabraDescripcion">Candidatos</jsp:attribute> 
    <jsp:attribute name="tituloSitio">Portal del Empleo</jsp:attribute>
 	<jsp:attribute name="urlRedSocial"><c:url value="${url}"/></jsp:attribute>
	<jsp:attribute name="descripcionRedSocial">
		Empleo en tu m&oacute;vil es un nuevo servicio para la b&uacute;squeda y consulta de oportunidades de empleo que te ofrece el Portal del Empleo. Este servicio est&aacute; basado en la tecnolog&iacute;a GSM (Sistema Global para las comunicaciones M&oacute;viles) y en el lenguaje de programaci&oacute;n WAP (Protocolo de Aplicaciones Inal&aacute;mbricas).
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
          <li class="active">Recibe ofertas vía SMS</li>
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
          <h2>Recibe ofertas en tu dispositivo móvil</h2>
			<h2 class="titulosh2">Recibe ofertas vía SMS</h2>
		
	        <p>Empleo en tu m&oacute;vil es un nuevo servicio para la b&uacute;squeda y consulta de oportunidades de empleo que te ofrece el Portal del Empleo. Este servicio est&aacute; basado en la tecnolog&iacute;a GSM (Sistema Global para las comunicaciones M&oacute;viles) y en el lenguaje de programaci&oacute;n WAP (Protocolo de Aplicaciones Inal&aacute;mbricas).</p>
			<p>Con este nuevo servicio, ahora tambi&eacute;n puedes utilizar tu tel&eacute;fono celular, PDA o cualquier dispositivo m&oacute;vil que tenga acceso a Internet para buscar y consultar las ofertas de empleo que se publican en el Portal del Empleo www.empleo.gob.mx, adem&aacute;s de conocer los datos de contacto para aplicar a ellas.</p>
			<p><strong>Instrucciones:</strong></p>
			<p>Digita en tu dispositivo la direcci&oacute;n www.empleo.gob.mx/movil, y al ingresar al sitio &eacute;ste te mostrar&aacute; un men&uacute; con cuatro opciones de b&uacute;squeda de ofertas de empleo ya predefinidas; selecciona una de ellas y sigue la secuencia de pantallas hasta llegar a la informaci&oacute;n de contacto de la oferta de empleo seleccionada.</p>
				<ul class="list-group-contenidos">
    			 <li>B&uacute;squeda por zona geogr&aacute;fica.</li>
    			 <li>B&uacute;squeda por zona metropolitana.</li>
    			 <li>B&uacute;squeda por entidad federativa.</li>
    			 <li>B&uacute;squeda por palabra clave.</li>
				</ul>
<p><strong>&iexcl;Nuestros servicios son gratuitos!</strong></p>
<p><strong>&iexcl;Estamos para servirte!</strong></p>		

       	   </div>
	      </div>
	     </div>



    </div>
	</jsp:body>
</t:publicTemplate>
