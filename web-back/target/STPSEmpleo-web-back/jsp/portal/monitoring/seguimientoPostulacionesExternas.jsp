<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>

<fmt:setBundle basename="MessageResources" var="portalbundle"/>
<fmt:setBundle basename="portal-web" var="messages"/>

<%
String context = request.getContextPath() +"/";  
%>

<script type="text/javascript">
</script>

<noscript>Funciones de JavaScript desactivadas por navegador</noscript>

<div id="mis_ofertas" class="formApp miEspacio">

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
                        <li>
                            <a href="<c:url value="/misofertas.do?method=misPostulaciones"/>">Mis postulaciones</a>
                        </li>
                        <li>
                            <a href="<c:url value="/misofertas.do?method=misOfertasGuardadas"/>">Mis ofertas guardadas</a>
                        </li>
                        <li>
                            <a href="<c:url value="/misofertas.do?method=empresasQueMeBuscan"/>">Empresas que me buscan</a>
                        </li>
                        <!--INI_JGLC_PPC-->
<!--                         <li> -->
<%--                         	<a href="${context}registroPostulacionExterna.do?method=init">Registrar postulaciones externas</a> --%>
<!--                         </li> -->
<!--                         <li  class="curSubTab"> -->
<!--                             <span>Seguimiento a postulaciones externas</span> -->
<!--                         </li> -->
<!--                         <li> -->
<!--                             <a href="/adminNoPostulacionesCandidato.do?method=init" id="registrar_motivo_no_postulacion">Registrar motivo de no postulaci&oacute;n</a> -->
<!--                         </li> -->
                        <!--FIN_JGLC_PPC-->
                    </ul>
                    <div class="clearfix"></div>
                </div>
            </div>
            <p></p>
        </div>
        
        <div class="gris" style="text-align: center; font-weight: bold">TODO SEGUIMIENTO POSTULACIONES EXTERNAS</div>
</div>
        