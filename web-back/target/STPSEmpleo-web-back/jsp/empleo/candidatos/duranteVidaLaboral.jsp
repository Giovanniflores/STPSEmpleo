<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="url" value="${pageContext.request.requestURI}" />

<t:publicTemplate>
	<jsp:attribute name="titulo">Durante la vida laboral</jsp:attribute>
    <jsp:attribute name="palabraDescripcion">Candidatos</jsp:attribute>  
    <jsp:attribute name="tituloSitio">Portal del Empleo</jsp:attribute>
 	<jsp:attribute name="urlRedSocial"><c:url value="${url}"/></jsp:attribute>
	<jsp:attribute name="descripcionRedSocial">
		A medida que la vida laboral avanza, el trabajador experimenta diversas situaciones ante las que no sabe a ciencia cierta c&oacute;mo reaccionar o con qu&eacute; autoridades acudir para evitar abusos a sus derechos.
	</jsp:attribute>
	<jsp:body>
	
	<!-- div ruta_navegacion -->
    <div class="row">
      <div class="col-sm-12">
        <ol class="breadcrumb">
          <li>Ruta de navegación:</li>
          <li><a href="<c:url value="/inicio.do"/>">Inicio</a></li>
          <li><a href="<c:url value="/jsp/empleo/candidatos/candidatos.jsp"/>">Candidatos</a></li>
          <li><a href="<c:url value="/jsp/empleo/candidatos/asesoriaLaboral.jsp"/>">Asesoría laboral</a></li>
          <li><a href="<c:url value="/jsp/empleo/candidatos/preguntasFrecuentes.jsp"/>">Preguntas frecuentes</a></li>
          <li class="active">Durante de la vida laboral</li>
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
			<h2 class="titulosh2">Durante la vida laboral</h2>
		
	        <p>A medida que la vida laboral avanza, el trabajador experimenta diversas situaciones ante las que no sabe a ciencia cierta c&oacute;mo reaccionar o con qu&eacute; autoridades acudir para evitar abusos a sus derechos.</p>
<p>Inf&oacute;rmate sobre las dudas m&aacute;s comunes que surgen a ra&iacute;z de eventos que generalmente no se tienen considerados como los accidentes de trabajo, el pago de las cuotas obligatorias y embarazo, entre otros, y disp&oacute;n de los beneficios que te otorga la ley.</p>
<p><strong><a href="<c:url value="/jsp/empleo/candidatos/aforeDurante.jsp"/>">AFORE</a></strong></p>
<p>Ori&eacute;ntate sobre los beneficios de las AFORES, y evita malos manejos de tu patrimonio.</p>
<p><strong><a href="<c:url value="/jsp/empleo/candidatos/derechosLaboralesDurante.jsp"/>">Derechos laborales</a></strong></p>
<p>Aclara tus dudas acerca de todo lo concerniente a salarios, prestaciones, condiciones laborales, entre otros aspectos que rigen el desenvolvimiento en tu trabajo.</p>
<p><strong><a href="<c:url value="/jsp/empleo/candidatos/imssInfonavitDurante.jsp"/>">IMSS e Infonavit</a></strong></p>
<p>Conoce los derechos y obligaciones adquiridas al pertenecer al Instituto Mexicano de Seguro Social y al Infonavit.</p>
       	   </div>
	      </div>
	     </div>



    </div>
	</jsp:body>
</t:publicTemplate>
