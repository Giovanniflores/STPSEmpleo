<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.Calendar"%>
<%@ page import="mx.gob.stps.portal.web.infra.utils.PropertiesLoader"%>
<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
	String context = request.getContextPath() + "/";
String existenOfertasActivas = "No";
if (null != request.getSession().getAttribute("existenOfertasActivas"))  
	   existenOfertasActivas = (String)request.getSession().getAttribute("existenOfertasActivas"); 

%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="es" lang="es">
<head>
<link rel="P3Pv1" href="https://www.empleo.gob.mx/w3c/p3p.xml" />
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />

<meta http-equiv="X-UA-Compatible" content="IE=8" />

<title><tiles:getAsString name="title" /></title>

<link href='https://fonts.googleapis.com/css?family=Droid+Sans'
	rel='stylesheet' type='text/css' />

<style type="text/css">
@import "dojotoolkit/dojo/resources/dojo.css";

@import "dojotoolkit/dijit/themes/claro/claro.css";

@import "${PATH_CSS_SWB}css_perfil_empresa/estilos_espacioEmpresa.css";

.claro .dijitDialogTitleBar {
	background-color: #F47513;
	color: #FFFFFF;
	font-weight: bold;
	text-decoration: none;
}

#menuAdmin {
	width: 930px;
	font-family: Tahoma, Geneva, sans-serif;
	color: #333333;
	font-size: 11px;
	margin: auto
}

#menuAdmin ul {
	list-style: none;
	margin-left: 2%;
	margin-right: 2%;
	margin-top: 24px;
}

#menuAdmin ul li {
	float: left;
	margin-left: 5px;
	height: 36px;
	margin-bottom: 10px;
}

#menuAdmin ul li a {
	padding: 6px;
	width: auto;
	text-decoration: none;
	color: #333333;
	border: 1px solid #dbdbdb;
	text-align: center;
	background: #FFFFFF );
}

#menuAdmin ul li a.adminCerrar {
	background: #fe6a08;
	color: #ffffff
}

#menuAdmin ul li a.adminCerrar:hover {
	background: #dc5700
}

#menuAdmin ul li a:hover {
	background: #f2f2f2;
}

#menuAdmin ul li a:active {
	background: #f2f2f2;
}
</style>

<script src="dojotoolkit/dojo/dojo.js"
	djConfig="parseOnLoad: true, locale:'es'"></script>

<%
	String urliFrameSWB = PropertiesLoader.getInstance().getProperty(
			"miespacio.iframeswb.script");
	int height = 0;

	String action = (String) session.getAttribute("ACTION_REQUESTED");
	if (action != null) {
		if ("miEspacioEmpresas".equalsIgnoreCase(action)) {
			height = 1010;
		} else if ("edicionEmpresa".equalsIgnoreCase(action)) {
			height = 2300;
		} else if ("OfertaNavegacion".equalsIgnoreCase(action)) {
			height = 2200;
		} else if ("buscarcandidatos".equalsIgnoreCase(action)) {
			height = 1900;
		} else if ("comregpartnercompany".equalsIgnoreCase(action)) {
			height = 2200;
		} else if ("comregcontact".equalsIgnoreCase(action)) {
			height = 1900;
		} else if ("comregpartnercompanyv2".equalsIgnoreCase(action)) {
			height = 2300;
		} else if ("comregcontactv2".equalsIgnoreCase(action)) {
			height = 2000;
		} else if ("ofertaRegistro".equalsIgnoreCase(action)) {
			height = 2300;
		} else if ("ofertaEdicion".equalsIgnoreCase(action)) {
			height = 2300;
		} else if ("ofertaEdicionExperiencia".equalsIgnoreCase(action)) {
			height = 2000;
		} else if ("ofertaEdicionRequisitos".equalsIgnoreCase(action)) {
			height = 1200;
		} else if ("ofertaPlantilla".equalsIgnoreCase(action)) {
			height = 2800;
		} else if ("viewCandidateInfo".equalsIgnoreCase(action)) {
			height = 2800;
		} else if ("RecuperaOfertas".equalsIgnoreCase(action)) {
			height = 2000;
		} else if ("ofertaEmpleo".equalsIgnoreCase(action)) {
			height = 4000;
		}
	}
