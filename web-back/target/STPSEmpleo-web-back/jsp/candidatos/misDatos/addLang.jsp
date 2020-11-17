<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page import="java.util.Calendar, java.util.Date, java.text.DateFormat, java.text.SimpleDateFormat, java.text.ParseException"%>
<% 
	String context = request.getContextPath() + "/";
	String fechaExamen = null != session.getAttribute("fechaExamen") ? (String)session.getAttribute("fechaExamen") : "";
%>
<script type="text/javascript" src="js/helper/messageHelper.js"></script>
<script type="text/javascript">
	var depIdioma = new Array(); //Declara arreglo de dependencias de catalogo idiomas
</script>
<c:if test="${not empty ProgramaForm.idiomasDependientes}">
	<script type="text/javascript">
		<c:forEach var="idiomaDep" items="${ProgramaForm.idiomasDependientes}" varStatus="indexIdi">
			depIdioma[parseInt('${indexIdi.count - 1}')] = '${idiomaDep}';
		</c:forEach>
	</script>
</c:if>
<script>
	dojo.require("dijit.dijit"); // loads the optimized dijit layer
	dojo.require("dijit.form.Form");
    dojo.require("dijit.form.ValidationTextBox");
    dojo.require("dijit.form.TextBox");
	dojo.require("dojo.data.ItemFileReadStore");
	dojo.require("dijit.form.FilteringSelect");
	dojo.require("dijit.form.Textarea");
	dojo.require('dijit.Dialog');
	dojo.require('dijit.form.RadioButton');
	dojo.require('dojo.parser');
	
	dojo.addOnLoad(function() {
		load();
		updTest();
    }); 	
    
    function load() {
		var vIdioma = '${ProgramaForm.idiomaReqIng.idIdioma}';
		var vCert = '${ProgramaForm.idiomaReqIng.idCertificacion}';
		var vIdIdiomaDep = depIdioma[vIdioma];
		if (vIdIdiomaDep && vIdIdiomaDep != 0 ) {
    		certificacionStore.url = '${context}idiomas.do?method=certificaciones&idCatDep='+ vIdIdiomaDep;
    		certificacionStore.close();
    		certificacionStore.fetch({
         		onComplete: function(items, request) {
         			if (items.length == 0) return;
         			dijit.byId('idCertificacionIdioma').attr('value', '${ProgramaForm.idiomaReqIng.idCertificacion}');
         			dijit.byId('idExamenSelect').attr('value', '${ProgramaForm.idiomaReqIng.idCertificacion}');
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
		var idDominio = '${ProgramaForm.idiomaReqIng.idDominio}';
    	if(idDominio ==  2 || idDominio == 3 ) {
    		setLabelToLabelAndRequerido('idCertificacionIdioma','idLabelCertificacionIdioma','¿Cuentas con certificación?',true);
    	}else {
    		setLabelToLabelAndRequerido('idCertificacionIdioma','idLabelCertificacionIdioma','¿Cuentas con certificación?',false);
    	}
	}
	function actualizaDominio() {
    	var idIdioma = dijit.byId('idIdioma').get('value');
    	if (idIdioma  == 1) {
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
    function actualizaDominioAdd0(idStore){
		actualizaDominioAdd(idStore,0);
	}
	function actualizaDominioAdd(idStore,value) {
		if (idStore>0) {
			var vIdioma = dijit.byId('selectLanguage_'+idStore).get('value');
			document.getElementById('idiomaAdicional').style.display = 'none';
			dojo.xhrPost({url: '/perfilComplementario.do?method=idiomaDominio&vIdioma='+vIdioma+'&idMultiRegistro='+idStore+'&idDominio='+value ,preventCache: true, form:'ProgramaForm', sync: true, timeout:180000, 
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
	function actualizaCertificacionesAdd(store) {
    	var vIdioma = dijit.byId('idIdiomaAdd').get('value');
		//Necesitamos el valor guardado del catalogo que es referente a su idioma no el de la lista
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
    }
    function doSubmitAddIdioma() {	
		if (!validaCampoSelect('idIdiomaAdd')) return false;	
		if(!validaCampoSelect('idDominioIdiomaAdd')) return false;
		if (!validaCampoNoRequerido('idCertificacionIdiomaAdd')) return false;
		messageConfirmLang('¿Está seguro de guardar idioma adicional al nativo?');
	}
	function addIdioma() {
		var idIdiomaAdd = dijit.byId('idIdiomaAdd').get('value');
		var idCertificacionAdd = dijit.byId('idCertificacionIdiomaAdd').get('value');
		var idDominioAdd = dijit.byId('idDominioIdiomaAdd').get('value');
		document.getElementById('idiomaAdicional').style.display = 'none';
		dojo.xhrPost({url: 'perfilComplementario.do?method=saveAddLang&idIdiomaAdd='+idIdiomaAdd+'&idCertificacionAdd='+idCertificacionAdd+'&idDominioAdd='+idDominioAdd,preventCache: true, form:'ProgramaForm', sync: true, timeout:180000, 
			load: function(data) {
				var res = dojo.fromJson(data);
				if (res.type=='ext') {
					messagePath(res.message,'<%=context%>miEspacioCandidato.do?method=init');
				}else {
					message(res.message);
				}
			}});
	}
    function activarCertificaciones() {
    	var vIdioma = dijit.byId('idIdioma').get('value');
		//Necesitamos el valor guardado del catalogo que es referente a su idioma no el de la lista
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
	//habilitar una lista
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
	function validaCampoSelect(campo) {
		var control = dijit.byId(campo);	
		if (control && control.get('value')==''){
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
			if (!control.validate()) {
				dijit.byId(campo).focus();
				dojo.byId(campo).blur();
				message(control.get("missingMessage"));
				return false;
			}
		}
		return true;
	}
	function clearAddSetAgregaIdioma(data) {
		 clearWidgetsAndAddHtml('agregaIdioma',data);
	}
	function messageConfirmLang(msg) {
		var html =
			'<div id="messageDialgopC" name="messageDialgopC">' +
			'<p style="text-align: center;">'+ msg +'</p>'+
			'<p class="form_nav">' + 
			'<input id="btnRenv" class="boton_naranja" type="button" name="btnRenv" onClick="addIdioma()" value="Aceptar"/>' +
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
	function updTest() {
		var examenPresentado = getRadioValue('examenPresentadoRadio');
		if (null != examenPresentado && examenPresentado == 2) {
			document.getElementById('levenLang').style.display = "block";
		}
		if (null != examenPresentado && examenPresentado == 1) {
			document.getElementById('levenLang').style.display = "none";
		}
		setLevelEng(vDominio);
	}
</script>
<div dojoType="dojo.data.ItemFileReadStore" jsId="idiomaStore" url="${context}perfilComplementario.do?method=idiomas" urlPreventCache="true"></div>
<div dojoType="dojo.data.ItemFileReadStore" jsId="dominioStore" url="${context}idiomas.do?method=dominios" urlPreventCache="true" clearOnClose="true"></div>
<div dojoType="dojo.data.ItemFileReadStore" jsId="certificacionStore" urlPreventCache="true" clearOnClose="true"></div>
<div dojoType="dojo.data.ItemFileReadStore" jsId="dominioStoreAdd" url="${context}idiomas.do?method=dominios" urlPreventCache="true" clearOnClose="true"></div>
<div dojoType="dojo.data.ItemFileReadStore" jsId="certificacionAddStore" urlPreventCache="true" clearOnClose="true"></div>
<div class="formApp miEspacio">
		<div class="app_holder2">
				<div class="app">
					<div class="datos_personales">
						<fieldset class="data_edit">
							<legend>Idioma</legend>
							<div class="margin_top_10" style="display:block">
								<div class="grid1_3 fl">
									<label for="idIdioma"><span>*</span> Idioma adicional al nativo</label>
										<select id="idIdioma" name="idIdioma" store="idiomaStore" dojotype="dijit.form.FilteringSelect" value="5" required="true"
											missingMessage="Es necesario indicar el idioma adicional" disabled="disabled" onchange="javascript:actualizaDominio();">
										</select>
								</div>
								<div class="grid1_3 fl">
									<label id="idLabelDominioIdioma" for="idDominioIdioma"></label>
									<input type="hidden" name="idDominioReq" id="idDominioReq" dojoType="dijit.form.TextBox">
									<select id="idDominioIdioma" name="idDominioIdioma" required="true" readOnly="false" store="dominioStore" 
											dojotype="dijit.form.FilteringSelect" missingMessage="Es necesario indicar el dominio requerido del idioma"
											<c:if test='${ProgramaForm.idiomaReqIng.idIdioma==5 && ProgramaForm.idiomaReqIng.idCandidatoIdioma>0}'>disabled="disabled"</c:if> onchange="javascript:actualizaCertificaciones();" value="${ProgramaForm.idiomaReqIng.idDominio}">
									</select>
								</div>
								<div class="grid1_3 fl margin_no_r">
									<label id="idLabelCertificacionIdioma" for="idCertificacionIdioma">¿Cuentas con certificación?</label>
									<input type="hidden" name="idCertificacionReq" id="idCertificacionReq" dojoType="dijit.form.TextBox">
									<select id="idCertificacionIdioma" name="idCertificacionIdioma" required="false" store="certificacionStore"
											dojotype="dijit.form.FilteringSelect" missingMessage="Es necesario indicar la certificacion requerida"
											<c:if test='${ProgramaForm.idiomaReqIng.idIdioma==5 && ProgramaForm.idiomaReqIng.idCandidatoIdioma>0}'>disabled="disabled"</c:if> value="${ProgramaForm.idiomaReqIng.idCertificacion}">
									</select>
								</div>
								<div class="grid1_3 margin_top_10 fl">
									<label id="idLabelHabloIdioma" for="idLabelHabloIdioma"><span>*</span> Hablo</label>
									<input type="hidden" name="idDominioHabla" id="idDominioHabla" dojoType="dijit.form.TextBox">
									<select id="idHabloIdiomaSelect" name="idHabloIdiomaSelect" required="true" value="${ProgramaForm.idiomaReqIng.idDominioHabla}"
											dojotype="dijit.form.FilteringSelect" missingMessage="Es necesario indicar el nivel de habla del idioma">
											<c:forEach var="row" items="${competencia}">
												<option value="${row.idCatalogoOpcion}">${row.opcion}</option>
											</c:forEach>
									</select>
								</div>
								<div class="grid1_3 margin_top_10 fl">
									<label id="idLabelEscriboIdioma" for="idLabelEscriboIdioma"><span>*</span> Escribo</label>
									<input type="hidden" name="idDominioEscrito" id="idDominioEscrito" dojoType="dijit.form.TextBox">
									<select id="idEscriboIdiomaSelect" name="idEscriboIdiomaSelect" required="true" value="${ProgramaForm.idiomaReqIng.idDominioEscrito}"
											dojotype="dijit.form.FilteringSelect" missingMessage="Es necesario indicar el nivel de escritura del idioma">
											<c:forEach var="row" items="${competencia}">
												<option value="${row.idCatalogoOpcion}">${row.opcion}</option>
											</c:forEach>
									</select>
								</div>
								<div class="grid1_3 margin_top_10 fl">
									<label id="idLabelLeoIdioma" for="idLabelLeoIdioma"><span>*</span> Leo</label>
									<input type="hidden" name="idDominioLectura" id="idDominioLectura" dojoType="dijit.form.TextBox">
									<select id="idLeoIdiomaSelect" name="idLeoIdiomaSelect" required="true" value="${ProgramaForm.idiomaReqIng.idDominioLectura}"
											dojotype="dijit.form.FilteringSelect" missingMessage="Es necesario indicar el nivel de lectura del idioma">
											<c:forEach var="row" items="${competencia}">
												<option value="${row.idCatalogoOpcion}">${row.opcion}</option>
											</c:forEach>
									</select>
								</div>
							</div>
							<div id="idiomaAdicional" class="margin_top_30" style="display: none">
								<div class="grid1_3 fl">
									<label id="idLabelIdiomaAdd" for="idIdiomaAdd"><span>*</span> Idioma adicional al nativo</label>
									<select id="idIdiomaAdd" name="idIdiomaAdd" required="true" missingMessage="Es necesario indicar el idioma adicional" 
											store="idiomaStore" dojotype="dijit.form.FilteringSelect" onchange="javascript:actualizaDominioAdd(0,this.value);">
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
									<select id="idCertificacionIdiomaAdd" name="idCertificacionIdiomaAdd" required="false" readOnly="false" store="certificacionAddStore"
											dojotype="dijit.form.FilteringSelect" missingMessage="Es necesario indicar la certificacion requerida">
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
									<input type="button" class="enviar" title="Guardar idioma" value="Guardar idioma" onclick="doSubmitAddIdioma();" />
								</div>
							</div>
						</fieldset>
						<fieldset class="lang_edit">
							<legend>Nivel de inglés</legend>
							<div class="margin_top_10 fl">
								<p class="grid1_3 fl">
									<div class="labeled"><span>*</span> ¿Presentó algún examen?</div>
									<label class="fl margin-r_20"><input type="radio" name="examenPresentadoRadio" id="examenPresentadoSi" value="2" <c:if test='${ProgramaForm.idiomaReqIng.examenPresentado==2}'>checked</c:if> onclick="updTest();" /> Sí</label>
									<label class="fl"><input type="radio" name="examenPresentadoRadio" id="examenPresentadoNo" value="1" <c:if test='${ProgramaForm.idiomaReqIng.examenPresentado==1}'>checked</c:if> onclick="updTest();" /> No</label>
									<div class="clearfix"></div>
								</p>
							</div>
						<div id="levenLang" style="display: none;">
							<div class="grid1_3 margin_top_10 fl">
								<label for="idExamenSelect"><span>*</span> Examen</label>
								<input type="hidden" name="examenId" id="examenId" dojoType="dijit.form.TextBox">
								<select dojotype="dijit.form.FilteringSelect" id="idExamenSelect" autocomplete="true" store="certificacionStore"
										<c:if test='${ProgramaForm.idiomaReqIng.examenPresentado==2}'>disabled="disabled"</c:if>
										value="${ProgramaForm.idiomaReqIng.idCertificacion}" required="true" missingMessage="Seleccione examen">
								</select>
							</div>
							<div class="grid1_3 margin_top_10 fl">
								<label for="laboresInicialDia"><span>*</span> Fecha en que presentó el examen</label>
								<select dojotype="dijit.form.FilteringSelect" name="fechaExamenDia" id="fechaExamenDia" style="width: 7em;" missingMessage="Es necesario indicar la Fecha de examen.">
									<option value="0">Día</option>
									<%=getSelectedDay(fechaExamen)%>
								</select>
								<select dojotype="dijit.form.FilteringSelect" name="fechaExamenMes" id="fechaExamenMes" style="width: 7em;" missingMessage="Es necesario indicar la Fecha de examen.">
									<option value="0">Mes</option>
									<%=getSelectedMonth(fechaExamen) %>
								</select>
								<select dojotype="dijit.form.FilteringSelect" name="fechaExamenAnual" id="fechaExamenAnual" style="width: 7em;" missingMessage="Es necesario indicar la Fecha de examen.">
									<option value="0">Año</option>
									<%=getSelectedYear(fechaExamen) %>
								</select>
							</div>
							<div class="clearfix"></div>
							<div class="grid1_3 margin_top_10 fl">
								<label for="puntos"><span>*</span> Puntos</label>
								<input type="hidden" name="examenPuntos" id="examenPuntos" dojoType="dijit.form.TextBox">
								<select dojotype="dijit.form.FilteringSelect" id="examenPuntosSelect" autocomplete="true"
									value="${ProgramaForm.idiomaReqIng.examenPuntos}" required="true" missingMessage="Dato requerido">
									<option value=""></option>
									<% for (int i=1; i<11; i++) {
										out.println("<option value=\"" + i + "\">" + i + "0</option>");
									   }
									%>
								</select>
							</div>
							<div class="grid1_3 margin_top_10 fl">
								<label for="idNivelEqCanadienseSelect"><span>*</span> Nivel de equivalencia Canadiense</label>
								<input type="hidden" name="idNivelEqCanadiense" id="idNivelEqCanadiense" dojoType="dijit.form.TextBox">
								<select dojotype="dijit.form.FilteringSelect" id="idNivelEqCanadienseSelect" autocomplete="true"
									value="${ProgramaForm.idNivelEqCanadiense}" required="true" missingMessage="Dato requerido">
									<option value=""></option>
									<% for (int i=1; i<13; i++) {
											out.println("<option value=\"" + i + "\">" + i + "</option>");
										}
									%>
								</select>
							</div>
						</div>
						</fieldset>
					</div>
				</div>
			</div>
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