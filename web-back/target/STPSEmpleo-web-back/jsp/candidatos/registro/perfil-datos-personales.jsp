<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<META HTTP-EQUIV="CACHE-CONTROL" CONTENT="NO-CACHE">
<META HTTP-EQUIV="EXPIRES" CONTENT="Sun, 23 Nov 2014 11:12:01 GMT">

<c:set var="regexpnumext">^[\w\d\sñÑ]{1,50}$</c:set>
<c:set var="regexpnumint">^[\w\d\sñÑ]{1,150}</c:set>
<c:set var="regexpcalle">^[\w\d\s\.áéíóúÁÉÍÓÚñÑ]{1,200}$</c:set>

<c:set var="regexpentrecalle">^[\w\d\s\.áéíóúÁÉÍÓÚñÑ]{1,200}$</c:set>
<c:set var="regexpmail">^[a-zA-Z0-9_-]+(?:\.[a-zA-Z0-9_-]+)*@(?:[a-zA-Z0-9](?:[a-zA-Z0-9-_]*[a-zA-Z0-9-])?\.)+[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?$</c:set>
<c:set var="regexpacceso">^(044|01)$</c:set>
<c:set var="regexplada">^[+]?\d{2,3}$</c:set>
<c:set var="regexptelefono">^[+]?\d{7,8}$</c:set>
<c:set var="regexpextension">^[+]?\d{0,8}$</c:set>
<c:set var="regexpsalario6">^[+]?\d{1,6}(\.\d{1,2})?$</c:set>
<c:set var="regexpsalario7">^[+]?\d{1,7}(\.\d{1,2})?$</c:set>
<c:set var="regexp_folioprospera">^\d{9}$</c:set>
<c:set var="regexp_foliointegranteprospera">^\d{9}[a-zA-Z0-9]{1}\d{6}$</c:set>

<c:set var="regexpcp">^[0-9]{5}</c:set>


 <%String context = request.getContextPath() +"/";
 	 String vProxy = "http://orangoo.com/newnox?lang=";
// 	 String vProxy = context + "SpellCheck.do?method=gogiespell&lang=";
	 pageContext.setAttribute("vProxy",vProxy);
 %>
<style type="text/css">
.suggestionsBox {
	background-color: #FFFFFF;
	border: 1px solid #4f6710;
	color: #FFFFFF;
	left: 180px;
	margin: -20px 0 0;
	position: absolute;
	width: 320px;
	z-index: 1000;
}

.suggestionList {
	margin: 0px;
	padding: 0px;
}

.suggestionList li {
	color: #000000;
	cursor: pointer;
	padding: 3px;
}

.suggestionList li:hover {
	background-color: #659CD8;
}

#carreraEspecialidadSelect_popup {
	width: 400px !important;
}
</style>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="js/helper/dateHelper.js"></script>
<script type="text/javascript" src="js/helper/messageHelper.js"></script>
<script type="text/javascript" src="js/registro/validateServiceAjax.js"></script>
<script type="text/javascript" src="googiespell/AJS.js"></script>
<script type="text/javascript" src="googiespell/googiespell.js"></script>
<script type="text/javascript" src="googiespell/cookiesupport.js"></script>
<!--  setting up the spellcheck -->
<!--  script type='text/javascript' src='/JavaScriptSpellCheck/include.js' ></script>-->

<link href="googiespell/googiespell.css" rel="stylesheet"
	type="text/css" />

<script type="text/javascript">
	var depGrado = new Array(); //Declara arreglo de dependencias de catalogo Grado de Estudios
	var depIdioma = new Array(); //Declara arreglo de dependencias de catalogo idiomas
</script>

<c:if test="${not empty registroCandidatoForm.gradosDependientes}">
	<script type="text/javascript">
		<c:forEach var="gradoDep" items="${registroCandidatoForm.gradosDependientes}" varStatus="indexGr">
		depGrado[parseInt('${indexGr.count - 1}')] = '${gradoDep}';
		</c:forEach>
	</script>
</c:if>

<c:if test="${not empty registroCandidatoForm.idiomasDependientes}">
	<script type="text/javascript">
		<c:forEach var="idiomaDep" items="${registroCandidatoForm.idiomasDependientes}" varStatus="indexIdi">
		depIdioma[parseInt('${indexIdi.count - 1}')] = '${idiomaDep}';
		</c:forEach>
	</script>
</c:if>

