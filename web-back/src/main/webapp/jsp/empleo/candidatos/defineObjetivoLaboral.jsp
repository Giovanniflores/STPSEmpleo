<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="url" value="${pageContext.request.requestURI}" />


<t:publicTemplate>
	<jsp:attribute name="titulo">Define tu objetivo laboral</jsp:attribute>
    <jsp:attribute name="palabraDescripcion">Candidatos</jsp:attribute>  
    <jsp:attribute name="tituloSitio">Portal del Empleo</jsp:attribute>
 	<jsp:attribute name="urlRedSocial"><c:url value="${url}"/></jsp:attribute>
	<jsp:attribute name="descripcionRedSocial">
		 Definir tu objetivo laboral quiere decir que debes identificar que ocupaci&oacute;n o profesi&oacute;n quieres desarrollar en un periodo de tiempo corto, mediano y largo, siempre de acuerdo a tus motivaciones, intereses, necesidades, gustos, etc. En tal sentido, definir tu objetivo laboral es una responsabilidad exclusivamente tuya, y definirlo te puede ayudar a conseguir el empleo que te interesa y mantenerte en &eacute;l.
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
          <li><a href="<c:url value="/jsp/empleo/candidatos/conoce.jsp"/>">Conoce</a></li>
          <li class="active">Define tu objetivo laboral</li>
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
			<h2 class="titulosh2">Define tu objetivo laboral</h2>
		
	        <p>Definir tu objetivo laboral quiere decir que debes identificar que ocupaci&oacute;n o profesi&oacute;n quieres desarrollar en un periodo de tiempo corto, mediano y largo, siempre de acuerdo a tus motivaciones, intereses, necesidades, gustos, etc. En tal sentido, definir tu objetivo laboral es una responsabilidad exclusivamente tuya, y definirlo te puede ayudar a conseguir el empleo que te interesa y mantenerte en &eacute;l.</p>
			<p>Es importante que tengas un objetivo laboral ya que te ayudar&aacute; a orientar tu b&uacute;squeda y a postular a las vacantes de tu inter&eacute;s, tambi&eacute;n contribuye a la construcci&oacute;n de un objetivo de largo plazo, estableciendo metas de corto y mediano para que, finalmente, llegues al primero (de largo plazo).</p>
			<p>El objetivo que te traces debe ser realista, ello no implica que si trazas uno que en este momento no sea realista, no puedas llegar a &eacute;l; para lograrlo deber&aacute;s identificar que te hace falta en t&eacute;rminos de capacitaci&oacute;n para lograrlo.</p>
			<p>Si cuentas con 10 minutos quiz&aacute;s te interese definir tu objetivo laboral con la ayuda del siguiente <a href="${context}/download/candidatos/objetivo_laboral.docx" target="_new">formato</a>.</p>

       	   </div>
	      </div>
	     </div>



    </div>
	</jsp:body>
</t:publicTemplate>
