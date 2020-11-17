<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="url" value="${pageContext.request.requestURI}" />

<t:publicTemplate>
	<jsp:attribute name="titulo">Capacitaci&oacute;n a distancia para trabajadores</jsp:attribute>
    <jsp:attribute name="palabraDescripcion">Candidatos</jsp:attribute>  
    <jsp:attribute name="tituloSitio">Portal del Empleo</jsp:attribute>
 	<jsp:attribute name="urlRedSocial"><c:url value="${url}"/></jsp:attribute>
	<jsp:attribute name="descripcionRedSocial">
		 La Secretar&iacute;a del Trabajo y Previsi&oacute;n Social, te da la m&aacute;s cordial bienvenida al Programa de Capacitaci&oacute;n a Distancia para Trabajadores (PROCADIST), cuyo objetivo es fortalecer las competencias laborales de los trabajadores en activo, a trav&eacute;s de un esquema de capacitaci&oacute;n de cursos que favorecen el aprendizaje en entornos virtuales.
	</jsp:attribute>
	<jsp:body>
	
	<!-- div ruta_navegacion -->
    <div class="row">
      <div class="col-sm-12">
        <ol class="breadcrumb">
          <li>Ruta de navegación:</li>
          <li><a href="<c:url value="/inicio.do"/>">Inicio</a></li>
          <li><a href="<c:url value="/jsp/empleo/candidatos/candidatos.jsp"/>">Candidatos</a></li>
          <li><a href="<c:url value="/jsp/empleo/candidatos/capacitacionBecas.jsp"/>">Capacitación y becas</a></li>
          <li class="active">Capacitación a distancia para trabajadores</li>
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
			<h2 class="titulosh2">Capacitación a distancia para trabajadores</h2>
			<p>El <strong>Programa de Capacitación a Distancia para Trabajadores (PROCADIST)</strong> es una estrategia de la Secretaría del Trabajo y Previsión Social para ofrecer, mediante una plataforma educativa a distancia, cursos para el adiestramiento, la capacitación y la alineación a estándares de competencia que permitan a los trabajadores el desarrollo o perfeccionamiento de sus habilidades y con ello, mejoren o incrementen su productividad laboral.</p>
			<p>El <strong>PROCADIST</strong> centra sus esfuerzos en atender a <strong>trabajadores y trabajadoras en activo</strong>, principalmente de micro y pequeñas empresas. No obstante, también presta sus servicios a los <strong>responsables de recursos humanos</strong> en este tipo de empresas, lo anterior para el cumplimiento de las obligaciones legales de las mismas.</p>
			<p>El programa también ofrece sus servicios al <strong>público en general</strong> (sean trabajadores en activo o no) y a <strong>estudiantes de educación superior y recién egresados de ese nivel educativo.</strong></p>
			<p>El <strong>PROCADIST</strong> clasifica sus cursos con base en las categorías que se definen en el Programa Sectorial de Trabajo y Previsión Social 2013-2018 en materia de capacitación:</p>
			<ol style="list-style-type: lower-latin;">
				<li>Alineación a estándares de competencia</li>
				<li>Nuevas tecnologías</li>
				<li>Productividad laboral</li>
				<li>Seguridad y salud en el trabajo</li>
				<li>Formación educativa inicial</li>
				<li>Formación empresarial</li>
			</ol>
			<p>Los cursos que actualmente se tienen disponibles son:</p>
			<ol>
				<li>EC0107 Manejo de procesador de textos digitales*</li>
				<li>EC0038 Atención a comensales</li>
				<li>EC0366 Desarrollo de cursos de formación en línea*</li>
				<li>EC0581 Integración y funcionamiento de las comisiones mixtas de capacitación, adiestramiento y productividad laboral*</li>
				<li>Formación de tutores en línea</li>
				<li>Diseño de materiales y herramientas de capacitación</li>
				<li>Trabajo en equipo</li>
				<li>NOM-001-STPS-2008 Edificios, locales, instalaciones y áreas en los centros de trabajo</li>
				<li>NOM-002-STPS-2010 Condiciones de seguridad. Prevención y protección contra incendios en los centros de trabajo</li>
				<li>NOM-006-STPS-2010 Manejo y almacenamiento de materiales.</li>
				<li>NOM-012-STPS-2012 Condiciones de seguridad y salud en el trabajo donde se manejen fuentes de radiación ionizante.</li>
				<li>NOM-017-STPS-2008 Equipo de protección personal</li>
				<li>NOM-019-STPS-2014 Comisiones de seguridad e higiene</li>
				<li>NOM-025-STPS-2008 Condiciones de iluminación en los centros de trabajo</li>
				<li>NOM-026-STPS-2008 Colores y señales de seguridad e higiene</li>
				<li>NOM-028-STPS-2012 Sistema para la administración del trabajo</li>
				<li>NOM-029-STPS-2011 Mantenimiento de instalaciones eléctricas en los centros de trabajo</li>
				<li>NOM-030-STPS-2009 Servicios preventivos de seguridad y salud en el trabajo</li>
				<li>Prevención de adicciones en el ámbito laboral**</li>
				<li>Administración de la capacitación</li>
				<li>Innovando en mi Trabajo</li>
				<li>Comunicación Efectiva en el Trabajo</li>
				<li>Desarrollo de Habilidades Directivas</li>
				<li>NOM-009-STPS-2011 Condiciones de seguridad para realizar trabajos en altura</li>
			</ol>
			<p>Para tomar alguno de los cursos ingresa a <a target="_blank" href="https://www.procadist.gob.mx"><strong>PROCADIST</strong></a>.</p>
			<p>La información ha sido proporcionada por la Subdirección de Capacitación a Distancia de la STPS. Para establecer contacto favor escribir al correo procadist@stps.gob.mx o utilizar la opción <a target="_blank" href="https://www.procadist.gob.mx/portal/Contact"<em>Envíanos un mensaje</em></a>.</p>

       	   </div>
	      </div>
	     </div>



    </div>
	</jsp:body>
</t:publicTemplate>
