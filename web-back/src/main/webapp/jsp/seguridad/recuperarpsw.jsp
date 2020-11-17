
<%@ page import="mx.gob.stps.portal.web.security.form.RecuperarPswForm" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<% 
	RecuperarPswForm pswForm = (RecuperarPswForm)session.getAttribute("recuperarPswForm");	
%>

<script type="text/javascript">
	dojo.require("dijit.dijit"); // loads the optimized dijit layer
	dojo.require("dijit.form.Form");
	dojo.require("dijit.form.ValidationTextBox");
	dojo.require("dijit.form.TextBox");
	dojo.require("dijit.form.ComboBox");
	dojo.require("dojo.data.ItemFileReadStore");
	dojo.require('dijit.Dialog');
	dojo.require("dijit.form.CheckBox");
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

function doSubmit(method){
	recargarVentana();
}

function recargarVentana(){
	setTimeout("window.location.reload()",0);   		
}

function doSubmitAjax(tipo){

	tipo = getMetodo(tipo);
	
	document.getElementById("btnEnviar").disabled = true;
	document.getElementById("btnCancel").disabled = true;
	
	if (validarCampos(tipo)){
	
		dojo.byId('method').value = 'recuperaPsw' + tipo;
				
		dojo.xhrPost(
					 {
					  url: 'recuperarpsw.do',
					  form:'passwordForm',
					  timeout:180000, // Tiempo de espera 3 min
					  load: function(data){
						    var res = dojo.fromJson(data); // Datos del servidor
						if (res.type == 'ext'){
							mensaje(res.message);
							limpiarControles();
						} else if (res.type == 'err'){
							mensaje(res.message);
						}
					
					    }
					} 
				 );
			}

	document.getElementById("btnEnviar").disabled = false;
	document.getElementById("btnCancel").disabled = false;
	
		}

	function validarCampos(tipo){
	
	if('SinTipo'== tipo){
		mensaje("Recuerda seleccionar un tipo de Usuario.");
		return false;	
	}
	if('Candidato' == tipo && !dijit.byId('curp').isValid()){
		mensaje("El Formato de la Curp es Inválido.");
		return false;	
	}	
	if('Empresa' == tipo && !dijit.byId('idPortalEmpleo').isValid()){
		mensaje("El Formato del ID del Portal del Empleo es Inválido.");
		return false;	
	}	
	if(!validarFormatoCorreoValue(dojo.byId('correoActual').value)){
		mensaje("El Correo actual es Invalidado.");
		return false;	
	}	
	if(dijit.byId("cambioCorreo").get("checked") != false &&
	 !validarFormatoCorreoValue(dojo.byId('correoNuevo').value)){
		mensaje("El Correo nuevo es Inválido.");
		return false;	
	}
	if(dijit.byId("cambioCorreo").get("checked") != false &&	
	  !validarFormatoCorreoValue(dojo.byId('correoNuevoConfirma').value)){
		mensaje("El Correo nuevo por confirmar es Inválido.");
		return false;	
	}
	if(dojo.byId('correoNuevo').value != 
			dojo.byId('correoNuevoConfirma').value){
		mensaje("Los Correos de confirmacion son distintos.");
		return false;	
	}		
	return true;
	}	

	function mensaje(mensaje){
		dojo.byId('mensaje').innerHTML = mensaje;
		dijit.byId('MensajeAlert').show();		
	}
	
	function validarFormatoCorreoValue(correo) {	
		var regExp = /^[a-zA-Z0-9_-]+(?:\.[a-zA-Z0-9_-]+)*@(?:[a-zA-Z0-9](?:[a-zA-Z0-9-_]*[a-zA-Z0-9-])?\.)+[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?$/;
		if (!regExp.test(correo)) {
			mensaje('Formato de correo electronico inválido');
			return false;		
			}
			return true;
	}
	
	function validarFormatoCorreo(correo) {
		var regExp = /^[a-zA-Z0-9_-]+(?:\.[a-zA-Z0-9_-]+)*@(?:[a-zA-Z0-9](?:[a-zA-Z0-9-_]*[a-zA-Z0-9-])?\.)+[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?$/;
	if (!regExp.test(correo.value)) {
		mensaje('Formato de correo electronico inválido');		
		}	
	}
	
      dojo.addOnLoad(function() {  
      	limpiarControles();
      	mostraDivCorrecto('${recuperarPswForm.tipo}');	
      	document.getElementById("tipoUsuarioEmpresa").checked = false;
      	document.getElementById("tipoUsuarioCandidato").checked = false;		
       }); 
	
	
	function limpiarControles(){
 	  if('Candidato' == '${recuperarPswForm.tipo}'){    
     	dojo.byId('curp').value ='';
      }
      if('Empresa'   == '${recuperarPswForm.tipo}'){
     	dojo.byId('idPortalEmpleo').value ='';
      }	     	
     	dojo.byId('correoActual').value ='';	
     	dojo.byId('correoNuevo').value ='';
     	dojo.byId('correoNuevoConfirma').value ='';
	}
	
	function mostrarCorreoNuevo(){		
		dojo.byId('correoNuevo').value ='';
     	dojo.byId('correoNuevoConfirma').value ='';
     	
		if (dijit.byId("cambioCorreo").get("checked") != false){
			document.getElementById("divCorreoNuevo").style.display='block'; 
		}else{
			document.getElementById("divCorreoNuevo").style.display='none';
		}
	}
	
	function mostraDivCorrecto(opcion){
	
		if('Empresa' == opcion) {
			document.getElementById("divEmpresa").style.display='block'; 
		} else if('Candidato' == opcion){
			document.getElementById("divCandidato").style.display='block'; 
		} else {
		document.getElementById("divTipoUsuario").style.display='block';
		}	
	}
	
	function mostrarDivTipo(){		
		if(document.getElementById("tipoUsuarioEmpresa").checked){
			document.getElementById("divEmpresa").style.display='block';
			document.getElementById("divCandidato").style.display='none';
		} else {
			document.getElementById("divCandidato").style.display='block';
			document.getElementById("divEmpresa").style.display='none'; 
		}	
	}
	
	function getMetodo(tipo){
		if('SinTipo' == tipo && document.getElementById("tipoUsuarioEmpresa").checked){
			return 'Empresa';
	    } else if('SinTipo' == tipo && document.getElementById("tipoUsuarioCandidato").checked){
			return 'Candidato';
		} else {
			return tipo;
		}
	
	}
	
	function noArrastrar(correoElectronico){
		var aux = correoElectronico.value;
		correoElectronico.value = '';
		correoElectronico.value = aux;
	}
	
</script>

<html:messages id="errors">
	<span class="redFont Font80"><bean:write name="errors"/></span><br/>
</html:messages>

<html:messages id="messages" message="true">
	<span class="Font80"><bean:write name="messages"/></span><br/>
</html:messages>


<form name="passwordForm" id="passwordForm" method="post" action="recuperarpsw.do" dojoType="dijit.form.Form">
	<input type="hidden" name="method" id="method" value="init"/> <!-- indica el metodo a invocar -->

	<h3>Recuperar contraseña ${recuperarPswForm.tipo != "SinTipo" ? recuperarPswForm.tipo : "" }</h3>
	
	<div id="divTipoUsuario" style = "display: none">
		 <p class="un_tercio">
			 <strong>Tipo de Usuario</strong>
			 	<br/>
			 	
	           Empresa<input type="radio" name="tipoUsuario" 	
	           			value="1" id="tipoUsuarioEmpresa" onclick="mostrarDivTipo()">
	           	
	           	&nbsp;&nbsp;&nbsp;
	           	
	           Candidato<input type="radio" name="tipoUsuario"  
	           			value="2" id="tipoUsuarioCandidato" onclick="mostrarDivTipo()">
	    </p>
	</div>
	
	<div>
		<div id="divCandidato" style = "display: none">
	        <p class="entero">       
				<strong>CURP*:</strong><br/>
				<input id="curp" name="curp" required="true" 
		               dojoType="dijit.form.ValidationTextBox" regExp="[A-Z]{4}\d{6}[HM][A-Z]{2}[B-DF-HJ-NP-TV-Z]{3}[A-Z0-9][0-9]" 
					   invalidMessage="Dato inválido" maxlength="18" uppercase="true" clearOnClose="true" trim="true"
					   style="font-family:Arial, Helvetica, sans-serif;font-size:12px; color:#444444;width:300px;"/>
		     
	        </p>
     </div>       
      	<div id="divEmpresa" style = "display: none">
	        <p class="entero">
				<strong>ID del Portal del Empleo*:</strong><br/>
				<input id="idPortalEmpleo" name="idPortalEmpleo" required="true"
		               dojoType="dijit.form.ValidationTextBox" regExp="^[A-Za-z0-9áéíóúÁÉÍÓÚñÑ]{17}$"
					   invalidMessage="Dato inválido" maxlength="21" uppercase="true" clearOnClose="true" trim="true"
					   style="font-family:Arial, Helvetica, sans-serif;font-size:12px; color:#444444;width:300px;" />
	        </p>
	    </div>        
		<p class="entero">
			<strong>Correo electrónico*:</strong><br/>			
		       <input type="text" name="correoActual" id="correoActual" size="40" maxlength="65" 
                  	onchange="validarFormatoCorreo(this);" oncopy="return false;" onselect="noArrastrar(this);" 
                  	oncut="return false" onpaste="return false;" />
        </p>
        
                                                                                             <p class="entero"  style="display: block">  
	         <label for="cambioCorreo"><strong>Deseas cambiar el correo:</strong></label>
		      <input type="checkbox" id="cambioCorreo" name="cambioCorreo" value="true" onclick="mostrarCorreoNuevo()"
		             dojoType="dijit.form.CheckBox">
		</p>
		
	<div id = "divCorreoNuevo" style="display: none">  
		<p class="un_tercio">
			<strong>Nuevo correo electrónico*:</strong><br/>
			<input type="text" name="correoNuevo" id="correoNuevo" size="40" maxlength="65" 
                    	onchange="validarFormatoCorreo(this);" oncopy="return false;" onselect="noArrastrar(this);"
                    	oncut="return false" onpaste="return false;" />
        </p>
		<p class="un_tercio">
			<strong>Confirma correo electrónico*:</strong><br/>		
		   <input type="text" name="correoNuevoConfirma" id="correoNuevoConfirma" size="40" maxlength="65" 
                	onchange="validarFormatoCorreo(this);" oncopy="return false;"  onselect="noArrastrar(this);"
                	oncut="return false" onpaste="return false;" />
        </p>
     </div>
        <p class="un_tercio">&nbsp;</p>
        <p class="entero" >
			<input type="button" id="btnEnviar" value="Enviar" class="boton" 
					onclick="doSubmitAjax('${recuperarPswForm.tipo}');"/>
			<input type="button" id="btnCancel" value="Cancelar" class="boton" onclick="doSubmit('init');" />
        </p>
	</div>

	<div id="message"></div>
</form>

	<div dojoType="dijit.Dialog" id="MensajeAlert" title="Mensaje" style="display:none" draggable ="false">
  		<table class="alertDialog" >
			 <tr align="center">				 
				 <td><div id ="mensaje" style="width:400px;height:400px;overflow:auto;vertical-align:middle;"></div>&nbsp;</td>		 
			 </tr>
		 </table>	
	</div>
	
	<%
	if(pswForm != null){
		pswForm.setTipo(null);
	}
	%>