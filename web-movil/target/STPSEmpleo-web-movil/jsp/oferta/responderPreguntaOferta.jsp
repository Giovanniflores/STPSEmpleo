<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="mx.gob.stps.portal.movil.web.infra.utils.Utils"%>
<%@ page import="mx.gob.stps.portal.core.infra.utils.Constantes"%>



<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
	<jsp:include page="../seguridad/header.jsp"/>

	<script type="text/javascript">
	
	function preguntas(){
	
	document.respuestaPreguntaForm.method.value="preguntasOferta";
	document.respuestaPreguntaForm.submit();
	
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
<form id="respuestaPreguntaForm" name="respuestaPreguntaForm" method="post" action="detalleoferta.m" >
	<input type="hidden" id="idOfertaEmpleo" name="idOfertaEmpleo" value="${ofertaForm.idOfertaEmpleo}">
	<input type="hidden" id="idEmpresa" name="idEmpresa" value="${ofertaForm.idEmpresa}">
	<input type="hidden" id="empresaNombre" name="empresaNombre" value="${ofertaForm.empresaNombre}">
	<input type="hidden" id="tituloOferta" name="tituloOferta" value="${ofertaForm.tituloOferta}">
	<input type="hidden" id="tipoContrato" name="tipoContrato" value="${ofertaForm.tipoContrato}">
	<input type="hidden" id="horarioLaboral" name="horarioLaboral" value="${ofertaForm.horarioLaboral}">
	<input type="hidden" id="idOfertaPregunta" name="idOfertaPregunta" value="${ofertaForm.idOfertaPregunta}">
	<input type="hidden" id="method" name="method" value="registraRespuesta">
	<div id="respuesta" dojoType="dojox.mobile.View" selected="true">
	<h1 dojoType="dojox.mobile.Heading">
				<div dojoType="dojox.mobile.ToolBarButton" icon="images/ico_rwd.jpg" moveTo="bar" href="javascript:preguntas();" transitionDir="-1"></div>
				<div dojoType="dojox.mobile.ToolBarButton" icon="images/ico_exit.jpg" style="width:45px;float:right;" href="logout.m" transitionDir="-1"></div>
			</h1>
			
			
			
			<div dojoType="dojox.mobile.RoundRect" shadow="true">
			<h2 dojoType="dojox.mobile.RoundRectCategory" class="titNarSeccion">${ofertaForm.pregunta}</h2>
				<div class="mblVariableHeight" style="font-size: 10px;">
			
			<table align="center">
			
				<tr>
					<td align="left">
						<textarea style="width: 100%" id="respuesta" name="respuesta" cols="50" rows="5"></textarea>
					</td>
				</tr>
				
				<tr>
					<td align="right">
						<button dojoType="dojox.mobile.Button" type="submit">Enviar</button>
					</td>
				</tr>
				
			</table>
			
			
			</div>
			</div>
			
	</div>
			
	</form>
</body>
</html>