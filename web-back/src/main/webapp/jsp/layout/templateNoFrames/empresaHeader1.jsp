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
<div class="pleca_sup1">
    <div class="wrap_pleca">
        <!-- CUALQUIER ROL QUE NO SEA EMPRESA in empresaHeader.jsp-->
        <c:if test="${empty USUARIO_APP || !USUARIO_APP.empresa && !USUARIO_APP.administrador && !USUARIO_APP.publicador}">
            <div class="bvenida_user">
                Regístrate como
            </div> 
            <div class="head_controls">
                <ul>
                    <li><a class="simple_link" href="<c:url value="/registro_candidato.do"/>">Candidato</a></li>
                    <li><span>o</span></li>
                    <li><a class="simple_link" href="<c:url value="/registro_empresa.do"/>">Empresa</a></li>
                </ul>
                <a href="javascript:dialogLoginPopup();" class="cerrar_sesion ac">Iniciar sesi&oacute;n</a>
            </div>
        </c:if>

        <!-- ROL CANDIDATO in empresaheader1.jsp-->
        <c:if test="${USUARIO_APP.empresa}">
            <div class="bvenida_user">
                Bienvenido: <strong><%=empresa.getNombreEmpresa()%></strong>
            </div>
                <div class="head_controls">
                <ul>
                <li><a href="<c:url value="/miEspacioEmpresas.do"/>">Mi espacio</a></li>
                <li><a href="<c:url value="/dondePublicar.do?method=init"/>">Crear oferta de empleo</a></li>
                <li><a href="<c:url value="/registro-unico.do?method=redirectEditaEmpresaRU"/>">Mis datos</a></li>
                </ul>
                <a class="cerrar_sesion" href="${context}logout.do">Cerrar sesi&oacute;n</a>
            </div>
        </c:if>
    </div>
    <div class="clearfix"></div>
</div>
<div class="header_1">
   <!--   <h1 class="marca_1 fl">    
     <a href="<%=response.encodeUrl(request.getContextPath()+"/inicio.do")%>">
    	<img src="${context}css/images/m_empleoGob.png" alt="Portal del empleo. Llama 01 800 841 2020 - www.empleo.gob.mx"style="
    width: 515px;
" />
     </a>
    </h1>-->
    <div class="nav_complement fl">
        <div class="particip_1">
         <!--    <a class="logo_stps" href="http://www.stps.gob.mx">Secretaría del Trabajo y Previsión Social</a>--> 
       <!--     <a class="logo_sne" href="${context}jsp/empleo/servicioNacionalDeEmpleo/servicioNacionalDeEmpleo.jsp" onclick="window.location.href='${context}jsp/empleo/servicioNacionalDeEmpleo/servicioNacionalDeEmpleo.jsp';return false;">Servicio Nacional de Empleo</a>-->
            <div class="clearfix"></div>
        </div>
        <div class="nav_complement_1">
 <!--       <div class="col-xs-3">
            	<a href="javascript:acquireIO.max()">Ayuda En vivo</a>            
             </div>
              <div class="col-xs-3">
              <a href="<c:url value="/jsp/empleo/herramientasDelSitio/mapaDeSitio.jsp"/>">Mapa de sitio</a>
            </div>
             <div class="col-xs-4 solicitaCita">
   
	            <a href="<c:url value="/miespacionav.do?method=agendaCita"/>">&nbsp;Solicita una cita</a>
            </div> 
            <div class="col-xs-2">
              <a href="<c:url value="/jsp/empleo/herramientasDelSitio/contacto.jsp"/>">Contacto</a>
            </div> --->
              <ul>
            	<li><a	href="javascript:acquireIO.max()">Ayuda En vivo</a></li>                
                <li><a href="<c:url value="/jsp/empleo/herramientasDelSitio/contacto.jsp"/>">Contacto</a></li>
<%--                 <li><a href="<c:url value="/jsp/empleo/herramientasDelSitio/mapaDeSitio.jsp"/>">Mapa de sitio</a></li>
                <li><a href="<c:url value="/miespacionav.do?method=agendaCita"/>">Solicita una cita</a></li> --%>
            </ul> 
        </div>
    </div>
    <div class="clearfix"></div>
</div>
