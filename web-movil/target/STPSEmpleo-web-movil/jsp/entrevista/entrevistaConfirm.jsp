<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
	<jsp:include page="../seguridad/header.jsp"/>

	<script type="text/javascript">
	
	function doSubmit(method){
		document.entrevistaEmpresaForm.method.value=method;
		document.entrevistaEmpresaForm.submit();
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

<%--
	<div id="ofertasView" dojoType="dojox.mobile.View" selected="true">
		<h1 dojoType="dojox.mobile.Heading"></h1>
		
		<h2 dojoType="dojox.mobile.RoundRectCategory">Empleo en tu móvil</h2>
		
		<jsp:include page="../oferta/ocupate.jsp"/>
		<br/>
	</div>
--%>

	<form id="entrevistaEmpresaForm" name="entrevistaEmpresaForm" method="post" action="entrevistaProgramada.m">
		<input type="hidden" id="method" name="method" value="init">
		<input type="hidden" id="idEntrevista" name="idEntrevista" value="${entrevistaProgramadaForm.idEntrevista}">
		<input type="hidden" id="tituloTipo" name="tituloTipo" value="${entrevistaProgramadaForm.tituloTipo}">
		<input type="hidden" id="idCandidato" name="idCandidato" value="${entrevistaProgramadaForm.idCandidato}">
	</form>

	<div id="entrevista" dojoType="dojox.mobile.View" selected="true">
	
	<c:if test="${USERLOGGED.empresa}">
		<h1 dojoType="dojox.mobile.Heading" back="Entrevistas programadas" moveTo="bar"
			href="entrevistaProgramada.m?method=entrevistaProgramadaEmpresas">
		</h1>
			
			<h2 dojoType="dojox.mobile.RoundRectCategory">Espacio para Empresas</h2>
			<div dojoType="dojox.mobile.RoundRect" >
				<div class="mblVariableHeight" style="font-size: small;">
					<jsp:include page="/empresaEspacio.m" flush="true">
						<jsp:param name="method" value="detalleEmpresa" />
					</jsp:include>
				</div>
			</div>
	</c:if>		

		<c:if test="${USERLOGGED.candidato}">	
			<h1 dojoType="dojox.mobile.Heading">
				<div dojoType="dojox.mobile.ToolBarButton" icon="images/ico_home.jpg" moveTo="bar" href="candidatoEspacio.m?method=init" transitionDir="-1"></div>
				<div dojoType="dojox.mobile.ToolBarButton" icon="images/ico_exit.jpg" style="width:45px;float:right;" href="logout.m"></div>
			</h1>

			<%--
			<jsp:include page="../oferta/ocupate.jsp"/>
			
	    	<div dojoType="dojox.mobile.RoundRect" >
				<div class="mblVariableHeight" style="font-size: small;">
					<jsp:include page="/candidatoEspacio.m" flush="true">
				 		<jsp:param name="method" value="datosCandidato" />
					</jsp:include>
				</div>
	    	</div>
	    	--%>
		</c:if>		

		<div dojoType="dojox.mobile.RoundRect"  style="text-align: center; vertical-align: middle;">
			<p class="ingresoMsg">${ENTREVISTA_MENSAJE}</p>
		</div>
			
		<div dojoType="dojox.mobile.RoundRect" >
			<div style="text-align: center;" class="titNar">Entrevista programada</div>
			
			<div class="mblVariableHeight" style="font-size: small;">
				<b>Oferta:</b>    ${entrevistaProgramadaForm.tituloOferta}<br/>
				<b>Candidato:</b> ${entrevistaProgramadaForm.nombreCandidato}<br/>
				<b>Fecha:</b>     ${entrevistaProgramadaForm.fechaEntrevista} &nbsp;&nbsp;&nbsp;&nbsp;
				<b>Hora:</b>      ${entrevistaProgramadaForm.horaEntrevista}
			</div>
		</div>
	</div>
	
	<div id="bar" dojoType="dojox.mobile.View"></div>

</body>
</html>