<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>

<tiles:insert page="/jsp/layout/templateOffer.jsp" flush="true">
	<tiles:put name="title" value="Detalle de oferta"/>
	<tiles:put name="body" value="${BODY_JSP}"/>
</tiles:insert>