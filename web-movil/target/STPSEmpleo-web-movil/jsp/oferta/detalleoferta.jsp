<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="mx.gob.stps.portal.movil.web.infra.utils.Utils"%>
<%@ page import="mx.gob.stps.portal.core.infra.utils.Constantes"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
	<jsp:include page="../seguridad/header.jsp"/>

	<script type="text/javascript">

	function message(msg){
		alert(msg);
	}
	
	function preguntasOfertas(){
		document.detalleOfertaForm.method.value="preguntasOferta";
		document.detalleOfertaForm.submit();
	}

	function postularse(idOfertaEmpleo) {
		document.postulacionForm.idOfertaEmpleo.value=idOfertaEmpleo;
		document.postulacionForm.submit();
  	}

	</script>

	<script type="text/javascript">	
	 	dojo.require("dojox.mobile.parser"); // Load the widget parser
	 	dojo.require("dojox.mobile"); // Load the base lib
	 	dojo.require("dojox.mobile.TabBar");
	 	dojo.require("dojox.mobile.TabContainer");
	 	dojo.requireIf(!dojo.isWebKit, "dojox.mobile.compat"); // If not a WebKit-based client, load compat
		dojo.require("dijit.form.Form");
		dojo.require("dojox.mobile.TabBar");
		dojo.require("dojox.mobile.compat");		
	</script>

<script type="text/javascript">

  var _gaq = _gaq || [];
  _gaq.push(['_setAccount', 'UA-36829222-1']);
  _gaq.push(['_setDomainName', 'empleo.gob.mx']);
  _gaq.push(['_setAllowLinker', true]);
  _gaq.push(['_trackPageview']);

  (function() {
    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
  })();

</script>

</head>

