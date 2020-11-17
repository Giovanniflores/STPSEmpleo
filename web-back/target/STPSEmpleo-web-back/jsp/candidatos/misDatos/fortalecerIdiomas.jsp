<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page import="mx.gob.stps.portal.web.candidate.form.ProgramaForm"%>
<%
	String context = request.getContextPath() +"/";
%>
<script type="text/javascript" src="js/helper/messageHelper.js"></script>
<script type="text/javascript">
	var depGrado  = new Array(); //Declara arreglo de dependencias de catalogo Grado de Estudios
	var depIdioma = new Array(); //Declara arreglo de dependencias de catalogo idiomas
	//Declara arreglo de dependencias de catalogo Grado de Estudios
	<%String[] depGrado = session.getAttribute("depGrado") != null
					? (String[]) session.getAttribute("depGrado")
					: new String[0];
			for (int i = 0; i < depGrado.length; i++) {%>
			<%="depGrado[" + i + "] = '" + depGrado[i] + "';"%>
		<%}%>
</script>
<script type="text/javascript">
	dojo.require("dijit.dijit"); // loads the optimized dijit layer
	dojo.require('dojo.parser');
	dojo.require('dijit.Dialog');
	dojo.require("dijit.form.Form");
	dojo.require("dijit.form.TextBox");
	dojo.require('dijit.form.RadioButton');
	dojo.require("dijit.form.FilteringSelect");
	dojo.require("dojo.data.ItemFileReadStore");
    dojo.require("dijit.form.ValidationTextBox");
