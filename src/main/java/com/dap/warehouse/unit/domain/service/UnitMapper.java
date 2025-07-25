package com.dap.warehouse.unit.domain.service;

import com.dap.warehouse.unit.domain.api.UnitModel;
import com.dap.warehouse.unit.domain.model.Unit;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UnitMapper {
	
	public Unit fromRequestToMapping(UnitModel modelRequest) {
		
		var modelMapper = new ModelMapper();
		return modelMapper.map(modelRequest, Unit.class);
		
	}

}
