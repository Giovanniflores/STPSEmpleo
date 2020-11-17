<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<script src="dojotoolkit/dojo/dojo.js" djConfig="parseOnLoad: true, locale:'es'"></script>

<noscript>Funciones de JavaScript desactivadas por navegador</noscript>

<script type="text/javascript">
dojo.require("dijit.dijit"); // loads the optimized dijit layer
dojo.require("dijit.form.Form");
dojo.require("dijit.Dialog");

function doSubmitTestimonio(method){
	if(document.testimonioForm.testimonio.value==''){
		dojo.byId('msg').innerHTML = 'Debe escribir su testimonio para poder guardarlo';
		dojo.byId('msg').style.color ='#FF0000';
	}else {
		document.testimonioForm.method.value=method;
		dojo.xhrPost(
				 {url: 'testimonio.do?method=registrar', form:'testimonioForm', timeout:180000, // Tiempo de espera 3 min
				  load: function(data){
// 					  closeWindow();
// 					  dojo.byId('mensaje').innerHTML = 'Tu testimonio se guardo correctamente';
// 					  dijit.byId('MensajeAlert').show();
					  alert('Tu testimonio se guardo correctamente');
					  if(data == 'EMPRESA'){
						  document.location.href = '<c:url value="/miEspacioEmpresas.do"/>';  
					  }else{
						  document.location.href = '<c:url value="/miEspacioCandidato.do"/>';
					  }
				  }
				 } );
	}
}

function maxlength(field, size) {
    if (field.value.length > size) {
        field.value = field.value.substring(0, size);
    }
}

function maximaLongitud(texto,maxlong)
	{
	var tecla, int_value, out_value;

	if (texto.value.length > maxlong)
	{
	/*con estas 3 sentencias se consigue que el texto se reduzca
	al tamaño maximo permitido, sustituyendo lo que se haya
	introducido, por los primeros caracteres hasta dicho limite*/
	in_value = texto.value.replace(/^\s+|\s*[\r\n][\r\n \t]*/g, "");
	out_value = in_value.substring(0,maxlong);
	texto.value = out_value;
	texto.value = texto.value;
	return false;
	}
	return true;
	}

</script>

<form id="testimonioForm" name ="testimonioForm" action="testimonio.do" method="post" dojoType="dijit.form.Form">
	<input type="hidden" name="method" value="init"/>
	

	<div id="compartirTestimonio">
			<div class="campoTexto margin_top_30">
			<label for="testimonio">Testimonio (no mayor a 200 caracteres)</label><br>	   
	   		<textarea style="width:350px; height:120px;" cols="40" rows="3" name ="testimonio" id="testimonio" onkeypress="return maximaLongitud(this, 100);" onKeyUp="return maximaLongitud(this, 100);" onchange="return maximaLongitud(this, 100);"></textarea>
	   		</div>
		<div class="form_nav">
				<input type="button" class="boton" value="Enviar" onclick="javascript:doSubmitTestimonio('registrar');"/>
		</div>
	</div>

</form>
