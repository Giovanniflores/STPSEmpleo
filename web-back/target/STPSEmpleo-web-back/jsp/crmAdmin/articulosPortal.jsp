<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib prefix="logic" uri="http://struts.apache.org/tags-logic" %>
<%@ taglib prefix="bean" uri="http://struts.apache.org/tags-bean" %>
<%@ taglib prefix="s" uri="http://struts.apache.org/tags-logic" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


  <script type="text/javascript" >
    var djConfig = {
      parseOnLoad: true,
      isDebug: false
    };
  </script>
  <script type="text/javascript">
    <!--
    var recomDivId = 'recommendi1_55';
    function validate(frm) {
      var msg = [];
      if( isEmpty(frm.txtFromName.value) )
        msg.push('El nombre del remitente es requerido.');
      if( !isValidEmail(frm.txtFromEmail.value) )
        msg.push('El correo electrónico del remitente es requerido.');
      if( isEmpty(frm.txtToName.value) )
        msg.push('El nombre del destinatario es requerido.');
      if( !isValidEmail(frm.txtToEmail.value) )
        msg.push('El correo electrónico del destinatario es requerido.');
      if( isEmpty(frm.code.value) )
        msg.push('El código de seguridad es requerido');
      if( msg.length==0 ) {
        var xhrArgs = {
          form: frm,
          handleAs: "text",
          load: function(data) {
            alert(data);
            removeModal(recomDivId);
          }
        };
        var deferred = dojo.xhrPost(xhrArgs);
        return true;
      }else {
        alert(msg.join('\n'));
        return false;
      }
    }
    function createModal(divId, bgcolor, opacity) {
      var layer=document.createElement('div');
      layer.id=divId;
      layer.style.width='100%';
      layer.style.height='100%';
      layer.style.backgroundColor=bgcolor;
      layer.style.position='fixed';
      layer.style.top=0;
      layer.style.left=0;
      layer.style.zIndex=1000;
      layer.style.filter='alpha(opacity='+opacity+')';
      layer.style.opacity=opacity/100;
      document.body.appendChild(layer);
      return layer;
    }
    function removeModal(divId) {
      var layer=document.getElementById(divId);
      var superlayer=document.getElementById('s_'+divId);
      if(layer && superlayer) {
        document.body.removeChild(superlayer);
        document.body.removeChild(layer);
      }
    }
    function openRecommendationModal(divId, bgcolor, opacity) {
      if (document.getElementById(divId) != undefined) {
        return;
      }
      var newDiv = createModal(divId, bgcolor, opacity);
      var recomContainer=document.createElement('div');
      var s = new String('');
      s = s.concat('<div class="swb-recommend">  <h3>Recomendar<\/h3>  <form class="swb-rcmnd-frm" method="post" action="/es_mx/empleo/articulos/_rid/i1_55/_mto/3/_mod/send" id="frmContact">    <p class="swb-rcmnd-in">      <label for="txtFromName">Remitente:*<\/label>      <input type="text" id="txtFromName" name="txtFromName" value="" maxlength="100"/>    <\/p>    <p class="swb-rcmnd-in">      <label for="txtFromEmail">Correo del remitente:*<\/label>      <input type="text" id="txtFromEmail" name="txtFromEmail" value="" maxlength="80"/>    <\/p>    <p class="swb-rcmnd-in">      <label for="txtToName">Destinatario:*<\/label>      <input type="text" id="txtToName" name="txtToName" maxlength="100" />    <\/p>    <p class="swb-rcmnd-in">      <label for="txtToEmail">Correo del destinatario:*<\/label>      <input type="text" id="txtToEmail" name="txtToEmail" maxlength="80"/>    <\/p>    <p class="swb-rcmnd-in">      <label for="tarMsg">Mensaje adicional:<\/label>      <textarea rows="5" cols="40" id="tarMsg" name="tarMsg"><\/textarea>    <\/p><div class="swb-captcha-imagen">  <p><img src="/swbadmin/jsp/securecode.jsp" alt="" id="imgseccode" width="155" height="65" /></p>  <p><a href="javascript:changeSecureCodeImage(\'imgseccode\');">Cambiar imagen</a></p></div><div class="swb-captcha-txt">  <p><label for="cmnt_seccode">El texto de la imagen es:</label></p>  <p><input type="text" name="code" value=""/></p></div>    <p class="swb-rcmnd-cmd">      <input type="button" value="Cancelar" onclick="removeModal(recomDivId);"/>      <input type="reset" value="Limpiar"/>      <input type="button" value="Enviar" onclick="validate(this.form);"/>    <\/p>  <\/form>  <p class="swb-rcmnd-warn">* Dato requerido<\/p><\/div>');
      recomContainer.innerHTML = s;
      var cwidth=650;
      var cheight=350;
      recomContainer.id='s_'+divId;
      recomContainer.style.zIndex=1001;
      recomContainer.style.position='absolute';
      recomContainer.style.top='50%';
      recomContainer.style.left='50%';
      recomContainer.style.marginLeft=-cwidth/2+'px';
      recomContainer.style.marginTop=-cheight/2+'px';
      recomContainer.style.width=cwidth+'px';
      recomContainer.style.height=cheight+'px';
      document.body.appendChild(recomContainer);
    }
    -->
  </script>

  <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/dojo/1.9.3/dojo/dojo.js" ></script>
  <script type="text/javascript" >
    function employ() {
      document.ocp.searchPlace.value = document.ocp.searchPlace.value;
      document.ocp.searchQ.value = document.ocp.searchTopic.value;
      document.ocp.submit();
    }
  </script>
  <script type="text/javascript">
    <!--
    var recomDivId = 'recommendi1_55';
    function validate(frm) {
      var msg = [];
      if( isEmpty(frm.txtFromName.value) )
        msg.push('El nombre del remitente es requerido.');
      if( !isValidEmail(frm.txtFromEmail.value) )
        msg.push('El correo electrónico del remitente es requerido.');
      if( isEmpty(frm.txtToName.value) )
        msg.push('El nombre del destinatario es requerido.');
      if( !isValidEmail(frm.txtToEmail.value) )
        msg.push('El correo electrónico del destinatario es requerido.');
      if( isEmpty(frm.code.value) )
        msg.push('El código de seguridad es requerido');
      if( msg.length==0 ) {
        var xhrArgs = {
          form: frm,
          handleAs: "text",
          load: function(data) {
            alert(data);
            removeModal(recomDivId);
          }
        };
        var deferred = dojo.xhrPost(xhrArgs);
        return true;
      }else {
        alert(msg.join('\n'));
        return false;
      }
    }
    function createModal(divId, bgcolor, opacity) {
      var layer=document.createElement('div');
      layer.id=divId;
      layer.style.width='100%';
      layer.style.height='100%';
      layer.style.backgroundColor=bgcolor;
      layer.style.position='fixed';
      layer.style.top=0;
      layer.style.left=0;
      layer.style.zIndex=1000;
      layer.style.filter='alpha(opacity='+opacity+')';
      layer.style.opacity=opacity/100;
      document.body.appendChild(layer);
      return layer;
    }
    function removeModal(divId) {
      var layer=document.getElementById(divId);
      var superlayer=document.getElementById('s_'+divId);
      if(layer && superlayer) {
        document.body.removeChild(superlayer);
        document.body.removeChild(layer);
      }
    }
    function openRecommendationModal(divId, bgcolor, opacity) {
      if (document.getElementById(divId) != undefined) {
        return;
      }
      var newDiv = createModal(divId, bgcolor, opacity);
      var recomContainer=document.createElement('div');
      var s = new String('');
      s = s.concat('<div class="swb-recommend">  <h3>Recomendar<\/h3>  <form class="swb-rcmnd-frm" method="post" action="/es_mx/empleo/articulos/_rid/i1_55/_mto/3/_mod/send" id="frmContact">    <p class="swb-rcmnd-in">      <label for="txtFromName">Remitente:*<\/label>      <input type="text" id="txtFromName" name="txtFromName" value="" maxlength="100"/>    <\/p>    <p class="swb-rcmnd-in">      <label for="txtFromEmail">Correo del remitente:*<\/label>      <input type="text" id="txtFromEmail" name="txtFromEmail" value="" maxlength="80"/>    <\/p>    <p class="swb-rcmnd-in">      <label for="txtToName">Destinatario:*<\/label>      <input type="text" id="txtToName" name="txtToName" maxlength="100" />    <\/p>    <p class="swb-rcmnd-in">      <label for="txtToEmail">Correo del destinatario:*<\/label>      <input type="text" id="txtToEmail" name="txtToEmail" maxlength="80"/>    <\/p>    <p class="swb-rcmnd-in">      <label for="tarMsg">Mensaje adicional:<\/label>      <textarea rows="5" cols="40" id="tarMsg" name="tarMsg"><\/textarea>    <\/p><div class="swb-captcha-imagen">  <p><img src="/swbadmin/jsp/securecode.jsp" alt="" id="imgseccode" width="155" height="65" /></p>  <p><a href="javascript:changeSecureCodeImage(\'imgseccode\');">Cambiar imagen</a></p></div><div class="swb-captcha-txt">  <p><label for="cmnt_seccode">El texto de la imagen es:</label></p>  <p><input type="text" name="code" value=""/></p></div>    <p class="swb-rcmnd-cmd">      <input type="button" value="Cancelar" onclick="removeModal(recomDivId);"/>      <input type="reset" value="Limpiar"/>      <input type="button" value="Enviar" onclick="validate(this.form);"/>    <\/p>  <\/form>  <p class="swb-rcmnd-warn">* Dato requerido<\/p><\/div>');
      recomContainer.innerHTML = s;
      var cwidth=650;
      var cheight=350;
      recomContainer.id='s_'+divId;
      recomContainer.style.zIndex=1001;
      recomContainer.style.position='absolute';
      recomContainer.style.top='50%';
      recomContainer.style.left='50%';
      recomContainer.style.marginLeft=-cwidth/2+'px';
      recomContainer.style.marginTop=-cheight/2+'px';
      recomContainer.style.width=cwidth+'px';
      recomContainer.style.height=cheight+'px';
      document.body.appendChild(recomContainer);
    }
    -->
  </script>


  <!--<script type="text/javascript" src="https://qa.empleo.gob.mx/swbadmin/js/swb.js" ></script>-->

  <script src="https://qa.empleo.gob.mx/work/models/empleo/js/dw_sizerdx.js" type="text/javascript" ></script>
  <script src="https://qa.empleo.gob.mx/work/models/empleo/js/dw_cookies.js" type="text/javascript" ></script>
  <script src="https://qa.empleo.gob.mx/work/models/empleo/js/dw_event.js" type="text/javascript" ></script>
  <script type="text/javascript" >
    // setDefaults arguments: size unit, default size, minimum, maximum
    // optional array of elements or selectors to apply these defaults to
    dw_fontSizerDX.setDefaults( "px", 12, 9, 26,
            ['#intro_canal p', '#cuerpo_int .esbozo h3', '#cuerpo_int .esbozo',  'div.contenido', 'span.titnaranja', 'div.textmayusorange',  'p.titnaranja', 'a.ligastext2', '#text', '#contenido p', 'li.textmin', 'li.textmayus', 'span.textmayusorange', 'span.textmin', 'strong.textmin', 'p.textmin', 'p.textmayusblue',  'strong.textmayusorange', 'a.ligastext2' , 'td.textminB' , 'div.textmayusB', 'td.textmayusB', 'p.textmayusorange', 'a.textmayusorange', 'a.textmin'] );

    dw_Event.add( window, 'load', dw_fontSizerDX.init );

    function wbprint()
    {
      window.self.print();
      window.self.close();
    }


  </script>

  <script type="text/javascript" >
    setTimeout(function(){var a=document.createElement("script");
      var b=document.getElementsByTagName("script")[0];
      a.src=document.location.protocol+"//dnn506yrbagrg.cloudfront.net/pages/scripts/0012/7751.js?"+Math.floor(new Date().getTime()/3600000);
      a.async=true;a.type="text/javascript";b.parentNode.insertBefore(a,b)}, 1);
  </script>


  <script type="text/javascript" >
    dojo.require("dijit.dijit");
    dojo.require("dijit.Dialog");
  </script>


  <script type="text/javascript" >
    function keySubmit(e) {
      if (e.keyCode == 13) {
        document.loginForm.submit();
      }
    }
    function keycolSubmit(e) {
      if (e.keyCode == 13) {
        document.logincolForm.submit();
      }
    }
    function keybarSubmit(e) {
      if (e.keyCode == 13) {
        document.loginbarForm.submit();
      }
    }

    var userclean = true;
    var pswclean = true;
    var usercleancol = true;
    var pswcleancol = true;
    var cfmcleancol = true;
    var usercleanbar = true;
    var pswcleanbar = true;

    function clean(field, numfield) {

      if (numfield==1){
        if (userclean){
          field.value='';
          userclean = false;
        }
      }

      if (numfield==2){
        if (pswclean){
          field.value='';
          pswclean = false;
        }
      }
      if (numfield==3){
        if (usercleancol){
          field.value='';
          usercleancol = false;
        }
      }

      if (numfield==4){
        if (pswcleancol){
          field.value='';
          pswcleancol = false;
        }
      }
      if (numfield==5){
        if (cfmcleancol){
          field.value='';
          cfmcleancol = false;
        }
      }
      if (numfield==6){
        if (usercleanbar){
          field.value='';
          usercleanbar = false;
        }
      }

      if (numfield==7){
        if (pswcleanbar){
          field.value='';
          pswcleanbar = false;
        }
      }
    }

    function dialogLoginPopup(){

      var idTag = "loginPopupDialog";

      if (dijit.byId(idTag) == null){
        var html =
                '<div id="registroLat">'+
                '	<label for="usernamePopup">Usuario / Correo electr&oacute;nico</label>'+
                '	<input type="text" name="usernamePopup" id="usernamePopup" onfocus="clean(this, 1)" value="Escribe tu usuario" >'+
                '	<label for="passwordPopup">Contrase&ntildea</label>'+
                '	<input type="password" id="passwordPopup" name="passwordPopup" onfocus="clean(this, 2)" value="Escribe tu contraseña" >'+
                '	<a class="naranja" href="http://qa.empleo.gob.mx/recupera_contrasena.do" >¿Olvidaste tu usuario o contrase&ntildea? </a>'+
                '	<input type="button" onclick="loginPopup(usernamePopup.value, passwordPopup.value);" value="Iniciar sesi&oacute;n" id="ir" >'+
                '	<div class="registro">'+
                '		<p class="alta">¿No estás dado de alta?</p>'+
                '		<p>Regístrate como'+
                '		<a class="candidato" href="<%=response.encodeURL(request.getContextPath()+"/registro_candidato.do")%>">Candidato</a><span> o </span>'+
                '		<a class="empresa" href="<%=response.encodeURL(request.getContextPath()+"/registro_empresa.do")%>" >Empresa</a>'+
                '		</p>'+
                '	</div>'+
                '</div>';

        dialogLogin = new dijit.Dialog({title: 'Iniciar sesión', id: idTag, style: "width:350px;",
          content: html, showTitle: true, draggable : true, closable : true});
        //dojo.style(dialogLogin.closeButtonNode,"display","none");
      }

      dijit.byId(idTag).show();
    }

    function loginPopup(login, pwd) {
      var ref = window.location.href;
      if (!validaUserPwdPopup(login, pwd)) {
        return; // Datos erroneos
      }
      //var url = 'http://qa.empleo.gob.mx/login.do?method=loginPopup&username='+ login +'&password='+ pwd;
      var url = 'https://qa.empleo.gob.mx/work/jsp/loginapp.jsp?login='+ login +'&password='+ pwd;
      dojo.xhrPost({url: url,
        timeout:180000,
        load: function(data) {
          var resultVO = dojo.fromJson(data);
          if ('ext' == resultVO.type) {
            var rep = ref.replace("es_mx", "login") + "?wb_username=" + resultVO.value;
            window.location.assign(rep);
            //location.reload();
          }else if ('err' == resultVO.type) {
            alert(resultVO.message);
          }
        }
      });
    }

    function validaUserPwdPopup(login, pwd) {
      var valido = true;

      if (isEmpty(login) || isBlank(login)){
        alert('Debe proporcionar el nombre de usuario');
        valido = false;
      }

      if (isEmpty(pwd) || isBlank(pwd)){
        alert('Debe proporcionar la contraseña');
        valido = false;
      }

      return valido;
    }


    function isEmpty(str) {
      return (!str || 0 === str.length);
    }

    function isBlank(str) {
      if (isAllBlank(str)) {
        return false;
      } else {
        return (!str || /^\s*$/.test(str));
      }
    }

    function isAllBlank(str) {
      if (str.replace(/\s/g, "") == "") {
        return true;
      } else {
        return false;
      }
    }
  </script>








  <div id="cuerpo_int">
    <div id="ruta_navegacion">
      Ruta de navegaci&oacute;n: <a href="https://qa.empleo.gob.mx/es_mx/empleo/home"  >Inicio</a> | Herramientas del sitio | Artículos de interés para ti
    </div>

    <div id="contenido_principal">
      <div class="text_tools_articulos">
        <ul>
          <li><a target="_blank" href="https://www.facebook.com/sharer/sharer.php?u=https://qa.empleo.gob.mx/es_mx/empleo/articulos" ><img src="https://qa.empleo.gob.mx/work/models/empleo/css2014/images/icon-facebook_tools.png" alt="Compartir en facebook" /></a></li>
          <li><a target="_blank" href="https://twitter.com/share" class="twt"><img src="https://qa.empleo.gob.mx/work/models/empleo/css2014/images/icon-twitter_tools.png" alt="Compartir en Twitter" /></a></li>

          <li><a class="iconRecomend" href="#" onclick="openRecommendationModal('recommendi1_55','#000000', 80);void(0);"><img src="https://qa.empleo.gob.mx/work/models/empleo/css2014/images/icon-correo_tools.png" alt="Enviar por correo electrónico" /></a></li>

          <li><a href="javascript:print()" class="iconPrint"><img src="https://qa.empleo.gob.mx/work/models/empleo/css2014/images/icon-imprimir.png"  alt="Imprimir" /></a></li>

        </ul>
      </div>



      <h2>Artículos de interés para ti</h2>
      <ul class="contentArticulos">


        <h4 class="content_BusqTemas">

          <p>Consulta artículos de diversas fuentes que te orientarán en la búsqueda de empleo.</p>
          <div class="BusqTemas">

            <h3>Temas de empleo</h3>
