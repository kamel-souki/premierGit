package atos.testAF.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import atos.testAF.entities.User;

/**
 * @author souki
 * @apiNote Cette interface représente le repo de user. Hérite de CrudRepository.
 */

public interface UserRepository extends CrudRepository<User, Long>{
	
	/**
	 * Retourne les utilisateurs 
	 * @return Liste d'objet de type User dont le nom correspond à la recherche
	 */
	@Query("SELECT u FROM User u WHERE u.lastName = :ln ")
	public List<User> findUserByLastName(@Param("ln") String lastName);
	
	/**
	 * Retourne les utilisateurs 
	 * @return Liste d'objet de type User dont le nom et prénom correspondent à la recherche
	 */
	@Query("SELECT u FROM User u WHERE u.lastName = :ln AND u.firstName = :fn")
	public List<User> findUserByLastNameAndFirstName(@Param("ln") String lastName, @Param("fn") String firstName);
	
}
