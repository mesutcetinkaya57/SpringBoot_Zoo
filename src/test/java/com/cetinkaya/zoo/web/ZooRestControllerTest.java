package com.cetinkaya.zoo.web;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.aspectj.lang.annotation.Before;
import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.cetinkaya.zoo.model.Zookeeper;

public class ZooRestControllerTest {
	
	private RestTemplate restTemplate;
	
//	SetUp Methodları her test methodu çalışmadan önce çalışarak icindeki kodu execute eder.
//	Bir nevi testin initialize'ını sağlar.
	@Before(value = "")
	public void setUp() {
		restTemplate = new RestTemplate();
		BasicAuthenticationInterceptor basicAuthenticationInterceptor = new BasicAuthenticationInterceptor("user", "secret");
		restTemplate.setInterceptors(Arrays.asList(basicAuthenticationInterceptor));
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
		ResponseEntity<Zookeeper> response =  restTemplate.getForEntity("http://localhost:8085/rest/zookeeper/1", Zookeeper.class);
		MatcherAssert.assertThat(response.getStatusCodeValue(), Matchers.equalTo(200));
//		MatcherAssert.assertThat(response.getBody().getFirstName(), Matchers.equalTo("Mesut"));
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
}
