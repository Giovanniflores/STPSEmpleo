
<%String context = request.getContextPath() +"/";%>
<script type="text/javascript">
dojo.require("dijit.dijit"); // loads the optimized dijit layer
dojo.require("dijit.form.Form");
dojo.require("dijit.Dialog");


var dialogTestimonio;

function showWindow(){
	if (!dialogTestimonio){
		dialogTestimonio = new dijit.Dialog({
	        title: 'Comparte tu testimonio',
	        href: '<%=context%>testimonio.do?method=init',
	        style: "width:400px; height:350px;",
	        showTitle: false,
	        draggable : false
	    });
		
	}
	
	dialogTestimonio.show();
  }

function closeWindow(){
	dialogTestimonio.hide();
}


</script>
<div class="TabbedPanelsContent">
	<div style="float:left; width:310px; height:240px">
		<div id="aviso_carta">
			<h3>Carta de presentaci&oacute;n</h3>
			<div id="cartas">
				<hr />
				<p id="aviso">Tienes una carta de presentaci&oacute;n activada en ese momento: </p>
                <p><a href="#">CARTA DE PRESENTACI&Oacute;N 1</a></p>
                <hr />
            </div>
            <span>
            	<input class="boton" name="input" type="button" value="Visualizar carta de presentaci&oacute;n actual" />
            </span>
    	</div>
        <span id="bottom_carta"></span>
    </div>
    
    <div id="mis_herramientas">
    	<h3>Mis herramientas</h3>
    	<a href="#" id="mi_sitio">Mi sitio profesional</a> 
    	<a href="#" id="carta_presentacion">Carta de presentaci&oacute;n</a> 
    	<a href="#" id="entrevista">Entrevista en l&iacute;nea</a> 
    	<a href="#" id="completa_cv">Completa tu CV</a> 
    	<a href="javascript:showWindow()" id="testimonio">Compartir mi testimonio</a>
    </div>
    
    <div style="float:left; width:540px;">
    	<div class="crear_carta">
        	<h3>Crear una nueva carta de presentaci&oacute;n</h3>
            <div class="campo_carta">
                <a href="#" class="bold" title="Letra Bold">Letra bold</a>
                <a href="#" class="italica" title="Letra It&aacute;lica">Letra itálica</a>
                <a href="#" class="subrayada" title="Letra Subrayada">Letra subrayada</a>
                <a href="#" class="numerada" title="Lista Numerada">Lista numerada</a>
                <a href="#" class="no_numerada" title="Lista No-numerada">Lista no-numerada</a>
                <span class="maximo_caracteres">M&aacute;ximo 500 caracteres</span><br />
                <textarea name="" cols="70"></textarea>
            </div>
            <span class="bottom_crear_carta2"></span>
            <span><input type="button" value="Guardar" name="" class="boton"></span>
        </div>
        <span class="bottom_crear_carta"></span>
        <div class="crear_carta">
	        <h4>Imagen de fondo</h4>
	        <div id="agregar_imagen">
	        	Agregar imagen <input name="" type="text" />
	        	<input type="button" class="boton" name="" value="Examinar">
	        	<input type="button" class="boton" name="" value="Agregar">
	        </div>
	        <div class="muestras">
	        	<img src="images/muestra1.jpg" alt="muestra 1" /><br />
	            <input name="" type="checkbox" value="" />
	        </div>
	        <div class="muestras">
	        	<img src="images/muestra2.jpg" alt="muestra 2" /><br />
	            <input name="" type="checkbox" value="" />
	        </div>
	        <div class="muestras">
	        	<img src="images/muestra3.jpg" alt="muestra 3" /><br />
	            <input name="" type="checkbox" value="" />
	        </div>
	        <div class="muestras">
	        	<img src="images/muestra4.jpg" alt="muestra 4" /><br />
	            <input name="" type="checkbox" value="" />
	        </div>
	        <div class="muestras">
	        	<img src="images/muestra5.jpg" alt="muestra 5" /><br />
	            <input name="" type="checkbox" value="" />
	        </div><br />                                              
	        <span><input type="button" value="Guardar" name="" class="boton"></span>
        </div>
        <span class="bottom_crear_carta"></span>
    </div>
    <div id="muestra_presentacion">
    	<h4>Activar otra carta de presentaci&oacute;n</h4>
        <select>
        	<option>Carta de presentaci&oacute;n 1</option>
            <option>Carta de presentaci&oacute;n 2</option>
            <option>Carta de presentaci&oacute;n 3</option>
        </select>
        <input type="button" value="Activar" name="" class="boton">
        <input type="button" value="Editar" name="" class="boton"><br />
        <input name="" type="checkbox" value="" />No mostrar carta de presentaci&oacute;n
        <h4>carta de presentaci&oacute;n actual</h4>
        <img src="images/presentacion_carta.gif" alt="Carta de presentaci&oacute;n preliminar" />
        <span><input type="button" value="Imprimir" name="" class="boton"></span>
    </div>
</div>

