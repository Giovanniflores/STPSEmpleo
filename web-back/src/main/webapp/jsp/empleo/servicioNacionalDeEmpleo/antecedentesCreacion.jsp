<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="url" value="${pageContext.request.requestURI}" />

<t:publicTemplate>
	<jsp:attribute name="titulo">Antecedentes y creaci&oacute;n del SNE</jsp:attribute>
    <jsp:attribute name="palabraDescripcion">Servicio Nacional de Empleo</jsp:attribute>  
    <jsp:attribute name="tituloSitio">Portal del Empleo</jsp:attribute>
 	<jsp:attribute name="urlRedSocial"><c:url value="${url}"/></jsp:attribute>
	<jsp:attribute name="descripcionRedSocial">
		El SNE tiene como propósitos brindar a la población la información, vinculación y orientación ocupacional necesaria, así como apoyos económicos y de capacitación. Además, se encarga de instrumentar estrategias de movilidad laboral interna y externa entre la población económicamente activa.
	</jsp:attribute>
	<jsp:body>
	
	<!-- div ruta_navegacion -->
    <div class="row">
      <div class="col-sm-12">
        <ol class="breadcrumb">
          <li>Ruta de navegación:</li>
          <li><a href="<c:url value="/inicio.do"/>">Inicio</a></li>
          <li><a href="<c:url value="/jsp/empleo/servicioNacionalDeEmpleo/servicioNacionalDeEmpleo.jsp"/>">Servicio Nacional de Empleo</a></li>
          <li><a href="<c:url value="/jsp/empleo/servicioNacionalDeEmpleo/acercaSne.jsp"/>">Acerca del SNE </a></li>
          <li class="active">Antecedentes y creación</li>
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
			<h2 class="titulosh2">Antecedentes y creación</h2>
		
	        <p>El SNE tiene como propósitos brindar a la población la información, vinculación y orientación ocupacional necesaria, así como apoyos económicos y de capacitación. Además, se encarga de instrumentar estrategias de movilidad laboral interna y externa entre la población económicamente activa.</p>

			<p>Los objetivos de su creaci&oacute;n, de acuerdo con el Art&iacute;culo 537 de la <a href="${context}/download/candidatos/lft_125.pdf" target="_blank">Ley Federal del Trabajo</a>&nbsp;son:</p>
	        <ol>
	          <li>Promover la generación de empleos.</li>
	          <li>Impulsar y supervisar la colocación de los trabajadores.</li>
	          <li>Organizar, promover y supervisar la capacitación y adiestramiento de los trabajadores.</li>
	          <li>Registrar las constancias de habilidades laborales.</li>
	        </ol>
	        <p>Los antecedentes del Servicio Nacional de Empleo derivan de las reformas a las fracciones XIII y XXXI del Apartado A del Artículo 123 Constitucional, a través de las cuales se consignó como deber de los patrones el proporcionar a sus trabajadores capacitación y adiestramiento en el trabajo.</p>
	        <p>Otro de los cambios que propició su creación fue la federalización de la aplicación de las normas laborales en varias ramas industriales, incluyendo la capacitación, adiestramiento, seguridad e higiene en el trabajo.</p>
	        <p>Como consecuencia de dicha reforma constitucional, la <a href="${context}/download/candidatos/lft_125.pdf" target="_blank"> Ley Federal del Trabajo</a> tuvo diversas modificaciones relacionadas con el derecho de los trabajadores a recibir capacitación y adiestramiento (Artículos 3°, 25 Y 132), así como a cambios en los artículos 523 y 538.</p>
	        <p>Eso motiv&oacute; la creaci&oacute;n del Servicio Nacional de Empleo, Capacitaci&oacute;n y Adiestramiento (SNECA), en sustituci&oacute;n al Servicio P&uacute;blico de Empleo, quedando a cargo de un &oacute;rgano desconcentrado dependiente de la <a href="http://www.stps.gob.mx" target="_blank">Secretar&iacute;a de Trabajo y Previsi&oacute;n Social</a> (STPS) denominado Unidad Coordinadora del Empleo, Capacitaci&oacute;n y Adiestramiento.</p>
<p>Las actividades encomendadas a dicho &oacute;rgano se describieron en el Art&iacute;culo 539 de la <a href="${context}/download/candidatos/lft_125.pdf" target="_blank"> Ley Federal del Trabajo</a>, y se relacionaron con diversos aspectos de la promoci&oacute;n de empleos: colocaci&oacute;n de trabajadores, capacitaci&oacute;n y adiestramiento y registro de constancias de habilidades laborales.</p>
<p>De acuerdo con el T&iacute;tulo Once de la <a href="${context}/download/candidatos/lft_125.pdf" target="_blank">Ley Federal del Trabajo</a>, correspondiente a Autoridades del Trabajo y Servicios Sociales, se prev&eacute; al Servicio Nacional de Empleo, Capacitaci&oacute;n y Adiestramiento como autoridad competente para aplicar las normas de trabajo.</p>
<p>Posteriormente, con la reforma al art&iacute;culo 538 de la <a href="${context}/download/candidatos/lft_125.pdf" target="_blank">Ley Federal del Trabajo</a>, el SNECA qued&oacute; a cargo de la <a href="http://www.stps.gob.mx" target="_blank">Secretar&iacute;a del Trabajo y Previsi&oacute;n Social</a> (STPS) por conducto de las unidades administrativas a las que competan las funciones correspondientes en los t&eacute;rminos de su reglamento interior.</p>
<p>El art&iacute;culo 14, fracci&oacute;n I, del reglamento interior de dicha dependencia establece que corresponde a la Coordinaci&oacute;n General de Empleo operar el SNECA con la participaci&oacute;n de la Direcci&oacute;n General de Capacitaci&oacute;n y, por su parte, el art&iacute;culo 23, fracci&oacute;n I, dispone que a dicha unidad administrativa le corresponde participar en el SNECA &uacute;nicamente en la parte que se refiere a la capacitaci&oacute;n y adiestramiento de trabajadores en activo.</p>
       	   </div>
	      </div>
	     </div>



    </div>
	</jsp:body>
</t:publicTemplate>
