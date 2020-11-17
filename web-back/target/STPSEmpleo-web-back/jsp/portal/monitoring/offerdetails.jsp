<%@ page import="mx.gob.stps.portal.web.infra.utils.Constantes"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="mx.gob.stps.portal.web.infra.utils.Utils, mx.gob.stps.portal.web.offer.wrapper.PerfilJB, 
	mx.gob.stps.portal.persistencia.vo.TelefonoVO, mx.gob.stps.portal.web.offer.form.OfferQuestionForm,java.util.ArrayList, mx.gob.stps.portal.web.infra.utils.Constantes,
	mx.gob.stps.portal.core.oferta.pregunta.vo.OfertaPreguntaVO, mx.gob.stps.portal.web.infra.utils.PropertiesLoader, mx.gob.stps.portal.web.offer.wrapper.OfertaJB,
	mx.gob.stps.portal.web.infra.utils.Utils, mx.gob.stps.portal.web.candidate.vo.CandidatoAjaxVO"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	CandidatoAjaxVO candidato= (CandidatoAjaxVO)request.getSession().getAttribute("candidatoheader");
%>
<%
	int idTipoUsuarioEmpresa = Constantes.ID_TIPO_USUARIO_EMPRESA;
	pageContext.setAttribute("idTipoUsuarioEmpresa",idTipoUsuarioEmpresa);	
	int OFERTA_FUNCION_PUBLICA = 2;
	int compatibilityLimit = PropertiesLoader.getInstance().getPropertyInt("compatibility.upper.limit");
	String strRegistroCandidato = PropertiesLoader.getInstance().getProperty("app.swb.redirect.liga.registro.candidato");
	OfferQuestionForm offer = (OfferQuestionForm)request.getSession().getAttribute("OfferQuestionForm");
	OfertaJB employ = offer.getOfertaJB();
	String context = request.getContextPath() +"/";
	String invoke = (null != request.getSession().getAttribute("_urlpageinvoke") ? (String)request.getSession().getAttribute("_urlpageinvoke") : "javascript:history.go(-1)");
	String reference = "showMoreCompanyOffers.do?method=init&idEmpresa=" + employ.getIdEmpresa();
	String strHorarioLaboral="";
	String strHoraEntrada=employ.getHoraEntrada();
	String strHoraSalida=employ.getHoraSalida();
	if (null!=strHoraEntrada && !strHoraEntrada.equalsIgnoreCase(""))
		strHorarioLaboral = strHorarioLaboral + strHoraEntrada + ":00";
	if (null!=strHoraSalida && !strHoraSalida.equalsIgnoreCase(""))
		strHorarioLaboral = strHorarioLaboral + " a " + strHoraSalida + ":00";
	String idInactivoAPeticionDelUsuario = (null!= request.getSession().getAttribute("idInactivoAPeticionDelUsuario") ? request.getSession().getAttribute("idInactivoAPeticionDelUsuario").toString() : "");
	String idInactivoPorVigencia = (null!= request.getSession().getAttribute("idInactivoPorVigencia") ? request.getSession().getAttribute("idInactivoPorVigencia").toString() : "");
	String idInactivoPorAdmor = (null!= request.getSession().getAttribute("idInactivoPorAdmor") ? request.getSession().getAttribute("idInactivoPorAdmor").toString() : "");
	String sessionPostOferRec = (null != request.getSession().getAttribute("POST_OFFER_REC") ? (String)request.getSession().getAttribute("POST_OFFER_REC") : "");
	String strRegistroEmpresa = PropertiesLoader.getInstance().getProperty("app.swb.redirect.registro.empresa.datos");
	boolean inscritoPPC = (null!=candidato && (candidato.getPpcEstatusIdOpcion().equals("59") || candidato.getPpcEstatusIdOpcion().equals("60"))) ? true:false;
	boolean correoElectronico  = (null!=offer.getPerfilJB() && offer.getPerfilJB().getCorreoElectronico()!=null) ? true:false;
	boolean trabajaActualmente = (null!=offer.getPerfilJB() && offer.getPerfilJB().getIdTrabaja()==1) ? true:false;
	boolean isChanneling = offer.isChanneling();
	boolean checkChanneling = offer.isCheckChanneling();
