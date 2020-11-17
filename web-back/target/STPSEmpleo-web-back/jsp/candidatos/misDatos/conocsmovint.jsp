<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ page import="java.util.List, java.util.Iterator, java.util.ArrayList, mx.gob.stps.portal.persistencia.vo.ConocimientoHabilidadVO"%>
<%
	String context = request.getContextPath() +"/";
	String vProxy = context + "SpellCheck.do?method=gogiespell&lang=";
	int NUMERO_MAXIMO_DE_REGISTROS_ADICIONALES_PERMITIDOS = 3;
%>

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
	dojo.require("dijit.form.FilteringSelect");
	dojo.require("dijit.form.Select");
	dojo.require('dijit.Dialog');
</script>
<script type="text/javascript">
	var chkThis;
	var tableIDThis;
	var rowIDThis;
	function caracteresValidos(e){
    	var tecla_codigo = (document.all) ? e.keyCode : e.which;
    	if(tecla_codigo==8 || tecla_codigo==0) return true;
    	if (tecla_codigo==225 || tecla_codigo==233 || tecla_codigo==237 || tecla_codigo==243 || tecla_codigo==250) return true; //vocales minusculas con acento
    	if (tecla_codigo==193 || tecla_codigo==201 || tecla_codigo==205 || tecla_codigo==211 || tecla_codigo==218) return true; //vocales mayuzculas con acento
    	if (tecla_codigo==209 || tecla_codigo==241 ) return true; //Ñ y ñ
    	var patron =/[0-9\-a-zA-Z_ .,:;#]/;
    	tecla_valor = String.fromCharCode(tecla_codigo);
    	return patron.test(tecla_valor);
    }
	function confirmAlert(mensaje){
			dojo.byId('mensajeConfir').innerHTML = mensaje;
			dijit.byId('MensajeAlerConfirt').show();		
	}
	function closeDialog(){
	        dijit.byId('MensajeAlerConfirt').hide();
	        setTimeout("window.location.reload();",10);
	} 	 
	function deleteRow(chk, tableID, rowID) {
		chkThis 	= chk;
		tableIDThis = tableID;
		rowIDThis 	= rowID;
		confirmAlert("&iquest;Confirma que desea eliminar el registro?");
	}
	function deleteRowAux(){ 
		var fila = document.getElementById(rowIDThis);
		fila.parentNode.removeChild(fila); 				
		doSubmitDelete(chkThis.value);
	    setTimeout("window.location.reload();",10);
	}
    function doSubmitDelete(idConocHab) {
    	dijit.byId('method').set('value', 'borrarConocHab');
    	dijit.byId('idCandidatoConocimHabilidad').set('value', idConocHab);
    	dojo.xhrPost( {					 					 
			url: 'conocsHabs.do',
		  	form:'conocimHabilidadForm',
		  	timeout:180000,
		  	load: function(data){
				var res = dojo.fromJson(data); 
		    }
		 });
    }
    function validarTextArea(id) {
	   	var txt = dijit.byId(id);
		var regExp = /^[\w\d\s\.áéíóúÁÉÍÓÚñÑ]{1,2000}$/;
		if (!regExp.test(txt.get('value')) && txt.get('value') != '') {
			message('Descripcion contiene caracteres inválidos');
			txt.focus();
			return false;
		}
		return true;
	}
	function mensaje(mensaje) {	
		dojo.byId('mensaje').innerHTML = mensaje;
		dijit.byId('MensajeAlert').show();
	}
    function toggleKnow() {
    	if (validateKnowledge()) {
			document.getElementById('conocimientosadd').style.display = 'block';
			document.getElementById('btnAgregarConocimiento').style.display = 'none';
		}
    }
    function isValidKnowledge() {
    	var knowledge = document.getElementById('conocimientoAdd');
    	var desConAdd = document.getElementById('descripcionConAdd');
    	var experienceConAdd = document.getElementById('idExperienciaSelectAdd');
    	if (!knowledge.value) {
			message('Debe proporcionar el conocimiento.');
			knowledge.focus();
			return false;
		}
		if (experienceConAdd.value == 0) {
			message('Debe proporcionar la experiencia del conocimiento.');
			return false;
		}
		if (!desConAdd.value) {
			message('Debe proporcionar la descripción del conocimiento.');
			desConAdd.focus();
			return false;
		}
    	return true;
    }
	function doAddKnowledge() {
    	if(isValidKnowledge()) {
    		dojo.byId('btnEnviar').disabled=true;
	    	var idExperienciaSelectAdd = dijit.byId('idExperienciaSelectAdd').get('value');
	    	var descripcionConAdd = encodeURIComponent(document.getElementById('descripcionConAdd').value);
	    	var conocimientoAdd = encodeURIComponent(document.getElementById('conocimientoAdd').value);
	    	dojo.xhrGet({
				url: 'perfilComplementario.do?method=saveAddKnowledge&idExperienciaSelectAdd='+idExperienciaSelectAdd+'&descripcionConAdd='+descripcionConAdd+'&conocimientoAdd='+conocimientoAdd,
			  	form:'ProgramaForm',
			  	timeout:180000,
			  	load: function(data) {
			  		document.getElementById('idExperienciaSelectAdd').value='';
					document.getElementById('descripcionConAdd').value='';
					document.getElementById('conocimientoAdd').value='';
					clearWidgetsAndAddHtml('idConocimientosBloque',data);
					estableceCorrectores();
			  	}
			 });
		 }
    }
	function doDeleteKnowledge(idKnow) {
		messageConfirmDel('¿Está seguro de eliminar el conocimiento?', idKnow);
	}
	function messageConfirmDel(msg, idKnow) {
		var html =
			'<div id="messageDialgoDel" name="messageDialgoDel">' +
			'<p style="text-align: center;">'+ msg +'</p>'+
			'<p class="form_nav">' + 
			'<input id="btnRenv" class="boton_naranja" type="button" name="btnRenv" onClick="deleteKnowledge('+idKnow+')" value="Aceptar"/>' +
			'<input id="btnCanc" class="boton_naranja" type="button" name="btnCanc" onClick="dialogMsgConfirmDel.hide();" value="Cancelar"/>' +
    		'</p>' +
    		'</div>';
		dialogMsgConfirmDel = new dijit.Dialog({
	        title: 'Mensaje',
	        content: html,
	        style: "width:300px;",
	        showTitle: false, draggable : false, closable : false,
	    });	
		dialogMsgConfirmDel.show();
	}
	function deleteKnowledge(idKnow) {
		dialogMsgConfirmDel.hide();
		dojo.xhrGet({url: 'perfilComplementario.do?method=deleteKnowledge&idKnow='+idKnow, form:'ProgramaForm', sync: true, timeout:180000, 
			load: function(data) {
				clearWidgetsAndAddHtml('idConocimientosBloque',data);
				estableceCorrectores();
	  	}});
	}
  	function maximaLongitud(texto,maxlong) {
	  	var tecla, int_value, out_value;
	  	if (texto.value.length > maxlong) {
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
	function estableceCorrector(control){
		var vSpellCon = new GoogieSpell("googiespell/", '<%=vProxy%>');
 		vSpellCon.setLanguages({'es': 'Español'});
		vSpellCon.hideLangWindow();
		vSpellCon.setForceWidthHeight('583px');
		vSpellCon.decorateTextarea(control);
	}
	function estableceCorrectores() {
		if (dojo.byId('descripcionConAdd'))
			estableceCorrector('descripcionConAdd');
		if (dojo.byId('descripcionConAdd1'))
			estableceCorrector('descripcionConAdd1');
		if (dojo.byId('descripcionConAdd2'))
			estableceCorrector('descripcionConAdd2');
		if (dojo.byId('descripcionConAdd3'))
			estableceCorrector('descripcionConAdd3');
	}
	dojo.addOnLoad(function() {
		estableceCorrectores();
	});
</script>
<noscript>Funciones de JavaScript desactivadas por navegador</noscript>
<%
	int index = 1;
	String idExperienciaSelectAdd = "idExperienciaSelectAdd";
	String descripcionConAdd = "descripcionConAdd";
	String conocimientoAdd = "conocimientoAdd";
	String idCandidatoConocAdd = "idCandidatoConocAdd";
	
	List<ConocimientoHabilidadVO> knows = (List<ConocimientoHabilidadVO>)request.getSession().getAttribute("KNOWLEDGE_LIST");
	if (null == knows) knows = new ArrayList<ConocimientoHabilidadVO>();
	
	Iterator<ConocimientoHabilidadVO> itKnows = knows.iterator();
	while (itKnows.hasNext()) {
		ConocimientoHabilidadVO knw = itKnows.next();
		if (knw.getPrincipal() == 2) { 
			knw.setRow(index);
%> 
		<div class="conocimientos bloque">
			<input type="hidden" name="<% out.print(idCandidatoConocAdd + index); %>" id="<% out.print(idCandidatoConocAdd + index); %>" value="<%=knw.getIdCandidatoConocimHabilidad() %>" />
            <p class="un_medio fl"><label for="<% out.print(conocimientoAdd + index); %>">Conocimiento</label>
                <input class="sugerido" type="text" name="<% out.print(conocimientoAdd + index); %>" id="<% out.print(conocimientoAdd + index); %>" value="<%=knw.getConocimientoHabilidad() %>" 
	        		trim ="true" uppercase="true" style="width:25em;" maxlength="50" regExp="^[\w\d\s\.áéíóúÁÉÍÓÚñÑ]{1,50}$" dojoType="dijit.form.ValidationTextBox" required="true"/>
            </p>
            <p class="un_medio fl"><strong><label for="<% out.print(idExperienciaSelectAdd + index); %>">Experiencia</label></strong>
	        	<select class="sugerido" id="<% out.print(idExperienciaSelectAdd + index); %>" name="<% out.print(idExperienciaSelectAdd + index); %>" required="true" autocomplete="true" dojotype="dijit.form.FilteringSelect">
	        		<option value="0"></option>
                    <option value="2" <% if (knw.getIdExperiencia() == 2) out.print(" selected "); %>>6m - 1 año</option>
                    <option value="3" <% if (knw.getIdExperiencia() == 3) out.print(" selected "); %>>1 - 2 años</option>
                    <option value="4" <% if (knw.getIdExperiencia() == 4) out.print(" selected "); %>>2 - 3 años</option>
                    <option value="5" <% if (knw.getIdExperiencia() == 5) out.print(" selected "); %>>3 - 4 años</option>
                    <option value="6" <% if (knw.getIdExperiencia() == 6) out.print(" selected "); %>>4 - 5 años</option>
                    <option value="7" <% if (knw.getIdExperiencia() == 7) out.print(" selected "); %>>Más de 5 años</option>
	        	</select>
            </p>
            <br clear="all" /> 
            <div class="campoTexto">
                	<p><label for="<% out.print(descripcionConAdd + index); %>">Descripci&oacute;n</label></p>
					<div>En este espacio puedes ampliar la información sobre este conocimiento</div>
                	<textarea class="sugerido" rows="4" cols="70" name="<% out.print(descripcionConAdd + index); %>" id="<% out.print(descripcionConAdd + index); %>" 
	        			onkeypress="return caracteresValidos(event);" onblur="trimSpaces(this)"
	        			onchange="return maximaLongitud(this,2000)" onKeyUp="return maximaLongitud(this,2000)"
	        			maxlength="2000"  
	        			required="false" 
	        			regExp="^[\w\d\s\.&aacute;&eacute;&iacute;&oacute;&uacute;&Aacute;&Eacute;&Iacute;&Oacute;&Uacute;&Ntilde;&ntilde;]{1,2000}$"><%=knw.getDescripcion() %></textarea>
                </p>
            	<div style="width:930px" class="ta_right">
            	<br><br>
            		<input type="button" class="eliminar"
            			   onclick="javascript:doDeleteKnowledge(<%=knw.getRow() %>);"
            			   title="Eliminar conocimiento" value="Eliminar" />
            	</div>
            	<br clear="all" />
            </div>
    	</div>
 <% 		
 			index++;
 		} 		
	}
	if (knows.size() < NUMERO_MAXIMO_DE_REGISTROS_ADICIONALES_PERMITIDOS) {
%>
	    <div class="add_ph ta_right" style="width:932px">
	    	<input type="button" id="btnAgregarConocimiento" name="btnAgregarConocimiento"
	    	       class="agregar" onclick="javascript:toggleKnow();" title="Agregar conocimiento" value="Agregar conocimiento" />
	    </div>
<%  
	}else {
%>
		<div class="entero">
	    						
		</div>
<%  
	}
%> 
<div id="conocimientosadd" class="conocimientos bloque color_bloque" style="display:none">
	<h3>Agregar otro conocimiento</h3>
    <div class="margin_top_10">
    	<div class="grid1_3 fl">
        	<label for="conocimientoAdd">Conocimiento</label>
            <input class="sugerido" type="text" name="conocimientoAdd" id="conocimientoAdd" value="" maxlength="50" regExp="^[\w\d\s\.&aacute;&eacute;&iacute;&oacute;&uacute;&Aacute;&Eacute;&Iacute;&Oacute;&Uacute;&Ntilde;&ntilde;]{1,50}$" 
	        		dojoType="dijit.form.ValidationTextBox" trim ="true" uppercase="true" required="true" missingMessage="Debe indicar el conocimiento." invalidMessage="Valor no permitido" />
            	</div>
            	<div class="grid1_3 fl">
            		<label for="idExperienciaSelectAdd">Experiencia</label>
		        	<select class="sugerido" id="idExperienciaSelectAdd" required="true" autocomplete="true" dojotype="dijit.form.FilteringSelect">
		        		<option value="0"></option>
	                    <option value="2" >6m - 1 año</option>
	                    <option value="3" >1 - 2 años</option>
	                    <option value="4" >2 - 3 años</option>
	                    <option value="5" >3 - 4 años</option>
	                    <option value="6" >4 - 5 años</option>
	                    <option value="7">Más de 5 años</option>
		        	</select>	        	
            	</div>
            <div class="clearfix"></div>
            </div>
            <div class="campoTexto">
            		<p><label for="descripcionConAdd">Descripción</label></p>
                	<div>En este espacio puedes ampliar la información sobre este conocimiento</div>
                	<textarea class="sugerido" rows="4" cols="70" name="descripcionConAdd" id="descripcionConAdd" 
	        			onkeypress="return caracteresValidos(event);" onblur="trimSpaces(this)"
	        			onchange="return maximaLongitud(this,500)" onKeyUp="return maximaLongitud(this,500)"
	        			maxlength="500"  
	        			required="false" 
	        			regExp="^[\w\d\s\.&aacute;&eacute;&iacute;&oacute;&uacute;&Aacute;&Eacute;&Iacute;&Oacute;&Uacute;&Ntilde;&ntilde;]{1,500}$"></textarea>
	        			
	        	<div style="width: 930px" class="margin_top_50 ta_right">
            		<input type="button" id="btnEnviar" class="enviar" onclick="javascript:doAddKnowledge();" title="Guardar conocimiento" value="Guardar conocimiento" />
            	</div>
            </div>
    	</div>