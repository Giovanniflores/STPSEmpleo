<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="mx.gob.stps.portal.core.infra.utils.Constantes"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setBundle basename="MessageResources" var="portalbundle"/>

<%
	String context = request.getContextPath() +"/";
%>
<style type="text/css">
	.redFont{
		color: #FF0000;
	}
	.Font80{
		font-size: 80%;
	}
	.mod-date .dijitTextBox {
		width: 8em;
	}
	.form_consulta .row {
		margin-top: 30px;
		padding-bottom: 10px;
		border-bottom: 1px solid #cccccc;
	}
	#widget_titulo {
		width: 100%;
	}
	.btnPE {
		margin: 20px 20px 40px;
	}
	
</style>

<c:set var="regexpmail"><fmt:message key='app.pattern.mail' bundle='${portalbundle}'/></c:set>

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
    dojo.require("dijit.Tooltip");
    
    var dialogDetalle;

	function doSubmit(method){
		if(checkOtherFields()){
			document.FilterOfferForm.method.value=method;
			document.FilterOfferForm.submit();			
		}
	}
	function doSubmitAction(method){
		document.FilterOfferForm.method.value=method;
		document.FilterOfferForm.submit();			
	}
	function doSubmitPager(method){
	  dojo.byId('method').value = method;	 
	  dojo.xhrPost({url: 'filteroffer.do', form:'FilterOfferForm', 
	  	load: function(data) {
	  		dojo.byId('table').innerHTML=data;
		}});  
	}
	function cancelConfirmation() {
		var answer = confirm("Los datos no guardados se perderán ¿Continuar?");
		if (answer){
			doSubmit('init');
		}
	}
	function getAnyElementValueById(elementId){
		var vElement = dijit.byId(elementId).value;
		if(vElement==undefined || vElement==''){
			vElement = document.getElementById(elementId).value;
		}
		return vElement;
	}
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
		var vMessage = '';
		var countSelectedFields = 0;

		var idPortal = getAnyElementValueById('idPortal');
		if (!dijit.byId('idPortal').isValid())
			vForm = false;
		else if (idPortal.length > 0)	
			countSelectedFields = countSelectedFields + 1;

		var idEmpresa = getAnyElementValueById('idEmpresa');
		if (!dijit.byId('idEmpresa').isValid())
			vForm = false;
		else if (idEmpresa.length > 0)
			countSelectedFields = countSelectedFields + 1;
		
		var idoffer = getAnyElementValueById('idoffer');
		if (!dijit.byId('idoffer').isValid())
			vForm = false;
		else if (idoffer.length > 0)
			countSelectedFields = countSelectedFields + 1;
		
		var email = getAnyElementValueById('email');
		if (!dijit.byId('email').isValid())
			vForm = false;
		else if (email.length > 0)
			countSelectedFields = countSelectedFields + 1;		

		var contacto = getAnyElementValueById('contacto');
		if (!dijit.byId('contacto').isValid())
			vForm = false;
		else if (contacto.length > 0)
			countSelectedFields = countSelectedFields + 1;

		var telefono = getAnyElementValueById('telefono');
		if (!dijit.byId('telefono').isValid())
			vForm = false;
		else if (telefono.length > 0)
			countSelectedFields = countSelectedFields + 1;
		
		var idEntSelect = getAnyElementValueById('idEntidadSelect');		
		if (!dijit.byId('idEntidadSelect').isValid())
			vForm = false;
		else if (idEntSelect.length > 0)
			countSelectedFields = countSelectedFields + 1;

		var idMunicipio = getAnyElementValueById('idMunicipio');		
		if (!dijit.byId('idMunicipio').isValid())
			vForm = false;
		else if (idMunicipio.length > 0)
			countSelectedFields = countSelectedFields + 1;

		var dateInitAdd = dijit.byId('dateInitAdd').get('value');
		if (!dijit.byId('dateInitAdd').isValid())
			vForm = false;
		else if (dateInitAdd != null)	
			countSelectedFields = countSelectedFields + 1;
		
		var dateFinalAdd = dijit.byId('dateFinalAdd').get('value');
		if (!dijit.byId('dateFinalAdd').isValid())
			vForm = false;
		else if (dateFinalAdd != null)	
			countSelectedFields = countSelectedFields + 1;
		
		var dateInitUpd = dijit.byId('dateInitUpd').get('value');
		if (!dijit.byId('dateInitUpd').isValid())
			vForm = false;
		else if (dateInitUpd != null)	
			countSelectedFields = countSelectedFields + 1;
		
		var dateFinalUpd = dijit.byId('dateFinalUpd').get('value');
		if (!dijit.byId('dateFinalUpd').isValid())
			vForm = false;
		else if (dateFinalUpd != null)	
			countSelectedFields = countSelectedFields + 1;
		
		var salario = dijit.byId('salario').get('value');
		if (!dijit.byId('salario').isValid())
			vForm = false;		
		else if (salario.length > 0)
			countSelectedFields = countSelectedFields + 1;		
		
		if (!vForm)
			vMessage = 'Algunos campos contienen datos inválidos';
		
	    // Evaluamos que se cumple alguna de las combinaciones de búsqueda
	    // 1. Si selecciona Id. Portal de Empleo o Id. Empresa no exigimos más campos
	    // 2. Se seleccionan al menos dos campos, donde uno de ellos no es el municipio
		if (idPortal.length == 0 && idEmpresa.length == 0 && idoffer.length == 0 && vForm) {
    		if (countSelectedFields < 2){
    			alert("Si no conoce el Id. Portal del Empleo o el Id. Empresa es preciso seleccionar al menos dos campos, siendo uno de ellos el lugar de la vacante y el otro uno distinto al municipio");
    			return false;
    		} else if (countSelectedFields == 2 && idEntSelect.length > 0 && idMunicipio.length > 0){
	        	alert("Si no conoce el Id. Portal del Empleo o el Id. Empresa, además de entidad y municipio será preciso proporcionar al menos otro campo");
	    		return false;
    		} else if (countSelectedFields >= 2 && idEntSelect.length == 0){
    			alert("Si no conoce el Id. Portal del Empleo o el Id. Empresa es preciso seleccionar al menos dos campos, siendo uno de ellos el lugar de la vacante y el otro uno distinto al municipio");
    			return false;
    		}
		}
	    

