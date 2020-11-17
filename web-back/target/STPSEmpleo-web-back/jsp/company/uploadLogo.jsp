<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%String context = request.getContextPath() +"/";%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd"> 
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="es" lang="es"> 
	<head> 
		<script type="text/javascript" src="js/helper/messageHelper.js"></script>
    <script type="text/javascript">

    function valida(forma) {
   		
    	if (forma.logoFile.value != "") {
    		var filename = forma.logoFile.value.toLowerCase();    		
    		
    		if (filename.indexOf(".") > -1) {
    			var fileext = filename.substring(filename.lastIndexOf(".") + 1);
    			if (fileext != "gif" && fileext != "jpg" && fileext != "jpeg") {
    				mensaje('El tipo de archivo que contiene el logotipo'
                            + ' debe ser GIF, JPG o JPEG. Intente nuevamente con'
                            + ' otro archivo.');
    				forma.logoFile.value = "";
    	            return false;
    			} else {	
    			 	forma.method.value = 'registrar';
    		    	forma.submit();
   				}
    		} 
    	} else {
    		mensaje('Debe seleccionar un archivo con extensión: .gif, .jpg o .jpeg');
    		return false;
    	}
    }
    
    function cancela(forma) {
    	document.location.href = '<c:url value="/miEspacioEmpresas.do"/>';
    }
    </script>
<noscript>Funciones de JavaScript desactivadas por navegador</noscript>
</head>
<body class='claro'> 
<form action="uploadcompanylogo.do?method=registrar" enctype="multipart/form-data" method="post">		 
     <input type="hidden" name="method" value="registrar"/>
     
<div class="tab_block">
	<div align="left" id="returnME" style="display:block;">
		<a href="javascript:location.replace('<c:url value="/miEspacioEmpresas.do"/>');" class="expand">
			<strong>Ir al inicio de Mi espacio</strong>
		</a>
	</div>
	<div class="Tab_espacio">
		<h3>Datos de la empresa</h3>
		<div class="subTab">
			<ul>
				<li class="curSubTab">
					<span>Cambiar fotografía</span>
				</li>
				<li>
					<a href="<c:url value="/edicionEmpresa.do?method=init"/>">Editar mi perfil</a>
				</li>
			</ul>
			<div class="clearfix"></div>
		</div>
	</div>
</div>
    <div class="miEspacio formApp">
    	<fieldset class="field_curp">
    	<legend>Cambiar fotograf&iacute;a</legend>
    	<p>El archivo debe ser de tipo GIF, JPG o JPEG con un tamaño no mayor a 30KB y se recomienda que su ancho y altura no excedan los 90 pixeles.</p>   	    	
          <label for="logoFile"><span>*</span> Archivo con imagen</label>
          <input id="logoFile" type="file" name="logoFile"></input>  
        </fieldset>
        <div class="form_nav">
            <input type="button" id="btnEnviar" value="Guardar" class="boton" onclick="javascript:valida(this.form);"/>
            <input type="button" id="btnCancel" value="${labelBoton}" class="boton" onclick="javascript:cancela(this.form);"/> 
        </div>
        
		 <!--  div class="entero" >
			<html:messages id="errors">
			    <span class="Font80 red3Font"><bean:write name="errors"/></span><br/>
			</html:messages>
			
			<html:messages id="messages" message="true">
			    <span class="Font80 red3Font"><bean:write name="messages"/></span><br/>
			</html:messages>   
		 </div-->         
    </div>
</form>
<% 
	if (null != request.getSession().getAttribute("modal"))
		out.println(request.getSession().getAttribute("modal"));
%>
</body>
</html>