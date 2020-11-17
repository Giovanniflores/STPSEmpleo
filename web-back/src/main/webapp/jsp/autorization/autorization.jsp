<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%String context = request.getContextPath() +"/";%>

<style type="text/css">
	#dialogDetalle_underlay { background-color:gray; }
	#dialogEmpFrau_underlay { background-color:green; }
	
	.redFont{ color: #FF0000; }
	.Font80{ font-size: 80%; }
</style>

<script type="text/javascript">
dojo.require("dijit.dijit"); // loads the optimized dijit layer
dojo.require("dijit.form.Form");
dojo.require("dijit.Dialog");
dojo.require("dijit.form.Button");
dojo.require("dijit.form.ComboBox");
dojo.require("dojo.data.ItemFileReadStore");
dojo.require("dijit.form.Textarea");
	
  var dialogDetalle;
  var dialogEmpFrau;
  var dialogRechazo;
  
  function showWindow(idRegValidar, idTipoRegistro){
	//alert('idRegValidar:' + idRegValidar);
	if (idTipoRegistro == 1 || idTipoRegistro == 2){ // EMPRESA, OFERTA

		dialogDetalle = new dijit.Dialog({
	        title: 'Detalle de registro',
	        href: '${context}autorization.do?method=detalleRegistro&idRegValidar='+ idRegValidar,
	        style: "width:800px; height:720px;",
	        showTitle: false, draggable : false, closable : false
	    });

	} else if (idTipoRegistro == 3 || idTipoRegistro == 4){ // TESTIMONIO, VIDEO

		dialogDetalle = new dijit.Dialog({
	        title: 'Detalle de registro',
	        href: '${context}autorization.do?method=detalleRegistro&idRegValidar='+ idRegValidar,
	        style: "width:500px; height:400px;",
	        showTitle: false, draggable : false, closable : false
	    });		
	}

	dojo.style(dialogDetalle.closeButtonNode,"display","none");	
	dialogDetalle.show();
  }

  function closeWindow(){
	  if (dialogDetalle)
	  	dialogDetalle.hide();
  }
  
  function showWindowEmpFraudulenta(idEmpresa){

	  dialogEmpFrau = new dijit.Dialog({
	        title: 'Empresa Fraudulenta',
	        href: '${context}autorization.do?method=detalleEmpFraudulenta&idEmpresa='+ idEmpresa,
	        style: "width:600px; height:580px;",
	        showTitle: false, draggable : true, closable : false
	  });

	  dojo.style(dialogEmpFrau.closeButtonNode,"display","none");
	  dialogEmpFrau.show();
  }
  
  function closeWindowEmpFraudulenta(){
	  dialogEmpFrau.hide();
  }
  
  function autorizarRegistro(idRegValidar){
	  submitToMethod('autorizarRegistro', idRegValidar);
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
	
	  if(dijit.byId('motivoRechazoText').value.length<0){			
			alert("El siguiente campo muestra datos inválidos: Detalle. ");								
		return false;
		}		
	

	document.autorizationForm.idMotivoRechazo.value = dojo.byId('idMotivoRechazo').value;
	document.autorizationForm.motivoRechazo.value   = dojo.byId('motivoRechazoText').value;
	
	closeWindowRechazo();

	submitToMethod('rechazarRegistro', idRegValidar);
  }

  function showWindowMotivoRechazo(idRegValidar){

	  dialogRechazo = new dijit.Dialog({
	        title: 'Motivo de Rechazo',
	        href: '${context}autorization.do?method=motivoRechazoDetalle',
	        style: "width:280px; height:260px;",
	        showTitle: false, draggable : true, closable : false
	  });
	  
	  dojo.style(dialogRechazo.closeButtonNode,"display","none");
	  dialogRechazo.show();
  }
  
  function closeWindowRechazo(){
	  dialogRechazo.hide();
  }
  
  function cancelarValidacion(idRegValidar){
	  submitToMethod('cancelarValidacionRegistro', idRegValidar);
  }

  function submitToMethod(method, idRegValidar){
	  dojo.byId('idRegValidar').value = idRegValidar;
	  dojo.byId('method').value=method;
	  dojo.byId('autorizationForm').submit();

	  //closeWindow();
  }
 
  function doSubmitPager(method){
		
	  dojo.byId('method').value = method;

	  dojo.xhrPost({url: 'autorization.do', form:'autorizationForm', timeout:180000, 
				  load: function(data){
					    dojo.byId('tabla').innerHTML=data;
				  }});
  }

  function editarEmpresa(idRegValidar, idEmpresa){
	  dojo.byId('method').value='editarEmpresa';	  
	  dojo.byId('idRegValidar').value = idRegValidar;
	  dojo.byId('idEmpresa').value = idEmpresa;
	  dojo.byId('autorizationForm').submit();
  }

  function editarOfertaEmpleo(idRegValidar, idEmpresa, idOfertaEmpleo){
	  dojo.byId('method').value='editarOfertaEmpleo';
	  dojo.byId('idRegValidar').value = idRegValidar;
	  dojo.byId('idEmpresa').value = idEmpresa;
	  dojo.byId('idOfertaEmpleo').value = idOfertaEmpleo;
	  dojo.byId('autorizationForm').submit();
  }

  function doSubmitPagina(pagina){
	  dojo.byId('method').value = "goToPage";
	  dojo.byId('goToPageNumber').value = pagina;
	  dojo.xhrPost({url: 'autorization.do', form:'autorizationForm', timeout:180000, 
		  load: function(data){
			    dojo.byId('tabla').innerHTML=data;
		  }});
  }

</script>
<noscript>Funciones de JavaScript desactivadas por navegador</noscript>

<html:messages id="errors">
	<span class="redFont Font80"><bean:write name="errors"/></span><br/>
</html:messages>
<html:messages id="messages" message="true">
	<span class="Font80"><bean:write name="messages"/></span><br/>
</html:messages>

<h3>Validación de registros</h3>

<div id="tabla"></div>

<form name="autorizationForm" id="autorizationForm" method="post" action="autorization.do" dojoType="dijit.form.Form">
	<input type="hidden" name="method" id="method" value="init"/> <!-- indica el metodo a invocar -->
	<input type="hidden" name="idEmpresa" id="idEmpresa" value="0"/>
	<input type="hidden" name="idRegValidar" id="idRegValidar" value="0"/>
	<input type="hidden" name="idOfertaEmpleo" id="idOfertaEmpleo" value="0"/>
	<input type="hidden" name="idMotivoRechazo" id="idMotivoRechazo" value="0"/>
	<input type="hidden" name="motivoRechazo" id="motivoRechazo" value=""/>

	<input type="hidden" name="goToPageNumber" id="goToPageNumber" value="1" />

	<%--
	<p align="center" >
		<a href="javascript:doSubmitPager('prev')">&lt;&lt;&nbsp;Anterior</a>
		&nbsp;&nbsp;&nbsp;&nbsp;
		<a href="javascript:doSubmitPager('next')">Siguiente&nbsp;&gt;&gt;</a>
	</p>
	--%>
</form>

<script>
	doSubmitPager('page');
</script>