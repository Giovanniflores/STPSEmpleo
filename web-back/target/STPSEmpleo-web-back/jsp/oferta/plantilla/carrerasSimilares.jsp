
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
	document.carrerasFormBean.method.value=method;
	document.carrerasFormBean.submit();
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


 <form name="carrerasFormBean" action="ofertaEdicionCarreras.do" method="post" >
  <input type="hidden" name="method" value="experienciaInit"/>
  <input type="hidden" name="carrera" value="inicio"/>

 <div id="overPanel">

<div id="cuerpo_pop" class="entrevistaProgramar">
<h3><span>Oferta de empleo</span><br />
<strong class="empresa">Carreras similares.</strong></h3>


<div class="campo_pop derecha2">

</div>
</div>
</div>
 
	
    <div>
    
  
       <% 
       ArrayList<ElementoListaVO> listaCarreras=(ArrayList<ElementoListaVO>) (request.getSession().getAttribute("listaCarreras")!=null?request.getSession().getAttribute("listaCarreras"):new ArrayList<ElementoListaVO>());
       ElementoListaVO item=null;
       String nombreCheck,descripcion;
       for(int i=0;i<listaCarreras.size();i++){
    	   item=listaCarreras.get(i);
    	   nombreCheck="c"+(i+1);
    	   descripcion=(String)item.getDescripcion();
    	 
       %>
       <div>
       <p>
       
       <span><input type="checkbox" name=<%=nombreCheck%> <%=item.getSeleccionada()!=null?"checked":"" %> />   </span>
       <span><%=descripcion%></span>
       </p> 
       </div>
       <%} %> 
    <div>
    
    
    </div>
    
    <div>
    <br><br>
	 <span>
     
	 <input type="button" value="Guardar" class="boton" onclick="javascript:this.disabled=true;doSubmit('registrarCarrerasSimilares');cerrarse();"/>
     <input type="button" value="Cancelar" class="boton" onClick="cancelaRegistro();"/>
     <input type="button" value="Cerrar ventana" class="boton" onclick="javascript:this.disabled=true;cerrarse();" />
     </span>
     </div>
    
    
  

    
    
    </div>
    </form>
    </body>
</html>
