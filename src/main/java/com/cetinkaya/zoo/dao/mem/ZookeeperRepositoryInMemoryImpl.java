package com.cetinkaya.zoo.dao.mem;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.cetinkaya.zoo.dao.ZookeeperRespository;
import com.cetinkaya.zoo.model.Zookeeper;

@Repository
public class ZookeeperRepositoryInMemoryImpl implements ZookeeperRespository {

	private Map<Long, Zookeeper> zookeepersMap = new HashMap<>();
	
	public ZookeeperRepositoryInMemoryImpl() {
		Zookeeper zookeeper1 = new Zookeeper();
		zookeeper1.setFirstName("Mesut");
		zookeeper1.setLastName("Kaya");
		zookeeper1.setId(1L);
		
		Zookeeper zookeeper2 = new Zookeeper();
		zookeeper2.setFirstName("Nesrin");
		zookeeper2.setLastName("Kaya");
		zookeeper2.setId(2L);
		
		Zookeeper zookeeper3 = new Zookeeper();
		zookeeper3.setFirstName("Sevinc");
		zookeeper3.setLastName("Kaya");
		zookeeper3.setId(3L);
		
		Zookeeper zookeeper4 = new Zookeeper();
		zookeeper4.setFirstName("Sevim");
		zookeeper4.setLastName("Kaya");
		zookeeper4.setId(4L);
	
		zookeepersMap.put(1L, zookeeper1);
		zookeepersMap.put(2L, zookeeper2);
		zookeepersMap.put(3L, zookeeper3);
		zookeepersMap.put(4L, zookeeper4);
	}
	
	@Override
	public List<Zookeeper> findAll() {
		return new ArrayList<>(zookeepersMap.values());
	}

	@Override
	public Zookeeper findById(Long id) {
		return zookeepersMap.get(id);
	}

	@Override
	public List<Zookeeper> findByLastName(String lastName) {
		return zookeepersMap.values().stream()
				.filter(z -> z.getLastName().equals(lastName))
				.collect(Collectors.toList());
	}

	@Override
	public void create(Zookeeper zookeeper) {
		zookeeper.setId(new Date().getTime());
		zookeepersMap.put(zookeeper.getId(), zookeeper);
	}

	@Override
	public Zookeeper update(Zookeeper zookeeper) {
		zookeepersMap.replace(zookeeper.getId(), zookeeper);
		return zookeeper;
	}

	@Override
	public void delete(Long id) {
		zookeepersMap.remove(id);
	}

}
