<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 

<%

	//el sufijo solo se usa si se utilizan multiples tablas.
	// de no necesitarse se puede igualar a "" o simplemente eliminar toda referencia a la variable.
	String sufijo = "";
	//numero total de paginas del pager
	int paginaActual = 1;
	if(session.getAttribute("NUM_PAGE_LIST" + sufijo)!= null){
		paginaActual =((Integer) session.getAttribute("NUM_PAGE_LIST" + sufijo)).intValue();
	}
	/*
	//numero total de paginas del pager
	int totalPaginas = 0;
	if(session.getAttribute("TOTAL_PAGES" + sufijo)!= null){
		totalPaginas =((Integer) session.getAttribute("TOTAL_PAGES" + sufijo)).intValue();
	}
	//numero maximo de paginas mostradas en el paginador 
	int numeroPaginasMostrar = 0;
	if(session.getAttribute("PAGE_JUMP_SIZE" + sufijo)!= null){
		numeroPaginasMostrar =((Integer) session.getAttribute("PAGE_JUMP_SIZE" + sufijo)).intValue();
	}
	//multiplicador q indica cual es el rango de paginas mostrado actualmente
	//(ejemplo, si vale 3 y numeroPagina = 6 se mostrara desde la 19 a la 24)
	int saltoPagina = 0;
	if(session.getAttribute("NUM_PAGE_JUMP" + sufijo)!= null){
		saltoPagina =((Integer) session.getAttribute("NUM_PAGE_JUMP" + sufijo)).intValue();
	}
	*/
	int registrosVisibles = 0;
	if(session.getAttribute("NUM_RECORDS_VISIBLE" + sufijo)!= null){
		registrosVisibles =((Integer) session.getAttribute("NUM_RECORDS_VISIBLE" + sufijo)).intValue();
	}
	/*
	int registrosTotal = 0;
	if(session.getAttribute("NUM_RECORDS_TOTAL" + sufijo)!= null){
		registrosTotal =((Integer) session.getAttribute("NUM_RECORDS_TOTAL" + sufijo)).intValue();
	}
	//muestra los numeros de pagina correctanmente
	int sumarPaginas = numeroPaginasMostrar * saltoPagina;
	*/
