<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.Calendar, mx.gob.stps.portal.web.infra.utils.PropertiesLoader"%>
<%
	PropertiesLoader properties = PropertiesLoader.getInstance();
	String redirect = properties.getProperty("app.swb.redirect.home");
	String context = request.getContextPath() +"/";
%>
<script type="text/javascript">
	dojo.require("dijit.Dialog");
	dojo.require("dijit.form.FilteringSelect");
	dojo.require("dijit.form.Textarea");
	dojo.require("dijit.form.CheckBox");
	
	function validateData() {
		if (getRadioValue('Tema1') == null) {
			msgalert("Es necesario escoger una opción en la pregunta 1.");
			return false;
		}
		if (getRadioValue('Tema2') == null){
			msgalert("Es necesario escoger una opción en la pregunta 2.");
			return false;
		}
		if (getRadioValue('Tema3') == null){
			msgalert("Es necesario escoger una opción en la pregunta 3.");
			return false;
		}
		if (getRadioValue('Tema4') == null){
			msgalert("Es necesario escoger una opción en la pregunta 4.");
			return false;
		}		
		return true;
	}
	function validatemail(correo) {	
		var regExp = /^[a-zA-Z0-9_-]+(?:\.[a-zA-Z0-9_-]+)*@(?:[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?\.)+[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?$/;
		if (!regExp.test(correo.value)) {
			msgalert('Formato de correo electrónico no válido.');		
		}	
	}
	function validatetxt(exp) {	
		var regExp = /^[a-zA-Z áéíóúAÉÍÓÚÑñ]+$/;
		if (!regExp.test(exp)) {
			return false;		
		}
		return true;
	}
	function msgalert(msg){
		alert(msg);		
	}
	function doSubmitAjax() {
		if (validateData()) {
			var answer = confirm("¿Está seguro de enviar encuesta?");
			if (answer) {
				document.pollcommentForm.submit();
				alert('Los datos han sido enviados con éxito');
				//window.close();
			}
		}
	}
	function getRadioValue(idOrName) {
		var value = null;
		var element = document.getElementById(idOrName);
		var radioGroupName = null;  
		if (element == null) {
			radioGroupName = idOrName;
		} else {
			radioGroupName = element.name;     
		}
		if (radioGroupName == null) {
			return null;
		}
		var radios = document.getElementsByTagName('input');
		for (var i=0; i<radios.length; i++) {
			var input = radios[ i ];    
			if (input.type == 'radio' && input.name == radioGroupName && input.checked) {                          
				value = input.value;
				break;
			}
		}
		return value;
	}
</script>
<noscript>Funciones de JavaScript desactivadas por navegador</noscript>

<form id="pollcommentForm" name="pollcommentForm" action="<%=context%>pollcomment.do?method=savePollComment" method="post" autocomplete="off">
	<input type="hidden" name="method" id="method" value="savePollComment"/>
	<table width="400" cellspacing="0" cellpadding="0" border="0" id="contacttbl">
    <tbody>
        <tr>
            <td>
            <table width="400" cellspacing="10" cellpadding="0" border="0">
                <tbody>
                    <tr>
                        <td align="center" colspan="2"><strong>Encuesta</strong></td>
                    </tr>
                    <tr>
                        <td align="right" colspan="2"><hr size="1" />
                        </td>
                    </tr>
                    <tr>
                        <td width="50%" align="right">1.- &iquest;Qu&eacute; mejorar&iacute;as del nuevo Portal del empleo?</td>
                        <td>
                        	<input type="radio" name="Tema1" id="Tema11" value="1"><label for="Tema11">Dise&ntilde;o</label><br/>
                        	<input type="radio" name="Tema1" id="Tema12" value="2"><label for="Tema12">Sistema de b&uacute;squeda</label><br/>
							<input type="radio" name="Tema1" id="Tema13" value="3"><label for="Tema13">Oferta de empleo</label><br/>
                        </td>
                    </tr>
                    <tr>
                        <td width="50%" align="right">2.- &iquest;Crees que el Portal del Empleo es una opci&oacute;n adecuada para encontrar empleo?</td>
                        <td>
                        	<input type="radio" name="Tema2" id="Tema21" value="1"><label for="Tema21">Muy adecuada</label><br/>
                        	<input type="radio" name="Tema2" id="Tema22" value="2"><label for="Tema22">Adecuada</label><br/>
							<input type="radio" name="Tema2" id="Tema23" value="3"><label for="Tema23">Nada adecuada</label><br/>
                        </td>
                    </tr>
                    <tr>
                        <td width="50%" align="right">3.- &iquest;C&oacute;mo calificar&iacute;as el sistema de b&uacute;squeda del nuevo Portal del empleo?</td>
                        <td>
                        	<input type="radio" name="Tema3" id="Tema31" value="1"><label for="Tema31">Muy bueno</label><br/>
                        	<input type="radio" name="Tema3" id="Tema32" value="2"><label for="Tema32">Bueno</label><br/>
							<input type="radio" name="Tema3" id="Tema33" value="3"><label for="Tema33">Regular</label><br/>
							<input type="radio" name="Tema3" id="Tema34" value="4"><label for="Tema34">Malo</label><br/>
                        </td>
                    </tr>
                    <tr>
                        <td width="50%" align="right">4.- &iquest;Qu&eacute; te gustar&iacute;a conocer sobre las ofertas de empleo publicadas en el portal?</td>
                        <td>
                        	<input type="radio" name="Tema4" id="Tema41" value="1"><label for="Tema41">Funciones y responsabilidades</label><br/>
                        	<input type="radio" name="Tema4" id="Tema42" value="2"><label for="Tema42">Lo que ofrece la empresa</label><br/>
							<input type="radio" name="Tema4" id="Tema43" value="3"><label for="Tema43">Requisitos</label><br/>
				   		</td>
                    </tr>
                    <tr>
                        <td align="center" colspan="2"><input type="button" id="btnSave" class="textmin" onclick="doSubmitAjax();" name="btnSave" value="Enviar" />
                        <input type="button" id="btnCancel" value="Cerrar" class="textmin" onclick="window.close()" />
                        </td>
                    </tr>
                </tbody>
            </table>
            </td>
        </tr>
    </tbody>
	</table>	
</form>


