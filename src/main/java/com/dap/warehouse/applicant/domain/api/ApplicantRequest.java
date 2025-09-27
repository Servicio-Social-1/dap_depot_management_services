package com.dap.warehouse.applicant.domain.api;

import com.dap.warehouse.user.domain.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApplicantRequest {

	private ApplicantModel modelRequest;
	private User user;
}
