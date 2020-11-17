<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="mx.gob.stps.portal.persistencia.vo.CatalogoOpcionVO"%>
<%@ page import="mx.gob.stps.portal.core.infra.utils.Constantes"%>
<%@ page import="mx.gob.stps.portal.web.company.form.ComUpdCompanyForm"%>
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
String TELEFONO_CELULAR_ID = String.valueOf(request.getSession().getAttribute("TELEFONO_CELULAR_ID"));
String TELEFONO_CELULAR_DES = String.valueOf(request.getSession().getAttribute("TELEFONO_CELULAR_DES"));
String TELEFONO_FIJO_ID = String.valueOf(request.getSession().getAttribute("TELEFONO_FIJO_ID"));
String TELEFONO_FIJO_DES = String.valueOf(request.getSession().getAttribute("TELEFONO_FIJO_DES"));

String CLAVE_TELEFONO_FIJO = String.valueOf(request.getSession().getAttribute("CLAVE_TELEFONO_FIJO"));
String CLAVE_TELEFONO_CELULAR = String.valueOf(request.getSession().getAttribute("CLAVE_TELEFONO_CELULAR"));    
String CLAVE_TIPO_PERSONA_FISICA = String.valueOf(request.getSession().getAttribute("CLAVE_TIPO_PERSONA_FISICA"));
String CLAVE_TIPO_PERSONA_MORAL = String.valueOf(request.getSession().getAttribute("CLAVE_TIPO_PERSONA_MORAL"));
String ID_TIPO_USUARIO = String.valueOf(request.getSession().getAttribute("ID_TIPO_USUARIO"));
int ES_TIPO_EMPRESA = Constantes.TIPO_USUARIO.EMPRESA.getIdTipoUsuario();
ComUpdCompanyForm formCompany = (ComUpdCompanyForm)session.getAttribute("ComUpdCompanyForm");
String vProxy = context + "/SpellCheck.do?method=gogiespell&lang=";
%>

                <%
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

<link href='https://fonts.googleapis.com/css?family=Droid+Sans' rel='stylesheet' type='text/css' />

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
	dojo.require("dojo.parser");
</script>

<script type="text/javascript">
	var dialogTestimonio;
	function showWindow(){
		if (!dialogTestimonio){
			dialogTestimonio = new dijit.Dialog({
		        title: 'Comparte tu testimonio',
		        href: '<%=context%>testimonio.do?method=init',
		        style: "width:400px; height:350px;",
		        showTitle: false,
		        draggable : false
		    });
			
		}
		
		dialogTestimonio.show();
	  }
	
	function closeWindow(){
		dialogTestimonio.hide();
	}
