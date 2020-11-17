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
				<h3>Detalle de la oferta <small>Procesador de Crustáceos y Moluscos </small></h3>
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
									<span>Procesador de Crustáceos y Moluscos </span>
								</div>
							</div>
						</div>
						<div class="col-sm-6">
							<div class="panel panelOfferDetalle">
								<div class="panel-body">
									<strong>Salario neto:</strong><br>
									<span>USD $11.07 dólares canadienses</span>
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
									<span>L’Etang-du-Nord and Grande-Entre, Quebec.</span>
								</div>
							</div>
						</div>
						<div class="col-sm-6">
							<div class="panel panelOfferDetalle">
								<div class="panel-body">
									<strong>Número de plazas:</strong><br>
									<span>30</span>
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
									<strong>Edad:</strong><br>
									<span>---</span>
								</div>
							</div>
						</div>
						<div class="col-sm-6">
							<div class="panel panelOfferDetalle">
								<div class="panel-body">
									<strong>Inglés obligatorio:</strong><br>
									<span>Preferible que sepa interactuar en inglés. CLB 3 (inglés básico) Idioma francés también muy bien aceptado (oral).</span>
								</div>
							</div>
						</div>
						<div class="col-sm-6">
							<div class="panel panelOfferDetalle">
								<div class="panel-body">
									<strong>Duración del Contrato:</strong><br>
									<span>3 meses</span>
								</div>
							</div>
						</div>
						<div class="col-sm-12">
							<div class="panel panelOfferDetalle">
								<div class="panel-body">
									<strong>Experiencia laboral:</strong><br>
									<span>2 años de experiencia</span>
								</div>
							</div>
						</div>
						<div class="col-sm-12">
							<div class="panel panelOfferDetalle">
								<div class="panel-body">
									<strong>Información adicional:</strong><br>
									<span>Jornada laboral a la semana: de 40 a 60 hrs.</span>
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
						<div class="panel-body">Dar presentación a la carne de langosta, cangrejo, almeja y otras especies.</div>
					</div>
					<div class="panel panelOffer">
						<div class="panel-body">Limpiar el área de trabajo y equipo.</div>
					</div>
					<div class="panel panelOfferOdd">
						<div class="panel-body">Labores de desembarco. </div>
					</div>
					<div class="panel panelOffer">
						<div class="panel-body">Etiquetar y envasar los productos.</div>
					</div>
					<div class="panel panelOfferOdd">
						<div class="panel-body">Empacar en cajas master de acuerdo con las especificaciones. </div>
					</div>
					<div class="panel panelOffer">
						<div class="panel-body">Cargar los camiones.</div>
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
