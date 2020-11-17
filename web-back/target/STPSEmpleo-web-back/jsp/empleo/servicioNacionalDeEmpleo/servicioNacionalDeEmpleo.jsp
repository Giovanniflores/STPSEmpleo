<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="url" value="${pageContext.request.requestURI}" />
<t:publicTemplate>
	<jsp:attribute name="titulo">SNE</jsp:attribute>
    <jsp:attribute name="palabraDescripcion">Servicio Nacional de Empleo</jsp:attribute>  
    <jsp:attribute name="tituloSitio">Portal del Empleo</jsp:attribute>
 	<jsp:attribute name="urlRedSocial"><c:url value="${url}"/></jsp:attribute>
	<jsp:attribute name="descripcionRedSocial">
		El Servicio Nacional de Empleo (SNE) es la institución pública, a nivel nacional, que atiende los problemas de desempleo y subempleo en el país, en beneficio de sus habitantes. El SNE brinda diversos servicios, de manera gratuita y personalizada, para facilitar la búsqueda empleo y la vinculación laboral.
	</jsp:attribute>
	<jsp:body>
		<!-- div ruta_navegacion -->
	    <div class="row">
	      <div class="col-sm-12">
	        <ol class="breadcrumb">
	          <li>Ruta de navegación:</li>
	          <li><a href="<c:url value="/inicio.do"/>">Inicio</a></li>
	          <li class="active"> Servicio Nacional de Empleo</li>
	        </ol>
	      </div>
	    </div>
	    <!-- /div ruta_navegacion -->
	    
	    <div class="row">
		  <!-- div menu -->
		  <jsp:include page="menu.jsp"/>
		  
		  <!-- div contenido -->
	      <div class="col-sm-8 col-sm-pull-4">
	        <div class="panel panel-grey">
	          <div class="panel-body">
	          <div class="col-md-6">
	            <img alt="" src="${context}/css/images/contenido/canal_sne.png" class="img-responsive">
	          </div>
	          <div class="col-md-6">
	            <h2> Servicio Nacional de Empleo</h2>
	            <p>El servicio Nacional de Empleo (SNE) es la institución pública, a nivel nacional, que tiene como 
	            principales objetivos facilitar la vinculación entre oferentes y demandantes de empleo, orientar a 
	            los buscadores de empleo y apoyar su calificación, así como auxiliar a las empresas en la búsqueda 
	            de candidatos para cubrir sus vacantes de empleo.</p>
	          </div>
	        </div>
	        </div>
	
	        <div class="clearfix"></div>
	
	        <!--h2 class="titulosh2">Conoce los servicios que el SNE tiene para ti</h2>
	        <div class="panel panel-grey">
	          <div class="panel-heading">
	            <h3 class="panel-title">Vinculación laboral</h3>
	            <a href="<c:url value="/jsp/empleo/servicioNacionalDeEmpleo/vinculacionLaboral.jsp"/>">Contenido pendiente</a>
	          </div>
	          <div class="panel-body">
	            El objetivo de la vinculación y orientación ocupacional es informar y proporcionar, a las personas, información y herramientas de apoyo para facilitar su búsqueda de empleo y optimizar el contacto con las empresas.
	          </div>
	        </div-->
	
	        <h2 class="titulosh2">Conoce los servicios que el SNE tiene para ti</h2>
	        <div class="panel panel-grey">
	          <div class="panel-heading">
	            <h3 class="panel-title">
	              <a href="<c:url value="/jsp/empleo/servicioNacionalDeEmpleo/vinculacionLaboral.jsp"/>">Vinculación laboral</a>
	            </h3>
	          </div>
	          <div class="panel-body">
	            <p>La función de la colocación de trabajadores ha sido una de las acciones sustantivas del SNE, y se ha 
	            tenido el objetivo de ir desarrollando servicios de vinculación acordes a las circunstancias y necesidades 
	            del mercado laboral.</p>
	            
	            <p>De esta forma, se han establecido en el SNE servicios de vinculación que van desde la función tradicional
	            de las bolsas de trabajo y la consejería laboral, hasta los esquemas de autoayuda y aquéllos basados en las 
	            Tecnologías de la Información y la Comunicación (TIC), como el Portal del Empleo.</p>
	            
	            <p>A través de estos servicios, se brinda ayuda básica en el llenado de una solicitud de empleo, la 
	            elaboración del curriculum vitae, la forma correcta de buscar empleo en una bolsa de trabajo, hasta la 
	            vinculación entre un candidato con una oferta de empleo. Además, se proporciona asesoría, orientación e 
	            información ocupacional a través del Observatorio Laboral, que ofrece datos actualizados sobre el 
	            comportamiento del mercado laboral, con insumos de la Encuesta Nacional de Ocupación y Empleo (ENOE), muy 
	            útiles para quienes están por decidir su futuro profesional.</p>
	          </div>
	        </div>
	
	        <div class="panel panel-transparent">
	          <div class="panel-heading">
	            <h3 class="panel-title">
	              <a href="<c:url value="/jsp/empleo/servicioNacionalDeEmpleo/programasApoyoEmpleo.jsp"/>">Programas de Apoyo al Empleo (PAE)</a>
	            </h3>
	          </div>
	          <div class="panel-body">
	            <p>Los Programas de Apoyo al Empleo (PAE) otorgan ayuda económica y cursos de adiestramiento, con el 
	            objetivo de que los buscadores de empleo mejoren sus conocimientos y habilidades para el trabajo o, en caso 
	            de no encontrar empleos acordes con su experiencia, inicien una actividad laboral por cuenta propia. </p>
	          </div>
	        </div>
	        <div class="panel panel-grey">
	          <div class="panel-heading">
	            <h3 class="panel-title">
	              <a href="<c:url value="/jsp/empleo/servicioNacionalDeEmpleo/movilidadLaboralExterna.jsp"/>">Movilidad laboral</a>
	            </h3>
	          </div>
	          <div class="panel-body">
	            <p>El programa de movilidad laboral amplía las oportunidades de empleo para que las personas puedan 
	            trasladarse de forma temporal o permanente, a un lugar distinto al de su residencia, en caso de no 
	            encontrar ofertas de empleo cerca de su localidad. De esta manera, se logra vincular a oferentes y 
	            demandantes de empleo que se encuentran físicamente en regiones distintas.</p>
	          </div>
	        </div>
	
	        <!--div class="panel panel-transparent">
	          <div class="panel-heading">
	            <h3 class="panel-title">
	              <a href="#">Contenido pendiente</a>
	            </h3>
	          </div>
	          <div class="panel-body">
	            Contenido pendiente
	          </div>
	        </div>
	        <div class="panel panel-grey">
	          <div class="panel-heading">
	            <h3 class="panel-title">
	              <a href="#">Contenido pendiente</a>
	            </h3>
	          </div>
	          <div class="panel-body">
	            Contenido pendiente
	          </div>
	        </div>
	
	        <div class="panel panel-transparent">
	          <div class="panel-heading">
	            <h3 class="panel-title">
	              <a href="#">Contenido pendiente</a>
	            </h3>
	          </div>
	          <div class="panel-body">
	            Contenido pendiente
	          </div>
	        </div>
	        <div class="panel panel-grey">
	          <div class="panel-heading">
	            <h3 class="panel-title">
	              <a href="#">Contenido pendiente</a>
	            </h3>
	          </div>
	          <div class="panel-body">
	            Contenido pendiente
	          </div>
	        </div>
	
	        <div class="panel panel-transparent">
	          <div class="panel-heading">
	            <h3 class="panel-title">
	              <a href="#">Contenido pendiente</a>
	            </h3>
	          </div>
	          <div class="panel-body">
	            Contenido pendiente
	          </div>
	        </div-->
	
	        
	      </div>
	      <!-- /div contenido -->
	    </div>
	    
	    
	    
	    
	</jsp:body>
</t:publicTemplate>
