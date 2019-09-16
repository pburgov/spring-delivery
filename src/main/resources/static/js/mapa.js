var initialLatLng = {
    lat: 42.180298,
    lng: -8.706831
};
var map;

var markers = [];
var polis = [];
var colorlist = ['red', 'orange', 'green', 'purple', 'blue', 'navy', 'yellow',
    'gold', 'brown', 'pink', 'HotPink', 'DarkSalmon', 'Olive', 'OrangeRed',
    'Salmon', 'YellowGreen', 'Khaki', 'Indigo', 'FireBrick', 'Black',
    'BlueViolet'];
var name;
var code;
var matricula;
var cliente;
var hora_descarga;
var fotos;
var styles = [];
var ic_color;
var i;

function initMap() {
    var element = document.getElementById("map");
    var mapTypeIds = [];
    for (var type in google.maps.MapTypeId) {
          mapTypeIds.push(google.maps.MapTypeId[type]);
    }

     map = new google.maps.Map(element, {
        center: initialLatLng,
        zoom: 11,
        mapTypeId: "hybrid",
        mapTypeControlOptions: {
            mapTypeIds: mapTypeIds
        }
    });
}

function addMarkRepartos() {
    var infowindow = new google.maps.InfoWindow({
        content: "",
        borderRadius: 10
    });
    var bounds = new google.maps.LatLngBounds();
    var jsonObject = JSON.parse(document.getElementById('jsonMap').value);
    for (var i = 0; i < jsonObject.length; i++) {
        var reparto = jsonObject[i].reparto;
        var detalle = jsonObject[i].detalle;
        var matricula = reparto.reparto;
        var ic_color = colorlist[i];
        var path = [];
        for (var j = 0; j < detalle.length; j++) {
            var reparto_detalle_id = detalle[j].reparto_detalle_id;
            var cliente = detalle[j].cliente;
            var comment_back = detalle[j].comment_back;
            var latcliente = detalle[j].lat;
            var longcliente = detalle[j].lng;
            var hora_descarga = detalle[j].hora_descarga;
            var fotos = detalle[j].fotos;
            var icon = {
                path: google.maps.SymbolPath.CIRCLE,
                scale: 5,
                strokeColor: ic_color
            };
            if (fotos !== 0) {
                icon = contextRoot + 'images/photo_marker.png';
            }
            var position = new google.maps.LatLng(latcliente, longcliente);
            path.push(position);
            bounds.extend(position);

            var photoIcon = '';
            var mapIcon = '';
            if (fotos !== 0) {
                photoIcon = '<a href="#" onclick="viewFotos(\'' + fotos + '\');" '
                    + 'class="btn btn-success btn-sm"><i class="fas fa-camera"></i></a>';
                mapIcon = '<i class="fas fa-camera"></i>';
            }

            var contentString = '<div class="card">'
                + '<div class="map card-header">'
                + cliente
                + '</div>'
                + '<div class="map card-body">'
                + '<p class="card-text"><b>Delivery Time:</b> '
                + hora_descarga
                + '<p class="card-text"><b>Comment:</b> '
                + comment_back
                + '</p>'
                + photoIcon
                + '</div></div>';


            var marker = new Marker({
                position: position,
                map: map,
                icon: {
                    path: MAP_PIN,
                    fillColor: '#28a745',
                    fillOpacity: 0.75,
                    strokeColor: '',
                    strokeWeight: 0
                },
                map_icon_label: mapIcon
            });

            bindInfoWindow(marker, map, infowindow, contentString);
            markers.push(marker);
        }
        var polyline = new google.maps.Polyline({
            path: path,
            geodesic: true,
            strokeColor: ic_color,
            strokeOpacity: 1.0,
            strokeWeight: 2
        });
        polis.push(polyline);
        polyline.setMap(map);
    }
    map.fitBounds(bounds);
    removeLegend();
    setLegend(jsonObject);
}

function bindInfoWindow(marker, map, infoWindow, content) {
    marker.addListener('click', function () {
        infoWindow.setContent(content);
        infoWindow.open(map, marker);
    });
}

function setLegend(jsonObject) {
    var legend = document.createElement('div');
    legend.id = 'legend';
    map.controls[google.maps.ControlPosition.RIGHT_TOP].push(legend);

    for (var i = 0; i < jsonObject.length; i++) {
        var reparto = jsonObject[i].reparto;
        var chofer = reparto.chofer;
        var matricula = reparto.matricula;
        var co = colorlist[i];
        var div = document.createElement('div');
        var text = matricula + '--' + chofer;
        div.innerHTML = '<p><div class="color" style="background:' + co
            + '"></div>' + text + '</p>';
        legend.appendChild(div);
    }
}


function removeLegend() {
    var legend = document.getElementById('legend');
    if (legend !== null) {
        var child = document.getElementById("legend");
        child.parentNode.removeChild(child);
    }
}

function setMapOnAll(map) {
    for (var i = 0; i < markers.length; i++) {
        markers[i].setMap(map);
    }
}

function setMapOnAllPolis(map) {
    for (var i = 0; i < polis.length; i++) {
        polis[i].setMap(map);
    }
}


function deleteMarkers() {
    setMapOnAll(null);
    markers = [];
    styles = [];
}

function deleteMarkersRepartos() {
    setMapOnAll(null);
    setMapOnAllPolis(null);
    markers = [];
    polis = [];
}

function deleteRepartos() {
    setMapOnAll(null);
    markers = [];
    removeLegend();
}

function addstyle(numruta, nombreruta) {
    var col = colorlist[numruta - 1];
    var icon = '{path: google.maps.SymbolPath.CIRCLE,scale: 5,strokeColor:'
        + col + '}';
    styles.push({
        numruta: numruta,
        nombreruta: nombreruta,
        color: col,
        icon: icon
    });
}


function getRandomColor() {
    var letters = '0123456789ABCDEF'.split('');
    var color = '#';
    for (var i = 0; i < 6; i++) {
        color += letters[Math.floor(Math.random() * 16)];
    }
    return color;
}


