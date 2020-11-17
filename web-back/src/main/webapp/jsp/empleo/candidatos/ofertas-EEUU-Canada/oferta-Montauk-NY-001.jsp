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
		Detalle de la oferta. Trabajadores en Preparación de Alimentos (Repostería).
	</jsp:attribute>
	<jsp:body>

    <!-- Contenido -->
	<div class="row">
		<div class="col-sm-12">
			<div class="page-header">
				<h3>Detalle de la oferta <small>Trabajadores en Preparación de Alimentos (Repostería).</small></h3>
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
									<span>Trabajadores en Preparación de Alimentos (Repostería).</span>
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
									<span>Indistinto.</span>
								</div>
							</div>
						</div>
						<div class="col-sm-6">
							<div class="panel panelOfferDetalle">
								<div class="panel-body">
									<strong>Edad:</strong><br>
									<span>20 a 40 años.</span>
								</div>
							</div>
						</div>
						<div class="col-sm-6">
							<div class="panel panelOfferDetalle">
								<div class="panel-body">
									<strong>Estudios:</strong><br>
									<span>Secundaria.</span>
								</div>
							</div>
						</div>
						<div class="col-sm-6">
							<div class="panel panelOfferDetalle">
								<div class="panel-body">
									<strong>Experiencia laboral:</strong><br>
									<span>1 año mínimo.</span>
								</div>
							</div>
						</div>
						<div class="col-sm-6">
							<div class="panel panelOfferDetalle">
								<div class="panel-body">
									<strong>Salario mensual*</strong><br>
									<span>$45,000.00 aprox.</span>
								</div>
							</div>
						</div>
						<div class="col-sm-6">
							<div class="panel panelOfferDetalle">
								<div class="panel-body">
									<strong>Nivel de inglés:</strong><br>
									<span>60%</span>
								</div>
							</div>
						</div>
						<div class="col-sm-6">
							<div class="panel panelOfferDetalle">
								<div class="panel-body">
									<strong>Duración del Contrato:</strong><br>
									<span>Como llegue el trabajador a EUA regresaría en enero de 2018</span>
								</div>
							</div>
						</div>
						<div class="col-sm-6">
							<div class="panel panelOfferDetalle">
								<div class="panel-body">
									<strong>Habilidades adicionales :</strong><br>
									<span>Deberá ser experto en la elaboración de: pasteles, pan, pasta, sándwiches, galletas y postres.</span>
								</div>
							</div>
						</div>
						<div class="col-sm-6">
							<div class="panel panelOfferDetalle">
								<div class="panel-body">
									<strong>Información adicional:</strong><br>
									<span>Jornada laboral a la semana: 40 hrs. Pago: semanal. Inicio de contrato: Tan pronto como sea posible.</span>
								</div>
							</div>
						</div>
						<div class="col-sm-6">
							<div class="panel panelOfferDetalle">
								<div class="panel-body">
									<strong>Hospedaje:</strong><br>
									<span>125.00 dólares estadounidenses a la semana que incluye 3 alimentos al día todos los días.</span>
								</div>
							</div>
						</div>
						<div class="col-sm-6">
							<div class="panel panelOfferDetalle">
								<div class="panel-body">
									<strong>Transportación:</strong><br>
									<span>Se les reembolsará después de haber concluido medio contrato, asimismo, se les comprará el boleto de regreso.</span>
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
						<div class="panel-body">Preparar y cocinar platillos completos o platos individuales, comida.</div>
					</div>
					<div class="panel panelOffer">
						<div class="panel-body">Preparar y cocinar platos especiales para los clientes como pueda ser instruido por el Chef.</div>
					</div>
					<div class="panel panelOfferOdd">
						<div class="panel-body">Mantener el inventario y registro de la comida, suministros y equipo.</div>
					</div>
					<div class="panel panelOffer">
						<div class="panel-body">Puede limpiar la cocina y el área de trabajo.</div>
					</div>
					<div class="panel panelOfferOdd">
						<div class="panel-body">Preparación de platos o postres que sean dulces, a base de diferentes tipos de masas (como los budines, los bizcochuelos, panqueques o las masas de pastel), así como también postres a base de cremas (por ejemplo, las natillas), o a base de frutas (helados y otras preparaciones frías).</div>
					</div>
					<div class="panel panelOffer">
						<div class="panel-body">Uso de materiales tales como la harina (usualmente de trigo), el azúcar, huevos y manteca es esencial, sumar aromatizantes y saborizantes específicos para cada situación como esencias, frutas, especias, colorantes y muchos más.</div>
					</div>
					<div class="col-sm-12">
						<div class="panel panelOfferDetalle">
							<div class="panel-body">
								<span>*Salario promedio.</span>
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
