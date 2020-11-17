<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%
	String context = request.getContextPath() +"/";
 %>
 <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<style type="text/css">
	@import "dojotoolkit/dojo/resources/dojo.css";
	@import "dojotoolkit/dijit/themes/claro/claro.css";
</style> 

<script src="dojotoolkit/dojo/dojo.js" djConfig="parseOnLoad: true, locale:'es'"></script>

<link href="${PATH_CSS_SWB_APP}estilos_mi_espacio_can.css" rel="stylesheet" type="text/css" />
<link href="${PATH_CSS_SWB_APP}estilos_tabla_can.css" rel="stylesheet" type="text/css" />
<link href="${PATH_CSS_SWB_APP}estilos_mi_espacio.css" rel="stylesheet" type="text/css" /> 

<link href='https://fonts.googleapis.com/css?family=Droid+Sans' rel='stylesheet' type='text/css' /> 

<script type="text/javascript">
    dojo.require("dijit.dijit"); // loads the optimized dijit layer
    dojo.require('dijit.Dialog'); 
	dojo.require("dijit.form.Form");
    dojo.require("dijit.form.NumberTextBox");
    dojo.require("dijit.form.ValidationTextBox");
    dojo.require("dijit.form.TextBox");
	dojo.require("dojo.data.ItemFileReadStore");
	dojo.require("dijit.form.CheckBox");
	dojo.require("dijit.form.FilteringSelect");
</script>
<script type="text/javascript">

  var formatDate = function (formatDate, formatString) {
	if(formatDate instanceof Date) {
		var months = new Array("Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec");
		var yyyy = formatDate.getFullYear();
		var yy = yyyy.toString().substring(2);
		var m = formatDate.getMonth() + 1;
		var mm = m < 10 ? "0" + m : m;
		var mmm = months[m];
		var d = formatDate.getDate();
		var dd = d < 10 ? "0" + d : d;
		
		var h = formatDate.getHours();
		var hh = h < 10 ? "0" + h : h;
		var n = formatDate.getMinutes();
		var nn = n < 10 ? "0" + n : n;
		var s = formatDate.getSeconds();
		var ss = s < 10 ? "0" + s : s;

		formatString = formatString.replace(/yyyy/i, yyyy);
		formatString = formatString.replace(/yy/i, yy);
		formatString = formatString.replace(/mmm/i, mmm);
		formatString = formatString.replace(/mm/i, mm);
		formatString = formatString.replace(/m/i, m);
		formatString = formatString.replace(/dd/i, dd);
		formatString = formatString.replace(/d/i, d);
		formatString = formatString.replace(/hh/i, hh);
		formatString = formatString.replace(/h/i, h);
		formatString = formatString.replace(/nn/i, nn);
		formatString = formatString.replace(/n/i, n);
		formatString = formatString.replace(/ss/i, ss);
		formatString = formatString.replace(/s/i, s);

		return formatString;
		} else {
			return "";
		}
    };
    
    function anoActual(){
    var fecheActual = new Date();
    return formatDate(fecheActual,'yyyy');    
    }

function confirmAlert(mensaje){
		dojo.byId('mensaje').innerHTML = mensaje;
		dijit.byId('MensajeAlert').show();		
}

function closeDialog(){
        dijit.byId('MensajeAlert').hide();   
        window.location.reload();
}

var depGrado = new Array();
<%  String[] depGrado = session.getAttribute("depGrado") != null ? (String[])session.getAttribute("depGrado") : new String[0]; 
    for (int i = 0; i < depGrado.length; i++) {%>
		<%="depGrado["+i+"] = '"+depGrado[i]+"';"%>
	<%}
