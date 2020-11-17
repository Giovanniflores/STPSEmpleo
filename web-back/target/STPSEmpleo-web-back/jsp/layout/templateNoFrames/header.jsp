<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="mx.gob.stps.portal.web.security.vo.UsuarioWebVO"%>
<%@ page import="mx.gob.stps.portal.web.infra.utils.Constantes"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String context = (String)application.getAttribute("DOMAIN_PORTAL");
	String contextApp = request.getContextPath();
	String contextSWB = (String)application.getAttribute("DOMAIN_PORTAL");
	UsuarioWebVO usuario = (UsuarioWebVO) session.getAttribute(Constantes.USUARIO_APP);
	if (null != request.getSession().getAttribute("FROM_CIL")) {
		context = (String)application.getAttribute("DOMAIN_PORTAL_CIL");
		contextSWB = (String)application.getAttribute("DOMAIN_PORTAL_CIL");
	}
%>
<link href="<%=contextApp%>/css/bootstrap/bootstrap.css" rel="stylesheet" type="text/css">

<link rel="shortcut icon" media="all" type="image/x-icon" href="<%=request.getContextPath()%>/images/favicon.ico" /> 
<link rel="alternate" type="application/rss+xml" title="Portal del Empleo" href="https://empleo.gob.mx/rss/DF" />
<link href="https://fonts.googleapis.com/css?family=Droid+Sans" rel="stylesheet" type="text/css" />

<script type="text/javascript">
	var djConfig = {
		parseOnLoad : true,
		isDebug : false
	};

</script>
<script type="text/javascript">
	function employ() {
		document.ocp.searchPlace.value = document.ocp.searchPlace.value;
		document.ocp.searchQ.value = document.ocp.searchTopic.value;
		document.ocp.submit();
	}

