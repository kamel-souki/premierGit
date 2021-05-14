package atos.testAF.exception;

import atos.testAF.service.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;

/**
 * Classe qui représente les exceptions de type BadRequestException
 *
 */
@Slf4j
public class BadRequestException extends Exception {

	
	private static final long serialVersionUID = 1L;

	public BadRequestException() {
		super();
	}

	/**
	 * Ce constructeur crée une exception accompagnée un message 
	 * @param message : contient le message d'exception
	 */
	public BadRequestException(String message) {
		super(message);
		log.warn("Calling {}.UserException({}) : ", UserServiceImpl.class.getName(), message);
	}

}
