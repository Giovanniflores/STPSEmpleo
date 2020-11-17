<%@page import="mx.gob.stps.portal.core.infra.utils.Utils"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="mx.gob.stps.portal.web.candidate.form.EligeEstiloCurriculumForm"%>
<%@ page import="mx.gob.stps.portal.core.infra.utils.Constantes"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
  String context = request.getContextPath() +"/";
  //SImple == elegante
  int intEstiloSimple = Constantes.ESTILO_CV.SIMPLE.getIdOpcion();	
  int intEstiloModerno = Constantes.ESTILO_CV.MODERNO.getIdOpcion();
  int intEstiloClasico = Constantes.ESTILO_CV.CLASICO.getIdOpcion();
  String strEstiloSimple = Constantes.ESTILO_CV.SIMPLE.getOpcion();
  String strEstiloModerno = Constantes.ESTILO_CV.MODERNO.getOpcion();
  String strEstiloClasico = Constantes.ESTILO_CV.CLASICO.getOpcion();
  String strEstiloSimpleChecked = "";
  String strEstiloModernoChecked = "";
  String strEstiloClasicoChecked = "";
  
  EligeEstiloCurriculumForm formElige = (EligeEstiloCurriculumForm)session.getAttribute("EligeEstiloCurriculumForm");    
	if(null!=formElige){    	
		if(formElige.getEstiloCV()==intEstiloSimple){
			strEstiloSimpleChecked = "checked";
		} else if(formElige.getEstiloCV()==intEstiloModerno){
			strEstiloModernoChecked = "checked";
		} else if(formElige.getEstiloCV()==intEstiloClasico){
			strEstiloClasicoChecked = "checked";
		} 		       
  	} else{
  		strEstiloSimpleChecked = "checked";
  	}
  	
//    long candidatoId = (Long)request.getSession().getAttribute("idCanEstiloCv");		
  
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd"> 
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="es" lang="es"> 
<head> 
<script src="dojotoolkit/dojo/dojo.js" djConfig="parseOnLoad: true, locale:'es'"></script>
<script type="text/javascript" src="js/helper/messageHelper.js"></script>
<noscript>Funciones de JavaScript desactivadas por navegador</noscript>
<script type="text/javascript">
    dojo.require("dijit.dijit"); // loads the optimized dijit layer
	dojo.require("dijit.form.Form");    
    dojo.require("dijit._Calendar");
    dojo.require("dijit.form.Textarea");
    dojo.require("dijit.form.DateTextBox");    
    dojo.require("dijit.form.NumberTextBox");
    dojo.require("dijit.form.ValidationTextBox");
    dojo.require("dijit.form.TextBox");
	dojo.require("dijit.form.ComboBox");
	dojo.require("dojo.data.ItemFileReadStore");
	dojo.require("dijit.Dialog");
    dojo.require("dijit.form.Button");
    dojo.require("dijit.form.CheckBox");   
    dojo.require("dijit.form.FilteringSelect");
    dojo.require("dojo.parser");
</script>

<script type="text/javascript">
	function doSubmit(method){
		document.EligeEstiloCurriculumForm.method.value=method;
		document.EligeEstiloCurriculumForm.submit();		
	}

	function regresarAnt(){
		location.replace('<%=context%>miEspacioCandidato.do?method=init');		
	}
	
	function doSubmitEstiloCv(method){		
		document.getElementById('method').value=method;

		var vForm = document.getElementById('EligeEstiloCurriculumForm');
		
		var chkVal = getRadioValue('estiloCV');		
		if(chkVal!=null){
			document.EligeEstiloCurriculumForm.submit();
		} else {
			var vMessage =  document.getElementById('message'); 
			vMessage.innerHTML = '';
			vMessage.innerHTML = 'Debe seleccionar una de las opciones de estilo de curriculum.';								
		}		
	}
	
	function getRadioValue(idOrName) {
		var value = null;
		var element = document.getElementById(idOrName);
		var radioGroupName = null;  
		
		if (element == null) {
			radioGroupName = idOrName;
		} else {
			radioGroupName = element.name;     
		}
		if (radioGroupName == null) {
			return null;
		}
		var radios = document.getElementsByTagName('input');
		for (var i=0; i<radios.length; i++) {
			var input = radios[ i ];    
			if (input.type == 'radio' && input.name == radioGroupName && input.checked) {                          
				value = input.value;
				break;
			}
		}
		return value;
	}
	
	function showCVForm(){
	    var vStyle = document.getElementById('shwCvStyle').style.visibility;
	    var hideDiv = vStyle == 'hidden' ? true : false;
	    setDiv('shwCvStyle', hideDiv);	
	}
	
	function setDiv(id, visible) {	
		var vStyle = visible ? 'visible':'hidden';
		document.getElementById(id).style.visibility = vStyle;
	}
	
	function regrsarEspacioCandidato(){
		document.location.href = '<c:url value="/miEspacioCandidato.do"/>'; 
	}
