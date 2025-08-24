package com.dap.warehouse.managementmaterial.application;

import com.dap.warehouse.managementmaterial.domain.api.ManagementMaterialRequest;
import com.dap.warehouse.managementmaterial.domain.model.ManagementMaterial;
import com.dap.warehouse.managementmaterial.domain.service.ManagementMaterialMapper;
import com.dap.warehouse.managementmaterial.infrastructure.input.port.IManagementMaterialServiceInputPort;
import com.dap.warehouse.managementmaterial.infrastructure.output.port.IManagementMaterialRepositoryOutputPort;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ManagementMaterialServiceImplement implements IManagementMaterialServiceInputPort {
	
	@Autowired
	private IManagementMaterialRepositoryOutputPort iManagementMaterialRepositoryOutputPort;
	
	@Autowired
	private ManagementMaterialMapper managementMaterialMapper;
	
    public ResponseEntity<List<ManagementMaterial>> findAll(){
    	
    	var sortById = Sort.by("idManagementMaterial");
    	ResponseEntity<List<ManagementMaterial>> response = null;
    	try {
			List<ManagementMaterial> managementMaterialList = this.iManagementMaterialRepositoryOutputPort.findAll(sortById);
			if (!managementMaterialList.isEmpty()) {
				response = new ResponseEntity<>(managementMaterialList, HttpStatus.OK);
						} else {
				response = new ResponseEntity<>(managementMaterialList, HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			return response;
		}
    	return response;
    }
    

	@Override
	public ResponseEntity<ManagementMaterial> findById(Integer id) {
		
		ResponseEntity<ManagementMaterial> response = null;
		try {
			Optional<ManagementMaterial> optionalMaterial = iManagementMaterialRepositoryOutputPort.findById(id);
            response = optionalMaterial.map(material -> new ResponseEntity<>(material, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
		} catch (Exception e) {
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			return response;
		}
		return response;
	}
	

	@Override
	@Transactional
	public ResponseEntity<ManagementMaterial> deleteById(Integer id) {
		
		ResponseEntity<ManagementMaterial> response = null;
		try {
			Optional<ManagementMaterial> optionalMaterial = iManagementMaterialRepositoryOutputPort.findById(id);
			if(optionalMaterial.isPresent()) {
				iManagementMaterialRepositoryOutputPort.delete(optionalMaterial.get());
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
	public ResponseEntity<ManagementMaterial> save(ManagementMaterialRequest managementMaterialRequest) {

		ResponseEntity<ManagementMaterial> response;
		try {
			var managementResponse = managementMaterialMapper.fromRequestToMapping(managementMaterialRequest.getModelRequest());
			var materialManagement = iManagementMaterialRepositoryOutputPort.save(managementResponse);
			response = new ResponseEntity<>(materialManagement, HttpStatus.CREATED);
			
		} catch (Exception e) {
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			return response;	
		}
		return response;
	}

}
