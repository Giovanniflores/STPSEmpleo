<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="mx.gob.stps.portal.persistencia.vo.CatalogoOpcionVO"%>
<%@ page import="mx.gob.stps.portal.persistencia.vo.TelefonoVO"%>
<%@ page import="mx.gob.stps.portal.core.infra.utils.Constantes"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd"> 
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="es" lang="es"> 
<head> 
<title>Portal del Empleo</title> 

<style type="text/css">
	@import "dojotoolkit/dojo/resources/dojo.css";
	@import "dojotoolkit/dijit/themes/claro/claro.css";
</style> 
<style type="text/css">
	.redFont{
		color: #FF0000;
	}
	.Font80{
		font-size: 80%;
	}
</style>
<script src="dojotoolkit/dojo/dojo.js" djConfig="parseOnLoad: true, locale:'es'"></script>

<link href='https://fonts.googleapis.com/css?family=Droid+Sans' rel='stylesheet' type='text/css' /> 
 

<link href="${PATH_CSS_SWB}css_aplicacion/estilos_tabla.css" rel="stylesheet" type="text/css" />
<link href="${PATH_CSS_SWB_APP}estilos_tabla_can.css" rel="stylesheet" type="text/css" />  
<link href="${PATH_CSS_SWB_APP}estilos_mi_espacio.css" rel="stylesheet" type="text/css" /> 


<script type="text/javascript">
    dojo.require("dijit.dijit"); // loads the optimized dijit layer
	dojo.require("dijit.form.Form");    
    dojo.require("dijit._Calendar");
    dojo.require("dijit.form.Textarea");
    dojo.require("dijit.form.DateTextBox");    
    dojo.require("dijit.form.NumberTextBox");
    dojo.require("dijit.form.ValidationTextBox");
    dojo.require("dijit.form.TextBox");
	dojo.require("dijit.form.ComboBox");
	dojo.require("dojo.data.ItemFileReadStore");
	dojo.require("dijit.form.CheckBox");    
</script>
<noscript>Funciones de JavaScript desactivadas por navegador</noscript>
<%
  String context = request.getContextPath() +"/";  
  String TELEFONO_CELULAR_ID = request.getSession().getAttribute("TELEFONO_CELULAR_ID").toString();
  String TELEFONO_CELULAR_DES = request.getSession().getAttribute("TELEFONO_CELULAR_DES").toString();
  String TELEFONO_FIJO_ID = request.getSession().getAttribute("TELEFONO_FIJO_ID").toString();
  String TELEFONO_FIJO_DES = request.getSession().getAttribute("TELEFONO_FIJO_DES").toString();
  String CLAVE_TELEFONO_FIJO = request.getSession().getAttribute("CLAVE_TELEFONO_FIJO").toString();
  String CLAVE_TELEFONO_CELULAR = request.getSession().getAttribute("CLAVE_TELEFONO_CELULAR").toString(); 
  
  List<TelefonoVO> lstTelefonosAdicionales = (List<TelefonoVO>) request.getSession().getAttribute("LST_TELEFONOS_ADICIONALES");	
%>
<script type="text/javascript">
	function doSubmit(method){
		document.getElementById('method').value=method;
		document.phone.submit();		
	}
	
	function doSubmitAjax(method){
		if (dijit.byId('PhoneForm').isValid()){
		    document.getElementById('method').value=method;
			//dojo.byId('method').value=method;
			//dojo.byId('btnEnviar').disabled=true;
			//dojo.byId('btnAgregarTelefono').disabled=true;
						
			dojo.xhrPost(
					 {					 					 
					  url: 'phone.do',
					  form:'PhoneForm',
					  timeout:180000, 
					  load: function(data){
						    var res = dojo.fromJson(data); 
						    dojo.byId('message').innerHTML=res.message;
						    
							if (res.type!='err'){
						    	dojo.byId('message').style.color='#000066';
						    	//dojo.byId('divRegis').style.display='none';
						    }else{
						    	dojo.byId('message').style.color='#FF0000';
						    	//dojo.byId('divRegis').style.display='block';
						    }
					    }
					 } );		
		} else {
			dojo.byId('message').innerHTML = 'Los siguientes campos muestran datos inválidos - Se muestra(n) el (los) campo(s) con inconsistencias.';
		}			
	}	



	//funcion para evitar que avance de un campo si el contenido no es valido
	function verifyStop(id){
		var widget = dijit.byId(id);
		if(!widget.isValid()){
			widget.focus();
		}	
	}
	

