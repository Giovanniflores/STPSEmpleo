<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page trimDirectiveWhitespaces="true" %>
<% 
	String context = request.getContextPath() +"/";
	pageContext.setAttribute("context", context);
%>
<script type="text/javascript" src="js/helper/messageHelper.js"></script>
<script src="dojotoolkit/dojo/dojo.js" djConfig="parseOnLoad: true, locale:'es'"></script>
<script type="text/javascript">
	dojo.require("dijit.dijit"); // loads the optimized dijit layer
	dojo.require("dijit.Dialog");
	dojo.require("dijit.form.Form");
	dojo.require("dijit.form.Button");
	
	function doSubmit() {
		document.ProgramaForm.method.value = 'savecomplement';
		dojo.xhrGet({
			url: 'perfilComplementario.do',
			form:'ProgramaForm',
			sync: true,
			timeout:180000,
			load: function(data) {
				var res = dojo.fromJson(data);
				if (res.type=='ext') {
					messagePath(res.message,'${context}miEspacioCandidato.do?method=init');
				}else {
					message(res.message);
				}
			}
		}); 
	}
	
	function doSubmitComp(siglas) {
		location.href = '${context}perfilComplementario.do?method=complement&program='+siglas;
	}
	
	function messagePath(msg,path){
		var html =
			'<div id="messageDialgop2" name="messageDialgop2">' +
		'<p style="text-align: center;">'+ msg +'</p>'+
		'<p class="form_nav">'
		+ cerrarMsgRuta(path, "Aceptar")
	    + '</p>'
	    +'</div>';
	
		dialogMsg = new dijit.Dialog({
	        title: 'Mensaje',
	        content: html,
	        style: "width:300px;",
	        showTitle: false, draggable : false, closable : false,
	    });			
		dialogMsg.show();
	}
</script>
<noscript>Funciones de JavaScript desactivadas por navegador</noscript>
<div class="miEspacio">
	<form name="myopportunities" id="myopportunities" method="post">
	<h2>Mi espacio</h2>
	<div class="tab_block">
        <div align="left" id="returnME" style="display:block;">
            <a class="expand" href="javascript:location.replace('<c:url value="/miEspacioCandidato.do"/>');"><strong>Ir al inicio de Mi espacio</strong></a>
        </div>
        <div class="Tab_espacio">
        	<h3>
        		Portafolio de servicios
        	</h3>
            <div class="subTab">
                <ul>
                    <li class="curSubTab">
                        <span>
                        	${ProgramaForm.modalidad.nombreCorto}
                        </span>
                    </li>
                    <c:forEach var="program" items="${ProgramaForm.path}">
                    	<li><a href="${context}${program.url}">${program.nombreCorto}</a></li>
                    </c:forEach>
                </ul>
                <div class="clearfix"></div>
            </div>
        </div>
        <div class="sublevelTitle">
        	<c:if test="${ProgramaForm.modalidad.param.toLowerCase()!='beca'}">
            	${ProgramaForm.modalidad.nombreCorto}
            </c:if>
            <br/><span class="subsublevel">${ProgramaForm.modalidad.nombre}</span>
        </div>
        <p class="descProgram">
            ${ProgramaForm.descPrograma}
        </p>
        <div class="sublevelTitle">
            ${ProgramaForm.descRequisitos}
        </div>
        <div class="requistos">
        	<ul>
        		<c:forEach var="requisite" items="${ProgramaForm.requisitos}">
                   	<li>${requisite}</li>
                </c:forEach>
            </ul>
        </div>
        <c:if test="${ProgramaForm.modalidad.param.toLowerCase()!='ca'}">
	        <div class="sublevelTitle">
	            ${ProgramaForm.descDocumentos}
	        </div>
	        <div class="requistos">
	        	<ul>
	        		<c:forEach var="document" items="${ProgramaForm.documentos}">
	                   	<li>${document}</li>
	                </c:forEach>
	            </ul>
	        </div>
	    </c:if>
        <div class="sublevelTitle">
        	<span class="subsublevel">IMPORTANTE: Para enviar tu solicitud de participación, es necesario que realices la captura de datos complementarios, haz clic en el botón "Aceptar" para continuar.</span>
        </div>
	</div>
	</form>
</div> 
<div class="busquedaPorPerfil">
	<div id="mis_ofertas">
		<div id="divOffers"></div>
		<div class="form_nav">
			<c:choose>
				<c:when test="${ProgramaForm.modalidad.param.toLowerCase()=='ptat' || ProgramaForm.modalidad.param.toLowerCase()=='fa' || ProgramaForm.modalidad.param.toLowerCase()=='beca' || ProgramaForm.modalidad.param.toLowerCase()=='misa' || ProgramaForm.modalidad.param.toLowerCase()=='misi'}">
					<input type="button" class="boton" name="part" value="Aceptar" onclick="doSubmitComp('${ProgramaForm.modalidad.param.toLowerCase()}');"/>
				</c:when>
				<c:when test="${ProgramaForm.modalidad.param.toLowerCase()=='mml'}">
					<input type="button" class="boton" name="part" value="Aceptar" onclick="doSubmitComp('${ProgramaForm.modalidad.param.toLowerCase()}');"/>
				</c:when>
				<c:otherwise>
					<input type="button" class="boton" name="part" value="Aceptar" onclick="doSubmit();"/>
				</c:otherwise>
			</c:choose>
        </div>
	</div>
</div>
<form name="ProgramaForm" id="ProgramaForm" action="informacionPrograma.do" method="post" dojoType="dijit.form.Form">	
	<input type="hidden" name="method" id="method" value="init" />
</form>