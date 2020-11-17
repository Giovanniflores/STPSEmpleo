<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%-- <link href="${PATH_CSS_SWB_APP}estilos_canal.css" rel="stylesheet" type="text/css" /> --%>
<%-- <link href="${PATH_CSS_SWB}css_aplicacion/estilos_tabla.css" rel="stylesheet" type="text/css" /> --%>

<script type="text/javascript">
	dojo.require("dijit.dijit"); // loads the optimized dijit layer
	dojo.require("dijit.form.Form");
	dojo.require("dijit.Dialog");
	dojo.require("dijit.form.Button");
	dojo.require("dijit.form.ComboBox");
	dojo.require("dojo.data.ItemFileReadStore");
	dojo.require("dijit.form.Textarea");
	dojo.require("dijit.form.TextBox");
	dojo.require("dijit.form.ValidationTextBox");
	dojo.require("dijit.form.DateTextBox");
	dojo.require("dijit.form.FilteringSelect");
	dojo.require("dijit.form.MultiSelect");
	dojo.require("dijit.form.CheckBox");
	
	//PAGINACION
	function doSubmitPager(method) {
		 dojo.xhrPost({url: 'busquedaEspecificaCandidatos.do?method='+method, timeout:180000,
		                  load: function(data) {
		                        dojo.byId('tabla').innerHTML = data;
		                  }});
		}
	function doSubmitPagina(pagina, dif){
		
	   	dojo.xhrPost({url: 'busquedaEspecificaCandidatos.do?method=goToPage&goToPageNumber='+pagina, timeout:180000, 
					  load: function(dataCand){
					      dojo.byId('tabla').innerHTML=dataCand;
					  }});
	}
	
	function doSubmit(method){
		document.resultadoBusquedaForm.method.value = method;
		document.resultadoBusquedaForm.submit();
	}
	
	/* Ordena tabla por columna */
	  function orderBy(orden,tipocolumna){
		 		
		dojo.xhrPost({url: 'busquedaEspecificaCandidatos.do?method=orderByColumn&tipoOrden=' + orden + '&tipoColumna=' + tipocolumna, timeout:180000, 
			  load: function(dataCand){
			      dojo.byId('tabla').innerHTML=dataCand;
			      if(tipocolumna == '1' && orden == 'asc'){
				  		 document.getElementById("titulo_orden_asc").style.display = 'none';
				  		 document.getElementById("titulo_orden_desc").style.display = 'inline';
				  	}else if(tipocolumna == '1' && orden == 'desc'){
				  		document.getElementById("titulo_orden_desc").style.display = 'none';
				  		document.getElementById("titulo_orden_asc").style.display = 'inline';
				  	}else if(tipocolumna == '2' && orden == 'asc'){
				  		document.getElementById("ubicacion_orden_asc").style.display = 'none';
				  		 document.getElementById("ubicacion_orden_desc").style.display = 'inline';
				  	}else if(tipocolumna == '2' && orden == 'desc'){
				  		document.getElementById("ubicacion_orden_desc").style.display = 'none';
				  		document.getElementById("ubicacion_orden_asc").style.display = 'inline';
				  	}else if(tipocolumna == '3' && orden == 'asc'){
				  		document.getElementById("empresa_orden_asc").style.display = 'none';
				  		 document.getElementById("empresa_orden_desc").style.display = 'inline';
				  	}else if(tipocolumna == '3' && orden == 'desc'){
				  		document.getElementById("empresa_orden_asc").style.display = 'inline';
				  		 document.getElementById("empresa_orden_desc").style.display = 'none';
				  	}else if(tipocolumna == '4' && orden == 'asc'){
				  		document.getElementById("salario_orden_asc").style.display = 'none';
				  		 document.getElementById("salario_orden_desc").style.display = 'inline';
				  	}else if(tipocolumna == '4' && orden == 'desc'){
				  		document.getElementById("salario_orden_asc").style.display = 'inline';
				  		 document.getElementById("salario_orden_desc").style.display = 'none';
				  	}
			  }});
	  }
</script>
<noscript>Funciones de JavaScript desactivadas por navegador</noscript>
<form id="resultadoBusquedaForm" name="resultadoBusquedaForm" method="post" action="busquedaEspecificaCandidatos.do">
	<input type="hidden" name="method" id="method" value="init">
	<div id="cont-resultados">
		<div class="tab_block">
			<span class="regresarAMiEspacio">
				<a href="<c:url value="/miEspacioEmpresas.do"/>">Regresar a mi espacio</a>
			</span>
		</div>
		<h2>B&#250;squeda espec&#237;fica de candidatos</h2>
		<span class="clearfix">&nbsp</span>
		
		
			<c:if test="${total > 0}">
				<p id="busqueda_concepto" style="margin: 0 0 40px">${total} resultados encontrados para la b&#250;squeda: 
					<strong class="c_naranja">
						<c:forEach var="filtro" items="${busquedaCandidatosForm.filtrosBusqueda}" varStatus="status" >
							${filtro}<c:if test="${!status.last}">,</c:if>
						</c:forEach>
					</strong>
				</p>
			</c:if>
			<c:if test="${total == 0}">
				<p id="busqueda_concepto" class="alerta">No se encontraron candidatos para la b&#250;squeda:
						<strong class="c_naranja">
							<c:forEach var="filtro" items="${busquedaCandidatosForm.filtrosBusqueda}" varStatus="status" >
								${filtro}<c:if test="${!status.last}">,</c:if>
							</c:forEach>
						</strong>
				</p>
			</c:if>
		
	
		<a style="margin: 0 0 40px; text-decoration: none; display: block" href="javascript:doSubmit('init');"  class="boton_naranja nueva-busqueda" id="nueva-busqueda">Nueva b&#250;squeda</a>
			
		<div id="tabla"></div>
	</div>
</form>

<c:if test="${empty requestScope['init']}">
    <script>
    	doSubmitPager('page');
	</script>
</c:if>

