package curso.spring.model;

import javax.persistence.Entity;

import org.springframework.security.core.GrantedAuthority;

@Entity
public class Role implements GrantedAuthority{
	 
	private static final long serialVersionUID = 1L;
	
	private String nomeRole;
	
	@Override
	public String getAuthority() {// ex: ROLE_ADMIN, ROLE_GERENTE e etc
		
		return this.nomeRole;
	}

	// GET E SET
	public String getNomeRole() {
		return nomeRole;
	}

	public void setNomeRole(String nomeRole) {
		this.nomeRole = nomeRole;
	}
	
	

}
