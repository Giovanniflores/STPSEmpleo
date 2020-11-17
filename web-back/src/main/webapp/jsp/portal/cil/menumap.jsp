<% 
	long idCil = (null != request.getSession().getAttribute("idCil") ? (Long)request.getSession().getAttribute("idCil") : 0);
%>	
<div id="navegacion">
	<ul>
    	<li>
			<a href="/es_mx/empleo/servicio_nacional_de_empleo_" class="swb-menumap-act">Servicio Nacional de Empleo</a>
		</li>
		<li>
			<a href="/es_mx/empleo/solicitantes" class="swb-menumap-act">En busca de empleo</a>
		</li>
		<li>
			<a href="/es_mx/empleo/busco_capacitacion" class="swb-menumap-act">Capacitaci&oacute;n y becas</a>
		</li>
		<li>
			<a href="/es_mx/empleo/estudiantes" class="swb-menumap-act">Orientaci&oacute;n profesional</a>
		</li>
		<li>
			<a href="/es_mx/empleo/busco_asesoria_laboral" class="swb-menumap-act">Asesor&iacute;a laboral</a>	
		</li>
		<li>
			<a href="/es_mx/empleo/investigadores_del_mercado_laboral" class="swb-menumap-act">Estad&iacute;sticas laborales</a>
		</li>
		<%
			if (idCil > 0) {
		%>
				<li>
					<a href="/es_mx/empleo/Inter_Laboral" class="swb-menumap-act">Intermediaci&oacute;n Laboral</a>
				</li>
				<li>
					<a href="/es_mx/empleo/Registro_de_Servicios" class="swb-menumap-act">Registro de Servicios</a>
				</li>
		<%
			}
		%>	
	</ul>
</div>