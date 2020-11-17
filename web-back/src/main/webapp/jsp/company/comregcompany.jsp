<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="java.text.DateFormat"%>
<%@ page import="java.text.ParseException"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="mx.gob.stps.portal.persistencia.vo.CatalogoOpcionVO"%>
<%@ page import="mx.gob.stps.portal.core.infra.utils.Constantes"%>
<%@ page import="mx.gob.stps.portal.web.company.form.ComRegCompanyForm"%>
<%@ page import="mx.gob.stps.portal.core.oferta.detalle.helper.Utils"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script type="text/javascript" src="googiespell/AJS.js"></script>
<script type="text/javascript" src="googiespell/googiespell.js"></script>
<script type="text/javascript" src="googiespell/cookiesupport.js"></script>
<link href="googiespell/googiespell.css" rel="stylesheet" type="text/css" />

<style type="text/css">
li {
	background: url("images/bullet_naranja1.gif") no-repeat scroll 11px 13px;
	padding: 4px 5px 1px 19px;
}
.red3Font{
	color: #FF0000;
}
.Font60{
	font-size: 60%;
}
</style>

<%
  String context = request.getContextPath() +"/";
  String TELEFONO_CELULAR_ID = request.getSession().getAttribute("TELEFONO_CELULAR_ID").toString();
  String TELEFONO_CELULAR_DES = request.getSession().getAttribute("TELEFONO_CELULAR_DES").toString();
  String TELEFONO_FIJO_ID = request.getSession().getAttribute("TELEFONO_FIJO_ID").toString();
  String TELEFONO_FIJO_DES = request.getSession().getAttribute("TELEFONO_FIJO_DES").toString();
  String TERMINOS_CONDICIONES = request.getSession().getAttribute("TERMINOS_CONDICIONES").toString();
  String CLAVE_TELEFONO_FIJO = request.getSession().getAttribute("CLAVE_TELEFONO_FIJO").toString();
  String CLAVE_TELEFONO_CELULAR = request.getSession().getAttribute("CLAVE_TELEFONO_CELULAR").toString();
  
  ComRegCompanyForm formCompany = (ComRegCompanyForm)session.getAttribute("ComRegCompanyForm");
  
  SimpleDateFormat sdfSource = new SimpleDateFormat("yyyy-MM-dd");
  SimpleDateFormat sdfDestination = new SimpleDateFormat("MM-dd-yyyy hh:mm:ss");
  DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");    
  String fechaNacFormateada = "";
  String fechaActaFormateada = "";
  
  long lngIdTipoPersona = formCompany.getIdTipoPersona();
  long idFisica = Long.parseLong(String.valueOf(Constantes.TIPO_PERSONA.PERSONA_FISICA.getIdTipoPersona()));
  long idMoral = Long.parseLong(String.valueOf(Constantes.TIPO_PERSONA.PERSONA_MORAL.getIdTipoPersona()));


String vProxy = context + "/SpellCheck.do?method=gogiespell&lang=";  
%>

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
<noscript>Funciones de JavaScript desactivadas por navegador</noscript>
<script type="text/javascript">
function doSubmit(method){
	//document.ComRegCompanyForm.method.value=method;
	//document.ComRegCompanyForm.submit();	
    var vForm = dijit.byId('ComRegCompanyForm');
    document.getElementById('method').value=method;    
    //vForm.submit();		
    dojo.byId('ComRegCompanyForm').submit();
}

function setMensaje(mensaje){
	dojo.byId('mensaje').innerHTML = mensaje;
	dijit.byId('MensajeAlert').show();		
}


