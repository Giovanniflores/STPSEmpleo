<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="url" value="${pageContext.request.requestURI}" />



<t:publicTemplate>
	<jsp:attribute name="titulo">Empresas</jsp:attribute>
    <jsp:attribute name="palabraDescripcion">Empresas</jsp:attribute>  
    <jsp:attribute name="tituloSitio">Portal del Empleo</jsp:attribute>
 	<jsp:attribute name="urlRedSocial"><c:url value="${url}"/></jsp:attribute>
	<jsp:attribute name="descripcionRedSocial">
		  El Servicio Nacional de Empleo (SNE) te ofrece diversas herramientas y servicios para que encuentres candidatos acordes con tus ofertas de empleo. Accede a un espacio virtual para administrar tu información, reutiliza tus ofertas de empleo, agenda entrevistas en línea, busca candidatos a través de la herramienta Match, etc.	
    </jsp:attribute>
	<jsp:body>
		<!-- div ruta_navegacion -->
	    <div class="row">
	      <div class="col-sm-12">
	        <ol class="breadcrumb">
	          <li>Ruta de navegación:</li>
	          <li><a href="<c:url value="/inicio.do"/>">Inicio</a></li>
              <li class="active">Empresas</li>
	        </ol>
	      </div>
	    </div>
	    <!-- /div ruta_navegacion -->
	    
	    <div class="row">
		  <!-- div menu -->
		  <jsp:include page="menu.jsp"/>
		  
		  <!-- div contenido -->
	      <div class="col-sm-8 col-sm-pull-4">
	        <div class="panel panel-grey">
	          <div class="panel-body">
	          <div class="col-md-6">
	            <img alt="" src="${context}/css/images/contenido/empresas.png" class="img-responsive">
	          </div>
	          <div class="col-md-6">
	            <h2>Empresas</h2>
	            <p>Te ofrecemos diversas herramientas y servicios para que cubras, en el menor tiempo posible 
	            y de manera gratuita, tu puesto de trabajo vacante. Administra tu información y la de los 
	            postulantes a tus ofertas de empleo, encuentra el candidato idóneo a través de la herramienta 
	            match, entre otras cosas. </p>
	          </div>
	        </div>
	        </div>
	
	        <div class="clearfix"></div>
	
	        <h2 class="titulosh2">Descubre las herramientas que tenemos para ti</h2>
	        <div class="panel panel-grey">
	          <div class="panel-heading">
	            <h3 class="panel-title">Aprende cómo usarlas</h3>
	          </div>
	          <div class="panel-body">
	            ¡Olvídate de otras bolsas de trabajo! Regístrate en el Portal del Empleo y haz uso de las diferentes 
	            herramientas de búsqueda de candidatos de empleo. Administra la información de tus vacantes, contacta 
	            con los candidatos desde tu espacio virtual, publica empleos y más.
	          </div>
	        </div>
	
	        <h2 class="titulosh2">Conoce los servicios que tenemos para ti </h2>
	        <div class="panel panel-grey">
	          <div class="panel-heading">
	            <h3 class="panel-title">
	              <a href="javascript:dialogLoginPopup();">Publica ofertas de empleo</a>
	            </h3>
	          </div>
	          <div class="panel-body">
	            Inicia una sesión con un perfil de Empresa, registra ofertas de empleo y recibe a los candidatos para cubrirlas, en línea desde el Portal del Empleo 
	            o directo en tus oficinas a través de la bolsa de trabajo de la red de oficinas del Servicio 
	            Nacional de Empleo (SNE). También, registra ofertas de empleo para Abriendo Espacios (Personas con 
	            discapacidad y adultos mayores).
	          </div>
	        </div>
	
	        <div class="panel panel-transparent">
	          <div class="panel-heading">
	            <h3 class="panel-title">
	              <a href="<c:url value="/jsp/empleo/herramientasDelSitio/eventos.jsp"/>">Participa en las Ferias de Empleo</a>
	            </h3>
	          </div>
	          <div class="panel-body">
	            Encuentra candidatos para tus puestos de trabajo de manera rápida y sencilla, participando en nuestras 
	            Ferias de Empleo. Ahorra tiempo, recibiendo en tu localidad, la solicitud de empleo o curriculum vitae 
	            de varios postulantes. ¡Te esperamos en nuestros próximos eventos!
	          </div>
	        </div>
	        <div class="panel panel-grey">
	          <div class="panel-heading">
	            <h3 class="panel-title">
	              <a href="<c:url value="/jsp/empleo/servicioNacionalDeEmpleo/revistaInformativaDelSNE.jsp"/>">Consulta la revista informativa del SNE</a>
	            </h3>
	          </div>
	          <div class="panel-body">
	            Conoce periódicamente el resultado de los programas que ofrece el Servicio Nacional de Empleo en cada 
	            entidad federativa y en beneficio de la población. ¡Consulta la revista informativa!
	          </div>
	        </div>
	
	        <!-- div class="panel panel-transparent">
	          <div class="panel-heading">
	            <h3 class="panel-title">
	              <a href="<c:url value="/jsp/empleo/herramientasDelSitio/contacto.jsp"/>">Recibe asesoría telefónica</a>
	            </h3>
	          </div>
	          <div class="panel-body">
	            Para resolver cualquier duda o solicitar asesoría sobre los programas que opera el SNE, llámanos al 
	            Centro de Atención Telefónica, 01 800 841 20 20,  las 24 horas del día y los 365 días del año; donde 
	            uno de nuestros asesores con gusto te atenderá.
	          </div>
	        </div  -->
	        
	        <div class="panel panel-transparent">
	          <div class="panel-heading">
	            <h3 class="panel-title">

	              <a href="<c:url value="/miespacionav.do?method=agendaCita"/>">Agenda una cita</a>
	            </h3>
	          </div>
	          <div class="panel-body">
	            Desde la comodidad de tu oficina, solicita una cita para ser atendido, en la promoción de tus vacantes 
	            de empleo, en la bolsa de trabajo de la red de oficinas del Servicio Nacional de Empleo.
	          </div>
	        </div>
	
	        <div class="panel panel-grey">
	          <div class="panel-heading">
	            <h3 class="panel-title">
	              <a href="<c:url value="/jsp/empleo/candidatos/certificacionCompetenciasConocer.jsp"/>">Aumenta la competitividad de tu empresa</a>
	            </h3>
	          </div>
	          <div class="panel-body">
	            A través del Sistema Nacional de Competencias (Conocer), una instancia que certifica las habilidades 
	            de las personas, contrata personal calificado para desempeñar las funciones que exigen tus vacantes. 
	            Si deseas más información, ingresa a la sección de Conocer o ingresa a <a href="<c:url value="/jsp/empleo/candidatos/certificacionCompetenciasConocer.jsp"/>">www.conocer.gob.mx.</a> 
	          </div>
	        </div>
	
	        
	      </div>
	      <!-- /div contenido -->
	    </div>
	    
	    
	    
	    
	</jsp:body>
</t:publicTemplate>
