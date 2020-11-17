<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<% 
	String swb = (String)application.getAttribute("DOMAIN_PORTAL");
	String context = request.getContextPath() +"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd"> 
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="es" lang="es"> 
	<head> 
		<link rel="P3Pv1" href="https://www.empleo.gob.mx/w3c/p3p.xml" /> 
		
		<meta name="description" content="Regístrate en nuestra Bolsa de trabajo y postula la oferta de empleo que más se apegue según tu currículum vitae sin costo fácil y sin intermediarios"/>
		<meta name="robots" content="index, follow" />
		
		<title>Portal del Empleo: Bolsa de Trabajo en México</title> 
		<link href="<%=swb %>/work/models/empleo/css/estilos_empleo.css" rel="stylesheet" type="text/css" />
		<link href="<%=swb %>/work/models/empleo/css/estilos_calendario.css" rel="stylesheet" type="text/css" />
		<link rel="shortcut icon" media="all" type="image/x-icon" href="<%=context %>/work/models/empleo/css/favicon.ico" /> 
		<link rel="alternate" type="application/rss+xml" title="Portal del Empleo" href="/RSS" /> 
		<script src="<%=swb %>/work/models/empleo/Template/1/10/images/SpryTabbedPanels.js" type="text/javascript" ></script>
		<script src="<%=swb %>/work/models/empleo/Template/1/10/images/basic_tabs.js" type="text/javascript" ></script> 
		<script src="<%=swb %>/work/models/empleo/css/SpryValidationSelect.js" type="text/javascript" ></script>
		<script type="text/javascript" > 
		    var djConfig = {
		        parseOnLoad: true,
		        isDebug: false
		    };
		</script>
 
		<script type="text/javascript" > 
		   function employ() {
		      document.ocp.searchPlace.value = document.ocp.searchPlace.value;
		      document.ocp.searchQ.value = document.ocp.searchTopic.value;
		      document.ocp.submit();
		   }
		</script> 
		<script type="text/javascript" > 
			function keySubmit(e) {
			    if (e.keyCode == 13) {
			    	document.loginForm.submit();
			    }
			}
			function keycolSubmit(e) {
			    if (e.keyCode == 13) {
			    	document.logincolForm.submit();
			    }
			}
			function keybarSubmit(e) {
			    if (e.keyCode == 13) {
			    	document.loginbarForm.submit();
			    }
			}
			 
			var userclean = true;
			var pswclean = true;
			var usercleancol = true;
			var pswcleancol = true;
			var cfmcleancol = true;
			var usercleanbar = true;
			var pswcleanbar = true;
			 
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
				if (numfield==3){
					if (usercleancol){
						field.value='';
						usercleancol = false;
					}		
				}
			 
				if (numfield==4){
					if (pswcleancol){
						field.value='';
						pswcleancol = false;
					}		
				}
				if (numfield==5){
					if (cfmcleancol){
						field.value='';
						cfmcleancol = false;
					}		
				}
				if (numfield==6){
					if (usercleanbar){
						field.value='';
						usercleanbar = false;
					}		
				}
			 
				if (numfield==7){
					if (pswcleanbar){
						field.value='';
						pswcleanbar = false;
					}		
				}
			}
		</script>

<!-- Nuevo código analytics multidominio 01/06/2017 -->
<script> 
	(function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
	(i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
	m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
	})(window,document,'script','//www.google-analytics.com/analytics.js','ga');
    ga('create', 'UA-26166631-1', 'auto', {'allowLinker': true});
	ga('require', 'displayfeatures', 'linker');
	ga('linker:autoLink', ['publicaciones.empleo.gob.mx'] );
	ga('send', 'pageview');
</script>

</head>
<body>
<script>
  window.fbAsyncInit = function() {
    FB.init({
      appId      : '308925772806125',
      xfbml      : true,
      version    : 'v2.7'
    });
  };

  (function(d, s, id){
     var js, fjs = d.getElementsByTagName(s)[0];
     if (d.getElementById(id)) {return;}
     js = d.createElement(s); js.id = id;
     js.src = "//connect.facebook.net/en_US/sdk.js";
     fjs.parentNode.insertBefore(js, fjs);
   }(document, 'script', 'facebook-jssdk'));
