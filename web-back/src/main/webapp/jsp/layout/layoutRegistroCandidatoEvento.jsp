<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>

<tiles:insert page="/jsp/layout/templateNoFrames/templateCandidatoEvento.jsp" flush="true">
	<tiles:put name="title" value="Portal del Empleo :: Registro de candidato a un Evento"/>
	<tiles:put name="menu" value="/jsp/layout/menu.jsp"/>
	<tiles:put name="body" value="${BODY_JSP}"/>
</tiles:insert>