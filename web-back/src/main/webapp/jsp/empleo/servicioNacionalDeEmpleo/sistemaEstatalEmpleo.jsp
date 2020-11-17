<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="url" value="${pageContext.request.requestURI}" />

<t:publicTemplate>
	<jsp:attribute name="titulo">Sistema Estatal de Empleo</jsp:attribute>
    <jsp:attribute name="palabraDescripcion">Servicio Nacional de Empleo</jsp:attribute>  
    <jsp:attribute name="tituloSitio">Portal del Empleo</jsp:attribute>
 	<jsp:attribute name="urlRedSocial"><c:url value="${url}"/></jsp:attribute>
	<jsp:attribute name="descripcionRedSocial">
		El Sistema Estatal de Empleo es un mecanismo por el que establece y mantiene contacto el Servicio Nacional de Empleo (SNE) con áreas de reclutamiento y selección de personal de diversos agentes del mercado de trabajo tales como: empresas, agencias de colocación, instituciones educativas, cámaras empresariales, entre otros.
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
          <li class="active">Sistema Estatal de Empleo</li>
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
			<h2 class="titulosh2">Sistema Estatal de Empleo</h2>
		
	        <p>El Sistema Estatal de Empleo es un mecanismo por el que establece y mantiene contacto el Servicio Nacional de Empleo (SNE) con áreas de reclutamiento y selección de personal de diversos agentes del mercado de trabajo tales como: empresas, agencias de colocación, instituciones educativas, cámaras empresariales, entre otros.</p>
<p>Opera por medio de reuniones periódicas entre representantes del SNE y los agentes del mercado de trabajo, quienes intercambian información sobre los perfiles ocupacionales de personas que buscan activamente empleo y los puestos de trabajo disponibles, dándoles preferencia a las personas que enfrentan dificultades para insertarse en el mercado laboral.</p>
<p><strong>Requisito</strong>:</p>
<ul class="list-group-contenidos">
<li>Estar registrado en la Bolsa de Trabajo del SNE.</li>
</ul>
       	   </div>
	      </div>
	     </div>



    </div>
	</jsp:body>
</t:publicTemplate>
