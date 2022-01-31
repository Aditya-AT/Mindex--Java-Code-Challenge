package com.mindex.challenge.service.impl;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class Reporting_structure_tc {
	
	private String employeeUrl;
	private String reportingStructureUrl;
	 
	@Autowired
    private EmployeeRepository employeeRepository;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate template;

    private Employee testEmp1;
    private Employee testEmp2;
    
    @Before
    public void setup() {
        employeeUrl = "http://localhost:" + port + "/employee";
        reportingStructureUrl = "http://localhost:" + port + "/reporting structure/{id}";
        testEmp1 = employeeRepository.findByEmployeeId("62c1084e-6e34-4630-93fd-9153afb65309");
        testEmp2 = employeeRepository.findByEmployeeId("16a596ae-edd3-4847-99fe-c4518e82c86f");
    }
    @Test
    public void testReportingStructureRead() {
    	
        // zero reports
        ReportingStructure test1 = new ReportingStructure(testEmp1,0);

        // Read checks
        ReportingStructure readReportStructure1 =  template.getForEntity(reportingStructureUrl, ReportingStructure.class,
                        testEmp2.getEmployeeId()).getBody();

        assert readReportStructure1 != null;
        assertEquals(test1.getEmployee().getEmployeeId(), readReportStructure1.getEmployee().getEmployeeId());
        assertReportingStructureEquivalence(test1, readReportStructure1);
        
        // four reports
        ReportingStructure test2 = new ReportingStructure(testEmp2, 4);

        // Read checks
        ReportingStructure readReportStructure2 =
                template.getForEntity(reportingStructureUrl, ReportingStructure.class,
                        testEmp2.getEmployeeId()).getBody();

        assert readReportStructure2 != null;
        assertEquals(test2.getEmployee().getEmployeeId(), readReportStructure2.getEmployee().getEmployeeId());
        assertReportingStructureEquivalence(test2, readReportStructure2);
}
	public void assertReportingStructureEquivalence(ReportingStructure test, ReportingStructure read) {
    	assertEquals(test.getEmployee().getEmployeeId(), read.getEmployee().getEmployeeId());
        assertEquals(test.getNumberOfReports(), read.getNumberOfReports());
	}
}
