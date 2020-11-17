<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="mx.gob.stps.portal.core.oferta.vo.MiOfertaRecienteVO, java.util.List, java.util.Iterator" %>
<style type="text/css">
	.redFont{
		color: #FF0000;
	}
	.Font80{
		font-size: 80%;
	}
</style>
<%
List<MiOfertaRecienteVO> rows = (List<MiOfertaRecienteVO>) request.getAttribute("offers");
Iterator<MiOfertaRecienteVO> offers = null;
if (rows != null && !rows.isEmpty()) {
	offers = rows.iterator();
}
%>
<div>
    <h2>Mis ofertas de empleo recientes</h2>
<%
if (offers != null) {
    while (offers.hasNext()) {
        MiOfertaRecienteVO row = offers.next(); %>
    <ul>
        <li><a href="#?id=<%=row.getIdOferta()%>"><%=row.getTituloOferta()%></a></li>
    </ul>
<%  }
}    %>
	<html:messages id="messages" message="true">
	    <span class="Font80"><bean:write name="messages"/></span><br/>
	</html:messages>
	<a href="">Ver todas mis ofertas de empleo</a>
</div>