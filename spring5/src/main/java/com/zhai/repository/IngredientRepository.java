package com.zhai.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.zhai.domain.Ingredient;
public interface IngredientRepository extends CrudRepository<Ingredient, String>{
//	 @Query(value="select id, name, type from Ingredient",nativeQuery = true)
//    Iterable<Ingredient> findAll(@Param("id")String id,@Param("name")String name,@Param("type")String type);
//	 @Query(value="select id, name, type from Ingredient where id=?1",nativeQuery = true)
//    Ingredient findOne(String id);
//	
//    Ingredient save(Ingredient ingredient);
//    
   
}