<script type="text/javascript">
	dojo.require("dijit.dijit"); // loads the optimized dijit layer
	dojo.require("dijit.form.Form");
	dojo.require("dijit.Dialog");
	dojo.require("dijit.form.Button");
	dojo.require("dijit.form.ComboBox");
	dojo.require("dojo.data.ItemFileReadStore");
	dojo.require("dijit.form.Textarea");
	dojo.require("dijit.form.SimpleTextarea");
	dojo.require("dijit.form.TextBox");
	dojo.require("dijit.form.ValidationTextBox");
	dojo.require("dijit.form.DateTextBox");
	dojo.require("dijit.form.FilteringSelect");
	dojo.require("dijit.form.MultiSelect");
	dojo.require("dijit.form.CheckBox");
	dojo.require("dijit.focus");
	require([ "dojo/parser", "dijit/form/RadioButton", "dijit/form/Button", // used for example purpose
	"dojo/domReady!" ]);

	dojo
			.addOnLoad(function() {
				entidadFederativaStore.url = "${context}domicilio.do?method=obtenerEntidad";
				entidadFederativaStore.close();
				dojo.style(dojo.byId('otra_opcion_empleo'), "display", "none");
				dijit.byId('idEntidadSelect').disabled = false;
				dijit.byId('idMunicipioSelect').disabled = false;
				dijit.byId('idColoniaSelect').disabled = false;

				dojo.byId('salarioPretendido').value = '';

				estableceAcceso();

				/*var vSpell = new GoogieSpell("googiespell/",
						'${context}SpellCheck.do?method=gogiespell&lang=');
				vSpell.setLanguages({
					'es' : 'Español'
				});
				//vSpell.hideLangWindow();
				//vSpell.decorateTextarea("descripcionExperiencia");

				var vSpell2 = new GoogieSpell("googiespell/",
						'${context}SpellCheck.do?method=gogiespell&lang=');
				vSpell2.setLanguages({
					'es' : 'Español'
				});
				vSpell2.decorateTextarea("funcionesDesempenadas");
				//Esconder el segundo trabajo que busca
				*/
				//validar idioma para que se puede tener focus
				actulizaCertificaciones();
				//validar certificado para que se puede tener el focus si le hace en el dom directo no se genera bien
				onSelectDominio();

				dijit.byId("motivoSelect").on('change', function(evt){
					var motivo = dijit.byId('motivoSelect').get('value');
					if (motivo == 18) { // Otro
						dojo.style(dojo.byId('motivoOtroContainer'), "display", "inline");
					} else {
						dojo.style(dojo.byId('motivoOtroContainer'), "display", "none");
					}
				});

                // Data from HTML Geolocation API, used to get the geographical position of a user.
                if (navigator.geolocation) {
                    navigator.geolocation.getCurrentPosition(function(position) {
                        console.log(position);
                        document.getElementById("positionCoordsLatitude").value = position.coords.latitude;
                        document.getElementById("positionCoordsLongitude").value = position.coords.longitude;
                    });
                }
			});

	function doSubmit(method) {
		dojo.byId('method').value = method;
		dojo.byId('registroCandidatoForm').submit();
	}

	function estableceAcceso() {

		if (dojo.byId('telefonoFijo').checked) {
			addInnerHtml('accesoDiv', '${registroCandidatoForm.accesoFijo}');
			//dojo.byId('accesoDiv').innerText = '${registroCandidatoForm.accesoFijo}';
			dojo.byId('acceso').value = '${registroCandidatoForm.accesoFijo}';
			dojo.byId('extension').value = '';
			dojo.byId('extension').disabled = false;

		} else if (dojo.byId('telefonoCelular').checked) {
			addInnerHtml('accesoDiv', '${registroCandidatoForm.accesoCelular}');
			//dojo.byId('accesoDiv').innerText = '${registroCandidatoForm.accesoCelular}';
			dojo.byId('acceso').value = '${registroCandidatoForm.accesoCelular}';
			dojo.byId('extension').value = '';
			dojo.byId('extension').disabled = true;

		} else {
			addInnerHtml('accesoDiv', '${registroCandidatoForm.accesoCelular}');
			//dojo.byId('accesoDiv').innerText = '${registroCandidatoForm.accesoCelular}';
			dojo.byId('acceso').value = '${registroCandidatoForm.accesoCelular}';
		}
	}

	function showMsgCancelar() {
		dijit.byId('dialogCancelar').show();
	}

	function registrar() {
		almacenaDatosPerfil('enviaDatosRegistro');
	}

	function editar() {
		almacenaDatosPerfil('enviaDatosActualizacion');
	}

	function almacenaDatosPerfil(method) {

		estableceValores();

		if (!validaCampos()) {
			return;
		}

		if (validaCorreoElectronicoUnico() == false) {
			var textBox = dijit.byId('correoElectronico');
			displayErrorMsg(textBox,
					'El correo electrónico ya se encuentra registrado.');
			alertMsg('El correo electrónico ya se encuentra registrado.');
			return;
		}
		dojo.byId('btnGuardar').disabled = true;
		doSubmit(method);
	}
	function validaSegundoCampoPrimeroLlenado(campo1, campo2) {

		var control = dijit.byId(campo1);

		if (control && !control.value == '') {
			if (!validaCampo(campo2))
				return false;
		}
	}

	function validaSegundoTrabajoDeseado() {
		//Validar si esta bien la primero opcion capturado si no esta piden primero llenar primero
		//Validar los datos OtroOpción de empleo despues validacion de ocupaciondeseada

		//validacion si tiene una ocupacion selecionado entonces debe estar seleciona
		// Si el combo no esta selecionado y la experiencia es vaico es valido

		if (!validaFilterSelect('idOcupacionDeseada2', 'ocupacionDeseada2Select', 'listaOcupaciones2', 'setOcupacion2')) {
			var msgerr = "Se requiere seleccionar una opción válida del catálogo de ocupaciones";

			dijit.byId('ocupacionDeseada2Select').focus();
			displayErrorMsg(dijit.byId('ocupacionDeseada2Select'), msgerr);
			alertMsg(msgerr);
			return false;

		} else {

			var control = dojo.byId('ocupacionDeseada2Select');
			var control2 = dojo.byId('idOcupacionDeseada2');

			//showErrorMsg('else ' + !validaCapoSelectSinError('experiencia2Select') + " : " +control.value == 0 + ":" + control.value=='' +":"+ control2.get('value') == '');

			if (((control.value == 0 || control.value == '') && control2.value == '')) {
				if (!validaCapoSelectSinError('experiencia2Select')) {
					return true;
				}
			}

			if (!validaCampoSelect('experiencia2Select')) {
				return false;
			}
		}
		return true;
	}

	function validateConfidencial() {
		var confidencial = "";
		if (document.getElementById('radioOne').checked) {
			confidencial = document.getElementById('radioOne').value;

		}
		if (document.getElementById('radioTwo').checked) {
			confidencial = document.getElementById('radioTwo').value;
		}
		if (confidencial == "") {
			confidencial = document.getElementById('radioOne').focus();
			dijit.byId('radioOne').focus();
			alertMsg('Selecciona la Confidencialidad de datos.');
			return false;
		}
		dojo.attr(dojo.byId("confidencial"), "value", confidencial);

		return true
	}

	function validaCampos() {
		if (!validaCampo('calle')) {
			return false;
		}
		if (!validaCampo('numeroExterior')) {
			return false;
		}
		if (!validaCampoSelect('idEntidadSelect')) {
			return false;
		}
		if (!validaCampoSelect('idMunicipioSelect')) {
			return false;
		}
		if (!validaCampoSelect('idColoniaSelect')) {
			return false;
		}

		if (!validateConfidencial()) {
			return false;
		}

		if (!validaCampo('clave')) {
			return false;
		}
		if (!validaCampo('telefono')) {
			return false;
		}

		if (!changePhoneSizeRequired()) {
			alertMsg('Favor de verificar la clave lada y el número telefónico.');
			return false;
		}

		//if (!validaCampo('extension')) return false;
		if (dijit.byId('extension') && dijit.byId('extension').get('value')) {
			if (!dijit.byId('extension').isValid()) {
				alertMsg(dijit.byId('extension').get("invalidMessage"));
				return false;
			}
		}

		if (dojo.byId('mediocontactoCorreo').checked) {
			if (dijit.byId('correoElectronico').value == '') {
				dijit.byId('correElectronico').focus();
				alertMsg(dijit.byId('correoElectronico').get("missingMessage"));
				return false;
			}

			if (!dijit.byId('correoElectronico').isValid()) {
				dijit.byId('correElectronico').focus();
				alertMsg(dijit.byId('correoElectronico').get("invalidMessage"));
				return false;
			}
		}
		<c:if test="${registroCandidatoForm.registroPPC}">
		if (!validarCorreoElectronica('correoElectronico')) {
			return false;
		}
		</c:if>

		<c:if test="${not registroCandidatoForm.registroPPC}">
		if (dojo.byId('notificarCorreo').checked) {
			if (!validarCorreoElectronica('correoElectronico')) {
				return false;
			}
		}
		</c:if>

		if (!document.getElementById('mediocontactoTel').checked
				&& !document.getElementById('mediocontactoCorreo').checked) {

			dijit.byId('mediocontactoTel').focus();
			alertMsg('Es requerido que indique un medio de contacto');
			//displayErrorMsg(dijit.byId('mediocontactoTel'), dijit.byId('mediocontactoTel').get("missingMessage"));
			return false;
		}

		if (!validaCelularRequeridoRecibirOfertasError()) {
			return false;
		}
		if (!validaCampoSelect('idGradoEstudioSelect')) {
			return false;
		}
		if (!validaCampoSelect('carreraEspecialidadSelect')) {
			return false;
		}
		if (!validaCampoSelect('situacionAcademicaSelect')) {
			return false;
		}

		if (!validaCampoNoRequerido('idiomaSelect')){
			return false;
		}

		if (!validaCampoNoRequerido('dominioSelect')){
			return false;
		}

		if (!validaCampoNoRequerido('certificacionSelect')){
			return false;
		}

		if (!validaProspera()) {
			return false;
		}
		
		if (!dojo.byId('conocimientoCompNinguno').checked
				&& !dojo.byId('conocimientoCompProcesadorTxt').checked
				&& !dojo.byId('conocimientoCompHojaCal').checked
				&& !dojo.byId('conocimientoCompInternet').checked
				&& !dojo.byId('conocimientoCompRedes').checked) {
			dijit.byId('conocimientoCompNinguno').focus();
			alertMsg('Es requerido que indique si tiene conocimientos en computación.');
			//displayErrorMsg(dijit.byId('conocimientoCompProcesadorTxt'), dijit.byId('conocimientoCompProcesadorTxt').get("missingMessage"));
			return false;
		}

		// Habilidades

		var ischecked = false;
		var habilidaChecks = document.getElementsByName('idHabilidades');
		var numeroHabilidades = 0;

		if (habilidaChecks && habilidaChecks.length) {
			for (var i = 0; i < habilidaChecks.length; i++) {
				if (habilidaChecks[i].checked) {
					numeroHabilidades++;

				}
			}
		}

		if (numeroHabilidades > 0)
			ischecked = true;
		if (!ischecked) {
			
			dijit.byId('idHabilidades1').focus();
			message('Debe elegir por lo menos una habilidad');

			return false;
		}
		if (numeroHabilidades > 5) {

			dijit.byId('idHabilidades1').focus();
			message('Debe seleccionar máximo 5 habilidades y actitudes que te caracterizan.');

			return false;
		}

		if (dojo.byId('idOcupacionDeseada')
				&& (dojo.byId('idOcupacionDeseada').value == '' || dojo
						.byId('idOcupacionDeseada').value == '0')) {
			var msgerr = "Se requiere seleccionar una opción válida del catálogo de ocupaciones";

			dijit.byId('ocupacionDeseada').focus();
			//displayErrorMsg(dijit.byId('ocupacionDeseada'), msgerr);
			alertMsg(msgerr);
			return false;
		}

		//validacion de ocupacionDeseada 
		if (!validaTrabajoDeseado()) {
			return false;
		}

		if (!validaSegundoTrabajoDeseado())
			return false;

		if (dojo.byId('descripcionExperiencia')
				&& dojo.byId('descripcionExperiencia').value == '') {
			alertMsg('Es necesario describir tu experiencia.');

			dijit.byId('descripcionExperiencia').focus();
			return false;
		}
		// validar los campos de captura trabajo actual

		<c:if test="${registroCandidatoForm.registroPPC}">
		if (!llenadoCamposTrabajoActual()) {
			return false;
		}
		</c:if>
		<c:if test="${not registroCandidatoForm.registroPPC}">
		if (!llenadoComposTrabajoActualNoRequerido()) {
			return false;
		}
		</c:if>

		if (!validaCampoSelect('motivoSelect')) {
			return false;
		}
		if (!validaCampoSelect('diaSelect')) {
			return false;
		}
		if (!validaCampoSelect('mesSelect')) {
			return false;
		}
		if (!validaCampoSelect('anioSelect')) {
			return false;
		}

		var dia = dijit.byId('diaSelect').get('value');
		var mes = dijit.byId('mesSelect').get('value');
		var anio = dijit.byId('anioSelect').get('value');

		if (dia == '0') {
			dijit.byId('diaSelect').focus();
			//dijit.byId('diaSelect').blur();
			alertMsg(dijit.byId('diaSelect').get("missingMessage"));
			return false;
		}

		if (mes == '0') {
			dijit.byId('mesSelect').focus();
			//dijit.byId('mesSelect').blur();
			alertMsg(dijit.byId('mesSelect').get("missingMessage"));
			return false;
		}

		if (anio == '0') {
			dijit.byId('anioSelect').focus();
			//dijit.byId('anioSelect').blur();
			alertMsg(dijit.byId('anioSelect').get("missingMessage"));
			return false;
		}

		var hoy = new Date();
		var fhBuscaEmp = new Date();
		fhBuscaEmp.setFullYear(anio, --mes, dia);
		if (!validateFechaExisteDijit('diaSelect', 'mesSelect', 'anioSelect')) {
			dijit.byId(diaSelect).focus();
			alertMsg("seleciona un fecha valido");
			return false;
		}
		if (fhBuscaEmp > hoy) {
			alertMsg('La fecha en que comenzaste a buscar trabajo no puede ser mayor al dia de hoy.');
			return false;
		}

		//medios
		var ischecked = false;
		var mediosCheck = document.getElementsByName('idOtrosMedios');
		var count = 0;

		if (mediosCheck && mediosCheck.length) {
			for (var i = 0; i < mediosCheck.length; i++) {
				if (mediosCheck[i].checked) {
					count++;
				}
			}
		}

		if (count > 0) {
			ischecked = true;
		}

		if (!ischecked) {
			dijit.byId('idOtrosMedios1').focus();
			message('Seleccione al menos un medio que haya utilizado para buscar trabajo');
			return false;
		}
		if (mediosCheck[0].checked && count > 1) {
			dijit.byId('idOtrosMedios1').focus();
			message('Si selecciona ninguno no puede seleccionar otro medio');
			
			return false;
		}
		if (!validaCampoSelect('enterasteSelect')) {
			return false;
		}

		if (!dojo.byId('aceptacionTerminos').checked) {
			alertMsg('Es necesario que indique si acepta los terminos y condiciones del Portal del Empleo');
			//displayErrorMsg(dijit.byId('aceptacionTerminos'), dijit.byId('aceptacionTerminos').get("missingMessage"));
			return false;
		}

		//}

		return true;
	}

	function validarCorreoElectronica(campo) {
		campoControl = dijit.byId(campo);
		if (campoControl.value == '') {

			dijit.byId(campoControl).focus();
			alertMsg(campoControl.get("missingMessage"));
			return false;
		}

		if (!campoControl.isValid()) {

			campoControl.focus();
			alertMsg(campoControl.get("invalidMessage"));
			return false;
		}
		return true;
	}

	function llenadoComposTrabajoActualNoRequerido() {
		if (!validaCampoNoRequerido('nombreSocialEmpresa'))
			return false;
		if (!validaCampoNoRequerido('jeraquiaSelect'))
			return false;
		if (!validaCampoNoRequerido('personaACargoSelect'))
			return false;
		if (!validaCampoNoRequeridoConDifValor('salarioRecibido',0))
			return false;
		if (!validateFecha('diaLaboresInicialSelect',
				'mesLaboresInicialSelect', 'anioLaboresInicialSelect', false))
			return false;
		if (!validateFecha('diaLaboresFinalSelect', 'mesLaboresFinalSelect',
				'anioLaboresFinalSelect', false))
			return false;
		return true;
	}
	function llenadoCamposTrabajoActual() {
		if (!validaCampo('nombreSocialEmpresa'))
			return false;
		if (!validaCampo('puestoDesempenado'))
			return false;
		if (!validaCampoSelect('jeraquiaSelect'))
			return false;
		if (!validaCampoSelect('personaACargoSelect'))
			return false;
		if (!validaCampoNotZero('salarioRecibido'))
			return false;
		var fechaInicio = validateFromCombos('diaLaboresInicialSelect',
				'mesLaboresInicialSelect', 'anioLaboresInicialSelect');
		if (fechaInicio == 0)
			return false;
		var fechaFin = validateFromCombos('diaLaboresFinalSelect',
				'mesLaboresFinalSelect', 'anioLaboresFinalSelect');
		if (fechaFin == 0)
			return false;
		if (!validateDate1ToDate2(fechaInicio, fechaFin,
				'La fecha de ingreso no puede ser mayor al la fecha de terminación.'))
			return false;

		if (!validateDateToHoy(fechaFin,
				'La fecha de terminación no puede ser mayor al dia de hoy.'))
			return false;

		if (dojo.byId('funcionesDesempenadas')
				&& dojo.byId('funcionesDesempenadas').value == '') {

			dojo.byId('funcionesDesempenadas').focus();
			alertMsg('Es necesario indicar funciones desempeñadas.');
			return false;
		}
		return true;
	}

	function validaCapoSelectSinError(campo) {
		var control = dijit.byId(campo);

		if (control && control.get('value') == '') {

			//dijit.showTooltip(control.get("missingMessage"), control.domNode, control.get("tooltipPosition"), !control.isLeftToRight());
			return false;
		}

		return true;
	}

	function alertMsg(msg) {
		message(msg);
	}

	function replaceDialogMsg(errMsg) {
		var textnode = document.createTextNode(errMsg);
		var item = document.getElementById('errorMsgArea');
		item.replaceChild(textnode, item.childNodes[0]);
		dojo.byId('errorDialog').style.display = 'block';
	}

	function validaCorreoRequeridoMedioContacto() {
		var checked = dojo.byId('mediocontactoCorreo').checked;
		validaCorreoRequerido(checked);
	}

	function validaCorreoRequeridoRecibirOfertas() {
		var checked = dojo.byId('notificarCorreo').checked;
		validaCorreoRequerido(checked);
	}

	function validaCorreoRequerido(checked) {
		var control = dijit.byId('correoElectronico');

		if (checked) {

			if (control && control.value == '') {
				displayErrorMsg(control, control.get("missingMessage"));
				return false;
			}

			if (!control.isValid()) {
				displayErrorMsg(control, control.get("invalidMessage"));
				return false;
			}
		} else {
			var originalValidator = control.validator;
			control.validator = function() {
				return true;
			};
			control.validate();
			control.validator = originalValidator;
		}
	}

	function validaCelularRequeridoRecibirOfertas() {
		var controltel = dijit.byId('telefono');
	
		if (dojo.byId('notificarCel').checked) {

			if (!dojo.byId('telefonoCelular').checked) {
				displayErrorMsg(controltel,
						'Es necesario que indique un número celular para recibir ofertas.');
			}

			if (controltel.value == '') {
				displayErrorMsg(controltel, controltel.get("missingMessage"));
				return false;
			}

			if (!controltel.isValid()) {
				displayErrorMsg(controltel, controltel.get("invalidMessage"));
				return false;
			}
		} else {
			var originalValidator = controltel.validator;
			controltel.validator = function() {
				return true;
			};
			controltel.validate();
			controltel.validator = originalValidator;
		}
	}

	function validaCelularRequeridoRecibirOfertasError() {
		var controltel = dijit.byId('telefono');
		var validate = true;
		var message = ""
		if (dojo.byId('notificarCel').checked) {

			if (!dojo.byId('telefonoCelular').checked) {
				messageFocus2('telefono','No tiene registrado un número de celular.');
				//dijit.byId('telefonoCelular').focus();
				//message('No tiene registrado un número de celular.');
				validate = false;
			}

			if (controltel.value == '') {
				messageFocus2('telefono',controltel.get("missingMessage"));
				a
				validate = false;
			}

			if (!controltel.isValid()) {
				//message = controltel.get("invalidMessage");
				messageFocus2('telefono',controltel.get("invalidMessage"));
				
				validate = false;
			}
			
			return validate;

		} else {
			var originalValidator = controltel.validator;
			controltel.validator = function() {
				return true;
			};
			controltel.validate();
			controltel.validator = originalValidator;
		}
		return true;
	}

	function displayErrorMsg(textBox, errmsg) {
		var originalValidator = textBox.validator;
		textBox.validator = function() {
			return false;
		};
		textBox.validate();
		textBox.validator = originalValidator;

		dijit.showTooltip(
		//textBox.get("invalidMessage"),
		errmsg, textBox.domNode, textBox.get("tooltipPosition"), !textBox
				.isLeftToRight());
		textBox.focus();
	}

	function limpiarDomicilio() {
		dijit.byId('idEntidadSelect').attr('value', "");
		dijit.byId('idMunicipioSelect').attr('value', "");
		dijit.byId('idColoniaSelect').attr('value', "");
	}

	function estableceValores() {
		dojo.byId('idEntidad').value = dijit.byId('idEntidadSelect').get('value');
		dojo.byId('idMunicipio').value = dijit.byId('idMunicipioSelect').get('value');
		dojo.byId('idColonia').value = dijit.byId('idColoniaSelect').get('value');
		dojo.byId('idGradoEstudio').value = dijit.byId('idGradoEstudioSelect').get('value');
		dojo.byId('idCarreraEspecialidad').value = dijit.byId('carreraEspecialidadSelect').get('value');
		dojo.byId('idSituacionAcademica').value = dijit.byId('situacionAcademicaSelect').get('value');
		dojo.byId('idIdioma').value = dijit.byId('idiomaSelect').get('value');
		dojo.byId('idCertificacion').value = dijit.byId('certificacionSelect').get('value');
		dojo.byId('idDominio').value = dijit.byId('dominioSelect').get('value');
		dojo.byId('idExperienciaTotal').value = dijit.byId('experienciaTotalSelect').get('value');
		dojo.byId('idExperiencia2').value = dijit.byId('experiencia2Select').get('value');
		dojo.byId('idTipoContratoDeseado').value = dijit.byId('tiposContratoSelect').get('value');
		dojo.byId('idMotivo').value = dijit.byId('motivoSelect').get('value');
		dojo.byId('idComoEnteraste').value = dijit.byId('enterasteSelect').get('value');
		dojo.byId('diaBuscar').value = dijit.byId('diaSelect').get('value');
		dojo.byId('mesBuscar').value = dijit.byId('mesSelect').get('value');
		dojo.byId('anioBuscar').value = dijit.byId('anioSelect').get('value');
		dojo.byId('idPersonaACargo').value = dijit.byId('personaACargoSelect').get('value');
		dojo.byId('idJerquia').value = dijit.byId('jeraquiaSelect').get('value');
		dojo.byId('diaLaboresInicial').value = dijit.byId('diaLaboresInicialSelect').get('value');
		dojo.byId('mesLaboresInicial').value = dijit.byId('mesLaboresInicialSelect').get('value');
		dojo.byId('anioLaboresInicial').value = dijit.byId('anioLaboresInicialSelect').get('value');
		dojo.byId('diaLaboresFinal').value = dijit.byId('diaLaboresFinalSelect').get('value');
		dojo.byId('mesLaboresFinal').value = dijit.byId('mesLaboresFinalSelect').get('value');
		dojo.byId('anioLaboresFinal').value = dijit.byId('anioLaboresFinalSelect').get('value');
		dojo.byId('omitirTrabajoEnPerfil').value = getValueCheckbox('omitirTrabajoEnPerfilCh');

		if (dijit.byId('apoyoProsperaSi').get('checked')) {
			dojo.byId('apoyoProspera').value = dijit.byId('apoyoProsperaSi').get('value');
		} else if (dijit.byId('apoyoProsperaNo').get('checked')) {
			dojo.byId('apoyoProspera').value = dijit.byId('apoyoProsperaNo').get('value');
		}
	}

	function getValueCheckbox(field) {
		var lfckv = document.getElementById(field).checked;
		if (lfckv) {
			return 1
		}
		return 0;
	}
	function getValueRadio(field) {
		var test = document.getElementsByName(field);
		var sizes = test.length;

		for (i = 0; i < sizes; i++) {
			if (test[i].checked == true) {
				return test[i].value;
			}
		}
		return 0;
	}
	function trabaja(trab) {
		dijit.byId('motivoSelect').attr('value', "");
		motivosBuscaTrabajoStore.url = "${context}registro.do?method=motivosBuscaTrabajo&idTrab="
				+ trab;
		motivosBuscaTrabajoStore.close();

	}

	function changePhoneSizeRequired() {

		var vClave = dijit.byId('clave');
		var vTelefono = dijit.byId('telefono');

		if (vClave.value.length < 2) {
			vClave.focus();
			displayErrorMsg(vClave,
					'Debe proporcionar una clave LADA de 2 ó 3 caracteres');
			return false;
		} else if (vClave.value.length == 2) {
			if (vTelefono.value.length != 8) {
				displayErrorMsg(vTelefono,
						'Debe proporcionar un teléfono de 8 dígitos');
				return false;
			}
		} else if (vClave.value.length == 3) {
			if (vTelefono.value.length != 7) {
				displayErrorMsg(vTelefono,
						'Debe proporcionar un teléfono de 7 dígitos');
				return false;
			}
		}

		return true;
	}

	function actualizaCarreras() {
		var vGrado = dijit.byId('idGradoEstudioSelect').get('value');
		var vIdCatDep = depGrado[vGrado];

		if (vIdCatDep && vIdCatDep != 0) {
			carreraEspecialidadStore.url = '';
			carreraEspecialidadStore.close();
			dijit.byId('carreraEspecialidadSelect').reset();
			carreraEspecialidadStore.url = "${context}registro.do?method=carreras&idCatDep="
					+ vIdCatDep;
			carreraEspecialidadStore.close();
		}

		situacionAcademicaStore.url = '';
		situacionAcademicaStore.close();
		dijit.byId('situacionAcademicaSelect').reset();
		situacionAcademicaStore.url = "${context}registro.do?method=situacionesAcademicas&idEscolaridad="
				+ vGrado;
		situacionAcademicaStore.close();
	}

	//Actualiza el combo de idioma despues de haberlo selecionado
	function actulizaCertificaciones() {
		var vIdioma = dijit.byId('idiomaSelect').get('value');

		var vIdIdiomaDep = depIdioma[vIdioma];

		setLabelToLabelAndRequerido('dominioSelect', 'labelDominioSelect',
				'Dominio del idioma', false);
		if (vIdioma == 1 || vIdioma == '') {
			// Cuando seleciona en los opciones ninguna
			// debe desactivar todo los combos
			//vaciar el store para asegurar que los certificaciones se cargan correcto

			dominioStore.url = '';
			desactivaSelectConValor('dominioSelect', 0);

		} else {

			enableSelectConValor('dominioSelect', '');
			setLabelToLabelAndRequerido('dominioSelect', 'labelDominioSelect',
					'Dominio del idioma', true);
			dominioStore.url = "${context}registro.do?method=dominios";

		}

		//dijit.byId('dominioSelect').reset();
		dominioStore.close();

	}
	//Actualizar Certificado desde dominio
	function onSelectDominio() {
		var vIdioma = dijit.byId('idiomaSelect').get('value');
		//Necesitamos el valor guardado del catalogo que es referente a su idoma no lo de la lista
		var vIdIdiomaDep = depIdioma[vIdioma];
		var vDominio = dijit.byId('dominioSelect').get('value');

		certificacionStore.url = '';

		setLabelToLabelAndRequerido('certificacionSelect',
				'labelCertificacionSelect', '¿Cuentas con certificación?',
				false);
		if (vIdioma == 1 || vIdioma =='') {
			desactivaSelectConValor('certificacionSelect', 2);
		} else {
			if (vDominio == 3 || vDominio == 2) {
				certificacionStore.url = "${context}registro.do?method=certificaciones&idCatDep="
						+ vIdIdiomaDep;
				enableSelectConValor('certificacionSelect', '');
				setLabelToLabelAndRequerido('certificacionSelect',
						'labelCertificacionSelect',
						'¿Cuentas con certificación?', true);

			} else {
				desactivaSelectConValor('certificacionSelect', 2);
			}

		}
		dijit.byId('certificacionSelect').reset();
		certificacionStore.close();

	}

	function disableListAnterior() {
		dijit.byId('certificacionSelect').attr('value', 2); // NINGUNO
		dijit.byId('certificacionSelect').readOnly = 'readonly';

		dijit.byId('dominioSelect').set('value', '');
		dijit.byId('dominioSelect').reset();

		//dijit.byId('dominioSelect').readOnly='readonly';
		//document.getElementById('dominioSelect').readOnly = true;
		//document.getElementById('certificacionSelect').readOnly = true;
		dijit.byId('dominioSelect').disabled = true;
		dijit.byId('certificacionSelect').disabled = true;
		// Se apaga el color rojo del control

		dijit.byId('dominioSelect').validator = function() {
			return false;
		};
		dijit.byId('dominioSelect').validate();

		dijit.byId('dominioSelect').validator = originalValidator;

	}
	function enableSelectConValor(selectList, valor) {
		dijit.byId(selectList).set('value', valor);
		dijit.byId(selectList).reset();
		dijit.byId(selectList).readOnly = false;
		dijit.byId(selectList).set('disabled',false);
		var originalValidator = dijit.byId(selectList).validator;
		dijit.byId(selectList).validator = function() {
			return true;
		};
		dijit.byId(selectList).validate();

		dijit.byId(selectList).validator = originalValidator;
		dijit.byId(selectList).focus();
	}
	//deshabilitar una lista
	function desactivaSelectConValor(selectList, valor) {
		dijit.byId(selectList).attr('value', valor);
		dijit.byId(selectList).readOnly = true;
		dijit.byId(selectList).set('disabled',true);

		// Se apaga el color rojo del control
		var originalValidator = dijit.byId(selectList).validator;
		dijit.byId(selectList).validator = function() {
			return false;
		};
		dijit.byId(selectList).validate();
		dijit.byId(selectList).validator = originalValidator;
		dijit.byId(selectList).reset();

	}

	function actualizaDominio() {
		var vIdioma = dijit.byId('idiomaSelect').get('value');
		var cert = dijit.byId('certificacionSelect').get('value');

		if (cert == 2) { // NINGUNO

			if (vIdioma == 1) { // NINGUNO
				dijit.byId('dominioSelect').set('required', false);
				dijit.byId('dominioSelect').set('value', '');
				dijit.byId('dominioSelect').reset();

				//dijit.byId('dominioSelect').readOnly='readonly';
				document.getElementById('dominioSelect').readOnly = true;

				dijit.byId('dominioSelect').disabled = true;

				// Se apaga el color rojo del control
				var originalValidator = dijit.byId('dominioSelect').validator;
				dijit.byId('dominioSelect').validator = function() {
					return false;
				};
				dijit.byId('dominioSelect').validate();
				dijit.byId('dominioSelect').validator = originalValidator;

			} else {
				dijit.byId('dominioSelect').disabled = false;
				dijit.byId('dominioSelect').set('required', true);

				//dijit.byId('dominioSelect').readOnly='';
				document.getElementById('dominioSelect').readOnly = false;

				dijit.byId('dominioSelect').set('value', '');
				dijit.byId('dominioSelect').reset();
				dijit.byId('dominioSelect').focus();

			}

		} else {
			dijit.byId('dominioSelect').set('required', false);
			dijit.byId('dominioSelect').attr('value', 3); // NINGUNO

			//dijit.byId('dominioSelect').readOnly='readonly';
			document.getElementById('dominioSelect').readOnly = true;
		}
	}

	function actulizaMunicipio() {
		var vEntidad = dijit.byId('idEntidadSelect').get('value');

		if (vEntidad) {
			municipioStore.url = '';
			municipioStore.close();

			coloniaStore.url = '';
			coloniaStore.close();

			dojo.byId('codigoPostal').value = '';
			dijit.byId('idMunicipioSelect').reset();
			dijit.byId('idColoniaSelect').reset();

			//dijit.byId('idMunicipioSelect').disabled=false;
			municipioStore.url = "${context}domicilio.do?method=obtenerMunicipio"
					+ "&" + "idEntidad=" + vEntidad;
			municipioStore.close();
		}
	}

	function actulizaColonia() {
		var vEntidad = dijit.byId('idEntidadSelect').get('value');
		var vMunicipio = dijit.byId('idMunicipioSelect').get('value');

		if (vEntidad && vMunicipio) {
			coloniaStore.url = '';
			coloniaStore.close();

			dojo.byId('codigoPostal').value = '';
			dijit.byId('idColoniaSelect').reset();

			//dijit.byId('idColoniaSelect').disabled=false;
			coloniaStore.url = "${context}domicilio.do?method=obtenerColonia"
					+ "&" + "idEntidad=" + vEntidad + "&" + "idMunicipio="
					+ vMunicipio;
			coloniaStore.close();
		}
	}

	function actulizaCodigoPostal() {

		var vEntidad = dijit.byId('idEntidadSelect').get('value');
		var vMunicipio = dijit.byId('idMunicipioSelect').get('value');
		var vColonia = dijit.byId('idColoniaSelect').get('value');

		if (vEntidad && vMunicipio && vColonia) {
			var url = '${context}domicilio.do?method=obtenerCodigoPostal&idEntidad='
					+ vEntidad
					+ '&idMunicipio='
					+ vMunicipio
					+ '&idColonia='
					+ vColonia;

			dojo.xhrGet({
				url : url,
				sync : true,
				timeout : 180000,
				load : function(data) {
					var res = dojo.fromJson(data);
					dojo.byId('codigoPostal').value = res.codigoPostal;
					habilitarGeolocalizacion();
				}
			});
		}

	}

	function limpiaConocimientosComp() {
		
		if (dijit.byId('conocimientoCompNinguno').get("checked")) {
			
			dijit.byId('conocimientoCompProcesadorTxt').set("checked",false);
			dijit.byId('conocimientoCompHojaCal').set("checked",false);
			dijit.byId('conocimientoCompInternet').set("checked",false);
			dijit.byId('conocimientoCompRedes').set("checked",false);
			dijit.byId("conocimientoCompOtros").attr("checked", ""); // uncheck radio
		}
	}
	


	function limpiaNingunConocimientosComp(check) {
		if (check)
			dijit.byId('conocimientoCompNinguno').set("checked",false);
	}

	function limpiaOtrosMedios(control) {
		
		var medios = document.registroCandidatoForm.idOtrosMedios;
		if (medios && medios.length && medios.length > 0) {

			if (control && control.checked && control.value != 1) {
				dijit.byId('idOtrosMedios1').set("checked",false);
				return;
			}

			var ningunoChecked = medios[0].checked;
		

			for (var i = 1; i <= medios.length; i++) {
				if (ningunoChecked && i > 1) {
					
				
					dijit.byId('idOtrosMedios'+i).set("checked",false);
				}
			}

		}
	}

	function autocompleteOcupacion() {
		var search = dojo.byId('ocupacionDeseada').value;
		search = dojo.trim(search);
		//console.log(search +"-> "+ search.length);

		if (search.length < 4) {

			cerrarAutocomplete();

		} else if (search.length >= 4) {

			dojo.xhrPost({
					url : '${context}registro.do?method=ocupaciones&search='
							+ search,
					handleAs : "text",
					headers : {
						"Content-Type" : "application/x-www-form-urlencoded; charset=ISO-8859-1"
					},
					load : function(result) {
						if (result.length > 0) {
							dojo.byId('ocupacionesListDiv').style.display = 'block';
							clearAndAddlu('areaMensajes', result,
									'listaOcupaciones',
									'height: 200px; overflow: auto;list-style-type: none;')
							//dojo.byId('listaOcupaciones').innerHTML = result;
						} else {
							cerrarAutocomplete();
						}
					}
				});
		}
	}

	function autocompleteOcupacion2() {
		var search = dojo.byId('ocupacionDeseada2Select').value;
		search = dojo.trim(search);
		//console.log(search +"-> "+ search.length);

		if (search.length < 4) {

			cerrarAutocompleteOccupacion2();
			if(search.length == 0){
				dojo.byId('idOcupacionDeseada2').value = '';	
			}
			else
			{
				dojo.byId('idOcupacionDeseada2').value = 0;
			}
			

		} else if (search.length >= 4) {

			dojo.xhrPost({
					url : '${context}registro.do?method=ocupaciones&search='
							+ search + '&action=setOcupacion2',
					handleAs : "text",
					headers : {
						"Content-Type" : "application/x-www-form-urlencoded; charset=ISO-8859-1"
					},
					load : function(result) {
						if (result.length > 0) {
							dojo.byId('idOcupacionDeseada2').value = 0;
							dojo.byId('ocupacionesListDiv2').style.display = 'block';
							//dojo.byId('listaOcupaciones2').innerHTML = result;

							clearAndAddlu('areaMensajes2', result,
									'listaOcupaciones2',
									'height: 200px; overflow: auto; list-style-type: none;')
						} else {
							cerrarAutocomplete2();
						}
					}
				});
		}
	}

	function setOcupacion(id, val) {
		//console.log("--> id:"+ id +" "+ val);
		dojo.byId('idOcupacionDeseada').value = id;
		dojo.byId('ocupacionDeseada').value = val;
		dojo.byId('ocupacionDeseada').focus();

		cerrarAutocomplete();
	}

	function setOcupacion2(id, val) {
		//console.log("--> id:"+ id +" "+ val);
		dojo.byId('idOcupacionDeseada2').value = id;
		dojo.byId('ocupacionDeseada2Select').value = val;
		dojo.byId('ocupacionDeseada2Select').focus();

		cerrarAutocomplete2();
	}

	function cerrarAutocomplete2() {
		//dojo.byId('ocupacionesListDiv2').style.display = 'none';
		//dojo.byId('listaOcupaciones2').innerHTML = "";
		cerrarAutocompleteOccupacion2();
	}

	function cerrarAutocomplete() {
		dojo.byId('ocupacionesListDiv').style.display = 'none';
		//dojo.byId('listaOcupaciones').innerHTML = "";
		clearAndAddlu('areaMensajes', '', 'listaOcupaciones',
				'height: 200px; overflow: auto')

	}

	function cerrarAutocompleteOccupacion2() {
		dojo.byId('ocupacionesListDiv2').style.display = 'none';
		//dojo.byId('ocupacionesListDiv2').innerHTML = "";
		clearAndAddlu('areaMensajes', '', 'listaOcupaciones',
				'height: 200px; overflow: auto')

	}

	function maximaLongitud(texto, e, maxlong) {
	
		var valido = /[^a-zA-Z0-9\sáÁéÉíÍóÓúÚñÑ.]/.test(texto.value);
		var doblespace = /[/\s{2,}/g, ' ']/.test(texto.value);
		if (valido == true || doblespace) {
			/*con estas 3 sentencias se consigue que el texto se reduzca
			al tamaño maximo permitido, sustituyendo lo que se haya
			introducido, por los primeros caracteres hasta dicho limite*/
			in_value = texto.value.replace(/[^a-zA-Z0-9\sáÁéÉíÍóÓúÚñÑ.]/g, "");
		

			//string = string.replace(/\s{2,}/g, ' ');
			texto.value = in_value.replace(/\s{2,}/g, ' ');
		
		}

		if (texto.value.length > maxlong) {
			/*con estas 3 sentencias se consigue que el texto se reduzca
			al tamaño maximo permitido, sustituyendo lo que se haya
			introducido, por los primeros caracteres hasta dicho limite*/
			in_value = texto.value.replace(/^\s+|\s*[\r\n][\r\n \t]*/g, "");

			out_value = in_value.substring(0, maxlong);
			texto.value = out_value;

			return false;
		}
		return true;
	}
	function mostrarOtraOpcionDeEmpleo() {
		if (validaTrabajoDeseado()) {
			dojo.style(dojo.byId('otra_opcion_empleo'), "display", "block");
			dojo.style(dojo.byId('btnOpcion2'), "display", "none");
		}
	}

	function validaTrabajoDeseado() {
		if (dojo.byId('ocupacionDeseada')
				&& dojo.byId('ocupacionDeseada').value == '') {
			alertMsg(dijit.byId('ocupacionDeseada').get("missingMessage"));
			//displayErrorMsg(dijit.byId(campo), dijit.byId(campo).get("missingMessage"));
			return false;
		}

		if (!validaCampo('salarioPretendido'))
			return false;

		if (Number(dijit.byId('salarioPretendido').value) < 0) {
			var msgerr = 'Es necesario indicar el sueldo pretendido.';
			alertMsg(msgerr);
			dijit.byId('salarioPretendido').focus();
			displayErrorMsg(dijit.byId('salarioPretendido'), msgerr);
			return false;
		}

		if (!validaCampoNoRequerido('tiposContratoSelect')) {
			return false;
		}
		if (!validaCampoSelect('experienciaTotalSelect'))
			return false;

		return true;
	}
	
	function habilitarGeolocalizacion(){
		var address = null;
		<c:if test="${registroCandidatoForm.permisoGeolocalizacion}"> 
		if($('#calle').val() && $('#numeroExterior').val() && $('#codigoPostal').val()){
			address = $('#calle').val()+' '+
					  $('#numeroExterior').val()+', '+
					  $('#idColoniaSelect').val()+', '+
					  $('#idMunicipioSelect').val()+', '+
					  $('#idEntidadSelect').val();
			if(!map){
				loadGoogleMaps();				
			}
			geocodeAddress(map, address);
			$('#fieldGeoreferencia').slideDown();
			$('#btnBuscarByDireccion').show();
		}else{
			$('#btnBuscarByDireccion').hide();
		}
		</c:if>
	}
	
	function ubicarDireccionUsuario(){
  		var address =address = $('#calle').val()+' '+
		  $('#numeroExterior').val()+', '+
		  $('#idColoniaSelect').val()+', '+
		  $('#idMunicipioSelect').val()+', '+
		  $('#idEntidadSelect').val();
  		geocodeAddress(map, address);
  	}

	function displayProspera(show) {
		if (show) {	// Prospera support?
			dojo.attr(dojo.byId("prospera"), "style", "display:block");	// show div element
			dijit.byId("apoyoProsperaNo").attr("checked", ""); // uncheck radio
			dojo.attr(dojo.byId("apoyoProspera"), "value", dijit.byId("apoyoProsperaSi").attr("value")); // assign value to input hidden element
		} else {
			dojo.attr(dojo.byId("prospera"), "style", "display:none");	// hide div element
			dijit.byId("apoyoProsperaSi").attr("checked", ""); // uncheck radio
			dojo.attr(dojo.byId("apoyoProspera"), "value", dijit.byId("apoyoProsperaNo").attr("value")); // assign value to input hidden element
			// Clean input textfield
		}
	}

	function validaProspera() {
		if (document.getElementById("apoyoProsperaSi").checked) {
//			var folio = dijit.byId('folioProspera');
			if (!dijit.byId("folioProspera").isValid()) {
				dijit.byId('folioProspera').focus();
				alertMsg('Folio Prospera incorrecto.');
				return false;
			}
//			var folio = dijit.byId('folioProspera');
			if (!dijit.byId("folioIntegranteProspera").isValid()) {
				dijit.byId('folioIntegranteProspera').focus();
				alertMsg('Folio Integrante Prospera incorrecto.');
				return false;
			}
		}
		return true;
	}
