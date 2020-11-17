<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="messagePost" value="${MyOffersForm.message}"/>
<c:set var="queryPost" value="${QUERY}"/>
<c:set var="difTablaPager" value="${MyOffersForm.difTablaPager}"/>
<%--difTablaPager values:--%>
  <%--"_MIS_POSTULACIONES"--%>
  <%--"_MIS_OFERTAS_GUARDADAS"--%>
  <%--"_EMPRESAS_QUE_ME_BUSCAN"--%>
  <%--"_REGISTRAR_POSTULACIONES_EXTERNAS"--%>
  <%--"_SEGUIMIENTO_A_POSTULACIONES_EXTERNAS"--%>
  <%--"_REGISTRAR_MOTIVO_DE_NO_POSTULACION"--%>


<%
String context = request.getContextPath() +"/";  
%>
<script>

    function callable(form, field) {
        selectCheck(form, form.allCheck.checked, field);
    }

    function selectCheck(form, obj, field) {
        var i=form.elements.length;
        for(var k=0;k<i;k++) {
            if(form.elements[k].name==field) {
                if (!form.elements[k].disabled) {
                    form.elements[k].checked=obj;
                }
            }
        }
    }

    function doSubmitPagerMisOfertasDeEmpleo(difTablaPager){
        var method = "pageTable";

        dojo.xhrPost({url: 'misofertas.do?method='+method+'&tablaPager='+difTablaPager, timeout:180000,
            load: function(dataCand){
                dojo.byId('misOfertasDeEmpleo').innerHTML=dataCand;
            }});
    }

    function doSubmitPagina(pagina, dif){
        var divTabla = 'misOfertasDeEmpleo';

        dojo.xhrPost({url: 'misofertas.do?method=goToPage&tablaPager='+dif+'&goToPageNumber='+pagina, timeout:180000,
            load: function(dataCand){
                dojo.byId(divTabla).innerHTML=dataCand;
            }});
    }

    function hayOfertasSeleccionadas(){
        ofertasChecked = false;
        listaCheckboxes = dojo.query("input[type=checkbox]");
        listaCheckboxes.forEach( function(node, index, nodeList){
            if(node.checked){
                ofertasChecked = true;

                return true;
            }

        });
        if(!ofertasChecked)alert('Es preciso seleccionar al menos una oferta de trabajo');
        return ofertasChecked;
    }

    /* Ordena tabla por columna */
    function orderBy(orden,tipocolumna){

        dojo.xhrPost({url: 'misofertas.do?method=ordenarMisPostulaciones&tipoOrden=' + orden + '&tipoColumna=' + tipocolumna, timeout:180000,
            load: function(dataCand){
                dojo.byId('misOfertasDeEmpleo').innerHTML=dataCand;
                if(tipocolumna == 'puesto') {
                    if (orden == 'asc') {
                        document.getElementById("puesto_orden_asc").style.display = 'none';
                        document.getElementById("puesto_orden_desc").style.display = 'inline';
                    } else if (orden == 'desc') {
                        document.getElementById("puesto_orden_asc").style.display = 'inline';
                        document.getElementById("puesto_orden_desc").style.display = 'none';
                    }
                } else if(tipocolumna == 'empresa'){
                    if (orden == 'asc') {
                        document.getElementById("empresa_orden_asc").style.display = 'none';
                        document.getElementById("empresa_orden_desc").style.display = 'inline';
                    } else if (orden == 'desc') {
                        document.getElementById("empresa_orden_asc").style.display = 'inline';
                        document.getElementById("empresa_orden_desc").style.display = 'none';
                    }
                } else if(tipocolumna == 'ubicacion'){
                    if (orden == 'asc') {
                        document.getElementById("ubicacion_orden_asc").style.display = 'none';
                        document.getElementById("ubicacion_orden_desc").style.display = 'inline';
                    } else if (orden == 'desc') {
                        document.getElementById("ubicacion_orden_asc").style.display = 'inline';
                        document.getElementById("ubicacion_orden_desc").style.display = 'none';
                    }
                } else if(tipocolumna == 'vigencia'){
                    if (orden == 'asc') {
                        document.getElementById("vigencia_orden_asc").style.display = 'none';
                        document.getElementById("vigencia_orden_desc").style.display = 'inline';
                    } else if (orden == 'desc') {
                        document.getElementById("vigencia_orden_asc").style.display = 'inline';
                        document.getElementById("vigencia_orden_desc").style.display = 'none';
                    }
                } else if(tipocolumna == 'estatus'){
                    if (orden == 'asc') {
                        document.getElementById("estatus_orden_asc").style.display = 'none';
                        document.getElementById("estatus_orden_desc").style.display = 'inline';
                    } else if (orden == 'desc') {
                        document.getElementById("estatus_orden_asc").style.display = 'inline';
                        document.getElementById("estatus_orden_desc").style.display = 'none';
                    }
                }
            }});
    }

</script>

<noscript>Funciones de JavaScript desactivadas por navegador</noscript>

