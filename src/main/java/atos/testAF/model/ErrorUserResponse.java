package atos.testAF.model;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Classe qui crée un objet personnalisé par 3 attributs. <br>
 * Cet objet sera envoyé à l'utilisateur en cas d'exception intercepté lors des appels des webs services
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorUserResponse {

	private HttpStatus status;
	private int error;
	private String message;

}