</script>
	<div id="wrapper">
    	<div id="herramientas">  
			<img src="<%=swb %>/work/models/empleo/jsp/cil/images/logo_STPS.gif" width="404" height="51" alt="Secretar&iacute;a del Trabajo y Previsi&oacute;n Social: Servicio Nacional de Empleo" />
        	<ul>
     			<li><a href="<%=context %>login.do?method=loginCandidato" >Mi espacio</a></li>
     			<li><a href="<%=swb %>/swb/empleo/contacto" >Contacto</a></li>
     			<li><a href="<%=swb %>/swb/empleo/Mapa_del_Portal" >Mapa del Portal</a></li>
     			<li><a href="<%=swb %>/swb/empleo/Sitios_relacionados" >Sitios relacionados</a></li>
     			<li><a href="<%=swb %>/swb/empleo/Necesitas_ayuda" >¿Necesitas ayuda?</a></li>
			</ul>
      	</div>
    	<div id="header"><h1>Portal del Empleo : llama al 01 800 841 20 20</h1>
			<div id="perfil_usuario_2012">
				<p>Regístrate <em> como:</em></p>
				<p>
					<a class="candidato" href="<c:url value="/registro_candidato.do"/>">Candidato</a>
					<a class="empresa" href="<%=context %>portalRegistroEmpresa.do?method=init">Empresa</a>
					<br clear="all" />
				</p>
				<p>Si ya estás registrado, <a href="<%=swb %>/swb/empleo/Inicio_de_sesion">Inicia sesión</a></p>
	    	</div>
		</div>
    <div id="espacio_buscador">
  		<div id="buscador" class="ac_2011 b">
			<a class="busqueda_especifica" href="<%=context %>specificsearch.do?method=init" >Búsqueda específica</a>
			<div class="swb-ilta" id="ilta_31">237,000</div>
			<form name="ocp" id=ocp" action="/es_mx/empleo/Ocupate" method="get">
 				<input type="hidden" name="method" value="init" />
 				<input type="hidden" name="searchQ" value="" />
 				<div class="que_empleo">
  					<p class="t_buscador">¿Qué empleo buscas?</p>
  					<p class="buscador_1"><input name="searchTopic" type="text" value="" /></p>
  					<p class="ejemplo">Puesto, carrera, oficio <a title="Uso del buscador" class="ayuda1" href="/swb/empleo/Uso_del_Buscador">Ayuda</a></p> 
 				</div>
	 			<div class="donde">
  					<p class="t_buscador">¿Dónde?</p>
					<ul id="combo">
						<li class="texarea">
  							<span id="spryselect1">
  								<select name="searchPlace" id="searchPlace">
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
  							</span>
  						</li>
					</ul>
  					<p class="ejemplo">Entidad</p> 
 				</div>
 				<input type="button" name="bt_buscador" id="bt_buscador" onclick="employ();" value="Buscar">
			</form>
			<span class="numero_ofertas">ofertas de empleo</span>
       </div>
       <div id="ayuda_buscador" class="ac_2011"> 
       	<a class="ayuda" href="<%=swb %>/swb/empleo/Necesitas_ayuda" >¿Necesitas ayuda?</a>
		<a class="quejas" href="<c:url value="/suggestion.do?method=init"/>" target="popUp" onClick="window.open(this.href, this.target, 'toolbar=no,directories=no,scrollbars=no,location=no,statusbar=no,menubar=no,resizable=yes,width=830,height=700'); return false;">Sugerencias</a>
       </div>
	</div>
	<div id="cuerpo" class="home">
    	<div id="navegacion">
        	<ul>
                <li>
					<a href="<%=swb %>/es_mx/empleo/servicio_nacional_de_empleo_" class="swb-menumap-act">Servicio Nacional de Empleo</a>
				</li>
				<li>
					<a href="<%=swb %>/es_mx/empleo/solicitantes" class="swb-menumap-act">En busca de empleo</a>
				</li>
				<li>
					<a href="<%=swb %>/es_mx/empleo/busco_capacitacion" class="swb-menumap-act">Capacitación y becas</a>
				</li>
				<li>
					<a href="<%=swb %>/es_mx/empleo/estudiantes" class="swb-menumap-act">Orientación profesional</a>
				</li>
				<li>
					<a href="<%=swb %>/es_mx/empleo/busco_asesoria_laboral" class="swb-menumap-act">Asesoría laboral</a>
				</li>
				<li>
					<a href="<%=swb %>/es_mx/empleo/investigadores_del_mercado_laboral" class="swb-menumap-act">Estadísticas laborales</a>
				</li>
            </ul>
    	</div>
        <div id="contenido_principal">
			<div id="log-inContextual">
 				<form id="loginForm" name="loginForm" action="<%=context %>loginhome.do?method=login" method="post" autocomplete="off"> 
					<input type="hidden" id="isHome" name="isHome" value="home" />  
    	 			<p class="centrar_op"> 
						<span><label class="radio" for="candidato">Candidato</label>
							<input type="radio" checked="checked" value="cand" name="login_page" id="candidato"/>
						</span>
						<span><label class="radio" for="empresa">Empresa</label>
							<input type="radio" value="emp" name="login_page" id="empresa"/>
						</span>
						</p>
        			<p>
        				<div class="log_indications">
        					<span>Escribe el correo con el que te registraste en este portal, despúes escribe la contraseña enviada a tu correo electrónico</span>
        				</div>
						<input type="text"  name="username" onfocus="clean(this, 1)" id="email" value="Escribe tu usuario"/>
						<input type="password"  onkeypress="keySubmit(event)"  onfocus="clean(this, 2)" id="password"  name="password" value="Contraseña" />
						<input type="submit" class="boton_naranja" value="Iniciar sesión" />
					</p>
        			<p id="recuperar-password"><a href="<%=context %>recupera_contrasena.do">¿Olvidaste tu usuario o contraseña?</a></p>
        		</form>
    		</div>
			<div id="bannerReg">
				<a class="swb-banner" href="<c:url value="/registro_candidato.do"/>" onclick="window.location.href='<%=swb %>/es_mx/empleo/home/_aid/728';return false;" title="Banner_registro">
					<img src="<%=swb %>/work/models/empleo/css/banner_registro.jpg" alt="Regístrate en el portal del empleo"/>
				</a>
				<p><a href="<c:url value="/registro_candidato.do"/>">¡Regístrate AQUÍ!</a></p>
			</div>
			<div id="contenido_principal">
				<form name="OfertasRecientesForm" id="OfertasRecientesForm" method="post" action="ofertasRecientes.do">
					<div id="ofertas">								
						<input type="hidden" name="method" id="method" value="ofertasRecientes"/>
						<input type="hidden" name="numRegistros" id="numRegistros" value="6"/>
    					<h3>Ofertas de empleo</h3>
        				<a href="javascript:doSubmit('ofertasRecientes');" <c:out value="${OFERTA_RECIENTE == 1?'class=ofertas_recientes':'class=ofertas_destacadas'}"/>>Ofertas recientes</a>
        				<a href="<%=context %>ofertasRecientes.do?method=ofertasDestacadas" <c:out value="${OFERTA_RECIENTE == 1?'class=ofertas_destacadas':'class=ofertas_recientes'}"/>>Ofertas destacadas </a>
        				<table cellpadding="0" cellspacing="0">
        					<tbody>    
   								<c:forEach var="ofertaReciente" items="${OfertasRecientesForm.ofertas}" varStatus="rowCounter">
									<tr <c:out value="${rowCounter.count % 2 == 0?'':'class=odd'}"/>>   	
    									<td>
    										<strong>
    											<a href="${context}offerdetail.do?method=init&id_oferta_empleo=${ofertaReciente.idOfertaEmpleo}">${ofertaReciente.tituloOferta}</a>
    										</strong>
		   								</td>
        								<td>${ofertaReciente.ubicacion}</td>
        								<td>${ofertaReciente.vigencia}</td>
    								</tr>
    							</c:forEach>    		
    						</tbody>
        				</table>         
        				<div class="borde_inferior">        	
        					<div style="font-family:Arial, Helvetica, sans-serif; font-size:11px; color:#000000; text-decoration: none; font-weight:bold; position: relative; left: 30px;">
        	 					<c:if test="${OFERTA_RECIENTE == 1}">
									<a href="${context}ofertasRecientes.do?method=totalOfertasRecientes">ver m&aacute;s</a>
								</c:if>
								<c:if test="${OFERTA_RECIENTE == 2}">					
									<a href="${context}ofertasRecientes.do?method=totalOfertasDestacadas">ver m&aacute;s</a>			
								</c:if>	
        					</div>          		
        				</div>
					</div>
				</form>
			</div>
            <div id="perfiles">
				<div class="TabbedPanels" id="TabbedPanels1">
					<ul class="TabbedPanelsTabGroup">
						<li class="TabbedPanelsTab">Empleadores</li>
						<li class="TabbedPanelsTab">Estudiantes</li>
						<li class="TabbedPanelsTab">Reci&#233;n egresados</li>
						<li class="TabbedPanelsTab">Adultos mayores</li>
						<li class="TabbedPanelsTab">Personas con discapacidad</li>
					</ul>
					<div class="TabbedPanelsContentGroup">
					<div class="TabbedPanelsContent TabbedPanelsContentVisible" style="display:block;">
 					<div style="overflow: hidden; display: block; width: 100%; height: 73px;">
  						<p style="padding:0 10px 0 10px">En este canal podr&aacute; encontrar herramientas para optimizar su desempeño como empleador y conocer&#225; nuevas pr&#225;cticas de implementaci&#243;n para elevar la calidad y productividad de la empresa.</p>
 					</div>
 					<div style="display: block; height: 90px; width: 603px; position: relative; z-index: 1; top: 0pt;">
  						<a class="ir_a_seccion" href="<%=swb %>/es_mx/empleo/Empleadores">ir a la secci&oacute;n</a>
  						<div class="perfil_relacionado">
							<a href="<%=swb %>/es_mx/empleo/ferias_de_empleo_OE">Ferias de empleo</a>
							<a href="<%=swb %>/es_mx/empleo/Proceso_de_Reclutamiento">El proceso de reclutamiento</a>
							<a href="<%=swb %>/es_mx/empleo/CONOCER_Formas_de_aumentar_la_competitividad">CONOCER. Formas de aumentar la competitividad</a>
  						</div>
 					</div>
				</div>
				<div class="TabbedPanelsContent TabbedPanelsContentVisible" style="display:block;">
 					<div style="overflow: hidden; display: block; width: 100%; height: 73px;">
  						<p style="padding:0 10px 0 10px">Este canal est&#225; dirigido a estudiantes de los niveles de educaci&oacute;n media, quienes desean obtener orientaci&#243;n y consejo para seleccionar una carrera profesional.</p>
 					</div>
 					<div style="display: block; height: 90px; width: 603px; position: relative; z-index: 1; top: 0pt;">
  						<a class="ir_a_seccion" href="<%=swb %>/es_mx/empleo/Estudiantes">ir a la secci&oacute;n</a>
  						<div class="perfil_relacionado">
							<a href="<%=swb %>/es_mx/empleo/Proyeccion_laboral">Proyecci&oacute;n laboral</a>
							<a href="<%=swb %>/es_mx/empleo/Elige_tu_carrera">Elige tu carrera</a>
							<a href="<%=swb %>/es_mx/empleo/Descubre_tus_habilidades">Descubre tus habilidades</a>
  						</div>
 					</div>
				</div>
				<div class="TabbedPanelsContent TabbedPanelsContentVisible" style="display:block;">
 					<div style="overflow: hidden; display: block; width: 100%; height: 73px;">
  						<p style="padding:0 10px 0 10px">En este canal se presenta un panorama general de las alternativas disponibles para ti ahora que has concluido tu carrera universitaria.</p>
 					</div>
 					<div style="display: block; height: 90px; width: 603px; position: relative; z-index: 1; top: 0pt;">
  						<a class="ir_a_seccion" href="<%=swb %>/es_mx/empleo/Recien_egresados">ir a la secci&oacute;n</a>
  						<div class="perfil_relacionado">
							<a href="<%=swb %>/es_mx/empleo/Introduccion_a_la_vida_laboral">Introducci&oacute;n a la vida laboral</a>
							<a href="<%=swb %>/es_mx/empleo/Consejos_para_el_trabajo">Consejos para el trabajo</a>
  						</div>
 					</div>
				</div>
				<div class="TabbedPanelsContent TabbedPanelsContentVisible" style="display:block;">
 					<div style="overflow: hidden; display: block; width: 100%; height: 73px;">
  						<p style="padding:0 10px 0 10px">Este canal tiene la funci&oacute;n de exponerte alternativas de ocupaci&#243;n y capacitaci&#243;n para que contin&#250;es aportando a la productividad del pa&#237;s con tus conocimientos y habilidades.</p>
 					</div>
 					<div style="display: block; height: 90px; width: 603px; position: relative; z-index: 1; top: 0pt;">
  						<a class="ir_a_seccion" href="<%=swb %>/es_mx/empleo/Adultos_Mayores">ir a la secci&oacute;n</a>
  						<div class="perfil_relacionado">
							<a href="<%=swb %>/es_mx/empleo/Informacion_y_consejos_para_iniciar_un_negocio">Consejos para iniciar un negocio</a>
							<a href="<%=swb %>/es_mx/empleo/Plan_de_negocios">Plan de negocios</a>
							<a href="<%=swb %>/es_mx/empleo/Microempresas">Microempresas</a>
  						</div>
 					</div>
				</div>
				<div class="TabbedPanelsContent TabbedPanelsContentVisible" style="display:block;">
 					<div style="overflow: hidden; display: block; width: 100%; height: 73px;">
  						<p style="padding:0 10px 0 10px">En esta secci&oacute;n podr&aacute;s consultar informaci&oacute;n relacionada con las diferentes opciones de trabajo que el Servicio Nacional de Empleo tiene para ti. 
															Adem&aacute;s, ponemos  a disposici&oacute;n de patrones y trabajadores informaci&#243;n general relacionada con accesibilidad en el lugar de trabajo y  herramientas para realizar actividades laborales.
						</p>
 					</div>
 					<div style="display: block; height: 90px; width: 603px; position: relative; z-index: 1; top: 0pt;">
  						<a class="ir_a_seccion" href="<%=swb %>/es_mx/empleo/Personas_con_discapacidad">ir a la secci&oacute;n</a>
  						<div class="perfil_relacionado">
							<a href="<%=swb %>/es_mx/empleo/Accesibildad_para_ti">Accesibilidad</a>
							<a href="<%=swb %>/es_mx/empleo/Tipos_de_discapacidad_y_dispositivos_empleados_para_su_tratamiento">Informaci&#243;n general sobre discapacidad</a>
							<a href="<%=swb %>/es_mx/empleo/Alternativas_de_empleo_segun_tu_discapacidad">Alternativas de empleo para personas con discapacidad</a>
  						</div>
 					</div>
				</div>
			</div>
		</div>
	</div>
	<div id="asesoria">
		<div id="caja">
			<div class="busco_capacitacion">
				<h3>Capacitación y becas</h3>
 					<ul class="contenido_caja">
						<li><a href="<%=swb %>/es_mx/empleo/becas_cap">Becas a la capacitación del SNE</a></li>
						<li><a href="<%=swb %>/es_mx/empleo/cursos_de_formacion_para_el_trabajo">Cursos de formación para el trabajo de la SEP</a></li>
						<li><a href="<%=swb %>/es_mx/empleo/becas_manpower_tdc">Becas Manpower TDC</a></li>
						<li><a href="<%=swb %>/es_mx/empleo/credito_fonacot_para_formacion_y_capacitacion">Crédito FONACOT para la capacitación y formación educativa</a></li>
						<li><a href="<%=swb %>/es_mx/empleo/CONOCER_general">CONOCER</a></li>
 					</ul>
			</div>
			<ul class="nav_caja">
				<li><a href="javascript:postHtml('<%=swb %>/es_mx/empleo/home/_rid/54/_mto/3/_mod/index?tpc=servicio_nacional_de_empleo_','caja')" title="ir a Servicio Nacional de Empleo">servicio_nacional_de_empleo_</a></li>
				<li><a href="javascript:postHtml('<%=swb %>/es_mx/empleo/home/_rid/54/_mto/3/_mod/index?tpc=solicitantes','caja')" title="ir a En busca de empleo">solicitantes</a></li>
				<li class="seleccionado">busco_capacitacion</li>
				<li><a href="javascript:postHtml('<%=swb %>/es_mx/empleo/home/_rid/54/_mto/3/_mod/index?tpc=estudiantes','caja')" title="ir a Orientación profesional">estudiantes</a></li>
				<li><a href="javascript:postHtml('<%=swb %>/es_mx/empleo/home/_rid/54/_mto/3/_mod/index?tpc=busco_asesoria_laboral','caja')" title="ir a Asesoría laboral">busco_asesoria_laboral</a></li>
				<li><a href="javascript:postHtml('<%=swb %>/es_mx/empleo/home/_rid/54/_mto/3/_mod/index?tpc=investigadores_del_mercado_laboral','caja')" title="ir a Estadísticas laborales">investigadores_del_mercado_laboral</a></li>
			</ul>
			<a href="<%=swb %>/es_mx/empleo/busco_capacitacion" class="ver_mas_1" >Ver m&aacute;s</a>
		</div>
	</div>
    <div id="testimonios">
    	<h3>Testimonios de éxito</h3>
        <div id="texto_testimonio">                 
        	<p>"Me gusta el servicio proporcionado, espero me resulte eficiente." <span><strong>DANIEL PEREZ MORUA</strong></span></p>
 	     </div>
    </div>
    <div id="siguenos">
    	<h4>Síguenos</h4>
			<span class="twitter"><a href="http://twitter.com/empleogob_mx">Twitter</a></span>
			<!-- <span class="rss"><a href="<%=swb %>/swb/empleo/Rss" >RSS</a></span> -->
    </div>
    <style type="text/css"> 
		.link_encuestas {
			height: 154px;
		}
		.position_l {
			position: relative;
			top: -7px;
		}
		.parrafo_l {
			left:263px;
			position: absolute;
			top: 20px;
		}
		.decorar:hover {
			text-decoration: underline;
		}
		.decorar_l {
		    color: #FF6600;
		    display: block;
		    font-size: 40px;
		    font-weight: bold;
		    margin: 0 0 3px;
		    padding: 0 0 0 50px;
		}
		.decorar_lb {
		    color: #292929;
		    font-size: 19px;
		    font-weight: bold;
		    letter-spacing: 0.02em;
		}
		.link_l {
		    margin: 20px 0;
		    text-align: right;
		}
		.link_l span {
		    background: url("<%=swb %>/work/models/empleo/images/bg_boton_encuesta.jpg") repeat-x scroll 0 0 #FF6600;
		    border: 1px solid #EE6000;
		    border-radius: 5px 5px 5px 5px;
		    color: #FFFFFF;
		    cursor: pointer;
		    letter-spacing: 0.03em;
		    padding: 3px 30px;
		}
		.link_l span:hover {
			background: url("<%=swb %>/work/models/empleo/images/bg_bt_naranja_ov.jpg") #f57c03 repeat-x;	
		}
	</style>	
	<div class="link_encuestas" style="height: 154px;">
		<div class="position_l"><a href="<%=context %>pollcomment.do?method=init" target="popUp" onclick="window.open(this.href,this.target,'toolbar=no,directories=no,scrollbars=no,location=no,statusbar=no,menubar=no,resizable=yes,width=472,height=583'); return false;" >
			<img src="<%=swb %>/work/models/empleo/images/banner_encuesta.jpg" /></a>
			<a href="<%=context %>pollcomment.do?method=init" target="popUp" onclick="window.open(this.href,this.target,'toolbar=no,directories=no,scrollbars=no,location=no,statusbar=no,menubar=no,resizable=yes,width=472,height=583'); return false;" class="parrafo_l" >
				<p class="decorar">
					<span class="decorar_l">Encuesta</span>
					<span class="decorar_lb">Ayúdanos a mejorar nuestro portal</span>
				</p>
				<p class="link_l">
					<span>Participa</span>
				</p>			
			</a>
	     </div>
	</div>
