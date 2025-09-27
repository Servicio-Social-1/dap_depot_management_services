package com.dap.warehouse.area.infrastructure.input.port;

import com.dap.warehouse.area.domain.api.AreaRequest;
import com.dap.warehouse.area.domain.model.Area;
import org.springframework.http.ResponseEntity;
import java.util.List;

public interface IAreaServiceInputPort {
	
	ResponseEntity<List<Area>> findAll();
	ResponseEntity<Area> findById(Integer id);
	ResponseEntity<Area> deleteById(Integer id);
	ResponseEntity<Area> save(AreaRequest accessRequest, String nameMethod);

}
