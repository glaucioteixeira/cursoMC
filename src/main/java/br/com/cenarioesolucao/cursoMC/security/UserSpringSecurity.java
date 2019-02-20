package br.com.cenarioesolucao.cursoMC.security;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.cenarioesolucao.cursoMC.domains.enums.TipoPerfil;

public class UserSpringSecurity implements UserDetails {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Atributos
	 */
	private Integer id;
	private String email;
	private String senha;
	private Collection<? extends GrantedAuthority> authorities;
	
	

	/**
	 * Construtores
	 * @param id
	 * @param email
	 * @param senha
	 * @param authorities
	 */
	public UserSpringSecurity(Integer id, String email, String senha,
			Set<TipoPerfil> perfis) {
		super();
		this.id = id;
		this.email = email;
		this.senha = senha;
		this.authorities = perfis.stream().map(x -> new SimpleGrantedAuthority(x.getDescricao())).collect(Collectors.toList());
	}
	public UserSpringSecurity() {
		
	}


	/**
	 * Getters
	 * @return
	 */
	public Integer getId() {
		return id;
	}
	
	public boolean hasRole(TipoPerfil tipoPerfil) {
		return getAuthorities().contains(new SimpleGrantedAuthority(tipoPerfil.getDescricao()));
	}

	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return authorities;
	}

	@Override
	public String getPassword() {
		
		return senha;
	}

	@Override
	public String getUsername() {
		
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		
		return true;
	}

	@Override
	public boolean isEnabled() {
		
		return true;
	}

}
