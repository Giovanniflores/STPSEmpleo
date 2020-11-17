<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.Calendar" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<style>
		#menuAdmin {
		    color: #333333;
		    font-family: Tahoma,Geneva,sans-serif;
		    font-size: 11px;
		    margin: auto;
		    width: 930px;
		}
		#menuAdmin ul {
		    list-style: outside none none;
		    margin-left: 2%;
		    margin-right: 2%;
		    margin-top: 24px;
		}
		#menuAdmin ul li {
		    float: left;
		    height: 36px;
		    margin-bottom: 10px;
		    margin-left: 5px;
		}
		#menuAdmin ul li a.adminCerrar {
		    background: #fe6a08;
		    color: #ffffff;
		}
		#menuAdmin ul li a {
		    border: 1px solid #dbdbdb;
		    color: #333333;
		    padding: 6px;
		    text-align: center;
		    text-decoration: none;
		    width: auto;
		}
	</style>
 <%
 	String context = request.getContextPath() +"/";
 	String vProxy = "http://orangoo.com/newnox?lang=";
	pageContext.setAttribute("vProxy",vProxy);
 %>
<script type="text/javascript" src="js/helper/messageHelper.js"></script>
<script type="text/javascript" src="googiespell/AJS.js"></script>
<script type="text/javascript" src="googiespell/googiespell.js"></script>
<script type="text/javascript" src="googiespell/cookiesupport.js"></script>
<link   href="googiespell/googiespell.css" rel="stylesheet" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<c:set var="regexpedad">^[0-9]{1,3}$</c:set>


<script type="text/javascript">
var depGrado  = new Array(); //Declara arreglo de dependencias de catalogo Grado de Estudios
var depIdioma = new Array(); //Declara arreglo de dependencias de catalogo idiomas
</script>

<c:if test="${not empty ofertaEmpleoForm.gradosDependientes}">
	<script type="text/javascript">
		<c:forEach var="gradoDep" items="${ofertaEmpleoForm.gradosDependientes}" varStatus="indexGr">
			depGrado[parseInt('${indexGr.count - 1}')] = '${gradoDep}';
		</c:forEach>
	</script>
</c:if>

<c:if test="${not empty ofertaEmpleoForm.idiomasDependientes}">
	<script type="text/javascript">
		<c:forEach var="idiomaDep" items="${ofertaEmpleoForm.idiomasDependientes}" varStatus="indexIdi">
			depIdioma[parseInt('${indexIdi.count - 1}')] = '${idiomaDep}';
		</c:forEach>
	</script>
</c:if>
<script type="text/javascript">
	dojo.require("dijit.dijit"); // loads the optimized dijit layer
	dojo.require("dijit.form.Form");
	dojo.require("dijit.Dialog");
	dojo.require("dijit.form.Button");
	dojo.require("dijit.form.ComboBox");
	dojo.require("dojo.data.ItemFileReadStore");
	dojo.require("dijit.form.Textarea");
	dojo.require("dijit.form.TextBox");
	dojo.require("dijit.form.ValidationTextBox");
	dojo.require("dijit.form.DateTextBox");
	dojo.require("dijit.form.FilteringSelect");
	dojo.require("dijit.form.MultiSelect");
	dojo.require("dijit.form.CheckBox");
	dojo.require('dojo.parser');
</script>
<script type="text/javascript">
	dojo.addOnLoad(function(){
	    console.log("page ready, can modify DOM anytime after this");
	});
