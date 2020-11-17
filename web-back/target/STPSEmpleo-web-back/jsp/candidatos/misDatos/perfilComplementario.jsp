<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="mx.gob.stps.portal.web.candidate.form.ProgramaForm, mx.gob.stps.portal.core.infra.utils.Constantes"%>
<%
	String context = request.getContextPath() +"/";
	String vProxy = "http://orangoo.com/newnox?lang=";
	pageContext.setAttribute("vProxy",vProxy);
%>
<script type="text/javascript" src="js/helper/messageHelper.js"></script>
<c:set var="regexpmail">^[a-zA-Z0-9_-]+(?:\.[a-zA-Z0-9_-]+)*@(?:[a-zA-Z0-9](?:[a-zA-Z0-9-_]*[a-zA-Z0-9-])?\.)+[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?$</c:set>
<script>
	dojo.addOnLoad(function() {
    	entidadFederativaStore.url = "${context}domicilio.do?method=obtenerEntidad";
	    entidadFederativaStore.close();
	    loadFeds();
	});
</script>
<script type="text/javascript">
    dojo.require("dijit.dijit");
    dojo.require("dijit.form.Form");
    dojo.require("dijit.form.CheckBox");
    dojo.require("dijit/form/MultiSelect");
    dojo.require("dijit.form.FilteringSelect");
    dojo.require("dijit.form.ValidationTextBox");
    dojo.require("dojo.data.ItemFileReadStore");
    function doSubmmitDataComp() {
    	dialogMsgConfirm.hide();
		document.ProgramaForm.method.value = 'savecomplement';
		document.ProgramaForm.tipoTelefonoc.value = getRadioValue('tipoTelefonoc');
		document.ProgramaForm.idPeso.value = dijit.byId('idPesoSelect').get('value');
		document.ProgramaForm.idEstadoCivil = dijit.byId('idEstadoCivilSelect').get('value');
		document.ProgramaForm.idEstatura.value = dijit.byId('idEstaturaSelect').get('value');
		document.ProgramaForm.idLocalidad.value = dijit.byId('idLocalidadSelect').get('value');
		document.ProgramaForm.idComplexion.value = dijit.byId('idComplexionSelect').get('value');
		document.ProgramaForm.idExperienciaAgricola.value = dijit.byId('idExperienciaAgricolaSelect').get('value');
		document.ProgramaForm.idDominioIngles.value = dijit.byId('idDominioInglesSelect').get('value');
		document.ProgramaForm.idDominioFrances.value = dijit.byId('idDominioFrancesSelect').get('value');
		document.ProgramaForm.idLicencia.value = dijit.byId('idLicenciaSelect').get('value');
		document.ProgramaForm.idSituacionLicencia.value = dijit.byId('idSituacionLicenciaSelect').get('value');
		document.ProgramaForm.licenciaInternacional.value = getRadioValue('licenciaInternacional');
		dojo.xhrGet({
			url: 'perfilComplementario.do',
			form:'ProgramaForm',
			sync: true,
			timeout:180000,
			load: function(data) {
				var res = dojo.fromJson(data);
				if (res.type=='ext') {
					messagePath(res.message,'<%=context%>miEspacioCandidato.do?method=init');
				}else {
					message(res.message);
				}
			}
		}); 
    }
    function messagePath(msg,path){
		var html =
			'<div id="messageDialgop2" name="messageDialgop2">' +
		'<p style="text-align: center;">'+ msg +'</p>'+
		'<p class="form_nav">'
		+ cerrarMsgRuta(path, "Aceptar")
	    + '</p>'
	    +'</div>';
	
		dialogMsg = new dijit.Dialog({
	        title: 'Mensaje',
	        content: html,
	        style: "width:300px;",
	        showTitle: false, draggable : false, closable : false,
	    });			
		dialogMsg.show();
	}
    function messageConfirm(msg) {
		var html =
			'<div id="messageDialgopC" name="messageDialgopC">' +
			'<p style="text-align: center;">'+ msg +'</p>'+
			'<p class="form_nav">' + 
			'<input id="btnRenv" class="boton_naranja" type="button" name="btnRenv" onClick="doSubmmitDataComp()" value="Aceptar"/>' +
			'<input id="btnCanc" class="boton_naranja" type="button" name="btnCanc" onClick="dialogMsgConfirm.hide();" value="Cancelar"/>' +
    		'</p>' +
    		'</div>';
		dialogMsgConfirm = new dijit.Dialog({
	        title: 'Mensaje',
	        content: html,
	        style: "width:300px;",
	        showTitle: false, draggable : false, closable : false,
	    });	
		dialogMsgConfirm.show();
	}
	function doSubmitCancel() {
		messageRutaCancel('Los datos no guardados se perderán ¿Continuar?','<c:url value="/miEspacioCandidato.do"/>');
	}
	function validateForm() {
		if (!validateDataComp()) return false;
		if (!validateExtAdr()) return false;
		if (!validateContacts()) return false;
		if (!validateStationPh()) return false;
		if (!validateFarming()) return false;
		if (!validateClinic()) return false;
		messageConfirm('¿Está seguro de guardar datos complementarios?');
	}
	function validateContacts() {
		var idBeneficiario1= dijit.byId('idBeneficiario_1');
		var idBeneficiario2= dijit.byId('idBeneficiario_2');
		var idBeneficiario3= dijit.byId('idBeneficiario_3');
		var idBeneficiario4= dijit.byId('idBeneficiario_4');
		var idBeneficiario5= dijit.byId('idBeneficiario_5');
		if (idBeneficiario1 != undefined) {
			return validateDefContact(1);
		}
		if (idBeneficiario2 != undefined) {
			return validateDefContact(2);
		}
		if (idBeneficiario3 != undefined) {
			return validateDefContact(3);
		}
		if (idBeneficiario4 != undefined) {
			return validateDefContact(4);
		}
		if (idBeneficiario5 != undefined) {
			return validateDefContact(5);
		}
		return true;
	}
	function validateDefContact(index) {
		var nombreContacto = dijit.byId('nombreContacto_' + index);
		var primerApellidoCto = dijit.byId('primerApellidoCto_' + index);
		var idParentescoCtoSelect = dijit.byId('idParentescoCtoSelect_' + index);
		if (nombreContacto.value=='') {
			nombreContacto.focus();
			message('Nombre es requerido.');
			return false;
		}
		if (primerApellidoCto.value=='') {
			primerApellidoCto.focus();
			message('Primer apellido es requerido.');
			return false;
		}
		if (idParentescoCtoSelect.get('value')==0) {
			idParentescoCtoSelect.focus();
			message('Seleccione Parentesco.');
			return false;
		}
		return validateDefPhone(index);
	}
	function validateStationPh() {
		var tipoTelefonoId = getRadioValue('tipoTelefonoc');
		var vClave = document.getElementById('clavec').value;
        var vTelefono = document.getElementById('telefonoc').value; 
        var vExtension = document.getElementById('extensionc').value; 
        if (null == tipoTelefonoId) {
        	document.ProgramaForm.tipoTelefonoc[0].focus();
        	message('Debe seleccionar el tipo de teléfono.');
			return false;
        }
        if (!isNumber(vClave)) {
        	document.ProgramaForm.clavec.focus();
         	message('La clave LADA debe ser un valor numérico (0-9).');
        	return false;
        }else {
        	if (!vClave || vClave.length<2) {
        		document.ProgramaForm.clavec.focus();
        		message('Debe proporcionar una clave LADA de 2 ó 3 caracteres.');
        		return false;
	        }else {
				if(vClave.length==2) {
					if(vTelefono.length!=8) {
						document.ProgramaForm.clavec.focus();
						message('Debe proporcionar un número telefónico de 8 dígitos.');
        				return false;
					}
				}else if(vClave.length==3) {
					if (vTelefono.length!=7) {
						document.ProgramaForm.clavec.focus();
						message('Debe proporcionar un número telefónico de 7 dígitos.');
        				return false;
					}	
				}         
	         }          
         }
         if(!vTelefono) {
         	document.ProgramaForm.telefonoc.focus();
        	message('Debe proporcionar el número telefónico.');
        	return false;
        }else {
        	if(!isNumber(vTelefono)) {
        		document.ProgramaForm.telefonoc.focus();
	        	message('El número telefónico debe ser un valor numérico (0-9).');
        		return false;
	        }       
        }
         if (vExtension && !isNumber(vExtension)) {
         	document.ProgramaForm.extensionc.focus();
         	message('La extensión debe ser un valor numérico (0-9).');
        	return false;
         }
         return true;
	}
	function validateExtAdr() {
		var entreCalle = dijit.byId('entreCalle');
		var yCalle = dijit.byId('yCalle');
		var idLocalidadSelect = dijit.byId('idLocalidadSelect');
		var referenciaDomicilio = document.getElementById('referenciaDomicilio');
		if (entreCalle.get('value')=='') {
			entreCalle.focus();
			message('Entre calle es requerido.');
			return false;
		}
		if (yCalle.get('value')=='') {
			yCalle.focus();
			message('Y calle es requerido.');
			return false;
		}
		if (idLocalidadSelect.get('value')==0) {
			idLocalidadSelect.focus();
			message('Seleccione localidad.');
			return false;
		}
		if (referenciaDomicilio.value=='') {
			referenciaDomicilio.focus();
			message('Referencia para llegar al domicilio es requerido.');
			return false;
		}
		return true;
	}
	function validateDataComp() {
		var peso = dijit.byId('idPesoSelect');
		var estatura = dijit.byId('idEstaturaSelect');
		var complexion = dijit.byId('idComplexionSelect');
		var estadoCivil = dijit.byId('idEstadoCivilSelect');
		var totalHijos = dijit.byId('totalHijos');
		var hijosMenores = dijit.byId('hijosMenores');
		var tiempoLabores = dijit.byId('idExperienciaAgricolaSelect');
		var dominioIngles = dijit.byId('idDominioInglesSelect');
		var dominioFrances = dijit.byId('idDominioFrancesSelect');
		var manejoMaquinaria = document.getElementById('manejoMaquinaria');
		var licManejoTipo = dijit.byId('idLicenciaSelect');
		var situacionLicencia = dijit.byId('idSituacionLicenciaSelect');
		var licenciaInternacional = getRadioValue('licenciaInternacional');
		if (estadoCivil.get('value')==0 || estadoCivil.get('value')==5) {
			estadoCivil.focus();
			message('Seleccione estado civil.');
			return false;
		}
		if (estatura.get('value')==0) {
			estatura.focus();
			message('Seleccione estatura.');
			return false;
		}
		if (peso.get('value')==0) {
			peso.focus();
			message('Seleccione peso.');
			return false;
		}
		if (complexion.get('value')==0) {
			complexion.focus();
			message('Seleccione complexión.');
			return false;
		}
		if (totalHijos.get('value')=='') {
			totalHijos.focus();
			message('Total de hijos es requerido.');
			return false;
		}
		if (hijosMenores.get('value')=='') {
			hijosMenores.focus();
			message('Hijos menores de 18 años es requerido.');
			return false;
		}
		if (tiempoLabores.get('value')==0) {
			tiempoLabores.focus();
			message('Seleccione desde cuando realiza labores agrícolas.');
			return false;
		}
		if (dominioIngles.get('value')==0) {
			dominioIngles.focus();
			message('Seleccione nivel de idioma Inglés.');
			return false;
		}
		if (dominioFrances.get('value')==0) {
			dominioFrances.focus();
			message('Seleccione nivel de idioma Francés.');
			return false;
		}
		if (!isValidMultiple(manejoMaquinaria)) {
			manejoMaquinaria.focus();
			message('Seleccione que tipo de automóvil o maquinaria sabe manejar.');
			return false;
		}
		if (licManejoTipo.get('value')==0) {
			licManejoTipo.focus();
			message('Seleccione qué tipo de licencia de conducir tiene.');
			return false;
		}
		if (situacionLicencia.get('value')==0) {
			situacionLicencia.focus();
			message('Seleccione situación de licencia.');
			return false;
		}
		if (situacionLicencia.get('value')==0) {
			situacionLicencia.focus();
			message('Seleccione situación de licencia.');
			return false;
		}
		if (null == licenciaInternacional) {
			document.ProgramaForm.licenciaInternacional[0].focus();
	        	message('Debe seleccionar si estaría dispuesto a obtener la licencia internacional.');
			return false;
	    }
	    return true;
	}
	function validateFarming() {
		var legumbresCampo = document.getElementById('legumbresCampo');
		var arbolesCampo = document.getElementById('arbolesCampo');
		var frutas = document.getElementById('frutas');
		var tabaco = document.getElementById('tabaco');
		var cereales = document.getElementById('cereales');
		var legumbresViv = document.getElementById('legumbresViv');
		var arbolesViv = document.getElementById('arbolesViv');
		var ganado = document.getElementById('ganado');
		var rastro = document.getElementById('rastro');
		var apicultura = document.getElementById('apicultura');
		var otros_rubros = document.getElementById('otros_rubros');
		if (!isValidMultiple(legumbresCampo)) {
			legumbresCampo.focus();
			message('Seleccione al memos una opción de verduras y legumbres.');
			return false;
		}
		if (!isValidMultiple(arbolesCampo)) {
			arbolesCampo.focus();
			message('Seleccione al memos una opción de árboles.');
			return false;
		}
		if (!isValidMultiple(frutas)) {
			frutas.focus();
			message('Seleccione al memos una opción de frutas.');
			return false;
		}
		if (!isValidMultiple(tabaco)) {
			tabaco.focus();
			message('Seleccione al memos una opción de tabaco.');
			return false;
		}
		if (!isValidMultiple(cereales)) {
			cereales.focus();
			message('Seleccione al memos una opción de cereales.');
			return false;
		}
		if (!isValidMultiple(legumbresViv)) {
			legumbresViv.focus();
			message('Seleccione al memos una opción de verduras y legumbres.');
			return false;
		}
		if (!isValidMultiple(arbolesViv)) {
			arbolesViv.focus();
			message('Seleccione al memos una opción de árboles.');
			return false;
		}
		if (!isValidMultiple(ganado)) {
			ganado.focus();
			message('Seleccione al memos una opción de cuidado de ganado.');
			return false;
		}
		if (!isValidMultiple(rastro)) {
			rastro.focus();
			message('Seleccione al memos una opción de rastro.');
			return false;
		}
		if (!isValidMultiple(apicultura)) {
			apicultura.focus();
			message('Seleccione al memos una opción de apicultura.');
			return false;
		}
		if (!isValidMultiple(otros_rubros)) {
			otros_rubros.focus();
			message('Seleccione al memos una opción de otros rubros relacionados.');
			return false;
		}
		return true;
	}
	function validateClinic() {
		var fisicas = document.getElementById('fisicas');
		var cronicas = document.getElementById('cronicas');
		var lesiones = document.getElementById('lesiones');
		var contagiosas = document.getElementById('contagiosas');
		var tieneIntervenciones = getRadioValue('tieneIntervenciones');
		if (!isValidMultiple(contagiosas)) {
			contagiosas.focus();
			message('Seleccione al memos una opción de enfermedades contagiosas.');
			return false;
		}
		if (!isValidMultiple(cronicas)) {
			cronicas.focus();
			message('Seleccione al memos una opción de enfermedades crónicas.');
			return false;
		}
		if (!isValidMultiple(fisicas)) {
			fisicas.focus();
			message('Seleccione al memos una opción de limitaciones físicas.');
			return false;
		}
		if (!isValidMultiple(lesiones)) {
			lesiones.focus();
			message('Seleccione al memos una opción de enfermedades o lesiones.');
			return false;
		}
		if (null == tieneIntervenciones) {
			document.ProgramaForm.tieneIntervenciones[0].focus();
	        	message('Debe seleccionar si cuenta con cirugías, amputaciones o fracturas.');
			return false;
	    }
		return true;
	}
	function isValidMultiple(oSelect) {
		var count=0;
		for(var i=0;i<oSelect.options.length;i++) {
			if (oSelect.options[i].selected) count++;
		}
		if (count<1) return false;
		return true;
	}
	function toggleAddBenef() {
		if (validateBenef()) {
			addBenef();
		}
	}
	function validateBenef() {
		var nombre = dijit.byId('nombre_beneficiario');
		var apellido1 = dijit.byId('apellido_1_beneficiario');
		var parentesco = dijit.byId('idParentescoSelect');
		var edad =  dijit.byId('edad');
		if (!nombre.isValid()) {
			nombre.focus();
			message('El nombre del beneficiario es requerido');
			return false;
		}
		if (!apellido1.isValid()) {
			apellido1.focus();
			message('El apellido paterno del beneficiario es requerido');
			return false;
		}
		if (parentesco.get('value')==0) {
			parentesco.focus();
			message('El parentesco con el beneficiario es requerido');
			return false;
		}
		if (parentesco.get('value')==3 && !edad.isValid()) {
			edad.focus();
			message('Edad para hijos es obligatorio');
			return false;
		}
		return true;
	}
	function addBenef() {
		var nombre = dijit.byId('nombre_beneficiario').get('value');
		var apellido1 = dijit.byId('apellido_1_beneficiario').get('value');
		var apellido2 = dijit.byId('apellido_2_beneficiario').get('value');
		var parentesco = dijit.byId('idParentescoSelect').get('value');
		var edad =  dijit.byId('edad').get('value');
		var action = 'perfilComplementario.do?method=saveBeneficiary&nombre='+nombre+'&apellido1='+apellido1+'&apellido2='+apellido2+'&parentesco='+parentesco+'&edad='+edad;
		dojo.xhrPost({url: action, form:'ProgramaForm', sync: true, preventCache:true, timeout:180000, 
			load: function(data) {
				clearWidgetsAndAddHtml('beneficiaries',data);
		}});
	}
	function changePhoneSizeRequired() {
		var vClave = document.getElementById('clave');
		var vTelefono = document.getElementById('telefono');
		if(vClave.value.length < 2){
			vClave.focus();
			message('Debe proporcionar una clave LADA de 2 ó 3 caracteres');
			return false;
		} else if(vClave.value.length == 2) {
			if(vTelefono.value.length != 8){
				vClave.focus();
				message('Debe proporcionar un teléfono de 8 dígitos');
				return false;
			}
		} else if(vClave.value.length==3){
			if(vTelefono.value.length!=7){
				vClave.focus();
				message('Debe proporcionar un teléfono de 7 dígitos');
				return false;
			}	
		}
		return true;
	}
	function loadFeds() {
		var vEntidad = '${ProgramaForm.perfil.idEntidad}';
		var vMunicipio = '${ProgramaForm.perfil.idMunicipio}';
		var vLocalidad = '${ProgramaForm.perfil.idLocalidad}';
		entidadFederativaStore.fetch({
    		sync: true,
          	onComplete: function(items, request) {
	          	if (items.length == 0) return;
	          	dijit.byId('idEntidadSelect').attr('value', '${ProgramaForm.perfil.idEntidad}');
	    		municipioStore.url = "${context}domicilio.do?method=obtenerMunicipio" + "&" + "idEntidad="+ vEntidad;
	    		municipioStore.close();
	    		municipioStore.fetch({
		    		sync: true,
		         	onComplete: function(items, request) {
		          		if (items.length == 0) return;
		          		dijit.byId('idMunicipioSelect').attr('value', '${ProgramaForm.perfil.idMunicipio}');
		    			coloniaStore.url = "${context}domicilio.do?method=obtenerColonia" + "&" + "idEntidad="+ vEntidad + "&" + "idMunicipio="+ vMunicipio;
		    			coloniaStore.close();
		    			coloniaStore.fetch({
		    				sync: true,
		          			onComplete: function(items, request) {
		          				if (items.length == 0) return;                   	
		          				dijit.byId('idColoniaSelect').attr('value', '${ProgramaForm.perfil.idColonia}');
		          			}
		    			});
		    			localidadStore.url = "${context}domicilio.do?method=obtenerLocalidades" + "&" + "idEntidad="+ vEntidad + "&" + "idMunicipio="+ vMunicipio;
		    		    localidadStore.close();
		    		    localidadStore.fetch({
		    		    	sync: true,
		    		        onComplete: function(items, request) {
		    		        	if (items.length == 0) return;                   	
		    		          	dijit.byId('idLocalidadSelect').attr('value', '${ProgramaForm.perfil.idLocalidad}');
		    		        }
		    		    });
		          	}
	    		});
          	}
    	});
	}
	function validarFormatoCorreo(email) {	
		var regExp = /^[a-zA-Z0-9_-]+(?:\.[a-zA-Z0-9_-]+)*@(?:[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?\.)+[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?$/;
		email.focus();
		if (!regExp.test(email.value)) {
			message('Formato de correo electronico inválido');
			return false;
		}
		return true;
	}
	function noArrastrar(email){
		var aux = email.value;
		email.value = '';
		email.value = aux;
	}
	function fillAccessStand() {
		var accesoDes;
		var tipoTelefonoId = getRadioValue('idTipoTelefonoc');
	    if(tipoTelefonoId == 1) {
	    	accesoDes = <%="'" + Constantes.CLAVE_TELEFONO_CELULAR + "'"%>;
	    	document.getElementById('extensionc').value='';
	    	document.getElementById('extensionc').readOnly=true;
	    }else {
	    	accesoDes = <%="'" + Constantes.CLAVE_TELEFONO_FIJO + "'"%>; 
	    	document.getElementById('extensionc').readOnly=false;  	
	    }	
	    var wAcceso = dijit.byId('accesoc');
	    wAcceso.attr('value',accesoDes);
	}
	function validateDefPhone(phoneIndex) {
		var tipoTelefonoId = getRadioValue('tipoTelefono_' + phoneIndex);
		var tipoTelefono = document.getElementById('clave_' + phoneIndex);
		var clave = document.getElementById('clave_' + phoneIndex);
		var vClave = document.getElementById('clave_' + phoneIndex).value;
		var telefono = document.getElementById('telefono_' + phoneIndex);
		var vTelefono = document.getElementById('telefono_' + phoneIndex).value;
		var extension = document.getElementById('extension_' + phoneIndex);
		var vExtension = document.getElementById('extension_' + phoneIndex).value; 
        if (null == tipoTelefonoId) {
        	tipoTelefono[0].focus();
        	message('Debe seleccionar el tipo de teléfono.');
		return false;
        }
        if (!isNumber(vClave)) {
        	clave.focus();
         	message('La clave LADA debe ser un valor numérico (0-9).');
        	return false;
        }else {
        	if (!vClave || vClave.length<2) {
        		clave.focus();
        		message('Debe proporcionar una clave LADA de 2 ó 3 caracteres.');
        		return false;
	        }else {
				if(vClave.length==2) {
					if(vTelefono.length!=8) {
						telefono.focus();
						message('Debe proporcionar un número telefónico de 8 dígitos.');
        				return false;
					}
				}else if(vClave.length==3) {
					if (vTelefono.length!=7) {
						telefono.focus();
						message('Debe proporcionar un número telefónico de 7 dígitos.');
        				return false;
					}	
				}         
	         }          
         }
         if(!vTelefono) {
         	telefono.focus();
        	message('Debe proporcionar el número telefónico.');
        	return false;
        }else {
        	if(!isNumber(vTelefono)) {
        		telefono.focus();
	        	message('El número telefónico debe ser un valor numérico (0-9).');
        		return false;
	        }       
        }
         if (vExtension && !isNumber(vExtension)) {
         	extension.focus();
         	message('La extensión debe ser un valor numérico (0-9).');
        	return false;
         }
         return true;
	}
	function caracteresValidos(e) {
		var tecla_codigo = (document.all) ? e.keyCode : e.which;
	    if (tecla_codigo==8 || tecla_codigo==0)
	    	return true;
	    if (tecla_codigo==225 || tecla_codigo==233 || tecla_codigo==237 || tecla_codigo==243 || tecla_codigo==250) 
	    	return true; //vocales minusculas con acento
	    if (tecla_codigo==193 || tecla_codigo==201 || tecla_codigo==205 || tecla_codigo==211 || tecla_codigo==218) 
	    	return true; //vocales mayuzculas con acento
	    if (tecla_codigo==209 || tecla_codigo==241 ) 
	    	return true;
	    var patron =/[0-9\-a-zA-Z_ .,:;#]/;
	   	tecla_valor = String.fromCharCode(tecla_codigo);
	   	return patron.test(tecla_valor);
	}  	
	function maximaLongitud(texto, maxlong) {
		var tecla, int_value, out_value;
		if (texto.value.length > maxlong) {
		  	in_value = texto.value.replace(/^\s+|\s*[\r\n][\r\n \t]*/g, "");
		  	out_value = in_value.substring(0, maxlong);
		  	texto.value = out_value;
		  	return false;
		}
		return true;
	}
	function trimSpaces(textarea) {
		s = textarea.value;
		s = s.replace(/(^\s*)|(\s*$)/gi,"");
		s = s.replace(/[ ]{2,}/gi," ");
		s = s.replace(/\n /,"\n");
		textarea.value = s;
	}
</script>
<noscript>Funciones de JavaScript desactivadas por navegador</noscript>
<div dojoType="dojo.data.ItemFileReadStore" jsId="entidadFederativaStore" urlPreventCache="true" clearOnClose="true"></div>
<div dojoType="dojo.data.ItemFileReadStore" jsId="municipioStore" urlPreventCache="true" clearOnClose="true"></div>
<div dojoType="dojo.data.ItemFileReadStore" jsId="coloniaStore" urlPreventCache="true" clearOnClose="true"></div>
<div dojoType="dojo.data.ItemFileReadStore" jsId="localidadStore" urlPreventCache="true" clearOnClose="true"></div>
<div class="formApp miEspacio">
	<form name="ProgramaForm" id="ProgramaForm" method="post" action="perfilComplementario.do" dojoType="dijit.form.Form">
		<input type="hidden" name="method" id="method" value="" />
		<h2>Mi espacio</h2>
		<div class="tab_block">
			<div align="left" id="returnME" style="display: block;">
				<a
					href="javascript:location.replace('<c:url value="/miEspacioCandidato.do"/>');"
					class="expand"> <strong>Ir al inicio de Mi espacio</strong></a>
			</div>
			<div class="Tab_espacio">
				<h3>Portafolio de servicios</h3>
				<div class="subTab">
					<ul>
						<c:forEach var="program" items="${ProgramaForm.path}">
                    		<li class="curSubTab"><a href="${program.url}">${program.nombreCorto}</a></li>
                    	</c:forEach>
                    	<c:if test="${ProgramaForm.modalidad.param.toLowerCase()=='ptat'}">
							<li class="curSubTab"><span>Registro de datos complementarios</span></li>
						</c:if>
					</ul>
					<div class="clearfix"></div>
				</div>
				<div class="clearfix"></div>
			</div>
			<p class="labeled">
				${ProgramaForm.modalidad.nombreCorto}<br>
				<span class="normal">Datos iniciales para el ${ProgramaForm.modalidad.nombre}</span><br>
				Los datos marcados con <span>*</span> son obligatorios
			</p>
		</div>
		<div class="app_holder2">
			<div class="app">
				<div class="datos_personales">
						<c:if test="${ProgramaForm.modalidad.param.toLowerCase()=='ptat'}">
								<fieldset class="data_edit">
									<legend>Datos personales</legend>
									<div class="grid1_3 margin_top_10 fl">
										<label for="idEstadoCivilSelect"><span>*</span> Estado Civil</label>
										<input type="hidden" name="idEstadoCivil" id="idEstadoCivil" dojoType="dijit.form.TextBox">
										<select dojotype="dijit.form.FilteringSelect" id="idEstadoCivilSelect" ${ProgramaForm.perfil.idEstadoCivil < 5 ? 'disabled="disabled"' : '' }
											autocomplete="true" value="${ProgramaForm.perfil.idEstadoCivil}" required="true" missingMessage="Seleccione estado civil" class="${classCivil}">
											<c:forEach var="row" items="${estadoscivil}">
												<option value="${row.idCatalogoOpcion}">${row.opcion}</option>
											</c:forEach>
										</select>
									</div>
									<div class="clearfix"></div>
									<div class="grid1_3 margin_top_10 fl">
										<label for="idEstaturaSelect"><span>*</span> Estatura</label>
										<input type="hidden" name="idEstatura" id="idEstatura" dojoType="dijit.form.TextBox">
										<select dojotype="dijit.form.FilteringSelect" id="idEstaturaSelect" autocomplete="true"
											value="${ProgramaForm.ptat.estatura}" required="true" missingMessage="Seleccione estatura" class="${classCivil}">
											<c:forEach var="row" items="${estatura}">
												<option value="${row.idCatalogoOpcion}">${row.opcion}</option>
											</c:forEach>
										</select>
									</div>
									<div class="grid1_3 margin_top_10 fl">
										<label for="idPesoSelect"><span>*</span> Peso</label>
										<input type="hidden" name="idPeso" id="idPeso" dojoType="dijit.form.TextBox">
										<select dojotype="dijit.form.FilteringSelect" id="idPesoSelect" autocomplete="true"
											value="${ProgramaForm.ptat.peso}" required="true" missingMessage="Seleccione peso" class="${classCivil}">
											<c:forEach var="row" items="${peso}">
												<option value="${row.idCatalogoOpcion}">${row.opcion}</option>
											</c:forEach>
										</select>
									</div>
									<div class="grid1_3 margin_top_10 fl">
										<label for="idComplexionSelect"><span>*</span> Complexión</label>
										<input type="hidden" name="idComplexion" id="idComplexion" dojoType="dijit.form.TextBox">
										<select dojotype="dijit.form.FilteringSelect" id="idComplexionSelect" autocomplete="true"
											value="${ProgramaForm.ptat.idComplexion}" required="true" missingMessage="Dato requerido" class="${classCivil}">
											<c:forEach var="row" items="${complexion}">
												<option value="${row.idCatalogoOpcion}">${row.opcion}</option>
											</c:forEach>
										</select>
									</div>
								</fieldset>
								<fieldset class="data_edit">
									<legend>Datos de domicilio</legend>
									<div class="grid1_3 margin_top_10 fl">
										<label for="calle"><span>*</span> Calle</label>
										<input type="text" dojoType="dijit.form.ValidationTextBox" name="calle" id="calle" maxlength="50" required="true"
											value="${ProgramaForm.perfil.calle}" disabled="disabled" missingMessage="Dato requerido">
									</div>
									<div class="grid1_3 margin_top_10 fl">
										<label for="numeroExterior"><span>*</span> Número exterior</label>
										<input type="text" dojoType="dijit.form.ValidationTextBox" name="numeroExterior" id="numeroExterior" maxlength="50" required="true"
											value="${ProgramaForm.perfil.numeroExterior}" disabled="disabled" missingMessage="Dato requerido">
									</div>
									<div class="grid1_3 margin_top_10 fl">
										<label for="numeroInterior">Número interior</label>
										<input type="text" dojoType="dijit.form.ValidationTextBox" name="numeroInterior" id="numeroInterior" maxlength="50"
											trim ="true" required="false" value="${ProgramaForm.perfil.numeroInterior}" missingMessage="Dato requerido">
									</div>
									<div class="grid1_3 margin_top_10 fl">
										<label for="entreCalle"><span>*</span> Entre calles</label>
										<input type="text" dojoType="dijit.form.ValidationTextBox" name="entreCalle" id="entreCalle" maxlength="50" required="true"
											value="${ProgramaForm.perfil.entreCalle}" trim="true" uppercase="true" missingMessage="Dato requerido">
									</div>
									<div class="grid1_3 margin_top_10 fl">
										<label for="yCalle"><span>*</span> Y calle</label>
										<input type="text" dojoType="dijit.form.ValidationTextBox" name="yCalle" id="yCalle" maxlength="50" required="true"
											value="${ProgramaForm.perfil.yCalle}" trim="true" uppercase="true" missingMessage="Dato requerido">
									</div>
									<div class="clearfix"></div>
									<div class="grid1_3 margin_top_10 fl">
										<label for="idEntidadSelect"><span>*</span> Entidad Federativa</label>
										<input type="hidden" name="idEntidad" id="idEntidad" dojoType="dijit.form.TextBox">
										<select dojotype="dijit.form.FilteringSelect" store="entidadFederativaStore" id="idEntidadSelect" required="true"
											invalidMessage="Dato no válido" missingMessage="Es necesario indicar la entidad." autocomplete="true"
											disabled="disabled" onchange="javascript:actulizaMunicipio();" scrollOnFocus="true" maxHeight="250">
										</select>
									</div>
									<div class="grid1_3 margin_top_10 fl">
										<label for="idMunicipioSelect"><span>*</span> Municipio o Delegación</label>
										<input type="hidden" name="idMunicipio" id="idMunicipio" dojoType="dijit.form.TextBox">
											<select dojotype="dijit.form.FilteringSelect" store="municipioStore" id="idMunicipioSelect" required="true"
												disabled="disabled" invalidMessage="Dato no válido" missingMessage="Es necesario indicar un municipio."
												autocomplete="true" onchange="javascript:actulizaColonia();" scrollOnFocus="true" maxHeight="250">
											</select>
									</div>
									<div class="grid1_3 margin_top_10 fl">
										<label for="idColonia"><span>*</span> Colonia</label>
										<input type="hidden" name="idColonia" id="idColonia" dojoType="dijit.form.TextBox">
											<select dojotype="dijit.form.FilteringSelect" store="coloniaStore" id="idColoniaSelect" required="true"
												invalidMessage="Dato inválido" missingMessage="Es necesario indicar la colonia." autocomplete="true"
												disabled="disabled" onchange="javascript:actulizaCodigoPostal();" scrollOnFocus="true" maxHeight="250">
											</select>
									</div>
									<div class="grid1_3 margin_top_10 fl">
										<label for="idLocalidad"><span>*</span> Localidad</label>
										<input type="hidden" name="idLocalidad" id="idLocalidad" dojoType="dijit.form.TextBox">
										<select dojotype="dijit.form.FilteringSelect" id="idLocalidadSelect" store="localidadStore" required="true" 
											missingMessage="Debe seleccionar la localidad." autocomplete="true" scrollOnFocus="true" maxHeight="250" >
										</select>
									</div>
									<div class="grid1_3 margin_top_10 fl">
										<label for="codigoPostal">Código Postal</label>
										<input type="text" name="codigoPostal" id="codigoPostal" maxlength="5" style="width: 7em;"
											value="${ProgramaForm.perfil.codigoPostal}" dojoType="dijit.form.ValidationTextBox" regExp="^[0-9]{5}"
											invalidMessage="Codigo incorrecto, solo se admiten caracteres numericos." disabled="disabled"
								 			missingMessage="Es necesario indicar el codigo postal." trim="true" required="true"/>
									</div>
									<div class="clearfix"></div>
									<div class="campoTexto margin_top_30">
										<label for="referenciaDomicilio"><span>*</span> Referencia para llegar al domicilio</label>
										<textarea class="textGoogie" rows="4" cols="70" name="referenciaDomicilio" id="referenciaDomicilio" trim="true"
											onkeypress="return caracteresValidos(event);" onchange="return maximaLongitud(this,200)" maxlength="200"
											onKeyUp="return maximaLongitud(this,200)" required="true" onblur="trimSpaces(this)"
											regExp="^[\w\d\s\.&aacute;&eacute;&iacute;&oacute;&uacute;&Aacute;&Eacute;&Iacute;&Oacute;&Uacute;&Ntilde;&ntilde;]{1,200}$">${ProgramaForm.perfil.domicilioReferencia}</textarea>
										<script type="text/javascript">
						         			var vSpellCon = new GoogieSpell("googiespell/", '<%=vProxy%>');
						         			vSpellCon.setLanguages({'es': 'Español'});
					   						vSpellCon.hideLangWindow();
					  						vSpellCon.decorateTextarea("referenciaDomicilio");
					  					</script>
									</div>
								</fieldset>
								<div id="contacts" name="contacts">
									<jsp:include page="/jsp/candidatos/misDatos/contactable.jsp" flush="true" />  
								</div>
								<fieldset class="email_edit">
									<legend>Teléfono de caseta</legend>
									<div class="labeled margin_top_10"><span>*</span> Teléfono</div>
									<p class="labeled">Debe ingresar un total de 10 dígitos (Clave LADA + Teléfono).</p>
									<div class="grid1_3  margin_top_20 fl">
										<div class="margin-r_20 fl">
											<div class="labeled margin_no_t"><span>*</span> Tipo de teléfono</div>	
											<div>
												<div class="tipo_tel margin_top_10 margin-r_10 fl">		
													<input class="fl" type="radio" name="tipoTelefonoc" id="idTipoTelefonoFijoc" value="<%=Constantes.TELEFONO_FIJO%>"
														${ProgramaForm.ptat.caseta.idTipoTelefono eq 5 ? 'checked="checked"' : '' } onclick="fillAccessStand()"/>
													<label class="fl" for="idTipoTelefonoFijoc">Fijo</label>
												</div>
												<div class="tipo_tel margin_top_10 fl"> 
													<input class="fl" type="radio" name="tipoTelefonoc" id="idTipoTelefonoc" value="<%=Constantes.TELEFONO_CELULAR%>"
													${ProgramaForm.ptat.caseta.idTipoTelefono eq 1 ? 'checked="checked"' : '' } onclick="fillAccessStand()"/>
													<label class="fl" for="idTipoTelefonoc">Celular</label>
												</div>
											 </div>					       			       
									    </div>	
									    <div class="margin-r_10 fl">    
											 <label for="accesoc"><span>*</span> Acceso</label>
											 <div class="ta_center">
									             <span class="acceso">
									             	<input type="text" name="accesoc" id="accesoc" value="${ProgramaForm.ptat.caseta.acceso}" maxlength="3" style="width:3em;" dojoType="dijit.form.ValidationTextBox"
									             		required="true" disabled="disabled" regExp='^(<%=Constantes.CLAVE_TELEFONO_CELULAR + "|" + Constantes.CLAVE_TELEFONO_FIJO%>)$' 
									             		invalidMessage="Acceso telefónico inválido" missingMessage="Es necesario indicar el acceso telefónico" trim="true">
									             </span>
								             </div>
										</div>
										<div class="fl">
											<label for="clavec"><span>*</span> Clave lada</label>
								         	<input type="text" name="clavec" id="clavec" dojoType="dijit.form.ValidationTextBox" maxlength="3" style="width:8em;" regExp="^[+]?\d{2,3}$"
								       			value="${ProgramaForm.ptat.caseta.clave}" trim="true" required="true" invalidMessage="Clave lada inválida" missingMessage="Es necesario indicar la clave lada">
										</div>
									</div>
									<div class="margin_top_20 fl">
										<label for="telefonoc"><span>*</span> Teléfono</label>
								     	<input type="text" name="telefonoc" id="telefonoc" dojoType="dijit.form.ValidationTextBox" maxlength="8" style="width:8em;" regExp="^[+]?\d{7,8}$"
								     		value="${ProgramaForm.ptat.caseta.telefono}" trim="true" required="true" invalidMessage="Teléfono inválido" missingMessage="Es necesario indicar el teléfono">
									</div>
									<div class="margin_top_20 fl">
										<label for="extensionc">Extensión</label>
								           <input type="text" name="extensionc" id="extensionc" dojoType="dijit.form.ValidationTextBox" maxlength="8" style="width:8em;" value="${ProgramaForm.ptat.caseta.extension}" required="false"
								           		regExp="^[+]?\d{0,8}$" invalidMessage="Extensión telefónica inválida" missingMessage="Es necesario indicar la extensión telefónica" trim="true">		
									</div>
								</fieldset>
								<fieldset class="email_edit">
									<legend>Correos electrónicos</legend>
									<div class="grid1_3 margin_top_10 fl">
										<label for="email">Correo electrónico 1</label>
								     	<input type="text" name="email" id="email" dojoType="dijit.form.ValidationTextBox"
								     		value="${ProgramaForm.perfil.perfilVO.correoElectronico}" trim="true" required="false" 
								     		disabled="disabled" invalidMessage="Verifique correo electrónico" missingMessage="Es necesario indicar el correo electrónico">
									</div>
									<div class="grid1_3 margin_top_10 fl">
										<label for="emailad">Correo electrónico 2</label>
								     	<input type="text" name="correoElectronico2" id="correoElectronico2" dojoType="dijit.form.ValidationTextBox" trim="true" maxlength="65"
								     		value="${ProgramaForm.ptat.correoElectronico2}" onchange="validarFormatoCorreo(this);" regExp="${regexpmail}"
								     		oncopy="return false;" onpaste="return false;"  missingMessage="Es necesario indicar el correo electrónico." 
								     		onselect="noArrastrar(this);" oncut="return false" invalidMessage="Verifique correo electrónico" required="false">
									</div>
								</fieldset>
								<div id="beneficiaries" name="beneficiaries">
									<jsp:include page="/jsp/candidatos/misDatos/beneficiariestable.jsp" flush="true" />  
								</div>
								<fieldset class="experiencia_edit">
									<legend>Experiencia laboral general y en agricultura</legend>
									<div class="grid1_3 margin_top_10 fl">
										<label for="idExperienciaAgricolaSelect"><span>*</span> ¿Desde cuando realiza labores agrícolas?</label>
										<input type="hidden" name="idExperienciaAgricola" id="idExperienciaAgricola" dojoType="dijit.form.TextBox">
										<select dojotype="dijit.form.FilteringSelect" id="idExperienciaAgricolaSelect" value="${ProgramaForm.ptat.idExperienciaAgricola}" autocomplete="true"
											required="true" missingMessage="Seleccione desde cuando realiza labores agrícolas" class="${classCivil}">
											<c:forEach var="row" items="${experiencia}">
												<option value="${row.idCatalogoOpcion}">${row.opcion}</option>
											</c:forEach>
										</select>
									</div>
									<div class="grid1_3 margin_top_10 fl">
										<label for="idDominioInglesSelect"><span>*</span> Nivel de idioma Inglés</label>
										<input type="hidden" name="idDominioIngles" id="idDominioIngles" dojoType="dijit.form.TextBox">
										<select id="idDominioInglesSelect" dojotype="dijit.form.FilteringSelect" value="${ProgramaForm.idiomaReqIng.idDominio}"
											<c:if test='${ProgramaForm.idiomaReqIng.idCandidatoIdioma > 0}'>disabled="disabled"</c:if> required="true" missingMessage="Es necesario indicar el dominio requerido del idioma">
											<c:forEach var="row" items="${competencia}">
												<option value="${row.idCatalogoOpcion}">${row.opcion}</option>
											</c:forEach>
										</select>
									</div>
									<div class="grid1_3 margin_top_10 fl">
										<label for="idDominioFrancesSelect"><span>*</span> Nivel de idioma Francés</label>
										<input type="hidden" name="idDominioFrances" id="idDominioFrances" dojoType="dijit.form.TextBox">
										<select id="idDominioFrancesSelect" dojotype="dijit.form.FilteringSelect"  value="${ProgramaForm.idiomaReqFrn.idDominio}"
											required="true" missingMessage="Es necesario indicar el dominio requerido del idioma">
											<c:forEach var="row" items="${competencia}">
												<option value="${row.idCatalogoOpcion}">${row.opcion}</option>
											</c:forEach>
										</select>
									</div>
									<div class="grid1_3 margin_top_10 fl">
										<label for="manejoMaquinaria"><span>*</span> ¿Qué tipo de automóvil o maquinaria sabe manejar?</label>
										<select data-dojo-type="dijit/form/MultiSelect" id="manejoMaquinaria" name="manejoMaquinaria" multiple="true"
											size="3" style="width:290px;min-height:78px;">
											<c:forEach var="row" items="${vehicles}">
												<option value="${row.idCatalogoOpcion}" ${row.idCorto}>${row.opcion}</option>
											</c:forEach>
										</select>
									</div>
									<div class="grid1_3 margin_top_10 fl">
										<label for="idLicenciaSelect"><span>*</span> ¿Qué tipo de licencia de conducir tiene?</label>
										<input type="hidden" name="idLicencia" id="idLicencia" dojoType="dijit.form.TextBox">
										<select dojotype="dijit.form.FilteringSelect" id="idLicenciaSelect" autocomplete="true"
											value="${ProgramaForm.perfil.idLicencia}" required="true" missingMessage="Dato requerido">
											<c:forEach var="row" items="${licenciaManejo}">
												<option value="${row.idCatalogoOpcion}">${row.opcion}</option>
											</c:forEach>
										</select>
									</div>
									<div class="grid1_3 margin_top_10 fl">
										<label for="idSituacionLicenciaSelect"><span>*</span>  Situación de licencia</label>
										<input type="hidden" name="idSituacionLicencia" id="idSituacionLicencia" dojoType="dijit.form.TextBox">
										<select dojotype="dijit.form.FilteringSelect" id="idSituacionLicenciaSelect" autocomplete="true"
											value="${ProgramaForm.ptat.licenciaSituacion}" required="true" missingMessage="Dato requerido">
											<c:forEach var="row" items="${situacionlicencia}">
												<option value="${row.idCatalogoOpcion}">${row.opcion}</option>
											</c:forEach>
										</select>
									</div>									
									<div class="grid1_3 margin_top_10 fl">
										<label for="licenciaInternacional"><span>*</span> ¿Estaría dispuesto a obtener la licencia internacional?</label>
										<input type="radio" name="licenciaInternacional" value="2" <c:if test='${ProgramaForm.ptat.licenciaInternacionalDisp==2}'>checked</c:if>/> Sí
										<input type="radio" name="licenciaInternacional" value="1" <c:if test='${ProgramaForm.ptat.licenciaInternacionalDisp==1}'>checked</c:if>/> No
									</div>
									<div class="clearfix"></div>
									<p>Si deseas elegir mas de una opción de una lista utiliza la tecla control + click</p>
									<div class="margin_top_10 fl">
										<label>Campo</label>
										<p class="tres_cuartos fl">
											<label><span>*</span> Verduras y Legumbres</label>
											<select data-dojo-type="dijit/form/MultiSelect" id="legumbresCampo" name="legumbresCampo" multiple="true"
												 size="4" style="width:148px;min-height:78px;">
												<c:forEach var="row" items="${vegetales}">
													<option value="${row.idCatalogoOpcion}" ${row.idCorto}>${row.opcion}</option>
												</c:forEach>
											</select>
										</p>
										<p class="tres_cuartos fl">
											<label for="floresCampo">Flores</label>
											<select data-dojo-type="dijit/form/MultiSelect" id="floresCampo" name="floresCampo" multiple="true"
												size="4" style="width:148px;min-height:78px;">
												<c:forEach var="row" items="${flores}">
													<option value="${row.idCatalogoOpcion}" ${row.idCorto}>${row.opcion}</option>
												</c:forEach>
											</select>
										</p>
										<p class="tres_cuartos fl">
											<label for="arbolesCampo"><span>*</span> Arboles</label>
											<select data-dojo-type="dijit/form/MultiSelect" id="arbolesCampo" name="arbolesCampo" multiple="true"
												size="4" style="width:148px;min-height:78px;">
												<c:forEach var="row" items="${arboles}">
													<option value="${row.idCatalogoOpcion}" ${row.idCorto}>${row.opcion}</option>
												</c:forEach>
											</select>
										</p>
										<p class="tres_cuartos fl">
											<label for="frutas"><span>*</span> Frutas</label>
											<select data-dojo-type="dijit/form/MultiSelect" id="frutas" name="frutas" multiple="true"
												size="4" style="width:148px;min-height:78px;">
												<c:forEach var="row" items="${frutas}">
													<option value="${row.idCatalogoOpcion}" ${row.idCorto}>${row.opcion}</option>
												</c:forEach>
											</select>
										</p>
										<p class="tres_cuartos fl">
											<label for="tabaco"><span>*</span> Tabaco</label>
											<select data-dojo-type="dijit/form/MultiSelect" id="tabaco" name="tabaco" multiple="true"
												size="4" style="width:148px;min-height:78px;">
												<c:forEach var="row" items="${tabaco}">
													<option value="${row.idCatalogoOpcion}" ${row.idCorto}>${row.opcion}</option>
												</c:forEach>
											</select>
										</p>
										<p class="tres_cuartos fl">
											<label for="cereales"><span>*</span> Cereales</label>
											<select data-dojo-type="dijit/form/MultiSelect" id="cereales" name="cereales" multiple="true"
												size="4" style="width:148px;min-height:78px;">
												<c:forEach var="row" items="${cereales}">
													<option value="${row.idCatalogoOpcion}" ${row.idCorto}>${row.opcion}</option>
												</c:forEach>
											</select>
										</p>
									</div>
									<div class="margin_top_10 fl">
										<label>Vivero e invernadero</label>
										<p class="tres_cuartos fl">
											<label for="legumbresViv"><span>*</span> Verduras y Legumbres</label>
											<select data-dojo-type="dijit/form/MultiSelect" id="legumbresViv" name="legumbresViv" multiple="true"
												 size="4" style="width:148px;min-height:78px;">
												<c:forEach var="row" items="${vegetales}">
													<option value="${row.idCatalogoOpcion}" ${row.idCorto}>${row.opcion}</option>
												</c:forEach>
											</select>
										</p>
										<p class="tres_cuartos fl">
											<label for="floresViv">Flores</label>
											<select data-dojo-type="dijit/form/MultiSelect" id="floresViv" name="floresViv" multiple="true"
												size="4" style="width:148px;min-height:78px;">
												<c:forEach var="row" items="${flores}">
													<option value="${row.idCatalogoOpcion}" ${row.idCorto}>${row.opcion}</option>
												</c:forEach>
											</select>
										</p>
										<p class="tres_cuartos fl">
											<label for="arbolesViv"><span>*</span> Arboles</label>
											<select data-dojo-type="dijit/form/MultiSelect" id="arbolesViv" name="arbolesViv" multiple="true"
												size="4" style="width:148px;min-height:78px;">
												<c:forEach var="row" items="${arboles}">
													<option value="${row.idCatalogoOpcion}" ${row.idCorto}>${row.opcion}</option>
												</c:forEach>
											</select>
										</p>
									</div>
									<div class="margin_top_10 fl">
										<label>Sector pecuario</label>
										<p class="tres_cuartos fl">
											<label for="ganado"><span>*</span> Cuidado de ganado</label>
											<select data-dojo-type="dijit/form/MultiSelect" id="ganado" name="ganado" multiple="true"
												size="4" style="width:148px;min-height:78px;">
												<c:forEach var="row" items="${ganado}">
													<option value="${row.idCatalogoOpcion}" ${row.idCorto}>${row.opcion}</option>
												</c:forEach>
											</select>
										</p>
										<p class="tres_cuartos fl">
											<label for="rastro"><span>*</span> Rastro</label>
											<select data-dojo-type="dijit/form/MultiSelect" id="rastro" name="rastro" multiple="true"
												size="4" style="width:148px;min-height:78px;">
												<c:forEach var="row" items="${rastro}">
													<option value="${row.idCatalogoOpcion}" ${row.idCorto}>${row.opcion}</option>
												</c:forEach>
											</select>
										</p>
									</div>
									<div class="clearfix"></div>
									<div class="margin_top_10 fl">
										<p class="tres_cuartos fl">
											<label for="apicultura"><span>*</span> Apicultura</label>
											<select data-dojo-type="dijit/form/MultiSelect" id="apicultura" name="apicultura" multiple="true"
												size="4" style="width:140px;min-height:78px;">
												<c:forEach var="row" items="${apicultura}">
													<option value="${row.idCatalogoOpcion}" ${row.idCorto}>${row.opcion}</option>
												</c:forEach>
											</select>
										</p>
									</div>
									<div class="margin_top_10 fl">
										<p class="tres_cuartos fl">
											<label for="otros_rubros"><span>*</span> Otros rubros relacionados</label>
											<select data-dojo-type="dijit/form/MultiSelect" id="otros_rubros" name="otros_rubros" multiple="true"
												size="4" style="width:314px;min-height:78px;">
												<c:forEach var="row" items="${otros}">
													<option value="${row.idCatalogoOpcion}" ${row.idCorto}>${row.opcion}</option>
												</c:forEach>
											</select>
										</p>
									</div>
									<div class="clearfix"></div>
									<div class="campoTexto margin_top_30">
										<label for="otrosTiposCultivo">¿Existen otros tipos de cultivos que conozcas?</label>
										<textarea class="textGoogie" rows="4" cols="70" name="otrosTiposCultivo" id="otrosTiposCultivo" trim="true" 
											onkeypress="return caracteresValidos(event);" onchange="return maximaLongitud(this,200)" maxlength="200"
											onKeyUp="return maximaLongitud(this,200)" required="false"
											regExp="^[\w\d\s\.&aacute;&eacute;&iacute;&oacute;&uacute;&Aacute;&Eacute;&Iacute;&Oacute;&Uacute;&Ntilde;&ntilde;]{1,200}$">${ProgramaForm.ptat.otrosTiposCultivo}</textarea>
										<script type="text/javascript">
						         			var vSpellCon = new GoogieSpell("googiespell/", '<%=vProxy%>');
						         			vSpellCon.setLanguages({'es': 'Español'});
					   						vSpellCon.hideLangWindow();
					  						vSpellCon.decorateTextarea("otrosTiposCultivo");
					  					</script>
									</div>
								</fieldset>
								<fieldset class="requisites_edit">
									<legend>Historia clínica</legend>
									<div class="margin_top_10 fl">
										<p class="tres_cuartos fl">
											<label><span>*</span> Enfermedades contagiosas</label>
												<select data-dojo-type="dijit/form/MultiSelect" id="contagiosas" name="contagiosas" multiple="true" size="4" 
													style="width:225px;min-height:78px;">
													<c:forEach var="row" items="${contagiosas}">
														<option value="${row.idCatalogoOpcion}" ${row.idCorto}>${row.opcion}</option>
													</c:forEach>
												</select>
										</p>
										<p class="tres_cuartos fl">
											<label><span>*</span> Enfermedades crónicas</label>
												<select data-dojo-type="dijit/form/MultiSelect" id="cronicas" name="cronicas" multiple="true" size="4" 
													style="width:225px;min-height:78px;">
													<c:forEach var="row" items="${cronicas}">
														<option value="${row.idCatalogoOpcion}" ${row.idCorto}>${row.opcion}</option>
													</c:forEach>
												</select>
										</p>
										<p class="tres_cuartos fl">
											<label><span>*</span> Limitaciones físicas</label>
												<select data-dojo-type="dijit/form/MultiSelect" id="fisicas" name="fisicas" multiple="true" size="4" 
													style="width:225px;min-height:78px;">
													<c:forEach var="row" items="${fisicas}">
														<option value="${row.idCatalogoOpcion}" ${row.idCorto}>${row.opcion}</option>
													</c:forEach>
												</select>
										</p>
										<p class="tres_cuartos fl">
											<label><span>*</span> Enfermedades o lesiones</label>
												<select data-dojo-type="dijit/form/MultiSelect" id="lesiones" name="lesiones" multiple="true" size="4" 
													style="width:225px;min-height:78px;">
													<c:forEach var="row" items="${lesiones}">
														<option value="${row.idCatalogoOpcion}" ${row.idCorto}>${row.opcion}</option>
													</c:forEach>
												</select>
										</p>
									</div>
									<div class="margin_top_10 fl">
										<p class="grid1_3 fl">
											<label for="cuestionario"><span>*</span> ¿Cuenta con cirugías, amputaciones o fracturas?</label>
											<input type="radio" name="tieneIntervenciones" value="2" <c:if test='${ProgramaForm.ptat.tieneIntervencionesMedicas==2}'>checked</c:if>/> Sí<br>
											<input type="radio" name="tieneIntervenciones" value="1" <c:if test='${ProgramaForm.ptat.tieneIntervencionesMedicas==1}'>checked</c:if>/> No
										</p>
									</div>
								</fieldset>
						</c:if>
				</div>
			</div>
		</div>
		<div class="form_nav">
			<input type="button" id="btnActualizar" name="btnActualizar" class="boton_naranja" onclick="validateForm();" value="Solicitar participación">
			<input type="button" class="boton_naranja" onclick="doSubmitCancel();" value="Cancelar">
		</div>
	</form>
</div>