// 		var status = getAnyElementValueById('status');
// 		var deleteRazon = getAnyElementValueById('deleteRazon');

// 		if (email.length<1 && idoffer.length<1 && status==0 && dateInitAdd.length<1 && idPortal.length<1 && dateInitUpd.length<1 && deleteRazon==0 && idEntSelect==0) {
// 			vMessage = vMessage + '\nDebe proporcionar un filtro para realizar la busqueda.';
// 			vForm = false;
// 		}

// 		if ((dojo.byId('dateInitAdd').value != '' && dojo.byId('dateFinalAdd').value == '') ||
// 			(dojo.byId('dateInitAdd').value == '' && dojo.byId('dateFinalAdd').value != '')){
// 			vMessage = vMessage + '\nPara poder filtrar por fecha de alta, es preciso informar los dos campos que definen el intérvalo.';
// 			vForm = false;			
// 		}

// 		if ((dojo.byId('dateInitUpd').value != '' && dojo.byId('dateFinalUpd').value == '') ||
// 			(dojo.byId('dateInitUpd').value == '' && dojo.byId('dateFinalUpd').value != '')){			
// 				vMessage = vMessage + '\nPara poder filtrar por fecha de modificación, es preciso informar las dos fechas que definen el intérvalo.';
// 				vForm = false;				
// 		}			
		
		if (vMessage.length>0) {
			alert(vMessage);
		}
