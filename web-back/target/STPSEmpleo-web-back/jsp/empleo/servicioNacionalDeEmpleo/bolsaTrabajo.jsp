<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="url" value="${pageContext.request.requestURI}" />


<t:publicTemplate>
	<jsp:attribute name="titulo">Bolsa de Trabajo del SNE</jsp:attribute>
    <jsp:attribute name="palabraDescripcion">Servicio Nacional de Empleo</jsp:attribute>  
    <jsp:attribute name="tituloSitio">Portal del Empleo</jsp:attribute>
 	<jsp:attribute name="urlRedSocial"><c:url value="${url}"/></jsp:attribute>
	<jsp:attribute name="descripcionRedSocial">
		Si requieres apoyo para encontrar opciones de trabajo acordes con tu perfil laboral, el Servicio Nacional de Empleo (SNE) cuenta con el servicio gratuito de Bolsa de Trabajo.
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
          <li class="active">Bolsa de Trabajo</li>
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
			<h2 class="titulosh2">Bolsa de Trabajo</h2>
		
	        <p>Si requieres apoyo para encontrar opciones de trabajo acordes con tu perfil laboral, el Servicio Nacional de Empleo (SNE) cuenta con el servicio gratuito de Bolsa de Trabajo.</p>
<p>La Bolsa de Trabajo del SNE cuenta con consejeros de empleo especializados que, a trav&eacute;s de una entrevista personalizada con los candidatos, identifican tus conocimientos, habilidades y experiencia para desempe&ntilde;ar un puesto de trabajo.</p>
<p>Posterior a la entrevista, se seleccionan las opciones laborales m&aacute;s adecuadas y, en caso de ser aceptada por los solicitantes de empleo, se env&iacute;an a las empresas con una carta de presentaci&oacute;n, para que sean entrevistados con miras a una posible contrataci&oacute;n.</p>
<p>El servicio de Bolsa de Trabajo es totalmente gratuito. Para ser solicitado, se debe acudir, personalmente, a cualquiera de nuestras 169 oficinas ubicadas en las 31 entidades federativas, as&iacute; como en las 16 delegaciones del Distrito Federal.</p>
<p>Consulta nuestro directorio de oficinas del SNE o ll&aacute;manos al 01 800 841 20 20 de 8 am a 10 pm a los 365 d&iacute;as del a&ntilde;o, y uno de nuestros consejeros con gusto te atender&aacute;.</p>
<p><strong>Requisitos</strong>: Presentarse con una identificaci&oacute;n oficial vigente, CURP y comprobante de domicilio en la <a href="<c:url value="/jsp/empleo/servicioNacionalDeEmpleo/ubicacion.jsp"/>">oficina</a> del Servicio Nacional de Empleo m&aacute;s cercana a tu domicilio.</p>
       	   </div>
	      </div>
	     </div>



    </div>
	</jsp:body>
</t:publicTemplate>
