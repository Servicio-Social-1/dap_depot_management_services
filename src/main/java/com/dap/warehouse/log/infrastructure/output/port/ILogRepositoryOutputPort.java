package com.dap.warehouse.log.infrastructure.output.port;

import com.dap.warehouse.log.domain.model.Log;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ILogRepositoryOutputPort extends JpaRepository<Log, Integer>{

}
