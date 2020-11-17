<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.Calendar" %>

<%
   String context = (String)application.getAttribute("DOMAIN_PORTAL");  
   String contextApp = request.getContextPath() +"/";
   if (null != request.getSession().getAttribute("FROM_CIL"))
	   context = (String)application.getAttribute("DOMAIN_PORTAL_CIL");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd"> 
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="es" lang="es"> 
<head> 
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<meta http-equiv="X-UA-Compatible" content="IE=8" />
<meta http-equiv="Reply-to" content="empleo@stps.gob.mx" />
<meta name="Title" content="Portal del Empleo" />
<meta name="author" content="Oscar Manzo Camacho"/>
<meta name="description" content="El Portal del Empleo ofrece información y servicios que te apoyarán a mejorar tu búsqueda de empleo de la manera más fácil y novedosa."/>
<meta name="keywords" content="Empleo, Ofertas empleo, Estad&iacute;sticas del mercado laboral, Ofrezco empleo, Capacitaci&oacute;n, STPS, Secretar&iacute;a del Trabajo y Previsi&oacute;n Social, Ofertas de empleo en M&eacute;xico, Publicaci&oacute;n de ofertas de trabajo, Bolsa de trabajo, Curriculum Vitae, cv, "/>
 
<meta name="Subject" content="Empleo" />
<meta name="language" content="spanish" />
<meta name="revisit" content="1 days" />
<meta name="distribution" content="Global" />
<meta name="robots" content="all | index | nofollow" />
<meta name="geo.region" content="MX-DIF" />
<meta name="geo.placename" content="Mexico City, Mexico" /> 
<title>Portal del Empleo :: Espacio para Empresas</title>
<link rel="P3Pv1" href="https://www.empleo.gob.mx/w3c/p3p.xml" /> 
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" /> 
 
 	<meta http-equiv="X-UA-Compatible" content="IE=8"/>
 
	<title><tiles:getAsString name="title"/></title> 
  
	<link href='https://fonts.googleapis.com/css?family=Droid+Sans' rel='stylesheet' type='text/css' />

<style type="text/css">
		@import "dojotoolkit/dojo/resources/dojo.css";
		@import "dojotoolkit/dijit/themes/claro/claro.css";
		@import "css/estilos_formularios_resp.css";
		@import "css/estilos_plantilla_resp.css";
		.claro .dijitDialogTitleBar {
			background-color: #4F6710;
			color: #FFFFFF;
		    font-weight: bold;
		    text-decoration: none;
	    }
</style>
<!-- bootstrap -->
<link href="<%=request.getContextPath()%>/css/bootstrap/bootstrap.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/css/bootstrap/table-responsive.css" rel="stylesheet" type="text/css">
<meta name="viewport" content="width=device-width, initial-scale=1"> 
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

<link rel="shortcut icon" media="all" type="image/x-icon" href="<%=context %>/work/models/empleo/css/favicon.ico" /> 
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
    s = s.concat('<div class="swb-recommend">  <h3>Recomendar<\/h3>  <form class="swb-rcmnd-frm" method="post" action="<%=context %>/es_mx/empleo/solicitantes/_rid/i1_55/_mto/3/_mod/send" id="frmContact">    <p class="swb-rcmnd-in">      <label for="txtFromName">Remitente:*<\/label>      <input type="text" id="txtFromName" name="txtFromName" value="" maxlength="100"/>    <\/p>    <p class="swb-rcmnd-in">      <label for="txtFromEmail">Correo del remitente:*<\/label>      <input type="text" id="txtFromEmail" name="txtFromEmail" value="" maxlength="80"/>    <\/p>    <p class="swb-rcmnd-in">      <label for="txtToName">Destinatario:*<\/label>      <input type="text" id="txtToName" name="txtToName" maxlength="100" />    <\/p>    <p class="swb-rcmnd-in">      <label for="txtToEmail">Correo del destinatario:*<\/label>      <input type="text" id="txtToEmail" name="txtToEmail" maxlength="80"/>    <\/p>    <p class="swb-rcmnd-in">      <label for="tarMsg">Mensaje adicional:<\/label>      <textarea rows="5" cols="40" id="tarMsg" name="tarMsg"><\/textarea>    <\/p><div class="swb-captcha-imagen">  <p><img src="/swbadmin/jsp/securecode.jsp" alt="" id="imgseccode" width="155" height="65" /></p>  <p><a href="javascript:changeSecureCodeImage(\'imgseccode\');">Cambiar imagen</a></p></div><div class="swb-captcha-txt">  <p><label for="cmnt_seccode">El texto de la imagen es:</label></p>  <p><input type="text" name="code" value=""/></p></div>    <p class="swb-rcmnd-cmd">      <input type="button" value="Cancelar" onclick="removeModal(recomDivId);"/>      <input type="reset" value="Limpiar"/>      <input type="button" value="Enviar" onclick="validate(this.form);"/>    <\/p>  <\/form>  <p class="swb-rcmnd-warn">* Dato requerido<\/p><\/div>');
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
  
  function validaOfertasActivas(){
		<c:if test="${existenOfertasActivas == 'No'}">
			alert('Debe existir por lo menos una oferta de empleo activa, para ingresar a esta opción.');
		</c:if>
		<c:if test="${existenOfertasActivas == 'Si'}">
			document.location.href = '/buscarcandidatos.do?method=init';
		</c:if>
	}
	
	function validaOfertasActivasEspecifica(){
		<c:if test="${existenOfertasActivas == 'No'}">
			alert('Debe existir por lo menos una oferta de empleo activa, para ingresar a esta opción.');
		</c:if>
		<c:if test="${existenOfertasActivas == 'Si'}">
			document.location.href = '/busquedaEspecificaCandidatos.do?method=init';
		</c:if>
	}
