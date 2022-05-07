package transacao.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import transacao.Models.Usuario;

@Repository
public interface RepositoryUser extends JpaRepository<Usuario, Long> {

	Usuario findByUsername(String username);

	Usuario findByEmail(String email);
	
	@Query("SELECT u FROM Usuario u WHERE u.username = :username AND u.id != :id")
	Usuario findByUsernameDifferentOfId(String username, Long id);
	
	@Query("SELECT u FROM Usuario u WHERE u.email = :email AND u.id != :id")
	Usuario findByEmailDifferentOfId(String email, Long id);

	@Query("SELECT u FROM Usuario u WHERE u.id != 1 AND u.username != :name")
	List<Usuario> findAllWithoutPatternAndLogged(String name);


	
}
