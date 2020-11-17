<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="url" value="${pageContext.request.requestURI}" />

<t:publicTemplate>
	<jsp:attribute name="titulo">La evaluaci&oacute;n de candidatos</jsp:attribute>
    <jsp:attribute name="palabraDescripcion">Empresas</jsp:attribute>  
    <jsp:attribute name="tituloSitio">Portal del Empleo</jsp:attribute>
 	<jsp:attribute name="urlRedSocial"><c:url value="${url}"/></jsp:attribute>
	<jsp:attribute name="descripcionRedSocial">
		Dentro del proceso de reclutamiento y selecci&oacute;n de personal,   la evaluaci&oacute;n de candidatos es una fase fundamental en la que el   empleador puede comprobar que las habilidades y aptitudes manifestadas   por el postulante sean las esperadas y puedan satisfacer las necesidades   de la empresa.
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
          <li class="active">La evaluación de candidatos</li>
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
			<h2 class="titulosh2">La evaluación de candidatos</h2>
		
	        <p>Dentro del proceso de reclutamiento y <a href="<c:url value="/jsp/empleo/empresas/seleccionDePersonal.jsp"/>">selecci&oacute;n de personal</a>,   la evaluaci&oacute;n de candidatos es una fase fundamental en la que el   empleador puede comprobar que las habilidades y aptitudes manifestadas   por el postulante sean las esperadas y puedan satisfacer las necesidades   de la empresa.</p>
<p>La <a href="<c:url value="/jsp/empleo/empresas/preparacionEntrevista.jsp"/>">entrevista</a> suele ser la t&eacute;cnica m&aacute;s com&uacute;n para conocer a los postulantes al puesto   ofertado. Sin embargo, tambi&eacute;n existen diversos tipos de pruebas que   arrojan resultados confiables sobre las aptitudes de la persona y que,   combinadas, constituyen un excelente indicador en el momento de realizar   contrataciones.</p>
<p><strong>Herramientas de selecci&oacute;n</strong></p>
<p>Despu&eacute;s de haber definido las funciones a realizar y el perfil del empleado requerido, es necesario realizar tanto entrevistas como pruebas de evaluaci&oacute;n que permitan descubrir el potencial de los candidatos y determinar su posible impacto en la productividad de la empresa.</p>
<p>Una entrevista permite al empleador conocer aptitudes, habilidades, gustos e intereses del aspirante, as&iacute; como corroborar y confrontar la informaci&oacute;n que suscit&oacute; el inter&eacute;s por parte de la empresa.</p>
<p>El di&aacute;logo que se desarrolla durante la entrevista suele estar cargado de tensi&oacute;n para quien desea obtener el puesto, por lo que resulta necesario procurar un ambiente c&oacute;modo que propicie el di&aacute;logo y favorezca la obtenci&oacute;n de informaci&oacute;n valiosa acerca del entrevistado.</p>
<p>Muchos empleadores consideran que una contrataci&oacute;n basada exclusivamente en la entrevista puede crear una impresi&oacute;n err&oacute;nea al tomar en cuenta s&oacute;lo los aspectos observables del candidato, como la personalidad o la imagen f&iacute;sica. Por esta raz&oacute;n, se requiere de pruebas objetivas, profundas y espec&iacute;ficas que aseguren una selecci&oacute;n exitosa.</p>
<p>Con el fin de evitar impresiones basadas &uacute;nicamente en la entrevista suelen realizarse pruebas, tests y cuestionarios de evaluaci&oacute;n. Estos instrumentos se emplean para conocer el nivel de desempe&ntilde;o real, los rasgos de la personalidad, entre otros elementos importantes para el empleador y que pueden no resultar evidentes en la entrevista.</p>
<p>Los ex&aacute;menes de conocimientos o habilidades son parte imprescindible del proceso de contrataci&oacute;n debido a que cumplen con los objetivos de valorar los talentos del entrevistado y de dimensionar su desempe&ntilde;o en las funciones que deber&aacute; realizar.</p>
<p>Las pruebas de evaluaci&oacute;n deben ayudar a identificar las habilidades del candidato en relaci&oacute;n con el puesto, de manera independiente a su experiencia laboral. La descripci&oacute;n del cargo ser&aacute; lo que determine los aspectos importantes a evaluar en los candidatos.</p>
<p>Otro tipo de evaluaci&oacute;n est&aacute; dirigida a las pruebas de personalidad, las cuales permiten identificar y conocer las cualidades que hacen que una persona pueda desarrollar mejor una actividad que otra.</p>
<p>Las pruebas de personalidad permiten obtener una visi&oacute;n general de la forma en que se desenvolver&aacute; la persona bajo circunstancias determinadas, considerando aspectos que influyan en la ejecuci&oacute;n del trabajo.</p>
<p>En s&iacute;ntesis, las pruebas de evaluaci&oacute;n de candidatos depender&aacute;n de las responsabilidades que demanda el puesto. Con base en eso, ser&aacute; posible definir las t&eacute;cnicas e instrumentos a utilizar en la evaluaci&oacute;n. Entre las pruebas de evaluaci&oacute;n m&aacute;s comunes se encuentran las de personalidad, inteligencia, aptitudes, rendimiento e intereses, mismas que toman como referencia general la capacidad para afrontar y resolver los problemas m&aacute;s comunes en el desarrollo de las funciones definidas.</p>
<p>Como una forma de corroborar la informaci&oacute;n obtenida acerca del candidato, algunas empresas suelen realizar investigaciones de referencias acerca de empleos anteriores, ex&aacute;menes de salud o estudios socioecon&oacute;micos de la persona que desea ocupar el puesto.</p>
<p>Si bien estas evaluaciones no son infalibles, resultan una forma efectiva de realizar un proceso de selecci&oacute;n confiable, racional y objetivo. En otras palabras, la evaluaci&oacute;n de candidatos permite profesionalizar la contrataci&oacute;n de nuevos integrantes de la organizaci&oacute;n.</p>
       	   
       	   </div>
	      </div>
	     </div>



    </div>
	</jsp:body>
</t:publicTemplate>
