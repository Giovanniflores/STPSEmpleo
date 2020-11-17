<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles"%>

<%
String contextApp = request.getContextPath();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd"> 
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="es" lang="es"> 
<head> 
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<meta http-equiv="X-UA-Compatible" content="IE=8" />
<meta http-equiv="Reply-to" content="empleo@stps.gob.mx" />
<meta name="Title" content="Portal del Empleo" />
<meta name="author" content="Carlos Alberto López Sánchez"/>
<meta name="description" content="El Portal del Empleo ofrece información y servicios que te apoyarán a mejorar tu búsqueda de empleo de la manera más fácil y novedosa."/>
<meta name="keywords" content="Empleo, Ofertas empleo, Estad&iacute;sticas del mercado laboral, Ofrezco empleo, Capacitaci&oacute;n, STPS, Secretar&iacute;a del Trabajo y Previsi&oacute;n Social, Ofertas de empleo en M&eacute;xico, Publicaci&oacute;n de ofertas de trabajo, Bolsa de trabajo, Curriculum Vitae, cv, "/>
 
<meta name="Subject" content="Empleo" />
<meta name="language" content="spanish" />
<meta name="revisit" content="1 days" />
<meta name="distribution" content="Global" />
<meta name="robots" content="all | index | nofollow" />
<meta name="geo.region" content="MX-DIF" />
<meta name="geo.placename" content="Mexico City, Mexico" /> 
<title>*Portal del Empleo :: Ofertas Empleo</title>
<link href='https://fonts.googleapis.com/css?family=Droid+Sans' rel='stylesheet' type='text/css' />
<link href='https://fonts.googleapis.com/css?family=Puritan' rel='stylesheet' type='text/css' />

<link href="https://www.empleo.gob.mx/work/models/empleo/css/estilos_detalle_vacante_tmp.css" rel="stylesheet" type="text/css" />
<link href="https://www.empleo.gob.mx/work/models/empleo/css/estilos_lightBoxPost_tmp.css" rel="stylesheet" type="text/css" />
<link href="https://www.empleo.gob.mx/work/models/empleo/css/estilos_mi_espacio_tmp.css" rel="stylesheet" type="text/css" />
<link href="${PATH_CSS_SWB}css_aplicacion/estilos_tabla_tmp.css" rel="stylesheet" type="text/css" />
<link href="https://www.empleo.gob.mx/work/models/empleo/css/estilos_conocer_tmp.css" rel="stylesheet" type="text/css" />
<link href="https://www.empleo.gob.mx/work/models/empleo/css/estilos_empleo_tmp.css" rel="stylesheet" type="text/css" />
<link href="https://www.empleo.gob.mx/work/models/empleo/css/estilos_canal_tmp.css" rel="stylesheet" type="text/css" />
<link rel="shortcut icon" media="all" type="image/x-icon" href="https://www.empleo.gob.mx/work/models/empleo/css/favicon.ico" /> 
<link rel="alternate" type="application/rss+xml" title="Portal del Empleo" href="/RSS" /> 

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
    s = s.concat('<div class="swb-recommend">  <h3>Recomendar<\/h3>  <form class="swb-rcmnd-frm" method="post" action="https://www.empleo.gob.mx/es_mx/empleo/solicitantes/_rid/i1_55/_mto/3/_mod/send" id="frmContact">    <p class="swb-rcmnd-in">      <label for="txtFromName">Remitente:*<\/label>      <input type="text" id="txtFromName" name="txtFromName" value="" maxlength="100"/>    <\/p>    <p class="swb-rcmnd-in">      <label for="txtFromEmail">Correo del remitente:*<\/label>      <input type="text" id="txtFromEmail" name="txtFromEmail" value="" maxlength="80"/>    <\/p>    <p class="swb-rcmnd-in">      <label for="txtToName">Destinatario:*<\/label>      <input type="text" id="txtToName" name="txtToName" maxlength="100" />    <\/p>    <p class="swb-rcmnd-in">      <label for="txtToEmail">Correo del destinatario:*<\/label>      <input type="text" id="txtToEmail" name="txtToEmail" maxlength="80"/>    <\/p>    <p class="swb-rcmnd-in">      <label for="tarMsg">Mensaje adicional:<\/label>      <textarea rows="5" cols="40" id="tarMsg" name="tarMsg"><\/textarea>    <\/p><div class="swb-captcha-imagen">  <p><img src="/swbadmin/jsp/securecode.jsp" alt="" id="imgseccode" width="155" height="65" /></p>  <p><a href="javascript:changeSecureCodeImage(\'imgseccode\');">Cambiar imagen</a></p></div><div class="swb-captcha-txt">  <p><label for="cmnt_seccode">El texto de la imagen es:</label></p>  <p><input type="text" name="code" value=""/></p></div>    <p class="swb-rcmnd-cmd">      <input type="button" value="Cancelar" onclick="removeModal(recomDivId);"/>      <input type="reset" value="Limpiar"/>      <input type="button" value="Enviar" onclick="validate(this.form);"/>    <\/p>  <\/form>  <p class="swb-rcmnd-warn">* Dato requerido<\/p><\/div>');
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

