package com.dap.warehouse.materialdepot.application;

import com.dap.warehouse.depot.domain.model.Depot;
import com.dap.warehouse.materialdepot.domain.api.DuplicatedRequest;
import com.dap.warehouse.materialdepot.domain.api.MaterialDepotDuplicated;
import com.dap.warehouse.materialdepot.domain.api.MaterialDepotRequest;
import com.dap.warehouse.materialdepot.domain.model.MaterialDepot;
import com.dap.warehouse.materialdepot.domain.service.MaterialDepotMapper;
import com.dap.warehouse.materialdepot.infrastructure.input.port.IMaterialDepotServiceInputPort;
import com.dap.warehouse.materialdepot.infrastructure.output.port.IMaterialDepotRepositoryOutputPort;
import com.dap.warehouse.status.domain.model.Status;
import com.dap.warehouse.status.infrastructure.output.port.IStatusRepositoryOutputPort;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MaterialDepotServiceImplement implements IMaterialDepotServiceInputPort {
	
	@Autowired
	private IMaterialDepotRepositoryOutputPort iMaterialDepotRepositoryOutputPort;

	@Autowired
	private IStatusRepositoryOutputPort iStatusRepositoryOutputPort;
	
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

	@Override
	public ResponseEntity<List<MaterialDepotDuplicated>> findDuplicated(DuplicatedRequest duplicatedRequest) {
		ResponseEntity<List<MaterialDepotDuplicated>> response = null;
		try {
			List<MaterialDepotDuplicated> materialDuplicatedList = new ArrayList<>();
			List<Depot> depotList = duplicatedRequest.getDepotList();
			List<Status> statusList = this.iStatusRepositoryOutputPort.findAll();
			if (!depotList.isEmpty()){
				for (Depot depot : depotList){
                    for (Status status: statusList) {
						MaterialDepot duplicatedField =
								this.iMaterialDepotRepositoryOutputPort.findByUniqueFields(
										duplicatedRequest.getMaterial(),
										depot, status);
						if (duplicatedField != null){
							materialDuplicatedList.add(MaterialDepotDuplicated.builder()
									.materialDepot(duplicatedField)
									.isDuplicated(true)
									.build());
						}
					}
				}
			}
			if (!materialDuplicatedList.isEmpty()) {
				response = new ResponseEntity<>(materialDuplicatedList, HttpStatus.OK);
			} else {
				response = new ResponseEntity<>(materialDuplicatedList, HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			return response;
		}
		return response;
	}

	@Override
	public ResponseEntity<List<MaterialDepot>> findByMaterialId(Integer materialId) {
		ResponseEntity<List<MaterialDepot>> response = null;
		try {
			List<MaterialDepot> materialDepotList = this.iMaterialDepotRepositoryOutputPort.findByMaterialId(materialId);
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
