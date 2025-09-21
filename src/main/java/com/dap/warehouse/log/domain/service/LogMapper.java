package com.dap.warehouse.log.domain.service;

import com.dap.warehouse.log.domain.api.LogModel;
import com.dap.warehouse.log.domain.model.Log;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class LogMapper {
	
	public Log fromRequestToMapping(LogModel modelRequest) {
		
		var modelMapper = new ModelMapper();
		return modelMapper.map(modelRequest, Log.class);
		
	}

}
