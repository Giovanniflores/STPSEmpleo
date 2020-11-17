<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page import="java.util.List, java.util.Iterator, java.util.ArrayList, mx.gob.stps.portal.persistencia.vo.BeneficiarioVO, mx.gob.stps.portal.web.infra.helper.BeneficiarioBO, mx.gob.stps.portal.core.infra.utils.Constantes"%>
<%
	List<BeneficiarioVO> beneficiaries = new ArrayList<BeneficiarioVO>();
	if (null != request.getSession().getAttribute("LST_BENEFICIARIES_PTAT"))
		beneficiaries = (List<BeneficiarioVO>)request.getSession().getAttribute("LST_BENEFICIARIES_PTAT");
%>
<script>
	function messageConfirmAdd(msg, idbeneficiario) {
		var html =
			'<div id="messageDialgoAdd" name="messageDialgoAdd">' +
			'<p style="text-align: center;">'+ msg +'</p>'+
			'<p class="form_nav">' + 
			'<input id="btnRenv" class="boton_naranja" type="button" name="btnRenv" onClick="doDelete('+idbeneficiario+')" value="Aceptar"/>' +
			'<input id="btnCanc" class="boton_naranja" type="button" name="btnCanc" onClick="dialogMsgConfirm.hide();" value="Cancelar"/>' +
    		'</p>' +
    		'</div>';
		dialogMsgConfirmAdd = new dijit.Dialog({
	        title: 'Mensaje',
	        content: html,
	        style: "width:300px;",
	        showTitle: false, draggable : false, closable : false,
	    });	
		dialogMsgConfirmAdd.show();
	}
	function toggleDelete(idbeneficiario) {
		messageConfirmAdd('¿Está seguro de eliminar el beneficiario?', idbeneficiario);	
	}
	function doDelete(idbeneficiario) {
		dialogMsgConfirmAdd.hide();
		var action = 'perfilComplementario.do?method=deleteBeneficiary&idbeneficiario='+idbeneficiario;
		dojo.xhrPost({url: action, form:'ProgramaForm', sync: true, preventCache:true, timeout:180000, 
			load: function(data) {
				clearWidgetsAndAddHtml('beneficiaries',data);
		}});
	}
	function checkedDep() {
		var dependiente = dijit.byId('dependiente');
		var finado = dijit.byId('finado');
		if (dependiente.getValue('checked')) {
		 	if (finado.getValue('checked')) finado.set("checked", false);
		}
	}
	function checkedBenf() {
		var beneficiario = dijit.byId('beneficiario');
		var finado = dijit.byId('finado');
		if (beneficiario.getValue('checked')) {
		 	if (finado.getValue('checked')) finado.set("checked", false);
		 	document.getElementById("spanEdadBeneficiario").style.display="none";
    		document.getElementById("spanEdadBeneficiarioReq").style.display="block";
    		dijit.byId('edadBeneficiario').attr('required', true);
    		document.getElementById("spanPorcentajeBeneficiario").style.display="none";
    		document.getElementById("spanPorcentajeBeneficiarioReq").style.display="block";
    		dijit.byId('porcentajeBeneficiario').attr('required', true);
		}else {
			document.getElementById("spanEdadBeneficiario").style.display="block";
    		document.getElementById("spanEdadBeneficiarioReq").style.display="none";
    		dijit.byId('edadBeneficiario').attr('required', false);
    		document.getElementById("spanPorcentajeBeneficiario").style.display="block";
    		document.getElementById("spanPorcentajeBeneficiarioReq").style.display="none";
    		dijit.byId('porcentajeBeneficiario').attr('required', false);
		}
	}
	function checkedFind() {
		var dependiente = dijit.byId('dependiente');
		var beneficiario = dijit.byId('beneficiario');
		var finado = dijit.byId('finado');
		if (finado.getValue('checked')) {
		 	if (dependiente.getValue('checked')) dependiente.set("checked", false);
		 	if (beneficiario.getValue('checked')) {
		 		beneficiario.set("checked", false);
		 		document.getElementById("spanEdadBeneficiario").style.display="block";
	    		document.getElementById("spanEdadBeneficiarioReq").style.display="none";
	    		dijit.byId('edadBeneficiario').attr('required', false);
	    		document.getElementById("spanPorcentajeBeneficiario").style.display="block";
	    		document.getElementById("spanPorcentajeBeneficiarioReq").style.display="none";
	    		dijit.byId('porcentajeBeneficiario').attr('required', false);
		 	}
		 	dijit.byId('porcentajeBeneficiario').attr('value', '');
		}
	}
	function toggleAddBenef() {
		if (validateBenef() && validateExtAdrBef()) {
			doSubmmitBenef();
		}
	}
	function validateBenef() {
		var nombre = dijit.byId('nombre_beneficiario');
		var apellido1 = dijit.byId('apellido_1_beneficiario');
		var apellido2 = dijit.byId('apellido_2_beneficiario');
		var parentesco = dijit.byId('idParentescoBeneficiarioSelect');
		var edad = dijit.byId('edadBeneficiario');
		var porcentaje = dijit.byId('porcentajeBeneficiario');
		var beneficiario = dijit.byId('beneficiario');
		if (nombre.get('value')=='') {
			nombre.focus();
			message('El nombre del beneficiario es requerido');
			return false;
		}
		if (!nombre.isValid()) {
			nombre.focus();
			message('El nombre del beneficiario sólo debe contener letras');
			return false;
		}
		if (apellido1.get('value')=='') {
			apellido1.focus();
			message('El primer apellido es requerido');
			return false;
		}
		if (!apellido1.isValid()) {
			apellido1.focus();
			message('El primer apellido sólo debe contener letras');
			return false;
		}
		if (!apellido2.isValid()) {
			apellido1.focus();
			message('El segundo apellido sólo debe contener letras');
			return false;
		}
		if (parentesco.get('value')==0) {
			parentesco.focus();
			message('Seleccione parentesco');
			return false;
		}
		if (parentesco.get('value')==3 && !edad.isValid()) {
			edad.focus();
			message('Edad para hijos es obligatorio');
			return false;
		}
		if (beneficiario.getValue('checked')) {
			if (edad.get('value')=='') {
				edad.focus();
				message('Edad es obligatorio');
			}
			if (porcentaje.get('value')=='') {
				porcentaje.focus();
				message('Porcentaje es obligatorio');
				return false;
			}
			if (!isValidPercent()) return false;
		}
		if (!isCheckedEstatus()) return false;
		return true;
	}
	function isValidPercent() {
		var isValid = true;
		var porcentaje = dijit.byId('porcentajeBeneficiario').get('value');
		var action = 'perfilComplementario.do?method=isValidPercent&porcentaje='+porcentaje;
		dojo.xhrPost({url: action, form:'ProgramaForm', sync: true, preventCache:true, timeout:180000, 
			load: function(data) {
				var res = dojo.fromJson(data);
				if (res.type == 'err') {
					message(res.message);
					isValid = false;
				}
		}});
		return isValid;
	}
	function doSubmmitBenef() {
		document.ProgramaForm.method.value = 'saveBeneficiary';
		document.ProgramaForm.idParentescoBeneficiario.value = dijit.byId('idParentescoBeneficiarioSelect').get('value');
		document.ProgramaForm.idEntidadBeneficiario.value = dijit.byId('idEntidadBeneficiarioSelect').get('value');
		document.ProgramaForm.idMunicipioBeneficiario.value = dijit.byId('idMunicipioBeneficiarioSelect').get('value');
		document.ProgramaForm.idColoniaBeneficiario.value = dijit.byId('idColoniaBeneficiarioSelect').get('value');
		document.ProgramaForm.idLocalidadBeneficiario.value = dijit.byId('idLocalidadBeneficiarioSelect').get('value');
		dojo.xhrPost({
			url: 'perfilComplementario.do',
			form:'ProgramaForm',
			sync: true,
			timeout:180000,
			load: function(data) {
				clearWidgetsAndAddHtml('beneficiaries',data);
			}
		});
	}
	function validateExtAdrBef() {
		var calle = dijit.byId('calleBeneficiario');
		var numExt = dijit.byId('numExtBeneficiario');
		var numInt = dijit.byId('numIntBeneficiario');
		var entreCalle = dijit.byId('entreCalleBeneficiario');
		var yCalle = dijit.byId('yCalleBeneficiario');
		var idEntidadSelect = dijit.byId('idEntidadBeneficiarioSelect');
		var idMunicipioSelect = dijit.byId('idMunicipioBeneficiarioSelect');
		var idColoniaSelect = dijit.byId('idColoniaBeneficiarioSelect');
		var idLocalidadSelect = dijit.byId('idLocalidadBeneficiarioSelect');
		if (calle.get('value')=='') {
			calle.focus();
			message('Calle es requerido.');
			return false;
		}
		if (!calle.isValid()) {
			calle.focus();
			message('Calle no debe contener carácteres especiales.');
			return false;
		}
		if (numExt.get('value')=='') {
			numExt.focus();
			message('Número exterior es requerido.');
			return false;
		}
		if (!numExt.isValid()) {
			numExt.focus();
			message('El valor especificado no es válido para número exterior del beneficiario.');
			return false;
		}
		if (!numInt.isValid()) {
			numInt.focus();
			message('El valor especificado no es válido para número interior del beneficiario.');
			return false;
		}
		if (!entreCalle.isValid()) {
			entreCalle.focus();
			message('Entre calle no debe contener carácteres especiales.');
			return false;
		}
		if (!yCalle.isValid()) {
			yCalle.focus();
			message('Y calle no debe contener carácteres especiales.');
			return false;
		}
		if (idEntidadSelect.get('value')==0) {
			idEntidadSelect.focus();
			message('Seleccione entidad.');
			return false;
		}
		if (idMunicipioSelect.get('value')==0) {
			idMunicipioSelect.focus();
			message('Seleccione municipio o delegación.');
			return false;
		}
		if (idColoniaSelect.get('value')==0) {
			idColoniaSelect.focus();
			message('Seleccione colonia.');
			return false;
		}
		if (idLocalidadSelect.get('value')==0) {
			idLocalidadSelect.focus();
			message('Seleccione localidad.');
			return false;
		}
		return true;
	}
	function actulizaMunicipioBeneficiario() {
		var vEntidad = dijit.byId('idEntidadBeneficiarioSelect').get('value');
		if (vEntidad) {
			municipioStore.url = '';
			municipioStore.close();			

			coloniaStore.url = '';
			coloniaStore.close();			
		
			dojo.byId('codigoPostalBeneficiario').value = '';
			dijit.byId('idMunicipioBeneficiarioSelect').reset();
			dijit.byId('idColoniaBeneficiarioSelect').reset();
			municipioStore.url = "${context}domicilio.do?method=obtenerMunicipio" + "&" + "idEntidad="+ vEntidad;
			municipioStore.close();			
		}
	}
	function actulizaColoniaBeneficiario() {
		var vEntidad   = dijit.byId('idEntidadBeneficiarioSelect').get('value');
		var vMunicipio = dijit.byId('idMunicipioBeneficiarioSelect').get('value');
		if (vEntidad && vMunicipio){
			dojo.byId('codigoPostalBeneficiario').value = '';
			dijit.byId('idColoniaBeneficiarioSelect').reset();
			dijit.byId('idLocalidadBeneficiarioSelect').reset();
			coloniaStore.url = "${context}domicilio.do?method=obtenerColonia" + "&" + "idEntidad="+ vEntidad + "&" + "idMunicipio="+ vMunicipio;
			coloniaStore.close();
			localidadStore.url = "${context}domicilio.do?method=obtenerLocalidades" + "&" + "idEntidad="+ vEntidad + "&" + "idMunicipio="+ vMunicipio;
			localidadStore.close();
		}
	}
	function actulizaCodigoPostalBeneficiario() {
		var vEntidad   = dijit.byId('idEntidadBeneficiarioSelect').get('value');
		var vMunicipio = dijit.byId('idMunicipioBeneficiarioSelect').get('value');
		var vColonia = dijit.byId('idColoniaBeneficiarioSelect').get('value');;
		if (vEntidad && vMunicipio && vColonia){
	    	var url = '${context}domicilio.do?method=obtenerCodigoPostal&idEntidad='+ vEntidad + '&idMunicipio='+ vMunicipio + '&idColonia='+ vColonia;
			dojo.xhrGet({url: url, sync: true, timeout:180000,
		       load: function(data){
					       var res = dojo.fromJson(data);
						   dojo.byId('codigoPostalBeneficiario').value = res.codigoPostal;
					    }
				});
		} else {
			dojo.byId('codigoPostalBeneficiario').value='';
		}
	}
	function isCheckedEstatus() {
		var dependiente = dijit.byId('dependiente');
		var beneficiario = dijit.byId('beneficiario');
		var finado = dijit.byId('finado');
		if (!finado.getValue('checked') && !dependiente.getValue('checked') && !beneficiario.getValue('checked')) {
			dependiente.focus();
			message('Seleccione estado del familiar.');
		 	return false
		}
		return true;
	}
