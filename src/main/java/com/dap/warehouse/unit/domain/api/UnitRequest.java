package com.dap.warehouse.unit.domain.api;

import com.dap.warehouse.user.domain.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UnitRequest {

	private UnitModel modelRequest;
	private User user;
}
