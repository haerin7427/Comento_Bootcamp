package com.example.demo.dto;

public class MonthlyLoginDTO {
	
	public int days;
	public String yearMonth;
	
	@Override
	public String toString() {
		return "MonthlyLoginDTO [days=" + days + ", yearMonth=" + yearMonth + "]";
	}
	
	public int getDays() {
		return days;
	}
	public void setDays(int days) {
		this.days = days;
	}
	public String getYearMonth() {
		return yearMonth;
	}
	public void setYearMonth(String yearMonth) {
		this.yearMonth = yearMonth;
	}
}
