package com.dap.warehouse.status.infrastructure.input.adapters;

import com.dap.warehouse.status.domain.api.StatusRequest;
import com.dap.warehouse.status.domain.model.Status;
import com.dap.warehouse.status.infrastructure.input.port.IStatusServiceInputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Validated
@RestController
@RequestMapping("/dap/warehouse/v1/statusController")
public class StatusController {
	
	@Autowired
	private IStatusServiceInputPort iStatusServiceInputPort;
	
	@GetMapping("/findAll")
	public ResponseEntity<List<Status>> findAll(){
		return this.iStatusServiceInputPort.findAll();
	}
	
	@GetMapping("/findById/{id}")
	public ResponseEntity<Status> findById(@PathVariable("id") Integer id){
		return this.iStatusServiceInputPort.findById(id);
	}
	
	@DeleteMapping("/deleteById/{id}")
	public ResponseEntity<Status> deleteById(@PathVariable("id") Integer id){
		return this.iStatusServiceInputPort.deleteById(id);
	}
	
	@PostMapping("/save")
	public ResponseEntity<Status> save(@RequestBody StatusRequest statusRequest){
		return this.iStatusServiceInputPort.save(statusRequest);
	}
	
	@PutMapping("/update")
	public ResponseEntity<Status> update(@RequestBody StatusRequest statusRequest){
		return this.iStatusServiceInputPort.save(statusRequest);
	}

}
