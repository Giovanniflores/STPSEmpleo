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
    dojo.xhrPost({url: 'ofertasRivieraMaya.do?method='+method, timeout:180000,
                  load: function(data) {
                        dojo.byId('reporte').innerHTML = data;
                  }});
}

function doSubmitPagina(pagina, dif){
   	dojo.xhrPost({url: 'ofertasRivieraMaya.do?method=goToPage&goToPageNumber='+pagina, timeout:180000, 
				  load: function(dataCand){
				      dojo.byId('reporte').innerHTML=dataCand;
				  }});
}

function doSubmitOrden(campo, orden){
   	dojo.xhrPost({url: 'ofertasRivieraMaya.do?method=ordenaPor&campo='+ campo +'&orden='+ orden, timeout:180000, 
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

</script>
<noscript>Funciones de JavaScript desactivadas por navegador</noscript>


<!DOCTYPE html>
<html lang="es">

  <head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Tu trabajo hace al turismo - Vacantes en el sector turístico</title>

    <!-- Bootstrap core CSS -->
   
    <link href="${PATH_CSS_SWB_APP}estilos_sectur.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <!-- Custom styles for this template -->
   
    
    <!-- Fonts -->
    
    <link href="https://fonts.googleapis.com/css?family=Roboto:300,700,900" rel="stylesheet">
    <link href="fonts/font-awesome/css/font-awesome.css" rel="stylesheet">

  </head>

  <body>

    <!-- Navigation -->
<!--
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
      <div class="container">
        <a class="navbar-brand" href="#">Start Bootstrap</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
          <ul class="navbar-nav ml-auto">
            <li class="nav-item active">
              <a class="nav-link" href="#">Home
                <span class="sr-only">(current)</span>
              </a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#">About</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#">Services</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#">Contact</a>
            </li>
          </ul>
        </div>
      </div>
    </nav>
-->

    <!-- Page Content -->
    <div class="container">

      <!-- Heading Row -->
      <div class="row my-4 upper">
        <div class="col-lg-8">
          <img class="img-fluid rounded" src="css/cil/logo.png" alt="">
        </div>
        <!-- /.col-lg-8 -->
        <div class="col-lg-4 upper-text">
         <div class="set-down">          
         <h1> </h1>
          <p>Forma parte de la comunidad turística nacional y explora nuevos tipos de empleo. Muestra tu calidez, talento y compromiso para consolidar a México como referente turístico mundial.
Estas vacantes son el resultado del trabajo conjunto entre la STPS, a través del SNE, y  Sectur, mediante "Conéctate al Turismo".
 </p>
          <a class="btn btn-primary btn-lg upper-btn" href="#pleca">¿Cómo me postulo?</a>
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
      
      <div class="col-md-4">
      <label>Categoría</label>
      <input class="form-control" type="text" placeholder="Escribe una categoría..."/>
      </div>
      
      <div class="col-md-4">
      <label>Categoría</label>
      <select class="form-control" required >
      <option value="" disabled selected>Selecciona un estado...</option>
      <option value="1">1</option>
       <option value="2">2</option>
		  </select>
      </div>

<div id="cont-resultados" class="row">

	<form dojoType="dijit.form.Form" name="bolsasTrabajoForm" id="ofertasRivieraMayaForm" action="ofertasRivieraMaya.do" method="post">
		<input type="hidden" name="method" value="init"/>
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
             <img class="icono" src="css/cil/2.jpg" />
              <h2 class="card-title">Elige</h2>
              <p class="card-text">¿Playa o ciudad?
¿Provincia o CDMX?
Checa cuál es la vacante ideal para ti.</p>
            </div>
          </div>
        </div>

        
                        
         <div class="col-md-4 mb-4">
          <div class="card h-100">
            <div class="card-body">

             <img class="icono" src="css/cil/1.jpg" />
              <h2 class="card-title">Regístrate</h2>
              <p class="card-text"><a class="link" href="#">Ingresa tus datos</a> para formar parte de la comunidad turística. 
Si ya estás registrado, sólo inicia sesión.</p>
            </div>
          </div>
        </div>
        
        
        
        
        <!-- /.col-md-4 -->
        <div class="col-md-4 mb-4">
          <div class="card h-100">
            <div class="card-body">
             <img class="icono" src="css/cil/3.jpg" />
              <h2 class="card-title">Postúlate</h2>
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
      <div class="container-fluid registro">
      <div class="container">
      <h1>Publica tus vacantes</h1>
      <p class="intro">Para que tu empresa sea la mejor, necesitas a los mejores empleados.</p>
      
      <a class="btn btn-primary btn-lg upper-btn">Encuéntralos aquí.</a>

      </div>
      </div>
      
      
      <div class="container-fluid preparate">
    <div class="container">
     
      <h1>Infórmate</h1>
      <p class="intro">Conoce más acerca del sector turístico.
</p>
      <br/>
      <!-- Content Row -->
      <div class="row">
        
         <div class="col-md-3 mb-3">
          <div class="h-100">
            <div class="card-body">
              <p class="card-text">Título de artículo para vacante de Sector Turismo </p>
            </div>
          </div>
        </div>
        
        
         <div class="col-md-3 mb-3">
          <div class="h-100">
            <div class="card-body">
              <p class="card-text">Título de artículo para vacante de Sector Turismo </p>
            </div>
          </div>
        </div>
        
        
         <div class="col-md-3 mb-3">
          <div class="h-100">
            <div class="card-body">
              <p class="card-text">Título de artículo para vacante de Sector Turismo </p>
            </div>
          </div>
        </div>
        
        
         <div class="col-md-3 mb-3">
          <div class=" h-100">
            <div class="card-body">
              <p class="card-text">Título de artículo para vacante de Sector Turismo </p>
            </div>
          </div>
        </div>
        

		  

      </div>
      <!-- /.row -->

    </div>
	  </div>
	  
	  
	  
	  
	  <!-- Footer -->
    <footer class="py-5">
      <div class="container">
      
      	<div class="row">
      
         <div class="col-md-3 logo">
			<img  src="css/cil/logo_stps.jpg">
            </div>
            
         <div class="col-md-3 logo">
			<img  src="css/cil/logo_sectur.jpg">
            </div>

         <div class="col-md-3 logo">
			<img  src="css/cil/logo_sne.jpg">
            </div>

                                    
         <div class="col-md-3 logo">
			<img  src="css/cil/logo_conec.jpg">
            </div>
            
            
</div>          </div>
      <!-- /.container -->
    </footer>

    <!-- Bootstrap core JavaScript -->
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>


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
