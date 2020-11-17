<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"		prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<%
	String context = request.getContextPath() +"/";
	pageContext.setAttribute("context", context);
		
%>


<script type="text/javascript">
dojo.require("dijit.dijit"); // loads the optimized dijit layer
dojo.require("dijit.form.Form");    
dojo.require("dijit.form.RadioButton");
dojo.require("dijit.form.TextBox");
dojo.require("dijit.form.ComboBox");
dojo.require("dojo.data.ItemFileReadStore"); 
dojo.require("dijit.form.FilteringSelect");


function doRegresar(method){
	document.bolsasTrabajoForm.method.value=method;
	document.bolsasTrabajoForm.submit();
}

function doSubmitx(method){
	document.bolsasTrabajoForm.method.value=method;
	document.bolsasTrabajoForm.submit();
}

function doSubmitPaginaInicial(method) {
    dojo.xhrPost({url: 'ofertasSectur.do?method='+method, timeout:180000,
                  load: function(data) {
                        dojo.byId('reporte').innerHTML = data;
                  }});
}

function doSubmitPagina(pagina, dif){
   	dojo.xhrPost({url: 'ofertasSectur.do?method=goToPage&goToPageNumber='+pagina, timeout:180000, 
				  load: function(dataCand){
				      dojo.byId('reporte').innerHTML=dataCand;
				  }});
}

function doSubmitOrden(campo, orden){
   	dojo.xhrPost({url: 'ofertasSectur.do?method=ordenaPor&campo='+ campo +'&orden='+ orden, timeout:180000, 
				  load: function(dataCand){
				      dojo.byId('reporte').innerHTML=dataCand;
				  }});
}

//VER DETALLE OFERTAS SFP
function mostrarDetalle(idOfertaSFP){
	//COMENTAR EN QA document.bolsasTrabajoForm.method.value = 'detalleOfertaSFP';
	//COMENTAR EN PRODUCCION
	document.bolsasTrabajoForm.method.value = 'detalleOfertaGenerico';
	document.bolsasTrabajoForm.idOferta.value = idOfertaSFP;
	document.bolsasTrabajoForm.submit();
}


function doSubmitFiltro(){
	var busqueda = document.getElementById('txtofer').value;
	var edo = document.getElementById('selectedo').value;
	
	
	dojo.xhrPost({url: 'ofertasSectur.do?method=FiltraOfertas&busqueda='+ busqueda +'&edo='+ edo, timeout:180000, 
		load: function(dataCand){
		      dojo.byId('reporte').innerHTML=dataCand;
		  }});
}

</script>


<noscript>Funciones de JavaScript desactivadas por navegador</noscript>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd"> 
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="es" lang="es"> 
<head> 

    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" /> 
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Tu trabajo hace al turismo - Vacantes en el sector turístico</title>

    
    <!-- Bootstrap core CSS -->
    
    <!--<link href="< %=request.getContextPath()% >/css/sectur/bootstrap/css/bootstrap.min.css" rel="stylesheet">-->
    

    <!-- Custom styles for this template -->
    <link href="<%=request.getContextPath()%>/css/sectur/style.css" rel="stylesheet">
    
    <!-- Fonts -->
    <link href="https://fonts.googleapis.com/css?family=Roboto:300,700,900" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/css/sectur/fonts/font-awesome/css/font-awesome.css" rel="stylesheet">
    
    <link rel="shortcut icon" type="image/png" href="<%=request.getContextPath()%>/css/sectur/img/favicon.png"/>

  </head>


  <body>

    <!-- Page Content -->
    <div class="container" style="margin-bottom: 70px;">

      <!-- Heading Row -->
      <div class="row my-4 upper">
        <div class="col-lg-8">
          <img class="img-fluid rounded" src="<%=request.getContextPath()%>/css/sectur/img/logo.png" alt="">
        </div>
        <!-- /.col-lg-8 -->
        <div class="col-lg-4 upper-text">
         <div class="set-down">          
         <h1 style="font-size: 31px; text-align: center;">Lo mejor de México, somos los mexicanos</h1>
          <p style="font-size: 14px; text-align: center;">Forma parte de la comunidad turística nacional y explora nuevos tipos de empleo. Muestra tu calidez, talento y compromiso para consolidar a México como referente turístico mundial.
