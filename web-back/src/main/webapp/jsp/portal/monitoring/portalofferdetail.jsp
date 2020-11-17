
<%@page import="mx.gob.stps.portal.web.infra.utils.Constantes"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="mx.gob.stps.portal.web.infra.utils.Utils, mx.gob.stps.portal.web.offer.wrapper.PerfilJB, 
mx.gob.stps.portal.persistencia.vo.TelefonoVO, mx.gob.stps.portal.web.offer.form.OfferQuestionForm,java.util.ArrayList, 
mx.gob.stps.portal.core.oferta.pregunta.vo.OfertaPreguntaVO,
mx.gob.stps.portal.web.infra.utils.Constantes, mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_USUARIO,
mx.gob.stps.portal.web.infra.utils.PropertiesLoader, mx.gob.stps.portal.web.offer.wrapper.OfertaJB, mx.gob.stps.portal.web.infra.utils.Utils"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
	int idTipoUsuarioEmpresa = Constantes.ID_TIPO_USUARIO_EMPRESA;
	int idTipoUsuarioAdministrador = TIPO_USUARIO.ADMINISTRADOR.getIdTipoUsuario();
	pageContext.setAttribute("idTipoUsuarioEmpresa",idTipoUsuarioEmpresa);	
	int OFERTA_FUNCION_PUBLICA = 2;
	int compatibilityLimit = PropertiesLoader.getInstance().getPropertyInt("compatibility.upper.limit");
	String strRegistroCandidato = PropertiesLoader.getInstance().getProperty("app.swb.redirect.liga.registro.candidato");
	OfferQuestionForm offer = (OfferQuestionForm)request.getSession().getAttribute("OfferQuestionForm");
	OfertaJB employ = offer.getOfertaJB();
	String context = request.getContextPath() +"/";
	String invoke = (null != request.getSession().getAttribute("_urlpageinvoke") ? (String)request.getSession().getAttribute("_urlpageinvoke") : "javascript:history.go(-1)");
	String reference = "showMoreCompanyOffers.do?method=init&idEmpresa=" + employ.getIdEmpresa();
	String strHorarioLaboral="";
	String strHoraEntrada=employ.getHoraEntrada();
	String strHoraSalida=employ.getHoraSalida();
	if(null!=strHoraEntrada && !strHoraEntrada.equalsIgnoreCase(""))
		strHorarioLaboral = strHorarioLaboral + strHoraEntrada + ":00";
	if(null!=strHoraSalida && !strHoraSalida.equalsIgnoreCase(""))
		strHorarioLaboral = strHorarioLaboral + " a " + strHoraSalida + ":00";
	%>
	<c:set var="invoke" value="<%=invoke%>"/>
	<c:set var="postuladoOK" value="${postuladoOK}"/>
	<c:set var="OFERTA_FUNCION_PUBLICA" value="<%=OFERTA_FUNCION_PUBLICA%>"/>
	<c:set var="idTipoUsuarioAdministrador" value="<%=idTipoUsuarioAdministrador%>"/>