-->
</script>

<script src="dojotoolkit/dojo/dojo.js" djConfig="parseOnLoad: true, locale:'es'"></script>

<script type="text/javascript">
	dojo.require("dijit.dijit");
	dojo.require("dijit.Dialog"); 
</script>

<script type="text/javascript">
	//Desplegar ventana para modificar logotipo
	function openLogoWindow(){	
	 	var newLogoWin = window.open('<c:url value="/uploadcompanylogo.do?method=init"/>', "CambiarImagen","height=400,width=400,resizable=yes,scrollbars=yes"); 	
	} 	

	function validaOfertasActivas(){
		<c:if test="${existenOfertasActivas == 'No'}">
			alert('Debe existir por lo menos una oferta de empleo activa, para ingresar a esta opción.');
		</c:if>
		<c:if test="${existenOfertasActivas == 'Si'}">
			document.location.href = '/buscarcandidatos.do?method=init';
		</c:if>
	}
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
<body class="claro empresas">

<div class="container">

    <jsp:include page="empresaHeader1Resp.jsp" />
    <jsp:include page="empresaHeader2Resp.jsp" />


<%-- 
        <jsp:include page="navegacion.jsp"/>
        <div id="contenido_principal" class="sinCol">
        
        
        
        <div id="ruta_navegacion">
        <c:if test="${not empty USUARIO_APP && (USUARIO_APP.publicador)}">Estás en aqui: <a href="https://www.empleo.gob.mx"  >Inicio</a> | Publicador&nbsp;&nbsp;</c:if>
		<c:if test="${not empty USUARIO_APP && (USUARIO_APP.administrador)}">Estás en aqui: <a href="https://www.empleo.gob.mx"  >Inicio</a> | Administrador Empresas&nbsp;&nbsp;</c:if>
		<c:if test="${not empty USUARIO_APP && (!USUARIO_APP.administrador&&!USUARIO_APP.publicador)}">Estás en aqui: <a href="https://www.empleo.gob.mx"  >Inicio</a> | Herramientas del Sitio | Inscripción al portal y login | <a href="<%=context %>/es_mx/empleo/Empresas"  >Empresas</a> | Espacio para Empresas&nbsp;&nbsp;</c:if>
<script type="text/javascript" src="http://s7.addthis.com/js/250/addthis_widget.js#username=xa-4bfafaee595d62bd" ></script>
        </div>
        
        
        
 		<ul class="redesSoc">
			<li><a target="_self" href="https://www.facebook.com/empleogobmx" class="fbk">Facebook</a></li>
			<li><a target="_self" href="https://twitter.com/empleogob_mx" class="twt">twitter</a></li>
		</ul>
		--%>       

    <c:if test="${not empty USUARIO_APP && (USUARIO_APP.publicador)}"><h2>Publicador</h2></c:if>
	<c:if test="${not empty USUARIO_APP && (USUARIO_APP.administrador)}"><h2>Administrador</h2></c:if>
	<c:if test="${not empty USUARIO_APP && (!USUARIO_APP.administrador&&!USUARIO_APP.publicador)}"><div class="page-header"><h2>Mi espacio</h2></div></c:if>
	<c:if test="${empty TAB}">
	<c:set var="classCurrentMiEspacio"  value="tab_current"/>
	<c:set var="classCurrentMisDatos"   value=""/>
	<c:set var="classCurrentMisOfertas" value=""/>
	<c:set var="classCurrentCandidatos" value=""/>
	<c:set var="idDivAppHolder" value="appHolder1"/>
</c:if>
<c:if test="${not empty TAB and TAB eq 'TAB_MI_ESPACIO'}">
	<c:set var="classCurrentMiEspacio"  value="tab_current"/>
	<c:set var="classCurrentMisDatos"   value=""/>
	<c:set var="classCurrentMisOfertas" value=""/>
	<c:set var="classCurrentCandidatos" value=""/>
	<c:set var="idDivAppHolder" value="appHolder1"/>
</c:if>
<c:if test="${not empty TAB and TAB eq 'TAB_MIS_DATOS'}">
	<c:set var="classCurrentMiEspacio"  value=""/>
	<c:set var="classCurrentMisDatos"   value="tab_current"/>
	<c:set var="classCurrentMisOfertas" value=""/>
	<c:set var="classCurrentCandidatos" value=""/>
	<c:set var="idDivAppHolder" value="appHolder2"/>
