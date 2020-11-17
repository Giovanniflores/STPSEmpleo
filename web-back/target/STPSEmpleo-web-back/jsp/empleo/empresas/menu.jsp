<?xml version="1.0" encoding="ISO-8859-1" ?>
      <div class="col-sm-4 col-sm-push-8">
         <div id='cssmenu'>
         <button id="menuToggle" type="button" class="navbar-toggle">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
         	<h1 class="tituloMenu">Secciones de Empresas</h1>
			<ul class="activeMenu menuContent">
			   <li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/empresas/descubreHerramientasEmpresas.jsp")%>">Descubre las herramientas que tenemos para ti</a></li>
			   <li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/empresas/conoceServiciosPortalEmpresas.jsp")%>">Conoce los servicios que el portal tiene para ti</a></li>
			   <li class='has-sub'><a href='#'><span>Mejora el desempeño de tu empresa <span class="caret"/></span></a>
			      <ul>
			         <li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/empresas/mejoraDesempenoEmpresa.jsp")%>"><span class="right-caret"></span>Mejora el desempeño de tu empresa</a></li>
			         <li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/empresas/evaluacionCandidatos.jsp")%>"><span class="right-caret"></span>La evaluación de candidatos</a></li>
			         <li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/empresas/seleccionDePersonal.jsp")%>"><span class="right-caret"></span>La selección de personal</a></li>
			         <li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/empresas/analisisPuestos.jsp")%>"><span class="right-caret"></span>El análisis de puestos</a></li>
			         <li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/empresas/preparacionEntrevista.jsp")%>"><span class="right-caret"></span>Preparación de la entrevista</a></li>
			         <li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/empresas/conoceTusEmpleados.jsp")%>"><span class="right-caret"></span>Conoce a tus empleados</a></li>
			         <li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/empresas/motivaATuPersonal.jsp")%>"><span class="right-caret"></span>Motiva a tu personal</a></li>
			         <li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/empresas/accidentesTrabajo.jsp")%>"><span class="right-caret"></span>Accidentes en el trabajo</a></li>
			      </ul>
			   </li>
			</ul>
		</div>
    </div>
    
    <div class="clearfix visible-xs"></div>