</div>
<div id="columna_articulos">
	<div id="registroLat">
        <h2><strong>Registro</strong> de candidato</h2>
        <form autocomplete="off" method="get" action="<c:url value="/registro_candidato.do"/>" name="logincolForm" id="logincolForm">
	        <p>
	            <label for="usuario"><span class="uno">1</span> Escribe tu correo electrónico o un nombre de usuario de 8 a 12 caracteres</label>
	            <input type="text" onfocus="clean(this, 3)" name="usuario" id="usuario" value="">
	        </p>
        
        	<p>
            	<label for="password" class="pass"><span class="dos">2</span> Escribe y confirma tu contraseña <br>de  8 a 12 caracteres</label>
				<input type="password" value="Contraseña" onfocus="clean(this, 4)" id="contrasena" name="contrasena">
				<br>
				<input type="password" onkeypress="keybarSubmit(event)" onfocus="clean(this, 5)" value="Contraseña" id="confirmacion" name="confirmacion">
        	</p>
        	<p></p>
			<p>
	        	<input type="submit" value="Regístrate">
	        </p>
        </form>
    </div>
    <div id="video">
    	<h4>En video</h4>
    	<object id="video_235" type="application/x-shockwave-flash" data="/swbadmin/player_flv_maxi.swf" width="274" height="210" >
			 <param name="movie" value="/swbadmin/player_flv_maxi.swf" />
			 <param name="allowFullScreen" value="true" />
			 <param name="wmode" value="transparent" />
			 <param name="FlashVars" value="flv=<%=swb %>/work/models/empleo/Resource/235/ferias_3.flv&amp;width=274&amp;height=210&amp;showstop=1&amp;showvolume=1&amp;showtime=1
			&amp;startimage=/work/models/empleo/Resource/235/fndvideo2a.png
			&amp;showfullscreen=1&amp;bgcolor1=1c1c1c&amp;bgcolor2=a0a0a0&amp;playercolor=ff7423&amp;buttoncolor=ffffff&amp;buttonovercolor=da3800&amp;slidercolor1=ffffff" />
		</object>
 	</div>
    <div id="noticias">
    	<h4>Ent&eacute;rate</h4>
		<div id="carrusel">
    		<div  class="carrusel_transparent">
        		<p>
                    <span class="atras"><a href="javascript:changeImageBack()" onclick="clearTimeout(timerID);" >anterior</a></span>
          			<span class="num">
                		<a href="javascript:changeIndex(0)" onclick="clearTimeout(timerID);">1</a>
              			<a href="javascript:changeIndex(1)" onclick="clearTimeout(timerID);">2</a>
                		<a href="javascript:changeIndex(2)" onclick="clearTimeout(timerID);">3</a>
                		<a href="javascript:changeIndex(3)" onclick="clearTimeout(timerID);">4</a>
          			</span>
                    <span class="adelante"><a href="javascript:changeImageNext()" onclick="clearTimeout(timerID);" >siguiente</a></span>
        		</p>
        		<p class="noticia"><a id="urlEnterate" href="<%=swb %>/es_mx/empleo/Personas_con_discapacidad" >Ir a la noticia</a></p>
    		</div>
    		<div id="imgEnterate" class="carrusel_fotos"></div>
	    	<h5 id="titleEnterate">Accesibilidad para personas con discapacidad</h5>
	    	<p id="descriptionEnterate">Participa en las Ferias de Empleo, una opción más p...</p>
		</div>
		<span class="borde_carrusel">&nbsp;</span>
		<a href="<%=swb %>/es_mx/empleo/CONOCER_general" class="link_conocer" target="_self" ><span>Certificación <br /> laboral</span> <br />para trabajadores <br />y empresarios</a>
		<div class="tips">
			<a href="<%=swb %>/swb/empleo/Tips_para_conseguir_empleo" ><span>Tips</span> para conseguir empleo</a>
		</div>
		<div  class="foro">
			<a href="<%=swb %>/swb/empleo/Foro" ><span>Foro</span> del empleo</a></div>
	  	</div>
	    <div id="destacados">
	    	<h4>Notas destacadas</h4>
	             <table id="notas_destacadas">
	             	<tr class="odd">
	 					<td>
							  <p><strong>Recomienda Adecco cuidar uso de datos personales en b&uacute;squeda de empleo</strong><br />Ante las nuevas modalidades de b&uacute;squeda de empleo v&iacute;a internet y la incidencia del mal uso de datos personales</p>
							  <a href="/es_mx/empleo/Noticias?uri=http%3A%2F%2Fwww.empleo.swb%23Resource%3A99" class="ver_mas_2" >leer más</a>
	 					</td>
					</tr>
					<tr>
						 <td>
						  	<p><strong>Tres tendencias de contrataci&oacute;n para 2012</strong><br />La competencia por una vacante obliga a ser m&aacute;s "agresivo" al aplicar por el puesto, dicen expertos; demostrar que das resultados r&aacute;pidos y ambicionas crecer te vuelve un candidato m&aacute;s atractivo</p>
						  	<a href="<%=swb %>/es_mx/empleo/Noticias?uri=https://www.empleo.swb%23Resource%3A98" class="ver_mas_2" >leer más</a>
						 </td>
					</tr>
	             </table>
	             <a href="<%=swb %>/es_mx/empleo/Noticias" id="noticias_anteriores">Noticias anteriores</a>
	    </div>        
		<div class="container_calendario">
	    	<h4>Eventos</h4>
			<div id="calendario_eventos">
	 			<div id="mes">
	  				<h3>Julio 2012</h3>
	 					<a id="mes_siguiente" href="javascript:postHtml('<%=swb %>/en_mx/empleo/home/_rid/57/_mto/3/_mod/roll?m=7','calendario_eventos')" >mes siguiente</a>
	 			</div>
			<div>
				<div class="range">
					<span class="dia_calendario"><span class="cap">jul</span> 31</span>
				</div>
				<p><a href="http://bit.ly/MmTApP">FERIA REGIONAL DE EMPLEO ECATEPEC 2012</a></p>
				<p>31 de <span class="cap">julio</span> de 2012</p>
				<p>De 09:00 a 16:00 Hrs.</p>
	 		</div>
		</div>
		<div class="bottom_calendario">
	 		<a style="float: right; margin: 10px 20px 0pt 0pt;" name="a_57" href="<%=swb %>/en_mx/empleo/Eventos" class="links">ver todos</a>
		</div>
	</div>
  </div>
