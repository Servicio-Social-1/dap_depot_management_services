package com.dap.warehouse.user.domain.service;

import com.dap.warehouse.user.domain.api.UserModel;
import com.dap.warehouse.user.domain.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
	
	public User fromRequestToMapping(UserModel modelRequest) {
		
		var modelMapper = new ModelMapper();
		return modelMapper.map(modelRequest, User.class);
		
	}

}
