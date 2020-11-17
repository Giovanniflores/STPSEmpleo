<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="url" value="${pageContext.request.requestURI}" />
<!DOCTYPE html>	
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="es" lang="es">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	
	<title> No se encontr&oacute; la p&aacute;gina | Portal del empleo</title>
	<meta property="og:title" content="No se encontr&oacute; la p&aacute;gina | Portal del empleo">
	<meta name="twitter:title" content="No se encontr&oacute; la p&aacute;gina | Portal del empleo">
	<meta property="og:description" content="La p&aacute;gina solicitada no est&aacute; disponible temporalmente, ha cambiado de nombre o ya no existe.">
	<meta name="twitter:description" content="La p&aacute;gina solicitada no est&aacute; disponible temporalmente, ha cambiado de nombre o ya no existe.">
	<meta name="description" content="Candidatos, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo">
	<meta property="og:url" content="https://qa.empleo.gob.mx/jsp/seguridad/error404.jsp">
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
	
	<link rel="shortcut icon" media="all" type="image/x-icon" href="/images/favicon.ico" /> 
	
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
	
	<link href="/dojotoolkit/dojo/resources/dojo.css" rel="stylesheet" type="text/css">
	<link href="/dojotoolkit/dijit/themes/claro/claro.css" rel="stylesheet" type="text/css">

	
	<!-- bootstrap -->
	<link href="/css/bootstrap/bootstrap.css" rel="stylesheet" type="text/css">
	<link href="/css/bootstrap/menu.css" rel="stylesheet" type="text/css">
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
	<div class="container">
		




<script type="text/javascript">
    function employ() {
        document.ocp.searchPlace.value = document.ocp.searchPlace.value;
        document.ocp.searchQ.value = document.ocp.searchTopic.value;
        document.ocp.submit();
    }
