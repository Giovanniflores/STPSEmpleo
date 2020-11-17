<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles"%>
<%@ page import="java.util.Calendar" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd"> 
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="es" lang="es"> 
<head> 
<link rel="P3Pv1" href="https://www.empleo.gob.mx/w3c/p3p.xml" /> 
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" /> 
<meta name="viewport" content="width=device-width, initial-scale=1">

	<title><tiles:getAsString name="title" /> | Portal del empleo</title>
	<meta property="og:title" content="<tiles:getAsString name="title" /> | Portal del empleo">	
	<meta name="twitter:title" content="<tiles:getAsString name="title" /> | Portal del empleo">
	<meta property="og:description" content="<tiles:getAsString name="description" />"/>
	<meta name="twitter:description" content="<tiles:getAsString name="description" />"/>
	<meta name="description" content="<tiles:getAsString name="title" />, Secretaria del trabajo y previsi&oacute;n Social, Servicio Nacional de Empleo, Portal del Empleo  "/>
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
<link rel="shortcut icon" media="all" type="image/x-icon" href="nos aaf/images/favicon.ico" /> 

<style type="text/css">
	@import "dojotoolkit/dojo/resources/dojo.css";
	@import "dojotoolkit/dijit/themes/claro/claro.css";
	@import "css/search_message.css";
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
<script type="text/javascript">
	dojo.require("dijit.dijit");
	dojo.require("dijit.Dialog");
</script>



<link href='https://fonts.googleapis.com/css?family=Droid+Sans' rel='stylesheet' type='text/css'/>
<!-- bootstrap -->
<link href="<%=request.getContextPath()%>/css/bootstrap/bootstrap.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/css/bootstrap/table-responsive.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/css/bootstrap/calendario.css" rel="stylesheet" type="text/css">

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
<script>
  window.fbAsyncInit = function() {
    FB.init({
      appId      : '308925772806125',
      xfbml      : true,
      version    : 'v2.7'
    });
  };

  (function(d, s, id){
     var js, fjs = d.getElementsByTagName(s)[0];
     if (d.getElementById(id)) {return;}
     js = d.createElement(s); js.id = id;
     js.src = "//connect.facebook.net/en_US/sdk.js";
     fjs.parentNode.insertBefore(js, fjs);
   }(document, 'script', 'facebook-jssdk'));
</script>
	<div class="container">
		<jsp:include page="/WEB-INF/template/empresaHeader1RespV2.jsp" />
	    <jsp:include page="templateNoFrames/headerResponsiveV2.jsp" /> 
<%-- 	    <jsp:include page="/WEB-INF/template/buscador.jsp"/>
	    <jsp:include page="/WEB-INF/template/ayuda.jsp"/> --%>
		<tiles:insert name="body"/>
	    <%-- <jsp:include page="/WEB-INF/template/footer.jsp"/> --%> 
	</div>
</body>
</html>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="<%=request.getContextPath()%>/js/bootstrap.js"></script>
<script src="<%=request.getContextPath()%>/js/template.js"></script>
<script src="<%=request.getContextPath()%>/js/loginResponsive.js"></script>


<%
	Calendar start = (Calendar)request.getAttribute("TIME-START");
	if (start!=null){
		Calendar end = Calendar.getInstance();
		long dif = end.getTimeInMillis() - start.getTimeInMillis();
		out.write("<!-- "+ dif +" ms -->");
	}	
%>