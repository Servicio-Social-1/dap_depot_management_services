package com.dap.warehouse.log.infrastructure.input.adapters;

import com.dap.warehouse.log.domain.api.LogRequest;
import com.dap.warehouse.log.domain.model.Log;
import com.dap.warehouse.log.infrastructure.input.port.ILogServiceInputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/dap/warehouse/v1/logController")
public class LogController {
	
	@Autowired
	private ILogServiceInputPort iLogServiceInputPort;
	
	@GetMapping("/findAll")
	public ResponseEntity<List<Log>> findAll(){
		return this.iLogServiceInputPort.findAll();
	}
	
	@GetMapping("/findById/{id}")
	public ResponseEntity<Log> findById(@PathVariable("id") Integer id){
		return this.iLogServiceInputPort.findById(id);
	}
	
	@DeleteMapping("/deleteById/{id}")
	public ResponseEntity<Log> deleteById(@PathVariable("id") Integer id){
		return this.iLogServiceInputPort.deleteById(id);
	}
	
	@PostMapping("/save")
	public ResponseEntity<Log> save(@RequestBody LogRequest logRequest){
		return this.iLogServiceInputPort.save(logRequest);
	}
	
	@PutMapping("/update")
	public ResponseEntity<Log> update(@RequestBody LogRequest logRequest){
		return this.iLogServiceInputPort.save(logRequest);
	}

}
