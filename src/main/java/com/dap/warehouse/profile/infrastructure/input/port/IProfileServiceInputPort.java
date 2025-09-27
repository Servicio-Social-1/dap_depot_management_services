package com.dap.warehouse.profile.infrastructure.input.port;

import com.dap.warehouse.profile.domain.api.ProfileRequest;
import com.dap.warehouse.profile.domain.model.Profile;
import org.springframework.http.ResponseEntity;
import java.util.List;

public interface IProfileServiceInputPort {
	
	ResponseEntity<List<Profile>> findAll();
	ResponseEntity<Profile> findById(Integer id);
	ResponseEntity<Profile> deleteById(Integer id);
	ResponseEntity<Profile> save(ProfileRequest accessRequest,String nameMethod);

}
