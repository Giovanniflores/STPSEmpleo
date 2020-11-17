<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<% String context = (String)application.getAttribute("DOMAIN_PORTAL");  
   String contextApp = request.getContextPath() +"/";

   String contextSWB = (String)application.getAttribute("DOMAIN_PORTAL");
   
   if (null != request.getSession().getAttribute("FROM_CIL")){
	   context = (String)application.getAttribute("DOMAIN_PORTAL_CIL");
	   contextSWB = (String)application.getAttribute("DOMAIN_PORTAL_CIL");
   }
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd"> 
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="es" lang="es"> 
<head> 
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<meta http-equiv="X-UA-Compatible" content="IE=8" />
<meta http-equiv="Reply-to" content="empleo@stps.gob.mx" />
<meta name="Title" content="Portal del Empleo" />
<meta name="author" content="Sergio Tellez"/>
<meta name="description" content="Búsqueda específica"/>
<meta name="keywords" content="Empleo, Ofertas empleo, Estad&iacute;sticas del mercado laboral, Ofrezco empleo, Capacitaci&oacute;n, STPS, Secretar&iacute;a del Trabajo y Previsi&oacute;n Social, Ofertas de empleo en M&eacute;xico, Publicaci&oacute;n de ofertas de trabajo, Bolsa de trabajo, Curriculum Vitae, cv, "/>
 
<meta name="Subject" content="Empleo" />
<meta name="language" content="spanish" />
<meta name="revisit" content="1 days" />
<meta name="distribution" content="Global" />
<meta name="robots" content="all | index | nofollow" />
<meta name="geo.region" content="MX-DIF" />
<meta name="geo.placename" content="Mexico City, Mexico" /> 
<title>Portal del Empleo :: Búsqueda específica</title>

<link rel="P3Pv1" href="https://www.empleo.gob.mx/w3c/p3p.xml" />

<link href="<%=contextSWB%>/work/models/empleo/css_aplicacion/estilos_busquedaEspecifica2014.css;" rel="stylesheet" type="text/css" />

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

<link rel="shortcut icon" media="all" type="image/x-icon" href="<%=context %>/work/models/empleo/css/favicon.ico" /> 
<link rel="alternate" type="application/rss+xml" title="Portal del Empleo" href="/RSS"/> 

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

<script src="dojotoolkit/dojo/dojo.js" djConfig="parseOnLoad: true, locale:'es'"></script>

<script type="text/javascript">
	dojo.require("dijit.dijit");
	dojo.require("dijit.Dialog");
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
<body class="claro">
	<div class="wrapper">
		<jsp:include page="empresaHeader1.jsp" />
	    <jsp:include page="empresaHeader2.jsp" />
		<div class="miEspacio">
			<div id="ruta_navegacion">
	        	Ruta de navegación: <a href="https://www.empleo.gob.mx"  >Inicio</a> | Herramientas del Sitio | <c:if test="${not empty TITULO_CONSULTA}">${TITULO_CONSULTA}</c:if>&nbsp;&nbsp;
	        </div>
        	<div id="contenido_principal" class="sinCol">
				<c:if test="${not empty TITULO_CONSULTA}"><title>${TITULO_CONSULTA}</title></c:if>
		    	<c:if test="${not empty USUARIO_APP && (USUARIO_APP.administrador || USUARIO_APP.publicador)}">
		    		<div id="navegacion">
		        		<tiles:insert name="menu"/>
		        	</div>
		    	</c:if>
		        <div id="contenido">
					<tiles:insert name="body"/>
		        </div>
	            <jsp:include page="footer.jsp" />
	            <jsp:include page="footerSimple.jsp" />
			</div>
		</div>
	</div>
</body>
</html>