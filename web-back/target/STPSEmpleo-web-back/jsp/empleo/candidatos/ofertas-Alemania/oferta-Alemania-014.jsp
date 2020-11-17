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
		Detalle de la oferta. Ingeniero Industrial 
	</jsp:attribute>
	<jsp:body>

    <!-- Contenido -->
	<div class="row">
		<div class="col-sm-12">
			<div class="page-header">
				<h3>Detalle de la oferta <small>Ingeniero Industrial</small></h3>
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
									<span>Ingeniero Industrial</span>
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
									<span>6</span>
								</div>
							</div>
						</div>
						<div class="col-sm-6">
							<div class="panel panelOfferDetalle">
								<div class="panel-body">
									<strong>Estudios:</strong><br>
									<span>Grado universitario como Ingeniero Industrial (e.g. Ingeniero Mecánico, Ingeniero Eléctrico, etc.) + especialización posterior en el campo de desarrollo de negocios.</span>
								</div>
							</div>
						</div>
						<div class="col-sm-6">
							<div class="panel panelOfferDetalle">
								<div class="panel-body">
									<strong>Homologación:</strong><br>
									<span>La resolución de equivalencia (homologación), le ayudará a ejercer su profesión, pero no es requisito imprescindible. Esto significa que también puede acceder al mercado laboral como Ingeniero con un título extranjero, aunque no tenga la resolución formal de equivalencia.</span>
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
									<span>Mínimo B2, debido al constante intercambio de ideas con los colegas y clientes.</span>
								</div>
							</div>
						</div>
						<div class="col-sm-6">
							<div class="panel panelOfferDetalle">
								<div class="panel-body">
									<strong>Otros Idiomas:</strong><br>
									<span>Inglés – mínimo B2, idiomas adicionales son un plus para el candidato.</span>
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
									<span>Experiencia previa en el puesto.</span>
								</div>
							</div>
						</div>
						<div class="col-sm-6">
							<div class="panel panelOfferDetalle">
								<div class="panel-body">
									<strong>Habilidades adicionales:</strong><br>
									<span>Flexibilidad para trabajar dentro de Alemania y a nivel internacional.</span>
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
						<div class="panel-body">Los Ingenieros Industriales planean, revisan y mejoran las operaciones relacionadas a eficiencia técnica a los más altos estándares de optimización económica en varias áreas funcionales de la empresa.</div>
					</div>
					<div class="panel panelOfferOdd">
						<div class="panel-body">Aseguramiento en la producción y calidad.</div>
					</div>
					<div class="panel panelOfferOdd">
						<div class="panel-body">Supervisión en la logística materiales.</div>
					</div>
					<div class="panel panelOffer">
						<div class="panel-body">Marketing, ventas y soporte al cliente.</div>
					</div>
					<div class="panel panelOfferOdd">
						<div class="panel-body">Contabilidad y supervisión.</div>
					</div>
					<div class="panel panelOffer">
						<div class="panel-body">Aplicaciones IT y sistemas.</div>
					</div>
					<div class="panel panelOfferOdd">
						<div class="panel-body">Recursos Humanos.</div>
					</div>
					<div class="panel panelOfferOdd">
						<div class="panel-body">Los Ingenieros Industriales trabajan en una gran variedad de áreas.</div>
					</div>
					<div class="panel panelOfferOdd">
						<div class="panel-body">Producción: análisis y optimización en procesos comerciales.</div>
					</div>
					<div class="panel panelOfferOdd">
						<div class="panel-body">Producción: desarrollo e.g. nuevos procesos, así como ser responsable de su inducción dentro de la línea de producción.</div>
					</div>
					<div class="panel panelOfferOdd">
						<div class="panel-body">Logística, compras y distribución; estrategias de diseño, para la procuración de materiales, así como la distribución de productos.</div>
					</div>
					<div class="panel panelOfferOdd">
						<div class="panel-body">Sector IT: para desarrollo por ejemplo de solución de sistemas en particular para grupo de clientes en específico.</div>
					</div>
					<div class="panel panelOfferOdd">
						<div class="panel-body">Supervisión de Calidad: Donde se incluye a los empleados en procedimientos de control de calidad, así como apoyarles en la implementación de normas.</div>
					</div>
					<div class="panel panelOfferOdd">
						<div class="panel-body">Supervisión y consejería empresarial.</div>
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
