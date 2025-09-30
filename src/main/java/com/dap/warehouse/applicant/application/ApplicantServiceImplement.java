package com.dap.warehouse.applicant.application;

import com.dap.warehouse.applicant.domain.api.ApplicantRequest;
import com.dap.warehouse.applicant.domain.model.Applicant;
import com.dap.warehouse.applicant.domain.service.ApplicantMapper;
import com.dap.warehouse.applicant.infrastructure.input.port.IApplicantServiceInputPort;
import com.dap.warehouse.applicant.infrastructure.output.port.IApplicantRepositoryOutputPort;
import com.dap.warehouse.log.domain.model.Log;
import com.dap.warehouse.log.infrastructure.output.port.ILogRepositoryOutputPort;
import com.dap.warehouse.util.Constants;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ApplicantServiceImplement implements IApplicantServiceInputPort {
	
	@Autowired
	private IApplicantRepositoryOutputPort iApplicantRepositoryOutputPort;
	
	@Autowired
	private ApplicantMapper applicantMapper;

	@Autowired
	private ILogRepositoryOutputPort iLogRepositoryOutputPort;
	
    public ResponseEntity<List<Applicant>> findAll(){
    	
    	var sortById = Sort.by("idApplicant");
    	ResponseEntity<List<Applicant>> response = null;
    	try {
			List<Applicant> applicantList = this.iApplicantRepositoryOutputPort.findAll(sortById);
			if (!applicantList.isEmpty()) {
				response = new ResponseEntity<>(applicantList, HttpStatus.OK);
						} else {
				response = new ResponseEntity<>(applicantList, HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			return response;
		}
    	return response;
    }
    

	@Override
	public ResponseEntity<Applicant> findById(Integer id) {
		
		ResponseEntity<Applicant> response = null;
		try {
			Optional<Applicant> optionalApplicant = iApplicantRepositoryOutputPort.findById(id);
            response = optionalApplicant.map(applicant -> new ResponseEntity<>(applicant, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
		} catch (Exception e) {
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			return response;
		}
		return response;
	}
	

	@Override
	@Transactional
	public ResponseEntity<Applicant> deleteById(Integer id) {
		
		ResponseEntity<Applicant> response = null;
		try {
			Optional<Applicant> optionalApplicant = iApplicantRepositoryOutputPort.findById(id);
			if(optionalApplicant.isPresent()) {
				iApplicantRepositoryOutputPort.delete(optionalApplicant.get());
				response = new ResponseEntity<>(optionalApplicant.get(),HttpStatus.MOVED_PERMANENTLY);
			}else {
				response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			return response;
		}
		return response;
	}
	

	@Override
	@Transactional
	public ResponseEntity<Applicant> save(ApplicantRequest applicantRequest, String nameMethod) {

		ResponseEntity<Applicant> response;
		try {
			var depotResponse = applicantMapper.fromRequestToMapping(applicantRequest.getModelRequest());
			var applicant = iApplicantRepositoryOutputPort.save(depotResponse);

			iLogRepositoryOutputPort.save(Log.builder()
					.date(LocalDate.now())
					.operation(nameMethod + " - > " + applicant)
					.entity(applicant.getIdApplicant())
					.user(applicantRequest.getUser())
					.table(Constants.LOG_TABLE_APPLICANT)
					.build());

			response = new ResponseEntity<>(applicant, HttpStatus.CREATED);
			
		} catch (Exception e) {
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			return response;	
		}
		return response;
	}

}