//funcion para obtener el valor de un radiobutton seleccionado
function getRadioValue(idOrName) {
	var value = null;
	var element = document.getElementById(idOrName);
	var radioGroupName = null;  
	
	if (element == null) {
		radioGroupName = idOrName;
	} else {
		radioGroupName = element.name;     
	}
	if (radioGroupName == null) {
		return null;
	}
	var radios = document.getElementsByTagName('input');
	for (var i=0; i<radios.length; i++) {
		var input = radios[ i ];    
		if (input.type == 'radio' && input.name == radioGroupName && input.checked) {                          
			value = input.value;
			break;
		}
	}
	return value;
}


	//TELEFONOS
	//Dependiendo de la seleccion de tipo de telefono, coloca la clave de acceso correspondiente
	function fillUpAccessKey(){
		var tipoTelefonoId = getRadioValue('idTipoTelefono');
		var accesoDes;
        if(tipoTelefonoId==<%=TELEFONO_CELULAR_ID%>){ 
        	accesoDes = '<%=CLAVE_TELEFONO_CELULAR%>';
        } else {
        	accesoDes = '<%=CLAVE_TELEFONO_FIJO%>';
        }      		
        var wAcceso = dijit.byId('acceso');
        wAcceso.attr('disabled', false);
        wAcceso.attr('value',accesoDes);
        wAcceso.attr('disabled', true);
        var vAcceso = document.getElementById('acceso');
        vAcceso.value=accesoDes; 
	}
	
	//Limpiar controles
	function clearPhoneControls(){
        var wAcceso = dijit.byId('acceso');
        wAcceso.attr('value','');
        wAcceso.attr('disabled', false);
		var wClave = dijit.byId('clave');
		wClave.attr('value','');
		var wTelefono = document.getElementById('telefono');
		wTelefono.value='';
		var wExtension = document.getElementById('extension');
		wExtension.value='';		 
		 fillUpAccessKey();
	}	
	
	//funcion para validar que los tamaños de la clave LADA y el telefono correspondan
	// Retornando errores
	function changePhoneSizeRequired(){
		var vClave = document.getElementById('clave');
		var vTelefono = document.getElementById('telefono');
		var tipoTelefonoId 	= getRadioValue('idTipoTelefono');
	 
		if(vClave.value.length<2){
			return 'Debe proporcionar una Clave Lada de 2 ó 3 caracteres';
		} else if(vClave.value.length==2) {
			if(vTelefono.value.length!=8){
				return 'Debe proporcionar un teléfono de 8 dígitos';
			}
		} else if(vClave.value.length==3){
			if(vTelefono.value.length!=7){
				return'Debe proporcionar un teléfono de 7 dígitos';
			}	
		}
		return ''; //Sin errores
	}		
	
	
