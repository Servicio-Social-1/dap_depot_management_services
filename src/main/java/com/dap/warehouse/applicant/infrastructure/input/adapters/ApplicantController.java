package com.dap.warehouse.applicant.infrastructure.input.adapters;

import com.dap.warehouse.applicant.domain.api.ApplicantRequest;
import com.dap.warehouse.applicant.domain.model.Applicant;
import com.dap.warehouse.applicant.infrastructure.input.port.IApplicantServiceInputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/dap/warehouse/v1/applicantController")
public class ApplicantController {
	
	@Autowired
	private IApplicantServiceInputPort iApplicantServiceInputPort;
	
	@GetMapping("/findAll")
	public ResponseEntity<List<Applicant>> findAll(){
		return this.iApplicantServiceInputPort.findAll();
	}
	
	@GetMapping("/findById/{id}")
	public ResponseEntity<Applicant> findById(@PathVariable("id") Integer id){
		return this.iApplicantServiceInputPort.findById(id);
	}
	
	@DeleteMapping("/deleteById/{id}")
	public ResponseEntity<Applicant> deleteById(@PathVariable("id") Integer id){
		return this.iApplicantServiceInputPort.deleteById(id);
	}
	
	@PostMapping("/save")
	public ResponseEntity<Applicant> save(@RequestBody ApplicantRequest applicantRequest){
		return this.iApplicantServiceInputPort.save(applicantRequest);
	}
	
	@PutMapping("/update")
	public ResponseEntity<Applicant> update(@RequestBody ApplicantRequest applicantRequest){
		return this.iApplicantServiceInputPort.save(applicantRequest);
	}

}
