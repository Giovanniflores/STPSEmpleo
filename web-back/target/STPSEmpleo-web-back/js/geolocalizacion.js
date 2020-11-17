var map;
var mapCenter;
var marker;

var cgsneLocation = {lat : 19.3967626, lng : -99.1567424};

function loadCurrentLocation(){
	// ... check if your browser supports W3C Geolocation API ...
	if (navigator.geolocation) {
		navigator.geolocation.getCurrentPosition(locationFound, 
				handleError);
	} else {
		alert("Geolocation not available");
	}
}


function handleError(error) {
	alert('Error: ' + error.message + ' (' + error.code + ')');
}

function locationFound(position) {        
	// Center map on your location ...        
	mapCenter = new google.maps.LatLng(position.coords.latitude, 
			position.coords.longitude);
	map.setZoom(17);
	map.setCenter(mapCenter);      
	placeMarker(mapCenter);
} 

function loadGoogleMaps(){
	// Create new Google Map using the div having id 'map' ...
	map = new google.maps.Map(document.getElementById('map'), 
			{      zoom: 8, 
		center: {lat: cgsneLocation.lat, lng: cgsneLocation.lng},
		mapTypeId: google.maps.MapTypeId.ROADMAP });

	var input = document.getElementById('pac-input');
	var searchBox = new google.maps.places.SearchBox(input);
	map.controls[google.maps.ControlPosition.TOP_LEFT].push(input);

	// Bias the SearchBox results towards current map's viewport.
	map.addListener('bounds_changed', function() {
		searchBox.setBounds(map.getBounds());
	});

	google.maps.event.addListener(map, 'click', function(event) {
		placeMarker(event.latLng);
	});

	var markers = [];
	// Listen for the event fired when the user selects a prediction and retrieve
	// more details for that place.
	searchBox.addListener('places_changed', function() {
		var places = searchBox.getPlaces();
		if (places.length == 0) {
			return;
		}

		// Clear out the old markers.
		markers.forEach(function(marker) {
			marker.setMap(null);
		});
		markers = [];

		// For each place, get the icon, name and location.
		var bounds = new google.maps.LatLngBounds();
		places.forEach(function(place) {
			var icon = {
					url: place.icon,
					size: new google.maps.Size(71, 71),
					origin: new google.maps.Point(0, 0),
					anchor: new google.maps.Point(17, 34),
					scaledSize: new google.maps.Size(25, 25)
			};

			// Create a marker for each place.
			markers.push(new google.maps.Marker({
				map: map,
				icon: icon,
				title: place.name,
				position: place.geometry.location
			}));

			if (place.geometry.viewport) {
				// Only geocodes have viewport.
				bounds.union(place.geometry.viewport);
			} else {
				bounds.extend(place.geometry.location);
			}
		});
		if(places[0]){
			placeMarker(places[0].geometry.location);
		}

		map.fitBounds(bounds);
	});
	
}




function placeMarker(location) {
	if(marker){
		marker.setMap(null);
	}
	marker = new google.maps.Marker({
		position: location, 
		map: map
	});
	fillLatLng(location);
}

function geocodeAddress(resultsMap, address) {
	var geocoder = new google.maps.Geocoder();
	geocoder.geocode({'address': address}, function(results, status) {
		if (status === google.maps.GeocoderStatus.OK) {
			resultsMap.setCenter(results[0].geometry.location);
			resultsMap.setZoom(17);
			placeMarker(results[0].geometry.location);
		} else {
			alert('Geocode was not successful for the following reason: ' + status);
		}
	});
}

function fillLatLng(position){
	console.log("Lat: " + position.lat() + " Long: " + position.lng());	
}