%>
<c:set var="invoke" value="<%=invoke%>" />
<c:set var="urldefaultpage" value="${_urldefaultpage}" />
<c:set var="postuladoOK" value="${postuladoOK}" />
<c:set var="OFERTA_FUNCION_PUBLICA" value="<%=OFERTA_FUNCION_PUBLICA%>" />
<c:set var="idInactivoAPeticionDelUsuario" value="<%=idInactivoAPeticionDelUsuario%>" />
<c:set var="idInactivoPorVigencia" value="<%=idInactivoPorVigencia%>" />
<c:set var="idInactivoPorAdmor" value="<%=idInactivoPorAdmor%>" />
<c:set var="UsuarioAPP" value="${USUARIO_APP}" />
<c:set var="inscritoPPC" value="<%=inscritoPPC%>" />
<c:set var="correoElectronico" value="<%=correoElectronico%>" />
<c:set var="trabajaActualmente" value="<%=trabajaActualmente%>" />
<c:set var="isChanneling" value="<%=isChanneling%>" />
<c:set var="checkChanneling" value="<%=checkChanneling%>" />
<c:set var="sessionPostOferRec" value="<%=sessionPostOferRec%>" />
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js"></script>
<script type="text/javascript" src="http://maps.google.com/maps/api/js?v=3&sensor=false&libraries=places" ></script>
<script type="text/javascript" src="js/helper/messageHelper.js"></script>
<script src="dojotoolkit/dojo/dojo.js" djConfig="parseOnLoad: true, locale:'es'"></script>

