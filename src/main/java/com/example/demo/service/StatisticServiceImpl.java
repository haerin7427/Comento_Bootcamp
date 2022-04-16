package com.example.demo.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import com.example.demo.dao.StatisticMapper;
import com.example.demo.dto.MonthlyLoginDTO;
import com.example.demo.dto.organizationLoginDTO;
 
@Service
public class StatisticServiceImpl implements StatisticService {
    
    
    @Autowired
    private StatisticMapper uMapper;
    
    @Override
    public HashMap<String, Object> yearloginNum (String year) {
        // TODO Auto-generated method stub
        HashMap<String, Object> retVal = new HashMap<String,Object>();
        
        try {
            retVal = uMapper.selectYearLogin(year);
            retVal.put("year", year);
            retVal.put("is_success", true);
            
        }catch(Exception e) {
            retVal.put("totCnt", -999);
            retVal.put("year", year);
            retVal.put("is_success", false);
        }
        
        return retVal;
    }
    
    @Override
    public HashMap<String, Object> yearMonthloginNum (String yearMonth) {
        // TODO Auto-generated method stub
        HashMap<String, Object> retVal = new HashMap<String,Object>();
        
        try {
            retVal = uMapper.selectYearMonthLogin(yearMonth);
            retVal.put("yearMonth", yearMonth);
            retVal.put("is_success", true);
            
        }catch(Exception e) {
            retVal.put("totCnt", -999);
            retVal.put("yearMonth", yearMonth);
            retVal.put("is_success", false);
        }
        
        return retVal;
    }
    
    @Override
    public HashMap<String, Object> yearMonthDayloginNum (String yearMonthDay) {
        // TODO Auto-generated method stub
        HashMap<String, Object> retVal = new HashMap<String,Object>();
        
        try {
            retVal = uMapper.selectYearMonthDayLogin(yearMonthDay);
            retVal.put("yearMonthDay", yearMonthDay);
            retVal.put("is_success", true);
            
        }catch(Exception e) {
            retVal.put("totCnt", -999);
            retVal.put("yearMonthDay", yearMonthDay);
            retVal.put("is_success", false);
        }
        
        return retVal;
    }
    
    @Override
    public HashMap<String, Object> averageloginNum (String year, String month) {
        // TODO Auto-generated method stub
        HashMap<String, Object> retVal = new HashMap<String,Object>();
        MonthlyLoginDTO info = new MonthlyLoginDTO();
        
        
        int[] days = {31,28,31,30,31,30,31,31,30,31,30,31};
        int m = Integer.parseInt(month);
        int d = days[m-1];
        String yearMonth = year.substring(2,4) + month;
        
        if(m == 2) {
        	int y = Integer.parseInt(year);
        	
        	if( (y%4 == 0 && y%100 != 0) || y%400 == 0) {
        		d+=1;
        	}
        }
        
        info.setDays(d);
        info.setYearMonth(yearMonth);
        
        try {
            retVal = uMapper.selectMonthlyLogin(info);
            retVal.put("year", year);
            retVal.put("month", month);
            retVal.put("is_success", true);
            
        }catch(Exception e) {
            retVal.put("totCnt", -999);
            retVal.put("year", year);
            retVal.put("month", month);
            retVal.put("is_success", false);
        }
        
        return retVal;
    }
    
    @Override
	public Map<String, Object> weekloginNum(String yearMonth) {
		// TODO Auto-generated method stub
    	HashMap<String, Object> retVal = new HashMap<String,Object>();
        
        try {
            retVal = uMapper.selectExceptHolidaysLogin(yearMonth);
            retVal.put("yearMonth", yearMonth);
            retVal.put("is_success", true);
            
        }catch(Exception e) {
            retVal.put("totCnt", -999);
            retVal.put("yearMonth", yearMonth);
            retVal.put("is_success", false);
        }
        
        return retVal;
	}

	@Override
	public Map<String, Object> organizationloginNum(String organization, String yearMonth) {
		// TODO Auto-generated method stub
		HashMap<String, Object> retVal = new HashMap<String,Object>();
		organizationLoginDTO info = new organizationLoginDTO();
        
        info.setOrganization(organization);
        info.setYearMonth(yearMonth);
        
        try {
            retVal = uMapper.selectOrganizationLogin(info);
            retVal.put("organization", organization);
            retVal.put("yearMonth", yearMonth);
            retVal.put("is_success", true);
            
        }catch(Exception e) {
            retVal.put("totCnt", -999);
            retVal.put("organization", organization);
            retVal.put("yearMonth", yearMonth);
            retVal.put("is_success", false);
        }
        
        return retVal;
	}
 
}