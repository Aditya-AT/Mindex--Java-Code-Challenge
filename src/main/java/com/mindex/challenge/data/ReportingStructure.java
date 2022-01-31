package com.mindex.challenge.data;

public class ReportingStructure {
	
	
	Employee employee;
    int numberOfReports;
    
	public ReportingStructure(Employee employee, int numberOfReports) {
		super();
		this.employee = employee;
		this.numberOfReports = numberOfReports;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public int getNumberOfReports() {
		return numberOfReports;
	}
	public void setNumberOfReports(int numberOfReports) {
		this.numberOfReports = numberOfReports;
	}
	
	@Override
	public String toString() {
		return "ReportingStructure [employee=" + employee.getFirstName()+employee.getLastName() + ", numberOfReports=" + numberOfReports + "]";
	}

}
