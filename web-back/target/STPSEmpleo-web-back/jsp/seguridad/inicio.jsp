<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib prefix="logic" uri="http://struts.apache.org/tags-logic" %>
<%@ taglib prefix="bean" uri="http://struts.apache.org/tags-bean" %>
<%@ taglib prefix="s" uri="http://struts.apache.org/tags-logic" %>
<script type="text/javascript" src="js/helper/messageHelper.js"></script>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
 <script type='text/javascript'>
         (function () {
             var proto = document.location.protocol || 'http:';
             var node = document.createElement('script');
             node.type = 'text/javascript';
             node.async = true;
             node.src = proto + '//webchat-gabssa.i6.inconcertcc.com/v3/click_to_chat?token=0C89F1EFE60FA05ABD37E56725D7E785';
             var s = document.getElementsByTagName('script')[0];
             s.parentNode.insertBefore(node, s);
         })();
</script>
<script type="text/javascript">


	dojo.require('dijit.Dialog');
	function keySubmit(e) {
		if (e.keyCode == 13) {
			document.loginForm.submit();
		}
	}
	var userclean = true;
	var pswclean = true;

	function clean(field, numfield) {

		if (numfield==1){
			if (userclean){
				field.value='';
				userclean = false;
			}
		}

		if (numfield==2){
			if (pswclean) {
				field.value='';
				pswclean = false;
			}
		}
	}

	
</script>

<c:if test="${empty USUARIO_APP}">

