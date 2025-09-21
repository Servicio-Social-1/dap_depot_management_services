package log.infrastructure.input.port;

import log.domain.api.LogRequest;
import log.domain.model.Log;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ILogServiceInputPort {
	
	ResponseEntity<List<Log>> findAll();
	ResponseEntity<Log> findById(Integer id);
	ResponseEntity<Log> deleteById(Integer id);
	ResponseEntity<Log> save(LogRequest accessRequest);

}