<script type="text/javascript">
	dojo.require("dijit.dijit"); // loads the optimized dijit layer
	dojo.require("dijit.form.Form");
	dojo.require("dijit.Dialog");
	dojo.require("dijit.form.TextBox");

	var dialogAviso;

	function doAjax(method) {
		dojo.byId('method').value = method;
		dojo.byId('send').disabled = true;
		dojo.xhrPost({
			url : 'detalleoferta.do',
			form : 'form',
			timeout : 180000, // 3 min
			load : function(data) {
				var res = dojo.fromJson(data);
				dojo.byId('message').innerHTML = res.message;
			}
		});
	}
	function doAjaxPost(method, idCandidato, idOfertaEmpleo) {
		dojo.byId('method').value = method;
		dojo.byId('idCandidato').value = idCandidato;
		dojo.byId('idOfertaEmpleo').value = idOfertaEmpleo;
		//dojo.byId('agregar_miEspacio').disabled=true;
		dojo.xhrPost({
			url : 'detalleoferta.do?method=' + method + '&idCandidato='
					+ idCandidato + '&idOfertaEmpleo=' + idOfertaEmpleo,
			//form:'formPost',
			timeout : 180000, // 3 min
			load : function(data) {
				var res = dojo.fromJson(data);
				alert(res[1].message);
				dojo.byId('sendPost').innerHTML = res[0].message;
			}
		});
	}

	function doAjaxQuestion(method, idCandidato, idOfertaEmpleo) {

		if (!dojo.byId('pregunta').value
				|| dojo.string.trim(dojo.byId('pregunta').value) == '') {
			dojo.byId('pregunta').focus();
			mensaje('Favor de indicar la pregunta a realizar');
			return;
		}

		dojo.byId('method').value = method;
		dojo.byId('idCandidato').value = idCandidato;
		dojo.byId('idOfertaEmpleo').value = idOfertaEmpleo;
		dojo.byId('sendQuestion').disabled = true;
		dojo.xhrPost({
			url : 'detalleoferta.do',
			form : 'formQuestion',
			timeout : 180000, // 3 min
			load : function(data) {
				var res = dojo.fromJson(data);
				//dojo.byId('messageQuestion').innerHTML = res.message;
				message(res.message);
			}
		});
	}

	function mensaje(mensaje) {
		alert(mensaje);
		//var vMensaje = new dijit.Dialog({draggable: false, title: 'Notificación', content: mensaje, style: "width: 300px"});
		//vMensaje.show();

	}

	function redirectSWB(url) {
		window.top.location = url;
	}

	dojo.addOnLoad(function() {
		// if(${not empty USUARIO_APP})
		cargaIframeMasOfertas();
	});

	function cargaIframeMasOfertas() {
		dojo.xhrPost({
			url : 'ocupate.do?method=masOfertasDeEmpleo&id_oferta_empleo='
					+ "${id_oferta_empleo}",
			timeout : 180000,
			load : function(data) {
				dojo.byId('masOfertas').innerHTML = data;
			}
		});
	}

	function recuperaPsw() {
		document.formLogin.action = '${context}detalleoferta.do?method=toRecuperaContrasena';
		document.formLogin.submit();

	}

	/*
	function avisoCandidatoPostulado(){		  
	  postuladoOK = "${postuladoOK}";
	  if (postuladoOK) {
			dialogAviso = new dijit.Dialog({
		        title: 'Mensaje',
		        href: '${context}detalleoferta.do?method=avisoCandidatoPostuladoAction',			        		
		        style: 'width:500px; height:375px; background: #FFF',
		        showTitle: false, draggable : false, closable : true
		    });
			dialogAviso.show();
	  }		  
	}
	
	function cierraAvisoCandidatoPostulado(){
	  dialogAviso.hide(); 
	}*/

	function postularmeNoSesion() {
		window.top.scrollTo(0, 250);
		dojo.byId('username').focus();
		alert('Para postularse a la oferta es preciso registrarse o iniciar sesión.');
	}

	function redireccionar(home) {
		window.top.location.href = home;
	}
	
	function validaPostularOferta(usuario) {
		var inscritoPPC = ${inscritoPPC};
		var trabajaActualmente = ${trabajaActualmente};
		var correoElectronico = ${correoElectronico};
		var isChanneling = ${isChanneling};
		var checkChanneling = ${checkChanneling};
		var sessionOfertaReciente = '<%=sessionPostOferRec%>';
		
		if (null == usuario || usuario == "") {
			dialogLoginPopup(); //dialogo para logearse
		}
		if (null!=usuario && !usuario=="") {
			if ((inscritoPPC && !correoElectronico) || (!inscritoPPC && !correoElectronico)) {
				dojo.addOnLoad(inic);  //dialogo correo electronico			
			}
			if (inscritoPPC && trabajaActualmente){
				dojo.addOnLoad(init);	//dialogo trabaja actualmente
			}
// 			if (!isChanneling) {
// 				dojo.addOnLoad(msgNotChanneling);
// 			}
			if ((inscritoPPC && correoElectronico && !trabajaActualmente) || (!inscritoPPC && correoElectronico && isChanneling)) {
				dojo.addOnLoad(conf);	//dialogo confirmacion			
			}
		}
	}

	
	function postularOferta(){
		document.opost.action = '${context}detalleoferta.do?method=offerPost';
		document.opost.submit();
	}
	
	function closeDialog1(){dijit.byId('dialogoCorreo').hide();}
	function closeDialog2(){dijit.byId('dialogoTrabaja').hide();} 
	function closeDialog3(){dijit.byId('dialogoConfirmacion').hide();}
	function closeChanneling(){dijit.byId('dialogChanneling').hide();}
	function closeCheckChanneling(){dijit.byId('dialogCheckChanneling').hide();}
	
	var conf = function() {dijit.byId('dialogoConfirmacion').show();}
	var inic = function() {dijit.byId('dialogoCorreo').show();}
	var init = function() {dijit.byId('dialogoTrabaja').show();}
	var msgNotChanneling = function() {dijit.byId('dialogChanneling').show();}
	var msgCheckChanneling = function() {dijit.byId('dialogCheckChanneling').show();}
	
	function maximaLongitud(texto, maxlong) {
		var tecla, int_value, out_value;
		if (texto.value.length > maxlong) {
			/*con estas 3 sentencias se consigue que el texto se reduzca
			al tamaño maximo permitido, sustituyendo lo que se haya
			introducido, por los primeros caracteres hasta dicho limite*/
			in_value = texto.value.replace(/^\s+|\s*[\r\n][\r\n \t]*/g, "");
			out_value = in_value.substring(0, maxlong);
			texto.value = out_value;
			return false;
		}
		return true;
	}
	
	function closeIsNoLongerActive(path) {
		dijit.byId('dialogIsNoLongerActive').hide();
		location.replace(path);
	}
	<%--
	$(function(){
		<%if(employ.getGeoReferencia()!=null){%>
			$('#datosUbicacion').show();
			var lat = <%=employ.getGeoReferencia().getJavaPoint().getX()%>
			var lng = <%=employ.getGeoReferencia().getJavaPoint().getY()%>
			var location = new google.maps.LatLng(lat, lng);
			var map = new google.maps.Map(document.getElementById('map'), 
				{   zoom: 17, 
					center: {lat: lat, lng: lng},
					mapTypeId: google.maps.MapTypeId.ROADMAP });	
			var marker = new google.maps.Marker({
				position: location, 
				map: map
			});			
		<%}%>
	});--%>
