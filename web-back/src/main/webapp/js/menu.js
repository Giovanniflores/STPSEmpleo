( function( $ ) {
$( document ).ready(function() {
$('#cssmenu li.has-sub>a').on('click', function(){
		$(this).removeAttr('href');
		var element = $(this).parent('li');
		if (element.hasClass('open')) {
			element.removeClass('open');
			element.find('li').removeClass('open');
			element.find('ul').slideUp();
		}
		else {
			element.addClass('open');
			element.children('ul').slideDown();
			element.siblings('li').children('ul').slideUp();
			element.siblings('li').removeClass('open');
			element.siblings('li').find('li').removeClass('open');
			element.siblings('li').find('ul').slideUp();
		}
	});

	
	 $("#cssmenu a").each(function(){
	      if($(this).attr("href")==window.location.pathname)
	    	var active = $(this).addClass("active");
	      	var element = $(active).parent().parent();
	      	var element2 = $(element).css('display','block');
	      	var element3 = $(element2).parent().parent();
	      	$(element3).css('display','block').parent().addClass('active');
	  });
	  
	  $('#menuToggle').click(function(e) {
			$(this).toggleClass('activeMenu');
			$('.menuContent').toggleClass('activeMenu');

			e.preventDefault();
		});
	
});
} )( jQuery );
