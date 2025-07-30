package com.dap.warehouse.user.infrastructure.input.adapters;

import java.util.List;
import com.dap.warehouse.user.domain.api.UserRequest;
import com.dap.warehouse.user.domain.model.User;
import com.dap.warehouse.user.infrastructure.input.port.IUserServiceInputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/dap/warehouse/v1/userController")
public class UserController {
	
	@Autowired
	private IUserServiceInputPort iUserServiceInputPort;
	
	@GetMapping("/findAll")
	public ResponseEntity<List<User>> findAll(){
		return this.iUserServiceInputPort.findAll();
	}
	
	@GetMapping("/findById/{id}")
	public ResponseEntity<User> findById(@PathVariable("id") Integer id){
		return this.iUserServiceInputPort.findById(id);
	}
	
	@DeleteMapping("/deleteById/{id}")
	public ResponseEntity<User> deleteById(@PathVariable("id") Integer id){
		return this.iUserServiceInputPort.deleteById(id);
	}
	
	@PostMapping("/save")
	public ResponseEntity<User> save(@RequestBody UserRequest userRequest){
		return this.iUserServiceInputPort.save(userRequest);
	}
	
	@PutMapping("/update")
	public ResponseEntity<User> update(@RequestBody UserRequest userRequest){
		return this.iUserServiceInputPort.save(userRequest);
	}

}
