<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="es" lang="es"> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
//    TODO Mensaje 
//String msg = getMensaje(request, "emp.registro.msg1");
String context = request.getContextPath() +"/";  
%>
<br/><br/><br/><br/>
Los datos han sido guardados exitosamente. Presione el siguiente vínculo para <a href="<%=context%>comregpartnercompany.do?method=init" >continuar</a>

</body>
</html>