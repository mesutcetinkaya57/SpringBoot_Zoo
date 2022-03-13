package com.cetinkaya.zoo;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ZooConfiguration {

	@Autowired
	private ZooProperties zooProperties;
	
	@PostConstruct
	public void init() {
		System.out.println("Display zookeepers with zoos : " + zooProperties.isDisplayZookeepersWithPets());
	}
}
