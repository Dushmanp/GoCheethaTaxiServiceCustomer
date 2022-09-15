package com.icbtassignment.gct.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import com.icbtassignment.gct.entities.Customer;

import com.icbtassignment.gct.services.CommonServices;
import com.icbtassignment.gct.services.CustomerService;
import com.icbtassignment.gct.utils.EntityValidator;


@WebServlet("/CustomerControllerServlet")
public class CustomerControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Resource(name ="jdbc/gocheetha_application")
	private DataSource dataSource;
	
    public CustomerControllerServlet() {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String command =null;
		command = request.getParameter("command");
		
		if(command == null )
		{
			HttpSession session = request.getSession();
			session.invalidate();
			response.sendRedirect("login.jsp");
		}
		else if(command.equals("SHOW-BOOKING") )
		{
			response.sendRedirect("booking.jsp");
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String command =request.getParameter("command");
		if(command.equals("REGISTER") )
		{
			register(request,response);
		}
		
		if(command.equals("LOGIN") )
		{
			login(request,response);
		}
	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		PrintWriter out = response.getWriter();
		String phone_No = request.getParameter("phone_No");
		String password = request.getParameter("password");
		Customer cust = new Customer(phone_No,password);
		
		try {
		
		if(CustomerService.checkCredinatial(dataSource,cust)) {
			
			
			Customer customer = CustomerService.getCustomer(dataSource, phone_No);
			HttpSession session = request.getSession();
			session.setAttribute("Customer_Id", customer.getCustomer_Id());
			session.setAttribute("Customer_Name", customer.getCustomer_Name());
			session.setAttribute("Phone_No", customer.getPhone_No());
			response.sendRedirect("booking.jsp");
		}
		else {
			request.setAttribute("exceptionerror","User Credinatial incorrect");
			request.setAttribute("exceptionerrorshow", "");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
	} catch (Exception e) {
		
		request.setAttribute("exceptionerror", e.toString());
		request.setAttribute("exceptionerrorshow", "");
		request.getRequestDispatcher("/login.jsp").forward(request, response);
		
	}
		
	}
	
	private void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String tableName ="customer";
			
			String phone_No = request.getParameter("phone_No");
			
			try {
				if(CustomerService.checkCustomerPhoneNo(dataSource,phone_No)) {
					
					
						String customer_Id = CommonServices.getNumberFormat(dataSource,tableName);
						String customer_Name = request.getParameter("customer_Name");
						String password = request.getParameter("password");
						String email = request.getParameter("email");
						Customer cust = new Customer(customer_Id,customer_Name,phone_No,password,email);
						
						EntityValidator<Customer> validator = new EntityValidator<Customer>();
						String errors = validator.validate(cust);
						
						if(!errors.isEmpty()) {
							request.setAttribute("customer", cust);
							request.setAttribute("error", errors);
							request.getRequestDispatcher("/register.jsp").forward(request, response);
						}else {
							CustomerService.addCustomer(dataSource, cust);
							CommonServices.setNumberFormat(dataSource, tableName);
							 response.sendRedirect("login.jsp");
						}
					
				}
				else {
					request.setAttribute("exceptionerror","Mobile number already exists");
					request.setAttribute("exceptionerrorshow", "");
				
					request.getRequestDispatcher("/register.jsp").forward(request, response);
				}
			} catch (Exception e) {
				
				request.setAttribute("exceptionerror", e.toString());
				request.setAttribute("exceptionerrorshow", "");
				request.getRequestDispatcher("/register.jsp").forward(request, response);
		}
			
			
			
		
		
	}

}
