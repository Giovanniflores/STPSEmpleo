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
		Detalle de la oferta. Cantinero que sirva bebidas y alimentos/botanas
	</jsp:attribute>
	<jsp:body>
	

    <!-- Contenido -->
	<div class="row">
		<div class="col-sm-12">
			<div class="page-header">
				<h3>Detalle de la oferta <small>Mesero de Servicio de Alimentos y Bebidas</small></h3>
			</div>
		</div>
		<div class="clearfix"></div>
		<div class="col-md-4 col-md-push-8">
		</div>
		<div class="col-md-8 col-md-pull-4">
			<div class="row">
				<div class="col-sm-3">
					<div class="form-group text-center">
						<img alt="" src="${context}/css/images/contenido/ofertaUSA.png" class="img-responsive" style="margin:auto;" />
					</div>
				</div>
				<div class="col-sm-9">
					<div class="row">
						<div class="col-sm-6">
							<div class="panel panelOfferDetalle">
								<div class="panel-body">
									<strong>Ocupación:</strong><br>
									<span>Meseros (Camareros)</span>
								</div>
							</div>
						</div>
						<div class="col-sm-6">
							<div class="panel panelOfferDetalle">
								<div class="panel-body">
									<strong>Salario neto:</strong><br>
									<span>USD $12.03 dólares americanos</span>
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
									<span>Naples, Florida.  </span>
								</div>
							</div>
						</div>
						<div class="col-sm-6">
							<div class="panel panelOfferDetalle">
								<div class="panel-body">
									<strong>Número de plazas:</strong><br>
									<span>20</span>
								</div>
							</div>
						</div>
						<div class="col-sm-6">
							<div class="panel panelOfferDetalle">
								<div class="panel-body">
									<strong>Estudios:</strong><br>
									<span>Mínimo secundaria</span>
								</div>
							</div>
						</div>
						<div class="col-sm-6">
							<div class="panel panelOfferDetalle">
								<div class="panel-body">
									<strong>Sexo:</strong><br>
									<span>Indistinto.</span>
								</div>
							</div>
						</div>
						<div class="col-sm-6">
							<div class="panel panelOfferDetalle">
								<div class="panel-body">
									<strong>Inglés obligatorio:</strong><br>
									<span>Intermedio-Avanzado</span>
								</div>
							</div>
						</div>
						<div class="col-sm-6">
							<div class="panel panelOfferDetalle">
								<div class="panel-body">
									<strong>Duración del Contrato:</strong><br>
									<span>11 meses</span>
								</div>
							</div>
						</div>
						<div class="col-sm-6">
							<div class="panel panelOfferDetalle">
								<div class="panel-body">
									<strong>Experiencia laboral:</strong><br>
									<span>1 año de experiencia <br> En la atención de alimentos y bebidas.</span>
								</div>
							</div>
						</div>
						<div class="col-sm-12">
							<div class="panel panelOfferDetalle">
								<div class="panel-body">
									<strong>Requisito adicional:</strong><br>
									<span>USD $600/mes + $200 depósito, descuento por nómina, el pago incluye renta de la vivienda, electricidad y cable.</span>
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
						<div class="panel-body">
							Recibir a los comensales, presentar el menú, hacer sugerencias y responder preguntas relacionadas a los alimentos y bebidas.
						</div>
					</div>
					<div class="panel panelOffer">
						<div class="panel-body">
							Tomar órdenes y transmitirlas a la cocina o al personal del bar.
						</div>
					</div>
					<div class="panel panelOfferOdd">
						<div class="panel-body">
							Recomendar vinos que puedan ser complemento con los alimentos de los comensales.
						</div>
					</div>
					<div class="panel panelOffer">
						<div class="panel-body">
							Servir alimentos y bebidas a los comensales.
						</div>
					</div>
					<div class="panel panelOfferOdd">
						<div class="panel-body">
							Preparar y servir especialidades culinarias en la mesa de los comensales. 
						</div>
					</div>
					<div class="panel panelOffer">
						<div class="panel-body">
							Presentar la cuenta a los comensales y aceptar el pago.
						</div>
					</div>
					<div class="panel panelOfferOdd">
						<div class="panel-body">
							Ordenar y mantener el inventario de los vinos y la cristalería.
						</div>
					</div>
					<div class="panel panelOffer">
						<div class="panel-body">
							Llevar a cabo un monitoreo de evaluación de vinos.
						</div>
					</div>
				</div>
			</div>	
		</div>
		<div class="clearfix"></div>
	</div>
	</jsp:body>
</t:publicTemplate>
