<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">  -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="mx.gob.stps.portal.core.oferta.registro.vo.RequisitoVO"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="mx.gob.stps.portal.persistencia.vo.CatalogoOpcionVO"%>
<%@ page import="java.util.List"%>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="es" lang="es">

<style type="text/css">
	@import "dojotoolkit/dojo/resources/dojo.css";
	@import "dojotoolkit/dijit/themes/claro/claro.css";
</style> 

<link href="${PATH_CSS_SWB_APP}estilos_lightBox.css" rel="stylesheet" type="text/css" />
<link href="${PATH_CSS_SWB_APP}estilos_mi_espacio.css" rel="stylesheet" type="text/css" />

<link href='https://fonts.googleapis.com/css?family=Droid+Sans' rel='stylesheet' type='text/css' />

</head>

<script src="dojotoolkit/dojo/dojo.js" djConfig="parseOnLoad: true, locale:'es'"></script>

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
</script>

<% 
List<CatalogoOpcionVO> listaDominio= (List<CatalogoOpcionVO>)request.getSession().getAttribute("CATALOGO_OPCION_DOMINIO");	
List<CatalogoOpcionVO> listaExperiencia= (List<CatalogoOpcionVO>)request.getSession().getAttribute("CATALOGO_OPCION_EXPERIENCIA");	
%>

<script type="text/javascript">

function doSubmit(method){
	document.requisitosFormBean.method.value=method;
	
	if(validaDatos()){
		document.requisitosFormBean.submit();
		return true;
	   }
	else
		return false;
	
}

function cerrarse(){ 
window.close();
} 

function cancelaRegistro() {

	if(confirm("Los datos no guardados se perderán ¿Continuar?")) {
		window.close();
	} 
}

function validaDatos(){       
 	
	if(document.requisitosFormBean.conocimiento.value != "" ){
		if(document.requisitosFormBean.experiencia.value=="-1")
		 {  document.requisitosFormBean.experiencia.focus();
  			mensaje("El siguiente dato muestra datos invalidos: Experiencia.");
  			return false;
		 }
		if(document.requisitosFormBean.dominio.value=="-1")
		 {  document.requisitosFormBean.dominio.focus();
  			mensaje("El siguiente dato muestra datos invalidos: Dominio.");
  			return false;
		 }
	}
	return true;
}


function mensaje(mensaje){
	dojo.byId('mensaje').innerHTML = mensaje;
	dijit.byId('MensajeAlert').show();		
}



</script>
<noscript>Funciones de javascript desactivadas por el navegador</noscript>
  
<body class="claro">

 <form name="requisitosFormBean" action="ofertaEdicionExperiencia.do" method="post" >
 <input type="hidden" name="method" value="experienciaInit"/>
 
   
     <div id="overPanel">

<div id="cuerpo_pop" class="entrevistaProgramar">
<h3><span>Oferta de empleo</span><br />
<strong class="empresa">Competencias gerenciales, experiencia específica de acuerdo al área laboral</strong></h3>


<div class="campo_pop derecha2">

</div>
</div>
</div>
     
	
	<span><label for="conocimiento">Conocimiento</label>: <input type ="text" name="conocimiento" id="conocimiento" maxlength="50" size="50" /></span>
	<span><label for="experiencia">Experiencia</label>: <select name="experiencia" id="experiencia" >
	<option value="-1">Seleccione </option>
    <%for (CatalogoOpcionVO catalogoOpcionVO : listaExperiencia){%>
	<option value="<%=catalogoOpcionVO.getIdCatalogoOpcion() %>"><%=catalogoOpcionVO.getOpcion() %></option>
    <%}%>  
    </select></span>
	<span><label for="dominio">Dominio</label>:<select name="dominio" id="dominio" >
	<option value="-1">Seleccione </option>
    <%for (CatalogoOpcionVO catalogoOpcionVO : listaDominio){%>
	<option value="<%=catalogoOpcionVO.getIdCatalogoOpcion() %>"><%=catalogoOpcionVO.getOpcion() %></option>
    <%}%>  
	</select></span>
	
  <br><br><br>
    
  
       <table align="center"><tr>
       <th><strong>Eliminar/</strong></th>
       <th><strong>Conocimiento/</strong></th>
       <th><strong>Dominio/</strong></th>
       <th><strong>Experiencia</strong></th>
       
       </tr>
      
       <% 
       ArrayList<RequisitoVO> lista=(ArrayList<RequisitoVO>) (request.getSession().getAttribute("listaCompetencias")!=null?request.getSession().getAttribute("listaCompetencias"):new ArrayList<RequisitoVO>());
       RequisitoVO item=null;
       String nombreCheck;
       for(int i=0;i<lista.size();i++){
    	   item=lista.get(i);
    	   nombreCheck="elim"+(i+1);
    	 
       %>
      
      <tr>
       <td><input type=checkbox name=<%=nombreCheck%>></td>
       <td><%=item.getDescripcion() %></td>
       <td><%=item.getDomDescripcion() %></td>
       <td><%=item.getExpDescripcion() %></td>
     
      </tr>
       <%} %> 
    </table>
    
    
   <br><br>
    
   
	<table align="center">
	<tr>
	<td>
     <input type="button" value="Guardar" class="boton" onclick="doSubmit('registrarCompetencias');"/>
     </td>
     <td>
     <input type="button" value="Cancelar" class="boton" onClick="cancelaRegistro();"/>
     </td>
     <td>
     <input type="button" value="Cerrar ventana" class="boton" onclick="javascript:this.disabled=true;cerrarse();" />
	 </td>
	</tr>
	</table>
   
    
    
    <div dojoType="dijit.Dialog" id="MensajeAlert" title="Mensaje" style="display:none">	
			 <table width="300px">
			 <tr align="center">
				 <td><div id ="mensaje"></div></td>&nbsp;				 
			</tr>
		 </table>	
	</div>
    
   </form>
    </body>
</html>
