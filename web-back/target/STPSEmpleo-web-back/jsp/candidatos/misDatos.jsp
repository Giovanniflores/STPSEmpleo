<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>
<div class="TabbedPanelsContent" id="mis_datos">
	<div class="form_container">
    	<h2>Mis datos</h2>
        <div id="TabbedPanels2" class="TabbedPanels">
        	<ul class="TabbedPanelsTabGroup">
        		<li class="TabbedPanelsTab" tabindex="0"><h3><span class="subtitulo">Informaci&oacute;n de mi perfil</span><span>1</span></h3></li>
            	<li class="TabbedPanelsTab" tabindex="0"><h3><span class="subtitulo">Escolaridad y otros estudios</span><span>2</span></h3></li>
             	<li class="TabbedPanelsTab" tabindex="0"><h3><span class="subtitulo">Experiencia laboral</span><span>3</span></h3></li>
             	<li class="TabbedPanelsTab" tabindex="0"><h3><span class="subtitulo">Expectativas laborales</span><span>4</span></h3></li>
            </ul>
            <div class="TabbedPanelsContentGroup">
   	        	<tiles:insert page="/perfil.do" flush="true"></tiles:insert>
              	<tiles:insert page="./misDatos/escolaridad.jsp" flush="true"></tiles:insert>
                <tiles:insert page="./misDatos/experiencia.jsp" flush="true"></tiles:insert>
                <tiles:insert page="./misDatos/expectativas.jsp" flush="true"></tiles:insert>
        	</div>
    	</div>
	</div>
</div>