package com.dap.warehouse.managementmaterial.domain.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ManagementMaterialRequest {

	private ManagementMaterialModel modelRequest;
}
