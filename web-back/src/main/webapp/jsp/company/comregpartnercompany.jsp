<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="mx.gob.stps.portal.persistencia.vo.CatalogoOpcionVO"%>
<%@ page import="mx.gob.stps.portal.web.company.form.ComRegPartnerCompanyForm"%>
<%@ page import="mx.gob.stps.portal.core.empresa.vo.TerceraEmpresaVO"%>
<%@ page import="mx.gob.stps.portal.core.infra.utils.Constantes"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script type="text/javascript" src="googiespell/AJS.js"></script>
<script type="text/javascript" src="googiespell/googiespell.js"></script>
<script type="text/javascript" src="googiespell/cookiesupport.js"></script>
<link   href="googiespell/googiespell.css" rel="stylesheet" type="text/css" />
<style type="text/css">
	.red3Font{
		color: #FF0000;
	}
	.Font80{
		font-size: 80%;
	}
</style>
<%
  String context = request.getContextPath() +"/";  
  String TELEFONO_CELULAR_ID = request.getSession().getAttribute("TELEFONO_CELULAR_ID").toString();
  String TELEFONO_CELULAR_DES = request.getSession().getAttribute("TELEFONO_CELULAR_DES").toString();
  String TELEFONO_FIJO_ID = request.getSession().getAttribute("TELEFONO_FIJO_ID").toString();
  String TELEFONO_FIJO_DES = request.getSession().getAttribute("TELEFONO_FIJO_DES").toString();
  //String TERMINOS_CONDICIONES = request.getSession().getAttribute("TERMINOS_CONDICIONES").toString();
  String CLAVE_TELEFONO_FIJO = request.getSession().getAttribute("CLAVE_TELEFONO_FIJO").toString();
  String CLAVE_TELEFONO_CELULAR = request.getSession().getAttribute("CLAVE_TELEFONO_CELULAR").toString();
  
  String CLAVE_TIPO_PERSONA_FISICA = request.getSession().getAttribute("CLAVE_TIPO_PERSONA_FISICA").toString();
  String CLAVE_TIPO_PERSONA_MORAL = request.getSession().getAttribute("CLAVE_TIPO_PERSONA_MORAL").toString();
  String ESTATUS_ACTIVO_ID = request.getSession().getAttribute("ESTATUS_ACTIVO_ID").toString();
  String ESTATUS_ACTIVO_DES = request.getSession().getAttribute("ESTATUS_ACTIVO_DES").toString(); 
  String ESTATUS_INACTIVO_ID = request.getSession().getAttribute("ESTATUS_INACTIVO_ID").toString(); 
  String ESTATUS_INACTIVO_DES = request.getSession().getAttribute("ESTATUS_INACTIVO_DES").toString();  
  List<TerceraEmpresaVO> lstTercerasEmpresas = (List<TerceraEmpresaVO>) request.getSession().getAttribute("LST_TERCERAS_EMPRESAS");
  String DES_TIPO_EMPRESA_PADRE = request.getSession().getAttribute("DES_TIPO_EMPRESA_PADRE").toString();
  
  ComRegPartnerCompanyForm formPartnerCompany = (ComRegPartnerCompanyForm)session.getAttribute("ComRegPartnerCompanyForm");
  String vProxy = context + "/SpellCheck.do?method=gogiespell&lang=";
%>

  <%
   String idTipo = String.valueOf(formPartnerCompany.getPadreIdTipoPersona()); 
   String strName = String.valueOf(formPartnerCompany.getPadreLeyendaNombre());
   String strDesTipoPersona = ""; 
	if(CLAVE_TIPO_PERSONA_FISICA.equalsIgnoreCase(idTipo)){
		strDesTipoPersona = Constantes.TIPO_PERSONA.PERSONA_FISICA.getTipoPersona();
	} else {
		strDesTipoPersona = Constantes.TIPO_PERSONA.PERSONA_MORAL.getTipoPersona();
	}                                     		 
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
function doSubmit(method){
	//document.ComRegPartnerCompanyForm.method.value=method;
	//document.ComRegPartnerCompanyForm.submit();	
    var vForm = dijit.byId('ComRegPartnerCompanyForm');
    document.getElementById('method').value=method;
    vForm.submit();
	
}

function setMensaje(mensaje){
	dojo.byId('mensaje').innerHTML = mensaje;
	dijit.byId('MensajeAlert').show();		
}

