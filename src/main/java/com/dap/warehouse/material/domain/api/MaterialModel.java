package com.dap.warehouse.material.domain.api;

import com.dap.warehouse.depot.domain.model.Depot;
import com.dap.warehouse.unit.domain.model.Unit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MaterialModel {

	private Integer idMaterial;

	private String serialNumber;

	private Unit unit;

	private String description;

	private Boolean active;

	private List<Depot> depotList;

}
