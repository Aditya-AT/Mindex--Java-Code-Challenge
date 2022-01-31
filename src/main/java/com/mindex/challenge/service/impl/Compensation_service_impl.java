package com.mindex.challenge.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.service.Compensation_service;

@Service
public class Compensation_service_impl implements Compensation_service{
	private static final Logger LOG = LoggerFactory.getLogger(Compensation_service_impl.class);
	
	@Autowired
    private CompensationRepository compensationRepo;
	
	@Override
	public Compensation read(String id) {
		
		
		LOG.debug("Received request for reading Compensation with employee id [{}]", id);

        Compensation comp = compensationRepo.getCompensationByEmployeeEmployeeId(id); // fetching the compensation data by specific employee id
        if (comp == null)
        {
            throw new RuntimeException("Invalid employeeId: " + id);
        }
        return comp;
	}

	@Override
	public Compensation create(Compensation compensation) {
		LOG.debug("Received request for creating compensation [{}]", compensation);
		
		if (compensationRepo.getCompensationByEmployeeEmployeeId(compensation.getEmployee().getEmployeeId()) == null)		 // checking if compensation already exists
			compensationRepo.insert(compensation);		// add the new compensation data into the mongoDB via DAO layer
                       	
		return compensation;
	}

}
