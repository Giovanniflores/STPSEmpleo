<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.List"%>
<%@ page import="java.text.NumberFormat"%>
<%@ page import="java.util.List, java.util.Iterator"%>
<%@ page import="mx.gob.stps.portal.core.oferta.busqueda.vo.OfertaPorCanalVO"%>
<%
	String msg = "";
	String sufix = "";
	String context = request.getContextPath() +"/";
  	List<Long> list = (List<Long>)session.getAttribute("FULL_LIST");
  	if (null != list && list.size() > 0)
		msg = list.size() + " resultados encontrados para la búsqueda: " + (null != session.getAttribute("SPECIFIC_SEARCH") ? "<span class=\"busqueda_concepto\">" + (String)session.getAttribute("SPECIFIC_SEARCH") + "</span>" : "");
  	else
		msg = null != session.getAttribute("SPECIFIC_SEARCH") ? "No se encontraron ofertas para la búsqueda: <span class=\"busqueda_concepto\">" + (String)session.getAttribute("SPECIFIC_SEARCH") + "</span>" : "";
	List PAGE_LIST = (List)session.getAttribute("PAGE_LIST");
	/*Paginacion
  	Integer TOTAL_PAGES = (Integer)request.getSession().getAttribute("TOTAL_PAGES");
  	Integer ACTUAL_PAGES = (Integer)request.getSession().getAttribute("NUM_PAGE_LIST");
  	Integer NUM_RECORDS_TOTAL = (Integer)request.getSession().getAttribute("NUM_RECORDS_TOTAL");
  	Integer NUM_RECORDS_VISIBLE = (Integer)request.getSession().getAttribute("NUM_RECORDS_VISIBLE");
  	Integer NUM_REGISTROS = (Integer)request.getSession().getAttribute("NUM_REGISTROS");
  	if (TOTAL_PAGES==null) TOTAL_PAGES=0;
  	if (TOTAL_PAGES < 0) TOTAL_PAGES=0;
  	if (ACTUAL_PAGES==null || TOTAL_PAGES==0) ACTUAL_PAGES=0;
  	if (ACTUAL_PAGES < 0) ACTUAL_PAGES=0;
  	if (NUM_RECORDS_TOTAL==null) NUM_RECORDS_TOTAL=0;	
  	if (NUM_RECORDS_TOTAL < 0) NUM_RECORDS_TOTAL=0;
  	if (NUM_RECORDS_VISIBLE==null) NUM_RECORDS_VISIBLE=0;	
  	if (NUM_RECORDS_VISIBLE < 0) NUM_RECORDS_VISIBLE=0;
  	if (null != NUM_REGISTROS)
  		NUM_RECORDS_VISIBLE += (ACTUAL_PAGES-1)*NUM_REGISTROS;
  	if (NUM_RECORDS_VISIBLE < 0) NUM_RECORDS_VISIBLE=0;*/
	String tipoConsultaUrl = (null != request.getAttribute("tipoConsultaUrl") ? (String)request.getAttribute("tipoConsultaUrl") : "CAPACIDADES");

  	
  	if (null != PAGE_LIST && PAGE_LIST.size() > 0) {
	  	Iterator<OfertaPorCanalVO> it = PAGE_LIST.iterator();
%>


	<span class="entero">
		<%=msg%>
		<!--div id="hideAll" style="display:none;"  align="right"><a href="javascript:mostrarTodas(2, ${NUM_REGISTROS})" class="expand">[Ocultar resumen de todas las ofertas]</a></div>
		<div id="showAll" style="display:block;" align="right"><a href="javascript:mostrarTodas(1, ${NUM_REGISTROS})" class="expand">[Ver resumen de todas las ofertas]</a></div-->
	</span>
	<table border="0" cellspacing="0" cellpadding="0" class="offer">
		<tr class="temas">
			<th>T&iacute;tulo de la oferta <a id="titulo_orden_asc" style="display: inline;" class="ascendente" href="javascript:orderBy('asc','1','<c:out value="${SPECIFIC_SEARCH}"/>')"></a> <a id="titulo_orden_desc" style="display: inline;"  class="descendente" href="javascript:orderBy('desc','1','<c:out value="${SPECIFIC_SEARCH}"/>')"></a></th>
			<th>Ubicaci&oacute;n <a id="ubicacion_orden_asc" style="display: inline;" class="ascendente" href="javascript:orderBy('asc','2','<c:out value="${SPECIFIC_SEARCH}"/>')"></a> <a id="ubicacion_orden_desc" style="display: inline;"  class="descendente" href="javascript:orderBy('desc','2','<c:out value="${SPECIFIC_SEARCH}"/>')"></a></th>
			<th>Empresa <a id="empresa_orden_asc" style="display: inline;" class="ascendente" href="javascript:orderBy('asc','3','<c:out value="${SPECIFIC_SEARCH}"/>')"></a> <a id="empresa_orden_desc" style="display: inline;"  class="descendente" href="javascript:orderBy('desc','3','<c:out value="${SPECIFIC_SEARCH}"/>')"></a></th>
			<th>Salario neto ofrecido (mensual) <a id="salario_orden_asc" style="display: inline;" class="ascendente" href="javascript:orderBy('asc','4','<c:out value="${SPECIFIC_SEARCH}"/>')"></a> <a id="salario_orden_desc" style="display: inline;"  class="descendente" href="javascript:orderBy('desc','4','<c:out value="${SPECIFIC_SEARCH}"/>')"></a></th>
			<c:if test="${empty USUARIO_APP || !USUARIO_APP.candidato}">
				<th class="fin">Fecha de publicaci&oacute;n <a id="fecha_orden_asc" style="display: inline;" class="ascendente" href="javascript:orderBy('asc','5','<c:out value="${SPECIFIC_SEARCH}"/>')"></a> <a id="fecha_orden_desc" style="display: inline;"  class="descendente" href="javascript:orderBy('desc','5','<c:out value="${SPECIFIC_SEARCH}"/>')"></a></th>
			</c:if>
			<c:if test="${not empty USUARIO_APP && USUARIO_APP.candidato}">
				<th>Fecha de publicaci&oacute;n <a id="fecha_orden_asc" style="display: inline;" class="ascendente" href="javascript:orderBy('asc','5','<c:out value="${SPECIFIC_SEARCH}"/>')"></a> <a id="fecha_orden_desc" style="display: inline;"  class="descendente" href="javascript:orderBy('desc','5','<c:out value="${SPECIFIC_SEARCH}"/>')"></a></th>
				<th class="fin">Compatibilidad</th>
			</c:if>
		</tr>
	<%
		int rowCounter = 0;
		while (it.hasNext()) {
			OfertaPorCanalVO ofertasCanal = it.next();
	%>
			<tr <% out.print(rowCounter % 2 == 0 ? "" : "class='odd'"); %>/>
				<td>
					<div class="profesion">
					<strong>
					<a href="<%=context%>detalleoferta.do?method=init&id_oferta_empleo=<% out.print(ofertasCanal.getIdOfertaEmpleo()); %>&searchType=<%out.print(tipoConsultaUrl); %>"><% out.print(ofertasCanal.getTituloOferta()); %></a>
					</strong>
					</div>
				</td>
				<td><% out.print(notNull(ofertasCanal.getEntidad()) + (ofertasCanal.getEntidad()!=null?", ":"") + notNull(ofertasCanal.getMunicipio())); %></td>
				<td><% out.print(notNull(ofertasCanal.getEmpresa())); %></td>
				<td><% out.print(notNull(ofertasCanal.getsSalario())); %></td>
				<td><% out.print(notNull(ofertasCanal.getFechaInicioString())); %></td>
				<c:if test="${not empty USUARIO_APP && USUARIO_APP.candidato}">
					<% if (ofertasCanal.getCompatibility() > 0) {%>
							<td style="text-align:center;"><% out.print(ofertasCanal.getCompatibility()); %> %</td>
					<% }else { %>
							<td style="text-align:center;">---</td>
					<% } %>
				</c:if>
			</tr>
			<tr <% out.print(rowCounter % 2 == 0 ? "" : "class='odd detalles'"); %>>
				<td colspan="6">
					<!--a href="javascript:mostrarResumen('<% out.print("" + rowCounter); %>')" class="expand"><div id="hide<% out.print("" + rowCounter); %>" style="display:none;">Ocultar resumen</div><div id="show<% out.print("" + rowCounter); %>" style="display:block;">Ver resumen</div></a-->
					<div  style="display:none;" id="<% out.print("" + rowCounter); %>">
						Solicitamos: <% out.print(ofertasCanal.getTituloOferta()); %> <br/>
						<% out.print((null!=ofertasCanal.getGradoEstudio() ? ofertasCanal.getGradoEstudio() : "")); %> <% out.print((null!=ofertasCanal.getCarrera() ? ofertasCanal.getCarrera() : "")); %> <br/>
						<% out.print(ofertasCanal.getFunciones()); %> Edad: <% out.print(ofertasCanal.getEdad()); %> a&ntilde;os, <br/>
						<% out.print(ofertasCanal.getIdiomas()); %> Horario de empleo: <% out.print(ofertasCanal.getHorario()); %>, <br/>
						N&uacute;mero de plazas: <% out.print(ofertasCanal.getNumeroPlazas()); %>. Medio para contactar
						la oferta: <% out.print(ofertasCanal.getMedioContacto()); %> -->
						<strong>Conocimientos y habilidades generales:</strong> <% out.print(ofertasCanal.getHabilidadGeneral()); %> 
						<strong>Experiencia:</strong> <% out.print(ofertasCanal.getExperiencia()); %>
						<% if(!ofertasCanal.getCompetencias().isEmpty()) out.print("<strong>Competencias:</strong> " + ofertasCanal.getCompetencias()); %> 
						<strong>Idiomas:</strong> <% out.print(ofertasCanal.getIdiomas()); %> 
						<strong>Rango de edad:</strong> <% out.print(ofertasCanal.getEdad()); if (!"No es requisito".equals(ofertasCanal.getEdad())) out.print(" años"); %>
					</div>
				</td>
			</tr>
	<%
				rowCounter++;
			}
  		}else {
  	%>
  			<span class="entero">
				<%=msg%>
			</span>
	<%
  		}
	%>
	</table>
<%
	 if (null != list && list.size() > 0) {
%>
		<!--p align="center">
			<a href="javascript:doSubmitPager('prev')">&lt;&lt;&lt;&nbsp;</a>
		    &nbsp;..&nbsp;<--%=ACTUAL_PAGES %>&nbsp;..&nbsp;
		    <a href="javascript:doSubmitPager('next')">&nbsp;&gt;&gt;&gt;</a>
		    &nbsp;&nbsp;de <--%=TOTAL_PAGES %> P&aacute;ginas
		</p>
		<p align="center">Mostrando <--%=NUM_RECORDS_VISIBLE %> de <--%=NUM_RECORDS_TOTAL %> Ofertas</p-->
		<jsp:include page="../portal/layout/pager.jsp" flush="true">
       		<jsp:param name="SUFIJO" value="<%=sufix%>"/>
       		<jsp:param name="tipoRegistros" value="ofertas"/>
		</jsp:include>
<%
	}
%>

<%!
	private String notNull(String msg){
		return msg==null?"":msg;
	}
%>
