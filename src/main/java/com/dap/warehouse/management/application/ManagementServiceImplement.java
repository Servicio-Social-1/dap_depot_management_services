package com.dap.warehouse.management.application;

import com.dap.warehouse.log.domain.model.Log;
import com.dap.warehouse.log.infrastructure.output.port.ILogRepositoryOutputPort;
import com.dap.warehouse.management.domain.api.ManagementModel;
import com.dap.warehouse.management.domain.api.ManagementRequest;
import com.dap.warehouse.management.domain.api.ReportRequest;
import com.dap.warehouse.management.domain.model.Management;
import com.dap.warehouse.management.domain.service.ManagementMapper;
import com.dap.warehouse.management.infrastructure.input.port.IManagementServiceInputPort;
import com.dap.warehouse.management.infrastructure.output.port.IManagementRepositoryOutputPort;
import com.dap.warehouse.managementmaterial.domain.model.ManagementMaterial;
import com.dap.warehouse.managementmaterial.infrastructure.output.port.IManagementMaterialRepositoryOutputPort;
import com.dap.warehouse.materialdepot.domain.model.MaterialDepot;
import com.dap.warehouse.materialdepot.infrastructure.output.port.IMaterialDepotRepositoryOutputPort;
import com.dap.warehouse.util.Constants;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ManagementServiceImplement implements IManagementServiceInputPort {
	
	@Autowired
	private IManagementRepositoryOutputPort iManagementRepositoryOutputPort;
	
	@Autowired
	private ManagementMapper managementMapper;

	@Autowired
	private IManagementMaterialRepositoryOutputPort iManagementMaterialRepositoryOutputPort;

	@Autowired
	private IMaterialDepotRepositoryOutputPort iMaterialDepotRepositoryOutputPort;

	@Autowired
	private ILogRepositoryOutputPort iLogRepositoryOutputPort;
	
    public ResponseEntity<List<Management>> findAll(){
    	
    	var sortById = Sort.by("idMaterialManagement");
    	ResponseEntity<List<Management>> response = null;
    	try {
			List<Management> managementList = this.iManagementRepositoryOutputPort.findAll(sortById);
			if (!managementList.isEmpty()) {
				response = new ResponseEntity<>(managementList, HttpStatus.OK);
						} else {
				response = new ResponseEntity<>(managementList, HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			return response;
		}
    	return response;
    }
    

	@Override
	public ResponseEntity<Management> findById(Integer id) {
		
		ResponseEntity<Management> response = null;
		try {
			Optional<Management> optionalMaterial = iManagementRepositoryOutputPort.findById(id);
            response = optionalMaterial.map(material -> new ResponseEntity<>(material, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
		} catch (Exception e) {
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			return response;
		}
		return response;
	}
	

	@Override
	@Transactional
	public ResponseEntity<Management> deleteById(Integer id) {
		
		ResponseEntity<Management> response = null;
		try {
			Optional<Management> optionalMaterial = iManagementRepositoryOutputPort.findById(id);
			if(optionalMaterial.isPresent()) {
				iManagementRepositoryOutputPort.delete(optionalMaterial.get());
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
	public ResponseEntity<ManagementModel> save(ManagementRequest managementRequest, String nameMethod) {

		ResponseEntity<ManagementModel> response;
		try {
			List<ManagementMaterial> materialList = managementRequest.getModelRequest().getMaterialList();
			var managementResponse = managementMapper.fromRequestToMapping(managementRequest.getModelRequest());
			managementResponse.setFolio(getSerialNumber());
			managementRequest.getModelRequest().setFolio(managementResponse.getFolio());
			var management = iManagementRepositoryOutputPort.save(managementResponse);
			addMaterialsToManagement(management, materialList);

			iLogRepositoryOutputPort.save(Log.builder()
					.date(LocalDate.now())
					.operation(nameMethod + " - > " + management)
					.entity(management.getIdMaterialManagement())
					.user(managementRequest.getUser())
					.table(Constants.LOG_TABLE_MANAGEMENT)
					.build());

			response = new ResponseEntity<>(managementRequest.getModelRequest(), HttpStatus.CREATED);
		} catch (Exception e) {
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			return response;	
		}
		return response;
	}

	@Transactional
	public void addMaterialsToManagement(Management management,
										 List<ManagementMaterial> materialList) {
		if(management != null && materialList != null) {
			for (ManagementMaterial material : materialList) {
				material.setManagement(management);
				updateStockMaterial(material, management.getIo());
				iManagementMaterialRepositoryOutputPort.save(material);
			}
		}
	}

	@Transactional
	public void updateStockMaterial(ManagementMaterial material, Boolean io) {
		if(material != null){
			MaterialDepot materialDepot = iMaterialDepotRepositoryOutputPort.
					findByUniqueFields(material.getMaterial(),
							material.getManagement().getDepot(),
							material.getStatus());
			if (io != null && io){
				materialDepot.setStock(materialDepot.getStock() + material.getQuantity());
			} else if(io != null && (materialDepot.getStock() - material.getQuantity()) >= 0){
					materialDepot.setStock(materialDepot.getStock() - material.getQuantity());
			}
			iMaterialDepotRepositoryOutputPort.save(materialDepot);
		}
	}

	@Transactional
	public String getSerialNumber(){
		String serialNumber;
		int lastid = 0;
		Management lastManagement = iManagementRepositoryOutputPort.findFirstByOrderByIdMaterialManagementDesc();
		if(lastManagement != null){
			lastid = lastManagement.getIdMaterialManagement() + 1;
		} else {
			lastid = lastid + 1;
		}
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyyMMdd");
		serialNumber = "MT-" + LocalDate.now().format(format) + lastid;
		return serialNumber;
	}

	public ResponseEntity<List<ManagementModel>> findReport(ReportRequest reportRequest){

		ResponseEntity<List<ManagementModel>> response = null;
		try {
			if(reportRequest.getStartDate() != null &&
			   reportRequest.getEndDate() != null &&
			   reportRequest.getDepot() == null &&
			   reportRequest.getArea() == null) {
				List<Management> managementList = iManagementRepositoryOutputPort.managementListByPeriod(reportRequest.getStartDate(), reportRequest.getEndDate());
				if (!managementList.isEmpty()) {
                    List<ManagementModel> managementModelList =  new ArrayList<>();
					for(Management management: managementList){
						ManagementModel managementModel = managementMapper.fromObjectToModel(management);
						managementModelList.add(managementModel);
					}
					response = new ResponseEntity<>(managementModelList, HttpStatus.OK);
				} else {
					response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
				}
			} else if(reportRequest.getStartDate() != null &&
					reportRequest.getEndDate() != null &&
					reportRequest.getDepot() != null &&
					reportRequest.getArea() == null){
				List<Management> managementList = iManagementRepositoryOutputPort.managementListByPeriodAndDepot(
						reportRequest.getStartDate(),
						reportRequest.getEndDate(),
						reportRequest.getDepot());
				if (!managementList.isEmpty()) {
					List<ManagementModel> managementModelList =  new ArrayList<>();
					for(Management management: managementList){
						ManagementModel managementModel = managementMapper.fromObjectToModel(management);
						managementModelList.add(managementModel);
					}
					response = new ResponseEntity<>(managementModelList, HttpStatus.OK);
				} else {
					response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
				}
			} else if(reportRequest.getStartDate() != null &&
					reportRequest.getEndDate() != null &&
					reportRequest.getDepot() != null){
				List<Management> managementList = iManagementRepositoryOutputPort.managementListByPeriodDepotAndArea(
						reportRequest.getStartDate(),
						reportRequest.getEndDate(),
						reportRequest.getDepot(),
						reportRequest.getArea());
				if (!managementList.isEmpty()) {
					List<ManagementModel> managementModelList =  new ArrayList<>();
					for(Management management: managementList){
						ManagementModel managementModel = managementMapper.fromObjectToModel(management);
						managementModelList.add(managementModel);
					}
					response = new ResponseEntity<>(managementModelList, HttpStatus.OK);
				} else {
					response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
				}
			} else {
				response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (Exception e) {
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			return response;
		}
		return response;
	}
}
