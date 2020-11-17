<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="mx.gob.stps.portal.movil.web.infra.utils.Utils"%>
<%@ page import="mx.gob.stps.portal.core.infra.utils.Constantes"%>

<%
	String context = request.getContextPath() + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
	<jsp:include page="../seguridad/header.jsp"/>

	<script type="text/javascript">
	
	function responder(idOfertaPregunta,pregunta) {
     
     document.preguntaOfertaForm.method.value="responderPregunta";
     document.preguntaOfertaForm.idOfertaPregunta.value=idOfertaPregunta;
     document.preguntaOfertaForm.pregunta.value=pregunta;
     document.preguntaOfertaForm.submit();
        
      
  	}
	
	
	</script>

	<script type="text/javascript">
	
	function message(msg){
	alert(msg);
	}
	
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
<form id="preguntaOfertaForm" name="preguntaOfertaForm" method="post" action="detalleoferta.m" >
	<input type="hidden" id="idOfertaEmpleo" name="idOfertaEmpleo" value="${ofertaForm.idOfertaEmpleo}">
	<input type="hidden" id="idEmpresa" name="idEmpresa" value="${ofertaForm.idEmpresa}">
	<input type="hidden" id="empresaNombre" name="empresaNombre" value="${ofertaForm.empresaNombre}">
	<input type="hidden" id="tituloOferta" name="tituloOferta" value="${ofertaForm.tituloOferta}">
	<input type="hidden" id="tipoContrato" name="tipoContrato" value="${ofertaForm.tipoContrato}">
	<input type="hidden" id="horarioLaboral" name="horarioLaboral" value="${ofertaForm.horarioLaboral}">
	<input type="hidden" id="idOfertaPregunta" name="idOfertaPregunta" value="">
	<input type="hidden" id="pregunta" name="pregunta" value="">
	<input type="hidden" id="method" name="method" value="init">
	<div id="miespaciocand" dojoType="dojox.mobile.View" selected="true">
	<h1 dojoType="dojox.mobile.Heading">
				<div dojoType="dojox.mobile.ToolBarButton" icon="images/ico_rwd.jpg" moveTo="bar" href="${context}detalleoferta.m?method=init&idOfertaEmpleo=${ofertaForm.idOfertaEmpleo}" transitionDir="-1"></div>
				<div dojoType="dojox.mobile.ToolBarButton" icon="images/ico_exit.jpg" style="width:45px;float:right;" href="logout.m" transitionDir="-1"></div>
			</h1>
			
			<h2 dojoType="dojox.mobile.RoundRectCategory">Detalle de Oferta</h2>
			<div dojoType="dojox.mobile.RoundRect" >
				<div class="mblVariableHeight" style="font-size: 10px;">
					<table cellpadding="0" cellspacing="0">
						<tr>
						<td><img src="${context}imageAction.m?method=getLogotipoEmpresaRequest&ID_EMPRESA=${ofertaForm.idEmpresa}" alt="${ofertaForm.empresaNombre}" width="100px" height="100px">
						</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td><font color="#516814">${ofertaForm.empresaNombre}</font><br />
							<p>Oferta de empleo<br /><strong>${ofertaForm.tituloOferta}</strong></p>
							<p>Tipo de contrato<br /><strong>${ofertaForm.tipoContrato}</strong></p>
							<p>Horario laboral<br /><strong>${ofertaForm.horarioLaboral}</strong></p>				
						</tr>
					</table>
				</div>
			</div>
			
	

			<div dojoType="dojox.mobile.RoundRect" >
			<div class="mblVariableHeight" style="font-size: 10px;">	
					<c:if test="${empty ofertaForm.ofertaPreguntasList}">
						<div dojoType="dojox.mobile.View" id="vacio" selected="true">
							No hay preguntas recientes acerca de la oferta de empleo
							
						</div>
					</c:if>
					<c:if test="${not empty ofertaForm.ofertaPreguntasList}">
					<ul dojoType="dojox.mobile.RoundRectList" >
						<div style="text-align: center;" class="titNar">Preguntas Asociadas a la Oferta</div>
					<c:forEach var="pregunta" items="${ofertaForm.ofertaPreguntasList}">
						<c:if test="${empty pregunta.respuesta }">
							<li class="mblVariableHeight" dojoType="dojox.mobile.ListItem" moveTo="bar" href="javascript:responder(${pregunta.idOfertaPregunta},'${pregunta.pregunta}');">
							
							<table>
									<tbody>
										<tr>
											<td><p><strong>${pregunta.pregunta}</strong></p></td>
										</tr>
									</tbody>
								</table>	
								
								
						 	</li>
							</c:if>
							<c:if test="${not empty pregunta.respuesta}">
							<li class="mblVariableHeight" dojoType="dojox.mobile.ListItem">
								<table>
									<tbody>
										<tr>
											<td><p><strong>${pregunta.pregunta}</strong>
											<br>
											Reclutador: ${pregunta.respuesta}</p></td>
										</tr>
									</tbody>
								</table>	
								
						 	</li>
						 	</c:if>

						
				
					</c:forEach>	
			</ul>
					</c:if>
					
					
					
					
				</div>
			
		
		
		
	
	</div>
	<div id="bar" dojoType="dojox.mobile.View"></div>
	</form>
	
	
	</body>
	</html>
	