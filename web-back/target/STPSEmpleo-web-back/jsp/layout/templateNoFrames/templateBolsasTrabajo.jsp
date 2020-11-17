<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="url" value="${pageContext.request.requestURL}" />


<% String context = (String)application.getAttribute("DOMAIN_PORTAL");  
   String contextApp = request.getContextPath() +"/";
   if (null != request.getSession().getAttribute("FROM_CIL"))context = (String)application.getAttribute("DOMAIN_PORTAL_CIL");
%>


<link href="<%=request.getContextPath()%>/css/bootstrap/table-responsive.css" rel="stylesheet" type="text/css">

<script src="dojotoolkit/dojo/dojo.js" djConfig="parseOnLoad: true, locale:'es'"></script>
<link href='https://fonts.googleapis.com/css?family=Droid+Sans' rel='stylesheet' type='text/css' />


</head>

<t:publicTemplate>
	<jsp:attribute name="titulo">
      Bolsas de empleo asociadas
    </jsp:attribute>
    <jsp:attribute name="palabraDescripcion">Candidatos</jsp:attribute>  
    <jsp:attribute name="tituloSitio">Portal del empleo</jsp:attribute>
 	<jsp:attribute name="urlRedSocial"><c:url value="${url}"/></jsp:attribute>
	<jsp:attribute name="descripcionRedSocial">
		Bolsas de empleo asociadas
	</jsp:attribute>
	<jsp:body>
	
	<!-- div ruta_navegacion -->
    <div class="row">
      <div class="col-sm-12">
        <ol class="breadcrumb">
          <li>Ruta de navegación:</li>
          <li><a href="<c:url value="/inicio.do"/>">Inicio</a></li>
          <li><a href="<c:url value="/jsp/empleo/candidatos/candidatos.jsp"/>">Candidatos</a></li>
          <li><a href="<c:url value="/jsp/empleo/candidatos/alternativasBusquedaEmpleo.jsp"/>">Alternativas de búsqueda de empleo</a></li>
          <li class="active">Bolsas de empleo asociadas</li>
        </ol>
      </div>
    </div>

    <div class="row">
    
     <%--  <jsp:include page="/jsp/empleo/candidatos/menu.jsp"/>  --%>
      
      <div class="col-sm-12">
      	 
      	 <jsp:include page="/WEB-INF/template/redes.jsp"/>
      
        <div class="clearfix"></div>
		
		 <div class="panel">
          <div class="panel-body">
			<h2 class="titulosh2">Bolsas de empleo asociadas</h2>

		
		<tiles:insert name="body"/>
		
				 </div>
	     	 </div>
	     </div>



    </div>
	</jsp:body>
</t:publicTemplate>
	