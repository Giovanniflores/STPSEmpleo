<%@ page language="java" 
import="mx.gob.stps.portal.web.security.vo.UsuarioWebVO, 
 mx.gob.stps.portal.web.infra.utils.Constantes;" 
%>
<% 
UsuarioWebVO usuario = (UsuarioWebVO)session.getAttribute(Constantes.USUARIO_APP);
%>

<link href="${PATH_CSS_SWB_APP}estilos_lightBox.css" rel="stylesheet" type="text/css" />
<script src="dojotoolkit/dojo/dojo.js" djConfig="parseOnLoad: true, locale:'es'"></script>
<script type="text/javascript">
	dojo.require("dijit.dijit");
	dojo.require("dijit.form.Form");
	dojo.require("dijit.form.NumberTextBox");
	dojo.require("dijit.form.CurrencyTextBox");
	dojo.require("dijit.Dialog");
</script>
<script type="text/javascript">
function suma(){
	var elementos=dojo.byId("calculadoraForm").elements;
	var suma = 0;
	for (i=0; i<elementos.length; i++){
		posIni=elementos[i].name.indexOf("Txt");
		if(!elementos[i].value=='' && !parseInt(elementos[i].value)==0 
				&& elementos[i].name.substr(posIni)=="Txt"){
				suma += parseFloat(elementos[i].value);
			}
		}
	dojo.byId('sumaCalculada').value=suma;
}

function doSubmit(method){
	if (parseInt(dojo.byId('sumaCalculada').value)=="0" || dojo.byId('sumaCalculada').value==""){
		dojo.byId('message').innerHTML = 'El total de gastos debe ser mayor a 0';
		dojo.byId('message').style.color='#660000';
	} else {
		/*document.calculadoraForm.total.value=dojo.byId('sumaCalculada').value;
		dojo.byId('method').value=method;
		document.calculadoraForm.submit();
		*/
		dojo.byId('total').value =  dojo.byId('sumaCalculada').value;
		dojo.byId('method').value=method;
		 dojo.xhrPost({url: 'calculadora.do', form:'calculadoraForm', timeout:180000, 
			  load: function(data){
				  dojo.byId('mensaje').innerHTML = 'Tu c�lculo fue guardado correctamente';
				  dijit.byId('MensajeAlert').show();
			  }});
	}
}

</script>
<noscript>Funciones de JavaScript desactivadas por navegador</noscript>

<div id="overPanel">
	</div>
		<div id="cuerpo_pop" class="calculadoraEgresos">
		<div class="calcCuerpo">
        <h3>Calculadora de egresos</h3>
        <span class="deg"><img src="images/calculadora_pestana_deg.jpg" /></span>
        <p>
        Aquí puedes calcular el promedio de tus egresos mensuales, lo cual te permitirá definir un rango de expectativa salarial en tu búsqueda de empleo.
        </p>
        <p>
        *Recuerda que tu expectativa salarial debe ser coherente con tu experiencia, conocimientos y habilidades profesionales.
        </p>
        
