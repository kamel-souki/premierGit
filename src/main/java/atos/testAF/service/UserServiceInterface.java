package atos.testAF.service;

import java.util.List;
import atos.testAF.entities.User;


/**
 * Déclare les services suivants : 
 * <ul><li>Ajouter un utilisateur</li><li>Fournit les informations d'un utilisateur</li></ul>
 * @author Kamel Souki
 */
public interface UserServiceInterface {
	
	public User addUser(User user) throws Exception;
	public List<User> displayUser(String lastName, String firstName) throws Exception;

}
