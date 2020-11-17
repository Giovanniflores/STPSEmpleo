<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script src="dojotoolkit/dojo/dojo.js" djConfig="parseOnLoad: true, locale:'es'"></script>
<jsp:useBean id="ofertaVO" scope="session" class="mx.gob.stps.portal.core.oferta.vo.OfertaEmpleoVO" /> 
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

    function doSubmitTitulo(method){
    
    	if(!dijit.byId('titulo').isValid()){
    		dijit.byId('titulo').focus();
    		mensaje("El siguiente campo muestra datos invalidos: <br><strong>Título de la oferta</strong> ");								
    	return false;
    	}
    	
    	document.recuperaOfertasFormBean.method.value=method;
    	document.recuperaOfertasFormBean.submit();
    	return true;
    }
    
    function doSubmitFolio(method){
    	
    	if(!dijit.byId('folio').isValid()){
    		dijit.byId('folio').focus();
    		mensaje("El siguiente campo muestra datos invalidos: <br><strong>Folio</strong> ");								
    	return false;
    	}
    	document.recuperaOfertasFormBean.method.value=method;
    	document.recuperaOfertasFormBean.submit();
    	return true;
    }
    
    function doSubmitFecha(method){
    	
    	if(!dijit.byId('fechaDe').isValid()){
    		dijit.byId('fechaDe').focus();
    		mensaje("El siguiente campo muestra datos invalidos: <br><strong>Fecha inicial<strong> ");								
    	return false;
    	}
    	if(!dijit.byId('fechaA').isValid()){
    		dijit.byId('fechaA').focus();
    		mensaje("El siguiente campo muestra datos invalidos: <br><strong>Fecha final</strong> ");								
    	return false;
    	}
    	
    	document.recuperaOfertasFormBean.method.value=method;
    	document.recuperaOfertasFormBean.submit();
    	return true;
    }

function cerrarse(){ 
window.close();
} 

function doSiguiente(accion){
	document.recuperaOfertasFormBean.siguienteP.value='true';
	document.recuperaOfertasFormBean.action=accion;
	document.recuperaOfertasFormBean.submit();
}
function doAtras(accion){
	document.recuperaOfertasFormBean.atrasP.value='true';
	document.recuperaOfertasFormBean.action=accion;
	document.recuperaOfertasFormBean.submit();
} 

function mensaje(mensaje){
	dojo.byId('mensaje').innerHTML = mensaje;
	dijit.byId('MensajeAlert').show();		
}

function doSubmitPager(method) {
 dojo.xhrPost({url: 'RecuperaOfertas.do?method='+method, timeout:180000,
                  load: function(data) {
                        dojo.byId('reporte').innerHTML = data;
                  }});
}


function doSubmitPagina(pagina, dif){
	
   	dojo.xhrPost({url: 'RecuperaOfertas.do?method=goToPage&goToPageNumber='+pagina, timeout:180000, 
				  load: function(dataCand){
				      dojo.byId('reporte').innerHTML=dataCand;
				  }});
}

</script>
<noscript>Funciones de JavaScript desactivadas por navegador</noscript>

<div class="recuperacion_oferta">

	<div class="tab_block">
		<div align="left" id="returnME" style="display:block;">
			<a href="javascript:location.replace('<c:url value="/miEspacioEmpresas.do"/>');" class="expand">
				<strong>Ir al inicio de Mi espacio</strong>
			</a>
		</div>
		<div class="Tab_espacio">
			<h3>Administrar mis ofertas de empleo</h3>
			<div class="subTab">
				<div class="nav_miPerfil">
					<ul>
						<li><a href="<c:url value="/OfertaNavegacion.do?method=init"/>">Ver mis ofertas de empleo</a></li>
						<li><a href="<c:url value="/dondePublicar.do?method=init"/>">Crear oferta de empleo</a></li>	
						<li class="curSubTab"><span>Recuperar oferta de empleo</span></li>
					</ul>
				</div>
				<div class="clearfix"></div>
			</div>
			<div class="clearfix"></div>
		</div>
	</div>

<form name="recuperaOfertasFormBean" action="RecuperaOfertas.do" method="post" >
 <input type="hidden" name="method" value="init"/>
 <input type="hidden" name="atrasP" value="false" />
 <input type="hidden" name="siguienteP" value="false" />
 
 
 <fieldset>
 	<legend>Recuperación de ofertas eliminadas</legend>


	<div class="grid1_3 fl margin_top_10">
		<label for="folio">Número de folio</label>
	 	<input id="folio" name="folio" maxlength="10" size="10"	
			   dojoType="dijit.form.ValidationTextBox" regExp="^[0-9]{1,10}$"
			   invalidMessage="Dato Invalido" missingMessage="Dato requerido" trim="true"  required="true"/>
	</div> 
	<input type="button" value="Buscar" name="buscatFolio" class="fl margin_top_40" onclick="doSubmitFolio('busquedaFolio');"/>
	<div class="clearfix"></div>
	<div class="grid1_3 fl margin_top_10">
		<label for="titulo">Título de la oferta</label>
		<input id="titulo" name="titulo" maxlength="150" size="25" dojoType="dijit.form.ValidationTextBox" regExp="^[\w\D\s\.áéíóúÁÉÍÓÚñÑ]{5,150}$" required="true" 
        	   invalidMessage="Dato Invalido"  missingMessage="Dato requerido"/>
    </div>
    <input type="button" value="Buscar" name="buscatTitulo" class="fl margin_top_40"  onclick="doSubmitTitulo('busquedaTitulo');"/>
    <div class="clearfix"></div>
	<div class="grid1_3 margin_top_10 fl">
		<div class="labeled">Fecha de modificación oferta</div>
		<div class="fl margin-r_20">
			<label for="fechaDe">Inicial</label>
			<input type="text" style="width:134px" name="fechaDe" id="fechaDe" dojoType="dijit.form.DateTextBox" maxlength="10"  
			       constraints={datePattern:'dd/MM/yyyy'} required="true"
			       onChange="dijit.byId('fechaA').constraints.min = arguments[0]; dijit.byId('fechaA').disabled=false;"/>
		</div>
		<div class="fl">
			<label for="fechaA">Final</label>
			<input name="fechaA" style="width:134px" id="fechaA" dojoType="dijit.form.DateTextBox" maxlength="10" constraints={datePattern:'dd/MM/yyyy'} required="true"/>
		</div>
	</div>
	<div class="fl margin_top_40">
		<input type="button" value="Buscar" name="buscatFecha" class="margin_top_50" onclick="doSubmitFecha('busquedaFecha');"/>
	</div>
	<div class="clearfix"></div>


    <div class="entero"></div>

  
<div class="un_tercio"></div>

<div class="un_medio" >
    <div id="reporte"></div>	
</div>

<div class="un_tercio"></div>
  
<div dojoType="dijit.Dialog" id="MensajeAlert" title="Mensaje" style="display:none">
	<div class="msg_contain" id ="mensaje"></div>                  
                            
</div>
 </fieldset>   
    </form>
 
</div>

    <c:if test="${empty requestScope['init']}">
	    <script>
	    	doSubmitPager('page');
		</script>
	</c:if>

<c:if test="${not empty sessionScope.errorFecha}">
	<script type="text/javascript">
		alert('La fecha final no puede ser menor a la inicial');
	</script> 
	<c:set var="errorFecha" scope="session" value="${null}"/>
</c:if>

 
 
