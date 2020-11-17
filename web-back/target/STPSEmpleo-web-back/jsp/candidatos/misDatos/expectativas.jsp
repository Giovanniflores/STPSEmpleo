<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.Calendar, java.util.Date, java.text.DateFormat, java.text.SimpleDateFormat, java.text.ParseException, mx.gob.stps.portal.utils.Catalogos.TRABAJA_ACTUALMENTE"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" 		prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" 		prefix="fmt"%>

<%
	String context = request.getContextPath() +"/";
	pageContext.setAttribute("context", context);
	String inicioBusqueda = null != session.getAttribute("inicioBusqueda") ? (String)session.getAttribute("inicioBusqueda") : "";
%>
<script type="text/javascript" src="js/helper/messageHelper.js"></script>
<script type="text/javascript" src="js/helper/dateHelper.js"></script>

<script type="text/javascript">
    dojo.require("dijit.dijit"); // loads the optimized dijit layer
    dojo.require("dijit.form.Form");
    dojo.require("dijit.form.ValidationTextBox");
    dojo.require("dijit.form.TextBox");
	dojo.require("dojo.data.ItemFileReadStore");
	dojo.require("dijit.form.FilteringSelect");
	dojo.require('dijit.Dialog');
	dojo.require("dijit.form.CheckBox");
	dojo.require("dijit.form.DateTextBox");
	dojo.require("dijit.form.MultiSelect");
	dojo.require("dijit.form.Select");
	
	dojo.addOnLoad(function() {
		
		dojo.connect(dijit.byId('idTrabaja1'), 'onClick', function() {
			var vAux = dijit.byId('idTrabajaAux').get('value');
			var idTrabaja = dijit.byId('idTrabaja1').get('value');
			if (idTrabaja != vAux) {
				//dijit.byId('idRazonBusquedaSelect').reset();
				dijit.byId('idRazonBusquedaSelect').set('value',"");
				document.getElementById('idRazonBusquedaSelect').value = "";
				buscaTrabajoStore.url = "";
           		buscaTrabajoStore.close();
				buscaTrabajoStore.url = "${context}perfil.do?method=buscaTrabajo&idTrab="+ idTrabaja;
           		buscaTrabajoStore.close();
           		dijit.byId('idTrabajaAux').set('value', idTrabaja);
			}	
		});
	
		dojo.connect(dijit.byId('idTrabaja2'), 'onClick', function() {
			var vAux = dijit.byId('idTrabajaAux').get('value');
			var idTrabaja = dijit.byId('idTrabaja2').get('value');
			if (idTrabaja != vAux) {
				//dijit.byId('idRazonBusquedaSelect').reset();
				dijit.byId('idRazonBusquedaSelect').set('value',"");
				document.getElementById('idRazonBusquedaSelect').value = "";
				buscaTrabajoStore.url = "";
           		buscaTrabajoStore.close();
				buscaTrabajoStore.url = "${context}perfil.do?method=buscaTrabajo&idTrab="+ idTrabaja;
            	buscaTrabajoStore.close();
            	dijit.byId('idTrabajaAux').set('value', idTrabaja);
			}
		});
	
		requerirRazon();
		cargaCatalogoTrabajo();
		dojo.connect(dijit.byId("idAreaLaboralDeseadaSelect"), "onChange", function() {
			dijit.byId('idOcupacionDeseadaSelect').reset();
			var vArea = dijit.byId('idAreaLaboralDeseadaSelect').get('value');

            if(vArea != 0) {
            	ocupacionStore2.url = "${context}histLaborales.do?method=cargarOcupacion&idAreaLaboral="+ vArea;
            	ocupacionStore2.close();
			}
        });
        
        dojo.connect(dijit.byId("idEntidadDeseadaSelect"), "onChange", function() {
			dijit.byId('idMunicipioDeseadoSelect').reset();
			var vEntidad = dijit.byId('idEntidadDeseadaSelect').get('value');
			
            if(vEntidad != 0) {
            	municipioStore1.url = "${context}expecLugar.do?method=obtenerMunicipio&idEntidad="+ vEntidad;
            	municipioStore1.close();
			}
        });

		dijit.byId("idRazonBusquedaSelect").on('change', function(evt){
			var motivo = dijit.byId('idRazonBusquedaSelect').get('value');
			if (motivo == 18) { // Otro
				dojo.style(dojo.byId('motivoOtroContainer'), "display", "inline");
			} else {
				dojo.style(dojo.byId('motivoOtroContainer'), "display", "none");
			}
		});
	});
	
