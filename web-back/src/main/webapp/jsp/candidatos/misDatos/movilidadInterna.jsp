<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="mx.gob.stps.portal.web.candidate.form.ProgramaForm, mx.gob.stps.portal.core.infra.utils.Constantes, java.util.Calendar, java.util.Date, java.text.DateFormat, java.text.SimpleDateFormat, java.text.ParseException"%>
<%
	String context = request.getContextPath() +"/";
	String vProxy = "http://orangoo.com/newnox?lang=";
	pageContext.setAttribute("vProxy",vProxy);
	String laboresFinal = null != session.getAttribute("laboresFinal") ? (String)session.getAttribute("laboresFinal") : "";
	String laboresInicial = null != session.getAttribute("laboresInicial") ? (String)session.getAttribute("laboresInicial") : "";
	String fechaVinculacion = null != session.getAttribute("fechaVinculacion") ? (String)session.getAttribute("fechaVinculacion") : "";
%>
<style>
	#fade {
	    display: none;
	    position:absolute;
	    top: 0%;
	    left: 0%;
	    width: 100%;
	    height: 100%;
	    background-color: #ababab;
	    z-index: 1001;
	    -moz-opacity: 0.8;
	    opacity: .70;
	    filter: alpha(opacity=80);
	}
	#modal {
	    display: none;
	    position: absolute;
	    top: 45%;
	    left: 45%;
	    width: 64px;
	    height: 64px;
	    padding:30px 15px 0px;
	    border: 3px solid #ababab;
	    box-shadow:1px 1px 10px #ababab;
	    border-radius:20px;
	    background-color: white;
	    z-index: 1002;
	    text-align:center;
	    overflow: auto;
	}
</style>
<script>
	function startSpin() {
		document.getElementById('modal').style.display = 'block';
	    document.getElementById('fade').style.display = 'block';
	}
	function stopSpin() {
	    document.getElementById('modal').style.display = 'none';
	    document.getElementById('fade').style.display = 'none';
	}
</script>
<script type="text/javascript" src="js/helper/messageHelper.js"></script>
<script>
	dojo.addOnLoad(function() {
		startSpin();
		updPrev();
		var vCodigoPostal = dojo.byId('codigoPostal').value;
    	entidadFederativaStore.url = "${context}domicilio.do?method=obtenerEntidad";
	    entidadFederativaStore.close();
	    loadFeds();
	    setRequiredJob();
	    stopSpin();
	});
