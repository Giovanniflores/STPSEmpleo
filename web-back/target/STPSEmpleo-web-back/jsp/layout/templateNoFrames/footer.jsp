<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
    String contextApp = request.getContextPath();
    String contextSWB = (String)application.getAttribute("DOMAIN_PORTAL");

    if (null != request.getSession().getAttribute("FROM_CIL")){
        //contextApp = (String)application.getAttribute("DOMAIN_PORTAL_CIL");
        contextSWB = (String)application.getAttribute("DOMAIN_PORTAL_CIL");
    }
%>

<div class="clearfix"></div>
<div id="footer">
	<!-- GOBmx <div class="row">
	    <div class="col-md-12">
	      <div id="content_btoSB">
	      	<a class="subir" href="#">subir</a>
	      </div>
	    </div>
	  </div>-->
	<!-- GOBmx 
    <div id="links_footer" class="row footerOfertas">
        <div class="col-sm-4">
            <h3>Encuentra ofertas de empleo</h3>
            <ul>
                <li><a href="<c:url value="/busquedaEspecificaOfertas.do?method=buscar"/>">Búsqueda específica de ofertas de empleo</a></li>
                <li><a href="<c:url value="/ofertasRecientes.do?method=totalOfertasRecientes"/>">Ofertas de empleo recientes</a></li>
                <li><a href="<c:url value="/ofertasRecientes.do?method=totalOfertasDestacadas"/>">Ofertas de empleo destacadas</a></li>
                <li><a href="<c:url value="/jsp/empleo/servicioNacionalDeEmpleo/periodicoDeOfertasDeEmpleo.jsp"/>">Periódico de ofertas de empleo del SNE</a></li>
                <li><a href="<c:url value="/jsp/empleo/herramientasDelSitio/eventos.jsp"/>">Eventos próximos de Ferias de Empleo</a></li>
                <li><a href="<c:url value="/jsp/empleo/candidatos/bolsasEmpleoAsociadas.jsp"/>">Bolsas de empleo asociadas</a></li>
             </ul>
        </div>
        <div class="col-sm-4">
            <h3>¿Busco trabajo?<br>Aumenta tus Posibilidades</h3>
            <ul>
                <li><a href="<c:url value="/jsp/empleo/candidatos/registrate.jsp"/>">Regístrate y haz que las empresas te vean</a></li>
                <li><a href="<c:url value="/jsp/empleo/candidatos/habilidadesBusquedaEmpleo.jsp"/>">Elabora tú Curriculum Vitae</a></li>
                <li><a href="<c:url value="/jsp/empleo/servicioNacionalDeEmpleo/programasServiciosEmpleo.jsp"/>">Programas y servicios de empleo</a></li>
                <li><a href="<c:url value="/jsp/empleo/candidatos/conoceDescubreHabilidadesCapacidades.jsp"/>">Conoce y descubre tus habilidades y capacidades</a></li>
                <li><a href="<c:url value="/jsp/empleo/servicioNacionalDeEmpleo/talleresPresenciales.jsp"/>">Solicitud de empleo ¡Aprende como elaborarla!</a></li>
           <!-- <li><a href="<c:url value="/jsp/empleo/candidatos/becasManpowerTdc.jsp"/>">Becas Manpower TDC</a></li>   
                <li><a href="<c:url value="/jsp/empleo/candidatos/certificacionCompetenciasConocer.jsp"/>">Certifica tus habilidades laborales: CONOCER</a></li>
            </ul>
        </div>
        <div class="col-sm-4">
            <h3>Servicios para tu empresa</h3>
            <ul>
                <li><a href="<c:url value="/registroEmpresa.do?method=init"/>">Publica vacantes de empleo gratis</a></li>
                <li><a href="<c:url value="/jsp/empleo/servicioNacionalDeEmpleo/feriasEmpleo.jsp"/>">Participa en las Ferias de Empleo</a></li>
                <li><a href="<c:url value="/jsp/empleo/servicioNacionalDeEmpleo/revistaInformativaDelSNE.jsp"/>">Revista Informativa del SNE</a></li>
                <li><a href="<c:url value="/jsp/empleo/empresas/mejoraDesempeñoEmpresa.jsp"/>">Mejora el desempeño de tu empresa</a></li>
                <li><a href="<c:url value="/jsp/empleo/servicioNacionalDeEmpleo/estadisticasLaborales.jsp"/>">Estadísticas laborales</a></li>
                <li><a href="<c:url value="/jsp/empleo/servicioNacionalDeEmpleo/acercaSne.jsp"/>">Acerca del SNE</a></li>
            </ul>
        </div>
        <div class="clearfix"><img src="//seal.qualys.com/sealserv/seal.gif?i=5bd1fcbc-9922-432d-8f49-cdf7ac93888c" onclick="window.open('https://seal.qualys.com/sealserv/info/?i=5bd1fcbc-9922-432d-8f49-cdf7ac93888c','qualysSealInfo', 'height=600,width=851,resizable=1')" /></div>
    </div>-->
        
 </div>
 
