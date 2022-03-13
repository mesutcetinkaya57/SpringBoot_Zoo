package com.cetinkaya.zoo.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.cetinkaya.zoo.dao.ZookeeperRespository;
import com.cetinkaya.zoo.model.Zookeeper;

/* ZookeeperRespository @Autowired annotation ı ile enjekte edildiği class'daki ismi 
 * ile @Repository annontation'ın icerisindeki isim eşleşirse spring boot 
 * bu class'ı repository bean'ı olarak algılar*/
@Repository
public class ZookeeperRespositoryJdbcImpl implements ZookeeperRespository{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private RowMapper<Zookeeper> rowMapper = new RowMapper<Zookeeper>() {

		@Override
		public Zookeeper mapRow(ResultSet rs, int rowNum) throws SQLException {
			Zookeeper zookeeper = new Zookeeper();
			zookeeper.setId(rs.getLong("id"));
			zookeeper.setFirstName(rs.getString("first_name"));
			zookeeper.setLastName(rs.getString("last_name"));
			
			return zookeeper;
		}
	};
	
	@Override
	public List<Zookeeper> findAll() {
		String sql = "select id,first_name,last_name from t_zookeeper";
		return jdbcTemplate.query(sql, rowMapper);
	}

	@Override
	public Zookeeper findById(Long id) {
		String sql = "select id,first_name,last_name from t_zookeeper where id=?";
		return DataAccessUtils.singleResult(jdbcTemplate.query(sql, rowMapper,id));
	}

	@Override
	public List<Zookeeper> findByLastName(String lastName) {
		String sql = "select id,first_name,last_name from t_zookeeper where last_name like ?";
		return jdbcTemplate.query(sql, rowMapper, "%" + lastName + "%");
	}

	@Override
	public void create(Zookeeper zookeeper) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Zookeeper update(Zookeeper zookeeper) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long id) {
		String sql = "delete from t_zookeeper where id = ?";
		jdbcTemplate.update(sql,id);
	}

}
