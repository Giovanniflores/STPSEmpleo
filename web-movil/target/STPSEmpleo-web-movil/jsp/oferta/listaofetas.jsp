<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%
  //Paginacion
  	int paginaActual = 1;
	if(session.getAttribute("NUM_PAGE_LIST")!= null){
		paginaActual =((Integer) session.getAttribute("NUM_PAGE_LIST")).intValue();
	}
  
  Integer TOTAL_PAGES = (Integer)session.getAttribute("TOTAL_PAGES");
  Integer ACTUAL_PAGES = (Integer)session.getAttribute("NUM_PAGE_LIST");
  Integer NUM_RECORDS_VISIBLE = (Integer)session.getAttribute("NUM_RECORDS_VISIBLE");
  if (TOTAL_PAGES==null) TOTAL_PAGES = 0;
  if (TOTAL_PAGES < 0) TOTAL_PAGES = 0;
  if (ACTUAL_PAGES==null || TOTAL_PAGES==0) ACTUAL_PAGES = 0;
  if (ACTUAL_PAGES < 0) ACTUAL_PAGES = 0;
  Integer NUM_RECORDS_TOTAL = (Integer)session.getAttribute("NUM_RECORDS_TOTAL");
  if (NUM_RECORDS_TOTAL==null) NUM_RECORDS_TOTAL = 0;	
  if (NUM_RECORDS_TOTAL < 0) NUM_RECORDS_TOTAL = 0;
  if (NUM_RECORDS_VISIBLE==null) NUM_RECORDS_VISIBLE = 0;	
  if (NUM_RECORDS_VISIBLE < 0) NUM_RECORDS_VISIBLE = 0;
  NUM_RECORDS_VISIBLE += (ACTUAL_PAGES-1)*(Integer)session.getAttribute("NUM_REGISTROS");
  if(NUM_RECORDS_VISIBLE < 0) NUM_RECORDS_VISIBLE = 0;

  pageContext.setAttribute("NUM_RECORDS_VISIBLE", NUM_RECORDS_VISIBLE);
  pageContext.setAttribute("NUM_RECORDS_TOTAL", NUM_RECORDS_TOTAL);
