package com.dap.warehouse.status.infrastructure.input.port;

import com.dap.warehouse.status.domain.api.StatusRequest;
import com.dap.warehouse.status.domain.model.Status;
import org.springframework.http.ResponseEntity;
import java.util.List;

public interface IStatusServiceInputPort {
	
	ResponseEntity<List<Status>> findAll();
	ResponseEntity<Status> findById(Integer id);
	ResponseEntity<Status> deleteById(Integer id);
	ResponseEntity<Status> save(StatusRequest accessRequest);

}
