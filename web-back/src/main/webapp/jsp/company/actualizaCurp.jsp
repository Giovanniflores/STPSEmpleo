<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<fmt:setBundle basename="portal-web" var="messages"/>


<%String context = request.getContextPath() +"/";%>

<script type="text/javascript">
	dojo.require("dijit.dijit"); // loads the optimized dijit layer
	dojo.require("dijit.form.Form");
	dojo.require("dijit.Dialog");
	dojo.require("dijit.form.Button");
	dojo.require("dijit.form.ComboBox");
	dojo.require("dojo.data.ItemFileReadStore");
	dojo.require("dijit.form.Textarea");
	dojo.require("dijit.form.TextBox");
	dojo.require("dijit.form.ValidationTextBox");
	dojo.require("dijit.form.DateTextBox");
	dojo.require("dijit.form.FilteringSelect");

	
	function alertMsg(msg){
		alert(msg);
	}	

</script>

<noscript>Funciones de JavaScript desactivadas por navegador</noscript>


	<div dojoType="dojo.data.ItemFileReadStore" jsId="entidadNacimientoStore" url="${context}domicilio.do?method=obtenerEntidad" urlPreventCache="true"></div>	
	
	<form id="actualizaCurpForm" name="actualizaCurpForm" method="post" action="actualizaCurp.do" dojoType="dijit.form.Form">
	
		<input type="hidden" name="method" id="method" value="init" dojoType="dijit.form.TextBox"/>		
		<input type="hidden" name="idEntidadNacimiento" id="idEntidadNacimiento" value="" dojoType="dijit.form.TextBox"/>
		<input type="hidden" name="idGenero" id="idGenero" value="" dojoType="dijit.form.TextBox"/>
		
		<div id="divCurp" style="display:block">
		
			<h3>Identificamos que tu perfil está incompleto, te sugerimos proporciones la siguiente información:</h3>			
			<br>
		   	<label for="curp">* CURP</label>
           		<input type="text" id="curp"  name="curp" dojoType="dijit.form.ValidationTextBox" required="false" maxlength="18" 
           		uppercase="true" clearOnClose="true" trim="true" missingMessage="El campo curp es requerido" />
		   	<br><br>
			<label for="sexo">Sexo</label>
				<input type="radio" style="display:inline;float:none" name="genero" id="idGeneroMasculino" value="1" checked="checked"/>&nbsp;
				<label style="display:inline" for="idGeneroMasculino">Hombre</label>
				<input type="radio" style="display:inline;float:none" name="genero" id="idGeneroFemenino"  value="2"/>&nbsp;
				<label style="display:inline" for="idGeneroFemenino">Mujer</label>				
									
			<br><br>
			<label for="lugarNacimiento">Lugar de nacimiento</label>
				<select id="entidadNacimientoSelect" name="entidadNacimientoSelect"
						required="true" missingMessage="El campo entidad de nacimiento es requerido"
						invalidMessage="Lugar de nacimiento inválido."
				        value="" autocomplete="true"
				        dojotype="dijit.form.FilteringSelect" store="entidadNacimientoStore">
				</select>								
				
		   				
       </div>	
           	<p  style="text-align: center;">
           		<input type="button" id="btnValidar"    name="btnValidar"    class="boton_naranja"   
           			onclick="validarDatosSolicitados();" value="Validar" />
           		<input type="button" id="btnActualizar" name="btnActualizar" class="boton_naranja"    
           			onclick="actualizarDatosCurp();"     value="Actualizar datos" style="display:none;" />
           		<input type="button" id="btnCerrar"     name="btnCerrar"     class="boton_naranja"   
           			onclick="closeNotificacion();"       value="Cerrar" />
	           	
           	</p>
	</form>


	

