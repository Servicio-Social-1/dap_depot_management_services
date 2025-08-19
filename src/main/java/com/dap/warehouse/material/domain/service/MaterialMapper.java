package com.dap.warehouse.material.domain.service;

import com.dap.warehouse.material.domain.api.MaterialModel;
import com.dap.warehouse.material.domain.model.Material;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class MaterialMapper {
	
	public Material fromRequestToMapping(MaterialModel modelRequest) {
		var modelMapper = new ModelMapper();
		return modelMapper.map(modelRequest, Material.class);
	}

	public MaterialModel fromMappingToModel(Material model) {
		var modelMapper = new ModelMapper();
		return modelMapper.map(model, MaterialModel.class);
	}

}