<div id="mis_ofertas" class="miEspacio">

    <div class="contenedor_contenido">

        <h2>Mi espacio</h2>

        <div class="tab_block">
            <div align="left" style="display:block;" id="returnME">
                <a class="expand" href="javascript:location.replace('<c:url value="/miEspacioCandidato.do"/>');">
                    <strong>Ir al inicio de Mi espacio</strong>
                </a>
            </div>
            <div class="Tab_espacio">
                <h3>Mis ofertas de empleo</h3>

                <div class="subTab">
                    <ul>
                        <c:choose>
                            <c:when test="${difTablaPager == '_MIS_POSTULACIONES' }">
                                <li class="curSubTab">
                                    <span>Mis postulaciones</span>
                                </li>
                            </c:when>
                            <c:otherwise>
                                <li>
                                    <a href="<c:url value="/misofertas.do?method=misPostulaciones"/>">Mis postulaciones</a>
                                </li>
                            </c:otherwise>
                        </c:choose>
                        <c:choose>
                            <c:when test="${difTablaPager == '_MIS_OFERTAS_GUARDADAS' }">
                                <li class="curSubTab">
                                    <span>Mis ofertas guardadas</span>
                                </li>
                            </c:when>
                            <c:otherwise>
                                <li>
                                    <a href="<c:url value="/misofertas.do?method=misOfertasGuardadas"/>">Mis ofertas guardadas</a>
                                </li>
                            </c:otherwise>
                        </c:choose>
                        <c:choose>
                            <c:when test="${difTablaPager == '_EMPRESAS_QUE_ME_BUSCAN' }">
                                <li class="curSubTab">
                                    <span>Empresas que me buscan</span>
                                </li>
                            </c:when>
                            <c:otherwise>
                                <li>
                                    <a href="<c:url value="/misofertas.do?method=empresasQueMeBuscan"/>">Empresas que me buscan</a>
                                </li>
                            </c:otherwise>
                        </c:choose>
                        <!-- New links to PPC-SD -->
                        <!--INI_JGLC_PPC-->
<%--                         <c:if test="${candidatoheader.isActiveToPpc() || candidatoheader.isInactiveToPpc()}"> --%>
<%--                             <c:choose> --%>
<%--                                 <c:when test="${difTablaPager == '_REGISTRAR_POSTULACIONES_EXTERNAS' }"> --%>
<!--                                     <li class="curSubTab"> -->
<!--                                         <span>Registrar postulaciones externas</span> -->
<!--                                     </li> -->
<%--                                 </c:when> --%>
<%--                                 <c:otherwise> --%>
<!--                                     <li> -->
<%--                                     	<a href="${context}registroPostulacionExterna.do?method=init" id="registro_postulaciones_externas">Registrar postulaciones externas</a> --%>
<!--                                     </li> -->
<%--                                 </c:otherwise> --%>
<%--                             </c:choose> --%>
<%--                             <c:choose> --%>
<%--                                 <c:when test="${difTablaPager == '_SEGUIMIENTO_A_POSTULACIONES_EXTERNAS' }"> --%>
<!--                                     <li class="curSubTab"> -->
<!--                                         <span>Seguimiento a postulaciones externas</span> -->
<!--                                     </li> -->
<%--                                 </c:when> --%>
<%--                                 <c:otherwise> --%>
<!--                                     <li>                                    	 -->
<%--                                         <a href="${context}seguimientoPostulacionExterna.do?method=init"  id="seguimiento_postulaciones_externas">Seguimiento a postulaciones externas</a> --%>
<!--                                     </li> -->
<%--                                 </c:otherwise> --%>
<%--                             </c:choose> --%>
<%--                             <c:choose> --%>
<%--                                 <c:when test="${difTablaPager == '_REGISTRAR_MOTIVO_DE_NO_POSTULACION' }"> --%>
<!--                                     <li class="curSubTab"> -->
<!--                                         <span>Registrar motivo de no postulaci&oacute;n</span> -->
<!--                                     </li> -->
<%--                                 </c:when> --%>
<%--                                 <c:otherwise> --%>
<!--                                     <li> -->
<!--                                         <a href="/adminNoPostulacionesCandidato.do?method=init" id="registrar_motivo_no_postulacion">Registrar motivo de no postulaci&oacute;n</a> -->
<!--                                     </li> -->
<%--                                 </c:otherwise> --%>
<%--                             </c:choose> --%>
<!--                         </ul> -->
<%--                     </c:if> --%>
                    <!--FIN_JGLC_PPC-->
                    <div class="clearfix"></div>
                </div>
            </div>
            <p></p>
        </div>

        <div id="misOfertasDeEmpleo"></div> <!--Content filled via Ajax-->
    </div>

    <script>
        var difTablaPager = "<c:out value='${difTablaPager}'/>";
        doSubmitPagerMisOfertasDeEmpleo(difTablaPager);
    </script>
    <script>${messagePost}</script>
    <script>${queryPost}</script>
</div>