package com.cetinkaya.zoo;

import java.util.List;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cetinkaya.zoo.model.Vet;
import com.cetinkaya.zoo.model.Zookeeper;
import com.cetinkaya.zoo.service.ZooService;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = {"spring.profiles.active=dev"})
public class ZooIntegrationTests {
	
	@Autowired
	private ZooService zooService;

	@Test
	public void testFindOwners(){
		List<Zookeeper> zookeepers = zooService.findZookeepers();
		MatcherAssert.assertThat(zookeepers.size(), Matchers.equalTo(10));
	}
	
	@Test
	public void testFindVets() {
		List<Vet> vets = zooService.findVets();
		MatcherAssert.assertThat(vets.size(), Matchers.equalTo(3));
	}
}
