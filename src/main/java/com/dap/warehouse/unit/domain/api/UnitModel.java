package com.dap.warehouse.unit.domain.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UnitModel {

	private Integer idUnit;

	private String name;

	private Boolean active;

}
