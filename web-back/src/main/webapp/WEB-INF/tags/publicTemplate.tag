<!DOCTYPE html>
<%@tag pageEncoding="UTF-8" description="Public Template" language="java"%>
<%@attribute name="titulo" fragment="true" required="true"%>
<%@attribute name="tituloSitio" fragment="true" required="true"%>
<%@attribute name="palabraDescripcion" fragment="true" required="true"%>
<%@attribute name="urlRedSocial" fragment="true" required="false"%>
<%@attribute name="descripcionRedSocial" fragment="true" required="false"%>
<%@attribute name="js" fragment="true"%>

	
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="es" lang="es">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	
	<title> <jsp:invoke fragment="titulo" /> | <jsp:invoke fragment="tituloSitio"/></title>
	<meta property="og:title" content="<jsp:invoke fragment="titulo"/> | <jsp:invoke fragment="tituloSitio"/>">
	<meta name="twitter:title" content="<jsp:invoke fragment="titulo" /> | <jsp:invoke fragment="tituloSitio"/>">
	<meta property="og:description" content="<jsp:invoke fragment="descripcionRedSocial" />">
	<meta name="twitter:description" content="<jsp:invoke fragment="descripcionRedSocial" />">
	<meta name="description" content="<jsp:invoke fragment="palabraDescripcion" />, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo">
	<meta property="og:url" content="https://qa.empleo.gob.mx<jsp:invoke fragment="urlRedSocial"/>">
	<meta property="og:site_name" content="Portal del Empleo">
	<meta property="og:image" content="https://qa.empleo.gob.mx/css/images/contenido-compartir.jpg">
	<meta property="og:image:type" content="image/jpeg">
	<meta property="og:image:width" content="1200">
	<meta property="og:image:height" content="627">
	<meta property="fb:app_id" content="308925772806125" />
	<meta name="twitter:card" content="summary_large_image">
	<meta name="twitter:site" content="@empleogob_mx">
	<meta name="twitter:creator" content="@InfotecMexico">
	<meta name="twitter:image:src" content="https://qa.empleo.gob.mx/css/images/compartir-contenido-tweetA.jpg">
	<meta name="author" content="infotec">
	
	<link rel="shortcut icon" media="all" type="image/x-icon" href="<%=request.getContextPath()%>/images/favicon.ico" /> 
	
	<script src="../../../dojotoolkit/dojo/dojo.js" djConfig="parseOnLoad: true, locale:'es'"></script>
	<script type="text/javascript">
		dojo.require("dijit.dijit");
		dojo.require("dijit.Dialog");
		function employ() {
	        document.ocp.searchPlace.value = document.ocp.searchPlace.value;
	        document.ocp.searchQ.value = document.ocp.searchTopic.value;
	        document.ocp.submit();
	    }
	</script>
	
	<!-- dojo -->
	
	<link href="<%=request.getContextPath()%>/dojotoolkit/dojo/resources/dojo.css" rel="stylesheet" type="text/css">
	<link href="<%=request.getContextPath()%>/dojotoolkit/dijit/themes/claro/claro.css" rel="stylesheet" type="text/css">

	
	<!-- bootstrap -->
	<link href="<%=request.getContextPath()%>/css/bootstrap/bootstrap.css" rel="stylesheet" type="text/css">
	<link href="<%=request.getContextPath()%>/css/bootstrap/menu.css" rel="stylesheet" type="text/css">
	
	<!-- GOBmx -->
	<link rel="shortcut icon" type="image/x-icon" href="http://www.gob.mx/assets/favicon.ico">
    <link href="<%=request.getContextPath()%>/css/cssgobmx/main.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/css/cssgobmx/application.css" rel="stylesheet">
    <!-- GOBmx -->
    
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
	<script>
	/**
	* Función de Google Analytics que realiza un seguimiento 
	* de un clic en un enlace o vínculo externo.
	* Esta función toma una cadena de URL válida como argumento 
	* y la utiliza como la etiqueta del evento. 
	* Configurar el método de transporte como "beacon" 
	* permite que el hit se envíe con "navigator.sendBeacon" 
	* en el navegador que lo admita.
	*/
	var trackOutboundLink = function(url) {
	   ga('send', 'event', 'Cursos en línea', 'Catálogo de cursos', url, {
	     'transport': 'beacon',
	     'hitCallback': function(){document.location = url;}
	   });
	}
	</script>
</head>
<body class="claro">

    <!-- GOBmx -->