%>

<script type="text/javascript">
	//Desplegar ventana para modificar logotipo
	function openLogoWindow(){	
	 	var newLogoWin = window.open('<c:url value="/uploadcompanylogo.do?method=init"/>', "CambiarImagen","height=400,width=400,resizable=yes,scrollbars=yes"); 	
	} 	

	function updateIframeHeight() {
		var iframe = document.getElementById('hiddenIframe');
		var newHeight = parseInt(document.body.offsetHeight,10) + 10;
		iframe.src = '<%=urliFrameSWB%>?he
	ight=' + <%=height%>;
	}

	function validaOfertasActivas() {
		<%
		if(existenOfertasActivas.equalsIgnoreCase("Si")){
			%>
			document.location.href = '/buscarcandidatos.do?method=init';
			<%
		} else {
			%>
			alert('Debe existir por lo menos una oferta de empleo activa, para ingresar a esta opción.');
			<%
		}
		%>
	}

	function validaOfertasActivasEspecifica() {
		<%
		if(existenOfertasActivas.equalsIgnoreCase("Si")){
			%>
			document.location.href = '/busquedaEspecificaCandidatos.do?method=init';
			<%
		} else {
			%>
			alert('Debe existir por lo menos una oferta de empleo activa, para ingresar a esta opción.');
			<%
		}
		%>		
	}
	</script>

<!-- Nuevo código analytics multidominio 01/06/2017 -->
<script> 
	(function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
	(i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
	m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
	})(window,document,'script','//www.google-analytics.com/analytics.js','ga');
    ga('create', 'UA-26166631-1', 'auto', {'allowLinker': true});
	ga('require', 'displayfeatures', 'linker');
	ga('linker:autoLink', ['publicaciones.empleo.gob.mx'] );
	ga('send', 'pageview');
</script>

</head>

