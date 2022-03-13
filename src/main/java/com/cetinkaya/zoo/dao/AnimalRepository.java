package com.cetinkaya.zoo.dao;

import java.util.List;

import com.cetinkaya.zoo.model.Animal;

public interface AnimalRepository {

	Animal findByID(Long id);
	List<Animal> findByZookeeperId(Long zookeeperId);
	void create(Animal animal);
	Animal update(Animal animal);
	void delete(Long id);
	void deleteByZookeeperId(Long zookeeperId);
	
}
