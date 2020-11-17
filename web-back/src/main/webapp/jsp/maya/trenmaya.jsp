<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<link href="https://framework-gb.cdn.gob.mx/assets/styles/main.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdn.datatables.net/1.10.20/css/dataTables.bootstrap4.min.css">
<style>
			@font-face {
			    font-family: alright-regular;
			    src: url(css/images/AlrightSans-Regular-v3.otf);
			    font-weight: normal;
			}
			@font-face {
			    font-family: alright-regular;
			    src: url(css/images/AlrightSans-Bold-v3.otf);
			    font-weight: bold;
			}
			@font-face {
			    font-family: alright-light;
			    src: url(css/images/AlrightSans-Light-v3.otf);
			}
			.slogan h2 {
				font-family: alright-regular;
				font-weight: bold;
				color: #696aa7;
				text-align: center;
				font-size: 42px;
				margin-top: 60px;
			}
			.slogan p {
				color: #5c5c5c;
				font-family: alright-light;
				font-size: 22px;
				text-align: center;
				margin-top: 20px;
			}
			.slogan p span {
				font-family: alright-regular;
				font-weight: bold;
			}
			.ofertas ul {
				padding: 0;
			}
			.ofertas li {
				border: 1px solid #5c5c5c;
			    height: auto;
			    list-style: outside none none;
			    margin: 20px 19px;
			    padding: 10px;
			    text-align: center;
			}
			.ofertas li em {
				color: #5a6300;
			    display: block;
			    font-family: alright-regular;
			    font-size: 16px;
			    font-style: normal;
			    font-weight: bold;
			    height: 63px;
			    line-height: 16px;
			    overflow: hidden;
			}
			.ico-pais {
				background-repeat: no-repeat;
				display: inline-block;
				width: 53px;
				height: 53px;
			}
			.ico-pais.canada {
				background-image: url(css/images/TrenMaya_horizontal_1.png);
			}
			.ofertas li p {
				color: #5c5c5c;
				font-family: alright-light;
				margin: 0;
				height: 40px;
			}
			.contact-links {
				color: #5c5c5c;
				font-family: alright-light;
				font-size: 28px;
				text-align: center;
				padding-bottom: 120px;
			}
			.contact-links > div {
				margin-top: 57px;
				margin-bottom: 17px;
			}
			.contact-links .contact-opts {
				display: inline-block;
				margin: 0 30px;
			}
			.contact-links img {
				display: inline-block;
				max-width: 105px;
				margin-bottom: 20px;
			}
			.offer-link {
				position: relative;
			}
			.offer-link:hover a {
				display: block;
			} 
			.offer-link a {
				background-image: url(css/images/offer-hover.png);
				background-repeat: no-repeat;
				background-position: bottom;
			    display: none;
			    height: 100%;
			    left: 0;
			    position: absolute;
			    top: 0;
			    width: 100%;
			    z-index: 3000;
			}
			.offer-link a span {
				background-color: #0086ab;
				color: #ffffff;
				display: inline-block;
				font-weight: bold;
				font-size: 22px;
				padding: 10px 20px;
				position: relative;
				bottom: -60%;
				-webkit-border-radius: 3px;
				-moz-border-radius: 3px;
				border-radius: 3px;
			}
			.slogan-2 p {
				font-size: 32px;
			}
			.r-sociales a {
				display: inline-block;
			    height: 56px;
			    margin: 10px 15px 0;
			    text-indent: -9000px;
			    width: 56px;
			    background-repeat: no-repeat;
			}
			.facebook-icon {
				background-image: url(css/images/icon-facebook.png);
			    background-repeat: no-repeat;
			    display: inline-block;
			    text-indent: -9000px;
			    width: 56px;
			    height: 56px;
			    margin: 0 10px;
			}
			.tweeter-icon {
				background-image: url(css/images/icon-tweeter.png);
			    background-repeat: no-repeat;
			    display: inline-block;
			    text-indent: -9000px;
			    width: 56px;
			    height: 56px;
			    margin: 0 10px;
			}
			.contact-links a {
				text-decoration: underline;
			}
			
			.pagina, .current {
			    background-color: #d4d6d6;
				-webkit-border-radius: 3px;
				-moz-border-radius: 3px;
				border-radius: 3px;
			    color: #000;
			    display: block;
			    height: 23px;
			    margin: 0 2px;
			    padding-top: 3px;
			    text-decoration: none;
			    width: 26px;
			}
			.paginator li {
			    background-color: transparent !important;
			    border: medium none !important;
			    display: inline-block;
			    margin: 1px;
			    padding: 0 !important;
			}
			.paginator .current {
			    background-color: #474747;
			    color: #fff;
			}
			.paginator {
			    font-size: 12px;
			    text-align: center;
			}
			#numPagina {
			    background-color: #fff;
			    border: 1px solid #acb4b4;
			    height: 25px;
			    margin-left: 10px;
			    width: 40px;
			}
			.paginator .ir {
			    background-color: #474747;
			    color: #fff;
			    cursor: pointer;
			    height: 27px;
			    margin-left: 10px;
			    padding-left: 6px;
			    padding-right: 6px;
			    padding-top: 4px;
			    border: none;
			    -webkit-border-radius: 3px;
				-moz-border-radius: 3px;
				border-radius: 3px;
			}
			.accesible {
				height: 1px;
			    overflow: hidden;
			    width: 1px;
			}
				.portrait {
					display: none;
				}
				.landscape {
					display: none;
				}
				.desk {
					display: block;
				}
			
			@media (max-width:768px){
				.portrait {
					display: none;
				}
				.landscape {
					display: block;
				}
				.desk {
					display: none;
				}
				.contact-links {
					font-size: 22px;
				}
				.slogan h2,
				.slogan-2 p {
				    font-size: 30px;
				}
			}
			@media (max-width:568px){
				.portrait {
					display: block;
				}
				.landscape {
					display: none;
				}
				.desk {
					display: none;
				}
				.slogan p span {
					font-size: 14px;
				}
				.contact-links {
					font-size: 22px;
				}
				.slogan h2,
				.slogan-2 p {
				    font-size: 22px;
				}
			}
		</style>
