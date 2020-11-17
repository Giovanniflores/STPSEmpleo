<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<style type="text/css">
	#dialogDetalle_underlay { background-color:gray; }
	#dialogEmpFrau_underlay { background-color:green; }
</style>

<div class="PopAlert">
<p class="tit_PopAlert">Tienes certificaciones avaladas por:</p>
		<c:if test="${not empty titulosCertificaciones}">
			<!-- estilo provisional -->
			<div align="left" style="margin-left:0px; width:450px; color: #666666 !important; font-size: 12px !important; font-weight: bold !important; margin: 33px auto 0; width: 320px !important;">
				<c:forEach var="certificacion" items="${titulosCertificaciones}">
					&bull;&nbsp;<c:out value="${certificacion.titulo}"/><br>
				</c:forEach>
			</div>
		</c:if>
		
<p class="msg_PopAlert">
	¿deseas publicarlar en tu perfil para aumentar tus posibilidades de obtener un mejor empleo?
</p>
<p style="text-align: center;">

	<input type="button" value="Sí" onclick="javascript:registrarConocerConfig('Si')" style="width: 50px"/>
	<input type="button" value="No" onclick="javascript:registrarConocerConfig('No')" style="width: 50px"/>
</p>
<p class="check">
	<input name="checkVolverPreguntar" id="checkVolverPreguntar" type="checkbox" />&nbsp;<label for="checkVolverPreguntar">No volver a preguntarme</label>
</p>
</div>

<input id="idCandidato" name="idCandidato" type="hidden" value="${idCandidato}"/>