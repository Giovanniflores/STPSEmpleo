<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
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

	

	<div id="miespaciocand" dojoType="dojox.mobile.View" selected="true">
		<h1 dojoType="dojox.mobile.Heading">
				<div dojoType="dojox.mobile.ToolBarButton" icon="images/ico_exit.jpg" style="width:45px;float:right;" href="logout.m" transitionDir="-1"></div>
			</h1>
		<div dojoType="dojox.mobile.RoundRect" >
			<div class="mblVariableHeight" style="font-size: small;">
				<jsp:include page="/empresaEspacio.m" flush="true">
				 <jsp:param name="method" value="detalleEmpresa" />
				</jsp:include>	
			</div>
	    </div>

	   
		<div id="listaAcciones" dojoType="dojox.mobile.View" selected="true" style="font-size: 10px;'">
		<ul dojoType="dojox.mobile.RoundRectList">
			<li dojoType="dojox.mobile.ListItem" 
				 moveTo="bar" href="${context}buscarCandidatosEmpresa.m?method=init" transition="slide">&nbsp;&nbsp;Busqueda de candidatos</li>
			<li dojoType="dojox.mobile.ListItem" 
				 moveTo="bar" href="${context}ofertasEmpresa.m?method=init">&nbsp;&nbsp;Mis ofertas de empleo</li>
			<li dojoType="dojox.mobile.ListItem" 
				moveTo="bar" href="${context}entrevistaProgramada.m?method=entrevistaProgramadaEmpresas">&nbsp;&nbsp;Entrevistas programadas</li>
		</ul>

	</div>
	
	
	</div>
	
	 <div id="bar" dojoType="dojox.mobile.View">
	 
	 </div>
	
	

    </body>
</html>