%>
	<%--
	<h2 dojoType="dojox.mobile.RoundRectCategory">Ofertas de Empleo</h2>
	--%>
	<c:if test="${empty PAGE_LIST}">
		<div dojoType="dojox.mobile.RoundRect"  align="center">
			<div style="text-align: center;" class="titNar">Ofertas de Empleo</div>
			No se encontraron ofertas de empleo para la búsqueda:<b><i><c:out value="${searchQ}"/><c:out value="${entity}"/></i></b>
		</div>
	</c:if>

	<c:if test="${not empty PAGE_LIST}">

	<div id="reporte<%=paginaActual%>" name="reporte<%=paginaActual%>" dojoType="dojox.mobile.View" selected="true">
		<h1 dojoType="dojox.mobile.Heading" style="font-size: 10px; height: 38px; text-align: left;"
		    label="Ofertas de Empleo ${NUM_RECORDS_VISIBLE} de ${NUM_RECORDS_TOTAL}" class="mblHeadingNoImage">
			
			<ul dojoType="dojox.mobile.TabBar" barType="segmentedControl" style="float: right; margin-right: 6px;">
				<li dojoType="dojox.mobile.TabBarButton" class="mblDomButton mblDomButtonUpArrow_2" selectOne="false" onclick="javascript:doSubmitPager('prev')"></li>
				<li dojoType="dojox.mobile.TabBarButton" class="mblDomButton mblDomButtonDownArrow_2" selectOne="false"  onclick="javascript:doSubmitPager('next')"></li>
			</ul>
		</h1>

		<ul dojoType="dojox.mobile.EdgeToEdgeList" >			
			<c:forEach var="oferta" items="${PAGE_LIST}">
			<li class="mblVariableHeight" dojoType="dojox.mobile.ListItem" style="font-size: 10px;" moveTo="ofertadetalle${oferta.idOfertaEmpleo}"
			    >
				<table>
					<tr>
						<td>
							<a class="titNarSeccion">${oferta.tituloOferta}</a><br/>
							${oferta.ubicacion}<br/>
							${oferta.empresa}<br/>
							<c:if test="${not empty oferta.salario}">
								<fmt:setLocale value="en_US"/>Salario pretendido: <fmt:formatNumber type="CURRENCY" value="${oferta.salario}" />
							</c:if>
						</td>
						<%--
						<td>${oferta.bolsaTrabajo}</td>
						<c:if test="${not empty USUARIO_APP && USUARIO_APP.candidato}">
							<c:if test="${oferta.compatibility > 0}">
								<td style="text-align:center;">${oferta.compatibility} %</td>
							</c:if>
							<c:if test="${oferta.compatibility == 0}">
								<td style="text-align:center;">---</td>
							</c:if>
						</c:if>
						--%>
					</tr>
				</table>
			</li>
			</c:forEach>
		</ul>

	</div>


		<c:forEach var="ofertaDetalle" items="${PAGE_LIST}" varStatus="index">

		<div id="ofertadetalle${oferta.idOfertaEmpleo}" dojoType="dojox.mobile.View">

			<c:if test="${empty USERLOGGED}">
				<h1 dojoType="dojox.mobile.Heading" icon="images/ico_rwd.jpg" moveTo="reporte<%=paginaActual%>"></h1>
			</c:if>
			<c:if test="${not empty USERLOGGED}">
				<c:if test="${USERLOGGED.empresa}">
					<h1 dojoType="dojox.mobile.Heading">
						<div dojoType="dojox.mobile.ToolBarButton" icon="images/ico_home.jpg" href="ofertasEmpresa.m?method=init" moveTo="bar"></div>
						<div dojoType="dojox.mobile.ToolBarButton" icon="images/ico_exit.jpg" style="width:45px;float:right;" href="logout.m"></div>
					</h1>
				</c:if>
				<c:if test="${USERLOGGED.candidato}">
					<h1 dojoType="dojox.mobile.Heading">
						<div dojoType="dojox.mobile.ToolBarButton" icon="images/ico_home.jpg" href="candidatoEspacio.m?method=init" moveTo="bar"></div>
						<div dojoType="dojox.mobile.ToolBarButton" icon="images/ico_exit.jpg" style="width:45px;float:right;" href="logout.m"></div>
					</h1>
				</c:if>
			</c:if>
			
			<h2 dojoType="dojox.mobile.RoundRectCategory">Detalle de Oferta</h2>
			<div dojoType="dojox.mobile.RoundRect" >
				<div class="mblVariableHeight" style="font-size: small;">
					<table cellpadding="0" cellspacing="0">
						<tr>
						<td><img src="imageAction.m?method=getLogotipoEmpresaRequest&ID_EMPRESA=${ofertaDetalle.detalle.idEmpresa}" alt="${ofertaDetalle.detalle.empresaNombre}" width="100px" height="100px">
						</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td><font color="blue">${ofertaDetalle.detalle.empresaNombre}</font><br/>
							<p>Oferta de empleo<br /><strong>${ofertaDetalle.detalle.tituloOferta}</strong></p>
							<p>Tipo de contrato<br /><strong>${ofertaDetalle.detalle.tipoContrato}</strong></p>
							<p>Horario laboral<br /><strong>${ofertaDetalle.detalle.horaEntrada} a ${ofertaDetalle.detalle.horaSalida}</strong></p>				
							<p>Ubicación<br /><strong>${ofertaDetalle.detalle.ubicacion}</strong><br /></p></td>
							<p>Contacto<br /><strong>${ofertaDetalle.detalle.oferta.correoElectronicoContacto}</strong><br /></p>
							<c:if test="${empty USERLOGGED}"><strong>¿Te interesa esta oferta de empleo? Inicia sesión para postularte. Recuerda que debes estar registrado en el portal.</strong></c:if>
						
						</tr>
					</table>
				</div>
			</div>

			<div dojoType="dojox.mobile.RoundRect" >
				<div style="text-align: center;" class="titNar">Requisitos</div>
				<div class="mblVariableHeight" style="font-size: small;">
					
					
					<table cellpadding="0" cellspacing="0">
					<tr>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td><strong>Último grado de estudios:</strong>${ofertaDetalle.detalle.gradoEstudios}<br>
							<strong>Carrera o especialidad:</strong>${ofertaDetalle.detalle.especialidades}<br>
							<strong>Situación académica:</strong>${ofertaDetalle.detalle.situacionAcademica}<br>
							<strong>Conocimientos y habilidades generales:</strong>${ofertaDetalle.detalle.habilidadGeneral}<br>
				            <strong>Experiencia:</strong> ${ofertaDetalle.detalle.experienciaAnios}<br>
							<strong>Competencias:</strong>${ofertaDetalle.detalle.requisitos}<br>
							<strong>Idiomas:</strong> ${ofertaDetalle.detalle.idiomasCert}<br>
							<c:if test="${ofertaDetalle.detalle.edadRequisito == 2}">
								<strong>Rango de edad:</strong> ${ofertaDetalle.detalle.edadMinima}a ${ofertaDetalle.detalle.edadMaxima} años<br/>
							</c:if>
							<c:if test="${not empty ofertaDetalle.detalle.disponibilidadViajar}">
								<strong>${ofertaDetalle.detalle.disponibilidadViajar}</strong><br/>
							</c:if>
				            <c:if test="${not empty ofertaDetalle.detalle.disponibilidadRadicar}">
								<strong>${ofertaDetalle.detalle.disponibilidadRadicar}</strong><br>
							</c:if>
							<c:if test="${not empty ofertaDetalle.detalle.observaciones}">
				            	<strong>Observaciones:</strong> ${ofertaDetalle.detalle.observaciones}<br>
				            </c:if>
				        </td>
					</tr>
				</table>

				</div>
			</div>

		</div>

		</c:forEach>
	
	</c:if>
