<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%String context = request.getContextPath() +"/";%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
	<jsp:include page="../seguridad/header.jsp"/>

<script type="text/javascript">
 		//dojo.require("dijit.dijit");
	 	dojo.require("dojox.mobile.parser"); // Load the widget parser
	 	dojo.require("dojox.mobile"); // Load the base lib
	 	dojo.requireIf(!dojo.isWebKit, "dojox.mobile.compat"); // If not a WebKit-based client, load compat

		dojo.require("dojox.mobile.TabBar");
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


	
	<form id="busquedaCandidatos" name="busquedaCandidatos" method="post" action="${context}buscarCandidatosEmpresa.m?method=busquedaCandidatos">
	<div id="miespaciocand" dojoType="dojox.mobile.View" selected="true">
		<h1 dojoType="dojox.mobile.Heading">
				<div dojoType="dojox.mobile.ToolBarButton" icon="images/ico_home.jpg" moveTo="bar" href="<%=request.getContextPath()%>/empresaEspacio.m?method=init" transitionDir="-1"></div>
				<div dojoType="dojox.mobile.ToolBarButton" icon="images/ico_exit.jpg" style="width:45px;float:right;" href="logout.m" transitionDir="-1"></div>
			</h1>
		 <!-- <h2 dojoType="dojox.mobile.RoundRectCategory">Espacio para Empresas</h2>
	    <div dojoType="dojox.mobile.RoundRect" >
			<div class="mblVariableHeight" style="font-size: small;">
				<jsp:include page="/empresaEspacio.m" flush="true">
				 <jsp:param name="method" value="detalleEmpresa" />
				</jsp:include>	
			</div>
	    </div>-->

	<div id="busquedaCandidatos" dojoType="dojox.mobile.View" selected="true">
	<div dojoType="dojox.mobile.RoundRect"  align="center">
	<div style="text-align: center;" class="titNar">Buscador de candidatos</div>
			<input type="text" name="searchQ" id="searchQ" style="width: 100%;" />
			<button dojoType="dojox.mobile.Button" class="baseBtn roundBtn navyBtn" style="width:90px;" onclick="javascript:submit();">Buscar</button>
			 <p class="ejemplo">Ej. Asistente, $5000</p><br/>
		</div>
	</div>
	
	</div>

 <div id="bar" dojoType="dojox.mobile.View">
	 
	 </div>
	

</form>

</body>
</html>