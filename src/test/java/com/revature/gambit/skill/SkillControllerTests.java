package com.revature.gambit.skill;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.google.gson.Gson;
import com.revature.gambit.skill.beans.Skill;
import com.revature.gambit.skill.controllers.SkillController;
import com.revature.gambit.skill.services.SkillServiceImpl;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SkillControllerTests {

	private MockMvc mvc;

	@InjectMocks
	private SkillController skillController;

	@Mock
	private SkillServiceImpl skillServiceImpl;


	@Before
	public void setUp() {
		mvc = MockMvcBuilders.standaloneSetup(skillController).build();
	}
	
	@Test
	public void testDeleteSkillFunction() throws Exception {
		Skill skill1 = new Skill(100, "Java", true);
		when(skillServiceImpl.findById(100)).thenReturn(skill1);
		when(skillServiceImpl.deleteBySkillID(100)).thenReturn(true);
	    mvc.perform(MockMvcRequestBuilders.delete("/skill/{id}", 100)
	             .accept(MediaType.APPLICATION_JSON))
	             .andExpect(status().isAccepted());
	}

}
