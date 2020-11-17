<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="url" value="${pageContext.request.requestURI}" />


<t:publicTemplate>
	<jsp:attribute name="titulo">SNE: Portal del Empleo</jsp:attribute>
    <jsp:attribute name="palabraDescripcion">Servicio Nacional de Empleo</jsp:attribute>  
    <jsp:attribute name="tituloSitio">Portal del Empleo</jsp:attribute>
 	<jsp:attribute name="urlRedSocial"><c:url value="${url}"/></jsp:attribute>
	<jsp:attribute name="descripcionRedSocial">
		Si deseas obtener en un solo sitio de internet, informaci&oacute;n, orientaci&oacute;n, capacitaci&oacute;n y asesor&iacute;a relacionadas con el mercado laboral, el Servicio Nacional de Empleo (SNE) te ofrece el Portal del Empleo www.empleo.gob.mx.
	</jsp:attribute>
	<jsp:body>
	
	<!-- div ruta_navegacion -->
    <div class="row">
      <div class="col-sm-12">
        <ol class="breadcrumb">
          <li>Ruta de navegación:</li>
          <li><a href="<c:url value="/inicio.do"/>">Inicio</a></li>
          <li><a href="<c:url value="/jsp/empleo/servicioNacionalDeEmpleo/servicioNacionalDeEmpleo.jsp"/>">Servicio Nacional de Empleo</a></li>
          <li><a href="<c:url value="/jsp/empleo/servicioNacionalDeEmpleo/programasServiciosEmpleo.jsp"/>">Programas y servicios de empleo</a></li>
          <li><a href="<c:url value="/jsp/empleo/servicioNacionalDeEmpleo/vinculacionLaboral.jsp"/>">Vinculación laboral</a></li>
          <li class="active">Portal del Empleo</li>
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
			<h2 class="titulosh2">Portal del Empleo</h2>
		
	        <p>Si deseas obtener en un solo sitio de internet, informaci&oacute;n, orientaci&oacute;n, capacitaci&oacute;n y asesor&iacute;a relacionadas con el mercado laboral, el Servicio Nacional de Empleo (SNE) te ofrece el Portal del Empleo www.empleo.gob.mx.</p>
<p>El Portal del Empleo es una herramienta de atenci&oacute;n y apoyo a la ciudadan&iacute;a que orienta, capacita y asesora en temas relacionados con el mundo del trabajo a personas desempleadas, empresas, estudiantes y sus familias; trabajadores en activo que desean mejorar sus ingresos; acad&eacute;micos, entre otros.</p>
<p>Algunos servicios gratuitos que ofrece el Portal del Empleo son:</p>
<ol>
	<!--  las 24 horas del d&iacute;a  -->
    <li>Servicio telef&oacute;nico, ll&aacute;manos de 8 am a 10 pm  los 365 d&iacute;as del a&ntilde;o, y se puede tener acceso desde cualquier lugar.</li>
    <li>Informaci&oacute;n nacional y local.</li>
    <li>Asesor&iacute;a en l&iacute;nea v&iacute;a chat.</li>
    <li>Cursos de capacitaci&oacute;n en l&iacute;nea.</li>
    <li>Acceso a ofertas de empleo de las principales bolsas de trabajo privadas en M&eacute;xico, as&iacute; como vacantes del Gobierno Federal.</li>
    <li class="no_line">B&uacute;squeda f&aacute;cil de ofertas de empleo y candidatos por entidad federativa, oficio y profesi&oacute;n, entre otras opciones.</li>
</ol>
<p>El Portal del Empleo sirve a quienes buscan empleo, a quienes lo ofrecen, a quienes est&aacute;n interesados en conocer las tendencias locales y mundiales en la creaci&oacute;n de empleo, a los que desean conocer la demanda que existe para una cierta profesi&oacute;n o a quienes les interese conocer cu&aacute;l es el salario promedio que se paga en alg&uacute;n estado del pa&iacute;s para determinadas ocupaciones.</p>
<p>El objetivo del Portal del Empleo es ser el m&aacute;s grande punto de encuentro entre la oferta y la demanda en el pa&iacute;s.</p>
<p>El Portal del Empleo brinda informaci&oacute;n para:</p>
<ul class="list-group-contenidos">
    <li>Personas desempleadas.</li>
    <li>Empresas.</li>
    <li>Trabajadores en activo.</li>
    <li>Acad&eacute;micos, investigadores, analistas y gobiernos.</li>
</ul>
<p><strong>Requisitos:</strong></p>
<ol>
    <li>Contar con una computadora con acceso a internet y escribir en el navegador de &eacute;sta, la direcci&oacute;n electr&oacute;nica www.empleo.gob.mx.</li>
    <li>Si eres candidato, reg&iacute;strate en la secci&oacute;n correspondiente a Candidatos, para conocer los datos de contacto de las vacantes y postularte a ellas.</li>
    <li>Si eres empresa, reg&iacute;strate en la secci&oacute;n correspondiente a Empresas, y da de alta vacantes para encontrar candidatos acordes a tus ofertas.</li>
</ol>
<p>Para mayor información, acude a la oficina del Servicio Nacional de Empleo más cercana a tu domicilio.</p>
<!--  las 24 horas del día  -->
<p>También, puedes llamarnos al 01800 841 2020, sin ningún costo, ll&aacute;manos de 8 am a 10 pm los 365 días del año, y uno de nuestros consejeros con gusto te atenderá.</p>
       	   </div>
	      </div>
	     </div>



    </div>
	</jsp:body>
</t:publicTemplate>
