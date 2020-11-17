<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%String context = request.getContextPath() +"/";%>

<script src="dojotoolkit/dojo/dojo.js" djConfig="parseOnLoad: true, locale:'es'"></script>
<script type="text/javascript">
	dojo.require("dijit.dijit"); // loads the optimized dijit layer
	dojo.require("dijit.form.Form");

  function doSubmitPagerTop(method){
		
	  dojo.byId('method').value = method;
	  dojo.byId("tipoConsulta").value = 2;
	  dojo.xhrPost({url: 'ofertasPerfilesTop.do', form:'OfertasPorPerfilForm', timeout:180000, 
				  load: function(data){
					dojo.byId('reporteTop').innerHTML=data;
				  }});
  }

</script>
<noscript>Funciones de JavaScript desactivadas por navegador</noscript>

<div id="reporteTop" name="reporteTop"></div>

	<form name="OfertasPorPerfilForm" id="OfertasPorPerfilForm" action="ofertasPerfilesTop.do" method="post" dojoType="dijit.form.Form">		
		<input type="hidden" name="method" id="method" value="page" />
		<input type="hidden" name="tipoConsulta" id="tipoConsulta" value="2" />
	</form>

<script>
	doSubmitPagerTop('page');
</script>

