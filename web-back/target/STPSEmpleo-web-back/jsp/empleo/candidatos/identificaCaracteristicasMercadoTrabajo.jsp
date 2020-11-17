<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="url" value="${pageContext.request.requestURI}" />

<t:publicTemplate>
	<jsp:attribute name="titulo">Caracter&iacute;sticas del mercado de trabajo</jsp:attribute>
    <jsp:attribute name="palabraDescripcion">Candidatos</jsp:attribute>  
    <jsp:attribute name="tituloSitio">Portal del Empleo</jsp:attribute>
 	<jsp:attribute name="urlRedSocial"><c:url value="${url}"/></jsp:attribute>
	<jsp:attribute name="descripcionRedSocial">
		 La globalizaci&oacute;n de la econom&iacute;a y el desarrollo tecnol&oacute;gico han modificado las caracter&iacute;sticas del mercado de trabajo en la actualidad, haci&eacute;ndolo m&aacute;s din&aacute;mico y cambiante debido al flujo continuo de informaci&oacute;n, mercanc&iacute;as y personas a nivel mundial.
	</jsp:attribute>
	<jsp:body>
	
	<!-- div ruta_navegacion -->
    <div class="row">
      <div class="col-sm-12">
        <ol class="breadcrumb">
          <li>Ruta de navegación:</li>
          <li><a href="<c:url value="/inicio.do"/>">Inicio</a></li>
          <li><a href="<c:url value="/jsp/empleo/candidatos/candidatos.jsp"/>">Candidatos</a></li>
          <li><a href="<c:url value="/jsp/empleo/candidatos/habilidadesBusquedaEmpleo.jsp"/>">Habilidades para la Búsqueda de Empleo (HPBE) </a></li>
          <li><a href="<c:url value="/jsp/empleo/candidatos/conoce.jsp"/>">Conoce</a></li>
          <li class="active">Identifica las características del mercado de trabajo</li>
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
			<h2 class="titulosh2">Identifica las características del mercado de trabajo</h2>
		
	        <p>La globalizaci&oacute;n de la econom&iacute;a y el desarrollo tecnol&oacute;gico han modificado las caracter&iacute;sticas del mercado de trabajo en la actualidad, haci&eacute;ndolo m&aacute;s din&aacute;mico y cambiante debido al flujo continuo de informaci&oacute;n, mercanc&iacute;as y personas a nivel mundial.</p>
			<p>Estos dos acontecimientos han hecho cada vez m&aacute;s difusas las barreras territoriales, ocupacionales y profesionales, incrementando la necesidad de flexibilidad y aprendizaje continuo, as&iacute; como la disponibilidad a la movilidad territorial y/o al interior de la empresa en funci&oacute;n de las necesidades de &eacute;sta.</p>
			<p>En este marco, la b&uacute;squeda de empleo es el proceso mediante el cual las personas se insertan en el mercado de trabajo. Toda b&uacute;squeda suele estar determinada por la consecuci&oacute;n de un objetivo profesional. Como regla general, el objetivo de las personas es conseguir un trabajo en el que reciban una remuneraci&oacute;n que sea acorde con el puesto a desempe&ntilde;ar y suficiente para cubrir sus necesidades personales, en el que realicen una funci&oacute;n seg&uacute;n los estudios realizados y que le aporte nuevos conocimientos.</p>
			<p>Para lograr tu objetivo es conveniente que sepas cu&aacute;les son las caracter&iacute;sticas del mercado de trabajo en tu localidad o entidad. Las respuestas a las siguientes preguntas te pueden ayudar a identificar las principales caracter&iacute;sticas del mercado de trabajo: &iquest;D&oacute;nde trabaja la gente de esta localidad? &iquest;En qu&eacute; actividades? &iquest;D&oacute;nde trabajan las mujeres? &iquest;D&oacute;nde trabajan los hombres? &iquest;D&oacute;nde trabajan las y los j&oacute;venes? &iquest;D&oacute;nde trabajan las personas de mayor edad? &iquest;D&oacute;nde trabaja la gente con alguna discapacidad? &iquest;Cu&aacute;les son los requisitos que demandan hoy las empresas a los candidatos a las vacantes que ofrecen? &iquest;Qu&eacute; salarios se pagan en las principales ocupaciones de la localidad o entidad? &iquest;Por qu&eacute; piensas que es as&iacute;? &iquest;Cu&aacute;les son las v&iacute;as de inserci&oacute;n (mecanismos de b&uacute;squeda de empleo) que m&aacute;s se utilizan en tu localidad (peri&oacute;dico, bolsas de empleo, portales de empleo, organismos empresariales, instituciones de educaci&oacute;n, etc.)? &iquest;Hay vacantes en las que se demanden las habilidades y conocimientos que posees? &iquest;Qu&eacute; tipos de empresas ofrecen las vacantes que te interesan (comercio, servicios, industria, agropecuarias, etc., sector p&uacute;blico).</p>
			<!--  p>Conoce algunos indicadores del mercado laboral en el siguiente link: <a href="http://www.observatoriolaboral.gob.mx/swb/es/ola/ocupacion_por_sectores_economicos" target="_blank">http://www.observatoriolaboral.gob.mx/swb/es/ola/ocupacion_por_sectores_economicos</a></p-->
			<p>Conoce algunos indicadores del mercado laboral en el siguiente link: <a href="http://www.observatoriolaboral.gob.mx/static/estudios-publicaciones/Ocupacion_sectores.html" target="_blank">http://www.observatoriolaboral.gob.mx/swb/es/ola/ocupacion_por_sectores_economicos</a></p>

       	   </div>
	      </div>
	     </div>



    </div>
	</jsp:body>
</t:publicTemplate>
