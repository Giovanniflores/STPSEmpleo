<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
    <head>
 	<jsp:include page="../seguridad/header.jsp"/>
 	
 	<script type="text/javascript">
	 	dojo.require("dojox.mobile.parser"); // Load the widget parser
	 	dojo.require("dojox.mobile"); // Load the base lib
	 	dojo.requireIf(!dojo.isWebKit, "dojox.mobile.compat"); // If not a WebKit-based client, load compat
		dojo.require("dojox.mobile.TabBar");
	 	
		/*dojo.addOnLoad(function() {
			dijit.byId('ofertasrecomendadas').wakeUp();
			dijit.byId('ofertasrecomendadas').show();
		});*/

	 	function doSubmitCVMaker(method) {
	 		dojo.xhrGet({url: 'candidatoEspacio.m?method=generaCV'});
	 	}

	 	function doAceptaEntrevista(idEntrevista,oferta,candidato,fecha,hora,idCandidato,razonSocial){
			document.entrevistaEmpresaForm.method.value="modificarEstatusEntrevista";
			document.entrevistaEmpresaForm.idEntrevista.value=idEntrevista;
			document.entrevistaEmpresaForm.tituloOferta.value=oferta;
			document.entrevistaEmpresaForm.nombreCandidato.value=candidato;
			document.entrevistaEmpresaForm.fechaEntrevista.value=fecha;
			document.entrevistaEmpresaForm.horaEntrevista.value=hora;
			document.entrevistaEmpresaForm.idCandidato.value=idCandidato;
			document.entrevistaEmpresaForm.razonSocial.value=razonSocial;
			document.entrevistaEmpresaForm.submit();
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

		<div id="miespaciocand" dojoType="dojox.mobile.View" selected="true">
			
			<h1 dojoType="dojox.mobile.Heading">
				<div dojoType="dojox.mobile.ToolBarButton" icon="images/ico_exit.jpg" style="width:45px;float:right;" href="logout.m" transitionDir="-1"></div>
			</h1>
			<%--
	    	<h2 dojoType="dojox.mobile.RoundRectCategory">Espacio para Candidato</h2>
			--%>
			<jsp:include page="../oferta/ocupate.jsp"/>

	    	<div dojoType="dojox.mobile.RoundRect">
				<div class="mblVariableHeight" style="font-size: small;"> 
					<jsp:include page="/candidatoEspacio.m" flush="true">
				 		<jsp:param name="method" value="datosCandidato" />
					</jsp:include>
				</div>
	    	</div>

			<div id="listaAcciones" dojoType="dojox.mobile.View" selected="true">
				<ul dojoType="dojox.mobile.RoundRectList">
					<%--
					<li dojoType="dojox.mobile.ListItem">
					${preview}
					</li>--%>
					<li dojoType="dojox.mobile.ListItem" moveTo="ofertasrecomendadas">
					Ofertas recomendadas&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(<c:out value="${fn:length(candidatoForm.ofertas)}"/>)
					</li>
					<li dojoType="dojox.mobile.ListItem" moveTo="misofertas">
					Mis Ofertas de Empleo&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(<c:out value="${fn:length(candidatoForm.misOfertas)}"/>)
					</li>
					<li dojoType="dojox.mobile.ListItem" moveTo="misempresas">
					Empresas que me buscan&nbsp;&nbsp;(<c:out value="${fn:length(candidatoForm.empresas)}"/>)
					</li>
					<li dojoType="dojox.mobile.ListItem" moveTo="entrevistas">
					Entrevistas Programadas&nbsp;&nbsp;&nbsp;(<c:out value="${fn:length(candidatoForm.entrevistas)}"/>)
					</li>
					<%--<li dojoType="dojox.mobile.ListItem" onclick="javascript:doSubmitCVMaker();">--%>
					<li dojoType="dojox.mobile.ListItem" moveTo="curriculoView">
					Consulta de Curr&iacute;culo
					</li>
				</ul>
			</div>	
		</div>
	
	 	<div id="bar" dojoType="dojox.mobile.View"></div>


		<!-- SECCION - OFERTAS RECOMENDADAS -->
		<div id="ofertasrecomendadas" dojoType="dojox.mobile.View">
			<h1 dojoType="dojox.mobile.Heading">
				<div dojoType="dojox.mobile.ToolBarButton" icon="images/ico_home.jpg" moveTo="miespaciocand" transitionDir="-1"></div>
				<div dojoType="dojox.mobile.ToolBarButton" icon="images/ico_exit.jpg" style="width:45px;float:right;" href="logout.m" transitionDir="-1"></div>
			</h1>

			<jsp:include page="../oferta/ocupate.jsp"/>

			<c:if test="${not empty candidatoForm.ofertas}">
				<ul dojoType="dojox.mobile.RoundRectList" >
					<div style="text-align: center;" class="titNar">Ofertas recomendadas</div>

					<c:forEach var="oferta" items="${candidatoForm.ofertas}">
					<li class="mblVariableHeight" dojoType="dojox.mobile.ListItem" moveTo="bar" transitionDir="1"
					    href="detalleoferta.m?method=init&preview=ofertasrecomendadas&idOfertaEmpleo=${oferta.idOfertaEmpleo}">
	
						<a class="titNarSeccion">${oferta.tituloOferta}</a><br/>
						${oferta.ubicacion}<br/>
						${oferta.empresa}
					</li>
					</c:forEach>
				</ul>
			</c:if>
			<c:if test="${empty candidatoForm.ofertas}">
		    	<div dojoType="dojox.mobile.RoundRect" >
					<div style="text-align: center;" class="titNar">Ofertas recomendadas</div>
					<div class="mblVariableHeight" style="font-size: small;">
					Actualmente no hay ofertas de empleo recomendadas.
					</div>
					<br/>
		    	</div>
			</c:if>

		</div>

		<!-- SECCION - MIS OFERTAS -->
		<div id="misofertas" dojoType="dojox.mobile.View">
			<h1 dojoType="dojox.mobile.Heading">
				<div dojoType="dojox.mobile.ToolBarButton" icon="images/ico_home.jpg" moveTo="miespaciocand" transitionDir="-1"></div>
				<div dojoType="dojox.mobile.ToolBarButton" icon="images/ico_exit.jpg" style="width:45px;float:right;" href="logout.m" transitionDir="-1"></div>
			</h1>

			<jsp:include page="../oferta/ocupate.jsp"/>
			
			<c:if test="${not empty candidatoForm.misOfertas}">
			<ul dojoType="dojox.mobile.RoundRectList" >
				<div style="text-align: center;" class="titNar">Mis ofertas de empleo</div>
			
				<c:forEach var="oferta" items="${candidatoForm.misOfertas}">
				<li class="mblVariableHeight" dojoType="dojox.mobile.ListItem" style="font-size: 10px;" moveTo="bar" transitionDir="1"
				    href="detalleoferta.m?method=init&preview=misofertas&idOfertaEmpleo=${oferta.idOfertaEmpleo}">
						<c:choose>
							<c:when test="${ofertas.estatus eq estatusActivoIdOpcion}">
								<c:set var="status" value="activa"/>
							</c:when>
							<c:otherwise>
									<c:set var="status" value="inactiva"/>
							</c:otherwise>
						</c:choose>
						<a class="titNarSeccion">${oferta.tituloOferta}</a><br/>
						<c:out value="${status}"/><br/>
						${oferta.ubicacion}<br/>
						${oferta.empresaNombre}<br/>
						${oferta.fechaFin}<br/>
						${oferta.estatusOfertaCandidato}
				</li>
				</c:forEach>
			</ul>
			</c:if>
			<c:if test="${empty candidatoForm.misOfertas}">
		    	<div dojoType="dojox.mobile.RoundRect" >
		    		<div style="text-align: center;" class="titNar">Mis ofertas de empleo</div>
					<div class="mblVariableHeight" style="font-size: small;">
						No tiene ofertas de empleo seleccionadas o dónde se haya postulado.
					</div>
					<br/>
		    	</div>
			</c:if>

		</div>

		<!-- SECCION - EMPRESAS ME BUSCAN -->
		<div id="misempresas" dojoType="dojox.mobile.View">
			<h1 dojoType="dojox.mobile.Heading">
				<div dojoType="dojox.mobile.ToolBarButton" icon="images/ico_home.jpg" moveTo="miespaciocand" transitionDir="-1"></div>
				<div dojoType="dojox.mobile.ToolBarButton" icon="images/ico_exit.jpg" style="width:45px;float:right;" href="logout.m" transitionDir="-1"></div>
			</h1>

			<jsp:include page="../oferta/ocupate.jsp"/>

			<c:if test="${not empty candidatoForm.empresas}">
				<ul dojoType="dojox.mobile.RoundRectList" >
					<div style="text-align: center;" class="titNar">Empresas que me buscan</div>

					<c:forEach var="empresa" items="${candidatoForm.empresas}">
					<li class="mblVariableHeight" dojoType="dojox.mobile.ListItem" style="font-size: 10px;" moveTo="bar" transitionDir="1"
				    	href="detalleoferta.m?method=init&preview=misempresas&idOfertaEmpleo=${empresa.idOfertaEmpleo}">

						<a class="titNarSeccion">${empresa.tituloOferta}</a><br/>
						${empresa.ubicacion}<br/>
						${empresa.empresaNombre}<br/>
						${empresa.fechaFin}<br/>
						${empresa.estatusOfertaCandidato}
					</li>
				</c:forEach>
				</ul>
			</c:if>
			<c:if test="${empty candidatoForm.empresas}">
		    	<div dojoType="dojox.mobile.RoundRect" >
		    		<div style="text-align: center;" class="titNar">Empresas que me buscan</div>
					<div class="mblVariableHeight" style="font-size: small;">
						No tiene ofertas de empleo de empresas que lo hayan vinculado.
					</div>
					<br/>
		    	</div>
			</c:if>

		</div>

		<!-- SECCION - ENTREVISTAS -->
		<div id="entrevistas" dojoType="dojox.mobile.View">

			<h1 dojoType="dojox.mobile.Heading">
				<div dojoType="dojox.mobile.ToolBarButton" icon="images/ico_home.jpg" moveTo="miespaciocand" transitionDir="-1"></div>
				<div dojoType="dojox.mobile.ToolBarButton" icon="images/ico_exit.jpg" style="width:45px;float:right;" href="logout.m" transitionDir="-1"></div>
			</h1>

			<jsp:include page="../oferta/ocupate.jsp"/>

			<c:if test="${not empty candidatoForm.entrevistas}">
				<ul dojoType="dojox.mobile.RoundRectList" >
					<div style="text-align: center;" class="titNar">Entrevista en línea</div>
				
					<c:forEach var="entrevista" items="${candidatoForm.entrevistas}">
						<c:if test="${not entrevista.puedeAceptarEntrevista}">
						<li class="mblVariableHeight" dojoType="dojox.mobile.ListItem" style="font-size: 10px;" >
								Oferta: <a class="titNarSeccion">${entrevista.tituloOferta}</a><br/>
								Empresa: ${entrevista.razonSocial}<br>
								Candidato: ${entrevista.nombre}<br>
								Contacto: ${entrevista.contactoEmpresa}<br>
								Fecha: ${entrevista.fechaString}&nbsp;&nbsp;Hora: ${entrevista.hora}<br>
								Estatus: ${entrevista.estatusDesc}
						</li>
						</c:if>
						<c:if test="${entrevista.puedeAceptarEntrevista}">
						<li class="mblVariableHeight" dojoType="dojox.mobile.ListItem" moveTo="bar" style="font-size: 10px;"
						    href="javascript:doAceptaEntrevista(${entrevista.idEntrevista},'${entrevista.tituloOferta}','${entrevista.nombre}','${entrevista.fechaString}','${entrevista.hora}',${entrevista.idCandidato},'${entrevista.razonSocial}');">
								Oferta: <a class="titNarSeccion">${entrevista.tituloOferta}</a><br/>
								Empresa: ${entrevista.razonSocial}<br>
								Candidato: ${entrevista.nombre}<br>
								Contacto: ${entrevista.contactoEmpresa}<br>
								Fecha: ${entrevista.fechaString}&nbsp;&nbsp;Hora: ${entrevista.hora}<br>
								Estatus: ${entrevista.estatusDesc}
						
						</li>
						</c:if>
					</c:forEach>
				</ul>
			</c:if>
		
			<c:if test="${empty candidatoForm.entrevistas}">
		    	<div dojoType="dojox.mobile.RoundRect" >
		    		<div style="text-align: center;" class="titNar">Entrevista en línea</div>
					<div class="mblVariableHeight" style="font-size: small;">
						No tienes ninguna entrevista en l&iacute;nea.
					</div>
					<br/>
		    	</div>
			</c:if>
		
			<form id="entrevistaEmpresaForm" name="entrevistaEmpresaForm" method="post" action="entrevistaProgramada.m">
				<input type="hidden" id="method" name="method" value="init">
				<input type="hidden" id="idEntrevista" name="idEntrevista" value="">
				<input type="hidden" id="nombreCandidato" name="nombreCandidato" value="">
				<input type="hidden" id="tituloOferta" name="tituloOferta" value="">
				<input type="hidden" id="fechaEntrevista" name="fechaEntrevista" value="">
				<input type="hidden" id="horaEntrevista" name="horaEntrevista" value="">
				<input type="hidden" id="idCandidato" name="idCandidato" value="">
				<input type="hidden" id="razonSocial" name="razonSocial" value="">
			</form>
		
		</div>

		<!-- SECCION - CURRICULO -->
		<div id="curriculoView" dojoType="dojox.mobile.View">

			<h1 dojoType="dojox.mobile.Heading">
				<div dojoType="dojox.mobile.ToolBarButton" icon="images/ico_home.jpg" moveTo="miespaciocand" transitionDir="-1"></div>
				<div dojoType="dojox.mobile.ToolBarButton" icon="images/ico_exit.jpg" style="width:45px;float:right;" href="logout.m" transitionDir="-1"></div>
			</h1>

			<jsp:include page="../oferta/ocupate.jsp"/>

	    	<div dojoType="dojox.mobile.RoundRect">
				<div class="mblVariableHeight" style="font-size: small;"> 
					<jsp:include page="/candidatoEspacio.m" flush="true">
				 		<jsp:param name="method" value="datosCandidato" />
					</jsp:include>
				</div>
	    	</div>
	<c:if test="${not empty candidatoForm.perfilLaboral}">
			<div dojoType="dojox.mobile.RoundRect">
				<div  class="titNarSeccion">Datos Personales</div>
				<div class="mblVariableHeight" style="font-size: 10px;">

				
					<table cellpadding="0" cellspacing="0">
					<tr>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>
						                                        ${candidatoForm.candidato.nombre}  ${candidatoForm.candidato.apellido1}  ${candidatoForm.candidato.apellido2}<br/>
						<br/>
						<b>CURP:</b>                            ${candidatoForm.candidato.curp}<br/>
						<b>Sexo:</b>                            ${candidatoForm.perfilLaboral.genero}<br/>
						<b>Edad:</b>                            ${candidatoForm.candidato.edad} años<br/>
						<b>Fecha de nacimiento:</b>            	${candidatoForm.perfilLaboral.formattedFechaNacimiento}<br/>
						<b>Lugar de nacimiento:</b>             ${candidatoForm.perfilLaboral.entidadNacimiento} <br/>
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
						<b>Dirección:</b>&nbsp;${candidatoForm.perfilLaboral.calle} ${candidatoForm.perfilLaboral.numeroExterior} <c:if test="${not empty candidatoForm.perfilLaboral.numeroInterior}">${candidatoForm.perfilLaboral.numeroInterior}</c:if> ${candidatoForm.perfilLaboral.colonia}<br/>
						<c:if test="${not empty candidatoForm.perfilLaboral.calle}">Entre ${candidatoForm.perfilLaboral.calle}</c:if><c:if test="${not empty candidatoForm.perfilLaboral.yCalle}"> Y ${candidatoForm.perfilLaboral.yCalle}</c:if><br/>
						<b>Código postal:</b>                   &nbsp;${candidatoForm.perfilLaboral.codigoPostal}<br/>
						<b>Municipio o delegación:</b>          &nbsp;${candidatoForm.perfilLaboral.municipio}<br/>
						<b>Entidad:</b>                         &nbsp;${candidatoForm.perfilLaboral.entidad}<br/>
						<div  class="titNarSeccion">Datos para contactar</div>
						<c:if test="${candidatoForm.perfilLaboral.contactoTelefono==2}">
						<b>Tel&eacute;fono:</b>&nbsp;<c:if test="${not empty candidatoForm.perfilLaboral.principal}">
								                                ${candidatoForm.perfilLaboral.principal.acceso}
								                                &nbsp;-&nbsp;
								                                ${candidatoForm.perfilLaboral.principal.clave}
								                                &nbsp;-&nbsp;
								                                ${candidatoForm.perfilLaboral.principal.telefono}
								                                &nbsp;
								                                ${candidatoForm.perfilLaboral.principal.extension}
								</c:if>
								</c:if><br/>
						<c:if test="${candidatoForm.perfilLaboral.contactoCorreo==2}">
						<b>Correo electrónico:</b>				&nbsp;${candidatoForm.perfilLaboral.correoElectronico}
							</c:if>
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
						<b>Ultimo grado de estudios:</b>               &nbsp;${candidatoForm.perfilLaboral.gradoPrincipal.nivel}<br/>
						<b>Carrera o especialidad:</b>          &nbsp;${candidatoForm.perfilLaboral.gradoPrincipal.carrera}<br/>
						<b>Situación académica:</b>             &nbsp;${candidatoForm.perfilLaboral.gradoPrincipal.situacion}<br/>
						<div  class="titNarSeccion">Idiomas</div>
						<b>Idioma adicional al espa&ntilde;ol:</b>                          &nbsp;${candidatoForm.perfilLaboral.idiomaPrincipal.idioma}<br/>
						<b>Certificación:</b>                   &nbsp;${candidatoForm.perfilLaboral.idiomaPrincipal.certificacion}<br/>
						<b>Dominio:</b>                         &nbsp;${candidatoForm.perfilLaboral.idiomaPrincipal.dominio}<br/>
						<div  class="titNarSeccion">Conocimientos en computación</div>
						<b>Conocimientos en computación:</b>       	&nbsp;<c:if test="${candidatoForm.conocimientoComputacion.procesadorTxt==1}">Procesador de textos&nbsp;</c:if>
																	<c:if test="${candidatoForm.conocimientoComputacion.hojaCalculo==1}">Hojas de c&aacute;lculo&nbsp;</c:if>
																	<c:if test="${candidatoForm.conocimientoComputacion.internet==1}">Internet y correo electr&oacute;nico&nbsp;</c:if>
																	<c:if test="${candidatoForm.conocimientoComputacion.redesSociales==1}">Redes sociales&nbsp;</c:if><br/>
						<b>Otros conocimientos en computación:</b>&nbsp;<c:if test="${not empty candidatoForm.conocimientoComputacion.otros}">${candidatoForm.conocimientoComputacion.otros}</c:if><br/>
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
						<b>Puesto solicitado:</b>                &nbsp;${candidatoForm.perfilLaboral.expectativaPrincipal.ocupacionDeseada}<br/>
						<b>Experiencia en el puesto:</b>         &nbsp;${candidatoForm.perfilLaboral.perfilLaboral.experiencia}<br/>
						<b>Experiencia total:</b>               &nbsp;${candidatoForm.perfilLaboral.aniosExperiencia}<br/>
						<div  class="titNarSeccion">Expectativas laborales</div>
						<b>Salario pretendido:</b>               &nbsp;<fmt:setLocale value="en_US"/>  <fmt:formatNumber type="CURRENCY" value="${candidatoForm.perfilLaboral.expectativaPrincipal.salarioPretendido}"/><br/>
						<b>Tipo de empleo deseado:</b>               &nbsp;${candidatoForm.perfilLaboral.expectativaPrincipal.tipoEmpleoDeseado}<br/>
						<b>Tipo de contrato deseado:</b>               &nbsp;${candidatoForm.perfilLaboral.expectativaPrincipal.tipoContrato}<br/>
						<b>Disponibilidad para viajar:</b>      &nbsp;${candidatoForm.perfilDisponibilidadViajar}<br/>
						<b>Disponibilidad para radicar fuera:</b>&nbsp;${candidatoForm.perfilDisponibilidadRadicar}<br/>
						</td>
					</tr>
				</table>
			</div>
		</div>
						
						
						
						
					
</c:if>
		</div>

    </body>
</html>