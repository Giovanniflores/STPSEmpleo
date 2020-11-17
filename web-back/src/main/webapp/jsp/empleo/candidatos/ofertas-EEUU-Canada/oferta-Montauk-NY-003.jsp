<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="url" value="${pageContext.request.requestURI}" />

<t:publicTemplate>
	<jsp:attribute name="titulo">Capacitaci&oacute;n en la pr&aacute;ctica laboral</jsp:attribute>
    <jsp:attribute name="palabraDescripcion">Candidatos</jsp:attribute>  
    <jsp:attribute name="tituloSitio">Portal del Empleo</jsp:attribute>
 	<jsp:attribute name="urlRedSocial"><c:url value="${url}"/></jsp:attribute>
	<jsp:attribute name="descripcionRedSocial">
		Detalle de la oferta. Mucama/Lavandería.
	</jsp:attribute>
	<jsp:body>

    <!-- Contenido -->
	<div class="row">
		<div class="col-sm-12">
			<div class="page-header">
				<h3>Detalle de la oferta <small>Mucama/Lavandería.</small></h3>
			</div>
		</div>
		<div class="clearfix"></div>
		<div class="col-md-4 col-md-push-8">
		</div>
		<div class="col-md-8 col-md-pull-4">
			<div class="row">
				<div class="col-sm-3">
					<div class="form-group text-center">
						<img alt="" src="${context}/css/images/ofertaUSA.png" class="img-responsive" style="margin:auto;" />
					</div>
				</div>
				<div class="col-sm-9">
					<div class="row">
						<div class="col-sm-6">
							<div class="panel panelOfferDetalle">
								<div class="panel-body">
									<strong>Ocupación:</strong><br>
									<span>Mucama/Lavandería.</span>
								</div>
							</div>
						</div>
						<div class="col-sm-6">
							<div class="panel panelOfferDetalle">
								<div class="panel-body">
									<strong>País:</strong><br>
									<span>Estados Unidos</span>
								</div>
							</div>
						</div>
						<div class="col-sm-6">
							<div class="panel panelOfferDetalle">
								<div class="panel-body">
									<strong>Provincia:</strong><br>
									<span>Montauk, N.Y</span>
								</div>
							</div>
						</div>
						<div class="col-sm-6">
							<div class="panel panelOfferDetalle">
								<div class="panel-body">
									<strong>Número de plazas:</strong><br>
									<span>1</span>
								</div>
							</div>
						</div>
						<div class="col-sm-6">
							<div class="panel panelOfferDetalle">
								<div class="panel-body">
									<strong>Sexo:</strong><br>
									<span>Femenino.</span>
								</div>
							</div>
						</div>
						<div class="col-sm-6">
							<div class="panel panelOfferDetalle">
								<div class="panel-body">
									<strong>Edad:</strong><br>
									<span>20 a 35 años.</span>
								</div>
							</div>
						</div>
						<div class="col-sm-6">
							<div class="panel panelOfferDetalle">
								<div class="panel-body">
									<strong>Estudios:</strong><br>
									<span>Mínimo Preparatoria.</span>
								</div>
							</div>
						</div>
						<div class="col-sm-6">
							<div class="panel panelOfferDetalle">
								<div class="panel-body">
									<strong>Experiencia laboral:</strong><br>
									<span>2 años mínimo.</span>
								</div>
							</div>
						</div>
						<div class="col-sm-6">
							<div class="panel panelOfferDetalle">
								<div class="panel-body">
									<strong>Salario mensual</strong><br>
									<span>$35,000.00 aprox.</span><span> *Salario promedio.</span>
								</div>
							</div>
						</div>
						<div class="col-sm-6">
							<div class="panel panelOfferDetalle">
								<div class="panel-body">
									<strong>Nivel de inglés:</strong><br>
									<span>Intermedio-Avanzado</span>
								</div>
							</div>
						</div>
						<div class="col-sm-6">
							<div class="panel panelOfferDetalle">
								<div class="panel-body">
									<strong>Duración del Contrato:</strong><br>
									<span>6 Meses</span>
								</div>
							</div>
						</div>
						<div class="col-sm-6">
							<div class="panel panelOfferDetalle">
								<div class="panel-body">
									<strong>Información adicional:</strong><br>
									<span>Jornada laboral a la semana: 35 hrs. Pago: semanal. Inicio de contrato: Tan pronto como sea posible.</span>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-sm-12">
					<div class="page-header">
						<h4>Descripción de la ocupación</h4>
					</div>
					<div class="panel panelOfferOdd">
						<div class="panel-body">Arreglar camas, cambiar sabanas, mover muebles, trapear pisos y aspirar.</div>
					</div>
					<div class="panel panelOffer">
						<div class="panel-body">Barrer, trapear, encerar y pulir pisos.</div>
					</div>
					<div class="panel panelOfferOdd">
						<div class="panel-body">Sacudir muebles y aspirar alfombras, así como áreas con tapetes, cortinas y muebles tapizados.</div>
					</div>
					<div class="panel panelOffer">
						<div class="panel-body">Arreglar camas, cambiar las sabanas, así como distribuir toallas limpias y accesorios de baño.</div>
					</div>
					<div class="panel panelOfferOdd">
						<div class="panel-body">Atender a los huéspedes que soliciten suministros extra.</div>
					</div>
					<div class="panel panelOffer">
						<div class="panel-body">Suministrar el closet con sábanas, así como en otras áreas.</div>
					</div>
					<div class="panel panelOfferOdd">
						<div class="panel-body">Limpiar, desinfectar y pulir la cocina, así como los muebles de baño y electrónicos.</div>
					</div>
					<div class="panel panelOffer">
						<div class="panel-body">Limpiar y desinfectar áreas públicas tales como vestidores, duchas, y elevadores.</div>
					</div>
					<div class="panel panelOfferOdd">
						<div class="panel-body">Recolectar la basura a vaciar los botes de basura.</div>
					</div>
					<div class="panel panelOffer">
						<div class="panel-body">Lavar ventanas, paredes y techos.</div>
					</div>
					<div class="panel panelOfferOdd">
						<div class="panel-body">Reportar y almacenar objetos perdidos y encontrados.</div>
					</div>
					<div class="panel panelOffer">
						<div class="panel-body">Puede dar información básica sobre las instalaciones.</div>
					</div>
					<div class="panel panelOfferOdd">
						<div class="panel-body">Podrían atender quejas.</div>
					</div>
					<div class="col-sm-12">
						<div class="panel panelOfferDetalle">
							<div class="panel-body">
								<span>&nbsp;</span>
								<span style="color:green;"><h4>¿Dónde dirigirse?</h4></span>
								<ul>
									<li>A la Oficina del <a href="${context}/sne/directorio-de-oficinas-sne">Servicio Nacional de Empleo</a> más cercana a su domicilio</li>
									<li>Si tienes alguna duda, llama sin costo al 01800 841 2020 desde cualquier parte de la República Mexicana.</li>
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>	
		</div>
		<div class="clearfix"></div>
	</div>
	</jsp:body>
</t:publicTemplate>
