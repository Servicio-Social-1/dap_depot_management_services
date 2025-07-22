package com.dap.warehouse.profile.infrastructure.output.port;

import com.dap.warehouse.profile.domain.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProfileRepositoryOutputPort extends JpaRepository<Profile, Integer>{

}