<script type="text/javascript">
	dojo.require("dijit.dijit"); // loads the optimized dijit layer
	dojo.require("dijit.form.Form");
	dojo.require("dijit.Dialog");
	dojo.require("dijit.form.TextBox");
		
	var dialogAviso;

	function doAjax(method) {
		dojo.byId('method').value=method;
		dojo.byId('send').disabled=true;
		dojo.xhrPost(
			{
				url: 'detalleoferta.do',
				form:'form',
				timeout:180000, // 3 min
				load: function(data) {
					var res = dojo.fromJson(data);
					dojo.byId('message').innerHTML=res.message;
			    }
			 });
	}
	function doAjaxPost(method, idCandidato, idOfertaEmpleo) {
		dojo.byId('method').value=method;
		dojo.byId('idCandidato').value=idCandidato;
		dojo.byId('idOfertaEmpleo').value=idOfertaEmpleo;
		//dojo.byId('agregar_miEspacio').disabled=true;
		dojo.xhrPost(
			{
				url: 'detalleoferta.do?method='+method+'&idCandidato='+idCandidato+'&idOfertaEmpleo='+idOfertaEmpleo,
				//form:'formPost',
				timeout:180000, // 3 min
				load: function(data) {
					var res = dojo.fromJson(data);
					alert(res[1].message);
					dojo.byId('sendPost').innerHTML=res[0].message;
			    }
			});
	}
	function doAjaxQuestion(method, idCandidato, idOfertaEmpleo) {
		dojo.byId('method').value=method;
		dojo.byId('idCandidato').value=idCandidato;
		dojo.byId('idOfertaEmpleo').value=idOfertaEmpleo;
		dojo.byId('sendQuestion').disabled=true;
		dojo.xhrPost(
			{
				url: 'detalleoferta.do',
				form:'formQuestion',
				timeout:180000, // 3 min
				load: function(data) {
					var res = dojo.fromJson(data);
					dojo.byId('messageQuestion').innerHTML=res.message;
			    }
		    });
	}

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
			if (pswclean){
				field.value='';
				pswclean = false;
			}		
		}
	}	
	
	function mensaje(mensaje){
		alert(mensaje);
		//var vMensaje = new dijit.Dialog({draggable: false, title: 'Notificación', content: mensaje, style: "width: 300px"});
		//vMensaje.show();
		
	}
	
	  function redirectSWB(url){
			window.top.location = url;
		  }

		dojo.addOnLoad(function() {
			// if(${not empty USUARIO_APP})
			  cargaIframeMasOfertas();
		});
	  
	  function cargaIframeMasOfertas(){
		  dojo.xhrPost({url: 'ocupate.do?method=masOfertasDeEmpleo&id_oferta_empleo='+"${id_oferta_empleo}", timeout:180000,
              load: function(data) {
                    dojo.byId('masOfertas').innerHTML = data;
              }});
	  }
	  
		function recuperaPsw(){
			dojo.byId('method').value='toRecuperaContrasena';
			dojo.byId('formLogin').submit();			
		}
	  
	  /*
	  function avisoCandidatoPostulado(){		  
		  postuladoOK = "${postuladoOK}";
		  if (postuladoOK) {
				dialogAviso = new dijit.Dialog({
			        title: 'Mensaje',
			        href: '${context}detalleoferta.do?method=avisoCandidatoPostuladoAction',			        		
			        style: 'width:500px; height:375px; background: #FFF',
			        showTitle: false, draggable : false, closable : true
			    });
				dialogAviso.show();
		  }		  
	  }
	  
	  function cierraAvisoCandidatoPostulado(){
		  dialogAviso.hide(); 
	  }*/

		function postularmeNoSesion(){
		    window.top.scrollTo(0,250);
		    dojo.byId('username').focus();		    
		  	alert('Para postularse a la oferta es preciso registrarse o iniciar sesión.');
		}
	  
	  function redireccionar(home){
			 window.top.location.href=home;
	  }

	  function postularOferta(){
			document.opost.action='${context}detalleoferta.do?method=postulado';	
			document.opost.submit();
	  }
	  
	  	function maximaLongitud(texto,maxlong)
	  	{
	  	var tecla, int_value, out_value;

	  	if (texto.value.length > maxlong)
	  	{
	  	/*con estas 3 sentencias se consigue que el texto se reduzca
	  	al tamaño maximo permitido, sustituyendo lo que se haya
	  	introducido, por los primeros caracteres hasta dicho limite*/
	  	in_value = texto.value.replace(/^\s+|\s*[\r\n][\r\n \t]*/g, "");
	  	
	  	out_value = in_value.substring(0,maxlong);
	  	texto.value = out_value;
	  	
	  	return false;
	  	}
	  	return true;
	  	}			  
</script>

<c:if test="${not empty errors}">
<script type="text/javascript">

	dojo.addOnLoad(function(){
		mensaje('${errors}');
	});
</script>
</c:if>

