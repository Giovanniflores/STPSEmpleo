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
						<h1 align="center">Ofertas de empleo en el °Tren Maya!</h1>
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
				<th>Fecha publicaciÛn</th>
				<th>ver oferta</th>
			</thead>
			<tbody>
				<tr>
					<td>Supervisores</td>
					<td>$16,000.00</td>
					<td>13/05/2021</td>
					<td><a href="https://www.empleo.gob.mx/4498293-oferta-de-empleo-de-supervisores-pro-sevitium-adminstracion-especi" target="_blank">Ver oferta</a></td>
				</tr>
				<tr>
					<td>Abogados</td>
					<td>$20,000.00</td>
					<td>13/05/2021</td>
					<td><a href="https://www.empleo.gob.mx/4498281-oferta-de-empleo-de-abogados-pro-sevitium-adminstracion-especializ" target="_blank">Ver oferta</a></td>
				</tr>
				<tr>
					<td>Jefe de Planilla</td>
					<td>$16,000.00</td>
					<td>13/05/2021</td>
					<td><a href="https://www.empleo.gob.mx/4498217-oferta-de-empleo-de-jefe-de-planilla-china-communications-construc" target="_blank">Ver oferta</a></td>
				</tr>
				<tr>
					<td>Topografos</td>
					<td>$18,000.00</td>
					<td>13/05/2021</td>
					<td><a href="https://www.empleo.gob.mx/4498192-oferta-de-empleo-de-topografo-china-communications-construction-co" target="_blank">Ver oferta</a></td>
				</tr>
				<tr>
					<td>Arquitecto Q/C</td>
					<td>$19,000.00</td>
					<td>13/05/2021</td>
					<td><a href="https://www.empleo.gob.mx/4498182-oferta-de-empleo-de-arquitecto-qc-china-communications-constructio" target="_blank">Ver oferta</a></td>
				</tr>
				<tr>
					<td>Ingeniero Civil</td>
					<td>$19,000.00</td>
					<td>13/05/2021</td>
					<td><a href="https://www.empleo.gob.mx/4498169-oferta-de-empleo-de-ingeniero-civil-china-communications-construct" target="_blank">Ver oferta</a></td>
				</tr>
				<tr>
					<td>Plomeros</td>
					<td>$10,000.00</td>
					<td>13/05/2021</td>
					<td><a href="https://www.empleo.gob.mx/4498138-oferta-de-empleo-de-plomero-china-communications-construction-comp" target="_blank">Ver oferta</a></td>
				</tr>
				<tr>
					<td>Mecanico (diesel y gasolina)</td>
					<td>$9,500.00</td>
					<td>13/05/2021</td>
					<td><a href="https://www.empleo.gob.mx/4498161-oferta-de-empleo-de-mecanido-diesel-y-gasolina-china-communication" target="_blank">Ver oferta</a></td>
				</tr>
				<tr>
					<td>Soldador</td>
					<td>$9,200.00</td>
					<td>13/05/2021</td>
					<td><a href="https://www.empleo.gob.mx/4498133-oferta-de-empleo-de-soldador-china-communications-construction-com" target="_blank">Ver oferta</a></td>
				</tr>
				<tr>
					<td>Carpinteros</td>
					<td>$9,200.00</td>
					<td>13/05/2021</td>
					<td><a href="https://www.empleo.gob.mx/4498112-oferta-de-empleo-de-carpinteros-china-communications-construction-" target="_blank">Ver oferta</a></td>
				</tr>
				<tr>
					<td>Herreros</td>
					<td>$9,200.00</td>
					<td>13/05/2021</td>
					<td><a href="https://www.empleo.gob.mx/4498099-oferta-de-empleo-de-herreros-china-communications-construction-com" target="_blank">Ver oferta</a></td>
				</tr>
				<tr>
					<td>AlbaÒiles</td>
					<td>$9,500.00</td>
					<td>13/05/2021</td>
					<td><a href="https://www.empleo.gob.mx/4498092-oferta-de-empleo-de-albaniles-china-communications-construction-co" target="_blank">Ver oferta</a></td>
				</tr>
				<tr>
					<td>Ayudantes Generales</td>
					<td>$8,000.00</td>
					<td>13/05/2021</td>
					<td><a href="https://www.empleo.gob.mx/4498083-oferta-de-empleo-de-ayudantes-generales-china-communications-const" target="_blank">Ver oferta</a></td>
				</tr>
				<tr>
					<td>Tecnico en TopografÏa</td>
					<td>$13,500.00</td>
					<td>13/05/2021</td>
					<td><a href="https://www.empleo.gob.mx/4498074-oferta-de-empleo-de-tecnico-topografia-china-communications-constr" target="_blank">Ver oferta</a></td>
				</tr>
				<tr>
					<td>Mecanico Diesel</td>
					<td>$14,000.00</td>
					<td>13/05/2021</td>
					<td><a href="https://www.empleo.gob.mx/4495511-oferta-de-empleo-de-mecanico-diesel-servicios-tecnicos-y-administr" target="_blank">Ver oferta</a></td>
				</tr>
				<tr>
					<td>Mecanico General</td>
					<td>$12,000.00</td>
					<td>13/05/2021</td>
					<td><a href="https://www.empleo.gob.mx/4495517-oferta-de-empleo-de-mecanico-general-servicios-tecnicos-y-administ" target="_blank">Ver oferta</a></td>
				</tr>
				<tr>
					<td>Operado de pipa de combustible</td>
					<td>$17,000.00</td>
					<td>13/05/2021</td>
					<td><a href="https://www.empleo.gob.mx/4495534-oferta-de-empleo-de-operador-pipa-combustible-servicios-tecnicos-y" target="_blank">Ver oferta</a></td>
				</tr>
				<tr>
					<td>Oficial Electricista Corriente Directa</td>
					<td>$10,700.00</td>
					<td>13/05/2021</td>
					<td><a href="https://www.empleo.gob.mx/4495514-oferta-de-empleo-de-oficial-electricista-corriente-directa-servici" target="_blank">Ver oferta</a></td>
				</tr>
				<tr>
					<td>Maniobrista</td>
					<td>$12,800.00</td>
					<td>13/05/2021</td>
					<td><a href="https://www.empleo.gob.mx/4495897-oferta-de-empleo-de-maniobrista-servicios-tecnicos-y-administrativ" target="_blank">Ver oferta</a></td>
				</tr>
				<tr>
					<td>Auxiliar de Almacen</td>
					<td>$12,000.00</td>
					<td>13/05/2021</td>
					<td><a href="https://www.empleo.gob.mx/4496147-oferta-de-empleo-de-auxiliar-de-almacen-servicios-tecnicos-y-admin" target="_blank">Ver oferta</a></td>
				</tr>
				<tr>
					<td>Operador de Trituradora</td>
					<td>$17,000.00</td>
					<td>13/05/2021</td>
					<td><a href="https://www.empleo.gob.mx/4496130-oferta-de-empleo-de-operador-trituradora-servicios-tecnicos-y-admi" target="_blank">Ver oferta</a></td>
				</tr>
				<!--<tr>
					<td>Banderero vial </td>
					<td>$6,500.00</td>
					<td>19/04/2021</td>
					<td><a href="https://www.empleo.gob.mx/4479428-oferta-de-empleo-de-banderero-vial-servicios-tecnicos-y-administra" target="_blank">Ver oferta</a></td>
				</tr> -->
				<tr>
					<td>Operador de tractocompactador </td>
					<td>$10,898.00</td>
					<td>13/05/2021</td>
					<td><a href="https://www.empleo.gob.mx/4487523-oferta-de-empleo-de-operador-tractocompactador-servicios-tecnicos-" target="_blank">Ver oferta</a></td>
				</tr>
				<!-- <tr>
					<td>Operador de tractocompactador </td>
					<td>$10,898.00</td>
					<td>25/03/2021</td>
					<td><a href="https://www.empleo.gob.mx/4466953-oferta-de-empleo-de-operador-cargador-frontal-servicios-tecnicos-y" target="_blank">Ver oferta</a></td>
				</tr>-->
				<tr>
					<td>Operador de tractor </td>
					<td>$10,898.00</td>
					<td>13/05/2021</td>
					<td><a href="https://www.empleo.gob.mx/4487512-oferta-de-empleo-de-operador-de-tractor-servicios-tecnicos-y-admin" target="_blank">Ver oferta</a></td>
				</tr>
				<tr>
					<td>Operador de motoconformadora</td>
					<td>$10,898.00</td>
					<td>13/05/2021</td>
					<td><a href="https://www.empleo.gob.mx/4487547-oferta-de-empleo-de-operador-motoconformadora-servicios-tecnicos-y" target="_blank">Ver oferta</a></td>
				</tr>
				<!-- <tr>
					<td>Operador de excavadora</td>
					<td>$10,898.00</td>
					<td>25/03/2021</td>
					<td><a href="https://www.empleo.gob.mx/4466728-oferta-de-empleo-de-operador-excavadora-servicios-tecnicos-y-admin" target="_blank">Ver oferta</a></td>
				</tr> -->
				<tr>
					<td>Operador de retrocargador</td>
					<td>$10,898.00</td>
					<td>25/03/2021</td>
					<td><a href="https://www.empleo.gob.mx/4487532-oferta-de-empleo-de-operador-retrocargador-servicios-tecnicos-y-ad" target="_blank">Ver oferta</a></td>
				</tr>
				<tr>
					<td>Operador de compactador mixto</td>
					<td>$10,898.00</td>
					<td>25/03/2021</td>
					<td><a href="https://www.empleo.gob.mx/4466927-oferta-de-empleo-de-operador-compactador-mixto-servicios-tecnicos-" target="_blank">Ver oferta</a></td>
				</tr>
				<!-- <tr>
					<td>Checador de asistencia</td>
					<td>$10,898.00</td>
					<td>25/03/2021</td>
					<td><a href="https://www.empleo.gob.mx/4458726-oferta-de-empleo-de-checador-asistencia-servicios-tecnicos-y-admin" target="_blank">Ver oferta</a></td>
				</tr> -->
				 <tr>
					<td>Ayudante General</td>
					<td>$6,500.00</td>
					<td>13/05/2021</td>
					<td><a href="https://www.empleo.gob.mx/4495695-oferta-de-empleo-de-ayudante-general-servicios-tecnicos-y-administ" target="_blank">Ver oferta</a></td>
				</tr>
				<!-- <tr>
					<td>Operador de pipa diesel</td>
					<td>$8,000.00</td>
					<td>03/02/2021</td>
					<td><a href="https://www.empleo.gob.mx/4453361-oferta-de-empleo-de-operador-pipa-diesel-consorcio-tramo-dos-sa-cv" target="_blank">Ver oferta</a></td>
				</tr> -->
				<tr>
					<td>Medico ocupacional</td>
					<td> $18,000.00</td>
					<td>13/05/2021</td>
					<td><a href="https://www.empleo.gob.mx/4494826-oferta-de-empleo-de-medico-ocupacional-servicios-tecnicos-y-admini" target="_blank">Ver oferta</a></td>
				</tr>
				<tr>
					<td>Supervisor de seguridad laboral acsma</td>
					<td> $22,798.00</td>
					<td>19/04/2021</td>
					<td><a href="https://www.empleo.gob.mx/4470072-oferta-de-empleo-de-supervisor-seguridad-laboral-acsma-servicios-t" target="_blank">Ver oferta</a></td>
				</tr>
				<tr>
					<td>Instructor de seguridad laboral acsma</td>
					<td> $15,798.00</td>
					<td>26/02/2021</td>
					<td><a href="https://www.empleo.gob.mx/4470082-oferta-de-empleo-de-instructor-seguridad-laboral-acsma-servicios-t" target="_blank">Ver oferta</a></td>
				</tr>
				<!-- <tr>
					<td>Consultor sap</td>
					<td> $42,000.00</td>
					<td>26/02/2021</td>
					<td><a href="https://www.empleo.gob.mx/4448504-oferta-de-empleo-de-consultor-sap-fondo-nacional-fomento-al-turism" target="_blank">Ver oferta</a></td>
				</tr> -->
				<!-- <tr>
					<td>Analista de imss siroc</td>
					<td>$12,000.00</td>
					<td>03/03/2021</td>
					<td><a href="https://www.empleo.gob.mx/4450656-oferta-de-empleo-de-analista-imss-siroc-azvindi-ferroviario-sa-cv" target="_blank">Ver oferta</a></td>
				</tr> -->
				<!-- <tr>
					<td>Auxiliar administrativo</td>
					<td>$10,000.00</td>
					<td>03/03/2021</td>
					<td><a href="https://www.empleo.gob.mx/4452449-oferta-de-empleo-de-auxiliar-administrativo-azvindi-ferroviario-sa" target="_blank">Ver oferta</a></td>
				</tr> -->
				<!--<tr>
					<td>Dibujante cadista</td>
					<td>$15,000.00</td>
					<td>03/03/2021</td>
					<td><a href="https://www.empleo.gob.mx/4453183-oferta-de-empleo-de-dibujante-cadista-azvindi-ferroviario-sa-cv" target="_blank">Ver oferta</a></td>
				</tr> -->
				<!--<tr>
					<td>Auxiliar administrativo </td>
					<td>$10,000.00</td>
					<td>03/03/2021</td>
					<td><a href="https://www.empleo.gob.mx/4445013-oferta-de-empleo-de-auxiliar-administrativo-azvindi-ferroviario-sa" target="_blank">Ver oferta</a></td>
				</tr>-->
				<!--<tr>
					<td>Medico ocupacional</td>
					<td>$19,689.00</td>
					<td>18/11/2020</td>
					<td><a href="https://www.empleo.gob.mx/4387706-oferta-de-empleo-de-medico-ocupacional-servicios-tecnicos-y-admini" target="_blank">Ver oferta</a></td>
				</tr>-->
				<!--<tr>
					<td>Paramedico </td>
					<td>$12,683.00</td>
					<td>18/11/2020</td>
					<td><a href="https://www.empleo.gob.mx/4387801-oferta-de-empleo-de-paramedico-servicios-tecnicos-y-administrativo" target="_blank">Ver oferta</a></td>
				</tr>-->
				<!--<tr>
					<td>Supervisores</td>
					<td>$19,000.00</td>
					<td>18/11/2020</td>
					<td><a href="https://www.empleo.gob.mx/4404137-oferta-de-empleo-de-supervisores-capital-humano-especializado-kra-" target="_blank">Ver oferta</a></td>
				</tr>-->
				<!--<tr>
					<td>Encargado de campamentos</td>
					<td>$10,000.00</td>
					<td>18/11/2020</td>
					<td><a href="https://www.empleo.gob.mx/4404285-oferta-de-empleo-de-encargado-campamentos-consorcio-tramo-dos-sa-c" target="_blank">Ver oferta</a></td>
				</tr>-->
				<!--<tr>
					<td>Coordinador de nominas</td>
					<td>$3,700.00</td>
					<td>18/11/2020</td>
					<td><a href="https://www.empleo.gob.mx/4403770-oferta-de-empleo-de-coordinador-nominas-consorcio-tramo-dos-sa-cv" target="_blank">Ver oferta</a></td>
				</tr>-->
				<!--<tr>
					<td>Contador de obra</td>
					<td>$10,000.00</td>
					<td>18/11/2020</td>
					<td><a href="https://www.empleo.gob.mx/4403073-oferta-de-empleo-de-contador-de-obra-consorcio-tramo-dos-sa-cv" target="_blank">Ver oferta</a></td>
				</tr>-->
				<!--<tr>
					<td>Sobrestante</td>
					<td>$8,000.00</td>
					<td>29/10/2020</td>
					<td><a href="https://www.empleo.gob.mx/4411059-oferta-de-empleo-de-sobrestante-consorcio-tramo-dos-sa-cv" target="_blank">Ver oferta</a></td>
				</tr>-->
				<!--<tr>
					<td>Operador de tractocami√≥n cama baja</td>
					<td>$10,000.00</td>
					<td>29/10/2020</td>
					<td><a href="https://www.empleo.gob.mx/4411070-oferta-de-empleo-de-operador-tractocamion-cama-baja-consorcio-tram" target="_blank">Ver oferta</a></td>
				</tr>-->
				<!--<tr>
					<td>Operador de pipa di√©sel</td>
					<td>$8,000.00</td>
					<td>29/10/2020</td>
					<td><a href="https://www.empleo.gob.mx/4411088-oferta-de-empleo-de-operador-pipa-diesel-consorcio-tramo-dos-sa-cv" target="_blank">Ver oferta</a></td>
				</tr>-->
				<!--<tr>
					<td>Oficial mec√°nico especializado</td>
					<td>$10,000.00</td>
					<td>29/10/2020</td>
					<td><a href="https://www.empleo.gob.mx/4411106-oferta-de-empleo-de-oficial-mecanico-especializado-consorcio-tramo" target="_blank">Ver oferta</a></td>
				</tr>-->
				<!--<tr>
					<td>Oficial electromec√°nico</td>
					<td>$10,000.00</td>
					<td>29/10/2020</td>
					<td><a href="https://www.empleo.gob.mx/4411110-oferta-de-empleo-de-oficial-electromecanico-consorcio-tramo-dos-sa" target="_blank">Ver oferta</a></td>
				</tr>-->
				<!--<tr>
					<td>Oficial de soldador</td>
					<td>$6,000.00</td>
					<td>29/10/2020</td>
					<td><a href="https://www.empleo.gob.mx/4411115-oferta-de-empleo-de-oficial-de-soldador-consorcio-tramo-dos-sa-cv" target="_blank">Ver oferta</a></td>
				</tr>-->
				<!--<tr>
					<td>Lubricador</td>
					<td>$6,000.00</td>
					<td>29/10/2020</td>
					<td><a href="https://www.empleo.gob.mx/4411121-oferta-de-empleo-de-lubricador-consorcio-tramo-dos-sa-cv" target="_blank">Ver oferta</a></td>
				</tr>-->
				<!--<tr>
					<td>Llantero</td>
					<td>$6,000.00</td>
					<td>29/10/2020</td>
					<td><a href="https://www.empleo.gob.mx/4411125-oferta-de-empleo-de-llantero-consorcio-tramo-dos-sa-cv" target="_blank">Ver oferta</a></td>
				</tr>-->
				<!--<tr>
					<td>Ayudante de mec√°nico</td>
					<td>$6,000.00</td>
					<td>29/10/2020</td>
					<td><a href="https://www.empleo.gob.mx/4411129-oferta-de-empleo-de-ayudante-de-mecanico-consorcio-tramo-dos-sa-cv" target="_blank">Ver oferta</a></td>
				</tr>-->
				<!--<tr>
					<td>Ayudante de lubricador</td>
					<td>$6,000.00</td>
					<td>29/10/2020</td>
					<td><a href="https://www.empleo.gob.mx/4411135-oferta-de-empleo-de-ayudante-lubricador-consorcio-tramo-dos-sa-cv" target="_blank">Ver oferta</a></td>
				</tr>-->
				<!--<tr>
					<td>Auxiliar control de costos</td>
					<td>$10,000.00</td>
					<td>29/10/2020</td>
					<td><a href="https://www.empleo.gob.mx/4411140-oferta-de-empleo-de-auxiliar-control-costos-consorcio-tramo-dos-sa" target="_blank">Ver oferta</a></td>
				</tr>-->
				<!--<tr>
					<td>Auxiliar de sistemas</td>
					<td>$8,000.00</td>
					<td>29/10/2020</td>
					<td><a href="https://www.empleo.gob.mx/4394730-oferta-de-empleo-de-auxiliar-de-sistemas-consorcio-tramo-dos-sa-cv" target="_blank">Ver oferta</a></td>
				</tr>-->
				<!--<tr>
					<td>Checador de actividades</td>
					<td>$6,000.00</td>
					<td>29/10/2020</td>
					<td><a href="https://www.empleo.gob.mx/4411146-oferta-de-empleo-de-checador-actividades-consorcio-tramo-dos-sa-cv" target="_blank">Ver oferta</a></td>
				</tr>-->
				<!--<tr>
					<td>Oficial de tubero</td>
					<td>$6,000.00</td>
					<td>29/10/2020</td>
					<td><a href="https://www.empleo.gob.mx/4411151-oferta-de-empleo-de-oficial-de-tubero-consorcio-tramo-dos-sa-cv" target="_blank">Ver oferta</a></td>
				</tr>-->
				<tr>
					<td>Oficial de carpintero</td>
					<td>11,500.00</td>
					<td>13/05/2021</td>
					<td><a href="https://www.empleo.gob.mx/4496056-oferta-de-empleo-de-oficial-carpintero-consorcio-tramo-dos-sa-cv" target="_blank">Ver oferta</a></td>
				</tr>
				<tr>
					<td>Oficial de fierrero</td>
					<td>$11,500.00</td>
					<td>13/05/2021</td>
					<td><a href="https://www.empleo.gob.mx/4495955-oferta-de-empleo-de-oficial-de-fierrero-consorcio-tramo-dos-sa-cv" target="_blank">Ver oferta</a></td>
				</tr>
				<tr>
					<td>Oficial de albaÒil</td>
					<td>$11,500.00</td>
					<td>13/05/2021</td>
					<td><a href="https://www.empleo.gob.mx/4496033-oferta-de-empleo-de-oficial-de-albanil-consorcio-tramo-dos-sa-cv" target="_blank">Ver oferta</a></td>
				</tr>
				<!--<tr>
					<td>Operador de planta de luz</td>
					<td>$6,000.00</td>
					<td>29/10/2020</td>
					<td><a href="https://www.empleo.gob.mx/4411173-oferta-de-empleo-de-operador-planta-luz-consorcio-tramo-dos-sa-cv" target="_blank">Ver oferta</a></td>
				</tr>-->
				<!--<tr>
					<td>Operador de retroexcavadora</td>
					<td>$8,000.00</td>
					<td>29/10/2020</td>
					<td><a href="https://www.empleo.gob.mx/4411178-oferta-de-empleo-de-operador-retroexcavadora-consorcio-tramo-dos-s" target="_blank">Ver oferta</a></td>
				</tr>-->
				<!--<tr>
					<td>Operador de pipa de agua</td>
					<td>$6,000.00</td>
					<td>29/10/2020</td>
					<td><a href="https://www.empleo.gob.mx/4411184-oferta-de-empleo-de-operador-pipa-agua-consorcio-tramo-dos-sa-cv" target="_blank">Ver oferta</a></td>
				</tr>-->
				<!--<tr>
					<td>Operador de motoniveladora</td>
					<td>$8,000.00</td>
					<td>29/10/2020</td>
					<td><a href="https://www.empleo.gob.mx/4411187-oferta-de-empleo-de-operador-motoniveladora-consorcio-tramo-dos-sa" target="_blank">Ver oferta</a></td>
				</tr>-->
				<!--<tr>
					<td>Operador de compactador liso</td>
					<td>$6,000.00</td>
					<td>29/10/2020</td>
					<td><a href="https://www.empleo.gob.mx/4411190-oferta-de-empleo-de-operador-compactador-liso-consorcio-tramo-dos-" target="_blank">Ver oferta</a></td>
				</tr>-->
				<!--<tr>
					<td>Operador de pata de cabra</td>
					<td>$6,000.00</td>
					<td>29/10/2020</td>
					<td><a href="https://www.empleo.gob.mx/4411408-oferta-de-empleo-de-operador-pata-cabra-consorcio-tramo-dos-sa-cv" target="_blank">Ver oferta</a></td>
				</tr>-->
				<tr>
					<td>Operador de cargador</td>
					<td>$17,000.00</td>
					<td>29/10/2020</td>
					<td><a href="https://www.empleo.gob.mx/4496080-oferta-de-empleo-de-operador-de-cargador-consorcio-tramo-dos-sa-cv" target="_blank">Ver oferta</a></td>
				</tr>
				<tr>
					<td>Operador de excavadora</td>
					<td>$17,000.00</td>
					<td>13/05/2021</td>
					<td><a href="https://www.empleo.gob.mx/4496107-oferta-de-empleo-de-operador-excavadora-consorcio-tramo-dos-sa-cv" target="_blank">Ver oferta</a></td>
				</tr>
				<!--<tr>
					<td>Operador de tractor</td>
					<td>$8,000.00</td>
					<td>29/10/2020</td>
					<td><a href="https://www.empleo.gob.mx/4411201-oferta-de-empleo-de-operador-de-tractor-consorcio-tramo-dos-sa-cv" target="_blank">Ver oferta</a></td>
				</tr>-->
				<!--<tr>
					<td>Checador de materiales</td>
					<td>$8,000.00</td>
					<td>29/10/2020</td>
					<td><a href="https://www.empleo.gob.mx/4411210-oferta-de-empleo-de-checador-materiales-consorcio-tramo-dos-sa-cv" target="_blank">Ver oferta</a></td>
				</tr>-->
				<!--<tr>
					<td>Operador de gr√∫a hiab</td>
					<td>$8,000.00</td>
					<td>29/10/2020</td>
					<td><a href="https://www.empleo.gob.mx/4411215-oferta-de-empleo-de-operador-grua-hiab-consorcio-tramo-dos-sa-cv" target="_blank">Ver oferta</a></td>
				</tr>-->
				<!--<tr>
					<td>Chofer de camioneta 3 y medio</td>
					<td>$6,000.00</td>
					<td>29/10/2020</td>
					<td><a href="https://www.empleo.gob.mx/4394687-oferta-de-empleo-de-chofer-camioneta-3-y-medio-consorcio-tramo-dos" target="_blank">Ver oferta</a></td>
				</tr>-->
				<!--<tr>
					<td>Velador</td>
					<td>$6,000.00</td>
					<td>29/10/2020</td>
					<td><a href="https://www.empleo.gob.mx/4411219-oferta-de-empleo-de-velador-consorcio-tramo-dos-sa-cv" target="_blank">Ver oferta</a></td>
				</tr>-->
				<!--<tr>
					<td>Bodeguero</td>
					<td>$6,000.00</td>
					<td>29/10/2020</td>
					<td><a href="https://www.empleo.gob.mx/4411221-oferta-de-empleo-de-bodeguero-consorcio-tramo-dos-sa-cv" target="_blank">Ver oferta</a></td>
				</tr>-->
				<tr>
					<td>Cabo</td>
					<td>$17,140.00</td>
					<td>13/05/2021</td>
					<td><a href="https://www.empleo.gob.mx/4495615-oferta-de-empleo-de-cabo-consorcio-tramo-dos-sa-cv" target="_blank">Ver oferta</a></td>
				</tr>
				<!--<tr>
					<td>Jefe de frente</td>
					<td>$12,000.00</td>
					<td>29/10/2020</td>
					<td><a href="https://www.empleo.gob.mx/4411227-oferta-de-empleo-de-jefe-de-frente-consorcio-tramo-dos-sa-cv" target="_blank">Ver oferta</a></td>
				</tr>-->
				<!--<tr>
					<td>M√©dico de obra</td>
					<td>$12,000.00</td>
					<td>29/10/2020</td>
					<td><a href="https://www.empleo.gob.mx/4411232-oferta-de-empleo-de-medico-de-obra-consorcio-tramo-dos-sa-cv" target="_blank">Ver oferta</a></td>
				</tr>-->
				<!--<tr>
					<td>Auxiliar estimaciones jr</td>
					<td>$6,000.00</td>
					<td>29/10/2020</td>
					<td><a href="https://www.empleo.gob.mx/4411241-oferta-de-empleo-de-auxiliar-estimaciones-jr-consorcio-tramo-dos-s" target="_blank">Ver oferta</a></td>
				</tr>-->
				<!--<tr>
					<td>Auxiliar estimaciones</td>
					<td>$8,000.00</td>
					<td>29/10/2020</td>
					<td><a href="https://www.empleo.gob.mx/4411246-oferta-de-empleo-de-auxiliar-estimaciones-consorcio-tramo-dos-sa-c" target="_blank">Ver oferta</a></td>
				</tr>-->
				<!--<tr>
					<td>Analista de precios unitarios jr</td>
					<td>$8,000.00</td>
					<td>29/10/2020</td>
					<td><a href="https://www.empleo.gob.mx/4411249-oferta-de-empleo-de-analista-precios-unitarios-jr-consorcio-tramo-" target="_blank">Ver oferta</a></td>
				</tr>-->
				<!--<tr>
					<td>Analista de precios unitarios</td>
					<td>$8,000.00</td>
					<td>29/10/2020</td>
					<td><a href="https://www.empleo.gob.mx/4411274-oferta-de-empleo-de-analista-precios-unitarios-consorcio-tramo-" target="_blank">Ver oferta</a></td>
				</tr>-->
				<!--<tr>
					<td>Jefe de control de proyecto</td>
					<td>$10,000.00</td>
					<td>29/10/2020</td>
					<td><a href="https://www.empleo.gob.mx/4394657-oferta-de-empleo-de-jefe-control-proyecto-consorcio-tramo-dos-sa-c" target="_blank">Ver oferta</a></td>
				</tr>-->
				<!--<tr>
					<td>Jefe de estimaciones</td>
					<td>$10,000.00</td>
					<td>29/10/2020</td>
					<td><a href="https://www.empleo.gob.mx/4394655-oferta-de-empleo-de-jefe-de-estimaciones-consorcio-tramo-dos-sa-cv" target="_blank">Ver oferta</a></td>
				</tr>-->
				<!--<tr>
					<td>Jefe de precios unitarios</td>
					<td>$10,000.00</td>
					<td>29/10/2020</td>
					<td><a href="https://www.empleo.gob.mx/4394652-oferta-de-empleo-de-jefe-precios-unitarios-consorcio-tramo-dos-sa-" target="_blank">Ver oferta</a></td>
				</tr>-->
				<!--<tr>
					<td>Ingeniero de planeaci√≥n</td>
					<td>$10,000.00</td>
					<td>29/10/2020</td>
					<td><a href="https://www.empleo.gob.mx/4394653-oferta-de-empleo-de-ingeniero-planeacion-consorcio-tramo-dos-sa-cv" target="_blank">Ver oferta</a></td>
				</tr>-->
				<!--<tr>
					<td>Auxiliar subcontratos jr</td>
					<td>$6,000.00</td>
					<td>29/10/2020</td>
					<td><a href="https://www.empleo.gob.mx/4411278-oferta-de-empleo-de-auxiliar-subcontratos-jr-consorcio-tramo-dos-s" target="_blank">Ver oferta</a></td>
				</tr>-->
				<!--<tr>
					<td>Auxiliar subcontratos</td>
					<td>$10,000.00</td>
					<td>29/10/2020</td>
					<td><a href="https://www.empleo.gob.mx/4411287-oferta-de-empleo-de-auxiliar-subcontratos-consorcio-tramo-dos-sa-c" target="_blank">Ver oferta</a></td>
				</tr>-->
				<!--<tr>
					<td>Param√©dico de obra</td>
					<td>$6,500.00</td>
					<td>29/10/2020</td>
					<td><a href="https://www.empleo.gob.mx/4411289-oferta-de-empleo-de-paramedico-de-obra-consorcio-tramo-dos-sa-cv" target="_blank">Ver oferta</a></td>
				</tr>-->
				<!--<tr>
					<td>Personal de seguridad</td>
					<td>$12,000.00</td>
					<td>29/10/2020</td>
					<td><a href="https://www.empleo.gob.mx/4411300-oferta-de-empleo-de-personal-seguridad-consorcio-tramo-dos-sa-cv" target="_blank">Ver oferta</a></td>
				</tr>-->
				<!--<tr>
					<td>Oficial de seguridad</td>
					<td>$15,000.00</td>
					<td>29/10/2020</td>
					<td><a href="https://www.empleo.gob.mx/4411306-oferta-de-empleo-de-oficial-de-seguridad-consorcio-tramo-dos-sa-cv" target="_blank">Ver oferta</a></td>
				</tr>-->
				<!--<tr>
					<td>Jefe de frente / calidad</td>
					<td>$12,000.00</td>
					<td>29/10/2020</td>
					<td><a href="https://www.empleo.gob.mx/4411316-oferta-de-empleo-de-jefe-frente--calidad-consorcio-tramo-dos-sa-cv" target="_blank">Ver oferta</a></td>
				</tr>-->
				<!--<tr>
					<td>Ingeniero mec√°nico</td>
					<td>$10,000.00</td>
					<td>29/10/2020</td>
					<td><a href="https://www.empleo.gob.mx/4411311-oferta-de-empleo-de-ingeniero-mecanico-consorcio-tramo-dos-sa-cv" target="_blank">Ver oferta</a></td>
				</tr>-->
			</tbody>
		</table>
			<div class="clearfix"></div>
			<div class="row slogan slogan-2">
				<div class="col-md-12">
					<h2>øTienes dudas?</h2>
					<p>No te preocupes, estamos para apoyarte:</p>
				</div>
			</div>
			<div class="row contact-links">
				<div class="contact-opts">
					<img alt="" src="css/images/ico-llamada.png" class="img-responsive" /></br>
					ComunÌcate al</br>
					800 841 2020
				</div>
				<div class="contact-opts">
					<img alt="" src="css/images/ico-rSociales.png" class="img-responsive" /></br>
					VisÌtanos en:</br>
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
