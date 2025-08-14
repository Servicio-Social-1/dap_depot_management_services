package com.dap.warehouse.depot.domain.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DepotModel {

	private Integer idDepot;

	private String name;

	private String location;

	private String key;

	private Boolean active;

}
