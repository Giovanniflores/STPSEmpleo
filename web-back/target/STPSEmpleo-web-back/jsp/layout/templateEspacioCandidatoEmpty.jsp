<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.Calendar" %>
<%@ page import="mx.gob.stps.portal.web.infra.utils.PropertiesLoader"%>
<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%String context = request.getContextPath() +"/";%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd"> 
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="es" lang="es"> 
<head> 
	<link rel="P3Pv1" href="https://www.empleo.gob.mx/w3c/p3p.xml" />
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" /> 

	<title><tiles:getAsString name="title"/></title> 

	<link href='https://fonts.googleapis.com/css?family=Droid+Sans' rel='stylesheet' type='text/css' />

	<style type="text/css">
		@import "dojotoolkit/dojo/resources/dojo.css";
		@import "dojotoolkit/dijit/themes/claro/claro.css";
		@import "${PATH_CSS_SWB}css_aplicacion/otras-bolsas.css";
		@import "${PATH_CSS_SWB}css_registro_candidato/estilos_espacioCandidato.css";
	
		#menuAdmin{ width: 930px; font-family: Tahoma,Geneva,sans-serif; color: #333333; font-size: 11px; margin:auto}
		#menuAdmin ul{ list-style: none; margin-left: 2%; margin-right: 2%; margin-top: 24px;}
		#menuAdmin ul li{ float:left; margin-left: 5px; height: 36px; margin-bottom: 10px;}
		#menuAdmin ul li a{ padding: 6px; width: auto; text-decoration: none; color: #333333; border: 1px solid #dbdbdb; text-align: center; background: #FFFFFF);}
		#menuAdmin ul li a.adminCerrar{ background: #fe6a08; color: #ffffff}
		#menuAdmin ul li a.adminCerrar:hover{ background: #dc5700}
		#menuAdmin ul li a:hover{ background: #f2f2f2;}
		#menuAdmin ul li a:active{ background: #f2f2f2;}
	
		.claro .dijitDialogTitleBar {
			background-color: #F47513;
			color: #FFFFFF;
		    font-weight: bold;
		    text-decoration: none;
		}
	</style> 
	
	<script src="dojotoolkit/dojo/dojo.js" djConfig="parseOnLoad: true, locale:'es'"></script>
	
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
		        href: '<%=context%>testimonio.do?method=init',
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
	
	function editaFoto(){	
	 	var editaFotoWin = window.open("<%=response.encodeURL(context+"cargaFoto.do?method=init")%>", "CambiarImagen","height=500,width=400,resizable=yes,scrollbars=yes"); 	
	}
	
	</script>
<!-- 
	<%
	String urliFrameSWB = PropertiesLoader.getInstance().getProperty("miespacio.iframeswb.script");
	int height = 0;
	
	String action = (String)session.getAttribute("ACTION_REQUESTED");
	if (action!=null){
		if ("escolaridad".equalsIgnoreCase(action)){ 		 height = 8000;}
		
		/*if ("miEspacioCandidato".equalsIgnoreCase(action)){ 		 height = 1400;
		} else if ("perfil".equalsIgnoreCase(action)){ 				 height = 2900;
		} else if ("escolaridad".equalsIgnoreCase(action)){ 		 height = 3900;
		} else if ("experiencia".equalsIgnoreCase(action)){ 		 height = 1700;
		} else if ("expectativa".equalsIgnoreCase(action)){ 		 height = 1200;
		} else if ("misofertas".equalsIgnoreCase(action)){           height = 2400;
		} else if ("entrevistaProgramada".equalsIgnoreCase(action)){ height = 1200;
		} else if ("bolsasTrabajo".equalsIgnoreCase(action)){
			height = (request.getAttribute("LISTABOLSASEXTERNAS")==null &&
					  session.getAttribute("LISTABOLSASEXTERNAS")==null)?1000:2100;
		}*/
	}
	
	if (height>0){%>
	<script type="text/javascript">
		function updateIframeHeight() {
			var iframe = document.getElementById('hiddenIframe');
			var newHeight = parseInt(document.body.offsetHeight,10) + 10;
			iframe.src = '<%=urliFrameSWB%>?height=' + <%=height%>;
		}
	</script>
	<%}%>
	-->

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

	<div class="app_wrapper">
	    
	    <c:if test="${not empty USUARIO_APP && USUARIO_APP.candidato}">
	    
	    <div id="datos_usuario">
	    	<%--
			<div class="holder_cerrar">
	        	<a id="cerrar_sesion" href="${context}logout.do">Cerrar sesión</a>
	        </div>
	        --%>

			<div class="holder_foto">
				<img src="${context}imageAction.do?method=getImagen&<%="captcha?time="+ Calendar.getInstance().getTimeInMillis()%>" alt="foto del usuario" />
				<p><a href="<c:url value="/cargaFoto.do?method=init"/>">Editar</a></p>
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
	        	<p><a href="<c:url value="/perfil.do?method=init"/>">Para ser contratado en las vacantes que aquí se publican, no requieres hacer pago o depósito alguno</p>
	            <p style="text-align: center;"><img alt="foto del usuario" src="images/ico_miPerfil.gif" /></p>
	        </div>
	        
			<br clear="all" />
	
	        <ul class="nav_espacioCandidatos">
	        	<li class="tab_miEspacio ${classCurrentMiEspacio}">  <a href="<c:url value="/miEspacioCandidato.do"/>">Mi espacio</a></li>
	            <li class="tab_miPerfil ${classCurrentMiPerfil}">    <a href="<c:url value="/perfil.do?method=init"/>">Mi perfil</a></li>
	            <li class="tab_misOfertas ${classCurrentMisOfertas}"><a href="${context}misofertas.do?method=init">Mis ofertas laborales</a></li>
	        </ul>

	    </div>

		<div id="clearer" style="clear:both; height: 0"/>

		</c:if>

		<c:if test="${not empty USUARIO_APP && (USUARIO_APP.administrador || USUARIO_APP.publicador)}">
			<div id="menuAdmin">
		    	<tiles:insert name="menu"/>
		    </div>
		</c:if>    
	
	    <div class="app_holder" id="${idDivAppHolder}">
	        <div class="app">
	        	<tiles:insert name="body"/>
	        </div>
	    	
	    	<c:if test="${not empty USUARIO_APP && USUARIO_APP.candidato}">
	    	<c:if test="${empty SIN_ARTICULOS}">

	    	<div class="aside">
	    		<tiles:insert name="articulos"/>
	    	</div>

			</c:if>
	    	</c:if>
	    	
	        <br clear="all"/>
	    </div>
		
	</div>

	<iframe id="hiddenIframe" src="" width="1" height="1" frameborder="0" scrolling="no" >
		Iframes not supported.
	</iframe>

</body>
</html>
<%
	Calendar start = (Calendar)request.getAttribute("TIME-START");
	if (start!=null){
		Calendar end = Calendar.getInstance();
		long dif = end.getTimeInMillis() - start.getTimeInMillis();
		out.write("<!-- "+ dif +" ms -->");
	}	
%>