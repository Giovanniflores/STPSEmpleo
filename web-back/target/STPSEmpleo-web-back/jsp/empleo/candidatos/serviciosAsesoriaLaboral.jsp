<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="url" value="${pageContext.request.requestURI}" />


<t:publicTemplate>
	<jsp:attribute name="titulo">Servicios</jsp:attribute>
    <jsp:attribute name="palabraDescripcion">Candidatos</jsp:attribute>  
    <jsp:attribute name="tituloSitio">Portal del Empleo</jsp:attribute>
 	<jsp:attribute name="urlRedSocial"><c:url value="${url}"/></jsp:attribute>
	<jsp:attribute name="descripcionRedSocial">
		Conoce los servicios de la Secretar&iacute;a del Trabajo y Previsi&oacute;n Social.
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
          <li class="active">Servicios</li>
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
			<h2 class="titulosh2">Servicios</h2>
		
	        <p>Conoce los servicios de la Secretar&iacute;a del Trabajo y Previsi&oacute;n Social.</p>
			<p>La STPS te asiste al momento de requerir orientaci&oacute;n sobre diversas situaciones que se te pueden presentar en tu vida laboral.</p>
			<p><strong>1. Asesor&iacute;a</strong></p>
			<p>La Secretar&iacute;a del Trabajo y Previsi&oacute;n Social asesora y orienta a los trabajadores, a sus beneficiarios y a los sindicatos a los que pertenecen sobre los derechos y obligaciones derivados de las normas de trabajo y de prevenci&oacute;n y seguridad social. Adem&aacute;s, informa sobre los tr&aacute;mites, procedimientos y &oacute;rganos competentes ante los cuales acudir para hacerlos valer.</p>
			<p>La asesor&iacute;a y orientaci&oacute;n pueden brindarse de manera personalizada, por v&iacute;a telef&oacute;nica o por correo electr&oacute;nico a quien lo solicite, sin requerirse formalidad alguna.</p>
			<p><strong>Caracter&iacute;sticas:</strong></p>
				<ul class="list-group-contenidos">
    				<li>Para los trabajadores, beneficiarios y sus sindicatos.</li>
    				<li>Se otorga de manera personal, por tel&eacute;fono, correo o cualquier otro medio de comunicaci&oacute;n.</li>
    				<li>Se obtiene respuesta el mismo d&iacute;a que se solicita.</li>
				</ul>
			<p><strong>M&aacute;s informaci&oacute;n</strong></p>
			<p>Acude a Dr. V&eacute;rtiz 211 Col. Doctores, Delegaci&oacute;n Cuauht&eacute;moc C.P. 06720 o al m&oacute;dulo de asesor&iacute;a instalado en la Junta Federal de Conciliaci&oacute;n y Arbitraje ubicado en Calz. Azcapotzalco &ndash; La Villa No. 311. Barrio de Sto. Tom&aacute;s.</p>
			<p>Tambi&eacute;n puedes comunicarte al 51 34 98 00 para cualquier consulta, o enviar un correo electr&oacute;nico a <a href="mailto:ghernandez@stps.gob.mx">ghernandez@stps.gob.mx</a>. Del interior de la Rep&uacute;blica, llama sin costo al 01 800 7 17 29 42 o env&iacute;a un correo electr&oacute;nico a <a href="mailto:carlosjoaquin.magana@stps.gob.mx">carlosjoaquin.magana@stps.gob.mx</a>.</p>
			<p><strong>&iexcl;Nuestros servicios son gratuitos!</strong></p>
       	   </div>
	      </div>
	     </div>



    </div>
	</jsp:body>
</t:publicTemplate>
