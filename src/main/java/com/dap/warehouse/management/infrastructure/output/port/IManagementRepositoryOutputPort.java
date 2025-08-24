package com.dap.warehouse.management.infrastructure.output.port;

import com.dap.warehouse.management.domain.model.Management;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IManagementRepositoryOutputPort extends JpaRepository<Management, Integer>{

}