//--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*	

function cargaCatalogosEntidad(){
	entidadStore.fetch({
			onComplete: function(items, request) {
	      if (items.length == 0) {
	         return;
	      }
	      var vEntidad = '${expectativaForm.idEntidadDeseada}';
	      dijit.byId('idEntidadDeseadaSelect').attr('value', vEntidad);
	      
	      if(vEntidad != 0) {
        	municipioStore1.url = "${context}expecLugar.do?method=obtenerMunicipio&idEntidad="+ vEntidad;
        	municipioStore1.close();
        	municipioStore1.fetch({
					onComplete: function(items, request) {
					if (items.length == 0) {
			        	return;
			    	}
			    	dijit.byId('idMunicipioDeseadoSelect').attr('value', '${expectativaForm.idMunicipioDeseado}');
					}
			});
		  }
			}
	});
}

function checkOtrosMedios(checkbox_id){
	if(checkbox_id == "idOtrosMedios1"){
		if(dijit.byId('idOtrosMedios1').get("checked")){
			var media = document.expectativaForm.idOtrosMedios;
	
			if (media && media.length){
				for (var i=2;i<media.length+1;i++) {
					dijit.byId('idOtrosMedios'+i).set("checked",false);
					
				}			
			}
		}
	}
	else
	{
		if(dijit.byId(checkbox_id).get("checked")){
			dijit.byId('idOtrosMedios1').set("checked",false);
		}
		
	}
}


function cargaCatalogosAreaLaboral(){
areaLaboralStore1.fetch({
		onComplete: function(items, request) {
      if (items.length == 0) {
         return;
      }
      var vArea = '${expectativaForm.idAreaLaboralDeseada}';
      dijit.byId('idAreaLaboralDeseadaSelect').attr('value', vArea);
      
      if(vArea != 0) {
    	ocupacionStore2.url = "${context}histLaborales.do?method=cargarOcupacion&idAreaLaboral="+ vArea;
    	ocupacionStore2.close();
    	ocupacionStore2.fetch({
				onComplete: function(items, request) {
				if (items.length == 0) {
		        	return;
		    	}
		    	dijit.byId('idOcupacionDeseadaSelect').attr('value', '${expectativaForm.idOcupacionDeseada}');
				}
		});
	  }
		}
});
}

//--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*	

function validarRadio(nameGrp) {
	var elements = eval('document.expectativaForm.'+nameGrp);
	var radioChecked = false;
	for (i = 0; i < elements.length; i++) {
		if (elements[i].checked) {
			radioChecked = elements[i].checked;
			break;
		}
	}
	return radioChecked;
}

//Desplegar ventana para captura de expectativas laborales adicionales
function openExpecLaboralWindow() {
  var newWin = window.open('<%=context + "expecLaboral.do?method=init"%>', "Expectativa_Laboral","height=500,width=900,resizable=yes,scrollbars=yes");
}
//Desplegar ventana para captura de expectativas de lugares adicionales
function openExpecLugarWindow() {
  var newWin = window.open('<%=context + "expecLugar.do?method=init"%>', "Expectativa_Laboral","height=500,width=900,resizable=yes,scrollbars=yes");
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
		location.replace('<c:url value="miEspacioCandidato.do"/>');
	}
}