%>
	dojo.addOnLoad(function() {
		dojo.connect(dijit.byId("idGradoSelect"), "onChange", function() {
			var vGrado = dijit.byId('idGradoSelect').get('value');
			//alert('GradoSelect:'+vGrado);
			var vIdCatDep = depGrado[vGrado];
			//alert(vGrado+':'+vIdCatDep);
			dojo.byId('idCarreraSelect').value = '';
			
            if(vIdCatDep != 0 || vIdCatDep != undefined) {
            	carreraStore.url = "${context}grados.do?method=cargarCarrera" + "&" + "idCatDep="+ vIdCatDep;
            	carreraStore.close();
			}
        });
	});
	 
	 var chkThis;
	 var tableIDThis;
	 var rowIDThis;
	 
	//eliminar fila (de tabla grados academicos)
    function deleteRow(chk, tableID, rowID) {
    
	chkThis = chk;
	tableIDThis = tableID;
	rowIDThis = rowID;
	    
 	confirmAlert("&iquest;Confirma que desea eliminar el registro?");
    	
    }
    
   function deleteRowAux(){ 
		var fila = document.getElementById(rowIDThis);
		fila.parentNode.removeChild(fila); 				
		doSubmitDelete(chkThis.value);
      setTimeout("window.location.reload();",10);
   }
   
    
    function doSubmitDelete(idGrado) {
   
    	dijit.byId('method').set('value', 'borrarGrado');
    	dijit.byId('idCandidatoGradoAcademico').set('value', idGrado);
    	dojo.xhrPost( {					 					 
			url: 'grados.do',
		  	form:'gradoAcademicoForm',
		  	timeout:180000,
		  	load: function(data){
				var res = dojo.fromJson(data); 			   
		    }
		 });		
    }
    
    function doSubmitAdd() {
    	if(validarGrado()) {
	    	dijit.byId('method').set('value', 'agregarGrado');
	    	dijit.byId('idCandidatoGradoAcademico').set('value', '0');
	    	dijit.byId('idNivelEstudio').set('value', 
	    	dijit.byId('idGradoSelect').get('value'));
	    	dijit.byId('idCarreraEspecialidad').set('value', 
	    	dijit.byId('idCarreraSelect').get('value'));
	    	dijit.byId('idSituacionAcademica').set('value', 
	    	dijit.byId('idEstatusSelect').get('value'));
	    	dojo.xhrPost( {
				url: 'grados.do',
			  	form:'gradoAcademicoForm',
			  	timeout:180000,
			  	load: function(data){
					var res = dojo.fromJson(data);
					if (res.msg.type == 'ext') {
						agregarGrado('tblGrados', res.idCandidatoGradoAcademico);
						dijit.byId('gradoAcademicoForm').reset();
						alert(res.msg.message);
					} else {
						alert(res.msg.message);
						dijit.byId('idCarreraSelect').focus();
					}
			    }
			 });
		 }
    }
    
    var count = ${gradoAcademicoForm.elementos};
    function agregarGrado(tableID, idGrado) {
    	var table = document.getElementById(tableID);
     	var rowCount = table.rows.length;
		 
		try {
			var row = table.insertRow(rowCount);
			count++;
			var trId = 'trGrado' + count;
			row.id = trId;
			//alert('id: '+row.id);
			//CHECKBOX			 	
	      	var cell1 = row.insertCell(0);
		    var element1 = document.createElement("input");
		    element1.type = "checkbox";
		    element1.id = "chkGrado" + rowCount;
		    element1.name = "chkGrado" + rowCount;	      
		    element1.value = idGrado;//Pendiente idCandidatoGradoAcademico
		    element1.onclick = function(){deleteRow(this, eval(tableID), row.id);};
		    //DIV CLASS SELECCION PARA CHECKBOX
		    var div = document.createElement('div');
		    div.appendChild(element1);
		    div.setAttribute('class', 'seleccion');
		    cell1.appendChild(div);
	   		//Grado					
	        var cell2 = row.insertCell(1);
	        var texto2 = document.createTextNode(dijit.byId('idGradoSelect').get('displayedValue'));
	        cell2.appendChild(texto2);
	        //Carrera
	        var cell3 = row.insertCell(2);
	        var texto3 = document.createTextNode(dijit.byId('idCarreraSelect').get('displayedValue'));
	        cell3.appendChild(texto3);
	        //Escuela
	        var cell4 = row.insertCell(3);
	        var texto4 = document.createTextNode(dijit.byId('escuela').value);
	        cell4.appendChild(texto4);
	        //Inicio
	        var cell5 = row.insertCell(4);
	        var texto5 = document.createTextNode(dijit.byId('inicio').value);
	        cell5.appendChild(texto5);
	        //Inicio
	        var cell6 = row.insertCell(5);
	        var texto6 = document.createTextNode(dijit.byId('fin').value);
	        cell6.appendChild(texto6);
	        //Carrera
	        var cell7 = row.insertCell(6);
	        var texto7 = document.createTextNode(dijit.byId('idEstatusSelect').get('displayedValue'));
	        cell7.appendChild(texto7);
	    } catch(e) {
	          alert(e);
	    }
     }
    
    function validarGrado() {
    
    var anual =  anoActual();
    
    	if (dijit.byId('gradoAcademicoForm').isValid()) {
    		
    		var vInicio = dijit.byId('inicio');
			var vFin 	= dijit.byId('fin');
			
		if (vInicio.get('value') < 1900) {
			mensajeAlert('El campo inicio es inv&aacute;lido');
			vInicio.focus();
			return false;
		}
		if (vFin.get('value') < 1900) {
			mensajeAlert('El campo fin es inv&aacute;lido');
			vFin.focus();
			return false;
		}
		if (vInicio.get('value') > vFin.get('value')) {
			mensajeAlert('El rango inicio-fin es inv&aacute;lido');
			vInicio.focus();
			return false;
		}
		if(vInicio.get('value') > anual){
			mensajeAlert('El rango inicio es inv&aacute;lido, no puede ser mayor al a&ntilde;o actual.');
			vInicio.focus();
			return false;
		}
		
		if(vFin.get('value') > anual){
			mensajeAlert('El rango fin es inv&aacute;lido, no puede ser mayor al a&ntilde;o actual.');
			vFin.focus();
			return false;
		}
		
    	return true;
    	} else {
    		mensajeAlert('Los campos con * son obligatorios');
    		return false;
    	}
    }
    
    function mensajeAlert(mensaje){	
    	//dojo.byId('mensajeShow').innerHTML = mensaje;
    	//dijit.byId('MensajeAlertShow').show();
    	alert(mensaje);
    }
