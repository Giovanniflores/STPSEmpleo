<%@ page import="java.util.List,
	mx.gob.stps.portal.core.ws.ofertas.vo.OfertaEmpleoOutVO,
	mx.gob.stps.portal.web.infra.utils.Constantes,
	mx.gob.stps.portal.core.oferta.registro.vo.RegistroEntidadesVO,
	java.util.ArrayList;"
	language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>

<%!
List<OfertaEmpleoOutVO> listaBolsas;
%>

<script type="text/javascript">
dojo.require("dijit.dijit"); // loads the optimized dijit layer
dojo.require("dijit.form.Form");    
dojo.require("dijit.form.RadioButton");
dojo.require("dijit.form.TextBox");
dojo.require("dijit.form.ComboBox");
dojo.require("dojo.data.ItemFileReadStore"); 
dojo.require("dijit.form.FilteringSelect");

function doSubmit(method){
	i=0;
	if(dijit.byId('ofertasSFPForm').isValid()){
		document.ofertasSFPForm.method.value=method;
		dojo.byId('method').value=method;
		dojo.byId('ofertasSFPForm').submit();
	}
}

function doSubmitPager(method){
	 dojo.xhrPost({url: 'ofertasSFP.do?method='+method+'&tablaPager=_OFERTAS_SFP', timeout:180000, 
		  load: function(data){
		      dojo.byId('tabla').innerHTML=data;
		  }});	
}

function mostrarDetalle(idOfertaSFP){
	dojo.byId('method').value = 'buscarOfertaSFP';
	dojo.byId('idOferta').value = idOfertaSFP;
	document.ofertasSFPForm.submit();
}


//function doSubmitPagina(pagina, dif){
function doSubmitPage(pagina){	
 	var divTabla = 'tabla';
 	var dif = '_OFERTAS_SFP' 	
   	dojo.xhrPost({url: 'ofertasSFP.do?method=goToPage&tablaPager='+dif+'&goToPageNumber='+pagina, timeout:180000,
				  load: function(data){
				      dojo.byId(divTabla).innerHTML=data;
				  }});
}  

/* Ordena tabla por columna */
function orderBy(orden,tipocolumna){
	var divTabla = 'tabla'; 	
	var dif = '_OFERTAS_SFP' 	
	dojo.xhrPost({url: 'ofertasSFP.do?method=orderByColumn&tipoOrden=' + orden + '&tipoColumna=' + tipocolumna + '&tablaPager='+dif, timeout:180000, 
		  load: function(data){
		      dojo.byId(divTabla).innerHTML=data;
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

<form dojoType="dijit.form.Form" name="ofertasSFPForm" id="ofertasSFPForm">
<input type="hidden" name="method" id='method' value="init"/>
<input type="hidden" name="idOferta" id="idOferta"/>
<input type="hidden" name="tablaPager" id="tablaPager"  dojoType="dijit.form.TextBox" value=""/>
<input type="hidden" name="goToPageNumber" id="goToPageNumber" value="1" dojoType="dijit.form.TextBox"/>


<div id="msg"></div>   
          
</form>


<div id="tabla"></div>

<script type="text/javascript">
doSubmitPager('pageTable');
</script>