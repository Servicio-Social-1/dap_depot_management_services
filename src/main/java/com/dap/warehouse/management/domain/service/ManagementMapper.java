package com.dap.warehouse.management.domain.service;

import com.dap.warehouse.management.domain.api.ManagementModel;
import com.dap.warehouse.management.domain.model.Management;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ManagementMapper {
	
	public Management fromRequestToMapping(ManagementModel modelRequest) {
		
		var modelMapper = new ModelMapper();
		return modelMapper.map(modelRequest, Management.class);
		
	}

}
