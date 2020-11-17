<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page import="mx.gob.stps.portal.web.admin.form.CurrentOfferReportForm"%>
<%@ page import="mx.gob.stps.portal.web.infra.utils.Utils"%>
<%@ page import="java.util.Hashtable"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Vector"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="java.util.Collections"%>

<script src="dojotoolkit/dojo/dojo.js" djConfig="parseOnLoad: true, locale:'es'"></script>
<script type="text/javascript">


  function doSubmitPager(method) {
        
      dojo.byId('method').value = method;
      dojo.xhrPost({url: 'repofertasvig.do', form:'reportEscolaridad', timeout:180000,
                    load: function(data) {
                          dojo.byId('reporte').innerHTML = data;
                    }});

      dojo.attr('ofertasEntidadGenero', 'href', "javascript:doSubmitPager('ofertasEntidadGenero')");
      dojo.attr('ofertasEntidadEscolaridad', 'href', "javascript:doSubmitPager('ofertasEntidadEscolaridad')");
      dojo.attr('ofertasEntidadExperiencia', 'href', "javascript:doSubmitPager('ofertasEntidadExperiencia')");      
      dojo.removeAttr(method, 'href');
  }
</script>
<noscript>Funciones de JavaScript desactivadas por navegador</noscript>

<%
String context = request.getContextPath() +"/";
CurrentOfferReportForm formOffers = (CurrentOfferReportForm)request.getAttribute("CurrentOfferReportForm");
%>
<h3>Reporte de plazas vigentes</h3>

<h3>Plazas vigentes por Entidad Federativa registradas en el Portal del Empleo</h3>
    <form name="reportEscolaridad" id="reportEscolaridad" action="repofertasvig.do" method="post" dojoType="dijit.form.Form">       
        <input type="hidden" name="method" id="method" value="" /> 
    </form>

<table cellspacing="0" cellpadding="0" border="0" width="730px">
	<tr>
		<td width="33%"><a id="ofertasEntidadGenero" href="javascript:doSubmitPager('ofertasEntidadGenero')">G&eacute;nero</a></td>
		<td width="33%"><a id="ofertasEntidadEscolaridad" href="javascript:doSubmitPager('ofertasEntidadEscolaridad')">Grado de estudios</a></td>
		<td width="33%"><a id="ofertasEntidadExperiencia" href="javascript:doSubmitPager('ofertasEntidadExperiencia')">Experiencia</a></td>
	</tr>  
</table>

<div id="reporte" name="reporte">
</div>
<script>
	doSubmitPager('ofertasEntidadGenero');
</script>

<h3>Plazas vigentes de bolsas de empleo asociadas</h3>
<table id="dataTable" class="offer" cellspacing="0" cellpadding="0" border="0" width="730px">
	<tbody>
		<tr class="temas">
	    	<TH width="75%">Bolsa</TH>
	    	<TH width="25%">Total</TH>
		</tr>
		<c:forEach var="parametro" items="${ofertasExternasEntidad}" varStatus="rowCounter">
			<tr <c:out value="${rowCounter.count % 2 == 0?'':'class=odd'}"/>>
				<td>${parametro.descripcion} </td>
				<td align="right"><fmt:formatNumber type="number" pattern="###,###" value="${parametro.valor}"  /></td>				
			</tr>
		</c:forEach>
		<tr><td>TOTAL:</td><td align="right"><fmt:formatNumber type="number" pattern="###,###" value="${CurrentOfferReportForm.totalOfertasExternas}" /></td></tr>		
	</tbody>
</table>
<h3>Total de Plazas Vigentes en el Portal del Empleo</h3>
<table>
	<tr><td>Suma de plazas del Portal del Empleo y Plazas de Bolsas de empleo asociadas:</td><td align="right"><fmt:formatNumber type="number" pattern="###,###" value="${CurrentOfferReportForm.totalOfertas}" /></td></tr>
</table>
