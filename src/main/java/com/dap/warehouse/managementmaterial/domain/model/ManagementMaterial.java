package com.dap.warehouse.managementmaterial.domain.model;

import com.dap.warehouse.management.domain.model.Management;
import com.dap.warehouse.material.domain.model.Material;
import com.dap.warehouse.status.domain.model.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "MATERIAL_MANAGEMENT_MATERIAL", uniqueConstraints = @UniqueConstraint(columnNames = "PKI_MATERIAL_MANAGEMENT_MATERIAL"))
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ManagementMaterial {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PKI_MATERIAL_MANAGEMENT_MATERIAL")
	private Integer idMaterialManagementMaterial;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "FKI_MATERIAL_MANAGEMENT", nullable = false)
	private Management management;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "FKI_MATERIAL", nullable = false)
	private Material material;

	@Column(name = "FN_QUANTITY")
	private Integer quantity;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "FKI_STATUS", nullable = false)
	private Status status;
}

