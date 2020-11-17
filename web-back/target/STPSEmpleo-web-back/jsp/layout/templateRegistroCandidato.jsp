<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles"%>
<%@page import="java.util.Calendar" %>

<%String context = request.getContextPath() +"/";%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd"> 
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="es" lang="es"> 
<head> 
	<link rel="P3Pv1"         href="https://www.empleo.gob.mx/w3c/p3p.xml" /> 
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" /> 
	 
	<title><tiles:getAsString name="title"/></title> 

	<style type="text/css">
		@import "dojotoolkit/dojo/resources/dojo.css";
		@import "dojotoolkit/dijit/themes/claro/claro.css";
		@import "${PATH_CSS_SWB}css_registro_candidato/estilos_prerregistro.css";
	
		.claro .dijitDialogTitleBar {
			background-color: #4F6710;
			color: #FFFFFF;
		    font-weight: bold;
		    text-decoration: none;
	    }
	</style> 
	
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