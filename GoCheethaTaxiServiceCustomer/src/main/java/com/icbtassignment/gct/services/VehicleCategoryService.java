package com.icbtassignment.gct.services;

import java.util.List;

import javax.sql.DataSource;


import com.icbtassignment.gct.dao.VehicleCategoryDao;

import com.icbtassignment.gct.entities.VehicleCategory;


public class VehicleCategoryService {
	
	public static List<VehicleCategory> getCategory(DataSource dataSource){
		
		return VehicleCategoryDao.getCategory(dataSource);
	}
	
	public static VehicleCategory get(DataSource dataSource, String vehicle_category_Id) {
		
		return VehicleCategoryDao.get(dataSource,vehicle_category_Id);
	}
	
	public static List<VehicleCategory> getCategoryByCity(DataSource dataSource, String cityId){
		return VehicleCategoryDao.getCategoryByCity(dataSource,cityId);
	}

	
		
	
}