</script>
<noscript>Funciones de javascript desactivadas por el navegador</noscript>
<fieldset>
	<legend>Datos de familiares directos</legend>
	<div id="addBeneficiaries" style="display:${addisplay}">
		<div class="grid1_3 margin_top_10 fl">
			<label for="nombre_beneficiario"><span>*</span> Nombre(s)</label>
			<input type="text" dojoType="dijit.form.ValidationTextBox" name="nombre_beneficiario" id="nombre_beneficiario" regExp="^[A-Za-z\s\-.áéíóúÁÉÍÓÚñÑ']{1,150}$"
				invalidMessage="El nombre sólo debe contener letras" missingMessage="El nombre es requerido" required="true" trim="true" uppercase="true" maxlength="50" required="true" missingMessage="Dato requerido">
		</div>
		<div class="grid1_3 margin_top_10 fl">
			<label for="apellido_1_beneficiario"><span>*</span> Primer Apellido</label>
			<input type="text" dojoType="dijit.form.ValidationTextBox" name="apellido_1_beneficiario" id="apellido_1_beneficiario" regExp="^[A-Za-z\s\-.áéíóúÁÉÍÓÚñÑ']{1,150}$"
				invalidMessage="El primer apellido sólo debe contener letras" trim="true" uppercase="true" maxlength="50" required="true" missingMessage="El primer apellido es requerido">
		</div>
		<div class="grid1_3 margin_top_10 fl">
			<label for="apellido_2_beneficiario">Segundo Apellido</label>
			<input type="text" dojoType="dijit.form.ValidationTextBox" name="apellido_2_beneficiario" id="apellido_2_beneficiario" regExp="^[A-Za-z\s\-.áéíóúÁÉÍÓÚñÑ']{1,150}$"
				invalidMessage="El segundo apellido sólo debe contener letras" trim="true" uppercase="true" maxlength="50" required="false" missingMessage="El segundo apellido es requerido">
		</div>
		<div class="grid1_3 margin_top_10 fl">
			<label for="idParentescoBeneficiarioSelect"><span>*</span> Parentesco</label>
			<input type="hidden" name="idParentescoBeneficiario" id="idParentescoBeneficiario" dojoType="dijit.form.TextBox">
			<select dojotype="dijit.form.FilteringSelect" id="idParentescoBeneficiarioSelect" autocomplete="true"
				required="true" missingMessage="Seleccione Parentesco" class="${classCivil}">
				<c:forEach var="row" items="${parentesco}">
					<option value="${row.idCatalogoOpcion}">${row.opcion}</option>
				</c:forEach>
			</select>
		</div>
		<div class="grid1_3 margin_top_10 fl">
			<div id="spanEdadBeneficiarioReq" style="display:none"><label for="edadBeneficiario"><span >*</span> Edad</label></div>
			<div id="spanEdadBeneficiario" style="display:block"><label for="edadBeneficiario">Edad</label></div>
			<input type="text" dojoType="dijit.form.ValidationTextBox" name="edadBeneficiario" id="edadBeneficiario" regExp="^[\d]{1,3}$"
				invalidMessage="Ingrese un número entre 1 y 100." maxlength="2" required="false" missingMessage="Edad es requerido">
		</div>
		<div class="grid1_3 margin_top_10 fl">
			<div id="spanPorcentajeBeneficiarioReq" style="display:none"><label for="porcentajeBeneficiario"><span >*</span> Porcentaje</label></div>
			<div id="spanPorcentajeBeneficiario" style="display:block"><label for="porcentajeBeneficiario">Porcentaje</label></div>
			<input type="text" dojoType="dijit.form.ValidationTextBox" name="porcentajeBeneficiario" id="porcentajeBeneficiario" required="false"
				value="" invalidMessage="Ingrese un número entre 1 y 100." regExp="^[\d]{1,3}$" maxlength="3" missingMessage="Porcentaje es requerido">
		</div>
		<div class="clearfix"></div>
		<div class="grid1_3 margin_top_10 fl">
			<input type="checkbox" name="estado" id="dependiente" value="1" onclick="checkedDep()" dojoType="dijit.form.CheckBox"> Dependiente
		</div>
		<div class="grid1_3 margin_top_10 fl">
			<input type="checkbox" name="estado" id="beneficiario" value="2" onclick="checkedBenf()" dojoType="dijit.form.CheckBox"> Beneficiario
		</div>
		<div class="grid1_3 margin_top_10 fl">
			<input type="checkbox" name="estado" id="finado" value="3" onclick="checkedFind()" dojoType="dijit.form.CheckBox"> Finado
		</div>
		<div class="clearfix"></div><br>
		<div class="labeled margin_top_10">Datos de domicilio del familiar</div>
		<div class="grid1_3 margin_top_10 fl">
			<label for="calleBeneficiario"><span>*</span> Calle</label>
			<input type="text" name="calleBeneficiario" id="calleBeneficiario" dojoType="dijit.form.ValidationTextBox" maxlength="150"
				value="${ProgramaForm.beneficiario.domicilio.calle}" trim="true" regExp="^[\w\d\s\.áéíóúÁÉÍÓÚñÑ]{1,150}$"
				invalidMessage="El valor especificado no es válido para calle del beneficiario." trim="true" uppercase="true" required="true"
				missingMessage="Es necesario indicar calle del beneficiario." />
		</div>
		<div class="grid1_3 margin_top_10 fl">
			<label for="numExtBeneficiario"><span>*</span> Número exterior</label>
			<input type="text" name="numExtBeneficiario" id="numExtBeneficiario" dojoType="dijit.form.ValidationTextBox" maxlength="50"
				value="${ProgramaForm.beneficiario.domicilio.numeroExterior}" required="true" trim="true" regExp="^[\w\d\s]{1,50}$"
				invalidMessage="El valor especificado no es válido para número exterior del beneficiario."
				missingMessage="Es necesario indicar número exterior del beneficiario." />
		</div>
		<div class="grid1_3 margin_top_10 fl">
			<label for="numIntBeneficiario">Número interior</label>
			<input type="text" name="numIntBeneficiario" id="numIntBeneficiario" dojoType="dijit.form.ValidationTextBox" maxlength="50"
				value="${ProgramaForm.beneficiario.domicilio.numeroInterior}" required="false" trim="true" uppercase="true" regExp="^[\w\d\s]{1,50}"
				invalidMessage="El valor especificado no es válido para número interior del beneficiario."
				missingMessage="Es neccesario indicar número interior del beneficiario." />
		</div>
		<div class="grid1_3 margin_top_10 fl">
			<label for="entreCalleBeneficiario">Entre calle</label>
			<input type="text" name="entreCalleBeneficiario" id="entreCalleBeneficiario" dojoType="dijit.form.ValidationTextBox" maxlength="50"
				value="${ProgramaForm.beneficiario.domicilio.entreCalle}" required="false" trim="true" uppercase="true"
				invalidMessage="El valor especificado no es válido para entre calle del beneficiario." regExp="^[\w\d\s\.áéíóúÁÉÍÓÚñÑ]{1,150}$"
				missingMessage="Es neccesario indicar entre calle del beneficiario." />
		</div>
		<div class="grid1_3 margin_top_10 fl">
			<label for="yCalleBeneficiario">Y calle</label>
			<input type="text" name="yCalleBeneficiario" id="yCalleBeneficiario" dojoType="dijit.form.ValidationTextBox" maxlength="50"
				value="${ProgramaForm.beneficiario.domicilio.yCalle}" required="false" trim="true" uppercase="true"
				invalidMessage="El valor especificado no es válido para y calle del beneficiario." regExp="^[\w\d\s\.áéíóúÁÉÍÓÚñÑ]{1,150}$"
				missingMessage="Es neccesario indicar y calle del beneficiario." />
		</div>
		<div class="grid1_3 margin_top_10 fl">
			<label for="idEntidadBeneficiarioSelect"><span>*</span> Entidad Federativa</label>
			<input type="hidden" name="idEntidadBeneficiario" id="idEntidadBeneficiario" dojoType="dijit.form.TextBox">
			<select dojotype="dijit.form.FilteringSelect" store="entidadFederativaStore" id="idEntidadBeneficiarioSelect" required="true"
				invalidMessage="Dato no válido" missingMessage="Es necesario indicar la entidad." autocomplete="true"
				onchange="javascript:actulizaMunicipioBeneficiario();" scrollOnFocus="true" maxHeight="250">
			</select>
		</div>
		<div class="grid1_3 margin_top_10 fl">
			<label for="idMunicipioBeneficiarioSelect"><span>*</span> Municipio o Delegación</label>
			<input type="hidden" name="idMunicipioBeneficiario" id="idMunicipioBeneficiario" dojoType="dijit.form.TextBox">
				<select dojotype="dijit.form.FilteringSelect" store="municipioStore" id="idMunicipioBeneficiarioSelect" required="true"
					invalidMessage="Dato no válido" missingMessage="Es necesario indicar un municipio."
					autocomplete="true" onchange="javascript:actulizaColoniaBeneficiario();" scrollOnFocus="true" maxHeight="250">
				</select>
		</div>
		<div class="grid1_3 margin_top_10 fl">
			<label for="idColoniaBeneficiarioSelect"><span>*</span> Colonia</label>
			<input type="hidden" name="idColoniaBeneficiario" id="idColoniaBeneficiario" dojoType="dijit.form.TextBox">
				<select dojotype="dijit.form.FilteringSelect" store="coloniaStore" id="idColoniaBeneficiarioSelect" required="true"
					invalidMessage="Dato inválido" missingMessage="Es necesario indicar la colonia." autocomplete="true"
					onchange="javascript:actulizaCodigoPostalBeneficiario();" scrollOnFocus="true" maxHeight="250">
				</select>
		</div>
		<div class="grid1_3 margin_top_10 fl">
			<label for="idLocalidadBeneficiarioSelect"><span>*</span> Localidad</label>
				<input type="hidden" name="idLocalidadBeneficiario" id="idLocalidadBeneficiario" dojoType="dijit.form.TextBox">
				<select dojotype="dijit.form.FilteringSelect" value="" id="idLocalidadBeneficiarioSelect" store="localidadStore"
					required="true" missingMessage="Debe seleccionar la localidad." autocomplete="true" scrollOnFocus="true" maxHeight="250">
				</select>
		</div>
		<div class="grid1_3 margin_top_10 fl">
			<label for="codigoPostalBeneficiario">Código Postal</label>
			<input type="text" name="codigoPostalBeneficiario" id="codigoPostalBeneficiario" maxlength="5" style="width: 7em;"
				value="" dojoType="dijit.form.ValidationTextBox" regExp="^[0-9]{5}"
				invalidMessage="Codigo incorrecto, solo se admiten caracteres numericos." readonly="readonly"
	 			missingMessage="Es necesario indicar el codigo postal." trim="true" required="true"/>
		</div>
		<div class="grid1_3 margin_top_10 fl">
			<input type="button" class="agregar" onclick="javascript:toggleAddBenef();" title="Agregar Beneficiario" value="Agregar Beneficiario" />
		</div>
	</div>
