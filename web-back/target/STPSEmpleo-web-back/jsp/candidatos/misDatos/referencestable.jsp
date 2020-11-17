<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page import="java.util.List, java.util.Iterator, java.util.ArrayList, mx.gob.stps.portal.persistencia.vo.ReferenciaLaboralVO, mx.gob.stps.portal.core.infra.utils.Constantes"%>
<%
	List<ReferenciaLaboralVO> referencias = new ArrayList<ReferenciaLaboralVO>();
	if (null != request.getSession().getAttribute("LST_REFERENCES"))
		referencias = (List<ReferenciaLaboralVO>)request.getSession().getAttribute("LST_REFERENCES");
%>
<table id="referencias">
	<tbody>
<% if (!referencias.isEmpty())  { %>
		<tr>
        	<th>Empresa</th>
            <th>Puesto</th>
            <th>Domicilio</th>
            <th>Referencia</th>
            <th></th>
        </tr>
<%
	}
	int index = 1;
	String idReferenciaAdd = "idReferenciaAdd";
	Iterator<ReferenciaLaboralVO> itreferencias = referencias.iterator();
	while (itreferencias.hasNext()) {
		ReferenciaLaboralVO vo = itreferencias.next();
%>
	<input type="hidden" name="<% out.print(idReferenciaAdd + index); %>" id="<% out.print(idReferenciaAdd + index); %>" value="<% out.print(vo.getTrabajoActual().getIdHistorialLaboral()); %>" dojoType="dijit.form.TextBox"/>
	<tr <% if (index%2 ==0) out.println("class=''"); else out.println("class='odd'"); %>>
    	<td><%=vo.getTrabajoActual().getEmpresa() %></td>
        <td><%=vo.getTrabajoActual().getPuesto() %></td>
        <td><% if (null != vo.getDomicilio()) out.print(vo.getDomicilio().getDomicilioCompleto()); %></td>
        <td><%=vo.getReferencia() %></td>
        <td>
        	<% if (2 == vo.getTrabajoActual().getPrincipal()) { %>
        		<input type="button" class="agregar" onclick="javascript:toggleDelete(<%=vo.getTrabajoActual().getIdHistorialLaboral() %>);" title="Eliminar Referencia" value="Eliminar" />
        	<% } %>
        </td>
    </tr>
<% 
	index++;
	}
%>
	</tbody>
</table>