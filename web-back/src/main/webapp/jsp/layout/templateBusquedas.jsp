<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.Calendar" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd"> 
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="es" lang="es"> 
<head> 
	<link rel="P3Pv1" href="https://www.empleo.gob.mx/w3c/p3p.xml" />
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" /> 
	<title><tiles:getAsString name="title"/></title>

	<style type="text/css">
		@import "dojotoolkit/dijit/themes/claro/claro.css";
		@import "dojotoolkit/dojo/resources/dojo.css";
  		@import "${PATH_CSS_SWB}css_aplicacion/estilos_busquedaEspecifica.css";
  		@import "${PATH_CSS_SWB}css_aplicacion/otras-bolsas.css"; 
		
	</style> 

	<style>
	.claro .dijitDialogTitleBar {
		background-color: #F47513;
		color: #FFFFFF;
	    font-weight: bold;
	    text-decoration: none;
	    }
	</style>

	<link href='https://fonts.googleapis.com/css?family=Droid+Sans' rel='stylesheet' type='text/css' />

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

<body class='claro'>

	<div id="wrapper">
    	<c:if test="${not empty USUARIO_APP && (USUARIO_APP.administrador || USUARIO_APP.publicador)}">
    		<div id="navegacion">
        		<tiles:insert name="menu"/>
        	</div>
    	</c:if>
        
        <div id="contenido">
			<tiles:insert name="body"/>
        </div>
	</div>
	
	<iframe id="hiddenIframe" src="" width="1" height="1" frameborder="0" scrolling="no" >
		Iframes not supported.
	</iframe>

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