<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script type="text/javascript">

function doSubmit(method){
	document.RegContForm.method.value=method;
	document.RegContForm.submit();
}
 
</script>
<style type="text/css">
	.redFont{
		color: #FF0000;
	}
	.Font80{
		font-size: 80%;
	}
</style>
<html:messages id="errors">
	<span class="redFont Font80"><bean:write name="errors"/></span><br/>
</html:messages>

<html:messages id="messages" message="true">
	<span class="Font80"><bean:write name="messages"/></span><br/>
</html:messages>

<html:form action="regContForm" method="POST">

	<br/>

	<h3>Registro de contactos</h3>


		<input type="hidden" name="method" value="registrarContacto"/>
	<p>
		<input type="button" value="Test" class="boton" onclick="javascript:this.disabled=true;doSubmit('buscarDomicilio');" />
	</p>

	<p>
		<strong>Nombre*:</strong>
		<br/>
		<html:text property="nombreContacto" size="6" maxlength="6" />
		<br/>
		<input type="button" value="Validar CP" class="boton" onclick="javascript:void(0)" />
	</p>

	<p>
		<strong>Cargo*:</strong><br/>
		<html:text  property="cargo" size="50" maxlength="150" />
		<br/>
	</p>

	<p>
		<strong>Correo*:</strong><br/>
		<html:text  property="correoElectronico" size="50" maxlength="150" />
		<br/>
	</p>
	<p>
		<strong>idContacto*:</strong><br/>
		<html:text  property="idContacto" size="50" maxlength="150" />
		<br/>
	</p>
	<p>
		<strong>idEmpresa*:</strong><br/>
		<html:text  property="idEmpresa" size="50" maxlength="150" />
		<br/>
	</p>

</html:form>















