<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="url" value="${pageContext.request.requestURI}" />

<t:publicTemplate>
	<jsp:attribute name="titulo">Al t&eacute;rmino de la vida laboral</jsp:attribute>
    <jsp:attribute name="palabraDescripcion">Candidatos</jsp:attribute>  
    <jsp:attribute name="tituloSitio">Portal del Empleo</jsp:attribute>
 	<jsp:attribute name="urlRedSocial"><c:url value="${url}"/></jsp:attribute>
	<jsp:attribute name="descripcionRedSocial">
		Una vez concluida la vida laboral, llega el momento de disfrutar los beneficios que por ley corresponden a los trabajadores y mismos que deben permitirles una jubilaci&oacute;n digna.
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
          <li class="active">Al término de la vida laboral</li>
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
			<h2 class="titulosh2">Al término de la vida laboral</h2>
		
	        <p>Una vez concluida la vida laboral, llega el momento de disfrutar los beneficios que por ley corresponden a los trabajadores y mismos que deben permitirles una jubilaci&oacute;n digna.</p>
<p>Conoce qu&eacute; acciones debes tomar al momento de jubilarte, de renunciar a tu empleo o de ser despedido y defiende tu esatus como trabajador.</p>
<p><strong><a href="<c:url value="/jsp/empleo/candidatos/aforeTermino.jsp"/>">AFORE</a></strong></p>
<p>Ori&eacute;ntate sobre los beneficios de las AFORES, y evita malos manejos de tu patrimonio.</p>
<p><strong><a href="<c:url value="/jsp/empleo/candidatos/derechosLaboralesTermino.jsp"/>">Derechos laborales</a></strong></p>
<p>Aclara tus dudas acerca de todo lo concerniente a salarios, prestaciones, condiciones laborales, entre otros aspectos que rigen el desenvolvimiento en tu trabajo.</p>
<p><strong><a href="<c:url value="/jsp/empleo/candidatos/imssInfonavitTermino.jsp"/>">IMSS e Infonavit</a></strong></p>
<p>Conoce los derechos y obligaciones adquiridas al pertenecer al Instituto Mexicano de Seguro Social y al Infonavit.</p>
       	   </div>
	      </div>
	     </div>



    </div>
	</jsp:body>
</t:publicTemplate>
