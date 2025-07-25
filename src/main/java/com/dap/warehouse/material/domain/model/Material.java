package com.dap.warehouse.material.domain.model;

import com.dap.warehouse.depot.domain.model.Depot;
import com.dap.warehouse.unit.domain.model.Unit;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "MATERIAL_TYPE", uniqueConstraints = @UniqueConstraint(columnNames = "PKI_MATERIAL_TYPE"))
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Material {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PKI_MATERIAL_TYPE")
	private Integer idMaterial;

	@Column(name = "FN_SERIAL_NUMBER")
	private Integer serialNUmber;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "FKI_UNIT", nullable = false)
	private Unit unit;

	@Column(name = "FC_DESCRIPTION")
	private String description;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "FKI_DEPOT", nullable = false)
	private Depot depot;

	@Column(name = "FI_INDIVIDUAL")
	private Boolean individual;

	@Column(name = "FI_ACTIVE")
	private Boolean active;
}