<html:form action="/articuloDeInteress.do" method="post">
              <label for="temasEmpleo"> Selecciona por</label>
              <div class="content_select">
                <p class="bg_select">


                  <select name="temasEmpleo" size="1" id="temasEmpleo">
                      <option value="">Todos</option>
                      <c:forEach  items="${crmBuscadorArticulosForm.listaEtiquetas}" var="etiquetas">
                          <option>${etiquetas.etiqueta}</option>
                      </c:forEach>

                  </select>


                </p>
              </div>
              <!-- <span class="selectRequiredMsg">Seleccione una entidad</span>--></span>
              <input name="buscar" type="submit" value="Buscar" class="bto_form" />
            </html:form>
          </div>
       <c:forEach items="${crmBuscadorArticulosForm.articulosFormList}" var="articulos" varStatus="counter">
            <c:choose>
                <c:when test="${counter.count % 10 == 0 || counter.count == 1}">
                <div class="pagArticulo" style="display: none">

                    <ul class="tema">
                </c:when>
            </c:choose>
            <c:choose>
                <c:when test="${counter.count %2 == 0}">
                    <li class="temaPar">
                </c:when>
                <c:otherwise>
                 <li>
                </c:otherwise>
            </c:choose>
                <h4>${articulos.titulo}</h4>
                <p><strong>Fuente:</strong>${articulos.fuente}</p>
                <p>${articulos.fecha}</p>
                <p>${articulos.articulo}</p>
                    </li>
          <c:choose>

              <c:when test="${counter.count+1 % 10 == 0 || counter.count == crmBuscadorArticulosForm.totalRegistros}">
                </ul>
                </div>
              </c:when>
            </c:choose>
      </c:forEach>


        </div>
        <div class="paginator">
          <p class="mostradas">Mostrando 1 - 10 de 25 Temas</p>
          <ul>
            <li><a class="prev"></a></li>
            <li><span class="current">1</span></li>
            <li><a href="#" class="pagina">2</a></li>
            <li><a href="#" class="pagina">3</a></li>
            <li><a href="#" class="pagina">4</a></li>
            <li><a href="#" class="pagina">5</a></li>
            <li><a href="#" class="pagina">6</a></li>
            <li><a href="#" class="pagina">7</a></li>
            <li><a href="#" class="pagina">8</a></li>
            <li><a href="#" class="pagina">9</a></li>
            <li><a href="#" class="pagina">10</a></li>

            <!-- total de páginas -->
            <li><span class="noPags"> de ${crmBuscadorArticulosForm.totalPaginas} Páginas</span></li>
            <!-- liga para saltar al bloque posterior -->
            <li><a href="#" class="next sig">Siguiente</a></li></ul>
          <p><label for="numPagina"><strong>Página</strong></label>
            <input type="text" name="numPagina" id="numPagina">
            <input type="button" onclick="" value="Ir" class="ir">
          </p>


        </div>

        <script type="text/javascript">
          dojo.require("dojo.on");
          dojo.addOnLoad(function() {
            var actual = 1;
            var paginas = dojo.query('.pagArticulo').length;
            var articulos =${crmBuscadorArticulosForm.totalRegistros};
            var grupo = paginas / 10;
            if (paginas % 10 !== 0)
            {
              grupo++;
            }
            var currentGroup = 0;
            var muestra = function()
            {

              dojo.query('.pagArticulo').forEach(function(li, index)
              {
                var iact = parseInt(actual);
                if ((index + 1) === iact)
                {
                  dojo.style(li, 'display', 'block');
                }
                else
                {
                  dojo.style(li, 'display', 'none');
                }
              });
              createPages();

            };
            var click = function(page)
            {
              actual = page;
              muestra();
            };


            var createPages = function()
            {
              dojo.query('.paginator').forEach(function(enode)
              {
                dojo.empty(enode);
                var istartArt = (parseInt(actual) * 10) - 9;
                var iendArt = istartArt + 9;

                if (iendArt > articulos)
                {
                  iendArt = articulos;
                }
                dojo.create("p", {'class': 'mostradas', innerHTML: 'Mostrando ' + istartArt + ' - ' + iendArt + ' de ' + articulos + ''}, enode);
                var ul = dojo.create("ul", null, enode);
                var iactcurrent = parseInt(actual);
                if (iactcurrent > 1)
                {

                  var prev = dojo.create("li", {innerHTML: '<a class="prev" href="#" >Anterior</a>'}, ul);

                  dojo.query('a', prev).forEach(function(a)
                  {
                    dojo.on(a, 'click', function(e)
                    {
                      e.preventDefault();
                      e.stopPropagation();
                      var iact = parseInt(actual);
                      var nextPage = iact - 1;
                      if (nextPage <= 0)
                      {
                        actual = 1;
                      }
                      else
                      {
                        actual = nextPage;
                      }
                      muestra();
                    });

                  });
                }
                var iactPag = parseInt(actual);
                var startPage = iactPag;
                var endPage = startPage + 9;
                if (endPage > paginas)
                {
                  endPage = paginas;
                }
                if (actual >= (paginas - 9))
                {
                  startPage = paginas - 9;
                  if (startPage <= 0)
                  {
                    startPage = 1;
                  }
                  endPage = startPage + 9;
                  if (endPage > paginas)
                  {
                    endPage = paginas;
                  }
                }
                for (var i = startPage; i <= endPage; i++)
                {
                  var iact = parseInt(actual);
                  if (i === iact)
                  {
                    dojo.create("li", {innerHTML: '<span class="current">' + i + '</span>'}, ul);
                  }
                  else
                  {
                    var node = dojo.create("li", {innerHTML: '<a class="pagina" href="#" >' + i + '</a>'}, ul);
                    dojo.query('a', node).forEach(function(a)
                    {
                      var ipage = a.innerHTML;
                      dojo.on(a, 'click', function(e)
                      {
                        e.preventDefault();
                        e.stopPropagation();
                        click(ipage);
                      });
                    });
                  }
                }
                dojo.create("li", {innerHTML: '<span class="noPags"> de ' + paginas + ' Páginas</span>'}, ul);

                dojo.create('p', {innerHTML: '<label for="numPagina"><strong>Ir a página</strong></label><input type="text" name="numPagina" id="numPagina"><input type="button" id="ir" value="Ir" class="ir">'}, enode);

                dojo.query('#ir', enode).forEach(function(input)
                {
                  dojo.on(input, 'click', function(e)
                  {
                    dojo.query('#numPagina', enode).forEach(function(input)
                    {
                      if (input.value)
                      {
                        var pagBudq = parseInt(input.value);
                        if (!isNaN(pagBudq))
                        {
                          if (pagBudq > paginas)
                          {
                            pagBudq = paginas;
                          }
                          if (pagBudq < 1)
                          {
                            pagBudq = 1;
                          }
                          actual = pagBudq;
                          muestra();
                        }
                      }
                    });
                  });
                });
                if (iactcurrent < paginas)
                {
                  var next = dojo.create("li", {innerHTML: '<a href="" class="next sig">Siguiente</a>'}, ul);
                  dojo.query('a', next).forEach(function(a)
                  {
                    dojo.on(a, 'click', function(e)
                    {
                      e.preventDefault();
                      e.stopPropagation();
                      var iact = parseInt(actual);
                      var nextPage = iact + 1;
                      if (nextPage <= paginas)
                      {
                        actual = nextPage;
                      }
                      else
                      {
                        actual = paginas;
                      }
                      muestra();
                    });
                  });
                }
              });
            };
            createPages();
            muestra();
            if (paginas <= 1)
            {
              dojo.query('.paginator').forEach(function(enode)
              {
                dojo.style(enode, 'display', 'none');
              });
            }
            else
            {
              dojo.query('.paginator').forEach(function(enode)
              {
                dojo.style(enode, 'display', 'block');
              });
            }

          });
        </script>
      </div>
    </div>




    <div class="relacionados">

    </div>
  </div>


