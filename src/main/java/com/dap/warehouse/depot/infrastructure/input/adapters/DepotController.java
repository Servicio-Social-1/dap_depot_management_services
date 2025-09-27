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
	private IDepotServiceInputPort iDepotServiceInputPort;
	
	@GetMapping("/findAll")
	public ResponseEntity<List<Depot>> findAll(){
		return this.iDepotServiceInputPort.findAll();
	}
	
	@GetMapping("/findById/{id}")
	public ResponseEntity<Depot> findById(@PathVariable("id") Integer id){
		return this.iDepotServiceInputPort.findById(id);
	}
	
	@DeleteMapping("/deleteById/{id}")
	public ResponseEntity<Depot> deleteById(@PathVariable("id") Integer id){
		return this.iDepotServiceInputPort.deleteById(id);
	}
	
	@PostMapping("/save")
	public ResponseEntity<Depot> save(@RequestBody DepotRequest depotRequest){
		String nameMethod = new Throwable().getStackTrace()[0].getMethodName();
		return this.iDepotServiceInputPort.save(depotRequest, nameMethod);
	}
	
	@PutMapping("/update")
	public ResponseEntity<Depot> update(@RequestBody DepotRequest depotRequest){
		String nameMethod = new Throwable().getStackTrace()[0].getMethodName();
		return this.iDepotServiceInputPort.save(depotRequest, nameMethod);
	}

}
