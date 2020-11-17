<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page import="java.util.List, java.util.Iterator, java.util.ArrayList, mx.gob.stps.portal.persistencia.vo.TelefonoVO"%>
<%
    int NUMERO_MAXIMO_DE_REGISTROS_ADICIONALES_PERMITIDOS = 2;
	int rowCounter = 0;
	List<TelefonoVO> lstTelefonosAdicionales = new ArrayList<TelefonoVO>();
	if (null != request.getSession().getAttribute("LST_TELEFONOS_ADICIONALES"))
		lstTelefonosAdicionales = (List<TelefonoVO>)request.getSession().getAttribute("LST_TELEFONOS_ADICIONALES");
%>
<script>
	function doSubmit() {
		if (validatePhoneData()){
			addPhone();	
		}				
	}
	
	function doDeletePhone(idphone) {
		if (confirm('¿Esta usted seguro de eliminar el teléfono?')) {
			deletePhone(idphone);
		}	
	}
	
	function addPhone() {
		var claveAdd=document.getElementById('claveAdd').value;
		var accesoAdd=document.getElementById('accesoAdd').value;
		var telefonoAdd=document.getElementById('telefonoAdd').value;
		var tipoAdd=document.getElementById('idTipoTelefonoAdd').value;
		var extensionAdd=document.getElementById('extensionAdd').value;	
		var testUrl = 'edicionEmpresa.do?method=saveAddPhone&claveAdd='+claveAdd+'&accesoAdd='+accesoAdd+'&telefonoAdd='+telefonoAdd+'&tipoAdd='+tipoAdd+'&extensionAdd='+extensionAdd;
		dojo.xhrPost({url: 'edicionEmpresa.do?method=saveAddPhone&claveAdd='+claveAdd+'&accesoAdd='+accesoAdd+'&telefonoAdd='+telefonoAdd+'&tipoAdd='+tipoAdd+'&extensionAdd='+extensionAdd, form:'edicionEmpresaForm', sync: true, preventCache:true, timeout:180000, 
					  load: function(data) {
						  //document.edicionEmpresaForm.idTipoTelefonoAdd.value='';
						  //document.edicionEmpresaForm.accesoAdd.value='';
						  //document.edicionEmpresaForm.claveAdd.value='';
						  //document.edicionEmpresaForm.telefonoAdd.value='';
						  //document.edicionEmpresaForm.extensionAdd.value='';						  	
						  // dojo.byId('phoneadds').innerHTML=data;
						  clearWidgetsAndAddHtml('phoneadds',data);
					  }});
	}
	
	function deletePhone(idphone) {
		var param = "";
		var idTelefono = dijit.byId('idTelefono').get('value');
		var acceso = dijit.byId('acceso').get('value');
		var clave = dijit.byId('clave').get('value');
		var telefono = dijit.byId('telefono').get('value');
		var extension = dijit.byId('extension').get('value');
		param += '&accesoAdd0='+acceso+'&claveAdd0='+clave+'&telefonoAdd0='+telefono+'&extensionAdd0='+extension+'&idTelefonoAdd0='+idTelefono;
		if (dijit.byId('idTelefonoAdd1') != undefined) {
			var idTelefono1 = dijit.byId('idTelefonoAdd1').get('value');
			var acceso1 = dijit.byId('accesoAdd1').get('value');
			var clave1 = dijit.byId('claveAdd1').get('value');
			var telefono1 = dijit.byId('telefonoAdd1').get('value');
			var extension1 = dijit.byId('extensionAdd1').get('value');
			param += '&accesoAdd1='+acceso1+'&claveAdd1='+clave1+'&telefonoAdd1='+telefono1+'&extensionAdd1='+extension1+'&idTelefonoAdd1='+idTelefono1;
		}
		if (dijit.byId('idTelefonoAdd2') != undefined) {
			var idTelefono2 = dijit.byId('idTelefonoAdd2').get('value');
			var acceso2 = dijit.byId('accesoAdd2').get('value');
			var clave2 = dijit.byId('claveAdd2').get('value');
			var telefono2 = dijit.byId('telefonoAdd2').get('value');
			var extension2 = dijit.byId('extensionAdd2').get('value');
			param += '&accesoAdd2='+acceso2+'&claveAdd2='+clave2+'&telefonoAdd2='+telefono2+'&extensionAdd2='+extension2+'&idTelefonoAdd2='+idTelefono2;
		}
		dojo.xhrPost({url: 'edicionEmpresa.do?method=deletePhone&idPhone='+idphone+param,
					  form:'edicionEmpresaForm', sync: true, preventCache:true, timeout:180000, 
					  load: function(data) {
						clearWidgetsAndAddHtml('phoneadds',data);
						//dojo.byId('phoneadds').innerHTML=data;
					  }});
	}
	
	function fillAccessAddKey() {
		var accesoDes;
		var tipoTelefonoId = getRadioValue('idTipoTelefonoAdd');
        if(tipoTelefonoId==1){
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
		if(document.getElementById('claveAdd')){
			document.getElementById('claveAdd').focus();
		}			
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
		wAcceso.disabled=false;
		wAcceso.attr('value',accesoDes);
		wAcceso.disabled='disabled';
	}
	 
	function validatePhoneData() {
		var tipoTelefonoId = getRadioValue('idTipoTelefonoAdd');
		var vClave = document.getElementById('claveAdd').value;
        var vTelefono = document.getElementById('telefonoAdd').value; 
        var vExtension = ''
        if(document.getElementById('extensionAdd')){
        	vExtension = document.getElementById('extensionAdd').value;
        }                 
        if (!tipoTelefonoId) {
        	alert('Debe seleccionar el tipo de teléfono.');
			return false;
        }
        if (!isNumber(vClave)) {
         	alert('La clave LADA debe ser un valor numérico (0-9).');
         	//document.edicionEmpresaForm.claveAdd.focus();
         	document.getElementById('claveAdd').focus();
        	return false;
        }else {
        	if (!vClave || vClave.length<2){
        		alert('Debe proporcionar una clave LADA de 2 ó 3 caracteres.');
        		//document.edicionEmpresaForm.claveAdd.focus();
        		document.getElementById('claveAdd').focus();
        		return false;
	        }else {
	            if(!vTelefono) {
	            	alert('Debe proporcionar el número telefónico.');
	            	document.edicionEmpresaForm.telefonoAdd.focus();
	            	return false;
	            }else {
	            	if(!isNumber(vTelefono)){
	    	        	alert('El número telefónico debe ser un valor numérico (0-9).');
	    	        	document.edicionEmpresaForm.telefonoAdd.focus();
	            		return false;
	    	        } else {
	    				if(vClave.length==2) {
	    					if(vTelefono.length!=8){
	    						alert('El número telefónico debe tener 8 dígitos.');
	    						//document.edicionEmpresaForm.claveAdd.focus();
	    						document.getElementById('claveAdd').focus();
	            				return false;
	    					}
	    				}else if(vClave.length==3) {
	    					if (vTelefono.length!=7){
	    						alert('El número telefónico debe tener 7 dígitos.');
	    						//document.edicionEmpresaForm.claveAdd.focus();
	    						document.getElementById('claveAdd').focus();
	            				return false;
	    					}	
	    				}         	    	        	
	    	        }       
	            }	        		        	
	         }          
         }        

         if (vExtension && !isNumber(vExtension)) {
         	alert('La extensión debe ser un valor numérico (0-9).');
         	document.edicionEmpresaForm.extensionAdd.focus();
        	return false;
         }
         return true;
	}
	
	function validatePhoneList() {
		var tipoTelefonoId1 = getRadioValue('idTipoTelefonoAdd1');
		var tipoTelefonoId2 = getRadioValue('idTipoTelefonoAdd2');
		
		if (tipoTelefonoId1) {
			if (tipoTelefonoId2) {
				return (validatePhone(1) && validatePhone(2));
			}else {
				return validatePhone(1);
			}
		}
		return true;
	}
	
	function validatePhone(index) {
		var tipoTelefonoId = getRadioValue('idTipoTelefonoAdd' + index);
		var vClave = "";
		var vTelefono = "";
		var vExtension = "";
		
        if (!tipoTelefonoId) {
        	alert('Debe seleccionar el tipo de teléfono.');
			return false;
        }
        
		if(document.getElementById('claveAdd' + index)){
			vClave = document.getElementById('claveAdd' + index).value;	
			var claveAdd = document.getElementById('claveAdd' + index);		
			
	        if (!isNumber(vClave)) {
	         	alert('La clave LADA debe ser un valor numérico (0-9).');
	         	claveAdd.focus();
	        	return false;
	        }else {
	        	if (!vClave || vClave.length<2){
	        		alert('Debe proporcionar una clave LADA de 2 ó 3 caracteres.');
	        		claveAdd.focus();
	        		return false;
		        }else {
		    		if(document.getElementById('telefonoAdd' + index)){		    			
		    			vTelefono = document.getElementById('telefonoAdd' + index).value;	
						if(vClave.length==2) {
							if(vTelefono.length!=8){
								alert('Debe proporcionar un número telefónico de 8 dígitos.');
								claveAdd.focus();
		        				return false;
							}
						}else if(vClave.length==3) {
							if (vTelefono.length!=7){
								alert('Debe proporcionar un número telefónico de 7 dígitos.');
								claveAdd.focus();
		        				return false;
							}	
						}  		    					    			
		    		}		        	
		        }  
	    		if(document.getElementById('telefonoAdd' + index)){		    			
	    			vTelefono = document.getElementById('telefonoAdd' + index).value;
	    			var telefonoAdd = document.getElementById('telefonoAdd' + index);
	    	        if(!vTelefono) {
	    	          	alert('Debe proporcionar el número telefónico.');
	    	          	telefonoAdd.focus();
	    	          	return false;
	    	        }else {
	    	        	if(!isNumber(vTelefono)){
	    	 	         	alert('El número telefónico debe ser un valor numérico (0-9).');
	    	 	         	telefonoAdd.focus();
	    	         	 	return false;
	    	 	        }       
	    	        }          
	    		}       
	    		if(document.getElementById('extensionAdd' + index)){
	    			vExtension = document.getElementById('extensionAdd' + index).value;
	    	        var extensionAdd = document.getElementById('extensionAdd' + index);
	    	         if (vExtension && !isNumber(vExtension)) {
	    	         	alert('La extensión debe ser un valor numérico (0-9).');
	    	         	extensionAdd.focus();
	    	        	return false;
	    	         }	    			
	    		}	        	
	         }
		}        
        return true;
	}
		
</script>
<noscript>Funciones de javascript desactivadas por el navegador</noscript>

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
	<input type="hidden" name="<% out.print(idTelefonoAdd + index); %>" id="<% out.print(idTelefonoAdd + index); %>" value="<%=ph.getIdTelefono() %>" dojoType="dijit.form.TextBox"/>
	<div class="clearfix"></div>
	<div class="grid1_3  margin_top_20 fl">
		<div class="margin-r_20 fl">
			<div class="labeled margin_no_t"><span>*</span> Tipo de teléfono</div>
			<div>
				<div class="tipo_tel  margin-r_10 margin_top_10 fl">	
					<input class="fl" type="radio" name="<% out.print(idTipoTelefonoAdd + index); %>" id="<% out.print(idTipoTelefonoAdd + index); %>" value="5" <% if (ph.getIdTipoTelefono()==5) out.print("checked"); %> onClick="javascript:fillUpAccess(<%=index%>);" />
					<label class="fl" for="<% out.print(idTipoTelefonoAdd + index); %>">Fijo</label>
				</div>
				<div class="tipo_tel margin_top_10 fl">	
					<input class="fl" type="radio" name="<% out.print(idTipoTelefonoAdd + index); %>" id="<% out.print(idTipoTelefonoAdd + index); %>" value="1" <% if (ph.getIdTipoTelefono()==1) out.print("checked"); %> onClick="javascript:fillUpAccess(<%=index%>);" />
					<label class="fl" for="<% out.print(idTipoTelefonoAdd + index); %>">Celular</label>
				</div>
			</div>
		</div>
	    <div class="margin-r_10 fl">  
		    	<label for="<% out.print(accesoAdd + index); %>"><span>*</span> Acceso</label>
		    	<div class="ta_center">
			        <span class="acceso">
			        	<input name="<% out.print(accesoAdd + index); %>" style="width:3em" id="<% out.print(accesoAdd + index); %>" maxlength="3" size="3" value="<%=ph.getAcceso() %>" dojoType="dijit.form.ValidationTextBox" required="false"
					 			regExp="^(044|01)$" invalidMessage="acceso válido" missingMessage="acceso requerido" trim="true" readonly="readonly" />
		        	</span>
	        	</div>
	    </div>
	    <div class="fl">
	    	<label for="<% out.print(claveAdd + index); %>"><span>*</span> Clave lada</label>
		    	<input name="<% out.print(claveAdd + index); %>" style="width:90px !important" id="<% out.print(claveAdd + index); %>" maxlength="3" size="3" value="<%=ph.getClave() %>" dojoType="dijit.form.ValidationTextBox" required="true" regExp="^[0-9]{2,3}$"
				 invalidMessage="clave debe ser numérica" missingMessage="clave requerida" trim="true" required="true" onBlur="changeAdditPhoneSizeRequired(<% out.print(index); %>);" />
	    </div>
    </div>
    <div class="margin_top_20 fl">
    		<label for="<% out.print(telefonoAdd + index); %>"><span>*</span> Teléfono</label>
	    	<input name="<% out.print(telefonoAdd + index); %>" id="<% out.print(telefonoAdd + index); %>" maxlength="8" size="8" onBlur="changeAdditPhoneSizeRequired(<% out.print(index); %>)" value="<%=ph.getTelefono() %>" dojoType="dijit.form.ValidationTextBox" required="true" regExp="^[0-9]{7,8}$"			 	
			 invalidMessage="número telefónico debe ser numérico" missingMessage="número telefónico requerido" trim="true"  />
    </div>
    <div class="margin_top_20 fl">
	    <label for="<% out.print(extensionAdd + index); %>">Extensión</label>
	    	<input class="sugerido" name="<% out.print(extensionAdd + index); %>" id="<% out.print(extensionAdd + index); %>" maxlength="8" style="width:8em;" value="<%=ph.getExtension() %>" dojoType="dijit.form.ValidationTextBox" required="false" regExp="^[0-9]{1,8}$"
			 invalidMessage="extensión debe ser numérica" missingMessage="extensión requerida" trim="true" <% if (ph.getIdTipoTelefono()==1) out.print("readonly=true"); %> />
    </div>
    <div class="margin_top_50 margin-r_10 fl">
    	<input type="button" class="eliminar" onclick="javascript:doDeletePhone(<%=ph.getIdTelefono() %>)" value="Eliminar teléfono" />
    </div>
		<% 
		}
		index++;			
	}