</script>
<noscript>Funciones de JavaScript desactivadas por navegador</noscript>
<body class='claro'>
<div class="TabbedPanelsContent">
	<form name="gradoAcademicoForm" id="gradoAcademicoForm" method="post" action="grados.do" dojoType="dijit.form.Form" >
	<div id="administrar_grado">
	 <h2>Estudio</h2>
		<input type="hidden" name="method" id="method" value="init" dojoType="dijit.form.TextBox"/>
		<input type="hidden" name="idCandidatoGradoAcademico" id="idCandidatoGradoAcademico" dojoType="dijit.form.TextBox"/>
		<div dojoType="dojo.data.ItemFileReadStore" jsId="gradoAcademicoStore" 
		urlPreventCache="true" clearOnClose="true" 
        url="${context}grados.do?method=cargarGrados"></div>
        <div class="entero">
	        <p class="entero">
	        	<strong><label for="idGradoSelect">Grado*</label></strong><br />
	        	<input type="hidden" name="idNivelEstudio" id="idNivelEstudio" dojoType="dijit.form.TextBox"/>
	        	<select dojotype="dijit.form.FilteringSelect" store="gradoAcademicoStore" 
	        	id="idGradoSelect" required="true"></select>
	        </p>
	        <div dojoType="dojo.data.ItemFileReadStore" jsId="carreraStore" 
	        urlPreventCache="true" clearOnClose="true" />
	        <p class="entero">
	        	<strong><label for="idCarreraSelect">Carrera o especialidad*</label></strong><br />
	        	<input type="hidden" name="idCarreraEspecialidad" id="idCarreraEspecialidad" dojoType="dijit.form.TextBox" />
	        	<select dojotype="dijit.form.FilteringSelect" store="carreraStore" 
	        	id="idCarreraSelect" required="true"></select>
	        </p>
       
	        <p class="un_tercio">
	        	<strong><label for="escuela">Escuela de procedencia*</label></strong><br/>
	            <input type="text" name="escuela" id="escuela" maxlength="150" style="width:25em;" 
	            dojoType="dijit.form.ValidationTextBox" required="true" invalidMessage="Dato inv&aacute;lido" 
	            missingMessage="Dato requerido" trim="true" 
	            regExp="^[\w\d\s\.&aacute;&eacute;&iacute;&oacute;&uacute;&Aacute;&Eacute;&Iacute;&Oacute;&Uacute;&Ntilde;&ntilde;]{1,150}$"	            
	             />
	        </p>
	      
	      <div class="entero">
	      <p class="un_medio">
	        	<strong><label for="inicio">A&ntilde;o de inicio* </label></strong><br/>
	            <input type="text" name="inicio" id="inicio" maxlength="4" style="width:4em;" dojoType="dijit.form.ValidationTextBox" 
		        required="true" invalidMessage="Dato inv&aacute;lido" regExp="^[+]?\d{4}$" missingMessage="Dato requerido" trim="true" />
	        </p>
	        <p class="un_medio">
	        	<strong><label for="fin">A&ntilde;o de fin*</label></strong><br/>
	            <input type="text" name="fin" id="fin" maxlength="4" style="width:4em;" dojoType="dijit.form.ValidationTextBox" 
		        required="true" invalidMessage="Dato inv&aacute;lido" regExp="^[+]?\d{4}$" missingMessage="Dato requerido" trim="true" />
	        </p>
	        </div>
	        <div dojoType="dojo.data.ItemFileReadStore" jsId="estatusGradoStore" 
			urlPreventCache="true" clearOnClose="true" 
	        url="${context}grados.do?method=cargarEstatus"></div>
	        <p class="un_tercio">
	        	<strong><label for="idEstatusSelect">Situaci&oacute;n acad&eacute;mica*</label></strong><br />
	        	<input type="hidden" name="idSituacionAcademica" id="idSituacionAcademica" dojoType="dijit.form.TextBox"/>
	        	<select dojotype="dijit.form.FilteringSelect" store="estatusGradoStore" 
	        	id="idEstatusSelect" required="true"></select>
	        </p>
         </div>
	</div>
	<div class="entero" style="text-align:center; ">
    	<input type="button" class="boton" id="btnAgregarGrado" value="Agregar" onclick="doSubmitAdd();" />
        <input type="button" class="boton" id="btnCancelar" value="Cancelar" onclick="self.close();" />
    </div>
	<div id="mis_ofertas">
		<div id="publicados">
			<div class="entero" style="text-align:center;"><h2 >Grados acad&eacute;micos</h2></div>
			<table id="tblGrados" width="100%" border="0" cellspacing="0" cellpadding="0">
	        	<tr class="temas">
	        		<th width="5%">Eliminar</th>
	            	<th width="20%">Grado</th>
	                <th width="20%">Carrera o especialidad</th>
	                <th width="20%">Escuela de procedencia</th>
	                <th width="10%">A&ntilde;o de inicio</th>
	                <th width="10%">A&ntilde;o de fin</th>
	                <th width="15%" class="fin">Situaci&oacute;n acad&eacute;mica</th>
	            </tr>
	            <logic:notEmpty name="gradoAcademicoForm" property="grados" scope="session">
	            <logic:iterate id="grados" indexId="index" name="gradoAcademicoForm" property="grados" type="mx.gob.stps.portal.core.candidate.vo.GradoAcademicoVO">
	            <tr id="trGrado${index + 1}">
	            	<td class="seleccion">
	            		<div class="seleccion">
	            			<input type="checkbox" name="chkGrado" id="chkGrado${index}" value="${grados.idCandidatoGradoAcademico}" onclick="deleteRow(this, tblGrados, 'trGrado${index + 1}')" />
	            		</div>
	            	</td>
	            	<td>${grados.nivel}</td>
	            	<td>${grados.carrera}</td>
	            	<td>${grados.escuela}</td>
	            	<td>${grados.inicio}</td>
	            	<td>${grados.fin}</td>
	            	<td>${grados.situacion}</td>
	            </tr>
	            </logic:iterate>
	            </logic:notEmpty>
	        </table>
		</div>
	</div>
	</form>
</div>
<div dojoType="dijit.Dialog" id="MensajeAlert" title="Mensaje" draggable ="false" >
		  <table class="alertDialog">
			 <tr align="center">				 	
				 <td colspan="2"><div id ="mensaje"></div>&nbsp;</td>			 
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

	<div dojoType="dijit.Dialog" id="MensajeAlertShow" title="Mensaje" style="display:none" draggable ="false" >
		  <table class="alertDialog" >
			 <tr align="center">				 	
				 <td><div id ="mensajeShow" style="overflow:auto;vertical-align:middle;"></div>&nbsp;</td>			 
			 </tr>
		 </table>	
	</div>

</body>