</script>
<input type="hidden" id="hdnSession" data-value="@Request.RequestContext.HttpContext.Session["UserName"]" />
 	<!-- div herramientas error 404-->
    <div class="row">
      	<div class="herramientas hidden-xs">
      	<!-- CUALQUIER ROL QUE NO SEA EMPRESA -->
	        
	            <div class="col-sm-12">
	               <ul class="nav nav-pills pull-right">
	               		<li role="presentation">
	               			<div class="resgistrateComo">Regístrate como</div>
	               		</li>
	                    <li role="presentation"><a href="/registro-candidato" class="btn-cerrarSesion">Candidato</a></li>
	                    <li role="presentation">
							<div class="resgistrateComo">o</div>
						</li>
	                    <li role="presentation"><a href="/registro-empresa" class="btn-cerrarSesion">Empresa</a></li>
	                	<li role="presentation"><a href="javascript:dialogLoginPopup();" class="btn-herramientas">Iniciar sesión</a></li>
	                </ul>
	            </div>
	        
	
	        <!-- ROL EMPRESA -->
	        
	       	
	       	 <!-- ROL CANDIDATO -->
	        
        </div>
        
        <div class="herramientas visible-xs">
			<div class="col-xs-12">
				<div class="dropdown">
					<!-- ROL EMPRESA -->
					
					<!-- ROL CANDIDATO -->
					
					<!-- CUALQUIER ROL QUE NO SEA EMPRESA -->
					
						<button class="btn-sesion btn btn-sm btn-block dropdown-toggle"type="button" id="menu1" data-toggle="dropdown">
							<strong>Iniciar sesión</strong> <span
								class="caret"></span>
						</button>
					
					
					<ul class="dropdown-menu" role="menu" aria-labelledby="menu1">
						<!-- ROL EMPRESA -->
						
						<!-- ROL CANDIDATO -->
						
						<!-- CUALQUIER ROL QUE NO SEA EMPRESA -->
						


							<li role="presentation"><a href="javascript:dialogLoginPopup();">Iniciar sesión</a></li>
						
							<li class="divider"></li>
							<li><a href="/herramientas/mapa-sitio">Mapa de sitio</a></li>
							<li><a href="/espacio-candidato/solicita-una-cita">Solicita una cita</a></li>
							<li><a href="/herramientas/contacto">Contacto</a></li>
						<!-- ROL EMPRESA -->
						
						<!-- ROL CANDIDATO -->
						
						
					</ul>
				</div>
			</div>
        </div>
    </div>
    <!-- /div herramientas -->

		<?xml version="1.0" encoding="ISO-8859-1" ?>


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
<!-- div header -->
	<div class="row">
      <div class="col-sm-7">
        <div class="header">
         <a href="https://www.empleo.gob.mx"> 
          <img src="/css/images/m_empleoGob.png"
			alt="Portal del Empleo : llama al 01 800 841 20 20"
			class="img-responsive">
		 </a>
        </div>
      </div>

      <div class="col-sm-5 hidden-xs"> 
          <div class="col-xs-6 logoSTPS">
        	<a href="http://www.stps.gob.mx">
	          <img src="/css/images/stps_logo.png"
	            alt="Secretaría del Trabajo y Previsión Social"
	            class="img-responsive">
        	</a>
      	  </div>
	      <div class="col-xs-6 logoSNE"> 
	        <a href="/SNE/servicio-nacional-de-empleo" 
	        	onclick="window.location.href='/SNE/servicio-nacional-de-empleo';return false;">
		          <img src="/css/images/sne_logo.png"
		            alt="Servicio Nacional de Empleo"
		            class="img-responsive">
	         </a>
	      </div>
	      
      	  <div class="clearfix"></div>   
        
          <div class="text-center nav_rapido"> 
            <div class="col-xs-4">
              <a href="/herramientas/mapa-sitio">Mapa de sitio</a>
            </div>
            <div class="col-xs-4">
              <a href="/herramientas/contacto">Contacto</a>
            </div> 
            <div class="col-xs-4 solicitaCita">
            	<img src="<%=request.getContextPath()%>/css/images/bg_agendaCita2.png" alt="">
	            <a href="<c:url value="/miespacionav.do?method=agendaCita"/>">&nbsp;Solicita una cita</a>
            </div> 
      	  </div>
       </div>
	</div>
<!-- /div header -->

<!-- div nav-black -->
<div class="row">
	<div class="col-sm-12">
		<div class="btn-group btn-group-justified nav-black">
			<a href="/candidatos" class="btn btn-candidato">Candidatos</a> 
			<a href="/empresas" class="btn btn-empresa">Empresas</a>
			<a href="/SNE/servicio-nacional-de-empleo" class="btn btn-SNE">Servicio Nacional de Empleo</a>
		</div>
	</div>
</div>
<!-- /div nav-black -->

		<?xml version="1.0" encoding="ISO-8859-1" ?>

    <div class="row">
    <!-- div buscador -->
      <div class="col-sm-12">
        <div class="panel panel-buscador">
