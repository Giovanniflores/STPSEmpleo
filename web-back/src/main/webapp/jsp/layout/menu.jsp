<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:if test="${not empty USUARIO_APP}">
 	<!-- ADMINISTRADOR -->
	<c:if test="${USUARIO_APP.administrador}">
	<ul>
	    <li><a href="logout.do" class="adminCerrar">Cerrar sesión: </a></li>
		<li><a href="<c:url value="/adminpermisos.do?method=init"/>">Permisos</a></li>
	    <li><a href="<c:url value="/filtercompany.do?method=init"/>">Buscador de empresas</a></li>
	    <li><a href="<c:url value="/filtraCandidato.do?method=init"/>">Buscador de candidatos</a></li>
	    <li><a href="<c:url value="/filteroffer.do?method=init"/>">Buscador de ofertas</a></li>
	    <c:if test="${USUARIO_APP.superUsuario}">
	    	<li><a href="<c:url value="/parametrizarbloque.do?method=init"/>">Parámetros</a></li>
	    </c:if>	    
		<li><a href="<c:url value="/adminusuarios.do?method=init"/>">Desbloqueo</a></li>
		<li><a href="<c:url value="/transferenciasfp.do?method=init"/>">Ofertas SFP</a></li>
		<li><a href="<c:url value="/publishertree.do?method=init"/>">Publicadores</a></li>
		<li><a href="<c:url value="/publisheractivity.do?method=init"/>">Productividad de publicadores</a></li>
		<li><a href="<c:url value="/notificaciones.do?method=init"/>">Notificaciones por correo</a></li>
		<li><a href="<c:url value="/filteroffer.do?method=initPendientesPublicar"/>">Publicación de ofertas</a></li>
	</ul>
	</c:if>

	<!-- PUBLICADOR -->
	<c:if test="${USUARIO_APP.publicador}">
	<ul>
	    <li><a href="logout.do" class="adminCerrar">Cerrar sesión</a></li>
	    <li><a href="<c:url value="/filtercompany.do?method=init"/>">Buscador de empresas</a></li>
	    <!--<li><a href="<c:url value="/filtraCandidato.do?method=init"/>">Buscador de candidatos</a></li>-->
	    <li><a href="<c:url value="/filteroffer.do?method=init"/>">Buscador de ofertas</a></li>
	    <li><a href="autorization.do?method=init">Registros por autorizar</a></li>
	</ul>
	</c:if>

	<!-- Administrador SNETEL -->
	<c:if test="${USUARIO_APP.administradorSnetel}">
	<ul>
	    <li><a href="logout.do" class="adminCerrar">Cerrar sesión: </a></li>
	    <li><a href="<c:url value="/filtercompany.do?method=init"/>">Buscador de empresas</a></li>
	    <li><a href="<c:url value="/filtraCandidato.do?method=init"/>">Buscador de candidatos</a></li>
	    <li><a href="<c:url value="/filteroffer.do?method=init"/>">Buscador de ofertas</a></li>
	    <li><a href="autorization.do?method=init">Registros por autorizar</a></li>
<!-- 	    <li><a href="<c:url value="parametrizarbloque.do?method=init"/>">Parámetros</a></li> -->
	    <li><a href="<c:url value="/publishertree.do?method=init"/>">Publicadores</a></li>
	    <li><a href="<c:url value="/publisheractivity.do?method=init"/>">Productividad de publicadores</a></li>	    	   
	</ul>
	</c:if>
	
	<!-- Supervisor SNETEL -->
	<c:if test="${USUARIO_APP.supervisorSnetel}">
	<ul>
		<li><a href="logout.do" class="adminCerrar">Cerrar sesión</a></li>
	    <li><a href="<c:url value="/filtercompany.do?method=init"/>">Buscador de empresas</a></li>
	    <li><a href="<c:url value="/filtraCandidato.do?method=init"/>">Buscador de candidatos</a></li>
	    <li><a href="<c:url value="/filteroffer.do?method=init"/>">Buscador de ofertas</a></li>
	</ul>
	</c:if>	

	<!-- SNETEL Monitor -->
	<c:if test="${USUARIO_APP.monitor}">
	<ul>
	    <li><a href="logout.do" class="adminCerrar">Cerrar sesión</a></li>
	    <li><a href="<c:url value="/repofertasvig.do?method=init"/>">Plazas vigentes</a></li>
	    <li><a href="<c:url value="/repofertasvig.do?method=ofertasPorActividadEconomica"/>">Plazas vigentes por actividad econ&oacute;mica</a></li>
	    <li><a href="<c:url value="/repofertasvig.do?method=ofertasPorAreaOcupacion"/>">Plazas vigentes por &aacute;rea laboral y ocupaci&oacute;n</a></li>		       	     
	</ul>
	</c:if>

	<!-- ADMINISTRADOR TIPO A -->
	<c:if test="${USUARIO_APP.adminTipoA}">
	<ul>
	    <li><a href="logout.do" class="adminCerrar">Cerrar sesión</a></li>
	    <li><a href="<c:url value="/parametrizarbloque.do?method=init"/>">Parámetros</a></li>
	</ul>
	</c:if>

</c:if>