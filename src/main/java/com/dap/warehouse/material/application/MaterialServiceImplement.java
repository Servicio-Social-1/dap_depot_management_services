package com.dap.warehouse.material.application;

import com.dap.warehouse.material.domain.api.MaterialRequest;
import com.dap.warehouse.material.domain.model.Material;
import com.dap.warehouse.material.domain.service.MaterialMapper;
import com.dap.warehouse.material.infrastructure.input.port.IMaterialServiceInputPort;
import com.dap.warehouse.material.infrastructure.output.port.IMaterialRepositoryOutputPort;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MaterialServiceImplement implements IMaterialServiceInputPort {
	
	@Autowired
	private IMaterialRepositoryOutputPort iMaterialRepositoryOutputPort;
	
	@Autowired
	private MaterialMapper materialMapper;
	
    public ResponseEntity<List<Material>> findAll(){
    	
    	var sortById = Sort.by("idMaterial");
    	ResponseEntity<List<Material>> response = null;
    	try {
			List<Material> materialList = this.iMaterialRepositoryOutputPort.findAll(sortById);
			if (!materialList.isEmpty()) {
				response = new ResponseEntity<>(materialList, HttpStatus.OK);
						} else {
				response = new ResponseEntity<>(materialList, HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			return response;
		}
    	return response;
    }
    

	@Override
	public ResponseEntity<Material> findById(Integer id) {
		
		ResponseEntity<Material> response = null;
		try {
			Optional<Material> optionalMaterial = iMaterialRepositoryOutputPort.findById(id);
            response = optionalMaterial.map(material -> new ResponseEntity<>(material, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
		} catch (Exception e) {
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			return response;
		}
		return response;
	}
	

	@Override
	@Transactional
	public ResponseEntity<Material> deleteById(Integer id) {
		
		ResponseEntity<Material> response = null;
		try {
			Optional<Material> optionalMaterial = iMaterialRepositoryOutputPort.findById(id);
			if(optionalMaterial.isPresent()) {
				iMaterialRepositoryOutputPort.delete(optionalMaterial.get());
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
	public ResponseEntity<Material> save(MaterialRequest materialRequest) {

		ResponseEntity<Material> response;
		try {
			var depotResponse = materialMapper.fromRequestToMapping(materialRequest.getModelRequest());
			var material = iMaterialRepositoryOutputPort.save(depotResponse);
			response = new ResponseEntity<>(material, HttpStatus.CREATED);
			
		} catch (Exception e) {
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			return response;	
		}
		return response;
	}

}
