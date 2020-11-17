<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="mx.gob.stps.portal.core.empresa.vo.EmpresaVO"%>
<%
    String contextSWB = (String)application.getAttribute("DOMAIN_PORTAL");
    EmpresaVO empresa = (EmpresaVO)request.getSession().getAttribute("empresaheader");
    if (null != request.getSession().getAttribute("FROM_CIL")){
        contextSWB = (String)application.getAttribute("DOMAIN_PORTAL_CIL");
    }
%>

<script type="text/javascript">
    function employ() {
        document.ocp.searchPlace.value = document.ocp.searchPlace.value;
        document.ocp.searchQ.value = document.ocp.searchTopic.value;
        document.ocp.submit();
    }
    
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
<input type="hidden" id="hdnSession" data-value="@Request.RequestContext.HttpContext.Session["UserName"]" />
<input type="hidden" id="hdnSession2" value="${USUARIO_APP}" />
 	<!-- div herramientas in empresaheader1Resp.jsp temp-->
    <div class="row">
      	<div class="herramientas hidden-xs">
      	<!-- USUARIO_APP empty in empresaheader1Resp.jsp temp-->
	        <c:if test="${empty USUARIO_APP}">
	            <div class="col-sm-12">
	               <ul class="nav nav-pills pull-right">
	               		<li role="presentation">
	               			<div class="resgistrateComo">Regístrate como</div>
	               		</li>
	                    <li role="presentation"><a href="<%=response.encodeURL(request.getContextPath()+"/registro_candidato.do")%>" class="btn-cerrarSesion">Candidato</a></li>
	                    <li role="presentation">
							<div class="resgistrateComo">o</div>
						</li>
	                    <li role="presentation"><a href="<%=response.encodeURL(request.getContextPath()+"/registro_empresa.do")%>" class="btn-cerrarSesion">Empresa</a></li>
	                	<li role="presentation"><a href="javascript:dialogLoginPopup();" class="btn-herramientas">Iniciar sesión</a></li>
	                </ul>
	            </div>
	        </c:if>
	        
	        <!-- CUALQUIER ROL QUE NO SEA EMPRESA in empresaheader1Resp.jsp temp-->
	        <c:if test="${not empty USUARIO_APP && !USUARIO_APP.candidato && !USUARIO_APP.empresa}">
				<div class="col-sm-12">
	               <ul class="nav nav-pills pull-right">
	               		<li role="presentation">
	               			<div class="resgistrateComo">Regístrate como</div>
	               		</li>
	                    <li role="presentation"><a href="<%=response.encodeURL(request.getContextPath()+"/registro_candidato.do")%>" class="btn-cerrarSesion">Candidato</a></li>
	                    <li role="presentation">
							<div class="resgistrateComo">o</div>
						</li>
	                    <li role="presentation"><a href="<%=response.encodeURL(request.getContextPath()+"/registro_empresa.do")%>" class="btn-cerrarSesion">Empresa</a></li>
	                	<li role="presentation"><a href="javascript:dialogLoginPopup();" class="btn-herramientas">Iniciar sesión</a></li>
	                </ul>
	            </div>
	        </c:if>
	        
	        <!-- ROL EMPRESA -->
	        <c:if test="${not empty USUARIO_APP && USUARIO_APP.empresa}">
	            <div class="col-sm-12">
		            <ul class="nav nav-pills pull-right">
		              <li role="presentation">
		              	<div class="bienvenidoUser">Bienvenido: <strong><%=empresa.getNombreEmpresa()%></strong></div>
		              </li>
					  <li role="presentation">
					  	<a href="<c:url value="/miEspacioEmpresas.do"/>" class="btn-herramientas">Mi espacio</a>
					  </li>
					  <li role="presentation">
					  	<a href="<c:url value="/dondePublicar.do?method=init"/>" class="btn-herramientas">Crear oferta de empleo</a>
					  </li>
					  <li role="presentation">
					  	<a href="<c:url value="/edicionEmpresa.do?method=init"/>" class="btn-herramientas">Mis datos</a>
					  </li>
					  <li role="presentation">
					  	<a href="<%=request.getContextPath()%>/logout.do" class="btn-cerrarSesion">Cerrar sesión</a>
					  </li>
					</ul>
	            </div>
	       	</c:if>
	       	
	       	 <!-- ROL CANDIDATO in empresaheaderResp1.jsp temp-->
	        <c:if test="${not empty USUARIO_APP && USUARIO_APP.candidato}">
	        	<div class="col-sm-12">
		            <ul class="nav nav-pills pull-right">
		            	<c:if test="${FROM_CIL}">
		              		<li role="presentation">
            					<img src="<%=response.encodeURL(request.getContextPath())%>/css/cil/logo_cil.gif" width="250" height="15" alt="Centros de Intermediaci&oacute;n Laboral" />
            		  		</li>
            		  	</c:if>
		              <li role="presentation">
		              	<div class="bienvenidoUser">Bienvenido: <strong>${candidatoheader.nombre}</strong></div>
		              </li>
		              <c:if test="${FROM_CIL}">
	            		<li role="presentation">
	            			<a href="<%=response.encodeURL(request.getContextPath()+"/servicioscil.do?method=init")%>" class="btn-herramientas">Registro de Servicios</a>
                    	</li>
                    	<li role="presentation">
                    		<a href="<%=response.encodeURL(request.getContextPath()+"/seguimientocil.do?method=init")%>" class="btn-herramientas">Seguimiento Servicios</a>
                    	</li>
                      </c:if>
                      <c:if test="${!FROM_CIL}">
						  <li role="presentation">
						  	<a href="<c:url value="/miEspacioCandidato.do"/>" class="btn-herramientas">Mi espacio</a>
						  </li>
						  <li role="presentation">
						  	 <li><a href="/registro-unico.do?method=redirectEditaCandidatoRU" class="btn-herramientas">Mi	perfil</a></li>
						  </li>
					  </c:if>
					  <li role="presentation">
					  	<a href="<%=response.encodeURL(request.getContextPath()+"/misofertas.do?method=misPostulaciones")%>" class="btn-herramientas">Mis ofertas laborales</a>
					  </li>
					  <li role="presentation">
					  	<a class="btn-cerrarSesion" href="<%=request.getContextPath()%>/logout.do">Cerrar sesión</a>
					  </li>
					</ul>
	            </div>
	        </c:if>
        </div>
        
        <div class="herramientas visible-xs">
			<div class="col-xs-12">
				<div class="dropdown">
					<!-- ROL EMPRESA in empresaheader1Resp.jsp temp-->
					<c:if test="${USUARIO_APP.empresa}">
						<button class="btn-sesion btn btn-sm btn-block dropdown-toggle"type="button" id="menu1" data-toggle="dropdown">
							Bienvenido: <strong><%=empresa.getNombreEmpresa()%></strong> <span
								class="caret"></span>
						</button>
					</c:if>
					<!-- ROL CANDIDATO -->
					<c:if test="${USUARIO_APP.candidato}">
						<button class="btn-sesion btn btn-sm btn-block dropdown-toggle"type="button" id="menu1" data-toggle="dropdown">
							Bienvenido: <strong>${candidatoheader.nombre}</strong> <span
								class="caret"></span>
						</button>
					</c:if>
					<!-- CUALQUIER ROL QUE NO SEA EMPRESA -->
					<c:if test="${empty USUARIO_APP || !USUARIO_APP.candidato && !USUARIO_APP.empresa}">
						<button class="btn-sesion btn btn-sm btn-block dropdown-toggle"type="button" id="menu1" data-toggle="dropdown">
							<strong>Iniciar sesión</strong> <span
								class="caret"></span>
						</button>
					</c:if>
					
					<ul class="dropdown-menu" role="menu" aria-labelledby="menu1">
						<!-- ROL EMPRESA -->
						<c:if test="${USUARIO_APP.empresa}">
							<li class="dropdown-header"><%=empresa.getNombreEmpresa()%></li>
