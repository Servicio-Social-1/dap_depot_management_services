package com.dap.warehouse.management.domain.service;

import com.dap.warehouse.management.domain.api.ManagementModel;
import com.dap.warehouse.management.domain.model.Management;
import com.dap.warehouse.managementmaterial.domain.model.ManagementMaterial;
import com.dap.warehouse.managementmaterial.infrastructure.output.port.IManagementMaterialRepositoryOutputPort;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ManagementMapper {

	@Autowired
	private IManagementMaterialRepositoryOutputPort iManagementMaterialRepositoryOutputPort;
	
	public Management fromRequestToMapping(ManagementModel modelRequest) {
		var modelMapper = new ModelMapper();
		return modelMapper.map(modelRequest, Management.class);
	}

	public ManagementModel fromObjectToModel(Management management) {

		List<ManagementMaterial> managementMaterialList = iManagementMaterialRepositoryOutputPort.findByManagement(management);

		return ManagementModel.builder()
				.idMaterialManagement(management.getIdMaterialManagement())
				.folio(management.getFolio())
				.userDispatcher(management.getUserDispatcher())
				.date(management.getDate())
				.comment(management.getComment())
				.userReceiver(management.getUserReceiver())
				.depot(management.getDepot())
				.io(management.getIo())
				.materialList(managementMaterialList)
				.applicantDocument(management.getApplicantDocument())
				.serviceBoos(management.getServiceBoos())
				.jud(management.getJud())
				.build();
	}

}
