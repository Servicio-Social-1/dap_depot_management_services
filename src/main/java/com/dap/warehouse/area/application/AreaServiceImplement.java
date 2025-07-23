package com.dap.warehouse.area.application;

import com.dap.warehouse.area.domain.api.AreaRequest;
import com.dap.warehouse.area.domain.model.Area;
import com.dap.warehouse.area.domain.service.AreaMapper;
import com.dap.warehouse.area.infrastructure.input.port.IAreaServiceInputPort;
import com.dap.warehouse.area.infrastructure.output.port.IAreaRepositoryOutputPort;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AreaServiceImplement implements IAreaServiceInputPort {
	
	@Autowired
	private IAreaRepositoryOutputPort iAreaRepositoryOutputPort;
	
	@Autowired
	private AreaMapper areaMapper;
	
    public ResponseEntity<List<Area>> findAll(){
    	
    	var sortById = Sort.by("idArea");
    	ResponseEntity<List<Area>> response = null;
    	try {
			List<Area> areaList = this.iAreaRepositoryOutputPort.findAll(sortById);
			if (!areaList.isEmpty()) {
				response = new ResponseEntity<>(areaList, HttpStatus.OK);
						} else {
				response = new ResponseEntity<>(areaList, HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			return response;
		}
    	return response;
    }
    

	@Override
	public ResponseEntity<Area> findById(Integer id) {
		
		ResponseEntity<Area> response = null;
		try {
			Optional<Area> optionalArea = iAreaRepositoryOutputPort.findById(id);
            response = optionalArea.map(area -> new ResponseEntity<>(area, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
		} catch (Exception e) {
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			return response;
		}
		return response;
	}
	

	@Override
	@Transactional
	public ResponseEntity<Area> deleteById(Integer id) {
		
		ResponseEntity<Area> response = null;
		try {
			Optional<Area> optionalArea = iAreaRepositoryOutputPort.findById(id);
			if(optionalArea.isPresent()) {
				iAreaRepositoryOutputPort.delete(optionalArea.get());
				response = new ResponseEntity<>(optionalArea.get(),HttpStatus.MOVED_PERMANENTLY);
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
	public ResponseEntity<Area> save(AreaRequest areaRequest) {

		ResponseEntity<Area> response;
		try {
			var depotResponse = areaMapper.fromRequestToMapping(areaRequest.getModelRequest());
			var area = iAreaRepositoryOutputPort.save(depotResponse);
			response = new ResponseEntity<>(area, HttpStatus.CREATED);
			
		} catch (Exception e) {
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			return response;	
		}
		return response;
	}

}
