
<script type="text/javascript">
    dojo.require("dijit.dijit"); // loads the optimized dijit layer
    dojo.require("dijit.form.Form");
    dojo.require("dijit.Dialog");
    dojo.require("dijit.form.Button");

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

  function doSubmitPager(method) {
      dojo.byId('method').value = method;
      dojo.xhrPost({url: 'buscarcandidatos.do', form:'SearchCandidatesForm', timeout:180000, 
                    load: function(data) {
                          dojo.byId('reporte').innerHTML = data;
                    }});
  }  	

  function doSubmitPagina(pagina, dif){
		
	   	dojo.xhrPost({url: 'buscarcandidatos.do?method=goToPage&goToPageNumber='+pagina, timeout:180000, 
					  load: function(dataCand){
					      dojo.byId('reporte').innerHTML=dataCand;
					  }});
  }
  
</script>
<noscript>Funciones de JavaScript desactivadas por navegador</noscript>
	
    <div id="reporte" name="reporte">
    </div>

    <form name="SearchCandidatesForm" id="SearchCandidatesForm" action="buscarcandidatos.do" method="post" dojoType="dijit.form.Form">       
        <input type="hidden" name="method" id="method" value="search" />
        <input type="hidden" name="searchQ" id="searchQ" value="${searchQ}"/>
    </form>

	<script>
	    doSubmitPager('page');
	</script>
