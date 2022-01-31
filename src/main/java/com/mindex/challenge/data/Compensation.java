package com.mindex.challenge.data;

import java.sql.Date;

public class Compensation {
	Employee employee;
	double salary;
	String effectiveDate;
	
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public String getEffectiveDate() {
		return effectiveDate;
	}
	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	public Compensation(Employee employee, double salary, String effectiveDate) {
		super();
		this.employee = employee;
		this.salary = salary;
		this.effectiveDate = effectiveDate;
	}
	
	@Override
	public String toString() {
		return "Compensation [employee=" + employee + ", salary=" + salary + ", effectiveDate=" + effectiveDate + "]";
	}
	
	
	
}
