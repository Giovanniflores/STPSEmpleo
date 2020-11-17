	<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ page import="mx.gob.stps.portal.web.infra.utils.Utils"%>
	<%
		String context = request.getContextPath() +"/";
	%>
	<script type="text/javascript">
		function send(action, method){
			if (confirm('¿Está seguro que desea indexar ' + action + '?')) {
				document.formi.type.value = action;
				document.formi.method.value = method;
				document.formi.submit();
			}
		}
		function stop(method) {
			if (confirm('¿Está seguro que desea reestablecer el indice?')) {
				document.formi.method.value = method;
				document.formi.submit();
			}
		}
	</script>
	<noscript>Funciones de JavaScript desactivadas por navegador</noscript>
	
	<h2 class="titulo_profesion">Administrador de &iacute;ndices de b&uacute;squeda</h2>
    <div class="redacta">
		<c:out value="${msgindex}"/>
		<form name="formi" id="formi" method="post" action="indexer.do">
			<input type="hidden" name="method" value="run"/>
			<input type="hidden" name="type" value="ofertas"/>
			<div class="campo_carta">
				<input type="button" id="indexar_ofertas" name="indexar_ofertas" value="indexar ofertas" onclick="send('ofertas','run')" class="boton"/>
				<input type="button" id="indexar_candidatos" name="indexar_candidatos" value="indexar candidatos" onclick="send('candidatos','run')" class="boton"/>
				<input type="button" id="detener_indice" name="detener_indice" value="reestablecer" onclick="stop('stop')" class="boton"/>
			</div>
		</form>
    </div>