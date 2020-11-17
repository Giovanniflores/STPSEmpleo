<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<style type="text/css">
	.verde{
		color: #4F6710;
	}
	.empresa{
		width:200px;
	}
	.ubicacion{
		margin-top:-20px
	}
	.listaMasOfertasEmpleo{
		margin-left:-18px;
	}
</style>
<c:if test="${not empty LISTA_SIGUIENTES_OFERTAS}">
	<h3>M&aacute;s ofertas de empleo</h3>
	<ul class="listaMasOfertasEmpleo">
	<c:forEach var="siguientesOfertas" items="${LISTA_SIGUIENTES_OFERTAS}">	
		<c:if test="${idOfertaEmpleoConsultada != siguientesOfertas.idOfertaEmpleo}">
			
			<c:url var="url" value="detalleoferta.do?method=init&id_oferta_empleo=${siguientesOfertas.idOfertaEmpleo}">
				<c:if test="${not empty _urlpageinvoke}">
					<c:if test="${fn:contains(_urlpageinvoke, 'ofertasRecientesTodas')}">
						<c:param name="_urlpageinvoke" value="ofertasRecientesTodas"/>
					</c:if>
					<c:if test="${fn:contains(_urlpageinvoke, 'ofertasDestacadasTodas')}">
						<c:param name="_urlpageinvoke" value="ofertasDestacadasTodas"/>
					</c:if>
				</c:if>
			</c:url>			

			<li>
				<a href="${url}">
					<strong><span class="verde"><c:out value="${siguientesOfertas.tituloOferta}"></c:out></span></strong>
				</a>
				<c:out value="${siguientesOfertas.empresaNombre}"/><br/>
				<c:out value="${siguientesOfertas.ubicacion}"/><br/><br/>
			</li>
		</c:if>
	</c:forEach>
	</ul>
</c:if>
