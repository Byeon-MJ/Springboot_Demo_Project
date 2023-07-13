package com.example.sburrestdemo;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
class Coffee {
	@Id
	private final String id;
	private String name;

	public Coffee(String id, String name){
		this.id = id;
		this.name = name;
	}

	public Coffee(String name){
		this(UUID.randomUUID().toString(), name);
	}

	public String getId(){
		return id;
	}

	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;
	}
}

interface CoffeeRepository extends CrudRepository<Coffee, String> {}