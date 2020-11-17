<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%String context = request.getContextPath() +"/";%>

<link href="${context}estilos_mi_espacio.css" rel="stylesheet" type="text/css" />

<style type="text/css">
	@import "dojotoolkit/dojo/resources/dojo.css";
	@import "dojotoolkit/dijit/themes/claro/claro.css";
</style> 

<script src="dojotoolkit/dojo/dojo.js" djConfig="parseOnLoad: true, locale:'es'"></script>
<noscript>Funciones de JavaScript desactivadas por navegador</noscript>
<link href="${PATH_CSS_SWB_APP}estilos_empleo.css" rel="stylesheet" type="text/css" />
<link href="${PATH_CSS_SWB_APP}estilos_canal.css" rel="stylesheet" type="text/css" />
<link href="${PATH_CSS_SWB_APP}estilos_mi_espacio.css" rel="stylesheet" type="text/css" />
<link href="${PATH_CSS_SWB_APP}estilos_lightBox.css" rel="stylesheet" type="text/css" />
<link href="${PATH_CSS_SWB}css_aplicacion/estilos_tabla.css" rel="stylesheet" type="text/css" />
<link href="${PATH_CSS_SWB_APP}estilos_genericos.css" rel="stylesheet" type="text/css" />

<script type="text/javascript">
    dojo.require("dijit.dijit"); // loads the optimized dijit layer
    dojo.require('dijit.Dialog'); 
    dojo.require("dijit.form.Form");
    dojo.require("dojo.date.locale");
    dojo.require("dijit.Calendar");
    dojo.require("dijit.form.DateTextBox");
    dojo.require("dijit.form.Textarea");
    dojo.require("dijit.form.ValidationTextBox");
	dojo.require("dojo.data.ItemFileReadStore");
	dojo.require("dijit.form.CheckBox");
	dojo.require("dijit.form.FilteringSelect");
	dojo.require("dijit.form.NumberTextBox");
	
	dojo.addOnLoad(function() {
		dojo.connect(dijit.byId("idAreaLaboralSelect"), "onChange", function() {
			dijit.byId('idOcupacionSelect').reset();
			var vArea = dijit.byId('idAreaLaboralSelect').get('value');
			//alert('IdiomaSelect:'+vIdioma);
			
            if(vArea != 0) {
            	ocupacionStore.url = "${context}histLaborales.do?method=cargarOcupacion&idAreaLaboral="+ vArea;
            	ocupacionStore.close();
			}
        });        
	});
//---------------------------------------------------------------------------------------------------------	
 	
var chkThis;
var tableIDThis;
var rowIDThis;
	 
function confirmAlert(mensaje){
		dojo.byId('mensajeConfir').innerHTML = mensaje;
		dijit.byId('MensajeAlerConfirt').show();		
}
function closeDialog(){
        dijit.byId('MensajeAlerConfirt').hide();
        setTimeout("window.location.reload();",10);
} 	 
	
 
function deleteRow(chk,rowID) {
   
	chkThis 	= chk;	
	rowIDThis 	= rowID;
    
	confirmAlert("&iquest;Confirma que desea eliminar el registro?");
   	
}
    
