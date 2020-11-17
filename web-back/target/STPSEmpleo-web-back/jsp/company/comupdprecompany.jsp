<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="mx.gob.stps.portal.persistencia.vo.CatalogoOpcionVO"%>
<%@ page import="mx.gob.stps.portal.core.infra.utils.Constantes"%>
<%@ page import="mx.gob.stps.portal.web.company.form.ComUpdPreCompanyForm"%>
<%@ page import="java.text.DateFormat"%>
<%@ page import="java.text.ParseException"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import = "java.io.*" %>
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
  String ID_TIPO_USUARIO = request.getSession().getAttribute("ID_TIPO_USUARIO").toString();
  ComUpdPreCompanyForm formCompany = (ComUpdPreCompanyForm)session.getAttribute("ComUpdPreCompanyForm");
  String vProxy = context + "/SpellCheck.do?method=gogiespell&lang=";
%>

                <%
                //Dependiendo del tipo de persona, concatenar nombre o presentar razon social
                String idTipo = Long.toString(formCompany.getIdTipoPersona());               
                String strName = "";
                String strDesTipoPersona = ""; 
				if(CLAVE_TIPO_PERSONA_FISICA.equalsIgnoreCase(idTipo)){
					strName = formCompany.getNombre() + " " + formCompany.getApellido1() + " " + formCompany.getApellido2();
					strDesTipoPersona = Constantes.TIPO_PERSONA.PERSONA_FISICA.getTipoPersona();
				} else {
					strName = formCompany.getRazonSocial();
					strDesTipoPersona = Constantes.TIPO_PERSONA.PERSONA_MORAL.getTipoPersona();
				}             
				
	        	SimpleDateFormat sdfSource = new SimpleDateFormat("MM-dd-yyyy hh:mm:ss");
	        	SimpleDateFormat sdfDestination = new SimpleDateFormat("dd/MM/yyyy");
	        	DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	        	String strFechaNacimiento = "";
	        	String strFechaActa = "";
	        	if(null!=formCompany.getFechaNacimiento()){
	        		strFechaNacimiento = sdfDestination.format(formCompany.getFechaNacimiento());
	        	}
	        	if(null!=formCompany.getFechaActa()){
	        		strFechaActa = sdfDestination.format(formCompany.getFechaActa());
	        	}	        	
                %>                        

<link href="${PATH_CSS_SWB_APP}estilos_empleo.css" rel="stylesheet" type="text/css" />
<link href="${PATH_CSS_SWB_APP}estilos_mi_espacio.css" rel="stylesheet" type="text/css" />
<link href="${PATH_CSS_SWB_APP}estilos_canal.css" rel="stylesheet" type="text/css" />

<link href='http://fonts.googleapis.com/css?family=Droid+Sans' rel='stylesheet' type='text/css' />

<link href="${PATH_CSS_SWB_APP}estilos_canal.css" rel="stylesheet" type="text/css" />
<link href="${PATH_CSS_SWB_APP}estilos_mi_espacio.css" rel="stylesheet" type="text/css" />
<!--  TODO QUITAR ESTILOS_TABLA????? -->
<link href="${PATH_CSS_SWB}css_aplicacion/estilos_tabla.css" rel="stylesheet" type="text/css" />

<style type="text/css">
	@import "dojotoolkit/dojo/resources/dojo.css";
	@import "dojotoolkit/dijit/themes/claro/claro.css";
</style>

<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js"></script>

<!--EMPIEZA EXPAND COLLAPSE-->

<noscript>Funciones de JavaScript desactivadas por navegador</noscript>
<script type="text/javascript">
<!--//--><![CDATA[//><!--
$(function() {
    $("a.expand").toggler();   
});
//--><!]]>
</script>
<!--TERMINA EXPAND COLLAPSE-->


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
</script>

<script type="text/javascript">
	//Desplegar ventana para captura de telefonos adicionales
	function openPhoneWindow(){	
	 	var newPhoneWin = window.open("<%=context%>phone.do?method=init", "Teléfonos","height=500,width=900,resizable=yes,scrollbars=yes"); 	
	} 	
	//Desplegar ventana para modificar logotipo
	function openLogoWindow(){	
	 	var newLogoWin = window.open('<c:url value="/uploadcompanylogo.do?method=init"/>', "Cambiar imagen","height=500,width=900,resizable=yes,scrollbars=yes"); 	
	} 	
	//Desplegar ventana para captura de contactos
	function openContactWindow(){	
	 	var newContactWin = window.open("<%=context%>regCont.do?method=init", "Registrar contactos","height=500,width=900,resizable=yes,scrollbars=yes"); 	
	} 	
	

</script>	

<script type="text/javascript">
function doSubmit(method){
	
    //var vForm = dijit.byId('ComUpdPreCompanyForm');
    document.getElementById('method').value=method;
    //vForm.submit();	
	//document.ComUpdPreCompanyForm.method.value=method;
	document.ComUpdPreCompanyForm.submit();    		
}

function cancelConfirmation() {
	var answer = confirm("Los datos no guardados se perderán ¿Continuar?");
	if (answer){
		<%if(formCompany.getIdTipoUsuario()==(long)Constantes.TIPO_USUARIO.ADMINISTRADOR.getIdTipoUsuario()){%>
			doSubmit('cancelaModificacionAdmin');
		<%}else{%>
			doSubmit('init');
		<%}%>
	}
}

	function clrAdrFlds(){
		var vCodigoPostal = getAnyElementValueById('codigoPostal');
		var oCodigoPostal = document.getElementById('hiddenCodigoPostal').value;
		if(vCodigoPostal!=oCodigoPostal){   
			dijit.byId('calle').attr('value', String());
			dijit.byId('numeroExterior').attr('value', String());
			dijit.byId('numeroInterior').attr('value', String());
			dijit.byId('entreCalle').attr('value', String());
			dijit.byId('yCalle').attr('value', String());
		}
	}
 
