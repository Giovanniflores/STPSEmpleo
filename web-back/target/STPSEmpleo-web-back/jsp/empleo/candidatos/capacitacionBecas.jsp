<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="url" value="${pageContext.request.requestURI}" />


<t:publicTemplate>
	<jsp:attribute name="titulo">Capacitaci&oacute;n y becas</jsp:attribute>
    <jsp:attribute name="palabraDescripcion">Candidatos</jsp:attribute>  
    <jsp:attribute name="tituloSitio">Portal del Empleo</jsp:attribute>
 	<jsp:attribute name="urlRedSocial"><c:url value="${url}"/></jsp:attribute>
	<jsp:attribute name="descripcionRedSocial">
		La secci&oacute;n de capacitaci&oacute;n te ofrece los siguientes servicios: La capacitaci&oacute;n de las personas es un recurso que contribuye al crecimiento de la sociedad y a generar condiciones de bienestar para todos. Es un instrumento fundamental para lograr el cambio tecnol&oacute;gico y organizacional que imponen los nuevos procesos de generaci&oacute;n de bienes y servicios.
	</jsp:attribute>
	<jsp:body>
	
	<!-- div ruta_navegacion -->
    <div class="row">
      <div class="col-sm-12">
        <ol class="breadcrumb">
          <li>Ruta de navegación:</li>
          <li><a href="<c:url value="/inicio.do"/>">Inicio</a></li>
          <li><a href="<c:url value="/jsp/empleo/candidatos/candidatos.jsp"/>">Candidatos</a></li>
          <li class="active">Capacitación y becas</li>
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
            <img alt="" src="${context}/css/images/contenido/imagen_capacitacion.png" class="img-responsive">
          </div>
          <div class="col-md-6">
            <h2>Capacitación y becas</h2>
            <p>¡Te damos la más cordial bienvenida a esta sección del Portal del Empleo!</p>
          </div>
        </div>
        </div>

        <div class="clearfix"></div>
        
        <div class="panel">
          <div class="panel-body">

	        <p><strong>La secci&oacute;n de capacitaci&oacute;n te ofrece los siguientes servicios:</strong></p>
			<p>La capacitaci&oacute;n de las personas es un recurso que contribuye al crecimiento de la sociedad y a generar condiciones de bienestar para todos. Es un instrumento fundamental para lograr el cambio tecnol&oacute;gico y organizacional que imponen los nuevos procesos de generaci&oacute;n de bienes y servicios.</p>
			<p>En el sitio de <a href="http://www.capacinet.gob.mx" target="_blank">CapaciNET</a>, se presenta un inventario de cursos gratuitos cuyo dise&ntilde;o, respaldado por instituciones de prestigio en el &aacute;rea de la capacitaci&oacute;n interactiva, aspira a generar y mejorar las aptitudes, habilidades, conocimientos y actitudes de las personas que aspiran a un empleo o desean mejorar su desempe&ntilde;o productivo.</p> 
			<p>La gama de cursos comprende la oferta de capacitaci&oacute;n a distancia a cargo de la Secretar&iacute;a del Trabajo y Previsi&oacute;n Social a través del Programa de Capacitaci&oacute;n a Distancia para Trabajadores (<a target="_blank" href="https://www.procadist.gob.mx/portal/">PROCADIST</a>), la cual, en l&iacute;nea, suministra servicios de asesor&iacute;a, seguimiento y evaluaci&oacute;n del aprendizaje de los participantes.</p> 
			<p>La oferta de cursos tambi&eacute;n abarca la de otras instituciones p&uacute;blicas y privadas que, en una alianza con el Sistema Nacional e-M&eacute;xico de la Secretar&iacute;a de Comunicaciones y Transportes, han accedido a publicar sus contenidos de la red de cursos virtuales.</p>
			<p>Las personas que deseen encontrar opciones de capacitaci&oacute;n estrechamente vinculadas a la pr&aacute;ctica laboral, encontrar&aacute;n alternativas en las 5 modalidades del Programa de Becas a la Capacitaci&oacute;n para el Trabajo a cargo del Servicio Nacional de Empleo.</p>
			<p><strong>&iexcl;Nuestros servicios son gratuitos!</strong></p>
			<p><strong>&iexcl;Estamos para servirte!</strong></p>

	       </div>
	     </div>
       
      </div>



    </div>
	</jsp:body>
</t:publicTemplate>
