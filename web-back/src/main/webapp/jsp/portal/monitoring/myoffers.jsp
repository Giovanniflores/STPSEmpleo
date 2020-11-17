<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="mx.gob.stps.portal.web.infra.utils.Utils"%>
<%@ page import="mx.gob.stps.portal.core.candidate.vo.PerfilVO"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="java.util.Date"%>
<%@ page import="mx.gob.stps.portal.web.infra.utils.DateConverter"%>
<%@ page import="mx.gob.stps.portal.core.oferta.detalle.vo.OfertaEmpleoJB"%>
<%@ page import="mx.gob.stps.portal.core.infra.utils.Constantes.*"%>
<%@ page import="mx.gob.stps.portal.web.offer.form.MyOffersForm"%>

<script>	
	
    function callable(form, field) {
		selectCheck(form, form.allCheck.checked, field);
    }
	function selectCheck(form, obj, field) {
		var i=form.elements.length;
		for(var k=0;k<i;k++) {
			if(form.elements[k].name==field) {
				form.elements[k].checked=obj;
			}
		}
	}
	function mostrarResumen(idResumen) {
		var thisRow = dojo.style(idResumen, "display");
		if(thisRow == 'block'){
			dojo.style(idResumen, "display", "none");
			dojo.style(eval("\"" + "hide" + idResumen + "\""), "display", "none");
			dojo.style(eval("\"" + "show" + idResumen + "\""), "display", "block");
		}
		if(thisRow == 'none'){
			dojo.style(idResumen, "display", "block");
			dojo.style(eval("\"" + "hide" + idResumen + "\""), "display", "block");
			dojo.style(eval("\"" + "show" + idResumen + "\""), "display", "none");
		}
	}

	function mostrarTodas(tipo, totReg, prefix) {
		
		var vSelector;
		var queryObjects;
		
		// mostrar resumen
		if(tipo == 1) {
			vSelector = "div[id^=hideAll"+prefix+"]";		
			queryObjects = dojo.query(vSelector);	
			queryObjects.style("display", "block");

			vSelector = "div[id^=showAll"+prefix+"]";
			queryObjects = dojo.query(vSelector);
			queryObjects.style("display", "none");
			
			vSelector = "div[id^="+prefix+"]";
			queryObjects = dojo.query(vSelector);			
			queryObjects.style("display", "block");
		}
		
		// ocultar resumen
		if(tipo == 2){
    			vSelector = "div[id^=hideAll"+prefix+"]";
    			queryObjects = dojo.query(vSelector);
    			queryObjects.style("display", "none");
    			
    			vSelector = "div[id^=showAll"+prefix+"]";
    			queryObjects = dojo.query(vSelector);
    			queryObjects.style("display", "block");    			

    			vSelector = "div[id^="+prefix+"]";
    			queryObjects = dojo.query(vSelector);			
    			queryObjects.style("display", "none");
    	}

	}
	
	function doSubmitInitPagers(){
		doSubmitPagerOffers('pageTable');
		doSubmitPagerOffersFindMe('pageTable');
	}
	
  	function doSubmitPagerOffers(method){
		 dojo.xhrPost({url: 'misofertas.do?method='+method+'&tablaPager=_OFFERS', timeout:180000, 
					  load: function(dataCand){
					      dojo.byId('divOffers').innerHTML=dataCand;
					  }});
	}

  	function doSubmitPagerOffersFindMe(method){
		 dojo.xhrPost({url: 'misofertas.do?method='+method+'&tablaPager=_OFFERSFINDME', timeout:180000, 
					  load: function(dataCand){
					      dojo.byId('divOffersFindMe').innerHTML=dataCand;
					  }});
	}  	
  	
	function doSubmitPagina(pagina, dif){
	 	var divTabla = '';
	 	if (dif=='_OFFERS'){
	 		divTabla =  'divOffers';
	 	}else {
	 		divTabla =  'divOffersFindMe';
	 	}
	 	
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
  	
</script>
<noscript>Funciones de JavaScript desactivadas por navegador</noscript>

<div id="mis_ofertas">

	<div class="contenedor_contenido">
		<div id="divOffers"></div>
		<div id="divOffersFindMe"></div>		
    </div>
    
	<script>
		doSubmitInitPagers();
	</script>    
    
</div>
