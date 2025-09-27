package com.dap.warehouse.depot.application;

import com.dap.warehouse.depot.domain.api.DepotRequest;
import com.dap.warehouse.depot.domain.model.Depot;
import com.dap.warehouse.depot.domain.service.DepotMapper;
import com.dap.warehouse.depot.infrastructure.input.port.IDepotServiceInputPort;
import com.dap.warehouse.depot.infrastructure.output.port.IDepotRepositoryOutputPort;
import com.dap.warehouse.log.domain.model.Log;
import com.dap.warehouse.log.infrastructure.output.port.ILogRepositoryOutputPort;
import com.dap.warehouse.util.Constants;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class DepotServiceImplement implements IDepotServiceInputPort {
	
	@Autowired
	private IDepotRepositoryOutputPort iDepotRepositoryOutputPort;
	
	@Autowired
	private DepotMapper depotMapper;

	@Autowired
	private ILogRepositoryOutputPort iLogRepositoryOutputPort;
	
    public ResponseEntity<List<Depot>> findAll(){
    	
    	var sortById = Sort.by("idDepot");
    	ResponseEntity<List<Depot>> response = null;
    	try {
			List<Depot> depotList = this.iDepotRepositoryOutputPort.findAll(sortById);
			if (!depotList.isEmpty()) {
				response = new ResponseEntity<>(depotList, HttpStatus.OK);
						} else {
				response = new ResponseEntity<>(depotList, HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			return response;
		}
    	return response;
    }
    

	@Override
	public ResponseEntity<Depot> findById(Integer id) {
		
		ResponseEntity<Depot> response = null;
		try {
			Optional<Depot> optionalDepot = iDepotRepositoryOutputPort.findById(id);
            response = optionalDepot.map(depot -> new ResponseEntity<>(depot, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
		} catch (Exception e) {
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			return response;
		}
		return response;
	}
	

	@Override
	@Transactional
	public ResponseEntity<Depot> deleteById(Integer id) {
		
		ResponseEntity<Depot> response = null;
		try {
			Optional<Depot> optionalDepot = iDepotRepositoryOutputPort.findById(id);
			if(optionalDepot.isPresent()) {
				iDepotRepositoryOutputPort.delete(optionalDepot.get());
				response = new ResponseEntity<>(optionalDepot.get(),HttpStatus.MOVED_PERMANENTLY);
			}else {
				response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			return response;
		}
		return response;
	}
	

	@Override
	@Transactional
	public ResponseEntity<Depot> save(DepotRequest depotRequest, String nameMethod) {

		ResponseEntity<Depot> response;
		try {
			var depotResponse = depotMapper.fromRequestToMapping(depotRequest.getModelRequest());
			var depot = iDepotRepositoryOutputPort.save(depotResponse);

			iLogRepositoryOutputPort.save(Log.builder()
					.date(LocalDate.now())
					.operation(nameMethod + " - > " + depot)
					.entity(depot.getIdDepot())
					.user(depotRequest.getUser())
					.table(Constants.LOG_TABLE_DEPOT)
					.build());

			response = new ResponseEntity<>(depot, HttpStatus.CREATED);
			
		} catch (Exception e) {
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			return response;	
		}
		return response;
	}

}
