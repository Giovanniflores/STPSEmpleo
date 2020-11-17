<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="mx.gob.stps.portal.web.candidate.form.EscolaridadForm"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%
	String context = request.getContextPath() + "/";
	String vProxy = "http://orangoo.com/newnox?lang=";
	pageContext.setAttribute("vProxy",vProxy);
%>
<!--  detectar internet explorer -->
<!--[if lt IE 7 ]> <html class="ie6"> <![endif]-->
<!--[if IE 7 ]>    <html class="ie7"> <![endif]-->
<!--[if IE 8 ]>    <html class="ie8"> <![endif]-->
<!--[if IE 9 ]>    <html class="ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html class=""> <!--<![endif]-->

<script type="text/javascript" src="js/helper/messageHelper.js"></script>
<script type="text/javascript" src="js/misDatos/messagesValidate.js"></script>
<script type="text/javascript" src="googiespell/AJS.js"></script>
<script type="text/javascript" src="googiespell/googiespell.js"></script>
<script type="text/javascript" src="googiespell/cookiesupport.js"></script>
<link   href="googiespell/googiespell.css" rel="stylesheet" type="text/css" />

<style type="text/css">
#idCarreraEspecialidadSelect_popup {
	width: 400px !important;
}
</style>

<c:set var="regexp_folioprospera">^\d{9}$</c:set>
<c:set var="regexp_foliointegranteprospera">^\d{9}[a-zA-Z0-9]{1}\d{6}$</c:set>

<c:if test="${empty escolaridadForm.conocimiento}">
	<c:set var="classCono" value="sugerido" />
</c:if>
<c:if test="${escolaridadForm.idExperienciaCon<=0}">
	<c:set var="classExpCon" value="sugerido" />
</c:if>
<c:if test="${empty escolaridadForm.descripcionCon}">
	<c:set var="classDesCon" value="sugerido" />
</c:if>
<c:if test="${empty escolaridadForm.conocimientoCompOtros}">
	<c:set var="classOtros" value="sugerido" />
</c:if>

<c:if test="${not empty escolaridadForm.conocimiento}">
	<c:set var="classCono" value="" />
</c:if>
<c:if test="${escolaridadForm.idExperienciaCon>0}">
	<c:set var="classExpCon" value="" />
</c:if>
<c:if test="${not empty escolaridadForm.descripcionCon}">
	<c:set var="classDesCon" value="" />
</c:if>
<c:if test="${not empty escolaridadForm.conocimientoCompOtros}">
	<c:set var="classOtros" value="" />
</c:if>
<script type="text/javascript">
	var depGrado  = new Array(); //Declara arreglo de dependencias de catalogo Grado de Estudios
	var depIdioma = new Array(); //Declara arreglo de dependencias de catalogo idiomas
	//Declara arreglo de dependencias de catalogo Grado de Estudios
	<%String[] depGrado = session.getAttribute("depGrado") != null
					? (String[]) session.getAttribute("depGrado")
					: new String[0];
			for (int i = 0; i < depGrado.length; i++) {%>
			<%="depGrado[" + i + "] = '" + depGrado[i] + "';"%>
		<%}%>
</script>
<noscript>Funciones de JavaScript desactivadas por navegador</noscript>

<c:if test="${not empty escolaridadForm.idiomasDependientes}">
	<script type="text/javascript">
		<c:forEach var="idiomaDep" items="${escolaridadForm.idiomasDependientes}" varStatus="indexIdi">
			depIdioma[parseInt('${indexIdi.count - 1}')] = '${idiomaDep}';
		</c:forEach>
	</script>
</c:if>

<script type="text/javascript">
    dojo.require("dijit.dijit"); // loads the optimized dijit layer
	dojo.require("dijit.form.Form");
    dojo.require("dijit.form.ValidationTextBox");
    dojo.require("dijit.form.TextBox");
	dojo.require("dojo.data.ItemFileReadStore");
	dojo.require("dijit.form.FilteringSelect");
	dojo.require("dijit.form.Textarea");
	dojo.require('dijit.Dialog');
	dojo.require('dijit.form.RadioButton');
	dojo.require("dijit.form.CheckBox");
	dojo.require("dijit.form.Textarea");
	dojo.require('dojo.parser');
	
	var publicaCertifCVSi = "${publicaCertificacionesCvSI}";	
	var publicaCertifCVNo = "${publicaCertificacionesCvNO}";
	
	dojo.addOnLoad(function() {
		dojo.connect(dijit.byId('idNivelEstudioSelect'), 'onChange', function() {
			var vGrado = dijit.byId('idNivelEstudioSelect').get('value');
			var vIdCatDep = depGrado[vGrado];
            if(vIdCatDep != 0 || vIdCatDep != undefined) {
            	dijit.byId('idCarreraEspecialidadSelect').reset();
            	carreraStore.url = '${context}grados.do?method=cargarCarrera&idCatDep='+ vIdCatDep;
            	carreraStore.close();
			}
			estatusGradoStore.url = '';
    		estatusGradoStore.close();
    		document.getElementById('idSituacionAcademicaSelect').value='';	
    		estatusGradoStore.url = '${context}grados.do?method=situacionesAcademicas&idEscolaridad='+ vGrado;
    		estatusGradoStore.close();
        });
        cargardoCatalogos();
       
        actualizaCertificaciones();

		if ("${escolaridadForm.apoyoProspera}" == "${escolaridadForm.apoyoProsperaSi}") {
			displayProspera(true);
		} else {
			displayProspera(false);
		}

	});	
//--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*	
	function cargardoCatalogos(){
		cargardoCatalogosGrado();
		cargardoCatalogosAcademico();
	}

	function cargardoCatalogosExperiencia(){
		experienciaStore.fetch({
 			onComplete: function(items, request) {
		      if (items.length == 0) {
		         return;
		      }
		      dijit.byId('idExperienciaConSelect').attr('value', '${escolaridadForm.idExperienciaCon}');
		      dijit.byId('idExperienciaCompuSelect').attr('value', '${escolaridadForm.idExperienciaCompu}');
		      dijit.byId('idExperienciaOfficeSelect').attr('value', '${escolaridadForm.idExperienciaOffice}');
		      dijit.byId('idExperienciaInternetSelect').attr('value', '${escolaridadForm.idExperienciaInternet}');
		      dijit.byId('idExperienciaSelect').attr('value', '${escolaridadForm.idExperiencia}');
 			}
		});
	}
	function cargardoCatalogosDominio(){
		dominioStore.fetch({
			onComplete: function(items, request) {
		      if (items.length == 0) {
		         return;
		      }
		      dijit.byId('idDominioSelect').attr('value', '${escolaridadForm.idDominio}');
		      dijit.byId('idDominioConSelect').attr('value', '${escolaridadForm.idDominioCon}');
		      dijit.byId('idDominioHabSelect').attr('value', '${escolaridadForm.idDominioHab}');
		      dijit.byId('idDominioCompuSelect').attr('value', '${escolaridadForm.idDominioCompu}');
		      dijit.byId('idDominioOfficeSelect').attr('value', '${escolaridadForm.idDominioOffice}');
		      dijit.byId('idDominioInternetSelect').attr('value', '${escolaridadForm.idDominioInternet}');
	      	  dijit.byId('idDominioCmpSelect').attr('value', '${escolaridadForm.idDominioCmp}');
			}
		});
	}
	
	function cargardoCatalogosGrado(){	  			      
		var vGrado = '${escolaridadForm.idNivelEstudio}';
		estatusGradoStore.url = "${context}grados.do?method=situacionesAcademicas&idEscolaridad="+ vGrado;
		estatusGradoStore.close();
		estatusGradoStore.fetch({
         	onComplete: function(items, request) {
         		if (items.length == 0) return;
         		dijit.byId('idSituacionAcademicaSelect').set('value','${escolaridadForm.idSituacionAcademica}');
         		
         	}
   		});	
	}
	
	function cargardoCatalogosAcademico(){
		gradoAcademicoStore.fetch({
 			onComplete: function(items, request) {
		      if (items.length == 0) {
		         return;
		      }
		      var vGrado = '${escolaridadForm.idNivelEstudio}';
		      dijit.byId('idNivelEstudioSelect').attr('value', vGrado);
		      var vIdCatDep = depGrado[vGrado];
		      if(vIdCatDep != 0 || vIdCatDep != undefined) {
	          	carreraStore.url = '${context}grados.do?method=cargarCarrera' + '&' + 'idCatDep='+ vIdCatDep;
	          	carreraStore.close();
	          	carreraStore.fetch({
 					onComplete: function(items, request) {
						if (items.length == 0) {
				        	return;
				    	}
				    	dijit.byId('idCarreraEspecialidadSelect').attr('value', '${escolaridadForm.idCarreraEspecialidad}');
 					}
				});
			  }
 			}
		});
	}
//--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*

function chkSinEstudios(habilita) {
	var elements = dijit.byId('escolaridadForm').getDescendants();
	//Si no tiene estudios el formulario se deshabilita todo.
	for (i = 0; i < elements.length; i++) {
	var element = elements[i];
	if (element.type != 'hidden' && 
		element.type != 'button' && 
		element.name != 'hasEstudios') {
			//Habilita o deshabilita el widget
			setEnableWg(element.id, !habilita);
		}
	}
	//Habilita o deshabilita los checkbox de computacion basica y avanzada
	setEnableChk('hascomputacionBasica', habilita);
	setEnableChk('hascomputacionAvanzada', habilita);
	//Habilita o deshabilita los campos de compu basica y avanzada
	if (!habilita) {
		chkCmpBasica(getChecked('hascomputacionBasica'));
		chkCmpAvan(getChecked('hascomputacionAvanzada'));
	}
	//Oculta o muestra las ligas de multiregistro
	setDiv('hrefEstudios', !habilita);
	setDiv('hrefIdiomas', !habilita);
	setDiv('hrefConocimientos', !habilita);
	setDiv('hrefHabs', !habilita);
	if (habilita) {
		setDiv('hrefCmpAvan', !habilita);
		setEnableHTML('descripcion', !habilita);
	
	}
	setEnableHTML('descripcionCon', !habilita);
	//setEnableHTML('descripcionHab', !habilita);
	
}

function chkCmpBasica(habilita) {
	setEnableWg('idExperienciaCompuSelect', habilita);
	setEnableWg('idDominioCompuSelect', habilita);
	setEnableWg('idExperienciaOfficeSelect', habilita);
	setEnableWg('idDominioOfficeSelect', habilita);
	setEnableWg('idExperienciaInternetSelect', habilita);
	setEnableWg('idDominioInternetSelect', habilita);
}

function chkCmpAvan(habilita) {
	setEnableWg('softwareHardware', habilita);
	setEnableWg('idExperienciaSelect', habilita);
	setEnableWg('idDominioCmpSelect', habilita);
	setEnableHTML('descripcion', habilita);
	setDiv('hrefCmpAvan', habilita);
}