function doSubmitAjax(method){
	var vCampos = '';
	var vCamposRelev = '';
	
	if (dijit.byId('ComUpdPreCompanyForm').isValid() && checkOtherFields()){		
		document.getElementById('method').value=method;
		dojo.byId('btnGuardar').disabled=true;
		
		if(dijit.byId('idTipoEmpresaSelect')){
			if (dijit.byId('idTipoEmpresaSelect').get('item') && dijit.byId('idTipoEmpresaSelect').get('item').label){
				dojo.byId('idTipoEmpresa').value = dijit.byId('idTipoEmpresaSelect').get('item').label[0];
			}
			if (dijit.byId('idActividadEconomicaSelect').get('item') && dijit.byId('idActividadEconomicaSelect').get('item').label){
				dojo.byId('idActividadEconomica').value = dijit.byId('idActividadEconomicaSelect').get('item').label[0];
			}		
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
		
		<%if(formCompany.getIdTipoUsuario()==Constantes.TIPO_USUARIO.EMPRESA.getIdTipoUsuario()){
			out.println("var vCorreoElectronico = getAnyElementValueById('correoElectronico');");
			out.println("var oCorreoElectronico = getAnyElementValueById('hiddenPreCorreoElectronico');");
			out.println("var vContacto = getAnyElementValueById('contactoEmpresa');");
			out.println("var oContacto = getAnyElementValueById('hiddenPreContacto');");
			out.println("var vIdTipoTelefono = getRadioValue('idTipoTelefono');");
			out.println("var oIdTipoTelefono = getAnyElementValueById('hiddenPreIdTipoTelefono');");
			out.println("var vAcceso = getAnyElementValueById('acceso');");
			out.println("var oAcceso = getAnyElementValueById('hiddenPreAcceso');");
			out.println("var vClave = getAnyElementValueById('clave');");
			out.println("var oClave = getAnyElementValueById('hiddenPreClave');");
			out.println("var vTelefono = getAnyElementValueById('telefono');");
			out.println("var oTelefono = getAnyElementValueById('hiddenPreTelefono');");
			out.println("var vExtension = getAnyElementValueById('extension');");
			out.println("var oExtension = getAnyElementValueById('hiddenPreExtension');");
			out.println("var vDescripcion = getAnyElementValueById('descripcion');");
			out.println("var oDescripcion = getAnyElementValueById('hiddenPreDescripcion');");
			
			out.println("if(vCorreoElectronico!=oCorreoElectronico){");
			out.println("	vCamposRelev = vCamposRelev + ' Correo electrónico ';");
			out.println("	var vEmailConfirm = emailConfirmation();");
			out.println("	if(vEmailConfirm==true){");					
			out.println("		document.ComUpdPreCompanyForm.submit();"); 
			out.println("	} else { dojo.byId('btnGuardar').disabled=false;  }");
			out.println("}  else { ");
			out.println("      if(vContacto!=oContacto){");
			out.println("	       vCamposRelev = vCamposRelev + ' Nombre de la persona de contacto con la empresa.<br/>';");
			out.println("	   } if(vIdTipoTelefono!=oIdTipoTelefono){");
			out.println("	       vCamposRelev = vCamposRelev + ' Tipo de teléfono principal ';");					
			out.println("      } if(vAcceso!=oAcceso){");
			out.println("		   vCamposRelev = vCamposRelev + ' Acceso del teléfono principal ';");
			out.println("	   } if(vClave!=oClave){");
			out.println("      	   vCamposRelev = vCamposRelev + ' Clave Lada del teléfono principal ';");
			out.println("      } if(vTelefono!=oTelefono){");
			out.println("      	   vCamposRelev = vCamposRelev + ' Teléfono principal ';");
			out.println("      } if(vExtension!=oExtension){");	
			out.println("      	   vCamposRelev = vCamposRelev + ' Extensión del teléfono principal ';");
			out.println("      } if(vDescripcion!=oDescripcion) {");
			out.println("      	   vCamposRelev = vCamposRelev + ' Descripción de la empresa ';");
			out.println("      } ");				
			out.println("      var vCamposLen = vCamposRelev.length;");
			out.println("      if(vCamposLen>0){");
			out.println("	       var vContactConfirm = contactFieldsConfirmation(vCamposRelev);");
			out.println("      	   if(vContactConfirm==true){");
			out.println("          	    document.ComUpdPreCompanyForm.submit();");
			out.println("          } else { dojo.byId('btnGuardar').disabled=false; }");
			out.println("      } else {");
			out.println("      	   document.ComUpdPreCompanyForm.submit();");				
			out.println("      }");
			out.println("}");
		} else {
			out.println("document.ComUpdPreCompanyForm.submit();");	
			//out.println("vForm.submit();");			
		}%>
		
	} else {		
		var chkVal = getRadioValue('idTipoPersona');
		var vForm = true;
		
		if(chkVal==null){ 
			vCampos = vCampos + '\n Tipo de persona';
		} else {
			if(chkVal==<%=Constantes.TIPO_PERSONA.PERSONA_FISICA.getIdTipoPersona()%>){
				var nomb = dijit.byId('nombre').value;
				var ap1 = dijit.byId('apellido1').value;
				var fechaNac = dijit.byId('fechaNacimiento').value;
				if(!nomb){
					vCampos = vCampos + ' Nombre.<br/>';	
					dijit.byId('nombre').focus();
				}
				if(!ap1){
					vCampos = vCampos + ' Primer apellido.<br/>';	
					dijit.byId('apellido1').focus();
				}			
				if(!fechaNac){
					vCampos = vCampos + ' Fecha de nacimiento.<br/>';
					dijit.byId('fechaNacimiento').focus();	
				}						
			} else {
				var razon = dijit.byId('razonSocial').value;
				var fechaAct = dijit.byId('fechaActa').value;	
				if(!razon){
					vCampos = vCampos + ' Razón social.<br/>';	
					dijit.byId('razonSocial').focus();
				}
				if(!fechaAct){
					vForm = false;	
					vCampos = vCampos + ' Fecha de acta constitutiva.<br/>';	
					dijit.byId('fechaActa').focus();
				}		
			}			
		}

		var vDescripcion = document.getElementById('descripcion').value;
		if(!vDescripcion){
			vCampos = vCampos + ' Descripción de la empresa.<br/>';
			document.getElementById('descripcion').focus();
		}
		var vContactoEmpresa = dijit.byId('contactoEmpresa').value;
		if(!vContactoEmpresa){
			vCampos = vCampos + ' Nombre de la persona de contacto con la empresa.<br/>';
			dijit.byId('contactoEmpresa').focus();
		}
		
		var vTipoEmpresa = getAnyElementValueById('idTipoEmpresaSelect').value;
		if(!vTipoEmpresa){
			vCampos = vCampos + ' Tipo de empresa.<br/>';
			dijit.byId('idTipoEmpresaSelect').focus();
		}		

		var vTipoActividad = getAnyElementValueById('idActividadEconomicaSelect').value;
		if(!vTipoActividad){
			vCampos = vCampos + ' Actividad económica principal.<br/>';
			dijit.byId('idActividadEconomicaSelect').focus();
		}		

		var vMedio = getAnyElementValueById('idMedioSelect').value;
		if(!vMedio){
			vCampos = vCampos + ' ¿Cómo se enteró del portal del empleo?.<br/>';
			dijit.byId('idMedioSelect').focus();
		}		

		var vNumeroEmpleados = getAnyElementValueById('numeroEmpleados').value;
		if(!vNumeroEmpleados){
			vCampos = vCampos + ' Número de Empleados.<br/>';
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
		
		var vCorreoElectronico = getAnyElementValueById('correoElectronico');
		if(!vCorreoElectronico){
			vCampos = vCampos + ' Correo electrónico.<br/>';
			document.getElementById('correoElectronico').focus();
		}		
		var vConfirmarCorreoElectronico = getAnyElementValueById('confirmarCorreoElectronico');	
		if(!vConfirmarCorreoElectronico){
			vCampos = vCampos + ' Confirmar correo electrónico.<br/>';
			document.getElementById('confirmarCorreoElectronico').focus();
		}		

		var vIdTipoTelefono = getRadioValue('idTipoTelefono');	
		if(null==vIdTipoTelefono){
			vCampos = vCampos + ' Tipo de telefono principal.<br/>';
			document.getElementById('idTipoTelefono').focus();
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
				
		dojo.byId('message').innerHTML = '';
		dojo.byId('message').innerHTML = 'Los siguientes campos muestran datos inválidos - Se muestra(n) el (los) campo(s) con inconsistencias: <br/>' + vCampos;
		dojo.byId('btnGuardar').disabled=false;
	}	
}
 
//funcion para obtener el valor de un campo 
function getAnyElementValueById(elementId){
	var vElement;
	if(dijit.byId(elementId)){
		vElement = dijit.byId(elementId).value;
		if(vElement==undefined || vElement==''){
			vElement = document.getElementById(elementId).value;
		}
	} else {
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

	function showConf() {
		var vStyle = document.getElementById('confCorreo').style.visibility;
		var hideDiv = vStyle == 'hidden' ? true : false;
		setDiv('confCorreo', hideDiv);
		if (hideDiv) {
			document.getElementById('correoElectronico').readOnly = false;
		} else {
			document.getElementById('correoElectronico').readOnly = true;
			document.getElementById('correoElectronico').value = 
			document.getElementById('hiddenPreCorreoElectronico').value;
		}
		document.getElementById('confirmarCorreoElectronico').value = '';
	}	
	
	function setDiv(id, visible) {
		var vStyle = visible ? 'visible':'hidden';
		document.getElementById(id).style.visibility = vStyle;
	}	

function disableMousePasting(e) {
    if (event.button == 2 || event.button == 3) {
        alert('Por favor, no copie la información en este campo.');        
        return false;
    }
    return true;
}

function validarFormatoCorreo(correo) {
	var regExp = /^[a-zA-Z0-9_-]+(?:\.[a-zA-Z0-9_-]+)*@(?:[a-zA-Z0-9](?:[a-zA-Z0-9-_]*[a-zA-Z0-9-])?\.)+[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?$/;
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
	var chkVal = getRadioValue('idTipoPersona');
	var vDescripcion = document.getElementById('descripcion');
	var vCorreoElectronico = getAnyElementValueById('correoElectronico');
	var vConfirmarCorreoElectronico = getAnyElementValueById('confirmarCorreoElectronico');	
	var vTipoTelefono = getRadioValue('idTipoTelefono'); 	
	var vTelefono = dijit.byId('telefono');
   	
	if(vCorreoElectronico!=vConfirmarCorreoElectronico){
		vForm = false;
		vMessage = vMessage + '\nEl Correo electrónico y Confirmar correo electrónico deben ser iguales.';
	}
		
	if(!vDescripcion.value){
		vForm = false;
		vMessage = vMessage + '\nDebe proporcionar la Descripción de la empresa.';	
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
	
//funcion para cambiar los campos requeridos dependiendo del tipo de persona
function changePersonTypeRequired(){
	var chkVal = getRadioValue('idTipoPersona');	
	if(chkVal==<%=Constantes.TIPO_PERSONA.PERSONA_FISICA.getIdTipoPersona()%>){
		dijit.byId('nombre').setDisabled(false);
		dijit.byId('apellido1').setDisabled(false);
		dijit.byId('apellido2').setDisabled(false);
		dijit.byId('fechaNacimiento').setDisabled(false);
		dijit.byId('razonSocial').setDisabled(true);
		dijit.byId('fechaActa').setDisabled(true);	
	} else if(chkVal==<%=Constantes.TIPO_PERSONA.PERSONA_MORAL.getIdTipoPersona()%>) {
		dijit.byId('nombre').setDisabled(true);
		dijit.byId('apellido1').setDisabled(true);
		dijit.byId('apellido2').setDisabled(true);
		dijit.byId('fechaNacimiento').setDisabled(true);
		dijit.byId('razonSocial').setDisabled(false);
		dijit.byId('fechaActa').setDisabled(false);
	}
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

		
				<form name="ComUpdPreCompanyForm" id="ComUpdPreCompanyForm" method="post" action="comupdprecompany.do" dojoType="dijit.form.Form">
					<input type="hidden" name="method" id="method" value="init"/>
					<input type="hidden" name="Ctrl" id="Ctrl" dojoType="dijit.form.TextBox" value="1"/>
					<input type="hidden" name="cpnuevo" id="cpnuevo" dojoType="dijit.form.TextBox" value="1"/>
					<input type="hidden" name="idEmpresa" id="idEmpresa" value="${ComUpdPreCompanyForm.idEmpresa}"/>
					<input type="hidden" name="hiddenDesTipoEmpresa" id="hiddenDesTipoEmpresa" value="${ComUpdPreCompanyForm.hiddenDesTipoEmpresa}"/>
					<input type="hidden" name="hiddenDesActividadEconomica" id="hiddenDesActividadEconomica" value="${ComUpdPreCompanyForm.hiddenDesActividadEconomica}"/>
					<input type="hidden" name="hiddenDesEntidad" id="hiddenDesEntidad" value="${ComUpdPreCompanyForm.hiddenDesEntidad}"/>
					<input type="hidden" name="hiddenDesMunicipio" id="hiddenDesMunicipio" value="${ComUpdPreCompanyForm.hiddenDesMunicipio}"/>
					<input type="hidden" name="hiddenDesColonia" id="hiddenDesColonia" value="${ComUpdPreCompanyForm.hiddenDesColonia}"/>
					<input type="hidden" name="hiddenCodigoPostal" id="hiddenCodigoPostal" value="${ComUpdPreCompanyForm.hiddenCodigoPostal}"/>
										
					<input type="hidden" name="hiddenPreCorreoElectronico" id="hiddenPreCorreoElectronico" value="${ComUpdPreCompanyForm.preCorreoElectronico}"/>
					<input type="hidden" name="hiddenPreContacto" id="hiddenPreContacto" value="${ComUpdPreCompanyForm.preContactoEmpresa}"/>
					<input type="hidden" name="hiddenPreIdTipoTelefono" id="hiddenPreIdTipoTelefono" value="${ComUpdPreCompanyForm.preIdTipoTelefono}"/>
					<input type="hidden" name="hiddenPreAcceso" id="hiddenPreAcceso" value="${ComUpdPreCompanyForm.preAcceso}"/>
					<input type="hidden" name="hiddenPreClave" id="hiddenPreClave" value="${ComUpdPreCompanyForm.preClave}"/>
					<input type="hidden" name="hiddenPreTelefono" id="hiddenPreTelefono" value="${ComUpdPreCompanyForm.preTelefono}"/>
					<input type="hidden" name="hiddenPreExtension" id="hiddenPreExtension" value="${ComUpdPreCompanyForm.preExtension}"/>
					<input type="hidden" name="hiddenPreDescripcion" id="hiddenPreDescripcion" value="${ComUpdPreCompanyForm.preDescripcion}"/>
					<input type="hidden" name="acceso" id="acceso" dojoType="dijit.form.TextBox" value="${ComUpdPreCompanyForm.acceso}" />					
					<input type="hidden" name="codigoPostalShow" id="codigoPostalShow" dojoType="dijit.form.TextBox" value="${ComUpdPreCompanyForm.codigoPostal}" />
					
					
              
              	<div class="formulario">
                	<h3>Datos de la empresa</h3>  
                	              	
                  <DIV class="entero">
				  <div id="message"></div>
					<html:messages id="errors" >
						<span class="Font80 red3Font"><bean:write name="errors"/></span><br/>
					</html:messages>
					
					<html:messages id="messages" message="true" >
						<span class="Font80 red3Font"><bean:write name="messages"/></span><br/>
					</html:messages>    				   
                  
                  </DIV>                  	              	
                	              	
                		<div class="entero">
				        <span class="un_cuarto">&nbsp;<br>
				        <label for="rfc"><strong>RFC</strong></label><br/>
				        <input id="rfc" name="rfc" maxlength="13" size="13"
				        	dojoType="dijit.form.ValidationTextBox" required="false" regExp="^[A-Za-z\s\ñÑ0-9]{0,13}$"
				        	value ="${ComUpdPreCompanyForm.rfc}" 
				        	invalidMessage="Dato inválido" trim="true" />        
				        </span>			
				        				        
				        <span class="un_cuarto">
				        <label><strong>Tipo de persona*</strong></label><br/>
				        <%=strDesTipoPersona%>
				        </span>                  		
                		</div>
		                <%		
						if(CLAVE_TIPO_PERSONA_FISICA.equalsIgnoreCase(idTipo)){
						%>
	                		<div class="entero">
		       			    <span class="un_cuarto">
					        <label><strong>Nombre(s)*</strong></label><br/>
					        <c:out value="${ComUpdPreCompanyForm.nombre}"/>
		       			    </span>
		       			    <span class="un_cuarto">
					        <label><strong>Primer apellido*</strong></label><br/>
					        <c:out value="${ComUpdPreCompanyForm.apellido1}"/>
		       			    </span>
		       			    <span class="un_cuarto">
					        <label><strong>Segundo apellido</strong></label><br/>
					        <c:out value="${ComUpdPreCompanyForm.apellido2}"/>
		       			    </span>
					        <span class="un_cuarto">
					        <label><strong>Fecha de nacimiento*</strong></label><br/>
					        <%=strFechaNacimiento%>
							</span>               			    
							</div>						
						<%
						} else {
						%>
							<div class="entero">
		       			    <span class="un_cuarto">
					        <label><strong>Razón social*</strong></label><br/>
					        <c:out value="${ComUpdPreCompanyForm.razonSocial}"/>
							</span>        
							<span class="un_cuarto">
					        <label><strong>Fecha de acta constitutiva*</strong></label><br/>
					        <%=strFechaActa%>
							</span>        
							</div>						
						<%
						}                      		
		                %>	
		                <%-- COMENTAR EN PRODUCCION  --%>
		                 <!-- Se agrego Nombre Comercial* -->
					        <div class="entero">
						        <span class="un_cuarto">
							        <strong><label for="nombreComercial">Nombre Comercial</label></strong><br/>
							        <input id="nombreComercial" name="nombreComercial" maxlength="150" size="50" 			
							        	dojoType="dijit.form.ValidationTextBox" required="false" onpaste="return false;" regExp="^[A-Za-z\s\d\-.áéíóúÁÉÍÓÚñÑ/']{1,150}$"  uppercase="true"			        	
							        	value="${ComUpdPreCompanyForm.nombreComercial}"
							        	invalidMessage="Dato inválido" missingMessage="Dato requerido" trim="true"/>
							            			    
								</span>			        
					        </div>
			       		 <!-- Fin Nombre Comercial* -->
						
						<div class="entero">
				        <strong><label for="descripcion">Descripción de la empresa</label>*</strong><br/>
					    <textarea name="descripcion" id="descripcion" maxlength="2000" 
		        	    onkeypress="return caracteresValidos(event);"
		        	    onpaste="return false;"
		        	    regExp="^[a-zA-ZáéíóúÁÉÍÓÚñÑ0-9.,;:#\\s]{1,2000}$" 
		        	    style="width:550px;min-height:120px;_height:200px;" 
		        	    required="true">${ComUpdPreCompanyForm.descripcion}<%//=formCompany.getDescripcion()%></textarea>
		        	    <script type="text/javascript">
		         		 var vSpellCon = new GoogieSpell("googiespell/", '<%=vProxy%>');
		         		 vSpellCon.setLanguages({'es': 'Español'});
		         		 vSpellCon.hideLangWindow();
		         		 vSpellCon.decorateTextarea("descripcion");
					    </script>					          	
               			</div>
               			
               			<div class="entero">
	       			    <span class="un_medio">
				        <strong><label for="contactoEmpresa">Nombre de la persona de contacto con la empresa</label>*</strong><br/>
				        	<input id="contactoEmpresa" name="contactoEmpresa"  maxlength="150" size="50"         	
				        	dojoType="dijit.form.ValidationTextBox" required="true" regExp="^[\D\s\.áéíóúÁÉÍÓÚñÑ]{1,150}$" 
				        	value="${ComUpdPreCompanyForm.contactoEmpresa}" 
				        	invalidMessage="Dato inválido" missingMessage="Dato requerido" trim="true" />
				        </span>
						<span class="un_medio">
			        	<strong><label for="idTipoEmpresaSelect">Tipo de empresa</label>*</strong><br/>   
						<input type="hidden" name="idTipoEmpresa" id="idTipoEmpresa" value="${ComUpdPreCompanyForm.idTipoEmpresa}" />
						<% 
						if(formCompany.getIdTipoUsuario()==(long)Constantes.TIPO_USUARIO.ADMINISTRADOR.getIdTipoUsuario()){%>
							<div dojoType="dojo.data.ItemFileReadStore" jsId="tiposEmpresaStore"  url="${context}comupdprecompany.do?method=tiposEmpresa"></div>
							<select dojotype="dijit.form.ComboBox" store="tiposEmpresaStore" id="idTipoEmpresaSelect" required="true"></select>
			        	<%} else {%>
			        		<c:out value="${ComUpdPreCompanyForm.hiddenDesTipoEmpresa}"/>
			        	<%}%>
	       			    </span>		
	       			    </div>
	       			    
	       			    <div class="entero">
	       			    <span class="un_medio">   			    
			        	<strong><label for="idActividadEconomicaSelect">Actividad económica principal</label>*</strong><br/> 
			        	<input type="hidden" name="idActividadEconomica" id="idActividadEconomica" value="${ComUpdPreCompanyForm.idActividadEconomica}" />
			        	<%
			        	if(formCompany.getIdTipoUsuario()==(long)Constantes.TIPO_USUARIO.ADMINISTRADOR.getIdTipoUsuario()){%>
							<div dojoType="dojo.data.ItemFileReadStore" jsId="tiposActividadEconomicaStore"  url="${context}comupdprecompany.do?method=tiposActividadEconomica"></div>
							<select dojotype="dijit.form.ComboBox" store="tiposActividadEconomicaStore" id="idActividadEconomicaSelect" required="true" style="width:100%"></select>
			        	<%} else {%>
			        		<c:out value="${ComUpdPreCompanyForm.hiddenDesActividadEconomica}"/>
			        	<%}%>							
						</span>
						</div>
						<div class="entero">							  
						<span class="un_medio">
				        <strong><label for="numeroEmpleados">Número de empleados</label>*</strong><br/>
				        <input id="numeroEmpleados" name="numeroEmpleados" maxlength="5" size="5"         	
				        	dojoType="dijit.form.ValidationTextBox" required="true" regExp="^[0-9]{1,5}$"
				        	value="${ComUpdPreCompanyForm.numeroEmpleados}"
				        	invalidMessage="Dato inválido" missingMessage="Dato requerido" trim="true" />
				                     			           			    					
						</span>						     			 				        				       
               		    </div>
               		               		
               			<div class="entero">												
	       			    <span class="un_medio">   			    
			        	<label><strong>¿Cómo se enteró del portal del empleo?*</strong></label><br/>
			        	<%
						List<CatalogoOpcionVO> lstTipoMedio = (List<CatalogoOpcionVO>) request.getSession().getAttribute("CAT_MEDIO_ENTERADO");					
						Iterator itLstTipoMedio = lstTipoMedio.iterator();
						while(itLstTipoMedio.hasNext()){
							CatalogoOpcionVO vo = (CatalogoOpcionVO) itLstTipoMedio.next();
							if(vo.getIdCatalogoOpcion()== formCompany.getIdMedio()){
								out.println(vo.getOpcion());
								break;
							}
						}			        		
						 %>  
						</span>		
						<span class="un_medio">
						<label for="confidencial"><strong>Deseo que mis datos permanezcan confidenciales</strong></label><br/>
						<%  if(formCompany.getConfidencial() == Constantes.DATOS_CONFIDENCIALES){
							%>
							<input id="confidencial" name="confidencial" checked type="checkbox" value="1" />
							<%
							} else {
							%>
							<input id="confidencial" name="confidencial" type="checkbox" value="1" />
							<%							
							} 
						%>						
						</span>                   														   		        
               			</div>
               			
               		
               			<!-- DOMICILIO -->
               			<h3>Domicilio</h3>
               			<div class="entero">
	       			    <span class="un_tercio">
	      			    <strong><label for="codigoPostal">Código postal</label>*</strong><br/>
	      			    <!--ComUpdPreCompanyForm.codigoPostal  domicilioForm.codigoPostal-->	      			    
				        <input id="codigoPostal" name="codigoPostal" maxlength="5" size="5" value="${ComUpdPreCompanyForm.codigoPostal}"
				        	dojoType="dijit.form.ValidationTextBox" required="true" regExp="^[0-9]{1,5}" 
				        	invalidMessage="Dato inválido" trim="true" onChange="clrAdrFlds()"  />       	      			    				        	 	       			   		
	       			    </span>	
	       			    <span class="un_tercio">
						<input type="checkBox" id="checkCtrl">
						<label for="checkCtrl"><strong>No conozco mi código postal</strong></label><br/>      	       			        			   
	       			    </span>	
	       			    <span class="un_tercio">
	       			    <input type="button" name="btnValidar" id="btnValidar" class="boton" value="Validar CP">
	       			    <!-- <div id="btnValidar" dojoType="dijit.form.Button" class="boton">Validar CP</div> -->	       			    
	       			    </span>
               			</div>
               			
               			<div class="entero">
				        <span class="un_tercio"><label for="pais"><strong>País</strong></label><br/>
				        <input name="pais" id="pais" maxlength="10" size="6" value="México" disabled />			        			        
				        </span>
				        </div>
               			
               			<div class="entero">
				        <span class="un_tercio">      
						<strong><label for="idEntidadSelect">Entidad federativa</label>*</strong><br/>
						<input type="hidden" name="idEntidad" id="idEntidad" value="" />
						<div dojoType="dojo.data.ItemFileReadStore" jsId="entidadFederativaStore" urlPreventCache="true" clearOnClose="true" ></div>
						<select dojotype="dijit.form.FilteringSelect" store="entidadFederativaStore" id="idEntidadSelect" required="true" disabled="disabled" autoComplete="false"></select>
				        </span>
				        
				        <span class="un_tercio">
				        <strong><label for="idMunicipioSelect">Municipio</label>*</strong><br/>
						<input type="hidden" name="idMunicipio" id="idMunicipio" value="" />
						<div dojoType="dojo.data.ItemFileReadStore" jsId="municipioStore" urlPreventCache="true" clearOnClose="true" ></div>
						<select dojotype="dijit.form.FilteringSelect" store="municipioStore" id="idMunicipioSelect" required="true" disabled="disabled" autoComplete="false"></select>				        
				        </span>
				        
				        <span class="un_tercio">
				        <strong><label for="idColoniaSelect">Colonia</label>*</strong><br/>
						<input type="hidden" name="idColonia" id="idColonia" value="" />
						<div dojoType="dojo.data.ItemFileReadStore" jsId="coloniaStore" urlPreventCache="true" clearOnClose="true" ></div>
						<select dojotype="dijit.form.FilteringSelect" store="coloniaStore" id="idColoniaSelect" required="true" disabled="disabled" autoComplete="true"></select>
				        </span>
				        </div>

               			<div class="entero">
				        <span class="un_tercio"> 
				        <strong><label for="calle">Calle</label>*</strong><br/>
				        <input id="calle" name="calle" maxlength="150" size="60" value="${ComUpdPreCompanyForm.calle}" 
				        	dojoType="dijit.form.ValidationTextBox" required="true" regExp="^[a-zA-ZáéíóúÁÉÍÓÚñÑ0-9\s]{1,150}$" uppercase="true"
				        	invalidMessage="Dato inválido" missingMessage="Dato requerido" trim="true"/>
				        </span> 
				        
				        <span class="un_tercio"> 
				        <strong><label for="numeroExterior">Número exterior</label>*</strong><br/> 
				        <input id="numeroExterior" name="numeroExterior" maxlength="50" size="4" value="${ComUpdPreCompanyForm.numeroExterior}"
				        	dojoType="dijit.form.ValidationTextBox" required="true" regExp="^[a-zA-ZáéíóúÁÉÍÓÚñÑ0-9\s]{1,50}" 
				        	invalidMessage="Dato inválido" trim="true" />        			            			   
				        </span> 
				        
				        <span class="un_tercio"> <label for="numeroInterior"><strong>Número interior</strong></label><br/>
				        <input id="numeroInterior" name="numeroInterior" maxlength="50" size="4" value="${ComUpdPreCompanyForm.numeroInterior}"
				        	dojoType="dijit.form.ValidationTextBox" required="false" regExp="^[a-zA-ZáéíóúÁÉÍÓÚñÑ0-9\s]{1,50}" 
				        	invalidMessage="Dato inválido" trim="true" />        				             			   
				        </span> 			        
               			</div>
               			
               			<div class="entero">
				        <span class="un_tercio"><strong><label for="entreCalle">Entre calles</label>*</strong><br/> 
				        <input id="entreCalle" name="entreCalle" maxlength="150" size="60" value="${ComUpdPreCompanyForm.entreCalle}" 
				        	dojoType="dijit.form.ValidationTextBox" required="true" regExp="^[a-zA-ZáéíóúÁÉÍÓÚñÑ0-9\s]{1,150}$" uppercase="true"
				        	invalidMessage="Dato inválido" missingMessage="Dato requerido" trim="true"/>		            			   
				        </span> 		
				        	      
				        <span class="un_tercio"><strong><label for="yCalle">Y</label>*</strong><br/>
				        <input id="yCalle" name="yCalle" maxlength="150" size="60" value="${ComUpdPreCompanyForm.yCalle}" 
				        	dojoType="dijit.form.ValidationTextBox" required="false" regExp="^[a-zA-ZáéíóúÁÉÍÓÚñÑ0-9\s]{1,150}$" uppercase="true"
				        	invalidMessage="Dato inválido" trim="true"/>
				        </span> 			        
               			</div>               			
               			<!-- TERMINA DOMICILIO -->
               		
               			<h3>Datos de identificación de la cuenta</h3>
               			<p class="entero">  
				        <strong><label for="correoElectronico">Correo electrónico</label>*</strong><br/>
						<input type="text" id="correoElectronico" name="correoElectronico" maxlength="65" size="50" trim="true" 		       
						       value="${ComUpdPreCompanyForm.correoElectronico}" onchange="validarFormatoCorreo(this);" oncopy="return false;" 
                   	     oncut="return false" onpaste="return false;" readonly="true" />  			             			   							        	
			            </p>
			            <DIV id="confCorreo" class="entero" style="visibility:hidden;">  
				        <strong><label for="confirmarCorreoElectronico">Confirmar correo electrónico</label>*</strong><br/>
						<input type="text" id="confirmarCorreoElectronico" name="confirmarCorreoElectronico" maxlength="65" size="50" trim="true"
						       value="${ComUpdPreCompanyForm.correoElectronico}" onchange="validarFormatoCorreo(this);" oncopy="return false;" oncut="return false" 
                               onpaste="return false;" ondragover="return false;" />                			          			   	
				        </div>		  
					  <p class="entero">
	                   	<a href="javascript:showConf();" class="un_cuarto links">Actualizar correo electr&oacute;nico</a>
	                   </p>				              			          			                			          
               			
               			<!-- TELEFONO  -->            			
               			<div ><!-- class="division"  -->             		
               			<span class="entero"><strong>Teléfono</strong></span> 	
				        <span class="un_tercio"><strong><label for="idTipoTelefono">Tipo de teléfono</label>*</strong><br/>
						<%
						long selectedPhoneType = (long)formCompany.getIdTipoTelefono();
						List<CatalogoOpcionVO> lstTipoTelefono = (List<CatalogoOpcionVO>) request.getSession().getAttribute("CAT_TIPO_TELEFONO");					
						Iterator itLstTipoTelefono = lstTipoTelefono.iterator();
						while(itLstTipoTelefono.hasNext()){
							CatalogoOpcionVO vo = (CatalogoOpcionVO) itLstTipoTelefono.next();
							
							if(vo.getIdCatalogoOpcion()==formCompany.getIdTipoTelefono() || vo.getIdCatalogoOpcion()==selectedPhoneType){
							%>
							<%=vo.getOpcion()%>&nbsp;
							<input type="radio" id="idTipoTelefono" name="idTipoTelefono" checked
							value="<%=String.valueOf(vo.getIdCatalogoOpcion())%>" onClick="fillUpAccessKey();">&nbsp;&nbsp;
							<%											
							} else {
							%>
							<%=vo.getOpcion()%>&nbsp;
							<input type="radio" id="idTipoTelefono" name="idTipoTelefono" 
							value="<%=String.valueOf(vo.getIdCatalogoOpcion())%>" onClick="fillUpAccessKey();">&nbsp;&nbsp;
							<%											
							}
						}							
						%>			          
				        </span> 	               			               			
				        <span class="un_tercio"><strong><label for="accesoShow">Acceso</label>*</strong><br/>
				        <!-- regExp="^(<%//=Constantes.CLAVE_TELEFONO_CELULAR%>|<%//=Constantes.CLAVE_TELEFONO_FIJO%>)$" -->
						<input id="accesoShow" name="accesoShow" maxlength="3" size="3"
						 	dojoType="dijit.form.ValidationTextBox" required="false" 
						 	regExp="^(<%=Constantes.CLAVE_TELEFONO_CELULAR%>|<%=Constantes.CLAVE_TELEFONO_FIJO%>)$"
						 	value="${ComUpdPreCompanyForm.acceso}"
						 	invalidMessage="Dato inválido" missingMessage="Dato requerido" trim="true" />      			        
				        </span> 			 
				        <span class="un_tercio"><strong><label for="clave">Clave Lada</label>*</strong><br/>  
						<input id="clave" name="clave" maxlength="3" size="3"
						 	dojoType="dijit.form.ValidationTextBox" required="true" regExp="^[0-9]{2,3}$"
						 	value="${ComUpdPreCompanyForm.clave}"
						 	invalidMessage="Dato inválido" missingMessage="Dato requerido" trim="true" />	        			        
				        </span> 			 
				        <span class="un_tercio"><strong><label for="telefono">Teléfono</label>*</strong><br/>
						<input id="telefono" name="telefono" maxlength="8" size="8" onBlur="changePhoneSizeRequired()"
						 	dojoType="dijit.form.ValidationTextBox" required="true" regExp="^[0-9]{7,8}$"	
						 	value="${ComUpdPreCompanyForm.telefono}"		 	
						 	invalidMessage="Dato inválido" missingMessage="Dato requerido" trim="true" />      			          
				        </span> 			 
				        <span class="un_tercio"><label for="extension"><strong>Extensión</strong></label><br/>
						<input id="extension" name="extension" maxlength="8" size="8"
						 	dojoType="dijit.form.ValidationTextBox" required="false" regExp="^[0-9]{1,6}$"
						 	value="${ComUpdPreCompanyForm.extension}"
						 	invalidMessage="Dato inválido" missingMessage="Dato requerido" trim="true" /> 
						</span> 	       			           
	                    <span class="un_tercio"><a href="#" class="links" onclick="openPhoneWindow()">Agregar teléfono</a></span>	                    
	                    </div>
               			<!-- TERMINA TELEFONO -->
               			
               			<div class="entero">
						<span class="un_medio">
				        <label for="paginaWeb"><strong>Página Web</strong></label><br/>
				        <input type="text" id="paginaWeb" name="paginaWeb" value="${ComUpdPreCompanyForm.paginaWeb}">
				        </span>	
				        </div>		
				        					        	                		
						<DIV class="entero" id="divRegis" style="clear:both;">         			    				   
	                 	<span class="un_medio" style="text-align:center; margin:40px 0 0 0; ">                                        
						<input type="button" id="btnGuardar" value="Guardar" class="boton" onclick="doSubmitAjax('actualizar');" />
						</span>
						<span class="un_medio" style="text-align:center; margin:40px 0 0 0; ">
						<input type="button" id="btnCancel" value="Cancelar" class="boton" onclick="cancelConfirmation();" />
						</span>
	                	</DIV>                		
                		
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
        //PARA UPDATE
		var vCodigoPostal = dojo.byId('codigoPostal').value;
				
		dijit.byId('idEntidadSelect').disabled=false;
		dijit.byId('idMunicipioSelect').disabled=false;
		dijit.byId('idColoniaSelect').disabled=false;

    	entidadFederativaStore.url = "${context}comupdprecompany.do?method=obtenerEntidadJSON" + "&" + "codigoPostal="+ vCodigoPostal;
    	entidadFederativaStore.close();
		
    	entidadFederativaStore.fetch({
          	onComplete: function(items, request) {                  	
          		if (items.length == 0) return;                    	
          		dijit.byId('idEntidadSelect').attr('value', items[0].value);
          	}
    	});

    	municipioStore.url = "${context}comupdprecompany.do?method=obtenerMunicipioJSON" + "&" + "codigoPostal="+ vCodigoPostal;
    	municipioStore.close();

    	municipioStore.fetch({
          	onComplete: function(items, request) {                  	
          		if (items.length == 0) return;                    	
          		dijit.byId('idMunicipioSelect').attr('value', items[0].value);
          	}
    	});

    	coloniaStore.url = "${context}comupdprecompany.do?method=obtenerColoniaJSON" + "&" + "codigoPostal="+ vCodigoPostal;
    	coloniaStore.close();

    	coloniaStore.fetch({
          	onComplete: function(items, request) {                  	
          		if (items.length == 0) return;                    	
          		dijit.byId('idColoniaSelect').attr('value', items[0].value);
          	}
    	});
    	
    	//TERMINA PARA UPDATE
        	
        dojo.connect(document.ComUpdPreCompanyForm.btnValidar, 'onclick', function() {

 			if(dijit.byId('codigoPostal').get('value') == '') {
 			   alert("El código postal es necesario.");
 			   return;
			}
 			if(dijit.byId('codigoPostal').value.length <5){
  			   alert("El código postal debe tener 5 dígitos.");
  			   return;
 			}		
 			

            if(dijit.byId('Ctrl').value == 1){

				var vCodigoPostal = dijit.byId('codigoPostal').get('value');
				var vMsjNotF = 0;
				document.ComUpdPreCompanyForm.method.value = 'obtenerEntidadJSON';
	
				dijit.byId('idEntidadSelect').disabled=false;
				dijit.byId('idMunicipioSelect').disabled=false;
				dijit.byId('idColoniaSelect').disabled=false;

            	entidadFederativaStore.url = "${context}comupdprecompany.do?method=obtenerEntidadJSON" + "&" + "codigoPostal="+ vCodigoPostal;
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
            	            	
            	municipioStore.url = "${context}comupdprecompany.do?method=obtenerMunicipioJSON" + "&" + "codigoPostal="+ vCodigoPostal;
            	municipioStore.close();

            	municipioStore.fetch({
                  	onComplete: function(items, request) {                  	
                  		if (items.length == 0) return;                    	
                  		dijit.byId('idMunicipioSelect').attr('value', items[0].value);
                  	}
            	});

            	coloniaStore.url = "${context}comupdprecompany.do?method=obtenerColoniaJSON" + "&" + "codigoPostal="+ vCodigoPostal;
            	coloniaStore.close();

            	coloniaStore.fetch({
                  	onComplete: function(items, request) {                  	
                  		if (items.length == 0) return;                    	
                  		dijit.byId('idColoniaSelect').attr('value', items[0].value);
                  	}
            	});   
            	
				if(vMsjNotF>0){
					dojo.byId('codigoPostalShow').value = dijit.byId("codigoPostal").value;
					dijit.byId("codigoPostal").setAttribute('disabled', true);						
				} else {
					dijit.byId("codigoPostal").setAttribute('disabled', false);
				}
				
			}
			
        });

        dojo.connect(dijit.byId("idEntidadSelect"), "onChange", function() {

            if(dijit.byId('Ctrl').value == 0){

				var vEntidad = dijit.byId('idEntidadSelect').get('value');
					
            	dijit.byId('idMunicipioSelect').disabled=false;
            		
            	municipioStore.url = "${context}comupdprecompany.do?method=obtenerMunicipio" + "&" + "idEntidad="+ vEntidad;
            	municipioStore.close();
            	            	
			}
        });


        dojo.connect(dijit.byId("idMunicipioSelect"), "onChange", function() {

            if(dijit.byId('Ctrl').value == 0){

				var vEntidad   = dijit.byId('idEntidadSelect').get('value');
				var vMunicipio = dijit.byId('idMunicipioSelect').get('value');
					
            	//dijit.byId('coloniaSelect').disabled=false;
            		
            	coloniaStore.url = "${context}comupdprecompany.do?method=obtenerColonia" + "&" + "idEntidad="+ vEntidad + "&" + "idMunicipio="+ vMunicipio;
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
				  url: 'comupdprecompany.do',
				  form:'ComUpdPreCompanyForm',
				  timeout:180000, 
				  load: function(data)
				  {
					var res = dojo.fromJson(data);
					dojo.byId('codigoPostal').value = res.codigoPostal;
					dojo.byId('codigoPostalShow').value = res.codigoPostal;
					dijit.byId("codigoPostal").setAttribute('disabled', true);
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
            		
            		entidadFederativaStore.url = "${context}comupdprecompany.do?method=obtenerEntidad";
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
					dijit.byId("idMunicipioSelect").setAttribute('disabled', true);
					dijit.byId("idColoniaSelect").setAttribute('disabled', true);
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