</div>
<div id="banners_publicitarios">
	<ul>
		<li>
			<a class="swb-banner" href="http://www.presidencia.gob.mx/vivirmejor/vivirmejor.html" onclick="window.location.href='/es_mx/empleo/home/_aid/i1_29';return false;" title="Vivir mejor">
				<img src="<%=swb %>/work/models/empleo/Resource/i1_29/banner_publicitario1.png" alt="Vivir Mejor" width="55px" height="65px"/>
			</a>
		</li>
		<li>
			<a class="swb-banner" href="http://www.stps.gob.mx" onclick="window.location.href='/es_mx/empleo/home/_aid/i1_1441';return false;" title="stps">
				<img src="<%=swb %>/work/models/empleo/Resource/i1_1441/banner_publicitario2.gif" alt="Secretaría del Trabajo y Previsión Social" width="65px" height="45px"/>
			</a>
		</li>
		<li>
			<a class="swb-banner" href="/swb/empleo/servicio_nacional_de_empleo_" onclick="window.location.href='/es_mx/empleo/home/_aid/i1_1443';return false;" title="SNE">
				<img src="<%=swb %>/work/models/empleo/Resource/i1_1443/banner_publicitario3.png" alt="Servicio Nacional de Empleo" width="92px" height="45px"/>
			</a>
		</li>
		<li>
			<a class="swb-banner" href="http://www.presidencia.gob.mx" onclick="window.location.href='/es_mx/empleo/home/_aid/i1_27';return false;" title="Presidencia">
				<img src="<%=swb %>/work/models/empleo/Resource/i1_27/banner_publicitario4.gif" alt="Presidencia de la república" width="103px" height="44px"/>
			</a>		
		</li>	
		<li>
			<a class="swb-banner" href="http://www.gob.mx" onclick="window.location.href='/es_mx/empleo/home/_aid/i1_28';return false;" title="GobMx">
				<img src="<%=swb %>/work/models/empleo/Resource/i1_28/banner_publicitario5.jpg" alt="Gobierno de México en Línea" width="103px" height="43px"/>
			</a>		
		</li>	
	</ul>

