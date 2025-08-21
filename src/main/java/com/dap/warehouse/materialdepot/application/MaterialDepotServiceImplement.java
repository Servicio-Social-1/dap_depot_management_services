package com.dap.warehouse.materialdepot.application;

import com.dap.warehouse.materialdepot.domain.api.MaterialDepotRequest;
import com.dap.warehouse.materialdepot.domain.model.MaterialDepot;
import com.dap.warehouse.materialdepot.domain.service.MaterialDepotMapper;
import com.dap.warehouse.materialdepot.infrastructure.input.port.IMaterialDepotServiceInputPort;
import com.dap.warehouse.materialdepot.infrastructure.output.port.IMaterialDepotRepositoryOutputPort;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MaterialDepotServiceImplement implements IMaterialDepotServiceInputPort {
	
	@Autowired
	private IMaterialDepotRepositoryOutputPort iMaterialDepotRepositoryOutputPort;
	
	@Autowired
	private MaterialDepotMapper materialDepotMapper;

	@Override
    public ResponseEntity<List<MaterialDepot>> findAll(){
    	
    	var sortById = Sort.by("idMaterialDepot");
    	ResponseEntity<List<MaterialDepot>> response = null;
    	try {
			List<MaterialDepot> materialDepotList = this.iMaterialDepotRepositoryOutputPort.findAll(sortById);
			if (!materialDepotList.isEmpty()) {
				response = new ResponseEntity<>(materialDepotList, HttpStatus.OK);
						} else {
				response = new ResponseEntity<>(materialDepotList, HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			return response;
		}
    	return response;
    }
    

	@Override
	public ResponseEntity<MaterialDepot> findById(Integer id) {
		
		ResponseEntity<MaterialDepot> response = null;
		try {
			Optional<MaterialDepot> optionalDepot = iMaterialDepotRepositoryOutputPort.findById(id);
            response = optionalDepot.map(depot -> new ResponseEntity<>(depot, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
		} catch (Exception e) {
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			return response;
		}
		return response;
	}
	

	@Override
	@Transactional
	public ResponseEntity<MaterialDepot> save(MaterialDepotRequest materialDepotRequest) {

		ResponseEntity<MaterialDepot> response;
		try {
			var depotResponse = materialDepotMapper.fromRequestToMapping(materialDepotRequest.getModelRequest());
			var depot = iMaterialDepotRepositoryOutputPort.save(depotResponse);
			response = new ResponseEntity<>(depot, HttpStatus.CREATED);
			
		} catch (Exception e) {
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			return response;	
		}
		return response;
	}

	@Override
	public ResponseEntity<List<MaterialDepot>> findByDepotId(Integer depotId) {
		ResponseEntity<List<MaterialDepot>> response = null;
		try {
			List<MaterialDepot> materialDepotList = this.iMaterialDepotRepositoryOutputPort.findByDepotId(depotId);
			if (!materialDepotList.isEmpty()) {
				response = new ResponseEntity<>(materialDepotList, HttpStatus.OK);
			} else {
				response = new ResponseEntity<>(materialDepotList, HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			return response;
		}
		return response;
	}

}
