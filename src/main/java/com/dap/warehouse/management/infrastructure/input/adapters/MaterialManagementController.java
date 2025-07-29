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
	private IMaterialManagementServiceInputPort iMaterialManagementServiceFindAllInputPort;
	
	@GetMapping("/findAll")
	public ResponseEntity<List<MaterialManagement>> findAll(){
		return this.iMaterialManagementServiceFindAllInputPort.findAll();
	}
	
	@GetMapping("/findById/{id}")
	public ResponseEntity<MaterialManagement> findById(@PathVariable("id") Integer id){
		return this.iMaterialManagementServiceFindAllInputPort.findById(id);
	}
	
	@DeleteMapping("/deleteById/{id}")
	public ResponseEntity<MaterialManagement> deleteById(@PathVariable("id") Integer id){
		return this.iMaterialManagementServiceFindAllInputPort.deleteById(id);
	}
	
	@PostMapping("/save")
	public ResponseEntity<MaterialManagement> save(@RequestBody MaterialManagementRequest materialManagementRequest){
		return this.iMaterialManagementServiceFindAllInputPort.save(materialManagementRequest);
	}
	
	@PutMapping("/update")
	public ResponseEntity<MaterialManagement> update(@RequestBody MaterialManagementRequest materialManagementRequest){
		return this.iMaterialManagementServiceFindAllInputPort.save(materialManagementRequest);
	}

}
