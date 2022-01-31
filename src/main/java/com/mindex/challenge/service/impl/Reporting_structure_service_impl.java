package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.Reporting_structure_service;

@Service
public class Reporting_structure_service_impl implements Reporting_structure_service {
	private static final Logger LOG = LoggerFactory.getLogger(Reporting_structure_service_impl.class);

    @Autowired
    private EmployeeRepository employeeRepo;

    @Override
    public ReportingStructure read(String id) {
        LOG.debug("Received employee reporting structure read request for id [{}]", id);

        Employee employee = employeeRepo.findByEmployeeId(id);
        if (employee == null) {
            throw new RuntimeException("Invalid employeeId: " + id);
        }

        ReportingStructure reportingStructure = new ReportingStructure(employee, getTotalNumberOfReportees(employee));
        
		return reportingStructure;

 
    }

	public int getTotalNumberOfReportees(Employee employee) {
        LOG.debug("Fetching total reports for employee id [{}]", employee.getEmployeeId());

        {
            int Count = 0;
            if(employee.getDirectReports()==null)
            {
                return Count;
            }
            else
                {
                    int size = employee.getDirectReports().size(); // find the size of direct reports for given employee
                    Count += size; //  store the number of direct reports

                    for (Employee current: employee.getDirectReports()) // this loop will get each employee from direct Reports
                    {
                        Employee directEmp =  employeeRepo.findByEmployeeId(current.getEmployeeId()); // this will get the employee details of each employee present in direct reports
                        
                        Count += getTotalNumberOfReportees(directEmp);// recursive call to find the direct reports of all employees in the hierarchy
                    }

                }
            return Count;
        }
    }
	


}