</script>

<noscript>Funciones de JavaScript desactivadas por navegador</noscript>

<%-- COMENTAR EN PRODUCCION --%>
<c:if
	test="${not empty idInactivoPorAdmor and idInactivoPorAdmor ne ''}">
	<script type="text/javascript">
		dojo.addOnLoad(function() {
					var vMensajeInactivo = 'Se detectó un problema con su registro, para ingresar nuevamente acuda a las oficinas del SNE más cercanas';
					alert(vMensajeInactivo);
				});
	</script>
</c:if>

<c:if test="${not empty urldefaultpage}">
	<script type="text/javascript">
		dojo.addOnLoad(function() {
					dijit.byId('dialogIsNoLongerActive').show();
				});
	</script>
	<div dojoType="dijit.Dialog" id="dialogIsNoLongerActive" title="Oferta Inactiva" style="display: none;" closable="false" draggable="false">
		<div class="msg_contain">
			<p id="errorIsNoLongerActive">
				Se detectó que la oferta seleccionada no está más activa.
			</p>	
			<p class="form_nav">				
				<button class="button" onclick="closeIsNoLongerActive('${urldefaultpage}');">Aceptar</button>
			</p>
		</div>		
	</div>
</c:if>

<c:if test="${not empty errors}">
	<script type="text/javascript">
		dojo.addOnLoad(function() {
			mensaje('${errors}');
		});
	</script>
</c:if>

<c:if test="${not empty ERROR_MSG}">
	<script type="text/javascript">
		dojo.addOnLoad(function() {
			dialogLoginPopup();
		});
	</script>
