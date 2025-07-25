package com.dap.warehouse.status.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "STATUS_MATERIAL", uniqueConstraints = @UniqueConstraint(columnNames = "PKI_STATUS_MATERIAL"))
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Status {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PKI_STATUS_MATERIAL")
	private Integer idStatus;

	@Column(name = "FC_STATUS")
	private String status;

	@Column(name = "FI_ACTIVE")
	private Boolean active;
}

