package com.dap.warehouse.management.infrastructure.input.port;

import com.dap.warehouse.management.domain.api.MaterialManagementRequest;
import com.dap.warehouse.management.domain.model.MaterialManagement;
import org.springframework.http.ResponseEntity;
import java.util.List;

public interface IMaterialManagementServiceInputPort {
	
	ResponseEntity<List<MaterialManagement>> findAll();
	ResponseEntity<MaterialManagement> findById(Integer id);
	ResponseEntity<MaterialManagement> deleteById(Integer id);
	ResponseEntity<MaterialManagement> save(MaterialManagementRequest accessRequest);

}
