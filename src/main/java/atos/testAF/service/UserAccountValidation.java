package atos.testAF.service;

import java.time.LocalDate;
import java.time.Period;

import org.springframework.stereotype.Service;

import atos.testAF.exception.BadRequestException;
import lombok.extern.slf4j.Slf4j;


/**
 * Cette classe vérifie la validité des données après les appels REST 
 */
@Slf4j
@Service
public class UserAccountValidation {

	private static final String COUNTRY_NAME = "France";

	/**
	 * Cette méthode vérifie que l'âge utilisateur est supérieur à 18 ans
	 * @return Retourne TRUE si l'âge > 18 sinon FALSE 
	 */
	public boolean checkAge(LocalDate birthDate) throws BadRequestException {
		log.info("Calling {}.checkAge({}) : ", UserAccountValidation.class.getName(), birthDate);
		LocalDate currentDate = LocalDate.now();
		if ((birthDate != null) && (currentDate != null)) {
			if (Period.between(birthDate, currentDate).getYears() >= 18) {
				log.info("Age utilisateur validé !");
				return true;
			} else {
				log.info("L'âge de l'utilisateur est inférieur à 18 ans !");
				throw new BadRequestException("L'âge de l'utilisateur est inférieur à 18 ans !");
			}
		} else {
			log.info("La date de naissance est vide !");
			throw new BadRequestException("La date de naissance est vide !");
		}
	}

	/**
	 * Cette méthode vérifie que le pays de l'utilisateur est égale à "FRANCE"
	 * @return Retourne TRUE si pays = FRANCE sinon FALSE
	 */
	public boolean checkCountry(String country) throws BadRequestException {
		log.info("Calling {}.checkCountry({}) : ", UserAccountValidation.class.getName(), country);
		if (country.equalsIgnoreCase(COUNTRY_NAME)) {
			log.info("Pays utilisateur validé !");
			return true;
		} else {
			log.info("Le pays renseigné n'est pas France !");
			throw new BadRequestException("Le pays doit être France !");
		}
	}

}
