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
			         <li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/servicioNacionalDeEmpleo/antecedentesCreacion.jsp")%>">Antecedentes y creaci�n</a></li>
			         <li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/servicioNacionalDeEmpleo/marcoJuridico.jsp")%>">Marco jur�dico</a></li>
			         <li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/servicioNacionalDeEmpleo/cobertura.jsp")%>">Cobertura</a></li>
			         <li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/servicioNacionalDeEmpleo/ubicacion.jsp")%>">Ubicaci�n</a></li>
			      </ul>
			   </li>
			   
			   <li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/servicioNacionalDeEmpleo/programasServiciosEmpleo.jsp")%>">Programas y servicios de empleo</a>
			   </li>
			      <li class='has-sub'><a href="#">Vinculaci�n laboral <span class="caret"/></span></a> 
			         	<ul>
			         		<li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/servicioNacionalDeEmpleo/vinculacionLaboral.jsp")%>">Vinculaci�n laboral</a></li>
			         		<li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/servicioNacionalDeEmpleo/bolsaTrabajo.jsp")%>">Bolsa de trabajo</a></li>
			         		<li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/servicioNacionalDeEmpleo/centrosIntermedicacionLaboral.jsp")%>">Centros de Intermediaci�n Laboral</a></li>
			         		<!--li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/servicioNacionalDeEmpleo/kioscosConsultaPortalEmpleo.jsp")%>">Kioscos de consulta del Portal del Empleo</a></li-->
			         		<li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/servicioNacionalDeEmpleo/feriasEmpleo.jsp")%>">Ferias de Empleo</a></li>
			         		<li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/servicioNacionalDeEmpleo/abriendoEspacios.jsp")%>">Abriendo Espacios</a></li>
			         		<li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/servicioNacionalDeEmpleo/portalEmpleo.jsp")%>">Portal del Empleo</a></li>
			         		<!--li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/servicioNacionalDeEmpleo/servicioNacionalEmpleoTelefono.jsp")%>">Servicio Nacional de Empleo por T�lefono</a></li-->
			         		<li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/servicioNacionalDeEmpleo/sistemaEstatalEmpleo.jsp")%>">Sistema Estatal de Empleo</a></li>
			         		<li class='has-sub'><a href="#">Talleres para buscadores de empleo <span class="caret"/></span></a>
					         	<ul>
					         		<li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/servicioNacionalDeEmpleo/talleresBuscadoresEmpleo.jsp")%>">Talleres para buscadores de empleo</a></li>
					         		<li class='has-sub'><a href="#">Talleres en l�nea <span class="caret"/></span></a>
							         	<ul>
							         		<li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/servicioNacionalDeEmpleo/talleresLinea.jsp")%>">Talleres en l�nea</a></li>
							         		<li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/servicioNacionalDeEmpleo/introduccionProcesoBusquedaEmpleo.jsp")%>">Introducci�n al proceso de b�squeda de empleo</a></li>
							         		<li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/servicioNacionalDeEmpleo/dondeBuscarEmpleo.jsp")%>">�D�nde buscar empleo?</a></li>
							         		<!--li><a href="<%=request.getContextPath()%>/jsp/empleo/servicioNacionalDeEmpleo/comoSerElegido.jsp">�C�mo ser elegido?</a></li>
							         		<li><a href="<%=request.getContextPath()%>/jsp/empleo/servicioNacionalDeEmpleo/instrumentosEvaluacion.jsp">Instrumentos de evaluaci�n</a></li>
							         		<li><a href="<%=request.getContextPath()%>/jsp/empleo/servicioNacionalDeEmpleo/decisionFinal.jsp">Decisi�n final</a></li>
							         		<li><a href="<%=request.getContextPath()%>/jsp/empleo/servicioNacionalDeEmpleo/siYaEstasContratado.jsp">Si ya est�s contratado</a></li-->
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
			         		<li class='has-sub'><a href="#">Becas a la Capacitaci�n para el Trabajo (B�cate) <span class="caret"/></span></a>
					         	<ul>
					         		<li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/servicioNacionalDeEmpleo/becasCapacitacionTrabajoBecate.jsp")%>">Becas a la Capacitaci�n para el Trabajo (B�cate)</a></li>
					         		<li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/servicioNacionalDeEmpleo/capacitacionMixtaBecate.jsp")%>">Capacitaci�n mixta</a></li>
					         		<li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/servicioNacionalDeEmpleo/capacitacionPracticaLaboralBecate.jsp")%>">Capacitaci�n en la pr�ctica laboral</a></li>
					         	    <li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/servicioNacionalDeEmpleo/capacitacionAutoempleoBecate.jsp")%>">Capacitaci�n para el autoempleo</a></li>
					         	    <li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/servicioNacionalDeEmpleo/valesCapacitacionBecate.jsp")%>">Vales de capacitaci�n</a></li>
					         	</ul>
					         </li>
			         	
			         	</ul>
			         </li>
			         
			         <li class='has-sub'><a href='#'><span>Movilidad Laboral Externa <span class="caret"/></span></a>
				     	<ul>
				        	<li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/servicioNacionalDeEmpleo/movilidadLaboralExterna.jsp")%>">Movilidad Laboral Externa</a></li>
				        	<li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/servicioNacionalDeEmpleo/programaTrabajadoresAgricolasTemporalesMexicoCanada.jsp")%>">Programa de Trabajadores Agr�colas Temporales M�xico-Canad� (PTAT)</a></li>
				        	<!-- li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/candidatos/ofertas-EEUU-Canada/oferta-Montauk-NY-001.jsp")%>">Trabajadores en Preparaci�n de Alimentos para Montauk, N.Y, EUA</a></li>
				        	<li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/candidatos/ofertas-EEUU-Canada/oferta-westbury-NY-001.jsp")%>">Jardinero paisajista para Westbury, N. Y., EUA</a></li>
				        	<li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/candidatos/ofertas-EEUU-Canada/oferta-Montauk-NY-003.jsp")%>">Mucama/Lavander�a para Montauk, N.Y., EUA</a></li>
				        	<li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/candidatos/ofertas-Alemania/oferta-Alemania-001.jsp")%>">Ingeniero en Automatizaci�n para Alemania</a></li>
				        	<li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/candidatos/ofertas-Alemania/oferta-Alemania-002.jsp")%>">Ingeniero El�ctrico para Alemania</a></li>
				        	<li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/candidatos/ofertas-Alemania/oferta-Alemania-003.jsp")%>">Asistente M�dico para Alemania</a></li>
				        	<li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/candidatos/ofertas-Alemania/oferta-Alemania-004.jsp")%>">Enfermeras en Cuidado de Adultos Mayores para Alemania</a></li>
				        	<li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/candidatos/ofertas-Alemania/oferta-Alemania-005.jsp")%>">Farmac�logo para Alemania</a></li>
				        	<li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/candidatos/ofertas-Alemania/oferta-Alemania-006.jsp")%>">Arquitectura de Software para Alemania</a></li>
				        	<li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/candidatos/ofertas-Alemania/oferta-Alemania-007.jsp")%>">Desarrollador de Software para Alemania</a></li>
				        	<li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/candidatos/ofertas-Alemania/oferta-Alemania-008.jsp")%>">Probador de Software para Alemania</a></li>
				        	<li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/candidatos/ofertas-Alemania/oferta-Alemania-009.jsp")%>">Desarrolladores de redes de interfaz y/o servidores para Alemania</a></li>
				        	<li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/candidatos/ofertas-Alemania/oferta-Alemania-010.jsp")%>">Enfermeras para Alemania</a></li>
				        	<li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/candidatos/ofertas-Alemania/oferta-Alemania-011.jsp")%>">Asistente de Radiolog�a para Alemania</a></li>
				        	<li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/candidatos/ofertas-Alemania/oferta-Alemania-012.jsp")%>">Terapeuta F�sico para Alemania</a></li>
				        	<li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/candidatos/ofertas-Alemania/oferta-Alemania-013.jsp")%>">Ingeniero de Proyecto para Alemania</a></li>
				        	<li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/candidatos/ofertas-Alemania/oferta-Alemania-014.jsp")%>">Ingeniero Industrial</a></li>
				        	<li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/candidatos/ofertas-Alemania/oferta-Alemania-015.jsp")%>">Ingeniero Mec�nico</a></li>
				        	<li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/candidatos/ofertas-Alemania/oferta-Alemania-016.jsp")%>">Cient�ficos de Datos / Analistas de Datos</a></li>
				        	<li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/candidatos/ofertas-Alemania/oferta-Alemania-017.jsp")%>">Desarrollador de Bases de Datos</a></li>
				        	<li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/candidatos/ofertas-Alemania/oferta-Alemania-018.jsp")%>">Ingeniero de Desarrollo en Pruebas</a></li> -->
				       </ul>
				  	</li>
			        <li class='has-sub'><a href='#'><span>Movilidad Laboral Interna <span class="caret"/></span></a>
				     	<ul>
				        	<li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/servicioNacionalDeEmpleo/movilidadLaboralInterna.jsp")%>">Movilidad Laboral Interna</a></li>
				        	<li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/servicioNacionalDeEmpleo/repatriadosTrabajando.jsp")%>">Repatriados trabajando</a></li>
				        	<!--li><a href="<%=request.getContextPath()%>/jsp/empleo/servicioNacionalDeEmpleo/sectorAgricola.jsp">Sector agr�cola</a></li-->
					        <!--li><a href="<%=request.getContextPath()%>/jsp/empleo/servicioNacionalDeEmpleo/sectorIndustrialServicios.jsp">Sector industrial y de servicios</a></li-->
				        </ul>
				  	</li> 
			         
			         <li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/servicioNacionalDeEmpleo/programaEmpleoTemporal.jsp")%>">Programa de Empleo Temporal (PET)</a></li>
			        <li class='has-sub'><a href="#">Publicaciones <span class="caret"/></span></a>
			         	<ul>
			         		<li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/servicioNacionalDeEmpleo/publicaciones.jsp")%>">Publicaciones</a></li>
			         		<li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/servicioNacionalDeEmpleo/periodicoDeOfertasDeEmpleo.jsp")%>">Peri�dico de ofertas de empleo del SNE</a></li>
			         		<li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/servicioNacionalDeEmpleo/revistaInformativaDelSNE.jsp")%>">Revista informativa del SNE</a></li>
			         	</ul>
			         </li>
			   </li>
			   
			   
			   <li class='has-sub'><a href="#">Estad�sticas laborales <span class="caret"/></span></a>
		         	<ul>
		         		<li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/servicioNacionalDeEmpleo/estadisticasLaborales.jsp")%>">Estad�sticas laborales</a></li>
		         		<li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/servicioNacionalDeEmpleo/accionesVinculacionLaboral.jsp")%>">Acciones de vinculaci�n laboral</a></li>
		         		<li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/servicioNacionalDeEmpleo/programaApoyoEmpleo.jsp")%>">Programa de Apoyo al Empleo (PAE)</a></li>
		         		<li><a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/servicioNacionalDeEmpleo/programasDeEmpleo.jsp")%>">Programas de empleo</a></li>
		         		<li><a href="http://publicaciones.empleo.gob.mx/estadisticas/Resumen_Global.xls">Resumen global</a></li>
		         	</ul>
		         </li>
			</ul>
		</div>
    </div>
    
    <div class="clearfix visible-xs"></div>