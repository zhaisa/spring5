package com.zhai.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.zhai.domain.Order;
import com.zhai.domain.User;

public interface OrderRepository extends CrudRepository<Order, Long>{
	 Order save(Order order);
	 List<Order> findByUserOrderByPlacedAtDesc(
	          User user, Pageable pageable);

}
