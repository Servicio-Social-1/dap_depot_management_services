package com.dap.warehouse.material.infrastructure.input.adapters;

import com.dap.warehouse.material.domain.api.MaterialModel;
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
	private IMaterialServiceInputPort iMaterialServiceInputPort;
	
	@GetMapping("/findAll")
	public ResponseEntity<List<MaterialModel>> findAll(){
		return this.iMaterialServiceInputPort.findAll();
	}
	
	@GetMapping("/findById/{id}")
	public ResponseEntity<Material> findById(@PathVariable("id") Integer id){
		return this.iMaterialServiceInputPort.findById(id);
	}
	
	@PostMapping("/save")
	public ResponseEntity<Material> save(@RequestBody MaterialRequest materialRequest){
		return this.iMaterialServiceInputPort.save(materialRequest);
	}
	
	@PutMapping("/update")
	public ResponseEntity<Material> update(@RequestBody MaterialRequest materialRequest){
		return this.iMaterialServiceInputPort.save(materialRequest);
	}

}