<body>

	<form id="detalleOfertaForm" name="detalleOfertaForm" method="post" action="detalleoferta.m" >
	
		<input type="hidden" id="idOfertaEmpleo" name="idOfertaEmpleo" value="${ofertaForm.offer.idOfertaEmpleo}">
		<input type="hidden" id="idEmpresa" name="idEmpresa" value="${ofertaForm.offer.idEmpresa}">
		<input type="hidden" id="empresaNombre" name="empresaNombre" value="${ofertaForm.offer.empresaNombre}">
		<input type="hidden" id="tituloOferta" name="tituloOferta" value="${ofertaForm.offer.tituloOferta}">
		<input type="hidden" id="tipoContrato" name="tipoContrato" value="${ofertaForm.offer.tipoContrato}">
		<input type="hidden" id="horarioLaboral" name="horarioLaboral" value="${ofertaForm.offer.horaEntrada} a ${ofertaForm.offer.horaSalida}">
		<input type="hidden" id="method" name="method" value="init">
	</form>

	<form id="postulacionForm" name="postulacionForm" method="post" action="postular.m" >
		<input type="hidden" name="idOfertaEmpleo" id="idOfertaEmpleo" value="" />
	</form>

		<div id="miespaciocand" dojoType="dojox.mobile.View" selected="true">

			<c:if test="${empty USERLOGGED}">
				<h1 dojoType="dojox.mobile.Heading">
					<div dojoType="dojox.mobile.ToolBarButton" icon="images/ico_rwd.jpg" moveTo="bar" href="home.m" transitionDir="-1"></div>
				</h1>
			</c:if>
			<c:if test="${not empty USERLOGGED}">
				<c:if test="${USERLOGGED.empresa}">
					<h1 dojoType="dojox.mobile.Heading">
					<div dojoType="dojox.mobile.ToolBarButton" icon="images/ico_rwd.jpg" moveTo="bar" href="ofertasEmpresa.m?method=init" transitionDir="-1"></div>
						<div dojoType="dojox.mobile.ToolBarButton" icon="images/ico_exit.jpg" style="width:45px;float:right;" href="logout.m"></div>
					</h1>
				</c:if>
				<c:if test="${USERLOGGED.candidato}">
					<h1 dojoType="dojox.mobile.Heading">
						<div dojoType="dojox.mobile.ToolBarButton" icon="images/ico_rwd.jpg" moveTo="bar" href="candidatoEspacio.m?method=init" transitionDir="-1"></div>
						<div dojoType="dojox.mobile.ToolBarButton" icon="images/ico_exit.jpg" style="width:45px;float:right;" href="logout.m"></div>
					</h1>
				</c:if>
			</c:if>
			<div dojoType="dojox.mobile.RoundRect" >
				<div style="text-align: center;" class="titNar">Detalle de Oferta</div>
			
				<div class="mblVariableHeight" style="font-size: 10px;">
					<table cellpadding="0" cellspacing="0">
						<tr>
						<td class="imgDetalle"><img src="imageAction.m?method=getLogotipoEmpresaRequest&ID_EMPRESA=${ofertaForm.offer.idEmpresa}" alt="${ofertaForm.offer.empresaNombre}" width="100px" height="100px">
						</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>
							<font color="#516814">${ofertaForm.offer.empresaNombre}</font><br/>
							<p>Oferta de empleo<br /><strong>${ofertaForm.offer.tituloOferta}</strong></p>
							<p>Tipo de contrato<br /><strong>${ofertaForm.offer.tipoContrato}</strong></p>
							<p>Horario laboral<br /><strong>${ofertaForm.offer.horaEntrada} a ${ofertaForm.offer.horaSalida}</strong></p>				
							<p>Ubicación<br /><strong>${ofertaForm.offer.ubicacion}</strong><br /></p>
							<p>Contacto<br /><strong>${ofertaForm.offer.oferta.correoElectronicoContacto}</strong><br /></p>
							<c:if test="${empty USERLOGGED}"><strong>¿Te interesa esta oferta de empleo? Inicia sesión para postularte. Recuerda que debes estar registrado en el portal.</strong></c:if>
						</td>
						</tr>
					</table>
				</div>
			</div>

			<c:if test="${not empty USERLOGGED and USERLOGGED.candidato}">
			<div dojoType="dojox.mobile.RoundRect" >
				<div class="mblVariableHeight" style="font-size: 10px; text-align: center;">
					<p><strong>${candidato.nombre}</strong><br /> tu porcentaje de compatibilidad <br /> con esta oferta de empleo es del:<br />
				   				<span id="porcentaje"><font style="font-weight: bolder; font-size: large;">${ofertaForm.compatibility}%</font></span>
							</p>
						
				</div>
			</div>
			</c:if>

			<div dojoType="dojox.mobile.RoundRect" >
				<div  class="titNarSeccion">Requisitos</div>
				<div class="mblVariableHeight" style="font-size: 10px;">
					
					
					<table cellpadding="0" cellspacing="0">
					<tr>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td><strong>Último grado de estudios:</strong>${ofertaForm.offer.gradoEstudios}<br>
							<strong>Carrera o especialidad:</strong>${ofertaForm.offer.especialidades}<br>
							<strong>Situación académica:</strong>${ofertaForm.offer.situacionAcademica}<br>
							<strong>Conocimientos y habilidades generales:</strong>${ofertaForm.offer.habilidadGeneral}<br>
				            <strong>Experiencia:</strong> ${ofertaForm.offer.experienciaAnios}<br>
							<c:if test="${not empty ofertaForm.offer.requisitos}"><strong>Competencias:</strong>${ofertaForm.offer.requisitos}<br></c:if>
							<strong>Idiomas:</strong> ${ofertaForm.offer.idiomasCert}<br>
							<c:if test="${ofertaForm.offer.edadRequisito == 2}">
								<strong>Rango de edad:</strong> ${ofertaForm.offer.edadMinima}a ${ofertaForm.offer.edadMaxima} años<br/>
							</c:if>
							<c:if test="${not empty ofertaForm.offer.disponibilidadViajar}">
								<strong>${ofertaForm.offer.disponibilidadViajar}</strong><br/>
							</c:if>
				            <c:if test="${not empty ofertaForm.offer.disponibilidadRadicar}">
								<strong>${ofertaForm.offer.disponibilidadRadicar}</strong><br>
							</c:if>
							<c:if test="${not empty ofertaForm.offer.observaciones}">
				            	<strong>Observaciones:</strong> ${ofertaForm.offer.observaciones}<br>
				            </c:if>
				        </td>
					</tr>
				</table>

				</div>
			</div>

			<div dojoType="dojox.mobile.RoundRect" >
				<div class="titNarSeccion">Descripci&oacute;n de la oferta</div>
				<div class="mblVariableHeight" style="font-size: 10px;">
					<table cellpadding="0" cellspacing="0">
					<tr>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td><!--<strong>Área laboral:</strong> ${ofertaForm.offer.areaLaboral}<br>-->
							<strong>Ocupación:</strong>  ${ofertaForm.offer.ocupacion}<br>
							<!--<strong>Sector:</strong> ${ofertaForm.offer.sector}<br>-->
							<strong>Funciones:</strong> ${ofertaForm.offer.funciones}<br>
							<strong>Tipo de empleo:</strong> ${ofertaForm.offer.tipoEmpleo}<br>
							<!--<p><strong>Horario:</strong> ${ofertaForm.offer.horario}</p>-->
							<strong>Días laborales:</strong> ${ofertaForm.offer.diasLaborales}<strong>${ofertaForm.offer.rolarTurno}</strong><br>
							
							<strong>Tipo de discapacidad aceptada:</strong> ${ofertaForm.offer.tipoDiscapacidad}<br>
							
							<!--<p><strong>Causa que origina la oferta:</strong> ${ofertaForm.offer.causaOriginaOferta}</p>--></td>
					</tr>
				</table>
				
				</div>
				</div>
				<div dojoType="dojox.mobile.RoundRect" >
				<div class="titNarSeccion">La empresa ofrece</div>
				<div class="mblVariableHeight" style="font-size: 10px;">
					<table cellpadding="0" cellspacing="0">
					<tr>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td><strong>La empresa ofrece: </strong>${ofertaForm.offer.empresaOfrece}<br>
							<c:if test="${not empty ofertaForm.offer.salario}">
							<strong>Sueldo:</strong> <fmt:setLocale value="en_US"/>  <fmt:formatNumber type="CURRENCY" value="${ofertaForm.offer.salario}"/><br>
							</c:if>
							<strong>Prestaciones:</strong> ${ofertaForm.offer.prestaciones}<br>
							<strong>Jerarquía:</strong>${ofertaForm.offer.jerarquia}<br>
				            <strong>No. de plazas:</strong> ${ofertaForm.offer.numeroPlazas}<br></td>
					</tr>
				</table>
				
				</div>
			</div>
			<c:set var="estatus" value="<%= Constantes.ESTATUS.ACTIVO.getOpcion()%>" />
			<c:if test="${not empty USERLOGGED}">
				<c:if test="${USERLOGGED.empresa}">

					

					<div id="listaAcciones" dojoType="dojox.mobile.View" selected="true" style="font-size: 10px;">
					<ul dojoType="dojox.mobile.RoundRectList">
						<c:if test="${ofertaForm.offer.estatus!=estatus}">
						<li dojoType="dojox.mobile.ListItem" moveTo="bar" 
						    href="detalleoferta.m?method=activaOferta&idOfertaEmpleo=${ofertaForm.offer.idOfertaEmpleo}" transition="slide">
						    &nbsp;&nbsp;Activar oferta
						</li>
						</c:if>
						<c:if test="${ofertaForm.offer.estatus==estatus}">
						<li dojoType="dojox.mobile.ListItem" moveTo="bar"
						    href="detalleoferta.m?method=cancelaOferta&idOfertaEmpleo=${ofertaForm.offer.idOfertaEmpleo}">
						    &nbsp;&nbsp;Desactivar oferta
						</li>
						</c:if>
						<li dojoType="dojox.mobile.ListItem" moveTo="bar" href="javascript:preguntasOfertas();">
							&nbsp;&nbsp;Responder preguntas(${ofertaForm.numeroPreguntasOferta})
						</li>
						<li dojoType="dojox.mobile.ListItem" moveTo="bar" 
						    href="buscarCandidatosEmpresa.m?method=busquedaCandidatosOferta&idOfertaEmpleo=${ofertaForm.offer.idOfertaEmpleo}&tituloOferta=${ofertaForm.offer.tituloOferta}">
						    &nbsp;&nbsp;Candidatos para esta oferta
						 </li>
					</ul>
					</div>
				</c:if>
				<c:if test="${ofertaForm.offer.estatus==estatus}">
				<c:if test="${USERLOGGED.candidato}">
				
						
						
					<c:if test="${!ofertaForm.postulated}">
						<div dojoType="dojox.mobile.RoundRect"  style="text-align: right;">
							<div style="text-align: left;" class="titNarSeccion">Postulación</div>
								<button id="postularBtn" dojoType="dojox.mobile.Button" style="width:120px;"
										onclick="postularse(${ofertaForm.offer.idOfertaEmpleo})">
								Postularme
								</button>
							</div>
						
					</c:if>
					<c:if test="${ofertaForm.postulated}">
						<div dojoType="dojox.mobile.RoundRect"  style="font-size: 10px;">
							<div style="text-align: left;" class="titNarSeccion">Postulación</div>
							<p class="ingresoMsg">Ya te encuentras postulado a esta oferta.</p>
						</div>
					</c:if>
				</c:if>
			</c:if>
		</c:if>

	</div>

	<div id="bar" dojoType="dojox.mobile.View"></div>

	<c:if test="${not empty errmsg}">
	<script>
		message('${errmsg}');
		<%session.removeAttribute("errmsg");%>
	</script>
	</c:if>
</body>
</html>


