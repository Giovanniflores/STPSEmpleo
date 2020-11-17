<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
<meta name="twitter:image:src" content="http://qa.empleo.gob.mx/css/images/compartir-contenido-tweetA.jpg">
<meta name="author" content="infotec">
</head>
<body>
<%
//    TODO Mensaje 
//String msg = getMensaje(request, "emp.registro.msg1");
String context = request.getContextPath() +"/";  

%>
<br/><br/><br/><br/>
Los datos han sido guardados exitosamente. Presione el siguiente vínculo para <a href="<%=context%>comregcontact.do?method=init" >continuar</a>

</body>
</html>