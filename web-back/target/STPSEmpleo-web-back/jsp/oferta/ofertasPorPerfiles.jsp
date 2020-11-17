<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="mx.gob.stps.portal.web.candidate.vo.CandidatoAjaxVO, mx.gob.stps.portal.core.oferta.busqueda.vo.OfertaPorPerfilVO, java.util.ArrayList"%>

<%String context = request.getContextPath() +"/";%>
<%CandidatoAjaxVO candidato= (CandidatoAjaxVO)request.getSession().getAttribute("candidatoheader");%>
<%ArrayList<OfertaPorPerfilVO> ofertas= (ArrayList<OfertaPorPerfilVO>)request.getSession().getAttribute("FULL_LIST");%>


<script src="dojotoolkit/dojo/dojo.js" djConfig="parseOnLoad: true, locale:'es'"></script>
<script type="text/javascript">
	dojo.require("dijit.dijit"); // loads the optimized dijit layer
	dojo.require("dijit.form.Form");
	dojo.require("dijit.Dialog");
	dojo.require("dijit.form.Button");

	function doSubmitPager(method){
		
	  dojo.byId('method').value = method;
	  dojo.byId("tipoConsulta").value = 1;
	  dojo.xhrPost({url: 'ofertasPerfiles.do', form:'OfertasPorPerfilForm', timeout:180000, 
				  load: function(data){
					    dojo.byId('divOffers').innerHTML=data;
					    dojo.addOnLoad(init);
				  }});
  }
	
  
  var init = function() {
	  <%if ((candidato.getPpcEstatusIdOpcion().equals("59") || candidato.getPpcEstatusIdOpcion().equals("60")) && ofertas.size() > 0)  //inscritos al PPC
		{	  %>
		  dijit.byId('dialogoPPC').show();	
		<% }%>
	}
  
	
 function mostrarResumen(idResumen) {

	var thisRow = dojo.style(idResumen, "display");
	
	if(thisRow == 'block'){
		dojo.style(idResumen, "display", "none");
		dojo.style(eval("\"" + "hide" + idResumen + "\""), "display", "none");
		dojo.style(eval("\"" + "show" + idResumen + "\""), "display", "block");
	}
		
	if(thisRow == 'none'){
		dojo.style(idResumen, "display", "block");
		dojo.style(eval("\"" + "hide" + idResumen + "\""), "display", "block");
		dojo.style(eval("\"" + "show" + idResumen + "\""), "display", "none");
	}
 }

 function mostrarTodas(tipo, totReg) {

	if(tipo == 1){
	
	    for(var i = 1; i <= totReg; i++){
    	
    	    dojo.style("hideAll", "display", "block");
    	    dojo.style("showAll", "display", "none");
			
			dojo.style(eval("\"" + i + "\""), "display", "block");
			dojo.style(eval("\"" + "hide" + i + "\""), "display", "block");
			dojo.style(eval("\"" + "show" + i + "\""), "display", "none");
		}
	}

	if(tipo == 2){
	
    	for(var i = 1; i <= totReg; i++){

    	    dojo.style("hideAll", "display", "none");
    	    dojo.style("showAll", "display", "block");

			dojo.style(eval("\"" + i + "\""), "display", "none");
			dojo.style(eval("\"" + "hide" + i + "\""), "display", "none");
			dojo.style(eval("\"" + "show" + i + "\""), "display", "block");
		}
	}

}


	//Evalua que pager debe ser llamado segun el diferenciador
	function doSubmitPagina(pagina){	 	
	 	dojo.byId('method').value = "goToPage";
		dojo.byId('goToPageNumber').value = pagina;
	   	dojo.xhrPost({url: 'ofertasPerfiles.do', form:'OfertasPorPerfilForm', timeout:180000, 
					  load: function(dataCand){
					      dojo.byId('divOffers').innerHTML=dataCand;
					  }});
	}
	
	
	function closeDialog(){
        dijit.byId('dialogoPPC').hide();           
  	} 	
	
	/* Ordena tabla por columna */
    function orderBy(orden,tipocolumna){	
    	 dojo.xhrPost({url: 'ofertasPerfiles.do?method=ordenarOfertas&tipoOrden=' + orden + '&tipoColumna=' + tipocolumna, timeout:180000,
         	load: function(data){
 			    dojo.byId('divOffers').innerHTML=data;
 			   if(tipocolumna == 'puesto'){
                   if (orden == 'asc') {
                       document.getElementById("puesto_orden_asc").style.display = 'none';
                       document.getElementById("puesto_orden_desc").style.display = 'inline';
                   } else if (orden == 'desc') {
                	   document.getElementById("puesto_orden_asc").style.display = 'inline';
                       document.getElementById("puesto_orden_desc").style.display = 'none';
                   }
               } else if(tipocolumna == 'empresa'){
                   if (orden == 'asc') {
                       document.getElementById("empresa_orden_asc").style.display = 'none';
                       document.getElementById("empresa_orden_desc").style.display = 'inline';
                   } else if (orden == 'desc') {
                       document.getElementById("empresa_orden_asc").style.display = 'inline';
                       document.getElementById("empresa_orden_desc").style.display = 'none';
                   }
               } else if(tipocolumna == 'ubicacion'){
                   if (orden == 'asc') {
                       document.getElementById("ubicacion_orden_asc").style.display = 'none';
                       document.getElementById("ubicacion_orden_desc").style.display = 'inline';
                   } else if (orden == 'desc') {
                	   document.getElementById("ubicacion_orden_asc").style.display = 'inline';
                       document.getElementById("ubicacion_orden_desc").style.display = 'none';
                   }
               } else if(tipocolumna == 'salario'){
                   if (orden == 'asc') {
                       document.getElementById("salario_orden_asc").style.display = 'none';
                       document.getElementById("salario_orden_desc").style.display = 'inline';
                   } else if (orden == 'desc') {
                       document.getElementById("salario_orden_asc").style.display = 'inline';
                       document.getElementById("salario_orden_desc").style.display = 'none';
                   }
               }  
  				  }});
    }	
	

