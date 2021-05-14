package atos.testAF.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import atos.testAF.entities.User;
import atos.testAF.exception.BadRequestException;
import atos.testAF.repo.UserRepository;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;


/**
 * Cette classe implémente l'interface "UserServiceInterface" et fournit les services suivants :
 * <ul><li>Ajouter un utilisateur</li><li>Fournit les informations d'un utilisateur</li></ul>
 * @author Kamel Souki 
 */
@Slf4j
@Service
public class UserServiceImpl implements UserServiceInterface {

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserAccountValidation userAccountValidation;

	/**
	 * Ajoute un utilisateur 
	 * @return  Un objet de type User
	 */
	@Override
	public User addUser(User user) throws BadRequestException {
		log.info("Calling {}.addUser({}) : ", UserServiceImpl.class.getName(), user);
		if (userAccountValidation.checkAge(user.getBirthDate())
				&& userAccountValidation.checkCountry(user.getCountry())) {
			log.info("User validated ! Executing UserRepository.save({})", user);
			userRepository.save(user);
			log.info("UserRepository.save() Result {}", user);
		}
		return user;
	}

	/**
	 * Affiche les détails de un ou plusieurs utilisateur 
	 * @return  Une liste d'objet de type User
	 */
	@Override
	public List<User> displayUser(String lastName, String firstName) throws NotFoundException  {
		log.info("Calling {}.displayUser({} {}) : ", UserServiceImpl.class.getName(), lastName, firstName);
		List<User> resultList = firstName.isEmpty() ? userRepository.findUserByLastName(lastName) : userRepository.findUserByLastNameAndFirstName(lastName, firstName);
		if(resultList.size()==0) {
			log.info("Aucun utilisateur ne correspond à vos paramètres de recherche");
			throw new NotFoundException("Aucun utilisateur ne correspond à vos paramètres de recherche");
		}
		log.info("UserRepository.displayUser() Result{}", resultList);
		return resultList;
	}

}
