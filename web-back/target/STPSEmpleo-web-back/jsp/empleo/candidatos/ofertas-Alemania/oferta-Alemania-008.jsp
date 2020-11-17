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
		Detalle de la oferta. Probador de Software
	</jsp:attribute>
	<jsp:body>

    <!-- Contenido -->
	<div class="row">
		<div class="col-sm-12">
			<div class="page-header">
				<h3>Detalle de la oferta <small>Probador de Software</small></h3>
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
									<span>Probador de Software</span>
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
									<span>5</span>
								</div>
							</div>
						</div>
						<div class="col-sm-6">
							<div class="panel panelOfferDetalle">
								<div class="panel-body">
									<strong>Estudios:</strong><br>
									<span>Título universitario en ciencias computacionales o relacionado a informática, título de educación técnica o científica.</span>
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
									<strong>Nivel de Alemán:</strong><br>
									<span>Mínimo de B1/B2 debido al amplio contacto con clientes.</span>
								</div>
							</div>
						</div>
						<div class="col-sm-6">
							<div class="panel panelOfferDetalle">
								<div class="panel-body">
									<strong>Otros Idiomas:</strong><br>
									<span>Inglés – mínimo B2, idiomas adicionales son una ventaja.</span>
								</div>
							</div>
						</div>
						<div class="col-sm-6">
							<div class="panel panelOfferDetalle">
								<div class="panel-body">
									<strong>Duración del Contrato:</strong><br>
									<span>Ilimitado.</span>
								</div>
							</div>
						</div>
						<div class="col-sm-6">
							<div class="panel panelOfferDetalle">
								<div class="panel-body">
									<strong>Experiencia laboral</strong><br>
									<span>En desarrollo de software y bases de datos.</span>
								</div>
							</div>
						</div>
						<div class="col-sm-6">
							<div class="panel panelOfferDetalle">
								<div class="panel-body">
									<strong>Habilidades adicionales:</strong><br>
									<span>Certificaciones (ISTQB) son una ventaja.</span>
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
									<span>Jornada laboral a la semana: 40 hrs. Pago: Mensual. Inicio de contrato: Tan pronto como sea posible. Flexibilidad para viajar dentro de Alemania y al extranjero.</span>
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
						<div class="panel-body">Sustentar los procesos de desarrollo de software probando las características del producto con respecto a la calidad y usabilidad del software, lo cual se logra a través de una cooperación estrecha con especialistas del departamento de análisis de sistemas y con los clientes.</div>
					</div>
					<div class="panel panelOffer">
						<div class="panel-body">Planeación y diseño de pruebas, incluyendo estrategias de pruebas y entornos de pruebas.</div>
					</div>
					<div class="panel panelOfferOdd">
						<div class="panel-body">Configuración y mantenimiento de entornos de pruebas.</div>
					</div>
					<div class="panel panelOffer">
						<div class="panel-body">Determinación de niveles de pruebas.</div>
					</div>
					<div class="panel panelOfferOdd">
						<div class="panel-body">Desarrollo, preparación, conducción y documentación de secuencias de pruebas manuales/automatizadas de acuerdo a los objetos de prueba, los objetivos de prueba y las características de calidad.</div>
					</div>
					<div class="panel panelOffer">
						<div class="panel-body">Desarrollo y transferencia de protocolos de pruebas/bitácoras de errores.</div>
					</div>
					<div class="panel panelOffer">
						<div class="panel-body">Evaluar los errores con respecto a los posibles efectos en la funcionalidad del sistema.</div>
					</div>
					<div class="panel panelOffer">
						<div class="panel-body">Experiencia sólida en por lo menos uno de los siguientes lenguajes: C, C++, Python, Java, PHP, .NET, JavaScript, C#, HTML, CSS, Ruby, Perl.</div>
					</div>
					<div class="panel panelOffer">
						<div class="panel-body">Conocimiento profundo y funcional de las herramientas de prueba.</div>
					</div>
					<div class="panel panelOffer">
						<div class="panel-body">Conocimiento de los procedimientos y requerimientos en gestión de calidad.</div>
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