function setEnableWg(id, habilita) {
	if (habilita) { //Habilita Widget
		dijit.byId(id).attr('disabled', '');
		dojo.removeAttr(id, 'disabled');
	} else { //Deshabilita Widget
		dijit.byId(id).attr('disabled', 'disabled');
	}
}

function setEnableHTML(id, habilita) {
	document.getElementById(id).disabled = !habilita;
}


function setEnableChk(id, habilita) {
	document.getElementById(id).disabled = habilita;
}

function setChecked(id, chk) {
	document.getElementById(id).checked = chk;
}

function getChecked(id) {
	return document.getElementById(id).checked;
}

function setDiv(id, visible) {
	var vStyle = visible ? 'visible':'hidden';
	document.getElementById(id).style.visibility = vStyle;
}
	
	function envio(data){
		var res = dojo.fromJson(data);
		if (res.msg.type == 'ext') {
			//dijit.byId('idCandidatoGradoAcademico').set('value', res.idCandidatoGradoAcademico);
			//dijit.byId('idCandidatoIdioma').set('value', res.idCandidatoIdioma);
			//dijit.byId('idCandidatoConocimiento').set('value', res.idCandidatoConocimiento);
			//dijit.byId('idCandidatoHabilidad').set('value', res.idCandidatoHabilidad);
			//dijit.byId('idCandidatoCompuAvanzada').set('value', res.idCandidatoCompuAvanzada);
			location.replace('<%=context%>experiencia.do?method=init');
		}
	}

