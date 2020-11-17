/**
 * 
 */



dojo.declare("Monitor", null,{
	_events : [ [ window, 'scroll' ], [ window, 'resize' ],
			[ document, 'mousemove' ], [ document, 'keydown' ] ],
	_idleTime : null,
	_timers : null,
	idleTime : null,

	constructor : function(time, timers) {
		Monitor.prototype.time = time;
		this._timers = timers;
		this.initObservers();
		this.setTimer();
	},
	initObservers : function() {
		dojo.forEach(this._events, function(e) {
			dojo.connect(e[0], e[1], function(event) {
				Monitor.prototype.onInterrupt();
			});
		})
	},
	onInterrupt : function() {
		this.idleTime = new Date() - this._idleTime;
		dojo.publish("state:active", [ this.idleTime ]);
		this.setTimer();
	},
	setTimer : function() {
		var oj = Monitor.prototype;
		this.clearTimers();
		this._idleTime = new Date();

		this._timers.push(setTimeout(function() {
			dojo.publish("state:idle", null);
		}, oj.time));

	},
	clearTimers : function() {
		if (this._timers) {
			for (var i = 0; i < this._timers.length; i++) {
//				console.debug("Clearing Timer: " + this._timers[i]);
				clearTimeout(this._timers[i]);
			}
		}
		this._timers = new Array();
	}
});

var timerArray = new Array();

		dojo.addOnLoad(function() {
			var timeout = 840000; // Timeout is 15 minutes //900 000 15 // minutes
			//var timeout = 5000; // prueba
			var monitor = new Monitor(timeout, timerArray);

			dojo.subscribe("state:active", null, onActive);
			dojo.subscribe("state:idle", null, onIdle);

			function onActive(args) {
				// dojo.query("p").style("opacity",1);
				// console.debug("active... after: " + args + "ms");
				monitor.clearTimers();
			};
			
			function onIdle() {
				// dojo.query("p").style("opacity",0.2);

//				console.debug("idle...logging out");
//				var logoutTimeout = setTimeout(logoutNow, 60000); // Show the
																	// logout
																	// dialog
				// for 1 minute. Give time
				// to reset.
				raiseQueryDialog(
						"Su sesión ha expirado",
						"La sesión ha expirado, es necesario volver a iniciar sesión",
						function(isContinue) {

							if (isContinue) {
//								clearTimeout(logoutTimeout);
								monitor.clearTimers();

							} else {

								logoutNow();
							}

						});

				// window.location.href='j_spring_security_logout?inactive=y';

			}
			;
		});

var logoutNow = function() {

	window.location.href = 'https://qa.empleo.gob.mx/';

}

var randomString = function randomString() {
	var chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXTZabcdefghiklmnopqrstuvwxyz";
	var string_length = 8;
	var randomstring = '';
	for (var i = 0; i < string_length; i++) {
		var rnum = Math.floor(Math.random() * chars.length);
		randomstring += chars.substring(rnum, rnum + 1);
	}
	return randomstring;
}

function raiseQueryDialog(title, question, callbackFn, e) {

	var randomId = randomString();
	var html =
		'<div id="messageDialgop2" name="Dialgop2">' +
		'<p style="text-align: center;">'+ question +
		'</p>' + '</div>';
	
	var errorDialog = new dijit.Dialog({
		id : randomId,
		title : title,
		showTitle: false, draggable : false, closable : false
	});
	// When either button is pressed, kill the dialog and call the callbackFn.
	var commonCallback = function(mouseEvent) {
		errorDialog.hide();
		errorDialog.destroyRecursive();
		// alert(mouseEvent.explicitOriginalTarget.nodeName);

		if (window.event)
			e = window.event;

		var srcEl = mouseEvent.srcElement ? mouseEvent.srcElement
				: mouseEvent.target;

		if (srcEl.id == 'idAceptar' + randomId) {
			callbackFn(true, e);
		} else {
			callbackFn(false, e);
		}
	};
	var questionDiv = dojo.create('div', {
		innerHTML : html
	});
	var yesButton = new dijit.form.Button({
		label : 'Aceptar',
		id : 'idAceptar' + randomId,
		baseClass: "btnSession",
		onClick : commonCallback
		
	});
	
	
	
	errorDialog.containerNode.appendChild(questionDiv);
	errorDialog.containerNode.appendChild(yesButton.domNode);
	errorDialog.show();
}