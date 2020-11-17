<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="mx.gob.stps.portal.core.empresa.vo.EmpresaVO"%>
<%
    String contextSWB = (String)application.getAttribute("DOMAIN_PORTAL");
    EmpresaVO empresa = (EmpresaVO)request.getSession().getAttribute("empresaheader");
    if (null != request.getSession().getAttribute("FROM_CIL")){
        contextSWB = (String)application.getAttribute("DOMAIN_PORTAL_CIL");
    }
%>

<script type="text/javascript">
    function employ() {
        document.ocp.searchPlace.value = document.ocp.searchPlace.value;
        document.ocp.searchQ.value = document.ocp.searchTopic.value;
        document.ocp.submit();
    }
</script>

<script type="text/javascript" src="js/login.js"></script>
<!-- GOBmx -->
<link rel="shortcut icon" type="image/x-icon" href="http://www.gob.mx/assets/favicon.ico">
<link href="<%=request.getContextPath()%>/css/cssgobmx/main.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/css/cssgobmx/application.css" rel="stylesheet">
<!-- GOBmx -->
<!-- GOBmx -->
<div class="row">
	<header>
	<nav class="navbar navbar-inverse navbar-fixed-top navbar">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed" data-target="#navbarMainCollapse" data-toggle="collapse">
					<span class="sr-only">Interruptor de Navegación</span>
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
<div class="row">
	<div class="herramientas hidden-xs">
    <div class="col-sm-12">
        <!-- CUALQUIER ROL QUE NO SEA EMPRESA in empresaheader1Resp.jsp-->
        <c:if test="${empty USUARIO_APP || !USUARIO_APP.empresa}">
        		<ul class="nav nav-pills pull-right">
            		<li role="presentation" class="bvenida_user">Reg&iacute;strate como</li>
                    <li role="presentation"><a class="simple_link" href="<c:url value="/registro_candidato.do"/>">Candidato</a></li>
                    <li role="presentation"><span>o</span></li>
                    <li role="presentation"><a class="simple_link" href="<c:url value="/registro_empresa.do"/>">Empresa</a></li>
                    <li role="presentation"><a href="javascript:dialogLoginPopup();" class="cerrar_sesion ac">Iniciar sesi&oacute;n</a></li>
                </ul>
        </c:if>

        <!-- ROL CANDIDATO -->
        <c:if test="${USUARIO_APP.empresa}">
        		<ul class="nav nav-pills pull-right">
	            	<li role="presentation" class="bvenida_user">Bienvenido: <strong><%=empresa.getNombreEmpresa()%></strong></li>
	                <li role="presentation"><a class="btn-herramientas" href="<c:url value="/miEspacioEmpresas.do"/>">Mi espacio</a></li>
	                <li role="presentation"><a class="btn-herramientas" href="<c:url value="/dondePublicar.do?method=init"/>">Crear oferta de empleo</a></li>
	                <li role="presentation"><a class="btn-herramientas" href="<c:url value="/edicionEmpresa.do?method=init"/>>Mis datos</a></li>
	                <li role="presentation"><a class="btn-cerrarSesion" href="${context}logout.do">Cerrar sesión</a></li>
                </ul>
        </c:if>
    </div>
    <div class="clearfix"></div>
    </div>
    
    
    
        <div class="herramientas visible-xs">
			<div class="col-xs-12">
				<div class="dropdown">
					<!-- ROL EMPRESA in empresaheader1Resp.jsp-->
					<c:if test="${USUARIO_APP.empresa}">
						<button class="btn-sesion btn btn-sm btn-block dropdown-toggle"type="button" id="menu1" data-toggle="dropdown">
							Bienvenido: <strong><%=empresa.getNombreEmpresa()%></strong> <span
								class="caret"></span>
						</button>
					</c:if>
					<!-- ROL CANDIDATO -->
					<c:if test="${USUARIO_APP.candidato}">
						<button class="btn-sesion btn btn-sm btn-block dropdown-toggle"type="button" id="menu1" data-toggle="dropdown">
							Bienvenido: <strong>${candidatoheader.nombre}</strong> <span
								class="caret"></span>
						</button>
					</c:if>
					<!-- CUALQUIER ROL QUE NO SEA EMPRESA -->
					<c:if test="${empty USUARIO_APP || !USUARIO_APP.candidato && !USUARIO_APP.empresa}">
						<button class="btn-sesion btn btn-sm btn-block dropdown-toggle"type="button" id="menu1" data-toggle="dropdown">
							<strong>Iniciar sesión</strong> <span
								class="caret"></span>
						</button>
					</c:if>
					
					<ul class="dropdown-menu" role="menu" aria-labelledby="menu1">
						<!-- ROL EMPRESA in empresaheader1Resp.jsp-->
						<c:if test="${USUARIO_APP.empresa}">
							<li class="dropdown-header"><%=empresa.getNombreEmpresa()%></li>