<script type="text/javascript" >
   function employ() {
      document.ocp.searchPlace.value = document.ocp.searchPlace.value;
      document.ocp.searchQ.value = document.ocp.searchTopic.value;
      document.ocp.submit();
   }
</script>

<script type="text/javascript" > 
// setDefaults arguments: size unit, default size, minimum, maximum
// optional array of elements or selectors to apply these defaults to

function wbprint()
{
  window.self.print();
  window.self.close();
}
</script>

<script type="text/javascript" >
function ajustaFrame(){
   window.scrollTo(0,0);
}

function resizeIframeHeight(nHeight) {

   var valid = false;
   var ifrHeight = 0;
		
   try{
      if (nHeight) ifrHeight = parseInt(nHeight);

      if (ifrHeight > 0) valid = true;
   }catch(e){
      valid = false;
   }
		
   if (valid){
      var iframe = document.getElementById('iframecontentswb');
      iframe.setAttribute('height', ifrHeight);
   } else {
      var iframe = document.getElementById('iframecontentswb');
      iframe.setAttribute('height', iframe.getAttribute('height'));
   }
}

function onloadFrame(){
   var ifr = document.getElementById('iframecontentswb');
   if (ifr)
      ifr.onload = function(){window.scrollTo(0,0);};
}

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
</script>
</head>
<body class="soria" onload="onloadFrame();" >
<div id="wrapper">

      <div id="herramientas">
	
<!-- style="color: #FFFFFF">Ofertas_Empleo -->


		<img src="https://www.empleo.gob.mx/work/models/empleo/jsp/cil/images/logo_STPS.gif" width="404" height="51" alt="Secretar&iacute;a del Trabajo y Previsi&oacute;n Social: Servicio Nacional de Empleo" />

        	<ul>
     <li><a href="https://www.empleo.gob.mx/swb/empleo/Candidatos" >Mi espacio</a></li>
     <li><a href="https://www.empleo.gob.mx/swb/empleo/contacto" >Contacto</a></li>
     <li><a href="https://www.empleo.gob.mx/swb/empleo/Mapa_del_Portal" >Mapa del Portal</a></li>
    <!-- <li><a href="/swb/empleo/Guia_de_Uso_del_Porta" >Guía de uso</a></li>-->
     <li><a href="https://www.empleo.gob.mx/swb/empleo/Sitios_relacionados" >Sitios relacionados</a></li>
     <li><a href="https://www.empleo.gob.mx/swb/empleo/Necesitas_ayuda" >¿Necesitas ayuda?</a></li>
</ul>

      </div>
    <div id="header"><h1><a href="https://www.empleo.gob.mx/swb/empleo/" >Portal del Empleo : llama al 01 800 841 20 20</a></h1>

<div id="perfil_usuario_2012">
		<p>Regístrate <em> como:</em></p>
		<p><a class="candidato" href="https://www.empleo.gob.mx/swb/empleo/Registro_Candidato">Candidato</a>
		<a class="empresa" href="https://www.empleo.gob.mx/swb/empleo/EmpresaRegistro">Empresa</a>
