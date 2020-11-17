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
		Detalle de la oferta. Ingeniero Mecánico 
	</jsp:attribute>
	<jsp:body>

    <!-- Contenido -->
	<div class="row">
		<div class="col-sm-12">
			<div class="page-header">
				<h3>Detalle de la oferta <small>Ingeniero Mecánico</small></h3>
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
									<span>Ingeniero Mecánico</span>
								</div>
							</div>
						</div>
						<div class="col-sm-6">
							<div class="panel panelOfferDetalle">
								<div class="panel-body">
									<strong>Salario mensual*:</strong><br>
									<span>$75,000.00 aprox.</span>
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
									<span>13</span>
								</div>
							</div>
						</div>
						<div class="col-sm-6">
							<div class="panel panelOfferDetalle">
								<div class="panel-body">
									<strong>Estudios:</strong><br>
									<span>Grado Universitario como Ingeniero Mecánico (e.g. Ingeniero Eléctrico, etc.) haber terminado como adjunto o técnico superior de Ingeniera Mecánica.</span>
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
									<span>Mínimo B1, debido a la constante interacción con colegas y clientes.</span>
								</div>
							</div>
						</div>
						<div class="col-sm-6">
							<div class="panel panelOfferDetalle">
								<div class="panel-body">
									<strong>Otros Idiomas:</strong><br>
									<span>Inglés – mínimo B2, idiomas adicionales serían un plus.</span>
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
									<span>Previa experiencia en el puesto.</span>
								</div>
							</div>
						</div>
						<div class="col-sm-6">
							<div class="panel panelOfferDetalle">
								<div class="panel-body">
									<strong>Habilidades adicionales:</strong><br>
									<span>Habilidades diversas dependiendo de las tareas y actividades, ser comunicativo.</span>
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
									<span>Jornada laboral a la semana: 40 hrs. Pago: Mensual. Inicio de contrato: Tan pronto como sea posible. Flexibilidad para trabajar en: Alemania y de manera internacional
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
						<div class="panel-body">Los Ingenieros Mecánicos investigan, diseñan, desarrollan, edifican además de comprobar sensores térmicos y mecánicos, además, así como instrumentos que incluyen herramientas, máquinas y maquinaria..</div>
					</div>
					<div class="panel panelOfferOdd">
						<div class="panel-body">Servicio de Ingeniería.</div>
					</div>
					<div class="panel panelOfferOdd">
						<div class="panel-body">Manufactura de Maquinaria.</div>
					</div>
					<div class="panel panelOffer">
						<div class="panel-body">Manufacturación de producto electrónico y de computadoras.</div>
					</div>
					<div class="panel panelOfferOdd">
						<div class="panel-body">Investigación y desarrollo en física, ingeniería y biociencias.</div>
					</div>
					<div class="panel panelOffer">
						<div class="panel-body">Productos aeroespaciales y de movimiento, así como manufacturación de partes.</div>
					</div>
					<div class="panel panelOfferOdd">
						<div class="panel-body">Análisis de problemas para observar como los instrumentos mecánicos y térmicos podrían ayudar a solventar un problema.</div>
					</div>
					<div class="panel panelOfferOdd">
						<div class="panel-body">Diseñar y rediseñar instrumentos mecánicos y térmicos o subsistemas, haciendo uso de análisis y diseño de apoyo computacional(CAD).</div>
					</div>
					<div class="panel panelOfferOdd">
						<div class="panel-body">Desarrollo y prueba de prototipos de instrumentos de diseño.</div>
					</div>
					<div class="panel panelOfferOdd">
						<div class="panel-body">Analizar la prueba de resultados y el cambio de diseño o sistema como sea requerido.</div>
					</div>
					<div class="panel panelOfferOdd">
						<div class="panel-body">Revisión del proceso manufacturero de los instrumentos.</div>
					</div>
					<div class="panel panelOfferOdd">
						<div class="panel-body">Diseño y revisión de la manufactura de muchos productos de cobertura médica.</div>
					</div>
					<div class="panel panelOfferOdd">
						<div class="panel-body">Instrumentos de baterías nuevas.</div>
					</div>
					<div class="panel panelOfferOdd">
						<div class="panel-body">Diseño de máquinas generadoras de poder tales como generadores eléctricos, máquinas de combustión interna, turbinas de vapor y gas, así como maquinarias de uso de energía tales como sistemas de refrigeración y aire acondicionado.</div>
					</div>
					<div class="panel panelOfferOdd">
						<div class="panel-body">Diseño de otro tipo de máquinas tales como elevadores y escaleras eléctricas, así como también sistemas de manipulación de material, sistemas de banda transportadora y estaciones de transferencia automatizada.</div>
					</div>
					<div class="panel panelOfferOdd">
						<div class="panel-body">Integración de sensores, controladores y maquinaria.</div>
					</div>
					<div class="panel panelOfferOdd">
						<div class="panel-body">La tecnología computacional apoya a los Ingenieros Mecánicos a crear y analizar diseños, así como echar andar simulaciones y probar de qué manera una máquina es afín a ser eficiente en el trabajo, interactuar con sistemas conectados, así como generar especificaciones por partes.</div>
					</div>
					<div class="panel panelOfferOdd">
						<div class="panel-body">Habilidades en herramientas de diseño 2D-/3D en software, programas o sistemas CAD/CAM o CAE.</div>
					</div>
				</div>
				<div class="col-sm-12">
					<div class="panel panelOfferDetalle">
						<div class="panel-body">
							<span>*Salario promedio, 45,000 € (salario bruto, depende del área geográfica en Alemania, lugar de Trabajo y experiencia profesional).</span>
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
