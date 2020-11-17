<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page import="java.util.List, java.util.Iterator, java.util.ArrayList, mx.gob.stps.portal.core.candidate.vo.CandidatoOtroEstudioVO"%>
<script>
	dojo.require("dijit.Calendar");
	dojo.require("dijit.form.DateTextBox");
	dojo.require("dijit.form.Select");
	
	function addStudie() {

		if (isValidStudie() && validateDates()) {
			
			var studieTypeAdd		= document.getElementById('studietypeadd').value;
			var studieAdd			= encodeURIComponent(document.getElementById('studieadd').value);
			var institutionAdd		= encodeURIComponent(document.getElementById('institutionadd').value);
			var dayiniAdd			= document.getElementById('dayiniadd').value;
			var monthiniAdd			= document.getElementById('monthiniadd').value;
			var yeariniAdd			= document.getElementById('yeariniadd').value;
			var dateini 			= yeariniAdd + '-' + monthiniAdd + '-' + dayiniAdd;
			var dayfinAdd			= document.getElementById('dayfinadd').value;
			var monthfinAdd			= document.getElementById('monthfinadd').value;
			var yearfinAdd			= document.getElementById('yearfinadd').value;
			var datefin 			= yearfinAdd + '-' + monthfinAdd + '-' + dayfinAdd;
			var statusacademicAdd	= document.getElementById('statusacademic').value;

			dojo.xhrGet({url: 'escolaridad.do?method=saveAddStudie&studieTypeAdd='+studieTypeAdd+'&studieAdd='+studieAdd+'&institutionAdd='+institutionAdd+'&dateini='+dateini+'&datefin='+datefin+'&statusacademicAdd='+statusacademicAdd, form:'escolaridadForm', sync: true, timeout:180000, 
						  load: function(data) {
							  	document.escolaridadForm.studietypeadd.value='';
							  	document.escolaridadForm.studieadd.value='';
							  	document.escolaridadForm.institutionadd.value='';
							  	document.escolaridadForm.dayiniadd.value='';
							  	document.escolaridadForm.monthiniadd.value='';
							  	document.escolaridadForm.yeariniadd.value='';
							  	document.escolaridadForm.dayfinadd.value='';
							  	document.escolaridadForm.monthfinadd.value='';
							  	document.escolaridadForm.yearfinadd.value='';
							  	document.escolaridadForm.monthiniadd.value='';
							  	document.escolaridadForm.statusacademic.value='';
							  	
								dojo.byId('studieadd').value = '';
								dojo.byId('institutionadd').value = '';
							  	
							    dojo.byId('otros_estudios bloque').innerHTML=data;
						  }});
		}
	}
	
	function toggle() {
		
		document.getElementById('studiesadd').style.display = 'block';
		document.getElementById('btnAgregar').style.display = 'none';
		
    }
    function doDeleteStudie(idstudie, index) {
    	document.getElementById('bloque'+index).style.backgroundColor = '#E0ECF8';
		if (confirm('Esta usted seguro de eliminar estudio')) {
			deleteStudie(idstudie);
		}else
			document.getElementById('bloque'+index).style.backgroundColor = 'white';	
	}
	function deleteStudie(idstudie) {
		dojo.xhrGet({url: 'escolaridad.do?method=deleteStudie&idStudie='+idstudie, form:'escolaridadForm', sync: true, timeout:180000, 
					  load: function(data) {
						dojo.byId('otros_estudios bloque').innerHTML=data;
					  }});
	}
	function isValidStudie() {
		var studietypeadd = document.getElementById('studietypeadd');
		var studieadd = dojo.byId('studieadd');
		var institutionadd = dojo.byId('institutionadd');
		var dayiniadd = document.getElementById('dayiniadd');
		var monthiniadd = document.getElementById('monthiniadd');
		var yeariniadd = document.getElementById('yeariniadd');
		var dayfinadd = document.getElementById('dayfinadd');
		var monthfinadd = document.getElementById('monthfinadd');
		var yearfinadd = document.getElementById('yearfinadd');
		var statusacademic = document.getElementById('statusacademic');
		if (studietypeadd.value == 0) {
    		alert('Debe seleccionar otro estudio.');
			return false;
    	}
    	if (!studieadd.value) {
			alert('Es necesario indicar el nombre del estudio.');
			studieadd.focus();
			return false;
		}
		if (!institutionadd.value) {
			alert('Es necesario indicar la institución del estudio.');
			institutionadd.focus();
			return false;
		}
		if (dayiniadd.value == 0) {
    		alert('Debe seleccionar día de fecha de inicio del estudio.');
			return false;
    	}
    	if (monthiniadd.value == 0) {
    		alert('Debe seleccionar mes de fecha de inicio del estudio.');
			return false;
    	}
    	if (yeariniadd.value == 0) {
    		alert('Debe seleccionar año de fecha de inicio del estudio.');
			return false;
    	}
    	if (dayfinadd.value == 0) {
    		alert('Debe seleccionar día de fecha de fin del estudio.');
			return false;
    	}
    	if (monthfinadd.value == 0) {
    		alert('Debe seleccionar mes de fecha de fin del estudio.');
			return false;
    	}
    	if (yearfinadd.value == 0) {
    		alert('Debe seleccionar año de fecha de fin del estudio.');
			return false;
    	}
    	if (!validateDates()) {
    		return false;
    	}
    	if (statusacademic.value == 0) {
    		alert('Debe seleccionar situación académica del estudio.');
			return false;
    	}
    	return true;
	}
	function validateDates() {
	 	var dayiniAdd=document.getElementById('dayiniadd').value;
	 	var monthiniAdd=document.getElementById('monthiniadd').value;
	 	var yeariniAdd=document.getElementById('yeariniadd').value;
	 	var inicialdate = new Date(yeariniAdd, monthiniAdd, dayiniAdd);
	 	var dayfinAdd=document.getElementById('dayfinadd').value;
	 	var monthfinAdd=document.getElementById('monthfinadd').value;
	 	var yearfinAdd=document.getElementById('yearfinadd').value;
	 	var finaldate = new Date(yearfinAdd, monthfinAdd, dayfinAdd);
	 	var hoy = new Date();
	 	var fhBuscaEmp = new Date();
	 	var fhEstFin = new Date();
		fhBuscaEmp.setFullYear(yeariniAdd,--monthiniAdd,dayiniAdd);
		fhEstFin.setFullYear(yearfinAdd,--monthfinAdd,dayfinAdd);
	 	var dif = dojo.date.compare(finaldate, inicialdate, 'date');
	 	var valido = dif >= 0;
	 	if (fhBuscaEmp > hoy){
	 	 alert('La fecha de inicio de otros estudios no puede ser mayor al dia de hoy.');
	 	 return false;
	 	}
	 	if (fhEstFin > hoy){
	 	 alert('La fecha de fin de otros estudios no puede ser mayor al dia de hoy.');
	 	 return false;
	 	}
	 	if (!valido) {
	 	 alert('Es necesario indicar la fecha inicial y final con valores validos');	 
	 	}
	 	return valido;
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
	
	List<CandidatoOtroEstudioVO> otherStudies = (List<CandidatoOtroEstudioVO>)request.getSession().getAttribute("OTHER_STUDIES_LIST");
	if (null == otherStudies || otherStudies.isEmpty()) otherStudies = new ArrayList<CandidatoOtroEstudioVO>();
	
	Iterator<CandidatoOtroEstudioVO> itOtherStudies = otherStudies.iterator();
	
	while (itOtherStudies.hasNext()) {

		CandidatoOtroEstudioVO oe = itOtherStudies.next();
%>
		<div class="otros_estudios bloque" id="bloque<%=index %>" > 
        	<div class="labeled">Otros estudios</div>
        	
        	<div class="margin_top_30">
        		<div class="grid1_3 fl">
	        		<input type="hidden" name="<% out.print(idCandidateStudie + index); %>" id="<% out.print(idCandidateStudie + index); %>" value="<%=oe.getIdCandidatoOtroEstudio() %>" /> 
	            	<label>Otros estudios</label>
	                <select name="<% out.print(studieTypeAdd + index); %>" id="<% out.print(studieTypeAdd + index); %>" data-dojo-type="dijit.form.Select">
	                    <option value="0">Selecciona</option>
	                    <option value="1" <% if (oe.getIdTipoEstudio() == 1) out.print(" selected=\"selected\" "); %>>Curso</option>
	                    <option value="2" <% if (oe.getIdTipoEstudio() == 2) out.print(" selected=\"selected\" "); %>>Diplomado</option>
	                    <option value="3" <% if (oe.getIdTipoEstudio() == 3) out.print(" selected=\"selected\" "); %>>Taller</option>
	                </select>
                </div>
                <div class="grid1_3 fl">
	            	<label for="<% out.print(studieAdd + index); %>">Nombre del estudio</label>
	                <input class="sugerido" type="text" name="<% out.print(studieAdd + index); %>" id="<% out.print(studieAdd + index); %>" value="<%=oe.getNombreEstudio() %>" maxlength="50" regExp="^[\w\d\s\.áéíóúÁÉÍÓÚñÑ]{1,50}$" 
		        		dojoType="dijit.form.ValidationTextBox" required="true"/>
            	</div>
            	<div class="grid1_3 fl">
	            	<label for="<% out.print(institutionAdd + index); %>">Institución</label>
	            	<input class="sugerido" type="text" name="<% out.print(institutionAdd + index); %>" id="<% out.print(institutionAdd + index); %>" value="<%=oe.getInstitucion() %>" maxlength="50" regExp="^[\w\d\s\.áéíóúÁÉÍÓÚñÑ]{1,50}$" 
		        		dojoType="dijit.form.ValidationTextBox" required="true"/>
            	</div>
            </div>
            <div class="clearfix"></div>
            <div class="margin_top_30">
            	<div class="grid1_3 fl">
            		<div class="labeled">Fecha de inicio</div>
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
            	<div class="grid1_3 fl">
            		<div class="labeled">Fecha de fin</div>
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
            </div>
            <div class="clearfix"></div>
            	<div class="grid1_3 margin_top_30 s_academica">
            		<label for="<% out.print(statusAcademicAdd + index); %>">Situación académica</label>
	                <select name="<% out.print(statusAcademicAdd + index); %>" id="<% out.print(statusAcademicAdd + index); %>" class="sugerido" data-dojo-type="dijit.form.Select">
	                    <option value="0">Selecciona</option>
	                    <option value="1" <% if (oe.getIdEstatusAcademico() == 1) out.print(" selected=\"selected\" "); %>>En curso</option>
	                    <option value="2" <% if (oe.getIdEstatusAcademico() == 2) out.print(" selected=\"selected\" "); %>>Incompleto</option>
	                    <option value="3" <% if (oe.getIdEstatusAcademico() == 3) out.print(" selected=\"selected\" "); %>>Terminado</option>
	                </select>
            	</div>
            <p>
                <input type="button" title="Eliminar otro estudio" value="Eliminar" onclick="doDeleteStudie(<%=oe.getIdCandidatoOtroEstudio()%>, <%=index %>);"/>
            </p> 
    	</div>
<% 
		index++;
	}
	
	if (null==otherStudies || otherStudies.isEmpty() || otherStudies.size() < 11) {
%>
	    <div class="add_ph margin_top_10 ta_right" style="display:block; width: 918px">
	    	<input type="button" id="btnAgregar" name="btnAgregar" class="agregar"
	    		   onclick="javascript:toggle();" title="Agregar otro estudio" value="Agregar otro estudio" />
	    </div>
<%  
	}else {
%>
		<div class="entero">
	     	
		</div>
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
	        		dojoType="dijit.form.ValidationTextBox" required="true"/>
            </div>
            <div class="grid1_3 fl">
            	<label for="institutionadd">Institución</label>
            	<input class="sugerido" type="text" name="institutionadd" id="institutionadd" value="" maxlength="50" regExp="^[\w\d\s\.áéíóúÁÉÍÓÚñÑ]{1,50}$" 
	        		dojoType="dijit.form.ValidationTextBox" required="true"/>
            </div>
            <div class="clearfix"></div>
            <div class="margin_top_30">
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
	           		    <option value="1982">1982</option>
			            <option value="1983">1983</option>
			            <option value="1984">1984</option>
			            <option value="1985">1985</option>
			            <option value="1986">1986</option>
			            <option value="1987">1987</option>
			            <option value="1988">1988</option>
			            <option value="1989">1989</option>
			            <option value="1990">1990</option>
			            <option value="1991">1991</option>
			            <option value="1992">1992</option>
			            <option value="1993">1993</option>
			            <option value="1994">1994</option>
			            <option value="1995">1995</option>
			            <option value="1996">1996</option>
			            <option value="1997">1997</option>
			            <option value="1998">1998</option>
			            <option value="1999">1999</option>
			            <option value="2000">2000</option>
			            <option value="2001">2001</option>
			            <option value="2002">2002</option>
			            <option value="2003">2003</option>
			            <option value="2004">2004</option>
			            <option value="2005">2005</option>
			            <option value="2006">2006</option>
			            <option value="2007">2007</option>
			            <option value="2008">2008</option>
			            <option value="2009">2009</option>
			            <option value="2010">2010</option>
			            <option value="2011">2011</option>
			            <option value="2012">2012</option>
			            <option value="2013">2013</option>
			            <option value="2014">2014</option>
			            <option value="2015">2015</option>
			            <option value="2016">2016</option>
			            <option value="2017">2017</option>
			            <option value="2018">2018</option>
			            <option value="2019">2019</option>
			            <option value="2020">2020</option>
			            <option value="2021">2021</option>
			            <option value="2022">2022</option>
			            <option value="2023">2023</option>
			            <option value="2024">2024</option>
			            <option value="2025">2025</option>
			            <option value="2026">2026</option>
			            <option value="2027">2027</option>
			            <option value="2028">2028</option>
			            <option value="2029">2029</option>
			            <option value="2030">2030</option>
			            <option value="2031">2031</option>
	           		    <option value="2032">2032</option>
	                </select>
	                <!-- img class="ico_calendar" src="images/ico_calendario.gif" / -->
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
	           		    <option value="1982">1982</option>
			            <option value="1983">1983</option>
			            <option value="1984">1984</option>
			            <option value="1985">1985</option>
			            <option value="1986">1986</option>
			            <option value="1987">1987</option>
			            <option value="1988">1988</option>
			            <option value="1989">1989</option>
			            <option value="1990">1990</option>
			            <option value="1991">1991</option>
			            <option value="1992">1992</option>
			            <option value="1993">1993</option>
			            <option value="1994">1994</option>
			            <option value="1995">1995</option>
			            <option value="1996">1996</option>
			            <option value="1997">1997</option>
			            <option value="1998">1998</option>
			            <option value="1999">1999</option>
			            <option value="2000">2000</option>
			            <option value="2001">2001</option>
			            <option value="2002">2002</option>
			            <option value="2003">2003</option>
			            <option value="2004">2004</option>
			            <option value="2005">2005</option>
			            <option value="2006">2006</option>
			            <option value="2007">2007</option>
			            <option value="2008">2008</option>
			            <option value="2009">2009</option>
			            <option value="2010">2010</option>
			            <option value="2011">2011</option>
			            <option value="2012">2012</option>
			            <option value="2013">2013</option>
			            <option value="2014">2014</option>
			            <option value="2015">2015</option>
			            <option value="2016">2016</option>
			            <option value="2017">2017</option>
			            <option value="2018">2018</option>
			            <option value="2019">2019</option>
			            <option value="2020">2020</option>
			            <option value="2021">2021</option>
			            <option value="2022">2022</option>
			            <option value="2023">2023</option>
			            <option value="2024">2024</option>
			            <option value="2025">2025</option>
			            <option value="2026">2026</option>
			            <option value="2027">2027</option>
			            <option value="2028">2028</option>
			            <option value="2029">2029</option>
			            <option value="2030">2030</option>
			            <option value="2031">2031</option>
	           		    <option value="2032">2032</option>
	                </select>
	            </div>
	            <div class="grid1_3 fl">
	            	<label for="statusacademic">Situación académica</label>
	                <select name="statusacademic" id="statusacademic" class="sugerido" data-dojo-type="dijit.form.Select">
	                    <option value="0">Selecciona</option>
	                    <option value="1">En curso</option>
	                    <option value="2">Incompleto</option>
	                    <option value="3">Terminado</option>
	                </select>
	            </div>
	            <div class="clearfix"></div>
            </div>
            <div class="ta_right margin_top_10" style="width: 918px">
                <input type="button" class="enviar" title="Guardar otro estudio" value="Guardar otro estudio" onclick="addStudie();"/>
            </div>
    	</div>