<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="mx.gob.stps.portal.persistencia.vo.CatalogoOpcionVO"%>
<%@ page import="mx.gob.stps.portal.web.company.form.ComRegContactFormv2"%>
<%@ page import="mx.gob.stps.portal.core.empresa.vo.RegistroContactoVO"%>
<%@ page import="mx.gob.stps.portal.core.infra.utils.Constantes"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style type="text/css">
	.red3Font{
		color: #FF0000;
	}
	.Font60{
		font-size: 60%;
	}
</style>
<%
	String context = request.getContextPath() +"/";  
	String CLAVE_TIPO_PERSONA_FISICA = request.getSession().getAttribute("CLAVE_TIPO_PERSONA_FISICA").toString();
	String CLAVE_TIPO_PERSONA_MORAL = request.getSession().getAttribute("CLAVE_TIPO_PERSONA_MORAL").toString();  
	String DES_TIPO_EMPRESA_PADRE = request.getSession().getAttribute("DES_TIPO_EMPRESA_PADRE").toString();
	String ESTATUS_ACTIVO_ID = request.getSession().getAttribute("ESTATUS_ACTIVO_ID").toString();
	String ESTATUS_ACTIVO_DES = request.getSession().getAttribute("ESTATUS_ACTIVO_DES").toString(); 
	String ESTATUS_INACTIVO_ID = request.getSession().getAttribute("ESTATUS_INACTIVO_ID").toString(); 
	String ESTATUS_INACTIVO_DES = request.getSession().getAttribute("ESTATUS_INACTIVO_DES").toString();  
	String CLAVE_TELEFONO_FIJO = request.getSession().getAttribute("CLAVE_TELEFONO_FIJO").toString();
	String CLAVE_TELEFONO_CELULAR = request.getSession().getAttribute("CLAVE_TELEFONO_CELULAR").toString();
  	ComRegContactFormv2 formContact = (ComRegContactFormv2)session.getAttribute("ComRegContactFormv2");
  	List<RegistroContactoVO> lstContactos = (List<RegistroContactoVO>) request.getSession().getAttribute("LST_CONTACTOS");
    String idTipo = String.valueOf(formContact.getPadreIdTipoPersona()); 
    String strName = String.valueOf(formContact.getPadreLeyendaNombre());
    String strDesTipoPersona = ""; 
  	if(CLAVE_TIPO_PERSONA_FISICA.equalsIgnoreCase(idTipo)){
  		strDesTipoPersona = Constantes.TIPO_PERSONA.PERSONA_FISICA.getTipoPersona();
  	} else {
  		strDesTipoPersona = Constantes.TIPO_PERSONA.PERSONA_MORAL.getTipoPersona();
  	}        
  	long idContacto = formContact.getIdContacto();
%>

<script src="dojotoolkit/dojo/dojo.js" djConfig="parseOnLoad: true, locale:'es'"></script>
<noscript>Funciones de JavaScript desactivadas por navegador</noscript>
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
    dojo.require("dojo.parser");
</script>    
    
