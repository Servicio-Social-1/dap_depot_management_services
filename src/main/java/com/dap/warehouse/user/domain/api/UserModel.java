package com.dap.warehouse.user.domain.api;

import com.dap.warehouse.depot.domain.model.Depot;
import com.dap.warehouse.profile.domain.model.Profile;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserModel {

	private Integer idUser;

	private String mail;

	private String name;

	private String password;

	private Profile profile;

	private Depot depot;

	private Boolean active;

}
