<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles"%>
<%@page import="java.util.Calendar" %>

<% 
	String swb = (String)application.getAttribute("DOMAIN_PORTAL");
	String context = request.getContextPath() +"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd"> 
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="es" lang="es"> 
	<head> 
		<link rel="P3Pv1" href="https://www.empleo.gob.mx/w3c/p3p.xml" /> 
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
		<meta http-equiv="Reply-to" content="empleo@stps.gob.mx" />
		<meta name="Title" content="Portal del Empleo" />
		<meta http-equiv="X-UA-Compatible" content="IE=8" />
		<meta name="author" content="Sergio Téllez Vázquez"/>
		<meta name="description" content="El Portal del Empleo ofrece información y servicios que te apoyarán a mejorar tu búsqueda de empleo de la manera más fácil y novedosa."/>
		<meta name="keywords" content="Empleo, Ofertas empleo, Estad&iacute;sticas del mercado laboral, Ofrezco empleo, Capacitaci&oacute;n, STPS, Secretar&iacute;a del Trabajo y Previsi&oacute;n Social, Ofertas de empleo en M&eacute;xico, Publicaci&oacute;n de ofertas de trabajo, Bolsa de trabajo, Curriculum Vitae, cv, "/>
		<meta name="Subject" content="Empleo" />
		<meta name="language" content="spanish" />
		<meta name="revisit" content="1 days" />
		<meta name="distribution" content="Global" />
		<meta name="robots" content="all | index | nofollow" />
		<meta name="geo.region" content="MX-DIF" />
		<meta name="geo.placename" content="Mexico City, Mexico" />
		<link href="<%=swb %>/work/models/empleo/css/estilos_empleo.css" rel="stylesheet" type="text/css" />
		<link href="<%=swb %>/work/models/empleo/css/estilos_canalB.css" rel="stylesheet" type="text/css" />
		<link href="<%=swb %>/work/models/empleo/css/estilos_mapa.css" rel="stylesheet" type="text/css" />
		<link href="<%=swb %>/work/models/empleo/css/estilos_foro.css" rel="stylesheet" type="text/css" />
		<link href="<%=swb %>/work/models/empleo/css/estilos_mi_espacio.css" rel="stylesheet" type="text/css" />
		<link href="<%=swb %>/work/models/empleo/css/estilos_lightBox.css" rel="stylesheet" type="text/css" />
		<link href="<%=swb %>/work/models/empleo/css/estilos_print.css" rel="stylesheet" type="text/css" media="print" />
		<link href="<%=swb %>/work/models/empleo/css_perfil_empresa/estilos_espacioEmpresa.css" rel="stylesheet" type="text/css" />
		<link rel="shortcut icon" media="all" type="image/x-icon" href="<%=swb %>/work/models/empleo/css/favicon.ico" /> 
		<link rel="alternate" type="application/rss+xml" title="Portal del Empleo" href="https://empleo.gob.mx/rss/DF" /> 
 
		<script type="text/javascript" > 
		    var djConfig = {
		        parseOnLoad: true,
		        isDebug: false
		    };
		</script>
		<script type="text/javascript" > 
			function employ() {
			      document.ocp.searchPlace.value = document.ocp.searchPlace.value;
			      document.ocp.searchQ.value = document.ocp.searchTopic.value;
			      document.ocp.submit();
			}
		</script>

	<title><tiles:getAsString name="title"/></title> 
	<style type="text/css">
		@import "dojotoolkit/dijit/themes/claro/claro.css";

		.claro .dijitDialogTitleBar {
			background-color: #F47513;
			color: #FFFFFF;
		    font-weight: bold;
		    text-decoration: none;
	    }
	</style> 
	
	<link href="${PATH_CSS_SWB_APP}estilos_espacioEmpresa.css" rel="stylesheet" type="text/css" />
	<link href="${PATH_CSS_SWB_APP}estilos_detalle_vacante.css" rel="stylesheet" type="text/css" />
	<link href="${PATH_CSS_SWB_APP}estilos_canalB.css" rel="stylesheet" type="text/css" />
	
	<script src="dojotoolkit/dojo/dojo.js" djConfig="parseOnLoad: true, locale:'es'"></script>

<!-- Nuevo código analytics multidominio 01/06/2017 -->
<script> 
	(function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
	(i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
	m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
	})(window,document,'script','//www.google-analytics.com/analytics.js','ga');
    ga('create', 'UA-26166631-1', 'auto', {'allowLinker': true});
	ga('require', 'displayfeatures', 'linker');
	ga('linker:autoLink', ['publicaciones.empleo.gob.mx'] );
	ga('send', 'pageview');
</script>

</head>
<body class="claro">

	<tiles:insert name="body"/>

</body>
</html>

<%
	Calendar start = (Calendar)request.getAttribute("TIME-START");
	if (start!=null){
		Calendar end = Calendar.getInstance();
		long dif = end.getTimeInMillis() - start.getTimeInMillis();
		out.write("<!-- "+ dif +" ms -->");
	}	
%>