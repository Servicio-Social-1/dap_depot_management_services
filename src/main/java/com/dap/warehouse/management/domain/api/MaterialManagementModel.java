package com.dap.warehouse.management.domain.api;

import com.dap.warehouse.depot.domain.model.Depot;
import com.dap.warehouse.user.domain.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MaterialManagementModel {

	private Integer idMaterialManagement;

	private String folio;

	private User userDispatcher;

	private LocalDate date;

	private String comment;

	private User userReceiver;

	private Depot depot;

}
