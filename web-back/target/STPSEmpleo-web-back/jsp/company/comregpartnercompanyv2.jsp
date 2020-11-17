<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="mx.gob.stps.portal.persistencia.vo.CatalogoOpcionVO"%>
<%@ page import="mx.gob.stps.portal.web.company.form.ComRegPartnerCompanyFormv2"%>
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
  ComRegPartnerCompanyFormv2 formPartnerCompany = (ComRegPartnerCompanyFormv2)session.getAttribute("ComRegPartnerCompanyFormv2");
  String idTipo = String.valueOf(formPartnerCompany.getPadreIdTipoPersona()); 
  String strName = String.valueOf(formPartnerCompany.getPadreLeyendaNombre());
  String strDesTipoPersona = ""; 
	if(CLAVE_TIPO_PERSONA_FISICA.equalsIgnoreCase(idTipo)){
		strDesTipoPersona = Constantes.TIPO_PERSONA.PERSONA_FISICA.getTipoPersona();
	} else {
		strDesTipoPersona = Constantes.TIPO_PERSONA.PERSONA_MORAL.getTipoPersona();
	}     
	long idTerceraEmpresa = formPartnerCompany.getIdTerceraEmpresa();
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
	function confirmParent() {
		var answer = confirm("Los datos no guardados se perderán ¿Continuar?");
		if (answer){
			location.replace('<%=context%>comupdcompany.do?method=init');
		}
	}
	
	function getAnyElementValueById(elementId){
		var vElement = dijit.byId(elementId).value;
		if(vElement==undefined || vElement==''){
			vElement = document.getElementById(elementId).value;
		}
		return vElement;
	}		
	
	function doSubmit(method){
	    var vForm = dijit.byId('ComRegPartnerCompanyFormv2');
	    document.getElementById('method').value=method;
	    dojo.byId('ComRegPartnerCompanyFormv2').submit();
	}
	
	function doSubmitAjax(method){
		var vCampos = '';
		document.getElementById('method').value=method;
		var vForm = dijit.byId('ComRegPartnerCompanyFormv2');
		if(method=='cambiarEstatus' || method=='editar'){	
			//cambiarEstatus: deshabilitar boton Guardar, setear datos de catalogos de domicilio y codigo postal
			var vSelectedPartner = document.getElementById('selectPartnerCompany').selectedIndex;
			if(vSelectedPartner<1){
				if(method=='cambiarEstatus'){
					alert('Debe seleccionar una tercera empresa para modificar su estatus');
				} else if(method=='editar'){
					alert('Debe seleccionar una tercera empresa para modificar sus datos');
				}
				document.getElementById('selectPartnerCompany').focus();	
			} else {
				vForm = document.getElementById('ComRegPartnerCompanyFormv2');
				vForm.submit();				    		
			}			
		} else if(method=='salvar'){	
			
			if (vForm.isValid()){
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
			var answer = confirm("¿Está seguro que desea limpiar el formulario para capturar una nueva tercera empresa? Todos sus cambios se perderán.");
			if (answer){								
				document.getElementById('idTerceraEmpresa').value='';
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
		if (dijit.byId('contactoEmpresa')) dijit.byId('contactoEmpresa').set('value', '');
		if (dijit.byId('nombreComercial')) dijit.byId('nombreComercial').set('value', '');
		if (dijit.byId('numeroEmpleados')) dijit.byId('numeroEmpleados').set('value', '');
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
		var chkVal = getRadioValue('idTipoPersona');
		var vDescripcion = document.getElementById('descripcion').value;
		var vContactoEmpresa = dijit.byId('contactoEmpresa').value;
		var vTipoEmpresa = getAnyElementValueById('idTipoEmpresaSelect');
		var vTipoActividad = getAnyElementValueById('idActividadEconomicaSelect');
		var vNumeroEmpleados = getAnyElementValueById('numeroEmpleados');
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
		if(!vDescripcion){
			vCampos = vCampos + ' Descripción de la empresa.<br/>';
			document.getElementById('descripcion').focus();
		} else {
			if(vDescripcion.length>2000){
				vCampos = vCampos + ' Descripción de la empresa es demasiado larga.<br/>';
				document.getElementById('descripcion').focus();
			}
		}		
		if(!vContactoEmpresa){
			vCampos = vCampos + ' Nombre de la persona de contacto con la empresa.<br/>';
			dijit.byId('contactoEmpresa').focus();
		}
		if(!vTipoEmpresa){
			vCampos = vCampos + ' Tipo de empresa.<br/>';
			dijit.byId('idTipoEmpresaSelect').focus();
		}		
		if(!vTipoActividad){
			vCampos = vCampos + ' Actividad económica principal.<br/>';
			dijit.byId('idActividadEconomicaSelect').focus();
		}		
		if(!vNumeroEmpleados){
			vCampos = vCampos + ' Número de empleados.<br/>';
			dijit.byId('numeroEmpleados').focus();
		} else {
			if(parseInt(vNumeroEmpleados)<1){
				vCampos = vCampos + ' Número de empleados.<br/>';
				dijit.byId('numeroEmpleados').focus();	
			}
		}	
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
		if(!vCalle){
			vCampos = vCampos + ' Calle.<br/>';
			dijit.byId('calle').focus();
		}		
		if(!vNumExterior){
			vCampos = vCampos + ' Número exterior.<br/>';
			dijit.byId('numeroExterior').focus();
		}		
		if(!vEntreCalle){
			vCampos = vCampos + ' Entre calle.<br/>';
			dijit.byId('entreCalle').focus();
		}		
		if(!vYCalle){
			vCampos = vCampos + ' Y calle.<br/>';
			dijit.byId('yCalle').focus();
		}				
		if(!vCorreoElectronico){
			vCampos = vCampos + ' Correo electrónico.<br/>';
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
			vCampos = vCampos + ' Tipo de teléfono principal.<br/>';
		}				
		if(!vAcceso){
			vCampos = vCampos + ' Acceso del teléfono principal.<br/>';
		}				
		if(!vClave){
			vCampos = vCampos + ' Clave Lada del teléfono principal.<br/>';
			dijit.byId('clave').focus();
		}				
		if(!vTelefono){
			vCampos = vCampos + ' Teléfono principal.<br/>';
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
					
	function cancelConfirmation() {
		var answer = confirm("Los datos no guardados se perderán ¿Continuar?");
		if (answer){
			doSubmit('cancelar');
		}
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
	
	//funcion para cambiar los campos requeridos dependiendo del tipo de persona
	function changePersonTypeRequired(){
		var chkVal = getRadioValue('idTipoPersona');
		
		if (chkVal==<%=CLAVE_TIPO_PERSONA_FISICA%>){	
			dijit.byId('nombre').setDisabled(false);
			dijit.byId('apellido1').setDisabled(false);
			dijit.byId('apellido2').setDisabled(false);
			//dijit.byId('razonSocial').set('value', '');
			document.getElementById('razonSocial').value = '';
			dijit.byId('razonSocial').setDisabled(true);
		} else if (chkVal==<%=CLAVE_TIPO_PERSONA_MORAL%>) {
			//dijit.byId('nombre').set('value', '');
			//dijit.byId('apellido1').set('value', '');
			//dijit.byId('apellido2').set('value', '');
			document.getElementById('nombre').value = '';
			document.getElementById('apellido1').value = '';
			document.getElementById('apellido2').value = '';
			dijit.byId('nombre').setDisabled(true);
			dijit.byId('apellido1').setDisabled(true);
			dijit.byId('apellido2').setDisabled(true);			
			dijit.byId('razonSocial').setDisabled(false);
		}
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
	
	function setMensaje(mensaje){
		dojo.byId('mensaje').innerHTML = mensaje;
		dijit.byId('MensajeAlert').show();		
	}	
</script>

<form name="ComRegPartnerCompanyFormv2" id="ComRegPartnerCompanyFormv2" method="post" action="comregpartnercompanyv2.do" 
	dojoType="dijit.form.Form"  dojoattachevent="onreset:_onReset,onsubmit:_onSubmit">
	<input type="hidden" name="method" id="method" value="init" />
	<input type="hidden" name="idTerceraEmpresa" id="idTerceraEmpresa" value="${ComRegPartnerCompanyFormv2.idTerceraEmpresa}"/>
	<input type="hidden" name="Ctrl" id="Ctrl" dojoType="dijit.form.TextBox" value="1" />
	<input type="hidden" name="cpnuevo" id="cpnuevo" dojoType="dijit.form.TextBox" value="1"/>	
	<input type="hidden" name="hiddenDesTipoEmpresa" id="hiddenDesTipoEmpresa" value="${ComRegPartnerCompanyFormv2.hiddenDesTipoEmpresa}"/>
	<input type="hidden" name="hiddenDesActividadEconomica" id="hiddenDesActividadEconomica" value="${ComRegPartnerCompanyFormv2.hiddenDesActividadEconomica}"/>
	<input type="hidden" name="acceso" id="acceso" dojoType="dijit.form.TextBox" value="${ComRegPartnerCompanyFormv2.acceso}" />
	<input type="hidden" name="codigoPostalShow" id="codigoPostalShow" dojoType="dijit.form.TextBox" value="${ComRegPartnerCompanyFormv2.codigoPostal}" />
	<input type="hidden" name="hiddenPreCorreoElectronico" id="hiddenPreCorreoElectronico" value="${ComRegPartnerCompanyFormv2.preCorreoElectronico}"/>
	<div class="formulario" >
		<h3>Terceras Empresas</h3>
		<DIV class="entero" >
			<a href="#" onclick="confirmParent();"  class="links" >Regresar a Empresa padre</a><br/>
		</DIV>		
		<%-- ENCABEZADO --%>
        <DIV class="entero">&nbsp;<br>             
	        <span class="un_cuarto"><strong>Id Portal del Empleo: </strong><br/><c:out value="${ComRegPartnerCompanyFormv2.padreIdPortalEmpleo}"/><br></span>			
	        <span class="un_cuarto"><strong>Tipo de persona: </strong><br/> <%=DES_TIPO_EMPRESA_PADRE%></span>			                
        </DIV>  
        <c:choose>         
	        <c:when test="${ComRegPartnerCompanyFormv2.padreIdTipoPersona eq '1'}">
		    	<DIV class="entero">
		    	<span class="un_cuarto">
		        <strong>Nombre(s): </strong><br/><c:out value="${ComRegPartnerCompanyFormv2.padreNombre}"/>
		    	</span>
		    	<span class="un_cuarto">
		        <strong>Primer apellido: </strong><br/><c:out value="${ComRegPartnerCompanyFormv2.padreApellido1}"/>
		    	</span>
		    	<span class="un_cuarto">
		        <strong>Segundo apellido: </strong><br/><c:out value="${ComRegPartnerCompanyFormv2.padreApellido2}"/>
		    	</span>
		    	</DIV>	        
	        </c:when>
	        <c:otherwise>
		    	<DIV class="entero">
		    	<span class="un_cuarto">
		        <strong>Razón social: </strong><br/><c:out value="${ComRegPartnerCompanyFormv2.padreRazonSocial}"/>
				</span>        
		        </DIV>	        
	        </c:otherwise>
        </c:choose>
		<%-- MENSAJES Y ERRORES --%>
		<DIV class="entero">
			<div id="message"></div>
			<html:messages id="errors" ><span class="Font80 red3Font"><bean:write name="errors"/></span><br/></html:messages>					
			<html:messages id="messages" message="true" ><span class="Font80 red3Font"><bean:write name="messages"/></span><br/></html:messages>    				                     
        </DIV> 
        <%-- LISTADO DE TERCERAS EMPRESAS REGISTRADAS --%>
        <h3>Terceras empresas registradas</h3>
		<DIV>
	    	<DIV class="entero">
		    	<span class="un_medio">
		    	<strong><label for="selectPartnerCompany">Terceras empresas registradas - Estatus</label></strong><br>
				<select id="selectPartnerCompany" name="selectPartnerCompany" size="1" style="width:100%">
				<option value="-1">Seleccionar</option>
				<c:if test="${not empty ComRegPartnerCompanyFormv2.lstTercerasEmpresas}">
					<c:forEach var="terceraEmpresa" items="${ComRegPartnerCompanyFormv2.lstTercerasEmpresas}" varStatus="rowCounter">
						<c:if test="${terceraEmpresa.estatus == 1 }">
							<option value="${terceraEmpresa.idTerceraEmpresa}">${terceraEmpresa.nombreEmpresa} - ACTIVO</option>
						</c:if>
						<c:if test="${terceraEmpresa.estatus == 2 }">
							<option value="${terceraEmpresa.idTerceraEmpresa}">${terceraEmpresa.nombreEmpresa} - INACTIVO</option>
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
        <h3>Registro de tercera empresa</h3>
        <input type="hidden" name="estatus" id="estatus" value="${ComRegPartnerCompanyFormv2.estatus}"/>
        <DIV>
        	<DIV class="entero">&nbsp;<br>
		        <span class="un_tercio"><strong><label for="rfc">RFC</label></strong><br/>
		        <input id="rfc" name="rfc" maxlength="13" size="13"
		        	dojoType="dijit.form.ValidationTextBox" required="false" regExp="^[A-Za-z\s\áéíóúÁÉÍÓÚñÑ0-9/']{1,150}$" 
		        	value="${ComRegPartnerCompanyFormv2.rfc}"
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
		        	value="${ComRegPartnerCompanyFormv2.nombre}"
		        	invalidMessage="Dato inválido" missingMessage="Dato requerido" trim="true"/>
		               			    
		    	</span>
		    	<span class="un_tercio">
		        <strong><label for="apellido1">Primer apellido</label>*</strong><br/>
		        <input id="apellido1" name="apellido1" maxlength="50" size="50" 	        	
		        	dojoType="dijit.form.ValidationTextBox" required="false" regExp="^[A-Za-z\s\-.áéíóúÁÉÍÓÚñÑ/']{1,50}$"  uppercase="true"
		        	value="${ComRegPartnerCompanyFormv2.apellido1}"
		        	invalidMessage="Dato inválido" missingMessage="Dato requerido" trim="true"/>
		               			    
		    	</span>
		    	<span class="un_tercio">
		        <strong><label for="apellido2">Segundo apellido</label></strong><br/>
		        <input id="apellido2" name="apellido2" maxlength="50" size="50" 
		        	dojoType="dijit.form.ValidationTextBox" required="false" regExp="^[A-Za-z\s\-.áéíóúÁÉÍÓÚñÑ/']{1,50}$"  uppercase="true"
		        	value="${ComRegPartnerCompanyFormv2.apellido2}"
		        	invalidMessage="Dato inválido" trim="true"/>
		        			               			    
		    	</span>
	    	</DIV>        	
	    	<DIV class="entero">
		    	<span class="un_tercio">
		        <strong><label for="razonSocial">Razón social</label>*</strong><br/>
		        <input id="razonSocial" name="razonSocial" maxlength="150" size="50" 			
		        	dojoType="dijit.form.ValidationTextBox" required="false" regExp="^[A-Za-z\s\d\]\-.&áéíóúÁÉÍÓÚñÑ/']{1,150}$"  uppercase="true"
		        	value="${ComRegPartnerCompanyFormv2.razonSocial}"
		        	invalidMessage="Dato inválido" missingMessage="Dato requerido" trim="true"/>
		               			    
				</span>        
	        </DIV>        	
	        <div class="entero">
		        <span class="un_cuarto">
			        <strong><label for="nombreComercial">Nombre Comercial</label></strong><br/>
			        <input id="nombreComercial" name="nombreComercial" maxlength="150" size="50" 			
			        	dojoType="dijit.form.ValidationTextBox" required="false" onpaste="return false;" regExp="^[A-Za-z\s\d\-.&áéíóúÁÉÍÓÚñÑ/']{1,150}$"  uppercase="true"			        	
			        	value="${ComRegPartnerCompanyFormv2.nombreComercial}"
			        	invalidMessage="Dato inválido" missingMessage="Dato requerido" trim="true"/>
			               			    
				</span>			        
	        </div>        	
			<DIV class="entero">
	        <strong><label for="descripcion">Descripción de la empresa</label>*</strong></span> <br/>        
		    <textarea name="descripcion" id="descripcion" maxlength="2000" onkeypress="return caracteresValidos(event);"
	      	    regExp="^[A-Za-z\s\-.áéíóúÁÉÍÓÚñÑ0-9.,;:/']{1,2000}$" 
	      	    style="width:550px;min-height:120px;_height:200px;" 
	      	    required="true">${ComRegPartnerCompanyFormv2.descripcion}<%//=formCompany.getDescripcion()%></textarea>
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
		        	value="${ComRegPartnerCompanyFormv2.contactoEmpresa}"
		        	invalidMessage="Dato inválido" missingMessage="Dato requerido" trim="true" />
		        	
		        </span>
				<span class="un_medio">
		       	<strong><label for="idTipoEmpresaSelect">Tipo de empresa</label>*</strong><br/>   
				<input type="hidden" name="idTipoEmpresa" id="idTipoEmpresa" value="${ComRegPartnerCompanyFormv2.idTipoEmpresa}" />
				<div dojoType="dojo.data.ItemFileReadStore" jsId="tiposEmpresaStore" url="${context}comregpartnercompanyv2.do?method=tiposEmpresa"></div>
				<select dojotype="dijit.form.ComboBox" store="tiposEmpresaStore" id="idTipoEmpresaSelect" required="false"></select>			        
		    	</span>		
	    	</DIV>        	
	    	<DIV class="entero">        	
		    	<span class="un_medio">   			    
		       	<strong><label for="idActividadEconomicaSelect">Actividad económica principal</label>*</strong><br/>   
				<input type="hidden" name="idActividadEconomica" id="idActividadEconomica" value="${ComRegPartnerCompanyFormv2.idActividadEconomica}" />
				<div dojoType="dojo.data.ItemFileReadStore" jsId="tiposActividadEconomicaStore" 
					url="${context}comregpartnercompanyv2.do?method=tiposActividadEconomica"></div>
				<select dojotype="dijit.form.ComboBox" store="tiposActividadEconomicaStore" id="idActividadEconomicaSelect" required="false" style="width:100%"></select>
				</span>	       	
				</DIV>
				<DIV class="entero"> 		    		        
				<span class="un_medio">
		        <strong><label for="numeroEmpleados">Número de empleados</label>*</strong><br/> 
		        <input id="numeroEmpleados" name="numeroEmpleados" maxlength="5" size="5"         	
		        	dojoType="dijit.form.ValidationTextBox" required="false" regExp="^[0-9]{1,5}$"
		        	value="${ComRegPartnerCompanyFormv2.numeroEmpleados}"
		        	invalidMessage="Dato inválido" missingMessage="Dato requerido" trim="true" />
		                       			           			    					
				</span>	
			</DIV>        	
        </DIV>
		<!-- DOMICILIO --> 
		<h3>Domicilio</h3>
		<DIV class="entero">
	       	<span class="un_tercio">
	      	<strong><label for="codigoPostal">Código postal</label>*</strong><br/>
			<input id="codigoPostal" name="codigoPostal" maxlength="5" size="5" value="${ComRegPartnerCompanyFormv2.codigoPostal}" 
				dojoType="dijit.form.ValidationTextBox" required="false" regExp="^[0-9]{1,5}"  invalidMessage="Dato inválido" trim="true" />        
	        	       			   		
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
	        <input id="calle" name="calle" maxlength="150" size="60" value="${ComRegPartnerCompanyFormv2.calle}" 
	        	dojoType="dijit.form.ValidationTextBox" required="false" regExp="^[a-zA-ZáéíóúÁÉÍÓÚñÑ0-9\s]{1,150}$" uppercase="true"
	        	invalidMessage="Dato inválido" missingMessage="Dato requerido" trim="true"/>			             			   
	       
	        </span> 	        
	        <span class="un_tercio"><strong><label for="numeroExterior">Número exterior</label>*</strong><br/> 
	        <input id="numeroExterior" name="numeroExterior" maxlength="50" size="4" value="${ComRegPartnerCompanyFormv2.numeroExterior}"
	        	dojoType="dijit.form.ValidationTextBox" required="false" regExp="^[a-zA-ZáéíóúÁÉÍÓÚñÑ0-9\s]{1,50}" 
	        	invalidMessage="Dato inválido" trim="true" />        
	        				            			   
	        </span> 	        
	        <span class="un_tercio"><strong><label for="numeroInterior">Número interior</label></strong><br/>
	        <input id="numeroInterior" name="numeroInterior" maxlength="50" size="4" value="${ComRegPartnerCompanyFormv2.numeroInterior}"
	        	dojoType="dijit.form.ValidationTextBox" required="false" regExp="^[a-zA-ZáéíóúÁÉÍÓÚñÑ0-9\s]{1,50}" 
	        	invalidMessage="Dato inválido" trim="true" />        
	        				             			   
	        </span> 			        
    	</DIV>    	
        <DIV class="entero">  
	        <span class="un_tercio"><strong><label for="entreCalle">Entre calles</label>*</strong><br/> 
	        <input id="entreCalle" name="entreCalle" maxlength="150" size="60" value="${ComRegPartnerCompanyFormv2.entreCalle}" 
	        	dojoType="dijit.form.ValidationTextBox" required="false" regExp="^[a-zA-ZáéíóúÁÉÍÓÚñÑ0-9\s]{1,150}$" uppercase="true"
	        	invalidMessage="Dato inválido" missingMessage="Dato requerido" trim="true"/>
						            			   
	        </span> 			      
	        <span class="un_tercio"><strong><label for="yCalle">Y</label>*</strong><br/>
	        <input id="yCalle" name="yCalle" maxlength="150" size="60" value="${ComRegPartnerCompanyFormv2.yCalle}" 
	        	dojoType="dijit.form.ValidationTextBox" required="false" regExp="^[a-zA-ZáéíóúÁÉÍÓÚñÑ0-9\s]{1,150}$" uppercase="true"
	        	invalidMessage="Dato inválido" trim="true"/>
	             			   
	        </span> 			        
    	</DIV>
    	<!-- TERMINA DOMICILIO --> 
    	<h3>Datos de identificación de la cuenta</h3>    	
    	<%		    
    	if(idTerceraEmpresa>0){
    		%>           
           <p class="entero">
			   <strong><label for="correoElectronico">Correo electrónico</label>*</strong><br/>
		       <input type="text" name="correoElectronico" id="correoElectronico" size="50" maxlength="65" trim="true"
		       value="${ComRegPartnerCompanyFormv2.correoElectronico}" onchange="validarFormatoCorreo(this);" oncopy="return false;" 
		       oncut="return false" onpaste="return false;" readonly="true"  />			        
           </p>
           <div id="confCorreo" class="entero" style="visibility:hidden;">
			   <strong><label for="confirmarCorreoElectronico">Confirmar correo electrónico</label>*</strong><br/>
		       <input type="text" name="confirmarCorreoElectronico" id="confirmarCorreoElectronico" size="50" maxlength="65" trim="true"
		       value="${ComRegPartnerCompanyFormv2.correoElectronico}" onchange="validarFormatoCorreo(this);" oncopy="return false;" 
		       oncut="return false" onpaste="return false;" ondragover="return false;" />			        				        
		   </div>	
		   <p class="entero"><a href="javascript:showConf();" class="un_cuarto links">Actualizar correo electr&oacute;nico</a></p>    		
    		<%
    	} else {
    		%>
	        <DIV class="entero">  
		        <span class="un_medio">  
		        <strong><label for="correoElectronico">Correo electrónico</label>*</strong><br/>
		       	<input type="text" name="correoElectronico" id="correoElectronico" size="50" maxlength="65" 
		       	value="${ComRegPartnerCompanyFormv2.correoElectronico}" onchange="validarFormatoCorreo(this);" oncopy="return false;" 
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
	        <span class="un_tercio"><strong><label for="acceso">Acceso</label>*</strong><br/>
			 <input id="accesoShow" name="accesoShow" maxlength="3" size="3"
			 	dojoType="dijit.form.ValidationTextBox" required="false" regExp="^(<%=CLAVE_TELEFONO_CELULAR%>|<%=CLAVE_TELEFONO_FIJO%>)$"
			 	value="${ComRegPartnerCompanyFormv2.acceso}"
			 	invalidMessage="Dato inválido" missingMessage="Dato requerido" trim="true" disabled="disabled" />
			       			        
	        </span> 			 
	        <span class="un_tercio"><strong><label for="clave">Clave Lada</label>*</strong><br/> 
			<input id="clave" name="clave" maxlength="3" size="3"
			 	dojoType="dijit.form.ValidationTextBox" required="false" regExp="^[0-9]{2,3}$"
			 	value="${ComRegPartnerCompanyFormv2.clave}"
			 	invalidMessage="Dato inválido" missingMessage="Dato requerido" trim="true" />	        			        
	        </span> 		
	        </DIV>
	        <DIV class="entero">  	 
	        <span class="un_tercio"><strong><label for="telefono">Teléfono</label>*</strong><br/>
			<input id="telefono" name="telefono" maxlength="8" size="8" onBlur="changePhoneSizeRequired()"
			 	dojoType="dijit.form.ValidationTextBox" required="false" regExp="^[0-9]{7,8}$"
			 	value="${ComRegPartnerCompanyFormv2.telefono}"			 	
			 	invalidMessage="Dato inválido" missingMessage="Dato requerido" trim="true" />
			        			          
	        </span> 			 
	        <span class="un_tercio"><strong><label for="extension">Extensión</label></strong><br/>
			<input id="extension" name="extension" maxlength="8" size="8"
			 	dojoType="dijit.form.ValidationTextBox" required="false" regExp="^[0-9]{1,6}$"
			 	value="${ComRegPartnerCompanyFormv2.extension}"
			 	invalidMessage="Dato inválido" missingMessage="Dato requerido" trim="true" />
			        			           
	        <a href="#" class="links" onclick="openPhoneWindow()">Agregar teléfono</a>
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
	        	entidadFederativaStore.url = "${context}comregpartnercompanyv2.do?method=obtenerEntidadJSON" + "&" + "codigoPostal="+ codigoPostal;
	        	entidadFederativaStore.close();    		  				
			} else {
	    		entidadFederativaStore.url = "${context}comregpartnercompanyv2.do?method=obtenerEntidad";
	    		entidadFederativaStore.close();     
			}
		}

		function fillCboMunicipio(codigoPostal, tipoLlenado){
			//llenar el combo de municipio
			var vEntidad = dijit.byId('idEntidadSelect').get('value');
			if(tipoLlenado=='JSON'){
	        	municipioStore.url = "${context}comregpartnercompanyv2.do?method=obtenerMunicipioJSON" + "&" + "codigoPostal="+ codigoPostal;
	        	municipioStore.close();  	        	
			} else {
	        	municipioStore.url = "${context}comregpartnercompanyv2.do?method=obtenerMunicipio" + "&" + "idEntidad="+ vEntidad;
	        	municipioStore.close();
			}
		}
		
		function fillCboColonia(codigoPostal, tipoLlenado){
			//llenar el combo de colonia
			var vEntidad = dijit.byId('idEntidadSelect').get('value');
			var vMunicipio = dijit.byId('idMunicipioSelect').get('value');
			if(tipoLlenado=='JSON'){
	        	coloniaStore.url = "${context}comregpartnercompanyv2.do?method=obtenerColoniaJSON" + "&" + "codigoPostal="+ codigoPostal;
	        	coloniaStore.close();
			} else {
	        	coloniaStore.url = "${context}comregpartnercompanyv2.do?method=obtenerColonia" + "&" + "idEntidad="+ vEntidad + "&" + "idMunicipio="+ vMunicipio;
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
			loadPreviousData();
           	var vDesActividadEconomica =  document.getElementById('hiddenDesActividadEconomica').value;
           	var vDesTipoEmpresa =  document.getElementById('hiddenDesTipoEmpresa').value;
           	if(dijit.byId('idActividadEconomicaSelect')){
           		dijit.byId('idActividadEconomicaSelect').setDisplayedValue(vDesActividadEconomica);
           		dijit.byId('idTipoEmpresaSelect').setDisplayedValue(vDesTipoEmpresa);	
            }                     			
			
			dojo.connect(document.getElementById('btnValidar'), "onclick", function() {
	        	validateCtrlCP();
	            if(dijit.byId('Ctrl').value == 1){
					var vCodigoPostal = dijit.byId('codigoPostal').value;
					var vMsjNotF = 0;
					document.ComRegPartnerCompanyFormv2.method.value = 'obtenerEntidadJSON';
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
	            	dijit.byId('idColoniaSelect').set('value', '${ComRegPartnerCompanyFormv2.idColoniaSelected}');
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
					  url: 'comregpartnercompanyv2.do',
					  form:'ComRegPartnerCompanyFormv2',
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
	        changePersonTypeRequired();
	        fillUpAccessKey();    	        			
		});
</script>
	
	
	
	
	
	
	
	




