<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="mx.gob.stps.portal.web.candidate.vo.CandidatoAjaxVO"%>
<%	
String invoke = (null != request.getSession().getAttribute("_urlpageinvoke") ? (String)request.getSession().getAttribute("_urlpageinvoke") : "javascript:history.go(-1)");
%>
<%
	CandidatoAjaxVO candidato= (CandidatoAjaxVO)request.getSession().getAttribute("candidatoheader");
     boolean inscritoPPC = (candidato.getPpcEstatusIdOpcion().equals("59") || candidato.getPpcEstatusIdOpcion().equals("60")) ? true:false;
%>
<div class="PopAlert">

	<p align ="center"><strong>Tus datos han sido enviados correctamente a la empresa</strong></p><br/>
	<p align ="center">&Eacute;sta te notificar&aacute; si eres candidato para la oferta de empleo:</p><br/>
	<p align ="center"><strong>
		<c:out value="${OfferQuestionForm.ofertaJB.tituloOferta}" />
	</strong></p><br/><br/>
	
	<p align ="center"><strong>Datos de contacto con la empresa</strong></p><br/>
	
	<div class="datosContac">	
	
		<p align ="center"><strong>Persona de contacto: </strong>
			<c:out value="${nombreContacto}" />
		</p><br/>
		
		<c:if test="${not empty cargoContacto and cargoContacto ne ''}">
			<p align ="center"><strong>Cargo: </strong>
			<c:out value="${cargoContacto}" />	
			</p><br/>
		</c:if>
		
		<c:if test="${!empty OfferQuestionForm.ofertaJB.idContactoCorreo and OfferQuestionForm.ofertaJB.idContactoCorreo eq 2}">
			<p align ="center"><strong>Correo electr&oacute;nico: </strong>
			<c:out value="${correoElectronico}" />
			</p><br/>		
		</c:if>		
		
		<c:if test="${!empty OfferQuestionForm.ofertaJB.idContactoTel and OfferQuestionForm.ofertaJB.idContactoTel eq 2}">		
			<p align ="center"><strong>Tel&eacute;fono(s): </strong>
			<c:forEach var="row" items="${OfferQuestionForm.ofertaJB.telefonos}" varStatus="rowCounter">
				<c:if test="${rowCounter.count != 1}">
					<c:out value=", " />
				</c:if>	
				<c:out value="${row.telefono}" />
			</c:forEach>
			</p><br/>
		</c:if>
		
		<c:if test="${!empty OfferQuestionForm.ofertaJB.idContactoDomicilio and OfferQuestionForm.ofertaJB.idContactoDomicilio eq 2}">	
			<p align ="center"><strong>Domicilio: </strong>
			<c:out value="${domicilio}" />		
			</p><br/><br/><br/>
		</c:if>
		
	</div>
	<!--INI_JGLC_PPC-->
<%-- 	<%if (inscritoPPC){%> --%>
<!-- 	<p><strong>Recuerda que tienes 9 d&iacute;as para dar seguimiento a esta postulaci&oacute;n de lo contrario estar&aacute;s incumpliendo con el Programa  -->
<!-- 				de Promoci&oacute;n y Colocaci&oacute;n del Seguro de Desempleo (PPC-SD).</strong></p><br/> -->
<%-- 	<%}%> --%>
	<!--FIN_JGLC_PPC-->

	<form id="opost" name="opost" action="<%=invoke%>" method="post">
		<p align ="center"><input type="submit" id="cancelPost" value="Regresar" class="boton" /></p>
	</form>

</div>
