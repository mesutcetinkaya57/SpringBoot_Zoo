package com.cetinkaya.zoo.dao;

import static org.junit.Assert.assertThrows;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import org.junit.Rule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.cetinkaya.zoo.model.Zookeeper;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
@Transactional
public class ZookeeperRepositoryTests {

	@Autowired
	private ZookeeperRespository zookeeperRespository;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Test
	public void testCreateZookeeper() {

		
		Zookeeper zookeeper = new Zookeeper();
		zookeeper.setFirstName(null);
		zookeeper.setLastName(null);
		
		zookeeperRespository.create(zookeeper);
        Throwable thrown = assertThrows(PersistenceException.class, () -> entityManager.flush());

		
	}
}
