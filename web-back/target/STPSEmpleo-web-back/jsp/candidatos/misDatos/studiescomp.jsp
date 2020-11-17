<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page import="java.util.List, java.util.Date, java.util.Calendar, java.util.Iterator, java.util.ArrayList, mx.gob.stps.portal.core.candidate.vo.CandidatoOtroEstudioVO"%>
<%
    Calendar cal = Calendar.getInstance();
    cal.setTime(new Date());
    int year = cal.get(Calendar.YEAR);
    int month = cal.get(Calendar.MONTH);
    int day = cal.get(Calendar.DAY_OF_MONTH);
 %>
<script>
	dojo.require("dijit.dijit");
	dojo.require("dijit.Calendar");
	dojo.require("dijit.form.DateTextBox");
	dojo.require("dijit.form.Select");
	
	function addStudie() {
		if (isValidStudie()) {
			var studieTypeAdd		= dijit.byId('studietypeadd').get('value');
			var studieAdd			= encodeURIComponent(document.getElementById('studieadd').value);
			var institutionAdd		= encodeURIComponent(document.getElementById('institutionadd').value);
			var dayiniAdd			= dijit.byId('dayiniadd').value;
			var monthiniAdd			= dijit.byId('monthiniadd').value;
			var yeariniAdd			= dijit.byId('yeariniadd').value;
			var dayfinAdd			= dijit.byId('dayfinadd').value;
			var monthfinAdd			= dijit.byId('monthfinadd').value;
			var yearfinAdd			= dijit.byId('yearfinadd').value;
			var dateini 			= dayiniAdd + '/' + monthiniAdd + '/' + yeariniAdd;
			var datefin 			= dayfinAdd + '/' + monthfinAdd + '/' + yearfinAdd;
			var statusacademicAdd	= dijit.byId('statusacademic').get('value');
			dojo.xhrGet({url: 'perfilComplementario.do?method=saveAddStudie&studieTypeAdd='+studieTypeAdd+'&studieAdd='+studieAdd+'&institutionAdd='+institutionAdd+'&dateini='+dateini+'&datefin='+datefin+'&statusacademicAdd='+statusacademicAdd, form:'ProgramaForm', sync: true, timeout:180000, 
				load: function(data) {
					clearWidgetsAndAddHtml('otros_estudios bloque',data);
			}});
		}
	}
	
	function toggle() {
		document.getElementById('studiesadd').style.display = 'block';
		document.getElementById('btnAgregar').style.display = 'none';
    }
    function doDeleteStudie(idstudie) {
    	messageConfirmEst('¿Esta usted seguro de eliminar estudio?', idstudie);
	}
	function messageConfirmEst(msg, idstudie) {
		var html =
			'<div id="messageDialgoAdd" name="messageDialgoAdd">' +
			'<p style="text-align: center;">'+ msg +'</p>'+
			'<p class="form_nav">' + 
			'<input id="btnRenv" class="boton_naranja" type="button" name="btnRenv" onClick="deleteStudie('+idstudie+')" value="Aceptar"/>' +
			'<input id="btnCanc" class="boton_naranja" type="button" name="btnCanc" onClick="dialogMsgConfirm.hide();" value="Cancelar"/>' +
    		'</p>' +
    		'</div>';
		dialogMsgConfirmEst = new dijit.Dialog({
	        title: 'Mensaje',
	        content: html,
	        style: "width:300px;",
	        showTitle: false, draggable : false, closable : false,
	    });	
		dialogMsgConfirmEst.show();
	}
	function deleteStudie(idstudie) {
		dialogMsgConfirmEst.hide();
		dojo.xhrGet({url: 'perfilComplementario.do?method=deleteStudie&idStudie='+idstudie, form:'ProgramaForm', sync: true, timeout:180000, 
			load: function(data) {
				clearWidgetsAndAddHtml('otros_estudios bloque',data);
		}});
	}
	function isValidStudie() {
		var studietypeadd = dijit.byId('studietypeadd');
		var studieadd = dojo.byId('studieadd');
		var institutionadd = dojo.byId('institutionadd');
		var dayiniadd = dijit.byId('dayiniadd');
		var monthiniadd = dijit.byId('monthiniadd');
		var yeariniadd = dijit.byId('yeariniadd');
		var dayfinadd = dijit.byId('dayfinadd');
		var monthfinadd = dijit.byId('monthfinadd');
		var yearfinadd = dijit.byId('yearfinadd');
		var statusacademic = dijit.byId('statusacademic');
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
    	if (!validateDates(dayiniadd, monthiniadd, yeariniadd, dayfinadd, monthfinadd, yearfinadd)) {
    		return false;
    	}
    	if (statusacademic.value == 0) {
    		message('Debe seleccionar situación académica del estudio.');
			return false;
    	}
    	return true;
	}
	function validateDates(dayiniAdd, monthiniAdd, yeariniAdd, dayfinAdd, monthfinAdd, yearfinAdd) {
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
	 	var valid = dif >= 0;
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
	 	if (!valid) {
	 		dayiniAdd.focus();
	 		message('Es necesario indicar la fecha inicial y final con valores validos');
	 		return false;	 
	 	}
	 	return true;
	 }
