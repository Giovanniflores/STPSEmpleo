<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="url" value="${pageContext.request.requestURI}" />


<t:publicTemplate>
	<jsp:attribute name="titulo">Introducci&oacute;n a la b&uacute;squeda de empleo</jsp:attribute>
    <jsp:attribute name="palabraDescripcion">Candidatos</jsp:attribute>  
    <jsp:attribute name="tituloSitio">Portal del Empleo</jsp:attribute>
 	<jsp:attribute name="urlRedSocial"><c:url value="${url}"/></jsp:attribute>
	<jsp:attribute name="descripcionRedSocial">
		El Portal del Empleo te ofrece acceso permanente y gratuito a más de 200 mil vacantes en todos los sectores y a lo largo y ancho del país.
	</jsp:attribute>
	<jsp:body>
	
	<!-- div ruta_navegacion -->
    <div class="row">
      <div class="col-sm-12">
        <ol class="breadcrumb">
          <li>Ruta de navegación:</li>
           <li><a href="<c:url value="/inicio.do"/>">Inicio</a></li>
          <li><a href="<c:url value="/jsp/empleo/candidatos/candidatos.jsp"/>">Candidatos</a></li>
          <li class="active">Introducción a la búsqueda de empleo</li>
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
								src="${context}/css/images/contenido/introduccion.png"
								class="img-responsive">
          </div>
          <div class="col-md-6">
            <h2>Introducción a la búsqueda de empleo</h2>
            <p>El Portal del Empleo te ofrece acceso permanente y gratuito a más de 200 mil vacantes en todos los sectores y a lo largo y ancho del país.</p>
          </div>
        </div>
        </div>

        <div class="clearfix"></div>
        
        <div class="panel">
          <div class="panel-body">

	        <p>Buscar trabajo no es fácil, y no lo es porque algunas veces el número de vacantes es menor al de personas que se encuentran buscando un empleo; y en otras ocasiones, algunos sectores productivos no hallan el personal que requieren para sus procesos productivos, pues no encuentran a las personas que posean las habilidades idóneas.</p>
	        <p>La búsqueda de empleo requiere dedicación, esfuerzo y constancia, al mismo tiempo implica la utilización de métodos que sean adecuados para lograr el objetivo, encontrar un empleo.</p>
	        <p>Es probable que algunas veces, tu búsqueda de empleo no la realices a través de los canales adecuados, para poderlos seleccionar es necesario que los conozcas, que establezcas cuáles son tus intereses, identifiques que conocimientos y habilidades posees, que características buscan las empresas en sus probables miembros y que definas tu objetivo laboral.</p>
	        <p>También es conveniente que comprendas que la búsqueda de empleo no depende exclusivamente de <strong>factores personales</strong> tales como: la motivación que te lleva a su búsqueda, la planeación que realices para lograr conseguir un empleo, las habilidades que posees, etc., sino que también depende de <strong>factores externos</strong> que se expresan en la dinámica económica de la entidad o localidad en que te interesa trabajar, de los cambios tecnológicos que han adoptado las empresas, de los ciclos productivos, de las políticas de recursos humanos de las empresas, entre otros, sobre los que difícilmente podrás influir.</p>
	        <p>Esto quiere decir que la búsqueda de empleo te enfrenta a una situación en la que hay factores sobre los que no puedes incidir y otros que dependen directamente de ti. Los factores sobre los que puedes influir, los puedes revisar en el apartado de <strong>Habilidades para la búsqueda de empleo</strong> que te invitamos a conocer aquí en dos grandes pasos: <strong>Conoce</strong> y <strong>Postula.</strong>
						</p>

	       </div>
	     </div>
       
      </div>



    </div>
	</jsp:body>
</t:publicTemplate>
