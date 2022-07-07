package com.example.demo.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface StatisticService {
    public HashMap<String,Object> yearloginNum (String year);
    
    public HashMap<String,Object> yearMonthloginNum (String yearMonth);
    
    public HashMap<String,Object> yearMonthDayloginNum (String yearMonthDay);
    
    public HashMap<String,Object> averageloginNum (String year, String month);
    
    public Map<String, Object> weekloginNum(String yearMonth);

	public Map<String, Object> organizationloginNum(String organization, String yearMonth);

	public HashMap<String,Object> get_num_users_except_holiday(String startDate, String endDate);
	
	public List<String> get_holidays(String startDate, String endDate) throws IOException;
    
}
