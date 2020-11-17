	<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
	<%@ page import="mx.gob.stps.portal.web.infra.utils.Utils, mx.gob.stps.portal.web.offer.form.OfferQuestionForm,java.util.ArrayList, mx.gob.stps.portal.web.offer.wrapper.OfertaJB, mx.gob.stps.portal.core.oferta.pregunta.vo.OfertaPreguntaVO"%>
	<%
		OfferQuestionForm offer = (OfferQuestionForm)request.getSession().getAttribute("OfferQuestionForm");
		OfertaJB employ = offer.getOfertaJB();
		String context = request.getContextPath() +"/";
	%>
	<div class="det_index">
	<h2 class="titulo_profesion"><%=employ.getTituloOferta()%></h2>
    <div class="perfil_candidato">
		<div class="user_pic">
			<img src="<%=context%>imageAction.do?method=getImagen&ID_EMPRESA=<%=employ.getIdEmpresa()%>" alt="<%=employ.getEmpresaNombre()%>" />
			<div class="shadow"></div>
        </div>
        <div class="perfil_c">
        	<div class="perfil_title">
        		<h3><%=employ.getEmpresaNombre()%></h3>
        	</div>
        	<div class="perfil_index">
        		<ul>
		            <li><strong>Oferta de empleo</strong><br /><span><%=employ.getTituloOferta()%></span></li>
		            <li><strong>Tipo de contrato</strong><br /><span><%=employ.getTipoContrato()%></span></li>
		            <li><strong>Horario</strong><br /><span><%=employ.getHorarioLaboral()%></span></li>
	            </ul>
            </div>
        </div>
    </div>
    <div class="clearfix"></div>
    <div class="offer_index">
    <div id="preguntas_relacionadas" class="offer_section odd">
		<%
			ArrayList<OfertaPreguntaVO> preguntas = (ArrayList<OfertaPreguntaVO>)offer.getOfertaPreguntasList();
			if (preguntas.size() > 0) {
				out.println("<h3>Preguntas recientes acerca de la oferta de empleo</h3>");
				for (int i=0; i<preguntas.size(); i++) {
					OfertaPreguntaVO vo = preguntas.get(i);
			%>
					<p><strong><%=vo.getPregunta()%></strong></p>
			<%
					if (null != vo.getRespuesta() && !"".equals(vo.getRespuesta())) {
			%>		<p>
						<span>Reclutador: </span><%=vo.getRespuesta()%>
					</p>
			<%
					}else {
						out.println("<p><a href=\"" + context + "detalleoferta.do?method=answer&id_oferta_pregunta=" + vo.getIdOfertaPregunta() + "\">Responder</a></p>");
					}
				}
			}else
				out.println("<h3>No hay preguntas recientes acerca de la oferta de empleo</h3>");
		%>
    </div>
    	<p class="ligas_consulta" style="text-align: center; padding: 0 0 60px">
			<a href="/OfertaNavegacion.do?method=init">Regresar a mis ofertas de empleo</a>
			<!--<a href="<c:url value="/OfertaNavegacion.do?method=init"/" class="regresar_a_resultado">Regresar a mis ofertas de empleo</a>-->
			<!--<a href="<c:url value="/OfertaNavegacion.do?method=init"/>" class="regresar_a_resultado">Regresar a mis ofertas de empleo</a>  -->
	    </p>
    </div>
    </div>
    <div class="clearfix"></div>
