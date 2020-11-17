<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%
    String contextApp = request.getContextPath();
    String contextSWB = (String)application.getAttribute("DOMAIN_PORTAL");
    if (null != request.getSession().getAttribute("FROM_CIL")){
        //contextApp = (String)application.getAttribute("DOMAIN_PORTAL_CIL");
        contextSWB = (String)application.getAttribute("DOMAIN_PORTAL_CIL");
    }
%>

<div class="clarfix"></div>
<!-- JMOR
<div class="sec_menu">
	<ul>
		<li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/herramientasDelSitio/mapaDeSitio.jsp")%>">Mapa de sitio</a></li>
		<li><a href="<%=response.encodeURL(request.getContextPath()+"/miespacionav.do?method=agendaCita")%>">Solicita una cita</a></li>
		<li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/herramientasDelSitio/necesitasAyuda.jsp")%>" >¿Necesitas ayuda?</a></li>
		<li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/herramientasDelSitio/contacto.jsp")%>">Contacto</a></li>
		<li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/herramientasDelSitio/avisoDeProteccionDeDatosPersonales.jsp")%>">Aviso de Protección de Datos Personales</a></li>
		<li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/herramientasDelSitio/politicasYCondicionesDeUso.jsp")%>">Políticas y condiciones de uso</a></li>
	</ul>
    <div class="clearfix"></div>
</div>
<div class="address_1">
	<div class="address_txt">
		<p>Llama a JMOR 01800 841 2020<br />
			<span>Boulevard Adolfo López Mateos <abbr title="Número">No.</abbr> 1968, <abbr title="Colonia">Col.</abbr>  Los Alpes, Álvaro Obregón 01010, Ciudad de México.</span>
		</p>
	</div>
	<div class="social_1">
		<span class="social_t">Síguenos</span>
		<ul>
			<li><a class="FB_1" lang="en" href="https://www.facebook.com/empleogobmx">Facebook</a></li>
			<li><a class="Tw_1" lang="en" href="https://twitter.com/empleogob_mx">Twitter</a></li>
			<li><a class="Lk_1" lang="en" href="https://www.linkedin.com/company-beta/24773620/" title="Linkedin" target="_blank">Linkedin</a></li>
			<!-- <li><a class="rss_1" href="<%=response.encodeURL(request.getContextPath()+"/jsp/ofertasRSS/ofertasRSSConsultar.jsp")%>"><acronym title="Really Simple Syndication - ">RSS</acronym></a></li> 
		</ul>
	</div>
    <div class="clearfix"></div>
</div>
<div class="ft_bnnr">
	<ul>
		<li><a class="logo_stps" href="http://www.stps.gob.mx" onclick="window.location.href='<%=response.encodeUrl(request.getContextPath()+"/inicio.do")%>';return false;">Secretaría del Trabajo y Previsión Social</a></li>
		<li><a class="logo_sne" href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/servicioNacionalDeEmpleo/servicioNacionalDeEmpleo.jsp")%>" onclick="window.location.href='<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/servicioNacionalDeEmpleo/servicioNacionalDeEmpleo.jsp")%>';return false;">Servicio Nacional de Empleo</a></li>
		<li><a class="logo_presidencia" href="http://www.presidencia.gob.mx" target="_blank">Gobierno de la República</a></li>
	</ul>
    <div class="clearfix"><img src="//seal.qualys.com/sealserv/seal.gif?i=5bd1fcbc-9922-432d-8f49-cdf7ac93888c" onclick="window.open('https://seal.qualys.com/sealserv/info/?i=5bd1fcbc-9922-432d-8f49-cdf7ac93888c','qualysSealInfo', 'height=600,width=851,resizable=1')" /></div>
</div>
-->
<!-- GOBmx -->
<div class="row">

<footer class="main-footer">
  <div class="list-info">
    <div class="container">
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
          <h2>&iquest;Qu&eacute; es gob.mx?</h2>
          <p>Es el portal &uacute;nico de tr&aacute;mites, informaci&oacute;n y participaci&oacute;n ciudadana. <a href="https://www.gob.mx/que-es-gobmx" target="_blank">Leer m&aacute;s</a></p>
          <ul>
            <li><a href="https://www.gob.mx/en/index" title="Versión en inglés del sitio web">English</a></li>
            <li><a href="https://www.gob.mx/temas" target="_blank">Temas</a></li>
            <li><a href="https://www.gob.mx/accesibilidad" target="_blank">Declaraci&oacute;n de Accesibilidad</a></li>
            <li><a href="https://www.gob.mx/privacidadintegral" target="_blank">Aviso de privacidad integral</a></li>
            <li><a href="https://www.gob.mx/privacidadsimplificado" target="_blank">Aviso de privacidad simplificado</a></li>
            <li><a href="https://www.gob.mx/terminos" target="_blank">T&eacute;rminos y Condiciones</a></li>
            <li><a href="https://www.gob.mx/terminos#medidas-seguridad-informacion" target="_blank">Pol&iacute;tica de seguridad</a></li>
            <li><a href="http://www.ordenjuridico.gob.mx/" target="_blank" class="sendEstFooter" title="Enlace abre en ventana nueva">Marco Jur&iacute;dico</a></li>
            <li><a href="https://www.gob.mx/sitemap" target="_blank">Mapa de sitio</a></li>
          </ul>
        </div>
        <div class="col-sm-4">
          <h2>Contacto</h2>
          <p>Mesa de ayuda: dudas e informaci&oacute;n<br>gobmx@funcionpublica.gob.mx</p>
          <p><a href="https://www.gob.mx/tramites/ficha/presentacion-de-quejas-y-denuncias-en-la-sfp/SFP54" target="_blank">Denuncia contra servidores p&uacute;blicos</a></p>
        </div>
      </div>
      <div class="row">
      <div class="col-sm-4">
          <form id="subscribe" action="http://www.gobernacion.gob.mx/work/models/header/subscribe" accept-charset="UTF-8" data-remote="true" method="post"><input name="utf8" type="hidden" value="?">
            <h2>Mantente informado. Suscr&iacute;bete.</h2>
            <div class="form-group-icon">
              <label class="sr-only" for="email">Suscribirse</label>
              <input id="email" type="text" name="email" class="form-control" placeholder="Ingresa tu correo electr&oacute;nico" maxlength="255">
              <button type="submit" class="blue-button-footer btn registerMail" title="Suscribirse">
                <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
              </button>
            </div>
            <div id="responseEmail" aria-live="polite"></div>
          </form>
      </div>
        <div class="col-sm-4 col-sm-offset-4">
          <h2>S&iacute;guenos en</h2>
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
  <div class="container">
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