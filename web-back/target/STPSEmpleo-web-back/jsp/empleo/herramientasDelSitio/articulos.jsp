<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="url" value="${pageContext.request.requestURI}" />

<t:publicTemplate>
	<jsp:attribute name="titulo">Art&iacute;culos de inter&eacute;s para ti</jsp:attribute>
    <jsp:attribute name="palabraDescripcion">Herramientas del sitio</jsp:attribute>  
    <jsp:attribute name="tituloSitio">Portal del Empleo</jsp:attribute>
 	<jsp:attribute name="urlRedSocial"><c:url value="${url}"/></jsp:attribute>
	<jsp:attribute name="descripcionRedSocial">
		Consulta artículos de diversas fuentes que te orientarán en la búsqueda de empleo.
	</jsp:attribute>
	<jsp:body>
	
	<!-- div ruta_navegacion -->
    <div class="row">
      <div class="col-sm-12">
        <ol class="breadcrumb">
          <li>Ruta de navegación:</li>
<%--          <li> <a href="<c:url value="/inicio.do"/>">Inicio</a></li> --%>
          <li><a href="<c:url value="/inicio.do"/>">Inicio</a></li>
<%-- <li><a href="<c:url value="/jsp/empleo/herramientasDelSitio/mapaDeSitio.jsp"/>">Herramientas del sitio</a></li> --%>
          <li><a href="<c:url value="/jsp/empleo/herramientasDelSitio/mapaDeSitio.jsp"/>">Herramientas del sitio</a></li>
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
      
			<div class="page-header">
				<h2 class="titulosh2">Artículos de interés para ti</h2>
			</div>
			<p>Consulta artículos de diversas fuentes que te orientarán en la búsqueda de empleo.</p>
	      
			<div class="panel-grey">
				<div class="panel-body">
					<div class="col-md-12">
						<div class="page-heading titleTemas">
							<h3 class="titulosh2">Temas de empleo <span>Adultos mayores</span></h3>
						</div>
					</div>
					<div class="col-sm-8">
						<div class="form-group">
							 <label for="temasEmpleo"> Selecciona por</label>
							 <select id="temasEmpleo" name="temasEmpleo" class="form-control">
							 	<option value="">Todos</option>
		                        <option >Actitud</option>
		                        <option >Adultos mayores</option>
		                        <option >Aplicaciones móviles</option>
		                        <option >Bolsas de trabajo</option>
		                        <option >Capacitación</option>
		                        <option >Clima laboral</option>
		                        <option >Consejos</option>
		                        <option >Contratación</option>
		                        <option >Currículum</option>
		                        <option >Empresas</option>
		                        <option >Entrevista</option>
		                        <option >Especialidades</option>
		                        <option >Estadísticas</option>
		                        <option >Idiomas</option>
		                        <option >Indicadores</option>
		                        <option >Internet</option>
		                        <option >Jóvenes</option>
		                        <option >Lenguaje corporal</option>
		                        <option >Mujeres</option>
		                        <option >Perfiles</option>
		                        <option >Personas con discapacidad</option>
		                        <option >Prestaciones</option>
		                        <option >Reclutador</option>
		                        <option >Redes sociales</option>
		                        <option >Tecnología</option>
		                        <option >Vacantes</option>
		                        <option >Vestimenta</option>
		                        </select>
						</div>
					</div>
					<div class="col-sm-4">
						<div class="form-group">
							 <span class="labelHidden hidden-xs"></span>
							 <button type="button" class="btn btnForm form-control">Buscar</button>
						</div>
					</div>
				</div>
			</div>
	
	        <div class="clearfix"></div>
        
		
	        
	        <div class="panel">
	          <div class="panel-heading">
	            <h4 class="panel-title">Cuatro formas de venderte a un reclutador si ya has fracasado muchas veces</h3>
	          </div>
	          <div class="panel-body">
	          	<p><b>Fuente:</b> <span>Expansión</span></p>
	          	<p><span title="Fecha" class="glyphicon glyphicon-calendar"></span> 14-oct-2015</p>
	            <p>Los métodos de los concursos de talento ('La Voz', por ejemplo) pueden servir para fortalecer tu discurso. Te decimos cómo. <a href="descripcionArticulo.jsp">Leer más</a></p>
	          </div>
	        </div>
	        
	        <div class="panel panel-articulos">
	          <div class="panel-heading">
	            <h4 class="panel-title">Lo que debes saber sobre las prestaciones laborales</h3>
	          </div>
	          <div class="panel-body">
	          	<p><b>Fuente:</b> <span>OCC Mundial</span></p>
	          	<p><span title="Fecha" class="glyphicon glyphicon-calendar"></span> 13-oct-2015</p>
	            <p>Las prestaciones laborales son tan importantes como tu salario ¿las conoces todas? <a href="#">Leer más</a></p>
	          </div>
	        </div>
	        
	         <div class="panel">
	          <div class="panel-heading">
	            <h4 class="panel-title">Cuatro formas de venderte a un reclutador si ya has fracasado muchas veces</h3>
	          </div>
	          <div class="panel-body">
	          	<p><b>Fuente:</b> <span>Expansión</span></p>
	          	<p><span title="Fecha" class="glyphicon glyphicon-calendar"></span> 14-oct-2015</p>
	            <p>Los métodos de los concursos de talento ('La Voz', por ejemplo) pueden servir para fortalecer tu discurso. Te decimos cómo. <a href="#">Leer más</a></p>
	          </div>
	        </div>
	        
	        <div class="panel panel-articulos">
	          <div class="panel-heading">
	            <h4 class="panel-title">Lo que debes saber sobre las prestaciones laborales</h3>
	          </div>
	          <div class="panel-body">
	          	<p><b>Fuente:</b> <span>OCC Mundial</span></p>
	          	<p><span title="Fecha" class="glyphicon glyphicon-calendar"></span> 13-oct-2015</p>
	            <p>Las prestaciones laborales son tan importantes como tu salario ¿las conoces todas? <a href="#">Leer más</a></p>
	          </div>
	        </div>
	        
	        <div class="panel panel-paginador">
	          <div class="panel-body text-center">
	          	<p><strong>Mostrando 71 - 80 de 426</strong></p>
	          	<ul>
					<li><a href="#" class="prev">Anterior</a></li>
					<li><span class="current">1</span></li>
					<li><a href="" class="pagina">2</a></li>
	                <li><a href="" class="pagina">3</a></li>
					<li><a href="" class="pagina">4</a></li>
					<li><a href="" class="pagina">5</a></li>
					<li><a href="" class="pagina">6</a></li>
	                <li><a href="" class="pagina">7</a></li>
	                <li><a href="" class="pagina">8</a></li>
	                <li><a href="" class="pagina">9</a></li>
	                <li><a href="" class="pagina">10</a></li>				
		
					<!-- total de páginas -->
					<li><span class="noPags"> de 25 Páginas</span></li>
					<!-- liga para saltar al bloque posterior -->
					<li><a href="" class="next sig">Siguiente</a></li>
				</ul>
	          </div>
	          
	          <div class="form-inline text-center">
	          	<div class="form-group">
				    <label for="irPagina">Ir a página</label>
				    <div class="input-group">
					    <input id="irPagina" type="text" class="form-control input-sm">
					    <span class="input-group-btn">
					    	<button type="button" class="btn btnForm btn-sm">Ir</button>
					    </span>
					</div>
				</div>
			  </div>
			  
	        </div>
	        
        </div>
        
      </div><!-- /div contenido -->
    </div>
    
	</jsp:body>
</t:publicTemplate>
