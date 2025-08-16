package com.dap.warehouse.material.infrastructure.output.port;

import com.dap.warehouse.material.domain.model.Material;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMaterialRepositoryOutputPort extends JpaRepository<Material, Integer>{
    Material findFirstByOrderByIdMaterialDesc();
}
