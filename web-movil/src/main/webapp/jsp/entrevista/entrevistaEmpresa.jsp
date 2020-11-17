<%@page import="mx.gob.stps.portal.core.infra.utils.Constantes"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
	<jsp:include page="../seguridad/header.jsp"/>

	<script type="text/javascript">
	function doSubmit(idEntrevista,oferta,candidato,fecha,hora,idCandidato){	
		document.entrevistaEmpresaForm.method.value="modificarEstatusEntrevista";
		document.entrevistaEmpresaForm.idEntrevista.value=idEntrevista;
		document.entrevistaEmpresaForm.tituloOferta.value=oferta;
		document.entrevistaEmpresaForm.nombreCandidato.value=candidato;
		document.entrevistaEmpresaForm.fechaEntrevista.value=fecha;
		document.entrevistaEmpresaForm.horaEntrevista.value=hora;
		document.entrevistaEmpresaForm.idCandidato.value=idCandidato;
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

	<form id="entrevistaEmpresaForm" name="entrevistaEmpresaForm" method="post" action="${context}entrevistaProgramada.m">
		<input type="hidden" id="method" name="method" value="init">
		<input type="hidden" id="idEntrevista" name="idEntrevista" value="">
		<input type="hidden" id="nombreCandidato" name="nombreCandidato" value="">
		<input type="hidden" id="tituloOferta" name="tituloOferta" value="">
		<input type="hidden" id="fechaEntrevista" name="fechaEntrevista" value="">
		<input type="hidden" id="horaEntrevista" name="horaEntrevista" value="">
		<input type="hidden" id="idCandidato" name="idCandidato" value="">
	
	<div id="miespaciocand" dojoType="dojox.mobile.View" selected="true">
			<h1 dojoType="dojox.mobile.Heading">
				<div dojoType="dojox.mobile.ToolBarButton" icon="images/ico_home.jpg" moveTo="bar" href="<%=request.getContextPath()%>/empresaEspacio.m?method=init" transitionDir="-1"></div>
				<div dojoType="dojox.mobile.ToolBarButton" icon="images/ico_exit.jpg" style="width:45px;float:right;" href="logout.m" transitionDir="-1"></div>
			</h1>

		<div dojoType="dojox.mobile.RoundRect" >
			<h2 dojoType="dojox.mobile.RoundRectCategory" class="titNarSeccion">Entrevistas Programadas</h2>
				<div class="mblVariableHeight" style="font-size: small;">	
					<c:if test="${empty entrevistaProgramadaForm.entrevistas}">
						<div dojoType="dojox.mobile.View" id="vacio" selected="true" style="font-size: 10px; text-align: center;">
							No cuenta con entrevistas programadas
							
						</div>
					</c:if>
					<c:if test="${not empty entrevistaProgramadaForm.entrevistas}">
					
					<c:forEach var="entrevista" items="${entrevistaProgramadaForm.entrevistas}">
						<c:set var="estatus" value="${entrevista.estatus}" scope="request"/>
						<ul dojoType="dojox.mobile.EdgeToEdgeList" >
						<%if(Constantes.ESTATUS.ACEPTADA.getIdOpcion() == (Integer)request.getAttribute("estatus") || 
							 Constantes.ESTATUS.CANCELADA.getIdOpcion() == (Integer)request.getAttribute("estatus")  || 
							 Constantes.ESTATUS.RECHAZADA.getIdOpcion() == (Integer)request.getAttribute("estatus") ||
							 Constantes.ESTATUS.ELIMINADA_EMP.getIdOpcion() == (Integer)request.getAttribute("estatus") ||
							 Constantes.ESTATUS.ELIMINADA_ADMIN.getIdOpcion() == (Integer)request.getAttribute("estatus") ||
							 Constantes.ESTATUS.ELIMINADA_VIG.getIdOpcion() == (Integer)request.getAttribute("estatus") ||
							 Constantes.ESTATUS.PENDIENTE_PUBLICAR.getIdOpcion() == (Integer)request.getAttribute("estatus") ||
							 Constantes.ESTATUS.ELIMINADA_EMP_FRAUDULENTA.getIdOpcion() == (Integer)request.getAttribute("estatus")){ %>
						<li class="mblVariableHeight" dojoType="dojox.mobile.ListItem" style="font-size: 10px;" >
						Oferta: ${entrevista.tituloOferta}<br>
								Candidato: ${entrevista.nombre}<br>
								Contacto: ${entrevista.contactoEmpresa}<br>
								Fecha: ${entrevista.fechaString}&nbsp;&nbsp;Hora: ${entrevista.hora}<br>
								Estatus: <%=Constantes.ESTATUS.getDescripcion((Integer)request.getAttribute("estatus"))%>
						
						</li>
						<%}else{ %>
						<li style="font-size: 10px;" class="mblVariableHeight" dojoType="dojox.mobile.ListItem" moveTo="bar" href="javascript:doSubmit(${entrevista.idEntrevista},'${entrevista.tituloOferta}','${entrevista.nombre}','${entrevista.fechaString}','${entrevista.hora}',${entrevista.idCandidato});">
						Oferta: ${entrevista.tituloOferta}<br>
								Candidato: ${entrevista.nombre}<br>
								Contacto: ${entrevista.contactoEmpresa}<br>
								Fecha: ${entrevista.fechaString}&nbsp;&nbsp;Hora: ${entrevista.hora}<br>
								Estatus: <%=Constantes.ESTATUS.getDescripcion((Integer)request.getAttribute("estatus"))%>
						
						</li>
						
						<%} %>
						</ul>
					
					</c:forEach>	
					</tbody>
					
					</c:if>
					
					
					
					
				</div>
			</div>
</div>
		<div id="bar" dojoType="dojox.mobile.View"></div>
		

	</form>

</body>
</html>
