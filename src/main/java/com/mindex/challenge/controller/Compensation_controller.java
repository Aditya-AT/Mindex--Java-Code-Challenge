package com.mindex.challenge.controller;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.service.Compensation_service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class Compensation_controller
{
    private static final Logger LOG = LoggerFactory.getLogger(Compensation_controller.class);

    @Autowired
    private Compensation_service compService;

    @PostMapping("/compensation") // creating a compensation data
    public Compensation create(@RequestBody Compensation compensation)
    {
        LOG.debug("Received request for creating compensation  [{}]", compensation);
        return compService.create(compensation);
    }

    @GetMapping("/compensation/{id}") // fetching compensation data of employee with specific empId
    public Compensation read(@PathVariable String id)
    {
        LOG.debug("Received request for reading compensation for id [{}]", id);

        return compService.read(id);
    }


}