<div class="row">
	<header>
	<nav class="navbar navbar-inverse navbar-fixed-top navbar">
		<div class="wrapper">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed" data-target="#navbarMainCollapse" data-toggle="collapse">
					<span class="sr-only">Interruptor de Navegación</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
				 <a class="gobmx" title="Ir a gob.mx" target="_blank" style="padding-left: 10px;" href="https://www.gob.mx/">
                <img src="<%=request.getContextPath()%>/css/cssgobmx/gobmxlogo.svg" alt="gob.mx"></a>
			</div>
			<div id="navbarMainCollapse" class="collapse navbar-collapse">
				<ul class="nav navbar-nav navbar-right">
					<li><a href="https://www.gob.mx/tramites" class="" target="_blank" title="Trámites">Trámites</a></li>
					<li><a href="https://www.gob.mx/gobierno" class="" target="_blank" title="Gobierno">Gobierno</a></li>
					<li><a href="https://www.gob.mx/participa" class="" target="_blank" title="Participación Ciudadana">Participa</a></li>
					<li><a href="http://datos.gob.mx/" target="_blank" title="Datos abiertos">Datos</a></li>
					<li><a href="https://www.gob.mx/busqueda?utf8=%E2%9C%93" target="_blank" title="Datos abiertos">
						<img alt="Búsqueda" width="20" height="20" class="optical-adjust-search" src="<%=request.getContextPath()%>/css/cssgobmx/search.svg">
					</a></li>
				</ul>
			</div>
		</div>
	</nav>
</header>
	</div>
	    <!-- GOBmx -->
	    
 <div class="clearfix"></div>


	<div class="row">
	
		<!-- div buscador -->
		<div class="col-md-6 col-md-offset-3">
			<div class="panel panel-buscador inicial">

				<div class="panel-footer text-right">
					<span>También puedes realizar una <a href="<c:url value="/busquedaEspecificaOfertas.do?method=buscar" /> ">búsqueda específica</a></span>
				</div>
			</div>
			
			<!-- div ayuda -->
			<div class="row">
	
				<div class="col-md-4 col-sm-6 col-xs-12">
					<div class="panel panel-contactoSWB">
						<div class="atencion">
							<a href="<c:url value="/jsp/empleo/herramientasDelSitio/contacto.jsp"/>">Atención: 01 800 841 2020</a>
						</div>
					</div>
				</div>
		
				<div class="col-md-4 col-sm-6 col-xs-12">
					<div class="panel panel-contactoSWB">
						<div class="quejas">
							<a onclick="window.open(this.href, this.target, &#39;toolbar=no,directories=no,scrollbars=yes,location=no,statusbar=no,menubar=no,resizable=yes,width=830,height=700&#39;); return false;" target="popUp" href="<c:url value="/suggestion.do?method=init"/>">Quejas y sugerencias</a>
						</div>
					</div>
				</div>
				
				<div class="col-md-4 col-sm-6 col-xs-12">
					<div class="panel panel-contactoSWB act">
						<div class="ayuda">
													
							<a href="https://wise-interactions.com/stps-chat/" target="_blank" id="chat">Ayuda en línea</a>
						</div>
					</div>
				</div>
				
				
			</div>
			<!-- div ayuda -->
		</div>
		<!-- /div buscador -->
		
		 <div class="clearfix"></div>

	<div class="container">
		<%-- <jsp:include page="/WEB-INF/template/empresaHeader1Resp.jsp" />
		 <jsp:include page="/WEB-INF/template/header.jsp"/> 
		<jsp:include page="/WEB-INF/template/buscador.jsp"/>
		<jsp:include page="/WEB-INF/template/ayuda.jsp"/> --%>
		<jsp:doBody/>
		<%-- <jsp:include page="/WEB-INF/template/footer.jsp"/> --%>
	</div>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	
  	<script src="<%=request.getContextPath()%>/js/bootstrap.js"></script>
  	<script src="<%=request.getContextPath()%>/js/template.js"></script>
  	<script src="<%=request.getContextPath()%>/js/menu.js"></script>
  	<script src="<%=request.getContextPath()%>/js/loginResponsive.js"></script>
  		<script type="text/javascript">
		$(document).ready(function(){
			// grab the initial top offset of the navigation 
		   	var stickyNavTop = $('.indice-perfil').offset().top;
		   	
		   	// our function that decides weather the navigation bar should have "fixed" css position or not.
		   	var stickyNav = function(){
			    var scrollTop = $(window).scrollTop(); // our current vertical position from the top
			         
			    // if we've scrolled more than the navigation, change its position to fixed to stick to top,
			    // otherwise change it back to relative
			    if (scrollTop > stickyNavTop) { 
			        $('.fuerza-perfil').addClass('sticky');
			    } else {
			        $('.fuerza-perfil').removeClass('sticky'); 
			    }
			};

			stickyNav();
			// and run it again every time you scroll
			$(window).scroll(function() {
				stickyNav();
			});
		});
	</script>
  	<jsp:invoke fragment="js" />
  	
  	<!-- GOBmx -->
  	
  				      	 <div class="row"> 

