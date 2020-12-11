<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<script type="text/javascript" src="js/helper/messageHelper.js"></script>
<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="url" value="${pageContext.request.requestURI}" />
<c:if test="${not empty ERROR_MSG}">
	<script type="text/javascript">
		dojo.addOnLoad(function() {
			dialogLoginPopup();
		});
	</script>
</c:if>
<script type="text/javascript">
	var conf = function() {dijit.byId('dialogoConfirmacion').show();}
	function closeDialog3(){dijit.byId('dialogoConfirmacion').hide();}
	function validaPostularOferta(usuario) {
		if (null == usuario || usuario == "") {
			dialogLoginPopup(); //dialogo para logearse
		}
		if (null!=usuario && !usuario=="") {
			dojo.addOnLoad(conf);	//dialogo confirmacion			
		}
	}
	function postularOferta() {
		document.formPost.action = '${context}/ofertasExtranjeras.do?method=offerPost';
		document.formPost.submit();
	}
	function goback() {
		location.replace('${context}/ofertasExtranjeras.do?method=init');
	}
</script>
<div class="row">
		<div class="col-sm-12">
			<div class="page-header">
				<h3>Detalle de la oferta <small>${ofertaExtranjera.tituloOferta}</small></h3>
			</div>
		</div>
		<div class="clearfix"></div>
		<div class="col-md-4 col-md-push-8">
			<c:if test="${not empty _user}"> 
				<div class="row">
					<div class="col-sm-12">
						<div class="panel panel-compatibilidad">
						  <div class="panel-heading">
						    <h3 class="panel-title">
						    	${_NAME}
							</h3>
						  </div>
						  <div class="panel-body">
						    <p><em>Tu porcentaje de compatibilidad con esta oferta de empleo es del</em></p>
						    <c:if test="${compatibility <= 25}">
						    	<c:set var="classProgress" value="progress-bar-warning" />
						    </c:if>
						    <c:if test="${compatibility > 25 and compatibility <= 75}">
						    	<c:set var="classProgress" value="progress-bar-info" />
						    </c:if>
						    <c:if test="${compatibility > 75}">
						    	<c:set var="classProgress" value="progress-bar-success" />
						    </c:if>
							<div class="progress progresCompatibilidad">
								<div class="progress-bar ${classProgress} progress-bar-striped" role="progressbar" aria-valuenow="${compatibility}>" 
							  		aria-valuemin="0" aria-valuemax="100" style="width: ${compatibility}%;">
									<strong id="porcentaje">${compatibility}%</strong>
							  	</div>
							</div>
						  </div>
						  <div class="panel-footer text-center">
							<p>Para mejorar tu compatibilidad, te sugerimos</p>
							<a class="hidden-xs" href="${context}/perfil.do?method=init"> Actualizar tu perfil laboral </a>
						  </div>
						</div>
					</div>
				</div>
			</c:if>
		</div>
		<div class="col-md-8 col-md-pull-4">
			<div class="row">
				<div class="col-sm-3">
					<div class="form-group text-center">
						<c:if test="${ofertaExtranjera.pais == 'Alemania'}">
							<img alt="" src="${context}/css/images/alemania.png" class="img-responsive" style="margin:auto;" />
						</c:if>
						<c:if test="${ofertaExtranjera.pais == 'EUA'}">
							<img alt="" src="${context}/css/images/ofertaUSA.png" class="img-responsive" style="margin:auto;" />
						</c:if>
						<c:if test="${ofertaExtranjera.pais == 'Canada'}">
							<img alt="" src="${context}/css/images/ofertaCanada.png" class="img-responsive" style="margin:auto;" />
						</c:if>
					</div>
				</div>
				<div class="col-sm-9">
					<div class="row">
						<div class="col-sm-6">
							<div class="panel panelOfferDetalle">
								<div class="panel-body">
									<strong>Ocupación:</strong><br>
									<span>${ofertaExtranjera.ocupacion}</span>
								</div>
							</div>
						</div>
						<c:if test="${not empty ofertaExtranjera.sector}">
							<div class="col-sm-6">
								<div class="panel panelOfferDetalle">
									<div class="panel-body">
										<strong>Sector:</strong><br>
										<span>${ofertaExtranjera.sector}</span>
									</div>
								</div>
							</div>
						</c:if>
						<div class="col-sm-6">
							<div class="panel panelOfferDetalle">
								<div class="panel-body">
									<strong>Salario mensual*:</strong><br>
									<fmt:setLocale value = "es_MX"/>
									<span><fmt:formatNumber value = "${ofertaExtranjera.salarioMensual}" type = "currency"/> aprox</span>
								</div>
							</div>
						</div>
						<div class="col-sm-6">
							<div class="panel panelOfferDetalle">
								<div class="panel-body">
									<strong>País:</strong><br>
									<span>${ofertaExtranjera.pais}<c:if test="${not empty ofertaExtranjera.provincia}">, ${ofertaExtranjera.provincia}</c:if></span>
								</div>
							</div>
						</div>
						<div class="col-sm-6">
							<div class="panel panelOfferDetalle">
								<div class="panel-body">
									<strong>Número de plazas:</strong><br>
									<span>${ofertaExtranjera.numeroPlazas}</span>
								</div>
							</div>
						</div>
						<div class="col-sm-6">
							<div class="panel panelOfferDetalle">
								<div class="panel-body">
									<strong>Estudios:</strong><br>
									<span>${ofertaExtranjera.estudios}</span>
								</div>
							</div>
						</div>
						<div class="col-sm-6">
							<div class="panel panelOfferDetalle">
								<div class="panel-body">
									<strong>Sexo:</strong><br>
									<span>${ofertaExtranjera.genero}</span>
								</div>
							</div>
						</div>
						<div class="col-sm-6">
							<div class="panel panelOfferDetalle">
								<div class="panel-body">
									<c:if test="${ofertaExtranjera.pais == 'Alemania'}">
										<strong>Nivel de Alemán:</strong><br>
									</c:if>
									<c:if test="${ofertaExtranjera.pais == 'EUA'}">
										<strong>Nivel de Inglés:</strong><br>
									</c:if>
									<c:if test="${ofertaExtranjera.pais == 'Canada'}">
										<strong>Nivel de Fránces:</strong><br>
									</c:if>
									<span>${ofertaExtranjera.nivelIdiomaPrincipal}</span>
								</div>
							</div>
						</div>
						<div class="col-sm-6">
							<div class="panel panelOfferDetalle">
								<div class="panel-body">
									<strong>Otros Idiomas:</strong><br>
									<span>${ofertaExtranjera.otrosIdiomas}</span>
								</div>
							</div>
						</div>
						<div class="col-sm-6">
							<div class="panel panelOfferDetalle">
								<div class="panel-body">
									<strong>Duración del Contrato:</strong><br>
									<span>${ofertaExtranjera.duracionContrato}</span>
								</div>
							</div>
						</div>
						<div class="col-sm-6">
							<div class="panel panelOfferDetalle">
								<div class="panel-body">
									<strong>Experiencia laboral:</strong><br>
									<span>${ofertaExtranjera.experienciaLaboral}</span>
								</div>
							</div>
						</div>
						<div class="col-sm-6">
							<div class="panel panelOfferDetalle">
								<div class="panel-body">
									<strong>Hospedaje:</strong><br>
									<span>${ofertaExtranjera.hospedaje}</span>
								</div>
							</div>
						</div>
						<div class="col-sm-6">
							<div class="panel panelOfferDetalle">
								<div class="panel-body">
									<strong>Transportación:</strong><br>
									<span>${ofertaExtranjera.transportacion}</span>
								</div>
							</div>
						</div>
						<div class="col-sm-6">
							<div class="panel panelOfferDetalle">
								<div class="panel-body">
									<strong>Información adicional:</strong><br>
									<span>${ofertaExtranjera.informacionAdicional}</span>
								</div>
							</div>
						</div>
						<div class="col-sm-12">
							<div class="panel panelOfferDetalle">
								<div class="panel-body">
									<strong>Requisitos:</strong><br>
									<span>${ofertaExtranjera.requisitos}</span>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-sm-12">
					<div class="page-header">
						<h4>Descripción de la ocupación</h4>
					</div>
					<c:forEach var="requisitos" items="${requisitos}" varStatus="rowCounter">
						<div class="panel panelOfferOdd">
							<div class="panel-body">${requisitos.descripcion}</div>
						</div>
					</c:forEach>
				</div>
				<div class="col-sm-12">
					<div class="panel panelOfferDetalle">
						<div class="panel-body">
							<c:if test="${ofertaExtranjera.pais == 'Alemania'}">
								<!-- span>*Salario promedio, Iniciando a partir de 45.000 € (salario bruto, dependiendo del área geográfica en Alemania, lugar de trabajo y experiencia profesional).</span-->
							</c:if>
							
						</div>
						<c:if test="${ofertaExtranjera.pais == 'Alemania'}">
							<div class="panel panelOfferOdd">
								<div class="panel-body"><strong>Salarios en Alemania</strong></div>
							</div>
							<ul>
								<li>En Alemania hay desde 2015 un salario mínimo estatutario, que en estos momentos es de 8.84 &#8364; por hora.</li>
								<li>Generalmente los Salarios dependen de acuerdos colectivos que existen para la mayoría de los sectores</li>
								<li>Diferencias en los salarios (entre Este/Oeste y Norte/Sur).</li>
								<li>En Alemania los salarios establecidos son generalmente salarios totales - los salarios netos son menores.</li>
							</ul>
							<div class="panel panelOfferOdd">
								<div class="panel-body"><strong>Seguro Social</strong></div>
							</div>
							<ul>
								<li>2.55% Seguros de cuidado a largo plazo.</li>
								<li>3% Seguros de desempleo</li>
								<li>18.7% Seguros de retiro</li>
								<li>14.6% Seguros por enfermedad</li>
								<li>Seguros por accidente (pagados por el empleador)</li>
							</ul>
							
							<!-- div class="panel panelOfferOdd">
								<div class="panel-body"><strong>Impuestos y ejemplos de facturación de salarios</strong></div>
							</div>
							<ul>
								<li>Salario total 3000.00 &#8364;</li>
								<li>Impuestos (Ingresos, impuesto eclesiástico, cargo de solidaridad) -536.23 &#8364;</li>
								<li>Seguro social -621.77 &#8364;</li>
								<li>Salario neto 1842.00 &#8364;</li>
								<li>Los impuestos serán automáticamente deducidos del sueldo.</li>
							</ul-->
							<img class="img-responsive" alt="más bolsas de empleo" src="css/images/sueldos-alemania1.jpg" />
						</c:if>
							<span style="color:green;"><h4>¿Dónde dirigirse para postularse?</h4></span>
								<ul>
									<li>A la Oficina del <a href="${context}/sne/directorio-de-oficinas-sne">Servicio Nacional de Empleo</a> más cercana a su domicilio</li>
									<li>Si tienes alguna duda, llama sin costo al 01800 841 2020 desde cualquier parte de la República Mexicana.</li>
								</ul>
					</div>
				</div>						
			</div>	
		</div>
		<div class="clearfix"></div>
		<div class="col-sm-12" style="text-align: center">
			<!--<c:if test="${ofertaExtranjera.postulated == false}">
				<div class="form-group text-center">
					<input type="submit" class="btnPE estaof" id="postularOferta" onclick="javascript:validaPostularOferta('${USUARIO_APP}');" value="Postularme a esta oferta" />
					<form name="formPost" id="formPost" method="post" action="ofertasExtranjeras.do">
						<input type="hidden" name="method" id="method" value="init" />
						<input type="hidden" name="idCandidato" id="idCandidato" value="" />
						<input type="hidden" name="idOfertaEmpleo" id="idOfertaEmpleo" value="" />
					</form>
				</div>
			</c:if> -->
				<c:choose>
					<c:when test="${not empty _urlpageinvoke}">
						<c:url var="url" value="${_urlpageinvoke}" />
						<a class="btn-green regs" href="${url}">Regresar</a>
					</c:when>
					<c:otherwise>
						<a class="btn-green regs" href="javascript:history.go(-1)">Regresar a las vacantes</a>
					</c:otherwise>
				</c:choose>

		</div>
	</div>
	<div dojoType="dijit.Dialog" id="dialogoConfirmacion" title="Confirmación de la Postulación" style="display: none; width:400px" draggable="false">
			<div class="msg_contain">
						<p>Al postularte, el Servicio Nacional de Empleo recibe tu<br>
							 información para programar una entrevista si cubres el perfil.
						<br><br>
						<strong>¿Deseas continuar con el proceso de postulación?</strong></p>
					
					<p class="form_nav 2">
						<input type="button" class="boton" value="Aceptar" onclick="javascript:postularOferta();">				
						<input type="button" class="boton" value="Cancelar" onclick="closeDialog3();">
					</p>
			</div>		
	</div>
	<c:if test="${not empty _MSG}">
		<script>message('${_MSG}');</script>
	</c:if>
<%
	session.removeAttribute("_MSG");
%>