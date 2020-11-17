<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%String context = request.getContextPath() +"/";%>

<style type="text/css">
	#dialogDetalle_underlay { background-color:gray; }
	#dialogEmpFrau_underlay { background-color:green; }
</style>

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
	dojo.require("dijit.form.FilteringSelect");

	dojo.addOnLoad(function() {
		// ... 
	});

	function doSubmit(method){
		dojo.byId('method').value=method;
		dojo.byId('registroEmpresaForm').submit();
	}

	function irMiEspacio(){
		/* TODO validaciones */
		doSubmit('toMiEspacio');
	}

	function irPerfilLaboral(){
		/* TODO validaciones */
		doSubmit('toMisDatos');
	}
</script>

<form name="registroEmpresaForm" id="registroEmpresaForm" method="post" action="registroEmpresa.do" dojoType="dijit.form.Form">
	<input type="hidden" name="method" id="method" value="init" dojoType="dijit.form.TextBox"/> <!-- indica el metodo a invocar -->

	<div id="registro_bienvenida" class="bloque">
		<h3 class="important c_naranja">¡Bienvenido, se guardó con éxito tu empresa!</h3>
	    <p>&nbsp;</p>

</form>