<br clear="all" /></p>
		<p class="plecases">Si ya estás registrado, <a href="https://www.empleo.gob.mx/swb/empleo/Inicio_de_sesion">Inicia sesión</a></p>
            </div>



            </div>
    <div id="espacio_buscador">
  <div id="buscador" class="ac_2011 b">
<a class="busqueda_especifica" href="<%=response.encodeURL(request.getContextPath()+"/busquedaEspecificaOfertas.do?method=buscar")%>" >Búsqueda específica</a>
	<div class="swb-ilta" id="ilta_31">
	<jsp:include page="/ofertas/total" 	flush="true" >	
   	</jsp:include>
	</div>
	<form name="ocp" id=ocp" action="https://www.empleo.gob.mx/es_mx/empleo/Ocupate" method="get">
 <input type="hidden" name="method" value="init" />
 <input type="hidden" name="searchQ" value="" />
 <div class="que_empleo">
  <p class="t_buscador">¿Qué empleo buscas?</p>
  <p class="buscador_1">
   <input name="searchTopic" type="text" value="" /></p>
  <p class="ejemplo">
   Puesto, carrera, oficio <a title="Uso del buscador" class="ayuda1" href="https://www.empleo.gob.mx/swb/empleo/Uso_del_Buscador">Ayuda</a></p> 
 </div>
 <div class="donde">
  <p class="t_buscador">¿Dónde?</p>

<ul id="combo">
<li class="texarea">
  <span id="spryselect1">
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
	</select>
  </span>
  </li>
</ul>

  <p class="ejemplo">Entidad y/o Localidad</p> 
 </div>
 <input type="button" name="bt_buscador" id="bt_buscador" onclick="employ();" value="Buscar">
</form>

	<span class="numero_ofertas">ofertas de empleo</span>
        </div>
       <div id="ayuda_buscador" class="ac_2011"> 
         <a class="ayuda" href="https://www.empleo.gob.mx/swb/empleo/Necesitas_ayuda" >¿Necesitas ayuda?</a>
<a class="quejas" href="https://empleo.gob.mx/suggestion.do?method=init" target="popUp" onClick="window.open(this.href, this.target, 'toolbar=no,directories=no,scrollbars=no,location=no,statusbar=no,menubar=no,resizable=yes,width=830,height=740'); return false;">Sugerencias</a>

       </div>
</div>

<div id="cuerpo_int">
        <div id="navegacion">
	 
                    <ul>
                <li>
<a href="https://www.empleo.gob.mx/es_mx/empleo/servicio_nacional_de_empleo_" class="swb-menumap-act">Servicio Nacional de Empleo</a>
</li>
<li>
<a href="https://www.empleo.gob.mx/es_mx/empleo/solicitantes" class="swb-menumap-act">En busca de empleo</a>
</li>
<li>
<a href="https://www.empleo.gob.mx/es_mx/empleo/busco_capacitacion" class="swb-menumap-act">Capacitación y becas</a>
</li>
<li>
<a href="https://www.empleo.gob.mx/es_mx/empleo/estudiantes" class="swb-menumap-act">Orientación profesional</a>
</li>
<li>
<a href="https://www.empleo.gob.mx/es_mx/empleo/busco_asesoria_laboral" class="swb-menumap-act">Asesoría laboral</a>
</li>
<li>
<a href="https://www.empleo.gob.mx/es_mx/empleo/investigadores_del_mercado_laboral" class="swb-menumap-act">Estadísticas laborales</a>
</li>
                    </ul>
                

        </div>
        <div id="contenido_principal" class="sinCol">
        <div id="ruta_navegacion">
        Estás en aqui: <a href="https://www.empleo.gob.mx/es_mx/empleo/home"  >Inicio</a> | Herramientas del Sitio | Ofertas Empleo&nbsp;&nbsp;

