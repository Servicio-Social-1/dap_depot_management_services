package com.dap.warehouse.area.domain.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AreaModel {

	private Integer idArea;

	private String name;

	private String contract;

	private Boolean active;

}