</script>
<script>
	dojo.addOnLoad(function() {
		actualizaCertificaciones();
		loadLang();
	});
	function doSubmmitDataComp() {
    	dialogMsgConfirmLang.hide();
		document.ProgramaForm.method.value = 'saveStrengthenLangs';
		dijit.byId('idDominioHabla').set('value', dijit.byId('idHabloIdiomaSelect').get('value'));
 		dijit.byId('idDominioEscrito').set('value', dijit.byId('idEscriboIdiomaSelect').get('value'));
 		dijit.byId('idDominioLectura').set('value', dijit.byId('idLeoIdiomaSelect').get('value'));
 		if (dijit.byId('selectLanguage_1') != undefined) {
		   	dijit.byId('idIdiomaAdicional_1').set('value', dijit.byId('selectLanguage_1').get('value'));
		   	dijit.byId('idDominioIdiomaAdd_1').set('value', dijit.byId('dominio_1').get('value'));
	   		dijit.byId('idCertificacionIdiomaAdd_1').set('value', dijit.byId('certification_1').get('value'));
	   		dijit.byId('idDominioHablaAdd1').set('value', dijit.byId('habloDominio_1').get('value'));
	   		dijit.byId('idDominioEscritoAdd1').set('value', dijit.byId('escriboDominio_1').get('value'));
	   		dijit.byId('idDominioLecturaAdd1').set('value', dijit.byId('leoDominio_1').get('value'));
	   	}
	   	if (dijit.byId('selectLanguage_2') != undefined) {
		   	dijit.byId('idIdiomaAdicional_2').set('value', dijit.byId('selectLanguage_2').get('value'));
		   	dijit.byId('idDominioIdiomaAdd_2').set('value', dijit.byId('dominio_2').get('value'));
	   		dijit.byId('idCertificacionIdiomaAdd_2').set('value', dijit.byId('certification_2').get('value'));
	   		dijit.byId('idDominioHablaAdd2').set('value', dijit.byId('habloDominio_2').get('value'));
	   		dijit.byId('idDominioEscritoAdd2').set('value', dijit.byId('escriboDominio_2').get('value'));
	   		dijit.byId('idDominioLecturaAdd2').set('value', dijit.byId('leoDominio_2').get('value'));
		}
	   	if (dijit.byId('selectLanguage_3') != undefined) {
		   	dijit.byId('idIdiomaAdicional_3').set('value', dijit.byId('selectLanguage_3').get('value'));
		   	dijit.byId('idDominioIdiomaAdd_3').set('value', dijit.byId('dominio_3').get('value'));
	   		dijit.byId('idCertificacionIdiomaAdd_3').set('value', dijit.byId('certification_3').get('value'));
	   		dijit.byId('idDominioHablaAdd3').set('value', dijit.byId('habloDominio_3').get('value'));
	   		dijit.byId('idDominioEscritoAdd3').set('value', dijit.byId('escriboDominio_3').get('value'));
	   		dijit.byId('idDominioLecturaAdd3').set('value', dijit.byId('leoDominio_3').get('value'));
		}
		dojo.xhrGet({
			url: 'perfilComplementario.do',
			form:'ProgramaForm',
			sync: true,
			timeout:180000,
			load: function(data) {
				var res = dojo.fromJson(data);
				if (res.type=='ext') {
					messagePath(res.message,'<%=context%>perfilComplementario.do?method=workReferences');
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
    function eliminarIdioma(idIdioma, idCertificacion) {
		var idIdiomaEliminar=idIdioma;
		var idCertificacionEliminar=idCertificacion;
		document.getElementById('idiomaAdicional').style.display = 'none';
		dojo.xhrPost({url: 'perfilComplementario.do?method=eliminarIdiomaAdicional&idIdiomaEliminar='+idIdiomaEliminar+'&idCertificacionIdiomaEliminar='+idCertificacionEliminar,preventCache: true, form:'ProgramaForm', sync: true, timeout:180000, 
			load: function(data) {
				dijit.byId('idIdiomaAdd').set('value','');
				dijit.byId('idCertificacionIdiomaAdd').set('value','');
				dijit.byId('idDominioIdiomaAdd').set('value','');
				clearAddSetAgregaIdioma(data);
			}});
	}
	function validateForm() {
		if (!validateLangList()) return false;
		messageConfirmLang('¿Está seguro de guardar datos complementarios?');
	}
	function messageConfirmLang(msg) {
		var html =
			'<div id="messageDialgopL" name="messageDialgopL">' +
			'<p style="text-align: center;">'+ msg +'</p>'+
			'<p class="form_nav">' + 
			'<input id="btnRenv" class="boton_naranja" type="button" name="btnRenv" onClick="doSubmmitDataComp()" value="Aceptar"/>' +
			'<input id="btnCanc" class="boton_naranja" type="button" name="btnCanc" onClick="dialogMsgConfirmLang.hide();" value="Cancelar"/>' +
    		'</p>' +
    		'</div>';
		dialogMsgConfirmLang = new dijit.Dialog({
	        title: 'Mensaje',
	        content: html,
	        style: "width:300px;",
	        showTitle: false, draggable : false, closable : false,
	    });	
		dialogMsgConfirmLang.show();
	}
	function doSubmitAddIdioma() {	
		var idIdiomaPrincipal = dijit.byId('idIdioma').get('value');
		var idCertificacionIdiomaPrincipal = dijit.byId('idCertificacionIdioma').get('value');
		var idIdiomaAdd = dijit.byId('idIdiomaAdd').get('value');
		var idCertificacionIdiomaAdd = dijit.byId('idCertificacionIdiomaAdd').get('value');
		if ((idIdiomaPrincipal==idIdiomaAdd) && (idCertificacionIdiomaPrincipal==idCertificacionIdiomaAdd)){
			message('Los idiomas adicionales deben ser diferentes al idioma principal');
			return false;
		}
		if (!validaCampoSelect('idIdiomaAdd')) return false;	
		if (!validaCampoSelect('idDominioIdiomaAdd')) return false;
		if (!validaCampoNoRequerido('idCertificacionIdiomaAdd')) return false;
		if (!validaCampoSelect('idHabloIdiomaAddSelect')) return false;
		if (!validaCampoSelect('idEscriboIdiomaAddSelect')) return false;
		if (!validaCampoSelect('idLeoIdiomaAddSelect')) return false;
		addIdioma();
	}
	function addIdioma() {
		var idIdiomaAdd = dijit.byId('idIdiomaAdd').get('value');
		var idCertificacionIdiomaAdd = dijit.byId('idCertificacionIdiomaAdd').get('value');
		var idDominioIdiomaAdd = dijit.byId('idDominioIdiomaAdd').get('value');
		var idHabloIdiomaAdd = dijit.byId('idHabloIdiomaAddSelect').get('value');
		var idEscriboIdiomaAdd = dijit.byId('idEscriboIdiomaAddSelect').get('value');
		var idLeoIdiomaAdd = dijit.byId('idLeoIdiomaAddSelect').get('value');
		document.getElementById('idiomaAdicional').style.display = 'none';
		dojo.xhrPost({url: 'perfilComplementario.do?method=addLang&idIdiomaAdd='+idIdiomaAdd+'&idCertificacionIdiomaAdd='+idCertificacionIdiomaAdd+'&idDominioIdiomaAdd='+idDominioIdiomaAdd+'&idHabloIdiomaAdd='+idHabloIdiomaAdd+'&idEscriboIdiomaAdd='+idEscriboIdiomaAdd+'&idLeoIdiomaAdd='+idLeoIdiomaAdd,preventCache: true, form:'ProgramaForm', sync: true, timeout:180000, 
			load: function(data) {
				dijit.byId('idIdiomaAdd').set('value','');
				dijit.byId('idCertificacionIdiomaAdd').set('value','');
				dijit.byId('idDominioIdiomaAdd').set('value','');
				dijit.byId('idHabloIdiomaAddSelect').set('value','');
				dijit.byId('idEscriboIdiomaAddSelect').set('value','');
				dijit.byId('idLeoIdiomaAddSelect').set('value','');
				clearAddSetAgregaIdioma(data);
		}});
	}
	function actualizaCertificaciones() {
    	var vIdioma = dijit.byId('idIdioma').get('value');
		var vDominio = dijit.byId('idDominioIdioma').get('value');
		certificacionStore.url = '';
		setLabelToLabelAndRequerido('idCertificacionIdioma', 'idLabelCertificacionIdioma', '¿Cuentas con certificación?', false);
		if (vIdioma == 1) {
			desactivaSelectConValor('idCertificacionIdioma', 1);
		}else {
			if (vDominio == 3 || vDominio == 2) {
				activarCertificaciones();
			} else {
				desactivarCertifiaciones();	
			}
		}
		setLevelEng(vDominio);
    }
    function setLevelEng(vDominio) {
    	if (vDominio > 2) {
			dijit.byId('idHabloIdiomaSelect').attr('value', 3);
			dijit.byId('idEscriboIdiomaSelect').attr('value', 3);
			dijit.byId('idLeoIdiomaSelect').attr('value', 3);
			dijit.byId('idHabloIdiomaSelect').set('disabled', true);
			dijit.byId('idEscriboIdiomaSelect').set('disabled', true);
			dijit.byId('idLeoIdiomaSelect').set('disabled', true);
		}else {
			enableSelectConValor('idHabloIdiomaSelect', '');
			enableSelectConValor('idEscriboIdiomaSelect', '');
			enableSelectConValor('idLeoIdiomaSelect', '');
		}
    }
	function activarCertificaciones() {
    	var vIdioma = dijit.byId('idIdioma').get('value');
		//Necesitamos el valor guardado del catalogo que es referente a su idoma no lo de la lista
		var vIdIdiomaDep = depIdioma[vIdioma];
    	certificacionStore.url ='${context}idiomas.do?method=certificaciones&idCatDep='+ vIdIdiomaDep;
    	enableSelectConValor('idCertificacionIdioma', 1);
    	setLabelToLabelAndRequerido('idCertificacionIdioma', 'idLabelCertificacionIdioma', '¿Cuentas con certificación?', true);
		certificacionStore.close();
    }
    function desactivarCertifiaciones() {
    	certificacionStore.url = '';
		certificacionStore.close();
		dijit.byId('idCertificacionIdioma').reset();
		desactivaSelectConValor('idCertificacionIdioma','');
		setLabelToLabelAndRequerido('idCertificacionIdioma', 'idLabelCertificacionIdioma', '¿Cuentas con certificación?', false);
    }
    function enableSelectConValor(selectList, valor) {
		dijit.byId(selectList).set('value', valor);
		dijit.byId(selectList).readOnly = false;
		dijit.byId(selectList).disabled = false;
		var originalValidator = dijit.byId(selectList).validator;
		dijit.byId(selectList).validator = function() {
			return true;
		};
		dijit.byId(selectList).validate();
		dijit.byId(selectList).validator = originalValidator;
	}
	function desactivaSelectConValor(selectList, valor) {
		dijit.byId(selectList).attr('value', valor);
		// Se apaga el color rojo del control
		var originalValidator = dijit.byId(selectList).validator;
		dijit.byId(selectList).validator = function() {
			return false;
		};
		dijit.byId(selectList).validate();
		dijit.byId(selectList).validator = originalValidator;
		dijit.byId(selectList).reset();
		document.getElementById(selectList).value == valor;
		dijit.byId(selectList).readOnly = true;
		dijit.byId(selectList).disabled = true;
	}
	function loadLang() {
		var vIdioma = '${ProgramaForm.perfil.idiomaPrincipal.idIdioma}';
		var vIdIdiomaDep = depIdioma[vIdioma];
		if (vIdIdiomaDep && vIdIdiomaDep != 0 ) {
    		certificacionStore.url = '${context}idiomas.do?method=certificaciones&idCatDep='+ vIdIdiomaDep;
    		certificacionStore.close();
    		certificacionStore.fetch({
         		onComplete: function(items, request) {
         			if (items.length == 0) return;
         				dijit.byId('idCertificacionIdioma').attr('value', '${ProgramaForm.perfil.idiomaPrincipal.idCertificacion}');
         			}
   			});
		}
		if (vIdioma==1) {
			desactivarCertifiaciones();
    		dominioStore.url = '';
    		dominioStore.close();
    		dijit.byId('idDominioIdioma').set('value','0');
			dijit.byId('idDominioIdioma').attr('value', 0); 
			desactivaSelectConValor('idDominioIdioma','');
			setLabelToLabelAndRequerido('idDominioIdioma','idLabelDominioIdioma','Dominio del idioma',false);
    	}else {
			setLabelToLabelAndRequerido('idDominioIdioma','idLabelDominioIdioma','Dominio del idioma',true);
		}
		var idDominio = '${ProgramaForm.perfil.idiomaPrincipal.idDominio}';
    	if(idDominio ==  2 || idDominio == 3 ) {
    		setLabelToLabelAndRequerido('idCertificacionIdioma','idLabelCertificacionIdioma','¿Cuentas con certificación?',true);
    	}
    	else {
    		setLabelToLabelAndRequerido('idCertificacionIdioma','idLabelCertificacionIdioma','¿Cuentas con certificación?',false);
    	}
    	enableSelectConValor('idHabloIdiomaSelect', '${ProgramaForm.perfil.idiomaPrincipal.idDominioHabla}');
		enableSelectConValor('idEscriboIdiomaSelect', '${ProgramaForm.perfil.idiomaPrincipal.idDominioEscrito}');
		enableSelectConValor('idLeoIdiomaSelect', '${ProgramaForm.perfil.idiomaPrincipal.idDominioLectura}');
	}
	function actualizaDominio() {
    	var idIdioma = dijit.byId('idIdioma').get('value');
    	if(idIdioma  == 1 ) {
    		desactivarCertifiaciones();
    		dominioStore.url = '';
    		dominioStore.close();
    		dijit.byId('idDominioIdioma').set('value','0');
			dijit.byId('idDominioIdioma').attr('value', 0); 
			desactivaSelectConValor('idDominioIdioma','');
			setLabelToLabelAndRequerido('idDominioIdioma','idLabelDominioIdioma','Dominio del idioma',false);
    	}else {
    		desactivarCertifiaciones();
    		dominioStore.url = '';
    		dominioStore.close();
    		dijit.byId('idDominioIdioma').reset();
		   	dominioStore.url = '${context}idiomas.do?method=cargarDominio';
    		dominioStore.close();
    		enableSelectConValor('idDominioIdioma', 0);
			dijit.byId('idDominioIdioma').attr('value', 0); 
    		setLabelToLabelAndRequerido('idDominioIdioma','idLabelDominioIdioma','Dominio del idioma',true);	
    	}	
    }
    function toggleIdioma() {
		document.getElementById('idiomaAdicional').style.display = 'block';
		document.getElementById('btnAgregarIdioma').style.display = 'none';
	}
    function actualizaDominioAdd0(idStore) {
		actualizaDominioAdd(idStore,0);
	}
	function actualizaDominioAdd(idStore,value) {
		if (idStore>0) {
			var vIdioma = dijit.byId('selectLanguage_'+idStore).get('value');
			document.getElementById('idiomaAdicional').style.display = 'none';
			dojo.xhrPost({url: 'perfilComplementario.do?method=idiomaDominio&vIdioma='+vIdioma+'&idMultiRegistro='+idStore+'&idDominio='+value ,preventCache: true, form:'ProgramaForm', sync: true, timeout:180000, 
				load: function(data) {
					dijit.byId('idIdiomaAdd').set('value','');
					dijit.byId('idCertificacionIdiomaAdd').set('value','');
					dijit.byId('idDominioIdiomaAdd').set('value','');
					clearWidgetsAndAddHtml('agregaIdioma',data);
				}});
		}else {
			var idIdioma = dijit.byId('idIdiomaAdd').get('value');
			if (idIdioma == 1) {
				setLabelToLabelAndRequerido('idIdiomaAdd','idLabelIdiomaAdd','Idioma adicional al nativo',false);
				setLabelToLabelAndRequerido('idDominioIdiomaAdd','idLabelDominioIdiomaAdd','Dominio del idioma',false);
				desactivaSelectConValor('idDominioIdiomaAdd','');
			}else {
				enableSelectConValor('idDominioIdiomaAdd','');
				setLabelToLabelAndRequerido('idIdiomaAdd','idLabelIdiomaAdd','Idioma adicional al nativo',true);
				setLabelToLabelAndRequerido('idDominioIdiomaAdd','idLabelDominioIdiomaAdd','Dominio del idioma',true);
			}
		}
	}
	function actualizaCertificacionesAdd(store){
    	var vIdioma = dijit.byId('idIdiomaAdd').get('value');
		//Necesitamos el valor guardado del catalogo que es referente a su idoma no lo de la lista
		var vIdIdiomaDep = depIdioma[vIdioma];
		var vDominio = dijit.byId('idDominioIdiomaAdd').get('value');
		certificacionAddStore.url = '';
		if (vIdioma == 1) {
			desactivaSelectConValor('idCertificacionIdiomaAdd','');
			setLabelToLabelAndRequerido('idCertificacionIdiomaAdd', 'idLabelCertificacionIdiomaAdd', '¿Cuentas con certificación?', false);
		}else {
			if (vDominio == 3 || vDominio == 2) {
				certificacionAddStore.url = "${context}idiomas.do?method=certificaciones&idCatDep=" + vIdIdiomaDep;
				enableSelectConValor('idCertificacionIdiomaAdd', 0);
				setLabelToLabelAndRequerido('idCertificacionIdiomaAdd', 'idLabelCertificacionIdiomaAdd', '¿Cuentas con certificación?', true);
			}else {
				desactivaSelectConValor('idCertificacionIdiomaAdd', '');
				setLabelToLabelAndRequerido('idCertificacionIdiomaAdd', 'idLabelCertificacionIdiomaAdd', '¿Cuentas con certificación?', false);
			}
		}
		dijit.byId('idCertificacionIdiomaAdd').reset();
		certificacionAddStore.close();
		setLevelEngAdd(vDominio);
    }
	function actualizaValorAd(campo,valor) {
		if (valor!='') {
			document.getElementById(campo).value=valor;
		} 			
    }
    function setLevelEngAdd(vDominio) {
    	if (vDominio > 2) {
			dijit.byId('idHabloIdiomaAddSelect').attr('value', 3);
			dijit.byId('idEscriboIdiomaAddSelect').attr('value', 3);
			dijit.byId('idLeoIdiomaAddSelect').attr('value', 3);
			dijit.byId('idHabloIdiomaAddSelect').set('disabled', true);
			dijit.byId('idEscriboIdiomaAddSelect').set('disabled', true);
			dijit.byId('idLeoIdiomaAddSelect').set('disabled', true);
		}else {
			enableSelectConValor('idHabloIdiomaAddSelect', '');
			enableSelectConValor('idEscriboIdiomaAddSelect', '');
			enableSelectConValor('idLeoIdiomaAddSelect', '');
		}
    }
    function validaCampoSelect(campo) {
		var control = dijit.byId(campo);	
		if (control && control.get('value')=='') {
			dijit.showTooltip(control.get('missingMessage'), control.domNode, control.get('tooltipPosition'), !control.isLeftToRight());
			control.focus();
			document.getElementById(campo).blur();
			control.focus();
			message(control.get('missingMessage'));
			return false;
		}
		return true;
	}
	function validaCampoNoRequerido(campo) {
		var control = dijit.byId(campo);
		if (isDefinedObject(control)) {
			var requerido = control.get('required');
			if (!control.validate()) {
				dijit.byId(campo).focus();
				dojo.byId(campo).blur();
				message(control.get("missingMessage"));
				return false;
			}
		}
		return true;
	}
	function validateLangList() {
    	if (!validatePrincipal()) return false;
    	if (!validateLang(1)) return false;
    	if (!validateLang(2)) return false;
    	if (!validateLang(3)) return false;
    	return true;
	}
	function validatePrincipal() {
		if (!validaCampoSelect('idHabloIdiomaSelect')) return false;
		if (!validaCampoSelect('idEscriboIdiomaSelect')) return false;
		if (!validaCampoSelect('idLeoIdiomaSelect')) return false;
		return true;
	}
	function validateLang(idStore){
		if (!validaCampoNoRequerido('selectLanguage_'+idStore)) return false;
		if (!validaCampoNoRequerido('dominio_'+idStore)) return false;
		if (!validaCampoNoRequerido('certification_'+idStore)) return false;
		if (!validaCampoNoRequerido('habloDominio_'+idStore)) return false;
		if (!validaCampoNoRequerido('escriboDominio_'+idStore)) return false;
		if (!validaCampoNoRequerido('leoDominio_'+idStore)) return false;
		return true;
	}
    function clearAddSetAgregaIdioma(data){
		 clearWidgetsAndAddHtml('agregaIdioma',data);	 
	}
</script>
<c:if test="${not empty ProgramaForm.idiomasDependientes}">
	<script type="text/javascript">
		<c:forEach var="idiomaDep" items="${ProgramaForm.idiomasDependientes}" varStatus="indexIdi">
			depIdioma[parseInt('${indexIdi.count - 1}')] = '${idiomaDep}';
		</c:forEach>
	</script>
</c:if>
<noscript>Funciones de JavaScript desactivadas por navegador</noscript>
<div dojoType="dojo.data.ItemFileReadStore" jsId="idiomaStore" url="${context}perfilComplementario.do?method=idiomas" urlPreventCache="true"></div>
<div dojoType="dojo.data.ItemFileReadStore" jsId="certificacionStore" urlPreventCache="true" clearOnClose="true"></div>
<div dojoType="dojo.data.ItemFileReadStore" jsId="certificacionAddStore" urlPreventCache="true" clearOnClose="true"></div>
<div dojoType="dojo.data.ItemFileReadStore" jsId="certificacionAddStore_1" urlPreventCache="true" clearOnClose="true"></div>
<div dojoType="dojo.data.ItemFileReadStore" jsId="certificacionAddStore_2" urlPreventCache="true" clearOnClose="true"></div>
<div dojoType="dojo.data.ItemFileReadStore" jsId="certificacionAddStore_3" urlPreventCache="true" clearOnClose="true"></div>
<div dojoType="dojo.data.ItemFileReadStore" jsId="dominioStore" url="${context}idiomas.do?method=dominios" urlPreventCache="true" clearOnClose="true"></div>
<div dojoType="dojo.data.ItemFileReadStore" jsId="dominioStore2"url="${context}idiomas.do?method=dominios" urlPreventCache="true" clearOnClose="true"></div>
<div dojoType="dojo.data.ItemFileReadStore" jsId="dominioStore3" url="${context}idiomas.do?method=dominios" urlPreventCache="true" clearOnClose="true"></div>
<div dojoType="dojo.data.ItemFileReadStore" jsId="dominioStoreAdd" url="${context}idiomas.do?method=dominios" urlPreventCache="true" clearOnClose="true"></div>
<div class="formApp miEspacio">
	<form name="ProgramaForm" id="ProgramaForm" method="post" action="perfilComplementario.do" dojoType="dijit.form.Form">
		<input type="hidden" name="method" id="method" value="" />
		<h2>Mi espacio</h2>
		<div class="tab_block">
			<div align="left" id="returnME" style="display: block;">
				<a href="javascript:location.replace('<c:url value="/miEspacioCandidato.do"/>');" class="expand"> <strong>Ir al inicio de Mi espacio</strong></a>
			</div>
			<div class="Tab_espacio">
				<h3>Portafolio de servicios</h3>
				<div class="subTab">
					<ul>
						<li class="curSubTab"><a href="${context}perfilComplementario.do?method=strengthenProfile">Fortalecer Perfil</a></li>
						<c:if test="${ProgramaForm.modalidad.param.toLowerCase()=='mml'}">
							<li class="curSubTab"><span>Idiomas</span></li>
						</c:if>
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
							<legend>Idiomas</legend>
							<div class="margin_top_10">
								<div class="grid1_3 fl">
									<label for="idIdioma"><span>*</span>Idioma adicional al nativo</label>
									<select id="idIdioma" name="idIdioma" required="true" missingMessage="Es necesario indicar el idioma adicional"
										store="idiomaStore" dojotype="dijit.form.FilteringSelect" value="${ProgramaForm.perfil.idiomaPrincipal.idIdioma}"
										disabled="disabled" onchange="javascript:actualizaDominio();">
									</select>
								</div>
								<div class="grid1_3 fl">
									<label id="idLabelDominioIdioma" for="idDominioIdioma"></label>
									<select id="idDominioIdioma" name="idDominioIdioma" dojotype="dijit.form.FilteringSelect"
										required="true" disabled="disabled" store="dominioStore" missingMessage="Es necesario indicar el dominio requerido del idioma"
										onchange="javascript:actualizaCertificaciones();" value="${ProgramaForm.perfil.idiomaPrincipal.idDominio}">
									</select>
								</div>
								<div class="grid1_3 fl margin_no_r">
									<label id="idLabelCertificacionIdioma" for="idCertificacionIdioma">¿Cuentas con certificación?</label>
									<select id="idCertificacionIdioma" name="idCertificacionIdioma" required="false" disabled="disabled"
										store="certificacionStore" dojotype="dijit.form.FilteringSelect" missingMessage="Es necesario indicar la certificacion requerida"
										value="${ProgramaForm.perfil.idiomaPrincipal.idCertificacion}">
									</select>
								</div>
								<div class="grid1_3 margin_top_10 fl">
									<label id="idLabelHabloIdioma" for="idLabelHabloIdioma"><span>*</span> Hablo</label>
									<input type="hidden" name="idDominioHabla" id="idDominioHabla" dojoType="dijit.form.TextBox">
									<select id="idHabloIdiomaSelect" name="idHabloIdiomaSelect" required="false" value="${ProgramaForm.perfil.idiomaPrincipal.idDominioHabla}"
											dojotype="dijit.form.FilteringSelect" missingMessage="Es necesario indicar el nivel de habla del idioma">
											<c:forEach var="row" items="${competencia}">
												<option value="${row.idCatalogoOpcion}">${row.opcion}</option>
											</c:forEach>
									</select>
								</div>
								<div class="grid1_3 margin_top_10 fl">
									<label id="idLabelEscriboIdioma" for="idLabelEscriboIdioma"><span>*</span> Escribo</label>
									<input type="hidden" name="idDominioEscrito" id="idDominioEscrito" dojoType="dijit.form.TextBox">
									<select id="idEscriboIdiomaSelect" name="idEscriboIdiomaSelect" required="false" value="${ProgramaForm.perfil.idiomaPrincipal.idDominioEscrito}"
											dojotype="dijit.form.FilteringSelect" missingMessage="Es necesario indicar el nivel de escritura del idioma">
											<c:forEach var="row" items="${competencia}">
												<option value="${row.idCatalogoOpcion}">${row.opcion}</option>
											</c:forEach>
									</select>
								</div>
								<div class="grid1_3 margin_top_10 fl">
									<label id="idLabelLeoIdioma" for="idLabelLeoIdioma"><span>*</span> Leo</label>
									<input type="hidden" name="idDominioLectura" id="idDominioLectura" dojoType="dijit.form.TextBox">
									<select id="idLeoIdiomaSelect" name="idLeoIdiomaSelect" required="false" value="${ProgramaForm.perfil.idiomaPrincipal.idDominioLectura}"
											dojotype="dijit.form.FilteringSelect" missingMessage="Es necesario indicar el nivel de lectura del idioma">
											<c:forEach var="row" items="${competencia}">
												<option value="${row.idCatalogoOpcion}">${row.opcion}</option>
											</c:forEach>
									</select>
								</div>
							</div>
							<div id="agregaIdioma" name="agregaIdioma">
								<jsp:include page="/jsp/candidatos/misDatos/domainLanguages.jsp"
									flush="true" />
							</div>
							<div id="idiomaAdicional" class="margin_top_30" style="display: none">
								<div class="grid1_3 fl">
									<label id="idLabelIdiomaAdd" for="idIdiomaAdd">Idioma
										adicional al nativo</label> <select id="idIdiomaAdd" name="idIdiomaAdd"
										required="true"
										missingMessage="Es necesario indicar el idioma adicional"
										store="idiomaStore" dojotype="dijit.form.FilteringSelect"
										onchange="javascript:actualizaDominioAdd(0,this.value);">
									</select>
								</div>
								<div class="grid1_3 fl">
									<label id="idLabelDominioIdiomaAdd" for="idDominioIdiomaAdd">Dominio del idioma</label>
									<select id="idDominioIdiomaAdd" name="idDominioIdiomaAdd" required="false" readOnly="false" store="dominioStoreAdd"
										dojotype="dijit.form.FilteringSelect" missingMessage="Es necesario indicar el dominio requerido del idioma"
										onchange="javascript:actualizaCertificacionesAdd(0);">
									</select>
								</div>
								<div class="grid1_3 fl">
									<label id="idLabelCertificacionIdiomaAdd" for="idCertificacionIdiomaAdd">¿Cuentas con certificación?</label>
									<select id="idCertificacionIdiomaAdd" name="idCertificacionIdiomaAdd" store="certificacionAddStore" readOnly="false"
										required="false" dojotype="dijit.form.FilteringSelect" missingMessage="Es necesario indicar la certificacion requerida">
									</select>
								</div>
								<div class="grid1_3 margin_top_10 fl">
									<label id="idLabelHabloIdiomaAdd" for="idLabelHabloIdiomaAdd"><span>*</span> Hablo</label>
									<input type="hidden" name="idHabloIdiomaAdd" id="idHabloIdiomaAdd" dojoType="dijit.form.TextBox">
									<select id="idHabloIdiomaAddSelect" name="idHabloIdiomaAddSelect" required="false" value=""
											dojotype="dijit.form.FilteringSelect" missingMessage="Es necesario indicar el nivel de habla del idioma">
											<c:forEach var="row" items="${competencia}">
												<option value="${row.idCatalogoOpcion}">${row.opcion}</option>
											</c:forEach>
									</select>
								</div>
								<div class="grid1_3 margin_top_10 fl">
									<label id="idLabelEscriboIdiomaAdd" for="idLabelEscriboIdiomaAdd"><span>*</span> Escribo</label>
									<input type="hidden" name="idEscriboIdiomaAdd" id="idEscriboIdiomaAdd" dojoType="dijit.form.TextBox">
									<select id="idEscriboIdiomaAddSelect" name="idEscriboIdiomaAddSelect" required="false" value=""
											dojotype="dijit.form.FilteringSelect" missingMessage="Es necesario indicar el nivel de escritura del idioma">
											<c:forEach var="row" items="${competencia}">
												<option value="${row.idCatalogoOpcion}">${row.opcion}</option>
											</c:forEach>
									</select>
								</div>
								<div class="grid1_3 margin_top_10 fl">
									<label id="idLabelLeoIdiomaAdd" for="idLabelLeoIdiomaAdd"><span>*</span> Leo</label>
									<input type="hidden" name="idLeoIdiomaAdd" id="idLeoIdiomaAdd" dojoType="dijit.form.TextBox">
									<select id="idLeoIdiomaAddSelect" name="idLeoIdiomaAddSelect" required="false" value=""
											dojotype="dijit.form.FilteringSelect" missingMessage="Es necesario indicar el nivel de lectura del idioma">
											<c:forEach var="row" items="${competencia}">
												<option value="${row.idCatalogoOpcion}">${row.opcion}</option>
											</c:forEach>
									</select>
								</div>
								<div class="clearfix"></div>
								<div class="margin_top_10 ta_right" style="width: 918px">
									<input type="button" class="enviar" title="Guardar idioma"
										value="Guardar idioma" onclick="doSubmitAddIdioma();" />
								</div>
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