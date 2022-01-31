package com.mindex.challenge.service;

import com.mindex.challenge.data.Compensation;

public interface Compensation_service {
	
	Compensation read(String id);		//read compensation by emp id
	
	Compensation create(Compensation compensation);		//create new compensation
}
