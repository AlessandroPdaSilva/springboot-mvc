package curso.spring.security;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import curso.spring.model.Usuario;
import curso.spring.repository.UsuarioRepository;

@Service
@Transactional
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService{

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Usuario u = usuarioRepository.findUsuarioByLogin(username);
		
		if(u == null) {
			throw new UsernameNotFoundException("Usuario não encontrado");
		}
		
		return new User(u.getLogin(), u.getSenha(), u.getAuthorities());
	}

}
