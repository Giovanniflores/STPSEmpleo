<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%
	String context = request.getContextPath() +"/";
	String vProxy = context + "SpellCheck.do?method=gogiespell&lang=";
 %>

<script type="text/javascript" src="googiespell/AJS.js"></script>
<script type="text/javascript" src="googiespell/googiespell.js"></script>
<script type="text/javascript" src="googiespell/cookiesupport.js"></script>

<link href="googiespell/googiespell.css" rel="stylesheet" type="text/css"/>

<style type="text/css">
	@import "dojotoolkit/dojo/resources/dojo.css";
	@import "dojotoolkit/dijit/themes/claro/claro.css";
</style> 

<script src="dojotoolkit/dojo/dojo.js" djConfig="parseOnLoad: true, locale:'es'"></script>
 
<link href="${PATH_CSS_SWB_APP}estilos_mi_espacio_can.css" rel="stylesheet" type="text/css" />
<link href="${PATH_CSS_SWB_APP}estilos_tabla_can.css" rel="stylesheet" type="text/css" />
<link href="${PATH_CSS_SWB_APP}estilos_mi_espacio.css" rel="stylesheet" type="text/css" /> 
<link href='https://fonts.googleapis.com/css?family=Droid+Sans' rel='stylesheet' type='text/css' />

<script type="text/javascript" src="googiespell/AJS.js"></script>
<script type="text/javascript" src="googiespell/googiespell.js"></script>
<script type="text/javascript" src="googiespell/cookiesupport.js"></script>

<script type="text/javascript">
    dojo.require("dijit.dijit"); // loads the optimized dijit layer
    dojo.require('dijit.Dialog'); 
    dojo.require("dijit.form.Form");
    dojo.require("dijit.form.Textarea");
    dojo.require("dijit.form.ValidationTextBox");
	dojo.require("dojo.data.ItemFileReadStore");
	dojo.require("dijit.form.CheckBox");
	dojo.require("dijit.form.FilteringSelect");
</script>
<noscript>Funciones de JavaScript desactivadas por navegador</noscript>
<script type="text/javascript">
//---------------------------------------------------------------------------------------------------------	
	
var chkThis;
var tableIDThis;
var rowIDThis;
	 
dojo.addOnLoad(function() {

        dojo.connect(dijit.byId('idExperienciaSelect'), "onChange", function() {
        	var vcatalogo = dijit.byId('idExperienciaSelect').get('value');        	
			if (vcatalogo==1){ // Ninguna expediencia no se cuenta con dominio
				
				dijit.byId('idDominioSelect').attr('required', false);
				dijit.byId('idDominioSelect').attr('value', 0);
				dijit.byId('idDominioSelect').setDisplayedValue('');
				setEnableWg('idDominioSelect', false);

				document.getElementById('lblDominio').innerHTML = 'Dominio';
			}else{
				setEnableWg('idDominioSelect', true);
				dijit.byId('idDominioSelect').attr('required', true);
				document.getElementById('lblDominio').innerHTML = 'Dominio*';
			}
        });

});

function setEnableWg(id, habilita) {
	if (habilita) { //Habilita Widget
		dijit.byId(id).attr('disabled', '');
		dojo.removeAttr(id, 'disabled');
	} else { //Deshabilita Widget
		dijit.byId(id).attr('disabled', 'disabled');
	}
}

function confirmAlert(mensaje){
		dojo.byId('mensajeConfir').innerHTML = mensaje;
		dijit.byId('MensajeAlerConfirt').show();		
}
function closeDialog(){
        dijit.byId('MensajeAlerConfirt').hide();
        setTimeout("window.location.reload();",10);
} 	 
	
 
function deleteRow(chk,  rowID) {
   
	chkThis 	= chk;
	//tableIDThis = tableID;
	rowIDThis 	= rowID;
    
	confirmAlert("&iquest;Confirma que desea eliminar el registro?");
   	
}
    