</c:if>
<div class="row">

	<div class="col-sm-12">
		<div class="page-header">
			<h3>Detalle de la oferta <small><%=employ.getTituloOferta()%> - <%=employ.getEmpresaNombre()%></small></h3>
		</div>
	</div>
	
	<div class="clearfix"></div>
	
	<div class="col-md-4 col-md-push-8">
	<% if (null != request.getSession().getAttribute("_user") && OFERTA_FUNCION_PUBLICA != employ.getFuenteId() && offer.getPerfilJB() != null) {
		PerfilJB vo = offer.getPerfilJB();
		TelefonoVO tel = vo.getPrincipal(); %> 
		<div class="row">
			<div class="col-sm-12">
				<div class="panel panel-compatibilidad">
				  <div class="panel-heading">
				    <h3 class="panel-title">
				    	<% out.print(vo.getNombre() + " " + vo.getApellido1() + " " + vo.getApellido2()); %>
					</h3>
				  </div>
				  <div class="panel-body">
				    <p><em>Tu porcentaje de compatibilidad con esta oferta de empleo es del</em></p>
					
					<%
						String classProgress = "progress-bar-";
						int compatibilidad = offer.getCompatibility();
						if(compatibilidad > 0 && compatibilidad <= 25){
							classProgress+="warning";
						}else{
							if(compatibilidad <= 75){
								classProgress+="info";
							}else{
								classProgress+="success";
							}
						}
					%>
					
					<div class="progress progresCompatibilidad">
					  <div class="progress-bar <%=classProgress%> progress-bar-striped" role="progressbar" aria-valuenow="${OfferQuestionForm.compatibility}" 
					  	aria-valuemin="0" aria-valuemax="100" style="width: ${OfferQuestionForm.compatibility}%;">
						<strong id="porcentaje"><%=compatibilidad%>%</strong>
					  </div>
					</div>
					<c:if test="${OfferQuestionForm.ofertaJB.estatusOfertaCandidato eq '0'}">
						<div class="form-group text-center">
							<a class="btn btnCompatibilidad btn-sm" onclick="doAjaxPost('addToMyOffers', '${OfferQuestionForm.idCandidato}','${OfferQuestionForm.idOfertaEmpleo}');"
								href="#">Agregar a mis ofertas</a>
						</div>
					</c:if>
				  </div>
				  <div class="panel-footer text-center">
					<p>Para mejorar tu compatibilidad, te sugerimos</p>
					<a class="hidden-xs" href="<%=response.encodeURL(request.getContextPath() +"/registro-unico.do?method=redirectEditaCandidatoRU")%>"> Actualizar tu perfil laboral </a>
				  </div>
				</div>
				
				<div class="progress progresCompatibilidad1">
<div class="progress-bar progress-bar-warning progress-bar-striped" role="progressbar" aria-valuenow="44" aria-valuemin="100" aria-valuemax="100" style="width: 100%;">                   
           
             <div class="panel-footer text-center">
       <p style="color:black; font-family:Helvetica; font-size:14px;  " align="justify"><b>Si la empresa le pide invertir dinero, para el pago de exámenes, compra de uniformes o compra de cualquier artículo, por favor haga caso omiso y denuncie a la empresa a través de la sección de 
   <br>                                         &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="https://www.empleo.gob.mx/sugerencias" style="color:blue;">Quejas y Sugerencias</a>
</b></p>										

                    </div>
               </div>
               </div>
			</div>
		</div>
	<%}%>
	</div>
	
	
	
	
	<div class="col-md-8 col-md-pull-4">
		<div class="row">
				
		  	<div class="col-sm-3">
				<div class="form-group text-center">
					<img src="<%=context%>imageAction.do?method=getImagen&ID_EMPRESA=<%=employ.getIdEmpresa()%>"
						alt="<%=employ.getEmpresaNombre()%>" class="img-responsive" style="margin:auto;"/>
				</div>
			</div>
			<div class="col-sm-9">
				<div class="row">
					<div class="col-sm-6">
						<div class="panel panelOfferDetalle">
	  						<div class="panel-body">
								<strong>Salario neto mensual:</strong><br>
								<span><%=Utils.formatMoney(employ.getSalario())%></span>
							</div>
						</div>
					</div>
					<div class="col-sm-6">
						<div class="panel panelOfferDetalle">
	  						<div class="panel-body">
								<strong>Ubicación:</strong><br>
								<span><%=employ.getUbicacion()%></span> - 
				
								<%if (null != request.getSession().getAttribute("_user") && !"".equals(employ.getMapaUbicacion())) {%>
									<a href="javascript:void(0)" onclick="window.open('<%=employ.getMapaUbicacion()%>','mapa','height=600,width=800,menubar=no,location=no,toolbar=no,scrollbars=no, resizable=yes');">Ver mapa de ubicación</a>
								<%}%>
							</div>
						</div>
					</div>
					<div class="col-sm-6">
						<div class="panel panelOfferDetalle">
	  						<div class="panel-body">
								<strong>Tipo de contrato:</strong><br>
								<span><%=employ.getTipoContrato()%></span>
							</div>
						</div>
					</div>
					<div class="col-sm-6">
						<div class="panel panelOfferDetalle">
	  						<div class="panel-body">
								<strong>Vigencia de la oferta:</strong><br>
								<span><%=employ.getFechaFin()%></span>
							</div>
						</div>
					</div>
					<div class="col-sm-6">
						<div class="panel panelOfferDetalle">
	  						<div class="panel-body">
								<strong>Días laborales:</strong><br>
								<span><%=employ.getDiasLaborales()%></span>
							</div>
						</div>
					</div>
					<div class="col-sm-6">
						<div class="panel panelOfferDetalle">
	  						<div class="panel-body">
								<strong>Horario de trabajo:</strong><br>
								<span><%=strHorarioLaboral%> hrs.</span>
							</div>
						</div>
					</div>
					 <%if (!("").equals(employ.getRolarTurno())) {%>
					 	<div class="col-sm-6">
					 		<div class="panel panelOfferDetalle">
		  						<div class="panel-body">
									<strong>Rolar turnos:</strong><br>
									<span><%=employ.getRolarTurno()%></span>
								</div>
							</div>
						</div>
					<%}%>
				</div>
			</div>
				
			
			
			<div class="col-sm-12">
				<div class="page-header">
					<h4>Requisitos</h4>
				</div>
				
				<div class="panel panelOfferOdd">
				  <div class="panel-body">
				    <strong>Estudios Solicitados:</strong>
					<div class="especificos">
						<%=employ.getGradoEstudios()%>
						<% if (!("").equals(employ.getEspecialidades())) {%>
							<span> en </span><%=employ.getEspecialidades()%>
						<%}%>
						<% if (!("").equals(employ.getSituacionAcademica())) {%>
							<span>, </span><%=employ.getSituacionAcademica()%>
						<%}%>							
					</div>
				  </div>
				</div>
				
			  	<% if (!("").equals(employ.getExperienciaAnios()) && !("Ninguna").equalsIgnoreCase(employ.getExperienciaAnios())) {%>
					<div class="panel panelOffer">
					  <div class="panel-body">
						<strong>Experiencia:</strong>
						<div class="especificos"><%=employ.getExperienciaAnios()%></div>
					  </div>
					</div>
				<%}%>
				
				<div class="panel panelOfferOdd">
				  <div class="panel-body">
					<strong>Competencias transversales:</strong><br>
					<c:if test="${empty OfferQuestionForm.ofertaJB.habilidades && not OfferQuestionForm.ofertaJB.habilidadGeneral == ''}">
						<c:out value="${OfferQuestionForm.ofertaJB.habilidadGeneral}" />
					</c:if>
					
					<c:if test="${not empty OfferQuestionForm.ofertaJB.habilidades}">
						<c:forEach var="habilidad" items="${OfferQuestionForm.ofertaJB.habilidades}" varStatus="indexHab">
						${habilidad.opcion}<c:if test="${indexHab.count < fn:length(OfferQuestionForm.ofertaJB.habilidades)}">,</c:if>
						</c:forEach>
					</c:if>
					<c:if test="${empty OfferQuestionForm.ofertaJB.habilidades && OfferQuestionForm.ofertaJB.habilidadGeneral == ''}">	
						<span>No es requisito</span>
					</c:if>				
				  </div>
				</div>
				
				<!-- div class="panel panelOffer">
				  <div class="panel-body">
					<strong>Conocimientos en computación:</strong><br>
					<div class="especificos"><%=employ.getConocimientoComputacionDesc()%></div>
				  </div>
				</div -->
				
			  	<% if (!("").equals(employ.getIdiomasCert())) {%>
					<div class="panel panelOfferOdd">
					  <div class="panel-body">
						<strong>Idiomas:</strong>
						<div class="especificos"><%=employ.getIdiomasCert()%></div>
					  </div>
					</div>
				<%}%>
				
			  	<%if (!("").equals(employ.getDisponibilidadViajar())) {%>
					<div class="panel panelOffer">
					  <div class="panel-body">
						<strong><%=employ.getDisponibilidadViajar()%></strong>
					  </div>
					</div>
				<%}%>
				
			  	<%if (!("").equals(employ.getDisponibilidadRadicar())) {%>
					<div class="panel panelOfferOdd">
					  <div class="panel-body">
						<strong><%=employ.getDisponibilidadRadicar()%></strong>
					  </div>
					</div>
				<%}%>
			</div>
			
			<div class="col-sm-12">
				<div class="page-header">
					<h4>La empresa ofrece</h4>
				</div>
				
			  	<% if (!("").equals(employ.getPrestaciones()) && !("Ninguna").equalsIgnoreCase(employ.getPrestaciones())) {%>
					<div class="panel panelOfferOdd">
					  <div class="panel-body">
						<strong>Prestaciones:</strong>
						<div class="especificos"><%=employ.getPrestaciones()%></div>
					  </div>
					</div>
				<%}%>
				
				<div class="panel panelOffer">
				  <div class="panel-body">
				  	<strong>Número de plazas:</strong><br>
					<div class="especificos"><%=employ.getNumeroPlazas()%></div>
				  </div>
				</div>
			</div>
			
			<div class="col-sm-12">
				<div class="page-header">
					<h4>Funciones y actividades a realizar</h4>
				</div>
				
				<div class="panel panelOfferOdd">
				  <div class="panel-body">
				  	<%=employ.getFunciones()%>
				  </div>
				</div>
			</div>
			
			<div class="col-sm-12">
				<div class="page-header">
					<h4>Observaciones</h4>
				</div>
				
				<div class="panel panelOfferOdd">
				  <div class="panel-body">
				  	<%=employ.getObservaciones()%>
				  </div>
				</div>
				
				<div id="datosUbicacion" class="panel panelOffer" style="display: none;">
				  <div class="panel-body">
				  	<h3>Ubicación</h3>
					<div id="map" style="width: 98%; height: 400px;"></div>
				  </div>
				</div>

			</div>
			
			
			<% if (null != request.getSession().getAttribute("_user")) {%>
			<div id="contenido_principal" class="col-sm-12">
				<div id="preguntas_relacionadas">
					<% ArrayList<OfertaPreguntaVO> preguntas = (ArrayList<OfertaPreguntaVO>)offer.getOfertaPreguntasList();
						if (preguntas.size() > 0){%>
					<div class="page-header">
						<h4>Preguntas recientes acerca de la oferta de empleo</h4>
					</div>
					<%}
						for (int i=0; i<preguntas.size(); i++) {
							  OfertaPreguntaVO vo = preguntas.get(i);
					%>
					<blockquote>
						<p>Candidato</p>
						<footer> <% out.println(Utils.capitalize(vo.getPregunta())); %></footer>
					</blockquote>
					<blockquote class="blockquote-reverse">
						<p>Empresa</p>
						<footer><% out.println(Utils.capitalize(vo.getRespuesta())); %></footer>
					</blockquote>
					
					<%}
						if (null != request.getSession().getAttribute("_user")) {%>
					<div class="redacta">
						<div id="messageQuestion"></div>
						<form method="post" action="detalleoferta.do" name="formQuestion" id="formQuestion">
							<input type="hidden" name="idCandidato" id="idCandidato" value="<%=offer.getIdCandidato()%>" />
							<input type="hidden"name="idOfertaEmpleo" id="idOfertaEmpleo"value="<%=offer.getIdOfertaEmpleo()%>" />
							<div class="panel panelOfferOdd">
				  				<div class="panel-body">
								<div class="form-group">
									<input type="hidden" name="method" id="method"value="addQuestion" />
									<label>Preguntale a la empresa:</label>
									<textarea name="pregunta" id="pregunta" cols="50" rows="7" trim="true" onchange="return maximaLongitud(this,2000)"
										onKeyUp="return maximaLongitud(this,2000)" maxlength="2000" class="form-control"></textarea>
								</div>
								<div class="form-group text-center">
									<input type="button" id="sendQuestion" value="Enviar pregunta" class="btnPE"
										onclick="doAjaxQuestion('addQuestion','<%=offer.getIdCandidato()%>','<%=offer.getIdOfertaEmpleo()%>');" />
								</div>
							</div>
						</form>
					</div>
					<%}%>
				</div>
			</div>
			<%}%>
			
			<div class="col-sm-12">
				<div class="form-group text-center">
					<c:if test="${not USUARIO_APP.empresa}">
						<% if (/*offer.getCompatibility() > compatibilityLimit &&  null != request.getSession().getAttribute("_user") &&*/ !offer.isPostulated()) {%>
							<input type="submit" class="btnPE" id="postularOferta" onclick="javascript:validaPostularOferta('${USUARIO_APP}');" value="Postularme a esta oferta" />
							<form name="formPost" id="formPost" method="post" action="detalleoferta.do">
								<input type="hidden" name="method" id="method" value="addToMyOffers" />
							    <input type="hidden" name="idCandidato" id="idCandidato" value="" />
							    <input type="hidden" name="idOfertaEmpleo" id="idOfertaEmpleo" value="" />
							</form>
			
							<form name="opost" id="opost" method="post" action="detalleoferta.do">
								<input type="hidden" name="method" id="method" value="offerPost" />
							</form>
						<%} %>
					</c:if>
				</div>
				<div class="form-group text-center">
					<c:choose>
						<c:when test="${not empty _urlpageinvoke}">
							<c:url var="url" value="${_urlpageinvoke}" />
							<p><strong><a href="${url}">Regresar</a></strong></p>
						</c:when>
						<c:otherwise>
							<p><strong><a href="javascript:history.go(-1)">Regresar</a></strong></p>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
			
			
		</div>	
	</div>
	
	<div class="clearfix"></div>
	


	
	
		

		
	


		

		
		
		

	<div dojoType="dijit.Dialog" id="dialogoCorreo" title="Aviso" style="display: none; width:400px" draggable="false">
			<div class="msg_contain twolines">
						<!--INI_JGLC_PPC-->
