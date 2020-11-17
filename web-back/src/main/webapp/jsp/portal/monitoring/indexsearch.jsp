<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ page import="mx.gob.stps.portal.web.infra.utils.Utils"%>
	<%
		String context = request.getContextPath() +"/";
	%>
	<script type="text/javascript">
		function send(action, method, entity){
			if (confirm('¿Está seguro que desea buscar ' + entity + '?')) {
				document.formi.type.value = action;
				document.formi.method.value = method;
				document.formi.submit();
			}
		}
		function index(action, method, entity){
			if (confirm('¿Está seguro que desea indexar ' + entity + '?')) {
				document.formi.type.value = action;
				document.formi.method.value = method;
				document.formi.submit();
			}
		}
	</script>
	<h2 class="titulo_profesion">Administrador de &iacute;ndices de b&uacute;squeda</h2>
    <div class="redacta">
		<c:out value="${msgindex}"/>
		<form name="formi" id="formi" method="post" action="indexer.do">
			<input type="hidden" name="method" value="indexedElement"/>
			<input type="hidden" name="type" value="offer"/>
			<div class="campo_carta">
				<table>
					<tr>
						<td align="center">
							<input type="text" id="identity" name="identity" value="" size="10"/>
						</td>
						<td align="center">
							<input type="button" id="search_offer" name="search_offer" value="Buscar oferta" onclick="send('0','indexedElement','oferta')" class="boton"/>
							<input type="button" id="search_candidate" name="search_candidate" value="Buscar candidato" onclick="send('1','indexedElement','candidato')" class="boton"/>
							<input type="button" id="index_offer" name="index_offer" value="Indexar oferta" onclick="index('0','indexElement','oferta')" class="boton"/>
						</td>
					</tr>
				</table>
			</div>
		</form>
    </div>