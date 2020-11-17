<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>
<tiles:insert page="./fotoCandidato.jsp" flush="true"></tiles:insert>
<div id="contenido_perfil">
	<div id="columna_articulos" class="perfil">
    	<div id="avisos">
    		<h2>Avisos <span>(3 nuevos)</span></h2>
            <p>La oferta laboral "Ejecutivo de Cuenta" de "INNOVA PUBLICIDAD" ha sido eliminada - Hace 4 horas</p>
            <div id="paginador_avisos"><a href="#" class="anterior">anterior</a> 2 de 12 <a href="#" class="siguiente">siguiente</a>
            </div>
        </div>
        <div class="tips"><a href="#" ><span>Tips</span> para conseguir empleo</a></div>
        <div  class="foro"><a href="#"><span>Foro</span> del empleo</a></div>
    </div>
    <div id="TabbedPanels1" class="TabbedPanels">
    	<ul class="TabbedPanelsTabGroup">
        	<li class="TabbedPanelsTab" tabindex="0">Mi espacio</li>
            <li class="TabbedPanelsTab" tabindex="0">Mis datos</li>
            <li class="TabbedPanelsTab" tabindex="0">Mis ofertas de empleo</li>
        </ul>
        <div class="TabbedPanelsContentGroup">
        	<tiles:insert page="./miEspacio.jsp" flush="true"></tiles:insert>
            <tiles:insert page="./misDatos.jsp" flush="true"></tiles:insert>
            <tiles:insert page="./misOfertas.jsp" flush="true"></tiles:insert>
        </div>
    </div>
</div>