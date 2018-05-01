package com.revature.gambit.skill;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.revature.gambit.skill.beans.SkillType;
import com.revature.gambit.skill.services.SkillTypeService;
import org.junit.Before;
import org.junit.BeforeClass;
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

import com.revature.gambit.skill.controllers.SkillTypeController;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SkillTypeControllerTests {

	private static ObjectMapper mapper;

	private MockMvc mvc;

	@InjectMocks
	private SkillTypeController skillTypeController;

	@Mock
	private SkillTypeService skillTypeService;

	@BeforeClass
	public static void setUpClass() {
		mapper = new ObjectMapper();
	}

	@Before
	public void setUp() {
		mvc = MockMvcBuilders.standaloneSetup(skillTypeController).build();
	}

	@Test
	public void postCreate() throws Exception {

		SkillType skill1 = new SkillType(100, "Java", "I can code in Java", true, true);
		Gson gson = new Gson();
		String json = gson.toJson(skill1);

		when(skillTypeService.create(skill1)).thenReturn(skill1);
		mvc.perform(MockMvcRequestBuilders.post("/skillType/").contentType(MediaType.APPLICATION_JSON).content(json)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());
	}

	@Test
	public void testDeleteSkillTypeID() throws Exception {
		// Create the skillType to delete
		SkillType skillType = new SkillType(1, "Test", "Test delete by id", true, true);
		SkillType expectedSkillType = new SkillType(1, "Test", "Test delete by id", false, true);
		when(skillTypeService.update(skillType)).thenReturn(expectedSkillType);

		// Delete the newly added skill.
		mvc.perform(MockMvcRequestBuilders.delete("/skilltype/{id}", skillType.getSkillTypeId())
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isAccepted());
	}

	@Test
	public void testDeleteSkillTypeNameInvalidID() throws Exception {
		mvc.perform(MockMvcRequestBuilders.delete("/skilltype/{id}", 1000).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isAccepted());
	}

	@Test
	public void testDeleteSkillTypeName() throws Exception {
		// Create the skillType to delete
		SkillType skillType = new SkillType(1, "Test", "Test delete by id", true, true);
		SkillType expectedSkillType = new SkillType(1, "Test", "Test delete by id", false, true);
		when(skillTypeService.update(skillType)).thenReturn(expectedSkillType);

		mvc.perform(MockMvcRequestBuilders.delete("/skilltype/name/{name}", "Test").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isAccepted());
	}

	@Test
	public void testDeleteSkillTypeNameInvalidName() throws Exception {
		mvc.perform(MockMvcRequestBuilders.delete("/skilltype/name/{name}", "Tests").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isAccepted());
	}

	@Test
	public void getSkillType() throws Exception {

		SkillType skill1 = new SkillType(100, "Java", "I can code in Java", true, true);
		SkillType skill2 = new SkillType(101, "Fortran", "What is Fortran", true, true);

		Iterable<SkillType> skills = Arrays.asList(skill1, skill2);

		when(skillTypeService.findAll()).thenReturn((List<SkillType>) skills);

		mvc.perform(MockMvcRequestBuilders.get("/skillType").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	public void getSkillTypeById() throws Exception {

		SkillType skill1 = new SkillType(100, "Java", "I can code in Java", true, true);
		SkillType skill2 = new SkillType(101, "Fortran", "What is Fortran", true, true);

		Iterable<SkillType> skills = Arrays.asList(skill1, skill2);

		when(skillTypeService.findBySkillTypeId(100)).thenReturn(((List<SkillType>) skills).get(0));
		when(skillTypeService.findBySkillTypeId(101)).thenReturn(((List<SkillType>) skills).get(1));

		mvc.perform(MockMvcRequestBuilders.get("/skillType/{id}", 100).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

		mvc.perform(MockMvcRequestBuilders.get("/skillType/{id}", 101).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	public void getSkillTypeByName() throws Exception {

		SkillType skill1 = new SkillType(100, "Java", "I can code in Java", true, true);
		SkillType skill2 = new SkillType(101, "Fortran", "What is Fortran", true, true);

		Iterable<SkillType> skills = Arrays.asList(skill1, skill2);

		when(skillTypeService.findBySkillTypeName("Java")).thenReturn(((List<SkillType>) skills).get(0));
		when(skillTypeService.findBySkillTypeName("Fortran")).thenReturn(((List<SkillType>) skills).get(1));

		mvc.perform(MockMvcRequestBuilders.get("/skillType/name/{name}", "Java").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

		mvc.perform(MockMvcRequestBuilders.get("/skillType/name/{name}", "Fortran").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	public void putSkillByNameType() throws Exception {

		SkillType skill1 = new SkillType(100, "Java", "I can code in Java", true, true);
		Gson gson = new Gson();
		String json = gson.toJson(skill1);

		when(skillTypeService.update(skill1)).thenReturn(skill1);

		mvc.perform(MockMvcRequestBuilders.put("/skillType/name/{id}", "Java").contentType(MediaType.APPLICATION_JSON)
				.content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isAccepted());

	}

	@Test
	public void putSkillTypeNameFailed() throws Exception {

		SkillType skill1 = new SkillType(100, "Java", "I can code in Java", true, true);
		Gson gson = new Gson();
		String json = gson.toJson(skill1);

		when(skillTypeService.update(skill1)).thenReturn(skill1);

		mvc.perform(MockMvcRequestBuilders.put("/skillType/name/{name}", "jv").contentType(MediaType.APPLICATION_JSON)
				.content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
	}

	@Test
	public void putSkillTypeById() throws Exception {

		SkillType skill1 = new SkillType(100, "Java", "I can code in Java", true, true);
		Gson gson = new Gson();
		String json = gson.toJson(skill1);

		when(skillTypeService.update(skill1)).thenReturn(skill1);

		mvc.perform(MockMvcRequestBuilders.put("/skillType/{id}", 100).contentType(MediaType.APPLICATION_JSON)
				.content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isAccepted());
	}

	@Test
	public void putSkillTypeByIdFailed() throws Exception {

		SkillType skill1 = new SkillType(100, "Java", "I can code in Java", true, true);
		Gson gson = new Gson();
		String json = gson.toJson(skill1);

		when(skillTypeService.update(skill1)).thenReturn(skill1);

		mvc.perform(MockMvcRequestBuilders.put("/skillType/{id}", 101).contentType(MediaType.APPLICATION_JSON)
				.content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
	}

}
