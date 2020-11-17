<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.Calendar" %>
<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
	String context=(String)application.getAttribute("DOMAIN_PORTAL");
	String contextApp=request.getContextPath();
	
	if (null != request.getSession().getAttribute("FROM_CIL"))
		context = (String)application.getAttribute("DOMAIN_PORTAL_CIL");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd"> 
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="es" lang="es"> 
<head> 
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<link rel="P3Pv1" href="https://www.empleo.gob.mx/w3c/p3p.xml" />
<meta http-equiv="X-UA-Compatible" content="IE=8" />
<meta http-equiv="Reply-to" content="empleo@stps.gob.mx" />

<meta name="Subject" content="Empleo" />
<meta name="language" content="spanish" />
<meta name="revisit" content="1 days" />
<meta name="geo.region" content="MX-DIF" />
<meta name="geo.placename" content="Mexico City, Mexico" /> 
<title><tiles:getAsString name="title" /> | Portal del Empleo</title>
	<meta property="og:title" content="<tiles:getAsString name="title" /> | Portal del Empleo">	
	<meta name="twitter:title" content="<tiles:getAsString name="title" /> | Portal del Empleo">
	<meta property="og:description" content="<tiles:getAsString name="description" />"/>
	<meta name="twitter:description" content="<tiles:getAsString name="description" />"/>
	<meta name="description" content="<tiles:getAsString name="description" />"/>
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
</style> 

<style>
.claro .dijitDialogTitleBar {
	background-color: #F47513;
	color: #FFFFFF;
    font-weight: bold;
    text-decoration: none;
    }
</style>

<link href="${PATH_CSS_SWB_APP}estilos_canal.css" rel="stylesheet" type="text/css" />
<link href="${PATH_CSS_SWB_APP}estilos_mi_espacio_admin.css" rel="stylesheet" type="text/css" />
<link href="${PATH_CSS_SWB_APP}estilos_lightBox.css" rel="stylesheet" type="text/css" />
<link href="${PATH_CSS_SWB_APP}estilos_tabla.css" rel="stylesheet" type="text/css" />
<link href="${PATH_CSS_SWB_APP}estilos_tabla_admin.css" rel="stylesheet" type="text/css" />
<link href="${PATH_CSS_SWB_APP}estilos_genericos.css" rel="stylesheet" type="text/css" />

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