<div class="clearfix"></div>


	<div class="row">
	
		<!-- div buscador -->
		<div class="col-md-6 col-md-offset-3">
			<div class="panel panel-buscador inicial">
				<!--  <div class="panel-heading">
					<h3 class="panel-title">
						Tenemos más de <strong>${SHOW_BUSCADOR_OFERTAS}</strong> ofertas de empleo esperando por ti
					</h3>
				</div>-->
				<!-- <div class="panel-body">
					<div class="row">
						<form name="ocp" id="ocp" action="<c:url value="/ocupate.do"/>" method="get">
							<input type="hidden" name="method" value="init" />
							<input type="hidden" name="searchQ" value="" />
							<!--  <div class="col-md-6">
								<div class="form-group">
									<label for="searchTopic" class="t_buscador b2">¿Qué empleo buscasJMORinicio?</label>
									<input name="searchTopic" id="searchTopic" class="form-control" value="" type="text" />
								</div>
								<span class="help-block">Puedes indicar un puesto, carrera u oficio</span>
							</div>
							 <div class="col-md-6">
								<div class="form-group">
									<label for="searchPlace" class="t_buscador">¿Dónde?</label>
									<select name="searchPlace" id="searchPlace" class="form-control">
										<option value="" selected="selected"></option>
										<option value="1">Aguascalientes</option>
										<option value="2">Baja California</option>
										<option value="3">Baja California Sur</option>
										<option value="4">Campeche</option>
										<option value="5">Coahuila</option>
										<option value="6">Colima</option>
										<option value="7">Chiapas</option>
										<option value="8">Chihuahua</option>
										<option value="9">Ciudad de México</option>
										<option value="10">Durango</option>
										<option value="11">Guanajuato</option>
										<option value="12">Guerrero</option>
										<option value="13">Hidalgo</option>
										<option value="14">Jalisco</option>
										<option value="15">México</option>
										<option value="16">Michoacán</option>
										<option value="17">Morelos</option>
										<option value="18">Nayarit</option>
										<option value="19">Nuevo León</option>
										<option value="20">Oaxaca</option>
										<option value="21">Puebla</option>
										<option value="22">Querétaro</option>
										<option value="23">Quintana Roo</option>
										<option value="24">San Luis Potosí</option>
										<option value="25">Sinaloa</option>
										<option value="26">Sonora</option>
										<option value="27">Tabasco</option>
										<option value="28">Tamaulipas</option>
										<option value="29">Tlaxcala</option>
										<option value="30">Veracruz</option>
										<option value="31">Yucatán</option>
										<option value="32">Zacatecas</option>
									</select>
								</div>
								<span class="help-block text-right"><a href="<c:url value="/jsp/empleo/herramientasDelSitio/usoBuscador.jsp"/>">¿Cómo utilizar el buscador?</a></span>
							</div>
							<div class="col-sm-8 col-sm-offset-2">
								<input type="button" name="bt_buscador" id="bt_buscador" onclick="employ()" onkeypress="employ()" value="Buscar"
									   class="btn btn-buscador form-control"/>
							</div>
						</form>
					</div>
				</div>-->
				<div class="panel-footer text-right">
					<span>También puedes realizar una <a href="<c:url value="/busquedaEspecificaOfertas.do?method=buscar" /> "> búsqueda específica</a></span>
				</div>
			</div>
			
			<!-- div ayuda -->
			<div class="row">
	
				<div class="col-md-4 col-sm-6 col-xs-12">
					<div class="panel panel-contactoSWB">
						<div class="atencion">
							<a href="<c:url value="/jsp/empleo/herramientasDelSitio/contacto.jsp"/>"> Atención: 01 800 841 2020</a>
						</div>
					</div>
				</div>
		
				<div class="col-md-4 col-sm-6 col-xs-12">
					<div class="panel panel-contactoSWB">
						<div class="quejas">
							<a onclick="window.open(this.href, this.target, &#39;toolbar=no,directories=no,scrollbars=yes,location=no,statusbar=no,menubar=no,resizable=yes,width=830,height=700&#39;); return false;" target="popUp" href="<c:url value="/suggestion.do?method=init"/>">Quejas y sugerencias</a>
						</div>
					</div>
				</div>
				
				<div class="col-md-4 col-sm-6 col-xs-12">
					<div class="panel panel-contactoSWB act">
						<div class="ayuda">
							<!-- a href="#" title="Próximamente">Ayuda en línea</a-->							
							<a href="#" id="chat">Ayuda en línea</a>
						</div>
					</div>
				</div>
				
				<!-- div class="col-md-3 col-sm-6 col-xs-12">
					<div class="panel panel-contactoSWB">
						<div class="agenda">
							<a href="<c:url value="/miespacionav.do?method=agendaCita"/>">¿Necesitas atención? <br> Agenda una cita</a>
						</div>
					</div>
				</div-->
			</div>
			<!-- div ayuda -->
		</div>
		<!-- /div buscador -->
		

		
		</div>
		
		<div class="clearfix"></div>
		
		<div class="row">

		<!-- div Inicio sesion -->
		<div class="col-sm-4 col-md-offset-4">
			<div class="panel panelInicioSesion">
				<div class="panel-heading">
					<h3 class="panel-title">Inicio de sesión</h3>
				</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-12">

							<form id="loginForm" name="loginForm" action="login.do?method=login" method="post">
								<div class="form-group">
									<label for="username">Usuario / Correo electrónico</label>
									<input type="text" name="username" id="username" class="form-control"
										   value="Escribe tu nombre de usuario" onfocus="clean(this, 1)" />
								</div>
								<div class="form-group">
									<label for="password">Contraseña</label>
									<input type="password" name="password" id="password" class="form-control"
										   value="contrasena" onfocus="clean(this, 2)"
										   onkeypress="keySubmit(event)" />
								</div>
								<p id="ayudas">
									<a href="<c:url value="/recupera_contrasena.do"/>"
									   class="recuperar">¿Olvidaste tu contraseña?</a>
								</p>
								<p class="text-center">
									<input type="button" value="Iniciar Sesión" class="btn btn-sesion form-control"
										   onclick="javascript:document.loginForm.submit();" />
								</p>
							</form>
							<p><strong>¿No estás dado de alta? Regístrate como:</strong></p>
							<p class="registrarte">
								<a class="btn btn-info col-md-5" href="<c:url value="/registro_candidato.do"/>">Candidato</a>
								<a class="btn btn-info col-md-5" href="<c:url value="/registroEmpresa.do?method=init"/>">Empresa</a>
							</p>

						</div>
					</div>
				</div>
			</div>
		</div>
	
		<!-- /div Inicio sesion -->
	</div>



	<!-- 
	<div class="row seccionApp">
		<div class="col-md-4 col-sm-6 col-xs-12">
			<h2 class="titulosh2">Mejora tu empleabilidad</h2>
			<div class="panel panel-default multiple">
				<a class="mas-bolsas" href="<c:url value="/bolsasTrabajo.do?method=init&fromPortal=true"/>">
					<img class="img-responsive" alt="más bolsas de empleo" src="css/img/mult-bolsas.png" />
				</a>
				<a class="Ofertas-riviera" href="<c:url value="/ofertasRivieraMaya.do?method=init"/>">
					<img class="img-responsive" alt="Ofertas en la riviera maya" src="css/img/mult-maya.png" />
				</a>
				<a class="Directorio-oficinas" href="<c:url value="/jsp/empleo/servicioNacionalDeEmpleo/ubicacion.jsp"/>">
					<img class="img-responsive" alt="Directorio de oficinas del SNE" src="css/img/mult-directorio.png" />
				</a>
				<a class="Becas-SNE" href="<c:url value="/jsp/empleo/candidatos/becasCapacitacionSne.jsp"/>">
					<img class="img-responsive" alt="Becas a la capacitación del SNE" src="css/img/mult-becas.png" />
				</a>
			</div>
		</div>


		<div class="col-md-4 col-sm-6 col-xs-12">
			<h2 class="titulosh2">&nbsp</h2>
			<div class="panel panel-default">
				<a target="_blank" style="color: #000000; text-decoration: none;" href="http://taller.empleo.gob.mx/">
					<img class="img-responsive" alt="Habilidades para la búsqueda de empleo" src="css/img/boton-habilidades.png" />
				</a>
			</div>
		</div>


		<div class="col-md-4 col-sm-12 col-xs-12">
			<h2 class="titulosh2">&nbsp;</h2>
			<div class="panel panel-default">
				<a href="/candidatos/cursos-en-linea;JSESSIONIDPORTAL=6ZHXZRpdW3vhMDpC226TZ8JhQ2xCkQrjbpLKTWtpKNv1cqm4ZnPP!-1795217920" onclick="ga('send', 'event', 'Cursos en línea - Banner', 'Página principal', 'Catálogo de cursos', 0);">
					<img class="img-responsive" alt="Capacitate en línea, gratis" src="css/img/boton-cursos.png">
				</a>
			</div>

		</div>
	</div> -->
	<!-- /div tres columnas  ->



	<div class="row">
		<div class="col-md-8">

						<!-- banner-  ->
						<div id="carousel-example-captions" class="carousel slide" data-ride="carousel">
							<ol class="carousel-indicators">
								<logic:iterate id="banner" name="inicioForm" property="bannerFormList" indexId="counter1">
									<c:choose>
									<c:when test="${counter1==0}">
									<li data-target="#carousel-example-captions" data-slide-to="${counter1}" class="active"></li>
									</c:when>
									<c:otherwise>
									<li data-target="#carousel-example-captions" data-slide-to="${counter1}" class=""></li>
									</c:otherwise>
									</c:choose>
								</logic:iterate>

							</ol>
							<div class="carousel-inner" role="listbox">
								<logic:iterate id="bannerContent" name="inicioForm" property="bannerFormList" indexId="counter">
									<c:choose>
									<c:when test="${counter==0}">
								<div class="item active">
									<a <bean:write name="bannerContent" property="hyperlink"></bean:write>>
										<img alt="" src="<%=request.getContextPath()%>/ImageBanner?idBanner=<bean:write name='bannerContent'  property='id'></bean:write>"></img>
									</a>
									<div class="carousel-caption hidden-xs">
										<p id="titleEnterate"><bean:write name="bannerContent" property="titulo"></bean:write></p>
										<p id="descriptionEnterate"><bean:write name="bannerContent" property="descripcion"></bean:write></p>
									</div>
								</div>
									</c:when>
									<c:otherwise>
								<div class="item">
									<a <bean:write name="bannerContent" property="hyperlink"></bean:write>>
										<img alt="" src="<%=request.getContextPath()%>/ImageBanner?idBanner=<bean:write name='bannerContent'  property='id'></bean:write>"></img>
									</a>
									<div class="carousel-caption hidden-xs">
										<p><bean:write name="bannerContent" property="descripcion"></bean:write></p>
									</div>
								</div>
									</c:otherwise>
									</c:choose>
								</logic:iterate>
							<a class="left carousel-control" href="#carousel-example-captions" role="button" data-slide="prev">
								<span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
								<span class="sr-only">Previous</span>
							</a>
							<a class="right carousel-control" href="#carousel-example-captions" role="button" data-slide="next">
								<span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
								<span class="sr-only">Next</span>
							</a>
						</div>
							</div>
						<!-- /banner -  ->

			<!-- Articulos de Interes -  ->

			<h2 class="titulosh2">Artículos de interés para ti</h2>
			<div class="panel panel-default">
				<div class="panel-body">
					<table id="notas_destacadas">
						<tbody>
						<logic:iterate id="crearArticulosForm" name="inicioForm" property="articulosFormList">
							<tr>
								<td>
									<p><a href="<bean:write name="crearArticulosForm"  property="redirect"></bean:write>"><bean:write name="crearArticulosForm"  property="titulo"></bean:write></a></p>
								</td>
							</tr>

						</logic:iterate>

						</tbody>
					</table>
				</div>
				<div class="panel-footer text-center">
					<a id="noticias_anteriores" class="btn btn-green" href="<c:url value="/articuloDeInteress.do"/>">Ver m&aacute;s art&iacute;culos</a>
				</div>
			</div>
			<!-- /Articulos de Interes -  ->
			<!--  <div class="app-download col-md-12">
				<div class="col-md-offset-4 col-sm-offset-4 col-xs-offset-4">
					<a href="#"><img alt="apl stor" src="css/img/bnn-appstore.png"></a>
					<a href="#"><img alt="gugl play stor" src="css/img/bnn-google-play.png"></a>
				</div>
				
			</div>-  ->
			
		</div>
		<div class="col-md-4">
			<!-- Proximos eventos -  ->
			<h2 class="titulosh2">Conoce los pr&oacute;ximos eventos</h2>
			<div id="registroEventos" name="REGISTROS_EVENTOS" >
				<jsp:include page="/registroseventos.do?method=proximosEventos" />
			</div>
			<div class="panel-footer text-center">
				<a class="btn btn-green" href="<c:url value="/registroseventos.do?method=nextEvents"/>">Ver todos los eventos</a>
			</div>
			
			
			
			<h2 class="titulosh2">Video: ¿Cómo registrarme al Portal del Empleo?</h2>
			
			
			<div class="link_video panel panel-default" style="text-align: center;">
				<a href="<c:url value="/jsp/empleo/herramientasDelSitio/videoComoRegistrarse.jsp"/>">
					<img alt="Video como registrarme" src="css/img/img_video.png" />
				</a>
			</div>
			
			
			
			 
			
			
			
			
		</div>
		<!-  - /Proximos eventos -  ->
	</div>-->


	<c:if test="${not empty errmsg}">
		<script>
			message('${errmsg}');
			<%session.removeAttribute("errmsg");%>
		</script>
	</c:if>
</c:if>	