<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
  String context = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+ request.getContextPath() +"/";
%>

<style type="text/css">
	@import "dojotoolkit/dojo/resources/dojo.css";
	@import "dojotoolkit/dijit/themes/claro/claro.css";
</style> 

<script src="dojotoolkit/dojo/dojo.js" djConfig="parseOnLoad: true, locale:'es'"></script>

<script type="text/javascript" src="googiespell/AJS.js"></script>
<script type="text/javascript" src="googiespell/googiespell.js"></script>
<script type="text/javascript" src="googiespell/cookiesupport.js"></script>
<link   href="googiespell/googiespell.css" rel="stylesheet" type="text/css" />

<script type="text/javascript">
    dojo.require("dijit.dijit"); // loads the optimized dijit layer
	dojo.require("dijit.form.Form");
	dojo.require("dijit.form.Button");
	dojo.require("dijit.form.Textarea");    
</script>
         


<form name="spellForm" id="spellForm" method="post" action="SpellTest.do" dojoType="dijit.form.Form">

	<input type="hidden" name="method" id="method" value="init"/>	
    
    <textarea name="miTexto" id="miTexto" class="textarea" > </textarea>	

	<div id="btnValidar" dojoType="dijit.form.Button">Validar</div>


<!-- <input id="bTest" type="button" value="Run" onclick="fnSpell()"> -->    
</form>


<script type="text/javascript">

  //alert(document.spellForm.miTexto.value );" + document.spellForm.miTexto.value + "
  //var googie1 = new GoogieSpell("googiespell/", "http://192.168.6.212:7001/SpellTest.do?method=gogiespell&lang=");
  //googie1.decorateTextarea("miTexto");


   //var vProxy = "https://www.google.com/tbproxy/spell?lang=";
   var vProxy = "http://localhost:7001/SpellTest.do?method=gogiespell&lang=";
   var vSpell = new GoogieSpell("googiespell/", vProxy); 
   vSpell.decorateTextarea("miTexto");


</script>


  <script type="text/javascript">

	dojo.addOnLoad(function() {

    	dojo.connect(dijit.byId("btnValidar"), "onClick", function() {

		dojo.byId('method').value='gogiespell';
		//alert(dojo.byId('method').value);
		
		//var cadena =  document.spellForm.miTexto.value;
    	//alert(cadena);
    	
    	//var googie1 = new GoogieSpell("googiespell/", "${context}SpellTest.do?method=gogiespell" + "&miTexto=" + cadena + "&" + "lang=");
    	//googie1.decorateTextarea("miTexto"); 
                
        dojo.xhrPost(
		{
				  url: 'SpellTest.do',
				  form:'spellForm',
				  timeout:180000, 
				  load: function(data)
				  {
				  //alert("Listones!!!");
				  //alert(data);
				  }
				});
        
    	});

    });        
  </script>

