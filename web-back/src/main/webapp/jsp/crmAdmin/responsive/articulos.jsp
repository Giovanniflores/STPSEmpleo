<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="logic" uri="http://struts.apache.org/tags-logic" %>
<%@ taglib prefix="bean" uri="http://struts.apache.org/tags-bean" %>
<%@ taglib prefix="s" uri="http://struts.apache.org/tags-logic" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="url" value="${pageContext.request.requestURI}" />

<t:publicTemplate>
	<jsp:attribute name="titulo">
      Artículos de inter&eacute;s para ti
    </jsp:attribute>
    <jsp:attribute name="palabraDescripcion">
    	Artículos de inter&eacute;s para ti
    </jsp:attribute>  
    <jsp:attribute name="tituloSitio">
      	Portal del Empleo
    </jsp:attribute>
 	<jsp:attribute name="urlRedSocial"><c:url value="${url}"/></jsp:attribute>
	<jsp:attribute name="descripcionRedSocial">
		Consulta artículos de diversas fuentes que te orientarán en la búsqueda de empleo.
	</jsp:attribute>
	<jsp:body>
	
	<!-- div ruta_navegacion -->
    <div class="row">
      <div class="col-sm-12">
        <ol class="breadcrumb">
          <li>Ruta de navegaci&oacute;n:</li>
<%--          <li> <a href="${context}/inicio.do">Inicio</a></li> --%>
          <li><a href="#">Inicio</a></li>
