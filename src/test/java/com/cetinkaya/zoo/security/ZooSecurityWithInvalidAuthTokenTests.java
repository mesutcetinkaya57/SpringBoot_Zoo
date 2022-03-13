package com.cetinkaya.zoo.security;

import static org.junit.Assert.assertThrows;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.cetinkaya.zoo.service.ZooService;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.profiles.active=dev")
public class ZooSecurityWithInvalidAuthTokenTests {
	
	@Autowired
	private ZooService zooService;
	
	@Test
	public void testFindZookeepers() {
		TestingAuthenticationToken auth = new TestingAuthenticationToken("user", "secret", "ROLE_XXX");
		SecurityContextHolder.getContext().setAuthentication(auth);
		Throwable thrown = assertThrows(AccessDeniedException.class, () -> zooService.findZookeepers());
		SecurityContextHolder.clearContext();
	}
}
