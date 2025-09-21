package com.dap.warehouse.access.infrastructure.input.adapters;

import com.dap.warehouse.access.domain.api.AccessRequest;
import com.dap.warehouse.access.domain.model.Access;
import com.dap.warehouse.access.infrastructure.input.port.IAccessServiceInputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/dap/warehouse/v1/accessController")
public class AccessController {
	
	@Autowired
	private IAccessServiceInputPort iAccessServiceInputPort;
	
	@PostMapping("/auth")
	public ResponseEntity<Access> validateAccess(@RequestBody AccessRequest accessRequest){
		return this.iAccessServiceInputPort.validateAccess(accessRequest);
	}



}