</script>
<noscript>Funciones de JavaScript desactivadas por navegador</noscript>

<div class="busquedaPorPerfil">
	<div id="mis_ofertas">
		<c:choose>
			<c:when test="${not empty showMsg}">
				<div dojoType="dijit.Dialog" id="dialogoPPC" title="Aviso" style="display: none; width:400px"
					draggable="false">

			      <div class="dialog">
						<p class="margin_top_10">
							Se encontraron ${OfertasPorPerfilForm.ofertas.size()} ofertas de empleo de acuerdo
								a tu perfil.<br><br>
							<!--INI_JGLC_PPC-->	
<!-- 					<strong>Para que continúes con el Programa de Promoción -->
<!-- 								y Colocación del Seguro de Desempleo (PPC-SD), debes postularte -->
<!-- 								al menos a una oferta.</strong> -->
								<!--FIN_JGLC_PPC-->
						</p>
						<p class="form_nav">
							<input type="button" class="boton" value="Aceptar"
								onclick="closeDialog();">
						</p>
			      </div>
		       </div>
			</c:when>
		</c:choose>	

		<div id="divOffers" name="divOffers"></div>

	</div>
</div>


	<form name="OfertasPorPerfilForm" id="OfertasPorPerfilForm" action="ofertasPerfiles.do" method="post" dojoType="dijit.form.Form">		
		<input type="hidden" name="method" id="method" value="init" />
		<input type="hidden" name="goToPageNumber" id="goToPageNumber" value="1" />
				
		<input type="hidden" name="tipoConsulta" id="tipoConsulta" value="1" />
	</form>

<!-- 
		<p align="center" >
			<a href="javascript:doSubmitPager('prev')">&lt;&lt;&nbsp;Anterior</a>
			&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="javascript:doSubmitPager('next')">Siguiente&nbsp;&gt;&gt;</a>
		</p>
 -->


<script>
	doSubmitPager('page');
</script>
		
	