</div>
<div id="footer">
	<span>Portal del Empleo: empleo.gob.mx</span>
    <div id="links_footer">
    	<p>
    		<a href="#" >Inicio</a>
    		<a href="<%=swb %>/swb/empleo/contacto" >Contacto</a>
			<a href="<%=swb %>/swb/empleo/Mapa_del_Portal" >Mapa del Portal</a>
			<a href="<%=swb %>/swb/empleo/Sitios_relacionados" >Sitios relacionados</a>
			<a href="<%=swb %>/swb/empleo/Necesitas_ayuda" >¿Necesitas ayuda?</a>
			<a href="<%=swb %>/swb/empleo/condiciones_de_uso" >Condiciones de uso</a>
		</p>
    	<div id="navegacion_bottom">
        	<ul>
                <li>
					<a href="<%=swb %>/es_mx/empleo/servicio_nacional_de_empleo_" class="swb-menumap-act">Servicio Nacional de Empleo</a>
				</li>
				<li>
					<a href="<%=swb %>/es_mx/empleo/solicitantes" class="swb-menumap-act">En busca de empleo</a>
				</li>
				<li>
					<a href="<%=swb %>/es_mx/empleo/busco_capacitacion" class="swb-menumap-act">Capacitación y becas</a>
				</li>
				<li>
					<a href="<%=swb %>/es_mx/empleo/estudiantes" class="swb-menumap-act">Orientación profesional</a>
				</li>
				<li>
					<a href="<%=swb %>/es_mx/empleo/busco_asesoria_laboral" class="swb-menumap-act">Asesoría laboral</a>
				</li>
				<li>
					<a href="<%=swb %>/es_mx/empleo/investigadores_del_mercado_laboral" class="swb-menumap-act">Estadísticas laborales</a>
				</li>
			</ul>
        </div>
	</div>
    <div id="direccion">
    	<p><strong>Periférico Sur <abbr title="Número">No.</abbr> 4271, <abbr title="Colonia">Col.</abbr> Fuentes del Pedregal, Tlalpan 14149, México, <acronym title="Distrito Federal">D.F.</acronym> Teléfono 01-800-841-20-20</strong></p>
    </div>
