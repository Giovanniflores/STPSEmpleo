<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="mx.gob.stps.portal.web.candidate.form.ProgramaForm, mx.gob.stps.portal.core.infra.utils.Constantes, java.util.Calendar, java.util.Date, java.text.DateFormat, java.text.SimpleDateFormat, java.text.ParseException"%>
<%
	String context = request.getContextPath() +"/";
	String vProxy = "http://orangoo.com/newnox?lang=";
	pageContext.setAttribute("vProxy",vProxy);
	String fechaIngresoInst = null != session.getAttribute("fechaIngresoInst") ? (String)session.getAttribute("fechaIngresoInst") : "";
	String fechaTerminacionInst = null != session.getAttribute("fechaTerminacionInst") ? (String)session.getAttribute("fechaTerminacionInst") : "";
%>
<script type="text/javascript" src="js/helper/messageHelper.js"></script>
<script type="text/javascript">
	var depGrado  = new Array(); //Declara arreglo de dependencias de catalogo Grado de Estudios
	<% //Declara arreglo de dependencias de catalogo Grado de Estudios
		String[] depGrado = session.getAttribute("depGrado") != null  ? (String[]) session.getAttribute("depGrado") : new String[0];
		for (int i = 0; i < depGrado.length; i++) { %>
			<%="depGrado[" + i + "] = '" + depGrado[i] + "';"%>
	<%}%>
</script>
<script>
	dojo.addOnLoad(function() {
    	var vCodigoPostal = dojo.byId('codigoPostal').value;
    	entidadFedStore.url = "${context}domicilio.do?method=obtenerEntidad";
	    entidadFedStore.close();
	    loadFeds();
		dojo.connect(dijit.byId('idNivelEstudioSelect'), 'onChange', function() {
			var vGrado = dijit.byId('idNivelEstudioSelect').get('value');
			var vIdCatDep = depGrado[vGrado];
            if (vIdCatDep != 0 || vIdCatDep != undefined) {
            	dijit.byId('idCarreraEspecialidadSelect').reset();
            	carreraStore.url = '${context}grados.do?method=cargarCarrera&idCatDep='+ vIdCatDep;
            	carreraStore.close();
			}
			estatusGradoStore.url = '';
    		estatusGradoStore.close();
    		document.getElementById('idSituacionAcademicaSelect').value='';	
    		estatusGradoStore.url = '${context}grados.do?method=situacionesAcademicas&idEscolaridad='+ vGrado;
    		estatusGradoStore.close();
        });
		loadDegree();
		hasLicense();
		setRequiredJob();
		hasTest();
	});
