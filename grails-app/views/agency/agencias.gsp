<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'agency.label', default: 'Agency')}" />
    <title><g:message code="default.list.label" args="[entityName]" /></title>

</head>
    <table class="table">
        <tr>
            <th>Descripción</th>
            <th>Distancia en Metros</th>
            <th>Acciones</th>
        </tr>
        <g:each var="agency" in="${agenciesList}">
            <tr>
                <td>${agency.description}</td>
                <td><g:formatNumber number="${agency.distance}" roundingMode="HALF_UP"/></td>
                <td>
                    <button class="btn btn-primary" onclick="getDescription(${agency.agencyCode})" type="button">
                        Ver detalle
                    </button>
                </td>
            </tr>
            <g:render template="layouts/modal" model="['id':agency.agencyCode, 'agency':agency]"/>
        </g:each>
    </table>

    <div id="map" class="map-agency"></div>

    <script>
        function initMap() {
            var location = {lat: ${location.lat as Double}, lng: ${location.lng as Double}};
            var map = new google.maps.Map(document.getElementById('map'), {
                center: location,
                zoom: 15
            });

            var marker = new google.maps.Marker({
                position: location,
                icon:"http://maps.google.com/mapfiles/ms/icons/blue-dot.png",
                map: map
            });

            <g:each var="agency" in="${agenciesList}">
                var marker = new google.maps.Marker({
                    position: {lat: ${agency.address.location.lat}, lng: ${agency.address.location.lng}},
                    map: map
                });
            </g:each>
        }
    </script>
    <script async defer
            src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBXTadEqQfAquGVgWKScmjIDqHkEd0rlIw&callback=initMap">
    </script>

    <script>
        function getDescription(id){
            $('#myModal'+id).modal('toggle').modal('show');
        }
    </script>
</body>
</html>