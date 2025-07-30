package com.dap.warehouse.managementmaterial.infrastructure.input.adapters;

import com.dap.warehouse.managementmaterial.domain.api.ManagementMaterialRequest;
import com.dap.warehouse.managementmaterial.domain.model.ManagementMaterial;
import com.dap.warehouse.managementmaterial.infrastructure.input.port.IManagementMaterialServiceInputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Validated
@RestController
@RequestMapping("/dap/warehouse/v1/managementMaterialController")
public class ManagementMaterialController {
	
	@Autowired
	private IManagementMaterialServiceInputPort iManagementMaterialServiceInputPort;
	
	@GetMapping("/findAll")
	public ResponseEntity<List<ManagementMaterial>> findAll(){
		return this.iManagementMaterialServiceInputPort.findAll();
	}
	
	@GetMapping("/findById/{id}")
	public ResponseEntity<ManagementMaterial> findById(@PathVariable("id") Integer id){
		return this.iManagementMaterialServiceInputPort.findById(id);
	}
	
	@DeleteMapping("/deleteById/{id}")
	public ResponseEntity<ManagementMaterial> deleteById(@PathVariable("id") Integer id){
		return this.iManagementMaterialServiceInputPort.deleteById(id);
	}
	
	@PostMapping("/save")
	public ResponseEntity<ManagementMaterial> save(@RequestBody ManagementMaterialRequest managementMaterialRequest){
		return this.iManagementMaterialServiceInputPort.save(managementMaterialRequest);
	}
	
	@PutMapping("/update")
	public ResponseEntity<ManagementMaterial> update(@RequestBody ManagementMaterialRequest managementMaterialRequest){
		return this.iManagementMaterialServiceInputPort.save(managementMaterialRequest);
	}

}
