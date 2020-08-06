package com.zhai.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.zhai.domain.Ingredient;
import com.zhai.domain.Ingredient.Type;
import com.zhai.domain.Order;
import com.zhai.domain.Taco;
import com.zhai.domain.User;
import com.zhai.repository.IngredientRepository;
import com.zhai.repository.TacoRepository;
import com.zhai.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {
	@Autowired
	private IngredientRepository ingredientRepo;
	@Autowired
	private TacoRepository designRepo;
	@Autowired
	 private UserRepository userRepo;
//	@Autowired
//	public DesignTacoController(IngredientRepository ingredientRepo) {
//		this.ingredientRepo = ingredientRepo;
//	}

	
//	public DesignTacoController(IngredientRepository ingredientRepo, TacoRepository designRepo,UserRepository userRepo) {
//		this.ingredientRepo = ingredientRepo;
//		this.designRepo = designRepo;
//		this.userRepo=userRepo;
//	}
	 @ModelAttribute(name = "order")
	  public Order order() {
	    return new Order();
	  }
	  
	  @ModelAttribute(name = "design")
	  public Taco design() {
	    return new Taco();
	  }
	@GetMapping
	public String showDesignForm(Model model,Principal principal) {
		 log.info("   --- Designing taco");
		List<Ingredient> ingredients = new ArrayList<>();
		ingredientRepo.findAll().forEach(i -> ingredients.add(i));
		log.info("取值- - -   -  -   - >"+ingredients);
		Type[] types = Ingredient.Type.values();
		for (Type type : types) {
			model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
		}

		 String username = principal.getName();
		    User user = userRepo.findByUsername(username);
		    model.addAttribute("user", user);
		return "design";
	}

	// provided by 'aexiaosong'
	private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
		return ingredients.stream().filter(x -> x.getType().equals(type)).collect(Collectors.toList());
	}




	@PostMapping
	public String processDesign(@Valid Taco design, Errors errors, @ModelAttribute Order order) {
		// Save the taco design...
		// We'll do this in chapter 3
//		if (errors.hasErrors()) {
//			return "design";
//		}

		log.info("Processing design: " + design);
		
		Taco saved = designRepo.save(design);
		order.addDesign(saved);
		return "redirect:/orders/current";
	}
}