<script type="text/javascript">    
    function confirmParent() {
    	var answer = confirm("Los datos no guardados se perderán ¿Continuar?");
    	if (answer){
    		location.replace('<%=context%>comupdcompany.do?method=init');
    	}
    }        
    
  //funcion para obtener el valor de un campo 
    function getAnyElementValueById(elementId){
    	var vElement = dijit.byId(elementId).value;
    	if(vElement==undefined || vElement==''){
    		vElement = document.getElementById(elementId).value;
    	}
    	return vElement;
    }	    
    
    function doSubmit(method){
        var vForm = dijit.byId('ComRegContactFormv2');
        document.getElementById('method').value=method;
        dojo.byId('ComRegContactFormv2').submit();
    }    
    
    function doSubmitAjax(method){
    	var vCampos = '';
    	document.getElementById('method').value=method;
    	var vForm = dijit.byId('ComRegContactFormv2');
    	if(method=='cambiarEstatus' || method=='editar'){
    		//cambiarEstatus: deshabilitar boton Guardar, setear datos de catalogos de domicilio y codigo postal
			var vSelectedContact = document.getElementById('selectContact').selectedIndex;
			if(vSelectedContact<1){
				if(method=='cambiarEstatus'){
					alert('Debe seleccionar un contacto para modificar su estatus');
				} else if(method=='editar'){
					alert('Debe seleccionar un contacto para modificar sus datos');
				}
				document.getElementById('selectContact').focus();	
			} else {
				vForm = document.getElementById('ComRegContactFormv2');
				vForm.submit();				    		
			}
    	} else if(method=='salvar'){
    		if (vForm.isValid()){    			
    			dojo.byId('btnGuardar').disabled=true;
    			document.getElementById('method').value=method;
    			dojo.byId('message').innerHTML = '';     		    			    			
    			if (dijit.byId('idEntidadSelect').get('item') && dijit.byId('idEntidadSelect').get('item').label){
    				dojo.byId('idEntidad').value = dijit.byId('idEntidadSelect').get('item').label[0];
    			}
    			if (dijit.byId('idMunicipioSelect').get('item') && dijit.byId('idMunicipioSelect').get('item').label){
    				dojo.byId('idMunicipio').value = dijit.byId('idMunicipioSelect').get('item').label[0];
    			}
    			if (dijit.byId('idColoniaSelect').get('item') && dijit.byId('idColoniaSelect').get('item').label){
    				dojo.byId('idColonia').value = dijit.byId('idColoniaSelect').get('item').label[0];
    			}						
    			dijit.byId("codigoPostal").setAttribute('value', dojo.byId('codigoPostalShow').value);
    			vForm.submit();	    			
    		} else {
        		vCampos = checkFields();
        		if(vCampos.length>0){	
        			var vMessage =  document.getElementById('message'); 
        			vMessage.innerHTML = '';
        			vMessage.innerHTML = 'Los siguientes campos muestran datos inválidos - Se muestra(n) el (los) campo(s) con inconsistencias: <br/>' + vCampos;
        			//setMensaje(vMessage);	
        		}
    		}    			
    	} else if(method=='nuevo'){
    		//nuevo    			
			var answer = confirm("¿Está seguro que desea limpiar el formulario para capturar un nuevo contacto? Todos sus cambios se perderán.");
			if (answer){								
				document.getElementById('idContacto').value='';
				doSubmit('init');	//nuevo?				
			}    		
    	} else if(method=='cancelar'){
    		//cancelar
			var answer = confirm("¿Está seguro que desea cancelar la operación? Todos sus cambios se perderán.");
			if (answer){
				doSubmit('init');	//cancelar?
			}
    	}
    }	

    function limpiar(){
		if (dijit.byId('idTipoTelefono')) dijit.byId('idTipoTelefono').set('value', '');
		if (dijit.byId('codigoPostal')) dijit.byId('codigoPostal').set('value', '');	
		if (dijit.byId('codigoPostalShow')) dijit.byId('codigoPostal').set('value', '');			
		if (dijit.byId('idEntidad')) dijit.byId('idEntidad').set('value', '');
		if (dijit.byId('idMunicipio')) dijit.byId('idMunicipio').set('value', '');
		if (dijit.byId('idColonia')) dijit.byId('idColonia').set('value', '');
		if (dijit.byId('nombreContacto')) dijit.byId('nombreContacto').set('value', '');
		if (dijit.byId('cargo')) dijit.byId('cargo').set('value', '');
		if (dijit.byId('calle')) dijit.byId('calle').set('value', '');
		if (dijit.byId('numeroExterior')) dijit.byId('numeroExterior').set('value', '');
		if (dijit.byId('numeroInterior')) dijit.byId('numeroInterior').set('value', '');
		if (dijit.byId('entreCalle')) dijit.byId('entreCalle').set('value', '');
		if (dijit.byId('yCalle')) dijit.byId('yCalle').set('value', '');
		if (dijit.byId('acceso')) dijit.byId('acceso').set('value', '');
		if (dijit.byId('clave')) dijit.byId('clave').set('value', '');
		if (dijit.byId('telefono')) dijit.byId('telefono').set('value', '');
		if (dijit.byId('extension')) dijit.byId('extension').set('value', '');	
		if (document.getElementById('correoElectronico')) document.getElementById('correoElectronico').value = '';
		if (document.getElementById('confirmarCorreoElectronico')) document.getElementById('confirmarCorreoElectronico').value = '';
    }
    	
    function checkFields(){
    	var vCampos = '';
		var vContactoEmpresa = dijit.byId('nombreContacto').value;
		var vCargo = dijit.byId('cargo').value;
		var vCodigoPostal = getAnyElementValueById('codigoPostal');
		var vCalle = getAnyElementValueById('calle');			
		var vNumExterior = getAnyElementValueById('numeroExterior');
		var vEntreCalle = getAnyElementValueById('entreCalle');
		var vYCalle = getAnyElementValueById('yCalle');
		var vCorreoElectronico = document.getElementById('correoElectronico').value;
		var vConfirmarCorreoElectronico = document.getElementById('confirmarCorreoElectronico').value;	
		var vIdTipoTelefono = getRadioValue('idTipoTelefono');	
		var vAcceso = getAnyElementValueById('acceso');	
		var vClave = getAnyElementValueById('clave');	
		var vTelefono = getAnyElementValueById('telefono');	    
		var vSelectedTerceraEmpresa = document.getElementById('selectTerceraEmpresa').selectedIndex;		
		if(!vContactoEmpresa){
			vCampos = vCampos + ' Nombre<br/>';
			dijit.byId('nombreContacto').focus();
		}
		if(!vCargo){
			vCampos = vCampos + ' Cargo<br/>';
			dijit.byId('cargo').focus();
		}					
		if(vSelectedTerceraEmpresa<1){
			vCampos = vCampos + ' Tercera Empresa<br/>';
			document.getElementById('selectTerceraEmpresa').focus();			
		}		
		if(!vCodigoPostal){
			vCampos = vCampos + ' Código postal<br/>';
			dijit.byId('codigoPostal').focus();
		}		
		if(!vCalle){
			vCampos = vCampos + ' Calle<br/>';
			dijit.byId('calle').focus();
		}		
		if(!vNumExterior){
			vCampos = vCampos + ' Número exterior<br/>';
			dijit.byId('numeroExterior').focus();
		}		
		if(!vEntreCalle){
			vCampos = vCampos + ' Entre calle<br/>';
			dijit.byId('entreCalle').focus();
		}		
		if(!vYCalle){
			vCampos = vCampos + ' Y calle<br/>';
			dijit.byId('yCalle').focus();
		}		
		if(!vCorreoElectronico){
			vCampos = vCampos + ' Correo electrónico<br/>';
			document.getElementById('correoElectronico').focus();
		}		
		var vStyle = document.getElementById('confirmarCorreoElectronico').style.visibility;
		var hideDiv = vStyle == 'hidden' ? true : false;
		setDiv('confCorreo', hideDiv);			
		if(!hideDiv && !vConfirmarCorreoElectronico){
			vCampos = vCampos + ' Confirmar correo electrónico.<br/>';
			document.getElementById('confirmarCorreoElectronico').focus();
		}
		if(!vConfirmarCorreoElectronico){
			vCampos = vCampos + ' Confirmar correo electrónico.<br/>';
			document.getElementById('confirmarCorreoElectronico').focus();
		}	
						
		if(null==vIdTipoTelefono){
			vCampos = vCampos + ' Tipo de Telefono principal<br/>';
		}					
		if(!vAcceso){
			vCampos = vCampos + ' Acceso del teléfono principal<br/>';
		}		
		if(!vClave){
			vCampos = vCampos + ' Clave Lada del teléfono principal<br/>';
			dijit.byId('clave').focus();
		}					
		if(!vTelefono){
			vCampos = vCampos + ' Teléfono principal<br/>';
			dijit.byId('telefono').focus();
		}	
		return vCampos;
    }
    	
    function validarFormatoCorreo(correo) {
    	//var regExp = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
    	var regExp = /^[a-zA-Z0-9_-]+(?:\.[a-zA-Z0-9_-]+)*@(?:[a-zA-Z0-9](?:[a-zA-Z0-9-_]*[a-zA-Z0-9])?\.)+[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?$/;
    	if (!regExp.test(correo.value)) {
    		alert('Formato de correo electrónico inválido');
    		correo.focus();
    	}
    }
    
    function showConf() {
    	var vStyle = document.getElementById('confCorreo').style.visibility;
    	var hideDiv = vStyle == 'hidden' ? true : false;
    	setDiv('confCorreo', hideDiv);
    	if (hideDiv) {
    		document.getElementById('correoElectronico').readOnly = false;
    	} else {
    		document.getElementById('correoElectronico').readOnly = true;
    		document.getElementById('correoElectronico').value = document.getElementById('hiddenPreCorreoElectronico').value;
    	}
    	document.getElementById('confirmarCorreoElectronico').value = '';
    }	

    function setDiv(id, visible) {
    	var vStyle = visible ? 'visible':'hidden';
    	if(document.getElementById(id)){
    		document.getElementById(id).style.visibility = vStyle;
    	}    	
    }	

    //Funcion para validar campos de verificacion
    function checkVerificationField(idField1, idField2, lblField1, lblField2){
    	var widget1 = dijit.byId(idField1);
    	var widget2 = dijit.byId(idField2);
    	if(widget1.value != widget2.value){
    		alert('Compruebe que el campo ' + lblField1 + ' y el campo ' + lblField2 + ' tengan el mismo valor.');
    		widget1.focus();
    	}
    }
    
  //funcion para obtener el valor de un radiobutton seleccionado
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
  
	//Desplegar ventana para captura de telefonos adicionales
	function openPhoneWindow(){	
	 	var newWin = window.open("<%=context%>phone.do?method=init", "Telefonos","height=500,width=900,resizable=yes,scrollbars=yes"); 	
	} 	
	
	//Dependiendo de la seleccion de tipo de telefono, coloca la clave de acceso correspondiente
	function fillUpAccessKey(){
		var tipoTelefonoId = getRadioValue('idTipoTelefono');
		var accesoDes;
        if(tipoTelefonoId=='1'){ 
        	accesoDes = '<%=CLAVE_TELEFONO_CELULAR%>';
        } else {
        	accesoDes = '<%=CLAVE_TELEFONO_FIJO%>';
        }      		
        var wAcceso = dijit.byId('accesoShow');
        wAcceso.attr('disabled', false);
        wAcceso.attr('value',accesoDes);
        wAcceso.attr('disabled', true);
        var vAcceso = document.getElementById('acceso');
        vAcceso.value=accesoDes;
	}	
	
	//funcion para validar que los tamaños de la clave LADA y el telefono correspondan
	function changePhoneSizeRequired(){
		var vClave = document.getElementById('clave');
		var vTelefono = document.getElementById('telefono');
	 
		if(vClave.value.length<2){
			alert('Debe proporcionar una Clave Lada de 2 ó 3 caracteres');
			document.getElementById('clave').focus();
		} else if(vClave.value.length==2) {
			if(vTelefono.value.length!=8){
				alert('Debe proporcionar un teléfono de 8 dígitos');
				document.getElementById('telefono').focus();
			}
		} else if(vClave.value.length==3){
			if(vTelefono.value.length!=7){
				alert('Debe proporcionar un teléfono de 7 dígitos');
				document.getElementById('telefono').focus();
			}	
		}
		
	}		
    
   	function setMensaje(mensaje){
   		dojo.byId('mensaje').innerHTML = mensaje;
   		dijit.byId('MensajeAlert').show();		
   	}    	
    	
</script>
	
<form name="ComRegContactFormv2" id="ComRegContactFormv2" method="post" action="comregcontactv2.do" 
	dojoType="dijit.form.Form"  dojoattachevent="onreset:_onReset,onsubmit:_onSubmit">
	<input type="hidden" name="method" id="method" value="init" />	
	<input type="hidden" name="idContacto" id="idContacto" value="${ComRegContactFormv2.idContacto}"/>
	<input type="hidden" name="Ctrl" id="Ctrl" dojoType="dijit.form.TextBox" value="1"/>
	<input type="hidden" name="cpnuevo" id="cpnuevo" dojoType="dijit.form.TextBox" value="1"/>	
	<input type="hidden" name="acceso" id="acceso" dojoType="dijit.form.TextBox" value="${ComRegContactFormv2.acceso}" />
	<input type="hidden" name="codigoPostalShow" id="codigoPostalShow" dojoType="dijit.form.TextBox" value="${ComRegContactFormv2.codigoPostal}" />
	<input type="hidden" name="hiddenPreCorreoElectronico" id="hiddenPreCorreoElectronico" value="${ComRegContactFormv2.preCorreoElectronico}"/>
	<div class="formulario"> 
		<DIV class="entero">		
		<a href="#" onclick="confirmParent();" class="links">Regresar a Empresa padre (TESTING CONTACTOS)</a>
		</DIV>
		<%-- ENCABEZADO --%>
        <DIV class="entero">&nbsp;<br>             
	        <span class="un_cuarto"><strong>Id Portal del Empleo: </strong><br/><c:out value="${ComRegContactFormv2.padreIdPortalEmpleo}"/><br></span>			
	        <span class="un_cuarto"><strong>Tipo de persona: </strong><br/> <%=DES_TIPO_EMPRESA_PADRE%></span>			                
        </DIV>  
        <c:choose>         
	        <c:when test="${ComRegContactFormv2.padreIdTipoPersona eq '1'}">
		    	<DIV class="entero">
		    	<span class="un_cuarto">
		        <strong>Nombre(s): </strong><br/><c:out value="${ComRegContactFormv2.padreNombre}"/>
		    	</span>
		    	<span class="un_cuarto">
		        <strong>Primer apellido: </strong><br/><c:out value="${ComRegContactFormv2.padreApellido1}"/>
		    	</span>
		    	<span class="un_cuarto">
		        <strong>Segundo apellido: </strong><br/><c:out value="${ComRegContactFormv2.padreApellido2}"/>
		    	</span>
		    	</DIV>	        
	        </c:when>
	        <c:otherwise>
		    	<DIV class="entero">
		    	<span class="un_cuarto">
		        <strong>Razón social: </strong><br/><c:out value="${ComRegContactFormv2.padreRazonSocial}"/>
				</span>        
		        </DIV>	        
	        </c:otherwise>
        </c:choose>
		<%-- MENSAJES Y ERRORES --%>
		<DIV class="entero">
			<div id="message"></div>
			<html:messages id="errors" ><span class="Font60 red3Font"><bean:write name="errors"/></span><br/></html:messages>					
			<html:messages id="messages" message="true" ><span class="Font60 red3Font"><bean:write name="messages"/></span><br/></html:messages>    				                     
        </DIV> 	        
		<%-- LISTADO DE CONTACTOS REGISTRADOS --%>
		<h3>Contactos registrados</h3>
		<DIV>
	    	<DIV class="entero">
		    	<span class="un_medio">
		    	<strong><label for="selectContact">Contactos registrados - Estatus</label></strong><br>
				<select id="selectContact" name="selectContact" size="1" style="width:100%">
				<option value="-1">Seleccionar</option>
				<c:if test="${not empty ComRegContactFormv2.lstContactos}">
					<c:forEach var="contacto" items="${ComRegContactFormv2.lstContactos}" varStatus="rowCounter">
						<c:if test="${contacto.estatus == 1 }">
							<option value="${contacto.idContacto}">${contacto.nombreContacto} - ACTIVO</option>
						</c:if>
						<c:if test="${contacto.estatus == 2 }">
							<option value="${contacto.idContacto}">${contacto.nombreContacto} - INACTIVO</option>
						</c:if>						
					</c:forEach>
				</c:if>		
				</select> 		    	
				</span>    
			</DIV>
			<DIV class="un_medio">   
		    	<span class="un_cuarto">
				<input type="button" id="btnNuevo" value="Nuevo" class="boton" onclick="doSubmitAjax('nuevo');" />	    	
				</span>        				
		    	<span class="un_cuarto">
				<input type="button" id="btnEditar" value="Editar" class="boton" onclick="doSubmitAjax('editar');" />	    	
				</span>							
		    	<span class="un_cuarto">
				<input type="button" id="btnCambiarEstatus" value="Cambiar estatus" class="boton" onclick="doSubmitAjax('cambiarEstatus');" />	    	
				</span>        							      		
	        </DIV>	
		</DIV>			        
		<%-- FORMULARIO PARA REGISTRO Y EDICION DEL CONTACTO --%>
		<h3>Registro de contacto</h3>
		<input type="hidden" name="estatus" id="estatus" value="${ComRegContactFormv2.estatus}"/>	
		<DIV>				
	    	<DIV class="entero">&nbsp;<br>
	    	<span class="un_tercio"><strong><label for="nombreContacto">Nombre</label>*</strong><br/>
	        <input id="nombreContacto" name="nombreContacto" maxlength="150" size="50" 	        	
	        	dojoType="dijit.form.ValidationTextBox" required="false" regExp="^[A-Za-z\s\-.áéíóúÁÉÍÓÚñÑ/']{1,150}$" uppercase="true"
	        	value="${ComRegContactFormv2.nombreContacto}"
	        	invalidMessage="Dato inválido" missingMessage="Dato requerido" trim="true"/>
	               			    
	    	</span>
	    	</DIV>
    		<DIV class="entero">&nbsp;<br>
	    	<span class="un_tercio">
	        <strong><label for="cargo">Cargo del contacto</label>*</strong><br/>
	        <input id="cargo" name="cargo" maxlength="150" size="50" 	        	
	        	dojoType="dijit.form.ValidationTextBox" required="false" regExp="^[A-Za-z\s\-.áéíóúÁÉÍÓÚñÑ/']{1,150}$"  uppercase="true"
	        	value="${ComRegContactFormv2.cargo}"
	        	invalidMessage="Dato inválido" missingMessage="Dato requerido" trim="true"/>
	               			    
	    	</span>
    		</DIV> 
    		<DIV class="entero">&nbsp;<br>
	    	<span class="un_medio">
	        <strong><label for="selectTerceraEmpresa">Tercera empresa</label>*</strong><br/>
			<select id="selectTerceraEmpresa" name="selectTerceraEmpresa" size="1" style="width:100%">
	    	<%		    
	    	if(idContacto>0){
	    		%>	 				
				<c:choose>
					<c:when test="${ComRegContactFormv2.idTerceraEmpresa==0}">
						<option value="0" selected>Ninguna</option>						
					</c:when>
					<c:otherwise>
						<option value="0">Ninguna</option>					
					</c:otherwise>
				</c:choose>				
				<c:if test="${not empty ComRegContactFormv2.lstTercerasEmpresas}">
					<c:forEach var="terceraEmpresa" items="${ComRegContactFormv2.lstTercerasEmpresas}" varStatus="rowCounter">
						<c:choose>
							<c:when test="${terceraEmpresa.idTerceraEmpresa == ComRegContactFormv2.selectTerceraEmpresa}">
								<option value="${terceraEmpresa.idTerceraEmpresa}" selected>${terceraEmpresa.nombreEmpresa} 
								<c:if test="${terceraEmpresa.estatus == 1 }"> - ACTIVO</c:if>
								<c:if test="${terceraEmpresa.estatus == 2 }"> - INACTIVO</c:if>							
								</option>							
							</c:when>
							<c:otherwise>
								<option value="${terceraEmpresa.idTerceraEmpresa}">${terceraEmpresa.nombreEmpresa} 
								<c:if test="${terceraEmpresa.estatus == 1 }"> - ACTIVO</c:if>
								<c:if test="${terceraEmpresa.estatus == 2 }"> - INACTIVO</c:if>							
								</option>						
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</c:if>	    		 
	    		<%
	    	} else {
	    		%>	 
				<option value="-1">Seleccionar</option>
				<option value="0">Ninguna</option>
				<c:if test="${not empty ComRegContactFormv2.lstTercerasEmpresas}">
					<c:forEach var="terceraEmpresa" items="${ComRegContactFormv2.lstTercerasEmpresas}" varStatus="rowCounter">
						<c:if test="${terceraEmpresa.estatus == 1 }">
							<option value="${terceraEmpresa.idTerceraEmpresa}">${terceraEmpresa.nombreEmpresa} - ACTIVO</option>
						</c:if>
						<c:if test="${terceraEmpresa.estatus == 2 }">
							<option value="${terceraEmpresa.idTerceraEmpresa}">${terceraEmpresa.nombreEmpresa} - INACTIVO</option>
						</c:if>						
					</c:forEach>
				</c:if>		    		   		  	
	    		<%    		
	    	}
	    	%>  			
			</select> 		    	    
	    	</span>
    		</DIV>     		 		    		   	      			 
			<h3>Teléfono*</h3>       		
        	<DIV class="entero">  
		        <span class="un_tercio"><strong><label for="idTipoTelefono">Tipo de teléfono</label>*</strong><br/>
				<%
				long selectedPhoneType = (long)formContact.getIdTipoTelefono();
				List<CatalogoOpcionVO> lstTipoTelefono = (List<CatalogoOpcionVO>) request.getSession().getAttribute("CAT_TIPO_TELEFONO");					
				Iterator itLstTipoTelefono = lstTipoTelefono.iterator();
				while(itLstTipoTelefono.hasNext()){
					CatalogoOpcionVO vo = (CatalogoOpcionVO) itLstTipoTelefono.next();						
					%>
				<%=vo.getOpcion()%>&nbsp;
				<input type="radio" id="idTipoTelefono" name="idTipoTelefono" 
				<% if(selectedPhoneType==vo.getIdCatalogoOpcion()) { out.print(" checked "); }%>
				value="<%=String.valueOf(vo.getIdCatalogoOpcion())%>" onClick="fillUpAccessKey();">&nbsp;&nbsp;
				<%					
				}							
				%>				          
		        </span> 			 
		        <span class="un_tercio"><strong><label for="accesoShow">Acceso</label>*</strong><br/>
				 <input id="accesoShow" name="accesoShow" maxlength="3" size="3"
				 	dojoType="dijit.form.ValidationTextBox" required="false" regExp="^(<%=CLAVE_TELEFONO_CELULAR%>|<%=CLAVE_TELEFONO_FIJO%>)$"
				 	value="${ComRegContactFormv2.acceso}"
				 	invalidMessage="Dato inválido" missingMessage="Dato requerido" trim="true" disabled="disabled" />
				       			        
		        </span> 			 
		        <span class="un_tercio"><strong><label for="clave">Clave Lada</label>*</strong><br/> 
				<input id="clave" name="clave" maxlength="3" size="3"
				 	dojoType="dijit.form.ValidationTextBox" required="true" regExp="^[0-9]{2,3}$"
				 	value="${ComRegContactFormv2.clave}"
				 	invalidMessage="Dato inválido" missingMessage="Dato requerido" trim="true" />	        			        
		        </span> 		
        	</DIV>
	        <DIV class="entero"> 	 
		        <span class="un_tercio"><strong><label for="telefono">Teléfono</label>*</strong><br/>
				<input id="telefono" name="telefono" maxlength="8" size="8" onBlur="changePhoneSizeRequired()"
				 	dojoType="dijit.form.ValidationTextBox" required="true" regExp="^[0-9]{7,8}$"
				 	value="${ComRegContactFormv2.telefono}"			 	
				 	invalidMessage="Dato inválido" missingMessage="Dato requerido" trim="true" />
				        			          
		        </span> 			 
		        <span class="un_tercio"><strong><label for="extension">Extensión</label></strong><br/>
				<input id="extension" name="extension" maxlength="8" size="8"
				 	dojoType="dijit.form.ValidationTextBox" required="false" regExp="^[0-9]{1,6}$"
				 	value="${ComRegContactFormv2.extension}"
				 	invalidMessage="Dato inválido" missingMessage="Dato requerido" trim="true" />
				        			           
		        <a href="#" class="links" onclick="openPhoneWindow()">Agregar teléfono</a>
		        </span>
	    	</DIV>      		    	
	    	<%		    
	    	if(idContacto>0){
	    		%>
	           <h3>Cuenta de correo electrónico del contacto</h3>
	           <p class="entero">
				   <strong><label for="correoElectronico">Correo electrónico</label>*</strong><br/>
			       <input type="text" name="correoElectronico" id="correoElectronico" size="50" maxlength="65" trim="true"
			       value="${ComRegContactFormv2.correoElectronico}" onchange="validarFormatoCorreo(this);" oncopy="return false;" 
			       oncut="return false" onpaste="return false;" readonly="true"  />			        
	           </p>
	           <div id="confCorreo" class="entero" style="visibility:hidden;">
				   <strong><label for="confirmarCorreoElectronico">Confirmar correo electrónico</label>*</strong><br/>
			       <input type="text" name="confirmarCorreoElectronico" id="confirmarCorreoElectronico" size="50" maxlength="65" trim="true"
			       value="${ComRegContactFormv2.correoElectronico}" onchange="validarFormatoCorreo(this);" oncopy="return false;" oncut="return false" 
			       onpaste="return false;" ondragover="return false;" />			        				        
			   </div>	
			   <p class="entero"><a href="javascript:showConf();" class="un_cuarto links">Actualizar correo electr&oacute;nico</a></p>    		
	    		<%
	    	} else {
	    		%>
				<h3>Cuenta de correo electrónico del contacto</h3>
		        <DIV class="entero">  
			        <span class="un_medio">  
			        <strong><label for="correoElectronico">Correo electrónico</label>*</strong><br/>
			       	<input type="text" name="correoElectronico" id="correoElectronico" size="50" maxlength="65" 
			       	value="${ComRegContactFormv2.correoElectronico}" onchange="validarFormatoCorreo(this);" oncopy="return false;" 
			       	oncut="return false" onpaste="return false;"  />			        		             			           
			        </span> 			
		        </DIV>
		        <DIV class="entero">
			        <span class="un_medio">   
			        <strong><label for="confirmarCorreoElectronico">Confirmar correo electrónico</label>*</strong><br/>
			        <input type="text" name="confirmarCorreoElectronico" id="confirmarCorreoElectronico" size="50" maxlength="65" 
			        value="" onchange="validarFormatoCorreo(this);" oncopy="return false;" oncut="return false" 
			        onpaste="return false;" ondragover="return false;" />			                
			        </span> 			        			          			                			          
		    	</DIV>	    		
	    		<%    		
	    	}
	    	%>    	 
			<h3>Domicilio</h3>
			<DIV class="entero">
		       	<span class="un_tercio">
		      	<strong><label for="codigoPostal">Código postal</label>*</strong><br/>
				<input id="codigoPostal" name="codigoPostal" maxlength="5" size="5" value="${ComRegContactFormv2.codigoPostal}"
		        	dojoType="dijit.form.ValidationTextBox" required="true" regExp="^[0-9]{1,5}" 
		        	invalidMessage="Dato inválido" trim="true" />        
		        	       			   		
		    	</span>	
		    	<span class="un_tercio">
				<input type="checkbox" id="checkCtrl">
				<label for="checkCtrl">No conozco mi código postal</label>               			   
		    	</span>	
		    	<span class="un_tercio">
		    	<input type="button" name="btnValidar" id="btnValidar" class="boton" value="Validar CP">
		    	</span>
	    	</DIV>    			  
	    	<DIV class="entero">
		        <span class="un_tercio"><strong><label for="pais">País</label></strong><br/>			        
		        <input name="pais" id="pais" maxlength="10" size="6" value="México" disabled />
		        	
		        </span>
	        </DIV>        
	        <DIV class="entero">  
		        <span class="un_tercio">     			   
				<strong><label for="idEntidadSelect">Entidad federativa</label>*</strong><br/>
						<input type="hidden" name="idEntidad" id="idEntidad" value="" />
						<div dojoType="dojo.data.ItemFileReadStore" jsId="entidadFederativaStore" urlPreventCache="true" clearOnClose="true" ></div>
						<select dojotype="dijit.form.FilteringSelect" store="entidadFederativaStore" id="idEntidadSelect" required="false" disabled="disabled" autoComplete="false"></select>					
		        </span>
		        <span class="un_tercio">
		        <strong><label for="idMunicipioSelect">Delegación ó municipio</label>*</strong><br/>			
		        		<input type="hidden" name="idMunicipio" id="idMunicipio" value="" />	
						<div dojoType="dojo.data.ItemFileReadStore" jsId="municipioStore" urlPreventCache="true" clearOnClose="true" ></div>
						<select dojotype="dijit.form.FilteringSelect" store="municipioStore" id="idMunicipioSelect" required="false" disabled="disabled" autoComplete="false"></select>			        
		        </span>
		        <span class="un_tercio">
		        <strong><label for="idColoniaSelect">Colonia</label>*</strong><br/>		
		        <input type="hidden" name="idColonia" id="idColonia" value="" />
				<div dojoType="dojo.data.ItemFileReadStore" jsId="coloniaStore" urlPreventCache="true" clearOnClose="true" ></div>
				<select dojotype="dijit.form.FilteringSelect" store="coloniaStore" id="idColoniaSelect" required="false" disabled="disabled" autoComplete="true"></select>			        
		        </span> 
	    	</DIV>    			   
	        <DIV class="entero">  
		        <span class="un_tercio"><strong><label for="calle">Calle</label>*</strong><br/>
		        <input id="calle" name="calle" maxlength="150" size="60" value="${ComRegContactFormv2.calle}" 
		        	dojoType="dijit.form.ValidationTextBox" required="false" regExp="^[a-zA-ZáéíóúÁÉÍÓÚñÑ0-9\s]{1,150}$" uppercase="true"
		        	invalidMessage="Dato inválido" missingMessage="Dato requerido" trim="true"/>			             			   
		        
		        </span> 	        
		        <span class="un_tercio"><strong><label for="numeroExterior">Número exterior</label>*</strong><br/> 
		        <input id="numeroExterior" name="numeroExterior" maxlength="50" size="4" value="${ComRegContactFormv2.numeroExterior}"
		        	dojoType="dijit.form.ValidationTextBox" required="false" regExp="^[a-zA-ZáéíóúÁÉÍÓÚñÑ0-9\s]{1,50}" 
		        	invalidMessage="Dato inválido" trim="true" />        
		        				            			   
		        </span> 	        
		        <span class="un_tercio"><strong><label for="numeroInterior">Número interior</label></strong><br/>
		        <input id="numeroInterior" name="numeroInterior" maxlength="50" size="4" value="${ComRegContactFormv2.numeroInterior}"
		        	dojoType="dijit.form.ValidationTextBox" required="false" regExp="^[a-zA-ZáéíóúÁÉÍÓÚñÑ0-9\s]{1,50}" 
		        	invalidMessage="Dato inválido" trim="true" />        
		        				             			   
		        </span> 			        
	    	</DIV>    			  
	        <DIV class="entero">  
		        <span class="un_tercio"><strong><label for="entreCalle">Entre calles</label>*</strong><br/> 
		        <input id="entreCalle" name="entreCalle" maxlength="150" size="60" value="${ComRegContactFormv2.entreCalle}" 
		        	dojoType="dijit.form.ValidationTextBox" required="true" regExp="^[a-zA-ZáéíóúÁÉÍÓÚñÑ0-9\s]{1,150}$" uppercase="true"
		        	invalidMessage="Dato inválido" missingMessage="Dato requerido" trim="true"/>
							            			   
		        </span> 			      
		        <span class="un_tercio"><strong><label for="yCalle">Y</label>*</strong><br/>
		        <input id="yCalle" name="yCalle" maxlength="60" size="150" value="${ComRegContactFormv2.yCalle}" 
		        	dojoType="dijit.form.ValidationTextBox" required="false" regExp="^[a-zA-ZáéíóúÁÉÍÓÚñÑ0-9\s]{1,150}$" uppercase="true"
		        	invalidMessage="Dato inválido" trim="true"/>
		             			   
		        </span> 			        
	    	</DIV>	
			<DIV class="entero" id="divRegis" style="text-align: center;">         			    				   
		        <span class="un_tercio">                                        
				<input type="button" id="btnGuardar" value="Guardar" class="boton" onclick="doSubmitAjax('salvar');" />
				</span>
				<span class="un_tercio">
				<input type="button" id="btnCancel" value="Cancelar" class="boton" onclick="doSubmitAjax('cancelar');" />
				</span>		
	        </DIV>    	
     	</div>   
		<div dojoType="dijit.Dialog" id="MensajeAlert" title="Mensaje" style="display:none" draggable ="false">
			  <table width="300px" >
				 <tr align="center"><td><div id ="mensaje"></div>&nbsp;</td></tr>
			 </table>	
		</div>                                    
	</div>
</form>
  <script type="text/javascript">
  		function clearCP(){
  			//limpiar la casilla
  			dojo.byId('codigoPostal').value = '';
  		}
  		function clearCbo(cboId){
  			//limpiar listado
			var widgetCbo = dijit.byId(cboId);
			widgetCbo.reset();  			
  		}
  		function toggleCP(estado){
  			//habilitar o deshabilitar casilla codigoPostal
  			dijit.byId("codigoPostal").setAttribute('disabled', estado);
  		}
  		function toggleBtnValidar(estado){
  			//habilitar o deshabilitar boton Validar CP
    		var vValidar = document.getElementById('btnValidar');  		
    		vValidar.disabled = estado;  			  			
  		}
  		function toggleCbo(cboId, estado){
  			//habilitar o deshabilitar un listado
  			dijit.byId(cboId).setAttribute('disabled', estado);
  		}
  		
  		function enableCtrlCP(){
  			//Habilitar y limpiar casilla codigoPostal
  			toggleCP(false);
  			clearCP();
  			//Checkbox siempre esta habilitado
  			//Habilitar boton validar CP
  			toggleBtnValidar(false);
  			//limpiar listados EMC
  			clearCbo('idColoniaSelect');
  			clearCbo('idMunicipioSelect');
  			clearCbo('idEntidadSelect');
  			//deshabilitar listados EMC
  			toggleCbo('idEntidadSelect', true);
  			toggleCbo('idMunicipioSelect', true);
  			toggleCbo('idColoniaSelect', true);
  		}
  		
  		function fillCboEntidad(codigoPostal, tipoLlenado){
  			//llenar el combo de entidad
  			if(tipoLlenado=='JSON'){
  	        	entidadFederativaStore.url = "${context}comregcontactv2.do?method=obtenerEntidadJSON" + "&" + "codigoPostal="+ codigoPostal;
  	        	entidadFederativaStore.close();    		  				
  			} else {
        		entidadFederativaStore.url = "${context}comregcontactv2.do?method=obtenerEntidad";
        		entidadFederativaStore.close();     
  			}
  		}

  		function fillCboMunicipio(codigoPostal, tipoLlenado){
  			//llenar el combo de municipio
  			var vEntidad = dijit.byId('idEntidadSelect').get('value');
  			if(tipoLlenado=='JSON'){
  	        	municipioStore.url = "${context}comregcontactv2.do?method=obtenerMunicipioJSON" + "&" + "codigoPostal="+ codigoPostal;
  	        	municipioStore.close();  	        	
  			} else {
            	municipioStore.url = "${context}comregcontactv2.do?method=obtenerMunicipio" + "&" + "idEntidad="+ vEntidad;
            	municipioStore.close();
  			}
  		}
  		
  		function fillCboColonia(codigoPostal, tipoLlenado){
  			//llenar el combo de colonia
  			var vEntidad = dijit.byId('idEntidadSelect').get('value');
  			var vMunicipio = dijit.byId('idMunicipioSelect').get('value');
  			if(tipoLlenado=='JSON'){
  	        	coloniaStore.url = "${context}comregcontactv2.do?method=obtenerColoniaJSON" + "&" + "codigoPostal="+ codigoPostal;
  	        	coloniaStore.close();
  			} else {
            	coloniaStore.url = "${context}comregcontactv2.do?method=obtenerColonia" + "&" + "idEntidad="+ vEntidad + "&" + "idMunicipio="+ vMunicipio;
            	coloniaStore.close();
  			}
  		}
  		
  		function loadCboEntidadCP(codigoPostal){
  			//buscar y poblar el combo entidad  
  			fillCboEntidad(codigoPostal, 'JSON');
        	entidadFederativaStore.fetch({
        		onComplete: function(items, request) {
              		if (items.length == 0) return;                    	
              		dijit.byId('idEntidadSelect').attr('value', items[0].value);        			
        		}        			
        	});  			
  		}
  		
  		function loadCboMunicipioCP(codigoPostal){
  			//buscar y poblar el combo municipio
  			fillCboMunicipio(codigoPostal, 'JSON');
        	municipioStore.fetch({
              	onComplete: function(items, request) {                  	
              		if (items.length == 0) return;                    	
              		dijit.byId('idMunicipioSelect').attr('value', items[0].value);
              	}
        	});  		
  		}  	
  		
  		function loadCboColoniaCP(codigoPostal){
  			//buscar y poblar el combo colonia
  			fillCboColonia(codigoPostal, 'JSON');
        	coloniaStore.fetch({
              	onComplete: function(items, request) {                  	
              		if (items.length == 0) return;         
              		dijit.byId('idColoniaSelect').attr('value', items[0].value);
              	}
        	});
  		}   		
  		
  		function  loadPreviousData(){
  			//Cargar datos existentes del domicilio CP, entidad, municipio, colonia 			
  			var vCodigoPostal = dojo.byId('codigoPostal').value;
    		//ENTIDAD
    		loadCboEntidadCP(vCodigoPostal);
        	//MUNICIPIO
        	loadCboMunicipioCP(vCodigoPostal);      	
        	//COLONIA
        	loadCboColoniaCP(vCodigoPostal);        	
  		}
  		
  		function validateCtrlCP(){
 			if(dijit.byId('codigoPostal').value == ''){
  			   alert("El código postal es necesario.");
  			   return;
 			}
  			if(dijit.byId('codigoPostal').value.length <5){
   			   alert("El código postal debe tener 5 dígitos.");
   			   return;
  			}			  			
  		}
  
        dojo.addOnLoad(function() {
            //PARA UPDATE
            loadPreviousData();
        	//TERMINA PARA UPDATE        	
        	
	        dojo.connect(document.getElementById('btnValidar'), "onclick", function() {	
	        	validateCtrlCP();
	            if(dijit.byId('Ctrl').value == 1){
					var vCodigoPostal = dijit.byId('codigoPostal').value;
					var vMsjNotF = 0;
					document.ComRegContactFormv2.method.value = 'obtenerEntidadJSON';
					toggleCbo('idEntidadSelect', false);
					toggleCbo('idMunicipioSelect', false);
					toggleCbo('idColoniaSelect', false);			
					fillCboEntidad(vCodigoPostal, 'JSON');				
	            	entidadFederativaStore.fetch({
	                  	onComplete: function(items, request) { 
	                  	    vMsjNotF = items.length;                 	
	                  		if (items.length == 0) {    	                  			
	        					alert("El código no se encontró o es inválido.");                  			
	                  			return;	
	                  		} else {
	                  		    dojo.byId('codigoPostalShow').value = vCodigoPostal;
	                  		}                   	
	                  		dijit.byId('idEntidadSelect').attr('value', items[0].value);
	                  	}
	            	});       	            	
	            	//MUNICIPIO
	            	loadCboMunicipioCP(vCodigoPostal);      	
	            	//COLONIA
	  				fillCboColonia(vCodigoPostal, 'JSON');
	            	coloniaStore.fetch({
	                  	onComplete: function(items, request) {                  	
	                  		if (items.length == 0) return;        
	                  		dijit.byId(idColoniaSelect).attr('value', items[0].value);
	                  		dojo.byId(idColoniaSelect).value;
	                  	}
	            	});                	                    	            	
					if(vMsjNotF>0){
						dojo.byId('codigoPostalShow').value = dijit.byId("codigoPostal").value;
						toggleCP(true);
					} else {
						toggleCP(false);
					}            	        	
				}			
	        });

	        dojo.connect(dijit.byId("idEntidadSelect"), "onChange", function() {
	            if(dijit.byId('Ctrl').value == 0){
	            	var vCodigoPostal = dijit.byId('codigoPostal').value;
					toggleCbo('idEntidadSelect', false);
					toggleCbo('idMunicipioSelect', false);
					fillCboMunicipio(vCodigoPostal, 'NO_JSON');				
				}
	        });

	        dojo.connect(dijit.byId("idMunicipioSelect"), "onChange", function() {
	            if(dijit.byId('Ctrl').value == 0){
	            	var vCodigoPostal = dijit.byId('codigoPostal').value;
					toggleCbo('idColoniaSelect', false);    
					fillCboColonia(vCodigoPostal, 'NO_JSON');
				}
	            
	            if(dijit.byId('idColoniaSelect')){
	            	dijit.byId('idColoniaSelect').set('value', '${ComRegContactFormv2.idColoniaSelected}');
	            	toggleCbo('idColoniaSelect', false);	
	            }               
	        });

	        dojo.connect(dijit.byId("idColoniaSelect"), "onChange", function() {
	            if(dijit.byId('Ctrl').value == 0){
	            	toggleCP(false);
					dojo.byId('idEntidad').value   = dijit.byId('idEntidadSelect').get('value');
					dojo.byId('idMunicipio').value = dijit.byId('idMunicipioSelect').get('value');
					dojo.byId('idColonia').value   = dijit.byId('idColoniaSelect').get('value');                
					document.getElementById('method').value="obtenerCodigoPostal";
					dojo.xhrPost(
					{
					  url: 'comregcontactv2.do',
					  form:'ComRegContactFormv2',
					  timeout:180000, 
					  load: function(data)
					  {
						var res = dojo.fromJson(data);
						dojo.byId('codigoPostal').value = res.codigoPostal;
						dojo.byId('codigoPostalShow').value = res.codigoPostal;
						toggleCP(true);
					  }
					});
				}		            
	        });

	        var checkBox = new dijit.form.CheckBox({
	            name: "checkCtrl",
	            value: 1,
	            checked: false,
	            onChange: function(b) {
	                if(b == false){
						dijit.byId('Ctrl').value = 1;
						clearCP();
						dojo.byId('codigoPostalShow').value = dijit.byId("codigoPostal").value;
						toggleCP(false);	
						clearCbo('idColoniaSelect');
						clearCbo('idMunicipioSelect');
						clearCbo('idEntidadSelect');
	            		toggleCbo('idEntidadSelect', true);
	            		toggleCbo('idMunicipioSelect', true);
	            		toggleCbo('idColoniaSelect', true);											
						var vCodigoPostal = dijit.byId('codigoPostal').value;
	                }                
	                if(b == true){
						dijit.byId('Ctrl').value = 0;		
						toggleCP(false);					
						clearCbo('idColoniaSelect');
						clearCbo('idMunicipioSelect');
						clearCbo('idEntidadSelect');
	            		toggleCbo('idEntidadSelect', false);
	            		toggleCbo('idMunicipioSelect', false);
	            		toggleCbo('idColoniaSelect', false);					
	            		toggleBtnValidar(false); 
	            		fillCboEntidad(vCodigoPostal, 'NO_JSON');
	                }
	            }
	        },
	        "checkCtrl");
	        fillUpAccessKey();      	        
	   });                               
  </script>               


