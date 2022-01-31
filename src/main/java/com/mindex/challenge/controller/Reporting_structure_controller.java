package com.mindex.challenge.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.Reporting_structure_service;


@RestController
public class Reporting_structure_controller {
	
	private static final Logger LOG = LoggerFactory.getLogger(Reporting_structure_controller.class);

    @Autowired
    private Reporting_structure_service reportingStructureService;
    
    @GetMapping("/reporting structure/{id}")
    
    public ReportingStructure read(@PathVariable String id) {
        LOG.debug("Received reporting structure request for employee id [{}]", id);
        return reportingStructureService.read(id);
    }
}