Estas vacantes son el resultado del trabajo conjunto entre la STPS, a través del SNE, y  SECTUR, mediante <a href="http://www.conectatealturismo.mx" class="link">"Conéctate al Turismo".</a>
 </p>
          <a class="btn btn-primary btn-lg upper-btn" href="#pleca">¿Cómo me postulo?</a>
          <p style="text-align: center; font-size: 20px; margin:0"> - ó - </p>
          <a class="btn btn-primary btn-lg upper-btn btn" href="#publica">Registra tu vacante</a>
        </div>
        </div>

        <!-- /.col-md-4 -->
      </div>
      <!-- /.row -->
	  </div>

      <!-- Call to Action Well -->
      <div class="container-fluid vacantes">
      <div class="container">
      <h1>Conoce nuestras vacantes</h1>
      <p class="intro">Trabaja y muestra que #LoMejorDeMéxicoEresTú</p>
      
     

      
      <div class="card tabla_vacantes">
        <div class="card-body">

	<form dojoType="dijit.form.Form" name="bolsasTrabajoForm" id="ofertasRivieraMayaForm" action="ofertasSectur.do" method="post">
		<input type="hidden" name="method" value="init"/>
		
		 <div class="col-md-4">
      <label>¿Qué empleo buscas?</label>
      <input class="form-control" type="text" id="txtofer" name="txtofer" dojoType="dijit.form.ValidationTextBox" placeholder="Escribe una categoría... " onKeyUp="doSubmitFiltro();"/>
      </div>
      
      <div class="col-md-4">
      <label>¿Dónde?</label>
      <select class="form-control" id="selectedo" name="selectedo" required onchange="doSubmitFiltro();">
      <option value="0" disabled selected>Selecciona un estado...</option>
      <option value="1">Aguascalientes</option>
      <option value="2">Baja California</option>
      <option value="3">Baja California Sur</option>
      <option value="4">Campeche</option>
      <option value="5">Coahuila</option>
      <option value="6">Colima</option>
      <option value="7">Chiapas</option>
      <option value="8">Chihuahua</option>
      <option value="9">Ciudad de México</option>
      <option value="10">Durango</option>
      <option value="11">Guanajuato</option>
      <option value="12">Guerrero</option>
      <option value="13">Hidalgo</option>
      <option value="14">Jalisco</option>
      <option value="15">México</option>
      <option value="16">Michoacán</option>
      <option value="17">Morelos</option>
      <option value="18">Nayarit</option>
      <option value="19">Nuevo León</option>
      <option value="20">Oaxaca</option>
      <option value="21">Puebla</option>
      <option value="22">Querétaro</option>
      <option value="23">Quintana Roo</option>
      <option value="24">San Luis Potosí</option>
      <option value="25">Sinaloa</option>
      <option value="26">Sonora</option>
      <option value="27">Tabasco</option>
      <option value="28">Tamaulipas</option>
      <option value="29">Tlaxcala</option>
      <option value="30">Veracruz</option>
      <option value="31">Yucatán</option>
      <option value="32">Zacatecas</option>
		  </select>
      </div>
      <div class="col-sm-12">
      <h2></h2>
      <h2></h2>
      </div>
		
			<div class="col-sm-12">
				<div class="page-header">
					<h3>Resultados en la bolsa de trabajo del sector turismo</h3>
				</div>
			</div>
			<div class="col-sm-12">
				<div id="reporte"></div>
			</div>

		<div class="entero">
			<div id="msg"></div>
		</div> 

	</form>

	
</div>
</div>

<script type="text/javascript">
    doSubmitPaginaInicial('page');
</script>
      
      
      </div>
      </div>
      
      <div id="pleca" class="container-fluid pleca"></div>
      
    <div class="container como_postulo">
     
      <h1>¿Cómo me postulo?</h1>
      <p class="intro">¿Playa o ciudad?
¿Provincia o CDMX?
Checa cuál es la vacante ideal para ti.
</p>
      <!-- Content Row -->
      <div class="row">
        
        <!-- /.col-md-4 -->
        <div class="col-md-4 mb-4">
          <div class="card h-100">
            <div class="card-body">
             <img class="icono" src="<%=request.getContextPath()%>/css/sectur/img/2.jpg" />
              <h2 class="card-title">1. Elige</h2>
              <p class="card-text">¿Playa o ciudad?
