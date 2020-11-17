<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="url" value="${pageContext.request.requestURI}" />

<t:publicTemplate>
	<jsp:attribute name="titulo">Capacitaci&oacute;n en la pr&aacute;ctica laboral</jsp:attribute>
    <jsp:attribute name="palabraDescripcion">Candidatos</jsp:attribute>  
    <jsp:attribute name="tituloSitio">Portal del Empleo</jsp:attribute>
 	<jsp:attribute name="urlRedSocial"><c:url value="${url}"/></jsp:attribute>
	<jsp:attribute name="descripcionRedSocial">
		Esta modalidad apoya a los j&oacute;venes de entre 16 y 29 a&ntilde;os para que, con la asesor&iacute;a personalizada de un instructor, generen su primera experiencia laboral mediante su ocupaci&oacute;n productiva en las empresas.
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
          <li><a href="<c:url value="/jsp/empleo/candidatos/becasCapacitacionSne.jsp"/>">Becas a la capacitación del SNE</a></li>
          <li class="active">Capacitación en la práctica laboral</li>
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
			<h2 class="titulosh2">Capacitación en la práctica laboral</h2>
		
	        <p>Esta modalidad apoya a los j&oacute;venes de entre 16 y 29 a&ntilde;os para que, con la asesor&iacute;a personalizada de un instructor, generen su primera experiencia laboral mediante su ocupaci&oacute;n productiva en las empresas.</p>
<p><strong>Apoyos</strong></p>
<p>Al ser aceptado en la capacitaci&oacute;n, tendr&aacute;s derecho a los siguientes beneficios:</p>
<ul class="list-group-contenidos">
    <li>Apoyo econ&oacute;mico de uno a                 tres salarios m&iacute;nimos mensuales.</li>
    <li>Pago de materiales e               instructores para la capacitaci&oacute;n.</li>
    <li>Apoyo para el transporte.</li>
    <li>Seguro contra accidentes.</li>
    <li>Constancia que acredita la pr&aacute;ctica adquirida.</li>
    <li>Posibilidad de contrataci&oacute;n.</li>
</ul>
<p><strong>Requisitos</strong></p>
<p>Para poder participar en un curso de capacitaci&oacute;n en esta modalidad, deber&aacute;s cubrir los siguientes requisitos:</p>
<ul class="list-group-contenidos">
    <li>Estar desempleado.</li>
    <li>Ser mayor de 16 a&ntilde;os.</li>
    <li>Cubrir el perfil requerido por la empresa.</li>
    <li>Solicitar tu incorporaci&oacute;n de manera personal en las oficinas del <a href="<c:url value="/jsp/empleo/servicioNacionalDeEmpleo/ubicacion.jsp"/>">Servicio                   Nacional del Empleo</a> en tu localidad.</li>
</ul>
<p>Conoce algunos de los cursos que se han impartido en esta modalidad:</p>
<ol>
    <li>Auxiliar administrativo</li>
    <li>Atenci&oacute;n a clientes</li>
    <li>Empleado de piso</li>
    <li>Empleado de mostrador</li>
    <li>Cultora de belleza</li>
    <li>Auxiliar contable</li>
    <li>Asistente jur&iacute;dico</li>
    <li>Asistente educativo</li>
    <li>Ayudante general</li>
    <li>Asistente dental</li>
</ol>
<p>Para mayor informaci&oacute;n sobre los cursos disponibles,  acude a las oficinas del <a href="<c:url value="/jsp/empleo/servicioNacionalDeEmpleo/ubicacion.jsp"/>">Servicio Nacional del Empleo</a> en tu localidad.</p>
       	   </div>
	      </div>
	     </div>



    </div>
	</jsp:body>
</t:publicTemplate>
