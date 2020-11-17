<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<c:set var="sufijo" value="_EMPRESAS_QUE_ME_BUSCAN" />
<c:set var="pageList" value="PAGE_LIST${sufijo}" />
<c:set var="totalOfertas" value="${fn:length(sessionScope[pageList])}" />

<form name="myopportunities" id="myopportunities" action="misofertas.do" method="post">
    <input type="hidden" name="method" id="method" value="remove"/>
    <div class="publicados miEspacio">

        <%--<div id="hideAlloffersFindMe" style="display:none;"  align="left">--%>
            <%--<a href="javascript:mostrarTodas(2, ${totalOfertas}, 'offersFindMe')" class="expand">[Ocultar resumen de todas las ofertas]</a>--%>
        <%--</div>--%>

        <%--<div id="showAlloffersFindMe" style="display:block;" align="left">--%>
            <%--<a href="javascript:mostrarTodas(1, ${totalOfertas}, 'offersFindMe')" class="expand">[Ver resumen de todas las ofertas]</a>--%>
        <%--</div>--%>

        <div class="sublevelTitle">
            Empresas que me buscan
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
                    <tr class="detalles"><td colspan="7"><div class="gris" style="text-align: center; font-weight: bold">No tiene ofertas de empleo de empresas que lo hayan vinculado</div></td></tr>
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
                        <c:url var="urlDetalleOferta" value="detalleoferta.do">
                            <c:param name="method" value="init"/>
                            <c:param name="id_oferta_empleo" value="${ofertas.idOfertaEmpleo}"/>
                        </c:url>

                        <!-- registros sobre los que se pagina-->
                        <tr class="${odd ? 'odd' : 'null'}">
                            <td>
                                <div class="profesion">
                                    <strong><a href="${urlDetalleOferta}"><c:out value="${ofertas.tituloOferta}"/></a></strong><br />
                                </div>
                            </td>
                            <td>
                                <div class="${status}"><span><c:out value="${status}"/></span></div>
                            </td>
                            <td><c:out value="${ofertas.ubicacion}"/></td>
                            <td><c:out value="${ofertas.empresaNombre}"/></td>
                            <td><c:out value="${ofertas.fechaFin}"/></td>
                            <td><c:out value="${ofertas.estatusOfertaCandidato}"/></td>
                            <td style="text-align: center; width: 90px">
                                <input name="myoffers" type="checkbox" value="${ofertas.idOfertaCandidato}" />
                            </td>
                        </tr>
                        <tr class="detalles ${odd ? 'odd' : ''}">
                            <td colspan="6">
                                <%--<a href="javascript:mostrarResumen('offersFindMe${rowMeter.count}')" class="expand">--%>
                                    <%--<div id="hideoffersFindMe${rowMeter.count}" style="display:none;">Ocultar resumen</div>--%>
                                    <%--<div id="showoffersFindMe${rowMeter.count}" style="display:block;">Ver resumen</div>--%>
                                <%--</a>--%>
                                <div <%--style="display:none;"--%> id="offersFindMe${rowMeter.count}">
                                    <p><strong>Resumen:</strong></p>
                                    <c:out value="${ofertas.tituloOferta}"/>&nbsp;
                                    <c:out value="${ofertas.gradoEstudios}"/>,&nbsp;
                                    <c:out value="${ofertas.especialidades}"/>,&nbsp;
                                    <c:if test="${not empty ofertas.funciones}">
                                        <c:out value="${ofertas.funciones}"/>,&nbsp;
                                    </c:if>
                                    <c:if test="${ofertas.edadRequisito eq edadRequisitoSiIdOpcion}">
                                        <c:out value="Edad: ${ofertas.edadMinima}-${ofertas.edadMaxima} años"/>,&nbsp;
                                    </c:if>
                                    <c:if test="${not empty ofertas.idiomas}">
                                        <c:out value="Idiomas: ${ofertas.idiomas}"/>&nbsp;
                                    </c:if>
                                    Tipo de empleo:&nbsp;<c:out value="${ofertas.tipoEmpleo}"/>,&nbsp;
                                    N&uacute;mero de plazas:&nbsp;<c:out value="${ofertas.numeroPlazas}"/>,&nbsp;
                                    Medio para contactar la oferta:&nbsp;<c:out value="${ofertas.medioContacto}"/>,&nbsp;
                                </div>
                            </td>
                            <td></td>
                        </tr>
                        <c:set var="odd" value="${not odd}"/>
                    </c:forEach>
                    <tr>
                        <td colspan="6">&nbsp;</td>
                        <td style="text-align: center; width: 90px">
                            <input type="submit" class="boton" name="" value="Eliminar" onclick="javascript: if(hayOfertasSeleccionadas() && confirm('¿Está seguro que desea eliminar las ofertas seleccionadas?'))return true; else return false;">
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
