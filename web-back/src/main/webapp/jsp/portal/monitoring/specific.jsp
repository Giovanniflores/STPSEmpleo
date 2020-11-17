<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%String context = request.getContextPath() +"/";%>

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
		var idFuenteSelect = getAnyElementValueById('idFuenteSelect');
		var idRegion = getAnyElementValueById('idRegion');
		if (idFuenteSelect !=3 && idEntSelect==0 && idAreaLaboral==0 && idEscolaridad==0 && idSalario==0 && idEdad==0 & idRegion==0) {
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
		if (dijit.byId('idFuenteSelect').get('item') && dijit.byId('idFuenteSelect').get('item').label){
			dojo.byId('fuente').value = dijit.byId('idFuenteSelect').get('item').label[0];			
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

<c:if test="${not empty USUARIO_APP and USUARIO_APP.candidato}">
<div id="returnME" style="display:block;"  align="left">
  <a href="javascript:window.top.location='<c:url value="/miEspacioCandidato.do"/>'" class="expand">
    <strong>[Regresar a mi espacio]</strong>
  </a>
</div>
</c:if>

<c:if test="${not empty USUARIO_APP and USUARIO_APP.empresa}">
<div id="returnME" style="display:block;"  align="left">
  <a href="javascript:window.top.location='<c:url value="/miEspacioEmpresas.do"/>'" class="expand">
    <strong>[Regresar a mi espacio]</strong>
  </a>
</div>
</c:if>

<form name="FilterOfferForm" id="FilterOfferForm" method="post" action="specificsearch.do" dojoType="dijit.form.Form">
	<input type="hidden" name="method" id="method" value="filter"/>
	<input type="hidden" name="goToPageNumber" id="goToPageNumber" value="1" />
	<br clear="all" />
	<p class="specific_msg"><strong>Puedes buscar por una o más de estas opciones</strong></p>
	
	<div class="specific_block">	
	<p class="lighter">
		<!--  COMENTAR EN PRPDUCCION 		-->
		<p><strong><label for="idFuenteSelect">Pais</label></strong>
			<input type="hidden" name="fuente" id="fuente" value="" />
			<div dojoType="dojo.data.ItemFileReadStore" jsId="fuenteStore" urlPreventCache="true" clearOnClose="true" ></div>
				<select dojotype="dijit.form.FilteringSelect" store="fuenteStore" id="idFuenteSelect" required="false" autoComplete="true"></select>               			           			    					
		</p>

		<p>
			<strong><span id="tEntidad"><label for="idEntidadSelect">Entidad Federativa</label></span></strong>
			<input type="hidden" name="district" id="district" value="" />
			<div dojoType="dojo.data.ItemFileReadStore" jsId="entidadFederativaStore" urlPreventCache="true" clearOnClose="true" ></div>
				<select dojotype="dijit.form.FilteringSelect" store="entidadFederativaStore" id="idEntidadSelect" required="false" autoComplete="true"></select>               			           			    					
		</p>
		<p>
			<strong><span id="tMunicipio"><label for="idMunicipioSelect">Delegación o Municipio</label></span></strong>
			<input type="hidden" name="idMunicipio" id="idMunicipio" value="" />
			<div dojoType="dojo.data.ItemFileReadStore" jsId="municipioStore" urlPreventCache="true" clearOnClose="true" ></div>
				<select dojotype="dijit.form.FilteringSelect" store="municipioStore" id="idMunicipioSelect" required="false" 
				 autoComplete="false"></select>
		</p>
		<p class="lighter">
			<strong><label for="idRegion">Regi&oacute;n</label></strong>
			<input type="hidden" name="region" id="region" value="" />
			<div dojoType="dojo.data.ItemFileReadStore" jsId="regionStore" urlPreventCache="true" clearOnClose="true" ></div>
				<select dojotype="dijit.form.FilteringSelect" store="regionStore" id="idRegion" required="false" autoComplete="true"></select>
		</p>
	</div>
	<div class="specific_block">
		<p>
			<strong><label for="idAreaLaboral">Area laboral</label></strong>
			<input type="hidden" name="area" id="area" value="" />
			<div dojoType="dojo.data.ItemFileReadStore" jsId="areaLaboralStore" urlPreventCache="true" clearOnClose="true" ></div>
				<select dojotype="dijit.form.FilteringSelect" store="areaLaboralStore" id="idAreaLaboral" required="false" autoComplete="true"></select>               			           			    					
		</p>
		<p>
			<strong><label for="idOcupacion">Ocupación</label></strong>
			<input type="hidden" name="ocupacion" id="ocupacion" value="" />
			<div dojoType="dojo.data.ItemFileReadStore" jsId="ocupacionStore" urlPreventCache="true" clearOnClose="true" ></div>
				<select dojotype="dijit.form.FilteringSelect" store="ocupacionStore" id="idOcupacion" required="false" autoComplete="true"></select>
		</p>
	</div>
	<div class="specific_block">
		<p>
			<strong><label for="idEscolaridad">Escolaridad</label></strong>
			<input type="hidden" name="escolaridad" id="escolaridad" value="" />
			<div dojoType="dojo.data.ItemFileReadStore" jsId="escolaridadStore" urlPreventCache="true" clearOnClose="true" ></div>
				<select dojotype="dijit.form.FilteringSelect" store="escolaridadStore" id="idEscolaridad" required="false" autoComplete="true"></select>               			           			    					
		</p>
		<p>
			<strong><label for="idCarrera">Carrera</label></strong>
			<input type="hidden" name="carrera" id="carrera" value="" />
			<div dojoType="dojo.data.ItemFileReadStore" jsId="carreraStore" urlPreventCache="true" clearOnClose="true" ></div>
				<select dojotype="dijit.form.FilteringSelect" store="carreraStore" id="idCarrera" required="false" autoComplete="true"></select>
		</p>
	</div>
	<div class="specific_block last">
		<p>
			<strong><label for="idSalario">Salario mensual</label></strong>
			<input type="hidden" name="salario" id="salario" value="" />
			<div dojoType="dojo.data.ItemFileReadStore" jsId="salarioStore" urlPreventCache="true" clearOnClose="true" ></div>
				<select dojotype="dijit.form.FilteringSelect" store="salarioStore" id="idSalario" required="false" autoComplete="true"></select>               			           			    					               			           			    					               			           			    					
		</p>
		<p>
			<strong><label for="idEdad">Rango de edad</label></strong>
			<input type="hidden" name="edad" id="edad" value="" />
			<div dojoType="dojo.data.ItemFileReadStore" jsId="edadStore" urlPreventCache="true" clearOnClose="true" ></div>
				<select dojotype="dijit.form.FilteringSelect" store="edadStore" id="idEdad" required="false" autoComplete="true"></select>               			           			    					               			           			    					
		</p>
	</div>
	<br clear="all" />
	<div style="text-align: center;">
		<input type="button" id="btnBuscar" value="Buscar"  class="boton" onclick="doSubmit('filter');" />
	</div>
	<div id="table"></div>
</form>
<script type="text/javascript">
	function filSelectEntidad() {
		var vPais = dijit.byId('idFuenteSelect').get('value');
  		var vEntidad = dijit.byId('idEntidadSelect').get('value');
  		entidadFederativaStore.url="";
  		entidadFederativaStore.close();	
		municipioStore.url="";
		municipioStore.close();	
		dijit.byId('idEntidadSelect').reset();
		dijit.byId('idMunicipioSelect').reset();
		if (vPais==3)
  			dijit.byId('idRegion').setDisabled(true);
  		else dijit.byId('idRegion').setDisabled(false);
  		
  		if(vPais>0){
  		if(vPais!=3){
  			dojo.byId('tEntidad').innerHTML = "Entidad federativa";
			dojo.byId('tMunicipio').innerHTML = "Municipio y/o delegación";
			dijit.byId('idMunicipioSelect').setDisabled(false);
  		entidadFederativaStore.url =  "<%=context%>specificsearch.do?method=entidadesFederativasOpcion";
  		entidadFederativaStore.close();
  		}else{
  			dojo.byId('tEntidad').innerHTML = "Provincia";
			dijit.byId('idMunicipioSelect').setDisabled(true);
			entidadFederativaStore.url =  "<%=context%>specificsearch.do?method=provinciasCanadaOpcion";
  	  		entidadFederativaStore.close();
  			
  		}
  	}
	}
	function filSelectMunicipio() {
		var vPais = dijit.byId('idFuenteSelect').get('value');
  		var vEntidad = dijit.byId('idEntidadSelect').get('value');
  		municipioStore.url="";
		municipioStore.close();	
		dijit.byId('idMunicipioSelect').reset();
		if (vEntidad > 0 || vPais == 3)
  			dijit.byId('idRegion').setDisabled(true);
  		if (vEntidad == 0 && vPais!=3)
  			dijit.byId('idRegion').setDisabled(false);
  		dijit.byId('idMunicipioSelect').reset();
  		if(vEntidad>0){
  		if(vPais!=3){
  			
  		municipioStore.url = "<%=context%>specificsearch.do?method=obtenerMunicipio&idEntidad="+ vEntidad;
        municipioStore.close();
  		}else{
  			
  			municipioStore.url = "<%=context%>specificsearch.do?method=obtenerCiudadCanada&idProvincia="+ vEntidad;
  	        municipioStore.close();
  			
  		}
  		}
  			
  		
  	}
  	function filSelectOccupation() {
  		var vArea = dijit.byId('idAreaLaboral').get('value');
  		ocupacionStore.url = "<%=context%>specificsearch.do?method=occupation&idAreaLaboral="+ vArea;
        ocupacionStore.close();
  	}
  	function filSelectCareer() {
  		var vEsc = dijit.byId('idEscolaridad').get('value');
  		carreraStore.url = "<%=context%>specificsearch.do?method=career&idEscolaridad="+ vEsc;
        carreraStore.close();
  	}
  	function disableEntity() {
  		var vRegion = dijit.byId('idRegion').get('value');
  		if (vRegion > 0) {
  			dijit.byId('idEntidadSelect').setDisabled(true);
  			dijit.byId('idMunicipioSelect').setDisabled(true);
  		}
  		if (vRegion == 0) {
  			dijit.byId('idEntidadSelect').setDisabled(false);
  			dijit.byId('idMunicipioSelect').setDisabled(false);
  		}
  	}
		dojo.addOnLoad(function() {
	
		fuenteStore.url = "<%=context%>specificsearch.do?method=fuenteOpcion";
		fuenteStore.close();
				
			
		dojo.connect(dijit.byId('idFuenteSelect'), "onChange", function() {
				filSelectEntidad();
			});
	
		dojo.connect(dijit.byId('idEntidadSelect'), "onChange", function() {
			filSelectMunicipio();
		});
		dojo.connect(dijit.byId('idAreaLaboral'), "onChange", function() {
			filSelectOccupation();
		});
		dojo.connect(dijit.byId('idEscolaridad'), "onChange", function() {
			filSelectCareer();
		});
		dojo.connect(dijit.byId('idRegion'), "onChange", function() {
			disableEntity();
		});
		
		loadArea();
		loadSchooling();
		loadSalary();
		loadAges();
		loadRegion();
		//loadEntidad();
		
	
	});
		function loadEntidad() {
			entidadFederativaStore.url =  "<%=context%>specificsearch.do?method=entidadesFederativasOpcion";
	  		entidadFederativaStore.close();
	        areaLaboralStore.fetch({
	        	onComplete: function(items, request) {                  	
	            	if (items.length == 0) return;         
	              		dijit.byId('idEntidadSelect').attr('value', items[0].value);
	            }
	        });
	  	}
	function loadArea() {
		areaLaboralStore.url="<%=context%>specificsearch.do?method=filteringSelect&filter=20";
		areaLaboralStore.close();
        areaLaboralStore.fetch({
        	onComplete: function(items, request) {                  	
            	if (items.length == 0) return;         
              		dijit.byId('idAreaLaboral').attr('value', items[0].value);
            }
        });
  	}
  	function loadSchooling() {
		escolaridadStore.url="<%=context%>specificsearch.do?method=filteringSelect&filter=8";
		escolaridadStore.close();
        escolaridadStore.fetch({
        	onComplete: function(items, request) {                  	
            	if (items.length == 0) return;         
              		dijit.byId('idEscolaridad').attr('value', items[0].value);
            }
        });
  	}
  	function loadSalary() {
		salarioStore.url="<%=context%>specificsearch.do?method=salary";
		salarioStore.close();
        salarioStore.fetch({
        	onComplete: function(items, request) {                  	
            	if (items.length == 0) return;         
              		dijit.byId('idSalario').attr('value', items[0].value);
            }
        });
  	}
  	function loadAges() {
		edadStore.url="<%=context%>specificsearch.do?method=ages";
		edadStore.close();
        edadStore.fetch({
        	onComplete: function(items, request) {                  	
            	if (items.length == 0) return;         
              		dijit.byId('idEdad').attr('value', items[0].value);
            }
        });
  	}
  	function loadRegion() {
		regionStore.url="<%=context%>specificsearch.do?method=regions";
		regionStore.close();
        regionStore.fetch({
        	onComplete: function(items, request) {                  	
            	if (items.length == 0) return;         
              		dijit.byId('idRegion').attr('value', items[0].value);
            }
        });
  	}
</script>
<script>
	doSubmitPager('page');
</script>