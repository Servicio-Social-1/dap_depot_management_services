package com.dap.warehouse.unit.infrastructure.output.port;

import com.dap.warehouse.unit.domain.model.Unit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUnitRepositoryOutputPort extends JpaRepository<Unit, Integer>{

}
