package com.dap.warehouse.unit.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "UNIT", uniqueConstraints = @UniqueConstraint(columnNames = "PKI_UNIT"))
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Unit {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PKI_UNIT")
	private Integer idUnit;

	@Column(name = "FC_NAME")
	private String name;

	@Column(name = "FI_ACTIVE")
	private Boolean active;
}

