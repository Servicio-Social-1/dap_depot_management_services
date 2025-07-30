package com.dap.warehouse.materialdepot.infrastructure.input.port;

import com.dap.warehouse.materialdepot.domain.api.MaterialDepotRequest;
import com.dap.warehouse.materialdepot.domain.model.MaterialDepot;
import org.springframework.http.ResponseEntity;
import java.util.List;

public interface IMaterialDepotServiceInputPort {
	
	ResponseEntity<List<MaterialDepot>> findAll();
	ResponseEntity<MaterialDepot> findById(Integer id);
	ResponseEntity<MaterialDepot> deleteById(Integer id);
	ResponseEntity<MaterialDepot> save(MaterialDepotRequest accessRequest);

}