</script>
<!-- TK2806  -->
<script src="<%=request.getContextPath()%>/js/co.browsing.js"></script>
	<div id="herramientas">

	 <%
	if (null != request.getSession().getAttribute("FROM_CIL"))
		request.setAttribute("CIL","ON");
	else if (null != request.getAttribute("CIL") && "ON".equals((String)request.getAttribute("CIL")))
		request.getSession().setAttribute("USER_CIL_ON", "READY");
	String cil = (String)request.getAttribute("CIL");
	if (null != cil) {
	%>
		<img src="<%=contextApp%>/css/cil/logo_cil.gif" width="474" height="51" alt="Centros de Intermediaci&oacute;n Laboral" />
	<%
			if ("ON".equals(cil)) {
	%>
			<ul class="cil">
				<li><a href="<%=contextApp%>/servicioscil.do?method=init">Registro de Servicios</a></li>
				<li><a href="<%=contextApp%>/seguimientocil.do?method=init">Seguimiento Servicios</a></li>
			</ul>
	<%
			}
		}else {
	%>
		<img src="<%=contextApp%>/css/cil/logo_STPS.gif" width="305" height="51" alt="Secretar&iacute;a del Trabajo y Previsi&oacute;n Social: Servicio Nacional de Empleo" />
	<%
		}
	%>
		<!-- <ul>
			<li><a href="<c:url value="/miEspacioCandidato.do"/>">Mi espacio</a></li>
			<li><a href="<c:url value="/jsp/empleo/candidatos/bolsasEmpleoAsociadas.jsp"/>">Sitios relacionados</a></li>
			<li><a href="<c:url value="/jsp/empleo/herramientasDelSitio/necesitasAyuda.jsp"/>">¿Necesitas ayuda?</a></li>
			<li><a href="<c:url value="/jsp/empleo/herramientasDelSitio/politicasYCondicionesDeUso.jsp"/>">Políticas y condiciones de uso </a></li>
		</ul> -->

	</div>
	<div class="row">
    	<div id="header" class="herramientas hidden-xs">
    		<div class="col-sm-12">
				
				<%
					if (usuario != null) {
				%>
				<%
					if (usuario.getEmpresa()) {
				%>
				<div id="mi_espacioHome" class="col-sm-5 col-sm-push-7">
					<div id="mi_espacioFoto" class="col-md-4">
						<img
							src="<%=contextApp%>/imageAction.do?method=getImagen&usuario=<%=usuario.getUsuario()%>"
							title="foto del perfil"  alt="foto del usuario"/>
					</div>
					<div class="col-md-8">
					<h2 class="mi_espacioTitulo">Mi espacio:</h2>
					<div class="nombrePerfil">${empresaheader.nombreEmpresa}<br />
					<a class="miEspacio"
						href="<c:url value="/miEspacioEmpresas.do"/>"> Ir a mi espacio
					</a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a class="cerrarSesion" href="<%=contextApp%>/logout.do"> Cerrar
						Sesi&oacute;n </a>
						</div>
						</div>
				</div>
				<%
					} else if (usuario.getCandidato()) {
				%>
				<div id="mi_espacioHome" class="col-sm-5 col-sm-push-7">
					<div id="mi_espacioFoto" class="col-md-4">
						<img
							src="<%=contextApp%>/imageAction.do?method=getImagen&usuario=<%=usuario.getUsuario()%>"
							title="foto del perfil" alt="foto del usuario"/>
					</div>
					<div class="col-md-8">
					<h2 class="mi_espacioTitulo">Mi espacio:</h2>
					<div class="nombrePerfil">${candidatoheader.nombre}<br />
					<a class="miEspacio"
						href="<c:url value="/miEspacioCandidato.do"/>"> Ir a mi espacio </a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a class="cerrarSesion" href="<%=contextApp%>/logout.do">
						Cerrar Sesi&oacute;n </a>
						</div>
						</div>
				</div>
				<%
					} else if (usuario.getAdministrador()) {
				%>
				<div id="mi_espacioHome" class="col-sm-5 col-sm-push-7">
					<div id="mi_espacioFoto" class="col-md-4">
						<img
							src="<%=contextApp%>/imageAction.do?method=getImagen&usuario=admin@<%=usuario.getUsuario()%>"
							title="foto del perfil" alt="foto del usuario"/>
					</div>
					<div class="col-md-8">
					<h2 class="mi_espacioTitulo">Mi espacio:</h2>
					<div class="nombrePerfil">${datosAdmin}<br />
					<a class="miEspacio"
						href="<c:url value="/miEspacioCandidato.do"/>">Ir a mi espacio </a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a class="cerrarSesion" href="<%=contextApp%>/logout.do">
						Cerrar sesi&oacute;n </a>
						</div>
						</div>
				</div>
				<%
					} else if (usuario.getIdPerfil() == mx.gob.stps.portal.core.infra.utils.Constantes.PERFIL.SUPERVISOR_SNETEL.getIdOpcion()) {
				%>
				<div id="mi_espacioHome" class="col-sm-5 col-sm-push-7">
					<div id="mi_espacioFoto" class="col-md-4">
						<img
							src="<%=contextApp%>/imageAction.do?method=getImagen&usuario=admin@<%=usuario.getUsuario()%>"
							title="foto del perfil" alt="foto del usuario"/>
					</div>
					<div class="col-md-8">
					<h2 class="mi_espacioTitulo">Mi espacio:</h2>
					<div class="nombrePerfil">${datosAdmin}<br />
					<a class="miEspacio"
						href="<c:url value="/filtercompany.do?method=init"/>"> Ir a mi espacio </a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a class="cerrarSesion" href="<%=contextApp%>/logout.do">
						Cerrar sesi&oacute;n </a>
						</div>
						</div>
				</div>
				<%
					} else if (usuario.getPublicador()) {
				%>
				<div id="mi_espacioHome" class="col-sm-12">
					<div id="mi_espacioFoto" class="col-md-4">
						<img
							src="<%=contextApp%>/imageAction.do?method=getImagen&usuario=admin@<%=usuario.getUsuario()%>"
							title="foto del perfil" alt="foto del usuario"/>
					</div>
					<div class="col-md-8">
					<h2 class="mi_espacioTitulo">Mi espacio:</h2>
					<div class="nombrePerfil">${datosAdmin}<br />
					<a class="miEspacio"
						href="<%=contextApp%>/autorization.do?method=init"> Ir a mi espacio </a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a class="cerrarSesion" href="<%=contextApp%>/logout.do">
						Cerrar sesi&oacute;n </a>
						</div>
						</div>
				</div>
				<%
					}
					} else {
				%>
				<div id="perfil_usuario_2012">
					<ul class="nav nav-pills pull-right">
						<li role="presentation"><div class="resgistrateComo">Regístrate como: </div></li>
						<li role="presentation"><a class="candidato btn-cerrarSesion" href="<c:url value="/registro_candidato.do"/>">Candidato</a></li>
						<li role="presentation">
							<div class="resgistrateComo">o</div>
						</li>
						<li role="presentation"><a class="empresa btn-cerrarSesion" href="<c:url value="/registroEmpresa.do?method=init"/>">Empresa</a></li>
						<li role="presentation"><a class="btn-herramientas" href="<c:url value="/inicio.do"/>">Inicia sesión</a></li>
					</ul>
				</div>
				<%
					}
				%>
			</div><div class="clearfix"></div>
		</div>
    </div>
    
    	<div class="row">
	      <div class="col-sm-7">
	        <div class="header">
	         <a href="<c:url value="/inicio.do"/>"> 
	          <img src="<%=contextApp%>/css/images/m_empleoGob.png" alt="Portal del Empleo : llama al 01 800 841 20 20" class="img-responsive">
			 </a>
	        </div>
	      </div>
	
	      <div class="col-sm-5 hidden-xs"> 
	          <div class="col-xs-6 logoSTPS">
	        	<a href="http://www.stps.gob.mx">
		          <img src="<%=contextApp%>/css/images/stps_logo.png" alt="Secretaría del Trabajo y Previsión Social" class="img-responsive">
	        	</a>
	      	  </div>
		      <div class="col-xs-6 logoSNE"> 
		        <a href="/SNE" onclick="window.location.href='/SNE';return false;">
			          <img src="<%=contextApp%>/css/images/sne_logo.png" alt="Servicio Nacional de Empleo" class="img-responsive">
		         </a>
		      </div>
		      
	      	  <div class="clearfix"></div>   
	        
	          <div class="text-center nav_rapido"> 
	          <!-- TK2806  -->
	           	 <div class="col-xs-3">
	              <a	href="javascript:acquireIO.max()">Ayuda En vivo</a>
	            </div>
	            <div class="col-xs-4">
	              <a href="<c:url value="/jsp/empleo/herramientasDelSitio/mapaDeSitio.jsp"/>">Mapa de sitio</a>
	            </div>
	            <div class="col-xs-4">
	              <a href="<c:url value="/jsp/empleo/herramientasDelSitio/contacto.jsp"/>">Contacto</a>
	            </div>
	            <%
				if (usuario	!=	null &&	usuario.getCandidato()) {
				%>
	            <div class="col-xs-4 solicitaCita">
	            	<img src="<%=request.getContextPath()%>/css/images/bg_agendaCita2.png" alt="">
		            <a href="<%=response.encodeURL(request.getContextPath()+"/miespacionav.do?method=agendaCita")%>">&nbsp;Solicita una cita</a>
	            </div>
	             <%
					}
				%>
	      	  </div>
	       </div>
		</div>
		
		<div class="row">
	<div class="col-sm-12">
		<div class="btn-group btn-group-justified nav-black">
			<a href="/candidatos" class="btn btn-candidato">Candidatos</a> 
			<a href="/empresas" class="btn btn-empresa">Empresas</a>
			<a href="/SNE" class="btn btn-SNE">Servicio Nacional de Empleo</a>
		</div>
	</div>
