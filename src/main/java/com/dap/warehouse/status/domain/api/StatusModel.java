package com.dap.warehouse.status.domain.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StatusModel {

	private Integer idStatus;

	private String status;

	private Boolean active;

}
