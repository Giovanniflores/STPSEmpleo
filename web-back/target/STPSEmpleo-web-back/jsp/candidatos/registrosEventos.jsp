<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="mx.gob.stps.portal.core.infra.utils.Constantes" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html"%>

<script type="text/javascript">
		 function doSubmitPager(method) {
	  	  dojo.xhrPost({url: 'registrarCandidatoEvento.do?method='+method, form:'paginationForm', timeout:180000, 
			  load: function(data){
			    dojo.byId('tabla').innerHTML=data;
		  }});
	    }

		   	
		function orderBy(orderType, columnNumber){
			dojo.xhrPost({url: 'registrarCandidatoEvento.do?method=orderByColumn&orderType=' + orderType + '&columnNumber=' + columnNumber, timeout:180000, 
			  load: function(availableEventsData){
			      dojo.byId('tabla').innerHTML = availableEventsData;
			      // TODO: Change to Dojo methods
			      if(columnNumber == '1' && orderType == 'asc'){
						document.getElementById("eventNameAsc").style.display = 'none';
				  		document.getElementById("eventNameDesc").style.display = 'inline';
				  }else if(columnNumber == '1' && orderType == 'desc'){
				  		document.getElementById("eventNameDesc").style.display = 'none';
				  		document.getElementById("eventNameAsc").style.display = 'inline';
				  }else if(columnNumber == '2' && orderType == 'asc'){
				  		document.getElementById("entityNameAsc").style.display = 'none';
				  		document.getElementById("entityNameDesc").style.display = 'inline';
			  	  }else if(columnNumber == '2' && orderType == 'desc'){
				  		document.getElementById("entityNameDesc").style.display = 'none';
				  		document.getElementById("entityNameAsc").style.display = 'inline';
				  }else if(columnNumber == '3' && orderType == 'asc'){
				  		document.getElementById("sedeNameAsc").style.display = 'none';
				  		 document.getElementById("sedeNameDesc").style.display = 'inline';
				  }else if(columnNumber == '3' && orderType == 'desc'){
				  		document.getElementById("sedeNameDesc").style.display = 'none';
				  		document.getElementById("sedeNameAsc").style.display = 'inline';
				  }else if(columnNumber == '4' && orderType == 'asc'){
				  		document.getElementById("ambientNameAsc").style.display = 'none';
				  		document.getElementById("ambientNameDesc").style.display = 'inline';
				  }else if(columnNumber == '4' && orderType == 'desc'){
				 		document.getElementById("ambientNameDesc").style.display = 'none';
				  		document.getElementById("ambientNameAsc").style.display = 'inline';
				  }else if(columnNumber == '5' && orderType == 'asc'){
				  		document.getElementById("dateNameAsc").style.display = 'none';
				  		document.getElementById("dateNameDesc").style.display = 'inline';
				  }else if(columnNumber == '5' && orderType == 'desc'){
				  		document.getElementById("dateNameDesc").style.display = 'none';
				  		document.getElementById("dateNameAsc").style.display = 'inline';
				  }
			  }});
		 }
		 
		  function doSubmitPagina(pagina, dif) {
    		dojo.xhrPost({url: 'registrarCandidatoEvento.do?method=goToPage&goToPageNumber='+pagina, timeout:180000, 
				load: function(dataCand){
    	 			dojo.byId('tabla').innerHTML=dataCand;
			}});
    	 }
</script>

<c:if test="${eventoSeleccionado != null}">
<script>
dojo.addOnLoad(function(){
			window.location.href='${context}registrarCandidatoEvento.do?method=imprimirComprobante';
		});
</script>

</c:if>

