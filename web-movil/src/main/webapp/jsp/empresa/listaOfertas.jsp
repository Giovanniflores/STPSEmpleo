<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%String context = request.getContextPath() +"/";%>
	<br>
	<c:if test="${empty ofertasEmpresaForm.listaOfertas}">
	<div dojoType="dojox.mobile.View" id="vacio" selected="true">
			<div dojoType="dojox.mobile.RoundRect"  align="center">
			No cuenta con ofertas de empleo registradas
		
			</div>
			</div>
	</c:if>
	<c:if test="${not empty ofertasEmpresaForm.listaOfertas}">
	<h1 dojoType="dojox.mobile.Heading"
				label="Ofertas de empleo ${ofertasEmpresaForm.ofertaDesde} a ${ofertasEmpresaForm.ofertaHasta} de <%=(Integer)request.getSession().getAttribute("TOTAL_OFERTAS")%>" class="mblHeadingNoImage" style="font-size: 10px;">
				<ul dojoType="dojox.mobile.TabBar" barType="segmentedControl"
					style="float: right; mar7gin-right: 6px;">
					<c:if test="${ofertasEmpresaForm.paginaAnterior==true}"><li dojoType="dojox.mobile.TabBarButton"
						class="mblDomButton mblDomButtonUpArrow_2" selectOne="false" onclick="javascript:doSubmitPager('prev',${ofertasEmpresaForm.paginaActual})"><img src="images/bt_up.png"/></li></c:if>
					<c:if test="${ofertasEmpresaForm.paginaAnterior==false}"><li dojoType="dojox.mobile.TabBarButton"
						class="mblDomButton mblDomButtonUpArrow_2" selectOne="false" ><img src="images/bt_up.png"/></li></c:if>
					
					<c:if test="${ofertasEmpresaForm.paginaSiguiente==true}"><li dojoType="dojox.mobile.TabBarButton"
						class="mblDomButton mblDomButtonDownArrow_2" selectOne="false"  onclick="javascript:doSubmitPager('next',${ofertasEmpresaForm.paginaActual})"><img src="images/bt_down.png"/></li></c:if>
							<c:if test="${ofertasEmpresaForm.paginaSiguiente==false}"><li dojoType="dojox.mobile.TabBarButton"
						class="mblDomButton mblDomButtonDownArrow_2" selectOne="false"><img src="images/bt_down.png"/></li></c:if>
				</ul>
			</h1>
			<ul dojoType="dojox.mobile.RoundRectList" >
			<div style="text-align: center;" class="titNar">Mis Ofertas de Empleo</div>
	<c:forEach var="oferta" items="${ofertasEmpresaForm.listaOfertas}">
						<li class="mblVariableHeight" dojoType="dojox.mobile.ListItem"
							style="font-size: 10px;" moveTo="bar" href="${context}detalleoferta.m?method=init&idOfertaEmpleo=${oferta.idOfertaEmpleo}">
							<table>
								<tbody>
									<tr>
										<td><a class="titNarSeccion">${oferta.tituloOferta}</a>
										<br>ID ${oferta.idOfertaEmpleo}
										<c:choose>
										<c:when test="${oferta.estatus == 1}">
											<br>Activa
											</c:when>
										<c:otherwise>
											Inactiva
										</c:otherwise>
										</c:choose>
										<br>${oferta.ocupacion}
										<br>${oferta.nivelEstudios}
										<br>${oferta.carrera}
										<br>${oferta.ubicacion}
										
										
										</td>
									</tr>
								</tbody>
							</table></li>

				
				
		</c:forEach>	
				</ul>
	</c:if>
	

 

 
 