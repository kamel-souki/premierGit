package atos.testAF.exception;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import atos.testAF.model.ErrorUserResponse;
import javassist.NotFoundException;

/**
 * Classe qui intercepte et gére 3 classes d'exceptions
 */
@ControllerAdvice
public class ExceptionHandlerController {

	/**
	 * Cette méthode gére les exceptions de type BadRequestException
	 * 
	 * @param ex : L'exception à gérer
	 * @return Liste d'objet de type ErrorUserResponse qui fournissent les détails
	 *         de l'exception
	 */
	@ExceptionHandler(BadRequestException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public List<ErrorUserResponse> handleBadRequestException(BadRequestException ex) {
		ErrorUserResponse errorUserResponse = new ErrorUserResponse(HttpStatus.BAD_REQUEST,
				HttpStatus.BAD_REQUEST.value(), ex.getMessage());
		return Arrays.asList(errorUserResponse);
	}

	/**
	 * Cette méthode gére les exceptions de type NotFoundException
	 * 
	 * @param ex : L'exception à gérer
	 * @return Liste d'objet de type ErrorUserResponse qui fournissent les détails
	 *         de l'exception
	 */
	@ExceptionHandler(NotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public List<ErrorUserResponse> handleBadRequestException(NotFoundException ex) {
		ErrorUserResponse errorUserResponse = new ErrorUserResponse(HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value(),
				ex.getMessage());
		return Arrays.asList(errorUserResponse);
	}

	/**
	 * Cette méthode gére les exceptions de type MethodArgumentNotValidException
	 * 
	 * @param ex : L'exception à gérer
	 * @return Liste d'objet de type ErrorUserResponse qui fournissent les détails
	 *         de l'exception
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public List<ErrorUserResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
		List<ErrorUserResponse> errorList = new ArrayList<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String errorMessage = error.getDefaultMessage();
			ErrorUserResponse errorUserResponse = new ErrorUserResponse(HttpStatus.BAD_REQUEST,
					HttpStatus.BAD_REQUEST.value(), errorMessage);
			errorList.add(errorUserResponse);
		});
		return errorList;
	}
}