</script>
<style>
	
.clean-gray{
	background:#52B956;
	color:#000000;
	font-weight:bold;
	padding:4px;
	text-align:center;
}

</style>
</head>
<body class="claro">
<div id="shwCvStyle">
<form name="EligeEstiloCurriculumForm" id="EligeEstiloCurriculumForm" method="post" action="eligeEstiloCurriculum.do" >

<div class="miEspacio formApp">
	<h2>Mi espacio</h2>
			<div class="tab_block">
				<div align="left" style="display:block;" id="returnME">
					<a class="expand" href="javascript:location.replace('<c:url value="/miEspacioCandidato.do"/>');">
					<strong>Ir al inicio de Mi espacio</strong></a>
				</div>
				<div class="Tab_espacio">
					<h3>Crear mi currículum</h3>
					<div class="subTab">
						<ul>
							<li class="curSubTab"><span>Elegir estilo de <acronym title="Curriculum Vitae">CV</acronym></span></li>
							<li><a href="/miscandidatos.do?method=genera&idCandidato=${sessionScope.idCanEstiloCv != null ? sessionScope.idCanEstiloCv : 0}">Ver currículum</a></li>
<%-- 							<li><a href="<c:url value="/miscandidatos.do?method=genera&idCandidato=${candidato}"/>">Ver currículum</a></li> --%>
							<li><a href="<c:url value="/cargaCurriculum.do?method=init"/>">Subir video-currículum</a></li>
						</ul>
		                <div class="clearfix"></div>
		            </div>
				</div>	      
			</div>
			<fieldset class="cv_style">
				<legend>Elige un estilo de <acronym title="Curriculum Vitae">CV</acronym></legend>
				
				    <input type="hidden" name="method" id="method" value="selectCvStyle" />
				    
				    <ul>
				    	<li><label for="estiloCV"><%=strEstiloClasico%></label><br/>
					    		<img src="images/cv_elegante.jpg" width="69" height="94" /><br/>	    	       		
					    		<input type="radio" id="estiloCV" name="estiloCV" <%=strEstiloClasicoChecked%> value="<%=intEstiloClasico%>"  /></li>
				    	<li><label for="estiloCV"><%=strEstiloSimple%></label><br/>  
					    	    <img src="images/cv_clasico.jpg" width="69" height="94" /><br/>	       
					    		<input type="radio" id="estiloCV" name="estiloCV" <%=strEstiloSimpleChecked%> value="<%=intEstiloSimple%>"  /></li>
				    	<li><label for="estiloCV"><%=strEstiloModerno%></label><br/>
					    		<img src="images/cv_moderno.jpg" width="69" height="94" /><br/>
					    		<input type="radio" id="estiloCV" name="estiloCV" <%=strEstiloModernoChecked%> value="<%=intEstiloModerno%>" /></li>
				    </ul>
				    <div class="clearfix"></div>

			</fieldset>

			<div class="form_nav">
				<input type="button" id="btnSelectCvStyle" value="Guardar" class="boton" onclick="doSubmitEstiloCv('selectCvStyle');" />
				<input type="button" id="btnRegresar" value="${labelBoton}" class="boton" onclick="javascript:regrsarEspacioCandidato();" />
			</div>
			
<!-- 						<div class="entero"> -->
<%-- 							<html:messages id="errors"> --%>
<%-- 							<span class="redFont Font80"><bean:write name="errors"/></span><br/> --%>
<%-- 							</html:messages> --%>
<%-- 							<html:messages id="messages" message="true"> --%>
<%-- 							<span class="Font80 clean-gray"><bean:write name="messages"/></span><br/> --%>
<%-- 							</html:messages>	 --%>
<!-- 						</div> -->
<!-- 						 <div id="message" ></div>  -->
</div>

</form>
</div>   
</body>
</html>

<% 
	if (null != request.getSession().getAttribute("modal")) {
		out.println(request.getSession().getAttribute("modal"));
		request.getSession().setAttribute("modal", null);
	}
%>