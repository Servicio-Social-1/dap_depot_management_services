package log.infrastructure.output.port;

import log.domain.model.Log;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ILogRepositoryOutputPort extends JpaRepository<Log, Integer>{

}
