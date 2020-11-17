

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="es-mx" lang="es-mx">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib prefix="logic" uri="http://struts.apache.org/tags-logic" %>
<%@ taglib prefix="bean" uri="http://struts.apache.org/tags-bean" %>
<%@ taglib prefix="s" uri="http://struts.apache.org/tags-logic" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="url" value="${pageContext.request.requestURL}" />
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
  <meta http-equiv="X-UA-Compatible" content="IE=8" />
  
  

  <title>Artículos de interés para ti | Portal del Empleo</title>
  <meta property="og:title" content="El Portal del Empleo ofrece informaci&oacute;n y servicios que te apoyar&aacute;n a mejorar tu b&uacute;squeda de empleo de la manera m&aacute;s f&aacute;cil y novedosa.">
<meta name="twitter:title" content="El Portal del Empleo ofrece informaci&oacute;n y servicios que te apoyar&aacute;n a mejorar tu b&uacute;squeda de empleo de la manera m&aacute;s f&aacute;cil y novedosa.">
<meta property="og:description" content="El Portal del Empleo ofrece informaci&oacute;n y servicios que te apoyar&aacute;n a mejorar tu b&uacute;squeda de empleo de la manera m&aacute;s f&aacute;cil y novedosa.">
<meta name="twitter:description" content="El Portal del Empleo ofrece informaci&oacute;n y servicios que te apoyar&aacute;n a mejorar tu b&uacute;squeda de empleo de la manera m&aacute;s f&aacute;cil y novedosa.">
<meta name="description" content="El Portal del Empleo ofrece informaci&oacute;n y servicios que te apoyar&aacute;n a mejorar tu b&uacute;squeda de empleo de la manera m&aacute;s f&aacute;cil y novedosa."/>
<meta property="og:url" content="${url}">
<meta property="og:site_name" content="Portal del Empleo">
<meta property="og:image" content="http://qa.empleo.gob.mx/css/images/contenido-compartir.jpg">
<meta property="og:image:type" content="image/jpeg">
<meta property="og:image:width" content="1200">
<meta property="og:image:height" content="627">
<meta property="fb:app_id" content="308925772806125" />
<meta name="twitter:card" content="summary_large_image">
<meta name="twitter:site" content="@empleogob_mx">
<meta name="twitter:creator" content="@InfotecMexico">
<meta name="twitter:image:src" content="http://qa.empleo.gob.mx/css/images/compartir-contenido-tweetA.jpg">
<meta name="author" content="infotec">

  <link href="/work/models/empleo/css2014/estilos_empleo2014.css" rel="stylesheet" type="text/css" />
  <link href="/work/models/empleo/css2014/estilos_canalB.css" rel="stylesheet" type="text/css" />
  <link href="/work/models/empleo/css2014/estilos_print.css" rel="stylesheet" type="text/css" media="print" />
  <link rel="shortcut icon" media="all" type="image/x-icon" href="/work/models/empleo/css/favicon.ico" />
  <link rel="alternate" type="application/rss+xml" title="Portal del Empleo" href="https://empleo.gob.mx/rss/DF" />
  <link href="https://fonts.googleapis.com/css?family=Droid+Sans" rel="stylesheet" type="text/css" />

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
      s = s.concat('<div class="swb-recommend">  <h3>Recomendar<\/h3>  <form class="swb-rcmnd-frm" method="post" action="/es_mx/empleo/home/_rid/i1_55/_mto/3/_mod/send" id="frmContact">    <p class="swb-rcmnd-in">      <label for="txtFromName">Remitente:*<\/label>      <input type="text" id="txtFromName" name="txtFromName" value="" maxlength="100"/>    <\/p>    <p class="swb-rcmnd-in">      <label for="txtFromEmail">Correo del remitente:*<\/label>      <input type="text" id="txtFromEmail" name="txtFromEmail" value="" maxlength="80"/>    <\/p>    <p class="swb-rcmnd-in">      <label for="txtToName">Destinatario:*<\/label>      <input type="text" id="txtToName" name="txtToName" maxlength="100" />    <\/p>    <p class="swb-rcmnd-in">      <label for="txtToEmail">Correo del destinatario:*<\/label>      <input type="text" id="txtToEmail" name="txtToEmail" maxlength="80"/>    <\/p>    <p class="swb-rcmnd-in">      <label for="tarMsg">Mensaje adicional:<\/label>      <textarea rows="5" cols="40" id="tarMsg" name="tarMsg"><\/textarea>    <\/p><div class="swb-captcha-imagen">  <p><img src="/swbadmin/jsp/securecode.jsp" alt="" id="imgseccode" width="155" height="65" /></p>  <p><a href="javascript:changeSecureCodeImage(\'imgseccode\');">Cambiar imagen</a></p></div><div class="swb-captcha-txt">  <p><label for="cmnt_seccode">El texto de la imagen es:</label></p>  <p><input type="text" name="code" value=""/></p></div>    <p class="swb-rcmnd-cmd">      <input type="button" value="Cancelar" onclick="removeModal(recomDivId);"/>      <input type="reset" value="Limpiar"/>      <input type="button" value="Enviar" onclick="validate(this.form);"/>    <\/p>  <\/form>  <p class="swb-rcmnd-warn">* Dato requerido<\/p><\/div>');
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

  <script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/dojo/1.9.3/dojo/dojo.js" ></script>
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
      s = s.concat('<div class="swb-recommend">  <h3>Recomendar<\/h3>  <form class="swb-rcmnd-frm" method="post" action="/es_mx/empleo/home/_rid/i1_55/_mto/3/_mod/send" id="frmContact">    <p class="swb-rcmnd-in">      <label for="txtFromName">Remitente:*<\/label>      <input type="text" id="txtFromName" name="txtFromName" value="" maxlength="100"/>    <\/p>    <p class="swb-rcmnd-in">      <label for="txtFromEmail">Correo del remitente:*<\/label>      <input type="text" id="txtFromEmail" name="txtFromEmail" value="" maxlength="80"/>    <\/p>    <p class="swb-rcmnd-in">      <label for="txtToName">Destinatario:*<\/label>      <input type="text" id="txtToName" name="txtToName" maxlength="100" />    <\/p>    <p class="swb-rcmnd-in">      <label for="txtToEmail">Correo del destinatario:*<\/label>      <input type="text" id="txtToEmail" name="txtToEmail" maxlength="80"/>    <\/p>    <p class="swb-rcmnd-in">      <label for="tarMsg">Mensaje adicional:<\/label>      <textarea rows="5" cols="40" id="tarMsg" name="tarMsg"><\/textarea>    <\/p><div class="swb-captcha-imagen">  <p><img src="/swbadmin/jsp/securecode.jsp" alt="" id="imgseccode" width="155" height="65" /></p>  <p><a href="javascript:changeSecureCodeImage(\'imgseccode\');">Cambiar imagen</a></p></div><div class="swb-captcha-txt">  <p><label for="cmnt_seccode">El texto de la imagen es:</label></p>  <p><input type="text" name="code" value=""/></p></div>    <p class="swb-rcmnd-cmd">      <input type="button" value="Cancelar" onclick="removeModal(recomDivId);"/>      <input type="reset" value="Limpiar"/>      <input type="button" value="Enviar" onclick="validate(this.form);"/>    <\/p>  <\/form>  <p class="swb-rcmnd-warn">* Dato requerido<\/p><\/div>');
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


  <script type="text/javascript" src="/swbadmin/js/swb.js" ></script>

  <script src="/work/models/empleo/js/dw_sizerdx.js" type="text/javascript" ></script>
  <script src="/work/models/empleo/js/dw_cookies.js" type="text/javascript" ></script>
  <script src="/work/models/empleo/js/dw_event.js" type="text/javascript" ></script>
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
                '	<a class="naranja" href="<%=response.encodeURL(request.getContextPath() + "/recupera_contrasena.do" )%>">¿Olvidaste tu usuario o contrase&ntildea? </a>'+
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
      var url = '/work/jsp/loginapp.jsp?login='+ login +'&password='+ pwd;
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






</head>
<body >
<div id="wrapper">
  <div id="herramientas">
    <div id="perfil_usuario_2014Int">
      <a class="iniciaSesion_Int" href="<c:url value="/login.do?method=userlogged&seccion=espacio"/>">Ir a mi espacio</a></p>
      <div style="clear:both;"></div>
    </div>

  </div>
  <div id="header">
    <h1><a href="/en_mx/empleo/home" >Portal del Empleo : llama al 01 800 841 20 20</a></h1>


    <img src="/work/models/empleo/jsp/cil/images/logo_STPS.png" alt="Secretar&iacute;a del Trabajo y Previsi&oacute;n Social: Servicio Nacional de Empleo" />

    <div id="nav_rapido">
      <ul>
        <li><a href="/swb/empleo/mapa_sitio">Mapa de sitio</a></li>
        <li><a href="<c:url value="/miespacionav.do?method=agendaCita"/>">Solicita una cita</a></li>
        <li><a href="/swb/empleo/contacto" >Contacto</a></li>
      </ul>

    </div>
  </div>
  <div id="navegacion">

    <ul>
      <li>
        <a href="/es_mx/empleo/candidatos" class="swb-menumap-act">Candidatos</a>
      </li><li>
      <a href="/es_mx/empleo/empresas" class="swb-menumap-act">Empresas</a>
    </li><li>
      <a href="/es_mx/empleo/servicio_nacional_de_empleo_" class="swb-menumap-act">Servicio Nacional de Empleo</a>
    </li>
    </ul>


  </div>

  <div id="espacio_buscador_interno">
    <div id="buscador_interno" class="ac_2014Interno">
      <h2>Buscador de ofertas de empleo</h2>

      <form name="ocp" id="ocp" action="http://qa.empleo.gob.mx/ocupate.do" method="get">
        <div>
          <input type="hidden" name="method" value="init" />
          <input type="hidden" name="searchQ" value="" />
          <div class="que_empleo">
            <p class="buscador_1">
              <label for="searchTopic" class="t_buscador b2">¿Qué empleo buscas?
                <input name="searchTopic" id="searchTopic" value="" type="text" /></label></p>
            <p class="ejemplo">Puedes indicar un puesto, carrera u oficio</p>
          </div>
          <div class="donde" id="spryselect1">
            <p id="combo">
              <label for="searchPlace" class="t_buscador">¿Dónde?
                <select name="searchPlace" id="searchPlace">
                  <option value="" selected="selected"></option>
                  <option value="1">Aguascalientes</option>
                  <option value="2">Baja California</option>
                  <option value="3">Baja California Sur</option>
                  <option value="4">Campeche</option>
                  <option value="5">Coahuila</option>
                  <option value="6">Colima</option>
                  <option value="7">Chiapas</option>
                  <option value="8">Chihuahua</option>
                  <option value="9">Distrito Federal</option>
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
                </select></label>
            </p>
            <p class="ejemplo"><a href="/swb/empleo/Uso_del_Buscador">¿Cómo utilizar el buscador?</a></p>
          </div>
          <input type="button" name="bt_buscador" id="bt_buscador" onclick="employ()" onkeypress="employ()" value="Buscar" />
        </div>
      </form>
      <div class="busqueda_especifica">También puedes realizar una <a href="<c:url value="/busquedaEspecificaOfertas.do?method=buscar"/>">búsqueda específica</a></div>


    </div>

  </div>


  <div class="clear"></div>
  <div class="contacto_op">
    <ul>
      <li><a class="ayuda_int" href="/swb/empleo/Necesitas_ayuda">¿Necesitas ayuda? Inicia una asesoría</a></li>
      <li><a class="atencion_int" href="/swb/empleo/contacto">Atención telefónica 01 800 841 2020</a></li>
      <li><a class="quejas_int" onclick="window.open(this.href, this.target, 'toolbar=no,directories=no,scrollbars=no,location=no,statusbar=no,menubar=no,resizable=yes,width=830,height=700'); return false;" target="popUp" href="<c:url value="/suggestion.do?method=init"/>">Quejas y sugerencias</a></li>
    </ul>
    <div class="clearfix"></div>
  </div>




  <div id="cuerpo_int">
    <div id="ruta_navegacion">
      Ruta de navegaci&oacute;n: <a href="/es_mx/empleo/home"  >Inicio</a> | Herramientas del sitio | Artículos de interés para ti
    </div>

    <div id="contenido_principal">
      <div class="text_tools_articulos">
        <ul>
          <li><a target="_blank" href="https://www.facebook.com/sharer/sharer.php?u=https://qa.empleo.gob.mx/es_mx/empleo/articulos" ><img src="/work/models/empleo/css2014/images/icon-facebook_tools.png" alt="Compartir en facebook" /></a></li>
          <li><a target="_blank" href="https://twitter.com/share" class="twt"><img src="/work/models/empleo/css2014/images/icon-twitter_tools.png" alt="Compartir en Twitter" /></a></li>

          <li><a class="iconRecomend" href="#" onclick="openRecommendationModal('recommendi1_55','#000000', 80);void(0);"><img src="/work/models/empleo/css2014/images/icon-correo_tools.png" alt="Enviar por correo electrónico" /></a></li>

          <li><a href="javascript:print()" class="iconPrint"><img src="/work/models/empleo/css2014/images/icon-imprimir.png"  alt="Imprimir" /></a></li>

        </ul>
      </div>



      <h2>Artículos de interés para ti</h2>
      <div class="contentArticulos">






        <div class="content_BusqTemas">

          <p>Consulta artículos de diversas fuentes que te orientarán en la búsqueda de empleo.</p>
          <div class="BusqTemas">

            <h3>Temas de empleo</h3>
            <form action="/es_mx/empleo/articulos" method="post">
              <label for="temasEmpleo"> Selecciona por</label>
              <div class="content_select">
                <p class="bg_select">
                  <select name="temasEmpleo" size="1" id="temasEmpleo">

                    <option value="">Todos</option>

                    <option >Actitud</option>

                    <option >Adultos mayores</option>

                    <option >Aplicaciones móviles</option>

                    <option >Bolsas de trabajo</option>

                    <option >Capacitación</option>

                    <option >Clima laboral</option>

                    <option >Consejos</option>

                    <option >Contratación</option>

                    <option >Currículum</option>

                    <option >Empresas</option>

                    <option >Entrevista</option>

                    <option >Especialidades</option>

                    <option >Estadísticas</option>

                    <option >Idiomas</option>

                    <option >Indicadores</option>

                    <option >Internet</option>

                    <option >Jóvenes</option>

                    <option >Lenguaje corporal</option>

                    <option >Mujeres</option>

                    <option >Perfiles</option>

                    <option >Personas con discapacidad</option>

                    <option >Prestaciones</option>

                    <option >Reclutador</option>

                    <option >Redes sociales</option>

                    <option >Tecnología</option>

                    <option >Vacantes</option>

                    <option >Vestimenta</option>

                    <option >empresas</option>

                  </select>
                </p>
              </div>
              <!-- <span class="selectRequiredMsg">Seleccione una entidad</span>--></span>
              <input name="buscar" type="submit" value="Buscar" class="bto_form" />
            </form>
          </div>

          <div class="pagArticulo" style="display: none">
            <ul class="tema">


              <li ><h4>¿Por qué no te conviene trabajar muchas horas?</h4>
                <p><strong>Fuente:</strong>GMCRH</p>
                <p>17-nov-2015</p>
                <p>Un gran cúmulo de investigación sugiere que, independientemente de nuestros motivos para trabajar muchas horas, jefes demandantes, incentivos financieros, ambición personal, la sobrecarga de trabajo no ayuda.<a href="/es_mx/empleo/por_que_no_conviene_trabajar_muchas_horas">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>¿Qué errores cometen los reclutadores?</h4>
                <p><strong>Fuente:</strong>InfoJobs</p>
                <p>13-nov-2015</p>
                <p>Los técnicos de selección son profesionales, pero en ocasiones pueden equivocarse. En este artículo te comentamos algunos de los errores que cometen.<a href="/es_mx/empleo/errores_cometen_reclutadores">Leer más</a></p>
              </li>

              <li ><h4>Entrevistas maratónicas... ¿Cómo sobrevivir a ellas?</h4>
                <p><strong>Fuente:</strong>GMCRH</p>
                <p>12-nov-2015</p>
                <p>Para muchos candidatos el desafío más grande llega una vez que han superado filtros iniciales, como una entrevista telefónica y una reunión inicial uno a uno.<a href="/es_mx/empleo/entrevistas_maratonicas_sobrevivir_ellas">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Plan de acción para buscar trabajo</h4>
                <p><strong>Fuente:</strong>Empresariados</p>
                <p>11-nov-2015</p>
                <p>Muchos emprendedores que no tuvieron suerte después con su negocio, deciden volver a buscar trabajo por cuenta ajena. Otros muchos profesionales también buscan empleo experimentando las inseguridades lógicas cuando el desempleo se prolonga en el tiempo.<a href="/es_mx/empleo/plan_accion_buscar_trabajo">Leer más</a></p>
              </li>

              <li ><h4>¿Son los tatuajes un problema para conseguir trabajo?</h4>
                <p><strong>Fuente:</strong>Universia</p>
                <p>10-nov-2015</p>
                <p>En una sociedad en que los tatuajes se ponen cada vez más de moda, te contamos qué tanto peso tienen a la hora de conseguir trabajo.<a href="/es_mx/empleo/tatuajes_problema_conseguir_trabajo">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Nuevos estudios que pueden darte un empleo</h4>
                <p><strong>Fuente:</strong>Expansión</p>
                <p>9-nov-2015</p>
                <p>La formación online, la combinación de títulos y posgrados más prácticos son las principales apuestas de los centros educativos para acercarse al mercado laboral.<a href="/es_mx/empleo/nuevos_estudios_pueden_darte_empleo">Leer más</a></p>
              </li>

              <li ><h4>La entrevista de trabajo en inglés: cómo superarla</h4>
                <p><strong>Fuente:</strong>Busco un Trabajo</p>
                <p>6-nov-2015</p>
                <p>Está buscando trabajo, enviando currículums, haciendo llamadas? y le convocan a una entrevista laboral, en inglés para más señas.<a href="/es_mx/empleo/La_entrevista_de_trabajo_en_ingles_como_superarla">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Cinco razones por las que querrán contratarte</h4>
                <p><strong>Fuente:</strong>Adecco</p>
                <p>3-nov-2015</p>
                <p>Conoce algunas de las razones por las que puedes destacar por encima del resto de candidatos.<a href="/es_mx/empleo/Cinco_razones_por_las_que_querran_contratarte">Leer más</a></p>
              </li>

              <li ><h4>Necesito un trabajo que se adapte a mí tiempo</h4>
                <p><strong>Fuente:</strong>GMCRH</p>
                <p>3-nov-2015</p>
                <p>Existen empleos con diferentes horarios y  jornadas, es muy importante señalar que ambas palabras no significan lo mismo. Conoce la diferencia en este artículo.<a href="/es_mx/empleo/necesito_trabajo_adapte_mi_tiempo">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Competencias profesionales que las empresas buscan en los trabajadores</h4>
                <p><strong>Fuente:</strong>Empresariados</p>
                <p>30-oct-2015</p>
                <p>¿Quieres saber cuál es una de las competencias más valoradas por las empresas? Sigue leyendo.<a href="/es_mx/empleo/competencias_profesionales_empresas_buscan_trabajadores">Leer más</a></p>
              </li>




            </ul>
          </div>
<html:form action="/buscadorForm">
          <ul class="tema">
            <logic:iterate id="articulosForm" name="buscadorArticulosForm" property="articulosFormList">

            <li ><h4><bean:write name="articulosForm"  property="titulo"></bean:write></h4>
              <p><strong>Fuente:</strong><bean:write name="articulosForm"  property="fuente"></bean:write></p>
              <p><bean:write name="articulosForm"  property="fecha"></bean:write></p>
              <p>Todo lo que imaginamos, pensamos y sentimos se refleja a través de nosotros mediante el lenguaje (verbal y no verbal). Cada persona desarrolla un mapa lingüístico único en su interacción con los demás.<a href="/es_mx/empleo/sabes_lenguaje_utilizar_entrevista">Leer más</a></p>
            </li>
