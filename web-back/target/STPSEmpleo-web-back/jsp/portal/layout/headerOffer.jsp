<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<% 
	String swb = (String)application.getAttribute("DOMAIN_PORTAL");
	String context = request.getContextPath() +"/";
%>
		<div id="wrapper">
	    	<div id="herramientas">
				<img src="<%=swb %>/work/models/empleo/jsp/cil/images/logo_STPS.gif" width="404" height="51" alt="Secretar&iacute;a del Trabajo y Previsi&oacute;n Social: Servicio Nacional de Empleo" />
          		<ul>
				     <li><a href="<%=swb %>/swb/empleo/Candidatos" >Mi espacio</a></li>
				     <li><a href="<%=swb %>/swb/empleo/contacto" >Contacto</a></li>
				     <li><a href="<%=swb %>/swb/empleo/Mapa_del_Portal" >Mapa del Portal</a></li>
				     <li><a href="<%=swb %>/swb/empleo/Sitios_relacionados" >Sitios relacionados</a></li>
				     <li><a href="<%=swb %>/swb/empleo/Necesitas_ayuda" >¿Necesitas ayuda?</a></li>
				</ul>
      		</div>
      		<div id="header">
      			<a href="<%=context %>ofertasRecientes.do?method=init" ><h1>Portal del Empleo : llama al 01 800 841 20 20</h1></a>
         		<div id="perfil_usuario_2012">
					<p>Regístrate <em> como:</em></p>
					<p>
						<a class="candidato" href="<c:url value="/registro_candidato.do"/>">Candidato</a>
						<a class="empresa" href="<c:url value="registroEmpresa.do?method=init"/>">Empresa</a>
						<br clear="all" />
					</p>
					<p>Si ya estás registrado, <a href="<%=swb %>/swb/empleo/Inicio_de_sesion">Inicia sesión</a></p>
            	</div>
      		</div>
     		<div id="espacio_buscador">
         		<div id="buscador" class="ac_2011 b">
               		<a class="busqueda_especifica" href="<%=context %>specificsearch.do?method=init" >Búsqueda específica</a>
         			<div class="swb-ilta" id="ilta_31">250,000</div>
               		<form name="ocp" id=ocp" action="<%=context %>ocupate.do" method="get">
 						<input type="hidden" name="method" value="init" />
 						<input type="hidden" name="searchQ" value="" />
 						<div class="que_empleo">
 							<label>
 							<p class="t_buscador">¿Qué empleo buscas?</p>
 							<p class="buscador_1"><input name="searchTopic" type="text" value="" lang="es"/></p>
							</label>
  							<p class="ejemplo">Puesto, carrera, oficio <a title="Uso del buscador" class="ayuda1" href="<%=swb %>/swb/empleo/Uso_del_Buscador">Ayuda</a></p> 
 						</div>
 						<div class="donde">
 							<label>
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
										    <option value="9">Distrito Federal</option>
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
							</label>
  							<p class="ejemplo">Entidad</p> 
 						</div>
 						<input type="button" name="bt_buscador" id="bt_buscador" onclick="employ();" value="Buscar">
					</form>
	    			<span class="numero_ofertas">ofertas de empleo</span>
         		</div>
       			<div id="ayuda_buscador" class="ac_2011"> 
         			<a class="ayuda" href="<%=swb %>/swb/empleo/Necesitas_ayuda" >¿Necesitas ayuda?</a>
					<a class="quejas" href="<c:url value="suggestion.do?method=init"/>" target="popUp" onClick="window.open(this.href, this.target, 'toolbar=no,directories=no,scrollbars=no,location=no,statusbar=no,menubar=no,resizable=yes,width=830,height=700'); return false;">Sugerencias</a>
       			</div>
			</div>
			<div id="cuerpo_int">
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
							<a href="/<%=swb %>es_mx/empleo/investigadores_del_mercado_laboral" class="swb-menumap-act">Estadísticas laborales</a>
						</li>
                    </ul>
        		</div>
        		<div id="contenido_principal" class="sinCol">
        			<div id="ruta_navegacion">Estás en: <a href="<%=context %>ofertasRecientes.do?method=init"  >Inicio</a> | Herramientas del sitio |  Ofertas Empleo&nbsp;&nbsp;

						<script type="text/javascript" src="http://s7.addthis.com/js/250/addthis_widget.js#username=xa-4bfafaee595d62bd" ></script>
        			</div>
					<ul class="redesSoc">
						<li><a target="_self" href="https://www.facebook.com/empleogobmx" class="fbk">Facebook</a></li>
						<li><a target="_self" href="https://twitter.com/empleogob_mx" class="twt">twitter</a></li>
					</ul>        			
        			
        			<div id="contenido" class="frames">  
						<br/>
						<br/>
	 					<h2>Detalle Oferta</h2>
						<div id="contenido_principal">
							<jsp:include page="/jsp/portal/monitoring/portalofferdetail.jsp"/>
						</div>
        			</div>      
    			</div>
        	</div>
        	<div id="banners_publicitarios"></div>
        	<div id="footer">
            	<span>Portal del Empleo: empleo.gob.mx</span>
            	<div id="links_footer">
                	<p>
                		<a href="<%=context %>ofertasRecientes.do?method=init" >Inicio</a>
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
               		<p><strong>Periférico Sur <abbr title="Número">No.</abbr> 4271, <abbr title="Colonia">Col.</abbr> Fuentes del Pedregal, Tlalpan 14149, Ciudad de México, Teléfono 01-800-841-20-20</strong></p>
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
						<p>
							<a href="#" class="barSesion" onclick="javascript:document.loginbarForm.submit();">Iniciar sesión</a>
						</p>
					</form>
					<div class="bannerCanal_princ">
            			<ul>
			            	<li><a class="textAumentar" href="#">Aumentar tamaño de texto</a></li>
			            	<li><a href="#" class="textNormal">Reestablecer tamaño de texto</a></li>
			            	<li><a href="#" class="textReducir">Disminuir tamaño de texto</a></li>
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