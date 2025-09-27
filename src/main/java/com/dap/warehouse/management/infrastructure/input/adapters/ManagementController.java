package com.dap.warehouse.management.infrastructure.input.adapters;

import com.dap.warehouse.management.domain.api.ManagementModel;
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
	public ResponseEntity<ManagementModel> save(@RequestBody ManagementRequest managementRequest){
		String nameMethod = new Throwable().getStackTrace()[0].getMethodName();
		return this.iManagementServiceInputPort.save(managementRequest,nameMethod);
	}


}