function isNumber(n) {
  return !isNaN(parseFloat(n)) && isFinite(n);
}	
	
	//funcion para validar un registro de numero telefonico
	 function validatePhoneNumber(){
		
		var CELULAR 	= 1;
		var FIJO	 	= 2;
		
	 	 var validPhone = true;
	 	 var phoneErrorMsg = '';
	 	 
		 var tipoTelefonoId = getRadioValue('idTipoTelefono');
		 var vClave = document.getElementById('clave').value;
         var vTelefono = dijit.byId('telefono').attr('value');
         var vExtension = dijit.byId('extension').attr('value');      
         
         if(!tipoTelefonoId){
         	phoneErrorMsg = 'Debe seleccionar el tipo de telefono.';
         } 
         
         if(!vTelefono){
         	phoneErrorMsg = phoneErrorMsg + '\nDebe proporcionar el número telefonico.';
         } else{
	         if(!isNumber(vTelefono)){
	         	phoneErrorMsg = phoneErrorMsg + '\nEl número telefonico debe ser un valor numérico (0-9).';
	         }         
         }         
         
         if(!isNumber(vClave)){
         	phoneErrorMsg = phoneErrorMsg + '\n La clave LADA debe ser un valor numérico (0-9).';
         } 
         
         phoneErrorMsg = phoneErrorMsg + changePhoneSizeRequired();
         
         if(vExtension && !isNumber(vExtension)){
         	phoneErrorMsg = phoneErrorMsg + '\nLa extensión debe ser un valor numérico (0-9).';
         }          

         if(phoneErrorMsg.length>0){
         	validPhone= false;
         	alert(phoneErrorMsg);
         }
         return validPhone;
	 }	
	
	//Cargar en la tabla los telefonos adicionales capturados anteriormente
	function loadTable(tableID){
		var tRows = <%=lstTelefonosAdicionales.size()%>;
		//alert('tRows:' + tRows);
		if(tRows){
			 var table = document.getElementById(tableID);			   
			 var hiddenRowCount = document.getElementById("dataTableRows"); 
			 var vHiddenRowCount = parseInt(tRows);	     	 
			 hiddenRowCount.value = vHiddenRowCount;
			 <%
			 Iterator itLstTelefonosAdicionales = lstTelefonosAdicionales.iterator();
			 while(itLstTelefonosAdicionales.hasNext()){
				 TelefonoVO telVo = (TelefonoVO)itLstTelefonosAdicionales.next();				 
				 if(telVo.getPrincipal()==Constantes.MULTIREGISTRO.ADICIONAL.getIdOpcion()){
					%>
					 var rowCount = table.rows.length;	 
			         var tipoTelefonoId = <%=telVo.getIdTipoTelefono()%>;
			         var tipoTelefonoDes;
			         var accesoDes;		         
			         var vClave = '<%=telVo.getClave()%>';
			         var vTelefono = '<%=telVo.getTelefono()%>';
			         var vExtension = '<%="null".equals(telVo.getExtension()) ? "" : telVo.getExtension()%>';			         
			         if(tipoTelefonoId==<%=TELEFONO_CELULAR_ID%>){ 
			        	 accesoDes = '<%=CLAVE_TELEFONO_CELULAR%>';
			        	 tipoTelefonoDes = '<%=TELEFONO_CELULAR_DES%>';     	
			         } else {
			        	 accesoDes = '<%=CLAVE_TELEFONO_FIJO%>';
			        	 tipoTelefonoDes = '<%=TELEFONO_FIJO_DES%>';	        	
			         }     		
			         
			         try{
					 	var row = table.insertRow(rowCount);
					 	//CHECKBOX			 	
				        var cell1 = row.insertCell(0);
				        var element1 = document.createElement("input");
				        element1.type = "checkbox";
				        element1.id = "dataTableCell0Row" + rowCount;
				        element1.name = "dataTableCell0Row" + rowCount;
				        element1.value = 1;  	      
				        element1.onclick = function(){deleteRow('dataTable');};  				        
				        cell1.appendChild(element1);	
				        
					    //HIDDEN idTipoTelefono    					
				        var cell2 = row.insertCell(1);
				        var element2 = document.createElement("input");
				        element2.type = "hidden";
				        element2.id = "dataTableCell1Row" + rowCount;
				        element2.name = "dataTableCell1Row" + rowCount;
				        element2.value = tipoTelefonoId;
				        //element2.value = dijit.byId('idTipoTelefonoSelect').get('item').label;	         
				        cell2.appendChild(element2);
				
						 //DESCRIPCION idTipoTelefono DESHABILITADO
				         var element3 = document.createElement("input");
				         element3.type = "text";
				         element3.size = 10;
				         element3.id = "dataTableCell2Row" + rowCount;
				         element3.name = "dataTableCell2Row" + rowCount;
				         element3.value = tipoTelefonoDes;
				         //element3.value = dijit.byId('idTipoTelefonoSelect').attr('value');
				         element3.disabled="disabled";
				         cell2.appendChild(element3);
				
						 //HIDDEN ACCESO 
				         var cell3 = row.insertCell(2);
				         var element4 = document.createElement("input");
				         element4.type = "hidden";
				         element4.size = 10;
				         element4.id = "dataTableCell3Row" + rowCount;
				         element4.name = "dataTableCell3Row" + rowCount;
				         element4.value = accesoDes;
				         //element4.value = dijit.byId('acceso').attr('value');
				         cell3.appendChild(element4);
				         
						 //DESCRIPCION ACCESO DESHABILITADO
				         var element5 = document.createElement("input");
				         element5.type = "text";
				         element5.size = 10;
				         element5.id = "dataTableCell4Row" + rowCount;
				         element5.name = "dataTableCell4Row" + rowCount;  		
				         element5.value = accesoDes;
				         //element5.value = dijit.byId('acceso').attr('value');
				         element5.disabled="disabled";
				         cell3.appendChild(element5);		         
				         
				         //HIDDEN CLAVE
				         var cell4 = row.insertCell(3);
				         var element6 = document.createElement("input");
				         element6.type = "hidden";
				         element6.id = "dataTableCell5Row" + rowCount;
				         element6.name = "dataTableCell5Row" + rowCount;
				         element6.value = vClave;
				         //element6.value = dijit.byId('claveSelect').get('item').label;
				         cell4.appendChild(element6);
				
						 //DESCRIPCION CLAVE DESHABILITADO
				         var element7 = document.createElement("input");
				         element7.type = "text";
				         element7.size = 10;
				         element7.id = "dataTableCell6Row" + rowCount;
				         element7.name = "dataTableCell6Row" + rowCount;
				         element7.value = vClave;
				         //element7.value = dijit.byId('claveSelect').attr('value');
				         element7.disabled="disabled";
				         cell4.appendChild(element7);
				         
				         //HIDDEN TELEFONO
				         var cell5 = row.insertCell(4);
				         var element8 = document.createElement("input");
				         element8.type = "hidden";
				         element8.size = 10;
				         element8.id = "dataTableCell7Row" + rowCount;
				         element8.name = "dataTableCell7Row" + rowCount;
				         element8.value = vTelefono;
				         cell5.appendChild(element8);
				         
				         //DESCRIPCION TELEFONO DESHABILITADO		         
				         var element9 = document.createElement("input");
				         element9.type = "text";
				         element9.size = 10;
				         element9.id = "dataTableCell8Row" + rowCount;
				         element9.name = "dataTableCell8Row" + rowCount;
				         element9.value = vTelefono;
				         element9.disabled="disabled";
				         cell5.appendChild(element9);
				         
				         //HIDDEN EXTENSION
				         var cell6 = row.insertCell(5);
				         var element10 = document.createElement("input");
				         element10.type = "hidden";
				         element10.size = 10;
				         element10.id = "dataTableCell9Row" + rowCount;
				         element10.name = "dataTableCell9Row" + rowCount;
				         element10.value = vExtension;
				         cell6.appendChild(element10);
				         
				         //DESCRIPCION EXTENSION DESHABILITADO
				         var element11 = document.createElement("input");
				         element11.type = "text";
				         element11.size = 10;
				         element11.id = "dataTableCell10Row" + rowCount;
				         element11.name = "dataTableCell10Row" + rowCount;
				         element11.value = vExtension;
				         element11.disabled="disabled";
				         cell6.appendChild(element11);		         
				        			         
				         <%if(telVo.getIdTelefono()>0){%>	
				         	var vIdTelefono = '<%=telVo.getIdTelefono()%>';
				         	var element12 = document.createElement("hidden");
				         	element12.type = "text";
				         	element12.size = 10;
				         	element12.id = "dataTableCell11Row" + rowCount;
				         	element12.name = "dataTableCell11Row" + rowCount;
				         	element12.value = vIdTelefono;				        
				         	cell6.appendChild(element12);		         				         		         
				         <%}%>			         
			         }catch(e) {
			             //alert(e);
			         }							
			         <%	 
				}
			 }			 
			 %>				
		}			
	}	
	
	//agregar fila a la tabla (de telefonos)
	function addRow(tableID) {
    	if(validatePhoneNumber()){
	         var table = document.getElementById(tableID);
	     	 var rowCount = table.rows.length;	   
	     	 var hiddenRowCount = document.getElementById("dataTableRows"); 
	     	 var vHiddenRowCount = parseInt(hiddenRowCount.value) + 1;	     	 
	     	 hiddenRowCount.value = vHiddenRowCount;
	     	 //var rowCount = vHiddenRowCount;
	         var tipoTelefonoId = getRadioValue('idTipoTelefono');
	         var tipoTelefonoDes;
	         var accesoDes;		         
	         var vClave = document.getElementById('clave');
	         var vTelefono = dijit.byId('telefono').attr('value');
	         var vExtension = dijit.byId('extension').attr('value');
			 
			 try{
			 	var row = table.insertRow(rowCount);
			 	//CHECKBOX			 	
		        var cell1 = row.insertCell(0);
		        var element1 = document.createElement("input");
		        element1.type = "checkbox";
		        element1.id = "dataTableCell0Row" + vHiddenRowCount;
		        element1.name = "dataTableCell0Row" + vHiddenRowCount;	      
		        element1.value = 1;  	      
		        element1.onclick = function(){deleteRow('dataTable');};  
		        cell1.appendChild(element1);	
		        		 	
		        if(tipoTelefonoId==<%=TELEFONO_CELULAR_ID%>){ 
		        	accesoDes = '<%=CLAVE_TELEFONO_CELULAR%>';
		        	tipoTelefonoDes = '<%=TELEFONO_CELULAR_DES%>';     	
		        } else {
		        	accesoDes = '<%=CLAVE_TELEFONO_FIJO%>';
		        	tipoTelefonoDes = '<%=TELEFONO_FIJO_DES%>';	        	
		        }     
			        					
			    //HIDDEN idTipoTelefono    					
		        var cell2 = row.insertCell(1);
		        var element2 = document.createElement("input");
		        element2.type = "hidden";
		        element2.id = "dataTableCell1Row" + vHiddenRowCount;
		        element2.name = "dataTableCell1Row" + vHiddenRowCount;
		        element2.value = tipoTelefonoId;
		        //element2.value = dijit.byId('idTipoTelefonoSelect').get('item').label;	         
		        cell2.appendChild(element2);
		
				 //DESCRIPCION idTipoTelefono DESHABILITADO
		         var element3 = document.createElement("input");
		         element3.type = "text";
		         element3.size = 10;
		         element3.id = "dataTableCell2Row" + vHiddenRowCount;
		         element3.name = "dataTableCell2Row" + vHiddenRowCount;
		         element3.value = tipoTelefonoDes;
		         //element3.value = dijit.byId('idTipoTelefonoSelect').attr('value');
		         element3.disabled="disabled";
		         cell2.appendChild(element3);
		
				 //HIDDEN ACCESO 
		         var cell3 = row.insertCell(2);
		         var element4 = document.createElement("input");
		         element4.type = "hidden";
		         element4.size = 10;
		         element4.id = "dataTableCell3Row" + vHiddenRowCount;
		         element4.name = "dataTableCell3Row" + vHiddenRowCount;
		         element4.value = accesoDes;
		         //element4.value = dijit.byId('acceso').attr('value');
		         cell3.appendChild(element4);
		         
				 //DESCRIPCION ACCESO DESHABILITADO
		         var element5 = document.createElement("input");
		         element5.type = "text";
		         element5.size = 10;
		         element5.id = "dataTableCell4Row" + vHiddenRowCount;
		         element5.name = "dataTableCell4Row" + vHiddenRowCount;  		
		         element5.value = accesoDes;
		         //element5.value = dijit.byId('acceso').attr('value');
		         element5.disabled="disabled";	         
		         cell3.appendChild(element5);
		         
		         //HIDDEN CLAVE
		         var cell4 = row.insertCell(3);
		         var element6 = document.createElement("input");
		         element6.type = "hidden";
		         element6.id = "dataTableCell5Row" + vHiddenRowCount;
		         element6.name = "dataTableCell5Row" + vHiddenRowCount;
		         element6.value = vClave.value;
		         //element6.value = dijit.byId('claveSelect').get('item').label;
		         cell4.appendChild(element6);
		
				 //DESCRIPCION CLAVE DESHABILITADO
		         var element7 = document.createElement("input");
		         element7.type = "text";
		         element7.size = 10;
		         element7.id = "dataTableCell6Row" + vHiddenRowCount;
		         element7.name = "dataTableCell6Row" + vHiddenRowCount;
		         element7.value = vClave.value;
		         //element7.value = dijit.byId('claveSelect').attr('value');
		         element7.disabled="disabled";
		         cell4.appendChild(element7);
		         
		         //HIDDEN TELEFONO
		         var cell5 = row.insertCell(4);
		         var element8 = document.createElement("input");
		         element8.type = "hidden";
		         element8.size = 10;
		         element8.id = "dataTableCell7Row" + vHiddenRowCount;
		         element8.name = "dataTableCell7Row" + vHiddenRowCount;
		         element8.value = vTelefono;
		         cell5.appendChild(element8);
		         
		         //DESCRIPCION TELEFONO DESHABILITADO		         
		         var element9 = document.createElement("input");
		         element9.type = "text";
		         element9.size = 10;
		         element9.id = "dataTableCell8Row" + vHiddenRowCount;
		         element9.name = "dataTableCell8Row" + vHiddenRowCount;
		         element9.value = vTelefono;
		         element9.disabled="disabled";
		         cell5.appendChild(element9);
		         
		         //HIDDEN EXTENSION
		         var cell6 = row.insertCell(5);
		         var element10 = document.createElement("input");
		         element10.type = "hidden";
		         element10.size = 10;
		         element10.id = "dataTableCell9Row" + vHiddenRowCount;
		         element10.name = "dataTableCell9Row" + vHiddenRowCount;
		         element10.value = vExtension;
		         cell6.appendChild(element10);
		         
		         //DESCRIPCION EXTENSION DESHABILITADO
		         var element11 = document.createElement("input");
		         element11.type = "text";
		         element11.size = 10;
		         element11.id = "dataTableCell10Row" + vHiddenRowCount;
		         element11.name = "dataTableCell10Row" + vHiddenRowCount;
		         element11.value = vExtension;
		         element11.disabled="disabled";	         
		         cell6.appendChild(element11);
			 
	         	var element12 = document.createElement("hidden");
	         	element12.type = "text";
	         	element12.size = 10;
	         	element12.id = "dataTableCell11Row" + rowCount;
	         	element12.name = "dataTableCell11Row" + rowCount;
	         	element12.value = 0;				        
	         	cell6.appendChild(element12);		         				         		         
			 			 
	         }catch(e) {
	             //alert(e);
	         }			 
     		//LIMPIAR
     		clearPhoneControls();			 				 		     	
     	
     		doSubmitAjax('salvarTelefonosAdicionales');
     	}
     }
	
     //eliminar fila (de tabla telefonos)
     function deleteRow(tableID) {
         try {

	         var table = document.getElementById(tableID);
	         var rowCount = table.rows.length;
         
         	 var vConfirm = confirm("Los datos del teléfono se perderán ¿Continuar?");

	         if(vConfirm){
		
		         for(var i=0; i<rowCount; i++) {
		             var row = table.rows[i];
		             var chkbox = row.cells[0].childNodes[0];
		             if(null != chkbox && true == chkbox.checked) {
		                 table.deleteRow(i);
		                 rowCount--;
		                 i--;
		             }
		         }
		         
		         doSubmitAjax('salvarTelefonosAdicionales');	         
	         
	         } else{
	         	 //quitar seleccion del checkbox
		         for(var i=0; i<rowCount; i++) {
		             var row = table.rows[i];
		             var chkbox = row.cells[0].childNodes[0];
		             if(null != chkbox && true == chkbox.checked) {
						chkbox.checked = false;
		             }
		         }	         	
	         }
         

         }catch(e) {
             //alert(e);
         }
     }    
     
     dojo.addOnLoad(function() {                   
    	 dojo.byId('acceso').disabled = true;
    	 dojo.byId('acceso').value = '01';
    	 loadTable("dataTable");	    
    	
    }); 
	
