<%@ page import="mx.gob.stps.portal.web.security.vo.UsuarioWebVO"%>
<%@ page import="mx.gob.stps.portal.web.infra.utils.Constantes"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String context = (String)application.getAttribute("DOMAIN_PORTAL");
	String contextApp = request.getContextPath();
	String contextSWB = (String)application.getAttribute("DOMAIN_PORTAL");
	UsuarioWebVO usuario = (UsuarioWebVO) session.getAttribute(Constantes.USUARIO_APP);
	if (null != request.getSession().getAttribute("FROM_CIL")){
		contextSWB = (String)application.getAttribute("DOMAIN_PORTAL_CIL");
		context = (String)application.getAttribute("DOMAIN_PORTAL_CIL");
	}
%>

<script type="text/javascript">
	var djConfig = {
		parseOnLoad : true,
		isDebug : false
	};

</script>
<script type="text/javascript">
	function employ() {
		document.ocp.searchPlace.value = document.ocp.searchPlace.value;
		document.ocp.searchQ.value = document.ocp.searchTopic.value;
		document.ocp.submit();
	}

</script>
<!-- GOBmx -->
<link rel="shortcut icon" type="image/x-icon" href="http://www.gob.mx/assets/favicon.ico">
<link href="<%=request.getContextPath()%>/css/cssgobmx/main.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/css/cssgobmx/application.css" rel="stylesheet">
<!-- GOBmx -->
<!-- GOBmx -->
<div class="row">
	<header>
	<nav class="navbar navbar-inverse navbar-fixed-top navbar">
		<div class="wrapper">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed" data-target="#navbarMainCollapse" data-toggle="collapse">
					<span class="sr-only">Interruptor de Navegaci&oacute;n</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
				 <a class="gobmx" title="Ir a gob.mx" target="_blank" style="padding-left: 10px;" href="https://www.gob.mx/">
                <img src="<%=request.getContextPath()%>/css/cssgobmx/gobmxlogo.svg" alt="gob.mx"></a>
			</div>
			<div id="navbarMainCollapse" class="collapse navbar-collapse">
				<ul class="nav navbar-nav navbar-right">
					<li><a href="https://www.gob.mx/tramites" class="" target="_blank" title="Trámites">Tr&aacute;mites</a></li>
					<li><a href="https://www.gob.mx/gobierno" class="" target="_blank" title="Gobierno">Gobierno</a></li>
					<li><a href="https://www.gob.mx/participa" class="" target="_blank" title="Participación Ciudadana">Participa</a></li>
					<li><a href="http://datos.gob.mx/" target="_blank" title="Datos abiertos">Datos</a></li>
					<li><a href="https://www.gob.mx/busqueda?utf8=%E2%9C%93" target="_blank" title="Datos abiertos">
						<img alt="Búsqueda" width="20" height="20" class="optical-adjust-search" src="<%=request.getContextPath()%>/css/cssgobmx/search.svg">
					</a></li>
				</ul>
			</div>
		</div>
	</nav>
</header>
	</div>
<!-- GOBmx -->
<!-- div header JGLC-->
	<div class="row">
      <div class="col-sm-7">
        <%-- <div class="header">
         <a href="<%=context%>">
          <img src="<%=request.getContextPath()%>/css/images/m_empleoGob.png"
			alt="Portal del Empleo : llama al 01 800 841 20 20"
			class="img-responsive">
		 </a>
        </div> --%>
      </div>

      <div class="col-sm-5 hidden-xs">
          <%-- <div class="col-xs-6 logoSTPS">
        	<a href="http://www.stps.gob.mx">
	          <img src="<%=request.getContextPath()%>/css/images/stps_logo.png"
	            alt="Secretaría del Trabajo y Previsión Social"
	            class="img-responsive">
        	</a>
      	  </div> --%>
	     <%--  <div class="col-xs-6 logoSNE">
	        <a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/servicioNacionalDeEmpleo/servicioNacionalDeEmpleo.jsp")%>" 
	        	onclick="window.location.href='<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/servicioNacionalDeEmpleo/servicioNacionalDeEmpleo.jsp")%>';return false;">
		          <img src="<%=request.getContextPath()%>/css/images/sne_logo.png"
		            alt="Servicio Nacional de Empleo"
		            class="img-responsive">
	         </a>
	      </div> --%>
	      
      	  <div class="clearfix"></div>
        
          <div class="text-center nav_rapido">
          
          <!--  TK2806-->
          
         	 
           <div class="col-xs-3">
              <%-- <a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/herramientasDelSitio/mapaDeSitio.jsp")%>">Mapa de sitio</a> --%>
            </div>
             <div class="col-xs-2">
               <a href="<c:url value="/jsp/empleo/herramientasDelSitio/contacto.jsp"/>">Contacto</a> 
            </div>
            <div class="col-xs-3">
            	<a href="javascript:acquireIO.max()">Ayuda En vivo</a>            
             </div>
            <div class="col-xs-4 solicitaCita">
            	<img src="<%=request.getContextPath()%>/css/images/bg_agendaCita2.png" alt="">
	            <a href="<c:url value="/miespacionav.do?method=agendaCita"/>">&nbsp;Solicita una cita</a>
            </div>
               
            
      	  </div>
       </div>
	</div>
<!-- /div header -->

<!-- div nav-black -->
<%-- <div class="row">
	<div class="col-sm-12">
		<div class="btn-group btn-group-justified nav-black">
			<a href="<c:url value="/jsp/empleo/candidatos/candidatos.jsp"/>" class="btn btn-candidato">Candidatos</a> 
			<a href="<c:url value="/jsp/empleo/empresas/empresas.jsp"/>" class="btn btn-empresa">Empresas</a>
			<a href="<c:url value="/jsp/empleo/servicioNacionalDeEmpleo/servicioNacionalDeEmpleo.jsp"/>" class="btn btn-SNE">Servicio Nacional de Empleo</a>
		</div>
	</div>
</div> --%>
<!-- /div nav-black -->
