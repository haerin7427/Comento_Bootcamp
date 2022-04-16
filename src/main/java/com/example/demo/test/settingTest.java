package com.example.demo.test;
 
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