function deleteRowAux(){ 
	var fila = document.getElementById(rowIDThis);
	fila.parentNode.removeChild(fila); 				
	doSubmitDelete(chkThis.value);
     	setTimeout("window.location.reload();",10);
}
//
    
    function doSubmitDelete(idCompAva) {
    	dijit.byId('method').set('value', 'borrarCompuAvanzada');
    	dijit.byId('idCandidatoCompuAvanzada').set('value', idCompAva);
    	dojo.xhrPost( {					 					 
			url: 'compuAvanzadas.do',
		  	form:'compuAvanzadaForm',
		  	timeout:180000,
		  	load: function(data){
				var res = dojo.fromJson(data); 
		    }
		 });
    }
    
    function doSubmitAdd() {    	
    	if(validarForm()) {
	    	dijit.byId('method').set('value', 'agregarCompuAvanzada');
	    	dijit.byId('idCandidatoCompuAvanzada').set('value', '0');
	    	dijit.byId('descripcion').set('value',  document.getElementById('descr').value);	    
	    	dijit.byId('idDominio').set('value', 
	    	dijit.byId('idDominioSelect').get('value'));
	    	dijit.byId('idExperiencia').set('value', 
	    	dijit.byId('idExperienciaSelect').get('value'));
	    	dojo.xhrPost( {
				url: 'compuAvanzadas.do',
			  	form:'compuAvanzadaForm',
			  	timeout:180000,
			  	load: function(data){
					var res = dojo.fromJson(data);
					if (res.msg.type == 'ext') {
						agregarCmp('tblcompuAvanzadas', res.idCandidatoCompuAvanzada);
						dijit.byId('compuAvanzadaForm').reset();
						alert(res.msg.message);
					} else {
						alert(res.msg.message);
						dijit.byId('softwareHardware').focus();
					}
			    }
			 });	    	
	    	setTimeout("window.location.reload();",1000);
		 }
    }
    
    var count = ${compuAvanzadaForm.elementos};
    function agregarCmp(tableID, idCompAva) {
    	var table = document.getElementById(tableID);
     	var rowCount = table.rows.length;
		 
		try {
			var row = table.insertRow(rowCount);
			count++;
			var trId = 'trCompAva' + count;
			row.id = trId;
			//alert('id: '+row.id);
			//CHECKBOX			 	
	      	var cell1 = row.insertCell(0);
		    var element1 = document.createElement("input");
		    element1.type = "checkbox";
		    element1.id = "chkCompAva" + rowCount;
		    element1.name = "chkCompAva" + rowCount;	      
		    element1.value = idCompAva;
		    element1.onclick = function(){deleteRow(this, eval(tableID), row.id);};
		    //DIV CLASS SELECCION PARA CHECKBOX
		    var div = document.createElement('div');
		    div.appendChild(element1);
		    div.setAttribute('class', 'seleccion');
		    cell1.appendChild(div);
	   		//Conocimiento o habilidad
	        var cell2 = row.insertCell(1);
	        var texto2 = document.createTextNode(dijit.byId('softwareHardware').get('value'));
	        cell2.appendChild(texto2);
	        //Experiencia
	        var cell3 = row.insertCell(2);
	        var texto3 = document.createTextNode(dijit.byId('idExperienciaSelect').get('displayedValue'));
	        cell3.appendChild(texto3);
	        //Dominio
	        var cell4 = row.insertCell(3);
	        var texto4 = document.createTextNode(dijit.byId('idDominioSelect').get('displayedValue'));
	        cell4.appendChild(texto4);
	        //Dominio
	        var cell5 = row.insertCell(4);
	        var texto5 = document.createTextNode(dijit.byId('descripcion').get('displayedValue'));
	        cell5.appendChild(texto5);
	    } catch(e) {
	          alert(e);
	    }
     }
    
    function validarForm() {
    	if (dijit.byId('compuAvanzadaForm').isValid()) {
    		if (!validarTextArea('descripcion'))
    			return false;
    		return true;
    	} else {
    		alert('Los campos con * son obligatorios');
    		return false;
    	}
    }
    
    function validarTextArea(id) {
	   	var txt = dijit.byId(id);
		var regExp = /^[\w\d\s\.áéíóúÁÉÍÓÚñÑ]{1,2000}$/;
		if (!regExp.test(txt.get('value')) && txt.get('value') != '') {
			alert('Descripcion contiene caracteres inválidos');
			txt.focus();
			return false;
		}
		return true;
	}
    
  	function maximaLongitud(texto,maxlong)
  	{
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
</script>
<div class="TabbedPanelsContent">
	<form name="compuAvanzadaForm" id="compuAvanzadaForm" method="post" action="compuAvanzadas.do" dojoType="dijit.form.Form" >
	<div id="administrar_CompAva">
		<input type="hidden" name="method" id="method" value="init" dojoType="dijit.form.TextBox"/>
		<input type="hidden" name="idCandidatoCompuAvanzada" id="idCandidatoCompuAvanzada" dojoType="dijit.form.TextBox"/>
        <p class="entero">
        	<strong><label for="softwareHardware">Software y/o hardware</label>*</strong><br />
        	<input type="text" name="softwareHardware" id="softwareHardware" style="width:25em;" maxlength="50" 
        	regExp="^[\w\d\s\.áéíóúÁÉÍÓÚñÑ]{1,50}$" dojoType="dijit.form.ValidationTextBox" require="true"/>
        </p>
        <div dojoType="dojo.data.ItemFileReadStore" jsId="experienciaStore" urlPreventCache="true" clearOnClose="true" url="${context}conocsHabs.do?method=cargarExperiencia"/>
        <p class="un_medio">
        	<strong><label for="idExperienciaSelect">Experiencia</label>*</strong><br />
        	<input type="hidden" name="idExperiencia" id="idExperiencia" dojoType="dijit.form.TextBox" />
        	<select dojotype="dijit.form.FilteringSelect" store="experienciaStore" id="idExperienciaSelect" required="true"></select>
        </p>
        <div dojoType="dojo.data.ItemFileReadStore" jsId="dominioStore" urlPreventCache="true" clearOnClose="true" url="${context}idiomas.do?method=cargarDominio"></div>
        <p class="un_medio">
        	<strong id="lblDominio"><label for="idDominioSelect">Dominio</label>*</strong><br />
        	<input type="hidden" name="idDominio" id="idDominio" dojoType="dijit.form.TextBox"/>
        	<select dojotype="dijit.form.FilteringSelect" store="dominioStore" id="idDominioSelect" style="width:8em;" required="true"></select>
        </p>
        <p class="entero">
        	<strong><label for="descr">Descripci&oacute;n</label></strong><br />
        	<input type="hidden" name="descripcion" id="descripcion" dojoType="dijit.form.TextBox"/>
        	<textarea name="descr" id="descr" maxlength="2000" style="width:550px;min-height:150px;_height:200px;"         		
        	 require="false" onchange="validarTextArea(this.id);"></textarea>
        	 <script type="text/javascript">
	         		var vSpell = new GoogieSpell("googiespell/",'<%=vProxy%>'); 
   					vSpell.setLanguages({'es': 'Español'});
   					vSpell.hideLangWindow();
  					vSpell.decorateTextarea("descr");
				</script>
        	
        </p>
	</div>
	<div class="entero" style="text-align:center;">
    	<input type="button" class="boton" id="btnAgregarCompAva" value="Agregar" onclick="doSubmitAdd();" />
        <input type="button" class="boton" id="btnCancelar" value="Cancelar" onclick="self.close();" />
    </div>
	<div id="mis_ofertas">
		<div id="publicados">			
			</br>			
			<table id="tblcompuAvanzadas" width="100%" border="0" cellspacing="0" cellpadding="0">
	        	<tr class="temas">
	        		<th width="8%">Eliminar</th>
	            	<th width="17%">Software y/o hardware</th>
	                <th width="15%">Experiencia</th>
	                <th width="10%">Dominio</th>
	                <th width="50%">Descripci&oacute;n</th>
	            </tr>
	            <logic:notEmpty name="compuAvanzadaForm" property="avanzadas" scope="session">
	            <logic:iterate id="compuAvanzadas" indexId="index" name="compuAvanzadaForm" property="avanzadas" type="mx.gob.stps.portal.core.candidate.vo.ComputacionAvanzadaVO">
	            <tr id="trCompAva${index + 1}">
	            	<td>
	            		<div class="seleccion">
	            			<input type="checkbox" name="chkCompAva" id="chkCompAva${index}" value="${compuAvanzadas.idCandidatoCompuAvanzada}" onclick="deleteRow(this,'trCompAva${index + 1}')" />
	            		</div>
	            	</td>
	            	<td>${compuAvanzadas.softwareHardware}</td>
	            	<td>${compuAvanzadas.experiencia}</td>
	            	<td>${compuAvanzadas.dominio}</td>
	            	<td>${compuAvanzadas.descripcion}</td>
	            </tr>
	            </logic:iterate>
	            </logic:notEmpty>
	        </table>
		</div>
	</div>
	</form>
	<%--
		<div dojoType="dijit.Dialog" id="MensajeAlert" title="Mensaje" style="display:none" draggable ="false" >
	  <table class="alertDialog" >
		 <tr align="center">
			 <td><div id ="mensaje"></div>&nbsp;</td>				 
		 </tr>
	 </table>	
	</div>
	--%>
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
</div>