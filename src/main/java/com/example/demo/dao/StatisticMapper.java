package com.example.demo.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import com.example.demo.dto.MonthlyLoginDTO;
import com.example.demo.dto.organizationLoginDTO;
 
//import com.example.demo.dto.StatisticDto;
 
public interface  StatisticMapper {
    public HashMap<String, Object> selectYearLogin(String year);
    
    public HashMap<String, Object> selectYearMonthLogin(String yearMonth);
    
    public HashMap<String, Object> selectYearMonthDayLogin(String yearMonth);

	public HashMap<String, Object> selectMonthlyLogin(MonthlyLoginDTO yearMonth);
	
	public HashMap<String, Object> selectExceptHolidaysLogin(String yearMonth);

	public LinkedHashMap<String, Object> selectOrganizationLogin(organizationLoginDTO info);
	
	public List<Date> selectLoginNumInPeriod(String startDate, String endDate);
 
}