<body class="claro">

	<c:if test="${empty TAB}">
		<c:set var="classCurrentMiEspacio" value="tab_current" />
		<c:set var="classCurrentMisDatos" value="" />
		<c:set var="classCurrentMisOfertas" value="" />
		<c:set var="classCurrentCandidatos" value="" />
		<c:set var="idDivAppHolder" value="appHolder1" />
	</c:if>
	<c:if test="${not empty TAB and TAB eq 'TAB_MI_ESPACIO'}">
		<c:set var="classCurrentMiEspacio" value="tab_current" />
		<c:set var="classCurrentMisDatos" value="" />
		<c:set var="classCurrentMisOfertas" value="" />
		<c:set var="classCurrentCandidatos" value="" />
		<c:set var="idDivAppHolder" value="appHolder1" />
	</c:if>
	<c:if test="${not empty TAB and TAB eq 'TAB_MIS_DATOS'}">
		<c:set var="classCurrentMiEspacio" value="" />
		<c:set var="classCurrentMisDatos" value="tab_current" />
		<c:set var="classCurrentMisOfertas" value="" />
		<c:set var="classCurrentCandidatos" value="" />
		<c:set var="idDivAppHolder" value="appHolder2" />
	</c:if>
	<c:if test="${not empty TAB and TAB eq 'TAB_MIS_OFERTAS'}">
		<c:set var="classCurrentMiEspacio" value="" />
		<c:set var="classCurrentMisDatos" value="" />
		<c:set var="classCurrentMisOfertas" value="tab_current" />
		<c:set var="classCurrentCandidatos" value="" />
		<c:set var="idDivAppHolder" value="appHolder3" />
	</c:if>
	<c:if test="${not empty TAB and TAB eq 'TAB_MIS_CANDIDATOS'}">
		<c:set var="classCurrentMiEspacio" value="" />
		<c:set var="classCurrentMisDatos" value="" />
		<c:set var="classCurrentMisOfertas" value="" />
		<c:set var="classCurrentCandidatos" value="tab_current" />
		<c:set var="idDivAppHolder" value="appHolder4" />
	</c:if>


	<div class="app_wrapper">
		<div id="datos_usuario">

			<c:if test="${not empty USUARIO_APP && USUARIO_APP.empresa}">
				<%--
	    <div class="holder_cerrar">
	         <a id="cerrar_sesion" href="${context}logout.do">Cerrar sesión</a>
	    </div>
	    --%>
				<div class="holder_foto">
					<img
						src="imageAction.do?method=getLogotipoEmpresa&<%=("captcha?time=" + Calendar.getInstance()
						.getTimeInMillis())%>" alt="foto del usuario"/>
					<p>
						<a href="<c:url value="/uploadcompanylogo.do?method=init"/>">Editar</a>
					</p>
				</div>

				<div class="datos_usuario" lang="es">
					<h2 class="nombre" id="nombre" lang="es">${empresaheader.nombreEmpresa}</h2>
					<ul>
						<li>Representante: ${empresaheader.contactoEmpresa}</li>
						<li>ID del Portal del Empleo: ${empresaheader.idPortalEmpleo}</li>
						<li>${empresaheader.tipoPersona}</li>
						<c:if test="${empresaheader.idTipoPersona eq 1}">
							<li>Fecha de nacimiento: ${empresafechaheader}</li>
						</c:if>
						<c:if test="${empresaheader.idTipoPersona eq 2}">
							<li>Fecha de acta constitutiva: ${empresafechaheader}</li>
						</c:if>
					</ul>
				</div>

				<div class="perfil_call">
					<p>
						Siempre podrás <a href="<c:url value="/edicionEmpresa.do?method=init"/>"
							class="c_naranja">actualizar los datos</a> de tu empresa
					</p>
					<p style="text-align: center;">
						<img src="images/ico_misDatos.gif" />
					</p>
				</div>

				<br clear="all" />

				<ul class="nav_espacioCandidatos" lang="es">
					<li class="tab_miEspacio ${classCurrentMiEspacio}"><a
						href="<c:url value="/miEspacioEmpresas.do"/>">Mi espacio</a></li>
					<li class="tab_misDatos ${classCurrentMisDatos}"><a
						href="<c:url value="/edicionEmpresa.do?method=init"/>">Mis datos</a></li>
					<li class="tab_misOfertasEmpleo ${classCurrentMisOfertas}"><a
						href="<c:url value="/OfertaNavegacion.do?method=init"/>">Mis ofertas
							de empleo</a></li>
					<%-- 	        <li class="tab_buscadorCandidatos ${classCurrentCandidatos}"> --%>
					<!-- 	        	<a href="javascript:validaOfertasActivas()">Buscador de candidatos</a> -->
					<!-- 	        </li> -->
				</ul>
			</c:if>

			<br clear="all" />
		</div>

		<c:if
			test="${not empty USUARIO_APP && (USUARIO_APP.administrador || USUARIO_APP.publicador)}">
			<div id="menuAdmin">
				<tiles:insert name="menu" />
			</div>
		</c:if>

		<div class="app_holder" id="${idDivAppHolder}">
			<div class="app">
				<tiles:insert name="body" />
			</div>

			<c:if test="${empty SIN_ARTICULOS}">
				<div id="columna_articulos" class="aside">
					<tiles:insert name="articulos" />
				</div>
			</c:if>


		</div>

	</div>

	<iframe id="hiddenIframe" src="" width="1" height="1" frameborder="0"
		scrolling="no"> Iframes not supported. </iframe>

</body>
</html>
<%
	Calendar start = (Calendar) request.getAttribute("TIME-START");
	if (start != null) {
		Calendar end = Calendar.getInstance();
		long dif = end.getTimeInMillis() - start.getTimeInMillis();
		out.write("<!-- " + dif + " ms -->");
	}
%>