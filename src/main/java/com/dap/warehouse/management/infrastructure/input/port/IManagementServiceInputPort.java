package com.dap.warehouse.management.infrastructure.input.port;

import com.dap.warehouse.management.domain.api.ManagementRequest;
import com.dap.warehouse.management.domain.model.Management;
import org.springframework.http.ResponseEntity;
import java.util.List;

public interface IManagementServiceInputPort {
	
	ResponseEntity<List<Management>> findAll();
	ResponseEntity<Management> findById(Integer id);
	ResponseEntity<Management> deleteById(Integer id);
	ResponseEntity<Management> save(ManagementRequest accessRequest);

}
