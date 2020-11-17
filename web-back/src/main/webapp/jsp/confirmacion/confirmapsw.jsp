<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>

<link href="${PATH_CSS_SWB_APP}estilos_error.css" rel="stylesheet" type="text/css" />
<style type="text/css">
	.redFont{
		color: #FF0000;
	}
	.Font80{
		font-size: 80%;
	}
</style>
<div id="errorPage">
    <h2 class="titulo404">Acceso restringido</h2>   
    
	<html:messages id="errors">
		<span class="redFont Font80"><bean:write name="errors"/></span><br/>
	</html:messages>
	<html:messages id="messages" message="true">
		<span class="Font80"><bean:write name="messages"/></span><br/>
	</html:messages>

    <h3 class="subTituloSeccion">Intenta lo siguiente:</h3>
    <ol>
      <li>Ir a la página <a href="${confirmacionRegistroForm.home}">principal</a>.</li>
      <li>Iniciar <a href="${confirmacionRegistroForm.urlInicioSesion}">sesión</a>.</li>
      <li>Favor de <a href="${confirmacionRegistroForm.urlRegistro}">registrarse</a>.</li>
    </ol>    
<p>&nbsp;</p>
</div>
