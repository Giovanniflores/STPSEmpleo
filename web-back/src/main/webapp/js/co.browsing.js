 ((function(){
	 
	 var load=function(){
		 var script="https://s.acquire.io/a-d5258/init.js?full";
         var x=document.createElement('script');
         x.src=script;x.async=true;
         var sx=document.getElementsByTagName('script')[0];
         sx.parentNode.insertBefore(x, sx);
         /*API_CALL_HERE*/
	 };
	 if(document.readyState === "complete"){
		 load();
	 }
	 else if (window.addEventListener){
    	 window.addEventListener('load',load,false);		 
	 }  // W3C DOM
	 else if (window.attachEvent) { // IE DOM
      	 window.attachEvent("onload", load);
     }
	 
})())