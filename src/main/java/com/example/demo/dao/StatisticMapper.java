package com.example.demo.dao;

import java.util.HashMap;
 
//import com.example.demo.dto.StatisticDto;
 
public interface  StatisticMapper {
    public HashMap<String, Object> selectYearLogin(String year);
 
}