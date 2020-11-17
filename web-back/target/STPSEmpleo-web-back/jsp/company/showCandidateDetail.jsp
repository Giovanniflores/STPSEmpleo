<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="mx.gob.stps.portal.core.candidate.vo.*" %>
<%@ page import="java.util.Calendar"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	OfertaCandidatoResumenVo ofertaCandidatoVo = new OfertaCandidatoResumenVo();
	InformacionGeneralVO detalle = (InformacionGeneralVO) request.getAttribute("detalle");
	pageContext.setAttribute("detalle", detalle);

	Calendar cal = Calendar.getInstance();
	int fulfillment = ((Integer) request.getAttribute("fulfillment")).intValue();
	long idCandidato = ((Long) request.getAttribute("idCandidato")).longValue();
	long idOferta = 0;
	int intCandidatoEntrevistaId = 0;

	try {
		idOferta = Long.parseLong(request.getParameter("ido"));
	} catch (Exception nfe) {}

	pageContext.setAttribute("idOferta", idOferta);

	try {
		ofertaCandidatoVo = (OfertaCandidatoResumenVo) request.getAttribute("ofertaCandidato");
		cal.setTime(ofertaCandidatoVo.getFecha());
		intCandidatoEntrevistaId = (int)ofertaCandidatoVo.getEntrevista().getIdEntrevista();
	}catch (NullPointerException npe){}
	pageContext.setAttribute("ofertaCandidatoVo", ofertaCandidatoVo);

	long idEstatus = 0;
	String context = request.getContextPath() +"/";
	String cvCompleto= context+"miscandidatos.do?method=genera&idCandidato="+ofertaCandidatoVo.getIdCandidato();
	String contactCand = "admonCandidatos.do?method=init&ido=" + ofertaCandidatoVo.getIdOfertaEmpleo();
	String invoke = (null != request.getSession().getAttribute("_urlpageinvoke") ? (String)request.getSession().getAttribute("_urlpageinvoke") : "admonCandidatos.do?method=init&ido=" + ofertaCandidatoVo.getIdOfertaEmpleo());
	String estatusOfertaCandidato = (null != request.getSession().getAttribute("estatusOfertaCandidato") ? (String)request.getSession().getAttribute("estatusOfertaCandidato") : "0");
