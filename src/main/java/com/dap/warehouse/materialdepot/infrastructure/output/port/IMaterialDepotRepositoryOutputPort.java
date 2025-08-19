package com.dap.warehouse.materialdepot.infrastructure.output.port;

import com.dap.warehouse.material.domain.model.Material;
import com.dap.warehouse.materialdepot.domain.model.MaterialDepot;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface IMaterialDepotRepositoryOutputPort extends JpaRepository<MaterialDepot, Integer>{
    List<MaterialDepot> findByMaterial(Material material);
}
