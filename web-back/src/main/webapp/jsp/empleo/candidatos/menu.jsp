<?xml version="1.0" encoding="ISO-8859-1" ?>
      <div class="col-sm-4 col-sm-push-8">
      
      
         <div id='cssmenu'>
         <button id="menuToggle" type="button" class="navbar-toggle">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
         	<h1 class="tituloMenu">Secciones de Candidatos</h1>
			<ul class="activeMenu menuContent">
			   <li><a name="socialMedia" href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/candidatos/registrate.jsp")%>">Regístrate y haz que las empresas te vean</a></li>
			   <li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/candidatos/descubreHerramientas.jsp")%>">Descubre las herramientas que tenemos para ti</a></li>
			   <li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/candidatos/conoceServiciosCandidato.jsp")%>">Conoce los servicios que el portal tiene para ti</a></li>
			   <li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/candidatos/introduccionBusquedaEmpleo.jsp")%>">Introducción a la búsqueda de empleo</a></li>
			   <li class='has-sub'><a href='#'><span>Habilidades para la Búsqueda de Empleo (HPBE) <span class="caret"/></span></a>
			      <ul>
			         <li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/candidatos/habilidadesBusquedaEmpleo.jsp")%>">Habilidades para la Búsqueda de Empleo (HPBE)</a></li>
			         <li class='has-sub'><a href='#'><span>Conoce <span class="caret"/></span></a>
			            <ul>
			                <li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/candidatos/conoce.jsp")%>"><span class="right-caret"></span> Conoce</a></li>
			                 <li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/candidatos/herramientasOfreceSne.jsp")%>"><span class="right-caret"></span> Las herramientas que te ofrece el SNE</a></li>
			                 <li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/candidatos/conoceDescubreHabilidadesCapacidades.jsp")%>"><span class="right-caret"></span> Conoce y descubre tus habilidades y capacidades</a></li>
			                 <li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/candidatos/identificaCaracteristicasMercadoTrabajo.jsp")%>"><span class="right-caret"></span> Identifica las características del mercado de trabajo</a></li>
			                 <li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/candidatos/defineObjetivoLaboral.jsp")%>"><span class="right-caret"></span> Define tu objetivo laboral</a></li>
			            </ul>
			         </li>
			         <li class='has-sub'><a href='#'><span>Postula <span class="caret"/></span></a>
			            <ul>
			                <li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/candidatos/postula.jsp")%>"><span class="right-caret"></span> Postula</a></li>
			                <li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/candidatos/comoDondeEncontrarVacantesOfertasEmpleo.jsp")%>"><span class="right-caret"></span> ¿Cómo y dónde encontrar vacantes y ofertas de empleo?</a></li>
			                <li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/candidatos/comoRedactarCurriculumVitae.jsp")%>"><span class="right-caret"></span> ¿Cómo redactar un currículum vitae?</a></li>
			                <li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/candidatos/cartaRecomendacion.jsp")%>"><span class="right-caret"></span> La carta de recomendación</a></li>
			                <li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/candidatos/comoSuperarEntrevistaTrabajo.jsp")%>"><span class="right-caret"></span> ¿Cómo superar la entrevista de trabajo?</a></li>
			            </ul>
			         </li>
			      </ul>
			   </li>
			   <li class='has-sub'><a href='#'><span>Alternativas de búsqueda de empleo <span class="caret"/></span></span></a>
			      <ul>
			         <!--  <li><a href="<--%=request.getContextPath()%>/jsp/empleo/candidatos/alternativasBusquedaEmpleo.jsp"><span class="right-caret"></span> Alternativas de búsqueda de empleo</a></li>-->
			         <li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/candidatos/bolsasEmpleoAsociadas.jsp")%>"><span class="right-caret"></span> Bolsas de empleo asociadas</a></li>
			         <li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/servicioNacionalDeEmpleo/periodicoDeOfertasDeEmpleo.jsp")%>"><span class="right-caret"></span> Periódico de ofertas de empleo del SNE</a></li>
			         <li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/candidatos/recibeOfertasViaSms.jsp")%>"><span class="right-caret"></span> Recibe ofertas vía SMS</a></li>
			      </ul>
			   </li>
			   <li class='has-sub'><a href='#'><span>Capacitación y becas <span class="caret"/></span></span></a>
			      <ul>
			         <li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/candidatos/capacitacionBecas.jsp")%>"><span class="right-caret"></span> Capacitación y becas</a></li>
			          <li class='has-sub'><a href="#"><span>Becas a la capacitación del SNE <span class="caret"/></span></span> </a>
			          	<ul>
				         <li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/candidatos/becasCapacitacionSne.jsp")%>"><span class="right-caret"></span> Becas a la capacitación del SNE</a></li>
				          <li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/candidatos/capacitacionMixta.jsp")%>"><span class="right-caret"></span> Capacitación mixta</a></li>
				          <li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/candidatos/capacitacionPracticaLaboral.jsp")%>"><span class="right-caret"></span> Capacitación en la práctica laboral</a></li>
				          <li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/candidatos/capacitacionAutoempleo.jsp")%>"><span class="right-caret"></span> Capacitación para el autoempleo</a></li>
				          <li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/candidatos/valesCapacitacion.jsp")%>"><span class="right-caret"></span> Vales de capacitación</a></li>
				        </ul>
			          
			          </li>
			     <%--  <li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/candidatos/becasManpowerTdc.jsp")%>"><span class="right-caret"></span> Becas Manpower TDC</a></li>  --%>
			          <li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/candidatos/capacitacionDistanciaTrabajadores.jsp")%>"><span class="right-caret"></span> Capacitación a distancia para trabajadores</a></li>
			          <li class='has-sub'><a href="#"><span>Certificación de competencias (CONOCER) <span class="caret"/></span></span></a>
			          	<ul>
				         <li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/candidatos/certificacionCompetenciasConocer.jsp")%>"><span class="right-caret"></span> Certificación de competencias (CONOCER)</a></li>
				          <li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/candidatos/certificacionCompetencias.jsp")%>"><span class="right-caret"></span> Certificación de Competencias</a></li>
				        </ul>
			          </li>
			      </ul>
			   </li>
			   <li class='has-sub'><a href='#'><span>Asesoría laboral <span class="caret"/></span></span></a>
			      <ul>
			         <li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/candidatos/asesoriaLaboral.jsp")%>"><span class="right-caret"></span> Asesoría laboral</a></li>
			         <li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/candidatos/serviciosAsesoriaLaboral.jsp")%>"><span class="right-caret"></span> Servicios</a></li>
			          <li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/candidatos/oficinasPROFEDET.jsp")%>"><span class="right-caret"></span> Oficinas de la PROFEDET</a></li>
			          <li class='has-sub'><a href="#"><span>Preguntas frecuentes <span class="caret"/></span></span></a>
			          	<ul>
				         <li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/candidatos/preguntasFrecuentes.jsp")%>"><span class="right-caret"></span> Preguntas frecuentes</a></li>
				         <li class='has-sub'><a href="#"><span>Al inicio de la vida laboral <span class="caret"/></span></span></a>
				          	<ul>
					         <li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/candidatos/alInicioVidaLaboral.jsp")%>"><span class="right-caret"></span> Al inicio de la vida laboral</a></li>
					         <li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/candidatos/aforeInicio.jsp")%>"><span class="right-caret"></span> AFORE</a></li>
					         <li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/candidatos/derechosLaboralesInicio.jsp")%>"><span class="right-caret"></span> Derechos laborales</a></li>
					         <li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/candidatos/imssInfonavitInicio.jsp")%>"><span class="right-caret"></span> IMSS e Infonavit</a></li>
					        </ul>
				         </li>
				          <li class='has-sub'><a href="#"><span>Durante la vida laboral <span class="caret"/></span></span></a>
				          	<ul>
					         <li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/candidatos/duranteVidaLaboral.jsp")%>"><span class="right-caret"></span> Durante la vida laboral</a></li>
					         <li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/candidatos/aforeDurante.jsp")%>"><span class="right-caret"></span> AFORE</a></li>
					         <li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/candidatos/derechosLaboralesDurante.jsp")%>"><span class="right-caret"></span> Derechos laborales</a></li>
					         <li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/candidatos/imssInfonavitDurante.jsp")%>"><span class="right-caret"></span> IMSS e Infonavit</a></li>
					        </ul>
				         </li>
				          <li class='has-sub'><a href="#"><span>Al término de la vida laboral <span class="caret"/></span></span></a>
				          	<ul>
					         <li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/candidatos/alTerminoVidaLaboral.jsp")%>"><span class="right-caret"></span> Al término de la vida laboral</a></li>
					         <li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/candidatos/aforeTermino.jsp")%>"><span class="right-caret"></span> AFORE</a></li>
					         <li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/candidatos/derechosLaboralesTermino.jsp")%>"><span class="right-caret"></span> Derechos laborales</a></li>
					         <li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/candidatos/imssInfonavitTermino.jsp")%>"><span class="right-caret"></span> IMSS e Infonavit</a></li>
					        </ul>
				         </li>
				        </ul>
			          </li>
			          <li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/candidatos/leyFederalTrabajo.jsp")%>"><span class="right-caret"></span> Ley Federal del Trabajo</a></li>
			          <li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/candidatos/glosarioTerminosLaborales.jsp")%>"><span class="right-caret"></span> Glosario de términos laborales</a></li>
			      </ul>
			   </li>
			</ul>
		</div>
    </div>
    
    <div class="clearfix visible-xs"></div>