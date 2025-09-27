package com.dap.warehouse.area.infrastructure.input.adapters;

import com.dap.warehouse.area.domain.api.AreaRequest;
import com.dap.warehouse.area.domain.model.Area;
import com.dap.warehouse.area.infrastructure.input.port.IAreaServiceInputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/dap/warehouse/v1/areaController")
public class AreaController {
	
	@Autowired
	private IAreaServiceInputPort iAreaServiceInputPort;
	
	@GetMapping("/findAll")
	public ResponseEntity<List<Area>> findAll(){
		return this.iAreaServiceInputPort.findAll();
	}
	
	@GetMapping("/findById/{id}")
	public ResponseEntity<Area> findById(@PathVariable("id") Integer id){
		return this.iAreaServiceInputPort.findById(id);
	}
	
	@DeleteMapping("/deleteById/{id}")
	public ResponseEntity<Area> deleteById(@PathVariable("id") Integer id){
		return this.iAreaServiceInputPort.deleteById(id);
	}
	
	@PostMapping("/save")
	public ResponseEntity<Area> save(@RequestBody AreaRequest areaRequest){
		String nameMethod = new Throwable().getStackTrace()[0].getMethodName();
		return this.iAreaServiceInputPort.save(areaRequest, nameMethod);
	}
	
	@PutMapping("/update")
	public ResponseEntity<Area> update(@RequestBody AreaRequest areaRequest){
		String nameMethod = new Throwable().getStackTrace()[0].getMethodName();
		return this.iAreaServiceInputPort.save(areaRequest, nameMethod);
	}

}