</fieldset>
<table id="beneficiarios">
	<tbody>
<% if (!beneficiaries.isEmpty())  { %>
		<tr>
        	<th>Beneficiario</th>
            <th>Parentesco</th>
            <th>Edad</th>
            <th>Porcentaje</th>
            <th class="benef_dom">Dirección</th>
            <th>Estado</th>
            <th>Selecionar</th>
        </tr>
<%
	}
	int index = 0;
	String idBeneficiarioAdd = "idBeneficiarioAdd";
	Iterator<BeneficiarioVO> itbeneficiaries = beneficiaries.iterator();
	while (itbeneficiaries.hasNext()) {
		String count = "" + index;
		BeneficiarioBO bo = new BeneficiarioBO(itbeneficiaries.next());
%>
	<tr <% if (index%2 ==0) out.println("class=''"); else out.println("class='odd'"); %>>
    	<td><%=bo.getNombreCompleto() %></td>
        <td><%=bo.getParentesco() %></td>
        <td><% if (null != bo.getBeneficiario().getEdad()) out.print(bo.getBeneficiario().getEdad()); %></td>
        <td><% if (-1 != bo.getBeneficiario().getPorcentaje()) out.print(bo.getBeneficiario().getPorcentaje()); %></td>
        <td><%=bo.getBeneficiario().getDomicilio().getDomicilioCompleto() %></td>
        <td><%=bo.getEstado() %></td>
        <td><input type="button" class="agregar" onclick="javascript:toggleDelete(<%=count %>);" title="Eliminar Beneficiario" value="Eliminar" /></td>
    </tr>
<% 
	index++;
	}