<%-- 							<li><a href="${context}miEspacioEmpresas.do">Mi espacio</a></li> --%>
<%-- 							<li><a href="${context}dondePublicar.do?method=init">Crear oferta de empleo</a></li> --%>
<%-- 							<li><a href="${context}edicionEmpresa.do?method=init">Mis datos</a></li> --%>
						</c:if>
						<!-- ROL CANDIDATO -->
						<c:if test="${USUARIO_APP.candidato}">
							<li class="dropdown-header">${candidatoheader.nombre}</li>
<%-- 							<li><a href="${context}miEspacioCandidato.do">Mi espacio</a></li> --%>
<%-- 							<li><a href="${context}perfil.do?method=init">Mi perfil</a></li> --%>
<%-- 							<li><a href="${context}misofertas.do?method=misPostulaciones">Mis ofertas laborales</a></li> --%>
						</c:if>
						<!-- CUALQUIER ROL QUE NO SEA EMPRESA -->
						<c:if test="${empty USUARIO_APP || !USUARIO_APP.candidato && !USUARIO_APP.empresa}">
<%-- 							<li class="hidden-xs"><a href="${context}registro_candidato.do">Candidato</a></li> --%>
<%-- 							<li class="hidden-xs"><a href="${context}registro_empresa.do">Empresa</a></li> --%>
							<li role="presentation"><a href="javascript:dialogLoginPopup();">Iniciar sesión</a></li>
						</c:if>
							<li class="divider"></li>
							<li><a href="<c:url value="/jsp/empleo/herramientasDelSitio/mapaDeSitio.jsp"/>">Mapa de sitio</a></li>
							<li><a href="<c:url value="/miespacionav.do?method=agendaCita"/>">Solicita una cita</a></li>
							<li><a href="<c:url value="/jsp/empleo/herramientasDelSitio/contacto.jsp"/>">Contacto</a></li>
						<!-- ROL EMPRESA -->
						<c:if test="${USUARIO_APP.empresa}">	
							<li class="divider"></li>
							<li><a href="<%=request.getContextPath()%>/logout.do">Cerrar sesión</a></li>
						</c:if>
						<!-- ROL CANDIDATO -->
						<c:if test="${USUARIO_APP.candidato}">	
							<li class="divider"></li>
							<li><a href="<%=request.getContextPath()%>/logout.do">Cerrar sesión</a></li>
						</c:if>
						
					</ul>
				</div>
			</div>
        </div>
    
    
    
</div>
<div class="row">
	<div class="col-sm-7">		
	</div>
	<div class="col-sm-5 hidden-xs">		
		<div class="clearfix"></div>
		<div class="text-center nav_rapido">
			<div class="col-xs-4">  
				 <%-- <a href="<%=contextSWB %>/swb/empleo/mapa_sitio">Mapa de sitio</a>  --%>
			</div>
			<div class="col-xs-4">
				 <a href="<%=contextSWB %>/swb/empleo/contacto">Contacto</a> 
			</div>
			<div class="col-xs-4 solicitaCita">
            	<img src="<%=request.getContextPath()%>/css/images/bg_agendaCita2.png" alt="">
	            <a href="<%=response.encodeURL(request.getContextPath()+"/miespacionav.do?method=agendaCita")%>">&nbsp;Solicita una cita</a>
            </div>
		</div>
	</div> 
</div>
