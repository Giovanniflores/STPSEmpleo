<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>

<tiles:insert page="/jsp/layout/templateEspacioEmpresasEmptyTemp.jsp" flush="true">
	<tiles:put name="title" value="Portal del Empleo :: Registro de candidato"/>
	<tiles:put name="body" value="${BODY_JSP}"/>
</tiles:insert>