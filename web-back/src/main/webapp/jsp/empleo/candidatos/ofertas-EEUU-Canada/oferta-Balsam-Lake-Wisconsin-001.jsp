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
		Detalle de la oferta. Ingeniero Agrónomo
	</jsp:attribute>
	<jsp:body>

    <!-- Contenido -->
	<div class="row">
		<div class="col-sm-12">
			<div class="page-header">
				<h3>Detalle de la oferta <small>Ingeniero Agrónomo</small></h3>
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
									<span>Jefe de Equipo en Granja Orgánica/Supervisor de Primera Línea</span>
								</div>
							</div>
						</div>
						<div class="col-sm-6">
							<div class="panel panelOfferDetalle">
								<div class="panel-body">
									<strong>Salario neto:</strong><br>
									<span>USD $ 14.79/ hora dólares estadounidense, (sujeto a negociación)</span>
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
									<span>Balsam Lake, Wisconsin</span>
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
									<strong>Estudios:</strong><br>
									<span>Ingeniero Agrónomo (Titulado), Horticultura, Agronomía, Agricultura y/o cultivador de planta</span>
								</div>
							</div>
						</div>
						<div class="col-sm-6">
							<div class="panel panelOfferDetalle">
								<div class="panel-body">
									<strong>Sexo:</strong><br>
									<span>Masculino.</span>
								</div>
							</div>
						</div>
						<div class="col-sm-6">
							<div class="panel panelOfferDetalle">
								<div class="panel-body">
									<strong>Inglés obligatorio:</strong><br>
									<span>80% de interacción con el idioma, (conversación).</span>
								</div>
							</div>
						</div>
						<div class="col-sm-6">
							<div class="panel panelOfferDetalle">
								<div class="panel-body">
									<strong>Duración del Contrato:</strong><br>
									<span>De marzo a noviembre con posibilidad de extenderse a 3 años</span>
								</div>
							</div>
						</div>
						<div class="col-sm-6">
							<div class="panel panelOfferDetalle">
								<div class="panel-body">
									<strong>Experiencia laboral:</strong><br>
									<span>3 años de experiencia.</span>
								</div>
							</div>
						</div>
						<div class="col-sm-6">
							<div class="panel panelOfferDetalle">
								<div class="panel-body">
									<strong>Herramientas de trabajo:</strong><br>
									<span>Tractor y Herramientas agrícolas manuales.</span>
								</div>
							</div>
						</div>
						<div class="col-sm-6">
							<div class="panel panelOfferDetalle">
								<div class="panel-body">
									<strong>Información adicional:</strong><br>
									<span>Jornada laboral a la semana: 55 hrs.</span>
								</div>
							</div>
						</div>
						<div class="col-sm-12">
							<div class="panel panelOfferDetalle">
								<div class="panel-body">
									<strong>Requisito adicional:</strong><br>
									<span>Experiencia previa: haber laborado una o más temporadas completas en una granja que produce vegetales (no necesariamente orgánica).</span>
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
						<div class="panel-body">De manera manual siembran, cultivan, y cosechan verduras.  Usan herramientas manuales tales como palas, desplantador, azadón, pisón, ganchos de poda, tijeras, y cuchillos.  Las tareas podrán incluir labrar y aplicar fertilizante, trasplantar, desmalezar, reducción, o poda de cosecha; o limpiar, clasificar, sortear, empacar, y cargar producto cosechado.  Así mismo podrán construir enrejados, trabajo con tractor, trabajo en invernadero, reparar cercas y edificios de granja, y participar en actividades de irrigación..</div>
					</div>
					<div class="panel panelOffer">
						<div class="panel-body">Utilizar equipo de motor/mecanizado.</div>
					</div>
					<div class="panel panelOfferOdd">
						<div class="panel-body">Supervisar la implementación de un plan diario. Supervisar la cosecha y empaque de vegetales.</div>
					</div>
					<div class="panel panelOffer">
						<div class="panel-body">Supervisor en trabajo en equipo.</div>
					</div>
					<div class="panel panelOfferOdd">
						<div class="panel-body">Desarrollar y supervisar todas las tareas manuales de siembra, uso de herramientas y tareas adicionales referidas al buen mantenimiento y desarrollo de las tareas como Supervisor de Primera Línea.</div>
					</div>
					
				</div>
			</div>	
		</div>
		<div class="clearfix"></div>
	</div>
	</jsp:body>
</t:publicTemplate>
