<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="mx.gob.stps.portal.core.candidate.vo.*" %>
<%@ page import="java.util.Calendar"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String context = request.getContextPath() +"/";

	OfertaCandidatoResumenVo ofertaCandidatoVo = new OfertaCandidatoResumenVo();
	InformacionGeneralVO detalle = (InformacionGeneralVO) request.getAttribute("detalle");
	pageContext.setAttribute("detalle", detalle);

	long idCandidato = ((Long) request.getAttribute("idCandidato")).longValue();
%>

<style>
	.formApp.miEspacio > h2 {display:none;}
</style>

<div class="detalle_oferta">
	<div class="det_index">
		<h2 class="titulo_profesion">Detalle del candidato</h2>

		<!--Se cambio la etiqueta de cierre del div class="ficha_oferta" este debe abarcar el div class="logo_empresa" y el div class="text_ficha_oferta"-->
		<div class="perfil_candidato">
			<div class="user_pic">
				<img src="<%=context%>imageAction.do?method=getImagen&<%="captcha?time="+ Calendar.getInstance().getTimeInMillis()%>&ID_CANDIDATO=<%=idCandidato%>"
					 alt="Foto Candidato" />
			</div>


			<div class="perfil_c emp">
				<div class="perfil_title">
					<h3>${detalle.nombre} ${detalle.apellido1} ${detalle.apellido2}</h3>
					<div class="puesto">
						<ul>
							<li>
								<strong>Puesto(s) solicitado(s):</strong>
								<br>
								${detalle.ocupacionDeseada1}
								<c:if test="${not empty detalle.ocupacionDeseada2}">
									<br>
									${detalle.ocupacionDeseada2}
								</c:if>
							</li>
						</ul>
					</div>
				</div>

				<div class="perfil_index">
					<ul style="margin-right: 20px">
						<c:if test="${detalle.descripcionDiscapacidad != 'Ninguna'}">
							<li>
								<strong>Discapacidad</strong>
								<br>
								<span> ${detalle.descripcionDiscapacidad}</span>
							</li>
						</c:if>


						<c:if test="${detalle.contactoTelefono == 2}">
							<li>
								<strong>Tel&eacute;fono</strong>
								<br>
								<c:forEach var="telefono" items="${detalle.telefonos}" varStatus="rowMeter">
									<span>
										${telefono.acceso} - ${telefono.clave} - ${telefono.telefono} <c:if test="${not empty telefono.extension}"> ext. ${telefono.extension}</c:if>
									</span>
									<br>
								</c:forEach>
							</li>
						</c:if>

						<c:if test="${detalle.contactoCorreo == 2}">
							<li>
								<strong>Correo electr&oacute;nico<br></strong>
								<span>${detalle.correoElectronico}</span>
							</li>
						</c:if>

						<c:if test="${detalle.confidencialidadCandidato == 1}"><%-- 1 -> NO Confidencial --%>
							<li>
								<strong>Direcci&oacute;n</strong>
								<br>
									${detalle.nombreEntidad} ${detalle.nombreMunicipio} ${detalle.nombreColonia} ${detalle.codigoPostal != '' ? 'c.p. '.concat(detalle.codigoPostal) : ''}
							</li>
						</c:if>

					</ul>


					<ul class="conoce_mas">
						<%--<li><strong>Estatus</strong><br><span>${detalle.descEstatus}</span></li>--%>
						<%--<li><strong>Conoce m&aacute;s del candidato</strong></li>--%>

						<li>
							<%--<a class="consultar_mas_vacantes" href="<%=context%>miscandidatos.do?method=genera&idCandidato=<%=idCandidato%>">Ver CV completo</a>--%>
							<c:url var="urlAgregarCand" value="OfertaNavegacion.do">
								<c:param name="method" value="init"/>
								<c:param name="idCandidato" value="${requestScope.idCandidato}"/>
							</c:url>

							<c:if test="${estatusOfertaCandidato == '0'}">
								<input type="button" class="boton" name="btnAgregar" id="btnAgregar"
									   value="Agregar a mis candidatos seleccionados"
									   onclick="document.location='${urlAgregarCand}'"/>
							</c:if>

							<%--<%--%>
							<%--String urlRegresar = "#";--%>
							<%--if (null != request.getSession().getAttribute("_urlpageinvoke")) {--%>
							<%--urlRegresar = (String) request.getSession().getAttribute("_urlpageinvoke");--%>
							<%--}--%>
							<%--%>--%>
							<%--<div class="send_q"><input type="button" class="boton" name="btnRegresar" id="btnRegresar"--%>
							<%--value="Regresar a Mis ofertas de empleo"--%>
							<%--onclick="document.location='<%=urlRegresar%>'"/></div>--%>
						</li>


					</ul>
				</div>
			</div>
			<div class="clearfix"></div>
		</div>


		<div class="offer_index">
			<div class="offer_section" id="infoProf">
				<div class="ficha_relacionada" id="formacion">
					<h3>Formaci&oacute;n profesional</h3>
					<ul>
						<li class="odd">
							<strong>Estudios</strong>
							<br>
							<span>${detalle.gradoEstudios} en ${detalle.carreraEspecialidad}, ${detalle.situacionAcademica}</span>
						</li>

						<c:if test="${not empty detalle.idiomas}">
							<li>
								<strong>Idiomas adicionales al español</strong>
								<ul>
									<c:forEach var="idioma" items="${detalle.idiomas}" varStatus="rowMeter">
										<li>
											${not empty idioma.idioma and  idioma.idioma ne 'Ninguno' ? idioma.idioma : 'Ninguno'}
												<c:if test="${not empty idioma.idioma and idioma.idioma ne 'Ninguno'}">
												. Certificado ${idioma.certificacion}, ${idioma.dominio}
											</c:if>
										</li>
									</c:forEach>
								</ul>
							</li>
						</c:if>

						<c:if test="${not empty detalle.conocimientoComputacionVO}">
							<li class="odd">
								<strong>Conocimientos en computaci&oacute;n:</strong>
								<br/>
								<c:if test="${detalle.conocimientoComputacionVO.procesadorTxt==1}">Procesador de textos&nbsp;</c:if>
								<c:if test="${detalle.conocimientoComputacionVO.hojaCalculo==1}">Hojas de c&aacute;lculo&nbsp;</c:if>
								<c:if test="${detalle.conocimientoComputacionVO.internet==1}">Internet y correo electr&oacute;nico&nbsp;</c:if>
								<c:if test="${detalle.conocimientoComputacionVO.redesSociales==1}">Redes sociales&nbsp;</c:if>
								<c:if test="${not empty detalle.conocimientoComputacionVO.otros}">${detalle.conocimientoComputacionVO.otros}</c:if>
							</li>
						</c:if>

						<c:if test="${not empty detalle.conocimientos}">
							<li>
								<strong>Conocimientos:</strong>
								<ul>
									<c:forEach var="conocimiento" items="${detalle.conocimientos}" varStatus="rowMeter">
										<li>
												${conocimiento.conocimientoHabilidad}, experiencia ${conocimiento.experiencia != null ? conocimiento.experiencia : 'nada'}
										</li>
									</c:forEach>
								</ul>
							</li>
						</c:if>

						<c:if test="${not empty detalle.habilidadesCandidato}">
							<li>
								<strong>Habilidades y actitudes:</strong>
								<br/>
								<c:forEach var="habilidad" items="${detalle.habilidadesCandidato}" varStatus="rowMeter">
									${habilidad.opcion},
								</c:forEach>
							</li>
						</c:if>
					</ul>
				</div>

				<div class="offer_section" id="experiencia">
					<h3>Experiencia relevante</h3>
					<ul>
						<%--<li class="odd"><strong>A&ntilde;os de experiencia en el puesto solicitado:</strong> ${detalle.aniosExperencia}</li>--%>
						<li>
							<strong>A&ntilde;os de experiencia en ${detalle.ocupacionDeseada1}:</strong>
							<br/>
							${detalle.experienciaOcupacion1}
						</li>
						<c:if test="${not empty detalle.ocupacionDeseada2}">
							<li>
								<strong>A&ntilde;os de experiencia en ${detalle.ocupacionDeseada2}:</strong>
								<br/>
									${detalle.experienciaOcupacion2}
							</li>
						</c:if>
						<li>
							<strong>Experiencia:</strong>
							<br/>
							${detalle.experiencia}
						</li>

						<%--<c:if test="${not empty listaEstandares}">--%>
						<%--<c:forEach var="rowListaEstandares" items="${listaEstandares}">--%>
						<%--<li class="odd"><c:out value="${rowListaEstandares.titulo}"/></li>--%>
						<%--</c:forEach>--%>
						<%--</c:if>--%>
					</ul>
				</div>

				<div class="offer_section" id="expectativas">
					<h3>Expectativas laborales</h3>
					<ul>
						<li>
							<strong>Salario mensual pretendido:</strong>
							<br/>
							${detalle.salarioPretendido}
						</li>
						<li>
							<strong>Disponibilidad para viajar:</strong>
							<br/>
							<c:if test="${detalle.disponibilidadViajar==1}">No</c:if>
							<c:if test="${detalle.disponibilidadViajar!=1}">S&iacute;</c:if>
						</li>
						<li>
							<strong>Disponibilidad para radicar fuera:</strong>
							<br/>
							<c:if test="${detalle.disponibilidadRadicar==1}">No</c:if>
							<c:if test="${detalle.disponibilidadRadicar!=1}">S&iacute;</c:if>
						</li>
					</ul>
				</div>

				<div class="pdf_dwn">
					<a class="consultar_mas_vacantes" href="<%=context%>miscandidatos.do?method=genera&idCandidato=<%=idCandidato%>">Descargar CV completo en PDF</a>
				</div>
   
				<div>
					<%--<p class="ligas_consulta" style="color: #EE6000 !important;">--%>
					<%--<a href="javascript:history.go(-1);" class="regresar_a_resultado">Regresar</a>--%>
					<%--<a href="<%//=context%>miscandidatos.do?method=genera&idCandidato=<%//=idCandidato%>" class="regresar_a_resultado">Ver CV completo</a>--%>

					<div>
						<c:url var="urlAgregarCand" value="OfertaNavegacion.do">
							<c:param name="method" value="init"/>
							<c:param name="idCandidato" value="${requestScope.idCandidato}"/>
						</c:url>
						<a class="consultar_mas_vacantes" href="${urlAgregarCand}">Agregar a mis candidatos seleccionados</a>
					</div>
					<%--</p>--%>

					<div>
						<a class="" href="javascript:window.history.go(-1)">Regresar</a>
					</div>
				</div>

			</div>
		</div>
	</div>

</div>

<div class="porcentaje_c">

	<div class="videoCV_c">
		<h3>Video Currículum</h3>
		<c:choose>
			<c:when test="${detalle.urlVideoEstatus == 1}">
				<a href="${detalle.urlVideoCV}" target="_blank">${detalle.urlVideoCV}</a><br>
			</c:when>
			<c:otherwise>
				<div style="text-align: center; margin-top: 50px">No disponible </div>
			</c:otherwise>
		</c:choose>
		<c:if test="${detalle.urlVideoEstatus == 1}">
			<iframe width="280" height="200" src="${decoratedVideo}" frameborder="0" allowfullscreen></iframe>
		</c:if>
	</div>

</div>

<div class="clearfix"></div>