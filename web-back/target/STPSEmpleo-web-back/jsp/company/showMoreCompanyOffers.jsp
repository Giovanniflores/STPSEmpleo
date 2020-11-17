<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="java.util.Date"%>
<%@ page import="mx.gob.stps.portal.persistencia.vo.CatalogoOpcionVO"%>
<%@ page import="mx.gob.stps.portal.core.oferta.busqueda.vo.OfertaPorCanalVO"%>
<%@ page import="mx.gob.stps.portal.core.infra.utils.Constantes"%>
<%@ page import="mx.gob.stps.portal.web.company.form.ShowMoreCompanyOffersForm"%>
<%@ page import="java.util.Calendar"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String context = request.getContextPath() +"/";  
	ShowMoreCompanyOffersForm formCompany = (ShowMoreCompanyOffersForm)session.getAttribute("ShowMoreCompanyOffersForm");
	String strNombreEmpresa  = formCompany.getNombreEmpresa();
	String strDescripcion = formCompany.getDescripcion();
	String strWebSite  = formCompany.getPaginaWeb();
	List<OfertaPorCanalVO> lstOfertas = formCompany.getLstOfertas();  
%>

<script src="dojotoolkit/dojo/dojo.js" djConfig="parseOnLoad: true, locale:'es'"></script>
<noscript>Funciones de JavaScript desactivadas por navegador</noscript>
<style type="text/css"> 
	@import "dojotoolkit/dojo/resources/dojo.css";
	@import "dojotoolkit/dijit/themes/claro/claro.css";
</style> 

<link href='https://fonts.googleapis.com/css?family=Droid+Sans' rel='stylesheet' type='text/css' />

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

<link href="${PATH_CSS_SWB}css_aplicacion/estilos_tabla.css" rel="stylesheet" type="text/css" /> 

<script>
    function callable(form, field) {
		selectCheck(form, form.allCheck.checked, field);
    }
	function selectCheck(form, obj, field) {
		var i=form.elements.length;
		for(var k=0;k<i;k++) {
			if(form.elements[k].name==field) {
				form.elements[k].checked=obj;
			}
		}
	}
  function mostrarResumen(idResumen) {
    var thisRow = dojo.style(idResumen, "display");
    
    if (thisRow == 'block') {
        dojo.style(idResumen, "display", "none");
        dojo.style(eval("\"" + "hide" + idResumen + "\""), "display", "none");
        dojo.style(eval("\"" + "show" + idResumen + "\""), "display", "block");
    }
        
    if (thisRow == 'none') {
        dojo.style(idResumen, "display", "block");
        dojo.style(eval("\"" + "hide" + idResumen + "\""), "display", "block");
        dojo.style(eval("\"" + "show" + idResumen + "\""), "display", "none");
    }
  }

  function mostrarTodas(tipo, totReg) {
    if (tipo == 1) {
        for (var i = 1; i <= totReg; i++) {
            dojo.style("hideAll", "display", "block");
            dojo.style("showAll", "display", "none");
            dojo.style(eval("\"" + i + "\""), "display", "block");
            dojo.style(eval("\"" + "hide" + i + "\""), "display", "block");
            dojo.style(eval("\"" + "show" + i + "\""), "display", "none");
        }
    }

    if (tipo == 2) {
        for (var i = 1; i <= totReg; i++) {
            dojo.style("hideAll", "display", "none");
            dojo.style("showAll", "display", "block");
            dojo.style(eval("\"" + i + "\""), "display", "none");
            dojo.style(eval("\"" + "hide" + i + "\""), "display", "none");
            dojo.style(eval("\"" + "show" + i + "\""), "display", "block");
        }
    }
  }
</script>

<script type="text/javascript">
	function doSubmitPager(method){
		dojo.byId('method').value = method;	 	  
		  dojo.xhrPost({url: 'showMoreCompanyOffers.do', form:'ShowMoreCompanyOffersForm', timeout:180000, 
			  load: function(data){
				    dojo.byId('tabla').innerHTML=data;
			  }});
	}
	function visitWebSite() {
		var answer = confirm("La liga se encuentra en un sitio externo ¿Continuar?");
		if (answer){
			var winCompanyWebSite = window.open("<%=strWebSite%>", "Website","height=500,width=900,resizable=yes,scrollbars=yes");
		}
	}
</script>

<h2>Más ofertas de empleo</h2>
<form name="ShowMoreCompanyOffersForm" id="ShowMoreCompanyOffersForm" method="post" action="showMoreCompanyOffers.do" dojoType="dijit.form.Form">
	<input type="hidden" name="method" id="method" value="init"/>
	<div>
		<h2><%=strNombreEmpresa%></h2>
		<div class="logo_empresa">
			<img alt="logo <%=strNombreEmpresa%>" src="${context}imageAction.do?method=getImagen&<%="captcha?time="+ Calendar.getInstance().getTimeInMillis()%>&ID_EMPRESA=<%=formCompany.getIdEmpresa()%>" width="90" height="90" >
			<%if(null!=strWebSite && !strWebSite.equalsIgnoreCase("")){
				out.print("<input type=\"button\" name=\"btnWebsite\" id=\"btnWebsite\" class=\"boton\" value=\"Visitar página web\" onclick=\"visitWebSite();\">");
			}%>
		</div>
		<div>
			<h3>Acerca de <%=strNombreEmpresa%></h3> 
			<p><%=strDescripcion%></p>
		</div>
	</div>
	<div>		
	   <div class="entero">	   
	   <h3>Oferta(s)</h3>
	   <div id="tabla" name="tabla">	   
	   </div>	
       </div>   
       <p align="center" >
		<a href="javascript:doSubmitPager('prev')">&lt;&lt;&nbsp;Anterior</a>
		&nbsp;&nbsp;&nbsp;&nbsp;
		<a href="javascript:doSubmitPager('next')">Siguiente&nbsp;&gt;&gt;</a>
	   </p>	
	</div>
	   
</form>
<script>
	doSubmitPager('page');
</script>