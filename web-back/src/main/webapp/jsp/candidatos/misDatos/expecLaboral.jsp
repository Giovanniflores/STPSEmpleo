<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="url" value="${pageContext.request.requestURL}" />
<%@ page import="mx.gob.stps.portal.web.infra.utils.Utils"%>

<%String context = request.getContextPath() +"/";%>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd"> 
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="es" lang="es"> 
<head> 
<link rel="P3Pv1" href="https://www.empleo.gob.mx/w3c/p3p.xml" /> 
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" /> 

<title>Expectativa Laboral | Portal del Empleo</title> 
<meta property="og:title" content="Portal del Empleo: Expectativa Laboral">
<meta name="twitter:title" content="Portal del Empleo: Expectativa Laboral">
<meta property="og:description" content="Portal del Empleo: ${expecLaborales.sectorDeseado}">
<meta name="twitter:description" content="Portal del Empleo: ${expecLaborales.sectorDeseado}">
<meta name="description" content="Portal del Empleo: ${expecLaborales.sectorDeseado}">
<meta property="og:url" content="${url}">
<meta property="og:site_name" content="Portal del Empleo">
<meta property="og:image" content="http://qa.empleo.gob.mx/css/images/contenido-compartir.jpg">
<meta property="og:image:type" content="image/jpeg">
<meta property="og:image:width" content="1200">
<meta property="og:image:height" content="627">
<meta property="fb:app_id" content="308925772806125" />
<meta name="twitter:card" content="summary_large_image">
<meta name="twitter:site" content="@empleogob_mx">
<meta name="twitter:creator" content="@InfotecMexico">
<meta name="twitter:image:src" content="http://qa.empleo.gob.mx/css/images/compartir-contenido-tweetA.jpg">
<meta name="author" content="infotec">

<link href='https://fonts.googleapis.com/css?family=Droid+Sans' rel='stylesheet' type='text/css' />

<link href="${PATH_CSS_SWB_APP}estilos_empleo.css" rel="stylesheet" type="text/css" />
<link href="${PATH_CSS_SWB_APP}estilos_canal.css" rel="stylesheet" type="text/css" />
<link href="${PATH_CSS_SWB_APP}estilos_mi_espacio.css" rel="stylesheet" type="text/css" />
<link href="${PATH_CSS_SWB_APP}estilos_lightBox.css" rel="stylesheet" type="text/css" />
<link href="${PATH_CSS_SWB}css_aplicacion/estilos_tabla.css" rel="stylesheet" type="text/css" />
<link href="${PATH_CSS_SWB_APP}estilos_genericos.css" rel="stylesheet" type="text/css" />

<style type="text/css"> 
	@import "dojotoolkit/dojo/resources/dojo.css";
	@import "dojotoolkit/dijit/themes/claro/claro.css";
</style> 

