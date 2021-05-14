package atos.testAF.testController;

import static org.junit.Assert.assertEquals;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import atos.testAF.entities.User;
import atos.testAF.exception.BadRequestException;
import atos.testAF.model.Gender;
import atos.testAF.service.UserServiceInterface;
import atos.testAF.web.UserController;
import javassist.NotFoundException;


/**
 * Classe de test sur les web services suivant
 * <ul><li>addUser</li><li>displayUser</li></ul>
 * @author souki
 */
@RunWith(SpringRunner.class)
@WebMvcTest(value = UserController.class)
@WithMockUser
public class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserServiceInterface userServiceInterface;

	LocalDate date = LocalDate.parse("2000-02-22");
	Gender gender = Gender.Male;
	User userTest = new User((long) 1, "myLastName", "myFirstName", "France", date, gender, "a@email", "");
	List<User> u = Arrays.asList(userTest);

	String userValidJson = "{\"lastName\":\"myLastName\",\"firstName\":\"myFirstName\",\"country\":\"France\",\"birthDate\":\"2000-02-22\",\"gender\":\"Male\",\"mail\":\"a@email\",\"phoneNumber\":\"\"}";
	String userNotValidJson = "{\"lastName\":\"myLastName\",\"firstName\":\"myFirstName\",\"country\":\"Allemagne\",\"birthDate\":\"2000-02-22\",\"gender\":\"Male\",\"mail\":\"a@email\",\"phoneNumber\":\"\"}";

	/** 
	 * Test GET request of displayUser when success (status = OK)
	 * @throws Exception
	 */
	@Test
	public void givenEmployees_whenGetUsers_thenStatus200() throws Exception {

		Mockito.when(userServiceInterface.displayUser(Mockito.anyString(), Mockito.anyString())).thenReturn(u);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/user/myLastName")
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expected = "[{\"id\":1,\"lastName\":\"myLastName\",\"firstName\":\"myFirstName\",\"country\":\"France\",\"birthDate\":\"2000-02-22\",\"gender\":\"Male\",\"mail\":\"a@email\",\"phoneNumber\":\"\"}]";

		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
		assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
	}

	/**
	 * Test GET request of displayUser when fail (status = NOT FOUND)
	 * @throws Exception
	 */
	@Test
	public void givenEmployees_whenNotGetUsers_thenStatus404() throws Exception {

		Mockito.when(userServiceInterface.displayUser(Mockito.anyString(), Mockito.anyString()))
				.thenThrow(new NotFoundException("Aucun utilisateur ne correspond a vos parametres de recherche"));

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/user/myLastName")
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		String expected = "[{\"status\":\"NOT_FOUND\",\"error\":404,\"message\":\"Aucun utilisateur ne correspond a vos parametres de recherche\"}]";

		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
		assertEquals(HttpStatus.NOT_FOUND.value(), result.getResponse().getStatus());
	}

	/**
	 * Test POST request of addUser when success (status = CREATED)
	 * @throws Exception
	 */
	@Test
	public void whenValidInput_thenCreateUser() throws Exception {

		Mockito.when(userServiceInterface.addUser(Mockito.any(User.class))).thenReturn(userTest);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/user").accept(MediaType.APPLICATION_JSON)
				.content(userValidJson).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expected = "{\"id\":1,\"lastName\":\"myLastName\",\"firstName\":\"myFirstName\",\"country\":\"France\",\"birthDate\":\"2000-02-22\",\"gender\":\"Male\",\"mail\":\"a@email\",\"phoneNumber\":\"\"}";

		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
		assertEquals(HttpStatus.CREATED.value(), result.getResponse().getStatus());

	}

	/**
	 * Test POST request of addUser when fail (status = BAD REQUEST)
	 * @throws Exception
	 */
	@Test
	public void whenNotValidInput_thenStatus400() throws Exception {

		Mockito.when(userServiceInterface.addUser(Mockito.any(User.class)))
				.thenThrow(new BadRequestException("Le pays doit etre France"));

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/user").accept(MediaType.APPLICATION_JSON)
				.content(userNotValidJson).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expected = "[{\"status\":\"BAD_REQUEST\",\"error\":400,\"message\":\"Le pays doit etre France\"}]";

		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
		assertEquals(HttpStatus.BAD_REQUEST.value(), result.getResponse().getStatus());

	}

}
