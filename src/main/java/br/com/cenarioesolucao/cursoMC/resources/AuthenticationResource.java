package br.com.cenarioesolucao.cursoMC.resources;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.cenarioesolucao.cursoMC.DTO.EmailDTO;
import br.com.cenarioesolucao.cursoMC.security.JWTUtil;
import br.com.cenarioesolucao.cursoMC.security.UserSpringSecurity;
import br.com.cenarioesolucao.cursoMC.services.AuthenticationService;
import br.com.cenarioesolucao.cursoMC.services.UserService;

@RestController
@RequestMapping(value = "/authentication")
public class AuthenticationResource {

	@Autowired
	private JWTUtil jwtUtil;
	
	@Autowired 
	private AuthenticationService authenticationService;
	
	@RequestMapping(value = "/refresh_token", method = RequestMethod.POST)
	public ResponseEntity<Void> refreshToken(HttpServletResponse response) {
		UserSpringSecurity userSpringSecurity = UserService.authenticated();
		String token = jwtUtil.generateToken(userSpringSecurity.getUsername());
		response.addHeader("Authorization", "Bearer " + token);
		response.addHeader("access-control-expose-headers", "Authorization");
		
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/forgot", method = RequestMethod.POST)
	public ResponseEntity<Void> forgot(@Valid @RequestBody EmailDTO emailDTO) {
		authenticationService.sendNewPassword(emailDTO.getEmail());
		
		return ResponseEntity.noContent().build();
	}

}