</script>
<c:if test="${registroCandidatoForm.permisoGeolocalizacion}">
	<script type="text/javascript" src="https://maps.google.com/maps/api/js?v=3&sensor=false&libraries=places" ></script>
	<script src="<%=request.getContextPath()%>/js/geolocalizacion.js" type="text/javascript"></script>
	<script type="text/javascript">
		function fillLatLng(position){
			dijit.byId('txtLat').attr('value', position.lat());
			dijit.byId('txtLng').attr('value', position.lng());
		}
	</script>
</c:if>
<noscript>Funciones de JavaScript desactivadas por navegador</noscript>

<style>
	@import "css/geolocalizacion.css";
</style>

<c:if test="${not empty ERROR_MSG}">
	<script type="text/javascript">
		dojo.addOnLoad(function() {
			var html = '${ERROR_MSG}'
				messageRegistro(html,"<c:url value="/miEspacioCandidato.do"/>","Aceptar","Inicio");
			//alertMsg('${ERROR_MSG}');
		});
	</script>
</c:if>


<div dojoType="dojo.data.ItemFileReadStore"
	jsId="entidadFederativaStore" urlPreventCache="true"
	clearOnClose="true"></div>
<div dojoType="dojo.data.ItemFileReadStore" jsId="municipioStore"
	urlPreventCache="true" clearOnClose="true"></div>
<div dojoType="dojo.data.ItemFileReadStore" jsId="coloniaStore"
	urlPreventCache="true" clearOnClose="true"></div>

<div dojoType="dojo.data.ItemFileReadStore" jsId="gradoEstudioStore"
	url="${context}registro.do?method=gradosEstudio"></div>
<div dojoType="dojo.data.ItemFileReadStore"
	jsId="carreraEspecialidadStore" urlPreventCache="true"
	clearOnClose="true"></div>

<div dojoType="dojo.data.ItemFileReadStore"
	jsId="situacionAcademicaStore" urlPreventCache="true"
	clearOnClose="true"></div>
<div dojoType="dojo.data.ItemFileReadStore" jsId="idiomaStore"
	url="${context}registro.do?method=idiomas"></div>
<div dojoType="dojo.data.ItemFileReadStore" jsId="certificacionStore"
	urlPreventCache="true" clearOnClose="true"></div>
<div dojoType="dojo.data.ItemFileReadStore" jsId="dominioStore"
	urlPreventCache="true" clearOnClose="true"
	url="${context}registro.do?method=dominios"></div>

<div dojoType="dojo.data.ItemFileReadStore" jsId="experienciaTotalStore"
	url="${context}registro.do?method=experiencia"></div>

<%--
<div dojoType="dojo.data.ItemFileReadStore" jsId="ocupacionesDeseadasStore"  url="${context}registro.do?method=ocupacionesDeseadas"></div>
--%>
<div dojoType="dojo.data.ItemFileReadStore"
	jsId="tiposEmpleoDeseadoStore"
	url="${context}registro.do?method=tiposEmpleo"></div>
<div dojoType="dojo.data.ItemFileReadStore"
	jsId="tiposContratoDeseadoStore"
	url="${context}registro.do?method=tiposContrato"></div>
<div dojoType="dojo.data.ItemFileReadStore" jsId="jeraquiaStore"
	url="${context}registro.do?method=jeraquia"></div>
<div dojoType="dojo.data.ItemFileReadStore" jsId="personaACargoStore"
	url="${context}registro.do?method=personasACargo"></div>
