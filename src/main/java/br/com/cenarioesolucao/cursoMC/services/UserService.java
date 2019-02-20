package br.com.cenarioesolucao.cursoMC.services;

import org.springframework.security.core.context.SecurityContextHolder;

import br.com.cenarioesolucao.cursoMC.security.UserSpringSecurity;

public class UserService {

	public static UserSpringSecurity authenticated() {
		try {
			// Retorna o usuario logado
			return (UserSpringSecurity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			return null;
		}
	}
}