function doSubmitAjax(method){
	var vCampos = '';
	document.getElementById('method').value=method;
	var vForm = dijit.byId('ComRegPartnerCompanyForm');
	<%-- COMENTAR EN PRODUCCION 
	if(method!='cambiarEstatus' && method!='editar' ){	
	--%>		
	if(method!='cambiarEstatus'){		
		if (vForm.isValid() && checkOtherFields()){
			dojo.byId('btnGuardar').disabled=true;
			
			if (dijit.byId('idTipoEmpresaSelect').get('item') && dijit.byId('idTipoEmpresaSelect').get('item').label){
				dojo.byId('idTipoEmpresa').value = dijit.byId('idTipoEmpresaSelect').get('item').label[0];
			}
			if (dijit.byId('idActividadEconomicaSelect').get('item') && dijit.byId('idActividadEconomicaSelect').get('item').label){
				dojo.byId('idActividadEconomica').value = dijit.byId('idActividadEconomicaSelect').get('item').label[0];
			}				
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
			var chkVal = getRadioValue('idTipoPersona');
			if(chkVal==null){ 
				vCampos = vCampos + ' Tipo de persona.<br/>';
			} else {
				if(chkVal==<%=Constantes.TIPO_PERSONA.PERSONA_FISICA.getIdTipoPersona()%>){
					var nomb = dijit.byId('nombre').value;
					var ap1 = dijit.byId('apellido1').value;
					if(!nomb){
						vCampos = vCampos + ' Nombre.<br/>';	
						dijit.byId('nombre').focus();
					}
					if(!ap1){
						vCampos = vCampos + ' Primer apellido.<br/>';	
						dijit.byId('apellido1').focus();
					}			
				} else {
					var razon = dijit.byId('razonSocial').value;
					if(!razon){
						vCampos = vCampos + ' Razón social.<br/>';	
						dijit.byId('razonSocial').focus();
					}
				}				
			}
			
			var vDescripcion = document.getElementById('descripcion').value;
			if(!vDescripcion){
				vCampos = vCampos + ' Descripción de la empresa.<br/>';
				document.getElementById('descripcion').focus();
			} else {
				if(vDescripcion.length>2000){
					vCampos = vCampos + ' Descripción de la empresa es demasiado larga.<br/>';
					document.getElementById('descripcion').focus();
				}
			}
			var vContactoEmpresa = dijit.byId('contactoEmpresa').value;
			if(!vContactoEmpresa){
				vCampos = vCampos + ' Nombre de la persona de contacto con la empresa.<br/>';
				dijit.byId('contactoEmpresa').focus();
			}
			
			var vTipoEmpresa = getAnyElementValueById('idTipoEmpresaSelect');
			if(!vTipoEmpresa){
				vCampos = vCampos + ' Tipo de empresa.<br/>';
				dijit.byId('idTipoEmpresaSelect').focus();
			}		
	
			var vTipoActividad = getAnyElementValueById('idActividadEconomicaSelect');
			if(!vTipoActividad){
				vCampos = vCampos + ' Actividad económica principal.<br/>';
				dijit.byId('idActividadEconomicaSelect').focus();
			}		
			
			var vNumeroEmpleados = getAnyElementValueById('numeroEmpleados');
			if(!vNumeroEmpleados){
				vCampos = vCampos + ' Número de empleados.<br/>';
				dijit.byId('numeroEmpleados').focus();
			} else {
				if(parseInt(vNumeroEmpleados)<1){
					vCampos = vCampos + ' Número de empleados.<br/>';
					dijit.byId('numeroEmpleados').focus();	
				}
			}	
			
			var vCodigoPostal = getAnyElementValueById('codigoPostal');
			if(!vCodigoPostal){
				vCampos = vCampos + ' Código postal.<br/>';
				dijit.byId('codigoPostal').focus();
			} else {
				var vCodigoPostal_len = vCodigoPostal.length;
				if(vCodigoPostal_len<5){
					vCampos = vCampos + ' Código postal debe tener 5 dígitos.<br/>';	
					dijit.byId('codigoPostal').focus();
				}				
			}		
			
			var vCalle = getAnyElementValueById('calle');
			if(!vCalle){
				vCampos = vCampos + ' Calle.<br/>';
				dijit.byId('calle').focus();
			}		
	
			var vNumExterior = getAnyElementValueById('numeroExterior');
			if(!vNumExterior){
				vCampos = vCampos + ' Número exterior.<br/>';
				dijit.byId('numeroExterior').focus();
			}		
	
			var vEntreCalle = getAnyElementValueById('entreCalle');
			if(!vEntreCalle){
				vCampos = vCampos + ' Entre calle.<br/>';
				dijit.byId('entreCalle').focus();
			}		
			
			var vYCalle = getAnyElementValueById('yCalle');
			if(!vYCalle){
				vCampos = vCampos + ' Y calle.<br/>';
				dijit.byId('yCalle').focus();
			}		
			var vCorreoElectronico = document.getElementById('correoElectronico').value;
			if(!vCorreoElectronico){
				vCampos = vCampos + ' Correo electrónico.<br/>';
				document.getElementById('correoElectronico').focus();
			}		
			
			var vConfirmarCorreoElectronico = document.getElementById('confirmarCorreoElectronico').value;	
			<%-- COMENTAR EN PROD
			var vStyle = document.getElementById('confirmarCorreoElectronico').style.visibility;
			var hideDiv = vStyle == 'hidden' ? true : false;
			setDiv('confCorreo', hideDiv);			
			if(!hideDiv && !vConfirmarCorreoElectronico){
				vCampos = vCampos + ' Confirmar correo electrónico.<br/>';
				document.getElementById('confirmarCorreoElectronico').focus();
			}
			--%>			
			if(!vConfirmarCorreoElectronico){
				vCampos = vCampos + ' Confirmar correo electrónico.<br/>';
				document.getElementById('confirmarCorreoElectronico').focus();
			}		
	
			var vIdTipoTelefono = getRadioValue('idTipoTelefono');	
			if(null==vIdTipoTelefono){
				vCampos = vCampos + ' Tipo de teléfono principal.<br/>';
			}		
			var vAcceso = getAnyElementValueById('acceso');	
			if(!vAcceso){
				vCampos = vCampos + ' Acceso del teléfono principal.<br/>';
			}		
			var vClave = getAnyElementValueById('clave');	
			if(!vClave){
				vCampos = vCampos + ' Clave Lada del teléfono principal.<br/>';
				dijit.byId('clave').focus();
			}		
			var vTelefono = getAnyElementValueById('telefono');	
			if(!vTelefono){
				vCampos = vCampos + ' Teléfono principal.<br/>';
				dijit.byId('telefono').focus();
			}
			var vMessage =  document.getElementById('message'); 
			vMessage.innerHTML = '';
			vMessage.innerHTML = 'Los siguientes campos muestran datos inválidos - Se muestra(n) el (los) campo(s) con inconsistencias: <br/>' + vCampos;
		}
	} else {
		var vSelectedPartner = document.getElementById('selectPartnerCompany').selectedIndex;

		if(vSelectedPartner<1){
			alert('Debe seleccionar una empresa para modificar su estatus');
			//COMENTAR EN PRODUCCION 
			//alert('Debe seleccionar un contacto para modificar su estatus o sus datos');
			document.getElementById('selectPartnerCompany').focus();
		} else {
			vForm.submit();
		}				
	}
}
		
function cancelConfirmation() {
	var answer = confirm("Los datos no guardados se perderán ¿Continuar?");
	if (answer){
		doSubmit('cancelar');
	}
}		

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
		
//funcion para indicar que un campo es requerido
function setWidgetRequiredAttributes(baseDomNodeId, requiredValue){
    var widget = dojo.byId(baseDomNodeId);
    widget.required = requiredValue; 
}

//funcion para evitar que avance de un campo si el contenido no es valido
function verifyStop(id){
	var widget = dijit.byId(id);
	if(!widget.isValid()){
		widget.focus();
	}	
}

function validarFormatoCorreo(correo) {
	var regExp = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
	if (!regExp.test(correo.value)) {
		alert('Formato de correo electrónico inválido');
		correo.focus();
	}
}

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

//funcion para validar campos de manera alterna al DOJO
function checkOtherFields(){
	var vForm = true;
	var vMessage = '';
	var vDescripcion = document.getElementById('descripcion');
	var vCorreoElectronico = document.getElementById('correoElectronico').value;
	var vConfirmarCorreoElectronico = document.getElementById('confirmarCorreoElectronico').value;
	var vTipoTelefono = getRadioValue('idTipoTelefono'); 	
	var vClave = dijit.byId('clave');
	var vTelefono = dijit.byId('telefono');
	var chkVal = getRadioValue('idTipoPersona');
	
	if(vCorreoElectronico!=vConfirmarCorreoElectronico){
		vForm = false;
		vMessage = vMessage + '\nCorreo electrónico y Confirmar correo electrónico deben ser iguales.';
		document.getElementById('correoElectronico').focus();
	}
	if(chkVal==null){
		vForm = false;	
		vMessage = vMessage + '\nDebe seleccionar el Tipo de persona.';		
	} else {
		if(chkVal==<%=Constantes.TIPO_PERSONA.PERSONA_FISICA.getIdTipoPersona()%>){
			var nomb = dijit.byId('nombre').value;
			var ap1 = dijit.byId('apellido1').value;
			if(!nomb){
				vForm = false;	
				vMessage = vMessage + '\nDebe proporcionar el Nombre.';	
				dijit.byId('nombre').focus();
			}
			if(!ap1){
				vForm = false;	
				vMessage = vMessage + '\nDebe proporcionar el Primer Apellido.';	
				dijit.byId('apellido1').focus();
			}			
		} else {
			var razon = dijit.byId('razonSocial').value;
			if(!razon){
				vForm = false;	
				vMessage = vMessage + '\nDebe proporcionar la Razón social.';	
				dijit.byId('razonSocial').focus();
			}
		}	
	}
	if(!vDescripcion.value){
		vForm = false;
		vMessage = vMessage + '\nDebe proporcionar la Descripción de la empresa.';	
		vDescripcion.focus();
	}
	if(vClave.value.length==2 && vTelefono.value.length!=8){
		vForm = false;
		vMessage = vMessage + '\nEl teléfono debe tener 8 dígitos si la clave Lada tiene 2 dígitos.';
		vTelefono.focus();	
	}else if(vClave.value.length==3 && vTelefono.value.length!=7){
		vForm = false;
		vMessage = vMessage + '\nEl teléfono debe tener 7 dígitos si la clave Lada tiene 3 dígitos.';	
		vTelefono.focus();	
	}	
		
	if(vMessage.length>0){
		alert(vMessage);
	}
	return vForm;
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

//funcion para cambiar los campos requeridos dependiendo del tipo de persona
function changePersonTypeRequired(){
	var chkVal = getRadioValue('idTipoPersona');
	
	if (chkVal==<%=CLAVE_TIPO_PERSONA_FISICA%>){		
		dijit.byId('nombre').setDisabled(false);
		dijit.byId('apellido1').setDisabled(false);
		dijit.byId('apellido2').setDisabled(false);
		dijit.byId('razonSocial').setDisabled(true);
	} else if (chkVal==<%=CLAVE_TIPO_PERSONA_MORAL%>) {
		dijit.byId('nombre').setDisabled(true);
		dijit.byId('apellido1').setDisabled(true);
		dijit.byId('apellido2').setDisabled(true);
		dijit.byId('razonSocial').setDisabled(false);
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

	//TELEFONOS	
	//Desplegar ventana para captura de telefonos adicionales
	function openPhoneWindow(){	
	 	var newWin = window.open("<%=context%>phone.do?method=init", "Teléfonos","height=500,width=900,resizable=yes,scrollbars=yes"); 	
	} 	
	
	//Dependiendo de la seleccion de tipo de telefono, coloca la clave de acceso correspondiente
	function fillUpAccessKey(){
		var tipoTelefonoId = getRadioValue('idTipoTelefono');
		var accesoDes;
        if(tipoTelefonoId==<%=TELEFONO_CELULAR_ID%>){ 
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
	//TERMINA TELEFONOS		

</script>

	
	<form name="ComRegPartnerCompanyForm" id="ComRegPartnerCompanyForm" method="post" action="comregpartnercompany.do" dojoType="dijit.form.Form" >
	 <!-- dojoattachevent="onreset:_onReset,onsubmit:_onSubmit"  -->
		<input type="hidden" name="method" id="method" value="init" />
		<input type="hidden" name="Ctrl" id="Ctrl" dojoType="dijit.form.TextBox" value="1" />
		<input type="hidden" name="cpnuevo" id="cpnuevo" dojoType="dijit.form.TextBox" value="0"/>
		<%-- EN PRODUCCION 
		<input type="hidden" name="idContacto" id="idContacto" value="${ComRegContactForm.idContacto}"/>
		<input type="hidden" name="hiddenPreCorreoElectronico" id="hiddenPreCorreoElectronico" value="${ComRegContactForm.preCorreoElectronico}"/>
		--%>				
		<input type="hidden" name="hiddenDesTipoEmpresa" id="hiddenDesTipoEmpresa" value="${ComRegPartnerCompanyForm.hiddenDesTipoEmpresa}"/>
		<input type="hidden" name="hiddenDesActividadEconomica" id="hiddenDesActividadEconomica" value="${ComRegPartnerCompanyForm.hiddenDesActividadEconomica}"/>
		<input type="hidden" name="acceso" id="acceso" dojoType="dijit.form.TextBox" value="${ComRegPartnerCompanyForm.acceso}" />
		<input type="hidden" name="codigoPostalShow" id="codigoPostalShow" dojoType="dijit.form.TextBox" value="${ComRegPartnerCompanyForm.codigoPostal}" />
	
	<div class="formulario" >
	<h3>Terceras Empresas</h3>  
	<DIV class="entero" >
	<a href="#" onclick="confirmParent();"  class="links" >Regresar a Empresa padre</a><br/>
	</DIV>
	
                <DIV class="entero">
				  <div id="message"></div> 
					<html:messages id="errors" >
						<span class="Font80 red3Font"><bean:write name="errors"/></span><br/>
					</html:messages>
					
					<html:messages id="messages" message="true" >
						<span class="Font80 red3Font"><bean:write name="messages"/></span><br/>
					</html:messages>    				   
                  
                  </DIV> 	
			
        <DIV class="entero">    
        <span class="un_cuarto"><strong>Id Portal del Empleo: </strong><br/><c:out value="${ComRegPartnerCompanyForm.padreIdPortalEmpleo}"/><br>
        </span>			
        <span class="un_cuarto">
        <strong>Tipo de persona: </strong><br/> <%=DES_TIPO_EMPRESA_PADRE%>
        </span>			                
        </DIV>  
        
        <%
        if(CLAVE_TIPO_PERSONA_FISICA.equalsIgnoreCase(idTipo)){ %>        
    	<DIV class="entero">
    	<span class="un_cuarto">
        <strong>Nombre(s): </strong><br/><c:out value="${ComRegPartnerCompanyForm.padreNombre}"/>
    	</span>
    	<span class="un_cuarto">
        <strong>Primer apellido: </strong><br/><c:out value="${ComRegPartnerCompanyForm.padreApellido1}"/>
    	</span>
    	<span class="un_cuarto">
        <strong>Segundo apellido: </strong><br/><c:out value="${ComRegPartnerCompanyForm.padreApellido2}"/>
    	</span>
    	</DIV>
    	<% } else { %>		           			 		  			           			   
    	<DIV class="entero">
    	<span class="un_cuarto">
        <strong>Razón social: </strong><br/><c:out value="${ComRegPartnerCompanyForm.padreRazonSocial}"/>
		</span>        
        </DIV>
		<% } %>
	
	<h3>Terceras empresas registradas</h3>
	<DIV>
    	<DIV class="entero">
	    	<span class="un_medio">
	    	<strong><label for="selectPartnerCompany">Terceras empresas registradas - Estatus</label></strong><br>
			<select id="selectPartnerCompany" name="selectPartnerCompany" size="1" style="width:100%">
			<option value="-1">Seleccionar</option>
			<%
			Iterator itTerceras = lstTercerasEmpresas.iterator();
			while(itTerceras.hasNext()){
				TerceraEmpresaVO terceraVo = (TerceraEmpresaVO) itTerceras.next();
				long idTercera = terceraVo.getIdTerceraEmpresa();
				String idTipoTercera = String.valueOf(terceraVo.getIdTipoPersona());	
				String strStatus = String.valueOf(terceraVo.getEstatus());
				String strNameTercera = ""; 			
				String strStatusLegend = "";		
				
				if(CLAVE_TIPO_PERSONA_FISICA.equalsIgnoreCase(idTipoTercera)){
					strNameTercera = terceraVo.getNombre() + " " + terceraVo.getApellido1();
					if(null!=terceraVo.getApellido2() && !terceraVo.getApellido2().equalsIgnoreCase("")){
					 strNameTercera = strNameTercera + " " + terceraVo.getApellido2();
					}
				} else if(CLAVE_TIPO_PERSONA_MORAL.equalsIgnoreCase(idTipoTercera)) {
					strNameTercera = terceraVo.getRazonSocial();
				}
				if(ESTATUS_ACTIVO_ID.equalsIgnoreCase(strStatus)){
					strStatusLegend = ESTATUS_ACTIVO_DES;
				} else {
					strStatusLegend = ESTATUS_INACTIVO_DES;
				}
				
				%>
				<option value="<%=idTercera%>"><%=strNameTercera%> - <%=strStatusLegend%></option>
				<%			
			}
			%>			
			</select> 		    	
			</span> 
		</DIV>	
		<DIV class="entero">	       
	    	<span class="un_medio">
			<input type="button" id="btnCambiarEstatus" value="Cambiar estatus" class="boton" onclick="doSubmitAjax('cambiarEstatus');" />	    	
			</span>    
			<%-- //COMENTAR EN PRODUCCION  		
	    	<span class="un_medio">
			<input type="button" id="btnEditar" value="Editar" class="boton" onclick="doSubmitAjax('editar');" />	    	
			</span>
			--%>     			    			
        </DIV>	
	</DIV>	
	<h3>Registro de tercera empresa</h3>	
	<DIV>	
        <DIV class="entero">&nbsp;<br>
        <span class="un_tercio"><strong><label for="rfc">RFC</label></strong><br/>
        <input id="rfc" name="rfc" maxlength="13" size="13"
        	dojoType="dijit.form.ValidationTextBox" required="false" regExp="^[A-Za-z\s\áéíóúÁÉÍÓÚñÑ0-9/']{1,150}$" 
        	value="${ComRegPartnerCompanyForm.rfc}"
        	invalidMessage="Dato inválido" trim="true" />        
        
        </span>			
        <span class="un_tercio">
        <strong><label for="idTipoPersona">Tipo de persona</label>*</strong><br/>
        <% long lngIdTipoPersona = formPartnerCompany.getIdTipoPersona();        
           long idFisica = Long.parseLong(String.valueOf(Constantes.TIPO_PERSONA.PERSONA_FISICA.getIdTipoPersona()));
           long idMoral = Long.parseLong(String.valueOf(Constantes.TIPO_PERSONA.PERSONA_MORAL.getIdTipoPersona()));
           
           if(lngIdTipoPersona==idFisica){
      	   %>
		        Física&nbsp;<input type="radio" id="idTipoPersona" name="idTipoPersona" value="1" checked onClick="changePersonTypeRequired()" >&nbsp;&nbsp;&nbsp;
		        Moral&nbsp;<input type="radio" id="idTipoPersona" name="idTipoPersona" value="2" onClick="changePersonTypeRequired()" >			           			           		
           <% } else if(lngIdTipoPersona==idMoral) {%>
		        Física&nbsp;<input type="radio" id="idTipoPersona" name="idTipoPersona" value="1" onClick="changePersonTypeRequired()" >&nbsp;&nbsp;&nbsp;
		        Moral&nbsp;<input type="radio" id="idTipoPersona" name="idTipoPersona" value="2" checked onClick="changePersonTypeRequired()" >			           			           		
           <% } else {%>
                Física&nbsp;<input type="radio" id="idTipoPersona" name="idTipoPersona" value="1" onClick="changePersonTypeRequired()" >&nbsp;&nbsp;&nbsp;
                Moral&nbsp;<input type="radio" id="idTipoPersona" name="idTipoPersona" value="2" onClick="changePersonTypeRequired()" >			           
		    <%} %>           		
        </span>			                
        </DIV>  
		
    	<DIV class="entero">
    	<span class="un_tercio">
        <strong><label for="nombre">Nombre(s)</label>*</strong><br/>
        <input id="nombre" name="nombre" maxlength="50" size="50" 	        	
        	dojoType="dijit.form.ValidationTextBox" required="false" regExp="^[A-Za-z\s\-.áéíóúÁÉÍÓÚñÑ/']{1,50}$" uppercase="true"
        	value="${ComRegPartnerCompanyForm.nombre}"
        	invalidMessage="Dato inválido" missingMessage="Dato requerido" trim="true"/>
           			    
    	</span>
    	<span class="un_tercio">
        <strong><label for="apellido1">Primer apellido</label>*</strong><br/>
        <input id="apellido1" name="apellido1" maxlength="50" size="50" 	        	
        	dojoType="dijit.form.ValidationTextBox" required="false" regExp="^[A-Za-z\s\-.áéíóúÁÉÍÓÚñÑ/']{1,50}$"  uppercase="true"
        	value="${ComRegPartnerCompanyForm.apellido1}"
        	invalidMessage="Dato inválido" missingMessage="Dato requerido" trim="true"/>
            			    
    	</span>
    	<span class="un_tercio">
        <strong><label for="apellido2">Segundo apellido</label></strong><br/>
        <input id="apellido2" name="apellido2" maxlength="50" size="50" 
        	dojoType="dijit.form.ValidationTextBox" required="false" regExp="^[A-Za-z\s\-.áéíóúÁÉÍÓÚñÑ/']{1,50}$"  uppercase="true"
        	value="${ComRegPartnerCompanyForm.apellido2}"
        	invalidMessage="Dato inválido" trim="true"/>
        			               			    
    	</span>
    	</DIV>
		
		<!-- Persona Moral onblur="verifyStop('razonSocial');"  --> 
    	<DIV class="entero">
    	<span class="un_tercio">
        <strong><label for="razonSocial">Razón social</label>*</strong><br/>
        <input id="razonSocial" name="razonSocial" maxlength="150" size="50" 			
        	dojoType="dijit.form.ValidationTextBox" required="false" regExp="^[A-Za-z\s\d\]\-.&áéíóúÁÉÍÓÚñÑ/']{1,150}$"  uppercase="true"
        	value="${ComRegPartnerCompanyForm.razonSocial}"
        	invalidMessage="Dato inválido" missingMessage="Dato requerido" trim="true"/>
               			    
		</span>        
        </DIV>
        
		<!-- Independientes de tipo de persona -->       			            
        <div class="entero">
	        <span class="un_cuarto">
		        <strong><label for="nombreComercial">Nombre Comercial</label></strong><br/>
		        <input id="nombreComercial" name="nombreComercial" maxlength="150" size="50" 			
		        	dojoType="dijit.form.ValidationTextBox" required="false" onpaste="return false;" regExp="^[A-Za-z\s\d\-.&áéíóúÁÉÍÓÚñÑ/']{1,150}$"  uppercase="true"			        	
		        	value="${ComRegCompanyForm.nombreComercial}"
		        	invalidMessage="Dato inválido" missingMessage="Dato requerido" trim="true"/>
		               			    
			</span>			        
        </div>
	
		<DIV class="entero">
        <strong><label for="descripcion">Descripción de la empresa</label>*</strong></span> <br/>        
	    <textarea name="descripcion" id="descripcion" maxlength="2000" 
      	    onkeypress="return caracteresValidos(event);"
      	    regExp="^[A-Za-z\s\-.áéíóúÁÉÍÓÚñÑ0-9.,;:/']{1,2000}$" 
      	    style="width:550px;min-height:120px;_height:200px;" 
      	    required="true">${ComRegPartnerCompanyForm.descripcion}<%//=formCompany.getDescripcion()%></textarea>
      	    <script type="text/javascript">
       		 var vSpellCon = new GoogieSpell("googiespell/", '<%=vProxy%>');
       		 vSpellCon.setLanguages({'es': 'Español'});
       		 vSpellCon.hideLangWindow();
       		 vSpellCon.decorateTextarea("descripcion");
	    </script>		     	       	        	      	
		</DIV>	
		
    	<DIV class="entero">
    	<span class="un_medio">
        <strong><label for="contactoEmpresa">Nombre de la persona de contacto con la empresa</label>*</strong><br/>
        	<input id="contactoEmpresa" name="contactoEmpresa"  maxlength="150" size="50"         	
        	dojoType="dijit.form.ValidationTextBox" required="false" regExp="^[a-zA-ZáéíóúÁÉÍÓÚñÑ0-9\s]{1,150}$" 
        	value="${ComRegPartnerCompanyForm.contactoEmpresa}"
        	invalidMessage="Dato inválido" missingMessage="Dato requerido" trim="true" />
        	
        </span>
		<span class="un_medio">
       	<strong><label for="idTipoEmpresaSelect">Tipo de empresa</label>*</strong><br/>   
		<input type="hidden" name="idTipoEmpresa" id="idTipoEmpresa" value="${ComRegPartnerCompanyForm.idTipoEmpresa}" />
		<div dojoType="dojo.data.ItemFileReadStore" jsId="tiposEmpresaStore" url="${context}comregpartnercompany.do?method=tiposEmpresa"></div>
		<select dojotype="dijit.form.ComboBox" store="tiposEmpresaStore" id="idTipoEmpresaSelect" required="false"></select>			        
    	</span>		
    	</DIV>
    	
    	<DIV class="entero">        	
    	<span class="un_medio">   			    
       	<strong><label for="idActividadEconomicaSelect">Actividad económica principal</label>*</strong><br/>   
		<input type="hidden" name="idActividadEconomica" id="idActividadEconomica" value="${ComRegPartnerCompanyForm.idActividadEconomica}" />
		<div dojoType="dojo.data.ItemFileReadStore" jsId="tiposActividadEconomicaStore" 
			url="${context}comregpartnercompany.do?method=tiposActividadEconomica"></div>
		<select dojotype="dijit.form.ComboBox" store="tiposActividadEconomicaStore" id="idActividadEconomicaSelect" required="false" style="width:100%"></select>
		</span>	       	
		</DIV>
		<DIV class="entero"> 		    		        
		<span class="un_medio">
        <strong><label for="numeroEmpleados">Número de empleados</label>*</strong><br/> 
        <input id="numeroEmpleados" name="numeroEmpleados" maxlength="5" size="5"         	
        	dojoType="dijit.form.ValidationTextBox" required="false" regExp="^[0-9]{1,5}$"
        	value="${ComRegPartnerCompanyForm.numeroEmpleados}"
        	invalidMessage="Dato inválido" missingMessage="Dato requerido" trim="true" />
                       			           			    					
		</span>	
		</DIV>

    	<!-- DOMICILIO -->
		<h3>Domicilio</h3>
		<DIV class="entero">
       	<span class="un_tercio">
      	<strong><label for="codigoPostal">Código postal</label>*</strong><br/>
		<input id="codigoPostal" name="codigoPostal" maxlength="5" size="5" value="${ComRegPartnerCompanyForm.codigoPostal}" 
			dojoType="dijit.form.ValidationTextBox" required="false" regExp="^[0-9]{1,5}"  invalidMessage="Dato inválido" trim="true" />        
        	       			   		
    	</span>	
    	<span class="un_tercio">
		<input type="checkbox" id="checkCtrl">
		<label for="checkCtrl">No conozco mi código postal</label>               			   
    	</span>	
    	<span class="un_tercio">
    	<!--  <div id="btnValidar" dojoType="dijit.form.Button">Validar CP</div> --> 
    	<input type="button" name="btnValidar" id="btnValidar" class="boton" value="Validar CP">	
       <!--  <button id="btnValidar" name="btnValidar"  dojoType="dijit.form.Button" class="boton">ValidarCP</button>  -->   	 	
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
        <input id="calle" name="calle" maxlength="150" size="60" value="${ComRegPartnerCompanyForm.calle}" 
        	dojoType="dijit.form.ValidationTextBox" required="false" regExp="^[a-zA-ZáéíóúÁÉÍÓÚñÑ0-9\s]{1,150}$" uppercase="true"
        	invalidMessage="Dato inválido" missingMessage="Dato requerido" trim="true"/>			             			   
        
        </span> 
        
        <span class="un_tercio"><strong><label for="numeroExterior">Número exterior</label>*</strong><br/> 
        <input id="numeroExterior" name="numeroExterior" maxlength="50" size="4" value="${ComRegPartnerCompanyForm.numeroExterior}"
        	dojoType="dijit.form.ValidationTextBox" required="false" regExp="^[a-zA-ZáéíóúÁÉÍÓÚñÑ0-9\s]{1,50}" 
        	invalidMessage="Dato inválido" trim="true" />        
        				            			   
        </span> 
        
        <span class="un_tercio"><strong><label for="numeroInterior">Número interior</label></strong><br/>
        <input id="numeroInterior" name="numeroInterior" maxlength="50" size="4" value="${ComRegPartnerCompanyForm.numeroInterior}"
        	dojoType="dijit.form.ValidationTextBox" required="false" regExp="^[a-zA-ZáéíóúÁÉÍÓÚñÑ0-9\s]{1,50}" 
        	invalidMessage="Dato inválido" trim="true" />        
        				             			   
        </span> 			        
    	</DIV>
    			   
        <DIV class="entero">  
        <span class="un_tercio"><strong><label for="entreCalle">Entre calles</label>*</strong><br/> 
        <input id="entreCalle" name="entreCalle" maxlength="150" size="60" value="${ComRegPartnerCompanyForm.entreCalle}" 
        	dojoType="dijit.form.ValidationTextBox" required="false" regExp="^[a-zA-ZáéíóúÁÉÍÓÚñÑ0-9\s]{1,150}$" uppercase="true"
        	invalidMessage="Dato inválido" missingMessage="Dato requerido" trim="true"/>
					            			   
        </span> 			      
        <span class="un_tercio"><strong><label for="yCalle">Y</label>*</strong><br/>
        <input id="yCalle" name="yCalle" maxlength="150" size="60" value="${ComRegPartnerCompanyForm.yCalle}" 
        	dojoType="dijit.form.ValidationTextBox" required="false" regExp="^[a-zA-ZáéíóúÁÉÍÓÚñÑ0-9\s]{1,150}$" uppercase="true"
        	invalidMessage="Dato inválido" trim="true"/>
             			   
        </span> 			        
    	</DIV>
    	<!-- TERMINA DOMICILIO --> 

    	<%-- DESCOMENTAR EN PRODUCCION --%>
    	<%-- --%>
		<h3>Datos de identificación de la cuenta</h3>
        <DIV class="entero">  
        <span class="un_medio">  
        <strong><label for="correoElectronico">Correo electrónico</label>*</strong><br/>
       	<input type="text" name="correoElectronico" id="correoElectronico" size="50" maxlength="65" trim="true"
       	value="${ComRegPartnerCompanyForm.correoElectronico}" onchange="validarFormatoCorreo(this);" oncopy="return false;" 
       	oncut="return false" onpaste="return false;"  />			        		             			   
        </span> 			
        </DIV>
        <DIV class="entero">
        <span class="un_medio">   
        <strong><label for="confirmarCorreoElectronico">Confirmar correo electrónico</label>*</strong><br/>
        <input type="text" name="confirmarCorreoElectronico" id="confirmarCorreoElectronico" size="50" maxlength="65" trim="true"
        value="${ComRegPartnerCompanyForm.confirmarCorreoElectronico}" onchange="validarFormatoCorreo(this);" oncopy="return false;" oncut="return false" 
        onpaste="return false;" ondragover="return false;" />			                
        </span> 			        			          			                			          
    	</DIV>
    	<%-- TERMINA DESCOMENTAR EN PRODUCCION --%>
    	<%-- COMENTAR EN PRODUCCION --%>
    	<%--  
    	<%
	    long idContacto = formContact.getIdContacto();
    	if(idContacto>0){
    		%>
           <h3>Cuenta de correo electrónico del contacto</h3>
           <p class="entero">
		   <strong>Correo electrónico*</strong><br/>
	       <input type="text" name="correoElectronico" id="correoElectronico" size="50" maxlength="65" 
	       value="${ComRegPartnerCompanyForm.correoElectronico}" onchange="validarFormatoCorreo(this);" oncopy="return false;" 
	       oncut="return false" onpaste="return false;" readonly="true"  />			        
           </p>
           <div id="confCorreo" class="entero" style="visibility:hidden;">
		   <strong>Confirmar correo electrónico*</strong><br/>
	       <input type="text" name="confirmarCorreoElectronico" id="confirmarCorreoElectronico" size="50" maxlength="65" 
	       value="${ComRegPartnerCompanyForm.correoElectronico}" onchange="validarFormatoCorreo(this);" oncopy="return false;" oncut="return false" 
	       onpaste="return false;" ondragover="return false;" />			        				        
		   </div>	
		   <p class="entero">
	       <a href="javascript:showConf();" class="un_cuarto links">Actualizar correo electr&oacute;nico</a>
	       </p>    		
    		<%
    	} else {
    		%>
			<h3>Cuenta de correo electrónico del contacto</h3>
	        <DIV class="entero">  
	        <span class="un_medio">  
	        <strong>Correo electrónico*</strong><br/>
	       	<input type="text" name="correoElectronico" id="correoElectronico" size="50" maxlength="65" 
	       	value="${ComRegPartnerCompanyForm.correoElectronico}" onchange="validarFormatoCorreo(this);" oncopy="return false;" 
	       	oncut="return false" onpaste="return false;"  />			        		             			           
	        </span> 			
	        </DIV>
	        <DIV class="entero">
	        <span class="un_medio">   
	        <strong>Confirmar correo electrónico*</strong><br/>
	        <input type="text" name="confirmarCorreoElectronico" id="confirmarCorreoElectronico" size="50" maxlength="65" 
	        value="" onchange="validarFormatoCorreo(this);" oncopy="return false;" oncut="return false" 
	        onpaste="return false;" ondragover="return false;" />			                
	        </span> 			        			          			                			          
	    	</DIV>	    		
    		<%    		
    	}
    	%>
    	--%>
		<%-- TERMINA COMENTAR EN PRODUCCION --%>
		  			   
		<h3>Teléfono*</h3>       		
        <DIV class="entero">  
        <span class="un_tercio"><strong><label for="idTipoTelefono">Tipo de teléfono</label>*</strong><br/>
		<%
		long selectedPhoneType = (long)formPartnerCompany.getIdTipoTelefono();
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
		 	value="${ComRegPartnerCompanyForm.acceso}"
		 	invalidMessage="Dato inválido" missingMessage="Dato requerido" trim="true" disabled="disabled" />
		       			        
        </span> 			 
        <span class="un_tercio"><strong><label for="clave">Clave Lada</label>*</strong><br/> 
		<input id="clave" name="clave" maxlength="3" size="3"
		 	dojoType="dijit.form.ValidationTextBox" required="false" regExp="^[0-9]{2,3}$"
		 	value="${ComRegPartnerCompanyForm.clave}"
		 	invalidMessage="Dato inválido" missingMessage="Dato requerido" trim="true" />	        			        
        </span> 		
        </DIV>
        <DIV class="entero">  	 
        <span class="un_tercio"><strong><label for="telefono">Teléfono</label>*</strong><br/>
		<input id="telefono" name="telefono" maxlength="8" size="8" onBlur="changePhoneSizeRequired()"
		 	dojoType="dijit.form.ValidationTextBox" required="false" regExp="^[0-9]{7,8}$"
		 	value="${ComRegPartnerCompanyForm.telefono}"			 	
		 	invalidMessage="Dato inválido" missingMessage="Dato requerido" trim="true" />
		        			          
        </span> 			 
        <span class="un_tercio"><strong><label for="extension">Extensión</label></strong><br/>
		<input id="extension" name="extension" maxlength="8" size="8"
		 	dojoType="dijit.form.ValidationTextBox" required="false" regExp="^[0-9]{1,6}$"
		 	value="${ComRegPartnerCompanyForm.extension}"
		 	invalidMessage="Dato inválido" missingMessage="Dato requerido" trim="true" />
		        			           
        <a href="#" class="links" onclick="openPhoneWindow()">Agregar teléfono</a>
        </span>
    	</DIV>  
    	
		<DIV class="entero" id="divRegis" style="text-align: center;">         			    				   
        <span class="un_tercio">                                        
		<input type="button" id="btnGuardar" value="Guardar" class="boton" onclick="doSubmitAjax('salvar');" />
		</span>
		<span class="un_tercio">
		<input type="button" id="btnCancel" value="Cancelar" class="boton" onclick="cancelConfirmation();" />
		</span>		
        </DIV>    	    
    </DIV>

					<div dojoType="dijit.Dialog" id="MensajeAlert" title="Mensaje" style="display:none" draggable ="false">
						  <table width="300px" >
							 <tr align="center">
								 <td><div id ="mensaje"></div>&nbsp;</td>				 
							 </tr>
						 </table>	
					</div>                  
                  
  
</div>
</form>


  <script type="text/javascript">
        dojo.addOnLoad(function() {
        	        	
           	var vDesActividadEconomica =  document.getElementById('hiddenDesActividadEconomica').value;
           	var vDesTipoEmpresa =  document.getElementById('hiddenDesTipoEmpresa').value;
           	
           	if(dijit.byId('idActividadEconomicaSelect')){
           		dijit.byId('idActividadEconomicaSelect').setDisplayedValue(vDesActividadEconomica);
           		dijit.byId('idTipoEmpresaSelect').setDisplayedValue(vDesTipoEmpresa);	
            }            	
        	
            //PARA UPDATE
    		var vCodigoPostal = dojo.byId('codigoPostal').value;
		
    		dijit.byId('idEntidadSelect').disabled=false;
    		dijit.byId('idMunicipioSelect').disabled=false;
    		dijit.byId('idColoniaSelect').disabled=false;

        	entidadFederativaStore.url = "${context}comregpartnercompany.do?method=obtenerEntidadJSON" + "&" + "codigoPostal="+ vCodigoPostal;
        	entidadFederativaStore.close();
    		
        	entidadFederativaStore.fetch({
              	onComplete: function(items, request) {                  	
              		if (items.length == 0) return;                    	
              		dijit.byId('idEntidadSelect').attr('value', items[0].value);
              	}
        	});

        	municipioStore.url = "${context}comregpartnercompany.do?method=obtenerMunicipioJSON" + "&" + "codigoPostal="+ vCodigoPostal;
        	municipioStore.close();

        	municipioStore.fetch({
              	onComplete: function(items, request) {                  	
              		if (items.length == 0) return;                    	
              		dijit.byId('idMunicipioSelect').attr('value', items[0].value);
              	}
        	});

        	coloniaStore.url = "${context}comregpartnercompany.do?method=obtenerColoniaJSON" + "&" + "codigoPostal="+ vCodigoPostal;
        	coloniaStore.close();

        	coloniaStore.fetch({
              	onComplete: function(items, request) {                  	
              		if (items.length == 0) return;                    	
              		dijit.byId('idColoniaSelect').attr('value', items[0].value);
              	}
        	});        	
        	//TERMINA PARA UPDATE
        	
        //dojo.connect(document.ComRegPartnerCompanyForm.btnValidar, "onClick", function() {
        dojo.connect(document.getElementById('btnValidar'), "onclick", function() {
			var vCodigoPostal = dojo.byId('codigoPostal').value;	
				
 			if(dijit.byId('codigoPostal').value == ''){
 			   alert('El código postal es necesario.');
 			   return;
			}
 			if(dijit.byId('codigoPostal').value.length <5){
  			   alert('El código postal debe tener 5 dígitos.');
  			   return;
 			}			

            if(dijit.byId('Ctrl').value == 1){

				var vCodigoPostal = dijit.byId('codigoPostal').value;
				var vMsjNotF = 0;
				document.ComRegPartnerCompanyForm.method.value = 'obtenerEntidadJSON';
	
				dijit.byId('idEntidadSelect').disabled=false;
				dijit.byId('idMunicipioSelect').disabled=false;
				dijit.byId('idColoniaSelect').disabled=false;

            	entidadFederativaStore.url = "${context}comregpartnercompany.do?method=obtenerEntidadJSON" + "&" + "codigoPostal="+ vCodigoPostal;
            	entidadFederativaStore.close();
								
            	entidadFederativaStore.fetch({
                  	onComplete: function(items, request) {      
                  	    vMsjNotF = items.length;        	
                  		if (items.length == 0) {                  			
        					alert('El código no se encontró o es inválido.');                  			
                  			return;	
                  		} else {
                  		    dojo.byId('codigoPostalShow').value = vCodigoPostal;
                  		}                     	
                  		dijit.byId('idEntidadSelect').attr('value', items[0].value);
                  	}
            	});

            	municipioStore.url = "${context}comregpartnercompany.do?method=obtenerMunicipioJSON" + "&" + "codigoPostal="+ vCodigoPostal;
            	municipioStore.close();

            	municipioStore.fetch({
                  	onComplete: function(items, request) {                  	
                  		if (items.length == 0) return;                    	
                  		dijit.byId('idMunicipioSelect').attr('value', items[0].value);
                  	}
            	});

            	coloniaStore.url = "${context}comregpartnercompany.do?method=obtenerColoniaJSON" + "&" + "codigoPostal="+ vCodigoPostal;
            	coloniaStore.close();

            	coloniaStore.fetch({
                  	onComplete: function(items, request) {                  	
                  		if (items.length == 0) return;                    	
                  		dijit.byId('idColoniaSelect').attr('value', items[0].value);
                  	}
            	});     
            	
				if(vMsjNotF>0){
					dojo.byId('codigoPostalShow').value = dijit.byId('codigoPostal').value;
					dijit.byId('codigoPostal').setAttribute('disabled', true);						
				} else {
					dijit.byId('codigoPostal').setAttribute('disabled', false);
				}            	       	

			}
			
        });

        dojo.connect(dijit.byId('idEntidadSelect'), "onChange", function() {

            if(dijit.byId('Ctrl').value == 0){

				var vEntidad = dijit.byId('idEntidadSelect').get('value');
				var vMunicipio = dijit.byId('idMunicipioSelect').get('value');
				var vColonia = dijit.byId('idColoniaSelect').get('value');				
					
				if(vMunicipio>0 || vColonia>0){
            		var wColonia = dijit.byId('idColoniaSelect');
            		wColonia.reset();					
            		var wMunicipio = dijit.byId('idMunicipioSelect');
            		wMunicipio.reset();					
				} 					
					
            	dijit.byId('idMunicipioSelect').disabled=false;
            		
            	municipioStore.url = "${context}comregpartnercompany.do?method=obtenerMunicipio" + "&" + "idEntidad="+ vEntidad;
            	municipioStore.close();
			}
        });


        dojo.connect(dijit.byId("idMunicipioSelect"), "onChange", function() {

            if(dijit.byId('Ctrl').value == 0){

				var vEntidad   = dijit.byId('idEntidadSelect').get('value');
				var vMunicipio = dijit.byId('idMunicipioSelect').get('value');
				var vColonia = dijit.byId('idColoniaSelect').get('value');
					
            	//dijit.byId('idColoniaSelect').disabled=false;
				if(vColonia>0){
            		var wColonia = dijit.byId('idColoniaSelect');
            		wColonia.reset();					
				}               	
            		
            	coloniaStore.url = "${context}comregpartnercompany.do?method=obtenerColonia" + "&" + "idEntidad="+ vEntidad + "&" + "idMunicipio="+ vMunicipio;
            	coloniaStore.close();
			}
        });


        dojo.connect(dijit.byId('idColoniaSelect'), "onChange", function() {

            if(dijit.byId('Ctrl').value == 0){

            	dijit.byId('codigoPostal').disabled=false;

				dojo.byId('idEntidad').value   = dijit.byId('idEntidadSelect').get('value');
				dojo.byId('idMunicipio').value = dijit.byId('idMunicipioSelect').get('value');
				dojo.byId('idColonia').value   = dijit.byId('idColoniaSelect').get('value');
                
				document.getElementById('method').value="obtenerCodigoPostal";

	
				dojo.xhrPost(
				{
				  url: 'comregpartnercompany.do',
				  form:'ComRegPartnerCompanyForm',
				  timeout:180000, 
				  load: function(data)
				  {
					var res = dojo.fromJson(data);
					dojo.byId('codigoPostal').value = res.codigoPostal;
					dojo.byId('codigoPostalShow').value = res.codigoPostal;
					dijit.byId('codigoPostal').setAttribute('disabled', true);					
				  }
				});
			}			
        });


        var checkBox = new dijit.form.CheckBox({
            name: "checkCtrl",
            value: 1,
            checked: false,
            onChange: function(b) {
                
                if(b == true){

					dijit.byId('Ctrl').value = 0;
					var wCP = dijit.byId('codigoPostal');
					wCP.attr('value','');					
					//dojo.byId('codigoPostal').value="";
					//dijit.byId('codigoPostal').disabled=true;
					
					var wColonia = dijit.byId('idColoniaSelect');
					wColonia.reset();
					
					var wMunicipio = dijit.byId('idMunicipioSelect');
					wMunicipio.reset();
					
					var wEntidad = dijit.byId('idEntidadSelect');
					wEntidad.attr('disabled', false);
					wEntidad.reset();                        		
            		//dijit.byId('idEntidadSelect').disabled=false;
            		
            		entidadFederativaStore.url = "${context}comregpartnercompany.do?method=obtenerEntidad";
            		entidadFederativaStore.close();     
            		
            		var vValidar = document.getElementById('btnValidar');
            		vValidar.disabled = true;
            		//dijit.byId("btnValidar").setAttribute('disabled', true);       		            		                                
                }
                
                if(b == false){

					dijit.byId('Ctrl').value = 1;
					var wCP = dijit.byId('codigoPostal');
					wCP.attr('value','');
					wCP.attr('disabled', false);	
					//dojo.byId('codigoPostal').value="";					
					//dijit.byId('codigoPostal').disabled=false;					                        		
            		//dijit.byId('idEntidadSelect').disabled=true;
            		//dijit.byId('idMunicipioSelect').disabled=true;
            		//dijit.byId('idColoniaSelect').disabled=true;        
            		var wColonia = dijit.byId('idColoniaSelect');
            		wColonia.reset();
            		var wMunicipio = dijit.byId('idMunicipioSelect');
            		wMunicipio.reset();
            		var wEntidad = dijit.byId('idEntidadSelect');
            		wEntidad.reset();
            		wEntidad.attr('disabled', true);
            		dijit.byId('idMunicipioSelect').setAttribute('disabled', true);
            		dijit.byId('idColoniaSelect').setAttribute('disabled', true); 
            		var vValidar = document.getElementById('btnValidar');   	
            		vValidar.disabled = false;	
                }
            }
        },
        "checkCtrl");

        changePersonTypeRequired();
        fillUpAccessKey();
        });
        
  </script>
          		                
