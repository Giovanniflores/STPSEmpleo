<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles"%>
<%@ page import="java.util.Calendar" %>
<%@page import="mx.gob.stps.portal.web.infra.utils.PropertiesLoader"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd"> 
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="es" lang="es"> 
<head> 
<link rel="P3Pv1" href="https://www.empleo.gob.mx/w3c/p3p.xml" /> 
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" /> 
 
<title><tiles:getAsString name="title"/></title> 

<style type="text/css">
	@import "dojotoolkit/dojo/resources/dojo.css";
	@import "dojotoolkit/dijit/themes/claro/claro.css";
</style>

<style>
.claro .dijitDialogTitleBar {
	background-color: #F47513;
	color: #FFFFFF;
    font-weight: bold;
    text-decoration: none;
    }
</style>

<script src="dojotoolkit/dojo/dojo.js" djConfig="parseOnLoad: true, locale:'es'"></script>

<link href='https://fonts.googleapis.com/css?family=Droid+Sans' rel='stylesheet' type='text/css' />
<link href='https://fonts.googleapis.com/css?family=Puritan' rel='stylesheet' type='text/css' />

<link href="${PATH_CSS_SWB_APP}estilos_empleo.css" rel="stylesheet" type="text/css" />
<link href="${PATH_CSS_SWB_APP}estilos_canal.css" rel="stylesheet" type="text/css" />
<link href="${PATH_CSS_SWB_APP}estilos_detalle_vacante.css" rel="stylesheet" type="text/css" />
<link href="${PATH_CSS_SWB_APP}estilos_lightBoxPost.css" rel="stylesheet" type="text/css" />
<link href="${PATH_CSS_SWB_APP}estilos_mi_espacio.css" rel="stylesheet" type="text/css" />
<link href="${PATH_CSS_SWB}css_aplicacion/estilos_tabla.css" rel="stylesheet" type="text/css" />
<link href="${PATH_CSS_SWB_APP}estilos_conocer.css" rel="stylesheet" type="text/css" />

<%
String urliFrameSWB = PropertiesLoader.getInstance().getProperty("miespacio.iframeswb.script");
int height = 0;

String action = (String)session.getAttribute("ACTION_REQUESTED");
if (action!=null){ 
	if ("detalleoferta".equalsIgnoreCase(action)){            height = 3000;
	} else if ("viewCandidateInfo".equalsIgnoreCase(action)){ height = 2000;
	}
}
%>
<script type="text/javascript">
	function updateIframeHeight() {
		var iframe = document.getElementById('hiddenIframe');
		var newHeight = parseInt(document.body.offsetHeight,10) + 10;
		iframe.src = '<%=urliFrameSWB%>?height=' + <%=height%>;
	}
</script>

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

<body class='claro' onload="updateIframeHeight();">
<div id="wrapper" class="offIframe">
	<div id="cuerpo_int">
    <div id="contenido_principal" class="detalle">
        <div id="contenido">
        	<tiles:insert name="body"/>
        </div>
      
        <!-- Más ofertas de empleo -->
		<div class="opciones_col">			
	    	<div id="masOfertas"></div>
		</div>
				
    </div>

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