	<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="url" value="${pageContext.request.requestURI}" />


<t:publicTemplate>
	<jsp:attribute name="titulo">&iquest;Qu&eacute; es una carta de recomendaci&oacute;n?</jsp:attribute>
    <jsp:attribute name="palabraDescripcion">Candidatos</jsp:attribute>  
    <jsp:attribute name="tituloSitio">Portal del Empleo</jsp:attribute>
	<jsp:attribute name="urlRedSocial"><c:url value="${url}"/></jsp:attribute>
	<jsp:attribute name="descripcionRedSocial">
		La evaluación de un candidato a un puesto de trabajo es una actividad de la empresa o el departamento de Recursos Humanos, es muy típico pedir referencias o una valoración del candidato a empresas en las que trabajó anteriormente, y que sirven para verificar lo expuesto en el currículum, o lo dicho por el candidato durante la entrevista, así como para resolver ciertas incoherencias que el entrevistador pueda haber detectado.
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
          <li><a href="<c:url value="/jsp/empleo/candidatos/postula.jsp"/>">Postula</a></li>
          <li class="active">La carta de recomendación</li>
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
			<h2 class="titulosh2">La carta de recomendación</h2>
		
	        <p>La evaluación de un candidato a un puesto de trabajo es una actividad de la empresa o el departamento de Recursos Humanos, es muy típico pedir referencias o una valoración del candidato a empresas en las que trabajó anteriormente, y que sirven para verificar lo expuesto en el currículum, o lo dicho por el candidato durante la entrevista, así como para resolver ciertas incoherencias que el entrevistador pueda haber detectado.</p>
	        <p>Si te encuentras en búsqueda activa de empleo, es necesario tener control sobre las referencias y cartas de recomendación que pudieran solicitarte. Prepárate y analiza muy bien a cada uno de tus contactos, eligiendo y elaborando con anticipación una lista de las personas más adecuadas que puedan dar razón de tu buen hacer.</p>
	        
       	   </div>
	      </div>
	     </div>



    </div>
	</jsp:body>
</t:publicTemplate>
