package com.dap.warehouse.user.domain.model;

import com.dap.warehouse.area.domain.model.Area;
import com.dap.warehouse.depot.domain.model.Depot;
import com.dap.warehouse.profile.domain.model.Profile;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Table(name = "USER", uniqueConstraints = @UniqueConstraint(columnNames = "PKI_USER"))
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PKI_USER")
	private Integer idUser;

	@Column(name = "FC_MAIL")
	private String mail;

	@Column(name = "FC_NAME")
	private String name;

	@JsonIgnore
	@Column(name = "FC_PASSWORD")
	private String password;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "FKI_USER_PROFILE", nullable = false)
	private Profile profile;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "FKI_AREA", nullable = false)
	private Area area;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name = "USER_DEPOT",
			joinColumns = @JoinColumn(name = "FKI_USER"),
			inverseJoinColumns = @JoinColumn(name = "FKI_DEPOT")
	)
	private List<Depot> depotList;

	@Column(name = "FI_ACTIVE")
	private Boolean active;
}