<link rel="shortcut icon" media="all" type="image/x-icon" href="<%=context %>/work/models/empleo/css/favicon.ico" /> 
<link rel="alternate" type="application/rss+xml" title="Portal del Empleo" href="/RSS" /> 

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

	<script src="dojotoolkit/dojo/dojo.js" djConfig="parseOnLoad: true, locale:'es'"></script>

	<script type="text/javascript">
		dojo.require("dijit.dijit");
		dojo.require("dijit.Dialog");
	</script>
	
	<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js"></script>

	<script type="text/javascript" src="googiespell/AJS.js"></script>
	<script type="text/javascript" src="googiespell/googiespell.js"></script>
	<script type="text/javascript" src="googiespell/cookiesupport.js"></script>

	<link href="googiespell/googiespell.css" rel="stylesheet" type="text/css"/>
	
	<script type="text/javascript">
	dojo.require("dijit.dijit"); // loads the optimized dijit layer
	dojo.require("dijit.form.Form");
	dojo.require("dijit.Dialog");
	
	var dialogTestimonio;
	
	function showWindow(){
		if (!dialogTestimonio){
			dialogTestimonio = new dijit.Dialog({
		        title: 'Comparte tu testimonio',
		        href: '<%=request.getContextPath()%>/testimonio.do?method=init',
		        style: "width:400px; height:350px;",
		        showTitle: false,
		        draggable : false
		    });
		}
	
		dialogTestimonio.show();
	}
	
	function closeWindow(){
		dialogTestimonio.hide();
	}
	
	function doSubmit(method){
		if(document.testimonioForm.testimonio.value==''){
			dojo.byId('msg').innerHTML = 'Debe escribir su testimonio para poder guardarlo';
			dojo.byId('msg').style.color ='#FF0000';
		}else {
			document.testimonioForm.method.value=method;
			dojo.xhrPost(
					 {url: 'testimonio.do', form:'testimonioForm', timeout:180000, // Tiempo de espera 3 min
					  load: function(data){
						  closeWindow();				  
					  }
					 } );
		}
	}

	dojo.addOnLoad(function() {
		// ...
	});
	
	function editaFoto(){	
	 	var editaFotoWin = window.open("<%=response.encodeURL(request.getContextPath()+"/cargaFoto.do?method=init")%>", "CambiarImagen","height=500,width=400,resizable=yes,scrollbars=yes"); 	
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
<body class="claro">
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
<div class="wrapper">

	<jsp:include page="candidatoHeader1.jsp" />
	<jsp:include page="candidatoHeader2.jsp" />
	
	<%-- 
	<c:if test="${empty TAB}">
        <c:set var="classCurrentMiEspacio" value="tab_current"/>
        <c:set var="classCurrentMiPerfil" value=""/>
        <c:set var="classCurrentMisOfertas" value=""/>
        <c:set var="idDivAppHolder" value="appHolder"/>
	</c:if>
	
    <c:if test="${not empty TAB and TAB eq 'TAB_MI_ESPACIO'}">
        <c:set var="classCurrentMiEspacio" value="tab_current"/>
        <c:set var="classCurrentMiPerfil" value=""/>
        <c:set var="classCurrentMisOfertas" value=""/>
    	<c:if test="${not empty seccion and seccion eq 'otras-bolsas'}">
    		<c:set var="idDivAppHolder" value="appHolderMisOfertas"/>
    	</c:if>
    	<c:if test="${empty seccion}">
	        <c:set var="idDivAppHolder" value="appHolder"/>
        </c:if>
	</c:if>
    <c:if test="${not empty TAB and TAB eq 'TAB_MIS_DATOS'}">
        <c:set var="classCurrentMiEspacio" value=""/>
        <c:set var="classCurrentMiPerfil" value="tab_current"/>
        <c:set var="classCurrentMisOfertas" value=""/>
        <c:set var="idDivAppHolder" value="appHolder"/>
	</c:if>
    <c:if test="${not empty TAB and TAB eq 'TAB_MIS_OFERTAS'}">
        <c:set var="classCurrentMiEspacio" value=""/>
        <c:set var="classCurrentMiPerfil" value=""/>
        <c:set var="classCurrentMisOfertas" value="tab_current"/>
        <c:set var="idDivAppHolder" value="appHolderMisOfertas"/>
	</c:if>
	--%>
	

		<%--
		 <c:if test="${not empty USUARIO_APP && USUARIO_APP.candidato}">
		 	<div id="datos_usuario">
		 		<div class="holder_cerrar">
	        		<a id="cerrar_sesion" href="${context}logout.do">Cerrar sesión</a>
	        	</div>
	        	<div class="holder_foto">
					<img alt="foto del usuario" src="${context}imageAction.do?method=getFotoCandidato&<%="captcha?time="+ Calendar.getInstance().getTimeInMillis()%>"/>
					<p><a href="${context}cargaFoto.do?method=init">Editar</a></p>
	        	</div>
	        	<div class="datos_usuario">
		        	<h2 class="nombre" id="nombre">${candidatoheader.nombre}</h2>
		            <ul>
		            	<li id="curp">CURP: ${candidatoheader.curp}</li>
		                <li id="sexo">${candidatoheader.sexo}, ${candidatoheader.edad} años</li>
		                <li id="entidad">Lugar de nacimiento: ${candidatoheader.entidad}</li>
		                <li id="fechaalta">Fecha de alta en este portal: ${candidatoheader.fechaalta}</li>
		            </ul>
				</div>
				<div class="perfil_call">
		        	<p><a href="${context}perfil.do?method=init">Para ser contratado en las vacantes que aquí se publican, no requieres hacer pago o depósito alguno</p>
		            <p style="text-align: center;"><img alt="foto del usuario" src="images/ico_miPerfil.gif" /></p>
		        </div>
		        
		        <br clear="all" />
	
		        <ul class="nav_espacioCandidatos">
		        	<li class="tab_miEspacio ${classCurrentMiEspacio}">  <a href="${context}miEspacioCandidato.do">Mi espacio</a></li>
		            <li class="tab_miPerfil ${classCurrentMiPerfil}">    <a href="${context}perfil.do?method=init">Mi perfil</a></li>
		            <li class="tab_misOfertas ${classCurrentMisOfertas}"><a href="${context}misofertas.do?method=init">Mis ofertas laborales</a></li>
		        </ul>
		        
		 	</div>
		 	<div id="clearer" style="clear:both; height: 0"></div>
		 </c:if>
		 --%>
		 
		 
		 <c:if test="${not empty USUARIO_APP && (USUARIO_APP.administrador || USUARIO_APP.publicador)}"> 
			<style type="text/css">
				#menuAdmin{ width: 930px; font-family: Tahoma,Geneva,sans-serif; color: #333333; font-size: 11px; margin:auto}
				#menuAdmin ul{ list-style: none; margin-left: 2%; margin-right: 2%; margin-top: 24px;}
				#menuAdmin ul li{ float:left; margin-left: 5px; height: 36px; margin-bottom: 10px;}
				#menuAdmin ul li a{ padding: 6px; width: auto; text-decoration: none; color: #333333; border: 1px solid #dbdbdb; text-align: center; background: #FFFFFF);}
				#menuAdmin ul li a.adminCerrar{ background: #fe6a08; color: #ffffff}
				#menuAdmin ul li a.adminCerrar:hover{ background: #dc5700}
				#menuAdmin ul li a:hover{ background: #f2f2f2;}
				#menuAdmin ul li a:active{ background: #f2f2f2;}
			</style>
		 
			<div id="menuAdmin">
		    	<tiles:insert name="menu"/>
		    </div>
		</c:if> 
		

	        <div class="app">
	        	<tiles:insert name="body"/>
	        </div>

       <!--     <jsp:include page="footer.jsp" /> -->
           <jsp:include page="footerSimple.jsp" />

</div>


</body>
</html>