<!-- 						<p>Se identificó que te encuentras inscrito al Programa de Promoción y Colocación del Seguro de Desempleo PPC-SD, -->
<!-- 							 para poder postularte debes proporcionar una cuenta de correo electrónico -->
<!-- 						</p> -->
						<!--FIN_JGLC_PPC-->
					<p class="form_nav">
						<p>
							Para poder postularte debes proporcionar una cuenta de correo electrónico
 						</p>	
						<a href="<c:url value="/registro-unico.do?method=redirectEditaCandidatoRU"/>"> Actualizar tu perfil laboral </a><br><br>
						<input type="button" class="boton" value="Cerrar" onclick="closeDialog1();">
					</p>
			</div>	
	</div>
	
	<div dojoType="dijit.Dialog" id="dialogoTrabaja" title="Aviso" style="display: none; width:400px" draggable="false" class="suelto">
			<div class="msg_contain twolines">
						<!--INI_JGLC_PPC-->
<!-- 						<p>Se identificó que te encuentras inscrito al Programa de Promoción y Colocación del Seguro de Desempleo (PPC-SD), -->
<!-- 							 para poder postularte y cumplir con el programa actualiza el dato ¿Trabajas actualmente?. -->
<!-- 						</p> -->
						<!--FIN_JGLC_PPC-->
					<p class="form_nav">	
						<a href="<c:url value="/perfil.do?method=init"/>"> Actualizar tu perfil laboral </a><br><br>
						<input type="button" class="boton" value="Cerrar" onclick="closeDialog2();">
					</p>
			</div>	
	</div>	
	
	<div dojoType="dijit.Dialog" id="dialogoConfirmacion" title="Confirmación de la Postulación" style="display: none; width:400px" draggable="false">
			<div class="msg_contain">
						<p>Al postularte, el Portal del Empleo enviará información de tu perfil a la<br>
