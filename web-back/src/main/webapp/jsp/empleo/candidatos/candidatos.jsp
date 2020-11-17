<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="url" value="${pageContext.request.requestURI}" />

<t:publicTemplate>
	<jsp:attribute name="titulo">Candidatos</jsp:attribute>
    <jsp:attribute name="palabraDescripcion">Candidatos</jsp:attribute>
	<jsp:attribute name="tituloSitio">Portal del Empleo</jsp:attribute>
 	<jsp:attribute name="urlRedSocial"><c:url value="${url}"/></jsp:attribute>
	<jsp:attribute name="descripcionRedSocial">
		El Servicio Nacional de Empleo (SNE) te ofrece diversas herramientas y servicios para que encuentres ofertas de empleo acordes con tu experiencia laboral. Accede a tu espacio personal para administrar tu información, busca ofertas de empleo, postúlate a ellas a través de la herramienta Match.
	</jsp:attribute>
	<jsp:body>
	
	<!-- div ruta_navegacion -->
    <div class="row">
      <div class="col-sm-12">
        <ol class="breadcrumb">
          <li>Ruta de navegación:</li>
          <li><a href="<c:url value="/inicio.do"/>">Inicio</a></li>
          <li class="active">Candidatos</li>
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
            <img alt="" src="${context}/css/images/contenido/candidatos.png" class="img-responsive">
          </div>
          <div class="col-md-6">
            <h2>Candidatos</h2>
            <p>Te ofrecemos diversas herramientas y servicios gratuitos para que encuentres ofertas de
            empleo acordes con tu experiencia laboral. Accede a un espacio personal para administrar 
            información, busca vacantes de empleo, postúlate a ellas y más.</p>
          </div>
        </div>
        </div>

        <div class="clearfix"></div>

        <h2 class="titulosh2">Descubre las herramientas que tenemos para ti</h2>
        <div class="panel panel-grey">
          <div class="panel-heading">
            <h3 class="panel-title">¡Aprende cómo usarlas!</h3>
          </div>
          <div class="panel-body">
            Al estar registrado, aumentarás las posibilidades de encontrar empleo con los múltiples 
            beneficios que tenemos para ti, como son: acceso a un espacio virtual, búsqueda específica, 
            postulación a ofertas laborales compatibles con tu perfil, envío de  ofertas de empleo a tu 
            celular, entre otras. 
          </div>
        </div>

        <h2 class="titulosh2">Conoce los servicios que tenemos para ti </h2>
        <div class="panel panel-grey">
          <div class="panel-heading">
            <h3 class="panel-title">
              <a href="<c:url value="/miespacionav.do?method=agendaCita"/>">Agenda una cita</a>
            </h3>
          </div>
          <div class="panel-body">
            Desde la comodidad de tu casa, solicita una cita para ser atendido en tu búsqueda de empleo, 
            en la bolsa de trabajo de la red de oficinas del Servicio Nacional de Empleo (SNE).
          </div>
        </div>

        <div class="panel panel-transparent">
          <div class="panel-heading">
            <h3 class="panel-title">
              <a href="<c:url value="/jsp/empleo/servicioNacionalDeEmpleo/periodicoDeOfertasDeEmpleo.jsp"/>">Consulta el periódico de ofertas de empleo del SNE</a>
            </h3>
          </div>
          <div class="panel-body">
            Entérate de las oportunidades laborales que ofrece el SNE. Quincenalmente, consulta el periódico 
            de ofertas de empleo que corresponde a tu entidad y encuentra empleos acordes con tu perfil. 
          </div>
        </div>

        <!-- div class="panel panel-grey">
          <div class="panel-heading">
            <h3 class="panel-title">
              <a href="<c:url value="/jsp/empleo/herramientasDelSitio/contacto.jsp"/>">Recibe asesoría telefónica</a>
            </h3>
          </div>
          <div class="panel-body">
            Para resolver cualquier duda o solicitar asesoría sobre los programas que opera el SNE, llámanos 
            al Centro de Atención Telefónica, 01 800 841 20 20,  las 24 horas del día y los 365 días del año; 
            donde uno de nuestros asesores con gusto te atenderá.
          </div>
        </div  -->
        
        <div class="panel panel panel-grey">
          <div class="panel-heading">
            <h3 class="panel-title">
              <a href="<c:url value="/jsp/empleo/candidatos/bolsasEmpleoAsociadas.jsp"/>">Busca empleo en otras bolsas de trabajo </a>
            </h3>
          </div>
          <div class="panel-body">
            Si no encontraste vacantes de empleo de tu interés en el Portal del Empleo, puedes acceder a otras 
            bolsas de trabajo como OCC, Manpower, Adecco, entre otras. 
          </div>
        </div>
        
        <div class="panel panel-transparent">
          <div class="panel-heading">
            <h3 class="panel-title">
   <!--       <a href="<c:url value="/jsp/empleo/candidatos/becasManpowerTdc.jsp"/> "> Prepárate con cursos en línea</a>-->
               Prepárate con cursos en línea
            </h3>  
          </div>
          <div class="panel-body">
            Adquiere y desarrolla nuevos conocimientos con las becas a la capacitación que el SNE tiene para ti. 
            Conoce <a target="_blank" href="https://www.procadist.gob.mx/portal/">PROCADIST</a>, la plataforma que la Secretaría del Trabajo y Previsión Social (STPS) pone a tu 
            disposición para capacitarte a distancia o en línea. <!--o ingresa a <a href="${context}/jsp/empleo/candidatos/becasManpowerTdc.jsp ">becas Manpower</a>, donde podrás inscribirte 
            a alguno de los 1 800 cursos que tiene en línea.-->
          </div>
        </div>
        
        <div class="panel panel-grey">
          <div class="panel-heading">
            <h3 class="panel-title">
              <a href="<c:url value="/jsp/empleo/herramientasDelSitio/eventos.jsp"/>">Participa en las Ferias de Empleo</a>
            </h3>
          </div>
          <div class="panel-body">
            Aumenta tus alternativas para encontrar empleo, participando en las Ferias de Empleo. Contacta directamente 
            con representantes de empresas de diferentes sectores y entrega en persona tu curriculum vitae y solicitud 
            de empleo. Para participar, inscríbete desde el Portal del Empleo. ¡Te esperamos en nuestros próximos eventos!
          </div>
        </div>
           
           <div class="panel panel-transparent">
          <div class="panel-heading">
            <h3 class="panel-title">
              <a href="<c:url value="/jsp/empleo/servicioNacionalDeEmpleo/estadisticasLaborales.jsp"/>">Conoce los resultados de nuestros programas</a>
            </h3>
          </div>
          <div class="panel-body">
            Entérate sobre los avances de cada uno de los programas que opera el SNE, a nivel nacional, a través de la 
            serie estadística desde 2011. 
          </div>
        </div>
        
        <div class="panel panel-grey">
          <div class="panel-heading">
            <h3 class="panel-title">
              	Instala nuestra nueva App
            </h3>
          </div>
          <div class="panel-body">
            Instala en tu dispositivo móvil la nueva <strong>App del Portal del Empleo</strong>, disponible para <a target="_blank" href="https://play.google.com/store/apps/details?id=mx.gob.stps.empleo.cn1&hl=es_419">Android</a> e <a target="_blank" href="https://itunes.apple.com/mx/app/app-del-portal-del-empleo/id976373643?mt=8">iOS</a>, y entérate de las ofertas 
            de empleo más recientes. Descargarla desde AppStore o en Google Play como EmpleoGobMX.
          </div>
        </div>             
        
      </div>
      <!-- /div contenido -->
    </div>
    
	</jsp:body>
</t:publicTemplate>
