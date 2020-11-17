<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="url" value="${pageContext.request.requestURI}" />


<t:publicTemplate>
	<jsp:attribute name="titulo">&iquest;C&oacute;mo redactar un curr&iacute;culum vitae?</jsp:attribute>
    <jsp:attribute name="palabraDescripcion">Candidatos</jsp:attribute>  
    <jsp:attribute name="tituloSitio">Portal del Empleo</jsp:attribute>
 	<jsp:attribute name="urlRedSocial"><c:url value="${url}"/></jsp:attribute>
	<jsp:attribute name="descripcionRedSocial">
		Adem&aacute;s de las habilidades que debes desarrollar, que forman parte del Taller para Buscadores de Empleo: <strong>Habilidades para la B&uacute;squeda de Empleo</strong>, es necesario que conozcas algunas herramientas que te facilitan la b&uacute;squeda de empleo. Una de ellas, la principal, es tu curr&iacute;culum vitae (CV), el cual est&aacute; orientado a cumplir dos objetivos centrales:
	</jsp:attribute>
	<jsp:body>
	
	<!-- div ruta_navegacion -->
    <div class="row">
      <div class="col-sm-12">
        <ol class="breadcrumb">
          <li>Ruta de navegación:</li>
          <li><a href="<c:url value="/inicio.do"/>">Inicio</a></li>
          <li><a href="<c:url value="/jsp/empleo/candidatos/candidatos.jsp"/>">Candidatos</a></li>
          <li><a href="<c:url value="/jsp/empleo/candidatos/habilidadesBusquedaEmpleo.jsp"/>">Habilidades para la Búsqueda de Empleo (HPBE)</a></li>
          <li><a href="<c:url value="/jsp/empleo/candidatos/postula.jsp"/>">Postula</a></li>
          <li class="active">¿Cómo redactar un currículum vitae?</li>
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
			<h2 class="titulosh2">¿Cómo redactar un currículum vitae?</h2>
		
	        <p>Adem&aacute;s de las habilidades que debes desarrollar, que forman parte del Taller para Buscadores de Empleo: <strong>Habilidades para la B&uacute;squeda de Empleo</strong>, es necesario que conozcas algunas herramientas que te facilitan la b&uacute;squeda de empleo. Una de ellas, la principal, es tu curr&iacute;culum vitae (CV), el cual est&aacute; orientado a cumplir dos objetivos centrales:</p>
<ul class="list-group-contenidos">
    <li>Proveer de informaci&oacute;n personal a las empresas en las que te interesa trabajar.</li>
    <li class="no_line">Captar la atenci&oacute;n de los probables empleadores con el fin de que puedas conseguir una entrevista, en la que puedas ampliar la informaci&oacute;n que asentaste en tu curr&iacute;culo.</li>
</ul>
<p>El curr&iacute;culo es la herramienta m&aacute;s importante en la b&uacute;squeda de empleo, cuya traducci&oacute;n es <strong>historia de vida</strong>, y en la que debes consignar la historia de tu formaci&oacute;n y educaci&oacute;n recibidas, as&iacute; como la experiencia laboral que has acumulado durante tu trayectoria ocupacional; debe construirse orientando la informaci&oacute;n que en &eacute;l se consigna hacia la vacante que te interesa.</p>
<p>En el siguiente v&iacute;nculo puedes encontrar diversos <a href="${context}/download/candidatos/estilos_de_curriculum_vitae.docx">estilos de CV</a>, mismos que puedes emplear como plantilla para realizar el tuyo.</p>
<p>Finalmente, te recomendamos que, a la hora de hacer tu curr&iacute;culum vitae (CV), tomes en cuenta las siguientes recomendaciones:</p>
<ul class="list-group-contenidos">
    <li>A menos que te lo soliciten, no pongas tu fotograf&iacute;a.</li>
    <li>Inmediatamente despu&eacute;s de tu nombre, coloca tus datos de contacto: tel&eacute;fono vigente (fijo y celular) y un correo electr&oacute;nico serio (no uses cuentas como: elpatitofeliz@cualquier.com).</li>
    <li>Si a&uacute;n no haz definido tu objetivo laboral, om&iacute;telo y resalta las habilidades que te hacen id&oacute;neo para la vacante.</li>
    <li>Anota s&oacute;lo tu &uacute;ltimo grado de estudios.</li>
    <li>Anota s&oacute;lo tus empleos m&aacute;s recientes (2), o los m&aacute;s relevantes respecto al puesto que te interesa.</li>
    <li class="no_line">En el campo de Otros Datos, haz hincapi&eacute; en las habilidades o conocimientos que posees.</li>
</ul>
       	   </div>
	      </div>
	     </div>



    </div>
	</jsp:body>
</t:publicTemplate>
