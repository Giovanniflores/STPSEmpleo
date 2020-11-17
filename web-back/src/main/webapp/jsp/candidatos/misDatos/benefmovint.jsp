<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page import="java.util.List, java.util.Iterator, java.util.ArrayList, mx.gob.stps.portal.persistencia.vo.BeneficiarioVO, mx.gob.stps.portal.web.infra.helper.BeneficiarioBO, mx.gob.stps.portal.core.infra.utils.Constantes"%>
<%
	int rowCounter = 0;
	List<BeneficiarioBO> beneficiaries = new ArrayList<BeneficiarioBO>();
	if (null != request.getSession().getAttribute("LST_BENEFICIARIES"))
		beneficiaries = (List<BeneficiarioBO>)request.getSession().getAttribute("LST_BENEFICIARIES");
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
	function toggleAddBenef() {
		if (validateBenef() && validateExtAdrBef()) {
			addBenef();
		}
	}
	function validateBenef() {
		var nombre = dijit.byId('nombre_beneficiario');
		var apellido1 = dijit.byId('apellido_1_beneficiario');
		var apellido2 = dijit.byId('apellido_2_beneficiario');
		var parentesco = dijit.byId('idParentescoSelect');
		var edad = dijit.byId('edad');
		var porcentaje = dijit.byId('porcentaje');
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
		if (edad.get('value')=='') {
			edad.focus();
			message('Edad es requerido');
			return false;
		}
		if (!edad.isValid()) {
			edad.focus();
			message('Edad sólo debe contener números');
			return false;
		}
		if (porcentaje.get('value')=='') {
			porcentaje.focus();
			message('Porcentaje es obligatorio');
			return false;
		}
		if (!isValidPercent()) return false;
		return true;
	}
	function isValidPercent() {
		var isValid = true;
		var porcentaje = dijit.byId('porcentaje').get('value');
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
	function addBenef() {
		var nombre = dijit.byId('nombre_beneficiario').get('value');
		var apellido1 = dijit.byId('apellido_1_beneficiario').get('value');
		var apellido2 = dijit.byId('apellido_2_beneficiario').get('value');
		var parentesco = dijit.byId('idParentescoSelect').get('value');
		var edad =  dijit.byId('edad').get('value');
		var porcentaje = dijit.byId('porcentaje').get('value');
		var calle = dijit.byId('calleBeneficiario').get('value');
		var numext = dijit.byId('numExtBeneficiario').get('value');
		var numint = dijit.byId('numIntBeneficiario').get('value');
		var ecalle = dijit.byId('entreCalleBeneficiario').get('value');
		var ycalle = dijit.byId('yCalleBeneficiario').get('value');
		var identidad = dijit.byId('idEntidadBeneficiarioSelect').get('value');
		var idmunicipio = dijit.byId('idMunicipioBeneficiarioSelect').get('value');
		var idcolonia = dijit.byId('idColoniaBeneficiarioSelect').get('value');
		var idlocalidad = dijit.byId('idLocalidadBeneficiarioSelect').get('value');
		var cp = dijit.byId('codigoPostalBeneficiario').get('value');
		var action = 'perfilComplementario.do?method=createBeneficiary&nombre='+nombre+'&apellido1='+apellido1+'&apellido2='+apellido2+'&parentesco='+parentesco
			+'&edad='+edad+'&calle='+calle+'&numext='+numext+'&numint='+numint+'&ecalle='+ecalle+'&ycalle='+ycalle+'&identidad='+identidad+'&idmunicipio='+idmunicipio
			+'&idcolonia='+idcolonia+'&idlocalidad='+idlocalidad+'&cp='+cp;
		dojo.xhrPost({url: action, form:'ProgramaForm', sync: true, preventCache:true, timeout:180000, 
			load: function(data) {
				clearWidgetsAndAddHtml('beneficiaries',data);
		}});
	}
	function toggleDelete(idbeneficiario) {
		messageConfirmAdd('¿Está seguro de eliminar el beneficiario?', idbeneficiario);	
	}
	function doDelete(idbeneficiario) {
		dialogMsgConfirmAdd.hide();
		var action = 'perfilComplementario.do?method=removeBeneficiary&idbeneficiario='+idbeneficiario;
		dojo.xhrPost({url: action, form:'ProgramaForm', sync: true, preventCache:true, timeout:180000, 
			load: function(data) {
				clearWidgetsAndAddHtml('beneficiaries',data);
		}});
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
	function toggleEdit(idbeneficiario) {
		var action = 'perfilComplementario.do?method=loadBeneficiary&idBeneficiario='+idbeneficiario;
		dojo.xhrPost({url: action, form:'ProgramaForm', sync: true, preventCache:true, timeout:180000, 
			load: function(data) {
				clearWidgetsAndAddHtml('beneficiaries',data);
		}});
	}
</script>
<noscript>Funciones de javascript desactivadas por el navegador</noscript>
<fieldset>
	<legend>Beneficiarios</legend>
	<p>En caso de ser aceptado para participar en algún curso del Subprograma ${ProgramaForm.modalidad.nombre} en las modalidades de Capacitación en la Práctica Laboral, Capacitación para el Autoempleo y Capacitación para técnicos y Profesionistas, tendrás derecho a un seguro contra accidentes. Por lo que se te solicita designes a tus beneficiarios (se sugiere que sea solo uno y que sea mayor de edad)</p>
	<div id="addBeneficiaries" style="display:${addisplay}">
		<div class="grid1_3 margin_top_10 fl">
			<label for="nombre_beneficiario"><span>*</span> Nombre</label>
			<input type="text" dojoType="dijit.form.ValidationTextBox" name="nombre_beneficiario" id="nombre_beneficiario" trim ="true" uppercase="true" maxlength="50"
				value="${ProgramaForm.beneficiario.nombre}" regExp="^[A-Za-z\s\-.áéíóúÁÉÍÓÚñÑ']{1,50}$" invalidMessage="El nombre sólo debe contener letras" missingMessage="Dato requerido" required="true">
		</div>
		<div class="grid1_3 margin_top_10 fl">
			<label for="apellido_1_beneficiario"><span>*</span> Primer Apellido</label>
			<input type="text" dojoType="dijit.form.ValidationTextBox" name="apellido_1_beneficiario" id="apellido_1_beneficiario" maxlength="50" trim ="true" uppercase="true"
				value="${ProgramaForm.beneficiario.primerApellido}" regExp="^[A-Za-z\s\-.áéíóúÁÉÍÓÚñÑ']{1,50}$" invalidMessage="El nombre sólo debe contener letras" required="true" missingMessage="Dato requerido">
		</div>
		<div class="grid1_3 margin_top_10 fl">
			<label for="apellido_2_beneficiario">Segundo Apellido</label>
			<input type="text" dojoType="dijit.form.ValidationTextBox" name="apellido_2_beneficiario" id="apellido_2_beneficiario" maxlength="50" trim ="true" uppercase="true"
				value="${ProgramaForm.beneficiario.segundoApellido}" regExp="^[A-Za-z\s\-.áéíóúÁÉÍÓÚñÑ']{1,50}$" invalidMessage="El nombre sólo debe contener letras" required="false" missingMessage="Dato requerido">
		</div>
		<div class="grid1_3 margin_top_10 fl">
			<label for="idParentescoSelect"><span>*</span> Parentesco</label>
			<input type="hidden" name="idParentesco" id="idParentesco" dojoType="dijit.form.TextBox">
			<select dojotype="dijit.form.FilteringSelect" id="idParentescoSelect" autocomplete="true"
				value="${ProgramaForm.beneficiario.idParentesco}" required="true" missingMessage="Seleccione Parentesco">
				<c:forEach var="row" items="${parentesco}">
					<option value="${row.idCatalogoOpcion}">${row.opcion}</option>
				</c:forEach>
			</select>
		</div>
		<div class="grid1_3 margin_top_10 fl">
			<label for="edad"><span>*</span> Edad</label>
			<input type="text" dojoType="dijit.form.ValidationTextBox" name="edad" id="edad" value="${ProgramaForm.beneficiario.edad}" trim="true"
				invalidMessage="Ingrese un número entre 1 y 99." regExp="^[\d]{1,3}$" maxlength="2" required="true" missingMessage="Dato requerido">
		</div>
		<div class="grid1_3 margin_top_10 fl">
			<label for="porcentaje"><span>*</span> Porcentaje</label>
			<input type="text" dojoType="dijit.form.ValidationTextBox" name="porcentaje" id="porcentaje" required="true" trim="true"
				value="${ProgramaForm.beneficiario.porcentaje}" invalidMessage="Ingrese un número entre 1 y 100." regExp="^[\d]{1,3}$"
				maxlength="3" missingMessage="Dato requerido">
		</div>
		<div class="clearfix"></div><br>
		<div class="labeled margin_top_10">Datos de domicilio del beneficiario</div>
		<div class="grid1_3 margin_top_10 fl">
			<label for="calleBeneficiario"><span>*</span> Calle</label>
			<input type="text" name="calleBeneficiario" id="calleBeneficiario" dojoType="dijit.form.ValidationTextBox" maxlength="50"
				value="${ProgramaForm.beneficiario.domicilio.calle}" regExp="^[\w\d\s\.áéíóúÁÉÍÓÚñÑ]{1,50}$"
				invalidMessage="El valor especificado no es válido para calle del beneficiario." trim ="true" uppercase="true" required="true"
				missingMessage="Es neccesario indicar calle del beneficiario." />
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
				value="${ProgramaForm.beneficiario.domicilio.numeroInterior}" required="false" trim="true" regExp="^[\w\d\s]{1,50}"
				invalidMessage="El valor especificado no es válido para número interior del beneficiario."
				missingMessage="Es neccesario indicar número interior del beneficiario." />
		</div>
		<div class="grid1_3 margin_top_10 fl">
			<label for="entreCalleBeneficiario">Entre calle</label>
			<input type="text" name="entreCalleBeneficiario" id="entreCalleBeneficiario" dojoType="dijit.form.ValidationTextBox" maxlength="50"
				value="${ProgramaForm.beneficiario.domicilio.entreCalle}" required="false" trim="true" uppercase="true"
				invalidMessage="El valor especificado no es válido para entre calle del beneficiario." regExp="^[\w\d\s\.áéíóúÁÉÍÓÚñÑ]{1,50}$"
				missingMessage="Es neccesario indicar entre calle del beneficiario." />
		</div>
		<div class="grid1_3 margin_top_10 fl">
			<label for="yCalleBeneficiario">Y calle</label>
			<input type="text" name="yCalleBeneficiario" id="yCalleBeneficiario" dojoType="dijit.form.ValidationTextBox" maxlength="50"
				value="${ProgramaForm.beneficiario.domicilio.yCalle}" required="false" trim="true" uppercase="true"
				invalidMessage="El valor especificado no es válido para y calle del beneficiario." regExp="^[\w\d\s\.áéíóúÁÉÍÓÚñÑ]{1,50}$"
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
		<div class="grid1_3 margin_top_10 fl"></div>
		<div class="grid1_3 margin_top_40 fl">
			<p align="right"><input type="button" class="agregar" onclick="javascript:toggleAddBenef();" title="Agregar Beneficiario" value="Agregar Beneficiario" /></p>
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
            <th>Selecionar</th>
        </tr>
<%
	}
	int index = 1;
	String idBeneficiarioAdd = "idBeneficiarioAdd";
	Iterator<BeneficiarioBO> itbeneficiaries = beneficiaries.iterator();
	while (itbeneficiaries.hasNext()) {
		BeneficiarioBO bo = itbeneficiaries.next();
		bo.setIndex(index);
%>
	<input type="hidden" name="<% out.print(idBeneficiarioAdd + index); %>" id="<% out.print(idBeneficiarioAdd + index); %>" value="<% out.print(index); %>" dojoType="dijit.form.TextBox"/>
	<tr <% if (index%2 ==0) out.println("class=''"); else out.println("class='odd'"); %>>
    	<td><%=bo.getNombreCompleto() %></td>
        <td><%=bo.getParentesco() %></td>
        <td><%=bo.getEdad() %></td>
        <td><%=bo.getPorcentaje() %></td>
        <td><% if (null != bo.getDomicilio()) out.println(bo.getDomicilio().getDomicilioCompleto()); %></td>
        <td><input type="button" class="agregar" onclick="javascript:toggleDelete(<% out.print(index); %>);" title="Eliminar Beneficiario" value="Eliminar" /></td>
    </tr>
<% 
	index++;
	}
%>
	</tbody>
</table>