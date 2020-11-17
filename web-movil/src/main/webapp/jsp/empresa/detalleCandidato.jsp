<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="mx.gob.stps.portal.core.candidate.vo.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
	InformacionGeneralVO detalle = (InformacionGeneralVO) request.getAttribute("detalle");
	String context = request.getContextPath() + "/";
	long idCandidato = (Long) request.getSession().getAttribute("idCandidato");
	long idOferta = (Long) request.getSession().getAttribute("idOferta");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
	<jsp:include page="../seguridad/header.jsp"/>

<script type="text/javascript">

 		//dojo.require("dijit.dijit");
	 	dojo.require("dojox.mobile.parser"); // Load the widget parser
	 	dojo.require("dojox.mobile"); // Load the base lib
	 	dojo.require("dojox.mobile.TabBar");
	 	dojo.require("dojox.mobile.TabContainer");
	 	dojo.requireIf(!dojo.isWebKit, "dojox.mobile.compat"); // If not a WebKit-based client, load compat
		dojo.require("dijit.form.Form");
		dojo.require("dojox.mobile.TabBar");
		dojo.require("dojox.mobile.compat");
		//dojo.require("dojox.mobile.TabBarButton");
	
		
		function doSubmit(method){
			
			
			document.busquedaCandidatos.action="${context}entrevistaProgramada.m?method="+method;
			
			
		}
		function toCurriculum(){
			var viewresult = dijit.byId('curriculoView');
			viewresult.show();
		}
		
		function doSubmitCarpeta(method){
			
			document.busquedaCandidatos.action="${context}buscarCandidatosEmpresa.m?method="+method;
			
			
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

	<!-- application will go here -->


<div id="detallecandidatoview" dojoType="dojox.mobile.View" selected="true">
	<form id="busquedaCandidatos" name="busquedaCandidatos" method="post"
		action="${context}buscarCandidatosEmpresa.m?method=busquedaCandidatos" >
		<input type="hidden" id="tituloOferta" name="tituloOferta" value="${tituloOferta}">
		<input type="hidden" id="nombreCandidato" name="nombreCandidato" value="${detalle.nombre} ${detalle.apellido1} ${detalle.apellido2 }">
		<input type="hidden" id="idCandidato" name="idCandidato" value="<%=idCandidato%>">
		<input type="hidden" id="idOferta" name="idOferta" value="<%=idOferta%>">
		<input type="hidden" id="idc" name="idc" value="<%=idCandidato%>">
		<input type="hidden" id="compatibilidad" name="compatibilidad" value="${compatibilidadCandidato}">
		
		<h1 dojoType="dojox.mobile.Heading">
				<div dojoType="dojox.mobile.ToolBarButton" icon="images/ico_rwd.jpg" moveTo="bar" href="<%=request.getContextPath()%>/buscarCandidatosEmpresa.m?method=busquedaCandidatosOferta" transitionDir="-1"></div>
				<div dojoType="dojox.mobile.ToolBarButton" icon="images/ico_exit.jpg" style="width:45px;float:right;" href="logout.m" transitionDir="-1"></div>
			</h1>
			
			<!--<h2 dojoType="dojox.mobile.RoundRectCategory">Espacio para
				empresas</h2>
			<div dojoType="dojox.mobile.RoundRect" >
				<div class="mblVariableHeight" style="font-size: small;">
					<jsp:include page="/empresaEspacio.m" flush="true">
						<jsp:param name="method" value="detalleEmpresa" />
					</jsp:include>
				</div>
			</div>  -->
			

		
		<div dojoType="dojox.mobile.RoundRect" >
				<div class="mblVariableHeight" style="font-size: 10px;">
					<table cellpadding="0" cellspacing="0" width="100%">
					<tr>
						<td><img src="${context}imageAction.m?method=fotoCandidato&idCandidato=<%=idCandidato%>" width="100px" height="100px" alt="Foto Candidato" >
						</td>
						<td>&nbsp;</td>
						<td><font color="#516814"> ${detalle.nombre} ${detalle.apellido1} ${detalle.apellido2 }</font><br>
						<strong>Edad:</strong>${detalle.edadActual}<br>
            <strong>Tel&eacute;fono:</strong> ${detalle.acceso} - ${detalle.clave} - ${detalle.telefono} <c:if test="${not empty detalle.extension}"> ext. ${detalle.extension}</c:if><br>
           <strong> Correo electr&oacute;nico:</strong>${detalle.correoElectronico}<br>
            <strong>Direcci&oacute;n:</strong>
                     	${detalle.nombreEntidad} ${detalle.nombreMunicipio}
       				${detalle.nombreColonia} <c:if test="${not empty detalle.codigoPostal}"> c.p. ${detalle.codigoPostal}</c:if>
      			<br /> 
           
            <strong>Disponibilidad para viajar:</strong>
            	<%
            	if(detalle.getDisponibilidadViajar()==1){
            		out.print("No");
            	} else {
            		out.print("S&iacute;");
            	}
            	%>
            	<br />				
                  
            <strong>Disponibilidad para radicar fuera:
				</strong>
            	<%
            	if(detalle.getDisponibilidadRadicar()==1){
            		out.print("No");
            	} else {
            		out.print("S&iacute;");
            	}
            	%>				
				
				<br /> 
            <strong>Estatus:
				</strong>${detalle.descEstatus}	<br /> 
            </td>
					</tr>
					<tr>
					<td align="center" colspan="3">
					<ul dojoType="dojox.mobile.RoundRectList">
			<li dojoType="dojox.mobile.ListItem" 
				 moveTo="curriculoView" transition="slide">&nbsp;&nbsp;Ver CV completo</li>
					
					</ul>
					</td>
					</tr>
					
				</table>
					
				</div>
			</div>

			
			<div dojoType="dojox.mobile.RoundRect" >
			<div class="mblVariableHeight" style="font-size: 10px;">
			
				<table cellpadding="0" cellspacing="0" width="100%">
				<tr>
				<td class="titNarSeccion">Formaci&oacute;n profesional</td>
				</tr>
				</table>
				<br>
					<table cellpadding="0" cellspacing="0" width="100%">
					<tr>
						<td><strong>Grado de estudios:</strong> <%=detalle.getGradoEstudios() %><br>
	                <strong>Carrera o especialidad:</strong> <%=detalle.getCarreraEspecialidad() %><br>
	                 <!--<strong>Escuela de procedencia:</strong> <%=detalle.getEscuela() %><br>
	                 <strong>Conocimientos: </strong>
						<% for (ConocimientoHabilidadVO ch : detalle.getConocimientos()) {%>
		            		<%=ch.getConocimientoHabilidad() %>&nbsp;
						<%}%><br>
	                <strong>Habilidades:</strong>
						<% for (ConocimientoHabilidadVO ch : detalle.getHabilidades()) {%>
	            			<%=ch.getConocimientoHabilidad() %>&nbsp;
						<%}%>-->
						<c:if test="${not empty detalle.conocimientoComputacionVO}">
					<strong>Conocimientos en computaci&oacute;n:</strong>
				<c:if test="${detalle.conocimientoComputacionVO.procesadorTxt==1}">Procesador de textos&nbsp;</c:if>
				<c:if test="${detalle.conocimientoComputacionVO.hojaCalculo==1}">Hojas de c&aacute;lculo&nbsp;</c:if>
				<c:if test="${detalle.conocimientoComputacionVO.internet==1}">Internet y correo electr&oacute;nico&nbsp;</c:if>
				<c:if test="${detalle.conocimientoComputacionVO.redesSociales==1}">Redes sociales&nbsp;</c:if>
				<c:if test="${not empty detalle.conocimientoComputacionVO.otros}">${detalle.conocimientoComputacionVO.otros}</c:if>
			
			</c:if><br/>
			
			
						</td>
					</tr>
				</table>
				</div>
			</div>
			
			
			<div dojoType="dojox.mobile.RoundRect" >
			<div class="mblVariableHeight" style="font-size: 10px;">
				<table cellpadding="0" cellspacing="0" width="100%">
				<tr>
				<td class="titNarSeccion">Experiencia relevante
				</td>
				</tr>
				</table>
				<br>
					<table cellpadding="0" cellspacing="0" width="100%">
					<tr>
						<td> <strong>A&ntilde;os de experiencia en el puesto solicitado:</strong> ${detalle.aniosExperencia}<br/>
	       					 <strong>Experiencia:</strong> ${detalle.experiencia}<br/></td>
					</tr>
				</table>
				</div>
			</div>

		
	
		<c:if test="${idOfertaExiste}">
			<div dojoType="dojox.mobile.RoundRect" >
				<div class="mblVariableHeight" style="font-size: 10px; text-align: center;">
					<p><strong>${detalle.nombre} ${detalle.apellido1} ${detalle.apellido2}</strong> tiene <br /> 
				   				<span id="porcentaje">${compatibilidadCandidato}%</span><br/>
				   				de compatibilidad con la oferta<br/>
				   				${tituloOferta}
							</p>
						
				</div>
				
			</div>
			<div id="listaAcciones" dojoType="dojox.mobile.View" selected="true" style="text-align: center;">
			<button id="btn1" dojoType="dojox.mobile.Button" style="width:200px" onclick="javascript:doSubmitCarpeta('asociarCandidatoOferta');">Agregar a mi carpeta empresarial</button>
			<c:if test="${!idEntrevistaExiste}">
			<button id="btn2" dojoType="dojox.mobile.Button" style="width:200px" onclick="javascript:doSubmit('inicioProgramarEntrevista');">Programar entrevista</button>
			</c:if>
			</div>
	
	
		</c:if>
		
	

	</form>
</div>
<div id="bar" dojoType="dojox.mobile.View"></div>
			<!-- SECCION - CURRICULO -->
		<div id="curriculoView" dojoType="dojox.mobile.View">

			<h1 dojoType="dojox.mobile.Heading">
				<div dojoType="dojox.mobile.ToolBarButton" icon="images/ico_rwd.jpg" moveTo="detallecandidatoview" transitionDir="-1"></div>
				<div dojoType="dojox.mobile.ToolBarButton" icon="images/ico_exit.jpg" style="width:45px;float:right;" href="logout.m" transitionDir="-1"></div>
			</h1>

			
	<c:if test="${not empty buscarCandidatosEmpresaForm.perfilLaboral}">
			<div dojoType="dojox.mobile.RoundRect">
				<div  class="titNarSeccion">Datos Personales</div>
				<div class="mblVariableHeight" style="font-size: 10px;">

				
					<table cellpadding="0" cellspacing="0">
					<tr>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>
						                                        ${buscarCandidatosEmpresaForm.perfilLaboral.nombre}  ${buscarCandidatosEmpresaForm.perfilLaboral.apellido1}  ${buscarCandidatosEmpresaForm.perfilLaboral.apellido2}<br/>
						<br/>
						<b>CURP:</b>                            ${buscarCandidatosEmpresaForm.perfilLaboral.curp}<br/>
						<b>Sexo:</b>                            ${buscarCandidatosEmpresaForm.perfilLaboral.genero}<br/>
						<b>Edad:</b>                            ${buscarCandidatosEmpresaForm.perfilLaboral.edad} años<br/>
						<b>Fecha de nacimiento:</b>            	${buscarCandidatosEmpresaForm.perfilLaboral.formattedFechaNacimiento}<br/>
						<b>Lugar de nacimiento:</b>             ${buscarCandidatosEmpresaForm.perfilLaboral.entidadNacimiento} <br/>
						</table>
				

				</div>
			</div>
		<div dojoType="dojox.mobile.RoundRect">
			<div  class="titNarSeccion">Datos actuales</div>
				<div class="mblVariableHeight" style="font-size: 10px;">
							<table cellpadding="0" cellspacing="0">
					<tr>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>
						<div  class="titNarSeccion">Domicilio</div>
						<b>Dirección:</b>&nbsp;${buscarCandidatosEmpresaForm.perfilLaboral.calle} ${buscarCandidatosEmpresaForm.perfilLaboral.numeroExterior} <c:if test="${not empty buscarCandidatosEmpresaForm.perfilLaboral.numeroExterior}">${buscarCandidatosEmpresaForm.perfilLaboral.numeroExterior}</c:if> ${buscarCandidatosEmpresaForm.perfilLaboral.colonia}<br/>
						<c:if test="${not empty buscarCandidatosEmpresaForm.perfilLaboral.calle}">Entre ${buscarCandidatosEmpresaForm.perfilLaboral.calle}</c:if><c:if test="${not empty buscarCandidatosEmpresaForm.perfilLaboral.yCalle}">Y ${buscarCandidatosEmpresaForm.perfilLaboral.yCalle}</c:if><br/>
						<b>Código postal:</b>                   &nbsp;${buscarCandidatosEmpresaForm.perfilLaboral.codigoPostal}<br/>
						<b>Municipio o delegación:</b>          &nbsp;${buscarCandidatosEmpresaForm.perfilLaboral.municipio}<br/>
						<b>Entidad:</b>                         &nbsp;${buscarCandidatosEmpresaForm.perfilLaboral.entidad}<br/>
						<div  class="titNarSeccion">Datos para contactar</div>
						<b>Tel&eacute;fono:</b>&nbsp;<c:if test="${not empty buscarCandidatosEmpresaForm.perfilLaboral.principal}">
								                                ${buscarCandidatosEmpresaForm.perfilLaboral.principal.acceso}
								                                &nbsp;-&nbsp;
								                                ${buscarCandidatosEmpresaForm.perfilLaboral.principal.clave}
								                                &nbsp;-&nbsp;
								                                ${buscarCandidatosEmpresaForm.perfilLaboral.principal.telefono}
								                                &nbsp;
								                                ${buscarCandidatosEmpresaForm.perfilLaboral.principal.extension}
								</c:if><br/>
						<b>Correo electrónico:</b>				&nbsp;${buscarCandidatosEmpresaForm.perfilLaboral.correoElectronico}
							
						<br/>
						</td>
					</tr>
				</table>
			</div>
		</div>
		<div dojoType="dojox.mobile.RoundRect">
			<div  class="titNarSeccion">Escolaridad</div>
				<div class="mblVariableHeight" style="font-size: 10px;">
							<table cellpadding="0" cellspacing="0">
					<tr>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>
						<b>Ultimo grado de estudios:</b>               &nbsp;${buscarCandidatosEmpresaForm.perfilLaboral.gradoPrincipal.nivel}<br/>
						<b>Carrera o especialidad:</b>          &nbsp;${buscarCandidatosEmpresaForm.perfilLaboral.gradoPrincipal.carrera}<br/>
						<b>Situación académica:</b>             &nbsp;${buscarCandidatosEmpresaForm.perfilLaboral.gradoPrincipal.situacion}<br/>
						<div  class="titNarSeccion">Idiomas</div>
						<b>Idioma adicional al espa&ntilde;ol:</b>                          &nbsp;${buscarCandidatosEmpresaForm.perfilLaboral.idiomaPrincipal.idioma}<br/>
						<b>Certificación:</b>                   &nbsp;${buscarCandidatosEmpresaForm.perfilLaboral.idiomaPrincipal.certificacion}<br/>
						<b>Dominio:</b>                         &nbsp;${buscarCandidatosEmpresaForm.perfilLaboral.idiomaPrincipal.dominio}<br/>
						<div  class="titNarSeccion">Conocimientos en computación</div>
						<b>Conocimientos en computación:</b>       	&nbsp;<c:if test="${buscarCandidatosEmpresaForm.conocimientoComputacion.procesadorTxt==1}">Procesador de textos&nbsp;</c:if>
																	<c:if test="${buscarCandidatosEmpresaForm.conocimientoComputacion.hojaCalculo==1}">Hojas de c&aacute;lculo&nbsp;</c:if>
																	<c:if test="${buscarCandidatosEmpresaForm.conocimientoComputacion.internet==1}">Internet y correo electr&oacute;nico&nbsp;</c:if>
																	<c:if test="${buscarCandidatosEmpresaForm.conocimientoComputacion.redesSociales==1}">Redes sociales&nbsp;</c:if><br/>
						<b>Otros conocimientos en computación:</b>&nbsp;<c:if test="${not empty buscarCandidatosEmpresaForm.conocimientoComputacion.otros}">${buscarCandidatosEmpresaForm.conocimientoComputacion.otros}</c:if><br/>
						</td>
					</tr>
				</table>
			</div>
		</div>
		<div dojoType="dojox.mobile.RoundRect">
			<div  class="titNarSeccion">Experiencia y expectativas laborales</div>
				<div class="mblVariableHeight" style="font-size: 10px;">
							<table cellpadding="0" cellspacing="0">
					<tr>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>
						<div  class="titNarSeccion">Puesto Solicitado</div>
						<b>Puesto solicitado:</b>                &nbsp;${buscarCandidatosEmpresaForm.perfilLaboral.expectativaPrincipal.ocupacionDeseada}<br/>
						<b>Experiencia en el puesto:</b>         &nbsp;${buscarCandidatosEmpresaForm.perfilLaboral.perfilLaboral.experiencia}<br/>
						<b>Experiencia total:</b>               &nbsp;${buscarCandidatosEmpresaForm.perfilLaboral.aniosExperiencia}<br/>
						<div  class="titNarSeccion">Expectativas laborales</div>
						<b>Salario pretendido:</b>               &nbsp;<fmt:setLocale value="en_US"/>  <fmt:formatNumber type="CURRENCY" value="${buscarCandidatosEmpresaForm.perfilLaboral.expectativaPrincipal.salarioPretendido}"/><br/>
						<b>Tipo de empleo deseado:</b>               &nbsp;${buscarCandidatosEmpresaForm.perfilLaboral.expectativaPrincipal.tipoEmpleoDeseado}<br/>
						<b>Tipo de contrato deseado:</b>               &nbsp;${buscarCandidatosEmpresaForm.perfilLaboral.expectativaPrincipal.tipoContrato}<br/>
						<b>Disponibilidad para viajar:</b>      &nbsp;<c:if test="${buscarCandidatosEmpresaForm.perfilLaboral.perfilLaboral.disponibilidadViajar==1}">No</c:if><c:if test="${buscarCandidatosEmpresaForm.perfilLaboral.perfilLaboral.disponibilidadViajar==2}">Si</c:if><br/>
						<b>Disponibilidad para radicar fuera:</b>&nbsp;<c:if test="${buscarCandidatosEmpresaForm.perfilLaboral.perfilLaboral.disponibilidadRadicar==1}">No</c:if><c:if test="${buscarCandidatosEmpresaForm.perfilLaboral.perfilLaboral.disponibilidadRadicar==2}">Si</c:if><br/>
						</td>
					</tr>
				</table>
			</div>
		</div>
						
						
						
						
					
</c:if>
		</div>
	<c:if test="${not empty msg}">
	<script type="text/javascript">
	alert('${msg}');
	</script>
	<%request.getSession().removeAttribute("msg"); %>
	</c:if>	
</body>
</html>


