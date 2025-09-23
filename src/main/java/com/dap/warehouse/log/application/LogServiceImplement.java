package com.dap.warehouse.log.application;

import com.dap.warehouse.log.domain.api.LogRequest;
import com.dap.warehouse.log.domain.model.Log;
import com.dap.warehouse.log.domain.service.LogMapper;
import com.dap.warehouse.log.infrastructure.input.port.ILogServiceInputPort;
import com.dap.warehouse.log.infrastructure.output.port.ILogRepositoryOutputPort;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class LogServiceImplement implements ILogServiceInputPort {
	
	@Autowired
	private ILogRepositoryOutputPort iLogRepositoryOutputPort;
	
	@Autowired
	private LogMapper logMapper;
	
    public ResponseEntity<List<Log>> findAll(){
    	
    	var sortById = Sort.by("idLog");
    	ResponseEntity<List<Log>> response = null;
    	try {
			List<Log> logList = this.iLogRepositoryOutputPort.findAll(sortById);
			if (!logList.isEmpty()) {
				response = new ResponseEntity<>(logList, HttpStatus.OK);
						} else {
				response = new ResponseEntity<>(logList, HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			return response;
		}
    	return response;
    }
    

	@Override
	public ResponseEntity<Log> findById(Integer id) {
		
		ResponseEntity<Log> response = null;
		try {
			Optional<Log> optionalLog = iLogRepositoryOutputPort.findById(id);
            response = optionalLog.map(log -> new ResponseEntity<>(log, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
		} catch (Exception e) {
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			return response;
		}
		return response;
	}
	

	@Override
	@Transactional
	public ResponseEntity<Log> deleteById(Integer id) {
		
		ResponseEntity<Log> response = null;
		try {
			Optional<Log> optionalLog = iLogRepositoryOutputPort.findById(id);
			if(optionalLog.isPresent()) {
				iLogRepositoryOutputPort.delete(optionalLog.get());
				response = new ResponseEntity<>(optionalLog.get(),HttpStatus.MOVED_PERMANENTLY);
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
	public ResponseEntity<Log> save(LogRequest logRequest) {

		ResponseEntity<Log> response;
		try {
			var depotResponse = logMapper.fromRequestToMapping(logRequest.getModelRequest());
			var log = iLogRepositoryOutputPort.save(depotResponse);
			response = new ResponseEntity<>(log, HttpStatus.CREATED);
			
		} catch (Exception e) {
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			return response;	
		}
		return response;
	}


	@Override
	public ResponseEntity<List<Log>> findByTableAndEntity(String table, Integer entity) {

		ResponseEntity<List<Log>> response = null;
		try {
			List<Log> logList = iLogRepositoryOutputPort.findByTableAndEntity(table, entity);
			if (!logList.isEmpty()) {
				response = new ResponseEntity<>(logList, HttpStatus.OK);
			} else {
				response = new ResponseEntity<>(logList, HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			return response;
		}
		return response;
	}
}