<div class="detalleoferta">

	<h2 class="titulo_profesion"><%=employ.getTituloOferta()%></h2>
	
	<div class="detalles" id="login">
		<c:if test="${not empty USUARIO_APP}">
       		<a href="logout.do" id="cerrar_sesion">Cerrar sesión</a>
       	</c:if> 			
    </div>
	
    <div class="ficha_oferta">
		<div class="logo_empresa">
			<table>
        	<tr>
            	<td width="191px" valign="middle" height="191px" align="center">
              	<img src="<%=context%>imageAction.do?method=getImagen&ID_EMPRESA=<%=employ.getIdEmpresa()%>" alt="<%=employ.getEmpresaNombre()%>" />
            	</td>
        	</tr>	
    		</table>
			
        </div>
        <div class="text_ficha_oferta">
			<h3><%=employ.getEmpresaNombre()%></h3>
		
		<c:if test="${not empty USUARIO_APP&&idTipoUsuarioEmpresa!=USUARIO_APP.idTipoUsuario}">
		<a 	class="consultar_mas_vacantes" 
			onclick="javascript:if(confirm('Se iniciará la búsqueda de ofertas de empleo de la empresa, por lo que perderá su primera búsqueda. ¿Desea continuar?'))return true; else return false;" 
			href="<%=reference%>">
				M&aacute;s ofertas de esta empresa
		</a>
		</c:if>	
		<c:if test="${empty USUARIO_APP}">
		<a 	class="consultar_mas_vacantes" 
			onclick="javascript:if(confirm('Se iniciará la búsqueda de ofertas de empleo de la empresa, por lo que perderá su primera búsqueda. ¿Desea continuar?'))return true; else return false;" 
			href="<%=reference%>">
				M&aacute;s ofertas de esta empresa
		</a>
		</c:if>	
				<p>Oferta de empleo<br><strong><%=employ.getTituloOferta()%></strong></p>
				<% 
					if (null != request.getSession().getAttribute("_user") && OFERTA_FUNCION_PUBLICA != employ.getFuenteId()) {
						PerfilJB vo = offer.getPerfilJB();
						TelefonoVO tel = vo.getPrincipal();
				%>	
						<!-- id="medidor" -->	
						<div class="reqEmp">
							<p><strong><%=vo.getNombre()%></strong><br /> tu porcentaje de compatibilidad <br /> con esta oferta de empleo es del:<br />
				   				<span id="porcentaje"><%=offer.getCompatibility()%>%</span>
							</p>
							Para mejorar el porcentaje de compatibilidad, te sugerimos <a class="perfil" href="<c:url value="/perfil.do?method=init"/>">Ampliar tu perfil laboral</a>									
						</div>
									
				<%					
					}
				%>
				<p>Tipo de contrato<br><strong><%=employ.getTipoContrato()%></strong></p>
				<p>Horario laboral<br><strong><%=strHorarioLaboral%></strong></p>				
				<p>Ubicación<br><strong><%=employ.getUbicacion()%></strong><br /></p>
				
				<c:if test="${not empty USUARIO_APP and USUARIO_APP.idTipoUsuario == idTipoUsuarioAdministrador}">
					<p>Fuente<br><strong><%=employ.getFuente()%></strong><br /></p>
				</c:if>

			<%
				if (null != request.getSession().getAttribute("_user") && !"".equals(employ.getMapaUbicacion())) {
			%>
					<a href="javascript:void(0)" onclick="window.open('<%=employ.getMapaUbicacion()%>','mapa','height=600,width=800,menubar=no,location=no,toolbar=no,scrollbars=no, resizable=yes');">Ver mapa de ubicación</a>
			<%
				}
			%>
		
		</div>		

		<c:if test="${empty USUARIO_APP}">
			<div id="log-inContextual2">		
	    		<p class="naranja">¿Te interesa esta oferta de empleo? Inicia sesión para postularte</p>
		        
			<form name="formLogin" id="formLogin" method="post" action="detalleoferta.do">  
						<input type="hidden" name="idOfertaEmpleo" id="idOfertaEmpleo" value="<%=offer.getIdOfertaEmpleo()%>"/> 
						<input type="hidden" name="method" id="method" value="loginUser"/>   
		       			<input type="hidden" id="isHome" name="isHome" value="false" />
		                <p>	
			        	<div class="log_indications2">
							<span>Escribe el correo con el que te registraste en este portal, despu&eacute;s escribe la contrase&ntilde;a enviada a tu correo electr&oacute;nico</span>
						</div>
		            	<input type="text" name="username" id="username" value="Escribe tu nombre de usuario" onfocus="clean(this, 1)"/>
		           		<input type="password" name="password" id="password" value="contrasena" onfocus="clean(this, 2)" onkeypress="keySubmit(event)" />
			        	<input type="submit" class="bottom_orange" value="Iniciar sesión" onclick="javascript:document.formLogin.submit();" />			        	
		        		</p>	        
		        	<p id="recuperar-password"><a href="#" onclick="recuperaPsw();">¿Olvidaste tu usuario o contraseña?</a></p>	        
		        </form>		        		
			</div>
		
		<p id="registrate">
			<span>Recuerda que debes estar registrado en el portal</span>
			<input type="button" class="bottom_blue" value="Regístrate" onclick="redirectSWB('<%out.print(strRegistroCandidato);%>')" />
		</p>  
		
		</c:if>		

		<p class="ligas_consulta">					
			<c:choose>
				<c:when test="${not empty _urlpageinvoke}">
					<c:url var="url" value="${_urlpageinvoke}"/>
					<a class="regresar_a_resultado" href="${url}">Regresar</a>			
				</c:when>
				<c:otherwise>
					<a class="regresar_a_resultado" href="javascript:history.go(-1)">Regresar</a>
				</c:otherwise>
			</c:choose>		
						
			<c:if test="${not empty _user and OFERTA_FUNCION_PUBLICA != OfferQuestionForm.ofertaJB.fuenteId}">
				<c:choose>
					<c:when test="${OfferQuestionForm.ofertaJB.estatusOfertaCandidato eq requestScope.ESTATUS_SELECCIONADA or 
									OfferQuestionForm.ofertaJB.estatusOfertaCandidato eq requestScope.ESTATUS_POSTULADO    or
									OfferQuestionForm.ofertaJB.estatusOfertaCandidato eq requestScope.ESTATUS_VINCULADO}">
						<c:url var="urlMisOfertas" value="misofertas.do">
							<c:param name="method" value="init"/>
						</c:url>
						<a class="perfil" href="${urlMisOfertas}">En mis ofertas de empleo</a>								
					</c:when>
					<c:when test="${OfferQuestionForm.ofertaJB.estatusOfertaCandidato eq '0'}">			
						<a class="agregar" onclick="doAjaxPost('addToMyOffers', '${OfferQuestionForm.idCandidato}','${OfferQuestionForm.idOfertaEmpleo}');" href="#">agregar a mis ofertas de empleo</a>
					</c:when>
				</c:choose>
			</c:if>
		
		</p>

    </div>

    <div class="fichas_vacante">
		<div class="ficha_relacionada">
			<h3>Requisitos</h3>
            <p><strong>Último grado de estudios:</strong> <%=employ.getGradoEstudios()%></p>
			 <%if(!("").equals(employ.getEspecialidades()) && !("Ninguna").equalsIgnoreCase(employ.getEspecialidades())){ %>
				<p><strong>Carrera o especialidad:</strong> <%=employ.getEspecialidades()%></p>
			<%}%>            
			<%if(!("").equals(employ.getSituacionAcademica()) && !("Ninguna").equalsIgnoreCase(employ.getSituacionAcademica())){ %>
				<p><strong>Situación académica:</strong> <%=employ.getSituacionAcademica()%></p>
			<%}%> 
			
			<p><strong>Conocimientos y habilidades generales:</strong> <%=employ.getHabilidadGeneral()%></p>

			<%if(!("").equals(employ.getExperienciaAnios()) && !("Ninguna").equalsIgnoreCase(employ.getExperienciaAnios())){ %>
				<p><strong>Experiencia:</strong> <%=employ.getExperienciaAnios()%></p>
			<%}%> 	            
            
            
			<%if(!("").equals(employ.getRequisitos())){ %><p><strong>Competencias:</strong> <%=employ.getRequisitos()%></p><%} %>
			<%if(!("").equals(employ.getIdiomasCert())){ %>
				<p><strong>Idiomas:</strong> <%=employ.getIdiomasCert()%></p>
			<%}%> 
			<%
				if (employ.getEdadRequisito() == 2) {
			%>
					<p><strong>Rango de edad:</strong> <%=employ.getEdadMinima()%> a <%=employ.getEdadMaxima()%> años</p>
			<%
				}
			%>
            <p><strong><%=employ.getDisponibilidadViajar()%></strong></p>
			<p><strong><%=employ.getDisponibilidadRadicar()%></strong></p>
			<%if(!("").equals(employ.getObservaciones()) && !("Ninguna").equalsIgnoreCase(employ.getObservaciones())){ %>
				<p><strong>Observaciones:</strong> <%=employ.getObservaciones()%></p>
			<%}%>  
        </div>
        <div class="ficha_relacionada">
			<h3>Descripci&oacute;n de la oferta</h3>
			 <!-- <p><strong>&Aacute;rea laboral:</strong> <%=employ.getAreaLaboral()%></p> -->
			<p><strong>Ocupaci&oacute;n:</strong>  <%=employ.getOcupacion()%></p>
			<!-- <p><strong>Sector:</strong> <%=employ.getSector()%></p> -->
			<p><strong>Funciones:</strong> <%=employ.getFunciones()%></p>
			<p><strong>Tipo de empleo:</strong> <%=employ.getTipoEmpleo()%></p>
			<!--<p><strong>Horario:</strong> <%=employ.getHorario()%></p>-->
			<p><strong>D&iacute;as laborales:</strong> <%=employ.getDiasLaborales()%></p>
			<c:if test="${not empty OfferQuestionForm.ofertaJB.descripcionesDiscapacidades and OfferQuestionForm.ofertaJB.descripcionesDiscapacidades!=''}">
				<p><strong>Tipo de discapacidad aceptada:</strong> <%=employ.getDescripcionesDiscapacidades()%></p>
			</c:if>
			<!--<p><strong>Causa que origina la oferta:</strong> <%=employ.getCausaOriginaOferta()%></p>-->
        </div>

        <br clear="all" />
        <div class="empresa_ofrece">
			<h3>La empresa ofrece</h3>
			<ul>
			<%if(!("").equals(employ.getEmpresaOfrece()) && !("Ninguna").equalsIgnoreCase(employ.getEmpresaOfrece())){ %>
				<li><strong>La empresa ofrece: </strong><%=employ.getEmpresaOfrece()%></li>
			<%}%>  	
            <li><strong>Sueldo:</strong> <%=Utils.formatMoney(employ.getSalario())%></li>
            <%if(!("").equals(employ.getPrestaciones()) && !("Ninguna").equalsIgnoreCase(employ.getPrestaciones())){ %>
				<li><strong>Prestaciones:</strong> <%=employ.getPrestaciones()%></li>
			<%}%>  	
			<li><strong>Jerarqu&iacute;a:</strong> <%=employ.getJerarquia()%></li>
            <li><strong>No. de plazas:</strong> <%=employ.getNumeroPlazas()%></li>
            </ul>
        </div>           
    </div>
    
	<p class="ligas_consulta">
			<c:if test="${not empty _user and OFERTA_FUNCION_PUBLICA != OfferQuestionForm.ofertaJB.fuenteId}">
				<c:choose>
					<c:when test="${OfferQuestionForm.ofertaJB.estatusOfertaCandidato eq requestScope.ESTATUS_SELECCIONADA or 
									OfferQuestionForm.ofertaJB.estatusOfertaCandidato eq requestScope.ESTATUS_POSTULADO    or
									OfferQuestionForm.ofertaJB.estatusOfertaCandidato eq requestScope.ESTATUS_VINCULADO}">
						<c:url var="urlMisOfertas" value="misofertas.do">
							<c:param name="method" value="init"/>
						</c:url>
						<a class="perfil" href="#">En mis ofertas de empleo</a>								
					</c:when>
					<c:when test="${OfferQuestionForm.ofertaJB.estatusOfertaCandidato eq '0'}">			
						<a class="agregar" onclick="doAjaxPost('addToMyOffers', '${OfferQuestionForm.idCandidato}','${OfferQuestionForm.idOfertaEmpleo}');" href="#">agregar a mis ofertas de empleo</a>
					</c:when>
				</c:choose>
			</c:if>
			
    </p>
    
	<c:choose>
		<c:when test="${not empty USUARIO_APP}">
		<%		
			if (OFERTA_FUNCION_PUBLICA != employ.getFuenteId() && offer.getPerfilJB()!=null) {
				PerfilJB vo = offer.getPerfilJB();
				TelefonoVO tel = vo.getPrincipal();			
		%>
			<div id="medidor">	
				<p><strong><% out.print(vo.getNombre() + " " + vo.getApellido1() + " " + vo.getApellido2()); %></strong> <br> tu porcentaje de compatibilidad <br /> con esta oferta de empleo es del:<br />
			   		<span id="porcentaje"><%=offer.getCompatibility()%> %</span>
				</p>
				<div class="botones_medidor">
				
				<%if (/*offer.getCompatibility() > compatibilityLimit && */ !offer.isPostulated()) {
				%>
                    <p>Oferta de empleo:<br />
                    	<strong><%=employ.getTituloOferta()%></strong>                            
                        <a href="javascript:postularOferta();" id="postularme">Postularme</a>
                    </p>		
		
					<div id="sendPost"></div>
					<!--<a href="#" id="agregar_miEspacio">Agregar a mi espacio personal</a>-->
					<form name="formPost" id="formPost" method="post" action="detalleoferta.do">
						<input type="hidden" name="method" id="method" value="addToMyOffers"/>
						<input type="hidden" name="idCandidato" id="idCandidato" value=""/>
						<input type="hidden" name="idOfertaEmpleo" id="idOfertaEmpleo" value=""/>
						<!--<input type="button" id="agregar_miEspacio" value="Agregar a mi espacio personal" class="boton" onclick="doAjaxPost('addToMyOffers', '<%=offer.getIdCandidato()%>','<%=offer.getIdOfertaEmpleo()%>');"/>-->
					</form>

					<form name="opost" id="opost" method="post" action="detalleoferta.do">
						<input type="hidden" name="method" id="method" value="offerPost"/>
						<%--
						<p class="enviarDatos"><!--<p style="text-align:center">-->
						<input type="submit" value="Enviar mis datos" class="boton" onclick="javascript:if(confirm('¿Está seguro que desea enviar sus datos para postularse a la oferta?'))return true; else return false;">
						<input type="button" id="cancelPost" value="Cancelar" class="boton"/>
						</p>
						--%>
					</form>

					<%--
							<div id="overPanel">
                   			<div id="cerrar" class="seiscientospx"><a href="#">Cerrar X</a></div>
                    		<!--<div id="detalleVacante">-->
							<div id="cuerpo_pop" class="mantenimiento">
								<h3><span>Postularse para</span><br /><%=employ.getTituloOferta()%></h3>
                            	<div class="ruta_pop">
									<a href="#"><%=employ.getEmpresaNombre()%></a>
									<a href="#"><%=employ.getUbicacion()%></a>
									<a href="#">$<%=employ.getSalario()%></a>
									<span><%=employ.getFuente()%></span>
                            	</div>
                            	<!--<div id="cuerpo_pop">-->
								<div>
									<p>Solicitamos <%=employ.getTituloOferta()%> <%=employ.getGradoEstudios()%> <%=employ.getEspecialidades()%> <%=employ.getFunciones()%>, <% if(employ.getEdadRequisito() == 2) out.print("Edad " + employ.getEdadMinima() + "-" + employ.getEdadMaxima() + " años,"); %> <%=employ.getIdiomas()%> <%=employ.getTipoEmpleo()%>, Número de plazas: <%=employ.getNumeroPlazas()%>, <%=employ.getMedioContacto()%> </p>
									<div id="datos_capturaNombre">
										<p>Nombre: <span><%=vo.getNombre()%></span></p>
										<p>Primer apellido: <span><%=vo.getApellido1()%></span></p>
										<p>Segundo apellido: <span><%=vo.getApellido2()%></span></p>
									</div>
									<div class="campo_pop derecha3">
										<div class="mail">
                                  			<p><strong>Correo electrónico:</strong><span> <%=vo.getCorreoElectronico()%></span></p>
                                    	</div>
										<div id="tel">
											<% if (null != tel && !"".equals(tel.getTelefono())) { %>
													<p><strong>Tel&eacute;fonos:</strong></p>
													<% if (tel.getIdTipoTelefono() == 5) out.print("<p>Fijo: <span>" + tel.getTelefono() + "</span></p>"); else out.println(""); %>
													<% if (tel.getIdTipoTelefono() == 1) out.print("<p>Celular: <span>" + tel.getTelefono() + "</span></p>"); else out.println(""); %>
													<% if (!"".equals(tel.getTelefono())) { %>
															<p>Hora de llamar: <span><%=vo.getHorarioLlamar()%></span></p>
													<% }else { %>
															<p></p>
													<% }
											   }
											%>
											<div id="dirPostular">
												<p><strong>Direcci&oacute;n:</strong></p>
												<p>Entidad federativa: <span><%=vo.getEntidad()%></span></p>
												<p>Delegaci&oacute;n/ municipio: <span><%=vo.getMunicipio()%></span></p>
												<p>Calle y n&uacute;mero: <span><% out.print(vo.getCalle() + " " + vo.getNumeroExterior() + " " + vo.getNumeroInterior()); %></span></p>
											</div>
											<p><strong>Si deseas modificar alg&uacute;n dato de los presentados en esta pantalla, ingresa a la secci&oacute;n Actualizar mis datos</strong></p>
										</div>
                                	</div>
                                	
							</div>
							<div class="logoLightBox">
								<img src="images/logo_bottomLightBox.gif" alt="Portal del Empleo" />
							</div>
						</div>
						<div class="bottom_lightBox2"></div>
					</div>
				--%>
			<%}%>

		        </div>
		    </div>
			
			<%} else {%>
			<%--
			<div id="observaciones">
				<div class="espacio_observaciones">
				< % = employ.getObservaciones()% >
				</div>
			</div>
			--%>
			<%}%>

		<br clear="all" />  
		
		<%if (null != request.getSession().getAttribute("_user")) {%>
		<div style="text-align: center; !important">
			Para mejorar el porcentaje de compatibilidad, te sugerimos <a class="perfil medidor2" href="<c:url value="/perfil.do?method=init"/>">Ampliar tu perfil laboral</a>
		</div>

		<%}%> 	

	  	</c:when>
	  	<c:otherwise>
	  		<!-- opción Postularme para usuarios que no están en sesión -->	  		
	  		<p style="text-align: center; !important">
	  		<c:if test="${OFERTA_FUNCION_PUBLICA != OfferQuestionForm.ofertaJB.fuenteId}">	  		
	  			<input type="button" id="postularmeNoSesion" value="Postularme" class="boton_naranja" onclick="javascript:postularmeNoSesion()"/>
	  		</c:if>
	  		</p>
	  	</c:otherwise>
     </c:choose>

