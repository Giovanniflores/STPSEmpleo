<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ page import="java.util.Calendar, mx.gob.stps.portal.web.candidate.form.SeguimientoPostulacionForm"%>
<%
	String context = request.getContextPath() +"/";
	SeguimientoPostulacionForm spf = (SeguimientoPostulacionForm)request.getSession().getAttribute("SeguimientoPostulacionForm");
	Calendar cal = Calendar.getInstance();
	cal.setTime(spf.getOfferCand().getFechaInicioContratacion());
%>
<script type="text/javascript" src="js/helper/messageHelper.js"></script>
<c:set var="regexpmotivo">[a-zA-Z]{4}\d{6}[hmHM][a-zA-Z]{2}[B-DF-HJ-NP-TV-Z|b-df-hj-np-tv-z]{3}[a-zA-Z0-9][0-9]</c:set>
<script type="text/javascript">
	dojo.require("dijit.dijit");
	dojo.require("dijit.Dialog");
	dojo.require("dijit.form.Form");
	dojo.require("dijit.form.ValidationTextBox");
	dojo.require("dijit.Tooltip");
	dojo.require("dojo.data.ItemFileReadStore");
	dojo.require("dijit.form.FilteringSelect");
	function displayErrorMsg(textBox, errmsg) {
		var originalValidator = textBox.validator;
		textBox.validator = function() {return false;};
		textBox.validate();
		textBox.validator = originalValidator;
		dijit.showTooltip(
			    errmsg,
			    textBox.domNode, 
			    textBox.get("tooltipPosition"),
			    !textBox.isLeftToRight()
		);
	}
	function entrevistaTrabajo() {
		if (dojo.byId('fuisteContratadoSi').checked) {
			dojo.byId('fuisteContratadoFechaSi').style.display='block';
			dojo.byId('fuisteContratadoFechaNo').style.display='none';
			dojo.byId('otroMotivo').style.display='none';
		}else if (dojo.byId('fuisteContratadoNo').checked) {
			dojo.byId('fuisteContratadoFechaSi').style.display='none';
			dojo.byId('fuisteContratadoFechaNo').style.display='block';
		}else if (dojo.byId('fuisteContratadoEsp').checked) {
			dojo.byId('fuisteContratadoFechaSi').style.display='none';
			dojo.byId('fuisteContratadoFechaNo').style.display='none';
			dojo.byId('otroMotivo').style.display='none';
		}
		dijit.byId('motivoNoContrato').reset();
	}
	function verificarResultado() {
		if (!validateWereHired())
			return false;
		if (!verificarSeguimiento())
			return false;
		//var answer = confirm("¿Está seguro de guardar resultado de la entrevista?");
		//if (answer) {
			doSubmitForm('result');
		//}
	}
	function validateWereHired() {
		var fuisteContratadoSi = document.getElementById('fuisteContratadoSi');
		var fuisteContratadoNo = document.getElementById('fuisteContratadoNo');
		var fuisteContratadoEsp = document.getElementById('fuisteContratadoEsp');
		if (!fuisteContratadoSi.checked && !fuisteContratadoNo.checked && !fuisteContratadoEsp.checked) {
			message('Debe seleccionar si fue contratado');
			return false;
		}
		return true;
	}
	function verificarSeguimiento() {
		if (dojo.byId('fuisteContratadoSi').checked) {
			if (!validateSelect('diaContrato'))
				return false;
			if (!validateSelect('mesContrato'))
				return false;
			if (!validateSelect('anioContrato'))
				return false;
			if (!validateContractDate())
				return false;
		}
		if (dojo.byId('fuisteContratadoNo').checked) {
			if (!validateEmptySelect('motivoNoContrato'))
				return false;
			var option = dijit.byId('motivoNoContrato').get('value');
  			if (option == 13 && !validateMotivo('motivoNoContratoEspecifique'))
  				return false;
		}
		return true;
	}
	function doSubmitForm(method) {
		dojo.byId('method').value = method;
		dojo.byId('segPostForm').submit();
	}
	function validateSelect(field) {
		var control = dijit.byId(field);
		if (control && control.get('value') == '0') {
			showErrorMsg(control);
			return false;
		}
		return true;
	}
	function validateEmptySelect(field) {
		var control = dijit.byId(field);
		if (control && control.get('value') == '') {
			showErrorMsg(control);
			return false;
		}
		return true;
	}
	function validateScheduleInterview() {
		var conseguisteEntrevistaSi = document.getElementById('conseguisteEntrevistaSi');
		if (conseguisteEntrevistaSi.checked) {
			if (!validateSelect('diaEntrevista'))
				return false;
			if (!validateSelect('mesEntrevista'))
				return false;
			if (!validateSelect('anioEntrevista'))
				return false;
		}
		if (conseguisteEntrevistaNo.checked) {
			if (!validateSelect('motivoNoEntrevista'))
				return false;
		}
		return true;
	}
	function showErrorMsg(control) {
  		displayErrorMsg(control, control.get("missingMessage"));
  	}
  	var dialogMensaje; 
	function showMensaje() {
		var msg;
		<% if (spf.getFuisteContratado() == 3 && spf.getEstatusPPC() > 0) {%> 
			msg = '<p>Para continuar en el Programa de Promoción y Colocación del Seguro de Desempleo, debes registrar el resultado de tu entrevista.</p>' +
			'<div class="form_nav 2"><button id="btnExit" name="btnExit" onclick="exit();">Aceptar</button></div>';
		<% }else if (spf.getFuisteContratado() == 3 && spf.getEstatusPPC() == 0) {%>
			msg = '<p>Te recomendamos continuar con el seguimiento de tu postulación.</p>' +
			'<div class="form_nav 2"><button id="btnAcp" name="btnAcp" onclick="exit();">Aceptar</button></div>';
		<% }else if (spf.getFuisteContratado() == 2) {%>
			msg = '<p>Te agradecemos el tiempo dedicado para notificarnos la respuesta a tu proceso de selección.</p>' +
			'<div class="form_nav 2"><button id="btnAcp" name="btnAcp" onclick="exit();">Aceptar</button></div>';
		<% }else if (spf.getFuisteContratado() == 1 && spf.getEstatusPPC() > 0) {%>
			msg = '<p>Para evitar la inactividad de tu registro en el Programa de Promoción y Colocación del Seguro de Desempleo (PPC-SD), debes continuar con la búsqueda de ofertas y postularte a las de tu interés.</p>' +
			'<div class="form_nav 2"><button id="btnAcp" name="btnAcp" onclick="exit();">Aceptar</button></div>';
		<% }else {%>
			msg = '<p>Te recomendamos continuar buscando ofertas de empleo y postulándote a las de tu interés.</p>' + 
			'<div class="form_nav 2"><button id="btnAcp" name="btnAcp" onclick="exit();">Aceptar</button></div>';
		<% }%>
		dialogMensaje = new dijit.Dialog({
	        title: 'Mensaje',
			content: msg,
	        style: "width:300px;",
	        showTitle: true, draggable : true, closable : true
	    });		
		<% if (spf.getControl() > 1) {%>
			dojo.style(dialogMensaje.closeButtonNode,"display","none");
			dialogMensaje.show();
		<% }%>
	}
	function closeMsg() {
		dialogMensaje.hide();
	}
	function exit() {
		window.location.href="${_urlpageinvoke}";
		dialogMensaje.hide();
	}
	function validateContractDate() {
		var dateSch = new Date();
		var dateContrat = new Date();
		var dateIniContrat = new Date();
		var diaEntrevista = dijit.byId('diaEntrevista').get('value');
		var mesEntrevista = dijit.byId('mesEntrevista').get('value');
		var anioEntrevista = dijit.byId('anioEntrevista').get('value');
		var diaContrato = dijit.byId('diaContrato').get('value');
		var mesContrato = dijit.byId('mesContrato').get('value');
		var anioContrato = dijit.byId('anioContrato').get('value');
		var diaIniCont = dijit.byId('diaIniCont').get('value');
		var mesIniCont = dijit.byId('mesIniCont').get('value');
		var anioIniCont = dijit.byId('anioIniCont').get('value');
		dateSch.setFullYear(anioEntrevista, --mesEntrevista, diaEntrevista);
		dateContrat.setFullYear(anioContrato, --mesContrato, diaContrato);
		dateIniContrat.setFullYear(anioIniCont, --mesIniCont, diaIniCont);
		if (dateSch > dateContrat) {
			++mesEntrevista;
			message('La fecha ¿A partir de que fecha quedaste contratado? debe ser igual o posterior a la fecha ' + diaEntrevista + "/" + ("0" + mesEntrevista).slice(-2) + "/" + anioEntrevista);
			return false;
		}
		if (dateIniContrat > dateContrat) {
			++mesEntrevista;
			message('La fecha ¿A partir de que fecha quedaste contratado? debe ser igual o posterior a la fecha ' + diaIniCont + "/" + ("0" + mesIniCont).slice(-2) + "/" + anioIniCont);
			return false;
		}
		return true;
	}
	function caracteresValidos(e){
	    var strChar = dijit.byId(e).get('value');
    	var patron = /^[0-9A-Za-z\\d\\s\\.\\,\\;\\:\\-\\'áéíóúÁÉÍÓÚñÑüÜ ]{0,150}$/;
    	if (!patron.test(strChar)){
    		mensajesModal('<h3>caracter <strong>' + strChar.substring(strChar.length - 1) +'</strong> inv&aacute;lido, no se permiten caracteres especiales.</h3>','width:500px;','height:100px;');
    		dojo.byId('motivoNoContratoEspecifique').value = strChar.replace(strChar.substring(strChar.length - 1), '');
    	}
    	
    }
    function mensajesModal(msg, width, height){
  		dialogAviso = new dijit.Dialog({
  			title: 'Mensaje',
			content: msg,
  			style: width + height + 'background: #FFF',
  			showTitle: false, draggable : false, closable : true
  		});
  		dialogAviso.show();
  	}
  	function verifyOption() {
  		var option = dijit.byId('motivoNoContrato').get('value');
  		if (option == 13)
  			dojo.byId('otroMotivo').style.display='block';
  		if (option != 13)
  			dojo.byId('otroMotivo').style.display='none';
  	}
  	function validateMotivo(field) {
		var control = dijit.byId(field);
		if (control && control.get('value') == '') {
			message('Especifique otro motivo por el que no te contrataron');
			return false;
		}
		return true;
	}
</script>
<div dojoType="dojo.data.ItemFileReadStore" jsId="diasStore"  url="${context}registro.do?method=dias"></div>
<div dojoType="dojo.data.ItemFileReadStore" jsId="mesesStore" url="${context}seguimientoPostulacion.do?method=meses"></div>
<div dojoType="dojo.data.ItemFileReadStore" jsId="aniosStore" url="${context}registro.do?method=aniosbusca"></div>
<div dojoType="dojo.data.ItemFileReadStore" jsId="motivoNoEntrevistaStore" url="${context}seguimientoPostulacion.do?method=motivoNoEntrevista"></div>
<div dojoType="dojo.data.ItemFileReadStore" jsId="motivoNoContratoStore" url="${context}seguimientoPostulacion.do?method=motivoNoContrato"></div>
<form name="segPostForm" id="segPostForm" method="post" action="seguimientoPostulacion.do" dojoType="dijit.form.Form">
	<input type="hidden" name="method" id="method" value="result" dojoType="dijit.form.TextBox"/>
	<input type="hidden" name="diaIniCont" id="diaIniCont" value="<%out.println(cal.get(Calendar.DAY_OF_MONTH));%>" dojoType="dijit.form.TextBox" /> 
	<input type="hidden" name="mesIniCont" id="mesIniCont" value="<%out.println(cal.get(Calendar.MONTH) + 1);%>" dojoType="dijit.form.TextBox" />
	<input type="hidden" name="anioIniCont" id="anioIniCont" value="<%out.println(cal.get(Calendar.YEAR));%>" dojoType="dijit.form.TextBox" />
	<div class="formApp publicados miEspacio">
	        <h2>Mi espacio</h2>
        <div class="tab_block">
            <div align="left" style="display:block;" id="returnME">
                <a class="expand" href="javascript:location.replace('<c:url value="/miEspacioCandidato.do"/>');">
                    <strong>Ir al inicio de Mi espacio</strong>
                </a>
            </div>
            <div class="Tab_espacio">
                <h3>Mis ofertas de empleo</h3>

                <div class="subTab">
                    <ul>
                        <c:choose>
                            <c:when test="${difTablaPager == '_MIS_POSTULACIONES' }">
                                <li class="curSubTab">
                                    <span>Mis postulaciones</span>
                                </li>
                            </c:when>
                            <c:otherwise>
                                <li>
                                    <a href="<c:url value="/misofertas.do?method=misPostulaciones"/>">Mis postulaciones</a>
                                </li>
                            </c:otherwise>
                        </c:choose>
                        <c:choose>
                            <c:when test="${difTablaPager == '_MIS_OFERTAS_GUARDADAS' }">
                                <li class="curSubTab">
                                    <span>Mis ofertas guardadas</span>
                                </li>
                            </c:when>
                            <c:otherwise>
                                <li>
                                    <a href="<c:url value="/misofertas.do?method=misOfertasGuardadas"/>">Mis ofertas guardadas</a>
                                </li>
                            </c:otherwise>
                        </c:choose>
                        <c:choose>
                            <c:when test="${difTablaPager == '_EMPRESAS_QUE_ME_BUSCAN' }">
                                <li class="curSubTab">
                                    <span>Empresas que me buscan</span>
                                </li>
                            </c:when>
                            <c:otherwise>
                                <li>
                                    <a href="<c:url value="/misofertas.do?method=empresasQueMeBuscan"/>">Empresas que me buscan</a>
                                </li>
                            </c:otherwise>
                        </c:choose>
                        <!-- New links to PPC-SD -->
                        <!--INI_JGLC_PPC-->
<%--                         <c:if test="${candidatoheader.isActiveToPpc() || candidatoheader.isInactiveToPpc()}"> --%>
<%--                             <c:choose> --%>
<%--                                 <c:when test="${difTablaPager == '_REGISTRAR_POSTULACIONES_EXTERNAS' }"> --%>
<!--                                     <li class="curSubTab"> -->
<!--                                         <span>Registrar postulaciones externas</span> -->
<!--                                     </li> -->
<%--                                 </c:when> --%>
<%--                                 <c:otherwise> --%>
<!--                                     <li> -->
<%--                                     	<a href="${context}registroPostulacionExterna.do?method=init" id="registro_postulaciones_externas">Registrar postulaciones externas</a> --%>
<!--                                     </li> -->
<%--                                 </c:otherwise> --%>
<%--                             </c:choose> --%>
<%--                             <c:choose> --%>
<%--                                 <c:when test="${difTablaPager == '_SEGUIMIENTO_A_POSTULACIONES_EXTERNAS' }"> --%>
<!--                                     <li class="curSubTab"> -->
<!--                                         <span>Seguimiento a postulaciones externas</span> -->
<!--                                     </li> -->
<%--                                 </c:when> --%>
<%--                                 <c:otherwise> --%>
<!--                                     <li>                                    	 -->
<%--                                         <a href="${context}seguimientoPostulacionesExternas.do?method=init"  id="seguimiento_postulaciones_externas">Seguimiento a postulaciones externas</a> --%>
<!--                                     </li> -->
<%--                                 </c:otherwise> --%>
<%--                             </c:choose> --%>
<%--                             <c:choose> --%>
<%--                                 <c:when test="${difTablaPager == '_REGISTRAR_MOTIVO_DE_NO_POSTULACION' }"> --%>
<!--                                     <li class="curSubTab"> -->
<!--                                         <span>Registrar motivo de no postulaci&oacute;n</span> -->
<!--                                     </li> -->
<%--                                 </c:when> --%>
<%--                                 <c:otherwise> --%>
<!--                                     <li> -->
<!--                                         <a href="/adminNoPostulacionesCandidato.do?method=init" id="registrar_motivo_no_postulacion">Registrar motivo de no postulaci&oacute;n</a> -->
<!--                                     </li> -->
<%--                                 </c:otherwise> --%>
<%--                             </c:choose> --%>
<!--                         </ul> -->
<%--                     </c:if> --%>
                    <!--FIN_JGLC_PPC-->
                    <div class="clearfix"></div>
                </div>
            </div>
        </div>
	

		<fieldset>
			<legend>Seguimiento a postulación</legend>
			<div>
				<p><strong>ID de la oferta: </strong><% out.print(spf.getOffer().getIdOfertaEmpleo()); %></p>
				<p><strong>Nombre de la oferta de empleo: </strong><% out.print(spf.getOffer().getTituloOferta()); %></p>
				<p><strong>Empresa: </strong><% out.print(spf.getOffer().getEmpresaNombre()); %></p>
			</div>
			<div>
				<label><span>*</span>¿Cuándo te contactó la empresa? </label>
				<p>
					<select id="diaContacto" name="diaContacto" disabled="disabled"
						required="true" missingMessage="El día es requerido."
						invalidMessage="Dia no válido, favor de verificar."
						promptMessage="Dia"
					    value="<% out.print(spf.getDiaContacto()); %>" autocomplete="true"
					    dojotype="dijit.form.FilteringSelect" store="diasStore">
					</select>
					<select id="mesContacto" name="mesContacto" disabled="disabled"
						required="true" missingMessage="El mes es requerido.."
						invalidMessage="Mes no válido, favor de verificar."
						promptMessage="Mes"
					    value="<% out.print(spf.getMesContacto()); %>" autocomplete="true"
					    dojotype="dijit.form.FilteringSelect" store="mesesStore">
					</select>
					<select id="anioContacto" name="anioContacto" disabled="disabled"
						required="true" missingMessage="El año es requerido.."
						invalidMessage="Año no válido, favor de verificar." 
						promptMessage="Año"
					    value="<% out.print(spf.getAnioContacto()); %>" autocomplete="true"
					    dojotype="dijit.form.FilteringSelect" store="aniosStore">
					</select>
				</p>
			</div>
			<div>
				<label><span>*</span>¿Conseguiste una entrevista de trabajo? </label>
				<p>
					<input type="radio" name="conseguisteEntrevista" id="conseguisteEntrevistaSi" value="2" disabled="disabled" <% if (spf.getConseguisteEntrevista() == 2) {out.print("checked");} %> /><label for="conseguisteEntrevistaSi">Sí</label><br />
			    	<input type="radio" name="conseguisteEntrevista" id="conseguisteEntrevistaNo" value="1" disabled="disabled" <% if (spf.getConseguisteEntrevista() == 1) {out.print("checked");} %>/><label for="conseguisteEntrevistaNo">No</label>
				</p>
			</div>
			<div id="programoEntrevistaSi" style="display:
				<% 
					if (spf.getConseguisteEntrevista() == 2) out.print("block");
					else out.print("none");
				%>">
				<label><span>*</span>¿Para cuándo se programó tu entrevista? </label>
				<p>
					<select id="diaEntrevista" name="diaEntrevista" disabled="disabled"
						required="true" missingMessage="El día es requerido."
						invalidMessage="Dia no válido, favor de verificar."
						promptMessage="Dia"
					    value="<% out.print(spf.getDiaEntrevista()); %>" autocomplete="true"
					    dojotype="dijit.form.FilteringSelect" store="diasStore">
					</select>
					<select id="mesEntrevista" name="mesEntrevista" disabled="disabled"
						required="true" missingMessage="El mes es requerido."
						invalidMessage="Mes no válido, favor de verificar."
						promptMessage="Mes"
					    value="<% out.print(spf.getMesEntrevista()); %>" autocomplete="true"
					    dojotype="dijit.form.FilteringSelect" store="mesesStore">
					</select>
					<select id="anioEntrevista" name="anioEntrevista" disabled="disabled"
						required="true" missingMessage="El año es requerido."
						invalidMessage="Año no válido, favor de verificar." 
						promptMessage="Año"
					    value="<% out.print(spf.getAnioEntrevista()); %>" autocomplete="true"
					    dojotype="dijit.form.FilteringSelect" store="aniosStore">
					</select>
				</p>
			</div>
			<div id="programoEntrevistaNo" style="display:<% 
					if (spf.getConseguisteEntrevista() > 0) out.print("block");
					else out.print("none");
				%>">
				<label>¿Cuál fue el motivo por el que no te programaron una entrevista?</label>
				<p>
					<select id="motivoNoEntrevista" name="motivoNoEntrevista" disabled="disabled"
						required="false" missingMessage="Debe indicar motivo."
						invalidMessage="Motivo no válido, favor de verificar."
						promptMessage="Motivo"
					    value="<% out.print(spf.getMotivoNoEntrevista()); %>" autocomplete="true"
					    dojotype="dijit.form.FilteringSelect" store="motivoNoEntrevistaStore">
					</select>
				</p>
			</div>
			<div>
				<div class="sublevelTitle">
					Resultado de la entrevista
				</div>
				<div>
					<div>
						<p>Llena los siguientes campos para ingresar el resultado de tu entrevista</p>
					</div>
					<div>
						<label><span>*</span>¿Fuiste contratado? </label>
						<p>
							<input type="radio" name="fuisteContratado" id="fuisteContratadoSi" onclick="entrevistaTrabajo()" value="2" <% if (spf.getFuisteContratado() == 2) {out.print("checked");} %> /><label for="fuisteContratadoSi">Sí</label><br/>
					    	<input type="radio" name="fuisteContratado" id="fuisteContratadoNo" onclick="entrevistaTrabajo()" value="1" <% if (spf.getFuisteContratado() == 1) {out.print("checked");} %> /><label for="fuisteContratadoNo">No</label><br/>
					    	<input type="radio" name="fuisteContratado" id="fuisteContratadoEsp" onclick="entrevistaTrabajo()" value="3" <% if (spf.getFuisteContratado() == 3) {out.print("checked disabled");} %> /><label for="fuisteContratadoEsp">Estoy en espera del proceso de selección</label>
						</p>
					</div>
					<div id="fuisteContratadoFechaSi" style="display:<% 
							if (spf.getFuisteContratado() == 2) out.print("block");
							else out.print("none");
						%>">
						<label>¿A partir de qué fecha quedaste contratado?</label>
						<p>
							<select id="diaContrato" name="diaContrato"
								required="true" missingMessage="El día es requerido."
								invalidMessage="Dia no válido, favor de verificar."
								promptMessage="Dia"
							    value="<% out.print(spf.getDiaContrato()); %>" autocomplete="true"
							    dojotype="dijit.form.FilteringSelect" store="diasStore">
							</select>
							<select id="mesContrato" name="mesContrato"
								required="true" missingMessage="El mes es requerido."
								invalidMessage="Mes no válido, favor de verificar."
								promptMessage="Mes"
							    value="<% out.print(spf.getMesContrato()); %>" autocomplete="true"
							    dojotype="dijit.form.FilteringSelect" store="mesesStore">
							</select>
							<select id="anioContrato" name="anioContrato"
								required="true" missingMessage="El año es requerido."
								invalidMessage="Año no válido, favor de verificar." 
								promptMessage="Año"
							    value="<% out.print(spf.getAnioContrato()); %>" autocomplete="true"
							    dojotype="dijit.form.FilteringSelect" store="aniosStore">
							</select>
						</p>
					</div>
					<div id="fuisteContratadoFechaNo" style="display:<% 
							if (spf.getFuisteContratado() == 1) out.print("block");
							else out.print("none");
						%>">
						<label>¿Cuál fue el motivo por el que no te contrataron?</label>
						<p>
							<select id="motivoNoContrato" name="motivoNoContrato" onchange="verifyOption()"
								required="false" missingMessage="Debe seleccionar motivo por el que no te contrataron."
								invalidMessage="Motivo no válido, favor de verificar."
								promptMessage="Motivo"
							    value="<% out.print(spf.getMotivoNoContrato()); %>" autocomplete="true"
							    dojotype="dijit.form.FilteringSelect" store="motivoNoContratoStore">
							</select>
						</p>
					</div>
					<div id="otroMotivo" style="display:<% 
							if (spf.getMotivoNoContrato() == 13) out.print("block");
							else out.print("none");
						%>">
						<label>Especifica motivo</label>
						<p>
							<textArea cols="56" rows="5" name="motivoNoContratoEspecifique" id="motivoNoContratoEspecifique" data-dojo-type="dijit/form/SimpleTextarea"
								required="false" trim="true" clearOnClose="true" invalidMessage="Caracter o longitud no validos." missingMessage="Debe capturar motivo por el que no te contrataron."
								regExp="${regexpmotivo}" dojoType="dijit.form.ValidationTextBox" maxlength="150" onkeyup="javascript:caracteresValidos(this);" ><% out.println(null != spf.getMotivoNoContratoEspecifique() ? spf.getMotivoNoContratoEspecifique() : ""); %></textArea>
						</p>
					</div>
				</div>
			</div>
		</fieldset>
		<div class="form_nav">
			<input id="btnEnviar" name="btnEnviar" class="boton_azul" type="button" value="Guardar" onclick="verificarResultado();" />
			<input id="btnCancelar" name="btnCancelar" class="boton_azul" type="button" value="Cancelar" onclick="dijit.byId('msgCancelar').show();" />
		</div>
	</div>
</form>
<div dojoType="dijit.Dialog" id="msgCancelar" title="Aviso" style="display:none" draggable="false">
	<div class="msg_contain">
		<p>Los datos no guardados se perder&aacute;n. ¿Continuar?</p>		
	</div>
	<p class="form_nav">	
		<button class="button" onclick="window.open('<c:url value="/misofertas.do?method=misPostulaciones"/>', '_self');">Aceptar</button>
		<button class="button" onclick="dijit.byId('msgCancelar').hide();">Cancelar</button>
	</p>
</div>
<script>
	showMensaje();
</script>