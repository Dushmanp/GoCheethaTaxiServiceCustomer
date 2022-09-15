package com.icbtassignment.gct.controllers;

import java.io.IOException;
import java.sql.Date;
import java.util.List;


import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.google.gson.Gson;
import com.icbtassignment.gct.entities.Booking;

import com.icbtassignment.gct.entities.City;
import com.icbtassignment.gct.entities.Street;
import com.icbtassignment.gct.entities.VehicleCategory;
import com.icbtassignment.gct.services.BookingService;
import com.icbtassignment.gct.services.CityServices;
import com.icbtassignment.gct.services.CommonServices;
import com.icbtassignment.gct.services.StreetService;
import com.icbtassignment.gct.services.VehicleCategoryService;
import com.icbtassignment.gct.utils.EntityValidator;

@WebServlet("/BookingControllerServlet")
public class BookingControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Resource(name ="jdbc/gocheetha_application")
	private DataSource dataSource;
	
  
    public BookingControllerServlet() {
        super();
      
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String command =null;
		command = request.getParameter("command");
		
		if(command.equals("GETCITY") )
		{
			getCityDrp(request, response);
		}
		
		if(command.equals("GETSTREET") )
		{
			getStreetDrp(request, response);
		}
		
		if(command.equals("GETVEHICLE") )
		{
			getVehicleDrp(request, response);
		}
		
		if(command.equals("GETVEHICLECHARGES") )
		{
			getVehicleCharges(request, response);
		}

		if(command.equals("SHOW-LIST") )
		{
			mainList(request,response);
		}
			
			
	}

	
	


	private void mainList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();
			String customer_Id = (String) session.getAttribute("Customer_Id");
			List<Booking> booking =  BookingService.getBooking(dataSource,customer_Id);
			request.setAttribute("booking_lists", booking);
			request.setAttribute("exceptionerrorshow", "d-none");
			request.getRequestDispatcher("/booking-list.jsp").forward(request, response);
		}catch (Exception e) {
			request.setAttribute("exceptionerror", e.toString());
			request.setAttribute("exceptionerrorshow", "");
			request.getRequestDispatcher("/booking-list.jsp").forward(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String command =null;
		command = request.getParameter("command");
		if(command.equals("ADDBOOKING") )
		{
			addData(request, response);
		}
		
	}
	
	private void addData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
				HttpSession session = request.getSession();
				String tableName ="booking";
				String booking_Id = CommonServices.getNumberFormat(dataSource,tableName);
				String driver_Id = "";
				String pickup_Time ="";
				String drop_Time ="";
				String customer_Id = (String) session.getAttribute("Customer_Id");
				String source_Location = request.getParameter("source_Location");
				String destinationation_Location = request.getParameter("destinationation_Location");
				String booking_Status = "Pending";
				String vehicle_category_Id = request.getParameter("vehicle_category_Id");
				String getbooking_Date = request.getParameter("booking_Date");
				String city_Id = request.getParameter("city_Id");
				Date booking_Date=Date.valueOf(getbooking_Date);

				Booking booking = new Booking(booking_Id,driver_Id,customer_Id,source_Location,destinationation_Location,pickup_Time,drop_Time,booking_Status,vehicle_category_Id,booking_Date,city_Id);
				
				EntityValidator<Booking> validator = new EntityValidator<Booking>();
				String errors = validator.validate(booking);
				request.setAttribute("exceptionerrorshow", "d-none");
				if(!errors.isEmpty()) {
					request.setAttribute("branch", booking);
					request.setAttribute("error", errors);
					request.getRequestDispatcher("/booking.jsp").forward(request, response);
				}else {
						BookingService.addBooking(dataSource, booking);
					 CommonServices.setNumberFormat(dataSource, tableName);
					 response.sendRedirect(request.getContextPath() +"/BookingControllerServlet?command=SHOW-LIST");
				}
			
			
		} catch (Exception e) {
			
			request.setAttribute("exceptionerror", e.toString());
			request.setAttribute("exceptionerrorshow", "");
			request.getRequestDispatcher("/booking.jsp").forward(request, response);
		}
		
	}

	private void getCityDrp(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		  	List<City> cityDrp =  CityServices.getCity(dataSource);
		    Gson json = new Gson();
		    String countryList = json.toJson(cityDrp);
		    response.setContentType("application/json");
		    response.setCharacterEncoding("UTF-8");
		    response.getWriter().write(countryList);
		    
		
	}
	

	private void getVehicleDrp(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String cityId =null;
		cityId = request.getParameter("cityId");
		List<VehicleCategory> vc =  VehicleCategoryService.getCategoryByCity(dataSource,cityId);
	    Gson json = new Gson();
	    String countryList = json.toJson(vc);
	    response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
	    response.getWriter().write(countryList);
		
	}
	
	private void getVehicleCharges(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String vehiclecategoryId =null;
		vehiclecategoryId = request.getParameter("vehiclecategoryId");
		VehicleCategory vc =  VehicleCategoryService.get(dataSource,vehiclecategoryId);
	    Gson json = new Gson();
	    String countryList = json.toJson(vc);
	    response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
	    response.getWriter().write(countryList);
		
	}

	private void getStreetDrp(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String cityId =null;
		cityId = request.getParameter("cityId");
		List<Street> streetDrp =  StreetService.getStreetByCity(dataSource,cityId);
	    Gson json = new Gson();
	    String countryList = json.toJson(streetDrp);
	    response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
	    response.getWriter().write(countryList);
		
	}

}
