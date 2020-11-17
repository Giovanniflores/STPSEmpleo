<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="url" value="${pageContext.request.requestURI}" />

<t:publicTemplate>
	<jsp:attribute name="titulo">CONOCER. Formas de aumentar la competitividad</jsp:attribute>
    <jsp:attribute name="palabraDescripcion">Candidatos</jsp:attribute>  
    <jsp:attribute name="tituloSitio">Portal del Empleo</jsp:attribute>
 	<jsp:attribute name="urlRedSocial"><c:url value="${url}"/></jsp:attribute>
	<jsp:attribute name="descripcionRedSocial">
		Nuevas formas de aumentar la competitividad de su empresa El CONOCER es la entidad del gobierno federal que promueve el Sistema Nacional de Competencias en el país y reconoce los conocimientos, habilidades, destrezas y comportamientos de las personas sin importar la forma en que estos hayan sido adquiridos, otorgando certificados con validez nacional. Esto, facilita la contratación de personal calificado, reduce tiempos y costos en los procesos de reclutamiento y selección de candidatos y aumenta la competitividad de los trabajadores y de las organizaciones.
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
          <li><a href="<c:url value="/jsp/empleo/candidatos/certificacionCompetenciasConocer.jsp"/>">Certificación de competencias (CONOCER)</a></li>
          <li class="active">CONOCER. Formas de aumentar la competitividad</li>
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
			<h2 class="titulosh2">CONOCER. Formas de aumentar la competitividad</h2>
		
	        <p>Nuevas formas de aumentar la competitividad de su empresa</p>
			<p>El CONOCER es la entidad del gobierno federal que promueve el Sistema Nacional de Competencias en el país y reconoce los conocimientos, habilidades, destrezas y comportamientos de las personas sin importar la forma en que estos hayan sido adquiridos, otorgando certificados con validez nacional. Esto, facilita la contratación de personal calificado, reduce tiempos y costos en los procesos de reclutamiento y selección de candidatos y aumenta la competitividad de los trabajadores y de las organizaciones.</p>
			<p>El CONOCER promueve la integración de Comités Sectoriales de Gestión por Competencias, los cuales definen la agenda de capital humano para la competitividad del sector al que representan y desarrollan Estándares de Competencia que describen los conocimientos, habilidades, destrezas y comportamientos que debe tener una persona para llevar a cabo una función con un alto nivel de desempeño.</p>
			<p>Actualmente existen Comités de Gestión por Competencias instalados en diversos sectores, como lo son: Comercio, Construcción e Inmobiliario, Logística, Procesamiento de Alimentos, Tecnologías de la Información, Turismo, Financiero, Agrícola y Pecuario, entre otros.</p> 
			<p>Te invitamos a revisar el <a href="http://www.conocer.gob.mx/index.php/index.php?option=com_wrapper&amp;view=wrapper&amp;Itemid=11" target="_blank">Registro Nacional de Est&aacute;ndares de Competencia</a>, donde seguramente encontrarás Estándares de Competencia relacionados con las funciones que desarrolla tu personal dentro de la empresa o institución.</p> 
			<p>Funcionamiento de un Comité Sectorial de Gestión por Competencias:</p> 
			 <img alt="Conocer" src="${context}/css/images/contenido/esquema_empresas.jpg" class="img-responsive"/>
			 <p>Cuando una empresa contrata personal certificado en estándares de competencia relacionados con su sector, obtiene beneficios como:</p>
			 <ul class="list-group-contenidos">
			 <li>Certidumbre para la contratación de los mejores candidatos y reducción de costos transaccionales en los mercados laborales, particularmente en los procesos de reclutamiento y selección de personal.</li>
			 <li>Mayor seguridad para los clientes acerca de la calidad de los productos y servicios ofrecidos.</li>
			 <li>Atracción de inversión extranjera al contar con personal certificado en las empresas.</li>
			 <li>Reducción de los tiempos, costos y desperdicios en la producción.</li>
			 <li>Cerrar brechas de conocimientos entre el personal y ofrecer servicios más estandarizados.</li>
			 <li>Aumentar la seguridad y disminuir los riesgos y accidentes de trabajo, así como disminuir el ausentismo y la rotación del personal.</li>
			 <li>Incremento de las ventas y satisfacción de tus clientes.</li>
			 </ul>			 
			 
<p><strong>CONOCER Tel.</strong><br />
Desde la Cd. de M&eacute;xico <strong>2282-0200</strong><br />
Del interior marca <strong>01 800 288 26 66</strong></p>
<p>M&aacute;s informaci&oacute;n en <a href="http://www.conocer.gob.mx">www.conocer.gob.mx</a></p>
		

       	   </div>
	      </div>
	     </div>



    </div>
	</jsp:body>
</t:publicTemplate>
