package com.icbtassignment.gct.services;

import java.util.List;

import javax.sql.DataSource;

import com.icbtassignment.gct.dao.BookingDao;

import com.icbtassignment.gct.entities.Booking;


public class BookingService {
	
	public static List<Booking> getBooking(DataSource dataSource,String customerId){
			
			return BookingDao.getBooking(dataSource,customerId);
		}
	public static void addBooking(DataSource dataSource, Booking booking) {
		BookingDao.addBooking(dataSource,booking);
		
	}

}
