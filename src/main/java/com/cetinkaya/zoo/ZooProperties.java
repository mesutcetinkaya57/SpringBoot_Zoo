package com.cetinkaya.zoo;

import org.springframework.boot.context.properties.ConfigurationProperties;

/*
 * Type safety olarak application.properties veya application.yaml dosyasındaki değerleri @ConfigurationProperties 
 * annotation'ı ile bu şekilde class ile alınabilir.
 * */

@ConfigurationProperties(prefix = "zoo")
public class ZooProperties {
	private boolean displayZookeepersWithPets = false;

	public boolean isDisplayZookeepersWithPets() {
		return displayZookeepersWithPets;
	}

	public void setDisplayZookeepersWithPets(boolean displayZookeepersWithPets) {
		this.displayZookeepersWithPets = displayZookeepersWithPets;
	}
	
}
