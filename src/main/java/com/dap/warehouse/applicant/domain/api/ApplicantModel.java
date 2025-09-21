package com.dap.warehouse.applicant.domain.api;

import com.dap.warehouse.area.domain.model.Area;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApplicantModel {

	private Integer idApplicant;

	private String name;

	private String contract;

	private Area area;

	private Boolean active;

}
