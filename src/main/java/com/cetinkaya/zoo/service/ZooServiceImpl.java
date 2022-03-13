package com.cetinkaya.zoo.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.cetinkaya.zoo.dao.AnimalRepository;
import com.cetinkaya.zoo.dao.VetRepository;
import com.cetinkaya.zoo.dao.ZookeeperRespository;
import com.cetinkaya.zoo.exceptions.VetNotFoundException;
import com.cetinkaya.zoo.exceptions.ZookeeperNotFoundException;
import com.cetinkaya.zoo.model.Vet;
import com.cetinkaya.zoo.model.Zookeeper;

@Validated
// @Validated annotation'ı ile bu service bean'a ait method'larının ve return değerlerinin validasyona tabi tutulmasını sağlamış oluyoruz.
@Service
@Transactional(rollbackFor = Exception.class)
/* @Transactional annonation'ı ile bu sınıfın bütün public metodları 
 * transactional olacaktır. yani herhangi bir servis metod çağrısında 
 * aktif transaction yoksa yeni bir transaction başlatılacak method'un 
 * başarılı ya da başarısız sonlanmasına göre de commit ya da rollback gerçekleştirilecektir.
 * (rollbackFor = Exception.class) --> checked exception'larda rollback yapması için kullanılır.*/
public class ZooServiceImpl implements ZooService {

	private ZookeeperRespository zookeeperRespository;
	
	private AnimalRepository animalRepository;

	private VetRepository vetRepository;
	
	@Autowired
	public void setZookeeperRespository(ZookeeperRespository zookeeperRespository) {
		this.zookeeperRespository = zookeeperRespository;
	}
	
	@Autowired
	public void setAnimalRepository(AnimalRepository animalRepository) {
		this.animalRepository = animalRepository;
	}
	
	@Autowired
	public void setVetRepository(VetRepository vetRepository) {
		this.vetRepository = vetRepository;
	}
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	@Secured(value = {"ROLE_USER", "ROLE_EDITOR"})
	public List<Zookeeper> findZookeepers() {
		return zookeeperRespository.findAll();
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Zookeeper> findZookeepers(String lastName) {
		return zookeeperRespository.findByLastName(lastName);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Zookeeper findZookeeper(Long id) throws ZookeeperNotFoundException {
		Zookeeper zookeeper = zookeeperRespository.findById(id);
		if (zookeeper == null) throw new ZookeeperNotFoundException("Zookeeper not found with id :" + id);
		return zookeeper;
	}

	@CacheEvict(cacheNames = "allZookeepers")
	@Override
	public void createZookeeper(@Valid Zookeeper zookeeper) {
		zookeeperRespository.create(zookeeper);
		
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setFrom("m@c");
		msg.setTo("n@c");
		msg.setText("Zookeeper entity with id : "+ zookeeper.getId() + " created successfully");
		
		mailSender.send(msg);
	}

	@Override
	public void updateZookeeper(Zookeeper zookeeper) {
		zookeeperRespository.update(zookeeper);
	}

	@Override
	public void deleteZookeeper(Long id) {
		animalRepository.deleteByZookeeperId(id);
		zookeeperRespository.delete(id);
	}

	@Override
	public List<Vet> findVets() {
		return vetRepository.findAll();
	}

	@Override
	public Vet findVet(Long id) throws VetNotFoundException {
		return vetRepository.findById(id).orElseThrow(()->{return new VetNotFoundException("Vet not found by id : " + id);});
	}

}
