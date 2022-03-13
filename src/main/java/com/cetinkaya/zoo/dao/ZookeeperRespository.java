package com.cetinkaya.zoo.dao;

import java.util.List;

import com.cetinkaya.zoo.model.Zookeeper;

public interface ZookeeperRespository {

	List<Zookeeper> findAll();
	Zookeeper findById(Long id);
	List<Zookeeper> findByLastName(String lastName);
	void create(Zookeeper zookeeper);
	Zookeeper update(Zookeeper zookeeper);
	void delete(Long id);
}
