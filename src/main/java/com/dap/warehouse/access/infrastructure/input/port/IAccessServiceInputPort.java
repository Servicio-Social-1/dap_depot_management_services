package com.dap.warehouse.access.infrastructure.input.port;

import com.dap.warehouse.access.domain.api.AccessRequest;
import com.dap.warehouse.access.domain.model.Access;
import org.springframework.http.ResponseEntity;

public interface IAccessServiceInputPort {

	ResponseEntity<Access> validateAccess(AccessRequest accessRequest);

}
