<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="ftp" value="${FTP_STATIC_CONTENT}" />
<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="context" value="${pageContext.request.requestURL}" />


<t:publicTemplate>
	<jsp:attribute name="titulo">Programa de Apoyo al Empleo (PAE)</jsp:attribute>
    <jsp:attribute name="palabraDescripcion">Servicio Nacional de Empleo</jsp:attribute>  
    <jsp:attribute name="tituloSitio">Portal del Empleo</jsp:attribute>
 	<jsp:attribute name="urlRedSocial"><c:url value="${url}"/></jsp:attribute>
	<jsp:attribute name="descripcionRedSocial">
		El objetivo del <strong>Programa de Apoyo al Empleo</strong> es promover la colocaci&oacute;n, en una ocupaci&oacute;n o actividad productiva, de personas desempleadas o subempleadas  mediante el otorgamiento de apoyos econ&oacute;micos o en especie que permitan la capacitaci&oacute;n, autoempleo o movilidad laboral requerida para su desarrollo.
	</jsp:attribute>
	<jsp:body>
	
	<!-- div ruta_navegacion -->
    <div class="row">
      <div class="col-sm-12">
        <ol class="breadcrumb">
          <li>Ruta de navegación:</li>
          <li><a href="<c:url value="/inicio.do"/>">Inicio</a></li>
          <li><a href="<c:url value="/jsp/empleo/servicioNacionalDeEmpleo/servicioNacionalDeEmpleo.jsp"/>">Servicio Nacional de Empleo</a></li>
          <li><a href="<c:url value="/jsp/empleo/servicioNacionalDeEmpleo/estadisticasLaborales.jsp"/>">Estadísticas laborales</a></li>
          <li class="active">Programas de Apoyo al Empleo</li>
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
			
			<h3 class="titulosh3">Programa de Apoyo al Empleo</h3>
			<p>Estadísticas del Programa de Apoyo al Empleo, el cual, promueve la colocación, en una ocupación o actividad productiva, de personas desempleadas o subempleadas mediante el otorgamiento de apoyos económicos o en especie que permitan la capacitación, autoempleo o movilidad laboral requerida para su desarrollo</p>
			<p>
				<a href="${ftp}/estadisticas/PAE.xls">PAE.xls</a>
			</p>
			<h3 class="titulosh3">Fomento al autoempleo</h3>
			<p>Estadísticas del Programa Fomento al Autoempleo (FA), un subprograma que tiene como objetivo apoyar con la entrega de mobiliario, maquinaria, equipo y/o herramienta a los buscadores de empleo que desean desarrollar una actividad por cuenta propia y que, aun teniendo experiencia, no logran vincularse a un puesto de trabajo.</p>
			<p>
				<a href="${ftp}/estadisticas/PAE_Fomento_al_Autoempleo.xls">PAE_Fomento_al_Autoempleo.xls</a>
			</p>
			<h3 class="titulosh3">Becas a la Capacitación para el Trabajo (Bécate)</h3>
			<p>Estadísticas del subprograma Bécate, el cual,  apoya a buscadores de empleo que requieran capacitarse para facilitar su colocación en un empleo o el desarrollo de una actividad productiva por cuenta propia.</p>
			<p>
				<a href="${ftp}/estadisticas/PAE_Becate.xls">PAE_Becate.xls</a>
			</p>
			<h3 class="titulosh3">Movilidad Laboral Interna</h3>
			<p>Estadísticas del subprograma Movilidad Laboral Interna, el cual, apoya económicamente a los Solicitantes de empleo que requieren trasladarse a Entidades federativas, municipios o localidades distintas al lugar de su residencia, con fines ocupacionales, para ocupar un puesto de trabajo acorde a su perfil laboral.</p>
			<p>
				<a href="${ftp}/estadisticas/PAE_Mov_Lab_Int.xls">PAE_Mov_Lab_Int.xls</a>
			</p>
			<h3 class="titulosh3">Repatriados trabajando</h3>
			<p>Estadísticas del subprograma Repatriados Trabajando, el cual, apoya a Solicitantes de empleo seleccionados que hayan sido repatriados por algún estado de la frontera norte del país o alguno de los aeropuertos que las autoridades migratorias señalen como puntos de repatriación de connacionales, que manifiesten a la OSNE no tener intenciones de emigrar nuevamente al extranjero y su interés por encontrar un empleo en su lugar de origen o residencia, así como no haber sido beneficiado por este subprograma.</p>
			<p>
				<a href="${ftp}/estadisticas/PAE_Repatriados_Trabajando.xls">PAE_Repatriados_Trabajando.xls</a>
			</p>
       	   </div>
	      </div>
	     </div>



    </div>
	</jsp:body>
</t:publicTemplate>
