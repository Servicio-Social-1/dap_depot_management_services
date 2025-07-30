package com.dap.warehouse.materialdepot.domain.model;

import com.dap.warehouse.depot.domain.model.Depot;
import com.dap.warehouse.material.domain.model.Material;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "MATERIAL_DEPOT", uniqueConstraints = @UniqueConstraint(columnNames = "PKI_MATERIAL_DEPOT"))
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MaterialDepot {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PKI_MATERIAL_DEPOT")
	private Integer idMaterialDepot;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "FKI_DEPOT", nullable = false)
	private Depot depot;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "FKI_MATERIAL", nullable = false)
	private Material material;

	@Column(name = "FN_STOCK")
	private Integer stock;
}

