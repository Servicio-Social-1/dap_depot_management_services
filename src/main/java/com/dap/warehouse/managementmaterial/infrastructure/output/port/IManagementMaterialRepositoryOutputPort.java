package com.dap.warehouse.managementmaterial.infrastructure.output.port;

import com.dap.warehouse.management.domain.model.Management;
import com.dap.warehouse.managementmaterial.domain.model.ManagementMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface IManagementMaterialRepositoryOutputPort extends JpaRepository<ManagementMaterial, Integer>{

    List<ManagementMaterial> findByManagement(Management management);

}
