<%@page import="mx.gob.stps.portal.core.oferta.registro.vo.RegistroEntidadesVO"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="mx.gob.stps.portal.persistencia.vo.CatalogoOpcionVO"%>
<%@page import="java.util.List"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>

<style type="text/css">
	@import "dojotoolkit/dojo/resources/dojo.css";
	@import "dojotoolkit/dijit/themes/claro/claro.css";
</style> 

<link href="${PATH_CSS_SWB_APP}estilos_lightBox.css" rel="stylesheet" type="text/css" />

<link href="${PATH_CSS_SWB}css_aplicacion/estilos_tabla.css" rel="stylesheet" type="text/css" />

</head>

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
</script>


<% 
List<CatalogoOpcionVO>  listaEntidades= (List<CatalogoOpcionVO>)request.getSession().getAttribute("CATALOGO_OPCION_ENTIDAD_FEDERATIVA");
List<CatalogoOpcionVO> listaMunicipios= (List<CatalogoOpcionVO>)request.getSession().getAttribute("CATALOGO_OPCION_MUNICIPIO");
String context = request.getContextPath() +"/";
%>



<script type="text/javascript">
	function doSubmit(method) {
		document.entidadesFormBean.method.value = method;
		document.entidadesFormBean.submit();
	}

	function cerrarse() {
		window.close();
	}

	function cancelaRegistro() {

		if (confirm("Los datos no guardados se perderán ¿Continuar?")) {
			window.close();
		}
	}
</script>





<body class="claro">


<form name="entidadesFormBean" action="ofertaEdicionEntidades.do"
	method="post"><input type="hidden" name="method"
	value="experienciaInit" />


<div id="overPanel">

<div id="cuerpo_pop" class="entrevistaProgramar">
<h3><span>Oferta de empleo</span><br />
<strong class="empresa">Ubicaciones adicionales</strong></h3>


<div class="campo_pop derecha2">

</div>
</div>
</div>

<table class="offer">
	<tr>
		<td>Entidad federativa
		<input type="hidden" name="entidad"
			id="entidad" value="-1" />
		<div dojoType="dojo.data.ItemFileReadStore"
			jsId="entidadFederativaStore" urlPreventCache="true"
			clearOnClose="true"></div>
		<select dojotype="dijit.form.FilteringSelect"
			store="entidadFederativaStore" id="entidadFederativaSelect"
			required="true" disabled="true" autoComplete="false"></select>
		</td>

		<td>Municipio <input type="hidden" name="municipio"
			id="municipio" value="-1" />
		<div dojoType="dojo.data.ItemFileReadStore" jsId="municipioStore"
			urlPreventCache="true" clearOnClose="true"></div>
		<select dojotype="dijit.form.FilteringSelect" store="municipioStore"
			id="municipioSelect" required="true" disabled="true"
			autoComplete="false"></select></td>
		<td></td>
	</tr>


</table>

<br>

<br>
<div >
<table class="offer">
	<tr class="temas">
		<th>Eliminar</th>
		<th>Entidad</th>
		<th  class="fin">Delegacion/Municipio</th>

	</tr>
	<% 
       ArrayList<RegistroEntidadesVO> lista=(ArrayList<RegistroEntidadesVO>) (request.getSession().getAttribute("listaEntidadesSeleccionadas")!=null?request.getSession().getAttribute("listaEntidadesSeleccionadas"):new ArrayList<RegistroEntidadesVO>());
       RegistroEntidadesVO item=null;
       String nombreCheck;
   
       for(int i=0;i<lista.size();i++){
    	   item=lista.get(i);
    	   nombreCheck="elim"+(i+1);
      %>
	<tr class="odd" >
		<td  class="checar"><input type=checkbox name=<%=nombreCheck%>></td>
		<td  class="checar"><%=item.getEntidadDescripcion()%></td>
		<td  class="checar"><%=item.getMunicipioDescripcion()%></td>
	</tr>
	<%} %>

</table>
</div>
<br><br>
<div>
<table class="offer">
	<tr>
		<td><input type="button" value="Guardar" class="boton"
			onclick="javascript:this.disabled=true;doSubmit('registrarEntidadMunicipio');" />
		</td>
		<td><input type="button" value="Cancelar" class="boton"
			onClick="cancelaRegistro();" /></td>
		<td><input type="button" value="Cerrar ventana" class="boton"
			onclick="javascript:this.disabled=true;cerrarse();" /></td>
	</tr>
</table>
</div>
<div class="logoLightBox"><img
	src="images/logo_bottomLightBox.gif" alt="Portal del Empleo" /></div>

<div class="bottom_lightBox2"></div>

</form>
</body>
</html>

<script type="text/javascript">
	dojo.addOnLoad(function() {

				dijit.byId('entidadFederativaSelect').disabled = false;

				entidadFederativaStore.url = "${context}ofertaEdicionEntidades.do?method=obtenerCatEntidades";
				entidadFederativaStore.close();

				dojo
						.connect(
								dijit.byId("entidadFederativaSelect"),
								"onChange",
								function() {
									dijit.byId("municipioSelect").reset();
									var vEntidad = dijit.byId(
											'entidadFederativaSelect').get(
											'value');

									dijit.byId('municipioSelect').disabled = false;

									municipioStore.url = "${context}domicilio.do?method=obtenerMunicipio"
											+ "&" + "idEntidad=" + vEntidad;
									municipioStore.close();

									document.entidadesFormBean.entidad.value = dijit
											.byId('entidadFederativaSelect')
											.get('value');
								});

				dojo.connect(dijit.byId("municipioSelect"), "onChange",
						function() {
							document.entidadesFormBean.municipio.value = dijit
									.byId('municipioSelect').get('value');
						});

			});
</script>