// 		if (dijit.byId('idEntidadSelect').get('item') && dijit.byId('idEntidadSelect').get('item').label){
// 			dojo.byId('district').value = dijit.byId('idEntidadSelect').get('item').label[0];			
// 		}

		return vForm;
	}
	
		function doSubmitPagina(pagina, dif) {
    		dojo.xhrPost({url: 'filteroffer.do?method=goToPage&tablaPager=_OFEPUBLISH&goToPageNumber='+pagina, timeout:180000, 
				load: function(data){
    	 			dojo.byId('table').innerHTML=data;
    	 			
			}});
    }
    
    
	/*function doSubmitPagina(pagina) {
		dojo.byId('method').value = "goToPage";
		dojo.byId('goToPageNumber').value = pagina;
		dojo.xhrPost({url: 'filteroffer.do', form:'FilterOfferForm', timeout:180000, 
			load: function(data) {
				dojo.byId('table').innerHTML=data;
		}});
	}*/
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
	
	function showWindowOffer(idRegValidar){
		dojo.byId('idRegValidar').value = idRegValidar;
		/**dialogDetalle = new dijit.Dialog({
		      title: 'Detalle de registro',
		      href: '${pageContext.request.contextPath}/autorization.do?method=detalleRegistro&idRegValidar='+idRegValidar+'&desdeBusquedaOfertas=true',
		      style: "width:800px; height:720px;",
		      showTitle: false, draggable : false, closable : false});
		dojo.style(dialogDetalle.closeButtonNode,"display","none");	
		dialogDetalle.show();*/
		
		document.location='${pageContext.request.contextPath}/autorization.do?method=detalleRegistro&idRegValidar='+idRegValidar+'&desdeBusquedaOfertas=true';
	}

	  function autorizarRegistro(idRegValidar){
		  dialogDetalle.hide();		  
		  dojo.byId('idRegValidar').value = idRegValidar;
		  dojo.xhrPost(
					{url: 'filteroffer.do?method=autorizarRegistro', form:'autorizationForm', timeout:180000, 
					 load: function(data)
					  		{var res = dojo.fromJson(data);
					  		alert(res.message);
					  		if (res.type == 'ext')
								doSubmit('filter');
					  }
					});
	  }
	  
	  function editarOfertaEmpleo(idRegValidar, idEmpresa, idOfertaEmpleo){
		  dojo.byId('method2').value='editarOfertaEmpleo';
		  dojo.byId('idRegValidar').value = idRegValidar;
		  dojo.byId('idEmpresa').value = idEmpresa;
		  dojo.byId('idOfertaEmpleo').value = idOfertaEmpleo;  
		  dojo.byId('autorizationForm').submit();
	  }
	  
	  function showWindowMotivoRechazo(idRegValidar){

		  if (dojo.byId('dialogRechazo')){			  
			  dialogRechazo.show();
		  }
		  else{
			  dialogRechazo = new dijit.Dialog({
		        title: 'Motivo de Rechazo',
		        id: 'dialogRechazo',
		        href: '${context}autorization.do?method=motivoRechazoDetalle',
		        style: "width:280px; height:260px;",
		        showTitle: false, draggable : true, closable : false
		  });
		  
		  dojo.style(dialogRechazo.closeButtonNode,"display","none");
		  dialogRechazo.show();
		  }
	  }	  
	  
	  function cancelarValidacion(idRegValidar){
		  dialogDetalle.hide();
	  }
	  
	  function rechazarRegistro(idRegValidar){

		if(!dijit.byId('idMotivoRechazoSelect').isValid()){
			dijit.byId('idMotivoRechazoSelect').focus();
			alert("El siguiente campo muestra datos inválidos: Motivo. ");
			return false;
		}			
		  
		if (dijit.byId('idMotivoRechazoSelect').get('item') && dijit.byId('idMotivoRechazoSelect').get('item').label){
			dojo.byId('idMotivoRechazo').value = dijit.byId('idMotivoRechazoSelect').get('item').label[0];
		}
		
		if(dijit.byId('motivoRechazoText').get('value').length == 0){			
			alert("El siguiente campo muestra datos inválidos: Detalle. ");
			dijit.byId('motivoRechazoText').focus();
			return false;
		}
		
		document.autorizationForm.idMotivoRechazo.value = dojo.byId('idMotivoRechazo').value;
		document.autorizationForm.motivoRechazo.value   = dojo.byId('motivoRechazoText').value;
		
		dialogRechazo.hide();
		dialogDetalle.hide();
		
		submitToMethod('rechazarRegistro', idRegValidar);
	  }	  
	  
	  function closeWindowRechazo(){
		  dialogRechazo.hide();
	  }

	  function submitToMethod(method, idRegValidar){
		  dojo.byId('idRegValidar').value = idRegValidar;
		  dojo.byId('method2').value=method;
		  dojo.byId('autorizationForm').submit();

		  closeWindow();
	  }	
	
	  function limpiaForm(){
		  dojo.byId('idoffer').value = "";
		  dojo.byId('idPortal').value = "";
		  dojo.byId('email').value = "";
		  dijit.byId('idEntidadSelect').attr('value', 0);
// 		  dijit.byId('status').attr('value', 0);
// 		  dijit.byId('deleteRazon').attr('value', 0);
		  dijit.byId('dateInitAdd').attr('value', null);
		  dijit.byId('dateFinalAdd').attr('value', null);
		  dijit.byId('dateInitUpd').attr('value', null);
		  dijit.byId('dateFinalUpd').attr('value', null);
		 
		  dijit.byId('telefono').attr('value', null);
		  dijit.byId('idEmpresa').attr('value', null);		  
		  dijit.byId('contacto').attr('value', null);
		  dijit.byId('salario').attr('value', null);
		  dijit.byId('titulo').attr('value', null);
		  
       	dijit.byId('idEntidadSelect').set('value', null);
 		dijit.byId('idEntidadSelect').set('placeHolder', 'Seleccione el lugar');
 		
       	dijit.byId('idMunicipio').set('value', null);
 		dijit.byId('idMunicipio').set('placeHolder', 'Seleccione el municipio'); 		
 		
       	dijit.byId('salarioRango').set('value', null);
 		dijit.byId('salarioRango').set('placeHolder', 'Seleccione la condición');
	  }
	  

