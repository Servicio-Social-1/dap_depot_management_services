package com.dap.warehouse.management.infrastructure.output.port;

import com.dap.warehouse.management.domain.model.MaterialManagement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMaterialManagementRepositoryOutputPort extends JpaRepository<MaterialManagement, Integer>{

}
