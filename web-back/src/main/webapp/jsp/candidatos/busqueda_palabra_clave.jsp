    <div class="candidatoOf">
    	<h3 class="subtituloHerramientas">Buscador de ofertas de empleo</h3>
        <div id="buscador_ofertas">
        	<strong>Palabras clave</strong>
                            
            <form name="ocupateForm" id="ocupateForm" method="post" action="ocupate.do">
            <div>
            	<input type="hidden" name="method" id="method" value="init"/>
            	<input type="text" name="searchQ" id="searchQ" onfocus="this.value = '';" value="Escribe las palabras clave" />
                <input type="submit" id="btnBuscar" value="Buscar"/>
            </div>
            </form>
            <p class="ejemplo">Ej. Asistente, $5000</p> 
        </div>
    </div>