package com.example.demo.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
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
	
	@Override
	public LinkedHashMap<String,Object> get_num_users_except_holiday(String startDate, String endDate) {
		// TODO Auto-generated method stub
    	LinkedHashMap<String, Object> retVal = new LinkedHashMap<String,Object>();
    	List<Date> list = new ArrayList<>();
        
        try {
        	list = uMapper.selectLoginNumInPeriod(startDate, endDate);
            
        	int cnt = 0;
        	List<String> holidays = get_holidays(startDate, endDate);
            for(Date str : list) {
            	SimpleDateFormat dtFormat = new SimpleDateFormat("yyyyMMdd");
            	
            	if(!holidays.contains(dtFormat.format(str))) cnt += 1;
            }
            
            retVal.put("totCnt", cnt);
            retVal.put("startDate", startDate);
            retVal.put("endDate", endDate);
            retVal.put("is_success", true);
            
        }catch(Exception e) {
        	System.out.println(e.getMessage());
            retVal.put("totCnt", -999);
            retVal.put("is_success", false);
        }
        
        return retVal;
	}

	@Override
	public List<String> get_holidays(String startDate, String endDate) throws IOException {
		// TODO Auto-generated method stub
		
		List<String> holidays = new ArrayList<>();
		for(int year = Integer.parseInt(startDate.substring(0,4)); year<= Integer.parseInt(endDate.substring(0,4)); year++){
			
			StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/B090041/openapi/service/SpcdeInfoService/getRestDeInfo"); /*URL*/
	        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=q6mx0UzQv%2FGBiGy4vIDna4fOCB7RPKvyBtiKDrtt5v0LKE5EKwzsdAH5BdM7mfmLd49dVyH7siHcjUhFa1e7Eg%3D%3D");/*Service Key*/
	        urlBuilder.append("&" + URLEncoder.encode("_type","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /*타입*/
	        urlBuilder.append("&" + URLEncoder.encode("solYear","UTF-8") + "=" + URLEncoder.encode(String.valueOf(year), "UTF-8")); /*연*/
	        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("365", "UTF-8")); /*최대로 출력할 공휴일 수*/

	        URL url = new URL(urlBuilder.toString());
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("GET");
	        conn.setRequestProperty("Content-type", "application/json");
	        System.out.println("Response code: " + conn.getResponseCode());
	        
	        BufferedReader rd;
	        StringBuilder sb = new StringBuilder();
	        try {
	        	if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
	                rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
	            } else {
	                rd = new BufferedReader(new InputStreamReader(conn.getErrorStream(), "UTF-8"));
	            }
	        	
	        	JSONParser jsonParser = new JSONParser();
	            JSONObject jsonObject = (JSONObject) jsonParser.parse(rd); // JSON 데이터로 JSON object 생성 
	            JSONObject responseResult = (JSONObject)jsonObject.get("response"); // json name으로  추출
	            JSONObject bodyInfo = (JSONObject)responseResult.get("body");
	            JSONObject itemsInfo = (JSONObject)bodyInfo.get("items");
	            JSONArray itemInfo = (JSONArray)itemsInfo.get("item"); //item이라는 이름을 가진 json 객체들을 배열로 생성 
	            
	            for(int i=0; i<itemInfo.size(); i++) {
	            	JSONObject day = (JSONObject)itemInfo.get(i); // 배열에 인덱스로 접근
	            	
	            	holidays.add(day.get("locdate").toString());
	            }

	            rd.close();            
	        }catch(Exception e) {
	    		e.printStackTrace();
	    	}
	        
	        conn.disconnect();
		}
		
        
        
		return holidays;
	}
	
	
 
}