package atos.testAF.entities;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import atos.testAF.model.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Cette classe représente l'entité user
 * @author Souki
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@NotNull(message = "Last name is mandatory")
	private String lastName;

	@NotNull(message = "First name is mandatory")
	private String firstName;

	@NotNull(message = "country is mandatory")
	private String country;

	@NotNull(message = "Birthdate is mandatory")
	private LocalDate birthDate;

	@Enumerated(EnumType.STRING)
	private Gender gender;

	@Email(message = "Email format not valid")
	private String mail;

	private String phoneNumber;

}
