<html>
<head>
<title>Contact Us</title>
<script
	src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAGcvfPFHHvaFpa_ctqbVzYRzXX0yi8vxQ&libraries=places&callback=initMap">
</script>

<script>
function initMap() {
	  const myLatLng = {lat: 1.2903, lng: 103.852};
	  const map = new google.maps.Map(document.getElementById("map"), {
	    zoom: 17,
	    center: myLatLng});
	  new google.maps.Marker({
	    position: myLatLng,
	    map});
	}
google.maps.event.addDomListener(window, 'load', initMap);
</script>
</head>
<body>
 <%@ include file="Master/preloginheader.jsp" %>  
<h1>Contact Us</h1>
Our office is located at 1 St Andrew's Rd, National Gallery, #01-30 Singapore 178957.<br></br>
Alternatively you can reach us at <a href = "mailto: VRSystem@gmail.com">VRSystem@gmail.com</a> or call us at 67480299.
<br></br>
<div id="map" style="height: 400px; width: 650px"></div>
<a href="login.jsp">Click Here to return to Login Page.</a>
<br></br>
<a href="Register.jsp">Click Here to return to Registration Page.</a>
<jsp:include page="/Master/footer.jsp"/>
</body>
</html>

