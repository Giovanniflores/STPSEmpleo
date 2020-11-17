<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="url" value="${pageContext.request.requestURI}" />


<t:publicTemplate>
	<jsp:attribute name="titulo">Trabajadores Agr&iacute;colas Temporales M&eacute;xico-Canad&aacute;</jsp:attribute>
    <jsp:attribute name="palabraDescripcion">Servicio Nacional de Empleo</jsp:attribute>  
    <jsp:attribute name="tituloSitio">Portal del Empleo</jsp:attribute>
 	<jsp:attribute name="urlRedSocial"><c:url value="${url}"/></jsp:attribute>
	<jsp:attribute name="descripcionRedSocial">
		El Programa de Trabajadores Agr&iacute;colas Temporales M&eacute;xico-Canad&aacute; es una alternativa de ocupaci&oacute;n temporal, ordenada, legal y segura para trabajadores agr&iacute;colas mexicanos en ese pa&iacute;s con pleno respeto de sus derechos laborales y en igualdad de condiciones a la de los trabajadores canadienses.
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
          <li><a href="<c:url value="/jsp/empleo/servicioNacionalDeEmpleo/mecanismoMovilidadLaboralInternaExterna.jsp"/>">Mecanismo de Movilidad Laboral interna y externa</a></li>
          <li class="active">Programa de Trabajadores Agrícolas Temporales México-Canadá (PTAT)</li>
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
			<h2 class="titulosh2">Programa de Trabajadores Agrícolas Temporales México-Canadá (PTAT)</h2>
		
	        <p>El Programa de Trabajadores Agr&iacute;colas Temporales M&eacute;xico-Canad&aacute; es una alternativa de ocupaci&oacute;n temporal, ordenada, legal y segura para trabajadores agr&iacute;colas mexicanos en ese pa&iacute;s con pleno respeto de sus derechos laborales y en igualdad de condiciones a la de los trabajadores canadienses.</p>
<p>Las personas interesadas en trabajar en granjas agr&iacute;colas en Canad&aacute;, deber&aacute;n tener experiencia en, al menos, una de las siguientes actividades: cultivo y cosecha de verduras, frutas, cereales, tabaco, &aacute;rboles y pasto, as&iacute; como en labores de horticultura, apicultura y del sector pecuario.</p>
<p>Los aspirantes deben presentarse en la Oficina del Servicio Nacional de Empleo m&aacute;s cercana a su domicilio y cumplir con los siguientes requisitos:</p>
<ul class="list-group-contenidos">
    <li>Ser de nacionalidad mexicana y residir en el territorio nacional.</li>
    <li>Ser jornalero agr&iacute;cola o campesino y vivir en zona rural.</li>
    <li>Tener hijos y de preferencia estar casado o vivir en uni&oacute;n libre.</li>
    <li>Saber leer y escribir y haber estudiado como m&aacute;ximo primero de preparatoria.</li>
</ul>
<p>Si est&aacute;s interesado, deber&aacute;s solicitar informaci&oacute;n sobre el per&iacute;odo de reclutamiento y selecci&oacute;n en la <a href="<c:url value="/jsp/empleo/servicioNacionalDeEmpleo/ubicacion.jsp"/>">Oficina</a> del Servicio Nacional de Empleo m&aacute;s cercana a su domicilio, as&iacute; como considerar que el programa est&aacute; sujeto a la demanda de mano de obra agr&iacute;cola mexicana, por parte de los empleadores canadienses; por lo que la posibilidad de ingresar, depender&aacute; de los espacios de trabajo ofertados por dichos empleadores en cada temporada.</p>
       	   
       	   </div>
	      </div>
	     </div>



    </div>
	</jsp:body>
</t:publicTemplate>
