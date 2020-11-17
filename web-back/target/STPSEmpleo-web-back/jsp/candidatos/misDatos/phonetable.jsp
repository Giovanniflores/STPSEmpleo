<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page import="java.util.List, java.util.Iterator, java.util.ArrayList, mx.gob.stps.portal.persistencia.vo.TelefonoVO"%>
<%
//     int NUMERO_MAXIMO_DE_REGISTROS_ADICIONALES_PERMITIDOS = 2;
	int NUMERO_MAXIMO_DE_REGISTROS_ADICIONALES_PERMITIDOS = 3;
	int rowCounter = 0;
	List<TelefonoVO> lstTelefonosAdicionales = new ArrayList<TelefonoVO>();
	if (null != request.getSession().getAttribute("LST_TELEFONOS_ADICIONALES")){
		lstTelefonosAdicionales = (List<TelefonoVO>)request.getSession().getAttribute("LST_TELEFONOS_ADICIONALES");
	}		
%>
<script>
	function doSubmit() {
		if (validateData())
			addPhone();		
	}
	function doDeletePhone(idphone) {
		if (confirm('Esta usted seguro de eliminar teléfono')) {
			deletePhone(idphone);
		}	
	}
	function addPhone() {
		var claveAdd=document.getElementById('claveAdd').value;
		var accesoAdd=document.getElementById('accesoAdd').value;
		var telefonoAdd=document.getElementById('telefonoAdd').value;
		var tipoAdd=document.getElementById('idTipoTelefonoAdd').value;
		var extensionAdd=document.getElementById('extensionAdd').value;
		dojo.xhrGet({url: 'perfil.do?method=saveAddPhone&claveAdd='+claveAdd+'&accesoAdd='+accesoAdd+'&telefonoAdd='+telefonoAdd+'&tipoAdd='+tipoAdd+'&extensionAdd='+extensionAdd, form:'perfilForm', sync: true, timeout:180000, 
					  load: function(data) {
						  	document.perfilForm.idTipoTelefonoAdd.value='';
						  	document.perfilForm.accesoAdd.value='';
						  	document.perfilForm.claveAdd.value='';
						  	document.perfilForm.telefonoAdd.value='';
						  	document.perfilForm.extensionAdd.value='';
						    dojo.byId('phoneadds').innerHTML=data;
					  }});
	}
	function deletePhone(idphone) {
		dojo.xhrGet({url: 'perfil.do?method=deletePhone&idPhone='+idphone, form:'perfilForm', sync: true, timeout:180000, 
					  load: function(data) {
						dojo.byId('phoneadds').innerHTML=data;
					  }});
	}
	function fillAccessAddKey() {
		var accesoDes;
		var tipoTelefonoId = getRadioValue('idTipoTelefonoAdd');
		if (tipoTelefonoId == 1){
        	accesoDes = '044';
        	document.getElementById('extensionAdd').value=''; 
        	dijit.byId('extensionAdd').disabled=true; 
        	document.getElementById('extensionAdd').readOnly=true;
        }
        else{
        	accesoDes = '01';   
        	document.getElementById('extensionAdd').value=''; 
        	dijit.byId('extensionAdd').disabled=false;
        	document.getElementById('extensionAdd').readOnly=false;
        } 		
        var wAcceso = document.getElementById('accesoAdd');
        wAcceso.value=accesoDes;
        wAcceso.disabled='disabled';
	}
	function toggle() {
		document.getElementById('addPhone').style.display = 'block';
		document.getElementById('bAgregarTelefono').style.display = 'none';
    }
	function isNumber(n) {
  		return !isNaN(parseFloat(n)) && isFinite(n);
	}
	 //Dependiendo de la seleccion de tipo de telefono, coloca la clave de acceso correspondiente
	function fillUpAccess(index) {
		var tipoTelefonoId = getRadioValue('idTipoTelefonoAdd' + index);
		var accesoDes;
		if (tipoTelefonoId == 1){
			accesoDes = '044';
			document.getElementById('extensionAdd' + index).value=''; 
        	dijit.byId('extensionAdd' + index).disabled=true; 
        	document.getElementById('extensionAdd' + index).readOnly=true;
			
		}
	    		
		else{
	    		accesoDes = '01';
	    		document.getElementById('extensionAdd' + index).value=''; 
	        	dijit.byId('extensionAdd' + index).disabled=false; 
	        	document.getElementById('extensionAdd' + index).readOnly=false;
	    		
		}
		var wAcceso = dijit.byId('accesoAdd' + index);
		wAcceso.attr('value',accesoDes);
	}
	function validateData() {
		var tipoTelefonoId = getRadioValue('idTipoTelefonoAdd');
		var vClave = document.getElementById('claveAdd').value;
        var vTelefono = document.getElementById('telefonoAdd').value; 
        var vExtension = document.getElementById('extensionAdd').value; 
        if (!tipoTelefonoId) {
        	alert('Debe seleccionar el tipo de teléfono.');
			return false;
        }
        if (!isNumber(vClave)) {
         	alert('La clave LADA debe ser un valor numérico (0-9).');
         	document.perfilForm.claveAdd.focus();
        	return false;
        }else {
        	if (!vClave || vClave.length<2){
        		alert('Debe proporcionar una clave LADA de 2 ó 3 caracteres.');
        		document.perfilForm.claveAdd.focus();
        		return false;
	        }else {
				if(vClave.length==2) {
					if(vTelefono.length!=8){
						alert('Debe proporcionar un número telefónico de 8 dígitos.');
						document.perfilForm.claveAdd.focus();
        				return false;
					}
				}else if(vClave.length==3) {
					if (vTelefono.length!=7){
						alert('Debe proporcionar un número telefónico de 7 dígitos.');
						document.perfilForm.claveAdd.focus();
        				return false;
					}	
				}         
	         }          
         }
         if(!vTelefono) {
        	alert('Debe proporcionar el número telefónico.');
        	document.perfilForm.telefonoAdd.focus();
        	return false;
        }else {
        	if(!isNumber(vTelefono)){
	        	alert('El número telefónico debe ser un valor numérico (0-9).');
	        	document.perfilForm.telefonoAdd.focus();
        		return false;
	        }       
        }
         if (vExtension && !isNumber(vExtension)) {
         	alert('La extensión debe ser un valor numérico (0-9).');
         	document.perfilForm.extensionAdd.focus();
        	return false;
         }
         return true;
	}
