<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%String context = request.getContextPath() +"/";%>

    <span id="n_ayuda"><a href="#">¿Necesitas ayuda?</a></span>

	<c:if test="${not empty USUARIO_APP}">
		<c:if test="${USUARIO_APP.idTipoUsuario == 2}">
			<span id="mi_espacio"><a href="<c:url value="/miEspacioEmpresas.do"/>">Ir a mi espacio</a></span>
		</c:if>
		<c:if test="${USUARIO_APP.idTipoUsuario == 3}">
		<span id="mi_espacio"><a href="<c:url value="/miEspacioCandidato.do"/>">Ir a mi espacio</a></span>
		</c:if>
	</c:if>

    <span id="p1"><a href="calculadora.do?method=init">Calculadora de egresos</a>&nbsp;|</span>
    <%--
    <span id="p2"><a href="buscadorCarrera.html">Buscador de carrera</a>&nbsp;|</span>
    <span id="p3"><a href="perfil_video.html">Sube tu vídeoCV</a>&nbsp;</span>
    <span id="letra_chica"><a href="#">A</a></span>
    <span id="letra_normal"><a href="#">A</a></span>
    <span id="letra_grande"><a href="#">A</a></span>
    <span id="imprimir"><a href="#">Imprimir</a></span>
    <span id="send_mail"><a href="#">Enviar correo electrónico</a></span>
    <span id="comentario"><a href="#">Comentario</a></span>
    --%>
    <span id="que_buscas">
    <label for="que_buscas"></label>
    <input type="text" name="que_buscas" id="que_buscas_field" />
    <input name="" type="button" value="ir" id="bt_buscar" />
    </span>