<script src="dojotoolkit/dojo/dojo.js" djConfig="parseOnLoad: true, locale:'es'"></script>
<noscript>Funciones de JavaScript desactivadas por navegador</noscript>
<script type="text/javascript">
    dojo.require("dijit.dijit"); // loads the optimized dijit layer
    dojo.require("dijit.form.Form");
    dojo.require('dijit.Dialog'); 
    dojo.require("dijit.form.ValidationTextBox");
    dojo.require("dijit.form.TextBox");
	dojo.require("dojo.data.ItemFileReadStore");
	dojo.require("dijit.form.CheckBox");
	dojo.require("dijit.form.FilteringSelect");
	
	dojo.addOnLoad(function() {
		dojo.connect(dijit.byId("idAreaLaboralDeseadaSelect"), "onChange", function() {
			dijit.byId('idOcupacionDeseadaSelect').reset();
			var vArea = dijit.byId('idAreaLaboralDeseadaSelect').get('value');
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
        setTimeout("window.location.reload();",1000);
} 	 
	
 
function deleteRow(chk, tableID, rowID) {
   
	chkThis 	= chk;
	tableIDThis = tableID;
	rowIDThis 	= rowID;
    
	confirmAlert("&iquest;Confirma que desea eliminar el registro?");
   	
}
    
function deleteRowAux(){ 
	var fila = document.getElementById(rowIDThis);
	fila.parentNode.removeChild(fila); 				
	doSubmitDelete(chkThis.value);
     	setTimeout("window.location.reload();",1000);
}
//---------------------------------------------------------------------------------------------------------
    
    function doSubmitDelete(idHist) {
    	dijit.byId('method').set('value', 'borrar');
    	dijit.byId('idExpectativaLaboral').set('value', idHist);
    	dojo.xhrPost( {					 					 
			url: 'expecLaboral.do',
		  	form:'expecLaboralForm',
		  	timeout:180000,
		  	load: function(data){
				var res = dojo.fromJson(data); 
		    }
		 });
    }
    
    function doSubmitAdd() {
    	if(validarForm()) {
	    	dijit.byId('method').set('value', 'agregar');
	    	dijit.byId('idExpectativaLaboral').set('value', '0');
	    	dijit.byId('idSectorDeseado').set('value', 
	    	dijit.byId('idSectorDeseadoSelect').get('value'));
	    	dijit.byId('idAreaLaboralDeseada').set('value', 
	    	dijit.byId('idAreaLaboralDeseadaSelect').get('value'));
	    	dijit.byId('idOcupacionDeseada').set('value', 
	    	dijit.byId('idOcupacionDeseadaSelect').get('value'));
	    	dijit.byId('idTipoEmpleoDeseado').set('value', 
	    	dijit.byId('idTipoEmpleoDeseadoSelect').get('value'));
	    	dijit.byId('idTipoContrato').set('value', 
	    	dijit.byId('idTipoContratoSelect').get('value'));
	    	dojo.xhrPost( {
				url: 'expecLaboral.do',
			  	form:'expecLaboralForm',
			  	timeout:180000,
			  	load: function(data){
					var res = dojo.fromJson(data);
				    if (res.msg.type == 'ext') {
						agregarExpec('tblExpecs', res.idExpectativaLaboral);
						dijit.byId('expecLaboralForm').reset();
						alert(res.msg.message);
					} else {
						alert(res.msg.message);
						dijit.byId('idSectorDeseadoSelect').focus();
					}
			    }
			 });
		 }
    	setTimeout("window.location.reload();",1000);
    	
    }
    
    var count = ${expecLaboralForm.elementos};
    function agregarExpec(tableID, idExpec) {
    	var table = document.getElementById(tableID);
     	var rowCount = table.rows.length;
		 
		try {
			var row = table.insertRow(rowCount);
			count++;
			var trId = 'trExpec' + count;
			row.id = trId;
			//alert('id: '+row.id);
			//CHECKBOX			 	
	      	var cell1 = row.insertCell(0);
		    var element1 = document.createElement("input");
		    element1.type = "checkbox";
		    element1.id = "chkExpec" + rowCount;
		    element1.name = "chkExpec" + rowCount;	      
		    element1.value = idExpec;//Pendiente idExpectativaLaboral
		    element1.onclick = function(){deleteRow(this, eval(tableID), row.id);};
		    //DIV CLASS SELECCION PARA CHECKBOX
		    var div = document.createElement('div');
		    div.appendChild(element1);
		    div.setAttribute('class', 'seleccion');
		    cell1.appendChild(div);
	   		//Sector deseado
	        var cell2 = row.insertCell(1);
	        var texto2 = document.createTextNode(dijit.byId('idSectorDeseadoSelect').get('displayedValue'));
	        cell2.appendChild(texto2);
	        //Puesto pretendido
	        var cell3 = row.insertCell(2);
	        var texto3 = document.createTextNode(dijit.byId('puestoDeseado').value);
	        cell3.appendChild(texto3);
	        //Area Laboral deseada
	        var cell4 = row.insertCell(3);
	        var texto4 = document.createTextNode(dijit.byId('idAreaLaboralDeseadaSelect').get('displayedValue'));
	        cell4.appendChild(texto4);
	        //Ocupacion deseada
	        var cell5 = row.insertCell(4);
	        var texto5 = document.createTextNode(dijit.byId('idOcupacionDeseadaSelect').get('displayedValue'));
	        cell5.appendChild(texto5);
	        //Salario pretendido
	        var cell6 = row.insertCell(5);
	        var texto6 = document.createTextNode(dijit.byId('salarioPretendido').value);
	        cell6.appendChild(texto6);
	        //Tipo empleo deseado
	        var cell7 = row.insertCell(6);
	        var texto7 = document.createTextNode(dijit.byId('idTipoEmpleoDeseado').get('displayedValue'));
	        cell7.appendChild(texto7);
	        //Tipo contrato
	        var cell8 = row.insertCell(7);
	        var texto8 = document.createTextNode(dijit.byId('idTipoContrato').get('displayedValue'));
	        cell8.appendChild(texto8);
	    } catch(e) {
	          alert(e);
	    }
     }
    
    function validarForm() {
    	if (dijit.byId('expecLaboralForm').isValid()) {
    		return true;
    	} else {
    		alert('Los campos con * son obligatorios');
    		return false;
    	}
    }
</script>
</head>
<body class='claro'>

<div class="TabbedPanelsContent">
	<form name="expecLaboralForm" id="expecLaboralForm" method="post" action="expecLaboral.do" dojoType="dijit.form.Form" >
	<div id="administrar_grado">
	  <h2>Salario y actividad</h2>
      
		<input type="hidden" name="method" id="method" value="init" dojoType="dijit.form.TextBox"/>
		<input type="hidden" name="idExpectativaLaboral" id="idExpectativaLaboral" dojoType="dijit.form.TextBox"/>
        <div dojoType="dojo.data.ItemFileReadStore" jsId="sectorStore" urlPreventCache="true" clearOnClose="true" url="${context}histLaborales.do?method=cargarSector" ></div>
        <p class="entero">
        	<strong><label for="idSectorDeseadoSelect">Sector deseado</label>*</strong><br />
        	<input type="hidden" name="idSectorDeseado" id="idSectorDeseado" dojoType="dijit.form.TextBox" />
        	<select dojotype="dijit.form.FilteringSelect" store="sectorStore" id="idSectorDeseadoSelect" required="true" style="width:60%"></select>
        </p>
        <p class="entero">
        	<strong><label for="puestoDeseado">Puesto pretendido</label>*</strong><br />
        	<input type="text" name="puestoDeseado" id="puestoDeseado" style="width:30em;" maxlength="150" 
        	regExp="^[\w\d\s\.&aacute;&eacute;&iacute;&oacute;&uacute;&Aacute;&Eacute;&Iacute;&Oacute;&Uacute;&Ntilde;&ntilde;]{1,150}$"	            
        	dojoType="dijit.form.ValidationTextBox" required="true" />
        </p>
        <div dojoType="dojo.data.ItemFileReadStore" jsId="areaLaboralStore" urlPreventCache="true" clearOnClose="true" url="${context}histLaborales.do?method=cargarAreaLaboral"></div>
        <p class="entero">
        	<strong><label for="idAreaLaboralDeseadaSelect">&Aacute;rea laboral deseada</label>*</strong><br />
        	<input type="hidden" name="idAreaLaboralDeseada" id="idAreaLaboralDeseada" dojoType="dijit.form.TextBox" />
        	<select dojotype="dijit.form.FilteringSelect" store="areaLaboralStore" id="idAreaLaboralDeseadaSelect" required="true" style="width:60%"></select>
        </p>
        <div dojoType="dojo.data.ItemFileReadStore" jsId="ocupacionStore" urlPreventCache="true" clearOnClose="true"></div>
        <p class="entero">
        	<strong><label for="idOcupacionDeseadaSelect">Ocupaci&oacute;n deseada</label>*</strong><br />
        	<input type="hidden" name="idOcupacionDeseada" id="idOcupacionDeseada" dojoType="dijit.form.TextBox" />
        	<select dojotype="dijit.form.FilteringSelect" store="ocupacionStore" id="idOcupacionDeseadaSelect" required="true" style="width:60%"></select>
        </p>
        <p class="entero">
        	<strong><label for="salarioPretendido">Salario pretendido</lbael>*</strong><br />
        	<input type="text" name="salarioPretendido" id="salarioPretendido" maxlength="9" style="width:10em;" 
        	regExp="^[+]?\d{1,6}(\.\d{1,2})?$" dojoType="dijit.form.ValidationTextBox" required="false" />
        </p>
        <div dojoType="dojo.data.ItemFileReadStore" jsId="tipoEmpleoStore" urlPreventCache="true" clearOnClose="true" url="${context}expecLaboral.do?method=cargarTipoEmpleo"></div>
        <p class="un_medio">
        	<strong><label for="idTipoEmpleoDeseadoSelect">Tipo de empleo</label>*</strong><br />
        	<input type="hidden" name="idTipoEmpleoDeseado" id="idTipoEmpleoDeseado" dojoType="dijit.form.TextBox" />
        	<select dojotype="dijit.form.FilteringSelect" store="tipoEmpleoStore" id="idTipoEmpleoDeseadoSelect" required="true" style="width:60%"></select>
        </p>
        <div dojoType="dojo.data.ItemFileReadStore" jsId="tipoContratoStore" urlPreventCache="true" clearOnClose="true" url="${context}expecLaboral.do?method=cargarTipoContrato"></div>
        <p class="un_medio">
        	<strong><label for="idTipoContratoSelect">Tipo de contrato</label>*</strong><br />
        	<input type="hidden" name="idTipoContrato" id="idTipoContrato" dojoType="dijit.form.TextBox" />
        	<select dojotype="dijit.form.FilteringSelect" store="tipoContratoStore" id="idTipoContratoSelect" required="true" style="width:60%"></select>
        </p>
	</div>
	<div class="entero" style="text-align:center;">
    	<input type="button" class="boton" id="btnAgregarHist" value="Agregar" onclick="doSubmitAdd();" />
        <input type="button" class="boton" id="btnCancelar" value="Cancelar" onclick="self.close();" />
    </div>
	<div id="mis_ofertas">
		<div id="publicados" class="entero" style="text-align:center;">
			<h2 class="mis_ofertas">Expectativas laborales</h2>
			<table id="tblExpecs" width="100%" border="0" cellspacing="0" cellpadding="0">
	        	<tr class="temas">
	        		<th width="8%">Eliminar</th>
	                <th width="10%">Sector deseado</th>
	                <th width="12%">Puesto pretendido</th>
	                <th width="25%">&Aacute;rea laboral deseada</th>
	                <th width="10%">Ocupaci&oacute;n deseada</th>
	                <th width="10%">Salario pretendido</th>
	                <th width="10%">Tipo de empleo</th>
	                <th class="fin" width="10%">Tipo de contrato</th>
	            </tr>
	            <logic:notEmpty name="expecLaboralForm" property="expecLaborales" scope="session">
	            <logic:iterate id="expecLaborales" indexId="index" name="expecLaboralForm" property="expecLaborales" type="mx.gob.stps.portal.core.candidate.vo.ExpectativaLaboralVO">
	            <tr id="trExpec${index + 1}">
	            	<td class="seleccion">
	            		<div class="seleccion">
	            			<input type="checkbox" name="chkExpec" id="chkExpec${index}" value="${expecLaborales.idExpectativaLaboral}" onclick="deleteRow(this, tblExpecs, 'trExpec${index + 1}')" />
	            		</div>
	            	</td>
	            	<td>${expecLaborales.sectorDeseado}</td>
	            	<td>${expecLaborales.puestoDeseado}</td>
	            	<td>${expecLaborales.areaLaboralDeseada}</td>
	            	<td>${expecLaborales.ocupacionDeseada}</td>
	            	<td><%=Utils.formatMoney(expecLaborales.getSalarioPretendido())%></td>
	            	<td>${expecLaborales.tipoEmpleoDeseado}</td>
	            	<td>${expecLaborales.tipoContrato}</td>
	            </tr>
	            </logic:iterate>
	            </logic:notEmpty>
	        </table>
		</div>
	</div>
	<div dojoType="dijit.Dialog" id="MensajeAlerConfirt" title="Mensaje" style="display:none"  style="width:300px;height:150px;" draggable ="false" >
		  <table class="alertDialog">
			 <tr align="center">				 	
				 <td colspan="2"><div id ="mensajeConfir"></div>&nbsp;</td>			 
			 </tr>			 
			 <tr align="center">		
				<td>
					 <input type="button" value = "Aceptar" onclick="deleteRowAux();"/>
				</td>
				<td>
					 <input type="button" value = "Cancelar" onclick="closeDialog()"/>
				</td>		
			 </tr>
		 </table>	
	</div>
	
	</form>
</div>
</body>
</html>