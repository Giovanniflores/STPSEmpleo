<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.Calendar" %>

<% String context = (String)application.getAttribute("DOMAIN_PORTAL");  
   String contextApp = request.getContextPath() +"/";
   if (null != request.getSession().getAttribute("FROM_CIL"))
	   context = (String)application.getAttribute("DOMAIN_PORTAL_CIL");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd"> 
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="es" lang="es"> 
<head> 
<link rel="P3Pv1" href="https://www.empleo.gob.mx/w3c/p3p.xml" /> 
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" /> 
 
 <!-- 
 <c:if test="${not empty TITULO}">
	<title>${TITULO}</title> 
 </c:if>
 <c:if test="${empty TITULO}">
	<title><tiles:getAsString name="title"/></title> 
 </c:if> 
 -->
 
<title><tiles:getAsString name="title" /> | Portal del Empleo</title>
<meta property="og:title" content="<tiles:getAsString name="title" /> | Portal del Empleo">	
<meta name="twitter:title" content="<tiles:getAsString name="title" /> | Portal del Empleo">
<meta property="og:description" content="<tiles:getAsString name="description" />"/>
<meta name="twitter:description" content="<tiles:getAsString name="description" />"/>
<meta name="description" content="<tiles:getAsString name="title" />, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo"/>
<meta property="og:url" content="<tiles:getAsString name="url" />">
<meta name="twitter:image:src" content="<tiles:getAsString name="twitter" />">
<meta property="og:image" content="<tiles:getAsString name="facebook" />">
<meta property="og:site_name" content="Portal del Empleo">	
<meta property="og:image:type" content="image/jpeg">
<meta property="og:image:width" content="1200">
<meta property="og:image:height" content="627">
<meta property="fb:app_id" content="308925772806125" />
<meta name="twitter:card" content="summary_large_image">
<meta name="twitter:site" content="@empleogob_mx">
<meta name="twitter:creator" content="@InfotecMexico">
<meta name="author" content="infotec">

<style type="text/css">
	@import "dojotoolkit/dijit/themes/claro/claro.css";
	@import "dojotoolkit/dojo/resources/dojo.css";
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
<link href="${PATH_CSS_SWB_APP}estilos_lightBox.css" rel="stylesheet" type="text/css" />
<link href="${PATH_CSS_SWB}css_aplicacion/estilos_tabla.css" rel="stylesheet" type="text/css" />
<link href="${PATH_CSS_SWB_APP}estilos_genericos.css" rel="stylesheet" type="text/css" />

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
<jsp:include page="header.jsp" />
      
<div id="cuerpo_int">
    <jsp:include page="navegacion.jsp" />
    
        <div id="contenido_principal">
        <div id="ruta_navegacion">
        Estás en: <a href="https://www.empleo.gob.mx"  >Inicio</a> | <a href="<%=context %>/en_mx/empleo/busco_capacitacion"  >Capacitación y becas</a> | Cursos de formación para el trabajo de la SEP
       </div> 

<div class="addthis_toolbox addthis_default_style ">
<a class="addthis_counter addthis_pill_style" ></a>
</div>
<script type="text/javascript" src="http://s7.addthis.com/js/250/addthis_widget.js#pubid=xa-4dbaf80d0337b59d" ></script>


       <div id="contenido" >  
		<h2>Cursos de formación para el trabajo de la SEP</h2>
 			<tiles:insert name="body"/>
        </div>          
    	

                
    </div>
    <jsp:include page="barraLateral.jsp" />
        
        </div>
        <jsp:include page="footer.jsp" />
</div>
<jsp:include page="barraFooter.jsp" /><script type="text/javascript"> 
<!--
    function addLoadEvent(func) {
        var oldonload = window.onload;
        if (typeof window.onload != 'function') {
            window.onload = func;
        } else {
            window.onload = function() {
                if (oldonload) {
                    oldonload();
                }
                func();
            }
        }
    }

    function fileLinks()
    {
        if (!document.getElementsByTagName) return;
        var anchors = document.getElementsByTagName("a");
        for (var i=anchors.length-1; i>=0; i=i-1)
        {
            var anchor = anchors[i];
            if (anchor.href)
            {
                if( anchor.href.indexOf(".pdf")>0
                    || anchor.href.indexOf(".doc")>0
                    || anchor.href.indexOf(".xdoc")>0
                    || anchor.href.indexOf(".ppt")>0
                    || anchor.href.indexOf(".zip")>0
                    || anchor.href.indexOf(".rar")>0
                    || anchor.href.indexOf(".gzip")>0
                    || anchor.href.indexOf(".tar")>0
            )
                {
                    anchor.target = "_blank";
                }
            }
        }
    }

    addLoadEvent(fileLinks);
-->
</script>
</body></html>

<%
	Calendar start = (Calendar)request.getAttribute("TIME-START");
	if (start!=null){
		Calendar end = Calendar.getInstance();
		long dif = end.getTimeInMillis() - start.getTimeInMillis();
		out.write("<!-- "+ dif +" ms -->");
	}	
%>