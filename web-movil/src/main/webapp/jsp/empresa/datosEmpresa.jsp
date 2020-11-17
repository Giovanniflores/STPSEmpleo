<%String context = request.getContextPath() +"/";%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<table cellpadding="0" cellspacing="0">
	<tr>
		<td><img src="${context}imageAction.m?method=getLogotipoEmpresa" alt="logo ${empresaEspacioForm.nombreEmpresa}" width="100px" height="100px">
		</td>
		<td>&nbsp;</td>
		<td style="font-size: 10px;"><font color="#516814">${empresaHeader.nombreEmpresa}</font><br />
			 <p id="contacto_empresa">Representante: ${empresaHeader.contactoEmpresa}<br />
	            ID del Portal del Empleo: ${empresaHeader.idPortalEmpleo}<br />
	          ${empresaHeader.tipoPersona}<br />
	            <c:if test="${empresaHeader.idTipoPersona eq 1}">
	            	Fecha de nacimiento: ${empresafechaheader}<br />
	            </c:if>
	            <c:if test="${empresaHeader.idTipoPersona eq 2}">
	            	Fecha de acta constitutiva: ${empresafechaheader}<br />
	            </c:if></p></td>
	</tr>
</table>