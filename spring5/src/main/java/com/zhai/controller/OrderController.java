package com.zhai.controller;


import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.zhai.domain.Order;
import com.zhai.domain.User;
import com.zhai.repository.OrderRepository;
import com.zhai.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
@ConfigurationProperties(prefix="taco.orders")
public class OrderController {
	  @Autowired
	  UserRepository userRepository;
	  @Autowired
	  OrderRepository orderRepo;
	  private OrderProps props;
	  private int pageSize = 20;
	    
	    public void setPageSize(int pageSize) {
	        this.pageSize = pageSize;
	    }
	  @GetMapping("/current")
	    public String orderForm(@AuthenticationPrincipal User user, 
	    	      @ModelAttribute Order order) {
	        if (order.getDeliveryName() == null) {
	          order.setDeliveryName(user.getFullname());
	        }
	        if (order.getDeliveryStreet() == null) {
	          order.setDeliveryStreet(user.getStreet());
	        }
	        if (order.getDeliveryCity() == null) {
	          order.setDeliveryCity(user.getCity());
	        }
	        if (order.getDeliveryState() == null) {
	          order.setDeliveryState(user.getState());
	        }
	        if (order.getDeliveryZip() == null) {
	          order.setDeliveryZip(user.getZip());
	        }
	        
	        return "orderForm";

	    }
	 /*
	  * @AuthenticationPrincipal 的优点在于它不需要强制转换（与身份验证一样），并且将特定于安全性的代码限制为注释本身。当在 processOrder() 中获得 User 对象时，它已经准备好被使用并分配给订单了。
	  *  
	  *  
	  *  
	  */
	  @PostMapping
	  public String processOrder(@Valid Order order, Errors errors,SessionStatus sessionStatus,
			  @AuthenticationPrincipal User user) {
		  if (errors.hasErrors()) {
		        return "orderForm";
		    }
		  order.setUser(user);
		    
		    orderRepo.save(order);
		    sessionStatus.setComplete();
	      log.info("Order submitted: " + order);
	      return "redirect:/";
	  }
	  
	  @GetMapping
	  public String ordersForUser(
	      @AuthenticationPrincipal User user, Model model) {
		  Pageable pageable = PageRequest.of(0, pageSize);
	      model.addAttribute("orders",
	          orderRepo.findAll());
	      
	      return "orderList";
	  }
}
