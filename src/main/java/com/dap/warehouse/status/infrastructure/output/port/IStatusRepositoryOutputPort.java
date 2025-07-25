package com.dap.warehouse.status.infrastructure.output.port;

import com.dap.warehouse.status.domain.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IStatusRepositoryOutputPort extends JpaRepository<Status, Integer>{

}
