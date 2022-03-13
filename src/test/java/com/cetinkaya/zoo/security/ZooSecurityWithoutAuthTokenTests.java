package com.cetinkaya.zoo.security;

import static org.junit.Assert.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.cetinkaya.zoo.service.ZooService;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.profiles.active=dev")
public class ZooSecurityWithoutAuthTokenTests {
	
	@Autowired
	private ZooService zooService;

	@Test
	public void testFindZookeepers() {
//		zooService.findZookeepers();
		Throwable thrown = assertThrows(AuthenticationCredentialsNotFoundException.class, () -> zooService.findZookeepers());
	}
}
