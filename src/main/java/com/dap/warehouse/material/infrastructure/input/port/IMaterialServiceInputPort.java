package com.dap.warehouse.material.infrastructure.input.port;

import com.dap.warehouse.material.domain.api.MaterialModel;
import com.dap.warehouse.material.domain.api.MaterialRequest;
import com.dap.warehouse.material.domain.model.Material;
import org.springframework.http.ResponseEntity;
import java.util.List;

public interface IMaterialServiceInputPort {
	
	ResponseEntity<List<MaterialModel>> findAll();
	ResponseEntity<Material> findById(Integer id);
	ResponseEntity<Material> save(MaterialRequest materialRequest);

}
