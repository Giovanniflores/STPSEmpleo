<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<c:set var="regexpmotivo">[a-zA-Z]{4}\d{6}[hmHM][a-zA-Z]{2}[B-DF-HJ-NP-TV-Z|b-df-hj-np-tv-z]{3}[a-zA-Z0-9][0-9]</c:set>
<style type="text/css">
	.oculto {
	   position: absolute !important;
	   top: -9999px !important;
	   left: -9999px !important;
	}
</style>
<c:if test="{not AdministracionNoPostulacionesForm.prerrequisitos}`">
	<script type="text/javascript">
	  dojo.addOnLoad(function() {
					var html = 'No cumple con los prerrequisitos para entrar al m&oacute;dulo de Administraci&oacute;n de Ofertas No Postuladas.'+
					   '<br/><br/>'+
					   '<input id="btnCerrar" name="btnCerrar" class="boton_naranja" type="button" value="Cerrar" onclick="dialogAviso.hide();"/>';	  
		mensajesModal(html,'width:500px;','height:450px;');
	  });
	</script>
</c:if>
<c:if test="${OFFER_NO_POST_LIST_SIZE <= 0}">
	<script type="text/javascript">
		dojo.addOnLoad(function() {
					var html = 'No se encontraron ofertas disponibles.'+
					   '<br/><br/>'+
					   '<input id="btnCerrar" name="btnCerrar" class="boton_naranja" type="button" value="Cerrar" onclick="dialogAviso.hide();"/>';
			mensajesModal(html,'width:200px;','height:250px;');
		});
	</script>
</c:if>
<c:if test="${not empty REGISTRO_MSG}">
	<script type="text/javascript">
		dojo.addOnLoad(function() {
			var html = '${REGISTRO_MSG}'+
					   '<br/><br/>'+
					   '<input id="btnCerrar" name="btnCerrar" class="boton_naranja" type="button" value="Cerrar" onclick="dialogAviso.hide();"/>';
			mensajesModal(html,'width:400px;','height:150px;');
		});
	</script>
