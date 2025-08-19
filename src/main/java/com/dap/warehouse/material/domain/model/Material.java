package com.dap.warehouse.material.domain.model;

import com.dap.warehouse.unit.domain.model.Unit;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "MATERIAL", uniqueConstraints = @UniqueConstraint(columnNames = "PKI_MATERIAL"))
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Material {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PKI_MATERIAL")
	private Integer idMaterial;

	@Column(name = "FC_SERIAL_NUMBER")
	private String serialNumber;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "FKI_UNIT", nullable = false)
	private Unit unit;

	@Column(name = "FC_DESCRIPTION")
	private String description;

	@Column(name = "FI_ACTIVE")
	private Boolean active;
}

