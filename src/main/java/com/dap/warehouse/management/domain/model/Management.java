package com.dap.warehouse.management.domain.model;

import com.dap.warehouse.applicant.domain.model.Applicant;
import com.dap.warehouse.depot.domain.model.Depot;
import com.dap.warehouse.user.domain.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Table(name = "MATERIAL_MANAGEMENT", uniqueConstraints = @UniqueConstraint(columnNames = "PKI_MATERIAL_MANAGEMENT"))
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Management {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PKI_MATERIAL_MANAGEMENT")
	private Integer idMaterialManagement;

	@Column(name = "FC_FOLIO")
	private String folio;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "FKI_USER_DISPATCHER", nullable = false)
	private User userDispatcher;

	@Column(name = "FD_DATE")
	private LocalDate date;

	@Column(name = "FC_COMMENT")
	private String comment;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "FKI_USER_RECEIVER", nullable = false)
	private Applicant userReceiver;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "FKI_DEPOT", nullable = false)
	private Depot depot;

	@Column(name = "FI_IO")
	private Boolean io;

	@Column(name = "FC_APPLICANT_DOCUMENT")
	private String applicantDocument;

	@Column(name = "FC_SERVICE_BOOS")
	private String serviceBoos;

	@Column(name = "FC_JUD")
	private String jud;
}

