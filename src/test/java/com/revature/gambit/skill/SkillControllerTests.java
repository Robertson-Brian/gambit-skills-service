package com.revature.gambit.skill;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.revature.gambit.skill.beans.Skill;
import com.revature.gambit.skill.controllers.SkillController;
import com.revature.gambit.skill.services.SkillService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SkillControllerTests {

	private MockMvc mvc;

	@InjectMocks
	private SkillController skillController;

	@Mock
	private SkillService skillService;

	@Before
	public void setUp() throws Exception {
		mvc = MockMvcBuilders.standaloneSetup(skillController).build();
	}
	
	@Test
	public void getSkillType() throws Exception{

		Skill skill1 = new Skill(100, "Java", true);
		Skill skill2 = new Skill(101, "Fortran", true);

		Iterable<Skill> skills = Arrays.asList(skill1,skill2);

		when(skillService.findAll()).thenReturn((Iterable<Skill>) skills);
	 
		mvc.perform(MockMvcRequestBuilders.get("/skill")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

	}
}