<br/>
<br/>  
     
    <div id="preguntas_relacionadas">
		<%
			ArrayList<OfertaPreguntaVO> preguntas = (ArrayList<OfertaPreguntaVO>)offer.getOfertaPreguntasList();

			if (preguntas.size() > 0){%>
			<h3>Preguntas recientes acerca de la oferta de empleo</h3>
			<%}

			for (int i=0; i<preguntas.size(); i++) {
				OfertaPreguntaVO vo = preguntas.get(i);
		%>
				<p><strong><% out.println(Utils.capitalize(vo.getPregunta())); %></strong><br>
				<span>Reclutador: </span><% out.println(Utils.capitalize(vo.getRespuesta())); %>
				</p>
        <!--<p><strong>¿En que plataforma realizan sus desarrollos?</strong><br />
		   <span>Reclutador:</span> La empresa cuenta con una plataforma de desarrollo propia llamada WebBuilder</p>
        <p><strong>No conozco la plataforma, ¿La empresa cuenta con algún curso introductorio?</strong><br />
           <span>Reclutador:</span> Sí, contamos con personal capacitado para impartir los cursos</p>-->
		<%
			}
			
			if (null != request.getSession().getAttribute("_user")) {
		%>
        <div class="redacta">
			<div id="messageQuestion"></div>
			<form method="post" action="detalleoferta.do" name="formQuestion" id="formQuestion">
				<input type="hidden" name="idCandidato" id="idCandidato" value="<%=offer.getIdCandidato()%>"/>
				<input type="hidden" name="idOfertaEmpleo" id="idOfertaEmpleo" value="<%=offer.getIdOfertaEmpleo()%>"/>
				<h4>Preguntale a la empresa:</h4>
				<div class="campo_carta">
				<input type="hidden" name="method" id="method" value="addQuestion"/>
					<textarea name="pregunta" id="pregunta" cols="50" rows="7" 
					trim="true" onchange="return maximaLongitud(this,2000)" onKeyUp="return maximaLongitud(this,2000)"
	        			maxlength="2000"></textarea>
				</div>

                <div style="text-align: center;">
					<input type="button" id="sendQuestion" value="enviar pregunta" class="boton" onclick="doAjaxQuestion('addQuestion','<%=offer.getIdCandidato()%>','<%=offer.getIdOfertaEmpleo()%>');"/>
				</div>
			</form>
        </div>
		<%
			}
		%>
    </div>
</div>