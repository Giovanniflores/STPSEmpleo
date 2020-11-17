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

<script src="dojotoolkit/dojo/dojo.js" djConfig="parseOnLoad: true, locale:'es'"></script>

<style type="text/css">
	@import "dojotoolkit/dojo/resources/dojo.css";
	@import "dojotoolkit/dijit/themes/claro/claro.css";
</style> 

<link href="${PATH_CSS_SWB_APP}estilos_empleo.css" rel="stylesheet" type="text/css" />
<link href="${PATH_CSS_SWB_APP}estilos_canal.css" rel="stylesheet" type="text/css" />
<link href="${PATH_CSS_SWB_APP}estilos_mi_espacio.css" rel="stylesheet" type="text/css" />
<link href="${PATH_CSS_SWB_APP}estilos_lightBox.css" rel="stylesheet" type="text/css" />
<link href="${PATH_CSS_SWB}css_aplicacion/estilos_tabla.css" rel="stylesheet" type="text/css" />
<link href="${PATH_CSS_SWB_APP}estilos_genericos.css" rel="stylesheet" type="text/css" />

<link href='http://fonts.googleapis.com/css?family=Droid+Sans' rel='stylesheet' type='text/css' />

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


<% 
List<CatalogoOpcionVO> listaDominio= (List<CatalogoOpcionVO>)request.getSession().getAttribute("CATALOGO_OPCION_DOMINIO");	
List<CatalogoOpcionVO> listaIdiomas= (List<CatalogoOpcionVO>)request.getSession().getAttribute("CATALOGO_OPCION_IDIOMA");



%>




<script type="text/javascript">

function doSubmit(method){
	document.requisitosFormBean.method.value=method;
	document.requisitosFormBean.submit();
}

function cerrarse(){ 
window.close();
} 

function cancelaRegistro() {

	if(confirm("Los datos no guardados se perderán ¿Continuar?")) {
		window.close();
	} 
}
</script> 


</script>

  

<body class="claro">

 <form name="requisitosFormBean" action="ofertaEdicionExperiencia.do" method="post" >
  <input type="hidden" name="method" value="experienciaInit"/>

 
    <div>
    <p>
     <span>
     Competencias gerenciales Idioma.
    </p>
    </div>
	
	
	
	<p class="entero">
		<strong>Idioma:</strong>
				<input type="hidden" name="conocimiento" id="conocimiento" value="-1" />
				<div dojoType="dojo.data.ItemFileReadStore" jsId="idiomaStore" urlPreventCache="true" clearOnClose="true" ></div>
				<select dojotype="dijit.form.FilteringSelect" store="idiomaStore" id="idiomaSelect" required="true" disabled="true" autoComplete="false"></select>
        </p>

        <p class="entero">
        <strong>Certificación</strong>
				<input type="hidden" name="experiencia" id="experiencia" value="-1" />
				<div dojoType="dojo.data.ItemFileReadStore" jsId="certificacionStore" urlPreventCache="true" clearOnClose="true" ></div>
				<select dojotype="dijit.form.FilteringSelect" store="certificacionStore" id="certificacionSelect" required="true" disabled="true" autoComplete="false"></select>
        </p>
	
	
	
	
	<span>Dominio:<select name="dominio" id="dominio" >
	<option value="-1">Seleccione </option>
    <%for (CatalogoOpcionVO catalogoOpcionVO : listaDominio){%>
	<option value="<%=catalogoOpcionVO.getIdCatalogoOpcion() %>"><%=catalogoOpcionVO.getOpcion() %></option>
    <%}%> 
	</select></span>
	
    <div>
    
    <div>
       <p>
       <span>Idioma</span>
       <span>Certificación</span>
       <span>Dominio</span>
       <span>Eliminar</span>
       </p> 
       </div>
    
       <% 
       ArrayList<RegistroIdiomaVO> lista=(ArrayList<RegistroIdiomaVO>) (request.getSession().getAttribute("listaIdiomas")!=null?request.getSession().getAttribute("listaIdiomas"):new ArrayList<RegistroIdiomaVO>());
       RegistroIdiomaVO item=null;
       String nombreCheck;
       for(int i=0;i<lista.size();i++){
    	   item=lista.get(i);
    	   nombreCheck="elim"+(i+1);
    	 
       %>
       <div>
       <p>
       <span><input type=checkbox name=<%=nombreCheck%>></span>
       <span><%=item.getIdiomaDescripcion() %>,</span>
       <span><%=item.getDominioDescripcion() %>,</span>
       <span><%=item.getCertificacionDescripcion() %></span>
       
       </p> 
       </div>
       <%} %> 
    <div>
    
    
    </div>
    
    <div>
	 <span>
     <input type="button" value="Guardar" class="boton" onclick="javascript:this.disabled=true;doSubmit('registrarIdiomas');"/>
     <input type="button" value="Cancelar" class="boton" onClick="cancelaRegistro();"/>
     <input type="button" value="Cerrar ventana" class="boton" onclick="javascript:this.disabled=true;cerrarse();" />
	 </span>
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
		
        
        document.requisitosFormBean.conocimiento.value=dijit.byId('idiomaSelect').get('value');
    });

	 dojo.connect(dijit.byId("certificacionSelect"), "onChange", function() {
         document.requisitosFormBean.experiencia.value=dijit.byId('certificacionSelect').get('value');
     });
	
	});     


</script>




