<!DOCTYPE html>
<html>

<head>
	 <title>Register - GoCheetha Taxi</title>
	<jsp:include page="header.html"></jsp:include>
 

</head>
	<body class="sub_page">
	
	  <div class="hero_area">
	   <jsp:include page="hero_area_header.jsp"></jsp:include>
	  </div>
	
		<%
		   response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");
			if(session.getAttribute("Customer_Id") != null  ){
				response.sendRedirect("login.jsp");
				
			}
		%>
	
	
	  <!-- contact section -->
	
	  <section class="contact_section layout_padding">
	    <div class="container">
	    
    
	     
	      
	    </div>
	    
	    <div class="container-fluid">
	      <div class="row">
	        <div class="col-md-5  offset-md-1">
	          <div class="contact_form">
	            <h4>
	              Register
	            </h4>
	             <div class="alert alert-danger col-md-12 ${exceptionerrorshow}" id="divShowError" role="alert">
					<span id="showerrormsg"class="text-danger">${exceptionerror}</span>
				</div>
	            <form action="CustomerControllerServlet" method="post">
	            <input type="hidden" name="command" value="REGISTER">
	            <div class="form-group row d-none">
						<label class = "col-4 col-form-label text-light" for="txtcustomer_Id">Id</label>
                        <div class="col-8"> 
                         	<input type="text" class="form-control" name="customer_Id" id="txtcustomer_Id" readonly="readonly" value="${customer.customer_Id }" placeholder="Enter your Name">
								
						</div>
                   	</div>
	              
	              <div class="form-group row">
						<label class = "col-4 col-form-label text-light" for="txtcustomer_Name">Name</label>
                        <div class="col-8"> 
                         	<input type="text" class="form-control" name="customer_Name" id="txtcustomer_Name" value="${customer.customer_Name }" placeholder="Enter your Name">
								
						</div>
                   	</div>
                   	
                   	<div class="form-group row">
						<label class = "col-4 col-form-label text-light" for="txtphone_No">Mobile Num.</label>
                        <div class="col-8"> 
                         	<input type="text" class="form-control" name="phone_No" id="txtphone_No" value="${customer.phone_No }" placeholder="Enter Mobile Number">
								
						</div>
                   	</div>
                   	
                   	<div class="form-group row">
						<label class = "col-4 col-form-label text-light" for="txtpassword">Password</label>
                        <div class="col-8"> 
                         	<input type="text" class="form-control" name="password" id="txtpassword" value="${customer.password }" placeholder="Enter Password">
								
						</div>
                   	</div>
                   	
                   		<div class="form-group row">
						<label class = "col-4 col-form-label text-light" for="txtemail">Email</label>
                        <div class="col-8"> 
                         	<input type="email" class="form-control" name="email" id="txtemail" value="${customer.email }" placeholder="Enter Email">
								
						</div>
                   	</div>
                   	
                   	<div class="form-group row has-error has-feedback text-warning">
						<label for="errorInput">${error }</label>
                   	</div>
			                    	
	             
	              <button type="submit" class="btn btn-primary ">Register</button>
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

		show_Error();
    });
	
	function show_Error() {
		var showerror = $("#showerrormsg").text();
		if(showerror ==""){
			$("#divShowError").hide();
		}
		
		
	}
	
	
	</script>
	</body>

</html>