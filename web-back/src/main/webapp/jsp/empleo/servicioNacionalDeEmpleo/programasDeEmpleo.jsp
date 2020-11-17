<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="ftp" value="${FTP_STATIC_CONTENT}" />
<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="url" value="${pageContext.request.requestURI}" />

<t:publicTemplate>
	<jsp:attribute name="titulo">Programas de empleo SNE</jsp:attribute>
    <jsp:attribute name="palabraDescripcion">Servicio Nacional de Empleo</jsp:attribute>  
    <jsp:attribute name="tituloSitio">Portal del Empleo</jsp:attribute>
 	<jsp:attribute name="urlRedSocial"><c:url value="${url}"/></jsp:attribute>
	<jsp:attribute name="descripcionRedSocial">
		Consulta la informaci&oacute;n estad&iacute;stica correspondiente a los programas de empleo orientados a la generaci&oacute;n y preservaci&oacute;n del empleo, por medio de alternativas de ocupaci&oacute;n.
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
          <li class="active">Programas de empleo</li>
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
			<h2 class="titulosh2">Programas de empleo</h2>
		
	        <p>Consulta la informaci&oacute;n estad&iacute;stica correspondiente a los programas de empleo orientados a la generaci&oacute;n y preservaci&oacute;n del empleo, por medio de alternativas de ocupaci&oacute;n.</p>
<p>Selecciona la informaci&oacute;n que deseas consultar:</p>
<p><strong><a href="${ftp}/estadisticas/PASCL1.xls">Programa de Atenci&oacute;n a Situaciones de Contingencia Laboral</a></strong><br />
Estad&iacute;sticas del Programa de Atenci&oacute;n a Situaciones de Contingencia Laboral, el cual tiene por objetivo contribuir a la preservaci&oacute;n del empleo u ocupaci&oacute;n productiva, y a la recuperaci&oacute;n econ&oacute;mica, en caso de presentarse situaciones de contingencia laboral.</p>
<p><strong><a href="${ftp}/estadisticas/PET1.xls">Programa de Empleo Temporal Urbano</a></strong><br />
Estad&iacute;sticas del Programa de Empleo Temporal Urbano, orientado al apoyo de forma temporal en el ingreso monetario de la poblaci&oacute;n afectada durante periodos de baja demanda laboral o emergencias originadas por fen&oacute;menos naturales o causas econ&oacute;micas.</p>
<p><strong><a href="${ftp}/estadisticas/Prog_de_Trab_Agric_Migr_Temp_MC2.xls">Programa de Trabajadores Agr&iacute;colas Temporales M&eacute;xico-Canad&aacute;</a></strong><br />
Estad&iacute;sticas del Programa de Trabajadores Agr&iacute;colas Temporales M&eacute;xico-Canad&aacute;, una alternativa de ocupaci&oacute;n segura, ordenada y legal para trabajadores mexicanos que desean laborar temporalmente en granjas agr&iacute;colas canadienses en actividades de cultivo y cosecha, as&iacute; como en actividades de apicultura y horticultura.</p>
       	   </div>
	      </div>
	     </div>



    </div>
	</jsp:body>
</t:publicTemplate>
