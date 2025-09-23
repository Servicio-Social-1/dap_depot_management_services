package com.dap.warehouse.log.infrastructure.output.port;

import com.dap.warehouse.log.domain.model.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ILogRepositoryOutputPort extends JpaRepository<Log, Integer>{

    List<Log> findByTableAndEntity(String table, Integer entity);

}
