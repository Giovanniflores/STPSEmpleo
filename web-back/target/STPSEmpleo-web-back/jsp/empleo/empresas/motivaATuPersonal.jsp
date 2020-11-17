<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="url" value="${pageContext.request.requestURI}" />

<t:publicTemplate>
	<jsp:attribute name="titulo">Motiva a tu personal</jsp:attribute>
    <jsp:attribute name="palabraDescripcion">Empresas</jsp:attribute>  
    <jsp:attribute name="tituloSitio">Portal del Empleo</jsp:attribute>
 	<jsp:attribute name="urlRedSocial"><c:url value="${url}"/></jsp:attribute>
	<jsp:attribute name="descripcionRedSocial">
		Una de las razones por las cuales, miles de empleados abandonan su trabajo es porque no se sienten a gusto en la empresa o no reciben los suficientes incentivos para realizar mejor su trabajo y dar un mayor esfuerzo. Motivar a tus empleados ayudar&iacute;a a mejorar su desempe&ntilde;o y a aumentar la eficiencia de la empresa.
	</jsp:attribute>
	<jsp:body>
	
	<!-- div ruta_navegacion -->
    <div class="row">
      <div class="col-sm-12">
        <ol class="breadcrumb">
          <li>Ruta de navegación:</li>
          <li><a href="<c:url value="/inicio.do"/>">Inicio</a></li>
          <li><a href="<c:url value="/jsp/empleo/empresas/empresas.jsp"/>">Empresas</a></li>
          <li><a href="<c:url value="/jsp/empleo/empresas/mejoraDesempenoEmpresa.jsp"/>">Mejora el desempeño de tu empresa</a></li>
          <li class="active">Motiva a tu personal</li>
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
			<h2 class="titulosh2">Motiva a tu personal</h2>
		
	        <p>Una de las razones por las cuales, miles de empleados abandonan su trabajo es porque no se sienten a gusto en la empresa o no reciben los suficientes incentivos para realizar mejor su trabajo y dar un mayor esfuerzo. Motivar a tus empleados ayudar&iacute;a a mejorar su desempe&ntilde;o y a aumentar la eficiencia de la empresa.</p>
<p>En la medida en la que los empleados se sienten motivados, trabajan con mayor gusto y su rendimiento crece. Aqu&iacute; te presentamos algunos consejos para mantener motivado a tu personal:</p>
<ul class="list-group-contenidos">
    <li><strong>Clima laboral:</strong><br />
    Es fundamental que desarrolles un ambiente propicio y de respeto para que el personal pueda desempe&ntilde;arse de la mejor manera y, al mismo tiempo, lograr una conexi&oacute;n con sus compa&ntilde;eros de trabajo.</li>
    <li><strong>Escucha a tu personal:</strong><br />
    Es importante que la empresa se muestre interesada en las inquietudes y necesidades de los trabajadores. Tambi&eacute;n, toma en cuenta sus opiniones o propuestas en cuando a la mejora de la empresa.</li>
    <li><strong>Incentivos:</strong><br />
    Puedes motivar a tu personal a trav&eacute;s de gratificaciones morales, motiv&aacute;ndolos con frases como &ldquo;en la empresa estamos orgullosos de que trabajes con nosotros&rdquo; o con alg&uacute;n reconocimiento. Tambi&eacute;n, podr&iacute;as facilitarles alg&uacute;n permiso o que trabajen menos horas a aqu&eacute;llos trabajadores que siempre llegan temprano o que cumplen en tiempo y forma sus tareas.</li>
    <li><strong>Facilita el aprendizaje:</strong><br />
    Si mantienes capacitado a tu personal, adem&aacute;s que resultar&aacute; ben&eacute;fico para mejorar su desarrollo dentro de la empresa, tambi&eacute;n los motivar&aacute;s por su desarrollo personal.</li>
    <li><strong>Actividades extras:</strong><br />
    &nbsp;Puedes organizar alg&uacute;n torneo de futbol, de danza, ajedrez, yoga, etc&eacute;tera, para fomentar la sociabilidad y convivencia entre los empleados.</li>
    <li><strong>Home office:</strong><br />
    Cuando exista mayor carga de trabajo que requiera horas extra, puedes compensarlo brindando flexibilidad en los horarios (salir m&aacute;s temprano o llegar un poco m&aacute;s tarde) o recurrir al trabajo desde casa (home office).</li>
</ul>
<p>Construye una marca para la empresa: Destaca los buenos valores y la reputaci&oacute;n que tenga la compa&ntilde;&iacute;a, as&iacute; como sus logros; esto motivar&aacute; al empleado sabiendo que trabaja en una empresa de prestigio y renombre.</p>
<p>Si bien, existen otras maneras de mantener motivado a tu personal para que su desempe&ntilde;o sea mayor y su estancia en el trabajo sea placentera; en la medida en la que tus trabajadores se sientan c&oacute;modos y con la satisfacci&oacute;n de hacer su trabajo por gusto y no por necesidad, la productividad de tu empresa, sin duda, crecer&aacute;.</p>
       	   
       	   </div>
	      </div>
	     </div>



    </div>
	</jsp:body>
</t:publicTemplate>