<!-- EMPIEZA CALCULADORA-->
<form name="calculadoraForm" id="calculadoraForm">
<input type="hidden" id="method" name="method" value="init"/>
            <div id="Accordion1" class="Accordion" tabindex="0">
  <div class="AccordionPanel">
    <div class="AccordionPanelTab">Hogar y alimentación<span class="subirBajar"></span></div>
    <div class="AccordionPanelContent">
    <div id="calcHogar">      
     <p class="campo_calc"><label for="hogarRentaTxt">Renta</label><span>$
      <input type="text" name="hogarRentaTxt" id="hogarRentaTxt" 
      style="width: 80px;"
	  dojoType="dijit.form.CurrencyTextBox"
	  invalidMessage="Este campo solo acepta numeros"
	  value="0.00"/></span>
     </p>
     <p class="campo_calc"><label for="hogarMantenimientoTxt">Mantenimiento</label><span>$
       <input type="text" name="hogarMantenimientoTxt" id="hogarMantenimientoTxt" 
       style="width: 80px;"
	  dojoType="dijit.form.CurrencyTextBox"
	  invalidMessage="Este campo solo acepta numeros"
	  value="0.00"/></span>
     </p>
     <p class="campo_calc"><label for="hogarLimpiezaTxt">Personal de limpieza dom&eacute;stica</label><span>$
       <input type="text" name="hogarLimpiezaTxt" id="hogarLimpiezaTxt"
	  style="width: 80px;"
	  dojoType="dijit.form.CurrencyTextBox"
	  invalidMessage="Este campo solo acepta numeros"
	  value="0.00"/></span>
     </p>
     <p class="campo_calc"><label for="hogarServiciosTxt">Pago de servicios</label><span>$
       <input type="text" name="hogarServiciosTxt" id="hogarServiciosTxt"
       style="width: 80px;"
	  dojoType="dijit.form.CurrencyTextBox"
	  invalidMessage="Este campo solo acepta numeros"
	  value="0.00"/></span>
     </p>
     <p class="campo_calc"><label for="hogarArtHogarTxt">Art&iacute;culos para el hogar</label><span>$
       <input type="text" name="hogarArtHogarTxt" id="hogarArtHogarTxt"
       style="width: 80px;"
	  dojoType="dijit.form.CurrencyTextBox"
	  invalidMessage="Este campo solo acepta numeros"
	  value="0.00"/></span>
     </p>
     <p class="campo_calc"><label for="hogarDespensaTxt">Alimentos/ despensa</label><span>$
       <input type="text" name="hogarDespensaTxt" id="hogarDespensaTxt"
       style="width: 80px;"
	  dojoType="dijit.form.CurrencyTextBox"
	  invalidMessage="Este campo solo acepta numeros"
	  value="0.00"/></span>
     </p>
     </div>
    
    </div>
  </div>
  <div class="AccordionPanel">
    <div class="AccordionPanelTab">Salud y cuidado personal</div>
    <div class="AccordionPanelContent">
    <div id="calcSalud">      
     <p class="campo_calc"><label for="saludMedicTxt">Medicamentos</label><span>$
      <input type="text" name="saludMedicTxt" id="saludMedicTxt"
      style="width: 80px;"
	  dojoType="dijit.form.CurrencyTextBox"
	  invalidMessage="Este campo solo acepta numeros"
	  value="0.00"/></span>
     </p>
     <p class="campo_calc"><label for="saludVestidoTxt">Vestimenta</label><span>$
       <input type="text" name="saludVestidoTxt" id="saludVestidoTxt"
       style="width: 80px;"
	  dojoType="dijit.form.CurrencyTextBox"
	  invalidMessage="Este campo solo acepta numeros"
	  value="0.00"/></span>
     </p>
     <p class="campo_calc"><label for="saludHigieneTxt">Higiene y cuidado personal</label><span>$
       <input type="text" name="saludHigieneTxt" id="saludHigieneTxt"
       style="width: 80px;"
	  dojoType="dijit.form.CurrencyTextBox"
	  invalidMessage="Este campo solo acepta numeros"
	  value="0.00"/></span>
     </p>
     </div>
    </div>
  </div>
  <div class="AccordionPanel">
    <div class="AccordionPanelTab">Transporte y comunicaci&oacute;n</div>
    <div class="AccordionPanelContent">
    <div id="calcTransporte">      
     <p class="campo_calc"><label for="transportGasolinaTxt">Gasolina</label><span>$
      <input type="text" name="transportGasolinaTxt" id="transportGasolinaTxt" 
      style="width: 80px;"
	  dojoType="dijit.form.CurrencyTextBox"
	  invalidMessage="Este campo solo acepta numeros"
	  value="0.00"/></span>
     </p>
     <p class="campo_calc"><label for="transportPublicoTxt">Transporte p&uacute;blico</label><span>$
       <input type="text" name="transportPublicoTxt" id="transportPublicoTxt"
       style="width: 80px;"
	  dojoType="dijit.form.CurrencyTextBox"
	  invalidMessage="Este campo solo acepta numeros"
	  value="0.00"/></span>
     </p>
     <p class="campo_calc"><label for="transportTelFijoTxt">Telefon&iacute;a fija</label><span>$
       <input type="text" name="transportTelFijoTxt" id="transportTelFijoTxt"
       style="width: 80px;"
	  dojoType="dijit.form.CurrencyTextBox"
	  invalidMessage="Este campo solo acepta numeros"
	  value="0.00"/></span>
     </p>
     <p class="campo_calc"><label for="transportTelCelTxt">Tel&eacute;fono celular</label><span>$
       <input type="text" name="transportTelCelTxt" id="transportTelCelTxt"
       style="width: 80px;"
	  dojoType="dijit.form.CurrencyTextBox"
	  invalidMessage="Este campo solo acepta numeros"
	  value="0.00"/></span>
     </p>
     </div>
    </div>
  </div>
  <div class="AccordionPanel">
    <div class="AccordionPanelTab">Deuda, ahorro e inversi&oacute;n</div>
    <div class="AccordionPanelContent">
    <div id="calcDeuda">      
     <p class="campo_calc"><label for="finanzaTarjetaTxt">Pago de tarjeta de cr&eacute;dito</label><span>$
      <input type="text" name="finanzaTarjetaTxt" id="finanzaTarjetaTxt"
      style="width: 80px;"
	  dojoType="dijit.form.CurrencyTextBox"
	  invalidMessage="Este campo solo acepta numeros"
	  value="0.00"/></span>
     </p>
     <p class="dosRenglones"><label for="finanzaAutoTxt">Mensualidad</label></p><p class="campo_calc">(pago mensual de autom&oacute;vil)<span>$
       <input type="text" name="finanzaAutoTxt" id="finanzaAutoTxt"
       style="width: 80px;"
	  dojoType="dijit.form.CurrencyTextBox"
	  invalidMessage="Este campo solo acepta numeros"
	  value="0.00"/></span>
     </p>
     <p class="campo_calc"><label for="finanzaOtrosTxt">Otras deudas</label><span>$
       <input type="text" name="finanzaOtrosTxt" id="finanzaOtrosTxt"
       style="width: 80px;"
	  dojoType="dijit.form.CurrencyTextBox"
	  invalidMessage="Este campo solo acepta numeros"
	  value="0.00"/></span>
     </p>
     <p class="campo_calc"><label for="finanzaAhorroTxt">Ahorro mensual</label><span>$
       <input type="text" name="finanzaAhorroTxt" id="finanzaAhorroTxt"
       style="width: 80px;"
	  dojoType="dijit.form.CurrencyTextBox"
	  invalidMessage="Este campo solo acepta numeros"
	  value="0.00"/></span>
     </p>
     <p class="dosRenglones"><label for="finanzaViviendaTxt">Mensualidad</label></p><p class="campo_calc">(pago parcial de vivienda)<span>$
       <input type="text" name="finanzaViviendaTxt" id="finanzaViviendaTxt" 
       style="width: 80px;"
	  dojoType="dijit.form.CurrencyTextBox"
	  invalidMessage="Este campo solo acepta numeros"
	  value="0.00"/></span>
     </p>
     </div>
    </div>
  </div>
  <div class="AccordionPanel">
    <div class="AccordionPanelTab">Educaci&oacute;n</div>
    <div class="AccordionPanelContent">
    <div id="calcEdu">      
     <p class="campo_calc"><label for="">Colegiaturas/Guarder&iacute;a<span>$
      <input type="text" name="educacionColegTxt" id="educacionColegTxt"
      style="width: 80px;"
	  dojoType="dijit.form.CurrencyTextBox"
	  invalidMessage="Este campo solo acepta numeros"
	  value="0.00"/></span>
     </p>
     <p class="dosRenglones"><label for="educacionFormacionTxt">Formaci&oacute;n profesional</label></p><p class="campo_calc">(cursos, materias, etc.)<span>$
       <input type="text" name="educacionFormacionTxt" id="educacionFormacionTxt"
       style="width: 80px;"
	  dojoType="dijit.form.CurrencyTextBox"
	  invalidMessage="Este campo solo acepta numeros"
	  value="0.00"/></span>
     </p>
     </div>
    </div>
  </div>
  <div class="AccordionPanel">
    <div class="AccordionPanelTab">Recreaci&oacute;n y entretenimiento</div>
    <div class="AccordionPanelContent">
    <div id="calcFin">      
     <p class="campo_calc"><label for="osioFinSemanaTxt">Fin de semana</label><span>$
      <input type="text" name="osioFinSemanaTxt" id="osioFinSemanaTxt"
      style="width: 80px;"
	  dojoType="dijit.form.CurrencyTextBox"
	  invalidMessage="Este campo solo acepta numeros"
	  value="0.00"/></span>
     </p>
     <p class="campo_calc"><label for="osioEntretenimientoTxt">Gastos de entretenimiento</label><span>$
       <input type="text" name="osioEntretenimientoTxt" id="osioEntretenimientoTxt"
       style="width: 80px;"
	  dojoType="dijit.form.CurrencyTextBox"
	  invalidMessage="Este campo solo acepta numeros"
	  value="0.00"/></span>
     </p>
     </div>
    </div>
  </div>
  </div>
   <div>
    <div class="TabGastos">
    <p class="gastos"><label for="osioGastosExtraTxt">Gastos extraordinarios</label><span>$
       <input type="text" name="osioGastosExtraTxt" id="osioGastosExtraTxt"
       style="width: 80px; height: 20px "
	  dojoType="dijit.form.CurrencyTextBox"
	  invalidMessage="Este campo solo acepta numeros"
	  value="0.00"/></span>
     </p>
     
    </div>
    <p style="margin-left: 343px"><label for="sumaCalculada">Total</label><span>$
    <input type="text"
	style="width: 80px; height: 20px" id="sumaCalculada" name="sumaCalculada" 
	dojoType="dijit.form.CurrencyTextBox"
	readonly="readonly"/>
    </span>
     </p>
</div>  
<div id='message'></div>
<br>
<div >
	
	<input type="hidden" id="total" name="total" />
	<input type="button" size="10" value="Calcular" onclick="suma();"/>
	<% if(usuario != null && usuario.getIdPropietario() != 0) {%>
		<input type="button" size="10" value="Guardar" onclick="doSubmit('guardarSumaCalcu',document.calculadoraForm.total.value)"/>
	<%}%>
	</div>

</form>
<div dojoType="dijit.Dialog" id="MensajeAlert" title="Mensaje" style="display:none" draggable ="false" >
		  <table class="alertDialog" >
			 <tr align="center">
				 <td><div id ="mensaje"></div>&nbsp;</td>				 
			 </tr>
		 </table>	
</div> 
<!--TERMINA CALCULADORA-->

		<p>
        *Te aconsejamos que tu expectativa salarial sea coherente con tu experiencia, conocimientos y habilidades.
        </p><br />
		</div>
        <div class="logoLightBox">
                            <img src="images/logo_bottomLightBox.gif" alt="Portal del Empleo" />
                            </div>       	
		</div>
          
        <!-- hasta aquí  -->
        
        
    <script type="text/javascript">
var Accordion1 = new Spry.Widget.Accordion("Accordion1");
</script>      