<div dojoType="dojo.data.ItemFileReadStore" jsId="diasStore"
	url="${context}registro.do?method=dias"></div>
<div dojoType="dojo.data.ItemFileReadStore" jsId="mesesStore"
	url="${context}registro.do?method=meses"></div>
<div dojoType="dojo.data.ItemFileReadStore" jsId="aniosStore"
	url="${context}registro.do?method=aniosbusca"></div>


<%--<div dojoType="dojo.data.ItemFileReadStore" jsId="otrosMediosStore"          url="${context}registro.do?method=otrosMedios"></div>--%>
<div dojoType="dojo.data.ItemFileReadStore" jsId="portalEnterasteStore"
	url="${context}registro.do?method=medioPortal"></div>
<div dojoType="dojo.data.ItemFileReadStore"
	jsId="motivosBuscaTrabajoStore"
	url="${context}registro.do?method=motivosBuscaTrabajo&idTrab=${registroCandidatoForm.trabajaSi}"
	urlPreventCache="true" clearOnClose="true"></div>

<c:if test="${registroCandidatoForm.correo}">
	<c:set var="correoprev" value="${registroCandidatoForm.usuario}" />
</c:if>
<c:if test="${!registroCandidatoForm.correo}">
	<c:set var="correoprev" value="" />
</c:if>

