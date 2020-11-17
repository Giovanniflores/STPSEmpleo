<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
    String contextSWB = (String)application.getAttribute("DOMAIN_PORTAL");
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

	function showCerrarSesionPPC() {
		var idTag = "dialogCerrarSesionPPC";
		dijit.byId(idTag).show();
	}

	function hideCerrarSesionPPC() {
		var idTag = "dialogCerrarSesionPPC";
		dijit.byId(idTag).hide();
	}

	function cerrarSesion() {
		var sessionValue = $("#hdnSession").data('value');

		alert(sessionValue);
		if (request.Querystring["action"] != null) {
			response.clear();
			Session.abondon();
			response.write("Success");
			response.End();
		}
	}
</script>
<!-- TK2806  -->
<script src="<%=request.getContextPath()%>/js/co.browsing.js"></script>
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

<input type="hidden" id="hdnSession"
	data-value="@Request.RequestContext.HttpContext.Session[" UserName"]" />
<script type="text/javascript" src="js/login.js"></script>




<!-- CUALQUIER ROL QUE NO SEA CANDIDATO in candidatoHeader1.jsp-->
<c:if
	test="${empty USUARIO_APP || !USUARIO_APP.candidato && !USUARIO_APP.empresa}">
	<div class="bvenida_user" style="height: auto;">
		<p>
			Regístrate como <a href="<c:url value="/registro_candidato.do"/>"
				class="candidato">Candidato</a><span> o </span> <a
				href="<c:url value="/registro_empresa.do"/>" class="empresa">Empresa</a>
			<a href="javascript:dialogLoginPopup();" class="iniciaSesion_Int">Iniciar
				sesión</a>
		</p>
	</div>
</c:if>

<!-- ROL EMPRESA -->
<c:if test="${USUARIO_APP.empresa}">
	<div class="bvenida_user" style="height: auto">
		Bienvenido: <strong>${empresaheader.nombre}</strong>
		<!--TODO: Fix this-->
	</div>
	<div class="head_controls">
		<ul>
			<li><a href="<c:url value="/miEspacioEmpresas.do"/>">Mi
					espacio</a></li>
			<li><a href="<c:url value="/dondePublicar.do?method=init"/>">Crear
					oferta de empleo</a></li>
			<li><a href="<c:url value="/registro-unico.do?method=redirectEditaEmpresaRU"/>">Mis
					datos</a></li>
		</ul>
		<a class="cerrar_sesion" href="${context}logout.do">Cerrar sesión</a>
	</div>
</c:if>

<!-- ROL CANDIDATO -->
<c:if test="${USUARIO_APP.candidato}">
	<div class="bvenida_user" style="height: auto; padding: 0;">
		<c:if test="${FROM_CIL}">
			<img
				src="<%=response.encodeURL(request.getContextPath())%>/css/cil/logo_cil.gif"
				alt="Centros de Intermediaci&oacute;n Laboral" />
		</c:if>
		<div style="padding: 10px; text-align: center; float: right;">
			Bienvenido: <strong>${candidatoheader.nombre}</strong>
		</div>
	</div>
	<div class="clearfix"></div>
	<div class="pleca_sup1">
		<div class="wrap_pleca">
			<div class="head_controls">
				<ul>
					<c:if test="${FROM_CIL}">
						<li><a
							href="<%=response.encodeURL(request.getContextPath() +"/servicioscil.do?method=init")%>">Registro
								de Servicios</a></li>
						<li><a
							href="<%=response.encodeURL(request.getContextPath() +"/seguimientocil.do?method=init")%>">Seguimiento
								Servicios</a></li>
					</c:if>
					<c:if test="${!FROM_CIL}">
						<li><a
							href="<%=response.encodeURL(request.getContextPath() +"/miEspacioCandidato.do")%>">Mi
								espacio</a></li>
						<li><a
							href="<%=response.encodeURL(request.getContextPath() +"/registro-unico.do?method=redirectEditaCandidatoRU")%>">Mi
								perfil</a></li>
					</c:if>
					<li><a
						href="<%=response.encodeURL(request.getContextPath() +"/misofertas.do?method=misPostulaciones")%>">Mis
							ofertas laborales</a></li>
				</ul>
				<!--INI_JGLC_PPC-->
				<!--<a class="cerrar_sesion" href="#" onclick="showCerrarSesionPPC()"> Cerrar sesión</a> -->
				<!--FIN_JGLC_PPC-->
				<a class="cerrar_sesion" href="${context}logout.do">Cerrar
					sesión</a>
			</div>
		</div>
	</div>
</c:if>
<div class="clearfix"></div>
<!-- div header-->
 <div class="header_1"><!--
	<h1 class="marca_1 fl">
		<a
			 href="<%=response.encodeUrl(request.getContextPath()+"/inicio.do")%>">
			<img src="${context}css/images/m_empleoGob.png"
			alt="Portal del empleo. Llama 01 800 841 2020 - www.empleo.gob.mx" style="
    width: 515px;
" />
		</a>
	</h1> -->
	<div class="nav_complement fl">
		<!-- <div class="particip_1">
			<a class="logo_stps" href="http://www.stps.gob.mx">Secretaría del
				Trabajo y Previsión Social</a> <a class="logo_sne"
				href="${context}jsp/empleo/servicioNacionalDeEmpleo/servicioNacionalDeEmpleo.jsp"
				onclick="window.location.href='${context}jsp/empleo/servicioNacionalDeEmpleo/servicioNacionalDeEmpleo.jsp';return false;">Servicio
				Nacional de Empleo</a>
			<div class="clearfix"></div>
		</div>-->
		<div class="nav_complement_1">
           
  			<!--<ul>
			<li><a	href="javascript:acquireIO.max()">Ayuda En vivo</a> 
					</li>
				<li><a
					href="<c:url value="/jsp/empleo/herramientasDelSitio/mapaDeSitio.jsp"/>">Mapa
						de sitio</a></li>
				<li><a
					href="<c:url value="/miespacionav.do?method=agendaCita"/>">Solicita
						una cita</a></li>
				<li><a
					href="<c:url value="/jsp/empleo/herramientasDelSitio/contacto.jsp"/>">Contacto</a></li>
			</ul> --> 
			<ul>
            	<li><a	href="javascript:acquireIO.max()">Ayuda En vivo</a></li>                
                <li><a href="<c:url value="/miespacionav.do?method=agendaCita"/>">Solicita una cita</a></li>
                <%-- <li><a href="<c:url value="/jsp/empleo/herramientasDelSitio/mapaDeSitio.jsp"/>">Mapa de sitio</a></li>
                <li><a href="<c:url value="/jsp/empleo/herramientasDelSitio/contacto.jsp"/>">Contacto</a></li> --%>
            </ul> 
		</div>
	</div>
	<div class="clearfix"></div>
</div> 

<!-- /div header -->

<div dojoType="dijit.Dialog" id="dialogCerrarSesionPPC" title="Aviso"
	draggable="false" style="display: none">
	<div class="msg_contain">
		<p id="msgCerrarSesion">Se ha indentificado que estás inscrito al
			Programa de Promoción y Colocación del Seguro de Desempleo (PPC-SD),
			para cumplir con el programa debes postularte al menos a una oferta,
			o indicar el motivo por el cual no te postulas.</p>
		<br />

		<p class="form_nav 2">

			<button id="btnOfertasDeAcuerdoAmiPerfil" class="button">Ofertas
				de acuerdo a mi perfil</button>
			<br /> <br />
			<button id="btnRegistrarMotivoNoPostu" class="button">Registrar
				motivos de no postulación</button>
			<br />
		</p>


	</div>
</div>