package com.zhai.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Transient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zhai.domain.Ingredient.Type;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@Entity
@Table(name="taco")
public class Taco {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@JoinColumn(name = "id", referencedColumnName = "taco")
	private Long id;

	@NotNull
	@Size(min = 5, message = "Name must be at least 5 characters long")
	private String name;
	
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createdAt;
	/*
	 * 要声明 Taco 及其相关 Ingredient 列表之间的关系，可以使用 @ManyToMany 注解 ingredient 属性。 一个 Taco
	 * 可以有很多 Ingredient，一个 Ingredient 可以是很多 Taco 的一部分。
	 */
	@ManyToMany(targetEntity = Ingredient.class)
	@Size(min = 1, message = "You must choose at least 1 ingredient")
	private List<Ingredient> ingredients;

	/*
	 * 
	 * 还有一个新方法 createdAt()，它用 @PrePersist 注解。将使用它将 createdAt 属性设置为保存 Taco
	 * 之前的当前日期和时间。
	 */
	@PrePersist
	public void createdAt() {
		this.createdAt = new Date();
	}


	
}
