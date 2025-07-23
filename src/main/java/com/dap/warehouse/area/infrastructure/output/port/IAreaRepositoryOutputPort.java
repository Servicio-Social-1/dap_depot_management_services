package com.dap.warehouse.area.infrastructure.output.port;

import com.dap.warehouse.area.domain.model.Area;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAreaRepositoryOutputPort extends JpaRepository<Area, Integer>{

}
