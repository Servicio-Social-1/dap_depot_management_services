package com.dap.warehouse.materialdepot.domain.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MaterialDepotRequest {

	private MaterialDepotModel modelRequest;
}
