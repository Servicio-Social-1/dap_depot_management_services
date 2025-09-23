package com.dap.warehouse.management.domain.api;

import com.dap.warehouse.applicant.domain.model.Applicant;
import com.dap.warehouse.depot.domain.model.Depot;
import com.dap.warehouse.managementmaterial.domain.model.ManagementMaterial;
import com.dap.warehouse.user.domain.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ManagementModel {

	private Integer idMaterialManagement;

	private String folio;

	private User userDispatcher;

	private LocalDate date;

	private String comment;

	private Applicant userReceiver;

	private Depot depot;

	private Boolean io;

	private List<ManagementMaterial> materialList;

	private String applicantDocument;

}
