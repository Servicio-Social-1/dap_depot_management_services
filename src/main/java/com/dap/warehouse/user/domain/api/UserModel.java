package com.dap.warehouse.user.domain.api;

import com.dap.warehouse.area.domain.model.Area;
import com.dap.warehouse.depot.domain.model.Depot;
import com.dap.warehouse.profile.domain.model.Profile;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

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

	private Area area;

	private List<Depot> depotList;

	private Boolean active;

	private String contract;

}