function doSubmitAjax(method){

	if (dijit.byId('ComRegCompanyForm').isValid() && checkOtherFields()){		
		document.getElementById('method').value=method;
		dojo.byId('btnGuardar').disabled=true;
		
		if (dijit.byId('idTipoEmpresaSelect').get('item') && dijit.byId('idTipoEmpresaSelect').get('item').label){
			dojo.byId('idTipoEmpresa').value = dijit.byId('idTipoEmpresaSelect').get('item').label[0];
		}
		if (dijit.byId('idActividadEconomicaSelect').get('item') && dijit.byId('idActividadEconomicaSelect').get('item').label){
			dojo.byId('idActividadEconomica').value = dijit.byId('idActividadEconomicaSelect').get('item').label[0];
		}
		if (dijit.byId('idMedioSelect').get('item') && dijit.byId('idMedioSelect').get('item').label){
			dojo.byId('idMedio').value = dijit.byId('idMedioSelect').get('item').label[0];
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

		document.ComRegCompanyForm.submit();
				 
	} else {
		var vCampos = '';
		vCampos += '<ul>';
		
		var chkVal = getRadioValue('idTipoPersona');
		
		if(chkVal==null){ 
			vCampos = vCampos + '<li> Tipo de persona.</li> ';
		} else {
			if(chkVal==<%=Constantes.TIPO_PERSONA.PERSONA_FISICA.getIdTipoPersona()%>){
				var nomb = dijit.byId('nombre').value;
				var ap1 = dijit.byId('apellido1').value;
				var fechaNac = dijit.byId('fechaNacimiento').value;
				if(!nomb){
					vCampos = vCampos + '<li> Nombre.</li>';	
					dijit.byId('nombre').focus();
				}
				if(!ap1){
					vCampos = vCampos + '<li> Primer Apellido.</li>';	
					dijit.byId('apellido1').focus();
				}			
				if(!fechaNac){
					vCampos = vCampos + '<li> Fecha de nacimiento.</li>';	
					dijit.byId('fechaNacimiento').focus();
				}						
			} else {
				var razon = dijit.byId('razonSocial').value;
				var fechaAct = dijit.byId('fechaActa').value;	
				if(!razon){
					vCampos = vCampos + '<li> Razón social.</li>';	
					dijit.byId('razonSocial').focus();
				}
				if(!fechaAct){
					vForm = false;	
					vCampos = vCampos + '<li> Fecha de acta constitutiva.</li>';	
					dijit.byId('fechaActa').focus();
				}		
			}			
		}
	
		//var vDescripcion = dijit.byId('descripcion').value;
		var vDescripcion = document.getElementById('descripcion').value;
		if(!vDescripcion){
			vCampos = vCampos + '<li> Descripción de la empresa.</li>';
			document.getElementById('descripcion').focus();
		} else {
			if(vDescripcion.length>2000){
				vCampos = vCampos + '<li> Descripción de la empresa es demasiado larga.</li>';
				document.getElementById('descripcion').focus();
			}
		}
		var vContactoEmpresa = dijit.byId('contactoEmpresa').value;
		if(!vContactoEmpresa){
			vCampos = vCampos + '<li> Nombre de la persona de contacto con la empresa.</li>';
			dijit.byId('contactoEmpresa').focus();
		}
		
		var vTipoEmpresa = getAnyElementValueById('idTipoEmpresaSelect');
		if(!vTipoEmpresa){
			vCampos = vCampos + '<li> Tipo de empresa.</li>';
			dijit.byId('idTipoEmpresaSelect').focus();
		}		

		var vTipoActividad = getAnyElementValueById('idActividadEconomicaSelect');
		if(!vTipoActividad){
			vCampos = vCampos + '<li> Actividad económica principal.</li>';
			dijit.byId('idActividadEconomicaSelect').focus();
		}		

		var vMedio = getAnyElementValueById('idMedioSelect');
		if(!vMedio){
			vCampos = vCampos + '<li> ¿Cómo se enteró del portal del empleo?.</li>';
			dijit.byId('idMedioSelect').focus();
		}		

		var vNumeroEmpleados = getAnyElementValueById('numeroEmpleados');
		if(!vNumeroEmpleados){
			vCampos = vCampos + '<li> Número de Empleados.</li>';
			dijit.byId('numeroEmpleados').focus();
		} else {
			if(parseInt(vNumeroEmpleados)<1){
				vCampos = vCampos + '<li> Número de Empleados.</li>';	
				dijit.byId('numeroEmpleados').focus();
			}
		}		
		
		var vCodigoPostal = getAnyElementValueById('codigoPostal');
		if(!vCodigoPostal){
			vCampos = vCampos + '<li> Código postal.</li>';
			dijit.byId('codigoPostal').focus();
		} else {
			var vCodigoPostal_len = vCodigoPostal.length;
			if(vCodigoPostal_len<5){
				vCampos = vCampos + '<li> Código postal debe tener 5 dígitos.</li>';	
				dijit.byId('codigoPostal').focus();
			}
		}		
		
		var vCalle = getAnyElementValueById('calle');
		if(!vCalle){
			vCampos = vCampos + '<li> Calle.</li>';
			dijit.byId('calle').focus();
		}		

		var vNumExterior = getAnyElementValueById('numeroExterior');
		if(!vNumExterior){
			vCampos = vCampos + '<li> Número Exterior.</li>';
			dijit.byId('numeroExterior').focus();
		}		

		var vEntreCalle = getAnyElementValueById('entreCalle');
		if(!vEntreCalle){
			vCampos = vCampos + '<li> Entre calle.</li>';
			dijit.byId('entreCalle').focus();
		}		
		
		var vYCalle = getAnyElementValueById('yCalle');
		if(!vYCalle){
			vCampos = vCampos + '<li> Y calle.</li>';
			dijit.byId('yCalle').focus();
		}		
		
		var vCorreoElectronico = document.getElementById('correoElectronico').value;
		if(!vCorreoElectronico){
			vCampos = vCampos + '<li> Correo electrónico.</li>';
			document.getElementById('correoElectronico').focus();
		}		
		var vConfirmarCorreoElectronico = document.getElementById('confirmarCorreoElectronico').value;	
		if(!vConfirmarCorreoElectronico){
			vCampos = vCampos + '<li> Confirmar correo electrónico.</li>';
			document.getElementById('confirmarCorreoElectronico').focus();
		}		

		var vIdTipoTelefono = getRadioValue('idTipoTelefono');	
		if(null==vIdTipoTelefono){
			vCampos = vCampos + '<li> Tipo de telefono principal.</li>';
		}		
		var vAcceso = getAnyElementValueById('acceso');	
		if(!vAcceso){
			vCampos = vCampos + '<li> Acceso del teléfono principal.</li>';
		}		
		var vClave = getAnyElementValueById('clave');	
		if(!vClave){
			vCampos = vCampos + '<li> Clave Lada del teléfono principal.</li>';
			dijit.byId('clave').focus();
		}		
		var vTelefono = getAnyElementValueById('telefono');	
		if(!vTelefono){
			vCampos = vCampos + '<li> Teléfono principal.</li>';
			//dijit.byId('telefono').focus();
		}		

		vCampos += '</ul>';

		var errmsg = 'Se muestra(n) el (los) campo(s) con inconsistencias:<br/>' + vCampos +'';

		errmsg += '<p style="text-align:center">';
		errmsg += '<input type="button" value="Cerrar" class="boton" onclick="javascript:closeDialogError();"/>';
		errmsg += '</p>';

		showDialogError(errmsg);

		//dojo.byId('message').innerHTML = '';
		//dojo.byId('message').innerHTML = errmsg;
		//var vMensaje = 'Los siguientes campos muestran datos inválidos - <br/>Se muestra(n) el (los) campo(s) con inconsistencias:<br/> ' + vCampos;
		//setMensaje(vMensaje);
		//getLocationHash();
	}	
}
var dialogError;

function showDialogError(errmsg){
	dialogError = new dijit.Dialog({
        title: 'Datos inválidos',
        content: errmsg,
        style: "width:500px;",
        showTitle: true, draggable : false, closable : true,
        onShow : function(){window.scrollTo(0,0);}
    });

	dialogError.show();
}

function closeDialogError(){
	dialogError.hide();
}

function cancelConfirmation() {
	var answer = confirm("Los datos no guardados se perderán ¿Continuar?");
	if (answer){
		doSubmit('cancelar');
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


function disableMousePasting(e) {
    if (event.button == 2 || event.button == 3) {
        alert('Por favor, no copie la información en este campo.');        
        return false;
    }
    return true;
}

function caracteresValidos(e){
    	var tecla_codigo = (document.all) ? e.keyCode : e.which;
    	if(tecla_codigo==8 || tecla_codigo==0) return true;
    	if (tecla_codigo==225 || tecla_codigo==233 || tecla_codigo==237 || tecla_codigo==243 || tecla_codigo==250) return true; //vocales minusculas con acento
    	if (tecla_codigo==193 || tecla_codigo==201 || tecla_codigo==205 || tecla_codigo==211 || tecla_codigo==218) return true; //vocales mayuzculas con acento
    	if (tecla_codigo==209 || tecla_codigo==241 ) return true; //Ñ y ñ
   		
    	var patron =/[A-Za-z\s\-.áéíóúÁÉÍÓÚñÑ0-9.,;:]/;
    	tecla_valor = String.fromCharCode(tecla_codigo);
    	return patron.test(tecla_valor);

    }

function validarFormatoCorreo(correo) {
	var regExp = /^[a-zA-Z0-9_-]+(?:\.[a-zA-Z0-9_-]+)*@(?:[a-zA-Z0-9](?:[a-zA-Z0-9-_]*[a-zA-Z0-9-])?\.)+[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?$/;
	if (!regExp.test(correo.value)) {
		alert('Formato de correo electronico inválido');
		correo.focus();
	}
}

function validateWebsite(website){
	var regExp = /(http|https):\/\/(\w+:{0,1}\w*@)?(\S+)(:[0-9]+)?(\/|\/([\w#!:.?+=&%@!-\/]))?/;
	if (!regExp.test(website.value)) {
		alert('Formato de pagina web inválido');
		website.focus();
	}
}


//funcion para validar campos de manera alterna al DOJO
function checkOtherFields(){
	var vForm = true;
	var vMessage = '';
	var chkVal = getRadioValue('idTipoPersona');
	var vDescripcion = document.getElementById('descripcion');
	var vCorreoElectronico = document.getElementById('correoElectronico').value;
	var vConfirmarCorreoElectronico = document.getElementById('confirmarCorreoElectronico').value;	
	var vTipoTelefono = getRadioValue('idTipoTelefono'); 	
	var vClave = dijit.byId('clave');
	var vTelefono = dijit.byId('telefono');
   	var vAsegurarDatos = document.getElementById("aseguraDatos"); 
   	
	if(vCorreoElectronico!=vConfirmarCorreoElectronico){
		vForm = false;
		vMessage = vMessage + '<li>Correo electrónico y Confirmar correo electrónico deben ser iguales.</li>';
		document.getElementById('correoElectronico').focus();
	}
		
	if(chkVal==null){
		vForm = false;	
		vMessage = vMessage + '<li>Debe seleccionar el Tipo de persona.</li>';
	} else {
		if(chkVal==<%=Constantes.TIPO_PERSONA.PERSONA_FISICA.getIdTipoPersona()%>){
			var nomb = dijit.byId('nombre').value;
			var ap1 = dijit.byId('apellido1').value;
			var fechaNac = dijit.byId('fechaNacimiento').value;
			if(!nomb){
				vForm = false;	
				vMessage = vMessage + '<li>Debe proporcionar el nombre.</li>';
				dijit.byId('nombre').focus();
			}
			if(!ap1){
				vForm = false;	
				vMessage = vMessage + '<li>Debe proporcionar el primer apellido.</li>';
				dijit.byId('apellido1').focus();	
			}			
			if(!fechaNac){
				vForm = false;	
				vMessage = vMessage + '<li>Debe proporcionar la fecha de nacimiento.</li>';	
				dijit.byId('fechaNacimiento').focus();	
			}						
		} else {
			var razon = dijit.byId('razonSocial').value;
			var fechaAct = dijit.byId('fechaActa').value;	
			if(!razon){
				vForm = false;	
				vMessage = vMessage + '<li>Debe proporcionar la razón social.</li>';	
				dijit.byId('razonSocial').focus();	
			}
			if(!fechaAct){
				vForm = false;	
				vMessage = vMessage + '<li>Debe proporcionar la fecha de acta constitutiva.</li>';	
				dijit.byId('fechaActa').focus();	
			}		
		}	
	}
	if(!vDescripcion.value){
		vForm = false;
		vMessage = vMessage + '<li>Debe proporcionar la descripcion de la empresa.</li>';	
		vDescripcion.focus();
	}
	if(!vAsegurarDatos.checked){
		vForm = false;
		vMessage = vMessage + '<li>Debe asegurar que sus datos son correctos.</li>';	
		vAsegurarDatos.focus();	
	}
	if(vClave.value.length==2 && vTelefono.value.length!=8){
		vForm = false;
		vMessage = vMessage + '<li>El teléfono debe tener 8 dígitos si la clave Lada tiene 2 dígitos.</li>';
		vTelefono.focus();	
	}else if(vClave.value.length==3 && vTelefono.value.length!=7){
		vForm = false;
		vMessage = vMessage + '<li>El teléfono debe tener 7 dígitos si la clave Lada tiene 3 dígitos.</li>';	
		vTelefono.focus();	
	}
	
	
	if(vMessage.length>0){
		
		vMessage = '<ul>' + vMessage + '</ul>';
		
		showDialogError(vMessage);
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
	if(chkVal==<%=Constantes.TIPO_PERSONA.PERSONA_FISICA.getIdTipoPersona()%>){		
		dijit.byId('razonSocial').attr('value','');		
		dijit.byId('fechaActa').attr('value','');	
		dijit.byId('nombre').setDisabled(false);
		dijit.byId('apellido1').setDisabled(false);				
		dijit.byId('apellido2').setDisabled(false);				
		dijit.byId('fechaNacimiento').setDisabled(false);		
		dijit.byId('razonSocial').setDisabled(true);
		dijit.byId('fechaActa').setDisabled(true);	
	} else if(chkVal==<%=Constantes.TIPO_PERSONA.PERSONA_MORAL.getIdTipoPersona()%>) {
		dijit.byId('nombre').attr('value','');	
		dijit.byId('apellido1').attr('value','');
		dijit.byId('apellido2').attr('value','');
		dijit.byId('fechaNacimiento').attr('value','');	
		dijit.byId('nombre').setDisabled(true);
		dijit.byId('apellido1').setDisabled(true);
		dijit.byId('apellido2').setDisabled(true);
		dijit.byId('fechaNacimiento').setDisabled(true);		
		dijit.byId('razonSocial').setDisabled(false);
		dijit.byId('fechaActa').setDisabled(false);
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
		} else if(vClave.value.length==2) {
			if(vTelefono.value.length!=8){
				alert('Debe proporcionar un teléfono de 8 dígitos');
			}
		} else if(vClave.value.length==3){
			if(vTelefono.value.length!=7){
				alert('Debe proporcionar un teléfono de 7 dígitos');
			}	
		}
		
	}	
	//TERMINA TELEFONOS		
	    
</script>

		<form name="ComRegCompanyForm" id="ComRegCompanyForm" method="post" action="comregcompany.do" dojoType="dijit.form.Form">
		
			<input type="hidden" name="method" id="method" value="registrar"/>
			<input type="hidden" name="Ctrl" id="Ctrl" dojoType="dijit.form.TextBox" value="1"/>
			<input type="hidden" name="cpnuevo" id="cpnuevo" dojoType="dijit.form.TextBox" value="0"/>
			<input type="hidden" name="hiddenDesTipoEmpresa" id="hiddenDesTipoEmpresa" value="${ComRegCompanyForm.hiddenDesTipoEmpresa}"/>
			<input type="hidden" name="hiddenDesActividadEconomica" id="hiddenDesActividadEconomica" value="${ComRegCompanyForm.hiddenDesActividadEconomica}"/>
			<input type="hidden" name="hiddenDesMedio" id="hiddenDesMedio" value="${ComRegCompanyForm.hiddenDesMedio}"/>
			<input type="hidden" name="acceso" id="acceso" dojoType="dijit.form.TextBox" value="${ComRegCompanyForm.acceso}" />
			<input type="hidden" name="codigoPostalShow" id="codigoPostalShow" dojoType="dijit.form.TextBox" value="${ComRegCompanyForm.codigoPostal}" />
			

		<h3>Datos generales de empresa</h3>
       			 <div>   			 
	                  <DIV class="entero">
					  <div id="message"></div>
						<html:messages id="errors" >
							<span class="Font60 red3Font"><bean:write name="errors"/></span><br/>
						</html:messages>
						
						<html:messages id="messages" message="true" >
							<span class="Font60 red3Font"><bean:write name="messages"/></span><br/>
						</html:messages>    				   
	                  </DIV>        			 
			        <DIV class="entero">&nbsp;<br>
			        <span class="un_cuarto"><strong><label for="rfc">RFC</label></strong><br>
			        <input id="rfc" name="rfc" maxlength="13" size="13"
			        	dojoType="dijit.form.ValidationTextBox" required="false" regExp="^[A-Za-z\s\áéíóúÁÉÍÓÚñÑ0-9/']{1,150}$" 
			        	value="${ComRegCompanyForm.rfc}"
			        	invalidMessage="Dato inválido" trim="true" />        
			        <label for="rfc"></label>	
			        </span>			
			        <span class="un_cuarto">
			        <strong><label for="idTipoPersona">Tipo de persona</label>*</strong><br>
			        <% 
			           if(lngIdTipoPersona==idFisica){
			        	   out.print("Física&nbsp;<input type=\"radio\" id=\"idTipoPersona\" name=\"idTipoPersona\" value=\"1\" checked onClick=\"changePersonTypeRequired()\" >&nbsp;&nbsp;&nbsp;");
			        	   out.print("Moral&nbsp;<input type=\"radio\" id=\"idTipoPersona\" name=\"idTipoPersona\" value=\"2\" onClick=\"changePersonTypeRequired()\" >");
			           } else if(lngIdTipoPersona==idMoral) {
			        	   out.print("Física&nbsp;<input type=\"radio\" id=\"idTipoPersona\" name=\"idTipoPersona\" value=\"1\" onClick=\"changePersonTypeRequired()\" >&nbsp;&nbsp;&nbsp;");
			        	   out.print("Moral&nbsp;<input type=\"radio\" id=\"idTipoPersona\" name=\"idTipoPersona\" value=\"2\" checked onClick=\"changePersonTypeRequired()\" >");
			           } else {
			        	   out.print("Física&nbsp;<input type=\"radio\" id=\"idTipoPersona\" name=\"idTipoPersona\" value=\"1\" onClick=\"changePersonTypeRequired()\" >&nbsp;&nbsp;&nbsp;");
			        	   out.print("Moral&nbsp;<input type=\"radio\" id=\"idTipoPersona\" name=\"idTipoPersona\" value=\"2\"  onClick=\"changePersonTypeRequired()\" >");
			           }	 
			        	%>
			        </span>			                
			        </DIV>  
			             			          			   
       			    <DIV class="entero">
       			    <span class="un_cuarto">
			        <strong><label for="nombre">Nombre(s)</label>*</strong><br>
			        <input id="nombre" name="nombre" maxlength="50" size="50" 	        	
			        	dojoType="dijit.form.ValidationTextBox" required="false" regExp="^[A-Za-z\s\-.áéíóúÁÉÍÓÚñÑ/']{1,50}$" onpaste="return false;" uppercase="true"
			        	value="${ComRegCompanyForm.nombre}"
			        	invalidMessage="Dato inválido" missingMessage="Dato requerido" trim="true"/>
			        	      			    
       			    </span>
       			    <span class="un_cuarto">
			        <strong><label for="apellido1">Primer apellido</label>*</strong><br>
			        <input id="apellido1" name="apellido1" maxlength="50" size="50" 	        	
			        	dojoType="dijit.form.ValidationTextBox" required="false" regExp="^[A-Za-z\s\-.áéíóúÁÉÍÓÚñÑ/']{2,50}$" onpaste="return false;" uppercase="true"
			        	value="${ComRegCompanyForm.apellido1}"
			        	invalidMessage="Dato inválido" missingMessage="Dato requerido" trim="true"/>
			        <label for="apellido1"></label>       			    
       			    </span>
       			    <span class="un_cuarto">
			        <strong><label for="apellido2">Segundo apellido</label></strong><br>
			        <input id="apellido2" name="apellido2" maxlength="50" size="50" 
			        	dojoType="dijit.form.ValidationTextBox" required="false" regExp="^[A-Za-z\s\-.áéíóúÁÉÍÓÚñÑ/']{1,50}$" onpaste="return false;" uppercase="true"
			        	value="${ComRegCompanyForm.apellido2}"
			        	invalidMessage="Dato inválido" trim="true"/>
			      	               			    
       			    </span>
			        <span class="un_cuarto">
			        <strong>Fecha de nacimiento*</strong><br/>
					<input type="text" name="fechaNacimiento" id="fechaNacimiento" dojoType="dijit.form.DateTextBox" required="false" 
					constraints={datePattern:'dd/MM/yyyy'}
					value="${ComRegCompanyForm.fechaNacimiento}" trim="true" />
					<label for="fechaNacimiento">Seleccionar una fecha</label>
					</span>               			    
       			    </DIV>
       			           			     			           			    
       			    <DIV class="entero">
       			    <span class="un_cuarto">
			        <strong> <label for="razonSocial">Razón social</label>*</strong><br/>
			        <input id="razonSocial" name="razonSocial" maxlength="150" size="50" 			
			        	dojoType="dijit.form.ValidationTextBox" required="false" onpaste="return false;" regExp="^[A-Za-z\s\d\-.&,áéíóúÁÉÍÓÚñÑ/']{3,150}$"  uppercase="true"			        	
			        	value="${ComRegCompanyForm.razonSocial}"
			        	invalidMessage="Dato inválido" missingMessage="Dato requerido" trim="true"/>
			              			    
					</span>        
					<span class="un_cuarto">
			        <strong>Fecha de acta constitutiva*</strong><br/>
					<input type="text" name="fechaActa" id="fechaActa" dojoType="dijit.form.DateTextBox" 
					constraints={datePattern:'dd/MM/yyyy'}
					required="false" value="${ComRegCompanyForm.fechaActa}" trim="true" />
					<label for="fechaActa">Seleccionar una fecha</label>                					
					</span>        
			        </DIV>
			        
			        <div class="entero">
				        <span class="un_cuarto">
					        <strong><label for="nombreComercial">Nombre Comercial</label></strong><br/>
					        <input id="nombreComercial" name="nombreComercial" maxlength="150" size="50" 			
					        	dojoType="dijit.form.ValidationTextBox" required="false" onpaste="return false;" regExp="^[A-Za-z\s\d\-.áéíóúÁÉÍÓÚñÑ/']{1,150}$"  uppercase="true"			        	
					        	value="${ComRegCompanyForm.nombreComercial}"
					        	invalidMessage="Dato inválido" missingMessage="Dato requerido" trim="true"/>
					             			    
						</span>			        
			        </div>
			        	
					<!-- Independientes de tipo de persona -->       			    
					<DIV class="entero">
			        <strong><label for="descripcion">Descripción de la empresa</label>*</strong><br/>
				    <textarea name="descripcion" id="descripcion" maxlength="2000" 
	        	    onkeypress="return caracteresValidos(event);"	     
	        	    onpaste="return false;"  	    
	        	    regExp="^[A-Za-z\s\-.áéíóúÁÉÍÓÚñÑ0-9.,;:/']{1,2000}$" 
	        	    style="width:550px;min-height:120px;_height:200px;" trim="true"
	        	    required="true">${ComRegCompanyForm.descripcion}<%//=formCompany.getDescripcion()%></textarea>
	        	    <script type="text/javascript">
	         		 var vSpellCon = new GoogieSpell("googiespell/", '<%=vProxy%>');
	         		 vSpellCon.setLanguages({'es': 'Español'});
	         		 vSpellCon.hideLangWindow();
	         		 vSpellCon.decorateTextarea("descripcion");
				    </script>			            	
					</DIV>	
       			    
       			    <DIV class="entero">
       			    <span class="un_medio">
			        <strong><label for="contactoEmpresa">Nombre de la persona de contacto con la empresa</label>*</strong><br>
			        	<input id="contactoEmpresa" name="contactoEmpresa"  maxlength="150" size="50"         	
			        	dojoType="dijit.form.ValidationTextBox" required="true" regExp="^[A-Za-z\s\-.áéíóúÁÉÍÓÚñÑ/']{1,150}$" 
			        	value="${ComRegCompanyForm.contactoEmpresa}"
			        	invalidMessage="Dato inválido" missingMessage="Dato requerido" trim="true" />
			        	
			        </span>
					<span class="un_medio">
		        	<strong><label for="idTipoEmpresaSelect">Tipo de empresa</label>*</strong><br>   
					<input type="hidden" name="idTipoEmpresa" id="idTipoEmpresa" value="${ComRegCompanyForm.idTipoEmpresa}"/>
					<div dojoType="dojo.data.ItemFileReadStore" jsId="tiposEmpresaStore" 
						url="${context}comregcompany.do?method=tiposEmpresa"></div>
					<select dojotype="dijit.form.ComboBox" store="tiposEmpresaStore" id="idTipoEmpresaSelect" required="true" ></select>			        
       			    </span>		
       			    </DIV>	
       			    
       			     <DIV class="entero">
       			    <span class="un_medio">   			    
		        	<strong><label for="idActividadEconomicaSelect">Actividad económica principal</label>*</strong><br>   
					<input type="hidden" name="idActividadEconomica" id="idActividadEconomica" value="${ComRegCompanyForm.idActividadEconomica}" displayValue="${ComRegCompanyForm.hiddenDesActividadEconomica}" />
					<div dojoType="dojo.data.ItemFileReadStore" jsId="tiposActividadEconomicaStore" 
						url="${context}comregcompany.do?method=tiposActividadEconomica"></div>
					<select dojotype="dijit.form.ComboBox" store="tiposActividadEconomicaStore" id="idActividadEconomicaSelect" required="true" style="width:100%"></select>
					</span>	      
					</DIV> 	
					<DIV class="entero">		    		        
					<span class="un_medio">
			        <strong><label for="numeroEmpleados">Número de empleados</label>*</strong><br> 
			        <input id="numeroEmpleados" name="numeroEmpleados" maxlength="5" size="5"         	
			        	dojoType="dijit.form.ValidationTextBox" required="true" regExp="^[0-9]{1,5}$" 
			        	value="${ComRegCompanyForm.numeroEmpleados}"
			        	invalidMessage="Dato inválido" missingMessage="Dato requerido" trim="true" />
			                    			           			    					
					</span>	
					</DIV>	
					
					<DIV class="entero"> 
       			    <span class="un_medio">   			    
		        	<strong>¿<label for="idMedioSelect">Cómo se enteró del portal del empleo</label>?*</strong><br/>   
					<input type="hidden" name="idMedio" id="idMedio" value="${ComRegCompanyForm.idMedio}" />
					<div dojoType="dojo.data.ItemFileReadStore" jsId="medioEnteradoStore" 
						url="${context}comregcompany.do?method=medioEnterado"></div>
					<select dojotype="dijit.form.ComboBox" store="medioEnteradoStore" id="idMedioSelect" required="true" style="width:100%"></select>       			         
					</span>		
					</DIV>
					<DIV class="entero"> 				
                      <span class="un_medio"><input id="confidencial" name="confidencial" type="checkbox" value="1" />&nbsp;<strong><label for="confidencial">Deseo que mis datos permanezcan confidenciales</label></strong>&nbsp;                      
                      </span>        			   
       			    </DIV>				
       			   
       			   <!-- DOMICILIO -->
				   <h3>Domicilio</h3>
				   <DIV class="entero">
       			   <span class="un_tercio">
      			   <strong><label for="codigoPostal">Código postal</label>*</strong><br>
			       <input id="codigoPostal" name="codigoPostal" maxlength="5" size="5" value="${ComRegCompanyForm.codigoPostal}"
			        	dojoType="dijit.form.ValidationTextBox" required="true" regExp="^[0-9]{1,5}" 
			        	invalidMessage="Dato inválido" trim="true" />        
			       	       			   		
       			   </span>	
       			   <span class="un_tercio">
					<input type="checkBox" id="checkCtrl">
					<label for="checkCtrl">No conozco mi código postal</label>               			   
       			   </span>	
       			   <span class="un_tercio">
				   <!-- <div id="btnValidar" dojoType="dijit.form.Button">Validar CP</div>  -->
       			   <input type="button" name="btnValidar" id="btnValidar" class="boton" value="Validar CP">		
       			   </span>
       			   </DIV>
       			   
       			   <DIV class="entero">
			        <span class="un_tercio"><strong><label for="pais">País </label></strong><br>			        
			        <input name="pais" id="pais" maxlength="10" size="6" value="México" disabled />
			       	
			        </span>
			        </DIV>
			        
			        <DIV class="entero">  
			        <span class="un_tercio">     			   
					<strong><label for="idEntidadSelect">Entidad federativa</label>*</strong><br>
							<input type="hidden" name="idEntidad" id="idEntidad" value="" />
							<div dojoType="dojo.data.ItemFileReadStore" jsId="entidadFederativaStore" urlPreventCache="true" clearOnClose="true" ></div>
						   <select dojotype="dijit.form.FilteringSelect" store="entidadFederativaStore" id="idEntidadSelect" required="true" disabled="disabled" autoComplete="false"></select>
			        </span>
			        <span class="un_tercio">
			        <strong><label for="idMunicipioSelect">Delegación ó municipio</label>*</strong><br>
							<input type="hidden" name="idMunicipio" id="idMunicipio" value="" />
							<div dojoType="dojo.data.ItemFileReadStore" jsId="municipioStore" urlPreventCache="true" clearOnClose="true" ></div>
							<select dojotype="dijit.form.FilteringSelect" store="municipioStore" id="idMunicipioSelect" required="true" disabled="disabled" autoComplete="false"></select>			        
			        </span>
			        <span class="un_tercio">
			        <strong><label for="idColoniaSelect">Colonia</label>*</strong><br>
					<input type="hidden" name="idColonia" id="idColonia" value="" />
					<div dojoType="dojo.data.ItemFileReadStore" jsId="coloniaStore" urlPreventCache="true" clearOnClose="true" ></div>
					<select dojotype="dijit.form.FilteringSelect" store="coloniaStore" id="idColoniaSelect" required="true"  autoComplete="true"></select>			        
			        </span> 
       			   </DIV>
       			   
       			   
			        <DIV class="entero">  
			        <span class="un_tercio"><strong><label for="calle">Calle</label>*</strong><br>  
			        <input id="calle" name="calle" maxlength="150" size="60" value="${ComRegCompanyForm.calle}" 
			        	dojoType="dijit.form.ValidationTextBox" required="true" regExp="^[a-zA-ZáéíóúÁÉÍÓÚñÑ0-9\s]{1,150}$" uppercase="true"
			        	invalidMessage="Dato inválido" missingMessage="Dato requerido" trim="true"/>			             			   
			       
			        </span> 
			        
			        <span class="un_tercio"><strong><label for="numeroExterior">Número exterior</label>*</strong><br> 
			        <input id="numeroExterior" name="numeroExterior" maxlength="50" size="4" value="${ComRegCompanyForm.numeroExterior}"
			        	dojoType="dijit.form.ValidationTextBox" required="true" regExp="^[a-zA-ZáéíóúÁÉÍÓÚñÑ0-9\s]{1,50}" 
			        	invalidMessage="Dato inválido" trim="true" />        
			        				            			   
			        </span> 
			        
			        <span class="un_tercio"><strong><label for="numeroInterior">Número interior</label></strong><br>
			        <input id="numeroInterior" name="numeroInterior" maxlength="50" size="4" value="${ComRegCompanyForm.numeroInterior}"
			        	dojoType="dijit.form.ValidationTextBox" required="false" regExp="^[a-zA-ZáéíóúÁÉÍÓÚñÑ0-9\s]{1,50}" 
			        	invalidMessage="Dato inválido" trim="true" />        
			        				             			   
			        </span> 			        
       			    </DIV>
       			   
			        <DIV class="entero">  
			        <span class="un_tercio"><strong><label for="entreCalle">Entre calles</label>*</strong><br> 
			        <input id="entreCalle" name="entreCalle" maxlength="150" size="60" value="${ComRegCompanyForm.entreCalle}" 
			        	dojoType="dijit.form.ValidationTextBox" required="true" regExp="^[a-zA-ZáéíóúÁÉÍÓÚñÑ0-9\s]{1,150}$" uppercase="true"
			        	invalidMessage="Dato inválido" missingMessage="Dato requerido" trim="true"/>
								            			   
			        </span> 			      
			        <span class="un_tercio"><strong><label for="yCalle">Y</label>*</strong><br>
			        <input id="yCalle" name="yCalle" maxlength="150" size="60" value="${ComRegCompanyForm.yCalle}" 
			        	dojoType="dijit.form.ValidationTextBox" required="true" regExp="^[a-zA-ZáéíóúÁÉÍÓÚñÑ0-9\s]{1,150}$" uppercase="true"
			        	invalidMessage="Dato inválido" missingMessage="Dato requerido" trim="true"/>
			            			   
			        </span> 			        
       			    </DIV>
       			   <!-- TERMINA DOMICILIO --> 
       			   
			    <h3>Datos de identificación de la cuenta</h3>			   
			        <DIV class="entero">  
			        <span class="un_medio">  
			        <strong><label for="correoElectronico">Correo electrónico</label>*</strong><br>
                   	<input type="text" name="correoElectronico" id="correoElectronico" size="50" maxlength="65" trim="true"
                   	value="${ComRegCompanyForm.correoElectronico}" onchange="validarFormatoCorreo(this);" oncopy="return false;" 
                   	oncut="return false" onpaste="return false;"  />			        
			        </span> 			
			        </div>
			        <DIV class="entero">  
			        <span class="un_medio">   
			        <strong><label for="confirmarCorreoElectronico">Confirmar correo electrónico</label>*</strong><br>
                    <input type="text" name="confirmarCorreoElectronico" id="confirmarCorreoElectronico" size="50" maxlength="65" trim="true"
                    value="" onchange="validarFormatoCorreo(this);" oncopy="return false;" oncut="return false" 
                    onpaste="return false;" ondragover="return false;" />			        
			        </span> 			        			          			                			          
       			    </DIV>
       			   
					<!-- TELEFONO -->       			   
			<h3>Teléfono*</h3>       		
			        <DIV class="entero">  
			        <span class="un_tercio"><strong><label for="idTipoTelefono">Tipo de teléfono</label>*</strong><br>
					<%
					long selectedPhoneType = (long)formCompany.getIdTipoTelefono();
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
			        <span class="un_tercio"><strong><label for="accesoShow">Acceso</label>*</strong><br>
					 <input id="accesoShow" name="accesoShow" maxlength="3" size="3"
					 	dojoType="dijit.form.ValidationTextBox" required="false" regExp="^(<%=CLAVE_TELEFONO_CELULAR%>|<%=CLAVE_TELEFONO_FIJO%>)$"
					 	value="${ComRegCompanyForm.acceso}"	
					 	invalidMessage="Dato inválido" missingMessage="Dato requerido" trim="true" disabled="disabled" />
					       			        
			        </span> 			 
			        <span class="un_tercio"><strong><label for="clave">Clave Lada</label>*</strong><br>  
					<input id="clave" name="clave" maxlength="3" size="3"
					 	dojoType="dijit.form.ValidationTextBox" required="true" regExp="^[0-9]{2,3}$"
					 	value="${ComRegCompanyForm.clave}"	   
					 	invalidMessage="Dato inválido" missingMessage="Dato requerido" trim="true" />	        			        
			        </span> 			
			        </div>
			        <div  class="entero">  
			        <span class="un_tercio"><strong><label for="telefono">Teléfono</label>*</strong><br>
					<input id="telefono" name="telefono" maxlength="8" size="8" onBlur="changePhoneSizeRequired()"
					 	dojoType="dijit.form.ValidationTextBox" required="true" regExp="^[0-9]{7,8}$"
					 	value="${ComRegCompanyForm.telefono}"				 	
					 	invalidMessage="Dato inválido" missingMessage="Dato requerido" trim="true" />
					        			          
			        </span> 			 
			        <span class="un_tercio"><strong><label for="extension">Extensión</label></strong><br>
					<input id="extension" name="extension" maxlength="6" size="6"
					 	dojoType="dijit.form.ValidationTextBox" required="false" regExp="^[0-9]{1,6}$"
					 	value="${ComRegCompanyForm.extension}"	
					 	invalidMessage="Dato inválido" missingMessage="Dato requerido" trim="true" />
					         			           
                    <a href="#" class="links" onclick="openPhoneWindow()">Agregar Teléfono</a>
                    </span>
       			    </DIV>  
       			    <!-- TERMINA TELEFONO -->
					<DIV class="entero">
					<span class="un_medio">
			        <strong><label for="paginaWeb">Página Web</label></strong><br>
			        <input type="text" id="paginaWeb" name="paginaWeb" maxlength="65" value="${ComRegCompanyForm.paginaWeb}" onchange="validateWebsite(this);">
			         	
			        </span>				
					</DIV>				
					
					<DIV class="entero">
					<span class="un_medio">
					<input id="aceptacionTerminos" name="aceptacionTerminos" type="checkbox" value="1" />&nbsp;
			        <strong><label for="aceptacionTerminos">He leído y acepto los términos y condiciones</label></strong>			        
			        <a href="<%=context%><%=TERMINOS_CONDICIONES%>" class="links" target="_blank">Ver términos y condiciones</a>					
			        </span>				
					</DIV>
					
					<DIV class="entero">
					<span class="entero">
					<input id="aseguraDatos" name="aseguraDatos" type="checkbox" value="1" />&nbsp;
			        <strong><label for="aseguraDatos">Aseguro que los datos que he proporcionado son correctos</label></strong>		        					
			        </span>				
					</DIV>
					
					<DIV class="entero" id="divRegis" style="text-align: center;">         			    				   
                 	<span class="un_medio">                                        
					<input type="button" id="btnGuardar" value="Guardar" class="boton" onclick="doSubmitAjax('salvar');" />
					</span>
					<span class="un_medio">
					<input type="button" id="btnCancel" value="Cancelar" class="boton" onclick="cancelConfirmation()" />
					</span>
                	</DIV>
                
                  </DIV>
                  <!-- cierra div principal -->
                  
					<div dojoType="dijit.Dialog" id="MensajeAlert" title="Mensaje" style="display:none" draggable ="false">
						  <table width="400px" height="400px" >
							 <tr align="center">
								 <td><div id ="mensaje" style="width:400px;height:400px;overflow:auto;vertical-align:middle;"></div>&nbsp;</td>				 
							 </tr>
						 </table>						
					</div>                  
                  

                  
</form>
  <script type="text/javascript">
        dojo.addOnLoad(function() {

       var vDesActividadEconomica =  document.getElementById('hiddenDesActividadEconomica').value;
       var vDesTipoEmpresa =  document.getElementById('hiddenDesTipoEmpresa').value;
       var vDesMedio =  document.getElementById('hiddenDesMedio').value;

       	if(dijit.byId('idActividadEconomicaSelect')){
       		dijit.byId('idActividadEconomicaSelect').setDisplayedValue(vDesActividadEconomica);
       		dijit.byId('idTipoEmpresaSelect').setDisplayedValue(vDesTipoEmpresa);
       		dijit.byId('idMedioSelect').setDisplayedValue(vDesMedio);		
       	}               	
       	
       	
        //PARA UPDATE
		var vCodigoPostal = dojo.byId('codigoPostal').value;
				
		dijit.byId('idEntidadSelect').disabled=false;
		dijit.byId('idMunicipioSelect').disabled=false;
		dijit.byId('idColoniaSelect').disabled=false;

    	entidadFederativaStore.url = "${context}comregcompany.do?method=obtenerEntidadJSON" + "&" + "codigoPostal="+ vCodigoPostal;
    	entidadFederativaStore.close();
		
    	entidadFederativaStore.fetch({
          	onComplete: function(items, request) {                  	
          		if (items.length == 0) return;                    	
          		dijit.byId('idEntidadSelect').attr('value', items[0].value);
          	}
    	});

    	municipioStore.url = "${context}comregcompany.do?method=obtenerMunicipioJSON" + "&" + "codigoPostal="+ vCodigoPostal;
    	municipioStore.close();

    	municipioStore.fetch({
          	onComplete: function(items, request) {                  	
          		if (items.length == 0) return;                    	
          		dijit.byId('idMunicipioSelect').attr('value', items[0].value);
          	}
    	});

    	coloniaStore.url = "${context}comregcompany.do?method=obtenerColoniaJSON" + "&" + "codigoPostal="+ vCodigoPostal;
    	coloniaStore.close();

    	coloniaStore.fetch({
          	onComplete: function(items, request) {                  	
          		if (items.length == 0) return;                    	
          		dijit.byId('idColoniaSelect').attr('value', items[0].value);
          	}
    	});
    	
    	//TERMINA PARA UPDATE
        	
        dojo.connect(document.ComRegCompanyForm.btnValidar, 'onclick', function() {

 			if(dijit.byId('codigoPostal').value == ''){
 			   alert("El código postal es necesario.");
 			   return;
			}
 			
 			if(dijit.byId('codigoPostal').value.length <5){
  			   alert("El código postal debe tener 5 dígitos.");
  			   return;
 			}
 			

            if(dijit.byId('Ctrl').value == 1){

				var vCodigoPostal = dijit.byId('codigoPostal').value;
				var vMsjNotF = 0;
				document.ComRegCompanyForm.method.value = 'obtenerEntidadJSON';
	
				dijit.byId('idEntidadSelect').disabled=false;
				dijit.byId('idMunicipioSelect').disabled=false;
				dijit.byId('idColoniaSelect').disabled=false;

            	entidadFederativaStore.url = "${context}comregcompany.do?method=obtenerEntidadJSON" + "&" + "codigoPostal="+ vCodigoPostal;
            	entidadFederativaStore.close();
								
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
            	            	
            	municipioStore.url = "${context}comregcompany.do?method=obtenerMunicipioJSON" + "&" + "codigoPostal="+ vCodigoPostal;
            	municipioStore.close();

            	municipioStore.fetch({
                  	onComplete: function(items, request) {                  	
                  		if (items.length == 0) return;                    	
                  		dijit.byId('idMunicipioSelect').attr('value', items[0].value);
                  	}
            	});

            	coloniaStore.url = "${context}comregcompany.do?method=obtenerColoniaJSON" + "&" + "codigoPostal="+ vCodigoPostal;
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
            		
            	municipioStore.url = "${context}comregcompany.do?method=obtenerMunicipio" + "&" + "idEntidad="+ vEntidad;
            	municipioStore.close();            	
            	            	
			}        
        });


        dojo.connect(dijit.byId('idMunicipioSelect'), "onChange", function() {
        
            if(dijit.byId('Ctrl').value == 0){

				var vEntidad   = dijit.byId('idEntidadSelect').get('value');
				var vMunicipio = dijit.byId('idMunicipioSelect').get('value');
				var vColonia = dijit.byId('idColoniaSelect').get('value');
					
            	//dijit.byId('coloniaSelect').disabled=false;
				if(vColonia>0){
            		var wColonia = dijit.byId('idColoniaSelect');
            		wColonia.reset();					
				}             	
            		
            	dijit.byId('idColoniaSelect').disabled=false;
            		
            	coloniaStore.url = "${context}comregcompany.do?method=obtenerColonia" + "&" + "idEntidad="+ vEntidad + "&" + "idMunicipio="+ vMunicipio;
            	coloniaStore.close();
            	
			}        
        });


        dojo.connect(dijit.byId("idColoniaSelect"), "onChange", function() {

            if(dijit.byId('Ctrl').value == 0){

            	dijit.byId('codigoPostal').disabled=false;

				dojo.byId('idEntidad').value   = dijit.byId('idEntidadSelect').get('value');
				dojo.byId('idMunicipio').value = dijit.byId('idMunicipioSelect').get('value');
				dojo.byId('idColonia').value   = dijit.byId('idColoniaSelect').get('value');
                
				dojo.byId('method').value="obtenerCodigoPostal";				

	
				dojo.xhrPost(
				{
				  url: 'comregcompany.do',
				  form:'ComRegCompanyForm',
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
			        //wCP.attr('disabled', true);
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
            		
            		entidadFederativaStore.url = "${context}comregcompany.do?method=obtenerEntidad";
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
										
            		//dijit.byId('idMunicipioSelect').disabled=true;
					var wColonia = dijit.byId('idColoniaSelect');
					wColonia.reset();
					//wColonia('disabled', true);									
            		//dijit.byId('idColoniaSelect').disabled=true;   
            		
					var wMunicipio = dijit.byId('idMunicipioSelect');
					wMunicipio.reset();
					//wMunicipio('disabled', true);
            	
					var wEntidad = dijit.byId('idEntidadSelect');
					wEntidad.reset();
					wEntidad.attr('disabled', true);	
					dijit.byId('idMunicipioSelect').setAttribute('disabled', true);
					dijit.byId('idColoniaSelect').setAttribute('disabled', true);
            		//dijit.byId('idEntidadSelect').disabled=true;
					
					var vValidar = document.getElementById('btnValidar');
					vValidar.disabled = false;
					//dijit.byId("btnValidar").setAttribute('disabled', false);
            		
                }
            }
        },
        "checkCtrl");

changePersonTypeRequired();
fillUpAccessKey();
        });                       
  </script>