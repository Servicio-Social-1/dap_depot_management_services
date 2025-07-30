package com.dap.warehouse.materialdepot.domain.api;

import com.dap.warehouse.depot.domain.model.Depot;
import com.dap.warehouse.material.domain.model.Material;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MaterialDepotModel {

	private Integer idMaterialDepot;

	private Depot depot;

	private Material material;

	private Integer stock;

}
