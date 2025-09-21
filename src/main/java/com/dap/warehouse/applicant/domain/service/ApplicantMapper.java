package com.dap.warehouse.applicant.domain.service;

import com.dap.warehouse.applicant.domain.api.ApplicantModel;
import com.dap.warehouse.applicant.domain.model.Applicant;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ApplicantMapper {
	
	public Applicant fromRequestToMapping(ApplicantModel modelRequest) {
		
		var modelMapper = new ModelMapper();
		return modelMapper.map(modelRequest, Applicant.class);
		
	}

}
