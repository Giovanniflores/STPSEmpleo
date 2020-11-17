<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!-- inicio índice de paginación -->
<c:set var= "sufijo" value="${param.SUFIJO}" />
<c:set var= "tipoRegistros" value="${param.tipoRegistros}" />

<c:set var="totalPages" value="TOTAL_PAGES${sufijo}" />
<c:set var="numPageList" value="NUM_PAGE_LIST${sufijo}" />

<c:set var="pageJumpSize" value="PAGE_JUMP_SIZE${sufijo}" />
<c:set var="numRecordsTotal" value="NUM_RECORDS_TOTAL${sufijo}" />

<c:set var="totalPaginas" value="${sessionScope[totalPages]}" />
<c:set var="paginaActual" value="${sessionScope[numPageList]}" />

<c:if test="${empty paginaActual}">
	<c:set var="paginaActual" value="1" />
</c:if>
<c:set var="paginasPorBloque" value="${10}" />

<c:set var="registrosPorPagina" value="${sessionScope[pageJumpSize]}" />
<c:set var="totalRegistros" value="${sessionScope[numRecordsTotal]}" />


<c:choose>
	<c:when
		test="${paginaActual != 0 && totalPaginas != 0 && totalRegistros != 0 && registrosPorPagina != 0 && totalRegistros != 0}">
		<c:set var="numBloque"
			 value="${(paginaActual-1)/paginasPorBloque - (paginaActual-1)%paginasPorBloque/paginasPorBloque}" /> 
		<fmt:formatNumber maxFractionDigits="0" value="${numBloque}"
			var="numBloque" />
		<c:set var="ultimoBloque"
			value="${(totalPaginas-1)/paginasPorBloque - (totalPaginas-1)%paginasPorBloque/paginasPorBloque}" />
		<fmt:formatNumber maxFractionDigits="0" value="${ultimoBloque}"
			var="ultimoBloque" />
		<c:set var="paginaInicialBloque"
			value="${numBloque*paginasPorBloque+1}" />
		<c:set var="paginaFinalBloque"
			value="${paginaInicialBloque+paginasPorBloque-1}" />
		<c:set var="primerRegistroMostrado"
			value="${(paginaActual-1)*registrosPorPagina+1}" />
		<c:set var="ultimoRegistroMostrado"
			value="${primerRegistroMostrado+registrosPorPagina-1}" />
		<c:if test="${ultimoRegistroMostrado>totalRegistros}">
			<c:set var="ultimoRegistroMostrado" value="${totalRegistros}" />
		</c:if>
		<c:if test="${paginaFinalBloque>totalPaginas}">
			<c:set var="paginaFinalBloque" value="${totalPaginas}" />
		</c:if>
	</c:when>
	<c:otherwise>
		<c:set var="numBloque" value="0" />
		<c:set var="paginaInicialBloque" value="0" />
		<c:set var="paginaFinalBloque" value="0" />
		<c:set var="primerRegistroMostrado" value="0" />
		<c:set var="ultimoRegistroMostrado" value="0" />
	</c:otherwise>
</c:choose>
<!-- liga para saltar al bloque anterior -->
<div class="paginator">
	<ul>
	
	<c:choose>
		<c:when test="${numBloque==0}">
				<li><a class="prev"></a></li>
			</c:when>
		<c:otherwise>
			<c:set var="primeraPaginaBloqueAnterior" value="${(numBloque-1)*paginasPorBloque+1}" />
			<li><a class="prev" href="javascript:doSubmitPage(${primeraPaginaBloqueAnterior})">Anterior</a></li>
		</c:otherwise>
	</c:choose>
	<!-- numeración de páginas a mostrar -->

		<c:forEach var="i" begin="${paginaInicialBloque}" end="${paginaFinalBloque}" step="1" varStatus="status">
			<c:choose>
				<c:when test="${i==paginaActual}">
					<li><span class="current"><c:out value="${i}" /></span></li>
				</c:when>
				<c:otherwise>
					<li><a class="pagina" href="javascript:doSubmitPage(${i})"><c:out value="${i}" /></a></li>
				</c:otherwise>
			</c:choose>
		</c:forEach>
	<!-- total de páginas -->
	<li><span class="noPags"> de <c:out value="${totalPaginas}" /> P&aacute;ginas</span>&nbsp;</li>
	
	<!-- liga para saltar al bloque posterior -->
	<c:choose>
		<c:when test="${numBloque==ultimoBloque or totalRegistros==0}">
			<li><a class="next"></a></li>
		</c:when>
		<c:otherwise>
			<c:set var="primeraPaginaBloqueSiguiente" value="${(numBloque+1)*paginasPorBloque+1}" />
			<li><a class="next" href="javascript:doSubmitPage(${primeraPaginaBloqueSiguiente})">Siguiente</a></li>
		</c:otherwise>
	</c:choose>
	</ul>
	<br clear="all" />
	<p class="mostradas">
		Mostrando
		<c:if test="${totalRegistros gt 0 }">
		<c:out value="${primerRegistroMostrado}" />
		-
		<c:out value="${ultimoRegistroMostrado}" />
			de
		</c:if>			
		<c:out value="${totalRegistros} ${tipoRegistros}" />
		<p>
			<label for="numPagina"><strong>Página</strong></label>
		    <input type="text" id="numPagina"/>
		    <input class="ir" type="button" value="Ir" onclick="javascript:doSubmitPage(getElementById('numPagina').value, '${sufijo}')"/>
		</p>		
	</p>
</div>
<!-- final índice de paginación -->

