package com.dap.warehouse.managementmaterial.infrastructure.input.port;

import com.dap.warehouse.managementmaterial.domain.api.ManagementMaterialRequest;
import com.dap.warehouse.managementmaterial.domain.model.ManagementMaterial;
import org.springframework.http.ResponseEntity;
import java.util.List;

public interface IManagementMaterialServiceInputPort {
	
	ResponseEntity<List<ManagementMaterial>> findAll();
	ResponseEntity<ManagementMaterial> findById(Integer id);
	ResponseEntity<ManagementMaterial> deleteById(Integer id);
	ResponseEntity<ManagementMaterial> save(ManagementMaterialRequest accessRequest);

}
