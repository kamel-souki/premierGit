package atos.testAF.web;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import atos.testAF.entities.User;
import atos.testAF.service.TrackExecutionTime;
import atos.testAF.service.UserServiceInterface;
import lombok.extern.slf4j.Slf4j;


/**
 * Cette classe est un controlleur REST qui propose deux web services : 
 * <ul><li>addUser : Ajouter un utilisateur</li><li>displayUser : Fournit les informations d'un utilisateur</li></ul>
 * @author souki
 */
@Slf4j
@RestController
public class UserController {

	@Autowired
	UserServiceInterface userServiceInterface;

	/**
	 * Web service qui ajoute un utilisateur dans la BDD
	 * @return Un objet de type User  
	 */
	@TrackExecutionTime
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(path = "/user", consumes = { "application/json" })
	public User addUser(@Valid @RequestBody User user) throws Exception {
		log.info("Calling {}.addUser({}) : ", UserController.class.getName(), user);
		return userServiceInterface.addUser(user);
	}

	/**
	 * Web service qui affiche les détails d'un utilisateur.
	 * @return Une liste d'objet de type User.
	 */
	@TrackExecutionTime
	@GetMapping(path = "/user/{lastName}", produces = { "application/json" })
	public List<User> displayUser(@PathVariable String lastName,
			@RequestParam(value = "firstName", required = false, defaultValue = "") String firstName) throws Exception {
		log.info("Calling {}.displayUser({} {}) : ", UserController.class.getName(), lastName, firstName);
		return userServiceInterface.displayUser(lastName, firstName);
	}
}
