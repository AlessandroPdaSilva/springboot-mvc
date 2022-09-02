package curso.spring.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import curso.spring.model.Telefone;
import curso.spring.model.Usuario;

@Repository
@Transactional
public interface TelefoneRepository extends CrudRepository<Telefone, Long>{

	@Query("SELECT t FROM Telefone t WHERE t.usuario.id = ?1")
	public List<Telefone> pesquisarTelefoneByidUsuario(Long idUsuario);
}
