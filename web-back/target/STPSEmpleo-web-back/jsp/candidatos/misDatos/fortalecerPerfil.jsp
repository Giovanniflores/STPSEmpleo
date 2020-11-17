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
    function doSubmmitDataComp() {
    	dialogMsgConfirmMML.hide();
		document.ProgramaForm.method.value = 'saveStrengthenProfile';
		document.ProgramaForm.idLocalidad.value = dijit.byId('idLocalidadSelect').get('value');
		document.ProgramaForm.licManejoTiene.value = getRadioValue('licManejoTiene');
		document.ProgramaForm.licManejoTipo.value =  dijit.byId('idLicenciaManejoSelect').get('value');
		document.ProgramaForm.disponibilidadRadicarPais.value =  getRadioValue('disponibilidadRadicarPais');
		var fechaIngresoDia=document.getElementById('fechaIngresoDia').value;
		var fechaIngresoDiaMes=dijit.byId('fechaIngresoDiaMes').get('value');
		var fechaIngresoAnual=document.getElementById('fechaIngresoAnual').value;
		var fechaIngreso = fechaIngresoDia + '/' + fechaIngresoDiaMes + '/' + fechaIngresoAnual
		if (fechaIngresoDia != 0)
			document.ProgramaForm.fechaIngreso.value = fechaIngreso;	
		var fechaTerminacionDia=document.getElementById('fechaTerminacionDia').value;
		var fechaTerminacionMes=dijit.byId('fechaTerminacionMes').get('value');
		var fechaTerminacionAnual=document.getElementById('fechaTerminacionAnual').value;
		var fechaTerminacion = fechaTerminacionDia + '/' + fechaTerminacionMes + '/' + fechaTerminacionAnual
		if (fechaTerminacionDia != 0)
			document.ProgramaForm.fechaTerminacion.value = fechaTerminacion;
		dojo.xhrGet({
			url: 'perfilComplementario.do',
			form:'ProgramaForm',
			sync: true,
			timeout:180000,
			load: function(data) {
				var res = dojo.fromJson(data);
				if (res.type=='ext') {
					messagePath(res.message,'<%=context%>perfilComplementario.do?method=languages');
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
		+ cerrarMsgRuta(path, "Continuar")
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
		if (!validateExtAdr()) return false;
		if (!validateLastDegree()) return false;
		if (!validateOtherStudiesList()) return false;
		if (!validateComplementData()) return false;
		messageConfirmMML('¿Está seguro de guardar datos complementarios?');
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
	function validateExtAdr() {
		var numeroInterior = dijit.byId('numeroInterior');
		var idLocalidadSelect = dijit.byId('idLocalidadSelect');
		var referenciaDomicilio = document.getElementById('referenciaDomicilio');
		if (numeroInterior.get('value')=='') {
			numeroInterior.focus();
			message('Número interior es requerido.');
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
	function validateLastDegree() {
		var institucion = dijit.byId('institucion');
		var fechaIngresoDia = dijit.byId('fechaIngresoDia');
		var fechaIngresoDiaMes = dijit.byId('fechaIngresoDiaMes');
		var fechaIngresoAnual = dijit.byId('fechaIngresoAnual');
		var fechaTerminacionDia = dijit.byId('fechaTerminacionDia');
		var fechaTerminacionMes = dijit.byId('fechaTerminacionMes');
		var fechaTerminacionAnual = dijit.byId('fechaTerminacionAnual');
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
	function validateComplementData() {
		var objetivos = document.getElementById('objetivos');
		var disponibilidadRadicarPais = getRadioValue('disponibilidadRadicarPais');
		if (objetivos.value=='') {
			objetivos.focus();
			message('Objetivos Personales es requerido.');
			return false;
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
                    	<c:if test="${ProgramaForm.modalidad.param.toLowerCase()=='mml'}">
							<li class="curSubTab"><span>Fortalecer perfil</span></li>
						</c:if>
						<li class="curSubTab"><a href="${context}perfilComplementario.do?method=languages">Idiomas</a></li>
						<li class="curSubTab"><a href="${context}perfilComplementario.do?method=workReferences">Experiencia laboral</a></li>
					</ul>
					<div class="clearfix"></div>
				</div>
				<div class="clearfix"></div>
			</div>
			<p class="labeled">
				${ProgramaForm.modalidad.nombreCorto}<br>
				<span class="normal">Datos iniciales para el Fortalecimiento del perfil</span><br>
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
										required="false" value="${ProgramaForm.perfil.numeroInterior}" missingMessage="Dato requerido">
								</div>
								<div class="grid1_3 margin_top_10 fl">
									<label for="entreCalle"><span>*</span> Entre calles</label>
									<input type="text" dojoType="dijit.form.ValidationTextBox" name="entreCalle" id="entreCalle" maxlength="50" required="true"
										value="${ProgramaForm.perfil.entreCalle}" uppercase="true" missingMessage="Dato requerido">
								</div>
								<div class="grid1_3 margin_top_10 fl">
									<label for="yCalle"><span>*</span> Y calle</label>
									<input type="text" dojoType="dijit.form.ValidationTextBox" name="yCalle" id="yCalle" maxlength="50" required="true"
										value="${ProgramaForm.perfil.yCalle}" uppercase="true" missingMessage="Dato requerido">
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
										onKeyUp="return maximaLongitud(this,2000)" required="false"
										regExp="^[\w\d\s\.&aacute;&eacute;&iacute;&oacute;&uacute;&Aacute;&Eacute;&Iacute;&Oacute;&Uacute;&Ntilde;&ntilde;]{1,2000}$">${ProgramaForm.perfil.domicilioReferencia}</textarea>
										<script type="text/javascript">
						         			var vSpellCon = new GoogieSpell("googiespell/", '<%=vProxy%>');
						         			vSpellCon.setLanguages({'es': 'Español'});
					   						vSpellCon.hideLangWindow();
					  						vSpellCon.decorateTextarea("referenciaDomicilio");
					  					</script>
								</div>
							</fieldset>
							<fieldset class="academic_edit">
								<legend>Nivel máximo de educación</legend>
								<div class="grid1_3 margin_top_10 fl">
									<label for="institucion"><span>*</span> Institución</label>
									<input type="text" dojoType="dijit.form.ValidationTextBox" name="institucion" id="institucion" uppercase="true" maxlength="50"
										value="${ProgramaForm.ultimoGrado.escuela}" regExp="^[A-Za-z\s\-.áéíóúÁÉÍÓÚñÑ']{1,150}$" invalidMessage="El nombre sólo debe contener letras" missingMessage="Dato requerido" required="true">
								</div>
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
								<div class="margin_top_30">
					            	<div class="grid1_3 fl">
					            		<div class="labeled">Fecha de ingreso</div>
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
					            		<div class="labeled">Fecha de terminación</div>
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
									<input type="text" dojoType="dijit.form.ValidationTextBox" name="lugar" id="lugar" uppercase="true" maxlength="50"
										value="${ProgramaForm.ultimoGrado.lugar}" regExp="^[A-Za-z\s\-.,áéíóúÁÉÍÓÚñÑ']{1,150}$" invalidMessage="El nombre sólo debe contener letras" missingMessage="Dato requerido" required="true">
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
									<label for="objetivos"><span>*</span> Objetivos Personales</label>
									<textarea class="textGoogie" rows="4" cols="70" name="objetivos" id="objetivos" trim="true"
										onkeypress="return caracteresValidos(event);" onchange="return maximaLongitud(this,2000)" maxlength="2000"
										onKeyUp="return maximaLongitud(this,2000)" required="false"
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
									<input type="radio" name="licManejoTiene" value="2" <c:if test='${ProgramaForm.licManejoTiene==2}'>checked</c:if>/> Sí
									<input type="radio" name="licManejoTiene" value="1" <c:if test='${ProgramaForm.licManejoTiene==1}'>checked</c:if>/> No
								</div>
								<div class="grid1_3 margin_top_10 fl">
									<label for="idLicenciaManejoSelect">¿Qué tipo de licencia tiene?</label>
									<input type="hidden" name="licManejoTipo" id="licManejoTipo" dojoType="dijit.form.TextBox">
									<select dojotype="dijit.form.FilteringSelect" id="idLicenciaManejoSelect" autocomplete="true"
										value="${ProgramaForm.licManejoTipo}" required="true" missingMessage="Dato requerido">
										<c:forEach var="row" items="${licenciaManejo}">
											<option value="${row.idCatalogoOpcion}">${row.opcion}</option>
										</c:forEach>
									</select>
								</div>
								<div class="grid1_3 margin_top_10 fl">
									<p class="grid1_3 fl">
										<label for="disponibilidadRadicarPais"><span>*</span> ¿Puedes radicar en otro país?</label>
										<input type="radio" name="disponibilidadRadicarPais" value="2" <c:if test='${ProgramaForm.sne.disponibilidadRadicarPais==2}'>checked</c:if>/> Sí
										<input type="radio" name="disponibilidadRadicarPais" value="1" <c:if test='${ProgramaForm.sne.disponibilidadRadicarPais==1}'>checked</c:if>/> No
									</p>
								</div>
								<div class="clearfix"></div>
								<div class="campoTexto margin_top_30">
									<label for="entretenimiento">Interéses y pasatiempos</label>
									<textarea class="textGoogie" rows="4" cols="70" name="entretenimiento" id="entretenimiento" trim="true"
										onkeypress="return caracteresValidos(event);" onchange="return maximaLongitud(this,2000)" maxlength="2000"
										onKeyUp="return maximaLongitud(this,2000)" required="false"
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
										onKeyUp="return maximaLongitud(this,2000)" required="false"
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
			<input type="button" id="btnActualizar" name="btnActualizar" class="boton_naranja" onclick="validateForm();" value="Aceptar">
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
		for (int i = 1979; i<2032; i++) {
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