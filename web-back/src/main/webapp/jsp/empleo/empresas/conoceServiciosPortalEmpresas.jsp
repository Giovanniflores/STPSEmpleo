<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="url" value="${pageContext.request.requestURI}" />


<t:publicTemplate>
	<jsp:attribute name="titulo">Conoce los servicios que el portal tiene para ti</jsp:attribute>
    <jsp:attribute name="palabraDescripcion">Empresas</jsp:attribute>  
    <jsp:attribute name="tituloSitio">Portal del Empleo</jsp:attribute>
 	<jsp:attribute name="urlRedSocial"><c:url value="${url}"/></jsp:attribute>
	<jsp:attribute name="descripcionRedSocial">
		El Servicio Nacional de Empleo (SNE) te ofrece diversos servicios, para apoyarte en la búsqueda de candidatos acordes con tus ofertas de empleo. Entérate de todos los servicios que el SNE tiene para ti.
	</jsp:attribute>
	<jsp:body>
	
	<!-- div ruta_navegacion -->
    <div class="row">
      <div class="col-sm-12">
        <ol class="breadcrumb">
          <li>Ruta de navegación:</li>
          <li><a href="<c:url value="/inicio.do"/>">Inicio</a></li>
          <li><a href="<c:url value="/jsp/empleo/empresas/empresas.jsp"/>">Empresas</a></li>
          <li class="active">Conoce los servicios que el portal tiene para ti</li>
        </ol>
      </div>
    </div>
    <!-- /div ruta_navegacion -->

    <!-- div ruta_navegacion -->
    <div class="row">
    
      <jsp:include page="menu.jsp" />
      
      <div class="col-sm-8 col-sm-pull-4">
      
      <jsp:include page="/WEB-INF/template/redes.jsp" />
      	      
        <div class="panel panel-grey">
          <div class="panel-body">
          <div class="col-md-6">
            <img alt=""
								src="${context}/css/images/contenido/como_usar.png"
								class="img-responsive">
          </div>
          <div class="col-md-6">
            <h2>Conoce los servicios que el portal tiene para ti</h2>
            <p>El Servicio Nacional de Empleo (SNE) te ofrece diversos servicios, para apoyarte en la búsqueda de candidatos acordes con tus ofertas de empleo. Entérate de todos los servicios que el SNE tiene para ti.</p>
          </div>
        </div>
        </div>

        <div class="clearfix"></div>
        
        <div class="panel">
          <div class="panel-body">

	        <p>El Servicio Nacional de Empleo (SNE) cuenta con diversos servicios que te ser&aacute;n &uacute;tiles para cubrir tus vacantes f&aacute;cilmente.</p>
<p>Haz uso de todos los beneficios de tu registro, as&iacute; como de todas las herramientas y servicios que te ofrece el SNE, registrando ofertas de empleo para el Portal del Empleo y para el programa Abriendo Espacios. Cubre tus vacantes de una manera r&aacute;pida y sencilla.</p>
<p>Participa en las <a
								href="http://ferias.empleo.gob.mx/content/common/home.jsf"
								target="_blank">Ferias de Empleo</a>, un medio de vinculaci&oacute;n directo y &aacute;gil que ofrece el SNE en todo el pa&iacute;s. Contamos con las modalidades presencial y virtual para reducir tiempo y costos en el proceso de reclutamiento y encuentres al candidato ideal para tus ofertas de empleo.</p>
<p>Si deseas conocer el alcance de los programas emprendidos por el SNE, consulta y descarga la Revista Informativa del SNE. Esta publicaci&oacute;n se encuentra disponible, trimestralmente, en las oficinas del SNE; as&iacute; como en una versi&oacute;n electr&oacute;nica, <a
								href="<c:url value="/jsp/empleo/servicioNacionalDeEmpleo/revistaInformativaDelSNE.jsp"/>">haz clic aqu&iacute; para descargarla</a>. Tambi&eacute;n podr&aacute;s consultar los resultados de los programas emprendidos por el SNE en las 32 entidades federativas del pa&iacute;s, descarga y consulta las <a
								href="<c:url value="/jsp/empleo/servicioNacionalDeEmpleo/estadisticasLaborales.jsp"/>">estad&iacute;sticas laborales</a>, en formato Excel, desde 2001 a la fecha.</p>
<p>El SNE cuenta con servicio telef&oacute;nico, ll&aacute;manos de 8 am a 10 pm , los 365 d&iacute;as del a&ntilde;o. Si tienes alguna duda, queja o sugerencia, ll&aacute;manos al  01800 8412020 para ser atendido por uno de nuestros consejeros. Si lo requieres, podemos agendarte una cita en cualquiera de nuestras oficinas.</p>
<p>Utiliza nuestro nuevo Sistema de Citas para:</p>
<ul class="list-group-contenidos">
    <li>Recibir atenci&oacute;n personalizada, sobre el &aacute;mbito laboral.</li>
    <li>Conocer los servicios que el SNE tiene para tu empresa.</li>
    <li>Resolver dudas sobre la publicaci&oacute;n de ofertas de empleo, etcétera.</li>
    </ul>
<p>Aumenta la competitividad en tu empresa, contratando personal certificado por medio de CONOCER, la entidad del Gobierno Federal, que promueve el Sistema Nacional de Competencias, y valida los conocimientos de las personas. Por medio de CONOCER,  facilitar&aacute;s tus procesos de reclutamiento.</p>
	        
	       </div>
	     </div>
       
      </div>

    </div>
	</jsp:body>
</t:publicTemplate>
