package com.revature.gambit.skill;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.core.Application;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.google.gson.Gson;
import com.revature.gambit.skill.beans.Skill;
import com.revature.gambit.skill.controllers.SkillController;
import com.revature.gambit.skill.services.SkillService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SkillControllerGetandPutTests {

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
	public void getSkills() throws Exception {
		Skill skill1 = new Skill(99, "Javas", true);
		Skill skill2 = new Skill(100, "Javas2", false);
		Iterable<Skill> skills = Arrays.asList(skill1, skill2);
		
		when(skillService.findAllSkill()).thenReturn((List<Skill>) skills);
		
		mvc.perform(MockMvcRequestBuilders.get("/skill")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
	
	@Test
	public void getSkillByName() throws Exception {
		Skill skill1 = new Skill(99, "Javas", true);
		Skill skill2 = new Skill(100, "Javas2", false);
		Iterable<Skill> skills = Arrays.asList(skill1, skill2);
		
		when(skillService.findByName("Javas")).thenReturn(((List<Skill>) skills).get(0));
        when(skillService.findByName("Javas2")).thenReturn(((List<Skill>) skills).get(1));
        
        mvc.perform(MockMvcRequestBuilders.get("/skill/{name}", "Javas")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
        
        mvc.perform(MockMvcRequestBuilders.get("/skill/{name}", "Javas2")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
	}
	
	@Test
	public void getSkillIsActive() throws Exception {
		Skill skill1 = new Skill(99, "Javas", true);
		Skill skill2 = new Skill(100, "Javas2", false);
		Iterable<Skill> active = Arrays.asList(skill1);
		
		when(skillService.findAllActive()).thenReturn((List<Skill>) active);
		
		mvc.perform(MockMvcRequestBuilders.get("/skill/active")
			.accept(MediaType.APPLICATION_JSON))
        	.andExpect(status().isOk());
	}
	
	@Test
	public void putSkill() throws Exception {
		Skill skill1 = new Skill(99, "Javas", true);
		Gson gson = new Gson();
        String json = gson.toJson(skill1);
        
        when(skillService.saveSkill(skill1)).thenReturn(skill1);
        
        mvc.perform(MockMvcRequestBuilders.put("/skill", "{\"skillID\":1,\"skillName\":\"Springs\",\"active\":false}")
                .contentType(MediaType.APPLICATION_JSON).content(json)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
	}
	
}
