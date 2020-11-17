<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<%@ page import="mx.gob.stps.portal.core.oferta.registro.vo.OfertaCarreraEspecialidadVO"%>
<%@ page import="mx.gob.stps.portal.core.oferta.registro.vo.RegistroIdiomaVO"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="mx.gob.stps.portal.persistencia.vo.CatalogoOpcionVO"%>
<%@ page import="java.util.List"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
	@import "dojotoolkit/dojo/resources/dojo.css";
	@import "dojotoolkit/dijit/themes/claro/claro.css";
</style> 

<script src="dojotoolkit/dojo/dojo.js" djConfig="parseOnLoad: true, locale:'es'"></script>

<link href="${PATH_CSS_SWB_APP}estilos_lightBox.css" rel="stylesheet" type="text/css" />
<link href="${PATH_CSS_SWB_APP}estilos_mi_espacio.css" rel="stylesheet" type="text/css" />

<link href='https://fonts.googleapis.com/css?family=Droid+Sans' rel='stylesheet' type='text/css' />

</head>

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
<noscript>Funciones de JavaScript desactivadas por navegador</noscript>


<% 
List<CatalogoOpcionVO> listaDominio= (List<CatalogoOpcionVO>)request.getSession().getAttribute("CATALOGO_OPCION_DOMINIO");	
List<CatalogoOpcionVO> listaIdiomas= (List<CatalogoOpcionVO>)request.getSession().getAttribute("CATALOGO_OPCION_IDIOMA");



%>




<script type="text/javascript">


function doSubmit(method){
	if(validaDatos()){
		document.requisitosFormBean.method.value=method;
		document.requisitosFormBean.submit();
		return true;
	   }
	else
		return false;
	
}

function validaDatos(){
	
	if(dijit.byId('idiomaSelect').get('value')>0){
		if(!dijit.byId('certificacionSelect').get('value')>0){
			dijit.byId('certificacionSelect').focus();
			mensaje("El siguiente dato muestra datos invalidos: Certificación ");	
			return false;
		}
		if(document.requisitosFormBean.dominio.value<0){
			document.requisitosFormBean.dominio.focus();
			mensaje("El siguiente dato muestra datos invalidos: Dominio ");		
			return false;
		}
		}	
		
	return true;
	
}

function cerrarse(){ 
window.close();
} 

function cancelaRegistro() {

	if(confirm("Los datos no guardados se perderán ¿Continuar?")) {
		window.close();
	} 
}



function mensaje(mensaje){
	dojo.byId('mensaje').innerHTML = mensaje;
	dijit.byId('MensajeAlert').show();		
}


</script> 


</script>

  

<body class="claro">

 <form name="requisitosFormBean" action="ofertaEdicionExperiencia.do" method="post" >
  <input type="hidden" name="method" value="experienciaInit"/>



  <div id="overPanel">

<div id="cuerpo_pop" class="entrevistaProgramar">
<h3><span>Oferta de empleo</span><br />
<strong class="empresa"> Competencias gerenciales, idioma</strong></h3>


<div class="campo_pop derecha2">

</div>
</div>
</div>
     

	<table align="center">
	<tbody align="left">
	<tr>
	<td>
		<strong><label for="idiomaSelect">Idioma</label></strong>
				<input type="hidden" name="conocimiento" id="conocimiento" value="-1" />
				<div dojoType="dojo.data.ItemFileReadStore" jsId="idiomaStore" urlPreventCache="true" clearOnClose="true" ></div>
				<select dojotype="dijit.form.FilteringSelect" store="idiomaStore" id="idiomaSelect"  disabled="true" autoComplete="false"></select>
    </td><td> 
        <strong><label for="certificacionSelect">Certificación</label></strong>
				<input type="hidden" name="experiencia" id="experiencia" value="-1" />
				<div dojoType="dojo.data.ItemFileReadStore" jsId="certificacionStore" urlPreventCache="true" clearOnClose="true" ></div>
				<select dojotype="dijit.form.FilteringSelect" store="certificacionStore" id="certificacionSelect" disabled="true" autoComplete="false"></select>
   </td>
  <td>
	
	
	
   <strong><label for="dominio">Dominio</label></strong>
<select name="dominio" id="dominio" disabled>
	<option value="-1">Seleccione </option>
    <%for (CatalogoOpcionVO catalogoOpcionVO : listaDominio){%>
	<option value="<%=catalogoOpcionVO.getIdCatalogoOpcion() %>"><%=catalogoOpcionVO.getOpcion() %></option>
    <%}%> 
	</select>
	</td>
	</tr>
	</tbody>
    </table>
    
  <br><br>
  
  <table align="center">
    
    <tr >
       <th><strong>Eliminar/</strong></th>
       <th><strong>Idioma/</strong></th>
       <th><strong>Dominio/</strong></th>
       <th><strong>Certificación</strong></th>
      </tr>
      
      
      
    
       <% 
       ArrayList<RegistroIdiomaVO> lista=(ArrayList<RegistroIdiomaVO>) (request.getSession().getAttribute("listaIdiomas")!=null?request.getSession().getAttribute("listaIdiomas"):new ArrayList<RegistroIdiomaVO>());
       RegistroIdiomaVO item=null;
       String nombreCheck;
       for(int i=0;i<lista.size();i++){
    	   item=lista.get(i);
    	   nombreCheck="elim"+(i+1);
    	 
       %>
      <tr>
       <td><input type=checkbox name=<%=nombreCheck%>></td>
       <td><%=item.getIdiomaDescripcion() %></td>
       <td><%=item.getDominioDescripcion() %></td>
       <td><%=item.getCertificacionDescripcion() %></td>
       </tr>
      
       <%} %> 
  
	</table>
	<br><br>
	<table align="center">
	<tr>
	<td>
     <input type="button" value="Guardar" class="boton" onclick="doSubmit('registrarIdiomas');"/>
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
	
        <form>
    </body>
</html>


<script type="text/javascript">

dojo.addOnLoad(function() {
 
	dijit.byId('idiomaSelect').disabled=false;
	
	idiomaStore.url = "${context}ofertaEdicionExperiencia.do?method=obtenerIdioma";
	idiomaStore.close();          

	dojo.connect(dijit.byId("idiomaSelect"), "onChange", function() {

		dijit.byId('certificacionSelect').reset();

			var vIdioma = dijit.byId('idiomaSelect').get('value');
				
        	dijit.byId('certificacionSelect').disabled=false;
        		
        	certificacionStore.url = "${context}ofertaEdicionExperiencia.do?method=obtenerCertificacion" + "&" + "idioma="+ vIdioma;
        	certificacionStore.close();
		
        	document.requisitosFormBean.dominio.disabled=false;
        document.requisitosFormBean.conocimiento.value=dijit.byId('idiomaSelect').get('value');
    });

	 dojo.connect(dijit.byId("certificacionSelect"), "onChange", function() {
         document.requisitosFormBean.experiencia.value=dijit.byId('certificacionSelect').get('value');
     });
	
	});     


</script>




