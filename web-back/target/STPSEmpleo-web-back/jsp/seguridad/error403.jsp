<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="url" value="${pageContext.request.requestURI}" />

<t:publicTemplate>
	<jsp:attribute name="titulo">Acceso restringido</jsp:attribute>
    <jsp:attribute name="palabraDescripcion">Candidatos</jsp:attribute> 
	<jsp:attribute name="tituloSitio">Portal del empleo</jsp:attribute>
    <jsp:attribute name="urlRedSocial"><c:url value="${url}"/></jsp:attribute>
	<jsp:attribute name="descripcionRedSocial">
		La página solicitada esta restringida para tu nivel de acceso.
	</jsp:attribute>
	<jsp:body>
		<!-- div ruta_navegacion -->
	    <div class="row">
	      <div class="col-sm-12">
	        <ol class="breadcrumb">
	          <li>Ruta de navegación:</li>
	          <li><a href="<c:url value="/inicio.do"/>">Inicio</a></li>
          	  <li class="active">Acceso restringido.</li>
	        </ol>
	      </div>
	    </div>
	    <!-- /div ruta_navegacion -->
	    
		<div class="row">
			<div class="col-sm-8">
				<div id="error" class="contenedor">
					<div class="panel panel-grey">
						<div class="panel-body">
							<div class="col-md-6">
								<img class="img-responsive" src="${context}/css/images/alerta-icon.png" alt="">
							</div>
							<div class="col-md-6">
								<h2>Error 403<br>Acceso restringido</h2>
								<p><span class="medium">La p&aacute;gina solicitada est&aacute; restringida para tu nivel de acceso.</span></p>
								<p>Si no te has firmado o quieres cambiar de usuario, ingresa tu usuario y contraseña a continuaci&oacute;n </p>
							</div>
						</div>
					</div>
					<!-- div Inicio sesion -->
					<div class="col-sm-12">
						<div class="panel panelInicioSesion">
							<div class="panel-heading">
								<h3 class="panel-title">Inicio de sesión</h3>
							</div>
							<div class="panel-body">
								<div class="row">
									<div class="col-sm-8">
			
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
												   class="recuperar">¿Olvidaste tu usuario o contraseña?</a>
											</p>
											<p class="text-center">
												<input type="button" value="Iniciar Sesión" class="btn btn-sesion form-control"
													   onclick="javascript:document.loginForm.submit();" />
											</p>
										</form>
			
									</div>
								</div>
							</div>
						</div>
					</div>
					<!-- /div Inicio sesion -->
                
					<p>&nbsp;</p>
					<div class="clearfix"></div>
				</div>
			</div>
		</div>
	    
	    
	    
	    
	</jsp:body>
</t:publicTemplate>
