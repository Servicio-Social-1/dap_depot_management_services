package com.dap.warehouse.profile.domain.service;

import com.dap.warehouse.profile.domain.api.ProfileModel;
import com.dap.warehouse.profile.domain.model.Profile;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ProfileMapper {
	
	public Profile fromRequestToMapping(ProfileModel modelRequest) {
		
		var modelMapper = new ModelMapper();
		return modelMapper.map(modelRequest, Profile.class);
		
	}

}