<!--           <div class="panel-heading"> -->
<!--             <h3 class="panel-title"> -->
<!--               Tenemos más de <strong>1,440,000</strong> ofertas de empleo esperando por ti -->
<!--             </h3> -->
<!--           </div> -->
          <div class="panel-body">
            <div class="row">
              <form name="ocp" id="ocp" action="/ocupate.do" method="get">
                <input type="hidden" name="method" value="init">
                <input type="hidden" name="searchQ" value="">
                <div class="col-md-5">
                    <div class="form-group">
                      <label for="searchTopic">¿Qué empleo buscas? </label>
                      <input id="searchTopic" name="searchTopic" value="" type="text" class="form-control">
                    </div>
                     <span class="help-block">Puedes indicar un puesto, carrera u oficio</span> 
                </div>
                <div class="col-md-5">
                  <div class="form-group">
                    <label for="searchPlace" class="t_buscador">¿Dónde?</label>
                    <select id="searchPlace" name="searchPlace" class="form-control">
                            <option value="" selected="selected"></option>
                            <option value="1">Aguascalientes</option>
                            <option value="2">Baja California</option>
                            <option value="3">Baja California Sur</option>
                            <option value="4">Campeche</option>
                            <option value="5">Coahuila</option>
                            <option value="6">Colima</option>
                            <option value="7">Chiapas</option>
                            <option value="8">Chihuahua</option>
                            <option value="9">Ciudad de México</option>
                            <option value="10">Durango</option>
                            <option value="11">Guanajuato</option>
                            <option value="12">Guerrero</option>
                            <option value="13">Hidalgo</option>
                            <option value="14">Jalisco</option>
                            <option value="15">México</option>
                            <option value="16">Michoacán</option>
                            <option value="17">Morelos</option>
                            <option value="18">Nayarit</option>
                            <option value="19">Nuevo León</option>
                            <option value="20">Oaxaca</option>
                            <option value="21">Puebla</option>
                            <option value="22">Querétaro</option>
                            <option value="23">Quintana Roo</option>
                            <option value="24">San Luis Potosí</option>
                            <option value="25">Sinaloa</option>
                            <option value="26">Sonora</option>
                            <option value="27">Tabasco</option>
                            <option value="28">Tamaulipas</option>
                            <option value="29">Tlaxcala</option>
                            <option value="30">Veracruz</option>
                            <option value="31">Yucatán</option>
                            <option value="32">Zacatecas</option>
                    </select>
                  </div>
                   <span class="help-block text-right"><a href="/uso-del-buscador">¿Cómo utilizar el buscador?</a></span> 
                </div>
                <div class="col-md-2">
                  <span class="hidden-xs blockBtn"></span>
                   <input id="bt_buscador" class="btn btn-buscador form-control" type="button" name="bt_buscador" onclick="employ()" onkeypress="employ()" value="Buscar"/>
                </div>
              </form>
            </div>
          </div>
          <div class="panel-footer text-right">
            <span>También puedes realizar una <a href="/herramientas/busqueda-especifica">búsqueda específica</a></span>
          </div>
        </div>
    </div>
      <!-- /div buscador -->
    </div>
			<!-- div ayuda -->
    <div class="row">
      <!-- div class="col-sm-4">
        <div class="panel panel-contactoSWB">
          <div class="ayuda">
            <a href="/herramientas/necesitas-ayuda">
              &iquest;Necesitas ayuda? Inicia una asesor&iacute;a</a>
          </div>       
        </div>
      </div-->

      <div class="col-sm-4">
        <div class="panel panel-contactoSWB">
          <div class="atencion">
           <a href="/herramientas/contacto">Atenci&oacute;n telef&oacute;nica 01 800 841 2020</a>
        </div>
      </div>
    </div>

      <div class="col-sm-4">
        <div class="panel panel-contactoSWB">
          <div class="quejas">
            <a onclick="window.open(this.href, this.target, &#39;toolbar=no,directories=no,scrollbars=no,location=no,statusbar=no,menubar=no,resizable=yes,width=830,height=700&#39;); return false;" target="popUp" href="/sugerencias">Quejas y sugerencias</a>
          </div>       
        </div>
      </div>
    </div>
    <!-- div ayuda -->
		
	
      
    
 	
	
	
		<!-- div ruta_navegacion -->
	    <div class="row">
	      <div class="col-sm-12">
	        <ol class="breadcrumb">
	          <li>Ruta de navegación:</li>
	          <li><a href="https://www.empleo.gob.mx">Inicio</a></li>
          	  <li class="active">No se encontr&oacute; la p&aacute;gina.</li>
	        </ol>
	      </div>
	    </div>
	    <!-- /div ruta_navegacion -->
	    
		<div class="row">
			<div class="col-sm-8">
				<div id="error" class="contenedor">
					<div class="panel panel-grey">
						<div class="panel-body">
							<div class="col-md-6">
								<img class="img-responsive" src="/css/images/alerta-icon.png" alt="">
							</div>
							<div class="col-md-6">
								<h2>Error 404<br>No se encontró la página</h2>
								<p><span class="medium">La página solicitada no está disponible temporalmente, ha cambiado de nombre o ya no existe.</span></p>
								<p>Comprueba que la dirección en la barra de direcciones esté escrita correctamente.</p>
							</div>
						</div>
					</div>
					<ul class="list-group-contenidos">
						<li>Verifica que hayas escrito correctamente la dirección.</li>
						<li>Haz clic en la opción <a href="javascript:history.go(-1)">Atrás</a> para intentar otro vínculo.</li>
						<li>Utiliza el <a href="/herramientas/mapa-sitio">mapa de sitio</a> del portal para ubicar la ruta de la página que deseas consultar.</li>
					</ul>
					<p>&nbsp;</p>
					<div class="clearfix"></div>
				</div>
			</div>
		</div>
	    
	    
	    
	    
	

		<?xml version="1.0" encoding="ISO-8859-1" ?>