<footer class="main-footer">
  <div class="list-info">
    <div class="wrapper">
      <div class="row">
        <div class="col-sm-4">
          <h2>Enlaces</h2>
          <ul>
            <li><a href="http://reformas.gob.mx/" target="_blank" class="sendEstFooter" title="Enlace abre en ventana nueva">Reformas</a></li>
            <li><a href="https://www.gob.mx/publicaciones" target="_blank" title="Enlace abre en ventana nueva">Publicaciones Oficiales</a></li>
            <li><a href="http://portaltransparencia.gob.mx/" target="_blank" class="sendEstFooter" title="Enlace abre en ventana nueva">Portal de Obligaciones de Transparencia</a></li>
            <li><a href="https://www.infomex.org.mx/gobiernofederal/home.action" target="_blank" class="sendEstFooter" title="Enlace abre en ventana nueva">Sistema Infomex</a></li>
            <li><a href="http://inicio.ifai.org.mx/SitePages/ifai.aspx" target="_blank" class="sendEstFooter" title="Enlace abre en ventana nueva">INAI</a></li>
          </ul>
        </div>
        <div class="col-sm-4">
          <h2>¿Qué es gob.mx?</h2>
          <p>Es el portal único de trámites, información y participación ciudadana. <a href="https://www.gob.mx/que-es-gobmx" target="_blank">Leer más</a></p>
          <ul>
            <li><a href="https://www.gob.mx/en/index" title="Versión en inglés del sitio web">English</a></li>
            <li><a href="https://www.gob.mx/temas" target="_blank">Temas</a></li>
            <li><a href="https://www.gob.mx/accesibilidad" target="_blank">Declaración de Accesibilidad</a></li>
            <li><a href="https://www.gob.mx/privacidadintegral" target="_blank">Aviso de privacidad integral</a></li>
            <li><a href="https://www.gob.mx/privacidadsimplificado" target="_blank">Aviso de privacidad simplificado</a></li>
            <li><a href="https://www.gob.mx/terminos" target="_blank">Términos y Condiciones</a></li>
            <li><a href="https://www.gob.mx/terminos#medidas-seguridad-informacion" target="_blank">Política de seguridad</a></li>
            <li><a href="http://www.ordenjuridico.gob.mx/" target="_blank" class="sendEstFooter" title="Enlace abre en ventana nueva">Marco Jurídico</a></li>
            <li><a href="https://www.gob.mx/sitemap" target="_blank">Mapa de sitio</a></li>
          </ul>
        </div>
        <div class="col-sm-4">
          <h2>Contacto</h2>
          <p>Mesa de ayuda: dudas e información<br>gobmx@funcionpublica.gob.mx</p>
          <p><a href="https://www.gob.mx/tramites/ficha/presentacion-de-quejas-y-denuncias-en-la-sfp/SFP54" target="_blank">Denuncia contra servidores públicos</a></p>
        </div>
      </div>
      <div class="row">
      <div class="col-sm-4">
          <form id="subscribe" action="http://www.gobernacion.gob.mx/work/models/header/subscribe" accept-charset="UTF-8" data-remote="true" method="post"><input name="utf8" type="hidden" value="✓">
            <h2>Mantente informado. Suscríbete.</h2>
            <div class="form-group-icon">
              <label class="sr-only" for="email">Suscribirse</label>
              <input id="email" type="text" name="email" class="form-control" placeholder="Ingresa tu correo electrónico" maxlength="255">
              <button type="submit" class="blue-button-footer btn registerMail" title="Suscribirse">
                <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
              </button>
            </div>
            <div id="responseEmail" aria-live="polite"></div>
          </form>
      </div>
        <div class="col-sm-4 col-sm-offset-4">
          <h2>Síguenos en</h2>
          <ul class="list-inline">
            <li>
            <a href="https://www.facebook.com/gobmx" target="_blank" class="sendEstFooterRs share-info" red="Facebook" title="enlace a facebook abre en una nueva ventana">
            <img alt="Facebook" src="<%=request.getContextPath()%>/css/cssgobmx/facebook_footer.png">
            </a>
            </li>
            <li>
            <a href="https://twitter.com/gobmx" target="_blank" class="sendEstFooterRs share-info" red="Twitter" title="Enlace a twitter abre en una nueva ventana">
            <img alt="Twitter" src="<%=request.getContextPath()%>/css/cssgobmx/twitter_footer.png">
            </a>
            </li>
          </ul>
        </div>
      </div>
    </div>
  </div>
  <div class="wrapper">
    <div class="row">
      <div class="col-sm-4">
         <a class="gobmx" title="Ir a gob.mx" target="_blank" style="padding-left: 10px;" href="https://www.gob.mx/">
                <img src="<%=request.getContextPath()%>/css/cssgobmx/gobmxlogo.svg" alt="gob.mx"></a>
      </div>
      <div class="col-sm-4 col-sm-offset-4">
      </div>
    </div>
  </div>
</footer>
 </div> 
 
 <!-- GOBmx -->
</body>
</html>