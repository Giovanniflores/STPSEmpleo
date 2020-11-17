<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="url" value="${pageContext.request.requestURI}" />

<t:publicTemplate>
	<jsp:attribute name="titulo">Cursos en l&iacute;nea para el empleo</jsp:attribute>
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
	.birrete {
		text-align:center;
		margin-top:43px;
	}
	.intro-1 {
		font-size:22px;
		text-align: center;
		margin-top: 40px;
		color: #707074;
		font-family: alright-regular, Arial, "Helvetica Neue", Helvetica, sans-serif;
	}
	.intro-1 strong {
		font-weight: bold;
	}
	.intro-1 p {
		width:60%;
		margin: auto;
	}
	.intro-1 p span {
		color: #7bb907;
	}
	.beneficios {
		font-family: alright-regular, Arial, "Helvetica Neue", Helvetica, sans-serif;
		background: url(${context}/css/images/fnd-beneficios.jpg);
		margin: 60px 15px;
	}
	.beneficios h2 {
		font-size: 76px;
		padding-top: 141px;
		font-weight: bold;
		color: #ffffff;
		margin-left: 40px;
	}
	.beneficios p {
		font-family: alright-light, Arial, "Helvetica Neue", Helvetica, sans-serif;
		color: #3b433c;
		margin-left: 40px;
		font-size: 28px;
		line-height: 34px;
	}
	.beneficios strong {
		font-family: alright-regular, Arial, "Helvetica Neue", Helvetica, sans-serif;
		font-weight: bold;
	}
	.im-beneficios {
		margin-top: 40px;
	}
	.beneficios .ico {
		background-image: url(${context}/css/images/sprite-beneficios.png);
		background-repeat: no-repeat;
		width: 123px;
		height: 123px;
		margin: auto;
	}
	.impulsas .ico {
		background-position: 5px center;
	}
	.obtienes .ico {
		background-position: -173px center;
	}
	.aumentas .ico {
		background-position: -352px center;
	}
	.row-beneficios {
		margin-top: 80px;
	}
	.row-beneficios p {
		font-family: alright-regular, Arial, "Helvetica Neue", Helvetica, sans-serif;
		text-align: center;
		color: #ffffff;
		font-size: 18px;
		padding: 10px 60px;
		line-height: 24px;
		margin: auto;
	}
	.row-beneficios p span {
		font-weight: bold;
	}
	.intro-2 .vin {
		text-align: center;
	}
	.intro-2 .vin img {
		max-width: 348px;
		display: inline-block;
	}
	.intro-2 p {
		font-family: alright-regular, Arial, "Helvetica Neue", Helvetica, sans-serif;
		text-align: center;
		font-size: 36px;
		color: #7ebb01;
		margin-top: 60px;
	}
	.intro-2 p strong {
		font-weight: bold;
		font-size: 58px;
	}
	.intro-2 p span {
		font-family: alright-light, Arial, "Helvetica Neue", Helvetica, sans-serif;
		color: #242e25;
		display: inline-block;
		margin-top: 40px;
	}
	.card {
		background-color: #ffffff;
		-webkit-box-shadow: 0 1px 5px rgba(0,0,0,0.75);
		-moz-box-shadow: 0 1px 5px rgba(0,0,0,0.75);
		box-shadow: 0 1px 5px rgba(0,0,0,0.75);
		margin: 15px;
		font-family: alright-regular, Arial, "Helvetica Neue", Helvetica, sans-serif;
		padding: 0 30px;
		min-height: 385px;
	}
	.tit-programa {
		padding: 15px 0;
	}
	.tit-programa h2 {
		text-align: center;
		color: #7ebb01;
		font-weight: bold;
	}
	.tit-programa h3 {
		text-align: right;
		color: #7ebb01;
		font-weight: bold;
	}
	.mas-info {
		text-align: center;
		padding: 20px 0 40px;
	}
	.mas-info button {
		display: inline-block;
		padding: 5px 10px;
		color: #ffffff;
		text-align: center;
		width: 150px;
		background-color: #5f7c12;
		-webkit-border-radius: 3px;
		-moz-border-radius: 3px;
		border-radius: 3px;
		-webkit-box-shadow: 1px 2px 4px rgba(0,0,0,0.5);
		-moz-box-shadow: 1px 2px 4px rgba(0,0,0,0.5);
		box-shadow: 1px 2px 4px rgba(0,0,0,0.5);
	}
	.logo-programa {
		display: table-cell;
		vertical-align: middle;
	}
	.carousel-control.right {
		right: -130px;
	}
	.carousel-control.left{
		left: -130px;
	}
	.modal-content {
		background-color: #000000;
	}
	.head-cursos {
		display: block;
	}
	.head-cursos2 {
		display: none;
	}
	.close {
	    font-size: 40px;
	    font-weight: bold;
	    color: #fff;
	    text-shadow: 0 1px 0 #000;
	    filter: alpha(opacity=90);
	    opacity: 1;
	}
	.close:hover {
		color: #ffffff;
	}
	.modal-header {
		border-bottom: 1px solid #000000;
	}
	@media (max-width:768px){
		.birrete img {
			width: 50%;
		}
		.intro-1 p {
			width:90%;
			font-size: 16px;
		}
		.beneficios h2 {
			font-size: 52px;
			padding-top: 40px;
			margin-left: 0;
		}
		.beneficios p {
			font-size: 22px;
			margin-left: 0;
		}
		.row-beneficios {
			margin-top: 40px;
		}
		.row-beneficios p span {
			font-family: alright-regular, Arial, "Helvetica Neue", Helvetica, sans-serif;
			font-weight: strong;
		}
		.row-beneficios p {
			padding: 10px 0 40px;
			font-family: alright-light, Arial, "Helvetica Neue", Helvetica, sans-serif;
			font-size: 16px;
		}
		.im-beneficios {
			padding-top: 40px;
		}
		.head-cursos {
			display: none;
		}
		.head-cursos2 {
			display: block;
		}
		.intro-2 p {
			font-size: 28px;
			margin-top: 60px;
		}
		.intro-2 p strong {
			font-size: 38px;
		}
		.card .col-md-6 {
			padding: 0;
			margin: 0;
		}
		.card {
			margin: 0;
		}
		.container {
			padding: 0;
		}
	}
	</style>

    <!-- Contenido -->
	<div class="row">
		<div class="col-sm-12">
			<div class="page-header">
				<h3>Cursos en l&iacute;nea <small>Aprende especial&aacute;zate y &#161;Obt&eacute;n el mejor empleo!</small></h3>
			</div>
		</div>
		<div class="clearfix"></div>
		<div class="col-md-12 head-cursos">
			<img alt="" src="${context}/css/images/head-cursos.jpg" class="img-responsive" style="margin:auto;" />
		</div>
		<div class="col-md-12 head-cursos2">
			<img alt="" src="${context}/css/images/head-cursos2.jpg" class="img-responsive" style="margin:auto;" />
		</div>
		<div class="col-md-12 birrete">
			<img alt="" src="${context}/css/images/ico-birrete.png" />
		</div>
		<div class="col-md-12 intro-1">
			<p><span>En el Servicio Nacional de Empleo (SNE)</span> sumamos nuestros
			esfuerzos con diversas organizaciones, para brindarte cursos de
			capacitaci&oacute;n en l&iacute;nea gratuitos, que puedes realizar <strong>sin la
			necesidad de acudir a un aula</strong> y en el horario que prefieras.</p>
		</div>
		<div class="clearfix"></div>

		<div class="intro-2">
			<div class="col-md-7 col-md-push-5">
				<p>No dejes pasar el tiempo<br>
				<strong>&#161;Comienza ahora!</strong><br>
				<span>Tenemos alianzas con:</span></p>
			</div>
			<div class="col-md-5 col-md-pull-7 vin">
				<img alt="" src="${context}/css/images/intro-2.jpg" class="img-responsive" />
			</div>
		</div>
	</div>
	<div class="col-md-6">
		<div class="card">
			<div class="tit-programa">
				<div class="col-md-3 logo-programa">
					<img alt="" src="${context}/css/images/logo-aprende.png" />
				</div>
				<div class="clearfix"></div>
			</div>
			<p>Aprende te ofrece la oportunidad de estudiar, capacitarte para el empleo 
			y conocer sobre el cuidado de la salud. Todos sus cursos completamente 
			gratuitos, son interactivos y puedes acceder a ellos cuantas veces quieras 
			sin ning&uacute;n requisito.</p>
			<div class="mas-info">		
				<button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#Aprende" onclick="ga('send', 'event', 'Cursos en l�nea', 'Cat�logo de cursos', 'Aprende - Detalle', 0);">Ver m&aacute;s</button>
			</div>
		</div>
	</div>
	<div class="col-md-6">
		<div class="card">
			<div class="tit-programa">
				<div class="col-md-3 logo-programa">
					<img alt="" src="${context}/css/images/logo-slim.png" />
				</div>
				<div class="clearfix"></div>
			</div>
			<p>Capac&iacute;tate para el empleo te ofrece cursos, bolsa de trabajo y diplomados 
			con validez oficial (SEP), completamente gratuitos, para que puedas crecer 
			profesionalmente o iniciar tu propio negocio. Toma todos los cursos que 
			quieras sin ning&uacute;n requisito e imprime tu certificado desde cualquier lugar. </p>
			<div class="mas-info">		
				<button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#Capacitate" onclick="ga('send', 'event', 'Cursos en l�nea', 'Cat�logo de cursos', 'Capac?tate - Detalle', 0);">Ver m&aacute;s</button>
			</div>
		</div>
	</div>
	<div class="clearfix"></div>
	<div class="col-md-6">
		<div class="card"> 
			<div class="tit-programa">
				<div class="col-md-3 logo-programa">
					<img alt="" src="${context}/css/images/logo-vive.jpg" />
				</div>
				<h3 class="col-md-9">Proyecto Vive</h3>
				<div class="clearfix"></div>
			</div>
			<p>Vive: Ven, Insp&iacute;rate y Vende es un proyecto que tiene como objetivo capacitarte 
			en las &aacute;reas como: liderazgo, promotor&iacute;a, ventas y servicio al cliente; lo que te 
			permitir&aacute; obtener mayores oportunidades de conseguir un empleo digno y as&iacute; mejorar 
			tus condiciones de vida. Adem&aacute;s, si te unes al proyecto obtendr&oacute;s la certificaci&oacute;n 
			CONOCER.</p>
			<div class="mas-info">		
				<button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#ProgramaVive" onclick="ga('send', 'event', 'Cursos en l�nea', 'Cat�logo de cursos', 'Programa Vive - Detalle', 0);">Ver m&aacute;s</button>
			</div>
		</div>
	</div>
	<div class="col-md-6">
		<div class="card">
			<div class="tit-programa">
				<div class="col-md-3 logo-programa">
					<img alt="" src="${context}/css/images/logo-procadist.jpg" />
				</div>
				<h3 class="col-md-9">Procadist</h3>
				<div class="clearfix"></div>
			</div>
			<p>El programa de Capacitaci&oacute;n a Distancia (PROCADIST) es una plataforma que te  
			ofrece un cat&aacute;logo de cursos en l&iacute;nea con el objetivo de aumentar tu desarrollo a 
			trav&eacute;s de la capacitaci&oacute;n en y para el trabajo, en este sitio tambi&eacute;n encontrar&aacute;s 
			asesor&iacute;a para sectores econ&oacute;micos espec&iacute;ficos que te ayudar&aacute;n a incrementar la 
			productividad.<br><br><br></p>
			<div class="mas-info">		
				<button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#Procadist" onclick="ga('send', 'event', 'Cursos en l�nea', 'Cat�logo de cursos', 'Procadist - Detalle', 0);">Ver m&aacute;s</button>
			</div>
		</div>
	</div>	
	
	<div class="clearfix"></div>
	<div class="col-md-6">
		<div class="card">
			<div class="tit-programa">
				<div class="col-md-3 logo-programa">
					<img alt="" src="${context}/css/images/logo-capacinet.jpg" />
				</div>
				<div class="clearfix"></div>
			</div>
			<p>CapaciNET es una plataforma que te brinda un inventario de cursos 
			gratuitos, cuyo dise&#241;o interactivo es respaldado por instituciones de 
			prestigio, que aspiran a generar y mejorar las aptitudes, habilidades, 
			conocimientos y actitudes de las personas que como t&uacute; buscan un empleo 
			o desean mejorar su desempe&#241;o productivo.</p>
			<div class="mas-info">		
				<button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#Capacinet" onclick="ga('send', 'event', 'Cursos en l�nea', 'Cat�logo de cursos', 'Capacinet - Detalle', 0);">Ver m&aacute;s</button>
			</div>
		</div>
	</div>	
	<div class="clearfix"></div>
	
			<div class="beneficios">
				<div class="col-md-6 col-md-push-6">
					<img class="im-beneficios img-responsive" alt="" src="${context}/css/images/beneficios1.png" />
				</div>
				<div class="col-md-6 col-md-pull-6">
					<h2>Beneficios</h2>
					<p><strong>Enriquece tu CV</strong> con<br>
					nuestros cursos oficiales</p>
				</div>
				<div class="clearfix"></div>
				<div class="row-beneficios">
					<div class="col-md-4 impulsas">
						<div class="ico"></div>
						<p><span>Impulsas</span><br>
						tu desarrollo laboral y personal</p>
					</div>
					<div class="col-md-4 obtienes">
						<div class="ico"></div>
						<p><span>Obtienes</span><br>
						m&aacute;s conocimientos y habilidades</p>
					</div>
					<div class="col-md-4 aumentas">
						<div class="ico"></div>
						<p><span>Aumentas</span><br>
						tus oportunidades de un mejor empleo o ser un emprendedor</p>
					</div>
				</div>
				<img alt="" src="${context}/css/images/remate-1.png" class="img-responsive" />
			</div>
				

			

  <div class="modal fade" id="Aprende" role="dialog">
    <div class="modal-dialog modal-lg">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">&nbsp;
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        <div class="modal-body">
            <div id="myCarousel" class="carousel slide" data-ride="carousel" data-interval="false" data-wrap="false">
			    <!-- Indicators -->
			    <ol class="carousel-indicators">
			      <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
			      <li data-target="#myCarousel" data-slide-to="1"></li>
			      <li data-target="#myCarousel" data-slide-to="2"></li>
			      <li data-target="#myCarousel" data-slide-to="3"></li>
			      <li data-target="#myCarousel" data-slide-to="4"></li>
			    </ol>
			
			    <!-- Wrapper for slides -->
			    <div class="carousel-inner">
			      <div class="item active">
			        <iframe style="width:100%;height:500px" src="/aprende.html"></iframe>
			      </div>
				  <div class="item">
			        <iframe style="width:100%;height:500px" src="/capacitate.html"></iframe>
			      </div>
			      <div class="item">
			        <iframe style="width:100%;height:500px" src="/vive.html"></iframe>
			      </div>
			      <div class="item">
			        <iframe style="width:100%;height:500px" src="/procadist.html"></iframe>
			      </div>
			      <div class="item">
			        <iframe style="width:100%;height:500px" src="/capacinet.html"></iframe>
			      </div>
			    </div>
			
			    <!-- Left and right controls -->
			    <a class="left carousel-control" href="#myCarousel" data-slide="prev">
			      <span class="glyphicon glyphicon-chevron-left"></span>
			      <span class="sr-only">Previous</span>
			    </a>
			    <a class="right carousel-control" href="#myCarousel" data-slide="next">
			      <span class="glyphicon glyphicon-chevron-right"></span>
			      <span class="sr-only">Next</span>
			    </a>
			  </div>
        </div>
      </div>
      
    </div>
  </div>
  
  <div class="modal fade" id="Capacitate" role="dialog">
    <div class="modal-dialog modal-lg">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">&nbsp;
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        <div class="modal-body">
            <div id="myCarousel1" class="carousel slide" data-ride="carousel"  data-interval="false" data-wrap="false">
			    <!-- Indicators -->
			    <ol class="carousel-indicators">
			      <li data-target="#myCarousel1" data-slide-to="0"></li>
			      <li data-target="#myCarousel1" data-slide-to="1" class="active"></li>
			      <li data-target="#myCarousel1" data-slide-to="2"></li>
			      <li data-target="#myCarousel1" data-slide-to="3"></li>
			      <li data-target="#myCarousel1" data-slide-to="4"></li>
			    </ol>
			
			    <!-- Wrapper for slides -->
			    <div class="carousel-inner">
			      <div class="item">
			        <iframe style="width:100%;height:500px" src="/aprende.html"></iframe>
			      </div>
				  <div class="item active">
			        <iframe style="width:100%;height:500px" src="/capacitate.html"></iframe>
			      </div>
			      <div class="item">
			        <iframe style="width:100%;height:500px" src="/vive.html"></iframe>
			      </div>
			    
			      <div class="item">
			        <iframe style="width:100%;height:500px" src="/procadist.html"></iframe>
			      </div>
			      <div class="item">
			        <iframe style="width:100%;height:500px" src="/capacinet.html"></iframe>
			      </div>
			    </div>
			
			    <!-- Left and right controls -->
			    <a class="left carousel-control" href="#myCarousel1" data-slide="prev">
			      <span class="glyphicon glyphicon-chevron-left"></span>
			      <span class="sr-only">Previous</span>
			    </a>
			    <a class="right carousel-control" href="#myCarousel1" data-slide="next">
			      <span class="glyphicon glyphicon-chevron-right"></span>
			      <span class="sr-only">Next</span>
			    </a>
			  </div>
        </div>
      </div>
      
    </div>
  </div>

  <div class="modal fade" id="ProgramaVive" role="dialog">
    <div class="modal-dialog modal-lg">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">&nbsp;
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        <div class="modal-body">
            <div id="myCarousel2" class="carousel slide" data-ride="carousel"  data-interval="false" data-wrap="false">
			    <!-- Indicators -->
			    <ol class="carousel-indicators">
			      <li data-target="#myCarousel2" data-slide-to="0"></li>
			      <li data-target="#myCarousel2" data-slide-to="1"></li>
			      <li data-target="#myCarousel2" data-slide-to="2" class="active"></li>
			      <li data-target="#myCarousel2" data-slide-to="3"></li>
			      <li data-target="#myCarousel2" data-slide-to="4"></li>
			    </ol>
			
			    <!-- Wrapper for slides -->
			    <div class="carousel-inner">
			      <div class="item">
			        <iframe style="width:100%;height:500px" src="/aprende.html"></iframe>
			      </div>
				  <div class="item">
			        <iframe style="width:100%;height:500px" src="/capacitate.html"></iframe>
			      </div>	
			      <div class="item active">
			        <iframe style="width:100%;height:500px" src="/vive.html"></iframe>
			      </div>
			    
			      <div class="item">
			        <iframe style="width:100%;height:500px" src="/procadist.html"></iframe>
			      </div>
			      <div class="item">
			        <iframe style="width:100%;height:500px" src="/capacinet.html"></iframe>
			      </div>
			    </div>
			
			    <!-- Left and right controls -->
			    <a class="left carousel-control" href="#myCarousel2" data-slide="prev">
			      <span class="glyphicon glyphicon-chevron-left"></span>
			      <span class="sr-only">Previous</span>
			    </a>
			    <a class="right carousel-control" href="#myCarousel2" data-slide="next">
			      <span class="glyphicon glyphicon-chevron-right"></span>
			      <span class="sr-only">Next</span>
			    </a>
			  </div>
        </div>
      </div>
      
    </div>
  </div>

  <div class="modal fade" id="Procadist" role="dialog">
    <div class="modal-dialog modal-lg">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">&nbsp;
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        <div class="modal-body">
            <div id="myCarousel3" class="carousel slide" data-ride="carousel"  data-interval="false" data-wrap="false">
			    <!-- Indicators -->
			    <ol class="carousel-indicators">
			      <li data-target="#myCarousel3" data-slide-to="0"></li>
			      <li data-target="#myCarousel3" data-slide-to="1"></li>
			      <li data-target="#myCarousel3" data-slide-to="2"></li>
			      <li data-target="#myCarousel3" data-slide-to="3" class="active"></li>
			      <li data-target="#myCarousel3" data-slide-to="4"></li>
			    </ol>
			
			    <!-- Wrapper for slides -->
			    <div class="carousel-inner">
			      <div class="item">
			        <iframe style="width:100%;height:500px" src="/aprende.html"></iframe>
			      </div>
			      <div class="item">
			        <iframe style="width:100%;height:500px" src="/capacitate.html"></iframe>
			      </div>
			      <div class="item">
			        <iframe style="width:100%;height:500px" src="/vive.html"></iframe>
			      </div>
			    
			      <div class="item active">
			        <iframe style="width:100%;height:500px" src="/procadist.html"></iframe>
			      </div>
			      <div class="item">
			        <iframe style="width:100%;height:500px" src="/capacinet.html"></iframe>
			      </div>
			    </div>
			
			    <!-- Left and right controls -->
			    <a class="left carousel-control" href="#myCarousel3" data-slide="prev">
			      <span class="glyphicon glyphicon-chevron-left"></span>
			      <span class="sr-only">Previous</span>
			    </a>
			    <a class="right carousel-control" href="#myCarousel3" data-slide="next">
			      <span class="glyphicon glyphicon-chevron-right"></span>
			      <span class="sr-only">Next</span>
			    </a>
			  </div>
        </div>
      </div>
      
    </div>
  </div>
  <div class="modal fade" id="Capacinet" role="dialog">
    <div class="modal-dialog modal-lg">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">&nbsp;
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        <div class="modal-body">
            <div id="myCarousel4" class="carousel slide" data-ride="carousel"  data-interval="false" data-wrap="false">
			    <!-- Indicators -->
			    <ol class="carousel-indicators">
			      <li data-target="#myCarousel4" data-slide-to="0"></li>
			      <li data-target="#myCarousel4" data-slide-to="1"></li>
			      <li data-target="#myCarousel4" data-slide-to="2"></li>
			      <li data-target="#myCarousel4" data-slide-to="3"></li>
			      <li data-target="#myCarousel4" data-slide-to="4" class="active"></li>
			    </ol>
			
			    <!-- Wrapper for slides -->
			    <div class="carousel-inner">
			      <div class="item">
			        <iframe style="width:100%;height:500px" src="/aprende.html"></iframe>
			      </div>
			      <div class="item">
			        <iframe style="width:100%;height:500px" src="/capacitate.html"></iframe>
			      </div>
			      <div class="item">
			        <iframe style="width:100%;height:500px" src="/vive.html"></iframe>
			      </div>
			    
			      <div class="item">
			        <iframe style="width:100%;height:500px" src="/procadist.html"></iframe>
			      </div>
			      <div class="item active">
			        <iframe style="width:100%;height:500px" src="/capacinet.html"></iframe>
			      </div>
			    </div>
			
			    <!-- Left and right controls -->
			    <a class="left carousel-control" href="#myCarousel4" data-slide="prev">
			      <span class="glyphicon glyphicon-chevron-left"></span>
			      <span class="sr-only">Previous</span>
			    </a>
			    <a class="right carousel-control" href="#myCarousel4" data-slide="next">
			      <span class="glyphicon glyphicon-chevron-right"></span>
			      <span class="sr-only">Next</span>
			    </a>
			  </div>
        </div>
      </div>
      
    </div>
  </div>	
		
 

	</jsp:body>
</t:publicTemplate>