</ul>
  </logic:iterate>
          </html:form>
          <div class="pagArticulo" style="display: none">

            <ul class="tema">


              <li ><h4>Conductas que el reclutador detesta (y no lo dice)</h4>
                <p><strong>Fuente:</strong>CNN Expansión</p>
                <p>30-oct-2015</p>
                <p>7 de cada 10 mexicanos cometen errores en las entrevistas de trabajo que los descartan; soberbia es 1 de 9 actitudes que irritan al empleador. Conoce las demás.<a href="/es_mx/empleo/conductas_reclutador_detesta_no_dice">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>El lunes es el mejor día para buscar trabajo</h4>
                <p><strong>Fuente:</strong>Universia</p>
                <p>30-oct-2015</p>
                <p>¿Quieres conseguir trabajo? Empieza a enviar tus solicitudes los lunes, porque según un estudio éste es el mejor día para buscar empleo.<a href="/es_mx/empleo/lunes_mejor_dia_buscar_trabajo">Leer más</a></p>
              </li>

              <li ><h4>¿Qué hacer después de que te despiden?</h4>
                <p><strong>Fuente:</strong>Dinero en Imagen</p>
                <p>30-oct-2015</p>
                <p>A pesar de la situación en que te encuentres, la pregunta de ?¿qué sigue ahora?? no dejará de perseguirte. Enfréntala con la mejor actitud.<a href="/es_mx/empleo/que_hacer_despues_te_despiden">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Cómo redactar un CV con gancho</h4>
                <p><strong>Fuente:</strong>InfoJobs</p>
                <p>23-oct-2015</p>
                <p>El currículum vitae ha evolucionado y se ha transformado en algo más que un documento en el que resumir tus méritos.<a href="/es_mx/empleo/como_redactar__cv_con_gancho">Leer más</a></p>
              </li>

              <li ><h4>Dí qué puedes hacer | Elevator pitch</h4>
                <p><strong>Fuente:</strong>Busco un Trabajo</p>
                <p>22-oct-2015</p>
                <p>En todos esos curriculums que has hecho, en todos los portales registrados y en todos esos mails? ¿has reflejado realmente quién eres y qué puedes aportar?<a href="/es_mx/empleo/di_que_puedes_hacer_elevator_pitch">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Cursos en línea ¿atractivos para el reclutador?</h4>
                <p><strong>Fuente:</strong>CNN Expansión</p>
                <p>21-oct-2015</p>
                <p>Seis de cada 10 profesionales en México se capacita a través de cursos en línea; certificar este conocimiento y practicarlo aumenta la rentabilidad del curso frente a las empresas.<a href="/es_mx/empleo/cursos_linea_atractivos_para_reclutador">Leer más</a></p>
              </li>

              <li ><h4>¿Eres más de mus o de poker?</h4>
                <p><strong>Fuente:</strong>Zumo de Empleo</p>
                <p>21-oct-2015</p>
                <p>Has cambiado tu CV 10 veces y, aunque encajes en el perfil, ¿no te llaman a la entrevista? Esto te interesa.<a href="/es_mx/empleo/eres_mas_mus_o_poker">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Cómo utilizar tu Solicitud de Empleo para redactar tu CV</h4>
                <p><strong>Fuente:</strong>OCC Mundial</p>
                <p>20-oct-2015</p>
                <p>¿Alguna vez has llenado una Solicitud de Empleo? Entonces también puedes hacer un CV.<a href="/es_mx/empleo/como_utilizar_solicitud_empleo_redactar_cv">Leer más</a></p>
              </li>

              <li ><h4>Cuatro errores en la realización de un portafolio de trabajo</h4>
                <p><strong>Fuente:</strong>Empresariados</p>
                <p>19-oct-2015</p>
                <p>Así como ocurre en el currículum, un portafolio profesional también es susceptible de mejora a través de posteriores revisiones y correcciones. No cometas estos errores.<a href="/es_mx/empleo/cuatro_errores_realizacion_portafolio_trabajo">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Cuatro formas de superar un rechazo en tu vida profesional</h4>
                <p><strong>Fuente:</strong>Universia</p>
                <p>19-oct-2015</p>
                <p>Ser rechazados es parte de la vida, pero hay formas de seguir adelante. Conoce cuatro formas de superar un rechazo con la cabeza en alto.<a href="/es_mx/empleo/cuatro_formas_superar_rechazo_vida_profesional">Leer más</a></p>
              </li>




            </ul>

          </div>

          <div class="pagArticulo" style="display: none">
            <ul class="tema">


              <li ><h4>¿Sabes qué lenguaje debes utilizar en una entrevista?</h4>
                <p><strong>Fuente:</strong>InfoJobs</p>
                <p>16-oct-2015</p>
                <p>Todo lo que imaginamos, pensamos y sentimos se refleja a través de nosotros mediante el lenguaje (verbal y no verbal). Cada persona desarrolla un mapa lingüístico único en su interacción con los demás.<a href="/es_mx/empleo/sabes_lenguaje_utilizar_entrevista">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>¿Marcas la diferencia?</h4>
                <p><strong>Fuente:</strong>Busco un Trabajo</p>
                <p>15-oct-2015</p>
                <p>A veces es más importante saber marcar la diferencia que cualquier otra cosa a la hora de hacernos nuestro hueco en el mercado de trabajo. Descúbrelo.<a href="/es_mx/empleo/marcas_la_diferencia">Leer más</a></p>
              </li>

              <li ><h4>Cuatro formas de venderte a un reclutador si ya has fracasado muchas veces</h4>
                <p><strong>Fuente:</strong>Expansión</p>
                <p>14-oct-2015</p>
                <p>Los métodos de los concursos de talento ('La Voz', por ejemplo) pueden servir para fortalecer tu discurso. Te decimos cómo.<a href="/es_mx/empleo/cuatro_formas_venderte_reclutador_si_has_fracasado_muchas_veces">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Lo que debes saber sobre las prestaciones laborales</h4>
                <p><strong>Fuente:</strong>OCC Mundial</p>
                <p>13-oct-2015</p>
                <p>Las prestaciones laborales son tan importantes como tu salario ¿las conoces todas?<a href="/es_mx/empleo/lo_que_debes_saber_sobre_prestaciones_laborales">Leer más</a></p>
              </li>

              <li ><h4>¿Pones toda tu experiencia en el curriculum?</h4>
                <p><strong>Fuente:</strong>Busco un Trabajo</p>
                <p>16-jul-2015</p>
                <p>Lo importante es que el curriculum se adapte a la oferta pero no es bueno tampoco que borres o elimines experiencia, aprende la mejor manera de estructurar tu CV con estas recomendaciones.<a href="/es_mx/empleo/pones_toda_experiencia_curriculum">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Los cuatro pecados capitales de la entrevista personal</h4>
                <p><strong>Fuente:</strong>InfoJobs</p>
                <p>15-jul-2015</p>
                <p>En este artículo te explicamos cuáles son para que puedas evitarlos y para ayudarte en tu camino por conseguir empleo.<a href="/es_mx/empleo/cuatro_pecados_capitales_entrevista_personal">Leer más</a></p>
              </li>

              <li ><h4>Consigue el trabajo que quieres armando un currículum vitae creativo</h4>
                <p><strong>Fuente:</strong>Universia</p>
                <p>14-jul-2015</p>
                <p>Conseguir ese trabajo que tanto quieres dependerá de armar un currículum vitae que se distinga de los demás.<a href="/es_mx/empleo/consigue_trabajo_quieres_armando_curriculum_vitae_creativo">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Utiliza las técnicas del FBI para detectar el empleo de tu vida</h4>
                <p><strong>Fuente:</strong>Expansión</p>
                <p>13-jul-2015</p>
                <p>En una entrevista de trabajo, plantear las preguntas adecuadas y cuestionar las respuestas forma parte de la dinámica para analizar, valorar y concretar el perfil del entrevistado.<a href="/es_mx/empleo/utiliza_tecnicas_fbi_detectar_empleo_vida">Leer más</a></p>
              </li>

              <li ><h4>Preguntas que NO tienes que responder en una entrevista laboral</h4>
                <p><strong>Fuente:</strong>Bienestar 180</p>
                <p>10-jul-2015</p>
                <p>Una entrevista de trabajo puede ser muy estresante, sin embargo, es básico que sepas que tienes derechos. Conócelos.<a href="/es_mx/empleo/preguntas_no_tienes_responder_entrevista_laboral">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Utiliza ejemplos de cartas de presentación</h4>
                <p><strong>Fuente:</strong>Busco un Trabajo</p>
                <p>9-jul-2015</p>
                <p>Si no sabes cómo redactar tu carta de presentación este artículo te ayudará a darte algunas ideas para que tu búsqueda de empleo sea más fácil.<a href="/es_mx/empleo/utiliza_ejemplos_cartas_presentacion">Leer más</a></p>
              </li>




            </ul>
          </div>

          <div class="pagArticulo" style="display: none">
            <ul class="tema">


              <li ><h4>¿Cómo explicar una mala experiencia laboral?</h4>
                <p><strong>Fuente:</strong>Adecco</p>
                <p>9-jul-2015</p>
                <p>A lo largo de una entrevista de trabajo, es normal que te pregunten por tu experiencia previa en otras compañías, aprende cómo responder adecuadamente.<a href="/es_mx/empleo/como_explicar_mala_experiencia_laboral">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Cambio de trabajo **4 semanas** antes de buscar trabajo de nuevo</h4>
                <p><strong>Fuente:</strong>Coaching Virtual</p>
                <p>8-jul-2015</p>
                <p>Se necesitan al menos cuatro semanas antes de buscar un nuevo trabajo cuando se ha dejado el anterior de una forma 'traumática', cada semana tiene un objetivo, conócelos y llévalos a la práctica.<a href="/es_mx/empleo/cambio_trabajo_4_semanas_antes_buscar_trabajo_nuevo">Leer más</a></p>
              </li>

              <li ><h4>Pautas para valorar una oferta de trabajo</h4>
                <p><strong>Fuente:</strong>CV Coach</p>
                <p>7-jul-2015</p>
                <p>¿Te conviene aceptar este empleo? Aquí listamos algunas cosas que debes tener presentes a la hora de valorar una oferta.<a href="/es_mx/empleo/pautas_valorar_oferta_trabajo">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>¿Quién es tu fan?</h4>
                <p><strong>Fuente:</strong>Zumo de Empleo</p>
                <p>3-jul-2015</p>
                <p>Si eres de los que aplica 'La ley de la estadística' y crees que entre más CV mandes, más posibilidades tienes de conseguir el empleo de tus sueños, sigue leyendo.<a href="/es_mx/empleo/quien_es_tu_fan">Leer más</a></p>
              </li>

              <li ><h4>Claves para crear un buen currículum</h4>
                <p><strong>Fuente:</strong>InfoJobs</p>
                <p>2-jul-2015</p>
                <p>El CV tiene que caracterizarse por ser breve, estructurado y, sobre todo, comprensible. Aprende cómo lograrlo en este artículo.<a href="/es_mx/empleo/claves_crear_buen_curriculum">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>El proceso de selección</h4>
                <p><strong>Fuente:</strong>Busco un Trabajo</p>
                <p>1-jul-2015</p>
                <p>Veamos en qué consiste un proceso de selección y que puedes hacer para convertirte en uno de los/as candidatos/as mejor valorados.<a href="/es_mx/empleo/proceso_seleccion">Leer más</a></p>
              </li>

              <li ><h4>Nuevas habilidades que buscan las empresas</h4>
                <p><strong>Fuente:</strong>Adecco</p>
                <p>1-jul-2015</p>
                <p>Las empresas buscan, cada vez más, profesionales con perfiles polivalentes, capaces de diferenciarse del resto y ofrecer valor por sí mismos.<a href="/es_mx/empleo/nuevas_habilidades_buscan_empresas">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Desempleo: lo que debes hacer para no perder la cordura</h4>
                <p><strong>Fuente:</strong>Universia</p>
                <p>30-jun-2015</p>
                <p>Descubre cómo mantener la cordura en este difícil período, siguiendo estos consejos.<a href="/es_mx/empleo/desempleo_debes_hacer_no_perder_cordura">Leer más</a></p>
              </li>

              <li ><h4>Evalúa la igualdad de oportunidades en tu contratación</h4>
                <p><strong>Fuente:</strong>Generadores de Mejoras Contínuas en Recursos Humanos</p>
                <p>30-jun-2015</p>
                <p>Antes de aceptar un trabajo,  asegúrate de que sea una empresa preocupada por mantenerse a la vanguardia en esquemas de ambiente laboral.<a href="/es_mx/empleo/evalua_igualdad_oportunidades_contratacion">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Tanto WhatsApp puede no ayudarte con los reclutadores</h4>
                <p><strong>Fuente:</strong>OCC Mundial</p>
                <p>29-jun-2015</p>
                <p>La comunicación oral y escrita es una de las habilidades básicas más buscadas por las empresas y también, la que menos
                  encuentran.<a href="/es_mx/empleo/tanto_whatsapp_puede_no_ayudarte_reclutadores">Leer más</a></p>
              </li>




            </ul>
          </div>

          <div class="pagArticulo" style="display: none">
            <ul class="tema">


              <li ><h4>Lo que no dices...no cuenta</h4>
                <p><strong>Fuente:</strong>Busco un Trabajo</p>
                <p>26-jun-2015</p>
                <p>La persona que lee nuestro currículum en un portal de empleo o que nos ve por primera vez en una entrevista, no nos conoce, y debemos explicarle lo necesario para facilitarle la tarea de averiguar si somos el candidato adecuado.<a href="/es_mx/empleo/lo_que_no_dices_no_cuenta">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Cuatro preguntas que pueden dejarte en blanco en una entrevista</h4>
                <p><strong>Fuente:</strong>InfoJobs</p>
                <p>25-jun-2015</p>
                <p>Ciertas preguntas pueden ser realmente comprometedoras, por lo que es muy importante estar preparado para responderlas.<a href="/es_mx/empleo/cuatro_preguntas_pueden_dejarte_en_blanco_entrevista">Leer más</a></p>
              </li>

              <li ><h4>Cuatro secretos para mostrarte seguro en una entrevista</h4>
                <p><strong>Fuente:</strong>Adecco Blog</p>
                <p>24-jun-2015</p>
                <p>Estos cuatro elementos son buenas herramientas que te ayudarán a mostrarte más seguro en una presentación o entrevista.<a href="/es_mx/empleo/cuatro_secretos_mostrarte_seguro_entrevista">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Ocho tips para crear tu Carta de Motivación</h4>
                <p><strong>Fuente:</strong>Student Job</p>
                <p>24-jun-2015</p>
                <p>Este documento te da la oportunidad de ampliar la información que no ha sido posible explicar en tu CV. Conoce más.<a href="/es_mx/empleo/ocho_tips_crear_carta_motivacion">Leer más</a></p>
              </li>

              <li ><h4>El titular de tu carta de presentación, el secreto del éxito</h4>
                <p><strong>Fuente:</strong>CV Coach</p>
                <p>23-jun-2015</p>
                <p>La carta de presentación y, sobre todo, el título de ésta,  puede hacer que un reclutador quiera saber más de ti o te descarte.<a href="/es_mx/empleo/titular_carta_presentacion_secreto_exito">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Preguntas ilegales: cómo responderlas</h4>
                <p><strong>Fuente:</strong>InfoJobs</p>
                <p>23-jun-2015</p>
                <p>Te damos algunas pautas para responder a preguntas ilegales en la entrevista de trabajo.<a href="/es_mx/empleo/preguntas_ilegales_como_responderlas">Leer más</a></p>
              </li>

              <li ><h4>Consejos para trabajar como ejecutivo de ventas</h4>
                <p><strong>Fuente:</strong>Empresariados</p>
                <p>22-jun-2015</p>
                <p>Para trabajar como ejecutivo de ventas y destacar a nivel profesional en este ámbito es importante que sepas esto.<a href="/es_mx/empleo/consejos_trabajar_ejecutivo_ventas">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>¿Buscas trabajo? ¡Limpia tu reputación online!</h4>
                <p><strong>Fuente:</strong>OCC Mundial</p>
                <p>22-jun-2015</p>
                <p>Ya tienes listo tu currículum y estás a punto de postularte, antes de hacerlo tienes algo más que revisar: tu reputación online.<a href="/es_mx/empleo/buscas_trabajo_limpia_reputacion_online">Leer más</a></p>
              </li>

              <li ><h4>Cinco factores a tener en cuenta antes de aceptar una oferta de empleo</h4>
                <p><strong>Fuente:</strong>Generadores de Mejoras Contínuas en Recursos Humanos</p>
                <p>12-jun-2015</p>
                <p>Son muchas las razones que pueden llevarnos a buscar un nuevo desafío laboral; sin embargo, antes de concretar el cambio es necesario analizar los pros y los contras.<a href="/es_mx/empleo/cinco_factores_tener_cuenta_antes_aceptar_oferta_empleo">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Cómo solicitar una carta de recomendación</h4>
                <p><strong>Fuente:</strong>Empresariados</p>
                <p>11-jun-2015</p>
                <p>Existen muchos trabajadores que no se sienten cómodos solicitando una carta de recomendación. ¿Cómo lograr este objetivo?<a href="/es_mx/empleo/como_solicitar_carta_recomendacion">Leer más</a></p>
              </li>




            </ul>
          </div>

          <div class="pagArticulo" style="display: none">
            <ul class="tema">


              <li ><h4>Dime qué tipo de profesional eres, y te diré lo que tienes que hacer</h4>
                <p><strong>Fuente:</strong>Zumo de Empleo</p>
                <p>10-jun-2015</p>
                <p>¿Cuál es la fórmula que en este momento te puede llevar hasta el trabajo que deseas? Descúbrelo en este artículo.<a href="/es_mx/empleo/dime_tipo_profesional_eres_te_dire_tienes_hacer">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>El hombre que trabajó en 28 empleos durante 28 semanas</h4>
                <p><strong>Fuente:</strong>OCC Mundial</p>
                <p>9-jun-2015</p>
                <p>¿Qué piensan las empresas cuando ven en tu CV que has cambiado muy seguido de trabajo?<a href="/es_mx/empleo/hombre_trabajo_28_empleos_durante_28_semanas">Leer más</a></p>
              </li>

              <li ><h4>¿Cuándo utilizar texto en negrita en el CV?</h4>
                <p><strong>Fuente:</strong>Adecco Blog</p>
                <p>8-jun-2015</p>
                <p>¿Qué es lo que habitualmente mira un reclutador en un currículum? Continúa leyendo.<a href="/es_mx/empleo/cuando_utilizar_negrita_cv">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Es importante resaltar tus cualidades en una entrevista de trabajo</h4>
                <p><strong>Fuente:</strong>Dinero en Imagen</p>
                <p>5-jun-2015</p>
                <p>Es necesario elaborar un CV que capte la atención del lector y muestre una combinación entre habilidades, experiencias y logros.<a href="/es_mx/empleo/importante_resaltar_cualidades_entrevista_trabajo">Leer más</a></p>
              </li>

              <li ><h4>Cinco reglas para brillar en una videoentrevista laboral</h4>
                <p><strong>Fuente:</strong>CNN Expansión</p>
                <p>4-jun-2015</p>
                <p>Cada vez más empresas solicitan a sus candidatos enviar grabaciones para postularse a una vacante; lúcete con estos consejos.<a href="/es_mx/empleo/cinco_reglas_brillar_videoentrevista_laboral">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Cuatro consejos para optimizar tu CV en InfoJobs</h4>
                <p><strong>Fuente:</strong>InfoJobs</p>
                <p>29-may-2015</p>
                <p>¿Tu CV es atractivo para las empresas? Tu experiencia, tus conocimientos y tener un CV bien redactado son aspectos clave en la búsqueda de empleo.<a href="/es_mx/empleo/cuatro_consejos_optimizar_cv_infojobs">Leer más</a></p>
              </li>

              <li ><h4>Examinan empleadores redes sociales de solicitantes antes de contratar</h4>
                <p><strong>Fuente:</strong>Universia</p>
                <p>29-may-2015</p>
                <p>Cada vez más empresas monitoreen las redes sociales de los solicitantes de empleo previo a su contratación.<a href="/es_mx/empleo/examinan_empleadores_redes_sociales_solicitantes_antes_contratar">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Cómo afrontar la búsqueda de empleo</h4>
                <p><strong>Fuente:</strong>Busco un Trabajo</p>
                <p>28-may-2015</p>
                <p>Aprende a afrontar la búsqueda de empleo de una manera menos exigente sin dejar de hacer lo que tienes que hacer.<a href="/es_mx/empleo/como_afrontar_busqueda_empleo">Leer más</a></p>
              </li>

              <li ><h4>Tres fases en la entrevista de trabajo</h4>
                <p><strong>Fuente:</strong>Empresariados</p>
                <p>27-may-2015</p>
                <p>Una entrevista de trabajo es una prueba que aunque en muchos casos tiene una duración breve, siempre sigue una estructura coherente y lógica. Conócela.<a href="/es_mx/empleo/tres_fases_entrevista_trabajo">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>¿Es hora de actualizar mi CV?</h4>
                <p><strong>Fuente:</strong>Adecco Blog</p>
                <p>26-may-2015</p>
                <p>Para saber si es necesario realizar una nueva actualización en tu currículum te invitamos a leer este artículo.<a href="/es_mx/empleo/hora_actualizar_cv">Leer más</a></p>
              </li>




            </ul>
          </div>

          <div class="pagArticulo" style="display: none">
            <ul class="tema">


              <li ><h4>Cuatro consejos para hacer de tu hobby un trabajo</h4>
                <p><strong>Fuente:</strong>Universia</p>
                <p>25-may-2015</p>
                <p>Si eres de esas personas que están dispuestas a arriesgarse para hacer de tu pasión su forma de vida, entonces estos 4 consejos son para ti.<a href="/es_mx/empleo/cuatro_consejos_hacer_hobby_trabajo">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Evite autosabotearse en su entrevista laboral</h4>
                <p><strong>Fuente:</strong>El Economista</p>
                <p>25-may-2015</p>
                <p>Falsear información, la informalidad y hablar mal de sus ex jefes son algunos de los errores más comunes.<a href="/es_mx/empleo/evite_autosabotearse_entrevista_laboral">Leer más</a></p>
              </li>

              <li ><h4>Aprende a enfrentar un despido laboral</h4>
                <p><strong>Fuente:</strong>OCC Mundial</p>
                <p>22-may-2015</p>
                <p>Acéptalo, supéralo y explícalo de manera inteligente en tu próxima entrevista de trabajo.<a href="/es_mx/empleo/aprende_enfrentar_despido_laboral">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Posgrado, ¿la opción para universitarios desempleados?</h4>
                <p><strong>Fuente:</strong>Alto Nivel</p>
                <p>18-may-2015</p>
                <p>Según la ANUIES, actualmente el 40% de los universitarios egresados, está desempleado. ¿Es la especialización la solución para ellos?<a href="/es_mx/empleo/posgrado_opcion_universitarios_desempleados">Leer más</a></p>
              </li>

              <li ><h4>Cuatro preguntas que pueden discriminar en una entrevista laboral</h4>
                <p><strong>Fuente:</strong>CNN Expansión</p>
                <p>15-may-2015</p>
                <p>El Código Penal del DF tipifica como delito negar un trabajo por edad, sexo, embarazo, entre otros.<a href="/es_mx/empleo/cuatro_preguntas_pueden_discriminar_entrevista_laboral">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Cómo encontrar el trabajo que quieres</h4>
                <p><strong>Fuente:</strong>InfoJobs</p>
                <p>14-may-2015</p>
                <p>Es posible si te organizas y, sobre todo, si diriges tus esfuerzos de la manera adecuada. Pero, ¿sabes por dónde empezar?<a href="/es_mx/empleo/como_encontrar_trabajo_quieres">Leer más</a></p>
              </li>

              <li ><h4>No te contratarán si...</h4>
                <p><strong>Fuente:</strong>Busco un Trabajo</p>
                <p>13-may-2015</p>
                <p>Te decimos tres razones por las que nunca te contratarán en ninguna empresa.<a href="/es_mx/empleo/no_te_contrataran_si">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>10 tips para ser contratado y conseguir un buen salario</h4>
                <p><strong>Fuente:</strong>Publimetro</p>
                <p>12-may-2015</p>
                <p>El 50 por ciento de los mexicanos no sabe vender sus capacidades profesionales y conocimientos. Aprende cómo hacerlo para conseguir ese empleo.<a href="/es_mx/empleo/10_tips_ser_contratado_conseguir_buen_salario">Leer más</a></p>
              </li>

              <li ><h4>Cómo destacar con tu perfil en Linkedin</h4>
                <p><strong>Fuente:</strong>Empresariados</p>
                <p>11-may-2015</p>
                <p>Cuando un profesional elabora su red de contactos teniendo claros sus objetivos también tiene más posibilidades de poder concretar colaboraciones de trabajo a partir de dichos contactos.<a href="/es_mx/empleo/como_destacar_perfil_linkedin">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Diseña el trabajo de tu vida antes de empezar a buscarlo</h4>
                <p><strong>Fuente:</strong>Expansión</p>
                <p>11-may-2015</p>
                <p>Es muy difícil que aciertes con la carrera que te llevará al éxito laboral. Pero puedes hacer preparativos antes de tomar una decisión.<a href="/es_mx/empleo/disena_trabajo_vida_antes_empezar_buscarlo">Leer más</a></p>
              </li>




            </ul>
          </div>

          <div class="pagArticulo" style="display: none">
            <ul class="tema">


              <li ><h4>Cómo hacer para que tu currículum pase la prueba de los ocho segundos</h4>
                <p><strong>Fuente:</strong>Universia</p>
                <p>8-may-2015</p>
                <p>Se estima que un reclutador invierte ocho segundos en una lectura rápida de los CV. Descubre cómo pasar esta prueba de fuego.<a href="/es_mx/empleo/como_hacer_tu_curriculum_pase_prueba_ocho_segundos">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>¿Estas preparado para el nuevo mundo laboral?</h4>
                <p><strong>Fuente:</strong>Dinero en Imagen</p>
                <p>8-may-2015</p>
                <p>La forma en la que hoy en día un empleado construye su marca profesional es su principal carta de presentación en los lugares de trabajo modernos.<a href="/es_mx/empleo/estas_preparado_nuevo_mundo_laboral">Leer más</a></p>
              </li>

              <li ><h4>Vístete para triunfar: 10 enemigos de una imagen profesional</h4>
                <p><strong>Fuente:</strong>OCC Mundial</p>
                <p>7-may-2015</p>
                <p>¿Tienes el talento pero no avanzas en tu profesión? ¿Cubres el perfil pero no te quedas con el puesto? Descubre por qué.<a href="/es_mx/empleo/vistete_triunfar_10_enemigos_imagen_profesional">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Cuidado con las ofertas en internet y prensa</h4>
                <p><strong>Fuente:</strong>Busco un Trabajo</p>
                <p>6-may-2015</p>
                <p>Vamos a ver cómo puedes identificar las ofertas falsas que hay en internet y en la prensa para evitar un disgusto.<a href="/es_mx/empleo/cuidado_ofertas_internet_prensa">Leer más</a></p>
              </li>

              <li ><h4>Tres cosas que debes saber sobre ti para buscar empleo</h4>
                <p><strong>Fuente:</strong>Adecco</p>
                <p>6-may-2015</p>
                <p>Tu personalidad afecta el modo en que buscas empleo. Por eso, es importante que para avanzar en tu búsqueda conozcas ciertos rasgos de ella.<a href="/es_mx/empleo/tres_cosas_debes_saber_sobre_ti_buscar_empleo">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Cinco claves para saber los pros y contras de ser Freelancer</h4>
                <p><strong>Fuente:</strong>El Financiero</p>
                <p>4-may-2015</p>
                <p>Este modelo resulta de mucho interés para los empresarios, ya que al pagar únicamente por proyecto se garantiza el resultado deseado.<a href="/es_mx/empleo/cinco_claves_saber_pros_contras_ser_freelancer">Leer más</a></p>
              </li>

              <li ><h4>El ABC de las ferias de empleo en línea</h4>
                <p><strong>Fuente:</strong>CNN Expansión</p>
                <p>30-abr-2015</p>
                <p>Dada la gran demanda en estos eventos, debes buscar diferenciarte e intentar ir más allá. Te decimos cómo.<a href="/es_mx/empleo/abc_ferias_empleo_linea">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>¿El curriculum es importante?</h4>
                <p><strong>Fuente:</strong>Busco un Trabajo</p>
                <p>30-abr-2015</p>
                <p>Con esto nos referimos a que al final lo que van a contratar es la persona que está detrás del curriculum y sólo depende de ti que esa persona les guste.<a href="/es_mx/empleo/curriculum_importante">Leer más</a></p>
              </li>

              <li ><h4>Cinco verdades que debes conocer antes de empezar tu primer trabajo</h4>
                <p><strong>Fuente:</strong>Universia</p>
                <p>29-abr-2015</p>
                <p>En esta nota te contamos algunas verdades que debes considerar antes de dar este importante paso en tu vida.<a href="/es_mx/empleo/cinco_verdades_debes_conocer_antes_empezar_primer_trabajo">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Cómo piensa un reclutador</h4>
                <p><strong>Fuente:</strong>InfoJobs</p>
                <p>29-abr-2015</p>
                <p>¿Si tuvieras el poder de leer la mente lo utilizarías en una entrevista para conseguir el empleo que buscas?<a href="/es_mx/empleo/como_piensa_reclutador">Leer más</a></p>
              </li>




            </ul>
          </div>

          <div class="pagArticulo" style="display: none">
            <ul class="tema">


              <li ><h4>El uso de la grafología en los procesos de selección</h4>
                <p><strong>Fuente:</strong>Empresariados</p>
                <p>28-abr-2015</p>
                <p>Existen ofertas en las que como requisito los candidatos deben enviar una carta de presentación manuscrita. ¿Cuál es su finalidad?<a href="/es_mx/empleo/uso_grafologia_procesos_seleccion">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>¿Cómo 'disfrazar' el desempleo en tu Currículum?</h4>
                <p><strong>Fuente:</strong>OCC Mundial</p>
                <p>28-abr-2015</p>
                <p>¿Llevas tiempo sin trabajar y tu CV tiene un 'hueco'? Sigue estos consejos y evita que un periodo de desempleo te deje sin oportunidades.<a href="/es_mx/empleo/como_disfrazar_desempleo_curriculum">Leer más</a></p>
              </li>

              <li ><h4>Encontrar trabajo después de los 40: ¿una misión imposible?</h4>
                <p><strong>Fuente:</strong>Universia</p>
                <p>27-abr-2015</p>
                <p>Los expertos indican que, pese a las dificultades, las empresas aún buscan apoyarse en su experiencia.<a href="/es_mx/empleo/encontrar_trabajo_despues_40_mision_imposible">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>El arte de ser y parecer el mejor candidato</h4>
                <p><strong>Fuente:</strong>InfoJobs</p>
                <p>24-abr-2015</p>
                <p>En este artículo te damos unos consejos genéricos para entrar con buen pie en un proceso de selección.<a href="/es_mx/empleo/arte_ser_parecer_mejor_candidato">Leer más</a></p>
              </li>

              <li ><h4>Información que debe ofrecer una oferta de trabajo</h4>
                <p><strong>Fuente:</strong>Empresariados</p>
                <p>23-abr-2015</p>
                <p>¿Qué datos debe aportar una oferta de empleo más allá del medio en el que se publique?<a href="/es_mx/empleo/informacion_debe_ofrecer_oferta_trabajo">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Cómo entrar con buen pie en un nuevo empleo</h4>
                <p><strong>Fuente:</strong>Expansión</p>
                <p>22-abr-2015</p>
                <p>Ser el nuevo requiere un proceso de adaptación que es clave para el éxito de novatos y de los más veteranos.<a href="/es_mx/empleo/como_entrar_buen_pie_nuevo_empleo">Leer más</a></p>
              </li>

              <li ><h4>¿Por qué debes actualizar estos siete datos en tu CV?</h4>
                <p><strong>Fuente:</strong>OCC Mundial</p>
                <p>21-abr-2015</p>
                <p>Hay información que en cualquier momento puede llevarte a un mejor puesto. Conócela y ponla al día.<a href="/es_mx/empleo/por_que_actualizar_siete_datos_cv">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>La entrevista de trabajo de tensión</h4>
                <p><strong>Fuente:</strong>Empresariados</p>
                <p>17-abr-2015</p>
                <p>Es una prueba utilizada para conocer la reacción del candidato y su nivel de madurez. ¿Deseas saber en qué consiste? Continúa leyendo.<a href="/es_mx/empleo/entrevista_trabajo_tension">Leer más</a></p>
              </li>

              <li ><h4>Nunca pescarás un empleo si no tienes contactos</h4>
                <p><strong>Fuente:</strong>Expansión</p>
                <p>17-abr-2015</p>
                <p>Un 'networking' que te permita acceder a las ofertas de trabajo ocultas es, según algunos expertos, la única opción para triunfar en la búsqueda de un puesto.<a href="/es_mx/empleo/nunca_pescaras_empleo_no_tienes_contactos">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Cinco cosas que nunca debes hacer en una entrevista de trabajo</h4>
                <p><strong>Fuente:</strong>InfoJobs</p>
                <p>16-abr-2015</p>
                <p>Salir de una entrevista de trabajo con una buena sensación puede ayudarte a superar un proceso de selección con éxito y conseguir el empleo que quieres.<a href="/es_mx/empleo/cinco_cosas_nunca_hacer_entrevista_trabajo">Leer más</a></p>
              </li>




            </ul>
          </div>

          <div class="pagArticulo" style="display: none">
            <ul class="tema">


              <li ><h4>Si vas por tu empleo soñado, usa el color adecuado</h4>
                <p><strong>Fuente:</strong>El Financiero</p>
                <p>16-abr-2015</p>
                <p>¿Vas a una entrevista para tu empleo ideal y no sabes qué ponerte? Conoce el significado de los colores y proyecta lo mejor de ti.<a href="/es_mx/empleo/vas_empleo_sonado_usa_color_adecuado">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>No pierdas el tiempo en tu búsqueda de empleo</h4>
                <p><strong>Fuente:</strong>Adecco Blog</p>
                <p>15-abr-2015</p>
                <p>¿Cómo saber si estás haciendo lo necesario para encontrar un nuevo trabajo o simplemente estás perdiendo el tiempo?<a href="/es_mx/empleo/no_pierdas_tiempo_busqueda_empleo">Leer más</a></p>
              </li>

              <li ><h4>Seis trucos para no perder la cabeza tras un despido</h4>
                <p><strong>Fuente:</strong>Dinero en Imagen</p>
                <p>15-abr-2015</p>
                <p>Sobrevivir en todo el sentido de la palabra es la meta por ahora, pero? ¿cómo lograrlo?<a href="/es_mx/empleo/seis_trucos_no_perder_cabeza_tras_despido">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Lucir ansioso en la entrevista puede costarte el trabajo</h4>
                <p><strong>Fuente:</strong>Bienestar 180</p>
                <p>14-abr-2015</p>
                <p>La ansiedad en la entrevista de trabajo podría hacerte perder la oportunidad de quedarte con el puesto.<a href="/es_mx/empleo/lucir_ansioso_entrevista_puede_costarte_trabajo">Leer más</a></p>
              </li>

              <li ><h4>Millennials: seis consejos para entrevistas de trabajo</h4>
                <p><strong>Fuente:</strong>CNN Expansión</p>
                <p>14-abr-2015</p>
                <p>Los reclutadores suelen tener prejuicios ante un miembro de esta generación; limpiar tus redes sociales te puede ayudar a enfrentar estos clichés.<a href="/es_mx/empleo/millennials_seis_consejos_entrevistas_trabajo">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Consejos para realizar prácticas en una empresa</h4>
                <p><strong>Fuente:</strong>InfoJobs</p>
                <p>13-abr-2015</p>
                <p>Las prácticas profesionales para los recién graduados son hoy en día un factor clave para la inserción laboral.<a href="/es_mx/empleo/consejos_realizar_practicas_empresa">Leer más</a></p>
              </li>

              <li ><h4>Qué debes saber de la empresa antes de tu entrevista</h4>
                <p><strong>Fuente:</strong>Adecco Blog</p>
                <p>13-abr-2015</p>
                <p>Compartimos contigo ideas para hacer un buen trabajo de investigación sobre la empresa antes de una entrevista.<a href="/es_mx/empleo/debes_saber_empresa_antes_entrevista">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Los cinco tips laborales que todo recién graduado debe conocer</h4>
                <p><strong>Fuente:</strong>Universia</p>
                <p>10-abr-2015</p>
                <p>Es hora de saltar al mercado laboral. ¿Aún no te sientes preparado? Conoce cinco tips para que la transición sea todo un éxito.<a href="/es_mx/empleo/cinco_tips_laborales_recien_graduado_debe_conocer">Leer más</a></p>
              </li>

              <li ><h4>¿Cómo usar las redes sociales para conseguir trabajo?</h4>
                <p><strong>Fuente:</strong>Dinero en Imagen</p>
                <p>10-abr-2015</p>
                <p>Son una nueva herramienta y un canal de comunicación entre empresas y candidatos. Aprende a sacarles provecho.<a href="/es_mx/empleo/como_usar_redes_sociales_conseguir_trabajo">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Consejos para enviar el currículum a través de correo electrónico</h4>
                <p><strong>Fuente:</strong>Empresariados</p>
                <p>9-abr-2015</p>
                <p>¿Eres freelance? Te decimos cómo enviar el currículum a través de correo electrónico de una forma eficaz.<a href="/es_mx/empleo/consejos_enviar_curriculum_correo_electronico">Leer más</a></p>
              </li>




            </ul>
          </div>

          <div class="pagArticulo" style="display: none">
            <ul class="tema">


              <li ><h4>Cuatro secretos personales que revela tu currículum</h4>
                <p><strong>Fuente:</strong>Bienestar 180</p>
                <p>9-abr-2015</p>
                <p>Aunque tu CV no sea muy extenso puede revelar todos los secretos de tu vida. ¡Descubre algunos de ellos!<a href="/es_mx/empleo/cuatro_secretos_personales_revela_curriculum">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Cuatro aptitudes fundamentales para el empleo</h4>
                <p><strong>Fuente:</strong>Adecco Blog</p>
                <p>8-abr-2015</p>
                <p>En la actualidad, además de la capacitación profesional y técnica en el área específica que nos interesa, es cada vez más importante reunir una serie de aptitudes, conócelas aquí.<a href="/es_mx/empleo/cuatro_aptitudes_fundamentales_empleo">Leer más</a></p>
              </li>

              <li ><h4>Pruebas de selección</h4>
                <p><strong>Fuente:</strong>Busco un Trabajo</p>
                <p>8-abr-2015</p>
                <p>Existen diversos tipos de estas pruebas. A continuación te presentamos los más habituales, cuyo objetivo no es otro que el de tratar de determinar si, efectivamente, somos los candidatos perfectos para el puesto.<a href="/es_mx/empleo/pruebas_seleccion">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>En qué se fijan los reclutadores cuando leen un CV</h4>
                <p><strong>Fuente:</strong>InfoJobs</p>
                <p>7-abr-2015</p>
                <p>Resultado de un estudio llevado a cabo en Estados Unidos dando seguimiento durante 10 semanas a 30 reclutadores para averiguar en qué se fijaban realmente cuando leían un CV.<a href="/es_mx/empleo/en_que_fijan_reclutadores_cuando_leen_cv">Leer más</a></p>
              </li>

              <li ><h4>Las 10 preguntas más raras en una entrevista laboral</h4>
                <p><strong>Fuente:</strong>CNN Expansión</p>
                <p>6-abr-2015</p>
                <p>'¿Qué desayunaste hoy?' es solo una de las preguntas que puedes enfrentar en una entrevista; conoce el objetivo del reclutador al hacerla.<a href="/es_mx/empleo/10_preguntas_raras_entrevista_laboral">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Palabras clave que le dan energía a tu currículum</h4>
                <p><strong>Fuente:</strong>OCC Mundial</p>
                <p>6-abr-2015</p>
                <p>Cuando buscas trabajo y envías tu currículo a las empresas, más que actividades será bueno contar evidencias: decir qué has hecho en tu vida laboral con las palabras y los datos que lo confirmen.<a href="/es_mx/empleo/palabras_clave_dan_energia_curriculum">Leer más</a></p>
              </li>

              <li ><h4>Cuando un despido se convierte en el trampolín de tu carrera</h4>
                <p><strong>Fuente:</strong>Expansión</p>
                <p>27-mar-2015</p>
                <p>¿Te han echado de tu empresa? No es el final. Podría ser una oportunidad para impulsar tu vida profesional y alcanzar el éxito.<a href="/es_mx/empleo/cuando_despido_convierte_trampolin_carrera">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Elimina la incertidumbre al buscar trabajo</h4>
                <p><strong>Fuente:</strong>Mejorar tu CV</p>
                <p>26-mar-2015</p>
                <p>Cuando peligra la estabilidad económica esta incertidumbre se sufre en mayor medida, pero aún así es posible eliminarla.<a href="/es_mx/empleo/elimina_incertidumbre_buscar_trabajo">Leer más</a></p>
              </li>

              <li ><h4>Las palabras que le restan fuerza a tu currículum</h4>
                <p><strong>Fuente:</strong>CNN Expansión</p>
                <p>25-mar-2015</p>
                <p>Los candidatos deben eliminar frases repetidas y lugares comunes, recomiendan expertos.<a href="/es_mx/empleo/palabras_restan_fuerza_curriculum">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>No consigo pasar la entrevista de selección</h4>
                <p><strong>Fuente:</strong>Zumo de Empleo</p>
                <p>24-mar-2015</p>
                <p>Si después de hacer una entrevista no eres seleccionado, te voy a contar lo que ocurre 'detrás del escenario'.<a href="/es_mx/empleo/no_consigo_pasar_entrevista_seleccion">Leer más</a></p>
              </li>




            </ul>
          </div>

          <div class="pagArticulo" style="display: none">
            <ul class="tema">


              <li ><h4>Cómo responder a la pregunta "háblame de ti" en una entrevista de trabajo</h4>
                <p><strong>Fuente:</strong>InfoJobs</p>
                <p>23-mar-2015</p>
                <p>El objetivo del seleccionador con esta pregunta es ver si eres un candidato seguro de ti mismo.<a href="/es_mx/empleo/como_responder_pregunta_hablame_ti_entrevista_trabajo">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>¿Desempleado?, tu discurso es el culpable</h4>
                <p><strong>Fuente:</strong>Bienestar 180</p>
                <p>23-mar-2015</p>
                <p>La forma en cómo dices las cosas o imitar el comportamiento de quien te entrevista son los culpables de que aún  no encuentres el trabajo que tanto anhelas.<a href="/es_mx/empleo/desempleado_discurso_culpable">Leer más</a></p>
              </li>

              <li ><h4>Así se capta la atención de los cazatalentos</h4>
                <p><strong>Fuente:</strong>Expansión</p>
                <p>20-mar-2015</p>
                <p>La trayectoria profesional y la disponibilidad del profesional para formar parte del talento que integra la base de datos de los 'headhunters' son clave.<a href="/es_mx/empleo/capta_atencion_cazatalentos">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>¿Cómo realizáis la autocandidatura?</h4>
                <p><strong>Fuente:</strong>Blog del Inaem</p>
                <p>19-mar-2015</p>
                <p>¿Cuál es la forma adecuada de hacer la autocandidatura? ¿Qué resultados se pueden obtener? Aquí te lo platicamos.<a href="/es_mx/empleo/como_realizais_autocandidatura">Leer más</a></p>
              </li>

              <li ><h4>Motivos por los que usar las redes sociales para buscar empleo</h4>
                <p><strong>Fuente:</strong>Adecco Blog</p>
                <p>18-mar-2015</p>
                <p>Si aún no estás convencido de las posibilidades que ofrecen, hoy te explicamos seis motivos que seguro te convencen.<a href="/es_mx/empleo/motivos_usar_redes_sociales_buscar_empleo">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>¿Buscar trabajo? Tres pautas básicas</h4>
                <p><strong>Fuente:</strong>Busco un Trabajo</p>
                <p>17-mar-2015</p>
                <p>Si te acabas de quedar en paro o si llevas ya tiempo y no hay manera de conseguir ver la luz por ningún lado, sigue estas recomendaciones.<a href="/es_mx/empleo/buscar_trabajo_tres_pautas_basicas">Leer más</a></p>
              </li>

              <li ><h4>Cuatro tipos de preguntas en la entrevista de trabajo</h4>
                <p><strong>Fuente:</strong>Empresariados</p>
                <p>13-mar-2015</p>
                <p>Existen cuatro tipos de preguntas que  permiten al seleccionador obtener un mayor conocimiento sobre el candidato. ¿Cuáles son?<a href="/es_mx/empleo/cuatro_tipos_preguntas_entrevista_trabajo">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Piensa siempre como si estuvieras buscando empleo</h4>
                <p><strong>Fuente:</strong>InfoJobs</p>
                <p>12-mar-2015</p>
                <p>Artículo donde te decimos cómo buscar trabajo con redes sociales (y sin ellas).<a href="/es_mx/empleo/piensa_siempre_como_si_estuvieras_buscando_empleo">Leer más</a></p>
              </li>

              <li ><h4>¿Qué dice tu forma de escribir sobre tu perfil profesional?</h4>
                <p><strong>Fuente:</strong>Universia</p>
                <p>11-mar-2015</p>
                <p>Por poco relevante que parezca, un reclutador de personal puede sacar de tu forma de escribir más información sobre tu perfil profesional de la que imaginas. ¿Listo para saber que dice tu letra de ti?<a href="/es_mx/empleo/que_dice_forma_escribir_sobre_perfil_profesional">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>El CV como embajador en la búsqueda de empleo</h4>
                <p><strong>Fuente:</strong>InfoJobs</p>
                <p>10-mar-2015</p>
                <p>El CV es tu representante en el país del empleo. Siguiendo tus órdenes viajará a todos los destinos que le indiques presentándote.<a href="/es_mx/empleo/cv_embajador_busqueda_empleo">Leer más</a></p>
              </li>




            </ul>
          </div>

          <div class="pagArticulo" style="display: none">
            <ul class="tema">


              <li ><h4>Genera un mayor impacto en tu entrevista</h4>
                <p><strong>Fuente:</strong>Be Executive</p>
                <p>9-mar-2015</p>
                <p>Lo que más interesa en el momento de la entrevista es tu actitud, la cual podemos resumir en personalidad, profesionalismo y liderazgo.<a href="/es_mx/empleo/genera_mayor_impacto_entrevista">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Organiza tu búsqueda de trabajo</h4>
                <p><strong>Fuente:</strong>Adecco Blog</p>
                <p>9-mar-2015</p>
                <p>La estrategia para buscar trabajo debe ser como un plan de marketing en el que el producto eres tú mismo. Lo importante será tener claro dónde quieres ir.<a href="/es_mx/empleo/organiza_busqueda_trabajo">Leer más</a></p>
              </li>

              <li ><h4>Cómo afrontar una entrevista por videoconferencia</h4>
                <p><strong>Fuente:</strong>InfoJobs</p>
                <p>6-mar-2015</p>
                <p>Es importante preparar este tipo de entrevistas como si fuera presencial, a pesar de la distancia, para evitar ser descartado.<a href="/es_mx/empleo/afrontar_entrevista_videoconferencia">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Cómo realizar una práctica profesional exitosa</h4>
                <p><strong>Fuente:</strong>Universia</p>
                <p>6-mar-2015</p>
                <p>En esta nota te contamos cómo sacarles el mayor rédito posible a estas instancias de aprendizaje para estudiantes y recién graduados.<a href="/es_mx/empleo/realizar_practica_profesional_exitosa">Leer más</a></p>
              </li>

              <li ><h4>Claves para encontrar trabajo con  más de 45 años</h4>
                <p><strong>Fuente:</strong>Mejorar tu CV</p>
                <p>4-mar-2015</p>
                <p>No es imposible encontrar trabajo con esa edad. Aprovecha tu experiencia y conocimientos.<a href="/es_mx/empleo/claves_encontrar_trabajo_mas_45_anos">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Cuatro pasos para crear tu identidad en Internet y hallar empleo</h4>
                <p><strong>Fuente:</strong>CNN Expansión</p>
                <p>4-mar-2015</p>
                <p>Usar correctamente la red puede aumentar 10 por ciento la posibilidad de encontrar un trabajo.<a href="/es_mx/empleo/cuatro_pasos_crear_identidad_internet_hallar_empleo">Leer más</a></p>
              </li>

              <li ><h4>Si eres un crack del networking, ¿por qué no encuentras trabajo?</h4>
                <p><strong>Fuente:</strong>Expansión</p>
                <p>3-mar-2015</p>
                <p>La cantidad en tu red de contactos no sirve de nada si tus relaciones profesionales son de baja calidad.<a href="/es_mx/empleo/si_eres_crack_networking_no_encuentras_trabajo">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Cómo superar las dificultades en las entrevistas laborales</h4>
                <p><strong>Fuente:</strong>Laborum</p>
                <p>2-mar-2015</p>
                <p>¿Qué salario ofrecen? o ¿qué beneficios brinda la empresa? Son algunas de las dudas que surgen en una entrevista y muchas veces no sabemos cómo plantearlas.<a href="/es_mx/empleo/como_superar_dificultades_entrevistas_laborales">Leer más</a></p>
              </li>

              <li ><h4>Tres errores a evitar en un curriculum</h4>
                <p><strong>Fuente:</strong>Busco un Trabajo</p>
                <p>2-mar-2015</p>
                <p>Dado que el CV es una de las primeras cosas que verá tu posible empleador, hay errores que debes evitar para así poder pasar el filtro inicial.<a href="/es_mx/empleo/tres_errores_evitar_curriculum">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>No hagas suposiciones</h4>
                <p><strong>Fuente:</strong>Mejorar tu CV</p>
                <p>23-feb-2015</p>
                <p>Una de las cosas que más perjudica sin duda a un candidato son las suposiciones que hace a lo largo de un proceso de selección.<a href="/es_mx/empleo/no_hagas_suposiciones">Leer más</a></p>
              </li>




            </ul>
          </div>

          <div class="pagArticulo" style="display: none">
            <ul class="tema">


              <li ><h4>Que una empresa "transa" no dañe tu CV</h4>
                <p><strong>Fuente:</strong>Expansión</p>
                <p>20-feb-2015</p>
                <p>La reputación de tus empleadores influye en la percepción que otros tienen de ti, dicen expertos.<a href="/es_mx/empleo/empresa_transa_no_dane_cv">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>¿Es bueno trabajar mientras se estudia en la universidad?</h4>
                <p><strong>Fuente:</strong>Universia</p>
                <p>19-feb-2015</p>
                <p>Trabajar mientras estudias es una buena decisión, pero no es nada fácil por lo que seguro unos consejos para combinar ambas actividades no te vendrán nada mal.<a href="/es_mx/empleo/es_bueno_trabajar_mientras_estudia_universidad">Leer más</a></p>
              </li>

              <li ><h4>Cuatro errores inconscientes que pueden descartarte en una entrevista</h4>
                <p><strong>Fuente:</strong>InfoJobs</p>
                <p>16-feb-2015</p>
                <p>En una entrevista no sólo cuenta lo que explicas al reclutador.<a href="/es_mx/empleo/cuatro_errores_inconscientes_pueden_descartarte_entrevista">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>¿Eres el candidato adecuado?</h4>
                <p><strong>Fuente:</strong>Busco un Trabajo</p>
                <p>13-feb-2015</p>
                <p>¿Quieres saber qué prioriza un reclutador al momento de elegir a un candidato? Aquí te lo decimos.<a href="/es_mx/empleo/eres_candidato_adecuado">Leer más</a></p>
              </li>

              <li ><h4>Cuánto puedes ganar con los perfiles mejor valorados</h4>
                <p><strong>Fuente:</strong>Expansión</p>
                <p>12-feb-2015</p>
                <p>Aquí están los perfiles más cotizados y la mejor forma de acceder a ellos.<a href="/es_mx/empleo/cuanto_puedes_ganar_perfiles_mejor_valorados">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Cuando la esperada llamada por fin llega</h4>
                <p><strong>Fuente:</strong>InfoJobs</p>
                <p>11-feb-2015</p>
                <p>Consejos sobre cómo actuar ante el primer contacto con el seleccionador después de que le enviaste tu currículum.<a href="/es_mx/empleo/cuando_esperada_llamada_llega">Leer más</a></p>
              </li>

              <li ><h4>Siete de cada 10 que buscan empleo no saben negociar su salario</h4>
                <p><strong>Fuente:</strong>Dinero en Imagen</p>
                <p>9-feb-2015</p>
                <p>Para tener idea de cuánto se debe ganar por los servicios otorgados hay que recordar que el trabajo, como cualquier producto, se comercializa y tiene un precio de mercado.<a href="/es_mx/empleo/siete_cada_10_buscan_empleo_no_saben_negociar_salario">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Consejos para mayores de 45 años que buscan trabajo</h4>
                <p><strong>Fuente:</strong>InfoJobs</p>
                <p>6-feb-2015</p>
                <p>Convéncete de que tu amplia experiencia puede jugar a tu favor y aprovéchala para adaptar tu CV a las ofertas. No ocultes tu edad y siéntete orgulloso de tu bagaje.<a href="/es_mx/empleo/consejos_mayores_45_anos_buscan_trabajo">Leer más</a></p>
              </li>

              <li ><h4>¿Cómo debes vestirte para una entrevista de trabajo?</h4>
                <p><strong>Fuente:</strong>Busco un Trabajo</p>
                <p>6-feb-2015</p>
                <p>Se puede ser un gran profesional, pero si no vas adecuadamente vestido, darás una mala primera impresión que influirá en el desarrollo de la entrevista.<a href="/es_mx/empleo/como_vestirte_entrevista_trabajo">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Cinco dudas típicas del buscador de empleo</h4>
                <p><strong>Fuente:</strong>Mejorar tu CV</p>
                <p>3-feb-2015</p>
                <p>Serie de preguntas que ha recibido la autora en los últimos meses y que espera te ayuden a despejar tus propias dudas.<a href="/es_mx/empleo/cinco_dudas_tipicas_buscador_empleo">Leer más</a></p>
              </li>




            </ul>
          </div>

          <div class="pagArticulo" style="display: none">
            <ul class="tema">


              <li ><h4>¿Qué encuentran los reclutadores al investigarte?</h4>
                <p><strong>Fuente:</strong>CNN Expansión</p>
                <p>3-feb-2015</p>
                <p>Las empresas pueden contratar a firmas de investigación para revisar tus antecedentes y solicitar datos personales, penales,legales y más.<a href="/es_mx/empleo/que_encuentran_reclutadores_investigarte">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Cómo rechazar una oferta de empleo</h4>
                <p><strong>Fuente:</strong>Adecco Blog</p>
                <p>30-ene-2015</p>
                <p>Es importante saber cómo hacerlo, ya que seguramente, generaremos un problema a la empresa, que ya contaba con nosotros.<a href="/es_mx/empleo/como_rechazar_oferta_empleo">Leer más</a></p>
              </li>

              <li ><h4>Así cambian las redes sociales la manera de contratar</h4>
                <p><strong>Fuente:</strong>Expansión</p>
                <p>29-ene-2015</p>
                <p>Saber gestionar adecuadamente nuestros perfiles sociales es ya una necesidad para aquellos que buscan un cambio profesional o rastrean un puesto de trabajo.<a href="/es_mx/empleo/asi_cambian_redes_sociales_manera_contratar">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Descubre los secretos para brillar en una entrevista laboral</h4>
                <p><strong>Fuente:</strong>Universia</p>
                <p>29-ene-2015</p>
                <p>La respuesta que des a muchas de las preguntas del reclutador, una vez que captes su atención, será determinante para obtener el empleo.<a href="/es_mx/empleo/descubre_secretos_brillar_entrevista_laboral">Leer más</a></p>
              </li>

              <li ><h4>Tres pasos a seguir si no consigues ese empleo</h4>
                <p><strong>Fuente:</strong>Adecco Blog</p>
                <p>28-ene-2015</p>
                <p>Sigue estos tres sencillos pasos y transforma una negativa en nuevas oportunidades.<a href="/es_mx/empleo/tres_pasos_seguir_no_consigues_empleo">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Habilidades digitales, requisito para encontrar empleo</h4>
                <p><strong>Fuente:</strong>Generadores de Mejoras Contínuas en Recursos Humanos</p>
                <p>22-ene-2015</p>
                <p>Análisis de las principales tendencias del mercado laboral global realizado por Hays Journal.<a href="/es_mx/empleo/habilidades_digitales_requisito_encontrar_empleo">Leer más</a></p>
              </li>

              <li ><h4>¿Te despidieron? Guía rápida para enfrentarlo</h4>
                <p><strong>Fuente:</strong>CNN Expansión</p>
                <p>20-ene-2015</p>
                <p>El primer paso no es buscar otro empleo; elaborar el proceso de duelo es lo más difícil, 80 por ciento se estanca en el enojo.<a href="/es_mx/empleo/te_despidieron_guia_rapida_enfrentarlo">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>¿Se pueden predecir las cualidades del candidato ideal?</h4>
                <p><strong>Fuente:</strong>Expansión</p>
                <p>19-ene-2015</p>
                <p>Las tradicionales entrevistas de trabajo están dando paso a pruebas de carácter práctico, más difíciles de preparar. Conócelas.<a href="/es_mx/empleo/pueden_predecir_cualidades_candidato_ideal">Leer más</a></p>
              </li>

              <li ><h4>Mentir en un currículum</h4>
                <p><strong>Fuente:</strong>InfoJobs</p>
                <p>16-ene-2015</p>
                <p>El CV nos abre las puertas a la entrevista de trabajo que, de ser exitosa, se convierte en la antesala de un nuevo empleo.<a href="/es_mx/empleo/mentir_curriculum">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>La mejor manera de buscar trabajo</h4>
                <p><strong>Fuente:</strong>Lucas5</p>
                <p>14-ene-2015</p>
                <p>¿Quieres obtener resultados efectivos en tu búsqueda laboral? Sigue estos pasos.<a href="/es_mx/empleo/mejor_manera_buscar_trabajo">Leer más</a></p>
              </li>




            </ul>
          </div>

          <div class="pagArticulo" style="display: none">
            <ul class="tema">


              <li ><h4>Seis tips para encontrar trabajo por Internet</h4>
                <p><strong>Fuente:</strong>Laborum</p>
                <p>12-ene-2015</p>
                <p>Si te encuentras buscando trabajo aquí te dejamos algunos tips para encontrarlo a través de Internet.<a href="/es_mx/empleo/seis_tips_encontrar_trabajo_internet">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Consejos para preparar con éxito una entrevista de trabajo</h4>
                <p><strong>Fuente:</strong>Empresariados</p>
                <p>2-ene-2015</p>
                <p>Al preparar una entrevista de trabajo, ganas seguridad en ti mismo, fortaleza y motivación.<a href="/es_mx/empleo/consejos_preparar_exito_entrevista_trabajo">Leer más</a></p>
              </li>

              <li ><h4>Es hora de hacer balance</h4>
                <p><strong>Fuente:</strong>Busco un Trabajo</p>
                <p>31-dic-2014</p>
                <p>El momento de la evaluación es clave en cualquier proyecto, y también lo es a la hora de buscar trabajo, de esta manera sabremos qué aspectos mejorar, qué nuevas ideas incorporar o eliminar aquello que no nos funciona.<a href="/es_mx/empleo/hora_hacer_balance">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Qué no hacer si buscas trabajo</h4>
                <p><strong>Fuente:</strong>Busco un Trabajo</p>
                <p>31-dic-2014</p>
                <p>Hay actitudes que nunca debes permitir que afloren en ti cuando estás en búsqueda de trabajo, sobre todo cuando interactúas con otras personas, te las compartimos.<a href="/es_mx/empleo/que_no_hacer_buscas_trabajo">Leer más</a></p>
              </li>

              <li ><h4>Cómo crear un perfil de LinkedIn que te consiga empleo</h4>
                <p><strong>Fuente:</strong>CNN Expansión</p>
                <p>30-dic-2014</p>
                <p>El año nuevo se acerca y con este la oportunidad de obtener un mejor empleo;  la red social de profesionistas compartió cinco recomendaciones para mejorar tu perfil.<a href="/es_mx/empleo/como_crear_perfil_linkedin_consiga_empleo">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Cinco habilidades que dan un mayor sueldo</h4>
                <p><strong>Fuente:</strong>Generadores de Mejoras Contínuas en Recursos Humanos</p>
                <p>29-dic-2014</p>
                <p>Conoce cuáles son las cinco competencias por las que las empresas están dispuestas a pagar mayores sueldos.<a href="/es_mx/empleo/cinco_habilidades_dan_mayor_sueldo">Leer más</a></p>
              </li>

              <li ><h4>Los idiomas, un punto clave para encontrar trabajo</h4>
                <p><strong>Fuente:</strong>Adecco</p>
                <p>25-dic-2014</p>
                <p>Conocer idiomas y dominarlos (cuantos más, mejor) mejora la empleabilidad de los candidatos a un puesto de trabajo.<a href="/es_mx/empleo/idiomas_punto_clave_encontrar_trabajo">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>¿Qué datos personales hay que poner en un CV?</h4>
                <p><strong>Fuente:</strong>InfoJobs</p>
                <p>24-dic-2014</p>
                <p>Recomendaciones sobre qué información personal es importante poner (y cuál no) y de paso evitar que el currículum sea demasiado extenso.<a href="/es_mx/empleo/datos_personales_poner_cv">Leer más</a></p>
              </li>

              <li ><h4>¿Qué especializaciones serán las más buscadas por las empresas en el 2015?</h4>
                <p><strong>Fuente:</strong>Universia</p>
                <p>23-dic-2014</p>
                <p>En un mercado cada vez más competitivo, para tener acceso a ciertas vacantes de trabajo se vuelve requisito disponer de una serie de competencias, te las compartimos.<a href="/es_mx/empleo/especializaciones_mas_buscadas_empresas_2015">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Cuatro cosas que debes hacer si has perdido tu trabajo</h4>
                <p><strong>Fuente:</strong>Adecco</p>
                <p>22-dic-2014</p>
                <p>Es normal sentirse sin rumbo al perder tu empleo, pero no te preocupes te damos cuatro consejos para que conviertas ese bache en una oportunidad.<a href="/es_mx/empleo/cuatro_cosas_debes_hacer_has_perdido_trabajo">Leer más</a></p>
              </li>




            </ul>
          </div>

          <div class="pagArticulo" style="display: none">
            <ul class="tema">


              <li ><h4>Baby boomers + baby boosters + millennials = mejores equipos de trabajo</h4>
                <p><strong>Fuente:</strong>Universia</p>
                <p>19-dic-2014</p>
                <p>Conoce las cualidades de cada generación y forma el mejor equipo de trabajo.<a href="/es_mx/empleo/baby_boomers_baby_boosters_millennials_mejores_equipos_trabajo">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Gestionar el fracaso en la búsqueda de empleo</h4>
                <p><strong>Fuente:</strong>InfoJobs</p>
                <p>18-dic-2014</p>
                <p>En este artículo te damos algunos consejos para afrontar los malos momentos y aprender a gestionar el fracaso en la búsqueda de empleo.<a href="/es_mx/empleo/gestionar_fracaso_busqueda_empleo">Leer más</a></p>
              </li>

              <li ><h4>Cómo explicar un agujero en tu currículum</h4>
                <p><strong>Fuente:</strong>CNN Expansión</p>
                <p>17-dic-2014</p>
                <p>Sigue estos consejos para explicar tu ausencia a los reclutadores sin entrar en detalles.<a href="/es_mx/empleo/como_explicar_agujero_curriculum">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>¿Por qué no consigues trabajo? Por estos cuatro motivos</h4>
                <p><strong>Fuente:</strong>Caza tu Trabajo</p>
                <p>16-dic-2014</p>
                <p>Replantéate la pregunta para que encuentres la respuesta que estas buscando y mejore tu búsqueda de empleo.<a href="/es_mx/empleo/no_consigues_trabajo_cuatro_motivos">Leer más</a></p>
              </li>

              <li ><h4>Cuatro consejos para cuidar tu imagen profesional en las redes sociales</h4>
                <p><strong>Fuente:</strong>Universia</p>
                <p>15-dic-2014</p>
                <p>Son una gran herramienta para hallar trabajo, pero también son ideales para ofrecer información a los reclutadores acerca de ti.<a href="/es_mx/empleo/cuatro_consejos_cuidar_imagen_profesional_redes_sociales">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Cinco reglas del "Yes or No" en una entrevista de trabajo</h4>
                <p><strong>Fuente:</strong>CNN Expansión</p>
                <p>12-dic-2014</p>
                <p>Conoce las recomendaciones que un experto da para perder el miedo y fluir en una entrevista de trabajo en inglés.<a href="/es_mx/empleo/cinco_reglas_yes_no_entrevista_trabajo">Leer más</a></p>
              </li>

              <li ><h4>¿Tienes en cuenta estos siete aspectos sobre la búsqueda de empleo?</h4>
                <p><strong>Fuente:</strong>Mejorar tu CV</p>
                <p>12-dic-2014</p>
                <p>Se ha hablado mucho sobre la búsqueda de empleo, pero quizá no hayas tenido en cuenta estos aspectos.<a href="/es_mx/empleo/siete_aspectos_sobre_busqueda_empleo">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Proceso de selección de personal en pequeñas empresas</h4>
                <p><strong>Fuente:</strong>Empresariados</p>
                <p>11-dic-2014</p>
                <p>Te platicamos los errores que debes evitar para un mejor proceso de reclutamiento en tu empresa.<a href="/es_mx/empleo/proceso_seleccion_personal_pequenas_empresas">Leer más</a></p>
              </li>

              <li ><h4>Diciembre es buen mes para buscar empleo</h4>
                <p><strong>Fuente:</strong>El Financiero</p>
                <p>10-dic-2014</p>
                <p>Contrario a lo que se cree, la temporada decembrina representa una oportunidad de emplearse no sólo de manera temporal.<a href="/es_mx/empleo/diciembre_buen_mes_buscar_empleo">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>¿Experto en "zumba"? Tienes  un arma para encontrar trabajo</h4>
                <p><strong>Fuente:</strong>Expansión</p>
                <p>9-dic-2014</p>
                <p>¿Sabes hacer algo mejor que nadie pero es extraño o un poco 'friki'?. Esa 'distinción profesional' puede ser una valiosa arma de selección.<a href="/es_mx/empleo/experto_zumba_arma_encontrar_trabajo">Leer más</a></p>
              </li>




            </ul>
          </div>

          <div class="pagArticulo" style="display: none">
            <ul class="tema">


              <li ><h4>Cómo ser un candidato pasivo atractivo para los headhunters</h4>
                <p><strong>Fuente:</strong>Mejorar tu CV</p>
                <p>8-dic-2014</p>
                <p>A pesar de no estar buscando empleo de forma activa se puede ser un candidato atractivo para los headhunters. Te decimos cómo conseguirlo.<a href="/es_mx/empleo/como_ser_candidato_pasivo_atractivo_headhunters">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Los cinco mayores errores de los reclutadores</h4>
                <p><strong>Fuente:</strong>huntRED</p>
                <p>5-dic-2014</p>
                <p>Expertos en selección nos comparten las peores prácticas que han visto.<a href="/es_mx/empleo/cinco_mayores_errores_reclutadores">Leer más</a></p>
              </li>

              <li ><h4>Cinco tendencias actuales en la búsqueda de empleo</h4>
                <p><strong>Fuente:</strong>Adecco Blog</p>
                <p>3-dic-2014</p>
                <p>Te compartimos tendencias que no puedes ignorar si realmente quieres conseguir ese nuevo empleo.<a href="/es_mx/empleo/cinco_tendencias_actuales_busqueda_empleo">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>La reputación online: ¿leyenda urbana o realidad en los procesos de selección?</h4>
                <p><strong>Fuente:</strong>InfoJobs</p>
                <p>2-dic-2014</p>
                <p>No es leyenda urbana que los seleccionadores busquemos a los candidatos en la red durante un proceso de selección, incluso antes de llamarlos.<a href="/es_mx/empleo/reputacion_online_leyenda_urbana_realidad_procesos_seleccion">Leer más</a></p>
              </li>

              <li ><h4>Tres desafíos para cubrir vacantes especializadas</h4>
                <p><strong>Fuente:</strong>CNN Expansión</p>
                <p>28-nov-2014</p>
                <p>En México persiste la dificultad para reclutar perfiles para ciertos puestos, le recomendamos algunas medidas para revertir la tendencia.<a href="/es_mx/empleo/tres_desafios_cubrir_vacantes_especializadas">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>¿Los seleccionadores llaman a otros que son "peores" que tú?</h4>
                <p><strong>Fuente:</strong>Mejorar tu CV</p>
                <p>27-nov-2014</p>
                <p>¿Qué hacen otros desempleados que consideras 'peores' que tú para que los seleccionen?<a href="/es_mx/empleo/seleccionadores_llaman_otros_peores_que_tu">Leer más</a></p>
              </li>

              <li ><h4>Consejos para conseguir trabajo freelance</h4>
                <p><strong>Fuente:</strong>Empresariados</p>
                <p>26-nov-2014</p>
                <p>Para conseguir trabajo como freelance, en primer lugar, tienes que valorar el punto en el que estás dependiendo si eres joven o todo un veterano en el ramo.<a href="/es_mx/empleo/consejos_conseguir_trabajo_freelance">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Entrevista de trabajo: cómo hablar de tus defectos</h4>
                <p><strong>Fuente:</strong>InfoJobs</p>
                <p>25-nov-2014</p>
                <p>¿Cómo hablar de nuestras debilidades o defectos sin que nos perjudique en una entrevista?<a href="/es_mx/empleo/entrevista_trabajo_como_hablar_defectos">Leer más</a></p>
              </li>

              <li ><h4>¿Qué hace que un reclutador rechace tu CV en segundos?</h4>
                <p><strong>Fuente:</strong>CNN Expansión</p>
                <p>24-nov-2014</p>
                <p>Cualquiera de estos siete errores pueden hacer que el reclutador te deseche como un candidato ideal.<a href="/es_mx/empleo/que_hace_que_reclutador_rechace_cv_segundos">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>¿Sin experiencia y sin empleo? Supera la crisis del recién egresado</h4>
                <p><strong>Fuente:</strong>OCC Educación</p>
                <p>21-nov-2014</p>
                <p>¿Sin experiencia porque no te dan empleo? ¿No te dan empleo porque no tienes experiencia? Te decimos cómo romper este círculo vicioso.<a href="/es_mx/empleo/sin_experiencia_empleo_supera_crisis_recien_egresado">Leer más</a></p>
              </li>




            </ul>
          </div>

          <div class="pagArticulo" style="display: none">
            <ul class="tema">


              <li ><h4>¿Tienes alguna duda al finalizar la entrevista?</h4>
                <p><strong>Fuente:</strong>Busco un Trabajo</p>
                <p>19-nov-2014</p>
                <p>Te decimos las tres cosas que debes tener claras nada más finalizar la entrevista de trabajo.<a href="/es_mx/empleo/tienes_alguna_duda_finalizar_entrevista">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Cómo encontrar trabajo si las ofertas de empleo son invisibles</h4>
                <p><strong>Fuente:</strong>Expansión</p>
                <p>18-nov-2014</p>
                <p>Si el 80 por ciento de las ofertas están ocultas y dependen de tus contactos, ¿qué métodos funcionan realmente en tu búsqueda?<a href="/es_mx/empleo/como_encontrar_trabajo_ofertas_empleo_invisibles">Leer más</a></p>
              </li>

              <li ><h4>Atrae colaboradores comprometidos a tu empresa ¡Te decimos cómo!</h4>
                <p><strong>Fuente:</strong>Generadores de Mejoras Contínuas en Recursos Humanos</p>
                <p>14-nov-2014</p>
                <p>Artículo dirigido a empresas en el que encontrarán sugerencias para contratar colaboradores comprometidos.<a href="/es_mx/empleo/atrae_colaboradores_comprometidos_empresa_decimos_como">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Movilidad laboral ¿qué debes saber?</h4>
                <p><strong>Fuente:</strong>El Financiero</p>
                <p>13-nov-2014</p>
                <p>Analiza variables como el costo y la calidad de vida en el país destino antes de elegir una nueva oportunidad laboral en el extranjero.<a href="/es_mx/empleo/movilidad_laboral_debes_saber">Leer más</a></p>
              </li>

              <li ><h4>Tres tipos de preguntas en la entrevista de trabajo</h4>
                <p><strong>Fuente:</strong>Empresariados</p>
                <p>12-nov-2014</p>
                <p>Las distintas preguntas de una entrevista de trabajo podrían clasificarse en tres grupos fundamentales. ¿Cuáles son?<a href="/es_mx/empleo/tres_tipos_preguntas_entrevista_trabajo">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Ocho competencias laborales que piden las empresas para contratarte</h4>
                <p><strong>Fuente:</strong>OCC Educación</p>
                <p>11-nov-2014</p>
                <p>Muchos candidatos se postulan a una vacante considerándose capacitados, pero en el proceso no se quedan con el empleo. ¿Por qué sucede?<a href="/es_mx/empleo/ocho_competencias_laborales_piden_empresas_contratarte">Leer más</a></p>
              </li>

              <li ><h4>¿Conoces la intención positiva de estas cinco preguntas de entrevista de trabajo?</h4>
                <p><strong>Fuente:</strong>Mejorar tu CV</p>
                <p>10-nov-2014</p>
                <p>Por muy extraña que parezca una pregunta siempre hay una intención positiva detrás. Descúbrela.<a href="/es_mx/empleo/conoces_intencion_positiva_estas_cinco_preguntas_entrevista_trabajo">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Nunca es demasiado tarde para dar un giro total a tu carrera</h4>
                <p><strong>Fuente:</strong>Expansión</p>
                <p>6-nov-2014</p>
                <p>Aunque en principio puede resultar una experiencia desagradable, un despido puede ser un incentivo para el cambio a cualquier edad.<a href="/es_mx/empleo/nunca_demasiado_tarde_giro_total_carrera">Leer más</a></p>
              </li>

              <li ><h4>Habilidades que pueden hacer crecer tu salario</h4>
                <p><strong>Fuente:</strong>El Financiero</p>
                <p>5-nov-2014</p>
                <p>Capacitarse en temas como programación aplicada, big data o movilidad permiten tener sueldos hasta tres veces superiores al promedio.<a href="/es_mx/empleo/habilidades_pueden_hacer_crecer_salario">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Ocho horas para encontrar tu próximo puesto de trabajo</h4>
                <p><strong>Fuente:</strong>Expansión</p>
                <p>4-nov-2014</p>
                <p>Buscar trabajo es un trabajo que requiere dedicación, constancia, motivación y una buena organización. Te proponemos una agenda para llevarlo a cabo con éxito.<a href="/es_mx/empleo/ocho_horas_encontrar_proximo_puesto_trabajo">Leer más</a></p>
              </li>




            </ul>
          </div>

          <div class="pagArticulo" style="display: none">
            <ul class="tema">


              <li ><h4>Preguntas disparatadas en entrevistas de trabajo</h4>
                <p><strong>Fuente:</strong>El Empresario</p>
                <p>3-nov-2014</p>
                <p>Aunque parezca mentira, en tu próxima entrevista de trabajo podrían sorprenderte con preguntas curiosas en las que pondrán a prueba tu ingenio o paciencia.<a href="/es_mx/empleo/preguntas_disparatadas_entrevistas_trabajo">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>¿Qué preguntas debes hacer en una entrevista de trabajo?</h4>
                <p><strong>Fuente:</strong>Expansión</p>
                <p>3-nov-2014</p>
                <p>Conseguir datos como cuánto ganarás y qué horario tendrás, además del puesto, depende de cómo lo plantees.<a href="/es_mx/empleo/que_preguntas_debes_hacer_entrevista_trabajo">Leer más</a></p>
              </li>

              <li ><h4>Fórmula para conseguir empleo después de los 40</h4>
                <p><strong>Fuente:</strong>Publimetro</p>
                <p>31-oct-2014</p>
                <p>Sin importar la edad, es necesario estar atentos a las tendencias del mercado laboral para no ser descartado.<a href="/es_mx/empleo/formula_conseguir_empleo_despues_40">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>25 preguntas clave en una entrevista laboral</h4>
                <p><strong>Fuente:</strong>OCC Educación</p>
                <p>30-oct-2014</p>
                <p>Ya conseguiste la entrevista. Se abre una oportunidad de quedarte con el empleo y la primera pregunta es: ¿estás preparado?<a href="/es_mx/empleo/25_preguntas_clave_entrevista_laboral">Leer más</a></p>
              </li>

              <li ><h4>70 por ciento de quienes buscan trabajo en México lo hacen en Internet</h4>
                <p><strong>Fuente:</strong>CNN Expansión</p>
                <p>30-oct-2014</p>
                <p>Según un estudio de la AMIPCI; los aspirantes entre 18 y 34 años son los más activos en la búsqueda de vacantes por Internet.<a href="/es_mx/empleo/70_por_ciento_quienes_buscan_trabajo_mexico_hacen_internet">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Cinco claves para salir adelante tras un rechazo laboral</h4>
                <p><strong>Fuente:</strong>CNN Expansión</p>
                <p>29-oct-2014</p>
                <p>El primer paso al recibir la negativa de un empleo es no tomarlo de forma personal, dicen expertos; te decimos cuáles son sus posibles causas.<a href="/es_mx/empleo/cinco_claves_salir_adelante_tras_rechazo_laboral">Leer más</a></p>
              </li>

              <li ><h4>Prepárate para las nuevas entrevistas de trabajo</h4>
                <p><strong>Fuente:</strong>Expansión</p>
                <p>28-oct-2014</p>
                <p>Actualmente, las entrevistas buscan corroborar que los candidatos cuentan con las habilidades y las competencias que busca la compañía.<a href="/es_mx/empleo/preparate_nuevas_entrevistas_trabajo">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Trabajar durante la universidad es la clave para conseguir empleo</h4>
                <p><strong>Fuente:</strong>Universia</p>
                <p>28-oct-2014</p>
                <p>Trabajar mientras se estudia puede resultar agotador, pero ofrece un mayor número de oportunidades de conseguir empleo una vez egresado.<a href="/es_mx/empleo/trabajar_durante_universidad_clave_conseguir_empleo">Leer más</a></p>
              </li>

              <li ><h4>Cómo hacer un currículum</h4>
                <p><strong>Fuente:</strong>OCC Educación</p>
                <p>27-oct-2014</p>
                <p>Para la mayoría de las personas hacer un CV es un proceso confuso y detestable. Aquí te decimos cómo hacerlo más ameno.<a href="/es_mx/empleo/como_hacer_curriculum">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Tu actitud en la entrevista de trabajo</h4>
                <p><strong>Fuente:</strong>Be Executive</p>
                <p>27-oct-2014</p>
                <p>La actitud que tomes ante los retos que te imponga el reclutador será tu mejor arma para conseguir ese puesto.<a href="/es_mx/empleo/tu_actitud_entrevista_trabajo">Leer más</a></p>
              </li>




            </ul>
          </div>

          <div class="pagArticulo" style="display: none">
            <ul class="tema">


              <li ><h4>Para este puesto ya soy mayor</h4>
                <p><strong>Fuente:</strong>InfoJobs</p>
                <p>23-oct-2014</p>
                <p>¿Es mejor omitir la edad en tu CV o ser sincero?<a href="/es_mx/empleo/para_este_puesto_ya_soy_mayor">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Consejos para enviar una autocandidatura eficaz</h4>
                <p><strong>Fuente:</strong>Empresariados</p>
                <p>22-oct-2014</p>
                <p>¿Eres freelance? Aprende cómo presentar tus servicios profesionales ante un proyecto determinado.<a href="/es_mx/empleo/consejos_enviar_autocandidatura_eficaz">Leer más</a></p>
              </li>

              <li ><h4>Cómo sobrevivir a los exámenes psicométricos</h4>
                <p><strong>Fuente:</strong>Be Executive</p>
                <p>20-oct-2014</p>
                <p>Prepararse para mostrar un perfil apropiado en los exámenes psicométricos puede hacer la diferencia entre obtener o no un empleo.<a href="/es_mx/empleo/como_sobrevivir_examenes_psicometricos">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Cinco errores más comunes en una carta de presentación</h4>
                <p><strong>Fuente:</strong>InfoJobs</p>
                <p>16-oct-2014</p>
                <p>Las faltas de ortografía, reutilizar diseños de internet y no saber venderse son algunos de los fallos más recurrentes.<a href="/es_mx/empleo/cinco_errores_comunes_carta_presentacion">Leer más</a></p>
              </li>

              <li ><h4>Encuentra el trabajo ideal de acuerdo a tu personalidad</h4>
                <p><strong>Fuente:</strong>OCC Educación</p>
                <p>16-oct-2014</p>
                <p>Conocer tu personalidad puede ayudarte a enfocarte al área que realmente hará brillar tus habilidades.<a href="/es_mx/empleo/encuentra_trabajo_ideal_acuerdo_personalidad">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Cómo triunfar en una entrevista de trabajo</h4>
                <p><strong>Fuente:</strong>Dinero en imagen</p>
                <p>15-oct-2014</p>
                <p>Te decimos cómo prepararte emocionalmente para tu próxima entrevista de trabajo.<a href="/es_mx/empleo/como_triunfar_entrevista_trabajo">Leer más</a></p>
              </li>

              <li ><h4>Reglas útiles para un CV</h4>
                <p><strong>Fuente:</strong>Be Executive</p>
                <p>13-oct-2014</p>
                <p>Consejos para venderte de la mejor manera a través de tu CV.<a href="/es_mx/empleo/reglas_utiles_cv">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Las cinco preguntas trampa más comunes en la entrevista de trabajo</h4>
                <p><strong>Fuente:</strong>InfoJobs</p>
                <p>8-oct-2014</p>
                <p>Cómo responder a preguntas con truco para evitar que te descarten del proceso de selección.<a href="/es_mx/empleo/cinco_preguntas_trampa_comunes_entrevista_trabajo">Leer más</a></p>
              </li>

              <li ><h4>Management. Como te ven te contratan</h4>
                <p><strong>Fuente:</strong>El Universal</p>
                <p>6-oct-2014</p>
                <p>La imagen y apariencia figuran entre los aspectos que tienes que cuidar. No importa si trabajas en oficinas o eres freelance.<a href="/es_mx/empleo/management_como_ven_contratan">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>El secreto para conseguir el empleo que quiero</h4>
                <p><strong>Fuente:</strong>El Empresario</p>
                <p>1-oct-2014</p>
                <p>A la hora de mejorar tus posibilidades para encontrar un empleo, debes tener en cuenta varios aspectos, conócelos aquí.<a href="/es_mx/empleo/secreto_conseguir_empleo_quiero">Leer más</a></p>
              </li>




            </ul>
          </div>

          <div class="pagArticulo" style="display: none">
            <ul class="tema">


              <li ><h4>Lo que nunca debes hacer en una entrevista de trabajo</h4>
                <p><strong>Fuente:</strong>Expansión</p>
                <p>30-sep-2014</p>
                <p>En esos minutos de los que depende tu futuro laboral no debes cometer errores.<a href="/es_mx/empleo/nunca_debes_hacer_entrevista_trabajo">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Pasos para mejorar la presentación de tu currículum</h4>
                <p><strong>Fuente:</strong>Empresariados</p>
                <p>29-sep-2014</p>
                <p>Descubre los errores más comunes en la redacción de tu currículum y de tu carta de presentación y aprende a corregirlos.<a href="/es_mx/empleo/pasos_mejorar_presentacion_curriculum">Leer más</a></p>
              </li>

              <li ><h4>La imagen y el éxito profesional</h4>
                <p><strong>Fuente:</strong>Be Executive</p>
                <p>26-sep-2014</p>
                <p>Al buscar empleo necesitas proyectar una imagen ejecutiva a la altura de tus metas en todo momento, aquí algunas recomendaciones.<a href="/es_mx/empleo/imagen_exito_profesional">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Estos serán los empleos del 2025</h4>
                <p><strong>Fuente:</strong>huntRED</p>
                <p>25-sep-2014</p>
                <p>Artículo que describe los perfiles que deberán cubrir los candidatos en los próximos 10 años.<a href="/es_mx/empleo/estos_seran_empleos_2025">Leer más</a></p>
              </li>

              <li ><h4>Tener un título ayuda a aumentar 70 por ciento el salario</h4>
                <p><strong>Fuente:</strong>CNN Expansión</p>
                <p>24-sep-2014</p>
                <p>La posibilidad de tener mejor remuneración salarial es mayor con educación superior, según la OCDE.<a href="/es_mx/empleo/tener_titulo_ayuda_aumentar_70_por_ciento_salario">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Contactos que perfeccionan las búsquedas de empleo</h4>
                <p><strong>Fuente:</strong>CNN Expansión</p>
                <p>23-sep-2014</p>
                <p>Fórmulas y plataformas que asocian las estrategias de búsqueda de pareja con la selección de candidatos.<a href="/es_mx/empleo/contactos_perfeccionan_busquedas_empleo">Leer más</a></p>
              </li>

              <li ><h4>Cómo afrontar el "ya te llamaremos" y el "no has sido seleccionado"</h4>
                <p><strong>Fuente:</strong>InfoJobs</p>
                <p>22-sep-2014</p>
                <p>Te platicamos cómo superar negativas como 'No das el perfil' o 'No pasas a la siguiente fase', y varios: 'Ya te llamaremos'.<a href="/es_mx/empleo/como_afrontar_llamaremos_seleccionado">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Cuando buscar empleo es un trabajo</h4>
                <p><strong>Fuente:</strong>Generadores de Mejoras Contínuas en Recursos Humanos</p>
                <p>8-sep-2014</p>
                <p>Recomendaciones para comenzar la búsqueda laboral con éxito.<a href="/es_mx/empleo/cuando_buscar_empleo_trabajo">Leer más</a></p>
              </li>

              <li ><h4>Descubre las empresas que mejor fomentan el equilibrio entre la vida laboral y la personal</h4>
                <p><strong>Fuente:</strong>Universia</p>
                <p>5-sep-2014</p>
                <p>En este artículo te revelamos cuáles son las compañías que mejor fomentan esta clase de equilibrio.<a href="/es_mx/empleo/descubre_empresas_mejor_fomentan_equilibrio_entre_vida_laboral_personal">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Cómo superar una entrevista de trabajo en inglés</h4>
                <p><strong>Fuente:</strong>Empresariados</p>
                <p>4-sep-2014</p>
                <p>Consejos útiles sobre lo que debes y no debes hacer si te enfrentas a una entrevista en inglés.<a href="/es_mx/empleo/como_superar_entrevista_trabajo_ingles">Leer más</a></p>
              </li>




            </ul>
          </div>

          <div class="pagArticulo" style="display: none">
            <ul class="tema">


              <li ><h4>¿Te conviene una cuenta premium de LinkedIn?</h4>
                <p><strong>Fuente:</strong>El Financiero</p>
                <p>4-sep-2014</p>
                <p>Aprende a apoyarte en herramientas de promoción profesional en la red. Las cuentas premium pueden darte ventajas.<a href="/es_mx/empleo/te_conviene_cuenta_premium_linkedIn">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Derribando mitos sobre buscar empleo en Internet</h4>
                <p><strong>Fuente:</strong>Portal de Empleo de iBazar</p>
                <p>3-sep-2014</p>
                <p>Circulan opiniones y comentarios que muchas veces nos dejan confundidos. En este artículo, te ayudamos a desentrañar los principales mitos y verdades sobre la búsqueda online de trabajo. <a href="/es_mx/empleo/derribando_mitos_sobre_buscar_empleo_internet">Leer más</a></p>
              </li>

              <li ><h4>Cómo buscar trabajo después de haber sido emprendedor</h4>
                <p><strong>Fuente:</strong>Empresariados</p>
                <p>28-ago-2014</p>
                <p>Artículo con consejos para la búsqueda de empleo después de haber sido empresario.<a href="/es_mx/empleo/como_buscar_trabajo_despues_haber_sido_emprendedor">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Seis pasos para una entrevista de trabajo exitosa</h4>
                <p><strong>Fuente:</strong>Publimetro</p>
                <p>28-ago-2014</p>
                <p>Recomendaciones del sitio Lucas5.com para destacar en una entrevista.<a href="/es_mx/empleo/seis_pasos_entrevista_trabajo_exitosa">Leer más</a></p>
              </li>

              <li ><h4>¿Qué espera de ti un reclutador?</h4>
                <p><strong>Fuente:</strong>CNN Expansión</p>
                <p>28-ago-2014</p>
                <p>Según la edad del candidato las empresas buscan diferentes competencias, conócelas en este artículo.<a href="/es_mx/empleo/que_espera_reclutador">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Palabras que no debes incluir en tu currículum vitae</h4>
                <p><strong>Fuente:</strong>Publimetro</p>
                <p>26-ago-2014</p>
                <p>¿Qué es lo que buscan los reclutadores a la hora de evaluar a un candidato?<a href="/es_mx/empleo/palabras_que_no_debes_incluir_curriculum_vitae">Leer más</a></p>
              </li>

              <li ><h4>Los errores que "afectan" tu currículo</h4>
                <p><strong>Fuente:</strong>ZonaJobs</p>
                <p>22-ago-2014</p>
                <p>Un CV bien preparado, es tu oportunidad para llamar la atención del reclutador y recibir la invitación a una entrevista.<a href="/es_mx/empleo/los_errores_afectan_curriculo">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Cuatro meses de motivación, clave para encontrar trabajo</h4>
                <p><strong>Fuente:</strong>Bienestar 180</p>
                <p>21-ago-2014</p>
                <p>Una actitud positiva es, efectivamente, una de las mejores claves durante la búsqueda laboral.<a href="/es_mx/empleo/cuatro_meses_motivacion_clave_encontrar_trabajo">Leer más</a></p>
              </li>

              <li ><h4>Cómo redactar tu currículum cuando no tienes experiencia laboral</h4>
                <p><strong>Fuente:</strong>OCC Educación</p>
                <p>20-ago-2014</p>
                <p>Si eres recién egresado te ofrecemos una guía para la elaboración de tu primer CV.<a href="/es_mx/empleo/como_redactar_curriculum_cuando_no_tienes_experiencia_laboral">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Qué debes hacer ahora para que tu currículo funcione</h4>
                <p><strong>Fuente:</strong>Expansión</p>
                <p>19-ago-2014</p>
                <p>Los contenidos de tu CV deben reflejar los talentos y habilidades que eres capaz de ofrecer en el puesto que deseas conseguir.<a href="/es_mx/empleo/que_debes_hacer_ahora_curriculo_funcione">Leer más</a></p>
              </li>




            </ul>
          </div>

          <div class="pagArticulo" style="display: none">
            <ul class="tema">


              <li ><h4>Empresas en busca de personal mayor de 50 años</h4>
                <p><strong>Fuente:</strong>IDC Online</p>
                <p>18-ago-2014</p>
                <p>Las empresas están buscando a personal nacido entre 1946 y 1960, ya que su experiencia profesional es un gran aporte.<a href="/es_mx/empleo/empresas_busca_personal_mayor_50_anos">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Qué hacer en caso de perder su empleo</h4>
                <p><strong>Fuente:</strong>El Economista</p>
                <p>15-ago-2014</p>
                <p>Recomendaciones para contrarrestar los efectos negativos en caso de perder un empleo.<a href="/es_mx/empleo/que_hacer_caso_perder_empleo">Leer más</a></p>
              </li>

              <li ><h4>Cuatro actitudes que enojan a un reclutador</h4>
                <p><strong>Fuente:</strong>CNN Expansión</p>
                <p>14-ago-2014</p>
                <p>De acuerdo con datos de diferentes fuentes, se elaboró una lista de las cuatro actitudes más negativas que reflejan  los candidatos al realizar una entrevista laboral.<a href="/es_mx/empleo/cuatro_actitudes_enojan_reclutador">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Las 10 áreas laborales que pagan mejores sueldos</h4>
                <p><strong>Fuente:</strong>Dinero en Imagen</p>
                <p>13-ago-2014</p>
                <p>Arquitectura, urbanismo y diseño, son las carreras que perciben un mayor salario.<a href="/es_mx/empleo/las_10_areas_laborales_pagan_mejores_sueldos">Leer más</a></p>
              </li>

              <li ><h4>¿Cuáles son las cinco mejores áreas laborales para ser freelance?</h4>
                <p><strong>Fuente:</strong>El Financiero</p>
                <p>12-ago-2014</p>
                <p>Si bien internet facilita encontrar proyectos en todo el mundo por labores específicas, algunas áreas son mejor remuneradas que otras.<a href="/es_mx/empleo/cuales_son_cinco_mejores_areas_laborales_freelance">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Top 10 de las ofertas de empleo en TIC</h4>
                <p><strong>Fuente:</strong>El Economista</p>
                <p>11-ago-2014</p>
                <p>Empleos como programador de software, soporte técnico, programador web, consultoría en TI e informática en general entre ellos.<a href="/es_mx/empleo/top_10_ofertas_empleo_tic">Leer más</a></p>
              </li>

              <li ><h4>¿Por qué no me llaman? ¡Soy un buen candidato!</h4>
                <p><strong>Fuente:</strong>ZonaJobs</p>
                <p>8-ago-2014</p>
                <p>El descuido o la desorganización del candidato pueden retrasar o limitar la llegada a una oferta laboral.<a href="/es_mx/empleo/por_que_no_me_llaman_soy_buen_candidato">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Novedosas tarjetas de presentación con USB</h4>
                <p><strong>Fuente:</strong>Universia</p>
                <p>7-ago-2014</p>
                <p>Las tarjetas de presentación son un detalle que los profesionales no deben pasar por alto.<a href="/es_mx/empleo/novedosas_tarjetas_presentacion_usb">Leer más</a></p>
              </li>

              <li ><h4>Empleo de verano, fuente de ingresos para jóvenes</h4>
                <p><strong>Fuente:</strong>Publimetro</p>
                <p>6-ago-2014</p>
                <p>Las ofertas aumentan entre cinco y 10 por ciento durante esta época.<a href="/es_mx/empleo/empleo_verano_fuente_ingresos_jovenes">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Seis meses, límite para hallar empleo</h4>
                <p><strong>Fuente:</strong>CNN Expansión</p>
                <p>6-ago-2014</p>
                <p>Si tu búsqueda de trabajo se prolonga demasiado, debes cambiar de estrategia.<a href="/es_mx/empleo/seis_meses_limite_hallar_empleo">Leer más</a></p>
              </li>




            </ul>
          </div>

          <div class="pagArticulo" style="display: none">
            <ul class="tema">


              <li ><h4>El decálogo de un buen candidato</h4>
                <p><strong>Fuente:</strong>Blog ZonaJobs</p>
                <p>1-ago-2014</p>
                <p>¿Cómo hacer para destacar en un campo tan reñido y lograr que te contraten?<a href="/es_mx/empleo/el_decalogo_buen_candidato">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Cinco habilidades por las que las empresas mexicanas pagarían más dinero</h4>
                <p><strong>Fuente:</strong>Universia</p>
                <p>30-jul-2014</p>
                <p>Entérate cuáles son las habilidades por las que los empresarios mexicanos pagarían más.<a href="/es_mx/empleo/cinco_habilidades_empresas_mexicanas_pagarian_mas_dinero">Leer más</a></p>
              </li>

              <li ><h4>Nueve ocupaciones sin problemas de empleo</h4>
                <p><strong>Fuente:</strong>CNN Expansión</p>
                <p>28-jul-2014</p>
                <p>Los reclutadores tienen dificultades para encontrar contadores bilingües, ingenieros en informática, así como ejecutivos y asesores de viajes para el sector turístico.<a href="/es_mx/empleo/nueve_ocupaciones_sin_problemas_empleo">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Aprende a trabajar en un entorno digital</h4>
                <p><strong>Fuente:</strong>Expansión</p>
                <p>25-jul-2014</p>
                <p>Conocer y saber qué uso puedes dar a las herramientas tecnológicas, garantiza tu productividad en el contexto laboral.<a href="/es_mx/empleo/aprende_trabajar_entorno_digital">Leer más</a></p>
              </li>

              <li ><h4>¿Cómo manejar la ansiedad durante el proceso de búsqueda de trabajo?</h4>
                <p><strong>Fuente:</strong>ZonaJobs</p>
                <p>25-jul-2014</p>
                <p>¿Cómo la identificamos? y ¿qué podemos hacer para disminuirla?<a href="/es_mx/empleo/como_manejar_ansiedad_durante_proceso_busqueda_trabajo">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>¡Adelántate, aprende chino y conquista el mercado laboral!</h4>
                <p><strong>Fuente:</strong>Generadores de Mejoras Contínuas en Recursos Humanos</p>
                <p>23-jul-2014</p>
                <p>Te contamos cuáles son las claves para triunfar en el gigante asiático.<a href="/es_mx/empleo/adelantate_aprende_chino_conquista_mercado_laboral">Leer más</a></p>
              </li>

              <li ><h4>Cómo llamar la atención de un headhunter</h4>
                <p><strong>Fuente:</strong>Empresariados</p>
                <p>22-jul-2014</p>
                <p>Conoce, entre otras cosas, cómo hacer una entrevista con un cazatalentos.<a href="/es_mx/empleo/como_llamar_atencion_headhunter">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Cinco claves de un currículum ganador</h4>
                <p><strong>Fuente:</strong>OCC Educación</p>
                <p>21-jul-2014</p>
                <p>Consejos para crear un currículum que te lleve al siguiente paso.<a href="/es_mx/empleo/cinco_claves_curriculum_ganador">Leer más</a></p>
              </li>

              <li ><h4>Lo que no debes preguntar en una entrevista de trabajo</h4>
                <p><strong>Fuente:</strong>Publimetro</p>
                <p>21-jul-2014</p>
                <p>Artículo que menciona algunas de las preguntas que nunca se deben hacer en una entrevista de trabajo.<a href="/es_mx/empleo/lo_que_no_debes_preguntar_entrevista_trabajo">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>¿Tienes una entrevista de trabajo por teléfono? Atento</h4>
                <p><strong>Fuente:</strong>Universia Blog Empleo</p>
                <p>18-jul-2014</p>
                <p>Es un modelo más de selección de candidatos, por eso, es fundamental saber cómo desenvolverse para causar buena impresión.<a href="/es_mx/empleo/tienes_entrevista_trabajo_telefono_atento">Leer más</a></p>
              </li>




            </ul>
          </div>

          <div class="pagArticulo" style="display: none">
            <ul class="tema">


              <li ><h4>Déficit de talento bilingüe, foco rojo</h4>
                <p><strong>Fuente:</strong>CNN Expansión</p>
                <p>16-jul-2014</p>
                <p>Los sectores de tecnología, logística, finanzas, consumo masivo y ventas requieren un nivel importante de inglés.<a href="/es_mx/empleo/deficit_talento_bilingue_foco_rojo">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>¿Qué debo hacer para alcanzar el trabajo de mis sueños?</h4>
                <p><strong>Fuente:</strong>Universia</p>
                <p>11-jul-2014</p>
                <p>Grant Imahara, conductor de ?Cazadores de Mitos?, te ofrece una guía para alcanzar el trabajo de tus sueños.<a href="/es_mx/empleo/que_debo_hacer_alcanzar_trabajo_suenos">Leer más</a></p>
              </li>

              <li ><h4>Preguntas más frecuentes en una entrevista de trabajo en inglés</h4>
                <p><strong>Fuente:</strong>Generadores de Mejoras Contínuas en Recursos Humanos</p>
                <p>10-jul-2014</p>
                <p>Te dejamos una selección de las preguntas que más se repiten en una entrevista de trabajo en inglés y su traducción.<a href="/es_mx/empleo/preguntas_frecuentes_entrevista_trabajo_ingles">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Seis claves para mejorar tu nivel de inglés</h4>
                <p><strong>Fuente:</strong>Alto Nivel</p>
                <p>9-jul-2014</p>
                <p>Si lo estás estudiando, te gustaría hacerlo o tienes cierto dominio pero te sientes 'oxidado', sigue estos consejos.<a href="/es_mx/empleo/seis_claves_mejorar_nivel_ingles">Leer más</a></p>
              </li>

              <li ><h4>Tips para mejorar tus entrevistas de trabajo</h4>
                <p><strong>Fuente:</strong>Publimetro</p>
                <p>8-jul-2014</p>
                <p>Te presentamos siete claves para impresionar al reclutador.<a href="/es_mx/empleo/tips_mejorar_entrevistas_trabajo">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Un mal currículum puede cerrar puertas de trabajo</h4>
                <p><strong>Fuente:</strong>Dinero en Imagen</p>
                <p>8-jul-2014</p>
                <p>Contar con un currículum vitae mal diseñado puede cerrarle las puertas al aplicar para algún puesto laboral.<a href="/es_mx/empleo/un_mal_curriculum_puede_cerrar_puertas_trabajo">Leer más</a></p>
              </li>

              <li ><h4>Candidato: dime tus cinco defectos y cualidades</h4>
                <p><strong>Fuente:</strong>ZonaJobs</p>
                <p>7-jul-2014</p>
                <p>Cuántas veces has llegado a una entrevista de trabajo y el reclutador sorprende con la pregunta.<a href="/es_mx/empleo/candidato_dime_cinco_defectos_cualidades">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Un título no es suficiente; las empresas buscan habilidades</h4>
                <p><strong>Fuente:</strong>Publimetro</p>
                <p>7-jul-2014</p>
                <p>Los empleadores prefieren capacidades para trabajar en equipo, comunicarse y confiabilidad.<a href="/es_mx/empleo/un_titulo_suficiente_empresas_buscan_habilidades">Leer más</a></p>
              </li>

              <li ><h4>Cuide su información personal al solicitar empleo</h4>
                <p><strong>Fuente:</strong>El Economista</p>
                <p>4-jul-2014</p>
                <p>Consejos para cuidar sus datos personales al momento de buscar empleo.<a href="/es_mx/empleo/cuide_informacion_personal_solicitar_empleo">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Si el currículo de siempre ya no sirve... ¿para qué mentir?</h4>
                <p><strong>Fuente:</strong>Expansión</p>
                <p>3-jul-2014</p>
                <p>Falsear tu vida laboral, exagerarla o incluso rebajarla no tiene sentido.<a href="/es_mx/empleo/si_el_curriculo_siempre_ya_no_sirve_para_que_mentir">Leer más</a></p>
              </li>




            </ul>
          </div>

          <div class="pagArticulo" style="display: none">
            <ul class="tema">


              <li ><h4>Cinco tips para conseguir trabajo después de titularte</h4>
                <p><strong>Fuente:</strong>Publimetro</p>
                <p>30-jun-2014</p>
                <p>Aunque crear un perfil en LinkedIn no es una mala idea, hay otros consejos simples que pueden ayudarte. Revísalos aquí.<a href="/es_mx/empleo/cinco_tips_conseguir_trabajo_despues_titularte">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Técnicos e ingenieros, talento escaso</h4>
                <p><strong>Fuente:</strong>CNN Expansión</p>
                <p>27-jun-2014</p>
                <p>Reporte de Manpower donde se presentan los puestos con mayor dificultad para ser cubiertos y el por qué.<a href="/es_mx/empleo/tecnicos_ingenieros_talento_escaso">Leer más</a></p>
              </li>

              <li ><h4>El primer empleo</h4>
                <p><strong>Fuente:</strong>Universia</p>
                <p>25-jun-2014</p>
                <p>¿Tienes dudas al buscar tu primer empleo? conoce las opiniones de los responsables de selección de varias empresas y resuélvelas.<a href="/es_mx/empleo/el_primer_empleo">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Sitios web para encontrar trabajo en México</h4>
                <p><strong>Fuente:</strong>Universia</p>
                <p>24-jun-2014</p>
                <p>En este artículo te revelamos los mejores sitios web para encontrar trabajo y, también, algunos consejos de gran utilidad.<a href="/es_mx/empleo/sitios_web_encontrar_trabajo_mexico">Leer más</a></p>
              </li>

              <li ><h4>Faltan a jóvenes habilidades emocionales</h4>
                <p><strong>Fuente:</strong>CNN Expansión</p>
                <p>23-jun-2014</p>
                <p>Según el BID; la posiblidad de encontrar un empleo y el nivel salarial no depende de conocimientos técnicos.<a href="/es_mx/empleo/falta_jovenes_habilidades_emocionales">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Este es el becario que quieren las empresas</h4>
                <p><strong>Fuente:</strong>Expansión</p>
                <p>20-jun-2014</p>
                <p>Si quieres acceder a tu primer empleo, participar en un programa de prácticas te abrirá las puertas del mercado laboral.<a href="/es_mx/empleo/este_becario_quieren_empresas">Leer más</a></p>
              </li>

              <li ><h4>Fórmula para encontrar el empleo de tus sueños</h4>
                <p><strong>Fuente:</strong>Publimetro</p>
                <p>19-jun-2014</p>
                <p>Recomendaciones para conseguir el éxito en la búsqueda de trabajo y lograr mejores oportunidades laborales.<a href="/es_mx/empleo/formula_encontrar_empleo_suenos">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Cómo retomar tu carrera tras una pausa</h4>
                <p><strong>Fuente:</strong>CNN Expansión</p>
                <p>18-jun-2014</p>
                <p>Cinco consejos  dirigidos a las mujeres para reincorporarse a la fuerza laboral.<a href="/es_mx/empleo/como_retomar_carrera_tras_pausa">Leer más</a></p>
              </li>

              <li ><h4>Cómo preparar una entrevista de trabajo para un puesto interesante</h4>
                <p><strong>Fuente:</strong>Empresariados</p>
                <p>16-jun-2014</p>
                <p>Se brindan una serie de consejos sobre cómo preparar una entrevista para obtener el trabajo ideal.<a href="/es_mx/empleo/como_preparar_entrevista_trabajo_puesto_interesante">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Empresarios vislumbran mejoras en empleo durante 2014</h4>
                <p><strong>Fuente:</strong>El Informador</p>
                <p>13-jun-2014</p>
                <p>Los ramos de tecnología y servicios profesionales son los que brindan más oportunidades.<a href="/es_mx/empleo/empresarios_vislumbran_mejoras_empleo_durante_2014">Leer más</a></p>
              </li>




            </ul>
          </div>

          <div class="pagArticulo" style="display: none">
            <ul class="tema">


              <li ><h4>Siete preguntas geniales para hacerle al reclutador</h4>
                <p><strong>Fuente:</strong>OCC Mundial</p>
                <p>12-jun-2014</p>
                <p>En una entrevista de trabajo existe un 99 por ciento de probabilidades de que el reclutador te pregunta '¿Tienes alguna duda?'<a href="/es_mx/empleo/siete_preguntas_geniales_hacerle_reclutador">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Los nervios: tu arma secreta en una entrevista de trabajo</h4>
                <p><strong>Fuente:</strong>Asociación Mexicana en Dirección de Recursos Humanos</p>
                <p>11-jun-2014</p>
                <p>Los nervios pueden ser excelentes aliados.¿Pero cómo podemos convertir tal debilidad en una fortaleza?<a href="/es_mx/empleo/los_nervios_arma_secreta_entrevista_trabajo">Leer más</a></p>
              </li>

              <li ><h4>Video currículum, el arma que seducirá a tu reclutador</h4>
                <p><strong>Fuente:</strong>Alto Nivel</p>
                <p>11-jun-2014</p>
                <p>En la búsqueda de empleo la competencia es de habilidad, pero también de ingenio, creatividad e innovación.<a href="/es_mx/empleo/video_curriculum_arma_seducira_reclutador">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Claves para destacar en un nuevo empleo</h4>
                <p><strong>Fuente:</strong>Universia</p>
                <p>10-jun-2014</p>
                <p>Evitar problemas con los demás y enfocarse en las responsabilidades propias, son algunas de las actitudes más recomendables.<a href="/es_mx/empleo/claves_destacar_nuevo_empleo">Leer más</a></p>
              </li>

              <li ><h4>¿Buscas un nuevo rumbo? Estudia un diplomado</h4>
                <p><strong>Fuente:</strong>El Financiero</p>
                <p>9-jun-2014</p>
                <p>Los diplomados ayudan a ampliar el nivel de profesionalización y tienen la ventaja de durar pocos meses.<a href="/es_mx/empleo/buscas_nuevo_rumbo_estudia_diplomado">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Usa la mercadotecnia para contratarte</h4>
                <p><strong>Fuente:</strong>CNN Expansión</p>
                <p>6-jun-2014</p>
                <p>Un 70 por ciento de la oportunidad de convencer a un reclutador se pierde por descuidar tu 'branding'.<a href="/es_mx/empleo/usa_mercadotecnia_contratarte">Leer más</a></p>
              </li>

              <li ><h4>Cómo pasar de becario a empleado en una empresa</h4>
                <p><strong>Fuente:</strong>Blog Empleo Universia</p>
                <p>5-jun-2014</p>
                <p>¿Qué debe tener un buen becario para hacerse imprescindible?, ¿cómo desenvolverse para conseguir que te consideren un valor seguro?<a href="/es_mx/empleo/como_pasar_becario_empleado_empresa">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>La verdadera razón por la que no se contrata a universitarios</h4>
                <p><strong>Fuente:</strong>Forbes</p>
                <p>5-jun-2014</p>
                <p>Las empresas y sus líderes empiezan a insistir en que la oferta actual de candidatos no cubre su demanda de talento.<a href="/es_mx/empleo/la_verdadera_razon_contrata_universitarios">Leer más</a></p>
              </li>

              <li ><h4>Errores que pueden ahuyentar un empleo</h4>
                <p><strong>Fuente:</strong>CNN Expansión</p>
                <p>26-may-2014</p>
                <p>Buscar empresas distintas a tu perfil o no personalizar tu CV son elementos que te quitan puntos.<a href="/es_mx/empleo/errores_pueden_ahuyentar_empleo">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Ocho temores que impiden desarrollarte y ser exitoso</h4>
                <p><strong>Fuente:</strong>Publimetro</p>
                <p>23-may-2014</p>
                <p>Definir prioridades, planear y organizar tu agenda es necesario para vencer cualquier obstáculo o temor profesional o personal.<a href="/es_mx/empleo/ocho_temores_impiden_desarrollarte_exitoso">Leer más</a></p>
              </li>




            </ul>
          </div>

          <div class="pagArticulo" style="display: none">
            <ul class="tema">


              <li ><h4>Siete claves de lenguaje corporal para una entrevista de trabajo</h4>
                <p><strong>Fuente:</strong>Forbes</p>
                <p>22-may-2014</p>
                <p>Siete consejos que siempre hay que tener en mente cuando se está frente a un entrevistador.<a href="/es_mx/empleo/siete_claves_lenguaje_corporal_entrevista_trabajo">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Por qué el reclutador sigue sin llamarte</h4>
                <p><strong>Fuente:</strong>CNN Expansión</p>
                <p>20-may-2014</p>
                <p>Muchas veces, un CV mal redactado, pocos detalles o lagunas pueden cerrarte la puerta a un empleo.<a href="/es_mx/empleo/por_que_reclutador_sigue_llamarte">Leer más</a></p>
              </li>

              <li ><h4>Cinco tipos de empleados que nadie quiere</h4>
                <p><strong>Fuente:</strong>CNN Expansión</p>
                <p>13-may-2014</p>
                <p>Los empleados con poca iniciativa o que dejan las cosas a medias, no son los preferidos por las organizaciones.<a href="/es_mx/empleo/cinco_tipos_empleados_nadie_quiere">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Cinco hábitos que las personas con suerte poseen</h4>
                <p><strong>Fuente:</strong>Alto Nivel</p>
                <p>7-may-2014</p>
                <p>Seguro conoces a alguien exitoso que pareciera tener mucha 'suerte'. ¿Qué hacen para tenerla y para conseguir sus sueños?<a href="/es_mx/empleo/cinco_habitos_personas_suerte_poseen">Leer más</a></p>
              </li>

              <li ><h4>Cuatro preguntas que hacen los reclutadores</h4>
                <p><strong>Fuente:</strong>CNN Expansión</p>
                <p>6-may-2014</p>
                <p>¿Por qué dejaste tu último trabajo? es una de ellas; conoce algunas formas de responder de forma correcta.<a href="/es_mx/empleo/cuatro_preguntas_hacen_reclutadores">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>¿Oficios o profesiones? Las nuevas vacantes en el mercado laboral</h4>
                <p><strong>Fuente:</strong>CD Consultores</p>
                <p>2-may-2014</p>
                <p>Conoce las funciones, tareas y responsabilidades que deben desempeñar quienes ocupan los puestos que se ofertan con títulos en inglés.<a href="/es_mx/empleo/oficios_profesiones_nuevas_vacantes_mercado_laboral">Leer más</a></p>
              </li>

              <li ><h4>No te dejes vencer por el desempleo</h4>
                <p><strong>Fuente:</strong>Blog Universia</p>
                <p>29-abr-2014</p>
                <p>Si quieres saber qué tienes que hacer para no dejarte arrastrar por el desánimo, sigue leyendo.<a href="/es_mx/empleo/no_te_dejes_vencer_desempleo">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Tips para tener éxito en una negociación laboral</h4>
                <p><strong>Fuente:</strong>Publimetro</p>
                <p>25-abr-2014</p>
                <p>Conoce algunos importantes consejos para alcanzar el éxito laboral y empresarial.<a href="/es_mx/empleo/tips_tener_exito_negociacion_laboral">Leer más</a></p>
              </li>

              <li ><h4>Siete tips para hacer una buena entrevista de trabajo</h4>
                <p><strong>Fuente:</strong>Dinero en Imagen</p>
                <p>24-abr-2014</p>
                <p>Luego de aplicar para la vacante ideal y ser seleccionado, viene la entrevista de trabajo. Prepárate para ella.<a href="/es_mx/empleo/siete_tips_hacer_buena_entrevista_trabajo">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>¿En qué se fijan las empresas antes de contratarte?</h4>
                <p><strong>Fuente:</strong>Alto Nivel</p>
                <p>23-abr-2014</p>
                <p>Buscan candidatos con 10 competencias muy escasas en el mercado. Desarrolla y vende estas hablidades en tu entrevista de trabajo.<a href="/es_mx/empleo/en_que_se_fijan_empresas_antes_contratarte">Leer más</a></p>
              </li>




            </ul>
          </div>

          <div class="pagArticulo" style="display: none">
            <ul class="tema">


              <li ><h4>Verdades y mitos al buscar trabajo</h4>
                <p><strong>Fuente:</strong>Publimetro</p>
                <p>22-abr-2014</p>
                <p>Catalina Moreno, nos comenta sobre algunos mitos y verdades que surgen al momento de buscar trabajo.<a href="/es_mx/empleo/verdades_mitos_al_buscar_trabajo">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Cuánto puedes ganar si estudias un máster</h4>
                <p><strong>Fuente:</strong>Expansión</p>
                <p>21-abr-2014</p>
                <p>Los jóvenes que realizan un programa de posgrado pueden aspirar a un 40% de sueldo más que aquellos que no poseen esta formación.<a href="/es_mx/empleo/cuanto_puedes_ganar_estudias_master">Leer más</a></p>
              </li>

              <li ><h4>Básicos pero infalibles para buscar empleo en tus redes</h4>
                <p><strong>Fuente:</strong>Alto Nivel</p>
                <p>16-abr-2014</p>
                <p>La era digital llegó y debes aprender a explotar tus redes sociales más allá del entretenimiento.<a href="/es_mx/empleo/basicos_infalibles_buscar_empleo_redes">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Ingenierías garantizan empleo; humanidades, con la peor tasa</h4>
                <p><strong>Fuente:</strong>Informador</p>
                <p>15-abr-2014</p>
                <p>Estudio del Imco subraya que Física e ingenierías en vehículos llegan a 100 por ciento de inserción laboral.<a href="/es_mx/empleo/ingenierias_garantizan_empleo_humanidades_tasa">Leer más</a></p>
              </li>

              <li ><h4>Siete poses de poder para ser más exitoso</h4>
                <p><strong>Fuente:</strong>Soy Entrepreneur</p>
                <p>14-abr-2014</p>
                <p>Te decimos cómo usar el lenguaje corporal para proyectar dominio y confianza en ti mismo en situaciones laborales comunes.<a href="/es_mx/empleo/siete_poses_poder_ser_exitoso">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>En el desempleo, ¿retirar del fondo de vivienda o de ahorro para el retiro?</h4>
                <p><strong>Fuente:</strong>El Financiero</p>
                <p>11-abr-2014</p>
                <p>Si pierdes tu empleo y no cuentas con fondo de emergencias, puedes retirar recursos del fondo para el retiro o del seguro de desempleo.<a href="/es_mx/empleo/desempleo_retirar_fondo_vivienda_ahorro_retiro">Leer más</a></p>
              </li>

              <li ><h4>La marca personal es algo que puede ofrecernos muchas oportunidades profesionales</h4>
                <p><strong>Fuente:</strong>PuroMarketing</p>
                <p>8-abr-2014</p>
                <p>Descubre cuáles son.<a href="/es_mx/empleo/marca_personal_algo_puede_ofrecernos_muchas_oportunidades_profesionales">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Mujeres son más exigentes en el ambiente laboral</h4>
                <p><strong>Fuente:</strong>Publimetro</p>
                <p>7-abr-2014</p>
                <p>Buscan empresas que cuenten con experiencia internacional, imagen y reputación.<a href="/es_mx/empleo/mujeres_son_mas_exigentes_ambiente_laboral">Leer más</a></p>
              </li>

              <li ><h4>Cinco técnicas para tener mente positiva</h4>
                <p><strong>Fuente:</strong>Soy Entrepreneur</p>
                <p>4-abr-2014</p>
                <p>Aprende a deshacerte de las emociones negativas y a visualizarte como una persona generosa, feliz y capaz de alcanzar el éxito.<a href="/es_mx/empleo/cinco_tecnicas_tener_mente_positiva">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>¿Cómo enfrentar el primer día de trabajo?</h4>
                <p><strong>Fuente:</strong>Universia</p>
                <p>2-abr-2014</p>
                <p>Es importante que el primer día de trabajo muestres una actitud proactiva y que te muestres dispuesto.<a href="/es_mx/empleo/como_enfrentar_primer_dia_trabajo">Leer más</a></p>
              </li>




            </ul>
          </div>

          <div class="pagArticulo" style="display: none">
            <ul class="tema">


              <li ><h4>Errores que asustan a los reclutadores</h4>
                <p><strong>Fuente:</strong>CNN Expansión</p>
                <p>1-abr-2014</p>
                <p>Con la batalla que existe por conseguir una entrevista de trabajo, no es conveniente darse el lujo de caer en estos errores.<a href="/es_mx/empleo/errores_asustan_reclutadores">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Graduados: tener experiencia laboral es sinónimo de empleo</h4>
                <p><strong>Fuente:</strong>Universia</p>
                <p>31-mar-2014</p>
                <p>Especialistas aseguran que la experiencia laboral al graduarse de la universidad es factor determinante para conseguir empleo enseguida.<a href="/es_mx/empleo/graduados_tener_experiencia_laboral_sinonimo_empleo">Leer más</a></p>
              </li>

              <li ><h4>Diferénciate, haz un video currículum</h4>
                <p><strong>Fuente:</strong>CNN Expansión</p>
                <p>28-mar-2014</p>
                <p>Ésta herramienta aporta una idea de cómo es el candidato, su personalidad. Aquí te damos algunos consejos sobre cómo elaborarlo.<a href="/es_mx/empleo/diferenciate_haz_video_curriculum">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Promueven inclusión laboral de los adultos mayores</h4>
                <p><strong>Fuente:</strong>Informador</p>
                <p>27-mar-2014</p>
                <p>Buscan concretar convenios con empresas para que se inserten en la vida productiva.<a href="/es_mx/empleo/promueven_inclusion_laboral_adultos_mayores">Leer más</a></p>
              </li>

              <li ><h4>ABC para elevar la productividad freelance</h4>
                <p><strong>Fuente:</strong>Forbes</p>
                <p>24-mar-2014</p>
                <p>Te presentamos cinco claves que pueden convertirte en un 'super profesionista' cuando trabajas de manera independiente.<a href="/es_mx/empleo/abc_elevar_productividad_freelance">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Empresas reprueban imagen de jóvenes profesionistas</h4>
                <p><strong>Fuente:</strong>Informador</p>
                <p>20-mar-2014</p>
                <p>Falta de liderazgo, no saberse vender, carencia de habilidades sociales y de conocimientos, entre los peores indicadores.<a href="/es_mx/empleo/empresas_reprueban_imagen_jovenes_profesionistas">Leer más</a></p>
              </li>

              <li ><h4>Tips: La mejor opción para buscar trabajo en línea</h4>
                <p><strong>Fuente:</strong>Publimetro</p>
                <p>14-mar-2014</p>
                <p>Las redes sociales hoy en día son una herramienta de trabajo e incluso sirven para buscar uno cuando no se tiene.<a href="/es_mx/empleo/tips_mejor_opcion_buscar_trabajo_linea">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>¿Qué buscan las mujeres millennial en un trabajo?</h4>
                <p><strong>Fuente:</strong>Dinero en Imagen</p>
                <p>13-mar-2014</p>
                <p>Es la generación que más rápido se ha insertado en el mercado laboral y sus exigencias marcarán las tendencias entre empleadores.<a href="/es_mx/empleo/que_buscan_mujeres_millennial_trabajo">Leer más</a></p>
              </li>

              <li ><h4>Los errores financieros de un "freelance"</h4>
                <p><strong>Fuente:</strong>El Financiero</p>
                <p>12-mar-2014</p>
                <p>El trabajo independiente requiere una detallada organización y una administración minuciosa, para evitar problemas financieros y fiscales.<a href="/es_mx/empleo/errores_financieros_freelance">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Inglés, deficiente en empleados</h4>
                <p><strong>Fuente:</strong>CNN Expansión</p>
                <p>11-mar-2014</p>
                <p>Casi 90 por ciento de los empleadores en México está insatisfecho con el manejo del idioma por su personal; te decimos el reto que enfrentan.<a href="/es_mx/empleo/ingles_deficiente_empleados">Leer más</a></p>
              </li>




            </ul>
          </div>

          <div class="pagArticulo" style="display: none">
            <ul class="tema">


              <li ><h4>¿Cómo superar una entrevista incómoda de trabajo?</h4>
                <p><strong>Fuente:</strong>Publimetro</p>
                <p>7-mar-2014</p>
                <p>Algunas de las recomendaciones de los especialistas al momento de la entrevista son evitar angustias y concentrarse en la respuesta.<a href="/es_mx/empleo/como_superar_entrevista_incomoda_trabajo">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>¿Qué hacer tras una entrevista laboral?</h4>
                <p><strong>Fuente:</strong>CNN Expansión</p>
                <p>6-mar-2014</p>
                <p>La delgada línea entre el entusiasmo y la necedad hacen la diferencia para obtener un trabajo; descubre qué actitudes pueden borrarte del mapa.<a href="/es_mx/empleo/que_hacer_tras_entrevista_laboral">Leer más</a></p>
              </li>

              <li ><h4>Ese currículo que traes no sirve para encontrar un trabajo</h4>
                <p><strong>Fuente:</strong>Expansión</p>
                <p>5-mar-2014</p>
                <p>Para los empleadores resultan cada vez más irrelevantes tus 'éxitos' pasados y la forma 'tradicional' en la que los vendes. Piensa si dedicar demasiado tiempo a esto merece la pena cuando buscas un empleo.<a href="/es_mx/empleo/ese_curriculo_traes_no_sirve_encontrar_trabajo">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Haga posgrado y vuélvase más competitivo</h4>
                <p><strong>Fuente:</strong>El Economista</p>
                <p>4-mar-2014</p>
                <p>Una amplia preparación le puede dar más y mejores oportunidades de trabajo.<a href="/es_mx/empleo/haga_posgrado_vuelvase_mas_competitivo">Leer más</a></p>
              </li>

              <li ><h4>Exámenes, poco usados para reclutar</h4>
                <p><strong>Fuente:</strong>CNN Expansión</p>
                <p>28-feb-2014</p>
                <p>México necesita profesionalizar sus sistemas de selección de personal y no usar recomendaciones.<a href="/es_mx/empleo/examenes_poco_usados_reclutar">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Cinco cosas que puedes aprender del primer empleo de Steve Jobs</h4>
                <p><strong>Fuente:</strong>Dinero en Imagen</p>
                <p>26-feb-2014</p>
                <p>Sin duda, algo que siempre tuvo Steve Jobs fue iniciativa, así lo demuestra la anécdota que narra cómo obtuvo su primer empleo como un becario.<a href="/es_mx/empleo/cinco_cosas_puedes_aprender_primer_empleo_steve_Jobs">Leer más</a></p>
              </li>

              <li ><h4>Cinco habilidades que impresionarán a cualquier reclutador</h4>
                <p><strong>Fuente:</strong>Universia</p>
                <p>25-feb-2014</p>
                <p>En esta nota te presentamos las habilidades más buscadas por los reclutadores de nuevos talentos.<a href="/es_mx/empleo/cinco_habilidades_impresionaran_cualquier_reclutador">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Cuatro preguntas clave para un reclutador</h4>
                <p><strong>Fuente:</strong>CNN Expansión</p>
                <p>24-feb-2014</p>
                <p>Los cuestionamientos buscan conocer las debilidades y fortalezas de un candidato; a los 15 minutos de la entrevista, el reclutador puede saber si la persona funciona para el puesto.<a href="/es_mx/empleo/cuatro_preguntas_clave_reclutador">Leer más</a></p>
              </li>

              <li ><h4>Guía para no cometer errores durante una entrevista laboral</h4>
                <p><strong>Fuente:</strong>El Economista</p>
                <p>21-feb-2014</p>
                <p>Preste atención a las recomendaciones que comparten los expertos para no fracasar en las entrevistas de empleo.<a href="/es_mx/empleo/guia_no_cometer_errores_durante_entrevista_laboral">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Las referencias laborales son de gran importancia</h4>
                <p><strong>Fuente:</strong>El Economista</p>
                <p>5-feb-2014</p>
                <p>Expertos advierten que seis de cada 10 empleadores han rechazado a un candidato por una mala referencia laboral.<a href="/es_mx/empleo/referencias_laborales_son_gran_importancia">Leer más</a></p>
              </li>




            </ul>
          </div>

          <div class="pagArticulo" style="display: none">
            <ul class="tema">


              <li ><h4>Dos claves de la motivación laboral</h4>
                <p><strong>Fuente:</strong>Soy Entrepreneur</p>
                <p>4-feb-2014</p>
                <p>Para que tus empleados den su mejor esfuerzo y se comprometan con el bienestar de la empresa, debes ofrecerles autonomía y transparencia.<a href="/es_mx/empleo/dos_claves_motivacion_laboral">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>La fórmula para alcanzar éxito laboral</h4>
                <p><strong>Fuente:</strong>CNN Expansión</p>
                <p>31-ene-2014</p>
                <p>La capacitación ayuda a los profesionistas a crecer; antes de saber qué conocimientos mejorar, es necesario fijarse un plan de carrera.<a href="/es_mx/empleo/formula_alcanzar_exito_laboral">Leer más</a></p>
              </li>

              <li ><h4>¿Qué son las startup y a quién le interesan?</h4>
                <p><strong>Fuente:</strong>Forbes</p>
                <p>31-ene-2014</p>
                <p>Ideas de negocio innovadoras que requieren poca inversión y que ofrecen un crecimiento muy alto. Conoce más de ellas.<a href="/es_mx/empleo/que_son_startup_quien_interesan">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Las habilidades más buscadas por los reclutadores</h4>
                <p><strong>Fuente:</strong>Forbes</p>
                <p>29-ene-2014</p>
                <p>Encontrar candidatos dispuestos a asimilar nuevas habilidades es más importante que contratar a especialistas con experiencia.<a href="/es_mx/empleo/habilidades_mas_buscadas_reclutadores">Leer más</a></p>
              </li>

              <li ><h4>25 preguntas extrañas en entrevistas de trabajo</h4>
                <p><strong>Fuente:</strong>Soy Entrepreneur</p>
                <p>27-ene-2014</p>
                <p>Conoce las preguntas poco comunes que grandes empresas hacen a sus candidatos a empleados para descubrir su potencial y personalidad.<a href="/es_mx/empleo/25_preguntas_extranas_entrevistas_trabajo">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Experiencia, requisito en 80 por ciento de plazas</h4>
                <p><strong>Fuente:</strong>CNN Expansión</p>
                <p>24-ene-2014</p>
                <p>Sólo uno de cada 10 estudiantes consigue empleo inmediatamente después de terminar la carrera; una solución para integrarlos es darle más valor a la figura del practicante en las empresas.<a href="/es_mx/empleo/experiencia_requisito_80_por_ciento_plazas">Leer más</a></p>
              </li>

              <li ><h4>E-mail puede restar valor a su currículum</h4>
                <p><strong>Fuente:</strong>El Economista</p>
                <p>23-ene-2014</p>
                <p>El e-mail, aunque pareciera ser un dato irrelevante, también entrega información de la persona y puede llegar a ser crucial a la hora de avanzar a la siguiente fase.<a href="/es_mx/empleo/email_puede_restar_valor_curriculum">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>¿Buscas trabajo? Sigue estas diez recomendaciones</h4>
                <p><strong>Fuente:</strong>Terra</p>
                <p>13-ene-2014</p>
                <p>Toma en consideración algunos puntos que te harán resaltar a la hora de mandar tu currículum a una empresa o presentarte a una entrevista.<a href="/es_mx/empleo/buscas_trabajo_sigue_estas_diez_recomendaciones">Leer más</a></p>
              </li>

              <li ><h4>Semana laboral de tres días ¿factible?</h4>
                <p><strong>Fuente:</strong>CNN Expansión</p>
                <p>24-dic-2013</p>
                <p>Reducir costos y lograr un mayor balance entre la vida personal y el trabajo son algunos de los beneficios que generaría una semana laboral más corta.<a href="/es_mx/empleo/semana_laboral_tres_dias_factible">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Las mejores apps para buscar y encontrar empleo</h4>
                <p><strong>Fuente:</strong>Computer Hoy</p>
                <p>20-dic-2013</p>
                <p>Tu smartphone o tablet están dispuestos a ayudarte mediante las apps para encontrar trabajo.<a href="/es_mx/empleo/mejores_apps_buscar_encontrar_empleo">Leer más</a></p>
              </li>




            </ul>
          </div>

          <div class="pagArticulo" style="display: none">
            <ul class="tema">


              <li ><h4>Cómo superar un fracaso laboral</h4>
                <p><strong>Fuente:</strong>Universia</p>
                <p>16-dic-2013</p>
                <p>Aprende a superar los fracasos personales y laborales siguiendo esta serie de consejos.<a href="/es_mx/empleo/como_superar_fracaso_laboral">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Entrevista laboral: cinco preguntas que no debes dejar de hacer</h4>
                <p><strong>Fuente:</strong>Universia</p>
                <p>5-dic-2013</p>
                <p>La entrevista es el momento ideal para eliminar las dudas que puedan surgirte y reafirmar que ese es el empleo que deseas.<a href="/es_mx/empleo/entrevista_laboral_cinco_preguntas_no_debes_dejar_hacer">Leer más</a></p>
              </li>

              <li ><h4>Errores comunes al buscar empleo</h4>
                <p><strong>Fuente:</strong>CNN Expansión</p>
                <p>4-dic-2013</p>
                <p>No confirmar un interés permanente tras la entrevista es uno de los errores más frecuentes al buscar empleo.<a href="/es_mx/empleo/errores_comunes_buscar_empleo">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Radiografía de la fuerza laboral en México</h4>
                <p><strong>Fuente:</strong>Alto Nivel</p>
                <p>29-nov-2013</p>
                <p>Este país se mueve por la generación Y y el 45 por ciento de la gente no trabaja en áreas de su carrera.<a href="/es_mx/empleo/radiografia_fuerza_laboral_mexico">Leer más</a></p>
              </li>

              <li ><h4>Exámenes psicométricos: 10 tips para resolverlos</h4>
                <p><strong>Fuente:</strong>OCC Mundial</p>
                <p>25-nov-2013</p>
                <p>El examen psicométrico se aplica para llenar huecos y en la mayoría de los casos para evaluar tu personalidad.<a href="/es_mx/empleo/examenes_psicometricos_diez_tips_resolverlos">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Los puntos débiles de un reclutador</h4>
                <p><strong>Fuente:</strong>CNN Expansión</p>
                <p>22-nov-2013</p>
                <p>En una entrevista de trabajo, el empleador puede tener ciertos prejuicios que puedes aprovechar.<a href="/es_mx/empleo/puntos_debiles_reclutador">Leer más</a></p>
              </li>

              <li ><h4>Tips para entrevistas en inglés</h4>
                <p><strong>Fuente:</strong>ZonaJobs</p>
                <p>22-nov-2013</p>
                <p>Tranquilidad, hablar despacio, practicar preguntas generales, son algunas recomendaciones que debes tomar en cuenta.<a href="/es_mx/empleo/tips_entrevistas_ingles">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Las ocho prestaciones laborales más valoradas</h4>
                <p><strong>Fuente:</strong>OCC Mundial</p>
                <p>21-nov-2013</p>
                <p>Existen prestaciones que por ley deben ser otorgadas a los trabajadores al ser contratados, conócelas.<a href="/es_mx/empleo/ocho_prestaciones_laborales_mas_valoradas">Leer más</a></p>
              </li>

              <li ><h4>Cinco errores al enviar tu currículum</h4>
                <p><strong>Fuente:</strong>OCC Mundial</p>
                <p>6-nov-2013</p>
                <p>Identifica los errores más comunes al enviar tu currículum y eleva las posibilidades de que se interensen en conocerte.<a href="/es_mx/empleo/cinco_errores_enviar_curriculum">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Todo lo que debes hacer para triunfar en una entrevista de trabajo</h4>
                <p><strong>Fuente:</strong>Expansión</p>
                <p>4-nov-2013</p>
                <p>Cada detalle cuenta en un proceso de selección: tu imagen, tus gestos, la forma de hablar, etc... Es uno de tus grandes momentos.<a href="/es_mx/empleo/todo_debes_hacer_triunfar_entrevista_trabajo">Leer más</a></p>
              </li>




            </ul>
          </div>

          <div class="pagArticulo" style="display: none">
            <ul class="tema">


              <li ><h4>Renueva empleo en un tris</h4>
                <p><strong>Fuente:</strong>El Universal</p>
                <p>31-oct-2013</p>
                <p>Hacer cambios en la trayectoria profesional es clave para ascender en tu carrera o ser un emprendedor.<a href="/es_mx/empleo/renueva_empleo_tris">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Seis razones para rechazar un trabajo</h4>
                <p><strong>Fuente:</strong>CNN Expansión</p>
                <p>28-oct-2013</p>
                <p>El salario no es lo único a evaluar en una oferta, también analiza si cumple con tu plan laboral.<a href="/es_mx/empleo/seis_razones_rechazar_trabajo">Leer más</a></p>
              </li>

              <li ><h4>Profesionales TIC cada vez más demandados</h4>
                <p><strong>Fuente:</strong>Universia</p>
                <p>23-oct-2013</p>
                <p>Trabajadores de 32 países aseguran que sus empresas les exigen cada vez más conocimientos relacionados con las TIC.<a href="/es_mx/empleo/profesionales_tic_cada_vez_mas_demandados">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>10 consejos básicos para buscar empleo en redes sociales</h4>
                <p><strong>Fuente:</strong>Cuarto Poder</p>
                <p>17-oct-2013</p>
                <p>Te ofrecemos diez pistas para venderte mejor en internet y convertirte en el candidato ideal para cualquier empresa.<a href="/es_mx/empleo/diez_consejos_basicos_buscar_empleo_redes_sociales">Leer más</a></p>
              </li>

              <li ><h4>Seis tips para convencer al reclutador</h4>
                <p><strong>Fuente:</strong>CNN Expansión</p>
                <p>16-oct-2013</p>
                <p>Los reclutadores toman en cuenta no sólo la parte intelectual también las emociones de un candidato.<a href="/es_mx/empleo/seis_tips_convencer_reclutador">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Escoge la mejor vía para acceder a un empleo</h4>
                <p><strong>Fuente:</strong>Expansión</p>
                <p>15-oct-2013</p>
                <p>Saber cómo funcionan los intermediarios laborales es básico para conseguir un trabajo.<a href="/es_mx/empleo/escoge_mejor_via_acceder_empleo">Leer más</a></p>
              </li>

              <li ><h4>Antes de dar el sí, analice a detalle una oferta laboral</h4>
                <p><strong>Fuente:</strong>El Economista</p>
                <p>14-oct-2013</p>
                <p>Antes de dar el sí definitivo, considere algunos consejos que le dan expertos de ManpowerGroup y OCCMundial.<a href="/es_mx/empleo/antes_dar_si_analice_detalle_oferta_laboral">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Cinco cosas que debes hacer luego de aceptar un nuevo trabajo</h4>
                <p><strong>Fuente:</strong>Universia</p>
                <p>11-oct-2013</p>
                <p>Una de las primeras cosas que debes hacer antes de aceptar una nueva propuesta de trabajo es hablar con tu actual jefe.<a href="/es_mx/empleo/cinco_cosas_debes_hacer_luego_aceptar_nuevo_trabajo">Leer más</a></p>
              </li>

              <li ><h4>Encuentra empleo en tu smartphone</h4>
                <p><strong>Fuente:</strong>CNN Expansión</p>
                <p>10-oct-2013</p>
                <p>Según Our Mobile Planet; el uso de aplicaciones móviles tiene ventajas pero requiere mayor proactividad del candidato.<a href="/es_mx/empleo/encuentra_empleo_smartphone">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Se buscan profesionales excelentes en capacidades digitales</h4>
                <p><strong>Fuente:</strong>Expansión</p>
                <p>9-oct-2013</p>
                <p>Nueve de cada diez empleados reconocen que se les exige más que hace cinco años.<a href="/es_mx/empleo/se_buscan_profesionales_excelentes_capacidades_digitales">Leer más</a></p>
              </li>




            </ul>
          </div>

          <div class="pagArticulo" style="display: none">
            <ul class="tema">


              <li ><h4>10 razones para rechazar una oferta de empleo</h4>
                <p><strong>Fuente:</strong>Alto Nivel</p>
                <p>4-oct-2013</p>
                <p>Siempre vale la pena analizar si realmente esa oferta laboral traerá beneficios a futuro.<a href="/es_mx/empleo/diez_razones_rechazar_oferta_empleo">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Desaprovechado, el mercado laboral por internet</h4>
                <p><strong>Fuente:</strong>El Informador</p>
                <p>3-oct-2013</p>
                <p>En los últimos tres años se han triplicado las exploraciones de trabajo a través de portales electrónicos.<a href="/es_mx/empleo/desaprovechado_mercado_laboral_internet">Leer más</a></p>
              </li>

              <li ><h4>Utiliza 50 por ciento de los mexicanos su smartphone para buscar empleo</h4>
                <p><strong>Fuente:</strong>El Financiero</p>
                <p>2-oct-2013</p>
                <p>Cerca del 80 por ciento de los mexicanos no cuentan con currículum vitae.<a href="/es_mx/empleo/utiliza_50_por_ciento_mexicanos_smartphone_buscar_empleo">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Muchos empleos en pocos años ¿conviene?</h4>
                <p><strong>Fuente:</strong>CNN Expansión</p>
                <p>1-oct-2013</p>
                <p>Tus saltos laborales pueden ser un elemento a tu favor, siempre y cuando sepas cómo explicarlos.<a href="/es_mx/empleo/muchos_empleos_pocos_anos_conviene">Leer más</a></p>
              </li>

              <li ><h4>¿Cómo buscar trabajo en internet?</h4>
                <p><strong>Fuente:</strong>Dinero en Imagen</p>
                <p>27-sep-2013</p>
                <p>Un buen candidato requiere de esfuerzos, estos son cinco consejos a la hora de buscar trabajo en internet.<a href="/es_mx/empleo/Como_buscar_trabajo_internet">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Cómo poder enfrentar su retiro laboral</h4>
                <p><strong>Fuente:</strong>El Economista</p>
                <p>26-sep-2013</p>
                <p>Especialistas recomiendan a las personas próximas a jubilarse que se preparen para ese momento por medio de diplomados.<a href="/es_mx/empleo/como_poder_enfrentar_retiro_laboral">Leer más</a></p>
              </li>

              <li ><h4>17 maneras de hacerte indispensable en el trabajo</h4>
                <p><strong>Fuente:</strong>Forbes México</p>
                <p>25-sep-2013</p>
                <p>No importa el giro de tu empresa, ser un elemento importante en el equipo es siempre de ayuda para ascender en nuestra carrera profesional.<a href="/es_mx/empleo/diecisiete_maneras_hacerte_indispensable_trabajo">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Haz que tus contactos te ayuden a encontrar empleo</h4>
                <p><strong>Fuente:</strong>OCC Mundial</p>
                <p>20-sep-2013</p>
                <p>Construir una red de contactos puede ser de mucha utilidad cuando buscas nuevas oportunidades laborales. Aquí te decimos cómo.<a href="/es_mx/empleo/haz_contactos_ayuden_encontrar_empleo">Leer más</a></p>
              </li>

              <li ><h4>México necesita más técnicos: Coparmex</h4>
                <p><strong>Fuente:</strong>CNN Expansión</p>
                <p>19-sep-2013</p>
                <p>Es necesario crear un mecanismo de seguimiento entre gobierno, escuelas y empresas para formar los recursos humanos necesarios.<a href="/es_mx/empleo/mexico_necesita_mas_tecnicos_coparmex">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>10 metas laborales para antes de los 40</h4>
                <p><strong>Fuente:</strong>CNN Expansión</p>
                <p>18-sep-2013</p>
                <p>Después de una década de vida laboral, lo ideal es tener claro lo que quieres y lo que disfrutas.<a href="/es_mx/empleo/diez_metas_laborales_antes_40">Leer más</a></p>
              </li>




            </ul>
          </div>

          <div class="pagArticulo" style="display: none">
            <ul class="tema">


              <li ><h4>10 cosas que debes hacer si estás desempleado</h4>
                <p><strong>Fuente:</strong>Forbes México</p>
                <p>13-sep-2013</p>
                <p>Son tiempos difíciles pero en lo que llega esa nueva oportunidad, no desaproveches tus días. Aquí te decimos qué hacer.<a href="/es_mx/empleo/diez_cosas_debes_hacer_estas_desempleado">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Los cinco perfiles digitales más empleados en México</h4>
                <p><strong>Fuente:</strong>Alto Nivel</p>
                <p>10-sep-2013</p>
                <p>Muchos perfiles laborales tradicionales evolucionaron a las exigencias de la era digital y la consultora. Conoce cinco ejemplos.<a href="/es_mx/empleo/cinco_perfiles_digitales_mas_empleados_mexico">Leer más</a></p>
              </li>

              <li ><h4>Conoce las trampas de un reclutador</h4>
                <p><strong>Fuente:</strong>CNN Expansión</p>
                <p>9-sep-2013</p>
                <p>En una entrevista laboral algunas preguntas buscan conocer el perfil emocional de un candidato.<a href="/es_mx/empleo/conoce_trampas_reclutador">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Guía para encontrar trabajo a través de Twitter</h4>
                <p><strong>Fuente:</strong>Alto Nivel</p>
                <p>5-sep-2013</p>
                <p>Encontrar el trabajo que tanto has deseado puede ser posible en 140 caracteres. Cinco claves para encontrar empleo vía Twitter.<a href="/es_mx/empleo/guia_encontrar_trabajo_traves_twitter">Leer más</a></p>
              </li>

              <li ><h4>Los cinco errores fatales que puede tener tu currículum</h4>
                <p><strong>Fuente:</strong>Universia</p>
                <p>4-sep-2013</p>
                <p>Errores en el momento de elaborar tu currículum pueden estar comprometiendo tu futuro laboral.<a href="/es_mx/empleo/cinco_errores_fatales_puede_tener_tu_curriculum">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Contrato de trabajo ¿qué debes saber antes de firmarlo?</h4>
                <p><strong>Fuente:</strong>OCC Mundial</p>
                <p>3-sep-2013</p>
                <p>Al iniciar una relación laboral existe un documento que tiene como objetivo formalizar el acuerdo entre la empresa y el empleado.<a href="/es_mx/empleo/contrato_trabajo_debes_saber_antes_firmarlo">Leer más</a></p>
              </li>

              <li ><h4>¿Cómo aprovechar las malas experiencias laborales y convertirlas en oportunidades?</h4>
                <p><strong>Fuente:</strong>Universia</p>
                <p>2-sep-2013</p>
                <p>Climas hostiles, malos tratos, inconformismo salarial, son algunos aspectos que provocan malas experiencias laborales.<a href="/es_mx/empleo/como_aprovechar_malas_experiencias_laborales_convertirlas_oportunidades">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Lo que nunca debes responder en una entrevista de trabajo</h4>
                <p><strong>Fuente:</strong>Universia</p>
                <p>23-ago-2013</p>
                <p>Expertos dan a conocer una serie de pautas que debemos seguir para conseguir el puesto para el que nos estamos postulando. Conocélas.<a href="/es_mx/empleo/nunca_debes_responder_entrevista_trabajo">Leer más</a></p>
              </li>

              <li ><h4>¿Buscas trabajo? Prepara tu CV y tu actitud</h4>
                <p><strong>Fuente:</strong>Forbes México</p>
                <p>22-ago-2013</p>
                <p>Las compañías se inclinan por el mejor candidato, las habilidades como el trabajo en equipo y la actitud positiva son prioritarias.<a href="/es_mx/empleo/buscas_trabajo_prepara_cv_y_actitud">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Usando la reputación en línea para encontrar empleo</h4>
                <p><strong>Fuente:</strong>Forbes México</p>
                <p>21-ago-2013</p>
                <p>El hacerse visible online y desarrollar una red de contactos, es indispensable en estos días.<a href="/es_mx/empleo/usando_reputacion_linea_encontrar_empleo">Leer más</a></p>
              </li>




            </ul>
          </div>

          <div class="pagArticulo" style="display: none">
            <ul class="tema">


              <li ><h4>Cómo manejar los nervios en una entrevista de trabajo</h4>
                <p><strong>Fuente:</strong>Alto Nivel</p>
                <p>13-ago-2013</p>
                <p>Tanto reclutador como candidato deben enfentar el nerviosismo implícito en una entrevista.<a href="/es_mx/empleo/como_manejar_nervios_entrevista_trabajo">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Hallar empleo ¿un martirio o recompensa?</h4>
                <p><strong>Fuente:</strong>El Economista</p>
                <p>13-ago-2013</p>
                <p>Si estás buscando empleo, te invitamos a conocer las recomendaciones que hace el autor Richard N. Bolles.<a href="/es_mx/empleo/hallar_empleo_martirio_recompensa">Leer más</a></p>
              </li>

              <li ><h4>10 consejos para recién egresados que buscan empleo</h4>
                <p><strong>Fuente:</strong>OCC Mundial</p>
                <p>9-ago-2013</p>
                <p>Salir de la universidad y enfrentar una realidad muy diferente a la esperada es algo común para muchos jóvenes.<a href="/es_mx/empleo/diez_consejos_recien_egresados_buscan_empleo">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>¿Desempleado? Convierte la frustración en motivación</h4>
                <p><strong>Fuente:</strong>Alto Nivel</p>
                <p>6-ago-2013</p>
                <p>Hasta cierto punto es lógico deprimirse cuando se está desempleado, pero no debe superarte.<a href="/es_mx/empleo/desempleado_convierte_frustracion_motivacion">Leer más</a></p>
              </li>

              <li ><h4>Certificación en TI ¿el nuevo requisito laboral?</h4>
                <p><strong>Fuente:</strong>Forbes México</p>
                <p>5-ago-2013</p>
                <p>Las empresas buscan solicitantes certificados en diversas áreas de TI como la gestión de redes o virtualización de escritorios.<a href="/es_mx/empleo/certificacion_ti_nuevo_requisito_laboral">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Las curiosidades de Google a la hora de contratar personal</h4>
                <p><strong>Fuente:</strong>Universia</p>
                <p>5-ago-2013</p>
                <p>El vicepresidente de recursos humanos de Google, Laszlo Bock, compartió algunas curiosidades de la empresa a la hora de contratar personal.<a href="/es_mx/empleo/curiosidades_google_hora_contratar_personal">Leer más</a></p>
              </li>

              <li ><h4>¿Qué tipo de trabajo elegir de acuerdo a tus motivaciones?</h4>
                <p><strong>Fuente:</strong>ZonaJobs</p>
                <p>29-jul-2013</p>
                <p>Conoce cuatro perfiles de empleados, diseñados de acuerdo a los impulsores que tienen las personas para buscar trabajo.<a href="/es_mx/empleo/tipo_trabajo_elegir_acuerdo_tus_motivaciones">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Cuidado con las vacantes de ensueño</h4>
                <p><strong>Fuente:</strong>CNN Expansión</p>
                <p>14-jul-2013</p>
                <p>Si buscas empleo, desconfía de ofertas que ofrecen altas remuneraciones sin mucho esfuerzo; las empresas serias no piden dinero durante el proceso de reclutamiento.<a href="/es_mx/empleo/cuidado_vacantes_ensueno">Leer más</a></p>
              </li>

              <li ><h4>Internet, clave para los freelance</h4>
                <p><strong>Fuente:</strong>CNN Expansión</p>
                <p>11-jul-2013</p>
                <p>Se estima que 10 millones de profesionales en el mundo encuentran trabajos independientes online.<a href="/es_mx/empleo/internet_clave_freelance">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Cinco tips de seguridad al buscar empleo</h4>
                <p><strong>Fuente:</strong>El Economista</p>
                <p>8-jul-2013</p>
                <p>Especialistas realizan una lista de cinco tips que hay que tomar en cuenta en el momento de buscar trabajo, para evitar ser víctima de un fraude virtual.<a href="/es_mx/empleo/cinco_tips_seguridad_buscar_empleo">Leer más</a></p>
              </li>




            </ul>
          </div>

          <div class="pagArticulo" style="display: none">
            <ul class="tema">


              <li ><h4>Buscando empleo: el lenguaje corporal puede ser un factor decisivo</h4>
                <p><strong>Fuente:</strong>Ganadería México</p>
                <p>6-jul-2013</p>
                <p>No existirá una segunda oportunidad para causar una primera buena impresión. La atención y concentración de tu entrevistador será máxima en los primeros cinco minutos, captando mucha información a partir de muy pocos datos.<a href="/es_mx/empleo/buscando_empleo_lenguaje_corporal_puede_ser_factor_decisivo">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Siete errores de un candidato que busca empleo</h4>
                <p><strong>Fuente:</strong>Alto Nivel</p>
                <p>5-jul-2013</p>
                <p>Una vacante, 200 candidatos, reclutadores contra reloj, 10 segundos para revisar cada CV y para ti: una oportunidad. Aprovéchala y no cometas estos errores.<a href="/es_mx/empleo/siete_errores_candidato_busca_empleo">Leer más</a></p>
              </li>

              <li ><h4>Los 10 principios para tener el CV al 100 por ciento</h4>
                <p><strong>Fuente:</strong>ZonaJobs</p>
                <p>4-jul-2013</p>
                <p>Entre todos los consejos y tips que existen sobre cómo hacer un buen CV, hay algunos que son imprescindibles y que engloban al resto. Te presentamos una lista de conceptos que te ayudarán a realizar un currículum profesional.<a href="/es_mx/empleo/10_principios_tener_cv_100_por_ciento">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Creencias falsas de los reclutadores</h4>
                <p><strong>Fuente:</strong>Publimetro</p>
                <p>2-jul-2013</p>
                <p>Quienes efectúan las entrevistas de trabajo no siempre hacen la mejor elección de un nuevo empleado porque existen factores ajenos a su labor.<a href="/es_mx/empleo/creencias_falsas_reclutadores">Leer más</a></p>
              </li>

              <li ><h4>El trabajo que viene: 10 profesiones que arrasarán en 2020</h4>
                <p><strong>Fuente:</strong>El Economista America</p>
                <p>1-jul-2013</p>
                <p>Fernando Calderón, director de Mecadotecnia y Relaciones Públicas de OCC  Mundial, enlista 10 profesiones que destacarán en el 2020.<a href="/es_mx/empleo/trabajo_viene_diez_profesiones_arrasaran_2020">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>La comunicación no verbal ¿cuenta en una entrevista?</h4>
                <p><strong>Fuente:</strong>ZonaJobs</p>
                <p>1-jul-2013</p>
                <p>Es importante prestar atención a las acciones 'no verbales' porque a través de este lenguaje un reclutador valora habilidades de comunicación, confianza y capacidad de generar empatía.<a href="/es_mx/empleo/comunicacion_no_verbal_cuenta__entrevista">Leer más</a></p>
              </li>

              <li ><h4>Las empresas de marketing y publicidad se lanzan a la búsqueda de talentos</h4>
                <p><strong>Fuente:</strong>Puro Marketing</p>
                <p>26-jun-2013</p>
                <p>Una encuesta realizada en Estados Unidos refleja que las empresas de marketing y publicidad necesitan reforzar sus departamentos profesionales.<a href="/es_mx/empleo/empresas_marketing_publicidad_se_lanzan_busqueda_talentos">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>¿De verdad sabes cómo se busca un empleo?</h4>
                <p><strong>Fuente:</strong>Expansión</p>
                <p>25-jun-2013</p>
                <p>Buscar trabajo ya no es lo que era y para encontrar la mejor opción hay que reinventarse.<a href="/es_mx/empleo/de_verdad_sabes_como_busca_empleo">Leer más</a></p>
              </li>

              <li ><h4>La web es la principal reclutadora de mandos medios en empresas</h4>
                <p><strong>Fuente:</strong>El País</p>
                <p>24-jun-2013</p>
                <p>Las redes sociales llegaron para complementar la búsqueda más formal, en procesos serios, los reclutadores utilizarán las redes sociales como un apoyo a los métodos más tradicionales, no se basarán sólo en ellos.<a href="/es_mx/empleo/web_es_principal_reclutadora_mandos_medios_empresas">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>¿Quieres trabajo? apuesta a lo que buscan los reclutadores</h4>
                <p><strong>Fuente:</strong>Forbes</p>
                <p>20-jun-2013</p>
                <p>Sentirte identificado con los valores y objetivos de la empresa, son las principales características que buscan empresas al momento de emplear a un profesionista.<a href="/es_mx/empleo/quieres_trabajo_apuesta_buscan_reclutadores">Leer más</a></p>
              </li>




            </ul>
          </div>

          <div class="pagArticulo" style="display: none">
            <ul class="tema">


              <li ><h4>10 pasos indispensables en tu búsqueda de empleo</h4>
                <p><strong>Fuente:</strong>Universia</p>
                <p>19-jun-2013</p>
                <p>Idiomas, capacidad de aprendizaje e iniciativa, es lo primero que buscan las empresas en un candidato.<a href="/es_mx/empleo/diez_pasos_indispensables_busqueda_empleo">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Cómo escribir una carta de presentación</h4>
                <p><strong>Fuente:</strong>Hacer currículum</p>
                <p>19-jun-2013</p>
                <p>Una carta de presentación puede llegar a ser incluso, aún más importante que el CV, pues de la primera impresión que ésta genere, dependerá el interés del reclutador en el currículum.<a href="/es_mx/empleo/como_escribir_carta_presentacion">Leer más</a></p>
              </li>

              <li ><h4>Encuentra trabajo según tus talentos</h4>
                <p><strong>Fuente:</strong>CNN Expansión</p>
                <p>19-jun-2013</p>
                <p>Invertir en detectar para qué eres bueno te permite buscar empleo de forma más acertada; si trabajas con tu talento natural rindes más y evitas frustraciones laborales, dicen expertos.<a href="/es_mx/empleo/encuentra_trabajo_segun_talentos">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Matemáticas ¿vía hacia buenos trabajos?</h4>
                <p><strong>Fuente:</strong>CNN Expansión</p>
                <p>19-jun-2013</p>
                <p>La fobia que rodea a esta disciplina, hace que jóvenes eviten carreras vinculadas a los números; los universitarios no consideran su importancia como fuente de empleos, dicen expertos.<a href="/es_mx/empleo/matematicas_via_buenos_trabajos">Leer más</a></p>
              </li>

              <li ><h4>¿Cómo hacer una entrevista de trabajo por Skype?</h4>
                <p><strong>Fuente:</strong>La información</p>
                <p>19-jun-2013</p>
                <p>Configura tu cuenta, evita distracciones, apaga el teléfono y mira siempre a la cámara y no a la pantalla.<a href="/es_mx/empleo/como_hacer_entrevista_trabajo_skype">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>¿Cómo prepararse para una entrevista laboral?</h4>
                <p><strong>Fuente:</strong>Sitio Andino</p>
                <p>19-jun-2013</p>
                <p>La clave para que el empleador te considere un candidato idóneo para el puesto es ir preparado a la cita.<a href="/es_mx/empleo/como_prepararse_entrevista_laboral">Leer más</a></p>
              </li>

              <li ><h4>¿Tu vestimenta dice contrátame?</h4>
                <p><strong>Fuente:</strong>Zona Jobs</p>
                <p>19-jun-2013</p>
                <p>La vestimenta y la imagen proyectada son factores determinantes en el proceso de búsqueda laboral.<a href="/es_mx/empleo/vestimenta_dice_contratame">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Seis ideas para la productividad 2.0: tómate tu vida profesional como algo personal</h4>
                <p><strong>Fuente:</strong>Generadores de Mejoras Contínuas en Recursos Humanos</p>
                <p>18-jun-2013</p>
                <p>Te damos algunas sugerencias de cómo trabajar mejor, disfrutar tu empleo y cómo  concentrarte en tus metas para alcanzarlas.<a href="/es_mx/empleo/seis_ideas_productividad_20_tomate_vida_profesional_algo_personal">Leer más</a></p>
              </li>

              <li ><h4>¿Buscas empleo? Organízate</h4>
                <p><strong>Fuente:</strong>IDC Online</p>
                <p>17-jun-2013</p>
                <p>Existen diez hábitos que pueden impedirte conseguir el puesto de trabajo que deseas.<a href="/es_mx/empleo/buscas_empleo_organizate">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Tips de inserción para mayores de 45 años</h4>
                <p><strong>Fuente:</strong>ZonaJobs</p>
                <p>14-jun-2013</p>
                <p>Cuando la edad es un factor considerable en la búsqueda de empleo, es importante adoptar una actitud favorable,  pasar de víctima a protagonista para hacer que su realidad cambie.<a href="/es_mx/empleo/tips_insercion_mayores_45_anos">Leer más</a></p>
              </li>




            </ul>
          </div>

          <div class="pagArticulo" style="display: none">
            <ul class="tema">


              <li ><h4>Cómo definir mi perfil laboral</h4>
                <p><strong>Fuente:</strong>Generadores de Mejoras Contínuas en Recursos Humanos</p>
                <p>13-jun-2013</p>
                <p>Esta parte de tu hoja de vida es clave para que un entrevistador te llame a entrevista y te tenga en cuenta en un proceso de selección.<a href="/es_mx/empleo/como_definir_perfil_laboral">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Profesiones 2.0 Extraños puestos en los organigramas</h4>
                <p><strong>Fuente:</strong>La Jornada</p>
                <p>11-jun-2013</p>
                <p>Les pagan por tuitear, subir fotos o contar los me gusta en Facebook y por eso algunos no los toman en serio. Son los pioneros de las profesiones 2.0, surgidas hace menos de cinco años con el boom de Internet y las redes sociales.<a href="/es_mx/empleo/profesiones_20_extranos_puestos_organigramas">Leer más</a></p>
              </li>

              <li ><h4>Las disciplinas que más piden las empresas</h4>
                <p><strong>Fuente:</strong>Universia</p>
                <p>10-jun-2013</p>
                <p>¿Sabías que ingeniería civil, informática, topografía y geodesia, son las disciplinas más solicitadas por las empresas de infraestructura?<a href="/es_mx/empleo/disciplinas_mas_piden_empresas">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Prepárate para una entrevista de trabajo en inglés</h4>
                <p><strong>Fuente:</strong>OCC Mundial</p>
                <p>7-jun-2013</p>
                <p>Si te postulaste para un empleo que requiere el dominio del idioma inglés o lo agregaste en tu CV como uno de tus conocimientos, debes estar preparado para una posible entrevista en ese idioma.<a href="/es_mx/empleo/preparate_entrevista_trabajo_ingles">Leer más</a></p>
              </li>

              <li ><h4>Conoce todo lo que afecta tu CV</h4>
                <p><strong>Fuente:</strong>IDC Online</p>
                <p>6-jun-2013</p>
                <p>Considera todos los detalles dentro de tus antecedentes laborales, que pueden llegar a impedirte que consigas un nuevo empleo.<a href="/es_mx/empleo/conoce_todo_afecta_cv">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>¿Sabes cuándo callar en una entrevista de trabajo?</h4>
                <p><strong>Fuente:</strong>ZonaJobs</p>
                <p>5-jun-2013</p>
                <p>El tiempo de una entrevista es valioso, tanto para hacer empatía con el reclutador como para que te des a conocer. Aprovechalo mejor con las siguientes recomendaciones.<a href="/es_mx/empleo/sabes_callar_entrevista_trabajo">Leer más</a></p>
              </li>

              <li ><h4>¿Cómo combinar el trabajo con el estudio?</h4>
                <p><strong>Fuente:</strong>Dinero en Imagen</p>
                <p>4-jun-2013</p>
                <p>Los jóvenes y profesionistas que combinan estas actividades se enfrentan al reto de hacer rendir su tiempo para cumplir con todas las responsabilidades.<a href="/es_mx/empleo/como_combinar_trabajo_estudio">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Los cinco fallos del currículum que molestan a las empresas</h4>
                <p><strong>Fuente:</strong>OCC Mundial</p>
                <p>3-jun-2013</p>
                <p>No sólo errores de contenido afectan tu CV, detalles de formato o en el envío pueden intervenir para que te consideren o no como un posible candidato.<a href="/es_mx/empleo/cinco_fallos_curriculum_molestan_empresas">Leer más</a></p>
              </li>

              <li ><h4>Las cinco preguntas de oro en una entrevista</h4>
                <p><strong>Fuente:</strong>OCC Mundial</p>
                <p>31-may-2013</p>
                <p>Las preguntas que te realiza el reclutador no son las únicas importantes en una entrevista de trabajo, las que tienes que hacer tú, también lo son.<a href="/es_mx/empleo/cinco_preguntas_oro_entrevista">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Contactos, la puerta al próximo empleo</h4>
                <p><strong>Fuente:</strong>CNN Expansión</p>
                <p>30-may-2013</p>
                <p>Armar una red de conocidos es la vía más efectiva para buscar trabajo, señalan estudios; internet es una gran vía de empleo, pero no garantiza conseguir una entrevista con el reclutador.<a href="/es_mx/empleo/contactos_puerta_proximo_empleo">Leer más</a></p>
              </li>




            </ul>
          </div>

          <div class="pagArticulo" style="display: none">
            <ul class="tema">


              <li ><h4>Mentir en el currículum sólo aumenta los obstáculos a la hora de conseguir empleo</h4>
                <p><strong>Fuente:</strong>Tendencias 21</p>
                <p>29-may-2013</p>
                <p>Según los expertos de recursos humanos, mentir sobre la experiencia laboral es un error que disminuye las posibilidades de lograr un empleo, por esa razón las empresas han comenzado a aplicar instrumentos más efectivos de descarte.<a href="/es_mx/empleo/mentir_curriculum_solo_aumenta_obstaculos_conseguir_empleo">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>¿Buscas trabajo? Haz tu página de internet personal</h4>
                <p><strong>Fuente:</strong>Forbes</p>
                <p>28-may-2013</p>
                <p>Un sitio personal cuyo dominio lleve tu nombre es una herramienta no sólo muy eficaz, sino cada vez más necesaria en el mercado laboral. Aquí te decimos qué debe incluir.<a href="/es_mx/empleo/buscas_trabajo_haz_pagina_internet_personal">Leer más</a></p>
              </li>

              <li ><h4>Conoce las preguntas típicas de la entrevista laboral</h4>
                <p><strong>Fuente:</strong>IDC Online</p>
                <p>27-may-2013</p>
                <p>Todas tienen una lógica para el reclutador, aprende a contestarlas adecuadamente.<a href="/es_mx/empleo/conoce_preguntas_tipicas_entrevista_laboral">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Reglas para conseguir empleo sin morir en el intento</h4>
                <p><strong>Fuente:</strong>Conde Jobs</p>
                <p>25-may-2013</p>
                <p>Conoce algunos consejos para optimizar la búsqueda de empleo y poder enfrentar las exigencias de las vacantes.<a href="/es_mx/empleo/reglas_conseguir_empleo_morir_intento">Leer más</a></p>
              </li>

              <li ><h4>Nuevos oficios digitales, las raras profesiones de hoy</h4>
                <p><strong>Fuente:</strong>La Nación</p>
                <p>24-may-2013</p>
                <p>Nacieron en los últimos diez años y ahora son de las mejor remuneradas. Conoce algunas de las nuevas profesiones que destacan en la búsqueda del menú laboral.<a href="/es_mx/empleo/nuevos_oficios_digitales_raras_profesiones_hoy">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Recursos humanos se traslada a las redes sociales</h4>
                <p><strong>Fuente:</strong>CNN Expansión</p>
                <p>20-may-2013</p>
                <p>Según datos estadísticos, cada vez más los mexicanos recurren a las redes sociales como una opción para buscar empleo.<a href="/es_mx/empleo/recursos_humanos_traslada_redes_sociales">Leer más</a></p>
              </li>

              <li ><h4>Tu currículum, ¿será capaz de persuadir?</h4>
                <p><strong>Fuente:</strong>About.com</p>
                <p>17-may-2013</p>
                <p>Conoce qué tipo de información integrar a tu CV para que éste responda a preguntas básicas que realiza el reclutador.<a href="/es_mx/empleo/curriculum_sera_capaz_persuadir">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Universitario, consigue empleo en menos de un mes</h4>
                <p><strong>Fuente:</strong>Publimetro</p>
                <p>17-may-2013</p>
                <p>Becas, intercambios, prácticas profesionales y el servicio social son las estancias que facilitan conseguir un primer empleo.<a href="/es_mx/empleo/universitario_consigue_empleo_menos_mes">Leer más</a></p>
              </li>

              <li ><h4>Al momento de contratar, ¿cuáles son las habilidades y cualidades que más valoran las organizaciones?</h4>
                <p><strong>Fuente:</strong>CD Consultores</p>
                <p>15-may-2013</p>
                <p>Las exigencias que actualmente impone el mercado a las organizaciones, las obliga a demandar a sus empleados, nuevas cualidades que les permitan alcanzar resultados de forma más eficiente.<a href="/es_mx/empleo/momento_contratar_cuales_son_habilidades_cualidades_valoran_organizaciones">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Cambian las tendencias de búsqueda de empleo</h4>
                <p><strong>Fuente:</strong>Universia</p>
                <p>14-may-2013</p>
                <p>Twitter es considerada ya como una gran herramienta para los responsables de recursos humanos, ya sea para publicar ofertas o bien, para realizar parte del proceso de selección.<a href="/es_mx/empleo/cambian_tendencias_busqueda_empleo">Leer más</a></p>
              </li>




            </ul>
          </div>

          <div class="pagArticulo" style="display: none">
            <ul class="tema">


              <li ><h4>Trainee, la vía a tu primer empleo</h4>
                <p><strong>Fuente:</strong>CNN Expansión</p>
                <p>13-may-2013</p>
                <p>Las prácticas profesionales permiten, en la mayoría de los casos, un rápido ingreso al mundo laboral y se potencializa cuando se cuenta con el conocimiento de idiomas.<a href="/es_mx/empleo/trainee_via_primer_empleo">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Tips para buscar trabajo si eres discapacitado</h4>
                <p><strong>Fuente:</strong>Publimetro</p>
                <p>10-may-2013</p>
                <p>Si cuentas con alguna discapacidad, estas sugerencias pueden ser de gran utilidad para conseguir empleo.<a href="/es_mx/empleo/tips_buscar_trabajo_si_eres_discapacitado">Leer más</a></p>
              </li>

              <li ><h4>Aprovecha al máximo las ferias de empleo</h4>
                <p><strong>Fuente:</strong>Mexican Business Web</p>
                <p>9-may-2013</p>
                <p>Recomendaciones de cómo prepararse para acudir a una Feria de Empleo, optimizar la búsqueda y conseguir trabajo.<a href="/es_mx/empleo/aprovecha_maximo_ferias_empleo">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Siete pecados capitales al buscar empleo</h4>
                <p><strong>Fuente:</strong>La Razón</p>
                <p>8-may-2013</p>
                <p>Siete actitudes que entorpecen el proceso de búsqueda de empleo y consejos para evitarlos.<a href="/es_mx/empleo/siete_pecados_capitales_buscar_empleo">Leer más</a></p>
              </li>

              <li ><h4>Nuevos modelos de trabajo</h4>
                <p><strong>Fuente:</strong>Universia</p>
                <p>7-may-2013</p>
                <p>En un mundo en permanente cambio, los modelos de trabajo, el perfil de los trabajadores y las expectativas de los empleadores es imposible que permanezcan estáticos. La flexibilidad, entre otras características, parece imponerse en estos tiempos.<a href="/es_mx/empleo/nuevos_modelos_trabajo">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Siete cosas que no sabías de tu búsqueda de empleo</h4>
                <p><strong>Fuente:</strong>Forbes</p>
                <p>6-may-2013</p>
                <p>El proceso de búsqueda de empleo puede ser largo y extenuante, pero es importante no perder el ánimo y cuidar ciertos detalles.<a href="/es_mx/empleo/siete_cosas_no_sabias_busqueda_empleo">Leer más</a></p>
              </li>

              <li ><h4>¿Por qué no me llaman a entrevista?</h4>
                <p><strong>Fuente:</strong>Reclutalento</p>
                <p>3-may-2013</p>
                <p>Artículo que explica algunos de los múltiples factores por los que no se recibe respuestas ante el envío de un CV.<a href="/es_mx/empleo/por_que_no_llaman_entrevista">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>¿Cómo vestir para obtener empleo?</h4>
                <p><strong>Fuente:</strong>El Universal</p>
                <p>2-may-2013</p>
                <p>Puntos básicos sobre la vestimenta y el lenguaje corporal según un asesor de búsqueda de empleo.<a href="/es_mx/empleo/como_vestir_obtener_empleo">Leer más</a></p>
              </li>

              <li ><h4>Cinco factores que perjudican tu currículum</h4>
                <p><strong>Fuente:</strong>OCC Mundial</p>
                <p>1-may-2013</p>
                <p>Descripción de cinco elementos a considerar en la elaboración del CV y que pueden resultar perjudiciales en una entrevista de trabajo.<a href="/es_mx/empleo/cinco_factores_perjudican_curriculum">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Consejos para buscar empleo por internet</h4>
                <p><strong>Fuente:</strong>Infobae</p>
                <p>30-abr-2013</p>
                <p>Dada la creciente búsqueda de empleo en Internet, se presentan algunos consejos para estar alerta ante estafas y engaños posibles en la red.<a href="/es_mx/empleo/consejos_buscar_empleo_internet">Leer más</a></p>
              </li>




            </ul>
          </div>

          <div class="pagArticulo" style="display: none">
            <ul class="tema">


              <li ><h4>Consejos para destacar en las ferias de empleo y tener éxito en la búsqueda de tu primer trabajo</h4>
                <p><strong>Fuente:</strong>20 minutos</p>
                <p>29-abr-2013</p>
                <p>Consejos de una especialista sobre cómo elaborar un CV y destacar como un candidato idóneo en una feria de empleo.<a href="/es_mx/empleo/consejos_destacar_ferias_empleo_tener_exito_busqueda_primer_trabajo">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Cómo mostrar tu marca personal en una entrevista de trabajo</h4>
                <p><strong>Fuente:</strong>Forbes</p>
                <p>25-abr-2013</p>
                <p>Nuestra manera de conducirnos, de interactuar con los demás es lo que deja una buena o mala impresión.<a href="/es_mx/empleo/como_mostrar_marca_personal_entrevista_trabajo">Leer más</a></p>
              </li>

              <li ><h4>¿Egresado sin experiencia? ¡Atrapa al reclutador!</h4>
                <p><strong>Fuente:</strong>ZonaJobs</p>
                <p>25-abr-2013</p>
                <p>Sugerencias sobre cuáles elementos destacar en un CV para los recién egresados que no cuentan con experiencia laboral.<a href="/es_mx/empleo/egresado_experiencia_atrapa_reclutador">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Busque empleo por internet sin perder dinero</h4>
                <p><strong>Fuente:</strong>Finanzas Personales</p>
                <p>24-abr-2013</p>
                <p>Consejos para evitar riesgos durante la búsqueda de empleo en internet.<a href="/es_mx/empleo/busque_empleo_internet_perder_dinero">Leer más</a></p>
              </li>

              <li ><h4>Actividades que agregan valor a tu CV</h4>
                <p><strong>Fuente:</strong>Reclutalento</p>
                <p>23-abr-2013</p>
                <p>Realizar actividades extra habla del interés por sobresalir y el gusto por aprender, por eso es importante desarrollarlas y agregarlas al CV.<a href="/es_mx/empleo/actividades_agregan_valor_cv">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Cinco maneras de encontrar trabajo cuando no lo hay</h4>
                <p><strong>Fuente:</strong>Forbes</p>
                <p>23-abr-2013</p>
                <p>El coach de carrera Roger Wright plantea cinco principios que se pueden utilizar durante la búsque de empleo.<a href="/es_mx/empleo/cinco_maneras_encontrar_trabajo_cuando_no_hay">Leer más</a></p>
              </li>

              <li ><h4>Tendencias creativas e innovadoras para hacer tu CV</h4>
                <p><strong>Fuente:</strong>Alto Nivel</p>
                <p>22-abr-2013</p>
                <p>El videocurrículum es una opción que aprovecha el uso de las nuevas tecnologías.<a href="/es_mx/empleo/tendencias_creativas_innovadoras_hacer_cv">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>¿Buscando empleo? Cuatro tipos de reclutadores que debes conocer</h4>
                <p><strong>Fuente:</strong>OCC Mundial</p>
                <p>22-abr-2013</p>
                <p>Conoce los cuatro tipos de reclutadores más comunes, cómo identificarlos y qué esperar de ellos en el proceso de selección.<a href="/es_mx/empleo/buscando_empleo_cuatro_tipos_reclutadores_debes_conocer">Leer más</a></p>
              </li>

              <li ><h4>10 errores poco usuales en la entrevista de trabajo y seis demasiado comunes</h4>
                <p><strong>Fuente:</strong>De revistas.com</p>
                <p>19-abr-2013</p>
                <p>Durante las entrevistas de trabajo se viven momentos de estrés y nerviosismo que puede generar diferentes tipos de errores.<a href="/es_mx/empleo/diez_errores_poco_usuales_entrevista_trabajo_seis_demasiado_comunes">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>¡Organízate! Incrementa tus oportunidades de encontrar trabajo</h4>
                <p><strong>Fuente:</strong>Publimetro</p>
                <p>19-abr-2013</p>
                <p>Contar con una estrategia o un plan optimiza las oportunidades de encontrar empleo.<a href="/es_mx/empleo/organizate_Incrementa_oportunidades_encontrar_trabajo">Leer más</a></p>
              </li>




            </ul>
          </div>

          <div class="pagArticulo" style="display: none">
            <ul class="tema">


              <li ><h4>Conócete y atrapa al reclutador</h4>
                <p><strong>Fuente:</strong>CNN Expansión</p>
                <p>18-abr-2013</p>
                <p>Según expertos, es necesario conocerse para no cometer errores como sobrevenderse e improvisar en una entrevista de trabajo.<a href="/es_mx/empleo/conocete_atrapa_reclutador">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Cómo conseguir una entrevista en cuatro pasos</h4>
                <p><strong>Fuente:</strong>OCC Educación</p>
                <p>18-abr-2013</p>
                <p>Consejos para definir una estrategia y lograr una entrevista de trabajo cuando se busca empleo en línea.<a href="/es_mx/empleo/como_conseguir_entrevista_cuatro_pasos">Leer más</a></p>
              </li>

              <li ><h4>Consigue tu primer empleo sin perder tiempo ni esfuerzo</h4>
                <p><strong>Fuente:</strong>Publimetro</p>
                <p>17-abr-2013</p>
                <p>Un especialista en Recursos Humanos da nueve sugerencias para conseguir el primer empleo.<a href="/es_mx/empleo/consigue_primer_empleo_perder_tiempo_esfuerzo">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Twitter como herramienta para buscar empleo</h4>
                <p><strong>Fuente:</strong>Reclutalentos</p>
                <p>17-abr-2013</p>
                <p>El uso popular de twitter posicionan a esta red social como una buena opción para buscar empleo.<a href="/es_mx/empleo/twitter_herramienta_buscar_empleo">Leer más</a></p>
              </li>

              <li ><h4>Al buscar empleo, tu firma habla por ti</h4>
                <p><strong>Fuente:</strong>Universia</p>
                <p>20-mar-2013</p>
                <p>Conocer los detalles que se revelan del tipo de letra de una persona permite descubrir sus características personales.<a href="/es_mx/empleo/buscar_empleo_firma_habla_por_ti">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Cinco formas de ganar experiencia laboral sin enrolarte en un trabajo</h4>
                <p><strong>Fuente:</strong>Alt1040</p>
                <p>30-abr-2012</p>
                <p>Suena contradictorio, pero es posible. Para muchos jóvenes que saltan al mercado laboral, la experiencia es uno de los rubros que suele obstaculizar su entrada a nuevos trabajos.<a href="/es_mx/empleo/cinco_formas_ganar_experiencia_laboral_enrolarte_trabajo">Leer más</a></p>
              </li>

              <li ><h4>Encuentre un mejor empleo</h4>
                <p><strong>Fuente:</strong>El Universal</p>
                <p>30-abr-2012</p>
                <p>El tiempo promedio para obtener trabajo va de seis a nueve meses, dicen expertos.<a href="/es_mx/empleo/encuentre_mejor_empleo">Leer más</a></p>
              </li>

              <li class="temaPar"><h4>Las 23 claves del lenguaje corporal en la entrevista</h4>
                <p><strong>Fuente:</strong>OCCEducación</p>
                <p>30-abr-2012</p>
                <p>En la comunicación, tan importante es lo que se dice como lo que no se dice y aún más, cuando se trata de una entrevista laboral.<a href="/es_mx/empleo/23_claves_lenguaje_corporal_entrevista">Leer más</a></p>
              </li>




            </ul>
          </div>

        </div>
        <div class="paginator">
          <p class="mostradas">Mostrando 1 - 10 de 25 Temas</p>
          <ul>
            <li><a class="prev"></a></li>
            <li><span class="current">1</span></li>
            <li><a href="" class="pagina">2</a></li>
            <li><a href="" class="pagina">3</a></li>
            <li><a href="" class="pagina">4</a></li>
            <li><a href="" class="pagina">5</a></li>
            <li><a href="" class="pagina">6</a></li>
            <li><a href="" class="pagina">7</a></li>
            <li><a href="" class="pagina">8</a></li>
            <li><a href="" class="pagina">9</a></li>
            <li><a href="" class="pagina">10</a></li>

            <!-- total de páginas -->
            <li><span class="noPags"> de 25 Páginas</span></li>
            <!-- liga para saltar al bloque posterior -->
            <li><a href="" class="next sig">Siguiente</a></li></ul>
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
            var articulos =448;
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

  <div id="content_btoSB"><a class="subir" href="#" >subir</a></div>
  <div id="footer">
    <span>Portal del Empleo: empleo.gob.mx</span>
    <div id="links_footer">
      <div class="col">
        <h3>Encuentra ofertas de empleo</h3>
        <ul>
          <li><a href="/swb/empleo/Busqueda_especifica">Búsqueda específica de ofertas de empleo</a></li>
          <!--<li><a href="/swb/empleo/vacantes_en_la_administracion_publica">Ofertas de empleo en la Administración pública federal</a></li>-->
          <li><a href="/swb/empleo/ofertas_recientes">Ofertas de empleo recientes</a></li>
          <li><a href="/swb/empleo/ofertas_destacadas">Ofertas de empleo destacadas</a></li>
          <li><a href="/swb/empleo/periodico_ofertas_empleo_sne">Periódico de ofertas de empleo del SNE</a></li>
          <li><a href="/swb/empleo/Eventos">Eventos próximos de Ferias de Empleo</a></li>
          <li><a href="/swb/empleo/bolsas_empleo_asociadas">Bolsas de empleo asociadas</a></li>
        </ul>
      </div>

      <div class="col">
        <h3>Aumenta tus posibilidades</h3>
        <ul>
          <li><a href="/swb/empleo/Registro_Candidato">Regístrate y haz que las empresas te vean</a></li>
          <li><a href="/swb/empleo/habilidades_busqueda_empleo_hpbe">Habilidades para la búsqueda de empleo</a></li>
          <li><a href="/swb/empleo/programas_servicios_empleo">Programas y servicios de empleo</a></li>
          <li><a href="/swb/empleo/conoce_descubre_habilidades_capacidades">Conoce y descubre tus habilidades y capacidades</a></li>
          <!-- <li><a href="/swb/empleo/Consejos_para_el_trabajo">Consejos para el trabajo</a></li>-->
          <li><a href="/swb/empleo/becas_capacitacion_sne">Becas a la Capacitación del SNE</a></li>
          <li><a href="/swb/empleo/becas_manpower_tdc">Becas Manpower TDC</a></li>
          <li><a href="/swb/empleo/certificacion_competencias_conocer">Certifica tus habilidades laborales: CONOCER</a></li>
          <li></li>
        </ul>

      </div>

      <div class="col">
        <h3>Servicios para tu empresa</h3>
        <ul>
          <li><a href="/swb/empleo/EmpresaRegistro">Publica ofertas de empleo gratis</a></li>
          <li><a href="/swb/empleo/ferias_empleo">Participa en las Ferias de Empleo</a></li>
          <li><a href="/swb/empleo/revista_informativa_sne">Revista Informativa del SNE</a></li>
          <!--<li><a href="/swb/empleo/Proceso_de_Reclutamiento">El proceso de reclutamiento</a></li>-->
          <li><a href="/swb/empleo/mejora_desempeno_tu_empresa">Mejora el desempeño de tu empresa</a></li>
          <li><a href="/swb/empleo/estadisticas_laborales">Estadísticas laborales</a></li>
          <li><a href="/swb/empleo/acerca_sne">Acerca del SNE</a></li>
        </ul>
      </div>
      <div class="clearfix"></div>

    </div>
    <div id="nav_bottom">
      <ul>
        <li><a href="/swb/empleo/mapa_sitio">Mapa de sitio</a></li>
        <li><a href="<c:url value="/miespacionav.do?method=agendaCita"/>" >Solicita una cita</a></li>
        <li><a href="/swb/empleo/Necesitas_ayuda">¿Necesitas ayuda?</a></li>
        <li><a href="/swb/empleo/contacto" >Contacto</a></li>
        <!--li><a href="/swb/empleo/condiciones_de_uso" >Políticas y condiciones de uso</a></li-->
        <li><a href="/swb/empleo/proteccion_datos_personales">Aviso de Protección de Datos Personales</a></li>
      </ul>

    </div>
    <div class="certificado_seguridad">
      <img alt="Certificado de Seguridad Seal" src="//seal.qualys.com/sealserv/seal.gif?i=2136fe76-c0b1-4072-bf60-2ebd34c45fd0" onclick="window.open('https://seal.qualys.com/sealserv/info/?i=2136fe76-c0b1-4072-bf60-2ebd34c45fd0','qualysSealInfo', 'height=600,width=851,resizable=1')" />

    </div>
    <div id="siguenos">
      <h4>S&iacute;guenos</h4>
      <div class="whatsapp"><a href="#"><span class="whatsappText">55 73 35 68 24	</br>Lunes a Viernes de 8:00 a 18:00 hrs</span></a>	</div>
      <div class="facebook"><a href="http://facebook.com/empleogobmx" target="blank"></a></div>
      <div class="twitter"><a href="http://twitter.com/empleogob_mx" target="blank"></a></div>
      <!-- <div class="rss"><a href="http://qa.empleo.gob.mx/ofertasRSSConsultar.do?method=init" ></a></div> -->

    </div>
    <div id="direccion">
      <p class="t-footer"><strong>Llama a SNETEL 01-800-20-20</strong></p>
      <p>Av. Cuahutémoc 614<abbr title="Número">No.</abbr> 614, <abbr title="Colonia">Col.</abbr> Narvarte, Benito Juárez 03020, Ciudad de México</p>


    </div>
  </div>
  <ul id="banners_publicitarios">
    <li> <a class="swb-banner" href="http://www.stps.gob.mx" onclick="window.location.href='/es_mx/empleo/articulos/_aid/i1_1441';return false;" title="STPS">
      <img src="/work/models/empleo/Resource/i1_1441/banner_publicitario2.fw.png" alt="Secretaría del Trabajo y Previsión Social" width="166" height="57"/>
    </a></li>
    <li> <a class="swb-banner" href="/swb/empleo/servicio_nacional_de_empleo_" onclick="window.location.href='/es_mx/empleo/articulos/_aid/i1_1443';return false;" title="SNE">
      <img src="/work/models/empleo/Resource/i1_1443/banner_publicitario3.png" alt="Servicio Nacional de Empleo" width="92" height="56"/>
    </a></li>
    <li> <a class="swb-banner" href="http://www.presidencia.gob.mx" onclick="window.location.href='/es_mx/empleo/articulos/_aid/i1_27';return false;" title="Presidencia">
      <img src="/work/models/empleo/Resource/i1_27/banner_publicitario4.png" alt="Presidencia de la república"/>
    </a></li>
  </ul>
</div>

</body>
</html>
<!--Time: 277ms - SemanticWebBuilder: http://www.empleo.swb#WebPage:articulos--> 
