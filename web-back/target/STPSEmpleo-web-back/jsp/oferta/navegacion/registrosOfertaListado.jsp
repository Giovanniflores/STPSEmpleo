<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<table id="mis-ofertas" style="width:940px" class="margin_top_30">
	<caption>Mis ofertas de empleo</caption>
	<c:set var="sufijo" value="" />
	<c:set var="pageList" value="PAGE_LIST${sufijo}" />
	<tr id="ofertas-head">
	
		<c:if test="${!empty param.idCandidato && param.idCandidato > 0}">
			<th><div class="fl">Seleccionar</div>
				<div class="fl" style="height: 20px"></div>
			</th>
		</c:if>
		<th class="tipoDiscapacidad" style="width: 100px;">
			<div style="width: 74px" class="fl">
				Tipo de discapacidad
			</div>
			<div class="order-fix">
				<a id="discapacidades_orden_asc" style="display: inline;" class="ascendente" href="javascript:orderBy('asc','10')"></a>
				<a id="discapacidades_orden_desc" style="display: inline;"  class="descendente" href="javascript:orderBy('desc','10')"></a>
			</div>
		</th>		
		<th class="id">
			<div class="fl">
				Id
			</div>
			<div class="order-fix"> 
				<a id="id_orden_asc" style="display: inline;" class="ascendente" href="javascript:orderBy('asc','1')"></a> 
				<a id="id_orden_desc" style="display: inline;"  class="descendente" href="javascript:orderBy('desc','1')"></a>
			</div>
		</th>
    	<th class="titulo">
    		<div style="width: 80px" class="fl">
	    		T&iacute;tulo de la oferta 
	    	</div>
	    	<div class="order-fix">
		    	<a id="titulo_orden_asc" style="display: inline;" class="ascendente" href="javascript:orderBy('asc','2')"></a> 
		    	<a id="titulo_orden_desc" style="display: inline;"  class="descendente" href="javascript:orderBy('desc','2')"></a>
	    	</div>
    	</th>
        <th class="ocupacion">
	        <div class="fl">
	        	Área/Subárea
	        </div> 
	        <div class="order-fix">
		        <a id="ocupacion_orden_asc" style="display: inline;" class="ascendente" href="javascript:orderBy('asc','3')"></a> 
		        <a id="ocupacion_orden_desc" style="display: inline;"  class="descendente" href="javascript:orderBy('desc','3')"></a>
	        </div>
        </th>
        <th style="width: 89px" class="nivel">
        	<div style="width: 49px" class="fl">
	        	Nivel de estudios
	        </div>
	        <div class="order-fix"> 
		        <a id="nivel_orden_asc" style="display: inline;" class="ascendente" href="javascript:orderBy('asc','4')"></a> 
		        <a id="nivel_orden_desc" style="display: inline;"  class="descendente" href="javascript:orderBy('desc','4')"></a>
	        </div>
        </th>
        <th class="carrera">
        	<div class="fl">
	        	Carrera 
	        </div>
	        <div class="order-fix">
		        <a id="carrera_orden_asc" style="display: inline;" class="ascendente" href="javascript:orderBy('asc','5')"></a> 
		        <a id="carrera_orden_desc" style="display: inline;"  class="descendente" href="javascript:orderBy('desc','5')"></a>
	        </div>
        </th>
        <th class="ubicacion">
        	<div class="fl">
	        	Ubicaci&oacute;n
	        </div>
	        <div class="order-fix"> 
		        <a id="ubicacion_orden_asc" style="display: inline;" class="ascendente" href="javascript:orderBy('asc','6')"></a> 
		        <a id="ubicacion_orden_desc" style="display: inline;"  class="descendente" href="javascript:orderBy('desc','6')"></a>
	        </div>
        </th>
        <th class="fecha" >
        	<div class="fl">
	        	Fecha <br/>de alta
	        </div>
	        <div class="order-fix"> 
		        <a id="fecha_orden_asc" style="display: inline;" class="ascendente" href="javascript:orderBy('asc','7')"></a> 
		        <a id="fecha_orden_desc" style="display: inline;"  class="descendente" href="javascript:orderBy('desc','7')"></a>
	        </div>
        </th> 
        <th style="width: 76px" class="estatus">
        	<div style="width: 41px" class="fl">
	        	Estatus 
	        </div>
	        <div class="order-fix">
		        <a id="estatus_orden_asc" style="display: inline;" class="ascendente" href="javascript:orderBy('asc','8')"></a> 
		        <a id="estatus_orden_desc" style="display: inline;"  class="descendente" href="javascript:orderBy('desc','8')"></a>
	        </div>
        </th> 
         <th style="width: 76px" class="tipoOferta">
        	<div style="width: 41px" class="fl">
	        	Tipo de oferta 
	        </div>
	        <div class="order-fix">
		       	<a id="tipo_oferta_orden_asc" style="display: inline;" class="ascendente" href="javascript:orderBy('asc','11')"></a>
				<a id="tipo_oferta_orden_desc" style="display: inline;"  class="descendente" href="javascript:orderBy('desc','11')"></a>
	        </div>
        </th> 
    </tr>
	<c:forEach var="oferta" items="${sessionScope[pageList]}" varStatus="rowCounter">
	
	<tr class="datos">

		<c:if test="${!empty param.idCandidato && param.idCandidato > 0}">
			<td>
 				<input type="radio" data-dojo-type="dijit/form/RadioButton" name="radioOferta" id="radioOferta" value="${oferta.idOfertaEmpleo}"/>
			</td>
		</c:if>
	
		<td style="text-align: center" class="tipoDiscapacidad"><c:out value="${oferta.descripcionesDiscapacidades}"/></td>
		<td style="text-align: center" class="id"><c:out value="${oferta.idOfertaEmpleo}"/></td>
		<td class="titulo">
			<c:choose>
				<c:when test="${oferta.estatus == ESTATUS_ACTIVO}">
					<a href="${pageContext.request.contextPath}/admonCandidatos.do?method=init&ido=${oferta.idOfertaEmpleo}" title="Ver detalle">
				  		<c:out value="${oferta.tituloOferta}"/>
	              	</a>				
				</c:when>	
				<c:when test="${oferta.estatus == ESTATUS_ABRIENDO_ESPACIOS_ACTIVO}">
					<a href="${pageContext.request.contextPath}/admonCandidatos.do?method=init&ido=${oferta.idOfertaEmpleo}" title="Ver detalle">
				  		<c:out value="${oferta.tituloOferta}"/>
	              	</a>				
				</c:when>								
				<c:otherwise>
					<a href="${pageContext.request.contextPath}/detalleoferta.do?method=init&id_oferta_empleo=${oferta.idOfertaEmpleo}" title="Ver detalle">
				  		<c:out value="${oferta.tituloOferta}"/>
	              	</a>
				</c:otherwise>
			</c:choose>                       
		</td> 
		<td class="ocupacion"><c:out value="${oferta.areaLaboralDescripcion} ${oferta.subAreaLaboralDescripcion}"/></td>
		<td class="nivel"><c:out value="${oferta.nivelEstudiosDescripcion}"/></td>
		<td class="carrera"><c:out value="${oferta.carreraDescripcion}"/></td>
		<td class="ubicacion"><c:out value="${oferta.entidadDescripcion} / ${oferta.municipioDescripcion}"/></td>
		<td class="fecha"><fmt:formatDate value="${oferta.fechaAlta}" pattern="dd/MM/yyyy"/></td> 
		<td class="estatus">	 
 			<c:choose> 
 				<c:when test="${oferta.estatus == ESTATUS_ACTIVO}"> 
 					<c:out value="Activa"/> 
 				</c:when> 
 				<c:when test="${oferta.estatus == ESTATUS_ABRIENDO_ESPACIOS_ACTIVO}"> 
 					<c:out value="Activa sólo para Abriendo Espacios"/> 
 				</c:when>  				
 				<c:otherwise> 
 					<c:out value="Cancelada"/> 
 				</c:otherwise> 
			</c:choose> 
		</td> 
		<td class="tipoOferta">	 
 			<c:choose> 
 				<c:when test="${oferta.tipoOferta == OFERTA_NORMAL}"> 
 					<c:out value="Oferta Normal"/> 
 				</c:when>	
 				<c:when test="${oferta.tipoOferta == OFERTA_BECATE}"> 
 					<c:out value="Oferta Bécate"/> 
 				</c:when>	
 				<c:otherwise> 
 					<c:out value=""/> 
 				</c:otherwise> 
			</c:choose> 
		</td> 
	</tr>
	
	<tr class="opciones">	
		<td colspan="10">
		
		<c:choose>
		<c:when test="${empty param.idCandidato || param.idCandidato == 0}">
		<ul>

				  <li>
					<c:choose>
					  <c:when test="${oferta.totalCandidatos > 0 || oferta.totalPostulados > 0}">
						<a href="${pageContext.request.contextPath}/admonCandidatos.do?method=init&ido=${oferta.idOfertaEmpleo}">Postulantes(<c:out value="${oferta.totalPostulados}"/>)</a>		      
					  </c:when>
					  <c:otherwise>
						<a>Postulantes(<c:out value="${oferta.totalCandidatos + oferta.totalPostulados}"/>)</a>
					  </c:otherwise>
					</c:choose>
				  </li>
				  <li>
					<c:choose>
					  <c:when test="${oferta.numeroPreguntas != 0}">
						<a href="${pageContext.request.contextPath}/detalleoferta.do?method=questionList&amp;id_oferta_empleo=${oferta.idOfertaEmpleo}">Responder preguntas (<c:out value="${oferta.numeroPreguntas}"/>)</a>		      
					  </c:when>
					  <c:otherwise>
						<a>Responder preguntas (<c:out value="${oferta.numeroPreguntas}"/>)</a>
					  </c:otherwise>
					</c:choose>
				  </li>
				  <li><a target="_blank" href="${pageContext.request.contextPath}/registro-unico.do?method=redirectEditaOfertaRU&amp;id=${oferta.idOfertaEmpleo}&amp;idEvento=69&amp;tipoOferta=${oferta.tipoOferta}">Editar</a></li>
				  <li><a target="_blank" href="${pageContext.request.contextPath}/registro-unico.do?method=redirectUsarPlangillaRU&amp;id=${oferta.idOfertaEmpleo}&amp;idEvento=69&amp;tipoOferta=${oferta.tipoOferta}">Usar como plantilla</a></li>
				  <c:choose>
					<c:when test="${oferta.estatus == ESTATUS_ACTIVO}">
					<li><a href="#" onclick="cancelaOferta('OfertaNavegacion.do?method=cancelaOferta&amp;id=${oferta.idOfertaEmpleo}')">Desactivar</a></li>				
					</c:when>
					<c:when test="${oferta.estatus == ESTATUS_ABRIENDO_ESPACIOS_ACTIVO}">
					<li><a href="#" onclick="cancelaOferta('OfertaNavegacion.do?method=cancelaOferta&amp;id=${oferta.idOfertaEmpleo}')">Desactivar</a></li>				
					</c:when>					
					<c:otherwise>
					<li><a href="#" onclick="activaOferta('OfertaNavegacion.do?method=activaOferta&amp;id=${oferta.idOfertaEmpleo}')">Activar</a></li>				
					</c:otherwise>				
				  </c:choose>      
				  <li><a href="#" onclick="eliminaOferta('OfertaNavegacion.do?method=eliminaOferta&amp;id=${oferta.idOfertaEmpleo}')">Eliminar</a></li>          
				
				  <li width="170px">
					<c:if test="${oferta.estatus == ESTATUS_ACTIVO}">
						<a href="${pageContext.request.contextPath}/detalleOfertaCreada.do?method=init&ido=${oferta.idOfertaEmpleo}">Candidatos para esta oferta</a>
					</c:if>
					<c:if test="${oferta.estatus == ESTATUS_ABRIENDO_ESPACIOS_ACTIVO}">
						<a href="${pageContext.request.contextPath}/detalleOfertaCreada.do?method=init&ido=${oferta.idOfertaEmpleo}">Candidatos para esta oferta</a>
					</c:if>					
				  </li>
	
		</ul>
		</c:when>
		</c:choose>
		</td>
	</tr>				
	</c:forEach>
</table>

<p align="center">
<jsp:include page="../../layout/pager.jsp" flush="true">
         <jsp:param name="SUFIJO" value="${sufijo}"/>
         <jsp:param name="tipoRegistros" value="ofertas"/>         
</jsp:include>
</p>
