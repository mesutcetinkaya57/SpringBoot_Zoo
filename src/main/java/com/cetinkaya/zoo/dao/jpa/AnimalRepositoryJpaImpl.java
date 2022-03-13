package com.cetinkaya.zoo.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.cetinkaya.zoo.dao.AnimalRepository;
import com.cetinkaya.zoo.model.Animal;
import com.cetinkaya.zoo.model.Zookeeper;

@Repository("animalRepository")
public class AnimalRepositoryJpaImpl implements AnimalRepository {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public Animal findByID(Long id) {
		return entityManager.find(Animal.class, id);
	}

	@Override
	public List<Animal> findByZookeeperId(Long zookeeperId) {
		return entityManager.createQuery("from Animal where zookeeper.id= :zookeeperId ", Animal.class)
				.setParameter("zookeeperId", zookeeperId).getResultList();
	}

	@Override
	public void create(Animal animal) {
		entityManager.persist(animal);
	}

	@Override
	public Animal update(Animal animal) {

		return entityManager.merge(animal);
	}

	@Override
	public void delete(Long id) {
		entityManager.remove(entityManager.getReference(Animal.class, id));
	}

	@Override
	public void deleteByZookeeperId(Long zookeeperId) {
		entityManager.createQuery("delete from Animal where zookeeper.id= :zookeeperId").setParameter("zookeeperId", zookeeperId)
		.executeUpdate();
	}

}
