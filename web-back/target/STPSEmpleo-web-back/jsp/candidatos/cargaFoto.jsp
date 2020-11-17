<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
    String context = request.getContextPath() +"/";
    pageContext.setAttribute("context", context);
%>

<style type="text/css">
	.redFont{
		color: #FF0000;
	}
	.Font80{
		font-size: 80%;
	}
</style>
<script type="text/javascript" src="js/helper/messageHelper.js"></script>
<script type="text/javascript">
    function valida(forma) {
    	if (forma.archivoFoto.value != "") {
    		var filename = forma.archivoFoto.value.toLowerCase();
    		if (filename.indexOf(".") > -1) {
    			var fileext = filename.substring(filename.lastIndexOf(".") + 1);
    			if (fileext != "gif" && fileext != "jpg" && fileext != "jpeg") {
    				message('El tipo de archivo que contiene la fotografía'
                            + ' debe ser GIF, JPG o JPEG. Intente nuevamente con'
                            + ' otro archivo.');
    				forma.archivoFoto.value = "";
    	            return false;
    			} else {
    			 	forma.method.value = 'registrar';
    				forma.submit();
    			}
    		} 
    	} else {
    		message('Debe seleccionar un archivo con extensión: .gif, .jpg o .jpeg');
    		return false;
    	}
    }
    function cancela(forma) {
    	forma.method.value = 'cancelar';
    	forma.submit();
    	//self.close();
    }
</script>

<form id="fotoCargaForm" action="${pageContext.request.contextPath}/cargaFoto.do" enctype="multipart/form-data" method="post">		 
    <input type="hidden" name="method" value="registrar"/>







<div class="miEspacio formApp">
		<h2>Mi espacio</h2>





			<div class="tab_block">
				<div align="left" style="display:block;" id="returnME">
					<a class="expand" href="javascript:location.replace('<c:url value="/miEspacioCandidato.do"/>');">
						<strong>Ir al inicio de Mi espacio</strong>
					</a>
				</div>
				<div class="Tab_espacio">
					<h3>Cambiar fotografía</h3>
					<div class="subTab">
						<ul>
							<li><a href="<c:url value="/perfil.do?method=init"/>">Editar mi perfil</a></li>
							<li class="curSubTab"><span>Cambiar fotografía</span></li>
						</ul>
						<div class="clearfix"></div>
					</div>
				</div>
			</div>
			<fieldset class="field_curp">
				<legend>Cambiar fotografía</legend>
				
				<p>El archivo debe ser de tipo GIF, JPG o JPEG con un tamaño no mayor a 30KB y se recomienda que su ancho y altura no excedan los 90 pixeles.</p>    	    	
		        <p>
		          <label for="archivoFoto"><span>*</span> Archivo con fotograf&iacute;a</label>
		          <input type="file" name="archivoFoto" style="width:500px;" id=archivoFoto></input>         
		        </p>
		        
				 <!-- div class="entero" >
					<html:messages id="errors">
						<div class="gris">
					    	<span class="redFont" ><bean:write name="errors"/></span><br/>
					    </div>
					</html:messages>
					
					<html:messages id="messages" message="true">
						<div class="gris">
					    	<span><bean:write name="messages"/></span><br/>
					    </div>
					</html:messages>   
				 </div --> 
				 
			</fieldset>
			

			<div class="form_nav">
				<input type="button" id="btnEnviar" value="Guardar" class="boton" onclick="javascript:valida(this.form);"/>
				<%--<input type="button" id="btnCancel" value="${labelBoton}" class="boton" onclick="javascript:cancela(this.form);"/>--%>
                <input type="button" id="btnEnviar" value="Regresar" class="boton" onclick="javascript:window.location.href = '<c:url value="/miEspacioCandidato.do"/>';"/>
			</div>

</div>
</form>
<% 
	if (null != request.getSession().getAttribute("modal")) {
		out.println(request.getSession().getAttribute("modal"));
		request.getSession().setAttribute("modal", null);
	}
%>