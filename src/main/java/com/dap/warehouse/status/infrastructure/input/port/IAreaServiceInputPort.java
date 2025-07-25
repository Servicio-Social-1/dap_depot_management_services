package com.dap.warehouse.status.infrastructure.input.port;

import com.dap.warehouse.status.domain.api.AreaRequest;
import com.dap.warehouse.status.domain.model.Area;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IAreaServiceInputPort {
	
	ResponseEntity<List<Area>> findAll();
	ResponseEntity<Area> findById(Integer id);
	ResponseEntity<Area> deleteById(Integer id);
	ResponseEntity<Area> save(AreaRequest accessRequest);

}