%>

	<div id="addPhone" style="display:none">
	<div class="clearfix"></div>
		<div class="grid1_3  margin_top_20 fl">
			<div class="margin-r_20 fl">
				<div class="labeled margin_no_t"><span>*</span> Tipo de teléfono</div>
				<div>
					<div class="tipo_tel margin-r_10 margin_top_10 fl">				
			            <input class="fl" type="radio" name="idTipoTelefonoAdd" id="idTipoTelefonoAdd" value="5" onClick="fillAccessAddKey();" />
			            <label class="fl">Fijo</label>
					</div>
					<div class="tipo_tel margin_top_10 fl">	
			            <input class="fl" type="radio" name="idTipoTelefonoAdd" id="idTipoTelefonoAdd" value="1" onClick="fillAccessAddKey();" />
			            <label class="fl">Celular</label>
					</div>
		        </div>
	        </div>
			<div class="margin-r_10 fl"> 
	        	<label for="accesoAdd"><span>*</span> Acceso</label>
	        	<div class="ta_center">
		        	<span class="acceso">
				 		<input id="accesoAdd" name="accesoAdd" style="width:3em" maxlength="3" size="3" dojoType="dijit.form.ValidationTextBox" required="false"
				 			regExp="^(044|01)$" invalidMessage="acceso no válido" missingMessage="acceso requerido" trim="true" disabled="disabled" />
		        	</span>
	        	</div>
	        </div>
	        <div class="fl">         			 
	        	<label for="claveAdd"><span>*</span> Clave lada</label> 
					<input id="claveAdd" style="width:90px !important" name="claveAdd" maxlength="3" size="3" dojoType="dijit.form.ValidationTextBox" required="false" regExp="^[0-9]{2,3}$"
			 			invalidMessage="clave debe ser numérica" missingMessage="clave requerida" trim="true" onBlur="changePhoneSizeRequired()" />	        			        
	        </div>
        </div>	
        <div class="margin_top_20 fl">		
        	<label for="telefonoAdd"><span>*</span> Teléfono</label>                 
			<input id="telefonoAdd" name="telefonoAdd" maxlength="8" size="8" onBlur="changePhoneSizeRequired()" dojoType="dijit.form.ValidationTextBox" required="false" regExp="^[0-9]{7,8}$"			 	
		 			invalidMessage="número telefónico no es válido" missingMessage="número telefónico requerido" trim="true" />
        </div>	
        <div class="margin_top_20 fl">	 
        	<label for="extensionAdd">Extensión</label>
			<input id="extensionAdd" name="extensionAdd" maxlength="8" style="width:8em;" dojoType="dijit.form.ValidationTextBox" required="false" regExp="^[0-9]{1,8}$"
		 			invalidMessage="extensión debe ser numérica" missingMessage="extensión requerida" trim="true" />     		    			           
        </div>
        <div class="margin_top_50 fl add_ph">
			<input type="button" class="enviar" id="btnAgregarTelefono"  value="Guardar teléfono" onclick="doSubmit();" />
        </div>	  
        <div class="clearfix"></div>
	</div>

	<%
		if (lstTelefonosAdicionales.size() < NUMERO_MAXIMO_DE_REGISTROS_ADICIONALES_PERMITIDOS) {
	%>
		
		<div class="margin_top_50 fl add_ph" id="bAgregarTelefono" style="display: block;">
		   	<input type="button" class="agregar" onclick="javascript:toggle();" value="Agregar teléfono" />
		</div>
	<%  
		}else {
	%>
		<div class="entero">
	    						
		</div>
	<%  
		}
	%>
	