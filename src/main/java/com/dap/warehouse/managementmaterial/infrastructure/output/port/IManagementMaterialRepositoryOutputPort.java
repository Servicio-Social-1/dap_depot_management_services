package com.dap.warehouse.managementmaterial.infrastructure.output.port;

import com.dap.warehouse.managementmaterial.domain.model.ManagementMaterial;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IManagementMaterialRepositoryOutputPort extends JpaRepository<ManagementMaterial, Integer>{

}
