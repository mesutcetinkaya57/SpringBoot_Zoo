package com.cetinkaya.zoo.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.cetinkaya.zoo.dao.ZookeeperRespository;
import com.cetinkaya.zoo.model.Zookeeper;

/* ZookeeperRespository @Autowired annotation ı ile enjekte edildiği class'daki ismi 
 * ile @Repository annontation'ın icerisindeki isim eşleşirse spring boot 
 * bu class'ı repository bean'ı olarak algılar*/
@Repository("zookeeperRespository")
public class ZookeeperRepositoryJpaImpl implements ZookeeperRespository {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<Zookeeper> findAll() {
		return entityManager.createQuery("from Zookeeper", Zookeeper.class).getResultList();
	}

	@Override
	public Zookeeper findById(Long id) {
		return entityManager.find(Zookeeper.class, id);
	}

	@Override
	public List<Zookeeper> findByLastName(String lastName) {
		return entityManager.createQuery("from Zookeeper where lastname= :lastName", Zookeeper.class)
				.setParameter("lastName", lastName).getResultList();
	}

	@Override
	public void create(Zookeeper zookeeper) {
		entityManager.persist(zookeeper);
	}

	@Override
	public Zookeeper update(Zookeeper zookeeper) {

		return entityManager.merge(zookeeper);
	}

	@Override
	public void delete(Long id) {
		entityManager.remove(entityManager.getReference(Zookeeper.class, id));
	}

}