</script>
<script type="text/javascript">
    dojo.require("dijit.dijit");
    dojo.require("dijit.form.Form");
    dojo.require("dijit.form.CheckBox");
    dojo.require("dijit/form/MultiSelect");
    dojo.require("dijit.form.FilteringSelect");
    dojo.require("dojo.data.ItemFileReadStore");
    dojo.require("dijit.form.ValidationTextBox");
    function doSubmmitDataComp() {
    	dialogMsgConfirmMML.hide();
		document.ProgramaForm.method.value = 'savemml';
		document.ProgramaForm.idLocalidad.value = dijit.byId('idLocalidadSelect').get('value');
		document.ProgramaForm.idDominioReq.value = dijit.byId('idDominioIdioma').get('value');
		document.ProgramaForm.idCertificacionReq.value = dijit.byId('idCertificacionIdioma').get('value');
		document.ProgramaForm.idDominioHabla.value = dijit.byId('idHabloIdiomaSelect').get('value');
		document.ProgramaForm.idDominioEscrito.value = dijit.byId('idEscriboIdiomaSelect').get('value');
		document.ProgramaForm.idDominioLectura.value = dijit.byId('idLeoIdiomaSelect').get('value');
		document.ProgramaForm.examenPresentado.value = getRadioValue('examenPresentadoRadio');
		document.ProgramaForm.examenId.value = dijit.byId('idExamenSelect').get('value');
		document.ProgramaForm.examenPuntos.value = dijit.byId('examenPuntosSelect').get('value');
		document.ProgramaForm.idJerarquia.value = dijit.byId('idJerarquiaSelect').get('value');
		document.ProgramaForm.personasCargo.value = dijit.byId('personasCargoSelect').get('value');
		document.ProgramaForm.idEntidadEmpresa.value = dijit.byId('idEntidadEmpresaSelect').get('value');
		document.ProgramaForm.idMunicipioEmpresa.value = dijit.byId('idMunicipioEmpresaSelect').get('value');
		document.ProgramaForm.idColoniaEmpresa.value = dijit.byId('idColoniaEmpresaSelect').get('value');
		document.ProgramaForm.idLocalidadEmpresa.value = dijit.byId('idLocalidadEmpresaSelect').get('value');
		document.ProgramaForm.tipoTelefono.value = getRadioValue('tipoTelefono');
		document.ProgramaForm.licManejoTiene.value = getRadioValue('licManejoTiene');
		document.ProgramaForm.licManejoTipo.value = dijit.byId('idLicenciaManejoSelect').get('value');
		document.ProgramaForm.disponibilidadRadicarPais.value =  getRadioValue('disponibilidadRadicarPais');
		var fechaIngresoDia=dijit.byId('fechaIngresoDia').get('value');
		var fechaIngresoDiaMes=dijit.byId('fechaIngresoDiaMes').get('value');
		var fechaIngresoAnual=dijit.byId('fechaIngresoAnual').get('value');
		var fechaIngreso = fechaIngresoDia + '/' + fechaIngresoDiaMes + '/' + fechaIngresoAnual
		if (fechaIngresoDia != 0)
			document.ProgramaForm.fechaIngreso.value = fechaIngreso;	
		var fechaTerminacionDia=dijit.byId('fechaTerminacionDia').get('value');
		var fechaTerminacionMes=dijit.byId('fechaTerminacionMes').get('value');
		var fechaTerminacionAnual=dijit.byId('fechaTerminacionAnual').get('value');
		var fechaTerminacion = fechaTerminacionDia + '/' + fechaTerminacionMes + '/' + fechaTerminacionAnual
		if (fechaTerminacionDia != 0)
			document.ProgramaForm.fechaTerminacion.value = fechaTerminacion;
		var laboresInicialDia=document.getElementById('laboresInicialDia').value;
		var laboresInicialMes=dijit.byId('laboresInicialMes').get('value');
		var laboresInicialAnual=document.getElementById('laboresInicialAnual').value;
		var laboresInicial = laboresInicialDia + '/' + laboresInicialMes + '/' + laboresInicialAnual
		if (laboresInicialDia != 0)
			document.ProgramaForm.laboresInicial.value = laboresInicial;
		var laboresFinalDia=document.getElementById('laboresFinalDia').value;
		var laboresFinalMes=dijit.byId('laboresFinalMes').get('value');
		var laboresFinalAnual=document.getElementById('laboresFinalAnual').value;
		var laboresFinal = laboresFinalDia + '/' + laboresFinalMes + '/' + laboresFinalAnual
		if (laboresFinalDia != 0)
			document.ProgramaForm.laboresFinal.value = laboresFinal;
		var fechaExamenDia=document.getElementById('fechaExamenDia').value;
		var fechaExamenMes=dijit.byId('fechaExamenMes').get('value');
		var fechaExamenAnual=document.getElementById('fechaExamenAnual').value;
		var examenFecha = fechaExamenDia + '/' + fechaExamenMes + '/' + fechaExamenAnual
		if (fechaExamenDia != 0)
			document.ProgramaForm.examenFecha.value = examenFecha;
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
	function loadDegree() {
		setAcademic();
		setDegree();
	}
	function loadFeds() {
		var vEntidad = '${ProgramaForm.perfil.idEntidad}';
		var vMunicipio = '${ProgramaForm.perfil.idMunicipio}';
		var vColonia = '${ProgramaForm.perfil.idColonia}';
		entidadFedStore.fetch({
    		sync: true,
          	onComplete: function(items, request) {
	          	if (items.length == 0) return;
	          	dijit.byId('idEntidadSelect').attr('value', '${ProgramaForm.perfil.idEntidad}');
	    		municipioFedStore.url = "${context}domicilio.do?method=obtenerMunicipio" + "&" + "idEntidad="+ vEntidad;
	    		municipioFedStore.close();
	    		municipioFedStore.fetch({
		    		sync: true,
		         	onComplete: function(items, request) {
		          		if (items.length == 0) return;
		          		dijit.byId('idMunicipioSelect').attr('value', '${ProgramaForm.perfil.idMunicipio}');
		    			coloniaFedStore.url = "${context}domicilio.do?method=obtenerColonia" + "&" + "idEntidad="+ vEntidad + "&" + "idMunicipio="+ vMunicipio;
		    			coloniaFedStore.close();
		    			coloniaFedStore.fetch({
		    				sync: true,
		          			onComplete: function(items, request) {
		          				if (items.length == 0) return;                   	
		          				dijit.byId('idColoniaSelect').attr('value', '${ProgramaForm.perfil.idColonia}');
		          			}
		    			});
		    			localidadFedStore.url = "${context}domicilio.do?method=obtenerLocalidades" + "&" + "idEntidad="+ vEntidad + "&" + "idMunicipio="+ vMunicipio;
		    		    localidadFedStore.close();
		    		    localidadFedStore.fetch({
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
	function setAcademic(){
		gradoAcademicoStore.fetch({
 			onComplete: function(items, request) {
		      if (items.length == 0) {
		         return;
		      }
		      var vGrado = '${ProgramaForm.ultimoGrado.idNivelEstudio}';
		      dijit.byId('idNivelEstudioSelect').attr('value', vGrado);
		      var vIdCatDep = depGrado[vGrado];
		      if(vIdCatDep != 0 || vIdCatDep != undefined) {
	          	carreraStore.url = '${context}grados.do?method=cargarCarrera' + '&' + 'idCatDep='+ vIdCatDep;
	          	carreraStore.close();
	          	carreraStore.fetch({
 					onComplete: function(items, request) {
						if (items.length == 0) {
				        	return;
				    	}
				    	dijit.byId('idCarreraEspecialidadSelect').attr('value', '${ProgramaForm.ultimoGrado.idCarreraEspecialidad}');
 					}
				});
			  }
 			}
		});
	}
	function setDegree() {	      
		var vGrado = '${ProgramaForm.ultimoGrado.idNivelEstudio}';
		estatusGradoStore.url = "${context}grados.do?method=situacionesAcademicas&idEscolaridad="+ vGrado;
		estatusGradoStore.close();
		estatusGradoStore.fetch({
         	onComplete: function(items, request) {
         		if (items.length == 0) return;
         		dijit.byId('idSituacionAcademicaSelect').set('value','${ProgramaForm.ultimoGrado.idSituacionAcademica}');
         		
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
    function messageConfirmMML(msg) {
		var html =
			'<div id="messageDialgopM" name="messageDialgopM">' +
			'<p style="text-align: center;">'+ msg +'</p>'+
			'<p class="form_nav">' + 
			'<input id="btnRenv" class="boton_naranja" type="button" name="btnRenv" onClick="doSubmmitDataComp()" value="Aceptar"/>' +
			'<input id="btnCanc" class="boton_naranja" type="button" name="btnCanc" onClick="dialogMsgConfirmMML.hide();" value="Cancelar"/>' +
    		'</p>' +
    		'</div>';
		dialogMsgConfirmMML = new dijit.Dialog({
	        title: 'Mensaje',
	        content: html,
	        style: "width:300px;",
	        showTitle: false, draggable : false, closable : false,
	    });	
		dialogMsgConfirmMML.show();
	}
	function doSubmitCancel() {
		messageRutaCancel('Los datos no guardados se perderán ¿Continuar?','<c:url value="/miEspacioCandidato.do"/>');
	}
	function validateForm() {
		if (!validateExtAdr()) return false;
		if (!validateLang()) return false;
		if (!validateLevel()) return false;
		if (!validateLastJob()) return false;
		if (!validateReference()) return false;
		if (!validatePhone()) return false;
		if (!validateLastDegree()) return false;
		if (!validateOtherStudiesList()) return false;
		if (!validateComplementData()) return false;
		messageConfirmMML('¿Está seguro de guardar datos complementarios?');
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
	function validateLang() {
		var idHabloIdiomaSelect = dijit.byId('idHabloIdiomaSelect');
		var idEscriboIdiomaSelect = dijit.byId('idEscriboIdiomaSelect');
		var idLeoIdiomaSelect = dijit.byId('idLeoIdiomaSelect');
		if (idHabloIdiomaSelect.get('value')==0) {
			idHabloIdiomaSelect.focus();
			message('Seleccione dominio habla del idioma.');
			return false;
		}
		if (idEscriboIdiomaSelect.get('value')==0) {
			idEscriboIdiomaSelect.focus();
			message('Seleccione dominio escritura del idioma.');
			return false;
		}
		if (idLeoIdiomaSelect.get('value')==0) {
			idLeoIdiomaSelect.focus();
			message('Seleccione dominio lectura del idioma.');
			return false;
		}
		return true;
	}
	function validateLevel() {
		var required = false;
		var examenPresentado = getRadioValue('examenPresentadoRadio');
		var idExamenSelect = dijit.byId('idExamenSelect');
		var fechaExamenDia = dijit.byId('fechaExamenDia');
		var fechaExamenMes = dijit.byId('fechaExamenMes');
		var fechaExamenAnual = dijit.byId('fechaExamenAnual');
		var examenPuntosSelect = dijit.byId('examenPuntosSelect');
		var idNivelEqCanadienseSelect = dijit.byId('idNivelEqCanadienseSelect');
		if (null == examenPresentado) {
			document.ProgramaForm.examenPresentadoRadio[0].focus();
	        	message('Debe seleccionar si presentó algún examen.');
			return false;
	    }
		if (examenPresentado == 2) {
			required = true;
		}
		if (required) {
			if (idExamenSelect.get('value')==0) {
				idExamenSelect.focus();
				message('Seleccione examen.');
				return false;
			}
			if (fechaExamenDia.get('value')==0) {
				fechaExamenDia.focus();
				message('Seleccione dia fecha de examen.');
				return false;
			}
			if (fechaExamenMes.get('value')==0) {
				fechaExamenMes.focus();
				message('Seleccione mes fecha de examen.');
				return false;
			}
			if (fechaExamenAnual.get('value')==0) {
				fechaExamenAnual.focus();
				message('Seleccione año fecha de examen.');
				return false;
			}
			if (!validateExDate(fechaExamenDia.get('value'), fechaExamenMes.get('value'), fechaExamenAnual.get('value'), fechaExamenDia)) return false;
			if (examenPuntosSelect.get('value')==0) {
				examenPuntosSelect.focus();
				message('Puntos del examen es requerido.');
				return false;
			}
			if (idNivelEqCanadienseSelect.get('value')==0) {
				idNivelEqCanadienseSelect.focus();
				message('Nivel de equivalencia canadiense es requerido.');
				return false;
			}
		}
		return true;
	}
	function validateLastJob() {
		var required = false;
		var empresa = dijit.byId('empresa');
		var puesto = dijit.byId('puesto');
		var idJerarquiaSelect = dijit.byId('idJerarquiaSelect');
		var personasCargoSelect = dijit.byId('personasCargoSelect');
		var salarioMensual = dijit.byId('salarioMensual');
		var laboresInicialDia = dijit.byId('laboresInicialDia');
		var laboresInicialMes = dijit.byId('laboresInicialMes');
		var laboresInicialAnual = dijit.byId('laboresInicialAnual');
		var laboresFinalDia = dijit.byId('laboresFinalDia');
		var laboresFinalMes = dijit.byId('laboresFinalMes');
		var laboresFinalAnual = dijit.byId('laboresFinalAnual');
		var funcion = document.getElementById('funcion');
		var calleEmpresa = dijit.byId('calleEmpresa');
		var numExtEmpresa = dijit.byId('numExtEmpresa');
		var numIntEmpresa = dijit.byId('numIntEmpresa');
		var idEntidadEmpresaSelect = dijit.byId('idEntidadEmpresaSelect');
		var idMunicipioEmpresaSelect = dijit.byId('idMunicipioEmpresaSelect');
		var idColoniaEmpresaSelect = dijit.byId('idColoniaEmpresaSelect');
		var idEntidadEmpresaSelect = dijit.byId('idEntidadEmpresaSelect');
		var idLocalidadEmpresaSelect = dijit.byId('idLocalidadEmpresaSelect');
		var herramientas = document.getElementById('herramientas');
		if (empresa.get('value')!='') {
			required = true;
		}
		if (required) {
			if (puesto.get('value')=='') {
				puesto.focus();
				message('Puesto desempeñado es requerido.');
				return false;
			}
			if (idJerarquiaSelect.get('value')==0) {
				idJerarquiaSelect.focus();
				message('Seleccione jerarquía del puesto.');
				return false;
			}
			if (personasCargoSelect.get('value')==0) {
				personasCargoSelect.focus();
				message('Seleccione número de personas a cargo.');
				return false;
			}
			if (salarioMensual.get('value')=='') {
				salarioMensual.focus();
				message('Salario mensual recibido es requerido.');
				return false;
			}
			if (laboresInicialDia.get('value')==0) {
				laboresInicialDia.focus();
				message('Seleccione dia fecha de ingreso.');
				return false;
			}
			if (laboresInicialMes.get('value')==0) {
				laboresInicialMes.focus();
				message('Seleccione mes fecha de ingreso.');
				return false;
			}
			if (laboresInicialAnual.get('value')==0) {
				laboresInicialAnual.focus();
				message('Seleccione año fecha de ingreso.');
				return false;
			}
			if (laboresFinalDia.get('value')==0) {
				laboresFinalDia.focus();
				message('Seleccione dia fecha de terminación.');
				return false;
			}
			if (laboresFinalMes.get('value')==0) {
				laboresFinalMes.focus();
				message('Seleccione mes fecha de terminación.');
				return false;
			}
			if (laboresFinalAnual.get('value')==0) {
				laboresFinalAnual.focus();
				message('Seleccione año fecha de terminación.');
				return false;
			}
			if (funcion.value.trim()=='') {
				funcion.focus();
				message('Funciones desempeñadas es requerido.');
				return false;
			}
			if (calleEmpresa.get('value')=='') {
				calleEmpresa.focus();
				message('Calle es requerido.');
				return false;
			}
			if (numExtEmpresa.get('value')=='') {
				numExtEmpresa.focus();
				message('Número exterior es requerido.');
				return false;
			}
			if (idEntidadEmpresaSelect.get('value')==0) {
				idEntidadEmpresaSelect.focus();
				message('Seleccione entidad Federativa.');
				return false;
			}
			if (idMunicipioEmpresaSelect.get('value')==0) {
				idMunicipioEmpresaSelect.focus();
				message('Seleccione municipio o Delegación.');
				return false;
			}
			if (idColoniaEmpresaSelect.get('value')==0) {
				idColoniaEmpresaSelect.focus();
				message('Seleccione colonia.');
				return false;
			}
			if (idLocalidadEmpresaSelect.get('value')==0) {
				idLocalidadEmpresaSelect.focus();
				message('Seleccione localidad.');
				return false;
			}
			if (herramientas.value=='') {
				herramientas.focus();
				message('Herramientas es requerido.');
				return false;
			}
		}
		return true;
	}
	function validateReference() {
		var nombreReferencia = dijit.byId('nombreReferencia');
		var primerApellidoReferencia = dijit.byId('primerApellidoReferencia');
		var segundoApellidoReferencia = dijit.byId('segundoApellidoReferencia');
		var puestoReferencia = dijit.byId('puestoReferencia');
		if (nombreReferencia.value=='') {
			nombreReferencia.focus();
			message('Nombre es requerido.');
			return false;
		}
		if (primerApellidoReferencia.value=='') {
			primerApellidoReferencia.focus();
			message('Primer apellido es requerido.');
			return false;
		}
		if (puestoReferencia.value=='') {
			puestoReferencia.focus();
			message('Puesto desempeñado es requerido.');
			return false;
		}
		return true;
	}
	function validatePhone() {
		var tipoTelefonoId = getRadioValue('tipoTelefono');
		var vClave = document.getElementById('clave').value;
        var vTelefono = document.getElementById('telefono').value; 
        var vExtension = document.getElementById('extension').value; 
        if (null == tipoTelefonoId) {
        	document.ProgramaForm.tipoTelefono[0].focus();
        	message('Debe seleccionar el tipo de teléfono.');
			return false;
        }
        if (!isNumber(vClave)) {
        	document.ProgramaForm.clave.focus();
         	message('La clave LADA debe ser un valor numérico (0-9).');
        	return false;
        }else {
        	if (!vClave || vClave.length<2) {
        		document.ProgramaForm.clave.focus();
        		message('Debe proporcionar una clave LADA de 2 ó 3 caracteres.');
        		return false;
	        }else {
				if(vClave.length==2) {
					if(vTelefono.length!=8) {
						document.ProgramaForm.clave.focus();
						message('Debe proporcionar un número telefónico de 8 dígitos.');
        				return false;
					}
				}else if(vClave.length==3) {
					if (vTelefono.length!=7) {
						document.ProgramaForm.clave.focus();
						message('Debe proporcionar un número telefónico de 7 dígitos.');
        				return false;
					}	
				}         
	         }          
         }
         if(!vTelefono) {
         	document.ProgramaForm.telefono.focus();
        	message('Debe proporcionar el número telefónico.');
        	return false;
        }else {
        	if(!isNumber(vTelefono)) {
        		document.ProgramaForm.telefono.focus();
	        	message('El número telefónico debe ser un valor numérico (0-9).');
        		return false;
	        }       
        }
         if (vExtension && !isNumber(vExtension)) {
         	document.ProgramaForm.extension.focus();
         	message('La extensión debe ser un valor numérico (0-9).');
        	return false;
         }
         return true;
	}
	function validateLastDegree() {
		var institucion = dijit.byId('institucion');
		var fechaIngresoDia = dijit.byId('fechaIngresoDia');
		var fechaIngresoDiaMes = dijit.byId('fechaIngresoDiaMes');
		var fechaIngresoAnual = dijit.byId('fechaIngresoAnual');
		var fechaTerminacionDia = dijit.byId('fechaTerminacionDia');
		var fechaTerminacionMes = dijit.byId('fechaTerminacionMes');
		var fechaTerminacionAnual = dijit.byId('fechaTerminacionAnual');
		var lugar = dijit.byId('lugar');
		if (institucion.get('value')=='') {
			institucion.focus();
			message('Institución es requerido.');
			return false;
		}
		if (fechaIngresoDia.get('value')==0) {
			fechaIngresoDia.focus();
			message('Seleccione dia fecha de ingreso.');
			return false;
		}
		if (fechaIngresoDiaMes.get('value')==0) {
			fechaIngresoDiaMes.focus();
			message('Seleccione mes fecha de ingreso.');
			return false;
		}
		if (fechaIngresoAnual.get('value')==0) {
			fechaIngresoAnual.focus();
			message('Seleccione año fecha de ingreso.');
			return false;
		}
		if (fechaTerminacionDia.get('value')==0) {
			fechaTerminacionDia.focus();
			message('Seleccione dia fecha de terminación.');
			return false;
		}
		if (fechaTerminacionMes.get('value')==0) {
			fechaTerminacionMes.focus();
			message('Seleccione mes fecha de terminación.');
			return false;
		}
		if (fechaTerminacionAnual.get('value')==0) {
			fechaTerminacionAnual.focus();
			message('Seleccione año fecha de terminación.');
			return false;
		}
		if (!validateDatesLevel()) {
			return false;
		}
		if (lugar.get('value')=='') {
			lugar.focus();
			message('Ciudad, País es requerido.');
			return false;
		}
		return true;
	}
	function validateComplementData() {
		var objetivos = document.getElementById('objetivos');
		var licenciaManejo = getRadioValue('licManejoTiene');
		var idLicenciaManejoSelect = dijit.byId('idLicenciaManejoSelect');
		var disponibilidadRadicarPais = getRadioValue('disponibilidadRadicarPais');
		if (objetivos.value=='') {
			objetivos.focus();
			message('Objetivos Personales es requerido.');
			return false;
		}
		if (licenciaManejo == 2) {
			if (idLicenciaManejoSelect.get('value')==0) {
				idLicenciaManejoSelect.focus();
				message('Seleccione qué tipo de licencia tiene.');
				return false;
			}
		}
		if (null == disponibilidadRadicarPais) {
			document.ProgramaForm.disponibilidadRadicarPais[0].focus();
			message('Seleccione si puede radicar en otro país.');
			return false;
		}
		return true;
	}
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
	function isNumber(n) {
  		return !isNaN(parseFloat(n)) && isFinite(n);
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
	function actulizaMunicipio() {
		var vEntidad = dijit.byId('idEntidadSelect').get('value');
		if (vEntidad) {
			municipioFedStore.url = '';
			municipioFedStore.close();			

			coloniaFedStore.url = '';
			coloniaFedStore.close();			
		
			dojo.byId('codigoPostal').value = '';
			dijit.byId('idMunicipioSelect').reset();
			dijit.byId('idColoniaSelect').reset();
			municipioFedStore.url = "${context}domicilio.do?method=obtenerMunicipio" + "&" + "idEntidad="+ vEntidad;
			municipioFedStore.close();			
		}
	}
	function actulizaColonia() {
		var vEntidad   = dijit.byId('idEntidadSelect').get('value');
		var vMunicipio = dijit.byId('idMunicipioSelect').get('value');
		if (vEntidad && vMunicipio){
			dojo.byId('codigoPostal').value = '';
			dijit.byId('idColoniaSelect').reset();
			coloniaFedStore.url = "${context}domicilio.do?method=obtenerColonia" + "&" + "idEntidad="+ vEntidad + "&" + "idMunicipio="+ vMunicipio;
			coloniaFedStore.close();
		}
	}
	function actulizaCodigoPostal() {
		var vEntidad   = dijit.byId('idEntidadSelect').get('value');
		var vMunicipio = dijit.byId('idMunicipioSelect').get('value');
		var vColonia = dijit.byId('idColoniaSelect').get('value');;
		if (vEntidad && vMunicipio && vColonia){
	    	var url = '${context}domicilio.do?method=obtenerCodigoPostal&idEntidad='+ vEntidad + '&idMunicipio='+ vMunicipio + '&idColonia='+ vColonia;
			dojo.xhrGet({url: url, sync: true, timeout:180000,
		       load: function(data){
					       var res = dojo.fromJson(data);
						   dojo.byId('codigoPostal').value = res.codigoPostal;
					    }
				});
		} else {
			dojo.byId('codigoPostal').value='';
		}
	}
	function validateDay(day, month, year, widget) {
		if ((month==4) || (month==6) || (month==9) || (month==11)) {
			if (day > 30) {
				widget.focus();
			    message('El día seleccionado no puede ser mayor a 30');
			    return false;
			}
		}else {
			if (month==2) {
				if ((year % 4 == 0) && ((year % 100 != 0) || (year % 400 == 0))) {
					if (day > 29) {
						widget.focus();
					    message('El día seleccionado no puede ser mayor a 29');
					    return false;
					}
				}else {
					if (day > 28) {
						widget.focus();
						message('El día seleccionado no puede ser mayor a 28');
						return false;
					}
				}
			}
		}
		return true;
	}
	function validateExDate(laboresInicialDia, laboresInicialMes, laboresInicialAnual, widget) {
		var today = new Date();
		var exDate = new Date();
		var monthiniTmp = laboresInicialMes;
		exDate.setFullYear(laboresInicialAnual,--monthiniTmp,laboresInicialDia);
		if (!validateDay(laboresInicialDia, laboresInicialMes, laboresInicialAnual, widget)) return false;
		if (exDate > today) {
		 	widget.focus();
		 	message('La fecha seleccionada no puede ser mayor al dia de hoy.');
		 	 return false;
		 }
		return true;
	}
	function validateOtherStudiesList() {
		var idCandidateStudie1 = dijit.byId('studieAdd1');
	 	var idCandidateStudie2 = dijit.byId('studieAdd2');
	 	var idCandidateStudie3 = dijit.byId('studieAdd3');
	 	var idCandidateStudie4 = dijit.byId('studieAdd4');
	 	var idCandidateStudie5 = dijit.byId('studieAdd5');
	 	var idCandidateStudie6 = dijit.byId('studieAdd6');
	 	var idCandidateStudie7 = dijit.byId('studieAdd7');
	 	var idCandidateStudie8 = dijit.byId('studieAdd8');
	 	var idCandidateStudie9 = dijit.byId('studieAdd9');
	 	var idCandidateStudie10 = dijit.byId('studieAdd10');
		  if (idCandidateStudie1 != undefined) {
		   if (idCandidateStudie2 != undefined) {
		    if (idCandidateStudie3 != undefined) {
		     if (idCandidateStudie4 != undefined) {
		      if (idCandidateStudie5 != undefined) {
		       if (idCandidateStudie6 != undefined) {
		        if (idCandidateStudie7 != undefined) {
		         if (idCandidateStudie8 != undefined) {
		          if (idCandidateStudie9 != undefined) {
		           if (idCandidateStudie10 != undefined)
		            return (validateOtherStudie(1) && validateOtherStudie(2) && validateOtherStudie(3) && validateOtherStudie(4) && validateOtherStudie(5) && validateOtherStudie(6) && validateOtherStudie(7) && validateOtherStudie(8) && validateOtherStudie(9) && validateOtherStudie(10));
		           else
		            return (validateOtherStudie(1) && validateOtherStudie(2) && validateOtherStudie(3) && validateOtherStudie(4) && validateOtherStudie(5) && validateOtherStudie(6) && validateOtherStudie(7) && validateOtherStudie(8) && validateOtherStudie(9));
		          }else
		           return (validateOtherStudie(1) && validateOtherStudie(2) && validateOtherStudie(3) && validateOtherStudie(4) && validateOtherStudie(5) && validateOtherStudie(6) && validateOtherStudie(7) && validateOtherStudie(8));
		         }else
		          return (validateOtherStudie(1) && validateOtherStudie(2) && validateOtherStudie(3) && validateOtherStudie(4) && validateOtherStudie(5) && validateOtherStudie(6) && validateOtherStudie(7));
		        }else
		         return (validateOtherStudie(1) && validateOtherStudie(2) && validateOtherStudie(3) && validateOtherStudie(4) && validateOtherStudie(5) && validateOtherStudie(6));
		       }else
		        return (validateOtherStudie(1) && validateOtherStudie(2) && validateOtherStudie(3) && validateOtherStudie(4) && validateOtherStudie(5));
		      }else
		       return (validateOtherStudie(1) && validateOtherStudie(2) && validateOtherStudie(3) && validateOtherStudie(4));
		     }else
		      return (validateOtherStudie(1) && validateOtherStudie(2) && validateOtherStudie(3));
		    }else
		     return (validateOtherStudie(1) && validateOtherStudie(2));
		   }else
		    return validateOtherStudie(1);
		  }
		  return true;
		 }
		 function validateOtherStudie(index) {
		 	var studietypeadd = dijit.byId('studieTypeAdd' + index);
		  	var studieadd = dijit.byId('studieAdd' + index);
		  	var institutionadd = dijit.byId('institutionAdd' + index);
		  	var dayiniadd = dijit.byId('dayIniAdd' + index);
		  	var monthiniadd = dijit.byId('monthIniAdd' + index);
		  	var yeariniadd = dijit.byId('yearIniAdd' + index);
		  	var dayfinadd = dijit.byId('dayFinAdd' + index);
		  	var monthfinadd = dijit.byId('monthFinAdd' + index);
		  	var yearfinadd = dijit.byId('yearFinAdd' + index);
		  	var statusacademic = dijit.byId('statusAcademicAdd' + index);
		  	if (studietypeadd.value == 0) {
		  		studietypeadd.focus();
		    	message('Debe seleccionar otro estudio.');
		   		return false;
		    }
		    if (!studieadd.value) {
		    	studieadd.focus();
		   		message('Es necesario indicar el nombre del estudio.');
		   		return false;
		  	}
		  	if (!institutionadd.value) {
		  		institutionadd.focus();
		   		message('Es necesario indicar la institución del estudio.');
		   		return false;
		  	}
		  	if (statusacademic.value == 0) {
		    	statusacademic.focus();
		    	message('Debe seleccionar situación académica del estudio.');
		   		return false;
		    }
		  	if (dayiniadd.value == 0) {
		  		dayiniadd.focus();
		      	message('Debe seleccionar día de fecha de inicio del estudio.');
		   		return false;
		    }
		    if (monthiniadd.value == 0) {
		    	monthiniadd.focus();
		      	message('Debe seleccionar mes de fecha de inicio del estudio.');
		   		return false;
		    }
		    if (yeariniadd.value == 0) {
		    	yeariniadd.focus();
		    	message('Debe seleccionar año de fecha de inicio del estudio.');
		   		return false;
		    }
		    if (dayfinadd.value == 0) {
		    	dayfinadd.focus();
		    	message('Debe seleccionar día de fecha de fin del estudio.');
		   		return false;
		    }
		    if (monthfinadd.value == 0) {
		    	monthfinadd.focus();
		      	message('Debe seleccionar mes de fecha de fin del estudio.');
		   		return false;
		    }
		   	if (yearfinadd.value == 0) {
		   		yearfinadd.focus();
		    	message('Debe seleccionar año de fecha de fin del estudio.');
		  	 	return false;
		    }
		    if (!validateDatesList(dayiniadd, monthiniadd, yeariniadd, dayfinadd, monthfinadd, yearfinadd)) {
		    	return false;
		    }
		 	return true;
		 }
		 function validateDatesList(dayiniAdd, monthiniAdd, yeariniAdd, dayfinAdd, monthfinAdd, yearfinAdd) {
		 	var inicialdate = new Date(yeariniAdd.value, monthiniAdd.value, dayiniAdd.value);
		  	var finaldate = new Date(yearfinAdd.value, monthfinAdd.value, dayfinAdd.value);
		  	var hoy = new Date();
		  	var fhBuscaEmp = new Date();
		  	var fhEstFin = new Date();
		  	var monthiniTmp = monthiniAdd.value;
		  	var monthfinTmp = monthfinAdd.value;
		  	fhBuscaEmp.setFullYear(yeariniAdd.value,--monthiniTmp,dayiniAdd.value);
		  	fhEstFin.setFullYear(yearfinAdd.value,--monthfinTmp,dayfinAdd.value);
		  	var dif = dojo.date.compare(finaldate, inicialdate, 'date');
		  	var valido = dif >= 0;
		  	if (fhBuscaEmp > hoy){
		   		message('La fecha de inicio de otros estudios no puede ser mayor al dia de hoy.');
		   		return false;
		  	}
		  	if (fhEstFin > hoy){
		   		message('La fecha de fin de otros estudios no puede ser mayor al dia de hoy.');
		   		return false;
		  	}
		  	if (!valido) {
		   		message('La fecha de fin no puede ser menor a la fecha de inicio.');  
		  	}
		  	return valido;
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
	function hasLicense() {
		var licenciaManejo = getRadioValue('licManejoTiene');
		var idLicenciaManejoSelect = dijit.byId('idLicenciaManejoSelect');
		if (null != licenciaManejo && licenciaManejo == 1) {
			idLicenciaManejoSelect.readOnly=true;
		}else {
			idLicenciaManejoSelect.readOnly=false;
		}
	}
	function validateDatesLevel() {
		var laboresInicialDia = dijit.byId('fechaIngresoDia').get('value');
		var laboresInicialMes = dijit.byId('fechaIngresoDiaMes').get('value');
		var laboresInicialAnual = dijit.byId('fechaIngresoAnual').get('value');
	 	var laboresInicial = new Date(laboresInicialAnual, laboresInicialMes, laboresInicialDia);
	 	var laboresFinalDia = dijit.byId('fechaTerminacionDia').get('value');
	 	var laboresFinalMes = dijit.byId('fechaTerminacionMes').get('value');
	 	var laboresFinalAnual = dijit.byId('fechaTerminacionAnual').get('value');
	 	var laboresFinal = new Date(laboresFinalAnual, laboresFinalMes, laboresFinalDia);
	 	var hoy = new Date();
	 	var fhBuscaEmp = new Date();
	 	var fhEstFin = new Date();
	 	var monthiniTmp = laboresInicialMes;
	  	var monthfinTmp = laboresFinalMes;
		fhBuscaEmp.setFullYear(laboresInicialAnual,--monthiniTmp,laboresInicialDia);
		fhEstFin.setFullYear(laboresFinalAnual,--monthfinTmp,laboresFinalDia);
	 	var dif = dojo.date.compare(laboresFinal, laboresInicial, 'date');
	 	var valido = dif >= 0;
	 	if (!validateDay(laboresInicialDia, laboresInicialMes, laboresInicialAnual, dijit.byId('fechaIngresoDia'))) return false;
	 	if (!validateDay(laboresFinalDia, laboresFinalMes, laboresFinalAnual, dijit.byId('fechaTerminacionDia'))) return false;
	 	if (fhBuscaEmp > hoy) {
	 		dijit.byId('fechaIngresoDia').focus();
	 		message('La fecha de ingreso de último grado de estudios no puede ser mayor al dia de hoy.');
	 	 	return false;
	 	}
	 	if (fhEstFin > hoy) {
	 		dijit.byId('fechaTerminacionDia').focus();
	 		message('La fecha de terminación de último grado de estudios no puede ser mayor al dia de hoy.');
	 	 	return false;
	 	}
	 	if (!valido) {
	 		dijit.byId('fechaTerminacionDia').focus();
	 		message('La fecha de ingreso de último grado de estudios no puede ser mayor que la fecha de terminación');	
	 		return false;
	 	}
	 	return true;
	}
	function setRequiredJob() {
		if (dijit.byId('empresa').get('value')!='') {
    		document.getElementById("spanEmpresa").style.display="none";
    		document.getElementById("spanEmpresaReq").style.display="block";
    		dijit.byId('empresa').attr('required', true);
    		document.getElementById("spanPuesto").style.display="none";
    		document.getElementById("spanPuestoReq").style.display="block";
    		dijit.byId('puesto').attr('required', true);
    		document.getElementById("spanJerarquia").style.display="none";
    		document.getElementById("spanJerarquiaReq").style.display="block";
    		dijit.byId('idJerarquiaSelect').attr('required', true);
    		document.getElementById("spanPersonas").style.display="none";
    		document.getElementById("spanPersonasReq").style.display="block";
    		dijit.byId('personasCargo').attr('required', true);
    		document.getElementById("spanSalario").style.display="none";
    		document.getElementById("spanSalarioReq").style.display="block";
    		dijit.byId('salarioMensual').attr('required', true);
			document.getElementById("spanCalleEmpresa").style.display="none";
    		document.getElementById("spanCalleEmpresaReq").style.display="block";
    		dijit.byId('calleEmpresa').attr('required', true);

			document.getElementById("spanNumExtEmpresa").style.display="none";
    		document.getElementById("spanNumExtEmpresaReq").style.display="block";
    		dijit.byId('numExtEmpresa').attr('required', true);

			document.getElementById("spanIdEntidadSelect").style.display="none";
    		document.getElementById("spanIdEntidadSelectReq").style.display="block";
    		dijit.byId('idEntidadEmpresaSelect').attr('required', true);

			document.getElementById("spanIdMunicipioSelect").style.display="none";
    		document.getElementById("spanIdMunicipioSelectReq").style.display="block";
    		dijit.byId('idMunicipioEmpresaSelect').attr('required', true);

			document.getElementById("spanIdColoniaSelect").style.display="none";
    		document.getElementById("spanIdColoniaSelectReq").style.display="block";
    		dijit.byId('idColoniaEmpresaSelect').attr('required', true);

			document.getElementById("spanIdLocalidadSelect").style.display="none";
    		document.getElementById("spanIdLocalidadSelectReq").style.display="block";
    		dijit.byId('idLocalidadEmpresaSelect').attr('required', true);

			document.getElementById("spanCodigoPostalEmpresa").style.display="none";
    		document.getElementById("spanCodigoPostalEmpresaReq").style.display="block";
    		dijit.byId('codigoPostalEmpresa').attr('required', true);
    		
    		document.getElementById("spanIngreso").style.display="none";
    		document.getElementById("spanIngresoReq").style.display="block";
    		document.getElementById("spanIngreso").style.display="none";
    		document.getElementById("spanIngresoReq").style.display="block";
    		document.getElementById("spanTerminacion").style.display="none";
    		document.getElementById("spanTerminacionReq").style.display="block";
    		document.getElementById("spanFuncion").style.display="none";
    		document.getElementById("spanFuncionReq").style.display="block";
			document.getElementById("spanHerramientas").style.display="none";
    		document.getElementById("spanHerramientasReq").style.display="block";
    	}
	}
	function hasTest() {
		var examenPresentado = getRadioValue('examenPresentadoRadio');
		if (null != examenPresentado && examenPresentado == 2) {
			document.getElementById("examenPresentadoNo").disabled = true;
			document.getElementById("examenPresentadoSi").disabled = true;
		}
	}
</script>
<noscript>Funciones de JavaScript desactivadas por navegador</noscript>
<div dojoType="dojo.data.ItemFileReadStore" jsId="entidadFedStore" urlPreventCache="true" clearOnClose="true"></div>
<div dojoType="dojo.data.ItemFileReadStore" jsId="municipioFedStore" urlPreventCache="true" clearOnClose="true"></div>
<div dojoType="dojo.data.ItemFileReadStore" jsId="coloniaFedStore" urlPreventCache="true" clearOnClose="true"></div>
<div dojoType="dojo.data.ItemFileReadStore" jsId="localidadFedStore" urlPreventCache="true" clearOnClose="true"></div>
<div dojoType="dojo.data.ItemFileReadStore" jsId="carreraStore" urlPreventCache="true" clearOnClose="true"></div>
<div dojoType="dojo.data.ItemFileReadStore" jsId="estatusGradoStore" urlPreventCache="true" clearOnClose="true"></div>
<div dojoType="dojo.data.ItemFileReadStore" jsId="gradoAcademicoStore" urlPreventCache="true" clearOnClose="true" url="${context}grados.do?method=cargarGrados"></div>
<div class="formApp miEspacio">
	<form name="ProgramaForm" id="ProgramaForm" method="post" action="perfilComplementario.do" dojoType="dijit.form.Form">
		<input type="hidden" name="method" id="method" value="" />
		<input type="hidden" name="fechaIngreso" id="fechaIngreso" value="" />
		<input type="hidden" name="fechaTerminacion" id="fechaTerminacion" value="" />
		<input type="hidden" name="laboresInicial" id="laboresInicial" value="" />
		<input type="hidden" name="laboresFinal" id="laboresFinal" value="" />
		<input type="hidden" name="examenFecha" id="examenFecha" value="" />
		<input type="hidden" name="examenPresentado" id="examenPresentado" value="" />
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
                    	<c:if test="${ProgramaForm.modalidad.param.toLowerCase()=='mml'}">
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
						<c:if test="${ProgramaForm.modalidad.param.toLowerCase()=='mml'}">
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
									<label for="entreCalle"><span>*</span> Entre calle</label>
									<input type="text" dojoType="dijit.form.ValidationTextBox" name="entreCalle" id="entreCalle" maxlength="50" required="true"
										trim ="true" uppercase="true" value="${ProgramaForm.perfil.entreCalle}" missingMessage="Dato requerido">
								</div>
								<div class="grid1_3 margin_top_10 fl">
									<label for="yCalle"><span>*</span> Y calle</label>
									<input type="text" dojoType="dijit.form.ValidationTextBox" name="yCalle" id="yCalle" maxlength="50" required="true"
										value="${ProgramaForm.perfil.yCalle}" trim ="true" uppercase="true" missingMessage="Dato requerido">
								</div>
								<div class="clearfix"></div>
								<div class="grid1_3 margin_top_10 fl">
									<label for="idEntidadSelect"><span>*</span> Entidad Federativa</label>
									<input type="hidden" name="idEntidad" id="idEntidad" dojoType="dijit.form.TextBox">
									<select dojotype="dijit.form.FilteringSelect" store="entidadFedStore" id="idEntidadSelect" required="true"
										invalidMessage="Dato no válido" missingMessage="Es necesario indicar la entidad." autocomplete="true"
										disabled="disabled" onchange="javascript:actulizaMunicipio();" scrollOnFocus="true" maxHeight="250">
									</select>
								</div>
								<div class="grid1_3 margin_top_10 fl">
									<label for="idMunicipioSelect"><span>*</span> Municipio o Delegación</label>
									<input type="hidden" name="idMunicipio" id="idMunicipio" dojoType="dijit.form.TextBox">
									<select dojotype="dijit.form.FilteringSelect" store="municipioFedStore" id="idMunicipioSelect" required="true"
										disabled="disabled" invalidMessage="Dato no válido" missingMessage="Es necesario indicar un municipio."
										autocomplete="true" onchange="javascript:actulizaColonia();" scrollOnFocus="true" maxHeight="250">
									</select>
								</div>
								<div class="grid1_3 margin_top_10 fl">
									<label for="idColonia"><span>*</span> Colonia</label>
									<input type="hidden" name="idColonia" id="idColonia" dojoType="dijit.form.TextBox">
									<select dojotype="dijit.form.FilteringSelect" store="coloniaFedStore" id="idColoniaSelect" required="true"
										invalidMessage="Dato inválido" missingMessage="Es necesario indicar la colonia." autocomplete="true"
										disabled="disabled" onchange="javascript:actulizaCodigoPostal();" scrollOnFocus="true" maxHeight="250">
									</select>
								</div>
								<div class="grid1_3 margin_top_10 fl">
									<label for="idLocalidad"><span>*</span> Localidad</label>
									<input type="hidden" name="idLocalidad" id="idLocalidad" dojoType="dijit.form.TextBox">
									<select dojotype="dijit.form.FilteringSelect" id="idLocalidadSelect" store="localidadFedStore" required="true" 
										missingMessage="Debe seleccionar la localidad." autocomplete="true" scrollOnFocus="true" maxHeight="250" >
									</select>
								</div>
								<div class="grid1_3 margin_top_10 fl">
									<label for="codigoPostal">Código Postal</label>
									<input type="text" name="codigoPostal" id="codigoPostal" maxlength="5" style="width: 7em;"
										value="${ProgramaForm.perfil.codigoPostal}" dojoType="dijit.form.ValidationTextBox" regExp="^[0-9]{5}"
										invalidMessage="Codigo incorrecto, solo se admiten caracteres numericos." readonly="readonly"
								 		missingMessage="Es necesario indicar el codigo postal." trim="true" required="true"/>
								</div>
								<div class="clearfix"></div>
								<div class="campoTexto margin_top_30">
									<label for="referenciaDomicilio"><span>*</span> Referencia para llegar al domicilio</label>
									<textarea class="textGoogie" rows="4" cols="70" name="referenciaDomicilio" id="referenciaDomicilio" trim="true"
										onkeypress="return caracteresValidos(event);" onchange="return maximaLongitud(this,2000)" maxlength="2000"
										onKeyUp="return maximaLongitud(this,2000)" required="false" onblur="trimSpaces(this)"
										regExp="^[\w\d\s\.&aacute;&eacute;&iacute;&oacute;&uacute;&Aacute;&Eacute;&Iacute;&Oacute;&Uacute;&Ntilde;&ntilde;]{1,2000}$">${ProgramaForm.perfil.domicilioReferencia}</textarea>
										<script type="text/javascript">
						         			var vSpellCon = new GoogieSpell("googiespell/", '<%=vProxy%>');
						         			vSpellCon.setLanguages({'es': 'Español'});
					   						vSpellCon.hideLangWindow();
					  						vSpellCon.decorateTextarea("referenciaDomicilio");
					  					</script>
								</div>
							</fieldset>
							<div id="langs" name="langs">
								<jsp:include page="/jsp/candidatos/misDatos/addLang.jsp" flush="true" />  
							</div>
							<div id="experiences" name="experiences">
								<jsp:include page="/jsp/candidatos/misDatos/expcomp.jsp" flush="true" />  
							</div>
							<fieldset class="academic_edit">
								<legend>Nivel máximo de educación</legend>
								<div class="grid1_3 margin_top_10 fl">
									<label for="idNivelEstudioSelect"><span>*</span> Último grado de estudios</label>
									<input type="hidden" name="idNivelEstudio" id="idNivelEstudio" dojoType="dijit.form.TextBox" />
									<select dojotype="dijit.form.FilteringSelect" value='${ProgramaForm.ultimoGrado.idNivelEstudio}' autocomplete="true"
										id="idNivelEstudioSelect" required="true" disabled="disabled" missingMessage="Debe seleccionar el último grado de estudios">
										<c:forEach var="row" items="${opcGrados}">
											<option value="${row.idCatalogoOpcion}">${row.opcion}</option>
										</c:forEach>
									</select>
								</div>
								<div class="grid1_3 margin_top_10 fl">
									<label for="idCarreraEspecialidadSelect"><span>*</span> Carrera o especialidad</label>
									<input type="hidden" name="idCarreraEspecialidad" id="idCarreraEspecialidad" dojoType="dijit.form.TextBox" />
									<select dojotype="dijit.form.FilteringSelect" id="idCarreraEspecialidadSelect" name="idCarreraEspecialidadSelect"
										store="carreraStore" scrollOnFocus="true" maxHeight="250" autocomplete="true" required="true"
										disabled="disabled" missingMessage="Es necesario indicar la situación académica">
									</select>
								</div>
								<div class="grid1_3 margin_top_10 fl">
									<label for="idSituacionAcademicaSelect"><span>*</span> Situación académica</label>
									<input type="hidden" name="idSituacionAcademica" id="idSituacionAcademica" dojoType="dijit.form.TextBox" />
									<select dojotype="dijit.form.FilteringSelect" id="idSituacionAcademicaSelect" name="idSituacionAcademicaSelect" store="estatusGradoStore"
										disabled="disabled" autocomplete="true" required="true" missingMessage="Es necesario indicar la situación académica">
									</select>
								</div>
								<div class="grid1_3 margin_top_10 fl">
									<label for="institucion"><span>*</span> Institución</label>
									<input type="text" dojoType="dijit.form.ValidationTextBox" name="institucion" id="institucion" trim ="true" uppercase="true" maxlength="50"
										value="${ProgramaForm.ultimoGrado.escuela}" regExp="^[A-Za-z\s\-.áéíóúÁÉÍÓÚñÑ']{1,150}$" invalidMessage="El nombre sólo debe contener letras" missingMessage="Dato requerido" required="true">
								</div>
								<div class="margin_top_30">
					            	<div class="grid1_3 fl">
					            		<div class="labeled"><span>*</span> Fecha de inicio</div>
						                <select name="fechaIngresoDia" id="fechaIngresoDia" data-dojo-type="dijit.form.FilteringSelect" missingMessage="Es necesario indicar la Fecha de ingreso.">
						           			<option value="0">Día</option>
						           			<% out.println(getSelectedDay(fechaIngresoInst)); %>
								        </select>
						                <select name="fechaIngresoDiaMes" id="fechaIngresoDiaMes" data-dojo-type="dijit.form.FilteringSelect" missingMessage="Es necesario indicar la Fecha de ingreso.">
						                	<option value="0">Mes</option>
						           			<% out.println(getSelectedMonth(fechaIngresoInst)); %>
						                </select>
						                <select name="fechaIngresoAnual" id="fechaIngresoAnual" data-dojo-type="dijit.form.FilteringSelect" missingMessage="Es necesario indicar la Fecha de ingreso.">
						                    <option value="0">Año</option>
						           		    <% out.println(getSelectedYear(fechaIngresoInst)); %>
						                </select> 
					            	</div> 
					            	<div class="grid1_3 fl">
					            		<div class="labeled"><span>*</span> Fecha de fin</div>
						                <select name="fechaTerminacionDia" id="fechaTerminacionDia" data-dojo-type="dijit.form.FilteringSelect" missingMessage="Es necesario indicar la Fecha de termino.">
						           			<option value="0">Día</option>
						           			<% out.println(getSelectedDay(fechaTerminacionInst)); %>
								        </select>
						                <select name="fechaTerminacionMes" id="fechaTerminacionMes" data-dojo-type="dijit.form.FilteringSelect" missingMessage="Es necesario indicar la Fecha de termino.">
						                	<option value="0">Mes</option>
						           			<% out.println(getSelectedMonth(fechaTerminacionInst)); %>
						                </select>
						                <select name="fechaTerminacionAnual" id="fechaTerminacionAnual" data-dojo-type="dijit.form.FilteringSelect" missingMessage="Es necesario indicar la Fecha de termino.">
						                    <option value="0">Año</option>
						           		    <% out.println(getSelectedYear(fechaTerminacionInst)); %>
						                </select>
					            	</div>
					            </div>
					            <div class="grid1_3 margin_top_10 fl">
									<label for="pais"><span>*</span> Ciudad, País</label>
									<input type="text" dojoType="dijit.form.ValidationTextBox" name="lugar" id="lugar" trim ="true" uppercase="true" maxlength="50"
										value="${ProgramaForm.ultimoGrado.lugar}" regExp="^[A-Za-z\s\-.áéíóúÁÉÍÓÚñÑ,']{1,150}$" invalidMessage="El nombre sólo debe contener letras" missingMessage="Dato requerido" required="true">
								</div>
							</fieldset>
							<fieldset class="otros_estudios">
								<legend>Otros estudios</legend>
								<div id="otros_estudios bloque" name="otros_estudios bloque">
									<jsp:include page="/jsp/candidatos/misDatos/studiescomp.jsp" flush="true" />
								</div>
							</fieldset>
							<fieldset class="obj_edit">
								<legend>Objetivos</legend>
								<div class="campoTexto margin_top_30">
									<label for="objetivosPersonales"><span>*</span> Objetivos Personales</label>
									<textarea class="textGoogie" rows="4" cols="70" name="objetivos" id="objetivos" trim="true"
										onkeypress="return caracteresValidos(event);" onchange="return maximaLongitud(this,2000)" maxlength="2000"
										onKeyUp="return maximaLongitud(this,2000)" required="false" onblur="trimSpaces(this)"
										regExp="^[\w\d\s\.&aacute;&eacute;&iacute;&oacute;&uacute;&Aacute;&Eacute;&Iacute;&Oacute;&Uacute;&Ntilde;&ntilde;]{1,2000}$">${ProgramaForm.mml.objetivos}</textarea>
									<script type="text/javascript">
					         			var vSpellCon = new GoogieSpell("googiespell/", '<%=vProxy%>');
					         			vSpellCon.setLanguages({'es': 'Español'});
					   					vSpellCon.hideLangWindow();
					  					vSpellCon.decorateTextarea("objetivos");
									</script>
								</div>
								<div class="grid1_3 margin_top_10 fl">
									<label for="licenciaManejo">¿Tiene licencia de manejo?</label>
									<input type="radio" name="licManejoTiene" value="2" onclick="hasLicense();" <c:if test='${ProgramaForm.licManejoTiene==2}'>checked</c:if>/> Sí
									<input type="radio" name="licManejoTiene" value="1" onclick="hasLicense();" <c:if test='${ProgramaForm.licManejoTiene==1}'>checked</c:if>/> No
								</div>
								<div class="grid1_3 margin_top_10 fl">
									<label for="idLicenciaManejoSelect">¿Qué tipo de licencia tiene?</label>
									<input type="hidden" name="licManejoTipo" id="licManejoTipo" dojoType="dijit.form.TextBox">
									<select dojotype="dijit.form.FilteringSelect" id="idLicenciaManejoSelect" autocomplete="true"
										value="${ProgramaForm.perfil.idLicencia}" required="true" missingMessage="Dato requerido">
										<c:forEach var="row" items="${licenciaManejo}">
											<option value="${row.idCatalogoOpcion}">${row.opcion}</option>
										</c:forEach>
									</select>
								</div>
								<div class="grid1_3 margin_top_10 fl">
									<p class="grid1_3 fl">
										<label for="disponibilidadRadicarPais"><span>*</span> ¿Puedes radicar en otro país?</label>
										<input type="radio" name="disponibilidadRadicarPais" value="2" <c:if test='${ProgramaForm.perfil.perfilVO.disponibilidadRadicarPais==2}'>checked</c:if>/> Sí
										<input type="radio" name="disponibilidadRadicarPais" value="1" <c:if test='${ProgramaForm.perfil.perfilVO.disponibilidadRadicarPais==1}'>checked</c:if>/> No
									</p>
								</div>
								<div class="clearfix"></div>
								<div class="campoTexto margin_top_30">
									<label for="entretenimiento">Interéses y pasatiempos</label>
									<textarea class="textGoogie" rows="4" cols="70" name="entretenimiento" id="entretenimiento" trim="true"
										onkeypress="return caracteresValidos(event);" onchange="return maximaLongitud(this,2000)" maxlength="2000"
										onKeyUp="return maximaLongitud(this,2000)" required="false" onblur="trimSpaces(this)"
										regExp="^[\w\d\s\.&aacute;&eacute;&iacute;&oacute;&uacute;&Aacute;&Eacute;&Iacute;&Oacute;&Uacute;&Ntilde;&ntilde;]{1,2000}$">${ProgramaForm.mml.entretenimiento}</textarea>
									<script type="text/javascript">
					         			var vSpellCon = new GoogieSpell("googiespell/", '<%=vProxy%>');
					         			vSpellCon.setLanguages({'es': 'Español'});
					   					vSpellCon.hideLangWindow();
					  					vSpellCon.decorateTextarea("entretenimiento");
									</script>
								</div>
								<div class="campoTexto margin_top_30">
									<label for="observaciones">Observaciones</label>
									<textarea class="textGoogie" rows="4" cols="70" name="observaciones" id="observaciones" trim="true"
										onkeypress="return caracteresValidos(event);" onchange="return maximaLongitud(this,2000)" maxlength="2000"
										onKeyUp="return maximaLongitud(this,2000)" required="false" onblur="trimSpaces(this)"
										regExp="^[\w\d\s\.&aacute;&eacute;&iacute;&oacute;&uacute;&Aacute;&Eacute;&Iacute;&Oacute;&Uacute;&Ntilde;&ntilde;]{1,2000}$">${ProgramaForm.mml.observaciones}</textarea>
									<script type="text/javascript">
					         			var vSpellCon = new GoogieSpell("googiespell/", '<%=vProxy%>');
					         			vSpellCon.setLanguages({'es': 'Español'});
				   						vSpellCon.hideLangWindow();
				  						vSpellCon.decorateTextarea("observaciones");
									</script>
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
<%!
	String getSelectedDay(String sDate) {
		boolean today = false;
		Date date = null;
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			if (null == sDate || sDate.isEmpty()) {
				today = true;
				date = new Date();
			}else date = (Date)formatter.parse(sDate);
		} catch (ParseException e) { date = null; }
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		StringBuilder day = new StringBuilder();
		int dateDay = calendar.get(Calendar.DAY_OF_MONTH);
		for (int i = 1; i<32; i++) {
			String iday = "0" + i;
			if (iday.length() > 2) iday = iday.substring(1,3);
			day.append("<option value=\"" + iday + "\"");
			System.out.println(i+" "+dateDay+" "+today);
			if (!today && i==dateDay)
				day.append(" selected ");
			day.append(">" + iday + "</option>\n");
		}
		return day.toString();
	}
	
	String getSelectedMonth(String sDate) {
		Date date = null;
		boolean today = false;
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			if (null == sDate || sDate.isEmpty()) {
				today = true;
				date = new Date();
			}else date = (Date)formatter.parse(sDate);
		} catch (ParseException e) { date = null; }
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		StringBuilder day = new StringBuilder();
		int month = calendar.get(Calendar.MONTH) + 1;
		for (int i = 1; i<13; i++) {
			String iday = "0" + i;
			if (iday.length() > 2) iday = iday.substring(1,3);
			day.append("<option value=\"" + iday + "\"");
			if (!today && i==month)
				day.append(" selected ");
			day.append(">" + getLblMonth(i) + "</option>\n");
		}
		return day.toString();
	}
	
	String getSelectedYear(String sDate) {
		Date date = null;
		boolean today = false;
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		int cyear = cal.get(Calendar.YEAR);
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			if (null == sDate || sDate.isEmpty()) {
				today = true;
				date = new Date();
			}else date = (Date)formatter.parse(sDate);
		} catch (ParseException e) { date = null; }
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		StringBuilder day = new StringBuilder();
		int year = calendar.get(Calendar.YEAR);
		for (int i = 1982; i<cyear+1; i++) {
			day.append("<option value=\"" + i + "\"");
			if (!today &&  i==year)
				day.append(" selected ");
			day.append(">" + i + "</option>\n");
		}
		return day.toString();
	}
	
	String getLblMonth(int imonth) {
		StringBuilder month = new StringBuilder();
		switch(imonth) {
			case 1 : month.append("Enero"); break;
			case 2 : month.append("Febrero"); break;
			case 3 : month.append("Marzo"); break;
			case 4 : month.append("Abril"); break;
			case 5 : month.append("Mayo"); break;
			case 6 : month.append("Junio"); break;
			case 7 : month.append("Julio"); break;
			case 8 : month.append("Agosto"); break;
			case 9 : month.append("Septiembre"); break;
			case 10 : month.append("Octubre"); break;
			case 11 : month.append("Noviembre"); break;
			case 12 : month.append("Diciembre"); break;
			default : month.append("");
		}
		return month.toString();
	}
%>