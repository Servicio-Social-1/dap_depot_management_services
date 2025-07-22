package com.dap.warehouse.user.application;

import java.util.List;
import java.util.Optional;
import com.dap.warehouse.user.domain.api.UserRequest;
import com.dap.warehouse.user.domain.model.User;
import com.dap.warehouse.user.domain.service.UserMapper;
import com.dap.warehouse.user.infrastructure.input.port.IUserServiceInputPort;
import com.dap.warehouse.user.infrastructure.output.port.IUserRepositoryOutputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

@Service
public class UserServiceImplement implements IUserServiceInputPort {
	
	@Autowired
	private IUserRepositoryOutputPort iUserRepositoryOutputPort;
	
	@Autowired
	private UserMapper userMapper;
	
    public ResponseEntity<List<User>> findAll(){
    	
    	var sortById = Sort.by("idUser");
    	ResponseEntity<List<User>> response = null;
    	try {
			List<User> userList = this.iUserRepositoryOutputPort.findAll(sortById);
			if (!userList.isEmpty()) {
				response = new ResponseEntity<>(userList, HttpStatus.OK);
						} else {
				response = new ResponseEntity<>(userList, HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			return response;
		}
    	return response;
    }
    

	@Override
	public ResponseEntity<User> findById(Integer id) {
		
		ResponseEntity<User> response = null;
		try {
			Optional<User> optionaluserProfile = iUserRepositoryOutputPort.findById(id);
            response = optionaluserProfile.map(userProfile -> new ResponseEntity<>(userProfile, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
		} catch (Exception e) {
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			return response;
		}
		return response;
	}
	

	@Override
	@Transactional
	public ResponseEntity<User> deleteById(Integer id) {
		
		ResponseEntity<User> response = null;
		try {
			Optional<User> optionaluserProfile = iUserRepositoryOutputPort.findById(id);
			if(optionaluserProfile.isPresent()) {
				iUserRepositoryOutputPort.delete(optionaluserProfile.get());
				response = new ResponseEntity<>(optionaluserProfile.get(),HttpStatus.MOVED_PERMANENTLY);
			}else {
				response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			return response;
		}
		return response;
	}
	

	@Override
	@Transactional
	public ResponseEntity<User> save(UserRequest userRequest) {

		ResponseEntity<User> response;
		try {
			var userProfileResponse = userMapper.fromRequestToMapping(userRequest.getModelRequest());
			var userProfile = iUserRepositoryOutputPort.save(userProfileResponse);
			response = new ResponseEntity<>(userProfile, HttpStatus.CREATED);
			
		} catch (Exception e) {
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			return response;	
		}
		return response;
	}

}
