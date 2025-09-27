package com.dap.warehouse.status.application;

import com.dap.warehouse.log.domain.model.Log;
import com.dap.warehouse.log.infrastructure.output.port.ILogRepositoryOutputPort;
import com.dap.warehouse.status.domain.api.StatusRequest;
import com.dap.warehouse.status.domain.model.Status;
import com.dap.warehouse.status.domain.service.StatusMapper;
import com.dap.warehouse.status.infrastructure.input.port.IStatusServiceInputPort;
import com.dap.warehouse.status.infrastructure.output.port.IStatusRepositoryOutputPort;
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
public class StatusServiceImplement implements IStatusServiceInputPort {
	
	@Autowired
	private IStatusRepositoryOutputPort iStatusRepositoryOutputPort;
	
	@Autowired
	private StatusMapper statusMapper;

	@Autowired
	private ILogRepositoryOutputPort iLogRepositoryOutputPort;
	
    public ResponseEntity<List<Status>> findAll(){
    	
    	var sortById = Sort.by("idStatus");
    	ResponseEntity<List<Status>> response = null;
    	try {
			List<Status> statusList = this.iStatusRepositoryOutputPort.findAll(sortById);
			if (!statusList.isEmpty()) {
				response = new ResponseEntity<>(statusList, HttpStatus.OK);
						} else {
				response = new ResponseEntity<>(statusList, HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			return response;
		}
    	return response;
    }
    

	@Override
	public ResponseEntity<Status> findById(Integer id) {
		
		ResponseEntity<Status> response = null;
		try {
			Optional<Status> optionalStatus = iStatusRepositoryOutputPort.findById(id);
            response = optionalStatus.map(status -> new ResponseEntity<>(status, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
		} catch (Exception e) {
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			return response;
		}
		return response;
	}
	

	@Override
	@Transactional
	public ResponseEntity<Status> deleteById(Integer id) {
		
		ResponseEntity<Status> response = null;
		try {
			Optional<Status> optionalStatus = iStatusRepositoryOutputPort.findById(id);
			if(optionalStatus.isPresent()) {
				iStatusRepositoryOutputPort.delete(optionalStatus.get());
				response = new ResponseEntity<>(optionalStatus.get(),HttpStatus.MOVED_PERMANENTLY);
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
	public ResponseEntity<Status> save(StatusRequest statusRequest, String nameMethod) {

		ResponseEntity<Status> response;
		try {
			var depotResponse = statusMapper.fromRequestToMapping(statusRequest.getModelRequest());
			var status = iStatusRepositoryOutputPort.save(depotResponse);

			iLogRepositoryOutputPort.save(Log.builder()
					.date(LocalDate.now())
					.operation(nameMethod + " - > " + status)
					.entity(status.getIdStatus())
					.user(statusRequest.getUser())
					.table(Constants.LOG_TABLE_STATUS)
					.build());

			response = new ResponseEntity<>(status, HttpStatus.CREATED);
			
		} catch (Exception e) {
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			return response;	
		}
		return response;
	}

}
