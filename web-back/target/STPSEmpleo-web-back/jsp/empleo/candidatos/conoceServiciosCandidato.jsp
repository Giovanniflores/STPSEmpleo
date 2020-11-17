<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="url" value="${pageContext.request.requestURI}" />


<t:publicTemplate>
	<jsp:attribute name="titulo">Servicios del candidato</jsp:attribute>
    <jsp:attribute name="palabraDescripcion">Candidatos</jsp:attribute>  
    <jsp:attribute name="tituloSitio">Portal del Empleo</jsp:attribute>
 	<jsp:attribute name="urlRedSocial"><c:url value="${url}"/></jsp:attribute>
	<jsp:attribute name="descripcionRedSocial">
		El Servicio Nacional de Empleo (SNE) te ofrece una serie de beneficios, con el objetivo de proporcionarte la información necesaria para aumentar tus posibilidades de encontrar empleo. Entérate de todos los servicios que el SNE tiene para ti.
	</jsp:attribute>
	<jsp:body>
	
	<!-- div ruta_navegacion -->
    <div class="row">
      <div class="col-sm-12">
        <ol class="breadcrumb">
          <li>Ruta de navegación:</li>
           <li><a href="<c:url value="/inicio.do"/>">Inicio</a></li>
          <li><a href="<c:url value="/jsp/empleo/candidatos/candidatos.jsp"/>">Candidatos</a></li>
          <li class="active">Conoce los servicios que el portal tiene para ti</li>
        </ol>
      </div>
    </div>
    <!-- /div ruta_navegacion -->

    <!-- div ruta_navegacion -->
    <div class="row">
    
      <jsp:include page="menu.jsp" />
      
      <div class="col-sm-8 col-sm-pull-4">
      	 
      	 <jsp:include page="/WEB-INF/template/redes.jsp" />
      
        <div class="panel panel-grey">
          <div class="panel-body">
          <div class="col-md-6">
            <img alt=""
								src="${context}/css/images/contenido/servicios.png"
								class="img-responsive">
          </div>
          <div class="col-md-6">
            <h2>Conoce los servicios que el portal tiene para ti</h2>
            <p>El Servicio Nacional de Empleo (SNE) te ofrece una serie de beneficios, con el objetivo de proporcionarte la información necesaria para aumentar tus posibilidades de encontrar empleo. Entérate de todos los servicios que el SNE tiene para ti.</p>
          </div>
        </div>
        </div>

        <div class="clearfix"></div>
        
        <div class="panel">
          <div class="panel-body">

	        <p>El Servicio Nacional de Empleo (SNE) cuenta con diversos servicios que te ser&aacute;n &uacute;tiles para la b&uacute;squeda de empleo.</p>
<p>Utiliza nuestro <a
								href="<c:url value="/miespacionav.do?method=agendaCita"/>"
								target="_blank">Sistema de Citas</a> para recibir atenci&oacute;n personalizada en el &aacute;mbito laboral. Te ayudaremos a buscar empleo en la bolsa de trabajo, te diremos c&oacute;mo obtener apoyos econ&oacute;micos para el autoempleo y te daremos informaci&oacute;n para acceder a cursos de capacitaci&oacute;n para mejorar tus habilidades.</p>
<p>Ent&eacute;rate de las oportunidades laborales que tenemos para ti, en el <a
								href="<c:url value="/jsp/empleo/servicioNacionalDeEmpleo/periodicoDeOfertasDeEmpleo.jsp"/>">Peri&oacute;dico de Ofertas de Empleo</a>. El peri&oacute;dico se publica en l&iacute;nea los d&iacute;as 1 y 16 de cada mes.</p>
<!-- <p>El SNE cuenta con servicio telef&oacute;nico las 24 horas del d&iacute;a, los 365 d&iacute;as del a&ntilde;o. Llama al 01800 8412020 y obt&eacute;n informaci&oacute;n sobre las ofertas laborales disponibles, recibe asesor&iacute;a sobre el Portal del Empleo y sobre los servicios que ofrece el SNE. Tambi&eacute;n puedes programar una cita en nuestras oficinas.</p> -->
<p>Ampl&iacute;a tus oportunidades de encontrar un trabajo acorde a tus necesidades, utilizando las principales bolsas de trabajo por internet. Accede a OCC, Adecco y Manpower, as&iacute; como a las vacantes que se publiquen en la Secretar&iacute;a de la Funci&oacute;n P&uacute;blica (SFP) y en el Instituto Mexicano de la Juventud (Inmjuve).</p>
<p>Si deseas adquirir y desarrollar nuevos conocimientos, inscr&iacute;bete a los cursos en l&iacute;nea que ofrece la Secretar&iacute;a del Trabajo y Previsi&oacute;n Social a trav&eacute;s de su programa de Capacitaci&oacute;n a Distancia para Trabajadores (<a target="_blank" href="https://www.procadist.gob.mx/portal/">Procadist</a>), conoce las becas a la capacitaci&oacute;n que te ofrece el SNE, as&iacute; como los cursos en l&iacute;nea de la <a target="_blank" href="https://www.manpowergroup.com.mx/fundacion-manpowergroup">Fundaci&oacute;n Manpower</a>.</p>
<p>Participa en las <a target="_blank"
								href="http://ferias.empleo.gob.mx/content/common/home.jsf">Ferias de Empleo</a> que organiza el SNE en todo el pa&iacute;s. Si est&aacute;s interesado en dar a conocer tu perfil laboral a varias empresas, en un mismo d&iacute;a y lugar, las Ferias de Empleo son para ti. Participa en su modalidad presencial o virtual.</p>
<p>Para conocer los resultados de los programas emprendidos por el SNE en las 32 entidades federativas del pa&iacute;s, descarga y consulta las <a
								href="<c:url value="/jsp/empleo/servicioNacionalDeEmpleo/estadisticasLaborales.jsp"/>")>estad&iacute;sticas laborales</a>, en formato Excel, desde 2001 a la fecha.</p>
	        
	       </div>
	     </div>
       
      </div>

    </div>
	</jsp:body>
</t:publicTemplate>
