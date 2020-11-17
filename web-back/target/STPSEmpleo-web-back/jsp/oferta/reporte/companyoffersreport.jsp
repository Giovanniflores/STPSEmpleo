<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="mx.gob.stps.portal.core.infra.utils.Constantes"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript">
    dojo.require("dijit.dijit"); // loads the optimized dijit layer
	dojo.require("dijit.form.Form");    
    dojo.require("dijit._Calendar");
    dojo.require("dijit.form.Textarea");
    dojo.require("dijit.form.DateTextBox");    
    dojo.require("dijit.form.NumberTextBox");
    dojo.require("dijit.form.ValidationTextBox");
    dojo.require("dijit.form.TextBox");
	dojo.require("dijit.Dialog");
    dojo.require("dijit.form.Button");
	dojo.require("dojo.data.ItemFileReadStore");
	dojo.require("dijit.form.FilteringSelect");    
</script>
<style type="text/css">
	.redFont{
		color: #FF0000;
	}
	.Font80{
		font-size: 80%;
	}
</style>
<script type="text/javascript">
function doSubmit(method) {
	if(checkOtherFields()) {
		document.reporteOfertasEmpresaForm.method.value=method;
		document.reporteOfertasEmpresaForm.submit();			
	}
}
function doSubmitAction() {
	document.reporteOfertasEmpresaForm.method.value='excel';
	document.reporteOfertasEmpresaForm.submit();			
}
function getAnyElementValueById(elementId){
	var vElement = dijit.byId(elementId).value;
	if(vElement==undefined || vElement==''){
		vElement = document.getElementById(elementId).value;
	}
	return vElement;
}
function checkOtherFields(){
	var vForm = true;
	var vMessage = '';
	
	if(dijit.byId('filterIdEstatus'))
		dojo.byId('estatusAdd').value = dijit.byId('filterIdEstatus').get('value');		
	
	if(!validarFechas()){return;}	
	
	if (vMessage.length>0) {
		alert(vMessage);
	}
	return vForm;	
}



function doSubmitPager(method){
	  dojo.byId('method').value = method;	 
	  dojo.xhrPost({url: 'reporteOfertasEmpresa.do', form:'reporteOfertasEmpresaForm', timeout:180000, 
	  	load: function(data) {
	  		dojo.byId('table').innerHTML=data;
		}});  
	}
	
function validarFechas() {
	var valido = true;
	var dateIni = dijit.byId('dateInitAdd');
	var dateFin = dijit.byId('dateFinalAdd');
	if(dijit.byId('dateInitAdd').get('value')==null && dijit.byId('dateFinalAdd').get('value')!=null){
		alert('Debe seleccionar la fecha de inicio  del rango'); 
		valido = false;
	}	
	if(dijit.byId('dateInitAdd').get('value')!=null && dijit.byId('dateFinalAdd').get('value')==null){
		alert('Debe seleccionar la fecha de fin  del rango'); 
		valido = false;
	}
	if(dijit.byId('dateInitAdd').get('value')!=null && dijit.byId('dateFinalAdd').get('value')!=null){
		var dif = dojo.date.compare(dateFin.get('value'), dateIni.get('value'), 'date');
		valido = dif >= 0;	
		if (!valido) {
			alert('La fecha inicial debe ser anterior a la fecha final');		
			dateIni.focus();
		}		
	}
	return valido;
}	
	
function doSubmitPagina(pagina) {
	dojo.byId('method').value = "goToPage";
	dojo.byId('goToPageNumber').value = pagina;
	dojo.xhrPost({url: 'reporteOfertasEmpresa.do', form:'reporteOfertasEmpresaForm', timeout:180000, 
		load: function(data) {
			dojo.byId('table').innerHTML=data;
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

<div dojoType="dojo.data.ItemFileReadStore" jsId="estatusValidosStore" url="${context}reporteOfertasEmpresa.do?method=estatusValidos"></div>

<form name="reporteOfertasEmpresaForm" id="reporteOfertasEmpresaForm" method="post" action="reporteOfertasEmpresa.do" dojoType="dijit.form.Form">
	<input type="hidden" name="method" id="method" value="filter"/>
	<input type="hidden" name="goToPageNumber" id="goToPageNumber" value="1" />
	<input type="hidden" name="estatusAdd" id="estatusAdd"  value="" dojoType="dijit.form.TextBox"/>
	
	<div class="tab_block">
		<div align="left" style="display:block;" id="returnME">
			<a class="expand" href="javascript:location.replace('<c:url value="/miEspacioEmpresas.do"/>');">
				<strong>Ir al inicio de Mi espacio</strong>
			</a>
		</div>
		<div class="Tab_espacio">
			<h3>Administrar mis ofertas de empleo</h3>
			<div class="subTab">
				<ul>
					<li>
						<li><a href="<c:url value="/dondePublicar.do?method=init"/>">Crear oferta de empleo</a></li>
					</li>
					<li>
						<a href="<c:url value="/OfertaNavegacion.do?method=init"/>">Mis ofertas de empleo</a>
					</li>
					<li>
						<li><a href="<c:url value="/RecuperaOfertas.do?method=init"/>">Utiliza oferta como plantilla</a></li>
					</li>
					<li class="curSubTab">
						<span>Reporte de ofertas de empleo</span>
					</li>
				</ul>
				<div class="clearfix"></div>
			</div>
		</div>
	</div>
	<fieldset class="datos_contacto bloque">
		<legend>Reporte de ofertas de empleo</legend>
		<div class="grid1_3 margin_top_30">
			<label for="filterIdEstatus">Estatus de la(s) oferta(s)</label>
			<select id="filterIdEstatus" name="filterIdEstatus"
			        required="false" missingMessage="estatus de la oferta"
			        store="estatusValidosStore" dojotype="dijit.form.FilteringSelect">
			</select>			
		</div>
		<div class="grid1_3 margin_top_30">
			<div class="labeled">Fecha de alta de ofertas</div>
			<div class="fl margin-r_20">
				<label class="fw_no_bold" for="dateAddInit">Entre:</label>
       			<input type="text" style="width: 133px" name="dateInitAdd" id="dateInitAdd" value="" dojoType="dijit.form.DateTextBox" maxlength="10" constraints={datePattern:'dd/MM/yyyy'} trim="true" required="false"/>
       		</div>
       		<div class="fl">
	       		<label class="fw_no_bold" for="dateFinalAdd">y</label>
	       		<input type="text" style="width: 133px" name="dateFinalAdd" id="dateFinalAdd" value="" dojoType="dijit.form.DateTextBox" maxlength="10" constraints={datePattern:'dd/MM/yyyy'} trim="true" required="false"/>
       		</div>
       		<div class="clearfix"></div>
	   	</div>	
		<div id="table"></div>
	</fieldset>
	<div class="form_nav">
		<input type="button" id="btnBuscar" value="Buscar"  class="boton" onclick="doSubmit('filter');" />
		<input type="reset" id="btnReset" value="Restablecer" class="boton" />
	</div>
	<div id="message"></div>
</form>
<script>
	doSubmitPager('page');
</script>