</c:if>
<script type="text/javascript">
dojo.require("dijit.dijit"); // loads the optimized dijit layer
dojo.require("dijit.form.Form");
dojo.require("dijit.Dialog");
dojo.require("dijit.form.TextBox");
dojo.require("dijit.form.SimpleTextarea");
dojo.require("dijit.form.ValidationTextBox");
dojo.require("dijit.form.FilteringSelect");
dojo.require("dojo.data.ItemFileReadStore");
var dialogAviso;
var dialogRegNoPost;
var OTROS_MOTIVOS = 11;
  function mensajesModal(msg, width, height){
  	dialogAviso = new dijit.Dialog({
  		title: 'Mensaje',
		content: msg,
  		style: width + height + 'background: #FFF',
  		showTitle: false, draggable : false, closable : true
  	});
  	dialogAviso.show();
  }
  function openDialogRegMotivos(idOferta){
  	dojo.byId('idOfertaEmpleo').value = idOferta;
  	dijit.byId('regMotivNoPost').show();
  	tipoMotivo();
  }
  function closeDialogRegMotivos(){
  	dojo.byId('otroMotivo').value = '';
  	dijit.byId('motivos').reset();
  	dijit.byId('regMotivNoPost').hide();
  }
  function doSubmitPager(method){
	  dojo.byId('method').value = method;
	  dojo.xhrPost({url: 'adminNoPostulacionesCandidato.do', form:'administracionNoPostulacionesForm', timeout:180000, 
				  load: function(data){
					    dojo.byId('tabla').innerHTML=data;
				  }});
  }
  function doSubmitPagina(pagina){
	  dojo.byId('method').value = "goToPage";
	  dojo.byId('goToPageNumber').value = pagina;
	  dojo.xhrPost({url: 'adminNoPostulacionesCandidato.do', form:'administracionNoPostulacionesForm', timeout:180000, 
		  load: function(data){
			    dojo.byId('tabla').innerHTML=data;
		  }});
  }
  function tipoMotivo(){
  	var elem = dijit.byId('motivos').get('value');
  	if(elem != 11)
  		dojo.byId('otroMotivo').disabled = true;
  	 else
  		dojo.byId('otroMotivo').disabled = false;
  }
  function registroNoPostulaciones(){
  	if(dijit.byId('motivos').get('value') == 11 && descripcionMinimo5(dojo.byId('otroMotivo')) == 0)
  		return;
  	dojo.byId('method').value="registrar";
  	dojo.byId('idMotivo').value = dijit.byId('motivos').get('value');
  	
  	if(dojo.byId('idMotivo').value == OTROS_MOTIVOS)
  		dojo.byId('motivoDesc').value = dojo.byId('otroMotivo').value;
  	else
  		dojo.byId('motivoDesc').value = dijit.byId('motivos').get('name');
	closeDialogRegMotivos();
	dojo.byId('administracionNoPostulacionesForm').submit();
  }
   function caracteresValidos(e){
	    var strChar = dijit.byId(e).get('value');
    	var patron = /^[0-9A-Za-z\\d\\s\\.\\,\\;\\:\\-\\'áéíóúÁÉÍÓÚñÑüÜ ]{0,150}$/;
    	if (!patron.test(strChar)){
    		mensajesModal('<h3>caracter <strong>' + strChar.substring(strChar.length - 1) +'</strong> inv&aacute;lido, no se permiten caracteres especiales.</h3>','width:500px;','height:100px;');
    		dojo.byId('otroMotivo').value = strChar.replace(strChar.substring(strChar.length - 1), '');
    	}
    }
    function descripcionMinimo5(e){//minimo 5 caracteres
    	if(e.value.length == 0){
    		mensajesModal('<h3>El campo <strong>Especifique</strong> es requerido.</h3>','width:500px;','height:100px;');
    		return 0;
    	} else if(e.value.length < 5){
    		mensajesModal('<h3>El número de caracteres de la descripción debe ser mayor a 5.</h3>','width:500px;','height:100px;');
    		return 0;
    	} else
    		return 1;
    }
    function orderBy(orden,tipocolumna){/* Ordena tabla por columna */
        dojo.xhrPost({url: 'adminNoPostulacionesCandidato.do?method=ordenarMisOfertasNoPostuladas&tipoOrden=' + orden + '&tipoColumna=' + tipocolumna, timeout:180000,
            load: function(dataCand){
                dojo.byId('tabla').innerHTML=dataCand;
                if(tipocolumna == 'puesto') {
                    if (orden == 'asc') {
                        document.getElementById("puesto_orden_asc").style.display = 'none';
                        document.getElementById("puesto_orden_desc").style.display = 'inline';
                    } else if (orden == 'desc') {
                        document.getElementById("puesto_orden_asc").style.display = 'inline';
                        document.getElementById("puesto_orden_desc").style.display = 'none';
                    }
                } else if(tipocolumna == 'empresa'){
                    if (orden == 'asc') {
                        document.getElementById("empresa_orden_asc").style.display = 'none';
                        document.getElementById("empresa_orden_desc").style.display = 'inline';
                    } else if (orden == 'desc') {
                        document.getElementById("empresa_orden_asc").style.display = 'inline';
                        document.getElementById("empresa_orden_desc").style.display = 'none';
                    }
                } else if(tipocolumna == 'ubicacion'){
                    if (orden == 'asc') {
                        document.getElementById("ubicacion_orden_asc").style.display = 'none';
                        document.getElementById("ubicacion_orden_desc").style.display = 'inline';
                    } else if (orden == 'desc') {
                        document.getElementById("ubicacion_orden_asc").style.display = 'inline';
                        document.getElementById("ubicacion_orden_desc").style.display = 'none';
                    }
                } else if(tipocolumna == 'salario'){
                    if (orden == 'asc') {
                        document.getElementById("salario_mensual_asc").style.display = 'none';
                        document.getElementById("salario_mensual_desc").style.display = 'inline';
                    } else if (orden == 'desc') {
                        document.getElementById("salario_mensual_asc").style.display = 'inline';
                        document.getElementById("salario_mensual_desc").style.display = 'none';
                    }
                } else if(tipocolumna == 'compatibilidad'){
                    if (orden == 'asc') {
                        document.getElementById("compatibilidad_asc").style.display = 'none';
                        document.getElementById("compatibilidad_desc").style.display = 'inline';
                    } else if (orden == 'desc') {
                        document.getElementById("compatibilidad_asc").style.display = 'inline';
                        document.getElementById("compatibilidad_desc").style.display = 'none';
                    }
                } else if(tipocolumna == 'motivo'){
                    if (orden == 'asc') {
                        document.getElementById("motivo_asc").style.display = 'none';
                        document.getElementById("motivo_desc").style.display = 'inline';
                    } else if (orden == 'desc') {
                        document.getElementById("motivo_asc").style.display = 'inline';
                        document.getElementById("motivo_desc").style.display = 'none';
                    }
                }
            }});
    }
</script>
<noscript>Funciones de JavaScript desactivadas por navegador</noscript>
<div class="miEspacio">
<h2>Mi espacio</h2>
<div class="tab_block">
	<div align="left" id="returnME" style="display:block;">
		<a href="javascript:location.replace('<c:url value="/miEspacioCandidato.do"/>');" class="expand">
			<strong>Ir al inicio de Mi espacio</strong>
		</a>
	</div>
	<div class="Tab_espacio">
		<h3>Mis ofertas de empleo</h3>
		<div class="subTab">
			<ul>
				<li>
					<a href="<c:url value="/misofertas.do?method=misPostulaciones"/>">Mis postulaciones</a>
				</li>
				<li>
					<a href="<c:url value="/misofertas.do?method=misOfertasGuardadas"/>">Mis ofertas guardadas</a>
				</li>
				<li>
					<a href="<c:url value="/misofertas.do?method=empresasQueMeBuscan"/>">Empresas que me buscan</a>
				</li>
				<!--INI_JGLC_PPC-->
<!-- 				<li> -->
<!-- 					<a id="registro_postulaciones_externas" href="registroPostulacionExterna.do?method=init">Registrar postulaciones externas</a> -->
<!-- 				</li> -->
<!-- 				<li> -->
<%-- 					<a id="seguimiento_postulaciones_externas" href="${context}seguimientoPostulacionExterna.do?method=init">Seguimiento a postulaciones externas</a> --%>
<!-- 				</li> -->
<!-- 				<li class="curSubTab"> -->
<!-- 					<span>Registrar motivo de no postulación</span> -->
<!-- 				</li> -->
				<!--FIN_JGLC_PPC-->
			</ul>
			<div class="clearfix"></div>
		</div>
	</div>
</div>
<div dojoType="dojo.data.ItemFileReadStore" jsId="motivosNoPostulacionStore" url="${context}adminNoPostulacionesCandidato.do?method=motivosNoPostulacion"></div>
<div class="oculto">
	<div data-dojo-type="dijit.Dialog" data-dojo-props="title:'Motivo de no postulaci&oacute;n'" id="regMotivNoPost">
		<div class="grid1_3">
			<label for="motivos"><span>*</span> ¿Por qu&eacute; no deseas postularte?</label> 
			<select id="motivos" name="motivos" onchange="tipoMotivo();"
								 required="true" 
								 missingMessage="Debe indicar el motivo de no postulacion."
								 invalidMessage="Motivo de no postulacion invalido."
								 value="1"
						         autocomplete="true"
						         dojotype="dijit.form.FilteringSelect" 
						         store="motivosNoPostulacionStore"></select>
		</div>
		<div id="divOtroMotivo" class="grid1_3 margin_top_10">
			<label for="otroMotivo">Especifica el motivo:</label>
			<textArea cols="56" rows="5" name="otroMotivo" id="otroMotivo" name="otroMotivo" data-dojo-type="dijit/form/SimpleTextarea"
					required="false" trim="true" clearOnClose="true" invalidMessage="Caracter o longitud no validos." 
					missingMessage="Debe capturar un motivo de no postulacion." regExp="${regexpmotivo}" 
					dojoType="dijit.form.ValidationTextBox" maxlength="150" onkeyup="javascript:caracteresValidos(this);" ></textArea>
		</div>
		<p class="form_nav">
			<button class="margin-r_20" onclick="javascript:registroNoPostulaciones();">Guardar</button>
			<button onclick="javascript:closeDialogRegMotivos();">Cancelar</button>
		</p>
	</div>
</div>
<div class="entero" >
	<html:messages id="errors">
	    <span class="redFont Font80" ><bean:write name="errors"/></span><br/>
	</html:messages>
	
	<html:messages id="messages" message="true">
	    <span class="Font80"><bean:write name="messages"/></span><br/>
	</html:messages>   
</div> 
<div id="tabla"></div>
	<form name="administracionNoPostulacionesForm" id="administracionNoPostulacionesForm" 
			action="adminNoPostulacionesCandidato.do?method=registrar" method="post" dojoType="dijit.form.Form">	
		  <input type="hidden" value="" name="goToPageNumber" id="goToPageNumber"/>
		  <input type="hidden" value="" name="method" id="method"/>
		  <input type="hidden" value="" name="idMotivo" id="idMotivo"/>
		  <input type="hidden" value="" name="motivoDesc" id="motivoDesc"/>
		  <input type="hidden" value="" name="idOfertaEmpleo" id="idOfertaEmpleo"/>
	</form>
	<script>
		doSubmitPager('page');
	</script>
</div>