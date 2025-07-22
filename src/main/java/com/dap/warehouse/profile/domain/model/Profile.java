package com.dap.warehouse.profile.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "USER_PROFILE", uniqueConstraints = @UniqueConstraint(columnNames = "PKI_USER_PROFILE"))
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Profile {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PKI_USER_PROFILE")
	private Integer idProfile;

	@Column(name = "FC_PROFILE")
	private String profile;

	@Column(name = "FI_ACTIVE")
	private Boolean active;
}

