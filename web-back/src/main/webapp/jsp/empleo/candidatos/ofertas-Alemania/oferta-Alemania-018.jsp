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
		Detalle de la oferta. Ingeniero de Desarrollo en Pruebas
	</jsp:attribute>
	<jsp:body>

    <!-- Contenido -->
	<div class="row">
		<div class="col-sm-12">
			<div class="page-header">
				<h3>Detalle de la oferta <small>Ingeniero de Desarrollo en Pruebas</small></h3>
			</div>
		</div>
		<div class="clearfix"></div>
		<div class="col-md-4 col-md-push-8">
		</div>
		<div class="col-md-8 col-md-pull-4">
			<div class="row">
				<div class="col-sm-3">
					<div class="form-group text-center">
						<img alt="" src="${context}/css/images/alemania.png" class="img-responsive" style="margin:auto;" />
					</div>
				</div>
				<div class="col-sm-9">
					<div class="row">
						<div class="col-sm-6">
							<div class="panel panelOfferDetalle">
								<div class="panel-body">
									<strong>Ocupación:</strong><br>
									<span>Ingeniero de Desarrollo en Pruebas</span>
								</div>
							</div>
						</div>
						<div class="col-sm-6">
							<div class="panel panelOfferDetalle">
								<div class="panel-body">
									<strong>Salario mensual*:</strong><br>
									<span>$64,000.00 aprox.</span>
								</div>
							</div>
						</div>
						<div class="col-sm-6">
							<div class="panel panelOfferDetalle">
								<div class="panel-body">
									<strong>País:</strong><br>
									<span>Alemania</span>
								</div>
							</div>
						</div>
						<div class="col-sm-6">
							<div class="panel panelOfferDetalle">
								<div class="panel-body">
									<strong>Número de plazas:</strong><br>
									<span>10</span>
								</div>
							</div>
						</div>
						<div class="col-sm-6">
							<div class="panel panelOfferDetalle">
								<div class="panel-body">
									<strong>Estudios:</strong><br>
									<span>Grado Universitario como Ingeniero Eléctrico o Ingeniero en Computación (Ingenierías relacionadas al área).</span>
								</div>
							</div>
						</div>
						<div class="col-sm-6">
							<div class="panel panelOfferDetalle">
								<div class="panel-body">
									<strong>Sexo:</strong><br>
									<span>Masculino/Femenino.</span>
								</div>
							</div>
						</div>
						<div class="col-sm-6">
							<div class="panel panelOfferDetalle">
								<div class="panel-body">
									<strong>Nivel de Inglés:</strong><br>
									<span>Mínimo Intermedio-Avanzado.</span>
								</div>
							</div>
						</div>
						<div class="col-sm-6">
							<div class="panel panelOfferDetalle">
								<div class="panel-body">
									<strong>Otros Idiomas:</strong><br>
									<span>Idiomas adicionales son una ventaja.</span>
								</div>
							</div>
						</div>
						<div class="col-sm-6">
							<div class="panel panelOfferDetalle">
								<div class="panel-body">
									<strong>Duración del Contrato:</strong><br>
									<span>Sin fecha de terminación.</span>
								</div>
							</div>
						</div>
						<div class="col-sm-6">
							<div class="panel panelOfferDetalle">
								<div class="panel-body">
									<strong>Experiencia laboral</strong><br>
									<span>5 años o más trabajando con tecnologías 3G/4G/LTE, 3 años o más con habilidad de Desarrollo /Prueba de protocolos inalámbricos, así como el uso de herramientas de pruebas/depuración tales como CMW500, Analista Spectum, etc.</span>
								</div>
							</div>
						</div>
						<div class="col-sm-6">
							<div class="panel panelOfferDetalle">
								<div class="panel-body">
									<strong>Hospedaje:</strong><br>
									<span>Como Agencia de Empleo Federal apoyamos a los trabajadores a encontrar un lugar cercano al lugar de Trabajo. El trabajador paga su vivienda.</span>
								</div>
							</div>
						</div>
						<div class="col-sm-6">
							<div class="panel panelOfferDetalle">
								<div class="panel-body">
									<strong>Transportación:</strong><br>
									<span>El Pago depende de la empresa que contrate al trabajador.</span>
								</div>
							</div>
						</div>
						<div class="col-sm-6">
							<div class="panel panelOfferDetalle">
								<div class="panel-body">
									<strong>Información adicional:</strong><br>
									<span>Jornada laboral a la semana: 40 hrs. Pago: Mensual. Inicio de contrato: Tan pronto como sea posible.
									</span>
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
						<div class="panel-body">Estamos en busca de Ingenieros de prueba de Campo/Laboratorio, Ingenieros en Soporte involucrado en tecnología inalámbrica 4G/LTE preventivos y de pruebas trabajando de manera conjunta con operadores y otros colegas del ramo.</div>
					</div>
					<div class="panel panelOfferOdd">
						<div class="panel-body">El Candidato debe contar con un buen entendimiento de especificaciones 3 GPP, así como conocimiento en LTE/4G, pilas de protocolo de subdivisiones como NAS, RRC, RLC, MAC, PHY.</div>
					</div>
					<div class="panel panelOfferOdd">
						<div class="panel-body">Debe tener la posibilidad de hacer pruebas de campo y de laboratorio, así como de las causas de origen de los problemas o fallas.</div>
					</div>
					<div class="panel panelOffer">
						<div class="panel-body">Debe tener la posibilidad de sugerir, dar solución en la unidad donde fue identificado el problema.</div>
					</div>
					<div class="panel panelOfferOdd">
						<div class="panel-body">Debe tener conocimientos de C/C++.</div>
					</div>
					<div class="panel panelOffer">
						<div class="panel-body">Interfaz con operadores clave, trabajar con ellos junto con el equipo de desarrollo de apoyo para resolver casos que han sido asignados.</div>
					</div>
					<div class="panel panelOfferOdd">
						<div class="panel-body">Comunicación clara en el status de los temas en los que se esté trabajando.</div>
					</div>
					<div class="panel panelOfferOdd">
						<div class="panel-body">Trabajo con proveedores de Estación de Base y con el equipo de Intel para dar solución a asuntos OIT constantes y complicados.</div>
					</div>
					<div class="panel panelOfferOdd">
						<div class="panel-body">Trabajar con equipos Core R&D para coordinar cambios requeridos a ser incorporados en el área de desarrollo.</div>
					</div>
				</div>
				<div class="col-sm-12">
					<div class="panel panelOfferDetalle">
						<div class="panel-body">
							<span>*Salario promedio, 38,000-60,000 € (salario bruto, depende del área geográfica en Alemania, lugar de Trabajo y experiencia profesional).</span>
							<span style="color:green;"><h4>¿Dónde dirigirse?</h4></span>
							<ul>
								<li>A la Oficina del <a href="${context}/sne/directorio-de-oficinas-sne">Servicio Nacional de Empleo</a> más cercana a su domicilio</li>
								<li>Si tienes alguna duda, llama sin costo al 01800 841 2020 desde cualquier parte de la República Mexicana.</li>
							</ul>
						</div>
						<div class="panel-body">
							<img alt="" src="${context}/css/images/sueldos-alemania.png" class="img-responsive" style="margin:auto;" />
						</div>
					</div>
				</div>
			</div>	
		</div>
		<div class="clearfix"></div>
	</div>
	</jsp:body>
</t:publicTemplate>
