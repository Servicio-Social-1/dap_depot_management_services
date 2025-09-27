package com.dap.warehouse.profile.domain.api;

import com.dap.warehouse.user.domain.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProfileRequest {

	private ProfileModel modelRequest;
	private User user;
}
