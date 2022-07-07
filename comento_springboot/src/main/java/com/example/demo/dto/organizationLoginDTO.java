package com.example.demo.dto;

public class organizationLoginDTO {
	
	public String organization;
	public String yearMonth;
	
	
	@Override
	public String toString() {
		return "organizationLoginDTO [organization=" + organization + ", yearMonth=" + yearMonth + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}


	public String getOrganization() {
		return organization;
	}


	public void setOrganization(String organization) {
		this.organization = organization;
	}


	public String getYearMonth() {
		return yearMonth;
	}


	public void setYearMonth(String yearMonth) {
		this.yearMonth = yearMonth;
	}

}
