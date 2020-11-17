<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="url" value="${pageContext.request.requestURI}" />

<t:publicTemplate>
	<jsp:attribute name="titulo">Herramientas del SNE</jsp:attribute>
    <jsp:attribute name="palabraDescripcion">Candidatos</jsp:attribute>  
    <jsp:attribute name="tituloSitio">Portal del Empleo</jsp:attribute>
 	<jsp:attribute name="urlRedSocial"><c:url value="${url}"/></jsp:attribute>
	<jsp:attribute name="descripcionRedSocial">
		El portal del empleo: En el sitio web: https://www.empleo.gob.mx, puedes acceder a una base de datos con miles 
		de empleos vacantes en muchas de las áreas laborales. Si lo deseas puedes registrarte en dicho portal para ver 
		los empleos de tu área profesional u ocupacional, postular a los que te interesen y recibir información sobre 
		nuevas opciones que se vayan registrando y para las cuales tengas el perfil solicitado. En el Portal también 
		te ofrecemos vínculos a otros sitios de búsqueda de empleo.
	</jsp:attribute>
	<jsp:body>
	
	<!-- div ruta_navegacion -->
    <div class="row">
      <div class="col-sm-12">
        <ol class="breadcrumb">
          <li>Ruta de navegación:</li>
          <li><a href="<c:url value="/inicio.do"/>">Inicio</a></li>
          <li><a href="<c:url value="/jsp/empleo/candidatos/candidatos.jsp"/>">Candidatos</a></li>
          <li><a href="<c:url value="/jsp/empleo/candidatos/habilidadesBusquedaEmpleo.jsp"/>">
          Habilidades para la Búsqueda de Empleo (HPBE)</a></li>
          <li><a href="<c:url value="/jsp/empleo/candidatos/conoce.jsp"/>">Conoce</a></li>
          <li class="active">Las herramientas que te ofrece el SNE</li>
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
			<h2 class="titulosh2">Las herramientas que te ofrece el SNE</h2>
		
	        <p><strong>El portal del empleo:</strong> En el sitio web: 
	        <a href="https://www.empleo.gob.mx/">https://www.empleo.gob.mx/</a>, puedes acceder a una base de datos 
	        con miles de empleos vacantes en muchas de las áreas laborales. Si lo deseas puedes registrarte en dicho 
	        portal para ver los empleos de tu área profesional u ocupacional, postular a los que te interesen y 
	        recibir información sobre nuevas opciones que se vayan registrando y para las cuales tengas el perfil 
	        solicitado. En el Portal también te ofrecemos vínculos a otros sitios de búsqueda de empleo.</p>
	        <p><strong>Bolsa de trabajo:</strong> Es un servicio que, a través de atención personalizada en las 
	        oficinas del SNE, ofrece información y orientación sobre las ofertas de empleo existentes en sus registros. 
	        El requisito para ser atendido es que muestres una identificación oficial vigente.</p>
	        <p><strong>Ferias de empleo:</strong> Son eventos masivos orientados a poner en contacto a las/los 
	        buscadoras/ buscadores con empresas que están tratando de cubrir vacantes. Estos eventos pueden ser 
	        presenciales o virtuales. En ambos casos se puede tener acceso a los eventos programados en cada entidad 
	        en el portal del empleo: <a href="http://ferias.empleo.gob.mx" target="_blank">http://ferias.empleo.gob.mx</a>, 
	        en caso de que te interese participar en alguna, puedes registrarte y seguir los pasos que allí se indican.</p>
	        <p><strong>SNE por teléfono (SNETEL):</strong> Está orientado a vincularte de manera rápida y efectiva con 
	        alguna de las vacantes que se encuentren disponibles y que sean de tu interés. Un ejecutivo te ofrece 
	        información telefónica sobre la que deberás decidir cuál es de tu interés para que, finalmente, te 
	        proporcione la información de la empresa a la que corresponde la vacante seleccionada.</p>
	        <p><strong>Periódico Ofertas de Empleo:</strong> Es una publicación gratuita de periodicidad quincenal que 
	        tiene como propósito agilizar la comunicación sobre las oportunidades de trabajo existentes en el momento 
	        de publicación, y cuyo fin es apoyar tu inserción o reinserción al mercado de trabajo.</p> 
	        <p><strong>Directorio de Oficinas del Servicio Nacional de Empleo:</strong> 
	        <a href="<c:url value="/jsp/empleo/servicioNacionalDeEmpleo/ubicacion.jsp"/>">En la Oficina del Servicio Nacional de Empleo</a> 
	        más cercana a tu domicilio te podemos ayudar a conseguir un empleo.<br/>
	        Llama o acude a alguna de las oficinas qué el Servicio Nacional de Empleo tiene cerca de tí y recibe apoyo personalizado.</p> 
	        <p><strong>Talleres 
	        para Buscadores de Empleo:</strong> Además, puedes acudir a los <a href="https://www.empleo.gob.mx/sne/calendario">Talleres 
	        para Buscadores de Empleo</a> programados en tu localidad, donde podrás desarrollar las habilidades para 
	        la búsqueda de empleo.</p>

       	   </div>
	      </div>
	     </div>



    </div>
	</jsp:body>
</t:publicTemplate>