</div>
</div>
<div id="bar">
	<div id="barHome">
		<div id="barCenter" style="display: block;">
			<form id="loginbarForm" name="loginbarForm" action="<%=context %>loginhome.do?method=login" method="post" autocomplete="off">
				<input type="hidden" id="isHome" name="isHome" value="home" />
				<input type="hidden" id="login_page" name="login_page" value="cand"/>
				<p class="barLogin">
					<input type="text" onfocus="clean(this, 6)" name="username" id="login" value="Usuario candidato" style="font-size: 12px;">
				</p>
				<p class="barLogin">
					<input type="password" onkeypress="keybarSubmit(event)" onfocus="clean(this, 7)" value="Contraseña" name="password" id="pass">
				</p>
				<p><a href="#" class="barSesion" onclick="javascript:document.loginbarForm.submit();">Iniciar sesión</a></p>
			</form>
			<div class="bannerCanal_princ">
	            <ul>
		            <li><a class="textAumentar" href="#">Aumentar tamaño de texto</a></li>
		            <li><a href="#" class="textNormal">Reestablecer tamaño de texto</a></li>
		            <li><a href="#"  class="textReducir">Disminuir tamaño de texto</a></li>
		            <li><a class="iconPrint" href="javascript:print()">Imprimir</a></li>
		            <li><a class="iconRecomend" onclick="openRecommendationModal('recommendi1_55','#000000', 80);void(0);" href="#">Recomendar</a></li>
	            </ul>
			</div>
   			<p><a href="<%=swb %>/swb/empleo/Necesitas_ayuda" class="barFaq">¿Necesitas ayuda?</a></p>
			<form action="<%=swb %>/swb/empleo/buscador" method="get" id="formaq" name="formaq">
    			<p class="barLogin">
   					<input type="text" onfocus="this.value = '';" id="buscar" value="¿Qué buscas?" name="q">
				</p>
   				<p class="icoSearch">
					<input type="submit" id="bt_buscar" value="" name="q2">
   				</p>
			</form>
	    	</div>
		</div>
	</div>
		<script type="text/javascript" > 
			var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1");
		</script>

		<script type="text/javascript" > 
			var spryselect1 = new Spry.Widget.ValidationSelect("spryselect1");
		</script>
	</body>
</html>