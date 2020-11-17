<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
  String context = request.getContextPath() + "/";


%><script src="dojotoolkit/dojo/dojo.js" djConfig="parseOnLoad: true, locale:'es'"></script>
<script type="text/javascript">

  function doSubmitPager(method) {
        
      dojo.byId('method').value = method;
//      dojo.byId("tipoConsulta").value = 1;
      dojo.xhrPost({url: 'ofertasRecientes.do', form:'OfertasRecientesForm', timeout:180000, 
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

	function doSubmitPagina(pagina, dif){
	
	   	dojo.xhrPost({url: 'ofertasRecientes.do?method=goToPage&goToPageNumber='+pagina, timeout:180000, 
					  load: function(dataCand){
					      dojo.byId('reporte').innerHTML=dataCand;
					  }});
	}
	
</script>

<!--  
<h2>
	<c:if test="${OFERTA_RECIENTE==1}">
		Ofertas recientes
	</c:if>
	<c:if test="${OFERTA_RECIENTE==2}">
		Ofertas destacadas
	</c:if>	
</h2>
-->
<div class="row">
	<div class="col-sm-12">
	    <div id="reporte" name="reporte">       
	    </div>
	</div>
</div>

    <form name="OfertasRecientesForm" id="OfertasRecientesForm" action="ofertasRecientes.do" method="post">       
        <input type="hidden" name="method" id="method" value="ofertasRecientesTodas" />
    </form>

<script>
    doSubmitPager('page');
</script>

