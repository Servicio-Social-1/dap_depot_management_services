package com.dap.warehouse.access.application;

import com.dap.warehouse.access.domain.api.AccessRequest;
import com.dap.warehouse.access.domain.model.Access;
import com.dap.warehouse.access.infrastructure.input.port.IAccessServiceInputPort;
import com.dap.warehouse.area.infrastructure.output.port.IAreaRepositoryOutputPort;
import com.dap.warehouse.user.domain.model.User;
import com.dap.warehouse.user.infrastructure.output.port.IUserRepositoryOutputPort;
import com.dap.warehouse.util.JWT;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccessServiceImplement implements IAccessServiceInputPort {

	@Autowired
	private IAreaRepositoryOutputPort iAreaRepositoryOutputPort;

	@Autowired
	private IUserRepositoryOutputPort iUserRepositoryOutputPort;

	private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);

	public String hashPassword(String key) {
		return passwordEncoder.encode(key);
	}

	public boolean verifyPassword(String key, String hashedKey) {
		return passwordEncoder.matches(key, hashedKey);
	}

	@Override
	@Transactional
	public ResponseEntity<Access> validateAccess(AccessRequest accessRequest) {
		ResponseEntity<Access> response;
		try {
			User user = iUserRepositoryOutputPort.findByMail(accessRequest.getMail());
			if(user != null) {
				if(verifyPassword(accessRequest.getKey(),user.getPassword())) {

					String token = JWT.generateToken(user.getName());

					Access access = new Access();
					access.setUser(user);
					access.setToken(token);

					response = new ResponseEntity<>(access, HttpStatus.OK);
				} else {
					response = new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
				}
			} else {
				response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			return response;	
		}
		return response;
	}

}