</c:if>
<c:if test="${not empty TAB and TAB eq 'TAB_MIS_OFERTAS'}">
	<c:set var="classCurrentMiEspacio"  value=""/>
	<c:set var="classCurrentMisDatos"   value=""/>
	<c:set var="classCurrentMisOfertas" value="tab_current"/>
	<c:set var="classCurrentCandidatos" value=""/>
	<c:set var="idDivAppHolder" value="appHolder3"/>
</c:if>
<c:if test="${not empty TAB and TAB eq 'TAB_MIS_CANDIDATOS'}">
	<c:set var="classCurrentMiEspacio"  value=""/>
	<c:set var="classCurrentMisDatos"   value=""/>
	<c:set var="classCurrentMisOfertas" value=""/>
	<c:set var="classCurrentCandidatos" value="tab_current"/>
	<c:set var="idDivAppHolder" value="appHolder4"/>
</c:if>

	<%--
		<div id="datos_usuario">
			 <c:if test="${not empty USUARIO_APP && USUARIO_APP.empresa}">
			 	<div class="holder_cerrar">
			 		 <a id="cerrar_sesion" href="<%=contextApp %>logout.do">Cerrar sesión</a>
			 	</div>
			 	
			 	<div class="holder_foto">
			 		<img alt="foto del usuario" src="<%=contextApp %>imageAction.do?method=getLogotipoEmpresa&<%=("captcha?time="+ Calendar.getInstance().getTimeInMillis())%>"/>
	        		<p><a href="<%=contextApp %>uploadcompanylogo.do?method=init">Editar</a></p>
			 	</div>
			 	<div class="datos_usuario">
			 		<h2 class="nombre" id="nombre">${empresaheader.nombreEmpresa}</h2>
			        <ul>
			        	<li>Representante: ${empresaheader.contactoEmpresa}</li>
			            <li>ID del Portal del Empleo: ${empresaheader.idPortalEmpleo}</li>
			            <li>${empresaheader.tipoPersona}</li>
			            <c:if test="${empresaheader.idTipoPersona eq 1}">
			            	<li>Fecha de nacimiento: ${empresafechaheader}</li>
			            </c:if>
			            <c:if test="${empresaheader.idTipoPersona eq 2}">
			            	<li>Fecha de acta constitutiva: ${empresafechaheader}</li>
			            </c:if>
			        </ul>
					 	
			 	</div>
			 	<div class="perfil_call">
			 		<p>Siempre podrás <a href="<%=contextApp %>edicionEmpresa.do?method=init" class="c_naranja">actualizar los datos</a> de tu empresa</p>
	        		<p style="text-align: center;"><img src="images/ico_misDatos.gif"/></p>
			 	</div>
			 	
			 	<br clear="all" />
			 	
			 	<ul class="nav_espacioCandidatos">
			        <li class="tab_miEspacio ${classCurrentMiEspacio}">
			        	<a href="${context}miEspacioEmpresas.do">Mi espacio</a>
			        </li>
			        <li class="tab_misDatos ${classCurrentMisDatos}">
			        	<a href="${context}edicionEmpresa.do?method=init">Mis datos</a>
			        </li>
			        <li class="tab_misOfertasEmpleo ${classCurrentMisOfertas}">
			        	<a href="${context}OfertaNavegacion.do?method=init">Mis ofertas de empleo</a>
			        </li>
			        <li class="tab_buscadorCandidatos ${classCurrentCandidatos}">
			        	<a href="javascript:validaOfertasActivas()">Buscador de candidatos</a>
			        </li>
			    </ul>
			 	
			 </c:if>
			 
			 <br clear="all" />
			 
		</div>
		--%>
		<c:if test="${not empty USUARIO_APP && (USUARIO_APP.administrador || USUARIO_APP.publicador)}">
			
			<div id="menuAdmin">
				<tiles:insert name="menu"/>
			</div>
			
		</c:if>
		
			<div class="app_holder" id="${idDivAppHolder}">
			<div class="app">
	    		<tiles:insert name="body"/>
        	</div>
		</div>
	<%-- <jsp:include page="footerResp.jsp" />
	<jsp:include page="footerSimpleResp.jsp" />
	--%>
	<jsp:include page="/WEB-INF/template/footer.jsp" />
	
</div>


<%--	<div class="container">
		<jsp:include page="/WEB-INF/template/empresaHeader1Resp.jsp" />
	    <jsp:include page="templateNoFrames/headerResponsive.jsp" />
	    <jsp:include page="/WEB-INF/template/buscador.jsp"/>
	    <jsp:include page="/WEB-INF/template/ayuda.jsp"/>
		<tiles:insert name="body"/>
	    <jsp:include page="/WEB-INF/template/footer.jsp"/>
	</div>
	--%>

<%-- 
<jsp:include page="barraFooter.jsp" />
--%>


<script type="text/javascript"> 
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

    addLoadEvent(fileLinks);
-->
</script>
</body></html>

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