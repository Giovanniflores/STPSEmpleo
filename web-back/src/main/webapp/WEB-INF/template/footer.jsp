<?xml version="1.0" encoding="ISO-8859-1" ?>

<%
	String context = (String)application.getAttribute("DOMAIN_PORTAL");
	String contextApp = request.getContextPath();
	String contextSWB = (String)application.getAttribute("DOMAIN_PORTAL");
%>
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
  

  
  
