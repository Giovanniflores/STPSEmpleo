<?xml version="1.0" encoding="ISO-8859-1" ?>
      <div class="col-sm-4 col-sm-push-8">
         <div id='cssmenu'>
         <button id="menuToggle" type="button" class="navbar-toggle">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
         	<h1 class="tituloMenu">Secciones de Servicio Nacional de Empleo</h1>
			<ul class="activeMenu menuContent">
			   <li class='has-sub'><a href='#'><span>Acerca del SNE <span class="caret"/></span></a>
			      <ul>
			         <li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/servicioNacionalDeEmpleo/acercaSne.jsp")%>">Acerca del SNE</a></li>
			         <li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/servicioNacionalDeEmpleo/antecedentesCreacion.jsp")%>">Antecedentes y creación</a></li>
			         <li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/servicioNacionalDeEmpleo/marcoJuridico.jsp")%>">Marco jurídico</a></li>
			         <li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/servicioNacionalDeEmpleo/cobertura.jsp")%>">Cobertura</a></li>
			         <li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/servicioNacionalDeEmpleo/ubicacion.jsp")%>">Ubicación</a></li>
			      </ul>
			   </li>
			   
			   <li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/servicioNacionalDeEmpleo/programasServiciosEmpleo.jsp")%>">Programas y servicios de empleo</a>
			   </li>
			      <li class='has-sub'><a href="#">Vinculación laboral <span class="caret"/></span></a> 
			         	<ul>
			         		<li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/servicioNacionalDeEmpleo/vinculacionLaboral.jsp")%>">Vinculación laboral</a></li>
			         		<li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/servicioNacionalDeEmpleo/bolsaTrabajo.jsp")%>">Bolsa de trabajo</a></li>
			         		<li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/servicioNacionalDeEmpleo/centrosIntermedicacionLaboral.jsp")%>">Centros de Intermediación Laboral</a></li>
			         		<!--li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/servicioNacionalDeEmpleo/kioscosConsultaPortalEmpleo.jsp")%>">Kioscos de consulta del Portal del Empleo</a></li-->
			         		<li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/servicioNacionalDeEmpleo/feriasEmpleo.jsp")%>">Ferias de Empleo</a></li>
			         		<li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/servicioNacionalDeEmpleo/abriendoEspacios.jsp")%>">Abriendo Espacios</a></li>
			         		<li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/servicioNacionalDeEmpleo/portalEmpleo.jsp")%>">Portal del Empleo</a></li>
			         		<!--li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/servicioNacionalDeEmpleo/servicioNacionalEmpleoTelefono.jsp")%>">Servicio Nacional de Empleo por Télefono</a></li-->
			         		<li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/servicioNacionalDeEmpleo/sistemaEstatalEmpleo.jsp")%>">Sistema Estatal de Empleo</a></li>
			         		<li class='has-sub'><a href="#">Talleres para buscadores de empleo <span class="caret"/></span></a>
					         	<ul>
					         		<li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/servicioNacionalDeEmpleo/talleresBuscadoresEmpleo.jsp")%>">Talleres para buscadores de empleo</a></li>
					         		<li class='has-sub'><a href="#">Talleres en línea <span class="caret"/></span></a>
							         	<ul>
							         		<li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/servicioNacionalDeEmpleo/talleresLinea.jsp")%>">Talleres en línea</a></li>
							         		<li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/servicioNacionalDeEmpleo/introduccionProcesoBusquedaEmpleo.jsp")%>">Introducción al proceso de búsqueda de empleo</a></li>
							         		<li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/servicioNacionalDeEmpleo/dondeBuscarEmpleo.jsp")%>">¿Dónde buscar empleo?</a></li>
							         		<!--li><a href="<%=request.getContextPath()%>/jsp/empleo/servicioNacionalDeEmpleo/comoSerElegido.jsp">¿Cómo ser elegido?</a></li>
							         		<li><a href="<%=request.getContextPath()%>/jsp/empleo/servicioNacionalDeEmpleo/instrumentosEvaluacion.jsp">Instrumentos de evaluación</a></li>
							         		<li><a href="<%=request.getContextPath()%>/jsp/empleo/servicioNacionalDeEmpleo/decisionFinal.jsp">Decisión final</a></li>
							         		<li><a href="<%=request.getContextPath()%>/jsp/empleo/servicioNacionalDeEmpleo/siYaEstasContratado.jsp">Si ya estás contratado</a></li-->
							         	</ul>
							         </li>
					         		<li class='has-sub'><a href="#">Talleres presenciales <span class="caret"/></span></a>
							         	<ul>
							         		<li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/servicioNacionalDeEmpleo/talleresPresenciales.jsp")%>">Talleres presenciales</a></li>
							         		<li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/servicioNacionalDeEmpleo/calendario.jsp")%>">Calendario</a></li>
							         		<li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/servicioNacionalDeEmpleo/sedes.jsp")%>">Sedes</a></li>
							         	</ul>
							         </li>
					         	
					         	</ul>
					         </li>
			         		<li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/servicioNacionalDeEmpleo/observatorioLaboral.jsp")%>">Observatorio Laboral</a></li>
			         	</ul>
			         </li>
			         <li class='has-sub'><a href="#">Programa de Apoyo al Empleo <span class="caret"/></span></a>
			         	<ul>
			         		<li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/servicioNacionalDeEmpleo/programasApoyoEmpleo.jsp")%>">Programa de Apoyo al Empleo</a></li>
			         		<li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/servicioNacionalDeEmpleo/fomentoAutoempleo.jsp")%>">Fomento al autoempleo</a></li>
			         		<li class='has-sub'><a href="#">Becas a la Capacitación para el Trabajo (Bécate) <span class="caret"/></span></a>
					         	<ul>
					         		<li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/servicioNacionalDeEmpleo/becasCapacitacionTrabajoBecate.jsp")%>">Becas a la Capacitación para el Trabajo (Bécate)</a></li>
					         		<li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/servicioNacionalDeEmpleo/capacitacionMixtaBecate.jsp")%>">Capacitación mixta</a></li>
					         		<li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/servicioNacionalDeEmpleo/capacitacionPracticaLaboralBecate.jsp")%>">Capacitación en la práctica laboral</a></li>
					         	    <li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/servicioNacionalDeEmpleo/capacitacionAutoempleoBecate.jsp")%>">Capacitación para el autoempleo</a></li>
					         	    <li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/servicioNacionalDeEmpleo/valesCapacitacionBecate.jsp")%>">Vales de capacitación</a></li>
					         	</ul>
					         </li>
			         	
			         	</ul>
			         </li>
			         
			         <li class='has-sub'><a href='#'><span>Movilidad Laboral Externa <span class="caret"/></span></a>
				     	<ul>
				        	<li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/servicioNacionalDeEmpleo/movilidadLaboralExterna.jsp")%>">Movilidad Laboral Externa</a></li>
				        	<li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/servicioNacionalDeEmpleo/programaTrabajadoresAgricolasTemporalesMexicoCanada.jsp")%>">Programa de Trabajadores Agrícolas Temporales México-Canadá (PTAT)</a></li>
				        	<!-- li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/candidatos/ofertas-EEUU-Canada/oferta-Montauk-NY-001.jsp")%>">Trabajadores en Preparación de Alimentos para Montauk, N.Y, EUA</a></li>
				        	<li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/candidatos/ofertas-EEUU-Canada/oferta-westbury-NY-001.jsp")%>">Jardinero paisajista para Westbury, N. Y., EUA</a></li>
				        	<li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/candidatos/ofertas-EEUU-Canada/oferta-Montauk-NY-003.jsp")%>">Mucama/Lavandería para Montauk, N.Y., EUA</a></li>
				        	<li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/candidatos/ofertas-Alemania/oferta-Alemania-001.jsp")%>">Ingeniero en Automatización para Alemania</a></li>
				        	<li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/candidatos/ofertas-Alemania/oferta-Alemania-002.jsp")%>">Ingeniero Eléctrico para Alemania</a></li>
				        	<li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/candidatos/ofertas-Alemania/oferta-Alemania-003.jsp")%>">Asistente Médico para Alemania</a></li>
				        	<li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/candidatos/ofertas-Alemania/oferta-Alemania-004.jsp")%>">Enfermeras en Cuidado de Adultos Mayores para Alemania</a></li>
				        	<li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/candidatos/ofertas-Alemania/oferta-Alemania-005.jsp")%>">Farmacólogo para Alemania</a></li>
				        	<li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/candidatos/ofertas-Alemania/oferta-Alemania-006.jsp")%>">Arquitectura de Software para Alemania</a></li>
				        	<li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/candidatos/ofertas-Alemania/oferta-Alemania-007.jsp")%>">Desarrollador de Software para Alemania</a></li>
				        	<li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/candidatos/ofertas-Alemania/oferta-Alemania-008.jsp")%>">Probador de Software para Alemania</a></li>
				        	<li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/candidatos/ofertas-Alemania/oferta-Alemania-009.jsp")%>">Desarrolladores de redes de interfaz y/o servidores para Alemania</a></li>
				        	<li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/candidatos/ofertas-Alemania/oferta-Alemania-010.jsp")%>">Enfermeras para Alemania</a></li>
				        	<li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/candidatos/ofertas-Alemania/oferta-Alemania-011.jsp")%>">Asistente de Radiología para Alemania</a></li>
				        	<li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/candidatos/ofertas-Alemania/oferta-Alemania-012.jsp")%>">Terapeuta Físico para Alemania</a></li>
				        	<li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/candidatos/ofertas-Alemania/oferta-Alemania-013.jsp")%>">Ingeniero de Proyecto para Alemania</a></li>
				        	<li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/candidatos/ofertas-Alemania/oferta-Alemania-014.jsp")%>">Ingeniero Industrial</a></li>
				        	<li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/candidatos/ofertas-Alemania/oferta-Alemania-015.jsp")%>">Ingeniero Mecánico</a></li>
				        	<li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/candidatos/ofertas-Alemania/oferta-Alemania-016.jsp")%>">Científicos de Datos / Analistas de Datos</a></li>
				        	<li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/candidatos/ofertas-Alemania/oferta-Alemania-017.jsp")%>">Desarrollador de Bases de Datos</a></li>
				        	<li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/candidatos/ofertas-Alemania/oferta-Alemania-018.jsp")%>">Ingeniero de Desarrollo en Pruebas</a></li> -->
				       </ul>
				  	</li>
			        <li class='has-sub'><a href='#'><span>Movilidad Laboral Interna <span class="caret"/></span></a>
				     	<ul>
				        	<li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/servicioNacionalDeEmpleo/movilidadLaboralInterna.jsp")%>">Movilidad Laboral Interna</a></li>
				        	<li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/servicioNacionalDeEmpleo/repatriadosTrabajando.jsp")%>">Repatriados trabajando</a></li>
				        	<!--li><a href="<%=request.getContextPath()%>/jsp/empleo/servicioNacionalDeEmpleo/sectorAgricola.jsp">Sector agrícola</a></li-->
					        <!--li><a href="<%=request.getContextPath()%>/jsp/empleo/servicioNacionalDeEmpleo/sectorIndustrialServicios.jsp">Sector industrial y de servicios</a></li-->
				        </ul>
				  	</li> 
			         
			         <li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/servicioNacionalDeEmpleo/programaEmpleoTemporal.jsp")%>">Programa de Empleo Temporal (PET)</a></li>
			        <li class='has-sub'><a href="#">Publicaciones <span class="caret"/></span></a>
			         	<ul>
			         		<li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/servicioNacionalDeEmpleo/publicaciones.jsp")%>">Publicaciones</a></li>
			         		<li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/servicioNacionalDeEmpleo/periodicoDeOfertasDeEmpleo.jsp")%>">Periódico de ofertas de empleo del SNE</a></li>
			         		<li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/servicioNacionalDeEmpleo/revistaInformativaDelSNE.jsp")%>">Revista informativa del SNE</a></li>
			         	</ul>
			         </li>
			   </li>
			   
			   
			   <li class='has-sub'><a href="#">Estadísticas laborales <span class="caret"/></span></a>
		         	<ul>
		         		<li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/servicioNacionalDeEmpleo/estadisticasLaborales.jsp")%>">Estadísticas laborales</a></li>
		         		<li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/servicioNacionalDeEmpleo/accionesVinculacionLaboral.jsp")%>">Acciones de vinculación laboral</a></li>
		         		<li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/servicioNacionalDeEmpleo/programaApoyoEmpleo.jsp")%>">Programa de Apoyo al Empleo (PAE)</a></li>
		         		<li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/servicioNacionalDeEmpleo/programasDeEmpleo.jsp")%>">Programas de empleo</a></li>
		         		<li><a href="http://publicaciones.empleo.gob.mx/estadisticas/Resumen_Global.xls">Resumen global</a></li>
		         	</ul>
		         </li>
			</ul>
		</div>
    </div>
    
    <div class="clearfix visible-xs"></div>