<%-- <li><a href="${context}/jsp/empleo/herramientasDelSitio/mapaDeSitio.jsp">Herramientas del sitio</a></li> --%>
          <li><a href="#">Herramientas del sitio</a></li>
          <li class="active">Art&iacute;culos de inter&eacute;s para ti</li>
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
				<h class="panel-body">
					<div class="col-md-12">
                        <c:choose>
                        <c:when test="${crmBuscadorArticulosForm.temasEmpleo != null}">

                        <div class="page-heading titleTemas">
							<h3 class="titulosh2">Temas de empleo <span>${crmBuscadorArticulosForm.temasEmpleo}</span></h3>
						</div>
                        </c:when>
                        <c:otherwise>
                            <div class="page-heading titleTemas">
                                <h3 class="titulosh2">Temas de empleo</h3>
                            </div>
                        </c:otherwise>
                        </c:choose>
					</div>
                    <html:form action="/articuloDeInteress.do" method="post">
					<div class="col-sm-8">
						<div class="form-group">
							 <label for="temasEmpleo"> Selecciona por</label>
							 <select id="temasEmpleo" name="temasEmpleo" class="form-control">
							 	<option value="">Todos</option>
								 <c:forEach  items="${crmBuscadorArticulosForm.listaEtiquetas}" var="etiquetas">
									 <option>${etiquetas.etiqueta}</option>
								 </c:forEach>
							 </select>
                        </div>
					</div>
					<div class="col-sm-4">
						<div class="form-group">
							 <span class="labelHidden hidden-xs"></span>
							 <button type="submit" class="btn btnForm form-control">Buscar</button>
						</div>
					</div>
                    </html:form>
				</div>

	
	        <div class="clearfix"></div>


          <c:forEach items="${crmBuscadorArticulosForm.articulosFormList}" var="articulos" varStatus="counter">
          <c:choose>
          <c:when test="${counter.count == 1}">
          <div class="pagArticulo" >

                  </c:when>
                  <c:when test="${(counter.count-1) % 10 == 0 }">
                      </div>
                      <div class="pagArticulo" >

                   </c:when>
                       </c:choose>

              <c:choose>
                  <c:when test="${counter.count %2 == 0}">
                         <div class="panel panel-articulos">
                  </c:when>
                  <c:otherwise>
                      <div class="panel">
                  </c:otherwise>
              </c:choose>
              <div class="panel-heading">
                    <h4 class="panel-title">${articulos.titulo}</h4>
	          </div>
	          <div class="panel-body">
	          	<p><b>Fuente:</b> <span>${articulos.fuente}</span></p>
	          	<p><span title="Fecha" class="glyphicon glyphicon-calendar"></span>${articulos.fecha}</p>
	            <p>${articulos.articulo}</p>
	          </div>
	        </div>
                      <c:choose>

                      <c:when test="${counter.count == crmBuscadorArticulosForm.totalRegistros}">

                    </div>
                      </c:when>
                      </c:choose>
	        </c:forEach>

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
					<li><span class="noPags"> de ${crmBuscadorArticulosForm.totalPaginas} Páginas</span></li>
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

        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/dojo/1.9.3/dojo/dojo.js" ></script>
        <script type="text/javascript" >
            dojo.require("dijit.dijit");

            dojo.require("dijit.Dialog");
        </script>
        <script type="text/javascript">
            dojo.require("dojo.on");
            dojo.addOnLoad(function() {
                var actual = 1;
                var paginas = dojo.query('.pagArticulo').length;
                var articulos =${crmBuscadorArticulosForm.totalRegistros};
                var grupo = paginas / 10;
                if (paginas % 10 !== 0)
                {
                    grupo++;
                }
                var currentGroup = 0;
                var muestra = function()
                {

                    dojo.query('.pagArticulo').forEach(function(li, index)
                    {
                        var iact = parseInt(actual);
                        if ((index + 1) === iact)
                        {
                            dojo.style(li, 'display', 'block');
                        }
                        else
                        {
                            dojo.style(li, 'display', 'none');
                        }
                    });
                    createPages();

                };
                var click = function(page)
                {
                    actual = page;
                    muestra();
                };


                var createPages = function()
                {
                    dojo.query('.panel-paginador').forEach(function(enode)
                    {
                        dojo.empty(enode);
                        var istartArt = (parseInt(actual) * 10) - 9;
                        var iendArt = istartArt + 9;

                        if (iendArt > articulos)
                        {
                            iendArt = articulos;
                        }

                        var panelbody = dojo.create("p", {'class': 'panel-body text-center', innerHTML: 'Mostrando ' + istartArt + ' - ' + iendArt + ' de ' + articulos + ''}, enode);
                        var ul = dojo.create("ul", null, panelbody);
                        var iactcurrent = parseInt(actual);
                        if (iactcurrent > 1)
                        {

                            var prev = dojo.create("li", {innerHTML: '<a class="prev" href="#" >Anterior</a>'}, ul);

                            dojo.query('a', prev).forEach(function(a)
                            {
                                dojo.on(a, 'click', function(e)
                                {
                                    e.preventDefault();
                                    e.stopPropagation();
                                    var iact = parseInt(actual);
                                    var nextPage = iact - 1;
                                    if (nextPage <= 0)
                                    {
                                        actual = 1;
                                    }
                                    else
                                    {
                                        actual = nextPage;
                                    }
                                    muestra();
                                });

                            });
                        }
                        var iactPag = parseInt(actual);
                        var startPage = iactPag;
                        var endPage = startPage + 9;
                        if (endPage > paginas)
                        {
                            endPage = paginas;
                        }
                        if (actual >= (paginas - 9))
                        {
                            startPage = paginas - 9;
                            if (startPage <= 0)
                            {
                                startPage = 1;
                            }
                            endPage = startPage + 9;
                            if (endPage > paginas)
                            {
                                endPage = paginas;
                            }
                        }
                        for (var i = startPage; i <= endPage; i++)
                        {
                            var iact = parseInt(actual);
                            if (i === iact)
                            {
                                dojo.create("li", {innerHTML: '<span class="current">' + i + '</span>'}, ul);
                            }
                            else
                            {
                                var node = dojo.create("li", {innerHTML: '<a class="pagina" href="#" >' + i + '</a>'}, ul);
                                dojo.query('a', node).forEach(function(a)
                                {
                                    var ipage = a.innerHTML;
                                    dojo.on(a, 'click', function(e)
                                    {
                                        e.preventDefault();
                                        e.stopPropagation();
                                        click(ipage);
                                    });
                                });
                            }
                        }
                        dojo.create("li", {innerHTML: '<span class="noPags"> de ' + paginas + ' Páginas</span>'}, ul);

                        dojo.create('p', {innerHTML: '<label for="numPagina"><strong>Ir a página</strong></label><input type="text" name="numPagina" id="numPagina"><input type="button" id="ir" value="Ir" class="ir">'}, panelbody);

                        dojo.query('#ir', panelbody).forEach(function(input)
                        {
                            dojo.on(input, 'click', function(e)
                            {
                                dojo.query('#numPagina', panelbody).forEach(function(input)
                                {
                                    if (input.value)
                                    {
                                        var pagBudq = parseInt(input.value);
                                        if (!isNaN(pagBudq))
                                        {
                                            if (pagBudq > paginas)
                                            {
                                                pagBudq = paginas;
                                            }
                                            if (pagBudq < 1)
                                            {
                                                pagBudq = 1;
                                            }
                                            actual = pagBudq;
                                            muestra();
                                        }
                                    }
                                });
                            });
                        });
                        if (iactcurrent < paginas)
                        {
                            var next = dojo.create("li", {innerHTML: '<a href="" class="next sig">Siguiente</a>'}, ul);
                            dojo.query('a', next).forEach(function(a)
                            {
                                dojo.on(a, 'click', function(e)
                                {
                                    e.preventDefault();
                                    e.stopPropagation();
                                    var iact = parseInt(actual);
                                    var nextPage = iact + 1;
                                    if (nextPage <= paginas)
                                    {
                                        actual = nextPage;
                                    }
                                    else
                                    {
                                        actual = paginas;
                                    }
                                    muestra();
                                });
                            });
                        }
                    });
                };
                createPages();
                muestra();
                if (paginas <= 1)
                {
                    dojo.query('.panel-paginador').forEach(function(enode)
                    {
                        dojo.style(enode, 'display', 'none');
                    });
                }
                else
                {
                    dojo.query('.panel-paginador').forEach(function(enode)
                    {
                        dojo.style(enode, 'display', 'block');
                    });
                }

            });
        </script>

        <script type="text/javascript">
            window.onload = function() {
                document.crmBuscadorArticulosForm.action = "articuloDeInteress.do";
            }


        </script>
	</jsp:body>
</t:publicTemplate>
