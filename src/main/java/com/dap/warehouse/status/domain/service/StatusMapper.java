package com.dap.warehouse.status.domain.service;

import com.dap.warehouse.status.domain.api.StatusModel;
import com.dap.warehouse.status.domain.model.Status;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class StatusMapper {
	
	public Status fromRequestToMapping(StatusModel modelRequest) {
		
		var modelMapper = new ModelMapper();
		return modelMapper.map(modelRequest, Status.class);
		
	}

}