</script>
<script type="text/javascript">
	function actualizaCarreras(){
		var vGrado = dijit.byId('idNivelEstudio').get('value');
		var vIdCatDep = depGrado[vGrado];
	 	var carrerasAdicionales = document.getElementById('totalCarrerasAdicionales').value;
	 	
	 	if(carrerasAdicionales==2){
	 		
	 		eliminarCarrera(document.getElementById("idCarreraAdicional_2").value);
	 		actualizaCarreras();
	 	}
	 	if(carrerasAdicionales==1){
	 		
	 		eliminarCarrera(document.getElementById("idCarreraAdicional_1").value);
	 		actualizaCarreras();
	 	}
	 	
        if(vIdCatDep && vIdCatDep != 0 ) {            	
        	dijit.byId('idCarreraEspecialidad').set('value','');            	
        	carreraEspecialidadStore.url = "${context}ofertaEmpleo.do?method=carreras&idCatDep="+ vIdCatDep;
        	carreraEspecialidadStore.close();
        	carreraEspecialidadStore.fetch({
             	onComplete: function(items, request) {
             		if (items.length == 0) return;
             		if (items.length == 1){
             			
             			dijit.byId('idCarreraEspecialidad').set('value', items[0].value);
             		}
             	}
       	});
		
        	dijit.byId('idSituacionAcademica').set('value','');            	
        	situacionAcademicaStore.url = "${context}ofertaEmpleo.do?method=situacionesAcademicas&idEscolaridad="+ vGrado;
        	situacionAcademicaStore.close();
        	situacionAcademicaStore.fetch({
             	onComplete: function(items, request) {
             		if (items.length == 0) return;
             		if (items.length == 1){
             			
             			dijit.byId('idSituacionAcademica').set('value', items[0].value);
             		}
             	}
       	});	
        }
	}
	function actualizaDominio() {
    	var idIdioma = dijit.byId('idIdioma').get('value');
    	if (idIdioma  == 1 ) {
    		desactivarCertifiaciones();
    		dominioStore.url = '';
    		dominioStore.close();
    		dijit.byId('idDominioIdioma').set('value','0');
			dijit.byId('idDominioIdioma').attr('value', 0); 
			desactivaSelectConValor('idDominioIdioma','');
			setLabelToLabelAndRequerido('idDominioIdioma','idLabelDominioIdioma','Dominio del idioma',false);	    
    	} else {
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
			}else {
				desactivarCertifiaciones();
			}
		}
    }
	function desactivarCertifiaciones() {
    	certificacionStore.url = '';
		certificacionStore.close();
		desactivaSelectConValor('idCertificacionIdioma','');
		setLabelToLabelAndRequerido('idCertificacionIdioma', 'idLabelCertificacionIdioma', '¿Cuentas con certificación?', false);
    }
    function desactivaSelectConValor(selectList, valor) {
		dijit.byId(selectList).attr('value', valor);
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
	function activarCertificaciones(){
    	var vIdioma = dijit.byId('idIdioma').get('value');
		var vIdIdiomaDep = depIdioma[vIdioma];
    	certificacionStore.url ='${context}idiomas.do?method=certificaciones&idCatDep='+ vIdIdiomaDep;
    	enableSelectConValor('idCertificacionIdioma', 1);
    	setLabelToLabelAndRequerido('idCertificacionIdioma', 'idLabelCertificacionIdioma', '¿Cuentas con certificación?', true);
		certificacionStore.close();
    }
    function setLabelToLabelAndRequerido(id, idLabel, label, requerido) {
		var nodeText = document.createTextNode(label);
		var node = removeNodesElement(idLabel);
		if (requerido == true) {
			dijit.byId(id).set('required', true);
			node = addSpanRequired(node);
		}else {
			dijit.byId(id).set('required', false);
		}
		node.appendChild(nodeText);
	}
	function removeNodesElement(id) {
		var myNode = document.getElementById(id);
		while (myNode.firstChild) {
	    	myNode.removeChild(myNode.firstChild);
		}
		return myNode;
	}
	function addSpanRequired(node){
		var spanNode = document.createElement("SPAN");
		var spanText = document.createTextNode("* ");
		spanNode.appendChild(spanText);
		node.appendChild(spanNode);
		return node;
	}
	function actualizaDominioAdd(idStore,value) {
		if (idStore>0) {
			var vIdioma = dijit.byId('selectLanguage_'+idStore).get('value');
			document.getElementById('idiomaAdicional').style.display = 'none';
			dojo.xhrPost({url: 'ofertaEmpleo.do?method=idiomaDominio&vIdioma='+vIdioma+'&idMultiRegistro='+idStore+'&idDominio='+value ,preventCache: true, form:'registroRequisitos', sync: true, timeout:180000, 
				load: function(data) {
					dijit.byId('idIdiomaAdd').set('value','');
					dijit.byId('idCertificacionIdiomaAdd').set('value','');
					dijit.byId('idDominioIdiomaAdd').set('value','');
					clearWidgetsAndAddHtml('agregaIdioma',data);
				}});
		} else {
			var idIdioma = dijit.byId('idIdiomaAdd').get('value');
			if (idIdioma == 1) {
				setLabelToLabelAndRequerido('idIdiomaAdd','idLabelIdiomaAdd','Idioma adicional al nativo',false);
				setLabelToLabelAndRequerido('idDominioIdiomaAdd','idLabelDominioIdiomaAdd','Dominio del idioma',false);
				desactivaSelectConValor('idDominioIdiomaAdd','');
			} else {
				loadDominio();
				enableSelectConValor('idDominioIdiomaAdd','');
				setLabelToLabelAndRequerido('idIdiomaAdd','idLabelIdiomaAdd','Idioma adicional al nativo',true);
				setLabelToLabelAndRequerido('idDominioIdiomaAdd','idLabelDominioIdiomaAdd','Dominio del idioma',true);
			}
		}
	}
	function clearWidgetsAndAddHtml(name, data){
		clearWidgetsHtml(name);
		var node = removeNodesElement(name);
		if (!checkIE()){
			dojo.byId(name).innerHTML = data;
		} else {
			node.appendChild(adddiv(data));
			addSpanRequired(node);
		}
	 	dojo.parser.parse(name);
	}
	function clearWidgetsHtml(name)	{
		dojo.forEach(dijit.findWidgets(dojo.byId(name)), function(w) {
			w.destroyRecursive();
		});
	}
	function actualizaCertificacionesAdd(store) {
    	var vIdioma = dijit.byId('idIdiomaAdd').get('value');
		var vIdIdiomaDep = depIdioma[vIdioma];
		var vDominio = dijit.byId('idDominioIdiomaAdd').get('value');
		certificacionAddStore.url = '';
		if (vIdioma == 1) {
			desactivaSelectConValor('idCertificacionIdiomaAdd','');
			setLabelToLabelAndRequerido('idCertificacionIdiomaAdd', 'idLabelCertificacionIdiomaAdd', '¿Cuentas con certificación?', false);
		} else {
			if (vDominio == 3 || vDominio == 2) {
				certificacionAddStore.url = "${context}idiomas.do?method=certificaciones&idCatDep=" + vIdIdiomaDep;
				enableSelectConValor('idCertificacionIdiomaAdd', 0);
				setLabelToLabelAndRequerido('idCertificacionIdiomaAdd', 'idLabelCertificacionIdiomaAdd', '¿Cuentas con certificación?', true);
			} else {
				desactivaSelectConValor('idCertificacionIdiomaAdd', '');
				setLabelToLabelAndRequerido('idCertificacionIdiomaAdd', 'idLabelCertificacionIdiomaAdd', '¿Cuentas con certificación?', false);
			}
		}
		dijit.byId('idCertificacionIdiomaAdd').reset();
		certificacionAddStore.close();
    }
    function loadDominio() {
		dominioStoreAdd.url='${context}ofertaEmpleo.do?method=dominios';
		dominioStoreAdd.close();
        dominioStoreAdd.fetch({
        	onComplete: function(items, request) {                  	
            	if (items.length == 0) return;         
              		dijit.byId('idDominioIdiomaAdd').attr('value', items[0].value);
            }
        });
  	}
  	function load() {
		var vIdioma = '${ofertaEmpleoForm.idIdioma}';
		var vIdIdiomaDep = depIdioma[vIdioma];
		if (vIdioma && vIdIdiomaDep != 0 ) {
    		certificacionStore.url = '${context}idiomas.do?method=certificaciones&idCatDep='+ vIdIdiomaDep;
    		certificacionStore.close();
    		certificacionStore.fetch({
         		onComplete: function(items, request) {
         			if (items.length == 0) return;
         				dijit.byId('idCertificacionIdioma').attr('value', '${ofertaEmpleoForm.idCertificacionIdioma}');
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
    	}
		else {
			setLabelToLabelAndRequerido('idDominioIdioma','idLabelDominioIdioma','Dominio del idioma',true);
		}
		var idDominio = '${ofertaEmpleoForm.idDominioIdioma}';
    	if(idDominio ==  2 || idDominio == 3 ) {
    		setLabelToLabelAndRequerido('idCertificacionIdioma','idLabelCertificacionIdioma','¿Cuentas con certificación?',true);
    	}
    	else {
    		setLabelToLabelAndRequerido('idCertificacionIdioma','idLabelCertificacionIdioma','¿Cuentas con certificación?',false);
    	}
	}
	function clearAddSetAgregaIdioma(data){
		 clearWidgetsAndAddHtml('agregaIdioma',data);
	}
	function loadLanguageAdd() {
		idiomaStoreAdd.url='${context}ofertaEmpleo.do?method=idiomas';
		idiomaStoreAdd.close();
        idiomaStoreAdd.fetch({
        	onComplete: function(items, request) {                  	
            	if (items.length == 0) return;         
              		dijit.byId('idIdiomaAdd').attr('value', items[0].value);
            }
        });
  	}
  	function eliminarIdioma(idIdioma,idCertificacion){
		var idIdiomaEliminar=idIdioma;
		var idCertificacionEliminar=idCertificacion;
		document.getElementById('idiomaAdicional').style.display = 'none';
		dojo.xhrPost({url: 'ofertaEmpleo.do?method=eliminarIdiomaAdicional&idIdiomaEliminar='+idIdiomaEliminar+'&idCertificacionIdiomaEliminar='+idCertificacionEliminar,preventCache: true, form:'registroRequisitos', sync: true, timeout:180000, 
			  load: function(data) {
				  dijit.byId('idIdiomaAdd').set('value','');
				  dijit.byId('idCertificacionIdiomaAdd').set('value','');
				  dijit.byId('idDominioIdiomaAdd').set('value','');
				  clearAddSetAgregaIdioma(data);
			  }});
	}
	function alertMsg(msg) {
		message(msg);
	}
	function edadRequerida(param){
		if (param) {
		document.getElementById('edadMinima').readOnly=false;
		dijit.byId('edadMinima').set('value','');
		document.getElementById('edadMaxima').readOnly=false;
		dijit.byId('edadMaxima').set('value','');
		document.getElementById('spanDe').className = "";
		document.getElementById('spanA').className = "";
		} else {
			document.getElementById('edadMinima').readOnly=true;
			dijit.byId('edadMinima').set('value','');
			document.getElementById('edadMaxima').readOnly=true;
			dijit.byId('edadMaxima').set('value','');
			document.getElementById('spanDe').className = "desactivado";
			document.getElementById('spanA').className = "desactivado";
		}
	}
	function doSubmit(method,idBoton) {
		dojo.byId(idBoton).disabled= true;
		if (!validaCampos()) {
			dojo.byId(idBoton).disabled= false;
			return;
		}
		if (dojo.byId('conocimientoCompNingunoC').checked) document.getElementById('conocimientoCompNinguno').value=1;
		if (dojo.byId('conocimientoCompProcesadorTxtC').checked) document.getElementById('conocimientoCompProcesadorTxt').value=1;
		if (dojo.byId('conocimientoCompHojaCalC').checked) document.getElementById('conocimientoCompHojaCal').value=1;
		if (dojo.byId('conocimientoCompInternetC').checked) document.getElementById('conocimientoCompInternet').value=1;
		if (dojo.byId('conocimientoCompRedesC').checked) document.getElementById('conocimientoCompRedes').value=1;
		document.registroRequisitos.method.value=method;
		document.registroRequisitos.submit();
	}
	function gotoCancelInfUbi() {
		document.registroRequisitos.method.value='cancelarEdicionOferta';
		document.registroRequisitos.submit();
	}
	function doSubmitCancelar() {
		var input = '<input id="btnCnl" class="boton_naranja" type="button" name="btnCnl" onClick="gotoCancelInfUbi();" value="Aceptar"/>';
		messageInput('Los datos no guardados se perderán ¿Continuar?',input);
	}
	function validaCampos() {
		var vFuente = '${ofertaEmpleoForm.fuente}';
		if (!dijit.byId('registroRequisitos').isValid()) {
			if (!validaCampoSelect('idNivelEstudio')) return false;
			if (!validaCampoSelect('idCarreraEspecialidad')) return false;
			if (!validaCampoSelect('idSituacionAcademica')) return false;
			/*if (document.getElementById('habilidadGeneral') && document.getElementById('habilidadGeneral').value==''){
				alertMsg('Favor de registrar los conocimientos y habilidades necesarias para el puesto.');
				document.getElementById('habilidadGeneral').focus();
				return false;
			}*/
			if (!validaCampoSelect('aniosExperiencia')) return false;
			if (vFuente!=9) {
				if(dojo.byId('edadrequisitoSi').checked) {	
					if (!validaCampo('edadMinima')) return false;
					if (!validaCampo('edadMaxima')) return false;
					var edadMinima = dijit.byId('edadMinima').get('value');
					var edadMaxima = dijit.byId('edadMaxima').get('value');
					if (parseInt(edadMaxima)<parseInt(edadMinima)){
						alertMsg('La edad mínima debe ser menor a la edad máxima requerida.');
						return false;
					}
					if(dijit.byId('edadMinima').get('value')<14){
						alertMsg('La edad mínima permitida es de 14 años.');
						return false;
					}
				}			
			}
			if (dijit.byId('conocimiento').get('value')!='') {
				if (!validaCampoSelect('idExperienciaConocimiento')) return false;
			}
			// Seleccion de habilidades
			var ischecked = false;
			var habilidaChecks = document.registroRequisitos.idHabilidad;
			var numeroHabilidades = 0;
			if (habilidaChecks && habilidaChecks.length){
				for (var i=0;i<habilidaChecks.length;i++) {
					if (habilidaChecks[i].checked) {
						numeroHabilidades++;
					}
				}			
			}
			if(numeroHabilidades>0)ischecked = true;
			if (!ischecked){
				alertMsg('Debe elegir por lo menos una habilidad');
				return false;
			}
			if(numeroHabilidades>5){
				alertMsg('Debe elegir máximo 5 habilidades y actitudes necesarias para el puesto');
				return false;
			}
			if (!validaCampoSelect('idIdioma')) return false;
			if (dijit.byId('idIdioma').get('value')!=1) {
				if (!validaCampoSelect('idDominioIdioma')) return false;
			}
			//if (!validaCampoSelect('idCertificacionIdioma')) return false;
			if (dijit.byId('idDominioIdioma').get('value')!=1&&dijit.byId('idIdioma').get('value')!=1) {
				if (!validaCampoSelect('idCertificacionIdioma')) return false;
			}
			if (!dojo.byId('conocimientoCompNingunoC').checked &&
					!dojo.byId('conocimientoCompProcesadorTxtC').checked &&
					!dojo.byId('conocimientoCompHojaCalC').checked &&
					!dojo.byId('conocimientoCompInternetC').checked &&
					!dojo.byId('conocimientoCompRedesC').checked &&
					dojo.byId('conocimientoCompOtros').value=='') {
					document.getElementById('conocimientoCompNingunoC').focus();
					alertMsg('Es necesario que indique los conocimientos en computación.');
					return false;
			}
		}
		return true;
	}
	function limpiaConocimientosComp(param){
		
		if(param==0){
			dojo.byId('conocimientoCompProcesadorTxtC').checked = false;
			dojo.byId('conocimientoCompHojaCalC').checked = false;
			dojo.byId('conocimientoCompInternetC').checked = false;
			dojo.byId('conocimientoCompRedesC').checked = false;
			dijit.byId('conocimientoCompOtros').set('value','');
		}else{
			
			
			dojo.byId('conocimientoCompNingunoC').checked=false;
			
		}
		
		
		
		
	}
	
function validaCampo(campo){
	var control = dijit.byId(campo);
	
	if (control && control.value==''){
		alertMsg(control.get("missingMessage"));
		displayErrorMsg(dijit.byId(campo), dijit.byId(campo).get("missingMessage"));
		control.focus();
		document.getElementById(campo).blur();
		control.focus();
		return false;
	}
	
	if (!dijit.byId(campo).isValid()){
		alertMsg(control.get("invalidMessage"));
		displayErrorMsg(dijit.byId(campo), dijit.byId(campo).get("invalidMessage"));
		control.focus();
		document.getElementById(campo).blur();
		control.focus();
		return false;
	}

	return true;
}

	function isValidSelect(campo) {
		var control = dijit.byId(campo);
		if (control && control.get('value')==0) {
			messageFocus(control.get("missingMessage"), campo);
			dijit.showTooltip(control.get("missingMessage"), control.domNode, control.get("tooltipPosition"), !control.isLeftToRight());
			//control.focus();
			document.getElementById(campo).blur();
			//control.focus();
			return false;
		}
		return true;
	}
	
	function validaCampoSelect(campo) {
		var control = dijit.byId(campo);
		if (control && control.get('value')=='') {
			messageFocus(control.get("missingMessage"), campo);
			dijit.showTooltip(control.get("missingMessage"), control.domNode, control.get("tooltipPosition"), !control.isLeftToRight());
			//control.focus();
			document.getElementById(campo).blur();
			//control.focus();
			return false;
		}
		return true;
	}

function doSubmitAddCarrera() {
	if (validaCampoSelect('idCarreraEspecialidadAdd'))
		addCarrera();		
}

function addCarrera() {
	var idCarreraEspecialidadAdd=dijit.byId('idCarreraEspecialidadAdd').get('value');
	var idNivelEstudio=dijit.byId('idNivelEstudio').get('value');
	document.getElementById('carreraAdicional').style.display = 'none';		
	dojo.xhrPost({url: 'ofertaEmpleo.do?method=carreraAdicional&idCarreraEspecialidadAdd='+idCarreraEspecialidadAdd+'&idNivelEstudio='+idNivelEstudio, form:'registroRequisitos', sync: true,preventCache: true, timeout:180000, 
				  load: function(data) {					 
					  dijit.byId('idCarreraEspecialidadAdd').set('value','');
					  	dojo.byId('agregaCarrera').innerHTML=data;
					  	dojo.parser.parse("agregaCarrera");					  	
				  }});
}

function toggle() {
	if (validaCampoSelect('idNivelEstudio')){
		document.getElementById('carreraAdicional').style.display = 'block';
		document.getElementById('bAgregarCarrera').style.display = 'none';
	}
}


function eliminarCarrera(id){
	
	var idCarreraEliminar=id;
	document.getElementById('carreraAdicional').style.display = 'none';
	
	dojo.xhrPost({url: '${context}ofertaEmpleo.do?method=eliminarCarreraAdicional&idCarreraEliminar='+idCarreraEliminar, form:'registroRequisitos', sync: true,preventCache: true, timeout:180000, 
				  load: function(data) {
					 
					  dijit.byId('idCarreraEspecialidadAdd').set('value','');
					  	dojo.byId('agregaCarrera').innerHTML=data;
					  	dojo.parser.parse("agregaCarrera");
					  	
				  }});
}


function doSubmitAddConocimiento() {
	if(dijit.byId('conocimientoAdd').get('value')!=''){
		
		if (!validaCampoSelect('idExperienciaConocimientoAdd')) return ;
		else addConocimiento();
	}else {
		messageFocus('Debe especificar el conocimiento adicional de lo contrario no se almacenara.');
	}
				
}

function addConocimiento() {
	var conocimientoAdd=dijit.byId('conocimientoAdd').get('value');
	var idExperienciaConocimientoAdd=dijit.byId('idExperienciaConocimientoAdd').get('value');
	document.getElementById('conocimientoAdicional').style.display = 'none';
	dojo.xhrPost({url: 'ofertaEmpleo.do?method=conocimientoAdicional&conocimientoAdd='+conocimientoAdd+'&idExperienciaConocimientoAdd='+idExperienciaConocimientoAdd,preventCache: true, form:'registroRequisitos', sync: true, timeout:180000, 
				  load: function(data) {
					  dijit.byId('conocimientoAdd').set('value','');
					  dijit.byId('idExperienciaConocimientoAdd').set('value','');
					  clearAddSetKnow(data);
					  //dojo.byId('agregaConocimiento').innerHTML=data;
					  //dojo.parser.parse("agregaConocimiento");
				  }});
}

function clearAddSetKnow(data) {
	clearWidgetsAndAddHtml('agregaConocimiento',data);
}

function eliminarConocimiento(conocimiento){
	var conocimientoEliminar=conocimiento;
	document.getElementById('conocimientoAdicional').style.display = 'none';
	dojo.xhrPost({url: 'ofertaEmpleo.do?method=eliminarConocimientoAdicional&conocimientoEliminar='+conocimientoEliminar,preventCache: true, form:'registroRequisitos', sync: true, timeout:180000, 
				  load: function(data) {
					  dijit.byId('conocimientoAdd').set('value','');
					  dijit.byId('idExperienciaConocimientoAdd').set('value','');
					  	//dojo.byId('agregaConocimiento').innerHTML=data;
					  	//dojo.parser.parse("agregaConocimiento");
					  	clearAddSetKnow(data);
				  }});
	
	
	
}

function toggleConocimientos() {
	if (dijit.byId('conocimiento').get('value')=='') {
		message("Ingrese primero un conocimiento principal");
	}else if (dijit.byId('idExperienciaConocimiento').get('value')=='') {
		message("Seleccione experiencia del conocimiento principal");
	}else {
		document.getElementById('conocimientoAdicional').style.display = 'block';
		document.getElementById('bAgregarConocimiento').style.display = 'none';
	}
}


function doSubmitAddHabilidad() {
	if(dijit.byId('habilidadAdd').get('value')!=''){
		
		if (!validaCampoSelect('idExperienciaHabilidadAdd')) return ;
		else addHabilidad();
	}else{
		
		alert('Debe especificar la habilidad adicional de lo contrario no se almacenera.');
		
	}
				
}

function addHabilidad() {
	var habilidadAdd=dijit.byId('habilidadAdd').get('value');
	var idExperienciaHabilidadAdd=dijit.byId('idExperienciaHabilidadAdd').get('value');
	
	document.getElementById('habilidadAdicional').style.display = 'none';
	dojo.xhrPost({url: 'ofertaEmpleo.do?method=habilidadAdicional&habilidadAdd='+habilidadAdd+'&idExperienciaHabilidadAdd='+idExperienciaHabilidadAdd,preventCache: true, form:'registroRequisitos', sync: true, timeout:180000, 
				  load: function(data) {
					  dijit.byId('habilidadAdd').set('value','');
					  dijit.byId('idExperienciaHabilidadAdd').set('value','');
					  	dojo.byId('agregaHabilidad').innerHTML=data;
					  	dojo.parser.parse("agregaHabilidad");
					  	
				  }});
	
	
}

function eliminarHabilidad(habilidad){
	
	var habilidadEliminar=habilidad;
	document.getElementById('habilidadAdicional').style.display = 'none';
	dojo.xhrPost({url: 'ofertaEmpleo.do?method=eliminarHabilidadAdicional&habilidadEliminar='+habilidadEliminar,preventCache: true, form:'registroRequisitos', sync: true, timeout:180000, 
				  load: function(data) {
					  dijit.byId('habilidadAdd').set('value','');
					  dijit.byId('idExperienciaHabilidadAdd').set('value','');
					  	dojo.byId('agregaHabilidad').innerHTML=data;
					  	dojo.parser.parse("agregaHabilidad");
					  	
				  }});
	
	
	
}

function toggleHabilidad() {
	document.getElementById('habilidadAdicional').style.display = 'block';
	document.getElementById('bAgregarHabilidad').style.display = 'none';
}

function doSubmitAddIdioma() {
	var idIdiomaPrincipal=dijit.byId('idIdioma').get('value');
	//var idCertificacionIdiomaPrincipal=dijit.byId('idCertificacionIdioma').get('value');	
	var idIdiomaAdd=dijit.byId('idIdiomaAdd').get('value');
	//var idCertificacionIdiomaAdd=dijit.byId('idCertificacionIdiomaAdd').get('value');
	if (idIdiomaPrincipal==idIdiomaAdd) {
		message('Los idiomas adicionales deben ser diferentes al idioma principal');
		return false;
	}
	var idIdiomaAdicional_1 = document.getElementById('idIdiomaAdicional_1');
	if (null != idIdiomaAdicional_1 && (idIdiomaAdicional_1.value==idIdiomaAdd)) {
		message('Los idiomas adicionales deben ser diferentes');
		return false;
	}
	if (!isValidSelect('idIdiomaAdd')) return false;
	if (!isValidSelect('idDominioIdiomaAdd')) return false;
	if (!validaCampoNoRequerido('idCertificacionIdiomaAdd')) return false;
	addIdioma();
}

function addIdioma() {
	var idIdiomaAdd=dijit.byId('idIdiomaAdd').get('value');
	var idCertificacionIdiomaAdd=dijit.byId('idCertificacionIdiomaAdd').get('value');
	var idDominioIdiomaAdd=dijit.byId('idDominioIdiomaAdd').get('value');
	document.getElementById('idiomaAdicional').style.display = 'none';	
	dojo.xhrPost({url: 'ofertaEmpleo.do?method=idiomaAdicional&idIdiomaAdd='+idIdiomaAdd+'&idCertificacionIdiomaAdd='+idCertificacionIdiomaAdd+'&idDominioIdiomaAdd='+idDominioIdiomaAdd,preventCache: true, form:'registroRequisitos', sync: true, timeout:180000, 
				  load: function(data) {
					  dijit.byId('idIdiomaAdd').set('value','');
					  dijit.byId('idCertificacionIdiomaAdd').set('value','');
					  dijit.byId('idDominioIdiomaAdd').set('value','');
					  clearAddSetAgregaIdioma(data);
				  }});
}

function toggleIdioma() {
	loadLanguageAdd();
	document.getElementById('idiomaAdicional').style.display = 'block';
	document.getElementById('bAgregarIdioma').style.display = 'none';
}

function actulizaCertificacionesAdd(idStore,vIdioma){

	if(idStore>0){
		document.getElementById('idiomaAdicional').style.display = 'none';
		dojo.xhrPost({url: 'ofertaEmpleo.do?method=idiomaCertificacion&vIdioma='+vIdioma+'&idMultiRegistro='+idStore,preventCache: true, form:'registroRequisitos', sync: true, timeout:180000, 
			  load: function(data) {
				  dijit.byId('idIdiomaAdd').set('value','');
				  dijit.byId('idCertificacionIdiomaAdd').set('value','');
				  dijit.byId('idDominioIdiomaAdd').set('value','');
				  dojo.byId('agregaIdioma').innerHTML=data;
				  //dojo.parser.parse("agregaIdioma");
				  dojo.parser.instantiate([dojo.byId('agregaIdioma')]);	
			  }});
	}
	
	var vIdIdiomaDep = depIdioma[vIdioma];
	 if(vIdIdiomaDep && vIdIdiomaDep != 0 ) {
    	if(idStore==0){
    		certificacionAddStore.url = "${context}ofertaEmpleo.do?method=certificaciones&idCatDep="+ vIdIdiomaDep;
    		certificacionAddStore.close();
    		dijit.byId('idCertificacionIdiomaAdd').attr('value', 2); //FIXME: checar si es necesario
    	}
	}
	 
	if(vIdioma==1){     	
		 document.getElementById('idCertificacionIdiomaAdd').readOnly=true;
		 document.getElementById('idDominioIdiomaAdd').readOnly=true;
		 dijit.byId('idDominioIdiomaAdd').disabled=true;
         dijit.byId('idCertificacionIdiomaAdd').attr('value', 2);     	
    }else{     	
    	 document.getElementById('idCertificacionIdiomaAdd').readOnly=false;
    	 document.getElementById('idDominioIdiomaAdd').readOnly=false;
    	 dijit.byId('idDominioIdiomaAdd').disabled=false;    	
     	 dijit.byId('idDominioIdiomaAdd').reset();     	
    }
	 
}

function valorCertificacion(campo){
	
	alert(campo.id);
	
}

function actualizaValorAd(campo,valor){
	
	if(valor!=''){
		document.getElementById(campo).value=valor;	
	}
}

function enviarAMisOfertas() {
	document.registroRequisitos.action = '<c:url value="/OfertaNavegacion.do?method=init"/>';
	document.registroRequisitos.submit();
}	

dojo.addOnLoad(function() {
	var vIdCatDep = depGrado['${ofertaEmpleoForm.idNivelEstudio}'];
	load();
    if(vIdCatDep && vIdCatDep != 0 ) {            	
    	carreraEspecialidadStore.url = "${context}ofertaEmpleo.do?method=carreras&idCatDep="+ vIdCatDep;
    	carreraEspecialidadStore.close();
    	carreraEspecialidadStore.fetch({
         	onComplete: function(items, request) {
         		if (items.length == 0) return;
         		dijit.byId('idCarreraEspecialidad').attr('value', '${ofertaEmpleoForm.idCarreraEspecialidad}');
         	}
   	});
    	var vGrado = '${ofertaEmpleoForm.idNivelEstudio}';
      	situacionAcademicaStore.url = "${context}ofertaEmpleo.do?method=situacionesAcademicas&idEscolaridad="+ vGrado;
    	situacionAcademicaStore.close();
    	situacionAcademicaStore.fetch({
         	onComplete: function(items, request) {
         		if (items.length == 0) return;
         		dijit.byId('idSituacionAcademica').set('value','${ofertaEmpleoForm.idSituacionAcademica}');
         		
         	}
   	});	
    	
    }
    
    var edadRequisito = '${ofertaEmpleoForm.edadRequisito}';
    
    if(edadRequisito != 2){
    	document.getElementById('edadMinima').readOnly=true;
		dijit.byId('edadMinima').set('value','');
		document.getElementById('edadMaxima').readOnly=true;
		dijit.byId('edadMaxima').set('value','');
		document.getElementById('spanA').className = "desactivado";
		
    }
    
    var genero = '${ofertaEmpleoForm.genero}';
   
    if(genero==1)dojo.byId('generoH').checked=true;
    if(genero==2)dojo.byId('generoM').checked=true;
    if(genero!=1&&genero!=2)dojo.byId('generoN').checked=true;
    var conocimientoComp = "${fn:trim(fn:replace(ofertaEmpleoForm.conocimientoComputacion, '\\n|\\r|\\t', ''))}";
    var conocimientoCompNingunoC = '${ofertaEmpleoForm.conocimientoCompNinguno}';
    if(conocimientoComp!=null && conocimientoComp!= ''){
    	
    	var procesadorTxt= '${ofertaEmpleoForm.conocimientoComputacion.procesadorTxt}';
    	var hojaCalculo= '${ofertaEmpleoForm.conocimientoComputacion.hojaCalculo}';
    	var internet= '${ofertaEmpleoForm.conocimientoComputacion.internet}';
    	var redesSociales= '${ofertaEmpleoForm.conocimientoComputacion.redesSociales}';
    	var otros= "${fn:trim(fn:replace(ofertaEmpleoForm.conocimientoComputacion.otros, '\\n|\\r|\\t', ''))}";
    	
    	
    	if(procesadorTxt=='1') document.getElementById('conocimientoCompProcesadorTxtC').checked=true;
    	if(hojaCalculo=='1') document.getElementById('conocimientoCompHojaCalC').checked=true;
    	if(internet=='1') document.getElementById('conocimientoCompInternetC').checked=true;
    	if(redesSociales=='1') document.getElementById('conocimientoCompRedesC').checked=true;
    	document.getElementById('conocimientoCompOtros').value=otros;
    	
    }else if(conocimientoCompNingunoC>0){
    	
    	document.getElementById('conocimientoCompNingunoC').checked=true;
    	
    	
    }
    
    
    var viajar = '${ofertaEmpleoForm.viajar}';
    var radicar = '${ofertaEmpleoForm.radicar}';
   
    if(viajar==1)dojo.byId('viajarNo').checked=true;
    if(viajar==2)dojo.byId('viajarSi').checked=true;
    if(radicar==1)dojo.byId('radicarNo').checked=true;
    if(radicar==2)dojo.byId('radicarSi').checked=true;
    
    
   
}); 

function edicionValidadion(){
	alert('Favor de llenar los campos marcados como obligatorios');
}

	function maximaLongitud(texto,maxlong)
  	{
  	var tecla, int_value, out_value;

  	if (texto.value.length > maxlong)
  	{
  	/*con estas 3 sentencias se consigue que el texto se reduzca
  	al tamaño maximo permitido, sustituyendo lo que se haya
  	introducido, por los primeros caracteres hasta dicho limite*/
  	in_value = texto.value.replace(/^\s+|\s*[\r\n][\r\n \t]*/g, "");
  	out_value = in_value.substring(0,maxlong);
  	texto.value = out_value;
  	texto.value = texto.value;
  	return false;
  	}
  	return true;
  	}
	function gotoInformacionUbicacion() { 
  		document.registroRequisitos.requisitos.value="false";
  		document.registroRequisitos.informacion.value="true";
  		document.registroRequisitos.method.value='cancelarEdicionOferta';
  		document.registroRequisitos.submit();
  	}
	function gotoDatosContacto() { 
  		document.registroRequisitos.requisitos.value="false";
  		document.registroRequisitos.fin.value="true";
  		document.registroRequisitos.method.value='cancelarEdicionOferta';
  		document.registroRequisitos.submit();
  	}
  	function cambiaSeccion(seccion) {
  		var input;
  		if (seccion==4)
  			input = '<input id="btnSec" class="boton_naranja" type="button" name="btnSec" onClick="gotoInformacionUbicacion();" value="Aceptar"/>';
  		if (seccion==6)
  			input = '<input id="btnSec" class="boton_naranja" type="button" name="btnSec" onClick="gotoDatosContacto();" value="Aceptar"/>';
  		messageInput('Los datos no guardados se perderán ¿Continuar?',input);
  	}
  	function messageInput(msg,input) {
		var html =
			'<div id="messageDialgop2" name="messageDialgop2">' +
				'<p style="text-align: center;">'+ msg +'</p><br>'+
				'<p class="form_nav">' + input + cerrarMsg() + 
			'</div>';

		dialogMsg = new dijit.Dialog({
	        title: 'Mensaje',
	        content: html,
	        style: "width:300px;",
	        showTitle: false, draggable : false, closable : false,
	        hide: function(){
	        	clearWidgetsHtml('messageDialgop2');
	        	dialogMsg.destroy()
	        }
	    });			
		dialogMsg.show();
	}
  	function displayErrorMsg(textBox, errmsg){
  		var originalValidator = textBox.validator;
  		textBox.validator = function() {return false;};
  		textBox.validate();
  		textBox.validator = originalValidator;
  		
  		dijit.showTooltip(
  			    //textBox.get("invalidMessage"),
  			    errmsg,
  			    textBox.domNode, 
  			    textBox.get("tooltipPosition"),
  			    !textBox.isLeftToRight()
  		);
  	}

</script>

</head>
<body>

<div dojoType="dojo.data.ItemFileReadStore" jsId="escolaridadStore"         url="${context}ofertaEmpleo.do?method=escolaridad" urlPreventCache="true"></div>
<div dojoType="dojo.data.ItemFileReadStore" jsId="carreraEspecialidadStore"  urlPreventCache="true" clearOnClose="true"></div>
<div dojoType="dojo.data.ItemFileReadStore" jsId="situacionAcademicaStore"   urlPreventCache="true" clearOnClose="true"></div>
<div dojoType="dojo.data.ItemFileReadStore" jsId="experienciaTotalStore"     url="${context}ofertaEmpleo.do?method=experiencia" urlPreventCache="true"></div>
<div dojoType="dojo.data.ItemFileReadStore" jsId="idiomaStore"               url="${context}idiomas.do?method=idiomas" urlPreventCache="true"></div>
<div dojoType="dojo.data.ItemFileReadStore" jsId="idiomaStoreAdd"            url="${context}ofertaEmpleo.do?method=idiomas" urlPreventCache="true"></div>
<div dojoType="dojo.data.ItemFileReadStore" jsId="certificacionStore"        urlPreventCache="true" clearOnClose="true"></div>
<div dojoType="dojo.data.ItemFileReadStore" jsId="certificacionAddStore"        urlPreventCache="true" clearOnClose="true"></div>
<div dojoType="dojo.data.ItemFileReadStore" jsId="certificacionAddStore_1"        urlPreventCache="true" clearOnClose="true"></div>
<div dojoType="dojo.data.ItemFileReadStore" jsId="certificacionAddStore_2"        urlPreventCache="true" clearOnClose="true"></div>
<div dojoType="dojo.data.ItemFileReadStore" jsId="certificacionAddStore_3"        urlPreventCache="true" clearOnClose="true"></div>
<div dojoType="dojo.data.ItemFileReadStore" jsId="dominioStore"              url="${context}registro.do?method=dominios" urlPreventCache="true"></div>
<div dojoType="dojo.data.ItemFileReadStore" jsId="dominioStoreAdd"           url="${context}ofertaEmpleo.do?method=dominios" urlPreventCache="true" clearOnClose="true"></div>
<div style="clear: both"></div>
<div class="tab_block">
	<div align="left" id="returnME" style="display:block;">
		<a href="javascript:location.replace('<c:url value="/miEspacioEmpresas.do"/>');" class="expand">
			<strong>Ir al inicio de Mi espacio</strong>
		</a>
	</div>
	<div class="Tab_espacio">
		<h3>Administrar mis ofertas de empleo</h3>
		<div class="subTab">
				
					<c:if test="${ofertaEmpleoForm.idOfertaEmpleo>0}">
						<c:if test="${not empty USUARIO_APP && USUARIO_APP.empresa}">
							<div class="nav_miPerfil">
								<ul>
								    <li><a href="<c:url value="/OfertaNavegacion.do?method=init"/>">Ver mis ofertas de empleo</a></li>
							    	<li><a href="${context}ofertaEmpleo.do?method=init">Crear oferta de empleo</a></li>
							    	<li class="curSubTab"><span>Editar oferta de empleo</span></li>
							    </ul>
							</div>
						</c:if>
					    <div class="nav_misOfertas">
							<ul>
					        	<li class="nav_ubicacion"><a href="javascript:cambiaSeccion(4);">Información y ubicación de la oferta de empleo</a></li>	
					            <li class="curSubTab"><span>Requisitos de la oferta de empleo</span></li>
					            <li class="nav_contacto"><a href="javascript:cambiaSeccion(6);">Datos de contacto</a></li>
					        </ul>
					    </div>
					    <div class="clearfix"></div>
					    
					</c:if>
					<c:if test="${ofertaEmpleoForm.idOfertaEmpleo==0}">
						<c:if test="${not empty USUARIO_APP && USUARIO_APP.empresa}">
							<div class="nav_miPerfil">
								<ul>
							    	<li class="curSubTab">
							    		<span>Crear oferta de empleo</span>
							    	</li>
								    <li><a href="<c:url value="OfertaNavegacion.do?method=init"/>">Mis ofertas de empleo</a></li>	
								    <li><a href="<c:url value="/RecuperaOfertas.do?method=init"/>">Utiliza oferta como plantilla</a></li>
								    <li><a href="<c:url value="/reporteOfertasEmpresa.do?method=init"/>">Reporte de ofertas de empleo</a></li>	    	
							    </ul>
							</div>
						</c:if>
					</c:if>
					</div>
				<div class="clearfix"></div>
			</div>
			<div class="clearfix"></div>
		</div>
		<p class="instruc_01"><strong>Crea una oferta de empleo siguiendo tres sencillos pasos</strong></p>
		<div class="form_edge">
			<div class="stepApp">
				<h2><img alt="Paso 2 de 3. Requisitos de los candidatos" src="css/images/paso_2c.png" /></h2>
			</div>
		</div>
		<p class="labeled">Los datos marcados con <span>*</span> son obligatorios</p>



<form name="registroRequisitos" id="registroRequisitos" action="ofertaEmpleo.do" method="post" dojoType="dijit.form.Form">
<input type="hidden" name="method" id="method" value="init">
<input type="hidden" name="fin" id="fin" value="false">
<input type="hidden" name="informacion" id="informacion" value="false">
<input type="hidden" name="requisitos" id="requisitos" value="true">
<input type="hidden" name="idOfertaCarrera" id="idOfertaCarrera" value="${ofertaEmpleoForm.idOfertaCarrera}">
<input type="hidden" name="idOfertaRequisitoConocimiento" id="idOfertaRequisitoConocimiento" value="${ofertaEmpleoForm.idOfertaRequisitoConocimiento}">
<input type="hidden" name="idOfertaRequisitoHabilidad" id="idOfertaRequisitoHabilidad" value="${ofertaEmpleoForm.idOfertaRequisitoHabilidad}">
<input type="hidden" name="conocimientoCompNinguno" id="conocimientoCompNinguno" value="0">
<input type="hidden" name="conocimientoCompProcesadorTxt" id="conocimientoCompProcesadorTxt" value="0">
<input type="hidden" name="conocimientoCompHojaCal" id="conocimientoCompHojaCal" value="0">
<input type="hidden" name="conocimientoCompInternet" id="conocimientoCompInternet" value="0">
<input type="hidden" name="conocimientoCompRedes" id="conocimientoCompRedes" value="0">


<fieldset class="requsitios bloque">
		<legend>Escolaridad y otros conocimientos</legend>
		<div class="labeled">Escolaridad</div>
		<div>
			<div class="grid1_3 fl">
				<label for="idNivelEstudio"><span>*</span> Escolaridad mínima</label>
				<select id="idNivelEstudio" name="idNivelEstudio"
				        required="true" missingMessage="Debe indicar la escolaridad requerida."
						store="escolaridadStore" dojotype="dijit.form.FilteringSelect"
						value="${ofertaEmpleoForm.idNivelEstudio}"
						autocomplete="true"
					 onchange="javascript:actualizaCarreras();">
				</select>
			</div>
			<div class="clearfix"></div>
			
			<div class="grid1_3 fl">
				<label for="idCarreraEspecialidad"><span>*</span> Carrera o especialidad</label>
				<select id="idCarreraEspecialidad" name="idCarreraEspecialidad"
				        required="true" missingMessage="Es necesario indicar la carrera o especialidad."
				        store="carreraEspecialidadStore" dojotype="dijit.form.FilteringSelect"
				         maxheight="250" title="${ofertaEmpleoForm.idCarreraEspecialidad}"
				         value="${ofertaEmpleoForm.idCarreraEspecialidad}">
				</select> 
			</div>
			<div class="clearfix"></div>
		</div>	
		<div id="agregaCarrera" name="agregaCarrera"  class="textBox80">
			<jsp:include page="/jsp/oferta/carrerasAdicionales.jsp" flush="true" />
		</div>
		<div id="carreraAdicional" style="display:none" >
			<div class="textBox100">  
				<label for="idCarreraEspecialidadAdd">Carrera o especialidad</label>
				<select id="idCarreraEspecialidadAdd" name="idCarreraEspecialidadAdd"
					        required="true" missingMessage="Es necesario indicar la carrera o especialidad."
					        store="carreraEspecialidadStore" dojotype="dijit.form.FilteringSelect"
							maxheight="250" value="">
				</select>
				<p class="agregar_bloque bis">
					<input type="button" class="enviar" id="btnAgregarCarrera"  value="Enviar" onclick="doSubmitAddCarrera();" title="Guardar carrera"/>
				</p>		
			</div>
		</div>
		<div class="margin_top_20">
			<div class="grid1_3 fl">
				<label for="idSituacionAcademica"><span>*</span> Situación académica</label>
				<select id="idSituacionAcademica" name="idSituacionAcademica"
				        required="true" missingMessage="Es necesario indicar la situación académica"
				        store="situacionAcademicaStore" dojotype="dijit.form.FilteringSelect"
				        value="${ofertaEmpleoForm.idSituacionAcademica}">
				</select>
			</div>
			<div class="grid1_3 fl">
				<label><span>*</span> Años de experiencia</label>
				<select id="aniosExperiencia" name="aniosExperiencia"
				        required="true" missingMessage="Es necesario indicar los años de experiencia."
				        store="experienciaTotalStore" dojotype="dijit.form.FilteringSelect"
				        value="${ofertaEmpleoForm.aniosExperiencia}">
				</select>
			</div>
			<div class="clearfix"></div>
		</div>
	<div class="idiomas margin_top_20">
		<div class="labeled">Idioma</div>
		<div class="margin_top_10">				
			<div class="grid1_3 fl">
				<label for="idIdioma"><span>*</span> Idioma adicional al nativo principal</label>
				<select id="idIdioma" name="idIdioma"
					        required="true" missingMessage="Es necesario indicar el idioma adicional"
					        store="idiomaStore" dojotype="dijit.form.FilteringSelect"
					        value="${ofertaEmpleoForm.idIdioma}"
					        onchange="javascript:actualizaDominio();">
				</select>
			</div>
			<div class="grid1_3 fl">
				<label id="idLabelDominioIdioma" for="idDominioIdioma">Dominio del idioma</label>
				<select id="idDominioIdioma" name="idDominioIdioma"
				        required="true"
				        readOnly="false"
				        store="dominioStore" dojotype="dijit.form.FilteringSelect"
				        missingMessage="Es necesario indicar el dominio requerido del idioma"
				        onchange="javascript:actualizaCertificaciones();"
				        value="${ofertaEmpleoForm.idDominioIdioma}">
				</select>
			</div>
			<div class="grid1_3 fl margin_no_r">
				<label id="idLabelCertificacionIdioma" for="idCertificacionIdioma">Certificación requerida </label>
				<select id="idCertificacionIdioma" name="idCertificacionIdioma"
				        required="true"
				        readOnly="false"
				        store="certificacionStore" dojotype="dijit.form.FilteringSelect"
				        missingMessage="Es necesario indicar la certificacion requerida"
				        value="${ofertaEmpleoForm.idCertificacionIdioma}" >
				</select>
			</div>	
		</div>	
		<div id="agregaIdioma" name="agregaIdioma">
				<jsp:include page="/jsp/oferta/idiomasAdicionales.jsp" flush="true" />
		</div>
		<div id="idiomaAdicional" class="margin_top_30" style="display: none">  
			<div class="grid1_3 fl">
				<label id="idLabelIdiomaAdd" for="idIdiomaAdd">Idioma Adicional</label>
				<select id="idIdiomaAdd" name="idIdiomaAdd"
					required="true" missingMessage="Es necesario indicar el idioma adicional"
					store="idiomaStoreAdd" dojotype="dijit.form.FilteringSelect"
					onchange="javascript:actualizaDominioAdd(0,this.value);">
				</select>
			</div>
			<div class="grid1_3 fl">
				<label id="idLabelDominioIdiomaAdd" for="idDominioIdiomaAdd">Dominio</label>
					<select id="idDominioIdiomaAdd" name="idDominioIdiomaAdd"
						required="false"
						readOnly="false"
						store="dominioStore" dojotype="dijit.form.FilteringSelect"
						missingMessage="Es necesario indicar el dominio requerido del idioma"
						onchange="javascript:actualizaCertificacionesAdd(0);">
					</select>
			</div>
			<div class="grid1_3 fl">
				<label id="idLabelCertificacionIdiomaAdd" for="idCertificacionIdiomaAdd">Certificación requerida</label>
				<select id="idCertificacionIdiomaAdd" name="idCertificacionIdiomaAdd"
					required="false"
					readOnly="false"
					store="certificacionAddStore" dojotype="dijit.form.FilteringSelect"
					missingMessage="Es necesario indicar la certificacion requerida">
				</select>
			</div>	
			<div class="clearfix"></div>			
			<div class="margin_top_10 ta_right" style="width: 918px">
				<input type="button" class="enviar" id="btnAgregarIdioma"  value="Guardar adicional" onclick="doSubmitAddIdioma();" title="Guardar idioma"/>
			</div>
		</div>	
	</div>
		<div class="labeled">Conocimientos requeridos para la oferta</div>
		<div class="grid1_3 fl">
			<label for="conocimiento">Conocimiento</label>
			<div>Escribe un conocimiento para la oferta de empleo. Ejemplo: manejo de conmutador.</div><br>
			<input type="text" id="conocimiento" name="conocimiento" value="${ofertaEmpleoForm.conocimiento}"
			      size="150" maxlength="50" trim="true"
                   dojoType="dijit.form.ValidationTextBox"
				   required="false" missingMessage="Debe indicar el conocimiento." />
		</div>
		<div class="grid1_3 fl" style="margin-top:36px;">
			<label for="idExperienciaConocimiento">Experiencia</label>
			<select id="idExperienciaConocimiento" name="idExperienciaConocimiento"
			        required="false" missingMessage="Es necesario indicar los años de experiencia del conocimiento."
			        store="experienciaTotalStore" dojotype="dijit.form.FilteringSelect"
			        value="${ofertaEmpleoForm.idExperienciaConocimiento}">
			</select>
		</div>
		<div class="clearfix"></div>
		<div id="agregaConocimiento" name="agregaConocimiento">
			<jsp:include page="/jsp/oferta/conocimientosAdicionales.jsp" flush="true" />
		</div>
    	<div id="conocimientoAdicional" style="display:none">
			<div class="grid1_3 fl">  
		        <label for="conocimientoAdd">Conocimiento</label>
		        <input type="text" id="conocimientoAdd" name="conocimientoAdd"
			      size="150" maxlength="50" trim="true"
                   dojoType="dijit.form.ValidationTextBox"
				   required="false" missingMessage="Debe indicar el conocimiento." />
			</div>
			<div class="grid1_3 fl"> 
				<label for="idExperienciaConocimientoAdd">Experiencia</label>
				<select id="idExperienciaConocimientoAdd" name="idExperienciaConocimientoAdd"
			        required="false" missingMessage="Es necesario indicar los años de experiencia del conocimiento."
			        store="experienciaTotalStore" dojotype="dijit.form.FilteringSelect" >
				</select>
			</div>
			<div class="clearfix"></div>
			<p class="agregar_bloque bis">
					<input type="button" class="enviar" id="btnAgregarConocimiento"  value="Enviar" onclick="doSubmitAddConocimiento();" title="Guardar conocimiento"/>
			</p>		
		</div>
		
		<p class="labeled margin_top_10"><span>*</span>Conocimientos en computación</p>
		<div>Puedes seleccionar más de una opción</div>
		<div class="grid1_3">
			<ul class="diasLaborales margin_top_10">
	            <li>
					<input type="checkbox" name="conocimientoCompNingunoC" id="conocimientoCompNingunoC" onclick="javascript:limpiaConocimientosComp(0)">
					<label for="conocimientoCompNingunoC">Ninguno</label>
				</li>
				<li>
					<input type="checkbox" name="conocimientoCompProcesadorTxtC" id="conocimientoCompProcesadorTxtC" onclick="javascript:limpiaConocimientosComp(1)"/>
					<label for="conocimientoCompProcesadorTxtC">Procesador de textos</label>
				</li>
				<li>
					<input type="checkbox" name="conocimientoCompHojaCalC" id="conocimientoCompHojaCalC" onclick="javascript:limpiaConocimientosComp(1)">
					<label for="conocimientoCompHojaCalC">Hojas de cálculo</label>
				</li>
				<li>
					<input type="checkbox" name="conocimientoCompInternetC" id="conocimientoCompInternetC" onclick="javascript:limpiaConocimientosComp(1)">
					<label for="conocimientoCompInternetC">Internet o correo electrónico</label>
				</li>
				<li>
					<input type="checkbox" name="conocimientoCompRedes" id="conocimientoCompRedesC" onclick="javascript:limpiaConocimientosComp(1)">
					<label for="conocimientoCompRedesC">Redes sociales</label>
				</li>							
				<li><div class="campoTexto">
						<div class="labeled"><span>*</span> Otros conocimientos en computación</div>
				        <textarea cols="70" rows="4" 
							name="conocimientoCompOtros" id="conocimientoCompOtros" trim="true" 
							onchange="javascript:limpiaConocimientosComp(1); return maximaLongitud(this,2000)" 
							onKeyUp="javascript:limpiaConocimientosComp(1); return maximaLongitud(this,2000)" 
						    onblur="javascript:limpiaConocimientosComp(1); return maximaLongitud(this,2000)" 
						    required="false" class="textGoogie"
						    missingMessage="Favor de registrar otros conocimientos en computación.">${ofertaEmpleoForm.conocimientoCompOtros}
						</textarea>
					    <script type="text/javascript">
				         		var vSpellconocimientoCompOtros = new GoogieSpell("googiespell/", '${vProxy}');
				         		vSpellconocimientoCompOtros.setLanguages({'es': 'Español'});
			   					vSpellconocimientoCompOtros.hideLangWindow();
			  					vSpellconocimientoCompOtros.decorateTextarea("conocimientoCompOtros");
						</script>									
					</div> 
				</li>
         	</ul>
		</div>
		<div class="clearfix"></div>
		<div class="labeled">Habilidades y actitudes</div>
		<div class="labeled"><span>*</span> Selecciona máximo 5 habilidades y actitudes necesarias para el puesto</div>
		<fmt:formatNumber var="numbloque" value="${fn:length(opcHabilidades) / 3}" maxFractionDigits="0"/>
		<c:forEach var="habilidadOpc" items="${opcHabilidades}" varStatus="index">
			<c:set var="open" value="${index.count==1 || index.count==numbloque+1 || index.count==(numbloque*2)+1}"/>
			<c:set var="close" value="${index.count==numbloque || index.count==(numbloque*2) || index.count==fn:length(opcHabilidades)}"/>
			<c:set var="checkmedio" value=""/>
			<c:forEach var="habilidadSaved" items="${ofertaEmpleoForm.idHabilidad}">					
				<c:if test="${habilidadOpc.idCatalogoOpcion == habilidadSaved}">
					<c:set var="checkmedio" value="checked"/>
				</c:if>
			</c:forEach>
			<c:if test="${open}">
				<ul class="grid1_3 fl">
			</c:if>
			<li>
			<label>
				<input type="checkbox" id="idHabilidad" name="idHabilidad" value="${habilidadOpc.idCatalogoOpcion}" ${checkmedio}/>
				${habilidadOpc.opcion}</label>
			</li>
			<c:if test="${close}">
				</ul>
			</c:if>
		</c:forEach>
		
</fieldset>
		
				
<fieldset class="bloque disponibilidad">	
	<c:if test="${ofertaEmpleoForm.fuente ne 9}">
		<legend>Edad y sexo</legend>
	</c:if>
	<c:if test="${ofertaEmpleoForm.fuente eq 9}">
		<legend>Sexo</legend>
	</c:if>	
	
	<div class="margin_top_20">Estos datos no se mostrarán en el detalle de la oferta y servirán únicamente para fines informativos.</div>	
		<c:if test="${ofertaEmpleoForm.fuente ne 9}">
			<div class="labeled">Edad</div>
			<div class="grid1_3">
				<div class="labeled margin_no_t"><span>*</span> ¿Es requisito el rango de edad?</div>
				<c:if test="${ofertaEmpleoForm.edadRequisito==2}">
					<ul>
						<li>
							<input class="fl" type="radio" name="edadRequisito" id="edadrequisitoSi" 
							checked="checked" value="2" onclick="edadRequerida(true);">
							<label class="fl" for="edadrequisitoSi">Si</label>
							<div class="clearfix"></div>
						</li>
						<li>
							<input class="fl" type="radio" name="edadRequisito" id="edadrequisitoNo" 
							value="1" onclick="edadRequerida(false);">
							<label class="fl" for="edadrequisitoNo">No</label>
							<div class="clearfix"></div>
						</li>
					</ul>					
				</c:if>
				<c:if test="${ofertaEmpleoForm.edadRequisito!=2}">
					<ul>
						<li>
							<input class="fl" type="radio" name="edadRequisito" id="edadrequisitoSi" 
							value="2" onclick="edadRequerida(true);">
							<label class="fl" for="edadrequisitoSi">Si</label>
							<div class="clearfix"></div>
						</li>
						<li>
							<input class="fl" type="radio" name="edadRequisito" id="edadrequisitoNo"  checked="checked" 
							value="1" onclick="edadRequerida(false);">
							<label class="fl" for="edadrequisitoNo">No</label>
							<div class="clearfix"></div>
						</li>
					</ul>
				</c:if>
	
				<div class="margin_top_10">
					<div class="fl margin-r_10">
						<label for="edadMinima" id="spanDe" class="desactivado">De:</label>
						<input type="text" id="edadMinima" name="edadMinima" value="${ofertaEmpleoForm.edadMinima}"
						size="150" maxlength="3" trim="true" style="width: 60px"
						dojoType="dijit.form.ValidationTextBox"
						missingMessage="Debe indicar la edad mínima." 
						regExp="${regexpedad}" invalidMessage="La edad mínima. es invalida, favor de verificar" />
					</div>
					<div class="fl">
						<label for="edadMaxima" id="spanA">A:</label>
						<input type="text" id="edadMaxima" name="edadMaxima" value="${ofertaEmpleoForm.edadMaxima}"
						size="150" maxlength="3" trim="true" style="width: 60px"
						dojoType="dijit.form.ValidationTextBox"
						missingMessage="Debe indicar la edad máxima." 
						regExp="${regexpedad}" invalidMessage="La edad máxima es invalida, favor de verificar" />
					</div>
					<div class="clearfix"></div>
				</div>
			</div>
		</c:if>
		
		<div class="grid1_3">
			<div class="labeled margin_top_20">Sexo</div>
			<div class="labeled margin_no_t"><span>*</span> Sexo preferente</div>
			<ul class="margin_top_10">
				<li>
				<input class="fl" type="radio" name="genero" id="generoN" 
					 checked="checked" value="3" >
				<label class="fl" for="generoN">No es requisito</label>
				<div class="clearfix"></div>
				</li>
				<li>
				<input class="fl" type="radio" name="genero" id="generoH" 
				value="1">
				<label class="fl" for="generoH">Hombre</label>
				<div class="clearfix"></div>
				</li>
				<li>
				<input class="fl" type="radio" name="genero" id="generoM"
				value="2" >
				<label class="fl" for="generoM">Mujer</label>
				<div class="clearfix"></div>
				</li>
			</ul>
		</div>
</fieldset>
<fieldset class="bloque disponibilidad">
	<legend>Información adicional </legend>
	<div class="labeled">Datos complementarios</div>
	
	<div>
		<div class="labeled margin_no_t"><span>*</span> ¿Es necesario que el candidato cuente con disponibilidad para viajar?</div>
		<ul>
			<li>
				<input class="fl" type="radio" name="viajar" id="viajarSi" 
					 checked="checked" value="2" >
				<label class="fl" for="viajarSi">Si</label>
				<div class="clearfix"></div>
			</li>
			<li>
				<input class="fl" type="radio" name="viajar" id="viajarNo" 
					 checked="checked" value="1" >
				<label class="fl" for="viajarNo">No</label>
				<div class="clearfix"></div>
			</li>
		</ul>
	</div>
	<div>
		<div class="labeled"><span>*</span> ¿Es necesario que el candidato cuente con disponibilidad para radicar en otra ciudad?</div>
		<ul>
			<li>
				<input class="fl" type="radio" name="radicar" id="radicarSi" 
					 checked="checked" value="2" >
				<label class="fl" for="radicarSi">Si</label>
				<div class="clearfix"></div>
			</li>
			<li>
				<input class="fl" type="radio" name="radicar" id="radicarNo" 
					 checked="checked" value="1" >
				<label class="fl" for="radicarNo">No</label>
				<div class="clearfix"></div>
			</li>
		</ul>
	</div>
	<div class="campoTexto margin_top_10">
		<label for="observaciones">Observaciones adicionales</label>
        <textarea cols="70" rows="4"
			name="observaciones" id="observaciones" trim="true" onchange="return maximaLongitud(this,2000)" onKeyUp="return maximaLongitud(this,2000)" 
		    onblur="return maximaLongitud(this,2000)" class="textGoogie"
		    required="true" missingMessage="Favor de registrar los conocimientos y habilidades.">${ofertaEmpleoForm.observaciones}</textarea>
		    <script type="text/javascript">
	         		var vSpellobservaciones = new GoogieSpell("googiespell/", '${vProxy}');
	         		vSpellobservaciones.setLanguages({'es': 'Español'});
   					vSpellobservaciones.hideLangWindow();
  					vSpellobservaciones.decorateTextarea("observaciones");
				</script>
	</div>
</fieldset>

	<c:if test="${ofertaEmpleoForm.idOfertaEmpleo>0}">
		<div class="form_nav"><input id="btnGuardar" type="button" value="Guardar" onclick="doSubmit('editarOferta',this.id);"/>&nbsp;&nbsp;<input id="btnCancelar" type="button" value="Cancelar" class="boton_naranja" onclick="doSubmitCancelar();"/></div>
	</c:if>
	<c:if test="${ofertaEmpleoForm.idOfertaEmpleo==0}">
		<div class="form_nav"><input id="btnContinuar" type="button" value="Continuar" onclick="doSubmit('toDatosContacto',this.id);"/></div>
	</c:if>
	
</form>
</body>

<c:if test="${ofertaEmpleoForm.idOfertaEmpleo>0}">
	<c:if test="${!ofertaEmpleoForm.datosValidosEdicion}">
		<script type="text/javascript">edicionValidadion();</script>
	</c:if>
</c:if>

<%
if(request.getSession().getAttribute("mensajeEdicion")!=null&&request.getSession().getAttribute("mensajeEdicion")!=""){
	String mensaje=String.valueOf(request.getSession().getAttribute("mensajeEdicion"));
	%>
	<script type="text/javascript">
		var input = '<input id="btnAct" class="boton_naranja" type="button" name="btnAct" onClick="enviarAMisOfertas();" value="Aceptar"/>';
		messageAcept('<%=mensaje%>',input);
		function messageAcept(msg,input) {
			var html =
				'<div id="messageDialgop2" name="messageDialgop2">' +
					'<p style="text-align: center;">'+ msg +'</p><br>'+
					'<p class="form_nav">' + input + 
				'</div>';
			dialogMsg = new dijit.Dialog({
		        title: 'Mensaje',
		        content: html,
		        style: "width:300px;",
		        showTitle: false, draggable : false, closable : false,
		        hide: function(){
		        	clearWidgetsHtml('messageDialgop2');
		        	dialogMsg.destroy()
		        }
		    });			
			dialogMsg.show();
		}
	</script>
<%
		request.getSession().removeAttribute("mensajeEdicion");
	}
%>
<%
	if (request.getSession().getAttribute("alert4Admin") != null && request.getSession().getAttribute("alert4Admin") != "") {
		String msg = String.valueOf(request.getSession().getAttribute("alert4Admin"));
%>
		<script>message('<%=msg%>');</script>
<%
		request.getSession().removeAttribute("alert4Admin");
	}
%>
</html>