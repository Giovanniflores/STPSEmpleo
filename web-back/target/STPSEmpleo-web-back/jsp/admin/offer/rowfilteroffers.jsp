<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<script type="text/javascript">
function doSubmitPager(method){
	  dojo.byId('method').value = method;	 
	  dojo.xhrPost({url: 'filteroffer.do', form:'FilterOfferForm', 
	  	load: function(data) {
	  		dojo.byId('table').innerHTML=data;
		}});  
	}
	
	
	
		function doSubmitPagina(pagina, dif) {
    		dojo.xhrPost({url: 'filteroffer.do?method=goToPage&tablaPager=_OFEPUBLISH&goToPageNumber='+pagina, timeout:180000, 
				load: function(dataCand){
    	 			dojo.byId('table').innerHTML=dataCand;
    	 			
			}});
    }
    
</script>
<span class="entero">
	<div id="hideAll" style="display:none;"  align="right"><a href="javascript:mostrarTodas(2, ${NUM_RECORDS_VISIBLE_OFEPUBLISH})" class="expand">[Ocultar resumen de todas las ofertas]</a></div>
	<div id="showAll" style="display:block;" align="right"><a href="javascript:mostrarTodas(1, ${NUM_RECORDS_VISIBLE_OFEPUBLISH})" class="expand">[Ver resumen de todas las ofertas]</a></div>
</span>	    	
<table id="dataTable3" class="offer table table-striped" cellspacing="0" cellpadding="0" border="0" width="730px">
	<tbody>
		<tr class="temas">
			<c:if test="${filterAdminPublisher}">
			<TH width="5%"></TH>
			</c:if>
			<TH width="5%">Folio</TH>
		    <TH width="30%">Título</TH>
		    <TH width="20%">Empresa</TH>
		    <TH width="15%">Fecha de alta</TH>
		    <TH width="15%">Fecha de modificación</TH>
		    <TH width="15%">Estatus</TH>
		    <TH width="50%">Detalle</TH>		    		
		</tr>
				<c:if test="${total == 0}">
			 <tr><td colspan="7">No se encontraron registros con los criterios de búsqueda proporcionados</td></tr> 
		</c:if>
		
			<c:set var="sufijo" value="_OFEPUBLISH" />
			<c:set var="pageList" value="PAGE_LIST${sufijo}" />
	
			<c:forEach var="offer" items='<%= request.getSession(false).getAttribute("PAGE_LIST_OFEPUBLISH") %>' varStatus="rowCounter">		
				<tr <c:out value="${rowCounter.count % 2 == 0?'':'class=odd'}"/> >
					<c:if test="${filterAdminPublisher}">
					<td><input type="checkbox" name="idRegistro_${rowCounter.count}" id="idRegistro_${rowCounter.count}" value="${offer.idRegistroPorValidar}"/></td>
					</c:if>
					<td>${offer.idOfertaEmpleo}</td>
					
					<td><a href="<c:url value="/detalleoferta.do?method=init&id_oferta_empleo=${offer.idOfertaEmpleo}"/>">${offer.tituloOferta}</a></td>
		    		<td>${offer.empresa}</td>
		    		<td><fmt:formatDate value="${offer.fechaInicio}" pattern="dd/MM/yyyy" /></td>
		    		<td><fmt:formatDate value="${offer.fechaFin}" pattern="dd/MM/yyyy" /></td>
		    		<td>${offer.funciones}</td>
					<td>
						<c:if test="${offer.idRegistroPorValidar ne 0}">
							<a class="btn_portal" href="javascript:showWindowOffer(${offer.idRegistroPorValidar});">Ver detalle...</a>
						</c:if>
					</td>
				</tr>
				
				<tr class="detalles">
					<td colspan="6">
						<a href="javascript:mostrarResumen('${rowCounter.count}')" class="expand">
							<div id="hide${rowCounter.count}" style="display:none;">Ocultar resumen</div>
			    			<div id="show${rowCounter.count}" style="display:block;">Ver resumen</div>
						</a>
						<div  style="display:none;" id="${rowCounter.count}">
				    		<c:if test="${not empty offer.motivoRechazo}">
				    			<br/>
				    			<strong>Motivo:&nbsp;</strong>${offer.motivoRechazo}<br/>${offer.detalleMotivoRechazo}
				    		</c:if>
							<c:if test="${not empty offer.bitacora}">
								<ul>
									<c:if test="${not empty offer.bitacora}">
									<strong>Movimientos:</strong></br>
									</c:if>
									<c:forEach var="movimiento" items="${offer.bitacora}">
										<li>
											<c:if test="${not empty movimiento.fechaEvento}">
												<fmt:formatDate value="${movimiento.fechaEvento.time}" pattern="dd/MM/yyyy hh:mm"/>&nbsp;-&nbsp;
												${movimiento.descripcion}&nbsp;-->&nbsp;
				    					    	[${movimiento.correoElectronico} - ${movimiento.nombre} ${movimiento.apellido1} ${movimiento.apellido2} ]
											</c:if>
										</li>
									</c:forEach>
								</ul>
							</c:if>
							<c:if test="${empty offer.bitacora}">
							Estatus&nbsp;-->&nbsp;${offer.funciones}
							</c:if>
						</div>
					</td>
				</tr>
			</c:forEach>
			
		
		
	</tbody>	  
</table>

<jsp:include page="../../layout/pager.jsp" flush="true">
         <jsp:param name="SUFIJO" value="${sufijo}"/>
         <jsp:param name="tipoRegistros" value="ofertas"/>		 
</jsp:include>

<c:if test="${filterAdminPublisher}">
			<span class="entero" align="center">
			<input type="button" id="btnBuscar" value="Publicar seleccionadas"  class="boton" onclick="javascript:doSubmitPublicar();" />
			</span>
			</c:if>

<script>
		doSubmitPager('page');
	</script>
