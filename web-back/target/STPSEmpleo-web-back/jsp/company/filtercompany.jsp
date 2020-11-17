<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="mx.gob.stps.portal.core.infra.utils.Constantes"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" 		prefix="fmt"%>

<c:set var="regexpmail">^[a-zA-Z0-9_-]+(?:\.[a-zA-Z0-9_-]+)*@(?:[a-zA-Z0-9](?:[a-zA-Z0-9-_]*[a-zA-Z0-9-])?\.)+[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?$</c:set>


<style type="text/css">
	.redFont{
		color: #FF0000;
	}
	.Font80{
		font-size: 80%;
	}
	.form_consulta .row {
		margin-top: 30px;
		padding-bottom: 10px;
		border-bottom: 1px solid #cccccc;
	}
</style>

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
<script type="text/javascript">

var dialogDesactivacion;

function showWindowDesactivacion(idEmpresaADesactivar, tipoEmpresa){

	if(tipoEmpresa =='EMPRESA'){
		
		dialogDesactivacion = new dijit.Dialog({
	        title: 'Motivo de Desactivación de Empresa',
	        href: '${context}filtercompany.do?method=motivoDesactivacionDetalle&idEmpresaADesactivar=' + idEmpresaADesactivar,
	        style: "width:380px; height:460px;",
	        showTitle: false, draggable : true, closable : false
	  	});
	  
	  	dojo.style(dialogDesactivacion.closeButtonNode,"display","none");
	  	dialogDesactivacion.show();
	  
	} else {
		
		alert("La empresa " + idEmpresaADesactivar + " no se puede desactivar porque es una empresa de tipo " + tipoEmpresa);		
		
	}	
}

function closeWindowDesactivacion(){
	dialogDesactivacion.hide();
}

function warningSNECompany(idEmpresa, tipoEmpresa){
	alert("La empresa " + idEmpresa + " no puede desactivarse porque es una empresa del Sistema Nacional de Empleo.");
}

function deactivateCompany(idEmpresaADesactivar){
	
	if (confirm("¿Desea desactivar la empresa seleccionada?, al desactivar la empresa todas sus ofertas no serán visibles en el portal del empleo.")){
		
		if(!dijit.byId('idMotivoDesactivacionSelect').isValid()){
			dijit.byId('idMotivoDesactivacionSelect').focus();
			alert("El siguiente campo muestra datos inválidos: Motivo de desactivación. ");								
			return false;
		}			
		  
		if (dijit.byId('idMotivoDesactivacionSelect').get('item') && dijit.byId('idMotivoDesactivacionSelect').get('item').label){
			dojo.byId('idMotivoDesactivacion').value = dijit.byId('idMotivoDesactivacionSelect').get('item').label[0];
		}
				
		if(dojo.byId('motivoDesactivacionText').value.length<1){			
			alert("El siguiente campo muestra datos inválidos: Descripción. ");								
			return false;
		}		
		
		document.FilterCompanyForm.idMotivoDesactivacion.value = dojo.byId('idMotivoDesactivacion').value;
		document.FilterCompanyForm.motivoDesactivacion.value   = dojo.byId('motivoDesactivacionText').value;
		
		closeWindowDesactivacion();

		submitToMethod('desactivarEmpresa', idEmpresaADesactivar, null);		
		
	} else {
		
		closeWindowDesactivacion();
	}
} 


function submitToMethod(method, idEmpresaADesactivar, idEmpresaAReactivar){
	  dojo.byId('idEmpresaADesactivar').value = idEmpresaADesactivar;
	  dojo.byId('idEmpresaAReactivar').value = idEmpresaAReactivar;	  
	  dojo.byId('method').value=method;
	  dojo.byId('FilterCompanyForm').submit();
	  //closeWindow();
}


function reactivateCompanyConfirmation(idEmpresaAReactivar, tipoEmpresa) {
	if (confirm("¿Desea reactivar la empresa seleccionada?")){
		document.FilterCompanyForm.method.value='reactivarEmpresa';
		document.FilterCompanyForm.idEmpresaAReactivar.value=idEmpresaAReactivar;
		document.FilterCompanyForm.TIPO_EMPRESA_ELIM.value=tipoEmpresa;
		document.FilterCompanyForm.submit();		
	}
}

function caracteresValidos(e){
	var tecla_codigo = (document.all) ? e.keyCode : e.which;
	if(tecla_codigo==8 || tecla_codigo==0)
		return true;
	if (tecla_codigo==225 || tecla_codigo==233 || tecla_codigo==237 || tecla_codigo==243 || tecla_codigo==250) 
		return true; //vocales minusculas con acento
	if (tecla_codigo==193 || tecla_codigo==201 || tecla_codigo==205 || tecla_codigo==211 || tecla_codigo==218) 
		return true; //vocales mayuzculas con acento
	if (tecla_codigo==209 || tecla_codigo==241 ) 
		return true; 
		
	var patron =/[0-9\-a-zA-Z_ .,:;#]/;
	tecla_valor = String.fromCharCode(tecla_codigo);
	return patron.test(tecla_valor);
}  	

function maximaLongitud(texto,maxlong) {
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

function publishConfirmation(idEmpresa, tipoEmpresa) {

	if (confirm("¿Está seguro que desea recuperar la empresa para que sea publicable nuevamente?")){

		document.FilterCompanyForm.method.value='recuperaempresa';
		document.FilterCompanyForm.idEmpresa.value=idEmpresa;
		document.FilterCompanyForm.TIPO_EMPRESA_ELIM.value=tipoEmpresa;
		document.FilterCompanyForm.submit();

		/*dojo.xhrPost({url: 'filtercompany.do', form:'FilterCompanyForm', timeout:180000, 
					  load: function(data){
						    document.cilForm.idRegValidar.value=idRegVal;
						    dojo.byId('tablaTelefonos').innerHTML=data;
					  }});*/
	}
}	

function doSubmit(method){
	if(checkOtherFields()){
		document.FilterCompanyForm.method.value=method;
		document.FilterCompanyForm.submit();			
	}
}

function doSubmitAction(method){
	document.FilterCompanyForm.method.value=method;
	document.FilterCompanyForm.submit();			
}

function doSubmitPager(method){
	
	  dojo.byId('method').value = method;	 
	  		  
		  dojo.xhrPost({url: 'filtercompany.do', form:'FilterCompanyForm', timeout:180000, 
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
	var vMessage = '';
	var chkVal = getRadioValue('idTipoPersona');
	
	if(chkVal==null){ 
		vMessage = vMessage + '\nDebe proporcionar el Tipo de persona';
		vForm = false;
	} else {
		if(chkVal==<%=Constantes.TIPO_PERSONA.PERSONA_FISICA.getIdTipoPersona()%>){
			
			var nomb = dijit.byId('nombre').value;
			if (!dijit.byId('nombre').isValid())
				vForm = false;
			else if (nomb.length > 0)	
				countSelectedFields = countSelectedFields + 1;
			
			var ap1 = dijit.byId('apellido1').value;
			if (!dijit.byId('apellido1').isValid())
				vForm = false;
			else if (ap1.length > 0)	
				countSelectedFields = countSelectedFields + 1;
			
			var ap2 = dijit.byId('apellido2').value;
			if (!dijit.byId('apellido2').isValid())
				vForm = false;
			else if (ap2.length > 0)
				countSelectedFields = countSelectedFields + 1;
			
// 			if(!nomb && !ap1 && !correo && !idPortalEmpleo){
// 				vMessage = vMessage + '\nDebe proporcionar un filtro para realizar la busqueda.';	
// 				vForm = false;
// 			}
			
		} else {
			var razon = dijit.byId('razonSocial').value;
			if (!dijit.byId('razonSocial').isValid())
				vForm = false;
			else if (razon.length > 0)	
				countSelectedFields = countSelectedFields + 1;

			var fechaAct = dijit.byId('fechaActa').get('value');			
			if (!dijit.byId('fechaActa').isValid())
				vForm = false;
			else if (fechaAct != null)	
				countSelectedFields = countSelectedFields + 1;
		}
		
// 		var correo = dijit.byId('correoElectronico').value;					
// 		if (!dijit.byId('correoElectronico').isValid())
// 			vForm = false;
// 		else if (correo.length > 0)
// 			countSelectedFields = countSelectedFields + 1;			

// 			if(!razon && !fechaAct && !correo && !idPortalEmpleo){
// 				vMessage = vMessage + '\nDebe proporcionar un filtro para realizar la busqueda.';
// 				vForm = false;
// 			}
		
			var idPortalEmpleo = dijit.byId('idPortalEmpleo').value;
			if (!dijit.byId('idPortalEmpleo').isValid())
				vForm = false;
			else if (idPortalEmpleo.length > 0)	
				countSelectedFields = countSelectedFields + 1;
			
			var idEmpresa = dijit.byId('idEmpresa').value;
			if (!dijit.byId('idEmpresa').isValid())
				vForm = false;
			else if (idEmpresa.length > 0)	
				countSelectedFields = countSelectedFields + 1;
			
			var correoElectronico = dijit.byId('correoElectronico').value;
			if (!dijit.byId('correoElectronico').isValid())
				vForm = false;
			else if (correoElectronico.length > 0)	
				countSelectedFields = countSelectedFields + 1;
	
			var contacto = dijit.byId('contacto').value;
			if (!dijit.byId('contacto').isValid())
				vForm = false;
			else if (contacto.length > 0)
				countSelectedFields = countSelectedFields + 1;

			var telefono = dijit.byId('telefono').value;
			if (!dijit.byId('telefono').isValid())
				vForm = false;
			else if (telefono.length > 0)	
				countSelectedFields = countSelectedFields + 1;
	
			var domicilio = dijit.byId('domicilio').value;
			if (!dijit.byId('domicilio').isValid())
				vForm = false;
			else if (domicilio.length > 0)	
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
					
			var usuario = getAnyElementValueById('usuario');
			if (!dijit.byId('usuario').isValid())
				vForm = false;
			else if (usuario.length > 0)
				countSelectedFields = countSelectedFields + 1;

			if (!vForm){
				alert('Algunos campos contienen datos inválidos');
				return vForm;
			}

		    // Evaluamos que se cumple alguna de las combinaciones de búsqueda
		    // 1. Si han proporcionado idPortalEmpleo o idEmpresa no se solicitan más campos
		    // 2. Si no se ha proporcionado alguno de los campos anteriores, se pedirán dos campos donde uno de ellos sea la entidad y el otro sea distinto del municipio 
		    if (idPortalEmpleo.length == 0 && idEmpresa.length == 0 && vForm) {
	    		if (countSelectedFields < 2){
	    			alert("Si no conoce el Id. Portal del Empleo o el Id. Empresa es preciso seleccionar al menos dos campos, siendo uno de ellos la entidad y el otro uno distinto al municipio");
	    			return false;
	    		} else if (countSelectedFields == 2 && idEntidad.length > 0 && idMunicipio.length > 0){
		        	alert("Si no conoce el Id. Portal del Empleo o el Id. Empresa, además de entidad y municipio será preciso proporcionar al menos otro campo");
		    		return false;
	    		} else if (countSelectedFields >= 2 && idEntidad.length == 0){
	    			alert("Si no conoce el Id. Portal del Empleo o el Id. Empresa es preciso seleccionar al menos dos campos, siendo uno de ellos la entidad y el otro uno distinto al municipio");
	    			return false;
	    		}
		    }
	}
	
	if(vMessage.length>0){
		alert(vMessage);
	}
	return vForm;	
}

//funcion para cambiar los campos requeridos dependiendo del tipo de persona
function changePersonTypeRequired(){
	var chkVal = getRadioValue('idTipoPersona');	
	var widget1 = dijit.byId('nombre');
	var widget2 = dijit.byId('apellido1');
	var widget3 = dijit.byId('apellido2');	
	var widget5 = dijit.byId('razonSocial');
	var widget6 = dijit.byId('fechaActa');	
	if(chkVal==<%=Constantes.TIPO_PERSONA.PERSONA_FISICA.getIdTipoPersona()%>){		
		dijit.byId('razonSocial').attr('value','');		
		dijit.byId('fechaActa').attr('value',null);	
		widget1.setDisabled(false);
		widget2.setDisabled(false);
		widget3.setDisabled(false);		
		widget5.setDisabled(true);
		widget6.setDisabled(true);	
	} else {
		dijit.byId('nombre').attr('value','');	
		dijit.byId('apellido1').attr('value','');
		dijit.byId('apellido2').attr('value','');		
		widget1.setDisabled(true);
		widget2.setDisabled(true);
		widget3.setDisabled(true);
		widget5.setDisabled(false);
		widget6.setDisabled(false);
		widget6.reset();
	}
}

function doSubmitPagina(pagina){
	  dojo.byId('method').value = "goToPage";
	  dojo.byId('goToPageNumber').value = pagina;
	  dojo.xhrPost({url: 'filtercompany.do', form:'FilterCompanyForm', timeout:180000, 
		  load: function(data){
			    dojo.byId('tabla').innerHTML=data;
		  }});
}

function mostrarResumen(idResumen) {
    var thisRow = dojo.style(idResumen, "display");
    
    if (thisRow == 'block') {
        dojo.style(idResumen, "display", "none");
        dojo.style(eval("\"" + "hide" + idResumen + "\""), "display", "none");
        dojo.style(eval("\"" + "show" + idResumen + "\""), "display", "block");
    }
        
    if (thisRow == 'none') {
        dojo.style(idResumen, "display", "block");
        dojo.style(eval("\"" + "hide" + idResumen + "\""), "display", "block");
        dojo.style(eval("\"" + "show" + idResumen + "\""), "display", "none");
    }
 }
 
function mostrarTodas(tipo, totReg) {
    if (tipo == 1) {
        for (var i = 1; i <= totReg; i++) {
            dojo.style("hideAll", "display", "block");
            dojo.style("showAll", "display", "none");
            dojo.style(eval("\"" + i + "\""), "display", "block");
            dojo.style(eval("\"" + "hide" + i + "\""), "display", "block");
            dojo.style(eval("\"" + "show" + i + "\""), "display", "none");
        }
    }

    if (tipo == 2) {
        for (var i = 1; i <= totReg; i++) {
            dojo.style("hideAll", "display", "none");
            dojo.style("showAll", "display", "block");
            dojo.style(eval("\"" + i + "\""), "display", "none");
            dojo.style(eval("\"" + "hide" + i + "\""), "display", "none");
            dojo.style(eval("\"" + "show" + i + "\""), "display", "block");
        }
    }
  }
</script>

<html:messages id="errors">
    <span class="redFont Font80" ><bean:write name="errors"/></span><br/>
</html:messages>

<html:messages id="messages" message="true">
    <span class="Font80"><bean:write name="messages"/></span><br/>
</html:messages>

<form name="FilterCompanyForm" id="FilterCompanyForm" method="post" action="filtercompany.do" dojoType="dijit.form.Form">
	
	<input type="hidden" name="method" id="method" value="buscar"/>
	<input type="hidden" name="goToPageNumber" id="goToPageNumber" value="1" />
	
<!-- 	<input type="hidden" name="idEmpresa" id="idEmpresa"/> -->
	<input type="hidden" name="idEmpresaADesactivar" id="idEmpresaADesactivar"/>
	<input type="hidden" name="idEmpresaAReactivar" id="idEmpresaAReactivar"/>
	<input type="hidden" name="TIPO_EMPRESA_ELIM" id="TIPO_EMPRESA_ELIM"/>
	<input type="hidden" name="idMotivoDesactivacion" id="idMotivoDesactivacion" value="0"/>
	<input type="hidden" name="motivoDesactivacion" id="motivoDesactivacion" value=""/>	
	
	<div dojoType="dojo.data.ItemFileReadStore" jsId="entidadStore"   urlPreventCache="true" clearOnClose="true" url="filtercompany.do?method=consultarEntidades"></div>
	<div dojoType="dojo.data.ItemFileReadStore" jsId="municipioStore" urlPreventCache="true" clearOnClose="true" url="filtercompany.do?method=consultarMunicipio&idEntidad=0"></div>	

	<h3>Consulta de empresa</h3>
		
		<div class="form_consulta">
	       	<div class="row">
	       		<div class="col-md-6">
		       		<label for="idTipoPersona"><strong>Tipo de persona*</strong></label>:<br>
		       		Física&nbsp;<input type="radio" id="idTipoPersona" name="idTipoPersona" value="1" onClick="changePersonTypeRequired()"/>&nbsp;&nbsp;&nbsp;
		       		Moral&nbsp;<input type="radio" id="idTipoPersona" name="idTipoPersona" value="2" onClick="changePersonTypeRequired()"/>
	       		</div>
	       	</div>
	       	<div class="row">
	        	<div class="col-md-6">
		        	<strong><label for="idPortalEmpleo">ID del Portal del Empleo</label>*:</strong><br/>
					<input id="idPortalEmpleo" name="idPortalEmpleo" required="false" regExp="^[a-zA-ZáéíóúÁÉÍÓÚñÑ0-9]{3,17}$"
				           dojoType="dijit.form.ValidationTextBox"
						   invalidMessage="Dato inválido" maxlength="21" uppercase="true" clearOnClose="true" trim="true"
						   style="font-family:Arial, Helvetica, sans-serif;color:#444444;" />
		       	</div>
				<div class="col-md-6">
				   <strong><label for="idEmpresa">Id empresa</label>:</strong><br>
				   <input id="idEmpresa" name="idEmpresa" maxlength="8" size="8" 	        	
						dojoType="dijit.form.ValidationTextBox" required="false" regExp="^[0-9]{1,8}$"
						invalidMessage="Dato Invalido" missingMessage="Dato requerido" trim="true"/>
			   	</div>
	       	</div>
	       	<div class="row">
	       		<div class="col-md-6">
		           <strong> <label for="correoElectronico">Correo electronico</label>:&nbsp;&nbsp;</strong><br/>
		            <input id="correoElectronico" name="correoElectronico"
		            	   required="false" regExp="${regexpmail}" invalidMessage="Correo electrónico invalido, verificar captura." dojoType="dijit.form.ValidationTextBox" size="65" maxlength="65" trim="true"/>
		       	</div>
				<div class="col-md-6">
					<strong><label for="nombre">Nombre(s)</label>:&nbsp;&nbsp;</strong><br/>
		       		<input id="nombre" name="nombre" maxlength="50" size="50" 	        	
		        		   dojoType="dijit.form.ValidationTextBox" required="false" regExp="^[a-zA-ZáéíóúÁÉÍÓÚñÑ0-9\s]{3,50}$"
		        		   invalidMessage="Dato inválido" missingMessage="Dato requerido" trim="true"/>
		       		<label for="nombre"></label>       			    
		       	</div>
	       	</div>
	       	<div class="row">
	       		<div class="col-md-6">
		       		<strong><label for="apellido1">Primer apellido</label>:&nbsp;&nbsp;</strong><br/>
		       		<input id="apellido1" name="apellido1" maxlength="50" size="50" 	        	
		        		   dojoType="dijit.form.ValidationTextBox" required="false" regExp="^[a-zA-ZáéíóúÁÉÍÓÚñÑ0-9\s]{2,50}$"
		        		   invalidMessage="Dato inválido" missingMessage="Dato requerido" trim="true"/>
		       		<label for="apellido1"></label>       			    
		       	</div>
		       <div class="col-md-6">
				   <strong><label for="apellido2">Segundo apellido</label>:</strong><br>
				   <input id="apellido2" name="apellido2" maxlength="50" size="50" 	        	
						dojoType="dijit.form.ValidationTextBox" required="false" regExp="^[a-zA-ZáéíóúÁÉÍÓÚñÑ0-9\s]{2,50}$"
						invalidMessage="Dato Invalido" missingMessage="Dato requerido" trim="true"/>
				          			    
		       </div> 
	       	</div>
	       	<div class="row">
		       	<div class="col-md-6">
					<strong><label for="razonSocial">Razón social</label>:&nbsp;&nbsp;</strong><br/>
		       		<input id="razonSocial" name="razonSocial" maxlength="150" size="50" 			
		       			   dojoType="dijit.form.ValidationTextBox" required="false" regExp="^[a-zA-ZáéíóúüÁÉÍÓÚñÑÜ0-9.,&()!'+@/\s]{3,150}$" 
		       			   invalidMessage="Dato inválido" missingMessage="Dato requerido" trim="true"/>
		       		<label for="razonSocial"></label>       			    
				</div>
				<div class="col-md-6">
					<strong><label for="fechaActa">Fecha de acta constitutiva</label>:&nbsp;&nbsp;</strong><br/>
		       		<input type="text" name="fechaActa" id="fechaActa" value="" dojoType="dijit.form.DateTextBox" 
		              	   maxlength="10" constraints={datePattern:'dd/MM/yyyy'} trim="true" required="false"/>
			   	</div>
	       	</div>
<!-- 		<span class="un_tercio"> -->
<!-- 		   <strong><label for="idPortalEmpleo">Id Portal del Empleo</label></strong><br> -->
<!-- 		   <input id="idPortalEmpleo" name="idPortalEmpleo" maxlength="17" size="17" 	        	 -->
<!-- 				dojoType="dijit.form.ValidationTextBox" required="false" regExp="^[a-zA-ZáéíóúÁÉÍÓÚñÑ0-9\s]{3,17}$" -->
<!-- 				invalidMessage="Dato Invalido" missingMessage="Dato requerido" trim="true"/> -->
<!-- 	   	</span>	   	 -->

<!-- 		<span class="un_tercio"> -->
<!-- 		   <strong><label for="idEmpresa">Id empresa</label></strong><br> -->
<!-- 		   <input id="idEmpresa" name="idEmpresa" maxlength="8" size="8" 	        	 -->
<!-- 				dojoType="dijit.form.ValidationTextBox" required="false" regExp="^[0-9]{1,8}$" -->
<!-- 				invalidMessage="Dato Invalido" missingMessage="Dato requerido" trim="true"/> -->
<!-- 	   	</span> -->
	   	
<!-- 	   <span class="un_tercio"> -->
<!-- 		   <strong><label for="correoElectronico">Correo Electrónico</label></strong><br/> -->
<!-- 		   <input type="text" name="correoElectronico" id="correoElectronico" value="" dojoType="dijit.form.ValidationTextBox"  -->
<!-- 		   		  required="false" regExp="^[a-zA-Z0-9@.\s]{4,65}$" size="65" maxlength="65" trim="true" />             					 -->
<!-- 	   </span> -->

	       	<div class="row">
	       		<div class="col-md-6">
				   <strong><label for="contacto">Contacto</label>:</strong><br>
				   <input id="contacto" name="contacto" maxlength="150" size="150" 	        	
						dojoType="dijit.form.ValidationTextBox" required="false" regExp="^[a-zA-ZáéíóúÁÉÍÓÚñÑ\s]{3,150}$"
						invalidMessage="Dato Invalido" missingMessage="Dato requerido" trim="true"/>	    
		       </div>
			   <div class="col-md-6">
				   <strong><label for="telefono">Teléfono</label>:</strong><br/>
				   <input type="text" name="telefono" id="telefono" value="" dojoType="dijit.form.ValidationTextBox" 
				   		  required="false" regExp="^[0-9]{4,10}$" size="10" maxlength="10" trim="true" />             					
			   </div>
	       	</div>
	       	<div class="row">
	       		<div class="col-md-6">
				   <strong><label for="domicilio">Domicilio (calle)</label>:</strong><br/>	   
				   <input type="text" name="domicilio" id="domicilio" dojoType="dijit.form.ValidationTextBox" 
				   		  required="false" regExp="^[a-zA-ZáéíóúÁÉÍÓÚñÑ0-9\s]{4,200}$" size="20" maxlength="20" trim="true" />
			   </div>
			   <div class="col-md-6">
				   <strong><label for="idEntidad">Entidad</label>:</strong><br/>	   
					<select dojotype="dijit.form.FilteringSelect"  id="idEntidad" required="false"  name ="idEntidad" store="entidadStore" placeHolder="Seleccione la entidad"></select>	   
			   </div>
	       	</div>
	       	<div class="row">
		       	<div class="col-md-6"">
				   <strong><label for="idMunicipio">Municipio</label>:</strong><br/>	   
				   <select dojotype="dijit.form.FilteringSelect"  id="idMunicipio" required="false" name ="idMunicipio" store="municipioStore" placeHolder="Seleccione el municipio"></select>	   
			   </div>	   
			   <div class="col-md-6"">
				   <strong><label for="usuario">Usuario</label>:</strong><br/>	   
				   <input type="text" name="usuario" id="usuario" dojoType="dijit.form.ValidationTextBox" 
				   		  required="false" regExp="^[a-zA-Z0-9@.\s]{4,65}$" size="65" maxlength="65" trim="true" />
			   </div>
			   </div>
	       	</div>
       
		<div class="entero" style="text-align: center;">
			<input type="button" id="btnBuscar" value="Buscar"  class="boton" onclick="doSubmit('buscar');" />
			<input type="button" id="btnLimpiar" value="Limpiar"  class="boton" onclick="doSubmitAction('limpiar');" />
		</div>



	<div id="tabla"></div>
    <!-- cierra div principal -->
	<div id="message"></div>
	 
</form>

<c:if test="${empty requestScope['init']}">
	<script>
		doSubmitPager('page');
	</script>
</c:if>

<script type="text/javascript">

	dojo.addOnLoad(function() {
		if(dijit.byId('idEntidad')){
			
			dijit.byId('idMunicipio').disabled=true;
			dojo.connect(dijit.byId("idEntidad"), "onChange", function() {
				
				var vIdEntidad = dijit.byId('idEntidad').get('value');
	         	if(vIdEntidad > 0){         	
		         	dijit.byId('idMunicipio').disabled=false;	         			         	
		         	municipioStore.url = "${context}filtraCandidato.do?method=consultarMunicipio" + "&" + "idEntidad="+ vIdEntidad;		         	
		         	municipioStore.close();
		         	dijit.byId('idMunicipio').set('value', null);
	         		dijit.byId('idMunicipio').set('placeHolder', 'Seleccione el municipio');		         	
	         	} else {
	         		dijit.byId('idMunicipio').set('placeHolder', 'Seleccione el municipio');
		         	municipioStore.url = "${context}filtraCandidato.do?method=consultarMunicipio" + "&" + "idEntidad=0";		         	
		         	municipioStore.close();	        
 	         		dijit.byId('idMunicipio').disabled = true;
 		         	dijit.byId('idMunicipio').set('value', null);
 	         		dijit.byId('idMunicipio').set('placeHolder', 'Seleccione el municipio'); 	         		
	         	}
				
	     });	
		
		}
	});
</script>

