<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="url" value="${pageContext.request.requestURI}" />

<t:publicTemplate>
	<jsp:attribute name="titulo">La selecci&oacute;n de personal</jsp:attribute>
    <jsp:attribute name="palabraDescripcion">Empresas</jsp:attribute>  
    <jsp:attribute name="tituloSitio">Portal del Empleo</jsp:attribute>
 	<jsp:attribute name="urlRedSocial"><c:url value="${url}"/></jsp:attribute>
	<jsp:attribute name="descripcionRedSocial">
		Ante la necesidad de una contrataci&oacute;n, el empleador debe realizar un procedimiento de selecci&oacute;n que le permita recibir la mayor cantidad de postulantes posible para elegir el que mejor se adec&uacute;e a los requerimientos del puesto.
	</jsp:attribute>
	<jsp:body>
	
	<!-- div ruta_navegacion -->
    <div class="row">
      <div class="col-sm-12">
        <ol class="breadcrumb">
          <li>Ruta de navegación:</li>
          <li><a href="<c:url value="/inicio.do"/>">Inicio</a></li>
          <li><a href="<c:url value="/jsp/empleo/empresas/empresas.jsp"/>">Empresas</a></li>
          <li><a href="<c:url value="/jsp/empleo/empresas/mejoraDesempeñoEmpresa.jsp"/>">Mejora el desempeño de tu empresa</a></li>
          <li class="active">La selección de personal</li>
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
			<h2 class="titulosh2">La selección de personal</h2>
		
	        <p>Ante la necesidad de una contrataci&oacute;n, el empleador debe realizar un procedimiento de selecci&oacute;n que le permita recibir la mayor cantidad de postulantes posible para elegir el que mejor se adec&uacute;e a los requerimientos del puesto.</p>
