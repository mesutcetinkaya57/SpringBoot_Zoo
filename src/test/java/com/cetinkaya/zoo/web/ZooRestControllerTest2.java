package com.cetinkaya.zoo.web;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import com.cetinkaya.zoo.model.Zookeeper;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@ActiveProfiles("dev")
public class ZooRestControllerTest2 {
	
	@Autowired
	private TestRestTemplate restTemplate;
	
//	@Before(value = "")
	@org.junit.Before
	public void setUp() {
		restTemplate = restTemplate.withBasicAuth("user3", "secret");
	}
	
	@Test
	public void testCreateZookeeper() {
		Zookeeper zookeeper = new Zookeeper();
		zookeeper.setFirstName("Nazım");
		zookeeper.setLastName("Cetinkaya");
		
		URI location = restTemplate.postForLocation("http://localhost:8080/rest/zookeeper", zookeeper);
		
		Zookeeper zookeeper2 = restTemplate.getForObject(location, Zookeeper.class);
		
		MatcherAssert.assertThat(zookeeper2.getFirstName(), Matchers.equalTo(zookeeper.getFirstName()));
		MatcherAssert.assertThat(zookeeper2.getLastName(), Matchers.equalTo(zookeeper.getLastName()));
	}
	
	@Test
	public void testUpdateZookeeper() {
		Zookeeper zookeeper = restTemplate.getForObject("http://localhost:8080/rest/zookeeper/4", Zookeeper.class);
		MatcherAssert.assertThat(zookeeper.getFirstName(), Matchers.equalTo("Sevim"));
		zookeeper.setFirstName("Fatma");
		restTemplate.put("http://localhost:8080/rest/zookeeper/4", zookeeper);
		
		zookeeper = restTemplate.getForObject("http://localhost:8080/rest/zookeeper/4", Zookeeper.class);
		MatcherAssert.assertThat(zookeeper.getFirstName(), Matchers.equalTo("Fatma"));
		
		
	}
	
	@Test
	public void testDeleteZookeeper() {
		restTemplate.delete("http://localhost:8080/rest/zookeeper/1");
	
		try {
			restTemplate.getForEntity("http://localhost:8080/rest/zookeeper/1", Zookeeper.class);
//			Assert.fail("should have not returned zookeeper");
		} catch (HttpClientErrorException e) {
			MatcherAssert.assertThat(e.getStatusCode().value(), Matchers.equalTo(404));
		}
		
	
	}
	
	@Test
	public void testGetZookeeperById() {
//		8085'i TCP Monitor'i kullanmak icin setleniyor.
		ResponseEntity<Zookeeper> response =  restTemplate.withBasicAuth("user3", "secret").getForEntity("http://localhost:8085/rest/zookeeper/1", Zookeeper.class);
		MatcherAssert.assertThat(response.getStatusCodeValue(), Matchers.equalTo(200));
		MatcherAssert.assertThat(response.getBody().getFirstName(), Matchers.equalTo("Süleyman"));
	}
	
	@Test
	public void testGetZookeepersByLastName() {
		ResponseEntity<List> response = restTemplate.getForEntity("http://localhost:8080/rest/zookeeper?ln=Kaya", List.class);
		MatcherAssert.assertThat(response.getStatusCodeValue(), Matchers.equalTo(200));
		List<Map<String, String>> responseBody= response.getBody();
		List<String> firstNameList = responseBody.stream().map(b -> b.get("firstName")).collect(Collectors.toList());
		MatcherAssert.assertThat(firstNameList, Matchers.containsInAnyOrder("Mesut","Nesrin","Sevinc","Sevim"));
		
	}
	
	@Test
	public void testGetZookeepers() {
		ResponseEntity<List> response = restTemplate.getForEntity("http://localhost:8080/rest/zookeepers", List.class);
		MatcherAssert.assertThat(response.getStatusCodeValue(), Matchers.equalTo(200));
		List<Map<String, String>> responseBody= response.getBody();
		List<String> firstNameList = responseBody.stream().map(b -> b.get("firstName")).collect(Collectors.toList());
		MatcherAssert.assertThat(firstNameList, Matchers.containsInAnyOrder("Mesut","Nesrin","Sevinc","Sevim"));
		
		
	}

	@Test
	public void testServiceValidation() {
		Zookeeper zookeeper = new Zookeeper();
		
		ResponseEntity<URI> responseEntity = restTemplate.withBasicAuth("user2", "secret").postForEntity("http://localhost:8080/rest/zookeeper", zookeeper, URI.class);
		MatcherAssert.assertThat(responseEntity.getStatusCode(), Matchers.equalTo(HttpStatus.PRECONDITION_FAILED));
	}
}
