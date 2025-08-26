package com.dap.warehouse.materialdepot.infrastructure.output.port;

import com.dap.warehouse.material.domain.model.Material;
import com.dap.warehouse.materialdepot.domain.model.MaterialDepot;
import com.dap.warehouse.status.domain.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IMaterialDepotRepositoryOutputPort extends JpaRepository<MaterialDepot, Integer>{

    @Query("SELECT md FROM MaterialDepot md WHERE md.depot.idDepot = :depotId")
    List<MaterialDepot> findByDepotId(Integer depotId);

    @Query("SELECT md FROM MaterialDepot md WHERE md.material = :material AND md.status = :status")
    MaterialDepot findByMaterialAndStatus(Material material, Status status);
}
