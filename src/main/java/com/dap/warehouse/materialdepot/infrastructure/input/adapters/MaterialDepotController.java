package com.dap.warehouse.materialdepot.infrastructure.input.adapters;

import com.dap.warehouse.materialdepot.domain.api.DuplicatedRequest;
import com.dap.warehouse.materialdepot.domain.api.MaterialDepotDuplicated;
import com.dap.warehouse.materialdepot.domain.api.MaterialDepotRequest;
import com.dap.warehouse.materialdepot.domain.model.MaterialDepot;
import com.dap.warehouse.materialdepot.infrastructure.input.port.IMaterialDepotServiceInputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Validated
@RestController
@RequestMapping("/dap/warehouse/v1/materialDepotController")
public class MaterialDepotController {
	
	@Autowired
	private IMaterialDepotServiceInputPort iMaterialDepotServiceInputPort;
	
	@GetMapping("/findAll")
	public ResponseEntity<List<MaterialDepot>> findAll(){
		return this.iMaterialDepotServiceInputPort.findAll();
	}
	
	@GetMapping("/findById/{id}")
	public ResponseEntity<MaterialDepot> findById(@PathVariable("id") Integer id){
		return this.iMaterialDepotServiceInputPort.findById(id);
	}
	
	@PostMapping("/save")
	public ResponseEntity<MaterialDepot> save(@RequestBody MaterialDepotRequest materialDepotRequest){
		return this.iMaterialDepotServiceInputPort.save(materialDepotRequest);
	}
	
	@PutMapping("/update")
	public ResponseEntity<MaterialDepot> update(@RequestBody MaterialDepotRequest materialDepotRequest){
		return this.iMaterialDepotServiceInputPort.save(materialDepotRequest);
	}

	@GetMapping("/findByDepot/{depotId}")
	public ResponseEntity<List<MaterialDepot>> findByDepotId(@PathVariable("depotId") Integer depotId){
		return this.iMaterialDepotServiceInputPort.findByDepotId(depotId);
	}

	@PostMapping("/findDuplicate")
	public ResponseEntity<List<MaterialDepotDuplicated>> findDuplicated(@RequestBody DuplicatedRequest duplicatedRequest){
		return this.iMaterialDepotServiceInputPort.findDuplicated(duplicatedRequest);
	}

	@GetMapping("/findByMaterial/{materialId}")
	public ResponseEntity<List<MaterialDepot>> findByMaterialId(@PathVariable("materialId") Integer materialId){
		return this.iMaterialDepotServiceInputPort.findByMaterialId(materialId);
	}
}
