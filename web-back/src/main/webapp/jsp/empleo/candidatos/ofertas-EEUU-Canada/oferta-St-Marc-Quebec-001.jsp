<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="context" value="${pageContext.request.contextPath}" />

<t:publicTemplate>
	<jsp:attribute name="titulo">Ofertas en Canad&aacute; y Estados Unidos</jsp:attribute>
    <jsp:attribute name="palabraDescripcion">Candidatos</jsp:attribute> 
    <jsp:attribute name="tituloSitio">Portal del Empleo</jsp:attribute>
	<jsp:body>
	

    <!-- Contenido -->
	<div class="row">
		<div class="col-sm-12">
			<div class="page-header">
				<h3>Detalle de la oferta <small>Costurera</small></h3>
			</div>
		</div>
		<div class="clearfix"></div>
		<div class="col-md-4 col-md-push-8"> 
		</div>
		<div class="col-md-8 col-md-pull-4">
			<div class="row">
				<div class="col-sm-3">
					<div class="form-group text-center">
						<img alt="" src="${context}/css/images/ofertaCanada.png" class="img-responsive" style="margin:auto;" />
					</div>
				</div>
				<div class="col-sm-9">
					<div class="row">
						<div class="col-sm-6">
							<div class="panel panelOfferDetalle">
								<div class="panel-body"> 
									<strong>Ocupación:</strong><br> 
									<span>Costurera</span>
								</div>
							</div>
						</div>
						<div class="col-sm-6">
							<div class="panel panelOfferDetalle">
								<div class="panel-body">
									<strong>Salario neto:</strong><br>
									<span>$10.85 a $12.00 dolares canadienses</span>
								</div>
							</div>
						</div>
						<div class="col-sm-6">
							<div class="panel panelOfferDetalle">
								<div class="panel-body">
									<strong>País:</strong><br>
									<span>Canadá</span>
								</div>
							</div>
						</div>
						<div class="col-sm-6">
							<div class="panel panelOfferDetalle">
								<div class="panel-body">
									<strong>Provincia:</strong><br>
									<span>St-Marc-des-Carrieres, QC. (Quebec)  </span>
								</div>
							</div>
						</div>
						<div class="col-sm-6">
							<div class="panel panelOfferDetalle">
								<div class="panel-body">
									<strong>Número de plazas:</strong><br>
									<span>3</span>
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
									<span>Femenino.</span>
								</div>
							</div>
						</div>
						<div class="col-sm-6">
							<div class="panel panelOfferDetalle">
								<div class="panel-body">
									<strong>Edad:</strong><br>
									<span>20 - 45 años</span>
								</div>
							</div>
						</div>
						<div class="col-sm-6">
							<div class="panel panelOfferDetalle">
								<div class="panel-body">
									<strong>Francés obligatorio:</strong><br>
									<span>Intermedio</span>
								</div>
							</div>
						</div>
						<div class="col-sm-6">
							<div class="panel panelOfferDetalle">
								<div class="panel-body">
									<strong>Duración del Contrato:</strong><br>
									<span>6 a 8 meses</span>
								</div>
							</div>
						</div>
						<div class="col-sm-12">
							<div class="panel panelOfferDetalle">
								<div class="panel-body">
									<strong>Experiencia laboral:</strong><br>
									<span>1 año de experiencia.<br>Máquina de coser, tijeras y herramientas de medición.</span>
								</div>
							</div>
						</div>
						<div class="col-sm-12">
							<div class="panel panelOfferDetalle">
								<div class="panel-body">
									<strong>Hospedaje:</strong><br>
									<span>El empleador lo acordará con el trabajador.</span>
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
						<div class="panel-body">Saber utilizar máquina de coser.</div>
					</div>
					<div class="panel panelOffer">
						<div class="panel-body">Realizar cambios o reparaciones en las prendas de vestir requerido por el cliente.</div>
					</div>
					<div class="panel panelOfferOdd">
						<div class="panel-body">Ensamblar muestras para desarrollar nuevos diseños. </div>
					</div>
					<div class="panel panelOffer">
						<div class="panel-body">Trabajar con diferente tipo de textiles.</div>
					</div>
					<div class="panel panelOfferOdd">
						<div class="panel-body">Buena apreciación de formas y colores. </div>
					</div>
					<div class="panel panelOffer">
						<div class="panel-body">Buena resistencia física.</div>
					</div>	
					<div class="panel panelOfferOdd">
						<div class="panel-body">Destreza manual.</div>
					</div>	
					<div class="panel panelOffer">
						<div class="panel-body">Atención a los detalles para coser y ajustar la ropa.</div>
					</div>					
				</div>
				<div class="col-sm-12">
					<div class="panel panel-grey">
			          <div class="panel-heading">
			            <h3 class="panel-title">
			              Has click aquí: <a href="<c:url value="/jsp/empleo/servicioNacionalDeEmpleo/ubicacion.jsp"/>">Directorio de Oficinas del Servicio Nacional de Empleo</a>
			            </h3>
			          </div>
			          <div class="panel-body">
			            Favor de acudir a la Oficina del Servicio Nacional de Empleo más cercana a tú domicilio.
			          </div>
			        </div>
				</div>
			</div>	
		</div>
		<div class="clearfix"></div>
	</div>
	</jsp:body>
</t:publicTemplate>
