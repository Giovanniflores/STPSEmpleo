<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd"> 
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="es" lang="es"> 
<head> 

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<meta http-equiv="X-UA-Compatible" content="IE=8" />
<meta http-equiv="Reply-to" content="empleo@stps.gob.mx" />

<meta name="language" content="spanish" />
<meta name="revisit" content="1 days" />
<meta name="distribution" content="Global" />
<meta name="geo.region" content="MX-DIF" />
<meta name="geo.placename" content="Mexico City, Mexico" /> 

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

<link rel="shortcut icon" media="all" type="image/x-icon" href="https://www.empleo.gob.mx/work/models/empleo/css/favicon.ico" /> 
<link rel="alternate" type="application/rss+xml" title="Portal del Empleo" href="/RSS" /> 

<script src="dojotoolkit/dojo/dojo.js" djConfig="parseOnLoad: true, locale:'es'"></script>

<script type="text/javascript">
	dojo.require("dijit.dijit");
	dojo.require("dijit.Dialog");
</script>

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
<div class="wrapper">

    <jsp:include page="candidatoHeader1.jsp" />
    <c:if test="${USUARIO_APP.candidato}">
    	<jsp:include page="candidatoHeader2.jsp" />
	</c:if>
	<c:if test="${USUARIO_APP.empresa}">
		<jsp:include page="empresaHeader2.jsp" />
	</c:if>
<div class="detalle_oferta">
	<tiles:insert name="body"/>
    <jsp:include page="footer.jsp" />
    <jsp:include page="footerSimple.jsp" />
</div>

</div>

</body>
</html>

<c:out escapeXml="false" value="<!-- dbDurationTime: ${OfferQuestionForm.dbDurationTimeMs} ms -->"/>