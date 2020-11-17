<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles"%>
<%@ page import="java.util.Calendar" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd"> 
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="es" lang="es"> 
<head> 
<link rel="P3Pv1" href="https://www.empleo.gob.mx/w3c/p3p.xml" /> 
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" /> 

<title><tiles:getAsString name="title"/></title>

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

<script src="dojotoolkit/dojo/dojo.js" djConfig="parseOnLoad: true, locale:'es'"></script>

<link href="${PATH_CSS_SWB_APP}estilos_empleo_admin.css" rel="stylesheet" type="text/css" />
<link href="${PATH_CSS_SWB_APP}estilos_canal.css" rel="stylesheet" type="text/css" />
<link href="${PATH_CSS_SWB_APP}estilos_mi_espacio_admin.css" rel="stylesheet" type="text/css" />
<link href="${PATH_CSS_SWB_APP}estilos_lightBox.css" rel="stylesheet" type="text/css" />
<link href="${PATH_CSS_SWB_APP}estilos_tabla.css" rel="stylesheet" type="text/css" />
<link href="${PATH_CSS_SWB_APP}estilos_tabla_admin.css" rel="stylesheet" type="text/css" />
<link href="${PATH_CSS_SWB_APP}estilos_genericos.css" rel="stylesheet" type="text/css" />

<link href='https://fonts.googleapis.com/css?family=Droid+Sans' rel='stylesheet' type='text/css' />

</head>

<body class='claro'>

<div id="wrapper">
	<div id="cuerpo_int">
		<div id="menuAdmin">
			<tiles:insert name="menu"/>
		</div>
        <div id="contenido_principal" style="margin: 0 0 0 40px; width: 856px;">
	        <div id="contenido">
				<tiles:insert name="body"/>
	        </div>          
    	</div>
	</div>
</div>
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