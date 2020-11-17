
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<% String context = (String)application.getAttribute("DOMAIN_PORTAL");  
   String contextApp = request.getContextPath() +"/";

   if (null != request.getSession().getAttribute("FROM_CIL"))
	   context = (String)application.getAttribute("DOMAIN_PORTAL_CIL");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd"> 
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="es" lang="es"> 
<head> 
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<meta http-equiv="X-UA-Compatible" content="IE=8" />
<meta http-equiv="Reply-to" content="empleo@stps.gob.mx" />
<meta name="Title" content="Portal del Empleo" />
<meta name="author" content="Karla Swarovsky"/>
<meta name="description" content="Registro Candidato a Evento"/>
<meta name="keywords" content="Empleo, Ofertas empleo, Estad&iacute;sticas del mercado laboral, Ofrezco empleo, Capacitaci&oacute;n, STPS, Secretar&iacute;a del Trabajo y Previsi&oacute;n Social, Ofertas de empleo en M&eacute;xico, Publicaci&oacute;n de ofertas de trabajo, Bolsa de trabajo, Curriculum Vitae, cv, "/>
 
<meta name="Subject" content="Empleo" />
<meta name="language" content="spanish" />
<meta name="revisit" content="1 days" />
<meta name="distribution" content="Global" />
<meta name="robots" content="all | index | nofollow" />
<meta name="geo.region" content="MX-DIF" />
<meta name="geo.placename" content="Mexico City, Mexico" /> 
<title>*Portal del Empleo :: Registro candidato evento</title>



<link rel="shortcut icon" media="all" type="image/x-icon" href="<%=context %>/work/models/empleo/css/favicon.ico" /> 
<link rel="alternate" type="application/rss+xml" title="Portal del Empleo" href="/RSS" /> 
<script src="<%=context %>/work/models/empleo/js/dw_sizerdx.js" type="text/javascript" defer="defer" ></script>
<script src="<%=context %>/work/models/empleo/js/dw_cookies.js" type="text/javascript" defer="defer" ></script>
<script src="<%=context %>/work/models/empleo/js/dw_event.js" type="text/javascript" defer="defer" ></script>

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

<link rel="P3Pv1" href="https://www.empleo.gob.mx/w3c/p3p.xml" />
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" /> 

	<title><tiles:getAsString name="title"/></title>

	<style type="text/css">
		@import "dojotoolkit/dojo/resources/dojo.css";
		@import "dojotoolkit/dijit/themes/claro/claro.css";
		@import "css/estilos_formularios.css";
		@import "css/estilos_plantilla.css";
		.claro .dijitDialogTitleBar {
			background-color: #4F6710;
			color: #FFFFFF;
		    font-weight: bold;
		    text-decoration: none;
	    }
</style>

	<style>
	.claro .dijitDialogTitleBar {
		background-color: #F47513;
		color: #FFFFFF;
	    font-weight: bold;
	    text-decoration: none;
	    }
	    
	
.ventana_mensaje {
 
    background-color: #E5EECE;
    border: 2px solid #FFFFFF;
    margin: 0 17em;
    padding: 2em;
    position: fixed;
    text-align: center;
    top: 30%;
    width: 36em;
    z-index: 1001;
    
}

	.backMensaje {
    background-color: #666;
    height: 100%;
    left: 0;
    opacity: 0.8;
    position: fixed;
    top: 0;
    width: 100%;
    z-index: 1000;
}

</style>
	<link href='https://fonts.googleapis.com/css?family=Droid+Sans' rel='stylesheet' type='text/css' />

	<script src="dojotoolkit/dojo/dojo.js" djConfig="parseOnLoad: true, locale:'es'"></script>

	<script type="text/javascript">
		dojo.require("dijit.dijit");
		dojo.require("dijit.Dialog");
	</script>

</head>
<body class="claro">

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

<div id="wrapper" class="wrapper">

     <jsp:include page="candidatoHeader1.jsp" />
	 <jsp:include page="candidatoHeader2.jsp" />
	
        <div id="contenido" class="frames"> 
	<br/>
<br/><c:if test="${not empty TITULO_CONSULTA}"><title>${TITULO_CONSULTA}</title></c:if>

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
</div>
         <jsp:include page="footer.jsp" />
           <jsp:include page="footerSimple.jsp" />
</div>
<%-- <jsp:include page="barraFooter.jsp" /> --%>
<script type="text/javascript"> 
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
