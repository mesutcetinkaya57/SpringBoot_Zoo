package com.cetinkaya.zoo.web;

import java.net.URI;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.cetinkaya.zoo.exceptions.InternalServerException;
import com.cetinkaya.zoo.exceptions.ZookeeperNotFoundException;
import com.cetinkaya.zoo.model.Zookeeper;
import com.cetinkaya.zoo.service.ZooService;

@RestController
@RequestMapping("/rest")
public class ZooRestController {

	@Autowired
	private ZooService zooService;
	
	@RequestMapping(method = RequestMethod.POST, value = "/zookeeper")
	public ResponseEntity<URI> createZookeeper(@RequestBody Zookeeper zookeeper){
		try {
			zooService.createZookeeper(zookeeper);
			Long id = zookeeper.getId();
			URI location = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(id).toUri();
			return ResponseEntity.created(location).build();
		} catch (ConstraintViolationException e) {
			return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/zookeeper/{id}")
	public ResponseEntity<?> updateZookeeper(@PathVariable Long id, @RequestBody Zookeeper zookeeperRequest ){
		try {
			Zookeeper zookeeper = zooService.findZookeeper(id);
			zookeeper.setFirstName(zookeeperRequest.getFirstName());
			zookeeper.setLastName(zookeeperRequest.getLastName());
			zooService.updateZookeeper(zookeeper);
			return ResponseEntity.ok().build();
		} catch (ZookeeperNotFoundException e) {
			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/zookeeper/{id}")
	public ResponseEntity<?> deleteZookeeper(@PathVariable("id") Long id) {
		try {
			zooService.findZookeeper(id);
			zooService.deleteZookeeper(id);
			return ResponseEntity.ok().build();
		} catch (ZookeeperNotFoundException e) {
			throw e;
		} catch (Exception e) {
			throw new InternalServerException(e);
		}
	}
	
	@Cacheable("allZookeepers")
	@RequestMapping(method = RequestMethod.GET, value = "/zookeepers")
	public ResponseEntity<List<Zookeeper>> getZookeepers(){
		System.out.println(">>>> Cache'in calisirlik kontrolu : inside getZookeepers...");
		try {
		List<Zookeeper> zookeepers = zooService.findZookeepers();
		return ResponseEntity.ok(zookeepers);
	} catch (Exception e) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	} 
	
	@RequestMapping(method = RequestMethod.GET, value = "/zookeeper")
	public ResponseEntity<List<Zookeeper>> getZookeepers(@RequestParam("ln") String lastName){

		try {
			List<Zookeeper> zookeepers = zooService.findZookeepers(lastName);
			
			return ResponseEntity.ok(zookeepers);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/owner/{id}", produces = "application/json")
	public ResponseEntity<?> getZookeeperAsHateoasResource(@PathVariable("id") Long id){
		try {
			Zookeeper zookeeper = zooService.findZookeeper(id);
			return ResponseEntity.ok(zookeeper);
		} catch (ZookeeperNotFoundException e) {
			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/zookeeper/{id}")
	public ResponseEntity<?> getZookeeper(@PathVariable("id") Long id){
		try {
			Zookeeper zookeeper = zooService.findZookeeper(id);
			return ResponseEntity.ok(zookeeper);
		} catch (ZookeeperNotFoundException e) {
			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	} 
}
