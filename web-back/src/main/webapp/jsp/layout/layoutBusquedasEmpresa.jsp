<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>
<%@ page import="mx.gob.stps.portal.web.infra.utils.Constantes"%>

<%
	String bodyjsp = (String)session.getAttribute(Constantes.BODY_JSP);
	String body = bodyjsp!=null ? bodyjsp : "/jsp/layout/body.jsp";
%>

<tiles:insert page="/jsp/layout/templateNoFrames/templateBusquedasEmpresa.jsp" flush="true">
	<tiles:put name="title" value="Portal del Empleo"/>
	<tiles:put name="menu" value="/jsp/layout/menu.jsp"/>
	<tiles:put name="body" value="<%=body%>"/>
</tiles:insert>