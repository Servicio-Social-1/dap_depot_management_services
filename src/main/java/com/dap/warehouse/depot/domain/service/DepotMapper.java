package com.dap.warehouse.depot.domain.service;

import com.dap.warehouse.depot.domain.api.DepotModel;
import com.dap.warehouse.depot.domain.model.Depot;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class DepotMapper {
	
	public Depot fromRequestToMapping(DepotModel modelRequest) {
		
		var modelMapper = new ModelMapper();
		return modelMapper.map(modelRequest, Depot.class);
		
	}

}