<body>
	<br/>
	<br/>
	<div class="container">
		<div class="row">
			<div class="col-sm-7">
				<div class="header">
					<a href="https://www.empleo.gob.mx/"><img alt="Portal del Empleo : llama al 800 841 20 20" src="css/images/m_empleoGob.png" class="img-responsive" /></a>
				</div>
			</div>
			<div class="col-sm-5 hidden-xs">
				<div class="col-xs-6 logoSTPS">
					<a href="https://www.gob.mx/stps/"><img alt="Secretaria del Trabajo y Prevision Social" src="css/images/Trabajo_SNE_horizontal.png" class="img-responsive" /></a>
				</div>
				<div class="col-xs-6 logoSNE">
					<img alt="Banner de Vacantes para el tren maya" src="css/images/TrenMaya_horizontal_1.png" class="img-responsive"/>
				</div>
			</div>
		</div>
		<div class="row">
				<div class="col-sm-12" style="position: relative;">
					<div class="page-header">
						<h1 align="center">Ofertas de empleo en el ¡Tren Maya!</h1>
					</div>
				</div>
				<div class="clearfix"></div>
		</div>
		<div class="row">
				<div class="row slogan slogan-2">
						<img alt="Banner de Vacantes para el tren maya" src="css/images/TM_Banner_Web_v2.jpg" width="100%" />
				</div>
		</div>
		<br/>
		<br/>
		<table id="tablax" class="table table-striped table-bordered" style="width:100%">
			<thead>
				<th>Titulo de la oferta</th>
				<th>Salario</th>
				<th>Fecha publicación</th>
				<th>ver oferta</th>
			</thead>
			<tbody>
				<tr>
					<td>Auxiliar control de costos</td>
					<td> $10,000.00 </td>
					<td>04/09/2020</td>
					<td><a href="https://www.empleo.gob.mx/4374493-oferta-de-empleo-de-auxiliar-control-costos-consorcio-tramo-dos-sa" target="_blank">Ver oferta</a></td>
				</tr>
				<tr>
					<td>Contador de Obra</td>
					<td> $ 10,000.00</td>
					<td>08/10/2020</td>
					<td><a href="https://www.empleo.gob.mx/4382147-oferta-de-empleo-de-contador-de-obra-consorcio-tramo-dos-sa-cv" target="_blank">Ver oferta</a></td>
				</tr>
				<tr>
					<td>Supervisor de acsma</td>
					<td> $ 22,798.00</td>
					<td>21/10/2020</td>
					<td><a href="https://www.empleo.gob.mx/4387657-oferta-de-empleo-de-supervisor-de-acsma-servicios-tecnicos-y-admin" target="_blank">Ver oferta</a></td>
				</tr>
				<tr>
					<td>Medico ocupacional</td>
					<td> $ 19,689.00</td>
					<td>21/10/2020</td>
					<td><a href="https://www.empleo.gob.mx/4387706-oferta-de-empleo-de-medico-ocupacional-servicios-tecnicos-y-admini" target="_blank">Ver oferta</a></td>
				</tr>
				<tr>
					<td>Paramedico</td>
					<td> $ 12,683.00</td>
					<td>21/10/2020</td>
					<td><a href="https://www.empleo.gob.mx/4387801-oferta-de-empleo-de-paramedico-servicios-tecnicos-y-administrativo" target="_blank">Ver oferta</a></td>
				</tr>
				<tr>
					<td>Operador de maquinaria</td>
					<td> $ 11,791.00</td>
					<td>21/10/2020</td>
					<td><a href="https://www.empleo.gob.mx/4388332-oferta-de-empleo-de-operador-de-maquinaria" target="_blank">Ver oferta</a></td>
				</tr>
				<tr>
					<td>Operador de rodillo liso</td>
					<td> $ 8,521.00</td>
					<td>21/10/2020</td>
					<td><a href="https://www.empleo.gob.mx/4388728-oferta-de-empleo-de-operador-de-rodillo-liso" target="_blank">Ver oferta</a></td>
				</tr>
				<tr>
					<td>Auxiliar control de costos</td>
					<td> $10,000.00 </td>
					<td>04/09/2020</td>
					<td><a href="https://www.empleo.gob.mx/4374493-oferta-de-empleo-de-auxiliar-control-costos-consorcio-tramo-dos-sa" target="_blank">Ver oferta</a></td>
				</tr>
				<tr>
					<td>Oficial soldador</td>
					<td> $6,000.00 </td>
					<td>04/09/2020</td>
					<td><a href="https://www.empleo.gob.mx/4377832-oferta-de-empleo-de-oficial-de-soldador-consorcio-tramo-dos-sa-cv" target="_blank">Ver oferta</a></td>
				</tr>
				<tr>
					<td>Oficial mecánico especializado</td>
					<td> $10,000.00 </td>
					<td>04/09/2020</td>
					<td><a href="https://www.empleo.gob.mx/4377845-oferta-de-empleo-de-oficial-mecanico-especializado-consorcio-tramo" target="_blank">Ver oferta</a></td>
				</tr>
				<tr>
					<td>Oficial de electromecánico</td>
					<td> $10,000.00 </td>
					<td>04/09/2020</td>
					<td><a href="https://www.empleo.gob.mx/4377852-oferta-de-empleo-de-oficial-electromecanico-consorcio-tramo-dos-sa" target="_blank">Ver oferta</a></td>
				</tr>
				<tr>
					<td>Supervisor Ambiente/obra</td>
					<td> $ 22,441.00</td>
					<td>10/09/2020</td>
					<td><a href="https://www.empleo.gob.mx/4387636-oferta-de-empleo-de-supervisor-ambiente-obra" target="_blank">Ver oferta</a></td>
				</tr>
				<tr>
					<td>Técnico de Salud y Seguridad de obra</td>
					<td> $ 18,797.00</td>
					<td>10/09/2020</td>
					<td><a href="https://www.empleo.gob.mx/4387635-oferta-de-empleo-de-tecnico-de-salud-y-seguridad-de-obra" target="_blank">Ver oferta</a></td>
				</tr>
				<tr>
					<td>Técnico estimaciones obra</td>
					<td> $ 23,595.00</td>
					<td>10/09/2020</td>
					<td><a href="https://www.empleo.gob.mx/4387584-oferta-de-empleo-de-tecnico-estimaciones-obra" target="_blank">Ver oferta</a></td>
				</tr>
				<tr>
					<td>Técnico Control de Calidad</td>
					<td> $ 18,797.00</td>
					<td>10/09/2020</td>
					<td><a href="https://www.empleo.gob.mx/4387558-oferta-de-empleo-de-tecnico-control-de-calidad" target="_blank">Ver oferta</a></td>
				</tr>
				<tr>
					<td>Técnico Topográfía</td>
					<td> $23,595.00 </td>
					<td>10/09/2020</td>
					<td><a href="https://www.empleo.gob.mx/4387578-oferta-de-empleo-de-tecnico-en-topografia--obra-consorcio-lamat-tr" target="_blank">Ver oferta</a></td>
				</tr>
				<tr>
					<td>Operador revolverdora</td>
					<td> $6,500.00 </td>
					<td>04/09/2020</td>
					<td><a href="https://www.empleo.gob.mx/4387406-oferta-de-empleo-de-operador-revolvedora-cemex-operaciones-mexico" target="_blank">Ver oferta</a></td>
				</tr>
				<tr>
					<td>Topografos y Cadeneros</td>
					<td> $19,000.00 </td>
					<td>10/09/2020</td>
					<td><a href="https://www.empleo.gob.mx/4387411-oferta-de-empleo-de-topografos-aux-topografia-cadeneros-capital-hu" target="_blank">Ver oferta</a></td>
				</tr>
				<tr>
					<td>Coordinadores de Obra</td>
					<td> $30,000.00 </td>
					<td>04/09/2020</td>
					<td><a href="https://www.empleo.gob.mx/4387416-oferta-de-empleo-de-coordinadores-obra-capital-humano-especializad" target="_blank">Ver oferta</a></td>
				</tr>
				<tr>
					<td>Supervisores</td>
					<td> $19,000.00 </td>
					<td>04/09/2020</td>
					<td><a href="https://www.empleo.gob.mx/4387418-oferta-de-empleo-de-supervisores-capital-humano-especializado-kra-" target="_blank">Ver oferta</a></td>
				</tr>
				<tr>
					<td>Contador de obra</td>
					<td> $10,000.00 </td>
					<td>04/09/2020</td>
					<td><a href="https://www.empleo.gob.mx/4382147-oferta-de-empleo-de-contador-de-obra-consorcio-tramo-dos-sa-cv" target="_blank">Ver oferta</a></td>
				</tr>
				<tr>
					<td>Operador Tracto caminón con cama baja</td>
					<td> $10,000.00 </td>
					<td>04/09/2020</td>
					<td><a href="https://www.empleo.gob.mx/4377863-oferta-de-empleo-de-operador-tractocamion-cama-baja-consorcio-tram" target="_blank">Ver oferta</a></td>
				</tr>
				<tr>
					<td>Operador pipa diesel</td>
					<td> $8,000.00 </td>
					<td>04/09/2020</td>
					<td><a href="https://www.empleo.gob.mx/4377862-oferta-de-empleo-de-operador-pipa-diesel-consorcio-tramo-dos-sa-cv" target="_blank">Ver oferta</a></td>
				</tr>
			</tbody>
		</table>
			<div class="clearfix"></div>
			<div class="row slogan slogan-2">
				<div class="col-md-12">
					<h2>¿Tienes dudas?</h2>
					<p>No te preocupes, estamos para apoyarte:</p>
				</div>
			</div>
			<div class="row contact-links">
				<div class="contact-opts">
					<img alt="" src="css/images/ico-llamada.png" class="img-responsive" /></br>
					Comunícate al</br>
					800 841 2020
				</div>
				<div class="contact-opts">
					<img alt="" src="css/images/ico-rSociales.png" class="img-responsive" /></br>
					Visítanos en:</br>
					<a class="facebook-icon" href="https://www.facebook.com/empleogobmx" target="_blank">Facebook</a>
					<a class="tweeter-icon" href="https://twitter.com/empleogob_mx" target="_blank">Tweeter</a>
				</div>
			</div>	
		</div>
		<!-- JQUERY -->
    <script src="https://code.jquery.com/jquery-3.4.1.js"
        integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU=" crossorigin="anonymous">
        </script>
    <!-- DATATABLES -->
    <script src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.min.js">
    </script>
	<script src="https://cdn.datatables.net/1.10.20/js/dataTables.bootstrap4.min.js">
    </script>
    <script>
        $(document).ready(function () {
            $('#tablax').DataTable({
                language: {
                    processing: "Tratamiento en curso...",
                    search: "Buscar&nbsp;:",
                    lengthMenu: "Agrupar de _MENU_ ofertas",
                    info: "Mostrando oferta _START_ al _END_ de un total de _TOTAL_ ofertas",
                    infoEmpty: "No existen datos.",
                    infoFiltered: "(filtrado de _MAX_ elementos en total)",
                    infoPostFix: "",
                    loadingRecords: "Cargando...",
                    zeroRecords: "No se encontraron datos con tu busqueda",
                    emptyTable: "No hay datos disponibles en la tabla.",
                    paginate: {
                        first: "Primero",
                        previous: "Anterior",
                        next: "Siguiente",
                        last: "Ultimo"
                    },
                    aria: {
                        sortAscending: ": active para ordenar la columna en orden ascendente",
                        sortDescending: ": active para ordenar la columna en orden descendente"
                    }
                },
                scrollY: 400,
                lengthMenu: [ [10, 25, 50, -1], [10,25,50] ],
            });
        });
    </script>
		<script src="https://framework-gb.cdn.gob.mx/gobmx.js"></script>
</body>
</html>