</div>



	 <div id="espacio_buscador" class="row">
        <div id="buscador" class="ac_2011 b col-sm-12">
        	<div class="panel panel-buscador">
        		<div class="panel-body">
        			<div class="row">
			            <!--  div class="swb-ilta" id="ilta_31">
			            	${SHOW_BUSCADOR_OFERTAS}
							<span class="numero_ofertas">ofertas de empleo</span></div-->
			            <!--Empieza nuevo buscador-->
			            <form name="ocp" id="ocp" action="<c:url value="/ocupate.do"/>" method="get">
							<div>
							 <input type="hidden" name="method" value="init" />
							 <input type="hidden" name="searchQ" value="" />
							 <div class="col-md-5">  
							 	<div class="form-group">             
									<label for="searchTopic" class="t_buscador b2">¿Qué empleo buscas?</label>                
									<input class="form-control" name="searchTopic" id="searchTopic" value="" type="text" />
								</div>
								<span class="help-block">Puedes indicar un puesto, carrera u oficio</span> 
							 </div>
							 <div class="donde col-md-5" id="spryselect1"> 
							 	<div class="form-group">
							 		<div id="combo">
							 			<label for="searchPlace" class="t_buscador">¿Dónde?</label>
							 				<select class="form-control" name="searchPlace" id="searchPlace">
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
												</p>
											  </div>  
										   </div>
										   <span class="help-block text-right">
										   	<a href="<c:url value="/jsp/empleo/herramientasDelSitio/usoBuscador.jsp"/>" class="ayuda1" title="Ayuda para uso del buscador">¿Cómo utilizar el buscador?</a>
										   </span>
							</div>
							<div class="col-md-2">
										   	<span class="hidden-xs blockBtn"></span>
										   	<input class="btn btn-buscador form-control" type="button" name="bt_buscador" id="bt_buscador" onclick="employ()" value="Buscar" />
										   </div>
							</form>
							
		            	</div>
	            	</div>
	            	<div class="panel-footer text-right">
			            <span>También puedes realizar una <a class="busqueda_especifica" href="<c:url value="/busquedaEspecificaOfertas.do?method=buscar"/>">búsqueda específica</a></span>
			          </div>
	            </div>
        </div>
	<div class="row">
      <div class="col-sm-4">
        <div class="panel panel-contactoSWB inac">
          <div class="ayuda">
            <a href="<c:url value="/jsp/empleo/herramientasDelSitio/necesitasAyuda.jsp"/>" >
              Ayuda en línea</a>
          </div>       
        </div>
      </div>

      <div class="col-sm-4">
        <div class="panel panel-contactoSWB">
          <div class="atencion">
           <a href="/herramientas/contacto">Atención: 01 800 841 2020</a>
        </div>
      </div>
    </div>

      <div class="col-sm-4">
        <div class="panel panel-contactoSWB">
          <div class="quejas">
            <a href="<c:url value="/suggestion.do?method=init"/> target="popUp" onClick="window.open(this.href, this.target, 'toolbar=no,directories=no,scrollbars=no,location=no,statusbar=no,menubar=no,resizable=yes,width=830,height=700'); return false;">Quejas y sugerencias</a>
          </div>       
        </div>
      </div>
   </div>
 </div>
</div>