<p>Realizar una elecci&oacute;n correcta depender&aacute; de conocer los pasos m&aacute;s importantes que conforman el proceso de selecci&oacute;n de personal. De lo contrario, una decisi&oacute;n equivocada puede originar p&eacute;rdidas de tiempo y recursos monetarios para la organizaci&oacute;n.</p>
<p>El objetivo del procedimiento consiste en elegir a los candidatos que presenten las cualidades y experiencia profesional m&aacute;s apropiadas para el desempe&ntilde;o del cargo solicitado por la organizaci&oacute;n</p>
<p>Sin importar el tama&ntilde;o de la empresa, tener un m&eacute;todo eficaz de selecci&oacute;n de personal es fundamental para su evoluci&oacute;n, pues contratar a aquellos con m&aacute;s aptitudes ayudar&aacute; a su crecimiento y desarrollo continuo.</p>
<p><strong>Una selecci&oacute;n eficaz</strong></p>
<p>A partir de las pr&aacute;cticas de selecci&oacute;n de personal llevadas a cabo por distintas empresas, es posible identificar una serie de pasos en com&uacute;n que contribuyen al &eacute;xito en la contrataci&oacute;n de nuevo personal.</p>
<ol>
    <li><strong>Definir el cargo</strong><br />
    La definici&oacute;n del cargo est&aacute; muy ligada al <a href="<c:url value="/jsp/empleo/empresas/analisisPuestos.jsp"/>">an&aacute;lisis de puestos</a>, pues con base en los resultados de este proceso se delimitan las funciones a realizar y lo que se espera del solicitante.</li><br />
    <li><strong>Establecer el perfil requerido</strong><br />
    De acuerdo con la descripci&oacute;n del cargo, ser&aacute; necesario conformar el perfil id&oacute;neo para el &oacute;ptimo desempe&ntilde;o de las funciones requeridas por la empresa. El perfil puede incluir especificaciones como formaci&oacute;n acad&eacute;mica, a&ntilde;os de experiencia realizando actividades similares a las que requiere el puesto, entre otras que sean de relevancia para el puesto ofertado.</li><br />
    <li><strong>Preselecci&oacute;n</strong><br />
    Despu&eacute;s de realizar una definici&oacute;n detallada del perfil requerido por la empresa, es posible realizar una elecci&oacute;n preliminar de aquellos interesados que cumplan con las expectativas del puesto. Esta etapa constituye el primer filtro previo a la entrevista y su objetivo es filtrar a los candidatos que se adecuen en mayor medida al perfil establecido.</li><br />
    <li><strong>Entrevista</strong><br />
    Aquellos postulantes que fueron preseleccionados deber&aacute;n ser citados a una entrevista que permita al empleador comprobar lo referido en su hoja de solicitud o curr&iacute;culum, as&iacute; como para conocer aspectos de la personalidad del candidato. La primera <a href="<c:url value="/jsp/empleo/empresas/preparacionEntrevista.jsp"/>">entrevista</a> suele ser r&aacute;pida y superficial con el fin de descartar a aquellos postulantes que no re&uacute;nan las caracter&iacute;sticas requeridas y continuar evaluando a quienes podr&iacute;an cubrir el puesto satisfactoriamente.</li><br />
    <li><strong>Evaluaciones</strong><br />
    En esta etapa es necesario aplicar pruebas de diversos tipos a quienes presenten una mayor adecuaci&oacute;n al perfil establecido. La <a href="<c:url value="/jsp/empleo/empresas/evaluacionCandidatos.jsp"/>">evaluaci&oacute;n de candidatos</a> pueden consistir en actividades directamente relacionadas con el puesto, as&iacute; como pruebas psicom&eacute;tricas, de conocimiento general, entre otras, que evidencien las capacidades y aptitudes de la persona evaluada.</li><br />
    <li><strong>Entrevista de selecci&oacute;n</strong><br />
    Es muy com&uacute;n que las empresas u organizaciones soliciten una segunda entrevista con los candidatos que tuvieron un mejor desempe&ntilde;o en las pruebas de evaluaci&oacute;n. La entrevista suele realizarse con el jefe del &aacute;rea quien determinar&aacute; si esa persona cubre los requisitos o no. A diferencia de la primera entrevista, esta suele ser mucho m&aacute;s profunda y analiza aspectos de desempe&ntilde;o, pretensiones econ&oacute;micas, entre otras dudas que pueda tener el entrevistado.</li><br />
    <li><strong>Descripci&oacute;n precisa de puesto y condiciones</strong><br />
    Una vez elegido el candidato final, ser&aacute; necesario realizar una explicaci&oacute;n detallada de las funciones de su puesto, as&iacute; como discutir las condiciones de trabajo y los t&eacute;rminos de contrataci&oacute;n. En caso de no acordarse la relaci&oacute;n laboral, se recomienda considerar a otro candidato que haya participado en el proceso de selecci&oacute;n.</li><br />
</ol>
<p>Si bien estos pasos podr&iacute;an parecer sencillos, en cada uno intervienen factores fundamentales que identifican a los candidatos m&aacute;s aptos para el puesto. Por ejemplo, mediante el proceso de selecci&oacute;n es posible determinar la disposici&oacute;n de cada candidato para el trabajo en equipo, la capacidad para la resoluci&oacute;n de problemas, el desenvolvimiento en momentos de tensi&oacute;n, as&iacute; como las capacidades y aptitudes espec&iacute;ficas que demanda el perfil profesional solicitado.</p>
<p>Por esas razones, resulta de gran ayuda para el reclutador seguir los pasos descritos en el proceso de selecci&oacute;n y prestar atenci&oacute;n a los aspectos m&aacute;s significativos del candidato para no pasar por alto ning&uacute;n detalle que influya en la decisi&oacute;n final.</p>
<p>Una mala elecci&oacute;n no suele tener repercusiones inmediatas, sino hasta despu&eacute;s de un periodo de observaci&oacute;n que incluso puede tener implicaciones legales en el momento del despido. Esto, sin dejar fuera el costo econ&oacute;mico y social que la decisi&oacute;n err&oacute;nea representa para el empleado y para la organizaci&oacute;n que lo contrata.</p>
       	   
       	   </div>
	      </div>
	     </div>



    </div>
	</jsp:body>
</t:publicTemplate>
