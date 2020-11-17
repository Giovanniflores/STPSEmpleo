<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="mx.gob.stps.portal.core.infra.utils.Constantes"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


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
	#FilterOfferForm .row {
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
	  dojo.xhrPost({url: 'filteroffer.do', form:'FilterOfferForm', timeout:180000, 
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
		var nombreEmpresa = getAnyElementValueById('nombreEmpresa');
		var idPortal = getAnyElementValueById('idPortal');
		var idoffer = getAnyElementValueById('idoffer');
		
		if (nombreEmpresa.length<1 && idoffer.length<1 && idPortal.length<1 ) {
			vMessage = vMessage + '\nDebe proporcionar un filtro para realizar la busqueda.';
			vForm = false;
		}

		if (vMessage.length>0) {
			alert(vMessage);
		}
		
		return vForm;	
	}
/*	function doSubmitPagina(pagina) {
		dojo.byId('method').value = "goToPage";
		dojo.byId('goToPageNumber').value = pagina;
		dojo.xhrPost({url: 'filteroffer.do', form:'FilterOfferForm', timeout:180000, 
			load: function(data) {
				dojo.byId('table').innerHTML=data;
		}});
	}*/
	
	function doSubmitPagina(pagina, dif) {
    		dojo.xhrPost({url: 'filteroffer.do?method=goToPage&tablaPager=_OFEPUBLISH&goToPageNumber='+pagina, timeout:180000, 
				load: function(data){
    	 			dojo.byId('table').innerHTML=data;
    	 			
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
	
	function showWindowOffer(idRegValidar){
		dojo.byId('idRegValidar').value = idRegValidar;
		/**dialogDetalle = new dijit.Dialog({
		      title: 'Detalle de registro',
		      href: '${pageContext.request.contextPath}/autorization.do?method=detalleRegistro&idRegValidar='+idRegValidar+'&desdeBusquedaOfertas=true',
		      style: "width:800px; height:720px;",
		      showTitle: false, draggable : false, closable : false});
		dojo.style(dialogDetalle.closeButtonNode,"display","none");	
		dialogDetalle.show();*/
		
		document.location='${pageContext.request.contextPath}/autorization.do?method=detalleRegistro&idRegValidar='+idRegValidar+'&desdeBusquedaOfertas=true'+'&desdeAdminPendientesPublicar=true';
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
		  dojo.byId('nombreEmpresa').value = "";
		   
	  }
	  
	  function doSubmitPublicar(){
		  var registros = new Array();
		  var index=0;
		  if(document.getElementById("idRegistro_1")){
		  if(document.getElementById("idRegistro_1").checked){
			  registros[index]=document.getElementById("idRegistro_1").value;
			  index++;
		  	}
		  }
		  if(document.getElementById("idRegistro_2")){
		  if(document.getElementById("idRegistro_2").checked){
			  registros[index]=document.getElementById("idRegistro_2").value;
			  index++;
		  	}
		  }
		  if(document.getElementById("idRegistro_3")){
		  if(document.getElementById("idRegistro_3").checked){
			  registros[index]=document.getElementById("idRegistro_3").value;
			  index++;
		  	}
		  }
		  if(document.getElementById("idRegistro_4")){
		  if(document.getElementById("idRegistro_4").checked){
			  registros[index]=document.getElementById("idRegistro_4").value;
			  index++;
		  	}
		  }
		  if(document.getElementById("idRegistro_5")){
		  if(document.getElementById("idRegistro_5").checked){
			  registros[index]=document.getElementById("idRegistro_5").value;
			  index++;
		  	}
		  }
		  
		 if(registros==""){
			 	alert('Debe seleccionar al menos un registro para publicar');
			 	return false;
		 }
		 else{
			 dojo.byId('method2').value='publicaRegistrosAdmin';
			  dojo.byId('idRegistroValidar').value = registros;
			  dojo.byId('autorizationForm').action='filteroffer.do';
			 dojo.byId('autorizationForm').submit();
			 
			 
		 } 
	  }
	  
	
</script>
<noscript>Funciones de JavaScript desactivadas por navegador</noscript>

<html:messages id="errors">
	<span class="redFont Font80"><bean:write name="errors"/></span><br/>
</html:messages>

<html:messages id="messages" message="true">
	<span class="Font80"><bean:write name="messages"/></span><br/>
</html:messages>

<div class="form_consulta">
<form name="FilterOfferForm" id="FilterOfferForm" method="post" action="filteroffer.do" dojoType="dijit.form.Form">
	<input type="hidden" name="method" id="method" value="filter"/>
	<input type="hidden" name="goToPageNumber" id="goToPageNumber" value="1" />
	

	<h3>Publicación de ofertas</h3>
	<div>
       	<div class="row">
        	<label for="idPortal"><strong>ID del Portal del Empleo:</strong></label><br/>
			<input id="idPortal" name="idPortal" required="false" dojoType="dijit.form.ValidationTextBox"
				   invalidMessage="Dato no válido" maxlength="21" uppercase="true" clearOnClose="true" trim="true" value="${idPortal}"
				   style="font-family:Arial, Helvetica, sans-serif; color:#444444;width:300px;" />
       	</div>
       	<div class="row">
            <label for="nombreEmpresa"><strong>Nombre de empresa:&nbsp;&nbsp;</strong></label><br/>
            <input id=nombreEmpresa name="nombreEmpresa" required="false" dojoType="dijit.form.ValidationTextBox" size="65" maxlength="65" value="${nombreEmpresa}"
            trim="true" style="font-family:Arial, Helvetica, sans-serif; color:#444444;width:300px;" />
       	</div>
		<div class="row">
			<label for="idoffer"><strong>Folio de la oferta:&nbsp;&nbsp;</strong></label><br/>
       		<input id="idoffer" name="idoffer" maxlength="50" size="50" dojoType="dijit.form.ValidationTextBox" required="false"
       			regExp="^[0-9\s]{1,50}$" invalidMessage="Dato no válido" missingMessage="Dato requerido"
       			trim="true" style="font-family:Arial, Helvetica, sans-serif; color:#444444;width:300px;" value="${param.idoffer}"/>
       			
       			
       	</div>
       	
		<div class="entero">
			<input type="button" id="btnBuscar" value="Buscar"  class="boton" onclick="javascript:doSubmit('filterAdminPublisher');" />
			<input type="button" id="btnReset" value="Limpiar" class="boton" onclick="javascript:limpiaForm();"/>
		</div>
		<div id="table"></div>
	</div>
	<div id="message"></div>
</form>
</div>
<c:if test="${empty requestScope['init']}">
	<script>
		doSubmitPager('page');
	</script>
</c:if>

<form name="autorizationForm" id="autorizationForm" method="post" action="autorization.do" dojoType="dijit.form.Form">
	<input type="hidden" name="method" id="method2" value="init"/> <!-- indica el metodo a invocar -->
	<input type="hidden" name="desdeBusquedaOfertas" id="desdeBusquedaOfertas" value="true"/>	
	<input type="hidden" name="idEmpresa" id="idEmpresa" value="0"/>
	<input type="hidden" name="idRegValidar" id="idRegValidar" value="0"/>
	<input type="hidden" name="idOfertaEmpleo" id="idOfertaEmpleo" value="0"/>
	<input type="hidden" name="idMotivoRechazo" id="idMotivoRechazo" value="0"/>
	<input type="hidden" name="motivoRechazo" id="motivoRechazo" value=""/>
	<input type="hidden" name="idRegistroValidar" id="idRegistroValidar" value="" />
	<input type="hidden" name="goToPageNumber" id="goToPageNumber" value="1" />

</form>

