<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="mx.gob.stps.portal.core.infra.utils.Constantes"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
	<jsp:include page="../seguridad/header.jsp"/>

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

 	function doSubmitPager(method) {
 		document.ofertasForm.method.value = method;
		document.ofertasForm.submit();		
  	}

	function postularse(idOfertaEmpleo) {
		document.postulacionForm.idOfertaEmpleo.value=idOfertaEmpleo;
		document.postulacionForm.submit();
  	}
 	
 	/*function postularse(idOfertaEmpleo) {
    	var urlparams = 'postular.m?idOfertaEmpleo='+ idOfertaEmpleo;

      	dojo.xhrPost({url: urlparams, timeout:180000, 
                     load: function(data) {
                    	var result = dojo.fromJson(data);
                    	
                    	if ('ext' == result.type){
                    		var postularBtn = dojo.byId('postularBtn'+idOfertaEmpleo);
                    		if (postularBtn) postularBtn.disabled=true;
                    		
                    	} else if ('err' == result.type) {
                    	}

                    	message(result.message);
                    }});
  	}*/

	function message(msg){
		alert(msg);
	}
 	
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
	<!-- Formulario para invocacion a la postulacion -->
	<form id="postulacionForm" name="postulacionForm" method="post" action="postular.m" >
		<input type="hidden" name="idOfertaEmpleo" id="idOfertaEmpleo" value="" />
	</form>


	<div id="ofertasView" dojoType="dojox.mobile.View" selected="true">

		<c:if test="${empty USERLOGGED}">
			<h1 dojoType="dojox.mobile.Heading">
				<div dojoType="dojox.mobile.ToolBarButton" icon="images/ico_rwd.jpg" moveTo="bar" href="home.m" transitionDir="-1"></div>
			</h1>
		</c:if>
		<c:if test="${not empty USERLOGGED}">
			<c:if test="${USERLOGGED.empresa}">
				<h1 dojoType="dojox.mobile.Heading">
					<div dojoType="dojox.mobile.ToolBarButton" icon="images/ico_home.jpg" moveTo="bar" href="empresaEspacio.m?method=init" transitionDir="-1"></div>
					<div dojoType="dojox.mobile.ToolBarButton" icon="images/ico_exit.jpg"  style="width:45px;float:right;" href="logout.m" transitionDir="-1"></div>
				</h1>
			</c:if>
			<c:if test="${USERLOGGED.candidato}">
				<h1 dojoType="dojox.mobile.Heading">
					<div dojoType="dojox.mobile.ToolBarButton" icon="images/ico_home.jpg" moveTo="bar" href="candidatoEspacio.m?method=init" transitionDir="-1"></div>
					<div dojoType="dojox.mobile.ToolBarButton" icon="images/ico_exit.jpg" style="width:45px;float:right;" href="logout.m" transitionDir="-1"></div>
				</h1>
			</c:if>
		</c:if>
		
		<jsp:include page="../oferta/ocupate.jsp"/>

		<form id="ofertasForm" name="ofertasForm" method="post" action="ocupate.m">
			<input type="hidden" name="method" id="method" value="init"/>
			<input type="hidden" name="searchQ" id="searchQ" value="${searchQ}"/>
		</form>

		<!-- LISTA DE OFERTA -->
		<%--<h2 dojoType="dojox.mobile.RoundRectCategory">Ofertas de Empleo</h2>--%>
		<c:if test="${empty PAGE_LIST}">
		<div dojoType="dojox.mobile.RoundRect"  align="center">
			<div style="text-align: center;" class="titNar">Ofertas de Empleo</div>
			No se encontraron ofertas de empleo para la búsqueda:<b><i><c:out value="${searchQ}"/> <c:out value="${entity}"/></i></b>
		</div>
		</c:if>

		<c:if test="${not empty PAGE_LIST}">
		Ofertas de empleo para la búsqueda:<b><i><c:out value="${searchQ}"/><c:out value="${entity}"/></i></b>
		<c:set var="primerRegistroMostrado"
			value="${(NUM_PAGE_LIST-1)*PAGE_NUM_ROW+1}" />
		<c:set var="ultimoRegistroMostrado"
			value="${primerRegistroMostrado+PAGE_NUM_ROW-1}" />
			<c:if test="${ultimoRegistroMostrado>NUM_RECORDS_TOTAL}">
			<c:set var="ultimoRegistroMostrado" value="${NUM_RECORDS_TOTAL}" />
		</c:if>
		<h1 dojoType="dojox.mobile.Heading" style="font-size: 10px; height: 38px; text-align: left;"
		    label="Ofertas de empleo  <c:out value="${primerRegistroMostrado}" /> - <c:out value="${ultimoRegistroMostrado}" /> de ${NUM_RECORDS_TOTAL}, página ${NUM_PAGE_LIST}" class="mblHeadingNoImage">
			
			<ul dojoType="dojox.mobile.TabBar" barType="segmentedControl" style="float: right; margin-right: 6px;">
				<li dojoType="dojox.mobile.TabBarButton" class="mblDomButton mblDomButtonUpArrow_2" selectOne="false" onclick="javascript:doSubmitPager('prev')">
					<img src="images/bt_up.png"/>
				</li>
				<li dojoType="dojox.mobile.TabBarButton" class="mblDomButton mblDomButtonDownArrow_2" selectOne="false"  onclick="javascript:doSubmitPager('next')">
					<img src="images/bt_down.png"/>
				</li>
			</ul>
		</h1>

		<ul dojoType="dojox.mobile.EdgeToEdgeList" >			
			<c:forEach var="oferta" items="${PAGE_LIST}">
			<li class="mblVariableHeight" dojoType="dojox.mobile.ListItem" style="font-size: 10px;" moveTo="ofertadetalle${oferta.idOfertaEmpleo}">
				<table>
					<tr>
						<td>
							<a class="titNarSeccion">${oferta.tituloOferta}</a><br/>
							${oferta.ubicacion}<br/>
							${oferta.empresa}<br/>
							<c:if test="${not empty oferta.salario}">
								<fmt:setLocale value="en_US"/>Salario pretendido: <fmt:formatNumber type="CURRENCY" value="${oferta.salario}" />
							</c:if>
							<c:if test="${not empty USERLOGGED && USERLOGGED.candidato}">
								<br/><b>Compatibilidad:</b> ${oferta.compatibility}%
							</c:if>
						</td>
					</tr>
				</table>
			</li>
			</c:forEach>
		</ul>

		</c:if>

	</div>


	<!-- DETALLE DE OFERTA -->
	<c:if test="${not empty PAGE_LIST}">

		<c:forEach var="ofertaDetalle" items="${PAGE_LIST}">

		<div id="ofertadetalle${ofertaDetalle.idOfertaEmpleo}" dojoType="dojox.mobile.View">

			<h1 dojoType="dojox.mobile.Heading">
				<div dojoType="dojox.mobile.ToolBarButton" icon="images/ico_rwd.jpg" moveTo="ofertasView" transitionDir="-1"></div>
				<c:if test="${not empty USERLOGGED}">
				<div dojoType="dojox.mobile.ToolBarButton" icon="images/ico_exit.jpg" style="width:45px;float:right;" href="logout.m"></div>
				</c:if>
			</h1>

			<%--
			<h2 dojoType="dojox.mobile.RoundRectCategory">Detalle de Oferta</h2>
			--%>
			<div dojoType="dojox.mobile.RoundRect" >
				<div style="text-align: center;" class="titNar">Detalle de Oferta</div>

				<div class="mblVariableHeight" style="font-size: 10px;">
					<table cellpadding="0" cellspacing="0">
						<tr>
						<td class="imgDetalle"><img src="imageAction.m?method=getLogotipoEmpresaRequest&ID_EMPRESA=${ofertaDetalle.detalle.idEmpresa}" alt="${ofertaDetalle.detalle.empresaNombre}" width="100px" height="100px">
						</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>
							<font color="#516814">${ofertaDetalle.detalle.empresaNombre}</font><br/>
							<p>Oferta de empleo<br /><strong>${ofertaDetalle.detalle.tituloOferta}</strong></p>
							<p>Tipo de contrato<br /><strong>${ofertaDetalle.detalle.tipoContrato}</strong></p>
							<p>Horario laboral<br /><strong>${ofertaDetalle.detalle.horaEntrada} a ${ofertaDetalle.detalle.horaSalida}</strong></p>				
							<p>Ubicación<br /><strong>${ofertaDetalle.detalle.ubicacion}</strong><br /></p>
							<p>Contacto<br /><strong>${ofertaDetalle.detalle.oferta.correoElectronicoContacto}</strong><br /></p>
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
				   				<span id="porcentaje">${ofertaDetalle.compatibility}%</span>
							</p>
				</div>
			</div>
			</c:if>

			<div dojoType="dojox.mobile.RoundRect" >
				<div class="titNarSeccion">Requisitos</div>
				<div class="mblVariableHeight" style="font-size: 10px;">
					
					
					<table cellpadding="0" cellspacing="0">
					<tr>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td><strong>Último grado de estudios:</strong>${ofertaDetalle.detalle.gradoEstudios}<br>
							<strong>Carrera o especialidad:</strong>${ofertaDetalle.detalle.especialidades}<br>
							<strong>Situación académica:</strong>${ofertaDetalle.detalle.situacionAcademica}<br>
							<strong>Conocimientos y habilidades generales:</strong>${ofertaDetalle.detalle.habilidadGeneral}<br>
				            <strong>Experiencia:</strong> ${ofertaDetalle.detalle.experienciaAnios}<br>
							<c:if test="${not empty ofertaDetalle.detalle.requisitos}"><strong>Competencias:</strong>${ofertaDetalle.detalle.requisitos}<br></c:if>
							<strong>Idiomas:</strong> ${ofertaDetalle.detalle.idiomasCert}<br>
							<c:if test="${ofertaDetalle.detalle.edadRequisito == 2}">
								<strong>Rango de edad:</strong> ${ofertaDetalle.detalle.edadMinima}a ${ofertaDetalle.detalle.edadMaxima} años<br/>
							</c:if>
							<c:if test="${not empty ofertaDetalle.detalle.disponibilidadViajar}">
								<strong>${ofertaDetalle.detalle.disponibilidadViajar}</strong><br/>
							</c:if>
				            <c:if test="${not empty ofertaDetalle.detalle.disponibilidadRadicar}">
								<strong>${ofertaDetalle.detalle.disponibilidadRadicar}</strong><br>
							</c:if>
							<c:if test="${not empty ofertaDetalle.detalle.observaciones}">
				            	<strong>Observaciones:</strong> ${ofertaDetalle.detalle.observaciones}<br>
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
						<td><!--<strong>Área laboral:</strong> ${ofertaDetalle.detalle.areaLaboral}<br>-->
							<strong>Ocupación:</strong>  ${ofertaDetalle.detalle.ocupacion}<br>
							<!--<strong>Sector:</strong> ${ofertaDetalle.detalle.sector}<br>-->
							<strong>Funciones:</strong> ${ofertaDetalle.detalle.funciones}<br>
							<strong>Tipo de empleo:</strong> ${ofertaDetalle.detalle.tipoEmpleo}<br>
							<!--<p><strong>Horario:</strong> ${ofertaDetalle.detalle.horario}</p>-->
							<strong>Días laborales:</strong> ${ofertaDetalle.detalle.diasLaborales}<strong>${ofertaDetalle.detalle.rolarTurno}</strong><br>
							</td>
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
						<td><strong>La empresa ofrece: </strong>${ofertaDetalle.detalle.empresaOfrece}<br>
				            <c:if test="${not empty ofertaDetalle.detalle.salario}">
				            <strong>Sueldo:</strong> <fmt:setLocale value="en_US"/><fmt:formatNumber type="CURRENCY" value="${ofertaDetalle.detalle.salario}"/><br>
				            </c:if>							
							<strong>Prestaciones:</strong> ${ofertaDetalle.detalle.prestaciones}<br>
							<strong>Jerarquía:</strong>${ofertaDetalle.detalle.jerarquia}<br>
				            <strong>No. de plazas:</strong> ${ofertaDetalle.detalle.numeroPlazas}<br>
							<strong>Tipo de discapacidad aceptada:</strong> ${ofertaDetalle.detalle.tipoDiscapacidad}<br>
							
							<!--<p><strong>Causa que origina la oferta:</strong> ${ofertaDetalle.detalle.causaOriginaOferta}</p>--></td>
					</tr>
				</table>
				
				</div>
			</div>
			
			<c:if test="${not empty USERLOGGED}">
				<%-- LAS EMPRESAS NO TIENEN ACCIONES SOBRE LAS OFERTAS DEL OCUPATE
				<c:if test="${USERLOGGED.empresa}">

					<c:set var="estatus" value="<%= Constantes.ESTATUS.ACTIVO.getOpcion()%>" />

					<div id="listaAcciones" dojoType="dojox.mobile.View" selected="true" style="font-size: small;">
					<ul dojoType="dojox.mobile.RoundRectList">
						<c:if test="${ofertaDetalle.detalle.estatus!=estatus}">
						<li dojoType="dojox.mobile.ListItem" moveTo="bar" 
						    href="detalleoferta.m?method=activaOferta&idOfertaEmpleo=${ofertaDetalle.detalle.idOfertaEmpleo}" transition="slide">
						    &nbsp;&nbsp;Activar oferta
						</li>
						</c:if>
						<c:if test="${ofertaDetalle.detalle.estatus==estatus}">
						<li dojoType="dojox.mobile.ListItem" moveTo="bar"
						    href="detalleoferta.m?method=cancelaOferta&idOfertaEmpleo=${ofertaDetalle.detalle.idOfertaEmpleo}">
						    &nbsp;&nbsp;Cancelar oferta
						</li>
						</c:if>
						<li dojoType="dojox.mobile.ListItem" moveTo="bar" href="javascript:preguntasOfertas();">
							&nbsp;&nbsp;Preguntas asociadas a la oferta
						</li>
						<li dojoType="dojox.mobile.ListItem" moveTo="bar" 
						    href="buscarCandidatosEmpresa.m?method=busquedaCandidatosOferta&idOfertaEmpleo${ofertaDetalle.detalle.idOfertaEmpleo}">
						    &nbsp;&nbsp;Buscar candidatos
						 </li>
					</ul>
					</div>
				</c:if>
				--%>
				<c:if test="${USERLOGGED.candidato}">
					<c:if test="${!ofertaDetalle.postulated}">
						<div dojoType="dojox.mobile.RoundRect" style="text-align: center;">
								<div style="text-align: left;" class="titNarSeccion">Postulación</div>
								<button id="postularBtn${ofertaDetalle.detalle.idOfertaEmpleo}" dojoType="dojox.mobile.Button"
								        style="width:120px;" onclick="postularse(${ofertaDetalle.detalle.idOfertaEmpleo})">
								Postularme
								</button>
							</div>
						
						<c:if test="${ofertaDetalle.postulated}">
						<div dojoType="dojox.mobile.RoundRect"  style="font-size: 10px;">
							<div style="text-align: left;" class="titNarSeccion">Postulación</div>
							<p class="ingresoMsg">Ya te encuentras postulado a esta oferta.</p>
						</div>
					</c:if>
				</c:if>
			</c:if>
			</c:if>

		</div>

		</c:forEach>
	
	</c:if>

</body>
</html>