</script>
<noscript>Funciones de JavaScript desactivadas por navegador</noscript>

<%
	int index = 1;
	String claveAdd = "claveAdd";
	String accesoAdd = "accesoAdd";
	String telefonoAdd = "telefonoAdd";
	String extensionAdd = "extensionAdd";
	String idTelefonoAdd = "idTelefonoAdd";
	String idTipoTelefonoAdd = "idTipoTelefonoAdd";
	Iterator<TelefonoVO> itPhones = lstTelefonosAdicionales.iterator();
	while (itPhones.hasNext()) {
		TelefonoVO ph = itPhones.next();
		if (ph.getPrincipal() == 2) {
%>
	<div style="clear:both">
	
	<input type="hidden" name="<% out.print(idTelefonoAdd + index); %>" id="<% out.print(idTelefonoAdd + index); %>" value="<%=ph.getIdTelefono() %>"/>
	<div class="grid1_3  margin_top_20 fl">
		<div class="margin-r_20 fl">
			<div class="labeled margin_no_t"><span>*</span> Tipo de teléfono</div>
			<div class="tipo_tel margin_top_10 fl" style="margin-right:5px">
				<input class="fl" type="radio" name="<% out.print(idTipoTelefonoAdd + index); %>" id="<% out.print(idTipoTelefonoAdd + index); %>" value="2" <% if (ph.getIdTipoTelefono()==2) out.print("checked"); %> onClick="javascript:fillUpAccess(<%=index%>);" />
				<label class="fl" for="<% out.print(idTipoTelefonoAdd + index); %>">Fijo</label>
			</div>
			<div class="tipo_tel margin_top_10 fl">
				<input class="fl" type="radio" name="<% out.print(idTipoTelefonoAdd + index); %>" id="<% out.print(idTipoTelefonoAdd + index); %>" value="1" <% if (ph.getIdTipoTelefono()==1) out.print("checked"); %> onClick="javascript:fillUpAccess(<%=index%>);" />
				<label class="fl" for="<% out.print(idTipoTelefonoAdd + index); %>">Celular</label>
			</div>
		</div>
		<div class="margin-r_10 fl">
    		<label  class="fw_no_bold" for="<% out.print(accesoAdd + index);%>"><span>*</span> Acceso</label>
	        <div class="ta_center">
	        	<input style="width: 3em" name="<% out.print(accesoAdd + index); %>" id="<% out.print(accesoAdd + index); %>" maxlength="3" size="3" value="<%=ph.getAcceso() %>" dojoType="dijit.form.ValidationTextBox" required="false"
			 			regExp="^(044|01)$" invalidMessage="Dato no válido" missingMessage="Dato requerido" trim="true" readonly="readonly" />
			 	
	        </div>
    	</div>
	    <div class="fl">
	    	<label for="<% out.print(claveAdd + index);%>"><span>*</span> Clave lada</label>
	    	<input name="<% out.print(claveAdd + index); %>" id="<% out.print(claveAdd + index); %>" maxlength="3" size="3" value="<%=ph.getClave() %>" dojoType="dijit.form.ValidationTextBox" required="false" regExp="^[0-9]{2,3}$"
			 invalidMessage="La clave debe ser numérica" missingMessage="Dato requerido" trim="true" />
	    </div>
    </div>
    <div class="margin_top_20 fl">
    	<label for="<% out.print(telefonoAdd + index); %>"><span>*</span> Teléfono</label>
    	<input name="<% out.print(telefonoAdd + index); %>" id="<% out.print(telefonoAdd + index); %>" maxlength="8" size="8" onBlur="changePhoneSizeRequired()" value="<%=ph.getTelefono() %>" dojoType="dijit.form.ValidationTextBox" required="false" regExp="^[0-9]{7,8}$"			 	
		 invalidMessage="El teléfono debe ser numérico" missingMessage="Dato requerido" trim="true" />
    </div>
    <div class="margin_top_20 fl">
    	<label for="<% out.print(extensionAdd + index); %>">Extensión</label>
    	<input class="sugerido" name="<% out.print(extensionAdd + index); %>" id="<% out.print(extensionAdd + index); %>" maxlength="8" size="8" value="<%=ph.getExtension() %>" dojoType="dijit.form.ValidationTextBox" required="false" regExp="^[0-9]{1,6}$"
		 invalidMessage="La extensión debe ser numérica" missingMessage="Dato requerido" trim="true" <% if (ph.getIdTipoTelefono()==1) out.print("readonly=true"); %> />
    </div>
    <div class="margin_top_50 margin-r_10 fl">
    	<input type="button" class="eliminar" onclick="javascript:doDeletePhone(<%=ph.getIdTelefono() %>)" title="Eliminar teléfono" value="Eliminar teléfono" />
    </div>
    
    </div>
    
	<%
			index++;
		}
	}
	%>

	<div id="addPhone" style="display:none; padding-bottom:10px; clear:both;">
		<div class="grid1_3  margin_top_20 fl">
			<div class="margin-r_20 fl">
				<div class="labeled margin_no_t"><span>*</span> Tipo de teléfono</div>
				<div class="fl">
					<div class="tipo_tel margin_top_10 margin-r_10 fl">			
		            	<input class="fl" type="radio" name="idTipoTelefonoAdd" id="idTipoTelefonoAdd" value="2" onClick="fillAccessAddKey();" />
		            	<label class="fl" for="idTipoTelefonoAdd">Fijo</label>
		            </div>
		            <div class="tipo_tel margin_top_10 fl">
		            	<input class="fl" type="radio" name="idTipoTelefonoAdd" id="idTipoTelefonoAdd" value="1" onClick="fillAccessAddKey();" />
		            	<label class="fl" for="idTipoTelefonoAdd">Celular</label>
		            </div>
		        </div>
	        </div>
	        <div class="margin-r_10 fl">
				<label for="accesoAdd" class="fw_no_bold"><span>*</span> Acceso</label>
				<div class="ta_center">
			 		<input style="width:3em" id="accesoAdd" name="accesoAdd" maxlength="3" size="3" dojoType="dijit.form.ValidationTextBox" required="false" disabled="disabled"
			 			regExp="^(044|01)$" invalidMessage="Dato no válido" missingMessage="Dato requerido" trim="true" />  
		 		</div>			          
        	</div>
        	<div class="fl">       			 
        		<label for="claveAdd"><span>*</span> Clave lada</label>
				<input id="claveAdd" name="claveAdd" maxlength="3" size="3" dojoType="dijit.form.ValidationTextBox" required="false" regExp="^[0-9]{2,3}$"
		 			invalidMessage="La clave debe ser numérica" missingMessage="Dato requerido" trim="true" />	        			        
        	</div>
        </div> 
        <div class="margin_top_20 fl">			
        	<label for="telefonoAdd"><span>*</span> Teléfono</label>                 
			<input id="telefonoAdd" name="telefonoAdd" maxlength="8" size="8" onBlur="changePhoneSizeRequired()" dojoType="dijit.form.ValidationTextBox" required="false" regExp="^[0-9]{7,8}$"			 	
		 			invalidMessage="El numero telefonico no es válido" missingMessage="Dato requerido" trim="true" />
        </div>	
        <div class="margin_top_20 fl">	 
        	<label for="extensionAdd">Extensión</label>
			<input id="extensionAdd" name="extensionAdd" maxlength="8" size="8" dojoType="dijit.form.ValidationTextBox" required="false" regExp="^[0-9]{1,6}$"
		 			invalidMessage="La extensión debe ser numérica" missingMessage="Dato requerido" trim="true" />		            
        </div>
		<div class="margin_top_50 fl">         			    				                                        
			<input type="button" class="enviar" id="btnAgregarTelefono" title="Guardar teléfono" value="Guardar teléfono" onclick="doSubmit();" />
        </div>
	</div>

	
	<%if (lstTelefonosAdicionales.size() < NUMERO_MAXIMO_DE_REGISTROS_ADICIONALES_PERMITIDOS) {%>
	    <div class="margin_top_50 fl add_ph" id="bAgregarTelefono" style="display: block;">
	    	<input type="button" class="agregar" onclick="javascript:toggle();" title="Agregar teléfono" value="Agregar teléfono" />
	    </div>
	<%}else {%>
		<div class="entero"></div>
	<%}%>

