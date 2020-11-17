<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<script src="dojotoolkit/dojo/dojo.js" djConfig="parseOnLoad: true, locale:'es'"></script>
<%String context = request.getContextPath() +"/";%>
<style>

body {
    font: 12px Myriad,Helvetica,Tahoma,Arial,clean,sans-serif;
}
.capacitacionMixta {
	float: left; 
	margin: 0 0 0 13px; 
	width: 681px;
}
.formulario {
    
    padding: 0 20px;
    width: 600px;
}
h2, h3, h4 {
    color: #666666;
    font-family: 'Droid Sans',Geneva,Arial,Helvetica,sans-serif;
}
.formulario h3 {
    background: none repeat scroll 0 0 transparent;
    border-top: medium none;
    font-size: 16px;
    font-weight: bold;
    margin: -22px 0 0 -22px;
    padding: 40px 0 0 22px;
}
.formulario p {
    color: #535353;
    line-height: 25px;
    width: 100%;
	font-size: 1em;
}
.entero {
    
    margin: 15px 0 0 1px;
    width: 93%;
}
.otroEntero{
    margin: 45px 165px 1px;
    width: 93%;

}
.otroMedio{
	position:
	width: 48%;
}
.un_medio {
    
    width: 48%;
}
.boton {
    background: url("images/link_a.gif") repeat-x scroll 0 center #F47513;
    border: 1px solid #4f6710;
    border-radius: 5px 5px 5px 5px;
    color: #FFFFFF;
    font-weight: bold;
    margin: 0 30px 10px 0;
    padding: 5px 10px;
    text-decoration: none;
	cursor:pointer;
}

.contList li {
    background: url("images/contenido_bullet.png") no-repeat scroll 0 6px transparent;
    list-style: none outside none;padding-left: 25px; color: #666666;
    padding-top: 4px;
}
.formulario .titGral {
    color: #4f6710;
    font-weight: bold;
	padding-left: 40px;
    padding-top: 15px !important;
	clear:both;
	margin-bottom: -10px !important;

}
.formulario a {
    color: #4f6710;
    font-weight: bold;
}
</style>
<script type="text/javascript">
function abrirLigaPadre(){
	 window.open('https://www.empleo.gob.mx/es_mx/empleo/quienes_somos');
}

function caracteresValidos(e){
 	var tecla_codigo = (document.all) ? e.keyCode : e.which;
 	if(tecla_codigo==8 || tecla_codigo==0) return true;
 	if (tecla_codigo==225 || tecla_codigo==233 || tecla_codigo==237 || tecla_codigo==243 || tecla_codigo==250) return true; //vocales minusculas con acento
 	if (tecla_codigo==193 || tecla_codigo==201 || tecla_codigo==205 || tecla_codigo==211 || tecla_codigo==218) return true; //vocales mayuzculas con acento
 	if (tecla_codigo==209 || tecla_codigo==241 ) return true; //Ñ y ñ
		
 	var patron =/[A-Za-z\s\-.áéíóúÁÉÍÓÚñÑ0-9.,;:]/;
 	tecla_valor = String.fromCharCode(tecla_codigo);
 	return patron.test(tecla_valor);

 }
 
function doSubmitCapMixta(method){
	var texto = document.ComRegCompanyForm.nombre.value;
	if(texto.replace(/^\s+|\s+$/g,"")==''){
		dojo.byId('msg').innerHTML = 'Debe escribir algún texto';
		dojo.byId('msg').style.color ='#FF0000';
	}else {
		dojo.byId('msg').innerHTML = '';
		dojo.xhrPost(
				 {url: '${context}capacitacionMixta.do?method=salvarTexto', form:'ComRegCompanyForm', timeout:180000, // Tiempo de espera 3 min
				  load: function(data){
					  if(data == 'SUCCESS'){
						  alert('La Oficina del Servicio Nacional de Empleo más cercana a su domicilio se pondrá en contacto con usted en los próximos días. Gracias.');
						  document.location.href = '<c:url value="/miEspacioEmpresas.do"/>';
					  }else{
						  alert('Ocurrió un error al ingresar sus datos inténtelo otra ves');
					  }
				  }
				 });
	}
}

function closeCapacitacionMixta(){
	document.location.href = '<c:url value="/miEspacioEmpresas.do"/>';
}
</script>
<noscript>Funciones de JavaScript desactivadas por navegador</noscript>

<div class="capacitacionMixta">
<form id="ComRegCompanyForm" name ="ComRegCompanyForm"action="capacitacionMixta.do" method="post" dojoType="dijit.form.Form">
<div class="formulario">
	<h3 class="subtituloHerramientas">Capacitación mixta:</h3>
	<p class="entero">
	Consiste en apoyar a las empresas con cursos de uno a tres meses, realizados a petición expresa del sector empresarial para satisfacer sus requerimientos específicos de personal, diseñando los contenidos temáticos de los cursos e impartirlos con sus propios instructores.Se concertarán mediante un convenio con las empresas la contratación de al menos el 70% de los egresados.
	</p>
	<p class="entero">
	<strong>Organización de los cursos:</strong> Concertada con empresas.
	</p>
	<p class="entero">
	Conoce algunos de los cursos que se han impartido en esta modalidad:
	</p>
	<p class="titGral">CAPACITACIÓN MIXTA</p>
	<ol class="contList">
	<li> Ayudante general</li>
	<li>Costura industrial</li>
	<li>Ensamblador de partes automotrices</li>
	<li>Ensamble de equipos mecatrónicos </li>
	<li>Ensamble de material y equipo médico</li>
	<li>Máquinas y herramientas </li>
	<li>Operador de máquinas de costura industrial</li>
	<li>Soldadura</li>
	<li>Ventas y atención al cliente</li>
	<li>Ensamble de motores</li>
	</ol>
	<p>Te interesa participar en este tipo de capacitación, escríbenos, o acude a las oficinas del <a href="javascript:abrirLigaPadre()">Servicio Nacional del Empleo</a> en tu localidad.</p>
	<textarea trim="true" style="width:550px;min-height:120px;_height:200px;" regexp="^[a-zA-ZáéíóúÁÉÍÓÚñÑ0-9.,;:#\\s]{1,1000}$" onpaste="return false;" onkeypress="return caracteresValidos(event);" maxlength="2000" id="nombre" name="nombre"><c:if test="${texto != null}">${texto}</c:if></textarea></br>
	<div id="msg"> </div>
	<div style="clear:both;" class="otroEntero">         			    				   
		 <span style="text-align:center; margin:40px 0 0 0; " class="otroMedio">                                        
			<input type="button" onclick="javascript:doSubmitCapMixta('salvarTexto');" class="boton" value="Guardar" id="btnGuardar">
		</span>
		<span style="text-align:center; margin:40px 0 0 0; " class="otroMedio">
			<input type="button" onclick="javascript:closeCapacitacionMixta();" class="boton" value="Cancelar" id="btnCancel"> 
		</span>
	</div>
	</div>
</form>
</div>