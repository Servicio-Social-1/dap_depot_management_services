package com.dap.warehouse.depot.infrastructure.input.adapters;

import com.dap.warehouse.depot.domain.api.DepotRequest;
import com.dap.warehouse.depot.domain.model.Depot;
import com.dap.warehouse.depot.infrastructure.input.port.IDepotServiceInputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/dap/warehouse/v1/depotController")
public class DepotController {
	
	@Autowired
	private IDepotServiceInputPort iDepotServiceFindAllInputPort;
	
	@GetMapping("/findAll")
	public ResponseEntity<List<Depot>> findAll(){
		return this.iDepotServiceFindAllInputPort.findAll();
	}
	
	@GetMapping("/findById/{id}")
	public ResponseEntity<Depot> findById(@PathVariable("id") Integer id){
		return this.iDepotServiceFindAllInputPort.findById(id);
	}
	
	@DeleteMapping("/deleteById/{id}")
	public ResponseEntity<Depot> deleteById(@PathVariable("id") Integer id){
		return this.iDepotServiceFindAllInputPort.deleteById(id);
	}
	
	@PostMapping("/save")
	public ResponseEntity<Depot> save(@RequestBody DepotRequest depotRequest){
		return this.iDepotServiceFindAllInputPort.save(depotRequest);
	}
	
	@PutMapping("/update")
	public ResponseEntity<Depot> update(@RequestBody DepotRequest depotRequest){
		return this.iDepotServiceFindAllInputPort.save(depotRequest);
	}

}
