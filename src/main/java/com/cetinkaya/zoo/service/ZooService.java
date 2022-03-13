package com.cetinkaya.zoo.service;

import java.util.List;

import javax.validation.Valid;

import com.cetinkaya.zoo.exceptions.VetNotFoundException;
import com.cetinkaya.zoo.exceptions.ZookeeperNotFoundException;
import com.cetinkaya.zoo.model.Vet;
import com.cetinkaya.zoo.model.Zookeeper;

public interface ZooService {

	List<Zookeeper> findZookeepers();
	List<Zookeeper> findZookeepers(String lastName);
	Zookeeper findZookeeper(Long id) throws ZookeeperNotFoundException;
	void createZookeeper(@Valid Zookeeper zookeeper);
	void updateZookeeper(Zookeeper zookeeper);
	void deleteZookeeper(Long id);
	
	List<Vet> findVets();
	Vet findVet(Long id) throws VetNotFoundException;
}
