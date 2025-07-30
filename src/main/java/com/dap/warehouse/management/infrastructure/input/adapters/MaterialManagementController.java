package com.dap.warehouse.management.infrastructure.input.adapters;

import com.dap.warehouse.management.domain.api.MaterialManagementRequest;
import com.dap.warehouse.management.domain.model.MaterialManagement;
import com.dap.warehouse.management.infrastructure.input.port.IMaterialManagementServiceInputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Validated
@RestController
@RequestMapping("/dap/warehouse/v1/materialManagementController")
public class MaterialManagementController {
	
	@Autowired
	private IMaterialManagementServiceInputPort iMaterialManagementServiceInputPort;
	
	@GetMapping("/findAll")
	public ResponseEntity<List<MaterialManagement>> findAll(){
		return this.iMaterialManagementServiceInputPort.findAll();
	}
	
	@GetMapping("/findById/{id}")
	public ResponseEntity<MaterialManagement> findById(@PathVariable("id") Integer id){
		return this.iMaterialManagementServiceInputPort.findById(id);
	}
	
	@DeleteMapping("/deleteById/{id}")
	public ResponseEntity<MaterialManagement> deleteById(@PathVariable("id") Integer id){
		return this.iMaterialManagementServiceInputPort.deleteById(id);
	}
	
	@PostMapping("/save")
	public ResponseEntity<MaterialManagement> save(@RequestBody MaterialManagementRequest materialManagementRequest){
		return this.iMaterialManagementServiceInputPort.save(materialManagementRequest);
	}
	
	@PutMapping("/update")
	public ResponseEntity<MaterialManagement> update(@RequestBody MaterialManagementRequest materialManagementRequest){
		return this.iMaterialManagementServiceInputPort.save(materialManagementRequest);
	}

}