</script>
<noscript>Funciones de JavaScript desactivadas por navegador</noscript>

<html:messages id="errors">
	<span class="redFont Font80"><bean:write name="errors"/></span><br/>
</html:messages>

<html:messages id="messages" message="true">
	<span class="Font80"><bean:write name="messages"/></span><br/>
</html:messages>

<form name="FilterOfferForm" id="FilterOfferForm" method="post" action="filteroffer.do" dojoType="dijit.form.Form">
	<input type="hidden" name="method" id="method" value="filter"/>
	<input type="hidden" name="goToPageNumber" id="goToPageNumber" value="1" />

	<h3>Consulta de ofertas</h3>
	<div class="form_consulta">
		<div class="row">
	       	<div class="entero col-md-6">
	        	<label for="idPortal"><strong>ID del Portal del Empleo:</strong></label><br/>
				<input id="idPortal" name="idPortal" required="false" dojoType="dijit.form.ValidationTextBox"
					   invalidMessage="Dato no válido" maxlength="21" uppercase="true" clearOnClose="true" trim="true" value="${idPortal}"
					   style="font-family:Arial, Helvetica, sans-serif; color:#444444;" />
	       	</div>
	       	
			<div class="un_tercio col-md-6">
			   <strong><label for="idEmpresa">ID empresa:</label></strong><br>
			   <input id="idEmpresa" name="idEmpresa" maxlength="8" size="8" 	        	
					dojoType="dijit.form.ValidationTextBox" required="false" regExp="^[0-9]{1,8}$"
					invalidMessage="Dato Invalido" missingMessage="Dato requerido" trim="true"/>
		   	</div> 
	   	</div>      	
		<div class="row">
			<div class="un_tercio col-md-6">
				<label for="idoffer"><strong>Folio:&nbsp;&nbsp;</strong></label><br/>
	       		<input id="idoffer" name="idoffer" maxlength="50" size="50" dojoType="dijit.form.ValidationTextBox" required="false"
	       			regExp="^[0-9\s]{1,50}$" invalidMessage="Dato no válido" missingMessage="Dato requerido"
	       			trim="true" style="font-family:Arial, Helvetica, sans-serif; color:#444444;" value="${param.idoffer}"/>
	       	</div>
	       	<div class="entero col-md-6">
	            <label for="email"><strong>Correo electr&oacute;nico:&nbsp;&nbsp;</strong></label><br/>
	            <input id="email" name="email" required="false" dojoType="dijit.form.ValidationTextBox" size="65" maxlength="65" value="${email}"
	            		regExp="${regexpmail}"
	            		trim="true" style="font-family:Arial, Helvetica, sans-serif; color:#444444;" />
	       	</div> 
       	</div>
       	<div class="row">
	       	<div class="un_tercio col-md-6">
			   <strong><label for="contacto">Contacto de la oferta:</label></strong><br>
			   <input id="contacto" name="contacto" maxlength="150" size="150" 	        	
					dojoType="dijit.form.ValidationTextBox" required="false" regExp="^[a-zA-ZáéíóúÁÉÍÓÚñÑ\s]{3,150}$"
					invalidMessage="Dato Invalido" missingMessage="Dato requerido" trim="true"/>
			</div>
			<div class="un_tercio col-md-6">
			   <strong><label for="telefono">Teléfono:</label></strong><br/>
			   <input type="text" name="telefono" id="telefono" value="" dojoType="dijit.form.ValidationTextBox" 
			   		  required="false" regExp="^[0-9]{4,10}$" size="10" maxlength="10" trim="true" />             					
			</div>       
       	</div>
       	<div class="row">
			<div dojoType="dojo.data.ItemFileReadStore" jsId="entidadStore" urlPreventCache="true" clearOnClose="true" url="filteroffer.do?method=entidadesFederativasOpcion"></div>       	
			<div class="un_tercio col-md-6">
				<strong><label for="idEntidadSelect">Lugar de la vacante</label>:</strong><br>
				<select dojotype="dijit.form.FilteringSelect" store="entidadStore" id="idEntidadSelect" name="idEntidadSelect" required="false" autoComplete="true" placeHolder="Seleccione el lugar"></select>               			           			    					
			</div>
		
			<div dojoType="dojo.data.ItemFileReadStore" jsId="municipioStore" urlPreventCache="true" clearOnClose="true" url="filteroffer.do?method=consultarMunicipio&idEntidad=0"></div>		
			<div class="un_tercio col-md-6">
				<strong><label for="idMunicipio">Municipio:</label></strong><br/>	   
				<select dojotype="dijit.form.FilteringSelect"  id="idMunicipio" required="false" name="idMunicipio" store="municipioStore" placeHolder="Seleccione el municipio"></select>	   
			</div>
		</div>
		<div class="row mod-date">
			<div class="entero col-md-6">
				<label for="dateAdd"><strong>Fecha de alta&nbsp;&nbsp;</strong></label><br/>
				<label for="dateAddInit"><strong>Entre:&nbsp;&nbsp;</strong></label>
				
	       		<input type="text" name="dateInitAdd" id="dateInitAdd" value="${param.dateInitAdd}" dojoType="dijit.form.DateTextBox" maxlength="10" constraints={datePattern:'dd/MM/yyyy'} trim="true" required="false"/>
	       		<label for="dateFinalAdd"><strong>y&nbsp;&nbsp;</strong></label>
	       		<input type="text" name="dateFinalAdd" id="dateFinalAdd" value="${param.dateFinalAdd}" dojoType="dijit.form.DateTextBox" maxlength="10" constraints={datePattern:'dd/MM/yyyy'} trim="true" required="false"/>
		   	</div>
		   	<div class="entero col-md-6">
				<label for="dateUpd"><strong>Fecha de modificación&nbsp;&nbsp;</strong></label><br/>
				<label for="dateInitUpd"><strong>Entre:&nbsp;&nbsp;</strong></label>
	       		<input type="text" name="dateInitUpd" id="dateInitUpd" value="${param.dateInitUpd}" dojoType="dijit.form.DateTextBox" maxlength="10" constraints={datePattern:'dd/MM/yyyy'} trim="true" required="false"/>
	       		<label for="dateFinalUpd"><strong>y&nbsp;&nbsp;</strong></label>
	       		<input type="text" name="dateFinalUpd" id="dateFinalUpd" value="${param.dateFinalUpd}" dojoType="dijit.form.DateTextBox" maxlength="10" constraints={datePattern:'dd/MM/yyyy'} trim="true" required="false"/>
		   	</div>
	   	</div>
	   			
	   <div class="row">
	   	   <div dojoType="dojo.data.ItemFileReadStore" jsId="rangoSalarioStore" urlPreventCache="true" clearOnClose="true" url="filteroffer.do?method=consultarRangos"></div>
	   	   <div class="col-md-8">	   
			   <strong><label for="salarioRango">Salario:&nbsp;&nbsp;</label></strong><br/>	   
			   <select dojotype="dijit.form.FilteringSelect"  id="salarioRango" name ="salarioRango" required="false" store="rangoSalarioStore" placeHolder="Seleccione la condicion"></select>
			   <input type="text" name="salario" id="salario" value="" dojoType="dijit.form.ValidationTextBox" required="false" regExp="^[0-9]{1,5}$" size="10" maxlength="10" trim="true" />
		   </div>	   
	   </div>
	   <br>
       <div class="row">
			<div class="col-md-6">
				<strong><label for="titulo">Título de la oferta de empleo:</label></strong><br>
				<input id="titulo" name="titulo" maxlength="75" size="75" 	        	
				dojoType="dijit.form.ValidationTextBox" required="false" regExp="^[a-zA-ZáéíóúÁÉÍÓÚñÑ\s]{4,75}$"
				invalidMessage="Dato Invalido" missingMessage="Dato requerido" trim="true"/>
			</div>	    
       </div>
	   	
		<div class="row">
			<div class="col-md-6"> 
				<strong><label for="status">Estatus de Oferta</label>:</strong><br>
				<select id="status" name="status" dojoType="dijit.form.FilteringSelect" style="width:250px;">
					<option value="0">SELECCIONE UNA OPCIÓN</option>
					<option value="1">ACTIVA</option>
					<option value="9">INACTIVA POR EMPRESA MODIFICADA</option>
					<option value="10">CANCELADA</option>
					<option value="11">EN EDICION</option>
					<option value="12">PENDIENTE PUBLICAR</option>
					<option value="13">ELIMINADA POR EMPRESA</option>
					<option value="14">ELIMINADA POR PUBLICADOR</option>
					<option value="15">ELIMINADA POR VIGENCIA</option>
					<option value="26">ASIGNADA PUBLICADOR</option>
					<option value="27">EDICION PUBLICADOR</option>
					<option value="28">ELIMINADA POR EMPRESA FRAUDE</option>
					<option value="50">ACTIVO SOLO PARA ABRIENDO ESPACIOS</option>
				</select>             
			</div>  			           			    					
		</div> 
