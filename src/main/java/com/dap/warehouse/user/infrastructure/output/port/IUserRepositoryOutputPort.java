package com.dap.warehouse.user.infrastructure.output.port;

import com.dap.warehouse.user.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepositoryOutputPort extends JpaRepository<User, Integer>{

}
