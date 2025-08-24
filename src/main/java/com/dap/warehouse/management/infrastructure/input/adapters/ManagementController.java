package com.dap.warehouse.management.infrastructure.input.adapters;

import com.dap.warehouse.management.domain.api.ManagementRequest;
import com.dap.warehouse.management.domain.model.Management;
import com.dap.warehouse.management.infrastructure.input.port.IManagementServiceInputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Validated
@RestController
@RequestMapping("/dap/warehouse/v1/materialManagementController")
public class ManagementController {
	
	@Autowired
	private IManagementServiceInputPort iManagementServiceInputPort;
	
	@GetMapping("/findAll")
	public ResponseEntity<List<Management>> findAll(){
		return this.iManagementServiceInputPort.findAll();
	}
	
	@GetMapping("/findById/{id}")
	public ResponseEntity<Management> findById(@PathVariable("id") Integer id){
		return this.iManagementServiceInputPort.findById(id);
	}
	
	@DeleteMapping("/deleteById/{id}")
	public ResponseEntity<Management> deleteById(@PathVariable("id") Integer id){
		return this.iManagementServiceInputPort.deleteById(id);
	}
	
	@PostMapping("/save")
	public ResponseEntity<Management> save(@RequestBody ManagementRequest managementRequest){
		return this.iManagementServiceInputPort.save(managementRequest);
	}
	
	@PutMapping("/update")
	public ResponseEntity<Management> update(@RequestBody ManagementRequest managementRequest){
		return this.iManagementServiceInputPort.save(managementRequest);
	}

}
