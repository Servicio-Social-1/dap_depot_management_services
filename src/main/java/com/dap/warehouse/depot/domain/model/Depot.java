package com.dap.warehouse.depot.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "DEPOT", uniqueConstraints = @UniqueConstraint(columnNames = "PKI_DEPOT"))
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Depot {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PKI_DEPOT")
	private Integer idDepot;

	@Column(name = "FC_NAME")
	private String name;

	@Column(name = "FC_LOCATION")
	private String location;

	@Column(name = "FC_KEY")
	private String key;

	@Column(name = "FI_ACTIVE")
	private Boolean active;
}

