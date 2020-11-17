<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="url" value="${pageContext.request.requestURI}" />


<t:publicTemplate>
	<jsp:attribute name="titulo">Servicio Nacional de Empleo por Tel&eacute;fono</jsp:attribute>
    <jsp:attribute name="palabraDescripcion">Servicio Nacional de Empleo</jsp:attribute>  
    <jsp:attribute name="tituloSitio">Portal del Empleo</jsp:attribute>
 	<jsp:attribute name="urlRedSocial"><c:url value="${url}"/></jsp:attribute>
	<jsp:attribute name="descripcionRedSocial">
		El Servicio Nacional de Empleo (SNE) por tel&eacute;fono tiene como principales objetivos: facilitar la vinculaci&oacute;n entre oferentes y demandantes de empleo, informar a las personas sobre los servicios que proporciona el SNE, atender a los trabajadores que participan en el Programa de Trabajadores Agr&iacute;colas Temporales M&eacute;xico-Canad&aacute; y asesorar en la navegaci&oacute;n a los usuarios del Portal del Empleo. As&iacute; como registrar las vacantes que ofrecen los empleadores.
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
          <li class="active">Servicio Nacional de Empleo por Teléfono</li>
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
			<h2 class="titulosh2">Servicio Nacional de Empleo por Teléfono</h2>
		
	        <p>El Servicio Nacional de Empleo (SNE) por tel&eacute;fono tiene como principales objetivos: facilitar la vinculaci&oacute;n entre oferentes y demandantes de empleo, informar a las personas sobre los servicios que proporciona el SNE, atender a los trabajadores que participan en el Programa de Trabajadores Agr&iacute;colas Temporales M&eacute;xico-Canad&aacute; y asesorar en la navegaci&oacute;n a los usuarios del Portal del Empleo. As&iacute; como registrar las vacantes que ofrecen los empleadores.</p>
<p>Con este esquema, se atiende de 8 am a 10 pm  los 365 d&iacute;as del a&ntilde;o, a trav&eacute;s del n&uacute;mero gratuito 01 800 8 41 20 20, el cual ofrece:</p>
<ol>
    <li>Informaci&oacute;n sobre opciones de empleo en todo el pa&iacute;s que empaten con el perfil laboral del buscador de empleo.</li>
    <li>Informaci&oacute;n y asesor&iacute;a sobre el Portal del Empleo <a target="_blank" href="https://www.empleo.gob.mx">www.empleo.gob.mx</a>.</li>
    <li>Informaci&oacute;n sobre los servicios que brinda el SNE.</li>
    <li>Atenci&oacute;n a los trabajadores participantes en el Programa de Trabajadores Agr&iacute;colas Temporales M&eacute;xico-Canad&aacute;.</li>
    <li>Atenci&oacute;n a quejas y sugerencias de los usuarios del SNE.</li>
</ol>
<p>Adicionalmente, a trav&eacute;s de la p&aacute;gina del Portal del Empleo, se puede acceder a la asesor&iacute;a en l&iacute;nea, mediante la cual se gu&iacute;a al usuario por medio de una conversaci&oacute;n de texto (chat) para el uso y manejo del sitio.</p>
<p>Asimismo, se atienden las quejas, dudas y sugerencias que tengan los usuarios, respecto al Portal del Empleo o sobre lo servicios del SNE.</p>
       	   </div>
	      </div>
	     </div>



    </div>
	</jsp:body>
</t:publicTemplate>
