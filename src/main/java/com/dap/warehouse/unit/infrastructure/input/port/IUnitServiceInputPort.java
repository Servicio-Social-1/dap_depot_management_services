package com.dap.warehouse.unit.infrastructure.input.port;

import com.dap.warehouse.unit.domain.api.UnitRequest;
import com.dap.warehouse.unit.domain.model.Unit;
import org.springframework.http.ResponseEntity;
import java.util.List;

public interface IUnitServiceInputPort {
	
	ResponseEntity<List<Unit>> findAll();
	ResponseEntity<Unit> findById(Integer id);
	ResponseEntity<Unit> deleteById(Integer id);
	ResponseEntity<Unit> save(UnitRequest accessRequest, String nameMethod);

}
