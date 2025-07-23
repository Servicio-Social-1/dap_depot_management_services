package com.dap.warehouse.area.domain.service;

import com.dap.warehouse.area.domain.api.AreaModel;
import com.dap.warehouse.area.domain.model.Area;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class AreaMapper {
	
	public Area fromRequestToMapping(AreaModel modelRequest) {
		
		var modelMapper = new ModelMapper();
		return modelMapper.map(modelRequest, Area.class);
		
	}

}
