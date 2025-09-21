package log.domain.model;

import com.dap.warehouse.user.domain.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Table(name = "LOG", uniqueConstraints = @UniqueConstraint(columnNames = "PKI_LOG"))
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Log {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PKI_LOG")
	private Integer idLog;

	@Column(name = "FC_CREATED_DATE")
	private LocalDate date;

	@Column(name = "FC_OPERATION")
	private String operation;

	@Column(name = "FI_ENTITY")
	private Integer entity;

	@Column(name = "FI_USER")
	private User user;
}

