<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%@ page import="mx.gob.stps.portal.core.oferta.busqueda.vo.OfertaPorCanalVO"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%String context = request.getContextPath() +"/";%>

<!-- <script src="dojotoolkit/dojo/dojo.js" djConfig="parseOnLoad: true, locale:'es'"></script>-->

<script type="text/javascript">
	/* 
    dojo.require("dijit.dijit"); // loads the optimized dijit layer
    dojo.require("dijit.form.Form");
    dojo.require("dijit.Dialog");
    dojo.require("dijit.form.Button");
    */
	function doSubmitPage(page) {
    	//alert(page);
    	dojo.byId('method').value = "goToPage";
    	dojo.byId('goToPageNumber').value = page;
    	dojo.xhrPost({url: 'ocupate.do', form:'OffersPerCanalForm', timeout:180000,
    	load: function(data){    		
    		dojo.byId('reporte').innerHTML=data;    
    		location.href = '#showAll';	
    		}});
  	}

  function doSubmitPager(method) {
      dojo.byId('searchQ').value = '${searchQ}';
      dojo.byId('method').value = method;
      dojo.xhrPost({url: 'ocupate.do', form:'OffersPerCanalForm', timeout:180000, 
                    load: function(data) {
                          dojo.byId('reporte').innerHTML = data;
                    }});
  }

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

  /* Ordena tabla por columna */
  function orderBy(orden,tipocolumna){
	 		
	dojo.xhrPost({url: 'ocupate.do?method=orderByColumn&tipoOrden=' + orden + '&tipoColumna=' + tipocolumna, timeout:180000, 
		  load: function(dataCand){
		      dojo.byId('reporte').innerHTML=dataCand;
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

	<div class="col-sm-12">
		<div class="page-header">
			<h3>Ofertas de empleo</h3>
		</div>
	</div>
	
	<div class="col-sm-12">
		<div class="tab_block">
			<c:if test="${not empty USUARIO_APP and USUARIO_APP.candidato}">
			<div id="returnME" style="display:block;"  align="left">
			  <a href="javascript:window.top.location='<c:url value="/miEspacioCandidato.do"/>'" class="expand">
			    <strong>Regresar a mi espacio</strong>
			  </a>
			</div>
			</c:if>
			<c:if test="${not empty USUARIO_APP and USUARIO_APP.empresa}">
			<div id="returnME" style="display:block;"  align="left">
			  <a href="javascript:window.top.location='https://www.empleo.gob.mx/es_mx/empleo/Empresas_Registradas'" class="expand">
			    <strong>Regresar a mi espacio</strong>
			  </a>
			</div>
			</c:if>
		</div>
	</div>
	<div class="col-sm-12">
	    <div id="reporte" name="reporte" class="ocupate_ofertas">
	    	<jsp:include page="/jsp/portal/monitoring/registrosOcupate.jsp"/>       
	    </div>
	    <form name="OffersPerCanalForm" id="OffersPerCanalForm" action="ocupate.do" method="post" dojoType="dijit.form.Form">       
	        <input type="hidden" name="method" id="method" value="init" />
	        <input type="hidden" name="searchQ" id="searchQ" value="${searchQ}" />
	        <input type="hidden" name="goToPageNumber" id="goToPageNumber" value="" />
	    </form>
    </div>
<%--
<script>
    doSubmitPager('page');
</script>
--%>
