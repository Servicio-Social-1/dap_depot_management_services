package com.dap.warehouse.profile.infrastructure.input.adapters;

import com.dap.warehouse.profile.domain.api.ProfileRequest;
import com.dap.warehouse.profile.domain.model.Profile;
import com.dap.warehouse.profile.infrastructure.input.port.IProfileServiceInputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Validated
@RestController
@RequestMapping("/dap/warehouse/v1/ProfileController")
public class ProfileController {
	
	@Autowired
	private IProfileServiceInputPort iProfileServiceInputPort;
	
	@GetMapping("/findAll")
	public ResponseEntity<List<Profile>> findAll(){
		return this.iProfileServiceInputPort.findAll();
	}
	
	@GetMapping("/findById/{id}")
	public ResponseEntity<Profile> findById(@PathVariable("id") Integer id){
		return this.iProfileServiceInputPort.findById(id);
	}
	
	@DeleteMapping("/deleteById/{id}")
	public ResponseEntity<Profile> deleteById(@PathVariable("id") Integer id){
		return this.iProfileServiceInputPort.deleteById(id);
	}
	
	@PostMapping("/save")
	public ResponseEntity<Profile> save(@RequestBody ProfileRequest profileRequest){
		return this.iProfileServiceInputPort.save(profileRequest);
	}
	
	@PutMapping("/update")
	public ResponseEntity<Profile> update(@RequestBody ProfileRequest profileRequest){
		return this.iProfileServiceInputPort.save(profileRequest);
	}

}
