<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="mx.gob.stps.portal.movil.web.infra.utils.Utils"%>
<%@ page import="mx.gob.stps.portal.core.infra.utils.Constantes"%>

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

	<div id="postulacion" dojoType="dojox.mobile.View" selected="true">

		<c:if test="${not empty USERLOGGED}">
			<c:if test="${USERLOGGED.empresa}">
				<h1 dojoType="dojox.mobile.Heading">
				<div dojoType="dojox.mobile.ToolBarButton" icon="images/ico_rwd.jpg" moveTo="bar" href="ofertasEmpresa.m?method=init" transitionDir="-1"></div>
					<div dojoType="dojox.mobile.ToolBarButton" icon="images/ico_exit.jpg" style="width:45px;float:right;" href="logout.m"></div>
				</h1>
			</c:if>
			<c:if test="${USERLOGGED.candidato}">
				<h1 dojoType="dojox.mobile.Heading">
					<div dojoType="dojox.mobile.ToolBarButton" icon="images/ico_home.jpg" moveTo="bar" href="candidatoEspacio.m?method=init" transitionDir="-1"></div>
					<div dojoType="dojox.mobile.ToolBarButton" icon="images/ico_exit.jpg" style="width:45px;float:right;" href="logout.m"></div>
				</h1>
			</c:if>
		</c:if>

    	<div dojoType="dojox.mobile.RoundRect" >
			<div class="mblVariableHeight" style="font-size: small;"> 
				<jsp:include page="/candidatoEspacio.m" flush="true">
			 		<jsp:param name="method" value="datosCandidato" />
				</jsp:include>
			</div>
    	</div>

		<div dojoType="dojox.mobile.RoundRect" >
			<div style="text-align: center;" class="titNar">Oferta postulada</div>
		
			<div class="mblVariableHeight" style="font-size: 10px;">
				<table cellpadding="0" cellspacing="0">
					<tr>
					<td><img src="imageAction.m?method=getLogotipoEmpresaRequest&ID_EMPRESA=${ofertaForm.offer.idEmpresa}" alt="${ofertaForm.offer.empresaNombre}" width="100px" height="100px">
					</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>
						<font color="#516814">${ofertaForm.offer.empresaNombre}</font><br/>
						<p>Oferta de empleo<br /><strong>${ofertaForm.offer.tituloOferta}</strong></p>
						<p>Persona de contacto:<br /><strong>${nombreContacto}</strong></p>
						<c:if test="${!empty ofertaForm.offer.idContactoCorreo and ofertaForm.offer.idContactoCorreo eq 2}">
						<p>Correo electr&oacute;nico<br /><strong>${correoElectronico}</strong></p>				
						</c:if>	
						<c:if test="${!empty ofertaForm.offer.idContactoTel and ofertaForm.offer.idContactoTel eq 2}">
						<p>Tel&eacute;fono(s)<br /><strong><c:forEach var="row" items="${ofertaForm.offer.telefonos}" varStatus="rowCounter">
						<c:if test="${rowCounter.count != 1}">
							<c:out value=", " />
							</c:if>	
						<c:out value="${row.telefono}" />
						</c:forEach></strong></p>				
						</c:if>	
						<c:if test="${!empty ofertaForm.offer.idContactoDomicilio and ofertaForm.offer.idContactoDomicilio eq 2}">
						<p>Domicilio<br /><strong>${domicilio}</strong></p>				
						</c:if>		
						
					</td>
					</tr>
				</table>
			</div>
		</div>

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
