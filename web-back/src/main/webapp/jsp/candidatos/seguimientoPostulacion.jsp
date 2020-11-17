<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ page
	import="java.util.Calendar, mx.gob.stps.portal.web.candidate.form.SeguimientoPostulacionForm"%>
<%
	String context = request.getContextPath() + "/";
	SeguimientoPostulacionForm spf = (SeguimientoPostulacionForm) request
			.getSession().getAttribute("SeguimientoPostulacionForm");
	Calendar cal = Calendar.getInstance();
	cal.setTime(spf.getOfferCand().getFechaAlta());
%>
<script type="text/javascript" src="js/helper/messageHelper.js"></script>
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
		textBox.validator = function() {
			return false;
		};
		textBox.validate();
		textBox.validator = originalValidator;
		dijit.showTooltip(errmsg, textBox.domNode, textBox
				.get("tooltipPosition"), !textBox.isLeftToRight());
	}
	function entrevistaTrabajo() {
		if (dojo.byId('conseguisteEntrevistaSi').checked) {
			dojo.byId('programoEntrevistaSi').style.display = 'block';
			dojo.byId('programoEntrevistaNo').style.display = 'none';
		} else {
			dojo.byId('programoEntrevistaSi').style.display = 'none';
			dojo.byId('programoEntrevistaNo').style.display = 'block';
		}
	}
	function verificarSeguimiento() {
		
		var activoPpc = dojo.byId('activePpc').value;
		var inactivoPpc = dojo.byId('inactivePpc').value;
		
<%-- 		alert('<%=candidatoheader.isActiveToPpc()%>'); --%>
		if(activoPpc == 'true' || inactivoPpc == 'true'){
			if (!validateSelect('diaContacto'))
				return false;
			if (!validateSelect('mesContacto'))
				return false;
			if (!validateSelect('anioContacto'))
				return false;
			if (!validateGetInterview())
				return false;
			if (!validateScheduleInterview())
				return false;
			if (!validatePostDate())
				return false;
		}
		//var answer = confirm("¿Está seguro de guardar datos?");
		//if (answer) {
		doSubmit('tracking');
		//}
	}
	function doSubmit(method) {
		dojo.byId('method').value = method;
		dojo.byId('segPostForm').submit();
	}
	function validateSelect(field) {
		var control = dijit.byId(field);
		if (control
				&& (control.get('value') == '0' || control.get('value') == '')) {
			showErrorMsg(control);
			return false;
		}
		return true;
	}
	function validateGetInterview() {
		var conseguisteEntrevistaSi = document
				.getElementById('conseguisteEntrevistaSi');
		var conseguisteEntrevistaNo = document
				.getElementById('conseguisteEntrevistaNo');
		if (!conseguisteEntrevistaSi.checked
				&& !conseguisteEntrevistaNo.checked) {
			message('Debe seleccionar si consiguió una entrevista de trabajo');
			return false;
		}
		return true;
	}
	function validateScheduleInterview() {
		var conseguisteEntrevistaSi = document
				.getElementById('conseguisteEntrevistaSi');
		if (conseguisteEntrevistaSi.checked) {
			if (!validateSelect('diaEntrevista'))
				return false;
			if (!validateSelect('mesEntrevista'))
				return false;
			if (!validateSelect('anioEntrevista'))
				return false;
			if (!validateSchDate())
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
<%if (spf.getConseguisteEntrevista() == 2) {%>
	msg = '<p>Si ya se realizó tu entrevista selecciona continuar, en caso contrario selecciona salir.</p>'
				+ '<div class="form_nav 2"><button id="btnCont" name="btnCont" onclick="next();">Continuar</button>'
				+ '<button id="btnExit" name="btnExit" onclick="exit();">Salir</button></div>';
<%} else if (spf.getMotivoNoEntrevista() == 1
					|| spf.getMotivoNoEntrevista() == 3) {%>
	msg = '<p>Si posteriormente, la empresa te contacta para programar una entrevista, no olvides ingresar la fecha de entrevista.</p>'
				+ '<div class="form_nav 2"><button id="btnAcp" name="btnAcp" onclick="exit();">Salir</button></div>';
<%} else if ((spf.getMotivoNoEntrevista() == 2
					|| spf.getMotivoNoEntrevista() == 4 || spf
					.getMotivoNoEntrevista() == 5) && spf.getEstatusPPC() > 0) {%>
	msg = '<p>Para evitar la inactividad de tu registro en el Programa de Promoción y Colocación del Seguro de Desempleo (PPC-SD), debes continuar con la búsqueda de ofertas y postularte a las de tu interés.</p>'
				+ '<div class="form_nav 2"><button id="btnAcp" name="btnAcp" onclick="exit();">Aceptar</button></div>';
<%} else {%>
	msg = '<p>Te recomendamos continuar buscando ofertas de empleo y postulándote a las de tu interés.</p>'
				+ '<div class="form_nav 2"><button id="btnAcp" name="btnAcp" onclick="exit();">Aceptar</button></div>';
<%}%>
	dialogMensaje = new dijit.Dialog({
			title : 'Mensaje',
			content : msg,
			style : "width:300px;",
			showTitle : true,
			draggable : true,
			closable : true
		});
<%if (spf.getControl() > 0
					|| spf.getConseguisteEntrevista() > 1
					|| (spf.getMotivoNoEntrevista() > 1 && spf
							.getMotivoNoEntrevista() != 3)) {%>
	dojo.style(dialogMensaje.closeButtonNode, "display", "none");
		dialogMensaje.show();
<%}%>
	}
	function closeMsg() {
		dialogMensaje.hide();
	}
	function closeCancel() {
		dojo.byId('msgCancelar').hide();
	}
	function exit() {
		closeMsg();
		window.location.href = "<c:url value="/misofertas.do?method=misPostulaciones"/>";
	}
	function next() {
		doSubmit('interview');
	}
	function validatePostDate() {
		var today = new Date();
		var datePost = new Date();
		var dateContac = new Date();
		var diaPost = dijit.byId('diaPost').get('value');
		var mesPost = dijit.byId('mesPost').get('value');
		var anioPost = dijit.byId('anioPost').get('value');
		var diaContacto = dijit.byId('diaContacto').get('value');
		var mesContacto = dijit.byId('mesContacto').get('value');
		var anioContacto = dijit.byId('anioContacto').get('value');
		dateContac.setFullYear(anioContacto, --mesContacto, diaContacto);
		datePost.setFullYear(anioPost, --mesPost, diaPost);
		if (dateContac > today) {
			message('La fecha ¿Cuándo te contactó la empresa? debe ser igual o inferior a la fecha actual.');
			return false;
		}
		if (datePost > dateContac) {
			++mesPost;
			message('La fecha ¿Cuándo te contactó la empresa? debe ser igual o posterior a la fecha '
					+ diaPost
					+ "/"
					+ ("0" + mesPost).slice(-2)
					+ "/"
					+ anioPost);
			return false;
		}
		return true;
	}
	function validateSchDate() {
		var dateSch = new Date();
		var dateContac = new Date();
		var diaContacto = dijit.byId('diaContacto').get('value');
		var mesContacto = dijit.byId('mesContacto').get('value');
		var anioContacto = dijit.byId('anioContacto').get('value');
		var diaEntrevista = dijit.byId('diaEntrevista').get('value');
		var mesEntrevista = dijit.byId('mesEntrevista').get('value');
		var anioEntrevista = dijit.byId('anioEntrevista').get('value');
		dateContac.setFullYear(anioContacto, --mesContacto, diaContacto);
		dateSch.setFullYear(anioEntrevista, --mesEntrevista, diaEntrevista);
		var last = new Date();
		last.setDate(dateContac.getDate() + 90);
		var day = last.getDate();
		var month = last.getMonth() + 1;
		var year = last.getFullYear();
		if (dateContac > dateSch) {
			++mesContacto;
			message('La fecha ¿Para cuándo se programó tu entrevista? debe ser igual o posterior a la fecha '
					+ diaContacto
					+ "/"
					+ ("0" + mesContacto).slice(-2)
					+ "/"
					+ anioContacto);
			return false;
		}
		if (dateSch > last) {
			message('La fecha ¿Para cuándo se programó tu entrevista? no debe ser mayor a la fecha '
					+ day + "/" + ("0" + month).slice(-2) + "/" + year);
			return false;
		}
		return true;
	}
</script>
<div dojoType="dojo.data.ItemFileReadStore" jsId="diasStore"
	url="${context}registro.do?method=dias"></div>
<div dojoType="dojo.data.ItemFileReadStore" jsId="mesesStore"
	url="${context}seguimientoPostulacion.do?method=meses"></div>
<div dojoType="dojo.data.ItemFileReadStore" jsId="aniosStore"
	url="${context}registro.do?method=aniosbusca"></div>
<div dojoType="dojo.data.ItemFileReadStore"
	jsId="motivoNoEntrevistaStore"
	url="${context}seguimientoPostulacion.do?method=motivoNoEntrevista"></div>
<form name="segPostForm" id="segPostForm" method="post"
	action="seguimientoPostulacion.do" dojoType="dijit.form.Form">
	<input type="hidden" name="method" id="method" value="init"
		dojoType="dijit.form.TextBox" /> <input type="hidden" name="diaPost"
		id="diaPost" value="<%out.println(cal.get(Calendar.DAY_OF_MONTH));%>"
		dojoType="dijit.form.TextBox" /> <input type="hidden" name="mesPost"
		id="mesPost" value="<%out.println(cal.get(Calendar.MONTH) + 1);%>"
		dojoType="dijit.form.TextBox" /> <input type="hidden" name="anioPost"
		id="anioPost" value="<%out.println(cal.get(Calendar.YEAR));%>"
		dojoType="dijit.form.TextBox" />
	<div class="publicados miEspacio">
		<h2>Mi espacio</h2>
		<div class="tab_block">
			<div align="left" style="display: block;" id="returnME">
				<a class="expand"
					href="javascript:location.replace('<c:url value="/miEspacioCandidato.do"/>');">
					<strong>Ir al inicio de Mi espacio</strong>
				</a>
			</div>
			<div class="Tab_espacio">
				<h3>Mis ofertas de empleo</h3>

				<div class="subTab">
					<ul>
						<c:choose>
							<c:when test="${difTablaPager == '_MIS_POSTULACIONES' }">
								<li class="curSubTab"><span>Mis postulaciones</span></li>
							</c:when>
							<c:otherwise>
								<li><a
									href="<c:url value="/misofertas.do?method=misPostulaciones"/>">Mis
										postulaciones</a></li>
							</c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test="${difTablaPager == '_MIS_OFERTAS_GUARDADAS' }">
								<li class="curSubTab"><span>Mis ofertas guardadas</span></li>
							</c:when>
							<c:otherwise>
								<li><a
									href="<c:url value="/misofertas.do?method=misOfertasGuardadas"/>">Mis
										ofertas guardadas</a></li>
							</c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test="${difTablaPager == '_EMPRESAS_QUE_ME_BUSCAN' }">
								<li class="curSubTab"><span>Empresas que me buscan</span></li>
							</c:when>
							<c:otherwise>
								<li><a
									href="<c:url value="/misofertas.do?method=empresasQueMeBuscan"/>">Empresas
										que me buscan</a></li>
							</c:otherwise>
						</c:choose>
						<!-- New links to PPC-SD -->
						<!--INI_JGLC_PPC-->
						<%-- 						<c:if --%>
						<%-- 							test="${candidatoheader.isActiveToPpc() || candidatoheader.isInactiveToPpc()}"> --%>
						<%-- 							<c:choose> --%>
						<%-- 								<c:when --%>
						<%-- 									test="${difTablaPager == '_REGISTRAR_POSTULACIONES_EXTERNAS' }"> --%>
						<!-- 									<li class="curSubTab"><span>Registrar postulaciones -->
						<!-- 											externas</span></li> -->
						<%-- 								</c:when> --%>
						<%-- 								<c:otherwise> --%>
						<!-- 									<li><a -->
						<%-- 										href="${context}registroPostulacionExterna.do?method=init" --%>
						<!-- 										id="registro_postulaciones_externas">Registrar -->
						<!-- 											postulaciones externas</a></li> -->
						<%-- 								</c:otherwise> --%>
						<%-- 							</c:choose> --%>
						<%-- 							<c:choose> --%>
						<%-- 								<c:when --%>
						<%-- 									test="${difTablaPager == '_SEGUIMIENTO_A_POSTULACIONES_EXTERNAS' }"> --%>
						<!-- 									<li class="curSubTab"><span>Seguimiento a -->
						<!-- 											postulaciones externas</span></li> -->
						<%-- 								</c:when> --%>
						<%-- 								<c:otherwise> --%>
						<!-- 									<li><a -->
						<%-- 										href="${context}seguimientoPostulacionesExternas.do?method=init" --%>
						<!-- 										id="seguimiento_postulaciones_externas">Seguimiento a -->
						<!-- 											postulaciones externas</a></li> -->
						<%-- 								</c:otherwise> --%>
						<%-- 							</c:choose> --%>
						<%-- 							<c:choose> --%>
						<%-- 								<c:when --%>
						<%-- 									test="${difTablaPager == '_REGISTRAR_MOTIVO_DE_NO_POSTULACION' }"> --%>
						<!-- 									<li class="curSubTab"><span>Registrar motivo de no -->
						<!-- 											postulaci&oacute;n</span></li> -->
						<%-- 								</c:when> --%>
						<%-- 								<c:otherwise> --%>
						<!-- 									<li><a -->
						<!-- 										href="/adminNoPostulacionesCandidato.do?method=init" -->
						<!-- 										id="registrar_motivo_no_postulacion">Registrar motivo de -->
						<!-- 											no postulaci&oacute;n</a></li> -->
						<%-- 								</c:otherwise> --%>
						<%-- 							</c:choose> --%>
						<!-- 					</ul> -->
						<%-- 					</c:if> --%>
						<!--FIN_JGLC_PPC-->
						<div class="clearfix"></div>
				</div>
			</div>
		</div>

		<div class="formApp miEspacio">
			<fieldset>
				<legend>Seguimiento a la postulación</legend>
				<div>
					<p>
						<strong>ID de la oferta: </strong>
						<%
							out.print(spf.getOffer().getIdOfertaEmpleo());
						%>
					</p>
					<p>
						<strong>Nombre de la oferta de empleo: </strong>
						<%
							out.print(spf.getOffer().getTituloOferta());
						%>
					</p>
					<p>
						<strong>Empresa: </strong>
						<%
							out.print(spf.getOffer().getEmpresaNombre());
						%>
					</p>
				</div>
				<c:if
					test="${candidatoheader.isActiveToPpc() || candidatoheader.isInactiveToPpc()}">
					<div>
						<div class="labeled">
							<span>*</span>¿Cuándo te contactó la empresa?
						</div>
						<p>
							<select id="diaContacto" name="diaContacto" required="true"
								missingMessage="El día es requerido."
								invalidMessage="Dia no válido, favor de verificar."
								promptMessage="Dia" value="<%out.print(spf.getDiaContacto());%>"
								autocomplete="true" dojotype="dijit.form.FilteringSelect"
								store="diasStore">
							</select> <select id="mesContacto" name="mesContacto" required="true"
								missingMessage="El mes es requerido."
								invalidMessage="Mes no válido, favor de verificar."
								promptMessage="Mes" value="<%out.print(spf.getMesContacto());%>"
								autocomplete="true" dojotype="dijit.form.FilteringSelect"
								store="mesesStore">
							</select> <select id="anioContacto" name="anioContacto" required="true"
								missingMessage="El año es requerido."
								invalidMessage="Año no válido, favor de verificar."
								promptMessage="Año"
								value="<%out.print(spf.getAnioContacto());%>"
								autocomplete="true" dojotype="dijit.form.FilteringSelect"
								store="aniosStore">
							</select>
						</p>
					</div>

					<div>
						<label><span>*</span>¿Conseguiste una entrevista de
							trabajo? </label>
						<p>
							<input type="radio" name="conseguisteEntrevista"
								id="conseguisteEntrevistaSi" onclick="entrevistaTrabajo()"
								value="2"
								<%if (spf.getConseguisteEntrevista() == 2) {
					out.print("checked");
				}%> /><label
								for="conseguisteEntrevistaSi">Sí</label><br /> <input
								type="radio" name="conseguisteEntrevista"
								id="conseguisteEntrevistaNo" onclick="entrevistaTrabajo()"
								value="1"
								<%if (spf.getConseguisteEntrevista() == 1) {
					out.print("checked");
				}%> /><label
								for="conseguisteEntrevistaNo">No</label>
						</p>
					</div>
					<div id="programoEntrevistaSi"
						style="display:
				<%if (spf.getConseguisteEntrevista() == 2)
					out.print("block");
				else
					out.print("none");%>">
						<label><span>*</span>¿Para cuándo se programó tu
							entrevista? </label>
						<p>
							<select id="diaEntrevista" name="diaEntrevista" required="true"
								missingMessage="El día es requerido."
								invalidMessage="Dia no válido, favor de verificar."
								promptMessage="Dia"
								value="<%out.print(spf.getDiaEntrevista());%>"
								autocomplete="true" dojotype="dijit.form.FilteringSelect"
								store="diasStore">
							</select> <select id="mesEntrevista" name="mesEntrevista" required="true"
								missingMessage="El mes es requerido."
								invalidMessage="Mes no válido, favor de verificar."
								promptMessage="Mes"
								value="<%out.print(spf.getMesEntrevista());%>"
								autocomplete="true" dojotype="dijit.form.FilteringSelect"
								store="mesesStore">
							</select> <select id="anioEntrevista" name="anioEntrevista"
								required="true" missingMessage="El año es requerido."
								invalidMessage="Año no válido, favor de verificar."
								promptMessage="Año"
								value="<%out.print(spf.getAnioEntrevista());%>"
								autocomplete="true" dojotype="dijit.form.FilteringSelect"
								store="aniosStore">
							</select>
						</p>
					</div>
					<div id="programoEntrevistaNo"
						style="display:<%if (spf.getConseguisteEntrevista() == 1)
					out.print("block");
				else
					out.print("none");%>">
						<label><span>*</span>¿Cuál fue el motivo por el que no te
							programaron una entrevista?</label>
						<p>
							<select id="motivoNoEntrevista" name="motivoNoEntrevista"
								required="false"
								missingMessage="Debe seleccionar el motivo por el que no programaron entrevista."
								invalidMessage="Motivo no válido, favor de verificar."
								promptMessage="Motivo"
								value="<%out.print(spf.getMotivoNoEntrevista());%>"
								autocomplete="true" dojotype="dijit.form.FilteringSelect"
								store="motivoNoEntrevistaStore">
							</select>
						</p>
					</div>
				</c:if>
			</fieldset>
		</div>

		<div class="form_nav">
			<input id="btnEnviar" name="btnEnviar" class="boton_azul"
				type="button" value="Guardar" onclick="verificarSeguimiento();" />
			<input id="btnCancelar" name="btnCancelar" class="boton_azul"
				type="button" value="Cancelar"
				onclick="dijit.byId('msgCancelar').show();" />
		</div>
	</div>
</form>
<div dojoType="dijit.Dialog" id="msgCancelar" title="Aviso"
	style="display: none" draggable="false">
	<div class="msg_contain">
		<p>Los datos no guardados se perder&aacute;n. ¿Continuar?</p>
	</div>
	<p class="form_nav">
		<button class="button"
			onclick="window.open('<c:url value="/misofertas.do?method=misPostulaciones"/>', '_self');">Aceptar</button>
		<button class="button" onclick="dijit.byId('msgCancelar').hide();">Cancelar</button>
	</p>
</div>

<input type="hidden" id="activePpc" value="${candidatoheader.isActiveToPpc()}"/>
<input type="hidden" id="inactivePpc" value="${candidatoheader.isInactiveToPpc()}"/>

<script>
	showMensaje();
</script>