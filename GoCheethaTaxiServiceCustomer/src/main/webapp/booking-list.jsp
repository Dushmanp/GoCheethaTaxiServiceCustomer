<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
	 <title>Booking List - GoCheetha Taxi</title>
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
	
<div class="container mt-5 mb-3">
    <div class="row">
     <div class="alert alert-danger col-md-12 ${exceptionerrorshow}" id="divShowError" role="alert">
						<span id="showerrormsg"class="text-danger">${exceptionerror}</span>
					</div>
	             
    <c:forEach  items="${booking_lists}" var="booking_list">
        <div class="col-md-4">
            <div class="card p-3 mb-2">
                <div class="d-flex justify-content-between">
                    <div class="d-flex flex-row align-items-center">
                        <div class="icon"> <i class="bx bxl-mailchimp"></i> </div>
                        <div class="ms-2 c-details">
                            <h5 class="mb-0">${booking_list.booking_Id}</h5> <span>${booking_list.vehicle_type_Name} ${booking_list.booking_Status}</span>
                        </div>
                    </div>
                    <div class="badge"> <h6 class="mb-0">${booking_list.booking_Date}</h6> <span>${booking_list.city_Name}</span> </div>
                </div>
                <div class="mt-2">
               
                        <div class="mt-3"> <span class="text1">PickUp : <span class="text2">${booking_list.source}</span></span> </div>
               			<div class="mt-3"> <span class="text1">Drop By : <span class="text2">${booking_list.destinationation}</span></span> </div>
						<div class="mt-3"> <span class="text1">Driver : <span class="text2">${booking_list.driver_Name}</span></span> </div>
 						<div class="mt-3"> <span class="text1">Driver Mobile : <span class="text2">${booking_list.driver_phone_No}</span></span> </div>
 						<div class="mt-3"> <span class="text1">Vehicle No : <span class="text2">${booking_list.vehicle_No}</span></span> </div>

                </div>
                <div class="mt-2">
               
                        <div class="mt-3"> <span class="text1">PickUp Time : <span class="text2">${booking_list.pickup_Time}</span></span> </div>
               			<div class="mt-3"> <span class="text1">Drop Time : <span class="text2">${booking_list.drop_Time}</span></span> </div>
						<div class="mt-3"> <span class="text1">Per KM Charge : <span class="text2">${booking_list.charge_per_Km}</span></span> </div>
 						<div class="mt-3"> <span class="text1">Service Charge : <span class="text2">${booking_list.service_Charge}</span></span> </div>
 						
 						<div class="mt-3"> <span class="text1">KM Covered : <span class="text2">${booking_list.km_Covered}</span></span> </div>
 						
                </div>
                  <div class="d-flex justify-content-center">
                     <div class="ms-2 c-details">
                            <h5 class="mb-0">Total charges ${booking_list.charges_Calculated}</h5> 
                        </div>
                  </div>
            </div>
        </div>
       </c:forEach>
       </div>
</div>
 <jsp:include page="footer.html"></jsp:include>
 <script>
 
	 $(document).ready(function () {
	
			
			show_Error();
			
	 });
	 function show_Error() {
			var showerror = $("#showerrormsg").text();
			if(showerror ==""){
				$("#divShowError").hide();
			}	
		}
	 
 
 </script>
 
 <!-- owl carousel script -->
  <script type="text/javascript">
    $(".owl-carousel").owlCarousel({
      loop: true,
      margin: 20,
      navText: [],
      autoplay: true,
      autoplayHoverPause: true,
      responsive: {
        0: {
          items: 1
        },
        768: {
          items: 2
        },
        1000: {
          items: 2
        }
      }
    });
  </script>
  <!-- end owl carousel script -->
</body>

</html>