function validarTextArea(id) {
   	var txt = dijit.byId(id);  
	//var regExp = /^[\w\d\s\.áéíóúÁÉÍÓÚñÑ]{1,2000}$/; && txt.get('value') != ''
	var regExp = /^[\w\d\s\.áéíóúÁÉÍÓÚñÑ]{1,5}$/;
	if (!regExp.test(txt.get('value')) ) {
		mensaje('Descripcion contiene caracteres inválidos');
		txt.focus();
		return false;
	}
	return true;
}
//Desplegar ventana para captura de estudios adicionales
function openGradoWindow() {
  var newWin = window.open('<%=context + "grados.do?method=init"%>', "Grados","height=500,width=900,resizable=yes,scrollbars=yes");
}
//Desplegar ventana para captura de idiomas adicionales
function openIdiomaWindow() {
  var newWin = window.open('<%=context + "idiomas.do?method=init"%>', "Idiomas","height=500,width=900,resizable=yes,scrollbars=yes");
}
//Desplegar ventana para captura de conocimientos o habilidades adicionales
function openConocHabWindow(idTipo) {
  var newWin = window.open('<%=context + "conocsHabs.do?method=init&idTipo="%>'+idTipo, "Conocimientos","height=500,width=900,resizable=yes,scrollbars=yes");
}
//Desplegar ventana para captura de idiomas adicionales
function openCompuWindow() {
  var newWin = window.open('<%=context + "compuAvanzadas.do?method=init"%>', "CompuAvanzada","height=500,width=900,resizable=yes,scrollbars=yes");
}
function irA(opcion){
	if ((opcion > 0) && (opcion < 5)){
		var cv = ${estatusCV};
		cv ++; //para poder ver la ventana siguiente al estatus actual
		var tab = ${thisTabId};
		if ((cv >= opcion) && (tab != opcion) ){
			if (opcion == 1){
				location.replace('<%=response.encodeURL(context+"perfil.do?method=init")%>');
			}
			if (opcion == 2){
				location.replace('response.encodeURL(context+"escolaridad.do?method=init")%>');
			}
			if (opcion == 3){
				location.replace('<%=response.encodeURL(context+"experiencia.do?method=init")%>');
			}
			if (opcion == 4){
				location.replace('<%=response.encodeURL(context+"expectativa.do?method=init")%>');
			}
			//document.perfilForm.submit();
		}
		if (cv < opcion ){
			if (tab == (opcion -1)){
				mensaje('Debe completar el formulario actual');
			}else {
				mensaje('Debe completar el formulario #' + cv); //ultimo formulario completado
			}
			
		}
	}
}

	function cancelarPerfil() {
		var answer = confirm('Los datos no guardados se perderán ¿Continuar?');
		if (answer){
			location.replace('<c:url value="/miEspacioCandidato.do"/>');
		}
	}

	function caracteresValidos(e){
    	var tecla_codigo = (document.all) ? e.keyCode : e.which;
    	if(tecla_codigo==8 || tecla_codigo==0) return true;
    	if (tecla_codigo==225 || tecla_codigo==233 || tecla_codigo==237 || tecla_codigo==243 || tecla_codigo==250) return true; //vocales minusculas con acento
    	if (tecla_codigo==193 || tecla_codigo==201 || tecla_codigo==205 || tecla_codigo==211 || tecla_codigo==218) return true; //vocales mayuzculas con acento
    	if (tecla_codigo==209 || tecla_codigo==241 ) return true; //Ñ y ñ
   		
    	var patron =/[0-9\-a-zA-Z_ .,:;#]/;
    	tecla_valor = String.fromCharCode(tecla_codigo);
    	return patron.test(tecla_valor);

    }

	function validarIdMensaje() {
		var mensajeError =  '';	
			mensajeError += validarSelect('idCarreraEspecialidadSelect','Favor de Proporcionar la Carrera o Especialidad.');
		return mensajeError;
	}
	function validarPorIdConCheck(id,mensajeValidacion,bandera){
		if (bandera && !dijit.byId(id).isValid())
			return '<li>'+mensajeValidacion + '</li>';
		else
			return "";
	}
	function validarSelect(id,mensajeValidacion) {
		if (dijit.byId(id).get('item')== null)
			return '<li>'+mensajeValidacion + '</li>';
		else
			return "";
	}
	function validarSelectConCheck(id,mensajeValidacion,bandera){
		if (bandera && dijit.byId(id).get('item')== null)
			return '<li>'+mensajeValidacion + '</li>';
		else
			return "";
	}
	function validarPorId(id,mensajeValidacion) {
		if(!dijit.byId(id).isValid())
			return '<li>'+mensajeValidacion + "</li>";
		else
			return "";
	}
	function validarForma() {
		return validarDatoPorDato() && validateLanguageList();
	}
	function mensaje(mensaje){	
		//dojo.byId('mensaje').innerHTML = mensaje;
		//dijit.byId('MensajeAlert').show();
		message(mensaje);
	}
	function limpiaConocimientosComp(){	
		
		if(dijit.byId('conocimientoCompNinguno').get("checked")){
			dijit.byId('conocimientoCompProcesadorTxt').set("checked",false);
			dijit.byId('conocimientoCompHojaCal').set("checked",false);
			dijit.byId('conocimientoCompInternet').set("checked",false);
			dijit.byId('conocimientoCompRedes').set("checked",false);
			dijit.byId('conocimientoCompOtros').set("checked",false);
		}
	}
	
	function limpiaNingunConocimientosComp(check) {
		if (check)
			dijit.byId('conocimientoCompNinguno').set("checked",false);
	}

	
	function validarDatoPorDato() {
		return validateData();
	}
	function validateData() {
		var knowledge = document.getElementById('conocimiento');
		var desCon = document.getElementById('descripcionCon');
		var experienceCon = document.getElementById('idExperienciaConSelect');

		if (dijit.byId('idNivelEstudioSelect').get('item')== null || dijit.byId('idNivelEstudioSelect').get('item').value == 0) {
    		message('Debe seleccionar último grado de estudios.');
			return false;
    	}
    	if (dijit.byId('idCarreraEspecialidadSelect').get('item')== null || dijit.byId('idCarreraEspecialidadSelect').get('item').value == 0) {
    		message('Debe seleccionar carrera o especialidad.');
			return false;
    	}
    	//validar idioma
    	if(!validateLangue1()){
    		return false;
    		
    	}
		
    	//validar los idiomas adicionales
    	if(!validateLangue(1)){
    		return false;
    	}
    	if(!validateLangue(2)){
    		return false;
    	}
    	if(!validateLangue(3)){
    		return false;
    	}
    	
		if (knowledge.value && experienceCon.value == 0) {
			message('Es necesario indicar la experiencia del conocimiento.');
			return false;
		}    	
    	if (knowledge.value && !desCon.value) {
			message('Es necesario indicar la descripción del conocimiento.');
			desCon.focus();
			return false;
		}

		var ischecked = false;
		var habilidaChecks = document.getElementsByName('idHabilidad');
		var numeroHabilidades = 0;
		
		if (habilidaChecks && habilidaChecks.length){
			for (var i=0;i<habilidaChecks.length;i++) {
				if (habilidaChecks[i].checked) {
					numeroHabilidades++;
					
				}
			}			
		}
		
		if(numeroHabilidades>0)ischecked = true;
		if (!ischecked){
			message('Debe elegir por lo menos una habilidad');
			return false;
		}
		if(numeroHabilidades>5){
			message('Debe seleccionar máximo 5 habilidades y actitudes que te caracterizan.');
			return false;
		}

		if (validaProspera() == false) {
			return false;
		}
    	
		return true;
	}
	
	function validateLangue1(){
		if(!validaCampoSelect('idIdioma')) return false;
		if(!validaCampoNoRequerido('idDominioIdioma')) return false;
		if(!validaCampoNoRequerido('idCertificacionIdioma')) return false;
		return true;
		
	}
	
	function validateLangue(idStore){
		if(!validaCampoNoRequerido('selectLanguage_'+idStore)) return false;
		if(!validaCampoNoRequerido('dominio_'+idStore)) return false;
		if(!validaCampoNoRequerido('certification_'+idStore)) return false;
		return true;
		
	}
	
	function validarRadio(nameGrp) {
		var elements = eval('document.escolaridadForm.'+nameGrp);
		var radioChecked = false;
		for (i = 0; i < elements.length; i++) {
			if (elements[i].checked) {
				radioChecked = elements[i].checked;
				break;
			}
		}
		return radioChecked;
	}
	
	function validateLanguageList() {
		var idIdiomaSelectAdd1 = dijit.byId('idIdiomaSelectAdd1');
		var idIdiomaSelectAdd2 = dijit.byId('idIdiomaSelectAdd2');
		var idIdiomaSelectAdd3 = dijit.byId('idIdiomaSelectAdd3');
		if (idIdiomaSelectAdd1 != undefined) {
			if (idIdiomaSelectAdd2 != undefined) {
				if (idIdiomaSelectAdd3 != undefined)
					return (validateLanguage(1) && validateLanguage(2) && validateLanguage(3));
				else
					return (validateLanguage(1) && validateLanguage(2));
			}else {
				return validateLanguage(1);
			}
		}
		return true;
	}
	function validateLanguage(index) {
		var idIdiomaSelectAddIndex = dijit.byId('idIdiomaSelectAdd' + index).get('value');
        if (idIdiomaSelectAddIndex == 1) {
        	message('Debe seleccionar el idioma.');
			return false;
        }
         return true;
	}
	function doCancel() {
		/**if (confirm('Los datos no guardados se perderán ¿Continuar?')) {
			document.escolaridadForm.method.value = 'init';
			document.escolaridadForm.submit();
		}**/
		messageRutaCancel('${perfilForm.errorMessageTab}','<%=response.encodeURL(context+"perfil.do?method=init")%>');
	}
	function doSubmitEsc() {
		document.getElementById('btnGuardarPagina').disabled = true;
		//document.escolaridadForm.btnGuardarPagina.disabled = true;
		if (validateData() && validateKnowledgeList()) { 		
	   		//document.escolaridadForm.elements[0].value = 'registrar';	   		
	   		dijit.byId('idNivelEstudio').set('value', dijit.byId('idNivelEstudioSelect').get('value'));
	   		dijit.byId('idCarreraEspecialidad').set('value', dijit.byId('idCarreraEspecialidadSelect').get('value'));
	   		dijit.byId('idSituacionAcademica').set('value', dijit.byId('idSituacionAcademicaSelect').get('value'));
	   		dijit.byId('idIdiomaSelect').set('value', dijit.byId('idIdioma').get('value'));
 		   	dijit.byId('idCertificacion').set('value', dijit.byId('idCertificacionIdioma').get('value'));
 		   	dijit.byId('idDominio').set('value', dijit.byId('idDominioIdioma').get('value'));
 		   	if(dijit.byId('idExperienciaCon') != undefined){
	   			dijit.byId('idExperienciaCon').set('value', dijit.byId('idExperienciaConSelect').get('value'));
 		   	}
 		   	
		   	if (dijit.byId('idIdiomaAdd1') != undefined) {
		   		dijit.byId('idIdiomaAdd1').set('value', dijit.byId('idIdiomaSelectAdd1').get('value'));
	   			dijit.byId('idCertificacionAdd1').set('value', dijit.byId('idCertificacionSelectAdd1').get('value'));
	   		}
	   		if (dijit.byId('idDominioAdd1') != undefined) {
		   		dijit.byId('idDominioAdd1').set('value', dijit.byId('idDominioSelectAdd1').get('value'));
		   	}
	   		if (dijit.byId('idIdiomaAdd2') != undefined) {
		   		dijit.byId('idIdiomaAdd2').set('value', dijit.byId('idIdiomaSelectAdd2').get('value'));
		   	}
		   	if (dijit.byId('idIdiomaAdd3') != undefined) {
		   		dijit.byId('idIdiomaAdd3').set('value', dijit.byId('idIdiomaSelectAdd3').get('value'));
		   	}
		   	
		   	dojo.xhrPost( {
				url: 'escolaridad.do?method=registrar',
			  	form:'escolaridadForm',
			  	timeout:180000,
			  	load: function(data) {  		
			  					  		
			  		var res = dojo.fromJson(data);
					//alert(res.msg.message);
					if (res.msg.type == 'ext') {
						
						messageRuta2BtnEditarPerfil(res.msg.message,"<%=response.encodeURL(context+"experiencia.do?method=init")%>","<%=response.encodeURL(request.getContextPath()+"/ofertasPerfiles.do?method=init&tipoConsulta=1")%>");
						//envio(data);
					}
					else
					{
						message(res.msg.message);	
					}
					
					//setTimeout("envio('"+data+"');",5000);						
			    }
			});
		} else {
			document.getElementById('btnGuardarPagina').disabled = false;
			//document.escolaridadForm.btnGuardarPagina.disabled = false;
			return false;
		}
	}
	
	
/*
	function setLangDomain() {
    	var idCertificacionAdd = dijit.byId('idCertificacionSelect').get('value');
    	if (idCertificacionAdd != 2) {
    		 dijit.byId('idDominioSelect').attr('value', 3);
    		 dijit.byId('idDominioSelect').attr('disabled', true);
    	}
    	if (idCertificacionAdd == 2) {
    		 dijit.byId('idDominioSelect').attr('disabled', false);
    	}
    }*/

    function filSelectCertification() {
  		var vIdioma = dijit.byId('idIdiomaSelectAdd').get('value');
  		var vCatDep = depIdioma[vIdioma];
  		certificacionStoreAdd.url = '${context}idiomas.do?method=cargarCertificacion' + '&' + 'idCatDep=' + vCatDep;
        certificacionStoreAdd.close();
  	}
  	function loadDominio() {
		dominioStoreAdd.url='${context}idiomas.do?method=cargarDominio';
		dominioStoreAdd.close();
        dominioStoreAdd.fetch({
        	onComplete: function(items, request) {                  	
            	if (items.length == 0) return;         
              		dijit.byId('idDominioSelectAdd').attr('value', items[0].value);
            }
        });
  	}

    function doSubmitAdd() {
    	if(isValidLang()) {
	    	var idIdiomaAdd = dijit.byId('idIdiomaSelectAdd').get('value');
	    	var idCertificacionAdd = dijit.byId('idCertificacionSelectAdd').get('value');
	    	var idDominioAdd = dijit.byId('idDominioSelectAdd').get('value');
	    	document.getElementById('languagesadd').style.display = 'none';
	    	dojo.xhrPost( {
				url: 'idiomas.do?method=saveAddLang&idIdiomaAdd='+idIdiomaAdd+'&idCertificacionAdd='+idCertificacionAdd+'&idDominioAdd='+idDominioAdd,
			  	preventCache: true,
			  	form:'escolaridadForm',
			  	sync: true,
			  	timeout:180000,
			  	load: function(data) {
				    document.escolaridadForm.idIdiomaSelectAdd.value='';
					document.escolaridadForm.idCertificacionSelectAdd.value='';
					document.escolaridadForm.idDominioSelectAdd.value='';
					dojo.byId('idiomas bloque').innerHTML=data;
					dojo.parser.parse("idiomas bloque");
			    }
			 });
		 }
    }
    function deleteLang(idLang) {
		dojo.xhrGet({url: 'idiomas.do?method=deleteLang&idLang='+idLang, form:'escolaridadForm', sync: true, timeout:180000, 
					  load: function(data) {
						dojo.byId('idiomas bloque').innerHTML=data;
						dojo.parser.parse("idiomas bloque");
					  }});
	}
    function doDeleteLang(idLang) {
		if (confirm('Esta usted seguro de eliminar idioma')) {
			deleteLang(idLang);
		}	
	}   
    function togglelan() {
		document.getElementById('languagesadd').style.display = 'block';
    }
    function isValidLang() {
    	if (dijit.byId('idIdiomaSelectAdd') == null || dijit.byId('idIdiomaSelectAdd').get('value') == 1) {
    		message('Debe seleccionar idioma.');
			return false;
    	}
    	if (dijit.byId('idCertificacionSelectAdd') == null || dijit.byId('idCertificacionSelectAdd').get('value') == 0) {
    		message('Debe seleccionar certificación.');
			return false;
    	}
    	return true;
    }
    
   /* function setDomain() {
    	var idCertificacionAdd = dijit.byId('idCertificacionSelectAdd').get('value');
    	if (idCertificacionAdd != 2) {
    		 dijit.byId('idDominioSelectAdd').attr('value', 3);
    		 dijit.byId('idDominioSelectAdd').attr('disabled', true);
    	}
    }*/
    
    function cleanNone() {
		dijit.byId('conocimientoCompNinguno').set("checked",false);

    }
    /*
    		var vIdIdiomaDep = depIdioma[vIdioma];
		setLabelToLabelAndRequerido('dominioSelect', 'labelDominioSelect',
				'Dominio del idioma', false);

		

			if (vIdioma == 1) {
				// Cuando seleciona en los opciones ninguna
				// debe desactivar todo los combos
				//vaciar el store para asegurar que los certificaciones se cargan correcto

				dominioStore.url = '';

				desactivaSelectConValor('dominioSelect', 0);

			} else {

				enableSelectConValor('dominioSelect', '');
				setLabelToLabelAndRequerido('dominioSelect',
						'labelDominioSelect', 'Dominio del idioma', true);
				dominioStore.url = "${context}registro.do?method=dominios";

			}

			dijit.byId('dominioSelect').reset();
			dominioStore.close();

    */
    function actualizaDominio(){
    	
    	var idIdioma = dijit.byId('idIdioma').get('value');

   // 	setLabelToLabelAndRequerido('idDominioIdioma','idLabelDominioIdioma','Dominio del idioma',true);
    	if(idIdioma  == 1 ){
    		desactivarCertifiaciones();
    		dominioStore.url = '';
    		dominioStore.close();
    		//dijit.byId('idDominioIdioma').reset();
    	
    		dijit.byId('idDominioIdioma').set('value','0');
			dijit.byId('idDominioIdioma').attr('value', 0); 
			desactivaSelectConValor('idDominioIdioma','');
			setLabelToLabelAndRequerido('idDominioIdioma','idLabelDominioIdioma','Dominio del idioma',false);
			
			
			//dijit.byId('idDominioIdioma').reset();

			    
    	}
    	else
    	{
    		desactivarCertifiaciones();
    		dominioStore.url = '';
    		dominioStore.close();
    		dijit.byId('idDominioIdioma').reset();
		   	dominioStore.url = '${context}idiomas.do?method=cargarDominio';
    		dominioStore.close();
    		enableSelectConValor('idDominioIdioma', 0);
    		
    		//dijit.byId('idDominioIdioma').set('value','0');
			dijit.byId('idDominioIdioma').attr('value', 0); 
    		//dijit.byId('idDominioIdioma').reset();
    		setLabelToLabelAndRequerido('idDominioIdioma','idLabelDominioIdioma','Dominio del idioma',true);
			
    	}

    
    	
    }
    
    function validaCampoNoRequerido(campo) {
		var control = dijit.byId(campo);
		if(isDefinedObject(control)){
			
			var requerido = control.get('required');
			if (!control.validate()) {
				dijit.byId(campo).focus();
				dojo.byId(campo).blur();
				alertMsg(control.get("missingMessage"));
				
				return false;
			}
		}
		return true;
	}
    
    function actualizaCertificacionesAdd(store){
    	var vIdioma = dijit.byId('idIdiomaAdd').get('value');
		//Necesitamos el valor guardado del catalogo que es referente a su idoma no lo de la lista
		var vIdIdiomaDep = depIdioma[vIdioma];
		var vDominio = dijit.byId('idDominioIdiomaAdd').get('value');

		certificacionAddStore.url = '';

		
		if (vIdioma == 1) {
			desactivaSelectConValor('idCertificacionIdiomaAdd','');
			setLabelToLabelAndRequerido('idCertificacionIdiomaAdd',
					'idLabelCertificacionIdiomaAdd', '¿Cuentas con certificación?',
					false);
		} else {
			if (vDominio == 3 || vDominio == 2) {
				certificacionAddStore.url = "${context}idiomas.do?method=certificaciones&idCatDep="
						+ vIdIdiomaDep;
				enableSelectConValor('idCertificacionIdiomaAdd', 0);
				setLabelToLabelAndRequerido('idCertificacionIdiomaAdd',
						'idLabelCertificacionIdiomaAdd',
						'¿Cuentas con certificación?', true);
				

			} else {
				desactivaSelectConValor('idCertificacionIdiomaAdd', '');
				setLabelToLabelAndRequerido('idCertificacionIdiomaAdd',
						'idLabelCertificacionIdiomaAdd', '¿Cuentas con certificación?',
						false);
			}

		}
		dijit.byId('idCertificacionIdiomaAdd').reset();
		certificacionAddStore.close();
		
    }
    
    function desactivarCertifiaciones(){
   
    	certificacionStore.url = '';
		certificacionStore.close();
		dijit.byId('idCertificacionIdioma').reset();

		desactivaSelectConValor('idCertificacionIdioma','');
		setLabelToLabelAndRequerido('idCertificacionIdioma',
				'idLabelCertificacionIdioma', '¿Cuentas con certificación?',
				false);
		
    }
    
    function activarCertificaciones(){
    	var vIdioma = dijit.byId('idIdioma').get('value');
		//Necesitamos el valor guardado del catalogo que es referente a su idoma no lo de la lista
		var vIdIdiomaDep = depIdioma[vIdioma];
		message(vIdIdiomaDep);
    	certificacionStore.url ='${context}idiomas.do?method=certificaciones&idCatDep='+ vIdIdiomaDep;
	

    	enableSelectConValor('idCertificacionIdioma', 1);
    	setLabelToLabelAndRequerido('idCertificacionIdioma',
    			'idLabelCertificacionIdioma',
    			'¿Cuentas con certificación?', true);
		certificacionStore.close();
	//dijit.byId('idCertificacionIdioma').reset();
    }
    
    function activarCertificaciones(idStore,vIdioma){
    	var vIdioma = dijit.byId('idIdioma').get('value');
		//Necesitamos el valor guardado del catalogo que es referente a su idoma no lo de la lista
		var vIdIdiomaDep = depIdioma[vIdioma];
    	certificacionStore.url = '${context}idiomas.do?method=certificaciones&idCatDep='+ vIdIdiomaDep;
		

    	enableSelectConValor('idCertificacionIdioma', 1);
    	setLabelToLabelAndRequerido('idCertificacionIdioma',
    			'idLabelCertificacionIdioma',
    			'¿Cuentas con certificación?', true);
	certificacionStore.close();
	//dijit.byId('idCertificacionIdioma').reset();
    }
    
    function actualizaCertificaciones(){
    	
    	var vIdioma = dijit.byId('idIdioma').get('value');
		var vDominio = dijit.byId('idDominioIdioma').get('value');

		certificacionStore.url = '';

		setLabelToLabelAndRequerido('idCertificacionIdioma',
				'idLabelCertificacionIdioma', '¿Cuentas con certificación?',
				false);
		if (vIdioma == 1) {
			desactivaSelectConValor('idCertificacionIdioma', 1);
		} else {
			if (vDominio == 3 || vDominio == 2) {
				activarCertificaciones();

			} else {
				desactivarCertifiaciones();
				
				
			}

		}
		
		
    }
    
    function actulizaCertificaciones() {
		document.getElementById('idCertificacionIdioma').readOnly=false;
    	dijit.byId('idDominioIdioma').disabled=false;
    	document.getElementById('idDominioIdioma').readOnly=false;
    	dijit.byId('idDominioIdioma').set('value','');
		var vIdioma = dijit.byId('idIdioma').get('value');
		var vIdIdiomaDep = depIdioma[vIdioma];
        if(vIdIdiomaDep && vIdIdiomaDep != 0 ) {
        	dijit.byId('idCertificacionIdioma').set('value','');
        	certificacionStore.url = '${context}idiomas.do?method=certificaciones&idCatDep='+ vIdIdiomaDep;
        	certificacionStore.close();
		}
        if(vIdioma==1){
        	document.getElementById('idCertificacionIdioma').readOnly=true;
        	dijit.byId('idDominioIdioma').set('value','');
        	dijit.byId('idDominioIdioma').disabled=true;
        	document.getElementById('idDominioIdioma').readOnly=true;
        	dijit.byId('idCertificacionIdioma').attr('value', 2);
        }else{
        	document.getElementById('idCertificacionIdioma').readOnly=false;
        	dijit.byId('idDominioIdioma').disabled=false;
        	document.getElementById('idDominioIdioma').readOnly=false;
        	dijit.byId	('idDominioIdioma').set('value','');	
        }   
	}
   /*
	function actualizaDominio(idCertificacion) {
		dijit.byId('idDominioIdioma').set('value','');
		if (idCertificacion>0&&idCertificacion!=2) {
			dijit.byId('idDominioIdioma').disabled=true;
			document.getElementById('idDominioIdioma').readOnly=true;
			dijit.byId('idDominioIdioma').attr('value', 3);
		}else {
			if (dijit.byId('idIdioma').get('value')!=1){
				dijit.byId('idDominioIdioma').disabled=false;
				document.getElementById('idDominioIdioma').readOnly=false;
			}
		}
	}*/
	
	

	
	//habilitar una lista
	function enableSelectConValor(selectList, valor) {
		dijit.byId(selectList).set('value', valor);
		//dijit.byId(selectList).reset();
		dijit.byId(selectList).readOnly = false;
		dijit.byId(selectList).disabled = false;
		var originalValidator = dijit.byId(selectList).validator;
		dijit.byId(selectList).validator = function() {
			return true;
		};
		dijit.byId(selectList).validate();

		dijit.byId(selectList).validator = originalValidator;
	}

	//deshabilitar una lista
	function desactivaSelectConValor(selectList, valor) {
		dijit.byId(selectList).attr('value', valor);
		

		// Se apaga el color rojo del control
		var originalValidator = dijit.byId(selectList).validator;
		dijit.byId(selectList).validator = function() {
			return false;
		};
		dijit.byId(selectList).validate();
		dijit.byId(selectList).validator = originalValidator;
		dijit.byId(selectList).reset();
		document.getElementById(selectList).value == valor;
		dijit.byId(selectList).readOnly = true;
		dijit.byId(selectList).disabled = true;
		

	}

	function doSubmitAddIdioma() {	
		var idIdiomaPrincipal = dijit.byId('idIdioma').get('value');
		var idCertificacionIdiomaPrincipal = dijit.byId('idCertificacionIdioma').get('value');
		var idIdiomaAdd = dijit.byId('idIdiomaAdd').get('value');
		var idCertificacionIdiomaAdd = dijit.byId('idCertificacionIdiomaAdd').get('value');
		if ((idIdiomaPrincipal==idIdiomaAdd) && (idCertificacionIdiomaPrincipal==idCertificacionIdiomaAdd)){
			message('Los idiomas adicionales deben ser diferentes al idioma principal');
			return false;
		}
		
		if (!validaCampoSelect('idIdiomaAdd')) return false;	
		if(!validaCampoSelect('idDominioIdiomaAdd')) return false;
		if (!validaCampoNoRequerido('idCertificacionIdiomaAdd')) return false;
		/*if (dijit.byId('idCertificacionIdiomaAdd').get('value')==2&&dijit.byId('idIdiomaAdd').get('value')!=1) {
			if (!validaCampoSelect('idDominioIdiomaAdd')) return false;
		}*/
		addIdioma();
	}
	function addIdioma() {
		var idIdiomaAdd = dijit.byId('idIdiomaAdd').get('value');
		var idCertificacionIdiomaAdd = dijit.byId('idCertificacionIdiomaAdd').get('value');
		var idDominioIdiomaAdd = dijit.byId('idDominioIdiomaAdd').get('value');
		document.getElementById('idiomaAdicional').style.display = 'none';
		dojo.xhrPost({url: 'escolaridad.do?method=idiomaAdicional&idIdiomaAdd='+idIdiomaAdd+'&idCertificacionIdiomaAdd='+idCertificacionIdiomaAdd+'&idDominioIdiomaAdd='+idDominioIdiomaAdd,preventCache: true, form:'escolaridadForm', sync: true, timeout:180000, 
			load: function(data) {
				dijit.byId('idIdiomaAdd').set('value','');
				dijit.byId('idCertificacionIdiomaAdd').set('value','');
				dijit.byId('idDominioIdiomaAdd').set('value','');				
				clearAddSetAgregaIdioma(data);
				
			}});	
		/*
		if (document.getElementById('idIdiomaAdicional_1')) {
			actulizaCertificacionesAdd(1,document.getElementById('idIdiomaAdicional_1').value);
			
		}		
		if (document.getElementById('idIdiomaAdicional_2')) {
			actulizaCertificacionesAdd(2,document.getElementById('idIdiomaAdicional_2').value);
		}			
		if(document.getElementById('idIdiomaAdicional_3')){
			actulizaCertificacionesAdd(3,document.getElementById('idIdiomaAdicional_3').value);
		}*/
	}
	function eliminarIdioma(idIdioma,idCertificacion) {
		var idIdiomaEliminar=idIdioma;
		var idCertificacionEliminar=idCertificacion;
		document.getElementById('idiomaAdicional').style.display = 'none';
		dojo.xhrPost({url: 'escolaridad.do?method=eliminarIdiomaAdicional&idIdiomaEliminar='+idIdiomaEliminar+'&idCertificacionIdiomaEliminar='+idCertificacionEliminar,preventCache: true, form:'escolaridadForm', sync: true, timeout:180000, 
			load: function(data) {
				dijit.byId('idIdiomaAdd').set('value','');
				dijit.byId('idCertificacionIdiomaAdd').set('value','');
				dijit.byId('idDominioIdiomaAdd').set('value','');
				clearAddSetAgregaIdioma(data);
			}});
	}
	
	function toggleIdioma() {
		document.getElementById('idiomaAdicional').style.display = 'block';
		document.getElementById('btnAgregarIdioma').style.display = 'none';
	}
	
	function actulizaCertificacionesAdd(idStore,vIdioma) {
		//actualiza los adicionales desde language.jsp
		
		if(idStore>0){
			document.getElementById('idiomaAdicional').style.display = 'none';
			dojo.xhrPost({url: 'escolaridad.do?method=idiomaCertificacion&vIdioma='+vIdioma+'&idMultiRegistro='+idStore,preventCache: true, form:'escolaridadForm', sync: true, timeout:180000, 
				load: function(data) {
					
					if(isDefinedObject(dijit.byId('idIdiomaAdd'))){
						dijit.byId('idIdiomaAdd').set('value','');
						dijit.byId('idCertificacionIdiomaAdd').set('value','');
						dijit.byId('idDominioIdiomaAdd').set('value','');
					}
					//message(data);
					//dojo.byId('').destroyRecursive();
					clearAddSetAgregaIdioma(data)
					//dojo.parser.instantiate([dojo.byId('agregaIdioma')]);
				}});
		}
		var vIdIdiomaDep = depIdioma[vIdioma];
	 	if (vIdIdiomaDep && vIdIdiomaDep != 0 ) {
	    	if (idStore==0){
	    		certificacionAddStore.url = '${context}idiomas.do?method=certificaciones&idCatDep='+ vIdIdiomaDep;
	    		certificacionAddStore.close();
	    	}
		}
	    if (vIdioma==1) {
	  		document.getElementById('idCertificacionIdiomaAdd').readOnly=true;
			document.getElementById('idDominioIdiomaAdd').readOnly=true;
			dijit.byId('idDominioIdiomaAdd').disabled=true;
     		dijit.byId('idCertificacionIdiomaAdd').attr('value', 2);
      	}else {
      		document.getElementById('idCertificacionIdiomaAdd').readOnly=false;
    		document.getElementById('idDominioIdiomaAdd').readOnly=false;
    		dijit.byId('idDominioIdiomaAdd').disabled=false;
     		dijit.byId('idDominioIdiomaAdd').reset();
      	}	   
	}
	
	function clearAddSetAgregaIdioma(data){
		
		 clearWidgetsAndAddHtml('agregaIdioma',data);
		 
	}
	
	
	/*
	function actualizaCertificacionAdd(idStore,idCertificacion){
		if (idStore>0){				
			var vIdCertificacion=idCertificacion;
			document.getElementById('idiomaAdicional').style.display = 'none';
			dojo.xhrPost({url: 'escolaridad.do?method=idiomaDominio&vIdCertificacion='+vIdCertificacion+'&idMultiRegistro='+idStore,preventCache: true, form:'escolaridadForm', sync: true, timeout:180000, 
				load: function(data) {
					dijit.byId('idIdiomaAdd').set('value','');
					dijit.byId('idCertificacionIdiomaAdd').set('value','');
					dijit.byId('idDominioIdiomaAdd').set('value','');
					dojo.byId('agregaIdioma').innerHTML=data;
					//dojo.parser.parse('agregaIdioma');			
					dojo.parser.instantiate([dojo.byId('agregaIdioma')]);
				}});
		}else {
			if (idCertificacion>0&&idCertificacion!=2){
				document.getElementById('idDominioIdiomaAdd').readOnly=true;
				dijit.byId('idDominioIdiomaAdd').disabled=true;
				dijit.byId('idDominioIdiomaAdd').set('value', 3);
			}else {
				if (dijit.byId('idIdiomaAdd').get('value')!=1) {
					dijit.byId('idDominioIdiomaAdd').disabled=false;
					document.getElementById('idDominioIdioma').readOnly=false;
				}else {
					dijit.byId('idDominioIdiomaAdd').reset();
				}
			}
		}
	}
	*/
	function actualizaCertificacionAdd(idStore,value){
		if(idStore>0){
			var vIdioma = dijit.byId('selectLanguage_'+idStore).get('value');
			
			document.getElementById('idiomaAdicional').style.display = 'none';
			dojo.xhrPost({url: 'escolaridad.do?method=idiomaCertificacionAdd&vIdioma='+vIdioma+'&idMultiRegistro='+idStore+'&idDominio='+value ,preventCache: true, form:'escolaridadForm', sync: true, timeout:180000, 
				load: function(data) {
					if(isDefinedObject(dijit.byId('idIdiomaAdd'))){
						dijit.byId('idIdiomaAdd').set('value','');
						dijit.byId('idCertificacionIdiomaAdd').set('value','');
						dijit.byId('idDominioIdiomaAdd').set('value','');
					}
					//dojo.byId('agregaIdioma').innerHTML=data;
					//dojo.parser.parse('agregaIdioma');			
					//dojo.parser.instantiate([dojo.byId('agregaIdioma')]);
					clearWidgetsAndAddHtml('agregaIdioma',data);
				}});
			
		}
	}
	
	function actualizarIdiomaAdd(idStore,value){
		if(idStore>0){
			document.getElementById('idiomaAdicional').style.display = 'none';
			dojo.xhrPost({url: 'escolaridad.do?method=guardarFormCertificacionAdd&idMultiRegistro='+idStore+'&idCertificacion='+value ,preventCache: true, form:'escolaridadForm', sync: true, timeout:180000, 
				load: function(data) {
					if(isDefinedObject(dijit.byId('idIdiomaAdd'))){
						dijit.byId('idIdiomaAdd').set('value','');
						dijit.byId('idCertificacionIdiomaAdd').set('value','');
						dijit.byId('idDominioIdiomaAdd').set('value','');
					}
					//dojo.byId('agregaIdioma').innerHTML=data;
					//dojo.parser.parse('agregaIdioma');			
					//dojo.parser.instantiate([dojo.byId('agregaIdioma')]);
					clearWidgetsAndAddHtml('agregaIdioma',data);
				}});
			
		}
	}
	
	
	function actualizaDominioAdd0(idStore){
		actualizaDominioAdd(idStore,0);
	}
	
	function actualizaDominioAdd(idStore,value){
	
		
		if(idStore>0){
			var vIdioma = dijit.byId('selectLanguage_'+idStore).get('value');
			
			
			document.getElementById('idiomaAdicional').style.display = 'none';
			dojo.xhrPost({url: 'escolaridad.do?method=idiomaDominio&vIdioma='+vIdioma+'&idMultiRegistro='+idStore+'&idDominio='+value ,preventCache: true, form:'escolaridadForm', sync: true, timeout:180000, 
				load: function(data) {
					dijit.byId('idIdiomaAdd').set('value','');
					dijit.byId('idCertificacionIdiomaAdd').set('value','');
					dijit.byId('idDominioIdiomaAdd').set('value','');
					
					
					//dojo.byId('agregaIdioma').innerHTML=data;
					//dojo.parser.parse('agregaIdioma');			
					//dojo.parser.instantiate([dojo.byId('agregaIdioma')]);
					
					clearWidgetsAndAddHtml('agregaIdioma',data);
				}});
		}
		else{
			var idIdioma = dijit.byId('idIdiomaAdd').get('value');
			
			if (idIdioma == 1) {
				//dijit.byId('idDominioIdioma').disabled=true;
				setLabelToLabelAndRequerido('idIdiomaAdd','idLabelIdiomaAdd','Idioma adicional al nativo',false);
				setLabelToLabelAndRequerido('idDominioIdiomaAdd','idLabelDominioIdiomaAdd','Dominio del idioma',false);
				//document.getElementById('idDominioIdioma').readOnly=true;
				desactivaSelectConValor('idDominioIdiomaAdd','');
				
				
			}else {
					enableSelectConValor('idDominioIdiomaAdd','');
					//dijit.byId('idDominioIdioma').disabled=false;
					setLabelToLabelAndRequerido('idIdiomaAdd','idLabelIdiomaAdd','Idioma adicional al nativo',true);
					setLabelToLabelAndRequerido('idDominioIdiomaAdd','idLabelDominioIdiomaAdd','Dominio del idioma',true);
	//				document.getElementById('idDominioIdioma').readOnly=false;
				
			}
		}
	}
	/*function actualizaDominioAdd(idStore,idCertificacion){
		if (idStore>0){				
			var vIdCertificacion=idCertificacion;
			document.getElementById('idiomaAdicional').style.display = 'none';
			dojo.xhrPost({url: 'escolaridad.do?method=idiomaDominio&vIdCertificacion='+vIdCertificacion+'&idMultiRegistro='+idStore,preventCache: true, form:'escolaridadForm', sync: true, timeout:180000, 
				load: function(data) {
					dijit.byId('idIdiomaAdd').set('value','');
					dijit.byId('idCertificacionIdiomaAdd').set('value','');
					dijit.byId('idDominioIdiomaAdd').set('value','');
					dojo.byId('agregaIdioma').innerHTML=data;
					//dojo.parser.parse('agregaIdioma');			
					dojo.parser.instantiate([dojo.byId('agregaIdioma')]);
				}});
		}else {
			if (idCertificacion>0&&idCertificacion!=2){
				document.getElementById('idDominioIdiomaAdd').readOnly=true;
				dijit.byId('idDominioIdiomaAdd').disabled=true;
				dijit.byId('idDominioIdiomaAdd').set('value', 3);
			}else {
				if (dijit.byId('idIdiomaAdd').get('value')!=1) {
					dijit.byId('idDominioIdiomaAdd').disabled=false;
					document.getElementById('idDominioIdioma').readOnly=false;
				}else {
					dijit.byId('idDominioIdiomaAdd').reset();
				}
			}
		}
	}
	*/

	function actualizaValorAd(campo,valor) {
	
		if (valor!=''){
			
			document.getElementById(campo).value=valor;
		} 			
    }
	function load(){
	
		var vIdioma = '${escolaridadForm.idIdioma}';
		var vIdIdiomaDep = depIdioma[vIdioma];
		if (vIdIdiomaDep && vIdIdiomaDep != 0 ) {
    		certificacionStore.url = '${context}idiomas.do?method=certificaciones&idCatDep='+ vIdIdiomaDep;
    		certificacionStore.close();
    		certificacionStore.fetch({
         		onComplete: function(items, request) {
         			if (items.length == 0) return;
         				dijit.byId('idCertificacionIdioma').attr('value', '${escolaridadForm.idCertificacion}');
         			}
   				});
		}

		if (vIdioma==1){
    		//dijit.byId('idCertificacionIdioma').set('value',2);
    		//document.getElementById('idCertificacionIdioma').readOnly=true;
			//dijit.byId('idDominioIdioma').reset();
		
			desactivarCertifiaciones();
    		dominioStore.url = '';
    		dominioStore.close();
    		//dijit.byId('idDominioIdioma').reset();
    	
    		dijit.byId('idDominioIdioma').set('value','0');
			dijit.byId('idDominioIdioma').attr('value', 0); 
			desactivaSelectConValor('idDominioIdioma','');
			setLabelToLabelAndRequerido('idDominioIdioma','idLabelDominioIdioma','Dominio del idioma',false);
		
    	}
		else
		{
			setLabelToLabelAndRequerido('idDominioIdioma','idLabelDominioIdioma','Dominio del idioma',true);
		}
		/*
    	var idCertificacion = '${escolaridadForm.idCertificacion}';
    	if (idCertificacion!=2) {
    		dijit.byId('idDominioIdioma').disabled=true;
    		document.getElementById('idDominioIdioma').readOnly=true;
    	}
    	*/
    	//TODO agregar el disable de certificado
		var idDominio = '${escolaridadForm.idDominio}';
		
    	if(idDominio ==  2 || idDominio == 3 ){
    		
    		setLabelToLabelAndRequerido('idCertificacionIdioma','idLabelCertificacionIdioma','¿Cuentas con certificación?',true);
    	}
    	else
    	{
    		setLabelToLabelAndRequerido('idCertificacionIdioma','idLabelCertificacionIdioma','¿Cuentas con certificación?',false);
    	}
	}
    dojo.addOnLoad(function() {
		load();
    }); 
    function validaCampoSelect(campo){
		var control = dijit.byId(campo);	
		if (control && control.get('value')==''){
			dijit.showTooltip(control.get('missingMessage'), control.domNode, control.get('tooltipPosition'), !control.isLeftToRight());
			control.focus();
			document.getElementById(campo).blur();
			control.focus();
			alertMsg(control.get('missingMessage'));
			
			
			return false;
		}
		return true;
	}
    function validateKnowledgeList() {
    	var idKnowledgeAdd1 = dojo.byId('conocimientoAdd1');
    	var idKnowledgeAdd2 = dojo.byId('conocimientoAdd2');
    	var idKnowledgeAdd3 = dojo.byId('conocimientoAdd3');
    	if (idKnowledgeAdd1 != undefined) {
			if (idKnowledgeAdd2 != undefined) {
				if (idKnowledgeAdd3 != undefined)
					return (validateKnowledge(1) && validateKnowledge(2) && validateKnowledge(3));
				else
					return (validateKnowledge(1) && validateKnowledge(2));
    	   }else {
				return validateKnowledge(1);
    	   }
    	}
    	return true;
    }
	function validateKnowledge(index) {
		var conocimientoAddIndex = dojo.byId('conocimientoAdd' + index).value;
		var idExperienciaSelectAddIndex = document.getElementById('idExperienciaSelectAdd' + index).value;
		var descripcionConAddIndex = document.getElementById('descripcionConAdd'  + index);
		if (conocimientoAddIndex && idExperienciaSelectAddIndex == 0) {
			message('Es necesario indicar la experiencia del conocimiento.');
			return false;
		}
		if (conocimientoAddIndex && !descripcionConAddIndex.value) {
			message('Es necesario indicar la descripción del conocimiento.');
			descripcionConAddIndex.focus();
			return false;
		}
		return true;
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
		  var studietypeadd = document.getElementById('studieTypeAdd' + index);
		  var studieadd = dijit.byId('studieAdd' + index);
		  var institutionadd = dijit.byId('institutionAdd' + index);
		  var dayiniadd = document.getElementById('dayIniAdd' + index);
		  var monthiniadd = document.getElementById('monthIniAdd' + index);
		  var yeariniadd = document.getElementById('yearIniAdd' + index);
		  var dayfinadd = document.getElementById('dayFinAdd' + index);
		  var monthfinadd = document.getElementById('monthFinAdd' + index);
		  var yearfinadd = document.getElementById('yearFinAdd' + index);
		  var statusacademic = document.getElementById('statusAcademicAdd' + index);
		  if (studietypeadd.value == 0) {
		      message('Debe seleccionar otro estudio.');
		   return false;
		     }
		     if (!studieadd.value) {
		   message('Es necesario indicar el nombre del estudio.');
		   studieadd.focus();
		   return false;
		  }
		  if (!institutionadd.value) {
		   message('Es necesario indicar la institución del estudio.');
		   institutionadd.focus();
		   return false;
		  }
		  if (dayiniadd.value == 0) {
		      message('Debe seleccionar día de fecha de inicio del estudio.');
		   return false;
		     }
		     if (monthiniadd.value == 0) {
		      message('Debe seleccionar mes de fecha de inicio del estudio.');
		   return false;
		     }
		     if (yeariniadd.value == 0) {
		      message('Debe seleccionar año de fecha de inicio del estudio.');
		   return false;
		     }
		     if (dayfinadd.value == 0) {
		      message('Debe seleccionar día de fecha de fin del estudio.');
		   return false;
		     }
		     if (monthfinadd.value == 0) {
		      message('Debe seleccionar mes de fecha de fin del estudio.');
		   return false;
		     }
		     if (yearfinadd.value == 0) {
		      message('Debe seleccionar año de fecha de fin del estudio.');
		   return false;
		     }
		     if (!validateDatesList(dayiniadd, monthiniadd, yeariniadd, dayfinadd, monthfinadd, yearfinadd)) {
		      return false;
		     }
		     if (statusacademic.value == 0) {
		      message('Debe seleccionar situación académica del estudio.');
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
		  if (fhBuscaEmp > hoy){
		   message('La fecha de inicio de otros estudios no puede ser mayor al dia de hoy.');
		   return false;
		  }
		  if (fhEstFin > hoy){
		   message('La fecha de fin de otros estudios no puede ser mayor al dia de hoy.');
		   return false;
		  }
		  if (!valido) {
		   message('La fecha de fin no puede ser menor a la fecha de inicio.');  
		  }
		  return valido;
		 }
	
		function chkMostrarCertificacionesEnCV(mostrarCertifCVIsChecked){
			if (mostrarCertifCVIsChecked)
				dojo.byId('mostrarCertificacionesEnCV').value = publicaCertifCVSi;
			else
				dojo.byId('mostrarCertificacionesEnCV').value = publicaCertifCVNo;
		}
		
		function alertMsg(msg){
		
			showErrorDialog(msg);
		}
		
		
	function maximaLongitud(texto,maxlong) {
	  	var tecla, int_value, out_value;

	  	if (texto.value.length > maxlong)
	  	{
	  	/*con estas 3 sentencias se consigue que el texto se reduzca
	  	al tamaño maximo permitido, sustituyendo lo que se haya
	  	introducido, por los primeros caracteres hasta dicho limite*/
	  	in_value = texto.value.replace(/^\s+|\s*[\r\n][\r\n \t]*/g, "");
	  	
	  	out_value = in_value.substring(0,maxlong);
	  	texto.value = out_value;
	  	
	  	return false;
	  	}
	  	return true;
	}

	function displayProspera(show) {
		if (show) {	// Prospera support?
			dojo.attr(dojo.byId("prospera"), "style", "display:block");	// show div element
			dojo.attr(dojo.byId("apoyoProspera"), "value", dijit.byId("apoyoProsperaSi").attr("value")); // assign value to input hidden element
		} else {
			dojo.attr(dojo.byId("prospera"), "style", "display:none");	// hide div element
			dojo.attr(dojo.byId("apoyoProspera"), "value", dijit.byId("apoyoProsperaNo").attr("value")); // assign value to input hidden element
			dojo.attr(dojo.byId("folioProspera"), "value", "");
			// Clean input textfield
		}
	}
	function validaProspera() {
		if (document.getElementById("apoyoProsperaSi").checked) {
			//	var folio = dijit.byId('folioProspera');
			if (!dijit.byId("folioProspera").isValid()) {
				dijit.byId('folioProspera').focus();
				alertMsg('Folio Prospera incorrecto.');
				return false;
			}
			if (!dijit.byId("folioIntegranteProspera").isValid()) {
				dijit.byId('folioIntegranteProspera').focus();
				alertMsg('Folio Integrante Prospera incorrecto.');
				return false;
			}
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

<div dojoType="dojo.data.ItemFileReadStore" jsId="idiomaStore"
	url="${context}idiomas.do?method=idiomas" urlPreventCache="true"></div>
<div dojoType="dojo.data.ItemFileReadStore" jsId="certificacionStore"
	urlPreventCache="true" clearOnClose="true"></div>
<div dojoType="dojo.data.ItemFileReadStore" jsId="certificacionAddStore"
	urlPreventCache="true" clearOnClose="true"></div>
<div dojoType="dojo.data.ItemFileReadStore"
	jsId="certificacionAddStore_1" urlPreventCache="true"
	clearOnClose="true"></div>
<div dojoType="dojo.data.ItemFileReadStore"
	jsId="certificacionAddStore_2" urlPreventCache="true"
	clearOnClose="true"></div>
<div dojoType="dojo.data.ItemFileReadStore"
	jsId="certificacionAddStore_3" urlPreventCache="true"
	clearOnClose="true"></div>
<div dojoType="dojo.data.ItemFileReadStore" jsId="dominioStore"
	url="${context}idiomas.do?method=dominios" urlPreventCache="true"
	clearOnClose="true"></div>
<div dojoType="dojo.data.ItemFileReadStore" jsId="dominioStore2"
	url="${context}idiomas.do?method=dominios" urlPreventCache="true"
	clearOnClose="true"></div>
<div dojoType="dojo.data.ItemFileReadStore" jsId="dominioStore3"
	url="${context}idiomas.do?method=dominios" urlPreventCache="true"
	clearOnClose="true"></div>
<div dojoType="dojo.data.ItemFileReadStore" jsId="dominioStoreAdd"
	url="${context}idiomas.do?method=dominios" urlPreventCache="true"
	clearOnClose="true"></div>
<form name="escolaridadForm" id="escolaridadForm" method="post" action="escolaridad.do" dojoType="dijit.form.Form">
	<input type="hidden" name="method" id="method" value="init" />
	<input type="hidden" name="sinEstudios" id="sinEstudios" dojoType="dijit.form.TextBox" />
	<input type="hidden" name="apoyoProspera" id="apoyoProspera" value="${escolaridadForm.apoyoProspera}" />
	<div class="formApp miEspacio">



		<h2>Mi espacio</h2>
		<div class="tab_block">
			<div align="left" style="display: block;" id="returnME">
				<a class="expand"
					href="javascript:location.replace('<c:url value="/miEspacioCandidato.do"/>');">
					<strong>Ir al inicio de Mi espacio</strong>
				</a>
			</div>
			<div class="Tab_espacio">
				<h3>Editar mi perfil</h3>
				<div class="subTab">
					<ul>
						<li><a
							href="javascript:messageRutaCancel('${escolaridadForm.errorMessageTab}','<%=response.encodeURL(context+"perfil.do?method=init")%>');">Datos
								personales</a></li>
						<li class="curSubTab"><span>Escolaridad y otros
								conocimientos</span></li>
						<li><a
							href="javascript:messageRutaCancel('${escolaridadForm.errorMessageTab}','<%=response.encodeURL(context+"experiencia.do?method=init")%>');">Experiencia
								y expectativas laborales</a></li>
						<li><a
							href="javascript:messageRutaCancel('${escolaridadForm.errorMessageTab}','<%=response.encodeURL(context+"expectativa.do?method=init")%>');">Situación
								laboral</a></li>
					</ul>
					<div class="clearfix"></div>
				</div>
				<div class="clearfix"></div>
			</div>
			<p class="labeled">
				Los datos marcados con <span>*</span> son obligatorios
			</p>
		</div>
		<div class="escolaridad">
			<fieldset class="estudios">
				<legend>Estudios</legend>
				<input type="hidden" name="idCandidatoGradoAcademico" id="idCandidatoGradoAcademico" value="${escolaridadForm.idCandidatoGradoAcademico}" dojoType="dijit.form.TextBox" />
				<div dojoType="dojo.data.ItemFileReadStore"
					jsId="gradoAcademicoStore" urlPreventCache="true"
					clearOnClose="true" url="${context}grados.do?method=cargarGrados"></div>
				<div class="margin_top_10">
					<div class="grid1_3 fl">
						<label for="idNivelEstudioSelect"><span>*</span> Último grado de estudios</label>
						<input type="hidden" name="idNivelEstudio" id="idNivelEstudio" dojoType="dijit.form.TextBox" />
						<select
							dojotype="dijit.form.FilteringSelect"
							value='${escolaridadForm.idNivelEstudio}'
							id="idNivelEstudioSelect" required="true"
							missingMessage="Debe seleccionar el último grado de estudios"
							autocomplete="true">
							<c:forEach var="row" items="${opcGrados}">
								<option value="${row.idCatalogoOpcion}">${row.opcion}</option>
							</c:forEach>
						</select>
					</div>
					<div class="grid1_3 fl">
						<div dojoType="dojo.data.ItemFileReadStore" jsId="carreraStore" urlPreventCache="true" clearOnClose="true"></div>
						<label for="idCarreraEspecialidadSelect"><span>*</span>Carrera o especialidad</label>
						<input type="hidden" name="idCarreraEspecialidad" id="idCarreraEspecialidad" dojoType="dijit.form.TextBox" />
						<select
							dojotype="dijit.form.FilteringSelect" store="carreraStore"
							scrollOnFocus="true" maxHeight="250"
							id="idCarreraEspecialidadSelect" required="true"
							missingMessage="Debe seleccionar la carrera o especialidad"
							autocomplete="true">
						</select>
					</div>
					<div class="grid1_3 fl margin_no_r">
						<div dojoType="dojo.data.ItemFileReadStore"
							jsId="estatusGradoStore" urlPreventCache="true"
							clearOnClose="true"></div>
						<label for="idSituacionAcademicaSelect"><span>*</span>Situación académica</label>
						<input type="hidden" name="idSituacionAcademica" id="idSituacionAcademica" dojoType="dijit.form.TextBox" />
						<select
							id="idSituacionAcademicaSelect" name="idSituacionAcademicaSelect"
							dojotype="dijit.form.FilteringSelect" store="estatusGradoStore"
							required="true"
							missingMessage="Es necesario indicar la situación académica"
							autocomplete="true">
						</select>
					</div>
					
					<div class="clearfix"></div>
				</div>
				<div id="otros_estudios bloque" name="otros_estudios bloque">
					<jsp:include page="/jsp/candidatos/misDatos/otherstudies.jsp"
						flush="true" />
				</div>
				<div class="apoyoProspera margin_top_30">
				
					<div class="labeled">
						<span>*</span> ¿Realizaste tus estudios con ayuda del programa PROSPERA?
					</div>
					<div>
						<div class="fl">
						<input type="radio" name="apoyoProspera" id="apoyoProsperaSi"
							data-dojo-type="dijit.form.RadioButton"
							value="${escolaridadForm.apoyoProsperaSi}"
							"${escolaridadForm.apoyoProspera == escolaridadForm.apoyoProsperaSi ? ' checked ' : ''}"
						   	onclick="displayProspera(true);"
						/>
						</div>
						<label for="apoyoProsperaSi">Si</label><br/>
						<div class="fl">
						<input type="radio" name="apoyoProspera" id="apoyoProsperaNo"
							data-dojo-type="dijit.form.RadioButton"
							value="${escolaridadForm.apoyoProsperaNo}"
							"${escolaridadForm.apoyoProspera == escolaridadForm.apoyoProsperaNo ? ' checked ' : ''}"
						   	onclick="displayProspera(false);"
						/>
						</div>
						<label for="apoyoProsperaNo">No</label>
					</div>
					<div id="prospera">
						<div class="labeled">
							<span>*</span> Ingresa folio familia Prospera
						</div>
						<input class="margin_top_10" type="text" id="folioProspera" name="folioProspera"
							   value="${escolaridadForm.folioProspera}"
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
							   value="${escolaridadForm.folioIntegranteProspera}"
							   dojoType="dijit.form.ValidationTextBox" maxlength="16" size="65"
							   trim="true" required="true"
							   missingMessage="Es necesario indicar el folio familia Prospera"
							   regExp="${regexp_foliointegranteprospera}"
							   invalidMessage="El folio integrante Prospera es inválido."
						/>
					</div>
				</div>
				<div class="clearfix"></div>
				
			</fieldset>
			<fieldset class="idiomas">
				<legend>Idioma</legend>
				<input type="hidden" name="idIdiomaSelect" id="idIdiomaSelect"
					dojoType="dijit.form.TextBox" value="${escolaridadForm.idIdioma}" />
				<input type="hidden" name="idCertificacion" id="idCertificacion"
					dojoType="dijit.form.TextBox"
					value="${escolaridadForm.idCertificacion}" /> <input type="hidden"
					name="idDominio" id="idDominio" dojoType="dijit.form.TextBox"
					value="${escolaridadForm.idDominio}" />

				<div class="margin_top_10">
					<div class="grid1_3 fl">
						<label for="idIdioma"><span>*</span>Idioma adicional al
							nativo</label> <select id="idIdioma" name="idIdioma" required="true"
							missingMessage="Es necesario indicar el idioma adicional"
							store="idiomaStore" dojotype="dijit.form.FilteringSelect"
							value="${escolaridadForm.idIdioma}"
							onchange="javascript:actualizaDominio();">
						</select>
					</div>

					<div class="grid1_3 fl">
						<label id="idLabelDominioIdioma" for="idDominioIdioma"></label> <select id="idDominioIdioma" name="idDominioIdioma"
							required="true" readOnly="false" store="dominioStore"
							dojotype="dijit.form.FilteringSelect"
							missingMessage="Es necesario indicar el dominio requerido del idioma"
							onchange="javascript:actualizaCertificaciones();"
							value="${escolaridadForm.idDominio}">
						</select>
					</div>

					<div class="grid1_3 fl margin_no_r">
						<label id="idLabelCertificacionIdioma" for="idCertificacionIdioma">¿Cuentas
							con certificación?</label> <select id="idCertificacionIdioma"
							name="idCertificacionIdioma" required="false" readOnly="true"
							store="certificacionStore" dojotype="dijit.form.FilteringSelect"
							missingMessage="Es necesario indicar la certificacion requerida"
							value="${escolaridadForm.idCertificacion}">
						</select>
					</div>
				</div>

				<div id="agregaIdioma" name="agregaIdioma">
					<jsp:include page="/jsp/candidatos/misDatos/languages.jsp"
						flush="true" />
				</div>

				<div id="idiomaAdicional" class="margin_top_30"
					style="display: none">
					<div class="grid1_3 fl">
						<label id="idLabelIdiomaAdd" for="idIdiomaAdd">Idioma
							adicional al nativo</label> <select id="idIdiomaAdd" name="idIdiomaAdd"
							required="true"
							missingMessage="Es necesario indicar el idioma adicional"
							store="idiomaStore" dojotype="dijit.form.FilteringSelect"
							onchange="javascript:actualizaDominioAdd(0,this.value);">
						</select>
					</div>
					<div class="grid1_3 fl">
						<label id="idLabelDominioIdiomaAdd" for="idDominioIdiomaAdd">Dominio
							del idioma</label> <select id="idDominioIdiomaAdd"
							name="idDominioIdiomaAdd" required="false" readOnly="false"
							store="dominioStoreAdd" dojotype="dijit.form.FilteringSelect"
							missingMessage="Es necesario indicar el dominio requerido del idioma"
							onchange="javascript:actualizaCertificacionesAdd(0);">
						</select>
					</div>
					<div class="grid1_3 fl">
						<label id="idLabelCertificacionIdiomaAdd"
							for="idCertificacionIdiomaAdd">¿Cuentas con
							certificación?</label> <select id="idCertificacionIdiomaAdd"
							name="idCertificacionIdiomaAdd" required="false" readOnly="false"
							store="certificacionAddStore"
							dojotype="dijit.form.FilteringSelect"
							missingMessage="Es necesario indicar la certificacion requerida">
						</select>
					</div>

					<div class="clearfix"></div>
					<div class="margin_top_10 ta_right" style="width: 918px">
						<input type="button" class="enviar" title="Guardar idioma"
							value="Guardar idioma" onclick="doSubmitAddIdioma();" />
					</div>
				</div>

			</fieldset>

			<fieldset class="conocimientos">
				<legend>Conocimientos</legend>

				<div class="margin_top_20">Conocimiento es un saber adquirido
					sobre un tema en particular, albañiler&iacute;a, experto en uso de
					torno, manejo de Excel, etcétera.</div>
				<div class="labeled">Computaci&oacute;n b&aacute;sica</div>
				<ul>
					<li><c:if test="${escolaridadForm.conocimientoCompNinguno==1}">
							<input type="checkbox" name="conocimientoCompNinguno"
								id="conocimientoCompNinguno" value="1" checked="checked"
								onclick="javascript:limpiaConocimientosComp()"
								dojoType="dijit.form.CheckBox"/>
						</c:if> <c:if test="${escolaridadForm.conocimientoCompNinguno!=1}">
							<input type="checkbox" name="conocimientoCompNinguno"
								id="conocimientoCompNinguno" value="1"
								onclick="javascript:limpiaConocimientosComp()"
								dojoType="dijit.form.CheckBox"/>
						</c:if> <label for="conocimientoCompNinguno">Ninguno</label></li>
					<li><c:if
							test="${escolaridadForm.conocimientoCompProcesadorTxt==1}">
							<input type="checkbox" name="conocimientoCompProcesadorTxt"
								id="conocimientoCompProcesadorTxt" value="1" checked="checked"
								onclick="javascript:cleanNone()" 
								dojoType="dijit.form.CheckBox"/>
						</c:if> <c:if test="${escolaridadForm.conocimientoCompProcesadorTxt!=1}">
							<input type="checkbox" name="conocimientoCompProcesadorTxt"
								id="conocimientoCompProcesadorTxt" value="1"
								onclick="javascript:cleanNone()" dojoType="dijit.form.CheckBox"
								/>
						</c:if> <label for="conocimientoCompProcesadorTxt">Procesador de
							textos</label></li>
					<li><c:if test="${escolaridadForm.conocimientoCompHojaCal==1}">
							<input type="checkbox" name="conocimientoCompHojaCal"
								id="conocimientoCompHojaCal" value="1" checked="checked"
								onclick="javascript:cleanNone()"
								dojoType="dijit.form.CheckBox"/>
						</c:if> <c:if test="${escolaridadForm.conocimientoCompHojaCal!=1}">
							<input type="checkbox" name="conocimientoCompHojaCal"
								id="conocimientoCompHojaCal" value="1"
								onclick="javascript:cleanNone()"
								dojoType="dijit.form.CheckBox" />
						</c:if> <label for="conocimientoCompHojaCal">Hojas de cálculo</label></li>
					<li><c:if
							test="${escolaridadForm.conocimientoCompInternet==1}">
							<input type="checkbox" name="conocimientoCompInternet"
								id="conocimientoCompInternet" value="1" checked="checked"
								onclick="javascript:cleanNone()"
								dojoType="dijit.form.CheckBox"/>
						</c:if> <c:if test="${escolaridadForm.conocimientoCompInternet!=1}">
							<input type="checkbox" name="conocimientoCompInternet"
								id="conocimientoCompInternet" value="1"
								onclick="javascript:cleanNone()"
								dojoType="dijit.form.CheckBox"/>
						</c:if> <label for="conocimientoCompInternet">Internet y correo
							electrónico</label></li>
					<li><c:if test="${escolaridadForm.conocimientoCompRedes==1}">
							<input type="checkbox" name="conocimientoCompRedes"
								id="conocimientoCompRedes" value="1" checked="checked"
								onclick="javascript:cleanNone()"
								dojoType="dijit.form.CheckBox"/>
						</c:if> <c:if test="${escolaridadForm.conocimientoCompRedes!=1}">
							<input type="checkbox" name="conocimientoCompRedes"
								id="conocimientoCompRedes" value="1"
								onclick="javascript:cleanNone()"
								dojoType="dijit.form.CheckBox"/>
						</c:if> <label for="conocimientoCompRedes">Redes sociales</label></li>
					<li><label class="otros_c" for="conocimientoCompOtros">Otros:</label><br />
						<input id="conocimientoCompOtros" name="conocimientoCompOtros"
						class="${classOtros}" required="false"
						dojoType="dijit.form.ValidationTextBox"
						value="${escolaridadForm.conocimientoCompOtros}" /></li>
				</ul>
				<input type="hidden" name="idCandidatoConocimiento"
					id="idCandidatoConocimiento"
					value="${escolaridadForm.idCandidatoConocimiento}"
					dojoType="dijit.form.TextBox" /> <input type="hidden"
					name="idTipoConocimiento" id="idTipoConocimiento"
					value="${escolaridadForm.idTipoConocimiento}"
					dojoType="dijit.form.TextBox" />
				<div class="margin_top_40">
					<div class="grid1_3 fl">
						<label for="conocimiento">Conocimiento</label> <input
							class="${classCono}" type="text" name="conocimiento"
							id="conocimiento" value="${escolaridadForm.conocimiento}"
							maxlength="50"
							regExp="^[\w\d\s\.áéíóúÁÉÍÓÚñÑ]{1,50}$"
							missingMessage="Debe indicar el conocimiento."
							invalidMessage="Carácter no permitido"
							dojoType="dijit.form.ValidationTextBox" required="false" />
					</div>
					<div class="grid1_3 fl">
						<label for="idExperienciaConSelect">Experiencia</label> <input
							type="hidden" name="idExperienciaCon" id="idExperienciaCon"
							dojoType="dijit.form.TextBox" /> <select class="${classExpCon}"
							dojotype="dijit.form.FilteringSelect"
							value='${escolaridadForm.idExperienciaCon}'
							id="idExperienciaConSelect" required="false" autocomplete="true">
							<c:forEach var="row" items="${opciExperiencia}">
								<option value="${row.idCatalogoOpcion}">${row.opcion}</option>
							</c:forEach>
						</select>
					</div>
					<div class="clearfix"></div>
				</div>
				<div class="campoTexto margin_top_30">
					<label for="descripcionCon">Descripción</label>
					<div>En este espacio puedes ampliar
						la información sobre este conocimiento</div>
					<textarea class="textGoogie" rows="4" cols="70"
						name="descripcionCon" id="descripcionCon"
						onkeypress="return caracteresValidos(event);" trim="true"
						onchange="return maximaLongitud(this,2000)"
						onKeyUp="return maximaLongitud(this,2000)" maxlength="2000"
						required="false"
						regExp="^[\w\d\s\.&aacute;&eacute;&iacute;&oacute;&uacute;&Aacute;&Eacute;&Iacute;&Oacute;&Uacute;&Ntilde;&ntilde;]{1,2000}$">${escolaridadForm.descripcionCon}</textarea>
					<script type="text/javascript">
	         			var vSpellCon = new GoogieSpell("googiespell/", '<%=vProxy%>');
	         			vSpellCon.setLanguages({'es': 'Español'});
   						vSpellCon.hideLangWindow();
  						vSpellCon.decorateTextarea("descripcionCon");
					</script>
				</div>
				<div class="margin_40"></div>
				<div id="idConocimientosBloque">
					<jsp:include page="/jsp/candidatos/misDatos/conocsHabs.jsp"
						flush="true" />
				</div>
			</fieldset>

			<fieldset class="habilidades">
				<legend>Habilidades y aptitudes</legend>
				<div class="habilidades bloque" id="listhabilidades">
					<div class="labeled">
						<span>*</span> Selecciona máximo 5 habilidades y aptitudes que te
						caracterizan.
					</div>

					<fmt:formatNumber var="numbloque"
						value="${fn:length(opcHabilidades) / 3}" maxFractionDigits="0" />

					<c:forEach var="habilidadOpc" items="${opcHabilidades}"
						varStatus="index">
						<c:set var="open"
							value="${index.count==1 || index.count==numbloque+1 || index.count==(numbloque*2)+1}" />
						<c:set var="close"
							value="${index.count==numbloque || index.count==(numbloque*2) || index.count==fn:length(opcHabilidades)}" />

						<c:set var="checkmedio" value="" />

						<c:forEach var="habilidadSaved"
							items="${escolaridadForm.idHabilidad}">
							<c:if test="${habilidadOpc.idCatalogoOpcion == habilidadSaved}">
								<c:set var="checkmedio" value="checked" />
							</c:if>
						</c:forEach>

						<c:if test="${open}">
							<ul>
						</c:if>
						<li><input type="checkbox" id="idHabilidad${habilidadOpc.idCatalogoOpcion}"
							name="idHabilidad" value="${habilidadOpc.idCatalogoOpcion}"
							${checkmedio} dojoType="dijit.form.CheckBox"/> <label for="idHabilidad${habilidadOpc.idCatalogoOpcion}">${habilidadOpc.opcion}</label></li>

						<c:if test="${close}">
							</ul>
						</c:if>

					</c:forEach>
					<div class="clearfix"></div>
				</div>
			</fieldset>

			<div class="certificacion bloque">
				<c:choose>
					<c:when
						test="${(escolaridadForm.tieneCertificaciones == tieneCertificacionesSI)}">
						<c:choose>
							<c:when
								test="${(escolaridadForm.mostrarCertificacionesEnCV == tieneCertificacionesSI)}">
								<div id="competenciasConocer">
									<h3>Certificaci&oacute;n de competencias</h3>
									<p>
										<img src="images/logo_conocerCand.gif" alt="logo Conocer" /><br />
									<div class="recuadro_comp">
										<p>
											Tienes las siguientes <strong>certificaciones</strong>
										</p>
										<ul>
											<c:forEach var="rowListaCertificaciones"
												items="${escolaridadForm.listaCertificaciones}">
												<li><c:out value="${rowListaCertificaciones.titulo}" /></li>
											</c:forEach>
										</ul>
										<p class="indented">
											<input id="chkMostrarCertifCV" name="chkMostrarCertifCV"
												type="checkbox" dojoType="dijit.form.CheckBox"
												<c:if test="${escolaridadForm.mostrarCertificacionesEnCV == publicaCertificacionesCvSI}">										
												checked="checked"
											</c:if>
												onclick="javascript:chkMostrarCertificacionesEnCV(this.checked)" />
											<input type="hidden" id="mostrarCertificacionesEnCV"
												name="mostrarCertificacionesEnCV"
												value="${escolaridadForm.mostrarCertificacionesEnCV eq publicaCertificacionesCvSI ? escolaridadForm.mostrarCertificacionesEnCV : publicaCertificacionesCvNO}">
											<label>Mostrar certificaciones en mi detalle</label>
										</p>
										<p class="indented">
											<a href="http://www.conocer.gob.mx/" target="_black">Consultar
												m&aacute;s opciones de certificaci&oacute;n...</a>
										</p>
									</div>
								</div>
							</c:when>
							<c:otherwise>
								<div id="competenciasConocer" class="sin_certificacion">
									<h3>
										<span>Certificaci&oacute;n de competencias</span>
									</h3>
									<p>
										<img src="images/logo_conocerCand.gif" alt="logo Conocer" /><br />
									<div class="recuadro_comp">
										<p>
											<strong>Aumenta tus posibilidades de obtener un
												mejor empleo...</strong>
										</p>
										<p class="indented">
											<a href="http://conocer.gob.mx/certifico-mis-competencias/" target="blank">Certifica
												las habilidades, los conocimientos y las destrezas que has
												adquirido a lo largo de tu vida laboral.</a>
										</p>
									</div>
								</div>
							</c:otherwise>
						</c:choose>
					</c:when>
					<c:otherwise>
						<div id="competenciasConocer" class="sin_certificacion">
							<h3>
								<span>Certificaci&oacute;n de competencias</span>
							</h3>
							<p>
								<img src="images/logo_conocerCand.gif" alt="logo Conocer" />
							</p>
							<p>
								<strong>Aumenta tus posibilidades de obtener un mejor
									empleo...</strong><br /> <a class="c_naranja"
									href="http://conocer.gob.mx/certifico-mis-competencias/" target="blank">Certifica las
									habilidades, los conocimientos y las destrezas que has
									adquirido a lo largo de tu vida laboral.</a>
							</p>
						</div>
					</c:otherwise>
				</c:choose>
			</div>

			<div dojoType="dijit.Dialog" id="errorDialog" title="Error"
				style="display: none;" draggable="false">
				<div class="msg_contain">
					<p id="errorMsgArea">
						Favor de revisar los datos proporcionados.<br /> Si tus datos son
						correctos visita la siguiente liga<br /> <a
							href='http://www.renapo.gob.mx/swb/swb/RENAPO/tramite'
							target='_blank'>http://www.renapo.gob.mx/swb/swb/RENAPO/tramite</a><br />
						para ver si existe un problema con tu CURP.
					</p>
				</div>
				<p class="form_nav">
					<button class="button" onclick="dijit.byId('errorDialog').hide();">Aceptar</button>
				</p>
			</div>

			<div class="form_nav">
				<input type="button" id="btnGuardarPagina" name="btnGuardarPagina"
					value="Guardar" onclick="doSubmitEsc();"> <input
					type="button" value="Cancelar" onclick="doCancel();">
			</div>

		</div>
</form>

