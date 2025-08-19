package com.dap.warehouse.material.application;

import com.dap.warehouse.depot.domain.model.Depot;
import com.dap.warehouse.material.domain.api.MaterialModel;
import com.dap.warehouse.material.domain.api.MaterialRequest;
import com.dap.warehouse.material.domain.model.Material;
import com.dap.warehouse.material.domain.service.MaterialMapper;
import com.dap.warehouse.material.infrastructure.input.port.IMaterialServiceInputPort;
import com.dap.warehouse.material.infrastructure.output.port.IMaterialRepositoryOutputPort;
import com.dap.warehouse.materialdepot.domain.model.MaterialDepot;
import com.dap.warehouse.materialdepot.infrastructure.output.port.IMaterialDepotRepositoryOutputPort;
import com.dap.warehouse.status.domain.model.Status;
import com.dap.warehouse.status.infrastructure.output.port.IStatusRepositoryOutputPort;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class MaterialServiceImplement implements IMaterialServiceInputPort {
	
	@Autowired
	private IMaterialRepositoryOutputPort iMaterialRepositoryOutputPort;

	@Autowired
	private IMaterialDepotRepositoryOutputPort iMaterialDepotRepositoryOutputPort;

	@Autowired
	private IStatusRepositoryOutputPort iStatusRepositoryOutputPort;
	
	@Autowired
	private MaterialMapper materialMapper;
	
    public ResponseEntity<List<MaterialModel>> findAll(){
    	var sortById = Sort.by("idMaterial");
    	ResponseEntity<List<MaterialModel>> response;
    	try {
			List<Material> materialList = this.iMaterialRepositoryOutputPort.findAll(sortById);
			List<MaterialModel> materialModelList = createMaterialModelArray(materialList);
			if (!materialModelList.isEmpty()) {
				response = new ResponseEntity<>(materialModelList, HttpStatus.OK);
						} else {
				response = new ResponseEntity<>(materialModelList, HttpStatus.NO_CONTENT);
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
	public ResponseEntity<Material> save(MaterialRequest materialRequest) {
		ResponseEntity<Material> response;
		try {
			var depotResponse = materialMapper.fromRequestToMapping(materialRequest.getModelRequest());
            String serialNumber = getSerialNumber();
			depotResponse.setSerialNumber(serialNumber);
			var material = iMaterialRepositoryOutputPort.save(depotResponse);
			List<Depot> depotList = materialRequest.getModelRequest().getDepotList();
			addMaterialByStatusAndDepot(depotList, material);
			response = new ResponseEntity<>(material, HttpStatus.CREATED);
		} catch (Exception e) {
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			return response;	
		}
		return response;
	}

	public String getSerialNumber(){
		String serialNumber;
		int lastid = 0;
        Material lastMaterial = iMaterialRepositoryOutputPort.findFirstByOrderByIdMaterialDesc();
        if(lastMaterial != null){
            lastid = lastMaterial.getIdMaterial() + 1;
        } else {
			lastid = lastid + 1;
		}
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyyMMdd");
		serialNumber = "MT-" + LocalDate.now().format(format) + lastid;
        return serialNumber;
    }

	public void addMaterialByStatusAndDepot(List<Depot> depotList, Material material){
		if(!depotList.isEmpty()) {
		 for (Depot depot: depotList) {
		  List<Status> statusList = this.iStatusRepositoryOutputPort.findAll();
			for (Status status : statusList) {
				iMaterialDepotRepositoryOutputPort.save(MaterialDepot.builder()
						.depot(depot)
						.material(material)
						.stock(0)
						.status(status)
						.build());
			 }
		   }
		}
	}

	public List<MaterialDepot> findRelationshipsByMaterialId(Material material) {
		List<MaterialDepot> materialDepotList = iMaterialDepotRepositoryOutputPort.findByMaterial(material);
		if(materialDepotList.isEmpty()){
			return null;
		}
		return materialDepotList;
	}

	public List<MaterialModel> createMaterialModelArray(List<Material> materialList) {
		List<MaterialModel> materialModelList = new ArrayList<>();
		if(materialList != null && !materialList.isEmpty()) {
		for (Material material : materialList) {
			List<MaterialDepot> materialDepotList = findRelationshipsByMaterialId(material);
			List<Depot> depotList;
			if (materialDepotList != null) {
				depotList = materialDepotList.stream()
						.map(MaterialDepot::getDepot)
						.toList();
			} else {
				depotList = null;
			}
			MaterialModel materialModel = materialMapper.fromMappingToModel(material);
			materialModel.setDepotList(depotList);
            materialModelList.add(materialModel);
		}
		    return materialModelList;
		} else {
			return null;
		}
	}

}
