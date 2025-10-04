package com.dap.warehouse.management.infrastructure.output.port;

import com.dap.warehouse.area.domain.model.Area;
import com.dap.warehouse.depot.domain.model.Depot;
import com.dap.warehouse.management.domain.model.Management;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.time.LocalDate;
import java.util.List;

public interface IManagementRepositoryOutputPort extends JpaRepository<Management, Integer>{
    Management findFirstByOrderByIdMaterialManagementDesc();

    @Query("SELECT m FROM Management m WHERE m.date BETWEEN :startDate AND :endDate")
    List<Management> managementListByPeriod(LocalDate startDate, LocalDate endDate);

    @Query("SELECT m FROM Management m WHERE m.date BETWEEN :startDate AND :endDate AND m.depot = :depot")
    List<Management> managementListByPeriodAndDepot(LocalDate startDate, LocalDate endDate, Depot depot);

    @Query("SELECT m FROM Management m WHERE m.date BETWEEN :startDate AND :endDate AND m.depot = :depot AND m.userReceiver.area = :area")
    List<Management> managementListByPeriodDepotAndArea(LocalDate startDate, LocalDate endDate, Depot depot, Area area);

}
