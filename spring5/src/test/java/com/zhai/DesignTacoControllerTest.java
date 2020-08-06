package com.zhai;


import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;

import com.zhai.controller.DesignTacoController;
import com.zhai.repository.IngredientRepository;



@RunWith(SpringRunner.class)
@WebMvcTest(DesignTacoController.class)
@ComponentScan(basePackages = {"com.zhai.repository.IngredientRepository"})
public class DesignTacoControllerTest {
	@Autowired
	private MockMvc mockmvc;
	@Test
	public void testshowDesignForm(Model model) throws Exception {
		mockmvc.perform(get("/design"))
		.andExpect(status().isOk())
		.andExpect(view().name("design"))
		.andExpect(content().string(containsString("INGREDIENT")));
	}
}
