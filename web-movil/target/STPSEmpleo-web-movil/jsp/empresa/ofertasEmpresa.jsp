<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%String context = request.getContextPath() +"/";%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
	<jsp:include page="../seguridad/header.jsp"/>

<script type="text/javascript">
 	
 	function doSubmitPager(method,paginaActual) {
       document.OfertasEmpresaForm.paginaActual.value = paginaActual;
      dojo.byId('method').value = "cambiarPagina";
      
      if(method=="prev"){
		
		document.OfertasEmpresaForm.paginaAnterior.value=true;
		document.OfertasEmpresaForm.paginaSiguiente.value=false;
		
	}else{
		
		document.OfertasEmpresaForm.paginaSiguiente.value=true;
		document.OfertasEmpresaForm.paginaAnterior.value=false;
		
		
	}
   
      dojo.xhrPost({url: 'ofertasEmpresa.m', form:'OfertasEmpresaForm', timeout:180000, 
                    load: function(data) {
                          var container = dojo.byId('reporte');
                          
                         container.innerHTML = data;
                         dojox.mobile.parser.parse(container);
                         
                         
                    }});
  }
 	
 	
 	
 		//dojo.require("dijit.dijit");
	 	dojo.require("dojox.mobile.parser"); // Load the widget parser
	 	dojo.require("dojox.mobile"); // Load the base lib
	 	dojo.require("dojox.mobile.TabBar");
	 	dojo.require("dojox.mobile.TabContainer");
	 	dojo.requireIf(!dojo.isWebKit, "dojox.mobile.compat"); // If not a WebKit-based client, load compat
		dojo.require("dijit.form.Form");
		dojo.require("dojox.mobile.TabBar");
		dojo.require("dojox.mobile.compat");
		//dojo.require("dojox.mobile.TabBarButton");
	
		
 	</script>
 	
 	<script type="text/javascript">

  var _gaq = _gaq || [];
  _gaq.push(['_setAccount', 'UA-36829222-1']);
  _gaq.push(['_setDomainName', 'empleo.gob.mx']);
  _gaq.push(['_setAllowLinker', true]);
  _gaq.push(['_trackPageview']);

  (function() {
    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
  })();

</script>
 	

</head>
<body>

	<!-- application will go here -->



	<form id="ofertasEmpresa" name="ofertasEmpresa" method="post"
		action="${context}ofertasEmpresa.m?method=init" >
		<div id="miespaciocand" dojoType="dojox.mobile.View" selected="true">
		<h1 dojoType="dojox.mobile.Heading">
				<div dojoType="dojox.mobile.ToolBarButton" icon="images/ico_home.jpg" moveTo="bar" href="<%=request.getContextPath()%>/empresaEspacio.m?method=init" transitionDir="-1"></div>
				<div dojoType="dojox.mobile.ToolBarButton" icon="images/ico_exit.jpg" style="width:45px;float:right;" href="logout.m" transitionDir="-1"></div>
			</h1>
			
			<!-- <h2 dojoType="dojox.mobile.RoundRectCategory">Espacio para
				empresas</h2>
			<div dojoType="dojox.mobile.RoundRect" >
				<div class="mblVariableHeight" style="font-size: small;">
					<jsp:include page="/empresaEspacio.m" flush="true">
						<jsp:param name="method" value="detalleEmpresa" />
					</jsp:include>
				</div>
			</div> -->
			

		

		
		

		
	<div id="reporte" name="reporte" dojoType="dojox.mobile.View" selected="true">
			<jsp:include page="/jsp/empresa/listaOfertas.jsp" flush="true"/>
	</div>
	</div>
	<div id="bar" dojoType="dojox.mobile.View"></div>
	</form>
	
		<form name="OfertasEmpresaForm" id="OfertasEmpresaForm" action="${context}ofertasEmpresa.m" method="post" dojoType="dijit.form.Form">       
        <input type="hidden" name="method" id="method" value="init" />
        <input type="hidden" id="paginaActual" name="paginaActual" value="${ofertasEmpresaForm.paginaActual}">
		<input type="hidden" id="paginaSiguiente" name="paginaSiguiente" value="false">
		<input type="hidden" id="paginaAnterior" name="paginaAnterior" value="false">
    </form>
	
	

</body>
</html>


