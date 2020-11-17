<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%
	String context = request.getContextPath() +"/";
%>

<script type="text/javascript">
	dojo.require("dijit.dijit"); // loads the optimized dijit layer
	dojo.require("dijit.form.Form");
	dojo.require("dojo.data.ItemFileReadStore");
	dojo.require("dijit.form.FilteringSelect");
</script>

<script type="text/javascript">
	function mostrarResumen(idResumen) {
	    var thisRow = dojo.style(idResumen, "display");
	    if (thisRow == 'block') {
	        dojo.style(idResumen, "display", "none");
	        dojo.style(eval("\"" + "hide" + idResumen + "\""), "display", "none");
	        dojo.style(eval("\"" + "show" + idResumen + "\""), "display", "block");
	    }
	    if (thisRow == 'none') {
	        dojo.style(idResumen, "display", "block");
	        dojo.style(eval("\"" + "hide" + idResumen + "\""), "display", "block");
	        dojo.style(eval("\"" + "show" + idResumen + "\""), "display", "none");
	    }
	}
	function mostrarTodas(tipo, totReg) {
	    if (tipo == 1) {
	        for (var i = 1; i <= totReg; i++) {
	            dojo.style("hideAll", "display", "block");
	            dojo.style("showAll", "display", "none");
	            dojo.style(eval("\"" + i + "\""), "display", "block");
	            dojo.style(eval("\"" + "hide" + i + "\""), "display", "block");
	            dojo.style(eval("\"" + "show" + i + "\""), "display", "none");
	        }
	    }
	    if (tipo == 2) {
	        for (var i = 1; i <= totReg; i++) {
	            dojo.style("hideAll", "display", "none");
	            dojo.style("showAll", "display", "block");
	            dojo.style(eval("\"" + i + "\""), "display", "none");
	            dojo.style(eval("\"" + "hide" + i + "\""), "display", "none");
	            dojo.style(eval("\"" + "show" + i + "\""), "display", "block");
	        }
	    }
	}
	function getAnyElementValueById(elementId) {
		var vElement = dijit.byId(elementId).value;
		if(vElement==undefined || vElement==''){
			vElement = document.getElementById(elementId).value;
		}
		return vElement;
	}
	function doSubmit(method) {
		if(checkOtherFields()){
			document.FilterOfferForm.method.value=method;
			document.FilterOfferForm.submit();			
		}
	}
	function checkOtherFields(){
		var vForm = true;
		var vMessage = '';
		var idRegion = getAnyElementValueById('idRegion');
		var idEdad = getAnyElementValueById('idEdad');
		var idCarrera = getAnyElementValueById('idCarrera');
		var idOcupacion = getAnyElementValueById('idOcupacion');
		var idMunicipioSelect = getAnyElementValueById('idMunicipioSelect');
		var idSalario = getAnyElementValueById('idSalario');
		var idEscolaridad = getAnyElementValueById('idEscolaridad');
		var idAreaLaboral = getAnyElementValueById('idAreaLaboral');
		var idEntSelect = getAnyElementValueById('idEntidadSelect');
		var idRegion = getAnyElementValueById('idRegion');
		if (idEntSelect==0 && idAreaLaboral==0 && idEscolaridad==0 && idSalario==0 && idEdad==0 & idRegion==0) {
			vMessage = vMessage + '\nDebe proporcionar un filtro para realizar la busqueda.';
			vForm = false;
		}		
		if (vMessage.length>0) {
			alert(vMessage);
		}
		if (dijit.byId('idEntidadSelect').get('item') && dijit.byId('idEntidadSelect').get('item').label){
			dojo.byId('district').value = dijit.byId('idEntidadSelect').get('item').label[0];			
		}
		if (dijit.byId('idAreaLaboral').get('item') && dijit.byId('idAreaLaboral').get('item').label){
			dojo.byId('area').value = dijit.byId('idAreaLaboral').get('item').label[0];			
		}
		if (dijit.byId('idEscolaridad').get('item') && dijit.byId('idEscolaridad').get('item').label){
			dojo.byId('escolaridad').value = dijit.byId('idEscolaridad').get('item').label[0];			
		}
		if (dijit.byId('idSalario').get('item') && dijit.byId('idSalario').get('item').label){
			dojo.byId('salario').value = dijit.byId('idSalario').get('item').label[0];			
		}
		if (dijit.byId('idMunicipioSelect').get('item') && dijit.byId('idMunicipioSelect').get('item').label){
			dojo.byId('idMunicipio').value = dijit.byId('idMunicipioSelect').get('item').label[0];			
		}
		if (dijit.byId('idOcupacion').get('item') && dijit.byId('idOcupacion').get('item').label){
			dojo.byId('ocupacion').value = dijit.byId('idOcupacion').get('item').label[0];			
		}
		if (dijit.byId('idCarrera').get('item') && dijit.byId('idCarrera').get('item').label){
			dojo.byId('carrera').value = dijit.byId('idCarrera').get('item').label[0];			
		}
		if (dijit.byId('idEdad').get('item') && dijit.byId('idEdad').get('item').label){
			dojo.byId('edad').value = dijit.byId('idEdad').get('item').label[0];			
		}
		if (dijit.byId('idRegion').get('item') && dijit.byId('idRegion').get('item').label){
			dojo.byId('region').value = dijit.byId('idRegion').get('item').label[0];			
		}
		return vForm;	
	}
	function doSubmitPager(method) {
	  dojo.byId('method').value = method;	 
	  dojo.xhrPost({url: 'specificsearch.do', form:'FilterOfferForm', timeout:180000, 
	  	load: function(data) {
	  		dojo.byId('table').innerHTML=data;
		}});  
	}
	function doSubmitPage(page){
  		dojo.byId('method').value = "goToPage";
  		dojo.byId('goToPageNumber').value = page;
  		dojo.xhrPost({url: 'specificsearch.do', form:'FilterOfferForm', timeout:180000, 
  			load: function(data){
  			dojo.byId('table').innerHTML=data;
  		}});
  	}

	  /* Ordena tabla por columna */
	  function orderBy(orden,tipocolumna){
		 		
		dojo.xhrPost({url: 'specificsearch.do?method=orderByColumn&tipoOrden=' + orden + '&tipoColumna=' + tipocolumna, timeout:180000, 
			  load: function(dataCand){
			      dojo.byId('table').innerHTML=dataCand;
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
				  	}else if(tipocolumna == '5' && orden == 'asc'){
				  		document.getElementById("fecha_orden_asc").style.display = 'none';
				  		 document.getElementById("fecha_orden_desc").style.display = 'inline';
				  	}else if(tipocolumna == '5' && orden == 'desc'){
				  		document.getElementById("fecha_orden_asc").style.display = 'inline';
				  		 document.getElementById("fecha_orden_desc").style.display = 'none';
				  	}
			  }});
	  }
	
</script>
<noscript>Funciones de JavaScript desactivadas por navegador</noscript>

<style>
<!--
.specific_block strong {
    display: block;
    margin: 0 0 10px;
}
-->
</style>
<form name="FilterOfferForm" id="FilterOfferForm" method="post" action="specificsearch.do" dojoType="dijit.form.Form">
	<input type="hidden" name="method" id="method" value="filter"/>
	<input type="hidden" name="goToPageNumber" id="goToPageNumber" value="1" />
	<br clear="all" />
	<div id="table"></div>
</form>
<script>
	doSubmitPager('page');
</script>