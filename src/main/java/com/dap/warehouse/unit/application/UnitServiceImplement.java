package com.dap.warehouse.unit.application;

import com.dap.warehouse.unit.domain.api.UnitRequest;
import com.dap.warehouse.unit.domain.model.Unit;
import com.dap.warehouse.unit.domain.service.UnitMapper;
import com.dap.warehouse.unit.infrastructure.input.port.IUnitServiceInputPort;
import com.dap.warehouse.unit.infrastructure.output.port.IUnitRepositoryOutputPort;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UnitServiceImplement implements IUnitServiceInputPort {
	
	@Autowired
	private IUnitRepositoryOutputPort iUnitRepositoryOutputPort;
	
	@Autowired
	private UnitMapper unitMapper;
	
    public ResponseEntity<List<Unit>> findAll(){
    	
    	var sortById = Sort.by("idUnit");
    	ResponseEntity<List<Unit>> response = null;
    	try {
			List<Unit> unitList = this.iUnitRepositoryOutputPort.findAll(sortById);
			if (!unitList.isEmpty()) {
				response = new ResponseEntity<>(unitList, HttpStatus.OK);
						} else {
				response = new ResponseEntity<>(unitList, HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			return response;
		}
    	return response;
    }
    

	@Override
	public ResponseEntity<Unit> findById(Integer id) {
		
		ResponseEntity<Unit> response = null;
		try {
			Optional<Unit> optionalUnit = iUnitRepositoryOutputPort.findById(id);
            response = optionalUnit.map(unit -> new ResponseEntity<>(unit, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
		} catch (Exception e) {
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			return response;
		}
		return response;
	}
	

	@Override
	@Transactional
	public ResponseEntity<Unit> deleteById(Integer id) {
		
		ResponseEntity<Unit> response = null;
		try {
			Optional<Unit> optionalUnit = iUnitRepositoryOutputPort.findById(id);
			if(optionalUnit.isPresent()) {
				iUnitRepositoryOutputPort.delete(optionalUnit.get());
				response = new ResponseEntity<>(optionalUnit.get(),HttpStatus.MOVED_PERMANENTLY);
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
	public ResponseEntity<Unit> save(UnitRequest unitRequest) {

		ResponseEntity<Unit> response;
		try {
			var depotResponse = unitMapper.fromRequestToMapping(unitRequest.getModelRequest());
			var unit = iUnitRepositoryOutputPort.save(depotResponse);
			response = new ResponseEntity<>(unit, HttpStatus.CREATED);
			
		} catch (Exception e) {
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			return response;	
		}
		return response;
	}

}
