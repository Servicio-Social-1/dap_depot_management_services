package com.dap.warehouse.materialdepot.domain.service;

import com.dap.warehouse.materialdepot.domain.api.MaterialDepotModel;
import com.dap.warehouse.materialdepot.domain.model.MaterialDepot;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class MaterialDepotMapper {
	
	public MaterialDepot fromRequestToMapping(MaterialDepotModel modelRequest) {
		
		var modelMapper = new ModelMapper();
		return modelMapper.map(modelRequest, MaterialDepot.class);
		
	}

}
