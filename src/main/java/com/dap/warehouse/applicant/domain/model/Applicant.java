package com.dap.warehouse.applicant.domain.model;

import com.dap.warehouse.area.domain.model.Area;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "APPLICANT", uniqueConstraints = @UniqueConstraint(columnNames = "PKI_APPLICANT"))
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Applicant {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PKI_APPLICANT")
	private Integer idApplicant;

	@Column(name = "FC_NAME")
	private String name;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "FKI_AREA", nullable = false)
	private Area area;

	@Column(name = "FI_ACTIVE")
	private Boolean active;
}

