package com.dap.warehouse.unit.infrastructure.input.adapters;

import com.dap.warehouse.unit.domain.api.UnitRequest;
import com.dap.warehouse.unit.domain.model.Unit;
import com.dap.warehouse.unit.infrastructure.input.port.IUnitServiceInputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/dap/warehouse/v1/unitController")
public class UnitController {
	
	@Autowired
	private IUnitServiceInputPort iUnitServiceFindAllInputPort;
	
	@GetMapping("/findAll")
	public ResponseEntity<List<Unit>> findAll(){
		return this.iUnitServiceFindAllInputPort.findAll();
	}
	
	@GetMapping("/findById/{id}")
	public ResponseEntity<Unit> findById(@PathVariable("id") Integer id){
		return this.iUnitServiceFindAllInputPort.findById(id);
	}
	
	@DeleteMapping("/deleteById/{id}")
	public ResponseEntity<Unit> deleteById(@PathVariable("id") Integer id){
		return this.iUnitServiceFindAllInputPort.deleteById(id);
	}
	
	@PostMapping("/save")
	public ResponseEntity<Unit> save(@RequestBody UnitRequest unitRequest){
		return this.iUnitServiceFindAllInputPort.save(unitRequest);
	}
	
	@PutMapping("/update")
	public ResponseEntity<Unit> update(@RequestBody UnitRequest unitRequest){
		return this.iUnitServiceFindAllInputPort.save(unitRequest);
	}

}
