package com.dap.warehouse.status.infrastructure.output.port;

import com.dap.warehouse.status.domain.model.Area;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAreaRepositoryOutputPort extends JpaRepository<Area, Integer>{

}