<c:choose>	
		<c:when test="${total == 0}">
		<p id="msjTam" class="gris margin_top_40" style="text-align: center; font-weight: bold; margin: auto">
		No se encontraron eventos disponibles.
		</p>
		</c:when>
	
	<c:otherwise>
	<table class="offer margin_top_40" style="margin-bottom: 40px; width: 100%">
	<caption>Resultados de la búsqueda</caption>
	<tbody>
	<tr class="temas">
		<th style="width: 200px">
			<div class="fl">Nombre de evento</div>
			<div class="order-fix">
				<a id="eventNameAsc"    title="Orden Ascendente" style="display: inline;" class="ascendente" href="javascript:orderBy('asc','1')"></a> 
				<a id="eventNameDesc" 	  title="Orden Descendente" style="display: inline;"  class="descendente" href="javascript:orderBy('desc','1')"></a>
			</div>
		</th>
		<th style="width: 200px">
			<div class="fl">Lugar</div>
			<div class="order-fix">
				<a id="entityNameAsc"    title="Orden Ascendente" style="display: inline;" class="ascendente" href="javascript:orderBy('asc','2')"></a> 
				<a id="entityNameDesc" 	  title="Orden Descendente" style="display: inline;"  class="descendente" href="javascript:orderBy('desc','2')"></a>
			</div>
		</th>
		<th style="width: 180px">
			<div class="fl">Sede</div>
			<div class="order-fix">
				<a id="sedeNameAsc"    title="Orden Ascendente" style="display: inline;" class="ascendente" href="javascript:orderBy('asc','3')"></a> 
				<a id="sedeNameDesc" 	  title="Orden Descendente" style="display: inline;"  class="descendente" href="javascript:orderBy('desc','3')"></a>
			</div>
		</th>
		<th>
			<div class="fl">Tipo de Evento</div>
			<div class="order-fix">
				<a id="ambientNameAsc"    title="Orden Ascendente" style="display: inline;" class="ascendente" href="javascript:orderBy('asc','4')"></a> 
				<a id="ambientNameDesc" 	  title="Orden Descendente" style="display: inline;"  class="descendente" href="javascript:orderBy('desc','4')"></a>
			</div>
		</th>
		<th style="width: 150px">
			<div class="fl">Fecha de realización</div>
			<div class="order-fix">
				<a id="dateNameAsc"    title="Orden Ascendente" style="display: inline;" class="ascendente" href="javascript:orderBy('asc','5')"></a> 
				<a id="dateNameDesc" 	  title="Orden Descendente" style="display: inline;"  class="descendente" href="javascript:orderBy('desc','5')"></a>
			</div>
		</th>
	</tr>
	<logic:iterate id="eventos" collection='<%= request.getSession(false).getAttribute("PAGE_LIST") %>' indexId="eventosIndexerId">
	<bean:define id="indexMod3" value='<%= String.format("%d",eventosIndexerId % 2) %>'/>
		<logic:equal name="indexMod3" value="0">
			<bean:define id="trClase" value=""/>
		</logic:equal>
		<logic:notEqual name="indexMod3" value="0">
			<bean:define id="trClase" value="odd"/>
		</logic:notEqual>
		<tr class="${trClase}">
			<td style="width: 200px">
				<a href="<%=request.getContextPath()%>/detalleEvento.do?method=init&idEvento=${eventos.idEvento}"><bean:write name="eventos" property="evento"/></a>
			</td>
			<td style="width: 200px">
				<bean:write name="eventos" property="entidad"/> , <bean:write name="eventos" property="municipio"/>
			</td>
			<td style="width: 180px">
		<c:if test="${eventos.idAmbiente==1 }">
			<bean:write name="eventos" property="sede"/>
		</c:if>
		<c:if test="${eventos.idAmbiente ==2 }">
			<a target="_blank" href="<%=request.getContextPath()%>/registrarCandidatoEvento.do?method=redirectToVirtualEvent&idEventoVirtual=${eventos.idEvento}">Ir al evento</a>
		</c:if>
		</td>
			
			<td>
				<bean:write name="eventos" property="ambiente"/>
			</td>
			<td style="width: 150px">
				<bean:write name="eventos" property="fechaE"/>
			</td>
		</tr>
	</logic:iterate>
	</tbody>
	</table>
<bean:define id="sufijo" value=""/>
<jsp:include page="../layout/pager.jsp" flush="true">
        <jsp:param name="SUFIJO" value="${sufijo}"/>
        <jsp:param name="tipoRegistros" value="eventos"/>		 
</jsp:include>
</c:otherwise>
	</c:choose>





