<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd"> 
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="es" lang="es"> 
<head>
	<style type="text/css"> 
		@import "dojotoolkit/dojo/resources/dojo.css";
		@import "dojotoolkit/dijit/themes/claro/claro.css";
	</style>
	<style type="text/css">
	.Font60{
		font-size: 60%;
	}
	</style>
	
	<link type="text/css" rel="stylesheet" href="${PATH_CSS_SWB_APP}ventana_perfil.css"/>
	<link type="text/css" rel="stylesheet" href="${PATH_CSS_SWB_APP}estilos_detalle_vacante.css"/>
	
	<script type="text/javascript">
		function closeWindow(){
			window.opener.document.formEmpresa.submit();
			self.close();
		}
	</script>
	<noscript>Funciones de JavaScript desactivadas por navegador</noscript>
	
</head>
<body class='claro'>
<form>
	<div id="capaConfirm">
	  	<h2>La Oficina del Servicio Nacional de Empleo más cercana a su domicilio se pondrá en contacto con usted en los próximos días. Gracias.</h2>
	   	<div class="entero">
				<html:messages id="messages" message="true">
				   <span class="Font60"><bean:write name="messages"/></span><br/>
				</html:messages>
	   	</div>
	
	       <div style="text-align: center;" class="entero">
				<input type="button" class="boton" id="btnCerrar"  value="Cerrar ventana" onclick="javascript:closeWindow();" />
	       </div>
	   </div>
</form>
</body>
</html>