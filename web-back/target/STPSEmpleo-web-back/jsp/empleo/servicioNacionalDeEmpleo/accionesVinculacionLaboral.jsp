<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="ftp" value="${FTP_STATIC_CONTENT}" />
<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="url" value="${pageContext.request.requestURI}" />

<t:publicTemplate>
	<jsp:attribute name="titulo">Acciones de vinculaci&oacute;n laboral</jsp:attribute>
    <jsp:attribute name="palabraDescripcion">Servicio Nacional de Empleo</jsp:attribute>  
    <jsp:attribute name="tituloSitio">Portal del Empleo</jsp:attribute>
 	<jsp:attribute name="urlRedSocial"><c:url value="${url}"/></jsp:attribute>
	<jsp:attribute name="descripcionRedSocial">
		Consulta los reportes estad&iacute;sticos correspondientes a las principales acciones de vinculaci&oacute;n laboral promovidas por el Servicio Nacional de Empleo, en los 32 estados de la Rep&uacute;blica Mexicana.
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
          <li class="active">Acciones de vinculación laboral</li>
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
			<h2 class="titulosh2">Acciones de vinculación laboral</h2>
		
	        <p>Consulta los reportes estad&iacute;sticos correspondientes a las principales acciones de vinculaci&oacute;n laboral promovidas por el Servicio Nacional de Empleo, en los 32 estados de la Rep&uacute;blica Mexicana.</p>
<p>Selecciona la informaci&oacute;n que deseas consultar:</p>
<p><strong><a href="${ftp}/estadisticas/Abriendo_Espacios.xls">Abriendo espacios</a></strong><br />
Estad&iacute;sticas del programa &quot;Abriendo espacios&quot;, el cual promueve la creaci&oacute;n de bolsas de trabajo especializadas y el aprovechamiento de las capacidades de adultos mayores y personas con discapacidad.</p>
<p><strong><a href="${ftp}/estadisticas/Atencion_Emergente.xls">Atenci&oacute;n Emergente</a></strong><br />
Estad&iacute;sticas del &quot;Programa de Apoyo Emergente&quot;, orientado a preservar el equilibrio en el mercado laboral y promover las condiciones para el desarrollo de la actividad econ&oacute;mica y del empleo.</p>
<p><strong><a href="${ftp}/estadisticas/Atendidos_y_Colocados_por_genero.xls">Atendidos y colocados por g&eacute;nero</a></strong><br />
Estad&iacute;sticas de las personas atendidas y colocadas por medio de los programas del SNE, a nivel nacional.</p>
<p><strong><a href="${ftp}/estadisticas/Bolsa_de_Trabajo.xls">Bolsa de Trabajo</a></strong><br />
Estad&iacute;sticas del servicio gratuito de vinculaci&oacute;n laboral del SNE, el cual tiene por objetivo vincular al buscador  de empleo con opciones de trabajo acordes con su perfil laboral.</p>
<p><strong><a href="${ftp}/estadisticas/Centros_de_Intermediacion_Laboral.xls">Centros de Intermediaci&oacute;n Laboral (CIL)</a></strong><br />
Estad&iacute;sticas de los CIL, m&oacute;dulos de servicio, ubicados en las principales oficinas del SNE, equipados con computadores con acceso a internet y otras herramientas para el apoyo integral de los procesos de vinculaci&oacute;n laboral.</p>
<p><strong><a href="${ftp}/estadisticas/Ferias_de_Empleo.xls">Ferias de Empleo</a></strong><br />
Estad&iacute;sticas de las ferias de empleo, mecanismos directos y &aacute;giles, creados para atender las necesidades de recursos humanos de las empresas, y vincularlas con el perfil de los buscadores de empleo.</p>
<p><strong><a href="${ftp}/estadisticas/Portal_del_Empleo.xls">Portal del Empleo</a></strong><br />
Estad&iacute;sticas del Portal del Empleo, una herramienta de atenci&oacute;n y apoyo a la ciudadan&iacute;a que proporciona y facilita informaci&oacute;n, orientaci&oacute;n, capacitaci&oacute;n y asesor&iacute;a relacionadas con el mercado laboral.</p>
<p><strong><a href="${ftp}/estadisticas/Servicios_de_Vinculacion_Laboral.xls">Servicios de Vinculaci&oacute;n Laboral</a></strong><br />
Estad&iacute;sticas de los principales mecanismos de atenci&oacute;n del SNE, que promueven la vinculaci&oacute;n de buscadores de empleo con empresas que solicitan personal.</p>
<p><strong><a href="${ftp}/estadisticas/SNE_por_Telefono.xls">SNE por Tel&eacute;fono</a></strong><br />
Estad&iacute;sticas del SNE por tel&eacute;fono, el cual tiene como principales objetivos: facilitar la vinculaci&oacute;n entre oferentes y demandantes de empleo, e informar a las personas sobre los servicios que proporciona el SNE.</p>
       	   </div>
	      </div>
	     </div>



    </div>
	</jsp:body>
</t:publicTemplate>
