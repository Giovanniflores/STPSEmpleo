<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="url" value="${pageContext.request.requestURI}" />

<t:publicTemplate>
	<jsp:attribute name="titulo">Becas Manpower TDC</jsp:attribute>
    <jsp:attribute name="palabraDescripcion">Candidatos</jsp:attribute>  
    <jsp:attribute name="tituloSitio">Portal del Empleo</jsp:attribute>
 	<jsp:attribute name="urlRedSocial"><c:url value="${url}"/></jsp:attribute>
	<jsp:attribute name="descripcionRedSocial">
		Manpower Training and Development Center otorga becas trimestrales para m&aacute;s de 1,800 cursos en l&iacute;nea en 15 diferentes idiomas, 200 de ellos en espa&ntilde;ol y enfocados en &aacute;reas de Negocios, Inform&aacute;tica y Tecnolog&iacute;as de la Informaci&oacute;n. Algunos de estos cursos son: Office 2007, Six Sigma, Ventas, Atenci&oacute;n al cliente, Mercadotecnia, SAP y Contabilidad.
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
          <li class="active">Becas Manpower TDC</li>
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
			<h2 class="titulosh2">Becas Manpower TDC</h2>
		
	        <p>Manpower Training and Development Center otorga becas trimestrales para m&aacute;s de 1,800 cursos en l&iacute;nea en 15 diferentes idiomas, 200 de ellos en espa&ntilde;ol y enfocados en &aacute;reas de Negocios, Inform&aacute;tica y Tecnolog&iacute;as de la Informaci&oacute;n. Algunos de estos cursos son: Office 2007, Six Sigma, Ventas, Atenci&oacute;n al cliente, Mercadotecnia, SAP y Contabilidad.</p>
			<p>Para acceder a los cursos, por favor sigue estos sencillos pasos:</p>
				<ol>
    			 <li>Descarga el <a href="${context}/download/candidatos/Convenio_Man_power_TDC.doc">Convenio de Confidencialidad</a>.</li>
    			 <li>Ll&eacute;nalo y env&iacute;alo al correo electr&oacute;nico: <a href="mailto:skillware@manpower.com.mx">skillware@manpower.com.mx</a>.</li>
    			 <li>Recibir&aacute;s un e-mail con tu nombre de usuario y contrase&ntilde;a para acceder.</li>
				</ol>
       	   </div>
	      </div>
	     </div>



    </div>
	</jsp:body>
</t:publicTemplate>