%>
<script type="text/javascript" src="js/helper/messageHelper.js"></script>
<script type="text/javascript">
	dojo.require("dijit.dijit"); // loads the optimized dijit layer
	dojo.require("dijit.form.Form");
	dojo.require("dijit.Dialog");
	dojo.require("dijit.form.Button");
	dojo.require("dijit.form.DateTextBox");
	dojo.require("dijit.form.ValidationTextBox");
	dojo.require("dijit.form.TextBox");
	dojo.require("dojo.data.ItemFileReadStore");
	dojo.require("dijit.form.CheckBox");
	dojo.require("dijit.form.FilteringSelect");
	dojo.require("dijit.form.MultiSelect");

	function validar(accion) {
		ocultarDatosEntrevista();
		if (accion != null){
			cambiarEstatusOfertaCandidato(accion, '${idOferta}'); 	//se envia directamente a la accion facilitada, nada que validar;
			return;
		}else if (validarRadio('idBtnAccion')) {						// la accion proviene de la seleccion del radio button
			var valor = valorRadio('idBtnAccion');
			if (validarForm(valor)){
				if (valor == '${idEstatusRechazo}'){
					if (!setMotivo()){
						return;
					}
				}
				if (valor == '${idEstatusContrato}'){
					if (!validarFecha()){
						return;
					}
				}
				cambiarEstatusOfertaCandidato(valor, '${idOferta}');
			}
		}else{
			alert("Debe seleccionar una opci&oacute;n");
		}
	}

	function contactarCandidato() {
		ocultarDatosEntrevista();
		dojo.xhrPost( {
			url: 'viewCandidateInfo.do',
			form:'contactarCandidatoForm',
			timeout:180000,
			load: function(data) {
				var res = dojo.fromJson(data);
				if (res.type == 'ext') {
					alert(res.message);
					document.botonesCandidatosForm.action =
							'${context}admonCandidatos.do?method=init&ido=<%=ofertaCandidatoVo.getIdOfertaEmpleo()%>';
					document.botonesCandidatosForm.submit();
				} else {
					alert(res.message);
				}
			}
		});
	}

	function mostrarDatosEntrevista(){
		setDiv('divEntrevista', true);
		dijit.byId('fechaEntrevista').attr('required', true);
		//dijit.byId('idHoraSelect').attr('required', true);
		dijit.byId('hora').attr('required', true);
	}

	function ocultarDatosEntrevista(){
		if (<%=intCandidatoEntrevistaId%> == 0){
			setDiv('divEntrevista', false);
			dijit.byId('fechaEntrevista').attr('required', false);
			//dijit.byId('idHoraSelect').attr('required', false);
			dijit.byId('hora').attr('required', false);
		}
	}

	function validarEntrevista() {
		if (!dijit.byId('programarEntrevistaForm').isValid()) {
			alert('Existen campos obligatorios sin completar');
			return;
		}

		var control = dijit.byId('fechaEntrevista');
		if (!control.get('value') == ""){
			//control = dijit.byId('idHoraSelect');
			control = dijit.byId('hora');
			control.focus();
		}

		if (!validarFechaEntrevista()){
			return;
		}

		dojo.xhrPost( {
			url: 'viewCandidateInfo.do',
			form:'programarEntrevistaForm',
			timeout:180000,
			load: function(data) {
				var res = dojo.fromJson(data);
				if (res.type == 'ext') {
					messageInv(res.message,'${context}<%=contactCand%>');
					//document.contactarCandidatoForm.action = '${context}admonCandidatos.do?method=init&ido=<%=ofertaCandidatoVo.getIdOfertaEmpleo()%>';
					//document.contactarCandidatoForm.submit();
				} else {
					message(res.message);
				}
			}
		});
//		}
	}

	function validarForm(opcion) {
		var control = null;
		if (!dijit.byId('botonesCandidatosForm').isValid()) {
			if (opcion == '${idEstatusRechazo}'){
				control = dijit.byId('idMotivoSelect');
				if (!control.isValid()) {
					message('Seleccione el motivo de no aceptación');
					return false;
				}
			}else if (opcion == '${idEstatusContrato}') {
				control = dijit.byId('fechaContratoSelect');
				if (!control.isValid()) {
					message('La fecha de contratación no es válida');
					return false;
				}
			}else if (opcion == '${idAccionEntrevista}'){
				control = dijit.byId('fechaEntrevista');
			}
			if (control.get('value') == ""){
				control.focus();
				message('Existen campos obligatorios sin completar');
			}
			return false;
		}
		return true;
	}
	
	function setMotivo() {
		dijit.byId('idMotivo').set('value', dijit.byId('idMotivoSelect').get('value'));
		if (dijit.byId('idMotivo').getValue() == 13 && dijit.byId('motivo').getValue() == '') {
			message('Especifique otro motivo de no aceptación');
			dijit.byId('motivo').focus();
			return false;
		}
		if (dijit.byId('idMotivo').getValue() == 13 && !isValidExp(dijit.byId('motivo'))) {
			message('No se permiten carateres especiales en otro motivo de no aceptación');
			dijit.byId('motivo').focus();
			return false;
		}
		return true;
	}
	function validarFecha() {
		var datePost = new Date();
		var dateFin = dijit.byId('fechaContratoSelect');
		if (dateFin == '') {
			alert('Debe seleccionar fecha de contrato');
			return false;
		}
		var diaPost = dijit.byId('diaPost').get('value');
		var mesPost = dijit.byId('mesPost').get('value');
		var anioPost = dijit.byId('anioPost').get('value');
		datePost.setFullYear(anioPost, --mesPost, diaPost);
		var dif = dojo.date.compare(datePost, dateFin.get('value'), 'date');
		if (dif > 0) {
			++mesPost;
			message('La fecha de contratación debe ser igual o posterior a la fecha ' + diaPost + '/' + ("0" + mesPost).slice(-2) + '/' + anioPost);
			dateFin.focus();
			return false;
		}
		return true;
	}

	function validarFechaEntrevista() {
		var dateFin = dijit.byId('fechaEntrevista').get('value');
		var dateHoy = new Date();
		var hora = new Array();
		//hora = dijit.byId('idHoraSelect').get('displayedValue').split(':');
		hora = dijit.byId('hora').get('value').split(':');
		dateFin.setHours(hora[0], hora[1]);
		var dif = dojo.date.difference(dateHoy, dateFin, 'minute');
		if ((dif/60) < 72) {
			message('La fecha de la entrevista debe ser mayor a 72 horas naturales con respecto a la fecha actual');
			dateFin.focus();
			return false;
		}
		//dijit.byId('valorHora').set('value', dijit.byId('idHoraSelect').get('displayedValue'));
		dijit.byId('valorHora').set('value', dijit.byId('hora').get('value'));
		return true;
	}

	function validarRadio(nameGrp) {
		var elements = document.getElementsByName(nameGrp);
		var radioChecked = false;
		for (i = 0; i < elements.length; i++) {
			if (elements[i].checked) {
				radioChecked = elements[i].checked;
				break;
			}
		}
		return radioChecked;
	}

	function valorRadio(nameGrp) {
		var elements = document.getElementsByName(nameGrp);
		var valor = 0;
		for (i = 0; i < elements.length; i++) {
			if (elements[i].checked) {
				valor = elements[i].value;
				break;
			}
		}
		return valor;
	}
	function verifyOption() {
		var option = dijit.byId('idMotivoSelect').get('value');
		if (option == 13)
			dojo.byId('otroMotivo').style.display='block';
		if (option != 13)
			dojo.byId('otroMotivo').style.display='none';
	}
	function cambioOpcion(idEstatusOpcion) {
		try { 
			if (dijit.byId('idMotivoSelect'))
				dijit.byId('idMotivoSelect').reset();
			if (dijit.byId('fechaContratoSelect'))
				dijit.byId('fechaContratoSelect').reset();
			setDiv('divEnviar', true);
			if (idEstatusOpcion == '${idEstatusProceso}') {
				setDiv('divMotivos', false);
				setDiv('divFechaContrato', false);
				if (dijit.byId('idMotivoSelect'))
					dijit.byId('idMotivoSelect').attr('required', false);
				if (dijit.byId('fechaContratoSelect'))
					dijit.byId('fechaContratoSelect').attr('required', false);
			}
			if (idEstatusOpcion == '${idEstatusRechazo}'){
				setDiv('divMotivos', true);
				setDiv('divFechaContrato', false);
				if (dijit.byId('idMotivoSelect'))
					dijit.byId('idMotivoSelect').attr('required', true);
				if (dijit.byId('fechaContratoSelect'))
					dijit.byId('fechaContratoSelect').attr('required', false);
			}
			if (idEstatusOpcion == '${idEstatusContrato}'){
				setDiv('divMotivos', false);
				setDiv('divFechaContrato', true);
				dijit.byId('idMotivoSelect').attr('required', false);
				dijit.byId('fechaContratoSelect').attr('required', true);

			}
		}catch (e){
			alert(e);
		}
	}
	function setDiv(id, visible) {
		var vStyle = visible ? 'visible':'hidden';
		var vBlock = visible ? 'block':'none';
		document.getElementById(id).style.visibility = vStyle;
		document.getElementById(id).style.display = vBlock;
	}
	function isValidExp(control) {
		var str = control.get('value');
		var patron = /^[0-9A-Za-z\\d\\s\\.\\,\\;\\:\\-\\'áéíóúÁÉÍÓÚñÑüÜ ]{0,50}$/;
		return patron.test(str);
	}
	function cambiarEstatusOfertaCandidato(accion, idOferta) {
		dojo.byId('method').value="cambiarEstatusOfertaCandidato";
		dojo.byId('accion').value=accion;
		if (dijit.byId('idMotivoSelect'))
			dojo.byId('idMotivo').value=dijit.byId('idMotivoSelect').get('value');
		if (dijit.byId('idMotivoSelect') && dijit.byId('idMotivoSelect').get('value')==13) {
			dojo.byId('motivoDesc').value=dijit.byId('motivo').get('value');
		}
		if (dijit.byId('fechaContratoSelect') && null != dijit.byId('fechaContratoSelect').get('value')) {
			var date = dijit.byId('fechaContratoSelect').get('value');
			var day = date.getDate();
			var year = date.getFullYear();
			var month = date.getMonth() + 1;
			document.getElementById('fechaContrato').value=year+'-'+month+'-'+day;
		}
		dojo.xhrPost( {
			url: 'viewCandidateInfo.do',
			form:'botonesCandidatosForm',
			timeout:180000,
			load: function(data) {
				var res = dojo.fromJson(data);
				if (res.type == 'ext') {
					messageInv(res.message,'${context}<%=invoke%>');
					//document.botonesCandidatosForm.action = '${context}<%=invoke%>';
					//document.botonesCandidatosForm.submit();
				} else {
					message(res.message);
				}
			}
		});
	}
	function messageInv(msg,ruta) {
		var html =
			'<div id="messageDialgop2" name="messageDialgop2">' +
		'<p style="text-align: center;">'+ msg +'</p>'+
		'<p class="form_nav">'
		+ '<input id="btnCerrar" class="boton_naranja" type="button" name="btnCerrar" onClick="cerrarRuta(\''+ruta+'\');" value="Cerrar"/>';
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
</script>
<style>
	.formApp.miEspacio > h2 {display:none;}
</style>
<div class="detalle_oferta">
	<div class="det_index">
		<h2 class="titulo_profesion">Detalle del candidato</h2>

		<!--Se cambio la etiqueta de cierre del div class="ficha_oferta" este debe abarcar el div class="logo_empresa" y el div class="text_ficha_oferta"-->
		<div class="perfil_candidato">
			<div class="user_pic">
				<img src="<%=context%>imageAction.do?method=getFotoCandidatoRequest&<%="captcha?time="+ Calendar.getInstance().getTimeInMillis()%>&ID_CANDIDATO=<%=idCandidato%>"
					 alt="Foto Candidato" />
				<div class="shadow"></div>
			</div>


			<div class="perfil_c emp">
				<div class="perfil_title">
					<h3>${detalle.nombre} ${detalle.apellido1} ${detalle.apellido2}</h3>
					<ul class="puesto">
						<li>
							<strong>Puesto(s) solicitado(s):</strong>
							<br>
							${detalle.puestoSolicitado}
							<c:if test="${not empty detalle.ocupacionDeseada2}">
								<br>
								${detalle.ocupacionDeseada2}
							</c:if>
						</li>
					</ul>
				</div>

				<div class="perfil_index">
					<ul style="margin-right: 20px">
						<c:if test="${detalle.descripcionDiscapacidad != 'Ninguna'}">
							<li>
								<strong>Discapacidad</strong>
								<br>
								<span> ${detalle.descripcionDiscapacidad}</span>
							</li>
						</c:if>


						<c:if test="${detalle.contactoTelefono == 2}">
							<li>
								<strong>Tel&eacute;fono</strong>
								<br>
								<c:forEach var="telefono" items="${detalle.telefonos}" varStatus="rowMeter">
									<span>
										${telefono.acceso} - ${telefono.clave} - ${telefono.telefono} <c:if test="${not empty telefono.extension}"> ext. ${telefono.extension}</c:if>
									</span>
									<br>
								</c:forEach>
							</li>
						</c:if>

						<c:if test="${detalle.contactoCorreo == 2}">
							<li>
								<strong>Correo electr&oacute;nico<br></strong>
								<span>${detalle.correoElectronico}</span>
							</li>
						</c:if>

						<c:if test="${detalle.confidencialidadCandidato == 1}"><%-- 1 -> NO Confidencial --%>
							<li>
								<strong>Direcci&oacute;n</strong>
								<br>
									${detalle.nombreEntidad} ${detalle.nombreMunicipio} ${detalle.nombreColonia} ${detalle.codigoPostal != '' ? 'c.p. '.concat(detalle.codigoPostal) : ''}
							</li>
						</c:if>

					</ul>


					<ul class="conoce_mas">
						<%--<li><strong>Estatus</strong><br><span>${detalle.descEstatus}</span></li>--%>
						<%--<li><strong>Conoce m&aacute;s del candidato</strong></li>--%>

						<li>
							<%--<a class="consultar_mas_vacantes" href="<%=context%>miscandidatos.do?method=genera&idCandidato=<%=idCandidato%>">Ver CV completo</a>--%>
							<c:url var="urlAgregarCand" value="OfertaNavegacion.do">
								<c:param name="method" value="init"/>
								<c:param name="idCandidato" value="${requestScope.idCandidato}"/>
							</c:url>

							<c:if test="${estatusOfertaCandidato == '0'}">
								<input type="button" class="boton" name="btnAgregar" id="btnAgregar"
									   value="Agregar a mis candidatos seleccionados"
									   onclick="document.location='${urlAgregarCand}'"/>
							</c:if>

							<%--<%--%>
							<%--String urlRegresar = "#";--%>
							<%--if (null != request.getSession().getAttribute("_urlpageinvoke")) {--%>
							<%--urlRegresar = (String) request.getSession().getAttribute("_urlpageinvoke");--%>
							<%--}--%>
							<%--%>--%>
							<%--<div class="send_q"><input type="button" class="boton" name="btnRegresar" id="btnRegresar"--%>
							<%--value="Regresar a Mis ofertas de empleo"--%>
							<%--onclick="document.location='<%=urlRegresar%>'"/></div>--%>
						</li>


					</ul>
				</div>
			</div>
			<div class="clearfix"></div>
		</div>


		<div class="offer_index">
			<div class="offer_section" id="infoProf">
				<div class="ficha_relacionada" id="formacion">
					<h3>Formaci&oacute;n profesional</h3>
					<ul>
						<li class="odd">
							<strong>Estudios</strong>
							<br>
							<span>${detalle.gradoEstudios} en ${detalle.carreraEspecialidad}, ${detalle.situacionAcademica}</span>
						</li>

						<c:if test="${not empty detalle.idiomas}">
							<li>
								<strong>Idiomas adicionales al español</strong>
								<ul>
									<c:forEach var="idioma" items="${detalle.idiomas}" varStatus="rowMeter">
										<li>
											${not empty idioma.idioma and  idioma.idioma ne 'Ninguno' ? idioma.idioma : 'Ninguno'}
											<c:if test="${not empty idioma.idioma and idioma.idioma ne 'Ninguno'}">
												. Certificado ${idioma.certificacion}, ${idioma.dominio}
											</c:if>
										</li>
									</c:forEach>
								</ul>
							</li>
						</c:if>

						<c:if test="${not empty detalle.conocimientoComputacionVO}">
							<li class="odd">
								<strong>Conocimientos en computaci&oacute;n:</strong>
								<br/>
								<c:if test="${detalle.conocimientoComputacionVO.procesadorTxt==1}">Procesador de textos&nbsp;</c:if>
								<c:if test="${detalle.conocimientoComputacionVO.hojaCalculo==1}">Hojas de c&aacute;lculo&nbsp;</c:if>
								<c:if test="${detalle.conocimientoComputacionVO.internet==1}">Internet y correo electr&oacute;nico&nbsp;</c:if>
								<c:if test="${detalle.conocimientoComputacionVO.redesSociales==1}">Redes sociales&nbsp;</c:if>
								<c:if test="${not empty detalle.conocimientoComputacionVO.otros}">${detalle.conocimientoComputacionVO.otros}</c:if>
							</li>
						</c:if>

						<c:if test="${not empty detalle.conocimientos}">
							<li>
								<strong>Conocimientos:</strong>
								<ul>
									<c:forEach var="conocimiento" items="${detalle.conocimientos}" varStatus="rowMeter">
										<li>
												${conocimiento.conocimientoHabilidad}, experiencia ${conocimiento.experiencia != null ? conocimiento.experiencia : 'nada'}
										</li>
									</c:forEach>
								</ul>
							</li>
						</c:if>

						<c:if test="${not empty detalle.habilidadesCandidato}">
							<li>
								<strong>Habilidades y actitudes:</strong>
								<br/>
								<c:forEach var="habilidad" items="${detalle.habilidadesCandidato}" varStatus="rowMeter">
									${habilidad.opcion},
								</c:forEach>
							</li>
						</c:if>
					</ul>
				</div>

				<div class="offer_section" id="experiencia">
					<h3>Experiencia relevante</h3>
					<ul>
						<%--<li class="odd"><strong>A&ntilde;os de experiencia en el puesto solicitado:</strong> ${detalle.aniosExperencia}</li>--%>
						<li>
							<strong>A&ntilde;os de experiencia en ${detalle.puestoSolicitado}:</strong>
							<br/>
							${detalle.experienciaOcupacion1}
						</li>
						<c:if test="${not empty detalle.ocupacionDeseada2}">
							<li>
								<strong>A&ntilde;os de experiencia en ${detalle.ocupacionDeseada2}:</strong>
								<br/>
									${detalle.experienciaOcupacion2}
							</li>
						</c:if>
						<li>
							<strong>Experiencia:</strong>
							<br/>
							${detalle.experiencia}
						</li>

						<%--<c:if test="${not empty listaEstandares}">--%>
						<%--<c:forEach var="rowListaEstandares" items="${listaEstandares}">--%>
						<%--<li class="odd"><c:out value="${rowListaEstandares.titulo}"/></li>--%>
						<%--</c:forEach>--%>
						<%--</c:if>--%>
					</ul>
				</div>

				<div class="offer_section" id="expectativas">
					<h3>Expectativas laborales</h3>
					<ul>
						<li>
							<strong>Salario mensual pretendido:</strong>
							<br/>
							${detalle.salarioPretendido}
						</li>
						<!-- li>
							<strong>Disponibilidad para viajar:</strong>
							<br/>
							<c:if test="${detalle.disponibilidadViajar==1}">No</c:if>
							<c:if test="${detalle.disponibilidadViajar!=1}">S&iacute;</c:if>
						</li>
						<li>
							<strong>Disponibilidad para radicar fuera:</strong>
							<br/>
							<c:if test="${detalle.disponibilidadRadicar==1}">No</c:if>
							<c:if test="${detalle.disponibilidadRadicar!=1}">S&iacute;</c:if>
						</li -->
					</ul>
				</div>

				<div class="pdf_dwn">
					<a class="consultar_mas_vacantes" href="<%=context%>miscandidatos.do?method=genera&idCandidato=<%=idCandidato%>">Descargar CV completo en PDF</a>
				</div>  

				<c:if test="${ofertaCandidatoVo.getIdOfertaEmpleo() > 0}">

					<form name="contactarCandidatoForm" id="contactarCandidatoForm"
						  action="viewCandidateInfo.do?method=init&idc=${idCandidato}&ido=${idOferta}" method="post"
						  dojoType="dijit.form.Form">
						<input type="hidden" name="method" value="cambiarEstatusOfertaCandidato" dojoType="dijit.form.TextBox"/>
						<input type="hidden" name="accion" value="${idEstatusVinculado}" dojoType="dijit.form.TextBox"/>
							<%--<input type="hidden" name="idEstatus" id="idEstatus" dojoType="dijit.form.TextBox"/>--%>
							<%--<input type="hidden" name="Estatus" id="Estatus" dojoType="dijit.form.TextBox"/>--%>

						<div class="botones_medidor"
							 id="divOpciones" ${(idEstatus == idEstatusSeleccionado ||idEstatus == idEstatusSeleccionada || idEstatus == idEstatusProceso || idEstatus == idEstatusPostulado || idEstatus == idEstatusVinculado  ) ? '' : 'style="visibility:hidden; display:none"'}>
							<div style="margin: 15px auto;">
								<div class="form_nav">
									<div ${idEstatus == idEstatusVinculado || idEstatus == idEstatusProceso ? 'style="visibility:hidden; display:none"' : ""}>
										<input type="button" class="boton" name="btnContactar" id="btnContactar"
											   value="Contactar al candidato"
											   onclick="contactarCandidato();"/>
									</div>
								</div>
							</div>
						</div>
					</form>

					<form name="programarEntrevistaForm" id="programarEntrevistaForm"
						  action="viewCandidateInfo.do?method=init&idc=${idCandidato}&ido=${idOferta}" method="post"
						  dojoType="dijit.form.Form">
						<input type="hidden" name="method" value="cambiarEstatusOfertaCandidato" dojoType="dijit.form.TextBox"/>
						<input type="hidden" name="accion" value="${idAccionEntrevista}" dojoType="dijit.form.TextBox"/>
							<%--<input type="hidden" name="idEstatus" id="idEstatus" dojoType="dijit.form.TextBox"/>--%>
							<%--<input type="hidden" name="Estatus" id="Estatus" dojoType="dijit.form.TextBox"/>--%>
							<%--<input type="hidden" name="diaPost" id="diaPost"--%>
							<%--value="<% out.println(cal.get(Calendar.DAY_OF_MONTH));%>"--%>
							<%--dojoType="dijit.form.TextBox"/>--%>
							<%--<input type="hidden" name="mesPost" id="mesPost" value="<% out.println(cal.get(Calendar.MONTH) + 1);%>"--%>
							<%--dojoType="dijit.form.TextBox"/>--%>
							<%--<input type="hidden" name="anioPost" id="anioPost" value="<% out.println(cal.get(Calendar.YEAR));%>"--%>
							<%--dojoType="dijit.form.TextBox"/>--%>

						<div class="botones_medidor"
							 id="divOpciones" ${(idEstatus == idEstatusSeleccionado ||idEstatus == idEstatusSeleccionada || idEstatus == idEstatusProceso || idEstatus == idEstatusPostulado || idEstatus == idEstatusVinculado  ) ? '' : 'style="visibility:hidden; display:none"'}>
							<div style="margin: 15px auto;">
								<c:choose>
									<c:when test="${ofertaCandidatoVo.getEntrevista().getIdEntrevista() > 0}">
										<div class="entero">
											<strong>Ya ha sido programada una entrevista con el candidato para
												el d&iacute;a <%=ofertaCandidatoVo.getEntrevista().getFechaString()%> y
												hora <%=ofertaCandidatoVo.getEntrevista().getHora() %>.</strong>
										</div>
									</c:when>
									<c:otherwise>
										<c:if test="${idEstatus != idEstatusProceso}">
											<div class="form_nav">
												<input type="button" class="boton" name="btnEntrevista" id="btnEntrevista"
													   value="Programar entrevista en l&iacute;nea"
													   onclick="mostrarDatosEntrevista();"/>
											</div>
										</c:if>
										<div class="entero" id="divEntrevista"
											 style="display:none; visibility:hidden; position:relative; left:0px;">
											<strong>Fecha:</strong><br/>
											<input type="text" name="fechaEntrevista" id="fechaEntrevista"
												   dojoType="dijit.form.DateTextBox" required="false" size="10"
												   style="position:relative; left:0px;"/><br>
											<strong>Hora: <i>formato de 24 hrs, ejemplo (13:30)</i></strong><br>
											<input type="hidden" name="valorHora" id="valorHora" dojoType="dijit.form.TextBox"/>
											<input id="hora" name="hora" dojoType="dijit.form.ValidationTextBox"
												   required="false"
												   size="5"
												   regExp="^(0[1-9]|1\d|2[0-3]):([0-5]\d)$"
												   invalidMessage="Hora inv&aacute;lida, debe indicarse en formato de 24hrs, ejemplos 09:00 o 15:30"
												   uppercase="true" size="5" maxlength="5" value=""
												   style="position:relative; left:0px;" trim="true"/><br><br>
											<input type="button" class="boton" name="btnEnviar2" id="btnEnviar2"
												   value="Enviar Invitaci&oacute;n" onclick="validarEntrevista()"/>
											<input type="button" class="boton" name="btnCancelar" id="btnCancelar"
												   value="Cancelar"
												   onclick="ocultarDatosEntrevista()"/>
										</div>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
					</form>

				</c:if>

				<div>
					<a class="" href="javascript:window.history.go(-1)">Regresar</a>
				</div>

			</div>
		</div>
	</div>


	<!-- COMPANY  -->
	<form name="curriculum" action="miscandidatos.do" method="post" target="_blank">
		<input type="hidden" name="method" value="genera">
		<input type="hidden" name="idCandidato" value="<%=idCandidato%>">
	</form>

	<form name="botonesCandidatosForm" id="botonesCandidatosForm"
		  action="viewCandidateInfo.do?method=init&idc=${idCandidato}&ido=${idOferta}" method="post"
		  dojoType="dijit.form.Form">
		<input type="hidden" name="method" id="method" value="init" dojoType="dijit.form.TextBox"/>
		<input type="hidden" name="accion" id="accion" value="accion" dojoType="dijit.form.TextBox"/>
		<input type="hidden" name="idMotivo" id="idMotivo" dojoType="dijit.form.TextBox" />
		<input type="hidden" name="motivoDesc" id="motivoDesc" dojoType="dijit.form.TextBox" />
		<input type="hidden" name="idEstatus" id="idEstatus" dojoType="dijit.form.TextBox"/>
		<input type="hidden" name="Estatus" id="Estatus" dojoType="dijit.form.TextBox"/>
		<input type="hidden" name="fechaContrato" id="fechaContrato" value=""/>
		<input type="hidden" name="diaPost" id="diaPost"
			   value="<% out.println(cal.get(Calendar.DAY_OF_MONTH));%>"
			   dojoType="dijit.form.TextBox"/>
		<input type="hidden" name="mesPost" id="mesPost" value="<% out.println(cal.get(Calendar.MONTH) + 1);%>"
			   dojoType="dijit.form.TextBox"/>
		<input type="hidden" name="anioPost" id="anioPost" value="<% out.println(cal.get(Calendar.YEAR));%>"
			   dojoType="dijit.form.TextBox"/>

		<div class="porcentaje_c">
			<c:if test="${ofertaCandidatoVo.getIdOfertaEmpleo() > 0}">
				<h3><span>${detalle.nombre} ${detalle.apellido1} ${detalle.apellido2}</span></h3>

				<div class="porcentaje_c1">
					<em>Compatibilidad con la oferta de empleo:</em>
					<strong><%=fulfillment%> %</strong>
					<em class="puesto">
						<a href="detalleoferta.do?method=init&id_oferta_empleo=<%=ofertaCandidatoVo.getIdOfertaEmpleo()%>&idc=<%=idCandidato%>"><%=ofertaCandidatoVo.getTituloOferta()%>
						</a>
					</em>
				</div>
			</c:if>
			
			
			
		<div class="medidor_status">
			<div class="estatus_m" id="divOpcionesEstatusOferta" ${(idEstatus == idEstatusSeleccionado ||idEstatus == idEstatusSeleccionada || idEstatus == idEstatusProceso || idEstatus == idEstatusPostulado || idEstatus == idEstatusVinculado  ) ? '' : 'style="visibility:hidden; display:none"'}>
				<p class="estatus"><span>Estatus de aceptaci&oacute;n:</span><strong style="display: block; text-align: center">${estatus}</strong></p>
				<ul>
					<!-- li style="margin-right: 10px; margin-left: 10px" class="fl">
						<input type="radio" name="idBtnAccion" id="idBtnAccion1" value="${idEstatusProceso}"
						${idEstatus == idEstatusProceso ? 'checked="checked"' : '' } onclick="cambioOpcion(${idEstatusProceso});"
						${idEstatus == idEstatusProceso ? 'disabled="disabled"' : '' } />&nbsp;&nbsp;
						<label for="idBtnAccion1">En proceso</label>
					</li -->
					<li style="margin-right: 10px" class="fl">
						<input type="radio" name="idBtnAccion" id="idBtnAccion2" value="${idEstatusRechazo}"
						${idEstatus == idEstatusRechazo? 'checked="checked"' : '' } onclick="cambioOpcion(${idEstatusRechazo});"
								/>&nbsp;&nbsp;
						<label for="idBtnAccion2">No aceptar</label>
					</li>
					<li style="margin-right: 10px" class="fl">
						<input type="radio" name="idBtnAccion" id="idBtnAccion3" value="${idEstatusContrato}"
						${idEstatus == idEstatusContrato? 'checked="checked"' : '' } onclick="cambioOpcion(${idEstatusContrato});"
								/>&nbsp;&nbsp;
						<label for="idBtnAccion3">Contratar</label>
					</li>
				</ul>
				<div class="clearfix"></div>
				<ul class="razon_c">
					<!-- li><strong>En proceso:</strong>
					Este candidato está siendo evaluado para cubrir la oferta laboral.
					</li -->
					<li><strong>No aceptar:</strong>
					Este candidato no cumple con alguno de los requisitos de tu oferta laboral y no será contratado.</li>
					<li><strong>Contratar:</strong>
					Este candidato ha aprobado exitosamente tu proceso de selección y será contratado.
					</li>
				</ul>
				
				
				<div class="margin_top_20 motiv_c">
					<div id="divMotivos" style="visibility:hidden; display:none" >
						<div dojoType="dojo.data.ItemFileReadStore" jsId="motivoStore" urlPreventCache="true"
							 clearOnClose="true" url="${context}motivo.do?method=cargarMotivos"></div>
						<label for="idMotivoSelect">Motivo de no aceptaci&oacute;n: </label>
						<select dojotype="dijit.form.FilteringSelect" store="motivoStore" id="idMotivoSelect" value = "" required="false" onchange="verifyOption()"></select>
					</div>
					<div class="margin_top_20" id="otroMotivo" style="visibility:initial; display:<% 
								if (ofertaCandidatoVo.getIdMotivo() == 13) out.print("block");
								else out.print("none");
							%>">
						<label for="motivo">Especifica el motivo: </label>
						<textArea cols="40" rows="5" name="motivo" id="motivo" data-dojo-type="dijit/form/SimpleTextarea"
								required="false" trim="true" clearOnClose="true" invalidMessage="Caracter o longitud no validos." missingMessage="Debe capturar motivo de no aceptación."
								regExp="${regexpmotivo}" dojoType="dijit.form.ValidationTextBox" maxlength="150"></textArea>
					</div>
					<div id="divFechaContrato" style="visibility:hidden; display:none" >
						<label for="fechaContratoSelect">Fecha de contratación:</label>
						<input type="text" name="fechaContratoSelect" id="fechaContratoSelect" dojoType="dijit.form.DateTextBox" size="10" required="false" />
					</div>
					<div class="form_nav" id="divEnviar" style="display:none;" >
						<input type="button" class="boton" name="btnEnviar1" id="btnEnviar1" value="Enviar" onclick="validar()"/>
					</div>
				</div>
			</div>
		</div>
	</form>
			
			
			
	<div class="videoCV_c">
		<h3>Video Currículum</h3>
		<c:choose>
			<c:when test="${detalle.urlVideoEstatus == 1}">
				<p style="padding-left: 13px;"><a href="${detalle.urlVideoCV}" target="_blank">${detalle.urlVideoCV}</a></p>
			</c:when>
			<c:otherwise>
				<div class="gris margin_top_20" style="text-align: center">No disponible </div>
			</c:otherwise>
		</c:choose>
		<c:if test="${detalle.urlVideoEstatus == 1}">
			<iframe width="280" height="200" src="${decoratedVideo}" frameborder="0" allowfullscreen></iframe>
		</c:if>
	</div>
</div>








	<div class="clearfix"></div>
</div>

