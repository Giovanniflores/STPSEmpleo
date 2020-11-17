<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="sufijo" value="" />
<c:set var="pageList" value="PAGE_LIST${sufijo}" />

<c:if test="${not empty _RECORDS}">
	<c:set var="records" value="${_RECORDS}" />
</c:if>
<c:if test="${empty _RECORDS}">
	<c:set var="records" value="0" />
</c:if>

<c:set var="registrosAux" value="NUM_RECORDS_VISIBLE${sufijo}" />
<c:set var="registrosVisibles" value="${sessionScope[registrosAux]}" />

<c:if test="${not empty sessionScope[pageList]}">
	<h4>Total de registros por publicar: ${records}</h4>
	<span class="entero">
		<div id="hideAll" style="display:none;"  align="right"><a href="javascript:mostrarTodas(2, ${registrosVisibles})" class="expand">[Ocultar resumen de todos los publicadores]</a></div>
		<div id="showAll" style="display:block;" align="right"><a href="javascript:mostrarTodas(1, ${registrosVisibles})" class="expand">[Ver resumen de todos los publicadores]</a></div>
	</span>	
	<table id="dataTable3" class="offer" cellspacing="0" cellpadding="0" border="0" width="730px">
		<tbody>
			<tr class="temas">
		    	<TH width="25%">Publicador</TH>
		    	<%--<TH width="15%">Empresas</TH>--%>
		    	<TH width="15%">Ofertas</TH>
		    	<TH width="15%">Testimonios</TH>
		    	<TH width="15%">Videocurriculums</TH>
		    	<TH width="15%">Total</TH>
			</tr>
			<c:forEach var="publisher" items="${sessionScope[pageList]}" varStatus="rowCounter">
				<tr <c:out value="${rowCounter.count % 2 == 0?'':'class=odd'}"/>>
					<td>${publisher.nombre}&nbsp;${publisher.apellido1}&nbsp;${publisher.apellido2}</td>
					<%--<td>${publisher.empresas}</td>--%>
					<td>${publisher.ofertas}</td>
					<td>${publisher.testimonios}</td>
					<td>${publisher.videocv}</td>
					<td>${publisher.total}</td>
				</tr>
				<tr class="detalles">
					<td colspan="6">
						<a href="javascript:mostrarResumen('${rowCounter.count}')" class="expand">
							<div id="hide${rowCounter.count}" style="display:none;">Ocultar resumen</div>
			    			<div id="show${rowCounter.count}" style="display:block;">Ver resumen</div>
						</a>
						<div  style="display:none;" id="${rowCounter.count}">
							<%--
							<c:if test="${publisher.empresas > 0}">
								&nbsp;&nbsp;&nbsp;<strong>Empresas&nbsp;&nbsp;${publisher.empresas}</strong>
								<ul>
									<c:forEach var="company" items="${publisher.companies}">
										<li>
											&nbsp;&nbsp;&nbsp;&nbsp;[${company.idPortalEmpleo},<c:if test="${not empty company.razonSocial}">${company.razonSocial}</c:if><c:if test="${not empty company.nombre}">${company.nombre}&nbsp;${company.apellido1}&nbsp;${company.apellido2}</c:if>]
										</li>
									</c:forEach>
								</ul>
							</c:if>
							--%>
							<c:if test="${publisher.ofertas > 0}">
								&nbsp;&nbsp;&nbsp;<strong>Ofertas&nbsp;&nbsp;${publisher.ofertas}</strong>
								<ul>
									<c:forEach var="offer" items="${publisher.offers}">
										<li>
											&nbsp;&nbsp;&nbsp;&nbsp;[${offer.idOfertaEmpleo},${offer.tituloOferta}]
										</li>
									</c:forEach>
								</ul>
							</c:if>
							<c:if test="${publisher.testimonios > 0}">
								&nbsp;&nbsp;&nbsp;<strong>Testimonios&nbsp;&nbsp;${publisher.testimonios}</strong>
								<ul>
									<c:forEach var="testimony" items="${publisher.testimonies}">
										<li>
											&nbsp;&nbsp;&nbsp;&nbsp;[${testimony.nombre}]
										</li>
									</c:forEach>
								</ul>
							</c:if>
							<c:if test="${publisher.videocv > 0}">
								&nbsp;&nbsp;&nbsp;<strong>Video curriculums&nbsp;&nbsp;${publisher.videocv}</strong>
								<ul>
									<c:forEach var="curriculum" items="${publisher.curriculums}">
										<li>
												&nbsp;&nbsp;&nbsp;&nbsp;[${curriculum.nombre}&nbsp;${curriculum.apellido1}&nbsp;${curriculum.apellido2}]
										</li>
									</c:forEach>
								</ul>
							</c:if>
						</div>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

<jsp:include page="../../layout/pager.jsp" flush="true">
         <jsp:param name="SUFIJO" value="${sufijo}"/>
         <jsp:param name="tipoRegistros" value="publicadores"/>		 
</jsp:include>

</c:if>
<c:if test="${empty sessionScope[pageList]}">
	<table>
		<tr><td>No se encontraron publicadores activos</td></tr>
	</table>
</c:if>
