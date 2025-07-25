package com.dap.warehouse.material.infrastructure.input.adapters;

import com.dap.warehouse.material.domain.api.MaterialRequest;
import com.dap.warehouse.material.domain.model.Material;
import com.dap.warehouse.material.infrastructure.input.port.IMaterialServiceInputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Validated
@RestController
@RequestMapping("/dap/warehouse/v1/materialController")
public class MaterialController {
	
	@Autowired
	private IMaterialServiceInputPort iMaterialServiceFindAllInputPort;
	
	@GetMapping("/findAll")
	public ResponseEntity<List<Material>> findAll(){
		return this.iMaterialServiceFindAllInputPort.findAll();
	}
	
	@GetMapping("/findById/{id}")
	public ResponseEntity<Material> findById(@PathVariable("id") Integer id){
		return this.iMaterialServiceFindAllInputPort.findById(id);
	}
	
	@DeleteMapping("/deleteById/{id}")
	public ResponseEntity<Material> deleteById(@PathVariable("id") Integer id){
		return this.iMaterialServiceFindAllInputPort.deleteById(id);
	}
	
	@PostMapping("/save")
	public ResponseEntity<Material> save(@RequestBody MaterialRequest materialRequest){
		return this.iMaterialServiceFindAllInputPort.save(materialRequest);
	}
	
	@PutMapping("/update")
	public ResponseEntity<Material> update(@RequestBody MaterialRequest materialRequest){
		return this.iMaterialServiceFindAllInputPort.save(materialRequest);
	}

}
