<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="mx.gob.stps.portal.web.infra.utils.Constantes"%>
    <%@page import="mx.gob.stps.portal.web.infra.utils.PropertiesLoader"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="url" value="${pageContext.request.requestURL}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="es" lang="es"> 
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Portal del Empleo </title>
	<meta property="og:title" content="Portal del Empleo">
<meta name="twitter:title" content="Portal del Empleo">
<meta property="og:description" content="Portal del Empleo">
<meta name="twitter:description" content="Portal del Empleo">
<meta name="description" content="Portal del Empleo">
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
<meta name="twitter:image:src:src" content="http://qa.empleo.gob.mx/css/images/compartir-contenido-tweetA.jpg">
<meta name="author" content="infotec">
</head>
<body class='claro'>

<%
String context = request.getContextPath() +"/";  
PropertiesLoader properties = PropertiesLoader.getInstance();
String urlRedirect = properties.getProperty("app.swb.redirect.home");
%>
<script src="dojotoolkit/dojo/dojo.js" djConfig="parseOnLoad: true, locale:'es'"></script>
<noscript>Funciones de JavaScript desactivadas por navegador</noscript>
<script type="text/javascript">
    dojo.require("dijit.dijit"); // loads the optimized dijit layer
	dojo.require("dijit.form.Form");    
    dojo.require("dijit._Calendar");
    dojo.require("dijit.form.Textarea");
    dojo.require("dijit.form.DateTextBox");    
    dojo.require("dijit.form.NumberTextBox");
    dojo.require("dijit.form.ValidationTextBox");
    dojo.require("dijit.form.TextBox");
	dojo.require("dijit.form.ComboBox");
	dojo.require("dojo.data.ItemFileReadStore");
	dojo.require("dijit.Dialog");
    dojo.require("dijit.form.Button");
    dojo.require("dijit.form.CheckBox");   
    dojo.require("dijit.form.FilteringSelect");
    dojo.require("dojo.parser");
</script>


<script type="text/javascript">
function redirectSWB(){
	window.top.location = '<%=urlRedirect%>';
}

function fRedirect(){
	setTimeout("redirectSWB()", 15000);
}

function enviarMensajeExito(){
	alert('Sus datos han sido registrados, personal del Servicio Nacional de Empleo se pondrá<br/> en contacto con usted para confirmar la información proporcionada o si desea llame<br/> al 01-800-841-20-20.');
}



function setMensaje(mensaje){
	dojo.byId('mensaje').innerHTML = mensaje;
	var vMensajeAlert = dijit.byId('MensajeAlert');
	vMensajeAlert.show();		
}

function enviarMensajeExitoDialog(){
		var vMensaje = 'Sus datos han sido registrados,<br/> personal del Servicio Nacional de Empleo se pondrá<br/> en contacto con usted para confirmar<br/> la información proporcionada<br/>  o si desea llame al 01-800-841-20-20.';
		setMensaje(vMensaje);
		//redirectSWB();
		fRedirect();
}
</script>
<form>
					<div dojoType="dijit.Dialog" id="MensajeAlert" title="Mensaje" style="display:none" draggable ="false">
						  <table width="400px" height="200px" >
							 <tr align="center">
								 <td><div id ="mensaje" style="width:400px;height:200px;overflow:auto;vertical-align:middle;"></div>&nbsp;</td>				 
							 </tr>
						 </table>	
					</div>     

</form>
					
<!-- <script>enviarMensajeExitoDialog();</script> -->
<p>El formulario ha sido enviado.</p>
<!-- <meta http-equiv="refresh" content="15;url=<%=urlRedirect%>" />  -->


  <script type="text/javascript">
        dojo.addOnLoad(enviarMensajeExitoDialog);
  </script>      
</body>
</html>