<form name="registroCandidatoForm" id="registroCandidatoForm"
	method="post" action="registro.do" dojoType="dijit.form.Form">
	<input type="hidden" name="method" id="method" value="init" />
	<!-- indica el metodo a invocar -->

	<input type="hidden" name="idEntidad" id="idEntidad" value="" />
	<input type="hidden" name="idMunicipio" id="idMunicipio" value="" />
	<input type="hidden" name="idColonia" id="idColonia" value="" />
	<input type="hidden" name="idGradoEstudio" id="idGradoEstudio" value="" />
	<input type="hidden" name="idCarreraEspecialidad" id="idCarreraEspecialidad" value="" />
	<input type="hidden" name="idSituacionAcademica" id="idSituacionAcademica" value="" />
	<input type="hidden" name="idIdioma" id="idIdioma" value="" />
	<input type="hidden" name="idCertificacion" id="idCertificacion" value="" />
	<input type="hidden" name="idDominio" id="idDominio" value="" />
	<input type="hidden" name="idOcupacionDeseada" id="idOcupacionDeseada" value="" />
	<input type="hidden" name="idOcupacionDeseada2" id="idOcupacionDeseada2" value="" />
	<input type="hidden" name="idExperienciaTotal" id="idExperienciaTotal" value="" />
	<input type="hidden" name="idExperiencia2" id="idExperiencia2" value="" />
	<input type="hidden" name="idAnioLaboresInicial" id="idAnioLaboresInicial" value="" />
	<input type="hidden" name="idTipoEmpleoDeseado" id="idTipoEmpleoDeseado" value="" />
	<input type="hidden" name="idTipoContratoDeseado" id="idTipoContratoDeseado" value="" />
	<input type="hidden" name="idMotivo" id="idMotivo" value="" />
	<input type="hidden" name="idComoEnteraste" id="idComoEnteraste" value="" />
	<input type="hidden" name="diaBuscar" id="diaBuscar" value="" />
	<input type="hidden" name="mesBuscar" id="mesBuscar" value="" />
	<input type="hidden" name="anioBuscar" id="anioBuscar" value="" />
	<input type="hidden" name="diaLaboresInicial" id="diaLaboresInicial" value="" />
	<input type="hidden" name="mesLaboresInicial" id="mesLaboresInicial" value="" />
	<input type="hidden" name="anioLaboresInicial" id="anioLaboresInicial" value="" />
	<input type="hidden" name="diaLaboresFinal" id="diaLaboresFinal" value="" />
	<input type="hidden" name="mesLaboresFinal" id="mesLaboresFinal" value="" />
	<input type="hidden" name="anioLaboresFinal" id="anioLaboresFinal" value="" />
	<input type="hidden" name="acceso" id="acceso" value="" />
	<input type="hidden" name="idJerquia" id="idJerquia" value="" />
	<input type="hidden" name="idPersonaACargo" id="idPersonaACargo" value="" />
	<input type="hidden" name="confidencial" id="confidencial" value="${registroCandidatoForm.confidencial}" />
	<input type="hidden" name="correoElectronicoGuardado" id="correoElectronicoGuardado" value="${correoprev}" />
	<input type="hidden" name="omitirTrabajoEnPerfil" id="omitirTrabajoEnPerfil" />

	<input type="hidden" name="apoyoProspera" id="apoyoProspera" value="${registroCandidatoForm.apoyoProspera}" />

	<input type="hidden" name="positionCoordsLatitude" id="positionCoordsLatitude" value="" />
	<input type="hidden" name="positionCoordsLongitude" id="positionCoordsLongitude" value="" />


	<div class="formApp">
		<div class="flow_1">Registro de Candidatos</div>
		<div class="form_wrap">
			<div class="instruc_01">
				<strong>¡Regístrate en 3 sencillos pasos!</strong>
			</div>
			<div class="form_edge">
				<div class="stepApp">
					<h2>
						<img src="css/images/paso_3.png" alt="Paso 3 de 3. Perfil laboral" />
					</h2>
					<p>
						Los datos marcados con <span>*</span> son obligatorios
					</p>
				</div>
				<div class="user_dt">
					<h3>Datos Personales</h3>
					<div class="datos_01">
						<strong class="user_name">${registroCandidatoForm.nombre}
							${registroCandidatoForm.apellido1}
							${registroCandidatoForm.apellido2}</strong>
						<p>
							CURP: <strong>${registroCandidatoForm.curp},</strong>
							<c:if
								test="${registroCandidatoForm.genero==registroCandidatoForm.generoMasculino}">
		            Hombre, ${registroCandidatoForm.edad} años
		            </c:if>
							<c:if
								test="${registroCandidatoForm.genero==registroCandidatoForm.generoFemenino}">
		            Mujer, ${registroCandidatoForm.edad} años
		            </c:if>

							, Lugar de nacimiento: ${registroCandidatoForm.entidadNacimiento}
							<c:if test="${registroCandidatoForm.registroPPC}">,  NSS: <strong><c:out
										value="${registroCandidatoForm.nss}" />
							</c:if>
							</strong>
						</p>
					</div>
				</div>







				<fieldset class="domicilio_1">
					<legend>Domicilio actual</legend>
					<div class="grid1_3 margin_top_10 fl">
						<label for="calle"><span>*</span> Calle</label> <input type="text"
							id="calle" name="calle" value="${registroCandidatoForm.calle}"
							dojoType="dijit.form.ValidationTextBox" maxlength="200" size="60"
							uppercase="true" trim="true" required="true"
							missingMessage="Es necesario indicar la calle."
							regExp="${regexpcalle}"
							invalidMessage="La calle es inválida, favor de verificar." onchange="habilitarGeolocalizacion();" />
					</div>
					<div class="margin_top_10 fl">
						<div class="fl">
							<label for="numeroExterior"><span>*</span> Número
								exterior</label> <input type="text" id="numeroExterior"
								name="numeroExterior" maxlength="50" size="4" trim="true"
								value="${registroCandidatoForm.numeroExterior}"
								dojoType="dijit.form.ValidationTextBox" required="true"
								missingMessage="Es necesario indicar el número exterior."
								regExp="${regexpnumext}"
								invalidMessage="Número exterior inválido." 
								onchange="habilitarGeolocalizacion();"/>
						</div>
						<div class="fl">
							<label for="numeroInterior">Número interior</label> <input
								type="text" id="numeroInterior" name="numeroInterior"
								maxlength="150" size="4" trim="true"
								value="${registroCandidatoForm.numeroInterior}"
								dojoType="dijit.form.ValidationTextBox" required="false"
								regExp="${regexpnumint}"
								invalidMessage="Número interior inválido." />
						</div>
					</div>
					<div class="clearfix"></div>
					<div class="margin_top_10 ubicacion_1">
						<div class="grid1_3 fl">
							<label for="entreCalle">Entre qué calles</label> <input
								type="text" id="entreCalle" name="entreCalle"
								value="${registroCandidatoForm.entreCalle}"
								dojoType="dijit.form.ValidationTextBox" maxlength="150"
								size="60" uppercase="true" trim="true"
								regExp="${regexpentrecalle}"
								invalidMessage="La calle es inválida." />
						</div>
						<div class="y">
							<label for="yCalle">y</label>
						</div>
						<div class="grid1_3 margin_top_30 fl">
							<input type="text" id="yCalle" name="yCalle"
								value="${registroCandidatoForm.yCalle}"
								dojoType="dijit.form.ValidationTextBox" maxlength="150"
								size="60" uppercase="true" trim="true"
								regExp="${regexpentrecalle}"
								invalidMessage="La calle es inválida." />
						</div>
					</div>
					<div class="clearfix"></div>
					<div class="grid1_3 margin_top_10 fl">
						<label for="idEntidadSelect"><span>*</span> Entidad
							federativa donde radicas</label> <select id="idEntidadSelect"
							name="idEntidadSelect" dojotype="dijit.form.FilteringSelect"
							store="entidadFederativaStore" required="true"
							missingMessage="Debe seleccionar la entidad federativa donde radicas."
							invalidMessage="Debe seleccionar la entidad federativa donde radicas."
							scrollOnFocus="true" maxheight="250" autocomplete="true"
							onchange="javascript:actulizaMunicipio();">
						</select>
					</div>
					<div class="grid1_3 margin_top_10 fl">
						<label for="idMunicipioSelect"><span>*</span> Municipio o
							Delegación</label> <select id="idMunicipioSelect"
							name="idMunicipioSelect" dojotype="dijit.form.FilteringSelect"
							store="municipioStore" required="true"
							missingMessage="Debe seleccionar el un municipio o delegación."
							scrollOnFocus="true" maxheight="250" autocomplete="true"
							onchange="javascript:actulizaColonia();">
						</select>
					</div>
					<div class="grid1_3 fl margin_top_10 margin_no_r">
						<label for="idColoniaSelect"><span>*</span> Colonia</label> <select
							id="idColoniaSelect" name="idColoniaSelect"
							dojotype="dijit.form.FilteringSelect" store="coloniaStore"
							required="true" missingMessage="Debe seleccionar la colonia."
							scrollOnFocus="true" maxheight="250" autocomplete="true"
							onchange="javascript:actulizaCodigoPostal();">
						</select>
					</div>
					<div class="grid1_3 margin_top_10 fl">
						<label for="codigoPostal">Código Postal</label> <input type="text"
							name="codigoPostal" id="codigoPostal"
							dojoType="dijit.form.ValidationTextBox" readonly="readonly"
							maxlength="5" style="width: 7em;" trim="true" required="false"
							missingMessage="Es necesario indicar el codigo postal."
							regExp="${regexpcp}"
							invalidMessage="Código incorrecto, solo se admiten caracteres numéricos." />
					</div>
					<div class="clearfix"></div>
					<%-- <div class="margin_top_20">
		    <strong>Deseo que mis datos personales (CURP, domicilio y edad) permanezcan confidenciales para las empresas</strong><input type="checkbox" value="SI">
			</div>
			--%>
				</fieldset>
				
				
				
				<fieldset id="fieldGeoreferencia" class="datosContacto" style="display: none;">
					<legend>Georeferencia</legend>
					<input id="txtLat" type="hidden" name="latitud" value="" dojoType="dijit.form.TextBox"/>
		            <input id="txtLng" type="hidden" name="longitud" value="" dojoType="dijit.form.TextBox"/>
		            
		            <input id="pac-input" class="controls" type="hidden" placeholder="Indique una dirección... " autocomplete="off" />
					<div id="map" style="width: 100%; height: 400px;"></div>
					
					<input id="btnCurrentLocation" type="button" value="Obtener ubicación por Navegador" 
						style="font-size: 12px; margin: 0px;" class="boton_naranja"
						onclick="loadCurrentLocation();"/>
					
					<input id="btnBuscarByDireccion" type="button" value="Obtener por domicilio registrado" 
						style="font-size: 12px; margin: 0px;" class="boton_naranja"
						onclick="ubicarDireccionUsuario();"/>
						
				</fieldset>
				
				<fieldset class="confiden_01">
					<legend>Confidencialidad de datos</legend>
					<div class="margin_top_30">
						<strong class="fl">¿Deseas que tus datos personales
							(CURP, domicilio y edad) permanezcan confidenciales para el
							contacto inicial con las empresas? </strong>

						<div class="conf_controls fl" id="confidencialCurp">
							<div class="si_01">
								<input type="radio" data-dojo-type="dijit.form.RadioButton"
									name="radioOne" id="radioOne" value="2"><label
									for="radioOne">Si</label>
							</div>
							<div class="no_01">
								<input type="radio" data-dojo-type="dijit.form.RadioButton"
									name="radioOne" id="radioTwo" value="1"><label
									for="radioTwo">No</label>
							</div>
						</div>
					</div>
				</fieldset>



				<fieldset class="contact_1">
					<legend>Datos del contacto</legend>
					<div class="labeled margin_top_10">
						<span>*</span> Teléfono
					</div>
					<p>
						<em>Debe ingresar un total de 10 dígitos (Clave LADA +
							Teléfono).</em>
					</p>
					<div class="grid1_3  margin_top_20 fl">
						<div class="margin-r_20 fl">
							<div class="labeled margin_no_t">
								<span>*</span> Tipo de teléfono
							</div>
							<div class="tipo_tel margin_top_10 margin-r_10 fl">
								<input type="radio" name="tipoTelefono" id="telefonoFijo"
									checked="checked" data-dojo-type="dijit.form.RadioButton"
									value="${registroCandidatoForm.idTipoTelefonoFijo}"
									onclick="estableceAcceso()"> 
								<label for="telefonoFijo">Fijo</label>
							</div>
							<div class="tipo_tel margin_top_10 fl">
								<input type="radio" name="tipoTelefono" id="telefonoCelular"
									data-dojo-type="dijit.form.RadioButton"
									value="${registroCandidatoForm.idTipoTelefonoCelular}"
									onclick="estableceAcceso()"> 
								<label for="telefonoCelular">Celular</label>
							</div>
						</div>
						<div class="margin_top_10 margin-r_10 fl">
							<label for="accesoDiv" class="fw_no_bold"><span>*</span>
								Acceso</label>
							<div class="ta_center margin_top_10">
								<span id="accesoDiv"> ${registroCandidatoForm.acceso} </span>
							</div>
						</div>
						<div class="fl margin_top_10">
							<label for="clave"><span>*</span> Clave LADA</label> <input
								type="text" id="clave" name="clave"
								value="${registroCandidatoForm.clave}"
								dojoType="dijit.form.ValidationTextBox" maxlength="3"
								style="width: 3em;" trim="true" required="true"
								missingMessage="Es necesario indicar la clave lada."
								regExp="${regexplada}"
								invalidMessage="La clave lada es inválida."
								onBlur="changePhoneSizeRequired();" />
						</div>
					</div>
					<div class="margin_top_30 fl">
						<label for="telefono"><span>*</span> Teléfono</label> <input
							type="text" name="telefono" id="telefono"
							value="${registroCandidatoForm.telefono}"
							dojoType="dijit.form.ValidationTextBox" maxlength="8"
							style="width: 8em;" trim="true" required="true"
							missingMessage="Es necesario indicar el Teléfono."
							regExp="${regexptelefono}"
							invalidMessage="Teléfono debe ser numérica."
							onBlur="changePhoneSizeRequired();" />
					</div>
					<div class="margin_top_30 fl">
						<label for="extension">Extensión</label> <input type="text"
							name="extension" id="extension"
							value="${registroCandidatoForm.extension}"
							dojoType="dijit.form.ValidationTextBox" maxlength="8"
							style="width: 8em;" trim="true" required="false"
							missingMessage="Es necesario indicar la extensión."
							regExp="${regexpextension}"
							invalidMessage="La extensión debe ser numérica." />
					</div>
					<div class="clearfix"></div>
					<div class="margin_top_20">
						<c:if test="${not registroCandidatoForm.registroPPC}">
							<label for="correoElectronico">Correo electrónico</label>
							<div>Es importante capturar un correo electrónico, ya que
								por este medio recibirás las notificaciones de empleo.</div>
							<input class="margin_top_10" type="text" id="correoElectronico"
								name="correoElectronico" value="${correoprev}"
								dojoType="dijit.form.ValidationTextBox" size="65" maxlength="65"
								trim="false;" oncopy="return false;" oncut="return false"
								onpaste="return false;" required="false"
								<c:if test= "${registroCandidatoForm.correo}" >
								readonly=true
							</c:if>
								missingMessage="Es necesario indicar el correo electrónico."
								regExp="${regexpmail}"
								invalidMessage="Formato de correo electrónico invalido." />
					</div>
					</c:if>
					<c:if test="${registroCandidatoForm.registroPPC}">

						<label for="correoElectronico"><span>*</span> Correo
							electrónico</label>
						<div>Es importante capturar un correo electrónico, ya que
							por este medio recibirás las notificaciones de empleo.</div>

						<input class="margin_top_10" type="text" id="correoElectronico"
							name="correoElectronico" value="${correoprev}"
							dojoType="dijit.form.ValidationTextBox" size="65" maxlength="65"
							trim="false;" oncopy="return false;" oncut="return false"
							onpaste="return false;" required="true"
							<c:if test= "${registroCandidatoForm.correo}" >
								readonly=true
							</c:if>
							missingMessage="Es necesario indicar el correo electrónico."
							regExp="${regexpmail}"
							invalidMessage="Formato de correo electrónico invalido." />
			</div>
			</c:if>
			<div class="contact_pref">
				<div class="labeled margin_top_10">
					<span>*</span> Medio de contacto preferente
				</div>
				<input type="checkbox" name="mediocontactoTel" id="mediocontactoTel"
					dojoType="dijit.form.CheckBox"
					value="${registroCandidatoForm.contactoTelSi}" /> <label
					for="mediocontactoTel">Teléfono</label><br> <input
					type="checkbox" name="mediocontactoCorreo"
					dojoType="dijit.form.CheckBox" id="mediocontactoCorreo"
					value="${registroCandidatoForm.contactoCorreoSi}"
					onclick="validaCorreoRequeridoMedioContacto();"> <label
					for="mediocontactoCorreo">Correo electrónico</label>
			</div>
			<div class="offer_pref">
				<div class="labeled">¿Deseas recibir ofertas de empleo? por:</div>
				<input type="checkbox" name="notificarCel" id="notificarCel"
					value="${registroCandidatoForm.notificarCelSi}"
					dojoType="dijit.form.CheckBox"
					> <label
					for="notificarCel">Celular</label><br> <input
					type="checkbox" name="notificarCorreo" id="notificarCorreo"
					dojoType="dijit.form.CheckBox"
					value="${registroCandidatoForm.notificarCorreoSi}"
					onclick="validaCorreoRequeridoRecibirOfertas();"> <label
					for="notificarCorreo">Correo electrónico</label>
			</div>
			</fieldset>




			<h3>Escolaridad y otros conocimientos</h3>
			<fieldset class="escolaridad_1">
				<legend>Estudios</legend>
				<div class="margin_top_10">
					<div class="grid1_3 fl">
						<label for="idGradoEstudioSelect"><span>*</span> Último
							grado de estudios</label> <select id="idGradoEstudioSelect"
							name="idGradoEstudioSelect" required="true"
							missingMessage="Debe seleccionar el último grado de estudios."
							store="gradoEstudioStore" dojotype="dijit.form.FilteringSelect"
							onchange="javascript:actualizaCarreras();">
						</select>
					</div>
					<div class="grid1_3 fl">
						<label for="carreraEspecialidadSelect"><span>*</span>
							Carrera o especialidad</label> <select id="carreraEspecialidadSelect"
							name="carreraEspecialidadSelect" maxheight="250" required="true"
							missingMessage="Debe seleccionar la carrera o especialidad."
							scrollOnFocus="true" store="carreraEspecialidadStore"
							dojotype="dijit.form.FilteringSelect">
						</select>
					</div>
					<div class="grid1_3 fl margin_no_r">
						<label for="situacionAcademicaSelect"><span>*</span>
							Situación académica</label> <select id="situacionAcademicaSelect"
							name="situacionAcademicaSelect" required="true"
							missingMessage="Debe seleccionar la situación académica."
							store="situacionAcademicaStore"
							dojotype="dijit.form.FilteringSelect">
						</select>
					</div>
					<div class="clearfix"></div>
				</div>
				<div class="apoyoProspera">
					<div class="labeled">
						<span>*</span> ¿Realizaste tus estudios con ayuda del programa
						PROSPERA?
					</div>
					<div>
						<div class="conf_controls fl">
							<div class="si_01">
								<input type="radio" name="apoyoProsperaSi" id="apoyoProsperaSi"
									   data-dojo-type="dijit.form.RadioButton"
									   value="${registroCandidatoForm.apoyoProsperaSi}"
									   ${registroCandidatoForm.apoyoProspera eq registroCandidatoForm.apoyoProsperaSi ? 'checked' : ''}
									   onclick="displayProspera(true);"/>
								<label for="apoyoProsperaSi">Si</label>
							</div>
							<div class="no_01">
								<input type="radio" name="apoyoProsperaNo" id="apoyoProsperaNo"
										data-dojo-type="dijit.form.RadioButton"
										value="${registroCandidatoForm.apoyoProsperaNo}"
										${registroCandidatoForm.apoyoProspera eq registroCandidatoForm.apoyoProsperaNo ? 'checked' : ''}
									    onclick="displayProspera(false);"/>
								<label for="apoyoProsperaNo">No</label>
							</div>
						</div>
						<div class="clearfix"></div>

						<div id="prospera" style="display: none">
							<div class="labeled">
								<span>*</span> Ingresa folio familia Prospera
							</div>
							<input class="margin_top_10" type="text" id="folioProspera" name="folioProspera"
								   value="${registroCandidatoForm.folioProspera}"
								   dojoType="dijit.form.ValidationTextBox" maxlength="9" size="65"
								   trim="true" required="true"
								   missingMessage="Es necesario indicar el folio familia Prospera"
								   regExp="${regexp_folioprospera}"
								   invalidMessage="El folio familia Prospera es inválido."
							/>
							<div class="labeled">
								<span>*</span> Ingresa folio integrante Prospera
							</div>
							<input class="margin_top_10" type="text" id="folioIntegranteProspera" name="folioIntegranteProspera"
								   value="${registroCandidatoForm.folioIntegranteProspera}"
								   dojoType="dijit.form.ValidationTextBox" maxlength="16" size="65"
								   trim="true" required="true"
								   missingMessage="Es necesario indicar el folio familia Prospera"
								   regExp="${regexp_foliointegranteprospera}"
								   invalidMessage="El folio integrante Prospera es inválido."
							/>
						</div>
					</div>
					<div class="clearfix"></div>
				</div>
			</fieldset>
			<fieldset class="idiomas_01">
				<legend>Idiomas</legend>
				<div class="margin_top_10">
					<div class="grid1_3 fl">
						<label id="labelIdiomaSelect" for="idiomaSelect"><span>*</span>
							Idioma adicional al nativo</label> <select id="idiomaSelect"
							name="idiomaSelect" required="true"
							missingMessage="Debe seleccionar idioma adicional al nativo."
							store="idiomaStore" dojotype="dijit.form.FilteringSelect"
							value="${registroCandidatoForm.idIdioma}"
							onchange="actulizaCertificaciones();">
						</select>
					</div>
					<div class="grid1_3 fl">
						<label id="labelDominioSelect" for="dominioSelect">Dominio
							del idioma</label> <select id="dominioSelect" name="dominioSelect"
							
							missingMessage="Debe seleccionar el dominio del idioma"
							store="dominioStore" dojotype="dijit.form.FilteringSelect"
							value="${registroCandidatoForm.idDominio}"
							onchange="onSelectDominio();">
						</select>
					</div>
					<div class="grid1_3 fl margin_no_r">
						<label id="labelCertificacionSelect" for="certificacionSelect">La
							certificación obtenido</label> <select id="certificacionSelect"
							name="certificacionSelect" 
							missingMessage="Debe seleccionar la certificación obtenido."
							store="certificacionStore" dojotype="dijit.form.FilteringSelect"
							value="${registroCandidatoForm.idCertificacion}">
						</select>
					</div>
					<div class="clearfix"></div>
				</div>
			</fieldset>
			<fieldset class="computacion_01">
				<legend>Computación</legend>
				<div>
					<div class="labeled margin_top_10">
						<span>*</span> Conocimientos en computación
					</div>
					<p>
						<em>Puedes seleccionar más de una opción.</em>
					</p>
					<div>
						<ul class="conocimientos margin_top_20">
							<li><c:if
									test="${registroCandidatoForm.conocimientoCompNinguno==1}">
									<input type="checkbox" name="conocimientoCompNinguno"
										id="conocimientoCompNinguno" value="1" checked="checked"
										dojoType="dijit.form.CheckBox"
										onclick="limpiaConocimientosComp()">
								</c:if> <c:if
									test="${registroCandidatoForm.conocimientoCompNinguno!=1}">
									<input type="checkbox" name="conocimientoCompNinguno"
										id="conocimientoCompNinguno" value="1"
										dojoType="dijit.form.CheckBox"
										onclick="limpiaConocimientosComp()">
								</c:if> <label for="conocimientoCompNinguno">Ninguno</label></li>
							<li><c:if
									test="${registroCandidatoForm.conocimientoCompProcesadorTxt==1}">
									<input type="checkbox" name="conocimientoCompProcesadorTxt"
										id="conocimientoCompProcesadorTxt" value="1" checked="checked"
										dojoType="dijit.form.CheckBox"
										onclick="limpiaNingunConocimientosComp(this.checked)" />
								</c:if> <c:if
									test="${registroCandidatoForm.conocimientoCompProcesadorTxt!=1}">
									<input type="checkbox" name="conocimientoCompProcesadorTxt"
										id="conocimientoCompProcesadorTxt" value="1"
										dojoType="dijit.form.CheckBox"
										onclick="limpiaNingunConocimientosComp(this.checked)" />
								</c:if> <label for="conocimientoCompProcesadorTxt">Procesador
									de textos</label></li>
							<li><c:if
									test="${registroCandidatoForm.conocimientoCompHojaCal==1}">
									<input type="checkbox" name="conocimientoCompHojaCal"
										id="conocimientoCompHojaCal" value="1" checked="checked"
										dojoType="dijit.form.CheckBox"
										onclick="limpiaNingunConocimientosComp(this.checked)">
								</c:if> <c:if
									test="${registroCandidatoForm.conocimientoCompHojaCal!=1}">
									<input type="checkbox" name="conocimientoCompHojaCal"
										id="conocimientoCompHojaCal" value="1"
										dojoType="dijit.form.CheckBox"
										onclick="limpiaNingunConocimientosComp(this.checked)">
								</c:if> <label for="conocimientoCompHojaCal">Hojas de cálculo</label></li>
							<li><c:if
									test="${registroCandidatoForm.conocimientoCompInternet==1}">
									<input type="checkbox" name="conocimientoCompInternet"
										id="conocimientoCompInternet" value="1" checked="checked"
										dojoType="dijit.form.CheckBox"
										onclick="limpiaNingunConocimientosComp(this.checked)">
								</c:if> <c:if
									test="${registroCandidatoForm.conocimientoCompInternet!=1}">
									<input type="checkbox" name="conocimientoCompInternet"
										dojoType="dijit.form.CheckBox" id="conocimientoCompInternet"
										value="1"
										onclick="limpiaNingunConocimientosComp(this.checked)">
								</c:if> <label for="conocimientoCompInternet">Internet o correo
									electrónico</label></li>
							<li><c:if
									test="${registroCandidatoForm.conocimientoCompRedes==1}">
									<input type="checkbox" name="conocimientoCompRedes"
										id="conocimientoCompRedes" value="1" checked="checked"
										dojoType="dijit.form.CheckBox"
										onclick="limpiaNingunConocimientosComp(this.checked)">
								</c:if> <c:if test="${registroCandidatoForm.conocimientoCompRedes!=1}">
									<input type="checkbox" name="conocimientoCompRedes"
										dojoType="dijit.form.CheckBox" id="conocimientoCompRedes"
										value="1"
										onclick="limpiaNingunConocimientosComp(this.checked)">
								</c:if> <label for="conocimientoCompRedes">Redes sociales</label></li>
						</ul>
						<div class="experiencia_01 margin_top_30 ">
							<label for="conocimientoCompOtros">Otros conocimientos en
								computación</label>
							<textarea rows="6" cols="23" maxlength="2000" trim="true"
								onchange="return maximaLongitud(this,event,1000)"
								onKeyUp="return maximaLongitud(this,event,1000)"
								onpaste="return false;" id="conocimientoCompOtros"
								regExp="^[A-Za-z\s\-.áéíóúÁÉÍÓÚñÑ0-9.,;:/']{1,2000}$" 
								onkeypress="return caracteresValidos(event);" 
								onpaste="return false;"
		            	  		onblur="return maximaLongitud(this,2000)"
								name="conocimientoCompOtros" required="false" class="textGoogie">${registroCandidatoForm.conocimientoCompOtros}
							</textarea>
							<script type="text/javascript">
				         		var vSpellCon = new GoogieSpell("googiespell/", "<%=vProxy%>");
				         		vSpellCon.setLanguages({'es': 'Español'});
				         		vSpellCon.hideLangWindow();
				         		vSpellCon.decorateTextarea("conocimientoCompOtros");
							</script>
						</div>
					</div>
				</div>
				<div class="margin_40"></div> 
			</fieldset>
			<fieldset class="habilidades_01">
				<legend>Habilidades y actitudes </legend>
				<div class="margin_top_10">
					<div class="labeled">
						<span>*</span> Selecciona máximo 5 habilidades y actitudes que te
						caracterizan.
					</div>
					<div class="margin_top_20">
						<fmt:formatNumber var="numbloque"
							value="${fn:length(registroCandidatoForm.opcHabilidades) / 3}"
							maxFractionDigits="0" />

						<c:forEach var="habilidadOpc"
							items="${registroCandidatoForm.opcHabilidades}" varStatus="index">
							<c:set var="open"
								value="${index.count==1 || index.count==numbloque+1 || index.count==(numbloque*2)+1}" />
							<c:set var="close"
								value="${index.count==numbloque || index.count==(numbloque*2) || index.count==fn:length(opcHabilidades)}" />

							<c:set var="checkmedio" value="" />

							<c:forEach var="habilidadSaved"
								items="${registroCandidatoForm.idHabilidades}">

								<c:if test="${habilidadOpc.idCatalogoOpcion == habilidadSaved}">
									<c:set var="checkmedio" value="checked" />
								</c:if>
							</c:forEach>

							<c:if test="${open}">
								<div class="grid1_3 fl">
									<ul>
							</c:if>
							<li><label> <input type="checkbox"
									id="idHabilidades${habilidadOpc.idCatalogoOpcion}"
									class="idHabilidades" dojoType="dijit.form.CheckBox"
									name="idHabilidades" value="${habilidadOpc.idCatalogoOpcion}"
									${checkmedio} /> ${habilidadOpc.opcion}
							</label></li>

							<c:if test="${close}">
								</ul>
					</div>
					</c:if>

					</c:forEach>
					<div class="clearfix"></div>
				</div>
		</div>
		</fieldset>





		<h3>Expectativa y experiencia laboral</h3>
		<fieldset class="experiencia_1">
			<legend>Expectativas laborales</legend>
			<div class="margin_top_10">
				<label for="ocupacionDeseada"><span>*</span> ¿Qué empleo
					buscas?</label> <input type="text" dojoType="dijit.form.ValidationTextBox"
					name="ocupacionDeseada" id="ocupacionDeseada" value=""
					onkeyup="javascript:autocompleteOcupacion();" size="50"
					maxlength="45" oncopy="return false;" oncut="return false"
					onpaste="return false;" trim="true;" required="true"
					missingMessage="Es necesario indicar ¿Qu&eacute; empleo buscas?."
					/>
				<div class="suggestionsBox" id="ocupacionesListDiv"
					onblur="javascript:cerrarAutocomplete();" style="display: none">
					<div class="suggestionList" id="areaMensajes">
						<ul id="listaOcupaciones" style="height: 200px; overflow: auto">
						</ul>
					</div>
				</div>
			</div>
			<div class="margin_top_10">
				<div class="grid1_3 fl">
					<label><span>*</span> ¿Qué salario mensual pretendes?</label> <input
						type="text" name="salarioPretendido" id="salarioPretendido"
						required="true"
						missingMessage="Es necesario indicar ¿Qué salario mensual pretendes?."
						regExp="${regexpsalario7}"
						invalidMessage="El valor especificado de ¿Qué salario mensual pretendes? no es válido."
						value="${registroCandidatoForm.salarioPretendido}" maxlength="10"
						dojoType="dijit.form.ValidationTextBox" />
				</div>
				<div class="grid1_3 fl">
					<label for="tiposContratoSelect">Tipo de contrato</label> <select
						id="tiposContratoSelect" name="tiposContratoSelect"
						required="false" store="tiposContratoDeseadoStore"
						invalidMessage="El valor especificado no es válido por tipo de contrato."
						missingMessage="Debe seleccionar una opción del catálogo por tipo de contrato."
						dojotype="dijit.form.FilteringSelect"
						value="${registroCandidatoForm.idTipoContratoDeseado}">
					</select>
				</div>
				<div class="clearfix"></div>
			</div>
			<div class="margin_top_10">
				<label for="experienciaTotalSelect"><span>*</span> Años de
					experiencia</label> <select id="experienciaTotalSelect"
					name="experienciaTotalSelect" required="true"
					missingMessage="Debe seleccionar los años de experiencia."
					
					store="experienciaTotalStore" dojotype="dijit.form.FilteringSelect"
					value="${registroCandidatoForm.idExperienciaTotal}">
				</select>
			</div>
			<br> <input type="button" id="btnOpcion2"
				value="Agregar otra opción de empleo"
				onclick="mostrarOtraOpcionDeEmpleo();" />
			<div class="otra_opcion" id="otra_opcion_empleo">
				<div class="grid1_3">
					<label for="ocupacionDeseada2Select">¿Qué otra opción de
						empleo te interesa?</label> <input type="text"
						dojoType="dijit.form.ValidationTextBox"
						name="ocupacionDeseada2Select" id="ocupacionDeseada2Select"
						value="" onkeyup="javascript:autocompleteOcupacion2();" size="50"
						maxlength="45" oncopy="return false;" oncut="return false"
						onpaste="return false;" trim="true;" required="true"
						missingMessage="Debe seleccionar ¿Qué otra opción de empleo te interesa?."
						invalidMessage="Se requiere seleccionar una opción del catálogo." />
					<div class="suggestionsBox" id="ocupacionesListDiv2"
						onblur="javascript:cerrarAutocompleteOccupacion2();"
						style="display: none">
						<div class="suggestionList" id="areaMensajes2">
							<ul id="listaOcupaciones2" style="height: 200px; overflow: auto">
							</ul>
						</div>
					</div>
				</div>
				<div class="grid1_3">
					<label for="experiencia2Select">Años de experiencia</label> <select
						id="experiencia2Select" name="experiencia2Select" required="true"
						missingMessage="Debe seleccionar los años de experiencia."
						store="experienciaTotalStore"
						dojotype="dijit.form.FilteringSelect"
						value="${registroCandidatoForm.idExperiencia2}">
					</select>

				</div>
			</div>
			<div class="experiencia_01 margin_top_30">
				<label for="descripcionExperiencia"><span>*</span> Describe
					tu experiencia</label>
				<p>
					<em>Describe lo que sabes hacer relacionado al empleo que
						buscas; Ejemplo de auxiliar administrativo: Elaboraciones de
						nómina, altas y bajas y modificaciones en el seguro social,
						INFONAVIT, pago de nómina, etcétera.</em>
				</p>
				
				<textarea rows="6" cols="145" maxlength="2000" trim="true"
						style="height: 110px !important; max-height: 110px !important;"
						onchange="return maximaLongitud(this,event,1000)"						
						onpaste="return false;"
						regExp="^[A-Za-z\s\-.áéíóúÁÉÍÓÚñÑ0-9.,;:/']{1,2000}$" 
						onkeypress="return caracteresValidos(event);" 
						onpaste="return false;"
						missingMessage="Debe ingresar la descripción de su experiencia."
            	  		onblur="return maximaLongitud(this,2000)"
            	  			id="descripcionExperiencia"
						name="descripcionExperiencia" required="false" class="textGoogie">${registroCandidatoForm.descripcionExperiencia}
					</textarea>
					<script type="text/javascript">					    
		         		var vSpellCon = new GoogieSpell("googiespell/", "<%=vProxy%>");
		         		vSpellCon.setLanguages({'es': 'Español'});
		         		vSpellCon.hideLangWindow();
		         		vSpellCon.decorateTextarea("descripcionExperiencia");
					</script>
				</div>				
				<div class="margin_40"></div>            		            	 
				 				 
			</div>
			<div class="dom_shift">
				<div class="grid1_3 fl">
					<div class="labeled">
						<span>*</span> ¿Puedes viajar?
					</div>
					<div>
						<input type="radio" name="viajar" id="viajarSi"
							data-dojo-type="dijit.form.RadioButton"
							value="${registroCandidatoForm.viajarSi}"><label
							for="viajarSi">Si</label><br> <input type="radio"
							name="viajar" id="viajarNo"
							data-dojo-type="dijit.form.RadioButton"
							value="${registroCandidatoForm.viajarNo}" checked="checked"><label
							for="viajarNo">No</label>
					</div>
				</div>
				<div class="grid1_3 fl">
					<div class="labeled">
						<span>*</span> ¿Puedes radicar en otra ciudad?
					</div>
					<div>
						<input type="radio" name="radicar" id="radicarSi"
							data-dojo-type="dijit.form.RadioButton"
							value="${registroCandidatoForm.radicarSi}"><label
							for="radicarSi">Si</label><br> <input type="radio"
							name="radicar" id="radicarNo"
							data-dojo-type="dijit.form.RadioButton"
							value="${registroCandidatoForm.radicarNo}" checked="checked"><label
							for="radicarNo">No</label>
					</div>
				</div>
				<div class="clearfix"></div>
			</div>
		</fieldset>







		<fieldset class="experiencia_1">
			<legend>Trabajo actual o último</legend>
			<c:if test="${registroCandidatoForm.registroPPC}">
				<p class="labeled">
				<div class="grid1_3 fl">
					<label for="nombreSocialEmpresa"><span>*</span> Nombre o
						razón social de la empresa</label> <input type="text"
						id="nombreSocialEmpresa" name="nombreSocialEmpresa"
						value="${registroCandidatoForm.nombreSocialEmpresa}"
						dojoType="dijit.form.ValidationTextBox" maxlength="150"
						regExp="^[A-Za-z0-9\s\-.áéíóúÁÉÍÓÚñÑ']{3,150}$" size="60"
						uppercase="true" trim="true"
						invalidMessage="El valor especificado Nombre o	razón social de la empresa no es válido"
						missingMessage="Es necesario indicar que Nombre o razón social de la empresa." />
				</div>
				<div class="omitir grid1_3 fl margin_top_20">
					<input type="checkbox" name="omitirTrabajoEnPerfilCh"
						dojoType="dijit.form.CheckBox" id="omitirTrabajoEnPerfilCh" /> <label
						for="omitirTrabajoEnPerfilCh" class="fw_no_bold">Omitir
						este dato en la publicación de mi perfil</label>
				</div>
				<div class="clearfix"></div>
				<div class="grid1_3 fl margin_top_20">

					<label for="puestoDesempenado"><span>*</span> Puesto
						desempeñado</label> <input type="text" id="puestoDesempenado"
						name="puestoDesempenado"
						value="${registroCandidatoForm.puestoDesempenado}"
						dojoType="dijit.form.ValidationTextBox" maxlength="150" size="60"
						uppercase="true" trim="true"
						regExp="^[A-Za-z0-9\s\-.áéíóúÁÉÍÓÚñÑ']{1,150}$"
						invalidMessage="El valor especificado de puesto desempeñado no es válido"
						missingMessage="Es necesario indicar que el puesto desempeñado." />

				</div>
				<div class="grid1_3 fl margin_top_20">

					<label for="jeraquiaSelect"><span>*</span> Jerarquía del
						puesto</label> <select id="jeraquiaSelect" name="jeraquiaSelect"
						required="false" store="jeraquiaStore"
						dojotype="dijit.form.FilteringSelect"
						value="${registroCandidatoForm.idJerquia}"
						invalidMessage="El valor especificado de jerarquía del puesto no es válido."
						missingMessage="Debe seleccionar el jerarquía del puesto."></select>
				</div>
				<div class="clearfix"></div>
				<div class="grid1_3 fl margin_top_20">

					<label for="personaACargoSelect"><span>*</span> Número de
						personas a cargo</label> <select id="personaACargoSelect"
						name="personaACargoSelect" required="false"
						store="personaACargoStore" dojotype="dijit.form.FilteringSelect"
						value="${registroCandidatoForm.idPersonaACargo}"
						missingMessage="Debe seleccionar el número de personas a cargo."></select>
				</div>
				<div class="grid1_3 fl margin_top_20">
					<label for="salarioRecibido"><span>*</span> Salario mensual
						recibido</label> <input type="text" name="salarioRecibido"
						id="salarioRecibido" required="true"
						missingMessage="Debe seleccionar salario mensual recibido."
						regExp="${regexpsalario6}"
						invalidMessage="El valor especificado de salario mensual recibido no es válido."
						value="${registroCandidatoForm.salarioRecibido}" maxlength="9"
						dojoType="dijit.form.ValidationTextBox" />
				</div>
				<div class="clearfix"></div>
				<div class="grid1_3 fl">
					<div class="labeled">
						<span>*</span> Fecha de ingreso
					</div>
					<div class="fl">
						<select id="diaLaboresInicialSelect"
							name="diaLaboresInicialSelect" required="true"
							missingMessage="Debe seleccionar la fecha de ingreso."
							store="diasStore" dojotype="dijit.form.FilteringSelect"
							value="${registroCandidatoForm.historiaLaboralVO.diaLaboresInicial }"></select>



						<select id="mesLaboresInicialSelect"
							name="mesLaboresInicialSelect" required="true"
							missingMessage="Debe seleccionar la fecha de ingreso."
							store="mesesStore" dojotype="dijit.form.FilteringSelect"
							value="${registroCandidatoForm.historiaLaboralVO.mesLaboresInicial }"></select>


						<select id="anioLaboresInicialSelect"
							name="anioLaboresInicialSelect" required="true"
							missingMessage="Debe seleccionar la fecha de ingreso."
							store="aniosStore" dojotype="dijit.form.FilteringSelect"
							value="${registroCandidatoForm.historiaLaboralVO.anioLaboresInicial }">
						</select>
					</div>
				</div>
				<div class="grid1_3 fl">
					<div class="labeled">
						<span>*</span> Fecha de terminación
					</div>
					<div class="fl">
						<select id="diaLaboresFinalSelect" name="diaLaboresFinalSelect"
							required="true"
							missingMessage="Debe seleccionar la fecha de terminación."
							store="diasStore" dojotype="dijit.form.FilteringSelect"
							value="${registroCandidatoForm.historiaLaboralVO.diaLaboresFinal }"></select>



						<select id="mesLaboresFinalSelect" name="mesLaboresFinalSelect"
							required="true"
							missingMessage="Debe seleccionar la fecha de terminación."
							store="mesesStore" dojotype="dijit.form.FilteringSelect"
							value="${registroCandidatoForm.historiaLaboralVO.mesLaboresFinal }"></select>

						<select id="anioLaboresFinalSelect" name="anioLaboresFinalSelect"
							required="true"
							missingMessage="Debe seleccionar la fecha de terminación."
							store="aniosStore" dojotype="dijit.form.FilteringSelect"
							value="${registroCandidatoForm.historiaLaboralVO.anioLaboresFinal }"></select>
					</div>
				</div>
				<div class="clearfix"></div>
				<div class="margin_top_30">
					<label for="funcionesDesempenadas"><span>*</span> Funciones
						desempeñadas</label>
					<textarea
						style="height: 110px !important; max-height: 110px !important;"
						rows="6" cols="23" maxlength="2000" trim="true"
						onchange="return maximaLongitud(this,event,2000)"
						onKeyUp="return maximaLongitud(this,event,2000)"
						onpaste="return false;" name="funcionesDesempenadas1"
						regExp="^[\w\d\s\.&aacute;&eacute;&iacute;&oacute;&uacute;&Aacute;&Eacute;&Iacute;&Oacute;&Uacute;&Ntilde;&ntilde;]{1,2000}$"
						id="funcionesDesempenadas">${registroCandidatoForm.funcionesDesempenadas}</textarea>
					<script type="text/javascript">
		         		var vSpellCon = new GoogieSpell("googiespell/", "<%=vProxy%>");
		         		vSpellCon.setLanguages({'es': 'Español'});
		         		vSpellCon.hideLangWindow();
		         		vSpellCon.decorateTextarea("funcionesDesempenadas");
					</script>
				</div>
				
			</c:if>
			<c:if test="${not registroCandidatoForm.registroPPC}">

				<p class="labeled">
				<div class="grid1_3 fl">
					<label for="nombreSocialEmpresa"> Nombre o razón social de
						la empresa</label> <input type="text" id="nombreSocialEmpresa"
						name="nombreSocialEmpresa"
						value="${registroCandidatoForm.nombreSocialEmpresa}"
						dojoType="dijit.form.ValidationTextBox" maxlength="150"
						regExp="^[A-Za-z0-9\s\-.áéíóúÁÉÍÓÚñÑ']{3,150}$" size="60"
						uppercase="true" trim="true"
						invalidMessage="El valor especificado el nombre de la empresa no es válido." />
				</div>
				<div class="omitir grid1_3 fl margin_top_20">
					<input type="checkbox" name="omitirTrabajoEnPerfilCh"
						dojoType="dijit.form.CheckBox" id="omitirTrabajoEnPerfilCh"
						value="${registroCandidatoForm.omitirTrabajoEnPerfil}" /> <label
						for="omitirTrabajoEnPerfilCh" class="fw_no_bold">Omitir
						este dato en la publicación de mi perfil</label>
				</div>
				<div class="clearfix"></div>
				<div class="grid1_3 fl margin_top_20">

					<label for="puestoDesempenado"> Puesto desempeñado</label> <input
						type="text" id="puestoDesempenado" name="puestoDesempenado"
						value="${registroCandidatoForm.puestoDesempenado}"
						dojoType="dijit.form.ValidationTextBox" maxlength="150"
						regExp="^[A-Za-z0-9\s\-.áéíóúÁÉÍÓÚñÑ']{1,150}$" size="60"
						uppercase="true" trim="true" />
				</div>
				<div class="grid1_3 fl margin_top_20">

					<label for="jeraquiaSelect"> Jerarquía del puesto</label> <select
						id="jeraquiaSelect" name="jeraquiaSelect" required="false"
						store="jeraquiaStore" dojotype="dijit.form.FilteringSelect"
						value="${registroCandidatoForm.idJerquia}"></select>
				</div>
				<div class="clearfix"></div>
				<div class="grid1_3 fl margin_top_20">

					<label for="personaACargoSelect">Número de personas a cargo</label>
					<select id="personaACargoSelect" name="personaACargoSelect"
						required="false" store="personaACargoStore"
						dojotype="dijit.form.FilteringSelect"
						value="${registroCandidatoForm.idPersonaACargo}"></select>
				</div>
				<div class="grid1_3 fl margin_top_20">
					<label for="salarioRecibido"> Salario mensual recibido</label> <input
						type="text" name="salarioRecibido" id="salarioRecibido"
						required="false"
						missingMessage="Debe seleccionar el salario mensual recibido."
						regExp="${regexpsalario6}"
						invalidMessage="Debe seleccionar el salario mensual recibido."
						value="${registroCandidatoForm.salarioRecibido}" maxlength="9"
						dojoType="dijit.form.ValidationTextBox" />
				</div>
				<div class="clearfix"></div>
				<div class="grid1_3 fl">
					<div class="labeled">Fecha de ingreso</div>
					<div class="fl">
						<select id="diaLaboresInicialSelect"
							name="diaLaboresInicialSelect" required="false"
							missingMessage="Debe seleccionar la fecha de ingreso."
							store="diasStore" dojotype="dijit.form.FilteringSelect"
							value="${registroCandidatoForm.historiaLaboralVO.diaLaboresInicial }"></select>



						<select id="mesLaboresInicialSelect"
							name="mesLaboresInicialSelect" required="false"
							missingMessage="Debe seleccionar la fecha de ingreso."
							store="mesesStore" dojotype="dijit.form.FilteringSelect"
							value="${registroCandidatoForm.historiaLaboralVO.mesLaboresInicial }"></select>


						<select id="anioLaboresInicialSelect"
							name="anioLaboresInicialSelect" required="false"
							missingMessage="Debe seleccionar la fecha de ingreso."
							store="aniosStore" dojotype="dijit.form.FilteringSelect"
							value="${registroCandidatoForm.historiaLaboralVO.anioLaboresInicial }">
						</select>
					</div>
				</div>
				<div class="grid1_3 fl">
					<div class="labeled">Fecha de terminación</div>
					<div class="fl">
						<select id="diaLaboresFinalSelect" name="diaLaboresFinalSelect"
							required="false"
							missingMessage="Debe seleccionar la fecha de terminación."
							store="diasStore" dojotype="dijit.form.FilteringSelect"
							value="${registroCandidatoForm.historiaLaboralVO.diaLaboresFinal }"></select>



						<select id="mesLaboresFinalSelect" name="mesLaboresFinalSelect"
							required="false"
							missingMessage="Debe seleccionar la fecha de terminación."
							store="mesesStore" dojotype="dijit.form.FilteringSelect"
							value="${registroCandidatoForm.historiaLaboralVO.mesLaboresFinal }"></select>

						<select id="anioLaboresFinalSelect" name="anioLaboresFinalSelect"
							required="false"
							missingMessage="Debe seleccionar la fecha de terminación."
							store="aniosStore" dojotype="dijit.form.FilteringSelect"
							value="${registroCandidatoForm.historiaLaboralVO.anioLaboresFinal }"></select>
					</div>
				</div>
				<div class="clearfix"></div>
				<div class="experiencia_01 margin_top_30">
					<label for="funcionesDesempenadas">Funciones desempeñadas</label>
					<textarea
						style="height: 110px !important; max-height: 110px !important;"
						rows="6" cols="145" maxlength="2000" trim="true"
						onchange="return maximaLongitud(this,event,2000)"
						onKeyUp="return maximaLongitud(this,event,2000)"
						regExp="^^[A-Za-z\s\-.áéíóúÁÉÍÓÚñÑ']{3,2000}$"
						onpaste="return false;" name="funcionesDesempenadas"
						id="funcionesDesempenadas">${registroCandidatoForm.funcionesDesempenadas}
					</textarea>
					<script type="text/javascript">
		         		var vSpellCon = new GoogieSpell("googiespell/", "<%=vProxy%>");
		         		vSpellCon.setLanguages({'es': 'Español'});
		         		vSpellCon.hideLangWindow();
		         		vSpellCon.decorateTextarea("funcionesDesempenadas");
					</script>
				</div>
				<div class="margin_40"></div>
			</c:if>

		</fieldset>


		<h3>Situación laboral</h3>
		<fieldset class="situacion_1">
			<legend>Situación laboral actual</legend>
			<div class="trabajo_01">
				<div class="labeled margin_top_10">
					<span>*</span> ¿Trabajas actualmente?
				</div>
				<div>
					<c:if test="${registroCandidatoForm.registroPPC}">
						<input type="radio" name="trabajas" id="trabajasSi" disabled
							data-dojo-type="dijit.form.RadioButton">
						<label for="trabajasSi">Si</label>
						<br>
						<input type="radio" name="trabajas" id="trabajasNo"
							data-dojo-type="dijit.form.RadioButton" checked="checked"
							disabled>
						<label for="trabajasNo">No</label>
					</c:if>
					<c:if test="${not registroCandidatoForm.registroPPC}">
						<input type="radio" name="trabajas" id="trabajasSi"
							data-dojo-type="dijit.form.RadioButton"
							value="${registroCandidatoForm.trabajaSi}" checked="checked"
							onclick="javascript:trabaja(this.value);">
						<label for="trabajasSi">Si</label>
						<br>
						<input type="radio" name="trabajas" id="trabajasNo"
							data-dojo-type="dijit.form.RadioButton"
							value="${registroCandidatoForm.trabajaNo}"
							onclick="javascript:trabaja(this.value);">
						<label for="trabajasNo">No</label>
					</c:if>

				</div>
			</div>
			<div class="grid1_3 margin_top_20 fl">
				<label for="motivoSelect"><span>*</span> Motivo por el que buscas trabajo</label>
				<select id="motivoSelect" name="motivoSelect"
					required="true"
					missingMessage="Debe seleccionar el motivo por que buscas trabajo."
					store="motivosBuscaTrabajoStore"
					dojotype="dijit.form.FilteringSelect"
					value="${registroCandidatoForm.idMotivo}">
				</select>
			</div>
			<div id="motivoOtroContainer" class="grid1_3 margin_top_20 fl" style="display: none;">
				<label for="descripcionOtroMotivoBusq"><span>*</span> Describe el otro motivo</label>
				<textarea
						style="height: 50px !important; max-height: 50px !important;"
						rows="6" cols="70" maxlength="2000" trim="true"
						onchange="return maximaLongitud(this,event,2000)"
						onKeyUp="return maximaLongitud(this,event,2000)"
						regExp="^^[A-Za-z\s\-.áéíóúÁÉÍÓÚñÑ']{3,2000}$"
						onpaste="return false;"
						name="descripcionOtroMotivoBusq" id="descripcionOtroMotivoBusq">
						${registroCandidatoForm.descripcionOtroMotivoBusq}
				</textarea>
			</div>
			<div class="clearfix"></div>
			<div class="margin_top_20">
				<label for="diaSelect"><span>*</span> Fecha en que comenzaste a buscar trabajo</label>
				<div class="fl">
					<select id="diaSelect" name="diaSelect" promptMessage="Dia"
						required="true"
						missingMessage="Indicar el dia desde que comenzo a buscar trabajo."
						invalidMessage="El dia indicado es invalido, favor de verificar su selección."
						value="${registroCandidatoForm.diaBuscar}" autocomplete="false"
						dojotype="dijit.form.FilteringSelect" store="diasStore"></select>

					<select id="mesSelect" name="mesSelect" promptMessage="Mes"
						required="true"
						missingMessage="Indicar el mes desde que comenzo a buscar trabajo."
						invalidMessage="El mes indicado es invalido, favor de verificar su selección."
						value="${registroCandidatoForm.mesBuscar}" autocomplete="false"
						dojotype="dijit.form.FilteringSelect" store="mesesStore"></select>

					<select id="anioSelect" name="anioSelect" promptMessage="Año"
						required="true"
						missingMessage="Indicar el año desde que comenzo a buscar trabajo."
						invalidMessage="El año indicado es invalido, favor de verificar su selección."
						value="${registroCandidatoForm.anioBuscar}" autocomplete="false"
						dojotype="dijit.form.FilteringSelect" store="aniosStore"></select>
				</div>
				<div class="clearfix"></div>
			</div>



			<div class="margin_top_20">
				<div class="labeled">
					<span>*</span> ¿Qué otros medios has utilizado para buscar trabajo?
				</div>

				<fmt:formatNumber var="numbloque"
					value="${fn:length(registroCandidatoForm.otrosMedios) / 3}"
					maxFractionDigits="0" />

				<div class="buscar_trabajo margin_top_10">
					<c:forEach var="otrosMedios"
						items="${registroCandidatoForm.otrosMedios}" varStatus="index">
						<c:set var="open"
							value="${index.count==1 || index.count==numbloque+1 || index.count==(numbloque*2)+1}" />
						<c:set var="close"
							value="${index.count==numbloque || index.count==(numbloque*2) || index.count==fn:length(registroCandidatoForm.otrosMedios)}" />

						<c:set var="checkmedio" value="" />

						<c:forEach var="idOtrosAux"
							items="${registroCandidatoForm.idOtrosMedios}">
								name="idOtrosMedios"
								<c:if test="${otrosMedios.idCatalogoOpcion == idOtrosAux}">
								<c:set var="checkmedio" value="checked" />
							</c:if>
						</c:forEach>

						<c:if test="${open}">
							<div class="grid1_3 fl">
								<ul>
						</c:if>
						<li><label> <input type="checkbox"
								id="idOtrosMedios${otrosMedios.idCatalogoOpcion}"
								class="idOtrosMedios" name="idOtrosMedios"
								dojoType="dijit.form.CheckBox"
								value="${otrosMedios.idCatalogoOpcion}" ${checkmedio}
								onclick="limpiaOtrosMedios(this)" /> ${otrosMedios.opcion}
						</label></li>

						<c:if test="${close}">
							</ul>
				</div>
				</c:if>

				</c:forEach>
				<div class="clearfix"></div>
			</div>
			<div class="margin_top_20"></div>
			<label for="enterasteSelect"><span>*</span> ¿Cómo te
				enteraste del Portal del Empleo?</label> <select id="enterasteSelect"
				name="enterasteSelect" required="true"
				missingMessage="Debe seleccionar ¿Cómo te enteraste del Portal del Empleo?"
				store="portalEnterasteStore" dojotype="dijit.form.FilteringSelect"
				value="${registroCandidatoForm.idComoEnteraste}">
			</select>
		</fieldset>


		<h2 class="terminos">Términos y condiciones para el Portal del
			Empleo</h2>
		<div class="condiciones_1">
			<div class="condiciones_01">
				<div class="subtitulo">Consideraciones Generales:</div>
				<style>
				.condiciones_01 ol li ul li {background-image:none !important;}
				.condiciones_01 ol > li {border-bottom: 1px solid #dbe0e0;}
				</style>
				<ol>
				    <li>Las presentes Pol&iacute;ticas y Condiciones de Uso reglamentan el uso de los servicios que proporciona el sitio Web <strong>www.empleo.gob.mx</strong> (en adelante  <strong>Portal del Empleo</strong>) de la Secretar&iacute;a del Trabajo y Previsi&oacute;n Social (en adelante <strong>STPS</strong>). Los servicios que proporciona el <strong>Portal del Empleo</strong> est&aacute;n sujetos al cumplimiento de las presentes Pol&iacute;ticas y Condiciones de Uso por parte de los usuarios.</li>
				    <li>Las empresas, candidatos de empleo y en general toda persona que ingrese al <strong>Portal del Empleo</strong> por cualquier raz&oacute;n, acepta en forma voluntaria y sin ninguna reserva estar de acuerdo con Pol&iacute;ticas y Condiciones de uso del <strong>Portal del Empleo</strong>, y en caso contrario, tienen derecho a no aceptar su uso y los servicios que el Portal proporciona.</li>
				    <li>La <strong>STPS</strong> no es responsable por el uso de la informaci&oacute;n que se registre en el <strong>Portal del Empleo</strong>, as&iacute; como de cualquier perjuicio originado al usuario por el uso de la misma. La <strong>STPS</strong> se&ntilde;alar&aacute; con claridad las fuentes de informaci&oacute;n y los criterios de confiabilidad que se hubieran tomado en cuenta para la incorporaci&oacute;n de la informaci&oacute;n sobre las ofertas de empleo, por lo que en ning&uacute;n caso ser&aacute; responsable por los juicios de valor o las interpretaciones que los usuarios realicen sobre la exactitud o validez de la informaci&oacute;n publicada, as&iacute; como por cualquier perjuicio o reclamo de cualquier otra &iacute;ndole que pudiera ser imputable al uso de la informaci&oacute;n.</li>
				    <li>La <strong>STPS</strong> no es responsable de la informaci&oacute;n que se muestra en otros sitios web a los que el usuario tiene acceso desde el <strong>Portal del Empleo</strong>. Con el objeto de facilitarle la navegaci&oacute;n y ampliar la informaci&oacute;n ofrecida en el <strong>Portal del Empleo</strong> se establecen enlaces a otras p&aacute;ginas Web. Dado que el <strong>Portal del Empleo</strong>, no es titular, ni responsable de las p&aacute;ginas Web enlazadas, le recomendamos examine la Pol&iacute;tica de uso de &eacute;stas cuando acceda a las mismas. El <strong>Portal del Empleo</strong>, en ning&uacute;n caso ser&aacute; responsable de los contenidos ni de cualquier otro aspecto derivado de las Webs de terceros.</li>
				    <li>La <strong>STPS</strong> se reserva el derecho de suspender el servicio a todos aquellos usuarios que no cumplan con estas Pol&iacute;ticas y Condiciones de Uso.</li>
				    <li>El <strong>Portal del Empleo</strong> se reserva el derecho de modificar y actualizar, cualquier contenido de configuraci&oacute;n o de dise&ntilde;o, cualquier servicio y las presentes Pol&iacute;ticas y Condiciones de Uso en cualquier momento y sin necesidad de previo aviso o notificaci&oacute;n a los usuarios, as&iacute; como a&ntilde;adir servicios nuevos, entendi&eacute;ndose que las mismas son aceptadas si se utilizan los servicios que ofrece el sitio.</li>
				    <li>La <strong>STPS</strong> no es responsable de la veracidad de la informaci&oacute;n publicada en las ofertas de empleo y los perfiles laborales de los candidatos de empleo, as&iacute; como de los resultados finales del contacto entre empresas y candidatos.</li>
				    <li>Queda absolutamente prohibido la utilizaci&oacute;n de los contenidos, logotipos y formatos de registro que utiliza el <strong>Portal del Empleo</strong>, salvo que la <strong>STPS</strong> lo autorice en forma expl&iacute;cita y por escrito.</li>
				    <li>El <strong>Portal del Empleo</strong> no se responsabiliza de la inactividad del sitio web como consecuencia de causas ajenas como cortes de energ&iacute;a el&eacute;ctrica, aver&iacute;a en el hosting, aver&iacute;as o cortes en las redes de comunicaci&oacute;n, y en general debido a cualquier aver&iacute;a o fallo en nuestro sistema inform&aacute;tico, ajenas a nuestra voluntad, comprometi&eacute;ndonos a hacer todo lo que sea posible para restablecer el servicio a la brevedad posible.</li>
				    <li>El usuario deber&aacute; comunicar al <strong>Portal del Empleo</strong> cualquier ilegalidad o anomal&iacute;a que observe, tras estudiarla procederemos en consecuencia con toda la celeridad posible.</li>
				    <li>Lea peri&oacute;dicamente las condiciones de uso, estas, pueden ser modificadas sin previo aviso.</li>
				    <h3>De la secci&oacute;n &ldquo;Candidatos&rdquo;</h3>
				    <li>Los servicios que proporciona el <strong>Portal del Empleo</strong> a los candidatos de empleo del pa&iacute;s son enteramente gratuitos.</li>
				    <li>Para la utilizaci&oacute;n de los servicios que ofrece el <strong>Portal del Empleo</strong>, es necesario que los candidatos se registren en el sitio. Deber&aacute; hacerlo a partir de su Clave &Uacute;nica de Registro Poblacional (CURP) la cu&aacute;l ser&aacute; validada en l&iacute;nea con la base de datos del Registro Nacional de Poblaci&oacute;n (RENAPO). Si no cuenta todav&iacute;a con su CURP, le pedimos se dirija a un M&oacute;dulo CURP para tramitarla. Consulta las oficinas y horarios de atenci&oacute;n haciendo <a href="http://www.gob.mx/tramites/ficha/obtencion-de-la-curp/SEGOB173" target="_blank">clic aqu&iacute;</a>.</li>
				    <li>El ingreso al <strong>Portal del Empleo</strong> ser&aacute; a trav&eacute;s de un nombre de usuario y contrase&ntilde;a los cuales quedar&aacute;n conformados de la siguiente manera:
				    <ul>
				        <li>Nombre de Usuario: Tienes dos alternativas para conformar el Nombre de usuario la primera es usar tu cuenta de correo electr&oacute;nico, si ya tienes una o puedes generarla. La segunda opci&oacute;n es que generes un nombre de usuario de entre 8 y 12 caracteres utilizando letras may&uacute;sculas, letras min&uacute;sculas y n&uacute;meros puedes hacer una combinaci&oacute;n.</li>
				        <li>Contrase&ntilde;a: Se te solicitar&aacute; cada vez que quieras tener acceso a tu Espacio y a tu Perfil Laboral, y debe ser una sola palabra de 8 a 12 caracteres como m&iacute;nimo que pueden ser letras may&uacute;sculas o min&uacute;sculas y/o n&uacute;meros.</li>
				    </ul>
				    </li>
				    <li>Si el resultado de la validaci&oacute;n de la CURP es satisfactorio, podr&aacute;s continuar con el llenado del formato de alta en el <strong>Portal del Empleo</strong>. En caso contrario te pedimos que acudas a un m&oacute;dulo de la CURP para regularizar tu situaci&oacute;n. Consulta las oficinas y horarios de atenci&oacute;n haciendo <a href="http://www.gob.mx/tramites/ficha/obtencion-de-la-curp/SEGOB173" target="_blank">clic aqu&iacute;</a>.</li>
				    <li>El registro de los candidatos s&oacute;lo se hace una vez y nunca se cancela, por lo que si ya estas registrado e intentas realizar otro registro el <strong>Portal del Empleo</strong> no lo permitir&aacute; por duplicidad de la CURP.</li>
				    <li>La cuenta de correo electr&oacute;nico, tiene el car&aacute;cter de nombre de usuario por lo cual se verifica su existencia en nuestra base de datos, si no cuenta con una cuenta de correo electr&oacute;nico propia le sugerimos generar una ya que es el medio por el cual recibir&aacute; ofertas laborales.</li>
				    <li>A partir de ese momento, el candidato de empleo ser&aacute; el &uacute;nico responsable de sus claves de ingreso al <strong>Portal del Empleo</strong> y el uso correcto que se le d&eacute; a las mismas para los fines que est&aacute;n especificados. Es importante que el usuario proteja sus claves, ya que si otras personas hacen un mal uso del <strong>Portal del Empleo</strong> usando la misma clave se corre el riesgo de perder acceso al sitio.</li>
				    <li>Es compromiso de los candidatos de empleo garantizar en todo lo posible la veracidad y autenticidad de sus datos personales y perfil laboral que ingresen al <strong>Portal del Empleo</strong> a trav&eacute;s del formato establecido para ello. Con fundamento en los art&iacute;culos 18 fracciones II y 20 fracciones I y VI de la Ley Federal de Transparencia y Acceso a la Informaci&oacute;n P&uacute;blica Gubernamental, se garantiza la seguridad de los datos personales y s&oacute;lo ser&aacute;n difundidos con el consentimiento de los candidatos.</li>
				    <li>Para mayor seguridad de los candidatos de empleo, &uacute;nicamente empresas que previamente se hayan registrado en el <strong>Portal del Empleo</strong>, podr&aacute;n acceder a sus datos personales y perfil laboral.</li>
				    <li>Los candidatos de empleo podr&aacute;n tener un manejo y control sobre sus datos personales una vez iniciada la sesi&oacute;n en el <strong>Portal del Empleo</strong>.</li>
				    <li>El <strong>Portal del Empleo</strong> te permite mantener confidenciales los datos de tu domicilio y tel&eacute;fono solo debes marcar la opci&oacute;n de confidencialidad correspondiente.</li>
				    <li>En caso de que los candidatos de empleo no cuenten con acceso a Internet en forma personal, podr&aacute;n acudir a las oficinas del Servicio Nacional de Empleo (SNE) de su entidad, donde podr&aacute;n hacer uso de los servicios que ofrece el <strong>Portal del Empleo</strong> sin costo alguno.</li>
				    <li>Es compromiso de todos los candidatos de empleo que hagan uso de los servicios que proporciona el <strong>Portal del Empleo</strong>, que la informaci&oacute;n que se obtenga del sitio se utilice &uacute;nicamente para los prop&oacute;sitos de b&uacute;squeda de empleo.</li>
				    <li>Si alg&uacute;n usuario del <strong>Portal del Empleo</strong> detecta que alguna de las ofertas consultadas no cumple con las presentes Pol&iacute;ticas y Condiciones de Uso, puede dar aviso a la <strong>STPS</strong> a trav&eacute;s de la opci&oacute;n de quejas que se encuentra en el Portal, proporcionando el nombre de la empresa y las ofertas de empleo que no cumplen con lo estipulado.</li>
				    <li>No es responsabilidad de la <strong>STPS</strong> cualquier conflicto laboral, personal, legal o de cualquier otra &iacute;ndole, que pudiera darse entre el candidato de empleo y las empresas que buscan personal a trav&eacute;s del <strong>Portal del Empleo</strong> y/o terceros.</li>
				    <li>La <strong>STPS</strong> no es responsable de la veracidad de la informaci&oacute;n sobre ofertas de empleo que se difunde, ni de los resultados del contacto entre los empleadores y los buscadores de trabajo. En tal sentido, la <strong>STPS</strong> no es responsable de los conflictos laborales, personales, legales y de cualquier otra &iacute;ndole que se susciten entre empleador y buscador de trabajo derivados del contacto que motive la publicaci&oacute;n de las ofertas de empleo en las bolsas de trabajo que colaboran en el Portal.</li>
				    <li>Es posible que algunas empresas publiquen simult&aacute;neamente sus ofertas de empleo tanto en el <strong>Portal del Empleo</strong> como en las otras bolsas de trabajo por Internet a las que se tienen acceso desde la opci&oacute;n &ldquo;Buscar en otras bolsas de trabajo por Internet&rdquo;. El candidato podr&aacute; postularse a la oferta de empleo de su inter&eacute;s por el canal que m&aacute;s le convenga.</li>
				    <li>El perfil laboral registrado en el <strong>Portal del Empleo</strong> tendr&aacute; una vigencia de un a&ntilde;o, posteriores a la &uacute;ltima fecha de actualizaci&oacute;n. En caso de cumplirse con este periodo de vigencia, el perfil del candidato ser&aacute; inactivado y ser&aacute; obligaci&oacute;n del candidato volver a reactivar su perfil laboral.</li>
				    <li>La <strong>STPS</strong> y el <strong>Portal del Empleo</strong> se reservan el derecho de dar de baja definitiva en cualquier momento y sin previo aviso, a los candidatos de empleo que no cumplan con las presentes Pol&iacute;ticas y Condiciones de Uso.</li>
				    <h3>De la secci&oacute;n &ldquo;Empresas&rdquo;</h3>
				    <li>Los servicios que proporciona el <strong>Portal del Empleo</strong> a las empresas del pa&iacute;s son enteramente gratuitos.</li>
				    <li>Para la utilizaci&oacute;n de los servicios que ofrece el <strong>Portal del Empleo</strong>, es necesario que las empresas se registren en el sitio.</li>
				    <li>Ser&aacute;n autorizadas todas aquellas empresas cuya informaci&oacute;n est&eacute; debidamente cumplimentada y que publiquen ofertas de trabajo debidamente sustentadas y reales y que cumplan con lo establecido en las presentes Pol&iacute;ticas y Condiciones de Uso.</li>
				    <li>Todas las empresas que se registren en el <strong>Portal del Empleo</strong> deben de proporcionar al menos un n&uacute;mero de tel&eacute;fono de contacto fijo, ya que ser&aacute; este el medio por el cual se validar&aacute; el registro de la empresa.</li>
				    <li>El <strong>Portal del Empleo</strong> requiere que al registrar su empresa se ingrese como dato obligatorio una cuenta de correo electr&oacute;nico, la cual tiene el car&aacute;cter de nombre de usuario por lo cual se verifica que sea &uacute;nica, si no cuenta con una cuenta de correo electr&oacute;nico propia le solicitamos generar una.&nbsp;<span style="font-size: 9pt; line-height: 107%; font-family: Tahoma, sans-serif; background: whitesmoke;">La contrase&ntilde;a debe ser de 8 a 12 caracteres como m&iacute;nimo y sin espacios, que pueden ser letras may&uacute;sculas o min&uacute;sculas y/o n&uacute;meros. No se admiten letras acentuadas ni caracteres especiales.</span></li>
				    <li>El <strong>Portal del Empleo</strong> requiere que se ingrese como dato obligatorio la fecha de alta constitutiva de la empresa con la &uacute;nica intenci&oacute;n de generar un identificador de empresa y garantizar la confiabilidad del registro.</li>
				    <li>Una vez concluido el registro de su empresa inmediatamente debe proceder a dar de alta una oferta de empleo, ambos registros deben pasar al proceso de validaci&oacute;n realizado por personal del centro de administraci&oacute;n del <strong>Portal del Empleo</strong>. Mientras se lleva a cabo la validaci&oacute;n puede seguir dando de alta ofertas de empleo. Una vez validada la informaci&oacute;n se le dar&aacute;n todos los permisos de uso en el <strong>Portal del Empleo</strong>.</li>
				    <li>El registro de la empresa s&oacute;lo se hace una vez y nunca se cancela, por lo que si ya est&aacute; registrado e intenta realizar otro registro el <strong>Portal del Empleo</strong> no lo permitir&aacute; por duplicidad en el identificador de la empresa.</li>
				    <li>A partir de ese momento, la empresa ser&aacute; la &uacute;nica responsable de su clave y el uso correcto que se le d&eacute; a la misma para los fines que est&aacute;n especificados. Es importante que el empleador proteja su clave, ya que si otras personas hacen mal uso del Portal usando la misma clave se corre el riesgo de perder acceso al sitio.</li>
				    <li>Para un mejor control de referencia y manejo de las ofertas de empleo, el <strong>Portal del Empleo</strong> asignar&aacute; un n&uacute;mero de folio a todas las ofertas de empleo que las empresas usuarias den de alta en el sitio.</li>
				    <li>Es obligaci&oacute;n de las empresas proporcionar en forma clara y veraz la informaci&oacute;n de las ofertas de empleo que ingresen al <strong>Portal del Empleo</strong> conforme al formato de registro correspondiente.</li>
				    <li>Las ofertas de empleo que se ofrezcan a trav&eacute;s del <strong>Portal del Empleo</strong> deben estar debidamente respaldadas por contratos legalmente v&aacute;lidos y vigentes. Est&aacute;n expresamente prohibidas en el Portal las ofertas de empleo que <strong>a)</strong> exijan invertir dinero a los candidatos; <strong>b)</strong> aquellas que correspondan a empresas &ldquo;pir&aacute;mide&rdquo; o negocios tipo multinivel y oportunidades de negocios; <strong>c)</strong> que impliquen la contrataci&oacute;n de menores sin la autorizaci&oacute;n legal; <strong>d)</strong> las que est&aacute;n relacionadas con el entretenimiento de adultos, ocio nocturno o contenidos pornogr&aacute;ficos; <strong>e)</strong> las que contraten personal solamente con pago por comisi&oacute;n o cambaceo; <strong>f)</strong> Trabajos que no paguen por lo menos el salario m&iacute;nimo diario;  <strong>g)</strong> Trabajos que requieran que el candidato, o empleado, vaya a un entrenamiento no pagado para ser contratado; <strong>h)</strong> contengan referencias obscenas, difamatorias, amenazantes, acosador, abusivas; <strong>i)</strong> Cualquier cosa que sea embarazosa u ofensiva para otra persona o entidad; <strong>j)</strong>  No debe publicar simult&aacute;neamente la misma oferta.</li>
				    <li>Asimismo, no est&aacute;n permitidas y no ser&aacute;n autorizadas en el <strong>Portal del Empleo</strong> las ofertas de empleo que pertenezcan a empresas con nombre irreal o gen&eacute;rico o que demeriten la calidad de la informaci&oacute;n que ofrece el sitio, ya sea porque est&aacute;n repetidas o porque contienen datos de contacto en los campos de funciones y actividades a realizar, observaciones, y/o conocimientos requeridos.</li>
				    <li>Tampoco est&aacute;n permitidas y no ser&aacute;n autorizadas los requerimientos de personal para servicios en casas-habitaci&oacute;n.</li>
				    <li>Las Agencias de Colocaci&oacute;n de Trabajadores y/o de Reclutamiento y Selecci&oacute;n de Recursos Humanos que hagan uso del <strong>Portal del Empleo</strong>, deber&aacute;n contar con el registro y autorizaci&oacute;n de funcionamiento otorgado por la <strong>STPS</strong>, conforme a lo estipulado en art&iacute;culo 4 del Reglamento de Agencia de Colocaci&oacute;n de Trabajadores publicado en el Diario Oficial de la Federaci&oacute;n el 3 de marzo de 2006.</li>
				    <li>En las ofertas de trabajo se debe incluir toda la informaci&oacute;n requerida de manera clara y concisa. Los oferentes de empleo que se registren en el sitio dan su cabal consentimiento para que el <strong>Portal del Empleo</strong> proporcione sus datos a los candidatos de empleo que est&eacute;n interesados en postularse para las ofertas que ofrezcan. Con fundamento en el art&iacute;culo 19 de la Ley Federal de Transparencia y Acceso a la Informaci&oacute;n P&uacute;blica Gubernamental, y en las fracciones II y III del Trig&eacute;simo Sexto de los Lineamientos Generales para la Clasificaci&oacute;n y Desclasificaci&oacute;n de la Informaci&oacute;n de las Dependencias y Entidades de la Administraci&oacute;n P&uacute;blica Federal, la informaci&oacute;n de las empresas registradas se considera clasificada como comercial reservada; salvo para los candidatos de empleo, registrados en el sitio, al momento de postular las citadas ofertas.</li>
				    <li>La <strong>STPS</strong> revisar&aacute; puntualmente la informaci&oacute;n y contenido de las ofertas de empleo que se inserten en el sitio, y en un plazo no mayor a 15 horas entre semana y 48 horas en fin de semana, publicar&aacute; en el <strong>Portal del Empleo</strong> las ofertas que cumplan con las presentes Pol&iacute;ticas y Condiciones de Uso.</li>
				    <li>Cuando la <strong>STPS</strong> identifique que se haya dado de alta una oferta de empleo no admitida o prohibida en el <strong>Portal del Empleo</strong> de acuerdo a las presentes Pol&iacute;ticas y Condiciones de Uso, proceder&aacute; a su baja en forma definitiva, reserv&aacute;ndose el derecho de no admitir la publicaci&oacute;n de m&aacute;s ofertas de empleo por parte de la empresa responsable de la misma. La <strong>STPS</strong> se reserva el derecho de dar de baja definitiva en cualquier momento y sin previo aviso, a las empresas que no cumplan con las presentes Pol&iacute;ticas y Condiciones de Uso.</li>
				    <li>No es responsabilidad de la <strong>STPS</strong> cualquier conflicto laboral, personal, legal o de otra &iacute;ndole, que pudiera darse entre el candidato de empleo y las empresas que buscan personal a trav&eacute;s del <strong>Portal del Empleo</strong> y/o terceros.</li>
				    <li>Las ofertas que se publiquen en el <strong>Portal del Empleo</strong> tendr&aacute;n una vigencia se&ntilde;alada por la empresa en el formato de alta de ofertas; cuando se cumpla con este periodo de vigencia, ser&aacute;n dadas de baja. Las empresas estar&aacute;n en posibilidades de volver a darlas de alta si as&iacute; lo requieren, o cambiar esta vigencia a trav&eacute;s de la opci&oacute;n de Espacio para Empresas.</li>
				    <li>La <strong>STPS</strong> no garantiza ni es responsable de que las empresas usuarias del <strong>Portal del Empleo</strong> encuentren en el sitio al personal necesario para cubrir sus ofertas.</li>
				    <li>Reglas para publicar una Oferta en el <strong>Portal del Empleo</strong>
				    <ul>
				        <li>El Puesto ofrecido debe ser acorde a la Ocupaci&oacute;n en la que lo est&aacute; clasificando, adem&aacute;s de ser congruente con la Escolaridad requerida y las actividades que va a realizar.</li>
				        <li>Se debe precisar con claridad los requerimientos de conocimientos y habilidades para desempe&ntilde;ar el puesto y las actividades a realizar en el mismo.</li>
				        <li>Nombre del puesto: deber&aacute; reflejar las actividades a realizar y se debe escribir de la siguiente manera:</li>
				        <li>Singular y que indique el g&eacute;nero, ejemplo: costurera, enfermera, &hellip;</li>
				        <li>Sin utilizar abreviaturas</li>
				        <li>Sin puntos al final del nombre</li>
				        <li>Usar di&eacute;resis cuando se requiera</li>
				        <li>En los puestos de ayudante general o de obrero general, especificar entre par&eacute;ntesis el &aacute;rea para la que se requiere, ejemplo: ayudante general (almac&eacute;n).</li>
				        <li>En los puestos de vendedor, especificar el tipo de mercanc&iacute;a a vender</li>
				        <li>En el caso de guardia de seguridad, no utilizar elemento de seguridad</li>
				        <li>No anotar observaciones en este campo es s&oacute;lo para el nombre del puesto</li>
				    </ul>
				    </li>
				    <h3>De la secci&oacute;n &ldquo;Servicio Nacional de Empleo&rdquo;</h3>
				    <li>Los Talleres para Buscadores de Empleo son una estrategia de informaci&oacute;n complementaria de las acciones de vinculaci&oacute;n laboral. En tal virtud, la <strong>STPS</strong> no garantiza que la participaci&oacute;n en los talleres d&eacute; como resultado la colocaci&oacute;n de los candidatos en un empleo ni su permanencia en &eacute;l.</li>
				    <li>No es responsabilidad de la <strong>STPS</strong> cualquier inconveniente provocado por la cancelaci&oacute;n, reprogramaci&oacute;n o cambio de sede de los talleres presenciales.</li>
				    <li>Las condiciones de acceso a las modalidades del Subprograma de Becas a la Capacitaci&oacute;n (B&eacute;cate), se regir&aacute;n por las <a href="https://empleo.gob.mx/work/models/empleo/Resource/1434/2/images/reglas-operacion-pae-2016.pdf" target="_blank">Reglas de Operaci&oacute;n del Programa de Apoyo al Empleo</a>.</li>
				    <li>Para ser registrado en cualesquiera de las modalidades del Subprograma de Becas a la Capacitaci&oacute;n (B&eacute;cate), los interesados deber&aacute;n acudir a la oficina del Servicio Nacional de Empleo en su Entidad y cumplir con los requisitos establecidos.</li>
				    <h3>Video curr&iacute;culum</h3>
				    <li>El <strong>Portal del Empleo</strong> revisar&aacute; el contenido de la informaci&oacute;n del video curr&iacute;culum para verificar que cumplen las condiciones de uso y de ser satisfactoria la revisi&oacute;n autorizara su publicaci&oacute;n.</li>
				    <li>Todos los datos aportados por el usuario, escritos o en video, han de ser propios y verdaderos. El usuario es responsable &uacute;nico de la informaci&oacute;n aportada y de su veracidad. Est&aacute; totalmente prohibida la suplantaci&oacute;n de personalidad de persona f&iacute;sica o jur&iacute;dica.</li>
				    <li>El usuario afirma que es titular en exclusiva de todos los derechos de propiedad intelectual del referido v&iacute;deo.</li>
				    <li>Es requisito indispensable por parte del candidato de empleo disponer de video curr&iacute;culum, alojado en un sitio de Internet especializado en videos proporcionar su URL (direcci&oacute;n web) para que el video pueda ser visto por las empresas que est&aacute;n interesadas en su perfil laboral.</li>
				    <li>La duraci&oacute;n del video curr&iacute;culum no podr&aacute; exceder de tres minutos.</li>
				    <li>El video curr&iacute;culum ha de tener unos m&iacute;nimos de calidad. En el caso de que no los cumpla, podr&aacute; ser rechazado.</li>
				    <li>En el video curr&iacute;culum no se podr&aacute; dar datos de contacto como tel&eacute;fono, direcci&oacute;n web o e-mail. A los datos de contacto solo podr&aacute;n acceder empresarios registrados en nuestra web bajo las condiciones de uso vigentes.</li>
				</ol>
			</div>
			<div>
				<div dojoType="dijit.Dialog" id="errorDialog" title="Error"
					style="display: none;" draggable="false">
					<div class="msg_contain">
						<p id="errorMsgArea"></p>
					</div>
					<p class="form_nav">
						<button class="button" onclick="dijit.byId('errorDialog').hide();">Aceptar</button>
					</p>
				</div>

				<div class="margin_top_10">
					<label for="aceptacionTerminos" class="fw_no_bold"><span>*</span>
						Acepto los términos y condiciones para el Portal del Empleo</label> &nbsp;<input
						type="checkbox" dojoType="dijit.form.CheckBox"
						id="aceptacionTerminos" name="aceptacionTerminos"
						value="${registroCandidatoForm.aceptaTerminos}" />
				</div>
			</div>
		</div>
	</div>

	<div class="form_nav">
		<c:if test="${registroCandidatoForm.edicionPerfilLaboral}">
			<input id="btnGuardar" name="btnGuardar" class="boton_azul"
				type="button" value="Actualizar mi perfil laboral"
				onclick="editar();" />
		</c:if>
		<c:if test="${!registroCandidatoForm.edicionPerfilLaboral}">
			<input id="btnGuardar" name="btnGuardar" class="boton_azul"
				type="button" value="Continuar" onclick="registrar();" />
		</c:if>
		<input type="button" value="Cancelar" onclick="showMsgCancelar()" />
	</div>


</form>

<div dojoType="dijit.Dialog" id="dialogCancelar" title="Aviso"
	draggable="false" style="display: none">
	<div class="msg_contain">
		<p>Los cambios no se guardar&aacute;n</p>
	</div>
	<p class="form_nav">
		<button class="button"
			onclick="window.open('${pageContext.request.contextPath}/logout.do', '_self');">Aceptar</button>
	</p>
</div>
