package com.dap.warehouse.managementmaterial.domain.service;

import com.dap.warehouse.managementmaterial.domain.api.ManagementMaterialModel;
import com.dap.warehouse.managementmaterial.domain.model.ManagementMaterial;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ManagementMaterialMapper {
	
	public ManagementMaterial fromRequestToMapping(ManagementMaterialModel modelRequest) {
		
		var modelMapper = new ModelMapper();
		return modelMapper.map(modelRequest, ManagementMaterial.class);
		
	}

}