function deleteRowAux(){ 
	var fila = document.getElementById(rowIDThis);
	fila.parentNode.removeChild(fila); 				
	doSubmitDelete(chkThis.value);
     	setTimeout("window.location.reload();",10);
}
//---------------------------------------------------------------------------------------------------------
    
    function doSubmitDelete(idHist) {
    	dijit.byId('method').set('value', 'borrarHistLaboral');
    	dijit.byId('idHistorialLaboral').set('value', idHist);
    	dojo.xhrPost( {					 					 
			url: 'histLaborales.do',
		  	form:'histLaboralForm',
		  	timeout:180000,
		  	load: function(data){
				var res = dojo.fromJson(data); 
		    }
		 });
    }
    
    function doSubmitAdd() {
    	if(validarForm()) {
	    	dijit.byId('method').set('value', 'agregarHistLaboral');
	    	dijit.byId('idHistorialLaboral').set('value', '0');
	    	dijit.byId('idSector').set('value', 
	    	dijit.byId('idSectorSelect').get('value'));
	    	dijit.byId('idAreaLaboral').set('value', 
	    	dijit.byId('idAreaLaboralSelect').get('value'));
	    	dijit.byId('idOcupacion').set('value', 
	    	dijit.byId('idOcupacionSelect').get('value'));
	    	dijit.byId('aniosLaborados').set('value', 
	    	dijit.byId('aniosLaboradosSelect').get('value'));
	    	dijit.byId('idJerarquia').set('value', 
	    	dijit.byId('idJerarquiaSelect').get('value'));
	    	dijit.byId('personasCargo').set('value', 
	    	dijit.byId('personasCargoSelect').get('value'));
	    	
	    	
	    	if (dijit.byId('trabajoActual').checked) {
		   		dijit.byId('trabajoActual').set('value', '${trabActualSI}');
		   	} else {
		   		dijit.byId('trabajoActual').set('value', '${trabActualNO}');
		   	}
	    	
	    	
	    	dojo.xhrPost( {
				url: 'histLaborales.do',
			  	form:'histLaboralForm',
			  	timeout:180000,
			  	load: function(data){
					var res = dojo.fromJson(data);
					if (res.msg.type == 'ext') {
						agregarHist('tblHists', res.idHistorialLaboral);
						dijit.byId('histLaboralForm').reset();
						alert(res.msg.message);
						setTimeout("window.location.reload();",10);
					} else {
						alert(res.msg.message);
						dijit.byId('idSectorSelect').focus();
					}
			    }
			 });
		 }
    }
    
    var count = ${histLaboralForm.elementos};
    function agregarHist(tableID, idHist) {
    	var table = document.getElementById(tableID);
     	var rowCount = table.rows.length;
		 
		try {
			var row = table.insertRow(rowCount);
			count++;
			var trId = 'trHist' + count;
			row.id = trId;
			//alert('id: '+row.id);
			//CHECKBOX			 	
	      	var cell1 = row.insertCell(0);
		    var element1 = document.createElement("input");
		    element1.type = "checkbox";
		    element1.id = "chkHist" + rowCount;
		    element1.name = "chkHist" + rowCount;	      
		    element1.value = idHist;//Pendiente idHistorialLaboral
		    
		    element1.onclick = function(){
		    	deleteRow(this, eval(tableID), row.id);
		    };
		    
		    
		    //DIV CLASS SELECCION PARA CHECKBOX
		    var div = document.createElement('div');
		    div.appendChild(element1);
		    div.setAttribute('class', 'seleccion');
		    cell1.appendChild(div);
	   		//Sector
	        var cell2 = row.insertCell(1);
	        var texto2 = document.createTextNode(dijit.byId('idSectorSelect').get('displayedValue'));
	        cell2.appendChild(texto2);
	        //Puesto
	        var cell3 = row.insertCell(2);
	        var texto3 = document.createTextNode(dijit.byId('puesto').value);
	        cell3.appendChild(texto3);
	        //Area Laboral
	        var cell4 = row.insertCell(3);
	        var texto4 = document.createTextNode(dijit.byId('idAreaLaboralSelect').get('displayedValue'));
	        cell4.appendChild(texto4);
	        //Ocupacion
	        var cell5 = row.insertCell(4);
	        var texto5 = document.createTextNode(dijit.byId('idOcupacionSelect').get('displayedValue'));
	        cell5.appendChild(texto5);
	        //Labores Inicial
	        var cell7 = row.insertCell(5);
	        var texto7 = document.createTextNode(dijit.byId('laboresInicial').get('displayedValue'));
	        cell7.appendChild(texto7);
	        //Labores Final
	        var cell8 = row.insertCell(6);
	        var texto8 = document.createTextNode(dijit.byId('laboresFinal').get('displayedValue'));
	        cell8.appendChild(texto8);
	    } catch(e) {
	          alert(e);
	    }
     }
    
    function validarForm() {
    	
    	if (dijit.byId('histLaboralForm').isValid()) {
    		if (!validarFechas()) {
				return false;
			}
	    	if (!validarTextArea('funcion','Funciones desempeñadas contiene caracteres invalidos.')) {
				return false;
			}
			if (!validarTextArea('logro','Logros en la empresa contiene caracteres invalidos.')) {
				return false;
			}
    		return true;
    	} else {
    		alert('Los campos con * son obligatorios');
    		return false;
    	}
    }
    
    function validarTextArea(id,mensaje) {
	   	var txt = dijit.byId(id);	  
		var regExp = /^[\w\d\s\.áéíóúÁÉÍÓÚñÑ]{1,2000}$/;			
		if (!regExp.test(txt.get('value')) && txt.get('value') != '') {			
			alert(mensaje);
			txt.focus();
			return false;
		}
		return true;
	}
	
	
	
	function validarFechas() {
		var trabaja = dijit.byId('trabajoActual').checked;
		var dateIni = dijit.byId('laboresInicial');
		var dateFin = dijit.byId('laboresFinal');
		var dif = dojo.date.compare(dateFin.get('value'), dateIni.get('value'), 'date');
		var valido = dif >= 0;

		if(trabaja && dateFin == ''){
			return true;
		}	
		
		if (!valido) {
			alert('Favor de proporcionar la fecha inicial y final con valores validos');		
			dateIni.focus();
		}
		return valido;
	}
	
    function requerirFechaInicial(){
    	var trabaja = dijit.byId('trabajoActual');
    	
    	if (trabaja.checked) {
    		document.getElementById('lblFechaFinal').innerHTML = 'Fecha final';
    		dijit.byId('laboresFinal').attr('required', false);
    	}else{
    		document.getElementById('lblFechaFinal').innerHTML = 'Fecha final*';
    		dijit.byId('laboresFinal').attr('required', true);
    	}
    }

	