empresa que realiza la oferta. La empresa te notificará vía correo<br>
electrónico si eres candidato(a) para la oferta de empleo seleccionada.
						<br><br>
						<strong>¿Deseas continuar con el proceso de postulación?</strong></p>
					
					<p class="form_nav 2">
						<input type="button" class="boton" value="Aceptar" onclick="javascript:postularOferta();">				
						<input type="button" class="boton" value="Cancelar" onclick="closeDialog3();">
					</p>
			</div>		
	</div>
	
	<div dojoType="dijit.Dialog" id="dialogChanneling" title="No Proceso de canalización" style="display: none; width:400px" draggable="false">
		<div class="msg_contain">
			<p>
				Se detectó que no ha realizado el proceso de canalización, favor de acudir a la oficina del SNE más cercana para realizar este trámite.
			</p>	
			<p class="form_nav 2">				
				<input type="button" class="boton" value="Cerrar" onclick="closeChanneling();">
			</p>
		</div>		
	</div>
	
	<div dojoType="dijit.Dialog" id="dialogCheckChanneling" title="Verificar Proceso de canalización" style="display: none; width:400px" draggable="false">
		<div class="msg_contain">
			<p>
				Se detectó que está canalizado a otro programa, favor de acudir a la oficina del SNE más cercana para verificar proceso de canalización.
			</p>	
			<p class="form_nav 2">				
				<input type="button" class="boton" value="Cerrar" onclick="closeCheckChanneling();">
			</p>
		</div>		
	</div>
		
</div>

<% if (null != request.getSession().getAttribute("_user") && OFERTA_FUNCION_PUBLICA != employ.getFuenteId() && offer.getPerfilJB() != null) {
		PerfilJB vo = offer.getPerfilJB();
		TelefonoVO tel = vo.getPrincipal(); %> 
	</div>	
<%} %>