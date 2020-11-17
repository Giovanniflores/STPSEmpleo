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
		Detalle de la oferta. Desarrollador de Bases de Datos
	</jsp:attribute>
	<jsp:body>

    <!-- Contenido -->
	<div class="row">
		<div class="col-sm-12">
			<div class="page-header">
				<h3>Detalle de la oferta <small>Desarrollador de Bases de Datos</small></h3>
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
									<span>Desarrollador de Bases de Datos</span>
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
									<span>57</span>
								</div>
							</div>
						</div>
						<div class="col-sm-6">
							<div class="panel panelOfferDetalle">
								<div class="panel-body">
									<strong>Estudios:</strong><br>
									<span>Título universitario en ciencias computacionales, especialmente en sistemas de información de negocios.</span>
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
									<span>B1/B2 como mínimo debido al extenso contacto con clientes y asociados.</span>
								</div>
							</div>
						</div>
						<div class="col-sm-6">
							<div class="panel panelOfferDetalle">
								<div class="panel-body">
									<strong>Otros Idiomas:</strong><br>
									<span>Inglés– mínimo B2, idiomas adicionales son una ventaja.</span>
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
									<span>Dentro del campo de desarrollo de software y bases de datos.</span>
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
						<div class="panel-body">Los Desarrolladores de Bases de Datos se especializan en diseñar, programar y aplicar sistemas de bases de datos.</div>
					</div>
					<div class="panel panelOfferOdd">
						<div class="panel-body">Responsabilidad durante el análisis de los requerimientos, y estimación de recursos.</div>
					</div>
					<div class="panel panelOfferOdd">
						<div class="panel-body">Evaluación de las proporciones entre costo y beneficio.</div>
					</div>
					<div class="panel panelOffer">
						<div class="panel-body">Garantiza la integridad y seguridad de los datos.</div>
					</div>
					<div class="panel panelOfferOdd">
						<div class="panel-body">Estructuración y categorización de datos de negocio.</div>
					</div>
					<div class="panel panelOffer">
						<div class="panel-body">Responsabilidad sobre la estructura de datos, lógica de procesamiento e interfaces de usuarios.</div>
					</div>
					<div class="panel panelOfferOdd">
						<div class="panel-body">Proveer soporte a los departamentos especializados.</div>
					</div>
					<div class="panel panelOfferOdd">
						<div class="panel-body">Consulta de usuarios en intervalos regulares.</div>
					</div>
					<div class="panel panelOffer">
						<div class="panel-body">Pruebas de Software.</div>
					</div>
					<div class="panel panelOffer">
						<div class="panel-body">Entrenamiento de usuarios, creación de manuales.</div>
					</div>
					<div class="panel panelOffer">
						<div class="panel-body">Implementación independiente de tareas de desarrollo.</div>
					</div>
					<div class="panel panelOffer">
						<div class="panel-body">Experiencia en alguno de los siguientes: MySQL, Oracle, T-SQL y PL/SQL, Windows Server, Ajax, Apache, Java Hibernate, C#, .Net, PHP, Perl, Visual Studio, VMware.</div>
					</div>
					<div class="panel panelOffer">
						<div class="panel-body">Razonamiento abstracto y analítico bien entrenado.</div>
					</div>
					<div class="panel panelOffer">
						<div class="panel-body">Conocimiento de administración de procesos de negocios.</div>
					</div>
					<div class="panel panelOffer">
						<div class="panel-body">Personalidad comunicativa y orientada al trabajo en equipo.</div>
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
