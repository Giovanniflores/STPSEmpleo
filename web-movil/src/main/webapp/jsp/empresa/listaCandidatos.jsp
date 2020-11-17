<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="mx.gob.stps.portal.core.candidate.vo.CandidatoVo"%>
<%@ page import="java.util.List"%>

	
	<c:if test="${empty buscarCandidatosEmpresaForm.listaCandidatosOferta}">
	<div dojoType="dojox.mobile.View" id="vacio" selected="true">
			<div dojoType="dojox.mobile.RoundRect"  align="center">
			No se encontraron candidatos para la búsqueda: <c:out value="${searchQ}"/>
		
			</div>
			</div>
	</c:if>
	<c:if test="${not empty buscarCandidatosEmpresaForm.listaCandidatosOferta}">
	<h1 dojoType="dojox.mobile.Heading"
				label="Mostrando ${buscarCandidatosEmpresaForm.ofertaDesde} a ${buscarCandidatosEmpresaForm.ofertaHasta} de <%=(Integer)request.getSession().getAttribute("TOTAL_CANDIDATOS_OFERTA")%> Candidatos" class="mblHeadingNoImage" style="font-size: 10px">
				<ul dojoType="dojox.mobile.TabBar" barType="segmentedControl"
					style="float: right; margin-right: 6px;">
					<c:if test="${buscarCandidatosEmpresaForm.paginaAnterior==true}"><li dojoType="dojox.mobile.TabBarButton"
						class="mblDomButton mblDomButtonUpArrow_2" selectOne="false" onclick="javascript:doSubmitPager('prev',${buscarCandidatosEmpresaForm.paginaActual})"><img src="images/bt_up.png"/></li></c:if>
					<c:if test="${buscarCandidatosEmpresaForm.paginaAnterior==false}"><li dojoType="dojox.mobile.TabBarButton"
						class="mblDomButton mblDomButtonUpArrow_2" selectOne="false" ><img src="images/bt_up.png"/></li></c:if>
					
					<c:if test="${buscarCandidatosEmpresaForm.paginaSiguiente==true}"><li dojoType="dojox.mobile.TabBarButton"
						class="mblDomButton mblDomButtonDownArrow_2" selectOne="false"  onclick="javascript:doSubmitPager('next',${buscarCandidatosEmpresaForm.paginaActual})"><img src="images/bt_down.png"/></li></c:if>
							<c:if test="${buscarCandidatosEmpresaForm.paginaSiguiente==false}"><li dojoType="dojox.mobile.TabBarButton"
						class="mblDomButton mblDomButtonDownArrow_2" selectOne="false"><img src="images/bt_down.png"/></li></c:if>
				</ul>
			</h1>
			<ul dojoType="dojox.mobile.RoundRectList" >
			<div style="text-align: center;" class="titNar">Resultados</div>
			
				<c:forEach var="candidatos" items="${buscarCandidatosEmpresaForm.listaCandidatosOferta}">
		
		
						<li class="mblVariableHeight" dojoType="dojox.mobile.ListItem"
							 moveTo="bar" href="${context}buscarCandidatosEmpresa.m?method=detalleCandidato&idc=${candidatos.idCandidato}&compatibilidad=${candidatos.compatibilidad}">
							<table>
								<tbody>
									<tr>
										<td ><a class="titNarSeccion">${candidatos.nombre} ${candidatos.apellido1} ${candidatos.apellido2}</a>
										<c:if test="${candidatos.compatibilidad>0}"><br>Compatibilidad ${candidatos.compatibilidad}%</c:if>
										<br>${candidatos.carrera}
										<br>${candidatos.edad} años
										<br>${candidatos.municipioEntidad}
										<br>
										<strong>Expectativas laborales:</strong> 
							Ocupación deseada: ${candidatos.ocupacion}, 
							<c:if test="${not empty candidatos.salario}">
								<fmt:setLocale value="en_US"/>
								Salario pretendido: <fmt:formatNumber type="CURRENCY" value="${candidatos.salario}" />
					        </c:if>,				        				
							Tipo de contrato: ${candidatos.descTipoContrato}, 
							Tipo de empleo: ${candidatos.horarioEmpleo},
							Disponibilidad para viajar: ${candidatos.dispViajarFuera},
							Disponibilidad para radicar fuera: ${candidatos.dispRadicarFuera} 
							
										</td>
									</tr>
								</tbody>
							</table></li>

					
				
			
			
			</c:forEach>
			
			</ul>
		

</c:if>
	

 

 
 