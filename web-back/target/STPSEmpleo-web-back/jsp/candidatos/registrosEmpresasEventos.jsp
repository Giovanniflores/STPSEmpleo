<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="mx.gob.stps.portal.core.infra.utils.Constantes" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html"%>

<script type="text/javascript">
		  function doSubmitPager(method) {
	  	  dojo.xhrPost({url: 'detalleEvento.do?method='+method+'&tablaPager=_EMPRESAS', form:'pagForm', timeout:180000, 
			  load: function(data){
			    dojo.byId('tabla').innerHTML=data;
		  }});
	    }

		
 
		  function doSubmitPagina(pagina, dif) {
    		dojo.xhrPost({url: 'detalleEvento.do?method=goToPage&tablaPager=_EMPRESAS&goToPageNumber='+pagina, timeout:180000, 
				load: function(dataCand){
    	 			dojo.byId('tabla').innerHTML=dataCand;
			}});
    	 }
    	 
    
    	 
    function getAnyElementValueById(elementId) {
		var vElement = dojo.byId(elementId).value;
		if(vElement==undefined || vElement==''){
			vElement = document.getElementById(elementId).value;
		}
		return vElement;
	}	 
</script>

<strong><p id="msjTam">
		<c:if test="${total == 0}">
			No se encontraron empresas con ofertas para este evento.
		</c:if>
	</p></strong>

<table class="offer" style="width: 622px; margin: auto;">
<caption style="text-align: center; font-size: 14px">Algunas empresas que participan</caption>
<tbody>
<tr class="temas">
	<th>Empresas participantes al día de hoy</th>
	<th style="width: 115px">Número de plazas</th>
</tr>
<logic:iterate id="empresas" collection='<%= request.getSession(false).getAttribute("PAGE_LIST_EMPRESAS") %>' indexId="empresasIndexId" scope="request">
<bean:define id="indexMod3" value='<%= String.format("%d",empresasIndexId % 2) %>'/>
	<logic:equal name="indexMod3" value="0">
		<bean:define id="trClase" value=""/>
	</logic:equal>
	<logic:notEqual name="indexMod3" value="0">
		<bean:define id="trClase" value="odd"/>
	</logic:notEqual>
	<tr class="${trClase}">
		<td>
			<bean:write name="empresas" property="nombreEmpresa"/>
		</td>
		<td style="text-align: center">
			<bean:write name="empresas" property="noPlazasEvento"/>
		</td>
	</tr>
</logic:iterate>
</tbody>
</table>


<bean:define id="sufijo" value="_EMPRESAS"/>
<c:if test="${total > 0}">
<jsp:include page="../layout/pager.jsp" flush="true">
         <jsp:param name="SUFIJO" value="${sufijo}"/>
         <jsp:param name="tipoRegistros" value="empresas"/>		 
</jsp:include>
</c:if>
<br/>
<p class="gris" style="text-align:center; line-height: 170%; width: 599px; margin: auto">Una vez que quedes registrado al evento podrás consultar el detalle de todas las ofertas de empleo
ingresando con tu usuario y contraseña del Portal del Empleo a <a href="http://ferias.empleo.gob.mx">http://ferias.empleo.gob.mx</a></p>
<form name="registerForm" id="registerForm">
<div class="form_nav" id="div_form_nav">
<c:if test="${registered == 0}">
			<input type="button"  value="Registrarme"   onclick="javascript:doRegistraEvento()" id="btnReg"/>
			</c:if>
			<c:if test="${registered == 1}">
				<a href="<%=request.getContextPath()%>/detalleEvento.do?method=imprimirComprobante"  style="margin-right: 200px" class="boton_naranja nueva-busqueda" id="nueva-busqueda">Imprimir Comprobante</a>
			</c:if>
	</div>	
</form>