</script>

<html:messages id="errors">
	<span class="redFont Font80"><bean:write name="errors"/></span><br/>
</html:messages>

<html:messages id="messages" message="true">
	<span class="Font80"><bean:write name="messages"/></span><br/>
</html:messages>

</head>
<body class='claro'>

<form name="PhoneForm" id="PhoneForm" method="post" action="phone.do" dojoType="dijit.form.Form">
	<input type="hidden" name="method" id="method" value="salvarTelefonosAdicionales"/>
	<input type="hidden" name="dataTableRows" id="dataTableRows" value="0"/>
	
		<!-- <DIV style="margin-left:10px; width:600px;">  -->

        <h2>Teléfono</h2>
        <DIV class="entero" >  
        <span class="un_tercio"><strong><label for="idTipoTelefono">Tipo de teléfono</label>*</strong><br>
		<%
		List<CatalogoOpcionVO> lstTipoTelefono = (List<CatalogoOpcionVO>) request.getSession().getAttribute("CAT_TIPO_TELEFONO");					
		Iterator itLstTipoTelefono = lstTipoTelefono.iterator();
		while(itLstTipoTelefono.hasNext()){
			CatalogoOpcionVO vo = (CatalogoOpcionVO) itLstTipoTelefono.next();
			%>
			<%=vo.getOpcion()%>&nbsp;
			<input type="radio" id="idTipoTelefono" name="idTipoTelefono" checked="checked"
			value="<%=String.valueOf(vo.getIdCatalogoOpcion())%>" onClick="fillUpAccessKey();">&nbsp;&nbsp;
			<%					
		}							
		%>	
        </span> 
        <span class="un_tercio"><strong><label for="acceso">Acceso</label>*</strong><br>
		 <input id="acceso" name="acceso" maxlength="3" size="3"
		 	dojoType="dijit.form.ValidationTextBox" required="false" regExp="^(<%=CLAVE_TELEFONO_CELULAR%>|<%=CLAVE_TELEFONO_FIJO%>)$"
		 	invalidMessage="Dato inválido" missingMessage="Dato requerido" trim="true" readonly="readonly" />
		       			        
        </span>         			 
        <span class="un_tercio"><strong><label for="">Clave Lada</label>*</strong><br>  
		<input id="clave" name="clave" maxlength="3" size="3"
		 	dojoType="dijit.form.ValidationTextBox" required="false" regExp="^[0-9]{2,3}$"
		 	invalidMessage="Dato inválido" missingMessage="Dato requerido" trim="true" />	        			        
        </span> 			
        </div>
        <div  class="entero"  >                  
        <span class="un_tercio"><strong><label for="telefono">Teléfono</label>*</strong><br>
		<input id="telefono" name="telefono" maxlength="8" size="8" 
		 	dojoType="dijit.form.ValidationTextBox" required="false" regExp="^[0-9]{7,8}$"			 	
		 	invalidMessage="Dato inválido" missingMessage="Dato requerido" trim="true" />
		        			          
        </span> 
        			 
        <span class="un_tercio"><strong><label for="extension">Extensión</label></strong><br>
		<input id="extension" name="extension" maxlength="6" size="6"
		 	dojoType="dijit.form.ValidationTextBox" required="false" regExp="^[0-9]{1,6}$"
		 	invalidMessage="Dato inválido" missingMessage="Dato requerido" trim="true" />
		     		    			           
        </span>       	
       	</DIV>	  
		<DIV class="entero" id="divRegis" style="text-align:center;">         			    				   
        <span class="un_tercio">                                        
		<INPUT type="button" class="boton" id="btnAgregarTelefono"  value="Agregar" onclick="addRow('dataTable');" />
		</span>	
		<span class="un_tercio">
		<INPUT type="button" class="boton" id="btnCancelar"  value="Cerrar ventana" onclick="self.close();" />
		</span>
        </DIV>
	
		<div class="entero" >
		<h2>Teléfonos</h2>
		<table cellspacing="0" cellpadding="0" border="0" width="100%" id="dataTable">
		<tbody>
			<tr class="temas">
		    	<TH>Eliminar</TH>
		    	<TH>Tipo de teléfono</TH>
		    	<TH>Acceso</TH>
		    	<TH>Clave Lada</TH>
		    	<TH>Teléfono</TH>
		    	<TH>Extensión</TH>			
			</tr>
		</tbody>
		</table>
		</div> 

	<div id="message"></div>
		
</form>
</body>
</html>