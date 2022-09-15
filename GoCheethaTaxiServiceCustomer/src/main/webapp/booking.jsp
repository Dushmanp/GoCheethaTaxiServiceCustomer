<!DOCTYPE html>
<html>

<head>
	 <title>Booking - GoCheetha Taxi</title>
	<jsp:include page="header.html"></jsp:include>
 

</head>

<%
   response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");
	if(session.getAttribute("Customer_Id") == null  ){
		response.sendRedirect("login.jsp");
		
	}
%>
<body class="sub_page">

  <div class="hero_area">
   <jsp:include page="hero_area_header.jsp"></jsp:include>
  </div>

  <!-- contact section -->

  <section class="contact_section layout_padding">
   
    <div class="container-fluid">
      <div class="row">
        <div class="col-md-5  offset-md-1">
        <div class="contact_form">
	            <h4>
	              Book a Taxi
	            </h4>
	            <div class="alert alert-danger col-md-12 ${exceptionerrorshow}" id="divShowError" role="alert">
						<span id="showerrormsg"class="text-danger">${exceptionerror}</span>
					</div>
	             
	            <form action="BookingControllerServlet" method="post">
	            <input type="hidden" name="command" value="ADDBOOKING">
		            
		            
	              <div class="form-group row">
						<label class = "col-4 col-form-label text-light" for="txtcustomer_Name">City</label>
                        <div class="col-8"> 						
							<select class="form-control" name="city_Id" id="drpCity">
							      <option>Select City</option>
							 </select>
						</div>
                   	</div>
                   	
                   	 <div class="form-group row" id="divdrpPickup">
						<label class = "col-4 col-form-label text-light" for="txtcustomer_Name">Pickup Location</label>
                        <div class="col-8"> 						
							<select class="form-control" name="source_Location" id="drpPickup">
							      <option>Select Pickup Street</option>
							 </select>
						</div>
                   	</div>
                   	
                   	<div class="form-group row" id="divdrpDrop">
						<label class = "col-4 col-form-label text-light" for="txtcustomer_Name">Drop Location</label>
                        <div class="col-8"> 						
							<select class="form-control" name="destinationation_Location" id="drpDrop">
							      <option>Select Drop Street</option>
							 </select>
						</div>
                   	</div>
                   	 <div class="form-group row" id="divdrpVehicle">
						<label class = "col-4 col-form-label text-light" for="txtcustomer_Name">Vehicle</label>
                        <div class="col-8"> 						
							<select class="form-control" name="vehicle_category_Id" id="drpVehicle">
							      <option>Select Vehicle</option>
							 </select>
						</div>
                   	</div>
                   	
                   	<div class="form-group row ">
						<label class = "col-4 col-form-label text-light" for="txtpassword">BookingDate</label>
                        <div class="col-8"> 
                         	<input type="date" class="form-control" name="booking_Date" id="datePicker" value="${customer.password }" placeholder="Enter Password">
								
						</div>
                   	</div>
                   	
                
                   	
                   	<div class="form-group row" id="divservice">
						<label class = "col-5 col-form-label text-light">Service Charges:</label>
                        <div class="col-5"> 
                         	<label class = " col-form-label text-light" id="txtservice">Service Charges:</label>
						</div>
                   	</div>
			             
			            
			         <div class="form-group row" id="divperkm">
						<label class = "col-5 col-form-label text-light">Charge per KM:</label>
                        <div class="col-5"> 
                         	<label class = "col-form-label text-light" id="txtperkm">Charge per KM:</label>
						</div>
                   	</div>        	
	                	
                  <div class="form-group row text-warning">
						<label id="">${error }</label>
                   	</div>
                   	
	              <button type="submit" id="btnSubmit" class="btn btn-primary ">Book Now</button>
				  <a href="BookingControllerServlet?command=SHOW-LIST" class="btn btn-secondary">View Booking</a>
	              <a href="index.jsp" class="btn btn-danger">Cancel</a>
	            </form>
	          </div>
	        
        </div>
        <div class="col-md-6 px-0">
          <div class="img-box">
            <img src="resources/images/contact-img.png" alt="">
          </div>
        </div>
      </div>
    </div>
  </section>
  <!-- end contact section -->

	
 <jsp:include page="footer.html"></jsp:include>
	<script type="text/javascript">
	
	$(document).ready(function () {

		getCity();
		getCurrentDate()
		show_Error();
		bookingbtn();
		$('#btnSubmit').prop('disabled', true)
		$('#divdrpPickup').hide();
		$('#divdrpDrop').hide();
		$('#divdrpVehicle').hide();
		$('#divperkm').hide();
		$('#divservice').hide();
    });
	
	function getCurrentDate(){
		var today = new Date().toISOString().split('T')[0];
		$("#datePicker").val(today);
	}
	
	function bookingbtn(){
		var Pickup = $('#drpPickup').val();
		var Drop = $('#divdrpDrop').val();
		var Vehicle = $('#divdrpVehicle').val();

		var City = $("#drpCity").val();
		
		if(Pickup =="" ){
		    $('#btnSubmit').prop('disabled', false);
		}
		else
			{
			$('#btnSubmit').prop('disabled', false);
			}
		
	}
	function getCity(){
		$.ajax({
            url: "BookingControllerServlet",
            method: "GET",
            data: {command: 'GETCITY'},
            success: function (result) {
                console.log(result);
                console.log(result);
                
                $.each(result, function (key, value) {
                	console.log(value.city_Id);
                    $('#drpCity').append('<option value="' + value.city_Id + '">' + value.city_Name + '</option>')
                });
                
                
            },
            error: function (result) {
           	 console.log(result);
            },
            cache: false
        });
		bookingbtn();
	}
	

	function getStreet(){
		var cityId= $("#drpCity").val();
			$.ajax({
	            url: "BookingControllerServlet",
	            method: "GET",
	            data: {command: 'GETSTREET',cityId: cityId},
	            success: function (result) {
	                console.log(result);
	                console.log(result);
	                $('#divdrpDrop').show();
	                $('#divdrpPickup').show();
	                $('#drpPickup').append('<option value> Select </option>')
	                $('#drpDrop').append('<option value> Select </option>')
	                $.each(result, function (key, value) {
	                	console.log(value.city_Id);
	                    $('#drpPickup').append('<option value="' + value.street_Id + '">' + value.street_Name + '</option>')
	                     $('#drpDrop').append('<option value="' + value.street_Id + '">' + value.street_Name + '</option>')
	                });
	                
	                
	            },
	            error: function (result) {
	           	 console.log(result);
	            },
	            cache: false
	        });
			bookingbtn();
		}
	
	
		
	function getVehicle(){
		
		var cityId= $("#drpCity").val();
		$.ajax({
            url: "BookingControllerServlet",
            method: "GET",
            data: {command: 'GETVEHICLE',cityId: cityId},
            success: function (result) {
                console.log(result);
                console.log(result);
                $('#divdrpVehicle').show();
            
                $('#drpVehicle').append('<option value> Select </option>')
                $.each(result, function (key, value) {
                	console.log(value.vehicle_category_Id);
                    $('#drpVehicle').append('<option value="' + value.vehicle_category_Id + '">' + value.vehicle_type_Name + '</option>')
                });
                
                
            },
            error: function (result) {
           	 console.log(result);
            },
            cache: false
        });
		bookingbtn();
		
	}
	
	function show_Error() {
		var showerror = $("#showerrormsg").text();
		if(showerror ==""){
			$("#divShowError").hide();
		}
		
		
	}
		
	$("#drpCity").change(function (event) {
		$('#drpPickup').empty();
		$('#drpDrop').empty();
		$('#drpVehicle').empty();
		$('#divdrpPickup').hide();
		$('#divdrpDrop').hide();
		$('#divdrpVehicle').hide();
		$('#divperkm').hide();
		$('#divservice').hide();
		$('#btnSubmit').prop('disabled', true);
		getStreet();
		getVehicle();
    });	
	
	$("#drpVehicle").change(function (event) {
		$('#divperkm').hide();
		$('#divservice').hide();
		getVehicleCharges();
		bookingbtn();
    });	
		
	function getVehicleCharges(){
			
			var vehiclecategoryId= $("#drpVehicle").val();
			$.ajax({
	            url: "BookingControllerServlet",
	            method: "GET",
	            data: {command: 'GETVEHICLECHARGES',vehiclecategoryId: vehiclecategoryId},
	            success: function (result) {
	               
	            	$("#txtservice").html(result.service_Charge);
	            	$("#txtperkm").html(result.charge_per_Km);
	            	$('#divperkm').show();
	        		$('#divservice').show();
	                	
	                
	                
	            },
	            error: function (result) {
	           	 console.log(result);
	            },
	            cache: false
	        });
	
	}
	
		</script>
</body>

</html>