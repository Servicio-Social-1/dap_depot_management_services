package com.dap.warehouse.applicant.infrastructure.input.port;

import com.dap.warehouse.applicant.domain.api.ApplicantRequest;
import com.dap.warehouse.applicant.domain.model.Applicant;
import org.springframework.http.ResponseEntity;
import java.util.List;

public interface IApplicantServiceInputPort {
	
	ResponseEntity<List<Applicant>> findAll();
	ResponseEntity<Applicant> findById(Integer id);
	ResponseEntity<Applicant> deleteById(Integer id);
	ResponseEntity<Applicant> save(ApplicantRequest accessRequest);

}
