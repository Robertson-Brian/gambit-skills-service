package com.revature.gambit.skill;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

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
public class SkillControllerGetandPutTests {

	private MockMvc mvc;
	
	@InjectMocks
	private SkillController skillController;
	
	@Mock
	private SkillServiceImpl skillServiceImpl;
	
	@Before
	public void setUp() throws Exception {
		mvc = MockMvcBuilders.standaloneSetup(skillController).build();
	}
	
	@Test
	public void getSkillByName() throws Exception {
		Skill skill1 = new Skill(99, "Javas", true);
		Skill skill2 = new Skill(100, "Javas2", false);
		
		when(skillServiceImpl.findByName("Javas")).thenReturn(skill1);
        when(skillServiceImpl.findByName("Javas2")).thenReturn(skill2);
        
        mvc.perform(MockMvcRequestBuilders.get("/skill/name/{name}", "Javas")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
        
        mvc.perform(MockMvcRequestBuilders.get("/skill/name/{name}", "Javas2")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
	}
	
	@Test
	public void getSkillsById() throws Exception {
		Skill skill1 = new Skill(99, "Javas", true);
		Skill skill2 = new Skill(100, "Javas2", false);
		
		when(skillServiceImpl.findById(99)).thenReturn(skill1);
		when(skillServiceImpl.findById(100)).thenReturn(skill2);
		
		mvc.perform(MockMvcRequestBuilders.get("/skill/{id}", 99)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
		
		mvc.perform(MockMvcRequestBuilders.get("/skill/{id}", 100)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
	}
	

	
}
