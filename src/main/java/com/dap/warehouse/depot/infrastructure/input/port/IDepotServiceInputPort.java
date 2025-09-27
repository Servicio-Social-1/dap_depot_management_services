package com.dap.warehouse.depot.infrastructure.input.port;

import com.dap.warehouse.depot.domain.api.DepotRequest;
import com.dap.warehouse.depot.domain.model.Depot;
import org.springframework.http.ResponseEntity;
import java.util.List;

public interface IDepotServiceInputPort {
	
	ResponseEntity<List<Depot>> findAll();
	ResponseEntity<Depot> findById(Integer id);
	ResponseEntity<Depot> deleteById(Integer id);
	ResponseEntity<Depot> save(DepotRequest accessRequest, String nameMethod);

}
