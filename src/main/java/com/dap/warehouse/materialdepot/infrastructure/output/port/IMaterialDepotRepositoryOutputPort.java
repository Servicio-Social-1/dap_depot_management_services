package com.dap.warehouse.materialdepot.infrastructure.output.port;

import com.dap.warehouse.materialdepot.domain.model.MaterialDepot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMaterialDepotRepositoryOutputPort extends JpaRepository<MaterialDepot, Integer>{

}
