<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="mx.gob.stps.portal.core.oferta.vo.PostulacionRecienteVO, java.util.List, java.util.Iterator" %>
<style type="text/css">
	.redFont{
		color: #FF0000;
	}
	.Font80{
		font-size: 80%;
	}
</style>
<%
List<PostulacionRecienteVO> rows = (List<PostulacionRecienteVO>) request.getAttribute("postulations");
Iterator<PostulacionRecienteVO> postulations = null;
if (rows != null && !rows.isEmpty()) {
	postulations = rows.iterator();
}
%>
<div>
    <h2>Postulaciones recientes</h2>
<%
if (postulations != null) {
    while (postulations.hasNext()) {
    	PostulacionRecienteVO row = postulations.next(); %>
    <ul>
        <li>
            <a href="#?id=<%=row.getIdCandidato()%>">
                <%=row.getNombreCandidato()%> se postul&oacute; por la oferta &quot;<%=row.getTituloOferta()%>&quot;
            </a>
        </li>
    </ul>
<%  }
}    %>
    <html:messages id="messages" message="true">
        <span class="Font80"><bean:write name="messages"/></span><br/>
    </html:messages>
    <a href="">Ver m&aacute;s postulaciones</a>
</div>