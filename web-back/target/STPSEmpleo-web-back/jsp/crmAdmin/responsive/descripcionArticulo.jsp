<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="logic" uri="http://struts.apache.org/tags-logic" %>
<%@ taglib prefix="bean" uri="http://struts.apache.org/tags-bean" %>
<%@ taglib prefix="s" uri="http://struts.apache.org/tags-logic" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="url" value="${pageContext.request.requestURI}" />

<%
	String context = request.getContextPath() +"/";
	pageContext.setAttribute("context", context);
		
%>

<t:publicTemplate>
	<jsp:attribute name="titulo">${crmCreateArticuloForm.titulo}</jsp:attribute>
    <jsp:attribute name="palabraDescripcion">Candidatos</jsp:attribute>  
    <jsp:attribute name="tituloSitio">Portal del Empleo</jsp:attribute>
 	<jsp:attribute name="urlRedSocial"><c:url value="${url}"/></jsp:attribute>
	<jsp:attribute name="descripcionRedSocial">${crmCreateArticuloForm.titulo}, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo</jsp:attribute>
	<jsp:body>
	
	<!-- div ruta_navegacion -->
    <div class="row">
      <div class="col-sm-12">
        <ol class="breadcrumb">
          <li>Ruta de navegación:</li>
          <li><a href="#">Herramientas del sitio</a></li>
          <li class="active">Artículos de interés para ti</li>
        </ol>
      </div>
    </div>
    <!-- /div ruta_navegacion -->
    

    <div class="row">
 
	  <!-- div contenido -->
      <div class="col-sm-12">
      
      <jsp:include page="/WEB-INF/template/redes.jsp"/>
      
      <div class="panelArticulos">
      
			<div class="page-header noMargin">
				<h2 class="titulosh2">${crmCreateArticuloForm.titulo}</h2>
			</div>
			
			<div class="panel-descripcionArticulo">
	          <div class="panel-body">
	          	<p><b>Fuente:</b> <span>${crmCreateArticuloForm.fuente}</span></p>
	          	<p><span title="Fecha" class="glyphicon glyphicon-calendar"></span> ${crmCreateArticuloForm.fecha}</p>
	            <p><b>Temas relacionados</b>
					<c:forEach  items="${crmCreateArticuloForm.etiquetaForms}" var="etiquetas">
					
					<c:choose>
					    <c:when test="${(etiquetas.id == 161)}">
					        <span class="badgeArticulo" style="cursor: pointer;" onclick="window.location = '${pageContext.request.contextPath}/articulo.do?id=565';">${etiquetas.etiqueta}</span>
					        
					    </c:when>  
					    <c:when test="${(etiquetas.id == 162)}">
					        <span class="badgeArticulo" style="cursor: pointer;" onclick="window.location = '${pageContext.request.contextPath}/articulo.do?id=563';">${etiquetas.etiqueta}</span>
					        
					    </c:when>  
					    <c:when test="${(etiquetas.id == 163)}">
					        <span class="badgeArticulo" style="cursor: pointer;" onclick="window.location = '${pageContext.request.contextPath}/articulo.do?id=559';">${etiquetas.etiqueta}</span>
					        
					    </c:when>  
					    <c:when test="${(etiquetas.id == 164)}">
					        <span class="badgeArticulo" style="cursor: pointer;" onclick="window.location = '${pageContext.request.contextPath}/articulo.do?id=561';">${etiquetas.etiqueta}</span>
					        
					    </c:when>    
					    <c:otherwise>
					        <span class="badgeArticulo" style="cursor: pointer;" onclick="window.location = '${context}/articuloDeInteress.do?temasEmpleo=${etiquetas.etiqueta}&idTema=${etiquetas.id}';">${etiquetas.etiqueta}</span>
					        
					    </c:otherwise>
					</c:choose>
				                  	
                </c:forEach>
				</p>
	          </div>
	        </div>
        
	        <div class="panel">
	          <div class="panel-body">
	          	${crmCreateArticuloForm.articulo}
				
				<div class="form-group">
				
				<c:forEach  items="${crmCreateArticuloForm.etiquetaForms}" var="etiquetas">
				
				<c:choose>
					    <c:when test="${(etiquetas.id == 161) or (etiquetas.id == 162) or (etiquetas.id == 163) or (etiquetas.id == 164)}">
					        <c:set var = "sectur" scope = "session" value = "${1}"/>
					    </c:when>  
					    <c:otherwise>
					        <c:set var = "sectur" scope = "session" value = "${0}"/>
					    </c:otherwise>
					</c:choose>
				
				</c:forEach>
				
				<c:choose>
					    <c:when test="${sectur==1}">
					    	<input class="bntArticulos" onclick="window.location = '<c:url value="/articuloDeInteress.do"/>';" name="Regresar"  type="button" value="Regresar a temas de empleo" alt=""/>&nbsp;
					        <input class="bntArticulos" onclick="window.location = '<c:url value="${pageContext.request.contextPath}/ofertasSectur.do?method=init"/>';" name="Regresar"  type="button" value="Ir a Micrositio" alt=""/>
					    </c:when>  
					    <c:otherwise>
					        <input class="bntArticulos" onclick="window.location = '<c:url value="/articuloDeInteress.do"/>';" name="Regresar"  type="button" value="Regresar a temas de empleo" alt=""/>
					    </c:otherwise>
					</c:choose>
					
					
				</div>
	          </div>
	        </div>
	        
        </div>
        
      </div><!-- /div contenido -->
    </div>
    
	</jsp:body>
</t:publicTemplate>