<script type="text/javascript" src="http://s7.addthis.com/js/250/addthis_widget.js#username=xa-4bfafaee595d62bd" ></script>
        </div>
		<ul class="redesSoc">
			<li><a target="_self" href="https://www.facebook.com/empleogobmx" class="fbk">Facebook</a></li>
			<li><a target="_self" href="https://twitter.com/empleogob_mx" class="twt">twitter</a></li>
		</ul>        
        <div id="contenido" class="frames"> 
	<br/>
<br/><h2>Ofertas Empleo</h2>
		<div id="contenido_principal" class="detalle">
        <div id="contenido">
        	<tiles:insert name="body"/>
        </div>
      
        <!-- Más ofertas de empleo -->
		<div class="opciones_col">			
	    	<div id="masOfertas"></div>
		</div>
				
    </div>
 
<br/>

</div>
</div>
</div>

<div id="banners_publicitarios">
	<ul>
		<li>
			<a class="swb-banner" href="http://www.presidencia.gob.mx/vivirmejor/vivirmejor.html" onclick="window.location.href='http://www.empleo.gob.mx/es_mx/empleo/Ofertas_Empleo/_aid/i1_29?id_oferta_empleo=472710';return false;" title="Vivir mejor">
				<img src="https://www.empleo.gob.mx/work/models/empleo/Resource/i1_29/banner_publicitario1.png" alt="Vivir Mejor" width="55px" height="65px"/>
			</a>		
		</li>
		<li>
			<a class="swb-banner" href="http://www.stps.gob.mx" onclick="window.location.href='https://www.empleo.gob.mx/es_mx/empleo/Ofertas_Empleo/_aid/i1_1441?id_oferta_empleo=472710';return false;" title="stps">
				<img src="https://www.empleo.gob.mx/work/models/empleo/Resource/i1_1441/banner_publicitario2.gif" alt="Secretaría del Trabajo y Previsión Social" width="65px" height="45px"/>
			</a>		
		</li>
		<li>
			<a class="swb-banner" href="https://www.empleo.gob.mx/swb/empleo/servicio_nacional_de_empleo_" onclick="window.location.href='/es_mx/empleo/Ofertas_Empleo/_aid/i1_1443?id_oferta_empleo=472710';return false;" title="SNE">
				<img src="https://www.empleo.gob.mx/work/models/empleo/Resource/i1_1443/banner_publicitario3.png" alt="Servicio Nacional de Empleo" width="92px" height="45px"/>
			</a>		
		</li>
		<li>
			<a class="swb-banner" href="http://www.presidencia.gob.mx" onclick="window.location.href='https://www.empleo.gob.mx/es_mx/empleo/Ofertas_Empleo/_aid/i1_27?id_oferta_empleo=472710';return false;" title="Presidencia">
				<img src="https://www.empleo.gob.mx/work/models/empleo/Resource/i1_27/banner_publicitario4.gif" alt="Presidencia de la república" width="103px" height="44px"/>
			</a>		
		</li>
		<li>
			<a class="swb-banner" href="http://www.gob.mx" onclick="window.location.href='https://www.empleo.gob.mx/es_mx/empleo/Ofertas_Empleo/_aid/i1_28?id_oferta_empleo=472710';return false;" title="GobMx">
				<img src="https://www.empleo.gob.mx/work/models/empleo/Resource/i1_28/banner_publicitario5.jpg" alt="Gobierno de México en Línea" width="103px" height="43px"/>
			</a>		
		</li>								
	</ul>

		</div>
        <div id="footer">
            <span>Portal del Empleo: empleo.gob.mx</span>
            <div id="links_footer">
                <p><a href="https://www.empleo.gob.mx/swb/empleo/home" >Inicio</a>
<!--<a href="/swb/empleo/Mi_Carpeta" >Mi espacio</a>-->
<a href="https://www.empleo.gob.mx/swb/empleo/contacto" >Contacto</a>
<a href="https://www.empleo.gob.mx/swb/empleo/Mapa_del_Portal" >Mapa del Portal</a>
<!--<a href="/swb/empleo/Guia_de_Uso_del_Porta" >Guía de Uso</a>-->
<a href="https://www.empleo.gob.mx/swb/empleo/Sitios_relacionados" >Sitios relacionados</a>
<!--<a href="/swb/empleo/Necesitas_ayuda" >Atención al usuario</a>-->
<!--<a href="/swb/empleo/Necesitas_ayuda" >Administración</a>-->
</p>
               <div id="navegacion_bottom">

                    <ul>
                <li>
