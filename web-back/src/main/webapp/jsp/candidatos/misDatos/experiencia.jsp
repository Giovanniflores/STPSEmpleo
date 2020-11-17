<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page
	import="mx.gob.stps.portal.core.infra.utils.Constantes.EXPERIENCIA_LABORAL, java.util.Calendar, java.util.Date, java.text.DateFormat, java.text.SimpleDateFormat, java.text.ParseException"%>
<%
	String context = request.getContextPath() +"/";
	pageContext.setAttribute("context", context);
	String vProxy = "http://orangoo.com/newnox?lang=";
	pageContext.setAttribute("vProxy",vProxy);
	String laboresFinal = null != session.getAttribute("laboresFinal") ? (String)session.getAttribute("laboresFinal") : "";
	String laboresInicial = null != session.getAttribute("laboresInicial") ? (String)session.getAttribute("laboresInicial") : "";	
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
</style>
<script type="text/javascript" src="js/helper/dateHelper.js"></script>
<script type="text/javascript" src="js/helper/messageHelper.js"></script>

<link href="googiespell/googiespell.css" rel="stylesheet"
	type="text/css" />
<c:if test="${experienciaForm.idTipoContrato<=0}">
	<c:set var="classTipoCon" value="sugerido" />
</c:if>
<c:if test="${empty experienciaForm.empresa}">
	<c:set var="classEmpresa" value="sugerido" />
</c:if>
<c:if test="${empty experienciaForm.puesto}">
	<c:set var="classPuesto" value="sugerido" />
</c:if>
<c:if test="${experienciaForm.idJerarquia<=0}">
	<c:set var="classJerar" value="sugerido" />
</c:if>
<c:if test="${empty experienciaForm.personasCargo}">
	<c:set var="classCargo" value="sugerido" />
</c:if>
<c:if test="${empty experienciaForm.salarioMensual}">
	<c:set var="classMensual" value="sugerido" />
</c:if>
<c:if test="${empty experienciaForm.funcion}">
	<c:set var="classFuncion" value="sugerido" />
</c:if>

<c:if test="${experienciaForm.idTipoContrato>0}">
	<c:set var="classTipoCon" value="" />
</c:if>
<c:if test="${not empty experienciaForm.empresa}">
	<c:set var="classEmpresa" value="" />
</c:if>
<c:if test="${not empty experienciaForm.puesto}">
	<c:set var="classPuesto" value="" />
</c:if>
<c:if test="${experienciaForm.idJerarquia>0}">
	<c:set var="classJerar" value="" />
</c:if>
<c:if test="${not empty experienciaForm.personasCargo}">
	<c:set var="classCargo" value="" />
</c:if>
<c:if test="${not empty experienciaForm.salarioMensual}">
	<c:set var="classMensual" value="" />
</c:if>
<c:if test="${not empty experienciaForm.funcion}">
	<c:set var="classFuncion" value="" />
</c:if>




<script type="text/javascript">
    dojo.require("dijit.dijit"); // loads the optimized dijit layer
    dojo.require("dijit.form.Form");
    dojo.require("dijit.Calendar");
    dojo.require("dijit.form.DateTextBox");
    dojo.require("dijit.form.Textarea");
    dojo.require("dijit.form.ValidationTextBox");
	dojo.require("dojo.data.ItemFileReadStore");
	dojo.require("dijit.form.CheckBox");
	dojo.require("dijit.form.FilteringSelect");
	dojo.require('dijit.form.RadioButton');
	dojo.require('dijit.Dialog');
	
	dojo.addOnLoad(function() {
		dojo.connect(dijit.byId("idAreaLaboralMayorExprSelect"), "onChange", function() {
			dijit.byId('idOcupacionMayorExprSelect').reset();
			var vArea = dijit.byId('idAreaLaboralMayorExprSelect').get('value');
            if(vArea != 0) {
            	ocupacionStore.url = "${context}histLaborales.do?method=cargarOcupacion&idAreaLaboral="+ vArea;
            	ocupacionStore.close();
			}
        });
        dojo.connect(dijit.byId("idAreaLaboralSelect"), "onChange", function() {
			dijit.byId('idOcupacionSelect').reset();
			var vArea = dijit.byId('idAreaLaboralSelect').get('value');			
            if(vArea != 0) {
            	ocupacionStore1.url = "${context}histLaborales.do?method=cargarOcupacion&idAreaLaboral="+ vArea;
            	ocupacionStore1.close();
			}
        });
	cargandoCatalogos();

		if ("<c:out value='${experienciaForm.idExperienciaOcupacion2}' />" != "0") {
			
			mostrarOtraOpcionDeEmpleo();
		} else {
			
			setOcultaHTML("otra_opcion_empleo", false); // Hide
		}
	});
//--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*	
function cargandoCatalogos(){
	//cargandoCatalogoAreaLaboral();
}

function cargandoCatalogoAreaLaboral(){
	areaLaboralStore.fetch({
			onComplete: function(items, request) {
	      if (items.length == 0) {
	         return;
	      }
	      var vArea = '${experienciaForm.idAreaLaboralMayorExpr}';
	      dijit.byId('idAreaLaboralMayorExprSelect').attr('value', vArea);
	      
	      if(vArea != 0) {
        	ocupacionStore.url = "${context}histLaborales.do?method=cargarOcupacion&idAreaLaboral="+ vArea;
        	ocupacionStore.close();
        	ocupacionStore.fetch({
					onComplete: function(items, request) {
					if (items.length == 0) {
			        	return;
			    	}
			    	dijit.byId('idOcupacionMayorExprSelect').attr('value', '${experienciaForm.idOcupacionMayorExpr}');
					}
			});
		  }
		  
		  var vArea1 = '${experienciaForm.idAreaLaboral}';
		  dijit.byId('idAreaLaboralSelect').attr('value', vArea1);
		  if(vArea1 != 0) {
        	ocupacionStore1.url = "${context}histLaborales.do?method=cargarOcupacion&idAreaLaboral="+ vArea1;
        	ocupacionStore1.close();
        	ocupacionStore1.fetch({
					onComplete: function(items, request) {
					if (items.length == 0) {
			        	return;
			    	}
			    	dijit.byId('idOcupacionSelect').attr('value', '${experienciaForm.idOcupacion}');
					}
			});
		  }
			}
	});
}
//--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*
	
