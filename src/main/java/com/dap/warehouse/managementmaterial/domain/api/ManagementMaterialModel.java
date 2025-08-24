package com.dap.warehouse.managementmaterial.domain.api;

import com.dap.warehouse.management.domain.model.Management;
import com.dap.warehouse.material.domain.model.Material;
import com.dap.warehouse.status.domain.model.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ManagementMaterialModel {

	private Integer idMaterialManagementMaterial;

	private Management management;

	private Material material;

	private Integer quantity;

	private Status status;
}
