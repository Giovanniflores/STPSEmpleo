<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%
	String contextApp = request.getContextPath();
	String contextSWB = (String)application.getAttribute("DOMAIN_PORTAL");
    if (null != request.getSession().getAttribute("FROM_CIL"))
    	contextSWB = (String)application.getAttribute("DOMAIN_PORTAL_CIL");;
%>

<div id="navegacion" class="col-sm-4 col-sm-push-8"> 
<div id="cssmenu">     
<ul style="width:100%">
<li>
<a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/servicioNacionalDeEmpleo/servicioNacionalDeEmpleo.jsp")%>" class="swb-menumap-act">Servicio Nacional de Empleo</a>
</li>
<li>
<a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/servicioNacionalDeEmpleo/dondeBuscarEmpleo.jsp")%>" class="swb-menumap-act">En busca de empleo</a>
</li>
<li>
<a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/servicioNacionalDeEmpleo/capacitacionAutoempleoBecate.jsp")%>" class="swb-menumap-act">Capacitación y becas</a>
</li>
<li>
<a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/candidatos/conoceDescubreHabilidadesCapacidades.jsp")%>" class="swb-menumap-act">Orientación profesional</a>
</li>
<li>
<a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/candidatos/asesoriaLaboral.jsp")%>" class="swb-menumap-act">Asesoría laboral</a>
</li>
<li>
<a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/servicioNacionalDeEmpleo/estadisticasLaborales.jsp")%>" class="swb-menumap-act">Estadísticas laborales</a>
</li>
</ul>
</div>
</div>