<%-- 							<li><a href="${context}miEspacioEmpresas.do">Mi espacio</a></li> --%>
<%-- 							<li><a href="${context}dondePublicar.do?method=init">Crear oferta de empleo</a></li> --%>
<%-- 							<li><a href="${context}edicionEmpresa.do?method=init">Mis datos</a></li> --%>
						</c:if>
						<!-- ROL CANDIDATO -->
						<c:if test="${USUARIO_APP.candidato}">
							<li class="dropdown-header">${candidatoheader.nombre}</li>
<%-- 							<li><a href="${context}miEspacioCandidato.do">Mi espacio</a></li> --%>
<%-- 							<li><a href="${context}perfil.do?method=init">Mi perfil</a></li> --%>
<%-- 							<li><a href="${context}misofertas.do?method=misPostulaciones">Mis ofertas laborales</a></li> --%>
						</c:if>
						<!-- CUALQUIER ROL QUE NO SEA EMPRESA -->
						<c:if test="${empty USUARIO_APP || !USUARIO_APP.candidato && !USUARIO_APP.empresa}">
<%-- 							<li class="hidden-xs"><a href="${context}registro_candidato.do">Candidato</a></li> --%>
<%-- 							<li class="hidden-xs"><a href="${context}registro_empresa.do">Empresa</a></li> --%>
							<li role="presentation"><a href="javascript:dialogLoginPopup();">Iniciar sesión</a></li>
						</c:if>
							<li class="divider"></li>
							<li><a href="<c:url value="/jsp/empleo/herramientasDelSitio/mapaDeSitio.jsp"/>">Mapa de sitio</a></li>
							<li><a href="<c:url value="/miespacionav.do?method=agendaCita"/>">Solicita una cita</a></li>
							<li><a href="<c:url value="/jsp/empleo/herramientasDelSitio/contacto.jsp"/>">Contacto</a></li>
						<!-- ROL EMPRESA -->
						<c:if test="${USUARIO_APP.empresa}">	
							<li class="divider"></li>
							<li><a href="<%=request.getContextPath()%>/logout.do">Cerrar sesión</a></li>
						</c:if>
						<!-- ROL CANDIDATO -->
						<c:if test="${USUARIO_APP.candidato}">	
							<li class="divider"></li>
							<li><a href="<%=request.getContextPath()%>/logout.do">Cerrar sesión</a></li>
						</c:if>
						
					</ul>
				</div>
			</div>
        </div>
    </div>
    <!-- /div herramientas -->
