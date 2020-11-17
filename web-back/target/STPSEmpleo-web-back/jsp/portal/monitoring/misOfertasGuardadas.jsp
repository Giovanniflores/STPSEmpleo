<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<c:set var="sufijo" value="_MIS_OFERTAS_GUARDADAS" />

<c:set var="pageList" value="PAGE_LIST${sufijo}" />
<c:set var="totalOfertas" value="${fn:length(sessionScope[pageList])}"/>

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
            Mis ofertas guardadas
        </div>

        <table class="seleccionados" cellspacing="0" cellpadding="0" border="0" width="100%">
            <tr class="temas">
                <th>Puesto</th>
                <th>Estatus de oferta</th>
                <th>Ubicaci&oacute;n</th>
                <th>Empresa</th>
                <th>Vigencia</th>
                <th>Estatus</th>
                <th class="fin" style="text-align: center; width: 90px">
                    <input type="checkbox" name="allCheck" value="" onClick="callable(this.form,'myoffers')" />
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
                    <c:set var="dimmed" value="${false}"/>

                    <c:forEach var="oferta" items="${sessionScope[pageList]}" varStatus="rowMeter">
                        <c:if test="${oferta.estatus ne 1}">
                            <c:set var="dimmed" value="${not dimmed}"/>
                        </c:if>

                        <%--<c:choose>--%>
                            <%--<c:when test="${oferta.estatus eq estatusActivoIdOpcion}">--%>
                                <%--<c:set var="status" value="activa"/>--%>
                            <%--</c:when>--%>
                            <%--<c:otherwise>--%>
                                <%--<c:set var="status" value="inactiva"/>--%>
                            <%--</c:otherwise>--%>
                        <%--</c:choose>--%>
                        <%--<c:url var="urlDetalleOferta" value="detalleoferta.do">--%>
                            <%--<c:param name="method" value="init"/>--%>
                            <%--<c:param name="id_oferta_empleo" value="${oferta.idOfertaEmpleo}"/>--%>
                        <%--</c:url>--%>

                        <!-- registros sobre los que se pagina-->
                        <%--<tr class="${odd ? 'odd' : 'null'}">--%>
                            <%--<td>--%>
                                <%--<div class="profesion">--%>
                                    <%--<strong><a href="${urlDetalleOferta}"><c:out value="${oferta.tituloOferta}"/></a></strong><br />--%>
                                <%--</div>--%>
                            <%--</td>--%>
                            <%--<td>--%>
                                <%--<div class="${status}"><span><c:out value="${status}"/></span></div>--%>
                            <%--</td>--%>
                            <%--<td><c:out value="${oferta.ubicacion}"/></td>--%>
                            <%--<td><c:out value="${oferta.empresaNombre}"/></td>--%>
                            <%--<td><c:out value="${oferta.fechaFin}"/></td>--%>
                            <%--<td><c:out value="${oferta.estatusOfertaCandidato}"/></td>--%>
                            <%--<td style="text-align: center; width: 90px">--%>
                                <%--<input name="myoffers" type="checkbox" value="${oferta.idOfertaCandidato}" />--%>
                            <%--</td>--%>
                        <%--</tr>--%>

                        <tr class="${odd ? 'odd' : 'null'}${dimmed ? ' dimmed' : ''}">
                            <td>
                                <div class="profesion">
                                    <strong>
                                        <c:choose>
                                            <c:when test="${oferta.estatus eq 1}"><%--Catalogos.ESTATUS.ACTIVO--%>
                                                <%-- Only show the url is job status is 'activa' --%>
                                                <c:url var="urlDetalleOferta" value="detalleoferta.do"><!-- TODO: Here's the URL for viewing jobOffer detail-->
                                                    <c:param name="method" value="init"/>
                                                    <c:param name="id_oferta_empleo" value="${oferta.idOfertaEmpleo}"/>
                                                </c:url>
                                                <a href="${urlDetalleOferta}"><c:out value="${oferta.tituloOferta}"/></a>
                                            </c:when>
                                            <c:otherwise>
                                                <%-- Show job name --%>
                                                <c:out value="${oferta.tituloOferta}"/>
                                            </c:otherwise>
                                        </c:choose>
                                    </strong>
                                    <br />
                                </div>
                            </td>
                            <td>
                                <div class="${status}"><span><c:out value="${oferta.estatusEnum.getOpcion()}"/></span></div>
                            </td>
                            <td><c:out value="${oferta.ubicacion}"/></td>
                            <td><c:out value="${oferta.empresaNombre}"/></td>
                            <td><c:out value="${oferta.fechaFin}"/></td>
                            <td><c:out value="${oferta.estatusOfertaCandidato}"/></td>
                            <td style="text-align: center; width: 90px">
                                <input name="myoffers" type="checkbox" value="${oferta.idOfertaCandidato}" />
                            </td>
                        </tr>

                        <%--<tr class="detalles ${odd ? 'odd' : ''}">--%>
                        <tr class="detalles ${dimmed ? ' dimmed' : ''}">
                            <td colspan="6">
                                <%--<a href="javascript:mostrarResumen('offers${rowCounter.count}')" class="expand">--%>
                                    <%--<div id="hideoffer${rowCounter.count}" style="display:none;">Ocultar resumen</div>--%>
                                    <%--<div id="showoffer${rowCounter.count}" style="display:block;">Ver resumen</div>--%>
                                <%--</a>--%>
                                <div <%--style="display:none;"--%> id="offers${rowMeter.count}">
                                    <p><strong>Resumen:</strong></p>
                                    <c:out value="${oferta.tituloOferta}"/>&nbsp;
                                    <c:out value="${oferta.gradoEstudios}"/>,&nbsp;
                                    <c:out value="${oferta.especialidades}"/>,&nbsp;
                                    <c:if test="${not empty oferta.funciones}">
                                        <c:out value="${oferta.funciones}"/>,&nbsp;
                                    </c:if>
                                    <c:if test="${oferta.edadRequisito eq edadRequisitoSiIdOpcion}">
                                        <c:out value="Edad: ${oferta.edadMinima}-${oferta.edadMaxima} años"/>,&nbsp;
                                    </c:if>
                                    <c:if test="${not empty oferta.idiomas}">
                                        <c:out value="Idiomas: ${oferta.idiomas}"/>&nbsp;
                                    </c:if>
                                    Tipo de empleo:&nbsp;<c:out value="${oferta.tipoEmpleo}"/>,&nbsp;
                                    N&uacute;mero de plazas:&nbsp;<c:out value="${oferta.numeroPlazas}"/>,&nbsp;
                                    Medio para contactar la oferta:&nbsp;<c:out value="${oferta.medioContacto}"/>,&nbsp;
                                </div>
                            </td>
                            <td></td>
                        </tr>
                        <c:set var="odd" value="${not odd}"/>

                    </c:forEach>
                    <tr>
                        <td colspan="6">&nbsp;</td>
                        <td style="text-align: center; width: 90px" align="center">
                            <input type="submit" class="boton" name="" value="Eliminar" onclick="javascript: if(hayOfertasSeleccionadas()){if (confirm('¿Está seguro que desea eliminar las ofertas seleccionadas?')){return true;} else return false;}else return false;">
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