<!-- div subir -->
  <div class="row">
    <div class="col-md-12">
      <div id="content_btoSB">
      	<span class="subir" href="#">subir</span>
      </div>
    </div>
  </div>
  <!-- /div subir -->
<!-- div footerOfertas -->
  <div class="row footerOfertas">
    <div class="col-sm-4">
      <h3 class="hidden-xs">Encuentra ofertas de empleo</h3>
      <h3 class="encuentraOfertas visible-xs">Encuentra ofertas de empleo <span class="caret"></span></h3>
      <ul class="encuentraOfertasList activeFooter">
        <li><a href="/herramientas/busqueda-especifica">Búsqueda específica de ofertas de empleo</a></li>
        
        <li><a href="/ofertas-recientes">Ofertas de empleo recientes</a></li>
        <li><a href="/ofertas-destacadas">Ofertas de empleo destacadas</a></li>
        <li><a href="/SNE/periodico-ofertas-empleo-sne">Periódico de ofertas de empleo del SNE</a></li>
        <li><a href="/herramientas/ferias-de-empleo">Eventos próximos de Ferias de Empleo</a></li>
        <li><a href="/candidatos/bolsas-de-empleo-asociadas">Bolsas de empleo asociadas</a></li>
      </ul>
    </div>
     <div class="col-sm-4">
       <h3 class="hidden-xs">¿Busco trabajo?<br>Aumenta tus Posibilidades</h3>
       <h3 class="aumentaPosibilidades visible-xs">¿Busco trabajo?<br>Aumenta tus Posibilidades<span class="caret"></span></h3>
    <ul class="aumentaPosibilidadesList activeFooter">
        <li><a href="/candidatos/registrate">Regístrate y haz que las empresas te vean</a></li>
        <li><a href="/candidatos/habilidades-para-busqueda-de-empleo">Elabora tú Curriculum Vitae</a></li>
        <li><a href="/sne/programas-servicios-empleo">Programas y servicios de empleo</a></li>
        <li><a href="/candidatos/conoce-descubre-habilidades-y-capacidades">Conoce y descubre tus habilidades y capacidades</a></li>
        <li><a href="/sne/talleres-presenciales">Solicitud de empleo ¡Aprende como elaborarla!</a></li>
        <li><a href="/candidatos/becas-manpower-tdc">Becas Manpower TDC</a></li>
        <li><a href="/candidatos/certificacion-competencias-conocer">Certifica tus habilidades laborales: CONOCER</a></li>
    </ul>
    </div>
     <div class="col-sm-4">
      <h3 class="hidden-xs">Servicios para tu empresa</h3>
      <h3 class="serviciosEmpresa visible-xs">Servicios para tu empresa <span class="caret"></span></h3>
    <ul class="serviciosEmpresaList activeFooter">
       <li><a href="/registro-empresas">Publica ofertas de empleo gratis</a></li>
       <li><a href="/sne/ferias-de-empleo">Participa en las Ferias de Empleo</a></li>
       <li><a href="/sne/revista-informativa-sne">Revista Informativa del SNE</a></li>
       <li><a href="/mejora-desempeno-empresa">Mejora el desempeño de tu empresa</a></li>
       <li><a href="/sne/estadisticas-laborales">Estadísticas laborales</a></li>
       <li><a href="/SNE/acerca-del-sne">Acerca del SNE</a></li>
    </ul>
    </div>
  </div>
  <!-- /div footerOfertas --> 
  
  <!-- div footerLinks -->
  <div class="row footerLinks">
    <div class="col-md-12 row">
      <ul>
       <li><a href="/herramientas/mapa-sitio">Mapa de sitio</a></li>
		<li><a href="/espacio-candidato/solicita-una-cita">Solicita una cita</a></li>
		<li><a href="/herramientas/necesitas-ayuda">¿Necesitas ayuda?</a></li>
		<li><a href="/herramientas/contacto">Contacto</a></li>
		
		<li><a href="/herramientas/aviso-proteccion-datos-personales">Aviso de Protección de Datos Personales</a></li>
		<li><a href="/herramientas/politicas-condiciones-uso">Políticas y Condiciones de Uso</a></li>
		<li><a href="/por-que-usar-el-portal-del-empleo">¿Por qué usar el Portal del Empleo?</a></li>
      </ul>
    </div>
  </div>
  <!-- /div footerLinks -->

  <div class="row">
    <div class="footerSM">
      <div class="col-md-9 text-center">
        <p class="t-footer"><strong>&nbsp;</strong></p> 
        <p>Periférico Sur <abbr title="Número">No.</abbr> 4271, <abbr title="Colonia">Col.</abbr> Fuentes del Pedregal, Tlalpan 14149, Ciudad de México.</p>
      </div>
      <div id="siguenos" class="col-md-3">
        <h4><strong>Síguenos</strong></h4>
          <div class="whatsapp"><a href="#"><span class="whatsappText">55 73 35 68 24	</br>Lunes a Viernes de 8:00 a 18:00 hrs</span></a>	</div>
          <div class="facebook"><a class="FB_1" lang="en" href="https://www.facebook.com/empleogobmx" title="Facebook" target="_blank"></a></div>
          <div class="twitter"><a class="Tw_1" lang="en" href="https://twitter.com/empleogob_mx" title="Twitter" target="_blank"></a></div>
          <!-- <div class="rss"><a class="rss_1" href="/ofertas-rss-consulta"><acronym title="Really Simple Syndication - "></acronym></a></div> -->
      </div>
    </div>
  </div>
  
  <div class="row">
      <div class="footerLogos text-center">
  <div class="col-md-12 text-center">
  	<table>
  		<tr>
  			<td>
  				<a class="swb-banner" href="http://www.stps.gob.mx/" onclick="window.location.href='http://www.stps.gob.mx/';return false;" title="STPS">
		        <img class="img-responsive" src="/css/images/banner_publicitario2.fw.png" alt="Secretaría del Trabajo y Previsión Social" width="166">
		        </a>
  			</td>
  			<td>
  				<a class="swb-banner" href="/SNE/servicio-nacional-de-empleo" title="SNE">
		        <img class="img-responsive" src="/css/images/banner_publicitario3.png" alt="Servicio Nacional de Empleo" width="92">
		        </a>
  			</td>
  			<td>
  				<a class="swb-banner" href="http://www.presidencia.gob.mx/" onclick="window.location.href='http://www.presidencia.gob.mx';return false;" title="Presidencia">
		        <img class="img-responsive" src="/css/images/banner_publicitario4.png" alt="Presidencia de la república" width="166">
		        </a>
  			</td>
  		</tr>
  	</table>
       
      </div>
     </div>
  </div>
  

	</div>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	
  	<script src="/js/bootstrap.js"></script>
  	<script src="/js/template.js"></script>
  	<script src="/js/menu.js"></script>
  	<script src="/js/loginResponsive.js"></script>
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
  	
</body>
</html>