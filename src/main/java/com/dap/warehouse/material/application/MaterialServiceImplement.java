package com.dap.warehouse.material.application;

import com.dap.warehouse.depot.domain.model.Depot;
import com.dap.warehouse.log.domain.model.Log;
import com.dap.warehouse.log.infrastructure.output.port.ILogRepositoryOutputPort;
import com.dap.warehouse.material.domain.api.MaterialRequest;
import com.dap.warehouse.material.domain.model.Material;
import com.dap.warehouse.material.domain.service.MaterialMapper;
import com.dap.warehouse.material.infrastructure.input.port.IMaterialServiceInputPort;
import com.dap.warehouse.material.infrastructure.output.port.IMaterialRepositoryOutputPort;
import com.dap.warehouse.materialdepot.domain.model.MaterialDepot;
import com.dap.warehouse.materialdepot.infrastructure.output.port.IMaterialDepotRepositoryOutputPort;
import com.dap.warehouse.status.domain.model.Status;
import com.dap.warehouse.status.infrastructure.output.port.IStatusRepositoryOutputPort;
import com.dap.warehouse.util.Constants;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

	@Autowired
	private ILogRepositoryOutputPort iLogRepositoryOutputPort;
	
    public ResponseEntity<List<Material>> findAll(){
    	var sortById = Sort.by("idMaterial");
    	ResponseEntity<List<Material>> response;
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
	public ResponseEntity<Material> save(MaterialRequest materialRequest, String nameMethod) {
		ResponseEntity<Material> response;
		try {
			var depotResponse = materialMapper.fromRequestToMapping(materialRequest.getModelRequest());
            if(nameMethod.compareToIgnoreCase("save") == 0){
				String serialNumber = getSerialNumber();
				depotResponse.setSerialNumber(serialNumber);
			}
			var material = iMaterialRepositoryOutputPort.save(depotResponse);
			if(nameMethod.compareToIgnoreCase("save") == 0) {
				List<Depot> depotList = materialRequest.getModelRequest().getDepotList();
				addMaterialByStatusAndDepot(depotList, material);
			}

			iLogRepositoryOutputPort.save(Log.builder()
					.date(LocalDate.now())
					.operation(nameMethod + " - > " + material)
					.entity(material.getIdMaterial())
					.user(materialRequest.getUser())
					.table(Constants.LOG_TABLE_MATERIAL)
					.build());

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

}
