package com.dap.warehouse.area.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "AREA", uniqueConstraints = @UniqueConstraint(columnNames = "PKI_AREA"))
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Area {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PKI_AREA")
	private Integer idArea;

	@Column(name = "FC_NAME")
	private String name;

	@Column(name = "FC_CONTRACT")
	private String contract;

	@Column(name = "FI_ACTIVE")
	private Boolean active;
}

