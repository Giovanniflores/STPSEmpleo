<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="mx.gob.stps.portal.web.cil.form.EventUserForm" %>
<script type="text/javascript">
    dojo.require("dijit.dijit"); // loads the optimized dijit layer
	dojo.require("dijit.form.Form");    
    dojo.require("dijit.Dialog");
    dojo.require("dijit.form.Textarea");
    dojo.require("dijit.form.DateTextBox");    
    dojo.require("dijit.form.NumberTextBox");
    dojo.require("dijit.form.ValidationTextBox");
    dojo.require("dijit.form.TextBox");
</script>
<script>
	function cancelConfirmation() {
		var answer = confirm("Antes de cerrar sesión es necesario registrar los servicios solicitados.");
		if (answer) {
			document.ServicesForm.method.value='init';
			document.ServicesForm.submit();
		}
	}
	
	function deletePhone(phone){
	    document.ServicesForm.method.value='deletePhone';
	    document.ServicesForm.telefono.value=phone;
		dojo.xhrPost({url: 'servicioscil.do', form:'ServicesForm', timeout:180000, 
					  load: function(data) {
					  	document.ServicesForm.telefono.value='';
						dojo.byId('calls').innerHTML=data;
					  }});
	}
	
	function addPhone(){
	    document.ServicesForm.method.value='addPhone';
		dojo.xhrPost({url: 'servicioscil.do', form:'ServicesForm', timeout:180000, 
					  load: function(data) {
						  	document.ServicesForm.telefono.value='';
						  	document.ServicesForm.llamadas.value='';
						    dojo.byId('calls').innerHTML=data;
					  }});
	}
	
	function doSubmitAjax() {
		if (validateData()) {
			var answer = confirm("¿Está seguro de guardar los datos?");
			if (answer) {
				document.ServicesForm.method.value='saveEvent';
				document.ServicesForm.submit();
			}
		}
	}
	
	function validateData() {
		if(!dijit.byId('accesoscv').isValid()){
			msgalert("Favor de proporcionar el número de accesos o captura de curriculum.");								
			return false;
		}
		if(!dijit.byId('impresioncv').isValid()){
			msgalert("Favor de proporcionar el número de impresiones de curriculum.");								
			return false;
		}
		if(!dijit.byId('llamadas').isValid()){
			msgalert("Favor de proporcionar el númeo de llamadas.");								
			return false;
		}
		if(!dijit.byId('fotocopias').isValid()){
			msgalert("Favor de proporcionar el número de fotocopias.");								
			return false;
		}
		if(dijit.byId('portal').value == ''){
			msgalert("Favor de proporcionar el acceso al portal del empleo.");								
			return false;
		}
		if(dijit.byId('bolsas').value == ''){
			msgalert("Favor de proporcionar la consulta a otras bolsas de trabajo.");								
			return false;
		}
		return true;
	}
	function msgalert(msg) {
		dojo.byId('msg').innerHTML = msg;
		dijit.byId('msgAlert').show();		
	}	
</script>
<noscript>Funciones de JavaScript desactivadas por navegador</noscript>

<div class="col-md-6 col-sm-push-3">
<h2>Registro de servicios</h2>
<form name="ServicesForm" id="ServicesForm" method="post" action="servicioscil.do" dojoType="dijit.form.Form">
	<input type="hidden" name="method" id="method" value="saveEvent"/>
	<div class="entero control-group" style="margin:10px 0">
		<span class="un_medio">
			<strong><label for="accesoscv">Acceso o captura de curriculum</label></strong>
		</span>
		<span class="un_medio"> 
			<input class="form-control" id="accesoscv" name="accesoscv" maxlength="2" size="3" dojoType="dijit.form.ValidationTextBox" required="true" regExp="^[0-9]{1,5}$" value="${EventUserForm.accesoscv}"
				   invalidMessage="Dato no válido" trim="true" missingMessage="Acceso o captura de CV es requerido."/>               			           			    					
		</span>	
	</div>
	<div class="entero control-group" style="margin:10px 0">
		<span class="un_medio">
			<strong><label for="impresioncv">Impresión</label></strong>
		</span>
		<span class="un_medio">
			<input class="form-control" id="impresioncv" name="impresioncv" maxlength="2" size="3" dojoType="dijit.form.ValidationTextBox" required="true" regExp="^[0-9]{1,5}$" value="${EventUserForm.impresioncv}"
				   invalidMessage="Dato no válido" missingMessage="Impresión de CV es requerido" trim="true" />               			           			    					
		</span>	
	</div>
	<div class="entero control-group" style="margin:10px 0">
		<span class="un_medio">
			<strong><label for="llamadas">Llamada telefónica</label></strong>
		</span>
		<span class="un_medio"> 
			<input class="form-control" id="llamadas" name="llamadas" maxlength="2" size="3" dojoType="dijit.form.ValidationTextBox" required="true" regExp="^[0-9]{1,5}$" value="${EventUserForm.llamadas}"
				   invalidMessage="Dato no válido" missingMessage="Llamadas es requerido" trim="true" />              			           			    					
		</span>
	</div>
	<div class="entero form-group" style="margin:10px 0">
		<span class="un_medio">
			<strong><label for="fotocopias">Fotocopiado</label></strong>
		</span>
		<span class="un_medio">
			<input class="form-control" id="fotocopias" name="fotocopias" maxlength="2" size="3" dojoType="dijit.form.ValidationTextBox" required="true" regExp="^[0-9]{1,5}$" value="${EventUserForm.fotocopias}"
				   invalidMessage="Dato no válido" missingMessage="Fotocopias es requerido" trim="true" />               			           			    					
		</span>	
	</div>
	<div class="entero control-group" style="margin:10px 0">
		<span class="un_medio">
			<strong>Acceso a portal del empleo</strong><br />(Escribe las secciones que visitaste del Portal del Empleo)
		</span>
		<span class="un_medio">
			<textarea class="form-control" id="portal" name="portal" dojoType="dijit.form.Textarea" maxLength="150" style="width:200px; height:100px;" required="true" invalidMessage="Dato no válido" trim="true" missingMessage="El acceso al portal es requerido.">${EventUserForm.portal}</textarea>
		</span>		
	</div>
	<div class="entero control-group" style="margin:20px 0">
		<span class="un_medio">
			<strong>Consulta a otras bolsas de trabajo</strong><br />(Escribe las direcciones de las bolsas de trabajo que visitaste. Ej. http://www.occ.com.mx/)
		</span>
		<span class="un_medio">
			<textarea class="form-control" id="bolsas" name="bolsas" dojoType="dijit.form.Textarea" maxLength="150" style="width:200px; height:100px;" required="true" invalidMessage="Dato no válido" trim="true" missingMessage="La consulta a otras bolsas de trabajo es requerida.">${EventUserForm.bolsas}</textarea>             			           			    					               			           			    					
		</span>		
	</div>
	<div class="entero" id="divRegis" style="text-align: center;">         			    				   
		<span class="un_medio">                                        
			<input type="button" id="btnGuardar" value="Guardar" class="boton" onclick="javascript:doSubmitAjax();" />
			<input type="button" id="btnCancel" value="Cancelar" class="boton" onclick="cancelConfirmation();" />
		</span>
	</div>
	<div class="un_tercio" dojoType="dijit.Dialog" id="msgAlert" title="msg" style="display:none" draggable ="false" >
		  <table class="alertDialog" >
			 <tr align="center">				 	
				 <td><div id ="msg" style="width:250px;height:50px;overflow:auto;vertical-align:middle;"></div>&nbsp;</td>			 
			 </tr>
		 </table>	
	</div>
</form></div>