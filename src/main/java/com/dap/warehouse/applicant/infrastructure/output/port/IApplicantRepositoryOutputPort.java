package com.dap.warehouse.applicant.infrastructure.output.port;

import com.dap.warehouse.applicant.domain.model.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IApplicantRepositoryOutputPort extends JpaRepository<Applicant, Integer>{

}