</script>

	<form name="histLaboralForm" id="histLaboralForm" method="post" action="histLaborales.do" dojoType="dijit.form.Form" >
	<div id="administrar_grado">
		<h2>Agregar trabajo</h2>
	
		<input type="hidden" name="method" id="method" value="init" dojoType="dijit.form.TextBox"/>
		<input type="hidden" name="idHistorialLaboral" id="idHistorialLaboral" dojoType="dijit.form.TextBox"/>
        <span class="entero">
        	<strong><label for="trabajoActual">Trabajo actual</label>:</strong>&nbsp;&nbsp;
        	<input type="checkbox" name="trabajoActual" id="trabajoActual" value="1" dojoType="dijit.form.CheckBox" required="false" onChange="requerirFechaInicial()" />
        </span>
        <div dojoType="dojo.data.ItemFileReadStore" jsId="sectorStore" urlPreventCache="true" clearOnClose="true" url="${context}histLaborales.do?method=cargarSector" ></div>
        <span class="entero">
        	<strong><label for="idSectorSelect">Sector</label>*</strong><br />
        	<input type="hidden" name="idSector" id="idSector" dojoType="dijit.form.TextBox" />
        	<select dojotype="dijit.form.FilteringSelect" store="sectorStore" id="idSectorSelect" required="true" style="width:70%"></select>
        </span>
        <span class="entero">
        	<strong><label for="puesto">Puesto</label>*</strong><br />
        	<input type="text" name="puesto" id="puesto" style="width:30em;" maxlength="150"
        	regExp = "^[\w\d\s\.áéíóúÁÉÍÓÚñÑ]{1,150}$";
        	dojoType="dijit.form.ValidationTextBox" required="true" />
        	
        </span>
        <div dojoType="dojo.data.ItemFileReadStore" jsId="areaLaboralStore" urlPreventCache="true" clearOnClose="true" url="${context}histLaborales.do?method=cargarAreaLaboral"></div>
        <span class="entero">
        	<strong><label for="idAreaLaboralSelect">&Aacute;rea laboral</label>*</strong><br />
        	<input type="hidden" name="idAreaLaboral" id="idAreaLaboral" dojoType="dijit.form.TextBox" />
        	<select dojotype="dijit.form.FilteringSelect" store="areaLaboralStore" id="idAreaLaboralSelect" required="true" style="width:70%"></select>
        </span>
        <div dojoType="dojo.data.ItemFileReadStore" jsId="ocupacionStore" urlPreventCache="true" clearOnClose="true"></div>
        <span class="entero">
        	<strong><label for="idOcupacionSelect">Ocupaci&oacute;n</label>*</strong><br />
        	<input type="hidden" name="idOcupacion" id="idOcupacion" dojoType="dijit.form.TextBox" />
        	<select dojotype="dijit.form.FilteringSelect" store="ocupacionStore" id="idOcupacionSelect" required="true" style="width:70%"></select>
        </span>
        <span class="un_medio">
        	<br/>
        	<strong><label for="empresa">Empresa</label>*</strong><br />
        	<input type="text" name="empresa" id="empresa" style="width:30em;" maxlength="150" 
        	regExp = "^[\w\d\s\.áéíóúÁÉÍÓÚñÑ]{1,150}$";
        	dojoType="dijit.form.ValidationTextBox" required="true" />
        </span>
        <span class="un_medio" style="text-align:left;">
        	<br/>
        	<br/><input type="checkbox" name="confidencialidadEmpresa" id="confidencialidadEmpresa" value="1" 
        	dojoType="dijit.form.CheckBox" required="false" />&nbsp;<strong><label for="confidencialidadEmpresa">Mostrar este dato</label></strong>
        </span>
        <div class="entero">
	        <span class="un_tercio" >
	        	<strong>Fecha inicial*</strong><br />
	            <input type="text" name="laboresInicial" id="laboresInicial" dojoType="dijit.form.DateTextBox" 
	            required="false" /><br />
	            <label for="laboresInicial">Seleccionar fecha.</label>
	        </span>
	        <span class="un_tercio">
	        	<strong id="lblFechaFinal">Fecha final*</strong><br />	
	            <input type="text" name="laboresFinal" id="laboresFinal" dojoType="dijit.form.DateTextBox" required="false" /><br />
	            <label for="laboresFinal">Seleccionar fecha.</label>
	        </span>
	        <div dojoType="dojo.data.ItemFileReadStore" jsId="experienciaStore" urlPreventCache="true" clearOnClose="true" url="${context}conocsHabs.do?method=cargarExperiencia" ></div>
	    	<span class="un_tercio">
	        	<strong><label for="aniosLaboradosSelect">A&ntilde;os laborados</label>*</strong><br />
	        	<input type="hidden" name="aniosLaborados" id="aniosLaborados" dojoType="dijit.form.TextBox" />
	        	<select dojotype="dijit.form.FilteringSelect" store="experienciaStore" id="aniosLaboradosSelect" required="true"></select>
	        </span>
        </div>
        <div dojoType="dojo.data.ItemFileReadStore" jsId="jerarquiaStore" urlPreventCache="true" clearOnClose="true" url="${context}histLaborales.do?method=cargarJerarquia" ></div>
        <span class="un_tercio" >
        	<strong><label for="idJerarquiaSelect">Jerarqu&iacute;a del puesto</label>*</strong><br />
            <input type="hidden" name="idJerarquia" id="idJerarquia" dojoType="dijit.form.TextBox" />
            <select dojotype="dijit.form.FilteringSelect" store="jerarquiaStore" id="idJerarquiaSelect" required="true"></select>
        </span>
        <div dojoType="dojo.data.ItemFileReadStore" jsId="personasStore" urlPreventCache="true" clearOnClose="true" url="${context}histLaborales.do?method=cargarPersonasCargo" ></div>
        <span class="un_tercio" >
        	<strong><label for="personasCargoSelect">Personas a cargo</label>*</strong><br />
            <input type="hidden" name="personasCargo" id="personasCargo" dojoType="dijit.form.TextBox" />
            <select dojotype="dijit.form.FilteringSelect" store="personasStore" id="personasCargoSelect" required="true"></select>
        </span>
    	<span class="un_tercio">
        	<strong><label for="salarioMensual">&Uacute;ltimo salario mensual</label>*</strong><br />
        	<input type="text" name="salarioMensual" id="salarioMensual" dojoType="dijit.form.ValidationTextBox" 
            maxlength="9" style="width:10em;" required="true" invalidMessage="Dato inválido" 
            regExp="^[+]?\d{1,6}(\.\d{1,2})?$" missingMessage="Dato requerido" trim="true" />
        </span>
        <span class="entero">
        	<strong><label for="funcion">Funciones desempe&ntilde;adas</label></strong><br />
        	<textarea name="funcion" id="funcion" maxlength="2000" onchange="validarTextArea(this.id,'Funciones desempeñadas contiene caracteres invalidos.');"
        	style="width:550px;min-height:120px;_height:200px;" dojoType="dijit.form.Textarea" required="false"></textarea>
        </span>
        <span class="entero">
        	<strong><label for="logro">Logros en la empresa</label></strong><br />
        	<textarea name="logro" id="logro" maxlength="2000" onchange="validarTextArea(this.id,'Logros en la empresa contiene caracteres invalidos.');" 
        	style="width:550px;min-height:120px;_height:200px;" dojoType="dijit.form.Textarea" required="false"></textarea>
        </span>
	</div>
	<div class="entero" style="text-align:center;">
		<br/>
    	<input type="button" class="boton" id="btnAgregarHist" value="Agregar" onclick="doSubmitAdd();" />
        <input type="button" class="boton" id="btnCancelar" value="Cancelar" onclick="self.close();" />        
    </div>


		<div id="publicados">
		<br/>
		<h3>Historial laboral</h3>

			<table id="tblHists" width="100%" border="0" cellspacing="0" cellpadding="0">
	        	<tr class="temas">
	        		<th width="10%">Eliminar</th>
	            	<!-- <th width="20%">Trabajo actual</th> -->
	                <th width="15%">Sector</th>
	                <th width="15%">Puesto</th>
	                <th width="15%">&Aacute;rea laboral</th>
	                <th width="15%">Ocupaci&oacute;n</th>
	                <th width="10%">Fecha inicial</th>
	                <th class="fin" width="10%">Fecha final</th>
	                <!-- <th width="10%">A&ntilde;os laborados</th>
	                <th width="10%">Jerarqu&iacute;a del puesto</th>
	                <th width="10%">Personas a cargo</th>
	                <th width="10%">&Uacute;ltimo salario mensual</th>
	                <th width="10%">Funciones desempe&ntilde;adas</th>
	                <th width="10%" class="fin">Logros en la empresa</th> -->
	            </tr>
	            <logic:notEmpty name="detalles" scope="session">
	            <logic:iterate id="detalles" indexId="index" name="detalles" type="mx.gob.stps.portal.core.candidate.vo.HistoriaLaboralVO">
	            <tr id="trHist${index + 1}">
	            	<td class="seleccion">
	            		<div class="seleccion">
	            			<input type="checkbox" name="chkHist" id="chkHist${index}" value="${detalles.idHistorialLaboral}" onclick="deleteRow(this,'trHist${index + 1}')" />
	            		</div>
	            	</td>
	            	<td>${detalles.sector}</td>
	            	<td>${detalles.puesto}</td>
	            	<td>${detalles.areaLaboral}</td>
	            	<td>${detalles.ocupacion}</td>
	            	<td>${detalles.fechaIni}</td>
	            	<td>	            	
	            	 <c:if test="${'11/11/2099' != detalles.fechaFin}">
								${detalles.fechaFin}
					</c:if>
	            	</td>	
	            </tr>
	            </logic:iterate>
	            </logic:notEmpty>
	        </table>
		</div>

	<div dojoType="dijit.Dialog" id="MensajeAlerConfirt" title="Mensaje" style="display:none"  style="width:300px;height:150px;" draggable ="false" >
		  <table class="alertDialog">
			 <tr align="center">
				 <td colspan="2"><div id ="mensajeConfir"></div>&nbsp;</td>			 
			 </tr>
			 <tr align="center">
				<td>
					 <input type="button" value = "Aceptar" onclick="deleteRowAux();">
				</td>
				<td>
					 <input type="button" value = "Cancelar" onclick="closeDialog()">
				</td>		
			 </tr>
		 </table>	
	</div>
	</form>