<!-- 		<span class="entero"> -->
<!-- 			<strong><label for="deleteRazon">Motivo de eliminación</label>:</strong> -->
<!-- 			<select id="deleteRazon" name="deleteRazon" dojoType="dijit.form.FilteringSelect" style="width:260px;"> -->
<!-- 				<option value="0">SELECCIONE UNA OPCIÓN</option> -->
<!-- 				<option value="20">EMPRESA ELIMINADA</option> -->
<!-- 				<option value="21">COMISION, CAMBACEO, AMBULANTE</option> -->
<!-- 				<option value="22">DATOS INCOMPLETOS</option> -->
<!-- 				<option value="23">DATOS INCONSISTENTES</option> -->
<!-- 				<option value="24">DATOS IRREALES</option> -->
<!-- 				<option value="25">DISCRIMINATORIO</option> -->
<!-- 				<option value="26">DUPLICADO</option> -->
<!-- 				<option value="27">ENTRETENIMIENTO ADULTOS</option> -->
<!-- 				<option value="28">ESPECIFICA MAS DE UN PUESTO</option> -->
<!-- 				<option value="29">EVENTUAL, MENOR A 1 MES DE TRABAJO</option> -->
<!-- 				<option value="30">FRAUDE</option> -->
<!-- 				<option value="31">MAL ESPECIFICADA</option> -->
<!-- 				<option value="32">MULTINIVEL</option> -->
<!-- 				<option value="33">NO ES OFERTA DE EMPLEO</option> -->
<!-- 				<option value="34">SIRVIENTAS</option> -->
<!-- 				<option value="35">TRABAJO EN CASA</option> -->
<!-- 				<option value="36">TRABAJO EN EXTRANJERO</option> -->
<!-- 				<option value="37">OTRO</option> -->
<!-- 			</select>               			           			    					 -->
<!-- 		</span> -->
		<div class="entero" style="text-align:center;">
			<input type="button" id="btnBuscar" value="Buscar"  class="boton btnPE" onclick="javascript:doSubmit('filter');" />
			<input type="button" id="btnReset" value="Limpiar" class="boton btnPE" onclick="javascript:limpiaForm();"/>
		</div>
		<div id="table"></div>
	</div>
	<div id="message"></div>
