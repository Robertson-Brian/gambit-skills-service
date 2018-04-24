package com.revature.gambit.skill;

import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.revature.gambit.skill.beans.Skill;
import com.revature.gambit.skill.controllers.SkillController;
import com.revature.gambit.skill.repo.SkillRepository;
import com.revature.gambit.skill.services.SkillService;

@RunWith(SpringJUnit4ClassRunner.class)
public class SkillControllerTests {

	private MockMvc mvc;

	@InjectMocks
	private SkillController skillController;

	@Mock
	private SkillService skillService;
	
	@Mock
	private SkillRepository sr;

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
	
	
	@Test
	public void testDeleteSkillFunction() throws Exception {
		Skill skill1 = new Skill(100, "Java", true);
		when((skillService).findByName("Java")).thenReturn(skill1);
		when((skillService).deleteSkillViaName("Java")).thenReturn(true);
	    mvc.perform(MockMvcRequestBuilders.delete("/skill/{name}", "Java")
	             .accept(MediaType.APPLICATION_JSON))
	             .andExpect(status().isAccepted());
	}
}