%>
	</tbody>
</table>
<div class="clearfix"></div>
<div class="grid1_3 margin_top_10 fl">
	<label for="totalHijos"><span>*</span> Total de hijos</label>
	<input type="text" name="totalHijos" id="totalHijos" dojoType="dijit.form.ValidationTextBox" regExp="^[+]?\d{1,2}$" value="${ProgramaForm.ptat.numeroHijos}" trim="true"
		required="true" maxlength="2" invalidMessage="El valor especificado no es válido para total de hijos." missingMessage="Es necesario indicar total de hijos." />
</div>
<div class="grid1_3 margin_top_10 fl">
	<label for="hijosMenores"><span>*</span> Hijos menores de 18 años</label>
	<input type="text" name="hijosMenores" id="hijosMenores" dojoType="dijit.form.ValidationTextBox" regExp="^[+]?\d{1,2}$" value="${ProgramaForm.ptat.hijosMenores18}" trim="true"
		required="true" maxlength="2" invalidMessage="El valor especificado no es válido para hijos menores." missingMessage="Es necesario indicar hijos menores." />
</div>
<div class="grid1_3 margin_top_10 fl">
	<label for="dependientes">Número de dependientes</label>
	<input type="text" name="dependientes" id="dependientes" dojoType="dijit.form.ValidationTextBox" regExp="^[+]?\d{1,2}$" value="${ProgramaForm.ptat.numeroDependientes}" trim="true"
		disabled="disabled" required="false" maxlength="2" invalidMessage="El valor especificado no es válido para hijos menores." missingMessage="Es necesario indicar hijos menores." />
</div>