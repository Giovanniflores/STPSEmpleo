<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="java.util.Date"%>
<%@ page import="mx.gob.stps.portal.persistencia.vo.CatalogoOpcionVO"%>
<%@ page import="mx.gob.stps.portal.core.candidate.vo.CandidatoVo"%>
<%@ page import="mx.gob.stps.portal.core.infra.utils.Constantes"%>
<%@ page import="mx.gob.stps.portal.web.candidate.form.FiltraCandidatoForm"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" 		prefix="fmt"%>

<c:set var="regexpmail">^[a-zA-Z0-9_-]+(?:\.[a-zA-Z0-9_-]+)*@(?:[a-zA-Z0-9](?:[a-zA-Z0-9-_]*[a-zA-Z0-9-])?\.)+[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?$</c:set>


<%
  String context = request.getContextPath() +"/";  
  FiltraCandidatoForm formCandidato = (FiltraCandidatoForm)session.getAttribute("FiltraCandidatoForm");
%>

<style type="text/css">
	@import "dojotoolkit/dojo/resources/dojo.css";
	@import "dojotoolkit/dijit/themes/claro/claro.css";
</style> 
<style type="text/css">
	.redFont{
		color: #FF0000;
	}
	.form_consulta .row {
		margin-top: 30px;
		padding-bottom: 10px;
		border-bottom: 1px solid #cccccc;
	}
</style>

<script src="dojotoolkit/dojo/dojo.js" djConfig="parseOnLoad: true, locale:'es'"></script>

<link href='https://fonts.googleapis.com/css?family=Droid+Sans' rel='stylesheet' type='text/css' />

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
	dojo.require("dijit.Dialog");
    dojo.require("dijit.form.Button");
    dojo.require("dijit.form.CheckBox");   
    dojo.require("dijit.form.FilteringSelect");
</script>
<noscript>Funciones de JavaScript desactivadas por navegador</noscript>
<link href="${PATH_CSS_SWB}css_aplicacion/estilos_tabla.css" rel="stylesheet" type="text/css" /> 


<script type="text/javascript">
function doSubmit(method){
	var valida = checkOtherFields();
	if(valida){
		document.FiltraCandidatoForm.method.value=method;	
		document.FiltraCandidatoForm.submit();
	}
}

function doSubmitPager(method){
	  dojo.byId('method').value = method;	 
	  		  
		  dojo.xhrPost({url: 'filtraCandidato.do', form:'FiltraCandidatoForm', timeout:180000, 
			  load: function(data){
				    dojo.byId('tabla').innerHTML=data;
			  }});
		  
		  
}

function cancelConfirmation() {
	var answer = confirm("Los datos no guardados se perderán ¿Continuar?");
	if (answer){
		doSubmit('init');
	}
}

//funcion para obtener el valor de un campo 
function getAnyElementValueById(elementId){
	var vElement = dijit.byId(elementId).value;
	if(vElement==undefined || vElement==''){
		vElement = document.getElementById(elementId).value;
	}
	return vElement;
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

function checkOtherFields(){
	var vForm = true;
	var countSelectedFields = 0;
	
	var nombre = getAnyElementValueById('nombre');
	if (!dijit.byId('nombre').isValid())
		vForm = false;
	else if (nombre.length > 0)	
		countSelectedFields = countSelectedFields + 1;

	var apellido = getAnyElementValueById('apellido1');
	if (!dijit.byId('apellido1').isValid())
		vForm = false;
	else if (apellido.length > 0)	
		countSelectedFields = countSelectedFields + 1;
	
	var fechaNacimiento = dijit.byId('fechaNacimiento').displayedValue;
	
	if (!dijit.byId('fechaNacimiento').isValid())
		vForm = false;
	else if (fechaNacimiento.length > 0)	
		countSelectedFields = countSelectedFields + 1;	
	
	var curp = getAnyElementValueById('curp');
	if (!dijit.byId('curp').isValid())
		vForm = false;
	else if (curp.length > 0)	
		countSelectedFields = countSelectedFields + 1;
	
	var mail = getAnyElementValueById('correoElectronico');
	if (!dijit.byId('correoElectronico').isValid())
		vForm = false;
	else if (mail.length > 0)	
		countSelectedFields = countSelectedFields + 1;	
	
	var regExp = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
	var countNombre = 0;
	var countOtrosFiltros = 0;
	
	var apellido2 = getAnyElementValueById('apellido2');
	if (!dijit.byId('apellido2').isValid())
		vForm = false;
	else if (apellido2.length > 0)	
		countSelectedFields = countSelectedFields + 1;	

	var telefono = getAnyElementValueById('telefono');
	if (!dijit.byId('telefono').isValid())
		vForm = false;
	else if (telefono.length > 0)	
		countSelectedFields = countSelectedFields + 1;
	
	var idEntidad = getAnyElementValueById('idEntidad');
	if (!dijit.byId('idEntidad').isValid() && idEntidad.length > 0)
		vForm = false;
	else if (idEntidad.length > 0)
		countSelectedFields = countSelectedFields + 1;	

	var idMunicipio = getAnyElementValueById('idMunicipio');
	if (!dijit.byId('idMunicipio').isValid() && idMunicipio.length > 0)
		vForm = false;
	else if (idMunicipio.length > 0)
		countSelectedFields = countSelectedFields + 1;	
	
	var domicilio = getAnyElementValueById('domicilio');
	if (!dijit.byId('domicilio').isValid() && domicilio != undefined)
		vForm = false;
	else if (domicilio.length > 0)
		countSelectedFields = countSelectedFields + 1;	
	
	var usuario = getAnyElementValueById('usuario');
	if (!dijit.byId('usuario').isValid())
		vForm = false;
	else if (usuario.length > 0)
		countSelectedFields = countSelectedFields + 1;

	if (!vForm){
		alert('Algunos campos contienen datos inválidos');
		return;
	}

    // Evaluamos que se cumple alguna de las combinaciones de búsqueda
    // 1. se ha proporcionado la CURP completa, o
    // 2. se han seleccionado al menos dos campos, siendo uno de ellos la entidad y el otro distinto del municipio
    // 3. se han seleccionado entidad, municipio y otro más
    if (curp.length < 18){
    	if (idEntidad.length == 0){
    		alert("Si no conoce la CURP es preciso seleccionar al menos dos campos, siendo uno de ellos la entidad  y el otro uno distinto al municipio");	
    		return;
    	} else {
    		if (countSelectedFields < 2){
	        	alert("Si no conoce la CURP es preciso seleccionar al menos dos campos, siendo uno de ellos la entidad y el otro uno distinto al municipio");
	    		return;    			
    		} else if (countSelectedFields == 2 && idMunicipio.length > 0){
	        	alert("Si no conoce la CURP, además de entidad y municipio será preciso proporcionar al menos otro campo");
	    		return;
    		}
    	}
    }
	
	if(nombre)
		countNombre++;
	if(apellido)
		countNombre++;
	if(fechaNacimiento != "")
		countOtrosFiltros++;
	if(curp)
		countOtrosFiltros++;
	if(mail)
		countOtrosFiltros++;
	
// 	if(countNombre != 0 && countNombre <2 && countOtrosFiltros == 0){
// 		alert('Se debe proporcionar Nombre y Apellido del Candidato');	
// 		vForm = false;
// 	}		
	
// 	if(mail){
// 		if (!regExp.test(mail)) {
// 			alert('Formato de correo electrónico inválido');
// 			vForm = false;
// 		}
// 	}
	return vForm;	
}

function doSubmitPagina(pagina) {
	dojo.xhrPost({url: 'filtraCandidato.do?method=goToPage&goToPageNumber='+pagina, timeout:180000,
		load: function(data) {
			dojo.byId('tabla').innerHTML=data;
	}});
}

</script>

<html:messages id="errors">
	<span class="redFont Font80"><bean:write name="errors"/></span><br/>
</html:messages>

<html:messages id="messages" message="true">
	<span class="Font80"><bean:write name="messages"/></span><br/>
</html:messages>

<c:if test="${noFiltros != null}"><span class="redFont Font80">${noFiltros}</span></c:if>

<h2>Consulta de candidatos</h2>
<form class="form_consulta" name="FiltraCandidatoForm" id="FiltraCandidatoForm" method="post" action="filtraCandidato.do" dojoType="dijit.form.Form">
	<input type="hidden" name="method" id="method" value="init"/>
	<div dojoType="dojo.data.ItemFileReadStore" jsId="entidadStore"   urlPreventCache="true" clearOnClose="true" url="filtraCandidato.do?method=consultarEntidades"></div>
	<div dojoType="dojo.data.ItemFileReadStore" jsId="municipioStore" urlPreventCache="true" clearOnClose="true" url="filtraCandidato.do?method=consultarMunicipio&idEntidad=0"></div>	

	<h3>Criterios de búsqueda</h3>
	<div>
       <DIV>
	   <div class="row">
	   		<div class="col-md-6">
			   <strong><label for="nombre">Nombre(s)</label></strong><br>
			   <input id="nombre" name="nombre" maxlength="50" size="50" 	        	
					dojoType="dijit.form.ValidationTextBox" required="false" regExp="^[a-zA-ZáéíóúÁÉÍÓÚñÑ0-9\s]{3,50}$"
					invalidMessage="Dato Invalido" missingMessage="Dato requerido" trim="true"/>			    
	       </div>
	       <div class="col-md-6">
			   <strong><label for="apellido1">Primer apellido</label></strong><br>
			   <input id="apellido1" name="apellido1" maxlength="50" size="50" 	        	
					dojoType="dijit.form.ValidationTextBox" required="false" regExp="^[a-zA-ZáéíóúÁÉÍÓÚñÑ0-9\s]{2,50}$"
					invalidMessage="Dato Invalido" missingMessage="Dato requerido" trim="true"/>		    
	       </div>
	   </div>
	   <div class="row">
		   	<div class="col-md-6">
			   <strong><label for="apellido2">Segundo apellido</label></strong><br>
			   <input id="apellido2" name="apellido2" maxlength="50" size="50" 	        	
					dojoType="dijit.form.ValidationTextBox" required="false" regExp="^[a-zA-ZáéíóúÁÉÍÓÚñÑ0-9\s]{2,50}$"
					invalidMessage="Dato Invalido" missingMessage="Dato requerido" trim="true"/>  			    
	       </div>
		   
	   </div>
	   <div class="row">
	   		<div class="col-md-6">
			   <strong><label for="fechaNacimiento">Fecha de nacimiento</label></strong><br/>
			   <input type="text" name="fechaNacimiento" id="fechaNacimiento" value="" dojoType="dijit.form.DateTextBox" placeHolder="Seleccionar una fecha" required="false"/>               					
		   </div>
	   		<div class="col-md-6"">
			   <strong><label for="curp">CURP</label></strong><br>
			  <input id="curp" name="curp" maxlength="18" size="18" 	        	
					dojoType="dijit.form.ValidationTextBox" required="false" regExp="^[a-zA-ZáéíóúÁÉÍÓÚñÑ0-9\s]{4,18}$"
					invalidMessage="Dato Invalido" missingMessage="Dato requerido" trim="true"/>   			    
	       </div>
	   </div>
	   
	   <div class="row">
	   		<div class="col-md-6">
			   <strong><label for="correoElectronico">Correo Electrónico</label></strong><br/>
			   <input type="text" name="correoElectronico" id="correoElectronico" value="" dojoType="dijit.form.ValidationTextBox" 
			   		  required="false" regExp="${regexpmail}" invalidMessage="Correo electrónico invalido, verificar captura." size="65" maxlength="65" trim="true" />             					
		   </div>
		   <div class="col-md-6">
			   <strong><label for="telefono">Teléfono</label></strong><br/>
			   <input type="text" name="telefono" id="telefono" value="" dojoType="dijit.form.ValidationTextBox" 
			   		  required="false" regExp="^[0-9]{4,10}$" size="10" maxlength="10" trim="true" />             					
		   </div>
	   </div>
	   <div class="row">
	   		<div class="col-md-6">
			   <strong><label for="idEntidad">Entidad</label></strong><br/>	   
				<select dojotype="dijit.form.FilteringSelect"  id="idEntidad" required="false"  name ="idEntidad" store="entidadStore" placeHolder="Seleccione la entidad"></select>	   
		   </div>
		   <div class="col-md-6">
			   <strong><label for="idMunicipio">Municipio</label></strong><br/>	   
			   <select dojotype="dijit.form.FilteringSelect"  id="idMunicipio" required="false" name ="idMunicipio" store="municipioStore" placeHolder="Seleccione el municipio"></select>	   
		   </div>
	   </div>
	   <div class="row">
	   		<div class="col-md-6">
			   <strong><label for="domicilio">Domicilio(calle)</label></strong><br/>	   
			   <input type="text" name="domicilio" id="domicilio" dojoType="dijit.form.ValidationTextBox" 
			   		  required="false" regExp="^[a-zA-ZáéíóúÁÉÍÓÚñÑ0-9\s]{4,200}$" size="20" maxlength="20" trim="true" />
		   </div>
		   <div class="col-md-6">
			   <strong><label for="usuario">Usuario</label></strong><br/>	   
			   <input type="text" name="usuario" id="usuario" dojoType="dijit.form.ValidationTextBox" 
			   		  required="false" regExp="^[a-zA-Z0-9@._\s]{4,65}$" size="65" maxlength="65" trim="true" />
		   </div>
	   </div>
</DIV>


	   <DIV class="entero" id="divRegis" style="text-align: center;">         			    				   
	   <div class="entero">                                        
	   <input type="button" id="btnBuscar" value="Buscar" class="boton" onclick="doSubmit('buscar');" /> &nbsp;&nbsp;&nbsp;
	   <input type="reset" id="btnCancel" value="Limpiar" class="boton"  />
       </div>
       </DIV>     

	   <div class="entero" style="text-align:center; ">
	   <h3>Candidato(s)</h3>
	   <div id="tabla" name="tabla">
	   </div>	
	  </div>       

     

       </DIV>
       <!-- cierra div principa -->
	   <div id="message"></div>
	   <div dojoType="dijit.Dialog" id="MensajeAlert" title="Mensaje" style="display:none">    
         <table width="500px" height="100px" >
           <tr align="center">
             <td><div id ="mensajeDos" style="width:500px;height:100px;overflow:auto;vertical-align:middle;"></div>&nbsp;</td>                   
           </tr>
         </table>                                
    	</div> 
</form>
	<script type="text/javascript">
	doSubmitPager('page');

	dojo.addOnLoad(function() {
		if(dijit.byId('idEntidad')){
			
			dijit.byId('idMunicipio').disabled=true;
			dojo.connect(dijit.byId("idEntidad"), "onChange", function() {
				
				var vIdEntidad = dijit.byId('idEntidad').get('value');
	         	if(vIdEntidad > 0){         	
		         	dijit.byId('idMunicipio').disabled=false;	         			         	
		         	municipioStore.url = "${context}filtraCandidato.do?method=consultarMunicipio" + "&" + "idEntidad="+ vIdEntidad;		         	
		         	municipioStore.close();
	         	} else {
	         		dijit.byId('idMunicipio').set('value', null);
	         		dijit.byId('idMunicipio').set('placeHolder', 'Seleccione el municipio');
		         	municipioStore.url = "${context}filtraCandidato.do?method=consultarMunicipio" + "&" + "idEntidad=0";		         	
		         	municipioStore.close();	        
 	         		dijit.byId('idMunicipio').disabled = true;
	         	}
				
	     });	
		
		}
	});
</script>