function validarIdMensaje(){
	var mensajeError =  "";	
			/*mensajeError += validarSelect("idSectorDeseadoSelect","Favor de Proporcionar el Sector deseado.");
			mensajeError += validarPorId("puestoDeseado","Favor de Proporcionar el Puesto pretendido.");
			mensajeError += validarSelect("idAreaLaboralDeseadaSelect","Favor de Proporcionar el Área laboral deseada.");
			mensajeError += validarSelect("idOcupacionDeseadaSelect","Favor de Proporcionar la Ocupación deseada.");
			mensajeError += validarPorId("salarioPretendido","Favor de Proporcionar el Salario pretendido.");
			mensajeError += validarSelect("idTipoEmpleoDeseadoSelect","Favor de Proporcionar el Tipo de empleo.");
			mensajeError += validarSelect("idTipoContratoSelect","Favor de Proporcionar el Tipo de contrato");
			mensajeError += validarSelect("idEntidadDeseadaSelect","Favor de Proporcionar la Entidad.");
			mensajeError += validarSelect("idMunicipioDeseadoSelect","Favor de Proporcionar el Municipio.");*/	
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

	function mensaje(mensaje){	
		//dojo.byId('mensaje').innerHTML = mensaje;
		//dijit.byId('MensajeAlert').show();
		message(mensaje);
	}

	function requerirRazon() {
		/*
		var idTrabaja = dijit.byId('idTrabaja1');
		if (idTrabaja.checked) {
			document. getElementById('razonRequerida').innerHTML = 'Motivo por el que buscas trabajo';
			dijit.byId('idRazonBusquedaSelect').attr('required', true);
			
		}else{
			document. getElementById('razonRequerida').innerHTML = '<span>*</span> Motivo por el que buscas trabajo';
			dijit.byId('idRazonBusquedaSelect').attr('required', false);
		}
		*/
	}
	
	function cargaCatalogoTrabajo() {
		buscaTrabajoStore.fetch({
			sync: true,
	   			onComplete: function(items, request) {
			      if (items.length == 0) {
			         return;
			      }			      
			      dijit.byId('idRazonBusquedaSelect').attr('value', '${perfilForm.idRazonBusqueda}');
	   			}
			});
	}

	function validarExpecForm() {
		if (validateData()) {
			if (!validarRadio('disponibilidadViajar')) {
				mensaje('Disponibilidad para viajar es un dato requerido');
				return false;
			}
			if (!validarRadio('disponibilidadRadicar')){
				mensaje('Disponibilidad para radicar es un dato requerido');
				return false;
			}
	    	return true;
	    } else {    	
	    	return false;
	    }
	}
	function doSubmitExpec() {
		if(validateData()) {			
			dojo.byId('btnGuardar').disabled=true;	
		
			document.expectativaForm.method.value = 'registrar';
			
		  // 	document.expectativaForm.elements[0].value = 'registrar';
		   	var dayiniAdd=dijit.byId('inicioBusquedaDia').get('value');
			var monthiniAdd=dijit.byId('inicioBusquedaMes').get('value');
			var yeariniAdd=dijit.byId('inicioBusquedaAnual').get('value');
			var inicioBusqueda = yeariniAdd + '-' + monthiniAdd + '-' + dayiniAdd;
			dijit.byId('inicioBusqueda').set('value', inicioBusqueda);
		   	dijit.byId('idRazonBusqueda').set('value', dijit.byId('idRazonBusquedaSelect').get('value'));
		   	//dijit.byId('idMedioPortal').set('value', dijit.byId('idMediosSelect').get('value'));
		   	dojo.xhrPost( {
				url: 'expectativa.do',
			  	form:'expectativaForm',
			  	timeout:180000,
			  	load: function(data) {
					var res = dojo.fromJson(data);
					if (res.msg.type == 'ext') {
						messageRuta2BtnEditarPerfil(res.msg.message,"<%=response.encodeURL(context+"perfil.do?method=init")%>","<%=response.encodeURL(context+"ofertasPerfiles.do?method=init&tipoConsulta=1")%>");
					}
					else
					{
						message(res.msg.message);	
					}
					
					//mensaje(res.msg.message);
				    //if (res.msg.type == 'ext') {
				    	//forward(data);
				    	//setTimeout("forward('"+data+"');",3000);
					//}
			    }
			});
		}
	}

	function validateData() {

		//Validar si es de PPC
		
		<c:if test = "${perfilForm.ppc == true}">
			//validar si el valor de idTrabajo es no
			
			if(dijit.byId('idTrabaja1').checked){
				message('Debes cambiar el ¿Trabajas actualmente? a no para poder aplicar al programa de PPC');
				return false;
			}
		</c:if>
		
		if (!validarRadio('idTrabaja')) {
			message('Seleccione una opción de ¿trabajas actualmente?');
			return false;
		}
		if (dijit.byId('idRazonBusquedaSelect').get('item')== null || dijit.byId('idRazonBusquedaSelect').get('item').value == 0) {
    		message('Debe seleccionar el motivo por el que busca trabajo.');
			return false;
    	}		
	
		
//		var e = document.getElementById("inicioBusquedaDia");
//		var dia = e.options[e.selectedIndex].value;

//		e = document.getElementById("inicioBusquedaMes");
//		var mes = e.options[e.selectedIndex].value;

//		e = document.getElementById("inicioBusquedaAnual");
//		var anio = e.options[e.selectedIndex].value;
		
//		if (dia=='0'){
//			message('Indicar el dia desde que comenzo a buscar trabajo.');
//			document.getElementById("inicioBusquedaDia").focus();
//			return false;
//		}

//		if (mes=='0'){
//			message('Indicar el mes desde que comenzo a buscar trabajo.');
//			document.getElementById("inicioBusquedaMes").focus();
//			return false;
//		}

//		if (anio=='0'){
//			message('Indicar el año desde que comenzo a buscar trabajo.');
//			document.getElementById("inicioBusquedaAnual").focus();
//			return false;
//		}

//		var fhBuscaEmp = new Date();
		var hoy = new Date();

		var fhBuscaEmp = validateFromCombos("inicioBusquedaDia","inicioBusquedaMes","inicioBusquedaAnual");
		
		if(fhBuscaEmp == 0){
			return false;
		}
		if (fhBuscaEmp > hoy){
			message('La fecha en que comenzaste a buscar trabajo no puede ser mayor al dia de hoy.');
			return false;
		}
		
		
		var count=0;
		var none = false;
		var media = document.expectativaForm.idOtrosMedios;

		if (media && media.length){
			for (var i=0;i<media.length;i++) {
				if (media[i].checked) {
					count++;
					if (media[i].value == 1)
						none = true;
				}
			}			
		}
	
		if (count<1) {
			
			message('Seleccione al menos un medio que haya utilizado para buscar trabajo');
			return false;
		}
		if (none && count > 1) {
			message('Si selecciona ninguno no puede seleccionar otro medio');
			return false;
		}	
		
	    return true;
	}
	
	
	function doCancel() {
		/**if (confirm('Los datos no guardados se perderán ¿Continuar?')) {
			document.expectativaForm.method.value = 'init';
			document.expectativaForm.submit();
		}**/
		messageRutaCancel('${perfilForm.errorMessageTab}','<%=response.encodeURL(context+"experiencia.do?method=init")%>');
	}
	function forward(data) {
		var res = dojo.fromJson(data);
		if (res.msg.type == 'ext') {

			dojo.byId('method').value = 'misofertas';
			dojo.byId('expectativaForm').submit();
			
			//location.replace('< %=context% >ofertasPerfiles.do?method=init&tipoConsulta=1');
		}
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

	function setOcupacion2(id, val) {
		//console.log("--> id:"+ id +" "+ val);
		dojo.byId('idOcupacionDeseada2').value = id;
		dojo.byId('ocupacionDeseada2Select').value = val;
		dojo.byId('ocupacionDeseada2Select').focus();

		cerrarAutocomplete2();
	}
	
	function cerrarAutocomplete2() {
		dojo.byId('ocupacionesListDiv2').style.display = 'none';
		dojo.byId('listaOcupaciones2').innerHTML = "";
	}
	
	
	
	function setOcupacion(id, val) {
		//console.log("--> id:"+ id +" "+ val);
		dojo.byId('idOcupacionDeseada').value = id;
		dojo.byId('ocupacionDeseada').value = val;
		dojo.byId('ocupacionDeseada').focus();

		cerrarAutocomplete();
	}

  	

</script>
<noscript>Funciones de JavaScript desactivadas por navegador</noscript>

<form name="expectativaForm" id="expectativaForm" method="post" action="expectativa.do" dojoType="dijit.form.Form">
	<input type="hidden" name="method" id="method" value="init" />
	<input type="hidden" name="inicioBusqueda" id="inicioBusqueda" value="" dojoType="dijit.form.TextBox" />
<div class="formApp miEspacio">
	<div class="situacion_laboral">

    <h2>Mi espacio</h2>  
	<div class="tab_block">
		<div align="left" id="returnME" style="display:block;">
            <a href="javascript:location.replace('<c:url value="/miEspacioCandidato.do"/>');" class="expand">
			<strong>Ir al inicio de Mi espacio</strong></a>
        </div>
	        <div class="Tab_espacio">
	            <h3>Editar mi perfil</h3>
	            <div class="subTab">
	                <ul>
	                    <li>
	                        <a href="javascript:messageRutaCancel('${perfilForm.errorMessageTab}','<%=response.encodeURL(context+"perfil.do?method=init")%>');">Datos personales</a>
	                    </li>
	                    <li>
	                        <a href="javascript:messageRutaCancel('${perfilForm.errorMessageTab}','<%=response.encodeURL(context+"escolaridad.do?method=init")%>');">Escolaridad y otros conocimientos</a>
	                    </li>
	                    <li>
	                    	<a href="javascript:messageRutaCancel('${perfilForm.errorMessageTab}','<%=response.encodeURL(context+"experiencia.do?method=init")%>');">Experiencia y expectativas laborales</a>
	                    </li>
	                    <li class="curSubTab">
	                    	<span>Situación laboral</span>
	                    </li>
	                </ul>
	                <div class="clearfix"></div>
	            </div>
	            <div class="clearfix"></div>
	        </div>
	        <!--  
	        <div class="sublevelTitle">
	            Ofertas de acuerdo a mi perfil
	        </div>-->
	        <p class="labeled">Los datos marcados con <span>*</span> son obligatorios</p>
	</div>
	<fieldset>
		<legend>Situación laboral</legend>
        <div class="grid1_3 margin_top_30">
        	<input type="hidden" name="idTrabajaAux" id="idTrabajaAux" value="${perfilForm.idTrabaja}" dojoType="dijit.form.TextBox" />
        		<div class="labeled"><span>*</span> ¿Trabajas actualmente?</div>
				<input type="radio" name="idTrabaja" id="idTrabaja1" value="${trabajaSI}" 
				       ${perfilForm.idTrabaja == trabajaSI ? 'checked="checked"' : '' }  
				       dojoType="dijit.form.RadioButton" onchange="requerirRazon()"
				       <c:if test = "${perfilForm.ppc == true && perfilForm.idTrabaja == trabajaNO}">
				        readonly = true
				       </c:if>
				        />
						<label class="fw_no_bold" for="idTrabaja1">Si</label>
						<input type="radio" name="idTrabaja" id="idTrabaja2" value="${trabajaNO}" 
				       ${perfilForm.idTrabaja == trabajaNO ? 'checked="checked"' : '' }  
				       dojoType="dijit.form.RadioButton" onchange="requerirRazon()"
  						<c:if test = "${perfilForm.ppc == true && perfilForm.idTrabaja == trabajaNO}">
				        readonly = true
				       </c:if>
				        />
						<label class="fw_no_bold" for="idTrabaja2">No</label>
		</div>
		<%--<div class="margin_top_30 grid1_3">--%>
		<div class="grid1_3 margin_top_20 fl">
			<div dojoType="dojo.data.ItemFileReadStore" jsId="buscaTrabajoStore" urlPreventCache="true" clearOnClose="true" 
	         url="${context}perfil.do?method=buscaTrabajo&idTrab=${perfilForm.idTrabaja}"></div>
			<input type="hidden" name="idRazonBusqueda" id="idRazonBusqueda" dojoType="dijit.form.TextBox" value="${perfilForm.idRazonBusqueda}" />
			<label  id="razonRequerida" for="idRazonBusquedaSelect"><span>*</span> Motivo por el que buscas trabajo</label>
            <select dojotype="dijit.form.FilteringSelect" store="buscaTrabajoStore" id="idRazonBusquedaSelect" 
               	required="true" missingMessage="Debe seleccionar el motivo por el que busca trabajo." autocomplete="true" value="${perfilForm.idRazonBusqueda}">
            </select>
        </div>
		<div id="motivoOtroContainer" class="grid1_3 margin_top_20 fl" style="${perfilForm.idRazonBusqueda ne 18 ? 'display: none' : ''}"> <%--idRazonBusqueda(18) -> Otro--%>
			<label for="descripcionOtroMotivoBusq"><span>*</span> Describe el otro motivo</label>
				<textarea
						style="height: 50px !important; max-height: 50px !important;"
						rows="6" cols="70" maxlength="2000" trim="true"
						onchange="return maximaLongitud(this,event,2000)"
						onKeyUp="return maximaLongitud(this,event,2000)"
						regExp="^^[A-Za-z\s\-.áéíóúÁÉÍÓÚñÑ']{3,2000}$"
						onpaste="return false;"
						name="descripcionOtroMotivoBusq" id="descripcionOtroMotivoBusq">
					${perfilForm.descripcionOtroMotivoBusq}
				</textarea>
		</div>
		<div class="clearfix"></div>
        <div class="margin_top_30">
        		<div class="labeled"><span>*</span> Fecha en que comenzaste a buscar trabajo</div>
            	<select name="inicioBusquedaDia" id="inicioBusquedaDia" data-dojo-type="dijit.form.FilteringSelect"
            			missingMessage="Indicar el dia desde que comenzo a buscar trabajo.">
           			<option value="0">Día</option>
           			<%=getSelectedDay(inicioBusqueda)%>
		        </select>
                <select name="inicioBusquedaMes" id="inicioBusquedaMes" data-dojo-type="dijit.form.FilteringSelect"
                		missingMessage="Indicar el mes desde que comenzo a buscar trabajo.">
                	<option value="0">Mes</option>
           			<%=getSelectedMonth(inicioBusqueda) %>
                </select>
                <select name="inicioBusquedaAnual" id="inicioBusquedaAnual" data-dojo-type="dijit.form.FilteringSelect"
                		missingMessage="Indicar el año desde que comenzo a buscar trabajo.">
                    <option value="0">Año</option>
           		    <%=getSelectedYear(inicioBusqueda) %>
                </select>
        </div>
        <div class="margin_top_30">
        		<div class="labeled"><span>*</span> ¿Qué otros medios has utilizado para buscar trabajo?</div>
            <br/>
        	<input type="hidden" name="idOtroMedio" id="idOtroMedio" dojoType="dijit.form.TextBox" />

			<fmt:formatNumber var="numbloque" value="${fn:length(perfilForm.otrosMedios) / 3}" maxFractionDigits="0"/>

        	<c:forEach var="otrosMedios" items="${perfilForm.otrosMedios}" varStatus="index">
				<c:set var="open" value="${index.count==1 || index.count==numbloque+1 || index.count==(numbloque*2)+1}"/>
				<c:set var="close" value="${index.count==numbloque || index.count==(numbloque*2) || index.count==fn:length(perfilForm.otrosMedios)}"/>

        		<c:set var="checkmedio" value=""/>
        		
        		<c:forEach var="otrosMediosVO" items="${perfilForm.otrosMediosVO}">					
        			<c:if test="${otrosMedios.idCatalogoOpcion == otrosMediosVO.idMedioBusqueda}">
        				<c:set var="checkmedio" value="checked"/>
        			</c:if>
        		</c:forEach>

				<c:if test="${open}">
				<div class="grid1_3 fl">
				<ul>
				</c:if>
				<li>
					<label>
	        		<input type="checkbox" data-dojo-type="dijit.form.CheckBox" id="idOtrosMedios${otrosMedios.idCatalogoOpcion}" name="idOtrosMedios" value="${otrosMedios.idCatalogoOpcion}" onclick="checkOtrosMedios(this.id)" ${checkmedio}/>
	        	 	${otrosMedios.opcion}</label>
				</li>

				<c:if test="${close}">
				</ul>
				</div>
				</c:if>

        	</c:forEach>
        </div>

		<div class="clearfix"></div>
		<div class="margin_top_30 grid1_3">

        	<input type="hidden" name="idMedioPortal" id="idMedioPortal" dojoType="dijit.form.TextBox" value="${perfilForm.idMedioPortal}" />
        	<label for="idMediosSelect"><span>*</span> ¿Cómo te enteraste del Portal del Empleo?</label>
			<select dojotype="dijit.form.FilteringSelect" id="idMediosSelect" value="${perfilForm.idMedioPortal}"
            		required="true" missingMessage="Debe seleccionar el medio por el cual se entero en Portal." readonly="readonly"
            		autocomplete="true">
				<c:forEach var="row" items="${medios}">
				  <c:if test="${perfilForm.idMedioPortal eq row.idCatalogoOpcion}">
				 	 <option value="${row.idCatalogoOpcion}">${row.opcion}</option>
				   </c:if>
				 </c:forEach>
			</select>				 
		</fieldset>
        <div class="form_nav">
        	<input type="button" id="btnGuardar" value="Guardar" onclick="doSubmitExpec();">
        	<input type="button" value="Cancelar" onclick="doCancel();">
        </div>      
	</div>
	<div id="status_actual">
    </div>
</div>
</form>
<%!
	String getSelectedDay(String sDate) {
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = (Date)formatter.parse(sDate);
		} catch (ParseException e) { date = null; }
		if (null == date) return "";
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		StringBuilder day = new StringBuilder();
		int dateDay = calendar.get(Calendar.DAY_OF_MONTH);
		for (int i = 1; i<32; i++) {
			String iday = "0" + i;
			if (iday.length() > 2) iday = iday.substring(1,3);
			day.append("<option value=\"" + iday + "\"");
			if (i==dateDay)
				day.append(" selected ");
			day.append(">" + iday + "</option>\n");
		}
		
		return day.toString();
	}
	
	String getSelectedMonth(String sDate) {
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = (Date)formatter.parse(sDate);
		} catch (ParseException e) { date = null; }
		if (null == date) return "";
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		StringBuilder day = new StringBuilder();
		int month = calendar.get(Calendar.MONTH) + 1;
		for (int i = 1; i<13; i++) {
			String iday = "0" + i;
			if (iday.length() > 2) iday = iday.substring(1,3);
			day.append("<option value=\"" + iday + "\"");
			if (i==month)
				day.append(" selected ");
			day.append(">" + getLblMonth(i) + "</option>\n");
		}
		return day.toString();
	}
	
	String getSelectedYear(String sDate) {
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = (Date)formatter.parse(sDate);
		} catch (ParseException e) { date = null; }
		if (null == date) return "";
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		StringBuilder day = new StringBuilder();
		
		int year = calendar.get(Calendar.YEAR);

		Calendar hoy = Calendar.getInstance();
		int yearActual = hoy.get(Calendar.YEAR);
		
		for (int i=yearActual; i>= (yearActual-10); i--){
			day.append("<option value=\"" + i + "\"");
			if (i==year)
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
