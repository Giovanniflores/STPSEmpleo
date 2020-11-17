<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="url" value="${pageContext.request.requestURI}" />

<t:publicTemplate>
	<jsp:attribute name="titulo">Ofertas de empleo en el extranjero</jsp:attribute>
    <jsp:attribute name="palabraDescripcion">Candidatos</jsp:attribute>  
    <jsp:attribute name="tituloSitio">Portal del Empleo</jsp:attribute>
 	<jsp:attribute name="urlRedSocial"><c:url value="${url}"/></jsp:attribute>
	<jsp:attribute name="descripcionRedSocial"></jsp:attribute>
	<jsp:body>
	<style>
	@font-face {
	    font-family: alright-regular;
	    src: url(${context}/css/images/AlrightSans-Regular-v3.otf);
	    font-weight: normal;
	}
	@font-face {
	    font-family: alright-regular;
	    src: url(${context}/css/images/AlrightSans-Bold-v3.otf);
	    font-weight: bold;
	}
	@font-face {
	    font-family: alright-light;
	    src: url(${context}/css/images/AlrightSans-Light-v3.otf);
	}
	.slogan h2 {
		font-family: alright-regular;
		font-weight: bold;
		color: #7ebb01;
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
		background-image: url(${context}/css/images/ico-canada.png);
	}
	.ico-pais.eeuu {
		background-image: url(${context}/css/images/ico-eeuu.png);
	}
	.ico-pais.alemania {
		background-image: url(${context}/css/images/ico-alemania.png);
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
		background-image: url(${context}/css/images/offer-hover.png);
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
		background-image: url(${context}/css/images/icon-facebook.png);
	}
	.tweeter-icon {
		background-image: url(${context}/css/images/icon-tweeter.png);
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

    <!-- Contenido -->
	<div class="row">
		<div class="col-sm-12" style="position: relative;">
			<div class="page-header">
				<h3>Ofertas de empleo en el extranjero<small> ¡Trabaja en el extranjero!</small></h3>
			</div>
		</div>
		<div class="clearfix"></div>
	</div>
	<div class="row">
		<div class="col-md-12 header">
			<div class="accesible">
			<h2>QUE NADA TE DETENGA, TRABAJA EN EL EXTRANJERO</h2>
			<div class="col-md-6 col-sm-7 col-xs-7 intro">
				<p>En la <strong>Coordinación General del Servicio Nacional del Empleo</strong> en colaboración con las Oficinas Estatales, 
				realizamos alianzas mundiales que nos permiten brindarte opciones para que trabajes en otros países.</p>
				<p>Si eres profesionista, tienes experiencia, dominas el inglés u otro idioma y te gustaría laborar en otro país, 
				esta oportunidad es para ti.</p>
			</div>
			</div>
			<img alt="" src="${context}/css/images/head-vacante-extranjera.jpg" class="img-responsive desk" />
			<img alt="" src="${context}/css/images/head-vacante-extranjeraBP-1.jpg" class="img-responsive landscape" />
			<img alt="" src="${context}/css/images/head-vacante-extranjeraBP-2.jpg" class="img-responsive portrait" />
		</div>
	</div>
	<div class="row slogan">
		<div class="col-md-12">
			<h2>¡Conoce nuestras vacantes!</h2>
			<p>Postúlate y forma parte de la gran comunidad de</br>
			<span>#MexicanosTrabajandoEnElExtranjero</span></p>
		</div>
	</div>
	<div class="row ofertas">
		<ul>
			<li class="col-md-2 offer-link">
				<em>Desarrolladores de redes de interfaz y/o servidores</em>
				<div class="ico-pais alemania"></div>
				<p>Publicación: 02/05/2017</br>
				Sueldo Neto: $64,000.00</p>
				<a href="#"><span>ver oferta</span></a>
			</li>
			<li class="col-md-2 offer-link">
				<em>Desarrolladores de redes de interfaz y/o servidores</em>
				<div class="ico-pais canada"></div>
				<p>Publicación: 02/05/2017</br>
				Sueldo Neto: $64,000.00</p>
				<a href="#"><span>ver oferta</span></a>
			</li>
			<li class="col-md-2 offer-link">
				<em>Desarrolladores de redes de interfaz y/o servidores</em>
				<div class="ico-pais eeuu"></div>
				<p>Publicación: 02/05/2017</br>
				Sueldo Neto: $64,000.00</p>
				<a href="#"><span>ver oferta</span></a>
			</li>
			<li class="col-md-2 offer-link">
				<em>Desarrolladores de redes de interfaz y/o servidores</em>
				<div class="ico-pais alemania"></div>
				<p>Publicación: 02/05/2017</br>
				Sueldo Neto: $64,000.00</p>
				<a href="#"><span>ver oferta</span></a>
			</li>
			<li class="col-md-2 offer-link">
				<em>Desarrolladores de redes de interfaz y/o servidores</em>
				<div class="ico-pais canada"></div>
				<p>Publicación: 02/05/2017</br>
				Sueldo Neto: $64,000.00</p>
				<a href="#"><span>ver oferta</span></a>
			</li>
			<li class="col-md-2 offer-link">
				<em>Desarrolladores de redes de interfaz y/o servidores</em>
				<div class="ico-pais eeuu"></div>
				<p>Publicación: 02/05/2017</br>
				Sueldo Neto: $64,000.00</p>
				<a href="#"><span>ver oferta</span></a>
			</li>
			<li class="col-md-2 offer-link">
				<em>Desarrolladores de redes de interfaz y/o servidores</em>
				<div class="ico-pais alemania"></div>
				<p>Publicación: 02/05/2017</br>
				Sueldo Neto: $64,000.00</p>
				<a href="#"><span>ver oferta</span></a>
			</li>
			<li class="col-md-2 offer-link">
				<em>Desarrolladores de redes de interfaz y/o servidores</em>
				<div class="ico-pais canada"></div>
				<p>Publicación: 02/05/2017</br>
				Sueldo Neto: $64,000.00</p>
				<a href="#"><span>ver oferta</span></a>
			</li>
			<li class="col-md-2 offer-link">
				<em>Desarrolladores de redes de interfaz y/o servidores</em>
				<div class="ico-pais eeuu"></div>
				<p>Publicación: 02/05/2017</br>
				Sueldo Neto: $64,000.00</p>
				<a href="#"><span>ver oferta</span></a>
			</li>
			<li class="col-md-2 offer-link">
				<em>Desarrolladores de redes de interfaz y/o servidores</em>
				<div class="ico-pais alemania"></div>
				<p>Publicación: 02/05/2017</br>
				Sueldo Neto: $64,000.00</p>
				<a href="#"><span>ver oferta</span></a>
			</li>
		</ul>
		<div class="clearfix"></div>
	</div>
		
		<!-- PAGINADOR -->
		
		<div class="paginator">
			<ul>
				<li><a class="prev"></a></li>
				
				<!-- numeración de páginas a mostrar -->
				<li><a href="javascript:doSubmitPage(1)" class="pagina">1</a></li>
				<li><span class="current">2</span></li>
				<li><a href="javascript:doSubmitPage(3)" class="pagina">3</a></li>
				
				<li><span class="noPags"> de 28 Páginas</span>&nbsp;</li>
				
				<!-- liga para saltar al bloque posterior -->
				<li><a href="javascript:doSubmitPage(11)" class="next">Siguiente</a></li>
			</ul>
			<br clear="all">
			<p class="mostradas">
			Mostrando 11 - 20 de 2787
			</p>
			<p>
				<label for="numPagina"><strong>Página</strong></label>
			    <input type="text" id="numPagina">
			    <input type="button" onclick="javascript:doSubmitPage(getElementById('numPagina').value, '-65499267')" value="Ir" class="ir">
			</p>
			<p></p>
		</div>
		


	<div class="row" style="margin-top:100px">
		<div class="col-md-12">
			<div class="accesible">
				<strong>¿Qué beneficios obtengo?</strong>
				<ul>
					<li>Adaptación de una nueva cultura</li>
					<li>Crecimiento de redes de contactos profesionales en todo el mundo</li>
					<li>Especialización en el sector de tu interés</li>
					<li>Aprender o dominar nuevos idiomas</li>
					<li>Mayor posibilidad de promociones a tu regreso a México</li>
				</ul>
				<em>Recuerda que este programa es permanente, prepárate y ¡encuentra una vacante para ti!</em>
			</div>
			<img alt="" src="${context}/css/images/beneficios-ext.jpg" class="img-responsive desk" />
			<img alt="" src="${context}/css/images/beneficios-extBP-1.jpg" class="img-responsive landscape" />
			<img alt="" src="${context}/css/images/beneficios-extBP-2.jpg" class="img-responsive portrait" />
		</div>
	</div>
	<div class="row slogan slogan-2">
		<div class="col-md-12">
			<h2>¿Tienes dudas?</h2>
			<p>No te preocupes, estamos para apoyarte:</p>
		</div>
	</div>
	<div class="row contact-links">
		<div class="col-md-4">
			<img alt="" src="${context}/css/images/ico-llamada.png" class="img-responsive" /></br>
			Comunícate al</br>
			01800 841 2020
		</div>
		<div class="col-md-4">
			<img alt="" src="${context}/css/images/ico-mensaje.png" class="img-responsive" /></br>
			<a href="">Inicia un chat AQUÍ</a>
		</div>
		<div class="col-md-4 r-sociales">
			<img alt="" src="${context}/css/images/ico-rSociales.png" class="img-responsive" /></br>
			Visítanos en:</br>
			<a class="facebook-icon" href="https://www.facebook.com/empleogobmx" target="_blank">Facebook</a>
			<a class="tweeter-icon" href="https://twitter.com/empleogob_mx" target="_blank">Tweeter</a>
		</div>
	</div>




	

				

			


  





		


	</jsp:body>
</t:publicTemplate>
