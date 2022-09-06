package curso.spring.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import curso.spring.model.Usuario;

@Repository
@Transactional
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	@Query("SELECT u FROM Usuario u WHERE u.nome LIKE %?1%")
	public List<Usuario> pesquisarByNome(String nome);
	
	@Query("SELECT u FROM Usuario u WHERE u.login = ?1")
	public Usuario findUsuarioByLogin(String login);
	
	default Page<Usuario> pesquisarByNome(String nome, Pageable pageable) {
			
			
			// configuracao para procurar pelo nome, LIKE do sql
			ExampleMatcher ignoringExampleMatcher = ExampleMatcher.matchingAny()
				      .withMatcher("nome", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());
			
			
			Usuario u = new Usuario();
			u.setNome(nome);
			
			// Unir Configuracao e Objeto para a query
			Example<Usuario> example = Example.of( u , ignoringExampleMatcher);
			
			// lista paginada
			Page<Usuario> listaUsuario = findAll(example, pageable);
			
			return listaUsuario;
			
		}
	
}