</script>
<noscript>Funciones de JavaScript desactivadas por navegador</noscript>

<%
	int index = 1;
	String studieTypeAdd = "studieTypeAdd";
	String studieAdd = "studieAdd";
	String institutionAdd = "institutionAdd";
	String dayIniAdd = "dayIniAdd";
	String dayFinAdd = "dayFinAdd";
	String monthIniAdd = "monthIniAdd";
	String monthFinAdd = "monthFinAdd";
	String yearIniAdd = "yearIniAdd";
	String yearFinAdd = "yearFinAdd";
	String idCandidateStudie = "idCandidateStudie";
	String statusAcademicAdd = "statusAcademicAdd";
	String principalAdd = "principalAdd";
	List<CandidatoOtroEstudioVO> otherStudies = (List<CandidatoOtroEstudioVO>)request.getSession().getAttribute("OTHER_STUDIES_LIST");
	if (null == otherStudies || otherStudies.isEmpty()) otherStudies = new ArrayList<CandidatoOtroEstudioVO>();
	Iterator<CandidatoOtroEstudioVO> itOtherStudies = otherStudies.iterator();
	while (itOtherStudies.hasNext()) {
		CandidatoOtroEstudioVO oe = itOtherStudies.next();
		oe.setRow(index);
%>
		<div class="otros_estudios bloque" id="bloque<%=index %>" >
        	<div class="grid1_3 margin_top_10 fl">
	        	<input type="hidden" name="<% out.print(idCandidateStudie + index); %>" id="<% out.print(idCandidateStudie + index); %>" value="<%=oe.getIdCandidatoOtroEstudio() %>" /> 
	            <input type="hidden" name="<% out.print(principalAdd + index); %>" id="<% out.print(principalAdd + index); %>" value="<%=oe.getPrincipal() %>" /> 
	            <label>Otros estudios</label>
	            <select name="<% out.print(studieTypeAdd + index); %>" id="<% out.print(studieTypeAdd + index); %>" data-dojo-type="dijit.form.Select">
	            	<option value="0">Selecciona</option>
	                <option value="1" <% if (oe.getIdTipoEstudio() == 1) out.print(" selected=\"selected\" "); %>>Curso</option>
	                <option value="2" <% if (oe.getIdTipoEstudio() == 2) out.print(" selected=\"selected\" "); %>>Diplomado</option>
	                <option value="3" <% if (oe.getIdTipoEstudio() == 3) out.print(" selected=\"selected\" "); %>>Taller</option>
	            </select>
            </div>
            <div class="grid1_3 margin_top_10 fl">
	        	<label for="<% out.print(studieAdd + index); %>">Nombre del estudio</label>
	            <input class="sugerido" type="text" name="<% out.print(studieAdd + index); %>" id="<% out.print(studieAdd + index); %>" trim ="true" uppercase="true"
	            	value="<%=oe.getNombreEstudio() %>" maxlength="50" regExp="^[\w\d\s\.áéíóúÁÉÍÓÚñÑ]{1,50}$" dojoType="dijit.form.ValidationTextBox" required="true"/>
            </div>
            <div class="grid1_3 margin_top_10 fl">
	        	<label for="<% out.print(institutionAdd + index); %>">Institución</label>
	            	<input class="sugerido" type="text" name="<% out.print(institutionAdd + index); %>" id="<% out.print(institutionAdd + index); %>" 
	            		value="<%=oe.getInstitucion() %>" trim ="true" uppercase="true" maxlength="50" regExp="^[\w\d\s\.áéíóúÁÉÍÓÚñÑ]{1,50}$" 
		        		dojoType="dijit.form.ValidationTextBox" required="true"/>
            </div>
            <div class="grid1_3 margin_top_10 fl">
            	<label for="<% out.print(statusAcademicAdd + index); %>">Situación académica</label>
	            <select name="<% out.print(statusAcademicAdd + index); %>" id="<% out.print(statusAcademicAdd + index); %>" dojotype="dijit.form.FilteringSelect">
	               <option value="0">Selecciona</option>
	               <option value="1" <% if (oe.getIdEstatusAcademico() == 1) out.print(" selected=\"selected\" "); %>>En curso</option>
	               <option value="2" <% if (oe.getIdEstatusAcademico() == 2) out.print(" selected=\"selected\" "); %>>Incompleto</option>
	               <option value="3" <% if (oe.getIdEstatusAcademico() == 3) out.print(" selected=\"selected\" "); %>>Terminado</option>
	            </select>
           	</div>
            <div class="grid1_3 margin_top_10 fl">
            	<div class="grid1_3 fl">
            		<label for="<% out.print(dayIniAdd + index); %>">Fecha de inicio</label>
	                <select name="<% out.print(dayIniAdd + index); %>" id="<% out.print(dayIniAdd + index); %>" data-dojo-type="dijit.form.Select" class="sugerido">
	           			<option value="0">Día</option>
	           			<%=oe.getSelectedDay(oe.getFechaInicio()) %>
			        </select>
	                <select name="<% out.print(monthIniAdd + index); %>" id="<% out.print(monthIniAdd + index); %>" data-dojo-type="dijit.form.Select" class="sugerido">
	                	<option value="0">Mes</option>
	           			<%=oe.getSelectedMonth(oe.getFechaInicio()) %>
	                </select>
	                <select name="<% out.print(yearIniAdd + index); %>" id="<% out.print(yearIniAdd + index); %>" data-dojo-type="dijit.form.Select" class="sugerido">
	                    <option value="0">Año</option>
	           		    <%=oe.getSelectedYear(oe.getFechaInicio()) %>
	                </select> 
            	</div> 
            </div>
            <div class="grid1_3 margin_top_10 fl">
            	<div class="grid1_3 fl">
            		<label for="<% out.print(dayFinAdd + index); %>">Fecha de fin</label>
	                <select name="<% out.print(dayFinAdd + index); %>" id="<% out.print(dayFinAdd + index); %>" data-dojo-type="dijit.form.Select" class="sugerido">
	           			<option value="0">Día</option>
	           			<%=oe.getSelectedDay(oe.getFechaFin()) %>
			        </select>
	                <select name="<% out.print(monthFinAdd + index); %>" id="<% out.print(monthFinAdd + index); %>" data-dojo-type="dijit.form.Select" class="sugerido">
	                	<option value="0">Mes</option>
	           			<%=oe.getSelectedMonth(oe.getFechaFin()) %>
	                </select>
	                <select name="<% out.print(yearFinAdd + index); %>" id="<% out.print(yearFinAdd + index); %>" data-dojo-type="dijit.form.Select" class="sugerido">
	                    <option value="0">Año</option>
	           		    <%=oe.getSelectedYear(oe.getFechaFin()) %>
	                </select>
            	</div>
            </div><div class="clearfix"></div>
            <p>
                <input type="button" title="Eliminar otro estudio" value="Eliminar" onclick="doDeleteStudie(<%=index %>);"/>
            </p> 
    	</div>
<% 
		index++;
	}
	
	if (null==otherStudies || otherStudies.isEmpty() || otherStudies.size() < 3) {
%>
		<div class="clearfix"></div>
	    <div class="add_ph margin_top_10 ta_right" style="display:block; width: 918px">
	    	<input type="button" id="btnAgregar" name="btnAgregar" class="agregar"
	    		   onclick="javascript:toggle();" title="Agregar otro estudio" value="Agregar otro estudio" />
	    </div>
<%  
	}else {
%>
		<div class="entero"></div>
<%  
	}
