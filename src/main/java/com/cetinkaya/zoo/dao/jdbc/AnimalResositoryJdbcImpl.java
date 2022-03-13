package com.cetinkaya.zoo.dao.jdbc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cetinkaya.zoo.dao.AnimalRepository;
import com.cetinkaya.zoo.model.Animal;

@Repository
public class AnimalResositoryJdbcImpl implements AnimalRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public Animal findByID(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Animal> findByZookeeperId(Long zookeeperId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void create(Animal animal) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Animal update(Animal animal) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteByZookeeperId(Long zookeeperId) {
		String sql = "delete from t_animal where zookeeper_id=?";
		jdbcTemplate.update(sql, zookeeperId);
	}

}
