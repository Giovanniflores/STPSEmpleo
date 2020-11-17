<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div id="overPanel">
	<div id="cerrar" class="seiscientospx"></div>
	<div id="cuerpo_pop" class="busquedaCandidato">
		<div class="introLog" >			
			<p style="text-align: justify;"><h2><c:out value="${ERROR_MSG}"/></h2></p><br/>
		</div>
        <div class="logoLightBox"> <span><img src="images/busqueda_candidatos_div.jpg" /></span>
        	<p class="user">¿Nuevo usuario en el sitio?
        	<span><a href="<c:url value="/registro_candidato.do?method=init"/>">Inscríbete</a></span>
            <img src="images/logo_bottomLightBox.gif" alt="Portal del Empleo" />
		</div>
    </div>
    <div class="bottom_lightBox2"></div>
</div>


