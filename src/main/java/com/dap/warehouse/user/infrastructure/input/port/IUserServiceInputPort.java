package com.dap.warehouse.user.infrastructure.input.port;

import java.util.List;
import com.dap.warehouse.user.domain.api.UserRequest;
import com.dap.warehouse.user.domain.model.User;
import org.springframework.http.ResponseEntity;

public interface IUserServiceInputPort {
	
	ResponseEntity<List<User>> findAll();
	ResponseEntity<User> findById(Integer id);
	ResponseEntity<User> deleteById(Integer id);
	ResponseEntity<User> save(UserRequest accessRequest);

}
