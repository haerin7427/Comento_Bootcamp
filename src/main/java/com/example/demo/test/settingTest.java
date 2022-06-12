package com.example.demo.test;
 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.service.StatisticService;
 
@Controller
public class settingTest {
	
	@Autowired
    private StatisticService service;
    
    @ResponseBody 
    @RequestMapping("/sqlyearStatistic")
    public Map<String, Object> sqltest(String year) throws Exception{ 
        return service.yearloginNum(year);
    }
    
    @ResponseBody 
    @RequestMapping("/monthLogin")
    public Map<String, Object> monthLogin(String yearMonth) throws Exception{ 
        return service.yearMonthloginNum(yearMonth);
    }
    
    @ResponseBody 
    @RequestMapping("/dayLogin")
    public Map<String, Object> dayLogin(String yearMonthDay) throws Exception{
        return service.yearMonthDayloginNum(yearMonthDay);
    }
    
    @ResponseBody 
    @RequestMapping("/averageLogin")
    public Map<String, Object> averageLogin(String year, String month) throws Exception{
        return service.averageloginNum(year, month);
    }
    
    @ResponseBody 
    @RequestMapping("/weekLogin")
    public Map<String, Object> weekLogin(String yearMonth) throws Exception{
        return service.weekloginNum(yearMonth);
    }
    
    @ResponseBody 
    @RequestMapping("/orgaLogin")
    public Map<String, Object> organizationLogin(String organization, String yearMonth) throws Exception{
        return service.organizationloginNum(organization, yearMonth);
    }
    
    @ResponseBody 
    @RequestMapping("/openAPItest_v1")
    public String openAPItest_v1(String organization, String yearMonth) throws IOException{
    	StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/B090041/openapi/service/SpcdeInfoService/getRestDeInfo"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=q6mx0UzQv%2FGBiGy4vIDna4fOCB7RPKvyBtiKDrtt5v0LKE5EKwzsdAH5BdM7mfmLd49dVyH7siHcjUhFa1e7Eg%3D%3D"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("_type","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /*타입*/
        urlBuilder.append("&" + URLEncoder.encode("solYear","UTF-8") + "=" + URLEncoder.encode("2019", "UTF-8")); /*연*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("365", "UTF-8")); /*최대로 출력할 공휴일 수*/

        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        
        BufferedReader rd;       
    	if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream(), "UTF-8"));
        }
    	
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        System.out.println(sb.toString());

        return sb.toString();
    }
    
    @ResponseBody 
    @RequestMapping("/openAPItest_v2")
    public String openAPItest_v2(String organization, String yearMonth) throws IOException{
    	StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/B090041/openapi/service/SpcdeInfoService/getRestDeInfo"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=q6mx0UzQv%2FGBiGy4vIDna4fOCB7RPKvyBtiKDrtt5v0LKE5EKwzsdAH5BdM7mfmLd49dVyH7siHcjUhFa1e7Eg%3D%3D");/*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("_type","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /*타입*/
        urlBuilder.append("&" + URLEncoder.encode("solYear","UTF-8") + "=" + URLEncoder.encode("2022", "UTF-8")); /*연*/
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
            	sb.append(day.get("locdate").toString()).append("\n"); // 객체에서 locdate 이름의 값(=휴일)을 추출 
            }

            System.out.println(itemInfo.size());
            rd.close();            
        }catch(Exception e) {
    		e.printStackTrace();
    	}
        
        conn.disconnect();
        return sb.toString();
    }
    
    @RequestMapping("/test") 
    public ModelAndView test() throws Exception{ 
        ModelAndView mav = new ModelAndView("test"); 
        mav.addObject("name", "devfunpj"); 
        List<String> resultList = new ArrayList<String>(); 
        resultList.add("!!!HELLO WORLD!!!"); 
        resultList.add("설정 TEST!!!"); 
        resultList.add("설정 TEST!!!"); 
        resultList.add("설정 TEST!!!!!"); 
        resultList.add("설정 TEST!!!!!!"); 
        mav.addObject("list", resultList); 
        return mav; 
    }
    
    
 
}