¿Provincia o CDMX?<br>
<b>Checa cuál es la vacante ideal para ti.</b></p>
            </div>
          </div>
        </div>

        
                        
         <div class="col-md-4 mb-4">
          <div class="card h-100">
            <div class="card-body">
             <img class="icono" src="<%=request.getContextPath()%>/css/sectur/img/1.jpg" />
              <h2 class="card-title">2. Regístrate</h2>
              <p class="card-text"><a class="link" href="#">Ingresa tus datos</a> para formar parte de la comunidad turística. 
Si ya estás registrado, sólo inicia sesión.</p>
            </div>
          </div>
        </div>
        
        
        
        
        <!-- /.col-md-4 -->
        <div class="col-md-4 mb-4">
          <div class="card h-100">
            <div class="card-body">
             <img class="icono" src="<%=request.getContextPath()%>/css/sectur/img/3.jpg" />
              <h2 class="card-title">3. Postúlate</h2>
              <p class="card-text">Ve por la vacante que quieres ¡Éxito!
</p>
            </div>
          </div>
        </div>
        <!-- /.col-md-4 -->

      </div>
      <!-- /.row -->

    </div>
    
    
    
    <!-- /.container -->
      <div class="container-fluid registro" id="publica">
      <div class="container">
      <br>
      <h1>Publica tus vacantes</h1>
      <p class="intro">Para que tu empresa sea la mejor, necesitas a los mejores empleados.</p>
      
      <a class="btn btn-primary btn-lg upper-btn" href="http://www.conectatealturismo.mx/Home/CapitalHumano">Regístrate</a>

      </div>
      </div>
      
      
      <div class="container-fluid preparate">
    <div class="container">
      <br>
      <h1>Infórmate</h1>
      <p class="intro">Conoce más acerca del sector turístico.
</p>
      <br/>
      <!-- Content Row -->
      <div class="row">
        
         <div class="col-md-3 mb-3">
          <div class="h-100">
             <a href="${pageContext.request.contextPath}/articulo.do?id=5724">
            <div class="card-body">
              <p class="card-text">Trabaja en el sector turístico y amplia tus horizontes</p>

            </div>
              </a>
          </div>
        </div>
        
         <div class="col-md-3 mb-3">
          <div class="h-100">
             <a href="${pageContext.request.contextPath}/articulo.do?id=5725">
            <div class="card-body">
              <p class="card-text">Movilidad laboral</p>
            </div>
              </a>
          </div>
        </div>
		  
         <div class="col-md-3 mb-3">
          <div class="h-100">
             <a href="${pageContext.request.contextPath}/articulo.do?id=5726">
            <div class="card-body">
              <p class="card-text">Forma parte del éxito turístico y juntos rompamos más récords</p>
            </div>
              </a>
          </div>
        </div>

         <div class="col-md-3 mb-3">
          <div class="h-100">
             <a href="${pageContext.request.contextPath}/articulo.do?id=5726">
            <div class="card-body">
              <p class="card-text">Carreras que impulsan al turismo</p>
            </div>
              </a>
          </div>
        </div>
		  
		  
		  
      </div>
      <!-- /.row -->

    </div>
	  </div>
	  
	  
	  
	  
	  <!-- Footer -->
    <footer>
      <div class="container">
      
      	<div class="row">
      
         <div class="col-md-3 logo">
			<img  src="<%=request.getContextPath()%>/css/sectur/img/logo_stps.jpg">
            </div>
            
         <div class="col-md-3 logo">
			<img  src="<%=request.getContextPath()%>/css/sectur/img/logo_sectur.jpg">
            </div>

         <div class="col-md-3 logo">
			<img  src="<%=request.getContextPath()%>/css/sectur/img/logo_sne.jpg">
            </div>

                                    
         <div class="col-md-3 logo">
			<img  src="<%=request.getContextPath()%>/css/sectur/img/logo_conec.jpg">
            </div>
            
            
</div>          </div>
      <!-- /.container -->
    </footer>

    <!-- Bootstrap core JavaScript -->
    <script src="<%=request.getContextPath()%>/css/sectur/jquery/jquery.min.js"></script>
    <script src="<%=request.getContextPath()%>/css/sectur/bootstrap/js/bootstrap.bundle.min.js"></script>


<script>
$(function() {
    $('a[href*=#]:not([href=#])').click(function() {
        var target = $(this.hash);
        target = target.length ? target : $('[name=' + this.hash.substr(1) +']');
        if (target.length) {
            $('html,body').animate({
              scrollTop: target.offset().top
            }, 1000);
            return false;
        }
    });
});
</script>
	
  </body>

</html>
