<!-- header section strats -->
    <header class="header_section">
      <div class="container-fluid">
        <nav class="navbar navbar-expand-lg custom_nav-container ">
          <a class="navbar-brand" href="index.html">
            <span>
            
							<%  
							String name=(String)session.getAttribute("Customer_Name"); 
                  			if(name =="" || name == null){
                  				 
                  			}else{
							out.print("Hello "+name);  
                  			} 
							%>  
            </span>
          </a>
          <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
          </button>

          <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <div class="d-flex ml-auto flex-column flex-lg-row align-items-center">
              <ul class="navbar-nav  ">
                <li class="nav-item active">
                  <a class="nav-link" href="index.jsp">Home <span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item">
                  <a class="nav-link" href="about.jsp"> About</a>
                </li>
               
                <li class="nav-item">
                  <a class="nav-link" href="BookingControllerServlet?command=MAKE-BOOKING">Booking</a>
                </li>
                <li class="nav-item">
                  <a class="nav-link" href="login.jsp">Login</a>
                </li>
                 <li class="nav-item">
                 
                  <a class="nav-link" href="CustomerControllerServlet">Log Out</a>
                </li>
                
              </ul>
            </div>
          </div>
        </nav>
      </div>
    </header>
    <!-- end header section -->