<a href="https://www.empleo.gob.mx/es_mx/empleo/servicio_nacional_de_empleo_" class="swb-menumap-act">Servicio Nacional de Empleo</a>
</li>
<li>
<a href="https://www.empleo.gob.mx/es_mx/empleo/solicitantes" class="swb-menumap-act">En busca de empleo</a>
</li>
<li>
<a href="https://www.empleo.gob.mx/es_mx/empleo/busco_capacitacion" class="swb-menumap-act">Capacitación y becas</a>
</li>
<li>
<a href="https://www.empleo.gob.mx/es_mx/empleo/estudiantes" class="swb-menumap-act">Orientación profesional</a>
</li>
<li>
<a href="https://www.empleo.gob.mx/es_mx/empleo/busco_asesoria_laboral" class="swb-menumap-act">Asesoría laboral</a>
</li>
<li>
<a href="https://www.empleo.gob.mx/es_mx/empleo/investigadores_del_mercado_laboral" class="swb-menumap-act">Estadísticas laborales</a>
</li>
                    </ul>
                

          </div>
            </div>
            <div id="direccion">
               <p><strong>Periférico Sur <abbr title="Número">No.</abbr> 4271, <abbr title="Colonia">Col.</abbr> Fuentes del Pedregal, Tlalpan 14149, Ciudad de México, Teléfono 01-800-841-20-20</strong></p>

            </div>
        </div>
</div>
<div id="bar">
<div id="barHome">
<div id="barCenter" style="display: block;">
<form id="loginbarForm" name="loginbarForm" action="https://empleo.gob.mx//loginhome.do?method=login" method="post" autocomplete="off">

<input type="hidden" id="isHome" name="isHome" value="home" />
<input type="hidden" id="login_page" name="login_page" value="cand"/>

<p class="barLogin">
<input type="text" onfocus="clean(this, 6)" name="username" id="login" value="Usuario candidato" style="font-size: 12px;">
</p>

<p class="barLogin">
<input type="password" onkeypress="keybarSubmit(event)" onfocus="clean(this, 7)" value="Contraseña" name="password" id="pass">
</p>

<p><a href="#" class="barSesion" onclick="javascript:document.loginbarForm.submit();">Iniciar sesión</a>
</p>

</form>

<div class="bannerCanal_princ">
            <ul>
            <li><a class="textAumentar" href="#">Aumentar tamaño de texto</a></li>
            <li><a href="#" class="textNormal">Reestablecer tamaño de texto</a></li>
            <li><a href="#"  class="textReducir">Disminuir tamaño de texto</a></li>
            <li><a class="iconPrint" href="javascript:print()">Imprimir</a></li>
            <li><a class="iconRecomend" onclick="openRecommendationModal('recommendi1_55','#000000', 80);void(0);" href="#">Recomendar</a></li>
            </ul>
</div>
   		<p><a href="https://www.empleo.gob.mx/swb/empleo/Necesitas_ayuda" class="barFaq">¿Necesitas ayuda?</a>
        </p>
<form action="https://www.empleo.gob.mx/swb/empleo/buscador" method="get" id="formaq" name="formaq">
    <p class="barLogin">
   <input type="text" onfocus="this.value = '';" id="buscar" value="¿Qué buscas?" name="q">
</p>
   <p class="icoSearch">
<input type="submit" id="bt_buscar" value="" name="q2">
   </p>
</form>
    </div>

</div></div><script type="text/javascript"> 
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

    //addLoadEvent(fileLinks);
-->
</script>
</body></html>

<!--Time: 1187ms - SemanticWebBuilder: http://www.empleo.swb#WebPage:Ofertas_Empleo--> 
