package com.zhai.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.zhai.domain.Ingredient;
import com.zhai.domain.Taco;

public interface TacoRepository extends CrudRepository<Taco, Long>{
//	 Taco save(Taco taco);
//	 @Query(value ="insert into Taco_Ingredients(taco,ingredient) values(?1, ?2)",nativeQuery = true)
//	 void saveIngredientToTaco(Ingredient ingredient, long tacoId);
//	 @Query(value ="insert into Taco(name,createdAt) values(?1, ?2)",nativeQuery = true)
//	 Taco saveTacoInfo(Taco taco);

}
