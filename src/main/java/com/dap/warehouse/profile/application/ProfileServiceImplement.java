package com.dap.warehouse.profile.application;

import com.dap.warehouse.log.domain.model.Log;
import com.dap.warehouse.log.infrastructure.output.port.ILogRepositoryOutputPort;
import com.dap.warehouse.profile.domain.api.ProfileRequest;
import com.dap.warehouse.profile.domain.model.Profile;
import com.dap.warehouse.profile.domain.service.ProfileMapper;
import com.dap.warehouse.profile.infrastructure.input.port.IProfileServiceInputPort;
import com.dap.warehouse.profile.infrastructure.output.port.IProfileRepositoryOutputPort;
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
public class ProfileServiceImplement implements IProfileServiceInputPort {
	
	@Autowired
	private IProfileRepositoryOutputPort iProfileRepositoryOutputPort;
	
	@Autowired
	private ProfileMapper profileMapper;

	@Autowired
	private ILogRepositoryOutputPort iLogRepositoryOutputPort;
	
    public ResponseEntity<List<Profile>> findAll(){
    	
    	var sortById = Sort.by("idProfile");
    	ResponseEntity<List<Profile>> response = null;
    	try {
			List<Profile> profileList = this.iProfileRepositoryOutputPort.findAll(sortById);
			if (!profileList.isEmpty()) {
				response = new ResponseEntity<>(profileList, HttpStatus.OK);
						} else {
				response = new ResponseEntity<>(profileList, HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			return response;
		}
    	return response;
    }
    

	@Override
	public ResponseEntity<Profile> findById(Integer id) {
		
		ResponseEntity<Profile> response = null;
		try {
			Optional<Profile> optionalProfile = iProfileRepositoryOutputPort.findById(id);
            response = optionalProfile.map(profile -> new ResponseEntity<>(profile, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
		} catch (Exception e) {
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			return response;
		}
		return response;
	}
	

	@Override
	@Transactional
	public ResponseEntity<Profile> deleteById(Integer id) {
		
		ResponseEntity<Profile> response = null;
		try {
			Optional<Profile> optionalProfile = iProfileRepositoryOutputPort.findById(id);
			if(optionalProfile.isPresent()) {
				iProfileRepositoryOutputPort.delete(optionalProfile.get());
				response = new ResponseEntity<>(optionalProfile.get(),HttpStatus.MOVED_PERMANENTLY);
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
	public ResponseEntity<Profile> save(ProfileRequest profileRequest, String nameMethod) {

		ResponseEntity<Profile> response;
		try {
			var profileResponse = profileMapper.fromRequestToMapping(profileRequest.getModelRequest());
			var profile = iProfileRepositoryOutputPort.save(profileResponse);

			iLogRepositoryOutputPort.save(Log.builder()
					.date(LocalDate.now())
					.operation(nameMethod + " - > " + profile)
					.entity(profile.getIdProfile())
					.user(profileRequest.getUser())
					.table(Constants.LOG_TABLE_PROFILE)
					.build());

			response = new ResponseEntity<>(profile, HttpStatus.CREATED);
			
		} catch (Exception e) {
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			return response;	
		}
		return response;
	}

}
