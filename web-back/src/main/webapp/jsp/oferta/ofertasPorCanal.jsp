<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
  String context = request.getContextPath() + "/";


%><script src="dojotoolkit/dojo/dojo.js" djConfig="parseOnLoad: true, locale:'es'"></script>
<script type="text/javascript">
    dojo.require("dijit.dijit"); // loads the optimized dijit layer
    dojo.require("dijit.form.Form");
    dojo.require("dijit.Dialog");
    dojo.require("dijit.form.Button");

  function doSubmitPager(method) {
      dojo.byId('method').value = method;
      dojo.xhrPost({url: 'canalOfertas.do', form:'FilterOfferForm', timeout:180000, 
                    load: function(data) {
                          dojo.byId('table').innerHTML = data;
                    }});
  }
  
  //Realiza la navegación entre paginas
  	function doSubmitPage(page){
  		dojo.byId('method').value = "goToPage";
  		dojo.byId('goToPageNumber').value = page;
  		dojo.xhrPost({url: 'canalOfertas.do', form:'FilterOfferForm', timeout:180000, 
  			load: function(data){
  			dojo.byId('table').innerHTML=data;
  		}});
  	}

  /* Ordena tabla por columna */
  function orderBy(orden,tipocolumna,canal){
	 		
	dojo.xhrPost({url: 'canalOfertas.do?method=orderByColumn&tipoOrden=' + orden + '&tipoColumna=' + tipocolumna + '&ofertaCanal=' + canal, timeout:180000, 
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

    <form name="FilterOfferForm" id="FilterOfferForm" action="canalOfertas.do" method="post" dojoType="dijit.form.Form">       
        <input type="hidden" name="method" id="method" value="init" />
        <input type="hidden" name="searchType" id="searchType" />
        <input type="hidden" name="goToPageNumber" id="goToPageNumber" value="1" />
        <div id="table"></div>
    </form>

        

<script>
	doSubmitPager('page');
</script>