function validarExperForm() {
	if (validarForma()) {
		if (!validarFechas())
			return false;
		<c:if test="${experienciaForm.ppc}">
		if (!llenadoCamposTrabajoActual())
			return false;
		</c:if>
		<c:if test="${not experienciaForm.ppc}">
		if (!llenadoComposTrabajoActualNoRequerido())
			return false;
		</c:if>

		return true;
    }else
    	return false;
}
function llenadoCamposTrabajoActual() {
	if (!validaCampo('empresa'))
		return false;
	if (!validaCampo('puesto'))
		return false;
	if (!validaCampoSelect('idJerarquiaSelect'))
		return false;
	if (!validaCampoSelect('personasCargoSelect'))
		return false;
	if (!validaCampoMin('salarioMensual',0)){
		
		return false;
	}
	var fechaInicio = validateFromCombos('laboresInicialDia',
			'laboresInicialMes', 'laboresInicialAnual');
	if (fechaInicio == 0)
		return false;
	var fechaFin = validateFromCombos('laboresFinalDia',
			'laboresFinalMes', 'laboresFinalAnual');
	if (fechaFin == 0)
		return false;
	if (!validateDate1ToDate2(
			fechaInicio,
			fechaFin,
			'La fecha fin del último empleo no puede ser mayor al la fecha Inico del último empleo.'))
		return false;

	if (!validateDateToHoy(fechaFin,
			'La fecha fin del último empleo no puede ser mayor al dia de hoy.'))
		return false;

	if (dojo.byId('funcion')
			&& dojo.byId('funcion').value == '') {
		
		dojo.byId('funcion').focus();
		message('Favor de describir su funciones Desempenadas.');
		return false;
	}
	return true;
}

function llenadoComposTrabajoActualNoRequerido() {
	
	if (!validaCampoNoRequerido('empresa'))
		return false;
	if (!validaCampoNoRequerido('idJerarquiaSelect'))
		return false;
	if (!validaCampoNoRequerido('personasCargoSelect'))
		return false;
	if (!validaCampoNoRequerido('salarioMensual'))
		return false;
	if (!validateFecha('laboresInicialDia',
			'laboresInicialMes', 'laboresInicialAnual',false))
		return false;
	if (!validateFecha('laboresFinalDia', 'laboresFinalMes',
			'laboresFinalAnual',false))
		return false;
	return true;
}

function validarTextArea(id) {
   	var txt = dijit.byId(id);
	var regExp = /^[\w\d\s\.áéíóúÁÉÍÓÚñÑ;]{1,2000}$/;
	if (!regExp.test(txt.get('value')) && txt.get('value') != '') {
		mensaje('Descripcion contiene caracteres inválidos');
		txt.focus();
		return false;
	} else {
		var controlDescripcion = txt.get('value');
		if(controlDescripcion.length>2000){
			txt.focus();
			mensaje('La descripción es demasiado larga, no debe ser mayor a 2000 caracteres.');
			return false;					
		}		
	}
	return true;
}

//Desplegar ventana para captura de idiomas adicionales
function openTrabajoWindow() {
  var newWin = window.open('<%=context + "histLaborales.do?method=init"%>', "Historia_Laboral","height=500,width=980,resizable=yes,scrollbars=yes");
}	
function Upper(TextField) {
    TextField.value = TextField.value.toUpperCase();
}