</script>
<script type="text/javascript">
	function setMensaje(mensaje){
		dojo.byId('mensaje').innerHTML = mensaje;
		dijit.byId('MensajeAlert').show();		
	}


	//Desplegar ventana para captura de telefonos adicionales
	function openPhoneWindow(){	
	 	var newPhoneWin = window.open("<%=context%>phone.do?method=init", "Telefonos","height=500,width=900,resizable=yes,scrollbars=yes"); 	
	} 	
	//Desplegar ventana para modificar logotipo
	function openLogoWindow(){	
	 	var newLogoWin = window.open('<c:url value="/uploadcompanylogo.do?method=init"/>', "CambiarImagen","height=500,width=900,resizable=yes,scrollbars=yes"); 	
	} 	
	//Desplegar ventana para captura de contactos
	function openContactWindow(){	
	 	var newContactWin = window.open("<%=context%>regCont.do?method=init", "RegistrarContactos","height=500,width=900,resizable=yes,scrollbars=yes"); 	
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
</script>	
 <script type="text/javascript">
	function doSubmit(method){
		//document.ComUpdCompanyForm.method.value=method;
		//document.ComUpdCompanyForm.submit();	
	    var vForm = dijit.byId('ComUpdCompanyForm');
	    document.getElementById('method').value=method;
    	//vForm.submit();		
    	dojo.byId('ComUpdCompanyForm').submit();			
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


	
	function cancelConfirmation() {
		var answer = confirm("Los datos no guardados se perderán ¿Continuar?");
		if (answer){
			doSubmit('cancelar');
		}
	}
	
	function confirmPartner() {
		var answer = confirm("Los datos no guardados se perderán ¿Continuar?");
		if (answer){
			location.replace('<%=context%>comregpartnercompany.do?method=init');
		}
	}
	
	function confirmPartner2() {
		var answer = confirm("Los datos no guardados se perderán ¿Continuar?");
		if (answer){
			location.replace('<%=context%>comregpartnercompanyv2.do?method=init');
		}
	}	
	
	function confirmContact() {
		var answer = confirm("Los datos no guardados se perderán ¿Continuar?");
		if (answer==true){
			location.replace('<%=context%>comregcontact.do?method=init');
		}
	}	
	
	function confirmContact2() {
		var answer = confirm("Los datos no guardados se perderán ¿Continuar?");
		if (answer==true){
			location.replace('<%=context%>comregcontactv2.do?method=init');
		}
	}		

	function emailConfirmation() {
		var answer = confirm('Usted ha modificado su cuenta de correo electrónico, a partir de este momento, su cuenta empresarial no tendrá acceso temporalmente a los servicios que le proporciona el Portal, hasta que sea validada nuevamente. ¿Desea continuar?');
		if (answer==true){
			return true;
		} else {
			return false;
		}
	}
	
	function contactFieldsConfirmation(lstModFields) {
		var answer = confirm('Usted ha modificado la información de: ' + lstModFields + ', a partir de este momento, su cuenta empresarial no tendrá acceso temporalmente a los servicios que le proporciona el Portal, hasta que sea validada nuevamente. ¿Desea continuar?');
		if (answer==true){
			return true;
		} else {
			return false;
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
		
		if (dijit.byId('ComUpdCompanyForm').isValid() && checkOtherFields()){		
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
				
				out.println("var vCheckStyle = document.getElementById('confCorreo').style.visibility;");
				out.println("var checkHideDiv = vCheckStyle == 'hidden' ? true : false;");
				out.println("if(!checkHideDiv && vCorreoElectronico==oCorreoElectronico){");		
				out.println("	alert('La dirección de correo electronico ya esta registrada para esta empresa.');");	
				out.println("	dojo.byId('btnGuardar').disabled=false; ");
				out.println("} else {");				
					out.println("if(vCorreoElectronico!=oCorreoElectronico){");
					out.println("	vCamposRelev = vCamposRelev + ' la dirección de correo electrónico ';");
					out.println("	var vEmailConfirm = emailConfirmation();");
					out.println("	if(vEmailConfirm==true){");					
					out.println("		document.ComUpdCompanyForm.submit();");
					out.println("	} else { dojo.byId('btnGuardar').disabled=false;  }");
					out.println("}  else { ");
					out.println("      if(vContacto!=oContacto){");
					out.println("	       vCamposRelev = vCamposRelev + ' Nombre de la persona de contacto de la empresa ';");
					out.println("	   } if(vIdTipoTelefono!=oIdTipoTelefono){");
					out.println("	       vCamposRelev = vCamposRelev + ' Tipo de teléfono principal ';");					
					out.println("      } if(vAcceso!=oAcceso){");
					out.println("		   vCamposRelev = vCamposRelev + ' Acceso del teléfono principal ';");
					out.println("	   } if(vClave!=oClave){");
					out.println("      	   vCamposRelev = vCamposRelev + ' Clave Lada del teléfono principal ';");
					out.println("      } if(vTelefono!=oTelefono){");
					out.println("      	   vCamposRelev = vCamposRelev + ' Número telefónico principal ';");
					out.println("      } if(vExtension!=oExtension){");	
					out.println("      	   vCamposRelev = vCamposRelev + ' Extensión del teléfono principal ';");
					out.println("      } if(vDescripcion!=oDescripcion) {");
					out.println("      	   vCamposRelev = vCamposRelev + ' Descripción de la empresa ';");
					out.println("      } ");				
					out.println("      var vCamposLen = vCamposRelev.length;");
					out.println("      if(vCamposLen>0){");
					out.println("	       var vContactConfirm = contactFieldsConfirmation(vCamposRelev);");
					out.println("      	   if(vContactConfirm==true){");
					out.println("          	    document.ComUpdCompanyForm.submit();");
					out.println("          } else { dojo.byId('btnGuardar').disabled=false; }");
					out.println("      } else {");
					//out.println("      	   document.ComUpdCompanyForm.submit();");				
					out.println("      	   dojo.byId('ComUpdCompanyForm').submit();");	
					out.println("      }");
					out.println("}");				
				out.println("}");
				
			} else {
				//out.println("document.ComUpdCompanyForm.submit();");
				out.println("dojo.byId('ComUpdCompanyForm').submit();");			
			}%>
						
		} else {		
			var chkVal = getRadioValue('idTipoPersona');
			
			if(chkVal==null){ 
				vCampos = vCampos + ' Tipo de persona.<br/>';
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
			
			<%if(formCompany.getIdTipoUsuario()==Constantes.TIPO_USUARIO.ADMINISTRADOR.getIdTipoUsuario()){
			out.println("var vTipoEmpresa = getAnyElementValueById('idTipoEmpresaSelect');");
			out.println("if(!vTipoEmpresa){");
			out.println("	vCampos = vCampos + ' Tipo de empresa.<br/>';");
			out.println("	dijit.byId('idTipoEmpresaSelect').focus();");
			out.println("}");
			
			out.println("var vTipoActividad = getAnyElementValueById('idActividadEconomicaSelect');");
			out.println("if(!vTipoActividad){");
			out.println("	vCampos = vCampos + ' Actividad económica principal.<br/>';");
			out.println("	dijit.byId('idActividadEconomicaSelect').focus();");
			out.println("}");	
	
			out.println("var vMedio = getAnyElementValueById('idMedioSelect');");
			out.println("if(!vMedio){");
			out.println("	vCampos = vCampos + '¿Cómo se enteró del portal del empleo?.<br/>';");
			out.println("	dijit.byId('idMedioSelect').focus();");
			out.println("}");					
			}%>
			
	
			var vNumeroEmpleados = getAnyElementValueById('numeroEmpleados');
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
				vCampos = vCampos + ' Código Postal.<br/>';
				dijit.byId('codigoPostal').focus();
			}		
			
			var vCalle = getAnyElementValueById('calle');
			if(!vCalle){
				vCampos = vCampos + ' Calle .<br/>';
				dijit.byId('calle').focus();
			}		
	
			var vNumExterior = getAnyElementValueById('numeroExterior');
			if(!vNumExterior){
				vCampos = vCampos + ' Número Exterior.<br/>';
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
			var vStyle = document.getElementById('confirmarCorreoElectronico').style.visibility;
			var hideDiv = vStyle == 'hidden' ? true : false;
			setDiv('confCorreo', hideDiv);			
			if(!hideDiv && !vConfirmarCorreoElectronico){
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
			dojo.byId('message').innerHTML = 'Los siguientes campos muestran datos inválidos - Se muestra(n) el (los) campo(s) con inconsistencias:' + vCampos;
			//var vMensaje = 'Los siguientes campos muestran datos inválidos - Se muestra(n) el (los) campo(s) con inconsistencias:<br/> ' + vCampos;
			//setMensaje(vMensaje);	
			dojo.byId('btnGuardar').disabled=false;
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
		var vTelefono = dijit.byId('telefono');
	   	
		if(vCorreoElectronico!=vConfirmarCorreoElectronico){
			vForm = false;
			vMessage = vMessage + '\nCorreo electrónico y Confirmar correo electrónico deben ser iguales.';
			document.getElementById('correoElectronico').focus();
		}
			
		if(!vDescripcion.value){
			vForm = false;
			vMessage = vMessage + '\nDebe proporcionar la descripcion de la empresa.';	
			document.getElementById('descripcion').focus();
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
</script>
             
				<form name="ComUpdCompanyForm" id="ComUpdCompanyForm" method="post" action="comupdcompany.do" dojoType="dijit.form.Form">
					<input type="hidden" name="method" id="method" value="init"/>
					<input type="hidden" name="Ctrl" id="Ctrl" dojoType="dijit.form.TextBox" value="1"/>
					<input type="hidden" name="cpnuevo" id="cpnuevo" dojoType="dijit.form.TextBox" value="1"/>
					<input type="hidden" name="idEmpresa" id="idEmpresa" value="${ComUpdCompanyForm.idEmpresa}"/>
					<input type="hidden" name="hiddenDesTipoEmpresa" id="hiddenDesTipoEmpresa" value="${ComUpdCompanyForm.hiddenDesTipoEmpresa}"/>
					<input type="hidden" name="hiddenDesActividadEconomica" id="hiddenDesActividadEconomica" value="${ComUpdCompanyForm.hiddenDesActividadEconomica}"/>
					<input type="hidden" name="hiddenDesEntidad" id="hiddenDesEntidad" value="${ComUpdCompanyForm.hiddenDesEntidad}"/>
					<input type="hidden" name="hiddenDesMunicipio" id="hiddenDesMunicipio" value="${ComUpdCompanyForm.hiddenDesMunicipio}"/>
					<input type="hidden" name="hiddenDesColonia" id="hiddenDesColonia" value="${ComUpdCompanyForm.hiddenDesColonia}"/>
					<input type="hidden" name="hiddenCodigoPostal" id="hiddenCodigoPostal" value="${ComUpdCompanyForm.hiddenCodigoPostal}"/>
										
					<input type="hidden" name="hiddenPreCorreoElectronico" id="hiddenPreCorreoElectronico" value="${ComUpdCompanyForm.preCorreoElectronico}"/>
					<input type="hidden" name="hiddenPreContacto" id="hiddenPreContacto" value="${ComUpdCompanyForm.preContactoEmpresa}"/>
					<input type="hidden" name="hiddenPreIdTipoTelefono" id="hiddenPreIdTipoTelefono" value="${ComUpdCompanyForm.preIdTipoTelefono}"/>
					<input type="hidden" name="hiddenPreAcceso" id="hiddenPreAcceso" value="${ComUpdCompanyForm.preAcceso}"/>
					<input type="hidden" name="hiddenPreClave" id="hiddenPreClave" value="${ComUpdCompanyForm.preClave}"/>
					<input type="hidden" name="hiddenPreTelefono" id="hiddenPreTelefono" value="${ComUpdCompanyForm.preTelefono}"/>
					<input type="hidden" name="hiddenPreExtension" id="hiddenPreExtension" value="${ComUpdCompanyForm.preExtension}"/>
					<input type="hidden" name="hiddenPreDescripcion" id="hiddenPreDescripcion" value="${ComUpdCompanyForm.preDescripcion}"/>
					<input type="hidden" name="acceso" id="acceso" dojoType="dijit.form.TextBox" value="${ComUpdCompanyForm.acceso}" />
					<input type="hidden" name="codigoPostalShow" id="codigoPostalShow" dojoType="dijit.form.TextBox" value="${ComUpdCompanyForm.codigoPostal}" />
					
              
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
                	
                		<p class="entero">
                		<label><strong>Si modifica cualquiera de los siguientes campos, tendrá que pasar nuevamente por el proceso de validación como
                		cuando se efectuó el registro:</strong> Descripción de la empresa, nombre de la persona de contacto con la empresa, datos del teléfono principal 
						(tipo de teléfono, acceso, clave Lada, teléfono, extensión).</label>
                		</p>
                		<!-- 
                		<div class="entero">
				        <span class="un_medio">
				        <label><strong>Id Portal del Empleo</strong></label> ${ComUpdCompanyForm.idPortalEmpleo}<br/>				       
				        <span class="un_medio">
				        </span>                  		                		                	
                		</div>                		
                		 -->
                		<div class="entero">
				        <span class="un_medio">
				        <label for="rfc"><strong>RFC</strong></label><br/>
				        <input id="rfc" name="rfc" maxlength="13" size="13"
				        	dojoType="dijit.form.ValidationTextBox" required="false" regExp="^[A-Za-z\s\ñÑ0-9]{0,13}$"
				        	value ="${ComUpdCompanyForm.rfc}" 
				        	invalidMessage="Dato inválido" trim="true" />        
				        </span>							        				       
				        <span class="un_medio">
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
					        <c:out value="${ComUpdCompanyForm.nombre}"/>
		       			    </span>
		       			    <span class="un_cuarto">
					        <label><strong>Primer apellido*</strong></label><br/>
					        <c:out value="${ComUpdCompanyForm.apellido1}"/>
		       			    </span>
		       			    <span class="un_cuarto">
					        <label><strong>Segundo apellido</strong></label><br/>
					        <c:out value="${ComUpdCompanyForm.apellido2}"/>
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
	       			    <span class="un_medio">
				        <label><strong>Razón social*</strong></label><br/>
				        <c:out value="${ComUpdCompanyForm.razonSocial}"/>
						</span>        
						<span class="un_medio">
				        <label><strong>Fecha de acta constitutiva*</strong></label><br/>
				        <%=strFechaActa%>
						</span>        
						</div>						
						<%		
						}  
						%>   
						<div class="entero">
						 <label for="nombreComercial"><strong>Nombre Comercial</strong></label><br/>
						  <input id="nombreComercial" name="nombreComercial" maxlength="150" size="50" 			
					        	dojoType="dijit.form.ValidationTextBox" required="false" onpaste="return false;" regExp="^[A-Za-z\s\d\-.áéíóúÁÉÍÓÚñÑ/']{1,150}$"  uppercase="true"			        	
					        	value="${ComUpdCompanyForm.nombreComercial}"
					        	invalidMessage="Dato inválido" missingMessage="Dato requerido" trim="true"/>
					        <label for="nombreComercial"></label> 
						</div>    						             		
						
						<div class="entero">
				        <strong><label for="descripcion">Descripción de la empresa</label>*</strong><br/>
						<textarea name="descripcion" id="descripcion" maxlength="2000" 
			        	onkeypress="return caracteresValidos(event);"
			        	onpaste="return false;"
			        	regExp="^[a-zA-ZáéíóúÁÉÍÓÚñÑ0-9.,;:#\\s]{1,2000}$" 
			        	style="width:550px;min-height:120px;_height:200px;" trim="true"
			        	required="true">${ComUpdCompanyForm.descripcion}</textarea>	
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
				        	dojoType="dijit.form.ValidationTextBox" required="true" regExp="^[A-Za-z\s\áéíóúÁÉÍÓÚñÑ0-9/']{1,150}$" 
				        	value="${ComUpdCompanyForm.contactoEmpresa}" 
				        	invalidMessage="Dato inválido" missingMessage="Dato requerido" trim="true" />
				        </span>
						<span class="un_medio">
			        	<strong><label for="idTipoEmpresaSelect">Tipo de empresa</label>*</strong><br/>   
						<input type="hidden" name="idTipoEmpresa" id="idTipoEmpresa" value="${ComUpdCompanyForm.idTipoEmpresa}" />
						<% 
						if(formCompany.getIdTipoUsuario()==(long)Constantes.TIPO_USUARIO.ADMINISTRADOR.getIdTipoUsuario()){
							out.println("<div dojoType=\"dojo.data.ItemFileReadStore\" jsId=\"tiposEmpresaStore\"  url=\"" + context + "comupdcompany.do?method=tiposEmpresa\"></div>");
							out.println("<select dojotype=\"dijit.form.ComboBox\" store=\"tiposEmpresaStore\" id=\"idTipoEmpresaSelect\" required=\"true\"></select>");
			        	} else {
			        		//out.println("<c:out value=\"${ComUpdCompanyForm.hiddenDesTipoEmpresa}\"/>");			        		
			        		out.println(formCompany.getHiddenDesTipoEmpresa());
			        		
			        	}			        	
			        	%>			        	   										       
	       			    </span>		
	       			    </div>
                		
	       			    <div class="entero">
	       			    <span class="un_medio">   			    
			        	<strong><label for="idActividadEconomicaSelect">Actividad económica principal</label>*</strong><br/> 
			        	<input type="hidden" name="idActividadEconomica" id="idActividadEconomica" value="${ComUpdCompanyForm.idActividadEconomica}" />
			        	<%
			        	if(formCompany.getIdTipoUsuario()==(long)Constantes.TIPO_USUARIO.ADMINISTRADOR.getIdTipoUsuario()){
							out.println("<div dojoType=\"dojo.data.ItemFileReadStore\" jsId=\"tiposActividadEconomicaStore\"  url=\"" + context + "comupdcompany.do?method=tiposActividadEconomica\"></div>");
							out.println("<select dojotype=\"dijit.form.ComboBox\" store=\"tiposActividadEconomicaStore\" id=\"idActividadEconomicaSelect\" required=\"true\" style=\"width:100%\"></select>");
			        		
			        	} else {
			        		//out.println("<c:out value=\"${ComUpdCompanyForm.hiddenDesActividadEconomica}\"/>");
			        		out.println(formCompany.getHiddenDesActividadEconomica());
			        	}
			        	%>							
						</span>
						</div>
						<div class="entero">	  
						<span class="un_medio">
				        <strong><label for="numeroEmpleados">Número de empleados</label>*</strong><br/>
				        <input id="numeroEmpleados" name="numeroEmpleados" maxlength="5" size="5"         	
				        	dojoType="dijit.form.ValidationTextBox" required="true" regExp="^[0-9]{1,5}$"
				        	value="${ComUpdCompanyForm.numeroEmpleados}"
				        	invalidMessage="Dato inválido" missingMessage="Dato requerido" trim="true" />
				        <label for="numeroEmpleados"></label>               			           			    					
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
						<label for="confidencial"><strong>Deseo que mis datos permanezcan confidenciales</strong></label><br/>					
						</span>                   														   		        
               			</div>

               			<h3>Domicilio</h3>
               			<div class="entero">
	       			    <span class="un_tercio">
	      			    <strong><label for="codigoPostal">Código Postal</label>*</strong><br/>    			    
				        <input id="codigoPostal" name="codigoPostal" maxlength="5" size="5" value="${ComUpdCompanyForm.codigoPostal}"
				        	dojoType="dijit.form.ValidationTextBox" required="true" regExp="^[0-9]{1,5}" 
				        	invalidMessage="Dato inválido" trim="true" onChange="clrAdrFlds()" />       	      			    				        	 	       			   		
	       			    </span>	
	       			    <span class="un_tercio">
	       			    <input type="checkbox" id="checkCtrl" />
						<label for="checkCtrl"><strong>No conozco mi código postal</strong></label><br/>      	       			        			   
	       			    </span>	
	       			    <span class="un_tercio">
	       			    <input type="button" name="btnValidar" id="btnValidar" class="boton" value="Validar CP">
	       			    <!-- <div id="btnValidar" dojoType="dijit.form.Button" class="boton">Validar CP</div> -->		
	       			    </span>
               			</div>
               			
               			<div  class="entero">
				        <span class="un_tercio"><label for="pais"><strong>País</strong></label><br/>
				        <input name="pais" id="pais" maxlength="10" size="6" value="México" disabled />		        			        
				        </span>    
				        </div>           			
               			
               			<div  class="entero">
				        <span class="un_tercio">      
						<strong><label for="idEntidadSelect">Entidad federativa</label>*</strong><br/>
						<input type="hidden" name="idEntidad" id="idEntidad" value="" />
						<div dojoType="dojo.data.ItemFileReadStore" jsId="entidadFederativaStore" urlPreventCache="true" clearOnClose="true" ></div>
						<select dojotype="dijit.form.FilteringSelect" store="entidadFederativaStore" id="idEntidadSelect" required="true" disabled="disabled" autoComplete="false"></select>
				        </span>
				        
				        <span class="un_tercio">
				        <strong><label for="idMunicipioSelect">Delegación ó municipio</label>*</strong><br/>
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
				        <input id="calle" name="calle" maxlength="150" size="60" value="${ComUpdCompanyForm.calle}" 
				        	dojoType="dijit.form.ValidationTextBox" required="true" regExp="^[a-zA-ZáéíóúÁÉÍÓÚñÑ0-9\s]{1,150}$" uppercase="true"
				        	invalidMessage="Dato inválido" missingMessage="Dato requerido" trim="true"/>
				        </span> 
				        
				        <span class="un_tercio"> 
				        <strong><label for="numeroExterior">Número exterior</label>*</strong><br/> 
				        <input id="numeroExterior" name="numeroExterior" maxlength="50" size="4" value="${ComUpdCompanyForm.numeroExterior}"
				        	dojoType="dijit.form.ValidationTextBox" required="true" regExp="^[a-zA-ZáéíóúÁÉÍÓÚñÑ0-9\s]{1,50}" 
				        	invalidMessage="Dato inválido" trim="true" />        			            			   
				        </span> 
				        
				        <span class="un_tercio"><label for="numeroInterior"><strong>Número interior</strong></label><br/>
				        <input id="numeroInterior" name="numeroInterior" maxlength="50" size="4" value="${ComUpdCompanyForm.numeroInterior}"
				        	dojoType="dijit.form.ValidationTextBox" required="false" regExp="^[a-zA-ZáéíóúÁÉÍÓÚñÑ0-9\s]{1,50}" 
				        	invalidMessage="Dato inválido" trim="true" />        				             			   
				        </span> 			        
               			</div>
               			
               			<div class="entero" >
				        <span class="un_tercio"><strong><label for="entreCalle">Entre calles</label>*</strong><br/> 
				        <input id="entreCalle" name="entreCalle" maxlength="150" size="60" value="${ComUpdCompanyForm.entreCalle}" 
				        	dojoType="dijit.form.ValidationTextBox" required="true" regExp="^[a-zA-ZáéíóúÁÉÍÓÚñÑ0-9\s]{1,150}$" uppercase="true"
				        	invalidMessage="Dato inválido" missingMessage="Dato requerido" trim="true"/>		            			   
				        </span> 		
				        	      
				        <span class="un_tercio"><strong><label for="yCalle">Y</label>*</strong><br/>
				        <input id="yCalle" name="yCalle" maxlength="150" size="60" value="${ComUpdCompanyForm.yCalle}" 
				        	dojoType="dijit.form.ValidationTextBox" required="false" regExp="^[a-zA-ZáéíóúÁÉÍÓÚñÑ0-9\s]{1,150}$" uppercase="true"
				        	invalidMessage="Dato inválido" trim="true"/>
				        </span> 			        
               			</div>               			

               			<h3>Datos de identificación de la cuenta</h3>
               			<p class="entero">
				        <strong><label for="correoElectronico">Correo electrónico</label>*</strong><br/>
	                   	<input type="text" name="correoElectronico" id="correoElectronico" size="50" maxlength="65" trim="true"
	                   	value="${ComUpdCompanyForm.correoElectronico}" onchange="validarFormatoCorreo(this);" oncopy="return false;" 
	                   	oncut="return false" onpaste="return false;" readonly="true"  />			        
               			</p>
               			<div id="confCorreo" class="entero" style="visibility:hidden;">
				        <strong><label for="confirmarCorreoElectronico">Confirmar correo electrónico</label>*</strong><br/>
	                    <input type="text" name="confirmarCorreoElectronico" id="confirmarCorreoElectronico" size="50" maxlength="65" trim="true"
	                    value="${ComUpdCompanyForm.correoElectronico}" onchange="validarFormatoCorreo(this);" oncopy="return false;" oncut="return false" 
	                    onpaste="return false;" ondragover="return false;" />			        				        
				        </div>	
					  <p class="entero">
	                   	<a href="javascript:showConf();" class="un_cuarto links">Actualizar correo electr&oacute;nico</a>
	                   </p>

                     <div ><!-- class="division"  -->
               			<span class="entero"><strong>Teléfono</strong></span> 	
				        <span class="un_tercio"><label><strong><label for="idTipoTelefono">Tipo de teléfono</label>*</strong></label><br/>
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
						<input id="accesoShow" name="accesoShow" maxlength="3" size="3"
						 	dojoType="dijit.form.ValidationTextBox" required="false" 
						 	regExp="^(<%=Constantes.CLAVE_TELEFONO_CELULAR%>|<%=Constantes.CLAVE_TELEFONO_FIJO%>)$"
						 	value="${ComUpdCompanyForm.acceso}"
						 	invalidMessage="Dato inválido" missingMessage="Dato requerido" trim="true" disabled="disabled" />      			        
				        </span> 			 
				        <span class="un_tercio"><strong><label for="clave">Clave Lada</label>*</strong><br/>  
						<input id="clave" name="clave" maxlength="3" size="3"
						 	dojoType="dijit.form.ValidationTextBox" required="true" regExp="^[0-9]{2,3}$"
						 	value="${ComUpdCompanyForm.clave}"
						 	invalidMessage="Dato inválido" missingMessage="Dato requerido" trim="true" />	        			        
				        </span> 			 
				        </div>
				        <div>
				        <span class="un_tercio"><strong><label for="telefono">Teléfono</label>*</strong><br/>
						<input id="telefono" name="telefono" maxlength="8" size="8" onBlur="changePhoneSizeRequired()"
						 	dojoType="dijit.form.ValidationTextBox" required="true" regExp="^[0-9]{7,8}$"	
						 	value="${ComUpdCompanyForm.telefono}"		 	
						 	invalidMessage="Dato inválido" missingMessage="Dato requerido" trim="true" />      			          
				        </span> 			 
				        <span class="un_tercio"><label for="extension"><strong>Extensión</strong></label><br/>
						<input id="extension" name="extension" maxlength="8" size="8"
						 	dojoType="dijit.form.ValidationTextBox" required="false" regExp="^[0-9]{1,6}$"
						 	value="${ComUpdCompanyForm.extension}"
						 	invalidMessage="Dato inválido" missingMessage="Dato requerido" trim="true" /> 
						</span> 	       			           
	                    <span class="un_tercio"><a href="#" class="links" onclick="openPhoneWindow()">Agregar teléfono</a></span>	                    
	                    </div>

               			<div class="entero">
						<span class="un_medio">
				        <label for="paginaWeb"><strong>Página Web</strong></label><br/>
				        <input type="text" id="paginaWeb" name="paginaWeb" maxlength="65" value="${ComUpdCompanyForm.paginaWeb}"  onchange="validateWebsite(this);">
				        </span>	
				        </div>		
                            
               			<div class="entero">
						<span class="un_tercio">
						<a href="#" class="links" onclick="openLogoWindow()">Cambiar la imagen</a>						
				        </span>	
				        <%-- DESCOMENTAR EN PROD   
						<span class="un_tercio">
						<a href="#" onclick="confirmPartner();" class="links">Registro de terceras empresas</a>
				        </span>	
				        --%>
				        <%--SOLO PARA QA   	 	--%>
						<span class="un_tercio">
						<a href="#" onclick="confirmPartner2();" class="links">Registro y edición de terceras empresas</a>
				        </span>	
				       
				        <%--	DESCOMENTAR EN PROD	       
						<span class="un_tercio">
						<a href="#" onclick="confirmContact();" class="links">Registro de contactos</a>
				        </span>			
				        		   --%>     
				        <%--SOLO PARA QA  		--%>  
						<span class="un_tercio">
						<a href="#" onclick="confirmContact2();" class="links">Registro y edición de contactos</a>
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
					<div dojoType="dijit.Dialog" id="MensajeAlert" title="Mensaje" style="display:none" draggable ="false">
						  <table width="300px" >
							 <tr align="center">
								 <td><div id ="mensaje"></div>&nbsp;</td>				 
							 </tr>
						 </table>	
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

    	entidadFederativaStore.url = "${context}comupdcompany.do?method=obtenerEntidadJSON" + "&" + "codigoPostal="+ vCodigoPostal;
    	entidadFederativaStore.close();
		
    	entidadFederativaStore.fetch({
          	onComplete: function(items, request) {
          		if (items.length == 0) return;
          		dijit.byId('idEntidadSelect').attr('value', items[0].value);
          	}
    	});

    	municipioStore.url = "${context}comupdcompany.do?method=obtenerMunicipioJSON" + "&" + "codigoPostal="+ vCodigoPostal;
    	municipioStore.close();

    	municipioStore.fetch({
          	onComplete: function(items, request) {
          		if (items.length == 0) return;
          		dijit.byId('idMunicipioSelect').attr('value', items[0].value);
          	}
    	});

    	coloniaStore.url = "${context}comupdcompany.do?method=obtenerColoniaJSON" + "&" + "codigoPostal="+ vCodigoPostal;
    	coloniaStore.close();

    	coloniaStore.fetch({
          	onComplete: function(items, request) {
          		if (items.length == 0) return;
          		dijit.byId('idColoniaSelect').attr('value', items[0].value);
          	}
    	});
    	
    	//TERMINA PARA UPDATE
        	
        dojo.connect(document.ComUpdCompanyForm.btnValidar, 'onclick', function() {
 			if(dijit.byId('codigoPostal').get('value') == '') {
 			   alert("El código postal es necesario.");
 			   return;
			}
 			if(dijit.byId('codigoPostal').value.length <5){
  			   alert("El código postal debe tener 5 dígitos.");
  			   return;
 			}			
			

            if(dijit.byId('Ctrl').value == 1) {

				// ************************************
				
				var vCodigoPostal = dijit.byId('codigoPostal').get('value');
				var vMsjNotF = 0;
				document.ComUpdCompanyForm.method.value = 'obtenerEntidadJSON';
				
				dijit.byId('idEntidadSelect').disabled=false;
				dijit.byId('idMunicipioSelect').disabled=false;
				dijit.byId('idColoniaSelect').disabled=false;
				
            	entidadFederativaStore.url = "${context}comupdcompany.do?method=obtenerEntidadJSON" + "&" + "codigoPostal="+ vCodigoPostal;
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

            	municipioStore.url = "${context}comupdcompany.do?method=obtenerMunicipioJSON" + "&" + "codigoPostal="+ vCodigoPostal;
            	municipioStore.close();

            	municipioStore.fetch({
                  	onComplete: function(items, request) {                  	
                  		if (items.length == 0) return;                    	
                  		dijit.byId('idMunicipioSelect').attr('value', items[0].value);
                  	}
            	});

            	coloniaStore.url = "${context}comupdcompany.do?method=obtenerColoniaJSON" + "&" + "codigoPostal="+ vCodigoPostal;
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
            		
            	municipioStore.url = "${context}comupdcompany.do?method=obtenerMunicipio" + "&" + "idEntidad="+ vEntidad;
            	municipioStore.close();
            	            	
			}
        });

        dojo.connect(dijit.byId("idMunicipioSelect"), "onChange", function() {

            if(dijit.byId('Ctrl').value == 0){

				var vEntidad   = dijit.byId('idEntidadSelect').get('value');
				var vMunicipio = dijit.byId('idMunicipioSelect').get('value');
					
            	//dijit.byId('coloniaSelect').disabled=false;
            	dijit.byId('idColoniaSelect').disabled=false;
            		
            	coloniaStore.url = "${context}comupdcompany.do?method=obtenerColonia" + "&" + "idEntidad="+ vEntidad + "&" + "idMunicipio="+ vMunicipio;
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
				  url: 'comupdcompany.do',
				  form:'ComUpdCompanyForm',
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
            		
            		entidadFederativaStore.url = "${context}comupdcompany.do?method=obtenerEntidad";
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

//changePersonTypeRequired();
fillUpAccessKey();
        });                    
  </script>                  		                