%>
		<div class="otros_estudios bloque" id="studiesadd" style="display:none">
        	<h3>Agregar otro estudio</h3>
        	<div class="grid1_3 fl">
            	<label for="studietypeadd">Otros estudios</label>
                <select name="studietypeadd" id="studietypeadd" class="sugerido" data-dojo-type="dijit.form.Select">
                    <option value="0">Selecciona</option>
                    <option value="1">Curso</option>
                    <option value="2">Diplomado</option>
                    <option value="3">Taller</option>
                </select>
			</div>
            <div class="grid1_3 fl">
				<label for="studieadd">Nombre del estudio</label>
                <input class="sugerido" type="text" name="studieadd" id="studieadd" value="" maxlength="50" regExp="^[\w\d\s\.áéíóúÁÉÍÓÚñÑ]{1,50}$" 
	        		dojoType="dijit.form.ValidationTextBox" trim ="true" uppercase="true" required="true"/>
            </div>
            <div class="grid1_3 fl">
            	<label for="institutionadd">Institución</label>
            	<input class="sugerido" type="text" name="institutionadd" id="institutionadd" value="" maxlength="50" regExp="^[\w\d\s\.áéíóúÁÉÍÓÚñÑ]{1,50}$" 
	        		dojoType="dijit.form.ValidationTextBox" trim ="true" uppercase="true" required="true"/>
            </div>
            <div class="clearfix"></div>
            <div class="margin_top_30">
            	<div class="grid1_3 fl">
	            	<label for="statusacademic">Situación académica</label>
	                <select name="statusacademic" id="statusacademic" class="sugerido" data-dojo-type="dijit.form.Select">
	                    <option value="0">Selecciona</option>
	                    <option value="1">En curso</option>
	                    <option value="2">Incompleto</option>
	                    <option value="3">Terminado</option>
	                </select>
	            </div>
	            <div class="grid1_3 fl">
	            	<label for="dayiniadd">Fecha de inicio</label>
	                <select name="dayiniadd" id="dayiniadd" class="sugerido" data-dojo-type="dijit.form.Select">
	           			<option value="0">Día</option>
	           			<option value="01">01</option>
	           			<option value="02">02</option>
	           			<option value="03">03</option>
	           			<option value="04">04</option>
	           			<option value="05">05</option>
	           			<option value="06">06</option>
	           			<option value="07">07</option>
	           			<option value="08">08</option>
	           			<option value="09">09</option>
		           		<option value="10">10</option>
			            <option value="11">11</option>
			            <option value="12">12</option>
			            <option value="13">13</option>
			            <option value="14">14</option>
			            <option value="15">15</option>
	 	   	            <option value="16">16</option>
	 		            <option value="17">17</option>
			            <option value="18">18</option>
			            <option value="19">19</option>
			            <option value="20">20</option>
			            <option value="21">21</option>
			            <option value="22">22</option>
			            <option value="23">23</option>
			            <option value="24">24</option>
			            <option value="25">25</option>
			            <option value="26">26</option>
			            <option value="27">27</option>
			            <option value="28">28</option>
			            <option value="29">29</option>
			            <option value="30">30</option>
			            <option value="31">31</option>
			        </select>
	                <select name="monthiniadd" id="monthiniadd" class="sugerido" data-dojo-type="dijit.form.Select">
	                	<option value="0">Mes</option>
	           			<option value="01">Enero</option>
	           			<option value="02">Febrero</option>
	           			<option value="03">Marzo</option>
	           			<option value="04">Abril</option>
	           			<option value="05">Mayo</option>
	           			<option value="06">Junio</option>
	           			<option value="07">Julio</option>
	           			<option value="08">Agosto</option>
	           			<option value="09">Septiembre</option>
	           			<option value="10">Octubre</option>
	           			<option value="11">Noviembre</option>
	           			<option value="12">Diciembre</option>
	                </select>
	                <select name="yeariniadd" id="yeariniadd" class="sugerido" data-dojo-type="dijit.form.Select">
	                    <option value="0">Año</option>
	           		    <%
	                    	for (int i=1982; i<year+1; i++) {
	                    		out.println("<option value=\"" + i + "\">" + i + "</option>");
	                    	}
	                     %>
	                </select>
	            </div>    
	            <div class="grid1_3 fl">
	            	<label for="dayfinadd">Fecha de fin</label>
	                <select name="dayfinadd" id="dayfinadd" class="sugerido" data-dojo-type="dijit.form.Select">
	           			<option value="0">Día</option>
	           			<option value="01">01</option>
	           			<option value="02">02</option>
	           			<option value="03">03</option>
	           			<option value="04">04</option>
	           			<option value="05">05</option>
	           			<option value="06">06</option>
	           			<option value="07">07</option>
	           			<option value="08">08</option>
	           			<option value="09">09</option>
		           		<option value="10">10</option>
			            <option value="11">11</option>
			            <option value="12">12</option>
			            <option value="13">13</option>
			            <option value="14">14</option>
			            <option value="15">15</option>
	 	   	            <option value="16">16</option>
	 		            <option value="17">17</option>
			            <option value="18">18</option>
			            <option value="19">19</option>
			            <option value="20">20</option>
			            <option value="21">21</option>
			            <option value="22">22</option>
			            <option value="23">23</option>
			            <option value="24">24</option>
			            <option value="25">25</option>
			            <option value="26">26</option>
			            <option value="27">27</option>
			            <option value="28">28</option>
			            <option value="29">29</option>
			            <option value="30">30</option>
			            <option value="31">31</option>
			        </select>
	                <select name="monthfinadd" id="monthfinadd" class="sugerido" data-dojo-type="dijit.form.Select">
	                	<option value="0">Mes</option>
	           			<option value="01">Enero</option>
	           			<option value="02">Febrero</option>
	           			<option value="03">Marzo</option>
	           			<option value="04">Abril</option>
	           			<option value="05">Mayo</option>
	           			<option value="06">Junio</option>
	           			<option value="07">Julio</option>
	           			<option value="08">Agosto</option>
	           			<option value="09">Septiembre</option>
	           			<option value="10">Octubre</option>
	           			<option value="11">Noviembre</option>
	           			<option value="12">Diciembre</option>
	                </select>
	                <select name="yearfinadd" id="yearfinadd" class="sugerido" data-dojo-type="dijit.form.Select">
	                    <option value="0">Año</option>
	                    <%
	                    	for (int i=1982; i<year+1; i++) {
	                    		out.println("<option value=\"" + i + "\">" + i + "</option>");
	                    	}
	                     %>
	                </select>
	            </div>
	            <div class="clearfix"></div>
            </div>
            <div class="ta_right margin_top_10" style="width: 918px">
                <input type="button" class="enviar" title="Guardar otro estudio" value="Guardar otro estudio" onclick="addStudie();"/>
            </div>
    	</div>