%>

	<span class="entero">
		<div id="hideAll" style="display:none;"  align="right"><a href="javascript:mostrarTodas(2, <%=registrosVisibles%>)" class="expand">[Ocultar resumen de todas las ofertas]</a></div>
		<div id="showAll" style="display:block;" align="right"><a href="javascript:mostrarTodas(1, <%=registrosVisibles%>)" class="expand">[Ver resumen de todas las ofertas]</a></div>
	</span>
	<br>

	<table id="dataTable3" cellspacing="0" cellpadding="0" border="0" width="730px" style="float: none;">
		<tbody>

			<c:choose>
				<c:when test="${USUARIO_APP.administrador}">
					<tr class="temas">
				    	<th width="20%">ID Portal</th>
				    	<th width="30%">Usuario</th>
				    	<th width="15%">Nombre/Razón social</th>
				    	<th width="10%">Fecha de nacimiento/Fecha de acta</th>
				    	<th width="10%">Fecha de alta</th>
				    	<th width="15%">Contacto</th>
				    	<th width="15%">Tipo empresa</th>
				    	<th width="15%">Estatus</th>
				    	<th width="15%" class="fin">Desactivar/Activar</th>			
					</tr>					
				</c:when>
				<c:otherwise>
					<tr class="temas">
				    	<th width="20%">ID Portal</th>
				    	<th width="30%">Usuario</th>
				    	<th width="15%">Nombre /Razón social</th>
				    	<th width="10%">Fecha de nacimiento /Fecha de acta</th>
				    	<th width="10%">Fecha de alta</th>
				    	<th width="15%">Contacto</th>
				    	<th width="15%">Tipo empresa</th>
				    	<th width="15%" class="fin">Estatus</th>			
					</tr>									
				</c:otherwise>
			</c:choose>			
			
			<c:if test="${not empty PAGE_LIST}">
				<!-- <tr><td colspan="7">Resultado(s) encontrado(s): ${fn:length(FULL_LIST)}</td></tr> 	  -->
				
				<c:set var="sufijo" value="" />
				<c:set var="pageList" value="PAGE_LIST${sufijo}" />
				
				<c:forEach var="empresa" items="${sessionScope[pageList]}" varStatus="rowCounter">
				<tr <c:out value="${rowCounter.count % 2 == 0?'':'class=odd'}"/>>
				    <td> 
			        <c:choose>
			        	<c:when test="${empresa.tblEmpresa == 'EMPRESA'}">
			        		<a class="btn_portal" href="filtercompany.do?method=showCompany&idEmpresa=${empresa.idEmpresa}">${empresa.idPortalEmpleo}</a>
			        	</c:when>
			        	<c:otherwise>
			        		${empresa.idPortalEmpleo}
			        	</c:otherwise>
			        </c:choose>
				    </td>
					<td><b>${empresa.usuario}</b></td>
					<td>${empresa.nombre} ${empresa.apellido1} ${empresa.apellido2} ${empresa.razonSocial}</td>
			        <td>
						<c:if test="${not empty empresa.fechaNacimiento}">
							<fmt:formatDate value="${empresa.fechaNacimiento}" pattern="dd/MM/yyyy" />
			        	</c:if>		
						<c:if test="${not empty empresa.fechaActa}">
							<fmt:formatDate value="${empresa.fechaActa}" pattern="dd/MM/yyyy" />
			        	</c:if>
			        </td>
			        <td>
						<c:if test="${not empty empresa.fechaAlta}">
							<fmt:formatDate value="${empresa.fechaAlta}" pattern="dd/MM/yyyy" />
			        	</c:if>				        
			        </td>
			        <td>${empresa.contactoEmpresa}</td>
			        <td>
				        <c:choose>
				        	<c:when test="${empresa.tblEmpresa == 'EMPRESA'}">Empresa</c:when>
				        	<c:when test="${empresa.tblEmpresa == 'EMPRESA_POR_AUTORIZAR'}">Empresa por autorizar</c:when>
				        	<c:when test="${empresa.tblEmpresa == 'EMPRESA_FRAUDULENTA'}">Empresa fraudulenta</c:when>
				        </c:choose>
			        </td>
			        <td>${empresa.estatusDescripcion}
			        	<c:if test="${empresa.estatus == 14}">
			        		<br/><a class="btn_portal" href="javascript:publishConfirmation(${empresa.idEmpresa}, '${empresa.tblEmpresa}');" style="text-align: left;">[Recuperar]</a>
			        	</c:if>
			        </td>
			        			        
			        <c:if test="${USUARIO_APP.administrador or USUARIO_APP.publicador}">
			        		<c:choose>
				        		<c:when test="${empresa.estatus == 1 and empresa.esEmpresaSNE == 1}">
				        			<td><br/><a class="btn_portal" href="javascript:showWindowDesactivacion(${empresa.idEmpresa}, '${empresa.tblEmpresa}');" style="text-align: left;">[Desactivar]</a></td>				        			
				        		</c:when>
				        		<c:when test="${empresa.estatus == 1 and empresa.esEmpresaSNE == 2}">
				        			<td><br/><a class="btn_portal" href="javascript:warningSNECompany(${empresa.idEmpresa}, '${empresa.tblEmpresa}');" style="text-align: left;">[Desactivar]</a></td>
			        			</c:when>
			        			<c:when test="${empresa.estatus == 14 and empresa.esEmpresaSNE == 1}">
			        				<td><br/><a class="btn_portal" href="javascript:reactivateCompanyConfirmation(${empresa.idEmpresa}, '${empresa.tblEmpresa}');" style="text-align: left;">[Reactivar]</a></td>	
			        			</c:when>
			        			<c:otherwise>
			        				<td><br/>No aplica</td>
			        			</c:otherwise>
			        		</c:choose>			        
			        </c:if>
			        
			    </tr>
			    <tr class="detalles">
			    	<td colspan="7">
			    	<a href="javascript:mostrarResumen('${rowCounter.count}')" class="expand">
			    		<div id="hide${rowCounter.count}" style="display:none;">Ocultar resumen</div>
			    		<div id="show${rowCounter.count}" style="display:block;">Ver resumen</div>
			    	</a>
			    	<div  style="display:none;" id="${rowCounter.count}">
						<c:if test="${not empty empresa.fechaRevision}">
							<b>Fecha de revisión:&nbsp;</b><fmt:formatDate value="${empresa.fechaRevision}" pattern="dd/MM/yyyy"/><br/>
				        </c:if>				        
				    	<b>Publicador:&nbsp;</b>${empresa.nombrePublicador}
				    	<c:if test="${empresa.rechazada}">
				    	<br/>
				    	<b>Motivo:&nbsp;</b>${empresa.motivoRechazo}<br/>${empresa.detalleRechazo}
				    	</c:if>
				    	<c:if test="${not empty empresa.movimientos}">
				    	<br/>
				    	<b>Movimientos:</b>
				    	<ul>
				    	<c:forEach var="movimiento" items="${empresa.movimientos}">
				    		<li>
				    			<c:if test="${not empty movimiento.fechaEvento}">
								<fmt:formatDate value="${movimiento.fechaEvento.time}" pattern="dd/MM/yyyy hh:mm"/>&nbsp;-&nbsp;
				    			</c:if>
				    			${movimiento.descripcion}&nbsp;-->&nbsp;
				    			[${movimiento.correoElectronico} - ${movimiento.nombre} ${movimiento.apellido1} ${movimiento.apellido2} ]
				    		</li>
						</c:forEach>
						</ul>
						</c:if>
			    	</div>
			    	<br/>
			    	</td>
			    </tr>

			    </c:forEach>
		    </c:if>
		    <c:if test="${empty PAGE_LIST}">
		    	<tr><td colspan="7">No se encontraron registros con los criterios de búsqueda proporcionados</td></tr> 
		    </c:if>
		</tbody>	  
	</table>      

<jsp:include page="../layout/pager.jsp" flush="true">
         <jsp:param name="SUFIJO" value="${sufijo}"/>
         <jsp:param name="tipoRegistros" value="empresas"/>
</jsp:include>
</div>
