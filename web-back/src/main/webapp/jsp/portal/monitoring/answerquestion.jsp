	<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
	<%@ page import="mx.gob.stps.portal.web.infra.utils.Utils, mx.gob.stps.portal.web.offer.form.OfferQuestionForm,java.util.ArrayList, mx.gob.stps.portal.web.offer.wrapper.OfertaJB, mx.gob.stps.portal.core.oferta.pregunta.vo.OfertaPreguntaVO"%>
	<%
		OfferQuestionForm offer = (OfferQuestionForm)request.getSession().getAttribute("OfferQuestionForm");
		OfertaJB employ = offer.getOfertaJB();
		String context = request.getContextPath() +"/";
	%>
	
<script type="text/javascript">	
	  	function maximaLongitud(texto,maxlong)
	  	{
	  	var tecla, int_value, out_value;

	  	if (texto.value.length > maxlong)
	  	{
	  	/*con estas 3 sentencias se consigue que el texto se reduzca
	  	al tamaño maximo permitido, sustituyendo lo que se haya
	  	introducido, por los primeros caracteres hasta dicho limite*/
	  	in_value = texto.value.replace(/^\s+|\s*[\r\n][\r\n \t]*/g, "");
	  	
	  	out_value = in_value.substring(0,maxlong);
	  	texto.value = out_value;
	  	
	  	return false;
	  	}
	  	return true;
	  	}		  
</script>
<noscript>Funciones de JavaScript desactivadas por navegador</noscript>
	
	
	<div class="det_index">
	<h2 class="titulo_profesion"><%=employ.getTituloOferta()%></h2>
    <div class="ficha_oferta perfil_candidato">
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
        <div class="clearfix"></div>
    </div>
    <div id="preguntas_relacionadas" class="offer_section odd">
		<h3><%=offer.getPregunta()%></h3>
		<%
			if (null != request.getSession().getAttribute("_user")) {
		%>
        <div class="redacta">
			<form name="formAnswer" id="formAnswer" method="post" action="detalleoferta.do">
				<input type="hidden" name="idOfertaPregunta" id="idOfertaPregunta" value="<%=offer.getIdOfertaPregunta()%>"/>
				<p><strong>Responder:</strong></p>
				<div class="campo_carta">
					<input type="hidden" name="method" id="method" value="answerQuestion"/>
					<textarea name="respuesta" id="respuesta" cols="50" rows="7" 
					trim="true" onchange="return maximaLongitud(this,2000)" onKeyUp="return maximaLongitud(this,2000)" maxlength="2000"></textarea>
				</div>
				<span class="bottom_crear_carta2"></span>
				<br />
				<p style="text-align: center"><input type="submit" id="sendAnswer" value="enviar respuesta" class="boton"/></p>
			</form>
        </div>
		<%
			}
		%>
    </div>
    </div>
    <div class="clearfix"></div>