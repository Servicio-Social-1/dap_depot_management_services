package com.dap.warehouse.depot.infrastructure.output.port;

import com.dap.warehouse.depot.domain.model.Depot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDepotRepositoryOutputPort extends JpaRepository<Depot, Integer>{

}
