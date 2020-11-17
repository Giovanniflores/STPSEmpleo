<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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

<div class="pleca_sup1">
	<div class="wrap_pleca">
        <div class="bvenida_user">
            Bienvenido: <strong>${candidatoheader.nombre}</strong>
        </div>
        <div class="head_controls">
            <ul>
                <li><a href="<c:url value="miEspacioCandidato.do"/>">Mi espacio</a></li>
                <li><a href="<c:url value="perfil.do?method=init"/>">Mi perfil</a></li>
                <li><a href="${context}misofertas.do?method=init">Mis ofertas laborales</a></li>
            </ul>
            <a class="cerrar_sesion" href="${context}logout.do">Cerrar sesión</a>
        </div>
    </div>
    <div class="clearfix"></div>
</div>
<div class="header_1">
	<h1 class="marca_1 fl"><img src="/css/images/m_empleoGob.png" alt="Portal del empleo. Llama 01 800 841 2020 - www.empleo.gob.mx" /></h1>
	<div class="nav_complement fl">
		<div class="particip_1">
        	<a class="logo_stps" href="http://www.stps.gob.mx">Secretaría del Trabajo y Previsión Social</a>
            <a class="logo_sne" href="<%= context%>/swb/empleo/servicio_nacional_de_empleo_" onclick="window.location.href='http://qa.empleo.gob.mx/es/empleo/home/_aid/i1_1443';return false;">Servicio Nacional de Empleo</a>
            <div class="clearfix"></div>
		</div>
		<div class="nav_complement_1">
			<ul>
				<li><a href="<%= context%>/swb/empleo/mapa_sitio">Mapa de sitio</a></li>
				<li><a href="<c:url value="/miespacionav.do?method=agendaCita"/>">Solicita una cita</a></li>
				<li><a href="<%= context%>/swb/empleo/contacto">Contacto</a></li>
			</ul>
		</div>
	</div>
    <div class="clearfix"></div>
</div>
