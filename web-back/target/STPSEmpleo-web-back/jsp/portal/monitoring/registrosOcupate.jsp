<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="java.util.List"%>
<%@ page import="java.text.NumberFormat"%>
<%@ page import="java.util.List, java.util.Iterator"%>
<%@ page import="mx.gob.stps.portal.web.infra.utils.UrlUtils, mx.gob.stps.portal.core.oferta.busqueda.vo.OfertaPorCanalVO, mx.gob.stps.portal.core.search.ResultInfoBO"%>

<% 
   String contextSWB = (String)application.getAttribute("DOMAIN_PORTAL");
   if (null != request.getSession().getAttribute("FROM_CIL")){
	   contextSWB = (String)application.getAttribute("DOMAIN_PORTAL_CIL");
   }
  String searchQ = "";
  String searchMessage = "";
  String context = request.getContextPath() +"/";
  searchQ = (null != request.getParameter("searchQ") ? request.getParameter("searchQ") : "");
  searchQ = (String) (null != session.getAttribute("searchQ") ? session.getAttribute("searchQ") : "");
  String searchPlace = (null != session.getAttribute("entity") ? (String)session.getAttribute("entity") : "");
  Integer totalNumeroPlazas = (null != session.getAttribute("totalNumeroPlazas") ? (Integer)session.getAttribute("totalNumeroPlazas") : 0);
  searchMessage = (null != session.getAttribute("searchMessage") ? (String)session.getAttribute("searchMessage") : "");
  String strTotalNumeroPlazas = String.valueOf(totalNumeroPlazas);
  int hashcode = searchQ.hashCode();
  String sufix = "" + hashcode;
  List<ResultInfoBO> list = (List<ResultInfoBO>)session.getAttribute("FULL_LIST" + hashcode);
  out.println(searchMessage);
  List PAGE_LIST = (List)session.getAttribute("PAGE_LIST" + hashcode);  
  if (null != PAGE_LIST && PAGE_LIST.size() > 0) {
	  Iterator<OfertaPorCanalVO> it = PAGE_LIST.iterator();  
%>
	<!-- div id="hideAll" style="display:none;"  align="right"><a href="javascript:mostrarTodas(2, ${NUM_REGISTROS})" class="expand">[Ocultar resumen de todas las ofertas]</a></div>
	<div id="showAll" style="display:block;" align="right"><a href="javascript:mostrarTodas(1, ${NUM_REGISTROS})" class="expand">[Ver resumen de todas las ofertas]</a></div -->
<br /><br />
<div class="no-more-tables">
	<a name="showAll"></a>
	<table border="0" cellspacing="0" cellpadding="0" class="table table-striped table-condensed offer">
		<thead>
			<tr class="temas">
				<th>
					<span>Título de la oferta</span>
					<div class="pull-right">
						<a id="titulo_orden_asc" title="Orden Ascendente" style="display: inline;" class="ascendente" href="javascript:orderBy('asc','1')"></a>
						<a id="titulo_orden_desc" title="Orden Descendente" style="display: inline;"  class="descendente" href="javascript:orderBy('desc','1')"></a>
					</div>
				</th>
				<th>
					<span>Ubicaci&oacute;n</span>
					<div class="pull-right">
						<a id="ubicacion_orden_asc" title="Orden Ascendente" style="display: inline;" class="ascendente" href="javascript:orderBy('asc','2')"></a>
						<a id="ubicacion_orden_desc" title="Orden Descendente" style="display: inline;"  class="descendente" href="javascript:orderBy('desc','2')"></a>
					</div>
				</th>
				<th>
					<span>Empresa</span>
					<div class="pull-right">
						<a id="empresa_orden_asc" title="Orden Ascendente" style="display: inline;" class="ascendente" href="javascript:orderBy('asc','3')"></a>
						<a id="empresa_orden_desc" title="Orden Descendente" style="display: inline;"  class="descendente" href="javascript:orderBy('desc','3')"></a>
					</div>
				</th>
				<th>
					<span>Salario neto ofrecido (mensual)</span>
					<div class="pull-right">
						<a id="salario_orden_asc" title="Orden Ascendente" style="display: inline;" class="ascendente" href="javascript:orderBy('asc','4')"></a>
						<a id="salario_orden_desc" title="Orden Descendente" style="display: inline;"  class="descendente" href="javascript:orderBy('desc','4')"></a>
					</div>
				</th>
				<c:if test="${empty USUARIO_APP || !USUARIO_APP.candidato}">
					<th>
						<span>Fecha de publicaci&oacute;n</span>
						<div class="pull-right">
							<a id="fecha_orden_asc" title="Orden Ascendente" style="display: inline;" class="ascendente" href="javascript:orderBy('asc','5')"></a>
							<a id="fecha_orden_desc" title="Orden Descendente" style="display: inline;"  class="descendente" href="javascript:orderBy('desc','5')"></a>
						</div>
					</th>
				</c:if>
				<c:if test="${not empty USUARIO_APP && USUARIO_APP.candidato}">
					<th>
						<span>Fecha de publicaci&oacute;n</span>
						<div class="pull-right">
							<a id="fecha_orden_asc" title="Orden Ascendente" style="display: inline;" class="ascendente" href="javascript:orderBy('asc','5')"></a>
							<a id="fecha_orden_desc" title="Orden Descendente" style="display: inline;"  class="descendente" href="javascript:orderBy('desc','5')"></a>
						</div>
					</th>
					<th class="fin">
						<span>Compatibilidad</span>
					</th>
				</c:if>
			</tr>
		</thead>
	<%
		int rowCounter = 0;	
		while (it.hasNext()) {
			OfertaPorCanalVO ofertasCanal = it.next();
	%>
			<tr <% out.print(rowCounter % 2 == 0 ? "class='odd'" : "class='odd2'"); %>>
				<td data-title="Título de la oferta">
					<div class="profesion">
						<a href="<%=context%>detalleoferta.do?method=init&id_oferta_empleo=<% out.print(ofertasCanal.getIdOfertaEmpleo()); %>"><% out.print(ofertasCanal.getTituloOferta()); %></a>
					</div>
				</td>
				<td data-title="Ubicación"><% out.print(ofertasCanal.getUbicacion()); %></td>
				<td data-title="Empresa"><% out.print(ofertasCanal.getEmpresa()); %></td>
				<td data-title="Salario neto (mensual)"><% out.print(ofertasCanal.getsSalario()); %></td>
				<td data-title="Fecha de publicación"><% out.print(ofertasCanal.getFechaInicioString()); %></td>
				<c:if test="${not empty USUARIO_APP && USUARIO_APP.candidato}">
					<% if (ofertasCanal.getCompatibility() > 0) {%>
							<td data-title="Compatibilidad" style="text-align:center;"><% out.print(ofertasCanal.getCompatibility()); %> %</td>
					<% }else { %>
							<td data-title="Compatibilidad" style="text-align:center;">---</td>
					<% } %>
				</c:if>
			</tr>
			<tr <% out.print(rowCounter % 2 == 0 ? "class='detalles odd'" : "class='detalles'"); %>>
				<c:if test="${empty USUARIO_APP || !USUARIO_APP.candidato}">
					<td colspan="5">
				</c:if>
				<c:if test="${not empty USUARIO_APP && USUARIO_APP.candidato}">
					<td colspan="6">
				</c:if>
					<div id="<% out.print("" + rowCounter); %>">
					Solicitamos: <% out.print(ofertasCanal.getTituloOferta()); %> <br/>
					<% out.print((null!=ofertasCanal.getGradoEstudio() ? ofertasCanal.getGradoEstudio() : "")); %> <% out.print((null!=ofertasCanal.getCarrera() ? ofertasCanal.getCarrera() : "")); %> <br/>
					<% out.print(ofertasCanal.getFunciones()); %>  <br/><!-- Edad: <% out.print(ofertasCanal.getEdad()); %> a&ntilde;os, <br/>
					<% out.print(ofertasCanal.getIdiomas()); %> Horario de empleo: <% out.print(ofertasCanal.getHorario()); %> <br>
					N&uacute;mero de plazas: <% out.print(ofertasCanal.getNumeroPlazas()); %> --> 
					<% if (null!=ofertasCanal.getHabilidadGeneral() && !ofertasCanal.getHabilidadGeneral().isEmpty()) { %>
						<strong>Conocimientos y habilidades generales:</strong> <% out.print(ofertasCanal.getHabilidadGeneral()); %> <br>
					<% } %>
						<%-- strong>Experiencia:</strong> <% out.print(ofertasCanal.getExperiencia()); % --%>
						<% if (null!=ofertasCanal.getCompetencias() && !ofertasCanal.getCompetencias().isEmpty()) out.print("<strong>Competencias:</strong> " + ofertasCanal.getCompetencias()); %> 
						<strong>Idiomas:</strong> <% out.print(ofertasCanal.getIdiomas()); %> 
						<strong>Rango de edad:</strong> <% out.print(ofertasCanal.getEdad()); if (!"N/A".equals(ofertasCanal.getEdad())) out.print(" años"); %> 
					</div>
				</td>
			</tr>
	<%
			rowCounter++;
			}			
  		}
	%>
	</table>
<%
	 //if (null != list && list.size() > 0) {
%>
		<!-- p align="center">
			<a href="javascript:doSubmitPager('prev')">&lt;&lt;&lt;&nbsp;</a>
		    &nbsp;..&nbsp;<--%=ACTUAL_PAGES %>&nbsp;..&nbsp;
		    <a href="javascript:doSubmitPager('next')">&nbsp;&gt;&gt;&gt;</a>
		    &nbsp;&nbsp;de <--%=TOTAL_PAGES %> P&aacute;ginas
		</p>
		<p align="center">Mostrando <--%=NUM_RECORDS_VISIBLE %> de <--%=NUM_RECORDS_TOTAL %> Ofertas</p  -->
<%
	//}
%>
</div>
<br clear="all"/>
<jsp:include page="../layout/pager.jsp" flush="true">
       <jsp:param name="SUFIJO" value="<%=sufix%>"/>
</jsp:include>
