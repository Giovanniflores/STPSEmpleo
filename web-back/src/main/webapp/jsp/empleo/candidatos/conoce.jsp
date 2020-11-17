<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="url" value="${pageContext.request.requestURI}" />


<t:publicTemplate>
	<jsp:attribute name="titulo">Conoce tus habilidades</jsp:attribute>
    <jsp:attribute name="palabraDescripcion">Candidatos</jsp:attribute>  
    <jsp:attribute name="tituloSitio">Portal del Empleo</jsp:attribute>
 	<jsp:attribute name="urlRedSocial"><c:url value="${url}"/></jsp:attribute>
	<jsp:attribute name="descripcionRedSocial">
		El objetivo es que te conozcas a ti misma/mismo; sepas cuáles son los servicios de vinculación que te ofrece el SNE; y aprendas a establecer cuáles son algunas de las características del mercado de trabajo que debes contemplar durante tu búsqueda. De igual manera se trata de que identifiques cuáles son las habilidades y capacidades que posees y cómo se ajustan a lo que se solicita en el mercado de trabajo y, finalmente, que definas tu objetivo laboral.
	</jsp:attribute>
	<jsp:body>
	
	<!-- div ruta_navegacion -->
    <div class="row">
      <div class="col-sm-12">
        <ol class="breadcrumb">
          <li>Ruta de navegación:</li>
           <li><a href="<c:url value="/inicio.do"/>">Inicio</a></li>
          <li><a href="<c:url value="/jsp/empleo/candidatos/candidatos.jsp"/>">Candidatos</a></li>
          <li><a href="<c:url value="/jsp/empleo/candidatos/habilidadesBusquedaEmpleo.jsp"/>">Habilidades para la Búsqueda de Empleo (HPBE) </a></li>
          <li class="active">Conoce</li>
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
			<h2 class="titulosh2">Conoce</h2>
		
	        <p>El objetivo es que te conozcas a ti misma/mismo; sepas cuáles son los servicios de vinculación que te ofrece el SNE; y aprendas a establecer cuáles son algunas de las características del mercado de trabajo que debes contemplar durante tu búsqueda. De igual manera se trata de que identifiques cuáles son las habilidades y capacidades que posees y cómo se ajustan a lo que se solicita en el mercado de trabajo y, finalmente, que definas tu objetivo laboral.</p>

       	   </div>
	      </div>
	     </div>



    </div>
	</jsp:body>
</t:publicTemplate>
