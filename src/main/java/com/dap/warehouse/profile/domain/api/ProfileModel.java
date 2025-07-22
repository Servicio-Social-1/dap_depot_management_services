package com.dap.warehouse.profile.domain.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProfileModel {

	private Integer idProfile;

	private String profile;

	private Boolean active;

}
