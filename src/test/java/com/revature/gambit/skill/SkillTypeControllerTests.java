package com.revature.gambit.skill;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import com.revature.gambit.skill.beans.SkillType;
import com.revature.gambit.skill.controllers.SkillTypeController;
import com.revature.gambit.skill.services.SkillTypeServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SkillTypeControllerTests {

	private MockMvc mvc;

	@InjectMocks
	private SkillTypeController skillTypeController;

	@Mock
	private SkillTypeServiceImpl skillTypeServiceImpl;

	@Before
	public void setUp() {
		mvc = MockMvcBuilders.standaloneSetup(skillTypeController).build();
	}

	@Test
	public void postCreate() throws Exception {

		SkillType skill1 = new SkillType(100, "Java", "I can code in Java", true, true);
		Gson gson = new Gson();
		String json = gson.toJson(skill1);

        when(skillTypeServiceImpl.findBySkillTypeName("Java")).thenReturn( skill1);
        when(skillTypeServiceImpl.findBySkillTypeName("Fortran")).thenReturn(skill1);
		when(skillTypeServiceImpl.create(skill1)).thenReturn(skill1);

		mvc.perform(MockMvcRequestBuilders.post("/skillType/")
				.contentType(MediaType.APPLICATION_JSON).content(json)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

        mvc.perform(MockMvcRequestBuilders.get("/skilltype/{name}", "Fortran")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

	}

    @Test
    public void getSkillTypeByNameDoesNotExist() throws Exception {

        mvc.perform(MockMvcRequestBuilders.get("/skilltype/{name}", "jv")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void putSkillType() throws Exception{

        SkillType skill1 = new SkillType(100, "Java", "I can code in Java", true, true);
        Gson gson = new Gson();
        String json = gson.toJson(skill1);


        when(skillTypeServiceImpl.update(skill1, "Java")).thenReturn(true);

        mvc.perform(MockMvcRequestBuilders.put("/skilltype/{name}", "Java")
                .contentType(MediaType.APPLICATION_JSON_UTF8).content(json)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted());

    }


    @Test
    public void putSkillTypeFailed() throws Exception{

    	SkillType skill1 = new SkillType(100, "Java", "I can code in Java", true, true);
        Gson gson = new Gson();
        String json = gson.toJson(skill1);

        when(skillTypeServiceImpl.update(skill1, "Jv")).thenReturn(false);

        mvc.perform(MockMvcRequestBuilders.put("/skilltype/{name}", "Jv")
                .contentType(MediaType.APPLICATION_JSON_UTF8).content(json)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

	
	@Test
	public void testDeleteSkillTypeFunction() throws Exception {
		when(skillTypeServiceImpl.deleteBySkillTypeName("JTA")).thenReturn(true);
	    mvc.perform(MockMvcRequestBuilders.delete("/skilltype/{name}", "JTA")
	             .accept(MediaType.APPLICATION_JSON))
	             .andExpect(status().isAccepted());
	}

}
