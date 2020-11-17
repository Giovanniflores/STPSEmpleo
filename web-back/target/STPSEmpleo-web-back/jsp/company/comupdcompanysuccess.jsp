<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="es" lang="es"> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body class='claro'>
<%
String context = request.getContextPath() +"/";  
%>

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
    dojo.require("dojo.parser");
</script>
<noscript>Funciones de JavaScript desactivadas por navegador</noscript>

<script type="text/javascript">
function enviarMensajeExito(){
	setTimeout("fMensaje()", 15000);
}

function fMensaje(){
	alert('Sus datos han sido modificados, personal del Servicio Nacional de Empleo se pondrá en contacto con usted para confirmar la información proporcionada o si desea llame al 01-800-841-20-20.');
}

function setMensaje(mensaje){
	dojo.byId('mensaje').innerHTML = mensaje;
	var vMensajeAlert = dijit.byId('MensajeAlert');
	vMensajeAlert.show();		
}

function enviarMensajeBasicosDialog(){
		var vMensaje = 'Sus datos han sido registrados,<br/> personal del Servicio Nacional de Empleo se pondrá<br/> en contacto con usted para confirmar<br/> la información proporcionada<br/>  o si desea llame al 01-800-841-20-20.';
		setMensaje(vMensaje);
}

function enviarMensajeExitoDialog(){
	var vMensaje = 'Los datos han sido guardados exitosamente.';
	setMensaje(vMensaje);
}

</script>
<form>
	<div class="entero">
		&nbsp;&nbsp;&nbsp;&nbsp;
	</div>

					<div dojoType="dijit.Dialog" id="MensajeAlert" title="Mensaje" style="display:none" draggable ="false">
						  <table width="400px" height="200px" >
							 <tr align="center">
								 <td><div id ="mensaje" style="width:400px;height:200px;overflow:auto;vertical-align:middle;"></div>&nbsp;</td>				 
							 </tr>
						 </table>	
					</div>   
	<%
	String strUpdCompany = String.valueOf(request.getSession().getAttribute("UPDCOMPANY"));  
	System.out.println("---strUpdCompany:" + strUpdCompany);
	if(!strUpdCompany.equalsIgnoreCase("EMAIL") && !strUpdCompany.equalsIgnoreCase("BASICDATA")){
		out.print("<meta http-equiv=\"refresh\" content=\"15;url=" + context + "comupdcompany.do?method=init\" />");
	} else {
		out.print("<meta http-equiv=\"refresh\" content=\"15;url=" + context + "logout.do\" />");		
	}
	%>
</form>

<!-- <script>enviarMensajeExitoDialog();</script> -->
 
  <script type="text/javascript">
	<%
	if(strUpdCompany.equalsIgnoreCase("EMAIL") || strUpdCompany.equalsIgnoreCase("BASICDATA")){
		out.print("dojo.addOnLoad(enviarMensajeBasicosDialog);");
	} else {
		out.print("dojo.addOnLoad(enviarMensajeExitoDialog);");
	}
	%>
  </script>      

</body>
</html>