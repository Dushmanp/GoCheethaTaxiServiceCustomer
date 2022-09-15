package com.icbtassignment.gct.services;

import java.util.List;

import javax.sql.DataSource;

import com.icbtassignment.gct.dao.CityDao;
import com.icbtassignment.gct.entities.City;

public class CityServices {
	
	public static List<City> getCity(DataSource dataSource){
		
		return CityDao.getCity(dataSource);
	}
	
	
}