function irA(opcion){
	if ((opcion > 0) && (opcion < 5)){
		var cv = ${estatusCV};
		cv ++; //para poder ver la ventana siguiente al estatus actual
		var tab = ${thisTabId};
		if ((cv >= opcion) && (tab != opcion) ){
			if (opcion == 1){
				location.replace('<%=response.encodeURL(context +"perfil.do?method=init")%>');
			}
			if (opcion == 2){
				location.replace('<%=response.encodeURL(context+"escolaridad.do?method=init")%>');
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
				mensaje("Debe completar el formulario actual");
			}else {
				mensaje("Debe completar el formulario #" + cv); //ultimo formulario completado
			}
			
		}
	}
}

	function cancelarPerfil() {
		var answer = confirm("Los datos no guardados se perderán ¿Continuar?");
		if (answer){
			location.replace('<c:url value="/miEspacioCandidato.do"/>');
		}
	}


	function caracteresValidos(e){
    	var tecla_codigo = (document.all) ? e.keyCode : e.which;
    	if(tecla_codigo==8 || tecla_codigo==0)
    		return true;
    	if (tecla_codigo==225 || tecla_codigo==233 || tecla_codigo==237 || tecla_codigo==243 || tecla_codigo==250) 
    		return true; //vocales minusculas con acento
    	if (tecla_codigo==193 || tecla_codigo==201 || tecla_codigo==205 || tecla_codigo==211 || tecla_codigo==218) 
    		return true; //vocales mayuzculas con acento
    	if (tecla_codigo==209 || tecla_codigo==241 ) 
    		return true; //ï¿½ y ï¿½
   		
    	var patron =/[0-9\-a-zA-Z_ .,:;#]/;
    	tecla_valor = String.fromCharCode(tecla_codigo);
    	return patron.test(tecla_valor);
    }
    // Validacion para todo el formulario uno a uno

function validarForma() {
	return validarDatoPorDato() /*&& dijit.byId('experienciaForm').isValid()*/;
}

function habilitarLabores() {	
	var inicial = document.getElementsByName("laboresInicial");
	var finale = document.getElementsByName("laboresFinal");
	inicial[0].disabled = false;
	finale[0].disabled  = false;
}
	
function validarIdMensaje(){
	//var bandera = document.getElementById('nuncaHeTrabajado').checked;
	var mensajeError =  "";	
	//if(!bandera) {
			/*mensajeError += validarSelect("idExperienciaTotalSelect","Favor de Proporcionar la Experiencia total.");
			//mensajeError += validarPorId("puestoMayorExpr","Favor de Proporcionar el Puesto de mayor experiencia.");
			mensajeError += validarSelect("idSectorSelect","Favor de Proporcionar el Sector.");
			mensajeError += validarSelect("idAreaLaboralSelect","Favor de Proporcionar el área laboral.");
			mensajeError += validarSelect("aniosLaboradosSelect","Favor de Proporcionar los años laborados.");*/
			mensajeError += validarPorId("ocupacion","Favor de Proporcionar el empleo buscado.");
			//mensajeError += validarPorId("puestoDeseado","Favor de Proporcionar descripción.");
			mensajeError += validarPorId("empresa","Favor de Proporcionar la Empresa.");
			mensajeError += validarPorId("laboresInicial","Favor de Proporcionar la Fecha inicial.");
			mensajeError += validarPorId("laboresFinal","Favor de Proporcionar la Fecha final.");
			mensajeError += validarSelect("idJerarquiaSelect","Favor de Proporcionar la Jerarquía del puesto.");
			/*mensajeError += validarSelect("personasCargoSelect","Favor de Proporcionar la Personas a cargo.");
			mensajeError += validarPorId("salarioMensual","Favor de Proporcionar el ï¿½ltimo salario mensual neto.");*/
	//}	
	return mensajeError;
}


function validarPorId(id,mensajeValidacion){
	if(!dijit.byId(id).isValid())
		return "<li>"+mensajeValidacion + "</li>";
	else
		return "";
}

function validarSelect(id,mensajeValidacion){
	if (dijit.byId(id).get('item')== null)
		return "<li>"+mensajeValidacion + "</li>";
	else
		return "";
}
function validarSelectConCheck(id,mensajeValidacion,bandera){
if (bandera && dijit.byId(id).get('item')== null)
		return "<li>"+mensajeValidacion + "</li>";
	else
		return "";
}

function chkSinExperienciaLaboral(habilita) {
	var elements = dijit.byId('experienciaForm').getDescendants();
	//Si no tiene estudios el formulario se deshabilita todo.
	for (i = 0; i < elements.length; i++) {
	var element = elements[i];
	if (element.type != 'hidden' && 
		element.type != 'button' &&		
		element.name != 'nuncaHeTrabajado') {
			//Habilita o deshabilita el widget
			setEnableWg(element.id, !habilita);			
		}	
	}	
	setEnableHTML('logro', !habilita);
	setEnableHTML('funcion', !habilita);	
	setOcultaHTML('trabajoLink', !habilita);
	
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

	function setOcultaHTML(id, habilita) {
		if(!habilita)	
			document.getElementById(id).style.display='none';
		else
			document.getElementById(id).style.display='block';
	}
	function requerirFechaInicial() {
		var trabaja = dijit.byId('trabajoActual');
		if (trabaja.checked) {
			document.getElementById('lblFechaInicial').innerHTML = 'Fecha inicial*';
			dijit.byId('laboresInicial').attr('required', true);
		}else{
			document.getElementById('lblFechaInicial').innerHTML = 'Fecha inicial';
			dijit.byId('laboresInicial').attr('required', false);
		}
	}

	function doSubmitExper() {
		if(validarExperForm()) {
		   	document.experienciaForm.elements[0].value = 'registrar';
		   	//dijit.byId('idExperienciaTotal').set('value', 
		   	//dijit.byId('idExperienciaTotalSelect').get('value'));
		   	//dijit.byId('idSectorMayorExpr').set('value', 
		   	//dijit.byId('idSectorMayorExprSelect').get('value'));
		   	//dijit.byId('idAreaLaboralMayorExpr').set('value', 
		   	//dijit.byId('idAreaLaboralMayorExprSelect').get('value'));
		   	//dijit.byId('idOcupacionMayorExpr').set('value', 
		   	//dijit.byId('idOcupacionMayorExprSelect').get('value'));
		   	//dijit.byId('idSector').set('value', 
		   	//dijit.byId('idSectorSelect').get('value'));
		   	//dijit.byId('idAreaLaboral').set('value', 
		   	//dijit.byId('idAreaLaboralSelect').get('value'));
		   	//dijit.byId('idOcupacionDeseada').set('value', dijit.byId('idOcupacionSelect').get('value'));
		   	//dijit.byId('aniosLaborados').set('value', 
		   	//dijit.byId('aniosLaboradosSelect').get('value'));

			if (dojo.byId('idOcupacionDeseada') && (dojo.byId('idOcupacionDeseada').value=='' || dojo.byId('idOcupacionDeseada').value=='0')){
				var msgerr = "Se requiere seleccionar una opción valida del catalogo de ocupaciones";
				alertMsg(msgerr);
				dijit.byId('ocupacionDeseada').focus();
				displayErrorMsg(dijit.byId('ocupacionDeseada'), msgerr);
				return false;
			}

			if (dojo.byId('ocupacionDeseada') && dojo.byId('ocupacionDeseada').value==''){
				alertMsg(dijit.byId('ocupacionDeseada').get("missingMessage"));
				return false;
			}
			
			dojo.byId('btnGuardar').disabled=true;		
		   	dijit.byId('idExperienciaTotal').set('value', dijit.byId('idExperienceJob1').get('value'));
			dijit.byId('idExperienciaOcupacion2').set('value', dijit.byId('idExperienceJob2').get('value'));
			dijit.byId('idExperienciaOcupacion').set('value', dijit.byId('idExperienceJob1').get('value'));
			   	
		   	
	
		   	dijit.byId('idJerarquia').set('value', dijit.byId('idJerarquiaSelect').get('value'));
		   	dijit.byId('personasCargo').set('value', dijit.byId('personasCargoSelect').get('value'));
		   //dijit.byId('idTipoEmpleoDeseado').set('value', 
		   //	dijit.byId('idTipoEmpleoDeseadoSelect').get('value'));
		   	dijit.byId('idTipoContrato').set('value', dijit.byId('idTipoContratoSelect').get('value'));
		   	var dayiniAdd=document.getElementById('laboresInicialDia').value;
			var monthiniAdd=document.getElementById('laboresInicialMes').value;
			var yeariniAdd=document.getElementById('laboresInicialAnual').value;
			var laboresInicial = yeariniAdd + '-' + monthiniAdd + '-' + dayiniAdd;
			if (dayiniAdd != 0)
				dijit.byId('laboresInicial').set('value', laboresInicial);			
			var dayfinAdd=document.getElementById('laboresFinalDia').value;
			var monthfinAdd=document.getElementById('laboresFinalMes').value;
			var yearfinAdd=document.getElementById('laboresFinalAnual').value;
			var laboresFinal = yearfinAdd + '-' + monthfinAdd + '-' + dayfinAdd;
			if (dayfinAdd != 0)
				dijit.byId('laboresFinal').set('value', laboresFinal);
		  	if (dijit.byId('confidencialidadEmpresa').checked) {
		   		dijit.byId('confidencialidadEmpresa').set('value', '${mostrarEmpSI}');
		   	} else {
		   		dijit.byId('confidencialidadEmpresa').set('value', '${mostrarEmpNO}');
		   	}
		  	
		  	
		  	//validar empresa
		
		   	dojo.xhrPost({
				url: 'experiencia.do',
			  	form:'experienciaForm',
			  	timeout:180000,
			  	load: function(data) {
					var res = dojo.fromJson(data);
					dijit.byId('idHistorialLaboral').set('value', res.idHistorialLaboral);
					if (res.msg.type == 'ext') {
						messageRuta2BtnEditarPerfil(res.msg.message,"<%=response.encodeURL(context + "expectativa.do?method=init")%>","<%=response.encodeURL(context + "ofertasPerfiles.do?method=init&tipoConsulta=1")%>");
					}
					else
					{
						message(res.msg.message);	
					}
					//mensaje(res.msg.message);
					//forward(data);
					//setTimeout("forward('"+data+"');",5000);
			    }
			});
		}
	}
	function mensaje(mensaje) {	
		//dojo.byId('mensaje').innerHTML = mensaje;
		//dijit.byId('MensajeAlert').show();
		message(mensaje);
	}

	function alertMsg(msg){
		message(msg);
	}

	function validarDatoPorDato() {
		/*var varMenajes = validarIdMensaje();	
		var aux = '<p>Los campos con * son obligatorios</p>' + varMenajes;
		if ("" != varMenajes){
			mensaje(aux);
			return false;
		}else{
	 		return true;
		}*/
		return validateData();
	}
	function autocompleteOcupacion() {
  		var search = dojo.byId('ocupacionDeseada').value;
  		search = dojo.trim(search);
  		console.log(search +"-> "+ search.length);
  		
  		if(search.length < 2) {
  		
  			cerrarAutocomplete();
  		
  		}else if(search.length >= 2){
  			
  			dojo.xhrPost({url: '${context}registro.do?method=ocupaciones&search='+search, handleAs: "text",
  						 headers: { "Content-Type": "application/x-www-form-urlencoded; charset=ISO-8859-1" },
  						 load: function(result) {
  							   		if(result.length >0) {
  										dojo.byId('ocupacionesListDiv').style.display='block';
  										dojo.byId('listaOcupaciones').innerHTML = result;
  									}else{
  										cerrarAutocomplete();
  									}
  								}
  						});
  		}
  	}
  	function setOcupacion(id,val) {
	  	console.log("--> id:"+ id +" "+ val);
  		dojo.byId('idOcupacionDeseada').value = id;
  		dojo.byId('ocupacionDeseada').value = val;
  		dojo.byId('ocupacionDeseada').focus();

  		cerrarAutocomplete();
  	}
  	function cerrarAutocomplete() {
  		dojo.byId('ocupacionesListDiv').style.display='none';
  		dojo.byId('listaOcupaciones').innerHTML = "";
  	}
  	
  	function setOcupacion2(id, val) {
		//console.log("--> id:"+ id +" "+ val);
		dojo.byId('idOcupacionDeseada2').value = id;
		
		dojo.byId('ocupacionDeseada2Select').value = val;
		dojo.byId('ocupacionDeseada2Select').focus();

		cerrarAutocomplete2();
	}
	
	function cerrarAutocomplete2() {
		
		
		dojo.byId('listaOcupaciones2').innerHTML = "";
		dojo.byId('ocupacionesListDiv2').style.display = 'none';
	}
	
	function cerrarAutocompleteOccupacion2(){
		
		dojo.byId('listaOcupaciones2').innerHTML = "";
		dojo.byId('ocupacionesListDiv2').style.display = 'none';
	}

  	function validateData() {
  	 	//var ocupation = document.getElementById('ocupacion');
  	 	var description = document.getElementById('experiencia');
  	 	var salary = document.getElementById('salarioPretendido');
  	 	/*if (!ocupation.value) {
  	 	 message('Es necesario indicar el empleo que desea buscar.');
  	 	 ocupation.focus();
  	 	 return false;
  	 	}*/
  	 	if (dijit.byId('idExperienceJob1').get('item')== null || dijit.byId('idExperienceJob1').get('item').value == 0) {
	  	  message('Debe seleccionar años de experiencia en el puesto.');
	  	 	 return false;
		}

  	 	if (!salary.value) {
  	  	 	 message('Es necesario indicar el salario deseado.');
  	  	 	 salary.focus();
  	  	 	 return false;
  	  	 	}
		// TODO: Validate if idOcupacionDeseada2 and idExperienciaOcupacion2 have values
		
		if(!validaCampoSelect('idOcupacionDeseada2')){
			return false;
		}
		
		//var control = dijit.byId('idOcupacionDeseada2');
		var control2 = dijit.byId('ocupacionDeseada2Select');
		var control3 = dijit.byId('idExperienceJob2');

		if(!(control2.value == '' && control3.get('value') == '')){
  	 		if (!validaSegundoTrabajoDeseado())
				return false;
		}
  	 	if (!description.value) {
  	 	 message('Es necesario indicar la descripción de la experiencia.');
  	 	 description.focus();
  	 	 return false;
  	 	}
  	 	
  	 	return true;
  	 }
  	
  	
  	function validaSegundoTrabajoDeseado() {
		//Validar si esta bien la primero opcion capturado si no esta piden primero llenar primero
		//Validar los datos OtroOpción de empleo despues validacion de ocupaciondeseada

		//validacion si tiene una ocupacion selecionado entonces debe estar seleciona
		// Si el combo no esta selecionado y la experiencia es vaico es valido

		if (!validaFilterSelect('idOcupacionDeseada2',
				'ocupacionDeseada2Select', 'listaOcupaciones2', 'setOcupacion2')) {
			var msgerr = "Se requiere seleccionar una opción válida del catalogo de ocupaciones";
			
			dijit.byId('ocupacionDeseada2Select').focus();
			displayErrorMsg(dijit.byId('ocupacionDeseada2Select'), msgerr);
			message(msgerr);
			return false;

		} else {

			var control = dojo.byId('ocupacionDeseada2Select');
			var control2 = dijit.byId('idOcupacionDeseada2');

			//showErrorMsg('else ' + !validaCapoSelectSinError('experiencia2Select') + " : " +control.value == 0 + ":" + control.value=='' +":"+ control2.get('value') == '');
		
		
			if (((control.value == 0 || control.value == '') && control2
					.get('value') == '')
					&& !validaCapoSelectSinError('idExperienceJob2')) {
				return true;
			}

			if (!validaCampoSelect('idExperienceJob2'))
				return false;
		}
		return true;
	}
  	
  	
	function doCancel() {
		/**if (confirm('Los datos no guardados se perderán ¿Continuar?')) {
			document.experienciaForm.method.value = 'init';
			document.experienciaForm.submit();
		}**/
		messageRutaCancel('${perfilForm.errorMessageTab}','<c:url value="/escolaridad.do?method=init"/>');
	}
	function validarFechas() {
		var dayiniAdd=dijit.byId('laboresInicialDia').value;
		var monthiniAdd=dijit.byId('laboresInicialMes').value;
		var yeariniAdd=dijit.byId('laboresInicialAnual').value;
		var dayfinAdd=dijit.byId('laboresFinalDia').value;
		var monthfinAdd=dijit.byId('laboresFinalMes').value;
		var yearfinAdd=dijit.byId('laboresFinalAnual').value;
		var inicialdate = new Date(yeariniAdd, monthiniAdd, dayiniAdd);
		var finaldate = new Date(yearfinAdd, monthfinAdd, dayfinAdd);
		var today = new Date();
		var fhInicialdate = new Date();
		var fhFinaldate = new Date();
		fhInicialdate.setFullYear(yeariniAdd,--monthiniAdd,dayiniAdd);
		fhFinaldate.setFullYear(yearfinAdd,--monthfinAdd,dayfinAdd);
		var dif = dojo.date.compare(finaldate, inicialdate, 'date');
		if (fhInicialdate > today){
			message('La fecha de ingreso no puede ser mayor al dia de hoy.');
			return false;
		}
		if (fhFinaldate > today){
			message('La fecha de terminación no puede ser mayor al dia de hoy.');
			return false;
		}
		var valido = dif >= 0;
		if (!valido) {
			message('La fecha de terminación debe ser mayor a la fecha de ingreso');		
		}
		return valido;
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
  	
  	
  	function autocompleteOcupacion2() {
		var search = dojo.byId('ocupacionDeseada2Select').value;
		search = dojo.trim(search);
		//console.log(search +"-> "+ search.length);

		if (search.length < 4) {
			
			cerrarAutocomplete2();
			dojo.byId('idOcupacionDeseada2').value = 0;
			

		} else if (search.length >= 4) {

			dojo
					.xhrPost({
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
								dojo.byId('listaOcupaciones2').innerHTML = result;
							} else {
								cerrarAutocomplete2();
							}
						}
					});
		}
	}

	
	function mostrarOtraOpcionDeEmpleo() {
		dojo.byId('otra_opcion_empleo').style.display = 'block';
		dojo.byId('agreagarOtraOpcionEmpleo').style.display = 'none'
	}

</script>
<noscript>Funciones de JavaScript desactivadas por navegador</noscript>

<div class="formApp miEspacio">
	<form name="experienciaForm" id="experienciaForm" method="post"
		action="experiencia.do" dojoType="dijit.form.Form">
		<input type="hidden" name="method" id="method" value="init" /> <input
			type="hidden" name="idOcupacionDeseada" id="idOcupacionDeseada"
			value="${experienciaForm.idOcupacionDeseada}"
			dojoType="dijit.form.TextBox" /> <input type="hidden"
			name="idOcupacionDeseada2" id="idOcupacionDeseada2"
			value="${experienciaForm.idOcupacionDeseada2}"
			dojoType="dijit.form.TextBox" /> <input type="hidden"
			name="idHistorialLaboral" id="idHistorialLaboral"
			value="${experienciaForm.idHistorialLaboral}"
			dojoType="dijit.form.TextBox" /> <input type="hidden"
			name="idExperienciaTotal" id="idExperienciaTotal"
			value="${experienciaForm.idExperienciaTotal}"
			dojoType="dijit.form.TextBox" /> <input type="hidden"
			name="idExperienciaOcupacion" id="idExperienciaOcupacion"
			value="${experienciaForm.idExperienciaOcupacion}"
			dojoType="dijit.form.TextBox" /> <input type="hidden"
			name="idExperienciaOcupacion2" id="idExperienciaOcupacion2"
			value="${experienciaForm.idExperienciaOcupacion2}"
			dojoType="dijit.form.TextBox" /> <input type="hidden"
			name="laboresInicial" id="laboresInicial" value=""
			dojoType="dijit.form.TextBox" /> <input type="hidden"
			name="laboresFinal" id="laboresFinal" value=""
			dojoType="dijit.form.TextBox" />






		<div class="experiencia_laboral">
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
								href="javascript:messageRutaCancel('${experienciaForm.errorMessageTab}','<%=response.encodeURL(context +"perfil.do?method=init")%>');">Datos
									personales</a></li>
							<li><a
								href="javascript:messageRutaCancel('${experienciaForm.errorMessageTab}','<%=response.encodeURL(context +"escolaridad.do?method=init")%>');">Escolaridad
									y otros conocimientos</a></li>
							<li class="curSubTab"><span>Experiencia y
									expectativas laborales</span></li>
							<li><a
								href="javascript:messageRutaCancel('${experienciaForm.errorMessageTab}','<%=response.encodeURL(context +"expectativa.do?method=init")%>');">Situación
									laboral</a></li>
						</ul>
						<div class="clearfix"></div>
					</div>
					<div class="clearfix"></div>
				</div>
				<!--  
	        <div class="sublevelTitle">
	            Ofertas de acuerdo a mi perfil
	        </div>-->
				<p class="labeled">
					Los datos marcados con <span>*</span> son obligatorios
				</p>
			</div>
			<fieldset>
				<!-- Contenedor para almacenar una lista (tiempo de experiencia) obtenida via Ajax -->
				<div dojoType="dojo.data.ItemFileReadStore"
					jsId="experienceJobStore" urlPreventCache="true"
					clearOnClose="true"></div>

				<legend>Expectativas laborales</legend>
				<div class="grid1_3 margin_top_20">
					<label for="ocupacionDeseada"><span>*</span> ¿Qué empleo
						buscas?</label> <input type="text"
						value="${experienciaForm.ocupacionDeseada}"
						dojoType="dijit.form.ValidationTextBox" name="ocupacionDeseada"
						id="ocupacionDeseada"
						onkeyup="javascript:autocompleteOcupacion();" size="50"
						maxlength="45" oncopy="return false;" oncut="return false"
						onpaste="return false;" trim="true;" required="true"
						missingMessage="Es necesario indicar que empleo busca."
						invalidMessage="Se requiere seleccionar una opción del catálogo." />
					&nbsp;&nbsp;
					<div class="suggestionsBox" id="ocupacionesListDiv"
						onblur="javascript:cerrarAutocomplete();" style="display: none">
						<div class="suggestionList" id="areaMensajes">
							<ul id="listaOcupaciones">
							</ul>
						</div>
					</div>
				</div>


				<div class="margin_top_20">
					<div class="grid1_3 fl">
						<label for="salarioPretendido"><span>*</span> ¿Qué salario mensual pretendes?</label>
						<input type="text"
							name="salarioPretendido" id="salarioPretendido"
							value="${experienciaForm.salarioPretendido}" maxlength="9"
							style="width: 10em;" dojoType="dijit.form.ValidationTextBox"
							regExp="^[+]?\d{1,6}(\.\d{1,2})?$" required="true"
							missingMessage="Debe indicar el salario mensual pretendido." />
					</div>
					<div class="grid1_3 fl margin_no_r">
						<label for="idTipoContratoSelect">Tipo de contrato</label> <input
							type="hidden" name="idTipoContrato" id="idTipoContrato"
							dojoType="dijit.form.TextBox" /> <select
							dojotype="dijit.form.FilteringSelect"
							value="${experienciaForm.idTipoContrato}"
							id="idTipoContratoSelect" required="false"
							missingMessage="Debe seleccionar el tipo de contrato."
							autocomplete="true" class="${classTipoCon}">
							<c:forEach var="row" items="${tiposcontrato}">
								<option value="${row.idCatalogoOpcion}">${row.opcion}</option>
							</c:forEach>
						</select>
					</div>
					<div class="clearfix"></div>

					<div class="grid1_3 fl margin_top_20">
						<label for="idExperienceJob1"><span>*</span>Años de
							experiencia</label> <select id="idExperienceJob1"
							name="experiencia1Select" dojotype="dijit.form.FilteringSelect"
							store="experienceJobStore" required="true"
							missingMessage="Debe seleccionar los años que tiene de experiencia en el puesto."
							autocomplete="true" value="${experienciaForm.idExperienciaTotal}">
						</select>
					</div>
				</div>

				<div class="margin_top_50">
					<input type="button" value="Agregar otra opción de empleo"
						id="agreagarOtraOpcionEmpleo"
						onclick="mostrarOtraOpcionDeEmpleo();" />
					<div class="clearfix"></div>
					<div class="otra_opcion" id="otra_opcion_empleo">
						<div class="grid1_3 margin_top_20">
							<label>¿Qué otra opción de empleo te interesa?</label> <input
								type="text" dojoType="dijit.form.ValidationTextBox"
								name="ocupacionDeseada2Select" id="ocupacionDeseada2Select"
								onkeyup="javascript:autocompleteOcupacion2();" size="50"
								maxlength="45" oncopy="return false;" 
								oncut="return false"
								onpaste="return false;" 
								trim="true;" 
								required="true"
								missingMessage="Es necesario indicar que otro empleo busca."
								invalidMessage="Se requiere seleccionar una opción del catálogo."
								value="${experienciaForm.ocupacionDeseada2 != 0 ? experienciaForm.ocupacionDeseada2 : ''}" />
							<div class="suggestionsBox" id="ocupacionesListDiv2"
								onblur="javascript:cerrarAutocompleteOccupacion2();"
								style="display: none">
								<div class="suggestionList" id="areaMensajes">
									<ul id="listaOcupaciones2"
										style="height: 200px; overflow: auto">
									</ul>
								</div>
							</div>
						</div>

						<div class="grid1_3 margin_top_20">
							<label for="idExperienceJob2"><span>*</span>Años de
								experiencia</label> <select id="idExperienceJob2"
								name="idExperienceJob2" dojotype="dijit.form.FilteringSelect"
								store="experienceJobStore" required="true"
								missingMessage="Debe seleccionar los años de experiencia."
								autocomplete="true"
								value="${experienciaForm.idExperienciaOcupacion2}">
							</select>
						</div>
					</div>
				</div>

				<div class="margin_top_30">
					<label for="experiencia"><span>*</span> Describe tu
						experiencia</label>
					<p>Describe lo que sabes hacer relacionado al empleo que
						buscas; Ejemplo de Auxiliar Administrativo: elaboraciones de
						nómina, altas, bajas y modificaciones en el Seguro Social;
						INFONAVIT, pago de nómina, cuentas por cobrar; cartera de
						clientes, manejo de Office y paquetería contable, saber trabajar
						en equipo, iniciativa, creativo, etc.</p>
					<div class="campoTexto">
						<textarea class="textGoogie" rows="4" cols="70" name="experiencia" id="experiencia"
							onkeypress="return caracteresValidos(event);" trim="true"
							onchange="return maximaLongitud(this,2000)"
							onKeyUp="return maximaLongitud(this,2000)" maxlength="2000"
							required="false"
							regExp="^[\w\d\s\.&aacute;&eacute;&iacute;&oacute;&uacute;&Aacute;&Eacute;&Iacute;&Oacute;&Uacute;&Ntilde;&ntilde;]{1,2000}$">${experienciaForm.experiencia}</textarea>
						<script type="text/javascript">
		         			var vSpellCon = new GoogieSpell("googiespell/", '<%=vProxy%>');
		         			vSpellCon.setLanguages({'es': 'Español'});
	   						vSpellCon.hideLangWindow();
	  						vSpellCon.decorateTextarea("experiencia");
						</script>
					</div>
					<div class="margin_40"></div>
				</div>

				<div class="margin_top_30 disponib_c">
					<div class="grid1_3 fl">
						<div class="labeled">
							<span>*</span> ¿Puedes viajar?
						</div>
						<div>

							<input class="fl" type="radio"
								data-dojo-type="dijit.form.RadioButton"
								name="disponibilidadViajar" id="disponibilidadViajar1"
								value="${dispViajarSI}"
								${experienciaForm.disponibilidadViajar eq dispViajarSI ? 'checked="checked"' : '' } />
							<label for="disponibilidadViajar1">Si</label> <input class="fl"
								type="radio" data-dojo-type="dijit.form.RadioButton"
								name="disponibilidadViajar" id="disponibilidadViajar2"
								value="${dispViajarNO }"
								${experienciaForm.disponibilidadViajar eq dispViajarNO ? 'checked="checked"' : '' } />
							<label for="disponibilidadViajar2">No</label>
						</div>
					</div>
					<div class="grid1_3 fl">
						<div class="labeled">
							<span>*</span> ¿Puedes radicar en otra ciudad?
						</div>
						<div>

							<input class="fl" type="radio"
								data-dojo-type="dijit.form.RadioButton"
								name="disponibilidadRadicar" id="disponibilidadRadicar1"
								value="${dispRadicarSI}"
								${experienciaForm.disponibilidadRadicar eq dispRadicarSI ? 'checked="checked"' : '' } />
							<label for="disponibilidadRadicar1">Si</label> <input class="fl"
								type="radio" data-dojo-type="dijit.form.RadioButton"
								name="disponibilidadRadicar" id="disponibilidadRadicar2"
								value="${dispRadicarNO}"
								${experienciaForm.disponibilidadRadicar eq dispRadicarNO ? 'checked="checked"' : '' } />
							<label for="disponibilidadRadicar2">No</label>
						</div>

					</div>


				</div>
			</fieldset>

			<fieldset class="ultimo_trabajo bloque">
				<legend>Trabajo actual o último</legend>
				<div class="grid1_3 fl">
					<label for="empresa"><c:if test="${experienciaForm.ppc}">
							<span>*</span>
						</c:if>Nombre o razón social de la empresa</label> <input type="text"
						name="empresa" id="empresa" value="${experienciaForm.empresa}"
						maxlength="150" class="${classEmpresa}"
						regExp="^[A-Za-z0-9\s\d\-.&,áéíóúÁÉÍÓÚñÑ']{3,150}$"
						dojoType="dijit.form.ValidationTextBox" 
						<c:if test="${experienciaForm.ppc}">
							required="true" 
						</c:if>
						invalidMessage="El Nombre o	razón social de la empresa es invalido, favor de verificar"
						missingMessage="Es necesario indicar que Nombre o razón social de la empresa." />
					</p>
				</div>
				<div class="grid1_3 omitir margin_top_20 fl">
					<input class="fl" type="checkbox" name="confidencialidadEmpresa"
						id="confidencialidadEmpresa"
						${experienciaForm.confidencialidadEmpresa eq mostrarEmpSI ? 'checked="checked"' : '' }
						dojoType="dijit.form.CheckBox" required="false" /> <label
						class="fw_no_bold" for="confidencialidadEmpresa">Omitir
						este dato en la publicación de mi perfil</label>
				</div>
				<div class="clearfix"></div>
				<div class="grid1_3 fl">
					<label for="puesto"><c:if test="${experienciaForm.ppc}">
							<span>*</span>
						</c:if>Puesto desempeñado</label> <input type="text" name="puesto" id="puesto"
						maxlength="150" regExp="^[A-Za-z\s\-.áéíóúÁÉÍÓÚñÑ']{1,150}$"
						value="${experienciaForm.puesto}"
						dojoType="dijit.form.ValidationTextBox" 
						class="${classPuesto}" 
						<c:if test="${experienciaForm.ppc}">
							required="true" 
						</c:if>
						invalidMessage="El valor especificado puesto desempeñado no es válido."
						missingMessage="Es necesario selecionar el puesto desempeñado."
						 />
						
				</div>
				<div class="grid1_3 fl">
					<label for="idJerarquiaSelect"><c:if
							test="${experienciaForm.ppc}">
							<span>*</span>
						</c:if>Jerarquía del puesto</label>
						<input type="hidden" name="idJerarquia"
						id="idJerarquia" dojoType="dijit.form.TextBox" /> <select
						dojotype="dijit.form.FilteringSelect" id="idJerarquiaSelect"
						value="${experienciaForm.idJerarquia}" 
						required="${experienciaForm.ppc}"
						invalidMessage="El valor especificado jerarquía del puesto no es válido."
						missingMessage="Es necesario selecionar el Jerarquía del puesto."
						autocomplete="true" class="${classJerar}">
						<c:forEach var="row" items="${jerarquias}">
							<option value="${row.idCatalogoOpcion}">${row.opcion}</option>
						</c:forEach>
					</select>
				</div>
				<div class="clearfix"></div>
				<div class="margin_top_30">
					<div class="grid1_3 fl">
						<label for="personasCargoSelect"><c:if
								test="${experienciaForm.ppc}">
								<span>*</span>
							</c:if>Número de personas a cargo</label> <input type="hidden"
							name="personasCargo" id="personasCargo"
							dojoType="dijit.form.TextBox" /> <select
							dojotype="dijit.form.FilteringSelect" id="personasCargoSelect"
							value="${experienciaForm.personasCargo}" 
							required="${experienciaForm.ppc}"
						missingMessage="Es necesario seleccionar el número de personas a cargo."
							autocomplete="true" class="${classCargo}">
							<c:forEach var="row" items="${personascargo}">
								<option value="${row.idCatalogoOpcion}">${row.opcion}</option>
							</c:forEach>
						</select>
					</div>
					<div class="grid1_3 fl">
						<label for="salarioMensual"><c:if
								test="${experienciaForm.ppc}">
								<span>*</span>
							</c:if>Salario mensual recibido</label> <input type="text" name="salarioMensual"
							id="salarioMensual" value="${experienciaForm.salarioMensual}"
							dojoType="dijit.form.ValidationTextBox" maxlength="9"
							style="width: 10em;"
							required="${experienciaForm.ppc}"
							invalidMessage="El valor especificado no es válido por Salario Mensual recibido."
							missingMessage="Es neccesario indicar salario mensual recibido."
							regExp="^[+]?\d{1,6}(\.\d{1,2})?$" trim="true"
							class="${classMensual}" />
					</div>
					<div class="clearfix"></div>
				</div>
				<div class="grid1_3 fl margin_top_30">
					<div class="labeled">
						<c:if test="${experienciaForm.ppc}">
							<span>*</span>
						</c:if>
						Fecha de ingreso
					</div>
					<select dojotype="dijit.form.FilteringSelect"
						name="laboresInicialDia" id="laboresInicialDia"
						missingMessage="Es necesario indicar la Fecha de ingreso."
						>
						<option value="0">Día</option>
						<%=getSelectedDay(laboresInicial)%>
					</select> <select dojotype="dijit.form.FilteringSelect"
						name="laboresInicialMes" id="laboresInicialMes"
						missingMessage="Es necesario indicar la Fecha de ingreso.">
						<option value="0">Mes</option>
						<%=getSelectedMonth(laboresInicial) %>
					</select> <select dojotype="dijit.form.FilteringSelect"
						name="laboresInicialAnual" id="laboresInicialAnual"
						missingMessage="Es necesario indicar la Fecha de ingreso.">
						<option value="0">Año</option>
						<%=getSelectedYear(laboresInicial) %>
					</select>
				</div>
				<div class="grid1_3 fl margin_top_30">
					<div class="labeled">
						<c:if test="${experienciaForm.ppc}">
							<span>*</span>
						</c:if>
						Fecha de terminación
					</div>
					<select dojotype="dijit.form.FilteringSelect"
						name="laboresFinalDia" id="laboresFinalDia"
						missingMessage="Es necesario indicar la Fecha de terminación.">
						<option value="0">Día</option>
						<%=getSelectedDay(laboresFinal)%>
					</select> <select dojotype="dijit.form.FilteringSelect"
						name="laboresFinalMes" id="laboresFinalMes"
						missingMessage="Es necesario indicar la Fecha de terminación.">
						<option value="0">Mes</option>
						<%=getSelectedMonth(laboresFinal) %>
					</select> <select dojotype="dijit.form.FilteringSelect"
						name="laboresFinalAnual" id="laboresFinalAnual"
						missingMessage="Es necesario indicar la Fecha de terminación.">
						<option value="0">Año</option>
						<%=getSelectedYear(laboresFinal) %>
					</select>
				</div>
				<div class="clearfix"></div>
				<div class="funciones_01 margin_top_30">
					<label for="funcion"><c:if test="${experienciaForm.ppc}">
							<span>*</span>
						</c:if>Funciones desempeñadas</label>
					<div class="campoTexto">
						<textarea name="funcion" id="funcion" maxlength="2000"
							onkeypress="return caracteresValidos(event);" trim="true"
							onchange="return maximaLongitud(this,2000)"
							onKeyUp="return maximaLongitud(this,2000)"
							regExp="^[\w\d\s\.&aacute;&eacute;&iacute;&oacute;&uacute;&Aacute;&Eacute;&Iacute;&Oacute;&Uacute;&Ntilde;&ntilde;]{1,2000}$"
							class="textGoogie" require="false">${experienciaForm.funcion}</textarea>
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
			<div class="form_nav">
				<input type="button" id="btnGuardar" value="Guardar"
					onclick="doSubmitExper();" class="boton_naranja"> <input
					type="button" value="Cancelar" onclick="doCancel();"
					class="boton_naranja">
			</div>
			<div class="clearfix"></div>
		</div>
		<%--
	<div dojoType="dijit.Dialog" id="MensajeAlert" title="Mensaje" style="display:none" draggable ="false" >
	  <table class="alertDialog" >
		 <tr align="center">
			 <td><div id ="mensaje"></div>&nbsp;</td>				 
		 </tr>
	 </table>	
	</div>
	--%>
		<!--  parar crear el popup del texto -->
		<div id="holderMensaje" class="holder_mensaje"
			style="display: none; text-align: center;">
			<div class="posicion">
				<p class="mensaje_error" id="errorMsgArea"
					style='text-align: center;'>
					<input id="btnCerrar" name="btnCerrar" class="boton_naranja"
						type="button" value="Cerrar" onclick="cerrarError();" />
				</p>
			</div>
		</div>

	</form>
</div>

<script>
	dojo.addOnLoad(function() {
		experienceJobStore.url = "<%=context%>registro.do?method=experiencia";
		experienceJobStore.close();
		experienceJobStore.fetch({
			onComplete : function(items, request) {
				if (items.length != 0) {
					dijit.byId('idExperienceJob1').attr('value',
							'${experienciaForm.idExperienciaTotal}');
					dijit.byId('idExperienceJob2').attr('value',
							'${experienciaForm.idExperienciaOcupacion2}');
				}
			}
		})
	});
</script>
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
	for (int i = 1979; i<cyear+1; i++) {
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
