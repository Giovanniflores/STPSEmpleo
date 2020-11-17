<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<script>
	 function cancela(forma) {	 
	    	forma.method.value = "cancelar";
	    	forma.submit();
	    }
	</script>
	<style type="text/css">
		.Font80{
			font-size: 80%;
		}
	</style>
    <link href="${PATH_CSS_SWB_APP}estilos_mi_espacio.css" rel="stylesheet" type="text/css" /> 
<html:messages id="messages" message="true">
    <span class="Font80"><bean:write name="messages"/></span><br/>
</html:messages>
<form name="videocurriculumForm" id="videocurriculumForm" method="post" action="cargaCurriculum.do">
    <input type="hidden" name="method" value="registrar"/>
</form>