</form>
<c:if test="${empty requestScope['init']}">
	<script>
		doSubmitPager('page');
	</script>
</c:if>

<form name="autorizationForm" id="autorizationForm" method="post" action="autorization.do" dojoType="dijit.form.Form">
	<input type="hidden" name="method" id="method2" value="init"/> <!-- indica el metodo a invocar -->
	<input type="hidden" name="desdeBusquedaOfertas" id="desdeBusquedaOfertas" value="true"/>	
<!-- 	<input type="hidden" name="idEmpresa" id="idEmpresa" value="0"/> -->
	<input type="hidden" name="idRegValidar" id="idRegValidar" value="0"/>
	<input type="hidden" name="idOfertaEmpleo" id="idOfertaEmpleo" value="0"/>
	<input type="hidden" name="idMotivoRechazo" id="idMotivoRechazo" value="0"/>
	<input type="hidden" name="motivoRechazo" id="motivoRechazo" value=""/>

	<input type="hidden" name="goToPageNumber" id="goToPageNumber" value="1" />

</form>




<script type="text/javascript">

	dojo.addOnLoad(function() {
	
		// salario
		if(dijit.byId('salarioRango'))
			dijit.byId('salario').disabled=true;
		
		dojo.connect(dijit.byId("salarioRango"), "onChange", function() {
			var salarioRango = dijit.byId('salarioRango').get('value');
			
			if (salarioRango.length == 0){
				dijit.byId('salario').disabled=true;
				dijit.byId('salario').set('value', null);
			}
			else{
				dijit.byId('salario').disabled=false;
			}

		});
		
		// entidad y municipio
		if(dijit.byId('idMunicipio'))
			dijit.byId('idMunicipio').disabled=true;
		
		dojo.connect(dijit.byId('idEntidadSelect'), 'onChange', function() {
			var vIdEntidad = dijit.byId('idEntidadSelect').get('value');

         	if(vIdEntidad > 0){         	
	         	dijit.byId('idMunicipio').disabled=false;	         		         	
	         	municipioStore.url = '${context}filteroffer.do?method=consultarMunicipio' + '&' + 'idEntidad='+ vIdEntidad;
	         	municipioStore.close();
	         	dijit.byId('idMunicipio').set('value', null);
         		dijit.byId('idMunicipio').set('placeHolder', 'Seleccione el municipio');
         	} else {         		
         		dijit.byId('idMunicipio').set('placeHolder', 'Seleccione el municipio');
	         	municipioStore.url = '${context}filteroffer.do?method=consultarMunicipio' + '&' + 'idEntidad=0';	         	
	         	municipioStore.close();	        
	         	dijit.byId('idMunicipio').disabled = true;
         		dijit.byId('idMunicipio').set('value', null);	         	
         	}
		});
		
		
	});
</script>




