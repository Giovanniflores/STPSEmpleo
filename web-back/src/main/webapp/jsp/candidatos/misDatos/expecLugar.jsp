<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="url" value="${pageContext.request.requestURL}" />
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<%String context = request.getContextPath() +"/";%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd"> 
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="es" lang="es"> 
<head> 
<link rel="P3Pv1" href="https://www.empleo.gob.mx/w3c/p3p.xml" /> 
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" /> 
 
<title>Expectativa del lugar | Portal del Empleo</title> 
<meta property="og:title" content="Expectativa del lugar | Portal del Empleo">
<meta name="twitter:title" content="Expectativa del lugar | Portal del Empleo">
<meta property="og:description" content="Portal del Empleo: ${expecLugares.entidadDeseada} - ${expecLugares.municipioDeseado}">
<meta name="twitter:description" content="Portal del Empleo: ${expecLugares.entidadDeseada} - ${expecLugares.municipioDeseado}">
<meta name="description" content="Portal del Empleo: ${expecLugares.entidadDeseada} - ${expecLugares.municipioDeseado}"">
<meta property="og:url" content="${url}">
<meta property="og:site_name" content="Portal del Empleo">
<meta property="og:image" content="http:///qa.empleo.gob.mx/css/images/contenido-compartir.jpg">
<meta property="og:image:type" content="image/jpeg">
<meta property="og:image:width" content="1200">
<meta property="og:image:height" content="627">
<meta property="fb:app_id" content="308925772806125" />
<meta name="twitter:card" content="summary_large_image">
<meta name="twitter:site" content="@empleogob_mx">
<meta name="twitter:creator" content="@InfotecMexico">
<meta name="twitter:image:src" content="http:///qa.empleo.gob.mx/css/images/compartir-contenido-tweetA.jpg">
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
<script type="text/javascript">
    dojo.require("dijit.dijit"); // loads the optimized dijit layer
    dojo.require('dijit.Dialog'); 
    dojo.require("dijit.form.Form");
    dojo.require("dijit.form.TextBox");
	dojo.require("dojo.data.ItemFileReadStore");
	dojo.require("dijit.form.CheckBox");
	dojo.require("dijit.form.FilteringSelect");
	
	dojo.addOnLoad(function() {
		dojo.connect(dijit.byId("idEntidadDeseadaSelect"), "onChange", function() {
			dijit.byId('idMunicipioDeseadoSelect').reset();
			var vEntidad = dijit.byId('idEntidadDeseadaSelect').get('value');
			
            if(vEntidad != 0) {
            	municipioStore.url = "${context}expecLugar.do?method=obtenerMunicipio&idEntidad="+ vEntidad;
            	municipioStore.close();
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
    setTimeout("window.location.reload();",10);
}
//---------------------------------------------------------------------------------------------------------
 
    function doSubmitDelete(idHist) {
    	dijit.byId('method').set('value', 'borrar');
    	dijit.byId('idExpectativaLugar').set('value', idHist);
    	dojo.xhrPost( {					 					 
			url: 'expecLugar.do',
		  	form:'expecLugarForm',
		  	timeout:180000,
		  	load: function(data){
				var res = dojo.fromJson(data);
		    }
		 });
    }
    
    function doSubmitAdd() {
    	if(validarForm()) {
	    	dijit.byId('method').set('value', 'agregar');
	    	dijit.byId('idExpectativaLugar').set('value', '0');
	    	dijit.byId('idEntidadDeseada').set('value', 
	    	dijit.byId('idEntidadDeseadaSelect').get('value'));
	    	dijit.byId('idMunicipioDeseado').set('value', 
	    	dijit.byId('idMunicipioDeseadoSelect').get('value'));
	    	dojo.xhrPost( {
				url: 'expecLugar.do',
			  	form:'expecLugarForm',
			  	timeout:180000,
			  	load: function(data){
					var res = dojo.fromJson(data);
				    if (res.msg.type == 'ext') {
						agregarExpLugar('tblExpecs', res);
						dijit.byId('expecLugarForm').reset();
						alert(res.msg.message);
					} else {
						alert(res.msg.message);
						dijit.byId('idEntidadDeseadaSelect').focus();
					}
			    }
			 });
		 }
    }
    
    var count = ${expecLugarForm.elementos};
    function agregarExpLugar(tableID, idExpec) {
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
		    element1.value = idExpec;//Pendiente idExpectativaLugar
		    element1.onclick = function(){deleteRow(this, eval(tableID), row.id);};
		    //DIV CLASS SELECCION PARA CHECKBOX
		    var div = document.createElement('div');
		    div.appendChild(element1);
		    div.setAttribute('class', 'seleccion');
		    cell1.appendChild(div);
	   		//Sector deseado
	        var cell2 = row.insertCell(1);
	        var texto2 = document.createTextNode(dijit.byId('idEntidadDeseadaSelect').get('displayedValue'));
	        cell2.appendChild(texto2);
	        //Puesto pretendido
	        var cell3 = row.insertCell(2);
	        var texto3 = document.createTextNode(dijit.byId('idMunicipioDeseadoSelect').get('displayedValue'));
	        cell3.appendChild(texto3);	        
	    } catch(e) {
	          alert(e);
	    }
     }
    
    function validarForm() {
    	if (dijit.byId('expecLugarForm').isValid()) {
    		return true;
    	} else {
    		alert('Los campos con * son obligatorios');
    		return false;
    	}
    }
</script>
<noscript>Funciones de JavaScript desactivadas por navegador</noscript>
</head>
<body class='claro'>

<!-- <div style="margin-left:30px; width:500px; ">  -->
<div class="TabbedPanelsContent">
	<form name="expecLugarForm" id="expecLugarForm" method="post" action="expecLugar.do" dojoType="dijit.form.Form" >
	<div id="administrar_grado">
	  <h2>Ubicaci&oacute;n</h2>
      
		<input type="hidden" name="method" id="method" value="init" dojoType="dijit.form.TextBox"/>
		<input type="hidden" name="idExpectativaLugar" id="idExpectativaLugar" dojoType="dijit.form.TextBox"/>
        <div dojoType="dojo.data.ItemFileReadStore" jsId="entidadStore" urlPreventCache="true" clearOnClose="true" url="${context}domicilio.do?method=obtenerEntidad" ></div>
        <span class="un_medio">
        	<strong><label for="idEntidadDeseadaSelect">Entidad</label>*</strong><br />
        	<input type="hidden" name="idEntidadDeseada" id="idEntidadDeseada" dojoType="dijit.form.TextBox" />
        	<select dojotype="dijit.form.FilteringSelect" store="entidadStore" id="idEntidadDeseadaSelect" required="true"></select>
        </span>
        <div dojoType="dojo.data.ItemFileReadStore" jsId="municipioStore" urlPreventCache="true" clearOnClose="true"></div>
        <span class="un_medio">
        	<strong><label for="idMunicipioDeseadoSelect">Municipio</label>*</strong><br />
        	<input type="hidden" name="idMunicipioDeseado" id="idMunicipioDeseado" dojoType="dijit.form.TextBox" />
        	<select dojotype="dijit.form.FilteringSelect" store="municipioStore" id="idMunicipioDeseadoSelect" required="true"></select>
        </span>
	</div>
	<div class="entero" style="text-align:center;" >
		<span class="un_tercio">  
    	<input type="button" class="boton" id="btnAgregarHist" value="Agregar" onclick="doSubmitAdd();" />
    	</span>
    	<span class="un_tercio">  
        <input type="button" class="boton" id="btnCancelar" value="Cancelar" onclick="self.close();" />
        </span>
    </div>
	<div id="mis_ofertas">
		<div id="publicados" class="entero" style="text-align:center;">
			<h2 class="mis_ofertas">Ubicaci&oacute;n deseada</h2>
			<table id="tblExpecs" width="80%" border="0" cellspacing="0" cellpadding="0">
	        	<tr class="temas">
	        		<th width="10%">Eliminar</th>
	                <th width="45%">Entidad</th>
	                <th class="fin" width="45%">Municipio</th>
	            </tr>
	            <logic:notEmpty name="expecLugarForm" property="expecLugares" scope="session">
	            <logic:iterate id="expecLugares" indexId="index" name="expecLugarForm" property="expecLugares" type="mx.gob.stps.portal.core.candidate.vo.ExpectativaLugarVO">
	            <tr id="trExpec${index + 1}">
	            	<td class="seleccion">	            	
	            			<input type="checkbox" name="chkExpec" id="chkExpec${index}" value="${expecLugares.idExpectativaLugar}" onclick="deleteRow(this, tblExpecs, 'trExpec${index + 1}')" />	            	
	            	</td>
	            	<td>${expecLugares.entidadDeseada}</td>
	            	<td>${expecLugares.municipioDeseado}</td>
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

<!--</div> -->
</body>
</html>