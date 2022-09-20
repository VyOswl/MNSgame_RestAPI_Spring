package edu.mns.game;

import edu.mns.game.dao.QuestionDao;
import edu.mns.game.dao.UserDao;
import edu.mns.game.model.Question;
import edu.mns.game.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UnitTests {

	@Autowired
	private WebApplicationContext context;

	//faux mvc
	private MockMvc mvc;

	@Autowired
	private QuestionDao questionDao;

	@Autowired
	private UserDao userDao;

	//initialisation du mvc, min 46
	@BeforeEach
	private void initialisation() {
		mvc = MockMvcBuilders
				.webAppContextSetup(context)
				.apply(springSecurity())
				.build();
	}

	/*----------USER-----------*/
	//vérifie que lors de la création d'un user, la liste de rôle soit bien initialée
	//ce qu'on regarde (liste role) _ ce qu'on attend (initialisée)
	@Test
	void userRoleList_initialize() {
		User user = new User();
		assertDoesNotThrow(() -> user.getRoleList().size());
	}

	@Test
	@Rollback(false)
	public void testUpdateUser() {
		User user = userDao.findByEmail("vy@mail.com");
		user.setEmail("vy@mail.fr");
		userDao.save(user);
		User updateUser = userDao.findByEmail("vy@mail.fr");
		assertThat(updateUser.getEmail()).isEqualTo("vy@mail.fr");
	}

	/*----------QUESTION-----------*/
	@Test
	@Rollback(false)
	public void testCreateQuestion() {
		Question savedQuestion = questionDao.save(new Question("Junit 5 est ...",5f));
		assertThat(savedQuestion.getId()).isGreaterThan(0);
	}

	/*----------SCENARIO-----------*/
	@Test
	@WithMockUser(username ="doe", roles = {"USER"})
	void simpleUserGetListScenario_response403Forbidden() throws Exception {
		mvc.perform(get("/creator/list-scenario")).andExpect(status().isForbidden());
	}

	@Test
	@WithMockUser(username ="vyphan", roles = {"ADMIN"})
	void adminGetListScenario_response200Ok() throws Exception {
		mvc.perform(get("/creator/list-scenario")).andExpect(status().isOk());
	}

}