package com.zhai;


import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.zhai.controller.DesignTacoController;
import com.zhai.domain.Ingredient;
import com.zhai.domain.Ingredient.Type;
import com.zhai.domain.Taco;
import com.zhai.domain.User;
import com.zhai.repository.IngredientRepository;
import com.zhai.repository.OrderRepository;
import com.zhai.repository.TacoRepository;
import com.zhai.repository.UserRepository;


@RunWith(SpringRunner.class)
@WebMvcTest(DesignTacoController.class)
public class DesignTacoControllerTest {
	@Autowired
	private MockMvc mockmvc;
	private List<Ingredient> ingredients;
	  private MockHttpSession session;
	  private Taco design;
	  @MockBean
	  private IngredientRepository ingredientRepository;

	  @MockBean
	  private TacoRepository designRepository;

	  @MockBean
	  private OrderRepository orderRepository;
	  @MockBean
	  private UserRepository userRepository;
	@Before
	public void setup() {
		 session = new MockHttpSession();
		 ingredientRepository.findAll().forEach(i -> ingredients.add(i));
//			    when(ingredientRepository.findAll())
//			        .thenReturn(ingredients);
//			        
//			    when(ingredientRepository.findById("FLTO")).thenReturn(Optional.of(new Ingredient("FLTO", "Flour Tortilla", Type.WRAP)));
//			    when(ingredientRepository.findById("GRBF")).thenReturn(Optional.of(new Ingredient("GRBF", "Ground Beef", Type.PROTEIN)));
//			    when(ingredientRepository.findById("CHED")).thenReturn(Optional.of(new Ingredient("CHED", "Cheddar", Type.CHEESE)));
			    
		 design = new Taco();
		    design.setName("Test Taco");

		    design.setIngredients(Arrays.asList(
		        new Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
		        new Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
		        new Ingredient("CHED", "Cheddar", Type.CHEESE)
		    	));
		    when(userRepository.findByUsername("zhai"))
    		.thenReturn(new User("zhai", "$2a$10$zALOMEN88tihmZpmfjG5EO8rLDBNjv3zbFZJTqGBpA.Avqr1EVJRi", "zhaidg", "beijing", "beijing", "1", "2", "18909898999"));

	}
	@Test
	@WithMockUser(username="zhai", password="123456",authorities="ROLE_USERS")
	public void testshowDesignForm() throws Exception {

	        mockmvc.perform(get("/design"))
	        .andExpect(status().isOk())
	        .andExpect(view().name("design"))
//	        .andExpect(model().attribute("wrap", ingredients.subList(0, 2)))
//	        .andExpect(MockMvcResultMatchers.model().attribute("protein", ingredients.subList(2, 4)))
//	        .andExpect(model().attribute("veggies", ingredients.subList(4, 6)))
//	        .andExpect(model().attribute("cheese", ingredients.subList(6, 8)))
//	        .andExpect(model().attribute("sauce", ingredients.subList(8, 10)));
	     .andExpect(content().string(containsString("Name your taco creation")));
	}
	
	@Test
	  @WithMockUser(username="zhai", password="123456", authorities="ROLE_USERS")
	  public void processDesign() throws Exception {
	    when(designRepository.save(design))
	        .thenReturn(design);
	    
	    mockmvc.perform(post("/design").with(csrf())
	        .content("name=Test+Taco&ingredients=FLTO,GRBF,CHED")
	        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
	        .andExpect(status().is3xxRedirection())
	        .andExpect(header().stringValues("Location", "/orders/current"));
	  }
}
