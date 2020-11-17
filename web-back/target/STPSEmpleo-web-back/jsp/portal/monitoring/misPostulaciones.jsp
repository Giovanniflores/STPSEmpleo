<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<c:set var="sufijo" value="_MIS_POSTULACIONES" />

<c:set var="pageList" value="PAGE_LIST${sufijo}" />
<c:set var="totalOfertas" value="${fn:length(sessionScope[pageList])}"/>

<!-- Variables just for PPC-SD -->
<c:if test="${MyOffersForm.activeToPpc || MyOffersForm.inactiveToPpc}">
    <c:set var="enrolledToPpc" value="${true}"/>
</c:if>

<form name="myopportunities" id="myopportunities" action="misofertas.do" method="post">
    <input type="hidden" name="method" id="method" value="remove"/>
    <div class="publicados miEspacio">

        <%--<div id="hideAlloffers" style="display:none;"  align="left">--%>
            <%--<a href="javascript:mostrarTodas(2, ${totalOfertas}, 'offers')" class="expand">[Ocultar resumen de todas las ofertas]</a>--%>
        <%--</div>--%>

        <%--<div id="showAlloffers" style="display:block;" align="left">--%>
            <%--<a href="javascript:mostrarTodas(1, ${totalOfertas}, 'offers')" class="expand">[Ver resumen de todas las ofertas]</a>--%>
        <%--</div>--%>

        <div class="sublevelTitle">
            Ofertas a las que me postul&eacute;
        </div>

        <table class="seleccionados" cellspacing="0" cellpadding="0" border="0" width="100%">
            <tr class="temas">
                <th>
                    <div class="fl ccpt">Puesto</div>
                    <div class="fl level">
	                    <a id="puesto_orden_asc" title="Orden Ascendente" style="display: inline;" class="ascendente" href="javascript:orderBy('asc','puesto')"></a>
	                    <a id="puesto_orden_desc" title="Orden Descendente" style="display: inline;"  class="descendente" href="javascript:orderBy('desc','puesto')"></a>
                    </div>
                </th>
                <th>
                	<div class="fl ccpt">Empresa</div>
                	<div class="fl level">
	                    <a id="empresa_orden_asc" title="Orden Ascendente" style="display: inline;" class="ascendente" href="javascript:orderBy('asc','empresa')"></a>
	                    <a id="empresa_orden_desc" title="Orden Descendente" style="display: inline;"  class="descendente" href="javascript:orderBy('desc','empresa')"></a>
                    </div>
                </th>
                <th>
                	<div class="fl ccpt">Ubicaci&oacute;n</div>
                	<div class="fl level">
	                    <a id="ubicacion_orden_asc" title="Orden Ascendente" style="display: inline;" class="ascendente" href="javascript:orderBy('asc','ubicacion')"></a>
	                    <a id="ubicacion_orden_desc" title="Orden Descendente" style="display: inline;"  class="descendente" href="javascript:orderBy('desc','ubicacion')"></a>
                    </div>
                </th>
                <th>
                	<div style="width: 69px" class="fl ccpt">Vigencia de la oferta</div>
                	<div class="fl level">
	                    <a id="vigencia_orden_asc" title="Orden Ascendente" style="display: inline;" class="ascendente" href="javascript:orderBy('asc','vigencia')"></a>
	                    <a id="vigencia_orden_desc" title="Orden Descendente" style="display: inline;"  class="descendente" href="javascript:orderBy('desc','vigencia')"></a>
                    </div>
                </th>
                <th>
                	<div class="fl ccpt">Estatus</div>
                	<div class="fl level">
	                    <a id="estatus_orden_asc" title="Orden Ascendente" style="display: inline;" class="ascendente" href="javascript:orderBy('asc','estatus')"></a>
	                    <a id="estatus_orden_desc" title="Orden Descendente" style="display: inline;"  class="descendente" href="javascript:orderBy('desc','estatus')"></a>
                    </div>
                </th>
                <!--
	                <c:if test="${MyOffersForm.ofertasContratadoPpc < 1}">
	                    <th>
	                        <div class="fl ccpt">Dar seguimiento</div>
	                    </th>
	                </c:if>
	            -->
	            <th class="fin" style="text-align: center;">
	            	<div class="ccpt"><input style="margin-left: 10px;" type="checkbox" name="allCheck" value="" class="${enrolledToPpc ? 'disabled' : ''}" ${enrolledToPpc ? 'disabled="disabled"' : ''} onClick="callable(this.form,'myoffers')" /></div>
	            </th>
            </tr>
            <c:choose>
                <c:when test="${empty sessionScope[pageList]}">
                    <!-- si no hay registros que mostrar -->
                    <tr class="detalles"><td colspan="7"><div class="gris" style="text-align: center; font-weight: bold">No tiene ofertas de empleo seleccionadas o d&oacute;nde se haya postulado</div></td></tr>
                </c:when>
                <c:otherwise>
                    <!-- mostramos el resultado de la consulta -->
                    <c:set var="odd" value="${true}"/>

                    <c:forEach var="ofertas" items="${sessionScope[pageList]}" varStatus="rowMeter">

                        <c:choose>
                            <c:when test="${ofertas.estatus eq estatusActivoIdOpcion}">
                                <c:set var="status" value="activa"/>
                            </c:when>
                            <c:otherwise>
                                <c:set var="status" value="inactiva"/>
                            </c:otherwise>
                        </c:choose>
                        <c:url var="urlDetalleOferta" value="/detalleoferta.do">
                            <c:param name="method" value="init"/>
                            <c:param name="id_oferta_empleo" value="${ofertas.idOfertaEmpleo}"/>
                        </c:url>

                        <!-- registros sobre los que se pagina-->
                        <tr class="${odd ? 'odd' : 'null'}">
                            <td>
                                <div class="profesion">
                                    <strong><a href="${urlDetalleOferta}"><c:out value="${ofertas.tituloOferta}"/></a></strong>
                                </div>
                            </td>
                            <%--<td>--%>
                                <%--<div class="${status}"><span><c:out value="${status}"/></span></div>--%>
                            <%--</td>--%>
                            <td><c:out value="${ofertas.empresaNombre}"/></td>
                            <td><c:out value="${ofertas.ubicacion}"/></td>
                            <td><c:out value="${ofertas.fechaFin}"/></td>
                            <td><c:out value="${ofertas.estatusOfertaCandidato}"/></td>
                            <!--
	                            <c:if test="${MyOffersForm.ofertasContratadoPpc < 1}">
	                                <td>
	                                    <c:if test="${(ofertas.estatusOfertaCandidato ne 'Contratado') and (ofertas.estatusOfertaCandidato ne 'Contratado en otra oferta') and (ofertas.estatusOfertaCandidato ne 'No aceptado')}">
	                                        <a href="<%=request.getContextPath()%>/seguimientoPostulacion.do?method=init&idOfertaCandidato=${ofertas.idOfertaCandidato}">Dar seguimiento a esta postulaci&oacute;n</a>
	                                    </c:if>
	                                </td>
	                            </c:if>
	                        -->
                            <td style="text-align: center; width: 90px">
                                <c:set var="checkboxDisabled" value="${enrolledToPpc || ofertas.estatusOfertaCandidato != 'Postulado' ? true : false}"/>
                                <input name="myoffers" type="checkbox" value="${ofertas.idOfertaCandidato}" class="${checkboxDisabled ? 'disabled' : ''}" ${checkboxDisabled ? 'disabled="disabled"' : ''}/>
                            </td>
                        </tr>
                        <tr class="detalles ${odd ? 'odd' : ''}">
                            <td colspan="${(MyOffersForm.ofertasContratadoPpc == 0) ? 6 : 5}">
                                <%--<a href="javascript:mostrarResumen('offers${rowCounter.count}')" class="expand">--%>
                                    <%--<div id="hideoffer${rowCounter.count}" style="display:none;">Ocultar resumen</div>--%>
                                    <%--<div id="showoffer${rowCounter.count}" style="display:block;">Ver resumen</div>--%>
                                <%--</a>--%>
                                <div <%--style="display:none;"--%> id="offers${rowMeter.count}">
                                    <p><strong>Resumen:</strong></p>
                                    <c:out value="${ofertas.tituloOferta}"/>;&nbsp;
                                    <c:out value="${ofertas.gradoEstudios}"/>-<c:out value="${ofertas.especialidades}"/>;&nbsp;
                                    <c:if test="${not empty ofertas.funciones}">
                                        <c:out value="${ofertas.funciones}"/>;&nbsp;
                                    </c:if>
                                    <%--<c:if test="${ofertas.edadRequisito eq edadRequisitoSiIdOpcion}">--%>
                                        <%--<c:out value="Edad: ${ofertas.edadMinima}-${ofertas.edadMaxima} años"/>,&nbsp;--%>
                                    <%--</c:if>--%>
                                    <c:if test="${not empty ofertas.idiomas}">
                                        <c:out value="Idiomas: ${ofertas.idiomas}"/>;&nbsp;
                                    </c:if>
                                    <%--Tipo de empleo:&nbsp;<c:out value="${ofertas.tipoEmpleo}"/>,&nbsp;--%>
                                    <c:out value="${ofertas.numeroPlazas}"/>&nbsp; plaza<c:if test="${ofertas.numeroPlazas != '1'}">s</c:if>;
                                    <c:out value="${ofertas.medioContacto}"/>;&nbsp;
                                    <c:out value="${ofertas.habilidadesConcatenadas}"/>.
                                </div>
                            </td>
                            <td></td>
                        </tr>
                        <c:set var="odd" value="${not odd}"/>

                    </c:forEach>
                    <tr>
                        <td colspan="${(MyOffersForm.ofertasContratadoPpc < 1) ? 6 : 5}">&nbsp;</td>
                        <td style="text-align: center; width: 90px" align="center">
                            <input type="submit" class="boton ${enrolledToPpc || MyOffersForm.ofertasContratadoPpc > 0 ? 'disabled' : ''}" name="" value="Eliminar" ${enrolledToPpc || MyOffersForm.ofertasContratadoPpc > 0 ? 'disabled="disabled"' : ''} onclick="javascript: if(hayOfertasSeleccionadas()){if (confirm('¿Está seguro que desea eliminar las ofertas seleccionadas?')){return true;} else return false;}else return false;">
                        </td>
                    </tr>

                </c:otherwise>
            </c:choose>
        </table>
        <br/>

    </div>
</form>

<jsp:include page="../../layout/pager.jsp" flush="true">
    <jsp:param name="SUFIJO" value="${sufijo}"/>
    <jsp:param name="tipoRegistros" value="ofertas"/>
</jsp:include>
