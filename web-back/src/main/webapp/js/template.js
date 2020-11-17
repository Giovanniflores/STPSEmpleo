( function( $ ) {
$( document ).ready(function() {
	$('#facebookShare').click(function() {
		var initialPage = window.location.href;
		window.open('https://www.facebook.com/sharer/sharer.php?u=' + initialPage);
		console.log(compartir);
	})
	
	$( "#btnRecomienda" ).click(function() {
		$('#recomientaPE').slideToggle();
	});
	
	$('#cancelaRecomendar').click(function(){
		$('#recomientaPE').slideUp();
	});
	
	$('.subir').click(function(){
	    $('body,html').animate({scrollTop : 0}, 500);
	    return false;
	});
	
	 $('.encuentraOfertas').click(function(e) {
		$('.encuentraOfertasList').toggleClass('activeFooter');
		e.preventDefault();
	});
	 
	 $('.aumentaPosibilidades').click(function(e) {
			$('.aumentaPosibilidadesList').toggleClass('activeFooter');
			e.preventDefault();
		});
	 
	 $('.serviciosEmpresa').click(function(e) {
			$('.serviciosEmpresaList').toggleClass('activeFooter');
			e.preventDefault();
		});
	 

    function employ() {
        document.ocp.searchPlace.value = document.ocp.searchPlace.value;
        document.ocp.searchQ.value = document.ocp.searchTopic.value;
        document.ocp.submit();
    }
    
	 
	
});
} )( jQuery );



