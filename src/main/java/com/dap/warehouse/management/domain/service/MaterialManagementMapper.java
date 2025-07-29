package com.dap.warehouse.management.domain.service;

import com.dap.warehouse.management.domain.api.MaterialManagementModel;
import com.dap.warehouse.management.domain.model.MaterialManagement;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class MaterialManagementMapper {
	
	public MaterialManagement fromRequestToMapping(MaterialManagementModel modelRequest) {
		
		var modelMapper = new ModelMapper();
		return modelMapper.map(modelRequest, MaterialManagement.class);
		
	}

}
