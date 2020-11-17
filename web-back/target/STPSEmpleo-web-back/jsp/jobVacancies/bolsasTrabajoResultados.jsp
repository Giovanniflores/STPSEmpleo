<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"		prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%
	String context = request.getContextPath() +"/";
	pageContext.setAttribute("context", context);
%>

<script type="text/javascript">
dojo.require("dijit.dijit"); // loads the optimized dijit layer
dojo.require("dijit.form.Form");    
dojo.require("dijit.form.RadioButton");
dojo.require("dijit.form.TextBox");
dojo.require("dijit.form.ComboBox");
dojo.require("dojo.data.ItemFileReadStore"); 
dojo.require("dijit.form.FilteringSelect");

function doRegresar(method){
	document.bolsasTrabajoForm.method.value=method;
	document.bolsasTrabajoForm.submit();
}

function doSubmitx(method){
	document.bolsasTrabajoForm.method.value=method;
	document.bolsasTrabajoForm.submit();
}

function doSubmitPaginaInicial(method) {
    dojo.xhrPost({url: 'bolsasTrabajo.do?method='+method, timeout:180000,
                  load: function(data) {
                        dojo.byId('reporte').innerHTML = data;
                  }});
}

function doSubmitPagina(pagina, dif){
   	dojo.xhrPost({url: 'bolsasTrabajo.do?method=goToPage&goToPageNumber='+pagina, timeout:180000, 
				  load: function(dataCand){
				      dojo.byId('reporte').innerHTML=dataCand;
				  }});
}

function doSubmitOrden(campo, orden){
   	dojo.xhrPost({url: 'bolsasTrabajo.do?method=ordenaPor&campo='+ campo +'&orden='+ orden, timeout:180000, 
				  load: function(dataCand){
				      dojo.byId('reporte').innerHTML=dataCand;
				  }});
}

//VER DETALLE OFERTAS SFP
function mostrarDetalle(idOfertaSFP){
	//COMENTAR EN QA document.bolsasTrabajoForm.method.value = 'detalleOfertaSFP';
	//COMENTAR EN PRODUCCION
	document.bolsasTrabajoForm.method.value = 'detalleOfertaGenerico';
	document.bolsasTrabajoForm.idOferta.value = idOfertaSFP;
	document.bolsasTrabajoForm.submit();
}

</script>
<noscript>Funciones de JavaScript desactivadas por navegador</noscript>

<div>
<div id="cont-resultados" class="miEspacio">

	<form dojoType="dijit.form.Form" name="bolsasTrabajoForm" id="bolsasTrabajoForm" action="bolsasTrabajo.do" method="post">
		<input type="hidden" name="method" value="init"/>
<%-- 		<input type="hidden" name="fromPortal" id="fromPortal" value="${bolsasTrabajoForm.fromPortal}"  />
 --%>		
		
		
			<%-- 
			<h2>Mi espacio</h2>
			<div align="left" style="display:block;" id="returnME">
				<a class="expand" href="javascript:location.replace('/miEspacioCandidato.do');">
				<strong>Ir al inicio de Mi espacio</strong></a>
			</div>
			<div class="Tab_espacio">
				<h3>Resultados en otras bolsas<br> de empleo</h3>
				<div class="subTab">
					<ul>
						<li><a href="${context}ofertasPerfiles.do?method=init&amp;tipoConsulta=1">Ofertas de acuerdo a mi perfil</a></li>						
						<li><a href="${context}ofertasPorParametros.do?method=init">Búsqueda por parametrizable</a></li>						
						<li class="curSubTab"><span>Buscar en otras bolsas de trabajo</span></li>
					</ul>
					<div class="clearfix"></div>
				</div>
			</div>
			--%>
			<div class="otras_bolsas gris">
			<div class="sublevelTitle">
				Ofertas encontradas en:
			</div>
					<div id="resultados-en">
						<c:if test="${busquedaen == 1}"><img alt="${filtroBolsa}" title="${filtroBolsa}" src="images/otras-occ.png"/></c:if>
						<c:if test="${busquedaen == 2}"><img alt="${filtroBolsa}" title="${filtroBolsa}" src="images/otras-bumeran.png"/></c:if>
						<c:if test="${busquedaen == 3}"><img alt="${filtroBolsa}" title="${filtroBolsa}" src="images/otras-manpower.png"/></c:if>
						<c:if test="${busquedaen == 4}"><img alt="${filtroBolsa}" title="${filtroBolsa}" src="images/otras-adecco.png"/></c:if>
						<c:if test="${busquedaen == 5}"><img alt="${filtroBolsa}" title="${filtroBolsa}" src="images/otras-Trabajos-MX.png" style="background-color: white;"/></c:if>
						<c:if test="${busquedaen == 6}"><img alt="${filtroBolsa}" title="${filtroBolsa}" src="images/otras-zonajobs.png"/></c:if>
						<c:if test="${busquedaen == 7}"><img alt="${filtroBolsa}" title="${filtroBolsa}" src="images/otras-turijobs.png"/></c:if>
						<c:if test="${busquedaen == 8}"><img alt="${filtroBolsa}" title="${filtroBolsa}" src="images/otras-trabajaen.png"/></c:if>
						<c:if test="${busquedaen == 9}"><img alt="${filtroBolsa}" title="${filtroBolsa}" src="images/otras-discapacidadyempleo.png"/></c:if><br><br>
						<c:if test="${busquedaen == 10}"><img alt="${filtroBolsa}" title="${filtroBolsa}" src="images/otras-superchamba.png"/></c:if>
					</div>
			
					<c:if test="${fn:length(FULL_LIST) == 0}">
					<p id="busqueda_concepto" class="alerta">	    	
				    	<c:if test="${not empty filtroPalabra}">
				    	No se encontraron ofertas para la b&#250;squeda: <strong class="c_naranja">${filtroPalabra}, ${filtroEntidad}, ${filtroFecha}</strong>
				    	</c:if>
				    	<c:if test="${empty filtroPalabra}">
				    	No se encontraron ofertas para la b&#250;squeda.
				    	</c:if>
					</p>
					</c:if>
			
					<c:if test="${fn:length(FULL_LIST) > 0}">
				    <p id="cuantos-resultados">
				    	<c:if test="${not empty filtroPalabra}">
				    	${fn:length(FULL_LIST)} resultados encontrados para: <strong class="c_naranja">${filtroPalabra}, ${filtroEntidad}, ${filtroFecha}</strong>
				    	</c:if>
				    	<c:if test="${empty filtroPalabra}">
				    	${fn:length(FULL_LIST)} resultados encontrados
				    	</c:if>
				    </p>
				    </c:if>
				    <div class="form_nav">    
				    	<a href="javascript:doRegresar('init')" class="nueva-busqueda boton_naranja" id="nueva-busqueda">Nueva búsqueda</a>
				    </div>
			</div>
			<div id="reporte"></div>
		</div>

	



		<div class="entero">
			<div id="msg"></div>
		</div>

	</form>
</div>

<script type="text/javascript">
    doSubmitPaginaInicial('page');
</script>
