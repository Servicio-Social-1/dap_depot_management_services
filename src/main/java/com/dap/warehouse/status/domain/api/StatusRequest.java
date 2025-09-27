package com.dap.warehouse.status.domain.api;

import com.dap.warehouse.user.domain.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StatusRequest {

	private StatusModel modelRequest;
	private User user;
}
