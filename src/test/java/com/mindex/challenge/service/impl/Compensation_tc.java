package com.mindex.challenge.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import  org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.Compensation_service;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class Compensation_tc {

    private String employeeUrl;
    private String employeeIdUrl;
    private String compensationUrl;
    private String compensationIdUrl;
    
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private Compensation_service compensationService;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate template;

    @Before
    public void setup() {
        employeeUrl = "http://localhost:" + port + "/employee";
        employeeIdUrl = "http://localhost:" + port + "/employee/{id}";
        compensationUrl = "http://localhost:" + port + "/compensation";
        compensationIdUrl = "http://localhost:" + port + "/compensation/{id}";

    }

    @SuppressWarnings("deprecation")
	@Test
    public void Test()
    {
        Employee employee = employeeRepository.findByEmployeeId("62c1084e-6e34-4630-93fd-9153afb65309");
        // Create checks
        Compensation comp = new Compensation(employee, 1000000, "01/30/2022");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Compensation newComp=
                template.exchange(compensationUrl,
                        HttpMethod.PUT,
                        new HttpEntity<>(comp, headers),
                        Compensation.class,
                        comp.getEmployee().getEmployeeId()).getBody();

        assert newComp != null;
        assertNotNull(newComp.getEmployee().getEmployeeId());
        assertCompensationEquivalence(comp, newComp);

        // Read checks
        Compensation readComp =
                template.getForEntity(compensationIdUrl, Compensation.class,
                		newComp.getEmployee().getEmployeeId()).getBody();

        assert readComp != null;
        assertEquals(newComp.getEmployee().getEmployeeId(), readComp.getEmployee().getEmployeeId());
        assertCompensationEquivalence(newComp, readComp);
    }

	@SuppressWarnings("deprecation")
	private void assertCompensationEquivalence(Compensation expected, Compensation actual) {
		assertEquals(expected.getEmployee().getEmployeeId(), actual.getEmployee().getEmployeeId());
        assertEquals(expected.getSalary(), actual.getSalary());
        assertEquals(expected.getEffectiveDate(), actual.getEffectiveDate());
    }

}