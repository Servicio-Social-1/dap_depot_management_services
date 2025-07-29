package com.dap.warehouse.management.application;

import com.dap.warehouse.management.domain.api.MaterialManagementRequest;
import com.dap.warehouse.management.domain.model.MaterialManagement;
import com.dap.warehouse.management.domain.service.MaterialManagementMapper;
import com.dap.warehouse.management.infrastructure.input.port.IMaterialManagementServiceInputPort;
import com.dap.warehouse.management.infrastructure.output.port.IMaterialManagementRepositoryOutputPort;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MaterialManagementServiceImplement implements IMaterialManagementServiceInputPort {
	
	@Autowired
	private IMaterialManagementRepositoryOutputPort iMaterialManagementRepositoryOutputPort;
	
	@Autowired
	private MaterialManagementMapper materialManagementMapper;
	
    public ResponseEntity<List<MaterialManagement>> findAll(){
    	
    	var sortById = Sort.by("idMaterialManagement");
    	ResponseEntity<List<MaterialManagement>> response = null;
    	try {
			List<MaterialManagement> materialManagementList = this.iMaterialManagementRepositoryOutputPort.findAll(sortById);
			if (!materialManagementList.isEmpty()) {
				response = new ResponseEntity<>(materialManagementList, HttpStatus.OK);
						} else {
				response = new ResponseEntity<>(materialManagementList, HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			return response;
		}
    	return response;
    }
    

	@Override
	public ResponseEntity<MaterialManagement> findById(Integer id) {
		
		ResponseEntity<MaterialManagement> response = null;
		try {
			Optional<MaterialManagement> optionalMaterial = iMaterialManagementRepositoryOutputPort.findById(id);
            response = optionalMaterial.map(material -> new ResponseEntity<>(material, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
		} catch (Exception e) {
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			return response;
		}
		return response;
	}
	

	@Override
	@Transactional
	public ResponseEntity<MaterialManagement> deleteById(Integer id) {
		
		ResponseEntity<MaterialManagement> response = null;
		try {
			Optional<MaterialManagement> optionalMaterial = iMaterialManagementRepositoryOutputPort.findById(id);
			if(optionalMaterial.isPresent()) {
				iMaterialManagementRepositoryOutputPort.delete(optionalMaterial.get());
				response = new ResponseEntity<>(optionalMaterial.get(),HttpStatus.MOVED_PERMANENTLY);
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
	public ResponseEntity<MaterialManagement> save(MaterialManagementRequest materialManagementRequest) {

		ResponseEntity<MaterialManagement> response;
		try {
			var depotResponse = materialManagementMapper.fromRequestToMapping(materialManagementRequest.getModelRequest());
			var materialManagement = iMaterialManagementRepositoryOutputPort.save(depotResponse);
			response = new ResponseEntity<>(materialManagement, HttpStatus.CREATED);
			
		} catch (Exception e) {
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			return response;	
		}
		return response;
	}

}
