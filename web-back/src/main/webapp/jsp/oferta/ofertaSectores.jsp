
<%@page import="mx.gob.stps.portal.web.oferta.vo.ElementoListaVO"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<link href="${PATH_CSS_SWB_APP}estilos_lightBox.css" rel="stylesheet" type="text/css" />
<link href="${PATH_CSS_SWB_APP}estilos_mi_espacio.css" rel="stylesheet" type="text/css" />

<style type="text/css">
	@import "dojotoolkit/dojo/resources/dojo.css";
	@import "dojotoolkit/dijit/themes/claro/claro.css";
</style> 

<script type="text/javascript">

function doSubmit(method){
	document.sectoresFormBean.method.value=method;
	document.sectoresFormBean.submit();
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
<noscript>Funciones de JavaScript desactivadas por navegador</noscript>

  
<body class="claro">


 <form name="sectoresFormBean" action="ofertaEdicionSectores.do" method="post" >
  <input type="hidden" name="method" value="experienciaInit"/>
  <input type="hidden" name="sector" value="inicio"/>

 
<div id="overPanel">

<div id="cuerpo_pop" class="entrevistaProgramar">
<h3><span>Oferta de empleo</span><br />
<strong class="empresa">Me interesan los candidatos de los siguientes sectores</strong></h3>


<div class="campo_pop derecha2">

</div>
</div>
</div>
 
   
    <div>
    
  
       <% 
       ArrayList<ElementoListaVO> listaSectores=(ArrayList<ElementoListaVO>) (request.getSession().getAttribute("listaSectoresVO")!=null?request.getSession().getAttribute("listaSectoresVO"):new ArrayList<ElementoListaVO>());
       ElementoListaVO item=null;
       String nombreCheck,descripcion;
       for(int i=0;i<listaSectores.size();i++){
    	   item=listaSectores.get(i);
    	   nombreCheck="c"+i;
    	   descripcion=item.getDescripcion();
    	 
       %>
       <div>
       <p>
       
       <span><input type="checkbox" name=<%=nombreCheck%> <%=item.getSeleccionada()!=null?" checked ":" " %> />   </span>
       <span><%=descripcion%></span>
       </p> 
       </div>
       <%} %> 

    
    </div>
    <br><br>
    
    <div>
	 <table align="center">
     <tr>
     <td>
	 <input type="button" value="Guardar" class="boton" onclick="javascript:this.disabled=true;doSubmit('registrarSectores');cerrarse();"/>
	 </td>
	 <td>
	 <input type="button" value="Cancelar" class="boton" onClick="cancelaRegistro();"/> 
     </td>
     <td>
     <input type="button" value="Cerrar ventana" class="boton" onclick="javascript:this.disabled=true;cerrarse();" />
     </td>
     </tr>
     </table>
     </div>
    
    
  

    
    
    </form>
    </body>
</html>
