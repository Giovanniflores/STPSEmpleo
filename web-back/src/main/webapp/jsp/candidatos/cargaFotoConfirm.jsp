<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
    String context = request.getContextPath() +"/";
    pageContext.setAttribute("context", context);
%>

    <link href="${PATH_CSS_SWB_APP}estilos_mi_espacio.css" rel="stylesheet" type="text/css" />
  	<style type="text/css">
		.Font80{
			font-size: 80%;
		}
	</style>
    <script> 
	 function regresa(forma) {
	  	forma.method.value = "cancelar";
	   	forma.submit();
	 }
    </script>
	<noscript>Funciones de JavaScript desactivadas por navegador</noscript>    
    
<html:messages id="messages" message="true">
    <span class="Font80"><bean:write name="messages"/></span><br/>
</html:messages>
<html:form action="/cargaFoto.do" enctype="multipart/form-data" method="POST">
    <input type="hidden" name="method" value=""/>
<p style="text-align: center;" class="entero">
     <%--<input type="button" id="btnEnviar" value="Regresar" class="boton" onclick="javascript:regresa(this.form);"/>     --%>
     <input type="button" id="btnEnviar" value="Regresar" class="boton" onclick="javascript:window.location.href = '<c:url value="/miEspacioCandidato.do"/>';"/>
</p>
</html:form>