</script>
<script type="text/javascript">
    dojo.require("dijit.dijit");
    dojo.require("dijit.form.Form");
    dojo.require("dijit.form.CheckBox");
    dojo.require("dijit.form.NumberTextBox");
    dojo.require("dijit.form.FilteringSelect");
    dojo.require("dijit.form.ValidationTextBox");
    dojo.require("dojo.data.ItemFileReadStore");
    function doSubmmitDataComp() {
		dialogMsgConfirm.hide();
		document.ProgramaForm.method.value = 'savesne';
		document.ProgramaForm.idEstadoCivil.value = dijit.byId('idEstadoCivilSelect').get('value');
		document.ProgramaForm.idExperienciaCon.value = dijit.byId('idExperienciaConSelect').get('value');
		document.ProgramaForm.idTipoEmpleo.value = dijit.byId('idTipoEmpleoSelect').get('value');
		document.ProgramaForm.idTipoContrato.value = dijit.byId('idTipoContratoSelect').get('value');
		document.ProgramaForm.idJerarquia.value = dijit.byId('idJerarquiaSelect').get('value');
		document.ProgramaForm.personasCargo.value = dijit.byId('personasCargoSelect').get('value');
		document.ProgramaForm.idLenguaIndigena.value = dijit.byId('lenguaIndigenaSelect').get('value');
		document.ProgramaForm.idBanco.value = dijit.byId('idBancoSelect').get('value');
		document.ProgramaForm.idLocalidad.value = dijit.byId('idLocalidadSelect').get('value');
		var dayiniAdd=document.getElementById('laboresInicialDia').value;
		var monthiniAdd=dijit.byId('laboresInicialMes').get('value');
		var yeariniAdd=document.getElementById('laboresInicialAnual').value;
		var laboresInicial = dayiniAdd + '/' + monthiniAdd + '/' + yeariniAdd
		if (dayiniAdd != 0)
			document.ProgramaForm.laboresInicial.value = laboresInicial;	
		var dayfinAdd=document.getElementById('laboresFinalDia').value;
		var monthfinAdd=dijit.byId('laboresFinalMes').get('value');
		var yearfinAdd=document.getElementById('laboresFinalAnual').value;
		var laboresFinal = dayfinAdd + '/' + monthfinAdd + '/' + yearfinAdd
		if (dayfinAdd != 0)
			document.ProgramaForm.laboresFinal.value = laboresFinal;
		var dayVinc=document.getElementById('fechaVinculacionDia').value;
		var monthVinc=dijit.byId('fechaVinculacionMes').get('value');
		var yearVinc=document.getElementById('fechaVinculacionAnual').value;
		var fechaVinculacion = dayVinc + '/' + monthVinc + '/' + yearVinc
		if (dayVinc != 0)
			document.ProgramaForm.fechaVinculacion.value = fechaVinculacion;
		dojo.xhrGet({
			url: 'perfilComplementario.do',
			form:'ProgramaForm',
			sync: true,
			timeout:180000,
			load: function(data) {
				var res = dojo.fromJson(data);
				if (res.type=='ext') {
					messagePath(res.message,'<%=context%>miEspacioCandidato.do?method=init');
				}else {
					message(res.message);
				}
			}
		}); 
	}
    function messagePath(msg,path){
		var html =
			'<div id="messageDialgop2" name="messageDialgop2">' +
		'<p style="text-align: center;">'+ msg +'</p>'+
		'<p class="form_nav">'
		+ cerrarMsgRuta(path, "Aceptar")
	    + '</p>'
	    +'</div>';
	
		dialogMsg = new dijit.Dialog({
	        title: 'Mensaje',
	        content: html,
	        style: "width:300px;",
	        showTitle: false, draggable : false, closable : false,
	    });			
		dialogMsg.show();
	}
    function messageConfirm(msg) {
		var html =
			'<div id="messageDialgopC" name="messageDialgopC">' +
			'<p style="text-align: center;">'+ msg +'</p>'+
			'<p class="form_nav">' + 
			'<input id="btnRenv" class="boton_naranja" type="button" name="btnRenv" onClick="doSubmmitDataComp()" value="Aceptar"/>' +
			'<input id="btnCanc" class="boton_naranja" type="button" name="btnCanc" onClick="dialogMsgConfirm.hide();" value="Cancelar"/>' +
    		'</p>' +
    		'</div>';
		dialogMsgConfirm = new dijit.Dialog({
	        title: 'Mensaje',
	        content: html,
	        style: "width:300px;",
	        showTitle: false, draggable : false, closable : false,
	    });	
		dialogMsgConfirm.show();
	}
	function doSubmitCancel() {
		messageRutaCancel('Los datos no guardados se perderán ¿Continuar?','<c:url value="/miEspacioCandidato.do"/>');
	}
	function validateForm() {
		var estadoCivil = dijit.byId('idEstadoCivilSelect');
		var nombreBeneficiario = dijit.byId('nombre_beneficiario');
		if (estadoCivil.get('value')==7) {
			estadoCivil.focus();
			message('Seleccione estado civil.');
			return false;
		}
		if (!validateDisc()) return false;
		if (!validateExtAdr()) return false;
		if (!validateExpec()) return false;
		if (!validateLastJob()) return false;
		if (!validateOtherStudiesList()) return false;
		if (!validateInfComp()) return false;
		if (nombreBeneficiario.getValue('value')!='') {
			if (!validateBenef() || !validateExtAdrBef()) return false;
		}
		messageConfirm('¿Está seguro de guardar datos complementarios?');
	}
	function validateDisc() {
		var none = dijit.byId('discapacidadNinguna').getValue('checked');
		var motora = dijit.byId('discapacidadMotora').getValue('checked');
		var visual = dijit.byId('discapacidadVisual').getValue('checked');
		var auditiva = dijit.byId('discapacidadAuditiva').getValue('checked');
		var intelectual = dijit.byId('discapacidadIntelectual').getValue('checked');
		var mental = dijit.byId('discapacidadMental').getValue('checked');
		var isValidDisc = (none || motora || visual || auditiva || intelectual || mental);
		if (!isValidDisc) {
			dijit.byId('discapacidadNinguna').focus();
			message('Seleccione discapacidad.');
			return false;
		}
		return true;
	}
	function unchekedAll() {
		var motora = dijit.byId('discapacidadMotora');
		var visual = dijit.byId('discapacidadVisual');
		var auditiva = dijit.byId('discapacidadAuditiva');
		var intelectual = dijit.byId('discapacidadIntelectual');
		var mental = dijit.byId('discapacidadMental');
		if (motora.getValue('checked')) motora.set("checked", false);
		if (visual.getValue('checked')) visual.set("checked", false);
		if (auditiva.getValue('checked')) auditiva.set("checked", false);
		if (intelectual.getValue('checked')) intelectual.set("checked", false);
		if (mental.getValue('checked')) mental.set("checked", false);
	}
	function unchekedNone() {
		var none = dijit.byId('discapacidadNinguna');
		if (none.getValue('checked')) none.set("checked", false);
	}
	function validateExtAdr() {
		var entreCalle = dijit.byId('entreCalle');
		var yCalle = dijit.byId('yCalle');
		var idLocalidadSelect = dijit.byId('idLocalidadSelect');
		var referenciaDomicilio = document.getElementById('referenciaDomicilio');
		if (entreCalle.get('value')=='') {
			entreCalle.focus();
			message('Entre calle es requerido.');
			return false;
		}
		if (yCalle.get('value')=='') {
			yCalle.focus();
			message('Y calle es requerido.');
			return false;
		}
		if (idLocalidadSelect.get('value')==0) {
			idLocalidadSelect.focus();
			message('Seleccione localidad.');
			return false;
		}
		if (referenciaDomicilio.value=='') {
			referenciaDomicilio.focus();
			message('Referencia para llegar al domicilio es requerido.');
			return false;
		}
		return true;
	}
	function validateExpec() {
		var idTipoEmpleoSelect = dijit.byId('idTipoEmpleoSelect');
		var idTipoContratoSelect = dijit.byId('idTipoContratoSelect');
		var recibeOferta = document.ProgramaForm.recibeOferta[0].checked;
		var disponibilidadRadicarPais = getRadioValue('disponibilidadRadicarPais');
		if (false == recibeOferta) {
			recibeOferta = document.ProgramaForm.recibeOferta[1].checked;
			if (false == recibeOferta) {
				document.ProgramaForm.recibeOferta[0].focus();
				message('Seleccione si desea recibir ofertas de empleo.');
				return false;
			}
		}
		if (idTipoEmpleoSelect.get('value')==0) {
			idTipoEmpleoSelect.focus();
			message('Seleccione tipo de empleo.');
			return false;
		}
		if (idTipoContratoSelect.get('value')==0) {
			idTipoContratoSelect.focus();
			message('Seleccione tipo de contrato.');
			return false;
		}
		if (null == disponibilidadRadicarPais) {
			document.ProgramaForm.disponibilidadRadicarPais[0].focus();
			message('Seleccione si puede radicar en otro país.');
			return false;
		}
		return true;
	}
	function validateLastJob() {
		var required = false;
		var empresa = dijit.byId('empresa');
		var puesto = dijit.byId('puesto');
		var idJerarquiaSelect = dijit.byId('idJerarquiaSelect');
		var personasCargoSelect = dijit.byId('personasCargoSelect');
		var salarioMensual = dijit.byId('salarioMensual');
		var laboresInicialDia = dijit.byId('laboresInicialDia');
		var laboresInicialMes = dijit.byId('laboresInicialMes');
		var laboresInicialAnual = dijit.byId('laboresInicialAnual');
		var laboresFinalDia = dijit.byId('laboresFinalDia');
		var laboresFinalMes = dijit.byId('laboresFinalMes');
		var laboresFinalAnual = dijit.byId('laboresFinalAnual');
		var funcion = document.getElementById('funcion');
		if (empresa.get('value')!='') {
			required = true;
		}
		if (required) {
			if (puesto.get('value')=='') {
				puesto.focus();
				message('Puesto desempeñado es requerido.');
				return false;
			}
			if (idJerarquiaSelect.get('value')==0) {
				idJerarquiaSelect.focus();
				message('Seleccione jerarquía del puesto.');
				return false;
			}
			if (personasCargoSelect.get('value')==0) {
				personasCargoSelect.focus();
				message('Seleccione número de personas a cargo.');
				return false;
			}
			if (salarioMensual.get('value')=='') {
				salarioMensual.focus();
				message('Salario mensual recibido es requerido.');
				return false;
			}
			if (laboresInicialDia.get('value')==0) {
				laboresInicialDia.focus();
				message('Seleccione dia fecha de ingreso.');
				return false;
			}
			if (laboresInicialMes.get('value')==0) {
				laboresInicialMes.focus();
				message('Seleccione mes fecha de ingreso.');
				return false;
			}
			if (laboresInicialAnual.get('value')==0) {
				laboresInicialAnual.focus();
				message('Seleccione año fecha de ingreso.');
				return false;
			}
			if (laboresFinalDia.get('value')==0) {
				laboresFinalDia.focus();
				message('Seleccione dia fecha de terminación.');
				return false;
			}
			if (laboresFinalMes.get('value')==0) {
				laboresFinalMes.focus();
				message('Seleccione mes fecha de terminación.');
				return false;
			}
			if (laboresFinalAnual.get('value')==0) {
				laboresFinalAnual.focus();
				message('Seleccione año fecha de terminación.');
				return false;
			}
			if (!validateDatesLastJob()) {
	    		return false;
	    	}
			if (funcion.value=='') {
				funcion.focus();
				message('Funciones desempeñadas es requerido.');
				return false;
			}
		}
		return true;
	}
	function validateInfComp() {
		var cubrirVacante = getRadioValue('cubrirVacante');
		var colocadoVacante = getRadioValue('colocadoVacante');
		var lenguaIndigena = getRadioValue('lenguaIndigena');
		var lenguaIndigenaSelect = dijit.byId('lenguaIndigenaSelect');
		var requiereCapacitacion = getRadioValue('requiereCapacitacion');
		var necesitaCapacitacion = dijit.byId('necesitaCapacitacion');
		var tiempoCapacitacion = getRadioValue('tiempoCapacitacion');
		var negocioPropio = getRadioValue('negocioPropio');
		var pretendeNegocioPropio = getRadioValue('pretendeNegocioPropio');
		var beneficiarioApoyo = getRadioValue('beneficiarioApoyo');
		var ayudaProspera = getRadioValue('ayudaProspera');
		var folioProspera = dijit.byId('folioProspera');
		var clabe = dijit.byId('clabe');
		var idBancoSelect = dijit.byId('idBancoSelect');
		if (null == ayudaProspera) {
			document.ProgramaForm.ayudaProspera[0].focus();
			message('Seleccione si realizó estudios con apoyo de prospera.');
			return false;
		}
		if (ayudaProspera == 2 && folioProspera.get('value')=='') {
			folioProspera.focus();
			message('No. de Folio Familia PROSPERA es requerido.');
			return false;
		}
		if (ayudaProspera == 2 && folioProspera.get('value').length < 9) {
			folioProspera.focus();
			message('No. de Folio Familia PROSPERA debe contener 9 dígitos');
			return false;
		}
		if (null == cubrirVacante) {
			document.ProgramaForm.cubrirVacante[0].focus();
			message('Seleccione si el SNE lo envíó a cubrir alguna oferta de empleo.');
			return false;
		}
		if (cubrirVacante == 2 && null == colocadoVacante) {
			document.ProgramaForm.colocadoVacante[0].focus();
			message('Seleccione si se colocó en la vacante que lo enviaron.');
			return false;
		}
		if (null == lenguaIndigena) {
			document.ProgramaForm.lenguaIndigena[0].focus();
			message('Seleccione si habla alguna lengua indígena.');
			return false;
		}
		if (lenguaIndigena == 2 && lenguaIndigenaSelect.get('value')==0) {
			lenguaIndigenaSelect.focus();
			message('Seleccione lengua indígena.');
			return false;
		}
		if (null == requiereCapacitacion) {
			document.ProgramaForm.requiereCapacitacion[0].focus();
			message('Seleccione si requiere capacitación.');
			return false;
		}
		if (requiereCapacitacion == 2 && necesitaCapacitacion.get('value')=='') {
			necesitaCapacitacion.focus();
			message('En que considera que necesita capacitación es requerido.');
			return false;
		}
		if (requiereCapacitacion == 2 && null == tiempoCapacitacion) {
			document.ProgramaForm.tiempoCapacitacion[0].focus();
			message('Seleccione si cuenta con tiempo mínimo de seis horas diarias para capacitación.');
			return false;
		}
		if (null == negocioPropio) {
			document.ProgramaForm.negocioPropio[0].focus();
			message('Seleccione si ya tiene un negocio por cuenta propia.');
			return false;
		}
		if (null == pretendeNegocioPropio) {
			document.ProgramaForm.pretendeNegocioPropio[0].focus();
			message('Seleccione si pretende poner un negocio por cuenta propia.');
			return false;
		}
		if (null == beneficiarioApoyo) {
			document.ProgramaForm.beneficiarioApoyo[0].focus();
			message('Seleccione si ha sido beneficiario del programa de aapoyo al empleo.');
			return false;
		}
		if (clabe.get('value')!='' && clabe.get('value').length < 18) {
			clabe.focus();
			message('La CLAVE interbancaria debe contener 18 dígitos.');
			return false;
		}
		if (clabe.get('value')!='' && idBancoSelect.get('value')==0) {
			idBancoSelect.focus();
			message('Selecccione banco.');
			return false;
		}
		return true;
	}
	function validateKnowledge() {
		var conocimiento = dijit.byId('conocimiento');
		var idExperienciaConSelect = dijit.byId('idExperienciaConSelect');
		var descripcionCon = document.getElementById('descripcionCon');
		if (conocimiento.get('value')=='') {
			conocimiento.focus();
			message('Conocimiento es requerido.');
			return false;
		}
		if (idExperienciaConSelect.get('value')==0) {
			idExperienciaConSelect.focus();
			message('Seleccione experiencia.');
			return false;
		}
		if (descripcionCon.value=='') {
			descripcionCon.focus();
			message('Descripción es requerido.');
			return false;
		}
		return true;
	}
	function validateDatesLastJob() {
		var laboresInicialDia = dijit.byId('laboresInicialDia').get('value');
		var laboresInicialMes = dijit.byId('laboresInicialMes').get('value');
		var laboresInicialAnual = dijit.byId('laboresInicialAnual').get('value');
	 	var laboresInicial = new Date(laboresInicialAnual, laboresInicialMes, laboresInicialDia);
	 	var laboresFinalDia = dijit.byId('laboresFinalDia').get('value');
	 	var laboresFinalMes = dijit.byId('laboresFinalMes').get('value');
	 	var laboresFinalAnual = dijit.byId('laboresFinalAnual').get('value');
	 	var laboresFinal = new Date(laboresFinalAnual, laboresFinalMes, laboresFinalDia);
	 	var hoy = new Date();
	 	var fhBuscaEmp = new Date();
	 	var fhEstFin = new Date();
	 	var monthiniTmp = laboresInicialMes;
	  	var monthfinTmp = laboresFinalMes;
		fhBuscaEmp.setFullYear(laboresInicialAnual,--monthiniTmp,laboresInicialDia);
		fhEstFin.setFullYear(laboresFinalAnual,--monthfinTmp,laboresFinalDia);
	 	var dif = dojo.date.compare(laboresFinal, laboresInicial, 'date');
	 	var valido = dif >= 0;
	 	if (!validateDay(laboresInicialDia, laboresInicialMes, laboresInicialAnual, dijit.byId('laboresInicialDia'))) return false;
	 	if (!validateDay(laboresFinalDia, laboresFinalMes, laboresFinalAnual, dijit.byId('laboresFinalDia'))) return false;
	 	if (fhBuscaEmp > hoy) {
	 		dijit.byId('laboresInicialDia').focus();
	 		message('La fecha de ingreso de trabajo actual o último no puede ser mayor al dia de hoy.');
	 	 	return false;
	 	}
	 	if (fhEstFin > hoy) {
	 		dijit.byId('laboresFinalDia').focus();
	 		message('La fecha de terminación de trabajo actual o último no puede ser mayor al dia de hoy.');
	 	 	return false;
	 	}
	 	if (!valido) {
	 		dijit.byId('laboresFinalDia').focus();
	 		message('La fecha de ingreso de trabajo actual o último no puede ser mayor que la fecha de terminación');	
	 		return false;
	 	}
	 	return true;
	}
	function validateDay(day, month, year, widget) {
		if ((month==4) || (month==6) || (month==9) || (month==11)) {
			if (day > 30) {
				widget.focus();
			    message('El día seleccionado no puede ser mayor a 30');
			    return false;
			}
		}else {
			if (month==2) {
				if ((year % 4 == 0) && ((year % 100 != 0) || (year % 400 == 0))) {
					if (day > 29) {
						widget.focus();
					    message('El día seleccionado no puede ser mayor a 29');
					    return false;
					}
				}else {
					if (day > 28) {
						widget.focus();
						message('El día seleccionado no puede ser mayor a 28');
						return false;
					}
				}
			}
		}
		return true;
	}
	function updPrev() {
		updLastRec();
		updCapac();
		updOwnBis();
		updResBis();
		updBenefPr();
		updLenguaInd();
		updFolioProsp();
	}
	function updLastRec() {
		var cubrirVacante = getRadioValue('cubrirVacante');
		if (null != cubrirVacante && cubrirVacante == 2) {
			document.getElementById('lastRecruit').style.display = "block";
		}
		if (null != cubrirVacante && cubrirVacante == 1) {
			document.getElementById('lastRecruit').style.display = "none";
		}
	}
	function updLenguaInd() {
		var lenguaIndigena = getRadioValue('lenguaIndigena');
		if (null != lenguaIndigena && lenguaIndigena == 2) {
			document.getElementById('lenguaIndigenaDiv').style.display = "block";
		}
		if (null != lenguaIndigena && lenguaIndigena == 1) {
			document.getElementById('lenguaIndigenaDiv').style.display = "none";
		}
	}
	function updCapac() {
		var requiereCapacitacion = getRadioValue('requiereCapacitacion');
		if (null != requiereCapacitacion && requiereCapacitacion == 2) {
			document.getElementById('requiereCapacitacionDiv').style.display = "block";
		}
		if (null != requiereCapacitacion && requiereCapacitacion == 1) {
			document.getElementById('requiereCapacitacionDiv').style.display = "none";
		}
	}
	function updOwnBis() {
		var negocioPropio = getRadioValue('negocioPropio');
		if (null != negocioPropio && negocioPropio == 2) {
			document.getElementById('tipoNegocioDiv').style.display = "block";
		}
		if (null != negocioPropio && negocioPropio == 1) {
			document.getElementById('tipoNegocioDiv').style.display = "none";
		}
	}
	function updResBis() {
		var pretendeNegocioPropio = getRadioValue('pretendeNegocioPropio');
		if (null != pretendeNegocioPropio && pretendeNegocioPropio == 2) {
			document.getElementById('recursosNegocioDiv').style.display = "block";
		}
		if (null != pretendeNegocioPropio && pretendeNegocioPropio == 1) {
			document.getElementById('recursosNegocioDiv').style.display = "none";
		}
	}
	function updBenefPr() {
		var beneficiarioApoyo = getRadioValue('beneficiarioApoyo');
		if (null != beneficiarioApoyo && beneficiarioApoyo == 2) {
			document.getElementById('programaApoyoDiv').style.display = "block";
		}
		if (null != beneficiarioApoyo && beneficiarioApoyo == 1) {
			document.getElementById('programaApoyoDiv').style.display = "none";
		}
	}
	function updFolioProsp() {
		var ayudaProspera = getRadioValue('ayudaProspera');
		if (null != ayudaProspera && ayudaProspera == 2) {
			document.getElementById('folioProsperaDiv').style.display = "block";
		}
		if (null != ayudaProspera && ayudaProspera == 1) {
			document.getElementById('folioProsperaDiv').style.display = "none";
		}
	}
	function changePhoneSizeRequired() {
		var vClave = document.getElementById('clave');
		var vTelefono = document.getElementById('telefono');
		if(vClave.value.length < 2){
			vClave.focus();
			message('Debe proporcionar una clave LADA de 2 ó 3 caracteres');
			return false;
		} else if(vClave.value.length == 2) {
			if(vTelefono.value.length != 8){
				vClave.focus();
				message('Debe proporcionar un teléfono de 8 dígitos');
				return false;
			}
		} else if(vClave.value.length==3){
			if(vTelefono.value.length!=7){
				vClave.focus();
				message('Debe proporcionar un teléfono de 7 dígitos');
				return false;
			}	
		}
		return true;
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
			municipioStore.url = "${context}domicilio.do?method=obtenerMunicipio" + "&" + "idEntidad="+ vEntidad;
			municipioStore.close();			
		}
	}
	function actulizaColonia() {
		var vEntidad   = dijit.byId('idEntidadSelect').get('value');
		var vMunicipio = dijit.byId('idMunicipioSelect').get('value');
		if (vEntidad && vMunicipio){
			dojo.byId('codigoPostal').value = '';
			dijit.byId('idColoniaSelect').reset();
			coloniaStore.url = "${context}domicilio.do?method=obtenerColonia" + "&" + "idEntidad="+ vEntidad + "&" + "idMunicipio="+ vMunicipio;
			coloniaStore.close();
		}
	}
	function actulizaCodigoPostal() {
		var vEntidad   = dijit.byId('idEntidadSelect').get('value');
		var vMunicipio = dijit.byId('idMunicipioSelect').get('value');
		var vColonia = dijit.byId('idColoniaSelect').get('value');;
		if (vEntidad && vMunicipio && vColonia){
	    	var url = '${context}domicilio.do?method=obtenerCodigoPostal&idEntidad='+ vEntidad + '&idMunicipio='+ vMunicipio + '&idColonia='+ vColonia;
			dojo.xhrGet({url: url, sync: true, timeout:180000,
		       load: function(data){
					       var res = dojo.fromJson(data);
						   dojo.byId('codigoPostal').value = res.codigoPostal;
					    }
				});
		} else {
			dojo.byId('codigoPostal').value='';
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
	function loadFeds() {
		var vEntidad = '${ProgramaForm.perfil.idEntidad}';
		var vMunicipio = '${ProgramaForm.perfil.idMunicipio}';
		var vLocalidad = '${ProgramaForm.perfil.idLocalidad}';
		entidadFederativaStore.fetch({
    		sync: true,
          	onComplete: function(items, request) {
	          	if (items.length == 0) return;
	          	dijit.byId('idEntidadSelect').attr('value', '${ProgramaForm.perfil.idEntidad}');
	    		municipioStore.url = "${context}domicilio.do?method=obtenerMunicipio" + "&" + "idEntidad="+ vEntidad;
	    		municipioStore.close();
	    		municipioStore.fetch({
		    		sync: true,
		         	onComplete: function(items, request) {
		          		if (items.length == 0) return;
		          		dijit.byId('idMunicipioSelect').attr('value', '${ProgramaForm.perfil.idMunicipio}');
		    			coloniaStore.url = "${context}domicilio.do?method=obtenerColonia" + "&" + "idEntidad="+ vEntidad + "&" + "idMunicipio="+ vMunicipio;
		    			coloniaStore.close();
		    			coloniaStore.fetch({
		    				sync: true,
		          			onComplete: function(items, request) {
		          				if (items.length == 0) return;                   	
		          				dijit.byId('idColoniaSelect').attr('value', '${ProgramaForm.perfil.idColonia}');
		          			}
		    			});
		    			localidadStore.url = "${context}domicilio.do?method=obtenerLocalidades" + "&" + "idEntidad="+ vEntidad + "&" + "idMunicipio="+ vMunicipio;
		    		    localidadStore.close();
		    		    localidadStore.fetch({
		    		    	sync: true,
		    		        onComplete: function(items, request) {
		    		        	if (items.length == 0) return;                   	
		    		          	dijit.byId('idLocalidadSelect').attr('value', '${ProgramaForm.perfil.idLocalidad}');
		    		        }
		    		    });
		          	}
	    		});
          	}
    	});
	}
	function setRequiredJob() {
		if (dijit.byId('empresa').get('value')!='') {
    		document.getElementById("spanEmpresa").style.display="none";
    		document.getElementById("spanEmpresaReq").style.display="block";
    		dijit.byId('empresa').attr('required', true);
    		document.getElementById("spanPuesto").style.display="none";
    		document.getElementById("spanPuestoReq").style.display="block";
    		dijit.byId('puesto').attr('required', true);
    		document.getElementById("spanJerarquia").style.display="none";
    		document.getElementById("spanJerarquiaReq").style.display="block";
    		dijit.byId('idJerarquiaSelect').attr('required', true);
    		document.getElementById("spanPersonas").style.display="none";
    		document.getElementById("spanPersonasReq").style.display="block";
    		dijit.byId('personasCargo').attr('required', true);
    		document.getElementById("spanSalario").style.display="none";
    		document.getElementById("spanSalarioReq").style.display="block";
    		dijit.byId('salarioMensual').attr('required', true);
    		document.getElementById("spanIngreso").style.display="none";
    		document.getElementById("spanIngresoReq").style.display="block";
    		document.getElementById("spanIngreso").style.display="none";
    		document.getElementById("spanIngresoReq").style.display="block";
    		document.getElementById("spanTerminacion").style.display="none";
    		document.getElementById("spanTerminacionReq").style.display="block";
    		document.getElementById("spanFuncion").style.display="none";
    		document.getElementById("spanFuncionReq").style.display="block";
    	}
	}
	function validateOtherStudiesList() {
		var idCandidateStudie1 = dijit.byId('studieAdd1');
	 	var idCandidateStudie2 = dijit.byId('studieAdd2');
	 	var idCandidateStudie3 = dijit.byId('studieAdd3');
	 	var idCandidateStudie4 = dijit.byId('studieAdd4');
	 	var idCandidateStudie5 = dijit.byId('studieAdd5');
	 	var idCandidateStudie6 = dijit.byId('studieAdd6');
	 	var idCandidateStudie7 = dijit.byId('studieAdd7');
	 	var idCandidateStudie8 = dijit.byId('studieAdd8');
	 	var idCandidateStudie9 = dijit.byId('studieAdd9');
	 	var idCandidateStudie10 = dijit.byId('studieAdd10');
		  if (idCandidateStudie1 != undefined) {
		   if (idCandidateStudie2 != undefined) {
		    if (idCandidateStudie3 != undefined) {
		     if (idCandidateStudie4 != undefined) {
		      if (idCandidateStudie5 != undefined) {
		       if (idCandidateStudie6 != undefined) {
		        if (idCandidateStudie7 != undefined) {
		         if (idCandidateStudie8 != undefined) {
		          if (idCandidateStudie9 != undefined) {
		           if (idCandidateStudie10 != undefined)
		            return (validateOtherStudie(1) && validateOtherStudie(2) && validateOtherStudie(3) && validateOtherStudie(4) && validateOtherStudie(5) && validateOtherStudie(6) && validateOtherStudie(7) && validateOtherStudie(8) && validateOtherStudie(9) && validateOtherStudie(10));
		           else
		            return (validateOtherStudie(1) && validateOtherStudie(2) && validateOtherStudie(3) && validateOtherStudie(4) && validateOtherStudie(5) && validateOtherStudie(6) && validateOtherStudie(7) && validateOtherStudie(8) && validateOtherStudie(9));
		          }else
		           return (validateOtherStudie(1) && validateOtherStudie(2) && validateOtherStudie(3) && validateOtherStudie(4) && validateOtherStudie(5) && validateOtherStudie(6) && validateOtherStudie(7) && validateOtherStudie(8));
		         }else
		          return (validateOtherStudie(1) && validateOtherStudie(2) && validateOtherStudie(3) && validateOtherStudie(4) && validateOtherStudie(5) && validateOtherStudie(6) && validateOtherStudie(7));
		        }else
		         return (validateOtherStudie(1) && validateOtherStudie(2) && validateOtherStudie(3) && validateOtherStudie(4) && validateOtherStudie(5) && validateOtherStudie(6));
		       }else
		        return (validateOtherStudie(1) && validateOtherStudie(2) && validateOtherStudie(3) && validateOtherStudie(4) && validateOtherStudie(5));
		      }else
		       return (validateOtherStudie(1) && validateOtherStudie(2) && validateOtherStudie(3) && validateOtherStudie(4));
		     }else
		      return (validateOtherStudie(1) && validateOtherStudie(2) && validateOtherStudie(3));
		    }else
		     return (validateOtherStudie(1) && validateOtherStudie(2));
		   }else
		    return validateOtherStudie(1);
		  }
		return true;
	}
	function validateOtherStudie(index) {
	 	var studietypeadd = dijit.byId('studieTypeAdd' + index);
	  	var studieadd = dijit.byId('studieAdd' + index);
	  	var institutionadd = dijit.byId('institutionAdd' + index);
	  	var dayiniadd = dijit.byId('dayIniAdd' + index);
	  	var monthiniadd = dijit.byId('monthIniAdd' + index);
	  	var yeariniadd = dijit.byId('yearIniAdd' + index);
	  	var dayfinadd = dijit.byId('dayFinAdd' + index);
	  	var monthfinadd = dijit.byId('monthFinAdd' + index);
	  	var yearfinadd = dijit.byId('yearFinAdd' + index);
	  	var statusacademic = dijit.byId('statusAcademicAdd' + index);
	  	if (studietypeadd.value == 0) {
	  		studietypeadd.focus();
	    	message('Debe seleccionar otro estudio.');
	   		return false;
	    }
	    if (!studieadd.value) {
	    	studieadd.focus();
	   		message('Es necesario indicar el nombre del estudio.');
	   		return false;
	  	}
	  	if (!institutionadd.value) {
	  		institutionadd.focus();
	   		message('Es necesario indicar la institución del estudio.');
	   		return false;
	  	}
	  	if (statusacademic.value == 0) {
	    	statusacademic.focus();
	    	message('Debe seleccionar situación académica del estudio.');
	   		return false;
	    }
	  	if (dayiniadd.value == 0) {
	  		dayiniadd.focus();
	      	message('Debe seleccionar día de fecha de inicio del estudio.');
	   		return false;
	    }
	    if (monthiniadd.value == 0) {
	    	monthiniadd.focus();
	      	message('Debe seleccionar mes de fecha de inicio del estudio.');
	   		return false;
	    }
	    if (yeariniadd.value == 0) {
	    	yeariniadd.focus();
	    	message('Debe seleccionar año de fecha de inicio del estudio.');
	   		return false;
	    }
	    if (dayfinadd.value == 0) {
	    	dayfinadd.focus();
	    	message('Debe seleccionar día de fecha de fin del estudio.');
	   		return false;
	    }
	    if (monthfinadd.value == 0) {
	    	monthfinadd.focus();
	      	message('Debe seleccionar mes de fecha de fin del estudio.');
	   		return false;
	    }
	   	if (yearfinadd.value == 0) {
	   		yearfinadd.focus();
	    	message('Debe seleccionar año de fecha de fin del estudio.');
	  	 	return false;
	    }
	    if (!validateDatesList(dayiniadd, monthiniadd, yeariniadd, dayfinadd, monthfinadd, yearfinadd)) {
	    	return false;
	    }
	 	return true;
	}
	function validateDatesList(dayiniAdd, monthiniAdd, yeariniAdd, dayfinAdd, monthfinAdd, yearfinAdd) {
	 	var inicialdate = new Date(yeariniAdd.value, monthiniAdd.value, dayiniAdd.value);
	  	var finaldate = new Date(yearfinAdd.value, monthfinAdd.value, dayfinAdd.value);
	  	var hoy = new Date();
	  	var fhBuscaEmp = new Date();
	  	var fhEstFin = new Date();
	  	var monthiniTmp = monthiniAdd.value;
	  	var monthfinTmp = monthfinAdd.value;
	  	fhBuscaEmp.setFullYear(yeariniAdd.value,--monthiniTmp,dayiniAdd.value);
	  	fhEstFin.setFullYear(yearfinAdd.value,--monthfinTmp,dayfinAdd.value);
	  	var dif = dojo.date.compare(finaldate, inicialdate, 'date');
	  	var valido = dif >= 0;
	  	if (!validateDay(dayiniAdd.value, monthiniAdd.value, yeariniAdd.value, dayiniAdd)) return false;
	  	if (!validateDay(dayfinAdd.value, monthfinAdd.value, yearfinAdd.value, dayfinAdd)) return false;
	  	if (fhBuscaEmp > hoy) {
	  		dayiniAdd.focus();
	   		message('La fecha de inicio de otros estudios no puede ser mayor al dia de hoy.');
	   		return false;
	  	}
	  	if (fhEstFin > hoy) {
	  		dayfinAdd.focus();
	   		message('La fecha de fin de otros estudios no puede ser mayor al dia de hoy.');
	   		return false;
	  	}
	  	if (!valido) {
	  		dayfinAdd.focus();
	   		message('La fecha de fin no puede ser menor a la fecha de inicio.'); 
	   		return false;
	  	}
	  	return true;
	}
	function caracteresValidos(e) {
		var tecla_codigo = (document.all) ? e.keyCode : e.which;
	    if (tecla_codigo==8 || tecla_codigo==0)
	    	return true;
	    if (tecla_codigo==225 || tecla_codigo==233 || tecla_codigo==237 || tecla_codigo==243 || tecla_codigo==250) 
	    	return true; //vocales minusculas con acento
	    if (tecla_codigo==193 || tecla_codigo==201 || tecla_codigo==205 || tecla_codigo==211 || tecla_codigo==218) 
	    	return true; //vocales mayuzculas con acento
	    if (tecla_codigo==209 || tecla_codigo==241 ) 
	    	return true;
	    var patron =/[0-9\-a-zA-Z_ .,:;#]/;
	   	tecla_valor = String.fromCharCode(tecla_codigo);
	   	return patron.test(tecla_valor);
	}  	
	function maximaLongitud(texto, maxlong) {
		var tecla, int_value, out_value;
		if (texto.value.length > maxlong) {
		  	in_value = texto.value.replace(/^\s+|\s*[\r\n][\r\n \t]*/g, "");
		  	out_value = in_value.substring(0, maxlong);
		  	texto.value = out_value;
		  	return false;
		}
		return true;
	}
	function trimSpaces(textarea) {
		s = textarea.value;
		s = s.replace(/(^\s*)|(\s*$)/gi,"");
		s = s.replace(/[ ]{2,}/gi," ");
		s = s.replace(/\n /,"\n");
		textarea.value = s;
	}
</script>
<noscript>Funciones de JavaScript desactivadas por navegador</noscript>
<div id="fade" style="display: none;"></div>
<div id="modal" style="display: none;"><img id="loader" src="<%=request.getContextPath()%>/images/ajax-loader.gif" /></div>
<div dojoType="dojo.data.ItemFileReadStore" jsId="entidadFederativaStore" urlPreventCache="true" clearOnClose="true"></div>
<div dojoType="dojo.data.ItemFileReadStore" jsId="municipioStore" urlPreventCache="true" clearOnClose="true"></div>
<div dojoType="dojo.data.ItemFileReadStore" jsId="coloniaStore" urlPreventCache="true" clearOnClose="true"></div>
<div dojoType="dojo.data.ItemFileReadStore" jsId="localidadStore" urlPreventCache="true" clearOnClose="true"></div>
<div class="formApp miEspacio">
	<form name="ProgramaForm" id="ProgramaForm" method="post" action="perfilComplementario.do" dojoType="dijit.form.Form">
		<input type="hidden" name="method" id="method" value="" />
		<input type="hidden" name="laboresInicial" id="laboresInicial" value="" dojoType="dijit.form.TextBox" />
		<input type="hidden" name="laboresFinal" id="laboresFinal" value="" dojoType="dijit.form.TextBox" />
		<input type="hidden" name="fechaVinculacion" id="fechaVinculacion" value="" dojoType="dijit.form.TextBox" />
		<h2>Mi espacio</h2>
		<div class="tab_block">
			<div align="left" id="returnME" style="display: block;">
				<a
					href="javascript:location.replace('<c:url value="/miEspacioCandidato.do"/>');"
					class="expand"> <strong>Ir al inicio de Mi espacio</strong></a>
			</div>
			<div class="Tab_espacio">
				<h3>Portafolio de servicios</h3>
				<div class="subTab">
					<ul>
						<c:forEach var="program" items="${ProgramaForm.path}">
                    		<li class="curSubTab"><a href="${program.url}">${program.nombreCorto}</a></li>
                    	</c:forEach>
                    	<c:if test="${ProgramaForm.modalidad.param.toLowerCase()=='misi' || ProgramaForm.modalidad.param.toLowerCase()=='fa' || ProgramaForm.modalidad.param.toLowerCase()=='beca' || ProgramaForm.modalidad.param.toLowerCase()=='misa'}">
							<li class="curSubTab"><span>Registro de datos complementarios</span></li>
						</c:if>
					</ul>
					<div class="clearfix"></div>
				</div>
				<div class="clearfix"></div>
			</div>
			<p class="labeled">
				${ProgramaForm.modalidad.nombreCorto}<br>
				<span class="normal">Datos iniciales para ${ProgramaForm.modalidad.nombre}</span><br>
				Los datos marcados con <span>*</span> son obligatorios
			</p>
		</div>
		<div class="app_holder2">
			<div class="app">
				<div class="datos_personales">
						<c:if test="${ProgramaForm.modalidad.param.toLowerCase()=='misi' || ProgramaForm.modalidad.param.toLowerCase()=='fa' || ProgramaForm.modalidad.param.toLowerCase()=='beca' || ProgramaForm.modalidad.param.toLowerCase()=='misa'}">
								<fieldset class="data_edit">
									<legend>Datos personales</legend>
									<div class="grid1_3 margin_top_10 fl">
										<label for="idEstadoCivilSelect"><span>*</span> Estado Civil</label>
										<input type="hidden" name="idEstadoCivil" id="idEstadoCivil" dojoType="dijit.form.TextBox">
										<select dojotype="dijit.form.FilteringSelect" id="idEstadoCivilSelect" ${ProgramaForm.perfil.idEstadoCivil < 5 ? 'disabled="disabled"' : '' }
											autocomplete="true" value="${ProgramaForm.perfil.idEstadoCivil}" required="true" missingMessage="Seleccione estado civil" class="${classCivil}">
											<c:forEach var="row" items="${estadoscivil}">
												<option value="${row.idCatalogoOpcion}">${row.opcion}</option>
											</c:forEach>
										</select>
									</div>
									<div class="clearfix"></div>
									<div class="margin_top_10 fl">
										<div class="labeled"><span>*</span> ¿Tiene alguna discapacidad?</div>
									</div>
									<div class="clearfix"></div>
									<ul class="disc">
										<li>
											<input type="checkbox" name="discapacidades" id="discapacidadNinguna" value="1" onclick="unchekedAll()" dojoType="dijit.form.CheckBox"
												${ProgramaForm.discapacidad.ninguna eq 1 ? 'checked="checked"' : '' }
											<label for="discapacidadNinguna">Ninguna</label>
										</li>
										<li>
											<input type="checkbox" name="discapacidades" id="discapacidadAuditiva" value="2" onclick="unchekedNone()" dojoType="dijit.form.CheckBox"
												${ProgramaForm.discapacidad.auditiva eq 1 ? 'checked="checked"' : '' }
											<label for="discapacidadAuditiva">Auditiva</label>
										</li>
										<li>
											<input type="checkbox" name="discapacidades" id="discapacidadIntelectual" value="3" onclick="unchekedNone()" dojoType="dijit.form.CheckBox"
												${ProgramaForm.discapacidad.intelectual eq 1 ? 'checked="checked"' : '' }
											<label for="discapacidadIntelectual">Intelectual</label>
										</li>
										<li>
											<input type="checkbox" name="discapacidades" id="discapacidadMental" value="4" onclick="unchekedNone()" dojoType="dijit.form.CheckBox"
												${ProgramaForm.discapacidad.mental eq 1 ? 'checked="checked"' : '' }
											<label for="discapacidadMental">Mental</label>
										</li>
										<li>
											<input type="checkbox" name="discapacidades" id="discapacidadMotora" value="5" onclick="unchekedNone()" dojoType="dijit.form.CheckBox"
												${ProgramaForm.discapacidad.motora eq 1 ? 'checked="checked"' : '' }
											<label for="discapacidadMotora">Motriz</label>
										</li>
										<li>
											<input type="checkbox" name="discapacidades" id="discapacidadVisual" value="6" onclick="unchekedNone()" dojoType="dijit.form.CheckBox"
												${ProgramaForm.discapacidad.visual eq 1 ? 'checked="checked"' : '' }
											<label for="discapacidadVisual">Visual</label>
										</li>
									</ul>
								</fieldset>
								<fieldset class="data_edit">
									<legend>Datos de domicilio</legend>
									<div class="grid1_3 margin_top_10 fl">
										<label for="calle"><span>*</span> Calle</label>
										<input type="text" dojoType="dijit.form.ValidationTextBox" name="calle" id="calle" maxlength="50" required="true"
											value="${ProgramaForm.perfil.calle}" disabled="disabled" missingMessage="Dato requerido">
									</div>
									<div class="grid1_3 margin_top_10 fl">
										<label for="numeroExterior"><span>*</span> Número exterior</label>
										<input type="text" dojoType="dijit.form.ValidationTextBox" name="numeroExterior" id="numeroExterior" maxlength="50" required="true"
											value="${ProgramaForm.perfil.numeroExterior}" disabled="disabled" missingMessage="Dato requerido">
									</div>
									<div class="grid1_3 margin_top_10 fl">
										<label for="numeroInterior">Número interior</label>
										<input type="text" dojoType="dijit.form.ValidationTextBox" name="numeroInterior" id="numeroInterior" maxlength="50"
											trim ="true" required="false" value="${ProgramaForm.perfil.numeroInterior}" missingMessage="Dato requerido">
									</div>
									<div class="grid1_3 margin_top_10 fl">
										<label for="entreCalle"><span>*</span> Entre calles</label>
										<input type="text" dojoType="dijit.form.ValidationTextBox" name="entreCalle" id="entreCalle" maxlength="50" required="true"
											value="${ProgramaForm.perfil.entreCalle}" trim ="true" uppercase="true" missingMessage="Dato requerido">
									</div>
									<div class="grid1_3 margin_top_10 fl">
										<label for="yCalle"><span>*</span> Y calle</label>
										<input type="text" dojoType="dijit.form.ValidationTextBox" name="yCalle" id="yCalle" maxlength="50" required="true"
											value="${ProgramaForm.perfil.yCalle}" trim ="true" uppercase="true" missingMessage="Dato requerido">
									</div>
									<div class="clearfix"></div>
									<div class="grid1_3 margin_top_10 fl">
										<label for="idEntidadSelect"><span>*</span> Entidad Federativa</label>
										<input type="hidden" name="idEntidad" id="idEntidad" dojoType="dijit.form.TextBox">
										<select dojotype="dijit.form.FilteringSelect" store="entidadFederativaStore" id="idEntidadSelect" required="true"
											invalidMessage="Dato no válido" missingMessage="Es necesario indicar la entidad." autocomplete="true"
											disabled="disabled" onchange="javascript:actulizaMunicipio();" scrollOnFocus="true" maxHeight="250">
										</select>
									</div>
									<div class="grid1_3 margin_top_10 fl">
										<label for="idMunicipioSelect"><span>*</span> Municipio o Delegación</label>
										<input type="hidden" name="idMunicipio" id="idMunicipio" dojoType="dijit.form.TextBox">
											<select dojotype="dijit.form.FilteringSelect" store="municipioStore" id="idMunicipioSelect" required="true"
												disabled="disabled" invalidMessage="Dato no válido" missingMessage="Es necesario indicar un municipio."
												autocomplete="true" onchange="javascript:actulizaColonia();" scrollOnFocus="true" maxHeight="250">
											</select>
									</div>
									<div class="grid1_3 margin_top_10 fl">
										<label for="idColonia"><span>*</span> Colonia</label>
										<input type="hidden" name="idColonia" id="idColonia" dojoType="dijit.form.TextBox">
											<select dojotype="dijit.form.FilteringSelect" store="coloniaStore" id="idColoniaSelect" required="true"
												invalidMessage="Dato inválido" missingMessage="Es necesario indicar la colonia." autocomplete="true"
												disabled="disabled" onchange="javascript:actulizaCodigoPostal();" scrollOnFocus="true" maxHeight="250">
											</select>
									</div>
									<div class="grid1_3 margin_top_10 fl">
										<label for="idLocalidad"><span>*</span> Localidad</label>
										<input type="hidden" name="idLocalidad" id="idLocalidad" dojoType="dijit.form.TextBox">
										<select dojotype="dijit.form.FilteringSelect" id="idLocalidadSelect" store="localidadStore" required="true" 
											missingMessage="Debe seleccionar la localidad." autocomplete="true" scrollOnFocus="true" maxHeight="250" >
										</select>
									</div>
									<div class="grid1_3 margin_top_10 fl">
										<label for="codigoPostal">Código Postal</label>
										<input type="text" name="codigoPostal" id="codigoPostal" maxlength="5" style="width: 7em;"
											value="${ProgramaForm.perfil.codigoPostal}" dojoType="dijit.form.ValidationTextBox" regExp="^[0-9]{5}"
											invalidMessage="Codigo incorrecto, solo se admiten caracteres numericos." readonly="readonly"
								 			missingMessage="Es necesario indicar el codigo postal." trim="true" required="true"/>
									</div>
									<div class="clearfix"></div>
									<div class="campoTexto margin_top_30">
										<label for="referenciaDomicilio"><span>*</span> Referencia para llegar al domicilio</label>
										<textarea class="textGoogie" rows="4" cols="70" name="referenciaDomicilio" id="referenciaDomicilio"
											onkeypress="return caracteresValidos(event);" onchange="return maximaLongitud(this,200)" maxlength="200"
											onKeyUp="return maximaLongitud(this,200)" required="false" onblur="trimSpaces(this)"
											regExp="^[\w\d\s\.&aacute;&eacute;&iacute;&oacute;&uacute;&Aacute;&Eacute;&Iacute;&Oacute;&Uacute;&Ntilde;&ntilde;]{1,200}$">${ProgramaForm.perfil.domicilioReferencia}</textarea>
										<script type="text/javascript">
						         			var vSpellCon = new GoogieSpell("googiespell/", '<%=vProxy%>');
						         			vSpellCon.setLanguages({'es': 'Español'});
					   						vSpellCon.hideLangWindow();
					  						vSpellCon.decorateTextarea("referenciaDomicilio");
					  					</script>
									</div>
								</fieldset>
								<fieldset class="data_edit">
									<legend>Datos de contacto</legend>
									<div class="labeled margin_top_10"><span>*</span> Teléfono</div>
									<p class="labeled">Debe ingresar un total de 10 dígitos (Clave LADA + Teléfono).</p>
									<div class="grid1_3  margin_top_20 fl">
										<div class="margin-r_20 fl">
											<div class="labeled margin_no_t"><span>*</span> Tipo de teléfono</div>	
											<div>
												<div class="tipo_tel margin_top_10 margin-r_10 fl">		
													<input class="fl" type="radio" name="tipoTelefono" id="idTipoTelefonoFijo"
														${ProgramaForm.perfil.principal.idTipoTelefono == 5 ? 'checked="checked"' : '' }
														value="<%=Constantes.TELEFONO_FIJO%>" disabled="disabled"/>
													<label class="fl" for="idTipoTelefonoFijo">Fijo</label>
												</div>
												<div class="tipo_tel margin_top_10 fl"> 
													<input class="fl" type="radio" name="tipoTelefono" id="idTipoTelefono"
														${ProgramaForm.perfil.principal.idTipoTelefono == 1 ? 'checked="checked"' : '' }
														value="<%=Constantes.TELEFONO_CELULAR%>" disabled="disabled"/>
													<label class="fl" for="idTipoTelefono">Celular</label>
												</div>
											 </div>					       			       
									    </div>	
									    <div class="margin-r_10 fl">    
											 <label for="acceso"><span>*</span> Acceso</label>
											 <div class="ta_center">
									             <span class="acceso">
									             	<input type="text" name="acceso" id="acceso" value="${ProgramaForm.perfil.principal.acceso}" maxlength="3"
									             		style="width:3em;" dojoType="dijit.form.ValidationTextBox" required="true" disabled="disabled"
									             		regExp='^(<%=Constantes.CLAVE_TELEFONO_CELULAR + "|" + Constantes.CLAVE_TELEFONO_FIJO%>)$' 
									             		invalidMessage="Acceso telefónico inválido" missingMessage="Es necesario indicar el acceso telefónico" trim="true">
									             </span>
								             </div>
										</div>
										<div class="fl">
											<label for="clave"><span>*</span> Clave lada</label>
								         	<input type="text" name="clave" id="clave" maxlength="3" required="true" disabled="disabled"
								         		value="${ProgramaForm.perfil.principal.clave}" dojoType="dijit.form.ValidationTextBox" regExp="^[+]?\d{2,3}$"
								       			invalidMessage="Clave lada inválida" missingMessage="Es necesario indicar la clave lada" trim="true">
										</div>
									</div>
									<div class="margin_top_20 fl">
										<label for="telefono"><span>*</span> Teléfono</label>
								     	<input type="text" name="telefono" id="telefono" dojoType="dijit.form.ValidationTextBox" regExp="^[+]?\d{7,8}$"
								     		maxlength="8" style="width:8em;" value="${ProgramaForm.perfil.principal.telefono}" trim="true" required="true" 
								     		disabled="disabled" invalidMessage="Teléfono inválido" missingMessage="Es necesario indicar el teléfono">
									</div>
									<div class="margin_top_20 fl">
										<label for="extension">Extensión</label>
							            <input class="sugerido" type="text" name="extension" id="extension" maxlength="8" style="width:8em;" required="false"
							            	disabled="disabled" value="${ProgramaForm.perfil.principal.extension}" dojoType="dijit.form.ValidationTextBox" regExp="^[+]?\d{0,8}$"
							            	invalidMessage="Extensión telefónica inválida" missingMessage="Es necesario indicar la extensión telefónica" trim="true">		
									</div>
									<div class="clearfix"></div>
									<div id="phoneadds" name="phoneadds">
										<jsp:include page="/jsp/candidatos/misDatos/phonemovint.jsp" flush="true" />  
									</div>
									<div class="clearfix"></div>
									<div class="grid1_3 margin_top_10 fl">
										<div class="labeled"><span>*</span> ¿Deseas recibir ofertas de empleo? por:</div>
									</div>
									<div class="clearfix"></div>
									<div class="tipo_tel margin_top_10 margin-r_10 fl">
										<input class="fl" type="checkbox" name="recibeOferta" id="idRecibeOferta1" value="${recibeTEL}" onclick="hasCel(this);" dojoType="dijit.form.CheckBox"
											${ProgramaForm.perfil.perfilLaboral.idRecibeOferta eq recibeTEL ? 'checked="checked"' : '' }
											${ProgramaForm.perfil.perfilLaboral.idRecibeOferta eq recibeAMBAS ? 'checked="checked"' : '' }/>
										<label for="idRecibeOferta1">Celular</label>
									</div>
									<div class="tipo_tel margin_top_10 margin-r_10 fl">
										<input class="fl" type="checkbox" name="recibeOferta" id="idRecibeOferta2" value="${recibeCORREO}" onclick="correoCapturadoRadio(this)" dojoType="dijit.form.CheckBox"
											${ProgramaForm.perfil.perfilLaboral.idRecibeOferta eq recibeCORREO ? 'checked="checked"' : '' }
											${ProgramaForm.perfil.perfilLaboral.idRecibeOferta eq recibeAMBAS ? 'checked="checked"' : '' } />
										<label for="idRecibeOferta2">Correo electrónico</label>
									</div>
								</fieldset>
								<fieldset class="otros_estudios">
									<legend>Estudios</legend>
									<div class="fl margin-r_20 margin_top_20">
										<div class="labeled"><span>*</span> ¿Realizó estudios con ayuda de PROSPERA?</div>
										<label class="fl margin-r_20"><input type="radio" name="ayudaProspera" value="2" <c:if test='${ProgramaForm.perfil.perfilVO.apoyoProspera==2}'>checked</c:if> onclick="updFolioProsp();" ${disabledProsp}/> Sí</label>
										<label class="fl"><input type="radio" name="ayudaProspera" value="1" <c:if test='${ProgramaForm.perfil.perfilVO.apoyoProspera==1}'>checked</c:if> onclick="updFolioProsp();" ${disabledProsp}/> No</label>
										<div class="clearfix"></div>
									</div>
									<div id="folioProsperaDiv" style="display: none;">
										<div class="grid1_3 margin_top_40 fl">
											<label for="folioProspera"><span>*</span> No. de Folio Familia PROSPERA</label>
											<input type="text" name="folioProspera" id="folioProspera" dojoType="dijit.form.ValidationTextBox" maxlength="9"
												value="${ProgramaForm.perfil.perfilVO.folioProspera}" required="true" trim="true" regExp="^[+]?\d{9}?$"
												invalidMessage="El valor especificado no es válido para número de folio familia prospera."
												missingMessage="Es necesario indicar número de folio familia prospera." />
										</div>
									</div>
									<div class="clearfix"></div>
									<div id="otros_estudios bloque" name="otros_estudios bloque">
										<jsp:include page="/jsp/candidatos/misDatos/studiescomp.jsp" flush="true" />
									</div>
								</fieldset>
								<fieldset class="conocimientos">
									<legend>Conocimientos, habilidades y aptitudes</legend>
									<div class="grid1_3 margin_top_10 fl">
										<label for="conocimiento">Conocimiento</label>
										<input class="${classCono}" type="text" name="conocimiento"	id="conocimiento" regExp="^[\w\d\s\.áéíóúÁÉÍÓÚñÑ]{1,50}$"
											value="${ProgramaForm.perfil.conocimientoPrincipal.conocimientoHabilidad}" missingMessage="Debe indicar el conocimiento."
											invalidMessage="Carácter no permitido" dojoType="dijit.form.ValidationTextBox" trim ="true" uppercase="true" required="false" />
									</div>
									<div class="grid1_3 margin_top_10 fl">
										<label for="idExperienciaConSelect">Experiencia</label>
										<input type="hidden" name="idExperienciaCon" id="idExperienciaCon" dojoType="dijit.form.TextBox" />
										<select class="${classExpCon}" value='${ProgramaForm.perfil.conocimientoPrincipal.idExperiencia}'
											dojotype="dijit.form.FilteringSelect" id="idExperienciaConSelect" required="false" autocomplete="true">
											<c:forEach var="row" items="${opciExperiencia}">
												<option value="${row.idCatalogoOpcion}">${row.opcion}</option>
											</c:forEach>
										</select>
									</div>
									<div class="clearfix"></div>
									<div class="campoTexto margin_top_30">
										<label for="descripcionCon">Descripción</label>
										<div>En este espacio puedes ampliar la información sobre este conocimiento</div>
										<textarea class="textGoogie" rows="4" cols="70" name="descripcionCon" id="descripcionCon" onkeypress="return caracteresValidos(event);"
											onchange="return maximaLongitud(this,500)" onKeyUp="return maximaLongitud(this,500)" maxlength="500" required="false" onblur="trimSpaces(this)"
											regExp="^[\w\d\s\.&aacute;&eacute;&iacute;&oacute;&uacute;&Aacute;&Eacute;&Iacute;&Oacute;&Uacute;&Ntilde;&ntilde;]{1,500}$">${ProgramaForm.perfil.conocimientoPrincipal.descripcion}</textarea>
										<script type="text/javascript">
						         			var vSpellCon = new GoogieSpell("googiespell/", '<%=vProxy%>');
						         			vSpellCon.setLanguages({'es': 'Español'});
					   						vSpellCon.hideLangWindow();
						  					vSpellCon.decorateTextarea("descripcionCon");
										</script>
									</div>
									<div id="idConocimientosBloque">
										<jsp:include page="/jsp/candidatos/misDatos/conocsmovint.jsp"
											flush="true" />
									</div>
								</fieldset>
								<fieldset>
									<legend>Experiencia y expectativas laborales</legend>
									<div class="grid1_3 margin_top_10 fl">
										<input type="hidden" name="idTipoEmpleo" id="idTipoEmpleo" dojoType="dijit.form.TextBox" />
										<label for="idTipoEmpleoSelect"><span>*</span> Tipo de empleo</label>
										<select
											dojotype="dijit.form.FilteringSelect" value="${ProgramaForm.perfil.expectativaLaboral.idTipoEmpleoDeseado}"
											id="idTipoEmpleoSelect" required="false" missingMessage="Debe seleccionar el tipo de empleo." autocomplete="true">
											<c:forEach var="row" items="${tiposempleo}">
												<option value="${row.idCatalogoOpcion}">${row.opcion}</option>
											</c:forEach>
										</select>
									</div>
									<div class="grid1_3 margin_top_10 fl">
										<input type="hidden" name="idTipoContrato" id="idTipoContrato" dojoType="dijit.form.TextBox" />
										<label for="idTipoContratoSelect"><span>*</span> Tipo de contrato</label>
										<select
											dojotype="dijit.form.FilteringSelect" value="${ProgramaForm.perfil.expectativaLaboral.idTipoContrato}"
											id="idTipoContratoSelect" required="false" missingMessage="Debe seleccionar el tipo de contrato." autocomplete="true">
											<c:forEach var="row" items="${tiposcontrato}">
												<option value="${row.idCatalogoOpcion}">${row.opcion}</option>
											</c:forEach>
										</select>
									</div>
									<div class="disp grid1_3 margin_top_10 fl">
										<label for="disponibilidadRadicarPais"><span>*</span> ¿Puedes radicar en otro país?</label><br>
										<label><input type="radio" name="disponibilidadRadicarPais" value="2" <c:if test='${ProgramaForm.perfil.perfilVO.disponibilidadRadicarPais==2}'>checked</c:if>/> Sí</label>&nbsp;&nbsp;&nbsp;
										<label><input type="radio" name="disponibilidadRadicarPais" value="1" <c:if test='${ProgramaForm.perfil.perfilVO.disponibilidadRadicarPais==1}'>checked</c:if>/> No</label>
									</div>
								</fieldset>
								<fieldset id="ultimoTrabajoReq" class="ultimo_trabajo bloque">
									<legend>Trabajo actual o último</legend>
									<div class="grid1_3 margin_top_10 fl">
										<div id="spanEmpresaReq" style="display:none"><label for="empresa"><span >*</span> Nombre o razón social de la empresa</label></div>
										<div id="spanEmpresa" style="display:block"><label for="empresa">Nombre o razón social de la empresa</label></div>
										<input type="text" name="empresa" id="empresa" value="${ProgramaForm.perfil.trabajoActual.empresa}" maxlength="150"
											regExp="^[A-Za-z0-9\s\d\-.&,áéíóúÁÉÍÓÚñÑ']{3,150}$" dojoType="dijit.form.ValidationTextBox" required="false"
											invalidMessage="El Nombre o	razón social de la empresa es invalido, favor de verificar" onblur="setRequiredJob()"
											missingMessage="Es necesario indicar Nombre o razón social de la empresa." trim ="true" uppercase="true"/>
									</div>
									<div class="fl margin_top_30">
										<label class="fw_no_bold" for="confidencialidadEmpresa"><input type="checkbox" name="confidencialidadEmpresa" id="confidencialidadEmpresa" value="${mostrarEmpSI}"
											${ProgramaForm.perfil.trabajoActual.confidencialidadEmpresa eq mostrarEmpSI ? 'checked="checked"' : '' }
											dojoType="dijit.form.CheckBox" required="false" />
										Omitir este dato en la publicación de mi perfil</label>
									</div>
									<div class="clearfix"></div>
									<div class="grid1_3 margin_top_10 fl">
										<div id="spanPuestoReq" style="display:none"><label for="puesto"><span>*</span> Puesto desempeñado</label></div>
										<div id="spanPuesto" style="display:block"><label for="puesto">Puesto desempeñado</label></div>
										<input type="text" name="puesto" id="puesto" maxlength="150" regExp="^[A-Za-z\s\-.áéíóúÁÉÍÓÚñÑ']{1,150}$"
											value="${ProgramaForm.perfil.trabajoActual.puesto}" dojoType="dijit.form.ValidationTextBox" required="false" 
											invalidMessage="El valor especificado puesto desempeñado no es válido."
											missingMessage="Es necesario selecionar el puesto desempeñado." trim ="true" uppercase="true"/>
									</div>
									<div class="grid1_3 margin_top_10 fl">
										<div id="spanJerarquiaReq" style="display:none"><label for="idJerarquiaSelect"><span>*</span> Jerarquía del puesto</label></div>
										<div id="spanJerarquia" style="display:block"><label for="idJerarquiaSelect">Jerarquía del puesto</label></div>
										<input type="hidden" name="idJerarquia" id="idJerarquia" dojoType="dijit.form.TextBox" />
										<select dojotype="dijit.form.FilteringSelect" id="idJerarquiaSelect" value="${ProgramaForm.perfil.trabajoActual.idJerarquia}" 
											required="false" invalidMessage="El valor especificado jerarquía del puesto no es válido."
											missingMessage="Es necesario selecionar el Jerarquía del puesto." autocomplete="true">
											<c:forEach var="row" items="${jerarquias}">
												<option value="${row.idCatalogoOpcion}">${row.opcion}</option>
											</c:forEach>
										</select>
									</div>
									<div class="grid1_3 margin_top_10 fl">
										<div id="spanPersonasReq" style="display:none"><label for="personasCargoSelect"><span>*</span> Número de personas a cargo</label></div>
										<div id="spanPersonas" style="display:block"><label for="personasCargoSelect">Número de personas a cargo</label></div>
										<input type="hidden" name="personasCargo" id="personasCargo" dojoType="dijit.form.TextBox" />
										<select dojotype="dijit.form.FilteringSelect" id="personasCargoSelect" value="${ProgramaForm.perfil.trabajoActual.personasCargo}" 
											required="true" missingMessage="Es necesario seleccionar el número de personas a cargo." autocomplete="true">
											<c:forEach var="row" items="${personascargo}">
												<option value="${row.idCatalogoOpcion}">${row.opcion}</option>
											</c:forEach>
										</select>
									</div>
									<div class="grid1_3 margin_top_10 fl">
										<div id="spanSalarioReq" style="display:none"><label for="salarioMensual"><span>*</span> Salario mensual recibido</label></div>
										<div id="spanSalario" style="display:block"><label for="salarioMensual">Salario mensual recibido</label></div>
										<input type="text" name="salarioMensual" id="salarioMensual" dojoType="dijit.form.ValidationTextBox" maxlength="9"
											value="${ProgramaForm.perfil.trabajoActual.salarioMensual}" required="true" regExp="^[+]?\d{1,6}(\.\d{1,2})?$" trim="true"
											invalidMessage="El valor especificado no es válido por Salario Mensual recibido."
											missingMessage="Es necesario indicar salario mensual recibido." />
									</div>
									<div class="grid1_3 margin_top_10 fl">
										<div id="spanIngresoReq" style="display:none"><label for="laboresInicialDia"><span>*</span> Fecha de ingreso</label></div>
										<div id="spanIngreso" style="display:block"><label for="laboresInicialDia">Fecha de ingreso</label></div>
										<select dojotype="dijit.form.FilteringSelect" name="laboresInicialDia" id="laboresInicialDia"
											missingMessage="Es necesario indicar la Fecha de ingreso.">
											<option value="0">Día</option>
											<%=getSelectedDay(laboresInicial)%>
										</select>
										<select dojotype="dijit.form.FilteringSelect" name="laboresInicialMes" id="laboresInicialMes"
											missingMessage="Es necesario indicar la Fecha de ingreso.">
											<option value="0">Mes</option>
											<%=getSelectedMonth(laboresInicial) %>
										</select>
										<select dojotype="dijit.form.FilteringSelect" name="laboresInicialAnual" id="laboresInicialAnual"
											missingMessage="Es necesario indicar la Fecha de ingreso.">
											<option value="0">Año</option>
											<%=getSelectedYear(laboresInicial) %>
										</select>
									</div>
									<div class="grid1_3 margin_top_10 fl">
										<div id="spanTerminacionReq" style="display:none"><label for="laboresFinalDia"><span>*</span> Fecha de terminación</label></div>
										<div id="spanTerminacion" style="display:block"><label for="laboresFinalDia">Fecha de terminación</label></div>
										<select dojotype="dijit.form.FilteringSelect" name="laboresFinalDia" id="laboresFinalDia"
											missingMessage="Es necesario indicar la Fecha de terminación.">
											<option value="0">Día</option>
											<%=getSelectedDay(laboresFinal)%>
										</select>
										<select dojotype="dijit.form.FilteringSelect" name="laboresFinalMes" id="laboresFinalMes"
											missingMessage="Es necesario indicar la Fecha de terminación.">
											<option value="0">Mes</option>
											<%=getSelectedMonth(laboresFinal) %>
										</select>
										<select dojotype="dijit.form.FilteringSelect" name="laboresFinalAnual" id="laboresFinalAnual"
											missingMessage="Es necesario indicar la Fecha de terminación.">
											<option value="0">Año</option>
											<%=getSelectedYear(laboresFinal) %>
										</select>
									</div>
									<div class="clearfix"></div>
									<div class="funciones_01 margin_top_30">
										<div id="spanFuncionReq" style="display:none"><label for="funcion"><span>*</span> Funciones desempeñadas</label></div>
										<div id="spanFuncion" style="display:block"><label for="funcion">Funciones desempeñadas</label></div>
										<div class="campoTexto">
											<textarea name="funcion" id="funcion" maxlength="500" onkeypress="return caracteresValidos(event);"
												onchange="return maximaLongitud(this,500)" onKeyUp="return maximaLongitud(this,500)" onblur="trimSpaces(this)"
												regExp="^[\w\d\s\.&aacute;&eacute;&iacute;&oacute;&uacute;&Aacute;&Eacute;&Iacute;&Oacute;&Uacute;&Ntilde;&ntilde;]{1,500}$"
												class="textGoogie" require="false">${ProgramaForm.perfil.trabajoActual.funcion}</textarea>
											<script type="text/javascript">
							         			var vSpellFuncion = new GoogieSpell("googiespell/", '<%=vProxy%>');
							         			vSpellFuncion.setLanguages({'es': 'Español'});
						   						vSpellFuncion.hideLangWindow();
						  						vSpellFuncion.decorateTextarea("funcion");
											</script>
										</div>
										<div class="margin_40"></div>
									</div>
								</fieldset>
								<fieldset class="inf_com bloque">
									<legend>Información complementaria</legend>
									<div class="grid1_3 margin_top_10 fl">
										<div class="labeled"><span>*</span> ¿El SNE lo envió a cubrir alguna oferta de empleo?</div>
										<label class="fl"><input type="radio" name="cubrirVacante" value="2" <c:if test='${ProgramaForm.sne.vacanteEnviado==2}'>checked</c:if> ${disabledOffer} onclick="updLastRec();"/> Sí</label>
										<label class="fl"><input type="radio" name="cubrirVacante" value="1" <c:if test='${ProgramaForm.sne.vacanteEnviado==1}'>checked</c:if> ${disabledOffer} onclick="updLastRec();"/> No</label>
										<div class="clearfix"></div>
									</div>
									<div id="lastRecruit" style="display: none;">
										<div class="grid1_3 margin_top_30 fl">
											<label for="tituloOferta">Título de la oferta</label>
											<input type="text" name="tituloOferta" id="tituloOferta" dojoType="dijit.form.ValidationTextBox" maxlength="50"
												value="${ProgramaForm.sne.vacanteNombre}" required="true" trim ="true" uppercase="true" ${disabledOffer}
												invalidMessage="El valor especificado no es válido para el título de la oferta."
												missingMessage="Es necesario indicar el título de la oferta." />
										</div>
										<div class="grid1_3 margin_top_10 fl">
											<div class="labeled">En qué fecha</div>
											<select dojotype="dijit.form.FilteringSelect" name="fechaVinculacionDia" id="fechaVinculacionDia" ${disabledOffer}
												style="width: 7em;" missingMessage="Es necesario indicar la Fecha.">
												<option value="0">Día</option>
												<%=getSelectedDay(fechaVinculacion)%>
											</select>
											<select dojotype="dijit.form.FilteringSelect" name="fechaVinculacionMes" id="fechaVinculacionMes" ${disabledOffer}
												style="width: 7em;" missingMessage="Es necesario indicar la Fecha.">
												<option value="0">Mes</option>
												<%=getSelectedMonth(fechaVinculacion) %>
											</select>
											<select dojotype="dijit.form.FilteringSelect" name="fechaVinculacionAnual" id="fechaVinculacionAnual" ${disabledOffer}
												style="width: 7em;" missingMessage="Es necesario indicar la Fecha.">
												<option value="0">Año</option>
												<%=getSelectedYear(fechaVinculacion) %>
											</select>
										</div>
										</div>
										<div class="clearfix"></div>
										<ul>
										<li>
											<div class="labeled"><span>*</span> ¿Se colocó en la oferta?</div>
											<label class="fl"><input type="radio" name="colocadoVacante" value="2" <c:if test='${ProgramaForm.sne.vacanteColocado==2}'>checked</c:if> ${disabledOffer}/> Sí</label>
											<label class="fl"><input type="radio" name="colocadoVacante" value="1" <c:if test='${ProgramaForm.sne.vacanteColocado==1}'>checked</c:if> ${disabledOffer}/> No</label>
											<div class="clearfix"></div>
										</li>
										<li>									
									<div class="grid1_3 margin_top_30 fl">
										<div class="labeled"><span>*</span> ¿Habla alguna lengua indígena?</div>
										<label class="fl"><input type="radio" name="lenguaIndigena" value="2" <c:if test='${ProgramaForm.sne.lenguaIndigenaConocimiento==2}'>checked</c:if> onclick="updLenguaInd();"/> Sí</label>
										<label class="fl"><input type="radio" name="lenguaIndigena" value="1" <c:if test='${ProgramaForm.sne.lenguaIndigenaConocimiento==1}'>checked</c:if> onclick="updLenguaInd();"/> No</label>
										<div class="clearfix"></div>
									</div>
									<div id="lenguaIndigenaDiv" style="display: none;">
										<div class="grid1_3 margin_top_50 fl">
											<label for="lenguaIndigenaSelect"><span>*</span> Lengua indígena</label>
											<input type="hidden" name="idLenguaIndigena" id="idLenguaIndigena" dojoType="dijit.form.TextBox" />
											<select dojotype="dijit.form.FilteringSelect" id="lenguaIndigenaSelect" value="${ProgramaForm.sne.idLenguaIndigena}" 
												required="true" missingMessage="Es necesario seleccionar la lengua indígena." autocomplete="true">
												<c:forEach var="row" items="${lenguaIndigena}">
													<option value="${row.idCatalogoOpcion}">${row.opcion}</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<div class="clearfix"></div>
									</li>
									<li>
									<div class="grid1_3 margin_top_10 fl">
										<div class="labeled"><span>*</span> ¿Requiere capacitación?</div>
										<label class="fl"><input type="radio" name="requiereCapacitacion" value="2" <c:if test='${ProgramaForm.sne.capacitacionRequiere==2}'>checked</c:if> onclick="updCapac();"/> Sí</label>
										<label class="fl"><input type="radio" name="requiereCapacitacion" value="1" <c:if test='${ProgramaForm.sne.capacitacionRequiere==1}'>checked</c:if> onclick="updCapac();"/> No</label>
										<div class="clearfix"></div>
									</div>
									<div id="requiereCapacitacionDiv" style="display: none;">
										<div class="grid1_3 margin_top_30 fl">
											<label for="necesitaCapacitacion"><span>*</span> ¿En qué considera que necesita capacitación?</label>
											<input type="text" name="necesitaCapacitacion" id="necesitaCapacitacion" dojoType="dijit.form.ValidationTextBox"
												maxlength="100" value="${ProgramaForm.sne.capacitacionDesc}" required="true" trim ="true" uppercase="true"
												invalidMessage="El valor especificado no es válido para necesita capacitación."
												missingMessage="Es necesario indicar necesita capacitación." />
										</div>
										<div class="disp grid1_3 margin_top_10 fl">
											<div class="labeled"><span>*</span> ¿Cuenta con 6 horas diarias para capacitarse?</div>
											<label><input type="radio" name="tiempoCapacitacion" value="2" <c:if test='${ProgramaForm.sne.capacitacionDispone6h==2}'>checked</c:if>/> Sí</label>&nbsp;&nbsp;&nbsp;
											<label><input type="radio" name="tiempoCapacitacion" value="1" <c:if test='${ProgramaForm.sne.capacitacionDispone6h==1}'>checked</c:if>/> No</label>
										</div>
									</div>
									<div class="clearfix"></div>
									</li>
									<li>
									<div class="grid1_3 margin_top_10 fl">
										<div class="labeled"><span>*</span> ¿Tiene un negocio propio?</div>
										<label class="fl"><input type="radio" name="negocioPropio" value="2" <c:if test='${ProgramaForm.sne.negocioTiene==2}'>checked</c:if> onclick="updOwnBis();"/> Sí</label>
										<label class="fl"><input type="radio" name="negocioPropio" value="1" <c:if test='${ProgramaForm.sne.negocioTiene==1}'>checked</c:if> onclick="updOwnBis();"/> No</label>
										<div class="clearfix"></div>
									</div>
									<div id="tipoNegocioDiv" style="display: none;">
										<div class="grid1_3 margin_top_30 fl">
											<label for="tipoNegocio">¿Qué tipo o giro de negocio?</label>
											<input type="text" name="tipoNegocio" id="tipoNegocio" dojoType="dijit.form.ValidationTextBox" maxlength="100"
												value="${ProgramaForm.sne.negocioDescGiro}" required="true" trim ="true" uppercase="true"
												invalidMessage="El valor especificado no es válido para tipo de negocio."
												missingMessage="Es necesario indicar tipo de negocio." />
										</div>
									</div>
									<div class="clearfix"></div>
									</li>
									<li>
									<div class="grid1_3 margin_top_10 fl">
										<div class="labeled"><span>*</span> ¿Pretende poner un negocio propio?</div>
										<label class="fl"><input type="radio" name="pretendeNegocioPropio" value="2" <c:if test='${ProgramaForm.sne.negocioPretende==2}'>checked</c:if> onclick="updResBis();"/> Sí</label>
										<label class="fl"><input type="radio" name="pretendeNegocioPropio" value="1" <c:if test='${ProgramaForm.sne.negocioPretende==1}'>checked</c:if> onclick="updResBis();"/> No</label>
										<div class="clearfix"></div>
									</div>
									<div id="recursosNegocioDiv" style="display: none;">
										<div class="disp grid1_3 margin_top_10 fl">
											<div class="labeled">¿Cuenta con recursos propios para iniciarlo?</label></div>
											<label><input type="radio" name="recursosNegocio" value="2" <c:if test='${ProgramaForm.sne.negocioTieneRecursos==2}'>checked</c:if>/> Sí</label>&nbsp;&nbsp;&nbsp;
											<label><input type="radio" name="recursosNegocio" value="1" <c:if test='${ProgramaForm.sne.negocioTieneRecursos==1}'>checked</c:if>/> No</label>
										</div>
									</div>
									<div class="clearfix"></div>
									</li>
									<div class="grid1_3 margin_top_10 fl">
										<div class="labeled"><span>*</span> ¿Fue beneficiario de algún Programa?</div>
										<label class="fl"><input type="radio" name="beneficiarioApoyo" value="2" <c:if test='${ProgramaForm.sne.beneficiarioPrograma==2}'>checked</c:if> onclick="updBenefPr();"/> Sí</label>
										<label class="fl"><input type="radio" name="beneficiarioApoyo" value="1" <c:if test='${ProgramaForm.sne.beneficiarioPrograma==1}'>checked</c:if> onclick="updBenefPr();"/> No</label>
										<div class="clearfix"></div>
									</div>
									<div id="programaApoyoDiv" style="display: none;">
										<div class="grid1_3 margin_top_30 fl">
											<label for="idProgramaApoyo">¿Cuál?</label>
											<input type="text" name="idProgramaApoyo" id="idProgramaApoyo" dojoType="dijit.form.ValidationTextBox" maxlength="100"
												value="${ProgramaForm.sne.beneficiarioIdPrograma}" required="false" trim ="true" uppercase="true"
												invalidMessage="El valor especificado no es válido para cuál programa fué beneficiario."
												missingMessage="Es necesario indicar en cuál programa fué beneficiario." />
										</div>
									</div>
									<div class="clearfix"></div>
									</li>
									</ul>
									<div class="grid1_3 margin_top_10 fl">
										<label for="clabe">CLABE interbancaria</label>
										<input type="text" name="clabe" id="clabe" dojoType="dijit.form.ValidationTextBox" maxlength="18"
											value="${ProgramaForm.sne.clabeInterbancaria}" required="true" trim="true" regExp="^[+]?\d{18}$"
											invalidMessage="El valor especificado no es válido para CLABE interbancaria."
											missingMessage="Es necesario indicar CLABE interbancaria." />
									</div>
									<div class="grid1_3 margin_top_10 fl">
										<label for="idBancoSelect">Banco</label>
										<input type="hidden" name="idBanco" id="idBanco" dojoType="dijit.form.TextBox">
										<select dojotype="dijit.form.FilteringSelect" id="idBancoSelect" autocomplete="true"
											value="${ProgramaForm.sne.idBanco}" required="false" missingMessage="Seleccione banco">
											<c:forEach var="row" items="${bancos}">
												<option value="${row.idCatalogoOpcion}">${row.opcion}</option>
											</c:forEach>
										</select>
									</div>
								</fieldset>
								<div id="beneficiaries" name="beneficiaries">
									<jsp:include page="/jsp/candidatos/misDatos/benefmovint.jsp" flush="true" />  
								</div>
						</c:if>
				</div>
			</div>
		</div>
		<div class="form_nav">
			<input type="button" id="btnActualizar" name="btnActualizar" class="boton_naranja" onclick="validateForm();" value="Solicitar participación">
			<input type="button" class="boton_naranja" onclick="doSubmitCancel();" value="Cancelar">
		</div>
	</form>
</div>
<%!
	String getSelectedDay(String sDate) {
		boolean today = false;
		Date date = null;
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			if (null == sDate || sDate.isEmpty()) {
				today = true;
				date = new Date();
			}else date = (Date)formatter.parse(sDate);
		} catch (ParseException e) { date = null; }
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		StringBuilder day = new StringBuilder();
		int dateDay = calendar.get(Calendar.DAY_OF_MONTH);
		for (int i = 1; i<32; i++) {
			String iday = "0" + i;
			if (iday.length() > 2) iday = iday.substring(1,3);
			day.append("<option value=\"" + iday + "\"");
			if (!today && i==dateDay)
				day.append(" selected ");
			day.append(">" + iday + "</option>\n");
		}
		return day.toString();
	}
	
	String getSelectedMonth(String sDate) {
		Date date = null;
		boolean today = false;
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			if (null == sDate || sDate.isEmpty()) {
				today = true;
				date = new Date();
			}else date = (Date)formatter.parse(sDate);
		} catch (ParseException e) { date = null; }
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		StringBuilder day = new StringBuilder();
		int month = calendar.get(Calendar.MONTH) + 1;
		for (int i = 1; i<13; i++) {
			String iday = "0" + i;
			if (iday.length() > 2) iday = iday.substring(1,3);
			day.append("<option value=\"" + iday + "\"");
			if (!today && i==month)
				day.append(" selected ");
			day.append(">" + getLblMonth(i) + "</option>\n");
		}
		return day.toString();
	}
	
	String getSelectedYear(String sDate) {
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(new Date());
    	int cyear = cal.get(Calendar.YEAR);
		Date date = null;
		boolean today = false;
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			if (null == sDate || sDate.isEmpty()) {
				today = true;
				date = new Date();
			}else date = (Date)formatter.parse(sDate);
		} catch (ParseException e) { date = null; }
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		StringBuilder day = new StringBuilder();
		int year = calendar.get(Calendar.YEAR);
		for (int i = 1982; i<cyear+1; i++) {
			day.append("<option value=\"" + i + "\"");
			if (!today &&  i==year)
				day.append(" selected ");
			day.append(">" + i + "</option>\n");
		}
		return day.toString();
	}
	
	String getLblMonth(int imonth) {
		StringBuilder month = new StringBuilder();
		switch(imonth) {
			case 1 : month.append("Enero"); break;
			case 2 : month.append("Febrero"); break;
			case 3 : month.append("Marzo"); break;
			case 4 : month.append("Abril"); break;
			case 5 : month.append("Mayo"); break;
			case 6 : month.append("Junio"); break;
			case 7 : month.append("Julio"); break;
			case 8 : month.append("Agosto"); break;
			case 9 : month.append("Septiembre"); break;
			case 10 : month.append("Octubre"); break;
			case 11 : month.append("Noviembre"); break;
			case 12 : month.append("Diciembre"); break;
			default : month.append("");
		}
		return month.toString();
	}
%>